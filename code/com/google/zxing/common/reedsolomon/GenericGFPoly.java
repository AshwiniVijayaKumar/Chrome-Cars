package com.google.zxing.common.reedsolomon;

final class GenericGFPoly
{
  private final int[] coefficients;
  private final GenericGF field;
  
  GenericGFPoly(GenericGF paramGenericGF, int[] paramArrayOfInt)
  {
    if (paramArrayOfInt.length == 0) {
      throw new IllegalArgumentException();
    }
    this.field = paramGenericGF;
    int j = paramArrayOfInt.length;
    if ((j > 1) && (paramArrayOfInt[0] == 0))
    {
      int i = 1;
      for (;;)
      {
        if ((i >= j) || (paramArrayOfInt[i] != 0))
        {
          if (i != j) {
            break;
          }
          this.coefficients = paramGenericGF.getZero().coefficients;
          return;
        }
        i += 1;
      }
      this.coefficients = new int[j - i];
      System.arraycopy(paramArrayOfInt, i, this.coefficients, 0, this.coefficients.length);
      return;
    }
    this.coefficients = paramArrayOfInt;
  }
  
  GenericGFPoly addOrSubtract(GenericGFPoly paramGenericGFPoly)
  {
    if (!this.field.equals(paramGenericGFPoly.field)) {
      throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
    }
    if (isZero()) {
      return paramGenericGFPoly;
    }
    if (paramGenericGFPoly.isZero()) {
      return this;
    }
    int[] arrayOfInt1 = this.coefficients;
    int[] arrayOfInt3 = paramGenericGFPoly.coefficients;
    int[] arrayOfInt2 = arrayOfInt3;
    paramGenericGFPoly = arrayOfInt1;
    if (arrayOfInt1.length > arrayOfInt3.length)
    {
      paramGenericGFPoly = arrayOfInt3;
      arrayOfInt2 = arrayOfInt1;
    }
    arrayOfInt1 = new int[arrayOfInt2.length];
    int j = arrayOfInt2.length - paramGenericGFPoly.length;
    System.arraycopy(arrayOfInt2, 0, arrayOfInt1, 0, j);
    int i = j;
    for (;;)
    {
      if (i >= arrayOfInt2.length) {
        return new GenericGFPoly(this.field, arrayOfInt1);
      }
      arrayOfInt1[i] = GenericGF.addOrSubtract(paramGenericGFPoly[(i - j)], arrayOfInt2[i]);
      i += 1;
    }
  }
  
  GenericGFPoly[] divide(GenericGFPoly paramGenericGFPoly)
  {
    if (!this.field.equals(paramGenericGFPoly.field)) {
      throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
    }
    if (paramGenericGFPoly.isZero()) {
      throw new IllegalArgumentException("Divide by 0");
    }
    GenericGFPoly localGenericGFPoly2 = this.field.getZero();
    GenericGFPoly localGenericGFPoly1 = this;
    int i = paramGenericGFPoly.getCoefficient(paramGenericGFPoly.getDegree());
    i = this.field.inverse(i);
    for (;;)
    {
      if ((localGenericGFPoly1.getDegree() < paramGenericGFPoly.getDegree()) || (localGenericGFPoly1.isZero())) {
        return new GenericGFPoly[] { localGenericGFPoly2, localGenericGFPoly1 };
      }
      int j = localGenericGFPoly1.getDegree() - paramGenericGFPoly.getDegree();
      int k = this.field.multiply(localGenericGFPoly1.getCoefficient(localGenericGFPoly1.getDegree()), i);
      GenericGFPoly localGenericGFPoly3 = paramGenericGFPoly.multiplyByMonomial(j, k);
      localGenericGFPoly2 = localGenericGFPoly2.addOrSubtract(this.field.buildMonomial(j, k));
      localGenericGFPoly1 = localGenericGFPoly1.addOrSubtract(localGenericGFPoly3);
    }
  }
  
  int evaluateAt(int paramInt)
  {
    int j = 0;
    int i;
    if (paramInt == 0)
    {
      i = getCoefficient(0);
      return i;
    }
    int m = this.coefficients.length;
    if (paramInt == 1)
    {
      paramInt = 0;
      int[] arrayOfInt = this.coefficients;
      k = arrayOfInt.length;
      for (;;)
      {
        i = paramInt;
        if (j >= k) {
          break;
        }
        paramInt = GenericGF.addOrSubtract(paramInt, arrayOfInt[j]);
        j += 1;
      }
    }
    j = this.coefficients[0];
    int k = 1;
    for (;;)
    {
      i = j;
      if (k >= m) {
        break;
      }
      j = GenericGF.addOrSubtract(this.field.multiply(paramInt, j), this.coefficients[k]);
      k += 1;
    }
  }
  
  int getCoefficient(int paramInt)
  {
    return this.coefficients[(this.coefficients.length - 1 - paramInt)];
  }
  
  int[] getCoefficients()
  {
    return this.coefficients;
  }
  
  int getDegree()
  {
    return this.coefficients.length - 1;
  }
  
  boolean isZero()
  {
    boolean bool = false;
    if (this.coefficients[0] == 0) {
      bool = true;
    }
    return bool;
  }
  
  GenericGFPoly multiply(int paramInt)
  {
    if (paramInt == 0) {
      localObject = this.field.getZero();
    }
    do
    {
      return (GenericGFPoly)localObject;
      localObject = this;
    } while (paramInt == 1);
    int j = this.coefficients.length;
    Object localObject = new int[j];
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return new GenericGFPoly(this.field, (int[])localObject);
      }
      localObject[i] = this.field.multiply(this.coefficients[i], paramInt);
      i += 1;
    }
  }
  
  GenericGFPoly multiply(GenericGFPoly paramGenericGFPoly)
  {
    if (!this.field.equals(paramGenericGFPoly.field)) {
      throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
    }
    if ((isZero()) || (paramGenericGFPoly.isZero())) {
      return this.field.getZero();
    }
    int[] arrayOfInt1 = this.coefficients;
    int k = arrayOfInt1.length;
    paramGenericGFPoly = paramGenericGFPoly.coefficients;
    int m = paramGenericGFPoly.length;
    int[] arrayOfInt2 = new int[k + m - 1];
    int i = 0;
    if (i >= k) {
      return new GenericGFPoly(this.field, arrayOfInt2);
    }
    int n = arrayOfInt1[i];
    int j = 0;
    for (;;)
    {
      if (j >= m)
      {
        i += 1;
        break;
      }
      arrayOfInt2[(i + j)] = GenericGF.addOrSubtract(arrayOfInt2[(i + j)], this.field.multiply(n, paramGenericGFPoly[j]));
      j += 1;
    }
  }
  
  GenericGFPoly multiplyByMonomial(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0) {
      throw new IllegalArgumentException();
    }
    if (paramInt2 == 0) {
      return this.field.getZero();
    }
    int i = this.coefficients.length;
    int[] arrayOfInt = new int[i + paramInt1];
    paramInt1 = 0;
    for (;;)
    {
      if (paramInt1 >= i) {
        return new GenericGFPoly(this.field, arrayOfInt);
      }
      arrayOfInt[paramInt1] = this.field.multiply(this.coefficients[paramInt1], paramInt2);
      paramInt1 += 1;
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(getDegree() * 8);
    int i = getDegree();
    if (i < 0) {
      return localStringBuilder.toString();
    }
    int k = getCoefficient(i);
    int j;
    if (k != 0)
    {
      if (k >= 0) {
        break label110;
      }
      localStringBuilder.append(" - ");
      j = -k;
      label56:
      if ((i == 0) || (j != 1))
      {
        j = this.field.log(j);
        if (j != 0) {
          break label133;
        }
        localStringBuilder.append('1');
      }
      label86:
      if (i != 0)
      {
        if (i != 1) {
          break label167;
        }
        localStringBuilder.append('x');
      }
    }
    for (;;)
    {
      i -= 1;
      break;
      label110:
      j = k;
      if (localStringBuilder.length() <= 0) {
        break label56;
      }
      localStringBuilder.append(" + ");
      j = k;
      break label56;
      label133:
      if (j == 1)
      {
        localStringBuilder.append('a');
        break label86;
      }
      localStringBuilder.append("a^");
      localStringBuilder.append(j);
      break label86;
      label167:
      localStringBuilder.append("x^");
      localStringBuilder.append(i);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\common\reedsolomon\GenericGFPoly.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */