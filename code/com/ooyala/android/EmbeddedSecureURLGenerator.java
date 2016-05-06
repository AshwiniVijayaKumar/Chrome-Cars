package com.ooyala.android;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EmbeddedSecureURLGenerator
  implements SecureURLGenerator
{
  static final String KEY_API_KEY = "api_key";
  static final String KEY_DEVICE = "device";
  static final String KEY_EXPIRES = "expires";
  static final String KEY_SIGNATURE = "signature";
  static final String METHOD_GET = "GET";
  static final String METHOD_POST = "POST";
  static final String METHOD_PUT = "PUT";
  static final int RESPONSE_LIFE_SECONDS = 300;
  static final String SEPARATOR_SIGNATURE_PARAMS = "";
  private String _apiKey = null;
  private SignatureGenerator _signatureGenerator = null;
  
  public EmbeddedSecureURLGenerator(String paramString, SignatureGenerator paramSignatureGenerator)
  {
    this._apiKey = paramString;
    this._signatureGenerator = paramSignatureGenerator;
  }
  
  public EmbeddedSecureURLGenerator(String paramString1, String paramString2)
  {
    this._apiKey = paramString1;
    this._signatureGenerator = new EmbeddedSignatureGenerator(paramString2);
  }
  
  private String genStringToSign(String paramString1, Map<String, String> paramMap, String paramString2)
  {
    paramMap = Utils.getParamsString(paramMap, "", false);
    return paramString2 + paramString1 + paramMap;
  }
  
  public URL secureURL(String paramString1, String paramString2, Map<String, String> paramMap)
  {
    Object localObject;
    if (paramMap == null)
    {
      localObject = new HashMap();
      ((Map)localObject).put("api_key", this._apiKey);
      ((Map)localObject).put("expires", Long.toString(new Date().getTime() / 1000L + 300L));
      ((Map)localObject).put("signature", this._signatureGenerator.sign(genStringToSign(paramString2, (Map)localObject, "GET")));
    }
    for (;;)
    {
      return Utils.makeURL(paramString1, paramString2, (Map)localObject);
      HashMap localHashMap = new HashMap(paramMap);
      localObject = localHashMap;
      if (!paramMap.containsKey("signature"))
      {
        if (!paramMap.containsKey("api_key")) {
          localHashMap.put("api_key", this._apiKey);
        }
        if (!paramMap.containsKey("expires")) {
          localHashMap.put("expires", Long.toString(new Date().getTime() / 1000L + 300L));
        }
        localHashMap.put("signature", this._signatureGenerator.sign(genStringToSign(paramString2, localHashMap, "GET")));
        localObject = localHashMap;
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\EmbeddedSecureURLGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */