package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Display;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import java.lang.ref.WeakReference;

public class gd
{
  protected fx HB;
  protected a HC;
  
  private gd(fx paramfx, int paramInt)
  {
    this.HB = paramfx;
    aV(paramInt);
  }
  
  public static gd a(fx paramfx, int paramInt)
  {
    if (fr.eK()) {
      return new b(paramfx, paramInt);
    }
    return new gd(paramfx, paramInt);
  }
  
  protected void aV(int paramInt)
  {
    this.HC = new a(paramInt, new Binder(), null);
  }
  
  public void f(View paramView) {}
  
  public void fN()
  {
    this.HB.a(this.HC.HD, this.HC.fQ());
  }
  
  public Bundle fO()
  {
    return this.HC.fQ();
  }
  
  public IBinder fP()
  {
    return this.HC.HD;
  }
  
  public void setGravity(int paramInt)
  {
    this.HC.gravity = paramInt;
  }
  
  public static final class a
  {
    public IBinder HD;
    public int HE = -1;
    public int bottom = 0;
    public int gravity;
    public int left = 0;
    public int right = 0;
    public int top = 0;
    
    private a(int paramInt, IBinder paramIBinder)
    {
      this.gravity = paramInt;
      this.HD = paramIBinder;
    }
    
    public Bundle fQ()
    {
      Bundle localBundle = new Bundle();
      localBundle.putInt("popupLocationInfo.gravity", this.gravity);
      localBundle.putInt("popupLocationInfo.displayId", this.HE);
      localBundle.putInt("popupLocationInfo.left", this.left);
      localBundle.putInt("popupLocationInfo.top", this.top);
      localBundle.putInt("popupLocationInfo.right", this.right);
      localBundle.putInt("popupLocationInfo.bottom", this.bottom);
      return localBundle;
    }
  }
  
  private static final class b
    extends gd
    implements View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener
  {
    private boolean GA = false;
    private WeakReference<View> HF;
    
    protected b(fx paramfx, int paramInt)
    {
      super(paramInt, null);
    }
    
    private void g(View paramView)
    {
      int j = -1;
      int i = j;
      if (fr.eO())
      {
        localObject = paramView.getDisplay();
        i = j;
        if (localObject != null) {
          i = ((Display)localObject).getDisplayId();
        }
      }
      Object localObject = paramView.getWindowToken();
      int[] arrayOfInt = new int[2];
      paramView.getLocationInWindow(arrayOfInt);
      j = paramView.getWidth();
      int k = paramView.getHeight();
      this.HC.HE = i;
      this.HC.HD = ((IBinder)localObject);
      this.HC.left = arrayOfInt[0];
      this.HC.top = arrayOfInt[1];
      this.HC.right = (arrayOfInt[0] + j);
      this.HC.bottom = (arrayOfInt[1] + k);
      if (this.GA)
      {
        fN();
        this.GA = false;
      }
    }
    
    protected void aV(int paramInt)
    {
      this.HC = new gd.a(paramInt, null, null);
    }
    
    public void f(View paramView)
    {
      this.HB.fH();
      Object localObject2;
      Object localObject1;
      if (this.HF != null)
      {
        localObject2 = (View)this.HF.get();
        Context localContext = this.HB.getContext();
        localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject1 = localObject2;
          if ((localContext instanceof Activity)) {
            localObject1 = ((Activity)localContext).getWindow().getDecorView();
          }
        }
        if (localObject1 != null)
        {
          ((View)localObject1).removeOnAttachStateChangeListener(this);
          localObject1 = ((View)localObject1).getViewTreeObserver();
          if (!fr.eN()) {
            break label186;
          }
          ((ViewTreeObserver)localObject1).removeOnGlobalLayoutListener(this);
        }
      }
      for (;;)
      {
        this.HF = null;
        localObject2 = this.HB.getContext();
        localObject1 = paramView;
        if (paramView == null)
        {
          localObject1 = paramView;
          if ((localObject2 instanceof Activity))
          {
            localObject1 = ((Activity)localObject2).findViewById(16908290);
            paramView = (View)localObject1;
            if (localObject1 == null) {
              paramView = ((Activity)localObject2).getWindow().getDecorView();
            }
            fz.g("PopupManager", "You have not specified a View to use as content view for popups. Falling back to the Activity content view which may not work properly in future versions of the API. Use setViewForPopups() to set your content view.");
            localObject1 = paramView;
          }
        }
        if (localObject1 == null) {
          break;
        }
        g((View)localObject1);
        this.HF = new WeakReference(localObject1);
        ((View)localObject1).addOnAttachStateChangeListener(this);
        ((View)localObject1).getViewTreeObserver().addOnGlobalLayoutListener(this);
        return;
        label186:
        ((ViewTreeObserver)localObject1).removeGlobalOnLayoutListener(this);
      }
      fz.h("PopupManager", "No content view usable to display popups. Popups will not be displayed in response to this client's calls. Use setViewForPopups() to set your content view.");
    }
    
    public void fN()
    {
      if (this.HC.HD != null)
      {
        super.fN();
        return;
      }
      if (this.HF != null) {}
      for (boolean bool = true;; bool = false)
      {
        this.GA = bool;
        return;
      }
    }
    
    public void onGlobalLayout()
    {
      if (this.HF == null) {}
      View localView;
      do
      {
        return;
        localView = (View)this.HF.get();
      } while (localView == null);
      g(localView);
    }
    
    public void onViewAttachedToWindow(View paramView)
    {
      g(paramView);
    }
    
    public void onViewDetachedFromWindow(View paramView)
    {
      this.HB.fH();
      paramView.removeOnAttachStateChangeListener(this);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\gd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */