package org.apache.james.mime4j.field.language.parser;

import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ContentLanguageParser
  implements ContentLanguageParserConstants
{
  private static int[] jj_la1_0;
  private Vector<int[]> jj_expentries = new Vector();
  private int[] jj_expentry;
  private int jj_gen;
  SimpleCharStream jj_input_stream;
  private int jj_kind = -1;
  private final int[] jj_la1 = new int[3];
  public Token jj_nt;
  private int jj_ntk;
  private List<String> languages = new ArrayList();
  public Token token;
  public ContentLanguageParserTokenManager token_source;
  
  static {}
  
  public ContentLanguageParser(InputStream paramInputStream)
  {
    this(paramInputStream, null);
  }
  
  public ContentLanguageParser(InputStream paramInputStream, String paramString)
  {
    try
    {
      this.jj_input_stream = new SimpleCharStream(paramInputStream, paramString, 1, 1);
      this.token_source = new ContentLanguageParserTokenManager(this.jj_input_stream);
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
  
  public ContentLanguageParser(Reader paramReader)
  {
    this.jj_input_stream = new SimpleCharStream(paramReader, 1, 1);
    this.token_source = new ContentLanguageParserTokenManager(this.jj_input_stream);
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
  
  public ContentLanguageParser(ContentLanguageParserTokenManager paramContentLanguageParserTokenManager)
  {
    this.token_source = paramContentLanguageParserTokenManager;
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
  
  private final List<String> doParse()
    throws ParseException
  {
    language();
    for (;;)
    {
      if (this.jj_ntk == -1) {}
      for (int i = jj_ntk();; i = this.jj_ntk) {
        switch (i)
        {
        default: 
          this.jj_la1[0] = this.jj_gen;
          return this.languages;
        }
      }
      jj_consume_token(1);
      language();
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
    jj_la1_0 = new int[] { 2, 524292, 524292 };
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
  
  public void ReInit(ContentLanguageParserTokenManager paramContentLanguageParserTokenManager)
  {
    this.token_source = paramContentLanguageParserTokenManager;
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
  
  public final String language()
    throws ParseException
  {
    Object localObject = new StringBuffer();
    ((StringBuffer)localObject).append(jj_consume_token(18).image);
    for (;;)
    {
      if (this.jj_ntk == -1) {}
      for (int i = jj_ntk();; i = this.jj_ntk) {
        switch (i)
        {
        default: 
          this.jj_la1[1] = this.jj_gen;
          localObject = ((StringBuffer)localObject).toString();
          this.languages.add(localObject);
          return (String)localObject;
        }
      }
      if (this.jj_ntk == -1) {}
      for (i = jj_ntk();; i = this.jj_ntk) {
        switch (i)
        {
        default: 
          this.jj_la1[2] = this.jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      jj_consume_token(2);
      Token localToken = jj_consume_token(18);
      ((StringBuffer)localObject).append('-');
      ((StringBuffer)localObject).append(localToken.image);
      continue;
      localToken = jj_consume_token(19);
      ((StringBuffer)localObject).append('-');
      ((StringBuffer)localObject).append(localToken.image);
    }
  }
  
  public List<String> parse()
    throws ParseException
  {
    try
    {
      List localList = doParse();
      return localList;
    }
    catch (TokenMgrError localTokenMgrError)
    {
      throw new ParseException(localTokenMgrError);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\language\parser\ContentLanguageParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */