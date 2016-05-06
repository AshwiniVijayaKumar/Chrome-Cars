package nl.siegmann.epublib.epub;

import nl.siegmann.epublib.domain.Book;

public abstract interface BookProcessor
{
  public static final BookProcessor IDENTITY_BOOKPROCESSOR = new BookProcessor()
  {
    public Book processBook(Book paramAnonymousBook)
    {
      return paramAnonymousBook;
    }
  };
  
  public abstract Book processBook(Book paramBook);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\epub\BookProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */