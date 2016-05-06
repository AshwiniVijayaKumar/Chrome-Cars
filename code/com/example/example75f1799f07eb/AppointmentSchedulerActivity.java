package com.example.example75f1799f07eb;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.provider.Settings.Secure;
import java.io.PrintStream;
import org.apache.cordova.CordovaActivity;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

@SuppressLint({"NewApi"})
@TargetApi(9)
public class AppointmentSchedulerActivity
  extends CordovaActivity
{
  static String AppId;
  private static final String METHOD_NAME = "sendAppointment";
  private static final String NAMESPACE = "http://apps.appypie.com/services/soap/";
  private static final String SOAP_ACTION = "http://schemas.xmlsoap.org/wsdl/";
  private static final String URL = "http://apps.appypie.com/services/soap/";
  String AppVersion;
  String addId;
  AlertDialogManager alert = new AlertDialogManager();
  String appName = null;
  String uAId;
  
  static
  {
    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
  }
  
  public void appointmentServiceCall(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9)
  {
    System.out.println("appointmentServiceCall ");
    try
    {
      String str = Settings.Secure.getString(getContentResolver(), "android_id");
      paramString8 = new SoapObject(MyApplicationName.SOAP_NAMESPACE, "sendAppointment");
      paramString9 = new AndroidHttpTransport(MyApplicationName.SOAP_URL);
      paramString8.addProperty("appId", paramString1);
      paramString8.addProperty("appointmentDate", paramString2);
      paramString8.addProperty("appointmentTime", paramString3);
      paramString8.addProperty("userName", paramString4);
      paramString8.addProperty("userEmail", paramString5);
      paramString8.addProperty("userPhone", paramString6);
      paramString8.addProperty("userRemark", paramString7);
      paramString8.addProperty("status", "1");
      paramString8.addProperty("userDeviceType", "Android");
      paramString8.addProperty("userDeviceId", str);
      paramString8.addProperty("userDeviceToken", MyApplicationName.GCM_ID);
      paramString1 = new SoapSerializationEnvelope(110);
      paramString1.setOutputSoapObject(paramString8);
      paramString9.call("http://schemas.xmlsoap.org/wsdl/", paramString1);
      paramString2 = (KvmSerializable)paramString1.bodyIn;
      paramString1 = (SoapObject)paramString1.bodyIn;
      if (paramString2.getProperty(0).toString().equals("success"))
      {
        showAlertDialog(this, "Success", "Your request sent successfully.", 1);
        return;
      }
      showAlertDialog(this, "Error", "Server Error Please try again Later", 0);
      return;
    }
    catch (Exception paramString1)
    {
      System.out.println(paramString1.toString() + "Exception ocurs plz try again-:");
      showAlertDialog(this, "Server Error", "Problem with connecting server, please try again", 2);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent().getExtras();
    appointmentServiceCall(paramBundle.getString("APP_ID"), paramBundle.getString("APOINT_DATE"), paramBundle.getString("APOINT_TIME"), paramBundle.getString("APOINT_NAME"), paramBundle.getString("APOINT_EMAIL"), paramBundle.getString("APOINT_PHONE"), paramBundle.getString("APOINT_REMARK"), paramBundle.getString("STATUS"), paramBundle.getString("DEVICE_TYPE"));
  }
  
  public void showAlertDialog(Context paramContext, String paramString1, String paramString2, int paramInt)
  {
    paramContext = new AlertDialog.Builder(paramContext).create();
    paramContext.setTitle(paramString1);
    paramContext.setMessage(paramString2);
    if (paramInt > 0) {
      if (paramInt == 1) {
        paramInt = 2130837711;
      }
    }
    for (;;)
    {
      paramContext.setIcon(paramInt);
      paramContext.setButton("OK", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          AppointmentSchedulerActivity.this.finish();
        }
      });
      paramContext.show();
      return;
      paramInt = 2130837661;
      continue;
      paramInt = 2130837584;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\AppointmentSchedulerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */