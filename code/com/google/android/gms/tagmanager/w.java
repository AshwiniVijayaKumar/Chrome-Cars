package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class w
  extends dg
{
  private static final String ID = a.aq.toString();
  private static final String UN = b.bO.toString();
  private static final String VALUE = b.eH.toString();
  private final DataLayer TN;
  
  public w(DataLayer paramDataLayer)
  {
    super(ID, new String[] { VALUE });
    this.TN = paramDataLayer;
  }
  
  private void a(d.a parama)
  {
    if ((parama == null) || (parama == di.ko())) {}
    do
    {
      return;
      parama = di.j(parama);
    } while (parama == di.kt());
    this.TN.bg(parama);
  }
  
  private void b(d.a parama)
  {
    if ((parama == null) || (parama == di.ko())) {}
    for (;;)
    {
      return;
      parama = di.o(parama);
      if ((parama instanceof List))
      {
        parama = ((List)parama).iterator();
        while (parama.hasNext())
        {
          Object localObject = parama.next();
          if ((localObject instanceof Map))
          {
            localObject = (Map)localObject;
            this.TN.push((Map)localObject);
          }
        }
      }
    }
  }
  
  public void w(Map<String, d.a> paramMap)
  {
    b((d.a)paramMap.get(VALUE));
    a((d.a)paramMap.get(UN));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\w.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */