package com.googlecode.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences.Editor;

public final class LongPrefEditorField<T extends EditorHelper<T>>
  extends AbstractPrefEditorField<T>
{
  LongPrefEditorField(T paramT, String paramString)
  {
    super(paramT, paramString);
  }
  
  public T put(long paramLong)
  {
    this.editorHelper.getEditor().putLong(this.key, paramLong);
    return this.editorHelper;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\googlecode\androidannotations\api\sharedpreferences\LongPrefEditorField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */