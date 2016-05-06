package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.R.string;
import java.util.LinkedHashSet;
import java.util.Locale;

public class ib
{
  private static final String TAG = ib.class.getSimpleName();
  private final hl<hg> Lk;
  private final LinkedHashSet<String> OA;
  private final Locale Or;
  private final ic Os;
  private final id Oz;
  private final Context mContext;
  
  public ib(Context paramContext, Locale paramLocale, hl<hg> paramhl)
  {
    this.mContext = paramContext;
    this.Lk = paramhl;
    this.Or = paramLocale;
    this.Oz = new id(paramContext, paramLocale);
    this.OA = new LinkedHashSet();
    this.OA.add(paramContext.getString(R.string.location_client_powered_by_google));
    paramContext = this.mContext.getPackageName();
    try
    {
      i = this.mContext.getPackageManager().getPackageInfo(paramContext, 0).versionCode;
      this.Os = new ic(this.Lk, paramContext, i);
      return;
    }
    catch (PackageManager.NameNotFoundException paramLocale)
    {
      for (;;)
      {
        int i = -1;
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ib.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */