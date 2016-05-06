package org.ksoap2.serialization;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public class SoapSerializationEnvelope
  extends SoapEnvelope
{
  private static final String ANY_TYPE_LABEL = "anyType";
  private static final String ARRAY_MAPPING_NAME = "Array";
  private static final String ARRAY_TYPE_LABEL = "arrayType";
  static final Marshal DEFAULT_MARSHAL = new DM();
  private static final String HREF_LABEL = "href";
  private static final String ID_LABEL = "id";
  private static final String ITEM_LABEL = "item";
  private static final String NIL_LABEL = "nil";
  private static final String NULL_LABEL = "null";
  protected static final int QNAME_MARSHAL = 3;
  protected static final int QNAME_NAMESPACE = 0;
  protected static final int QNAME_TYPE = 1;
  private static final String ROOT_LABEL = "root";
  private static final String TYPE_LABEL = "type";
  protected boolean addAdornments = true;
  protected Hashtable classToQName = new Hashtable();
  public boolean dotNet;
  Hashtable idMap = new Hashtable();
  public boolean implicitTypes;
  Vector multiRef;
  public Hashtable properties = new Hashtable();
  protected Hashtable qNameToClass = new Hashtable();
  
  public SoapSerializationEnvelope(int paramInt)
  {
    super(paramInt);
    addMapping(this.enc, "Array", PropertyInfo.VECTOR_CLASS);
    DEFAULT_MARSHAL.register(this);
  }
  
  private int getIndex(String paramString, int paramInt1, int paramInt2)
  {
    if (paramString == null) {}
    while (paramString.length() - paramInt1 < 3) {
      return paramInt2;
    }
    return Integer.parseInt(paramString.substring(paramInt1 + 1, paramString.length() - 1));
  }
  
  private void writeElement(XmlSerializer paramXmlSerializer, Object paramObject1, PropertyInfo paramPropertyInfo, Object paramObject2)
    throws IOException
  {
    if (paramObject2 != null)
    {
      ((Marshal)paramObject2).writeInstance(paramXmlSerializer, paramObject1);
      return;
    }
    if ((paramObject1 instanceof SoapObject))
    {
      writeObjectBody(paramXmlSerializer, (SoapObject)paramObject1);
      return;
    }
    if ((paramObject1 instanceof KvmSerializable))
    {
      writeObjectBody(paramXmlSerializer, (KvmSerializable)paramObject1);
      return;
    }
    if ((paramObject1 instanceof Vector))
    {
      writeVectorBody(paramXmlSerializer, (Vector)paramObject1, paramPropertyInfo.elementType);
      return;
    }
    throw new RuntimeException("Cannot serialize: " + paramObject1);
  }
  
  public void addMapping(String paramString1, String paramString2, Class paramClass)
  {
    addMapping(paramString1, paramString2, paramClass, null);
  }
  
  public void addMapping(String paramString1, String paramString2, Class paramClass, Marshal paramMarshal)
  {
    Hashtable localHashtable = this.qNameToClass;
    SoapPrimitive localSoapPrimitive = new SoapPrimitive(paramString1, paramString2, null);
    if (paramMarshal == null) {}
    for (Object localObject = paramClass;; localObject = paramMarshal)
    {
      localHashtable.put(localSoapPrimitive, localObject);
      this.classToQName.put(paramClass.getName(), new Object[] { paramString1, paramString2, null, paramMarshal });
      return;
    }
  }
  
  public void addTemplate(SoapObject paramSoapObject)
  {
    this.qNameToClass.put(new SoapPrimitive(paramSoapObject.namespace, paramSoapObject.name, null), paramSoapObject);
  }
  
  public Object[] getInfo(Object paramObject1, Object paramObject2)
  {
    Object localObject = paramObject1;
    if (paramObject1 == null)
    {
      if (((paramObject2 instanceof SoapObject)) || ((paramObject2 instanceof SoapPrimitive))) {
        localObject = paramObject2;
      }
    }
    else
    {
      if (!(localObject instanceof SoapObject)) {
        break label71;
      }
      paramObject2 = (SoapObject)localObject;
      paramObject1 = new Object[4];
      paramObject1[0] = ((SoapObject)paramObject2).getNamespace();
      paramObject1[1] = ((SoapObject)paramObject2).getName();
      paramObject1[2] = null;
      paramObject1[3] = null;
    }
    label71:
    do
    {
      return (Object[])paramObject1;
      localObject = paramObject2.getClass();
      break;
      if ((localObject instanceof SoapPrimitive))
      {
        paramObject1 = (SoapPrimitive)localObject;
        return new Object[] { ((SoapPrimitive)paramObject1).getNamespace(), ((SoapPrimitive)paramObject1).getName(), null, DEFAULT_MARSHAL };
      }
      if ((!(localObject instanceof Class)) || (localObject == PropertyInfo.OBJECT_CLASS)) {
        break label153;
      }
      paramObject2 = (Object[])this.classToQName.get(((Class)localObject).getName());
      paramObject1 = paramObject2;
    } while (paramObject2 != null);
    label153:
    return new Object[] { this.xsd, "anyType", null, null };
  }
  
  public Object getResponse()
    throws SoapFault
  {
    if ((this.bodyIn instanceof SoapFault)) {
      throw ((SoapFault)this.bodyIn);
    }
    KvmSerializable localKvmSerializable = (KvmSerializable)this.bodyIn;
    if (localKvmSerializable.getPropertyCount() == 0) {
      return null;
    }
    return localKvmSerializable.getProperty(0);
  }
  
  public Object getResult()
  {
    KvmSerializable localKvmSerializable = (KvmSerializable)this.bodyIn;
    if (localKvmSerializable.getPropertyCount() == 0) {
      return null;
    }
    return localKvmSerializable.getProperty(0);
  }
  
  public boolean isAddAdornments()
  {
    return this.addAdornments;
  }
  
  public void parseBody(XmlPullParser paramXmlPullParser)
    throws IOException, XmlPullParserException
  {
    this.bodyIn = null;
    paramXmlPullParser.nextTag();
    Object localObject1;
    if ((paramXmlPullParser.getEventType() == 2) && (paramXmlPullParser.getNamespace().equals(this.env)) && (paramXmlPullParser.getName().equals("Fault")))
    {
      localObject1 = new SoapFault();
      ((SoapFault)localObject1).parse(paramXmlPullParser);
      this.bodyIn = localObject1;
    }
    for (;;)
    {
      return;
      while (paramXmlPullParser.getEventType() == 2)
      {
        localObject1 = paramXmlPullParser.getAttributeValue(this.enc, "root");
        Object localObject2 = read(paramXmlPullParser, null, -1, paramXmlPullParser.getNamespace(), paramXmlPullParser.getName(), PropertyInfo.OBJECT_TYPE);
        if (("1".equals(localObject1)) || (this.bodyIn == null)) {
          this.bodyIn = localObject2;
        }
        paramXmlPullParser.nextTag();
      }
    }
  }
  
  public Object read(XmlPullParser paramXmlPullParser, Object paramObject, int paramInt, String paramString1, String paramString2, PropertyInfo paramPropertyInfo)
    throws IOException, XmlPullParserException
  {
    String str2 = paramXmlPullParser.getName();
    String str1 = paramXmlPullParser.getAttributeValue(null, "href");
    if (str1 != null)
    {
      if (paramObject == null) {
        throw new RuntimeException("href at root level?!?");
      }
      paramPropertyInfo = str1.substring(1);
      paramString2 = this.idMap.get(paramPropertyInfo);
      if (paramString2 != null)
      {
        paramString1 = paramString2;
        if (!(paramString2 instanceof FwdRef)) {}
      }
      else
      {
        paramString1 = new FwdRef();
        paramString1.next = ((FwdRef)paramString2);
        paramString1.obj = paramObject;
        paramString1.index = paramInt;
        this.idMap.put(paramPropertyInfo, paramString1);
        paramString1 = null;
      }
      paramXmlPullParser.nextTag();
      paramXmlPullParser.require(3, null, str2);
      paramObject = paramString1;
    }
    String str3;
    do
    {
      paramXmlPullParser.require(3, null, str2);
      return paramObject;
      str1 = paramXmlPullParser.getAttributeValue(this.xsi, "nil");
      str3 = paramXmlPullParser.getAttributeValue(null, "id");
      paramObject = str1;
      if (str1 == null) {
        paramObject = paramXmlPullParser.getAttributeValue(this.xsi, "null");
      }
      if ((paramObject == null) || (!SoapEnvelope.stringToBoolean((String)paramObject))) {
        break;
      }
      paramString1 = null;
      paramXmlPullParser.nextTag();
      paramXmlPullParser.require(3, null, str2);
      paramObject = paramString1;
    } while (str3 == null);
    paramObject = this.idMap.get(str3);
    if ((paramObject instanceof FwdRef))
    {
      paramObject = (FwdRef)paramObject;
      do
      {
        if (!(((FwdRef)paramObject).obj instanceof KvmSerializable)) {
          break;
        }
        ((KvmSerializable)((FwdRef)paramObject).obj).setProperty(((FwdRef)paramObject).index, paramString1);
        paramString2 = ((FwdRef)paramObject).next;
        paramObject = paramString2;
      } while (paramString2 != null);
    }
    label366:
    while (paramObject == null) {
      for (;;)
      {
        this.idMap.put(str3, paramString1);
        paramObject = paramString1;
        break;
        String str4 = paramXmlPullParser.getAttributeValue(this.xsi, "type");
        if (str4 != null)
        {
          paramInt = str4.indexOf(':');
          str1 = str4.substring(paramInt + 1);
          if (paramInt == -1)
          {
            paramObject = "";
            paramObject = paramXmlPullParser.getNamespace((String)paramObject);
          }
        }
        for (;;)
        {
          if (str4 == null) {
            this.implicitTypes = true;
          }
          paramString2 = readInstance(paramXmlPullParser, (String)paramObject, str1, paramPropertyInfo);
          paramString1 = paramString2;
          if (paramString2 != null) {
            break;
          }
          paramString1 = readUnknown(paramXmlPullParser, (String)paramObject, str1);
          break;
          paramObject = str4.substring(0, paramInt);
          break label366;
          paramObject = paramString1;
          str1 = paramString2;
          if (paramString2 == null)
          {
            paramObject = paramString1;
            str1 = paramString2;
            if (paramString1 == null) {
              if (paramXmlPullParser.getAttributeValue(this.enc, "arrayType") != null)
              {
                paramObject = this.enc;
                str1 = "Array";
              }
              else
              {
                paramString1 = getInfo(paramPropertyInfo.type, null);
                paramObject = (String)paramString1[0];
                str1 = (String)paramString1[1];
              }
            }
          }
        }
        ((Vector)((FwdRef)paramObject).obj).setElementAt(paramString1, ((FwdRef)paramObject).index);
      }
    }
    throw new RuntimeException("double ID");
  }
  
  public Object readInstance(XmlPullParser paramXmlPullParser, String paramString1, String paramString2, PropertyInfo paramPropertyInfo)
    throws IOException, XmlPullParserException
  {
    Object localObject = this.qNameToClass.get(new SoapPrimitive(paramString1, paramString2, null));
    if (localObject == null) {
      return null;
    }
    if ((localObject instanceof Marshal)) {
      return ((Marshal)localObject).readInstance(paramXmlPullParser, paramString1, paramString2, paramPropertyInfo);
    }
    if ((localObject instanceof SoapObject)) {
      paramString1 = ((SoapObject)localObject).newInstance();
    }
    while ((paramString1 instanceof SoapObject))
    {
      readSerializable(paramXmlPullParser, (SoapObject)paramString1);
      return paramString1;
      if (localObject == SoapObject.class) {
        paramString1 = new SoapObject(paramString1, paramString2);
      } else {
        try
        {
          paramString1 = ((Class)localObject).newInstance();
        }
        catch (Exception paramXmlPullParser)
        {
          throw new RuntimeException(paramXmlPullParser.toString());
        }
      }
    }
    if ((paramString1 instanceof KvmSerializable))
    {
      readSerializable(paramXmlPullParser, (KvmSerializable)paramString1);
      return paramString1;
    }
    if ((paramString1 instanceof Vector))
    {
      readVector(paramXmlPullParser, (Vector)paramString1, paramPropertyInfo.elementType);
      return paramString1;
    }
    throw new RuntimeException("no deserializer for " + paramString1.getClass());
  }
  
  protected void readSerializable(XmlPullParser paramXmlPullParser, KvmSerializable paramKvmSerializable)
    throws IOException, XmlPullParserException
  {
    int i = -1;
    int n = paramKvmSerializable.getPropertyCount();
    PropertyInfo localPropertyInfo = new PropertyInfo();
    while (paramXmlPullParser.nextTag() != 3)
    {
      String str = paramXmlPullParser.getName();
      int m = n;
      int k = i;
      int j = m;
      if (this.implicitTypes)
      {
        if (!(paramKvmSerializable instanceof SoapObject))
        {
          j = m;
          k = i;
        }
      }
      else
      {
        do
        {
          do
          {
            m = j - 1;
            if (j == 0) {
              throw new RuntimeException("Unknown Property: " + str);
            }
            j = k + 1;
            i = j;
            if (j >= n) {
              i = 0;
            }
            paramKvmSerializable.getPropertyInfo(i, this.properties, localPropertyInfo);
            if (((localPropertyInfo.namespace == null) && (str.equals(localPropertyInfo.name))) || ((localPropertyInfo.name == null) && (i == 0))) {
              break;
            }
            k = i;
            j = m;
          } while (!str.equals(localPropertyInfo.name));
          k = i;
          j = m;
        } while (!paramXmlPullParser.getNamespace().equals(localPropertyInfo.namespace));
        paramKvmSerializable.setProperty(i, read(paramXmlPullParser, paramKvmSerializable, i, null, null, localPropertyInfo));
        continue;
      }
      ((SoapObject)paramKvmSerializable).addProperty(paramXmlPullParser.getName(), read(paramXmlPullParser, paramKvmSerializable, paramKvmSerializable.getPropertyCount(), ((SoapObject)paramKvmSerializable).getNamespace(), str, PropertyInfo.OBJECT_TYPE));
    }
    paramXmlPullParser.require(3, null, null);
  }
  
  protected void readSerializable(XmlPullParser paramXmlPullParser, SoapObject paramSoapObject)
    throws IOException, XmlPullParserException
  {
    int i = 0;
    while (i < paramXmlPullParser.getAttributeCount())
    {
      paramSoapObject.addAttribute(paramXmlPullParser.getAttributeName(i), paramXmlPullParser.getAttributeValue(i));
      i += 1;
    }
    readSerializable(paramXmlPullParser, paramSoapObject);
  }
  
  protected Object readUnknown(XmlPullParser paramXmlPullParser, String paramString1, String paramString2)
    throws IOException, XmlPullParserException
  {
    String str1 = paramXmlPullParser.getName();
    String str2 = paramXmlPullParser.getNamespace();
    paramXmlPullParser.next();
    Object localObject1 = null;
    Object localObject3 = null;
    Object localObject2;
    if (paramXmlPullParser.getEventType() == 4)
    {
      localObject2 = paramXmlPullParser.getText();
      localObject1 = new SoapPrimitive(paramString1, paramString2, (String)localObject2);
      paramXmlPullParser.next();
    }
    while (paramXmlPullParser.getEventType() == 2) {
      if ((localObject2 != null) && (((String)localObject2).trim().length() != 0))
      {
        throw new RuntimeException("Malformed input: Mixed content");
        localObject2 = localObject3;
        if (paramXmlPullParser.getEventType() == 3)
        {
          localObject1 = new SoapObject(paramString1, paramString2);
          localObject2 = localObject3;
        }
      }
      else
      {
        localObject1 = new SoapObject(paramString1, paramString2);
        while (paramXmlPullParser.getEventType() != 3)
        {
          ((SoapObject)localObject1).addProperty(paramXmlPullParser.getName(), read(paramXmlPullParser, localObject1, ((SoapObject)localObject1).getPropertyCount(), null, null, PropertyInfo.OBJECT_TYPE));
          paramXmlPullParser.nextTag();
        }
      }
    }
    paramXmlPullParser.require(3, str2, str1);
    return localObject1;
  }
  
  protected void readVector(XmlPullParser paramXmlPullParser, Vector paramVector, PropertyInfo paramPropertyInfo)
    throws IOException, XmlPullParserException
  {
    Object localObject1 = null;
    Object localObject2 = null;
    int i = paramVector.size();
    int k = 1;
    String str2 = paramXmlPullParser.getAttributeValue(this.enc, "arrayType");
    int j = k;
    Object localObject3;
    if (str2 != null)
    {
      i = str2.indexOf(':');
      j = str2.indexOf("[", i);
      localObject3 = str2.substring(i + 1, j);
      if (i != -1) {
        break label291;
      }
    }
    label291:
    for (localObject1 = "";; localObject1 = str2.substring(0, i))
    {
      String str1 = paramXmlPullParser.getNamespace((String)localObject1);
      int m = getIndex(str2, j, -1);
      localObject1 = str1;
      localObject2 = localObject3;
      j = k;
      i = m;
      if (m != -1)
      {
        paramVector.setSize(m);
        j = 0;
        i = m;
        localObject2 = localObject3;
        localObject1 = str1;
      }
      localObject3 = paramPropertyInfo;
      if (paramPropertyInfo == null) {
        localObject3 = PropertyInfo.OBJECT_TYPE;
      }
      paramXmlPullParser.nextTag();
      m = getIndex(paramXmlPullParser.getAttributeValue(this.enc, "offset"), 0, 0);
      while (paramXmlPullParser.getEventType() != 3)
      {
        m = getIndex(paramXmlPullParser.getAttributeValue(this.enc, "position"), 0, m);
        k = i;
        if (j != 0)
        {
          k = i;
          if (m >= i)
          {
            k = m + 1;
            paramVector.setSize(k);
          }
        }
        paramVector.setElementAt(read(paramXmlPullParser, paramVector, m, (String)localObject1, (String)localObject2, (PropertyInfo)localObject3), m);
        m += 1;
        paramXmlPullParser.nextTag();
        i = k;
      }
    }
    paramXmlPullParser.require(3, null, null);
  }
  
  public void setAddAdornments(boolean paramBoolean)
  {
    this.addAdornments = paramBoolean;
  }
  
  public void writeBody(XmlSerializer paramXmlSerializer)
    throws IOException
  {
    this.multiRef = new Vector();
    this.multiRef.addElement(this.bodyOut);
    Object[] arrayOfObject = getInfo(null, this.bodyOut);
    if (this.dotNet)
    {
      str = "";
      paramXmlSerializer.startTag(str, (String)arrayOfObject[1]);
      if (this.dotNet) {
        paramXmlSerializer.attribute(null, "xmlns", (String)arrayOfObject[0]);
      }
      if (this.addAdornments)
      {
        if (arrayOfObject[2] != null) {
          break label174;
        }
        str = "o0";
        label98:
        paramXmlSerializer.attribute(null, "id", str);
        paramXmlSerializer.attribute(this.enc, "root", "1");
      }
      writeElement(paramXmlSerializer, this.bodyOut, null, arrayOfObject[3]);
      if (!this.dotNet) {
        break label184;
      }
    }
    label174:
    label184:
    for (String str = "";; str = (String)arrayOfObject[0])
    {
      paramXmlSerializer.endTag(str, (String)arrayOfObject[1]);
      return;
      str = (String)arrayOfObject[0];
      break;
      str = (String)arrayOfObject[2];
      break label98;
    }
  }
  
  public void writeObjectBody(XmlSerializer paramXmlSerializer, KvmSerializable paramKvmSerializable)
    throws IOException
  {
    PropertyInfo localPropertyInfo = new PropertyInfo();
    int j = paramKvmSerializable.getPropertyCount();
    int i = 0;
    while (i < j)
    {
      paramKvmSerializable.getPropertyInfo(i, this.properties, localPropertyInfo);
      if ((localPropertyInfo.flags & 0x1) == 0)
      {
        paramXmlSerializer.startTag(localPropertyInfo.namespace, localPropertyInfo.name);
        writeProperty(paramXmlSerializer, paramKvmSerializable.getProperty(i), localPropertyInfo);
        paramXmlSerializer.endTag(localPropertyInfo.namespace, localPropertyInfo.name);
      }
      i += 1;
    }
  }
  
  public void writeObjectBody(XmlSerializer paramXmlSerializer, SoapObject paramSoapObject)
    throws IOException
  {
    int i = 0;
    while (i < paramSoapObject.getAttributeCount())
    {
      AttributeInfo localAttributeInfo = new AttributeInfo();
      paramSoapObject.getAttributeInfo(i, localAttributeInfo);
      paramXmlSerializer.attribute(localAttributeInfo.getNamespace(), localAttributeInfo.getName(), localAttributeInfo.getValue().toString());
      i += 1;
    }
    writeObjectBody(paramXmlSerializer, paramSoapObject);
  }
  
  protected void writeProperty(XmlSerializer paramXmlSerializer, Object paramObject, PropertyInfo paramPropertyInfo)
    throws IOException
  {
    if (paramObject == null)
    {
      paramPropertyInfo = this.xsi;
      if (this.version >= 120) {}
      for (paramObject = "nil";; paramObject = "null")
      {
        paramXmlSerializer.attribute(paramPropertyInfo, (String)paramObject, "true");
        return;
      }
    }
    Object[] arrayOfObject = getInfo(null, paramObject);
    if ((paramPropertyInfo.multiRef) || (arrayOfObject[2] != null))
    {
      int j = this.multiRef.indexOf(paramObject);
      int i = j;
      if (j == -1)
      {
        i = this.multiRef.size();
        this.multiRef.addElement(paramObject);
      }
      if (arrayOfObject[2] == null) {}
      for (paramObject = "#o" + i;; paramObject = "#" + arrayOfObject[2])
      {
        paramXmlSerializer.attribute(null, "href", (String)paramObject);
        return;
      }
    }
    if ((!this.implicitTypes) || (paramObject.getClass() != paramPropertyInfo.type))
    {
      String str = paramXmlSerializer.getPrefix((String)arrayOfObject[0], true);
      paramXmlSerializer.attribute(this.xsi, "type", str + ":" + arrayOfObject[1]);
    }
    writeElement(paramXmlSerializer, paramObject, paramPropertyInfo, arrayOfObject[3]);
  }
  
  protected void writeVectorBody(XmlSerializer paramXmlSerializer, Vector paramVector, PropertyInfo paramPropertyInfo)
    throws IOException
  {
    PropertyInfo localPropertyInfo = paramPropertyInfo;
    if (paramPropertyInfo == null) {
      localPropertyInfo = PropertyInfo.OBJECT_TYPE;
    }
    int m = paramVector.size();
    paramPropertyInfo = getInfo(localPropertyInfo.type, null);
    paramXmlSerializer.attribute(this.enc, "arrayType", paramXmlSerializer.getPrefix((String)paramPropertyInfo[0], false) + ":" + paramPropertyInfo[1] + "[" + m + "]");
    int i = 0;
    int k = 0;
    if (k < m)
    {
      if (paramVector.elementAt(k) == null) {}
      int j;
      for (i = 1;; i = j)
      {
        k += 1;
        break;
        paramXmlSerializer.startTag(null, "item");
        j = i;
        if (i != 0)
        {
          paramXmlSerializer.attribute(this.enc, "position", "[" + k + "]");
          j = 0;
        }
        writeProperty(paramXmlSerializer, paramVector.elementAt(k), localPropertyInfo);
        paramXmlSerializer.endTag(null, "item");
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\ksoap2\serialization\SoapSerializationEnvelope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */