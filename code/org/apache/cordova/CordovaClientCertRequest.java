package org.apache.cordova;

import android.webkit.ClientCertRequest;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class CordovaClientCertRequest
  implements ICordovaClientCertRequest
{
  private final ClientCertRequest request;
  
  public CordovaClientCertRequest(ClientCertRequest paramClientCertRequest)
  {
    this.request = paramClientCertRequest;
  }
  
  public void cancel()
  {
    this.request.cancel();
  }
  
  public String getHost()
  {
    return this.request.getHost();
  }
  
  public String[] getKeyTypes()
  {
    return this.request.getKeyTypes();
  }
  
  public int getPort()
  {
    return this.request.getPort();
  }
  
  public Principal[] getPrincipals()
  {
    return this.request.getPrincipals();
  }
  
  public void ignore()
  {
    this.request.ignore();
  }
  
  public void proceed(PrivateKey paramPrivateKey, X509Certificate[] paramArrayOfX509Certificate)
  {
    this.request.proceed(paramPrivateKey, paramArrayOfX509Certificate);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\CordovaClientCertRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */