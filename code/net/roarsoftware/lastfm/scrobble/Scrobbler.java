package net.roarsoftware.lastfm.scrobble;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import net.roarsoftware.lastfm.Caller;
import net.roarsoftware.lastfm.Session;
import net.roarsoftware.util.StringUtilities;

public class Scrobbler
{
  private static final String HANDSHAKE_URL = "http://post.audioscrobbler.com/";
  private final String clientId;
  private final String clientVersion;
  private String nowPlayingUrl;
  private String sessionId;
  private String submissionUrl;
  private final String user;
  
  private Scrobbler(String paramString1, String paramString2, String paramString3)
  {
    this.clientId = paramString1;
    this.clientVersion = paramString2;
    this.user = paramString3;
  }
  
  public static Scrobbler newScrobbler(String paramString1, String paramString2, String paramString3)
  {
    return new Scrobbler(paramString1, paramString2, paramString3);
  }
  
  private ResponseStatus performHandshake(String paramString)
    throws IOException
  {
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(Caller.getInstance().openConnection(paramString).getInputStream()));
    paramString = localBufferedReader.readLine();
    int i = ResponseStatus.codeForStatus(paramString);
    if (i == 0)
    {
      this.sessionId = localBufferedReader.readLine();
      this.nowPlayingUrl = localBufferedReader.readLine();
      this.submissionUrl = localBufferedReader.readLine();
    }
    for (paramString = new ResponseStatus(i);; paramString = new ResponseStatus(i, paramString.substring(paramString.indexOf(' ') + 1)))
    {
      localBufferedReader.close();
      return paramString;
      if (i != 5) {
        break;
      }
    }
    return new ResponseStatus(i);
  }
  
  public ResponseStatus handshake(String paramString)
    throws IOException
  {
    long l = System.currentTimeMillis() / 1000L;
    paramString = StringUtilities.md5(StringUtilities.md5(paramString) + l);
    return performHandshake(String.format("%s?hs=true&p=1.2.1&c=%s&v=%s&u=%s&t=%s&a=%s", new Object[] { "http://post.audioscrobbler.com/", this.clientId, this.clientVersion, this.user, Long.valueOf(l), paramString }));
  }
  
  public ResponseStatus handshake(Session paramSession)
    throws IOException
  {
    long l = System.currentTimeMillis() / 1000L;
    String str = StringUtilities.md5(paramSession.getSecret() + l);
    return performHandshake(String.format("%s?hs=true&p=1.2.1&c=%s&v=%s&u=%s&t=%s&a=%s&api_key=%s&sk=%s", new Object[] { "http://post.audioscrobbler.com/", this.clientId, this.clientVersion, this.user, Long.valueOf(l), str, paramSession.getApiKey(), paramSession.getKey() }));
  }
  
  public ResponseStatus nowPlaying(String paramString1, String paramString2)
    throws IOException
  {
    return nowPlaying(paramString1, paramString2, null, -1, -1);
  }
  
  public ResponseStatus nowPlaying(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.sessionId == null) {
      throw new IllegalStateException("Perform successful handshake first.");
    }
    String str1;
    if (paramString3 != null)
    {
      paramString3 = StringUtilities.encode(paramString3);
      if (paramInt1 != -1) {
        break label229;
      }
      str1 = "";
      label36:
      if (paramInt2 != -1) {
        break label239;
      }
    }
    label229:
    label239:
    for (String str2 = "";; str2 = String.valueOf(paramInt2))
    {
      paramString1 = String.format("s=%s&a=%s&t=%s&b=%s&l=%s&n=%s&m=", new Object[] { this.sessionId, StringUtilities.encode(paramString1), StringUtilities.encode(paramString2), paramString3, str1, str2 });
      if (Caller.getInstance().isDebugMode()) {
        System.out.println("now playing: " + paramString1);
      }
      Caller.getInstance().getProxy();
      paramString2 = Caller.getInstance().openConnection(this.nowPlayingUrl);
      paramString2.setRequestMethod("POST");
      paramString2.setDoOutput(true);
      paramString3 = new BufferedWriter(new OutputStreamWriter(paramString2.getOutputStream()));
      paramString3.write(paramString1);
      paramString3.close();
      paramString1 = new BufferedReader(new InputStreamReader(paramString2.getInputStream()));
      paramString2 = paramString1.readLine();
      paramString1.close();
      return new ResponseStatus(ResponseStatus.codeForStatus(paramString2));
      paramString3 = "";
      break;
      str1 = String.valueOf(paramInt1);
      break label36;
    }
  }
  
  public ResponseStatus submit(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, Source paramSource, long paramLong)
    throws IOException
  {
    return submit(new SubmissionData(paramString1, paramString2, paramString3, paramInt1, paramInt2, paramSource, paramLong));
  }
  
  public ResponseStatus submit(Collection<SubmissionData> paramCollection)
    throws IOException
  {
    if (this.sessionId == null) {
      throw new IllegalStateException("Perform successful handshake first.");
    }
    if (paramCollection.size() > 50) {
      throw new IllegalArgumentException("Max 50 submissions at once");
    }
    Object localObject = new StringBuilder(paramCollection.size() * 100);
    int i = 0;
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      ((StringBuilder)localObject).append(((SubmissionData)paramCollection.next()).toString(this.sessionId, i));
      ((StringBuilder)localObject).append('\n');
      i += 1;
    }
    paramCollection = ((StringBuilder)localObject).toString();
    if (Caller.getInstance().isDebugMode()) {
      System.out.println("submit: " + paramCollection);
    }
    localObject = Caller.getInstance().openConnection(this.submissionUrl);
    ((HttpURLConnection)localObject).setRequestMethod("POST");
    ((HttpURLConnection)localObject).setDoOutput(true);
    BufferedWriter localBufferedWriter = new BufferedWriter(new OutputStreamWriter(((HttpURLConnection)localObject).getOutputStream()));
    localBufferedWriter.write(paramCollection);
    localBufferedWriter.close();
    paramCollection = new BufferedReader(new InputStreamReader(((HttpURLConnection)localObject).getInputStream()));
    localObject = paramCollection.readLine();
    paramCollection.close();
    i = ResponseStatus.codeForStatus((String)localObject);
    if (i == 5) {
      return new ResponseStatus(i, ((String)localObject).substring(((String)localObject).indexOf(' ') + 1));
    }
    return new ResponseStatus(i);
  }
  
  public ResponseStatus submit(SubmissionData paramSubmissionData)
    throws IOException
  {
    return submit(Collections.singletonList(paramSubmissionData));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\scrobble\Scrobbler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */