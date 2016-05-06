package com.simplerssreader;

import android.text.Html;
import java.io.PrintStream;

public class RssItem
{
  private final String author;
  private final String date;
  private final String description;
  private final String image;
  private final String link;
  private final String title;
  
  public RssItem(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    this.title = paramString1;
    this.link = paramString2;
    this.description = paramString3;
    this.image = paramString4;
    this.date = paramString5;
    this.author = paramString6;
  }
  
  public String getAuthor()
  {
    try
    {
      String str = Html.fromHtml(this.author).toString();
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  public String getDate()
  {
    return this.date;
  }
  
  public String getDescription()
  {
    try
    {
      String str = Html.fromHtml(this.description).toString();
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  public String getImage()
  {
    return this.image;
  }
  
  public String getLink()
  {
    return this.link;
  }
  
  public String getTitle()
  {
    try
    {
      String str = Html.fromHtml(this.title).toString().trim();
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  public String toString()
  {
    String str = "Title" + this.title + " link: " + this.link + " Description : " + this.description + " Date: " + this.date + " Author: " + this.author;
    System.out.println("Title" + this.title + " link: " + this.link + " Description : " + this.description + " Date: " + this.date + " Author: " + this.author);
    return str;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\simplerssreader\RssItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */