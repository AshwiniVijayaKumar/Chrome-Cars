package org.apache.james.mime4j.message;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import org.apache.james.mime4j.codec.CodecUtil;
import org.apache.james.mime4j.field.ContentTypeField;
import org.apache.james.mime4j.parser.Field;
import org.apache.james.mime4j.util.ByteArrayBuffer;
import org.apache.james.mime4j.util.ByteSequence;
import org.apache.james.mime4j.util.ContentUtil;
import org.apache.james.mime4j.util.MimeUtil;

public class MessageWriter
{
  private static final byte[] CRLF = { 13, 10 };
  private static final byte[] DASHES = { 45, 45 };
  public static final MessageWriter DEFAULT = new MessageWriter();
  
  private ByteSequence getBoundary(ContentTypeField paramContentTypeField)
  {
    paramContentTypeField = paramContentTypeField.getBoundary();
    if (paramContentTypeField == null) {
      throw new IllegalArgumentException("Multipart boundary not specified");
    }
    return ContentUtil.encode(paramContentTypeField);
  }
  
  private ContentTypeField getContentType(Multipart paramMultipart)
  {
    paramMultipart = paramMultipart.getParent();
    if (paramMultipart == null) {
      throw new IllegalArgumentException("Missing parent entity in multipart");
    }
    paramMultipart = paramMultipart.getHeader();
    if (paramMultipart == null) {
      throw new IllegalArgumentException("Missing header in parent entity");
    }
    paramMultipart = (ContentTypeField)paramMultipart.getField("Content-Type");
    if (paramMultipart == null) {
      throw new IllegalArgumentException("Content-Type field not specified");
    }
    return paramMultipart;
  }
  
  private void writeBytes(ByteSequence paramByteSequence, OutputStream paramOutputStream)
    throws IOException
  {
    if ((paramByteSequence instanceof ByteArrayBuffer))
    {
      paramByteSequence = (ByteArrayBuffer)paramByteSequence;
      paramOutputStream.write(paramByteSequence.buffer(), 0, paramByteSequence.length());
      return;
    }
    paramOutputStream.write(paramByteSequence.toByteArray());
  }
  
  protected OutputStream encodeStream(OutputStream paramOutputStream, String paramString, boolean paramBoolean)
    throws IOException
  {
    OutputStream localOutputStream;
    if (MimeUtil.isBase64Encoding(paramString)) {
      localOutputStream = CodecUtil.wrapBase64(paramOutputStream);
    }
    do
    {
      return localOutputStream;
      localOutputStream = paramOutputStream;
    } while (!MimeUtil.isQuotedPrintableEncoded(paramString));
    return CodecUtil.wrapQuotedPrintable(paramOutputStream, paramBoolean);
  }
  
  public void writeBody(Body paramBody, OutputStream paramOutputStream)
    throws IOException
  {
    if ((paramBody instanceof Message))
    {
      writeEntity((Message)paramBody, paramOutputStream);
      return;
    }
    if ((paramBody instanceof Multipart))
    {
      writeMultipart((Multipart)paramBody, paramOutputStream);
      return;
    }
    if ((paramBody instanceof SingleBody))
    {
      ((SingleBody)paramBody).writeTo(paramOutputStream);
      return;
    }
    throw new IllegalArgumentException("Unsupported body class");
  }
  
  public void writeEntity(Entity paramEntity, OutputStream paramOutputStream)
    throws IOException
  {
    Object localObject = paramEntity.getHeader();
    if (localObject == null) {
      throw new IllegalArgumentException("Missing header");
    }
    writeHeader((Header)localObject, paramOutputStream);
    localObject = paramEntity.getBody();
    if (localObject == null) {
      throw new IllegalArgumentException("Missing body");
    }
    boolean bool = localObject instanceof BinaryBody;
    paramEntity = encodeStream(paramOutputStream, paramEntity.getContentTransferEncoding(), bool);
    writeBody((Body)localObject, paramEntity);
    if (paramEntity != paramOutputStream) {
      paramEntity.close();
    }
  }
  
  public void writeHeader(Header paramHeader, OutputStream paramOutputStream)
    throws IOException
  {
    paramHeader = paramHeader.iterator();
    while (paramHeader.hasNext())
    {
      writeBytes(((Field)paramHeader.next()).getRaw(), paramOutputStream);
      paramOutputStream.write(CRLF);
    }
    paramOutputStream.write(CRLF);
  }
  
  public void writeMultipart(Multipart paramMultipart, OutputStream paramOutputStream)
    throws IOException
  {
    ByteSequence localByteSequence = getBoundary(getContentType(paramMultipart));
    writeBytes(paramMultipart.getPreambleRaw(), paramOutputStream);
    paramOutputStream.write(CRLF);
    Iterator localIterator = paramMultipart.getBodyParts().iterator();
    while (localIterator.hasNext())
    {
      BodyPart localBodyPart = (BodyPart)localIterator.next();
      paramOutputStream.write(DASHES);
      writeBytes(localByteSequence, paramOutputStream);
      paramOutputStream.write(CRLF);
      writeEntity(localBodyPart, paramOutputStream);
      paramOutputStream.write(CRLF);
    }
    paramOutputStream.write(DASHES);
    writeBytes(localByteSequence, paramOutputStream);
    paramOutputStream.write(DASHES);
    paramOutputStream.write(CRLF);
    writeBytes(paramMultipart.getEpilogueRaw(), paramOutputStream);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\message\MessageWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */