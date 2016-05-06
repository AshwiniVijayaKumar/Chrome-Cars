package com.ooyala.android.captions;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build.VERSION;

public class ClosedCaptionsStyle
{
  public static final int CC_FONT_SP = 16;
  public int backgroundColor;
  public int backgroundOpacity;
  public int bottomMargin;
  public int edgeColor;
  public int edgeType;
  public int textColor;
  public Typeface textFont;
  public float textOpacity;
  public float textSize;
  
  public ClosedCaptionsStyle(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      CaptioningManagerWrapper.updateClosedCaptionsStyleFromCaptioningManager(this, paramContext);
      return;
    }
    this.textSize = 16.0F;
    this.textFont = Typeface.DEFAULT;
    this.textColor = -1;
    this.backgroundColor = -16777216;
    this.edgeType = 0;
    this.edgeColor = 0;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\captions\ClosedCaptionsStyle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */