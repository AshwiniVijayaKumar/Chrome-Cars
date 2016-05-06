package com.google.android.gms.drive;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class b
  extends Metadata
{
  private final MetadataBundle CZ;
  
  public b(MetadataBundle paramMetadataBundle)
  {
    this.CZ = paramMetadataBundle;
  }
  
  protected <T> T a(MetadataField<T> paramMetadataField)
  {
    return (T)this.CZ.a(paramMetadataField);
  }
  
  public Metadata eQ()
  {
    return new b(MetadataBundle.a(this.CZ));
  }
  
  public boolean isDataValid()
  {
    return this.CZ != null;
  }
  
  public String toString()
  {
    return "Metadata [mImpl=" + this.CZ + "]";
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */