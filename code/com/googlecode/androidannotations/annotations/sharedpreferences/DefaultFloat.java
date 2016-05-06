package com.googlecode.androidannotations.annotations.sharedpreferences;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({java.lang.annotation.ElementType.METHOD})
public @interface DefaultFloat
{
  float value();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\googlecode\androidannotations\annotations\sharedpreferences\DefaultFloat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */