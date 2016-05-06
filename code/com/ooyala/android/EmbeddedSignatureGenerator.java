package com.ooyala.android;

import android.util.Base64;
import java.io.PrintStream;
import java.security.MessageDigest;

public class EmbeddedSignatureGenerator
  implements SignatureGenerator
{
  private String _secret = null;
  
  public EmbeddedSignatureGenerator(String paramString)
  {
    this._secret = paramString;
  }
  
  public String sign(String paramString)
  {
    try
    {
      paramString = this._secret + paramString;
      String str = Base64.encodeToString(MessageDigest.getInstance("SHA-256").digest(paramString.getBytes()), 0);
      paramString = str;
      if (str.length() > 43) {
        paramString = str.substring(0, 43);
      }
      paramString = paramString.replaceAll("=", "");
      return paramString;
    }
    catch (Exception paramString)
    {
      System.out.println("Exception generating signature: " + paramString);
    }
    return null;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\EmbeddedSignatureGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */