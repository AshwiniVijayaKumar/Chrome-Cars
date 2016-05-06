package com.ooyala.android.player;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.SurfaceView;
import com.ooyala.android.util.DebugMode;

public class ControlSharingSurfaceView
  extends SurfaceView
{
  private static final String TAG = ControlSharingSurfaceView.class.getSimpleName();
  
  public ControlSharingSurfaceView(boolean paramBoolean, Context paramContext)
  {
    this(paramBoolean, paramContext, null);
  }
  
  public ControlSharingSurfaceView(boolean paramBoolean, Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramBoolean, paramContext, paramAttributeSet, 0);
  }
  
  @TargetApi(17)
  public ControlSharingSurfaceView(boolean paramBoolean, Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    DebugMode.logD(TAG, "<init>: setSecure=" + paramBoolean + ", sdk=" + Build.VERSION.SDK_INT);
    if (Build.VERSION.SDK_INT >= 17) {
      setSecure(paramBoolean);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\player\ControlSharingSurfaceView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */