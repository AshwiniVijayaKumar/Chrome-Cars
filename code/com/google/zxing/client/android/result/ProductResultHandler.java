package com.google.zxing.client.android.result;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.zxing.Result;
import com.google.zxing.client.android.R.string;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ProductParsedResult;

public final class ProductResultHandler
  extends ResultHandler
{
  private static final int[] buttons = { R.string.button_product_search, R.string.button_web_search, R.string.button_custom_product_search };
  
  public ProductResultHandler(Activity paramActivity, ParsedResult paramParsedResult, Result paramResult)
  {
    super(paramActivity, paramParsedResult, paramResult);
    showGoogleShopperButton(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = (ProductParsedResult)ProductResultHandler.this.getResult();
        ProductResultHandler.this.openGoogleShopper(paramAnonymousView.getNormalizedProductID());
      }
    });
  }
  
  public int getButtonCount()
  {
    if (hasCustomProductSearch()) {
      return buttons.length;
    }
    return buttons.length - 1;
  }
  
  public int getButtonText(int paramInt)
  {
    return buttons[paramInt];
  }
  
  public int getDisplayTitle()
  {
    return R.string.result_product;
  }
  
  public void handleButtonPress(int paramInt)
  {
    ProductParsedResult localProductParsedResult = (ProductParsedResult)getResult();
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      openProductSearch(localProductParsedResult.getNormalizedProductID());
      return;
    case 1: 
      webSearch(localProductParsedResult.getNormalizedProductID());
      return;
    }
    openURL(fillInCustomSearchURL(localProductParsedResult.getNormalizedProductID()));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\result\ProductResultHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */