package net.roarsoftware.lastfm.scrobble;

import net.roarsoftware.util.StringUtilities;

public class SubmissionData
{
  private String album;
  private String artist;
  private int length;
  private Rating rating;
  private String recommendationKey;
  private Source source;
  private long startTime;
  private String track;
  private int tracknumber;
  
  public SubmissionData(String paramString)
  {
    String[] arrayOfString = paramString.split("&", 9);
    this.artist = StringUtilities.decode(arrayOfString[0]);
    this.track = StringUtilities.decode(arrayOfString[1]);
    long l;
    if (arrayOfString[2].length() == 0)
    {
      l = 0L;
      this.startTime = l;
      this.source = Source.valueOf(arrayOfString[3]);
      if (arrayOfString[4].length() != 0) {
        break label176;
      }
      paramString = null;
      label83:
      this.recommendationKey = paramString;
      if (arrayOfString[5].length() != 0) {
        break label184;
      }
      paramString = null;
      label100:
      this.rating = paramString;
      if (arrayOfString[6].length() != 0) {
        break label195;
      }
      i = -1;
      label118:
      this.length = i;
      if (arrayOfString[7].length() != 0) {
        break label210;
      }
      paramString = (String)localObject;
      label137:
      this.album = paramString;
      if (arrayOfString[8].length() != 0) {
        break label222;
      }
    }
    label176:
    label184:
    label195:
    label210:
    label222:
    for (int i = j;; i = Integer.valueOf(arrayOfString[8]).intValue())
    {
      this.tracknumber = i;
      return;
      l = Long.valueOf(arrayOfString[2]).longValue();
      break;
      paramString = arrayOfString[4];
      break label83;
      paramString = Rating.valueOf(arrayOfString[5]);
      break label100;
      i = Integer.valueOf(arrayOfString[6]).intValue();
      break label118;
      paramString = StringUtilities.decode(arrayOfString[7]);
      break label137;
    }
  }
  
  public SubmissionData(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, Source paramSource, long paramLong)
  {
    this(paramString1, paramString2, paramString3, paramInt1, paramInt2, paramSource, null, paramLong);
  }
  
  public SubmissionData(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, Source paramSource, Rating paramRating, long paramLong)
  {
    this.artist = paramString1;
    this.track = paramString2;
    this.album = paramString3;
    this.length = paramInt1;
    this.tracknumber = paramInt2;
    this.source = paramSource;
    this.rating = paramRating;
    this.startTime = paramLong;
  }
  
  public SubmissionData(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, Source paramSource, Rating paramRating, long paramLong, String paramString4)
  {
    this(paramString1, paramString2, paramString3, paramInt1, paramInt2, paramSource, paramRating, paramLong);
    this.recommendationKey = paramString4;
  }
  
  public String toString()
  {
    String str1;
    String str6;
    String str7;
    String str8;
    if (this.album != null)
    {
      str1 = this.album;
      str6 = StringUtilities.encode(str1);
      str7 = StringUtilities.encode(this.artist);
      str8 = StringUtilities.encode(this.track);
      if (this.length != -1) {
        break label198;
      }
      str1 = "";
      label47:
      if (this.tracknumber != -1) {
        break label209;
      }
    }
    label198:
    label209:
    for (String str2 = "";; str2 = String.valueOf(this.tracknumber))
    {
      String str3 = "";
      if (this.rating != null) {
        str3 = this.rating.name();
      }
      String str5 = "";
      String str4 = str5;
      if (this.recommendationKey != null)
      {
        str4 = str5;
        if (this.source == Source.LAST_FM)
        {
          str4 = str5;
          if (this.recommendationKey.length() == 5) {
            str4 = this.recommendationKey;
          }
        }
      }
      return String.format("%s&%s&%s&%s&%s&%s&%s&%s&%s", new Object[] { str7, str8, Long.valueOf(this.startTime), this.source.name(), str4, str3, str1, str6, str2 });
      str1 = "";
      break;
      str1 = String.valueOf(this.length);
      break label47;
    }
  }
  
  String toString(String paramString, int paramInt)
  {
    String str1;
    String str6;
    String str7;
    String str8;
    if (this.album != null)
    {
      str1 = this.album;
      str6 = StringUtilities.encode(str1);
      str7 = StringUtilities.encode(this.artist);
      str8 = StringUtilities.encode(this.track);
      if (this.length != -1) {
        break label228;
      }
      str1 = "";
      label47:
      if (this.tracknumber != -1) {
        break label239;
      }
    }
    label228:
    label239:
    for (String str2 = "";; str2 = String.valueOf(this.tracknumber))
    {
      String str3 = "";
      if (this.rating != null) {
        str3 = this.rating.getCode();
      }
      String str5 = "";
      String str4 = str5;
      if (this.recommendationKey != null)
      {
        str4 = str5;
        if (this.source == Source.LAST_FM)
        {
          str4 = str5;
          if (this.recommendationKey.length() == 5) {
            str4 = this.recommendationKey;
          }
        }
      }
      return String.format("s=%s&a[%10$d]=%s&t[%10$d]=%s&i[%10$d]=%s&o[%10$d]=%s&r[%10$d]=%s&l[%10$d]=%s&b[%10$d]=%s&n[%10$d]=%s&m[%10$d]=", new Object[] { paramString, str7, str8, Long.valueOf(this.startTime), this.source.getCode() + str4, str3, str1, str6, str2, Integer.valueOf(paramInt) });
      str1 = "";
      break;
      str1 = String.valueOf(this.length);
      break label47;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\scrobble\SubmissionData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */