package com.googlecode.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences.Editor;

public final class BooleanPrefEditorField<T extends EditorHelper<T>>
  extends AbstractPrefEditorField<T>
{
  BooleanPrefEditorField(T paramT, String paramString)
  {
    super(paramT, paramString);
  }
  
  public T put(boolean paramBoolean)
  {
    this.editorHelper.getEditor().putBoolean(this.key, paramBoolean);
    return this.editorHelper;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\googlecode\androidannotations\api\sharedpreferences\BooleanPrefEditorField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */