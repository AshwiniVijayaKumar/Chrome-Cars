package com.ons.musicplayer;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class SongsManager
{
  final String MEDIA_PATH = new String("/sdcard/");
  private ArrayList<HashMap<String, String>> songsList = new ArrayList();
  
  public ArrayList<HashMap<String, String>> getPlayList(String[] paramArrayOfString)
  {
    this.songsList.clear();
    Object localObject;
    if ((paramArrayOfString.length == 1) && ((paramArrayOfString[0].contains("http://109.169.26.216:8056")) || (paramArrayOfString[0].contains("http://199.189.111.149:8132")) || (paramArrayOfString[0].contains("http://192.240.102.131:9930/jobeez"))))
    {
      localObject = new HashMap();
      ((HashMap)localObject).put("songTitle", "");
      if (paramArrayOfString[0].contains("http://199.189.111.149:8132"))
      {
        ((HashMap)localObject).put("songPath", "http://199.189.111.149:8132");
        ((HashMap)localObject).put("songImage", "");
        this.songsList.add(localObject);
      }
    }
    for (;;)
    {
      return this.songsList;
      if (paramArrayOfString[0].contains("http://192.240.102.131:9930"))
      {
        ((HashMap)localObject).put("songPath", "http://192.240.102.131:9930");
        break;
      }
      ((HashMap)localObject).put("songPath", "http://109.169.26.216:8056");
      break;
      if ((paramArrayOfString.length != 1) || (!paramArrayOfString[0].contains(".m3u8"))) {
        break label213;
      }
      localObject = new HashMap();
      ((HashMap)localObject).put("songTitle", "");
      ((HashMap)localObject).put("songPath", paramArrayOfString[0].split(",")[0]);
      ((HashMap)localObject).put("songImage", "");
      this.songsList.add(localObject);
    }
    label213:
    int i = 1;
    label215:
    HashMap localHashMap;
    if (i < paramArrayOfString.length)
    {
      localObject = paramArrayOfString[i].split("#####");
      localHashMap = new HashMap();
      if (localObject.length >= 2)
      {
        localHashMap.put("songTitle", localObject[0]);
        localHashMap.put("songPath", localObject[1]);
        if (localObject.length <= 2) {
          break label301;
        }
        localHashMap.put("songImage", localObject[2]);
      }
    }
    for (;;)
    {
      this.songsList.add(localHashMap);
      i += 1;
      break label215;
      break;
      label301:
      localHashMap.put("songImage", "");
    }
  }
  
  class FileExtensionFilter
    implements FilenameFilter
  {
    FileExtensionFilter() {}
    
    public boolean accept(File paramFile, String paramString)
    {
      return (paramString.endsWith(".mp3")) || (paramString.endsWith(".MP3"));
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\musicplayer\SongsManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */