package android.net.http;

import java.security.cert.X509Certificate;

public class SslError
{
  public static final int SSL_EXPIRED = 1;
  public static final int SSL_IDMISMATCH = 2;
  public static final int SSL_MAX_ERROR = 4;
  public static final int SSL_NOTYETVALID = 0;
  public static final int SSL_UNTRUSTED = 3;
  SslCertificate mCertificate;
  int mErrors;
  
  public SslError(int paramInt, SslCertificate paramSslCertificate)
  {
    addError(paramInt);
    this.mCertificate = paramSslCertificate;
  }
  
  public SslError(int paramInt, X509Certificate paramX509Certificate)
  {
    addError(paramInt);
    this.mCertificate = new SslCertificate(paramX509Certificate);
  }
  
  public boolean addError(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < 4)) {}
    for (boolean bool = true;; bool = false)
    {
      if (bool) {
        this.mErrors = (1 << paramInt | this.mErrors);
      }
      return bool;
    }
  }
  
  public SslCertificate getCertificate()
  {
    return this.mCertificate;
  }
  
  public int getPrimaryError()
  {
    if (this.mErrors != 0)
    {
      int i = 3;
      while (i >= 0)
      {
        if ((this.mErrors & 1 << i) != 0) {
          return i;
        }
        i -= 1;
      }
    }
    return 0;
  }
  
  public boolean hasError(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < 4)) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      boolean bool2 = bool1;
      if (bool1)
      {
        if ((this.mErrors & 1 << paramInt) == 0) {
          break;
        }
        bool2 = true;
      }
      return bool2;
    }
    return false;
  }
  
  public String toString()
  {
    return "primary error: " + getPrimaryError() + " certificate: " + getCertificate();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\android\net\http\SslError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */