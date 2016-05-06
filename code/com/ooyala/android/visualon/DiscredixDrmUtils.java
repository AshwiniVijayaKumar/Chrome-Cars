package com.ooyala.android.visualon;

import android.content.Context;
import com.discretix.drmdlc.api.DxDrmDlc;
import com.discretix.drmdlc.api.DxLogConfig;
import com.discretix.drmdlc.api.DxLogConfig.LogLevel;
import com.discretix.drmdlc.api.IDxDrmDlc;
import com.discretix.drmdlc.api.exceptions.DrmAndroidPermissionMissingException;
import com.discretix.drmdlc.api.exceptions.DrmClientInitFailureException;
import com.discretix.drmdlc.api.exceptions.DrmGeneralFailureException;
import com.discretix.drmdlc.api.exceptions.DrmInvalidFormatException;
import com.discretix.drmdlc.api.exceptions.DrmServerSoapErrorException;
import com.discretix.vodx.VODXPlayerImpl;
import com.ooyala.android.OoyalaException;
import com.ooyala.android.OoyalaException.OoyalaErrorCode;
import com.ooyala.android.item.Stream;
import com.ooyala.android.util.DebugMode;
import com.visualon.OSMPPlayer.VOCommonPlayer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

class DiscredixDrmUtils
{
  private static final String SECURE_PLAYER_VERSION = "03_05_02_0001";
  private static final String TAG = DiscredixDrmUtils.class.getName();
  
  public static boolean canFileBePlayed(Context paramContext, Stream paramStream, String paramString)
  {
    if (paramString == null) {
      return false;
    }
    if (!isStreamProtected(paramContext, paramString)) {
      return true;
    }
    try
    {
      boolean bool = DxDrmDlc.getDxDrmDlc(paramContext, null).verifyRights(paramString);
      return bool;
    }
    catch (DrmClientInitFailureException paramContext)
    {
      DebugMode.logE(TAG, "Caught!", paramContext);
      return false;
    }
    catch (IOException paramContext)
    {
      DebugMode.logE(TAG, "Caught!", paramContext);
      return false;
    }
    catch (DrmGeneralFailureException paramContext)
    {
      DebugMode.logE(TAG, "Caught!", paramContext);
      return false;
    }
    catch (DrmInvalidFormatException paramContext)
    {
      DebugMode.logE(TAG, "Caught!", paramContext);
      return false;
    }
    catch (IllegalArgumentException paramContext)
    {
      DebugMode.logE(TAG, "Caught!", paramContext);
      return false;
    }
    catch (DrmAndroidPermissionMissingException paramContext)
    {
      DebugMode.logE(TAG, "Caught!", paramContext);
    }
    return false;
  }
  
  public static void enableDebugging(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (;;)
    {
      try
      {
        localDxLogConfig = new DxLogConfig(DxLogConfig.LogLevel.Verbose, 0, new File(paramContext.getExternalCacheDir(), "vo_dx.log").toString(), true);
        DxDrmDlc.getDxDrmDlc(paramContext, localDxLogConfig);
        return;
      }
      catch (DrmClientInitFailureException paramContext)
      {
        DxLogConfig localDxLogConfig;
        paramContext.printStackTrace();
        return;
      }
      catch (DrmAndroidPermissionMissingException paramContext)
      {
        DebugMode.logE(TAG, "Caught!", paramContext);
      }
      localDxLogConfig = new DxLogConfig(DxLogConfig.LogLevel.Verbose, 0);
    }
  }
  
  public static VOCommonPlayer getVODXPlayerImpl()
  {
    return new VODXPlayerImpl();
  }
  
  public static OoyalaException handleDRMError(Exception paramException)
  {
    if (paramException.getClass() != DrmServerSoapErrorException.class)
    {
      DebugMode.logE(TAG, "Error with VisualOn Acquire Rights code");
      paramException = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_DRM_GENERAL_FAILURE, paramException);
    }
    for (;;)
    {
      return paramException;
      String str = ((DrmServerSoapErrorException)paramException).getCustomData();
      paramException = ((DrmServerSoapErrorException)paramException).getSoapMessage();
      if (str == null)
      {
        DebugMode.logE(TAG, "Unknown SOAP error from DRM server: " + paramException);
        return new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_DRM_RIGHTS_SERVER_ERROR, paramException);
      }
      paramException = str.replaceAll("<[^>]+>", "");
      if ("invalid token".equals(paramException))
      {
        DebugMode.logE(TAG, "VisualOn Rights error: Invalid token");
        paramException = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_DEVICE_INVALID_AUTH_TOKEN);
      }
      else if ("device limit reached".equals(paramException))
      {
        DebugMode.logE(TAG, "VisualOn Rights error: Device limit reached");
        paramException = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_DEVICE_LIMIT_REACHED);
      }
      else if ("device binding failed".equals(paramException))
      {
        DebugMode.logE(TAG, "VisualOn Rights error: Device binding failed");
        paramException = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_DEVICE_BINDING_FAILED);
      }
      else if ("device id too long".equals(paramException))
      {
        DebugMode.logE(TAG, "VisualOn Rights error: Device ID too long");
        paramException = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_DEVICE_ID_TOO_LONG);
      }
      else
      {
        DebugMode.logE(TAG, "General SOAP error from DRM server: " + paramException);
        paramException = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_DRM_RIGHTS_SERVER_ERROR, paramException);
      }
    }
  }
  
  public static boolean isDevicePersonalized(Context paramContext)
  {
    try
    {
      boolean bool = DxDrmDlc.getDxDrmDlc(paramContext, null).personalizationVerify();
      return bool;
    }
    catch (DrmClientInitFailureException paramContext)
    {
      DebugMode.logE(TAG, "Caught!", paramContext);
      return false;
    }
    catch (IllegalArgumentException paramContext)
    {
      for (;;)
      {
        DebugMode.logE(TAG, "Caught!", paramContext);
      }
    }
    catch (DrmAndroidPermissionMissingException paramContext)
    {
      for (;;)
      {
        DebugMode.logE(TAG, "Caught!", paramContext);
      }
    }
  }
  
  public static boolean isDiscredixVersionCorrect(Context paramContext)
  {
    try
    {
      paramContext = DxDrmDlc.getDxDrmDlc(paramContext, null);
      DebugMode.logD(TAG, "isDiscredixVersionCorrect. Discredix Version: " + paramContext.getDrmVersion());
      if (!paramContext.getDrmVersion().contains("03_05_02_0001"))
      {
        DebugMode.logE(TAG, "Discredix Version was not expected! Looking for: 03_05_02_0001, Actual: " + paramContext.getDrmVersion());
        DebugMode.logE(TAG, "Please ask your CSM for updated versions of the Discredix/SecurePlayer Libraries");
        return false;
      }
    }
    catch (DrmGeneralFailureException paramContext)
    {
      DebugMode.logE(TAG, "Caught!", paramContext);
      return false;
    }
    catch (DrmClientInitFailureException paramContext)
    {
      DebugMode.logE(TAG, "Caught!", paramContext);
      return false;
    }
    catch (DrmAndroidPermissionMissingException paramContext)
    {
      DebugMode.logE(TAG, "Caught!", paramContext);
    }
    return true;
  }
  
  public static boolean isStreamProtected(Context paramContext, String paramString)
  {
    try
    {
      paramContext = DxDrmDlc.getDxDrmDlc(paramContext, null);
      DebugMode.logD(TAG, "isStreamProtected. Discredix Version: " + paramContext.getDrmVersion());
      boolean bool = paramContext.isDrmContent(paramString);
      return bool;
    }
    catch (FileNotFoundException paramContext)
    {
      DebugMode.logE(TAG, "Caught!", paramContext);
      return false;
    }
    catch (DrmClientInitFailureException paramContext)
    {
      DebugMode.logE(TAG, "Caught!", paramContext);
      return false;
    }
    catch (DrmGeneralFailureException paramContext)
    {
      DebugMode.logE(TAG, "Caught!", paramContext);
      return false;
    }
    catch (DrmAndroidPermissionMissingException paramContext)
    {
      DebugMode.logE(TAG, "Caught!", paramContext);
    }
    return false;
  }
  
  public static void warmDxDrmDlc(Context paramContext)
  {
    DebugMode.logD(TAG, "Warming DxDrmDlc");
    try
    {
      paramContext = DxDrmDlc.getDxDrmDlc(paramContext, null);
      try
      {
        DebugMode.logD(TAG, "Discredix Version: " + paramContext.getDrmVersion());
        return;
      }
      catch (DrmGeneralFailureException paramContext)
      {
        DebugMode.logE(TAG, "Failed trying to get discredix version");
        DebugMode.logE(TAG, "Caught!", paramContext);
        return;
      }
      return;
    }
    catch (DrmClientInitFailureException paramContext)
    {
      DebugMode.logE(TAG, "Caught!", paramContext);
      return;
    }
    catch (DrmAndroidPermissionMissingException paramContext)
    {
      DebugMode.logE(TAG, "Caught!", paramContext);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\visualon\DiscredixDrmUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */