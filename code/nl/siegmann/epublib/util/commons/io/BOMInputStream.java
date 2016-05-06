package nl.siegmann.epublib.util.commons.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BOMInputStream
  extends ProxyInputStream
{
  private final List<ByteOrderMark> boms;
  private ByteOrderMark byteOrderMark;
  private int fbIndex;
  private int fbLength;
  private int[] firstBytes;
  private final boolean include;
  private int markFbIndex;
  private boolean markedAtStart;
  
  public BOMInputStream(InputStream paramInputStream)
  {
    this(paramInputStream, false, new ByteOrderMark[] { ByteOrderMark.UTF_8 });
  }
  
  public BOMInputStream(InputStream paramInputStream, boolean paramBoolean)
  {
    this(paramInputStream, paramBoolean, new ByteOrderMark[] { ByteOrderMark.UTF_8 });
  }
  
  public BOMInputStream(InputStream paramInputStream, boolean paramBoolean, ByteOrderMark... paramVarArgs)
  {
    super(paramInputStream);
    if ((paramVarArgs == null) || (paramVarArgs.length == 0)) {
      throw new IllegalArgumentException("No BOMs specified");
    }
    this.include = paramBoolean;
    this.boms = Arrays.asList(paramVarArgs);
  }
  
  public BOMInputStream(InputStream paramInputStream, ByteOrderMark... paramVarArgs)
  {
    this(paramInputStream, false, paramVarArgs);
  }
  
  private ByteOrderMark find()
  {
    Iterator localIterator = this.boms.iterator();
    while (localIterator.hasNext())
    {
      ByteOrderMark localByteOrderMark = (ByteOrderMark)localIterator.next();
      if (matches(localByteOrderMark)) {
        return localByteOrderMark;
      }
    }
    return null;
  }
  
  private boolean matches(ByteOrderMark paramByteOrderMark)
  {
    if (paramByteOrderMark.length() != this.fbLength) {
      return false;
    }
    int i = 0;
    for (;;)
    {
      if (i >= paramByteOrderMark.length()) {
        break label44;
      }
      if (paramByteOrderMark.get(i) != this.firstBytes[i]) {
        break;
      }
      i += 1;
    }
    label44:
    return true;
  }
  
  private int readFirstBytes()
    throws IOException
  {
    getBOM();
    if (this.fbIndex < this.fbLength)
    {
      int[] arrayOfInt = this.firstBytes;
      int i = this.fbIndex;
      this.fbIndex = (i + 1);
      return arrayOfInt[i];
    }
    return -1;
  }
  
  public ByteOrderMark getBOM()
    throws IOException
  {
    int i;
    if (this.firstBytes == null)
    {
      i = 0;
      Iterator localIterator = this.boms.iterator();
      while (localIterator.hasNext()) {
        i = Math.max(i, ((ByteOrderMark)localIterator.next()).length());
      }
      this.firstBytes = new int[i];
      i = 0;
    }
    for (;;)
    {
      if (i < this.firstBytes.length)
      {
        this.firstBytes[i] = this.in.read();
        this.fbLength += 1;
        if (this.firstBytes[i] >= 0) {
          break label103;
        }
      }
      for (;;)
      {
        return this.byteOrderMark;
        label103:
        this.byteOrderMark = find();
        if (this.byteOrderMark == null) {
          break;
        }
        if (!this.include) {
          this.fbLength = 0;
        }
      }
      i += 1;
    }
  }
  
  public String getBOMCharsetName()
    throws IOException
  {
    getBOM();
    if (this.byteOrderMark == null) {
      return null;
    }
    return this.byteOrderMark.getCharsetName();
  }
  
  public boolean hasBOM()
    throws IOException
  {
    return getBOM() != null;
  }
  
  public boolean hasBOM(ByteOrderMark paramByteOrderMark)
    throws IOException
  {
    if (!this.boms.contains(paramByteOrderMark)) {
      throw new IllegalArgumentException("Stream not configure to detect " + paramByteOrderMark);
    }
    return (this.byteOrderMark != null) && (getBOM().equals(paramByteOrderMark));
  }
  
  /* Error */
  public void mark(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_0
    //   4: getfield 92	nl/siegmann/epublib/util/commons/io/BOMInputStream:fbIndex	I
    //   7: putfield 146	nl/siegmann/epublib/util/commons/io/BOMInputStream:markFbIndex	I
    //   10: aload_0
    //   11: getfield 84	nl/siegmann/epublib/util/commons/io/BOMInputStream:firstBytes	[I
    //   14: ifnonnull +21 -> 35
    //   17: iconst_1
    //   18: istore_2
    //   19: aload_0
    //   20: iload_2
    //   21: putfield 148	nl/siegmann/epublib/util/commons/io/BOMInputStream:markedAtStart	Z
    //   24: aload_0
    //   25: getfield 103	nl/siegmann/epublib/util/commons/io/BOMInputStream:in	Ljava/io/InputStream;
    //   28: iload_1
    //   29: invokevirtual 150	java/io/InputStream:mark	(I)V
    //   32: aload_0
    //   33: monitorexit
    //   34: return
    //   35: iconst_0
    //   36: istore_2
    //   37: goto -18 -> 19
    //   40: astore_3
    //   41: aload_0
    //   42: monitorexit
    //   43: aload_3
    //   44: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	45	0	this	BOMInputStream
    //   0	45	1	paramInt	int
    //   18	19	2	bool	boolean
    //   40	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	17	40	finally
    //   19	32	40	finally
  }
  
  public int read()
    throws IOException
  {
    int i = readFirstBytes();
    if (i >= 0) {
      return i;
    }
    return this.in.read();
  }
  
  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    int k = 0;
    int i = 0;
    int j = paramInt2;
    paramInt2 = paramInt1;
    paramInt1 = k;
    while ((j > 0) && (i >= 0))
    {
      k = readFirstBytes();
      i = k;
      if (k >= 0)
      {
        paramArrayOfByte[paramInt2] = ((byte)(k & 0xFF));
        j -= 1;
        paramInt1 += 1;
        paramInt2 += 1;
        i = k;
      }
    }
    paramInt2 = this.in.read(paramArrayOfByte, paramInt2, j);
    if (paramInt2 < 0)
    {
      if (paramInt1 > 0) {
        return paramInt1;
      }
      return -1;
    }
    return paramInt1 + paramInt2;
  }
  
  public void reset()
    throws IOException
  {
    try
    {
      this.fbIndex = this.markFbIndex;
      if (this.markedAtStart) {
        this.firstBytes = null;
      }
      this.in.reset();
      return;
    }
    finally {}
  }
  
  public long skip(long paramLong)
    throws IOException
  {
    while ((paramLong > 0L) && (readFirstBytes() >= 0)) {
      paramLong -= 1L;
    }
    return this.in.skip(paramLong);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\util\commons\io\BOMInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */