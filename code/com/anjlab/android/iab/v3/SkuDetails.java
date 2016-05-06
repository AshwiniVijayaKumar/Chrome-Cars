package com.anjlab.android.iab.v3;

import org.json.JSONException;
import org.json.JSONObject;

public class SkuDetails
{
  public final String currency;
  public final String description;
  public final boolean isSubscription;
  public final String priceText;
  public final Double priceValue;
  public final String productId;
  public final String title;
  
  public SkuDetails(JSONObject paramJSONObject)
    throws JSONException
  {
    String str2 = paramJSONObject.getString("type");
    String str1 = str2;
    if (str2 == null) {
      str1 = "inapp";
    }
    this.productId = paramJSONObject.getString("productId");
    this.title = paramJSONObject.getString("title");
    this.description = paramJSONObject.getString("description");
    this.isSubscription = str1.equalsIgnoreCase("subs");
    this.currency = paramJSONObject.getString("price_currency_code");
    this.priceValue = Double.valueOf(paramJSONObject.getDouble("price_amount_micros") / 1000000.0D);
    this.priceText = paramJSONObject.getString("price");
  }
  
  public String toString()
  {
    return String.format("%s: %s(%s) %f in %s (%s)", new Object[] { this.productId, this.title, this.description, this.priceValue, this.currency, this.priceText });
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\anjlab\android\iab\v3\SkuDetails.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */