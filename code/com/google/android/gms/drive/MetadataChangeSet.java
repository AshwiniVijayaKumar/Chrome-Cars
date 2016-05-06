package com.google.android.gms.drive;

import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.fs;
import com.google.android.gms.internal.ft;
import java.util.Date;

public final class MetadataChangeSet
{
  private final MetadataBundle Dj;
  
  private MetadataChangeSet(MetadataBundle paramMetadataBundle)
  {
    this.Dj = MetadataBundle.a(paramMetadataBundle);
  }
  
  public MetadataBundle eS()
  {
    return this.Dj;
  }
  
  public String getIndexableText()
  {
    return (String)this.Dj.a(fs.EC);
  }
  
  public Date getLastViewedByMeDate()
  {
    return (Date)this.Dj.a(ft.LAST_VIEWED_BY_ME);
  }
  
  public String getMimeType()
  {
    return (String)this.Dj.a(fs.MIME_TYPE);
  }
  
  public String getTitle()
  {
    return (String)this.Dj.a(fs.TITLE);
  }
  
  public Boolean isPinned()
  {
    return (Boolean)this.Dj.a(fs.IS_PINNED);
  }
  
  public Boolean isStarred()
  {
    return (Boolean)this.Dj.a(fs.STARRED);
  }
  
  public Boolean isViewed()
  {
    return (Boolean)this.Dj.a(fs.Ew);
  }
  
  public static class Builder
  {
    private final MetadataBundle Dj = MetadataBundle.fh();
    
    public MetadataChangeSet build()
    {
      return new MetadataChangeSet(this.Dj, null);
    }
    
    public Builder setIndexableText(String paramString)
    {
      this.Dj.b(fs.EC, paramString);
      return this;
    }
    
    public Builder setLastViewedByMeDate(Date paramDate)
    {
      this.Dj.b(ft.LAST_VIEWED_BY_ME, paramDate);
      return this;
    }
    
    public Builder setMimeType(String paramString)
    {
      this.Dj.b(fs.MIME_TYPE, paramString);
      return this;
    }
    
    public Builder setPinned(boolean paramBoolean)
    {
      this.Dj.b(fs.IS_PINNED, Boolean.valueOf(paramBoolean));
      return this;
    }
    
    public Builder setStarred(boolean paramBoolean)
    {
      this.Dj.b(fs.STARRED, Boolean.valueOf(paramBoolean));
      return this;
    }
    
    public Builder setTitle(String paramString)
    {
      this.Dj.b(fs.TITLE, paramString);
      return this;
    }
    
    public Builder setViewed(boolean paramBoolean)
    {
      this.Dj.b(fs.Ew, Boolean.valueOf(paramBoolean));
      return this;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\MetadataChangeSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */