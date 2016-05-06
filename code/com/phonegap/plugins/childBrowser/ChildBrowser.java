package com.phonegap.plugins.childBrowser;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.example.example75f1799f07eb.AlertDialogManager;
import com.example.example75f1799f07eb.AppointmentSchedulerActivity;
import com.example.example75f1799f07eb.MenuScreen;
import com.example.example75f1799f07eb.MyApplicationName;
import com.example.example75f1799f07eb.SocialShare;
import java.io.IOException;
import java.io.PrintStream;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

public class ChildBrowser
  extends CordovaPlugin
{
  private static int CLOSE_EVENT = 0;
  private static final int FILECHOOSER_RESULTCODE = 1;
  private static int LOCATION_CHANGED_EVENT = 1;
  protected static final String LOG_TAG = "ChildBrowser";
  private static final String METHOD_NAME = "sendAppointment";
  private static final String NAMESPACE = "http://apps.appypie.com/services/soap/";
  private static final String SOAP_ACTION = "http://schemas.xmlsoap.org/wsdl/";
  private static final String URL = "http://apps.appypie.com/services/soap/";
  AlertDialogManager alert = new AlertDialogManager();
  private Dialog dialog;
  private TextView edittext;
  CallbackContext mContext;
  private ValueCallback<Uri> mUploadMessage;
  private boolean showLocationBar = true;
  private WebView webview;
  
  private void closeDialog()
  {
    if (this.dialog != null) {
      this.dialog.dismiss();
    }
  }
  
  private boolean getShowLocationBar()
  {
    return this.showLocationBar;
  }
  
  private void goBack()
  {
    if (this.webview.canGoBack()) {
      this.webview.goBack();
    }
  }
  
  private void goForward()
  {
    if (this.webview.canGoForward()) {
      this.webview.goForward();
    }
  }
  
  private void navigate(String paramString)
  {
    ((InputMethodManager)this.cordova.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.edittext.getWindowToken(), 0);
    if ((!paramString.startsWith("http")) && (!paramString.startsWith("file:"))) {
      this.webview.loadUrl("http://" + paramString);
    }
    for (;;)
    {
      this.webview.requestFocus();
      return;
      this.webview.loadUrl(paramString);
    }
  }
  
  private void sendUpdate(JSONObject paramJSONObject, boolean paramBoolean)
  {
    if (this.mContext != null)
    {
      new PluginResult(PluginResult.Status.OK, paramJSONObject).setKeepCallback(paramBoolean);
      this.mContext.success("success");
    }
  }
  
  public String appointmentServiceCall(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9)
  {
    System.out.println("appointmentServiceCall ");
    try
    {
      paramString8 = new SoapObject(MyApplicationName.SOAP_NAMESPACE, "sendAppointment");
      paramString9 = new AndroidHttpTransport(MyApplicationName.SOAP_URL);
      System.out.println(MyApplicationName.APP_ID + "   " + MyApplicationName.GCM_ID);
      System.out.println(paramString1);
      System.out.println(paramString2);
      System.out.println(paramString3);
      System.out.println(paramString4);
      System.out.println(paramString5);
      System.out.println(paramString6);
      System.out.println(paramString7);
      paramString8.addProperty("appId", paramString1);
      paramString8.addProperty("appointmentDate", paramString2);
      paramString8.addProperty("appointmentTime", paramString3);
      paramString8.addProperty("userName", paramString4);
      paramString8.addProperty("userEmail", paramString5);
      paramString8.addProperty("userPhone", paramString6);
      paramString8.addProperty("userRemark", paramString7);
      paramString8.addProperty("status", "1");
      paramString8.addProperty("userDeviceType", "Android");
      paramString8.addProperty("userDeviceId", "");
      paramString8.addProperty("userDeviceToken", MyApplicationName.GCM_ID);
      paramString1 = new SoapSerializationEnvelope(110);
      System.out.println("SOAP pass from serializationt for appointmentServiceCall=");
      paramString1.setOutputSoapObject(paramString8);
      System.out.println("SOAP pass from envelope.setOutputSoapObject(request) appointmentServiceCall");
      paramString9.call(MyApplicationName.SOAP_ACTION, paramString1);
      System.out.println("SOAP pass from envelope appointmentServiceCall");
      paramString2 = (KvmSerializable)paramString1.bodyIn;
      System.out.println("SOAP Result appointmentServiceCall=");
      paramString1 = (SoapObject)paramString1.bodyIn;
      paramString2.getProperty(0).toString();
      System.out.println("SOAP Result appointmentServiceCall=" + paramString1.toString());
      System.out.println("SOAP Result appointmentServiceCall=" + paramString2.getProperty(0).toString());
      if (paramString2.getProperty(0).toString().contains("success"))
      {
        System.out.println("Your request sent successfully.");
        this.alert.showAlertDialog(this.cordova.getActivity(), "", "Your request sent successfully.", 0);
        return "success";
      }
      System.out.println("Your request Revert.");
      this.alert.showAlertDialog(this.cordova.getActivity(), "", "Some error occurs please try again.", 0);
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        System.out.println(paramString1.toString() + "Exception SOAP-:" + paramString1.getMessage());
      }
    }
    return "fail";
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    PluginResult.Status localStatus = PluginResult.Status.OK;
    localObject = localStatus;
    for (;;)
    {
      try
      {
        if (!paramString.equals("openSocialShare")) {
          continue;
        }
        paramString = localStatus;
        localObject = localStatus;
        if (openSocialShare().length() > 0)
        {
          localObject = localStatus;
          paramString = PluginResult.Status.ERROR;
        }
      }
      catch (JSONException paramString)
      {
        boolean bool;
        paramString = (String)localObject;
        continue;
      }
      if (paramString != PluginResult.Status.OK) {
        continue;
      }
      return true;
      localObject = localStatus;
      bool = paramString.equals("exitApplication");
      if (bool)
      {
        localObject = localStatus;
        try
        {
          System.out.println("exitApplication");
          localObject = localStatus;
          System.exit(0);
        }
        catch (Exception paramString)
        {
          localObject = localStatus;
          System.out.println("exitApplication Exception");
          localObject = localStatus;
          paramString.printStackTrace();
          continue;
        }
        paramString = localStatus;
        localObject = localStatus;
        if ("".length() <= 0) {
          continue;
        }
        localObject = localStatus;
        paramString = PluginResult.Status.ERROR;
        continue;
      }
      localObject = localStatus;
      if (paramString.equals("openMenu"))
      {
        paramString = localStatus;
        localObject = localStatus;
        if (openMenu().length() > 0)
        {
          localObject = localStatus;
          paramString = PluginResult.Status.ERROR;
        }
      }
      else
      {
        localObject = localStatus;
        if (paramString.equals("appointmentServiceCall"))
        {
          localObject = localStatus;
          this.mContext = paramCallbackContext;
          localObject = localStatus;
          paramString = new Intent(this.cordova.getActivity(), AppointmentSchedulerActivity.class);
          localObject = localStatus;
          paramString.setFlags(67108864);
          localObject = localStatus;
          paramString.putExtra("APP_ID", paramJSONArray.getString(0));
          localObject = localStatus;
          paramString.putExtra("APOINT_DATE", paramJSONArray.getString(1));
          localObject = localStatus;
          paramString.putExtra("APOINT_TIME", paramJSONArray.getString(2));
          localObject = localStatus;
          paramString.putExtra("APOINT_NAME", paramJSONArray.getString(3));
          localObject = localStatus;
          paramString.putExtra("APOINT_EMAIL", paramJSONArray.getString(4));
          localObject = localStatus;
          paramString.putExtra("APOINT_PHONE", paramJSONArray.getString(5));
          localObject = localStatus;
          paramString.putExtra("APOINT_REMARK", paramJSONArray.getString(6));
          localObject = localStatus;
          paramString.putExtra("STATUS", paramJSONArray.getString(7));
          localObject = localStatus;
          paramString.putExtra("DEVICE_TYPE", paramJSONArray.getString(8));
          localObject = localStatus;
          System.out.println("The app id from args,,,,,,,,,,,,,,,,, " + paramJSONArray.getString(0));
          localObject = localStatus;
          this.cordova.startActivityForResult(this, paramString, 1);
          localObject = localStatus;
          paramString = PluginResult.Status.OK;
        }
        else
        {
          localObject = localStatus;
          if (paramString.equals("showWebPage"))
          {
            localObject = localStatus;
            this.mContext = paramCallbackContext;
            paramString = localStatus;
            localObject = localStatus;
            if (this.dialog != null)
            {
              paramString = localStatus;
              localObject = localStatus;
              if (this.dialog.isShowing())
              {
                localObject = localStatus;
                paramString = PluginResult.Status.ERROR;
              }
            }
            localObject = paramString;
            paramJSONArray = showWebPage(paramJSONArray.getString(0), paramJSONArray.optJSONObject(1));
            localObject = paramString;
            if (paramJSONArray.length() > 0)
            {
              localObject = paramString;
              paramString = PluginResult.Status.ERROR;
              localObject = paramString;
              paramString = PluginResult.Status.ERROR;
            }
            else
            {
              localObject = paramString;
              new PluginResult(paramString, paramJSONArray).setKeepCallback(true);
              localObject = paramString;
              paramString = PluginResult.Status.OK;
            }
          }
          else
          {
            localObject = localStatus;
            if (paramString.equals("close"))
            {
              localObject = localStatus;
              closeDialog();
              localObject = localStatus;
              paramString = new JSONObject();
              localObject = localStatus;
              paramString.put("type", CLOSE_EVENT);
              localObject = localStatus;
              new PluginResult(localStatus, paramString).setKeepCallback(false);
              localObject = localStatus;
              paramString = PluginResult.Status.OK;
            }
            else
            {
              localObject = localStatus;
              if (paramString.equals("openExternal"))
              {
                paramString = localStatus;
                localObject = localStatus;
                if (openExternal(paramJSONArray.getString(0), paramJSONArray.optBoolean(1)).length() > 0)
                {
                  localObject = localStatus;
                  paramString = PluginResult.Status.ERROR;
                }
              }
              else
              {
                localObject = localStatus;
                paramString = PluginResult.Status.INVALID_ACTION;
              }
            }
          }
        }
      }
    }
    return false;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 != 1) || (this.mUploadMessage == null)) {
      return;
    }
    if ((paramIntent == null) || (paramInt2 != -1)) {}
    for (paramIntent = null;; paramIntent = paramIntent.getData())
    {
      this.mUploadMessage.onReceiveValue(paramIntent);
      this.mUploadMessage = null;
      return;
    }
  }
  
  /* Error */
  public String openExternal(String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: iload_2
    //   1: ifeq +92 -> 93
    //   4: new 394	android/content/Intent
    //   7: dup
    //   8: invokespecial 490	android/content/Intent:<init>	()V
    //   11: aload_0
    //   12: getfield 148	com/phonegap/plugins/childBrowser/ChildBrowser:cordova	Lorg/apache/cordova/CordovaInterface;
    //   15: invokeinterface 154 1 0
    //   20: ldc_w 492
    //   23: invokevirtual 496	android/content/Intent:setClass	(Landroid/content/Context;Ljava/lang/Class;)Landroid/content/Intent;
    //   26: astore_3
    //   27: aload_3
    //   28: aload_1
    //   29: invokestatic 502	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   32: invokevirtual 506	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   35: pop
    //   36: aload_3
    //   37: ldc_w 508
    //   40: aload_1
    //   41: invokevirtual 415	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   44: pop
    //   45: aload_3
    //   46: ldc_w 510
    //   49: ldc_w 511
    //   52: invokevirtual 514	android/content/Intent:putExtra	(Ljava/lang/String;I)Landroid/content/Intent;
    //   55: pop
    //   56: aload_3
    //   57: ldc_w 516
    //   60: ldc_w 518
    //   63: invokevirtual 415	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   66: pop
    //   67: aload_3
    //   68: ldc_w 520
    //   71: iconst_1
    //   72: invokevirtual 523	android/content/Intent:putExtra	(Ljava/lang/String;Z)Landroid/content/Intent;
    //   75: pop
    //   76: aload_0
    //   77: getfield 148	com/phonegap/plugins/childBrowser/ChildBrowser:cordova	Lorg/apache/cordova/CordovaInterface;
    //   80: invokeinterface 154 1 0
    //   85: aload_3
    //   86: invokevirtual 527	android/app/Activity:startActivity	(Landroid/content/Intent;)V
    //   89: ldc_w 298
    //   92: areturn
    //   93: new 394	android/content/Intent
    //   96: dup
    //   97: ldc_w 529
    //   100: invokespecial 530	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   103: astore_3
    //   104: aload_3
    //   105: aload_1
    //   106: invokestatic 502	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   109: invokevirtual 506	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   112: pop
    //   113: goto -37 -> 76
    //   116: astore_3
    //   117: ldc 31
    //   119: new 186	java/lang/StringBuilder
    //   122: dup
    //   123: invokespecial 187	java/lang/StringBuilder:<init>	()V
    //   126: ldc_w 532
    //   129: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: aload_1
    //   133: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: ldc_w 534
    //   139: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: aload_3
    //   143: invokevirtual 535	android/content/ActivityNotFoundException:toString	()Ljava/lang/String;
    //   146: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: invokevirtual 197	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   152: invokestatic 541	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   155: pop
    //   156: aload_3
    //   157: invokevirtual 535	android/content/ActivityNotFoundException:toString	()Ljava/lang/String;
    //   160: areturn
    //   161: astore_3
    //   162: goto -45 -> 117
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	165	0	this	ChildBrowser
    //   0	165	1	paramString	String
    //   0	165	2	paramBoolean	boolean
    //   26	79	3	localIntent	Intent
    //   116	41	3	localActivityNotFoundException1	android.content.ActivityNotFoundException
    //   161	1	3	localActivityNotFoundException2	android.content.ActivityNotFoundException
    // Exception table:
    //   from	to	target	type
    //   4	76	116	android/content/ActivityNotFoundException
    //   76	89	116	android/content/ActivityNotFoundException
    //   93	104	116	android/content/ActivityNotFoundException
    //   104	113	161	android/content/ActivityNotFoundException
  }
  
  public String openMenu()
  {
    System.out.println("************** Hello openMenu ****");
    try
    {
      Intent localIntent = new Intent(this.cordova.getActivity(), MenuScreen.class);
      localIntent.setFlags(67108864);
      this.cordova.startActivityForResult(this, localIntent, 1);
      return "";
    }
    catch (Exception localException)
    {
      for (;;)
      {
        System.out.println("openMenu Exception");
        localException.printStackTrace();
      }
    }
  }
  
  public String openSocialShare()
  {
    try
    {
      Intent localIntent = new Intent(this.cordova.getActivity(), SocialShare.class);
      localIntent.setFlags(67108864);
      this.cordova.startActivityForResult(this, localIntent, 1);
      return "";
    }
    catch (Exception localException)
    {
      for (;;)
      {
        System.out.println("openSocialShare Exception in ChieldBrowser.java");
        localException.printStackTrace();
      }
    }
  }
  
  public String showWebPage(final String paramString, JSONObject paramJSONObject)
  {
    if (paramJSONObject != null) {
      this.showLocationBar = paramJSONObject.optBoolean("showLocationBar", true);
    }
    paramString = new Runnable()
    {
      private int dpToPixels(int paramAnonymousInt)
      {
        return (int)TypedValue.applyDimension(1, paramAnonymousInt, ChildBrowser.this.cordova.getActivity().getResources().getDisplayMetrics());
      }
      
      private Bitmap loadDrawable(String paramAnonymousString)
        throws IOException
      {
        return BitmapFactory.decodeStream(ChildBrowser.this.cordova.getActivity().getAssets().open(paramAnonymousString));
      }
      
      @TargetApi(13)
      public void run()
      {
        ChildBrowser.access$002(ChildBrowser.this, new Dialog(ChildBrowser.this.cordova.getActivity(), 16973830));
        ChildBrowser.this.dialog.getWindow().getAttributes().windowAnimations = 16973826;
        ChildBrowser.this.dialog.requestWindowFeature(1);
        ChildBrowser.this.dialog.setCancelable(true);
        ChildBrowser.this.dialog.setOnDismissListener(new DialogInterface.OnDismissListener()
        {
          public void onDismiss(DialogInterface paramAnonymous2DialogInterface)
          {
            try
            {
              paramAnonymous2DialogInterface = new JSONObject();
              paramAnonymous2DialogInterface.put("type", ChildBrowser.CLOSE_EVENT);
              ChildBrowser.this.sendUpdate(paramAnonymous2DialogInterface, false);
              ChildBrowser.this.closeDialog();
              ChildBrowser.this.webview.clearCache(true);
              ChildBrowser.this.webview.clearHistory();
              ChildBrowser.this.webview.destroy();
              return;
            }
            catch (JSONException paramAnonymous2DialogInterface)
            {
              Log.d("ChildBrowser", "Should never happen");
            }
          }
        });
        LinearLayout localLinearLayout = new LinearLayout(ChildBrowser.this.cordova.getActivity());
        localLinearLayout.setOrientation(1);
        Object localObject1 = new RelativeLayout(ChildBrowser.this.cordova.getActivity());
        ((RelativeLayout)localObject1).setLayoutParams(new RelativeLayout.LayoutParams(-1, dpToPixels(50)));
        ((RelativeLayout)localObject1).setPadding(dpToPixels(2), dpToPixels(2), dpToPixels(2), dpToPixels(2));
        ((RelativeLayout)localObject1).setHorizontalGravity(3);
        ((RelativeLayout)localObject1).setVerticalGravity(48);
        ((RelativeLayout)localObject1).setBackgroundColor(17170454);
        Object localObject2 = new RelativeLayout(ChildBrowser.this.cordova.getActivity());
        ((RelativeLayout)localObject2).setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        ((RelativeLayout)localObject2).setHorizontalGravity(3);
        ((RelativeLayout)localObject2).setVerticalGravity(16);
        ((RelativeLayout)localObject2).setId(1);
        ImageButton localImageButton = new ImageButton(ChildBrowser.this.cordova.getActivity());
        Object localObject3 = new RelativeLayout.LayoutParams(-2, -1);
        ((RelativeLayout.LayoutParams)localObject3).addRule(5);
        localImageButton.setLayoutParams((ViewGroup.LayoutParams)localObject3);
        localImageButton.setContentDescription("Back Button");
        localImageButton.setId(2);
        try
        {
          localImageButton.setImageBitmap(loadDrawable("www/childbrowser/icon_arrow_left.png"));
          localImageButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              ChildBrowser.this.goBack();
            }
          });
          localObject3 = new ImageButton(ChildBrowser.this.cordova.getActivity());
          localObject4 = new RelativeLayout.LayoutParams(-2, -1);
          ((RelativeLayout.LayoutParams)localObject4).addRule(1, 2);
          ((ImageButton)localObject3).setLayoutParams((ViewGroup.LayoutParams)localObject4);
          ((ImageButton)localObject3).setContentDescription("Forward Button");
          ((ImageButton)localObject3).setId(3);
        }
        catch (IOException localIOException2)
        {
          try
          {
            ((ImageButton)localObject3).setImageBitmap(loadDrawable("www/childbrowser/icon_arrow_right.png"));
            ((ImageButton)localObject3).setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymous2View)
              {
                ChildBrowser.this.goForward();
              }
            });
            ChildBrowser.access$702(ChildBrowser.this, new TextView(ChildBrowser.this.cordova.getActivity()));
            localObject4 = new RelativeLayout.LayoutParams(-1, -1);
            ((RelativeLayout.LayoutParams)localObject4).addRule(1, 1);
            ((RelativeLayout.LayoutParams)localObject4).addRule(0, 5);
            ChildBrowser.this.edittext.setLayoutParams((ViewGroup.LayoutParams)localObject4);
            ChildBrowser.this.edittext.setTextSize(20.0F);
            ChildBrowser.this.edittext.setPadding(20, 5, 0, 5);
            ChildBrowser.this.edittext.setId(4);
            ChildBrowser.this.edittext.setClickable(false);
            ChildBrowser.this.edittext.setSingleLine(true);
            ChildBrowser.this.edittext.setText(MyApplicationName.APP_NAME);
            ChildBrowser.this.edittext.setInputType(16);
            ChildBrowser.this.edittext.setImeOptions(2);
            ChildBrowser.this.edittext.setInputType(0);
            ChildBrowser.this.edittext.setOnKeyListener(new View.OnKeyListener()
            {
              public boolean onKey(View paramAnonymous2View, int paramAnonymous2Int, KeyEvent paramAnonymous2KeyEvent)
              {
                if ((paramAnonymous2KeyEvent.getAction() == 0) && (paramAnonymous2Int == 66))
                {
                  ChildBrowser.this.navigate(ChildBrowser.this.edittext.getText().toString());
                  return true;
                }
                return false;
              }
            });
            localObject4 = new ImageButton(ChildBrowser.this.cordova.getActivity());
            localObject5 = new RelativeLayout.LayoutParams(-2, -1);
            ((RelativeLayout.LayoutParams)localObject5).addRule(11);
            ((ImageButton)localObject4).setLayoutParams((ViewGroup.LayoutParams)localObject5);
            ((ImageButton)localObject3).setContentDescription("Close Button");
            ((ImageButton)localObject4).setId(5);
          }
          catch (IOException localIOException2)
          {
            try
            {
              for (;;)
              {
                Object localObject4;
                ((ImageButton)localObject4).setImageBitmap(loadDrawable("www/childbrowser/icon_close.png"));
                ((ImageButton)localObject4).setOnClickListener(new View.OnClickListener()
                {
                  public void onClick(View paramAnonymous2View)
                  {
                    try
                    {
                      paramAnonymous2View = new JSONObject();
                      paramAnonymous2View.put("type", ChildBrowser.CLOSE_EVENT);
                      ChildBrowser.this.sendUpdate(paramAnonymous2View, false);
                      ChildBrowser.this.closeDialog();
                      ChildBrowser.this.webview.clearCache(true);
                      ChildBrowser.this.webview.clearHistory();
                      return;
                    }
                    catch (JSONException paramAnonymous2View)
                    {
                      paramAnonymous2View.printStackTrace();
                    }
                  }
                });
                ChildBrowser.access$402(ChildBrowser.this, new WebView(ChildBrowser.this.cordova.getActivity()));
                ChildBrowser.this.webview.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                ChildBrowser.this.webview.setWebChromeClient(new WebChromeClient());
                Object localObject5 = new ChildBrowser.ChildBrowserClient(ChildBrowser.this, ChildBrowser.this.edittext);
                ChildBrowser.this.webview.setWebViewClient((WebViewClient)localObject5);
                ChildBrowser.this.webview.setWebChromeClient(new WebChromeClient()
                {
                  public void openFileChooser(ValueCallback<Uri> paramAnonymous2ValueCallback)
                  {
                    ChildBrowser.access$902(ChildBrowser.this, paramAnonymous2ValueCallback);
                    paramAnonymous2ValueCallback = new Intent("android.intent.action.GET_CONTENT");
                    paramAnonymous2ValueCallback.addCategory("android.intent.category.OPENABLE");
                    paramAnonymous2ValueCallback.setType("image/*");
                    ChildBrowser.this.cordova.startActivityForResult(ChildBrowser.this, Intent.createChooser(paramAnonymous2ValueCallback, "File Chooser"), 1);
                  }
                  
                  public void openFileChooser(ValueCallback paramAnonymous2ValueCallback, String paramAnonymous2String)
                  {
                    ChildBrowser.access$902(ChildBrowser.this, paramAnonymous2ValueCallback);
                    paramAnonymous2ValueCallback = new Intent("android.intent.action.GET_CONTENT");
                    paramAnonymous2ValueCallback.addCategory("android.intent.category.OPENABLE");
                    paramAnonymous2ValueCallback.setType("*/*");
                    ChildBrowser.this.cordova.getActivity().startActivityForResult(Intent.createChooser(paramAnonymous2ValueCallback, "File Browser"), 1);
                  }
                  
                  public void openFileChooser(ValueCallback<Uri> paramAnonymous2ValueCallback, String paramAnonymous2String1, String paramAnonymous2String2)
                  {
                    ChildBrowser.access$902(ChildBrowser.this, paramAnonymous2ValueCallback);
                    paramAnonymous2ValueCallback = new Intent("android.intent.action.GET_CONTENT");
                    paramAnonymous2ValueCallback.addCategory("android.intent.category.OPENABLE");
                    paramAnonymous2ValueCallback.setType("image/*");
                    ChildBrowser.this.cordova.startActivityForResult(ChildBrowser.this, Intent.createChooser(paramAnonymous2ValueCallback, "File Chooser"), 1);
                  }
                });
                localObject5 = ChildBrowser.this.webview.getSettings();
                ((WebSettings)localObject5).setJavaScriptEnabled(true);
                ((WebSettings)localObject5).setJavaScriptCanOpenWindowsAutomatically(true);
                ((WebSettings)localObject5).setBuiltInZoomControls(true);
                ((WebSettings)localObject5).setPluginState(WebSettings.PluginState.ON);
                ((WebSettings)localObject5).setDomStorageEnabled(true);
                ChildBrowser.this.webview.clearCache(true);
                ChildBrowser.this.webview.clearHistory();
                ChildBrowser.this.webview.loadUrl(paramString);
                ChildBrowser.this.webview.setId(6);
                ChildBrowser.this.webview.getSettings().setLoadWithOverviewMode(true);
                ChildBrowser.this.webview.getSettings().setUseWideViewPort(true);
                ChildBrowser.this.webview.requestFocus();
                ChildBrowser.this.webview.requestFocusFromTouch();
                ((RelativeLayout)localObject2).addView(localImageButton);
                ((RelativeLayout)localObject2).addView((View)localObject3);
                ((RelativeLayout)localObject1).addView((View)localObject2);
                ((RelativeLayout)localObject1).addView(ChildBrowser.this.edittext);
                ((RelativeLayout)localObject1).addView((View)localObject4);
                if (ChildBrowser.this.getShowLocationBar()) {
                  localLinearLayout.addView((View)localObject1);
                }
                localLinearLayout.addView(ChildBrowser.this.webview);
                localObject1 = new Point();
                localObject2 = (WindowManager)ChildBrowser.this.cordova.getActivity().getSystemService("window");
                if (Build.VERSION.SDK_INT < 13) {
                  break;
                }
                ((WindowManager)localObject2).getDefaultDisplay().getSize((Point)localObject1);
                i = ((Point)localObject1).x;
                i = ((Point)localObject1).y;
                System.out.println(">>>>>>>>>>" + i);
                localObject1 = new WindowManager.LayoutParams();
                ((WindowManager.LayoutParams)localObject1).width = -1;
                ((WindowManager.LayoutParams)localObject1).height = (i - 120);
                ((WindowManager.LayoutParams)localObject1).gravity = 48;
                ChildBrowser.this.dialog.setContentView(localLinearLayout);
                ChildBrowser.this.dialog.show();
                ChildBrowser.this.dialog.getWindow().setAttributes((WindowManager.LayoutParams)localObject1);
                return;
                localIOException1 = localIOException1;
                Log.e("ChildBrowser", localIOException1.getMessage(), localIOException1);
              }
              localIOException2 = localIOException2;
              Log.e("ChildBrowser", localIOException2.getMessage(), localIOException2);
            }
            catch (IOException localIOException3)
            {
              for (;;)
              {
                Log.e("ChildBrowser", localIOException3.getMessage(), localIOException3);
                continue;
                localObject1 = ((WindowManager)localObject2).getDefaultDisplay();
                ((Display)localObject1).getWidth();
                int i = ((Display)localObject1).getHeight();
              }
            }
          }
        }
      }
    };
    this.cordova.getActivity().runOnUiThread(paramString);
    return "";
  }
  
  public class ChildBrowserClient
    extends WebViewClient
  {
    TextView edittext;
    
    public ChildBrowserClient(TextView paramTextView)
    {
      this.edittext = paramTextView;
    }
    
    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      super.onPageStarted(paramWebView, paramString, paramBitmap);
      if (paramString.substring(paramString.length() - 3).equals("pdf"))
      {
        if ((paramString.startsWith("http:")) || (paramString.startsWith("https:")) || (paramString.startsWith("file:"))) {}
        for (;;)
        {
          if (!paramString.equals(this.edittext.getText().toString())) {}
          return;
          paramString = "http://" + paramString;
        }
      }
      System.out.println(">>>>>>>>>>>>>>>>>>" + paramString);
      if ((paramString.startsWith("http:")) || (paramString.startsWith("https:")) || (paramString.startsWith("file:"))) {}
      for (paramWebView = paramString;; paramWebView = "http://" + paramString)
      {
        if (!paramWebView.equals(this.edittext.getText().toString())) {}
        try
        {
          paramWebView = new JSONObject();
          paramWebView.put("type", ChildBrowser.LOCATION_CHANGED_EVENT);
          paramWebView.put("location", paramString);
          ChildBrowser.this.sendUpdate(paramWebView, true);
          return;
        }
        catch (JSONException paramWebView)
        {
          Log.d("ChildBrowser", "This should never happen");
          return;
        }
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\phonegap\plugins\childBrowser\ChildBrowser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */