package nl.siegmann.epublib.epub;

import java.io.OutputStream;
import nl.siegmann.epublib.domain.Resource;

public abstract interface HtmlProcessor
{
  public abstract void processHtmlResource(Resource paramResource, OutputStream paramOutputStream);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\epub\HtmlProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */