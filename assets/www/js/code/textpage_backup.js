//////////////////////////////Text Page//////////////////////////
 var imageSavedUrl = [];
var videoSavedUrl="";
function gettextpage(index)
{
    var textpageHTML="";
    //imageSavedUrl="";
    videoSavedUrl="";
    var summaryText="";
    var imageUrlText="";
    var videoUrlText="";
    var textPageImage=new Array();
    var textPageVideo=new Array();
     var fileTypeArray=new Array();
    $textpageXmlData= $(masterData).find("TextPage[indexval=" + index + "]");
    
                                     
                                     var title=($textpageXmlData.find("header").text());
                                     $summary=$textpageXmlData.find("summary");
  
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
                                                else if($(this).text()=="video")
                                                {
                                                var video=$textpageXmlData.find("video").text();
                                            
                                                $videoUrl=$textpageXmlData.find("video");
                                                $.each($videoUrl, function() {
                                                       videoUrlText+=$(this).text()+"@#";
                                                       });
                                                     textPageVideo= videoUrlText.substring(0,videoUrlText.length-2).split("@#");
                                                }
                                             });
                                            
//                                            alert(fileTypeArray);
//                                            alert(textPageImage);
//                                            alert(textPageVideo);
                                     var textPageDescription= summaryText.substring(0,summaryText.length-2).split("@#");
                             //   alert(textPageDescription.length);
                                     for(var i=0;i<textPageDescription.length;i++)
                                     {
                                      
                                     if(textPageImage[i]!=''||textPageVideo[i]!=''){
                                         
                                         if(fileTypeArray[i]=="image")
                                         {
										 imageSavedUrl[i]=textPageImage[i];
                                           //  imageSavedUrl+=textPageImage[i];
                                             //imageSavedUrl+=",";
                                                 console.log("header size text page --->"+sessionStorage.getItem('appPageHsize'));
                                             
											 if(i==0){
                                                 

                                             textpageHTML+='<div class="service-details" style="display:block !important; clear: both; margin: 10px 0px; overflow: hidden;color:'+sessionStorage.getItem('pageTextColor')+' !improtant;"><h2 class="'+sessionStorage.getItem('appPageHsize')+'" style="text-align:'+sessionStorage.getItem('pageIndentation')+' !important">'+title+'</h2><br><div class="textPara">' + textPageDescription[i] +'</div><img onclick="openImageFromNative('+i+');" src="'+textPageImage[i]+'" class="textParaImg"></div>';
                                             }
                                             else {
                                             textpageHTML+='<div class="service-details" style="display:block !important; clear: both; margin: 10px 0px; overflow: hidden;color:'+sessionStorage.getItem('pageTextColor')+'; !improtant" class="'+sessionStorage.getItem('appPageHsize')+'"><div class="textPara">' + textPageDescription[i] +'</div><img onclick="openImageFromNative('+i+');" src="'+textPageImage[i]+'" class="textParaImg"></div>';
                                             }
											 
                                     
                                         }
                                         else
                                         {
                                             videoSavedUrl+=textPageVideo[i];
                                             videoSavedUrl+=",";
                                             console.log("header size text page --->"+sessionStorage.getItem('appPageHsize'));
                                            if(i==0){
                                                 textpageHTML+='<div class="service-details" style="display:block !important; clear: both; margin: 10px 0px; overflow: hidden;color:'+sessionStorage.getItem('pageTextColor')+' !improtant;"><h2 class="'+sessionStorage.getItem('appPageHsize')+'" style="text-align:'+sessionStorage.getItem('pageIndentation')+' !important">'+title+'</h2><br><video width="100%" height="100%" src='+textPageVideo[i]+' controls>\</video><div class="textPara">' + textPageDescription[i] +'</div></div>';
                                             }
                                             else {
                                                 textpageHTML+='<div class="service-details" style="display:block !important; clear: both; margin: 10px 0px; overflow: hidden;color:'+sessionStorage.getItem('pageTextColor')+'; !improtant" class="'+sessionStorage.getItem('appPageHsize')+'"><video width="100%" height="100%" src='+textPageVideo[i]+' controls>\</video><div class="textPara">' + textPageDescription[i] +'</div></div>';
                                             }
                                         
                                         }
                                     }
                                     else
                                     {
                                     if(i==0){
                                     textpageHTML+='<div class="service-details" style="color:'+sessionStorage.getItem('pageTextColor')+'; !improtant"><h2 style="text-align:'+sessionStorage.getItem('pageIndentation')+' !important">'+title+'</h2><br><div class="textPara">' + textPageDescription[i] +'</div></div><br>';
                                     }
                                     else {
                                     textpageHTML+='<div class="service-details" style="color:'+sessionStorage.getItem('pageTextColor')+'; !improtant"><div class="textPara">' + textPageDescription[i] +'</div></div><br>';
                                     }
                                     }
                                     }
    appendHtml("<div style='color:"+sessionStorage.getItem('pageTextColor')+"; !improtant'><div class='page-text custom-color'>"+textpageHTML+"</div></div>",'',1);
    setTimeout(function(){ 
     $("#contentHolder").scrollTop(0);
     },100);
	}


function openImageFromNative(position)
{
var imagesURL=imageSavedUrl[position];
window.plugins.photo.show({ videoid:position, videoInfo:imagesURL}, function() {}, function() {} );
}
