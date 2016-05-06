package com.anjlab.android.iab.v3;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

class BillingCache
  extends BillingBase
{
  private static final String ENTRY_DELIMITER = "#####";
  private static final String LINE_DELIMITER = ">>>>>";
  private static final String VERSION_KEY = ".version";
  private String cacheKey;
  private HashMap<String, PurchaseInfo> data = new HashMap();
  private String version;
  
  public BillingCache(Context paramContext, String paramString)
  {
    super(paramContext);
    this.cacheKey = paramString;
    load();
  }
  
  private void flush()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.data.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      PurchaseInfo localPurchaseInfo = (PurchaseInfo)this.data.get(str);
      localArrayList.add(str + ">>>>>" + localPurchaseInfo.responseData + ">>>>>" + localPurchaseInfo.signature);
    }
    saveString(getPreferencesCacheKey(), TextUtils.join("#####", localArrayList));
    this.version = Long.toString(new Date().getTime());
    saveString(getPreferencesVersionKey(), this.version);
  }
  
  private String getCurrentVersion()
  {
    return loadString(getPreferencesVersionKey(), "0");
  }
  
  private String getPreferencesCacheKey()
  {
    return getPreferencesBaseKey() + this.cacheKey;
  }
  
  private String getPreferencesVersionKey()
  {
    return getPreferencesCacheKey() + ".version";
  }
  
  private void load()
  {
    String[] arrayOfString = loadString(getPreferencesCacheKey(), "").split(Pattern.quote("#####"));
    int j = arrayOfString.length;
    int i = 0;
    if (i < j)
    {
      Object localObject = arrayOfString[i];
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        localObject = ((String)localObject).split(Pattern.quote(">>>>>"));
        if (localObject.length <= 2) {
          break label95;
        }
        this.data.put(localObject[0], new PurchaseInfo(localObject[1], localObject[2]));
      }
      for (;;)
      {
        i += 1;
        break;
        label95:
        if (localObject.length > 1) {
          this.data.put(localObject[0], new PurchaseInfo(localObject[1], null));
        }
      }
    }
    this.version = getCurrentVersion();
  }
  
  private void reloadDataIfNeeded()
  {
    if (!this.version.equalsIgnoreCase(getCurrentVersion()))
    {
      this.data.clear();
      load();
    }
  }
  
  public void clear()
  {
    reloadDataIfNeeded();
    this.data.clear();
    flush();
  }
  
  public List<String> getContents()
  {
    return new ArrayList(this.data.keySet());
  }
  
  public PurchaseInfo getDetails(String paramString)
  {
    reloadDataIfNeeded();
    if (this.data.containsKey(paramString)) {
      return (PurchaseInfo)this.data.get(paramString);
    }
    return null;
  }
  
  public boolean includesProduct(String paramString)
  {
    reloadDataIfNeeded();
    return this.data.containsKey(paramString);
  }
  
  public void put(String paramString1, String paramString2, String paramString3)
  {
    reloadDataIfNeeded();
    if (!this.data.containsKey(paramString1))
    {
      this.data.put(paramString1, new PurchaseInfo(paramString2, paramString3));
      flush();
    }
  }
  
  public void remove(String paramString)
  {
    reloadDataIfNeeded();
    if (this.data.containsKey(paramString))
    {
      this.data.remove(paramString);
      flush();
    }
  }
  
  public String toString()
  {
    return TextUtils.join(", ", this.data.keySet());
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\anjlab\android\iab\v3\BillingCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */