package com.google.android.gms.drive;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.metadata.internal.c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class MetadataBuffer
  extends DataBuffer<Metadata>
{
  private static final String[] Dg;
  private final String Dh;
  
  static
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = c.fg().iterator();
    while (localIterator.hasNext()) {
      localArrayList.addAll(((MetadataField)localIterator.next()).ff());
    }
    Dg = (String[])localArrayList.toArray(new String[0]);
  }
  
  public MetadataBuffer(DataHolder paramDataHolder, String paramString)
  {
    super(paramDataHolder);
    this.Dh = paramString;
  }
  
  public Metadata get(int paramInt)
  {
    return new a(this.zU, paramInt);
  }
  
  public String getNextPageToken()
  {
    return this.Dh;
  }
  
  private static class a
    extends Metadata
  {
    private final int Di;
    private final DataHolder zU;
    private final int zX;
    
    public a(DataHolder paramDataHolder, int paramInt)
    {
      this.zU = paramDataHolder;
      this.Di = paramInt;
      this.zX = paramDataHolder.I(paramInt);
    }
    
    protected <T> T a(MetadataField<T> paramMetadataField)
    {
      return (T)paramMetadataField.c(this.zU, this.Di, this.zX);
    }
    
    public Metadata eQ()
    {
      MetadataBundle localMetadataBundle = MetadataBundle.fh();
      Iterator localIterator = c.fg().iterator();
      while (localIterator.hasNext()) {
        ((MetadataField)localIterator.next()).a(this.zU, localMetadataBundle, this.Di, this.zX);
      }
      return new b(localMetadataBundle);
    }
    
    public boolean isDataValid()
    {
      return !this.zU.isClosed();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\MetadataBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */