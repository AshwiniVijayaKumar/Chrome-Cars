package com.example.example75f1799f07eb;

import android.app.Dialog;
import android.content.Intent;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;

public abstract class YouTubeFailureRecoveryActivity
  extends YouTubeBaseActivity
  implements YouTubePlayer.OnInitializedListener
{
  private static final int RECOVERY_DIALOG_REQUEST = 1;
  
  protected abstract YouTubePlayer.Provider getYouTubePlayerProvider();
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 1) {
      getYouTubePlayerProvider().initialize("AIzaSyCA4uFxSabaAXUf59yTVZtX_JTJ3PJsPlk", this);
    }
  }
  
  public void onInitializationFailure(YouTubePlayer.Provider paramProvider, YouTubeInitializationResult paramYouTubeInitializationResult)
  {
    if (paramYouTubeInitializationResult.isUserRecoverableError())
    {
      paramYouTubeInitializationResult.getErrorDialog(this, 1).show();
      return;
    }
    Toast.makeText(this, String.format("could not play file", new Object[0]), 1).show();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\YouTubeFailureRecoveryActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */