package org.apache.james.mime4j.parser;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.logging.Log;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.codec.Base64InputStream;
import org.apache.james.mime4j.codec.QuotedPrintableInputStream;
import org.apache.james.mime4j.descriptor.BodyDescriptor;
import org.apache.james.mime4j.descriptor.MutableBodyDescriptor;
import org.apache.james.mime4j.io.BufferedLineReaderInputStream;
import org.apache.james.mime4j.io.LimitedInputStream;
import org.apache.james.mime4j.io.LineNumberSource;
import org.apache.james.mime4j.io.LineReaderInputStream;
import org.apache.james.mime4j.io.LineReaderInputStreamAdaptor;
import org.apache.james.mime4j.io.MimeBoundaryInputStream;
import org.apache.james.mime4j.util.ContentUtil;
import org.apache.james.mime4j.util.MimeUtil;

public class MimeEntity
  extends AbstractEntity
{
  private static final int T_IN_BODYPART = -2;
  private static final int T_IN_MESSAGE = -3;
  private LineReaderInputStreamAdaptor dataStream;
  private final BufferedLineReaderInputStream inbuffer;
  private final LineNumberSource lineSource;
  private MimeBoundaryInputStream mimeStream;
  private int recursionMode;
  private boolean skipHeader;
  private byte[] tmpbuf;
  
  public MimeEntity(LineNumberSource paramLineNumberSource, BufferedLineReaderInputStream paramBufferedLineReaderInputStream, BodyDescriptor paramBodyDescriptor, int paramInt1, int paramInt2)
  {
    this(paramLineNumberSource, paramBufferedLineReaderInputStream, paramBodyDescriptor, paramInt1, paramInt2, new MimeEntityConfig());
  }
  
  public MimeEntity(LineNumberSource paramLineNumberSource, BufferedLineReaderInputStream paramBufferedLineReaderInputStream, BodyDescriptor paramBodyDescriptor, int paramInt1, int paramInt2, MimeEntityConfig paramMimeEntityConfig)
  {
    super(paramBodyDescriptor, paramInt1, paramInt2, paramMimeEntityConfig);
    this.lineSource = paramLineNumberSource;
    this.inbuffer = paramBufferedLineReaderInputStream;
    this.dataStream = new LineReaderInputStreamAdaptor(paramBufferedLineReaderInputStream, paramMimeEntityConfig.getMaxLineLen());
    this.skipHeader = false;
  }
  
  private void advanceToBoundary()
    throws IOException
  {
    if (!this.dataStream.eof())
    {
      if (this.tmpbuf == null) {
        this.tmpbuf = new byte['ࠀ'];
      }
      InputStream localInputStream = getLimitedContentStream();
      while (localInputStream.read(this.tmpbuf) != -1) {}
    }
  }
  
  private void clearMimeStream()
  {
    this.mimeStream = null;
    this.dataStream = new LineReaderInputStreamAdaptor(this.inbuffer, this.config.getMaxLineLen());
  }
  
  /* Error */
  private void createMimeStream()
    throws MimeException, IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 89	org/apache/james/mime4j/parser/MimeEntity:body	Lorg/apache/james/mime4j/descriptor/MutableBodyDescriptor;
    //   4: invokeinterface 95 1 0
    //   9: astore_3
    //   10: aload_3
    //   11: invokevirtual 100	java/lang/String:length	()I
    //   14: iconst_2
    //   15: imul
    //   16: istore_2
    //   17: iload_2
    //   18: istore_1
    //   19: iload_2
    //   20: sipush 4096
    //   23: if_icmpge +7 -> 30
    //   26: sipush 4096
    //   29: istore_1
    //   30: aload_0
    //   31: getfield 76	org/apache/james/mime4j/parser/MimeEntity:mimeStream	Lorg/apache/james/mime4j/io/MimeBoundaryInputStream;
    //   34: ifnull +57 -> 91
    //   37: aload_0
    //   38: new 102	org/apache/james/mime4j/io/MimeBoundaryInputStream
    //   41: dup
    //   42: new 104	org/apache/james/mime4j/io/BufferedLineReaderInputStream
    //   45: dup
    //   46: aload_0
    //   47: getfield 76	org/apache/james/mime4j/parser/MimeEntity:mimeStream	Lorg/apache/james/mime4j/io/MimeBoundaryInputStream;
    //   50: iload_1
    //   51: aload_0
    //   52: getfield 80	org/apache/james/mime4j/parser/MimeEntity:config	Lorg/apache/james/mime4j/parser/MimeEntityConfig;
    //   55: invokevirtual 46	org/apache/james/mime4j/parser/MimeEntityConfig:getMaxLineLen	()I
    //   58: invokespecial 107	org/apache/james/mime4j/io/BufferedLineReaderInputStream:<init>	(Ljava/io/InputStream;II)V
    //   61: aload_3
    //   62: invokespecial 110	org/apache/james/mime4j/io/MimeBoundaryInputStream:<init>	(Lorg/apache/james/mime4j/io/BufferedLineReaderInputStream;Ljava/lang/String;)V
    //   65: putfield 76	org/apache/james/mime4j/parser/MimeEntity:mimeStream	Lorg/apache/james/mime4j/io/MimeBoundaryInputStream;
    //   68: aload_0
    //   69: new 42	org/apache/james/mime4j/io/LineReaderInputStreamAdaptor
    //   72: dup
    //   73: aload_0
    //   74: getfield 76	org/apache/james/mime4j/parser/MimeEntity:mimeStream	Lorg/apache/james/mime4j/io/MimeBoundaryInputStream;
    //   77: aload_0
    //   78: getfield 80	org/apache/james/mime4j/parser/MimeEntity:config	Lorg/apache/james/mime4j/parser/MimeEntityConfig;
    //   81: invokevirtual 46	org/apache/james/mime4j/parser/MimeEntityConfig:getMaxLineLen	()I
    //   84: invokespecial 49	org/apache/james/mime4j/io/LineReaderInputStreamAdaptor:<init>	(Ljava/io/InputStream;I)V
    //   87: putfield 51	org/apache/james/mime4j/parser/MimeEntity:dataStream	Lorg/apache/james/mime4j/io/LineReaderInputStreamAdaptor;
    //   90: return
    //   91: aload_0
    //   92: getfield 40	org/apache/james/mime4j/parser/MimeEntity:inbuffer	Lorg/apache/james/mime4j/io/BufferedLineReaderInputStream;
    //   95: iload_1
    //   96: invokevirtual 114	org/apache/james/mime4j/io/BufferedLineReaderInputStream:ensureCapacity	(I)V
    //   99: aload_0
    //   100: new 102	org/apache/james/mime4j/io/MimeBoundaryInputStream
    //   103: dup
    //   104: aload_0
    //   105: getfield 40	org/apache/james/mime4j/parser/MimeEntity:inbuffer	Lorg/apache/james/mime4j/io/BufferedLineReaderInputStream;
    //   108: aload_3
    //   109: invokespecial 110	org/apache/james/mime4j/io/MimeBoundaryInputStream:<init>	(Lorg/apache/james/mime4j/io/BufferedLineReaderInputStream;Ljava/lang/String;)V
    //   112: putfield 76	org/apache/james/mime4j/parser/MimeEntity:mimeStream	Lorg/apache/james/mime4j/io/MimeBoundaryInputStream;
    //   115: goto -47 -> 68
    //   118: astore_3
    //   119: new 83	org/apache/james/mime4j/MimeException
    //   122: dup
    //   123: aload_3
    //   124: invokevirtual 117	java/lang/IllegalArgumentException:getMessage	()Ljava/lang/String;
    //   127: aload_3
    //   128: invokespecial 120	org/apache/james/mime4j/MimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   131: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	132	0	this	MimeEntity
    //   18	78	1	i	int
    //   16	8	2	j	int
    //   9	100	3	str	String
    //   118	10	3	localIllegalArgumentException	IllegalArgumentException
    // Exception table:
    //   from	to	target	type
    //   30	68	118	java/lang/IllegalArgumentException
    //   91	115	118	java/lang/IllegalArgumentException
  }
  
  private InputStream getLimitedContentStream()
  {
    long l = this.config.getMaxContentLen();
    if (l >= 0L) {
      return new LimitedInputStream(this.dataStream, l);
    }
    return this.dataStream;
  }
  
  private EntityStateMachine nextMessage()
  {
    Object localObject = this.body.getTransferEncoding();
    if (MimeUtil.isBase64Encoding((String)localObject))
    {
      this.log.debug("base64 encoded message/rfc822 detected");
      localObject = new Base64InputStream(this.dataStream);
    }
    while (this.recursionMode == 2)
    {
      return new RawEntity((InputStream)localObject);
      if (MimeUtil.isQuotedPrintableEncoded((String)localObject))
      {
        this.log.debug("quoted-printable encoded message/rfc822 detected");
        localObject = new QuotedPrintableInputStream(this.dataStream);
      }
      else
      {
        localObject = this.dataStream;
      }
    }
    localObject = new MimeEntity(this.lineSource, new BufferedLineReaderInputStream((InputStream)localObject, 4096, this.config.getMaxLineLen()), this.body, 0, 1, this.config);
    ((MimeEntity)localObject).setRecursionMode(this.recursionMode);
    return (EntityStateMachine)localObject;
  }
  
  private EntityStateMachine nextMimeEntity()
  {
    if (this.recursionMode == 2) {
      return new RawEntity(this.mimeStream);
    }
    Object localObject = new BufferedLineReaderInputStream(this.mimeStream, 4096, this.config.getMaxLineLen());
    localObject = new MimeEntity(this.lineSource, (BufferedLineReaderInputStream)localObject, this.body, 10, 11, this.config);
    ((MimeEntity)localObject).setRecursionMode(this.recursionMode);
    return (EntityStateMachine)localObject;
  }
  
  public EntityStateMachine advance()
    throws IOException, MimeException
  {
    int i = 5;
    switch (this.state)
    {
    case -1: 
    case 1: 
    case 2: 
    case 11: 
    default: 
      if (this.state == this.endState) {
        this.state = -1;
      }
      break;
    case 0: 
    case 10: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 8: 
    case -2: 
    case 9: 
    case -3: 
    case 7: 
    case 12: 
      for (;;)
      {
        return null;
        if (this.skipHeader)
        {
          this.state = 5;
        }
        else
        {
          this.state = 3;
          continue;
          this.state = 3;
          continue;
          if (parseField()) {
            i = 4;
          }
          this.state = i;
          continue;
          String str = this.body.getMimeType();
          if (this.recursionMode == 3)
          {
            this.state = 12;
          }
          else if (MimeUtil.isMultipart(str))
          {
            this.state = 6;
            clearMimeStream();
          }
          else
          {
            if ((this.recursionMode != 1) && (MimeUtil.isMessage(str)))
            {
              this.state = -3;
              return nextMessage();
            }
            this.state = 12;
            continue;
            if (this.dataStream.isUsed())
            {
              advanceToBoundary();
              this.state = 7;
            }
            else
            {
              createMimeStream();
              this.state = 8;
              continue;
              advanceToBoundary();
              if (this.mimeStream.isLastPart())
              {
                clearMimeStream();
                this.state = 7;
              }
              else
              {
                clearMimeStream();
                createMimeStream();
                this.state = -2;
                return nextMimeEntity();
                advanceToBoundary();
                if ((this.mimeStream.eof()) && (!this.mimeStream.isLastPart())) {
                  monitor(Event.MIME_BODY_PREMATURE_END);
                }
                while (this.mimeStream.isLastPart())
                {
                  clearMimeStream();
                  this.state = 9;
                  break;
                }
                clearMimeStream();
                createMimeStream();
                this.state = -2;
                return nextMimeEntity();
                this.state = 7;
                continue;
                this.state = this.endState;
              }
            }
          }
        }
      }
    }
    throw new IllegalStateException("Invalid state: " + stateToString(this.state));
  }
  
  public InputStream getContentStream()
  {
    switch (this.state)
    {
    case 7: 
    case 10: 
    case 11: 
    default: 
      throw new IllegalStateException("Invalid state: " + stateToString(this.state));
    }
    return getLimitedContentStream();
  }
  
  protected LineReaderInputStream getDataStream()
  {
    return this.dataStream;
  }
  
  protected int getLineNumber()
  {
    if (this.lineSource == null) {
      return -1;
    }
    return this.lineSource.getLineNumber();
  }
  
  public int getRecursionMode()
  {
    return this.recursionMode;
  }
  
  public void setRecursionMode(int paramInt)
  {
    this.recursionMode = paramInt;
  }
  
  public void skipHeader(String paramString)
  {
    if (this.state != 0) {
      throw new IllegalStateException("Invalid state: " + stateToString(this.state));
    }
    this.skipHeader = true;
    paramString = ContentUtil.encode("Content-Type: " + paramString);
    this.body.addField(new RawField(paramString, 12));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\parser\MimeEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */