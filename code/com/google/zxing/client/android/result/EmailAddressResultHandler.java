package com.google.zxing.client.android.result;

import android.app.Activity;
import com.google.zxing.client.android.R.string;
import com.google.zxing.client.result.EmailAddressParsedResult;
import com.google.zxing.client.result.ParsedResult;

public final class EmailAddressResultHandler
  extends ResultHandler
{
  private static final int[] buttons = { R.string.button_email, R.string.button_add_contact };
  
  public EmailAddressResultHandler(Activity paramActivity, ParsedResult paramParsedResult)
  {
    super(paramActivity, paramParsedResult);
  }
  
  public int getButtonCount()
  {
    return buttons.length;
  }
  
  public int getButtonText(int paramInt)
  {
    return buttons[paramInt];
  }
  
  public int getDisplayTitle()
  {
    return R.string.result_email_address;
  }
  
  public void handleButtonPress(int paramInt)
  {
    EmailAddressParsedResult localEmailAddressParsedResult = (EmailAddressParsedResult)getResult();
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      sendEmailFromUri(localEmailAddressParsedResult.getMailtoURI(), localEmailAddressParsedResult.getEmailAddress(), localEmailAddressParsedResult.getSubject(), localEmailAddressParsedResult.getBody());
      return;
    }
    addEmailOnlyContact(new String[] { localEmailAddressParsedResult.getEmailAddress() }, null);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\result\EmailAddressResultHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */