package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.h;
import java.util.Arrays;

public class fu
  extends h<DriveId>
{
  public static final fu EI = new fu();
  
  private fu()
  {
    super("driveId", Arrays.asList(new String[] { "sqlId", "resourceId" }), 4100000);
  }
  
  protected DriveId i(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    long l = paramDataHolder.getMetadata().getLong("dbInstanceId");
    String str2 = paramDataHolder.getString("resourceId", paramInt1, paramInt2);
    String str1 = str2;
    if (str2 != null)
    {
      str1 = str2;
      if (str2.startsWith("generated-android-")) {
        str1 = null;
      }
    }
    return new DriveId(str1, Long.valueOf(paramDataHolder.getLong("sqlId", paramInt1, paramInt2)).longValue(), l);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\fu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */