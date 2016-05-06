package com.google.android.gms.drive;

import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.fs;
import com.google.android.gms.internal.ft;
import com.google.android.gms.internal.fv;
import java.util.Date;

public abstract class Metadata
  implements Freezable<Metadata>
{
  public static final int CONTENT_AVAILABLE_LOCALLY = 1;
  public static final int CONTENT_NOT_AVAILABLE_LOCALLY = 0;
  
  protected abstract <T> T a(MetadataField<T> paramMetadataField);
  
  public String getAlternateLink()
  {
    return (String)a(fs.Ep);
  }
  
  public int getContentAvailability()
  {
    Integer localInteger = (Integer)a(fv.EJ);
    if (localInteger == null) {
      return 0;
    }
    return localInteger.intValue();
  }
  
  public Date getCreatedDate()
  {
    return (Date)a(ft.EG);
  }
  
  public String getDescription()
  {
    return (String)a(fs.Er);
  }
  
  public DriveId getDriveId()
  {
    return (DriveId)a(fs.El);
  }
  
  public String getEmbedLink()
  {
    return (String)a(fs.Et);
  }
  
  public String getFileExtension()
  {
    return (String)a(fs.Eu);
  }
  
  public long getFileSize()
  {
    return ((Long)a(fs.Ev)).longValue();
  }
  
  public Date getLastViewedByMeDate()
  {
    return (Date)a(ft.LAST_VIEWED_BY_ME);
  }
  
  public String getMimeType()
  {
    return (String)a(fs.MIME_TYPE);
  }
  
  public Date getModifiedByMeDate()
  {
    return (Date)a(ft.EF);
  }
  
  public Date getModifiedDate()
  {
    return (Date)a(ft.EE);
  }
  
  public String getOriginalFilename()
  {
    return (String)a(fs.Ey);
  }
  
  public long getQuotaBytesUsed()
  {
    return ((Long)a(fs.Ez)).longValue();
  }
  
  public Date getSharedWithMeDate()
  {
    return (Date)a(ft.EH);
  }
  
  public String getTitle()
  {
    return (String)a(fs.TITLE);
  }
  
  public String getWebContentLink()
  {
    return (String)a(fs.EA);
  }
  
  public String getWebViewLink()
  {
    return (String)a(fs.EB);
  }
  
  public boolean isEditable()
  {
    Boolean localBoolean = (Boolean)a(fs.Em);
    if (localBoolean == null) {
      return false;
    }
    return localBoolean.booleanValue();
  }
  
  public boolean isFolder()
  {
    return "application/vnd.google-apps.folder".equals(getMimeType());
  }
  
  public boolean isInAppFolder()
  {
    Boolean localBoolean = (Boolean)a(fs.En);
    if (localBoolean == null) {
      return false;
    }
    return localBoolean.booleanValue();
  }
  
  public boolean isPinnable()
  {
    Boolean localBoolean = (Boolean)a(fv.EK);
    if (localBoolean == null) {
      return false;
    }
    return localBoolean.booleanValue();
  }
  
  public boolean isPinned()
  {
    Boolean localBoolean = (Boolean)a(fs.IS_PINNED);
    if (localBoolean == null) {
      return false;
    }
    return localBoolean.booleanValue();
  }
  
  public boolean isRestricted()
  {
    Boolean localBoolean = (Boolean)a(fs.Ex);
    if (localBoolean == null) {
      return false;
    }
    return localBoolean.booleanValue();
  }
  
  public boolean isShared()
  {
    Boolean localBoolean = (Boolean)a(fs.Eo);
    if (localBoolean == null) {
      return false;
    }
    return localBoolean.booleanValue();
  }
  
  public boolean isStarred()
  {
    Boolean localBoolean = (Boolean)a(fs.STARRED);
    if (localBoolean == null) {
      return false;
    }
    return localBoolean.booleanValue();
  }
  
  public boolean isTrashed()
  {
    Boolean localBoolean = (Boolean)a(fs.TRASHED);
    if (localBoolean == null) {
      return false;
    }
    return localBoolean.booleanValue();
  }
  
  public boolean isViewed()
  {
    Boolean localBoolean = (Boolean)a(fs.Ew);
    if (localBoolean == null) {
      return false;
    }
    return localBoolean.booleanValue();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\Metadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */