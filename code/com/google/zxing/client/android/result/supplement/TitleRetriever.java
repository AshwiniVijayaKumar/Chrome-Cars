package com.google.zxing.client.android.result.supplement;

import android.widget.TextView;
import com.google.zxing.client.android.HttpHelper;
import com.google.zxing.client.android.HttpHelper.ContentType;
import com.google.zxing.client.android.history.HistoryManager;
import com.google.zxing.client.result.URIParsedResult;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class TitleRetriever
  extends SupplementalInfoRetriever
{
  private static final int MAX_TITLE_LEN = 100;
  private static final Pattern TITLE_PATTERN = Pattern.compile("<title>([^<]+)");
  private final String httpUrl;
  
  TitleRetriever(TextView paramTextView, URIParsedResult paramURIParsedResult, HistoryManager paramHistoryManager)
  {
    super(paramTextView, paramHistoryManager);
    this.httpUrl = paramURIParsedResult.getURI();
  }
  
  void retrieveSupplementalInfo()
  {
    try
    {
      Object localObject = HttpHelper.downloadViaHttp(this.httpUrl, HttpHelper.ContentType.HTML, 4096);
      if ((localObject != null) && (((CharSequence)localObject).length() > 0))
      {
        localObject = TITLE_PATTERN.matcher((CharSequence)localObject);
        if (((Matcher)localObject).find())
        {
          String str1 = ((Matcher)localObject).group(1);
          if ((str1 != null) && (str1.length() > 0))
          {
            localObject = str1;
            if (str1.length() > 100) {
              localObject = str1.substring(0, 100) + "...";
            }
            str1 = this.httpUrl;
            String str2 = this.httpUrl;
            append(str1, null, new String[] { localObject }, str2);
          }
        }
      }
      return;
    }
    catch (IOException localIOException) {}
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\result\supplement\TitleRetriever.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */