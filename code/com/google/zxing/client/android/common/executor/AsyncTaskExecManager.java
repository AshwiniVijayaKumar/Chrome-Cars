package com.google.zxing.client.android.common.executor;

import com.google.zxing.client.android.common.PlatformSupportManager;

public final class AsyncTaskExecManager
  extends PlatformSupportManager<AsyncTaskExecInterface>
{
  public AsyncTaskExecManager()
  {
    super(AsyncTaskExecInterface.class, new DefaultAsyncTaskExecInterface());
    addImplementationClass(11, "com.google.zxing.client.android.common.executor.HoneycombAsyncTaskExecInterface");
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\common\executor\AsyncTaskExecManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */