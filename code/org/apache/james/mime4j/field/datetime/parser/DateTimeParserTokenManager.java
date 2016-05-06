package org.apache.james.mime4j.field.datetime.parser;

import java.io.IOException;
import java.io.PrintStream;

public class DateTimeParserTokenManager
  implements DateTimeParserConstants
{
  static int commentNest;
  static final long[] jjbitVec0 = { 0L, 0L, -1L, -1L };
  public static final int[] jjnewLexState;
  static final int[] jjnextStates = new int[0];
  public static final String[] jjstrLiteralImages = { "", "\r", "\n", ",", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", ":", null, "UT", "GMT", "EST", "EDT", "CST", "CDT", "MST", "MDT", "PST", "PDT", null, null, null, null, null, null, null, null, null, null, null, null, null, null };
  static final long[] jjtoMore = { 69956427317248L };
  static final long[] jjtoSkip;
  static final long[] jjtoSpecial;
  static final long[] jjtoToken;
  public static final String[] lexStateNames = { "DEFAULT", "INCOMMENT", "NESTED_COMMENT" };
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
  private final int[] jjrounds = new int[4];
  private final int[] jjstateSet = new int[8];
  int lengthOfMatch;
  
  static
  {
    jjnewLexState = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 0, -1, 2, -1, -1, -1, -1, -1, -1, -1, -1 };
    jjtoToken = new long[] { 70437463654399L };
    jjtoSkip = new long[] { 343597383680L };
    jjtoSpecial = new long[] { 68719476736L };
  }
  
  public DateTimeParserTokenManager(SimpleCharStream paramSimpleCharStream)
  {
    this.input_stream = paramSimpleCharStream;
  }
  
  public DateTimeParserTokenManager(SimpleCharStream paramSimpleCharStream, int paramInt)
  {
    this(paramSimpleCharStream);
    SwitchTo(paramInt);
  }
  
  private final void ReInitRounds()
  {
    this.jjround = -2147483647;
    int j;
    for (int i = 4;; i = j)
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
    this.jjnewStateCnt = 4;
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
          paramInt1 = i;
          switch (arrayOfInt[m])
          {
          default: 
            paramInt1 = i;
          case 1: 
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
        j = 4 - j;
        if (paramInt2 != j) {
          break label449;
        }
        return k;
        if ((0x3FF000000000000 & l) != 0L)
        {
          paramInt1 = i;
          if (i > 46) {
            paramInt1 = 46;
          }
          jjCheckNAdd(3);
          break;
        }
        if ((0x100000200 & l) != 0L)
        {
          paramInt1 = i;
          if (i > 36) {
            paramInt1 = 36;
          }
          jjCheckNAdd(2);
          break;
        }
        paramInt1 = i;
        if ((0x280000000000 & l) == 0L) {
          break;
        }
        paramInt1 = i;
        if (i <= 24) {
          break;
        }
        paramInt1 = 24;
        break;
        paramInt1 = i;
        if ((0x100000200 & l) == 0L) {
          break;
        }
        paramInt1 = 36;
        jjCheckNAdd(2);
        break;
        paramInt1 = i;
        if ((0x3FF000000000000 & l) == 0L) {
          break;
        }
        paramInt1 = 46;
        jjCheckNAdd(3);
        break;
        if (this.curChar < '')
        {
          int n = this.curChar;
          i = paramInt1;
          label337:
          arrayOfInt = this.jjstateSet;
          m = paramInt2 - 1;
          switch (arrayOfInt[m])
          {
          default: 
            paramInt1 = i;
          }
          for (;;)
          {
            paramInt2 = m;
            i = paramInt1;
            if (m != j) {
              break label337;
            }
            break;
            paramInt1 = i;
            if ((0x7FFFBFE07FFFBFE & 1L << (n & 0x3F)) != 0L) {
              paramInt1 = 35;
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
        label449:
        this.curChar = this.input_stream.readChar();
        paramInt1 = i;
      }
      catch (IOException localIOException) {}
    }
    return k;
  }
  
  private final int jjMoveNfa_1(int paramInt1, int paramInt2)
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
          if (paramInt2 > 41)
          {
            paramInt1 = 41;
            continue;
            paramInt1 = paramInt2;
            if (paramInt2 > 39) {
              paramInt1 = 39;
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
          if (paramInt2 > 41) {
            i = 41;
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
            if (paramInt2 > 39)
            {
              paramInt1 = 39;
              continue;
              paramInt1 = paramInt2;
              if (paramInt2 > 41) {
                paramInt1 = 41;
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
          if (paramInt2 > 41)
          {
            paramInt1 = 41;
            continue;
            paramInt1 = paramInt2;
            if ((jjbitVec0[n] & l) != 0L)
            {
              paramInt1 = paramInt2;
              if (paramInt2 > 39) {
                paramInt1 = 39;
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
          if (paramInt2 > 45)
          {
            paramInt1 = 45;
            continue;
            paramInt1 = paramInt2;
            if (paramInt2 > 42) {
              paramInt1 = 42;
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
          if (paramInt2 > 45) {
            i = 45;
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
            if (paramInt2 > 42)
            {
              paramInt1 = 42;
              continue;
              paramInt1 = paramInt2;
              if (paramInt2 > 45) {
                paramInt1 = 45;
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
          if (paramInt2 > 45)
          {
            paramInt1 = 45;
            continue;
            paramInt1 = paramInt2;
            if ((jjbitVec0[n] & l) != 0L)
            {
              paramInt1 = paramInt2;
              if (paramInt2 > 42) {
                paramInt1 = 42;
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
  
  private final int jjMoveStringLiteralDfa0_0()
  {
    switch (this.curChar)
    {
    default: 
      return jjMoveNfa_0(0, 0);
    case '\n': 
      return jjStopAtPos(0, 2);
    case '\r': 
      return jjStopAtPos(0, 1);
    case '(': 
      return jjStopAtPos(0, 37);
    case ',': 
      return jjStopAtPos(0, 3);
    case ':': 
      return jjStopAtPos(0, 23);
    case 'A': 
      return jjMoveStringLiteralDfa1_0(278528L);
    case 'C': 
      return jjMoveStringLiteralDfa1_0(1610612736L);
    case 'D': 
      return jjMoveStringLiteralDfa1_0(4194304L);
    case 'E': 
      return jjMoveStringLiteralDfa1_0(402653184L);
    case 'F': 
      return jjMoveStringLiteralDfa1_0(4352L);
    case 'G': 
      return jjMoveStringLiteralDfa1_0(67108864L);
    case 'J': 
      return jjMoveStringLiteralDfa1_0(198656L);
    case 'M': 
      return jjMoveStringLiteralDfa1_0(6442491920L);
    case 'N': 
      return jjMoveStringLiteralDfa1_0(2097152L);
    case 'O': 
      return jjMoveStringLiteralDfa1_0(1048576L);
    case 'P': 
      return jjMoveStringLiteralDfa1_0(25769803776L);
    case 'S': 
      return jjMoveStringLiteralDfa1_0(525824L);
    case 'T': 
      return jjMoveStringLiteralDfa1_0(160L);
    case 'U': 
      return jjMoveStringLiteralDfa1_0(33554432L);
    }
    return jjMoveStringLiteralDfa1_0(64L);
  }
  
  private final int jjMoveStringLiteralDfa0_1()
  {
    switch (this.curChar)
    {
    default: 
      return jjMoveNfa_1(0, 0);
    case '(': 
      return jjStopAtPos(0, 40);
    }
    return jjStopAtPos(0, 38);
  }
  
  private final int jjMoveStringLiteralDfa0_2()
  {
    switch (this.curChar)
    {
    default: 
      return jjMoveNfa_2(0, 0);
    case '(': 
      return jjStopAtPos(0, 43);
    }
    return jjStopAtPos(0, 44);
  }
  
  private final int jjMoveStringLiteralDfa1_0(long paramLong)
  {
    do
    {
      try
      {
        this.curChar = this.input_stream.readChar();
        switch (this.curChar)
        {
        default: 
          return jjStartNfa_0(0, paramLong);
        }
      }
      catch (IOException localIOException)
      {
        jjStopStringLiteralDfa_0(0, paramLong);
        return 1;
      }
      return jjMoveStringLiteralDfa2_0(paramLong, 22817013760L);
      return jjMoveStringLiteralDfa2_0(paramLong, 67108864L);
      return jjMoveStringLiteralDfa2_0(paramLong, 11408506880L);
    } while ((0x2000000 & paramLong) == 0L);
    return jjStopAtPos(1, 25);
    return jjMoveStringLiteralDfa2_0(paramLong, 43520L);
    return jjMoveStringLiteralDfa2_0(paramLong, 1048576L);
    return jjMoveStringLiteralDfa2_0(paramLong, 4722752L);
    return jjMoveStringLiteralDfa2_0(paramLong, 128L);
    return jjMoveStringLiteralDfa2_0(paramLong, 2097168L);
    return jjMoveStringLiteralDfa2_0(paramLong, 16384L);
    return jjMoveStringLiteralDfa2_0(paramLong, 256L);
    return jjMoveStringLiteralDfa2_0(paramLong, 459808L);
  }
  
  private final int jjMoveStringLiteralDfa2_0(long paramLong1, long paramLong2)
  {
    paramLong2 &= paramLong1;
    if (paramLong2 == 0L) {
      return jjStartNfa_0(0, paramLong1);
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      do
                      {
                        do
                        {
                          do
                          {
                            do
                            {
                              do
                              {
                                do
                                {
                                  try
                                  {
                                    this.curChar = this.input_stream.readChar();
                                    switch (this.curChar)
                                    {
                                    default: 
                                      return jjStartNfa_0(1, paramLong2);
                                    }
                                  }
                                  catch (IOException localIOException)
                                  {
                                    jjStopStringLiteralDfa_0(1, paramLong2);
                                    return 2;
                                  }
                                  if ((0x4000000 & paramLong2) != 0L) {
                                    return jjStopAtPos(2, 26);
                                  }
                                  if ((0x8000000 & paramLong2) != 0L) {
                                    return jjStopAtPos(2, 27);
                                  }
                                  if ((0x10000000 & paramLong2) != 0L) {
                                    return jjStopAtPos(2, 28);
                                  }
                                  if ((0x20000000 & paramLong2) != 0L) {
                                    return jjStopAtPos(2, 29);
                                  }
                                  if ((0x40000000 & paramLong2) != 0L) {
                                    return jjStopAtPos(2, 30);
                                  }
                                  if ((0x80000000 & paramLong2) != 0L) {
                                    return jjStopAtPos(2, 31);
                                  }
                                  if ((0x100000000 & paramLong2) != 0L) {
                                    return jjStopAtPos(2, 32);
                                  }
                                  if ((0x200000000 & paramLong2) != 0L) {
                                    return jjStopAtPos(2, 33);
                                  }
                                } while ((0x400000000 & paramLong2) == 0L);
                                return jjStopAtPos(2, 34);
                              } while ((0x1000 & paramLong2) == 0L);
                              return jjStopAtPos(2, 12);
                            } while ((0x400000 & paramLong2) == 0L);
                            return jjStopAtPos(2, 22);
                          } while ((0x40 & paramLong2) == 0L);
                          return jjStopAtPos(2, 6);
                        } while ((0x20 & paramLong2) == 0L);
                        return jjStopAtPos(2, 5);
                      } while ((0x40000 & paramLong2) == 0L);
                      return jjStopAtPos(2, 18);
                    } while ((0x100 & paramLong2) == 0L);
                    return jjStopAtPos(2, 8);
                  } while ((0x20000 & paramLong2) == 0L);
                  return jjStopAtPos(2, 17);
                  if ((0x10 & paramLong2) != 0L) {
                    return jjStopAtPos(2, 4);
                  }
                  if ((0x400 & paramLong2) != 0L) {
                    return jjStopAtPos(2, 10);
                  }
                  if ((0x800 & paramLong2) != 0L) {
                    return jjStopAtPos(2, 11);
                  }
                } while ((0x10000 & paramLong2) == 0L);
                return jjStopAtPos(2, 16);
              } while ((0x80000 & paramLong2) == 0L);
              return jjStopAtPos(2, 19);
              if ((0x2000 & paramLong2) != 0L) {
                return jjStopAtPos(2, 13);
              }
            } while ((0x4000 & paramLong2) == 0L);
            return jjStopAtPos(2, 14);
            if ((0x200 & paramLong2) != 0L) {
              return jjStopAtPos(2, 9);
            }
          } while ((0x100000 & paramLong2) == 0L);
          return jjStopAtPos(2, 20);
        } while ((0x80 & paramLong2) == 0L);
        return jjStopAtPos(2, 7);
      } while ((0x200000 & paramLong2) == 0L);
      return jjStopAtPos(2, 21);
    } while ((0x8000 & paramLong2) == 0L);
    return jjStopAtPos(2, 15);
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
  
  private final int jjStopAtPos(int paramInt1, int paramInt2)
  {
    this.jjmatchedKind = paramInt2;
    this.jjmatchedPos = paramInt1;
    return paramInt1 + 1;
  }
  
  private final int jjStopStringLiteralDfa_0(int paramInt, long paramLong)
  {
    switch (paramInt)
    {
    }
    do
    {
      do
      {
        return -1;
      } while ((0x7FE7CF7F0 & paramLong) == 0L);
      this.jjmatchedKind = 35;
      return -1;
    } while (((0x7FE7CF7F0 & paramLong) == 0L) || (this.jjmatchedPos != 0));
    this.jjmatchedKind = 35;
    this.jjmatchedPos = 0;
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
  
  void MoreLexicalActions()
  {
    int i = this.jjimageLen;
    int j = this.jjmatchedPos + 1;
    this.lengthOfMatch = j;
    this.jjimageLen = (i + j);
    switch (this.jjmatchedKind)
    {
    }
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
    SwitchTo(1);
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
    if ((paramInt >= 3) || (paramInt < 0)) {
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + paramInt + ". State unchanged.", 2);
    }
    this.curLexState = paramInt;
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
            break label419;
          }
          if (this.jjmatchedPos + 1 < i) {
            this.input_stream.backup(i - this.jjmatchedPos - 1);
          }
          if ((jjtoToken[(this.jjmatchedKind >> 6)] & 1L << (this.jjmatchedKind & 0x3F)) == 0L) {
            break label242;
          }
          Token localToken = jjFillToken();
          localToken.specialToken = ((Token)localObject2);
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
      label242:
      if ((jjtoSkip[(this.jjmatchedKind >> 6)] & 1L << (this.jjmatchedKind & 0x3F)) != 0L)
      {
        localObject1 = localObject2;
        if ((jjtoSpecial[(this.jjmatchedKind >> 6)] & 1L << (this.jjmatchedKind & 0x3F)) != 0L)
        {
          localObject1 = jjFillToken();
          if (localObject2 != null) {
            break label348;
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
          label348:
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
    label419:
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
            break label555;
          }
          k += 1;
          i = 0;
          break;
        }
        label555:
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


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\datetime\parser\DateTimeParserTokenManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */