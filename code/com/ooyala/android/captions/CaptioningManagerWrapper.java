package com.ooyala.android.captions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.accessibility.CaptioningManager;
import android.view.accessibility.CaptioningManager.CaptionStyle;

class CaptioningManagerWrapper
{
  @SuppressLint({"NewApi"})
  public static void updateClosedCaptionsStyleFromCaptioningManager(ClosedCaptionsStyle paramClosedCaptionsStyle, Context paramContext)
  {
    paramContext = (CaptioningManager)paramContext.getSystemService("captioning");
    CaptioningManager.CaptionStyle localCaptionStyle = paramContext.getUserStyle();
    paramClosedCaptionsStyle.textSize = (paramContext.getFontScale() * 16.0F);
    paramClosedCaptionsStyle.textFont = localCaptionStyle.getTypeface();
    paramClosedCaptionsStyle.textColor = localCaptionStyle.foregroundColor;
    paramClosedCaptionsStyle.backgroundColor = localCaptionStyle.backgroundColor;
    paramClosedCaptionsStyle.edgeType = localCaptionStyle.edgeType;
    paramClosedCaptionsStyle.edgeColor = localCaptionStyle.edgeColor;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\captions\CaptioningManagerWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */