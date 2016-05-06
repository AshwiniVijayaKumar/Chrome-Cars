///////////////Blog/////////////////////////
var blogxmldata='';
var  alert_blog = 'Alert';
var datanotfound_blog = 'Data not found.';
var datafound_blog = 'Data not found. \n Please try after some time';

function getBlog(index){

if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    
	sessionStorage.setItem("singleBlogPage","false");
    $blogIndex = $(masterData).find( "Blog[indexval="+index+"]" );
    var blog_pages=$blogIndex.find("blog_id").text().split(',');
    console.log("blog_pages--->"+blog_pages.length);
	var blog_newpages=$blogIndex.find("blog_new_id").text().split(',');
    var htmlBlog='';
    var funcNameVar='';
    var counter=0;
    
        for(var i=0;i<=blog_pages.length-1;i++)
        {
            
            var funcName=blog_pages[i].charAt(0).toUpperCase() + blog_pages[i].slice(1);
          
            $blogIndex.find(blog_pages[i]+"Index").each(function()
                                                    {
                                              var imageUrl='';
                                                        if(blog_pages[i] == 'tumbler')
                                                        {
                                                        imageUrl=$(this).find("tumblerImageUrl").text();
                                                        }
                                                        else
                                                        {
                                                        imageUrl=$(this).find(funcName+"ImageUrl").text();
                                                        }
                                              var pageName=$(this).find(blog_pages[i]+"Label").text();
                                              var blogUrl=$(this).find(""+blog_pages[i]).text();
                                              funcNameVar='getBlogData(\''+blogUrl+'\',\''+blog_pages[i]+'\')';
                                              console.log("blogUrl--->"+blogUrl);
                                              console.log("returnImageHtml(imageUrl) --- >"+returnImageHtml(imageUrl));
                                              htmlBlog+='<span class="audio_list" onclick="getBlogData(\''+blogUrl+'\',\''+blog_pages[i]+'\')">'+returnImageHtml(imageUrl)+'<span>'+pageName+'</span></span>';
                                              counter=parseFloat(counter)+1;
                                                    });
            
            console.log("funcName --->"+funcName);
        }
	
    if(parseFloat(counter) == 1)
    {
        window.setTimeout(funcNameVar,10);
        sessionStorage.setItem("singleBlogPage","true");
    }
    else
    {
        setTimeout(function(){
                   htmlBlog="<div class='page-text'>"+htmlBlog+"</div>";
                   appendHtml(htmlBlog,'',1);
                   },100);
    }
}


function getBlogData(blogUrl,blogType)
{
if(localStorage.getItem("applicationID") == '562d8fb93ce8')
{
     toaster.openRssReader(blogUrl);
	 }
	 else
	 {

    $('.appypie-loader').show();
    if(!checkNetworkConnection())
    {
        if(sessionStorage.getItem("singleBlogPage")=="true")
        {
            onBackKeyDown();
        }
        
    }
    else
    {
	 blogUrl = blogUrl.replace(/&/g, '&amp;');
        var wsUrl="http://"+localStorage.getItem("reseller")+"/services/utility-soap#blogService";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><blogService xmlns="\http://'+localStorage.getItem("reseller")+'/services/utility-soap#blogService/\"><type>'+blogType+'</type><link>'+blogUrl+'</link><isMedia>'+1+'</isMedia></blogService></soap:Body></soap:Envelope>';
        console.log("Soap "+soapRequest);
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
               
               if(data!='error')
               {
               blogxmldata=$(req.responseText).find("return").text();
               wordpress_view();
               setTimeout(function(){
                          $('.appypie-loader').hide();
                          },2000);
               }
               else
               {
			    
               alertPopUp(alert_blog,datanotfound_blog);
               }
               },
               error: function(response, textStatus, errorThrown)
               {
			    
               alertPopUp(alert_blog,datafound_blog);
               }
               });
    }
	}
	
}

function wordpress_view()
{
    var obj = jQuery.parseJSON(blogxmldata);
    var i=0
   var blogPageHtml='';
    for( i in obj) {
        console.log(i + " (" + obj[i]+ ")");
        var title=obj[i].title;
        if(title==''||title.length==0)
        {
            title=obj[i].authur;
        }
        var authorNew=obj[i].authur;
        var description=obj[i].desc;
        var pubDateNew=obj[i].pubdate;
        var blogLink=obj[i].link;
        var smalldesc=obj[i].smalldesc;
		
		//if(description)
		//{
        //description=description.substring(0,500);
		//}
		console.log("Abhishek blog  description length "+description);
  if(description!=null &&  description.length>500)
  {
  console.log("Abhishek blog  description length grate than 500 "+description);
  description=description.substring(0,500); 
  }
        if(typeof smalldesc == 'undefined'){
            smalldesc=description;
        }
		
		if(smalldesc === null){
        smalldesc="";
         }
		
		if(pubDateNew==null)
        {
            pubDateNew='';
        }
		else
		{
		pubDateNew=pubDateNew.trim();
		}
		
		
        if(blogLink != null || blogLink != '') {
            if(window.localStorage.getItem("applicationID") == "2ad1a3249753")
        	{
                blogPageHtml+='<div class="review_page"><span class="time">'+pubDateNew+'</span><h3><a onclick="openBlogLink(\''+blogLink+'\')">'+title+'</a></h3><p onclick="openBlogLink(\''+blogLink+'\')">'+smalldesc+'</p></div>';

        	}
        	else
        	{
              //  blogPageHtml+='<div class="review_page"><span class="time">'+pubDateNew+'</span><h3><a onclick="openBlogLink(\''+blogLink+'\')">'+title+'</a></h3><span onclick="detail_screen('+(i)+')">'+smalldesc+'</span></div>';
                blogPageHtml+='<div class="review_page"><span class="time">'+pubDateNew+'</span><h3><a onclick="openBlogLink(\''+blogLink+'\')">'+title+'</a></h3><p onclick="openBlogLink(\''+blogLink+'\')">'+smalldesc+'</p></div>';
        	}
        }
        else {
            blogPageHtml+='<div class="review_page" onclick="detail_screen('+(i)+')"><span class="time">'+pubDateNew+'</span><h3>'+title+'</h3><p>'+smalldesc+'</p></div>';
        }
        i++;
        
    }
        blogPageHtml="<div class='page-text'>"+blogPageHtml+"</div>";
	    if(sessionStorage.getItem("singleBlogPage")=="true")
        {
            appendHtml(blogPageHtml,'',1);
        }
		else
        {
            appendHtml(blogPageHtml,2,2);
		}
}

function openBlogLink(blogLink) {
   
    //global function for managing website backpage,as the same code was used many times;
    sessionStorage.setItem("blogWebsiteFlag","true");
    
    if(sessionStorage.getItem("singleBlogPage") == "true")
    {
        openWebPage(blogLink,2);
    }
    else
    {
        openWebPage(blogLink,3);
    }
}


function detail_screen(i)
{
    var obj = jQuery.parseJSON(blogxmldata);
    
    var title=obj[i].title;
    if(title==''||title.length==0)
    {
        title=obj[i].authur;
    }
    var authorNew=obj[i].authur;
    var description=obj[i].desc;
    var contentImg=addHttpInLink(obj[i].contentImg);
    var blogInnerPageHtml;
	if(contentImg != null && contentImg.length > 0)
    {
    	
		/*blogInnerPageHtml='<div><div class="review_page_details blog-details-new"><ul><li><span class="time"></span><h3>'+title+'</h3><p class="mytext">'+contentImg+'</p></li></ul></div></div>';*/
     
       blogInnerPageHtml='<div><div class="review_page_details blog-details-new"><ul><li><span class="time"></span><h3>'+title+'</h3><p class="mytext">'+contentImg+'</p></li></ul></div></div>';

    }
    else
    {
	 blogInnerPageHtml='<div><div class="review_page_details blog-details-new"><ul><li><span class="time"></span><h3>'+title+'</h3><p class="mytext">'+description+'</p></li></ul></div></div>';
        //blogInnerPageHtml='<div><div class="review_page_details"><ul><li><span class="time"></span><h3>'+title+'</h3><p class="mytext">'+description+'</p></li></ul></div></div>';

    }
	
     //blogInnerPageHtml='<div><div class="review_page_details"><ul><li><span class="time"></span><h3>'+title+'</h3><p class="mytext">'+description+contentImg+'</p></li></ul></div></div>';
     blogInnerPageHtml="<div class='page-text'>"+blogInnerPageHtml+"</div>";
	if(sessionStorage.getItem("singleBlogPage")=="true")
    {
            appendHtml(blogInnerPageHtml,2,2);
    }
    else
    {
            appendHtml(blogInnerPageHtml,3,3);
    }
	$(".review_page_details a").removeClass("more-link");
} 