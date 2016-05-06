package com.ooyala.android.item;

import java.util.Map;

public class ModuleData
{
  protected Map<String, String> _metadata;
  protected String _name;
  protected String _type;
  
  public ModuleData(String paramString1, String paramString2, Map<String, String> paramMap)
  {
    this._name = paramString1;
    this._type = paramString2;
    this._metadata = paramMap;
  }
  
  public Map<String, String> getMetadata()
  {
    return this._metadata;
  }
  
  public String getName()
  {
    return this._name;
  }
  
  public String getType()
  {
    return this._type;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\item\ModuleData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */