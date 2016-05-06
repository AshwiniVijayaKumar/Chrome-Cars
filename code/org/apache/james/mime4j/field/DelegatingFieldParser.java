package org.apache.james.mime4j.field;

import java.util.HashMap;
import java.util.Map;
import org.apache.james.mime4j.util.ByteSequence;

public class DelegatingFieldParser
  implements FieldParser
{
  private FieldParser defaultParser = UnstructuredField.PARSER;
  private Map<String, FieldParser> parsers = new HashMap();
  
  public FieldParser getParser(String paramString)
  {
    FieldParser localFieldParser = (FieldParser)this.parsers.get(paramString.toLowerCase());
    paramString = localFieldParser;
    if (localFieldParser == null) {
      paramString = this.defaultParser;
    }
    return paramString;
  }
  
  public ParsedField parse(String paramString1, String paramString2, ByteSequence paramByteSequence)
  {
    return getParser(paramString1).parse(paramString1, paramString2, paramByteSequence);
  }
  
  public void setFieldParser(String paramString, FieldParser paramFieldParser)
  {
    this.parsers.put(paramString.toLowerCase(), paramFieldParser);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\DelegatingFieldParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */