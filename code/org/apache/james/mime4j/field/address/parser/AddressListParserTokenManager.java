package org.apache.james.mime4j.field.address.parser;

import java.io.IOException;
import java.io.PrintStream;

public class AddressListParserTokenManager
  implements AddressListParserConstants
{
  static int commentNest;
  static final long[] jjbitVec0 = { 0L, 0L, -1L, -1L };
  public static final int[] jjnewLexState;
  static final int[] jjnextStates = new int[0];
  public static final String[] jjstrLiteralImages = { "", "\r", "\n", ",", ":", ";", "<", ">", "@", ".", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null };
  static final long[] jjtoMore = { 2146140160L };
  static final long[] jjtoSkip;
  static final long[] jjtoSpecial;
  static final long[] jjtoToken;
  public static final String[] lexStateNames = { "DEFAULT", "INDOMAINLITERAL", "INCOMMENT", "NESTED_COMMENT", "INQUOTEDSTRING" };
  protected char curChar;
  int curLexState = 0;
  public PrintStream debugStream = System.out;
  int defaultLexState = 0;
  StringBuffer image;
  protected SimpleCharStream input_stream;
  int jjimageLen;
  int jjmatchedKind;
  int jjmatchedPos;
  int jjnewStateCnt;
  int jjround;
  private final int[] jjrounds = new int[3];
  private final int[] jjstateSet = new int[6];
  int lengthOfMatch;
  
  static
  {
    jjnewLexState = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, 0, 2, 0, -1, 3, -1, -1, -1, -1, -1, 4, -1, -1, 0, -1, -1 };
    jjtoToken = new long[] { 2147763199L };
    jjtoSkip = new long[] { 1049600L };
    jjtoSpecial = new long[] { 1024L };
  }
  
  public AddressListParserTokenManager(SimpleCharStream paramSimpleCharStream)
  {
    this.input_stream = paramSimpleCharStream;
  }
  
  public AddressListParserTokenManager(SimpleCharStream paramSimpleCharStream, int paramInt)
  {
    this(paramSimpleCharStream);
    SwitchTo(paramInt);
  }
  
  private final void ReInitRounds()
  {
    this.jjround = -2147483647;
    int j;
    for (int i = 3;; i = j)
    {
      j = i - 1;
      if (i <= 0) {
        break;
      }
      this.jjrounds[j] = Integer.MIN_VALUE;
    }
  }
  
  private final void jjAddStates(int paramInt1, int paramInt2)
  {
    for (;;)
    {
      int[] arrayOfInt = this.jjstateSet;
      int i = this.jjnewStateCnt;
      this.jjnewStateCnt = (i + 1);
      arrayOfInt[i] = jjnextStates[paramInt1];
      if (paramInt1 == paramInt2) {
        return;
      }
      paramInt1 += 1;
    }
  }
  
  private final void jjCheckNAdd(int paramInt)
  {
    if (this.jjrounds[paramInt] != this.jjround)
    {
      int[] arrayOfInt = this.jjstateSet;
      int i = this.jjnewStateCnt;
      this.jjnewStateCnt = (i + 1);
      arrayOfInt[i] = paramInt;
      this.jjrounds[paramInt] = this.jjround;
    }
  }
  
  private final void jjCheckNAddStates(int paramInt)
  {
    jjCheckNAdd(jjnextStates[paramInt]);
    jjCheckNAdd(jjnextStates[(paramInt + 1)]);
  }
  
  private final void jjCheckNAddStates(int paramInt1, int paramInt2)
  {
    for (;;)
    {
      jjCheckNAdd(jjnextStates[paramInt1]);
      if (paramInt1 == paramInt2) {
        return;
      }
      paramInt1 += 1;
    }
  }
  
  private final void jjCheckNAddTwoStates(int paramInt1, int paramInt2)
  {
    jjCheckNAdd(paramInt1);
    jjCheckNAdd(paramInt2);
  }
  
  private final int jjMoveNfa_0(int paramInt1, int paramInt2)
  {
    int j = 0;
    this.jjnewStateCnt = 3;
    int i = 1;
    this.jjstateSet[0] = paramInt1;
    paramInt1 = Integer.MAX_VALUE;
    int k = paramInt2;
    paramInt2 = i;
    for (;;)
    {
      i = this.jjround + 1;
      this.jjround = i;
      if (i == Integer.MAX_VALUE) {
        ReInitRounds();
      }
      long l;
      int[] arrayOfInt;
      int m;
      if (this.curChar < '@')
      {
        l = 1L << this.curChar;
        i = paramInt1;
        do
        {
          arrayOfInt = this.jjstateSet;
          m = paramInt2 - 1;
          switch (arrayOfInt[m])
          {
          default: 
            paramInt1 = i;
            paramInt2 = m;
            i = paramInt1;
          }
        } while (m != j);
      }
      for (;;)
      {
        i = paramInt1;
        if (paramInt1 != Integer.MAX_VALUE)
        {
          this.jjmatchedKind = paramInt1;
          this.jjmatchedPos = k;
          i = Integer.MAX_VALUE;
        }
        k += 1;
        paramInt2 = this.jjnewStateCnt;
        this.jjnewStateCnt = j;
        j = 3 - j;
        if (paramInt2 != j) {
          break label444;
        }
        return k;
        if ((0xA3FFACFA00000000 & l) != 0L)
        {
          paramInt1 = i;
          if (i > 14) {
            paramInt1 = 14;
          }
          jjCheckNAdd(2);
          break;
        }
        paramInt1 = i;
        if ((0x100000200 & l) == 0L) {
          break;
        }
        paramInt1 = i;
        if (i > 10) {
          paramInt1 = 10;
        }
        jjCheckNAdd(0);
        break;
        paramInt1 = i;
        if ((0x100000200 & l) == 0L) {
          break;
        }
        paramInt1 = 10;
        jjCheckNAdd(0);
        break;
        paramInt1 = i;
        if ((0xA3FFECFA00000000 & l) == 0L) {
          break;
        }
        paramInt1 = i;
        if (i > 14) {
          paramInt1 = 14;
        }
        jjCheckNAdd(2);
        break;
        if (this.curChar < '')
        {
          int n = this.curChar;
          i = paramInt2;
          label312:
          arrayOfInt = this.jjstateSet;
          m = i - 1;
          switch (arrayOfInt[m])
          {
          default: 
            paramInt2 = paramInt1;
          }
          for (;;)
          {
            i = m;
            paramInt1 = paramInt2;
            if (m != j) {
              break label312;
            }
            paramInt1 = paramInt2;
            break;
            paramInt2 = paramInt1;
            if ((0x7FFFFFFFC7FFFFFE & 1L << (n & 0x3F)) != 0L)
            {
              paramInt2 = paramInt1;
              if (paramInt1 > 14) {
                paramInt2 = 14;
              }
              jjCheckNAdd(2);
            }
          }
        }
        i = this.curChar;
        i = this.curChar;
        do
        {
          arrayOfInt = this.jjstateSet;
          i = paramInt2 - 1;
          paramInt2 = arrayOfInt[i];
          paramInt2 = i;
        } while (i != j);
      }
      try
      {
        label444:
        this.curChar = this.input_stream.readChar();
        paramInt1 = i;
      }
      catch (IOException localIOException) {}
    }
    return k;
  }
  
  private final int jjMoveNfa_1(int paramInt1, int paramInt2)
  {
    int i = 0;
    this.jjnewStateCnt = 3;
    int k = 1;
    this.jjstateSet[0] = paramInt1;
    paramInt1 = Integer.MAX_VALUE;
    int j = paramInt2;
    paramInt2 = paramInt1;
    paramInt1 = k;
    for (;;)
    {
      k = this.jjround + 1;
      this.jjround = k;
      if (k == Integer.MAX_VALUE) {
        ReInitRounds();
      }
      if (this.curChar < '@')
      {
        k = this.curChar;
        k = paramInt1;
        arrayOfInt = this.jjstateSet;
        m = k - 1;
        switch (arrayOfInt[m])
        {
        default: 
          paramInt1 = paramInt2;
        }
        for (;;)
        {
          k = m;
          paramInt2 = paramInt1;
          if (m != i) {
            break;
          }
          paramInt2 = paramInt1;
          if (paramInt1 != Integer.MAX_VALUE)
          {
            this.jjmatchedKind = paramInt1;
            this.jjmatchedPos = j;
            paramInt2 = Integer.MAX_VALUE;
          }
          j += 1;
          paramInt1 = this.jjnewStateCnt;
          this.jjnewStateCnt = i;
          i = 3 - i;
          if (paramInt1 != i) {
            break label537;
          }
          return j;
          paramInt1 = paramInt2;
          if (paramInt2 > 17)
          {
            paramInt1 = 17;
            continue;
            paramInt1 = paramInt2;
            if (paramInt2 > 16) {
              paramInt1 = 16;
            }
          }
        }
      }
      if (this.curChar < '')
      {
        l = 1L << (this.curChar & 0x3F);
        k = paramInt1;
        label228:
        arrayOfInt = this.jjstateSet;
        m = k - 1;
        switch (arrayOfInt[m])
        {
        default: 
          paramInt1 = paramInt2;
        }
        for (;;)
        {
          k = m;
          paramInt2 = paramInt1;
          if (m != i) {
            break label228;
          }
          break;
          if ((0xFFFFFFFFC7FFFFFF & l) != 0L)
          {
            paramInt1 = paramInt2;
            if (paramInt2 > 17) {
              paramInt1 = 17;
            }
          }
          else
          {
            paramInt1 = paramInt2;
            if (this.curChar == '\\')
            {
              arrayOfInt = this.jjstateSet;
              paramInt1 = this.jjnewStateCnt;
              this.jjnewStateCnt = (paramInt1 + 1);
              arrayOfInt[paramInt1] = 1;
              paramInt1 = paramInt2;
              continue;
              paramInt1 = paramInt2;
              if (paramInt2 > 16)
              {
                paramInt1 = 16;
                continue;
                paramInt1 = paramInt2;
                if ((0xFFFFFFFFC7FFFFFF & l) != 0L)
                {
                  paramInt1 = paramInt2;
                  if (paramInt2 > 17) {
                    paramInt1 = 17;
                  }
                }
              }
            }
          }
        }
      }
      int n = (this.curChar & 0xFF) >> '\006';
      long l = 1L << (this.curChar & 0x3F);
      k = paramInt1;
      label421:
      int[] arrayOfInt = this.jjstateSet;
      int m = k - 1;
      switch (arrayOfInt[m])
      {
      default: 
        paramInt1 = paramInt2;
      }
      for (;;)
      {
        k = m;
        paramInt2 = paramInt1;
        if (m != i) {
          break label421;
        }
        break;
        paramInt1 = paramInt2;
        if ((jjbitVec0[n] & l) != 0L)
        {
          paramInt1 = paramInt2;
          if (paramInt2 > 17)
          {
            paramInt1 = 17;
            continue;
            paramInt1 = paramInt2;
            if ((jjbitVec0[n] & l) != 0L)
            {
              paramInt1 = paramInt2;
              if (paramInt2 > 16) {
                paramInt1 = 16;
              }
            }
          }
        }
      }
      try
      {
        label537:
        this.curChar = this.input_stream.readChar();
      }
      catch (IOException localIOException) {}
    }
    return j;
  }
  
  private final int jjMoveNfa_2(int paramInt1, int paramInt2)
  {
    int j = 0;
    this.jjnewStateCnt = 3;
    int i = 1;
    this.jjstateSet[0] = paramInt1;
    paramInt1 = Integer.MAX_VALUE;
    int k = paramInt2;
    paramInt2 = paramInt1;
    paramInt1 = i;
    for (;;)
    {
      i = this.jjround + 1;
      this.jjround = i;
      if (i == Integer.MAX_VALUE) {
        ReInitRounds();
      }
      if (this.curChar < '@')
      {
        i = this.curChar;
        i = paramInt1;
        arrayOfInt = this.jjstateSet;
        m = i - 1;
        switch (arrayOfInt[m])
        {
        default: 
          paramInt1 = paramInt2;
        }
        for (;;)
        {
          i = m;
          paramInt2 = paramInt1;
          if (m != j) {
            break;
          }
          paramInt2 = paramInt1;
          if (paramInt1 != Integer.MAX_VALUE)
          {
            this.jjmatchedKind = paramInt1;
            this.jjmatchedPos = k;
            paramInt2 = Integer.MAX_VALUE;
          }
          k += 1;
          paramInt1 = this.jjnewStateCnt;
          this.jjnewStateCnt = j;
          j = 3 - j;
          if (paramInt1 != j) {
            break label497;
          }
          return k;
          paramInt1 = paramInt2;
          if (paramInt2 > 23)
          {
            paramInt1 = 23;
            continue;
            paramInt1 = paramInt2;
            if (paramInt2 > 21) {
              paramInt1 = 21;
            }
          }
        }
      }
      if (this.curChar < '')
      {
        i = this.curChar;
        i = paramInt1;
        label217:
        arrayOfInt = this.jjstateSet;
        m = i - 1;
        switch (arrayOfInt[m])
        {
        default: 
          paramInt1 = paramInt2;
        }
        for (;;)
        {
          i = m;
          paramInt2 = paramInt1;
          if (m != j) {
            break label217;
          }
          break;
          i = paramInt2;
          if (paramInt2 > 23) {
            i = 23;
          }
          paramInt1 = i;
          if (this.curChar == '\\')
          {
            arrayOfInt = this.jjstateSet;
            paramInt1 = this.jjnewStateCnt;
            this.jjnewStateCnt = (paramInt1 + 1);
            arrayOfInt[paramInt1] = 1;
            paramInt1 = i;
            continue;
            paramInt1 = paramInt2;
            if (paramInt2 > 21)
            {
              paramInt1 = 21;
              continue;
              paramInt1 = paramInt2;
              if (paramInt2 > 23) {
                paramInt1 = 23;
              }
            }
          }
        }
      }
      int n = (this.curChar & 0xFF) >> '\006';
      long l = 1L << (this.curChar & 0x3F);
      i = paramInt1;
      label381:
      int[] arrayOfInt = this.jjstateSet;
      int m = i - 1;
      switch (arrayOfInt[m])
      {
      default: 
        paramInt1 = paramInt2;
      }
      for (;;)
      {
        i = m;
        paramInt2 = paramInt1;
        if (m != j) {
          break label381;
        }
        break;
        paramInt1 = paramInt2;
        if ((jjbitVec0[n] & l) != 0L)
        {
          paramInt1 = paramInt2;
          if (paramInt2 > 23)
          {
            paramInt1 = 23;
            continue;
            paramInt1 = paramInt2;
            if ((jjbitVec0[n] & l) != 0L)
            {
              paramInt1 = paramInt2;
              if (paramInt2 > 21) {
                paramInt1 = 21;
              }
            }
          }
        }
      }
      try
      {
        label497:
        this.curChar = this.input_stream.readChar();
      }
      catch (IOException localIOException) {}
    }
    return k;
  }
  
  private final int jjMoveNfa_3(int paramInt1, int paramInt2)
  {
    int j = 0;
    this.jjnewStateCnt = 3;
    int i = 1;
    this.jjstateSet[0] = paramInt1;
    paramInt1 = Integer.MAX_VALUE;
    int k = paramInt2;
    paramInt2 = paramInt1;
    paramInt1 = i;
    for (;;)
    {
      i = this.jjround + 1;
      this.jjround = i;
      if (i == Integer.MAX_VALUE) {
        ReInitRounds();
      }
      if (this.curChar < '@')
      {
        i = this.curChar;
        i = paramInt1;
        arrayOfInt = this.jjstateSet;
        m = i - 1;
        switch (arrayOfInt[m])
        {
        default: 
          paramInt1 = paramInt2;
        }
        for (;;)
        {
          i = m;
          paramInt2 = paramInt1;
          if (m != j) {
            break;
          }
          paramInt2 = paramInt1;
          if (paramInt1 != Integer.MAX_VALUE)
          {
            this.jjmatchedKind = paramInt1;
            this.jjmatchedPos = k;
            paramInt2 = Integer.MAX_VALUE;
          }
          k += 1;
          paramInt1 = this.jjnewStateCnt;
          this.jjnewStateCnt = j;
          j = 3 - j;
          if (paramInt1 != j) {
            break label497;
          }
          return k;
          paramInt1 = paramInt2;
          if (paramInt2 > 27)
          {
            paramInt1 = 27;
            continue;
            paramInt1 = paramInt2;
            if (paramInt2 > 24) {
              paramInt1 = 24;
            }
          }
        }
      }
      if (this.curChar < '')
      {
        i = this.curChar;
        i = paramInt1;
        label217:
        arrayOfInt = this.jjstateSet;
        m = i - 1;
        switch (arrayOfInt[m])
        {
        default: 
          paramInt1 = paramInt2;
        }
        for (;;)
        {
          i = m;
          paramInt2 = paramInt1;
          if (m != j) {
            break label217;
          }
          break;
          i = paramInt2;
          if (paramInt2 > 27) {
            i = 27;
          }
          paramInt1 = i;
          if (this.curChar == '\\')
          {
            arrayOfInt = this.jjstateSet;
            paramInt1 = this.jjnewStateCnt;
            this.jjnewStateCnt = (paramInt1 + 1);
            arrayOfInt[paramInt1] = 1;
            paramInt1 = i;
            continue;
            paramInt1 = paramInt2;
            if (paramInt2 > 24)
            {
              paramInt1 = 24;
              continue;
              paramInt1 = paramInt2;
              if (paramInt2 > 27) {
                paramInt1 = 27;
              }
            }
          }
        }
      }
      int n = (this.curChar & 0xFF) >> '\006';
      long l = 1L << (this.curChar & 0x3F);
      i = paramInt1;
      label381:
      int[] arrayOfInt = this.jjstateSet;
      int m = i - 1;
      switch (arrayOfInt[m])
      {
      default: 
        paramInt1 = paramInt2;
      }
      for (;;)
      {
        i = m;
        paramInt2 = paramInt1;
        if (m != j) {
          break label381;
        }
        break;
        paramInt1 = paramInt2;
        if ((jjbitVec0[n] & l) != 0L)
        {
          paramInt1 = paramInt2;
          if (paramInt2 > 27)
          {
            paramInt1 = 27;
            continue;
            paramInt1 = paramInt2;
            if ((jjbitVec0[n] & l) != 0L)
            {
              paramInt1 = paramInt2;
              if (paramInt2 > 24) {
                paramInt1 = 24;
              }
            }
          }
        }
      }
      try
      {
        label497:
        this.curChar = this.input_stream.readChar();
      }
      catch (IOException localIOException) {}
    }
    return k;
  }
  
  private final int jjMoveNfa_4(int paramInt1, int paramInt2)
  {
    int i = 0;
    this.jjnewStateCnt = 3;
    int k = 1;
    this.jjstateSet[0] = paramInt1;
    paramInt1 = Integer.MAX_VALUE;
    int j = paramInt2;
    paramInt2 = paramInt1;
    paramInt1 = k;
    for (;;)
    {
      k = this.jjround + 1;
      this.jjround = k;
      if (k == Integer.MAX_VALUE) {
        ReInitRounds();
      }
      if (this.curChar < '@')
      {
        n = this.curChar;
        k = paramInt1;
        arrayOfInt = this.jjstateSet;
        m = k - 1;
        switch (arrayOfInt[m])
        {
        default: 
          paramInt1 = paramInt2;
        }
        for (;;)
        {
          k = m;
          paramInt2 = paramInt1;
          if (m != i) {
            break;
          }
          paramInt2 = paramInt1;
          if (paramInt1 != Integer.MAX_VALUE)
          {
            this.jjmatchedKind = paramInt1;
            this.jjmatchedPos = j;
            paramInt2 = Integer.MAX_VALUE;
          }
          j += 1;
          paramInt1 = this.jjnewStateCnt;
          this.jjnewStateCnt = i;
          i = 3 - i;
          if (paramInt1 != i) {
            break label582;
          }
          return j;
          paramInt1 = paramInt2;
          if ((0xFFFFFFFBFFFFFFFF & 1L << n) != 0L)
          {
            paramInt1 = paramInt2;
            if (paramInt2 > 30) {
              paramInt1 = 30;
            }
            jjCheckNAdd(2);
            continue;
            paramInt1 = paramInt2;
            if (paramInt2 > 29) {
              paramInt1 = 29;
            }
          }
        }
      }
      if (this.curChar < '')
      {
        l = 1L << (this.curChar & 0x3F);
        k = paramInt1;
        label252:
        arrayOfInt = this.jjstateSet;
        m = k - 1;
        switch (arrayOfInt[m])
        {
        default: 
          paramInt1 = paramInt2;
        }
        for (;;)
        {
          k = m;
          paramInt2 = paramInt1;
          if (m != i) {
            break label252;
          }
          break;
          if ((0xFFFFFFFFEFFFFFFF & l) != 0L)
          {
            paramInt1 = paramInt2;
            if (paramInt2 > 30) {
              paramInt1 = 30;
            }
            jjCheckNAdd(2);
          }
          else
          {
            paramInt1 = paramInt2;
            if (this.curChar == '\\')
            {
              arrayOfInt = this.jjstateSet;
              paramInt1 = this.jjnewStateCnt;
              this.jjnewStateCnt = (paramInt1 + 1);
              arrayOfInt[paramInt1] = 1;
              paramInt1 = paramInt2;
              continue;
              paramInt1 = paramInt2;
              if (paramInt2 > 29)
              {
                paramInt1 = 29;
                continue;
                paramInt1 = paramInt2;
                if ((0xFFFFFFFFEFFFFFFF & l) != 0L)
                {
                  paramInt1 = paramInt2;
                  if (paramInt2 > 30) {
                    paramInt1 = 30;
                  }
                  jjCheckNAdd(2);
                }
              }
            }
          }
        }
      }
      int n = (this.curChar & 0xFF) >> '\006';
      long l = 1L << (this.curChar & 0x3F);
      k = paramInt1;
      label455:
      int[] arrayOfInt = this.jjstateSet;
      int m = k - 1;
      switch (arrayOfInt[m])
      {
      default: 
        paramInt1 = paramInt2;
      }
      for (;;)
      {
        k = m;
        paramInt2 = paramInt1;
        if (m != i) {
          break label455;
        }
        break;
        paramInt1 = paramInt2;
        if ((jjbitVec0[n] & l) != 0L)
        {
          paramInt1 = paramInt2;
          if (paramInt2 > 30) {
            paramInt1 = 30;
          }
          jjCheckNAdd(2);
          continue;
          paramInt1 = paramInt2;
          if ((jjbitVec0[n] & l) != 0L)
          {
            paramInt1 = paramInt2;
            if (paramInt2 > 29) {
              paramInt1 = 29;
            }
          }
        }
      }
      try
      {
        label582:
        this.curChar = this.input_stream.readChar();
      }
      catch (IOException localIOException) {}
    }
    return j;
  }
  
  private final int jjMoveStringLiteralDfa0_0()
  {
    switch (this.curChar)
    {
    default: 
      return jjMoveNfa_0(1, 0);
    case '\n': 
      return jjStopAtPos(0, 2);
    case '\r': 
      return jjStopAtPos(0, 1);
    case '"': 
      return jjStopAtPos(0, 28);
    case '(': 
      return jjStopAtPos(0, 19);
    case ',': 
      return jjStopAtPos(0, 3);
    case '.': 
      return jjStopAtPos(0, 9);
    case ':': 
      return jjStopAtPos(0, 4);
    case ';': 
      return jjStopAtPos(0, 5);
    case '<': 
      return jjStopAtPos(0, 6);
    case '>': 
      return jjStopAtPos(0, 7);
    case '@': 
      return jjStopAtPos(0, 8);
    }
    return jjStopAtPos(0, 15);
  }
  
  private final int jjMoveStringLiteralDfa0_1()
  {
    switch (this.curChar)
    {
    default: 
      return jjMoveNfa_1(0, 0);
    }
    return jjStopAtPos(0, 18);
  }
  
  private final int jjMoveStringLiteralDfa0_2()
  {
    switch (this.curChar)
    {
    default: 
      return jjMoveNfa_2(0, 0);
    case '(': 
      return jjStopAtPos(0, 22);
    }
    return jjStopAtPos(0, 20);
  }
  
  private final int jjMoveStringLiteralDfa0_3()
  {
    switch (this.curChar)
    {
    default: 
      return jjMoveNfa_3(0, 0);
    case '(': 
      return jjStopAtPos(0, 25);
    }
    return jjStopAtPos(0, 26);
  }
  
  private final int jjMoveStringLiteralDfa0_4()
  {
    switch (this.curChar)
    {
    default: 
      return jjMoveNfa_4(0, 0);
    }
    return jjStopAtPos(0, 31);
  }
  
  private final int jjStartNfaWithStates_0(int paramInt1, int paramInt2, int paramInt3)
  {
    this.jjmatchedKind = paramInt2;
    this.jjmatchedPos = paramInt1;
    try
    {
      this.curChar = this.input_stream.readChar();
      return jjMoveNfa_0(paramInt3, paramInt1 + 1);
    }
    catch (IOException localIOException) {}
    return paramInt1 + 1;
  }
  
  private final int jjStartNfaWithStates_1(int paramInt1, int paramInt2, int paramInt3)
  {
    this.jjmatchedKind = paramInt2;
    this.jjmatchedPos = paramInt1;
    try
    {
      this.curChar = this.input_stream.readChar();
      return jjMoveNfa_1(paramInt3, paramInt1 + 1);
    }
    catch (IOException localIOException) {}
    return paramInt1 + 1;
  }
  
  private final int jjStartNfaWithStates_2(int paramInt1, int paramInt2, int paramInt3)
  {
    this.jjmatchedKind = paramInt2;
    this.jjmatchedPos = paramInt1;
    try
    {
      this.curChar = this.input_stream.readChar();
      return jjMoveNfa_2(paramInt3, paramInt1 + 1);
    }
    catch (IOException localIOException) {}
    return paramInt1 + 1;
  }
  
  private final int jjStartNfaWithStates_3(int paramInt1, int paramInt2, int paramInt3)
  {
    this.jjmatchedKind = paramInt2;
    this.jjmatchedPos = paramInt1;
    try
    {
      this.curChar = this.input_stream.readChar();
      return jjMoveNfa_3(paramInt3, paramInt1 + 1);
    }
    catch (IOException localIOException) {}
    return paramInt1 + 1;
  }
  
  private final int jjStartNfaWithStates_4(int paramInt1, int paramInt2, int paramInt3)
  {
    this.jjmatchedKind = paramInt2;
    this.jjmatchedPos = paramInt1;
    try
    {
      this.curChar = this.input_stream.readChar();
      return jjMoveNfa_4(paramInt3, paramInt1 + 1);
    }
    catch (IOException localIOException) {}
    return paramInt1 + 1;
  }
  
  private final int jjStartNfa_0(int paramInt, long paramLong)
  {
    return jjMoveNfa_0(jjStopStringLiteralDfa_0(paramInt, paramLong), paramInt + 1);
  }
  
  private final int jjStartNfa_1(int paramInt, long paramLong)
  {
    return jjMoveNfa_1(jjStopStringLiteralDfa_1(paramInt, paramLong), paramInt + 1);
  }
  
  private final int jjStartNfa_2(int paramInt, long paramLong)
  {
    return jjMoveNfa_2(jjStopStringLiteralDfa_2(paramInt, paramLong), paramInt + 1);
  }
  
  private final int jjStartNfa_3(int paramInt, long paramLong)
  {
    return jjMoveNfa_3(jjStopStringLiteralDfa_3(paramInt, paramLong), paramInt + 1);
  }
  
  private final int jjStartNfa_4(int paramInt, long paramLong)
  {
    return jjMoveNfa_4(jjStopStringLiteralDfa_4(paramInt, paramLong), paramInt + 1);
  }
  
  private final int jjStopAtPos(int paramInt1, int paramInt2)
  {
    this.jjmatchedKind = paramInt2;
    this.jjmatchedPos = paramInt1;
    return paramInt1 + 1;
  }
  
  private final int jjStopStringLiteralDfa_0(int paramInt, long paramLong)
  {
    return -1;
  }
  
  private final int jjStopStringLiteralDfa_1(int paramInt, long paramLong)
  {
    return -1;
  }
  
  private final int jjStopStringLiteralDfa_2(int paramInt, long paramLong)
  {
    return -1;
  }
  
  private final int jjStopStringLiteralDfa_3(int paramInt, long paramLong)
  {
    return -1;
  }
  
  private final int jjStopStringLiteralDfa_4(int paramInt, long paramLong)
  {
    return -1;
  }
  
  void MoreLexicalActions()
  {
    int i = this.jjimageLen;
    int j = this.jjmatchedPos + 1;
    this.lengthOfMatch = j;
    this.jjimageLen = (i + j);
    switch (this.jjmatchedKind)
    {
    case 17: 
    case 18: 
    case 19: 
    case 20: 
    case 23: 
    case 27: 
    default: 
    case 16: 
    case 21: 
    case 22: 
    case 24: 
    case 25: 
    case 26: 
      do
      {
        return;
        if (this.image == null) {
          this.image = new StringBuffer();
        }
        this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
        this.jjimageLen = 0;
        this.image.deleteCharAt(this.image.length() - 2);
        return;
        if (this.image == null) {
          this.image = new StringBuffer();
        }
        this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
        this.jjimageLen = 0;
        this.image.deleteCharAt(this.image.length() - 2);
        return;
        if (this.image == null) {
          this.image = new StringBuffer();
        }
        this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
        this.jjimageLen = 0;
        commentNest = 1;
        return;
        if (this.image == null) {
          this.image = new StringBuffer();
        }
        this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
        this.jjimageLen = 0;
        this.image.deleteCharAt(this.image.length() - 2);
        return;
        if (this.image == null) {
          this.image = new StringBuffer();
        }
        this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
        this.jjimageLen = 0;
        commentNest += 1;
        return;
        if (this.image == null) {
          this.image = new StringBuffer();
        }
        this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
        this.jjimageLen = 0;
        commentNest -= 1;
      } while (commentNest != 0);
      SwitchTo(2);
      return;
    case 28: 
      if (this.image == null) {
        this.image = new StringBuffer();
      }
      this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
      this.jjimageLen = 0;
      this.image.deleteCharAt(this.image.length() - 1);
      return;
    }
    if (this.image == null) {
      this.image = new StringBuffer();
    }
    this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
    this.jjimageLen = 0;
    this.image.deleteCharAt(this.image.length() - 2);
  }
  
  public void ReInit(SimpleCharStream paramSimpleCharStream)
  {
    this.jjnewStateCnt = 0;
    this.jjmatchedPos = 0;
    this.curLexState = this.defaultLexState;
    this.input_stream = paramSimpleCharStream;
    ReInitRounds();
  }
  
  public void ReInit(SimpleCharStream paramSimpleCharStream, int paramInt)
  {
    ReInit(paramSimpleCharStream);
    SwitchTo(paramInt);
  }
  
  public void SwitchTo(int paramInt)
  {
    if ((paramInt >= 5) || (paramInt < 0)) {
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + paramInt + ". State unchanged.", 2);
    }
    this.curLexState = paramInt;
  }
  
  void TokenLexicalActions(Token paramToken)
  {
    switch (this.jjmatchedKind)
    {
    default: 
      return;
    case 18: 
      if (this.image == null) {
        this.image = new StringBuffer();
      }
      localStringBuffer = this.image;
      localSimpleCharStream = this.input_stream;
      i = this.jjimageLen;
      j = this.jjmatchedPos + 1;
      this.lengthOfMatch = j;
      localStringBuffer.append(localSimpleCharStream.GetSuffix(i + j));
      paramToken.image = this.image.toString();
      return;
    }
    if (this.image == null) {
      this.image = new StringBuffer();
    }
    StringBuffer localStringBuffer = this.image;
    SimpleCharStream localSimpleCharStream = this.input_stream;
    int i = this.jjimageLen;
    int j = this.jjmatchedPos + 1;
    this.lengthOfMatch = j;
    localStringBuffer.append(localSimpleCharStream.GetSuffix(i + j));
    paramToken.image = this.image.substring(0, this.image.length() - 1);
  }
  
  public Token getNextToken()
  {
    Object localObject2 = null;
    int j = 0;
    for (;;)
    {
      Object localObject1;
      try
      {
        this.curChar = this.input_stream.BeginToken();
        this.image = null;
        this.jjimageLen = 0;
        i = j;
        switch (this.curLexState)
        {
        default: 
          j = i;
          if (this.jjmatchedKind == Integer.MAX_VALUE) {
            break label471;
          }
          if (this.jjmatchedPos + 1 < i) {
            this.input_stream.backup(i - this.jjmatchedPos - 1);
          }
          if ((jjtoToken[(this.jjmatchedKind >> 6)] & 1L << (this.jjmatchedKind & 0x3F)) == 0L) {
            break label294;
          }
          Token localToken = jjFillToken();
          localToken.specialToken = ((Token)localObject2);
          TokenLexicalActions(localToken);
          if (jjnewLexState[this.jjmatchedKind] != -1) {
            this.curLexState = jjnewLexState[this.jjmatchedKind];
          }
          return localToken;
        }
      }
      catch (IOException localIOException1)
      {
        this.jjmatchedKind = 0;
        localObject1 = jjFillToken();
        ((Token)localObject1).specialToken = ((Token)localObject2);
        return (Token)localObject1;
      }
      this.jjmatchedKind = Integer.MAX_VALUE;
      this.jjmatchedPos = 0;
      i = jjMoveStringLiteralDfa0_0();
      continue;
      this.jjmatchedKind = Integer.MAX_VALUE;
      this.jjmatchedPos = 0;
      i = jjMoveStringLiteralDfa0_1();
      continue;
      this.jjmatchedKind = Integer.MAX_VALUE;
      this.jjmatchedPos = 0;
      i = jjMoveStringLiteralDfa0_2();
      continue;
      this.jjmatchedKind = Integer.MAX_VALUE;
      this.jjmatchedPos = 0;
      i = jjMoveStringLiteralDfa0_3();
      continue;
      this.jjmatchedKind = Integer.MAX_VALUE;
      this.jjmatchedPos = 0;
      i = jjMoveStringLiteralDfa0_4();
      continue;
      label294:
      if ((jjtoSkip[(this.jjmatchedKind >> 6)] & 1L << (this.jjmatchedKind & 0x3F)) != 0L)
      {
        localObject1 = localObject2;
        if ((jjtoSpecial[(this.jjmatchedKind >> 6)] & 1L << (this.jjmatchedKind & 0x3F)) != 0L)
        {
          localObject1 = jjFillToken();
          if (localObject2 != null) {
            break label400;
          }
        }
        for (;;)
        {
          j = i;
          localObject2 = localObject1;
          if (jjnewLexState[this.jjmatchedKind] == -1) {
            break;
          }
          this.curLexState = jjnewLexState[this.jjmatchedKind];
          j = i;
          localObject2 = localObject1;
          break;
          label400:
          ((Token)localObject1).specialToken = ((Token)localObject2);
          ((Token)localObject2).next = ((Token)localObject1);
        }
      }
      MoreLexicalActions();
      if (jjnewLexState[this.jjmatchedKind] != -1) {
        this.curLexState = jjnewLexState[this.jjmatchedKind];
      }
      j = 0;
      i = 0;
      this.jjmatchedKind = Integer.MAX_VALUE;
      try
      {
        this.curChar = this.input_stream.readChar();
      }
      catch (IOException localIOException2) {}
    }
    label471:
    int k = this.input_stream.getEndLine();
    int i = this.input_stream.getEndColumn();
    String str1 = null;
    boolean bool = false;
    try
    {
      this.input_stream.readChar();
      this.input_stream.backup(1);
      if (!bool)
      {
        this.input_stream.backup(1);
        if (j <= 1) {
          str1 = "";
        }
      }
      else
      {
        throw new TokenMgrError(bool, this.curLexState, k, i, str1, this.curChar, 0);
      }
    }
    catch (IOException localIOException3)
    {
      for (;;)
      {
        bool = true;
        if (j <= 1) {}
        for (String str2 = "";; str2 = this.input_stream.GetImage())
        {
          if ((this.curChar != '\n') && (this.curChar != '\r')) {
            break label607;
          }
          k += 1;
          i = 0;
          break;
        }
        label607:
        i += 1;
        continue;
        str2 = this.input_stream.GetImage();
      }
    }
  }
  
  protected Token jjFillToken()
  {
    Token localToken = Token.newToken(this.jjmatchedKind);
    localToken.kind = this.jjmatchedKind;
    String str2 = jjstrLiteralImages[this.jjmatchedKind];
    String str1 = str2;
    if (str2 == null) {
      str1 = this.input_stream.GetImage();
    }
    localToken.image = str1;
    localToken.beginLine = this.input_stream.getBeginLine();
    localToken.beginColumn = this.input_stream.getBeginColumn();
    localToken.endLine = this.input_stream.getEndLine();
    localToken.endColumn = this.input_stream.getEndColumn();
    return localToken;
  }
  
  public void setDebugStream(PrintStream paramPrintStream)
  {
    this.debugStream = paramPrintStream;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\address\parser\AddressListParserTokenManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */