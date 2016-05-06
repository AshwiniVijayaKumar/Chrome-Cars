package nl.siegmann.epublib.browsersupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.Resources;
import nl.siegmann.epublib.domain.Spine;

public class Navigator
  implements Serializable
{
  private static final long serialVersionUID = 1076126986424925474L;
  private Book book;
  private String currentFragmentId;
  private int currentPagePos;
  private Resource currentResource;
  private int currentSpinePos;
  private List<NavigationEventListener> eventListeners = new ArrayList();
  
  public Navigator()
  {
    this(null);
  }
  
  public Navigator(Book paramBook)
  {
    this.book = paramBook;
    this.currentSpinePos = 0;
    if (paramBook != null) {
      this.currentResource = paramBook.getCoverPage();
    }
    this.currentPagePos = 0;
  }
  
  private void handleEventListeners(NavigationEvent paramNavigationEvent)
  {
    int i = 0;
    try
    {
      while (i < this.eventListeners.size())
      {
        ((NavigationEventListener)this.eventListeners.get(i)).navigationPerformed(paramNavigationEvent);
        i += 1;
      }
      return;
    }
    finally
    {
      paramNavigationEvent = finally;
      throw paramNavigationEvent;
    }
  }
  
  public boolean addNavigationEventListener(NavigationEventListener paramNavigationEventListener)
  {
    return this.eventListeners.add(paramNavigationEventListener);
  }
  
  public Book getBook()
  {
    return this.book;
  }
  
  public String getCurrentFragmentId()
  {
    return this.currentFragmentId;
  }
  
  public Resource getCurrentResource()
  {
    return this.currentResource;
  }
  
  public int getCurrentSectionPos()
  {
    return this.currentPagePos;
  }
  
  public int getCurrentSpinePos()
  {
    return this.currentSpinePos;
  }
  
  public void gotoBook(Book paramBook, Object paramObject)
  {
    paramObject = new NavigationEvent(paramObject, this);
    this.book = paramBook;
    this.currentFragmentId = null;
    this.currentPagePos = 0;
    this.currentResource = null;
    this.currentSpinePos = paramBook.getSpine().getResourceIndex(this.currentResource);
    handleEventListeners((NavigationEvent)paramObject);
  }
  
  public int gotoFirstSpineSection(Object paramObject)
  {
    return gotoSpineSection(0, paramObject);
  }
  
  public int gotoLastSpineSection(Object paramObject)
  {
    return gotoSpineSection(this.book.getSpine().size() - 1, paramObject);
  }
  
  public int gotoNextSpineSection(Object paramObject)
  {
    if (this.currentSpinePos < 0) {
      return gotoSpineSection(0, paramObject);
    }
    return gotoSpineSection(this.currentSpinePos + 1, paramObject);
  }
  
  public int gotoPreviousSpineSection(int paramInt, Object paramObject)
  {
    if (this.currentSpinePos < 0) {
      return gotoSpineSection(0, paramInt, paramObject);
    }
    return gotoSpineSection(this.currentSpinePos - 1, paramInt, paramObject);
  }
  
  public int gotoPreviousSpineSection(Object paramObject)
  {
    return gotoPreviousSpineSection(0, paramObject);
  }
  
  public int gotoResource(String paramString, Object paramObject)
  {
    return gotoResource(this.book.getResources().getByHref(paramString), paramObject);
  }
  
  public int gotoResource(Resource paramResource, int paramInt, Object paramObject)
  {
    return gotoResource(paramResource, paramInt, null, paramObject);
  }
  
  public int gotoResource(Resource paramResource, int paramInt, String paramString, Object paramObject)
  {
    if (paramResource == null) {
      return -1;
    }
    paramObject = new NavigationEvent(paramObject, this);
    this.currentResource = paramResource;
    this.currentSpinePos = this.book.getSpine().getResourceIndex(this.currentResource);
    this.currentPagePos = paramInt;
    this.currentFragmentId = paramString;
    handleEventListeners((NavigationEvent)paramObject);
    return this.currentSpinePos;
  }
  
  public int gotoResource(Resource paramResource, Object paramObject)
  {
    return gotoResource(paramResource, 0, null, paramObject);
  }
  
  public int gotoResource(Resource paramResource, String paramString, Object paramObject)
  {
    return gotoResource(paramResource, 0, paramString, paramObject);
  }
  
  public int gotoResourceId(String paramString, Object paramObject)
  {
    return gotoSpineSection(this.book.getSpine().findFirstResourceById(paramString), paramObject);
  }
  
  public int gotoSpineSection(int paramInt1, int paramInt2, Object paramObject)
  {
    if (paramInt1 == this.currentSpinePos) {
      return this.currentSpinePos;
    }
    if ((paramInt1 < 0) || (paramInt1 >= this.book.getSpine().size())) {
      return this.currentSpinePos;
    }
    paramObject = new NavigationEvent(paramObject, this);
    this.currentSpinePos = paramInt1;
    this.currentPagePos = paramInt2;
    this.currentResource = this.book.getSpine().getResource(this.currentSpinePos);
    handleEventListeners((NavigationEvent)paramObject);
    return this.currentSpinePos;
  }
  
  public int gotoSpineSection(int paramInt, Object paramObject)
  {
    return gotoSpineSection(paramInt, 0, paramObject);
  }
  
  public boolean hasNextSpineSection()
  {
    return this.currentSpinePos < this.book.getSpine().size() - 1;
  }
  
  public boolean hasPreviousSpineSection()
  {
    return this.currentSpinePos > 0;
  }
  
  public boolean removeNavigationEventListener(NavigationEventListener paramNavigationEventListener)
  {
    return this.eventListeners.remove(paramNavigationEventListener);
  }
  
  public int setCurrentResource(Resource paramResource)
  {
    this.currentSpinePos = this.book.getSpine().getResourceIndex(paramResource);
    this.currentResource = paramResource;
    return this.currentSpinePos;
  }
  
  public void setCurrentSpinePos(int paramInt)
  {
    this.currentSpinePos = paramInt;
    this.currentResource = this.book.getSpine().getResource(paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\browsersupport\Navigator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */