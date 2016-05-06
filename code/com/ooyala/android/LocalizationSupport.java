package com.ooyala.android;

import java.util.HashMap;
import java.util.Map;

public final class LocalizationSupport
{
  private static Map<String, String> currentLocalizedStrings = null;
  private static Map<String, Map<String, String>> defaultLocales = null;
  
  private static void createDefaultLocales()
  {
    HashMap localHashMap1 = new HashMap();
    localHashMap1.put("LIVE", "LIVE");
    localHashMap1.put("Languages", "Languages");
    localHashMap1.put("Presentation Styles", "Presentation Styles");
    localHashMap1.put("Roll-Up", "Roll-Up");
    localHashMap1.put("Paint-On", "Paint-On");
    localHashMap1.put("Pop-On", "Pop-On");
    localHashMap1.put("Done", "Done");
    localHashMap1.put("None", "None");
    localHashMap1.put("Learn More", "Learn More");
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("LIVE", "ライブ");
    localHashMap2.put("Languages", "言語");
    localHashMap2.put("Presentation Styles", "プレゼンテーションのスタイル");
    localHashMap2.put("Roll-Up", "巻き上げる");
    localHashMap2.put("Paint-On", "上のペイント");
    localHashMap2.put("Pop-On", "上のポップアップ表示");
    localHashMap2.put("Done", "完了");
    localHashMap2.put("None", "なし");
    localHashMap2.put("Learn More", "さらに詳しく");
    HashMap localHashMap3 = new HashMap();
    localHashMap3.put("LIVE", "En vivo");
    localHashMap3.put("Languages", "Idioma");
    localHashMap3.put("Presentation Styles", "Estilos de presentación");
    localHashMap3.put("Done", "Hecho");
    localHashMap3.put("None", "Ninguno");
    localHashMap3.put("Learn More", "Más información");
    defaultLocales = new HashMap();
    defaultLocales.put("en_US", localHashMap1);
    defaultLocales.put("ja_JP", localHashMap2);
    defaultLocales.put("es", localHashMap3);
    currentLocalizedStrings = loadLocalizedStrings("en_US");
  }
  
  public static Map<String, String> loadLocalizedStrings(String paramString)
  {
    String str = paramString;
    try
    {
      if (paramString.contains("es")) {
        str = "es";
      }
      if (defaultLocales == null) {
        createDefaultLocales();
      }
      paramString = (Map)defaultLocales.get(str);
      return paramString;
    }
    finally {}
  }
  
  public static String localizedStringFor(String paramString)
  {
    try
    {
      if (currentLocalizedStrings == null) {
        createDefaultLocales();
      }
      paramString = (String)currentLocalizedStrings.get(paramString);
      return paramString;
    }
    finally {}
  }
  
  public static void useLocalizedStrings(Map<String, String> paramMap)
  {
    try
    {
      if (defaultLocales == null) {
        createDefaultLocales();
      }
      if (paramMap != null) {
        currentLocalizedStrings = paramMap;
      }
      return;
    }
    finally {}
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\LocalizationSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */