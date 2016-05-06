package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.b;

public final class d
  extends b
  implements Player
{
  private final a Gl;
  
  public d(DataHolder paramDataHolder, int paramInt)
  {
    this(paramDataHolder, paramInt, null);
  }
  
  public d(DataHolder paramDataHolder, int paramInt, String paramString)
  {
    super(paramDataHolder, paramInt);
    this.Gl = new a(paramString);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return PlayerEntity.a(this, paramObject);
  }
  
  public int fl()
  {
    return getInteger(this.Gl.Gt);
  }
  
  public Player freeze()
  {
    return new PlayerEntity(this);
  }
  
  public String getDisplayName()
  {
    return getString(this.Gl.Gn);
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    a(this.Gl.Gn, paramCharArrayBuffer);
  }
  
  public Uri getHiResImageUri()
  {
    return aa(this.Gl.Gq);
  }
  
  public String getHiResImageUrl()
  {
    return getString(this.Gl.Gr);
  }
  
  public Uri getIconImageUri()
  {
    return aa(this.Gl.Go);
  }
  
  public String getIconImageUrl()
  {
    return getString(this.Gl.Gp);
  }
  
  public long getLastPlayedWithTimestamp()
  {
    if (!hasColumn(this.Gl.Gu)) {
      return -1L;
    }
    return getLong(this.Gl.Gu);
  }
  
  public String getPlayerId()
  {
    return getString(this.Gl.Gm);
  }
  
  public long getRetrievedTimestamp()
  {
    return getLong(this.Gl.Gs);
  }
  
  public boolean hasHiResImage()
  {
    return getHiResImageUri() != null;
  }
  
  public boolean hasIconImage()
  {
    return getIconImageUri() != null;
  }
  
  public int hashCode()
  {
    return PlayerEntity.a(this);
  }
  
  public String toString()
  {
    return PlayerEntity.b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((PlayerEntity)freeze()).writeToParcel(paramParcel, paramInt);
  }
  
  private static final class a
  {
    public final String Gm;
    public final String Gn;
    public final String Go;
    public final String Gp;
    public final String Gq;
    public final String Gr;
    public final String Gs;
    public final String Gt;
    public final String Gu;
    
    public a(String paramString)
    {
      if (TextUtils.isEmpty(paramString))
      {
        this.Gm = "external_player_id";
        this.Gn = "profile_name";
        this.Go = "profile_icon_image_uri";
        this.Gp = "profile_icon_image_url";
        this.Gq = "profile_hi_res_image_uri";
        this.Gr = "profile_hi_res_image_url";
        this.Gs = "last_updated";
        this.Gt = "is_in_circles";
        this.Gu = "played_with_timestamp";
        return;
      }
      this.Gm = (paramString + "external_player_id");
      this.Gn = (paramString + "profile_name");
      this.Go = (paramString + "profile_icon_image_uri");
      this.Gp = (paramString + "profile_icon_image_url");
      this.Gq = (paramString + "profile_hi_res_image_uri");
      this.Gr = (paramString + "profile_hi_res_image_url");
      this.Gs = (paramString + "last_updated");
      this.Gt = (paramString + "is_in_circles");
      this.Gu = (paramString + "played_with_timestamp");
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\games\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */