////////////////////Share////////////////////

function getsocial(index)
{
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    window.location="removewebsite:";
    isSocial='YES';
    var socialHTML="";
    $socialXmlData = $(masterData).find( "social[indexval="+index+"]" );
        var itemTypeArray=["facebook", "twitter", "linkedin", "google", "youtube","instagram", "pinterest"];
        var deviceWidth=$(window).width();
		var pageLink;
		var count=0;
        for(i=0;i<itemTypeArray.length;i++)
        {
            if($socialXmlData.find(""+itemTypeArray[i]+"Row"))
            {
    $socialXmlData.find(itemTypeArray[i]+"Row").each(function(){
                                                     console.log("itemTypeArray[i --->"+itemTypeArray[i]);
                 pageLink=$(this).find(""+itemTypeArray[i]).text();
                if(pageLink.length > 5)
                {
                 console.log('check data--->'+pageLink);
                 var imageUrl=$(this).find(itemTypeArray[i]+'_icon').text()
                 console.log('check data image-->'+imageUrl);
                 var pageName=$(this).find(itemTypeArray[i]+'_name').text();
                 console.log('check data image-->'+pageName);
    socialHTML+='<span class="audio_list" onclick="openWebPage(\''+pageLink+'\',2)">'+returnImageHtml(imageUrl)+' <span>'+pageName+'</span></span>';
                count++;
            }
			
                                                          });
                }
				
        }
    
	if(count==1)
		{
		  openWebPage(pageLink,1);
		}
		else
		{
    appendHtml("<div class='page-text'>"+socialHTML+"</div>",'',1)
	   }
}


