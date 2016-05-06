package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

class ao
  extends aj
{
  private static final String ID = a.al.toString();
  private static final String US = b.bt.toString();
  private static final String UU = b.cS.toString();
  private static final String UY = b.bk.toString();
  
  public ao()
  {
    super(ID, new String[] { US });
  }
  
  private byte[] c(String paramString, byte[] paramArrayOfByte)
    throws NoSuchAlgorithmException
  {
    paramString = MessageDigest.getInstance(paramString);
    paramString.update(paramArrayOfByte);
    return paramString.digest();
  }
  
  public boolean iy()
  {
    return true;
  }
  
  public d.a u(Map<String, d.a> paramMap)
  {
    Object localObject = (d.a)paramMap.get(US);
    if ((localObject == null) || (localObject == di.ku())) {
      return di.ku();
    }
    String str = di.j((d.a)localObject);
    localObject = (d.a)paramMap.get(UY);
    if (localObject == null)
    {
      localObject = "MD5";
      paramMap = (d.a)paramMap.get(UU);
      if (paramMap != null) {
        break label110;
      }
      paramMap = "text";
      label73:
      if (!"text".equals(paramMap)) {
        break label118;
      }
      paramMap = str.getBytes();
    }
    for (;;)
    {
      try
      {
        paramMap = di.r(j.d(c((String)localObject, paramMap)));
        return paramMap;
      }
      catch (NoSuchAlgorithmException paramMap)
      {
        label110:
        label118:
        bh.t("Hash: unknown algorithm: " + (String)localObject);
      }
      localObject = di.j((d.a)localObject);
      break;
      paramMap = di.j(paramMap);
      break label73;
      if ("base16".equals(paramMap))
      {
        paramMap = j.aX(str);
      }
      else
      {
        bh.t("Hash: unknown input format: " + paramMap);
        return di.ku();
      }
    }
    return di.ku();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\ao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */