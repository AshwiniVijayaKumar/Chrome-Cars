package com.anjlab.android.iab.v3;

import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class TransactionDetails
{
  public final String orderId;
  public final String productId;
  public final PurchaseInfo purchaseInfo;
  public final Date purchaseTime;
  public final String purchaseToken;
  
  public TransactionDetails(PurchaseInfo paramPurchaseInfo)
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject(paramPurchaseInfo.responseData);
    this.purchaseInfo = paramPurchaseInfo;
    this.productId = localJSONObject.getString("productId");
    this.orderId = localJSONObject.getString("orderId");
    this.purchaseToken = localJSONObject.getString("purchaseToken");
    this.purchaseTime = new Date(localJSONObject.getLong("purchaseTime"));
  }
  
  public String toString()
  {
    return String.format("%s purchased at %s(%s). Token: %s, Signature: %s", new Object[] { this.productId, this.purchaseTime, this.orderId, this.purchaseToken, this.purchaseInfo.signature });
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\anjlab\android\iab\v3\TransactionDetails.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */