package org.apache.james.mime4j.field.mimeversion.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public class SimpleCharStream
{
  public static final boolean staticFlag = false;
  int available;
  protected int[] bufcolumn;
  protected char[] buffer;
  protected int[] bufline;
  public int bufpos = -1;
  int bufsize;
  protected int column = 0;
  protected int inBuf = 0;
  protected Reader inputStream;
  protected int line = 1;
  protected int maxNextCharInd = 0;
  protected boolean prevCharIsCR = false;
  protected boolean prevCharIsLF = false;
  protected int tabSize = 8;
  int tokenBegin;
  
  public SimpleCharStream(InputStream paramInputStream)
  {
    this(paramInputStream, 1, 1, 4096);
  }
  
  public SimpleCharStream(InputStream paramInputStream, int paramInt1, int paramInt2)
  {
    this(paramInputStream, paramInt1, paramInt2, 4096);
  }
  
  public SimpleCharStream(InputStream paramInputStream, int paramInt1, int paramInt2, int paramInt3)
  {
    this(new InputStreamReader(paramInputStream), paramInt1, paramInt2, paramInt3);
  }
  
  public SimpleCharStream(InputStream paramInputStream, String paramString)
    throws UnsupportedEncodingException
  {
    this(paramInputStream, paramString, 1, 1, 4096);
  }
  
  public SimpleCharStream(InputStream paramInputStream, String paramString, int paramInt1, int paramInt2)
    throws UnsupportedEncodingException
  {
    this(paramInputStream, paramString, paramInt1, paramInt2, 4096);
  }
  
  public SimpleCharStream(InputStream paramInputStream, String paramString, int paramInt1, int paramInt2, int paramInt3)
    throws UnsupportedEncodingException
  {}
  
  public SimpleCharStream(Reader paramReader)
  {
    this(paramReader, 1, 1, 4096);
  }
  
  public SimpleCharStream(Reader paramReader, int paramInt1, int paramInt2)
  {
    this(paramReader, paramInt1, paramInt2, 4096);
  }
  
  public SimpleCharStream(Reader paramReader, int paramInt1, int paramInt2, int paramInt3)
  {
    this.inputStream = paramReader;
    this.line = paramInt1;
    this.column = (paramInt2 - 1);
    this.bufsize = paramInt3;
    this.available = paramInt3;
    this.buffer = new char[paramInt3];
    this.bufline = new int[paramInt3];
    this.bufcolumn = new int[paramInt3];
  }
  
  public char BeginToken()
    throws IOException
  {
    this.tokenBegin = -1;
    char c = readChar();
    this.tokenBegin = this.bufpos;
    return c;
  }
  
  public void Done()
  {
    this.buffer = null;
    this.bufline = null;
    this.bufcolumn = null;
  }
  
  protected void ExpandBuff(boolean paramBoolean)
  {
    char[] arrayOfChar = new char[this.bufsize + 2048];
    int[] arrayOfInt1 = new int[this.bufsize + 2048];
    int[] arrayOfInt2 = new int[this.bufsize + 2048];
    if (paramBoolean) {}
    for (;;)
    {
      try
      {
        System.arraycopy(this.buffer, this.tokenBegin, arrayOfChar, 0, this.bufsize - this.tokenBegin);
        System.arraycopy(this.buffer, 0, arrayOfChar, this.bufsize - this.tokenBegin, this.bufpos);
        this.buffer = arrayOfChar;
        System.arraycopy(this.bufline, this.tokenBegin, arrayOfInt1, 0, this.bufsize - this.tokenBegin);
        System.arraycopy(this.bufline, 0, arrayOfInt1, this.bufsize - this.tokenBegin, this.bufpos);
        this.bufline = arrayOfInt1;
        System.arraycopy(this.bufcolumn, this.tokenBegin, arrayOfInt2, 0, this.bufsize - this.tokenBegin);
        System.arraycopy(this.bufcolumn, 0, arrayOfInt2, this.bufsize - this.tokenBegin, this.bufpos);
        this.bufcolumn = arrayOfInt2;
        i = this.bufpos + (this.bufsize - this.tokenBegin);
        this.bufpos = i;
        this.maxNextCharInd = i;
        this.bufsize += 2048;
        this.available = this.bufsize;
        this.tokenBegin = 0;
        return;
      }
      catch (Throwable localThrowable)
      {
        int i;
        throw new Error(localThrowable.getMessage());
      }
      System.arraycopy(this.buffer, this.tokenBegin, arrayOfChar, 0, this.bufsize - this.tokenBegin);
      this.buffer = arrayOfChar;
      System.arraycopy(this.bufline, this.tokenBegin, arrayOfInt1, 0, this.bufsize - this.tokenBegin);
      this.bufline = arrayOfInt1;
      System.arraycopy(this.bufcolumn, this.tokenBegin, arrayOfInt2, 0, this.bufsize - this.tokenBegin);
      this.bufcolumn = arrayOfInt2;
      i = this.bufpos - this.tokenBegin;
      this.bufpos = i;
      this.maxNextCharInd = i;
    }
  }
  
  protected void FillBuff()
    throws IOException
  {
    if (this.maxNextCharInd == this.available)
    {
      if (this.available != this.bufsize) {
        break label157;
      }
      if (this.tokenBegin <= 2048) {
        break label129;
      }
      this.maxNextCharInd = 0;
      this.bufpos = 0;
      this.available = this.tokenBegin;
    }
    int i;
    for (;;)
    {
      try
      {
        i = this.inputStream.read(this.buffer, this.maxNextCharInd, this.available - this.maxNextCharInd);
        if (i != -1) {
          break;
        }
        this.inputStream.close();
        throw new IOException();
      }
      catch (IOException localIOException)
      {
        this.bufpos -= 1;
        backup(0);
        if (this.tokenBegin == -1) {
          this.tokenBegin = this.bufpos;
        }
        throw localIOException;
      }
      label129:
      if (this.tokenBegin < 0)
      {
        this.maxNextCharInd = 0;
        this.bufpos = 0;
      }
      else
      {
        ExpandBuff(false);
        continue;
        label157:
        if (this.available > this.tokenBegin) {
          this.available = this.bufsize;
        } else if (this.tokenBegin - this.available < 2048) {
          ExpandBuff(true);
        } else {
          this.available = this.tokenBegin;
        }
      }
    }
    this.maxNextCharInd += i;
  }
  
  public String GetImage()
  {
    if (this.bufpos >= this.tokenBegin) {
      return new String(this.buffer, this.tokenBegin, this.bufpos - this.tokenBegin + 1);
    }
    return new String(this.buffer, this.tokenBegin, this.bufsize - this.tokenBegin) + new String(this.buffer, 0, this.bufpos + 1);
  }
  
  public char[] GetSuffix(int paramInt)
  {
    char[] arrayOfChar = new char[paramInt];
    if (this.bufpos + 1 >= paramInt)
    {
      System.arraycopy(this.buffer, this.bufpos - paramInt + 1, arrayOfChar, 0, paramInt);
      return arrayOfChar;
    }
    System.arraycopy(this.buffer, this.bufsize - (paramInt - this.bufpos - 1), arrayOfChar, 0, paramInt - this.bufpos - 1);
    System.arraycopy(this.buffer, 0, arrayOfChar, paramInt - this.bufpos - 1, this.bufpos + 1);
    return arrayOfChar;
  }
  
  public void ReInit(InputStream paramInputStream)
  {
    ReInit(paramInputStream, 1, 1, 4096);
  }
  
  public void ReInit(InputStream paramInputStream, int paramInt1, int paramInt2)
  {
    ReInit(paramInputStream, paramInt1, paramInt2, 4096);
  }
  
  public void ReInit(InputStream paramInputStream, int paramInt1, int paramInt2, int paramInt3)
  {
    ReInit(new InputStreamReader(paramInputStream), paramInt1, paramInt2, paramInt3);
  }
  
  public void ReInit(InputStream paramInputStream, String paramString)
    throws UnsupportedEncodingException
  {
    ReInit(paramInputStream, paramString, 1, 1, 4096);
  }
  
  public void ReInit(InputStream paramInputStream, String paramString, int paramInt1, int paramInt2)
    throws UnsupportedEncodingException
  {
    ReInit(paramInputStream, paramString, paramInt1, paramInt2, 4096);
  }
  
  public void ReInit(InputStream paramInputStream, String paramString, int paramInt1, int paramInt2, int paramInt3)
    throws UnsupportedEncodingException
  {
    if (paramString == null) {}
    for (paramInputStream = new InputStreamReader(paramInputStream);; paramInputStream = new InputStreamReader(paramInputStream, paramString))
    {
      ReInit(paramInputStream, paramInt1, paramInt2, paramInt3);
      return;
    }
  }
  
  public void ReInit(Reader paramReader)
  {
    ReInit(paramReader, 1, 1, 4096);
  }
  
  public void ReInit(Reader paramReader, int paramInt1, int paramInt2)
  {
    ReInit(paramReader, paramInt1, paramInt2, 4096);
  }
  
  public void ReInit(Reader paramReader, int paramInt1, int paramInt2, int paramInt3)
  {
    this.inputStream = paramReader;
    this.line = paramInt1;
    this.column = (paramInt2 - 1);
    if ((this.buffer == null) || (paramInt3 != this.buffer.length))
    {
      this.bufsize = paramInt3;
      this.available = paramInt3;
      this.buffer = new char[paramInt3];
      this.bufline = new int[paramInt3];
      this.bufcolumn = new int[paramInt3];
    }
    this.prevCharIsCR = false;
    this.prevCharIsLF = false;
    this.maxNextCharInd = 0;
    this.inBuf = 0;
    this.tokenBegin = 0;
    this.bufpos = -1;
  }
  
  protected void UpdateLineColumn(char paramChar)
  {
    this.column += 1;
    int i;
    if (this.prevCharIsLF)
    {
      this.prevCharIsLF = false;
      i = this.line;
      this.column = 1;
      this.line = (i + 1);
      switch (paramChar)
      {
      }
    }
    for (;;)
    {
      this.bufline[this.bufpos] = this.line;
      this.bufcolumn[this.bufpos] = this.column;
      return;
      if (!this.prevCharIsCR) {
        break;
      }
      this.prevCharIsCR = false;
      if (paramChar == '\n')
      {
        this.prevCharIsLF = true;
        break;
      }
      i = this.line;
      this.column = 1;
      this.line = (i + 1);
      break;
      this.prevCharIsCR = true;
      continue;
      this.prevCharIsLF = true;
      continue;
      this.column -= 1;
      this.column += this.tabSize - this.column % this.tabSize;
    }
  }
  
  public void adjustBeginLineColumn(int paramInt1, int paramInt2)
  {
    int m = this.tokenBegin;
    if (this.bufpos >= this.tokenBegin) {}
    int j;
    int i;
    int i1;
    int k;
    int[] arrayOfInt;
    for (int n = this.bufpos - this.tokenBegin + this.inBuf + 1;; n = this.bufsize - this.tokenBegin + this.bufpos + 1 + this.inBuf)
    {
      j = 0;
      i = 0;
      i1 = 0;
      for (;;)
      {
        k = m;
        if (j >= n) {
          break;
        }
        arrayOfInt = this.bufline;
        int i2 = m % this.bufsize;
        int i3 = arrayOfInt[i2];
        arrayOfInt = this.bufline;
        m += 1;
        int i4 = m % this.bufsize;
        i = i2;
        k = m;
        if (i3 != arrayOfInt[i4]) {
          break;
        }
        this.bufline[i2] = paramInt1;
        i = this.bufcolumn[i4];
        k = this.bufcolumn[i2];
        this.bufcolumn[i2] = (paramInt2 + i1);
        i1 = i + i1 - k;
        j += 1;
        i = i2;
      }
    }
    m = i;
    if (j < n)
    {
      arrayOfInt = this.bufline;
      m = paramInt1 + 1;
      arrayOfInt[i] = paramInt1;
      this.bufcolumn[i] = (paramInt2 + i1);
      paramInt1 = j;
      paramInt2 = m;
      for (;;)
      {
        j = paramInt1 + 1;
        if (paramInt1 >= n) {
          break;
        }
        arrayOfInt = this.bufline;
        i = k % this.bufsize;
        paramInt1 = arrayOfInt[i];
        arrayOfInt = this.bufline;
        k += 1;
        if (paramInt1 != arrayOfInt[(k % this.bufsize)])
        {
          this.bufline[i] = paramInt2;
          paramInt1 = j;
          paramInt2 += 1;
        }
        else
        {
          this.bufline[i] = paramInt2;
          paramInt1 = j;
        }
      }
      m = i;
    }
    this.line = this.bufline[m];
    this.column = this.bufcolumn[m];
  }
  
  public void backup(int paramInt)
  {
    this.inBuf += paramInt;
    paramInt = this.bufpos - paramInt;
    this.bufpos = paramInt;
    if (paramInt < 0) {
      this.bufpos += this.bufsize;
    }
  }
  
  public int getBeginColumn()
  {
    return this.bufcolumn[this.tokenBegin];
  }
  
  public int getBeginLine()
  {
    return this.bufline[this.tokenBegin];
  }
  
  public int getColumn()
  {
    return this.bufcolumn[this.bufpos];
  }
  
  public int getEndColumn()
  {
    return this.bufcolumn[this.bufpos];
  }
  
  public int getEndLine()
  {
    return this.bufline[this.bufpos];
  }
  
  public int getLine()
  {
    return this.bufline[this.bufpos];
  }
  
  protected int getTabSize(int paramInt)
  {
    return this.tabSize;
  }
  
  public char readChar()
    throws IOException
  {
    if (this.inBuf > 0)
    {
      this.inBuf -= 1;
      i = this.bufpos + 1;
      this.bufpos = i;
      if (i == this.bufsize) {
        this.bufpos = 0;
      }
      return this.buffer[this.bufpos];
    }
    int i = this.bufpos + 1;
    this.bufpos = i;
    if (i >= this.maxNextCharInd) {
      FillBuff();
    }
    char c = this.buffer[this.bufpos];
    UpdateLineColumn(c);
    return c;
  }
  
  protected void setTabSize(int paramInt)
  {
    this.tabSize = paramInt;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\mimeversion\parser\SimpleCharStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */