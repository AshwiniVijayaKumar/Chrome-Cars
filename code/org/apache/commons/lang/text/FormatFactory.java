package org.apache.commons.lang.text;

import java.text.Format;
import java.util.Locale;

public abstract interface FormatFactory
{
  public abstract Format getFormat(String paramString1, String paramString2, Locale paramLocale);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\commons\lang\text\FormatFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */