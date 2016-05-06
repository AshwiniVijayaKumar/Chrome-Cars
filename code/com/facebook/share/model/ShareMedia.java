package com.facebook.share.model;

import android.os.Bundle;
import android.os.Parcel;

public abstract class ShareMedia
  implements ShareModel
{
  private final Bundle params;
  
  ShareMedia(Parcel paramParcel)
  {
    this.params = paramParcel.readBundle();
  }
  
  protected ShareMedia(Builder paramBuilder)
  {
    this.params = new Bundle(paramBuilder.params);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  @Deprecated
  public Bundle getParameters()
  {
    return new Bundle(this.params);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeBundle(this.params);
  }
  
  public static abstract class Builder<M extends ShareMedia, B extends Builder>
    implements ShareModelBuilder<M, B>
  {
    private Bundle params = new Bundle();
    
    public B readFrom(M paramM)
    {
      if (paramM == null) {
        return this;
      }
      return setParameters(paramM.getParameters());
    }
    
    @Deprecated
    public B setParameter(String paramString1, String paramString2)
    {
      this.params.putString(paramString1, paramString2);
      return this;
    }
    
    @Deprecated
    public B setParameters(Bundle paramBundle)
    {
      this.params.putAll(paramBundle);
      return this;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\facebook\share\model\ShareMedia.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */