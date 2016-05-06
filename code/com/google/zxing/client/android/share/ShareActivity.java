package com.google.zxing.client.android.share;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.TextView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.android.R.id;
import com.google.zxing.client.android.R.layout;

public final class ShareActivity
  extends Activity
{
  private static final int PICK_APP = 2;
  private static final int PICK_BOOKMARK = 0;
  private static final int PICK_CONTACT = 1;
  private static final String TAG = ShareActivity.class.getSimpleName();
  private final View.OnClickListener appListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      paramAnonymousView = new Intent("android.intent.action.PICK");
      paramAnonymousView.addFlags(524288);
      paramAnonymousView.setClassName(ShareActivity.this, AppPickerActivity.class.getName());
      ShareActivity.this.startActivityForResult(paramAnonymousView, 2);
    }
  };
  private final View.OnClickListener bookmarkListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      paramAnonymousView = new Intent("android.intent.action.PICK");
      paramAnonymousView.addFlags(524288);
      paramAnonymousView.setClassName(ShareActivity.this, BookmarkPickerActivity.class.getName());
      ShareActivity.this.startActivityForResult(paramAnonymousView, 0);
    }
  };
  private Button clipboardButton;
  private final View.OnClickListener clipboardListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      paramAnonymousView = (ClipboardManager)ShareActivity.this.getSystemService("clipboard");
      if (paramAnonymousView.hasText()) {
        ShareActivity.this.launchSearch(paramAnonymousView.getText().toString());
      }
    }
  };
  private final View.OnClickListener contactListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      paramAnonymousView = new Intent("android.intent.action.PICK", ContactsContract.Contacts.CONTENT_URI);
      paramAnonymousView.addFlags(524288);
      ShareActivity.this.startActivityForResult(paramAnonymousView, 1);
    }
  };
  private final View.OnKeyListener textListener = new View.OnKeyListener()
  {
    public boolean onKey(View paramAnonymousView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
    {
      if ((paramAnonymousInt == 66) && (paramAnonymousKeyEvent.getAction() == 0))
      {
        paramAnonymousView = ((TextView)paramAnonymousView).getText().toString();
        if ((paramAnonymousView != null) && (paramAnonymousView.length() > 0)) {
          ShareActivity.this.launchSearch(paramAnonymousView);
        }
        return true;
      }
      return false;
    }
  };
  
  private void launchSearch(String paramString)
  {
    Intent localIntent = new Intent("com.google.zxing.client.android.ENCODE");
    localIntent.addFlags(524288);
    localIntent.putExtra("ENCODE_TYPE", "TEXT_TYPE");
    localIntent.putExtra("ENCODE_DATA", paramString);
    localIntent.putExtra("ENCODE_FORMAT", BarcodeFormat.QR_CODE.toString());
    startActivity(localIntent);
  }
  
  private static String massageContactData(String paramString)
  {
    String str = paramString;
    if (paramString.indexOf('\n') >= 0) {
      str = paramString.replace("\n", " ");
    }
    paramString = str;
    if (str.indexOf('\r') >= 0) {
      paramString = str.replace("\r", " ");
    }
    return paramString;
  }
  
  /* Error */
  private void showContactAsBarcode(android.net.Uri paramUri)
  {
    // Byte code:
    //   0: getstatic 42	com/google/zxing/client/android/share/ShareActivity:TAG	Ljava/lang/String;
    //   3: new 129	java/lang/StringBuilder
    //   6: dup
    //   7: invokespecial 130	java/lang/StringBuilder:<init>	()V
    //   10: ldc -124
    //   12: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: aload_1
    //   16: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   19: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   22: invokestatic 146	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   25: pop
    //   26: aload_1
    //   27: ifnonnull +4 -> 31
    //   30: return
    //   31: aload_0
    //   32: invokevirtual 150	com/google/zxing/client/android/share/ShareActivity:getContentResolver	()Landroid/content/ContentResolver;
    //   35: astore 6
    //   37: new 152	android/os/Bundle
    //   40: dup
    //   41: invokespecial 153	android/os/Bundle:<init>	()V
    //   44: astore 5
    //   46: aload 6
    //   48: aload_1
    //   49: aconst_null
    //   50: aconst_null
    //   51: aconst_null
    //   52: aconst_null
    //   53: invokevirtual 159	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   56: astore 7
    //   58: aload 7
    //   60: ifnull -30 -> 30
    //   63: aload 7
    //   65: invokeinterface 165 1 0
    //   70: istore 4
    //   72: iload 4
    //   74: ifne +13 -> 87
    //   77: aload 7
    //   79: invokeinterface 168 1 0
    //   84: return
    //   85: astore_1
    //   86: return
    //   87: aload 7
    //   89: aload 7
    //   91: ldc -86
    //   93: invokeinterface 174 2 0
    //   98: invokeinterface 178 2 0
    //   103: astore_1
    //   104: aload 7
    //   106: aload 7
    //   108: ldc -76
    //   110: invokeinterface 174 2 0
    //   115: invokeinterface 178 2 0
    //   120: astore 8
    //   122: aload 7
    //   124: aload 7
    //   126: ldc -74
    //   128: invokeinterface 174 2 0
    //   133: invokeinterface 185 2 0
    //   138: istore_2
    //   139: iload_2
    //   140: ifle +153 -> 293
    //   143: iconst_1
    //   144: istore_2
    //   145: aload 7
    //   147: invokeinterface 168 1 0
    //   152: aload 8
    //   154: ifnull +23 -> 177
    //   157: aload 8
    //   159: invokevirtual 189	java/lang/String:length	()I
    //   162: ifle +15 -> 177
    //   165: aload 5
    //   167: ldc -65
    //   169: aload 8
    //   171: invokestatic 193	com/google/zxing/client/android/share/ShareActivity:massageContactData	(Ljava/lang/String;)Ljava/lang/String;
    //   174: invokevirtual 197	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   177: iload_2
    //   178: ifeq +137 -> 315
    //   181: aload 6
    //   183: getstatic 203	android/provider/ContactsContract$CommonDataKinds$Phone:CONTENT_URI	Landroid/net/Uri;
    //   186: aconst_null
    //   187: new 129	java/lang/StringBuilder
    //   190: dup
    //   191: invokespecial 130	java/lang/StringBuilder:<init>	()V
    //   194: ldc -51
    //   196: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: aload_1
    //   200: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   206: aconst_null
    //   207: aconst_null
    //   208: invokevirtual 159	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   211: astore 7
    //   213: aload 7
    //   215: ifnull +100 -> 315
    //   218: iconst_0
    //   219: istore_2
    //   220: aload 7
    //   222: ldc -49
    //   224: invokeinterface 174 2 0
    //   229: istore_3
    //   230: aload 7
    //   232: invokeinterface 210 1 0
    //   237: ifeq +71 -> 308
    //   240: iload_2
    //   241: getstatic 216	com/google/zxing/client/android/Contents:PHONE_KEYS	[Ljava/lang/String;
    //   244: arraylength
    //   245: if_icmpge +63 -> 308
    //   248: aload 7
    //   250: iload_3
    //   251: invokeinterface 178 2 0
    //   256: astore 8
    //   258: aload 8
    //   260: ifnull +26 -> 286
    //   263: aload 8
    //   265: invokevirtual 189	java/lang/String:length	()I
    //   268: ifle +18 -> 286
    //   271: aload 5
    //   273: getstatic 216	com/google/zxing/client/android/Contents:PHONE_KEYS	[Ljava/lang/String;
    //   276: iload_2
    //   277: aaload
    //   278: aload 8
    //   280: invokestatic 193	com/google/zxing/client/android/share/ShareActivity:massageContactData	(Ljava/lang/String;)Ljava/lang/String;
    //   283: invokevirtual 197	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   286: iload_2
    //   287: iconst_1
    //   288: iadd
    //   289: istore_2
    //   290: goto -60 -> 230
    //   293: iconst_0
    //   294: istore_2
    //   295: goto -150 -> 145
    //   298: astore_1
    //   299: aload 7
    //   301: invokeinterface 168 1 0
    //   306: aload_1
    //   307: athrow
    //   308: aload 7
    //   310: invokeinterface 168 1 0
    //   315: aload 6
    //   317: getstatic 219	android/provider/ContactsContract$CommonDataKinds$StructuredPostal:CONTENT_URI	Landroid/net/Uri;
    //   320: aconst_null
    //   321: new 129	java/lang/StringBuilder
    //   324: dup
    //   325: invokespecial 130	java/lang/StringBuilder:<init>	()V
    //   328: ldc -51
    //   330: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   333: aload_1
    //   334: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   337: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   340: aconst_null
    //   341: aconst_null
    //   342: invokevirtual 159	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   345: astore 7
    //   347: aload 7
    //   349: ifnull +63 -> 412
    //   352: aload 7
    //   354: invokeinterface 210 1 0
    //   359: ifeq +46 -> 405
    //   362: aload 7
    //   364: aload 7
    //   366: ldc -49
    //   368: invokeinterface 174 2 0
    //   373: invokeinterface 178 2 0
    //   378: astore 8
    //   380: aload 8
    //   382: ifnull +23 -> 405
    //   385: aload 8
    //   387: invokevirtual 189	java/lang/String:length	()I
    //   390: ifle +15 -> 405
    //   393: aload 5
    //   395: ldc -35
    //   397: aload 8
    //   399: invokestatic 193	com/google/zxing/client/android/share/ShareActivity:massageContactData	(Ljava/lang/String;)Ljava/lang/String;
    //   402: invokevirtual 197	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   405: aload 7
    //   407: invokeinterface 168 1 0
    //   412: aload 6
    //   414: getstatic 224	android/provider/ContactsContract$CommonDataKinds$Email:CONTENT_URI	Landroid/net/Uri;
    //   417: aconst_null
    //   418: new 129	java/lang/StringBuilder
    //   421: dup
    //   422: invokespecial 130	java/lang/StringBuilder:<init>	()V
    //   425: ldc -51
    //   427: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   430: aload_1
    //   431: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   434: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   437: aconst_null
    //   438: aconst_null
    //   439: invokevirtual 159	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   442: astore_1
    //   443: aload_1
    //   444: ifnull +101 -> 545
    //   447: iconst_0
    //   448: istore_2
    //   449: aload_1
    //   450: ldc -49
    //   452: invokeinterface 174 2 0
    //   457: istore_3
    //   458: aload_1
    //   459: invokeinterface 210 1 0
    //   464: ifeq +75 -> 539
    //   467: iload_2
    //   468: getstatic 227	com/google/zxing/client/android/Contents:EMAIL_KEYS	[Ljava/lang/String;
    //   471: arraylength
    //   472: if_icmpge +67 -> 539
    //   475: aload_1
    //   476: iload_3
    //   477: invokeinterface 178 2 0
    //   482: astore 6
    //   484: aload 6
    //   486: ifnull +26 -> 512
    //   489: aload 6
    //   491: invokevirtual 189	java/lang/String:length	()I
    //   494: ifle +18 -> 512
    //   497: aload 5
    //   499: getstatic 227	com/google/zxing/client/android/Contents:EMAIL_KEYS	[Ljava/lang/String;
    //   502: iload_2
    //   503: aaload
    //   504: aload 6
    //   506: invokestatic 193	com/google/zxing/client/android/share/ShareActivity:massageContactData	(Ljava/lang/String;)Ljava/lang/String;
    //   509: invokevirtual 197	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   512: iload_2
    //   513: iconst_1
    //   514: iadd
    //   515: istore_2
    //   516: goto -58 -> 458
    //   519: astore_1
    //   520: aload 7
    //   522: invokeinterface 168 1 0
    //   527: aload_1
    //   528: athrow
    //   529: astore_1
    //   530: aload 7
    //   532: invokeinterface 168 1 0
    //   537: aload_1
    //   538: athrow
    //   539: aload_1
    //   540: invokeinterface 168 1 0
    //   545: new 71	android/content/Intent
    //   548: dup
    //   549: ldc 73
    //   551: invokespecial 75	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   554: astore_1
    //   555: aload_1
    //   556: ldc 76
    //   558: invokevirtual 80	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   561: pop
    //   562: aload_1
    //   563: ldc 82
    //   565: ldc -27
    //   567: invokevirtual 88	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   570: pop
    //   571: aload_1
    //   572: ldc 90
    //   574: aload 5
    //   576: invokevirtual 232	android/content/Intent:putExtra	(Ljava/lang/String;Landroid/os/Bundle;)Landroid/content/Intent;
    //   579: pop
    //   580: aload_1
    //   581: ldc 92
    //   583: getstatic 98	com/google/zxing/BarcodeFormat:QR_CODE	Lcom/google/zxing/BarcodeFormat;
    //   586: invokevirtual 101	com/google/zxing/BarcodeFormat:toString	()Ljava/lang/String;
    //   589: invokevirtual 88	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   592: pop
    //   593: getstatic 42	com/google/zxing/client/android/share/ShareActivity:TAG	Ljava/lang/String;
    //   596: new 129	java/lang/StringBuilder
    //   599: dup
    //   600: invokespecial 130	java/lang/StringBuilder:<init>	()V
    //   603: ldc -22
    //   605: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   608: aload 5
    //   610: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   613: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   616: invokestatic 146	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   619: pop
    //   620: aload_0
    //   621: aload_1
    //   622: invokevirtual 105	com/google/zxing/client/android/share/ShareActivity:startActivity	(Landroid/content/Intent;)V
    //   625: return
    //   626: astore 5
    //   628: aload_1
    //   629: invokeinterface 168 1 0
    //   634: aload 5
    //   636: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	637	0	this	ShareActivity
    //   0	637	1	paramUri	android.net.Uri
    //   138	378	2	i	int
    //   229	248	3	j	int
    //   70	3	4	bool	boolean
    //   44	565	5	localBundle	Bundle
    //   626	9	5	localObject1	Object
    //   35	470	6	localObject2	Object
    //   56	475	7	localCursor	android.database.Cursor
    //   120	278	8	str	String
    // Exception table:
    //   from	to	target	type
    //   46	58	85	java/lang/IllegalArgumentException
    //   63	72	298	finally
    //   87	139	298	finally
    //   220	230	519	finally
    //   230	258	519	finally
    //   263	286	519	finally
    //   352	380	529	finally
    //   385	405	529	finally
    //   449	458	626	finally
    //   458	484	626	finally
    //   489	512	626	finally
  }
  
  private void showTextAsBarcode(String paramString)
  {
    Log.i(TAG, "Showing text as barcode: " + paramString);
    if (paramString == null) {
      return;
    }
    Intent localIntent = new Intent("com.google.zxing.client.android.ENCODE");
    localIntent.addFlags(524288);
    localIntent.putExtra("ENCODE_TYPE", "TEXT_TYPE");
    localIntent.putExtra("ENCODE_DATA", paramString);
    localIntent.putExtra("ENCODE_FORMAT", BarcodeFormat.QR_CODE.toString());
    startActivity(localIntent);
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == -1) {}
    switch (paramInt1)
    {
    default: 
      return;
    case 0: 
    case 2: 
      showTextAsBarcode(paramIntent.getStringExtra("url"));
      return;
    }
    showContactAsBarcode(paramIntent.getData());
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.share);
    findViewById(R.id.share_contact_button).setOnClickListener(this.contactListener);
    findViewById(R.id.share_bookmark_button).setOnClickListener(this.bookmarkListener);
    findViewById(R.id.share_app_button).setOnClickListener(this.appListener);
    this.clipboardButton = ((Button)findViewById(R.id.share_clipboard_button));
    this.clipboardButton.setOnClickListener(this.clipboardListener);
    findViewById(R.id.share_text_view).setOnKeyListener(this.textListener);
  }
  
  protected void onResume()
  {
    super.onResume();
    ClipboardManager localClipboardManager = (ClipboardManager)getSystemService("clipboard");
    this.clipboardButton.setEnabled(localClipboardManager.hasText());
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\share\ShareActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */