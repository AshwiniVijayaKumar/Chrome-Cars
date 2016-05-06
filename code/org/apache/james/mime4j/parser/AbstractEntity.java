package org.apache.james.mime4j.parser;

import java.io.IOException;
import java.util.BitSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.descriptor.BodyDescriptor;
import org.apache.james.mime4j.descriptor.DefaultBodyDescriptor;
import org.apache.james.mime4j.descriptor.MaximalBodyDescriptor;
import org.apache.james.mime4j.descriptor.MutableBodyDescriptor;
import org.apache.james.mime4j.io.LineReaderInputStream;
import org.apache.james.mime4j.io.MaxHeaderLimitException;
import org.apache.james.mime4j.io.MaxLineLimitException;
import org.apache.james.mime4j.util.ByteArrayBuffer;

public abstract class AbstractEntity
  implements EntityStateMachine
{
  private static final int T_IN_BODYPART = -2;
  private static final int T_IN_MESSAGE = -3;
  private static final BitSet fieldChars = new BitSet();
  protected final MutableBodyDescriptor body;
  protected final MimeEntityConfig config;
  private boolean endOfHeader;
  protected final int endState;
  private Field field;
  private int headerCount;
  private int lineCount;
  private final ByteArrayBuffer linebuf;
  protected final Log log = LogFactory.getLog(getClass());
  protected final BodyDescriptor parent;
  protected final int startState;
  protected int state;
  
  static
  {
    int i = 33;
    while (i <= 57)
    {
      fieldChars.set(i);
      i += 1;
    }
    i = 59;
    while (i <= 126)
    {
      fieldChars.set(i);
      i += 1;
    }
  }
  
  AbstractEntity(BodyDescriptor paramBodyDescriptor, int paramInt1, int paramInt2, MimeEntityConfig paramMimeEntityConfig)
  {
    this.parent = paramBodyDescriptor;
    this.state = paramInt1;
    this.startState = paramInt1;
    this.endState = paramInt2;
    this.config = paramMimeEntityConfig;
    this.body = newBodyDescriptor(paramBodyDescriptor);
    this.linebuf = new ByteArrayBuffer(64);
    this.lineCount = 0;
    this.endOfHeader = false;
    this.headerCount = 0;
  }
  
  private ByteArrayBuffer fillFieldBuffer()
    throws IOException, MimeException
  {
    if (this.endOfHeader) {
      throw new IllegalStateException();
    }
    int k = this.config.getMaxLineLen();
    LineReaderInputStream localLineReaderInputStream = getDataStream();
    ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(64);
    int i;
    do
    {
      do
      {
        i = this.linebuf.length();
        if ((k > 0) && (localByteArrayBuffer.length() + i >= k)) {
          throw new MaxLineLimitException("Maximum line length limit exceeded");
        }
        if (i > 0) {
          localByteArrayBuffer.append(this.linebuf.buffer(), 0, i);
        }
        this.linebuf.clear();
        if (localLineReaderInputStream.readLine(this.linebuf) == -1)
        {
          monitor(Event.HEADERS_PREMATURE_END);
          this.endOfHeader = true;
          return localByteArrayBuffer;
        }
        int j = this.linebuf.length();
        i = j;
        if (j > 0)
        {
          i = j;
          if (this.linebuf.byteAt(j - 1) == 10) {
            i = j - 1;
          }
        }
        j = i;
        if (i > 0)
        {
          j = i;
          if (this.linebuf.byteAt(i - 1) == 13) {
            j = i - 1;
          }
        }
        if (j == 0)
        {
          this.endOfHeader = true;
          return localByteArrayBuffer;
        }
        this.lineCount += 1;
      } while (this.lineCount <= 1);
      i = this.linebuf.byteAt(0);
    } while ((i == 32) || (i == 9));
    return localByteArrayBuffer;
  }
  
  public static final String stateToString(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "Unknown";
    case -1: 
      return "End of stream";
    case 0: 
      return "Start message";
    case 1: 
      return "End message";
    case 2: 
      return "Raw entity";
    case 3: 
      return "Start header";
    case 4: 
      return "Field";
    case 5: 
      return "End header";
    case 6: 
      return "Start multipart";
    case 7: 
      return "End multipart";
    case 8: 
      return "Preamble";
    case 9: 
      return "Epilogue";
    case 10: 
      return "Start bodypart";
    case 11: 
      return "End bodypart";
    case 12: 
      return "Body";
    case -2: 
      return "Bodypart";
    }
    return "In message";
  }
  
  protected void debug(Event paramEvent)
  {
    if (this.log.isDebugEnabled()) {
      this.log.debug(message(paramEvent));
    }
  }
  
  public BodyDescriptor getBodyDescriptor()
  {
    switch (getState())
    {
    default: 
      throw new IllegalStateException("Invalid state :" + stateToString(this.state));
    }
    return this.body;
  }
  
  protected abstract LineReaderInputStream getDataStream();
  
  public Field getField()
  {
    switch (getState())
    {
    default: 
      throw new IllegalStateException("Invalid state :" + stateToString(this.state));
    }
    return this.field;
  }
  
  protected abstract int getLineNumber();
  
  public int getState()
  {
    return this.state;
  }
  
  protected String message(Event paramEvent)
  {
    if (paramEvent == null) {}
    int i;
    for (paramEvent = "Event is unexpectedly null.";; paramEvent = paramEvent.toString())
    {
      i = getLineNumber();
      if (i > 0) {
        break;
      }
      return paramEvent;
    }
    return "Line " + i + ": " + paramEvent;
  }
  
  protected void monitor(Event paramEvent)
    throws MimeException, IOException
  {
    if (this.config.isStrictParsing()) {
      throw new MimeParseEventException(paramEvent);
    }
    warn(paramEvent);
  }
  
  protected MutableBodyDescriptor newBodyDescriptor(BodyDescriptor paramBodyDescriptor)
  {
    if (this.config.isMaximalBodyDescriptor()) {
      return new MaximalBodyDescriptor(paramBodyDescriptor);
    }
    return new DefaultBodyDescriptor(paramBodyDescriptor);
  }
  
  protected boolean parseField()
    throws MimeException, IOException
  {
    int m = this.config.getMaxHeaderCount();
    if (this.endOfHeader) {
      return false;
    }
    if (this.headerCount >= m) {
      throw new MaxHeaderLimitException("Maximum header limit exceeded");
    }
    ByteArrayBuffer localByteArrayBuffer = fillFieldBuffer();
    this.headerCount += 1;
    int j = localByteArrayBuffer.length();
    int i = j;
    if (j > 0)
    {
      i = j;
      if (localByteArrayBuffer.byteAt(j - 1) == 10) {
        i = j - 1;
      }
    }
    j = i;
    if (i > 0)
    {
      j = i;
      if (localByteArrayBuffer.byteAt(i - 1) == 13) {
        j = i - 1;
      }
    }
    localByteArrayBuffer.setLength(j);
    int k = 1;
    int n = localByteArrayBuffer.indexOf((byte)58);
    if (n <= 0)
    {
      monitor(Event.INALID_HEADER);
      i = 0;
      label141:
      if (i != 0)
      {
        this.field = new RawField(localByteArrayBuffer, n);
        this.body.addField(this.field);
        return true;
      }
    }
    else
    {
      j = 0;
    }
    for (;;)
    {
      i = k;
      if (j >= n) {
        break label141;
      }
      if (!fieldChars.get(localByteArrayBuffer.byteAt(j) & 0xFF))
      {
        monitor(Event.INALID_HEADER);
        i = 0;
        break label141;
        break;
      }
      j += 1;
    }
  }
  
  public String toString()
  {
    return getClass().getName() + " [" + stateToString(this.state) + "][" + this.body.getMimeType() + "][" + this.body.getBoundary() + "]";
  }
  
  protected void warn(Event paramEvent)
  {
    if (this.log.isWarnEnabled()) {
      this.log.warn(message(paramEvent));
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\parser\AbstractEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */