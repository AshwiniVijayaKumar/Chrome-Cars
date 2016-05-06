package com.ooyala.android.visualon;

import android.content.Context;
import android.os.AsyncTask;
import com.ooyala.android.util.DebugMode;
import java.io.File;
import java.io.IOException;

class FileDownloadAsyncTask
  extends AsyncTask<Void, Void, String>
{
  protected String TAG = getClass().toString();
  protected FileDownloadCallback _callback = null;
  protected String _contentDir;
  protected String _localFilePath;
  protected String _streamUrl;
  
  public FileDownloadAsyncTask(Context paramContext, FileDownloadCallback paramFileDownloadCallback, String paramString1, String paramString2)
  {
    this._contentDir = VisualOnUtils.getLocalFileDir(paramContext);
    this._localFilePath = String.format("%s/%s", new Object[] { this._contentDir, paramString1 });
    this._callback = paramFileDownloadCallback;
    this._streamUrl = paramString2;
  }
  
  protected String doInBackground(Void... paramVarArgs)
  {
    if ((this._streamUrl == null) || (this._localFilePath == null)) {
      return null;
    }
    try
    {
      if ((!new File(this._contentDir).mkdirs()) && (!new File(this._contentDir).exists())) {
        DebugMode.logE(this.TAG, "Cannot create content directory on internal storage");
      }
      VisualOnUtils.DownloadFile(this._streamUrl, this._localFilePath);
      paramVarArgs = this._localFilePath;
      return paramVarArgs;
    }
    catch (IOException paramVarArgs)
    {
      DebugMode.logE(this.TAG, "Caught!", paramVarArgs);
    }
    return null;
  }
  
  protected void onPostExecute(String paramString)
  {
    this._callback.afterFileDownload(paramString);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\visualon\FileDownloadAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */