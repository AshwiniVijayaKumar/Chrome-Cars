package twitter4j.util;

public final class CharacterUtil
{
  private CharacterUtil()
  {
    throw new AssertionError();
  }
  
  public static int count(String paramString)
  {
    return paramString.length();
  }
  
  public static boolean isExceedingLengthLimitation(String paramString)
  {
    return count(paramString) > 140;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\util\CharacterUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */