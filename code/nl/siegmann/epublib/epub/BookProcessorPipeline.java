package nl.siegmann.epublib.epub;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import nl.siegmann.epublib.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookProcessorPipeline
  implements BookProcessor
{
  private List<BookProcessor> bookProcessors;
  private Logger log = LoggerFactory.getLogger(BookProcessorPipeline.class);
  
  public BookProcessorPipeline()
  {
    this(null);
  }
  
  public BookProcessorPipeline(List<BookProcessor> paramList)
  {
    this.bookProcessors = paramList;
  }
  
  public void addBookProcessor(BookProcessor paramBookProcessor)
  {
    if (this.bookProcessors == null) {
      this.bookProcessors = new ArrayList();
    }
    this.bookProcessors.add(paramBookProcessor);
  }
  
  public void addBookProcessors(Collection<BookProcessor> paramCollection)
  {
    if (this.bookProcessors == null) {
      this.bookProcessors = new ArrayList();
    }
    this.bookProcessors.addAll(paramCollection);
  }
  
  public List<BookProcessor> getBookProcessors()
  {
    return this.bookProcessors;
  }
  
  public Book processBook(Book paramBook)
  {
    if (this.bookProcessors == null) {
      return paramBook;
    }
    Iterator localIterator = this.bookProcessors.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (BookProcessor)localIterator.next();
      try
      {
        localObject = ((BookProcessor)localObject).processBook(paramBook);
        paramBook = (Book)localObject;
      }
      catch (Exception localException)
      {
        this.log.error(localException.getMessage(), localException);
      }
    }
    return paramBook;
  }
  
  public void setBookProcessingPipeline(List<BookProcessor> paramList)
  {
    this.bookProcessors = paramList;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\epub\BookProcessorPipeline.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */