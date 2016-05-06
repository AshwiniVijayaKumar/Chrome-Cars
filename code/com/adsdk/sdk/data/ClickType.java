package com.adsdk.sdk.data;

public enum ClickType
{
  INAPP,  BROWSER;
  
  public static ClickType getValue(String paramString)
  {
    ClickType[] arrayOfClickType = values();
    int j = arrayOfClickType.length;
    int i = 0;
    for (;;)
    {
      Object localObject;
      if (i >= j) {
        localObject = null;
      }
      ClickType localClickType;
      do
      {
        return (ClickType)localObject;
        localClickType = arrayOfClickType[i];
        localObject = localClickType;
      } while (localClickType.name().equalsIgnoreCase(paramString));
      i += 1;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\data\ClickType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */