package com.ooyala.android.visualon;

import android.content.Context;
import android.os.AsyncTask;

class AcquireRightsAsyncTask
  extends AsyncTask<Void, Void, Exception>
{
  protected String TAG = getClass().toString();
  protected String _authToken;
  protected AcquireRightsCallback _callback = null;
  protected Context _context;
  protected String _customDRMData;
  protected String _localFilename;
  
  public AcquireRightsAsyncTask(AcquireRightsCallback paramAcquireRightsCallback, Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    this._context = paramContext;
    this._callback = paramAcquireRightsCallback;
    this._localFilename = paramString1;
    this._authToken = paramString2;
    this._customDRMData = paramString3;
  }
  
  /* Error */
  protected Exception doInBackground(Void... paramVarArgs)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: getfield 37	com/ooyala/android/visualon/AcquireRightsAsyncTask:_context	Landroid/content/Context;
    //   6: aconst_null
    //   7: invokestatic 66	com/discretix/drmdlc/api/DxDrmDlc:getDxDrmDlc	(Landroid/content/Context;Lcom/discretix/drmdlc/api/DxLogConfig;)Lcom/discretix/drmdlc/api/IDxDrmDlc;
    //   10: astore_3
    //   11: ldc 68
    //   13: astore_1
    //   14: aload_0
    //   15: getfield 43	com/ooyala/android/visualon/AcquireRightsAsyncTask:_customDRMData	Ljava/lang/String;
    //   18: ifnull +42 -> 60
    //   21: aload_0
    //   22: getfield 43	com/ooyala/android/visualon/AcquireRightsAsyncTask:_customDRMData	Ljava/lang/String;
    //   25: astore_1
    //   26: aload_3
    //   27: aload_0
    //   28: getfield 39	com/ooyala/android/visualon/AcquireRightsAsyncTask:_localFilename	Ljava/lang/String;
    //   31: invokeinterface 74 2 0
    //   36: ifne +150 -> 186
    //   39: aload_3
    //   40: aload_0
    //   41: getfield 39	com/ooyala/android/visualon/AcquireRightsAsyncTask:_localFilename	Ljava/lang/String;
    //   44: aload_1
    //   45: aconst_null
    //   46: invokeinterface 78 4 0
    //   51: aload_3
    //   52: aconst_null
    //   53: invokeinterface 82 2 0
    //   58: aconst_null
    //   59: areturn
    //   60: ldc 68
    //   62: aload_0
    //   63: getfield 41	com/ooyala/android/visualon/AcquireRightsAsyncTask:_authToken	Ljava/lang/String;
    //   66: invokevirtual 88	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   69: ifne -43 -> 26
    //   72: new 90	java/lang/StringBuilder
    //   75: dup
    //   76: invokespecial 91	java/lang/StringBuilder:<init>	()V
    //   79: ldc 93
    //   81: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: aload_0
    //   85: getfield 41	com/ooyala/android/visualon/AcquireRightsAsyncTask:_authToken	Ljava/lang/String;
    //   88: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   94: astore_1
    //   95: goto -69 -> 26
    //   98: astore_1
    //   99: aload_0
    //   100: getfield 33	com/ooyala/android/visualon/AcquireRightsAsyncTask:TAG	Ljava/lang/String;
    //   103: ldc 100
    //   105: aload_1
    //   106: invokestatic 106	com/ooyala/android/util/DebugMode:logE	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   109: aload_1
    //   110: areturn
    //   111: astore_1
    //   112: aload_0
    //   113: getfield 33	com/ooyala/android/visualon/AcquireRightsAsyncTask:TAG	Ljava/lang/String;
    //   116: ldc 100
    //   118: aload_1
    //   119: invokestatic 106	com/ooyala/android/util/DebugMode:logE	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   122: aload_1
    //   123: areturn
    //   124: astore_1
    //   125: aload_0
    //   126: getfield 33	com/ooyala/android/visualon/AcquireRightsAsyncTask:TAG	Ljava/lang/String;
    //   129: ldc 100
    //   131: aload_1
    //   132: invokestatic 106	com/ooyala/android/util/DebugMode:logE	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   135: aload_1
    //   136: areturn
    //   137: astore_1
    //   138: aload_0
    //   139: getfield 33	com/ooyala/android/visualon/AcquireRightsAsyncTask:TAG	Ljava/lang/String;
    //   142: ldc 100
    //   144: aload_1
    //   145: invokestatic 106	com/ooyala/android/util/DebugMode:logE	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   148: aload_1
    //   149: areturn
    //   150: astore_1
    //   151: aload_0
    //   152: getfield 33	com/ooyala/android/visualon/AcquireRightsAsyncTask:TAG	Ljava/lang/String;
    //   155: ldc 100
    //   157: aload_1
    //   158: invokestatic 106	com/ooyala/android/util/DebugMode:logE	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   161: aload_1
    //   162: areturn
    //   163: astore_1
    //   164: aload_1
    //   165: areturn
    //   166: astore_2
    //   167: aload_0
    //   168: getfield 33	com/ooyala/android/visualon/AcquireRightsAsyncTask:TAG	Ljava/lang/String;
    //   171: ldc 108
    //   173: invokestatic 111	com/ooyala/android/util/DebugMode:logE	(Ljava/lang/String;Ljava/lang/String;)V
    //   176: aload_0
    //   177: getfield 33	com/ooyala/android/visualon/AcquireRightsAsyncTask:TAG	Ljava/lang/String;
    //   180: ldc 100
    //   182: aload_2
    //   183: invokestatic 106	com/ooyala/android/util/DebugMode:logE	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   186: aload_2
    //   187: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	188	0	this	AcquireRightsAsyncTask
    //   0	188	1	paramVarArgs	Void[]
    //   1	1	2	localObject	Object
    //   166	21	2	localException	Exception
    //   10	42	3	localIDxDrmDlc	com.discretix.drmdlc.api.IDxDrmDlc
    // Exception table:
    //   from	to	target	type
    //   2	11	98	com/discretix/drmdlc/api/exceptions/DrmClientInitFailureException
    //   14	26	98	com/discretix/drmdlc/api/exceptions/DrmClientInitFailureException
    //   26	58	98	com/discretix/drmdlc/api/exceptions/DrmClientInitFailureException
    //   60	95	98	com/discretix/drmdlc/api/exceptions/DrmClientInitFailureException
    //   2	11	111	java/io/IOException
    //   14	26	111	java/io/IOException
    //   26	58	111	java/io/IOException
    //   60	95	111	java/io/IOException
    //   2	11	124	com/discretix/drmdlc/api/exceptions/DrmGeneralFailureException
    //   14	26	124	com/discretix/drmdlc/api/exceptions/DrmGeneralFailureException
    //   26	58	124	com/discretix/drmdlc/api/exceptions/DrmGeneralFailureException
    //   60	95	124	com/discretix/drmdlc/api/exceptions/DrmGeneralFailureException
    //   2	11	137	com/discretix/drmdlc/api/exceptions/DrmNotProtectedException
    //   14	26	137	com/discretix/drmdlc/api/exceptions/DrmNotProtectedException
    //   26	58	137	com/discretix/drmdlc/api/exceptions/DrmNotProtectedException
    //   60	95	137	com/discretix/drmdlc/api/exceptions/DrmNotProtectedException
    //   2	11	150	com/discretix/drmdlc/api/exceptions/DrmInvalidFormatException
    //   14	26	150	com/discretix/drmdlc/api/exceptions/DrmInvalidFormatException
    //   26	58	150	com/discretix/drmdlc/api/exceptions/DrmInvalidFormatException
    //   60	95	150	com/discretix/drmdlc/api/exceptions/DrmInvalidFormatException
    //   2	11	163	com/discretix/drmdlc/api/exceptions/DrmServerSoapErrorException
    //   14	26	163	com/discretix/drmdlc/api/exceptions/DrmServerSoapErrorException
    //   26	58	163	com/discretix/drmdlc/api/exceptions/DrmServerSoapErrorException
    //   60	95	163	com/discretix/drmdlc/api/exceptions/DrmServerSoapErrorException
    //   2	11	166	java/lang/Exception
    //   14	26	166	java/lang/Exception
    //   26	58	166	java/lang/Exception
    //   60	95	166	java/lang/Exception
  }
  
  protected void onPostExecute(Exception paramException)
  {
    this._callback.afterAcquireRights(paramException);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\visualon\AcquireRightsAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */