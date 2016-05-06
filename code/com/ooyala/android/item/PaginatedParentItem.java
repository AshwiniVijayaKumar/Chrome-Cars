package com.ooyala.android.item;

import com.ooyala.android.util.OrderedMap;
import org.json.JSONObject;

public abstract interface PaginatedParentItem
  extends JSONUpdatableItem
{
  public abstract int childrenCount();
  
  public abstract OrderedMap<String, ? extends ContentItem> getAllAvailableChildren();
  
  public abstract String getEmbedCode();
  
  public abstract String getNextChildren();
  
  public abstract boolean hasMoreChildren();
  
  public abstract JSONUpdatableItem.ReturnState update(JSONObject paramJSONObject);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\item\PaginatedParentItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */