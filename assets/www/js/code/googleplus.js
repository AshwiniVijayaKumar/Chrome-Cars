/////////////////GooglePlus////////////////////////
	var follow_plus = "Follow";


function getgoogleplusPage(index){
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }

/*$("#reload").show();
    $("#bookmark").hide();
    $("#mainbackfoodecom").hide();*/
    $('.appypie-loader').show();
    console.log("Dheeraj log 1");
    sessionStorage.removeItem("singlePage");
    $googlePlusIndex = $(masterData).find( "Googleplus[indexval="+index+"]" );
    var funcUrl='';
    var counter=0;
    var htmlGooglePlus='';
    
    
    $googlePlusIndex.find('googleplusrow').each(function()
    		{
    	 console.log("Dheeraj log 2");
    		var GoogleplusName=$(this).find("googleplusName").text();
    	 	var GoogleplusUser=$(this).find("gp_url").text();
    		localStorage.setItem("GoogleplusUser",GoogleplusUser);
    	    var urlGooglePlus = 'http://'+window.localStorage.getItem("reseller")+'/mobile_api/gplus.php?username='+encodeURIComponent(GoogleplusUser);
    	                   console.log("Google "+urlGooglePlus);
    		var n=urlGooglePlus.search('%2F%2B');
    	    
    	    if(n!=-1)
    	    {
    	    	 console.log("Dheeraj log 3");
    	    urlGooglePlus=urlGooglePlus.replace("%2B","@@$$$");
    	    }
    	    var imgurl=$(this).find("imgurl").text();
    	    
    	    funcUrl='MyWebservice_urlGooglePlus(\''+urlGooglePlus+'\',\''+index+'\')';
    	    htmlGooglePlus+='<span class="appypie-list" onclick="MyWebservice_urlGooglePlus(\''+urlGooglePlus+'\',\''+index+'\')">'+returnImageHtml(imgurl)+' <span>'+GoogleplusName+'</span></span>';
    	    counter=parseFloat(counter)+1;
    		});
    
    if(parseFloat(counter) == 1)
    {
    	 console.log("Dheeraj log 4");
        window.setTimeout(funcUrl,10);
        sessionStorage.setItem("singlePage","true");
    }
    else
    {
        setTimeout(function(){
                   appendHtml(htmlGooglePlus,'',1);
                   },100);
    }
   
  

}
function MyWebservice_urlGooglePlus(path,indexValue)
{
    var xhr = jQuery.ajax({
                          url : path,
                          async : false,
                          dataType : 'json',
                          cache:false,
                          }).responseText;
    console.log("Dheeraj log 5");
   if(xhr!= null)
   {
	   console.log("Dheeraj log 6");
    createGooglePlusScreen(xhr,indexValue);
   }
   else
   {
	   console.log("Dheeraj loader");
   $('.appypie-loader').hide();
   }
    

}
function createGooglePlusScreen(Data,index)
{
    
    if(Data.length)
    {
    	 console.log("Dheeraj log 7");
        var jsonData=Data.split("like$$$@$$$&&");
        
        var userInfo = jQuery.parseJSON(jsonData[0]);
        
        console.log(userInfo);
        
       
        try
        {
       
        var profile_image_url=userInfo.image.url;
        
        var DisplayName=userInfo.displayName;
		if(userInfo.name) {
			var givenName=userInfo.name.givenName;
		}
		else {
			var givenName='';
		}
		if(typeof userInfo.tagline != 'undefined'){
			var tagline=userInfo.tagline;
        }
		else {
			var tagline="";
		}
		
	
     
		 console.log("Dheeraj log 8");
         var ht='<div class="tweet-user"><div class="tweet-user-image"><img src="'+profile_image_url+'"></div><a onclick="followGoogle('+index+');" class="google_follow_btn">'+follow_plus+'</a><div class="tweet-user-details"> <h3>'+DisplayName+' <span>@'+givenName+'</span></h3><p class="google-word">'+tagline+'</p></div></div>';
        
        var mainData=jQuery.parseJSON(jsonData[1]);
        
         var feedInfo = mainData.items;
     
        
        var TweetHTML='';
        
         for(var i=0;i<feedInfo.length;i++)
        {
            var feedData=feedInfo[i].title;
            
            var feedData11=feedInfo[i].title;
			
            console.log("TITLTK"+feedData.length);
            
            if(feedInfo[i].object.attachments)
            {
                
                if(feedInfo[i].object.attachments)
                {
                var imageDisplayName=feedInfo[i].object.attachments[0].displayName;
                
                
                if(typeof imageDisplayName == 'undefined')
                {
                    imageDisplayName='';
                }
                }
                else
                {
                    imageDisplayName='';
                }
                
               
                console.log("Dheeraj log 9");
                if(feedInfo[i].object.attachments)
                {
                if(feedInfo[i].object.attachments[0].content)
                {
                    var content=feedInfo[i].object.attachments[0].content;
                    
                    if(typeof content =='undefined')
                    {
                        content='';
                    }
                }
                else
                {
                    content='';
                }
                }
                else
                {
                    content='';
                }
                
               if(feedInfo[i].object.attachments)
                {
                if(feedInfo[i].object.attachments[0].image)
                {
                    var imageUrl=feedInfo[i].object.attachments[0].image.url;
                    
                    if(typeof imageUrl == 'undefined')
                    {
                        imageUrl=feedInfo[i].object.attachments[0].fullImage.url;
                        if(typeof imageUrl == 'undefined')
                        {
                            imageUrl='';
                        }
                    }
                }
                else
                    imageUrl='';
                }
                else
                {
                    imageUrl='';
                }
                // new add
				
				
				 if(feedInfo[i].object.attachments[0].objectType)
                {
                    var qwqw=feedInfo[i].object.attachments[0].objectType;
                   // console.log("@@@@@@@@@@@@"+qwqw);
                }
                
                if(feedInfo[i].object.attachments)
                {
                    
                    if(feedInfo[i].object.attachments[0].url)
                    {
                        var Url=feedInfo[i].object.attachments[0].url;
                       //console.log("@@@@@@@@@@@@"+Url);
                        
                        if(typeof Url == 'undefined')
                        {
                            Url=feedInfo[i].object.attachments[0].embed.url;
                            if(typeof Url == 'undefined')
                            {
                                Url='';
                            }
                        }
                    }
                    else
                        Url='';
                }
                else
                {
                    Url='';
                }
                if(imageUrl.length!=0)
			        feedData='<h2><b>'+feedData11+'</b></h2><br><h4>'+imageDisplayName+'</h4><p>'+content+'</br><img src='+imageUrl+' class="googleDImg" onclick="openLargeImage(\''+imageUrl+'\')"><p onclick="openTwitterLink(\''+Url+'\')">'+Url+'</p></p>';
                else
                {
                     feedData='<h4>'+imageDisplayName+'</h4><p>'+content+'</br></p>';
                }
                
                
            }
  TweetHTML += '<div class="tweet_feeds"><div class="tweet-user-image"><img src='+profile_image_url+'></div><div class="feeds_desc"><h3>'+givenName+'</br><span>'+DisplayName+'</span></h3> <p>'+feedData+'</p> </div></div>';
           
         }
         
         
         
         if(sessionStorage.getItem('singlePage') == 'true')
         {
         console.log('hellocalc 3');
         appendHtml(ht+TweetHTML,2,1);
         }
         else
         {
         
        	 appendHtml(ht+TweetHTML,2,2);
         }
        }
        catch(error)
        {
        	$('.appypie-loader').hide();
        }
    }
    else
    {
	$('.appypie-loader').hide();
	}	
}		 
function followGoogle(index){
    if(window.localStorage.getItem("layout")=="bottom"){
				sessionStorage.setItem("websitePageFlag","true");
			}
			else {
				sessionStorage.setItem("websitePageFlag","false");
		}
	var googleplusLink = localStorage.getItem("GoogleplusUser");
	setTimeout(function() {
		openWebPage(googleplusLink,2);	
	
});
	}
// new add


function openTwitterLink(link)
{
    link=link+"@@@@@flase";
    openWebPage(link,2);
}
function followTwitter()
{
    if(checkNetworkConnection())
    {
        //global function for managing website backpage,as the same code was used many times;
        var followUrl='https://twitter.com/intent/follow?screen_name='+sessionStorage.getItem("TwitUserId");
        if(sessionStorage.getItem("singlePage")=="true")
        {
            openWebPage(followUrl,2);
        }
        else
        {
            openWebPage(followUrl,3);
        }
    }
}

function openLargeImage(imagesURL)
{
   //var imagesURL=sessionStorage.getItem("bigImageUrl");
   window.plugins.photo.show({ videoid:0, videoInfo:imagesURL}, function() {}, function() {} );

}
	