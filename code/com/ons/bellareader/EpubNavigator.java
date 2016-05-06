package com.ons.bellareader;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.IOException;

public class EpubNavigator
  extends WebViewClient
{
  static String loadedUrl = "";
  private boolean atLeastOneBookOpen = false;
  private EpubManipulator book1;
  private EpubManipulator book2;
  private boolean exactlyOneBookOpen = true;
  private String pageOnView1 = "";
  private String pageOnView2 = "";
  private boolean synchronizedReadingActive = false;
  
  public static String getWebViewUrl()
  {
    return loadedUrl;
  }
  
  public ViewStateEnum closeView1()
  {
    if ((this.book1.getPageIndex(this.pageOnView1) >= 0) || (this.pageOnView1.equals(this.book1.getCurrentPageURL()))) {}
    try
    {
      this.book1.destroy();
      if ((this.exactlyOneBookOpen) || (this.book2 == null))
      {
        this.atLeastOneBookOpen = false;
        return ViewStateEnum.invisible;
      }
      this.book1 = this.book2;
      this.book2 = null;
      this.pageOnView1 = this.pageOnView2;
      this.pageOnView2 = "";
      this.exactlyOneBookOpen = true;
      setSynchronizedReadingActive(false);
      return loadPageIntoView1(this.pageOnView1);
      this.pageOnView1 = this.book1.getCurrentPageURL();
      loadPageIntoView1(this.book1.getCurrentPageURL());
      return ViewStateEnum.books;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public boolean flipSynchronizedReadingActive()
  {
    boolean bool = false;
    if (this.exactlyOneBookOpen) {
      return false;
    }
    if (!this.synchronizedReadingActive) {
      bool = true;
    }
    this.synchronizedReadingActive = bool;
    return true;
  }
  
  public String[] getLanguagesBook1()
  {
    return this.book1.getLanguages();
  }
  
  public String[] getLanguagesBook2()
  {
    return this.book2.getLanguages();
  }
  
  public void goToNextChapter(BookEnum paramBookEnum)
    throws Exception
  {
    if ((this.synchronizedReadingActive) || (paramBookEnum == BookEnum.first)) {
      setView1(this.book1.goToNextChapter());
    }
  }
  
  public void goToPrevChapter(BookEnum paramBookEnum)
    throws Exception
  {
    if ((this.synchronizedReadingActive) || (paramBookEnum == BookEnum.first)) {
      setView1(this.book1.goToPreviousChapter());
    }
  }
  
  public boolean isAtLeastOneBookOpen()
  {
    return this.atLeastOneBookOpen;
  }
  
  public boolean isExactlyOneBookOpen()
  {
    return this.exactlyOneBookOpen;
  }
  
  public ViewStateEnum loadPageIntoView1(String paramString)
  {
    EpubReaderMain.getView1().loadUrl(paramString);
    loadedUrl = paramString;
    if ((this.book1 != null) && ((paramString.equals(this.book1.getCurrentPageURL())) || (this.book1.getPageIndex(paramString) >= 0))) {
      return ViewStateEnum.books;
    }
    return ViewStateEnum.notes;
  }
  
  public boolean loadState(SharedPreferences paramSharedPreferences)
  {
    this.atLeastOneBookOpen = paramSharedPreferences.getBoolean("bookOpen", false);
    this.exactlyOneBookOpen = paramSharedPreferences.getBoolean("onlyOne", true);
    this.synchronizedReadingActive = paramSharedPreferences.getBoolean("sync", false);
    int i;
    int j;
    String str1;
    String str2;
    if (this.atLeastOneBookOpen)
    {
      this.pageOnView1 = paramSharedPreferences.getString("page1", "");
      i = paramSharedPreferences.getInt("CurrentPageBook1", 0);
      j = paramSharedPreferences.getInt("LanguageBook1", 0);
      str1 = paramSharedPreferences.getString("nameEpub1", "");
      str2 = paramSharedPreferences.getString("pathBook1", "");
    }
    try
    {
      this.book1 = new EpubManipulator(str2, str1, i, j);
      this.book1.goToPage(i);
      loadPageIntoView1(this.pageOnView1);
      this.pageOnView2 = paramSharedPreferences.getString("page2", "");
      if (!this.exactlyOneBookOpen)
      {
        i = paramSharedPreferences.getInt("CurrentPageBook2", 0);
        j = paramSharedPreferences.getInt("LanguageBook2", 0);
        str1 = paramSharedPreferences.getString("nameEpub2", "");
        paramSharedPreferences = paramSharedPreferences.getString("pathBook2", "");
      }
      try
      {
        this.book2 = new EpubManipulator(paramSharedPreferences, str1, i, j);
        this.book2.goToPage(i);
        return true;
      }
      catch (Exception paramSharedPreferences)
      {
        return true;
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public boolean openBook1(String paramString1, String paramString2, String paramString3)
  {
    for (;;)
    {
      int i;
      try
      {
        this.book1 = new EpubManipulator(paramString1, paramString2);
        if (!paramString3.equalsIgnoreCase("")) {
          break label108;
        }
        setView1(this.book1.getSpineElementPath(0));
        this.atLeastOneBookOpen = true;
        return true;
      }
      catch (Exception paramString1)
      {
        return false;
      }
      paramString1 = this.book1;
      if (i < EpubManipulator.spineElementPaths.length)
      {
        if (URLUtil.guessFileName(this.book1.getSpineElementPath(i), null, null).equalsIgnoreCase(URLUtil.guessFileName(paramString3, null, null))) {
          setView1(this.book1.getSpineElementPath(i));
        }
        i += 1;
        continue;
        label108:
        i = 0;
      }
    }
  }
  
  public void saveState(SharedPreferences.Editor paramEditor)
  {
    paramEditor.putBoolean("bookOpen", this.atLeastOneBookOpen);
    paramEditor.putBoolean("onlyOne", this.exactlyOneBookOpen);
    paramEditor.putBoolean("sync", this.synchronizedReadingActive);
    if ((this.atLeastOneBookOpen) && (this.book1 != null))
    {
      paramEditor.putString("page1", this.pageOnView1);
      paramEditor.putInt("CurrentPageBook1", this.book1.getCurrentSpineElementIndex());
      paramEditor.putInt("LanguageBook1", this.book1.getCurrentLanguage());
      paramEditor.putString("nameEpub1", this.book1.getDecompressedFolder());
      paramEditor.putString("pathBook1", this.book1.getFileName());
    }
    try
    {
      this.book1.closeStream();
      paramEditor.putString("page2", this.pageOnView2);
      if ((!this.exactlyOneBookOpen) && (this.book2 != null))
      {
        paramEditor.putInt("CurrentPageBook2", this.book2.getCurrentSpineElementIndex());
        paramEditor.putInt("LanguageBook2", this.book2.getCurrentLanguage());
        paramEditor.putString("nameEpub2", this.book2.getDecompressedFolder());
        paramEditor.putString("pathBook2", this.book2.getFileName());
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        try
        {
          this.book2.closeStream();
          return;
        }
        catch (IOException paramEditor)
        {
          Log.e("Cannot close stream", "book2 stream");
          paramEditor.printStackTrace();
        }
        localIOException = localIOException;
        Log.e("Cannot close stream", "book1 stream");
        localIOException.printStackTrace();
      }
    }
  }
  
  public void setSynchronizedReadingActive(boolean paramBoolean)
  {
    this.synchronizedReadingActive = paramBoolean;
  }
  
  public ViewStateEnum setView1(String paramString)
  {
    this.pageOnView1 = paramString;
    if ((this.book1 != null) && (this.book1.goToPage(paramString))) {}
    for (ViewStateEnum localViewStateEnum = ViewStateEnum.books;; localViewStateEnum = ViewStateEnum.notes)
    {
      loadPageIntoView1(paramString);
      return localViewStateEnum;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\bellareader\EpubNavigator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */