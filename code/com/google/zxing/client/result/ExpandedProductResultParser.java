package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.HashMap;
import java.util.Map;

public final class ExpandedProductResultParser
  extends ResultParser
{
  private static String findAIvalue(int paramInt, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramString.charAt(paramInt) != '(') {
      return null;
    }
    paramString = paramString.substring(paramInt + 1);
    paramInt = 0;
    for (;;)
    {
      if (paramInt >= paramString.length()) {
        return localStringBuilder.toString();
      }
      char c = paramString.charAt(paramInt);
      if (c == ')') {
        return localStringBuilder.toString();
      }
      if ((c < '0') || (c > '9')) {
        break;
      }
      localStringBuilder.append(c);
      paramInt += 1;
    }
  }
  
  private static String findValue(int paramInt, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramString = paramString.substring(paramInt);
    paramInt = 0;
    if (paramInt >= paramString.length()) {}
    char c;
    do
    {
      return localStringBuilder.toString();
      c = paramString.charAt(paramInt);
      if (c != '(') {
        break;
      }
    } while (findAIvalue(paramInt, paramString) != null);
    localStringBuilder.append('(');
    for (;;)
    {
      paramInt += 1;
      break;
      localStringBuilder.append(c);
    }
  }
  
  public ExpandedProductParsedResult parse(Result paramResult)
  {
    if (paramResult.getBarcodeFormat() != BarcodeFormat.RSS_EXPANDED) {
      return null;
    }
    String str5 = getMassagedText(paramResult);
    if (str5 == null) {
      return null;
    }
    Object localObject9 = null;
    Object localObject8 = null;
    Object localObject7 = null;
    Object localObject6 = null;
    Object localObject5 = null;
    Object localObject4 = null;
    Object localObject3 = null;
    Object localObject2 = null;
    String str2 = null;
    String str3 = null;
    Object localObject1 = null;
    String str1 = null;
    String str4 = null;
    HashMap localHashMap = new HashMap();
    int i = 0;
    for (;;)
    {
      if (i >= str5.length()) {
        return new ExpandedProductParsedResult((String)localObject9, (String)localObject8, (String)localObject7, (String)localObject6, (String)localObject5, (String)localObject4, (String)localObject3, (String)localObject2, str2, str3, (String)localObject1, str1, str4, localHashMap);
      }
      String str6 = findAIvalue(i, str5);
      if (str6 == null) {
        return null;
      }
      i += str6.length() + 2;
      paramResult = findValue(i, str5);
      i += paramResult.length();
      if ("00".equals(str6))
      {
        localObject8 = paramResult;
      }
      else if ("01".equals(str6))
      {
        localObject9 = paramResult;
      }
      else if ("10".equals(str6))
      {
        localObject7 = paramResult;
      }
      else if ("11".equals(str6))
      {
        localObject6 = paramResult;
      }
      else if ("13".equals(str6))
      {
        localObject5 = paramResult;
      }
      else if ("15".equals(str6))
      {
        localObject4 = paramResult;
      }
      else if ("17".equals(str6))
      {
        localObject3 = paramResult;
      }
      else if (("3100".equals(str6)) || ("3101".equals(str6)) || ("3102".equals(str6)) || ("3103".equals(str6)) || ("3104".equals(str6)) || ("3105".equals(str6)) || ("3106".equals(str6)) || ("3107".equals(str6)) || ("3108".equals(str6)) || ("3109".equals(str6)))
      {
        str2 = "KG";
        str3 = str6.substring(3);
        localObject2 = paramResult;
      }
      else if (("3200".equals(str6)) || ("3201".equals(str6)) || ("3202".equals(str6)) || ("3203".equals(str6)) || ("3204".equals(str6)) || ("3205".equals(str6)) || ("3206".equals(str6)) || ("3207".equals(str6)) || ("3208".equals(str6)) || ("3209".equals(str6)))
      {
        str2 = "LB";
        str3 = str6.substring(3);
        localObject2 = paramResult;
      }
      else if (("3920".equals(str6)) || ("3921".equals(str6)) || ("3922".equals(str6)) || ("3923".equals(str6)))
      {
        str1 = str6.substring(3);
        localObject1 = paramResult;
      }
      else if (("3930".equals(str6)) || ("3931".equals(str6)) || ("3932".equals(str6)) || ("3933".equals(str6)))
      {
        if (paramResult.length() < 4) {
          return null;
        }
        localObject1 = paramResult.substring(3);
        str4 = paramResult.substring(0, 3);
        str1 = str6.substring(3);
      }
      else
      {
        localHashMap.put(str6, paramResult);
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\ExpandedProductResultParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */