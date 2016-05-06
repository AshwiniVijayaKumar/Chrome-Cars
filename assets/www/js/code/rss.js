var CustomRssJson='';
var server_response_rss = "Server Response";
var data_not_found_onserver_rss = "Data not found on server !";
function getRSSFeed(index)
{
 if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
	window.location ="removewebsite:";
    $rssDataXml = $(masterData).find( "Rss[indexval="+index+"]" );
        var funcName='';
        var counter=0;
        var htmlRss='';
        $rssDataXml.find("rssFieldrow").each(function()
                                                    {
                                             
                                             var pageName=$(this).find('rssLabel').text();
                                             var link=$(this).find('link').text();
                                             var imageUrl=$(this).find('rssIconImage').text();
                                             funcName='newRssPage(\''+link+'\')';
                                             htmlRss+='<span class="audio_list" onclick="newRssPage(\''+link+'\')">'+returnImageHtml(imageUrl)+'<span>'+pageName+'</span></span>';
                                             counter=parseFloat(counter)+1;
                                             
                                                    });
        
        if(parseFloat(counter) == 1)
        {
            window.setTimeout(funcName,10);
            sessionStorage.setItem("singlePage","true");
        }
        else
        {
            setTimeout(function(){
				
                       appendHtml("<div class='page-text'>"+htmlRss+"</div>",'',1);
                       },100);
        }
}



function newRssPage(Rss_url)
{
toaster.openRssReader(Rss_url);
/*
    var wsUrl="http://"+localStorage.getItem("reseller")+"/services/utility-soap#rssfeed";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><rssfeed xmlns="\http://'+localStorage.getItem("reseller")+'/services/utility-soap#rssfeed/\"><link>'+encodeURIComponent(Rss_url)+'</link></rssfeed></soap:Body></soap:Envelope>';
    
    console.log("Saop "+soapRequest);
    
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           CustomRssJson=$(req.responseText).find("return").text();
           console.log("CustomRssJson-->"+CustomRssJson);
           if(CustomRssJson!=null && CustomRssJson.length>6)
           {
           createRssScreen(CustomRssJson);
           }
           else
           {
           alertPopUp(server_response_rss,data_not_found_onserver_rss);
           }
           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           alertPopUp(server_response_rss,data_not_found_onserver_rss);
           console.log("DATARSSERROR "+errorThrown.responseText);
           }
           });
		   */
}

function openRssLink(rssLink)
{
    
    if(!checkNetworkConnection())
    {
        
    }
    else
    {
    
    if(window.localStorage.getItem("layout")=="bottom")
    {
        sessionStorage.setItem("websitePageFlag","true");
    }
    else
    {
        sessionStorage.setItem("websitePageFlag","false");
    }
	if(rssLink != "" || rssLink.length>0)
    {
       if(rssLink.indexOf("yt:video:")!=-1)
  {
   var videoId=rssLink.split(":");
   if (checkNetworkConnection()) {
          window.plugins.youtube.show({ videoid: videoId[2], videoInfo: ""}, function() {}, function() {} );
           }
  }
  else{
   openWebPage(rssLink,2);
  }
	}
    }
}

function createRssScreen(rssData)
{
    var obj = JSON.parse(rssData);
    var htmlrss='';
    for(var i in obj)
    {
        console.log(i + " (" + obj[i]+ ")");
        var title=obj[i].title;
        var author=obj[i].authur;
        var description=obj[i].desc;
        var pubDate=obj[i].pubdate;
        var rssLink=obj[i].link;
        var contentImg=addHttpInLink(obj[i].contentImg);
        if(description!=null)
        {
            description = description.replace(/&(lt|gt);/g, function (strMatch, p1){
                                                return (p1 == "lt")? "<" : ">";
                                                });
             //description = description.replace(/<\/?[^>]+(>|$)/g, "");
            description = description.replace(/(&nbsp;|<([^>]+)>)/ig, "") ;
            description=description.substring(0,200);
			description= $('<div/>').html(description).text();
        }
        else
        {
            description="";
        }
        
        if(author==null)
        {
            author='';
        }
        
        if(pubDate==null)
        {
            pubDate='';
        }
		
		 if(rssLink==null)
         {   
          rssLink="";
         }
         else{
          rssLink=rssLink.trim();
         }
		
		 if(localStorage.getItem("applicationID").indexOf("60d353ce5311")!=-1)
		 {
		 contentImg="";
		 }
		
        //rssLink=rssLink.trim();
		console.log("krishna link>>>>"+rssLink);
        if(rssLink)
        {
			
            //htmlrss+='<div class="review_page_details"><span class="time">'+pubDate+'</span><h3><a onclick="openRssLink(\''+rssLink+'\')">'+title+'</a></h3><h6>'+author+'</h6><p class="rssContent" onclick="createFeedDetail('+(i)+')">'+description+contentImg+'</p></div>';
          htmlrss+='<div class="review_page_details"><span class="time">'+pubDate+'</span><span class="share-icon"><img src="images/share-icon.png" width="16px" height="16px" onclick="shareRssLink(\''+rssLink+'\')"></span><h3><a onclick="openRssLink(\''+rssLink+'\')">'+title+'</a></h3><h6>'+author+'</h6><p class="rssContent" onclick="openRssLink(\''+rssLink+'\')">'+description+contentImg+'</p></div>';
		  setTimeout(function(){ 
         $("#contentHolder").scrollTop(0);
         },1000);
		}
        else
        {
			;
            htmlrss+= '<div class="review_page_details" onclick="createFeedDetail('+(i)+')"><span class="time">'+pubDate+'</span><h3 >'+title+'</h3><h6>'+author+'</h6><p id="rssContent">'+description+contentImg+'</p></div>';
        }
    }
    

    if(sessionStorage.getItem("singlePage")=="true")
    {
		
        forSinglePageView();
        sessionStorage.setItem("history",$.mobile.activePage.attr("id"));
        appendHtml("<div class='page-text'>"+htmlrss+"</div>",'',1);
		$(".rssContent img[height='1']").remove();
         setTimeout(function(){ 
        $("#contentHolder").scrollTop(0);
        },1000);
        
        
    }
    else {
        normalView();
		
        
        sessionStorage.setItem("history",$.mobile.activePage.attr("id"));
        appendHtml("<div class='page-text'>"+htmlrss+"</div>",2,2);
		$(".rssContent img[height='1']").remove();
        
    }
	
    
}

 function shareRssLink(rssLink)
  {
	
	toaster.shareLink(rssLink)
	
	}


function addHttpInLink(link)
{
    if(link != null && link.length > 1)
    {
        var src=$("<p>"+link+"</p>").find("img").attr("src");
        console.log("src="+src);
        var newsrc=src.split('//');
        if(newsrc[0].length < 1)
        {
            link=link.replace("//", "http://");
            return link;
        }
        else
        {
             return link;
        }
    }
    else
    {
        return '';
    }
}

function createFeedDetail(i){
    
    
    /*if(window.localStorage.getItem("layout")=="slidemenu"){
        $("#reload").hide();
        $("#mainback").show();
    }
    else
    {
        $("#logo").hide();
        
        $("#mainback").show();
        $("#reload").show();
    }*/
    var data =JSON.parse(CustomRssJson);
    var title=data[i].title;
    var author=data[i].authur;
    var description=data[i].desc;
    var pubDate=data[i].pubdate;
    var contentImg=addHttpInLink(data[i].contentImg);
    var contentVideo=addHttpInLink(data[i].contentVideo);
    var smallDesc=data[i].smalldesc;
    if(author==null)
    {
        author='';
    }
    
	if(description!=null)
    {
        description = description.replace(/&(lt|gt);/g, function (strMatch, p1){
                                          return (p1 == "lt")? "<" : ">";
                                          });
        //description = description.replace(/<\/?[^>]+(>|$)/g, "");
        description = description.replace(/(&nbsp;|<([^>]+)>)/ig, "") ;
		description= $('<div/>').html(description).text();
    }
	else{
	description = smallDesc;
	}
	
    if(pubDate==null)
    {
        pubDate='';
    }
   
    var htmlDetailRss ='<div class="review_page_details"><span class="time">'+pubDate+'</span><ul><li><h3><a>'+title+'</a></h3><h6>'+author+'</h6><p id="rssContent">'+description+''+contentImg+'<br>'+contentVideo+'</p></li></ul></div>';
    
    
    if(sessionStorage.getItem("singlePage")=="true")
    {
		
    	 appendHtml("<div class='page-text'>"+htmlDetailRss+"</div>",2,2);
        
    }
    else
    {
	
    	 appendHtml("<div class='page-text'>"+htmlDetailRss+"</div>",3,3);
    }
	
}