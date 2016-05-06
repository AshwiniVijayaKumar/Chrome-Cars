package org.apache.james.mime4j.field.structured.parser;

import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

public class StructuredFieldParser
  implements StructuredFieldParserConstants
{
  private static int[] jj_la1_0;
  private Vector<int[]> jj_expentries = new Vector();
  private int[] jj_expentry;
  private int jj_gen;
  SimpleCharStream jj_input_stream;
  private int jj_kind = -1;
  private final int[] jj_la1 = new int[2];
  public Token jj_nt;
  private int jj_ntk;
  private boolean preserveFolding = false;
  public Token token;
  public StructuredFieldParserTokenManager token_source;
  
  static {}
  
  public StructuredFieldParser(InputStream paramInputStream)
  {
    this(paramInputStream, null);
  }
  
  public StructuredFieldParser(InputStream paramInputStream, String paramString)
  {
    try
    {
      this.jj_input_stream = new SimpleCharStream(paramInputStream, paramString, 1, 1);
      this.token_source = new StructuredFieldParserTokenManager(this.jj_input_stream);
      this.token = new Token();
      this.jj_ntk = -1;
      this.jj_gen = 0;
      int i = 0;
      while (i < 2)
      {
        this.jj_la1[i] = -1;
        i += 1;
      }
      return;
    }
    catch (UnsupportedEncodingException paramInputStream)
    {
      throw new RuntimeException(paramInputStream);
    }
  }
  
  public StructuredFieldParser(Reader paramReader)
  {
    this.jj_input_stream = new SimpleCharStream(paramReader, 1, 1);
    this.token_source = new StructuredFieldParserTokenManager(this.jj_input_stream);
    this.token = new Token();
    this.jj_ntk = -1;
    this.jj_gen = 0;
    int i = 0;
    while (i < 2)
    {
      this.jj_la1[i] = -1;
      i += 1;
    }
  }
  
  public StructuredFieldParser(StructuredFieldParserTokenManager paramStructuredFieldParserTokenManager)
  {
    this.token_source = paramStructuredFieldParserTokenManager;
    this.token = new Token();
    this.jj_ntk = -1;
    this.jj_gen = 0;
    int i = 0;
    while (i < 2)
    {
      this.jj_la1[i] = -1;
      i += 1;
    }
  }
  
  private final String doParse()
    throws ParseException
  {
    StringBuffer localStringBuffer = new StringBuffer(50);
    int i = 0;
    int j = 1;
    for (;;)
    {
      if (this.jj_ntk == -1) {}
      for (int k = jj_ntk();; k = this.jj_ntk) {
        switch (k)
        {
        default: 
          this.jj_la1[0] = this.jj_gen;
          return localStringBuffer.toString();
        }
      }
      if (this.jj_ntk == -1) {}
      for (k = jj_ntk();; k = this.jj_ntk) {
        switch (k)
        {
        default: 
          this.jj_la1[1] = this.jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      Token localToken = jj_consume_token(15);
      int m;
      if (j != 0)
      {
        k = 0;
        m = i;
      }
      for (;;)
      {
        localStringBuffer.append(localToken.image);
        j = k;
        i = m;
        break;
        k = j;
        m = i;
        if (i != 0)
        {
          localStringBuffer.append(" ");
          m = 0;
          k = j;
        }
      }
      localStringBuffer.append(jj_consume_token(11).image);
      continue;
      localToken = jj_consume_token(13);
      if (j != 0)
      {
        k = 0;
        m = i;
      }
      for (;;)
      {
        localStringBuffer.append(localToken.image);
        j = k;
        i = m;
        break;
        k = j;
        m = i;
        if (i != 0)
        {
          localStringBuffer.append(" ");
          m = 0;
          k = j;
        }
      }
      jj_consume_token(12);
      if (this.preserveFolding)
      {
        localStringBuffer.append("\r\n");
        continue;
        jj_consume_token(14);
        i = 1;
      }
    }
  }
  
  private final Token jj_consume_token(int paramInt)
    throws ParseException
  {
    Token localToken1 = this.token;
    if (localToken1.next != null) {}
    Token localToken3;
    for (this.token = this.token.next;; this.token = localToken3)
    {
      this.jj_ntk = -1;
      if (this.token.kind != paramInt) {
        break;
      }
      this.jj_gen += 1;
      return this.token;
      Token localToken2 = this.token;
      localToken3 = this.token_source.getNextToken();
      localToken2.next = localToken3;
    }
    this.token = localToken1;
    this.jj_kind = paramInt;
    throw generateParseException();
  }
  
  private static void jj_la1_0()
  {
    jj_la1_0 = new int[] { 63488, 63488 };
  }
  
  private final int jj_ntk()
  {
    Token localToken1 = this.token.next;
    this.jj_nt = localToken1;
    if (localToken1 == null)
    {
      localToken1 = this.token;
      Token localToken2 = this.token_source.getNextToken();
      localToken1.next = localToken2;
      i = localToken2.kind;
      this.jj_ntk = i;
      return i;
    }
    int i = this.jj_nt.kind;
    this.jj_ntk = i;
    return i;
  }
  
  public void ReInit(InputStream paramInputStream)
  {
    ReInit(paramInputStream, null);
  }
  
  public void ReInit(InputStream paramInputStream, String paramString)
  {
    try
    {
      this.jj_input_stream.ReInit(paramInputStream, paramString, 1, 1);
      this.token_source.ReInit(this.jj_input_stream);
      this.token = new Token();
      this.jj_ntk = -1;
      this.jj_gen = 0;
      int i = 0;
      while (i < 2)
      {
        this.jj_la1[i] = -1;
        i += 1;
      }
      return;
    }
    catch (UnsupportedEncodingException paramInputStream)
    {
      throw new RuntimeException(paramInputStream);
    }
  }
  
  public void ReInit(Reader paramReader)
  {
    this.jj_input_stream.ReInit(paramReader, 1, 1);
    this.token_source.ReInit(this.jj_input_stream);
    this.token = new Token();
    this.jj_ntk = -1;
    this.jj_gen = 0;
    int i = 0;
    while (i < 2)
    {
      this.jj_la1[i] = -1;
      i += 1;
    }
  }
  
  public void ReInit(StructuredFieldParserTokenManager paramStructuredFieldParserTokenManager)
  {
    this.token_source = paramStructuredFieldParserTokenManager;
    this.token = new Token();
    this.jj_ntk = -1;
    this.jj_gen = 0;
    int i = 0;
    while (i < 2)
    {
      this.jj_la1[i] = -1;
      i += 1;
    }
  }
  
  public final void disable_tracing() {}
  
  public final void enable_tracing() {}
  
  public ParseException generateParseException()
  {
    this.jj_expentries.removeAllElements();
    Object localObject = new boolean[18];
    int i = 0;
    while (i < 18)
    {
      localObject[i] = 0;
      i += 1;
    }
    if (this.jj_kind >= 0)
    {
      localObject[this.jj_kind] = 1;
      this.jj_kind = -1;
    }
    i = 0;
    while (i < 2)
    {
      if (this.jj_la1[i] == this.jj_gen)
      {
        int j = 0;
        while (j < 32)
        {
          if ((jj_la1_0[i] & 1 << j) != 0) {
            localObject[j] = 1;
          }
          j += 1;
        }
      }
      i += 1;
    }
    i = 0;
    while (i < 18)
    {
      if (localObject[i] != 0)
      {
        this.jj_expentry = new int[1];
        this.jj_expentry[0] = i;
        this.jj_expentries.addElement(this.jj_expentry);
      }
      i += 1;
    }
    localObject = new int[this.jj_expentries.size()][];
    i = 0;
    while (i < this.jj_expentries.size())
    {
      localObject[i] = ((int[])(int[])this.jj_expentries.elementAt(i));
      i += 1;
    }
    return new ParseException(this.token, (int[][])localObject, tokenImage);
  }
  
  public final Token getNextToken()
  {
    if (this.token.next != null) {}
    Token localToken2;
    for (this.token = this.token.next;; this.token = localToken2)
    {
      this.jj_ntk = -1;
      this.jj_gen += 1;
      return this.token;
      Token localToken1 = this.token;
      localToken2 = this.token_source.getNextToken();
      localToken1.next = localToken2;
    }
  }
  
  public final Token getToken(int paramInt)
  {
    Object localObject = this.token;
    int i = 0;
    if (i < paramInt)
    {
      if (((Token)localObject).next != null) {}
      Token localToken;
      for (localObject = ((Token)localObject).next;; localObject = localToken)
      {
        i += 1;
        break;
        localToken = this.token_source.getNextToken();
        ((Token)localObject).next = localToken;
      }
    }
    return (Token)localObject;
  }
  
  public boolean isFoldingPreserved()
  {
    return this.preserveFolding;
  }
  
  public String parse()
    throws ParseException
  {
    try
    {
      String str = doParse();
      return str;
    }
    catch (TokenMgrError localTokenMgrError)
    {
      throw new ParseException(localTokenMgrError);
    }
  }
  
  public void setFoldingPreserved(boolean paramBoolean)
  {
    this.preserveFolding = paramBoolean;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\structured\parser\StructuredFieldParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */