package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class VCardResultParser
  extends ResultParser
{
  private static final Pattern BEGIN_VCARD = Pattern.compile("BEGIN:VCARD", 2);
  private static final Pattern CR_LF_SPACE_TAB;
  private static final Pattern EQUALS = Pattern.compile("=");
  private static final Pattern NEWLINE_ESCAPE;
  private static final Pattern SEMICOLON = Pattern.compile(";");
  private static final Pattern UNESCAPED_SEMICOLONS = Pattern.compile("(?<!\\\\);+");
  private static final Pattern VCARD_ESCAPES;
  private static final Pattern VCARD_LIKE_DATE = Pattern.compile("\\d{4}-?\\d{2}-?\\d{2}");
  
  static
  {
    CR_LF_SPACE_TAB = Pattern.compile("\r\n[ \t]");
    NEWLINE_ESCAPE = Pattern.compile("\\\\[nN]");
    VCARD_ESCAPES = Pattern.compile("\\\\([,;\\\\])");
  }
  
  private static String decodeQuotedPrintable(CharSequence paramCharSequence, String paramString)
  {
    int k = paramCharSequence.length();
    StringBuilder localStringBuilder = new StringBuilder(k);
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    int i = 0;
    if (i >= k)
    {
      maybeAppendFragment(localByteArrayOutputStream, paramString, localStringBuilder);
      return localStringBuilder.toString();
    }
    char c1 = paramCharSequence.charAt(i);
    int j = i;
    switch (c1)
    {
    default: 
      maybeAppendFragment(localByteArrayOutputStream, paramString, localStringBuilder);
      localStringBuilder.append(c1);
      j = i;
    }
    for (;;)
    {
      i = j + 1;
      break;
      j = i;
      if (i < k - 2)
      {
        c1 = paramCharSequence.charAt(i + 1);
        j = i;
        if (c1 != '\r')
        {
          j = i;
          if (c1 != '\n')
          {
            char c2 = paramCharSequence.charAt(i + 2);
            j = parseHexDigit(c1);
            int m = parseHexDigit(c2);
            if ((j >= 0) && (m >= 0)) {
              localByteArrayOutputStream.write((j << 4) + m);
            }
            j = i + 2;
          }
        }
      }
    }
  }
  
  private static void formatNames(Iterable<List<String>> paramIterable)
  {
    if (paramIterable != null)
    {
      paramIterable = paramIterable.iterator();
      if (paramIterable.hasNext()) {}
    }
    else
    {
      return;
    }
    List localList = (List)paramIterable.next();
    Object localObject = (String)localList.get(0);
    String[] arrayOfString = new String[5];
    int j = 0;
    int i = 0;
    for (;;)
    {
      int k;
      if (i < arrayOfString.length - 1)
      {
        k = ((String)localObject).indexOf(';', j);
        if (k > 0) {}
      }
      else
      {
        arrayOfString[i] = ((String)localObject).substring(j);
        localObject = new StringBuilder(100);
        maybeAppendComponent(arrayOfString, 3, (StringBuilder)localObject);
        maybeAppendComponent(arrayOfString, 1, (StringBuilder)localObject);
        maybeAppendComponent(arrayOfString, 2, (StringBuilder)localObject);
        maybeAppendComponent(arrayOfString, 0, (StringBuilder)localObject);
        maybeAppendComponent(arrayOfString, 4, (StringBuilder)localObject);
        localList.set(0, ((StringBuilder)localObject).toString().trim());
        break;
      }
      arrayOfString[i] = ((String)localObject).substring(j, k);
      i += 1;
      j = k + 1;
    }
  }
  
  private static boolean isLikeVCardDate(CharSequence paramCharSequence)
  {
    return (paramCharSequence == null) || (VCARD_LIKE_DATE.matcher(paramCharSequence).matches());
  }
  
  static List<String> matchSingleVCardPrefixedField(CharSequence paramCharSequence, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramCharSequence = matchVCardPrefixedField(paramCharSequence, paramString, paramBoolean1, paramBoolean2);
    if ((paramCharSequence == null) || (paramCharSequence.isEmpty())) {
      return null;
    }
    return (List)paramCharSequence.get(0);
  }
  
  static List<List<String>> matchVCardPrefixedField(CharSequence paramCharSequence, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject3 = null;
    int i = 0;
    int m = paramString.length();
    for (;;)
    {
      if (i >= m) {}
      do
      {
        return (List<List<String>>)localObject3;
        localObject1 = Pattern.compile("(?:^|\n)" + paramCharSequence + "(?:;([^:]*))?:", 2).matcher(paramString);
        j = i;
        if (i > 0) {
          j = i - 1;
        }
      } while (!((Matcher)localObject1).find(j));
      int n = ((Matcher)localObject1).end(0);
      Object localObject6 = ((Matcher)localObject1).group(1);
      Object localObject2 = null;
      Object localObject4 = null;
      int j = 0;
      i = 0;
      Object localObject5 = null;
      Object localObject1 = null;
      if (localObject6 != null)
      {
        localObject6 = SEMICOLON.split((CharSequence)localObject6);
        int i1 = localObject6.length;
        j = 0;
        localObject2 = localObject4;
        if (j >= i1)
        {
          localObject5 = localObject1;
          j = i;
        }
      }
      else
      {
        i = n;
      }
      label158:
      int k;
      for (;;)
      {
        k = paramString.indexOf('\n', i);
        if (k < 0) {}
        label307:
        do
        {
          if (k >= 0) {
            break label434;
          }
          i = m;
          break;
          localObject5 = localObject6[j];
          localObject4 = localObject2;
          if (localObject2 == null) {
            localObject4 = new ArrayList(1);
          }
          ((List)localObject4).add(localObject5);
          localObject2 = EQUALS.split((CharSequence)localObject5, 2);
          k = i;
          localObject5 = localObject1;
          String str;
          if (localObject2.length > 1)
          {
            str = localObject2[0];
            localObject2 = localObject2[1];
            if ((!"ENCODING".equalsIgnoreCase(str)) || (!"QUOTED-PRINTABLE".equalsIgnoreCase((String)localObject2))) {
              break label307;
            }
            k = 1;
            localObject5 = localObject1;
          }
          for (;;)
          {
            j += 1;
            localObject2 = localObject4;
            i = k;
            localObject1 = localObject5;
            break;
            k = i;
            localObject5 = localObject1;
            if ("CHARSET".equalsIgnoreCase(str))
            {
              localObject5 = localObject2;
              k = i;
            }
          }
          if ((k < paramString.length() - 1) && ((paramString.charAt(k + 1) == ' ') || (paramString.charAt(k + 1) == '\t')))
          {
            i = k + 2;
            break label158;
          }
        } while ((j == 0) || (((k < 1) || (paramString.charAt(k - 1) != '=')) && ((k < 2) || (paramString.charAt(k - 2) != '='))));
        i = k + 1;
      }
      label434:
      if (k > n)
      {
        localObject4 = localObject3;
        if (localObject3 == null) {
          localObject4 = new ArrayList(1);
        }
        i = k;
        if (k >= 1)
        {
          i = k;
          if (paramString.charAt(k - 1) == '\r') {
            i = k - 1;
          }
        }
        localObject3 = paramString.substring(n, i);
        localObject1 = localObject3;
        if (paramBoolean1) {
          localObject1 = ((String)localObject3).trim();
        }
        if (j != 0)
        {
          localObject3 = decodeQuotedPrintable((CharSequence)localObject1, (String)localObject5);
          localObject1 = localObject3;
          if (paramBoolean2) {
            localObject1 = UNESCAPED_SEMICOLONS.matcher((CharSequence)localObject3).replaceAll("\n").trim();
          }
          label558:
          if (localObject2 != null) {
            break label680;
          }
          localObject2 = new ArrayList(1);
          ((List)localObject2).add(localObject1);
          ((List)localObject4).add(localObject2);
        }
        for (;;)
        {
          i += 1;
          localObject3 = localObject4;
          break;
          localObject3 = localObject1;
          if (paramBoolean2) {
            localObject3 = UNESCAPED_SEMICOLONS.matcher((CharSequence)localObject1).replaceAll("\n").trim();
          }
          localObject1 = CR_LF_SPACE_TAB.matcher((CharSequence)localObject3).replaceAll("");
          localObject1 = NEWLINE_ESCAPE.matcher((CharSequence)localObject1).replaceAll("\n");
          localObject1 = VCARD_ESCAPES.matcher((CharSequence)localObject1).replaceAll("$1");
          break label558;
          label680:
          ((List)localObject2).add(0, localObject1);
          ((List)localObject4).add(localObject2);
        }
      }
      i = k + 1;
    }
  }
  
  private static void maybeAppendComponent(String[] paramArrayOfString, int paramInt, StringBuilder paramStringBuilder)
  {
    if (paramArrayOfString[paramInt] != null)
    {
      paramStringBuilder.append(' ');
      paramStringBuilder.append(paramArrayOfString[paramInt]);
    }
  }
  
  private static void maybeAppendFragment(ByteArrayOutputStream paramByteArrayOutputStream, String paramString, StringBuilder paramStringBuilder)
  {
    byte[] arrayOfByte;
    if (paramByteArrayOutputStream.size() > 0)
    {
      arrayOfByte = paramByteArrayOutputStream.toByteArray();
      if (paramString != null) {
        break label36;
      }
      paramString = new String(arrayOfByte);
    }
    for (;;)
    {
      paramByteArrayOutputStream.reset();
      paramStringBuilder.append(paramString);
      return;
      try
      {
        label36:
        paramString = new String(arrayOfByte, paramString);
      }
      catch (UnsupportedEncodingException paramString)
      {
        paramString = new String(arrayOfByte);
      }
    }
  }
  
  private static String toPrimaryValue(List<String> paramList)
  {
    if ((paramList == null) || (paramList.isEmpty())) {
      return null;
    }
    return (String)paramList.get(0);
  }
  
  private static String[] toPrimaryValues(Collection<List<String>> paramCollection)
  {
    if ((paramCollection == null) || (paramCollection.isEmpty())) {
      return null;
    }
    ArrayList localArrayList = new ArrayList(paramCollection.size());
    Iterator localIterator = paramCollection.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return (String[])localArrayList.toArray(new String[paramCollection.size()]);
      }
      localArrayList.add((String)((List)localIterator.next()).get(0));
    }
  }
  
  private static String[] toTypes(Collection<List<String>> paramCollection)
  {
    if ((paramCollection == null) || (paramCollection.isEmpty())) {
      return null;
    }
    ArrayList localArrayList = new ArrayList(paramCollection.size());
    Iterator localIterator = paramCollection.iterator();
    if (!localIterator.hasNext()) {
      return (String[])localArrayList.toArray(new String[paramCollection.size()]);
    }
    List localList = (List)localIterator.next();
    Object localObject = null;
    int i = 1;
    for (;;)
    {
      if (i >= localList.size()) {}
      for (;;)
      {
        localArrayList.add(localObject);
        break;
        String str = (String)localList.get(i);
        int j = str.indexOf('=');
        if (j < 0)
        {
          localObject = str;
        }
        else
        {
          if (!"TYPE".equalsIgnoreCase(str.substring(0, j))) {
            break label166;
          }
          localObject = str.substring(j + 1);
        }
      }
      label166:
      i += 1;
    }
  }
  
  public AddressBookParsedResult parse(Result paramResult)
  {
    Object localObject2 = getMassagedText(paramResult);
    paramResult = BEGIN_VCARD.matcher((CharSequence)localObject2);
    if ((!paramResult.find()) || (paramResult.start() != 0)) {
      return null;
    }
    Object localObject1 = matchVCardPrefixedField("FN", (String)localObject2, true, false);
    paramResult = (Result)localObject1;
    if (localObject1 == null)
    {
      paramResult = matchVCardPrefixedField("N", (String)localObject2, true, false);
      formatNames(paramResult);
    }
    List localList2 = matchVCardPrefixedField("TEL", (String)localObject2, true, false);
    List localList3 = matchVCardPrefixedField("EMAIL", (String)localObject2, true, false);
    List localList4 = matchSingleVCardPrefixedField("NOTE", (String)localObject2, false, false);
    List localList5 = matchVCardPrefixedField("ADR", (String)localObject2, true, true);
    List localList6 = matchSingleVCardPrefixedField("ORG", (String)localObject2, true, true);
    List localList1 = matchSingleVCardPrefixedField("BDAY", (String)localObject2, true, false);
    localObject1 = localList1;
    if (localList1 != null)
    {
      localObject1 = localList1;
      if (!isLikeVCardDate((CharSequence)localList1.get(0))) {
        localObject1 = null;
      }
    }
    localList1 = matchSingleVCardPrefixedField("TITLE", (String)localObject2, true, false);
    List localList7 = matchSingleVCardPrefixedField("URL", (String)localObject2, true, false);
    localObject2 = matchSingleVCardPrefixedField("IMPP", (String)localObject2, true, false);
    return new AddressBookParsedResult(toPrimaryValues(paramResult), null, toPrimaryValues(localList2), toTypes(localList2), toPrimaryValues(localList3), toTypes(localList3), toPrimaryValue((List)localObject2), toPrimaryValue(localList4), toPrimaryValues(localList5), toTypes(localList5), toPrimaryValue(localList6), toPrimaryValue((List)localObject1), toPrimaryValue(localList1), toPrimaryValue(localList7));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\VCardResultParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */