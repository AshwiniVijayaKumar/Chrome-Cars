package com.adsdk.sdk.mraid;

class MraidPlacementTypeProperty
  extends MraidProperty
{
  private final MraidView.PlacementType mPlacementType;
  
  MraidPlacementTypeProperty(MraidView.PlacementType paramPlacementType)
  {
    this.mPlacementType = paramPlacementType;
  }
  
  public static MraidPlacementTypeProperty createWithType(MraidView.PlacementType paramPlacementType)
  {
    return new MraidPlacementTypeProperty(paramPlacementType);
  }
  
  public String toJsonPair()
  {
    return "placementType: '" + this.mPlacementType.toString().toLowerCase() + "'";
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\mraid\MraidPlacementTypeProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */