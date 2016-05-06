package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import com.google.android.gms.drive.query.internal.Operator;
import java.util.ArrayList;
import java.util.List;

public class Query
  implements SafeParcelable
{
  public static final Parcelable.Creator<Query> CREATOR = new a();
  LogicalFilter EL;
  String EM;
  final int wj;
  
  Query(int paramInt, LogicalFilter paramLogicalFilter, String paramString)
  {
    this.wj = paramInt;
    this.EL = paramLogicalFilter;
    this.EM = paramString;
  }
  
  Query(LogicalFilter paramLogicalFilter, String paramString)
  {
    this(1, paramLogicalFilter, paramString);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Filter getFilter()
  {
    return this.EL;
  }
  
  public String getPageToken()
  {
    return this.EM;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a.a(this, paramParcel, paramInt);
  }
  
  public static class Builder
  {
    private String EM;
    private final List<Filter> EN = new ArrayList();
    
    public Builder addFilter(Filter paramFilter)
    {
      this.EN.add(paramFilter);
      return this;
    }
    
    public Query build()
    {
      return new Query(new LogicalFilter(Operator.Ff, this.EN), this.EM);
    }
    
    public Builder setPageToken(String paramString)
    {
      this.EM = paramString;
      return this;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\query\Query.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */