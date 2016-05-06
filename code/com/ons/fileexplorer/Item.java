package com.ons.fileexplorer;

public class Item
  implements Comparable<Item>
{
  private String data;
  private String date;
  private String image;
  private String name;
  private String path;
  
  public Item(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    this.name = paramString1;
    this.data = paramString2;
    this.date = paramString3;
    this.path = paramString4;
    this.image = paramString5;
  }
  
  public int compareTo(Item paramItem)
  {
    if (this.name != null) {
      return this.name.toLowerCase().compareTo(paramItem.getName().toLowerCase());
    }
    throw new IllegalArgumentException();
  }
  
  public String getData()
  {
    return this.data;
  }
  
  public String getDate()
  {
    return this.date;
  }
  
  public String getImage()
  {
    return this.image;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getPath()
  {
    return this.path;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\fileexplorer\Item.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */