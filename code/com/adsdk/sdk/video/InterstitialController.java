package com.adsdk.sdk.video;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Formatter;
import java.util.Locale;
import java.util.Vector;

public class InterstitialController
  extends LinearLayout
{
  private static final int DEFAULT_TIMEOUT = 3000;
  private static final int FADE_OUT = 1;
  private static final int SHOW_PROGRESS = 2;
  private double buttonWidthPercent = 0.09D;
  private boolean mAutoclose;
  private AspectRatioImageViewWidth mBackButton;
  private View.OnClickListener mBackListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (InterstitialController.this.mBrowser != null) {
        InterstitialController.this.mBrowser.goBack();
      }
      InterstitialController.this.show(InterstitialController.this.mDefaultTimeout);
    }
  };
  private LinearLayout mBottomBar;
  private AspectRatioImageView mBottomBarBackground;
  private BrowserControl mBrowser;
  private FrameLayout mBrowserView;
  private Context mContext;
  private int mDefaultTimeout;
  private AspectRatioImageViewWidth mExternalButton;
  private View.OnClickListener mExternalListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (InterstitialController.this.mBrowser != null) {
        InterstitialController.this.mBrowser.launchExternalBrowser();
      }
    }
  };
  private boolean mFixed;
  StringBuilder mFormatBuilder;
  Formatter mFormatter;
  private AspectRatioImageViewWidth mForwardButton;
  private View.OnClickListener mForwardListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (InterstitialController.this.mBrowser != null) {
        InterstitialController.this.mBrowser.goForward();
      }
      InterstitialController.this.show(InterstitialController.this.mDefaultTimeout);
    }
  };
  private Handler mHandler;
  private InterstitialData mInterstitialData;
  private TextView mLeftTime;
  private LinearLayout mNavIconsLayout;
  private OnReloadListener mOnReloadListener;
  private OnResetAutocloseListener mOnResetAutocloseListener;
  private AspectRatioImageViewWidth mReloadButton;
  private View.OnClickListener mReloadListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      InterstitialController.this.reload();
    }
  };
  private ResourceManager mResourceManager;
  private boolean mShowing;
  private String mTitle;
  private TextView mTitleText;
  private LinearLayout mTopBar;
  private AspectRatioImageView mTopBarBackground;
  
  public InterstitialController(Context paramContext, InterstitialData paramInterstitialData)
  {
    super(paramContext);
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.mContext = paramContext;
    this.mInterstitialData = paramInterstitialData;
    if (this.mInterstitialData == null) {
      throw new IllegalArgumentException("Interstitial info cannot be null");
    }
    this.mFormatBuilder = new StringBuilder();
    this.mFormatter = new Formatter(this.mFormatBuilder, Locale.getDefault());
    this.mFixed = false;
    if (this.mInterstitialData.autoclose > 0) {}
    for (boolean bool = true;; bool = false)
    {
      this.mAutoclose = bool;
      this.mDefaultTimeout = 3000;
      if ((this.mInterstitialData != null) && (!this.mInterstitialData.allowTapNavigationBars)) {
        this.mDefaultTimeout = 0;
      }
      this.mHandler = new ResourceHandler(this);
      this.mResourceManager = new ResourceManager(this.mContext, this.mHandler);
      buildNavigationBarView(localDisplayMetrics);
      return;
    }
  }
  
  private void buildNavigationBarView(DisplayMetrics paramDisplayMetrics)
  {
    setWeightSum(1.0F);
    setOrientation(1);
    setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    this.mTopBar = new LinearLayout(this.mContext);
    this.mTopBar.setOrientation(0);
    Object localObject = new LinearLayout.LayoutParams(-1, -2);
    ((LinearLayout.LayoutParams)localObject).gravity = 48;
    ((LinearLayout.LayoutParams)localObject).weight = 0.0F;
    this.mTopBar.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this.mTopBar.setBackgroundColor(0);
    this.mTopBar.setGravity(17);
    int i = (int)TypedValue.applyDimension(1, 4.0F, getResources().getDisplayMetrics());
    this.mTopBar.setPadding(i, 0, i, 0);
    this.mTopBarBackground = new AspectRatioImageView(this.mContext);
    this.mTopBarBackground.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this.mTopBarBackground.fillParent(true, getWidth(), this.mTopBar.getHeight());
    addView(this.mTopBarBackground, (ViewGroup.LayoutParams)localObject);
    addView(this.mTopBar, (ViewGroup.LayoutParams)localObject);
    this.mTitleText = new TextView(this.mContext);
    localObject = new LinearLayout.LayoutParams(-2, -2, 1.0F);
    ((LinearLayout.LayoutParams)localObject).gravity = 17;
    this.mTitleText.setTextAppearance(this.mContext, 16973894);
    this.mTitleText.setTypeface(Typeface.defaultFromStyle(1));
    this.mTitleText.setGravity(17);
    this.mTopBar.addView(this.mTitleText, (ViewGroup.LayoutParams)localObject);
    this.mBrowserView = new FrameLayout(this.mContext);
    localObject = new LinearLayout.LayoutParams(-1, -2);
    ((LinearLayout.LayoutParams)localObject).gravity = 48;
    ((LinearLayout.LayoutParams)localObject).weight = 1.0F;
    this.mBrowserView.setBackgroundColor(-1);
    addView(this.mBrowserView, (ViewGroup.LayoutParams)localObject);
    this.mBottomBar = new LinearLayout(this.mContext);
    this.mBottomBar.setOrientation(0);
    localObject = new LinearLayout.LayoutParams(-1, (int)(paramDisplayMetrics.widthPixels * 0.119D));
    ((LinearLayout.LayoutParams)localObject).gravity = 80;
    ((LinearLayout.LayoutParams)localObject).weight = 0.0F;
    this.mBottomBar.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this.mBottomBar.setBackgroundColor(0);
    this.mBottomBar.setGravity(16);
    this.mBottomBar.setWeightSum(1.0F);
    this.mBottomBarBackground = new AspectRatioImageView(this.mContext);
    this.mBottomBarBackground.fillParent(true, getWidth(), this.mBottomBar.getHeight());
    addView(this.mBottomBarBackground);
    addView(this.mBottomBar, (ViewGroup.LayoutParams)localObject);
    localObject = new LinearLayout(this.mContext);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -1, 0.0F);
    localLayoutParams.gravity = 19;
    ((LinearLayout)localObject).setOrientation(0);
    this.mBottomBar.addView((View)localObject, localLayoutParams);
    i = (int)TypedValue.applyDimension(1, 4.0F, getResources().getDisplayMetrics());
    this.mReloadButton = new AspectRatioImageViewWidth(this.mContext);
    localLayoutParams = new LinearLayout.LayoutParams((int)(paramDisplayMetrics.widthPixels * this.buttonWidthPercent), (int)(paramDisplayMetrics.widthPixels * this.buttonWidthPercent));
    localLayoutParams.leftMargin = 4;
    localLayoutParams.rightMargin = 4;
    localLayoutParams.gravity = 16;
    this.mReloadButton.setAdjustViewBounds(true);
    this.mReloadButton.setPadding(i, 0, i, 0);
    ((LinearLayout)localObject).addView(this.mReloadButton, localLayoutParams);
    this.mBackButton = new AspectRatioImageViewWidth(this.mContext);
    this.mBackButton.setPadding(i, 0, i, 0);
    ((LinearLayout)localObject).addView(this.mBackButton, localLayoutParams);
    this.mForwardButton = new AspectRatioImageViewWidth(this.mContext);
    this.mForwardButton.setPadding(i, 0, i, 0);
    ((LinearLayout)localObject).addView(this.mForwardButton, localLayoutParams);
    this.mExternalButton = new AspectRatioImageViewWidth(this.mContext);
    this.mExternalButton.setPadding(i, 0, i, 0);
    ((LinearLayout)localObject).addView(this.mExternalButton, localLayoutParams);
    this.mLeftTime = new AutoResizeTextView(this.mContext);
    localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
    localLayoutParams.gravity = 17;
    this.mLeftTime.setTypeface(Typeface.defaultFromStyle(1));
    ((LinearLayout)localObject).addView(this.mLeftTime, localLayoutParams);
    this.mNavIconsLayout = new LinearLayout(this.mContext);
    localObject = new LinearLayout.LayoutParams(-2, -2, 1.0F);
    ((LinearLayout.LayoutParams)localObject).gravity = 21;
    this.mNavIconsLayout.setOrientation(0);
    this.mNavIconsLayout.setPadding(0, 0, 0, 0);
    this.mNavIconsLayout.setGravity(21);
    this.mBottomBar.addView(this.mNavIconsLayout, (ViewGroup.LayoutParams)localObject);
    initNavigationBarControllerView(i, paramDisplayMetrics);
  }
  
  private void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      do
                      {
                        do
                        {
                          do
                          {
                            return;
                            hide();
                            return;
                            switch (paramMessage.arg1)
                            {
                            default: 
                              return;
                            }
                          } while (this.mExternalButton == null);
                          paramMessage = this.mResourceManager.getResource(this.mContext, -17);
                        } while (paramMessage == null);
                        this.mExternalButton.setImageDrawable(paramMessage);
                        return;
                      } while (this.mTopBar == null);
                      paramMessage = this.mResourceManager.getResource(this.mContext, -1);
                    } while (paramMessage == null);
                    this.mTopBarBackground.setImageDrawable(paramMessage);
                    return;
                  } while (this.mBottomBar == null);
                  paramMessage = this.mResourceManager.getResource(this.mContext, -2);
                } while (paramMessage == null);
                this.mBottomBar.setBackgroundDrawable(paramMessage);
                return;
              } while (this.mBackButton == null);
              paramMessage = this.mResourceManager.getResource(this.mContext, -14);
            } while (paramMessage == null);
            this.mBackButton.setImageDrawable(paramMessage);
            return;
          } while (this.mForwardButton == null);
          paramMessage = this.mResourceManager.getResource(this.mContext, -15);
        } while (paramMessage == null);
        this.mForwardButton.setImageDrawable(paramMessage);
        return;
      } while (this.mReloadButton == null);
      paramMessage = this.mResourceManager.getResource(this.mContext, -16);
    } while (paramMessage == null);
    this.mReloadButton.setImageDrawable(paramMessage);
  }
  
  private void initNavigationBarControllerView(int paramInt, DisplayMetrics paramDisplayMetrics)
  {
    if (!this.mInterstitialData.showBottomNavigationBar)
    {
      this.mBottomBar.setVisibility(8);
      if (this.mInterstitialData.showTopNavigationBar) {
        break label829;
      }
      this.mTopBar.setVisibility(8);
    }
    for (;;)
    {
      if (!this.mInterstitialData.showNavigationBars)
      {
        if (this.mTopBar != null) {
          this.mTopBar.setVisibility(8);
        }
        if (this.mBottomBar != null) {
          this.mBottomBar.setVisibility(8);
        }
      }
      return;
      this.mBottomBar.setVisibility(0);
      if ((this.mInterstitialData.bottomNavigationBarBackground != null) && (this.mInterstitialData.bottomNavigationBarBackground.length() > 0))
      {
        this.mResourceManager.fetchResource(this.mContext, this.mInterstitialData.bottomNavigationBarBackground, -2);
        label132:
        if (this.mBackButton != null)
        {
          if ((this.mInterstitialData.backButtonImage == null) || (this.mInterstitialData.backButtonImage.length() <= 0)) {
            break label669;
          }
          this.mBackButton.setBackgroundDrawable(null);
          this.mResourceManager.fetchResource(this.mContext, this.mInterstitialData.backButtonImage, -14);
          label190:
          this.mBackButton.setOnClickListener(this.mBackListener);
          if (!this.mInterstitialData.showBackButton) {
            break label700;
          }
          this.mBackButton.setVisibility(0);
        }
        label219:
        if (this.mForwardButton != null)
        {
          if ((this.mInterstitialData.forwardButtonImage == null) || (this.mInterstitialData.forwardButtonImage.length() <= 0)) {
            break label712;
          }
          this.mForwardButton.setBackgroundDrawable(null);
          this.mResourceManager.fetchResource(this.mContext, this.mInterstitialData.forwardButtonImage, -15);
          label277:
          this.mForwardButton.setOnClickListener(this.mForwardListener);
          if (!this.mInterstitialData.showForwardButton) {
            break label735;
          }
          this.mForwardButton.setVisibility(0);
        }
        label306:
        if (this.mReloadButton != null)
        {
          if ((this.mInterstitialData.reloadButtonImage == null) || (this.mInterstitialData.reloadButtonImage.length() <= 0)) {
            break label747;
          }
          this.mReloadButton.setBackgroundDrawable(null);
          this.mResourceManager.fetchResource(this.mContext, this.mInterstitialData.reloadButtonImage, -16);
          label364:
          this.mReloadButton.setOnClickListener(this.mReloadListener);
          if (!this.mInterstitialData.showReloadButton) {
            break label770;
          }
          this.mReloadButton.setVisibility(0);
        }
        label393:
        if (this.mExternalButton != null)
        {
          if ((this.mInterstitialData.externalButtonImage == null) || (this.mInterstitialData.externalButtonImage.length() <= 0)) {
            break label782;
          }
          this.mExternalButton.setBackgroundDrawable(null);
          this.mResourceManager.fetchResource(this.mContext, this.mInterstitialData.externalButtonImage, -17);
          label451:
          this.mExternalButton.setOnClickListener(this.mExternalListener);
          if (!this.mInterstitialData.showExternalButton) {
            break label805;
          }
          this.mExternalButton.setVisibility(0);
        }
        label480:
        this.mFormatBuilder = new StringBuilder();
        this.mFormatter = new Formatter(this.mFormatBuilder, Locale.getDefault());
        if (this.mLeftTime != null)
        {
          if ((!this.mInterstitialData.showTimer) || (!this.mAutoclose)) {
            break label817;
          }
          this.mLeftTime.setVisibility(0);
        }
      }
      for (;;)
      {
        if (this.mInterstitialData.icons.isEmpty()) {
          break label827;
        }
        paramInt = 0;
        while (paramInt < this.mInterstitialData.icons.size())
        {
          Object localObject = (NavIconData)this.mInterstitialData.icons.get(paramInt);
          localObject = new NavIcon(this.mContext, (NavIconData)localObject);
          LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams((int)(paramDisplayMetrics.widthPixels * this.buttonWidthPercent), (int)(paramDisplayMetrics.widthPixels * this.buttonWidthPercent));
          this.mNavIconsLayout.addView((View)localObject, localLayoutParams);
          paramInt += 1;
        }
        break;
        this.mBottomBar.setBackgroundDrawable(this.mResourceManager.getResource(this.mContext, -2));
        break label132;
        label669:
        this.mBackButton.setImageDrawable(this.mResourceManager.getResource(this.mContext, -14));
        this.mBackButton.setEnabled(false);
        break label190;
        label700:
        this.mBackButton.setVisibility(8);
        break label219;
        label712:
        this.mForwardButton.setImageDrawable(this.mResourceManager.getResource(this.mContext, -15));
        break label277;
        label735:
        this.mForwardButton.setVisibility(8);
        break label306;
        label747:
        this.mReloadButton.setImageDrawable(this.mResourceManager.getResource(this.mContext, -16));
        break label364;
        label770:
        this.mReloadButton.setVisibility(8);
        break label393;
        label782:
        this.mExternalButton.setImageDrawable(this.mResourceManager.getResource(this.mContext, -17));
        break label451;
        label805:
        this.mExternalButton.setVisibility(8);
        break label480;
        label817:
        this.mLeftTime.setVisibility(8);
      }
      label827:
      break;
      label829:
      this.mTopBar.setVisibility(0);
      if ((this.mInterstitialData.topNavigationBarBackground != null) && (this.mInterstitialData.topNavigationBarBackground.length() > 0)) {
        this.mResourceManager.fetchResource(this.mContext, this.mInterstitialData.topNavigationBarBackground, -1);
      }
      for (;;)
      {
        if (this.mTitleText == null) {
          break label933;
        }
        if (this.mInterstitialData.topNavigationBarTitleType != 0) {
          break label935;
        }
        this.mTitleText.setText(this.mInterstitialData.topNavigationBarTitle);
        break;
        this.mTopBarBackground.setImageDrawable(this.mResourceManager.getResource(this.mContext, -1));
      }
      label933:
      continue;
      label935:
      if (this.mInterstitialData.topNavigationBarTitleType == 2) {
        this.mTitleText.setVisibility(8);
      }
    }
  }
  
  private int setProgress()
  {
    int i = 0;
    if (this.mBrowser != null) {
      i = this.mBrowser.getTime();
    }
    int j = this.mInterstitialData.autoclose * 1000;
    int k = j - i;
    if ((this.mAutoclose) && (j > 0) && (k >= 0)) {
      if (this.mLeftTime != null) {
        this.mLeftTime.setText(stringForTime(k));
      }
    }
    for (;;)
    {
      if ((this.mTitleText != null) && (this.mInterstitialData.topNavigationBarTitleType == 1) && (this.mBrowser != null) && ((this.mTitle == null) || (!this.mTitle.equals(this.mBrowser.getPageTitle()))))
      {
        this.mTitle = this.mBrowser.getPageTitle();
        this.mTitleText.setText(this.mTitle);
      }
      updateBackForward();
      return i;
      if (this.mLeftTime != null) {
        this.mLeftTime.setVisibility(8);
      }
    }
  }
  
  private String stringForTime(int paramInt)
  {
    int j = paramInt / 1000;
    paramInt = j % 60;
    int i = j / 60 % 60;
    j /= 3600;
    this.mFormatBuilder.setLength(0);
    if (j > 0) {
      return this.mFormatter.format("%d:%02d:%02d", new Object[] { Integer.valueOf(j), Integer.valueOf(i), Integer.valueOf(paramInt) }).toString();
    }
    if (i > 0) {
      return this.mFormatter.format("%02d:%02d", new Object[] { Integer.valueOf(i), Integer.valueOf(paramInt) }).toString();
    }
    return this.mFormatter.format("0:%02d", new Object[] { Integer.valueOf(paramInt) }).toString();
  }
  
  private void updateBackForward()
  {
    if (this.mBrowser == null) {}
    label81:
    do
    {
      return;
      if (this.mBrowser.canGoBack()) {
        if (this.mBackButton != null) {
          this.mBackButton.setEnabled(true);
        }
      }
      for (;;)
      {
        if (!this.mBrowser.canGoForward()) {
          break label81;
        }
        if (this.mForwardButton == null) {
          break;
        }
        this.mForwardButton.setEnabled(true);
        return;
        if (this.mBackButton != null) {
          this.mBackButton.setEnabled(false);
        }
      }
    } while (this.mForwardButton == null);
    this.mForwardButton.setEnabled(false);
  }
  
  public boolean canToggle()
  {
    return this.mInterstitialData.allowTapNavigationBars;
  }
  
  public void hide()
  {
    if ((canToggle()) && (this.mShowing))
    {
      this.mHandler.removeMessages(2);
      if (this.mTopBar != null) {
        this.mTopBar.setVisibility(8);
      }
      if (this.mBottomBar != null) {
        this.mBottomBar.setVisibility(8);
      }
      this.mShowing = false;
      this.mFixed = false;
    }
  }
  
  public boolean isShowing()
  {
    return this.mShowing;
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    onTouchEvent(paramMotionEvent);
    return false;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    super.onTouchEvent(paramMotionEvent);
    resetAutoclose();
    return true;
  }
  
  public void pageLoaded()
  {
    setProgress();
  }
  
  public void reload()
  {
    if (this.mBrowser != null) {
      this.mBrowser.reload();
    }
    setProgress();
    show(this.mDefaultTimeout);
    if (this.mOnReloadListener != null) {
      this.mOnReloadListener.onInterstitialReload();
    }
  }
  
  public void resetAutoclose()
  {
    if (this.mAutoclose)
    {
      this.mAutoclose = false;
      if (this.mOnResetAutocloseListener != null) {
        this.mOnResetAutocloseListener.onResetAutoclose();
      }
    }
  }
  
  public void resizeTopBar(int paramInt)
  {
    if (paramInt <= 0) {}
    int i;
    do
    {
      return;
      i = (int)TypedValue.applyDimension(1, 4.0F, getResources().getDisplayMetrics());
    } while (this.mTopBar == null);
    ViewGroup.LayoutParams localLayoutParams = this.mTopBar.getLayoutParams();
    localLayoutParams.height = (paramInt + i);
    this.mTopBar.setLayoutParams(localLayoutParams);
  }
  
  public void setBrowser(BrowserControl paramBrowserControl)
  {
    this.mBrowser = paramBrowserControl;
    updateBackForward();
  }
  
  public void setBrowserView(View paramView)
  {
    this.mBrowserView.addView(paramView, new FrameLayout.LayoutParams(-1, -1, 17));
  }
  
  public void setOnReloadListener(OnReloadListener paramOnReloadListener)
  {
    this.mOnReloadListener = paramOnReloadListener;
  }
  
  public void setOnResetAutocloseListener(OnResetAutocloseListener paramOnResetAutocloseListener)
  {
    this.mOnResetAutocloseListener = paramOnResetAutocloseListener;
  }
  
  public void show()
  {
    show(this.mDefaultTimeout);
  }
  
  public void show(int paramInt)
  {
    if (paramInt == 0) {
      this.mFixed = true;
    }
    if (!this.mShowing)
    {
      setProgress();
      if ((this.mTopBar != null) && (this.mInterstitialData.showTopNavigationBar)) {
        this.mTopBar.setVisibility(0);
      }
      if ((this.mBottomBar != null) && (this.mInterstitialData.showBottomNavigationBar)) {
        this.mBottomBar.setVisibility(0);
      }
      this.mShowing = true;
    }
    updateBackForward();
    this.mHandler.removeMessages(1);
    this.mHandler.sendEmptyMessage(2);
    if ((paramInt != 0) && (!this.mFixed))
    {
      Message localMessage = this.mHandler.obtainMessage(1);
      this.mHandler.sendMessageDelayed(localMessage, paramInt);
    }
  }
  
  public void toggle()
  {
    if (canToggle())
    {
      if (this.mShowing) {
        hide();
      }
    }
    else {
      return;
    }
    show();
  }
  
  public static abstract interface BrowserControl
  {
    public abstract boolean canGoBack();
    
    public abstract boolean canGoForward();
    
    public abstract String getPageTitle();
    
    public abstract int getTime();
    
    public abstract void goBack();
    
    public abstract void goForward();
    
    public abstract void launchExternalBrowser();
    
    public abstract void reload();
  }
  
  public static abstract interface OnReloadListener
  {
    public abstract void onInterstitialReload();
  }
  
  public static abstract interface OnResetAutocloseListener
  {
    public abstract void onResetAutoclose();
  }
  
  private static class ResourceHandler
    extends Handler
  {
    WeakReference<InterstitialController> interstitialController;
    
    public ResourceHandler(InterstitialController paramInterstitialController)
    {
      this.interstitialController = new WeakReference(paramInterstitialController);
    }
    
    public void handleMessage(Message paramMessage)
    {
      InterstitialController localInterstitialController = (InterstitialController)this.interstitialController.get();
      if (localInterstitialController != null) {
        switch (paramMessage.what)
        {
        default: 
          localInterstitialController.handleMessage(paramMessage);
        }
      }
      do
      {
        return;
        localInterstitialController.setProgress();
      } while ((!localInterstitialController.mShowing) || (!localInterstitialController.mInterstitialData.showTimer));
      sendMessageDelayed(obtainMessage(2), 1000L);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\video\InterstitialController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */