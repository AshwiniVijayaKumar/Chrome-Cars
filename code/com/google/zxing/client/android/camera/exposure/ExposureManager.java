package com.google.zxing.client.android.camera.exposure;

import com.google.zxing.client.android.common.PlatformSupportManager;

public final class ExposureManager
  extends PlatformSupportManager<ExposureInterface>
{
  public ExposureManager()
  {
    super(ExposureInterface.class, new DefaultExposureInterface());
    addImplementationClass(8, "com.google.zxing.client.android.camera.exposure.FroyoExposureInterface");
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\camera\exposure\ExposureManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */