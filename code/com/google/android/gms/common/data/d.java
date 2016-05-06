package com.google.android.gms.common.data;

import java.util.ArrayList;

public abstract class d<T>
  extends DataBuffer<T>
{
  private boolean Ap = false;
  private ArrayList<Integer> Aq;
  
  protected d(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  private int K(int paramInt)
  {
    if ((paramInt < 0) || (paramInt == this.Aq.size())) {
      return 0;
    }
    if (paramInt == this.Aq.size() - 1) {
      return this.zU.getCount() - ((Integer)this.Aq.get(paramInt)).intValue();
    }
    return ((Integer)this.Aq.get(paramInt + 1)).intValue() - ((Integer)this.Aq.get(paramInt)).intValue();
  }
  
  private void dK()
  {
    for (;;)
    {
      int i;
      try
      {
        if (!this.Ap)
        {
          int j = this.zU.getCount();
          this.Aq = new ArrayList();
          if (j > 0)
          {
            this.Aq.add(Integer.valueOf(0));
            String str2 = getPrimaryDataMarkerColumn();
            i = this.zU.I(0);
            Object localObject1 = this.zU.getString(str2, 0, i);
            i = 1;
            if (i < j)
            {
              int k = this.zU.I(i);
              String str1 = this.zU.getString(str2, i, k);
              if (str1.equals(localObject1)) {
                break label145;
              }
              this.Aq.add(Integer.valueOf(i));
              localObject1 = str1;
              break label145;
            }
          }
          this.Ap = true;
        }
        else
        {
          return;
        }
      }
      finally {}
      label145:
      i += 1;
    }
  }
  
  int J(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.Aq.size())) {
      throw new IllegalArgumentException("Position " + paramInt + " is out of bounds for this buffer");
    }
    return ((Integer)this.Aq.get(paramInt)).intValue();
  }
  
  protected abstract T c(int paramInt1, int paramInt2);
  
  public final T get(int paramInt)
  {
    dK();
    return (T)c(J(paramInt), K(paramInt));
  }
  
  public int getCount()
  {
    dK();
    return this.Aq.size();
  }
  
  protected abstract String getPrimaryDataMarkerColumn();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\data\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */