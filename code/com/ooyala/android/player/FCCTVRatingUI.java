package com.ooyala.android.player;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.ooyala.android.FCCTVRating;
import com.ooyala.android.OoyalaPlayer;
import com.ooyala.android.configuration.FCCTVRatingConfiguration;
import com.ooyala.android.item.Video;
import com.ooyala.android.ui.FCCTVRatingView;
import com.ooyala.android.ui.FCCTVRatingView.RestoreState;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

public class FCCTVRatingUI
  implements Observer
{
  private static final int MARGIN_DIP = 5;
  public static final int TVRATING_PLAYHEAD_TIME_MINIMUM = 250;
  private ViewGroup _parentLayout;
  private OoyalaPlayer _player;
  private RelativeLayout _relativeLayout;
  private FCCTVRatingView _tvRatingView;
  private View _videoView;
  
  public FCCTVRatingUI(OoyalaPlayer paramOoyalaPlayer, View paramView, ViewGroup paramViewGroup, FCCTVRatingConfiguration paramFCCTVRatingConfiguration)
  {
    this._player = paramOoyalaPlayer;
    this._videoView = paramView;
    this._parentLayout = paramViewGroup;
    paramOoyalaPlayer = this._parentLayout.getContext();
    this._relativeLayout = new RelativeLayout(paramOoyalaPlayer);
    this._relativeLayout.setBackgroundColor(0);
    if (this._videoView.getId() == -1) {
      this._videoView.setId(getUnusedId(this._parentLayout));
    }
    this._videoView.setBackgroundColor(-16777216);
    paramView = new RelativeLayout.LayoutParams(-2, -2);
    paramView.addRule(13, -1);
    this._relativeLayout.addView(this._videoView, paramView);
    this._tvRatingView = new FCCTVRatingView(paramOoyalaPlayer);
    this._tvRatingView.setVisibility(4);
    this._tvRatingView.setBackgroundColor(0);
    paramView = new RelativeLayout.LayoutParams(-1, -1);
    paramView.addRule(6, this._videoView.getId());
    paramView.addRule(5, this._videoView.getId());
    paramView.addRule(8, this._videoView.getId());
    paramView.addRule(7, this._videoView.getId());
    int i = (int)TypedValue.applyDimension(1, 5.0F, paramOoyalaPlayer.getResources().getDisplayMetrics());
    paramView.setMargins(i, i, i, i);
    this._relativeLayout.addView(this._tvRatingView, paramView);
    this._tvRatingView.setTVRatingConfiguration(paramFCCTVRatingConfiguration);
    this._tvRatingView.setTVRating(this._player.getCurrentItem().getTVRating());
    paramOoyalaPlayer = new FrameLayout.LayoutParams(-1, -1);
    this._parentLayout.addView(this._relativeLayout, 0, paramOoyalaPlayer);
    this._player.addObserver(this);
  }
  
  private int getUnusedId(ViewGroup paramViewGroup)
  {
    HashSet localHashSet = new HashSet();
    if (paramViewGroup != null)
    {
      localHashSet.add(Integer.valueOf(paramViewGroup.getId()));
      paramViewGroup = paramViewGroup.getParent();
      if ((paramViewGroup instanceof View)) {}
      for (paramViewGroup = (View)paramViewGroup;; paramViewGroup = null) {
        break;
      }
    }
    int i = 1;
    while ((localHashSet.contains(Integer.valueOf(i))) && (i < 16777214)) {
      i += 1;
    }
    return i;
  }
  
  public void destroy()
  {
    this._player.deleteObserver(this);
    removeVideoView();
    this._player = null;
  }
  
  public FCCTVRatingView.RestoreState getRestoreState()
  {
    FCCTVRatingView.RestoreState localRestoreState = null;
    if (this._tvRatingView != null) {
      localRestoreState = this._tvRatingView.getRestoreState();
    }
    return localRestoreState;
  }
  
  public FCCTVRating getTVRating()
  {
    if (this._tvRatingView == null) {
      return null;
    }
    return this._tvRatingView.getTVRating();
  }
  
  public boolean pushTVRating(FCCTVRating paramFCCTVRating)
  {
    if (this._tvRatingView != null) {}
    for (boolean bool = true;; bool = false)
    {
      if (bool) {
        this._tvRatingView.setTVRating(paramFCCTVRating);
      }
      return bool;
    }
  }
  
  public void removeVideoView()
  {
    if (this._parentLayout != null)
    {
      if (this._tvRatingView != null)
      {
        this._relativeLayout.removeView(this._tvRatingView);
        this._tvRatingView.setVisibility(8);
        this._tvRatingView = null;
      }
      if (this._videoView != null)
      {
        this._relativeLayout.removeView(this._videoView);
        this._videoView.setVisibility(8);
        this._videoView = null;
      }
      if (this._relativeLayout != null)
      {
        this._parentLayout.removeView(this._relativeLayout);
        this._relativeLayout.setVisibility(8);
        this._relativeLayout = null;
      }
      this._parentLayout = null;
    }
  }
  
  public void reshow()
  {
    if (this._tvRatingView != null) {
      this._tvRatingView.reshow();
    }
  }
  
  public void restoreState(FCCTVRatingView.RestoreState paramRestoreState)
  {
    if (this._tvRatingView != null) {
      this._tvRatingView.restoreState(paramRestoreState);
    }
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    if ((paramObservable == this._player) && ("playStarted".equals(paramObject))) {
      reshow();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\player\FCCTVRatingUI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */