package com.google.android.gms.plus.model.moments;

import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.internal.im;
import com.google.android.gms.internal.io;
import java.util.HashSet;
import java.util.Set;

public abstract interface Moment
  extends Freezable<Moment>
{
  public abstract String getId();
  
  public abstract ItemScope getResult();
  
  public abstract String getStartDate();
  
  public abstract ItemScope getTarget();
  
  public abstract String getType();
  
  public abstract boolean hasId();
  
  public abstract boolean hasResult();
  
  public abstract boolean hasStartDate();
  
  public abstract boolean hasTarget();
  
  public abstract boolean hasType();
  
  public static class Builder
  {
    private String Oc;
    private final Set<Integer> RM = new HashSet();
    private im SH;
    private im SI;
    private String Sz;
    private String uS;
    
    public Moment build()
    {
      return new io(this.RM, this.uS, this.SH, this.Sz, this.SI, this.Oc);
    }
    
    public Builder setId(String paramString)
    {
      this.uS = paramString;
      this.RM.add(Integer.valueOf(2));
      return this;
    }
    
    public Builder setResult(ItemScope paramItemScope)
    {
      this.SH = ((im)paramItemScope);
      this.RM.add(Integer.valueOf(4));
      return this;
    }
    
    public Builder setStartDate(String paramString)
    {
      this.Sz = paramString;
      this.RM.add(Integer.valueOf(5));
      return this;
    }
    
    public Builder setTarget(ItemScope paramItemScope)
    {
      this.SI = ((im)paramItemScope);
      this.RM.add(Integer.valueOf(6));
      return this;
    }
    
    public Builder setType(String paramString)
    {
      this.Oc = paramString;
      this.RM.add(Integer.valueOf(7));
      return this;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\plus\model\moments\Moment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */