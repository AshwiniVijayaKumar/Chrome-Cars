package com.google.android.gms.cast;

import android.text.TextUtils;
import com.google.android.gms.internal.dr;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.fp;
import org.json.JSONException;
import org.json.JSONObject;

public final class MediaInfo
{
  public static final int STREAM_TYPE_BUFFERED = 1;
  public static final int STREAM_TYPE_INVALID = -1;
  public static final int STREAM_TYPE_LIVE = 2;
  public static final int STREAM_TYPE_NONE = 0;
  private final String wK;
  private int wL;
  private String wM;
  private MediaMetadata wN;
  private long wO;
  private JSONObject wP;
  
  MediaInfo(String paramString)
    throws IllegalArgumentException
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("content ID cannot be null or empty");
    }
    this.wK = paramString;
    this.wL = -1;
  }
  
  MediaInfo(JSONObject paramJSONObject)
    throws JSONException
  {
    this.wK = paramJSONObject.getString("contentId");
    Object localObject = paramJSONObject.getString("streamType");
    if ("NONE".equals(localObject)) {
      this.wL = 0;
    }
    for (;;)
    {
      this.wM = paramJSONObject.getString("contentType");
      if (paramJSONObject.has("metadata"))
      {
        localObject = paramJSONObject.getJSONObject("metadata");
        this.wN = new MediaMetadata(((JSONObject)localObject).getInt("metadataType"));
        this.wN.b((JSONObject)localObject);
      }
      this.wO = dr.b(paramJSONObject.optDouble("duration", 0.0D));
      this.wP = paramJSONObject.optJSONObject("customData");
      return;
      if ("BUFFERED".equals(localObject)) {
        this.wL = 1;
      } else if ("LIVE".equals(localObject)) {
        this.wL = 2;
      } else {
        this.wL = -1;
      }
    }
  }
  
  void a(MediaMetadata paramMediaMetadata)
  {
    this.wN = paramMediaMetadata;
  }
  
  void a(JSONObject paramJSONObject)
  {
    this.wP = paramJSONObject;
  }
  
  void cS()
    throws IllegalArgumentException
  {
    if (TextUtils.isEmpty(this.wK)) {
      throw new IllegalArgumentException("content ID cannot be null or empty");
    }
    if (TextUtils.isEmpty(this.wM)) {
      throw new IllegalArgumentException("content type cannot be null or empty");
    }
    if (this.wL == -1) {
      throw new IllegalArgumentException("a valid stream type must be specified");
    }
  }
  
  public JSONObject cT()
  {
    JSONObject localJSONObject = new JSONObject();
    for (;;)
    {
      try
      {
        localJSONObject.put("contentId", this.wK);
        switch (this.wL)
        {
        case 2: 
          localJSONObject.put("streamType", localObject);
          if (this.wM != null) {
            localJSONObject.put("contentType", this.wM);
          }
          if (this.wN != null) {
            localJSONObject.put("metadata", this.wN.cT());
          }
          localJSONObject.put("duration", dr.l(this.wO));
          if (this.wP == null) {
            break label140;
          }
          localJSONObject.put("customData", this.wP);
          return localJSONObject;
        }
      }
      catch (JSONException localJSONException)
      {
        Object localObject;
        return localJSONObject;
      }
      localObject = "LIVE";
      continue;
      String str = "NONE";
      continue;
      label140:
      return localJSONObject;
      str = "BUFFERED";
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = true;
    boolean bool3 = false;
    if (this == paramObject) {
      bool1 = true;
    }
    int i;
    int j;
    label51:
    do
    {
      do
      {
        do
        {
          return bool1;
          bool1 = bool3;
        } while (!(paramObject instanceof MediaInfo));
        paramObject = (MediaInfo)paramObject;
        if (this.wP != null) {
          break;
        }
        i = 1;
        if (((MediaInfo)paramObject).wP != null) {
          break label169;
        }
        j = 1;
        bool1 = bool3;
      } while (i != j);
      if ((this.wP == null) || (((MediaInfo)paramObject).wP == null)) {
        break;
      }
      bool1 = bool3;
    } while (!fp.d(this.wP, ((MediaInfo)paramObject).wP));
    if ((dr.a(this.wK, ((MediaInfo)paramObject).wK)) && (this.wL == ((MediaInfo)paramObject).wL) && (dr.a(this.wM, ((MediaInfo)paramObject).wM)) && (dr.a(this.wN, ((MediaInfo)paramObject).wN)) && (this.wO == ((MediaInfo)paramObject).wO)) {}
    for (boolean bool1 = bool2;; bool1 = false)
    {
      return bool1;
      i = 0;
      break;
      label169:
      j = 0;
      break label51;
    }
  }
  
  public String getContentId()
  {
    return this.wK;
  }
  
  public String getContentType()
  {
    return this.wM;
  }
  
  public JSONObject getCustomData()
  {
    return this.wP;
  }
  
  public MediaMetadata getMetadata()
  {
    return this.wN;
  }
  
  public long getStreamDuration()
  {
    return this.wO;
  }
  
  public int getStreamType()
  {
    return this.wL;
  }
  
  public int hashCode()
  {
    return ep.hashCode(new Object[] { this.wK, Integer.valueOf(this.wL), this.wM, this.wN, Long.valueOf(this.wO), String.valueOf(this.wP) });
  }
  
  void j(long paramLong)
    throws IllegalArgumentException
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("Stream duration cannot be negative");
    }
    this.wO = paramLong;
  }
  
  void setContentType(String paramString)
    throws IllegalArgumentException
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("content type cannot be null or empty");
    }
    this.wM = paramString;
  }
  
  void setStreamType(int paramInt)
    throws IllegalArgumentException
  {
    if ((paramInt < -1) || (paramInt > 2)) {
      throw new IllegalArgumentException("invalid stream type");
    }
    this.wL = paramInt;
  }
  
  public static class Builder
  {
    private final MediaInfo wQ;
    
    public Builder(String paramString)
      throws IllegalArgumentException
    {
      if (TextUtils.isEmpty(paramString)) {
        throw new IllegalArgumentException("Content ID cannot be empty");
      }
      this.wQ = new MediaInfo(paramString);
    }
    
    public MediaInfo build()
      throws IllegalArgumentException
    {
      this.wQ.cS();
      return this.wQ;
    }
    
    public Builder setContentType(String paramString)
      throws IllegalArgumentException
    {
      this.wQ.setContentType(paramString);
      return this;
    }
    
    public Builder setCustomData(JSONObject paramJSONObject)
    {
      this.wQ.a(paramJSONObject);
      return this;
    }
    
    public Builder setMetadata(MediaMetadata paramMediaMetadata)
    {
      this.wQ.a(paramMediaMetadata);
      return this;
    }
    
    public Builder setStreamDuration(long paramLong)
      throws IllegalArgumentException
    {
      this.wQ.j(paramLong);
      return this;
    }
    
    public Builder setStreamType(int paramInt)
      throws IllegalArgumentException
    {
      this.wQ.setStreamType(paramInt);
      return this;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\cast\MediaInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */