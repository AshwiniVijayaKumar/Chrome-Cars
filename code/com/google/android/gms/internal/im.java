package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.plus.model.moments.ItemScope;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class im
  extends fb
  implements SafeParcelable, ItemScope
{
  public static final in CREATOR = new in();
  private static final HashMap<String, fb.a<?, ?>> RL = new HashMap();
  private String FH;
  private double KX;
  private double KY;
  private String Oc;
  private final Set<Integer> RM;
  private im RN;
  private List<String> RO;
  private im RP;
  private String RQ;
  private String RR;
  private String RS;
  private List<im> RT;
  private int RU;
  private List<im> RV;
  private im RW;
  private List<im> RX;
  private String RY;
  private String RZ;
  private String SA;
  private String SB;
  private im SC;
  private String SD;
  private String SE;
  private String SF;
  private String SG;
  private im Sa;
  private String Sb;
  private String Sc;
  private List<im> Sd;
  private String Se;
  private String Sf;
  private String Sg;
  private String Sh;
  private String Si;
  private String Sj;
  private String Sk;
  private String Sl;
  private im Sm;
  private String Sn;
  private String So;
  private String Sp;
  private im Sq;
  private im Sr;
  private im Ss;
  private List<im> St;
  private String Su;
  private String Sv;
  private String Sw;
  private String Sx;
  private im Sy;
  private String Sz;
  private String lt;
  private String mName;
  private String pS;
  private String uS;
  private final int wj;
  
  static
  {
    RL.put("about", fb.a.a("about", 2, im.class));
    RL.put("additionalName", fb.a.k("additionalName", 3));
    RL.put("address", fb.a.a("address", 4, im.class));
    RL.put("addressCountry", fb.a.j("addressCountry", 5));
    RL.put("addressLocality", fb.a.j("addressLocality", 6));
    RL.put("addressRegion", fb.a.j("addressRegion", 7));
    RL.put("associated_media", fb.a.b("associated_media", 8, im.class));
    RL.put("attendeeCount", fb.a.g("attendeeCount", 9));
    RL.put("attendees", fb.a.b("attendees", 10, im.class));
    RL.put("audio", fb.a.a("audio", 11, im.class));
    RL.put("author", fb.a.b("author", 12, im.class));
    RL.put("bestRating", fb.a.j("bestRating", 13));
    RL.put("birthDate", fb.a.j("birthDate", 14));
    RL.put("byArtist", fb.a.a("byArtist", 15, im.class));
    RL.put("caption", fb.a.j("caption", 16));
    RL.put("contentSize", fb.a.j("contentSize", 17));
    RL.put("contentUrl", fb.a.j("contentUrl", 18));
    RL.put("contributor", fb.a.b("contributor", 19, im.class));
    RL.put("dateCreated", fb.a.j("dateCreated", 20));
    RL.put("dateModified", fb.a.j("dateModified", 21));
    RL.put("datePublished", fb.a.j("datePublished", 22));
    RL.put("description", fb.a.j("description", 23));
    RL.put("duration", fb.a.j("duration", 24));
    RL.put("embedUrl", fb.a.j("embedUrl", 25));
    RL.put("endDate", fb.a.j("endDate", 26));
    RL.put("familyName", fb.a.j("familyName", 27));
    RL.put("gender", fb.a.j("gender", 28));
    RL.put("geo", fb.a.a("geo", 29, im.class));
    RL.put("givenName", fb.a.j("givenName", 30));
    RL.put("height", fb.a.j("height", 31));
    RL.put("id", fb.a.j("id", 32));
    RL.put("image", fb.a.j("image", 33));
    RL.put("inAlbum", fb.a.a("inAlbum", 34, im.class));
    RL.put("latitude", fb.a.h("latitude", 36));
    RL.put("location", fb.a.a("location", 37, im.class));
    RL.put("longitude", fb.a.h("longitude", 38));
    RL.put("name", fb.a.j("name", 39));
    RL.put("partOfTVSeries", fb.a.a("partOfTVSeries", 40, im.class));
    RL.put("performers", fb.a.b("performers", 41, im.class));
    RL.put("playerType", fb.a.j("playerType", 42));
    RL.put("postOfficeBoxNumber", fb.a.j("postOfficeBoxNumber", 43));
    RL.put("postalCode", fb.a.j("postalCode", 44));
    RL.put("ratingValue", fb.a.j("ratingValue", 45));
    RL.put("reviewRating", fb.a.a("reviewRating", 46, im.class));
    RL.put("startDate", fb.a.j("startDate", 47));
    RL.put("streetAddress", fb.a.j("streetAddress", 48));
    RL.put("text", fb.a.j("text", 49));
    RL.put("thumbnail", fb.a.a("thumbnail", 50, im.class));
    RL.put("thumbnailUrl", fb.a.j("thumbnailUrl", 51));
    RL.put("tickerSymbol", fb.a.j("tickerSymbol", 52));
    RL.put("type", fb.a.j("type", 53));
    RL.put("url", fb.a.j("url", 54));
    RL.put("width", fb.a.j("width", 55));
    RL.put("worstRating", fb.a.j("worstRating", 56));
  }
  
  public im()
  {
    this.wj = 1;
    this.RM = new HashSet();
  }
  
  im(Set<Integer> paramSet, int paramInt1, im paramim1, List<String> paramList, im paramim2, String paramString1, String paramString2, String paramString3, List<im> paramList1, int paramInt2, List<im> paramList2, im paramim3, List<im> paramList3, String paramString4, String paramString5, im paramim4, String paramString6, String paramString7, String paramString8, List<im> paramList4, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14, String paramString15, String paramString16, String paramString17, im paramim5, String paramString18, String paramString19, String paramString20, String paramString21, im paramim6, double paramDouble1, im paramim7, double paramDouble2, String paramString22, im paramim8, List<im> paramList5, String paramString23, String paramString24, String paramString25, String paramString26, im paramim9, String paramString27, String paramString28, String paramString29, im paramim10, String paramString30, String paramString31, String paramString32, String paramString33, String paramString34, String paramString35)
  {
    this.RM = paramSet;
    this.wj = paramInt1;
    this.RN = paramim1;
    this.RO = paramList;
    this.RP = paramim2;
    this.RQ = paramString1;
    this.RR = paramString2;
    this.RS = paramString3;
    this.RT = paramList1;
    this.RU = paramInt2;
    this.RV = paramList2;
    this.RW = paramim3;
    this.RX = paramList3;
    this.RY = paramString4;
    this.RZ = paramString5;
    this.Sa = paramim4;
    this.Sb = paramString6;
    this.Sc = paramString7;
    this.lt = paramString8;
    this.Sd = paramList4;
    this.Se = paramString9;
    this.Sf = paramString10;
    this.Sg = paramString11;
    this.FH = paramString12;
    this.Sh = paramString13;
    this.Si = paramString14;
    this.Sj = paramString15;
    this.Sk = paramString16;
    this.Sl = paramString17;
    this.Sm = paramim5;
    this.Sn = paramString18;
    this.So = paramString19;
    this.uS = paramString20;
    this.Sp = paramString21;
    this.Sq = paramim6;
    this.KX = paramDouble1;
    this.Sr = paramim7;
    this.KY = paramDouble2;
    this.mName = paramString22;
    this.Ss = paramim8;
    this.St = paramList5;
    this.Su = paramString23;
    this.Sv = paramString24;
    this.Sw = paramString25;
    this.Sx = paramString26;
    this.Sy = paramim9;
    this.Sz = paramString27;
    this.SA = paramString28;
    this.SB = paramString29;
    this.SC = paramim10;
    this.SD = paramString30;
    this.SE = paramString31;
    this.Oc = paramString32;
    this.pS = paramString33;
    this.SF = paramString34;
    this.SG = paramString35;
  }
  
  public im(Set<Integer> paramSet, im paramim1, List<String> paramList, im paramim2, String paramString1, String paramString2, String paramString3, List<im> paramList1, int paramInt, List<im> paramList2, im paramim3, List<im> paramList3, String paramString4, String paramString5, im paramim4, String paramString6, String paramString7, String paramString8, List<im> paramList4, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14, String paramString15, String paramString16, String paramString17, im paramim5, String paramString18, String paramString19, String paramString20, String paramString21, im paramim6, double paramDouble1, im paramim7, double paramDouble2, String paramString22, im paramim8, List<im> paramList5, String paramString23, String paramString24, String paramString25, String paramString26, im paramim9, String paramString27, String paramString28, String paramString29, im paramim10, String paramString30, String paramString31, String paramString32, String paramString33, String paramString34, String paramString35)
  {
    this.RM = paramSet;
    this.wj = 1;
    this.RN = paramim1;
    this.RO = paramList;
    this.RP = paramim2;
    this.RQ = paramString1;
    this.RR = paramString2;
    this.RS = paramString3;
    this.RT = paramList1;
    this.RU = paramInt;
    this.RV = paramList2;
    this.RW = paramim3;
    this.RX = paramList3;
    this.RY = paramString4;
    this.RZ = paramString5;
    this.Sa = paramim4;
    this.Sb = paramString6;
    this.Sc = paramString7;
    this.lt = paramString8;
    this.Sd = paramList4;
    this.Se = paramString9;
    this.Sf = paramString10;
    this.Sg = paramString11;
    this.FH = paramString12;
    this.Sh = paramString13;
    this.Si = paramString14;
    this.Sj = paramString15;
    this.Sk = paramString16;
    this.Sl = paramString17;
    this.Sm = paramim5;
    this.Sn = paramString18;
    this.So = paramString19;
    this.uS = paramString20;
    this.Sp = paramString21;
    this.Sq = paramim6;
    this.KX = paramDouble1;
    this.Sr = paramim7;
    this.KY = paramDouble2;
    this.mName = paramString22;
    this.Ss = paramim8;
    this.St = paramList5;
    this.Su = paramString23;
    this.Sv = paramString24;
    this.Sw = paramString25;
    this.Sx = paramString26;
    this.Sy = paramim9;
    this.Sz = paramString27;
    this.SA = paramString28;
    this.SB = paramString29;
    this.SC = paramim10;
    this.SD = paramString30;
    this.SE = paramString31;
    this.Oc = paramString32;
    this.pS = paramString33;
    this.SF = paramString34;
    this.SG = paramString35;
  }
  
  protected boolean a(fb.a parama)
  {
    return this.RM.contains(Integer.valueOf(parama.eu()));
  }
  
  protected Object ak(String paramString)
  {
    return null;
  }
  
  protected boolean al(String paramString)
  {
    return false;
  }
  
  protected Object b(fb.a parama)
  {
    switch (parama.eu())
    {
    case 35: 
    default: 
      throw new IllegalStateException("Unknown safe parcelable id=" + parama.eu());
    case 2: 
      return this.RN;
    case 3: 
      return this.RO;
    case 4: 
      return this.RP;
    case 5: 
      return this.RQ;
    case 6: 
      return this.RR;
    case 7: 
      return this.RS;
    case 8: 
      return this.RT;
    case 9: 
      return Integer.valueOf(this.RU);
    case 10: 
      return this.RV;
    case 11: 
      return this.RW;
    case 12: 
      return this.RX;
    case 13: 
      return this.RY;
    case 14: 
      return this.RZ;
    case 15: 
      return this.Sa;
    case 16: 
      return this.Sb;
    case 17: 
      return this.Sc;
    case 18: 
      return this.lt;
    case 19: 
      return this.Sd;
    case 20: 
      return this.Se;
    case 21: 
      return this.Sf;
    case 22: 
      return this.Sg;
    case 23: 
      return this.FH;
    case 24: 
      return this.Sh;
    case 25: 
      return this.Si;
    case 26: 
      return this.Sj;
    case 27: 
      return this.Sk;
    case 28: 
      return this.Sl;
    case 29: 
      return this.Sm;
    case 30: 
      return this.Sn;
    case 31: 
      return this.So;
    case 32: 
      return this.uS;
    case 33: 
      return this.Sp;
    case 34: 
      return this.Sq;
    case 36: 
      return Double.valueOf(this.KX);
    case 37: 
      return this.Sr;
    case 38: 
      return Double.valueOf(this.KY);
    case 39: 
      return this.mName;
    case 40: 
      return this.Ss;
    case 41: 
      return this.St;
    case 42: 
      return this.Su;
    case 43: 
      return this.Sv;
    case 44: 
      return this.Sw;
    case 45: 
      return this.Sx;
    case 46: 
      return this.Sy;
    case 47: 
      return this.Sz;
    case 48: 
      return this.SA;
    case 49: 
      return this.SB;
    case 50: 
      return this.SC;
    case 51: 
      return this.SD;
    case 52: 
      return this.SE;
    case 53: 
      return this.Oc;
    case 54: 
      return this.pS;
    case 55: 
      return this.SF;
    }
    return this.SG;
  }
  
  public int describeContents()
  {
    in localin = CREATOR;
    return 0;
  }
  
  public HashMap<String, fb.a<?, ?>> en()
  {
    return RL;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof im)) {
      return false;
    }
    if (this == paramObject) {
      return true;
    }
    paramObject = (im)paramObject;
    Iterator localIterator = RL.values().iterator();
    while (localIterator.hasNext())
    {
      fb.a locala = (fb.a)localIterator.next();
      if (a(locala))
      {
        if (((im)paramObject).a(locala))
        {
          if (!b(locala).equals(((im)paramObject).b(locala))) {
            return false;
          }
        }
        else {
          return false;
        }
      }
      else if (((im)paramObject).a(locala)) {
        return false;
      }
    }
    return true;
  }
  
  public ItemScope getAbout()
  {
    return this.RN;
  }
  
  public List<String> getAdditionalName()
  {
    return this.RO;
  }
  
  public ItemScope getAddress()
  {
    return this.RP;
  }
  
  public String getAddressCountry()
  {
    return this.RQ;
  }
  
  public String getAddressLocality()
  {
    return this.RR;
  }
  
  public String getAddressRegion()
  {
    return this.RS;
  }
  
  public List<ItemScope> getAssociated_media()
  {
    return (ArrayList)this.RT;
  }
  
  public int getAttendeeCount()
  {
    return this.RU;
  }
  
  public List<ItemScope> getAttendees()
  {
    return (ArrayList)this.RV;
  }
  
  public ItemScope getAudio()
  {
    return this.RW;
  }
  
  public List<ItemScope> getAuthor()
  {
    return (ArrayList)this.RX;
  }
  
  public String getBestRating()
  {
    return this.RY;
  }
  
  public String getBirthDate()
  {
    return this.RZ;
  }
  
  public ItemScope getByArtist()
  {
    return this.Sa;
  }
  
  public String getCaption()
  {
    return this.Sb;
  }
  
  public String getContentSize()
  {
    return this.Sc;
  }
  
  public String getContentUrl()
  {
    return this.lt;
  }
  
  public List<ItemScope> getContributor()
  {
    return (ArrayList)this.Sd;
  }
  
  public String getDateCreated()
  {
    return this.Se;
  }
  
  public String getDateModified()
  {
    return this.Sf;
  }
  
  public String getDatePublished()
  {
    return this.Sg;
  }
  
  public String getDescription()
  {
    return this.FH;
  }
  
  public String getDuration()
  {
    return this.Sh;
  }
  
  public String getEmbedUrl()
  {
    return this.Si;
  }
  
  public String getEndDate()
  {
    return this.Sj;
  }
  
  public String getFamilyName()
  {
    return this.Sk;
  }
  
  public String getGender()
  {
    return this.Sl;
  }
  
  public ItemScope getGeo()
  {
    return this.Sm;
  }
  
  public String getGivenName()
  {
    return this.Sn;
  }
  
  public String getHeight()
  {
    return this.So;
  }
  
  public String getId()
  {
    return this.uS;
  }
  
  public String getImage()
  {
    return this.Sp;
  }
  
  public ItemScope getInAlbum()
  {
    return this.Sq;
  }
  
  public double getLatitude()
  {
    return this.KX;
  }
  
  public ItemScope getLocation()
  {
    return this.Sr;
  }
  
  public double getLongitude()
  {
    return this.KY;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public ItemScope getPartOfTVSeries()
  {
    return this.Ss;
  }
  
  public List<ItemScope> getPerformers()
  {
    return (ArrayList)this.St;
  }
  
  public String getPlayerType()
  {
    return this.Su;
  }
  
  public String getPostOfficeBoxNumber()
  {
    return this.Sv;
  }
  
  public String getPostalCode()
  {
    return this.Sw;
  }
  
  public String getRatingValue()
  {
    return this.Sx;
  }
  
  public ItemScope getReviewRating()
  {
    return this.Sy;
  }
  
  public String getStartDate()
  {
    return this.Sz;
  }
  
  public String getStreetAddress()
  {
    return this.SA;
  }
  
  public String getText()
  {
    return this.SB;
  }
  
  public ItemScope getThumbnail()
  {
    return this.SC;
  }
  
  public String getThumbnailUrl()
  {
    return this.SD;
  }
  
  public String getTickerSymbol()
  {
    return this.SE;
  }
  
  public String getType()
  {
    return this.Oc;
  }
  
  public String getUrl()
  {
    return this.pS;
  }
  
  int getVersionCode()
  {
    return this.wj;
  }
  
  public String getWidth()
  {
    return this.SF;
  }
  
  public String getWorstRating()
  {
    return this.SG;
  }
  
  Set<Integer> hB()
  {
    return this.RM;
  }
  
  im hC()
  {
    return this.RN;
  }
  
  im hD()
  {
    return this.RP;
  }
  
  List<im> hE()
  {
    return this.RT;
  }
  
  List<im> hF()
  {
    return this.RV;
  }
  
  im hG()
  {
    return this.RW;
  }
  
  List<im> hH()
  {
    return this.RX;
  }
  
  im hI()
  {
    return this.Sa;
  }
  
  List<im> hJ()
  {
    return this.Sd;
  }
  
  im hK()
  {
    return this.Sm;
  }
  
  im hL()
  {
    return this.Sq;
  }
  
  im hM()
  {
    return this.Sr;
  }
  
  im hN()
  {
    return this.Ss;
  }
  
  List<im> hO()
  {
    return this.St;
  }
  
  im hP()
  {
    return this.Sy;
  }
  
  im hQ()
  {
    return this.SC;
  }
  
  public im hR()
  {
    return this;
  }
  
  public boolean hasAbout()
  {
    return this.RM.contains(Integer.valueOf(2));
  }
  
  public boolean hasAdditionalName()
  {
    return this.RM.contains(Integer.valueOf(3));
  }
  
  public boolean hasAddress()
  {
    return this.RM.contains(Integer.valueOf(4));
  }
  
  public boolean hasAddressCountry()
  {
    return this.RM.contains(Integer.valueOf(5));
  }
  
  public boolean hasAddressLocality()
  {
    return this.RM.contains(Integer.valueOf(6));
  }
  
  public boolean hasAddressRegion()
  {
    return this.RM.contains(Integer.valueOf(7));
  }
  
  public boolean hasAssociated_media()
  {
    return this.RM.contains(Integer.valueOf(8));
  }
  
  public boolean hasAttendeeCount()
  {
    return this.RM.contains(Integer.valueOf(9));
  }
  
  public boolean hasAttendees()
  {
    return this.RM.contains(Integer.valueOf(10));
  }
  
  public boolean hasAudio()
  {
    return this.RM.contains(Integer.valueOf(11));
  }
  
  public boolean hasAuthor()
  {
    return this.RM.contains(Integer.valueOf(12));
  }
  
  public boolean hasBestRating()
  {
    return this.RM.contains(Integer.valueOf(13));
  }
  
  public boolean hasBirthDate()
  {
    return this.RM.contains(Integer.valueOf(14));
  }
  
  public boolean hasByArtist()
  {
    return this.RM.contains(Integer.valueOf(15));
  }
  
  public boolean hasCaption()
  {
    return this.RM.contains(Integer.valueOf(16));
  }
  
  public boolean hasContentSize()
  {
    return this.RM.contains(Integer.valueOf(17));
  }
  
  public boolean hasContentUrl()
  {
    return this.RM.contains(Integer.valueOf(18));
  }
  
  public boolean hasContributor()
  {
    return this.RM.contains(Integer.valueOf(19));
  }
  
  public boolean hasDateCreated()
  {
    return this.RM.contains(Integer.valueOf(20));
  }
  
  public boolean hasDateModified()
  {
    return this.RM.contains(Integer.valueOf(21));
  }
  
  public boolean hasDatePublished()
  {
    return this.RM.contains(Integer.valueOf(22));
  }
  
  public boolean hasDescription()
  {
    return this.RM.contains(Integer.valueOf(23));
  }
  
  public boolean hasDuration()
  {
    return this.RM.contains(Integer.valueOf(24));
  }
  
  public boolean hasEmbedUrl()
  {
    return this.RM.contains(Integer.valueOf(25));
  }
  
  public boolean hasEndDate()
  {
    return this.RM.contains(Integer.valueOf(26));
  }
  
  public boolean hasFamilyName()
  {
    return this.RM.contains(Integer.valueOf(27));
  }
  
  public boolean hasGender()
  {
    return this.RM.contains(Integer.valueOf(28));
  }
  
  public boolean hasGeo()
  {
    return this.RM.contains(Integer.valueOf(29));
  }
  
  public boolean hasGivenName()
  {
    return this.RM.contains(Integer.valueOf(30));
  }
  
  public boolean hasHeight()
  {
    return this.RM.contains(Integer.valueOf(31));
  }
  
  public boolean hasId()
  {
    return this.RM.contains(Integer.valueOf(32));
  }
  
  public boolean hasImage()
  {
    return this.RM.contains(Integer.valueOf(33));
  }
  
  public boolean hasInAlbum()
  {
    return this.RM.contains(Integer.valueOf(34));
  }
  
  public boolean hasLatitude()
  {
    return this.RM.contains(Integer.valueOf(36));
  }
  
  public boolean hasLocation()
  {
    return this.RM.contains(Integer.valueOf(37));
  }
  
  public boolean hasLongitude()
  {
    return this.RM.contains(Integer.valueOf(38));
  }
  
  public boolean hasName()
  {
    return this.RM.contains(Integer.valueOf(39));
  }
  
  public boolean hasPartOfTVSeries()
  {
    return this.RM.contains(Integer.valueOf(40));
  }
  
  public boolean hasPerformers()
  {
    return this.RM.contains(Integer.valueOf(41));
  }
  
  public boolean hasPlayerType()
  {
    return this.RM.contains(Integer.valueOf(42));
  }
  
  public boolean hasPostOfficeBoxNumber()
  {
    return this.RM.contains(Integer.valueOf(43));
  }
  
  public boolean hasPostalCode()
  {
    return this.RM.contains(Integer.valueOf(44));
  }
  
  public boolean hasRatingValue()
  {
    return this.RM.contains(Integer.valueOf(45));
  }
  
  public boolean hasReviewRating()
  {
    return this.RM.contains(Integer.valueOf(46));
  }
  
  public boolean hasStartDate()
  {
    return this.RM.contains(Integer.valueOf(47));
  }
  
  public boolean hasStreetAddress()
  {
    return this.RM.contains(Integer.valueOf(48));
  }
  
  public boolean hasText()
  {
    return this.RM.contains(Integer.valueOf(49));
  }
  
  public boolean hasThumbnail()
  {
    return this.RM.contains(Integer.valueOf(50));
  }
  
  public boolean hasThumbnailUrl()
  {
    return this.RM.contains(Integer.valueOf(51));
  }
  
  public boolean hasTickerSymbol()
  {
    return this.RM.contains(Integer.valueOf(52));
  }
  
  public boolean hasType()
  {
    return this.RM.contains(Integer.valueOf(53));
  }
  
  public boolean hasUrl()
  {
    return this.RM.contains(Integer.valueOf(54));
  }
  
  public boolean hasWidth()
  {
    return this.RM.contains(Integer.valueOf(55));
  }
  
  public boolean hasWorstRating()
  {
    return this.RM.contains(Integer.valueOf(56));
  }
  
  public int hashCode()
  {
    Iterator localIterator = RL.values().iterator();
    int i = 0;
    if (localIterator.hasNext())
    {
      fb.a locala = (fb.a)localIterator.next();
      if (!a(locala)) {
        break label68;
      }
      int j = locala.eu();
      i = b(locala).hashCode() + (i + j);
    }
    label68:
    for (;;)
    {
      break;
      return i;
    }
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    in localin = CREATOR;
    in.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\im.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */