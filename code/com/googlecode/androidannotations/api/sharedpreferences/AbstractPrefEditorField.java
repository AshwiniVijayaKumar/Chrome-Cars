package com.googlecode.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences.Editor;

public abstract class AbstractPrefEditorField<T extends EditorHelper<T>>
{
  protected final T editorHelper;
  protected final String key;
  
  public AbstractPrefEditorField(T paramT, String paramString)
  {
    this.editorHelper = paramT;
    this.key = paramString;
  }
  
  public final T remove()
  {
    this.editorHelper.getEditor().remove(this.key);
    return this.editorHelper;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\googlecode\androidannotations\api\sharedpreferences\AbstractPrefEditorField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */