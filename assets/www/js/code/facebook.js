////////////////Facebook///////////////////////

function getfacebookPage(index) {

if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }

		  $('.appypie-loader').show();
		    sessionStorage.removeItem("singlePage");
		    $facebookPageIndex = $(masterData).find( "facebook[indexval="+index+"]" );
		    var funcUrl='';
		    var counter=0;
		    var htmlFacebookPage='';
		    $facebookPageIndex.find('facebookrow').each(function()
		    		{
		    	 	var page_url=$(this).find("page_url").text();
		    	    funcUrl='openWebPage(\''+page_url+'\',1)';
		    	    htmlFacebookPage+='<span class="appypie-list" onclick="openWebPage(\''+page_url+'\',2)">'+returnImageHtml($(this).find("imgurl").text())+' <span>'+$(this).find("facebookName").text()+'</span></span>';
		    	    counter=parseFloat(counter)+1;
		    		});
		    
		    if(parseFloat(counter) == 1)
		    {
		        window.setTimeout(funcUrl,10);
		    }
		    else
		    {
		                   appendHtml(htmlFacebookPage,'',1);
		    }
}