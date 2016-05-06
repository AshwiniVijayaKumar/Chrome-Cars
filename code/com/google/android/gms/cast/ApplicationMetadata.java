package com.google.android.gms.cast;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

public final class ApplicationMetadata
  implements SafeParcelable
{
  public static final Parcelable.Creator<ApplicationMetadata> CREATOR = new a();
  String mName;
  private final int wj;
  String wk;
  List<WebImage> wl;
  List<String> wm;
  String wn;
  Uri wo;
  
  private ApplicationMetadata()
  {
    this.wj = 1;
    this.wl = new ArrayList();
    this.wm = new ArrayList();
  }
  
  ApplicationMetadata(int paramInt, String paramString1, String paramString2, List<WebImage> paramList, List<String> paramList1, String paramString3, Uri paramUri)
  {
    this.wj = paramInt;
    this.wk = paramString1;
    this.mName = paramString2;
    this.wl = paramList;
    this.wm = paramList1;
    this.wn = paramString3;
    this.wo = paramUri;
  }
  
  public boolean areNamespacesSupported(List<String> paramList)
  {
    return (this.wm != null) && (this.wm.containsAll(paramList));
  }
  
  public Uri cR()
  {
    return this.wo;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getApplicationId()
  {
    return this.wk;
  }
  
  public List<WebImage> getImages()
  {
    return this.wl;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public String getSenderAppIdentifier()
  {
    return this.wn;
  }
  
  int getVersionCode()
  {
    return this.wj;
  }
  
  public boolean isNamespaceSupported(String paramString)
  {
    return (this.wm != null) && (this.wm.contains(paramString));
  }
  
  public String toString()
  {
    return this.mName;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\cast\ApplicationMetadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */