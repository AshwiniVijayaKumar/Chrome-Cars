/////////////////Instagram////////////////////////
var userInfo;
var instagramList;
var instagramIndexValue='';
var instagramPageIndex='';
var inatagreamImageList='';
var inatagaramData;
var moreButton = 'false';
var _index;

var resellerInitial="";
var reseller=window.localStorage.getItem("reseller");

var servernotdata_insta = 'Server does not return data';
var datanotserver_insta ='Data not found on server !';
var photo_insta = "Photo";
var follow_insta = "follow";
var following_insta = "Following";
var showmore_insta = "Show More";
var comments_insta = "comments";
var likes_insta = "likes";
var likethis_insta = "like this";
function getinstagram(index)
{

if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
 /*   $("#reload").show();
    $("#bookmark").hide();
    $("#mainbackfoodecom").hide();*/
    $('.appypie-loader').show();
	_index=index;
	instagramIndexValue=index;
	instagramPageIndex=index;
	inatagreamImageList='';   
	token_Instagram(index,index); 

}

function token_Instagram(indexValue,pageIndex)
{
    
	
	$(masterData).find("Instagram").each(function () {
		var id = $(this).attr('indexval');
		if(id==indexValue)
		{
		    var InstagramUserId =$(this).find("InstagramUserId").text();
			var instagramURL ='http://'+window.localStorage.getItem("reseller")+'/mobile_api/instagram.php?username='+InstagramUserId;
			MyWebservice_Instagram(instagramURL,indexValue,pageIndex,-1);
		}
   });
}

function MyWebservice_Instagram(path,indexValue,pageIndex,instagram_indx)
{
    
 var xhr = jQuery.ajax({
                       url : path,
                       async : false,
                       dataType : 'json',
                       cache:false,
                       }).responseText;
    if(xhr!= null)
    {
        var jsonData=xhr.split("like$$$@$$$&&");
        console.log("Length : "+jsonData.length+">>>>>>"+jsonData[0]+">>>>"+jsonData[1]);
        if(jsonData.length>1)
        {
         userInfo = jQuery.parseJSON(jsonData[0]);
         instagram_indx=instagram_indx+1;
         inatagaramData = jsonData[1];
         inatagaramData = inatagaramData +"like$$$@$$$&&";
        }else
        {
            instagram_indx=instagram_indx+1;
            inatagaramData = inatagaramData + xhr;
            inatagaramData = inatagaramData +"like$$$@$$$&&";
            
        }
    
        console.log(inatagaramData);
        instagram_Screen(indexValue,pageIndex,instagram_indx);
    }
	else {
		
		
		alertPopUp(servernotdata_insta,datanotserver_insta);
		  
		}
}

var obj1;
var dataIndex;

function instagram_Screen(x,pageIndex,instagram_indx)
{
	
	
	
    var header_Inatagram='';
	inatagramHTML = '<div class="instagram-user"> \
    <div class="instagram-user-image"><img src="'+userInfo.data.profile_picture+'"></div> \
    <div class="instagram-user-details"> \
    <h3>'+userInfo.data.full_name+'</h3> \
    <div class="instagram_options"> \
    <div class="insta_photo">'+userInfo.data.counts.media+' <span>'+photo_insta+'</span></div> \
    <div class="insta_follow">'+userInfo.data.counts.follows+' <span>'+follow_insta+'</span></div> \
    <div class="insta_following">'+ userInfo.data.counts.followed_by +' <span>'+following_insta+'</span></div> \
    </div> \
    </div> \
    </div> \
    <div class="instagram_feeds_main"> \
    <div class="instagram_feeds">  <div class="photos"> ';
    console.log(inatagaramData);
    var inatagaramData_ = inatagaramData.split("like$$$@$$$&&");   
    instagramList=jQuery.parseJSON(inatagaramData_[instagram_indx]);
    console.log(instagramList.data.length);
    for(var intagaramIndex=0;intagaramIndex<instagramList.data.length ;intagaramIndex++ )
    {
        var imageURL=instagramList.data[intagaramIndex].images.thumbnail.url;
        inatagreamImageList += '<div class="photo"><a  ><img border="0" onclick="instagram_Photo('+intagaramIndex+','+instagram_indx+');" src="'+imageURL+'"></a></div>';
    
    }
    inatagramHTML +=inatagreamImageList;
	inatagramHTML += '</div></div> ';
	console.log(nextURl);
	var nextURl=instagramList.pagination.next_url;
	if(nextURl != null && nextURl != '')
    inatagramHTML += '<div class="page-text"><a class="show-more" onclick="instagram_showmore('+instagram_indx+');">'+showmore_insta+'</a></div>';
    inatagramHTML += ' </div>';
    appendHtml(inatagramHTML,'',1);
}

function instagram_showmore(instagram_indx)
{
    $('.appypie-loader').show();
    moreButton = 'true';
    setTimeout(function() {
		var nextURl=instagramList.pagination.next_url;
		console.log(nextURl);
		if(nextURl != null && nextURl != '')
		{
			var showMoreURL= 'http://apps.appypie.com/mobile_api/instagram.php?page=1&next='+encodeURIComponent(nextURl);

			showMoreButton=1;
			MyWebservice_Instagram(showMoreURL,instagramIndexValue,instagramPageIndex,instagram_indx);  
		}
		else
		{
		$('.appypie-loader').hide();
			 console.log('error');
		}   
    }, 100);
}
var _index_instagram;
var _pageRECindex;
function backtoinstagram_Photo()
{
	instagram_Photo(_index_instagram,_pageRECindex);
}
function instagram_Photo(index_instagram,pageRECindex)
{
    if(window.localStorage.getItem("layout")=="slidemenu"){
				$("#reload").hide();
				$("#mainback").show();	
			}
			else {
				$("#logo").hide();
				$("#mainback").show();	
			}
    $('.appypie-loader').show();
	_index_instagram=index_instagram;
	_pageRECindex=pageRECindex;
    var inatagaramData_ = inatagaramData.split("like$$$@$$$&&");
    instagramList=jQuery.parseJSON(inatagaramData_[pageRECindex]);
    var imageURL=instagramList.data[index_instagram].images.standard_resolution.url;
    var likesOnPhoto=instagramList.data[index_instagram].likes.count;
    var commentsOnPhoto=instagramList.data[index_instagram].comments.count;  
    var Like_Photo='<section class="page-content"> \
    <div class="instagram-user"> \
    <div class="instagram-user-image"><img src="'+userInfo.data.profile_picture+'"></div> \
    <div class="instagram-user-details"> \
    <h3>'+userInfo.data.full_name+'</h3> \
    <div class="instagram_options"> \
    <div class="insta_photo">'+userInfo.data.counts.media+' <span>'+photo_insta+'</span></div> \
    <div class="insta_follow">'+userInfo.data.counts.follows+' <span>'+follow_insta+'</span></div> \
    <div class="insta_following">'+ userInfo.data.counts.followed_by +' <span>'+following_insta+'</span></div> \
    </div> \
    </div> \
    </div> \
    <div class="instagram_feeds_main"> \
    <div class="instagram_feeds"> \
    <p class="insta_pic-deatils"></p> \
    <div class="photos_details"> \
    <img border="0" src="'+imageURL+'"> ';
    console.log(likesOnPhoto + '==================='+ commentsOnPhoto);
    if(likesOnPhoto!='0' && commentsOnPhoto!='0')
    {
    console.log('1');
    Like_Photo+='<div class="photo_options"><div class="pic_like"><a onclick="instagram_Like('+index_instagram+','+pageRECindex+');" >'+likesOnPhoto+' <span>'+likes_insta+'</span></a></div> <div class="pic_comment"><a onclick="instagram_Comment('+index_instagram+','+pageRECindex+');" >'+commentsOnPhoto+' <span>'+comments_insta+'</span></a></div></div> ';
    }
    else if(likesOnPhoto=='0' && commentsOnPhoto=='0')
    {
    console.log('4');
    Like_Photo+='<div class="photo_options"><div class="pic_like">'+likesOnPhoto+' <span>'+likes_insta+'</span></div> <div class="pic_comment">'+commentsOnPhoto+' <span>'+comments_insta+'</span></div></div> ';
    }
    else if(likesOnPhoto=='0')
    {
       console.log('2');
     Like_Photo+='<div class="photo_options"><div class="pic_like">'+likesOnPhoto+' <span>'+likes_insta+'</span></div> <div class="pic_comment"><a onclick="instagram_Comment('+index_instagram+','+pageRECindex+');" >'+commentsOnPhoto+' <span>'+comments_insta+'</span></a></div></div> ';
    }
    else if(commentsOnPhoto=='0')
    {
       console.log('3');
     Like_Photo+='<div class="photo_options"><div class="pic_like"><a onclick="instagram_Like('+index_instagram+','+pageRECindex+');" >'+likesOnPhoto+' <span>'+likes_insta+'</span></a></div> <div class="pic_comment">'+commentsOnPhoto+' <span>'+comments_insta+'</span></div></div> ';
    }
    Like_Photo+='</div> \
    </div> \
    </div> \
    </section>';
    appendHtml(Like_Photo,2,2);
    
}
function instagram_Like(index_instagram,pageRECindex)
{
   if(window.localStorage.getItem("layout")=="slidemenu"){
				$("#reload").hide();
				$("#mainback").show();	
			}
			else {
				$("#logo").hide();
				$("#mainback").show();	
			}
    $('.appypie-loader').show();
	var inatagaramData_ = inatagaramData.split("like$$$@$$$&&");
    instagramList=jQuery.parseJSON(inatagaramData_[pageRECindex]);
    var Like_HTML='<section class="page-content">  \
    <div class="instagram-user"> \
    <div class="instagram-user-image"><img src="'+userInfo.data.profile_picture+'"></div> \
    <div class="instagram-user-details"> \
    <h3>'+userInfo.data.full_name+'</h3> \
    <div class="instagram_options"> \
    <div class="insta_photo">'+userInfo.data.counts.media+' <span>'+photo_insta+'</span></div> \
    <div class="insta_follow">'+userInfo.data.counts.follows+' <span>'+follow_insta+'</span></div> \
    <div class="insta_following">'+ userInfo.data.counts.followed_by +' <span>'+following_insta+'</span></div> \
    </div> \
    </div> \
    </div> \
    <div class="instagram_feeds_main"> \
    <div class="instagram_feeds"> \
    <ul> ';
    var numberOfLikes = instagramList.data[index_instagram].likes.data.length
    for(var i=0;i<numberOfLikes;i++)
    {
        var userImage = instagramList.data[index_instagram].likes.data[i].profile_picture;
        var full_name = instagramList.data[index_instagram].likes.data[i].full_name;
        
        Like_HTML +='<li><img class="like_pic" src="'+userImage+'"><h2>'+full_name+'</h2>'+likethis_insta+'</li>';
    }
    
    Like_HTML +='</ul> \
                </div> \
                </div> \
                </section>';
    console.log('Like_HTML=============>'+Like_HTML);
    appendHtml(Like_HTML,3,3);
}
function instagram_Comment(index_instagram,pageRECindex)
{
   if(window.localStorage.getItem("layout")=="slidemenu"){
				$("#reload").hide();
				$("#mainback").show();	
			}
			else {
				$("#logo").hide();
				$("#mainback").show();	
			}
    $('.appypie-loader').show();
	console.log(inatagaramData);
    var inatagaramData_ = inatagaramData.split("like$$$@$$$&&");
    instagramList=jQuery.parseJSON(inatagaramData_[pageRECindex]);
    var Like_HTML='<section class="page-content">  \
    <div class="instagram-user"> \
    <div class="instagram-user-image"><img src="'+userInfo.data.profile_picture+'"></div> \
    <div class="instagram-user-details"> \
    <h3>'+userInfo.data.full_name+'</h3> \
    <div class="instagram_options"> \
    <div class="insta_photo">'+userInfo.data.counts.media+' <span>'+photo_insta+'</span></div> \
    <div class="insta_follow">'+userInfo.data.counts.follows+' <span>'+follow_insta+'</span></div> \
    <div class="insta_following">'+ userInfo.data.counts.followed_by +' <span>'+following_insta+'</span></div> \
    </div> \
    </div> \
    </div> \
    <div class="instagram_feeds_main"> \
    <div class="instagram_feeds"> \
    <ul> ';
    var numberOfLikes = instagramList.data[index_instagram].comments.data.length
    for(var i=0;i<numberOfLikes;i++)
    {
        var userImage = instagramList.data[index_instagram].comments.data[i].from.profile_picture;
        var full_name = instagramList.data[index_instagram].comments.data[i].from.full_name;
        var text=instagramList.data[index_instagram].comments.data[i].text;
        Like_HTML +='<li><img class="like_pic" src="'+userImage+'"><h2>'+full_name+'</h2>'+text+'</li>';
    }
    
    Like_HTML +='</ul> \
                </div> \
                </div> \
                </section>';
    appendHtml(Like_HTML,3,3);
}