package org.apache.james.mime4j.field;

public class DefaultFieldParser
  extends DelegatingFieldParser
{
  public DefaultFieldParser()
  {
    setFieldParser("Content-Transfer-Encoding", ContentTransferEncodingField.PARSER);
    setFieldParser("Content-Type", ContentTypeField.PARSER);
    setFieldParser("Content-Disposition", ContentDispositionField.PARSER);
    FieldParser localFieldParser = DateTimeField.PARSER;
    setFieldParser("Date", localFieldParser);
    setFieldParser("Resent-Date", localFieldParser);
    localFieldParser = MailboxListField.PARSER;
    setFieldParser("From", localFieldParser);
    setFieldParser("Resent-From", localFieldParser);
    localFieldParser = MailboxField.PARSER;
    setFieldParser("Sender", localFieldParser);
    setFieldParser("Resent-Sender", localFieldParser);
    localFieldParser = AddressListField.PARSER;
    setFieldParser("To", localFieldParser);
    setFieldParser("Resent-To", localFieldParser);
    setFieldParser("Cc", localFieldParser);
    setFieldParser("Resent-Cc", localFieldParser);
    setFieldParser("Bcc", localFieldParser);
    setFieldParser("Resent-Bcc", localFieldParser);
    setFieldParser("Reply-To", localFieldParser);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\DefaultFieldParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */