package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.Filter;

public class FilterHolder
  implements SafeParcelable
{
  public static final Parcelable.Creator<FilterHolder> CREATOR = new c();
  final ComparisonFilter<?> ER;
  final FieldOnlyFilter ES;
  final LogicalFilter ET;
  final NotFilter EU;
  final InFilter<?> EV;
  private final Filter EW;
  final int wj;
  
  FilterHolder(int paramInt, ComparisonFilter<?> paramComparisonFilter, FieldOnlyFilter paramFieldOnlyFilter, LogicalFilter paramLogicalFilter, NotFilter paramNotFilter, InFilter<?> paramInFilter)
  {
    this.wj = paramInt;
    this.ER = paramComparisonFilter;
    this.ES = paramFieldOnlyFilter;
    this.ET = paramLogicalFilter;
    this.EU = paramNotFilter;
    this.EV = paramInFilter;
    if (this.ER != null)
    {
      this.EW = this.ER;
      return;
    }
    if (this.ES != null)
    {
      this.EW = this.ES;
      return;
    }
    if (this.ET != null)
    {
      this.EW = this.ET;
      return;
    }
    if (this.EU != null)
    {
      this.EW = this.EU;
      return;
    }
    if (this.EV != null)
    {
      this.EW = this.EV;
      return;
    }
    throw new IllegalArgumentException("At least one filter must be set.");
  }
  
  public FilterHolder(Filter paramFilter)
  {
    this.wj = 1;
    if ((paramFilter instanceof ComparisonFilter))
    {
      localObject = (ComparisonFilter)paramFilter;
      this.ER = ((ComparisonFilter)localObject);
      if (!(paramFilter instanceof FieldOnlyFilter)) {
        break label144;
      }
      localObject = (FieldOnlyFilter)paramFilter;
      label38:
      this.ES = ((FieldOnlyFilter)localObject);
      if (!(paramFilter instanceof LogicalFilter)) {
        break label149;
      }
      localObject = (LogicalFilter)paramFilter;
      label55:
      this.ET = ((LogicalFilter)localObject);
      if (!(paramFilter instanceof NotFilter)) {
        break label154;
      }
      localObject = (NotFilter)paramFilter;
      label72:
      this.EU = ((NotFilter)localObject);
      if (!(paramFilter instanceof InFilter)) {
        break label159;
      }
    }
    label144:
    label149:
    label154:
    label159:
    for (Object localObject = (InFilter)paramFilter;; localObject = null)
    {
      this.EV = ((InFilter)localObject);
      if ((this.ER != null) || (this.ES != null) || (this.ET != null) || (this.EU != null) || (this.EV != null)) {
        break label164;
      }
      throw new IllegalArgumentException("Invalid filter type or null filter.");
      localObject = null;
      break;
      localObject = null;
      break label38;
      localObject = null;
      break label55;
      localObject = null;
      break label72;
    }
    label164:
    this.EW = paramFilter;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    c.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\query\internal\FilterHolder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */