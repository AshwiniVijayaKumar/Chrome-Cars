package nl.siegmann.epublib.domain;

import java.io.Serializable;
import nl.siegmann.epublib.util.StringUtil;

public class GuideReference
  extends TitledResourceReference
  implements Serializable
{
  public static String ACKNOWLEDGEMENTS = "acknowledgements";
  public static String BIBLIOGRAPHY = "bibliography";
  public static String COLOPHON = "colophon";
  public static String COPYRIGHT_PAGE = "copyright-page";
  public static String COVER = "cover";
  public static String DEDICATION = "dedication";
  public static String EPIGRAPH = "epigraph";
  public static String FOREWORD = "foreword";
  public static String GLOSSARY;
  public static String INDEX;
  public static String LOI = "loi";
  public static String LOT = "lot";
  public static String NOTES = "notes";
  public static String PREFACE = "preface";
  public static String TEXT = "text";
  public static String TITLE_PAGE = "title-page";
  public static String TOC = "toc";
  private static final long serialVersionUID = -316179702440631834L;
  private String type;
  
  static
  {
    INDEX = "index";
    GLOSSARY = "glossary";
  }
  
  public GuideReference(Resource paramResource)
  {
    this(paramResource, null);
  }
  
  public GuideReference(Resource paramResource, String paramString)
  {
    super(paramResource, paramString);
  }
  
  public GuideReference(Resource paramResource, String paramString1, String paramString2)
  {
    this(paramResource, paramString1, paramString2, null);
  }
  
  public GuideReference(Resource paramResource, String paramString1, String paramString2, String paramString3)
  {
    super(paramResource, paramString2, paramString3);
    if (StringUtil.isNotBlank(paramString1)) {}
    for (paramResource = paramString1.toLowerCase();; paramResource = null)
    {
      this.type = paramResource;
      return;
    }
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String paramString)
  {
    this.type = paramString;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\domain\GuideReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */