package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.SystemClock;

public final class ea
  extends Drawable
  implements Drawable.Callback
{
  private boolean AO = true;
  private int AS = 0;
  private long AT;
  private int AU;
  private int AV;
  private int AW = 255;
  private int AX;
  private int AY = 0;
  private boolean AZ;
  private b Ba;
  private Drawable Bb;
  private Drawable Bc;
  private boolean Bd;
  private boolean Be;
  private boolean Bf;
  private int Bg;
  
  public ea(Drawable paramDrawable1, Drawable paramDrawable2)
  {
    this(null);
    Object localObject = paramDrawable1;
    if (paramDrawable1 == null) {
      localObject = a.dP();
    }
    this.Bb = ((Drawable)localObject);
    ((Drawable)localObject).setCallback(this);
    paramDrawable1 = this.Ba;
    paramDrawable1.Bk |= ((Drawable)localObject).getChangingConfigurations();
    paramDrawable1 = paramDrawable2;
    if (paramDrawable2 == null) {
      paramDrawable1 = a.dP();
    }
    this.Bc = paramDrawable1;
    paramDrawable1.setCallback(this);
    paramDrawable2 = this.Ba;
    paramDrawable2.Bk |= paramDrawable1.getChangingConfigurations();
  }
  
  ea(b paramb)
  {
    this.Ba = new b(paramb);
  }
  
  public boolean canConstantState()
  {
    if (!this.Bd) {
      if ((this.Bb.getConstantState() == null) || (this.Bc.getConstantState() == null)) {
        break label44;
      }
    }
    label44:
    for (boolean bool = true;; bool = false)
    {
      this.Be = bool;
      this.Bd = true;
      return this.Be;
    }
  }
  
  public Drawable dO()
  {
    return this.Bc;
  }
  
  public void draw(Canvas paramCanvas)
  {
    int j = 1;
    int i = 1;
    int k = 0;
    switch (this.AS)
    {
    }
    boolean bool;
    Drawable localDrawable1;
    Drawable localDrawable2;
    do
    {
      for (;;)
      {
        j = this.AY;
        bool = this.AO;
        localDrawable1 = this.Bb;
        localDrawable2 = this.Bc;
        if (i == 0) {
          break;
        }
        if ((!bool) || (j == 0)) {
          localDrawable1.draw(paramCanvas);
        }
        if (j == this.AW)
        {
          localDrawable2.setAlpha(this.AW);
          localDrawable2.draw(paramCanvas);
        }
        return;
        this.AT = SystemClock.uptimeMillis();
        this.AS = 2;
        i = k;
      }
    } while (this.AT < 0L);
    float f1 = (float)(SystemClock.uptimeMillis() - this.AT) / this.AX;
    if (f1 >= 1.0F) {}
    for (i = j;; i = 0)
    {
      if (i != 0) {
        this.AS = 0;
      }
      f1 = Math.min(f1, 1.0F);
      float f2 = this.AU;
      this.AY = ((int)(f1 * (this.AV - this.AU) + f2));
      break;
    }
    if (bool) {
      localDrawable1.setAlpha(this.AW - j);
    }
    localDrawable1.draw(paramCanvas);
    if (bool) {
      localDrawable1.setAlpha(this.AW);
    }
    if (j > 0)
    {
      localDrawable2.setAlpha(j);
      localDrawable2.draw(paramCanvas);
      localDrawable2.setAlpha(this.AW);
    }
    invalidateSelf();
  }
  
  public int getChangingConfigurations()
  {
    return super.getChangingConfigurations() | this.Ba.Bj | this.Ba.Bk;
  }
  
  public Drawable.ConstantState getConstantState()
  {
    if (canConstantState())
    {
      this.Ba.Bj = getChangingConfigurations();
      return this.Ba;
    }
    return null;
  }
  
  public int getIntrinsicHeight()
  {
    return Math.max(this.Bb.getIntrinsicHeight(), this.Bc.getIntrinsicHeight());
  }
  
  public int getIntrinsicWidth()
  {
    return Math.max(this.Bb.getIntrinsicWidth(), this.Bc.getIntrinsicWidth());
  }
  
  public int getOpacity()
  {
    if (!this.Bf)
    {
      this.Bg = Drawable.resolveOpacity(this.Bb.getOpacity(), this.Bc.getOpacity());
      this.Bf = true;
    }
    return this.Bg;
  }
  
  public void invalidateDrawable(Drawable paramDrawable)
  {
    if (fr.eJ())
    {
      paramDrawable = getCallback();
      if (paramDrawable != null) {
        paramDrawable.invalidateDrawable(this);
      }
    }
  }
  
  public Drawable mutate()
  {
    if ((!this.AZ) && (super.mutate() == this))
    {
      if (!canConstantState()) {
        throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
      }
      this.Bb.mutate();
      this.Bc.mutate();
      this.AZ = true;
    }
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    this.Bb.setBounds(paramRect);
    this.Bc.setBounds(paramRect);
  }
  
  public void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong)
  {
    if (fr.eJ())
    {
      paramDrawable = getCallback();
      if (paramDrawable != null) {
        paramDrawable.scheduleDrawable(this, paramRunnable, paramLong);
      }
    }
  }
  
  public void setAlpha(int paramInt)
  {
    if (this.AY == this.AW) {
      this.AY = paramInt;
    }
    this.AW = paramInt;
    invalidateSelf();
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.Bb.setColorFilter(paramColorFilter);
    this.Bc.setColorFilter(paramColorFilter);
  }
  
  public void startTransition(int paramInt)
  {
    this.AU = 0;
    this.AV = this.AW;
    this.AY = 0;
    this.AX = paramInt;
    this.AS = 1;
    invalidateSelf();
  }
  
  public void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable)
  {
    if (fr.eJ())
    {
      paramDrawable = getCallback();
      if (paramDrawable != null) {
        paramDrawable.unscheduleDrawable(this, paramRunnable);
      }
    }
  }
  
  private static final class a
    extends Drawable
  {
    private static final a Bh = new a();
    private static final a Bi = new a(null);
    
    public void draw(Canvas paramCanvas) {}
    
    public Drawable.ConstantState getConstantState()
    {
      return Bi;
    }
    
    public int getOpacity()
    {
      return -2;
    }
    
    public void setAlpha(int paramInt) {}
    
    public void setColorFilter(ColorFilter paramColorFilter) {}
    
    private static final class a
      extends Drawable.ConstantState
    {
      public int getChangingConfigurations()
      {
        return 0;
      }
      
      public Drawable newDrawable()
      {
        return ea.a.dP();
      }
    }
  }
  
  static final class b
    extends Drawable.ConstantState
  {
    int Bj;
    int Bk;
    
    b(b paramb)
    {
      if (paramb != null)
      {
        this.Bj = paramb.Bj;
        this.Bk = paramb.Bk;
      }
    }
    
    public int getChangingConfigurations()
    {
      return this.Bj;
    }
    
    public Drawable newDrawable()
    {
      return new ea(this);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ea.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */