package com.googlecode.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences.Editor;

public final class FloatPrefEditorField<T extends EditorHelper<T>>
  extends AbstractPrefEditorField<T>
{
  FloatPrefEditorField(T paramT, String paramString)
  {
    super(paramT, paramString);
  }
  
  public T put(float paramFloat)
  {
    this.editorHelper.getEditor().putFloat(this.key, paramFloat);
    return this.editorHelper;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\googlecode\androidannotations\api\sharedpreferences\FloatPrefEditorField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */