//////////////////////////////////Menu/////////////////////////////////

var alert_msg_for_alert_review = "Alert";
var alert_thanks_for_review_admin_approve_review = "Thanks for submitting your review. It will be posted to the app after it has been approved by the app admin.";
var alert_msg_oops_review = "Oops";
var alert_server_not_responding_try_later_review = "Server not responding\n Please try again later";
var alert_comments_fields_cant_blank_review = "*Comments field cannot be left blank";
var enter_valid_email_address_review = "Enter a valid E-mail address";
var name_filed_cant_blank_review = "*Name field cannot be left blank";
var please_select_rating_review = "*Please select ratings";
var rate_and_review = "Rate & Review"
var name_reviews = "Reviews";

function saveReview(subject,index)
{
    var ApName=document.getElementById('reviewName').value;
    var ApEmail=document.getElementById('reviewEmail').value;
    var ApRemark=document.getElementById('reviewRemark').value;
    var owneremailreview= localStorage.getItem("owneremailreview");
    
    var r1 = $('#star1').raty('score');
    if(typeof r1 != 'undefined')
    {
    if(ApName != '' && ApName != null)
    {
        if(ApEmail != '' && ApEmail != null && checkEmail(ApEmail))
        {
            if(ApRemark != '' && ApRemark != null)
            {
			}
			  else{
                ApRemark=' ';
                  }
            	alertPopUp(alert_msg_for_alert_review,alert_thanks_for_review_admin_approve_review);
                var wsUrl="http://"+localStorage.getItem("reseller")+"/services/reviews-soap#saveReviewDetails";
                var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><saveReviewDetails xmlns="\http://'+localStorage.getItem("reseller")+'/services/reviews-soap#saveReviewDetails/\"><appId>'+localStorage.getItem("applicationID")+'</appId><name>'+ApName+'</name><emailId>'+ApEmail+'</emailId><ratting>'+r1+'</ratting><comments><![CDATA['+ApRemark+']]></comments><subject><![CDATA['+subject+']]></subject><ownerEmail>'+owneremailreview+'</ownerEmail></saveReviewDetails></soap:Body></soap:Envelope>';
                console.log("Soap xza() -->"+soapRequest);
                $.ajax({
                       type: "POST",
                       url: wsUrl,
                       contentType: "text/xml",
                       dataType: "text",
                       data: soapRequest,
                       success: function(data, status, req)
                       {
                       console.log("result 1"+req.responseText);
                       getreviewData(index)
                       },
                       error: function(response, textStatus, errorThrown)
                       {
                       alertPopUp(alert_msg_oops_review,alert_server_not_responding_try_later_review);
                       console.log(errorThrown.responseText);
                       getreviewData(index)
                       }
                       });
            
        }
        else
        {
             alertPopUp(alert_msg_for_alert_review,enter_valid_email_address_review);
        }
    }
    else
    {
        alertPopUp(alert_msg_for_alert_review,name_filed_cant_blank_review);
    }
    }
    else
    {
        alertPopUp(alert_msg_for_alert_review,please_select_rating_review);
    }
    
}
	var reviewNames='';
function getreview(index)
{
 if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    $reviewData = $(masterData).find("review[indexval="+index+"]");
    var funcUrl='';
    var counter=0;
    var htmlReviewPage='';
    $reviewData.find('yelp').each(function()
                                         {
                                  console.log("yelp");
                                         var page_url=$(this).find("yelpLink").text();
                                         funcUrl='openWebPage(\''+page_url+'\',1)';
                                         htmlReviewPage+='<span class="appypie-list" onclick="openWebPage(\''+page_url+'\',2)">'+returnImageHtml($(this).find("yelpIcon").text())+' <span>'+$(this).find("yelpName").text()+'</span></span>';
                                        counter=parseFloat(counter)+1;
                                         });
    $reviewData.find('zomato').each(function()
                                    {
                                    console.log("zomato");
                                    var page_url=$(this).find("link").text();
                                    funcUrl='openWebPage(\''+page_url+'\',1)';
                                    htmlReviewPage+='<span class="appypie-list" onclick="openWebPage(\''+page_url+'\',2)">'+returnImageHtml($(this).find("zomatoImage").text())+' <span>'+$(this).find("zomatoName").text()+'</span></span>';
                                    counter=parseFloat(counter)+1;
                                    });
    $reviewData.find('mouthshut').each(function()
                                       {
                                       console.log("mouthshut");
                                       var page_url=$(this).find("link").text();
                                       funcUrl='openWebPage(\''+page_url+'\',1)';
                                       htmlReviewPage+='<span class="appypie-list" onclick="openWebPage(\''+page_url+'\',2)">'+returnImageHtml($(this).find("mouthshutImage").text())+' <span>'+$(this).find("mouthshutName").text()+'</span></span>';
                                       counter=parseFloat(counter)+1;
                                       });
    
    $reviewData.find('reviewlist').each(function()
                                  {
                                        reviewNames=$(this).find("reviewFormName").text();
                                          console.log("reviewlist");
                                  funcUrl='getreviewData(\''+index+'\')';
                                  htmlReviewPage+='<span class="appypie-list" onclick="getreviewData(\''+index+'\')">'+returnImageHtml($(this).find("reviewIcon").text())+' <span>'+$(this).find("reviewFormName").text()+'</span></span>';
                                        counter=parseFloat(counter)+1;
                                  });
    
    if(parseFloat(counter) == 1)
    {
        window.setTimeout(funcUrl,10);
    }
    else
    {
        appendHtml(htmlReviewPage,'',1);
    }
    
}


function getreviewData(index)
{
    
    $reviewData = $(masterData).find("review[indexval="+index+"]");
    var tagEmail='Email';
    var rateNow='Rate Now';
    var tagName='Name';
    var tagComment='Comments';
    var tagSubmit='Submit'
    var tagSubject='Review';
    $reviewData.find('reviewlist').each(function()
                                        {
										localStorage.setItem('owneremailreview',$(this).find('email').text());
                                        tagEmail=$(this).find('reviewemail').text();
                                        tagComment=$(this).find('reviewcomment').text();
                                        tagSubmit=$(this).find('reviewsubmit').text();
                                        tagName=$(this).find('reviewname').text();
                                        tagSubject=$(this).find('subject').text();
                                        });
    var reviewMainPageHtml="";
    var wsUrl="http://"+localStorage.getItem("reseller")+"/services/reviews-soap#getReviewData";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getReviewData xmlns="\http://'+localStorage.getItem("reseller")+'/services/reviews-soap#getReviewData/\"><appId>'+localStorage.getItem("applicationID")+'</appId></getReviewData></soap:Body></soap:Envelope>';
    console.log("Soap "+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           console.log("result 1"+req.responseText);
           var returnData=$(req.responseText).find('return').text();
           var totalRatings=0;
           var totalReviews=0;
           var avgRatings=0;
		   if(returnData != "null")
           {
           console.log("returnData -->"+returnData);
           var obj=jQuery.parseJSON(returnData);
           console.log("obj.length --->"+obj.length);
           
           
           for(var i=0;i<obj.length;i++)
           {
           
           var name=obj[i].name;
           console.log("name --->"+name);
           var emailId=obj[i].emailId;
           console.log("emailId --->"+emailId);
           var comments=obj[i].comments;
           console.log("comments --->"+comments);
           var ratting=obj[i].ratting;
           console.log("ratting --->"+ratting);
           var date=obj[i].date;
		    var datepart = date.split(" ");
           console.log("in loop review --->"+i);
           reviewMainPageHtml+='<div class="testimonials"><div class="head_rating"><i style="width:'+ratting*20+'%;"></i></div><div class="testi-comment">'+comments+' <span class="qt"></span></div><span class="user_name">'+name+' <br><span class="post-rating-date">'+datepart[0]+'</span></span></div></div>';
           totalRatings=parseFloat(totalRatings)+parseFloat(ratting);
           totalReviews=parseFloat(totalReviews)+1;
           }
           avgRatings=parseFloat(totalRatings)/parseFloat(totalReviews);
		   avgRatings=avgRatings.toFixed(2);
            if(isNaN(avgRatings))
            {
             avgRatings=0;
            }
           else
            {
            avgRatings=parseFloat(totalRatings)/parseFloat(totalReviews);
            }
           }
               reviewNames=rate_and_review;
            if(reviewNames=="")
           {
           reviewNames=name_reviews;
           }
           var newreviewMainPageHtml='<div class="testimonials_header"> \
           <div class="testimonials_header-user-image"><img src="images/logo.png"></div> \
           <div class="testimonials_header_details"> \
           <h3>'+reviewNames+'</h3><ul>\
           <li>'+avgRatings+'<span>Ratings</span></li> \
           <li>'+totalReviews+'<span>Reviews</span></li></ul></div></div>\
           <div class="page-text"><div class="appointment rating-box"><div class="field"><label> Rating <sup>*</sup></label><div id="star1" class="star-adding"></div></div><div class="field"><label>'+tagName+'<sup>*</sup></label><input data-role="none" id="reviewName" type="text"/></div> <div class="field"><label>'+tagEmail+'<sup>*</sup></label><input data-role="none" id="reviewEmail" type="text"/></div> <div class="field"><label>'+tagComment+'<sup></sup></label><p><textarea id="reviewRemark"></textarea></p></div>  <input data-role="none" type="submit" '+primaryColor+' value="'+tagSubmit+'" onclick="saveReview(\''+tagSubject+'\',\''+index+'\')"></div>';
           
           
           appendHtml(newreviewMainPageHtml+reviewMainPageHtml+'<script>$( document ).ready(function() {$.fn.raty.defaults.path = "images";$("#star1").raty({size: 24, starOff: "star-off-big.png", starOn: "star-on-big.png", score: 0});});</script>',2,1);
           console.log("html -->"+$('#contentHolder2').html());
           //$(".testimonials").appendTo(".page-text");
           
           },
           error: function(response, textStatus, errorThrown)
           {
           alertPopUp(alert_msg_oops_review,alert_server_not_responding_try_later_review);
           console.log(errorThrown.responseText);
           }
           });
    
    //         $reviewXmlData.find("reviewRecord").each(function () {
    //reviewMainPageHtml+='<div class="review_page_details" onclick="reviewDetail(' + x +','+index +');"><span class="time"></span><h3>'+$(this).find("name").text()+'</h3><p class="mytext">'+$(this).find("comment").text()+'</p></div>';
    //
    //                                      x=x+1;
    //                                      });
    
    
}
