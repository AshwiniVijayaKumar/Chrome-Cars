package com.google.zxing.client.android.common.executor;

import android.os.AsyncTask;

public abstract interface AsyncTaskExecInterface
{
  public abstract <T> void execute(AsyncTask<T, ?, ?> paramAsyncTask, T... paramVarArgs);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\common\executor\AsyncTaskExecInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */