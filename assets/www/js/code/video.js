var htmlnewvideo = '';
var countVideo = 1;
var videoList = [];
var videoIndexValue = '';
var YoutubeplsDataJson = '';
var htmlVideo = '';
var count_video='';
var error_video = 'Error';
var datanotfound_video = 'Data not found !';
var ops_video = 'Oops!';
var nodatafound_video = 'No data found';
var showmore_video = "Show More";
var serverresponse_video = 'Server Response';
var datanotserver_video = 'Data not found on server !';
var serverdata_video = 'Server does not return data';
var ok_video = 'OK';
var videos_video = "VIDEOS";
var enableShareFlag;
var showShareIcon="block";
function getvideo(index)
{
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    sessionStorage.setItem("singleYouTubePage", "false");
    sessionStorage.setItem("singleVimeoPage", "false");
    sessionStorage.setItem("singleUStreamPage", "false");
    $videoIndex = $(masterData).find("Video[indexval=" + index + "]");
    var videoId = $videoIndex.find("videoId").text().split(',');
	enableShareFlag=$videoIndex.find("enableShare").text();
    var videoPageHtml='';
    var counter=0;
    var funcName='';
    if (videoId == null || videoId == '')
    {
        
        alertPopUp(error_video,datanotfound_video);
        return false;
        
    }
    else
    {
	
	if(enableShareFlag==0)
	{
	showShareIcon="none";
	}
	
        for(x=0;x<videoId.length;x++)
        {
            console.log("videoId[x] ----->"+videoId[x]);
            if(videoId[x] == 'custom')
            {
                console.log("this is custom");
                $videoIndex.find("custom").each(function(){
                                                console.log("this is custom");
                                                var customName=$(this).find("galleryName").text();
                                                var innerImageUrl=$(this).find("galleryInnerImage").text();
                                                var videoCount=$(this).attr('indexval');
                                                videoPageHtml += '<a onclick="customVideosPage(\''+videoCount+'\',\'' + index + '\');" class="appypie-list">'+returnImageHtml(innerImageUrl)+'<span>' + customName + '</span></a>';
                                                funcName='customVideosPage(\''+videoCount+'\',\'' + index + '\');';
                                                counter=parseInt(counter)+1;
                                                console.log("videoPageHtml --->"+videoPageHtml);
                                                });
            }
			else if(videoId[x] == 'Facebook')
            {
                $videoIndex.find("Facebook").each(function(){
                                                console.log("this is custom");
                                                var customName=$(this).find("facebookCustomName").text();
                                                var innerImageUrl=$(this).find("facebookInnerImage").text();
                                                  var username=$(this).find("username").text();

                                                var videoCount=$(this).attr('indexval');
                                                videoPageHtml += '<a onclick="facebookVideosPage(\''+username+'\');" class="appypie-list">'+returnImageHtml(innerImageUrl)+'<span>' + customName + '</span></a>';
                                                funcName='facebookVideosPage(\''+username+'\');';
                                                counter=parseInt(counter)+1;
                                                console.log("videoPageHtml --->"+videoPageHtml);
                                                });
            }
            else
            {
            if(videoId[x] == 'YouTube')
            {
                videoId[x]='Youtube';
                
            }
            else if(videoId[x] == 'Vimeo')
            {
                videoId[x] = 'vimeo';
                if(videoId.length == 1)
                {
                    sessionStorage.setItem("singleVimeoPage", "true");
                }
            }
            else if(videoId[x] == 'UStream')
            {
                videoId[x] = 'ustream';
                
            }
            else if(videoId[x] == 'Rss')
            {
                videoId[x] = 'rssvideo';
                
            }
            else if(videoId[x] == 'ooyala')
            {
                videoId[x] = 'ooyala';
                
            }
            console.log("videoId[x]--->"+videoId[x]);
           
            if($videoIndex.find(""+videoId[x]))
            {
                $videoIndex.find(""+videoId[x]).each(function(){
                	
                	
                if(videoId[x] == 'Youtube')
                {
                    videoId[x]='youtube';
                }
                var customName=$(this).find(videoId[x]+"CustomName").text();
                var innerImageUrl=$(this).find(videoId[x]+"InnerImage").text();
                
                
                if (customName == null || customName == '' || customName.length < 1)
                {
                    customName = videoId[x];
                }
                if(videoId[x] == 'youtube')
                {
                    var list=$(this).find("list").text();
                    var name=$(this).find("username").text();
                    var YoutubePlaylist =$(this).find("YoutubePlsURL").text();
                    
                    funcName='video_view(\''+list+'\',\'' + name + '\',\''+YoutubePlaylist+'\',\''+index+'\');'
                    videoPageHtml += '<a onclick="video_view(\''+list+'\',\'' + name + '\',\''+YoutubePlaylist+'\',\''+index+'\');" class="appypie-list">'+returnImageHtml(innerImageUrl)+'<span>' + customName + '</span></a>';
                }
                else if(videoId[x] == "rssvideo")
                {
                    var rssurl=$(this).find(videoId[x]+"Url").text();
                                                     console.log("this is rssurl-->"+rssurl);
                    funcName='rssView(\''+rssurl.trim()+'\',\'' + index + '\');';
                    videoPageHtml += '<a onclick="rssView(\''+rssurl.trim()+'\',\'' + index + '\');" class="appypie-list">'+returnImageHtml(innerImageUrl)+'<span>' + customName + '</span></a>';
                }
                else if(videoId[x] == "ooyala")
                {
                    var ooyalapcode= $videoIndex.find("ooyalaApikey").text().split(".")[0];
                    var ooyalaembedcode=$(this).find("ooyalaContentId").text();
 
                     console.log("gourav :" + ooyalapcode + " ----" + ooyalaembedcode);
                    
                      funcName='ooyalaview(\''+ooyalapcode+'\',\'' + ooyalaembedcode + '\')';
                    videoPageHtml += '<a onclick="ooyalaview(\''+ooyalapcode+'\',\'' + ooyalaembedcode + '\');" class="appypie-list">'+returnImageHtml(innerImageUrl)+'<span>' + customName + '</span></a>';
                }
                else
                {
                    var url=$(this).find(videoId[x]+"Url").text();
                   
                    funcName='ustream_view(\''+url.trim()+'\',\'' + index + '\',\''+videoId[x]+'\')';
                    videoPageHtml += '<a onclick="ustream_view(\''+url.trim()+'\',\'' + index + '\',\''+videoId[x]+'\');" class="appypie-list">'+returnImageHtml(innerImageUrl)+'<span>' + customName + '</span></a>';
                  
                }
            counter=parseInt(counter)+1;
            });
            }
            }
        }
        
        
        
        if(parseInt(counter) == 1)
        {
		   sessionStorage.setItem("singlefacebookpage","true");
           window.setTimeout(funcName,10);
           sessionStorage.setItem("singleYouTubePage", "true"); 
        }
        else
        {
            console.log("videoPageHtml--->"+videoPageHtml);
            appendHtml("<div class='page-text'>" + videoPageHtml + "</div>",'',1);
           
        }
    }
}


/*
function customVideosPage(pageIndex,index)
{
    
    $videoIndex = $(masterData).find("Video[indexval=" + index + "]");
    $videoIndexXml = $videoIndex.find("custom[indexval=" + pageIndex + "]");
    var htmlVideo='';
    $videoIndexXml.find("gallery").each(function(){
                                        var customCustomName=$(this).find('customCustomName').text();
                                        var customUrl=$(this).find('customUrl').text();
                                        var customPublishDate=$(this).find('customPublishDate').text();
                                        var customInnerImage=$(this).find('customInnerImage').text();
                                        var imageHtml='';
										if (customInnerImage.indexOf('.') != -1)
                                        {
                                         imageHtml ='<img  src="'+ customInnerImage+'"  />';
                                        }
                                         else
                                        {
                                         imageHtml = '<i  class="' + customInnerImage + '" style="color:#000 !important"/></i>';
                                        }
										htmlVideo+='<div class="video"><div class="custom-video" onclick="openRssVideo(\''+customUrl+'\');">'+imageHtml+'</div><span class="share-icon"><img src="images/share-icon.png" width="16px" height="16px" onclick="shareRssLink(\''+customUrl+'\')"></span><h4>' + customCustomName + '</h4><p></p><time>'+customPublishDate+'<time></div>';
										
										});
    
    if(sessionStorage.getItem("singleYouTubePage") == "true")
    {
        appendHtml("<div class='page-text'>" + htmlVideo + "</div>",'',1);
    }
    else
    {
        appendHtml("<div class='page-text'>" + htmlVideo + "</div>",2,2);
    }
    
}
*/
function customVideosPage(pageIndex,index)
{
   
    $videoIndex = $(masterData).find("Video[indexval=" + index + "]");
    $videoIndexXml = $videoIndex.find("custom[indexval=" + pageIndex + "]");
    var htmlVideo='';
     var customUrl='';
    $videoIndexXml.find("gallery").each(function(){
                                    
                                     var customCustomName=$(this).find('customCustomName').text();
                                      customUrl=$(this).find('customUrl').text();
                                      var customPublishDate=$(this).find('customPublishDate').text();
                                      var customInnerImage=$(this).find('customInnerImage').text();
									    if(window.localStorage.getItem("applicationID") == "7072afa9707f")
                                      {
                                          htmlVideo+='<div class="video" ><div class="custom-video" onclick="openRssVideo(\''+customUrl+'\')">'+returnImageHtmlCustomVideo(customInnerImage)+'</div><h4>' + customCustomName + '</h4><p></p><time>'+customPublishDate+'<time></div>';

                                    	  
                                      }
									  
									  else{
									      htmlVideo+='<div class="video" ><div class="custom-video" onclick="openRssVideo(\''+customUrl+'\')">'+returnImageHtmlCustomVideo(customInnerImage)+'</div><span class="share-icon" style="display:'+showShareIcon+'"><img src="images/share-icon.png" width="16px" height="16px" onclick="shareRssLink(\''+customUrl+'\')"></span><h4>' + customCustomName + '</h4><p></p><time>'+customPublishDate+'<time></div>';

									  }
									  
                                        count_video++;
    });
   
    if(count_video==1)
    {
        
        openRssVideo(customUrl,1);
        count_video=0;
        
    }
    
    else{
    if(sessionStorage.getItem("singleYouTubePage") == "true")
    {
        appendHtml("<div class='page-text'>" + htmlVideo + "</div>",'',1);
    }
    else
    {
        appendHtml("<div class='page-text'>" + htmlVideo + "</div>",2,2);
    }
        count_video=0;
    }
    
}
function returnImageHtmlCustomVideo(imageUrl)
{
    var imageHtml='';
    if (imageUrl.indexOf('.') != -1)
    {
        imageHtml ='<img  src="'+ imageUrl+'"  />';
    }
    else
    {
        imageHtml = '<i  class="' + imageUrl + '" style="color:#000 !important"/></i>';
    }
    console.log("imageHtml --->"+imageHtml);
    return imageHtml;
}



function vimeo_view(obj, index)
{
    
    var htmlVideo = '';
    for (var i = 0; i < obj.length; i++)
    {
        var vimeoTitle = obj[i].title;
       // var vimeoLink = obj[i].link;
          var vimeoLink = obj[i].url;
        var vimeoDescription = obj[i].description;
       // var vimeopublishDate = obj[i].publishDate;
        var vimeopublishDate = obj[i].dateCreated;
        var imgSrc = obj[i].imgsrc;
        htmlVideo += '<div class="video" ><img src="' + imgSrc + '" width="112" border="0" onclick="playVimeoVideo(\'' + vimeoLink + '\');"><span class="share-icon" style="display:'+showShareIcon+'"><img src="images/share-icon.png" width="16px" height="16px" onclick="shareRssLink(\''+vimeoLink+'\')"></span><h4>' + vimeoTitle + '</h4><p>' + vimeoDescription + '</p><time>' + vimeopublishDate + '<time></div>';
    }
  
    if (sessionStorage.getItem("singleVimeoPage") == "true")
    {
       
        appendHtml("<div class='page-text'>"+htmlVideo+"</div>",2,2);  
       
    } else
    {
        
        
        appendHtml("<div class='page-text'>"+htmlVideo+"</div>",2,2);  
       
    }
    
}

function playVimeoVideo(link)
{
    if (checkNetworkConnection())
    {
        var vimeoLink = link;
        var vimeoLink = vimeoLink.split('/');
        var vimeoId="";
     if(link.indexOf("rss")==-1)
     {
      vimeoId = vimeoLink[vimeoLink.length - 1];
     }
     else
     {
      vimeoId = vimeoLink[vimeoLink.length - 2];
     }
        window.location ="playvimeo:"+vimeoId;
       
    }
}

function ustream_view(url,indexValue,pageName)
{
   window.location = "removewebsite:";
   var page=1;
    if (checkNetworkConnection())
    {
    	
        if(pageName == 'vimeo')
        {
            
                $('.appypie-loader').show();
                var wsUrl = "http://" + localStorage.getItem("reseller") + "/services/utility-soap#getVimeoJson";
                var soapRequest = '<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getVimeoJson xmlns=\"http://' + localStorage.getItem("reseller") + '/services/utility-soap#getVimeoJson/\"><url>' + url + '</url><page>'+page+'</page><appId>'+localStorage.getItem("applicationID")+'</appId></getVimeoJson></soap:Body></soap:Envelope>';
                console.log("Soap Request " + soapRequest);
                $.ajax({
                       type: "POST",
                       url: wsUrl,
                       contentType: "text/xml",
                       dataType: "text",
                       data: soapRequest,
                       success: function(data, status, req) {
                    	  
                       var eventJSON = $(req.responseText).find("return").text();
                     
                        if(eventJSON=="no data found")
                         {
                        	
                          alertPopUp(ops_video,nodatafound_video);
                         }
                         else
                         {
                          var obj = jQuery.parseJSON(eventJSON);
                          vimeo_view(obj, indexValue,url);
                         }

                       },
                       error: function(response, textStatus, errorThrown)
                       {
                        alertPopUp(error_video,datanotfound_video);
                       console.log(errorThrown.responseText);
                       }
                       });
        }
        else if(pageName == 'm3u8')
		{
		if(url.indexOf("www.youtube.com") != -1)
         {
          //alert("gourav");
          openWebPage(url,1);
         }
		 else
		 {
		toaster. liveVideoPlayMethod(url);
		}
		}
        else{
       if (sessionStorage.getItem("singleUStreamPage") == "true")
        {
        if(url.indexOf(".mp4") > -1 || url.indexOf(".m3u8") > -1
     || url.indexOf(".ogg") > -1 || url.indexOf(".mov") > -1
     || url.indexOf(".3gp") > -1) {
          
          toaster. liveVideoPlayMethod(url);
         }
		 else
		 {
            openWebPage(url,1);
         }
            
        } else
        {
        if(url.indexOf(".mp4") > -1 || url.indexOf(".m3u8") > -1
     || url.indexOf(".ogg") > -1 || url.indexOf(".mov") > -1
     || url.indexOf(".3gp") > -1) {
          
          toaster. liveVideoPlayMethod(url);
         }
		 else
		 {
         openWebPage(url,2);
           }
        }
        }
    }
}

function video_view(list,name,YoutubePlaylist,index)
{
    $('.appypie-loader').show();
    htmlVideo = '';
    videoList.length=0;
    htmlnewvideo = '';
    window.location = "removewebsite:";
    if (checkNetworkConnection())
    {
        if (list == 'channel')
        {
            showmoreoption=1;
            CallYouTubeDATAnew('https://www.googleapis.com/youtube/v3/search?channelId='+name+'&mime=true&part=snippet&maxResults=50&order=date&key=AIzaSyBKoIw22_DHPG00fAEMNPgxQiGZVCYBWS8', index, name);
        }
        else if(list == 'user') {
            CallYouTubeDATAuser('https://www.googleapis.com/youtube/v3/channels?part=contentDetails&maxResults=50&order=date&forUsername=' + name + '&key=AIzaSyA3KxUIBgANTtrwXWJ_qpt4DC4b9kYmAYs', index, name);
        }
         else if (list == 'youtu.be' || list == 'watch')
         {
         ParseYoutubeJson(name, index, 'true');
         }
         else if (list == 'search_query')
         {
         var YoutubePlaylistUrl = 'https://gdata.youtube.com/feeds/api/videos?q=' + name + '&orderby=published&start-index=11&max-results=10&v=2&alt=json';
         $.getJSON(YoutubePlaylistUrl, function(value) {
                   YoutubeplsData = value;
                   ParseYoutubeJson(YoutubeplsData, index, 'false');
                   });
         }else if (list == 'playlist' && (YoutubePlaylist != null || YoutubePlaylist != ''))
         {
          playlistid_new_api=YoutubePlaylist;
            CallYouTubeDATAnew('https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&order=date&playlistId=' + YoutubePlaylist + '&key=AIzaSyA3KxUIBgANTtrwXWJ_qpt4DC4b9kYmAYs', index, name);
         } else
         {
         CallYouTubeDATA('https://gdata.youtube.com/feeds/api/users/' + name + '/subscriptions?v=2.1', index, name);
         }

    }
}

function playYoutubeSong(videoId, videoInfo) {
    
    if (checkNetworkConnection()) {
       window.plugins.youtube.show({ videoid: videoId, videoInfo: videoInfo}, function() {}, function() {} );
    }
}

function ParseYoutubeJson(YoutubeplsData, index, flagValue)
{
  var check=0;
    var htmlVideo = '';
    if (flagValue == 'false')
    {
        var YoutubeplsDataJson = YoutubeplsData.feed;
        
        for (var i = 0; i < YoutubeplsDataJson.entry.length; i++) {
            var YouTubeLink = YoutubeplsDataJson.entry[i].link[0].href;
            var YouTubeLink1 = YouTubeLink.split('=');
            var YouTubeLink2 = YouTubeLink1[1].split('&');
            var publishedTime = YoutubeplsDataJson.entry[i].published["$t"];
            var mediaGroup = "";
            if (YoutubeplsDataJson.entry[i].media$group.media$description) {
                mediaGroup = YoutubeplsDataJson.entry[i].media$group.media$description["$t"];
            } else {
                mediaGroup = "";
            }
            var mediatitle = YoutubeplsDataJson.entry[i].media$group.media$title["$t"];
            mediatitle = mediatitle.split(/"/g).join("");
                                          mediatitle = mediatitle.split("'").join("");
                                          var truncateYoutubeDis = mediaGroup.substring(0, 40);
                                          var youtubeVideoInfo = mediatitle + publishedTime;
										  youtubeVideoInfo=youtubeVideoInfo.replace(/[&\/\\#,+()$~%.'":*?<>{}]/g,'');
										 
                                          htmlVideo += '<div class="video"><img onclick="playYoutubeSong(\'' + YouTubeLink2[0] + '\',\'' + youtubeVideoInfo + '\');" src="http://i.ytimg.com/vi/' + YouTubeLink2[0] + '/default.jpg" width="112" border="0"><span class="share-icon" style="display:'+showShareIcon+'"><img src="images/share-icon.png" width="16px" height="16px" onclick="shareRssLink(\''+ YouTubeLink2[0] +'\')"></span><h4>' + mediatitle + '</h4><p>' + truncateYoutubeDis + '</p><time>' + publishedTime + '<time></div>';
                                          }
                                          }
										  else {
                                          console.log("single video link");
										  check=1;
                                          //htmlVideo += '<div class="video1 single-video"><img onclick="playYoutubeSong(\''+YoutubeplsData+'\',\'\');" src="http://i.ytimg.com/vi/'+YoutubeplsData+'/hqdefault.jpg" width="100%"  border="0"><h4></h4></div>';
                                          setTimeout(function() {
										  $('.appypie-loader').hide();
                                        	  playYoutubeSong(YoutubeplsData,'');
											  
                                                    // window.location="youtubevideo:"+YoutubeplsData;
                                                     }, 1000);
                                         }
                                          if (sessionStorage.getItem("singleYouTubePage") == "true")
                                          {
                                      
                                  	    //appendHtml("<div class='page-text'>"+htmlVideo+"</div>",'',1);  
                                         
                                          } else
                                          {
                                           if(check==0)
										   {
										   appendHtml("<div class='page-text'>"+htmlVideo+"</div>",2,2);  
                                           }
                                          }
}
                                          

                                          function CallYouTubeDATA(Youtube_url, index, name)
                                          {
                                          console.log(Youtube_url);
                                          console.log(name);
                                          if (checkNetworkConnection())
                                          {
                                          $.ajax({
                                                 url: Youtube_url,
                                                 dataType: "text",
                                                 crossDomain: true,
                                                 cache: false,
                                                 success: function(xml) {
                                                 console.log("In success function of channel");
                                                 var count = 0;
                                                 var videoIndex = 0;
                                                 htmlnewvideo = '';
                                                 $(xml).find("entry").each(function() {
                                                                           var published = $(this).find("published").text();
                                                                           var updated = $(this).find("updated").text();
                                                                           var title = $(this).find("title").text();
                                                                           var videoInfo = title + published + updated;
                                                                           var id = $(this).find("id").text();
                                                                           if($(this).find("media\\:description"))
                                                                           {
                                                                           var media_decs=$(this).find("media\\:description").text();
                                                                           }
                                                                           else
                                                                           {
                                                                           var media_decs='';
                                                                           }
                                                                           console.log("media description--->"+media_decs);
																		    videoInfo=videoInfo.replace(/[&\/\\#,+()$~%.'":*?<>{}]/g,'');
                                                                           var n = id.split(":");
                                                                           var videoLink = n[3];
                                                                           console.log(videoLink);
                                                                           videoList.push(videoLink + "__" + videoInfo);
                                                                           
                                                                           var k = updated.split("T");
                                                                           var videoupdated = k[0];
                                                                           
                                                                           htmlnewvideo += '<div class="video" onclick="playYoutubeSong(\''+videoLink+'\',\''+videoInfo+'\');"><img src="http://i.ytimg.com/vi/' + videoLink + '/default.jpg" width="112" border="0"><span class="share-icon" style="display:'+showShareIcon+'"><img src="images/share-icon.png" width="16px" height="16px" onclick="shareRssLink(\''+ videoLink +'\')"></span><h4>' + title + '</h4><p>' +media_decs+ published + '</p><time>' + videoupdated + '<time></div>';
                                                                           
                                                                           count++;
                                                                           countVideo++;
                                                                           videoIndex++;
                                                                           });
                                                 
                                                 if (count > 49) {
                                                 sessionStorage.setItem("TotalVideoCount", count);
                                                 console.log("count--->>" + count + "countVideo--->>>" + countVideo);
                                                 
                                                 CreateYouTubeScreen(name, index);
                                                 } else {
                                                 countVideo = 1;
                                                 sessionStorage.removeItem("TotalVideoCount");
                                                 console.log("countVideo--->"+countVideo);
                                                 CreateYouTubeScreen(name, index);
                                                 
                                                 }
                                                 
                                                 
                                                 },
                                                 error: function(XMLHttpRequest, textStatus, errorThrown) {
                                                 console.log('fail');
                                                 console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
                                                 $('.appypie-loader').hide();
                                                 }
                                                 });
                                          }
                                          }

                                          
                                          function showmore_YouTube(name, indexValue)
                                          {
                                          $('.appypie-loader').show();
                                          $('.show-more').hide();
                                          var Youtube_url = 'https://gdata.youtube.com/feeds/api/users/' + name + '/uploads?start-index=' + countVideo + '&max-results=50&v=2';
                                          CallYouTubeDATA(Youtube_url, indexValue, name);
                                          sessionStorage.setItem("youtubeShowMore", "true");
                                          
                                          }
                                          
                                          function CreateYouTubeScreen(name, index, pageIndex) {
                                         
                                        	  
                                        	  
                                          console.log("countVideo---->"+countVideo);
                                          if (htmlnewvideo) {
                                          if (countVideo != 1) {
                                          var htmlShowMore = '<div id="showMoreButton"><a onclick="showmore_YouTube(\'' + name + '\',\'' + index + '\',\'' + pageIndex + '\');" class="show-more">'+showmore_video+'</a></div>';
                                          } else {
                                          console.log('TotalVideoCount--->'+sessionStorage.getItem("TotalVideoCount"));
                                          var htmlShowMore = '';
                                          }
                                         
                                          if (sessionStorage.getItem("singleYouTubePage") == "true") {
                                        	   appendHtml("<div class='page-text' id='htmlAppendId'>"+htmlnewvideo+htmlShowMore+"</div>",2,1);
                                         
                                         
                                          } else {
                                          appendHtml("<div class='page-text' id='htmlAppendId'>"+htmlnewvideo+htmlShowMore+"</div>",2,2);
                                          }
                                     
                                         
                                          } else
                                          {
                                          alertPopUp(serverresponse_video,datanotserver_video);
                                         /* if (window.localStorage.getItem("layout") != "slidemenu")
                                          {
                                          $("#logo").show();
                                          $("#mainback").hide();
                                          }*/
                                          
                                          }
                                          }
                                          
                                          function youtubeplayer(j) {
										  
										  for(var i=0;i<videoList.length;i++) {
	    var indexVideo=videoList[i].split("__");
		if(j==i) {
			 window.plugins.youtube.show({videoid:indexVideo[0], videoInfo:indexVideo[1]}, function() {}, function() {} );
		}
	}
										  
										  
										  }
                                          
                                          function youtubePlaylistParsing(YoutubeplsData, index, flagValue) {
                                          
                                          var htmlTxt = '';
                                          
                                          if (flagValue == 'false') {
                                          
                                          var youtubeNextLink = '';
                                          
                                          htmlVideo = '';
                                          YoutubeplsDataJson = YoutubeplsData.feed;
                                          if (YoutubeplsDataJson.link[5]) {
                                          var youtubeNextLinkCheck = YoutubeplsDataJson.link[5].rel;
                                          if (youtubeNextLinkCheck == "next") {
                                          youtubeNextLink = YoutubeplsDataJson.link[5].href;
                                          
                                          }
                                          }
                                          
                                          
                                          
                                          for (var i = 0; i < YoutubeplsDataJson.entry.length; i++) {
                                          var YouTubeLink = YoutubeplsDataJson.entry[i].link[0].href;
                                          
                                          var YouTubeLink1 = YouTubeLink.split('=');
                                          var YouTubeLink2 = YouTubeLink1[1].split('&');
                                          var publishedTime = YoutubeplsDataJson.entry[i].published["$t"];
                                          var mediaGroup = "";
                                          if (YoutubeplsDataJson.entry[i].media$group.media$description) {
                                          mediaGroup = YoutubeplsDataJson.entry[i].media$group.media$description["$t"];
                                          } else {
                                          mediaGroup = "";
                                          }
                                          var mediatitle = YoutubeplsDataJson.entry[i].media$group.media$title["$t"];
                                          mediatitle = mediatitle.split(/"/g).join("");
                                                                        mediatitle = mediatitle.split("'").join("");
                                                                        var truncateYoutubeDis = mediaGroup.substring(0, 40);
                                                                        var youtubeVideoInfo = mediatitle + publishedTime;
																		  youtubeVideoInfo=youtubeVideoInfo.replace(/[&\/\\#,+()$~%.'":*?<>{}]/g,'');
                                                                        console.log("Title "+ mediatitle);
                                                                        htmlVideo += '<div class="video"><img onclick="playYoutubeSong(\'' + YouTubeLink2[0] + '\',\'' + youtubeVideoInfo + '\');" src="http://i.ytimg.com/vi/' + YouTubeLink2[0] + '/default.jpg" width="112" border="0"><span class="share-icon" style="display:'+showShareIcon+'"><img src="images/share-icon.png" width="16px" height="16px" onclick="shareRssLink(\''+ YouTubeLink2[0] +'\')"></span><h4>' + mediatitle + '</h4><p>' + truncateYoutubeDis + '</p><time>' + publishedTime + '<time></div>';
                                                                        
                                                                        }
                                                                        
                                                                        
                                                                        if ((typeof youtubeNextLink != 'undefined') && youtubeNextLink.length != 0) {
                                                                        
                                                                        htmlTxt = '<div><a class="show-more" style="width:80%" onclick="showmorePlaylist(\'' + youtubeNextLink + '\',\'' + flagValue + '\');">'+showmore_video+'</a></div>';
                                                                        } else {
                                                                        htmlTxt = '';
                                                                        }
                                                                        
                                                                        }
                                                                        
                                                                        var newhtmlVideo = '';
                                                                        if (flagValue == 'true') {
                                                                        
                                                                        
                                                                        YoutubeplsDataJson = YoutubeplsData.feed;
                                                                        console.log("ShowMore " + YoutubeplsDataJson);
                                                                        var youtubeNextLinkCheck = YoutubeplsDataJson.link[5].rel;
                                                                        if (youtubeNextLinkCheck == "next") {
                                                                        youtubeNextLink = YoutubeplsDataJson.link[5].href;
                                                                        
                                                                        }
                                                                        
                                                                        
                                                                        for (var i = 0; i < YoutubeplsDataJson.entry.length; i++) {
                                                                        var YouTubeLink = YoutubeplsDataJson.entry[i].link[0].href;
                                                                        
                                                                        var YouTubeLink1 = YouTubeLink.split('=');
                                                                        var YouTubeLink2 = YouTubeLink1[1].split('&');
                                                                        var publishedTime = YoutubeplsDataJson.entry[i].published["$t"];
                                                                        var mediaGroup = "";
                                                                        if (YoutubeplsDataJson.entry[i].media$group.media$description) {
                                                                        mediaGroup = YoutubeplsDataJson.entry[i].media$group.media$description["$t"];
                                                                        } else {
                                                                        mediaGroup = "";
                                                                        }
                                                                        var mediatitle = YoutubeplsDataJson.entry[i].media$group.media$title["$t"];
                                                                        mediatitle = mediatitle.split(/"/g).join("");
                                                                                                      mediatitle = mediatitle.split("'").join("");
                                                                                                      var truncateYoutubeDis = mediaGroup.substring(0, 20);
                                                                                                      var youtubeVideoInfo = mediatitle + publishedTime;
                                                                                                       youtubeVideoInfo=youtubeVideoInfo.replace(/[&\/\\#,+()$~%.'":*?<>{}]/g,' '); 
                                                                                                      newhtmlVideo += '<div class="video"><img onclick="playYoutubeSong(\'' + YouTubeLink2[0] + '\',\'' + youtubeVideoInfo + '\');" src="http://i.ytimg.com/vi/' + YouTubeLink2[0] + '/default.jpg" width="112" border="0"><span class="share-icon" style="display:'+showShareIcon+'"><img src="images/share-icon.png" width="16px" height="16px" onclick="shareRssLink(\''+ YouTubeLink2[0] +'\')"></span><h4>' + mediatitle + '</h4><p>' + truncateYoutubeDis + '</p><time>' + publishedTime + '<time></div>';
                                                                                                      
                                                                                                      }
                                                                                                      
                                                                                                      if (typeof youtubeNextLink != 'undefined') {
                                                                                                      
                                                                                                      htmlTxt = '<div><a class="show-more" style="width:80%" onclick="showmorePlaylist(\'' + youtubeNextLink + '\',\'' + flagValue + '\');">'+showmore_video+'</a></div>';
                                                                                                      
                                                                                                      
                                                                                                      } else {
                                                                                                      htmlTxt = '';
                                                                                                      }
                                                                                                      
                                                                                                      
                                                                                                      
                                                                                                      }
                                                                                                      
                                                                                                      if (sessionStorage.getItem("singleYouTubePage") == "true") {
                                                                                                     
                                                                                                      console.log(newhtmlVideo);
                                                                                                      if (flagValue == 'false') {
                                                                                                      
                                                                                                    	  appendHtml("<div class='page-text' id='htmlAppendId'>" + htmlVideo + htmlTxt+"</div>",'',1);
                                                                                                      } else {
                                                                                                      console.log("in video else condition");
                                                                                                      $('.appypie-loader').hide();
                                                                                                      $("#htmlAppendId").append(newhtmlVideo + htmlTxt);
                                                                                                      
                                                                                                      }
                                                                                                      } else {
                                                                                                  
                                                                                                      if (flagValue == 'false') {
                                                                                                     
                                                                                 sessionStorage.setItem("history",$.mobile.activePage.attr("id"));
                                                                                 appendHtml("<div class='page-text' id='htmlAppendId'>" + htmlVideo + htmlTxt+"</div>",2,2);
                                                                                                      
                                                                                                      } else {
                                                                                                      console.log("in video else condition");
                                                                                                      $('.appypie-loader').hide();
                                                                                                      $("#htmlAppendId").append(newhtmlVideo + htmlTxt);
                                                                                                      }
                                                                                                      }
                                                                                                      
                                                                                                      
                                                                                                      }
                                                                                                      
function showmorePlaylist(nextPageUrl, flag) {
$('.show-more').hide();
$('.appypie-loader').show();
$.getJSON(nextPageUrl, function(value) {
    var YoutubeplsData = value;
    
    youtubePlaylistParsing(YoutubeplsData, videoIndexValue, 'true');
    });
}

var nextpageyoutubenewapi=''
function CallYouTubeDATAnew(Youtube_url, index, name)
{

console.log(Youtube_url);
console.log(name);
if (checkNetworkConnection()) {

$.ajax({
 url: Youtube_url,
 dataType: "text",
 crossDomain: true,
 cache: false,
 success: function(xml) {
 var obj = JSON.parse(xml);
 console.log("In success function of channel"+xml);
 if(obj.nextPageToken){
 nextpageyoutubenewapi=obj.nextPageToken;
 }
 else{
 nextpageyoutubenewapi='';
 }
 var count = 0;
 var videoIndex = 0;
 //htmlnewvideo = '';
 for(i=0;i<obj.items.length;i++)
 {
 var published = obj.items[i].snippet.publishedAt;
 var updated = obj.items[i].snippet.publishedAt;
 var title = obj.items[i].snippet.title;
 var videoInfo = title + published;
 var videoLink = '';
 if(obj.items[i].id.videoId)
 {
 videoLink = obj.items[i].id.videoId;
 }
 else if(obj.items[i].snippet.resourceId)
 {
 videoLink = obj.items[i].snippet.resourceId.videoId;
 }
 else
 {
       var thumbnailImage=obj.items[i].snippet.thumbnails.default.url;
       
       
       console.log("thumbnailImage --->"+thumbnailImage);
       videoLink=thumbnailImage.replace('https://i.ytimg.com/vi/','');
       console.log("videoLink --->"+videoLink);
       videoLink=videoLink.replace('/default.jpg','');
       console.log("videoLink 2--->"+videoLink);
 }
 var media_decs=obj.items[i].snippet.description;
 videoList.push(videoLink + "__" + videoInfo);
 //title=title.replace(/[&\/\\#,+()$~%.'":*?<>{}]/g,'');
 videoInfo=videoInfo.replace(/[&\/\\#,+()$~%.'":*?<>{}]/g,' ');
 var k = updated.split("T");
 var videoupdated = k[0];
  var share_link="";
										  if(videoLink!=null && videoLink!=""){
											share_link="https://www.youtube.com/watch?v="+videoLink;
										  }else{
											share_link="YouTube id: "+videoLink;
										  }
 htmlnewvideo += '<div class="video"><img src="http://i.ytimg.com/vi/' + videoLink + '/default.jpg" width="112" border="0"  onclick="playYoutubeSong(\''+videoLink+'\',\''+videoInfo+'\');"><span class="share-icon" style="display:'+showShareIcon+'"><img src="images/share-icon.png" width="16px" height="16px" onclick="shareRssLink(\''+share_link+'\')"></span><h4>' + title + '</h4><p>' +media_decs+ published + '</p><time>' + videoupdated + '<time></div>';
 
 count++;
 countVideo++;
 videoIndex++;
 }
 if (count > 49) {
 sessionStorage.setItem("TotalVideoCount", count);
 } else {
 countVideo = 1;
 sessionStorage.removeItem("TotalVideoCount");
 }
 CreateYouTubeScreen_new(name, index);
 
 },
 error: function(XMLHttpRequest, textStatus, errorThrown) {
 console.log('fail');
 console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
 $('.appypie-loader').hide();
 }
 });
}

}
                                                                                                      
function CreateYouTubeScreen_new(name, index, pageIndex) {
if (htmlnewvideo) {
if (nextpageyoutubenewapi != '') {
var htmlShowMore = '<div id="showMoreButton"><a onclick="showmore_YouTube_new(\'' + name + '\',\'' + index + '\',\'' + pageIndex + '\');" class="show-more">'+showmore_video+'</a></div>';
} else
{
var htmlShowMore = '';
}
console.log("Gaurav ->"+htmlnewvideo);
if (sessionStorage.getItem("singleYouTubePage") == "true") {
	appendHtml("<div class='page-text' id='htmlAppendId'>" + htmlnewvideo + htmlShowMore + "</div>",2,1);
}
else {
	appendHtml("<div class='page-text' id='htmlAppendId'>" + htmlnewvideo + htmlShowMore + "</div>",2,2);
}

} else {
$('.appypie-loader').hide();



navigator.notification.alert(
		datanotserver_video,
                           alertDismissed,
                           serverdata_video,
                           ok_video
                           );

}
}
                                                                                                      
function showmore_YouTube_new(name, indexValue) {


var Youtube_url='';
if(showmoreoption==1)
{
Youtube_url = 'https://www.googleapis.com/youtube/v3/search?channelId='+name+'&mime=true&part=snippet&maxResults=50&pageToken='+nextpageyoutubenewapi+'&order=date&key=AIzaSyA3KxUIBgANTtrwXWJ_qpt4DC4b9kYmAYs';
CallYouTubeDATAnew(Youtube_url, indexValue, name);
}
else
{
CallYouTubeDATAnew('https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&order=date&playlistId=' + playlistid_new_api + '&pageToken='+nextpageyoutubenewapi+'&key=AIzaSyA3KxUIBgANTtrwXWJ_qpt4DC4b9kYmAYs', indexValue, name);

}
sessionStorage.setItem("youtubeShowMore", "true");

}
var showmoreoption=0;
var playlistid_new_api='';
function CallYouTubeDATAuser(Youtube_url, index, name) {
showmoreoption=1;
if (!checkNetworkConnection()) {

} else {
$.ajax({
     url: Youtube_url,
     dataType: "text",
     crossDomain: true,
     cache: false,
     success: function(xml) {
     var obj = JSON.parse(xml);
     console.log("In success function of channel"+xml);
     playlistid_new_api=obj.items[0].contentDetails.relatedPlaylists.uploads;
     CallYouTubeDATAnew('https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&order=date&playlistId=' + obj.items[0].contentDetails.relatedPlaylists.uploads + '&key=AIzaSyA3KxUIBgANTtrwXWJ_qpt4DC4b9kYmAYs', index, name);
     
     
     },
     error: function(XMLHttpRequest, textStatus, errorThrown) {
     console.log('fail');
     console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
     $('.appypie-loader').hide();
     }
     });
}
}

function openRssVideo(url,count)
{ 
    var checkhttp=url.indexOf("http://");
  
    if (sessionStorage.getItem("singleVimeoPage") == "true")
    {
  if(url.indexOf(".mp4") > -1 || url.indexOf(".m3u8") > -1
   || url.indexOf(".ogg") > -1 || url.indexOf(".mov") > -1
   || url.indexOf(".3gp") > -1 || url.indexOf("rtsp") > -1  || url.indexOf("mpg")  > -1) 
   {
     
     toaster. liveVideoPlayMethod(url);
   }
  else
  {
   openWebPage(url,1);
  }
    }
    else
    {
 
  if(url.indexOf(".mp4") > -1 || url.indexOf(".m3u8") > -1
   || url.indexOf(".ogg") > -1 || url.indexOf(".mov") > -1
   || url.indexOf(".3gp") > -1 || url.indexOf("rtsp") > -1 || url.indexOf("mpg")  > -1) 
  {          
    toaster. liveVideoPlayMethod(url);
  }
  else if(checkhttp == -1 && localStorage.getItem("applicationID")=="1c3e171eadbb")
  {
   alert("Url not supported.");
  }  
  else
  {
    if(count)
    openWebPage(url,1);
    else
    openWebPage(url,2);
  }   
    }
}

function rssView(url,index)
{
    MyWebservice_delVider(url);
    console.log(xmldata);
	setTimeout(function(){
    parseVideoRssFeed(xmldata);
	},1000);
}

function MyWebservice_delVider(path)
{
  if(path.indexOf("http://scoutmastercg.com/feed/podcast/")!=-1)
  {
  path="feeds.feedburner.com/scoutmasterknowhow";
  }
    hello='';
    var xhr = new XMLHttpRequest();
    xhr.overrideMimeType('text/xml');
    
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState==4)
        {
            if (xhr.status==200&&xhr.responseText!=null)
            {
                xmldata=xhr.responseXML;
                console.log(xhr.responseText);
                console.log("success");
				//parseXMLdelrssfeed(xmldata,'');
                
            }
            else
            {
                console.log("fail" + xhr.responseText);
				 /*need to be converted to SA*/
                return "could not read file: "+path ;
            }
        }
    };
    
    xhr.open("GET", path, true);
    xhr.send();
    
}

function ooyalaview(pcode,embedcode)
{
	
   toaster.openOoyalaVideoPlayer(pcode,embedcode);
}

 function parseVideoRssFeed(xmldata)
{
    if(checkNetworkConnection())
    {
        console.log("parseXMLdelrssfeed() xmldata --->"+xmldata);
        var list123=xmldata.getElementsByTagName('enclosure');
        var Artistname=xmldata.getElementsByTagName('title')[0].textContent;
        console.log('list123 --->'+Artistname);
        var htmlVideo='';
        
        var item=xmldata.getElementsByTagName('item');
        for(i=0;i<item.length;i++)
        {
            console.log(i);
            var t=1+i;
            var description="";
            var title=item[i].getElementsByTagName('title')[0].textContent;
            console.log("Song "+title);
            if(xmldata.getElementsByTagName('enclosure')[i])
            {
                data=xmldata.getElementsByTagName('enclosure')[i].getAttribute('url');
            }
            else
            {
                //continue;
            }
            
            if(xmldata.getElementsByTagName('pubDate')[i])
            {
                description=xmldata.getElementsByTagName('pubDate')[i].textContent;
            }
            var thumbnail=$(xmldata).find("item").eq(i).find("media\\:thumbnail, thumbnail").attr("url");
            var videoLink=$(xmldata).find("item").eq(i).find("media\\:content, content").eq(1).attr("url");
            if(!videoLink)
            {
            videoLink=$(xmldata).find("item").eq(i).find("media\\:content, content").attr("url");
            }
			if(!videoLink)
             {
              videoLink=item[i].getElementsByTagName('guid')[0].textContent;
             }
			console.log(videoLink);
            if(thumbnail===undefined){
				htmlVideo+='<div class="video"><span class="share-icon" style="display:'+showShareIcon+'"><img src="images/share-icon.png" width="16px" height="16px" onclick="shareRssLink(\''+videoLink+'\')"></span><h4  onclick="openRssVideo(\''+videoLink+'\');">' + title + '</h4><p></p><time>'+description+'<time></div>';
			}else{
				htmlVideo+='<div class="video"><img src="' + thumbnail + '" width="112" border="0" onclick="openRssVideo(\''+videoLink+'\');"><span class="share-icon" style="display:'+showShareIcon+'"><img src="images/share-icon.png" width="16px" height="16px" onclick="shareRssLink(\''+videoLink+'\')"></span><h4  onclick="openRssVideo(\''+videoLink+'\');">' + title + '</h4><p></p><time>'+description+'<time></div>';
			}
        }
        
        
        if(sessionStorage.getItem("singleYouTubePage") == "true")
        {
            appendHtml("<div class='page-text'>" + htmlVideo + "</div>",'',1);
        }
        else
        {
            appendHtml("<div class='page-text'>" + htmlVideo + "</div>",2,2);
        }
    }
}

function shareRssLink(link){
	toaster.shareNotes(link);
}


function openfacebookVideo(link)
{
                               
    if (sessionStorage.getItem("singlefacebookpage") == "true")
    {
            openWebPage(link,2);
    }
    else
    {
            openWebPage(link,3);
    }

    setTimeout(function()
          {
          window.location="exitmusicplayer:";
          }, 100);
}


function facebookVideosPage(username)
{
    $('.appypie-loader').show();
    var accesstoken='';
    var profile_id='';
    var urlfacebook='https://graph.facebook.com/oauth/access_token?grant_type=client_credentials&client_id=969945853078467&client_secret=697a9a3de5bdf75c11c22c9cd1989079';
    $.ajax({
           url: urlfacebook,
           dataType: "text",
           crossDomain: true,
           cache: false,
           success: function(xml) {
           accesstoken=JSON.stringify(xml);
           console.log("Video : " + JSON.stringify(xml));
           var temp="https://graph.facebook.com/"+username+"?fields=posts&"+accesstoken;
           temp = temp.replace(/"/g,'');
                               
                               $.ajax({
                                      url: temp,
                                      dataType: "text",
                                      crossDomain: true,
                                      cache: false,
                                      success: function(xml) {
                                      xml = JSON.parse(xml);
                                      profile_id=xml.id;
                                      console.log("Video : " + JSON.stringify(xml));
                                      var tempval="https://graph.facebook.com/"+profile_id+"/videos?fields=picture&"+accesstoken;
                                      tempval = tempval.replace(/"/g,'');
                                                                $.ajax({
                                                                       url: tempval,
                                                                       dataType: "text",
                                                                       crossDomain: true,
                                                                       cache: false,
                                                                       success: function(xml) {
                                                                       console.log("Video : " + JSON.stringify(xml));
                                                                       dataMain=JSON.parse(xml);
                                                                       var newhtml='';
                                                                       for(var i=0;i<dataMain.data.length;i++)
                                                                       {
                                                                       var videoLink='https://www.facebook.com/video/embed?video_id='+dataMain.data[i].id;
                                                                       var thumbnail= dataMain.data[i].picture
                                                                       newhtml += '<a onclick="openfacebookVideo(\''+videoLink+'\');" style=\"background-image:url('+thumbnail+')\" class=\"photo-img customphoto-img\"></a>';
                                                                       //newhtml+='<div class="video" ><img src="'+thumbnail+'" width="112" border="0" onclick="openfacebookVideo(\''+videoLink+'\')"><span class="share-icon"><img src="images/share-icon.png" width="16px" height="16px" onclick="shareRssLink(\''+videoLink+'\')"></span><h4></h4><p></p><time><time></div>';
                                                                       }
                                                                       
                                                                       var checkObjectNext=""+dataMain.paging.next;
                                                                      
                                                                       
                                                                       var next_id =dataMain.paging.cursors.after;
                                                                       htmlTXT='<section><div class="instagram-user">\
                                                                       <div class="instagram-user-details" style="margin: 0; width: 100%; padding: 10px; box-sizing: border-box; min-height: 70px; position: relative; ">\
                                                                       <h3>'+username+'</h3> \
                                                                       <div class="instagram_options" style="float: right; margin: 0px; right: 10px; position: absolute; top: 10px"> \
                                                                       <div class="insta_photo"><div id="count">'+dataMain.data.length+' </div><span>'+videos_video+'</span></div> \
                                                                       </div></div></div></section>';
//                                                                       var showmore='';
                                                                       var showmore='<div id="showMoreButton"><a onclick="fbnext(\'' + next_id + '\',\'' + tempval + '\');" class="show-more">'+showmore_video+'</a></div>';
                                                                       if(sessionStorage.getItem("singlefacebookpage")=="true")
                                                                       {
                                                                        if(checkObjectNext=="undefined")
                                                                       {
                                                                            appendHtml(htmlTXT+newhtml,'',1);
                                                                       }
                                                                       else{
                                                                      
                                                                                appendHtml(htmlTXT+newhtml+showmore,'',1);
                                                                       }
                                                                       }
                                                                       else
                                                                       {
                                                                        if(checkObjectNext=="undefined")
                                                                       {
                                                                                appendHtml(htmlTXT+newhtml,2,2);
                                                                       }
                                                                       else{
                                                                       
                                                                                appendHtml(htmlTXT+'<div id="next_video">'+newhtml+'</div>'+showmore,2,2);
                                                                       }
                                                                      
                                                                       }
                                                                       console.log("------html for the append--"+htmlTXT+'<div id="next_video">'+newhtml+'</div>'+showmore);
                                                                       
                                                                       },
                                                                       error: function(XMLHttpRequest, textStatus, errorThrown) {
                                                                       console.log('fail');
                                                                       console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
                                                                       $('.appypie-loader').hide();
                                                                       }
                                                                       });
                                                                },
                                                                error: function(XMLHttpRequest, textStatus, errorThrown) {
                                                                console.log('fail');
                                                                console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
                                                                $('.appypie-loader').hide();
                                                                }
                                                                });
                                      },
                                      error: function(XMLHttpRequest, textStatus, errorThrown) {
                                      console.log('fail');
                                      console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
                                      $('.appypie-loader').hide();
                                      }
                                      });
  //change by dheeraj                             }

                       }
					   function fbnext(id,url)
{
var next_url = url+'&after='+id;
console.log("------next url--",next_url);
                       $.ajax({
                              url: next_url,
                              dataType: "text",
                              crossDomain: true,
                              cache: false,
                              success: function(xml) {
                              console.log("Videonext far5av uyudbjysekkdfgh-----: " + JSON.stringify(xml));
                              dataMain=JSON.parse(xml);
                              var newhtml='';
                            var next_id =dataMain.paging.cursors.after;
                              for(var i=0;i<dataMain.data.length;i++)
                              {
                              var videoLink='https://www.facebook.com/video/embed?video_id='+dataMain.data[i].id;
                              var thumbnail= dataMain.data[i].picture
                              newhtml += '<a onclick="openfacebookVideo(\''+videoLink+'\');" style=\"background-image:url('+thumbnail+')\" class=\"photo-img customphoto-img\"></a>';
                                                                    }
                              $("#next_video").append(newhtml);
                              var checkObjectNext=""+dataMain.paging.next;
                              if(checkObjectNext == "undefined")
                              {
                             
                                $("#showMoreButton").hide();
                              }
                              else{
                                var showmore='<div id="showMoreButton"><a onclick="fbnext(\'' + next_id + '\',\'' + url + '\');" class="show-more">'+showmore_video+'</a></div>';
                              $("#showMoreButton").html(showmore);
                              
                              }
                             var length= $("#count").html();
                              length = parseInt(length);
                              var add = length + dataMain.data.length;
                              $("#count").html(add);
                                                                   },error: function(XMLHttpRequest, textStatus, errorThrown) {
                              console.log('fail');
                              console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
                              $('.appypie-loader').hide();
                              }
                              });
}