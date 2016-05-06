/////////////////////////////////Website//////////////////////////////
function getdonation(index)
{
  if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    if(checkNetworkConnection())
    {
        sessionStorage.removeItem("singlePage");
        $websiteData= $(masterData).find("donation[indexval=" + index + "]");
        var funcUrl='';
        var counter=0;
        var htmlWebsite='';
        $websiteData.find("donationFieldrow").each(function()
                                                  {
                                                  var pageName=$(this).find('donationPageName').text();
                                                  var imageUrl=$(this).find('donationIconName').text();
                                                  var link=$(this).find("donationPage").text();
                                                  var navigationCheck="";
                                                  var flagForBrowserOpen="true";
                                                  var authenticationCheck="";

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