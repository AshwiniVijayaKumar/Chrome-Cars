package com.ooyala.android.visualon;

import android.content.Context;
import android.os.AsyncTask;
import com.discretix.drmdlc.api.DxDrmDlc;
import com.discretix.drmdlc.api.IDxDrmDlc;
import com.discretix.drmdlc.api.IDxDrmDlcDebug;
import com.discretix.drmdlc.api.exceptions.DrmClientInitFailureException;
import com.discretix.drmdlc.api.exceptions.DrmGeneralFailureException;
import com.discretix.drmdlc.api.exceptions.DrmNotSupportedException;
import com.discretix.drmdlc.api.exceptions.DrmUpdateRequiredException;
import com.ooyala.android.OoyalaPlayer;
import com.ooyala.android.util.DebugMode;

class PersonalizationAsyncTask
  extends AsyncTask<Void, Void, Exception>
{
  protected static final String SESSION_ID = "session";
  private static final String TAG = PersonalizationAsyncTask.class.getClass().toString();
  protected PersonalizationCallback _callback = null;
  protected Context _context;
  protected boolean _enableDebugDRMPlayback;
  protected String _pcode;
  protected String _personalizationServerUrl;
  
  public PersonalizationAsyncTask(PersonalizationCallback paramPersonalizationCallback, Context paramContext, String paramString1, String paramString2)
  {
    this._context = paramContext;
    this._callback = paramPersonalizationCallback;
    this._pcode = paramString1;
    this._personalizationServerUrl = paramString2;
    this._enableDebugDRMPlayback = OoyalaPlayer.enableDebugDRMPlayback;
  }
  
  protected Exception doInBackground(Void... paramVarArgs)
  {
    paramVarArgs = this._personalizationServerUrl;
    try
    {
      IDxDrmDlc localIDxDrmDlc = DxDrmDlc.getDxDrmDlc(this._context, null);
      if (this._enableDebugDRMPlayback) {
        localIDxDrmDlc.getDebugInterface().setClientSideTestPersonalization(true);
      }
      if (!localIDxDrmDlc.personalizationVerify())
      {
        DebugMode.logV(TAG, "Personalizing with Server URL: " + paramVarArgs);
        localIDxDrmDlc.performPersonalization(OoyalaPlayer.getVersion(), paramVarArgs, "session");
        return null;
      }
      DebugMode.logD(TAG, "Device is already personalized");
      return null;
    }
    catch (DrmGeneralFailureException paramVarArgs)
    {
      DebugMode.logE(TAG, "Caught!", paramVarArgs);
      return paramVarArgs;
    }
    catch (DrmUpdateRequiredException paramVarArgs)
    {
      DebugMode.logE(TAG, "Caught!", paramVarArgs);
      return paramVarArgs;
    }
    catch (DrmNotSupportedException paramVarArgs)
    {
      DebugMode.logE(TAG, "Caught!", paramVarArgs);
      return paramVarArgs;
    }
    catch (DrmClientInitFailureException paramVarArgs)
    {
      DebugMode.logE(TAG, "Caught!", paramVarArgs);
      return paramVarArgs;
    }
    catch (Exception paramVarArgs)
    {
      DebugMode.logE(TAG, "Unknown exception thrown in Personalization Async Task");
      DebugMode.logE(TAG, "Caught!", paramVarArgs);
    }
    return paramVarArgs;
  }
  
  protected void onPostExecute(Exception paramException)
  {
    this._callback.afterPersonalization(paramException);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\visualon\PersonalizationAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */