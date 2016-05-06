//////////////////////////Code Page///////////////////////////

function getcodepage(index)
{

    if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
	console.log('called=====>'+index);

	var tag=$(masterData).find("codePage").each(function () 
	{
		var id = $(this).attr('indexval');
		if(id==index)
		{
	var codePageHeading=$(this).find("codePageHeading").text();
    var codePageBody=$(this).find("codePageBody").text();
	
	var codePageHtmlUrl=$(this).find("codePageBodyHtml").text();
    codePageHtmlUrl="http://"+window.localStorage.getItem("reseller")+"/code_page_html/"+codePageHtmlUrl;
	//codePageHtmlUrl=codePageHtmlUrl+"@@@@@true";
	if(!checkNetworkConnection())
     {
		var div = document.createElement('div');
    div.innerHTML = codePageBody;
    var link = div.getElementsByTagName("a");
	/*if(div.getElementsByTagName('video')[0]){
		var video = div.getElementsByTagName('video')[0];
		sessionStorage.setItem("VideoURLInCodepage",video.src);
		video.setAttribute("onclick","OpenVideoFromCodepage()");
	}*/
    for (var i=0; i<link.length; i++) {
        var text = link[i].href;
		if(text.substring(0,4)=="tel:") {
			
		}
		else if(text.substring(0,7)=="mailto:"){
		
		}
		else {
			localStorage.setItem("codePageLink"+i+"",text);
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
        var videoHtml='<a onclick="youtubeVideoCodePage()"><img src="http://i.ytimg.com/vi/'+VideoId+'/default.jpg" width="'+width+'" height="'+height+'" border="0"></a>';
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
		var codepageDetail='<div class="text-page"><h2>' + codePageHeading + '</h2>' +codePageBody+ '</div>'; 
	}
	else {
		var codepageDetail='<div class="text-page"><h2>' + codePageHeading + '</h2>' +div.innerHTML+ '</div>'; 
	}	
    appendHtml("<div class='page-text'>"+codepageDetail+"</div>",'',1);
	    
		//setTimeout(function(){$('.appypie-loader').hide();},1000);
		}
         else
     {
	  if(localStorage.getItem("applicationID") == "6d966e4fa4ed" && toaster.androidVerion() <= '17')
        		 { 
				   var div = document.createElement('div');
    div.innerHTML = codePageBody;
    var link = div.getElementsByTagName("a");
	/*if(div.getElementsByTagName('video')[0]){
		var video = div.getElementsByTagName('video')[0];
		sessionStorage.setItem("VideoURLInCodepage",video.src);
		video.setAttribute("onclick","OpenVideoFromCodepage()");
	}*/
    for (var i=0; i<link.length; i++) {
        var text = link[i].href;
		if(text.substring(0,4)=="tel:") {
			
		}
		else if(text.substring(0,7)=="mailto:"){
		
		}
		else {
			localStorage.setItem("codePageLink"+i+"",text);
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
        var videoHtml='<a onclick="youtubeVideoCodePage()"><img src="http://i.ytimg.com/vi/'+VideoId+'/default.jpg" width="'+width+'" height="'+height+'" border="0"></a>';
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
		var codepageDetail='<div class="text-page"><h2>' + codePageHeading + '</h2>' +codePageBody+ '</div>'; 
	}
	else {
		var codepageDetail='<div class="text-page"><h2>' + codePageHeading + '</h2>' +div.innerHTML+ '</div>'; 
	}	
    appendHtml("<div class='page-text'>"+codepageDetail+"</div>",'',1);
				 }
				 else
				 {
		  sessionStorage.setItem("websitePageFlag","true");
	      sessionStorage.setItem("FirstWebsitePge","true");
		  setTimeout(function() {
               openWebPage(codePageHtmlUrl,1);
               },500);
			   }
	     
		 
     }
	
    }
	});
}

function OpenVideoFromCodepage(){
	var videoUrl=sessionStorage.getItem("VideoURLInCodepage");
	if(window.localStorage.getItem("layout")=="bottom"){
				sessionStorage.setItem("websitePageFlag","true");
			}
			else {
				sessionStorage.setItem("websitePageFlag","false");
			}
    openWebPage(videoUrl,1);
	
}
function yourFunction(i) {
    if(window.localStorage.getItem("layout")=="bottom"){
				sessionStorage.setItem("websitePageFlag","true");
			}
			else {
				sessionStorage.setItem("websitePageFlag","false");
			}
    
			 openWebPage(localStorage.getItem("codePageLink"+i+""),1);
	
	
}

function OpenTable(id)
{
   var OpenTable='<style>#OT_form, .OT_wrapper {width: 100% !important;height: 100% !important;}.OT_wrapper {border: 2px solid #fff !important;background-color:#fff !important;}.OT_title, .OT_subtitle, .OT_list {width: 100% !important;text-align: center !important;}.OT_day, .OT_time, .OT_party{text-align: center !importantr;width: auto !important;}.OT_submit{width: 100%!important;}.ot_searchtimefield, .ot_searchdatefield, .ot_searchpartyfield{width: 80%!important;} #OT_timeList, #OT_partyList{left: 22% !important;}.OT_day input, .OT_time input, .OT_party input{text-align: center !importantr;width: 80% !important;}</style><script type="text/javascript" src="https://secure.opentable.com/frontdoor/default.aspx?rid='+id+'&restref='+id+'&hover=1"></script>';
    
    console.log(OpenTable);
    
    toaster.openTheWebsiteOnNewPage(OpenTable);
}