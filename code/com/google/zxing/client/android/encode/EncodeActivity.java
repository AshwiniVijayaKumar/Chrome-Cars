package com.google.zxing.client.android.encode;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.zxing.WriterException;
import com.google.zxing.client.android.FinishListener;
import com.google.zxing.client.android.R.id;
import com.google.zxing.client.android.R.layout;
import com.google.zxing.client.android.R.menu;
import com.google.zxing.client.android.R.string;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EncodeActivity
  extends Activity
{
  private static final int MAX_BARCODE_FILENAME_LENGTH = 24;
  private static final Pattern NOT_ALPHANUMERIC = Pattern.compile("[^A-Za-z0-9]");
  private static final String TAG = EncodeActivity.class.getSimpleName();
  private static final String USE_VCARD_KEY = "USE_VCARD";
  private QRCodeEncoder qrCodeEncoder;
  
  private static CharSequence makeBarcodeFileName(CharSequence paramCharSequence)
  {
    String str = NOT_ALPHANUMERIC.matcher(paramCharSequence).replaceAll("_");
    paramCharSequence = str;
    if (str.length() > 24) {
      paramCharSequence = str.substring(0, 24);
    }
    return paramCharSequence;
  }
  
  /* Error */
  private void share()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 73	com/google/zxing/client/android/encode/EncodeActivity:qrCodeEncoder	Lcom/google/zxing/client/android/encode/QRCodeEncoder;
    //   4: astore 5
    //   6: aload 5
    //   8: ifnonnull +13 -> 21
    //   11: getstatic 26	com/google/zxing/client/android/encode/EncodeActivity:TAG	Ljava/lang/String;
    //   14: ldc 75
    //   16: invokestatic 81	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   19: pop
    //   20: return
    //   21: aload 5
    //   23: invokevirtual 86	com/google/zxing/client/android/encode/QRCodeEncoder:getContents	()Ljava/lang/String;
    //   26: astore 6
    //   28: aload 6
    //   30: ifnonnull +13 -> 43
    //   33: getstatic 26	com/google/zxing/client/android/encode/EncodeActivity:TAG	Ljava/lang/String;
    //   36: ldc 75
    //   38: invokestatic 81	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   41: pop
    //   42: return
    //   43: aload 5
    //   45: invokevirtual 90	com/google/zxing/client/android/encode/QRCodeEncoder:encodeAsBitmap	()Landroid/graphics/Bitmap;
    //   48: astore 7
    //   50: aload 7
    //   52: ifnull -32 -> 20
    //   55: new 92	java/io/File
    //   58: dup
    //   59: new 92	java/io/File
    //   62: dup
    //   63: invokestatic 98	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   66: ldc 100
    //   68: invokespecial 103	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   71: ldc 105
    //   73: invokespecial 103	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   76: astore_1
    //   77: aload_1
    //   78: invokevirtual 109	java/io/File:exists	()Z
    //   81: ifne +51 -> 132
    //   84: aload_1
    //   85: invokevirtual 112	java/io/File:mkdirs	()Z
    //   88: ifne +44 -> 132
    //   91: getstatic 26	com/google/zxing/client/android/encode/EncodeActivity:TAG	Ljava/lang/String;
    //   94: new 114	java/lang/StringBuilder
    //   97: dup
    //   98: ldc 116
    //   100: invokespecial 119	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   103: aload_1
    //   104: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   107: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   110: invokestatic 81	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   113: pop
    //   114: aload_0
    //   115: getstatic 131	com/google/zxing/client/android/R$string:msg_unmount_usb	I
    //   118: invokespecial 135	com/google/zxing/client/android/encode/EncodeActivity:showErrorMessage	(I)V
    //   121: return
    //   122: astore_1
    //   123: getstatic 26	com/google/zxing/client/android/encode/EncodeActivity:TAG	Ljava/lang/String;
    //   126: aload_1
    //   127: invokestatic 138	android/util/Log:w	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   130: pop
    //   131: return
    //   132: new 92	java/io/File
    //   135: dup
    //   136: aload_1
    //   137: new 114	java/lang/StringBuilder
    //   140: dup
    //   141: invokespecial 139	java/lang/StringBuilder:<init>	()V
    //   144: aload 6
    //   146: invokestatic 141	com/google/zxing/client/android/encode/EncodeActivity:makeBarcodeFileName	(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;
    //   149: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   152: ldc -113
    //   154: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   160: invokespecial 103	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   163: astore 4
    //   165: aload 4
    //   167: invokevirtual 149	java/io/File:delete	()Z
    //   170: pop
    //   171: aconst_null
    //   172: astore_1
    //   173: aconst_null
    //   174: astore_3
    //   175: new 151	java/io/FileOutputStream
    //   178: dup
    //   179: aload 4
    //   181: invokespecial 154	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   184: astore_2
    //   185: aload 7
    //   187: getstatic 160	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
    //   190: iconst_0
    //   191: aload_2
    //   192: invokevirtual 166	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   195: pop
    //   196: aload_2
    //   197: ifnull +7 -> 204
    //   200: aload_2
    //   201: invokevirtual 169	java/io/FileOutputStream:close	()V
    //   204: new 171	android/content/Intent
    //   207: dup
    //   208: ldc -83
    //   210: ldc -81
    //   212: invokestatic 181	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   215: invokespecial 184	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   218: astore_1
    //   219: aload_1
    //   220: ldc -70
    //   222: new 114	java/lang/StringBuilder
    //   225: dup
    //   226: aload_0
    //   227: getstatic 189	com/google/zxing/client/android/R$string:app_name	I
    //   230: invokevirtual 193	com/google/zxing/client/android/encode/EncodeActivity:getString	(I)Ljava/lang/String;
    //   233: invokestatic 197	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   236: invokespecial 119	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   239: ldc -57
    //   241: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   244: aload 5
    //   246: invokevirtual 202	com/google/zxing/client/android/encode/QRCodeEncoder:getTitle	()Ljava/lang/String;
    //   249: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   252: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   255: invokevirtual 206	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   258: pop
    //   259: aload_1
    //   260: ldc -48
    //   262: aload 6
    //   264: invokevirtual 206	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   267: pop
    //   268: aload_1
    //   269: ldc -46
    //   271: new 114	java/lang/StringBuilder
    //   274: dup
    //   275: ldc -44
    //   277: invokespecial 119	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   280: aload 4
    //   282: invokevirtual 215	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   285: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   288: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   291: invokestatic 181	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   294: invokevirtual 218	android/content/Intent:putExtra	(Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;
    //   297: pop
    //   298: aload_1
    //   299: ldc -36
    //   301: invokevirtual 224	android/content/Intent:setType	(Ljava/lang/String;)Landroid/content/Intent;
    //   304: pop
    //   305: aload_1
    //   306: ldc -31
    //   308: invokevirtual 229	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   311: pop
    //   312: aload_0
    //   313: aload_1
    //   314: aconst_null
    //   315: invokestatic 233	android/content/Intent:createChooser	(Landroid/content/Intent;Ljava/lang/CharSequence;)Landroid/content/Intent;
    //   318: invokevirtual 237	com/google/zxing/client/android/encode/EncodeActivity:startActivity	(Landroid/content/Intent;)V
    //   321: return
    //   322: astore_1
    //   323: aload_3
    //   324: astore_2
    //   325: aload_1
    //   326: astore_3
    //   327: aload_2
    //   328: astore_1
    //   329: getstatic 26	com/google/zxing/client/android/encode/EncodeActivity:TAG	Ljava/lang/String;
    //   332: new 114	java/lang/StringBuilder
    //   335: dup
    //   336: ldc -17
    //   338: invokespecial 119	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   341: aload 4
    //   343: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   346: ldc -15
    //   348: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   351: aload_3
    //   352: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   355: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   358: invokestatic 81	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   361: pop
    //   362: aload_2
    //   363: astore_1
    //   364: aload_0
    //   365: getstatic 131	com/google/zxing/client/android/R$string:msg_unmount_usb	I
    //   368: invokespecial 135	com/google/zxing/client/android/encode/EncodeActivity:showErrorMessage	(I)V
    //   371: aload_2
    //   372: ifnull -352 -> 20
    //   375: aload_2
    //   376: invokevirtual 169	java/io/FileOutputStream:close	()V
    //   379: return
    //   380: astore_1
    //   381: return
    //   382: astore_2
    //   383: aload_1
    //   384: ifnull +7 -> 391
    //   387: aload_1
    //   388: invokevirtual 169	java/io/FileOutputStream:close	()V
    //   391: aload_2
    //   392: athrow
    //   393: astore_1
    //   394: goto -3 -> 391
    //   397: astore_1
    //   398: goto -194 -> 204
    //   401: astore_3
    //   402: aload_2
    //   403: astore_1
    //   404: aload_3
    //   405: astore_2
    //   406: goto -23 -> 383
    //   409: astore_3
    //   410: goto -83 -> 327
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	413	0	this	EncodeActivity
    //   76	28	1	localFile1	java.io.File
    //   122	15	1	localWriterException	WriterException
    //   172	142	1	localIntent	Intent
    //   322	4	1	localFileNotFoundException1	java.io.FileNotFoundException
    //   328	36	1	localObject1	Object
    //   380	8	1	localIOException1	java.io.IOException
    //   393	1	1	localIOException2	java.io.IOException
    //   397	1	1	localIOException3	java.io.IOException
    //   403	1	1	localObject2	Object
    //   184	192	2	localObject3	Object
    //   382	21	2	localObject4	Object
    //   405	1	2	localObject5	Object
    //   174	178	3	localFileNotFoundException2	java.io.FileNotFoundException
    //   401	4	3	localObject6	Object
    //   409	1	3	localFileNotFoundException3	java.io.FileNotFoundException
    //   163	179	4	localFile2	java.io.File
    //   4	241	5	localQRCodeEncoder	QRCodeEncoder
    //   26	237	6	str	String
    //   48	138	7	localBitmap	Bitmap
    // Exception table:
    //   from	to	target	type
    //   43	50	122	com/google/zxing/WriterException
    //   175	185	322	java/io/FileNotFoundException
    //   375	379	380	java/io/IOException
    //   175	185	382	finally
    //   329	362	382	finally
    //   364	371	382	finally
    //   387	391	393	java/io/IOException
    //   200	204	397	java/io/IOException
    //   185	196	401	finally
    //   185	196	409	java/io/FileNotFoundException
  }
  
  private void showErrorMessage(int paramInt)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage(paramInt);
    localBuilder.setPositiveButton(R.string.button_ok, new FinishListener(this));
    localBuilder.setOnCancelListener(new FinishListener(this));
    localBuilder.show();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getIntent() == null)
    {
      finish();
      return;
    }
    setContentView(R.layout.encode);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(R.menu.encode, paramMenu);
    if ((this.qrCodeEncoder != null) && (this.qrCodeEncoder.isUseVCard()))
    {
      i = 1;
      if (i == 0) {
        break label99;
      }
    }
    label99:
    for (int i = R.string.menu_encode_mecard;; i = R.string.menu_encode_vcard)
    {
      MenuItem localMenuItem = paramMenu.findItem(R.id.menu_encode);
      localMenuItem.setTitle(i);
      Intent localIntent = getIntent();
      if (localIntent != null) {
        localMenuItem.setVisible("CONTACT_TYPE".equals(localIntent.getStringExtra("ENCODE_TYPE")));
      }
      return super.onCreateOptionsMenu(paramMenu);
      i = 0;
      break;
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    boolean bool = false;
    int i = paramMenuItem.getItemId();
    if (i == R.id.menu_share)
    {
      share();
      return true;
    }
    if (i == R.id.menu_encode)
    {
      paramMenuItem = getIntent();
      if (paramMenuItem == null) {
        return false;
      }
      if (this.qrCodeEncoder.isUseVCard()) {}
      for (;;)
      {
        paramMenuItem.putExtra("USE_VCARD", bool);
        startActivity(paramMenuItem);
        finish();
        return true;
        bool = true;
      }
    }
    return false;
  }
  
  protected void onResume()
  {
    super.onResume();
    Object localObject1 = ((WindowManager)getSystemService("window")).getDefaultDisplay();
    int i = ((Display)localObject1).getWidth();
    int j = ((Display)localObject1).getHeight();
    if (i < j) {}
    for (;;)
    {
      i = i * 7 / 8;
      localObject1 = getIntent();
      if (localObject1 != null) {
        break;
      }
      return;
      i = j;
    }
    try
    {
      this.qrCodeEncoder = new QRCodeEncoder(this, (Intent)localObject1, i, ((Intent)localObject1).getBooleanExtra("USE_VCARD", false));
      localObject2 = this.qrCodeEncoder.encodeAsBitmap();
      if (localObject2 == null)
      {
        Log.w(TAG, "Could not encode barcode");
        showErrorMessage(R.string.msg_encode_contents_failed);
        this.qrCodeEncoder = null;
        return;
      }
    }
    catch (WriterException localWriterException)
    {
      Log.w(TAG, "Could not encode barcode", localWriterException);
      showErrorMessage(R.string.msg_encode_contents_failed);
      this.qrCodeEncoder = null;
      return;
    }
    ((ImageView)findViewById(R.id.image_view)).setImageBitmap((Bitmap)localObject2);
    Object localObject2 = (TextView)findViewById(R.id.contents_text_view);
    if (localWriterException.getBooleanExtra("ENCODE_SHOW_CONTENTS", true))
    {
      ((TextView)localObject2).setText(this.qrCodeEncoder.getDisplayContents());
      setTitle(this.qrCodeEncoder.getTitle());
      return;
    }
    ((TextView)localObject2).setText("");
    setTitle("");
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\encode\EncodeActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */