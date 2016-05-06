package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class ch
  extends aj
{
  private static final String ID = a.ap.toString();
  private static final String Wc = b.bt.toString();
  private static final String Wd = b.bu.toString();
  private static final String We = b.cQ.toString();
  private static final String Wf = b.cK.toString();
  
  public ch()
  {
    super(ID, new String[] { Wc, Wd });
  }
  
  public boolean iy()
  {
    return true;
  }
  
  public d.a u(Map<String, d.a> paramMap)
  {
    Object localObject = (d.a)paramMap.get(Wc);
    d.a locala = (d.a)paramMap.get(Wd);
    if ((localObject == null) || (localObject == di.ku()) || (locala == null) || (locala == di.ku())) {
      return di.ku();
    }
    int i = 64;
    if (di.n((d.a)paramMap.get(We)).booleanValue()) {
      i = 66;
    }
    paramMap = (d.a)paramMap.get(Wf);
    int j;
    if (paramMap != null)
    {
      paramMap = di.l(paramMap);
      if (paramMap == di.kp()) {
        return di.ku();
      }
      int k = paramMap.intValue();
      j = k;
      if (k < 0) {
        return di.ku();
      }
    }
    else
    {
      j = 1;
    }
    try
    {
      paramMap = di.j((d.a)localObject);
      localObject = di.j(locala);
      locala = null;
      localObject = Pattern.compile((String)localObject, i).matcher(paramMap);
      paramMap = locala;
      if (((Matcher)localObject).find())
      {
        paramMap = locala;
        if (((Matcher)localObject).groupCount() >= j) {
          paramMap = ((Matcher)localObject).group(j);
        }
      }
      if (paramMap == null) {
        return di.ku();
      }
      paramMap = di.r(paramMap);
      return paramMap;
    }
    catch (PatternSyntaxException paramMap) {}
    return di.ku();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\ch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */