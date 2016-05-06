package com.nostra13.universalimageloader.core;

public final class DisplayImageOptions
{
  private final boolean cacheInMemory;
  private final boolean cacheOnDisc;
  private final DecodingType decodingType;
  private final Integer stubImage;
  
  private DisplayImageOptions(Builder paramBuilder)
  {
    this.stubImage = paramBuilder.stubImage;
    this.cacheInMemory = paramBuilder.cacheInMemory;
    this.cacheOnDisc = paramBuilder.cacheOnDisc;
    this.decodingType = paramBuilder.decodingType;
  }
  
  public static DisplayImageOptions createSimple()
  {
    return new Builder().build();
  }
  
  DecodingType getDecodingType()
  {
    return this.decodingType;
  }
  
  Integer getStubImage()
  {
    return this.stubImage;
  }
  
  boolean isCacheInMemory()
  {
    return this.cacheInMemory;
  }
  
  boolean isCacheOnDisc()
  {
    return this.cacheOnDisc;
  }
  
  boolean isShowStubImage()
  {
    return this.stubImage != null;
  }
  
  public static class Builder
  {
    private boolean cacheInMemory = false;
    private boolean cacheOnDisc = false;
    private DecodingType decodingType = DecodingType.FAST;
    private Integer stubImage = null;
    
    public DisplayImageOptions build()
    {
      return new DisplayImageOptions(this, null);
    }
    
    public Builder cacheInMemory()
    {
      this.cacheInMemory = true;
      return this;
    }
    
    public Builder cacheOnDisc()
    {
      this.cacheOnDisc = true;
      return this;
    }
    
    public Builder decodingType(DecodingType paramDecodingType)
    {
      this.decodingType = paramDecodingType;
      return this;
    }
    
    public Builder showStubImage(int paramInt)
    {
      this.stubImage = Integer.valueOf(paramInt);
      return this;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\nostra13\universalimageloader\core\DisplayImageOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */