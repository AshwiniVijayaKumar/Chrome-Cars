package com.nostra13.universalimageloader.core;

import java.util.Comparator;

class MemoryCacheKeyUtil
{
  private static final String MEMORY_CACHE_KEY_FORMAT = "%s_%sx%s";
  private static final String URL_AND_SIZE_SEPARATOR = "_";
  
  static Comparator<String> createFuzzyKeyComparator()
  {
    new Comparator()
    {
      public int compare(String paramAnonymousString1, String paramAnonymousString2)
      {
        return paramAnonymousString1.substring(0, paramAnonymousString1.lastIndexOf("_")).compareTo(paramAnonymousString2.substring(0, paramAnonymousString2.lastIndexOf("_")));
      }
    };
  }
  
  static String generateKey(String paramString, ImageSize paramImageSize)
  {
    return String.format("%s_%sx%s", new Object[] { paramString, Integer.valueOf(paramImageSize.width), Integer.valueOf(paramImageSize.height) });
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\nostra13\universalimageloader\core\MemoryCacheKeyUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */