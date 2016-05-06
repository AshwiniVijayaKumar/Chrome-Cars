function getLinkedin(index)
{
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    $('.appypie-loader').show();
    $linkdinIndex = $(masterData).find( "linkedin[indexval="+index+"]" );
    var funcUrl='';
    var counter=0;
    var htmllinkdin='';
    $linkdinIndex.find('linkedinrow').each(function()
                                                {
                                                var page_url=$(this).find("page_url").text();
                                                funcUrl='openWebPage(\''+page_url+'\',1)';
                                                htmllinkdin+='<span class="appypie-list" onclick="openWebPage(\''+page_url+'\',2)">'+returnImageHtml($(this).find("imgurl").text())+' <span>'+$(this).find("linkedinName").text()+'</span></span>';
                                                counter=parseFloat(counter)+1;
                                                });
    
    if(parseFloat(counter) == 1)
    {
        window.setTimeout(funcUrl,10);
    }
    else
    {
        appendHtml(htmllinkdin,'',1);
    }
}