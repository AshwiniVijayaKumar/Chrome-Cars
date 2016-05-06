package com.google.zxing.pdf417.decoder.ec;

import com.google.zxing.ChecksumException;

public final class ErrorCorrection
{
  private final ModulusGF field = ModulusGF.PDF417_GF;
  
  private int[] findErrorLocations(ModulusPoly paramModulusPoly)
    throws ChecksumException
  {
    int m = paramModulusPoly.getDegree();
    int[] arrayOfInt = new int[m];
    int j = 0;
    int i = 1;
    for (;;)
    {
      if ((i >= this.field.getSize()) || (j >= m))
      {
        if (j == m) {
          break;
        }
        throw ChecksumException.getChecksumInstance();
      }
      int k = j;
      if (paramModulusPoly.evaluateAt(i) == 0)
      {
        arrayOfInt[j] = this.field.inverse(i);
        k = j + 1;
      }
      i += 1;
      j = k;
    }
    return arrayOfInt;
  }
  
  private int[] findErrorMagnitudes(ModulusPoly paramModulusPoly1, ModulusPoly paramModulusPoly2, int[] paramArrayOfInt)
  {
    int j = paramModulusPoly2.getDegree();
    int[] arrayOfInt = new int[j];
    int i = 1;
    if (i > j)
    {
      paramModulusPoly2 = new ModulusPoly(this.field, arrayOfInt);
      j = paramArrayOfInt.length;
      arrayOfInt = new int[j];
      i = 0;
    }
    for (;;)
    {
      if (i >= j)
      {
        return arrayOfInt;
        arrayOfInt[(j - i)] = this.field.multiply(i, paramModulusPoly2.getCoefficient(i));
        i += 1;
        break;
      }
      int m = this.field.inverse(paramArrayOfInt[i]);
      int k = this.field.subtract(0, paramModulusPoly1.evaluateAt(m));
      m = this.field.inverse(paramModulusPoly2.evaluateAt(m));
      arrayOfInt[i] = this.field.multiply(k, m);
      i += 1;
    }
  }
  
  private ModulusPoly[] runEuclideanAlgorithm(ModulusPoly paramModulusPoly1, ModulusPoly paramModulusPoly2, int paramInt)
    throws ChecksumException
  {
    ModulusPoly localModulusPoly2 = paramModulusPoly1;
    ModulusPoly localModulusPoly1 = paramModulusPoly2;
    if (paramModulusPoly1.getDegree() < paramModulusPoly2.getDegree())
    {
      localModulusPoly1 = paramModulusPoly1;
      localModulusPoly2 = paramModulusPoly2;
    }
    paramModulusPoly2 = localModulusPoly2;
    ModulusPoly localModulusPoly3 = localModulusPoly1;
    localModulusPoly2 = this.field.getZero();
    paramModulusPoly1 = this.field.getOne();
    localModulusPoly1 = paramModulusPoly2;
    paramModulusPoly2 = localModulusPoly3;
    ModulusPoly localModulusPoly4 = localModulusPoly2;
    localModulusPoly3 = localModulusPoly1;
    if (paramModulusPoly2.getDegree() < paramInt / 2)
    {
      paramInt = paramModulusPoly1.getCoefficient(0);
      if (paramInt == 0) {
        throw ChecksumException.getChecksumInstance();
      }
    }
    else
    {
      localModulusPoly1 = paramModulusPoly2;
      localModulusPoly2 = paramModulusPoly1;
      if (localModulusPoly1.isZero()) {
        throw ChecksumException.getChecksumInstance();
      }
      paramModulusPoly2 = localModulusPoly3;
      paramModulusPoly1 = this.field.getZero();
      int i = localModulusPoly1.getCoefficient(localModulusPoly1.getDegree());
      i = this.field.inverse(i);
      for (;;)
      {
        if ((paramModulusPoly2.getDegree() < localModulusPoly1.getDegree()) || (paramModulusPoly2.isZero()))
        {
          paramModulusPoly1 = paramModulusPoly1.multiply(localModulusPoly2).subtract(localModulusPoly4).negative();
          break;
        }
        int j = paramModulusPoly2.getDegree() - localModulusPoly1.getDegree();
        int k = this.field.multiply(paramModulusPoly2.getCoefficient(paramModulusPoly2.getDegree()), i);
        paramModulusPoly1 = paramModulusPoly1.add(this.field.buildMonomial(j, k));
        paramModulusPoly2 = paramModulusPoly2.subtract(localModulusPoly1.multiplyByMonomial(j, k));
      }
    }
    paramInt = this.field.inverse(paramInt);
    return new ModulusPoly[] { paramModulusPoly1.multiply(paramInt), paramModulusPoly2.multiply(paramInt) };
  }
  
  public void decode(int[] paramArrayOfInt1, int paramInt, int[] paramArrayOfInt2)
    throws ChecksumException
  {
    Object localObject = new ModulusPoly(this.field, paramArrayOfInt1);
    int[] arrayOfInt = new int[paramInt];
    int j = 0;
    int i = paramInt;
    if (i <= 0) {
      if (j != 0)
      {
        localObject = this.field.getOne();
        j = paramArrayOfInt2.length;
        i = 0;
        label51:
        if (i < j) {
          break label169;
        }
        paramArrayOfInt2 = new ModulusPoly(this.field, arrayOfInt);
        localObject = runEuclideanAlgorithm(this.field.buildMonomial(paramInt, 1), paramArrayOfInt2, paramInt);
        paramArrayOfInt2 = localObject[0];
        arrayOfInt = localObject[1];
        localObject = findErrorLocations(paramArrayOfInt2);
        paramArrayOfInt2 = findErrorMagnitudes(arrayOfInt, paramArrayOfInt2, (int[])localObject);
        paramInt = 0;
      }
    }
    for (;;)
    {
      if (paramInt >= localObject.length)
      {
        return;
        int k = ((ModulusPoly)localObject).evaluateAt(this.field.exp(i));
        arrayOfInt[(paramInt - i)] = k;
        if (k != 0) {
          j = 1;
        }
        i -= 1;
        break;
        label169:
        k = paramArrayOfInt2[i];
        k = this.field.exp(paramArrayOfInt1.length - 1 - k);
        localObject = ((ModulusPoly)localObject).multiply(new ModulusPoly(this.field, new int[] { this.field.subtract(0, k), 1 }));
        i += 1;
        break label51;
      }
      i = paramArrayOfInt1.length - 1 - this.field.log(localObject[paramInt]);
      if (i < 0) {
        throw ChecksumException.getChecksumInstance();
      }
      paramArrayOfInt1[i] = this.field.subtract(paramArrayOfInt1[i], paramArrayOfInt2[paramInt]);
      paramInt += 1;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\pdf417\decoder\ec\ErrorCorrection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */