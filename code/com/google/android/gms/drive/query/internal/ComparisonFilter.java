package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.query.Filter;

public class ComparisonFilter<T>
  implements SafeParcelable, Filter
{
  public static final a CREATOR = new a();
  final Operator EO;
  final MetadataBundle EP;
  final MetadataField<T> EQ;
  final int wj;
  
  ComparisonFilter(int paramInt, Operator paramOperator, MetadataBundle paramMetadataBundle)
  {
    this.wj = paramInt;
    this.EO = paramOperator;
    this.EP = paramMetadataBundle;
    this.EQ = d.b(paramMetadataBundle);
  }
  
  public ComparisonFilter(Operator paramOperator, MetadataField<T> paramMetadataField, T paramT)
  {
    this(1, paramOperator, MetadataBundle.a(paramMetadataField, paramT));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\query\internal\ComparisonFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */