//////////////////////////editor Page///////////////////////////

function geteditorpage(index)
{

if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
	//alert("amritesh ");
	console.log('called=====>'+index);

	var tag=$(masterData).find("editorPage").each(function () 
	{
		var id = $(this).attr('indexval');
		if(id==index)
		{
	var editorPageHeading=$(this).find("editorPageHeading").text();
    var editorPageBody=$(this).find("editorPageBody").text();
	
	var editorPageHtmlUrl=$(this).find("editorPageBodyHtml").text();
   // editorPageHtmlUrl="http://"+window.localStorage.getItem("reseller")+"/editor_page_html/"+editorPageHtmlUrl;
    editorPageHtmlUrl='http://'+localStorage.getItem("reseller")+'/media/user_space/'+localStorage.getItem("applicationID")+'/editor_page/'+editorPageHtmlUrl;
	if(!checkNetworkConnection())
     {
		 alert("checkNetworkConnection");
		var div = document.createElement('div');
    div.innerHTML = editorPageBody;
    var link = div.getElementsByTagName("a");
	/*if(div.getElementsByTagName('video')[0]){
		var video = div.getElementsByTagName('video')[0];
		sessionStorage.setItem("VideoURLIneditorpage",video.src);
		video.setAttribute("onclick","OpenVideoFromeditorpage()");
	}*/
    for (var i=0; i<link.length; i++) {
        var text = link[i].href;
		if(text.substring(0,4)=="tel:") {
			
		}
		else if(text.substring(0,7)=="mailto:"){
		
		}
		else {
			localStorage.setItem("editorPageLink"+i+"",text);
			link[i].removeAttribute("href");
			link[i].setAttribute("onclick","yourFunction('"+i+"')");
		}
    }
    var iframeObject = div.getElementsByTagName("iframe")[0];
    if(iframeObject) {
    var Iframe = div.getElementsByTagName("iframe")[0].getAttribute("src");
    var a = document.createElement('a');
    a.href = Iframe;
    var hostname = a.hostname;
    if((hostname.substring(0,11) == 'www.youtube') || (hostname.substring(0,8) == 'youtu.be')) {
        var width = '';
        var height = '';
    	var IframeSplit=Iframe.split('/');
        var Length=IframeSplit.length;
        var VideoId=IframeSplit[Length-1];
        localStorage.setItem("iframeVideoId",VideoId);
        if(div.getElementsByTagName("iframe")[0].getAttribute("width") == null || div.getElementsByTagName("iframe")[0].getAttribute("width") == '') {
    		width="300px";
    	}
    	else {
    		width=div.getElementsByTagName("iframe")[0].getAttribute("width");
    	}
    	if(div.getElementsByTagName("iframe")[0].getAttribute("height") == null || div.getElementsByTagName("iframe")[0].getAttribute("height") == ''){
    		height="300px";
    	}
    	else {
            height=div.getElementsByTagName("iframe")[0].getAttribute("height");
        }
        var videoHtml='<a onclick="youtubeVideoeditorPage()"><img src="http://i.ytimg.com/vi/'+VideoId+'/default.jpg" width="'+width+'" height="'+height+'" border="0"></a>';
        div.innerHTML=videoHtml;
    }
    else {
    	if(div.getElementsByTagName("iframe")[0].getAttribute("width") == null || div.getElementsByTagName("iframe")[0].getAttribute("width") == '') {
    		iframeObject.width="300px";
    	}
    	if(div.getElementsByTagName("iframe")[0].getAttribute("height") == null || div.getElementsByTagName("iframe")[0].getAttribute("height") == ''){
    		iframeObject.height="300px";
    	}
    }
    }
    if(div.innerHTML=='') {
		var editorpageDetail='<div class="text-page"><h2>' + editorPageHeading + '</h2>' +editorPageBody+ '</div>'; 
	}
	else {
		var editorpageDetail='<div class="text-page"><h2>' + editorPageHeading + '</h2>' +div.innerHTML+ '</div>'; 
	}	
    appendHtml("<div class='page-text'>"+editorpageDetail+"</div>",'',1);
	    
		//setTimeout(function(){$('.appypie-loader').hide();},1000);
		}
         else
     {
		  sessionStorage.setItem("websitePageFlag","true");
	      sessionStorage.setItem("FirstWebsitePge","true");
		 setTimeout(function() {
               openWebPage(editorPageHtmlUrl,1);
               },500);
	     
		 
     }
	
    }
	});
}
function OpenVideoFromeditorpage(){
	//alert(OpenVideoFromeditorpage);
	var videoUrl=sessionStorage.getItem("VideoURLIneditorpage");
	if(window.localStorage.getItem("layout")=="bottom"){
				sessionStorage.setItem("websitePageFlag","true");
			}
			else {
				sessionStorage.setItem("websitePageFlag","false");
			}
    openWebPage(videoUrl,1);
	
}
function yourFunction(i) {
	//alert("yourFunction");
    if(window.localStorage.getItem("layout")=="bottom"){
				sessionStorage.setItem("websitePageFlag","true");
			}
			else {
				sessionStorage.setItem("websitePageFlag","false");
			}
    
			 openWebPage(localStorage.getItem("editorPageLink"+i+""),1);
	
	
}
