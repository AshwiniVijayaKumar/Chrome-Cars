package io.vov.vitamio.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Crypto
{
  private Cipher ecipher;
  
  public Crypto(String paramString)
  {
    try
    {
      setupCrypto(new SecretKeySpec(generateKey(paramString), "AES"));
      return;
    }
    catch (Exception paramString)
    {
      Log.e("Crypto", paramString);
    }
  }
  
  private static byte[] generateKey(String paramString)
  {
    try
    {
      paramString = paramString.getBytes("UTF-8");
      paramString = MessageDigest.getInstance("SHA256").digest(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      Log.e("generateKey", paramString);
    }
    return null;
  }
  
  public static String md5(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramString.getBytes());
      for (paramString = new BigInteger(1, localMessageDigest.digest()).toString(16);; paramString = "0" + paramString) {
        if (paramString.length() >= 32) {
          return paramString;
        }
      }
      return "";
    }
    catch (Exception paramString) {}
  }
  
  private PublicKey readKeyFromStream(InputStream paramInputStream)
    throws IOException
  {
    paramInputStream = new ObjectInputStream(new BufferedInputStream(paramInputStream));
    try
    {
      PublicKey localPublicKey = (PublicKey)paramInputStream.readObject();
      return localPublicKey;
    }
    catch (Exception localException)
    {
      Log.e("readKeyFromStream", localException);
      return null;
    }
    finally
    {
      paramInputStream.close();
    }
  }
  
  private void setupCrypto(SecretKey paramSecretKey)
  {
    Object localObject = new byte[16];
    localObject[1] = 1;
    localObject[2] = 2;
    localObject[3] = 3;
    localObject[4] = 4;
    localObject[5] = 5;
    localObject[6] = 6;
    localObject[7] = 7;
    localObject[8] = 8;
    localObject[9] = 9;
    localObject[10] = 10;
    localObject[11] = 11;
    localObject[12] = 12;
    localObject[13] = 13;
    localObject[14] = 14;
    localObject[15] = 15;
    localObject = new IvParameterSpec((byte[])localObject);
    try
    {
      this.ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      this.ecipher.init(1, paramSecretKey, (AlgorithmParameterSpec)localObject);
      return;
    }
    catch (Exception paramSecretKey)
    {
      this.ecipher = null;
      Log.e("setupCrypto", paramSecretKey);
    }
  }
  
  public String encrypt(String paramString)
  {
    if (this.ecipher == null) {
      return "";
    }
    try
    {
      paramString = Base64.encodeToString(this.ecipher.doFinal(paramString.getBytes("UTF-8")), 2);
      return paramString;
    }
    catch (Exception paramString)
    {
      Log.e("encryp", paramString);
    }
    return "";
  }
  
  public String rsaEncrypt(InputStream paramInputStream, String paramString)
  {
    try
    {
      paramInputStream = rsaEncrypt(paramInputStream, paramString.getBytes("UTF-8"));
      return paramInputStream;
    }
    catch (UnsupportedEncodingException paramInputStream) {}
    return "";
  }
  
  public String rsaEncrypt(InputStream paramInputStream, byte[] paramArrayOfByte)
  {
    try
    {
      paramInputStream = readKeyFromStream(paramInputStream);
      Cipher localCipher = Cipher.getInstance("RSA/ECB/NoPadding");
      localCipher.init(1, paramInputStream);
      paramInputStream = Base64.encodeToString(localCipher.doFinal(paramArrayOfByte), 2);
      return paramInputStream;
    }
    catch (Exception paramInputStream)
    {
      Log.e("rsaEncrypt", paramInputStream);
    }
    return "";
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\io\vov\vitamio\utils\Crypto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */