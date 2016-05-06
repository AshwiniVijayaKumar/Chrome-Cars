package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.er;

public abstract class b
{
  protected final DataHolder zU;
  protected final int zW;
  private final int zX;
  
  public b(DataHolder paramDataHolder, int paramInt)
  {
    this.zU = ((DataHolder)er.f(paramDataHolder));
    if ((paramInt >= 0) && (paramInt < paramDataHolder.getCount())) {}
    for (boolean bool = true;; bool = false)
    {
      er.v(bool);
      this.zW = paramInt;
      this.zX = paramDataHolder.I(this.zW);
      return;
    }
  }
  
  protected void a(String paramString, CharArrayBuffer paramCharArrayBuffer)
  {
    this.zU.copyToBuffer(paramString, this.zW, this.zX, paramCharArrayBuffer);
  }
  
  protected Uri aa(String paramString)
  {
    return this.zU.parseUri(paramString, this.zW, this.zX);
  }
  
  protected boolean ab(String paramString)
  {
    return this.zU.hasNull(paramString, this.zW, this.zX);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof b))
    {
      paramObject = (b)paramObject;
      bool1 = bool2;
      if (ep.equal(Integer.valueOf(((b)paramObject).zW), Integer.valueOf(this.zW)))
      {
        bool1 = bool2;
        if (ep.equal(Integer.valueOf(((b)paramObject).zX), Integer.valueOf(this.zX)))
        {
          bool1 = bool2;
          if (((b)paramObject).zU == this.zU) {
            bool1 = true;
          }
        }
      }
    }
    return bool1;
  }
  
  protected boolean getBoolean(String paramString)
  {
    return this.zU.getBoolean(paramString, this.zW, this.zX);
  }
  
  protected byte[] getByteArray(String paramString)
  {
    return this.zU.getByteArray(paramString, this.zW, this.zX);
  }
  
  protected int getInteger(String paramString)
  {
    return this.zU.getInteger(paramString, this.zW, this.zX);
  }
  
  protected long getLong(String paramString)
  {
    return this.zU.getLong(paramString, this.zW, this.zX);
  }
  
  protected String getString(String paramString)
  {
    return this.zU.getString(paramString, this.zW, this.zX);
  }
  
  public boolean hasColumn(String paramString)
  {
    return this.zU.hasColumn(paramString);
  }
  
  public int hashCode()
  {
    return ep.hashCode(new Object[] { Integer.valueOf(this.zW), Integer.valueOf(this.zX), this.zU });
  }
  
  public boolean isDataValid()
  {
    return !this.zU.isClosed();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\data\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */