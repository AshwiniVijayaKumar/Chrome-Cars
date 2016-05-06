package org.apache.james.mime4j.util;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class MimeUtil
{
  public static final String ENC_7BIT = "7bit";
  public static final String ENC_8BIT = "8bit";
  public static final String ENC_BASE64 = "base64";
  public static final String ENC_BINARY = "binary";
  public static final String ENC_QUOTED_PRINTABLE = "quoted-printable";
  public static final String MIME_HEADER_CONTENT_DESCRIPTION = "content-description";
  public static final String MIME_HEADER_CONTENT_DISPOSITION = "content-disposition";
  public static final String MIME_HEADER_CONTENT_ID = "content-id";
  public static final String MIME_HEADER_LANGAUGE = "content-language";
  public static final String MIME_HEADER_LOCATION = "content-location";
  public static final String MIME_HEADER_MD5 = "content-md5";
  public static final String MIME_HEADER_MIME_VERSION = "mime-version";
  public static final String PARAM_CREATION_DATE = "creation-date";
  public static final String PARAM_FILENAME = "filename";
  public static final String PARAM_MODIFICATION_DATE = "modification-date";
  public static final String PARAM_READ_DATE = "read-date";
  public static final String PARAM_SIZE = "size";
  private static final ThreadLocal<DateFormat> RFC822_DATE_FORMAT = new ThreadLocal()
  {
    protected DateFormat initialValue()
    {
      return new MimeUtil.Rfc822DateFormat();
    }
  };
  private static int counter;
  private static final Log log = LogFactory.getLog(MimeUtil.class);
  private static final Random random = new Random();
  
  static
  {
    counter = 0;
  }
  
  public static String createUniqueBoundary()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("-=Part.");
    localStringBuilder.append(Integer.toHexString(nextCounterValue()));
    localStringBuilder.append('.');
    localStringBuilder.append(Long.toHexString(random.nextLong()));
    localStringBuilder.append('.');
    localStringBuilder.append(Long.toHexString(System.currentTimeMillis()));
    localStringBuilder.append('.');
    localStringBuilder.append(Long.toHexString(random.nextLong()));
    localStringBuilder.append("=-");
    return localStringBuilder.toString();
  }
  
  public static String createUniqueMessageId(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder("<Mime4j.");
    localStringBuilder.append(Integer.toHexString(nextCounterValue()));
    localStringBuilder.append('.');
    localStringBuilder.append(Long.toHexString(random.nextLong()));
    localStringBuilder.append('.');
    localStringBuilder.append(Long.toHexString(System.currentTimeMillis()));
    if (paramString != null)
    {
      localStringBuilder.append('@');
      localStringBuilder.append(paramString);
    }
    localStringBuilder.append('>');
    return localStringBuilder.toString();
  }
  
  public static String fold(String paramString, int paramInt)
  {
    int m = paramString.length();
    if (paramInt + m <= 76) {
      return paramString;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = -paramInt;
    paramInt = indexOfWsp(paramString, 0);
    for (;;)
    {
      if (paramInt == m)
      {
        localStringBuilder.append(paramString.substring(Math.max(0, i)));
        return localStringBuilder.toString();
      }
      int k = indexOfWsp(paramString, paramInt + 1);
      int j = i;
      if (k - i > 76)
      {
        localStringBuilder.append(paramString.substring(Math.max(0, i), paramInt));
        localStringBuilder.append("\r\n");
        j = paramInt;
      }
      paramInt = k;
      i = j;
    }
  }
  
  public static String formatDate(Date paramDate, TimeZone paramTimeZone)
  {
    DateFormat localDateFormat = (DateFormat)RFC822_DATE_FORMAT.get();
    if (paramTimeZone == null) {
      localDateFormat.setTimeZone(TimeZone.getDefault());
    }
    for (;;)
    {
      return localDateFormat.format(paramDate);
      localDateFormat.setTimeZone(paramTimeZone);
    }
  }
  
  public static Map<String, String> getHeaderParams(String paramString)
  {
    Object localObject = unfold(paramString.trim());
    HashMap localHashMap = new HashMap();
    StringBuilder localStringBuilder;
    int i;
    int i1;
    int n;
    label91:
    char c;
    int j;
    int m;
    if (((String)localObject).indexOf(";") == -1)
    {
      paramString = (String)localObject;
      localObject = null;
      localHashMap.put("", paramString);
      if (localObject == null) {
        break label735;
      }
      paramString = ((String)localObject).toCharArray();
      localObject = new StringBuilder(64);
      localStringBuilder = new StringBuilder(64);
      i = 0;
      i1 = 0;
      int i2 = paramString.length;
      n = 0;
      if (n >= i2) {
        break label703;
      }
      c = paramString[n];
      j = i;
      k = i;
      m = i;
      switch (i)
      {
      default: 
        k = i1;
      }
    }
    for (;;)
    {
      label184:
      n += 1;
      i1 = k;
      break label91;
      paramString = ((String)localObject).substring(0, ((String)localObject).indexOf(";"));
      localObject = ((String)localObject).substring(paramString.length() + 1);
      break;
      k = i1;
      if (c == ';')
      {
        i = 0;
        k = i1;
        continue;
        if (c == '=')
        {
          log.error("Expected header param name, got '='");
          i = 99;
          k = i1;
        }
        else
        {
          ((StringBuilder)localObject).setLength(0);
          localStringBuilder.setLength(0);
          j = 1;
          if (c == '=')
          {
            if (((StringBuilder)localObject).length() == 0)
            {
              i = 99;
              k = i1;
            }
            else
            {
              i = 2;
              k = i1;
            }
          }
          else
          {
            ((StringBuilder)localObject).append(c);
            k = i1;
            i = j;
            continue;
            k = 0;
            m = k;
            j = i;
            switch (c)
            {
            default: 
              j = 3;
              m = 1;
            case '\t': 
            case ' ': 
              k = i1;
              i = j;
              if (m != 0)
              {
                k = j;
                m = 0;
                switch (c)
                {
                default: 
                  localStringBuilder.append(c);
                  j = k;
                }
              }
              break;
            case '"': 
              for (;;)
              {
                label385:
                k = i1;
                i = j;
                if (m == 0) {
                  break;
                }
                m = j;
                k = i1;
                i = m;
                switch (c)
                {
                case '\t': 
                case ' ': 
                default: 
                  i = 99;
                  k = i1;
                  break label184;
                  j = 4;
                  m = k;
                  break label385;
                  localHashMap.put(((StringBuilder)localObject).toString().trim().toLowerCase(), localStringBuilder.toString().trim());
                  j = 5;
                  m = 1;
                }
              }
              i = 0;
              k = i1;
              continue;
              switch (c)
              {
              default: 
                if (i1 != 0) {
                  localStringBuilder.append('\\');
                }
                k = 0;
                localStringBuilder.append(c);
                break;
              case '"': 
                if (i1 == 0)
                {
                  localHashMap.put(((StringBuilder)localObject).toString().trim().toLowerCase(), localStringBuilder.toString());
                  i = 5;
                  k = i1;
                }
                else
                {
                  k = 0;
                  localStringBuilder.append(c);
                }
                break;
              }
              break;
            }
          }
        }
      }
    }
    if (i1 != 0) {
      localStringBuilder.append('\\');
    }
    if (i1 == 0) {}
    for (int k = 1;; k = 0) {
      break;
    }
    label703:
    if (i == 3) {
      localHashMap.put(((StringBuilder)localObject).toString().trim().toLowerCase(), localStringBuilder.toString().trim());
    }
    label735:
    return localHashMap;
  }
  
  private static int indexOfWsp(String paramString, int paramInt)
  {
    int i = paramString.length();
    while (paramInt < i)
    {
      int j = paramString.charAt(paramInt);
      if ((j == 32) || (j == 9)) {
        return paramInt;
      }
      paramInt += 1;
    }
    return i;
  }
  
  public static boolean isBase64Encoding(String paramString)
  {
    return "base64".equalsIgnoreCase(paramString);
  }
  
  public static boolean isMessage(String paramString)
  {
    return (paramString != null) && (paramString.equalsIgnoreCase("message/rfc822"));
  }
  
  public static boolean isMultipart(String paramString)
  {
    return (paramString != null) && (paramString.toLowerCase().startsWith("multipart/"));
  }
  
  public static boolean isQuotedPrintableEncoded(String paramString)
  {
    return "quoted-printable".equalsIgnoreCase(paramString);
  }
  
  public static boolean isSameMimeType(String paramString1, String paramString2)
  {
    return (paramString1 != null) && (paramString2 != null) && (paramString1.equalsIgnoreCase(paramString2));
  }
  
  private static int nextCounterValue()
  {
    try
    {
      int i = counter;
      counter = i + 1;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static String unfold(String paramString)
  {
    int j = paramString.length();
    int i = 0;
    for (;;)
    {
      String str = paramString;
      if (i < j)
      {
        int k = paramString.charAt(i);
        if ((k == 13) || (k == 10)) {
          str = unfold0(paramString, i);
        }
      }
      else
      {
        return str;
      }
      i += 1;
    }
  }
  
  private static String unfold0(String paramString, int paramInt)
  {
    int i = paramString.length();
    StringBuilder localStringBuilder = new StringBuilder(i);
    if (paramInt > 0) {
      localStringBuilder.append(paramString.substring(0, paramInt));
    }
    paramInt += 1;
    while (paramInt < i)
    {
      char c = paramString.charAt(paramInt);
      if ((c != '\r') && (c != '\n')) {
        localStringBuilder.append(c);
      }
      paramInt += 1;
    }
    return localStringBuilder.toString();
  }
  
  private static final class Rfc822DateFormat
    extends SimpleDateFormat
  {
    private static final long serialVersionUID = 1L;
    
    public Rfc822DateFormat()
    {
      super(Locale.US);
    }
    
    public StringBuffer format(Date paramDate, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
    {
      paramDate = super.format(paramDate, paramStringBuffer, paramFieldPosition);
      int i = (this.calendar.get(15) + this.calendar.get(16)) / 1000 / 60;
      if (i < 0)
      {
        paramDate.append('-');
        i = -i;
      }
      for (;;)
      {
        paramDate.append(String.format("%02d%02d", new Object[] { Integer.valueOf(i / 60), Integer.valueOf(i % 60) }));
        return paramDate;
        paramDate.append('+');
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\util\MimeUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */