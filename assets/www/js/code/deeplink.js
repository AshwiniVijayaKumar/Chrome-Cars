/////////////////////////////////Website//////////////////////////////
var warning_deeplink = 'Warning';
var deeplinkingurlnoadded = 'Deeplinking url not added';

function getdeeplink(index)
{
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    if(checkNetworkConnection())
    {
        sessionStorage.removeItem("singlePage");
        $websiteData= $(masterData).find("deeplink[indexval=" + index + "]");
        var funcUrl='';
        var counter=0;
        var htmlWebsite='';
        $websiteData.find("deeplinkFieldrow").each(function()
                                             {
											      var deepLinkType=$(this).find('deeplinkDeviceType').text();
												  if(deepLinkType=="Android")
												  {
                                                  var pageName=$(this).find('deeplinkAppName').text();
                                                  var imageUrl=$(this).find('deeplinkAppLogo').text();
                                                  var link=$(this).find("deeplinkUrl").text();
                                                  var navigationCheck="false";
                                                  var flagForBrowserOpen= "true";
                                                  var authenticationCheck="false";

         funcUrl='openInnerDeepLinkWeb(\''+link+'\',\''+authenticationCheck+'\',\''+navigationCheck+'\',\''+flagForBrowserOpen+'\')';
         htmlWebsite+='<span class="audio_list" onclick="openInnerDeepLinkWeb(\''+link+'\',\''+authenticationCheck+'\',\''+navigationCheck+'\',\''+flagForBrowserOpen+'\')">'+returnImageHtml(imageUrl)+' <span>'+pageName+'</span></span>';
         counter=parseFloat(counter)+1;
		 }
        });
        
		if(parseFloat(counter) == 0)
        {
			
             alertPopUp(warning_deeplink,deeplinkingurlnoadded+'!');
            return;
        }
		
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


function openInnerDeepLinkWeb(link,authenticationCheck,navigationCheck,flagForBrowserOpen)
{
   
		toaster.openDeepLinkURL(link);
    
}

