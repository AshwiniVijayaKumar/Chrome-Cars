package org.apache.commons.lang;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Entities
{
  private static final String[][] APOS_ARRAY;
  private static final String[][] BASIC_ARRAY = { { "quot", "34" }, { "amp", "38" }, { "lt", "60" }, { "gt", "62" } };
  public static final Entities HTML32;
  public static final Entities HTML40;
  static final String[][] HTML40_ARRAY;
  static final String[][] ISO8859_1_ARRAY;
  public static final Entities XML;
  private final EntityMap map;
  
  static
  {
    APOS_ARRAY = new String[][] { { "apos", "39" } };
    Object localObject = { "aacute", "225" };
    ISO8859_1_ARRAY = new String[][] { { "nbsp", "160" }, { "iexcl", "161" }, { "cent", "162" }, { "pound", "163" }, { "curren", "164" }, { "yen", "165" }, { "brvbar", "166" }, { "sect", "167" }, { "uml", "168" }, { "copy", "169" }, { "ordf", "170" }, { "laquo", "171" }, { "not", "172" }, { "shy", "173" }, { "reg", "174" }, { "macr", "175" }, { "deg", "176" }, { "plusmn", "177" }, { "sup2", "178" }, { "sup3", "179" }, { "acute", "180" }, { "micro", "181" }, { "para", "182" }, { "middot", "183" }, { "cedil", "184" }, { "sup1", "185" }, { "ordm", "186" }, { "raquo", "187" }, { "frac14", "188" }, { "frac12", "189" }, { "frac34", "190" }, { "iquest", "191" }, { "Agrave", "192" }, { "Aacute", "193" }, { "Acirc", "194" }, { "Atilde", "195" }, { "Auml", "196" }, { "Aring", "197" }, { "AElig", "198" }, { "Ccedil", "199" }, { "Egrave", "200" }, { "Eacute", "201" }, { "Ecirc", "202" }, { "Euml", "203" }, { "Igrave", "204" }, { "Iacute", "205" }, { "Icirc", "206" }, { "Iuml", "207" }, { "ETH", "208" }, { "Ntilde", "209" }, { "Ograve", "210" }, { "Oacute", "211" }, { "Ocirc", "212" }, { "Otilde", "213" }, { "Ouml", "214" }, { "times", "215" }, { "Oslash", "216" }, { "Ugrave", "217" }, { "Uacute", "218" }, { "Ucirc", "219" }, { "Uuml", "220" }, { "Yacute", "221" }, { "THORN", "222" }, { "szlig", "223" }, { "agrave", "224" }, localObject, { "acirc", "226" }, { "atilde", "227" }, { "auml", "228" }, { "aring", "229" }, { "aelig", "230" }, { "ccedil", "231" }, { "egrave", "232" }, { "eacute", "233" }, { "ecirc", "234" }, { "euml", "235" }, { "igrave", "236" }, { "iacute", "237" }, { "icirc", "238" }, { "iuml", "239" }, { "eth", "240" }, { "ntilde", "241" }, { "ograve", "242" }, { "oacute", "243" }, { "ocirc", "244" }, { "otilde", "245" }, { "ouml", "246" }, { "divide", "247" }, { "oslash", "248" }, { "ugrave", "249" }, { "uacute", "250" }, { "ucirc", "251" }, { "uuml", "252" }, { "yacute", "253" }, { "thorn", "254" }, { "yuml", "255" } };
    localObject = new String[] { "fnof", "402" };
    String[] arrayOfString1 = { "Alpha", "913" };
    String[] arrayOfString2 = { "Beta", "914" };
    String[] arrayOfString3 = { "Gamma", "915" };
    String[] arrayOfString4 = { "Delta", "916" };
    String[] arrayOfString5 = { "Epsilon", "917" };
    String[] arrayOfString6 = { "Zeta", "918" };
    String[] arrayOfString7 = { "Eta", "919" };
    String[] arrayOfString8 = { "Iota", "921" };
    String[] arrayOfString9 = { "Kappa", "922" };
    String[] arrayOfString10 = { "Lambda", "923" };
    String[] arrayOfString11 = { "Mu", "924" };
    String[] arrayOfString12 = { "Nu", "925" };
    String[] arrayOfString13 = { "Xi", "926" };
    String[] arrayOfString14 = { "Omicron", "927" };
    String[] arrayOfString15 = { "Pi", "928" };
    String[] arrayOfString16 = { "Rho", "929" };
    String[] arrayOfString17 = { "Sigma", "931" };
    String[] arrayOfString18 = { "Tau", "932" };
    String[] arrayOfString19 = { "Upsilon", "933" };
    String[] arrayOfString20 = { "Phi", "934" };
    String[] arrayOfString21 = { "Chi", "935" };
    String[] arrayOfString22 = { "Omega", "937" };
    String[] arrayOfString23 = { "alpha", "945" };
    String[] arrayOfString24 = { "beta", "946" };
    String[] arrayOfString25 = { "gamma", "947" };
    String[] arrayOfString26 = { "delta", "948" };
    String[] arrayOfString27 = { "epsilon", "949" };
    String[] arrayOfString28 = { "zeta", "950" };
    String[] arrayOfString29 = { "eta", "951" };
    String[] arrayOfString30 = { "theta", "952" };
    String[] arrayOfString31 = { "iota", "953" };
    String[] arrayOfString32 = { "kappa", "954" };
    String[] arrayOfString33 = { "lambda", "955" };
    String[] arrayOfString34 = { "mu", "956" };
    String[] arrayOfString35 = { "nu", "957" };
    String[] arrayOfString36 = { "xi", "958" };
    String[] arrayOfString37 = { "omicron", "959" };
    String[] arrayOfString38 = { "pi", "960" };
    String[] arrayOfString39 = { "rho", "961" };
    String[] arrayOfString40 = { "sigmaf", "962" };
    String[] arrayOfString41 = { "sigma", "963" };
    String[] arrayOfString42 = { "tau", "964" };
    String[] arrayOfString43 = { "upsilon", "965" };
    String[] arrayOfString44 = { "phi", "966" };
    String[] arrayOfString45 = { "chi", "967" };
    String[] arrayOfString46 = { "psi", "968" };
    String[] arrayOfString47 = { "omega", "969" };
    String[] arrayOfString48 = { "thetasym", "977" };
    String[] arrayOfString49 = { "upsih", "978" };
    String[] arrayOfString50 = { "piv", "982" };
    String[] arrayOfString51 = { "bull", "8226" };
    String[] arrayOfString52 = { "hellip", "8230" };
    String[] arrayOfString53 = { "prime", "8242" };
    String[] arrayOfString54 = { "Prime", "8243" };
    String[] arrayOfString55 = { "oline", "8254" };
    String[] arrayOfString56 = { "frasl", "8260" };
    String[] arrayOfString57 = { "weierp", "8472" };
    String[] arrayOfString58 = { "image", "8465" };
    String[] arrayOfString59 = { "trade", "8482" };
    String[] arrayOfString60 = { "alefsym", "8501" };
    String[] arrayOfString61 = { "larr", "8592" };
    String[] arrayOfString62 = { "rarr", "8594" };
    String[] arrayOfString63 = { "darr", "8595" };
    String[] arrayOfString64 = { "harr", "8596" };
    String[] arrayOfString65 = { "crarr", "8629" };
    String[] arrayOfString66 = { "lArr", "8656" };
    String[] arrayOfString67 = { "uArr", "8657" };
    String[] arrayOfString68 = { "rArr", "8658" };
    String[] arrayOfString69 = { "dArr", "8659" };
    String[] arrayOfString70 = { "hArr", "8660" };
    String[] arrayOfString71 = { "forall", "8704" };
    String[] arrayOfString72 = { "part", "8706" };
    String[] arrayOfString73 = { "exist", "8707" };
    String[] arrayOfString74 = { "empty", "8709" };
    String[] arrayOfString75 = { "nabla", "8711" };
    String[] arrayOfString76 = { "isin", "8712" };
    String[] arrayOfString77 = { "notin", "8713" };
    String[] arrayOfString78 = { "ni", "8715" };
    String[] arrayOfString79 = { "prod", "8719" };
    String[] arrayOfString80 = { "sum", "8721" };
    String[] arrayOfString81 = { "minus", "8722" };
    String[] arrayOfString82 = { "lowast", "8727" };
    String[] arrayOfString83 = { "radic", "8730" };
    String[] arrayOfString84 = { "prop", "8733" };
    String[] arrayOfString85 = { "infin", "8734" };
    String[] arrayOfString86 = { "or", "8744" };
    String[] arrayOfString87 = { "cap", "8745" };
    String[] arrayOfString88 = { "cup", "8746" };
    String[] arrayOfString89 = { "int", "8747" };
    String[] arrayOfString90 = { "there4", "8756" };
    String[] arrayOfString91 = { "sim", "8764" };
    String[] arrayOfString92 = { "cong", "8773" };
    String[] arrayOfString93 = { "asymp", "8776" };
    String[] arrayOfString94 = { "ne", "8800" };
    String[] arrayOfString95 = { "equiv", "8801" };
    String[] arrayOfString96 = { "le", "8804" };
    String[] arrayOfString97 = { "ge", "8805" };
    String[] arrayOfString98 = { "sub", "8834" };
    String[] arrayOfString99 = { "supe", "8839" };
    String[] arrayOfString100 = { "oplus", "8853" };
    String[] arrayOfString101 = { "otimes", "8855" };
    String[] arrayOfString102 = { "perp", "8869" };
    String[] arrayOfString103 = { "sdot", "8901" };
    String[] arrayOfString104 = { "lceil", "8968" };
    String[] arrayOfString105 = { "rceil", "8969" };
    String[] arrayOfString106 = { "lfloor", "8970" };
    String[] arrayOfString107 = { "rfloor", "8971" };
    String[] arrayOfString108 = { "lang", "9001" };
    String[] arrayOfString109 = { "rang", "9002" };
    String[] arrayOfString110 = { "loz", "9674" };
    String[] arrayOfString111 = { "spades", "9824" };
    String[] arrayOfString112 = { "clubs", "9827" };
    String[] arrayOfString113 = { "hearts", "9829" };
    String[] arrayOfString114 = { "diams", "9830" };
    String[] arrayOfString115 = { "Scaron", "352" };
    String[] arrayOfString116 = { "scaron", "353" };
    String[] arrayOfString117 = { "Yuml", "376" };
    String[] arrayOfString118 = { "circ", "710" };
    String[] arrayOfString119 = { "tilde", "732" };
    String[] arrayOfString120 = { "ensp", "8194" };
    String[] arrayOfString121 = { "emsp", "8195" };
    String[] arrayOfString122 = { "thinsp", "8201" };
    String[] arrayOfString123 = { "zwnj", "8204" };
    String[] arrayOfString124 = { "zwj", "8205" };
    String[] arrayOfString125 = { "lrm", "8206" };
    String[] arrayOfString126 = { "rlm", "8207" };
    String[] arrayOfString127 = { "ndash", "8211" };
    String[] arrayOfString128 = { "mdash", "8212" };
    String[] arrayOfString129 = { "lsquo", "8216" };
    String[] arrayOfString130 = { "rsquo", "8217" };
    String[] arrayOfString131 = { "sbquo", "8218" };
    String[] arrayOfString132 = { "ldquo", "8220" };
    String[] arrayOfString133 = { "rdquo", "8221" };
    String[] arrayOfString134 = { "bdquo", "8222" };
    String[] arrayOfString135 = { "dagger", "8224" };
    String[] arrayOfString136 = { "Dagger", "8225" };
    String[] arrayOfString137 = { "permil", "8240" };
    String[] arrayOfString138 = { "lsaquo", "8249" };
    String[] arrayOfString139 = { "rsaquo", "8250" };
    String[] arrayOfString140 = { "euro", "8364" };
    HTML40_ARRAY = new String[][] { localObject, arrayOfString1, arrayOfString2, arrayOfString3, arrayOfString4, arrayOfString5, arrayOfString6, arrayOfString7, { "Theta", "920" }, arrayOfString8, arrayOfString9, arrayOfString10, arrayOfString11, arrayOfString12, arrayOfString13, arrayOfString14, arrayOfString15, arrayOfString16, arrayOfString17, arrayOfString18, arrayOfString19, arrayOfString20, arrayOfString21, { "Psi", "936" }, arrayOfString22, arrayOfString23, arrayOfString24, arrayOfString25, arrayOfString26, arrayOfString27, arrayOfString28, arrayOfString29, arrayOfString30, arrayOfString31, arrayOfString32, arrayOfString33, arrayOfString34, arrayOfString35, arrayOfString36, arrayOfString37, arrayOfString38, arrayOfString39, arrayOfString40, arrayOfString41, arrayOfString42, arrayOfString43, arrayOfString44, arrayOfString45, arrayOfString46, arrayOfString47, arrayOfString48, arrayOfString49, arrayOfString50, arrayOfString51, arrayOfString52, arrayOfString53, arrayOfString54, arrayOfString55, arrayOfString56, arrayOfString57, arrayOfString58, { "real", "8476" }, arrayOfString59, arrayOfString60, arrayOfString61, { "uarr", "8593" }, arrayOfString62, arrayOfString63, arrayOfString64, arrayOfString65, arrayOfString66, arrayOfString67, arrayOfString68, arrayOfString69, arrayOfString70, arrayOfString71, arrayOfString72, arrayOfString73, arrayOfString74, arrayOfString75, arrayOfString76, arrayOfString77, arrayOfString78, arrayOfString79, arrayOfString80, arrayOfString81, arrayOfString82, arrayOfString83, arrayOfString84, arrayOfString85, { "ang", "8736" }, { "and", "8743" }, arrayOfString86, arrayOfString87, arrayOfString88, arrayOfString89, arrayOfString90, arrayOfString91, arrayOfString92, arrayOfString93, arrayOfString94, arrayOfString95, arrayOfString96, arrayOfString97, arrayOfString98, { "sup", "8835" }, { "sube", "8838" }, arrayOfString99, arrayOfString100, arrayOfString101, arrayOfString102, arrayOfString103, arrayOfString104, arrayOfString105, arrayOfString106, arrayOfString107, arrayOfString108, arrayOfString109, arrayOfString110, arrayOfString111, arrayOfString112, arrayOfString113, arrayOfString114, { "OElig", "338" }, { "oelig", "339" }, arrayOfString115, arrayOfString116, arrayOfString117, arrayOfString118, arrayOfString119, arrayOfString120, arrayOfString121, arrayOfString122, arrayOfString123, arrayOfString124, arrayOfString125, arrayOfString126, arrayOfString127, arrayOfString128, arrayOfString129, arrayOfString130, arrayOfString131, arrayOfString132, arrayOfString133, arrayOfString134, arrayOfString135, arrayOfString136, arrayOfString137, arrayOfString138, arrayOfString139, arrayOfString140 };
    localObject = new Entities();
    ((Entities)localObject).addEntities(BASIC_ARRAY);
    ((Entities)localObject).addEntities(APOS_ARRAY);
    XML = (Entities)localObject;
    localObject = new Entities();
    ((Entities)localObject).addEntities(BASIC_ARRAY);
    ((Entities)localObject).addEntities(ISO8859_1_ARRAY);
    HTML32 = (Entities)localObject;
    localObject = new Entities();
    fillWithHtml40Entities((Entities)localObject);
    HTML40 = (Entities)localObject;
  }
  
  public Entities()
  {
    this.map = new LookupEntityMap();
  }
  
  Entities(EntityMap paramEntityMap)
  {
    this.map = paramEntityMap;
  }
  
  private StringWriter createStringWriter(String paramString)
  {
    return new StringWriter((int)(paramString.length() + paramString.length() * 0.1D));
  }
  
  private void doUnescape(Writer paramWriter, String paramString, int paramInt)
    throws IOException
  {
    paramWriter.write(paramString, 0, paramInt);
    int k = paramString.length();
    if (paramInt < k)
    {
      int i = paramString.charAt(paramInt);
      int m;
      int j;
      if (i == 38)
      {
        m = paramInt + 1;
        j = paramString.indexOf(';', m);
        if (j == -1) {
          paramWriter.write(i);
        }
      }
      for (;;)
      {
        paramInt += 1;
        break;
        int n = paramString.indexOf('&', paramInt + 1);
        if ((n != -1) && (n < j))
        {
          paramWriter.write(i);
        }
        else
        {
          String str = paramString.substring(m, j);
          i = -1;
          m = str.length();
          paramInt = i;
          if (m > 0)
          {
            if (str.charAt(0) != '#') {
              break label258;
            }
            paramInt = i;
            if (m > 1) {
              switch (str.charAt(1))
              {
              }
            }
          }
          try
          {
            i = Integer.parseInt(str.substring(1), 10);
            label193:
            paramInt = i;
            if (i > 65535) {
              paramInt = -1;
            }
          }
          catch (NumberFormatException localNumberFormatException)
          {
            for (;;)
            {
              label206:
              paramInt = -1;
            }
          }
          if (paramInt == -1)
          {
            paramWriter.write(38);
            paramWriter.write(str);
            paramWriter.write(59);
          }
          for (;;)
          {
            paramInt = j;
            break;
            i = Integer.parseInt(str.substring(2), 16);
            break label193;
            label258:
            paramInt = entityValue(str);
            break label206;
            paramWriter.write(paramInt);
          }
          paramWriter.write(i);
        }
      }
    }
  }
  
  static void fillWithHtml40Entities(Entities paramEntities)
  {
    paramEntities.addEntities(BASIC_ARRAY);
    paramEntities.addEntities(ISO8859_1_ARRAY);
    paramEntities.addEntities(HTML40_ARRAY);
  }
  
  public void addEntities(String[][] paramArrayOfString)
  {
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      addEntity(paramArrayOfString[i][0], Integer.parseInt(paramArrayOfString[i][1]));
      i += 1;
    }
  }
  
  public void addEntity(String paramString, int paramInt)
  {
    this.map.add(paramString, paramInt);
  }
  
  public String entityName(int paramInt)
  {
    return this.map.name(paramInt);
  }
  
  public int entityValue(String paramString)
  {
    return this.map.value(paramString);
  }
  
  public String escape(String paramString)
  {
    StringWriter localStringWriter = createStringWriter(paramString);
    try
    {
      escape(localStringWriter, paramString);
      return localStringWriter.toString();
    }
    catch (IOException paramString)
    {
      throw new UnhandledException(paramString);
    }
  }
  
  public void escape(Writer paramWriter, String paramString)
    throws IOException
  {
    int j = paramString.length();
    int i = 0;
    if (i < j)
    {
      int k = paramString.charAt(i);
      String str = entityName(k);
      if (str == null) {
        if (k > 127)
        {
          paramWriter.write("&#");
          paramWriter.write(Integer.toString(k, 10));
          paramWriter.write(59);
        }
      }
      for (;;)
      {
        i += 1;
        break;
        paramWriter.write(k);
        continue;
        paramWriter.write(38);
        paramWriter.write(str);
        paramWriter.write(59);
      }
    }
  }
  
  public String unescape(String paramString)
  {
    int i = paramString.indexOf('&');
    if (i < 0) {
      return paramString;
    }
    StringWriter localStringWriter = createStringWriter(paramString);
    try
    {
      doUnescape(localStringWriter, paramString, i);
      return localStringWriter.toString();
    }
    catch (IOException paramString)
    {
      throw new UnhandledException(paramString);
    }
  }
  
  public void unescape(Writer paramWriter, String paramString)
    throws IOException
  {
    int i = paramString.indexOf('&');
    if (i < 0)
    {
      paramWriter.write(paramString);
      return;
    }
    doUnescape(paramWriter, paramString, i);
  }
  
  static class ArrayEntityMap
    implements Entities.EntityMap
  {
    protected final int growBy;
    protected String[] names;
    protected int size = 0;
    protected int[] values;
    
    public ArrayEntityMap()
    {
      this.growBy = 100;
      this.names = new String[this.growBy];
      this.values = new int[this.growBy];
    }
    
    public ArrayEntityMap(int paramInt)
    {
      this.growBy = paramInt;
      this.names = new String[paramInt];
      this.values = new int[paramInt];
    }
    
    public void add(String paramString, int paramInt)
    {
      ensureCapacity(this.size + 1);
      this.names[this.size] = paramString;
      this.values[this.size] = paramInt;
      this.size += 1;
    }
    
    protected void ensureCapacity(int paramInt)
    {
      if (paramInt > this.names.length)
      {
        paramInt = Math.max(paramInt, this.size + this.growBy);
        Object localObject = new String[paramInt];
        System.arraycopy(this.names, 0, localObject, 0, this.size);
        this.names = ((String[])localObject);
        localObject = new int[paramInt];
        System.arraycopy(this.values, 0, localObject, 0, this.size);
        this.values = ((int[])localObject);
      }
    }
    
    public String name(int paramInt)
    {
      int i = 0;
      while (i < this.size)
      {
        if (this.values[i] == paramInt) {
          return this.names[i];
        }
        i += 1;
      }
      return null;
    }
    
    public int value(String paramString)
    {
      int i = 0;
      while (i < this.size)
      {
        if (this.names[i].equals(paramString)) {
          return this.values[i];
        }
        i += 1;
      }
      return -1;
    }
  }
  
  static class BinaryEntityMap
    extends Entities.ArrayEntityMap
  {
    public BinaryEntityMap() {}
    
    public BinaryEntityMap(int paramInt)
    {
      super();
    }
    
    private int binarySearch(int paramInt)
    {
      int i = 0;
      int j = this.size - 1;
      while (i <= j)
      {
        int k = i + j >>> 1;
        int m = this.values[k];
        if (m < paramInt)
        {
          i = k + 1;
        }
        else
        {
          j = k;
          if (m <= paramInt) {
            return j;
          }
          j = k - 1;
        }
      }
      j = -(i + 1);
      return j;
    }
    
    public void add(String paramString, int paramInt)
    {
      ensureCapacity(this.size + 1);
      int i = binarySearch(paramInt);
      if (i > 0) {
        return;
      }
      i = -(i + 1);
      System.arraycopy(this.values, i, this.values, i + 1, this.size - i);
      this.values[i] = paramInt;
      System.arraycopy(this.names, i, this.names, i + 1, this.size - i);
      this.names[i] = paramString;
      this.size += 1;
    }
    
    public String name(int paramInt)
    {
      paramInt = binarySearch(paramInt);
      if (paramInt < 0) {
        return null;
      }
      return this.names[paramInt];
    }
  }
  
  static abstract interface EntityMap
  {
    public abstract void add(String paramString, int paramInt);
    
    public abstract String name(int paramInt);
    
    public abstract int value(String paramString);
  }
  
  static class HashEntityMap
    extends Entities.MapIntMap
  {
    public HashEntityMap()
    {
      super(new HashMap());
    }
  }
  
  static class LookupEntityMap
    extends Entities.PrimitiveEntityMap
  {
    private static final int LOOKUP_TABLE_SIZE = 256;
    private String[] lookupTable;
    
    private void createLookupTable()
    {
      this.lookupTable = new String['Ā'];
      int i = 0;
      while (i < 256)
      {
        this.lookupTable[i] = super.name(i);
        i += 1;
      }
    }
    
    private String[] lookupTable()
    {
      if (this.lookupTable == null) {
        createLookupTable();
      }
      return this.lookupTable;
    }
    
    public String name(int paramInt)
    {
      if (paramInt < 256) {
        return lookupTable()[paramInt];
      }
      return super.name(paramInt);
    }
  }
  
  static abstract class MapIntMap
    implements Entities.EntityMap
  {
    protected final Map mapNameToValue;
    protected final Map mapValueToName;
    
    MapIntMap(Map paramMap1, Map paramMap2)
    {
      this.mapNameToValue = paramMap1;
      this.mapValueToName = paramMap2;
    }
    
    public void add(String paramString, int paramInt)
    {
      this.mapNameToValue.put(paramString, new Integer(paramInt));
      this.mapValueToName.put(new Integer(paramInt), paramString);
    }
    
    public String name(int paramInt)
    {
      return (String)this.mapValueToName.get(new Integer(paramInt));
    }
    
    public int value(String paramString)
    {
      paramString = this.mapNameToValue.get(paramString);
      if (paramString == null) {
        return -1;
      }
      return ((Integer)paramString).intValue();
    }
  }
  
  static class PrimitiveEntityMap
    implements Entities.EntityMap
  {
    private final Map mapNameToValue = new HashMap();
    private final IntHashMap mapValueToName = new IntHashMap();
    
    public void add(String paramString, int paramInt)
    {
      this.mapNameToValue.put(paramString, new Integer(paramInt));
      this.mapValueToName.put(paramInt, paramString);
    }
    
    public String name(int paramInt)
    {
      return (String)this.mapValueToName.get(paramInt);
    }
    
    public int value(String paramString)
    {
      paramString = this.mapNameToValue.get(paramString);
      if (paramString == null) {
        return -1;
      }
      return ((Integer)paramString).intValue();
    }
  }
  
  static class TreeEntityMap
    extends Entities.MapIntMap
  {
    public TreeEntityMap()
    {
      super(new TreeMap());
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\commons\lang\Entities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */