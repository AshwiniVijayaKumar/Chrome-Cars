package net.roarsoftware.lastfm.scrobble;

public class ResponseStatus
{
  public static final int BADAUTH = 2;
  public static final int BADSESSION = 4;
  public static final int BADTIME = 3;
  public static final int BANNED = 1;
  public static final int FAILED = 5;
  public static final int OK = 0;
  private String message;
  private int status;
  
  public ResponseStatus(int paramInt)
  {
    this(paramInt, null);
  }
  
  public ResponseStatus(int paramInt, String paramString)
  {
    this.status = paramInt;
    this.message = paramString;
  }
  
  static int codeForStatus(String paramString)
  {
    if ("OK".equals(paramString)) {
      return 0;
    }
    if (paramString.startsWith("FAILED")) {
      return 5;
    }
    if ("BADAUTH".equals(paramString)) {
      return 2;
    }
    if ("BADSESSION".equals(paramString)) {
      return 4;
    }
    if ("BANNED".equals(paramString)) {
      return 1;
    }
    if ("BADTIME".equals(paramString)) {
      return 3;
    }
    return -1;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public int getStatus()
  {
    return this.status;
  }
  
  public boolean ok()
  {
    return this.status == 0;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\scrobble\ResponseStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */