package com.google.android.gms.drive;

import android.content.IntentSender;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.internal.CreateFileIntentSenderRequest;
import com.google.android.gms.drive.internal.n;
import com.google.android.gms.drive.internal.u;
import com.google.android.gms.internal.er;
import java.io.IOException;

public class CreateFileActivityBuilder
{
  public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
  private MetadataChangeSet CV;
  private Contents CW;
  private String CX;
  private DriveId CY;
  
  public IntentSender build(GoogleApiClient paramGoogleApiClient)
  {
    er.b(this.CW, "Must provide initial contents to CreateFileActivityBuilder.");
    try
    {
      this.CW.getParcelFileDescriptor().close();
      this.CW.close();
      er.a(paramGoogleApiClient.isConnected(), "Client must be connected");
      paramGoogleApiClient = ((n)paramGoogleApiClient.a(Drive.va)).eT();
      try
      {
        paramGoogleApiClient = paramGoogleApiClient.a(new CreateFileIntentSenderRequest(this.CV.eS(), this.CW.eP(), this.CX, this.CY));
        return paramGoogleApiClient;
      }
      catch (RemoteException paramGoogleApiClient)
      {
        throw new RuntimeException("Unable to connect Drive Play Service", paramGoogleApiClient);
      }
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
  }
  
  public CreateFileActivityBuilder setActivityStartFolder(DriveId paramDriveId)
  {
    this.CY = ((DriveId)er.f(paramDriveId));
    return this;
  }
  
  public CreateFileActivityBuilder setActivityTitle(String paramString)
  {
    this.CX = ((String)er.f(paramString));
    return this;
  }
  
  public CreateFileActivityBuilder setInitialContents(Contents paramContents)
  {
    this.CW = ((Contents)er.f(paramContents));
    return this;
  }
  
  public CreateFileActivityBuilder setInitialMetadata(MetadataChangeSet paramMetadataChangeSet)
  {
    this.CV = ((MetadataChangeSet)er.f(paramMetadataChangeSet));
    return this;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\CreateFileActivityBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */