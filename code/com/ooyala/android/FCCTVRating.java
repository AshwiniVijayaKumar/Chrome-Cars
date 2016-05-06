package com.ooyala.android;

import android.text.TextUtils;
import java.util.Arrays;

public class FCCTVRating
{
  public final String ageRestriction;
  public final String clickthrough;
  public final String labels;
  
  public FCCTVRating(String paramString1, String paramString2, String paramString3)
  {
    String str = paramString1;
    if (paramString1 != null) {
      str = paramString1.toUpperCase().replace("TV-", "");
    }
    this.ageRestriction = str;
    paramString1 = paramString2;
    if (paramString2 != null)
    {
      paramString1 = paramString2.toUpperCase().replace(",", " ").replace(";", " ").split("\\s+");
      Arrays.sort(paramString1, String.CASE_INSENSITIVE_ORDER);
      paramString1 = TextUtils.join("", paramString1);
    }
    this.labels = paramString1;
    this.clickthrough = paramString3;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      do
      {
        return true;
        if (paramObject == null) {
          return false;
        }
        if (getClass() != paramObject.getClass()) {
          return false;
        }
        paramObject = (FCCTVRating)paramObject;
        if (this.ageRestriction == null)
        {
          if (((FCCTVRating)paramObject).ageRestriction != null) {
            return false;
          }
        }
        else if (!this.ageRestriction.equals(((FCCTVRating)paramObject).ageRestriction)) {
          return false;
        }
        if (this.clickthrough == null)
        {
          if (((FCCTVRating)paramObject).clickthrough != null) {
            return false;
          }
        }
        else if (!this.clickthrough.equals(((FCCTVRating)paramObject).clickthrough)) {
          return false;
        }
        if (this.labels != null) {
          break;
        }
      } while (((FCCTVRating)paramObject).labels == null);
      return false;
    } while (this.labels.equals(((FCCTVRating)paramObject).labels));
    return false;
  }
  
  public int hashCode()
  {
    int k = 0;
    int i;
    int j;
    if (this.ageRestriction == null)
    {
      i = 0;
      if (this.clickthrough != null) {
        break label53;
      }
      j = 0;
      label20:
      if (this.labels != null) {
        break label64;
      }
    }
    for (;;)
    {
      return ((i + 31) * 31 + j) * 31 + k;
      i = this.ageRestriction.hashCode();
      break;
      label53:
      j = this.clickthrough.hashCode();
      break label20;
      label64:
      k = this.labels.hashCode();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\FCCTVRating.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */