package com.phonegap.plugin;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.text.Html;
import android.util.Base64;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(11)
public class SocialSharing
  extends CordovaPlugin
{
  private static final String ACTION_AVAILABLE_EVENT = "available";
  private static final String ACTION_CAN_SHARE_VIA = "canShareVia";
  private static final String ACTION_CAN_SHARE_VIA_EMAIL = "canShareViaEmail";
  private static final String ACTION_SHARE_EVENT = "share";
  private static final String ACTION_SHARE_VIA = "shareVia";
  private static final String ACTION_SHARE_VIA_EMAIL_EVENT = "shareViaEmail";
  private static final String ACTION_SHARE_VIA_FACEBOOK_EVENT = "shareViaFacebook";
  private static final String ACTION_SHARE_VIA_FACEBOOK_WITH_PASTEMESSAGEHINT = "shareViaFacebookWithPasteMessageHint";
  private static final String ACTION_SHARE_VIA_SMS_EVENT = "shareViaSMS";
  private static final String ACTION_SHARE_VIA_TWITTER_EVENT = "shareViaTwitter";
  private static final String ACTION_SHARE_VIA_WHATSAPP_EVENT = "shareViaWhatsApp";
  private static final int ACTIVITY_CODE_SENDVIAEMAIL = 2;
  private CallbackContext _callbackContext;
  String message = "";
  private String pasteMessage;
  
  private void cleanupOldFiles(File paramFile)
  {
    paramFile = paramFile.listFiles();
    int j = paramFile.length;
    int i = 0;
    while (i < j)
    {
      paramFile[i].delete();
      i += 1;
    }
  }
  
  private void createOrCleanDir(String paramString)
    throws IOException
  {
    paramString = new File(paramString);
    if (!paramString.exists())
    {
      if (!paramString.mkdirs()) {
        throw new IOException("CREATE_DIRS_FAILED");
      }
    }
    else {
      cleanupOldFiles(paramString);
    }
  }
  
  private boolean doSendIntent(CallbackContext paramCallbackContext, final String paramString1, final String paramString2, final JSONArray paramJSONArray, String paramString3, final String paramString4, final boolean paramBoolean)
  {
    final CordovaInterface localCordovaInterface = this.cordova;
    this.message = paramString1;
    if (notEmpty(paramString3))
    {
      if (notEmpty(this.message)) {
        this.message = (this.message + " " + paramString3);
      }
    }
    else
    {
      if (Build.VERSION.SDK_INT >= 11) {
        break label155;
      }
      ((android.text.ClipboardManager)this.webView.getContext().getSystemService("clipboard")).setText(this.message);
    }
    for (;;)
    {
      Toast.makeText(this.webView.getContext(), "text copied to clipboard.", 1).show();
      this.cordova.getThreadPool().execute(new SocialSharingRunnable(paramCallbackContext, paramJSONArray)
      {
        public void run()
        {
          int i;
          Object localObject1;
          label20:
          Intent localIntent;
          if (paramJSONArray.length() > 1)
          {
            i = 1;
            if (i == 0) {
              break label129;
            }
            localObject1 = "android.intent.action.SEND_MULTIPLE";
            localIntent = new Intent((String)localObject1);
            localIntent.addFlags(524288);
            if (paramJSONArray.length() <= 0) {
              break label344;
            }
            localObject2 = new ArrayList();
          }
          Object localObject3;
          for (;;)
          {
            try
            {
              localObject3 = SocialSharing.this.getDownloadDir();
              localObject1 = null;
              int j = 0;
              if (j < paramJSONArray.length())
              {
                localObject1 = SocialSharing.this.getFileUriAndSetType(localIntent, (String)localObject3, paramJSONArray.getString(j), paramString2, j);
                if (localObject1 != null) {
                  ((ArrayList)localObject2).add(localObject1);
                }
                j += 1;
                continue;
                i = 0;
                break;
                label129:
                localObject1 = "android.intent.action.SEND";
                break label20;
              }
              if (!((ArrayList)localObject2).isEmpty())
              {
                if (i == 0) {
                  continue;
                }
                localIntent.putExtra("android.intent.extra.STREAM", (Serializable)localObject2);
              }
            }
            catch (Exception localException)
            {
              this.callbackContext.error(localException.getMessage());
              continue;
            }
            if (SocialSharing.notEmpty(paramString2)) {
              localIntent.putExtra("android.intent.extra.SUBJECT", paramString2);
            }
            if (SocialSharing.notEmpty(SocialSharing.this.message))
            {
              localIntent.putExtra("android.intent.extra.TEXT", SocialSharing.this.message);
              localIntent.putExtra("sms_body", SocialSharing.this.message);
            }
            if (paramString4 == null) {
              break label450;
            }
            localObject3 = paramString4;
            localObject1 = null;
            localObject2 = localObject3;
            if (((String)localObject3).contains("/"))
            {
              localObject1 = paramString4.split("/");
              localObject2 = localObject1[0];
              localObject1 = localObject1[1];
            }
            localObject3 = SocialSharing.this.getActivity(this.callbackContext, localIntent, (String)localObject2);
            if (localObject3 != null)
            {
              if (!paramBoolean) {
                break label355;
              }
              this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
            }
            return;
            localIntent.putExtra("android.intent.extra.STREAM", (Parcelable)localObject1);
            continue;
            label344:
            localIntent.setType("text/plain");
          }
          label355:
          localIntent.addCategory("android.intent.category.LAUNCHER");
          Object localObject2 = ((ActivityInfo)localObject3).applicationInfo.packageName;
          if (localException != null) {}
          for (;;)
          {
            localIntent.setComponent(new ComponentName((String)localObject2, localException));
            localCordovaInterface.startActivityForResult(jdField_this, localIntent, 0);
            if (SocialSharing.this.pasteMessage == null) {
              break;
            }
            new Timer().schedule(new TimerTask()
            {
              public void run()
              {
                SocialSharing.this.cordova.getActivity().runOnUiThread(new Runnable()
                {
                  public void run()
                  {
                    SocialSharing.this.showPasteMessage(SocialSharing.2.this.val$msg, SocialSharing.this.pasteMessage);
                  }
                });
              }
            }, 2000L);
            return;
            String str = ((ActivityInfo)localObject3).name;
          }
          label450:
          if (paramBoolean)
          {
            this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
            return;
          }
          localCordovaInterface.startActivityForResult(jdField_this, Intent.createChooser(localIntent, null), 1);
        }
      });
      return true;
      this.message = paramString3;
      break;
      label155:
      ((android.content.ClipboardManager)this.webView.getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Copied Text", this.message));
    }
  }
  
  private ActivityInfo getActivity(CallbackContext paramCallbackContext, Intent paramIntent, String paramString)
  {
    paramIntent = this.webView.getContext().getPackageManager().queryIntentActivities(paramIntent, 0);
    Iterator localIterator = paramIntent.iterator();
    while (localIterator.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)localIterator.next();
      if (localResolveInfo.activityInfo.packageName.contains(paramString)) {
        return localResolveInfo.activityInfo;
      }
    }
    paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, getShareActivities(paramIntent)));
    return null;
  }
  
  private byte[] getBytes(InputStream paramInputStream)
    throws IOException
  {
    paramInputStream = new BufferedInputStream(paramInputStream);
    ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(5000);
    for (;;)
    {
      int i = paramInputStream.read();
      if (i == -1) {
        break;
      }
      localByteArrayBuffer.append((byte)i);
    }
    return localByteArrayBuffer.toByteArray();
  }
  
  private String getDownloadDir()
    throws IOException
  {
    String str = this.webView.getContext().getExternalFilesDir(null) + "/socialsharing-downloads";
    createOrCleanDir(str);
    return str;
  }
  
  private String getFileName(String paramString)
  {
    int i = paramString.lastIndexOf('/');
    if (i == -1) {
      return paramString;
    }
    return paramString.substring(i + 1);
  }
  
  private Uri getFileUriAndSetType(Intent paramIntent, String paramString1, String paramString2, String paramString3, int paramInt)
    throws IOException
  {
    String str = paramString2;
    paramIntent.setType("image/*");
    if ((paramString2.startsWith("http")) || (paramString2.startsWith("www/")))
    {
      str = getFileName(paramString2);
      paramString3 = "file://" + paramString1 + "/" + str;
      if (paramString2.startsWith("http"))
      {
        URLConnection localURLConnection = new URL(paramString2).openConnection();
        Object localObject = localURLConnection.getHeaderField("Content-Disposition");
        paramString2 = str;
        paramIntent = paramString3;
        if (localObject != null)
        {
          localObject = Pattern.compile("filename=([^;]+)").matcher((CharSequence)localObject);
          paramString2 = str;
          paramIntent = paramString3;
          if (((Matcher)localObject).find())
          {
            paramString2 = ((Matcher)localObject).group(1).replaceAll("[^a-zA-Z0-9._-]", "");
            paramIntent = "file://" + paramString1 + "/" + paramString2;
          }
        }
        saveFile(getBytes(localURLConnection.getInputStream()), paramString1, paramString2);
      }
    }
    label463:
    do
    {
      for (;;)
      {
        return Uri.parse(paramIntent);
        saveFile(getBytes(this.webView.getContext().getAssets().open(paramString2)), paramString1, str);
        paramIntent = paramString3;
      }
      if (paramString2.startsWith("data:"))
      {
        if (!paramString2.contains(";base64,"))
        {
          paramIntent.setType("text/plain");
          return null;
        }
        str = paramString2.substring(paramString2.indexOf(";base64,") + 8);
        if (!paramString2.contains("data:image/")) {
          paramIntent.setType(paramString2.substring(paramString2.indexOf("data:") + 5, paramString2.indexOf(";base64")));
        }
        paramString2 = paramString2.substring(paramString2.indexOf("/") + 1, paramString2.indexOf(";base64"));
        paramIntent = "file." + paramString2;
        if (notEmpty(paramString3))
        {
          paramString3 = new StringBuilder().append(sanitizeFilename(paramString3));
          if (paramInt != 0) {
            break label463;
          }
        }
        for (paramIntent = "";; paramIntent = "_" + paramInt)
        {
          paramIntent = paramIntent + "." + paramString2;
          saveFile(Base64.decode(str, 0), paramString1, paramIntent);
          paramIntent = "file://" + paramString1 + "/" + paramIntent;
          break;
        }
      }
      paramIntent = str;
    } while (paramString2.startsWith("file://"));
    throw new IllegalArgumentException("URL_NOT_SUPPORTED");
  }
  
  private static String getPhoneNumbersWithManufacturerSpecificSeparators(String paramString)
  {
    if (notEmpty(paramString))
    {
      if (Build.MANUFACTURER.equalsIgnoreCase("samsung")) {}
      for (char c = ',';; c = ';') {
        return paramString.replace(';', c).replace(',', c);
      }
    }
    return null;
  }
  
  private JSONArray getShareActivities(List<ResolveInfo> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      localArrayList.add(((ResolveInfo)paramList.next()).activityInfo.packageName);
    }
    return new JSONArray(localArrayList);
  }
  
  private boolean invokeEmailIntent(CallbackContext paramCallbackContext, final String paramString1, final String paramString2, final JSONArray paramJSONArray1, final JSONArray paramJSONArray2, final JSONArray paramJSONArray3, final JSONArray paramJSONArray4)
    throws JSONException
  {
    this.cordova.getThreadPool().execute(new SocialSharingRunnable(paramCallbackContext, paramString1)
    {
      public void run()
      {
        Intent localIntent = new Intent("android.intent.action.SEND_MULTIPLE");
        if (SocialSharing.notEmpty(paramString1))
        {
          if (!paramString1.matches(".*<[^>]+>.*")) {
            break label251;
          }
          localIntent.putExtra("android.intent.extra.TEXT", Html.fromHtml(paramString1));
          localIntent.setType("text/html");
        }
        for (;;)
        {
          if (SocialSharing.notEmpty(paramString2)) {
            localIntent.putExtra("android.intent.extra.SUBJECT", paramString2);
          }
          try
          {
            if ((paramJSONArray1 != null) && (paramJSONArray1.length() > 0)) {
              localIntent.putExtra("android.intent.extra.EMAIL", SocialSharing.toStringArray(paramJSONArray1));
            }
            if ((paramJSONArray2 != null) && (paramJSONArray2.length() > 0)) {
              localIntent.putExtra("android.intent.extra.CC", SocialSharing.toStringArray(paramJSONArray2));
            }
            if ((paramJSONArray3 != null) && (paramJSONArray3.length() > 0)) {
              localIntent.putExtra("android.intent.extra.BCC", SocialSharing.toStringArray(paramJSONArray3));
            }
            if (paramJSONArray4.length() > 0)
            {
              ArrayList localArrayList = new ArrayList();
              String str = SocialSharing.this.getDownloadDir();
              int i = 0;
              for (;;)
              {
                if (i < paramJSONArray4.length())
                {
                  Uri localUri = SocialSharing.this.getFileUriAndSetType(localIntent, str, paramJSONArray4.getString(i), paramString2, i);
                  if (localUri != null) {
                    localArrayList.add(localUri);
                  }
                  i += 1;
                  continue;
                  label251:
                  localIntent.putExtra("android.intent.extra.TEXT", paramString1);
                  localIntent.setType("text/plain");
                  break;
                }
              }
              if (!localArrayList.isEmpty()) {
                localIntent.putExtra("android.intent.extra.STREAM", localArrayList);
              }
            }
          }
          catch (Exception localException)
          {
            for (;;)
            {
              this.callbackContext.error(localException.getMessage());
            }
          }
        }
        localIntent.setType("application/octet-stream");
        SocialSharing.this.cordova.startActivityForResult(jdField_this, Intent.createChooser(localIntent, "Choose Email App"), 2);
      }
    });
    return true;
  }
  
  private boolean invokeSMSIntent(CallbackContext paramCallbackContext, final JSONObject paramJSONObject, final String paramString)
  {
    paramJSONObject = paramJSONObject.optString("message");
    paramString = getPhoneNumbersWithManufacturerSpecificSeparators(paramString);
    this.cordova.getThreadPool().execute(new SocialSharingRunnable(paramCallbackContext, paramString)
    {
      public void run()
      {
        Object localObject2;
        Object localObject1;
        if (Build.VERSION.SDK_INT >= 19)
        {
          localObject2 = new Intent("android.intent.action.SENDTO");
          StringBuilder localStringBuilder = new StringBuilder().append("smsto:");
          if (SocialSharing.notEmpty(paramString))
          {
            localObject1 = paramString;
            ((Intent)localObject2).setData(Uri.parse((String)localObject1));
            localObject1 = localObject2;
          }
        }
        for (;;)
        {
          ((Intent)localObject1).putExtra("sms_body", paramJSONObject);
          ((Intent)localObject1).putExtra("sms_subject", this.val$subject);
          try
          {
            if ((this.val$image != null) && (!"".equals(this.val$image)))
            {
              localObject2 = SocialSharing.this.getFileUriAndSetType((Intent)localObject1, SocialSharing.access$200(SocialSharing.this), this.val$image, this.val$subject, 0);
              if (localObject2 != null) {
                ((Intent)localObject1).putExtra("android.intent.extra.STREAM", (Parcelable)localObject2);
              }
            }
            SocialSharing.this.cordova.startActivityForResult(jdField_this, (Intent)localObject1, 0);
            return;
          }
          catch (Exception localException)
          {
            this.callbackContext.error(localException.getMessage());
          }
          localObject1 = "";
          break;
          localObject2 = new Intent("android.intent.action.VIEW");
          ((Intent)localObject2).setType("vnd.android-dir/mms-sms");
          localObject1 = localObject2;
          if (paramString != null)
          {
            ((Intent)localObject2).putExtra("address", paramString);
            localObject1 = localObject2;
          }
        }
      }
    });
    return true;
  }
  
  private boolean isEmailAvailable()
  {
    Intent localIntent = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", "someone@domain.com", null));
    return this.cordova.getActivity().getPackageManager().queryIntentActivities(localIntent, 0).size() > 1;
  }
  
  private static boolean notEmpty(String paramString)
  {
    return (paramString != null) && (!"".equals(paramString)) && (!"null".equalsIgnoreCase(paramString));
  }
  
  public static String sanitizeFilename(String paramString)
  {
    return paramString.replaceAll("[:\\\\/*?|<> ]", "_");
  }
  
  private void saveFile(byte[] paramArrayOfByte, String paramString1, String paramString2)
    throws IOException
  {
    paramString1 = new FileOutputStream(new File(new File(paramString1), paramString2));
    paramString1.write(paramArrayOfByte);
    paramString1.flush();
    paramString1.close();
  }
  
  @SuppressLint({"NewApi"})
  private void showPasteMessage(String paramString1, String paramString2)
  {
    if (Build.VERSION.SDK_INT < 11) {
      return;
    }
    ((android.content.ClipboardManager)this.cordova.getActivity().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(paramString2, paramString1));
    paramString1 = Toast.makeText(this.webView.getContext(), paramString2, 1);
    paramString1.setGravity(17, 0, 0);
    paramString1.show();
  }
  
  private static String[] toStringArray(JSONArray paramJSONArray)
    throws JSONException
  {
    String[] arrayOfString = new String[paramJSONArray.length()];
    int i = 0;
    while (i < paramJSONArray.length())
    {
      arrayOfString[i] = paramJSONArray.getString(i);
      i += 1;
    }
    return arrayOfString;
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    this._callbackContext = paramCallbackContext;
    this.pasteMessage = null;
    if ("available".equals(paramString))
    {
      paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
      return true;
    }
    if ("share".equals(paramString)) {
      return doSendIntent(paramCallbackContext, paramJSONArray.getString(0), paramJSONArray.getString(1), paramJSONArray.getJSONArray(2), paramJSONArray.getString(3), null, false);
    }
    if ("shareViaTwitter".equals(paramString)) {
      return doSendIntent(paramCallbackContext, paramJSONArray.getString(0), paramJSONArray.getString(1), paramJSONArray.getJSONArray(2), paramJSONArray.getString(3), "twitter", false);
    }
    if ("shareViaFacebook".equals(paramString)) {
      return doSendIntent(paramCallbackContext, paramJSONArray.getString(0), paramJSONArray.getString(1), paramJSONArray.getJSONArray(2), paramJSONArray.getString(3), "com.facebook.katana", false);
    }
    if ("shareViaFacebookWithPasteMessageHint".equals(paramString))
    {
      this.pasteMessage = paramJSONArray.getString(4);
      return doSendIntent(paramCallbackContext, paramJSONArray.getString(0), paramJSONArray.getString(1), paramJSONArray.getJSONArray(2), paramJSONArray.getString(3), "com.facebook.katana", false);
    }
    if ("shareViaWhatsApp".equals(paramString)) {
      return doSendIntent(paramCallbackContext, paramJSONArray.getString(0), paramJSONArray.getString(1), paramJSONArray.getJSONArray(2), paramJSONArray.getString(3), "whatsapp", false);
    }
    if ("canShareVia".equals(paramString)) {
      return doSendIntent(paramCallbackContext, paramJSONArray.getString(0), paramJSONArray.getString(1), paramJSONArray.getJSONArray(2), paramJSONArray.getString(3), paramJSONArray.getString(4), true);
    }
    if ("canShareViaEmail".equals(paramString))
    {
      if (isEmailAvailable())
      {
        paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
        return true;
      }
      paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "not available"));
      return false;
    }
    if ("shareVia".equals(paramString)) {
      return doSendIntent(paramCallbackContext, paramJSONArray.getString(0), paramJSONArray.getString(1), paramJSONArray.getJSONArray(2), paramJSONArray.getString(3), paramJSONArray.getString(4), false);
    }
    if ("shareViaSMS".equals(paramString)) {
      return invokeSMSIntent(paramCallbackContext, paramJSONArray.getJSONObject(0), paramJSONArray.getString(1));
    }
    if ("shareViaEmail".equals(paramString))
    {
      String str1 = paramJSONArray.getString(0);
      String str2 = paramJSONArray.getString(1);
      JSONArray localJSONArray2 = paramJSONArray.getJSONArray(2);
      JSONArray localJSONArray1;
      if (paramJSONArray.isNull(3))
      {
        paramString = null;
        if (!paramJSONArray.isNull(4)) {
          break label481;
        }
        localJSONArray1 = null;
        label446:
        if (!paramJSONArray.isNull(5)) {
          break label491;
        }
      }
      label481:
      label491:
      for (paramJSONArray = null;; paramJSONArray = paramJSONArray.getJSONArray(5))
      {
        return invokeEmailIntent(paramCallbackContext, str1, str2, localJSONArray2, paramString, localJSONArray1, paramJSONArray);
        paramString = paramJSONArray.getJSONArray(3);
        break;
        localJSONArray1 = paramJSONArray.getJSONArray(4);
        break label446;
      }
    }
    paramCallbackContext.error("socialSharing." + paramString + " is not a supported function. Did you mean '" + "share" + "'?");
    return false;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (2 == paramInt1)
    {
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      this._callbackContext.success();
      return;
    }
    paramIntent = this._callbackContext;
    PluginResult.Status localStatus = PluginResult.Status.OK;
    if (paramInt2 == -1) {}
    for (boolean bool = true;; bool = false)
    {
      paramIntent.sendPluginResult(new PluginResult(localStatus, bool));
      return;
    }
  }
  
  private abstract class SocialSharingRunnable
    implements Runnable
  {
    public CallbackContext callbackContext;
    
    SocialSharingRunnable(CallbackContext paramCallbackContext)
    {
      this.callbackContext = paramCallbackContext;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\phonegap\plugin\SocialSharing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */