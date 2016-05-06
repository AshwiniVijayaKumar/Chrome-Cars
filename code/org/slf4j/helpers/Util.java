package org.slf4j.helpers;

import java.io.PrintStream;

public class Util
{
  public static final void report(String paramString)
  {
    System.err.println("SLF4J: " + paramString);
  }
  
  public static final void report(String paramString, Throwable paramThrowable)
  {
    System.err.println(paramString);
    System.err.println("Reported exception:");
    paramThrowable.printStackTrace();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\slf4j\helpers\Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */