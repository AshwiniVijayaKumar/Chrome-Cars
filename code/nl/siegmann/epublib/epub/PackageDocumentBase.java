package nl.siegmann.epublib.epub;

public class PackageDocumentBase
{
  public static final String BOOK_ID_ID = "BookId";
  public static final String NAMESPACE_DUBLIN_CORE = "http://purl.org/dc/elements/1.1/";
  public static final String NAMESPACE_OPF = "http://www.idpf.org/2007/opf";
  public static final String PREFIX_DUBLIN_CORE = "dc";
  public static final String PREFIX_OPF = "opf";
  public static final String dateFormat = "yyyy-MM-dd";
  
  protected static abstract interface DCAttributes
  {
    public static final String id = "id";
    public static final String scheme = "scheme";
  }
  
  protected static abstract interface DCTags
  {
    public static final String contributor = "contributor";
    public static final String coverage = "coverage";
    public static final String creator = "creator";
    public static final String date = "date";
    public static final String description = "description";
    public static final String format = "format";
    public static final String identifier = "identifier";
    public static final String language = "language";
    public static final String publisher = "publisher";
    public static final String relation = "relation";
    public static final String rights = "rights";
    public static final String source = "source";
    public static final String subject = "subject";
    public static final String title = "title";
    public static final String type = "type";
  }
  
  protected static abstract interface OPFAttributes
  {
    public static final String content = "content";
    public static final String event = "event";
    public static final String file_as = "file-as";
    public static final String href = "href";
    public static final String id = "id";
    public static final String idref = "idref";
    public static final String linear = "linear";
    public static final String media_type = "media-type";
    public static final String name = "name";
    public static final String property = "property";
    public static final String role = "role";
    public static final String scheme = "scheme";
    public static final String title = "title";
    public static final String toc = "toc";
    public static final String type = "type";
    public static final String uniqueIdentifier = "unique-identifier";
    public static final String version = "version";
  }
  
  protected static abstract interface OPFTags
  {
    public static final String guide = "guide";
    public static final String item = "item";
    public static final String itemref = "itemref";
    public static final String manifest = "manifest";
    public static final String meta = "meta";
    public static final String metadata = "metadata";
    public static final String packageTag = "package";
    public static final String reference = "reference";
    public static final String spine = "spine";
  }
  
  protected static abstract interface OPFValues
  {
    public static final String generator = "generator";
    public static final String meta_cover = "cover";
    public static final String no = "no";
    public static final String reference_cover = "cover";
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\epub\PackageDocumentBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */