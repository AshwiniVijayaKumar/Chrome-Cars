package nl.siegmann.epublib.browsersupport;

import java.util.EventObject;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.util.StringUtil;

public class NavigationEvent
  extends EventObject
{
  private static final long serialVersionUID = -6346750144308952762L;
  private Navigator navigator;
  private Book oldBook;
  private String oldFragmentId;
  private Resource oldResource;
  private int oldSectionPos;
  private int oldSpinePos;
  
  public NavigationEvent(Object paramObject)
  {
    super(paramObject);
  }
  
  public NavigationEvent(Object paramObject, Navigator paramNavigator)
  {
    super(paramObject);
    this.navigator = paramNavigator;
    this.oldBook = paramNavigator.getBook();
    this.oldFragmentId = paramNavigator.getCurrentFragmentId();
    this.oldSectionPos = paramNavigator.getCurrentSectionPos();
    this.oldResource = paramNavigator.getCurrentResource();
    this.oldSpinePos = paramNavigator.getCurrentSpinePos();
  }
  
  public Book getCurrentBook()
  {
    return getNavigator().getBook();
  }
  
  public String getCurrentFragmentId()
  {
    return this.navigator.getCurrentFragmentId();
  }
  
  public Resource getCurrentResource()
  {
    return this.navigator.getCurrentResource();
  }
  
  public int getCurrentSectionPos()
  {
    return this.navigator.getCurrentSectionPos();
  }
  
  public int getCurrentSpinePos()
  {
    return this.navigator.getCurrentSpinePos();
  }
  
  public Navigator getNavigator()
  {
    return this.navigator;
  }
  
  public Book getOldBook()
  {
    return this.oldBook;
  }
  
  public String getOldFragmentId()
  {
    return this.oldFragmentId;
  }
  
  public Resource getOldResource()
  {
    return this.oldResource;
  }
  
  public int getOldSectionPos()
  {
    return this.oldSectionPos;
  }
  
  public int getOldSpinePos()
  {
    return this.oldSpinePos;
  }
  
  public boolean isBookChanged()
  {
    if (this.oldBook == null) {}
    while (this.oldBook != this.navigator.getBook()) {
      return true;
    }
    return false;
  }
  
  public boolean isFragmentChanged()
  {
    return StringUtil.equals(getOldFragmentId(), getCurrentFragmentId());
  }
  
  public boolean isResourceChanged()
  {
    return this.oldResource != getCurrentResource();
  }
  
  public boolean isSectionPosChanged()
  {
    return this.oldSectionPos != getCurrentSectionPos();
  }
  
  public boolean isSpinePosChanged()
  {
    return getOldSpinePos() != getCurrentSpinePos();
  }
  
  public void setNavigator(Navigator paramNavigator)
  {
    this.navigator = paramNavigator;
  }
  
  public void setOldBook(Book paramBook)
  {
    this.oldBook = paramBook;
  }
  
  void setOldFragmentId(String paramString)
  {
    this.oldFragmentId = paramString;
  }
  
  void setOldPagePos(int paramInt)
  {
    this.oldSectionPos = paramInt;
  }
  
  public void setOldResource(Resource paramResource)
  {
    this.oldResource = paramResource;
  }
  
  public void setOldSpinePos(int paramInt)
  {
    this.oldSpinePos = paramInt;
  }
  
  public String toString()
  {
    return StringUtil.toString(new Object[] { "oldSectionPos", Integer.valueOf(this.oldSectionPos), "oldResource", this.oldResource, "oldBook", this.oldBook, "oldFragmentId", this.oldFragmentId, "oldSpinePos", Integer.valueOf(this.oldSpinePos), "currentPagePos", Integer.valueOf(getCurrentSectionPos()), "currentResource", getCurrentResource(), "currentBook", getCurrentBook(), "currentFragmentId", getCurrentFragmentId(), "currentSpinePos", Integer.valueOf(getCurrentSpinePos()) });
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\browsersupport\NavigationEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */