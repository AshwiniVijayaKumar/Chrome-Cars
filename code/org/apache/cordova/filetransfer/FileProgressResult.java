package org.apache.cordova.filetransfer;

import org.json.JSONException;
import org.json.JSONObject;

public class FileProgressResult
{
  private boolean lengthComputable = false;
  private long loaded = 0L;
  private long total = 0L;
  
  public boolean getLengthComputable()
  {
    return this.lengthComputable;
  }
  
  public long getLoaded()
  {
    return this.loaded;
  }
  
  public long getTotal()
  {
    return this.total;
  }
  
  public void setLengthComputable(boolean paramBoolean)
  {
    this.lengthComputable = paramBoolean;
  }
  
  public void setLoaded(long paramLong)
  {
    this.loaded = paramLong;
  }
  
  public void setTotal(long paramLong)
  {
    this.total = paramLong;
  }
  
  public JSONObject toJSONObject()
    throws JSONException
  {
    StringBuilder localStringBuilder = new StringBuilder().append("{loaded:").append(this.loaded).append(",total:").append(this.total).append(",lengthComputable:");
    if (this.lengthComputable) {}
    for (String str = "true";; str = "false") {
      return new JSONObject(str + "}");
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\filetransfer\FileProgressResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */