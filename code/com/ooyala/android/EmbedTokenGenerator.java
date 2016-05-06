package com.ooyala.android;

import java.util.List;

public abstract interface EmbedTokenGenerator
{
  public abstract void getTokenForEmbedCodes(List<String> paramList, EmbedTokenGeneratorCallback paramEmbedTokenGeneratorCallback);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\EmbedTokenGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */