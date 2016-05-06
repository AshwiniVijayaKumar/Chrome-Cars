package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class ax
  extends aj
{
  private static final String ID = a.am.toString();
  private static final String TD = b.bS.toString();
  private final Context kL;
  
  public ax(Context paramContext)
  {
    super(ID, new String[0]);
    this.kL = paramContext;
  }
  
  public boolean iy()
  {
    return true;
  }
  
  public d.a u(Map<String, d.a> paramMap)
  {
    if ((d.a)paramMap.get(TD) != null) {}
    for (paramMap = di.j((d.a)paramMap.get(TD));; paramMap = null)
    {
      paramMap = ay.d(this.kL, paramMap);
      if (paramMap == null) {
        break;
      }
      return di.r(paramMap);
    }
    return di.ku();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\ax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */