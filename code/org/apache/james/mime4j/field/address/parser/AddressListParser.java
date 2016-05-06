package org.apache.james.mime4j.field.address.parser;

import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Vector;

public class AddressListParser
  implements AddressListParserConstants, AddressListParserTreeConstants
{
  private static int[] jj_la1_0;
  private static int[] jj_la1_1;
  private final JJCalls[] jj_2_rtns = new JJCalls[2];
  private int jj_endpos;
  private Vector<int[]> jj_expentries = new Vector();
  private int[] jj_expentry;
  private int jj_gc = 0;
  private int jj_gen;
  SimpleCharStream jj_input_stream;
  private int jj_kind = -1;
  private int jj_la;
  private final int[] jj_la1 = new int[22];
  private Token jj_lastpos;
  private int[] jj_lasttokens = new int[100];
  private final LookaheadSuccess jj_ls = new LookaheadSuccess(null);
  public Token jj_nt;
  private int jj_ntk;
  private boolean jj_rescan = false;
  private Token jj_scanpos;
  private boolean jj_semLA;
  protected JJTAddressListParserState jjtree = new JJTAddressListParserState();
  public boolean lookingAhead = false;
  public Token token;
  public AddressListParserTokenManager token_source;
  
  static
  {
    jj_la1_0();
    jj_la1_1();
  }
  
  public AddressListParser(InputStream paramInputStream)
  {
    this(paramInputStream, null);
  }
  
  public AddressListParser(InputStream paramInputStream, String paramString)
  {
    int i;
    try
    {
      this.jj_input_stream = new SimpleCharStream(paramInputStream, paramString, 1, 1);
      this.token_source = new AddressListParserTokenManager(this.jj_input_stream);
      this.token = new Token();
      this.jj_ntk = -1;
      this.jj_gen = 0;
      i = 0;
      while (i < 22)
      {
        this.jj_la1[i] = -1;
        i += 1;
      }
      i = 0;
    }
    catch (UnsupportedEncodingException paramInputStream)
    {
      throw new RuntimeException(paramInputStream);
    }
    while (i < this.jj_2_rtns.length)
    {
      this.jj_2_rtns[i] = new JJCalls();
      i += 1;
    }
  }
  
  public AddressListParser(Reader paramReader)
  {
    this.jj_input_stream = new SimpleCharStream(paramReader, 1, 1);
    this.token_source = new AddressListParserTokenManager(this.jj_input_stream);
    this.token = new Token();
    this.jj_ntk = -1;
    this.jj_gen = 0;
    int i = 0;
    while (i < 22)
    {
      this.jj_la1[i] = -1;
      i += 1;
    }
    i = 0;
    while (i < this.jj_2_rtns.length)
    {
      this.jj_2_rtns[i] = new JJCalls();
      i += 1;
    }
  }
  
  public AddressListParser(AddressListParserTokenManager paramAddressListParserTokenManager)
  {
    this.token_source = paramAddressListParserTokenManager;
    this.token = new Token();
    this.jj_ntk = -1;
    this.jj_gen = 0;
    int i = 0;
    while (i < 22)
    {
      this.jj_la1[i] = -1;
      i += 1;
    }
    i = 0;
    while (i < this.jj_2_rtns.length)
    {
      this.jj_2_rtns[i] = new JJCalls();
      i += 1;
    }
  }
  
  /* Error */
  private final boolean jj_2_1(int paramInt)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: aload_0
    //   3: iload_1
    //   4: putfield 134	org/apache/james/mime4j/field/address/parser/AddressListParser:jj_la	I
    //   7: aload_0
    //   8: getfield 115	org/apache/james/mime4j/field/address/parser/AddressListParser:token	Lorg/apache/james/mime4j/field/address/parser/Token;
    //   11: astore 4
    //   13: aload_0
    //   14: aload 4
    //   16: putfield 136	org/apache/james/mime4j/field/address/parser/AddressListParser:jj_scanpos	Lorg/apache/james/mime4j/field/address/parser/Token;
    //   19: aload_0
    //   20: aload 4
    //   22: putfield 138	org/apache/james/mime4j/field/address/parser/AddressListParser:jj_lastpos	Lorg/apache/james/mime4j/field/address/parser/Token;
    //   25: aload_0
    //   26: invokespecial 142	org/apache/james/mime4j/field/address/parser/AddressListParser:jj_3_1	()Z
    //   29: istore_3
    //   30: iload_3
    //   31: ifne +11 -> 42
    //   34: aload_0
    //   35: iconst_0
    //   36: iload_1
    //   37: invokespecial 146	org/apache/james/mime4j/field/address/parser/AddressListParser:jj_save	(II)V
    //   40: iload_2
    //   41: ireturn
    //   42: iconst_0
    //   43: istore_2
    //   44: goto -10 -> 34
    //   47: astore 4
    //   49: aload_0
    //   50: iconst_0
    //   51: iload_1
    //   52: invokespecial 146	org/apache/james/mime4j/field/address/parser/AddressListParser:jj_save	(II)V
    //   55: iconst_1
    //   56: ireturn
    //   57: astore 4
    //   59: aload_0
    //   60: iconst_0
    //   61: iload_1
    //   62: invokespecial 146	org/apache/james/mime4j/field/address/parser/AddressListParser:jj_save	(II)V
    //   65: aload 4
    //   67: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	68	0	this	AddressListParser
    //   0	68	1	paramInt	int
    //   1	43	2	bool1	boolean
    //   29	2	3	bool2	boolean
    //   11	10	4	localToken	Token
    //   47	1	4	localLookaheadSuccess	LookaheadSuccess
    //   57	9	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   25	30	47	org/apache/james/mime4j/field/address/parser/AddressListParser$LookaheadSuccess
    //   25	30	57	finally
  }
  
  /* Error */
  private final boolean jj_2_2(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: iload_1
    //   2: putfield 134	org/apache/james/mime4j/field/address/parser/AddressListParser:jj_la	I
    //   5: aload_0
    //   6: getfield 115	org/apache/james/mime4j/field/address/parser/AddressListParser:token	Lorg/apache/james/mime4j/field/address/parser/Token;
    //   9: astore_3
    //   10: aload_0
    //   11: aload_3
    //   12: putfield 136	org/apache/james/mime4j/field/address/parser/AddressListParser:jj_scanpos	Lorg/apache/james/mime4j/field/address/parser/Token;
    //   15: aload_0
    //   16: aload_3
    //   17: putfield 138	org/apache/james/mime4j/field/address/parser/AddressListParser:jj_lastpos	Lorg/apache/james/mime4j/field/address/parser/Token;
    //   20: aload_0
    //   21: invokespecial 150	org/apache/james/mime4j/field/address/parser/AddressListParser:jj_3_2	()Z
    //   24: istore_2
    //   25: iload_2
    //   26: ifne +13 -> 39
    //   29: iconst_1
    //   30: istore_2
    //   31: aload_0
    //   32: iconst_1
    //   33: iload_1
    //   34: invokespecial 146	org/apache/james/mime4j/field/address/parser/AddressListParser:jj_save	(II)V
    //   37: iload_2
    //   38: ireturn
    //   39: iconst_0
    //   40: istore_2
    //   41: goto -10 -> 31
    //   44: astore_3
    //   45: aload_0
    //   46: iconst_1
    //   47: iload_1
    //   48: invokespecial 146	org/apache/james/mime4j/field/address/parser/AddressListParser:jj_save	(II)V
    //   51: iconst_1
    //   52: ireturn
    //   53: astore_3
    //   54: aload_0
    //   55: iconst_1
    //   56: iload_1
    //   57: invokespecial 146	org/apache/james/mime4j/field/address/parser/AddressListParser:jj_save	(II)V
    //   60: aload_3
    //   61: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	62	0	this	AddressListParser
    //   0	62	1	paramInt	int
    //   24	17	2	bool	boolean
    //   9	8	3	localToken	Token
    //   44	1	3	localLookaheadSuccess	LookaheadSuccess
    //   53	8	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   20	25	44	org/apache/james/mime4j/field/address/parser/AddressListParser$LookaheadSuccess
    //   20	25	53	finally
  }
  
  private final boolean jj_3R_10()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_12())
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(18)) {
        return true;
      }
    }
    return false;
  }
  
  private final boolean jj_3R_11()
  {
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(9)) {
      this.jj_scanpos = localToken;
    }
    localToken = this.jj_scanpos;
    if (jj_scan_token(14))
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(31)) {
        return true;
      }
    }
    return false;
  }
  
  private final boolean jj_3R_12()
  {
    if (jj_scan_token(14)) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_13());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_13()
  {
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(9)) {
      this.jj_scanpos = localToken;
    }
    return jj_scan_token(14);
  }
  
  private final boolean jj_3R_8()
  {
    if (jj_3R_9()) {}
    while ((jj_scan_token(8)) || (jj_3R_10())) {
      return true;
    }
    return false;
  }
  
  private final boolean jj_3R_9()
  {
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(14))
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(31)) {
        return true;
      }
    }
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_11());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3_1()
  {
    return jj_3R_8();
  }
  
  private final boolean jj_3_2()
  {
    return jj_3R_8();
  }
  
  private void jj_add_error_token(int paramInt1, int paramInt2)
  {
    if (paramInt2 >= 100) {}
    do
    {
      return;
      if (paramInt2 == this.jj_endpos + 1)
      {
        localObject = this.jj_lasttokens;
        paramInt2 = this.jj_endpos;
        this.jj_endpos = (paramInt2 + 1);
        localObject[paramInt2] = paramInt1;
        return;
      }
    } while (this.jj_endpos == 0);
    this.jj_expentry = new int[this.jj_endpos];
    int i = 0;
    while (i < this.jj_endpos)
    {
      this.jj_expentry[i] = this.jj_lasttokens[i];
      i += 1;
    }
    int j = 0;
    Object localObject = this.jj_expentries.elements();
    label99:
    int[] arrayOfInt;
    do
    {
      i = j;
      if (!((Enumeration)localObject).hasMoreElements()) {
        break;
      }
      arrayOfInt = (int[])((Enumeration)localObject).nextElement();
    } while (arrayOfInt.length != this.jj_expentry.length);
    int k = 1;
    j = 0;
    for (;;)
    {
      i = k;
      if (j < this.jj_expentry.length)
      {
        if (arrayOfInt[j] != this.jj_expentry[j]) {
          i = 0;
        }
      }
      else
      {
        j = i;
        if (i == 0) {
          break label99;
        }
        if (i == 0) {
          this.jj_expentries.addElement(this.jj_expentry);
        }
        if (paramInt2 == 0) {
          break;
        }
        localObject = this.jj_lasttokens;
        this.jj_endpos = paramInt2;
        localObject[(paramInt2 - 1)] = paramInt1;
        return;
      }
      j += 1;
    }
  }
  
  private final Token jj_consume_token(int paramInt)
    throws ParseException
  {
    Object localObject = this.token;
    if (((Token)localObject).next != null)
    {
      this.token = this.token.next;
      this.jj_ntk = -1;
      if (this.token.kind != paramInt) {
        break label159;
      }
      this.jj_gen += 1;
      paramInt = this.jj_gc + 1;
      this.jj_gc = paramInt;
      if (paramInt > 100)
      {
        this.jj_gc = 0;
        paramInt = 0;
      }
    }
    else
    {
      for (;;)
      {
        if (paramInt >= this.jj_2_rtns.length) {
          break label154;
        }
        localObject = this.jj_2_rtns[paramInt];
        for (;;)
        {
          if (localObject != null)
          {
            if (((JJCalls)localObject).gen < this.jj_gen) {
              ((JJCalls)localObject).first = null;
            }
            localObject = ((JJCalls)localObject).next;
            continue;
            Token localToken1 = this.token;
            Token localToken2 = this.token_source.getNextToken();
            localToken1.next = localToken2;
            this.token = localToken2;
            break;
          }
        }
        paramInt += 1;
      }
    }
    label154:
    return this.token;
    label159:
    this.token = ((Token)localObject);
    this.jj_kind = paramInt;
    throw generateParseException();
  }
  
  private static void jj_la1_0()
  {
    jj_la1_0 = new int[] { 2, -2147467200, 8, -2147467200, 80, -2147467200, -2147467200, -2147467200, 8, -2147467200, 256, 264, 8, -2147467264, -2147467264, -2147467264, -2147466752, 512, -2147467264, 16896, 512, 278528 };
  }
  
  private static void jj_la1_1()
  {
    jj_la1_1 = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
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
  
  private final void jj_rescan_token()
  {
    this.jj_rescan = true;
    int i = 0;
    for (;;)
    {
      if (i < 2) {}
      try
      {
        Object localObject1 = this.jj_2_rtns[i];
        Object localObject2;
        if (((JJCalls)localObject1).gen > this.jj_gen)
        {
          this.jj_la = ((JJCalls)localObject1).arg;
          localObject2 = ((JJCalls)localObject1).first;
          this.jj_scanpos = ((Token)localObject2);
          this.jj_lastpos = ((Token)localObject2);
          switch (i)
          {
          }
        }
        for (;;)
        {
          localObject2 = ((JJCalls)localObject1).next;
          localObject1 = localObject2;
          if (localObject2 != null) {
            break;
          }
          break label115;
          jj_3_1();
          continue;
          jj_3_2();
          continue;
          this.jj_rescan = false;
          return;
        }
      }
      catch (LookaheadSuccess localLookaheadSuccess)
      {
        label115:
        for (;;) {}
      }
      i += 1;
    }
  }
  
  private final void jj_save(int paramInt1, int paramInt2)
  {
    for (JJCalls localJJCalls1 = this.jj_2_rtns[paramInt1];; localJJCalls1 = localJJCalls1.next)
    {
      JJCalls localJJCalls2 = localJJCalls1;
      if (localJJCalls1.gen > this.jj_gen)
      {
        if (localJJCalls1.next == null)
        {
          localJJCalls2 = new JJCalls();
          localJJCalls1.next = localJJCalls2;
        }
      }
      else
      {
        localJJCalls2.gen = (this.jj_gen + paramInt2 - this.jj_la);
        localJJCalls2.first = this.token;
        localJJCalls2.arg = paramInt2;
        return;
      }
    }
  }
  
  private final boolean jj_scan_token(int paramInt)
  {
    Token localToken1;
    if (this.jj_scanpos == this.jj_lastpos)
    {
      this.jj_la -= 1;
      if (this.jj_scanpos.next == null)
      {
        localToken1 = this.jj_scanpos;
        Token localToken2 = this.token_source.getNextToken();
        localToken1.next = localToken2;
        this.jj_scanpos = localToken2;
        this.jj_lastpos = localToken2;
      }
    }
    while (this.jj_rescan)
    {
      int i = 0;
      localToken1 = this.token;
      for (;;)
      {
        if ((localToken1 != null) && (localToken1 != this.jj_scanpos))
        {
          i += 1;
          localToken1 = localToken1.next;
          continue;
          localToken1 = this.jj_scanpos.next;
          this.jj_scanpos = localToken1;
          this.jj_lastpos = localToken1;
          break;
          this.jj_scanpos = this.jj_scanpos.next;
          break;
        }
      }
      if (localToken1 != null) {
        jj_add_error_token(paramInt, i);
      }
    }
    if (this.jj_scanpos.kind != paramInt) {
      return true;
    }
    if ((this.jj_la == 0) && (this.jj_scanpos == this.jj_lastpos)) {
      throw this.jj_ls;
    }
    return false;
  }
  
  public static void main(String[] paramArrayOfString)
    throws ParseException
  {
    try
    {
      for (;;)
      {
        paramArrayOfString = new AddressListParser(System.in);
        paramArrayOfString.parseLine();
        ((SimpleNode)paramArrayOfString.jjtree.rootNode()).dump("> ");
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
    int i;
    try
    {
      this.jj_input_stream.ReInit(paramInputStream, paramString, 1, 1);
      this.token_source.ReInit(this.jj_input_stream);
      this.token = new Token();
      this.jj_ntk = -1;
      this.jjtree.reset();
      this.jj_gen = 0;
      i = 0;
      while (i < 22)
      {
        this.jj_la1[i] = -1;
        i += 1;
      }
      i = 0;
    }
    catch (UnsupportedEncodingException paramInputStream)
    {
      throw new RuntimeException(paramInputStream);
    }
    while (i < this.jj_2_rtns.length)
    {
      this.jj_2_rtns[i] = new JJCalls();
      i += 1;
    }
  }
  
  public void ReInit(Reader paramReader)
  {
    this.jj_input_stream.ReInit(paramReader, 1, 1);
    this.token_source.ReInit(this.jj_input_stream);
    this.token = new Token();
    this.jj_ntk = -1;
    this.jjtree.reset();
    this.jj_gen = 0;
    int i = 0;
    while (i < 22)
    {
      this.jj_la1[i] = -1;
      i += 1;
    }
    i = 0;
    while (i < this.jj_2_rtns.length)
    {
      this.jj_2_rtns[i] = new JJCalls();
      i += 1;
    }
  }
  
  public void ReInit(AddressListParserTokenManager paramAddressListParserTokenManager)
  {
    this.token_source = paramAddressListParserTokenManager;
    this.token = new Token();
    this.jj_ntk = -1;
    this.jjtree.reset();
    this.jj_gen = 0;
    int i = 0;
    while (i < 22)
    {
      this.jj_la1[i] = -1;
      i += 1;
    }
    i = 0;
    while (i < this.jj_2_rtns.length)
    {
      this.jj_2_rtns[i] = new JJCalls();
      i += 1;
    }
  }
  
  public final void addr_spec()
    throws ParseException
  {
    ASTaddr_spec localASTaddr_spec = new ASTaddr_spec(9);
    int k = 1;
    int i = 1;
    this.jjtree.openNodeScope(localASTaddr_spec);
    jjtreeOpenNodeScope(localASTaddr_spec);
    int j = k;
    for (;;)
    {
      try
      {
        local_part();
        j = k;
        jj_consume_token(8);
        j = k;
        domain();
        if (1 != 0)
        {
          this.jjtree.closeNodeScope(localASTaddr_spec, true);
          jjtreeCloseNodeScope(localASTaddr_spec);
        }
        return;
      }
      catch (Throwable localThrowable)
      {
        if (1 != 0)
        {
          j = k;
          this.jjtree.clearNodeScope(localASTaddr_spec);
          i = 0;
          j = i;
          if (!(localThrowable instanceof RuntimeException)) {
            break;
          }
          j = i;
          throw ((RuntimeException)localThrowable);
        }
      }
      finally
      {
        if (j != 0)
        {
          this.jjtree.closeNodeScope(localASTaddr_spec, true);
          jjtreeCloseNodeScope(localASTaddr_spec);
        }
      }
      j = k;
      this.jjtree.popNode();
    }
    j = i;
    if ((localObject instanceof ParseException))
    {
      j = i;
      throw ((ParseException)localObject);
    }
    j = i;
    throw ((Error)localObject);
  }
  
  public final void address()
    throws ParseException
  {
    ASTaddress localASTaddress = new ASTaddress(2);
    int m = 1;
    int k = 1;
    this.jjtree.openNodeScope(localASTaddress);
    jjtreeOpenNodeScope(localASTaddress);
    int j = m;
    int i;
    for (;;)
    {
      try
      {
        if (jj_2_1(Integer.MAX_VALUE))
        {
          j = m;
          addr_spec();
          if (1 != 0)
          {
            this.jjtree.closeNodeScope(localASTaddress, true);
            jjtreeCloseNodeScope(localASTaddress);
          }
          return;
        }
        j = m;
        if (this.jj_ntk != -1) {
          break label189;
        }
        j = m;
        i = jj_ntk();
      }
      catch (Throwable localThrowable)
      {
        label93:
        if (1 == 0) {
          break;
        }
        j = m;
        this.jjtree.clearNodeScope(localASTaddress);
        i = 0;
        label146:
        j = i;
        if (!(localThrowable instanceof RuntimeException)) {
          break label319;
        }
        j = i;
        throw ((RuntimeException)localThrowable);
      }
      finally
      {
        if (j == 0) {
          continue;
        }
        this.jjtree.closeNodeScope(localASTaddress, true);
        jjtreeCloseNodeScope(localASTaddress);
      }
      j = m;
      this.jj_la1[5] = this.jj_gen;
      j = m;
      jj_consume_token(-1);
      j = m;
      throw new ParseException();
      label189:
      j = m;
      i = this.jj_ntk;
      break label345;
      j = m;
      angle_addr();
    }
    j = m;
    phrase();
    j = m;
    if (this.jj_ntk == -1)
    {
      j = m;
      i = jj_ntk();
    }
    for (;;)
    {
      j = m;
      this.jj_la1[4] = this.jj_gen;
      j = m;
      jj_consume_token(-1);
      j = m;
      throw new ParseException();
      j = m;
      i = this.jj_ntk;
      break label383;
      j = m;
      group_body();
      break;
      j = m;
      angle_addr();
      break;
      j = m;
      this.jjtree.popNode();
      i = k;
      break label146;
      label319:
      j = i;
      if ((localObject instanceof ParseException))
      {
        j = i;
        throw ((ParseException)localObject);
      }
      j = i;
      throw ((Error)localObject);
      label345:
      switch (i)
      {
      }
      break label93;
      label383:
      switch (i)
      {
      }
    }
  }
  
  public final void address_list()
    throws ParseException
  {
    ASTaddress_list localASTaddress_list = new ASTaddress_list(1);
    int m = 1;
    int k = 1;
    this.jjtree.openNodeScope(localASTaddress_list);
    jjtreeOpenNodeScope(localASTaddress_list);
    int j = m;
    for (;;)
    {
      try
      {
        if (this.jj_ntk == -1)
        {
          j = m;
          i = jj_ntk();
          break label326;
          j = m;
          this.jj_la1[1] = this.jj_gen;
          label65:
          j = m;
          if (this.jj_ntk != -1) {
            break label205;
          }
          j = m;
          i = jj_ntk();
          break label363;
          label87:
          j = m;
          this.jj_la1[2] = this.jj_gen;
          if (1 != 0)
          {
            this.jjtree.closeNodeScope(localASTaddress_list, true);
            jjtreeCloseNodeScope(localASTaddress_list);
          }
          return;
        }
        j = m;
        i = this.jj_ntk;
      }
      catch (Throwable localThrowable)
      {
        if (1 == 0) {
          break label284;
        }
        j = m;
        this.jjtree.clearNodeScope(localASTaddress_list);
        i = 0;
        j = i;
        if (!(localThrowable instanceof RuntimeException)) {
          break label300;
        }
        j = i;
        throw ((RuntimeException)localThrowable);
      }
      finally
      {
        if (j == 0) {
          continue;
        }
        this.jjtree.closeNodeScope(localASTaddress_list, true);
        jjtreeCloseNodeScope(localASTaddress_list);
      }
      j = m;
      address();
    }
    label162:
    label205:
    j = m;
    int i = this.jj_ntk;
    break label363;
    j = m;
    jj_consume_token(3);
    j = m;
    if (this.jj_ntk == -1)
    {
      j = m;
      i = jj_ntk();
    }
    for (;;)
    {
      j = m;
      this.jj_la1[3] = this.jj_gen;
      break label65;
      j = m;
      i = this.jj_ntk;
      break label387;
      j = m;
      address();
      break label65;
      label284:
      j = m;
      this.jjtree.popNode();
      i = k;
      break label162;
      label300:
      j = i;
      if ((localObject instanceof ParseException))
      {
        j = i;
        throw ((ParseException)localObject);
      }
      j = i;
      throw ((Error)localObject);
      label326:
      switch (i)
      {
      }
      break;
      label363:
      switch (i)
      {
      }
      break label87;
      label387:
      switch (i)
      {
      }
    }
  }
  
  public final void angle_addr()
    throws ParseException
  {
    ASTangle_addr localASTangle_addr = new ASTangle_addr(6);
    int m = 1;
    int k = 1;
    this.jjtree.openNodeScope(localASTangle_addr);
    jjtreeOpenNodeScope(localASTangle_addr);
    int j = m;
    for (;;)
    {
      try
      {
        jj_consume_token(6);
        j = m;
        if (this.jj_ntk == -1)
        {
          j = m;
          i = jj_ntk();
          break label241;
          j = m;
          this.jj_la1[10] = this.jj_gen;
          j = m;
          addr_spec();
          j = m;
          jj_consume_token(7);
          if (1 != 0)
          {
            this.jjtree.closeNodeScope(localASTangle_addr, true);
            jjtreeCloseNodeScope(localASTangle_addr);
          }
        }
        else
        {
          j = m;
          i = this.jj_ntk;
        }
      }
      catch (Throwable localThrowable)
      {
        if (1 == 0) {
          break label199;
        }
        j = m;
        this.jjtree.clearNodeScope(localASTangle_addr);
        i = 0;
        j = i;
        if (!(localThrowable instanceof RuntimeException)) {
          break label215;
        }
        j = i;
        throw ((RuntimeException)localThrowable);
      }
      finally
      {
        if (j == 0) {
          continue;
        }
        this.jjtree.closeNodeScope(localASTangle_addr, true);
        jjtreeCloseNodeScope(localASTangle_addr);
      }
      j = m;
      route();
      continue;
      label199:
      j = m;
      this.jjtree.popNode();
      int i = k;
      continue;
      label215:
      j = i;
      if ((localObject instanceof ParseException))
      {
        j = i;
        throw ((ParseException)localObject);
      }
      j = i;
      throw ((Error)localObject);
      label241:
      switch (i)
      {
      }
    }
  }
  
  public final void disable_tracing() {}
  
  public final void domain()
    throws ParseException
  {
    ASTdomain localASTdomain = new ASTdomain(11);
    this.jjtree.openNodeScope(localASTdomain);
    jjtreeOpenNodeScope(localASTdomain);
    try
    {
      if (this.jj_ntk != -1) {
        break label85;
      }
      i = jj_ntk();
    }
    finally
    {
      if (1 == 0) {
        break label83;
      }
      this.jjtree.closeNodeScope(localASTdomain, true);
      jjtreeCloseNodeScope(localASTdomain);
    }
    this.jj_la1[21] = this.jj_gen;
    jj_consume_token(-1);
    throw new ParseException();
    label83:
    label85:
    int i = this.jj_ntk;
    break label251;
    Token localToken = jj_consume_token(14);
    label100:
    if (this.jj_ntk == -1)
    {
      i = jj_ntk();
      break label283;
      label116:
      this.jj_la1[19] = this.jj_gen;
      label127:
      if (1 != 0)
      {
        this.jjtree.closeNodeScope(localASTdomain, true);
        jjtreeCloseNodeScope(localASTdomain);
      }
    }
    else
    {
      i = this.jj_ntk;
      break label283;
      if (this.jj_ntk == -1)
      {
        i = jj_ntk();
        break label315;
      }
    }
    for (;;)
    {
      this.jj_la1[20] = this.jj_gen;
      while (localToken.image.charAt(localToken.image.length() - 1) != '.')
      {
        throw new ParseException("Atoms in domain names must be separated by '.'");
        i = this.jj_ntk;
        break label315;
        localToken = jj_consume_token(9);
      }
      localToken = jj_consume_token(14);
      break label100;
      jj_consume_token(18);
      break label127;
      label251:
      switch (i)
      {
      }
      break;
      label283:
      switch (i)
      {
      }
      break label116;
      label315:
      switch (i)
      {
      }
    }
  }
  
  public final void enable_tracing() {}
  
  public ParseException generateParseException()
  {
    this.jj_expentries.removeAllElements();
    Object localObject = new boolean[34];
    int i = 0;
    while (i < 34)
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
    while (i < 22)
    {
      if (this.jj_la1[i] == this.jj_gen)
      {
        int j = 0;
        while (j < 32)
        {
          if ((jj_la1_0[i] & 1 << j) != 0) {
            localObject[j] = 1;
          }
          if ((jj_la1_1[i] & 1 << j) != 0) {
            localObject[(j + 32)] = 1;
          }
          j += 1;
        }
      }
      i += 1;
    }
    i = 0;
    while (i < 34)
    {
      if (localObject[i] != 0)
      {
        this.jj_expentry = new int[1];
        this.jj_expentry[0] = i;
        this.jj_expentries.addElement(this.jj_expentry);
      }
      i += 1;
    }
    this.jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
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
    int i;
    if (this.lookingAhead)
    {
      localObject = this.jj_scanpos;
      i = 0;
      label14:
      if (i >= paramInt) {
        return localObject;
      }
      if (((Token)localObject).next == null) {
        break label46;
      }
    }
    label46:
    Token localToken;
    for (Object localObject = ((Token)localObject).next;; localObject = localToken)
    {
      i += 1;
      break label14;
      localObject = this.token;
      break;
      localToken = this.token_source.getNextToken();
      ((Token)localObject).next = localToken;
    }
    return (Token)localObject;
  }
  
  public final void group_body()
    throws ParseException
  {
    ASTgroup_body localASTgroup_body = new ASTgroup_body(5);
    int m = 1;
    int k = 1;
    this.jjtree.openNodeScope(localASTgroup_body);
    jjtreeOpenNodeScope(localASTgroup_body);
    int j = m;
    for (;;)
    {
      try
      {
        jj_consume_token(4);
        j = m;
        if (this.jj_ntk == -1)
        {
          j = m;
          i = jj_ntk();
          break label347;
          j = m;
          this.jj_la1[7] = this.jj_gen;
          label75:
          j = m;
          if (this.jj_ntk != -1) {
            break label225;
          }
          j = m;
          i = jj_ntk();
          break label387;
          label97:
          j = m;
          this.jj_la1[8] = this.jj_gen;
          j = m;
          jj_consume_token(5);
          if (1 != 0)
          {
            this.jjtree.closeNodeScope(localASTgroup_body, true);
            jjtreeCloseNodeScope(localASTgroup_body);
          }
          return;
        }
        j = m;
        i = this.jj_ntk;
      }
      catch (Throwable localThrowable)
      {
        if (1 == 0) {
          break label305;
        }
        j = m;
        this.jjtree.clearNodeScope(localASTgroup_body);
        i = 0;
        j = i;
        if (!(localThrowable instanceof RuntimeException)) {
          break label321;
        }
        j = i;
        throw ((RuntimeException)localThrowable);
      }
      finally
      {
        if (j == 0) {
          continue;
        }
        this.jjtree.closeNodeScope(localASTgroup_body, true);
        jjtreeCloseNodeScope(localASTgroup_body);
      }
      j = m;
      mailbox();
    }
    label182:
    label225:
    j = m;
    int i = this.jj_ntk;
    break label387;
    j = m;
    jj_consume_token(3);
    j = m;
    if (this.jj_ntk == -1)
    {
      j = m;
      i = jj_ntk();
    }
    for (;;)
    {
      j = m;
      this.jj_la1[9] = this.jj_gen;
      break label75;
      j = m;
      i = this.jj_ntk;
      break label411;
      j = m;
      mailbox();
      break label75;
      label305:
      j = m;
      this.jjtree.popNode();
      i = k;
      break label182;
      label321:
      j = i;
      if ((localObject instanceof ParseException))
      {
        j = i;
        throw ((ParseException)localObject);
      }
      j = i;
      throw ((Error)localObject);
      label347:
      switch (i)
      {
      }
      break;
      label387:
      switch (i)
      {
      }
      break label97;
      label411:
      switch (i)
      {
      }
    }
  }
  
  void jjtreeCloseNodeScope(Node paramNode)
  {
    ((SimpleNode)paramNode).lastToken = getToken(0);
  }
  
  void jjtreeOpenNodeScope(Node paramNode)
  {
    ((SimpleNode)paramNode).firstToken = getToken(1);
  }
  
  public final void local_part()
    throws ParseException
  {
    ASTlocal_part localASTlocal_part = new ASTlocal_part(10);
    this.jjtree.openNodeScope(localASTlocal_part);
    jjtreeOpenNodeScope(localASTlocal_part);
    try
    {
      if (this.jj_ntk != -1) {
        break label85;
      }
      i = jj_ntk();
    }
    finally
    {
      if (1 == 0) {
        break label83;
      }
      this.jjtree.closeNodeScope(localASTlocal_part, true);
      jjtreeCloseNodeScope(localASTlocal_part);
    }
    this.jj_la1[15] = this.jj_gen;
    jj_consume_token(-1);
    throw new ParseException();
    label83:
    label85:
    int i = this.jj_ntk;
    break label319;
    for (Token localToken = jj_consume_token(14); this.jj_ntk == -1; localToken = jj_consume_token(31))
    {
      label100:
      i = jj_ntk();
      break label351;
      label116:
      this.jj_la1[16] = this.jj_gen;
      if (1 != 0)
      {
        this.jjtree.closeNodeScope(localASTlocal_part, true);
        jjtreeCloseNodeScope(localASTlocal_part);
      }
      return;
    }
    i = this.jj_ntk;
    break label351;
    if (this.jj_ntk == -1)
    {
      i = jj_ntk();
      break label391;
      label180:
      this.jj_la1[17] = this.jj_gen;
    }
    else
    {
      while ((localToken.kind == 31) || (localToken.image.charAt(localToken.image.length() - 1) != '.'))
      {
        throw new ParseException("Words in local part must be separated by '.'");
        i = this.jj_ntk;
        break label391;
        localToken = jj_consume_token(9);
      }
      if (this.jj_ntk == -1)
      {
        i = jj_ntk();
        break label415;
      }
    }
    for (;;)
    {
      this.jj_la1[18] = this.jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
      i = this.jj_ntk;
      break label415;
      localToken = jj_consume_token(14);
      break label100;
      localToken = jj_consume_token(31);
      break label100;
      label319:
      switch (i)
      {
      }
      break;
      label351:
      switch (i)
      {
      }
      break label116;
      label391:
      switch (i)
      {
      }
      break label180;
      label415:
      switch (i)
      {
      }
    }
  }
  
  public final void mailbox()
    throws ParseException
  {
    ASTmailbox localASTmailbox = new ASTmailbox(3);
    int m = 1;
    int k = 1;
    this.jjtree.openNodeScope(localASTmailbox);
    jjtreeOpenNodeScope(localASTmailbox);
    int j = m;
    for (;;)
    {
      try
      {
        if (jj_2_2(Integer.MAX_VALUE))
        {
          j = m;
          addr_spec();
          if (1 != 0)
          {
            this.jjtree.closeNodeScope(localASTmailbox, true);
            jjtreeCloseNodeScope(localASTmailbox);
          }
          return;
        }
        j = m;
        if (this.jj_ntk != -1) {
          break label190;
        }
        j = m;
        i = jj_ntk();
      }
      catch (Throwable localThrowable)
      {
        if (1 == 0) {
          break label221;
        }
        j = m;
        this.jjtree.clearNodeScope(localASTmailbox);
        i = 0;
        j = i;
        if (!(localThrowable instanceof RuntimeException)) {
          break label237;
        }
        j = i;
        throw ((RuntimeException)localThrowable);
      }
      finally
      {
        if (j == 0) {
          continue;
        }
        this.jjtree.closeNodeScope(localASTmailbox, true);
        jjtreeCloseNodeScope(localASTmailbox);
      }
      j = m;
      this.jj_la1[6] = this.jj_gen;
      j = m;
      jj_consume_token(-1);
      j = m;
      throw new ParseException();
      label190:
      j = m;
      int i = this.jj_ntk;
      break label263;
      j = m;
      angle_addr();
      continue;
      j = m;
      name_addr();
      continue;
      label221:
      j = m;
      this.jjtree.popNode();
      i = k;
      continue;
      label237:
      j = i;
      if ((localObject instanceof ParseException))
      {
        j = i;
        throw ((ParseException)localObject);
      }
      j = i;
      throw ((Error)localObject);
      label263:
      switch (i)
      {
      }
    }
  }
  
  public final void name_addr()
    throws ParseException
  {
    ASTname_addr localASTname_addr = new ASTname_addr(4);
    int k = 1;
    int i = 1;
    this.jjtree.openNodeScope(localASTname_addr);
    jjtreeOpenNodeScope(localASTname_addr);
    int j = k;
    for (;;)
    {
      try
      {
        phrase();
        j = k;
        angle_addr();
        if (1 != 0)
        {
          this.jjtree.closeNodeScope(localASTname_addr, true);
          jjtreeCloseNodeScope(localASTname_addr);
        }
        return;
      }
      catch (Throwable localThrowable)
      {
        if (1 != 0)
        {
          j = k;
          this.jjtree.clearNodeScope(localASTname_addr);
          i = 0;
          j = i;
          if (!(localThrowable instanceof RuntimeException)) {
            break;
          }
          j = i;
          throw ((RuntimeException)localThrowable);
        }
      }
      finally
      {
        if (j != 0)
        {
          this.jjtree.closeNodeScope(localASTname_addr, true);
          jjtreeCloseNodeScope(localASTname_addr);
        }
      }
      j = k;
      this.jjtree.popNode();
    }
    j = i;
    if ((localObject instanceof ParseException))
    {
      j = i;
      throw ((ParseException)localObject);
    }
    j = i;
    throw ((Error)localObject);
  }
  
  public ASTaddress parseAddress()
    throws ParseException
  {
    try
    {
      parseAddress0();
      ASTaddress localASTaddress = (ASTaddress)this.jjtree.rootNode();
      return localASTaddress;
    }
    catch (TokenMgrError localTokenMgrError)
    {
      throw new ParseException(localTokenMgrError.getMessage());
    }
  }
  
  public final void parseAddress0()
    throws ParseException
  {
    address();
    jj_consume_token(0);
  }
  
  public ASTaddress_list parseAddressList()
    throws ParseException
  {
    try
    {
      parseAddressList0();
      ASTaddress_list localASTaddress_list = (ASTaddress_list)this.jjtree.rootNode();
      return localASTaddress_list;
    }
    catch (TokenMgrError localTokenMgrError)
    {
      throw new ParseException(localTokenMgrError.getMessage());
    }
  }
  
  public final void parseAddressList0()
    throws ParseException
  {
    address_list();
    jj_consume_token(0);
  }
  
  public final void parseLine()
    throws ParseException
  {
    address_list();
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
  
  public ASTmailbox parseMailbox()
    throws ParseException
  {
    try
    {
      parseMailbox0();
      ASTmailbox localASTmailbox = (ASTmailbox)this.jjtree.rootNode();
      return localASTmailbox;
    }
    catch (TokenMgrError localTokenMgrError)
    {
      throw new ParseException(localTokenMgrError.getMessage());
    }
  }
  
  public final void parseMailbox0()
    throws ParseException
  {
    mailbox();
    jj_consume_token(0);
  }
  
  public final void phrase()
    throws ParseException
  {
    ASTphrase localASTphrase = new ASTphrase(8);
    this.jjtree.openNodeScope(localASTphrase);
    jjtreeOpenNodeScope(localASTphrase);
    try
    {
      if (this.jj_ntk != -1) {
        break label85;
      }
      i = jj_ntk();
    }
    finally
    {
      if (1 == 0) {
        break label83;
      }
      this.jjtree.closeNodeScope(localASTphrase, true);
      jjtreeCloseNodeScope(localASTphrase);
    }
    this.jj_la1[13] = this.jj_gen;
    jj_consume_token(-1);
    throw new ParseException();
    label83:
    label85:
    int i = this.jj_ntk;
    break label164;
    jj_consume_token(14);
    label100:
    if (this.jj_ntk == -1) {
      i = jj_ntk();
    }
    for (;;)
    {
      this.jj_la1[14] = this.jj_gen;
      if (1 != 0)
      {
        this.jjtree.closeNodeScope(localASTphrase, true);
        jjtreeCloseNodeScope(localASTphrase);
      }
      return;
      jj_consume_token(31);
      break label100;
      i = this.jj_ntk;
      break label195;
      label164:
      switch (i)
      {
      }
      break;
      label195:
      switch (i)
      {
      }
    }
  }
  
  public final void route()
    throws ParseException
  {
    ASTroute localASTroute = new ASTroute(7);
    int m = 1;
    int k = 1;
    this.jjtree.openNodeScope(localASTroute);
    jjtreeOpenNodeScope(localASTroute);
    int j = m;
    int i;
    try
    {
      jj_consume_token(8);
      j = m;
      domain();
      j = m;
      if (this.jj_ntk == -1)
      {
        j = m;
        i = jj_ntk();
        break label306;
        label70:
        j = m;
        this.jj_la1[11] = this.jj_gen;
        j = m;
        jj_consume_token(4);
        if (1 != 0)
        {
          this.jjtree.closeNodeScope(localASTroute, true);
          jjtreeCloseNodeScope(localASTroute);
        }
      }
      else
      {
        j = m;
        i = this.jj_ntk;
      }
    }
    catch (Throwable localThrowable)
    {
      if (1 == 0) {
        break label264;
      }
      j = m;
      this.jjtree.clearNodeScope(localASTroute);
      i = 0;
      j = i;
      if (!(localThrowable instanceof RuntimeException)) {
        break label280;
      }
      j = i;
      throw ((RuntimeException)localThrowable);
    }
    finally
    {
      if (j == 0) {
        break label250;
      }
      this.jjtree.closeNodeScope(localASTroute, true);
      jjtreeCloseNodeScope(localASTroute);
    }
    j = m;
    jj_consume_token(3);
    j = m;
    if (this.jj_ntk == -1)
    {
      j = m;
      i = jj_ntk();
    }
    for (;;)
    {
      j = m;
      this.jj_la1[12] = this.jj_gen;
      j = m;
      jj_consume_token(8);
      j = m;
      domain();
      break;
      for (;;)
      {
        label250:
        j = m;
        i = this.jj_ntk;
        break;
        label264:
        j = m;
        this.jjtree.popNode();
        i = k;
      }
      label280:
      j = i;
      if ((localObject instanceof ParseException))
      {
        j = i;
        throw ((ParseException)localObject);
      }
      j = i;
      throw ((Error)localObject);
      label306:
      switch (i)
      {
      }
      break label70;
      switch (i)
      {
      }
    }
  }
  
  static final class JJCalls
  {
    int arg;
    Token first;
    int gen;
    JJCalls next;
  }
  
  private static final class LookaheadSuccess
    extends Error
  {}
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\address\parser\AddressListParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */