package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.MetadataField;

public final class j
  extends MetadataField<String>
{
  public j(String paramString, int paramInt)
  {
    super(paramString, paramInt);
  }
  
  protected void a(Bundle paramBundle, String paramString)
  {
    paramBundle.putString(getName(), paramString);
  }
  
  protected String h(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    return paramDataHolder.getString(getName(), paramInt1, paramInt2);
  }
  
  protected String l(Bundle paramBundle)
  {
    return paramBundle.getString(getName());
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\metadata\internal\j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */