package com.google.android.gms.common;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.e.a;
import com.google.android.gms.internal.er;
import com.google.android.gms.internal.es;
import com.google.android.gms.internal.et;

public final class SignInButton
  extends FrameLayout
  implements View.OnClickListener
{
  public static final int COLOR_DARK = 0;
  public static final int COLOR_LIGHT = 1;
  public static final int SIZE_ICON_ONLY = 2;
  public static final int SIZE_STANDARD = 0;
  public static final int SIZE_WIDE = 1;
  private int mSize;
  private int yX;
  private View yY;
  private View.OnClickListener yZ = null;
  
  public SignInButton(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public SignInButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public SignInButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setStyle(0, 0);
  }
  
  private static Button c(Context paramContext, int paramInt1, int paramInt2)
  {
    et localet = new et(paramContext);
    localet.a(paramContext.getResources(), paramInt1, paramInt2);
    return localet;
  }
  
  private void v(Context paramContext)
  {
    if (this.yY != null) {
      removeView(this.yY);
    }
    try
    {
      this.yY = es.d(paramContext, this.mSize, this.yX);
      addView(this.yY);
      this.yY.setEnabled(isEnabled());
      this.yY.setOnClickListener(this);
      return;
    }
    catch (e.a locala)
    {
      for (;;)
      {
        Log.w("SignInButton", "Sign in button not found, using placeholder instead");
        this.yY = c(paramContext, this.mSize, this.yX);
      }
    }
  }
  
  public void onClick(View paramView)
  {
    if ((this.yZ != null) && (paramView == this.yY)) {
      this.yZ.onClick(this);
    }
  }
  
  public void setColorScheme(int paramInt)
  {
    setStyle(this.mSize, paramInt);
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
    this.yY.setEnabled(paramBoolean);
  }
  
  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    this.yZ = paramOnClickListener;
    if (this.yY != null) {
      this.yY.setOnClickListener(this);
    }
  }
  
  public void setSize(int paramInt)
  {
    setStyle(paramInt, this.yX);
  }
  
  public void setStyle(int paramInt1, int paramInt2)
  {
    boolean bool2 = true;
    if ((paramInt1 >= 0) && (paramInt1 < 3))
    {
      bool1 = true;
      er.a(bool1, "Unknown button size " + paramInt1);
      if ((paramInt2 < 0) || (paramInt2 >= 2)) {
        break label96;
      }
    }
    label96:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      er.a(bool1, "Unknown color scheme " + paramInt2);
      this.mSize = paramInt1;
      this.yX = paramInt2;
      v(getContext());
      return;
      bool1 = false;
      break;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\SignInButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */