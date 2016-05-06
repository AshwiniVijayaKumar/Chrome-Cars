package com.phonegap.plugin;

import android.app.Activity;
import android.content.Intent;
import com.google.zxing.client.android.CaptureActivity;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BarcodeScanner
  extends CordovaPlugin
{
  private static final String CANCELLED = "cancelled";
  private static final String DATA = "data";
  private static final String EMAIL_TYPE = "EMAIL_TYPE";
  private static final String ENCODE = "encode";
  private static final String ENCODE_DATA = "ENCODE_DATA";
  private static final String ENCODE_INTENT = "com.phonegap.plugins.barcodescanner.ENCODE";
  private static final String ENCODE_TYPE = "ENCODE_TYPE";
  private static final String FORMAT = "format";
  private static final String PHONE_TYPE = "PHONE_TYPE";
  public static final int REQUEST_CODE = 195543262;
  private static final String SCAN = "scan";
  private static final String SCAN_INTENT = "com.phonegap.plugins.barcodescanner.SCAN";
  private static final String SMS_TYPE = "SMS_TYPE";
  private static final String TEXT = "text";
  private static final String TEXT_TYPE = "TEXT_TYPE";
  private static final String TYPE = "type";
  public String callback;
  private CallbackContext cbContext;
  
  public void encode(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent("com.phonegap.plugins.barcodescanner.ENCODE");
    localIntent.putExtra("ENCODE_TYPE", paramString1);
    localIntent.putExtra("ENCODE_DATA", paramString2);
    this.cordova.getActivity().startActivity(localIntent);
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    this.cbContext = paramCallbackContext;
    if (paramString.equals("encode"))
    {
      paramString = paramJSONArray.optJSONObject(0);
      if (paramString != null)
      {
        paramJSONArray = paramString.optString("type");
        String str = paramString.optString("data");
        paramString = paramJSONArray;
        if (paramJSONArray == null) {
          paramString = "TEXT_TYPE";
        }
        if (str == null)
        {
          paramCallbackContext.error("User did not specify data to encode");
          return false;
        }
        encode(paramString, str);
        return false;
      }
      paramCallbackContext.error("User did not specify data to encode");
      return false;
    }
    if (paramString.equals("scan"))
    {
      this.cordova.getActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          BarcodeScanner.this.scan();
        }
      });
      return true;
    }
    paramCallbackContext.error("Invalid Action");
    return false;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    JSONObject localJSONObject;
    if (paramInt1 == 195543262)
    {
      if (paramInt2 != -1) {
        break label67;
      }
      localJSONObject = new JSONObject();
    }
    try
    {
      localJSONObject.put("text", paramIntent.getStringExtra("SCAN_RESULT"));
      localJSONObject.put("format", paramIntent.getStringExtra("SCAN_RESULT_FORMAT"));
      localJSONObject.put("cancelled", false);
      this.cbContext.success(localJSONObject);
      return;
      label67:
      if (paramInt2 == 0) {
        paramIntent = new JSONObject();
      }
      try
      {
        paramIntent.put("text", "CancelledByBackButton");
        paramIntent.put("format", "");
        paramIntent.put("cancelled", true);
        this.cbContext.success(paramIntent);
        return;
        this.cbContext.error("Invalid Activity");
        return;
      }
      catch (JSONException localJSONException)
      {
        for (;;) {}
      }
    }
    catch (JSONException paramIntent)
    {
      for (;;) {}
    }
  }
  
  public void scan()
  {
    Intent localIntent = new Intent(this.cordova.getActivity(), CaptureActivity.class);
    localIntent.addCategory("android.intent.category.DEFAULT");
    this.cordova.startActivityForResult(this, localIntent, 195543262);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\phonegap\plugin\BarcodeScanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */