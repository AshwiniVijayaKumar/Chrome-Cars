package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecoderUtil;
import org.apache.james.mime4j.util.ByteSequence;

public class UnstructuredField
  extends AbstractField
{
  static final FieldParser PARSER = new FieldParser()
  {
    public ParsedField parse(String paramAnonymousString1, String paramAnonymousString2, ByteSequence paramAnonymousByteSequence)
    {
      return new UnstructuredField(paramAnonymousString1, paramAnonymousString2, paramAnonymousByteSequence);
    }
  };
  private boolean parsed = false;
  private String value;
  
  UnstructuredField(String paramString1, String paramString2, ByteSequence paramByteSequence)
  {
    super(paramString1, paramString2, paramByteSequence);
  }
  
  private void parse()
  {
    this.value = DecoderUtil.decodeEncodedWords(getBody());
    this.parsed = true;
  }
  
  public String getValue()
  {
    if (!this.parsed) {
      parse();
    }
    return this.value;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\UnstructuredField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */