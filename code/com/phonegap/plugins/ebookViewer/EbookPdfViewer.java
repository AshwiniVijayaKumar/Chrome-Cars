package com.phonegap.plugins.ebookViewer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import com.example.example75f1799f07eb.PdfView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EbookPdfViewer
  extends CordovaPlugin
{
  public static final String MIME_TYPE_PDF = "application/pdf";
  private boolean checkFileLoading = false;
  ProgressDialog pd;
  
  public static boolean canDisplayPdf(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setType("application/pdf");
    return paramContext.queryIntentActivities(localIntent, 65536).size() > 0;
  }
  
  private void doSendIntent(String paramString)
  {
    System.out.println("pdf url----------url=" + paramString);
    if (paramString.contains(".pdf"))
    {
      if (paramString.contains("storage/")) {
        try
        {
          File localFile = new File(paramString);
          Intent localIntent = new Intent();
          localIntent.setDataAndType(Uri.fromFile(localFile), "application/pdf");
          localIntent.setAction("android.intent.action.VIEW");
          localIntent.setFlags(67108864);
          this.cordova.startActivityForResult(this, localIntent, 1);
          return;
        }
        catch (Exception localException)
        {
          paramString = new Intent("android.intent.action.VIEW", Uri.parse("https://docs.google.com/gview?embedded=true&url=" + paramString));
          this.cordova.startActivityForResult(this, paramString, 1);
          return;
        }
      }
      localObject = paramString;
      if (paramString.contains("@@@@@")) {
        localObject = paramString.split("@@@@@")[0];
      }
      if (!((String)localObject).contains("&"))
      {
        paramString = "https://docs.google.com/gview?embedded=true&url=" + (String)localObject;
        localObject = new Intent(this.cordova.getActivity(), PdfView.class);
        ((Intent)localObject).putExtra("pdfurl", paramString);
        this.cordova.startActivityForResult(this, (Intent)localObject, 1);
        return;
      }
      if (canDisplayPdf(this.cordova.getActivity()))
      {
        paramString = ((String)localObject).substring(((String)localObject).lastIndexOf("/") + 1);
        paramString = new File(Environment.getExternalStorageDirectory(), paramString);
        if (paramString.exists())
        {
          paramString = new File(paramString.toString());
          localObject = new Intent();
          ((Intent)localObject).setDataAndType(Uri.fromFile(paramString), "application/pdf");
          ((Intent)localObject).setAction("android.intent.action.VIEW");
          ((Intent)localObject).setFlags(67108864);
          this.cordova.startActivityForResult(this, (Intent)localObject, 1);
          return;
        }
        new DownloadPdfFile().execute(new String[] { localObject, paramString.toString() });
        return;
      }
      paramString = new Intent("android.intent.action.VIEW");
      paramString.setData(Uri.parse("market://details?id=com.adobe.reader"));
      this.cordova.getActivity().startActivity(paramString);
      return;
    }
    Object localObject = new Intent(this.cordova.getActivity(), PdfView.class);
    ((Intent)localObject).putExtra("pdfurl", paramString);
    this.cordova.startActivityForResult(this, (Intent)localObject, 1);
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    boolean bool = false;
    this.pd = new ProgressDialog(this.cordova.getActivity());
    this.pd.setCancelable(false);
    this.pd.setMessage("loading...");
    paramString = PluginResult.Status.OK;
    try
    {
      System.out.println("loading EbookPdfViewer plugins");
      doSendIntent(paramJSONArray.getJSONObject(0).getString("url"));
      paramString = PluginResult.Status.OK;
      if (paramString == PluginResult.Status.OK) {
        bool = true;
      }
      return bool;
    }
    catch (JSONException paramString)
    {
      for (;;)
      {
        paramString = PluginResult.Status.JSON_EXCEPTION;
      }
    }
  }
  
  public class DownloadPdfFile
    extends AsyncTask<String, Void, Boolean>
  {
    String filePath = "";
    
    public DownloadPdfFile() {}
    
    protected Boolean doInBackground(String... paramVarArgs)
    {
      FileOutputStream localFileOutputStream;
      for (;;)
      {
        try
        {
          this.filePath = paramVarArgs[1];
          localFileOutputStream = new FileOutputStream(paramVarArgs[1]);
          paramVarArgs = (HttpURLConnection)new URL(paramVarArgs[0]).openConnection();
          paramVarArgs.setRequestMethod("GET");
          paramVarArgs.setDoOutput(false);
          paramVarArgs.connect();
          if (paramVarArgs.getResponseCode() >= 400)
          {
            paramVarArgs = paramVarArgs.getErrorStream();
            byte[] arrayOfByte = new byte['Ѐ'];
            int i = paramVarArgs.read(arrayOfByte);
            if (i <= 0) {
              break;
            }
            localFileOutputStream.write(arrayOfByte, 0, i);
            continue;
          }
          paramVarArgs = paramVarArgs.getInputStream();
        }
        catch (Exception paramVarArgs)
        {
          EbookPdfViewer.this.pd.dismiss();
          paramVarArgs.printStackTrace();
          return Boolean.valueOf(false);
        }
      }
      localFileOutputStream.close();
      return Boolean.valueOf(true);
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      EbookPdfViewer.access$002(EbookPdfViewer.this, true);
      EbookPdfViewer.this.pd.dismiss();
      if (paramBoolean.booleanValue())
      {
        File localFile = new File(this.filePath);
        Intent localIntent = new Intent();
        localIntent.setDataAndType(Uri.fromFile(localFile), "application/pdf");
        localIntent.setAction("android.intent.action.VIEW");
        localIntent.setFlags(67108864);
        EbookPdfViewer.this.cordova.startActivityForResult(EbookPdfViewer.this, localIntent, 1);
      }
      super.onPostExecute(paramBoolean);
    }
    
    protected void onPreExecute()
    {
      EbookPdfViewer.access$002(EbookPdfViewer.this, true);
      EbookPdfViewer.this.pd.show();
      super.onPreExecute();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\phonegap\plugins\ebookViewer\EbookPdfViewer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */