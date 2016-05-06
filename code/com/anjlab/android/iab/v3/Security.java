package com.anjlab.android.iab.v3;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

class Security
{
  private static final String KEY_FACTORY_ALGORITHM = "RSA";
  private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
  private static final String TAG = "IABUtil/Security";
  
  public static PublicKey generatePublicKey(String paramString)
  {
    try
    {
      paramString = Base64.decode(paramString, 0);
      paramString = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(paramString));
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      throw new RuntimeException(paramString);
    }
    catch (InvalidKeySpecException paramString)
    {
      Log.e("IABUtil/Security", "Invalid key specification.");
      throw new IllegalArgumentException(paramString);
    }
    catch (IllegalArgumentException paramString)
    {
      Log.e("IABUtil/Security", "Base64 decoding failed.");
      throw paramString;
    }
  }
  
  public static boolean verify(PublicKey paramPublicKey, String paramString1, String paramString2)
  {
    try
    {
      Signature localSignature = Signature.getInstance("SHA1withRSA");
      localSignature.initVerify(paramPublicKey);
      localSignature.update(paramString1.getBytes());
      if (!localSignature.verify(Base64.decode(paramString2, 0)))
      {
        Log.e("IABUtil/Security", "Signature verification failed.");
        return false;
      }
      return true;
    }
    catch (NoSuchAlgorithmException paramPublicKey)
    {
      Log.e("IABUtil/Security", "NoSuchAlgorithmException.");
      return false;
    }
    catch (InvalidKeyException paramPublicKey)
    {
      Log.e("IABUtil/Security", "Invalid key specification.");
      return false;
    }
    catch (SignatureException paramPublicKey)
    {
      Log.e("IABUtil/Security", "Signature exception.");
      return false;
    }
    catch (IllegalArgumentException paramPublicKey)
    {
      Log.e("IABUtil/Security", "Base64 decoding failed.");
    }
    return false;
  }
  
  public static boolean verifyPurchase(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if ((TextUtils.isEmpty(paramString3)) || (TextUtils.isEmpty(paramString2)) || (TextUtils.isEmpty(paramString4)))
    {
      Log.e("IABUtil/Security", "Purchase verification failed: missing data.");
      return false;
    }
    return verify(generatePublicKey(paramString2), paramString3, paramString4);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\anjlab\android\iab\v3\Security.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */