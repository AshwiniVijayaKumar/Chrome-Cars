package com.ooyala.android.util;

import android.content.Context;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public final class TemporaryInternalStorageFile
{
  private final File tmpFile;
  
  public TemporaryInternalStorageFile(Context paramContext, String paramString1, String paramString2)
    throws IOException
  {
    this.tmpFile = File.createTempFile(paramString1, paramString2, paramContext.getCacheDir());
    if ((this.tmpFile != null) && (!this.tmpFile.exists())) {
      this.tmpFile.createNewFile();
    }
  }
  
  public String getAbsolutePath()
  {
    if (this.tmpFile == null) {
      return "";
    }
    return this.tmpFile.getAbsolutePath();
  }
  
  public File getFile()
  {
    return this.tmpFile;
  }
  
  public void write(String paramString)
    throws FileNotFoundException
  {
    if (this.tmpFile != null)
    {
      PrintWriter localPrintWriter = new PrintWriter(this.tmpFile);
      localPrintWriter.write(paramString);
      localPrintWriter.flush();
      localPrintWriter.close();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\util\TemporaryInternalStorageFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */