package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;

final class FieldParser
{
  private static final Object[][] FOUR_DIGIT_DATA_LENGTH;
  private static final Object[][] THREE_DIGIT_DATA_LENGTH;
  private static final Object[][] THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH;
  private static final Object[][] TWO_DIGIT_DATA_LENGTH;
  private static final Object VARIABLE_LENGTH = new Object();
  
  static
  {
    Object localObject7 = { "10", VARIABLE_LENGTH, Integer.valueOf(20) };
    Object localObject8 = { "11", Integer.valueOf(6) };
    Object[] arrayOfObject1 = { "13", Integer.valueOf(6) };
    Object[] arrayOfObject2 = { "20", Integer.valueOf(2) };
    Object[] arrayOfObject3 = { "21", VARIABLE_LENGTH, Integer.valueOf(20) };
    Object localObject1 = VARIABLE_LENGTH;
    Object[] arrayOfObject4 = { "30", VARIABLE_LENGTH, Integer.valueOf(8) };
    Object localObject2 = VARIABLE_LENGTH;
    Object[] arrayOfObject5 = { "90", VARIABLE_LENGTH, Integer.valueOf(30) };
    Object localObject3 = VARIABLE_LENGTH;
    Object[] arrayOfObject6 = { "92", VARIABLE_LENGTH, Integer.valueOf(30) };
    Object localObject4 = VARIABLE_LENGTH;
    Object localObject5 = VARIABLE_LENGTH;
    Object[] arrayOfObject7 = { "95", VARIABLE_LENGTH, Integer.valueOf(30) };
    Object[] arrayOfObject8 = { "96", VARIABLE_LENGTH, Integer.valueOf(30) };
    Object localObject6 = VARIABLE_LENGTH;
    Object[] arrayOfObject9 = { "98", VARIABLE_LENGTH, Integer.valueOf(30) };
    Object[] arrayOfObject10 = { "99", VARIABLE_LENGTH, Integer.valueOf(30) };
    TWO_DIGIT_DATA_LENGTH = new Object[][] { { "00", Integer.valueOf(18) }, { "01", Integer.valueOf(14) }, { "02", Integer.valueOf(14) }, localObject7, localObject8, { "12", Integer.valueOf(6) }, arrayOfObject1, { "15", Integer.valueOf(6) }, { "17", Integer.valueOf(6) }, arrayOfObject2, arrayOfObject3, { "22", localObject1, Integer.valueOf(29) }, arrayOfObject4, { "37", localObject2, Integer.valueOf(8) }, arrayOfObject5, { "91", localObject3, Integer.valueOf(30) }, arrayOfObject6, { "93", localObject4, Integer.valueOf(30) }, { "94", localObject5, Integer.valueOf(30) }, arrayOfObject7, arrayOfObject8, { "97", localObject6, Integer.valueOf(30) }, arrayOfObject9, arrayOfObject10 };
    localObject3 = new Object[] { "240", VARIABLE_LENGTH, Integer.valueOf(30) };
    localObject4 = new Object[] { "241", VARIABLE_LENGTH, Integer.valueOf(30) };
    localObject5 = new Object[] { "242", VARIABLE_LENGTH, Integer.valueOf(6) };
    localObject6 = new Object[] { "250", VARIABLE_LENGTH, Integer.valueOf(30) };
    localObject7 = new Object[] { "251", VARIABLE_LENGTH, Integer.valueOf(30) };
    localObject8 = new Object[] { "253", VARIABLE_LENGTH, Integer.valueOf(17) };
    localObject1 = VARIABLE_LENGTH;
    arrayOfObject1 = new Object[] { "400", VARIABLE_LENGTH, Integer.valueOf(30) };
    arrayOfObject2 = new Object[] { "401", VARIABLE_LENGTH, Integer.valueOf(30) };
    arrayOfObject3 = new Object[] { "402", Integer.valueOf(17) };
    arrayOfObject4 = new Object[] { "403", VARIABLE_LENGTH, Integer.valueOf(30) };
    arrayOfObject5 = new Object[] { "410", Integer.valueOf(13) };
    arrayOfObject6 = new Object[] { "411", Integer.valueOf(13) };
    arrayOfObject7 = new Object[] { "413", Integer.valueOf(13) };
    arrayOfObject8 = new Object[] { "414", Integer.valueOf(13) };
    arrayOfObject9 = new Object[] { "420", VARIABLE_LENGTH, Integer.valueOf(20) };
    arrayOfObject10 = new Object[] { "421", VARIABLE_LENGTH, Integer.valueOf(15) };
    localObject2 = VARIABLE_LENGTH;
    Object[] arrayOfObject11 = { "424", Integer.valueOf(3) };
    Object[] arrayOfObject12 = { "425", Integer.valueOf(3) };
    Object[] arrayOfObject13 = { "426", Integer.valueOf(3) };
    THREE_DIGIT_DATA_LENGTH = new Object[][] { localObject3, localObject4, localObject5, localObject6, localObject7, localObject8, { "254", localObject1, Integer.valueOf(20) }, arrayOfObject1, arrayOfObject2, arrayOfObject3, arrayOfObject4, arrayOfObject5, arrayOfObject6, { "412", Integer.valueOf(13) }, arrayOfObject7, arrayOfObject8, arrayOfObject9, arrayOfObject10, { "422", Integer.valueOf(3) }, { "423", localObject2, Integer.valueOf(15) }, arrayOfObject11, arrayOfObject12, arrayOfObject13 };
    localObject5 = new Object[] { "314", Integer.valueOf(6) };
    localObject6 = new Object[] { "330", Integer.valueOf(6) };
    localObject7 = new Object[] { "349", Integer.valueOf(6) };
    localObject8 = new Object[] { "354", Integer.valueOf(6) };
    arrayOfObject1 = new Object[] { "360", Integer.valueOf(6) };
    arrayOfObject2 = new Object[] { "361", Integer.valueOf(6) };
    localObject1 = VARIABLE_LENGTH;
    localObject2 = VARIABLE_LENGTH;
    localObject3 = VARIABLE_LENGTH;
    arrayOfObject3 = new Object[] { "393", VARIABLE_LENGTH, Integer.valueOf(18) };
    localObject4 = VARIABLE_LENGTH;
    THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH = new Object[][] { { "310", Integer.valueOf(6) }, { "311", Integer.valueOf(6) }, { "312", Integer.valueOf(6) }, { "313", Integer.valueOf(6) }, localObject5, { "315", Integer.valueOf(6) }, { "316", Integer.valueOf(6) }, { "320", Integer.valueOf(6) }, { "321", Integer.valueOf(6) }, { "322", Integer.valueOf(6) }, { "323", Integer.valueOf(6) }, { "324", Integer.valueOf(6) }, { "325", Integer.valueOf(6) }, { "326", Integer.valueOf(6) }, { "327", Integer.valueOf(6) }, { "328", Integer.valueOf(6) }, { "329", Integer.valueOf(6) }, localObject6, { "331", Integer.valueOf(6) }, { "332", Integer.valueOf(6) }, { "333", Integer.valueOf(6) }, { "334", Integer.valueOf(6) }, { "335", Integer.valueOf(6) }, { "336", Integer.valueOf(6) }, { "340", Integer.valueOf(6) }, { "341", Integer.valueOf(6) }, { "342", Integer.valueOf(6) }, { "343", Integer.valueOf(6) }, { "344", Integer.valueOf(6) }, { "345", Integer.valueOf(6) }, { "346", Integer.valueOf(6) }, { "347", Integer.valueOf(6) }, { "348", Integer.valueOf(6) }, localObject7, { "350", Integer.valueOf(6) }, { "351", Integer.valueOf(6) }, { "352", Integer.valueOf(6) }, { "353", Integer.valueOf(6) }, localObject8, { "355", Integer.valueOf(6) }, { "356", Integer.valueOf(6) }, { "357", Integer.valueOf(6) }, arrayOfObject1, arrayOfObject2, { "362", Integer.valueOf(6) }, { "363", Integer.valueOf(6) }, { "364", Integer.valueOf(6) }, { "365", Integer.valueOf(6) }, { "366", Integer.valueOf(6) }, { "367", Integer.valueOf(6) }, { "368", Integer.valueOf(6) }, { "369", Integer.valueOf(6) }, { "390", localObject1, Integer.valueOf(15) }, { "391", localObject2, Integer.valueOf(18) }, { "392", localObject3, Integer.valueOf(15) }, arrayOfObject3, { "703", localObject4, Integer.valueOf(30) } };
    localObject1 = VARIABLE_LENGTH;
    localObject2 = VARIABLE_LENGTH;
    localObject3 = VARIABLE_LENGTH;
    localObject4 = VARIABLE_LENGTH;
    localObject5 = VARIABLE_LENGTH;
    localObject6 = VARIABLE_LENGTH;
    localObject7 = VARIABLE_LENGTH;
    localObject8 = VARIABLE_LENGTH;
    FOUR_DIGIT_DATA_LENGTH = new Object[][] { { "7001", Integer.valueOf(13) }, { "7002", localObject1, Integer.valueOf(30) }, { "7003", Integer.valueOf(10) }, { "8001", Integer.valueOf(14) }, { "8002", localObject2, Integer.valueOf(20) }, { "8003", localObject3, Integer.valueOf(30) }, { "8004", localObject4, Integer.valueOf(30) }, { "8005", Integer.valueOf(6) }, { "8006", Integer.valueOf(18) }, { "8007", localObject5, Integer.valueOf(30) }, { "8008", localObject6, Integer.valueOf(12) }, { "8018", Integer.valueOf(18) }, { "8020", localObject7, Integer.valueOf(25) }, { "8100", Integer.valueOf(6) }, { "8101", Integer.valueOf(10) }, { "8102", Integer.valueOf(2) }, { "8110", localObject8, Integer.valueOf(30) } };
  }
  
  static String parseFieldsInGeneralPurpose(String paramString)
    throws NotFoundException
  {
    if (paramString.length() == 0) {
      return null;
    }
    if (paramString.length() < 2) {
      throw NotFoundException.getNotFoundInstance();
    }
    String str = paramString.substring(0, 2);
    Object[][] arrayOfObject = TWO_DIGIT_DATA_LENGTH;
    int j = arrayOfObject.length;
    int i = 0;
    Object[] arrayOfObject1;
    for (;;)
    {
      if (i >= j)
      {
        if (paramString.length() >= 3) {
          break;
        }
        throw NotFoundException.getNotFoundInstance();
      }
      arrayOfObject1 = arrayOfObject[i];
      if (arrayOfObject1[0].equals(str))
      {
        if (arrayOfObject1[1] == VARIABLE_LENGTH) {
          return processVariableAI(2, ((Integer)arrayOfObject1[2]).intValue(), paramString);
        }
        return processFixedAI(2, ((Integer)arrayOfObject1[1]).intValue(), paramString);
      }
      i += 1;
    }
    str = paramString.substring(0, 3);
    arrayOfObject = THREE_DIGIT_DATA_LENGTH;
    j = arrayOfObject.length;
    i = 0;
    if (i >= j)
    {
      arrayOfObject = THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH;
      j = arrayOfObject.length;
      i = 0;
    }
    for (;;)
    {
      if (i >= j)
      {
        if (paramString.length() >= 4) {
          break label305;
        }
        throw NotFoundException.getNotFoundInstance();
        arrayOfObject1 = arrayOfObject[i];
        if (arrayOfObject1[0].equals(str))
        {
          if (arrayOfObject1[1] == VARIABLE_LENGTH) {
            return processVariableAI(3, ((Integer)arrayOfObject1[2]).intValue(), paramString);
          }
          return processFixedAI(3, ((Integer)arrayOfObject1[1]).intValue(), paramString);
        }
        i += 1;
        break;
      }
      arrayOfObject1 = arrayOfObject[i];
      if (arrayOfObject1[0].equals(str))
      {
        if (arrayOfObject1[1] == VARIABLE_LENGTH) {
          return processVariableAI(4, ((Integer)arrayOfObject1[2]).intValue(), paramString);
        }
        return processFixedAI(4, ((Integer)arrayOfObject1[1]).intValue(), paramString);
      }
      i += 1;
    }
    label305:
    str = paramString.substring(0, 4);
    arrayOfObject = FOUR_DIGIT_DATA_LENGTH;
    j = arrayOfObject.length;
    i = 0;
    for (;;)
    {
      if (i >= j) {
        throw NotFoundException.getNotFoundInstance();
      }
      arrayOfObject1 = arrayOfObject[i];
      if (arrayOfObject1[0].equals(str))
      {
        if (arrayOfObject1[1] == VARIABLE_LENGTH) {
          return processVariableAI(4, ((Integer)arrayOfObject1[2]).intValue(), paramString);
        }
        return processFixedAI(4, ((Integer)arrayOfObject1[1]).intValue(), paramString);
      }
      i += 1;
    }
  }
  
  private static String processFixedAI(int paramInt1, int paramInt2, String paramString)
    throws NotFoundException
  {
    if (paramString.length() < paramInt1) {
      throw NotFoundException.getNotFoundInstance();
    }
    String str1 = paramString.substring(0, paramInt1);
    if (paramString.length() < paramInt1 + paramInt2) {
      throw NotFoundException.getNotFoundInstance();
    }
    String str2 = paramString.substring(paramInt1, paramInt1 + paramInt2);
    paramString = paramString.substring(paramInt1 + paramInt2);
    str1 = '(' + str1 + ')' + str2;
    paramString = parseFieldsInGeneralPurpose(paramString);
    if (paramString == null) {
      return str1;
    }
    return str1 + paramString;
  }
  
  private static String processVariableAI(int paramInt1, int paramInt2, String paramString)
    throws NotFoundException
  {
    String str1 = paramString.substring(0, paramInt1);
    if (paramString.length() < paramInt1 + paramInt2) {}
    for (paramInt2 = paramString.length();; paramInt2 = paramInt1 + paramInt2)
    {
      String str2 = paramString.substring(paramInt1, paramInt2);
      paramString = paramString.substring(paramInt2);
      str1 = '(' + str1 + ')' + str2;
      paramString = parseFieldsInGeneralPurpose(paramString);
      if (paramString != null) {
        break;
      }
      return str1;
    }
    return str1 + paramString;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\rss\expanded\decoders\FieldParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */