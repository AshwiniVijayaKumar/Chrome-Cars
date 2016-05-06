package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.Person.AgeRange;
import com.google.android.gms.plus.model.people.Person.Cover;
import com.google.android.gms.plus.model.people.Person.Cover.CoverInfo;
import com.google.android.gms.plus.model.people.Person.Cover.CoverPhoto;
import com.google.android.gms.plus.model.people.Person.Image;
import com.google.android.gms.plus.model.people.Person.Name;
import com.google.android.gms.plus.model.people.Person.Organizations;
import com.google.android.gms.plus.model.people.Person.PlacesLived;
import com.google.android.gms.plus.model.people.Person.Urls;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class ir
  extends fb
  implements SafeParcelable, Person
{
  public static final is CREATOR = new is();
  private static final HashMap<String, fb.a<?, ?>> RL = new HashMap();
  private String FE;
  private final Set<Integer> RM;
  private String SK;
  private a SL;
  private String SM;
  private String SN;
  private int SO;
  private b SP;
  private String SQ;
  private c SR;
  private boolean SS;
  private String ST;
  private d SU;
  private String SV;
  private int SW;
  private List<f> SX;
  private List<g> SY;
  private int SZ;
  private int Ta;
  private String Tb;
  private List<h> Tc;
  private boolean Td;
  private int lu;
  private String pS;
  private String uS;
  private final int wj;
  
  static
  {
    RL.put("aboutMe", fb.a.j("aboutMe", 2));
    RL.put("ageRange", fb.a.a("ageRange", 3, a.class));
    RL.put("birthday", fb.a.j("birthday", 4));
    RL.put("braggingRights", fb.a.j("braggingRights", 5));
    RL.put("circledByCount", fb.a.g("circledByCount", 6));
    RL.put("cover", fb.a.a("cover", 7, b.class));
    RL.put("currentLocation", fb.a.j("currentLocation", 8));
    RL.put("displayName", fb.a.j("displayName", 9));
    RL.put("gender", fb.a.a("gender", 12, new ey().f("male", 0).f("female", 1).f("other", 2), false));
    RL.put("id", fb.a.j("id", 14));
    RL.put("image", fb.a.a("image", 15, c.class));
    RL.put("isPlusUser", fb.a.i("isPlusUser", 16));
    RL.put("language", fb.a.j("language", 18));
    RL.put("name", fb.a.a("name", 19, d.class));
    RL.put("nickname", fb.a.j("nickname", 20));
    RL.put("objectType", fb.a.a("objectType", 21, new ey().f("person", 0).f("page", 1), false));
    RL.put("organizations", fb.a.b("organizations", 22, f.class));
    RL.put("placesLived", fb.a.b("placesLived", 23, g.class));
    RL.put("plusOneCount", fb.a.g("plusOneCount", 24));
    RL.put("relationshipStatus", fb.a.a("relationshipStatus", 25, new ey().f("single", 0).f("in_a_relationship", 1).f("engaged", 2).f("married", 3).f("its_complicated", 4).f("open_relationship", 5).f("widowed", 6).f("in_domestic_partnership", 7).f("in_civil_union", 8), false));
    RL.put("tagline", fb.a.j("tagline", 26));
    RL.put("url", fb.a.j("url", 27));
    RL.put("urls", fb.a.b("urls", 28, h.class));
    RL.put("verified", fb.a.i("verified", 29));
  }
  
  public ir()
  {
    this.wj = 2;
    this.RM = new HashSet();
  }
  
  public ir(String paramString1, String paramString2, c paramc, int paramInt, String paramString3)
  {
    this.wj = 2;
    this.RM = new HashSet();
    this.FE = paramString1;
    this.RM.add(Integer.valueOf(9));
    this.uS = paramString2;
    this.RM.add(Integer.valueOf(14));
    this.SR = paramc;
    this.RM.add(Integer.valueOf(15));
    this.SW = paramInt;
    this.RM.add(Integer.valueOf(21));
    this.pS = paramString3;
    this.RM.add(Integer.valueOf(27));
  }
  
  ir(Set<Integer> paramSet, int paramInt1, String paramString1, a parama, String paramString2, String paramString3, int paramInt2, b paramb, String paramString4, String paramString5, int paramInt3, String paramString6, c paramc, boolean paramBoolean1, String paramString7, d paramd, String paramString8, int paramInt4, List<f> paramList, List<g> paramList1, int paramInt5, int paramInt6, String paramString9, String paramString10, List<h> paramList2, boolean paramBoolean2)
  {
    this.RM = paramSet;
    this.wj = paramInt1;
    this.SK = paramString1;
    this.SL = parama;
    this.SM = paramString2;
    this.SN = paramString3;
    this.SO = paramInt2;
    this.SP = paramb;
    this.SQ = paramString4;
    this.FE = paramString5;
    this.lu = paramInt3;
    this.uS = paramString6;
    this.SR = paramc;
    this.SS = paramBoolean1;
    this.ST = paramString7;
    this.SU = paramd;
    this.SV = paramString8;
    this.SW = paramInt4;
    this.SX = paramList;
    this.SY = paramList1;
    this.SZ = paramInt5;
    this.Ta = paramInt6;
    this.Tb = paramString9;
    this.pS = paramString10;
    this.Tc = paramList2;
    this.Td = paramBoolean2;
  }
  
  public static ir i(byte[] paramArrayOfByte)
  {
    Parcel localParcel = Parcel.obtain();
    localParcel.unmarshall(paramArrayOfByte, 0, paramArrayOfByte.length);
    localParcel.setDataPosition(0);
    paramArrayOfByte = CREATOR.aI(localParcel);
    localParcel.recycle();
    return paramArrayOfByte;
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
    case 10: 
    case 11: 
    case 13: 
    case 17: 
    default: 
      throw new IllegalStateException("Unknown safe parcelable id=" + parama.eu());
    case 2: 
      return this.SK;
    case 3: 
      return this.SL;
    case 4: 
      return this.SM;
    case 5: 
      return this.SN;
    case 6: 
      return Integer.valueOf(this.SO);
    case 7: 
      return this.SP;
    case 8: 
      return this.SQ;
    case 9: 
      return this.FE;
    case 12: 
      return Integer.valueOf(this.lu);
    case 14: 
      return this.uS;
    case 15: 
      return this.SR;
    case 16: 
      return Boolean.valueOf(this.SS);
    case 18: 
      return this.ST;
    case 19: 
      return this.SU;
    case 20: 
      return this.SV;
    case 21: 
      return Integer.valueOf(this.SW);
    case 22: 
      return this.SX;
    case 23: 
      return this.SY;
    case 24: 
      return Integer.valueOf(this.SZ);
    case 25: 
      return Integer.valueOf(this.Ta);
    case 26: 
      return this.Tb;
    case 27: 
      return this.pS;
    case 28: 
      return this.Tc;
    }
    return Boolean.valueOf(this.Td);
  }
  
  public int describeContents()
  {
    is localis = CREATOR;
    return 0;
  }
  
  public HashMap<String, fb.a<?, ?>> en()
  {
    return RL;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof ir)) {
      return false;
    }
    if (this == paramObject) {
      return true;
    }
    paramObject = (ir)paramObject;
    Iterator localIterator = RL.values().iterator();
    while (localIterator.hasNext())
    {
      fb.a locala = (fb.a)localIterator.next();
      if (a(locala))
      {
        if (((ir)paramObject).a(locala))
        {
          if (!b(locala).equals(((ir)paramObject).b(locala))) {
            return false;
          }
        }
        else {
          return false;
        }
      }
      else if (((ir)paramObject).a(locala)) {
        return false;
      }
    }
    return true;
  }
  
  public String getAboutMe()
  {
    return this.SK;
  }
  
  public Person.AgeRange getAgeRange()
  {
    return this.SL;
  }
  
  public String getBirthday()
  {
    return this.SM;
  }
  
  public String getBraggingRights()
  {
    return this.SN;
  }
  
  public int getCircledByCount()
  {
    return this.SO;
  }
  
  public Person.Cover getCover()
  {
    return this.SP;
  }
  
  public String getCurrentLocation()
  {
    return this.SQ;
  }
  
  public String getDisplayName()
  {
    return this.FE;
  }
  
  public int getGender()
  {
    return this.lu;
  }
  
  public String getId()
  {
    return this.uS;
  }
  
  public Person.Image getImage()
  {
    return this.SR;
  }
  
  public String getLanguage()
  {
    return this.ST;
  }
  
  public Person.Name getName()
  {
    return this.SU;
  }
  
  public String getNickname()
  {
    return this.SV;
  }
  
  public int getObjectType()
  {
    return this.SW;
  }
  
  public List<Person.Organizations> getOrganizations()
  {
    return (ArrayList)this.SX;
  }
  
  public List<Person.PlacesLived> getPlacesLived()
  {
    return (ArrayList)this.SY;
  }
  
  public int getPlusOneCount()
  {
    return this.SZ;
  }
  
  public int getRelationshipStatus()
  {
    return this.Ta;
  }
  
  public String getTagline()
  {
    return this.Tb;
  }
  
  public String getUrl()
  {
    return this.pS;
  }
  
  public List<Person.Urls> getUrls()
  {
    return (ArrayList)this.Tc;
  }
  
  int getVersionCode()
  {
    return this.wj;
  }
  
  Set<Integer> hB()
  {
    return this.RM;
  }
  
  a hW()
  {
    return this.SL;
  }
  
  b hX()
  {
    return this.SP;
  }
  
  c hY()
  {
    return this.SR;
  }
  
  d hZ()
  {
    return this.SU;
  }
  
  public boolean hasAboutMe()
  {
    return this.RM.contains(Integer.valueOf(2));
  }
  
  public boolean hasAgeRange()
  {
    return this.RM.contains(Integer.valueOf(3));
  }
  
  public boolean hasBirthday()
  {
    return this.RM.contains(Integer.valueOf(4));
  }
  
  public boolean hasBraggingRights()
  {
    return this.RM.contains(Integer.valueOf(5));
  }
  
  public boolean hasCircledByCount()
  {
    return this.RM.contains(Integer.valueOf(6));
  }
  
  public boolean hasCover()
  {
    return this.RM.contains(Integer.valueOf(7));
  }
  
  public boolean hasCurrentLocation()
  {
    return this.RM.contains(Integer.valueOf(8));
  }
  
  public boolean hasDisplayName()
  {
    return this.RM.contains(Integer.valueOf(9));
  }
  
  public boolean hasGender()
  {
    return this.RM.contains(Integer.valueOf(12));
  }
  
  public boolean hasId()
  {
    return this.RM.contains(Integer.valueOf(14));
  }
  
  public boolean hasImage()
  {
    return this.RM.contains(Integer.valueOf(15));
  }
  
  public boolean hasIsPlusUser()
  {
    return this.RM.contains(Integer.valueOf(16));
  }
  
  public boolean hasLanguage()
  {
    return this.RM.contains(Integer.valueOf(18));
  }
  
  public boolean hasName()
  {
    return this.RM.contains(Integer.valueOf(19));
  }
  
  public boolean hasNickname()
  {
    return this.RM.contains(Integer.valueOf(20));
  }
  
  public boolean hasObjectType()
  {
    return this.RM.contains(Integer.valueOf(21));
  }
  
  public boolean hasOrganizations()
  {
    return this.RM.contains(Integer.valueOf(22));
  }
  
  public boolean hasPlacesLived()
  {
    return this.RM.contains(Integer.valueOf(23));
  }
  
  public boolean hasPlusOneCount()
  {
    return this.RM.contains(Integer.valueOf(24));
  }
  
  public boolean hasRelationshipStatus()
  {
    return this.RM.contains(Integer.valueOf(25));
  }
  
  public boolean hasTagline()
  {
    return this.RM.contains(Integer.valueOf(26));
  }
  
  public boolean hasUrl()
  {
    return this.RM.contains(Integer.valueOf(27));
  }
  
  public boolean hasUrls()
  {
    return this.RM.contains(Integer.valueOf(28));
  }
  
  public boolean hasVerified()
  {
    return this.RM.contains(Integer.valueOf(29));
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
  
  List<f> ia()
  {
    return this.SX;
  }
  
  List<g> ib()
  {
    return this.SY;
  }
  
  List<h> ic()
  {
    return this.Tc;
  }
  
  public ir id()
  {
    return this;
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public boolean isPlusUser()
  {
    return this.SS;
  }
  
  public boolean isVerified()
  {
    return this.Td;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    is localis = CREATOR;
    is.a(this, paramParcel, paramInt);
  }
  
  public static final class a
    extends fb
    implements SafeParcelable, Person.AgeRange
  {
    public static final it CREATOR = new it();
    private static final HashMap<String, fb.a<?, ?>> RL = new HashMap();
    private final Set<Integer> RM;
    private int Te;
    private int Tf;
    private final int wj;
    
    static
    {
      RL.put("max", fb.a.g("max", 2));
      RL.put("min", fb.a.g("min", 3));
    }
    
    public a()
    {
      this.wj = 1;
      this.RM = new HashSet();
    }
    
    a(Set<Integer> paramSet, int paramInt1, int paramInt2, int paramInt3)
    {
      this.RM = paramSet;
      this.wj = paramInt1;
      this.Te = paramInt2;
      this.Tf = paramInt3;
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
      default: 
        throw new IllegalStateException("Unknown safe parcelable id=" + parama.eu());
      case 2: 
        return Integer.valueOf(this.Te);
      }
      return Integer.valueOf(this.Tf);
    }
    
    public int describeContents()
    {
      it localit = CREATOR;
      return 0;
    }
    
    public HashMap<String, fb.a<?, ?>> en()
    {
      return RL;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof a)) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      paramObject = (a)paramObject;
      Iterator localIterator = RL.values().iterator();
      while (localIterator.hasNext())
      {
        fb.a locala = (fb.a)localIterator.next();
        if (a(locala))
        {
          if (((a)paramObject).a(locala))
          {
            if (!b(locala).equals(((a)paramObject).b(locala))) {
              return false;
            }
          }
          else {
            return false;
          }
        }
        else if (((a)paramObject).a(locala)) {
          return false;
        }
      }
      return true;
    }
    
    public int getMax()
    {
      return this.Te;
    }
    
    public int getMin()
    {
      return this.Tf;
    }
    
    int getVersionCode()
    {
      return this.wj;
    }
    
    Set<Integer> hB()
    {
      return this.RM;
    }
    
    public boolean hasMax()
    {
      return this.RM.contains(Integer.valueOf(2));
    }
    
    public boolean hasMin()
    {
      return this.RM.contains(Integer.valueOf(3));
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
    
    public a ie()
    {
      return this;
    }
    
    public boolean isDataValid()
    {
      return true;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      it localit = CREATOR;
      it.a(this, paramParcel, paramInt);
    }
  }
  
  public static final class b
    extends fb
    implements SafeParcelable, Person.Cover
  {
    public static final iu CREATOR = new iu();
    private static final HashMap<String, fb.a<?, ?>> RL = new HashMap();
    private final Set<Integer> RM;
    private a Tg;
    private b Th;
    private int Ti;
    private final int wj;
    
    static
    {
      RL.put("coverInfo", fb.a.a("coverInfo", 2, a.class));
      RL.put("coverPhoto", fb.a.a("coverPhoto", 3, b.class));
      RL.put("layout", fb.a.a("layout", 4, new ey().f("banner", 0), false));
    }
    
    public b()
    {
      this.wj = 1;
      this.RM = new HashSet();
    }
    
    b(Set<Integer> paramSet, int paramInt1, a parama, b paramb, int paramInt2)
    {
      this.RM = paramSet;
      this.wj = paramInt1;
      this.Tg = parama;
      this.Th = paramb;
      this.Ti = paramInt2;
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
      default: 
        throw new IllegalStateException("Unknown safe parcelable id=" + parama.eu());
      case 2: 
        return this.Tg;
      case 3: 
        return this.Th;
      }
      return Integer.valueOf(this.Ti);
    }
    
    public int describeContents()
    {
      iu localiu = CREATOR;
      return 0;
    }
    
    public HashMap<String, fb.a<?, ?>> en()
    {
      return RL;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof b)) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      paramObject = (b)paramObject;
      Iterator localIterator = RL.values().iterator();
      while (localIterator.hasNext())
      {
        fb.a locala = (fb.a)localIterator.next();
        if (a(locala))
        {
          if (((b)paramObject).a(locala))
          {
            if (!b(locala).equals(((b)paramObject).b(locala))) {
              return false;
            }
          }
          else {
            return false;
          }
        }
        else if (((b)paramObject).a(locala)) {
          return false;
        }
      }
      return true;
    }
    
    public Person.Cover.CoverInfo getCoverInfo()
    {
      return this.Tg;
    }
    
    public Person.Cover.CoverPhoto getCoverPhoto()
    {
      return this.Th;
    }
    
    public int getLayout()
    {
      return this.Ti;
    }
    
    int getVersionCode()
    {
      return this.wj;
    }
    
    Set<Integer> hB()
    {
      return this.RM;
    }
    
    public boolean hasCoverInfo()
    {
      return this.RM.contains(Integer.valueOf(2));
    }
    
    public boolean hasCoverPhoto()
    {
      return this.RM.contains(Integer.valueOf(3));
    }
    
    public boolean hasLayout()
    {
      return this.RM.contains(Integer.valueOf(4));
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
    
    a jdMethod_if()
    {
      return this.Tg;
    }
    
    b ig()
    {
      return this.Th;
    }
    
    public b ih()
    {
      return this;
    }
    
    public boolean isDataValid()
    {
      return true;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      iu localiu = CREATOR;
      iu.a(this, paramParcel, paramInt);
    }
    
    public static final class a
      extends fb
      implements SafeParcelable, Person.Cover.CoverInfo
    {
      public static final iv CREATOR = new iv();
      private static final HashMap<String, fb.a<?, ?>> RL = new HashMap();
      private final Set<Integer> RM;
      private int Tj;
      private int Tk;
      private final int wj;
      
      static
      {
        RL.put("leftImageOffset", fb.a.g("leftImageOffset", 2));
        RL.put("topImageOffset", fb.a.g("topImageOffset", 3));
      }
      
      public a()
      {
        this.wj = 1;
        this.RM = new HashSet();
      }
      
      a(Set<Integer> paramSet, int paramInt1, int paramInt2, int paramInt3)
      {
        this.RM = paramSet;
        this.wj = paramInt1;
        this.Tj = paramInt2;
        this.Tk = paramInt3;
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
        default: 
          throw new IllegalStateException("Unknown safe parcelable id=" + parama.eu());
        case 2: 
          return Integer.valueOf(this.Tj);
        }
        return Integer.valueOf(this.Tk);
      }
      
      public int describeContents()
      {
        iv localiv = CREATOR;
        return 0;
      }
      
      public HashMap<String, fb.a<?, ?>> en()
      {
        return RL;
      }
      
      public boolean equals(Object paramObject)
      {
        if (!(paramObject instanceof a)) {
          return false;
        }
        if (this == paramObject) {
          return true;
        }
        paramObject = (a)paramObject;
        Iterator localIterator = RL.values().iterator();
        while (localIterator.hasNext())
        {
          fb.a locala = (fb.a)localIterator.next();
          if (a(locala))
          {
            if (((a)paramObject).a(locala))
            {
              if (!b(locala).equals(((a)paramObject).b(locala))) {
                return false;
              }
            }
            else {
              return false;
            }
          }
          else if (((a)paramObject).a(locala)) {
            return false;
          }
        }
        return true;
      }
      
      public int getLeftImageOffset()
      {
        return this.Tj;
      }
      
      public int getTopImageOffset()
      {
        return this.Tk;
      }
      
      int getVersionCode()
      {
        return this.wj;
      }
      
      Set<Integer> hB()
      {
        return this.RM;
      }
      
      public boolean hasLeftImageOffset()
      {
        return this.RM.contains(Integer.valueOf(2));
      }
      
      public boolean hasTopImageOffset()
      {
        return this.RM.contains(Integer.valueOf(3));
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
      
      public a ii()
      {
        return this;
      }
      
      public boolean isDataValid()
      {
        return true;
      }
      
      public void writeToParcel(Parcel paramParcel, int paramInt)
      {
        iv localiv = CREATOR;
        iv.a(this, paramParcel, paramInt);
      }
    }
    
    public static final class b
      extends fb
      implements SafeParcelable, Person.Cover.CoverPhoto
    {
      public static final iw CREATOR = new iw();
      private static final HashMap<String, fb.a<?, ?>> RL = new HashMap();
      private final Set<Integer> RM;
      private String pS;
      private int v;
      private int w;
      private final int wj;
      
      static
      {
        RL.put("height", fb.a.g("height", 2));
        RL.put("url", fb.a.j("url", 3));
        RL.put("width", fb.a.g("width", 4));
      }
      
      public b()
      {
        this.wj = 1;
        this.RM = new HashSet();
      }
      
      b(Set<Integer> paramSet, int paramInt1, int paramInt2, String paramString, int paramInt3)
      {
        this.RM = paramSet;
        this.wj = paramInt1;
        this.v = paramInt2;
        this.pS = paramString;
        this.w = paramInt3;
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
        default: 
          throw new IllegalStateException("Unknown safe parcelable id=" + parama.eu());
        case 2: 
          return Integer.valueOf(this.v);
        case 3: 
          return this.pS;
        }
        return Integer.valueOf(this.w);
      }
      
      public int describeContents()
      {
        iw localiw = CREATOR;
        return 0;
      }
      
      public HashMap<String, fb.a<?, ?>> en()
      {
        return RL;
      }
      
      public boolean equals(Object paramObject)
      {
        if (!(paramObject instanceof b)) {
          return false;
        }
        if (this == paramObject) {
          return true;
        }
        paramObject = (b)paramObject;
        Iterator localIterator = RL.values().iterator();
        while (localIterator.hasNext())
        {
          fb.a locala = (fb.a)localIterator.next();
          if (a(locala))
          {
            if (((b)paramObject).a(locala))
            {
              if (!b(locala).equals(((b)paramObject).b(locala))) {
                return false;
              }
            }
            else {
              return false;
            }
          }
          else if (((b)paramObject).a(locala)) {
            return false;
          }
        }
        return true;
      }
      
      public int getHeight()
      {
        return this.v;
      }
      
      public String getUrl()
      {
        return this.pS;
      }
      
      int getVersionCode()
      {
        return this.wj;
      }
      
      public int getWidth()
      {
        return this.w;
      }
      
      Set<Integer> hB()
      {
        return this.RM;
      }
      
      public boolean hasHeight()
      {
        return this.RM.contains(Integer.valueOf(2));
      }
      
      public boolean hasUrl()
      {
        return this.RM.contains(Integer.valueOf(3));
      }
      
      public boolean hasWidth()
      {
        return this.RM.contains(Integer.valueOf(4));
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
      
      public b ij()
      {
        return this;
      }
      
      public boolean isDataValid()
      {
        return true;
      }
      
      public void writeToParcel(Parcel paramParcel, int paramInt)
      {
        iw localiw = CREATOR;
        iw.a(this, paramParcel, paramInt);
      }
    }
  }
  
  public static final class c
    extends fb
    implements SafeParcelable, Person.Image
  {
    public static final ix CREATOR = new ix();
    private static final HashMap<String, fb.a<?, ?>> RL = new HashMap();
    private final Set<Integer> RM;
    private String pS;
    private final int wj;
    
    static
    {
      RL.put("url", fb.a.j("url", 2));
    }
    
    public c()
    {
      this.wj = 1;
      this.RM = new HashSet();
    }
    
    public c(String paramString)
    {
      this.RM = new HashSet();
      this.wj = 1;
      this.pS = paramString;
      this.RM.add(Integer.valueOf(2));
    }
    
    c(Set<Integer> paramSet, int paramInt, String paramString)
    {
      this.RM = paramSet;
      this.wj = paramInt;
      this.pS = paramString;
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
      default: 
        throw new IllegalStateException("Unknown safe parcelable id=" + parama.eu());
      }
      return this.pS;
    }
    
    public int describeContents()
    {
      ix localix = CREATOR;
      return 0;
    }
    
    public HashMap<String, fb.a<?, ?>> en()
    {
      return RL;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof c)) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      paramObject = (c)paramObject;
      Iterator localIterator = RL.values().iterator();
      while (localIterator.hasNext())
      {
        fb.a locala = (fb.a)localIterator.next();
        if (a(locala))
        {
          if (((c)paramObject).a(locala))
          {
            if (!b(locala).equals(((c)paramObject).b(locala))) {
              return false;
            }
          }
          else {
            return false;
          }
        }
        else if (((c)paramObject).a(locala)) {
          return false;
        }
      }
      return true;
    }
    
    public String getUrl()
    {
      return this.pS;
    }
    
    int getVersionCode()
    {
      return this.wj;
    }
    
    Set<Integer> hB()
    {
      return this.RM;
    }
    
    public boolean hasUrl()
    {
      return this.RM.contains(Integer.valueOf(2));
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
    
    public c ik()
    {
      return this;
    }
    
    public boolean isDataValid()
    {
      return true;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      ix localix = CREATOR;
      ix.a(this, paramParcel, paramInt);
    }
  }
  
  public static final class d
    extends fb
    implements SafeParcelable, Person.Name
  {
    public static final iy CREATOR = new iy();
    private static final HashMap<String, fb.a<?, ?>> RL = new HashMap();
    private final Set<Integer> RM;
    private String Sk;
    private String Sn;
    private String Tl;
    private String Tm;
    private String Tn;
    private String To;
    private final int wj;
    
    static
    {
      RL.put("familyName", fb.a.j("familyName", 2));
      RL.put("formatted", fb.a.j("formatted", 3));
      RL.put("givenName", fb.a.j("givenName", 4));
      RL.put("honorificPrefix", fb.a.j("honorificPrefix", 5));
      RL.put("honorificSuffix", fb.a.j("honorificSuffix", 6));
      RL.put("middleName", fb.a.j("middleName", 7));
    }
    
    public d()
    {
      this.wj = 1;
      this.RM = new HashSet();
    }
    
    d(Set<Integer> paramSet, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
    {
      this.RM = paramSet;
      this.wj = paramInt;
      this.Sk = paramString1;
      this.Tl = paramString2;
      this.Sn = paramString3;
      this.Tm = paramString4;
      this.Tn = paramString5;
      this.To = paramString6;
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
      default: 
        throw new IllegalStateException("Unknown safe parcelable id=" + parama.eu());
      case 2: 
        return this.Sk;
      case 3: 
        return this.Tl;
      case 4: 
        return this.Sn;
      case 5: 
        return this.Tm;
      case 6: 
        return this.Tn;
      }
      return this.To;
    }
    
    public int describeContents()
    {
      iy localiy = CREATOR;
      return 0;
    }
    
    public HashMap<String, fb.a<?, ?>> en()
    {
      return RL;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof d)) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      paramObject = (d)paramObject;
      Iterator localIterator = RL.values().iterator();
      while (localIterator.hasNext())
      {
        fb.a locala = (fb.a)localIterator.next();
        if (a(locala))
        {
          if (((d)paramObject).a(locala))
          {
            if (!b(locala).equals(((d)paramObject).b(locala))) {
              return false;
            }
          }
          else {
            return false;
          }
        }
        else if (((d)paramObject).a(locala)) {
          return false;
        }
      }
      return true;
    }
    
    public String getFamilyName()
    {
      return this.Sk;
    }
    
    public String getFormatted()
    {
      return this.Tl;
    }
    
    public String getGivenName()
    {
      return this.Sn;
    }
    
    public String getHonorificPrefix()
    {
      return this.Tm;
    }
    
    public String getHonorificSuffix()
    {
      return this.Tn;
    }
    
    public String getMiddleName()
    {
      return this.To;
    }
    
    int getVersionCode()
    {
      return this.wj;
    }
    
    Set<Integer> hB()
    {
      return this.RM;
    }
    
    public boolean hasFamilyName()
    {
      return this.RM.contains(Integer.valueOf(2));
    }
    
    public boolean hasFormatted()
    {
      return this.RM.contains(Integer.valueOf(3));
    }
    
    public boolean hasGivenName()
    {
      return this.RM.contains(Integer.valueOf(4));
    }
    
    public boolean hasHonorificPrefix()
    {
      return this.RM.contains(Integer.valueOf(5));
    }
    
    public boolean hasHonorificSuffix()
    {
      return this.RM.contains(Integer.valueOf(6));
    }
    
    public boolean hasMiddleName()
    {
      return this.RM.contains(Integer.valueOf(7));
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
    
    public d il()
    {
      return this;
    }
    
    public boolean isDataValid()
    {
      return true;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      iy localiy = CREATOR;
      iy.a(this, paramParcel, paramInt);
    }
  }
  
  public static class e
  {
    public static int aT(String paramString)
    {
      if (paramString.equals("person")) {
        return 0;
      }
      if (paramString.equals("page")) {
        return 1;
      }
      throw new IllegalArgumentException("Unknown objectType string: " + paramString);
    }
  }
  
  public static final class f
    extends fb
    implements SafeParcelable, Person.Organizations
  {
    public static final iz CREATOR = new iz();
    private static final HashMap<String, fb.a<?, ?>> RL = new HashMap();
    private int AI;
    private String CX;
    private String FH;
    private final Set<Integer> RM;
    private String Sj;
    private String Sz;
    private String Tp;
    private String Tq;
    private boolean Tr;
    private String mName;
    private final int wj;
    
    static
    {
      RL.put("department", fb.a.j("department", 2));
      RL.put("description", fb.a.j("description", 3));
      RL.put("endDate", fb.a.j("endDate", 4));
      RL.put("location", fb.a.j("location", 5));
      RL.put("name", fb.a.j("name", 6));
      RL.put("primary", fb.a.i("primary", 7));
      RL.put("startDate", fb.a.j("startDate", 8));
      RL.put("title", fb.a.j("title", 9));
      RL.put("type", fb.a.a("type", 10, new ey().f("work", 0).f("school", 1), false));
    }
    
    public f()
    {
      this.wj = 1;
      this.RM = new HashSet();
    }
    
    f(Set<Integer> paramSet, int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean, String paramString6, String paramString7, int paramInt2)
    {
      this.RM = paramSet;
      this.wj = paramInt1;
      this.Tp = paramString1;
      this.FH = paramString2;
      this.Sj = paramString3;
      this.Tq = paramString4;
      this.mName = paramString5;
      this.Tr = paramBoolean;
      this.Sz = paramString6;
      this.CX = paramString7;
      this.AI = paramInt2;
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
      default: 
        throw new IllegalStateException("Unknown safe parcelable id=" + parama.eu());
      case 2: 
        return this.Tp;
      case 3: 
        return this.FH;
      case 4: 
        return this.Sj;
      case 5: 
        return this.Tq;
      case 6: 
        return this.mName;
      case 7: 
        return Boolean.valueOf(this.Tr);
      case 8: 
        return this.Sz;
      case 9: 
        return this.CX;
      }
      return Integer.valueOf(this.AI);
    }
    
    public int describeContents()
    {
      iz localiz = CREATOR;
      return 0;
    }
    
    public HashMap<String, fb.a<?, ?>> en()
    {
      return RL;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof f)) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      paramObject = (f)paramObject;
      Iterator localIterator = RL.values().iterator();
      while (localIterator.hasNext())
      {
        fb.a locala = (fb.a)localIterator.next();
        if (a(locala))
        {
          if (((f)paramObject).a(locala))
          {
            if (!b(locala).equals(((f)paramObject).b(locala))) {
              return false;
            }
          }
          else {
            return false;
          }
        }
        else if (((f)paramObject).a(locala)) {
          return false;
        }
      }
      return true;
    }
    
    public String getDepartment()
    {
      return this.Tp;
    }
    
    public String getDescription()
    {
      return this.FH;
    }
    
    public String getEndDate()
    {
      return this.Sj;
    }
    
    public String getLocation()
    {
      return this.Tq;
    }
    
    public String getName()
    {
      return this.mName;
    }
    
    public String getStartDate()
    {
      return this.Sz;
    }
    
    public String getTitle()
    {
      return this.CX;
    }
    
    public int getType()
    {
      return this.AI;
    }
    
    int getVersionCode()
    {
      return this.wj;
    }
    
    Set<Integer> hB()
    {
      return this.RM;
    }
    
    public boolean hasDepartment()
    {
      return this.RM.contains(Integer.valueOf(2));
    }
    
    public boolean hasDescription()
    {
      return this.RM.contains(Integer.valueOf(3));
    }
    
    public boolean hasEndDate()
    {
      return this.RM.contains(Integer.valueOf(4));
    }
    
    public boolean hasLocation()
    {
      return this.RM.contains(Integer.valueOf(5));
    }
    
    public boolean hasName()
    {
      return this.RM.contains(Integer.valueOf(6));
    }
    
    public boolean hasPrimary()
    {
      return this.RM.contains(Integer.valueOf(7));
    }
    
    public boolean hasStartDate()
    {
      return this.RM.contains(Integer.valueOf(8));
    }
    
    public boolean hasTitle()
    {
      return this.RM.contains(Integer.valueOf(9));
    }
    
    public boolean hasType()
    {
      return this.RM.contains(Integer.valueOf(10));
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
    
    public f im()
    {
      return this;
    }
    
    public boolean isDataValid()
    {
      return true;
    }
    
    public boolean isPrimary()
    {
      return this.Tr;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      iz localiz = CREATOR;
      iz.a(this, paramParcel, paramInt);
    }
  }
  
  public static final class g
    extends fb
    implements SafeParcelable, Person.PlacesLived
  {
    public static final ja CREATOR = new ja();
    private static final HashMap<String, fb.a<?, ?>> RL = new HashMap();
    private final Set<Integer> RM;
    private boolean Tr;
    private String mValue;
    private final int wj;
    
    static
    {
      RL.put("primary", fb.a.i("primary", 2));
      RL.put("value", fb.a.j("value", 3));
    }
    
    public g()
    {
      this.wj = 1;
      this.RM = new HashSet();
    }
    
    g(Set<Integer> paramSet, int paramInt, boolean paramBoolean, String paramString)
    {
      this.RM = paramSet;
      this.wj = paramInt;
      this.Tr = paramBoolean;
      this.mValue = paramString;
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
      default: 
        throw new IllegalStateException("Unknown safe parcelable id=" + parama.eu());
      case 2: 
        return Boolean.valueOf(this.Tr);
      }
      return this.mValue;
    }
    
    public int describeContents()
    {
      ja localja = CREATOR;
      return 0;
    }
    
    public HashMap<String, fb.a<?, ?>> en()
    {
      return RL;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof g)) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      paramObject = (g)paramObject;
      Iterator localIterator = RL.values().iterator();
      while (localIterator.hasNext())
      {
        fb.a locala = (fb.a)localIterator.next();
        if (a(locala))
        {
          if (((g)paramObject).a(locala))
          {
            if (!b(locala).equals(((g)paramObject).b(locala))) {
              return false;
            }
          }
          else {
            return false;
          }
        }
        else if (((g)paramObject).a(locala)) {
          return false;
        }
      }
      return true;
    }
    
    public String getValue()
    {
      return this.mValue;
    }
    
    int getVersionCode()
    {
      return this.wj;
    }
    
    Set<Integer> hB()
    {
      return this.RM;
    }
    
    public boolean hasPrimary()
    {
      return this.RM.contains(Integer.valueOf(2));
    }
    
    public boolean hasValue()
    {
      return this.RM.contains(Integer.valueOf(3));
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
    
    public g in()
    {
      return this;
    }
    
    public boolean isDataValid()
    {
      return true;
    }
    
    public boolean isPrimary()
    {
      return this.Tr;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      ja localja = CREATOR;
      ja.a(this, paramParcel, paramInt);
    }
  }
  
  public static final class h
    extends fb
    implements SafeParcelable, Person.Urls
  {
    public static final jb CREATOR = new jb();
    private static final HashMap<String, fb.a<?, ?>> RL = new HashMap();
    private int AI;
    private final Set<Integer> RM;
    private String Ts;
    private final int Tt = 4;
    private String mValue;
    private final int wj;
    
    static
    {
      RL.put("label", fb.a.j("label", 5));
      RL.put("type", fb.a.a("type", 6, new ey().f("home", 0).f("work", 1).f("blog", 2).f("profile", 3).f("other", 4).f("otherProfile", 5).f("contributor", 6).f("website", 7), false));
      RL.put("value", fb.a.j("value", 4));
    }
    
    public h()
    {
      this.wj = 2;
      this.RM = new HashSet();
    }
    
    h(Set<Integer> paramSet, int paramInt1, String paramString1, int paramInt2, String paramString2, int paramInt3)
    {
      this.RM = paramSet;
      this.wj = paramInt1;
      this.Ts = paramString1;
      this.AI = paramInt2;
      this.mValue = paramString2;
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
      default: 
        throw new IllegalStateException("Unknown safe parcelable id=" + parama.eu());
      case 5: 
        return this.Ts;
      case 6: 
        return Integer.valueOf(this.AI);
      }
      return this.mValue;
    }
    
    public int describeContents()
    {
      jb localjb = CREATOR;
      return 0;
    }
    
    public HashMap<String, fb.a<?, ?>> en()
    {
      return RL;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof h)) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      paramObject = (h)paramObject;
      Iterator localIterator = RL.values().iterator();
      while (localIterator.hasNext())
      {
        fb.a locala = (fb.a)localIterator.next();
        if (a(locala))
        {
          if (((h)paramObject).a(locala))
          {
            if (!b(locala).equals(((h)paramObject).b(locala))) {
              return false;
            }
          }
          else {
            return false;
          }
        }
        else if (((h)paramObject).a(locala)) {
          return false;
        }
      }
      return true;
    }
    
    public String getLabel()
    {
      return this.Ts;
    }
    
    public int getType()
    {
      return this.AI;
    }
    
    public String getValue()
    {
      return this.mValue;
    }
    
    int getVersionCode()
    {
      return this.wj;
    }
    
    Set<Integer> hB()
    {
      return this.RM;
    }
    
    public boolean hasLabel()
    {
      return this.RM.contains(Integer.valueOf(5));
    }
    
    public boolean hasType()
    {
      return this.RM.contains(Integer.valueOf(6));
    }
    
    public boolean hasValue()
    {
      return this.RM.contains(Integer.valueOf(4));
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
    
    @Deprecated
    public int io()
    {
      return 4;
    }
    
    public h ip()
    {
      return this;
    }
    
    public boolean isDataValid()
    {
      return true;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      jb localjb = CREATOR;
      jb.a(this, paramParcel, paramInt);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ir.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */