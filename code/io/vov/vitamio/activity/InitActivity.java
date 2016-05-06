package io.vov.vitamio.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import java.lang.ref.WeakReference;

public class InitActivity
  extends Activity
{
  public static final String FROM_ME = "fromVitamioInitActivity";
  private ProgressDialog mPD;
  private UIHandler uiHandler;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getWindow().addFlags(128);
    this.uiHandler = new UIHandler(this);
    new AsyncTask()
    {
      protected Boolean doInBackground(Object... paramAnonymousVarArgs)
      {
        return null;
      }
      
      protected void onPostExecute(Boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean.booleanValue()) {
          InitActivity.this.uiHandler.sendEmptyMessage(0);
        }
      }
      
      protected void onPreExecute()
      {
        InitActivity.this.mPD = new ProgressDialog(InitActivity.this);
        InitActivity.this.mPD.setCancelable(false);
        InitActivity.this.mPD.setMessage(InitActivity.this.getString(InitActivity.this.getResources().getIdentifier("vitamio_init_decoders", "string", InitActivity.this.getPackageName())));
        InitActivity.this.mPD.show();
      }
    }.execute(new Object[0]);
  }
  
  private static class UIHandler
    extends Handler
  {
    private WeakReference<Context> mContext;
    
    public UIHandler(Context paramContext)
    {
      this.mContext = new WeakReference(paramContext);
    }
    
    public void handleMessage(Message paramMessage)
    {
      InitActivity localInitActivity = (InitActivity)this.mContext.get();
      switch (paramMessage.what)
      {
      default: 
        return;
      }
      localInitActivity.mPD.dismiss();
      paramMessage = localInitActivity.getIntent();
      Intent localIntent = new Intent();
      localIntent.setClassName(paramMessage.getStringExtra("package"), paramMessage.getStringExtra("className"));
      localIntent.setData(paramMessage.getData());
      localIntent.putExtras(paramMessage);
      localIntent.putExtra("fromVitamioInitActivity", true);
      localInitActivity.startActivity(localIntent);
      localInitActivity.finish();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\io\vov\vitamio\activity\InitActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */