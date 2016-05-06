package com.anjlab.android.iab.v3;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import java.lang.ref.WeakReference;

class BillingBase
{
  private WeakReference<Context> contextReference;
  
  public BillingBase(Context paramContext)
  {
    this.contextReference = new WeakReference(paramContext);
  }
  
  private SharedPreferences getPreferences()
  {
    if (this.contextReference.get() != null) {
      return PreferenceManager.getDefaultSharedPreferences((Context)this.contextReference.get());
    }
    return null;
  }
  
  public Context getContext()
  {
    return (Context)this.contextReference.get();
  }
  
  protected String getPreferencesBaseKey()
  {
    return ((Context)this.contextReference.get()).getPackageName() + "_preferences";
  }
  
  protected boolean loadBoolean(String paramString, boolean paramBoolean)
  {
    SharedPreferences localSharedPreferences = getPreferences();
    boolean bool = paramBoolean;
    if (localSharedPreferences != null) {
      bool = localSharedPreferences.getBoolean(paramString, paramBoolean);
    }
    return bool;
  }
  
  protected String loadString(String paramString1, String paramString2)
  {
    SharedPreferences localSharedPreferences = getPreferences();
    String str = paramString2;
    if (localSharedPreferences != null) {
      str = localSharedPreferences.getString(paramString1, paramString2);
    }
    return str;
  }
  
  public void release()
  {
    if (this.contextReference != null) {
      this.contextReference.clear();
    }
  }
  
  protected boolean saveBoolean(String paramString, Boolean paramBoolean)
  {
    Object localObject = getPreferences();
    if (localObject != null)
    {
      localObject = ((SharedPreferences)localObject).edit();
      ((SharedPreferences.Editor)localObject).putBoolean(paramString, paramBoolean.booleanValue());
      ((SharedPreferences.Editor)localObject).commit();
      return true;
    }
    return false;
  }
  
  protected boolean saveString(String paramString1, String paramString2)
  {
    Object localObject = getPreferences();
    if (localObject != null)
    {
      localObject = ((SharedPreferences)localObject).edit();
      ((SharedPreferences.Editor)localObject).putString(paramString1, paramString2);
      ((SharedPreferences.Editor)localObject).commit();
      return true;
    }
    return false;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\anjlab\android\iab\v3\BillingBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */