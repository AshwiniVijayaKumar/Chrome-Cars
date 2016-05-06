package com.nostra13.universalimageloader.core;

class ImageSize
{
  private static final String TO_STRING_PATTERN = "%sx%s";
  int height;
  int width;
  
  public ImageSize(int paramInt1, int paramInt2)
  {
    this.width = paramInt1;
    this.height = paramInt2;
  }
  
  public String toString()
  {
    return String.format("%sx%s", new Object[] { Integer.valueOf(this.width), Integer.valueOf(this.height) });
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\nostra13\universalimageloader\core\ImageSize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */