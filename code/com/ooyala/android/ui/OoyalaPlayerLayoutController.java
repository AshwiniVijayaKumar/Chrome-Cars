package com.ooyala.android.ui;

import android.app.Dialog;
import android.content.Context;
import android.widget.FrameLayout.LayoutParams;
import com.ooyala.android.OoyalaPlayer;
import com.ooyala.android.OoyalaPlayerLayout;

public class OoyalaPlayerLayoutController
  extends AbstractOoyalaPlayerLayoutController
{
  public OoyalaPlayerLayoutController(OoyalaPlayerLayout paramOoyalaPlayerLayout, OoyalaPlayer paramOoyalaPlayer)
  {
    this(paramOoyalaPlayerLayout, paramOoyalaPlayer, AbstractOoyalaPlayerLayoutController.DefaultControlStyle.AUTO);
  }
  
  public OoyalaPlayerLayoutController(OoyalaPlayerLayout paramOoyalaPlayerLayout, OoyalaPlayer paramOoyalaPlayer, AbstractOoyalaPlayerLayoutController.DefaultControlStyle paramDefaultControlStyle)
  {
    super(paramOoyalaPlayerLayout, paramOoyalaPlayer, paramDefaultControlStyle);
  }
  
  protected void doFullscreenChange(boolean paramBoolean)
  {
    this._player.suspend();
    OoyalaPlayerControls localOoyalaPlayerControls3 = null;
    Object localObject2 = null;
    OoyalaPlayerControls localOoyalaPlayerControls2 = null;
    Object localObject1 = null;
    OoyalaPlayerControls localOoyalaPlayerControls1;
    if ((isFullscreen()) && (!paramBoolean))
    {
      this._fullscreenDialog.dismiss();
      this._fullscreenDialog = null;
      this._fullscreenLayout.removeAllViews();
      this._fullscreenLayout = null;
      localOoyalaPlayerControls1 = this._inlineControls;
      if (this._inlineOverlay != null)
      {
        this._inlineOverlay.setParentLayout(this._layout);
        localObject1 = this._inlineOverlay;
      }
      if (this._inlineControls != null) {
        this._player.addObserver(this._inlineControls);
      }
      if (this._fullscreenControls != null) {
        this._player.deleteObserver(this._fullscreenControls);
      }
      this._inlineControls.setVisible(true);
    }
    do
    {
      do
      {
        if (localOoyalaPlayerControls1 != null) {
          localOoyalaPlayerControls1.show();
        }
        if (localObject1 != null) {
          ((OoyalaPlayerControls)localObject1).show();
        }
        this._player.resume();
        return;
        localOoyalaPlayerControls1 = localOoyalaPlayerControls3;
        localObject1 = localObject2;
      } while (isFullscreen());
      localOoyalaPlayerControls1 = localOoyalaPlayerControls3;
      localObject1 = localObject2;
    } while (!paramBoolean);
    this._fullscreenDialog = new Dialog(this._layout.getContext(), 16974122)
    {
      public void onBackPressed()
      {
        if (OoyalaPlayerLayoutController.this._player.isFullscreen())
        {
          OoyalaPlayerLayoutController.this._player.setFullscreen(false);
          return;
        }
        super.onBackPressed();
      }
    };
    this._fullscreenLayout = new OoyalaPlayerLayout(this._fullscreenDialog.getContext());
    this._fullscreenLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1, 119));
    this._fullscreenLayout.setLayoutController(this);
    this._fullscreenDialog.setContentView(this._fullscreenLayout);
    this._fullscreenDialog.show();
    this._inlineControls.setVisible(false);
    if (this._fullscreenControls == null) {
      setFullscreenControls(createDefaultControls(this._fullscreenLayout, true));
    }
    for (;;)
    {
      localOoyalaPlayerControls3 = this._fullscreenControls;
      if (this._fullscreenOverlay != null)
      {
        this._fullscreenOverlay.setParentLayout(this._fullscreenLayout);
        localOoyalaPlayerControls2 = this._fullscreenOverlay;
      }
      if (this._inlineControls != null) {
        this._player.deleteObserver(this._inlineControls);
      }
      localOoyalaPlayerControls1 = localOoyalaPlayerControls3;
      localObject1 = localOoyalaPlayerControls2;
      if (this._fullscreenControls == null) {
        break;
      }
      this._player.addObserver(this._fullscreenControls);
      localOoyalaPlayerControls1 = localOoyalaPlayerControls3;
      localObject1 = localOoyalaPlayerControls2;
      break;
      this._fullscreenControls.setParentLayout(this._fullscreenLayout);
    }
  }
  
  public boolean isFullscreen()
  {
    return this._fullscreenLayout != null;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\ui\OoyalaPlayerLayoutController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */