package net.roarsoftware.lastfm;

import java.util.Collection;

public class PaginatedResult<T>
{
  private int page;
  private Collection<T> pageResults;
  private int totalPages;
  
  PaginatedResult(int paramInt1, int paramInt2, Collection<T> paramCollection)
  {
    this.page = paramInt1;
    this.totalPages = paramInt2;
    this.pageResults = paramCollection;
  }
  
  public int getPage()
  {
    return this.page;
  }
  
  public Collection<T> getPageResults()
  {
    return this.pageResults;
  }
  
  public int getTotalPages()
  {
    return this.totalPages;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\PaginatedResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */