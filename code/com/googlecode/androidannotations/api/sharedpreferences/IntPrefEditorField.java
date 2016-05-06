package com.googlecode.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences.Editor;

public final class IntPrefEditorField<T extends EditorHelper<T>>
  extends AbstractPrefEditorField<T>
{
  IntPrefEditorField(T paramT, String paramString)
  {
    super(paramT, paramString);
  }
  
  public T put(int paramInt)
  {
    this.editorHelper.getEditor().putInt(this.key, paramInt);
    return this.editorHelper;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\googlecode\androidannotations\api\sharedpreferences\IntPrefEditorField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */