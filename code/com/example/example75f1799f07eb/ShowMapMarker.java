package com.example.example75f1799f07eb;

public class ShowMapMarker
{
  private static int index;
  private String mIcon;
  private String mLabel;
  private Double mLatitude;
  private Double mLongitude;
  
  public ShowMapMarker(String paramString1, String paramString2, Double paramDouble1, Double paramDouble2, int paramInt)
  {
    this.mLabel = paramString1;
    this.mLatitude = paramDouble1;
    this.mLongitude = paramDouble2;
    this.mIcon = paramString2;
    index = paramInt;
  }
  
  public static int getmIndex()
  {
    return index;
  }
  
  public String getmIcon()
  {
    return this.mIcon;
  }
  
  public String getmLabel()
  {
    return this.mLabel;
  }
  
  public Double getmLatitude()
  {
    return this.mLatitude;
  }
  
  public Double getmLongitude()
  {
    return this.mLongitude;
  }
  
  public void setmIcon(String paramString)
  {
    this.mIcon = paramString;
  }
  
  public void setmIndex(int paramInt)
  {
    index = paramInt;
  }
  
  public void setmLabel(String paramString)
  {
    this.mLabel = paramString;
  }
  
  public void setmLatitude(Double paramDouble)
  {
    this.mLatitude = paramDouble;
  }
  
  public void setmLongitude(Double paramDouble)
  {
    this.mLongitude = paramDouble;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\ShowMapMarker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */