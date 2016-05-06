package org.apache.cordova.media;

import android.app.Activity;
import android.media.AudioManager;
import android.net.Uri;
import android.util.Log;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AudioHandler
  extends CordovaPlugin
{
  public static final int PERMISSION_DENIED_ERROR = 20;
  public static int RECORD_AUDIO = 0;
  public static String TAG = "AudioHandler";
  public static int WRITE_EXTERNAL_STORAGE = 1;
  public static String[] permissions = { "android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE" };
  private String fileUriStr;
  private CallbackContext messageChannel;
  private int origVolumeStream = -1;
  ArrayList<AudioPlayer> pausedForPhone = new ArrayList();
  HashMap<String, AudioPlayer> players = new HashMap();
  private String recordId;
  
  private AudioPlayer getOrCreatePlayer(String paramString1, String paramString2)
  {
    AudioPlayer localAudioPlayer2 = (AudioPlayer)this.players.get(paramString1);
    AudioPlayer localAudioPlayer1 = localAudioPlayer2;
    if (localAudioPlayer2 == null)
    {
      if (this.players.isEmpty()) {
        onFirstPlayerCreated();
      }
      localAudioPlayer1 = new AudioPlayer(this, paramString1, paramString2);
      this.players.put(paramString1, localAudioPlayer1);
    }
    return localAudioPlayer1;
  }
  
  private void onFirstPlayerCreated()
  {
    this.origVolumeStream = this.cordova.getActivity().getVolumeControlStream();
    this.cordova.getActivity().setVolumeControlStream(3);
  }
  
  private void onLastPlayerReleased()
  {
    if (this.origVolumeStream != -1)
    {
      this.cordova.getActivity().setVolumeControlStream(this.origVolumeStream);
      this.origVolumeStream = -1;
    }
  }
  
  private void promptForRecord()
  {
    if ((this.cordova.hasPermission(permissions[WRITE_EXTERNAL_STORAGE])) && (this.cordova.hasPermission(permissions[RECORD_AUDIO])))
    {
      startRecordingAudio(this.recordId, FileHelper.stripFileProtocol(this.fileUriStr));
      return;
    }
    if (this.cordova.hasPermission(permissions[RECORD_AUDIO]))
    {
      getWritePermission(WRITE_EXTERNAL_STORAGE);
      return;
    }
    getMicPermission(RECORD_AUDIO);
  }
  
  private boolean release(String paramString)
  {
    paramString = (AudioPlayer)this.players.remove(paramString);
    if (paramString == null) {
      return false;
    }
    if (this.players.isEmpty()) {
      onLastPlayerReleased();
    }
    paramString.destroy();
    return true;
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    Object localObject = this.webView.getResourceApi();
    PluginResult.Status localStatus = PluginResult.Status.OK;
    this.messageChannel = paramCallbackContext;
    if (paramString.equals("startRecordingAudio"))
    {
      this.recordId = paramJSONArray.getString(0);
      paramString = paramJSONArray.getString(1);
    }
    for (;;)
    {
      try
      {
        this.fileUriStr = ((CordovaResourceApi)localObject).remapUri(Uri.parse(paramString)).toString();
        promptForRecord();
        paramCallbackContext.sendPluginResult(new PluginResult(localStatus, ""));
        return true;
      }
      catch (IllegalArgumentException paramJSONArray)
      {
        this.fileUriStr = paramString;
        continue;
      }
      if (paramString.equals("stopRecordingAudio"))
      {
        stopRecordingAudio(paramJSONArray.getString(0));
      }
      else
      {
        if (paramString.equals("startPlayingAudio"))
        {
          paramString = paramJSONArray.getString(1);
          try
          {
            localObject = ((CordovaResourceApi)localObject).remapUri(Uri.parse(paramString)).toString();
            paramString = (String)localObject;
          }
          catch (IllegalArgumentException localIllegalArgumentException)
          {
            for (;;) {}
          }
          startPlayingAudio(paramJSONArray.getString(0), FileHelper.stripFileProtocol(paramString));
          continue;
        }
        if (paramString.equals("seekToAudio"))
        {
          seekToAudio(paramJSONArray.getString(0), paramJSONArray.getInt(1));
        }
        else if (paramString.equals("pausePlayingAudio"))
        {
          pausePlayingAudio(paramJSONArray.getString(0));
        }
        else if (paramString.equals("stopPlayingAudio"))
        {
          stopPlayingAudio(paramJSONArray.getString(0));
        }
        else if (paramString.equals("setVolume"))
        {
          try
          {
            setVolume(paramJSONArray.getString(0), Float.parseFloat(paramJSONArray.getString(1)));
          }
          catch (NumberFormatException paramString) {}
        }
        else
        {
          if (paramString.equals("getCurrentPositionAudio"))
          {
            paramCallbackContext.sendPluginResult(new PluginResult(localStatus, getCurrentPositionAudio(paramJSONArray.getString(0))));
            return true;
          }
          if (paramString.equals("getDurationAudio"))
          {
            paramCallbackContext.sendPluginResult(new PluginResult(localStatus, getDurationAudio(paramJSONArray.getString(0), paramJSONArray.getString(1))));
            return true;
          }
          if (!paramString.equals("create")) {
            break;
          }
          getOrCreatePlayer(paramJSONArray.getString(0), FileHelper.stripFileProtocol(paramJSONArray.getString(1)));
        }
      }
    }
    if (paramString.equals("release"))
    {
      paramCallbackContext.sendPluginResult(new PluginResult(localStatus, release(paramJSONArray.getString(0))));
      return true;
    }
    if (paramString.equals("messageChannel"))
    {
      this.messageChannel = paramCallbackContext;
      return true;
    }
    return false;
  }
  
  public int getAudioOutputDevice()
  {
    AudioManager localAudioManager = (AudioManager)this.cordova.getActivity().getSystemService("audio");
    if (localAudioManager.getRouting(0) == 1) {
      return 1;
    }
    if (localAudioManager.getRouting(0) == 2) {
      return 2;
    }
    return -1;
  }
  
  public float getCurrentPositionAudio(String paramString)
  {
    paramString = (AudioPlayer)this.players.get(paramString);
    if (paramString != null) {
      return (float)paramString.getCurrentPosition() / 1000.0F;
    }
    return -1.0F;
  }
  
  public float getDurationAudio(String paramString1, String paramString2)
  {
    return getOrCreatePlayer(paramString1, paramString2).getDuration(paramString2);
  }
  
  protected void getMicPermission(int paramInt)
  {
    this.cordova.requestPermission(this, paramInt, permissions[RECORD_AUDIO]);
  }
  
  protected void getWritePermission(int paramInt)
  {
    this.cordova.requestPermission(this, paramInt, permissions[WRITE_EXTERNAL_STORAGE]);
  }
  
  public void onDestroy()
  {
    if (!this.players.isEmpty()) {
      onLastPlayerReleased();
    }
    Iterator localIterator = this.players.values().iterator();
    while (localIterator.hasNext()) {
      ((AudioPlayer)localIterator.next()).destroy();
    }
    this.players.clear();
  }
  
  public Object onMessage(String paramString, Object paramObject)
  {
    if (paramString.equals("telephone"))
    {
      if (("ringing".equals(paramObject)) || ("offhook".equals(paramObject))) {
        paramString = this.players.values().iterator();
      }
      while (paramString.hasNext())
      {
        paramObject = (AudioPlayer)paramString.next();
        if (((AudioPlayer)paramObject).getState() == AudioPlayer.STATE.MEDIA_RUNNING.ordinal())
        {
          this.pausedForPhone.add(paramObject);
          ((AudioPlayer)paramObject).pausePlaying();
          continue;
          if ("idle".equals(paramObject))
          {
            paramString = this.pausedForPhone.iterator();
            while (paramString.hasNext()) {
              ((AudioPlayer)paramString.next()).startPlaying(null);
            }
            this.pausedForPhone.clear();
          }
        }
      }
    }
    return null;
  }
  
  public void onRequestPermissionResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
    throws JSONException
  {
    int i = paramArrayOfInt.length;
    paramInt = 0;
    while (paramInt < i)
    {
      if (paramArrayOfInt[paramInt] == -1)
      {
        this.messageChannel.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, 20));
        return;
      }
      paramInt += 1;
    }
    promptForRecord();
  }
  
  public void onReset()
  {
    onDestroy();
  }
  
  public void pausePlayingAudio(String paramString)
  {
    paramString = (AudioPlayer)this.players.get(paramString);
    if (paramString != null) {
      paramString.pausePlaying();
    }
  }
  
  public void seekToAudio(String paramString, int paramInt)
  {
    paramString = (AudioPlayer)this.players.get(paramString);
    if (paramString != null) {
      paramString.seekToPlaying(paramInt);
    }
  }
  
  void sendEventMessage(String paramString, JSONObject paramJSONObject)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("action", paramString);
      if (paramJSONObject != null) {
        localJSONObject.put(paramString, paramJSONObject);
      }
    }
    catch (JSONException paramString)
    {
      for (;;)
      {
        Log.e(TAG, "Failed to create event message", paramString);
      }
    }
    paramString = new PluginResult(PluginResult.Status.OK, localJSONObject);
    paramString.setKeepCallback(true);
    if (this.messageChannel != null) {
      this.messageChannel.sendPluginResult(paramString);
    }
  }
  
  public void setAudioOutputDevice(int paramInt)
  {
    AudioManager localAudioManager = (AudioManager)this.cordova.getActivity().getSystemService("audio");
    if (paramInt == 2)
    {
      localAudioManager.setRouting(0, 2, -1);
      return;
    }
    if (paramInt == 1)
    {
      localAudioManager.setRouting(0, 1, -1);
      return;
    }
    System.out.println("AudioHandler.setAudioOutputDevice() Error: Unknown output device.");
  }
  
  public void setVolume(String paramString, float paramFloat)
  {
    AudioPlayer localAudioPlayer = (AudioPlayer)this.players.get(paramString);
    if (localAudioPlayer != null)
    {
      localAudioPlayer.setVolume(paramFloat);
      return;
    }
    System.out.println("AudioHandler.setVolume() Error: Unknown Audio Player " + paramString);
  }
  
  public void startPlayingAudio(String paramString1, String paramString2)
  {
    getOrCreatePlayer(paramString1, paramString2).startPlaying(paramString2);
  }
  
  public void startRecordingAudio(String paramString1, String paramString2)
  {
    getOrCreatePlayer(paramString1, paramString2).startRecording(paramString2);
  }
  
  public void stopPlayingAudio(String paramString)
  {
    paramString = (AudioPlayer)this.players.get(paramString);
    if (paramString != null) {
      paramString.stopPlaying();
    }
  }
  
  public void stopRecordingAudio(String paramString)
  {
    paramString = (AudioPlayer)this.players.get(paramString);
    if (paramString != null) {
      paramString.stopRecording();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\media\AudioHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */