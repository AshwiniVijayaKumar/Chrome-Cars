package com.google.zxing.common.reedsolomon;

public final class ReedSolomonDecoder
{
  private final GenericGF field;
  
  public ReedSolomonDecoder(GenericGF paramGenericGF)
  {
    this.field = paramGenericGF;
  }
  
  private int[] findErrorLocations(GenericGFPoly paramGenericGFPoly)
    throws ReedSolomonException
  {
    int m = paramGenericGFPoly.getDegree();
    if (m == 1)
    {
      arrayOfInt = new int[1];
      arrayOfInt[0] = paramGenericGFPoly.getCoefficient(1);
      paramGenericGFPoly = arrayOfInt;
      return paramGenericGFPoly;
    }
    int[] arrayOfInt = new int[m];
    int j = 0;
    int i = 1;
    for (;;)
    {
      if ((i >= this.field.getSize()) || (j >= m))
      {
        paramGenericGFPoly = arrayOfInt;
        if (j == m) {
          break;
        }
        throw new ReedSolomonException("Error locator degree does not match number of roots");
      }
      int k = j;
      if (paramGenericGFPoly.evaluateAt(i) == 0)
      {
        arrayOfInt[j] = this.field.inverse(i);
        k = j + 1;
      }
      i += 1;
      j = k;
    }
  }
  
  private int[] findErrorMagnitudes(GenericGFPoly paramGenericGFPoly, int[] paramArrayOfInt, boolean paramBoolean)
  {
    int n = paramArrayOfInt.length;
    int[] arrayOfInt = new int[n];
    int i = 0;
    int i1;
    int k;
    int j;
    for (;;)
    {
      if (i >= n) {
        return arrayOfInt;
      }
      i1 = this.field.inverse(paramArrayOfInt[i]);
      k = 1;
      j = 0;
      if (j < n) {
        break;
      }
      arrayOfInt[i] = this.field.multiply(paramGenericGFPoly.evaluateAt(i1), this.field.inverse(k));
      if (paramBoolean) {
        arrayOfInt[i] = this.field.multiply(arrayOfInt[i], i1);
      }
      i += 1;
    }
    int m = k;
    if (i != j)
    {
      m = this.field.multiply(paramArrayOfInt[j], i1);
      if ((m & 0x1) != 0) {
        break label173;
      }
      m |= 0x1;
    }
    for (;;)
    {
      m = this.field.multiply(k, m);
      j += 1;
      k = m;
      break;
      label173:
      m &= 0xFFFFFFFE;
    }
  }
  
  private GenericGFPoly[] runEuclideanAlgorithm(GenericGFPoly paramGenericGFPoly1, GenericGFPoly paramGenericGFPoly2, int paramInt)
    throws ReedSolomonException
  {
    GenericGFPoly localGenericGFPoly2 = paramGenericGFPoly1;
    GenericGFPoly localGenericGFPoly1 = paramGenericGFPoly2;
    if (paramGenericGFPoly1.getDegree() < paramGenericGFPoly2.getDegree())
    {
      localGenericGFPoly1 = paramGenericGFPoly1;
      localGenericGFPoly2 = paramGenericGFPoly2;
    }
    paramGenericGFPoly2 = localGenericGFPoly2;
    GenericGFPoly localGenericGFPoly3 = localGenericGFPoly1;
    localGenericGFPoly2 = this.field.getZero();
    paramGenericGFPoly1 = this.field.getOne();
    localGenericGFPoly1 = paramGenericGFPoly2;
    paramGenericGFPoly2 = localGenericGFPoly3;
    GenericGFPoly localGenericGFPoly4 = localGenericGFPoly2;
    localGenericGFPoly3 = localGenericGFPoly1;
    if (paramGenericGFPoly2.getDegree() < paramInt / 2)
    {
      paramInt = paramGenericGFPoly1.getCoefficient(0);
      if (paramInt == 0) {
        throw new ReedSolomonException("sigmaTilde(0) was zero");
      }
    }
    else
    {
      localGenericGFPoly1 = paramGenericGFPoly2;
      localGenericGFPoly2 = paramGenericGFPoly1;
      if (localGenericGFPoly1.isZero()) {
        throw new ReedSolomonException("r_{i-1} was zero");
      }
      paramGenericGFPoly2 = localGenericGFPoly3;
      paramGenericGFPoly1 = this.field.getZero();
      int i = localGenericGFPoly1.getCoefficient(localGenericGFPoly1.getDegree());
      i = this.field.inverse(i);
      for (;;)
      {
        if ((paramGenericGFPoly2.getDegree() < localGenericGFPoly1.getDegree()) || (paramGenericGFPoly2.isZero()))
        {
          paramGenericGFPoly1 = paramGenericGFPoly1.multiply(localGenericGFPoly2).addOrSubtract(localGenericGFPoly4);
          break;
        }
        int j = paramGenericGFPoly2.getDegree() - localGenericGFPoly1.getDegree();
        int k = this.field.multiply(paramGenericGFPoly2.getCoefficient(paramGenericGFPoly2.getDegree()), i);
        paramGenericGFPoly1 = paramGenericGFPoly1.addOrSubtract(this.field.buildMonomial(j, k));
        paramGenericGFPoly2 = paramGenericGFPoly2.addOrSubtract(localGenericGFPoly1.multiplyByMonomial(j, k));
      }
    }
    paramInt = this.field.inverse(paramInt);
    return new GenericGFPoly[] { paramGenericGFPoly1.multiply(paramInt), paramGenericGFPoly2.multiply(paramInt) };
  }
  
  public void decode(int[] paramArrayOfInt, int paramInt)
    throws ReedSolomonException
  {
    Object localObject1 = new GenericGFPoly(this.field, paramArrayOfInt);
    Object localObject2 = new int[paramInt];
    boolean bool = this.field.equals(GenericGF.DATA_MATRIX_FIELD_256);
    int j = 1;
    int i = 0;
    if (i >= paramInt) {
      if (j == 0) {
        break label110;
      }
    }
    for (;;)
    {
      return;
      GenericGF localGenericGF = this.field;
      if (bool) {}
      for (int k = i + 1;; k = i)
      {
        k = ((GenericGFPoly)localObject1).evaluateAt(localGenericGF.exp(k));
        localObject2[(localObject2.length - 1 - i)] = k;
        if (k != 0) {
          j = 0;
        }
        i += 1;
        break;
      }
      label110:
      localObject1 = new GenericGFPoly(this.field, (int[])localObject2);
      localObject2 = runEuclideanAlgorithm(this.field.buildMonomial(paramInt, 1), (GenericGFPoly)localObject1, paramInt);
      localObject1 = localObject2[0];
      localObject2 = localObject2[1];
      localObject1 = findErrorLocations((GenericGFPoly)localObject1);
      localObject2 = findErrorMagnitudes((GenericGFPoly)localObject2, (int[])localObject1, bool);
      paramInt = 0;
      while (paramInt < localObject1.length)
      {
        i = paramArrayOfInt.length - 1 - this.field.log(localObject1[paramInt]);
        if (i < 0) {
          throw new ReedSolomonException("Bad error location");
        }
        paramArrayOfInt[i] = GenericGF.addOrSubtract(paramArrayOfInt[i], localObject2[paramInt]);
        paramInt += 1;
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\common\reedsolomon\ReedSolomonDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */