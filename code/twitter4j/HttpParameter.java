package twitter4j;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

public final class HttpParameter
  implements Serializable, Comparable
{
  private static final String GIF = "image/gif";
  private static final String JPEG = "image/jpeg";
  private static final String OCTET = "application/octet-stream";
  private static final String PNG = "image/png";
  private static final long serialVersionUID = 4046908449190454692L;
  private File file = null;
  private InputStream fileBody = null;
  private String name = null;
  private String value = null;
  
  public HttpParameter(String paramString, double paramDouble)
  {
    this.name = paramString;
    this.value = String.valueOf(paramDouble);
  }
  
  public HttpParameter(String paramString, int paramInt)
  {
    this.name = paramString;
    this.value = String.valueOf(paramInt);
  }
  
  public HttpParameter(String paramString, long paramLong)
  {
    this.name = paramString;
    this.value = String.valueOf(paramLong);
  }
  
  public HttpParameter(String paramString, File paramFile)
  {
    this.name = paramString;
    this.file = paramFile;
  }
  
  public HttpParameter(String paramString1, String paramString2)
  {
    this.name = paramString1;
    this.value = paramString2;
  }
  
  public HttpParameter(String paramString1, String paramString2, InputStream paramInputStream)
  {
    this.name = paramString1;
    this.file = new File(paramString2);
    this.fileBody = paramInputStream;
  }
  
  public HttpParameter(String paramString, boolean paramBoolean)
  {
    this.name = paramString;
    this.value = String.valueOf(paramBoolean);
  }
  
  static boolean containsFile(List<HttpParameter> paramList)
  {
    boolean bool2 = false;
    paramList = paramList.iterator();
    do
    {
      bool1 = bool2;
      if (!paramList.hasNext()) {
        break;
      }
    } while (!((HttpParameter)paramList.next()).isFile());
    boolean bool1 = true;
    return bool1;
  }
  
  public static boolean containsFile(HttpParameter[] paramArrayOfHttpParameter)
  {
    boolean bool2 = false;
    if (paramArrayOfHttpParameter == null) {
      return false;
    }
    int j = paramArrayOfHttpParameter.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (paramArrayOfHttpParameter[i].isFile()) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static String encode(String paramString)
  {
    StringBuilder localStringBuilder = null;
    try
    {
      paramString = URLEncoder.encode(paramString, "UTF-8");
      localStringBuilder = new StringBuilder(paramString.length());
      int i = 0;
      if (i < paramString.length())
      {
        char c = paramString.charAt(i);
        if (c == '*') {
          localStringBuilder.append("%2A");
        }
        for (;;)
        {
          i += 1;
          break;
          if (c == '+')
          {
            localStringBuilder.append("%20");
          }
          else if ((c == '%') && (i + 1 < paramString.length()) && (paramString.charAt(i + 1) == '7') && (paramString.charAt(i + 2) == 'E'))
          {
            localStringBuilder.append('~');
            i += 2;
          }
          else
          {
            localStringBuilder.append(c);
          }
        }
      }
      return localStringBuilder.toString();
    }
    catch (UnsupportedEncodingException paramString)
    {
      for (;;)
      {
        paramString = localStringBuilder;
      }
    }
  }
  
  public static String encodeParameters(HttpParameter[] paramArrayOfHttpParameter)
  {
    if (paramArrayOfHttpParameter == null) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramArrayOfHttpParameter.length)
    {
      if (paramArrayOfHttpParameter[i].isFile()) {
        throw new IllegalArgumentException("parameter [" + paramArrayOfHttpParameter[i].name + "]should be text");
      }
      if (i != 0) {
        localStringBuilder.append("&");
      }
      localStringBuilder.append(encode(paramArrayOfHttpParameter[i].name)).append("=").append(encode(paramArrayOfHttpParameter[i].value));
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static HttpParameter[] getParameterArray(String paramString, int paramInt)
  {
    return getParameterArray(paramString, String.valueOf(paramInt));
  }
  
  public static HttpParameter[] getParameterArray(String paramString1, int paramInt1, String paramString2, int paramInt2)
  {
    return getParameterArray(paramString1, String.valueOf(paramInt1), paramString2, String.valueOf(paramInt2));
  }
  
  public static HttpParameter[] getParameterArray(String paramString1, String paramString2)
  {
    return new HttpParameter[] { new HttpParameter(paramString1, paramString2) };
  }
  
  public static HttpParameter[] getParameterArray(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    return new HttpParameter[] { new HttpParameter(paramString1, paramString2), new HttpParameter(paramString3, paramString4) };
  }
  
  public int compareTo(Object paramObject)
  {
    paramObject = (HttpParameter)paramObject;
    int j = this.name.compareTo(((HttpParameter)paramObject).name);
    int i = j;
    if (j == 0) {
      i = this.value.compareTo(((HttpParameter)paramObject).value);
    }
    return i;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof HttpParameter)) {
        return false;
      }
      paramObject = (HttpParameter)paramObject;
      if (this.file != null)
      {
        if (this.file.equals(((HttpParameter)paramObject).file)) {}
      }
      else {
        while (((HttpParameter)paramObject).file != null) {
          return false;
        }
      }
      if (this.fileBody != null)
      {
        if (this.fileBody.equals(((HttpParameter)paramObject).fileBody)) {}
      }
      else {
        while (((HttpParameter)paramObject).fileBody != null) {
          return false;
        }
      }
      if (!this.name.equals(((HttpParameter)paramObject).name)) {
        return false;
      }
      if (this.value == null) {
        break;
      }
    } while (this.value.equals(((HttpParameter)paramObject).value));
    for (;;)
    {
      return false;
      if (((HttpParameter)paramObject).value == null) {
        break;
      }
    }
  }
  
  public String getContentType()
  {
    if (!isFile()) {
      throw new IllegalStateException("not a file");
    }
    String str = this.file.getName();
    if (-1 == str.lastIndexOf(".")) {
      return "application/octet-stream";
    }
    str = str.substring(str.lastIndexOf(".") + 1).toLowerCase();
    if (str.length() == 3)
    {
      if ("gif".equals(str)) {
        return "image/gif";
      }
      if ("png".equals(str)) {
        return "image/png";
      }
      if ("jpg".equals(str)) {
        return "image/jpeg";
      }
      return "application/octet-stream";
    }
    if (str.length() == 4)
    {
      if ("jpeg".equals(str)) {
        return "image/jpeg";
      }
      return "application/octet-stream";
    }
    return "application/octet-stream";
  }
  
  public File getFile()
  {
    return this.file;
  }
  
  public InputStream getFileBody()
  {
    return this.fileBody;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getValue()
  {
    return this.value;
  }
  
  public boolean hasFileBody()
  {
    return this.fileBody != null;
  }
  
  public int hashCode()
  {
    int k = 0;
    int m = this.name.hashCode();
    int i;
    if (this.value != null)
    {
      i = this.value.hashCode();
      if (this.file == null) {
        break label79;
      }
    }
    label79:
    for (int j = this.file.hashCode();; j = 0)
    {
      if (this.fileBody != null) {
        k = this.fileBody.hashCode();
      }
      return ((m * 31 + i) * 31 + j) * 31 + k;
      i = 0;
      break;
    }
  }
  
  public boolean isFile()
  {
    return this.file != null;
  }
  
  public String toString()
  {
    return "PostParameter{name='" + this.name + '\'' + ", value='" + this.value + '\'' + ", file=" + this.file + ", fileBody=" + this.fileBody + '}';
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\HttpParameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */