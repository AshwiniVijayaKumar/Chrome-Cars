package com.joanzapata.pdfview.util;

public abstract interface Constants
{
  public static final boolean DEBUG_MODE = false;
  public static final int GRID_SIZE = 7;
  public static final int LOADED_SIZE = 3;
  public static final int MASK_ALPHA = 20;
  public static final float MINIMAP_MAX_SIZE = 200.0F;
  public static final float PART_SIZE = 256.0F;
  public static final float THUMBNAIL_RATIO = 0.2F;
  
  public static abstract interface Cache
  {
    public static final int CACHE_SIZE = (int)Math.pow(7.0D, 2.0D);
    public static final int THUMBNAILS_CACHE_SIZE = 4;
  }
  
  public static abstract interface Pinch
  {
    public static final float MAXIMUM_ZOOM = 10.0F;
    public static final float MINIMUM_ZOOM = 1.0F;
    public static final int QUICK_MOVE_THRESHOLD_DISTANCE = 50;
    public static final int QUICK_MOVE_THRESHOLD_TIME = 250;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\joanzapata\pdfview\util\Constants.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */