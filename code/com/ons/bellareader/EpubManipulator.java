package com.ons.bellareader;

import android.os.Environment;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.Spine;
import nl.siegmann.epublib.domain.SpineReference;
import nl.siegmann.epublib.epub.EpubReader;

public class EpubManipulator
{
  private static String location = Environment.getExternalStorageDirectory() + "/epubtemp/";
  public static int pageNumber;
  protected static String[] spineElementPaths;
  public static List<String> spineElementsFlip = new ArrayList();
  private List<String> availableLanguages;
  private Book book;
  private int currentLanguage;
  private String currentPage;
  public int currentSpineElementIndex;
  private String decompressedFolder;
  private String fileName;
  FileInputStream fs;
  private int pageCount;
  private List<Boolean> translations;
  
  public EpubManipulator(String paramString1, String paramString2)
    throws Exception
  {
    this.fs = new FileInputStream(paramString1);
    this.book = new EpubReader().readEpub(this.fs);
    this.fileName = paramString1;
    this.decompressedFolder = paramString2;
    List localList = this.book.getSpine().getSpineReferences();
    this.currentSpineElementIndex = 0;
    pageNumber = 0;
    this.currentLanguage = 0;
    paramString2 = new ArrayList();
    pages(localList, paramString2);
    this.pageCount = paramString2.size();
    spineElementPaths = new String[paramString2.size()];
    unzip(paramString1, location + this.decompressedFolder);
    paramString1 = getPathOPF(location + this.decompressedFolder);
    spineElementsFlip.clear();
    int i = 0;
    while (i < paramString2.size())
    {
      spineElementPaths[i] = ("file://" + location + this.decompressedFolder + "/" + paramString1 + "/" + (String)paramString2.get(i));
      spineElementsFlip.add(spineElementPaths[i]);
      i += 1;
    }
    if (paramString2.size() > 0) {
      goToPage(0);
    }
  }
  
  public EpubManipulator(String paramString1, String paramString2, int paramInt1, int paramInt2)
    throws Exception
  {
    this.fs = new FileInputStream(paramString1);
    this.book = new EpubReader().readEpub(this.fs);
    this.fileName = paramString1;
    this.decompressedFolder = paramString2;
    Object localObject = this.book.getSpine().getSpineReferences();
    this.currentSpineElementIndex = paramInt1;
    pageNumber = paramInt1;
    this.currentLanguage = paramInt2;
    paramString1 = new ArrayList();
    pages((List)localObject, paramString1);
    this.pageCount = paramString1.size();
    spineElementPaths = new String[paramString1.size()];
    localObject = getPathOPF(location + paramString2);
    paramInt2 = 0;
    while (paramInt2 < paramString1.size())
    {
      spineElementPaths[paramInt2] = ("file://" + location + paramString2 + "/" + (String)localObject + "/" + (String)paramString1.get(paramInt2));
      paramInt2 += 1;
    }
    goToPage(paramInt1);
  }
  
  private void deleteDir(File paramFile)
  {
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      int j = arrayOfFile.length;
      int i = 0;
      while (i < j)
      {
        deleteDir(arrayOfFile[i]);
        i += 1;
      }
    }
    paramFile.delete();
  }
  
  private static String getPathOPF(String paramString)
    throws IOException
  {
    String str1 = "";
    BufferedReader localBufferedReader = new BufferedReader(new FileReader(paramString + "/META-INF/container.xml"));
    String str2;
    int j;
    do
    {
      do
      {
        str2 = localBufferedReader.readLine();
        paramString = str1;
        if (str2 == null) {
          break;
        }
      } while (str2.indexOf("full-path") <= -1);
      i = str2.indexOf("\"", str2.indexOf("full-path"));
      j = str2.indexOf("\"", i + 1);
    } while ((i <= -1) || (j <= i));
    paramString = str2.substring(i + 1, j).trim();
    localBufferedReader.close();
    str1 = "./" + paramString;
    int i = str1.lastIndexOf('/');
    paramString = str1;
    if (i > -1) {
      paramString = str1.substring(0, i);
    }
    return paramString;
  }
  
  private int languageIndexFromID(String paramString)
  {
    int i = 0;
    while ((i < this.availableLanguages.size()) && (!((String)this.availableLanguages.get(i)).equals(paramString))) {
      i += 1;
    }
    return i;
  }
  
  private void pages(List<SpineReference> paramList, List<String> paramList1)
  {
    this.translations = new ArrayList();
    this.availableLanguages = new ArrayList();
    int i = 0;
    if (i < paramList.size())
    {
      String str1 = ((SpineReference)paramList.get(i)).getResource().getHref();
      String str2 = getPageLanguage(str1);
      if (str2 != "")
      {
        int j = languageIndexFromID(str2);
        if (j == this.availableLanguages.size()) {
          this.availableLanguages.add(str2);
        }
        if (j == 0)
        {
          this.translations.add(Boolean.valueOf(true));
          paramList1.add(str1);
        }
      }
      for (;;)
      {
        i += 1;
        break;
        this.translations.add(Boolean.valueOf(false));
        paramList1.add(str1);
      }
    }
  }
  
  public void closeStream()
    throws IOException
  {
    this.fs.close();
  }
  
  public void destroy()
    throws IOException
  {
    this.fs.close();
    deleteDir(new File(location + this.decompressedFolder));
  }
  
  public int getCurrentLanguage()
  {
    return this.currentLanguage;
  }
  
  public String getCurrentPageURL()
  {
    return this.currentPage;
  }
  
  public int getCurrentSpineElementIndex()
  {
    return this.currentSpineElementIndex;
  }
  
  public String getDecompressedFolder()
  {
    return this.decompressedFolder;
  }
  
  public String getFileName()
  {
    return this.fileName;
  }
  
  public String[] getLanguages()
  {
    String[] arrayOfString = new String[this.availableLanguages.size()];
    int i = 0;
    while (i < this.availableLanguages.size())
    {
      arrayOfString[i] = ((String)this.availableLanguages.get(i));
      i += 1;
    }
    return arrayOfString;
  }
  
  public int getPageIndex(String paramString)
  {
    int j = -1;
    String[] arrayOfString = paramString.split("\\.");
    String str2 = getPageLanguage(paramString);
    String str1 = paramString;
    if (this.availableLanguages.size() > 0)
    {
      str1 = paramString;
      if (str2 != "") {
        str1 = paramString.substring(0, paramString.lastIndexOf(str2)) + (String)this.availableLanguages.get(0) + "." + arrayOfString[(arrayOfString.length - 1)];
      }
    }
    int i = 0;
    while ((i < spineElementPaths.length) && (j == -1))
    {
      if (str1.equals(spineElementPaths[i])) {
        j = i;
      }
      i += 1;
    }
    return j;
  }
  
  public String getPageLanguage(String paramString)
  {
    paramString = paramString.split("\\.");
    if (paramString.length > 2)
    {
      paramString = paramString[(paramString.length - 2)];
      if (paramString.matches("[a-z][a-z]")) {
        return paramString;
      }
    }
    return "";
  }
  
  public String getSpineElementPath(int paramInt)
  {
    return spineElementPaths[paramInt];
  }
  
  public String goToNextChapter()
    throws Exception
  {
    return goToPage(this.currentSpineElementIndex + 1);
  }
  
  public String goToPage(int paramInt)
    throws Exception
  {
    return goToPage(paramInt, this.currentLanguage);
  }
  
  public String goToPage(int paramInt1, int paramInt2)
    throws Exception
  {
    int i = paramInt1;
    if (paramInt1 < 0) {
      i = 0;
    }
    paramInt1 = i;
    if (i >= this.pageCount) {
      paramInt1 = this.pageCount - 1;
    }
    this.currentSpineElementIndex = paramInt1;
    pageNumber = paramInt1;
    String str2 = spineElementPaths[this.currentSpineElementIndex];
    String str1 = str2;
    if (((Boolean)this.translations.get(paramInt1)).booleanValue())
    {
      str1 = str2.substring(str2.lastIndexOf("."));
      str2 = str2.substring(0, str2.lastIndexOf((String)this.availableLanguages.get(0)));
      str1 = str2 + (String)this.availableLanguages.get(paramInt2) + str1;
    }
    this.currentPage = str1;
    return str1;
  }
  
  public boolean goToPage(String paramString)
  {
    int i = getPageIndex(paramString);
    boolean bool = false;
    if (i >= 0) {
      paramString = getPageLanguage(paramString);
    }
    try
    {
      goToPage(i);
      if (paramString != "") {
        setLanguage(paramString);
      }
      bool = true;
      return bool;
    }
    catch (Exception paramString)
    {
      Log.e("goToPage error:", paramString.getMessage());
    }
    return false;
  }
  
  public String goToPreviousChapter()
    throws Exception
  {
    return goToPage(this.currentSpineElementIndex - 1);
  }
  
  public String metadata()
  {
    Object localObject3 = this.book.getMetadata();
    Object localObject2 = "<html><body><table>";
    List localList = ((Metadata)localObject3).getTitles();
    int i;
    if (localList.size() > 0)
    {
      localObject1 = "<html><body><table>" + "<tr><td>Titles:</td>";
      localObject1 = (String)localObject1 + "<td>" + (String)localList.get(0) + "</td></tr>";
      i = 1;
      for (;;)
      {
        localObject2 = localObject1;
        if (i >= localList.size()) {
          break;
        }
        localObject1 = (String)localObject1 + "<tr><td></td><td>" + (String)localList.get(0) + "</td></tr>";
        i += 1;
      }
    }
    localList = ((Metadata)localObject3).getAuthors();
    Object localObject1 = localObject2;
    if (localList.size() > 0)
    {
      localObject1 = (String)localObject2 + "<tr><td>Authors:</td>";
      localObject2 = (String)localObject1 + "<td>" + ((Author)localList.get(0)).getFirstname() + " " + ((Author)localList.get(0)).getLastname() + "</td></tr>";
      i = 1;
      for (;;)
      {
        localObject1 = localObject2;
        if (i >= localList.size()) {
          break;
        }
        localObject2 = (String)localObject2 + "<tr><td></td><td>" + ((Author)localList.get(0)).getFirstname() + " " + ((Author)localList.get(0)).getLastname() + "</td></tr>";
        i += 1;
      }
    }
    localList = ((Metadata)localObject3).getContributors();
    localObject2 = localObject1;
    if (localList.size() > 0)
    {
      localObject1 = (String)localObject1 + "<tr><td>Contributors:</td>";
      localObject1 = (String)localObject1 + "<td>" + ((Author)localList.get(0)).getFirstname() + " " + ((Author)localList.get(0)).getLastname() + "</td></tr>";
      i = 1;
      for (;;)
      {
        localObject2 = localObject1;
        if (i >= localList.size()) {
          break;
        }
        localObject1 = (String)localObject1 + "<tr><td></td><td>" + ((Author)localList.get(0)).getFirstname() + " " + ((Author)localList.get(0)).getLastname() + "</td></tr>";
        i += 1;
      }
    }
    localObject2 = (String)localObject2 + "<tr><td>Language:</td><td>" + ((Metadata)localObject3).getLanguage() + "</td></tr>";
    localList = ((Metadata)localObject3).getPublishers();
    localObject1 = localObject2;
    if (localList.size() > 0)
    {
      localObject1 = (String)localObject2 + "<tr><td>Publishers:</td>";
      localObject2 = (String)localObject1 + "<td>" + (String)localList.get(0) + "</td></tr>";
      i = 1;
      for (;;)
      {
        localObject1 = localObject2;
        if (i >= localList.size()) {
          break;
        }
        localObject2 = (String)localObject2 + "<tr><td></td><td>" + (String)localList.get(0) + "</td></tr>";
        i += 1;
      }
    }
    localList = ((Metadata)localObject3).getTypes();
    localObject2 = localObject1;
    if (localList.size() > 0)
    {
      localObject1 = (String)localObject1 + "<tr><td>Types:</td>";
      localObject1 = (String)localObject1 + "<td>" + (String)localList.get(0) + "</td></tr>";
      i = 1;
      for (;;)
      {
        localObject2 = localObject1;
        if (i >= localList.size()) {
          break;
        }
        localObject1 = (String)localObject1 + "<tr><td></td><td>" + (String)localList.get(0) + "</td></tr>";
        i += 1;
      }
    }
    localList = ((Metadata)localObject3).getDescriptions();
    localObject1 = localObject2;
    if (localList.size() > 0)
    {
      localObject1 = (String)localObject2 + "<tr><td>Descriptions:</td>";
      localObject2 = (String)localObject1 + "<td>" + (String)localList.get(0) + "</td></tr>";
      i = 1;
      for (;;)
      {
        localObject1 = localObject2;
        if (i >= localList.size()) {
          break;
        }
        localObject2 = (String)localObject2 + "<tr><td></td><td>" + (String)localList.get(0) + "</td></tr>";
        i += 1;
      }
    }
    localObject3 = ((Metadata)localObject3).getRights();
    localObject2 = localObject1;
    if (((List)localObject3).size() > 0)
    {
      localObject1 = (String)localObject1 + "<tr><td>Rights:</td>";
      localObject1 = (String)localObject1 + "<td>" + (String)((List)localObject3).get(0) + "</td></tr>";
      i = 1;
      for (;;)
      {
        localObject2 = localObject1;
        if (i >= ((List)localObject3).size()) {
          break;
        }
        localObject1 = (String)localObject1 + "<tr><td></td><td>" + (String)((List)localObject3).get(0) + "</td></tr>";
        i += 1;
      }
    }
    return (String)localObject2 + "</table></body></html>";
  }
  
  public void setLanguage(int paramInt)
    throws Exception
  {
    if ((paramInt >= 0) && (paramInt <= this.availableLanguages.size())) {
      this.currentLanguage = paramInt;
    }
    goToPage(this.currentSpineElementIndex);
  }
  
  public void setLanguage(String paramString)
    throws Exception
  {
    int i = 0;
    while ((i < this.availableLanguages.size()) && (!((String)this.availableLanguages.get(i)).equals(paramString))) {
      i += 1;
    }
    setLanguage(i);
  }
  
  public void unzip(String paramString1, String paramString2)
    throws IOException
  {
    Object localObject1 = new ArrayList();
    Object localObject2 = new File(paramString1);
    paramString1 = new File(paramString2);
    paramString1.mkdir();
    localObject2 = new ZipFile((File)localObject2, 1);
    Enumeration localEnumeration = ((ZipFile)localObject2).entries();
    while (localEnumeration.hasMoreElements())
    {
      Object localObject4 = (ZipEntry)localEnumeration.nextElement();
      Object localObject5 = ((ZipEntry)localObject4).getName();
      Object localObject3 = new File(paramString1, (String)localObject5);
      if (((String)localObject5).endsWith(".zip")) {
        ((List)localObject1).add(((File)localObject3).getAbsolutePath());
      }
      ((File)localObject3).getParentFile().mkdirs();
      if (!((ZipEntry)localObject4).isDirectory())
      {
        localObject4 = new BufferedInputStream(((ZipFile)localObject2).getInputStream((ZipEntry)localObject4));
        localObject5 = new byte['ࠀ'];
        localObject3 = new BufferedOutputStream(new FileOutputStream((File)localObject3), 2048);
        for (;;)
        {
          int i = ((BufferedInputStream)localObject4).read((byte[])localObject5, 0, 2048);
          if (i == -1) {
            break;
          }
          ((BufferedOutputStream)localObject3).write((byte[])localObject5, 0, i);
        }
        ((BufferedOutputStream)localObject3).flush();
        ((BufferedOutputStream)localObject3).close();
        ((BufferedInputStream)localObject4).close();
      }
    }
    ((ZipFile)localObject2).close();
    paramString1 = ((List)localObject1).iterator();
    while (paramString1.hasNext())
    {
      localObject1 = (String)paramString1.next();
      unzip((String)localObject1, paramString2 + File.separatorChar + ((String)localObject1).substring(0, ((String)localObject1).lastIndexOf(".zip")));
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\bellareader\EpubManipulator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */