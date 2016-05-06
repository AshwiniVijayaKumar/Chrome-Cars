var nextPageUrl;
var canshowmore;
var showMore;
var imageUrlArray=new Array();
var picTextArray=new Array();
var prevNoOfImages=0;
var totalImagesForFacebook;
var totalImages;
var CustomAlbum='';
var singleEvent='';
var setImagesArray= new Array();
var setTotalImagesArray= new Array();
var StreamData;
var flickrTabPressed="";
var galleryIndex='';
var typegallery='';
var coverImageCounter=0;
var FlickrPageCount=1;
var photoShare='';

var commentsArray=new Array();
var likeArray=new Array();
var FlickrPageCount=1;

var photos_gallery = "Photos";
var serverresponse_gallery = 'Server Response';
var unableloadphotos_gallery = 'Unable to load Photos !';
var error_gallery = 'Error!';
var albumempty_gallery = 'Album is empty';
var followedby_gallery = "Followed By";
var follows_gallery = 'Follows';
var showmore_gallery = 'Show More';
var photostream_gallery = "Photostream";
var sets_gallery = "Sets";
var alert_gallery = 'Alert';
var nosetfound_gallery = 'No Sets Found!';
var photooo_gallery = "Photo";
var viewallets = "View All Sets";


function photo_gallery(index)
{
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    flickrTabPressed=0;
    $('.appypie-loader').show();
    $photoIndex = $(masterData).find( "photo[indexval="+index+"]" );
    var photoId=$photoIndex.find("photoId").text().split(',');
    var photoIdArray=["Picasa","Flickr","Pinterest","Instagram","FacebookAlbum","Custom"];
    
    var photo_HTML='';
        sessionStorage.setItem("fromSingleGalleryPage","false");
        console.log("photoId[x]--->"+photoId);
    //FlickrPageCount=12;
    var funcUrl='';
    var counter=0;
        for(x=0;x<photoId.length;x++)
        {
            if(photoId[x] == 'Custom' && $photoIndex.find("CustomAlbum"))
            {
            //Custom
            $photoIndex.find("CustomAlbum").each(function()
                                                 {
                                                 var CustomName=$(this).find("CustomName").text();
                                                 var customIndex=$(this).attr('indexval');
                                                 var albumCoverImg=$(this).find("CustomImageUrl").text();
                                                 console.log("customIndex --->"+customIndex);
                                                 photo_HTML +='<a onclick="customUploadedImages(\''+customIndex+'\',\''+index+'\');" class="appypie-list">'+returnImageHtml(albumCoverImg)+'<span>'+CustomName+'</span></a>';
                                                 funcUrl='customUploadedImages(\''+customIndex+'\',\''+index+'\')';
                                                 counter++;
                                                 
                                                 });
            }
            
            if($photoIndex.find(""+photoId[x]))
            {
                console.log("photoId[x]--->"+photoId[x]);
                

                
                //Picasa,FacebookAlbum,Pinterest,Instagram
                $photoIndex.find(""+photoId[x]).each(function()
                                                     {
                                                     if(photoId[x] == 'FacebookAlbum')
                                                     {
                                                     photoId[x]='Facebook';
                                                     }
                                                     var CustomName=$(this).find(photoId[x]+"CustomName").text();
                                                     var UserName='';
                                                     if($(this).find(photoId[x]+"UserName") && $(this).find(photoId[x]+"UserName").text())
                                                     {
                                                     UserName=$(this).find(photoId[x]+"UserName").text();
                                                     }
                                                    
                                                     console.log("UserName--->"+$(this).find(photoId[x]+"PageName"));
                                                     var albumName='';
                                                     if($(this).find(photoId[x]+"AlbumName") && $(this).find(photoId[x]+"AlbumName").text())
                                                     {
                                                     albumName=$(this).find(photoId[x]+"AlbumName").text();
													 albumName = albumName.replace("'", "");
													 albumName = albumName.replace(/["]/g, "");  
                                                     }
                                                     var albumCoverImg= $(this).find(photoId[x]+"ImageUrl").text();
                                                     
                                                     photo_HTML +='<a onclick="showPicasaImages(\''+photoId[x]+'\',\''+UserName+'\',\''+albumName+'\');" class="appypie-list">'+returnImageHtml(albumCoverImg)+'<span>'+CustomName+'</span></a>';
                                                     
                                                     funcUrl='showPicasaImages(\''+photoId[x]+'\',\''+UserName+'\',\''+albumName+'\')';
                                                     counter++;
                                                     showMore=0;
                                                     });
            }
        }
    
    if(parseFloat(counter) == 1)
    {
        window.setTimeout(funcUrl,10);
        sessionStorage.setItem("fromSingleGalleryPage","true");
        singleEvent=1;
    }
    else
    {
        appendHtml("<div class='page-text'>"+photo_HTML+"</div>",'',1);
    }
}
function customUploadedImages(pageIndex,index)
{
    
    $photoIndex = $(masterData).find( "photo[indexval="+index+"]" );
    $CustomImageData=$photoIndex.find( "CustomAlbum[indexval="+pageIndex+"]" );
    imageUrlArray=new Array();
    var j=0;
    var newhtml='';
    $CustomImageData.find("photolist").each(function()
                                              {
                                            //photoImage
                                            imageUrlArray[j]=$(this).find("photoImage").text();
											imageUrlArray[j]=imageUrlArray[j].replace(/ /g,"%20");
											imageUrlArray[j]=imageUrlArray[j].replace(/'/g,"%27"); 
                                            if($(this).find("photoTitle").text() != "Untitled"){
                                            newhtml += '<a onclick="openPhoto('+(j)+');" style=\"background-image:url(\''+imageUrlArray[j]+'\')\" class=\"photo-img customphoto-img\"><span>'+$(this).find("photoTitle").text()+'</span></a>';
                                            }
                                            
                                            else
                                            {
                                                newhtml += '<a onclick="openPhoto('+(j)+');" style=\"background-image:url(\''+imageUrlArray[j]+'\')\" class=\"photo-img customphoto-img\"></a>';

                                            }
                                            j++;
                                            
                                            console.log("the value of j--->"+j);
                                              });
    sessionStorage.setItem("bigImageUrl",imageUrlArray);
    
  
    htmlTXT='<section><div class="instagram-user"> \
    <div class="instagram-user-image"><img src="'+imageUrlArray[0]+'"></div> \
    <div class="instagram-user-details"> \
    <h3>'+$CustomImageData.find("CustomName").text()+'</h3> \
    <div class="instagram_options"> \
    <div class="insta_photo">'+imageUrlArray.length+' <span>'+photos_gallery+'</span></div> \
    </div></div></div></section>';
    
    if($CustomImageData.find("photolist").length==1)
                                            {
                                            openPhoto(0);
                                            $('.appypie-loader').hide();
                                            }
											else{
    if(sessionStorage.getItem("fromSingleGalleryPage")=="true")
    {
        appendHtml(htmlTXT+newhtml,'',1);
    }
    else
    {
        appendHtml(htmlTXT+newhtml,2,2);
    }
	}
}


function showPicasaImages(gallerytype,userName,albumName)
{
try
{
    if(!checkNetworkConnection())
    {
        $('.appypie-loader').hide();
    }
    else
    {
        $('.appypie-loader').show();
        if(gallerytype!='Flickr')
        {
            showMore=0;
        }
        prevNoOfImages=0;
        var path='';
        if(gallerytype=='Picasa' || gallerytype=='Instagram')
        {
            if(gallerytype=='Picasa')
            {
                path='http://picasaweb.google.com/data/feed/api/user/'+userName+'/album/'+albumName;
            }
            else
            {
                path='http://'+localStorage.getItem("reseller")+'/mobile_api/instagram.php?username='+userName;
            }
            if(albumName.length < 2)
            {
                albumName=userName;
            }
            setTimeout(function(){
                       var xhr = jQuery.ajax({
                                             url : path,
                                             async : false,
                                             dataType : 'json',
                                             cache:false,
                                             }).responseText;
                      if(xhr!= null && xhr.length!=0 && xhr!="No album found.") {
                       PicasaScreen(xhr,gallerytype,albumName);
                       }
                       else {
						   PicasaScreen(xhr,gallerytype,albumName);
                       //alertPopUp('Server Response','Data not found on server !');
                       }
                       },100);
        }
        else if(gallerytype=='Custom')
        {
            var wsUrl="http://"+localStorage.getItem("reseller")+"/services/soap#loadImages";
            var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><loadImages xmlns=\"http://'+localStorage.getItem("reseller")+'/services/soap#loadImages/\"><appId>'+localStorage.getItem("applicationID")+'</appId><prevVersion>'+0+'</prevVersion></loadImages></soap:Body></soap:Envelope>';
            console.log("Soap  "+soapRequest);
            $.ajax({
                   type: "POST",
                   url: wsUrl,
                   contentType: "text/xml",
                   dataType: "text",
                   data: soapRequest,
                   success: function(data, status, req)
                   {
                   PicasaScreen(req,gallerytype,albumName);
                   },
                   error: function(response, textStatus, errorThrown)
                   {
                   console.log(response);
                  
                   alertPopUp(serverresponse_gallery,unableloadphotos_gallery);
                   }
                   });
        }
        /*else if(gallerytype=='Flickr')
        {
            var wsUrl="http://"+localStorage.getItem("reseller")+"/services/soap#flickr";
            var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><flickr xmlns=\"http://'+localStorage.getItem("reseller")+'/services/soap#flickr/\"><username>'+userName+'</username><page>'+FlickrPageCount+'</page></flickr></soap:Body></soap:Envelope>';
            console.log('Flickr--->'+soapRequest)
            $.ajax({
                   type: "POST",
                   url: wsUrl,
                   contentType: "text/xml",
                   dataType: "text",
                   data: soapRequest,
                   success: function(data, status, req)
                   {
                   
                   flickerScreen(req,gallerytype,userName);
                   },
                   error: function(response, textStatus, errorThrown)
                   {
                   console.log(response);
                   alertPopUp(serverresponse_gallery,unableloadphotos_gallery);
                   //alertPopUp('Server Response','Unable to load Photos !');
                   }
                   });*/
        else if(gallerytype=='Flickr')
        {
        	
                path="https://api.flickr.com/services/rest/?method=flickr.people.findByUsername&api_key=c01dcb476bb7126dd130992036d66dce&username="+userName+"&format=json&nojsoncallback=1";
                $.getJSON(path, function(value){
                          flickerScreenpre(value,userName,FlickrPageCount);
                          }).fail(function() {
                                  
                                  alertPopUp(Server_Response,Unable_to_load_Photos);
                                  });
        }
        else if(gallerytype=='Pinterest' || gallerytype=='Facebook')
        {
        	
        	//alert("facebook");
            if(gallerytype=='Facebook')
            {
            	//alert(albumName);
            var value=albumName.split('___');
            var albumID=value[0];
            totalImagesForFacebook=value[2];
            path='https://graph.facebook.com/'+albumID+'/photos?access_token=766197580056948|LHnvo02f1qxDKJxQA3n9ggzx-9Q';
            userName=value[1];
            gallerytype='FacebookAlbum';
            }
            else
            {
             path='https://api.pinterest.com/v3/pidgets/users/'+userName+'/pins';
            }
            
           $.getJSON(path, function(value){
			         //alert("status=="+status);
                      var  eventJSON=value;
                      PicasaScreen(eventJSON,gallerytype,userName);
                      }).fail(function() {
    console.log( "error" );
    
	$('.appypie-loader').hide();
	if(imageUrlArray.length==0)
    {
		
		
       alertPopUp(error_gallery,albumempty_gallery);
    }
  });
  
        }
		//change by dheeraj//
		/*setTimeout(function(){
			$('.appypie-loader').show();
			
		}, 100);*/
    }
	}
	catch(err)
	{
	setTimeout(function(){
			$('.appypie-loader').hide();
		}, 100);
	}
}


function PicasaScreen(data,galleryType,Info)
{
    $('.appypie-loader').show();
    if(showMore==0)
    {
        imageUrlArray=new Array();
    }
    if(galleryType=='Picasa')
    {
        $(data).find("media\\:content").each(function(i,j)
                                             {
                                             imageUrlArray[i]=$(j).attr("url");
                                             });
    }
    if(galleryType=='Pinterest')
    {
        for(i=0;i<data.data.pins.length;i++)
        {
            var dataWithPins=data.data.pins[i].images["237x"];
            imageUrlArray[i]=dataWithPins.url;
        }
    }
    if(galleryType=='FacebookAlbum')
    {
        picTextArray = [];
    	likeArray= [];
    	commentsArray= [];
        //data=jQuery.parseJSON(data);
        if(data.paging.next)
        {
            nextPageUrl=data.paging.next;
            nextPageUrl=nextPageUrl.replace('v2.0/','');
            console.log('nextPageUrl--->'+nextPageUrl);
        }
        else
        {
            nextPageUrl=null;
        }
        totalImages=totalImagesForFacebook;
        console.log('totalImagesForFacebook--->'+totalImagesForFacebook);
        if(typeof totalImages == 'undefined'){
            totalImages = '';
        }
        var newImageArray=new Array();
        console.log('data.data.length--->'+data.data.length);
        for(i=0;i<data.data.length;i++)
            {
                console.log('imageUrlArray[prevNoOfImages+i]--->'+imageUrlArray[prevNoOfImages+i]);
             imageUrlArray[prevNoOfImages+i]=data.data[i].picture;
            newImageArray[prevNoOfImages+i]=data.data[i].source;
            }
        sessionStorage.setItem("facebookLargeImage",newImageArray);

    }
    if(galleryType=='Instagram')
    {
		$('.appypie-loader').show();
        if(showMore==1)
        {
            if(data.length)
            {
                var instagramData=jQuery.parseJSON(data);
                nextPageUrl=instagramData.pagination.next_url;
                for(i=0;i<instagramData.data.length;i++)
                {
                    imageUrlArray[prevNoOfImages+i]=instagramData.data[i].images.low_resolution.url;
                    
                   // picTextArray[prevNoOfImages+i]=instagramData.data[i].caption.text.replace(",","");
                    console.log("Images URl"+imageUrlArray[prevNoOfImages+i]);
                }
            }
        }
        else
        {
            if(data.length)
            {
                var jsonData=data.split("like$$$@$$$&&");
                console.log("Length : "+jsonData.length);
                var userInfo = jQuery.parseJSON(jsonData[0]);
                totalImages=userInfo.data.counts.media;
				 var followed_by=userInfo.data.counts.followed_by;
                var follows=userInfo.data.counts.follows;
                var instagramData=jQuery.parseJSON(jsonData[1]);
                nextPageUrl=instagramData.pagination.next_url;
                for(i=0;i<instagramData.data.length;i++)
                {
                    imageUrlArray[i]=instagramData.data[i].images.low_resolution.url;
                     if(instagramData.data[i].caption)
                    {
                     picTextArray[prevNoOfImages+i]=instagramData.data[i].caption.text.replace(",", "");
                    }
                    else
					{
                     picTextArray[prevNoOfImages+i]="";
                    }

					if(instagramData.data[i].caption != null )
                                            {
                                            likeArray[i]=instagramData.data[i].likes.count+" likes";
                                            //likeArray[i]=likeArray[i]+"@#@#@#";
                                            console.log("@#@#@#"+likeArray[i]);
                                            }
                                            else
                                            {
                                            likeArray[i]="0 likes";
                                            console.log("texttexttext"+likeArray[i]);
                                            }
					
					if(instagramData.data[i].caption != null )
                                            {
                                            commentsArray[i]=instagramData.data[i].comments.count+" comments";
                                           // commentsArray[i]=commentsArray[i]+"@#@#@#";
                                            console.log("@#@#@#"+commentsArray[i]);
                                            }
                                            else
                                            {
                                            commentsArray[i]="0 comments";
                                            console.log("texttexttext"+commentsArray[i]);
                                            }
                    console.log("Images URl"+imageUrlArray[i]);
                }
            }
        }
    }
    if(galleryType=='Custom')
    {
        var strJSON = $(data.responseText).find("return").text();
        var obj = jQuery.parseJSON(strJSON);
        for( i=0; i<obj.response.length;i++)
        {
            imageUrlArray[i]= obj.response[i].imageBig;
        }
    }
	//@amritesh,change for default image
	/*if(imageUrlArray.length==0)
    {
       alertPopUp('Error!','Album is empty');
    }
	else {*/
        var htmlTXT;
        if(Info.length>27)
        {
             Info = Info.substring(0,27);
        }
        if(galleryType=='Instagram' || galleryType=='FacebookAlbum' || galleryType=='Flickr')
        {
         	if(typeof followed_by === "undefined")
             {
	             followed_by="0";
             }
   
             if(typeof totalImages === "undefined")
              {
	            totalImages="0";
              }
			if(typeof follows === "undefined")
             {
	          follows="0";
             }
			
            var followers = '';
           
            
            if (galleryType == 'Instagram')
            {
                followers = '<div class="insta_photo">' + followed_by + ' <span>'+followedby_gallery+'</span></div><div class="insta_photo">' + follows + ' <span>'+follows_gallery+'</span></div>';
            }
			
			if(typeof imageUrlArray[0] === "undefined"){
			var imageUrlArray2 = new Array();
			 imageUrlArray2[0]=cordova.file.applicationDirectory+'www/images/logo.png';
			  htmlTXT='<section> \
            <div class="instagram-user"> \
            <div class="instagram-user-image"><img src="'+imageUrlArray2[0]+'"></div> \
            <div class="instagram-user-details"> \
            <h3>'+Info+'</h3> \
            <div class="instagram_options"> \
            <div class="insta_photo">'+totalImages+' <span>'+photos_gallery+'</span></div> '+followers+'\
            </div> \
            </div> \
            </div> \
            </section>';
            }else{
            htmlTXT='<section> \
            <div class="instagram-user"> \
            <div class="instagram-user-image"><img src="'+imageUrlArray[0]+'"></div> \
            <div class="instagram-user-details"> \
            <h3>'+Info+'</h3> \
            <div class="instagram_options"> \
            <div class="insta_photo">'+totalImages+' <span>'+photos_gallery+'</span></div> '+followers+'\
            </div> \
            </div> \
            </div> \
            </section>';
            }
        }
        
        else
        {
            htmlTXT='<section> \
            <div class="instagram-user"> \
            <div class="instagram-user-image"><img src="'+imageUrlArray[0]+'"></div> \
            <div class="instagram-user-details"> \
            <h3>'+Info+'</h3> \
            <div class="instagram_options"> \
            <div class="insta_photo">'+imageUrlArray.length+' <span>'+photos_gallery+'</span></div> \
            </div> \
            </div> \
            </div> \
            </section>';
        }
        var bigImagesUrls = '';
                for(j=0; j<imageUrlArray.length;j++)
                {
                    console.log("j--->"+j);
                    htmlTXT += '<a onclick="openPhoto('+(j)+');" style=\"background-image:url(\''+imageUrlArray[j]+'\')\" class=\"photo-img\"></a>';
                }
        if(galleryType=='FacebookAlbum' && sessionStorage.getItem("facebookLargeImage"))
        {
            sessionStorage.setItem("bigImageUrl",sessionStorage.getItem("facebookLargeImage"));
        }
        else
        {
            sessionStorage.setItem("bigImageUrl",imageUrlArray);
        }
           
        if(typeof nextPageUrl != 'undefined' && (galleryType=='Instagram' || galleryType=='FacebookAlbum') && nextPageUrl != null)
        {
                htmlTXT += '<div class="page-text"><a class="show-more" '+primaryColor+' onclick="showmorePhoto(\''+galleryType+'\',\''+Info+'\');" >'+showmore_gallery+'</a></div>';
        }
        if(galleryType=='Flickr' && imageUrlArray.length<totalImages)
        {
                htmlTXT += '<div class="page-text"><a class="show-more" onclick="showmorePhoto(\''+galleryType+'\',\''+Info+'\');" '+primaryColor+'>'+showmore_gallery+'</a></div>';
            console.log("htmlTXT-->"+htmlTXT);
        }
        if(sessionStorage.getItem("fromSingleGalleryPage")=="true"){
            forSinglePageView();
            sessionStorage.setItem("history",$.mobile.activePage.attr("id"));
            appendHtml(htmlTXT,'',1);
          
            
        }
        else {
            normalView();
            sessionStorage.setItem("history",$.mobile.activePage.attr("id"));
            appendHtml(htmlTXT,2,2);
          
            
        }
        
	//setTimeout(function(){
			$('.appypie-loader').hide();
		//}, 100);
}
function showmorePhoto(GalType,Info)
{
    
    if(!checkNetworkConnection())
    {
    }else
    {
        $('.appypie-loader').show();
        if(singleEvent==1)
        {
            sessionStorage.setItem("fromSingleGalleryPage","true");
        }
        else
        {
            sessionStorage.setItem("fromSingleGalleryPage","false");
        }
        if(GalType=='FacebookAlbum')
        {
            showMore=1;
            prevNoOfImages=prevNoOfImages+25;
            $.getJSON(nextPageUrl, function(value){
                      var  eventJSON=value;
                      PicasaScreen(eventJSON,GalType,Info);
                      });
            
        }
        if(GalType=='Instagram')
        {
            showMore=1;
            prevNoOfImages=prevNoOfImages+20;
            var nexturl='http://'+localStorage.getItem("reseller")+'/mobile_api/instagram.php?page=1&next='+encodeURIComponent(nextPageUrl);
            var xhr = jQuery.ajax({
                                  url :nexturl,
                                  async : false,
                                  dataType : 'json',
                                  cache:false,
                                  }).responseText;
            PicasaScreen(xhr,GalType,Info);
        }
        if(GalType=='Flickr')
        {
            FlickrPageCount=FlickrPageCount+12;
            showPicasaImages(GalType,Info,'');
        }
	}
}
function photo_1(index,pageIndex)
{
    
    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/soap#loadImages";
    
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><loadImages xmlns=\"http://'+localStorage.getItem("reseller")+'/services/soap#loadImages/\"><appId>'+localStorage.getItem("applicationID")+'</appId><prevVersion>'+galleryIndex+'</prevVersion></loadImages></soap:Body></soap:Envelope>';
    
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           GalleryScreen(Galleryindex,GallerypageIndex,req);
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           alertPopUp(serverresponse_gallery,unableloadphotos_gallery);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
}

function openPhoto(position)
{
	var imagesURL=sessionStorage.getItem("bigImageUrl");
	
	var picTexts ='';
	var picLikes ='';
	var picComments ='';
	photoShare=$photoIndex.find("photoShare").text();
	if( picTextArray.length > 0)
	{
		picTexts= picTextArray.toString();
	}
	if( likeArray.length > 0)
	{
		picLikes= likeArray.toString();
	}
	if( commentsArray.length > 0)
	{
		picComments= commentsArray.toString();
	}
	
    window.plugins.photo.show({ videoid:position, videoInfo:imagesURL, pictext:picTexts, piclikes:picLikes, piccomments:picComments,photoShare:photoShare}, function() {}, function() {} );
}


function flickerScreenpre(value,userValue,FlickrPageCount)
{
                                            $('.appypie-loader').show();
                                            var userid=value.user.id;
                                            
     path="https://api.flickr.com/services/rest/?&method=flickr.people.getPublicPhotos&api_key=c01dcb476bb7126dd130992036d66dce&user_id="+value.user.id+"&per_page=50&page="+FlickrPageCount+"&format=json&extras=url_m&nojsoncallback=jsonFlickrApi";
    $.getJSON(path, function(value){
      flickerScreen(value,userid,userValue);
      }).fail(function() {
              
              alertPopUp(Server_Response,Unable_to_load_Photos);
              });
}
                                            function flickerScreen(value,userid,userValue)
                                            {
                                            galleryType='Flickr'
                                            var htmlTXT="";
                                            imageUrlArray.length=0;
                                            if(flickrTabPressed==0)
                                            {
                                            StreamData=value;
                                            var obj = value;
                                            totalImages=value.photos.total;
                                            console.log("totalImages--->"+totalImages);
                                            htmlTXT='<div class="album_header">\
                                            <h2>'+userValue+'</h2><span class="pic_count">'+totalImages+'<span>Photos</span></span>\
                                            <div class="flickr-tab"><a class="active">Photostream</a><a onclick="getSets(\''+userid+'\',\''+userValue+'\');">Sets</a>\
                                            </div>\
                                            </div>';
                                            for(var i=0;i<obj.photos.photo.length;i++)
                                            {
                                            imageUrlArray[i]=obj.photos.photo[i].url_m;
                                            htmlTXT += '<a onclick="openPhoto('+(i)+');" style=\"background-image:url('+obj.photos.photo[i].url_m+')\" class=\"photo-img\"></a>';
                                            }
                                            sessionStorage.setItem("bigImageUrl",imageUrlArray);
                                            
                                            if(imageUrlArray.length<totalImages)
                                            {
                                            htmlTXT += '<div class="page-text"><a class="show-more" onclick="showmorePhoto(\''+galleryType+'\',\''+userValue+'\');" style="border-color:'+sessionStorage.getItem("primaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("primaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("primaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;" >'+showmore_gallery+'</a></div>';
                                            }
                                            if(sessionStorage.getItem("fromSingleGalleryPage")=="true")
                                            {
                                            
                                            appendHtml(htmlTXT,'',1);
                                            
                                            }
                                            else {
                                            appendHtml(htmlTXT,2,2);
                                            }
                                            }//done here
                                            else
                                            {
                                            
                                            if(coverImageCounter==0)
                                            {
                                            StreamData2=value;
                                            }
                                            var strJSON=value;
                                            onndddd=2;
                                            if(onndddd>3)
                                            {
                                            htmlTXT='<div class="album_header">\
                                            <h2>'+userValue+'</h2><span class="pic_count">'+totalImages+'<span>'+Photos+'</span> </span>\
                                            <div class="flickr-tab">\
                                            <a onclick="getPhotoStream(\''+userid+'\',\''+userValue+'\');">'+Photostream+'</a>\
                                            <a class="active">'+Sets+'</a>\
                                            </div>\
                                            </div>';
                                            
                                            alertPopUp(Alert,No_Sets_Found);
                                            }
                                            else
                                            {
                                            var setNameArray=new Array();
                                            var setIdArray=new Array();
                                            for(i=0;i<strJSON.photosets.photoset.length;i++)
                                            {
                                            setIdArray[i]=strJSON.photosets.photoset[i].id;
                                            setTotalImagesArray[i]=strJSON.photosets.photoset[i].photos;
                                            setNameArray[i]=strJSON.photosets.photoset[i].title._content.replace("'","");
                                            }
                                            
                                            htmlTXT='<div class="album_header">\
                                            <h2>'+userValue+'</h2><span class="pic_count">'+setIdArray.length+'<span>'+Sets+'</span> </span>\
                                            <div class="flickr-tab">\
                                            <a onclick="getPhotoStream(\''+userid+'\',\''+userValue+'\');">'+Photostream+'</a>\
                                            <a class="active">'+Sets+'</a>\
                                            </div>\
                                            </div>';
                                            
                                            
                                            if(coverImageCounter<setIdArray.length)
                                            {
                                            var setImageUrl='https://api.flickr.com/services/rest/?&method=flickr.photosets.getPhotos&api_key=c01dcb476bb7126dd130992036d66dce&photoset_id='+setIdArray[coverImageCounter]+'&extras=url_s&format=json&jsoncallback=?';
                                            console.log(setImageUrl);
                                            $.getJSON(setImageUrl, function(value){
                                                      var  eventJSON=value;
                                                      var  coverImage=eventJSON.photoset.photo[0].url_s;
                                                      downloadCoverImage(coverImageCounter,coverImage,userValue,userid);
                                                      
                                                      });
                                            
                                            
                                            }
                                            else if(setIdArray.length==setImagesArray.length)
                                            {
                                            for(i=0;i<setImagesArray.length;i++)
                                            {
                                            htmlTXT+= '<a onclick="openSetsDetail(\''+setIdArray[i]+'\',\''+setNameArray[i]+'\',\''+setTotalImagesArray[i]+'\',\''+userValue+'\',\''+userid+'\');" style="background-image:url('+setImagesArray[i]+')" class="flickr-set"><span>'+setNameArray[i]+'<br>Photo:'+setTotalImagesArray[i]+'</span></a>';
                                            }
                                            
                                            
                                            if(sessionStorage.getItem("fromSingleGalleryPage")=="true")
                                            {
                                            appendHtml(htmlTXT,'',1);
                                            }
                                            else {
                                            appendHtml(htmlTXT,2,2);
                                            }
                                            
                                            }
                                            }
                                            }
                                            }
                                            
                                            function downloadCoverImage(counter,images,FlickrUserName,userid)
                                            {
                                            $('.appypie-loader').show();
                                            setImagesArray[coverImageCounter]=images;
                                            coverImageCounter++;
                                            flickerScreen(StreamData2,userid,FlickrUserName);
                                            }
                                            function getSets(FlickrUserName,userValue)
                                            {
                                            $('.appypie-loader').show();
                                            flickrTabPressed=1;
                                            
                                            var url="https://api.flickr.com/services/rest/?method=flickr.photosets.getList&api_key=c01dcb476bb7126dd130992036d66dce&user_id="+FlickrUserName+"&format=json&primary_photo_extras=url_m&nojsoncallback=jsonFlickrApi"
                                            $.getJSON(url, function(value){
                                                      flickerScreen(value,FlickrUserName,userValue);
                                                      }).fail(function() {
                                                              
                                                              alertPopUp(Server_Response,Unable_to_load_Photos);
                                                              });
                                            
                                            }
                                            
                                            function openSetsDetail(setid,setNameValue,setTotalImageValue,userName,userid)
                                            {
                                            $('.appypie-loader').show();
                                            imageUrlArray.length=0;
                                            var setUrl='https://api.flickr.com/services/rest/?format=json&method=flickr.photosets.getPhotos&photoset_id='+setid+'&per_page=500&page=1&extras=url_m&api_key=c01dcb476bb7126dd130992036d66dce&nojsoncallback=jsonFlickrApi';
                                            $.getJSON(setUrl, function(value){
                                                      var strJSON=value.photoset.photo;
                                                      htmlTXT='<div class="album_header">\
                                                      <h2>'+setNameValue+'</h2><span class="pic_count">'+setTotalImageValue+'<span>'+Photos+'</span> </span>\
                                                      <div class="flickr-tab">\
                                                      <a onclick="getPhotoStream(\''+userid+'\',\''+userName+'\');">'+Photostream+'</a>\
                                                      <a onclick="getSets(\''+userid+'\',\''+userName+'\');"  class="active">'+View_All_Sets+'</a>\
                                                      </div>\
                                                      </div>';
                                                      for(var i=0;i<strJSON.length;i++)
                                                      {
                                                      imageUrlArray[i]=strJSON[i].url_m;
                                                      htmlTXT += '<a onclick="openPhoto('+(i)+');" style=\"background-image:url('+imageUrlArray[i]+')\" class=\"photo-img\"></a>';
                                                      }
                                                      sessionStorage.setItem("bigImageUrl",imageUrlArray);
                                                      
                                                      if(sessionStorage.getItem("fromSingleGalleryPage")=="true")
                                                      {
                                                      appendHtml(htmlTXT,2,2);
                                                      }
                                                      else {
                                                      appendHtml(htmlTXT,3,3);
                                                      }
                                                      }).fail(function() {
                                                              
                                                              alertPopUp(Server_Response,Unable_to_load_Photos);
                                                              });
                                            // var setUrl='http://'+localStorage.getItem("reseller")+'/mobile_api/flickr_sets.php?setsid='+setid;
                                            
                                            //    console.log(setUrl);
                                            //    var xhr = jQuery.ajax({
                                            //                          url : setUrl,
                                            //                          async : false,
                                            //                          dataType :'json',
                                            //                          cache:false,
                                            //                          }).responseText;
                                            
                                            
                                            
                                            
                                            }
                                            
                                            
                                            function getPhotoStream(userid,FlickrUserName)
                                            {
                                            $('.appypie-loader').show();
                                            imageUrlArray.length=0;
                                            flickrTabPressed=0;
                                            flickerScreen(StreamData,userid,FlickrUserName);
                                            }

