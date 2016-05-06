package com.googlecode.androidannotations.annotations.rest;

import com.googlecode.androidannotations.api.rest.MediaType;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.TYPE})
public @interface Accept
{
  MediaType value();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\googlecode\androidannotations\annotations\rest\Accept.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */