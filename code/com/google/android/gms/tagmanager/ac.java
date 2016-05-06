package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class ac
  extends aj
{
  private static final String ID = a.aj.toString();
  private static final String US = b.bt.toString();
  private static final String UT = b.dt.toString();
  private static final String UU = b.cS.toString();
  private static final String UV = b.dB.toString();
  
  public ac()
  {
    super(ID, new String[] { US });
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
    String str2 = di.j((d.a)localObject);
    localObject = (d.a)paramMap.get(UU);
    String str1;
    if (localObject == null)
    {
      str1 = "text";
      localObject = (d.a)paramMap.get(UV);
      if (localObject != null) {
        break label148;
      }
      localObject = "base16";
      label75:
      paramMap = (d.a)paramMap.get(UT);
      if ((paramMap == null) || (!di.n(paramMap).booleanValue())) {
        break label322;
      }
    }
    label148:
    label257:
    label296:
    label322:
    for (int i = 3;; i = 2)
    {
      for (;;)
      {
        try
        {
          if ("text".equals(str1))
          {
            paramMap = str2.getBytes();
            if (!"base16".equals(localObject)) {
              break label257;
            }
            paramMap = j.d(paramMap);
            return di.r(paramMap);
            str1 = di.j((d.a)localObject);
            break;
            localObject = di.j((d.a)localObject);
            break label75;
          }
          if ("base16".equals(str1))
          {
            paramMap = j.aX(str2);
            continue;
          }
          if ("base64".equals(str1))
          {
            paramMap = Base64.decode(str2, i);
            continue;
          }
          if ("base64url".equals(str1))
          {
            paramMap = Base64.decode(str2, i | 0x8);
            continue;
          }
          bh.t("Encode: unknown input format: " + str1);
          paramMap = di.ku();
          return paramMap;
        }
        catch (IllegalArgumentException paramMap)
        {
          bh.t("Encode: invalid input:");
          return di.ku();
        }
        if ("base64".equals(localObject))
        {
          paramMap = Base64.encodeToString(paramMap, i);
        }
        else
        {
          if (!"base64url".equals(localObject)) {
            break label296;
          }
          paramMap = Base64.encodeToString(paramMap, i | 0x8);
        }
      }
      bh.t("Encode: unknown output format: " + (String)localObject);
      return di.ku();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\ac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */