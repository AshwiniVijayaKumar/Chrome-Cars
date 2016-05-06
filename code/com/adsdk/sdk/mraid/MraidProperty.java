package com.adsdk.sdk.mraid;

abstract class MraidProperty
{
  private String sanitize(String paramString)
  {
    if (paramString != null) {
      return paramString.replaceAll("[^a-zA-Z0-9_,:\\s\\{\\}\\'\\\"]", "");
    }
    return "";
  }
  
  public abstract String toJsonPair();
  
  public String toString()
  {
    return sanitize(toJsonPair());
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\mraid\MraidProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */