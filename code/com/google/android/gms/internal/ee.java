package com.google.android.gms.internal;

import android.os.Parcel;
import android.view.View;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class ee
{
  private final a Bo;
  private final View zp;
  
  public ee(String paramString1, Collection<String> paramCollection, int paramInt, View paramView, String paramString2)
  {
    this.Bo = new a(paramString1, paramCollection, paramInt, paramString2);
    this.zp = paramView;
  }
  
  public String dR()
  {
    return this.Bo.dR();
  }
  
  public int dS()
  {
    return this.Bo.dS();
  }
  
  public List<String> dT()
  {
    return this.Bo.dT();
  }
  
  public String[] dU()
  {
    return (String[])this.Bo.dT().toArray(new String[0]);
  }
  
  public String dV()
  {
    return this.Bo.dV();
  }
  
  public View dW()
  {
    return this.zp;
  }
  
  public String getAccountName()
  {
    return this.Bo.getAccountName();
  }
  
  public static final class a
    implements SafeParcelable
  {
    public static final eq CREATOR = new eq();
    private final List<String> Bp = new ArrayList();
    private final String vi;
    private final int wj;
    private final int zo;
    private final String zq;
    
    a(int paramInt1, String paramString1, List<String> paramList, int paramInt2, String paramString2)
    {
      this.wj = paramInt1;
      this.vi = paramString1;
      this.Bp.addAll(paramList);
      this.zo = paramInt2;
      this.zq = paramString2;
    }
    
    public a(String paramString1, Collection<String> paramCollection, int paramInt, String paramString2)
    {
      this(3, paramString1, new ArrayList(paramCollection), paramInt, paramString2);
    }
    
    public String dR()
    {
      if (this.vi != null) {
        return this.vi;
      }
      return "<<default account>>";
    }
    
    public int dS()
    {
      return this.zo;
    }
    
    public List<String> dT()
    {
      return new ArrayList(this.Bp);
    }
    
    public String dV()
    {
      return this.zq;
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public String getAccountName()
    {
      return this.vi;
    }
    
    public int getVersionCode()
    {
      return this.wj;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      eq.a(this, paramParcel, paramInt);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ee.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */