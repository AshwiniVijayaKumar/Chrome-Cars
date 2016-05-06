package com.example.example75f1799f07eb;

import android.content.Context;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.google.android.gcm.GCMRegistrar;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

public final class ServerUtilities
{
  private static final int BACKOFF_MILLI_SECONDS = 2000;
  private static final int MAX_ATTEMPTS = 5;
  private static final String METHOD_NAME = "serviceJobDsipatchWebService";
  private static final String NAMESPACE = "http://apps.appypie.com/services/soap/";
  private static final String SOAP_ACTION = "http://schemas.xmlsoap.org/wsdl/";
  private static final String URL = "http://apps.appypie.com/services/soap/";
  private static final Random random = new Random();
  
  private static void post(String paramString, Map<String, String> paramMap)
    throws IOException
  {
    Object localObject1;
    try
    {
      localObject1 = new URL(paramString);
      paramString = new StringBuilder();
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        localObject2 = (Map.Entry)paramMap.next();
        paramString.append((String)((Map.Entry)localObject2).getKey()).append('=').append((String)((Map.Entry)localObject2).getValue());
        if (paramMap.hasNext()) {
          paramString.append('&');
        }
      }
      paramString = paramString.toString();
    }
    catch (MalformedURLException paramMap)
    {
      throw new IllegalArgumentException("invalid url: " + paramString);
    }
    Log.v("AndroidHive GCM", "Posting '" + paramString + "' to " + localObject1);
    Object localObject2 = paramString.getBytes();
    paramMap = null;
    paramString = paramMap;
    try
    {
      Log.e("URL", "> " + localObject1);
      paramString = paramMap;
      paramMap = (HttpURLConnection)((URL)localObject1).openConnection();
      paramString = paramMap;
      paramMap.setDoOutput(true);
      paramString = paramMap;
      paramMap.setUseCaches(false);
      paramString = paramMap;
      paramMap.setFixedLengthStreamingMode(localObject2.length);
      paramString = paramMap;
      paramMap.setRequestMethod("POST");
      paramString = paramMap;
      paramMap.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
      paramString = paramMap;
      localObject1 = paramMap.getOutputStream();
      paramString = paramMap;
      ((OutputStream)localObject1).write((byte[])localObject2);
      paramString = paramMap;
      ((OutputStream)localObject1).close();
      paramString = paramMap;
      int i = paramMap.getResponseCode();
      if (i != 200)
      {
        paramString = paramMap;
        throw new IOException("Post failed with error code " + i);
      }
    }
    finally
    {
      if (paramString != null) {
        paramString.disconnect();
      }
    }
    if (paramMap != null) {
      paramMap.disconnect();
    }
  }
  
  static void register(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Log.i("AndroidHive GCM", "registering device (regId = " + paramString2 + ")");
    Log.i("AndroidHive GCM", "registering device (lat = " + paramString3 + ")");
    Log.i("AndroidHive GCM", "registering device (lng = " + paramString4 + ")");
    Object localObject4 = "";
    Object localObject1 = "";
    try
    {
      localObject2 = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkCountryIso();
      localObject1 = localObject2;
      str = new Locale("", (String)localObject2).getDisplayCountry();
      localObject4 = str;
      localObject1 = localObject2;
      localObject2 = localObject4;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        try
        {
          Object localObject2;
          String str;
          paramContext = new SoapObject(MyApplicationName.SOAP_NAMESPACE, "addDeviceToken");
          localObject4 = new AndroidHttpTransport(MyApplicationName.SOAP_URL);
          paramContext.addProperty("deviceToken", paramString2);
          paramContext.addProperty("appId", paramString1);
          paramContext.addProperty("deviceType", "Android");
          paramContext.addProperty("deviceId", str);
          paramContext.addProperty("latitude", paramString3);
          paramContext.addProperty("longitude", paramString4);
          paramContext.addProperty("country", localObject2);
          paramContext.addProperty("countryCode", localObject1);
          paramString1 = new SoapSerializationEnvelope(110);
          paramString1.setOutputSoapObject(paramContext);
          ((AndroidHttpTransport)localObject4).call(MyApplicationName.SOAP_ACTION, paramString1);
          paramContext = (SoapObject)paramString1.bodyIn;
          System.out.println("SOAP Result=" + paramContext.toString());
          return;
        }
        catch (Exception paramContext)
        {
          Object localObject3;
          System.out.println("Exception SOAP-:" + paramContext.getMessage());
        }
        localException = localException;
        localException.printStackTrace();
        localObject3 = localObject4;
      }
    }
    paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    str = paramContext + paramString1;
  }
  
  static void unregister(Context paramContext, String paramString)
  {
    Log.i("AndroidHive GCM", "unregistering device (regId = " + paramString + ")");
    HashMap localHashMap = new HashMap();
    localHashMap.put("regId", paramString);
    try
    {
      post("http://abhinav.pbodev.info/android/register.php/unregister", localHashMap);
      GCMRegistrar.setRegisteredOnServer(paramContext, false);
      CommonUtilities.displayMessage(paramContext, paramContext.getString(2131231069));
      return;
    }
    catch (IOException paramString)
    {
      CommonUtilities.displayMessage(paramContext, paramContext.getString(2131231071, new Object[] { paramString.getMessage() }));
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\ServerUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */