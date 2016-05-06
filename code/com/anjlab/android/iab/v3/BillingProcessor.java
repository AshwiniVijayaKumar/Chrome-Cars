package com.anjlab.android.iab.v3;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import com.android.vending.billing.IInAppBillingService;
import com.android.vending.billing.IInAppBillingService.Stub;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class BillingProcessor
  extends BillingBase
{
  private static final String LOG_TAG = "iabv3";
  private static final String MANAGED_PRODUCTS_CACHE_KEY = ".products.cache.v2_6";
  private static final int PURCHASE_FLOW_REQUEST_CODE = 2061984;
  private static final String PURCHASE_PAYLOAD_CACHE_KEY = ".purchase.last.v2_6";
  private static final String RESTORE_KEY = ".products.restored.v2_6";
  private static final String SETTINGS_VERSION = ".v2_6";
  private static final String SUBSCRIPTIONS_CACHE_KEY = ".subscriptions.cache.v2_6";
  private IInAppBillingService billingService;
  private BillingCache cachedProducts;
  private BillingCache cachedSubscriptions;
  private String contextPackageName;
  private IBillingHandler eventHandler;
  private ServiceConnection serviceConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      BillingProcessor.access$002(BillingProcessor.this, IInAppBillingService.Stub.asInterface(paramAnonymousIBinder));
      if ((!BillingProcessor.this.isPurchaseHistoryRestored()) && (BillingProcessor.this.loadOwnedPurchasesFromGoogle()))
      {
        BillingProcessor.this.setPurchaseHistoryRestored();
        if (BillingProcessor.this.eventHandler != null) {
          BillingProcessor.this.eventHandler.onPurchaseHistoryRestored();
        }
      }
      if (BillingProcessor.this.eventHandler != null) {
        BillingProcessor.this.eventHandler.onBillingInitialized();
      }
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      BillingProcessor.access$002(BillingProcessor.this, null);
    }
  };
  private String signatureBase64;
  
  public BillingProcessor(Context paramContext, String paramString, IBillingHandler paramIBillingHandler)
  {
    super(paramContext);
    this.signatureBase64 = paramString;
    this.eventHandler = paramIBillingHandler;
    this.contextPackageName = paramContext.getApplicationContext().getPackageName();
    this.cachedProducts = new BillingCache(paramContext, ".products.cache.v2_6");
    this.cachedSubscriptions = new BillingCache(paramContext, ".subscriptions.cache.v2_6");
    bindPlayServices();
  }
  
  private void bindPlayServices()
  {
    try
    {
      Intent localIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
      localIntent.setPackage("com.android.vending");
      getContext().bindService(localIntent, this.serviceConnection, 1);
      return;
    }
    catch (Exception localException)
    {
      Log.e("iabv3", localException.toString());
    }
  }
  
  private String getPurchasePayload()
  {
    return loadString(getPreferencesBaseKey() + ".purchase.last.v2_6", null);
  }
  
  private TransactionDetails getPurchaseTransactionDetails(String paramString, BillingCache paramBillingCache)
  {
    paramBillingCache = paramBillingCache.getDetails(paramString);
    if ((paramBillingCache != null) && (!TextUtils.isEmpty(paramBillingCache.responseData))) {
      try
      {
        paramBillingCache = new TransactionDetails(paramBillingCache);
        return paramBillingCache;
      }
      catch (JSONException paramBillingCache)
      {
        Log.e("iabv3", "Failed to load saved purchase details for " + paramString);
      }
    }
    return null;
  }
  
  private SkuDetails getSkuDetails(String paramString1, String paramString2)
  {
    if (this.billingService != null) {}
    try
    {
      Object localObject = new ArrayList();
      ((ArrayList)localObject).add(paramString1);
      Bundle localBundle = new Bundle();
      localBundle.putStringArrayList("ITEM_ID_LIST", (ArrayList)localObject);
      paramString2 = this.billingService.getSkuDetails(3, this.contextPackageName, paramString2, localBundle);
      int i = paramString2.getInt("RESPONSE_CODE");
      if (i == 0)
      {
        paramString2 = paramString2.getStringArrayList("DETAILS_LIST").iterator();
        do
        {
          if (!paramString2.hasNext()) {
            break;
          }
          localObject = new JSONObject((String)paramString2.next());
        } while (!paramString1.equals(((JSONObject)localObject).getString("productId")));
        return new SkuDetails((JSONObject)localObject);
      }
      if (this.eventHandler != null) {
        this.eventHandler.onBillingError(i, null);
      }
      Log.e("iabv3", String.format("Failed to retrieve info for %s: error %d", new Object[] { paramString1, Integer.valueOf(i) }));
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        Log.e("iabv3", String.format("Failed to call getSkuDetails %s", new Object[] { paramString1.toString() }));
      }
    }
    return null;
  }
  
  private boolean isPurchaseHistoryRestored()
  {
    return loadBoolean(getPreferencesBaseKey() + ".products.restored.v2_6", false);
  }
  
  private boolean loadPurchasesByType(String paramString, BillingCache paramBillingCache)
  {
    if (!isInitialized()) {
      return false;
    }
    try
    {
      paramString = this.billingService.getPurchases(3, this.contextPackageName, paramString, null);
      if (paramString.getInt("RESPONSE_CODE") == 0)
      {
        paramBillingCache.clear();
        ArrayList localArrayList1 = paramString.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
        ArrayList localArrayList2 = paramString.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
        int i = 0;
        if (i < localArrayList1.size())
        {
          String str = (String)localArrayList1.get(i);
          JSONObject localJSONObject = new JSONObject(str);
          if ((localArrayList2 != null) && (localArrayList2.size() > i)) {}
          for (paramString = (String)localArrayList2.get(i);; paramString = null)
          {
            paramBillingCache.put(localJSONObject.getString("productId"), str, paramString);
            i += 1;
            break;
          }
        }
      }
      return true;
    }
    catch (Exception paramString)
    {
      if (this.eventHandler != null) {
        this.eventHandler.onBillingError(100, paramString);
      }
      Log.e("iabv3", paramString.toString());
    }
    return false;
  }
  
  private boolean purchase(Activity paramActivity, String paramString1, String paramString2)
  {
    if ((!isInitialized()) || (TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2))) {
      return false;
    }
    int i;
    try
    {
      String str = paramString2 + ":" + UUID.randomUUID().toString();
      savePurchasePayload(str);
      paramString2 = this.billingService.getBuyIntent(3, this.contextPackageName, paramString1, paramString2, str);
      if (paramString2 == null) {
        break label255;
      }
      i = paramString2.getInt("RESPONSE_CODE");
      if (i == 0)
      {
        paramString1 = (PendingIntent)paramString2.getParcelable("BUY_INTENT");
        if (paramActivity != null) {
          paramActivity.startIntentSenderForResult(paramString1.getIntentSender(), 2061984, new Intent(), 0, 0, 0);
        } else if (this.eventHandler != null) {
          this.eventHandler.onBillingError(103, null);
        }
      }
    }
    catch (Exception paramActivity)
    {
      Log.e("iabv3", paramActivity.toString());
      return false;
    }
    if (i == 7)
    {
      if ((!isPurchased(paramString1)) && (!isSubscribed(paramString1))) {
        loadOwnedPurchasesFromGoogle();
      }
      if (this.eventHandler != null)
      {
        paramString2 = getPurchaseTransactionDetails(paramString1);
        paramActivity = paramString2;
        if (paramString2 == null) {
          paramActivity = getSubscriptionTransactionDetails(paramString1);
        }
        this.eventHandler.onProductPurchased(paramString1, paramActivity);
      }
    }
    else if (this.eventHandler != null)
    {
      this.eventHandler.onBillingError(101, null);
    }
    label255:
    return true;
  }
  
  private void savePurchasePayload(String paramString)
  {
    saveString(getPreferencesBaseKey() + ".purchase.last.v2_6", paramString);
  }
  
  private boolean verifyPurchaseSignature(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      if (TextUtils.isEmpty(this.signatureBase64)) {
        return true;
      }
      boolean bool = Security.verifyPurchase(paramString1, this.signatureBase64, paramString2, paramString3);
      return bool;
    }
    catch (Exception paramString1) {}
    return false;
  }
  
  public boolean consumePurchase(String paramString)
  {
    if (!isInitialized()) {}
    for (;;)
    {
      return false;
      try
      {
        TransactionDetails localTransactionDetails = getPurchaseTransactionDetails(paramString, this.cachedProducts);
        if ((localTransactionDetails != null) && (!TextUtils.isEmpty(localTransactionDetails.purchaseToken)))
        {
          int i = this.billingService.consumePurchase(3, this.contextPackageName, localTransactionDetails.purchaseToken);
          if (i == 0)
          {
            this.cachedProducts.remove(paramString);
            Log.d("iabv3", "Successfully consumed " + paramString + " purchase.");
            return true;
          }
          if (this.eventHandler != null) {
            this.eventHandler.onBillingError(i, null);
          }
          Log.e("iabv3", String.format("Failed to consume %s: error %d", new Object[] { paramString, Integer.valueOf(i) }));
          return false;
        }
      }
      catch (Exception paramString)
      {
        Log.e("iabv3", paramString.toString());
      }
    }
    return false;
  }
  
  public SkuDetails getPurchaseListingDetails(String paramString)
  {
    return getSkuDetails(paramString, "inapp");
  }
  
  public TransactionDetails getPurchaseTransactionDetails(String paramString)
  {
    return getPurchaseTransactionDetails(paramString, this.cachedProducts);
  }
  
  public SkuDetails getSubscriptionListingDetails(String paramString)
  {
    return getSkuDetails(paramString, "subs");
  }
  
  public TransactionDetails getSubscriptionTransactionDetails(String paramString)
  {
    return getPurchaseTransactionDetails(paramString, this.cachedSubscriptions);
  }
  
  public boolean handleActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 != 2061984) {
      return false;
    }
    if (paramIntent == null)
    {
      Log.e("iabv3", "handleActivityResult: data is null!");
      return false;
    }
    paramInt1 = paramIntent.getIntExtra("RESPONSE_CODE", 0);
    Log.d("iabv3", String.format("resultCode = %d, responseCode = %d", new Object[] { Integer.valueOf(paramInt2), Integer.valueOf(paramInt1) }));
    String str5 = getPurchasePayload();
    if ((paramInt2 == -1) && (paramInt1 == 0) && (!TextUtils.isEmpty(str5)))
    {
      String str2 = paramIntent.getStringExtra("INAPP_PURCHASE_DATA");
      String str3 = paramIntent.getStringExtra("INAPP_DATA_SIGNATURE");
      try
      {
        paramIntent = new JSONObject(str2);
        String str4 = paramIntent.getString("productId");
        String str1 = paramIntent.getString("developerPayload");
        paramIntent = str1;
        if (str1 == null) {
          paramIntent = "";
        }
        boolean bool = str5.startsWith("subs");
        if (!str5.equals(paramIntent)) {
          break label304;
        }
        if (verifyPurchaseSignature(str4, str2, str3))
        {
          if (bool) {}
          for (paramIntent = this.cachedSubscriptions;; paramIntent = this.cachedProducts)
          {
            paramIntent.put(str4, str2, str3);
            if (this.eventHandler == null) {
              break;
            }
            this.eventHandler.onProductPurchased(str4, new TransactionDetails(new PurchaseInfo(str2, str3)));
            break;
          }
        }
        Log.e("iabv3", "Public key signature doesn't match!");
        if (this.eventHandler == null) {
          break label369;
        }
        this.eventHandler.onBillingError(102, null);
      }
      catch (Exception paramIntent)
      {
        Log.e("iabv3", paramIntent.toString());
        if (this.eventHandler == null) {
          break label369;
        }
      }
      this.eventHandler.onBillingError(110, null);
      break label369;
      label304:
      Log.e("iabv3", String.format("Payload mismatch: %s != %s", new Object[] { str5, paramIntent }));
      if (this.eventHandler != null) {
        this.eventHandler.onBillingError(102, null);
      }
    }
    else if (this.eventHandler != null)
    {
      this.eventHandler.onBillingError(paramInt1, null);
    }
    label369:
    return true;
  }
  
  public boolean isInitialized()
  {
    return this.billingService != null;
  }
  
  public boolean isPurchased(String paramString)
  {
    return this.cachedProducts.includesProduct(paramString);
  }
  
  public boolean isSubscribed(String paramString)
  {
    return this.cachedSubscriptions.includesProduct(paramString);
  }
  
  public boolean isValid(TransactionDetails paramTransactionDetails)
  {
    return verifyPurchaseSignature(paramTransactionDetails.productId, paramTransactionDetails.purchaseInfo.responseData, paramTransactionDetails.purchaseInfo.signature);
  }
  
  public List<String> listOwnedProducts()
  {
    return this.cachedProducts.getContents();
  }
  
  public List<String> listOwnedSubscriptions()
  {
    return this.cachedSubscriptions.getContents();
  }
  
  public boolean loadOwnedPurchasesFromGoogle()
  {
    return (isInitialized()) && (loadPurchasesByType("inapp", this.cachedProducts)) && (loadPurchasesByType("subs", this.cachedSubscriptions));
  }
  
  public boolean purchase(Activity paramActivity, String paramString)
  {
    return purchase(paramActivity, paramString, "inapp");
  }
  
  public void release()
  {
    if ((this.serviceConnection != null) && (getContext() != null)) {}
    try
    {
      getContext().unbindService(this.serviceConnection);
      this.billingService = null;
      this.cachedProducts.release();
      super.release();
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e("iabv3", localException.toString());
      }
    }
  }
  
  public void setPurchaseHistoryRestored()
  {
    saveBoolean(getPreferencesBaseKey() + ".products.restored.v2_6", Boolean.valueOf(true));
  }
  
  public boolean subscribe(Activity paramActivity, String paramString)
  {
    return purchase(paramActivity, paramString, "subs");
  }
  
  public static abstract interface IBillingHandler
  {
    public abstract void onBillingError(int paramInt, Throwable paramThrowable);
    
    public abstract void onBillingInitialized();
    
    public abstract void onProductPurchased(String paramString, TransactionDetails paramTransactionDetails);
    
    public abstract void onPurchaseHistoryRestored();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\anjlab\android\iab\v3\BillingProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */