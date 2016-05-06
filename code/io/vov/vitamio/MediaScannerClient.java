package io.vov.vitamio;

public abstract interface MediaScannerClient
{
  public abstract void addNoMediaFolder(String paramString);
  
  public abstract void handleStringTag(String paramString1, byte[] paramArrayOfByte, String paramString2);
  
  public abstract void scanFile(String paramString, long paramLong1, long paramLong2);
  
  public abstract void setMimeType(String paramString);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\io\vov\vitamio\MediaScannerClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */