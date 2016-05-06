package com.example.example75f1799f07eb;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnErrorListener;
import android.media.MediaRecorder.OnInfoListener;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class AudioRecordingActivity
  extends Activity
{
  private static final String AUDIO_RECORDER_FILE_EXT_3GP = ".3gp";
  private static final String AUDIO_RECORDER_FILE_EXT_MP4 = ".mp3";
  private static final String AUDIO_RECORDER_FOLDER = "AudioRecorder";
  TextView appName;
  private View.OnClickListener btnClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      default: 
        return;
      case 2131558713: 
        AudioRecordingActivity.this.enableButtons(true);
        AudioRecordingActivity.this.startRecording();
        return;
      }
      Toast.makeText(AudioRecordingActivity.this, "Stop Recording", 0).show();
      AudioRecordingActivity.this.enableButtons(false);
      AudioRecordingActivity.this.stopRecording();
    }
  };
  private int currentFormat = 0;
  private MediaRecorder.OnErrorListener errorListener = new MediaRecorder.OnErrorListener()
  {
    public void onError(MediaRecorder paramAnonymousMediaRecorder, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      Toast.makeText(AudioRecordingActivity.this, "Error: " + paramAnonymousInt1 + ", " + paramAnonymousInt2, 0).show();
    }
  };
  private String[] file_exts = { ".mp3", ".3gp" };
  private MediaRecorder.OnInfoListener infoListener = new MediaRecorder.OnInfoListener()
  {
    public void onInfo(MediaRecorder paramAnonymousMediaRecorder, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      Toast.makeText(AudioRecordingActivity.this, "Warning: " + paramAnonymousInt1 + ", " + paramAnonymousInt2, 0).show();
    }
  };
  Chronometer myChronometer;
  private int[] output_formats = { 2, 1 };
  private MediaRecorder recorder = null;
  ImageView recordingList;
  
  private void displayFormatDialog(String paramString)
  {
    paramString = getFilename(paramString);
    startActivity(new Intent(this, ListAudio.class).putExtra("path", paramString));
    if (this.recorder != null)
    {
      this.recorder.release();
      this.recorder = null;
    }
  }
  
  private void enableButton(int paramInt, boolean paramBoolean)
  {
    ((Button)findViewById(paramInt)).setEnabled(paramBoolean);
  }
  
  private void enableButtons(boolean paramBoolean)
  {
    if (!paramBoolean) {}
    for (boolean bool = true;; bool = false)
    {
      enableButton(2131558713, bool);
      enableButton(2131558714, paramBoolean);
      return;
    }
  }
  
  private String getFilename(String paramString)
  {
    File localFile = new File(Environment.getExternalStorageDirectory().getPath(), "AudioRecorder");
    if (!localFile.exists()) {
      localFile.mkdirs();
    }
    System.out.println(paramString);
    return localFile.getAbsolutePath() + "/" + paramString + this.file_exts[this.currentFormat];
  }
  
  private void renameDefaultFile(String paramString)
  {
    File localFile1 = new File(Environment.getExternalStorageDirectory().getPath(), "AudioRecorder");
    File localFile2;
    File localFile3;
    if (localFile1.exists())
    {
      localFile2 = new File(localFile1, "default_name.mp3");
      localFile3 = new File(localFile1, paramString + ".mp3");
      if (localFile2.exists())
      {
        if (!localFile3.exists()) {
          break label391;
        }
        localFile3 = new File(localFile1, paramString + "-1.mp3");
        File localFile4 = new File(localFile1, paramString + "-2.mp3");
        File localFile5 = new File(localFile1, paramString + "-3.mp3");
        File localFile6 = new File(localFile1, paramString + "-4.mp3");
        if (!localFile3.exists()) {
          break label358;
        }
        if (!localFile4.exists()) {
          break label325;
        }
        if (!localFile5.exists()) {
          break label292;
        }
        if (!localFile6.exists()) {
          break label259;
        }
        localFile2.renameTo(new File(localFile1, paramString + "-5.mp3"));
      }
    }
    return;
    label259:
    localFile2.renameTo(new File(localFile1, paramString + "-4.mp3"));
    return;
    label292:
    localFile2.renameTo(new File(localFile1, paramString + "-3.mp3"));
    return;
    label325:
    localFile2.renameTo(new File(localFile1, paramString + "-2.mp3"));
    return;
    label358:
    localFile2.renameTo(new File(localFile1, paramString + "-1.mp3"));
    return;
    label391:
    localFile2.renameTo(localFile3);
  }
  
  private void setButtonHandlers()
  {
    ((Button)findViewById(2131558713)).setOnClickListener(this.btnClick);
    ((Button)findViewById(2131558714)).setOnClickListener(this.btnClick);
  }
  
  private void setFormatButtonCaption() {}
  
  private void startRecording()
  {
    Toast.makeText(this, "Start Recording", 0).show();
    this.recorder = new MediaRecorder();
    this.myChronometer.setBase(SystemClock.elapsedRealtime());
    this.myChronometer.start();
    this.recorder.setAudioSource(1);
    this.recorder.setOutputFormat(this.output_formats[this.currentFormat]);
    this.recorder.setAudioEncoder(2);
    this.recorder.setOutputFile(getFilename("default_name"));
    this.recorder.setOnErrorListener(this.errorListener);
    this.recorder.setOnInfoListener(this.infoListener);
    try
    {
      this.recorder.prepare();
      this.recorder.start();
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      localIllegalStateException.printStackTrace();
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }
  
  private void stopRecording()
  {
    if (this.recorder != null)
    {
      this.myChronometer.stop();
      this.myChronometer.setBase(SystemClock.elapsedRealtime());
      this.recorder.stop();
      this.recorder.reset();
      this.recorder.release();
      this.recorder = null;
      setRecordNamePopup();
    }
  }
  
  public void close(View paramView)
  {
    finish();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(7);
    setContentView(2130903133);
    getWindow().setFeatureInt(7, 2130903063);
    paramBundle = getResources().getString(2131230720);
    this.appName = ((TextView)findViewById(2131558500));
    this.recordingList = ((ImageView)findViewById(2131558503));
    this.recordingList.setVisibility(0);
    this.myChronometer = ((Chronometer)findViewById(2131558712));
    this.appName.setText(paramBundle);
    setButtonHandlers();
    enableButtons(false);
    setFormatButtonCaption();
  }
  
  public void recordingList(View paramView)
  {
    startActivity(new Intent(this, ListAudio.class).putExtra("path", "" + getFilename("")));
  }
  
  public void setRecordNamePopup()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage("Enter file name");
    final EditText localEditText = new EditText(this);
    localEditText.setHint("file name");
    localBuilder.setView(localEditText);
    localEditText.setFilters(new InputFilter[] { new InputFilter()
    {
      public CharSequence filter(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, Spanned paramAnonymousSpanned, int paramAnonymousInt3, int paramAnonymousInt4)
      {
        while (paramAnonymousInt1 < paramAnonymousInt2)
        {
          if (!Character.isLetterOrDigit(paramAnonymousCharSequence.charAt(paramAnonymousInt1))) {
            return "";
          }
          paramAnonymousInt1 += 1;
        }
        return null;
      }
    } });
    localBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = localEditText.getText().toString();
        if (!paramAnonymousDialogInterface.equals(""))
        {
          AudioRecordingActivity.this.renameDefaultFile(paramAnonymousDialogInterface);
          AudioRecordingActivity.this.displayFormatDialog(paramAnonymousDialogInterface);
          return;
        }
        AudioRecordingActivity.this.enableButtons(false);
      }
    });
    localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        AudioRecordingActivity.this.enableButtons(false);
        paramAnonymousDialogInterface.dismiss();
      }
    });
    localBuilder.show();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\AudioRecordingActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */