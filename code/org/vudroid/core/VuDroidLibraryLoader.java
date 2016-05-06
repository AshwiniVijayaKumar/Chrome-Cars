package org.vudroid.core;

public class VuDroidLibraryLoader
{
  private static boolean alreadyLoaded = false;
  
  public static void load()
  {
    if (alreadyLoaded) {
      return;
    }
    System.loadLibrary("vudroid");
    alreadyLoaded = true;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\VuDroidLibraryLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */