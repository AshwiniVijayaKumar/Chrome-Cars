/////////////////////////////////Website//////////////////////////////
var error_website = 'Error';
var pleaseentercorrectweburl_website = 'Please enter correct web url.';

function getwebsite(index)
{
	var appId = localStorage.getItem("applicationID");
	 if(appId == '242e50bdd6ac')
    {	
       
    	console.log("application id  found Abhishek");
    	showHideAd("hide");
    }
	else{
		
	     }
	
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    if(checkNetworkConnection())
    {
        sessionStorage.removeItem("singlePage");
        $websiteData= $(masterData).find("website[indexval=" + index + "]");
        var funcUrl='';
        var counter=0;
        var htmlWebsite='';
        $websiteData.find("websiteFieldrow").each(function()
                                             {
                                                  var pageName=$(this).find('websitePageName').text();
                                                  var imageUrl=$(this).find('websiteIconName').text();
                                                  var link=$(this).find("websitePage").text();
                                                  var navigationCheck=($(this).find("inAppNavigation").text());
                                                  var flagForBrowserOpen= ($(this).find("nativeOsBrowser").text());
                                                  var authenticationCheck=$(this).find("websiteAuthCheck").text();

         funcUrl='openInnerWebsite(\''+link+'\',\''+authenticationCheck+'\',\''+navigationCheck+'\',\''+flagForBrowserOpen+'\')';
         htmlWebsite+='<span class="audio_list" onclick="openInnerWebsite(\''+link+'\',\''+authenticationCheck+'\',\''+navigationCheck+'\',\''+flagForBrowserOpen+'\')">'+returnImageHtml(imageUrl)+' <span>'+pageName+'</span></span>';
         counter=parseFloat(counter)+1;
        });
        
        if(parseFloat(counter) == 1)
        {
            window.setTimeout(funcUrl,10);
            sessionStorage.setItem("singlePage","true");
        }
        else
        {
            setTimeout(function(){
                       appendHtml(htmlWebsite,'',1);
                       },100);
        }
    }
}


function openInnerWebsite(link,authenticationCheck,navigationCheck,flagForBrowserOpen)
{

    
    if(link.length<8)
	{
	alertPopUp(error_website,pleaseentercorrectweburl_website);
	return true;
	}
	
	
    if(flagForBrowserOpen=='true')
    {
        //window.open(link, "_blank");
		toaster.openWebUrl(link);
    }
    else
    {
	if(navigationCheck!=null||navigationCheck!='')
    {
        link=link+"@@@@@"+navigationCheck;
    }
    
    sessionStorage.setItem("websitePageFlag","true");
    
    if(sessionStorage.getItem("singlePage") == "true")
    {
    	 openWebPage(link,1);
    }
    else
    {
        sessionStorage.setItem("websiteInnerPageFlag","true");
        openWebPage(link,2);
    }
    }
}

function openWebPage(link,pageLevel)
{
    sessionStorage.setItem('openPageIndex',"false");
	sessionStorage.setItem('audioStatus',"true");
    //$("#contentHolder7").css({"background-color": "#fff"});
	$("#contentHolder7").css('background-image','url(' + sessionStorage.getItem("appBackground") + ')')
	.css('background-size','100% 100%');
    if(!pageLevel)
    {
        pageLevel=2;
    }
    if(window.localStorage.getItem("applicationID")!="2b77f6096336")
    {
    	appendHtml('',7,pageLevel);
    }
  
    if(link.indexOf("http")==-1)
	{
	link="http://"+link;
	}
  
  
    if(window.localStorage.getItem("layout")=="bottom") {
        if(link.indexOf(".pdf")!=-1)
        {
            history.go("-1");
            toaster.goToPdfReader(link,"NO");
        }
        else
        {
           if(sessionStorage.getItem("navigationBarType")=="none")
          {
         if(sessionStorage.getItem("singlePageId"))
		  {
		   window.location="fullscreenwesitesinglePageId"+link;
		   sessionStorage.removeItem("singlePageId");
		  }
		  else
		  {
          window.location="fullscreenwesite"+link;
		  }
          }
         else
          {
		   if(sessionStorage.getItem("singlePageId"))
		   {
						if(localStorage.getItem("applicationID") == "f1b2986281af")
						{
							localStorage.setItem("showheader","showheader");
							window.location="fullscreenwesitesinglePageIdforheaderbottom"+link;	
						}
						else
						{
							window.location="fullscreenwesitesinglePageId"+link;
						}							
						 sessionStorage.removeItem("singlePageId");
		  }
		  else
		  {
          window.location="bottom"+link;
		  }
          }
        }
        
    }
    else {
        if(link.indexOf(".pdf")!=-1)
        {
           if(localStorage.getItem("applicationID") == "b27f12bab258")
        	{
        		history.go("-1");
        		 window.plugins.pdfviewerplugin.show({
                     url: link
                     }, function() {}, function() {});
        	}
        	else
        	{
        		 history.go("-1");
                 toaster.goToPdfReader(link,"NO");
        	}
        }
        else
        {
        	if(link=="fb.me")
         {
          link="http://www.facebook.com";
         }
		 
		   if(mobileADS)
		   {
		    if(window.localStorage.getItem("applicationID")!="242e50bdd6ac")
				   {
				    window.location="bottom"+link;

				   }
			   else
				   {
				   window.location="show"+link;
				   }
		   }
		   else{
            window.location="show"+link;
			}
        }
        
    }
}

