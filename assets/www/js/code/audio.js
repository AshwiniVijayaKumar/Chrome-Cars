///////////////////////Audio///////////////////////
var RadioName='';
var  sound='';
var  Beatport='';
var  rssData='';
var  Custom='';
var soundFunctionName='';
var nowPlaying="";
var songsList = [];
var AllSongsList = [];
var masterData=$.parseXML(window.sessionStorage.getItem("xml"));
var plsType='';
var valueCustomRadio=0;
var valueSoundCloud=0;
var valueRssFeed=0;
var valueCustomPlaylist=0;
var soundImage="";
var couldnotreadfilegg_audio = "could not read file: ";
var enableAutoPlay;

function getaudio(index)
{
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
	 
	 valueCustomRadio=0;
	  valueSoundCloud=0;
	  valueRssFeed=0;
	  valueCustomPlaylist=0;
	
	  console.log("gourav inside audio");
    var html2='';
    xmlFileData= masterData;
    $xml = $(xmlFileData),
    $audioIndex = $xml.find( "audio[indexval="+index+"]" );
    var audioId=$audioIndex.find("audioId").text().split(',');
	enableAutoPlay=$audioIndex.find("enableAutoPlay").text();
    for(var i=0;i<=audioId.length-1;i++)
    {
        if(audioId[i] == 'soundCloud')
        {
            var soundCloudIndex=0;
            $audioIndex.find("soundCloud").each(function()
                                                {
                                                console.log("@amritesh ,inside sound cloud");
                                                var soundCloudImageUrl = $(this).find("soundCloudImageUrl").text();
                                                
                                                var soundCloudPlayList= $(this).find("soundCloudPlayList").text();
                                                
                                                var soundCloudPlayListId =$(this).find("soundCloudPlayListId").text();
                                                
                                                var soundCloudClientId =$(this).find("soundCloudClientId").text();
                                                var soundCloudCoverImage =$(this).find("soundCloudInnerImage").text();
                                                var soundCloudUserId= $(this).find("soundCloudUserId").text();
                                                var soundcloudImageHtml='';
                                                if (soundCloudPlayList!= null && soundCloudPlayList.length>1)
                                                {
                                                 console.log("@amritesh ,inside sound cloud soundCloudPlayList");
                                                sound = soundCloudPlayList;
                                                }
                                                else
                                                {
                                                 console.log("@amritesh ,inside sound cloud else");
												 /*need to be converted to SA*/
                                                sound="Sound Cloud";
                                                }
                                                if (soundCloudCoverImage!= null & soundCloudCoverImage!= ' ')
                                                {
                                                console.log("@amritesh ,inside sound cloud if");
                                                soundImage = soundCloudCoverImage;
                                                sessionStorage.setItem("soundcloud",soundImage);
                                                }
                                                else
                                                {
                                                console.log("@amritesh ,inside sound cloud else 2"); 
                                                if (soundCloudImageUrl!= null & soundCloudImageUrl!= ' ')
                                                {
                                                sessionStorage.setItem("soundcloud",soundImage);
                                                console.log("@amritesh ,inside sound cloud if2");
                                                }
                                                
                                                }
                                                soundFunctionName='soundcloud';
                                                html2+='<span class="audio_list" onclick="soundcloud('+index+','+valueSoundCloud+')">'+returnImageHtml(soundCloudCoverImage)+' <span>'+sound+'</span></span>';
                                                   soundCloudIndex++;
                                                valueSoundCloud++;
                                                }
                                                
                                                );
            
        }else  if(audioId[i]==='beatPort')
        {
            var valueBeatPort=0;
            
            $audioIndex.find("beatPort").each(function()
                                              {
                                              var beatPortImageUrl = $(this).find("beatPortImageUrl").text();
                                              var beatPortPlaylistName =  $(this).find("beatPortPlaylistName").text();
                                              
                                              var beatPortSelectedAlbumId=  $(this).find("beatPortSelectedAlbumId").text();
                                              
                                              var beatportUrl =  $(this).find("beatportUrl").text();
                                              var beatportImageHtml='';
                                              
                                              if (beatPortPlaylistName != null && beatPortPlaylistName.length>1)
                                              {
                                              Beatport=beatPortPlaylistName;
                                              }
                                              else
                                              {
											   /*need to be converted to SA*/
                                              Beatport="Beatport";
                                              }
                                              
                                              
                                              
                                              if (beatPortImageUrl!= null & beatPortImageUrl!= ' ')
                                              {
                                            	  
                                              sessionStorage.setItem("coverImage",beatPortImage);
                                              }
                                             
                                              soundFunctionName='beatport';
                                              html2+='<span class="audio_list" onclick="beatport('+index+')">'+returnImageHtml(beatPortImageUrl)+'<span>'+Beatport+'</span></span>';
                                              valueBeatPort++;
                                              }
                                              );
            
        }else  if(audioId[i]==='soundRss')
        {
            var soundImageUrl="";
            var valueRssFeed=0;
            $audioIndex.find("soundRss").each(function()
                                              {
                                              var soundRssPlayList =  $(this).find("soundRssPlayList").text();
                                              var soundRssPlayListImageUrl = $(this).find("soundRssInnerImage").text();
                                              var soundRssFeedurl = $(this).find("soundRssFeedurl").text();
                                              var soundRssFeedImageHtml='';
                                              var rssUrl=$(this).find("soundRssFeedurl").text();
                                              if (soundRssPlayList != null && soundRssPlayList.length>1)
                                              {
                                              rssData=soundRssPlayList;
                                              }
                                              else
                                              {
											   /*need to be converted to SA*/
                                              rssData="RSS";
                                              }
                                              if (soundRssPlayListImageUrl!= null & soundRssPlayListImageUrl!= ' ')
                                              {
                                              sessionStorage.setItem("coverImage",soundImageUrl);
                                              }
                                              
                                              soundFunctionName='rssfeed';
                                                html2+="<span class='audio_list' onclick='rssfeed(\""+index+"\",\""+valueRssFeed+"\")'>"+returnImageHtml(soundRssPlayListImageUrl)+"<span onclick='rssfeed("+index+","+valueRssFeed+");'>"+rssData+"</span></span>";
                                             // html2+="<span class='audio_list' onclick='rssfeed(\""+valueRssFeed+"\")'>"+returnImageHtml(soundRssPlayListImageUrl)+"<span onclick='rssfeed("+JSON.stringify(index)+","+JSON.stringify(soundRssPlayList)+","+JSON.stringify(soundRssPlayListImageUrl)+","+JSON.stringify(valueRssFeed)+","+JSON.stringify(soundRssFeedurl)+");'>"+rssData+"</span></span>";
                                              valueRssFeed++;
                                              }
                                              
                                              );
            
        }else  if(audioId[i]==='customPlayList')
        {
        	
        	console.log("gourav inside custom playList");
        	
		
		$audioIndex.find("custom").each(function()
            {
			var j =  $audioIndex.find("custom").index(this);
            var customPlayListImageUrl="";
            var audioCustomBgImage="";
            var audioCustomPlayList =  $(this).find("audioCustomPlayList").text();
            var audioCustomPlayListImageUrl =  $(this).find("soundCustomInnerImage").text();
            
             audioCustomBgImage = $(this).find("audioCustomBgImage").text();
            
            var customPlayListImageHtml='';
            
            if (audioCustomPlayList != null && audioCustomPlayList.length>1)
            {
                Custom=audioCustomPlayList;
            }
            else
            {
			 /*need to be converted to SA*/
                Custom="Custom";
            }
            
             if (audioCustomBgImage!= null && audioCustomBgImage.length>1)
               {
                 customPlayListImageUrl = audioCustomBgImage;
                  sessionStorage.setItem("coverImageCustom"+j,customPlayListImageUrl);
               }
               else
               {
                 customPlayListImageUrl='images/sound_custom_icon.jpg';
                 sessionStorage.setItem("coverImageCustom"+j,"");
               }
            
            if (audioCustomPlayListImageUrl!= null & audioCustomPlayListImageUrl!= ' ')
            {
            sessionStorage.setItem("coverImage",customPlayListImageUrl);
            }
            
            soundFunctionName='custom';
            html2+='<span class="audio_list" onclick="custom('+index+','+j+')">'+returnImageHtml(audioCustomPlayListImageUrl)+'<span>'+Custom+'</span></span>';
        valueCustomPlaylist++;
		});
		}
        else  if(audioId[i]==='dirble')
        {
            var dirbleImage="";
            var audioCustomPlayList = $audioIndex.find("audioRadioPlayList").text();
            var dirbleImageUrl = $audioIndex.find("soundRadioInnerImage").text();
            var dirbleImageHtml='';
            if (audioCustomPlayList != null && audioCustomPlayList.length>1)
            {
                RadioName=audioCustomPlayList;
            }
            else
            {
			 /*need to be converted to SA*/
                RadioName="Dirble";
            }
            if (dirbleImageUrl!= null & dirbleImageUrl!= ' ')
            {
            	
                sessionStorage.setItem("coverImage",dirbleImage);
            }
           
            soundFunctionName='dirbleListValue';
            html2+='<span class="audio_list" onclick="dirbleListValue('+index+')">'+returnImageHtml(dirbleImageUrl)+'<span>'+RadioName+'</span></span>';
            
            
        }
        else  if(audioId[i]==='customradio')
        {
            var customImageUrl="";
            
            $audioIndex.find("customRadio").each(function()
                                                 {
            console.log("workingggggggggggRV")
                                                 plsType=$(this).find("plsType").text();
                                                 var radiorssPlaylist = $(this).find("radiorssPlaylist").text();
												 radiorssPlaylist = radiorssPlaylist.replace(/\<\!\[CDATA\[(.+\]{0}\>{0})\]\]\>/g, '');
                                                 radiorssPlaylist =  radiorssPlaylist.replace(/'/g, '');
                                                 var radiorssPlaylistImageUrl = $(this).find("soundRadioRssInnerImage").text();
                                                  var playerSoundRadioRssInnerImage =$(this).find("playerSoundRadioRssInnerImage").text();
                                                  var customradioImageHtml='';
												 if (radiorssPlaylist != null && radiorssPlaylist.length>1)
                                                 {
                                                 RadioName=radiorssPlaylist;
                                                 }
                                                 else
                                                 {
												  /*need to be converted to SA*/
                                                 RadioName="Radio";
                                                 }
                                                
												 if (radiorssPlaylistImageUrl!= null & radiorssPlaylistImageUrl!= ' ')
                                                 {
                                                 radiorssPlaylistImageUrl = radiorssPlaylistImageUrl;
                                                 }
                                                 else
                                                 {
                                                 radiorssPlaylistImageUrl='images/sound_Cradio_icon.png';
                                                 }
                                                 
                                               if (playerSoundRadioRssInnerImage!= null & playerSoundRadioRssInnerImage!= ' ')
                                                 {
                                                 sessionStorage.setItem("coverImage",playerSoundRadioRssInnerImage);
                                                 }
                                                 else
                                                 {
                                                 sessionStorage.setItem("coverImage","");
                                                 }
                                                 if (plsType== null)
                                                 {
												  
                                                 plsType="shoutcast";
                                                 }
                                                 
                                                 
                                                 
                                                 var coverImage=sessionStorage.getItem("coverImage");
                                                 soundFunctionName='customRadio';
                                                 
                                                 
                                                 
                                                 html2+="<span class='audio_list' onclick='customRadio("+JSON.stringify(index)+","+JSON.stringify(RadioName)+","+JSON.stringify(coverImage)+","+JSON.stringify(valueCustomRadio)+");'>"+returnImageHtml(radiorssPlaylistImageUrl)+"<span>"+RadioName+"</span></span>";
                                                 valueCustomRadio++;
                                                 });
        }
        else  if(audioId[i]==='gaana')
        {
            
            $audioIndex.find("gaana").each(function()
                                           {
                                           var gaanaName = $(this).find("gaanaName").text();
                                           
                                           var link = $(this).find("link").text();
                                           
                                           var gaanaImage =$(this).find("gaanaImage").text();
                                           
                                           
                                           soundFunctionName='openWebPage';
                                           html2+='<span class="audio_list" onclick="openWebPage(\''+link+'\',2)">'+returnImageHtml(gaanaImage)+'<span>'+gaanaName+'</span></span>';
                                           
                                           
                                           valueCustomRadio+=2;
                                           });
            
            
        }
        else  if(audioId[i]==='saavn')
        {
            
            $audioIndex.find("saavn").each(function()
                                           {
                                           var saavnName = $(this).find("saavnName").text();
                                           
                                           var link = $(this).find("link").text();
                                           
                                           var saavnImage =$(this).find("saavnImage").text();
                                           
                                           
                                           soundFunctionName='openWebPage';
                                           html2+='<span class="audio_list" onclick="openWebPage(\''+link+'\',2)">'+returnImageHtml(saavnImage)+'<span>'+saavnName+'</span></span>';
                                           
                                           
                                           valueCustomRadio+=2;
                                           });
            
            
        }
        else  if(audioId[i]==='raaga')
        {
            $audioIndex.find("raaga").each(function()
                                           {
                                           var raagaName = $(this).find("raagaName").text();
                                           
                                           var link = $(this).find("link").text();
                                           
                                           var raagaImage =$(this).find("raagaImage").text();
                                           
                                           soundFunctionName='openWebPage';
                                           html2+='<span class="audio_list" onclick="openWebPage(\''+link+'\',2)">'+returnImageHtml(raagaImage)+'<span>'+raagaName+'</span></span>';
                                           
                                           
                                           valueCustomRadio+=2;
                                           });
            
            
        }
    }
    if(audioId.length == 1)
    {
    	 
        sessionStorage.setItem("fromServicePage","false");
        sessionStorage.setItem("soundFunctionName",1);
        sessionStorage.setItem("fromSingleTrack","true");
        if(soundFunctionName=="customRadio")
        {
            if(valueCustomRadio == 1)
            {
            window[soundFunctionName](index,RadioName,sessionStorage.getItem("coverImage"),0);
				
			if(sessionStorage.getItem("autoLoginStatus") == "YES") {
				
				sessionStorage.setItem("autoLoginStatus", "NO");
				
				onBackKeyDown();
				return false;
				}
			else {
				
				return false;
			}
			   return false;
            }
			else
			{
			appendHtml("<div class='page-text'>"+html2+"</div>",'',1);
			}
			
        }
        else
        {
        	console.log("gourav inside else");
        	
        	console.log("gourav " + valueSoundCloud + " " + valueRssFeed + " " + valueBeatPort + " " + valueCustomPlaylist);
            if(valueSoundCloud == 1 || valueRssFeed == 1 || valueBeatPort == 1 || valueCustomPlaylist==1)
            {
			console.log("gourav inside else if");
            	if(valueSoundCloud==1 || valueRssFeed == 1 || valueBeatPort == 1)
				{
				if(valueSoundCloud==1 || valueRssFeed == 1)
            		{
            			window[soundFunctionName](index,0);
            		}
            		else
            		{
        				window[soundFunctionName](index);

            		}
				}
				else
				{
				window[soundFunctionName](index,0);
				}
            	//return true;
            }
			else
			{
			appendHtml("<div class='page-text'>"+html2+"</div>",'',1);
			}
            
        }
    }
	else
	{
	sessionStorage.setItem('audioStatus',"false");
	appendHtml("<div class='page-text'>"+html2+"</div>",'',1);
	}
    
   
   
}

var hello='';
var data='';
var path='';
var xmldata='';
var playname='';
var Artistname='';
var html='';
var beatimage='';
var list='';
function soundcloud(x,y)
{
    // $('.appypie-loader').show();
    xmlFileData=masterData;
    $xml = $(xmlFileData),
    $audioIndex = $xml.find( "audio[indexval="+x+"]" );
    var playlistType='';
     $audioIndex.find("soundCloud[indexval="+y+"]").each(function()
                                        {
                                        var soundCloudImageUrl = $(this).find("soundCloudImageUrl").text();
                                        var soundCloudPlayList= $(this).find("soundCloudPlayList").text();
                                        var soundCloudPlayListId =$(this).find("soundCloudPlayListId").text();
                                        var soundCloudClientId =$(this).find("soundCloudClientId").text();
                                        var soundCloudUserId= $(this).find("soundCloudUserId").text();
                                        
                                        playname=soundCloudPlayList;
                                        var pathurl= soundCloudPlayListId;
                                        
                                        if(pathurl == '3' )
                                        {
										 
                                        playlistType='favorites';
                                        path='http://api.soundcloud.com/users/'+soundCloudUserId+'/tracks?client_id='+soundCloudClientId;
                                        
                                        }
                                        else if(pathurl == '2')
                                        {
										 
                                        playlistType='favorites';
                                        path='http://api.soundcloud.com/users/'+soundCloudUserId+'/favorites?client_id='+soundCloudClientId;
                                        
                                        }
                                        else
                                        {
                                        path='http://api.soundcloud.com/playlists/'+soundCloudPlayListId+'?client_id='+soundCloudClientId;
                                        }
                                        }
                                        );
    
    
    $.ajax({
           type: "GET",
           url: path,
           contentType: "text/xml",
           dataType: "text",
           success: function(data, status, req) {
           xmldata=jQuery.parseJSON(req.responseText);
           console.log('xmldata--->'+xmldata);
           parseXMLdel(xmldata,playlistType);
           },
           error: function(response, textStatus, errorThrown) {
           
           setTimeout(function() {
                      $('.appypie-loader').hide();
                      }, 1000);
           console.log('in error--->'+errorThrown.responseText);
           }
           });
    
}


function customRadio(x,channalName,imageCoverUrl,y)
{
    console.log("xy"+x+"  "+y);
   // alert(channalName);
   // alert(imageCoverUrl);
    // $('.appypie-loader').show();
    
    
    if(sessionStorage.getItem("fromServicePage")=='true')
    {
    	
        sessionStorage.removeItem("fromServicePage");
        songsList.splice(0,songsList.length);
        songsList.push(sessionStorage.getItem("serviceAudioUrl"));
        songsList.push(sessionStorage.getItem("customPlayListName"));
        
        //alert(songsList)
        
        window.plugins.playerplugin.show({plsTypeValue:'shoutcast',url:songsList,type:'customRadio',imageurl:sessionStorage.getItem("coverImagecustomRadio"),channalNameValue:"",appName:localStorage.getItem("AppName"),enableautoplay:enableAutoPlay}, function() {}, function() {} );
       //  window.location ="musicplayer:" +audioUrlArray+"$"+audioNameArray+"$CustomRadio"+"$"+sessionStorage.getItem("coverImagecustomRadio")+"$"+localStorage.getItem("AppName");
    }
    else
    {
    
    
    
    var songInfo="";
    songsList.splice(0, songsList.length);
    xmlFileData=masterData;
    $xml = $(xmlFileData),
    $audioIndex = $xml.find( "audio[indexval="+x+"]" );
    hello='';
    var radiorssPlaylist ='';
    localStorage.setItem('listCount',1);
	var v=0;
    $audioIndex.find("customRadio").each(function()
                                         {
										  if(v==y)
                                             {
                                         var i =  $audioIndex.find("customRadio").index(this);
                                         radiorssPlaylist = $(this).find("radiorssPlaylist").text();
                                         
                                         var radiorssFeedurl = $(this).find("radiorssFeedurl").text();
                                         var disableAutoalbum = $(this).find("disableAutoalbum").text();
                                         if(radiorssPlaylist != null)
                                         {
                                         title_Radio=radiorssPlaylist;
                                         }
                                         else
                                         {
										  /*need to be converted to SA*/
                                         title_Radio="Radio";
                                         }
                                         var radiotitle = title_Radio.substring(0,31);
                                         var path_radio=radiorssFeedurl;
                                         //alert(path_radio);
                                         document.addEventListener("deviceready", onDeviceReady, false);
                                         function onDeviceReady() {
                                         //if(path_radio.indexOf('.m3u')!=-1)
                                         //{
                                        // path_radio=path_radio.replace(".m3u", "");
                                         //}
                                         songInfo=songInfo+path_radio+",";
                                         }
                                         //alert("rv");
										 
    window.plugins.playerplugin.show({plsTypeValue:plsType,url:songInfo,type:'customRadio',imageurl:imageCoverUrl,channalNameValue:channalName,appName:localStorage.getItem("AppName")+"#####"+disableAutoalbum,enableautoplay:enableAutoPlay}, function() {}, function() {} );
    
										 }
                                          v++;
                                         });
    }
    
}
function MyWebservice_del(path)
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
				parseXMLdelrssfeed(xmldata,'');
                
            }
            else
            {
                console.log("fail" + xhr.responseText);
				 /*need to be converted to SA*/
               
                
                return couldnotreadfilegg_audio+path ;
            }
        }
    };
    
    xhr.open("GET", path, true);
    xhr.send();
    
}
/*
function parseXMLdel(xmldata,playlistType)
{
    if(playlistType)
    {
        var list123=xmldata;
    }
    else
    {
        var list123=xmldata.tracks;
        
    }
    
    songsList.splice(0,songsList.length);
    AllSongsList.splice(0,AllSongsList.length);
    var image='';
    list=list123.length;
    var i=0;
    localStorage.setItem('listCount',list);
    
    var songInfo="";
    
    for(var i=0;i<list;i++)
    {
        var title=list123[i].title;
        console.log("in sound cloud function title-->"+title);
        console.log(title);
		  if(image!="")
            {
           // image=image+','+list123[i].artwork_url;
                image=image+','+list123[i].user.avatar_url;
            }
            else
            {
               // image=list123[i].artwork_url;
                image=list123[i].user.avatar_url;
            }
		if(image != null && image.indexOf("large.jpg")!=-1)
        {
        image=image.replace("large.jpg", "t500x500.jpg");
        }
        data=list123[i].stream_url+'?client_id=42cd85e1c91e123b01f4af732e11bf44';
        description=list123[i].description;
        Artistname=list123[i].user.username;
        var myString = description;
		var myTruncatedString="";
		var titletruncated="";
		if(myString)
		{
        myTruncatedString = myString.substring(0,50);
		}
		if(title)
		{
        titletruncated=title.substring(0,31);
		}
        
        document.addEventListener("deviceready", onDeviceReady, false);
        function onDeviceReady() {
            var song = titletruncated+myTruncatedString;
            songsList.push(song);
            var songCompleteDetail = i+','+data+','+titletruncated+','+myTruncatedString;
            AllSongsList.push(songCompleteDetail);
            songInfo=songInfo+"@@--@@"+song+"#####"+data+"#####"+image;
        }
        
        
    }
    
    
    window.plugins.playerplugin.show({plsTypeValue:'',url:songInfo,type:'soundcloud',imageurl:sessionStorage.getItem("soundcloud"),channalNameValue:"",appName:localStorage.getItem("AppName")}, function() {}, function() {} );
    
}
*/
function parseXMLdel(xmldata,playlistType)
{
    if(playlistType)
    {
        var list123=xmldata;
    }
    else
    {
        var list123=xmldata.tracks;
        
    }
    
    songsList.splice(0,songsList.length);
    AllSongsList.splice(0,AllSongsList.length);
    //var image='';
    list=list123.length;
    var i=0;
    localStorage.setItem('listCount',list);
    
    var songInfo="";
    
    for(var i=0;i<list;i++)
    {
     var image='';
        var title=list123[i].title;
        console.log("in sound cloud function title-->"+title);
        console.log(title);
    if(image!="")
            {
              image=image+','+list123[i].artwork_url;
                //image=image+','+list123[i].user.avatar_url;
            }
            else
            {
                image=list123[i].artwork_url;
                //image=list123[i].user.avatar_url;
            }
  if(image != null && image.indexOf("large.jpg")!=-1)
        {
        image=image.replace("large.jpg", "t500x500.jpg");
        }
        data=list123[i].stream_url+'?client_id=42cd85e1c91e123b01f4af732e11bf44';
        description=list123[i].description;
        Artistname=list123[i].user.username;
        var myString = description;
  var myTruncatedString="";
  var titletruncated="";
  if(myString)
  {
        myTruncatedString = myString.substring(0,50);
  }
  if(title)
  {
        titletruncated=title.substring(0,31);
  }
        
       
  document.addEventListener("deviceready", onDeviceReady, false);
     function onDeviceReady() {
         var song = titletruncated+myTruncatedString;
         songsList.push(song);
         var songCompleteDetail = i+','+data+','+titletruncated+','+myTruncatedString;
         AllSongsList.push(songCompleteDetail);
         songInfo=songInfo+"@@--@@"+song+"#####"+data+"#####"+image;
     }
        
    }
	 
    
    window.plugins.playerplugin.show({plsTypeValue:'',url:songInfo,type:'soundcloud',imageurl:sessionStorage.getItem("soundcloud"),channalNameValue:"",appName:localStorage.getItem("AppName"),enableautoplay:enableAutoPlay}, function() {}, function() {} );
    
}

function dirbleListValue(x)
{
    songsList.splice(0, songsList.length);
    hello='';
    html='';
    xmlFileData= masterData;
    $xml = $(xmlFileData),
    $audioIndex = $xml.find( "audio[indexval="+x+"]" );
    $audioIndex.find("dirble").each(function()
                                    {
                                    var j =  $audioIndex.find("dirble").index(this);
                                    var RadioId = $(this).find("RadioId").text(); 
                                    var RadioCategory = $(this).find("RadioCategory").text();
                                    var RadioSubCategory = $(this).find("RadioSubCategory").text();
                                    var dirblePlayListName = $(this).find("audioRadioPlayList").text(); 
                                    var id=RadioId; 
                                    localStorage.setItem('listCount',1);
                                    var dirbleAudioUrl='http://api.dirble.com/v1/stations/apikey/a6105fd30195bd60aec7ce9263ed26cbe84106ac/id/'+id; 
                                    $.getJSON(dirbleAudioUrl, function(value){
                                              dirbleAudio=value;
                                              var songInfo="";
                                              for(var i=0;i<dirbleAudio.length;i++)
                                              {
                                              var dirbleName=dirbleAudio[i].name;
                                              var truncatedName=dirbleName.substring(0,31);
                                              document.addEventListener("deviceready", onDeviceReady, false);
                                              function onDeviceReady() {
                                              var song = truncatedName;
                                              var url=dirbleAudio[i].streamurl;
                                              /*
                                               if(url.slice(-1)=='/')
                                               {
                                               url = url.substring(0, url.length-1);
                                               console.log("after>>>>>>>"+url);
                                               }
                                               */
                                              songsList.push(song);
                                              songInfo=songInfo+"@@--@@"+truncatedName+"#####"+url+"#####"+"krishna";
                                              }	 
                                              }
                                              //setTimeout(function(){$('.appypie-loader').hide();},1000);
                                              //window.location="AudioPlayer:"+songInfo;
                                              window.plugins.playerplugin.show({plsTypeValue:'',url:songInfo,type:'drible',imageurl:sessionStorage.getItem("coverImage"),channalNameValue:"",appName:localStorage.getItem("AppName"),enableautoplay:enableAutoPlay}, function() {}, function() {} );
                                              }); 
                                    });         
    
}
function custom(x,j)
{

var songInfo="";
if(sessionStorage.getItem("fromServicePage")=='true')
{
    sessionStorage.setItem("soundFunctionName",0);
    var path=sessionStorage.getItem("serviceAudioUrl");
    songInfo=songInfo+"@@--@@"+"no"+"#####"+path+"#####"+"krishna";
    window.plugins.playerplugin.show({plsTypeValue:'',url:songInfo,type:'custom',imageurl:"",channalNameValue:"",appName:localStorage.getItem("AppName"),enableautoplay:enableAutoPlay}, function() {}, function() {} );
}
else{
    // $('.appypie-loader').show();
    songsList.splice(0, songsList.length);
    AllSongsList.splice(0, AllSongsList.length);
    xmlFileData= masterData;
    $xml = $(xmlFileData);
    $audioIndex = $xml.find( "audio[indexval="+x+"]" ).find( "custom[indexval="+(j)+"]" );
    var audioCustomPlayList = $audioIndex.find("audioCustomPlayList").text();
    hello='';
    var countTrack = 0;
    var songInfo="";
    $audioIndex.find("customTrack").each(function()
                                         {
                                         var i =  $audioIndex.find("customTrack").index(this);
                                         title=$(this).find("audioTrackName").text();
                                         data=$(this).find("audioTrackUrl1").text();
                                         description=$(this).find("audioTrackDescription").text();
                                         var myString = description;
                                         var myTruncatedString = myString.substring(0,50);
                                         var titletruncated=title.substring(0,31);
                                         document.addEventListener("deviceready", onDeviceReady, false);
                                         function onDeviceReady() {
                                         var song = titletruncated+" "+myTruncatedString;
                                         songsList.push(song);
                                         var songCompleteDetail = i+','+data+','+titletruncated+','+myTruncatedString;
                                         AllSongsList.push(songCompleteDetail);
                                         songInfo=songInfo+"@@--@@"+song+"#####"+data+"#####"+"krishna";
                                         }
                                         countTrack++;
                                         localStorage.setItem('listCount',countTrack);	 
                                         });
    //setTimeout(function(){$('.appypie-loader').hide();},1000);
    //    window.location="AudioPlayer:"+songInfo;
	console.log("gourav starting player");
    window.plugins.playerplugin.show({plsTypeValue:'',url:songInfo,type:'custom',imageurl:sessionStorage.getItem("coverImageCustom"+j),channalNameValue:"",appName:localStorage.getItem("AppName"),enableautoplay:enableAutoPlay}, function() {}, function() {} );
}
}


function rssfeed(mainindex,newpath)
{   console.log("rssfeed() starts newpath--->"+newpath);
    if(checkNetworkConnection())
    {
        if(sessionStorage.getItem("fromServicePage")=='true')
        {
        	
            sessionStorage.setItem("soundFunctionName",0);
            var path=sessionStorage.getItem("serviceAudioUrl");
            
            MyWebservice_del(path.trim());
            
            
            //setTimeout(function(){ parseXMLdelrssfeed(xmldata,''); }, 3000);
            
            
        }
        else
        {
         var xmlFileData=masterData;
		 var AudioData =xmlFileData.getElementsByTagName('audio');
            var soundCloud = AudioData[mainindex].getElementsByTagName('soundRss');
            var rssPlayListName = soundCloud[newpath].getElementsByTagName('soundRssPlayList')[0].childNodes[0].nodeValue;
            //alert(rssPlayListName.text);
            var path = soundCloud[newpath].getElementsByTagName('soundRssFeedurl')[0].childNodes[0].nodeValue;
            
            var rssAudioDisable =soundCloud[newpath].getElementsByTagName('rssAudioDisable')[0].childNodes[0].nodeValue;
            
            sessionStorage.setItem("rssAudioDisable",rssAudioDisable);
            
            var palyerSoundrssInnerImage =soundCloud[newpath].getElementsByTagName('palyerSoundrssInnerImage')[0].childNodes[0].nodeValue;
            if (palyerSoundrssInnerImage!= null & palyerSoundrssInnerImage!= ' ')
            {
                sessionStorage.setItem("palyerSoundrssInnerImage",palyerSoundrssInnerImage);
            }
            else
            {
                sessionStorage.setItem("palyerSoundrssInnerImage","");
            }
			
			MyWebservice_del(path.trim());
            //parseXMLdelrssfeed(xmldata,'');
        }
    }
    
    
}

/*function rssfeed(x,SoundRssPlayList,soundRssPlayListImageUrl,y,soundRssFeedurl)
{
	
	
		xmlFileData=masterData;
	    $xml = $(xmlFileData),
	    $audioIndex = $xml.find( "audio[indexval="+x+"]" );
	    
	    var v=0;
	    $audioIndex.find("soundRss").each(function()
                {
	    	 if(v==y)
             {
	    		 if(sessionStorage.getItem("fromServicePage")=='true')
	    		    {
	    		        sessionStorage.setItem("soundFunctionName",0);
	    		        var path=sessionStorage.getItem("serviceAudioUrl");
	    		        MyWebservice_del(path.trim());
	    		        parseXMLdelrssfeed(xmldata,rssPlayListName);
	    		        
	    		    }
	    		    else
	    		    {
	    		      
	    		        var rssPlayListName =SoundRssPlayList;
	    		        var path = soundRssFeedurl;
	    		        
	    		        MyWebservice_del(path.trim());
	    		        parseXMLdelrssfeed(xmldata,rssPlayListName);
	    		    }
	    		    
	    		 
             }
	    	v++;
	    	
	    	
                });
	    
    
    
}
*/
function parseXMLdelrssfeed(xmldata,rssPlayListName)
{
	console.log("xmldata>>>>>>>"+xmldata);
    songsList.splice(0, songsList.length);
    AllSongsList.splice(0, AllSongsList.length);
    var list123 = xmldata.getElementsByTagName('item');
    
    var Artistname=xmldata.getElementsByTagName('title')[0].textContent;
    //  var  item=xmldata.getElementsByTagName('item');
    var description="";
    list=list123.length;
    localStorage.setItem('listCount',list);
    var songInfo="";
   // alert(list123.length);
    for(i=0;i<list123.length;i++)
    {
        //var t=1+i;
        title=list123[i].getElementsByTagName('title')[0].textContent;
        if(list123[i].getElementsByTagName('enclosure')[0])
        {
            data=list123[i].getElementsByTagName('enclosure')[0].getAttribute('url');
        }
        else
        {
            continue;
            //data=xmldata.getElementsByTagName('media:content')[i].getAttribute('url');
        }
        if(list123[i].getElementsByTagName('pubDate')[0])
        {
            if(window.localStorage.getItem("applicationID")!="4d31b3660882")
        		{
                description=list123[i].getElementsByTagName('pubDate')[0].textContent;

        		}
        	else
        		{
        		description = "";
        		}
        }
        var myString = description;
        var myTruncatedString ="";
        if(description.length > 0)
        	{
        	myTruncatedString=myString.substring(0,50);
        	}
        var titletruncated=title.substring(0,31);
        document.addEventListener("deviceready", onDeviceReady, false);
        function onDeviceReady() {
            var song = titletruncated+myTruncatedString;
            songsList.push(song);
            var songCompleteDetail = i+','+data+','+titletruncated+','+myTruncatedString;
            AllSongsList.push(songCompleteDetail);
            songInfo=songInfo+"@@--@@"+song+"#####"+data+"#####"+"krishna";
        }	 
    }
    //setTimeout(function(){$('.appypie-loader').hide();},1000);
    //	window.location="AudioPlayer:"+songInfo;
    window.plugins.playerplugin.show({plsTypeValue:'',url:songInfo,type:'soundcloud',imageurl:sessionStorage.getItem("palyerSoundrssInnerImage"),channalNameValue:"",appName:localStorage.getItem("AppName"),enableautoplay:enableAutoPlay}, function() {}, function() {} );
    
}