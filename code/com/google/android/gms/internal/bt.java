package com.google.android.gms.internal;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;

public final class bt
  extends FrameLayout
  implements View.OnClickListener
{
  private final ImageButton nK;
  private final Activity nd;
  
  public bt(Activity paramActivity, int paramInt)
  {
    super(paramActivity);
    this.nd = paramActivity;
    setOnClickListener(this);
    this.nK = new ImageButton(paramActivity);
    this.nK.setImageResource(17301527);
    this.nK.setBackgroundColor(0);
    this.nK.setOnClickListener(this);
    this.nK.setPadding(0, 0, 0, 0);
    paramInt = cz.a(paramActivity, paramInt);
    addView(this.nK, new FrameLayout.LayoutParams(paramInt, paramInt, 17));
  }
  
  public void g(boolean paramBoolean)
  {
    ImageButton localImageButton = this.nK;
    if (paramBoolean) {}
    for (int i = 4;; i = 0)
    {
      localImageButton.setVisibility(i);
      return;
    }
  }
  
  public void onClick(View paramView)
  {
    this.nd.finish();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\bt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */