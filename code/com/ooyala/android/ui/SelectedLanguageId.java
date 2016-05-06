package com.ooyala.android.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SelectedLanguageId
{
  private static final String OO_SELECTED_LANGUAGE_STRING = "oo_selected_language_string";
  private Context context;
  private String selectedLanguageString;
  
  public SelectedLanguageId(Context paramContext)
  {
    this.context = paramContext;
    this.selectedLanguageString = loadSelectedLanguageString(paramContext);
  }
  
  private static String loadSelectedLanguageString(Context paramContext)
  {
    return paramContext.getSharedPreferences("com.ooyala.android_preferences", 4).getString("oo_selected_language_string", null);
  }
  
  private static void saveSelectedLanguageString(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("com.ooyala.android_preferences", 4).edit();
    paramContext.putString("oo_selected_language_string", paramString);
    paramContext.commit();
  }
  
  public String get()
  {
    return this.selectedLanguageString;
  }
  
  public void set(String paramString)
  {
    this.selectedLanguageString = paramString;
    saveSelectedLanguageString(this.context, this.selectedLanguageString);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\ui\SelectedLanguageId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */