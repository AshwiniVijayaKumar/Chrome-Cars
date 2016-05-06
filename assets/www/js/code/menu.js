//////////////////////////////////Menu/////////////////////////////////
function getmenu(index)
{
 if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
	var menuPageHtml="";
    $menuXmlData = $(masterData).find( "menu[indexval="+index+"]" );
   
    
    var x=0;
    $menuXmlData.find("MenuItems").each(function () {
                              
                              var itemHeader= $(this).find("itemHeader").text();
                              var itemDescription=$(this).find("itemDescription").text();
                              var itemImage= $(this).find("itemImage").text();
                              var itemPrice= $(this).find("itemPrice").text();
                                        if(itemImage != null && itemImage.trim() != ''){
                              menuPageHtml+='<div class="menu_page" onclick="menuDetailPage(' +index +','+x+');" ><h3>'+itemHeader+'</h3><span><img src="'+itemImage+'"/></span><div class="rgt-col"><span class="price">'+itemPrice+'</span><p>'+itemDescription+'</p></div><a class="more"></a></div>';
                                        }
                                        else
                                        {
                              menuPageHtml+='<div class="menu_page" onclick="menuDetailPage('+index+','+x+');" ><h3>'+itemHeader+'</h3><div class="rgt-col"><span class="price">'+itemPrice+'</span><p>'+itemDescription+'</p></div><a class="more"></a></div>';
                                        }
                              x=x+1;
                              });
	
    
    console.log('menuPageHtml --->'+menuPageHtml);
    appendHtml("<div class='page-text'>"+menuPageHtml+"</div>",'',1);
    
    
}

function menuDetailPage(index,pos)
{
    var menuXmlData = $(masterData).find( "menu[indexval="+parseInt(index)+"]" );
    $listIndexData=$(menuXmlData).find("MenuItems[indexval="+parseInt(pos)+"]");
    var menuDetailPageHtml='';
                                              var itemHeader= $listIndexData.find("itemHeader").text();
                                              var itemDescription=$listIndexData.find("itemDescription").text();
                                              var itemPrice=$listIndexData.find("itemPrice").text();
                                              if(itemPrice != null)
                                              {
                                               menuDetailPageHtml='<div class="menu_page"><h3>'+itemHeader+'</h3><div class="page-text"><p>'+itemDescription+'</p></div><span class="price">'+itemPrice+'</span></div>';
                                              }
                                              else
                                              {
                                               menuDetailPageHtml='<div class="menu_page"><h3>'+itemHeader+'</h3><div class="page-text"><p>'+itemDescription+'</p></div></div>';
                                              }
                console.log("menuDetailPageHml--->"+menuDetailPageHtml);
                appendHtml("<div class='page-text'>"+menuDetailPageHtml+"</div>",2,2);
}