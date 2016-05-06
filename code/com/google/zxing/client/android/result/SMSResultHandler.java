package com.google.zxing.client.android.result;

import android.app.Activity;
import android.telephony.PhoneNumberUtils;
import com.google.zxing.client.android.R.string;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.SMSParsedResult;

public final class SMSResultHandler
  extends ResultHandler
{
  private static final int[] buttons = { R.string.button_sms, R.string.button_mms };
  
  public SMSResultHandler(Activity paramActivity, ParsedResult paramParsedResult)
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
  
  public CharSequence getDisplayContents()
  {
    SMSParsedResult localSMSParsedResult = (SMSParsedResult)getResult();
    StringBuilder localStringBuilder = new StringBuilder(50);
    String[] arrayOfString1 = localSMSParsedResult.getNumbers();
    String[] arrayOfString2 = new String[arrayOfString1.length];
    int i = 0;
    while (i < arrayOfString1.length)
    {
      arrayOfString2[i] = PhoneNumberUtils.formatNumber(arrayOfString1[i]);
      i += 1;
    }
    ParsedResult.maybeAppend(arrayOfString2, localStringBuilder);
    ParsedResult.maybeAppend(localSMSParsedResult.getSubject(), localStringBuilder);
    ParsedResult.maybeAppend(localSMSParsedResult.getBody(), localStringBuilder);
    return localStringBuilder.toString();
  }
  
  public int getDisplayTitle()
  {
    return R.string.result_sms;
  }
  
  public void handleButtonPress(int paramInt)
  {
    SMSParsedResult localSMSParsedResult = (SMSParsedResult)getResult();
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      sendSMS(localSMSParsedResult.getNumbers()[0], localSMSParsedResult.getBody());
      return;
    }
    sendMMS(localSMSParsedResult.getNumbers()[0], localSMSParsedResult.getSubject(), localSMSParsedResult.getBody());
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\result\SMSResultHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */