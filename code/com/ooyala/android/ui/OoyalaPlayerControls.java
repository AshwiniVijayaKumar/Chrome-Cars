package com.ooyala.android.ui;

import com.ooyala.android.OoyalaPlayer;
import com.ooyala.android.OoyalaPlayerLayout;
import java.util.Observer;

public abstract interface OoyalaPlayerControls
  extends Observer
{
  public abstract int bottomBarOffset();
  
  public abstract void hide();
  
  public abstract boolean isShowing();
  
  public abstract void setFullscreenButtonShowing(boolean paramBoolean);
  
  public abstract void setOoyalaPlayer(OoyalaPlayer paramOoyalaPlayer);
  
  public abstract void setParentLayout(OoyalaPlayerLayout paramOoyalaPlayerLayout);
  
  public abstract void setVisible(boolean paramBoolean);
  
  public abstract void show();
  
  public abstract int topBarOffset();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\ui\OoyalaPlayerControls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */