package org.apache.james.mime4j.field.contentdisposition.parser;

import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ContentDispositionParser
  implements ContentDispositionParserConstants
{
  private static int[] jj_la1_0;
  private String dispositionType;
  private Vector<int[]> jj_expentries = new Vector();
  private int[] jj_expentry;
  private int jj_gen;
  SimpleCharStream jj_input_stream;
  private int jj_kind = -1;
  private final int[] jj_la1 = new int[3];
  public Token jj_nt;
  private int jj_ntk;
  private List<String> paramNames = new ArrayList();
  private List<String> paramValues = new ArrayList();
  public Token token;
  public ContentDispositionParserTokenManager token_source;
  
  static {}
  
  public ContentDispositionParser(InputStream paramInputStream)
  {
    this(paramInputStream, null);
  }
  
  public ContentDispositionParser(InputStream paramInputStream, String paramString)
  {
    try
    {
      this.jj_input_stream = new SimpleCharStream(paramInputStream, paramString, 1, 1);
      this.token_source = new ContentDispositionParserTokenManager(this.jj_input_stream);
      this.token = new Token();
      this.jj_ntk = -1;
      this.jj_gen = 0;
      int i = 0;
      while (i < 3)
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
  
  public ContentDispositionParser(Reader paramReader)
  {
    this.jj_input_stream = new SimpleCharStream(paramReader, 1, 1);
    this.token_source = new ContentDispositionParserTokenManager(this.jj_input_stream);
    this.token = new Token();
    this.jj_ntk = -1;
    this.jj_gen = 0;
    int i = 0;
    while (i < 3)
    {
      this.jj_la1[i] = -1;
      i += 1;
    }
  }
  
  public ContentDispositionParser(ContentDispositionParserTokenManager paramContentDispositionParserTokenManager)
  {
    this.token_source = paramContentDispositionParserTokenManager;
    this.token = new Token();
    this.jj_ntk = -1;
    this.jj_gen = 0;
    int i = 0;
    while (i < 3)
    {
      this.jj_la1[i] = -1;
      i += 1;
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
    jj_la1_0 = new int[] { 2, 8, 1835008 };
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
  
  public static void main(String[] paramArrayOfString)
    throws ParseException
  {
    try
    {
      for (;;)
      {
        new ContentDispositionParser(System.in).parseLine();
      }
      return;
    }
    catch (Exception paramArrayOfString)
    {
      paramArrayOfString.printStackTrace();
    }
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
      while (i < 3)
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
    while (i < 3)
    {
      this.jj_la1[i] = -1;
      i += 1;
    }
  }
  
  public void ReInit(ContentDispositionParserTokenManager paramContentDispositionParserTokenManager)
  {
    this.token_source = paramContentDispositionParserTokenManager;
    this.token = new Token();
    this.jj_ntk = -1;
    this.jj_gen = 0;
    int i = 0;
    while (i < 3)
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
    Object localObject = new boolean[23];
    int i = 0;
    while (i < 23)
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
    while (i < 3)
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
    while (i < 23)
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
  
  public String getDispositionType()
  {
    return this.dispositionType;
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
  
  public List<String> getParamNames()
  {
    return this.paramNames;
  }
  
  public List<String> getParamValues()
  {
    return this.paramValues;
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
  
  public final void parameter()
    throws ParseException
  {
    Token localToken = jj_consume_token(20);
    jj_consume_token(4);
    String str = value();
    this.paramNames.add(localToken.image);
    this.paramValues.add(str);
  }
  
  public final void parse()
    throws ParseException
  {
    this.dispositionType = jj_consume_token(20).image;
    for (;;)
    {
      if (this.jj_ntk == -1) {}
      for (int i = jj_ntk();; i = this.jj_ntk) {
        switch (i)
        {
        default: 
          this.jj_la1[1] = this.jj_gen;
          return;
        }
      }
      jj_consume_token(3);
      parameter();
    }
  }
  
  public final void parseAll()
    throws ParseException
  {
    parse();
    jj_consume_token(0);
  }
  
  public final void parseLine()
    throws ParseException
  {
    parse();
    int i;
    if (this.jj_ntk == -1)
    {
      i = jj_ntk();
      switch (i)
      {
      default: 
        this.jj_la1[0] = this.jj_gen;
      }
    }
    for (;;)
    {
      jj_consume_token(2);
      return;
      i = this.jj_ntk;
      break;
      jj_consume_token(1);
    }
  }
  
  public final String value()
    throws ParseException
  {
    if (this.jj_ntk == -1) {}
    for (int i = jj_ntk();; i = this.jj_ntk) {
      switch (i)
      {
      default: 
        this.jj_la1[2] = this.jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    Token localToken = jj_consume_token(20);
    for (;;)
    {
      return localToken.image;
      localToken = jj_consume_token(19);
      continue;
      localToken = jj_consume_token(18);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\contentdisposition\parser\ContentDispositionParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */