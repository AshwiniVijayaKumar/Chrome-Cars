package org.kobjects.pim;

public class VCard
  extends PimItem
{
  public VCard() {}
  
  public VCard(VCard paramVCard)
  {
    super(paramVCard);
  }
  
  public int getArraySize(String paramString)
  {
    if (paramString.equals("n")) {
      return 5;
    }
    if (paramString.equals("adr")) {
      return 6;
    }
    return -1;
  }
  
  public String getType()
  {
    return "vcard";
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\kobjects\pim\VCard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */