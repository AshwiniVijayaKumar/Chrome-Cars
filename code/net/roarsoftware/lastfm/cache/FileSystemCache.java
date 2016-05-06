package net.roarsoftware.lastfm.cache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import net.roarsoftware.lastfm.scrobble.Scrobbler;
import net.roarsoftware.lastfm.scrobble.SubmissionData;

public class FileSystemCache
  extends Cache
  implements ScrobbleCache
{
  private static final String SUBMISSIONS_FILE = "submissions.txt";
  private File cacheDir;
  
  public FileSystemCache()
  {
    this(new File(System.getProperty("user.home") + "/.last.fm-cache"));
  }
  
  public FileSystemCache(File paramFile)
  {
    this.cacheDir = paramFile;
  }
  
  private void createCache()
  {
    if (!this.cacheDir.exists())
    {
      this.cacheDir.mkdirs();
      if (!this.cacheDir.isDirectory()) {
        this.cacheDir = this.cacheDir.getParentFile();
      }
    }
  }
  
  public void cacheScrobble(Collection<SubmissionData> paramCollection)
  {
    cacheScrobble((SubmissionData[])paramCollection.toArray(new SubmissionData[paramCollection.size()]));
  }
  
  public void cacheScrobble(SubmissionData... paramVarArgs)
  {
    createCache();
    try
    {
      BufferedWriter localBufferedWriter = new BufferedWriter(new FileWriter(new File(this.cacheDir, "submissions.txt"), true));
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        localBufferedWriter.append(paramVarArgs[i].toString());
        localBufferedWriter.newLine();
        i += 1;
      }
      localBufferedWriter.close();
      return;
    }
    catch (IOException paramVarArgs) {}
  }
  
  public void clear()
  {
    File[] arrayOfFile = this.cacheDir.listFiles();
    int j = arrayOfFile.length;
    int i = 0;
    while (i < j)
    {
      File localFile = arrayOfFile[i];
      if (localFile.isFile()) {
        localFile.delete();
      }
      i += 1;
    }
  }
  
  public void clearScrobbleCache()
  {
    new File(this.cacheDir, "submissions.txt").delete();
  }
  
  public boolean contains(String paramString)
  {
    return new File(this.cacheDir, paramString + ".xml").exists();
  }
  
  public boolean isEmpty()
  {
    Object localObject = new File(this.cacheDir, "submissions.txt");
    if (!((File)localObject).exists()) {}
    for (;;)
    {
      return true;
      try
      {
        localObject = new BufferedReader(new FileReader((File)localObject));
        String str = ((BufferedReader)localObject).readLine();
        ((BufferedReader)localObject).close();
        if (str != null)
        {
          boolean bool = "".equals(str);
          if (!bool) {
            return false;
          }
        }
      }
      catch (IOException localIOException) {}
    }
    return true;
  }
  
  public boolean isExpired(String paramString)
  {
    paramString = new File(this.cacheDir, paramString + ".meta");
    if (!paramString.exists()) {}
    for (;;)
    {
      return false;
      try
      {
        Properties localProperties = new Properties();
        localProperties.load(new FileInputStream(paramString));
        long l1 = Long.valueOf(localProperties.getProperty("expiration-date")).longValue();
        long l2 = System.currentTimeMillis();
        if (l1 < l2) {
          return true;
        }
      }
      catch (IOException paramString) {}
    }
    return false;
  }
  
  public InputStream load(String paramString)
  {
    try
    {
      paramString = new FileInputStream(new File(this.cacheDir, paramString + ".xml"));
      return paramString;
    }
    catch (FileNotFoundException paramString) {}
    return null;
  }
  
  public void remove(String paramString)
  {
    new File(this.cacheDir, paramString + ".xml").delete();
    new File(this.cacheDir, paramString + ".meta").delete();
  }
  
  public void scrobble(Scrobbler paramScrobbler)
    throws IOException
  {
    File localFile = new File(this.cacheDir, "submissions.txt");
    if (localFile.exists())
    {
      BufferedReader localBufferedReader = new BufferedReader(new FileReader(localFile));
      ArrayList localArrayList = new ArrayList(50);
      for (;;)
      {
        String str = localBufferedReader.readLine();
        if (str == null) {
          break;
        }
        localArrayList.add(new SubmissionData(str));
        if (localArrayList.size() == 50)
        {
          paramScrobbler.submit(localArrayList);
          localArrayList.clear();
        }
      }
      if (localArrayList.size() > 0) {
        paramScrobbler.submit(localArrayList);
      }
      localBufferedReader.close();
      new FileWriter(localFile).close();
    }
  }
  
  public void store(String paramString, InputStream paramInputStream, long paramLong)
  {
    createCache();
    Object localObject = new File(this.cacheDir, paramString + ".xml");
    try
    {
      paramInputStream = new BufferedInputStream(paramInputStream);
      localObject = new BufferedOutputStream(new FileOutputStream((File)localObject));
      byte[] arrayOfByte = new byte['က'];
      for (;;)
      {
        int i = paramInputStream.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        ((BufferedOutputStream)localObject).write(arrayOfByte, 0, i);
      }
      ((BufferedOutputStream)localObject).close();
      paramInputStream.close();
      paramString = new File(this.cacheDir, paramString + ".meta");
      paramInputStream = new Properties();
      paramInputStream.setProperty("expiration-date", Long.toString(paramLong));
      paramInputStream.store(new FileOutputStream(paramString), null);
      return;
    }
    catch (IOException paramString) {}
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\cache\FileSystemCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */