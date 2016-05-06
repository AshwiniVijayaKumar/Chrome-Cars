package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.CollectionMetadataField;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.a;
import com.google.android.gms.drive.metadata.internal.e;
import com.google.android.gms.drive.metadata.internal.g;
import com.google.android.gms.drive.metadata.internal.i;
import com.google.android.gms.drive.metadata.internal.j;

public class fs
{
  public static final MetadataField<String> EA = new j("webContentLink", 4300000);
  public static final MetadataField<String> EB = new j("webViewLink", 4300000);
  public static final MetadataField<String> EC = new j("indexableText", 4300000);
  public static final MetadataField<Boolean> ED = new a("hasThumbnail", 4300000);
  public static final MetadataField<DriveId> El = fu.EI;
  public static final MetadataField<Boolean> Em;
  public static final MetadataField<Boolean> En;
  public static final MetadataField<Boolean> Eo;
  public static final MetadataField<String> Ep;
  public static final CollectionMetadataField<String> Eq;
  public static final MetadataField<String> Er;
  public static final MetadataField<Boolean> Es;
  public static final MetadataField<String> Et;
  public static final MetadataField<String> Eu;
  public static final MetadataField<Long> Ev;
  public static final MetadataField<Boolean> Ew;
  public static final MetadataField<Boolean> Ex;
  public static final MetadataField<String> Ey;
  public static final MetadataField<Long> Ez;
  public static final MetadataField<Boolean> IS_PINNED;
  public static final MetadataField<String> MIME_TYPE;
  public static final CollectionMetadataField<DriveId> PARENTS;
  public static final MetadataField<Boolean> STARRED;
  public static final MetadataField<String> TITLE = new j("title", 4100000);
  public static final MetadataField<Boolean> TRASHED;
  
  static
  {
    MIME_TYPE = new j("mimeType", 4100000);
    STARRED = new a("starred", 4100000);
    TRASHED = new a("trashed", 4100000)
    {
      protected Boolean d(DataHolder paramAnonymousDataHolder, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        if (paramAnonymousDataHolder.getInteger(getName(), paramAnonymousInt1, paramAnonymousInt2) != 0) {}
        for (boolean bool = true;; bool = false) {
          return Boolean.valueOf(bool);
        }
      }
    };
    Em = new a("isEditable", 4100000);
    IS_PINNED = new a("isPinned", 4100000);
    En = new a("isAppData", 4300000);
    Eo = new a("isShared", 4300000);
    PARENTS = new g("parents", 4100000);
    Ep = new j("alternateLink", 4300000);
    Eq = new i("ownerNames", 4300000);
    Er = new j("description", 4300000);
    Es = new a("isCopyable", 4300000);
    Et = new j("embedLink", 4300000);
    Eu = new j("fileExtension", 4300000);
    Ev = new e("fileSize", 4300000);
    Ew = new a("isViewed", 4300000);
    Ex = new a("isRestricted", 4300000);
    Ey = new j("originalFilename", 4300000);
    Ez = new e("quotaBytesUsed", 4300000);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\fs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */