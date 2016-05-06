///////////////////Twitter///////////////////

var alert_server_response_twitter = "Server Response";
var alert_data_not_found_on_server_twitter = "Data not found on server !"
var create_twit_screen_follow_twitter = "Follow";
var create_twit_screen_tweets_twitter = "Tweets";
var create_twit_screen_followers_twitter = "Followers";

function gettwitterPage(index)
{
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    $twitterXmlData=$(masterData).find("TwitBlock[indexval=" + index + "]");
    var funcUrl='';
    var counter=0;
    var htmlTwitter='';
    $twitterXmlData.find('twitterrow').each(function()
                                                {
                                             var twitterId=$(this).find("TwitUserId").text();
                                             funcUrl='gettwitterPageData(\''+twitterId+'\')';
                                             htmlTwitter+='<span class="appypie-list" onclick="gettwitterPageData(\''+twitterId+'\')">'+returnImageHtml($(this).find("imgurl").text())+' <span>'+$(this).find("twitterName").text()+'</span></span>';
                                             counter=parseFloat(counter)+1;
                                              });
    
    if(parseFloat(counter) == 1)
    {
        window.setTimeout(funcUrl,10);
        sessionStorage.setItem('singlePage','true');
    }
    else
    {
        appendHtml(htmlTwitter,'',1);
    }
    
}


function gettwitterPageData(TwitUserId)
{
   
    if(checkNetworkConnection())
    {
     $('.appypie-loader').show();

                var x=0;
				sessionStorage.setItem("TwitUserId",TwitUserId);
				var wsUrl="http://"+localStorage.getItem("reseller")+"/services/soap#twitterUser";
                var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><twitterUser xmlns=\"http://'+localStorage.getItem("reseller")+'/services/soap#twitterUser/\"><username>'+TwitUserId+'</username></twitterUser></soap:Body></soap:Envelope>';
            console.log("SoapRequest"+ soapRequest);
        
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               cache: false,
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
               var strJSON = $(req.responseText).find("return").text();
               console.log(strJSON);
               var twitterData = jQuery.parseJSON(strJSON);
               if(twitterData!=null && twitterData.length)
               {
               CreateTwitterScreen(twitterData,'no');
               }
               else if(twitterData!=null && twitterData.name)
               {
              
               CreateTwitterScreen(twitterData,'Yes');
               }
               else
               {
               alertPopUp(alert_server_response_twitter,alert_data_not_found_on_server_twitter);
               }
               },
               error: function(response, textStatus, errorThrown)
               {
               console.log('error tgwitter feed-->'+response);
               alertPopUp(alert_server_response_twitter,alert_data_not_found_on_server_twitter);
               }
               });
    }
}

function CreateTwitterScreen(twitterData,noTimeLineData)
{
    var twitterStatus=twitterData;
   
    if(noTimeLineData=='Yes')
	{
        var name=twitterData.name;
        var screen_name=twitterData.screen_name;
        var followers_count=twitterData.followers_count;
        var image_Url=twitterData.profile_image_url;
        
        var twitterHeaderHtml='<div class="tweet-user"><div class="tweet-user-image"><img src="'+image_Url+'"></div><a onclick="followTwitter();" class="tweet_follow_btn">'+create_twit_screen_follow_twitter+'</a><div class="tweet-user-details"> <h3>'+name+' <span>@'+screen_name+'</span></h3><div class="tweet_options"><div class="tweet">0<span>'+create_twit_screen_tweets_twitter+'</span></div><div class="follow">'+followers_count+' <span>'+create_twit_screen_followers_twitter+'</span></div></div></div></div>';
		
        if(sessionStorage.getItem("singlePage")=="true")
        {
            appendHtml(twitterHeaderHtml,'',1);
        }
        else
        {
            appendHtml(twitterHeaderHtml,2,2);
        }
        
	}
	else
    {
    if(twitterStatus.length && twitterData[0].user!=null)
    {
            var tweeter_name=twitterData[0].user.name;
            var screen_name=twitterData[0].user.screen_name;
            var tweetsCount=twitterData[0].user.statuses_count;
            var profile_image_url=twitterData[0].user.profile_image_url;
            var followers_count=twitterData[0].user.followers_count;
        
            var twitterHeaderHtml='<div class="tweet-user"><div class="tweet-user-image"><img src="'+profile_image_url+'"></div><a href="#" onclick=\'followTwitter()\' class="tweet_follow_btn">'+create_twit_screen_follow_twitter+'</a><div class="tweet-user-details"> <h3>'+tweeter_name+' <span>@'+screen_name+'</span></h3><div class="tweet_options"><div class="tweet">'+tweetsCount+' <span>'+create_twit_screen_tweets_twitter+'</span></div><div class="follow">'+followers_count+' <span>'+create_twit_screen_followers_twitter+'</span></div></div></div></div>';
            var tweetsFeedHtml='';
            
            for (var i=0;i<twitterStatus.length;i++)
            {
                var Tweet_text = twitterStatus[i].text;
                var created_at = twitterStatus[i].created_at;
                var    createdDate=  created_at.substring(0,10);
                createdDate = createdDate +' ' +created_at.substring(26,30);
               // console.log("mediaUrl"+mediaUrl);
                var entitiesCheck=twitterStatus[i].extended_entities;
                console.log("entitiesCheck--->"+entitiesCheck);
                var urlFinal='';
                if(entitiesCheck != 'null' && entitiesCheck != null)
                {
                    console.log("in if condition");
                        var mediaUrl=entitiesCheck.media[0].media_url;
                        console.log("mediaUrl--->"+mediaUrl);
                         urlFinal='<img src="'+mediaUrl+'" onclick="openLargeImage(\''+mediaUrl+'\')">';
                        //console.log("urlFinal--->"+urlFinal);
                    
                    
                }
                tweetsFeedHtml += '<div class="tweet_feeds"><div class="tweet-user-image"><img src='+profile_image_url+'></div><div class="feeds_desc"><h3>'+tweeter_name+'<span>@ '+screen_name+'</span> </h3> <p>'+Tweet_text+urlFinal+'</p></div> </div>';
               
            }
        
        if(sessionStorage.getItem("singlePage")=="true")
        {
            appendHtml(twitterHeaderHtml+tweetsFeedHtml,'',1);
        }
        else
        {
            appendHtml(twitterHeaderHtml+tweetsFeedHtml,2,2);
        }
        
        
	}
	else
    {
        alertPopUp(alert_server_response_twitter,alert_data_not_found_on_server_twitter);
    }
	}
    
}
function reTweet(id)
{
    
}
function openTwitterLink(link)
{
    showServicePage(link);
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