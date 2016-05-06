package com.google.zxing.client.android.result;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.zxing.Result;
import com.google.zxing.client.android.R.string;
import com.google.zxing.client.result.ISBNParsedResult;
import com.google.zxing.client.result.ParsedResult;

public final class ISBNResultHandler
  extends ResultHandler
{
  private static final int[] buttons = { R.string.button_product_search, R.string.button_book_search, R.string.button_search_book_contents, R.string.button_custom_product_search };
  
  public ISBNResultHandler(Activity paramActivity, ParsedResult paramParsedResult, Result paramResult)
  {
    super(paramActivity, paramParsedResult, paramResult);
    showGoogleShopperButton(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = (ISBNParsedResult)ISBNResultHandler.this.getResult();
        ISBNResultHandler.this.openGoogleShopper(paramAnonymousView.getISBN());
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
    return R.string.result_isbn;
  }
  
  public void handleButtonPress(int paramInt)
  {
    ISBNParsedResult localISBNParsedResult = (ISBNParsedResult)getResult();
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      openProductSearch(localISBNParsedResult.getISBN());
      return;
    case 1: 
      openBookSearch(localISBNParsedResult.getISBN());
      return;
    case 2: 
      searchBookContents(localISBNParsedResult.getISBN());
      return;
    }
    openURL(fillInCustomSearchURL(localISBNParsedResult.getISBN()));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\result\ISBNResultHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */