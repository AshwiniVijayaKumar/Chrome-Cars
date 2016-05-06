package com.facebook.share;

public abstract interface Sharer
{
  public abstract boolean getShouldFailOnDataError();
  
  public abstract void setShouldFailOnDataError(boolean paramBoolean);
  
  public static class Result
  {
    final String postId;
    
    public Result(String paramString)
    {
      this.postId = paramString;
    }
    
    public String getPostId()
    {
      return this.postId;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\facebook\share\Sharer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */