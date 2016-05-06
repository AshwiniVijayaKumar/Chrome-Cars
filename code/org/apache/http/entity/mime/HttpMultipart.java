package org.apache.http.entity.mime;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.james.mime4j.field.ContentTypeField;
import org.apache.james.mime4j.message.Body;
import org.apache.james.mime4j.message.BodyPart;
import org.apache.james.mime4j.message.Entity;
import org.apache.james.mime4j.message.Header;
import org.apache.james.mime4j.message.MessageWriter;
import org.apache.james.mime4j.message.Multipart;
import org.apache.james.mime4j.parser.Field;
import org.apache.james.mime4j.util.ByteArrayBuffer;
import org.apache.james.mime4j.util.ByteSequence;
import org.apache.james.mime4j.util.CharsetUtil;

@NotThreadSafe
public class HttpMultipart
  extends Multipart
{
  private static final ByteArrayBuffer CR_LF = encode(MIME.DEFAULT_CHARSET, "\r\n");
  private static final ByteArrayBuffer TWO_DASHES = encode(MIME.DEFAULT_CHARSET, "--");
  private HttpMultipartMode mode = HttpMultipartMode.STRICT;
  
  public HttpMultipart(String paramString)
  {
    super(paramString);
  }
  
  private void doWriteTo(HttpMultipartMode paramHttpMultipartMode, OutputStream paramOutputStream, boolean paramBoolean)
    throws IOException
  {
    List localList = getBodyParts();
    Charset localCharset = getCharset();
    ByteArrayBuffer localByteArrayBuffer = encode(localCharset, getBoundary());
    Object localObject;
    switch (paramHttpMultipartMode)
    {
    default: 
    case ???: 
      do
      {
        return;
        paramHttpMultipartMode = getPreamble();
        if ((paramHttpMultipartMode != null) && (paramHttpMultipartMode.length() != 0))
        {
          writeBytes(encode(localCharset, paramHttpMultipartMode), paramOutputStream);
          writeBytes(CR_LF, paramOutputStream);
        }
        i = 0;
        while (i < localList.size())
        {
          writeBytes(TWO_DASHES, paramOutputStream);
          writeBytes(localByteArrayBuffer, paramOutputStream);
          writeBytes(CR_LF, paramOutputStream);
          paramHttpMultipartMode = (BodyPart)localList.get(i);
          localObject = paramHttpMultipartMode.getHeader().getFields().iterator();
          while (((Iterator)localObject).hasNext())
          {
            writeBytes(((Field)((Iterator)localObject).next()).getRaw(), paramOutputStream);
            writeBytes(CR_LF, paramOutputStream);
          }
          writeBytes(CR_LF, paramOutputStream);
          if (paramBoolean) {
            MessageWriter.DEFAULT.writeBody(paramHttpMultipartMode.getBody(), paramOutputStream);
          }
          writeBytes(CR_LF, paramOutputStream);
          i += 1;
        }
        writeBytes(TWO_DASHES, paramOutputStream);
        writeBytes(localByteArrayBuffer, paramOutputStream);
        writeBytes(TWO_DASHES, paramOutputStream);
        writeBytes(CR_LF, paramOutputStream);
        paramHttpMultipartMode = getEpilogue();
      } while ((paramHttpMultipartMode == null) || (paramHttpMultipartMode.length() == 0));
      writeBytes(encode(localCharset, paramHttpMultipartMode), paramOutputStream);
      writeBytes(CR_LF, paramOutputStream);
      return;
    }
    int i = 0;
    while (i < localList.size())
    {
      writeBytes(TWO_DASHES, paramOutputStream);
      writeBytes(localByteArrayBuffer, paramOutputStream);
      writeBytes(CR_LF, paramOutputStream);
      paramHttpMultipartMode = (BodyPart)localList.get(i);
      localObject = paramHttpMultipartMode.getHeader().getField("Content-Disposition");
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(((Field)localObject).getName());
      localStringBuilder.append(": ");
      localStringBuilder.append(((Field)localObject).getBody());
      writeBytes(encode(localCharset, localStringBuilder.toString()), paramOutputStream);
      writeBytes(CR_LF, paramOutputStream);
      writeBytes(CR_LF, paramOutputStream);
      if (paramBoolean) {
        MessageWriter.DEFAULT.writeBody(paramHttpMultipartMode.getBody(), paramOutputStream);
      }
      writeBytes(CR_LF, paramOutputStream);
      i += 1;
    }
    writeBytes(TWO_DASHES, paramOutputStream);
    writeBytes(localByteArrayBuffer, paramOutputStream);
    writeBytes(TWO_DASHES, paramOutputStream);
    writeBytes(CR_LF, paramOutputStream);
  }
  
  private static ByteArrayBuffer encode(Charset paramCharset, String paramString)
  {
    paramCharset = paramCharset.encode(CharBuffer.wrap(paramString));
    paramString = new ByteArrayBuffer(paramCharset.remaining());
    paramString.append(paramCharset.array(), paramCharset.position(), paramCharset.remaining());
    return paramString;
  }
  
  private static void writeBytes(ByteArrayBuffer paramByteArrayBuffer, OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream.write(paramByteArrayBuffer.buffer(), 0, paramByteArrayBuffer.length());
  }
  
  private static void writeBytes(ByteSequence paramByteSequence, OutputStream paramOutputStream)
    throws IOException
  {
    if ((paramByteSequence instanceof ByteArrayBuffer))
    {
      writeBytes((ByteArrayBuffer)paramByteSequence, paramOutputStream);
      return;
    }
    paramOutputStream.write(paramByteSequence.toByteArray());
  }
  
  protected String getBoundary()
  {
    return ((ContentTypeField)getParent().getHeader().getField("Content-Type")).getBoundary();
  }
  
  protected Charset getCharset()
  {
    ContentTypeField localContentTypeField = (ContentTypeField)getParent().getHeader().getField("Content-Type");
    switch (this.mode)
    {
    default: 
      return null;
    case ???: 
      return MIME.DEFAULT_CHARSET;
    }
    if (localContentTypeField.getCharset() != null) {
      return CharsetUtil.getCharset(localContentTypeField.getCharset());
    }
    return CharsetUtil.getCharset("ISO-8859-1");
  }
  
  public HttpMultipartMode getMode()
  {
    return this.mode;
  }
  
  public long getTotalLength()
  {
    Object localObject = getBodyParts();
    long l1 = 0L;
    int i = 0;
    while (i < ((List)localObject).size())
    {
      Body localBody = ((BodyPart)((List)localObject).get(i)).getBody();
      if ((localBody instanceof ContentBody))
      {
        long l2 = ((ContentBody)localBody).getContentLength();
        if (l2 >= 0L)
        {
          l1 += l2;
          i += 1;
        }
        else
        {
          return -1L;
        }
      }
      else
      {
        return -1L;
      }
    }
    localObject = new ByteArrayOutputStream();
    try
    {
      doWriteTo(this.mode, (OutputStream)localObject, false);
      i = ((ByteArrayOutputStream)localObject).toByteArray().length;
      return i + l1;
    }
    catch (IOException localIOException) {}
    return -1L;
  }
  
  public void setMode(HttpMultipartMode paramHttpMultipartMode)
  {
    this.mode = paramHttpMultipartMode;
  }
  
  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    doWriteTo(this.mode, paramOutputStream, true);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\http\entity\mime\HttpMultipart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */