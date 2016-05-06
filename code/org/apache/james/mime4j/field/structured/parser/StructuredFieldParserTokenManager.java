package org.apache.james.mime4j.field.structured.parser;

import java.io.IOException;
import java.io.PrintStream;

public class StructuredFieldParserTokenManager
  implements StructuredFieldParserConstants
{
  static final long[] jjbitVec0 = { 0L, 0L, -1L, -1L };
  public static final int[] jjnewLexState;
  static final int[] jjnextStates = new int[0];
  public static final String[] jjstrLiteralImages = { "", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null };
  static final long[] jjtoMore = { 1024L };
  static final long[] jjtoSkip;
  static final long[] jjtoToken;
  public static final String[] lexStateNames = { "DEFAULT", "INCOMMENT", "NESTED_COMMENT", "INQUOTEDSTRING" };
  int commentNest;
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
  private final int[] jjrounds = new int[6];
  private final int[] jjstateSet = new int[12];
  int lengthOfMatch;
  
  static
  {
    jjnewLexState = new int[] { -1, 1, 0, 2, -1, -1, -1, -1, -1, 3, -1, -1, -1, 0, -1, -1, -1, -1 };
    jjtoToken = new long[] { 63489L };
    jjtoSkip = new long[] { 1022L };
  }
  
  public StructuredFieldParserTokenManager(SimpleCharStream paramSimpleCharStream)
  {
    this.input_stream = paramSimpleCharStream;
  }
  
  public StructuredFieldParserTokenManager(SimpleCharStream paramSimpleCharStream, int paramInt)
  {
    this(paramSimpleCharStream);
    SwitchTo(paramInt);
  }
  
  private final void ReInitRounds()
  {
    this.jjround = -2147483647;
    int j;
    for (int i = 6;; i = j)
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
    int i = 0;
    this.jjnewStateCnt = 2;
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
        long l = 1L << this.curChar;
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
          i = 2 - i;
          if (paramInt1 != i) {
            break label496;
          }
          return j;
          if ((0xFFFFFEFAFFFFD9FF & l) != 0L)
          {
            paramInt1 = paramInt2;
            if (paramInt2 > 15) {
              paramInt1 = 15;
            }
            jjCheckNAdd(1);
          }
          else
          {
            paramInt1 = paramInt2;
            if ((0x100002600 & l) != 0L)
            {
              paramInt1 = paramInt2;
              if (paramInt2 > 14) {
                paramInt1 = 14;
              }
              jjCheckNAdd(0);
              continue;
              paramInt1 = paramInt2;
              if ((0x100002600 & l) != 0L)
              {
                paramInt1 = 14;
                jjCheckNAdd(0);
                continue;
                paramInt1 = paramInt2;
                if ((0xFFFFFEFAFFFFD9FF & l) != 0L)
                {
                  paramInt1 = 15;
                  jjCheckNAdd(1);
                }
              }
            }
          }
        }
      }
      if (this.curChar < '')
      {
        k = this.curChar;
        k = paramInt1;
        label309:
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
            break label309;
          }
          break;
          paramInt1 = 15;
          jjCheckNAdd(1);
        }
      }
      int n = this.curChar;
      int i1 = this.curChar;
      k = paramInt1;
      label391:
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
          break label391;
        }
        break;
        paramInt1 = paramInt2;
        if ((jjbitVec0[((n & 0xFF) >> 6)] & 1L << (i1 & 0x3F)) != 0L)
        {
          paramInt1 = paramInt2;
          if (paramInt2 > 15) {
            paramInt1 = 15;
          }
          jjCheckNAdd(1);
        }
      }
      try
      {
        label496:
        this.curChar = this.input_stream.readChar();
      }
      catch (IOException localIOException) {}
    }
    return j;
  }
  
  private final int jjMoveNfa_1(int paramInt1, int paramInt2)
  {
    int i = 0;
    this.jjnewStateCnt = 1;
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
          i = 1 - i;
          if (paramInt1 != i) {
            break label377;
          }
          return j;
          paramInt1 = paramInt2;
          if ((0xFFFFFCFFFFFFFFFF & 1L << n) != 0L) {
            paramInt1 = 4;
          }
        }
      }
      if (this.curChar < '')
      {
        k = this.curChar;
        k = paramInt1;
        label211:
        arrayOfInt = this.jjstateSet;
        m = k - 1;
        switch (arrayOfInt[m])
        {
        }
        for (paramInt1 = paramInt2;; paramInt1 = 4)
        {
          k = m;
          paramInt2 = paramInt1;
          if (m != i) {
            break label211;
          }
          break;
        }
      }
      int n = this.curChar;
      int i1 = this.curChar;
      k = paramInt1;
      label285:
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
          break label285;
        }
        break;
        paramInt1 = paramInt2;
        if ((jjbitVec0[((n & 0xFF) >> 6)] & 1L << (i1 & 0x3F)) != 0L)
        {
          paramInt1 = paramInt2;
          if (paramInt2 > 4) {
            paramInt1 = 4;
          }
        }
      }
      try
      {
        label377:
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
        n = this.curChar;
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
            break label544;
          }
          return k;
          paramInt1 = paramInt2;
          if ((0xFFFFFCFFFFFFFFFF & 1L << n) != 0L)
          {
            paramInt1 = paramInt2;
            if (paramInt2 > 8)
            {
              paramInt1 = 8;
              continue;
              paramInt1 = paramInt2;
              if (paramInt2 > 7) {
                paramInt1 = 7;
              }
              arrayOfInt = this.jjstateSet;
              paramInt2 = this.jjnewStateCnt;
              this.jjnewStateCnt = (paramInt2 + 1);
              arrayOfInt[paramInt2] = 1;
            }
          }
        }
      }
      if (this.curChar < '')
      {
        i = this.curChar;
        i = paramInt1;
        label255:
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
            break label255;
          }
          break;
          i = paramInt2;
          if (paramInt2 > 8) {
            i = 8;
          }
          paramInt1 = i;
          if (this.curChar == '\\')
          {
            jjCheckNAdd(1);
            paramInt1 = i;
            continue;
            paramInt1 = paramInt2;
            if (paramInt2 > 7) {
              paramInt1 = 7;
            }
            jjCheckNAdd(1);
            continue;
            paramInt1 = paramInt2;
            if (paramInt2 > 8) {
              paramInt1 = 8;
            }
          }
        }
      }
      int n = (this.curChar & 0xFF) >> '\006';
      long l = 1L << (this.curChar & 0x3F);
      i = paramInt1;
      label404:
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
          break label404;
        }
        break;
        paramInt1 = paramInt2;
        if ((jjbitVec0[n] & l) != 0L)
        {
          paramInt1 = paramInt2;
          if (paramInt2 > 8)
          {
            paramInt1 = 8;
            continue;
            paramInt1 = paramInt2;
            if ((jjbitVec0[n] & l) != 0L)
            {
              paramInt1 = paramInt2;
              if (paramInt2 > 7) {
                paramInt1 = 7;
              }
              arrayOfInt = this.jjstateSet;
              paramInt2 = this.jjnewStateCnt;
              this.jjnewStateCnt = (paramInt2 + 1);
              arrayOfInt[paramInt2] = 1;
            }
          }
        }
      }
      try
      {
        label544:
        this.curChar = this.input_stream.readChar();
      }
      catch (IOException localIOException) {}
    }
    return k;
  }
  
  private final int jjMoveNfa_3(int paramInt1, int paramInt2)
  {
    int i = 0;
    this.jjnewStateCnt = 6;
    int k = 1;
    this.jjstateSet[0] = paramInt1;
    paramInt1 = Integer.MAX_VALUE;
    int j = paramInt2;
    paramInt2 = k;
    for (;;)
    {
      k = this.jjround + 1;
      this.jjround = k;
      if (k == Integer.MAX_VALUE) {
        ReInitRounds();
      }
      if (this.curChar < '@')
      {
        l = 1L << this.curChar;
        k = paramInt2;
        arrayOfInt = this.jjstateSet;
        m = k - 1;
        switch (arrayOfInt[m])
        {
        default: 
          paramInt2 = paramInt1;
        }
        for (;;)
        {
          k = m;
          paramInt1 = paramInt2;
          if (m != i) {
            break;
          }
          paramInt1 = paramInt2;
          if (paramInt2 != Integer.MAX_VALUE)
          {
            this.jjmatchedKind = paramInt2;
            this.jjmatchedPos = j;
            paramInt1 = Integer.MAX_VALUE;
          }
          j += 1;
          paramInt2 = this.jjnewStateCnt;
          this.jjnewStateCnt = i;
          i = 6 - i;
          if (paramInt2 != i) {
            break label793;
          }
          return j;
          if ((0xFFFFFFFBFFFFDFFF & l) != 0L)
          {
            paramInt2 = paramInt1;
            if (paramInt1 > 11) {
              paramInt2 = 11;
            }
            jjCheckNAdd(2);
          }
          else
          {
            paramInt2 = paramInt1;
            if (this.curChar == '\r')
            {
              arrayOfInt = this.jjstateSet;
              paramInt2 = this.jjnewStateCnt;
              this.jjnewStateCnt = (paramInt2 + 1);
              arrayOfInt[paramInt2] = 3;
              paramInt2 = paramInt1;
              continue;
              paramInt2 = paramInt1;
              if (paramInt1 > 10) {
                paramInt2 = 10;
              }
              arrayOfInt = this.jjstateSet;
              paramInt1 = this.jjnewStateCnt;
              this.jjnewStateCnt = (paramInt1 + 1);
              arrayOfInt[paramInt1] = 1;
              continue;
              paramInt2 = paramInt1;
              if ((0xFFFFFFFBFFFFDFFF & l) != 0L)
              {
                paramInt2 = paramInt1;
                if (paramInt1 > 11) {
                  paramInt2 = 11;
                }
                jjCheckNAdd(2);
                continue;
                paramInt2 = paramInt1;
                if (this.curChar == '\n')
                {
                  paramInt2 = paramInt1;
                  if (paramInt1 > 12) {
                    paramInt2 = 12;
                  }
                  jjCheckNAdd(4);
                  continue;
                  paramInt2 = paramInt1;
                  if ((0x100000200 & l) != 0L)
                  {
                    paramInt2 = paramInt1;
                    if (paramInt1 > 12) {
                      paramInt2 = 12;
                    }
                    jjCheckNAdd(4);
                    continue;
                    paramInt2 = paramInt1;
                    if (this.curChar == '\r')
                    {
                      arrayOfInt = this.jjstateSet;
                      paramInt2 = this.jjnewStateCnt;
                      this.jjnewStateCnt = (paramInt2 + 1);
                      arrayOfInt[paramInt2] = 3;
                      paramInt2 = paramInt1;
                    }
                  }
                }
              }
            }
          }
        }
      }
      if (this.curChar < '')
      {
        l = 1L << (this.curChar & 0x3F);
        k = paramInt2;
        label456:
        arrayOfInt = this.jjstateSet;
        m = k - 1;
        switch (arrayOfInt[m])
        {
        default: 
          paramInt2 = paramInt1;
        }
        for (;;)
        {
          k = m;
          paramInt1 = paramInt2;
          if (m != i) {
            break label456;
          }
          break;
          if ((0xFFFFFFFFEFFFFFFF & l) != 0L)
          {
            paramInt2 = paramInt1;
            if (paramInt1 > 11) {
              paramInt2 = 11;
            }
            jjCheckNAdd(2);
          }
          else
          {
            paramInt2 = paramInt1;
            if (this.curChar == '\\')
            {
              jjCheckNAdd(1);
              paramInt2 = paramInt1;
              continue;
              paramInt2 = paramInt1;
              if (paramInt1 > 10) {
                paramInt2 = 10;
              }
              jjCheckNAdd(1);
              continue;
              paramInt2 = paramInt1;
              if ((0xFFFFFFFFEFFFFFFF & l) != 0L)
              {
                paramInt2 = paramInt1;
                if (paramInt1 > 11) {
                  paramInt2 = 11;
                }
                jjCheckNAdd(2);
              }
            }
          }
        }
      }
      int n = (this.curChar & 0xFF) >> '\006';
      long l = 1L << (this.curChar & 0x3F);
      k = paramInt2;
      label646:
      int[] arrayOfInt = this.jjstateSet;
      int m = k - 1;
      switch (arrayOfInt[m])
      {
      default: 
        paramInt2 = paramInt1;
      }
      for (;;)
      {
        k = m;
        paramInt1 = paramInt2;
        if (m != i) {
          break label646;
        }
        break;
        paramInt2 = paramInt1;
        if ((jjbitVec0[n] & l) != 0L)
        {
          paramInt2 = paramInt1;
          if (paramInt1 > 11) {
            paramInt2 = 11;
          }
          jjCheckNAdd(2);
          continue;
          paramInt2 = paramInt1;
          if ((jjbitVec0[n] & l) != 0L)
          {
            paramInt2 = paramInt1;
            if (paramInt1 > 10) {
              paramInt2 = 10;
            }
            arrayOfInt = this.jjstateSet;
            paramInt1 = this.jjnewStateCnt;
            this.jjnewStateCnt = (paramInt1 + 1);
            arrayOfInt[paramInt1] = 1;
          }
        }
      }
      try
      {
        label793:
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
      return jjMoveNfa_0(2, 0);
    case '"': 
      return jjStopAtPos(0, 9);
    }
    return jjStopAtPos(0, 1);
  }
  
  private final int jjMoveStringLiteralDfa0_1()
  {
    switch (this.curChar)
    {
    default: 
      return jjMoveNfa_1(0, 0);
    case '(': 
      return jjStopAtPos(0, 3);
    }
    return jjStopAtPos(0, 2);
  }
  
  private final int jjMoveStringLiteralDfa0_2()
  {
    switch (this.curChar)
    {
    default: 
      return jjMoveNfa_2(0, 0);
    case '(': 
      return jjStopAtPos(0, 5);
    }
    return jjStopAtPos(0, 6);
  }
  
  private final int jjMoveStringLiteralDfa0_3()
  {
    switch (this.curChar)
    {
    default: 
      return jjMoveNfa_3(0, 0);
    }
    return jjStopAtPos(0, 13);
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
  
  void MoreLexicalActions()
  {
    int i = this.jjimageLen;
    int j = this.jjmatchedPos + 1;
    this.lengthOfMatch = j;
    this.jjimageLen = (i + j);
    switch (this.jjmatchedKind)
    {
    default: 
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
  
  void SkipLexicalActions(Token paramToken)
  {
    switch (this.jjmatchedKind)
    {
    case 4: 
    default: 
    case 3: 
    case 5: 
    case 6: 
      do
      {
        return;
        if (this.image == null) {
          this.image = new StringBuffer();
        }
        paramToken = this.image;
        localSimpleCharStream = this.input_stream;
        i = this.jjimageLen;
        j = this.jjmatchedPos + 1;
        this.lengthOfMatch = j;
        paramToken.append(localSimpleCharStream.GetSuffix(i + j));
        this.commentNest = 1;
        return;
        if (this.image == null) {
          this.image = new StringBuffer();
        }
        paramToken = this.image;
        localSimpleCharStream = this.input_stream;
        i = this.jjimageLen;
        j = this.jjmatchedPos + 1;
        this.lengthOfMatch = j;
        paramToken.append(localSimpleCharStream.GetSuffix(i + j));
        this.commentNest += 1;
        System.out.println("+++ COMMENT NEST=" + this.commentNest);
        return;
        if (this.image == null) {
          this.image = new StringBuffer();
        }
        paramToken = this.image;
        localSimpleCharStream = this.input_stream;
        i = this.jjimageLen;
        j = this.jjmatchedPos + 1;
        this.lengthOfMatch = j;
        paramToken.append(localSimpleCharStream.GetSuffix(i + j));
        this.commentNest -= 1;
        System.out.println("+++ COMMENT NEST=" + this.commentNest);
      } while (this.commentNest != 0);
      SwitchTo(1);
      return;
    }
    if (this.image == null) {
      this.image = new StringBuffer();
    }
    paramToken = this.image;
    SimpleCharStream localSimpleCharStream = this.input_stream;
    int i = this.jjimageLen;
    int j = this.jjmatchedPos + 1;
    this.lengthOfMatch = j;
    paramToken.append(localSimpleCharStream.GetSuffix(i + j));
    this.image.deleteCharAt(this.image.length() - 2);
  }
  
  public void SwitchTo(int paramInt)
  {
    if ((paramInt >= 4) || (paramInt < 0)) {
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
    int j = 0;
    for (;;)
    {
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
            break label365;
          }
          if (this.jjmatchedPos + 1 < i) {
            this.input_stream.backup(i - this.jjmatchedPos - 1);
          }
          if ((jjtoToken[(this.jjmatchedKind >> 6)] & 1L << (this.jjmatchedKind & 0x3F)) == 0L) {
            break label249;
          }
          Token localToken = jjFillToken();
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
        return jjFillToken();
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
      label249:
      if ((jjtoSkip[(this.jjmatchedKind >> 6)] & 1L << (this.jjmatchedKind & 0x3F)) != 0L)
      {
        SkipLexicalActions(null);
        j = i;
        if (jjnewLexState[this.jjmatchedKind] != -1)
        {
          this.curLexState = jjnewLexState[this.jjmatchedKind];
          j = i;
        }
      }
      else
      {
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
    }
    label365:
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
            break label501;
          }
          k += 1;
          i = 0;
          break;
        }
        label501:
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


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\structured\parser\StructuredFieldParserTokenManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */