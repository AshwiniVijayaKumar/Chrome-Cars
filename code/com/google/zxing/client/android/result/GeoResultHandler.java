package com.google.zxing.client.android.result;

import android.app.Activity;
import com.google.zxing.client.android.R.string;
import com.google.zxing.client.result.GeoParsedResult;
import com.google.zxing.client.result.ParsedResult;

public final class GeoResultHandler
  extends ResultHandler
{
  private static final int[] buttons = { R.string.button_show_map, R.string.button_get_directions };
  
  public GeoResultHandler(Activity paramActivity, ParsedResult paramParsedResult)
  {
    super(paramActivity, paramParsedResult);
  }
  
  public int getButtonCount()
  {
    return buttons.length;
  }
  
  public int getButtonText(int paramInt)
  {
    return buttons[paramInt];
  }
  
  public int getDisplayTitle()
  {
    return R.string.result_geo;
  }
  
  public void handleButtonPress(int paramInt)
  {
    GeoParsedResult localGeoParsedResult = (GeoParsedResult)getResult();
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      openMap(localGeoParsedResult.getGeoURI());
      return;
    }
    getDirections(localGeoParsedResult.getLatitude(), localGeoParsedResult.getLongitude());
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\result\GeoResultHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */