package org.apache.james.mime4j.codec;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class QuotedPrintableInputStream
  extends InputStream
{
  private static Log log = LogFactory.getLog(QuotedPrintableInputStream.class);
  ByteQueue byteq = new ByteQueue();
  private boolean closed = false;
  ByteQueue pushbackq = new ByteQueue();
  private byte state = 0;
  private InputStream stream;
  
  public QuotedPrintableInputStream(InputStream paramInputStream)
  {
    this.stream = paramInputStream;
  }
  
  private byte asciiCharToNumericValue(byte paramByte)
  {
    if ((paramByte >= 48) && (paramByte <= 57)) {
      return (byte)(paramByte - 48);
    }
    if ((paramByte >= 65) && (paramByte <= 90)) {
      return (byte)(paramByte - 65 + 10);
    }
    if ((paramByte >= 97) && (paramByte <= 122)) {
      return (byte)(paramByte - 97 + 10);
    }
    throw new IllegalArgumentException((char)paramByte + " is not a hexadecimal digit");
  }
  
  private void fillBuffer()
    throws IOException
  {
    byte b1 = 0;
    for (;;)
    {
      if (this.byteq.count() == 0)
      {
        if (this.pushbackq.count() == 0)
        {
          populatePushbackQueue();
          if (this.pushbackq.count() != 0) {}
        }
      }
      else {
        return;
      }
      byte b2 = this.pushbackq.dequeue();
      switch (this.state)
      {
      default: 
        log.error("Illegal state: " + this.state);
        this.state = 0;
        this.byteq.enqueue(b2);
        break;
      case 0: 
        if (b2 != 61) {
          this.byteq.enqueue(b2);
        } else {
          this.state = 1;
        }
        break;
      case 1: 
        if (b2 == 13)
        {
          this.state = 2;
        }
        else if (((b2 >= 48) && (b2 <= 57)) || ((b2 >= 65) && (b2 <= 70)) || ((b2 >= 97) && (b2 <= 102)))
        {
          this.state = 3;
          b1 = b2;
        }
        else if (b2 == 61)
        {
          if (log.isWarnEnabled()) {
            log.warn("Malformed MIME; got ==");
          }
          this.byteq.enqueue((byte)61);
        }
        else
        {
          if (log.isWarnEnabled()) {
            log.warn("Malformed MIME; expected \\r or [0-9A-Z], got " + b2);
          }
          this.state = 0;
          this.byteq.enqueue((byte)61);
          this.byteq.enqueue(b2);
        }
        break;
      case 2: 
        if (b2 == 10)
        {
          this.state = 0;
        }
        else
        {
          if (log.isWarnEnabled()) {
            log.warn("Malformed MIME; expected 10, got " + b2);
          }
          this.state = 0;
          this.byteq.enqueue((byte)61);
          this.byteq.enqueue((byte)13);
          this.byteq.enqueue(b2);
        }
        break;
      case 3: 
        if (((b2 >= 48) && (b2 <= 57)) || ((b2 >= 65) && (b2 <= 70)) || ((b2 >= 97) && (b2 <= 102)))
        {
          int i = asciiCharToNumericValue(b1);
          int j = asciiCharToNumericValue(b2);
          this.state = 0;
          this.byteq.enqueue((byte)(i << 4 | j));
        }
        else
        {
          if (log.isWarnEnabled()) {
            log.warn("Malformed MIME; expected [0-9A-Z], got " + b2);
          }
          this.state = 0;
          this.byteq.enqueue((byte)61);
          this.byteq.enqueue(b1);
          this.byteq.enqueue(b2);
        }
        break;
      }
    }
  }
  
  private void populatePushbackQueue()
    throws IOException
  {
    if (this.pushbackq.count() != 0)
    {
      return;
      this.pushbackq.enqueue((byte)i);
    }
    int i = this.stream.read();
    switch (i)
    {
    case 9: 
    case 32: 
    default: 
      this.pushbackq.enqueue((byte)i);
      return;
    case -1: 
      this.pushbackq.clear();
      return;
    }
    this.pushbackq.clear();
    this.pushbackq.enqueue((byte)i);
  }
  
  public void close()
    throws IOException
  {
    this.closed = true;
  }
  
  public int read()
    throws IOException
  {
    if (this.closed) {
      throw new IOException("QuotedPrintableInputStream has been closed");
    }
    fillBuffer();
    int i;
    if (this.byteq.count() == 0) {
      i = -1;
    }
    int j;
    do
    {
      return i;
      j = this.byteq.dequeue();
      i = j;
    } while (j >= 0);
    return j & 0xFF;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\codec\QuotedPrintableInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */