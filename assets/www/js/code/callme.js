function getCallMe(index)
{
 if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    $('.appypie-loader').show();
	var html="";
     $data=$(masterData).find( "callMe[indexval="+index+"]");
			var title= ($data.find("title").text());
			html='<h2 style="text-align:'+sessionStorage.getItem('pageIndentation')+' !important">'+title+'</h2>';
			$data.find("callMeFieldrow").each(function () {
                                         var text= $(this).find("text").text();
                                         var imageUrl=$(this).find("imgurl").text();
                                         var subtext=$(this).find("subtext").text();
                                        
                                          if(text!=' ')
                                         {
                                              
                                              if(textIndentation=='center')
                                              {
                                            	  //alert("Inside If");
                                               html+='<div class="list"><div class="page_innerIcon" onclick="callMe(\''+subtext+'\');">'+returnImageHtml(imageUrl)+"<b>"+text+"</b>"+'</div><div class="textIndent"><a onclick="callMe(\''+subtext+'\');" style="color:'+sessionStorage.getItem('pageTextColor')+';">'+$(this).find("subtext").text()+'</a></div></div>';
                                              }
                                              else
                                              {
                                            	  // alert("Inside Else");
                                               html+='<div class="list"><div class="page_innerIcon" onclick="callMe(\''+subtext+'\');">'+returnImageHtml(imageUrl)+"<b>"+text+"</b>"+'</div><a onclick="callMe(\''+subtext+'\');" style="color:'+sessionStorage.getItem('pageTextColor')+';">'+$(this).find("subtext").text()+'</a></div>';
                                              }
   
                                         console.log("imageUrl --->"+imageUrl);
                                         }
			});
        html="<div style='color:"+sessionStorage.getItem('pageTextColor')+";'><div class='page-text custom-color'>"+html+"</div></div>";
        //alert(html);
        appendHtml(html,'',1);
}

function callMe(number)
{
	//number="tel:"+number;
window.location="showtel:"+number+"";
}