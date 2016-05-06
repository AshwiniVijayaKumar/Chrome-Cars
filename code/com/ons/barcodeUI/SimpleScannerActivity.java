package com.ons.barcodeUI;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.ons.barcodeSupport.ZXingScannerView;
import com.ons.barcodeSupport.ZXingScannerView.ResultHandler;

public class SimpleScannerActivity
  extends Activity
  implements ZXingScannerView.ResultHandler
{
  public static String isActivityfrom = null;
  private ZXingScannerView mScannerView;
  
  public void handleResult(Result paramResult)
  {
    isActivityfrom = "yes";
    Intent localIntent = new Intent();
    localIntent.putExtra("BarResult", paramResult.getText());
    localIntent.putExtra("BarFormat", paramResult.getBarcodeFormat().toString());
    setResult(-1, localIntent);
    this.mScannerView.startCamera();
    finish();
  }
  
  /* Error */
  public void onCreate(android.os.Bundle paramBundle)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial 72	android/app/Activity:onCreate	(Landroid/os/Bundle;)V
    //   5: aload_0
    //   6: bipush 8
    //   8: invokevirtual 76	com/ons/barcodeUI/SimpleScannerActivity:requestWindowFeature	(I)Z
    //   11: pop
    //   12: aload_0
    //   13: invokevirtual 80	com/ons/barcodeUI/SimpleScannerActivity:getActionBar	()Landroid/app/ActionBar;
    //   16: astore_1
    //   17: aload_1
    //   18: iconst_1
    //   19: invokevirtual 86	android/app/ActionBar:setDisplayHomeAsUpEnabled	(Z)V
    //   22: aload_1
    //   23: new 88	android/graphics/drawable/ColorDrawable
    //   26: dup
    //   27: ldc 90
    //   29: invokestatic 96	android/graphics/Color:parseColor	(Ljava/lang/String;)I
    //   32: invokespecial 99	android/graphics/drawable/ColorDrawable:<init>	(I)V
    //   35: invokevirtual 103	android/app/ActionBar:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   38: aload_0
    //   39: invokevirtual 107	com/ons/barcodeUI/SimpleScannerActivity:getWindow	()Landroid/view/Window;
    //   42: invokevirtual 113	android/view/Window:getDecorView	()Landroid/view/View;
    //   45: aload_0
    //   46: invokevirtual 117	com/ons/barcodeUI/SimpleScannerActivity:getResources	()Landroid/content/res/Resources;
    //   49: ldc 119
    //   51: ldc 121
    //   53: ldc 123
    //   55: invokevirtual 129	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   58: invokevirtual 135	android/view/View:findViewById	(I)Landroid/view/View;
    //   61: checkcast 137	android/view/ViewGroup
    //   64: astore_1
    //   65: aload_1
    //   66: invokevirtual 143	java/lang/Object:getClass	()Ljava/lang/Class;
    //   69: invokevirtual 148	java/lang/Class:getSuperclass	()Ljava/lang/Class;
    //   72: ldc -106
    //   74: invokevirtual 154	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   77: astore_2
    //   78: aload_2
    //   79: iconst_1
    //   80: invokevirtual 159	java/lang/reflect/Field:setAccessible	(Z)V
    //   83: aload_2
    //   84: aload_1
    //   85: bipush 90
    //   87: invokestatic 165	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   90: invokevirtual 169	java/lang/reflect/Field:set	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   93: aload_0
    //   94: new 56	com/ons/barcodeSupport/ZXingScannerView
    //   97: dup
    //   98: aload_0
    //   99: invokespecial 172	com/ons/barcodeSupport/ZXingScannerView:<init>	(Landroid/content/Context;)V
    //   102: putfield 54	com/ons/barcodeUI/SimpleScannerActivity:mScannerView	Lcom/ons/barcodeSupport/ZXingScannerView;
    //   105: aload_0
    //   106: aload_0
    //   107: getfield 54	com/ons/barcodeUI/SimpleScannerActivity:mScannerView	Lcom/ons/barcodeSupport/ZXingScannerView;
    //   110: invokevirtual 176	com/ons/barcodeUI/SimpleScannerActivity:setContentView	(Landroid/view/View;)V
    //   113: return
    //   114: astore_1
    //   115: goto -22 -> 93
    //   118: astore_1
    //   119: goto -26 -> 93
    //   122: astore_1
    //   123: goto -30 -> 93
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	126	0	this	SimpleScannerActivity
    //   0	126	1	paramBundle	android.os.Bundle
    //   77	7	2	localField	java.lang.reflect.Field
    // Exception table:
    //   from	to	target	type
    //   5	65	114	java/lang/Exception
    //   65	93	114	java/lang/Exception
    //   65	93	118	java/lang/IllegalAccessException
    //   65	93	122	java/lang/NoSuchFieldException
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 16908332) {
      finish();
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  public void onPause()
  {
    super.onPause();
    this.mScannerView.stopCamera();
  }
  
  public void onResume()
  {
    super.onResume();
    this.mScannerView.setResultHandler(this);
    this.mScannerView.startCamera();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\barcodeUI\SimpleScannerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */