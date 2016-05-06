package org.apache.james.mime4j.field;

import org.apache.james.mime4j.util.ByteSequence;

public class ContentTransferEncodingField
  extends AbstractField
{
  static final FieldParser PARSER = new FieldParser()
  {
    public ParsedField parse(String paramAnonymousString1, String paramAnonymousString2, ByteSequence paramAnonymousByteSequence)
    {
      return new ContentTransferEncodingField(paramAnonymousString1, paramAnonymousString2, paramAnonymousByteSequence);
    }
  };
  private String encoding;
  
  ContentTransferEncodingField(String paramString1, String paramString2, ByteSequence paramByteSequence)
  {
    super(paramString1, paramString2, paramByteSequence);
    this.encoding = paramString2.trim().toLowerCase();
  }
  
  public static String getEncoding(ContentTransferEncodingField paramContentTransferEncodingField)
  {
    if ((paramContentTransferEncodingField != null) && (paramContentTransferEncodingField.getEncoding().length() != 0)) {
      return paramContentTransferEncodingField.getEncoding();
    }
    return "7bit";
  }
  
  public String getEncoding()
  {
    return this.encoding;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\ContentTransferEncodingField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */