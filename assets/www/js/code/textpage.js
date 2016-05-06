//////////////////////////////Text Page//////////////////////////
var imageSavedUrl = [];
var videoSavedUrl = "";
function gettextpage(index) {var textpageHTML="";
//imageSavedUrl="";
videoSavedUrl="";
var summaryText="";
var imageUrlText="";
var videoUrlText="";
var textPageImage=new Array();
var textPageVideo=new Array();
 var fileTypeArray=new Array();
$textpageXmlData= $(masterData).find("TextPage[indexval=" + index + "]");
console.log("-=-=-=-=-=-=-=----"+$textpageXmlData.text());
var textstyle=$textpageXmlData.find('pageStyle').text();
                                 
                                 var title=($textpageXmlData.find("header").text());
                                 $summary=$textpageXmlData.find("summary");
                                     console.log("-=-=summary=-=-=-=-=----"+$summary.text());
                                 $.each($summary, function() {
                                        summaryText+=$(this).text()+"@#";
                                        });

                                 $fileType=$textpageXmlData.find("fileType");

                                 $.each($fileType, function()
                                        {
                                        fileTypeArray[fileTypeArray.length]=$(this).text();
                                            if($(this).text()=="image")
                                            {
            
                                             var image=$textpageXmlData.find("image").text();
                                             $imageUrl=$textpageXmlData.find("image");
                                             $.each($imageUrl, function() {
                                                    imageUrlText+=$(this).text()+"@#";
                                                    });
                                                  textPageImage= imageUrlText.substring(0,imageUrlText.length-2).split("@#");
                                            }
                                            else if($(this).text()=="none")
                                            {
                                        
                                        }
                                        else if($(this).text()=="video")
                                        {
                                        var video=$textpageXmlData.find("video").text();
                                        console.log("epub video url-1232123--"+video);
                                        $videoUrl=$textpageXmlData.find("video");
                                        $.each($videoUrl, function() {
                                               videoUrlText+=$(this).text()+"@#";
                                               });
                                        textPageVideo= videoUrlText.substring(0,videoUrlText.length-2).split("@#");
                                        }
                                        else{
                                        
                                            var video=$textpageXmlData.find("videoUrl").text();
                                        
                                            $videoUrl=$textpageXmlData.find("videoUrl");
                                            $.each($videoUrl, function() {
                                                   videoUrlText+=$(this).text()+"@#";
                                                   });
                                                 textPageVideo= videoUrlText.substring(0,videoUrlText.length-2).split("@#");
                                            }
//                                        else if($(this).text()=="youtube")
//                                        {
//                                       alert("youtube");
//                                        var video=$textpageXmlData.find("videoUrl").text();
//                                         alert("youtube---"+video);
//                                        
//                                        $videoUrl=$textpageXmlData.find("videoUrl");
//                                       
//                                        $.each($videoUrl, function() {
//                                               videoUrlText+=$(this).text()+"@#";
//                                                console.log("youtube---"+videoUrlText);
//                                               });
//                                        textPageVideo= videoUrlText.substring(0,videoUrlText.length-2).split("@#");
//                                        }
                                        
                                         });

                                 var textPageDescription= summaryText.substring(0,summaryText.length-2).split("@#");

                                 for(var i=0;i<textPageDescription.length;i++)
                                 {
                                  
                                 if(textPageImage[i]!=''||textPageVideo[i]!=''){
                                     
                                     if(fileTypeArray[i]=="image")
                                     {
									 imageSavedUrl[i] = textPageImage[i];
                                         //imageSavedUrl+=textPageImage[i];
                                         //imageSavedUrl+=",";
                                             console.log("header size text page --->"+sessionStorage.getItem('appPageHsize'));
                                         if(i==0){
                                             if(textstyle!="bottom")
                                            	{
                                                 
                                                    textpageHTML+='<div class="service-details" style="display:block !important; clear: both; margin: 10px 0px; overflow: hidden;color:'+sessionStorage.getItem('pageTextColor')+' !improtant;"><h2 class="'+sessionStorage.getItem('appPageHsize')+'" style="text-align:'+sessionStorage.getItem('pageIndentation')+' !important">'+title+'</h2><br><img onclick="openImageFromNative('+i+');" src="'+textPageImage[i]+'" class="textParaImg"><div class="textPara">' + textPageDescription[i] +'</div></div>';
                                                }
                                             else
                                             {
                                                 textpageHTML+='<div class="service-details" style="display:block !important; clear: both; margin: 10px 0px; overflow: hidden;color:'+sessionStorage.getItem('pageTextColor')+' !improtant;"><h2 class="'+sessionStorage.getItem('appPageHsize')+'" style="text-align:'+sessionStorage.getItem('pageIndentation')+' !important">'+title+'</h2><br><div class="textPara">' + textPageDescription[i] +'</div><img onclick="openImageFromNative('+i+');" src="'+textPageImage[i]+'" class="textParaImg"></div>';
                                             }

                                         }
                                         else {
                                             
                                             if(textstyle!="bottom")
                                             {
                                                 textpageHTML+='<div class="service-details" style="display:block !important; clear: both; margin: 10px 0px; overflow: hidden;color:'+sessionStorage.getItem('pageTextColor')+'; !improtant" class="'+sessionStorage.getItem('appPageHsize')+'"><img onclick="openImageFromNative('+i+');" class="textParaImg" src="'+textPageImage[i]+'"><div class="textPara">' + textPageDescription[i] +'</div></div>';
                                             }
                                             else
                                             {
                                                 textpageHTML+='<div class="service-details" style="display:block !important; clear: both; margin: 10px 0px; overflow: hidden;color:'+sessionStorage.getItem('pageTextColor')+'; !improtant" class="'+sessionStorage.getItem('appPageHsize')+'"><div class="textPara">' + textPageDescription[i] +'</div><img onclick="openImageFromNative('+i+');" class="textParaImg" src="'+textPageImage[i]+'"></div>';
                                             }
                                         
                                         }
                                 
                                     }
                                     else if(fileTypeArray[i]=="video"){
                           
                                         videoSavedUrl+=textPageVideo[i];
                                         videoSavedUrl+=",";
                                         console.log("header size text page --->"+sessionStorage.getItem('appPageHsize'));
                                        if(i==0){
                                            
                                            if(textstyle=="bottom")
                                            {
                                              
                                                console.log("video page------url--"+textPageVideo[i]);
                                                textpageHTML+='<div class="service-details" style="display:block !important; clear: both; margin: 10px 0px; overflow: hidden;color:'+sessionStorage.getItem('pageTextColor')+' !improtant;"><h2 class="'+sessionStorage.getItem('appPageHsize')+'" style="text-align:'+sessionStorage.getItem('pageIndentation')+' !important">'+title+'</h2><br><div class="textPara">' + textPageDescription[i] +'</div><video width="100%" height="100%" src='+textPageVideo[i]+' controls>\</video></div>';
                                            }
                                            else
                                            {
                                               
                                                 console.log("video page------url--"+textPageVideo[i]);
                                               textpageHTML+='<div class="service-details" style="display:block !important; clear: both; margin: 10px 0px; overflow: hidden;color:'+sessionStorage.getItem('pageTextColor')+' !improtant;"><h2 class="'+sessionStorage.getItem('appPageHsize')+'" style="text-align:'+sessionStorage.getItem('pageIndentation')+' !important">'+title+'</h2><br><video width="100%" height="100%" src='+textPageVideo[i]+' controls>\</video><div class="textPara">' + textPageDescription[i] +'</div></div>';
                                            }
                                            
                                         }
                                         else {
                                             
                                             if(textstyle=="bottom")
                                             {
                                                 
                                                  console.log("video page------url--"+textPageVideo[i]);
                                                 textpageHTML+='<div class="service-details" style="display:block !important; clear: both; margin: 10px 0px; overflow: hidden;color:'+sessionStorage.getItem('pageTextColor')+'; !improtant" class="'+sessionStorage.getItem('appPageHsize')+'"><div class="textPara">' + textPageDescription[i] +'</div><video width="100%" height="100%" src='+textPageVideo[i]+' controls>\</video></div>';
                                             }
                                             else
                                             {
                                                 
                                                  console.log("video page------url--"+textPageVideo[i]);
                                                 textpageHTML+='<div class="service-details" style="display:block !important; clear: both; margin: 10px 0px; overflow: hidden;color:'+sessionStorage.getItem('pageTextColor')+'; !improtant" class="'+sessionStorage.getItem('appPageHsize')+'"><video width="100%" height="100%" src='+textPageVideo[i]+' controls>\</video><div class="textPara">' + textPageDescription[i] +'</div></div>';
                                             }
                                             
                                         }
                                     
                                     }
                                     else if(fileTypeArray[i]=="vimeo")
                                     {
                                         
                                         var vimeoUrl = textPageVideo[i];
                                         if (i == 0) {
                                             if(textstyle=="bottom")
                                             {
                                                 textpageHTML += '<div class="service-details" style="display:block !important; clear: both; margin: 10px 0px; overflow: hidden;color:'
                                                 + sessionStorage.getItem('pageTextColor')
                                                 + ' !improtant;"><h2 class="'
                                                 + sessionStorage.getItem('appPageHsize')
                                                 + '" style="text-align:'
                                                 + sessionStorage.getItem('pageIndentation')
                                                 + ' !important">'
                                                 + title
                                                 + '</h2><br><div class="textPara">'
                                                 + textPageDescription[i] + '</div><button class="youtube-btn"  onclick="playVimeoVideo(\''+vimeoUrl+'\')"><img width="" height="" src="images/vimeo_Pic.png"  /><span></span></button></div>';

                                             }
                                             else{
                                                 textpageHTML += '<div class="service-details" style="display:block !important; clear: both; margin: 10px 0px; overflow: hidden;color:'
                                             + sessionStorage.getItem('pageTextColor')
                                             + ' !improtant;"><h2 class="'
                                             + sessionStorage.getItem('appPageHsize')
                                             + '" style="text-align:'
                                             + sessionStorage.getItem('pageIndentation')
                                             + ' !important">'
                                             + title
                                             + '</h2><br><button class="youtube-btn"  onclick="playVimeoVideo(\''+vimeoUrl+'\')"><img width="" height="" src="images/vimeo_Pic.png"  /><span></span></button><div class="textPara">'
                                             + textPageDescription[i] + '</div></div>';
                                             }
                                         }
                                         else {
                                             if(textstyle=="bottom")
                                             {
                                                 textpageHTML += '<div class="service-details" style="display:block !important; clear: both; margin: 10px 0px; overflow: hidden;color:'
                                                 + sessionStorage.getItem('pageTextColor')
                                                 + '; !improtant" class="'
                                                 + sessionStorage.getItem('appPageHsize')
                                                 + '"><div class="textPara">'
                                                 + textPageDescription[i] + '</div><button class="youtube-btn"  onclick="playVimeoVideo(\''+vimeoUrl+'\')"><img width="" height="" src="images/vimeo_Pic.png"  /><span></span></button></div>';

                                             }
                                             else{
                                                 textpageHTML += '<div class="service-details" style="display:block !important; clear: both; margin: 10px 0px; overflow: hidden;color:'
                                                 + sessionStorage.getItem('pageTextColor')
                                                 + '; !improtant" class="'
                                                 + sessionStorage.getItem('appPageHsize')
                                                 + '"><button class="youtube-btn"  onclick="playVimeoVideo(\''+vimeoUrl+'\')"><img width="" height="" src="images/vimeo_Pic.png"  /><span></span></button><div class="textPara">'
                                                 + textPageDescription[i] + '</div></div>';

                                             }
                                                                                         }
                                     }
                                     else if(fileTypeArray[i]=="youtube")
                                     {
                                         
                                         var tubeImg = textPageVideo[i].substring(textPageVideo[i].lastIndexOf("/")+1, textPageVideo[i].length);
                                         var youtubeImgurl = "http://img.youtube.com/vi/"+tubeImg+"/hqdefault.jpg";
                                         if (i == 0) {
                                             if(textstyle=="bottom")
                                             {
                                                 textpageHTML += '<div class="service-details" style="display:block !important; clear: both; margin: 10px 0px; overflow: hidden;color:'
                                                 + sessionStorage.getItem('pageTextColor')
                                                 + ' !improtant;"><h2 class="'
                                                 + sessionStorage.getItem('appPageHsize')
                                                 + '" style="text-align:'
                                                 + sessionStorage.getItem('pageIndentation')
                                                 + ' !important">'
                                                 + title
                                                 + '</h2><br><div class="textPara">'
                                                 + textPageDescription[i] + '</div><button class="youtube-btn"  onclick="play_youtube(\''+textPageVideo[i]+'\')"><img width="" height="" src="'+youtubeImgurl+'"  /><span></span></button></div>';

                                             }
                                             else{
                                                 
                                             
                                             textpageHTML += '<div class="service-details" style="display:block !important; clear: both; margin: 10px 0px; overflow: hidden;color:'
                                             + sessionStorage.getItem('pageTextColor')
                                             + ' !improtant;"><h2 class="'
                                             + sessionStorage.getItem('appPageHsize')
                                             + '" style="text-align:'
                                             + sessionStorage.getItem('pageIndentation')
                                             + ' !important">'
                                             + title
                                             + '</h2><br><button class="youtube-btn"  onclick="play_youtube(\''+tubeImg+'\')"><img width="" height="" src="'+youtubeImgurl+'"  /><span></span></button><div class="textPara">'
                                             + textPageDescription[i] + '</div></div>';
                                             }
                                             
                                         }
                                         else {
                                             if(textstyle=="bottom")
                                             {
                                                 textpageHTML += '<div class="service-details" style="display:block !important; clear: both; margin: 10px 0px; overflow: hidden;color:'
                                                 + sessionStorage.getItem('pageTextColor')
                                                 + '; !improtant" class="'
                                                 + sessionStorage.getItem('appPageHsize')
                                                 + '"><div class="textPara">'
                                                 + textPageDescription[i] + '</div><button class="youtube-btn"  onclick="play_youtube(\''+tubeImg+'\')"><img width="" height="" src="'+youtubeImgurl+'"  /><span></span></button></div>';

                                             }
                                             else{
                                             textpageHTML += '<div class="service-details" style="display:block !important; clear: both; margin: 10px 0px; overflow: hidden;color:'
                                             + sessionStorage.getItem('pageTextColor')
                                             + '; !improtant" class="'
                                             + sessionStorage.getItem('appPageHsize')
                                             + '"><button class="youtube-btn"  onclick="play_youtube(\''+tubeImg+'\')"><img width="" height="" src="'+youtubeImgurl+'"  /><span></span></button><div class="textPara">'
                                             + textPageDescription[i] + '</div></div>';
                                             }
                                         }
                                     }
                                     
                                     else{
                                         
                                         if (i == 0) {
                                             textpageHTML += '<div class="service-details" style="color:'
                                             + sessionStorage.getItem('pageTextColor')
                                             + '; !improtant"><h2 style="text-align:'
                                             + sessionStorage.getItem('pageIndentation')
                                             + ' !important">' + title
                                             + '</h2><br><div class="textPara">'
                                             + textPageDescription[i] + '</div></div><br>';
                                         } else {
                                             textpageHTML += '<div class="service-details" style="color:'
                                             + sessionStorage.getItem('pageTextColor')
                                             + '; !improtant"><div class="textPara">'
                                             + textPageDescription[i] + '</div></div><br>';
                                         }
                                     }
                                 }
                                 else
                                 {
                                 if(i==0){
									 
                                 textpageHTML+='<div class="service-details" style="color:'+sessionStorage.getItem('pageTextColor')+'; !improtant"><h2 style="text-align:'+sessionStorage.getItem('pageIndentation')+' !important"  class="'+sessionStorage.getItem('appPageHsize')+'">'+title+'</h2><br><div class="textPara">' + textPageDescription[i] +'</div></div><br>';
                                 }
                                 else {
                                 textpageHTML+='<div class="service-details" style="color:'+sessionStorage.getItem('pageTextColor')+'; !improtant"><div class="textPara">' + textPageDescription[i] +'</div></div><br>';
                                 }
                                 }
                                 }
appendHtml("<div style='color:"+sessionStorage.getItem('pageTextColor')+"; !improtant'><div class='page-text custom-color'>"+textpageHTML+"</div></div>",'',1);
setTimeout(function(){
           $("#contentHolder").scrollTop(0);
           },500);}

function openImageFromNative(position) {
	var imagesURL = imageSavedUrl[position];
	window.plugins.photo.show({
		videoid : position,
		videoInfo : imagesURL
	}, function() {
	}, function() {
	});
}


function play_youtube(obj)
{
  
  openWebPage(obj,1);
 
}

function playTube(key){
	console.log("YouTube key..>> "+key);
	playYoutubeSong(key, " ");
 // toaster.liveVideoPlayMethod(url);
}

function playVimeo(link){
	playVimeoVideo(link);
}
