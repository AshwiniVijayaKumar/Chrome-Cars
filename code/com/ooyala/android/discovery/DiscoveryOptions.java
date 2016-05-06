package com.ooyala.android.discovery;

public class DiscoveryOptions
{
  private final long limit;
  private final long timeoutInMilliSecond;
  private final Type type;
  
  private DiscoveryOptions(Type paramType, long paramLong1, long paramLong2)
  {
    this.type = paramType;
    this.limit = paramLong1;
    this.timeoutInMilliSecond = paramLong2;
  }
  
  public long getLimit()
  {
    return this.limit;
  }
  
  public long getTimoutInMilliSeconds()
  {
    return this.timeoutInMilliSecond;
  }
  
  public Type getType()
  {
    return this.type;
  }
  
  public static class Builder
  {
    private long limit = 10L;
    private long timeoutInMilliSecond = 10000L;
    private DiscoveryOptions.Type type = DiscoveryOptions.Type.SimilarAssets;
    
    public DiscoveryOptions build()
    {
      return new DiscoveryOptions(this.type, this.limit, this.timeoutInMilliSecond, null);
    }
    
    public Builder setLimit(long paramLong)
    {
      this.limit = paramLong;
      return this;
    }
    
    public Builder setTimeout(long paramLong)
    {
      this.timeoutInMilliSecond = paramLong;
      return this;
    }
    
    public Builder setType(DiscoveryOptions.Type paramType)
    {
      this.type = paramType;
      return this;
    }
  }
  
  public static enum Type
  {
    Momentum,  Popular,  SimilarAssets;
    
    private Type() {}
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\discovery\DiscoveryOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */