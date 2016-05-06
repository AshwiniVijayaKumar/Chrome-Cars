package com.example.example75f1799f07eb;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

public class Profile
  extends Activity
{
  private static final String SOAP_ACTION = "http://schemas.xmlsoap.org/wsdl/";
  String Device_Oriantation = "";
  String HeaderBarbackgroundColor;
  String HeaderbarTextColor;
  String ImgURl_Land;
  String ImgURl_Port;
  private String NAMESPACE = "";
  int Oriantation_ID;
  File SDCardRoot;
  private String URL = "";
  TextView appName;
  ScrollView background;
  private Button btnProfile;
  private Button btnUpdatePass;
  private EditText etEmail;
  private EditText etName;
  private EditText etPassword;
  private EditText etPhone;
  private EditText etRePassword;
  File file;
  String foldername = "";
  String navigationBarType;
  private String password;
  private String repassword;
  RelativeLayout rlcustomeheader;
  SharedPreferences sharedpreferences;
  
  private void Check_Device_Oriantation()
  {
    this.Oriantation_ID = getResources().getConfiguration().orientation;
    switch (this.Oriantation_ID)
    {
    default: 
      this.Device_Oriantation = "";
      Setheaderimage();
      return;
    case 2: 
      this.Device_Oriantation = "LANDSCAPE";
      Setheaderimage();
      return;
    case 1: 
      this.Device_Oriantation = "PORTRAIT";
      Setheaderimage();
      return;
    }
    this.Device_Oriantation = "UNDEFINED";
    Setheaderimage();
  }
  
  private void Setbackgroundimage()
  {
    Object localObject = getApplicationName();
    if (this.sharedpreferences.getString("appbackgroundType", "").equals("custom_color"))
    {
      localObject = this.sharedpreferences.getString("appBarbackgroundColor", "");
      this.background.setBackgroundColor(Color.parseColor((String)localObject));
    }
    do
    {
      do
      {
        return;
        this.SDCardRoot = new File(Environment.getExternalStorageDirectory(), this.foldername + (String)localObject);
        if (!this.Device_Oriantation.equals("PORTRAIT")) {
          break;
        }
        this.file = new File(this.SDCardRoot, "appbg_port_img.jpg");
      } while (!this.file.exists());
      localObject = BitmapFactory.decodeFile(this.file.getAbsolutePath());
      localObject = new BitmapDrawable(getResources(), (Bitmap)localObject);
      this.background.setBackground((Drawable)localObject);
      return;
      this.file = new File(this.SDCardRoot, "appbg_land_img.jpg");
    } while (!this.file.exists());
    localObject = BitmapFactory.decodeFile(this.file.getAbsolutePath());
    localObject = new BitmapDrawable(getResources(), (Bitmap)localObject);
    this.background.setBackground((Drawable)localObject);
  }
  
  private void Setheaderimage()
  {
    Object localObject = getApplicationName();
    this.navigationBarType = this.sharedpreferences.getString("navigationBarType", "");
    this.HeaderBarbackgroundColor = this.sharedpreferences.getString("HeaderBarbackgroundColor", "");
    this.HeaderbarTextColor = this.sharedpreferences.getString("HeaderbarTextColor", "");
    this.ImgURl_Port = this.sharedpreferences.getString("ImgURl_Port", "");
    this.ImgURl_Land = this.sharedpreferences.getString("ImgURl_Land", "");
    System.out.println("navigationBarType " + this.navigationBarType + " , HeaderBarbackgroundColor " + this.HeaderBarbackgroundColor + " , HeaderbarTextColor " + this.HeaderbarTextColor);
    if (this.navigationBarType.equals("image"))
    {
      this.SDCardRoot = new File(Environment.getExternalStorageDirectory(), this.foldername + (String)localObject);
      if (this.Device_Oriantation.equals("PORTRAIT"))
      {
        this.file = new File(this.SDCardRoot, "header_port_img.jpg");
        if (this.file.exists())
        {
          localObject = BitmapFactory.decodeFile(this.file.getAbsolutePath());
          localObject = new BitmapDrawable(getResources(), (Bitmap)localObject);
          this.rlcustomeheader.setBackground((Drawable)localObject);
          this.appName.setText("");
        }
      }
      do
      {
        return;
        this.file = new File(this.SDCardRoot, "header_land_img.jpg");
      } while (!this.file.exists());
      localObject = BitmapFactory.decodeFile(this.file.getAbsolutePath());
      localObject = new BitmapDrawable(getResources(), (Bitmap)localObject);
      this.rlcustomeheader.setBackground((Drawable)localObject);
      this.appName.setText("");
      return;
    }
    System.out.println("Headerbar color code : HeaderBarbackgroundColor  " + this.HeaderBarbackgroundColor + "  ,  HeaderbarTextColor  " + this.HeaderbarTextColor);
    this.rlcustomeheader.setBackgroundColor(Color.parseColor(this.HeaderBarbackgroundColor));
    this.appName.setTextColor(Color.parseColor(this.HeaderbarTextColor));
  }
  
  private boolean isValidEmail(String paramString)
  {
    return Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(paramString).matches();
  }
  
  public void close(View paramView)
  {
    finish();
  }
  
  public String getApplicationName()
  {
    return getString(getApplicationInfo().labelRes);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (paramConfiguration.orientation == 2)
    {
      this.Device_Oriantation = "LANDSCAPE";
      Setheaderimage();
    }
    while (paramConfiguration.orientation != 1) {
      return;
    }
    this.Device_Oriantation = "PORTRAIT";
    Setheaderimage();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(7);
    setContentView(2130903128);
    this.background = ((ScrollView)findViewById(2131558699));
    getWindow().setFeatureInt(7, 2130903064);
    paramBundle = getResources().getString(2131230720);
    this.appName = ((TextView)findViewById(2131558451));
    this.appName.setText(paramBundle);
    this.sharedpreferences = getSharedPreferences("MyPrefs", 0);
    paramBundle = (ImageButton)findViewById(2131558452);
    this.rlcustomeheader = ((RelativeLayout)findViewById(2131558450));
    paramBundle.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Profile.this.finish();
      }
    });
    this.etName = ((EditText)findViewById(2131558454));
    this.etPhone = ((EditText)findViewById(2131558703));
    this.etEmail = ((EditText)findViewById(2131558702));
    if (!getIntent().getStringExtra("profileNameStr").equalsIgnoreCase("null")) {
      this.etName.setText("" + getIntent().getStringExtra("profileNameStr"));
    }
    if (!getIntent().getStringExtra("profileNumber").equalsIgnoreCase("null")) {
      this.etPhone.setText("" + getIntent().getStringExtra("profileNumber"));
    }
    if (!getIntent().getStringExtra("profileEmail").equalsIgnoreCase("null")) {
      this.etEmail.setText("" + getIntent().getStringExtra("profileEmail"));
    }
    this.etPassword = ((EditText)findViewById(2131558706));
    this.etRePassword = ((EditText)findViewById(2131558707));
    this.btnProfile = ((Button)findViewById(2131558704));
    this.btnUpdatePass = ((Button)findViewById(2131558708));
    paramBundle = this.sharedpreferences.getString("headerBarTitle", "");
    if ((paramBundle != null) && (!paramBundle.equals(""))) {
      this.appName.setText(paramBundle);
    }
    Object localObject = this.sharedpreferences.getString("headerBarFont", "georgia");
    try
    {
      String str = this.sharedpreferences.getString("textColor", "");
      paramBundle = (TextView)findViewById(2131558700);
      TextView localTextView = (TextView)findViewById(2131558705);
      paramBundle.setTextColor(Color.parseColor(str));
      localTextView.setTextColor(Color.parseColor(str));
      this.appName.setTextColor(Color.parseColor(str));
      this.btnProfile.setTextColor(Color.parseColor(str));
      this.btnUpdatePass.setTextColor(Color.parseColor(str));
      str = this.sharedpreferences.getString("primaryButtonBgColor", "#000");
      this.btnProfile.setBackgroundColor(Color.parseColor(str));
      this.btnUpdatePass.setBackgroundColor(Color.parseColor(str));
      localObject = Typeface.createFromAsset(getAssets(), "www/fonts/" + (String)localObject + ".ttf");
      paramBundle.setTypeface((Typeface)localObject);
      localTextView.setTypeface((Typeface)localObject);
      this.appName.setTypeface((Typeface)localObject);
      this.btnProfile.setTypeface((Typeface)localObject);
      this.btnUpdatePass.setTypeface((Typeface)localObject);
      this.btnProfile.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = Profile.this.etName.getText().toString();
          String str1 = Profile.this.etPhone.getText().toString();
          String str2 = Profile.this.etEmail.getText().toString();
          if ((paramAnonymousView.equals("")) || (str1.equals("")) || (str2.equals("")))
          {
            Toast.makeText(Profile.this.getApplicationContext(), "All fields are mandatory.", 1).show();
            return;
          }
          if (!Profile.this.isValidEmail(str2))
          {
            Profile.this.etEmail.setError("Invalid Email!");
            return;
          }
          Toast.makeText(Profile.this, "Profile updated.", 1).show();
          MyPhoneGapActivity.updateUserProfile(paramAnonymousView, str1);
        }
      });
      this.btnUpdatePass.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Profile.access$402(Profile.this, Profile.this.etPassword.getText().toString());
          Profile.access$602(Profile.this, Profile.this.etRePassword.getText().toString());
          if ((Profile.this.password.equals("")) && (Profile.this.repassword.equals("")))
          {
            Toast.makeText(Profile.this.getApplicationContext(), "Password must be filled first!", 1).show();
            return;
          }
          if (Profile.this.password.equals(Profile.this.repassword))
          {
            new Profile.PassChange(Profile.this, null).execute(new String[] { Profile.this.password });
            return;
          }
          Toast.makeText(Profile.this.getApplicationContext(), "Password not matched!", 1).show();
        }
      });
      Check_Device_Oriantation();
      Setheaderimage();
      Setbackgroundimage();
      return;
    }
    catch (Exception paramBundle)
    {
      for (;;)
      {
        System.out.println("............." + paramBundle.getMessage());
      }
    }
  }
  
  private class PassChange
    extends AsyncTask<String, Void, String>
  {
    private PassChange() {}
    
    protected String doInBackground(String... paramVarArgs)
    {
      System.out.println("DO IN BACKROUND.........." + paramVarArgs[0]);
      Profile.access$902(Profile.this, "http://" + MyPhoneGapActivity.appDomainValue + "/services/app-user-soap/");
      Profile.access$1002(Profile.this, "http://" + MyPhoneGapActivity.appDomainValue + "/services/app-user-soap/");
      try
      {
        SoapObject localSoapObject = new SoapObject(Profile.this.NAMESPACE, "changePassword");
        AndroidHttpTransport localAndroidHttpTransport = new AndroidHttpTransport(Profile.this.URL);
        localSoapObject.addProperty("password", paramVarArgs[0]);
        localSoapObject.addProperty("appId", MyPhoneGapActivity.AppId);
        localSoapObject.addProperty("email", Profile.this.getIntent().getStringExtra("profileEmail"));
        localSoapObject.addProperty("deviceType", "Android");
        paramVarArgs = new SoapSerializationEnvelope(110);
        paramVarArgs.setOutputSoapObject(localSoapObject);
        localAndroidHttpTransport.call("http://schemas.xmlsoap.org/wsdl/", paramVarArgs);
        paramVarArgs = (SoapObject)paramVarArgs.bodyIn;
        System.out.println("SOAP Result=" + paramVarArgs.toString());
        return "success";
      }
      catch (Exception paramVarArgs)
      {
        System.out.println("Exception SOAP-:" + paramVarArgs.getMessage());
      }
      return "";
    }
    
    protected void onPostExecute(String paramString)
    {
      super.onPostExecute(paramString);
      if (!paramString.equalsIgnoreCase(""))
      {
        Toast.makeText(Profile.this, "password changed successfully", 0).show();
        return;
      }
      Toast.makeText(Profile.this, "password change failed", 0).show();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\Profile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */