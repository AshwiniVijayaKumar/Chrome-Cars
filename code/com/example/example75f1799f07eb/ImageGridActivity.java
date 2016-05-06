package com.example.example75f1799f07eb;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.DecodingType;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.FailReason;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoadingListener;

public class ImageGridActivity
  extends Activity
{
  TextView appName;
  private String bigImageUrls;
  protected ImageLoader imageLoader = ImageLoader.getInstance();
  private String[] imageUrls;
  private String smallImageUrls;
  
  private void startImageGalleryActivity(int paramInt)
  {
    Intent localIntent = new Intent(this, ImageGalleryActivity.class);
    localIntent.putExtra("bigImageUrls", this.bigImageUrls);
    localIntent.putExtra("com.nostra13.example.universalimageloader.IMAGE_POSITION", paramInt);
    startActivity(localIntent);
  }
  
  public void close(View paramView)
  {
    finish();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(7);
    setContentView(2130903041);
    getWindow().setFeatureInt(7, 2130903063);
    paramBundle = getResources().getString(2131230720);
    this.appName = ((TextView)findViewById(2131558500));
    this.appName.setText(paramBundle);
    this.smallImageUrls = getIntent().getStringExtra("smallImageUrls");
    this.smallImageUrls = this.smallImageUrls.substring(0, this.smallImageUrls.length() - 1);
    this.bigImageUrls = getIntent().getStringExtra("bigImageUrls");
    this.bigImageUrls = this.bigImageUrls.substring(0, this.bigImageUrls.length() - 1);
    this.imageUrls = this.smallImageUrls.split(",");
    paramBundle = (GridView)findViewById(2131558430);
    paramBundle.setAdapter(new ImageAdapter());
    paramBundle.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        ImageGridActivity.this.startImageGalleryActivity(paramAnonymousInt);
      }
    });
  }
  
  protected void onDestroy()
  {
    this.imageLoader.stop();
    super.onDestroy();
  }
  
  public class ImageAdapter
    extends BaseAdapter
  {
    public ImageAdapter() {}
    
    public int getCount()
    {
      return ImageGridActivity.this.imageUrls.length;
    }
    
    public Object getItem(int paramInt)
    {
      return null;
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(int paramInt, final View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null) {}
      for (paramView = (ImageView)ImageGridActivity.this.getLayoutInflater().inflate(2130903090, paramViewGroup, false);; paramView = (ImageView)paramView)
      {
        paramViewGroup = new DisplayImageOptions.Builder().showStubImage(2130837530).cacheInMemory().cacheOnDisc().decodingType(DecodingType.FAST).build();
        ImageGridActivity.this.imageLoader.displayImage(ImageGridActivity.this.imageUrls[paramInt], paramView, paramViewGroup, new ImageLoadingListener()
        {
          public void onLoadingComplete() {}
          
          public void onLoadingFailed(FailReason paramAnonymousFailReason)
          {
            paramView.setImageResource(17301533);
            switch (ImageGridActivity.2.$SwitchMap$com$nostra13$universalimageloader$core$FailReason[paramAnonymousFailReason.ordinal()])
            {
            default: 
              return;
            }
            ImageGridActivity.this.imageLoader.clearMemoryCache();
          }
          
          public void onLoadingStarted() {}
        });
        return paramView;
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\ImageGridActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */