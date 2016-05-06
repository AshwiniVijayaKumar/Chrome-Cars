package com.ons.radio;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import net.roarsoftware.lastfm.Album;
import net.roarsoftware.lastfm.ImageSize;
import net.roarsoftware.lastfm.Track;

public class LastFMCover
{
  public static String album;
  public static String apiKey;
  public static String artist;
  public static String track;
  
  public static Bitmap getCoverImageFromAlbum(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = getCoverUrlFromAlbum(paramString1, paramString2);
      System.out.println(paramString1);
      if (paramString1 != null)
      {
        paramString1 = BitmapFactory.decodeStream(new URL(paramString1).openConnection().getInputStream());
        return paramString1;
      }
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
      System.out.println("No Cover Image Found");
    }
    return null;
  }
  
  public static Bitmap getCoverImageFromTrack(String paramString1, String paramString2, String paramString3)
  {
    apiKey = paramString1;
    try
    {
      paramString1 = getCoverUrlFromTrack(paramString2, paramString3);
      System.out.println(paramString1);
      if (paramString1 != null)
      {
        paramString1 = BitmapFactory.decodeStream(new URL(paramString1).openConnection().getInputStream());
        return paramString1;
      }
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
      System.out.println("No Cover Image Found");
      album = "";
    }
    return null;
  }
  
  public static String getCoverUrlFromAlbum(String paramString1, String paramString2)
    throws Exception
  {
    System.out.println("Album image: " + paramString2 + " - " + paramString1);
    return Album.getInfo(paramString1, paramString2, apiKey).getImageURL(ImageSize.LARGE);
  }
  
  public static String getCoverUrlFromTrack(String paramString1, String paramString2)
    throws Exception
  {
    System.out.println("Track image: " + paramString2 + " - " + paramString1);
    paramString1 = Track.getInfo(paramString1, paramString2, apiKey);
    album = paramString1.getAlbum();
    return paramString1.getImageURL(ImageSize.EXTRALARGE);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\radio\LastFMCover.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */