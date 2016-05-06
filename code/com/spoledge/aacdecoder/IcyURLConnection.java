package com.spoledge.aacdecoder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class IcyURLConnection
  extends HttpURLConnection
{
  protected HashMap<String, List<String>> headers;
  protected InputStream inputStream;
  protected OutputStream outputStream;
  protected HashMap<String, List<String>> requestProps;
  protected String responseLine;
  protected Socket socket;
  
  public IcyURLConnection(URL paramURL)
  {
    super(paramURL);
  }
  
  public void addRequestProperty(String paramString1, String paramString2)
  {
    try
    {
      if (this.requestProps == null) {
        this.requestProps = new HashMap();
      }
      List localList = (List)this.requestProps.get(paramString1);
      Object localObject = localList;
      if (localList == null) {
        localObject = new ArrayList();
      }
      ((List)localObject).add(paramString2);
      this.requestProps.put(paramString1, localObject);
      return;
    }
    finally {}
  }
  
  public void connect()
    throws IOException
  {
    for (;;)
    {
      try
      {
        boolean bool = this.connected;
        if (bool) {
          return;
        }
        this.socket = createSocket();
        Object localObject1 = this.socket;
        Object localObject3 = this.url.getHost();
        if (this.url.getPort() != -1)
        {
          i = this.url.getPort();
          ((Socket)localObject1).connect(new InetSocketAddress((String)localObject3, i), getConnectTimeout());
          localObject3 = getRequestProperties();
          this.connected = true;
          this.headers = new HashMap();
          this.outputStream = this.socket.getOutputStream();
          this.inputStream = this.socket.getInputStream();
          Object localObject4 = new StringBuilder().append("GET ");
          if (!"".equals(this.url.getPath())) {
            break label329;
          }
          localObject1 = "/";
          writeLine((String)localObject1 + " HTTP/1.1");
          writeLine("Host: " + this.url.getHost());
          if (localObject3 == null) {
            break label340;
          }
          localObject1 = ((Map)localObject3).entrySet().iterator();
          if (!((Iterator)localObject1).hasNext()) {
            break label340;
          }
          localObject3 = (Map.Entry)((Iterator)localObject1).next();
          localObject4 = ((List)((Map.Entry)localObject3).getValue()).iterator();
          if (((Iterator)localObject4).hasNext())
          {
            String str2 = (String)((Iterator)localObject4).next();
            writeLine((String)((Map.Entry)localObject3).getKey() + ": " + str2);
            continue;
          }
          continue;
        }
        int i = this.url.getDefaultPort();
      }
      finally {}
      continue;
      label329:
      String str1 = this.url.getPath();
      continue;
      label340:
      writeLine("");
      this.responseLine = readResponseLine();
      for (str1 = readLine(); (str1 != null) && (str1.length() != 0); str1 = readLine()) {
        parseHeaderLine(str1);
      }
    }
  }
  
  protected Socket createSocket()
  {
    return new Socket();
  }
  
  /* Error */
  public void disconnect()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 54	com/spoledge/aacdecoder/IcyURLConnection:connected	Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifne +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: getfield 60	com/spoledge/aacdecoder/IcyURLConnection:socket	Ljava/net/Socket;
    //   18: astore_2
    //   19: aload_2
    //   20: ifnull +15 -> 35
    //   23: aload_0
    //   24: getfield 60	com/spoledge/aacdecoder/IcyURLConnection:socket	Ljava/net/Socket;
    //   27: invokevirtual 193	java/net/Socket:close	()V
    //   30: aload_0
    //   31: aconst_null
    //   32: putfield 60	com/spoledge/aacdecoder/IcyURLConnection:socket	Ljava/net/Socket;
    //   35: aload_0
    //   36: aconst_null
    //   37: putfield 105	com/spoledge/aacdecoder/IcyURLConnection:inputStream	Ljava/io/InputStream;
    //   40: aload_0
    //   41: aconst_null
    //   42: putfield 99	com/spoledge/aacdecoder/IcyURLConnection:outputStream	Ljava/io/OutputStream;
    //   45: aload_0
    //   46: aconst_null
    //   47: putfield 93	com/spoledge/aacdecoder/IcyURLConnection:headers	Ljava/util/HashMap;
    //   50: aload_0
    //   51: aconst_null
    //   52: putfield 178	com/spoledge/aacdecoder/IcyURLConnection:responseLine	Ljava/lang/String;
    //   55: goto -44 -> 11
    //   58: astore_2
    //   59: aload_0
    //   60: monitorexit
    //   61: aload_2
    //   62: athrow
    //   63: astore_2
    //   64: goto -34 -> 30
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	67	0	this	IcyURLConnection
    //   6	2	1	bool	boolean
    //   18	2	2	localSocket	Socket
    //   58	4	2	localObject	Object
    //   63	1	2	localIOException	IOException
    // Exception table:
    //   from	to	target	type
    //   2	7	58	finally
    //   14	19	58	finally
    //   23	30	58	finally
    //   30	35	58	finally
    //   35	55	58	finally
    //   23	30	63	java/io/IOException
  }
  
  public String getHeaderField(int paramInt)
  {
    if (paramInt == 0) {
      return this.responseLine;
    }
    return null;
  }
  
  public String getHeaderField(String paramString)
  {
    HashMap localHashMap = this.headers;
    if (localHashMap != null)
    {
      paramString = (List)localHashMap.get(paramString);
      if ((paramString != null) && (!paramString.isEmpty())) {
        return (String)paramString.get(0);
      }
    }
    return null;
  }
  
  public Map<String, List<String>> getHeaderFields()
  {
    return this.headers;
  }
  
  public InputStream getInputStream()
  {
    return this.inputStream;
  }
  
  public OutputStream getOutputStream()
  {
    return this.outputStream;
  }
  
  public Map<String, List<String>> getRequestProperties()
  {
    return this.requestProps;
  }
  
  protected void parseHeaderLine(String paramString)
    throws IOException
  {
    int i = 2;
    int k = paramString.indexOf(": ");
    int j = k;
    if (k == -1)
    {
      i = 1;
      k = paramString.indexOf(':');
      j = k;
      if (k == -1) {
        return;
      }
    }
    String str = paramString.substring(0, j);
    paramString = paramString.substring(j + i);
    Object localObject = (List)this.headers.get(str);
    if (localObject != null)
    {
      ((List)localObject).add(paramString);
      return;
    }
    localObject = new ArrayList();
    ((List)localObject).add(paramString);
    this.headers.put(str, localObject);
  }
  
  protected String readLine()
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (;;)
    {
      int i = this.inputStream.read();
      if (i != -1)
      {
        if (i == 13) {
          continue;
        }
        if (i != 10) {}
      }
      else
      {
        return localStringBuilder.toString();
      }
      localStringBuilder.append((char)i);
    }
  }
  
  protected String readResponseLine()
    throws IOException
  {
    String str2 = readLine();
    String str1 = str2;
    if (str2 != null)
    {
      int i = str2.indexOf(' ');
      str1 = str2;
      if (i != -1) {
        str1 = "HTTP/1.0" + str2.substring(i);
      }
    }
    return str1;
  }
  
  public void setRequestProperty(String paramString1, String paramString2)
  {
    try
    {
      if (this.requestProps == null) {
        this.requestProps = new HashMap();
      }
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(paramString2);
      this.requestProps.put(paramString1, localArrayList);
      return;
    }
    finally {}
  }
  
  public boolean usingProxy()
  {
    return false;
  }
  
  protected void writeLine(String paramString)
    throws IOException
  {
    paramString = paramString + '\r';
    paramString = paramString + '\n';
    this.outputStream.write(paramString.getBytes("UTF-8"));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\spoledge\aacdecoder\IcyURLConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */