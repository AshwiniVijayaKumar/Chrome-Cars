package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.query.Filter;

public class FieldOnlyFilter
  implements SafeParcelable, Filter
{
  public static final Parcelable.Creator<FieldOnlyFilter> CREATOR = new b();
  final MetadataBundle EP;
  private final MetadataField<?> EQ;
  final int wj;
  
  FieldOnlyFilter(int paramInt, MetadataBundle paramMetadataBundle)
  {
    this.wj = paramInt;
    this.EP = paramMetadataBundle;
    this.EQ = d.b(paramMetadataBundle);
  }
  
  public FieldOnlyFilter(MetadataField<?> paramMetadataField)
  {
    this(1, MetadataBundle.a(paramMetadataField, null));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    b.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\query\internal\FieldOnlyFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */