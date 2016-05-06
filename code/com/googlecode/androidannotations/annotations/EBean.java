package com.googlecode.androidannotations.annotations;

import com.googlecode.androidannotations.api.Scope;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface EBean
{
  Scope scope() default Scope.Default;
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\googlecode\androidannotations\annotations\EBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */