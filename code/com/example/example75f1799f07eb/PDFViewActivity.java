package com.example.example75f1799f07eb;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.PDFView.Configurator;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PDFViewActivity
  extends Activity
  implements OnPageChangeListener
{
  public static final String ABOUT_FILE = "about.pdf";
  public static final String CHECK_FILE = "check.pdf";
  public static final String NEW_FILE = "ebook_1437665303_4211.pdf";
  public static final String SAMPLE_FILE = "sample.pdf";
  private static String url = "";
  String MyPREFERENCES = "MyPrefs";
  AlertDialogManager alert = new AlertDialogManager();
  private boolean checkFileLoading = false;
  private ImageView imgview_back;
  private ImageView imgview_share;
  Integer pageNumber = Integer.valueOf(1);
  ProgressDialog pd;
  String pdfName = "sample.pdf";
  PDFView pdfView;
  SharedPreferences sharedpreferences;
  private String tempUrl = "";
  private TextView txtview_number;
  private String typeLanguage;
  
  private boolean displaying(String paramString)
  {
    return paramString.equals(this.pdfName);
  }
  
  public void about()
  {
    if (!displaying("about.pdf")) {
      display("about.pdf", true);
    }
  }
  
  void afterViews()
  {
    display(this.pdfName, false);
  }
  
  public void display(String paramString, boolean paramBoolean)
  {
    if (paramBoolean) {
      this.pageNumber = Integer.valueOf(1);
    }
    this.pdfName = paramString;
    setTitle(paramString);
    this.pdfView.fromFile(new File(url)).defaultPage(this.pageNumber.intValue()).onPageChange(this).load();
  }
  
  public void onBackPressed()
  {
    if (!this.checkFileLoading)
    {
      if ("about.pdf".equals(this.pdfName))
      {
        display("sample.pdf", true);
        return;
      }
      super.onBackPressed();
      return;
    }
    super.onBackPressed();
  }
  
  /* Error */
  protected void onCreate(android.os.Bundle paramBundle)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial 142	android/app/Activity:onCreate	(Landroid/os/Bundle;)V
    //   5: aload_0
    //   6: invokevirtual 146	com/example/example75f1799f07eb/PDFViewActivity:getWindow	()Landroid/view/Window;
    //   9: bipush 8
    //   11: invokevirtual 152	android/view/Window:requestFeature	(I)Z
    //   14: pop
    //   15: aload_0
    //   16: invokevirtual 156	com/example/example75f1799f07eb/PDFViewActivity:getActionBar	()Landroid/app/ActionBar;
    //   19: invokevirtual 161	android/app/ActionBar:hide	()V
    //   22: aload_0
    //   23: aload_0
    //   24: aload_0
    //   25: getfield 66	com/example/example75f1799f07eb/PDFViewActivity:MyPREFERENCES	Ljava/lang/String;
    //   28: iconst_0
    //   29: invokevirtual 165	com/example/example75f1799f07eb/PDFViewActivity:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   32: putfield 167	com/example/example75f1799f07eb/PDFViewActivity:sharedpreferences	Landroid/content/SharedPreferences;
    //   35: aload_0
    //   36: aload_0
    //   37: getfield 167	com/example/example75f1799f07eb/PDFViewActivity:sharedpreferences	Landroid/content/SharedPreferences;
    //   40: ldc -87
    //   42: ldc 52
    //   44: invokeinterface 175 3 0
    //   49: putfield 177	com/example/example75f1799f07eb/PDFViewActivity:typeLanguage	Ljava/lang/String;
    //   52: getstatic 183	java/lang/System:out	Ljava/io/PrintStream;
    //   55: new 185	java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial 186	java/lang/StringBuilder:<init>	()V
    //   62: ldc -68
    //   64: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: aload_0
    //   68: getfield 177	com/example/example75f1799f07eb/PDFViewActivity:typeLanguage	Ljava/lang/String;
    //   71: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   77: invokevirtual 201	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   80: aload_0
    //   81: getfield 177	com/example/example75f1799f07eb/PDFViewActivity:typeLanguage	Ljava/lang/String;
    //   84: ldc -53
    //   86: invokevirtual 206	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   89: ifeq +217 -> 306
    //   92: aload_0
    //   93: ldc -49
    //   95: invokevirtual 211	com/example/example75f1799f07eb/PDFViewActivity:setContentView	(I)V
    //   98: aload_0
    //   99: new 213	android/app/ProgressDialog
    //   102: dup
    //   103: aload_0
    //   104: invokespecial 216	android/app/ProgressDialog:<init>	(Landroid/content/Context;)V
    //   107: putfield 218	com/example/example75f1799f07eb/PDFViewActivity:pd	Landroid/app/ProgressDialog;
    //   110: aload_0
    //   111: getfield 218	com/example/example75f1799f07eb/PDFViewActivity:pd	Landroid/app/ProgressDialog;
    //   114: iconst_0
    //   115: invokevirtual 222	android/app/ProgressDialog:setCancelable	(Z)V
    //   118: aload_0
    //   119: getfield 218	com/example/example75f1799f07eb/PDFViewActivity:pd	Landroid/app/ProgressDialog;
    //   122: ldc -32
    //   124: invokevirtual 227	android/app/ProgressDialog:setMessage	(Ljava/lang/CharSequence;)V
    //   127: aload_0
    //   128: aload_0
    //   129: ldc -28
    //   131: invokevirtual 232	com/example/example75f1799f07eb/PDFViewActivity:findViewById	(I)Landroid/view/View;
    //   134: checkcast 234	android/widget/ImageView
    //   137: putfield 236	com/example/example75f1799f07eb/PDFViewActivity:imgview_back	Landroid/widget/ImageView;
    //   140: aload_0
    //   141: aload_0
    //   142: ldc -19
    //   144: invokevirtual 232	com/example/example75f1799f07eb/PDFViewActivity:findViewById	(I)Landroid/view/View;
    //   147: checkcast 234	android/widget/ImageView
    //   150: putfield 239	com/example/example75f1799f07eb/PDFViewActivity:imgview_share	Landroid/widget/ImageView;
    //   153: aload_0
    //   154: aload_0
    //   155: ldc -16
    //   157: invokevirtual 232	com/example/example75f1799f07eb/PDFViewActivity:findViewById	(I)Landroid/view/View;
    //   160: checkcast 242	android/widget/TextView
    //   163: putfield 244	com/example/example75f1799f07eb/PDFViewActivity:txtview_number	Landroid/widget/TextView;
    //   166: aload_0
    //   167: getfield 236	com/example/example75f1799f07eb/PDFViewActivity:imgview_back	Landroid/widget/ImageView;
    //   170: new 8	com/example/example75f1799f07eb/PDFViewActivity$1
    //   173: dup
    //   174: aload_0
    //   175: invokespecial 247	com/example/example75f1799f07eb/PDFViewActivity$1:<init>	(Lcom/example/example75f1799f07eb/PDFViewActivity;)V
    //   178: invokevirtual 251	android/widget/ImageView:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   181: aload_0
    //   182: getfield 239	com/example/example75f1799f07eb/PDFViewActivity:imgview_share	Landroid/widget/ImageView;
    //   185: iconst_4
    //   186: invokevirtual 254	android/widget/ImageView:setVisibility	(I)V
    //   189: aload_0
    //   190: getfield 239	com/example/example75f1799f07eb/PDFViewActivity:imgview_share	Landroid/widget/ImageView;
    //   193: new 10	com/example/example75f1799f07eb/PDFViewActivity$2
    //   196: dup
    //   197: aload_0
    //   198: invokespecial 255	com/example/example75f1799f07eb/PDFViewActivity$2:<init>	(Lcom/example/example75f1799f07eb/PDFViewActivity;)V
    //   201: invokevirtual 251	android/widget/ImageView:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   204: aload_0
    //   205: invokevirtual 259	com/example/example75f1799f07eb/PDFViewActivity:getIntent	()Landroid/content/Intent;
    //   208: invokevirtual 265	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   211: ldc_w 267
    //   214: invokevirtual 272	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   217: putstatic 54	com/example/example75f1799f07eb/PDFViewActivity:url	Ljava/lang/String;
    //   220: aload_0
    //   221: aload_0
    //   222: ldc_w 273
    //   225: invokevirtual 232	com/example/example75f1799f07eb/PDFViewActivity:findViewById	(I)Landroid/view/View;
    //   228: checkcast 112	com/joanzapata/pdfview/PDFView
    //   231: putfield 105	com/example/example75f1799f07eb/PDFViewActivity:pdfView	Lcom/joanzapata/pdfview/PDFView;
    //   234: getstatic 54	com/example/example75f1799f07eb/PDFViewActivity:url	Ljava/lang/String;
    //   237: ldc_w 275
    //   240: invokevirtual 279	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   243: ifeq +133 -> 376
    //   246: aload_0
    //   247: getstatic 54	com/example/example75f1799f07eb/PDFViewActivity:url	Ljava/lang/String;
    //   250: putfield 60	com/example/example75f1799f07eb/PDFViewActivity:tempUrl	Ljava/lang/String;
    //   253: getstatic 54	com/example/example75f1799f07eb/PDFViewActivity:url	Ljava/lang/String;
    //   256: getstatic 54	com/example/example75f1799f07eb/PDFViewActivity:url	Ljava/lang/String;
    //   259: ldc_w 281
    //   262: invokevirtual 285	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   265: iconst_1
    //   266: iadd
    //   267: invokevirtual 289	java/lang/String:substring	(I)Ljava/lang/String;
    //   270: astore_1
    //   271: new 107	java/io/File
    //   274: dup
    //   275: invokestatic 295	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   278: aload_1
    //   279: invokespecial 298	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   282: astore_1
    //   283: aload_1
    //   284: invokevirtual 299	java/io/File:toString	()Ljava/lang/String;
    //   287: putstatic 54	com/example/example75f1799f07eb/PDFViewActivity:url	Ljava/lang/String;
    //   290: aload_1
    //   291: invokevirtual 303	java/io/File:exists	()Z
    //   294: ifeq +40 -> 334
    //   297: aload_0
    //   298: ldc_w 305
    //   301: iconst_1
    //   302: invokevirtual 98	com/example/example75f1799f07eb/PDFViewActivity:display	(Ljava/lang/String;Z)V
    //   305: return
    //   306: aload_0
    //   307: ldc_w 306
    //   310: invokevirtual 211	com/example/example75f1799f07eb/PDFViewActivity:setContentView	(I)V
    //   313: goto -215 -> 98
    //   316: astore_1
    //   317: aload_0
    //   318: getfield 81	com/example/example75f1799f07eb/PDFViewActivity:alert	Lcom/example/example75f1799f07eb/AlertDialogManager;
    //   321: aload_0
    //   322: getstatic 311	com/example/example75f1799f07eb/MyApplicationName:APP_NAME	Ljava/lang/String;
    //   325: ldc_w 313
    //   328: bipush 22
    //   330: invokevirtual 317	com/example/example75f1799f07eb/AlertDialogManager:showAlertDialog	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;I)V
    //   333: return
    //   334: aload_1
    //   335: invokevirtual 320	java/io/File:createNewFile	()Z
    //   338: pop
    //   339: new 12	com/example/example75f1799f07eb/PDFViewActivity$DownloadPdfFile
    //   342: dup
    //   343: aload_0
    //   344: invokespecial 321	com/example/example75f1799f07eb/PDFViewActivity$DownloadPdfFile:<init>	(Lcom/example/example75f1799f07eb/PDFViewActivity;)V
    //   347: iconst_2
    //   348: anewarray 87	java/lang/String
    //   351: dup
    //   352: iconst_0
    //   353: aload_0
    //   354: getfield 60	com/example/example75f1799f07eb/PDFViewActivity:tempUrl	Ljava/lang/String;
    //   357: aastore
    //   358: dup
    //   359: iconst_1
    //   360: aload_1
    //   361: invokevirtual 299	java/io/File:toString	()Ljava/lang/String;
    //   364: aastore
    //   365: invokevirtual 325	com/example/example75f1799f07eb/PDFViewActivity$DownloadPdfFile:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   368: pop
    //   369: return
    //   370: astore_1
    //   371: aload_1
    //   372: invokevirtual 328	java/lang/Exception:printStackTrace	()V
    //   375: return
    //   376: aload_0
    //   377: ldc_w 330
    //   380: iconst_1
    //   381: invokevirtual 98	com/example/example75f1799f07eb/PDFViewActivity:display	(Ljava/lang/String;Z)V
    //   384: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	385	0	this	PDFViewActivity
    //   0	385	1	paramBundle	android.os.Bundle
    // Exception table:
    //   from	to	target	type
    //   5	98	316	java/lang/Exception
    //   98	204	316	java/lang/Exception
    //   306	313	316	java/lang/Exception
    //   371	375	316	java/lang/Exception
    //   204	305	370	java/lang/Exception
    //   334	369	370	java/lang/Exception
    //   376	384	370	java/lang/Exception
  }
  
  public void onPageChanged(int paramInt1, int paramInt2)
  {
    this.pageNumber = Integer.valueOf(paramInt1);
    setTitle(String.format("%s %s / %s", new Object[] { this.pdfName, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) }));
    this.txtview_number.setText(String.format("%s %s / %s", new Object[] { this.pdfName, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) }));
    System.out.println("dheeraj page" + paramInt1);
    System.out.println("dheeraj pageCount" + paramInt2);
    System.out.println("dheeraj pdfName" + this.pdfName);
  }
  
  public class DownloadPdfFile
    extends AsyncTask<String, Void, Boolean>
  {
    public DownloadPdfFile() {}
    
    protected Boolean doInBackground(String... paramVarArgs)
    {
      FileOutputStream localFileOutputStream;
      for (;;)
      {
        try
        {
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
          PDFViewActivity.this.pd.dismiss();
          paramVarArgs.printStackTrace();
          return Boolean.valueOf(false);
        }
      }
      localFileOutputStream.close();
      return Boolean.valueOf(true);
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      PDFViewActivity.access$002(PDFViewActivity.this, true);
      PDFViewActivity.this.pd.dismiss();
      if (paramBoolean.booleanValue()) {
        PDFViewActivity.this.display("Pdf Reader", true);
      }
      super.onPostExecute(paramBoolean);
    }
    
    protected void onPreExecute()
    {
      PDFViewActivity.access$002(PDFViewActivity.this, true);
      PDFViewActivity.this.pd.show();
      super.onPreExecute();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\PDFViewActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */