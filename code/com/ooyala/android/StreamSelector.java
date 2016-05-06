package com.ooyala.android;

import com.ooyala.android.item.Stream;
import java.util.Set;

public abstract interface StreamSelector
{
  public abstract Stream bestStream(Set<Stream> paramSet, boolean paramBoolean);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\StreamSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */