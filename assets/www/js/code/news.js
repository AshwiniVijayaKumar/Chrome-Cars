var wsUrl='';
var soapRequest='';
var html='';
var catItemsListArray=new Array();
var checkedArray=new Array();
var homeCategoryNews_array = new Array();
var first=1;
var appid='';
var pageId='';
var accountDispl='block';
var loginDispl='block';
var email='';
var newsHeading='';
var newsFullStory='';
var id='';
var selectedCat=new Array();
var s_text='';
var page = '';
var check_read="";
var Bookmark_array = new Array();
var readUnread = new Array();

var news_id_array =new Array();

var invalidloginidorpassword_news = "Invalid login id or password.";
var email_news = "Email";
var password_news = "Password";
var login_news = "LOGIN";
var forgotpassword_news = "Forgot password?";
var signup_news = "Sign up";	
var name_news = "Name *";
var emaill_news = "Email *";
var phonenumber_news = "Phone Number";
var confirmpassword_news = "Confirm Password";
var alreadyhaveanaccount_news  = "Already have an account? ";
var signin_news = "Sign In";
var Wehavesentemailemailid_news = "We have sent an email to your email id";
var enteryouremail_news = "Enter your email";
var resetpassword_news = "Reset Password";
var signupnow_news = "Sign up Now";
var donothaveanaccount_news  = "Do not have an account? ";
var error_news = "Error";
var pleaseenterfirstname_news = "Please Enter First Name.";
var pleaseentervalidemailid_news = "Please Enter Valid Email Id.";
var pleaseenterphonenumber_news = "Please Enter Phone Number.";
var pleaseentervalidphonenumber_news = "Please Enter Valid Phone Number.";
var pleaseenterpassword_news = "Please Enter Password."; 
var confirmpasswordnotmatch_news = "Confirm Password do not match.";
var congratulations_news = "Congratulations!"; 
var youaresuccessfullyregistered_news = "You are successfully registered";
var someonealreadyusername_news = "Someone already has that username.";
var alert_news = 'Alert !';
var passwordresetsuccessfullpassword_news = 'Password Reset was successfull,\nPlease check your email for new password.';
var youhavenotbeenregistered_news = 'You have not been registered with us yet.';
var invalidemailddress_news = 'Invalid Email Address...';
var ok_news = "OK";
var sorry_news = "Sorry!";
var pleaseentercorrectpassword_news = "Please enter your correct password";
var younotregistered_news = "you are not registered";
var nohomepagenewsavailable_news = "No homepage news available";
var nonewsavailable_news = "No news available";
var searchnews_news = "Search News";
var settings_news = "Settings";
var choosewhichpushalertsreceive_news = "Choose which push alerts you would like to receive. These preferences can always be changed later.";
var alertsneeded_news = "Alerts needed";
var none_news = " None ";
var all_news = " All  ";
var everydays_news = "  Messages Per Day "; 
var dontsendalert_news = "Don\'t send alert between";
var morealeroptions_news = "More alert options";
var youcategoriesyourinterests_news = "You can also choose any of the following categories to get alerts based on your interests.";
var categories_news = "Categories";
var news_news = " News ";
var sports_news = " Sports ";
var entertainment_news = " Entertainment ";
var tech_news = " Tech ";
var save_news = " Save ";
var bookmark_news = "Bookmark";
var articlehas_news = " This Article has ";
var postcomment_news = "Post a comment";
var small_news = "Small";
var normal_news = "Normal";
var large_news = "Large";
var nobookmarknewsavailable_news = "No bookmark news available";
var pleaseentercomment_news = "Please enter your comment";
var postcommenthere_new = "Post your comment here";
var pleaseenterstarttime_news = "Please Enter Start time.";
var pleaseenterendtime_news = "Please Enter End time.";
var endtimealwaysgreater_news = "End time always greater than Start time.";
var pleaseselectcheckbox_news = "Please select the checkbox.";
var ccongratulation_news = "Congratulation";
var settingsaddedsuccessfully_news = "Settings added successfully";
var nonewsfoundcategory_news = "No news found in this category.";
var pleaseenteryourkeyword_news = "Please enter your keyword.";






function getnews(index)
{
 if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
	try{
	readUnread.push(",");
	localStorage.setItem("ashu1",readUnread);
	}catch(err){
		console.log("getNews Error: "+err);
	}
	//window.toaster.startGCM();
    var uuid = toaster.getGCMId();
	console.log("UUID...>> "+uuid);
	localStorage.setItem("UUID", uuid);
	sessionStorage.setItem("AppPageName","news");
	dynamicCSS('news');
	showHideAd("hide");
	$("#shopmenu").hide();
	$("body").addClass("news");
	
	$.mobile.resetActivePageHeight();
    sessionStorage.setItem("indexValue",index);
    var appid = localStorage.getItem("applicationID");

    var html="";
    var appName = localStorage.getItem("AppName");

    console.log("index--->"+index);
    $socialwallData=$(masterData).find("news[indexval="+index+"]");
    console.log(" in loop condition nishant ---->");
    
    pageId= $socialwallData.find("pageId").text();

    localStorage.setItem("ashu",pageId);
    console.log(" in if condition indexValWall ---->"+pageId);
    
    console.log("indexValWall ---->"+pageId);
    localStorage.setItem("news_page_id",pageId);
    console.log("asasasasasasasasasasasasasasas"+index);
   
    setting_catList();
  
//     document.getElementsByTagName('html')[0].style.backgroundImage = 'url(images/white_news_back.png)';
    
    if(localStorage.getItem("fooduserid"))
    {
    	applogin_y();
    }
    news();
}


//============= app login ==============================//

function applogin_y()
{
  var id = localStorage.getItem("fooduserid");
  localStorage.setItem("newsuserid",id);
  
  var appid = localStorage.getItem("applicationID");
  if(checkNetworkConnection())
  {
      
      var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/news-soap#getUserIdNews";
      var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getUserIdNews xmlns=\"http://'+localStorage.getItem("reseller")+'/services/news-soap#getUserIdNews\"><appId>'+appid+'</appId><emailId>'+id+'</emailId></getUserIdNews></soap:Body></soap:Envelope>';
      console.log("------------------------------------->"+wsUrl);
      
      console.log("------------------------------------->"+soapRequest);
      $.ajax({
             type: "POST",
             url: wsUrl,
             contentType: "text/xml",
             dataType: "text",
             data: soapRequest,
             success: function(data, status, req)
             {
             var strJSON = $(req.responseText).find("return").text();
             console.log('strJSON=A==============shutosh=>'+strJSON);
             localStorage.setItem("user_id",strJSON);
             var id_email = localStorage.getItem("fooduserid");
             localStorage.setItem("newsuserid",id_email);
             },
             error: function(response, textStatus, errorThrown)
             {
             //                   //alert("error");
             console.log("Error ========================: "+JSON.stringify(response));
             console.log("Error : "+textStatus);
             console.log("Error : "+errorThrown.responseText);
             setTimeout(function(){$('.appypie-loader').hide();},1000);
             console.log(response);
             }
             });
  }
}


//==========================login page--------//

function login_firstpage()
{
	
var appName = localStorage.getItem("AppName");
 
//  var loginHtml='<section class="home_screen"><div class="page-text"><div id="scroll_contener" class="scroll_contener"><div class="appypie-login">\<h2>Sign In</h2><div class="cart-MSG" id="cart-MSG" style="display:none"><div class="cart_error2">Invalid login id or password.</div>\</div><div class="login-feald"><label>Email ID</label><input data-role="none" type="text" id="loginid"/></div><div class="login-feald"><label>Password</label><input data-role="none" type="password" id="loginpass" /></div>\<div class="login-feald"><a class="login_btn" onclick="checkUserLoginDetails1()">Login</a> <a onclick="newsForgotPassView()">Forgot your password?</a></div>\<div class="or-feald"><span>OR</span></div><div class="fb-div"><br clear="all" />Do not have an account? <a onclick="newsSignUpView()">Sign up Now</a></div>\</div></div></div></section>';
  
  var headerHTML = ' <header class="newsHeader hyper-local header-main " style=" position:fixed; left:0; top:0">  <a class="hyper-back" href="#" onclick="news();"></a>\ <h1>'+appName+'</h1> </header>';
  
  var loginHtml = "";
  loginHtml += headerHTML+'<div class="login"><div class="login_box">\
  <div class="login_from">\
  <div class="cart-MSG" id="cart-MSG" style="display:none"><div class="cart_error2">'+invalidloginidorpassword_news+'</div></div>\
  <input data-role="none" type="text" id="loginid" class="mail" placeholder="'+email_news+'"  />\
  <input data-role="none" type="password" id="loginpass" class="pass" placeholder="'+password_news+'" />\
  <input type="button" class="button" value="'+login_news+'" onclick="checkUserLoginDetails1()">\
  <div class="links-view">\
  <a href="#" class="f-left" onclick="newsForgotPassView()">'+forgotpassword_news+'</a>\
  <a href="#" class="f-right" onclick="newsSignUpView()">'+signup_news+'</a>\
  </div>\
  </div>\
  </div></div>'
  appendHtml(loginHtml,2,2);
  resetNewsHeader();
 

}



function newsSignUpView() {
 
	
	
//  var index = sessionStorage.getItem("indexValue");
//  foodsignup_HTML='<section class="home_screen"><div class="page-text"><div id="scroll_contener" class="scroll_contener">\
//   <div class="appypie-login"><h2>Sign Up Now</h2><div class="cart-MSG" id="cart-MSG" style="display:none">\
//   <div class="cart_error"></div></div><div class="cart-MSG" id="cart-MSG2" style="display:none">\
//   <div class="cart_success2"></div></div><div class="login-feald"><label>Name<sup class="red-astrick">*</sup></label>\
//  <input data-role="none" type="text" id="fname" /></div><div class="login-feald"><label>Email ID<sup class="red-astrick">*</sup></label>\
//   <input data-role="none" type="text" id="emailId" /></div><div class="login-feald"><label>Phone No<sup class="red-astrick">*</sup></label>\
//  <input data-role="none" type="text" id="pNo" /></div><div class="login-feald"><label>Password<sup class="red-astrick">*</sup></label>\
//  <input data-role="none" type="password" id="pass"/></div><div class="login-feald"><label>Confirm  Password<sup class="red-astrick">*</sup></label>\
//  <input data-role="none" type="password" id="cpass"/></div><div class="login-feald"><a class="submit_btn" onclick="addNewUser()">Sign Up</a>\
//  </div><br /><div class="fb-div">Already have an account? <a onclick="getnews('+index+')">Sign In</a></div></div></div>\
//  </div></section>';
  
//  appendHtml(foodsignup_HTML,'','','');
  
  
  var appName = localStorage.getItem("AppName");
  var headerHTML = ' <header class="newsHeader hyper-local header-main ">  <a class="hyper-back" href="#" onclick="onBackKeyDown();"></a>\ <h1>'+appName+'</h1> </header>';
  $('.appypie-loader').show();
  ecomsignup_HTML='<div class="login">'+headerHTML+'<div class="login_box"><div class="cart-MSG" id="cart-MSG" style="display:none"><div class="cart_error"></div></div>\
  <div class="cart-MSG" id="cart-MSG2" style="display:none"><div class="cart_success2"></div></div>\
  <div class="login_from sign-up"><input data-role="none" type="text" id="fname" placeholder="'+name_news+'" />\
  <input data-role="none" type="text" id="emailId" placeholder="'+emaill_news+'" />\
  <input data-role="none" type="text" id="pNo" placeholder="'+phonenumber_news+'" />\
  <input data-role="none" type="password" id="pass" placeholder="'+password_news+'" />\
  <input data-role="none" type="password" id="cpass" placeholder="'+confirmpassword_news+'" />\
  <input type="button" class="button" value="'+signup_news+'" onclick="addNewUser()">\
  <div class="links-view"><p>'+alreadyhaveanaccount_news+'<a onclick="login_firstpage()">'+signin_news+'</a></p></div></div></div></div>';
  appendHtml(ecomsignup_HTML ,3,3);
 resetNewsHeader();
}
function newsForgotPassView() {
	
	
		
//  var index = sessionStorage.getItem("indexValue");
//  foodforgotpass_HTML='<section class="home_screen"><div class="page-text"><div id="scroll_contener" class="scroll_contener"><div class="appypie-login forgot-pss"><h2>Forgot Password</h2><div class="cart-MSG" id="passwordError" style="display:none"><div class="cart_error"></div></div><div class="cart-MSG" id="cart-MSG1" style="display:none"><div class="cart_success">We have sent an email to your email id</div></div><div class="login-feald"><label>Email ID</label><input data-role="none" type="text" id="forpassid" /></div><br /><div class="login-feald"><a class="submit_btn" onclick="sendPasswordNews()">Reset Password</a></div><br /><div class="fb-div">Do not have an account? <a onclick="newsSignUpView()">Sign up Now</a><br /><br />Already have an account? <a onclick="getnews('+index+')">Sign In</a></div></div></div></div></section>';
//  appendHtml(foodforgotpass_HTML,'','','');
  var appName = localStorage.getItem("AppName");
  $('.appypie-loader').show();
  var headerHTML = ' <header class="newsHeader hyper-local header-main">  <a class="hyper-back" href="#" onclick="onBackKeyDown();"></a>\ <h1>'+appName+'</h1> </header>';
  ecomforgotpass_HTML='<div class="login">'+headerHTML+'<div class="login_box">\
  <div class="cart-MSG" id="passwordError" style="display:none"><div class="cart_error"></div></div>\
  <div class="cart-MSG" id="cart-MSG1-forgot" style="display:none"><div class="cart_success">'+Wehavesentemailemailid_news+'</div></div>\
  <div class="login_from forgot-password">\
  <input data-role="none" type="text" id="forpasid" placeholder="'+enteryouremail_news+'" />\
  <input type="button" class="button" value="'+resetpassword_news+'"  onclick="sendPasswordNews()">\
  <div class="links-view">\
  <p>'+donothaveanaccount_news+'<a onclick="newsSignUpView()">'+signupnow_news+'</a></p>\
  <p>'+alreadyhaveanaccount_news+'<a onclick="login_firstpage()">'+signin_news+'</a></p>\
  </div>\
  </div></div></div>';
  appendHtml(ecomforgotpass_HTML ,3,3);
  resetNewsHeader();
}


//====================================================Signup=====================================================//


function addNewUser()
{
  if(checkNetworkConnection())
  {
      var fname=document.getElementById("fname").value;
      var pNo=document.getElementById("pNo").value;
      var emailId=document.getElementById("emailId").value;
      var pass=document.getElementById("pass").value;
      var cpass=document.getElementById("cpass").value;
      
      
      
      if(fname=='' || fname=='First Name')
      {
    	  
          
    	  
    	  alertPopUp(error_news,pleaseenterfirstname_news);
          return;
      }
      else if(!checkEmail(emailId) || emailId=='')
      {
          alertPopUp(error_news,pleaseentervalidemailid_news);
          return;
      }
    /*  else if(pNo=='' || pNo=='Phone Number' )
      {
          alertPopUp(error_news,pleaseenterphonenumber_news);
          return;
      }
	  */
      else if(isNaN(pNo))
      {
          alertPopUp(error_news,pleaseentervalidphonenumber_news);
          return;
      }
      else if(pass==''  || pass=='Password')
      {
          alertPopUp(error_news,pleaseenterpassword_news);
          return;
      }
      else if(cpass!=pass)
      {
          alertPopUp(error_news,confirmpasswordnotmatch_news);
          return;
      }
      else
      {
          userAdd1(fname,pNo,emailId,pass);
          
      }
  }
}

function checkEmailNews(email) {
  
  var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  
  if (!filter.test(email)) {
      return false;
  }
  return true;
}
function userAdd1(fname,pNo,emailId,pass)
{
   var appid = localStorage.getItem("applicationID");
  if(checkNetworkConnection())
  {

      var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/app-user-soap#customerRegistration";
      var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><customerRegistration xmlns=\"http://'+localStorage.getItem("reseller")+'/services/app-user-soap#customerRegistration\"><appId>'+appid+'</appId><email>'+emailId+'</email><password>'+pass+'</password><fname>'+fname+'</fname><lname>foodorder</lname><phone>'+pNo+'</phone></customerRegistration></soap:Body></soap:Envelope>';
       console.log("------------------------------------->"+wsUrl);
     
      console.log("------------------------------------->"+soapRequest);
      $.ajax({
             type: "POST",
             url: wsUrl,
             contentType: "text/xml",
             dataType: "text",
             data: soapRequest,
             success: function(data, status, req)
             {
             var strJSON = $(req.responseText).find("return").text();
             console.log('strJSON=Ashutosh=>'+strJSON);
             ////alert("registration response===="+strJSON);
             var result=strJSON.split("_");
             console.log("1234567"+result[0]);
             if(result[0]=="success")
             {
            	
             alertPopUp(congratulations_news,youaresuccessfullyregistered_news);
             var index = sessionStorage.getItem("indexValue");
             login_firstpage();
            
             }
             else{
             alertPopUp("",someonealreadyusername_news);
             return;
             }
             },
             error: function(response, textStatus, errorThrown)
             {
             setTimeout(function(){$('.appypie-loader').hide();},1000);
             console.log(response);
             }
             });
  }
}


//==========================================Send Password===================================================//

function sendPasswordNews()
{
	
  if(!checkNetworkConnection())
  {
  }else
  {
      $('.appypie-loader').show();
      var emailid=document.getElementById("forpasid").value;
      if(!checkEmail(emailid) || emailid=='')
      {
          alertPopUp(alert_news,pleaseentervalidemailid_news);
          setTimeout(function(){$('.appypie-loader').hide();},1000);
          
      }
      else
      {
          var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/app-user-soap#forgetPassword";
          var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><forgetPassword xmlns=\"http://'+localStorage.getItem("reseller")+'/services/app-user-soap#forgetPassword\"><appId>'+localStorage.getItem("applicationID")+'</appId><email>'+emailid+'</email></forgetPassword></soap:Body></soap:Envelope>';
          
          
          console.log("forgetPassword===="+soapRequest);
          $.ajax({
                 type: "POST",
                 url: wsUrl,
                 contentType: "text/xml",
                 dataType: "text",
                 data: soapRequest,
                 success: function(data, status, req)
                 {
                 var strJSON = $(req.responseText).find("return").text();
                 if(strJSON!='NotExist')
                 {
                 alertPopUp(alert_news,passwordresetsuccessfullpassword_news);
                 }
                 else
                 {
                 
                 alertPopUp(alert_news,youhavenotbeenregistered_news);
                 
                 setTimeout( function(){
                            $("#forpasid").focus();
                            }
                            , 3000 );
                 
                 }
                 setTimeout(function(){$('.appypie-loader').hide();},1000);
                 },
                 error: function(response, textStatus, errorThrown)
                 {
                 console.log("Error==="+response);
                 setTimeout(function(){$('.appypie-loader').hide();},1000);
                 }
                 });
      }
  }
}
//=================================================LoginPage=====================================================================//


function checkUserLoginDetails1()
{
	
	
  if(checkNetworkConnection())
  {
      
      $('.appypie-loader').show();
      var emailid=document.getElementById("loginid").value;
      var pass=document.getElementById("loginpass").value;
      if(!checkEmailNews(emailid) || emailid=='')
      {
          setTimeout(function(){$('.appypie-loader').hide();},1000);
          navigator.notification.alert(invalidemailddress_news, setTimeout, error_news, ok_news);
          $("#emailid").focus();
          
          return;
      }
      else if(pass==''  || pass=='Password')
      {
          setTimeout(function(){$('.appypie-loader').hide();},1000);
          navigator.notification.alert(pleaseenterpassword_news, setTimeout, error_news, ok_news);
          $("#pass").focus();
          return;
      }
      else
      {
          checklogin1(emailid,pass);
      }
  }
}
function checklogin1(emailid,pass)
{
   var appid = localStorage.getItem("applicationID");
 
  if(checkNetworkConnection())
  {
      
      var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/app-user-soap#customerLogin";
      var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><customerLogin xmlns=\"http://'+localStorage.getItem("reseller")+'/services/app-user-soap#customerLogin\"><email>'+emailid+'</email><password>'+pass+'</password><appid>'+appid+'</appid></customerLogin></soap:Body></soap:Envelope>';
      console.log("123456782345678=============="+soapRequest);
      $.ajax({
             type: "POST",
             url: wsUrl,
             contentType: "text/xml",
             dataType: "text",
             data: soapRequest,
             success: function(data, status, req)
             {
             setTimeout(function(){$('.appypie-loader').hide();},1000);
             
             console.log("req.responseText********************"+req.responseText);
             var strJSON = $(req.responseText).find("return").text();
             console.log("***************new*****"+strJSON);
             
             
             if(strJSON!='NotExist')
             {
             if(strJSON!='wrong password')
             {

             localStorage.setItem("newsuserid",emailid);
             localStorage.setItem("user_id",strJSON);
             var page_redirect = localStorage.getItem("login_redirect");
//             alert(page_redirect);
             if(page_redirect == "setting")
             {
                  setting();
                  news();
             }
             if(page_redirect =="comment")
             {
                  var id = localStorage.getItem("data");
//                  alert(id);
                  show_getComment(id);
             }
             }
             else
             {
            	
             alertPopUp(sorry_news,pleaseentercorrectpassword_news);
             }
             }
             else
             {
             alertPopUp(sorry_news,younotregistered_news);
             return;
             }
             },
             error: function(response, textStatus, errorThrown)
             {
             console.log(response);
             setTimeout(function(){$('.appypie-loader').hide();},1000);
             }
             });
  }
}

//=====================================news Page(TOP 5)==============================================//


function news()
{
   
    $(".appyHeader").hide();
    page = 1;
//    alert('aa');
    $('.appypie-loader').show();
    var appid = localStorage.getItem("applicationID");
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/news-soap#homeCategoryNews";
   
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><homeCategoryNews xmlns=\"http://'+localStorage.getItem("reseller")+'/services/news-soap#homeCategoryNews\"><appId>'+appid+'</appId><pageId>'+pageId+'</pageId><page>'+page+'</page></homeCategoryNews></soap:Body></soap:Envelope>';
    
    console.log('searchProducts---------->>>>'+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           
           console.log('topNewsRes12456ponse--->strJSON---------->>>>'+strJSON);
           if(strJSON == "no homepage category available")
           {
            alertPopUp(sorry_news,nohomepagenewsavailable_news);
           return;
           }
           else{
            ShowNews(strJSON,page);
           }
          
           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           console.log("Error ========================: "+JSON.stringify(response));
           console.log("Error : "+textStatus);
           console.log("Error : "+errorThrown.responseText);
           }
           });
    
}

function ShowNews(xml,page)
{
	
	
//    alert("ashu mishra showNews");
    //$( "[data-role='header']" ).remove();
    //$( "[data-role='header']" ).toolbar('destroy');
    //$( "[data-role='footer']" ).toolbar('destroy');
    //$.mobile.resetActivePageHeight();
    //$('#page3').attr("style","padding:0px !important");
    
    var appName = localStorage.getItem("AppName");
    var xmll = xml;
    var page_val = page;
    news_id_array.length=0;
    var count=0;
    var xml =jQuery.parseXML(xmll);
    var openHtml = '<div id="news-home" data-snap-ignore="true"><header class="newsHeader"><a href="#" class="navs"></a><h1>'+appName+'</h1><a onclick="newsSettingShow()" class="settings"></a></header><section>';
     var alert_1 =0;
    var q=0;
    var news_body_uper = '<div class="swiper-container"  id="news-swiper"><div class="swiper-wrapper">';
    var news_body_mid = '';
    $(xml).find("homeCategoryNewsList").each(function () {
                                    
                                    
                                    var $list = $(this);
                                $list.find("categoryList").each(function () {
                                            var $list = $(this);
                                                           var catId= $(this).find("catId").text();
                                                           var catName= $(this).find("catName").text();
                                                           if( q >= 4)
                                                           {
                                                            return;
                                                           }
                                                           homeCategoryNews_array[q]=catId+','+catName;
                                                           q++;
                                                           news_body_mid+=' <div class="swiper-slide news-detail" index="'+catId+'"><ul>';
                                                               console.log("Abhi... >>>> NewsCat "+catName+" Length "+q);
                                    $list.find("newsList").each(function () {
										                   alert_1++;
//                                                                                                                               alert("ashu");
                                                               
           													 var newsId= $(this).find("newsId").text();

                                                                var newsHeading= $(this).find("newsHeading").text();
																//deepak
																
																
																 var color_li = "#D96666";
                                                                                                         var arr = localStorage.getItem("ashu1");
                                                                                                         readUnread = arr.split(",");
                                                                                                         for(var i=0;i<readUnread.length;i++)
                                                                                                         {
                                                                                                         console.log('the value of the read unread 12345678---'+readUnread[i]);
                                                                                                         if(newsId == readUnread[i])
                                                                                                         {
                                                                                                         color_li ="white";
                                                                                                         break;
                                                                                                         }
                                                                                                         }
              
																
																
																

                                                                var imageUrl= $(this).find("imageUrl").text();
                                                                if(imageUrl == " ")
                                                                {
                                                                   // news_body_mid+='<li onclick = "categoryNewsDetailIn('+catId+',this,'+newsId+','+page_val+')"><img src="images/logo.png" />'+newsHeading+'</li>';
																   
																    news_body_mid+='<li onclick = "categoryNewsDetailIn('+catId+',this,'+newsId+','+page_val+')" style="background:'+color_li+';" index ="'+newsId+'"><img src="./images/logo.png" />'+newsHeading+'</li>';
                                                                }
                                                                else{
                                                              //  news_body_mid+='<li onclick = "categoryNewsDetailIn('+catId+',this,'+newsId+','+page_val+')"><img src="'+imageUrl+'" />'+newsHeading+'</li>';
															  news_body_mid+='<li onclick = "categoryNewsDetailIn('+catId+',this,'+newsId+','+page_val+')" style="background:'+color_li+';" index ="'+newsId+'"><img src="'+imageUrl+'" />'+newsHeading+'</li>';
                                                                }

                                                                });
                                                               
																
                                                            news_body_mid+='</ul></div>';
                                                            });
															
															  if(alert_1 == 0 )
                                                                {
                                                                 alertPopUp(sorry_news,nonewsavailable_news);
                                                                }
                                    });
    var news_body_lower = '</div></div>';
    var count_now=0
    var header_cat='';
    var result_12=new Array();
    for(var i=0;i<homeCategoryNews_array.length;i++)
    {
        result_12= homeCategoryNews_array[i].split(",");
        if(count_now == 4)
        {
            break;
        }
        if(count == 0)
        {
             header_cat+='<li class="active"><a href="#">'+result_12[1]+'</a></li>';
            count_now++;
        }
        else{
            header_cat+='<li><a href="#">'+result_12[1]+'</a></li>';
            count_now++;
        }
        
        
    }
    
    console.log("00000000======000"+openHtml+'<div class="news-menu" data-snap-ignore="true"><ul>'+header_cat+'</ul><span class="border"></span></div>'+news_body_uper+news_body_mid+news_body_lower+'</section></div>');
//    alert(openHtml+'<div class="news-menu"><ul>'+header_cat+'</ul><span class="border"></span></div>'+news_body_uper+news_body_mid+news_body_lower+'</section></div>');
    console.log("111111111111"+news_body_uper+news_body_mid+news_body_lower);
   
    appendHtml(openHtml+'<div class="news-menu" data-snap-ignore="true"><ul>'+header_cat+'</ul><span class="border"></span></div>'+news_body_uper+news_body_mid+news_body_lower+'</section></div>','2','2','');
	 resetNewsHeader();
      getNavs();
     setTimeout(function(){
//    	 alert("");
     newsSwiper($("#news-swiper")[0]);
     }, 1000)
    
    
    
    
    
    
    $('.appypie-loader').hide();
}
//====================================================scripts============================//

function changeBorder(index)
{
    var thisA = $(".news-menu a").eq(index);
    $(".news-menu li").removeClass("active").eq(index).addClass("active");
    $(".news-menu .border").width(thisA.width()).css("left", thisA.offset().left);
}

function newsSwiper(obj) {
    var thisObj = $(obj);
    var page =1;
    $(".news-navs").hide();
    var swiper = new Swiper(thisObj);
    $(".news-detail").scrollTop=0;
    swiper.on("SlideChangeEnd", function(){
    	
              changeBorder(swiper.activeIndex);
              })
    changeBorder(0);
    $(".news-menu a").click(function(){
                            var index = $(".news-menu a").index(this);
//                            alert(index);
                            swiper.slideTo(index);
                            });

    thisObj.find(".swiper-slide").each(function(){
                                       var rowExit = true;
                                       var rowSpan;
                                       page =1;
                                       
                                       thisObj.find(".swiper-slide").on("scroll", function(){
                                                                       var x='';
                                                                        var thisObj = this;
                                                                        if(rowExit)
                                                                        {
                                                                        if(rowSpan != null)
                                                                        {
                                                                        clearTimeout(rowSpan);
                                                                        }
                                                                        rowSpan = setTimeout(function(){
                                                                                             
                                                                                             if(getNextRows(thisObj))
                                                                                             {
                                                                                            
//                                                                                             tempforsingletym=0;
                                                                                             var catId = parseInt($("#news-swiper .news-detail").eq(swiper.activeIndex).attr("index"));
                                                                                             
//                                                                                             var title = $(thisObj).attr('title');
//
//                                                                                             $(thisObj).find("swiper-slide").attr("index");
                                                                                               tempData(page,catId,thisObj);
//
                                                                                          
                                                                                             page=page++;
                                                                                             }
                                                                                            
                                                                                             }, 300)
                                                                        
                                                                        }
                                                                        })
                                       
                                       })
    
    function getNextRows(obj) {
       
        var scrollHeight = $(obj).prop('scrollHeight');
        var height = $(obj).height();
        var endPoint = scrollHeight - height;
        var scrollTop =  $(obj).scrollTop();
        if(scrollTop === endPoint)
        {
            return true;
        }
        return false;
    }
    
    
    
}

//=================pagenation of the news home page===========================//
var tempforsingletym=0;
function tempData(page, catId,obj)
{
    if(tempforsingletym==0)
    {
        tempforsingletym=1;
    }
    else
    {
       return
    }
    var page_val=page+1;
//    alert(catId);
    var thisObj= obj;
    var catid = catId;
//    alert('12345');
    var appid = localStorage.getItem("applicationID");
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/news-soap#homeCategoryNews";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><homeCategoryNews xmlns=\"http://'+localStorage.getItem("reseller")+'/services/news-soap#homeCategoryNews\"><appId>'+appid+'</appId><pageId>'+pageId+'</pageId><page>'+page_val+'</page></homeCategoryNews></soap:Body></soap:Envelope>';
    
    
    console.log('tempData-page 2--------->>>>'+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           
           console.log('tempData--page 2-->strJSON---------->>>>'+strJSON);
           ShowNews_page(strJSON,catid,thisObj,page_val);
           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           console.log("Error : "+JSON.stringify(response));
           console.log("Error : "+textStatus);
           console.log("Error : "+errorThrown.responseText);
           }
           });

}

function ShowNews_page(xml,catid,obj,page)
{
//    alert('p');
    var thisObj = obj;
//    alert(thisObj);
    var xmll = xml;
    var count=0;
    var xml =jQuery.parseXML(xmll);
    var cate_id=catid;
    var page_val= page;
       var news_body_mid = '';
    $(xml).find("homeCategoryNewsList").each(function () {
                                             
                                             
                                             var $list = $(this);
                                             $list.find("categoryList").each(function () {
                                                                             var $list = $(this);
                                                                             var catId= $(this).find("catId").text();
                                                                             var catName= $(this).find("catName").text();
                                                                             
                                                                             if(cate_id ==catId)
                                                                             {
                                                                             
                                                                                $list.find("newsList").each(function () {
                                                                                        var newsId= $(this).find("newsId").text();
                                                                                        var newsHeading= $(this).find("newsHeading").text();
                                                                                        var imageUrl= $(this).find("imageUrl").text();
                                                                                        news_body_mid+='<li onclick = "categoryNewsDetailIn('+catId+',this,'+newsId+','+page_val+')"><img src="'+imageUrl+'" />'+newsHeading+'</li>';
                                                                                                         
                                                                                                         });
                                                                             }
                                                                            
                                                                             });
                                             });
    

  
  
     $(thisObj).find("ul").append(news_body_mid);
   
    
    
}

//================navigation bar==========//


//function getNavs()
//{
//    
//    setting_catList();
//    var navs_li = localStorage.getItem("slider");
//    var aashu = '<div class="news-navs"><div class="navs"><ul>'+navs_li+'</ul></div><div class="navs-search"><a href="#"></a><input type="text" class="search_news" onblur="openSearchNews(this)"/></div></div>';
//    $("body").append(aashu);
//    $(".navs").click(function(){
//                     $(".news-navs").css("top", "-100%").show().animate({top:"0"}, "slow");
//                     });
//    $(".news-navs a").click(function(){
//                            $(".news-navs").show().animate({top:"-100%"}, "slow", function(){
//                                                           $(this).hide();
//                                                           });
//                            });
//    $(".news-navs").hide();
//    
//}

function getNavs()
{
	
	
	
	
      var htmlD = '<div class="news-navs"><div class="navs"><ul>`</ul></div><div class="navs-search"><a href="#"></a><input type="search" class="search_news" placeholder="'+searchnews_news+'" onkeypress="openSearchNews(event, this)" /></div></div>';
	  $(".news-navs").remove();
	  $("body").append(htmlD);
	  //$(htmlD).appendTo("body");
    
	  $("a.navs").click(function(){
                                          $(".news-navs").css("top", "-100%").show().animate({top:"0"}, "slow");
									//	  $(".news-navs").show();
                                          });
                         $(".news-navs a").click(function(){
                                                 $(".news-navs").show().animate({top:"-100%"}, "slow", function(){
                                                                                $(this).hide();
                                                                                });
                                                 });
                         $(".news-navs").hide();
    

/*	
	var headerHTML = ' <header class="newsHeader hyper-local header-main " style=" position:fixed; left:0; top:0">  <a class="hyper-back" href="#" onclick="news();"></a>\ <h1>'+appName+'</h1> </header>';
*/	
	
	
    var htmlSetting ='<div class=" news-setting" id="setting_page" >\
    <div class="newsHeader setting-head"><a class="back-btn"></a><h1>'+settings_news+'</h1></div>\
    <div class="setting-scroll">\
    <div class="setting-contents">\
    <p>'+choosewhichpushalertsreceive_news+'</p>\
    <h4>'+alertsneeded_news+'</h4>\
    <ul class="list radio">\
    <li><span><input name="alert_recieve" data-role="none" id="alert_recieve1" type="radio" value="none" /></span>'+none_news+'</li>\
    <li><span><input name="alert_recieve" data-role="none" id="alert_recieve1" type="radio" value="all" /></span>'+all_news+'</li>\
	<li><span><input name="alert_recieve" data-role="none"  id="alert_recieve1" type="radio" value="4-5" /></span><input name="alert_recieve" data-role="none"  id="perday" type="text" value="5" class="text_day"/>'+everydays_news+'</li>\
    </ul>\
    <h4>'+dontsendalert_news+'</h4>\
    <div class="time-mode">\
    <span><input name="dontSend" type="checkbox" value="" id="do_not_disturb" data-role="none"/></span>\
    <input data-role="none" type="time" name="startcurrenttime" id="txtTime1"/>\
    <input data-role="none" type="time" name="endcurrenttime" id="txtTime2"/>\
    </div>\
    <h4>'+morealeroptions_news+'</h4>\
    <p>'+youcategoriesyourinterests_news+'</p>\
    <p><strong>'+categories_news+'</strong></p>\
    <ul class="list checkbox">\
    <li><span><input name="alert_recieve" id="alert_recieve1" type="checkbox"></span>'+news_news+'</li>\
    <li><span><input name="alert_recieve" id="alert_recieve2" type="checkbox"></span>'+sports_news+'</li>\
    <li><span><input name="alert_recieve" id="alert_recieve1" type="checkbox"></span>'+entertainment_news+'</li>\
    <li><span><input name="alert_recieve" id="alert_recieve2" type="checkbox"></span>'+tech_news+'</li>\
    </ul>\
    <div class="savebtn" onclick="set_settings()"><span id="save-button">'+save_news+'</span></div>\
    </div>\
    </div>\
    </div>';
    if(document.getElementById('setting_page'))
    {
        var element = document.getElementById('setting_page');
        element.parentNode.removeChild(element);
    }
    $("body").append(htmlSetting);
	 resetNewsHeader();
		try{
			var fontfamily = sessionStorage.getItem('appPageFont');
			console.log("Font Style getNavs() "+fontfamily);
			if(fontfamily)
			{
				$(".news-setting").addClass(fontfamily);
				$(".navs").addClass(fontfamily);
			}
		}catch(err){
			console.log("getNavs() font set error "+err);
		}
    $(".news-setting").hide();
    $(".news-setting").hide().find("a.back-btn").click(function(){
                                                       $(".news-setting").show().animate({top:"-100%"}, "fast", function(){
                                                                                         $(this).hide();
                                                                                         });
                                                       return false;
                                                       });
    
    
    $(".news-setting ul.list").each(function(){
                                    checkboxList(this);
                                    })
    
//    $(".news-setting .time-mode input[type='time']").prop('disabled', true);
    
    $(".news-setting .time-mode span").click(function(){		
                                             if($(this).parent().is(".on"))
                                             {
                                             $(this).removeClass("on").find("input").prop("checked", false);
                                             $(".news-setting .time-mode input[type='time']").prop('disabled', true);
                                             $(".news-setting .time-mode").removeClass("on");
                                             
                                             }
                                             else
                                             {
                                             $(this).addClass("on").find("input").prop("checked", true);	
                                             $(".news-setting .time-mode").addClass("on");
                                             $(".news-setting .time-mode input[type='time']").prop('disabled', false);
                                             }		
                                             })
    
    setting_catList();
    //var navs_li = localStorage.getItem("slider");

}

function newsSettingShow()
{
	 if(!(localStorage.getItem("newsuserid")))
	    {
	        localStorage.setItem("login_redirect","setting");
	        login_firstpage();
	    }
	 else{
		 setting();
		 getNavs();
		 $(".news-setting").hide();
		 $(".news-setting").css("top", "-100%").show().animate({top:"0"}, "fast");
	 }
    return false;
}

function checkboxList(obj) {
    var thisObj = $(obj);
    thisObj.find("li").click(function(){
                             if(thisObj.is(".radio"))
                             {
                             thisObj.find("li").removeClass("on").find("input").prop("checked", false);
                             $(this).addClass("on").find("input").prop("checked", true);
                             }
                             if(thisObj.is(".checkbox"))
                             {
                             if($(this).is(".on"))
                             {
                             $(this).removeClass("on").find("input").prop("checked", false);
                             }
                             else
                             {
                             $(this).addClass("on").find("input").prop("checked", true);
                             }
                             }
                             return false;
                             });
}

//=========================== categories list====================================//

function setting_catList()
{
    
    
   var appid = localStorage.getItem("applicationID");
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/news-soap#categoryListNews";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><categoryListNews xmlns=\"http://'+localStorage.getItem("reseller")+'/services/news-soap#categoryListNews\"><appId>'+appid+'</appId><pageId>'+pageId+'</pageId></categoryListNews></soap:Body></soap:Envelope>';
    console.log('categoryListNews()**---**------->>>>'+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           
           console.log('categoryListNews response--->strJSON---------->>>>'+strJSON);
           viewCatList(strJSON);
           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    
}

function viewCatList(xmll)
{
    var i=0;
    var j=0;
    var detail_cat='';
    var setting_html='';
    var search_html='';
    var logout_html='';
    var xml =jQuery.parseXML(xmll);
    
    $(xml).find("categoryListNews").each(function () {
                                         
                                         var $list = $(this);
                                         $list.find("categoryList").each(function () {
                                                                         
                                                                         var $list = $(this);
                                                                         var catid= $(this).find("catId").text();
                                                                         checkedArray[j]=catid;
                                                                         var cat_name=$(this).find("catName").text();
                                                                         catItemsListArray[i]=cat_name;
                                                                         i++;
                                                                         var cat_icon=$(this).find("catIcon").text();
                                                                         
                                                                         var cat_status=$(this).find("status").text();
                                                                         
                                                                         console.log("catid=="+catid)
                                                                         console.log("cat_name--"+cat_name)
                                                                         console.log("imageIcon==="+cat_icon);
                                                                         
                                                                         console.log("no_of_Comments==="+cat_status);
                                                                         console.log("ashutosh kumar mishra ");
                                                                         if(cat_name =="Main Menu")
                                                                         {
																		 if(PageID.length>1)
																		 {
                                                                         detail_cat+='<li><a onclick="homeNews()">'+cat_name+'</a></li>';
																		 }
//                                                                         detail_cat+='<li data-role="none"><a onclick="home1()" class="all-dynamic"><span class="sunicon" data-role="none">'+returnImageHtml(cat_icon)+'</span> '+cat_name+'</a></li>';
                                                                         }
                                                                         else{
                                                                         if(cat_name == "Home")
                                                                         {
                                                                         detail_cat+='<li><a onclick="news()">'+cat_name+'</a></li>';
//                                                                        detail_cat+='<li data-role="none"><a onclick="news()" class="all-dynamic"><span class="sunicon" data-role="none">'+returnImageHtml(cat_icon)+'</span> '+cat_name+'</a></li>';
                                                                         }
                                                                         else{
                                                                         if(cat_name == "Settings")
                                                                         {
                                                                         
                                                                         setting_html+='<li data-role="none" class="setting-icon"><a onclick="newsSettingShow()" class="all-dynamic"><span class="sunicon" data-role="none">'+returnImageHtml(cat_icon)+'</span></a></li>';
                                                                         localStorage.setItem("setting_icon",setting_html);
                                                                         }
                                                                         else{
                                                                         if(cat_name=="top News")
                                                                         {
                                                                         
                                                                         }
                                                                         else{
                                                                         if(cat_name=="Logout")
                                                                         {
																			if(localStorage.getItem("fooduserid") && localStorage.getItem("fooduserid") != "") {
																				logout_html+='<li><a onclick="Logout()">'+cat_name+'</a></li>';
																			}
																			else if(localStorage.getItem("newsuserid") && localStorage.getItem("newsuserid") != ""){
																				logout_html+='<li><a onclick="Logout()">'+cat_name+'</a></li>';
																			}
																			else{
																				logout_html += "";
																			}
                                                                         }
                                                                         else{
                                                                         if(cat_name== "Search")
                                                                         {
                                                                         
                                                                         search_html+='<li data-role="none" class="setting-icon"><span class="sunicon" data-role="none">'+returnImageHtml(cat_icon)+'</span></li>';
                                                                         localStorage.setItem("search_icon",search_html);
                                                                         
                                                                         
                                                                         }
                                                                         else
                                                                         {
                                                                         detail_cat+='<li><a onclick="categoryNews('+catid+')">'+cat_name+'</a></li>';
//                                                                         detail_cat+='<li data-role="none"><a onclick="categoryNews('+catid+')" class="all-dynamic"><span class="sunicon" data-role="none">'+returnImageHtml(cat_icon)+'</span> '+cat_name+'</a></li>';

                                                                         }
                                                                         }
                                                                         }
                                                                         }
                                                                         }
                                                                         }
                                                                         j++;
                                                                         });
                                         detail_cat+='<li><a onclick="bookmarkList()">'+bookmark_news+'</a></li>';
                                         });
    detail_cat+=logout_html;
    localStorage.setItem("catItemsList",catItemsListArray);
    var x=localStorage.getItem("setting_icon");
    console.log("qwaswqqasswwwawwswwqwsqsq"+x);
    localStorage.setItem("slider",detail_cat);
    
    console.log("========*****"+detail_cat);
    
    $(".news-navs .navs ul").html(detail_cat);
    
}


//==============================================News Page Details=====================================//


function categoryNewsDetailIn(id,obj,newsId,page)
{
	 check_read ="true";
//    alert('qq');
  
    var thisClickIndex = $(obj).parent().find("li").index(obj);
       var page_val = page;
//    alert(newsId);
//    alert(thisClickIndex);
        $('.appypie-loader').show();
        var appid = localStorage.getItem("applicationID");
        email=localStorage.getItem("newsuserid");
        var userId =localStorage.getItem("user_id");
        wsUrl = "http://"+localStorage.getItem("reseller")+"/services/news-soap#categoryNewsDetail";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><categoryNewsDetail xmlns=\"http://'+localStorage.getItem("reseller")+'/services/news-soap#categoryNewsDetail\"><catId>'+id+'</catId><appId>'+appid+'</appId><pageId>'+pageId+'</pageId><userId>'+userId+'</userId><page>'+page_val+'</page><count>20</count></categoryNewsDetail></soap:Body></soap:Envelope>';
        
        console.log('categoryNewsDetail--756243576423-------->>>>'+soapRequest);
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               dataType: "text",
               cache: false,
               data: soapRequest,
               success: function(data, status, req)
               {
               var strJSON = $(req.responseText).find("return").text();
               
               console.log('categoryNewsDetail--->strJSON---------->>>>'+strJSON);
               
               openNewsDetailPage(id,strJSON, thisClickIndex,newsId,page_val);
               },
               error: function(response, textStatus, errorThrown)
               {
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
    
   
    
}
function openNewsDetailPage(id,xml, newsIndex,newsId,page_val)
{
//    alert(id);
//    alert(newsId);
    var contentRow = 0;
    var page = page_val;
   // var slider_home_screen='<header class="newsHeader"><ul class="news-btns"><li class="back-btn" onclick="onBackKeyDown()"></li><li class="theme-btn"></li><li class="share-btn"></li><li class="comments-btn"></li><li class="font-btn"></li><li class="bookmark-btn"></li></ul></header>';
    
	sessionStorage.setItem("newsDetailsId",id);
	var slider_home_screen='<header><ul class="news-btns"><li class="back-btn" onclick="readUnread_news('+id+')"></li><li class="theme-btn"></li><li class="share-btn"></li><li class="comments-btn"></li><li class="font-btn"></li><li class="bookmark-btn"></li></ul></header>';
	
    var zxp = '<section class="news-section"><div class="swiper-container" id="news-swiperD"><div class="swiper-wrapper">';

    localStorage.setItem("idnews",id);
    
     $('.appypie-loader').hide();

    var count_now_asw =0;
    var detail_news='';
    var result1='';
    var xmll =jQuery.parseXML(xml);
    
    $(xmll).find("categoryNewsList").each(function () {
                                   var $list = $(this);
                                         
                                         var imageUrl='';
                                         var url_type='';
                                         var videoType='';
                                         var result1='';
                                         
                                          var summary = '';
                                          var type = '';
                                         
                                     $list.find("newsDetail").each(function () {
                                                                   var $list = $(this);
                                                                   var totalComments= $(this).find("totalComments").text();
                                                                  var newsId= $(this).find("id").text();
                                                                   var newsHeading= $(this).find("newsHeading").text();
                                                                   var newsLocation=$(this).find("newsLocation").text();
                                                                   var newsFullStory=$(this).find("newsFullStory").text();
                                                                   var addedon=$(this).find("addedon").text();
                                                                   var bookmark=$(this).find("bookmark").text();
                                                                  
                                                                detail_news+='<div class="swiper-slide news-detail" bookmark="'+bookmark+'" index="'+newsId+'"><div class="news-page"><h2>'+newsHeading+'</h2><time>'+addedon+', '+newsLocation+'</time><div class="news-banner swiper-container swiper-init"><div class="swiper-wrapper">';
                                                                   $(".bookmark-btn").is(".on");
                                                                   $list.find("newsMedia").each(function () {
                                                                                                type =$(this).find("type").text();
                                                                                                if(type=="image")
                                                                                                {
                                                                                                     imageUrl =$(this).find("imageUrl").text();
                                                                                                    summary =$(this).find("summary").text();
                                                                                                detail_news+='<div class="swiper-slide"><img width="" height="" src="'+imageUrl+'" onclick="openLargeImage(\''+imageUrl+'\')" /><br>'+summary+'</div>';
                                                                                                }
                                                                                                if(type=="video")
                                                                                                {
                                                                                                videoType =$(this).find("videoType").text();
                                                                                                if(videoType=="custom")
                                                                                                {
                                                                                                videoUrl =$(this).find("videoUrl").text();
                                                                                                imageUrl =$(this).find("imageUrl").text();
                                                                                                summary =$(this).find("summary").text();
                                                                                                console.log("qwertyuqwertyuqwerty>>>>>"+videoUrl);
                                                                                                detail_news+='<div class="swiper-slide"><img width="" height="" src="'+imageUrl+'" onclick="openLargeImage(\''+imageUrl+'\')" /></div><div class="swiper-slide"><video height="100%" width="100%" controls><source src="'+videoUrl+'"></video>'+summary+'</div>';
                                                                                                }
                                                                                                else{
                                                                                                videoUrl =$(this).find("videoUrl").text();
                                                                                                 result1= videoUrl.split("=");
                                                                                                summary =$(this).find("summary").text();
                                                                                                var youtubeImgurl = "http://img.youtube.com/vi/"+result1[1]+"/hqdefault.jpg";
                                                                                              //  var youTubeUrl = "http://www.youtube.com/embed/"+result1[1];
                                                                                                console.log("imgUrl..>> "+youtubeImgurl);
                                                                                                detail_news+='<div class="swiper-slide"><button class="video-icon"><img src="images/youtubeimg.png" onclick="playYouTube(\''+result1[1]+'\')" /></button><img width="" height="" src="'+youtubeImgurl+'"  /><br>'+summary+'</div>';
                                                                                         //     detail_news+='<div class="swiper-slide"><iframe src="http://www.youtube.com/embed/'+result1[1]+'" width="100%" height="100%"></iframe><br>'+summary+'</div>';
                                                                                                }
                                                                                                }
                                                                                                });
                                                                   var comment = "comments";
                                                                   if(totalComments <= 1){
                                                                	   comment = "comment";
                                                                   }
                                                                   
                                                                  
                                                                   
                                                                   
                                                                   console.log("Abhi NewsFullStory..>>"+newsFullStory);
                                                                   detail_news+='</div><div class="swiper-pagination news-banner-'+newsId+'"></div></div><div class="news-content">'+newsFullStory+'<div class="bottom-comment">'+articlehas_news+'<span>'+totalComments+' '+comment+' </span><div class="post-comment"  data-id="'+newsId+'" onclick="getComment(this)">'+postcomment_news+'</div></div></div></div></div>';
                                                                 
                                                                   contentRow++;
                                                                   
                                                                   });
                                                                   });
    
    
//    alert(count_now_asw);
    var cx='<div id="news-home" data-snap-ignore="true">'+slider_home_screen+zxp+detail_news+'</div></div></section></div>';
    console.log("-0-0-0-0=0=0=0=0=0=0=0=0="+cx);
    
    appendHtml('<div id="news-home" data-snap-ignore="true">'+slider_home_screen+zxp+detail_news+'</div></div></section></div>','3','3','');
    //resetNewsHeader();

//    alert('s');
   
    setTimeout(function(){
              
                   var swiper = new Swiper('#news-swiperD');
                   $(".news-detail").scrollTop=0;
//               alert(newsIndex);
               swiper.slideTo(newsIndex)
                var p ='';
               swiper.on("TouchStart", function(){
               
						 if(swiper.isEnd)
                         {
							page++;
							bindData(swiper, id,page);
                         }
                         changeBorder(swiper.activeIndex);
                         })
						 
			   var newsId = parseInt($("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("index"));
			    var arr = localStorage.getItem("ashu1");
                readUnread = arr.split(",");
                readUnread.push(newsId);
			   localStorage.setItem("ashu1",readUnread);
               console.log("---------values of get ids--"+localStorage.getItem("ashu1"));
               var Bookmark_array1 = new Array();
               
               if(localStorage.getItem("bookmark_store"))
               {
                    Bookmark_array1 = localStorage.getItem("bookmark_store");
            
                    var index = Bookmark_array1.indexOf(newsId);
                    if(index > -1)
                    {
                        $(".bookmark-btn").addClass("on");
                    }
               
               }
               
               swiper.on("SlideChangeEnd", function(){
                         var bookmarkVal = parseInt($("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("bookmark"));
                         var newsId = parseInt($("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("index"));
						 //deepak
						 var arr = localStorage.getItem("ashu1");
                         readUnread = arr.split(",");
                         readUnread.push(newsId);
						 localStorage.setItem("ashu1",readUnread);
                         console.log("---------values of get ids--"+localStorage.getItem("ashu1"));
						 
                         if(localStorage.getItem("bookmark_store"))
                         {
                            Bookmark_array1 = localStorage.getItem("bookmark_store");
                        
							var index = Bookmark_array1.indexOf(newsId);
							if(index > -1)
							{
								$(".bookmark-btn").addClass("on");
							}
							else
							{
								$(".bookmark-btn").removeClass("on");
							}
                         }else{
                             $(".bookmark-btn").removeClass("on");
                         }
                         
                         })
               $(".bookmark-btn").click(function(){
                                       var newsId = parseInt($("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("index"));
                                        if($(this).is(".on"))
                                        {
                                        
                                        
                                         $(this).removeClass("on");
                                        $("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("bookmark",0);
                                        
                                        
                                        if(localStorage.getItem("bookmark_store"))
                                        {
                                            Bookmark_array = localStorage.getItem("bookmark_store").replace(newsId, "").replace(",,", ",")
                                            localStorage.setItem("bookmark_store", Bookmark_array)
                                        }
                                        
                                       
                                        }
                                        else
                                        {
                                        $(this).addClass("on");
                                        var flag = "add";
//                                        addBookmark(flag,newsId);
                                        $("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("bookmark",1);
                                        if(localStorage.getItem("bookmark_store"))
                                        {
											Bookmark_array =localStorage.getItem("bookmark_store").split(",");
										}
                                            Bookmark_array.push(newsId);
                                            localStorage.setItem("bookmark_store",Bookmark_array);
                                        
                                        }
										
                                        })
               
               $(".comments-btn").click(function (){
                                         var newsId_getComment = parseInt($("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("index"));
                                        var newsHeading =$("#news-swiperD .news-detail").eq(swiper.activeIndex).find("h2").text();
//                                        alert(newsId_getComment);
//                                        alert(newsHeading);
                                        cmnt_header(newsId_getComment,newsHeading);
                                        });
               
               $(".share-btn").click(function (){
                                     var newsId_getComment = parseInt($("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("index"));
//                                      alert(newsId_getComment);
                                    var newsHeading =$("#news-swiperD .news-detail").eq(swiper.activeIndex).find("h2").text();
                                    var NewsDetail_story =$("#news-swiperD .news-detail").eq(swiper.activeIndex).find(".news-content").text();		                                     
//                                  alert(NewsDetail_story);		//                                     alert(newsHeading);
                                 share_News(newsId_getComment,newsHeading,NewsDetail_story);
//                                     alert(newsHeading);
//                                     share_News(newsId_getComment, newsHeading);
                                     });
               
               
               
               
               $("#news-swiperD .news-detail").each(function(i){
                     newsBanner(this, $(this).attr("index"));
                })
               
              
               
               //newsSwiper($("#news-swiper")[0]);
               }, 1000)
    
    $(".news-btns .theme-btn").click(function() {
                                     if($(this).is(".on"))
                                     {
//                                     alert(2);
                                     $(".news-section").removeClass("theme-on");
                                     $(this).removeClass("on");
                                     }
                                     else
                                     {
//                                     alert(1);
                                     $(".news-section").addClass("theme-on");
                                     $(this).addClass("on");
                                     }
                                     return false;
                                     })
    
    function fontView() {
    	
    	
        $(".font-popup").remove();
        $("body").append('<div class="font-popup"></div>');
        $(".font-popup").hide().append('<div><a href="#" class="#"><span></span>'+small_news+'</a><a href="#" class="active"><span></span>'+normal_news+'</a><a href="#"><span></span>'+large_news+'</a></div>')
        $(".font-popup a").click(function(){
                                 $(".font-popup a").removeClass("active");
                                 $(this).addClass("active");
                                 $(".font-popup").fadeOut("slow")
                                 $(".news-page").removeClass("news-small").removeClass("news-large").removeClass("news-normal").addClass("news-" + $(this).text().toLowerCase());
                                 return false;
                                 })
        $(".font-btn").click(function(){
                             $(".font-popup").fadeIn("slow")
                             })
    }
    fontView();
    
  
}



//=============== pagennation of the news details======================//

               function bindData(swiper, id,page)
               {
                  
               
//               var thisClickIndex = $(obj).parent().find("li").index(obj);
               var page_val = page;
           //    alert(page_val);
//               alert(thisClickIndex);
//               $('.appypie-loader').show();
               var appid = localStorage.getItem("applicationID");
               email=localStorage.getItem("newsuserid");
               var userId =localStorage.getItem("user_id");
               wsUrl = "http://"+localStorage.getItem("reseller")+"/services/news-soap#categoryNewsDetail";
               soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><categoryNewsDetail xmlns=\"http://'+localStorage.getItem("reseller")+'/services/news-soap#categoryNewsDetail\"><catId>'+id+'</catId><appId>'+appid+'</appId><pageId>'+pageId+'</pageId><userId>'+userId+'</userId><page>'+page_val+'</page><count>20</count></categoryNewsDetail></soap:Body></soap:Envelope>';
               
               console.log('categor----------yNewsDetail--756243576423-------->>>>'+soapRequest);
               $.ajax({
                      type: "POST",
                      url: wsUrl,
                      contentType: "text/xml",
                      dataType: "text",
                      cache: false,
                      data: soapRequest,
                      success: function(data, status, req)
                      {
                      var strJSON = $(req.responseText).find("return").text();
                      
                      console.log('category------------NewsDetail--->strJSON---------->>>>'+strJSON);
					 // alert("respoce  "+strJSON);
                      if(strJSON =="news not available for this category/page")
                      {
                      return ;
                      }
                      else{
                      bindData_detail(strJSON,swiper,id);
                      }
                      
                    
//                      openNewsDetailPage(id,strJSON, thisClickIndex,newsId);
                      },
                      error: function(response, textStatus, errorThrown)
                      {
                      setTimeout(function(){$('.appypie-loader').hide();},1000);
                      }
                      });
               
               
               
               }
			   
			   
function bindData_detail(xml,swiper,id)
{
   
    
//    alert(123456);
    
    $('.appypie-loader').hide();
    
    var count_now_asw =0;
    var detail_news='';
    var result1='';
    var xmll =jQuery.parseXML(xml);
    
    $(xmll).find("categoryNewsList").each(function () {
                                          var $list = $(this);
                                          
                                          var imageUrl='';
                                          var url_type='';
                                          var videoType='';
                                          var result1='';
                                          var summary = '';
                                          var type = '';
                                          
                                          $list.find("newsDetail").each(function () {
                                                                        var $list = $(this);
                                                                        var totalComments= $(this).find("totalComments").text();
                                                                        var newsId= $(this).find("id").text();
                                                                        var newsHeading= $(this).find("newsHeading").text();
                                                                        var newsLocation=$(this).find("newsLocation").text();
                                                                        var newsFullStory=$(this).find("newsFullStory").text();
                                                                        var addedon=$(this).find("addedon").text();
                                                                        var bookmark=$(this).find("bookmark").text();
                                                                        
                                                                        detail_news+='<div class="swiper-slide news-detail" bookmark="'+bookmark+'" index="'+newsId+'"><div class="news-page"><h2>'+newsHeading+'</h2><time>'+addedon+', '+newsLocation+'</time><div class="news-banner swiper-container swiper-init"><div class="swiper-wrapper">';
                                                                        $(".bookmark-btn").is(".on");
                                                                        $list.find("newsMedia").each(function () {
                                                                                                     type =$(this).find("type").text();
                                                                                                     if(type=="image")
                                                                                                     {
                                                                                                     imageUrl =$(this).find("imageUrl").text();
                                                                                                     summary =$(this).find("summary").text();
                                                                                                     detail_news+='<div class="swiper-slide"><img width="" height="" src="'+imageUrl+'" onclick="openLargeImage(\''+imageUrl+'\')" /><br>'+summary+'</div>';
                                                                                                     }
                                                                                                     if(type=="video")
                                                                                                     {
                                                                                                     videoType =$(this).find("videoType").text();
                                                                                                     if(videoType=="custom")
                                                                                                     {
                                                                                                     videoUrl =$(this).find("videoUrl").text();
                                                                                                     imageUrl =$(this).find("imageUrl").text();
                                                                                                     summary =$(this).find("summary").text();
                                                                                                     console.log("qwertyuqwertyuqwerty>>>>>"+videoUrl);
                                                                                                     detail_news+='<div class="swiper-slide"><img width="" height="" src="'+imageUrl+'" onclick="openLargeImage(\''+imageUrl+'\')" /></div><div class="swiper-slide"><video height="100%" width="100%" controls><source src="'+videoUrl+'"></video>'+summary+'</div>';
                                                                                                     }
                                                                                                     else{
                                                                                                     videoUrl =$(this).find("videoUrl").text();
                                                                                                     result1= videoUrl.split("=");
                                                                                                     summary =$(this).find("summary").text();
                                                                                                     var youtubeImgurl = "http://img.youtube.com/vi/"+result1[1]+"/hqdefault.jpg";
//                                                                                                   var youTubeUrl = "http://www.youtube.com/embed/"+result1[1];
                                                                                                     console.log("imgUrl..>> "+youtubeImgurl);
                                                                                                     detail_news+='<div class="swiper-slide"><button class="video-icon"><img src="images/youtubeimg.png" onclick="playYouTube(\''+result1[1]+'\')"  /></button><img width="" height="" src="'+youtubeImgurl+'" /><br>'+summary+'</div>';
                                                                              //                     detail_news+='<div class="swiper-slide"><iframe src="http://www.youtube.com/embed/'+result1[1]+'" width="100%" height="100%"></iframe><br>'+summary+'</div>';
                                                                                                     }
                                                                                                     }
                                                                                                     });
                                                                        var comment = "comments";
                                                                        if(totalComments <= 1){
                                                                     	   comment = "comment";
                                                                        }
                                                                        console.log("Abhi...>> NewsFullStory "+newsFullStory);
                                                                        detail_news+='</div><div class="swiper-pagination news-banner-'+newsId+'"></div></div><div class="news-content">'+newsFullStory+'<div class="bottom-comment">'+articlehas_news+'<span>'+totalComments+' '+comment+' </span><div class="post-comment"  data-id="'+newsId+'" onclick="getComment(this)">'+postcomment_news+'</div></div></div></div></div>';
                                                                        
                                                                        count_now_asw++;
                                                                        
                                                                        });
                                          });

        
    swiper.appendSlide(detail_news);
   
}




//=================bookmark================================//

function addBookmark(flag,newsId)
{

    $('.appypie-loader').show();
    var appid = localStorage.getItem("applicationID");
    email=localStorage.getItem("newsuserid");
    var userId = localStorage.getItem("user_id");
    //alert(userId);
    //alert(newsId);
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/news-soap#addBookmark";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><addBookmark xmlns=\"http://'+localStorage.getItem("reseller")+'/services/news-soap#addBookmark\"><newsId>'+newsId+'</newsId><appId>'+appid+'</appId><pageId>'+pageId+'</pageId><userId>'+userId+'</userId><mode>'+flag+'</mode></addBookmark></soap:Body></soap:Envelope>';
    
    console.log('addBookmark--756243576423-------->>>>'+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           
           console.log('addBookmark--->strJSON---------->>>>'+strJSON);
           //alert("json book mark details----"+strJSON);
          
           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    $('.appypie-loader').hide();
}

//=====================================bookmark list===================================//

function bookmarkList()
{
    $(".hyperlocal-menu").hide();
    $('.appypie-loader').show();
    var appid = localStorage.getItem("applicationID");
    email=localStorage.getItem("newsuserid");
    Bookmark_array = localStorage.getItem("bookmark_store");
//    alert(Bookmark_array);
    var page_val = 1;
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/news-soap#getBookMarkedNews";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getBookMarkedNews xmlns=\"http://'+localStorage.getItem("reseller")+'/services/news-soap#getBookMarkedNews\"><appId>'+appid+'</appId><pageId>'+pageId+'</pageId><newsIds>'+Bookmark_array+'</newsIds></getBookMarkedNews></soap:Body></soap:Envelope>';
    
    console.log('bookmarkList--756243576423-------->>>>'+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           
           console.log('bookmarkList--->strJSON-----page 1----->>>>'+strJSON);
           
           if(strJSON =="no results found")
           {
        	 
           alertPopUp(sorry_news,nobookmarknewsavailable_news);
           return;
           }
           else{
                bookmarkList_view(strJSON);
           }
           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
}
function bookmarkList_view(xml)
{
       var appName = localStorage.getItem("AppName");
    var slider_home_screen='<div id="news-home" data-snap-ignore="true"><header class="newsHeader"><a href="#" class="navs"></a><h1>'+appName+'</h1><a onclick="newsSettingShow()" class="settings"></a></header><section><div class="swiper-container" id="news-swiper"  style="height:100%"><div class="swiper-wrapper" ><div class="swiper-slide news-detail"><ul>';
    var page_val = page;
    var xmll = xml;
    localStorage.setItem("bookmark_xml",xmll);
//    alert(xmll);
    var xml =jQuery.parseXML(xmll);
    var sub_news="<div>";
    news_id_array.length=0;
    var q=0;
    $(xml).find("bookMarkhList").each(function () {
                                         var $list = $(this);
                                         $list.find("newsList").each(function () {
                                                                     var newsId= $(this).find("newsId").text();
                                                                     var newsHeading= $(this).find("newsHeading").text();
                                                                     var imageUrl='';
                                                                     $list.find("newsMedia").each(function(){
                                                                                                  imageUrl= $(this).find("imageUrl").text();
                                                                                                  })
                                                                     
                                                                     news_id_array[q]=newsId;
                                                                     q++;
																	  var color_li = "white";
                                                                     console.log("newsId=="+newsId)
                                                                     console.log("newsHeading--"+newsHeading)
                                                                     console.log("imageUrl==="+imageUrl);
                                                                     console.log("ashutosh kumar mishra ");
                                                                     if(imageUrl == "")
                                                                     {
                                                                     
                                                                      //  sub_news+='<li onclick = "bookmarkListDetailPage(this,'+newsId+')"><img src="./images/logo.png" />'+newsHeading+'</li>';
													sub_news+='<li onclick = "bookmarkListDetailPage(this,'+newsId+')" style="background:'+color_li+';" index='+newsId+'><img src="./images/logo.png" />'+newsHeading+'</li>';				  
                                                                                                                                    }
                                                                     else{
                                                                    
                                                                      //  sub_news+='<li onclick = "bookmarkListDetailPage(this,'+newsId+')"><img src="'+imageUrl+'" />'+newsHeading+'</li>';
																	 sub_news+='<li onclick = "bookmarkListDetailPage(this,'+newsId+')" style="background:'+color_li+';" index='+newsId+'><img src="'+imageUrl+'" />'+newsHeading+'</li>'; 
                                                                     }
                                                                     
                                                                     
                                                                     });
                                       
                                         });
    console.log("html of sub news boookmark----"+sub_news);
    appendHtml(slider_home_screen+sub_news+'</ul></div></div></div></section></div>','2','2','news');
	resetNewsHeader();
    getNavs();
    $('.appypie-loader').hide();
        
    setTimeout(function(){
               var page=1;
               newsSwiperB($("#news-swiper")[0],page);
               page++;
               }, 500)
    
}
//===============================================swiper========================================//

function newsSwiperB(obj,page)
{
    var thisObj = $(obj);
    
    //alert(page);
    var page_val = page;
    $(".news-navs").hide();
    var swiper = new Swiper('.swiper-container');
    $(".news-detail").scrollTop=0;
    
    thisObj.find(".swiper-slide").each(function(){
                                       var rowExit = true;
                                       var rowSpan;
                                       
                                       thisObj.find(".swiper-slide").on("scroll", function(){
                                                                        var x='';
                                                                        var thisObj = this;
                                                                       
                                                                        if(rowExit)
                                                                        {
                                                                        if(rowSpan != null)
                                                                        {
                                                                        clearTimeout(rowSpan);
                                                                        }
                                                                        rowSpan = setTimeout(function(){
                                                                                             
                                                                                             if(getNextRows(thisObj))
                                                                                             {
                                                                                             
                                                                                             //                                                                                             tempforsingletym=0;
                                                                                            
                                                                                             page_val++;
                                                                                             //                                                                                             var title = $(thisObj).attr('title');
                                                                                             //
                                                                                             //                                                                                             $(thisObj).find("swiper-slide").attr("index");
                                                                                             bookmarkList_view_next(page_val,thisObj);
                                                                                             //
                                                                                             
                                                                                             page_val=page_val++;
                                                                                             }
                                                                                             
                                                                                             }, 300)
                                                                        
                                                                        }
                                                                        })
                                       
                                       })
    
    function getNextRows(obj) {
        
        var scrollHeight = $(obj).prop('scrollHeight');
        var height = $(obj).height();
        var endPoint = scrollHeight - height;
        var scrollTop =  $(obj).scrollTop();
        if(scrollTop === endPoint)
        {
            return true;
        }
        return false;
    }
    
}

//========================next page of the bookmark list=============//

function bookmarkList_view_next(page,obj)
{
   
    var page_val = page;
//     alert(page_val);
    var appid = localStorage.getItem("applicationID");
    email=localStorage.getItem("newsuserid");
    var userId = localStorage.getItem("user_id");
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/news-soap#bookmarkList";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><bookmarkList xmlns=\"http://'+localStorage.getItem("reseller")+'/services/news-soap#bookmarkList\"><appId>'+appid+'</appId><pageId>'+pageId+'</pageId><userId>'+userId+'</userId><page>'+page_val+'</page></bookmarkList></soap:Body></soap:Envelope>';
    
    console.log('bookmarkList--756243576423-------->>>>'+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           
           console.log('bookmarkList--->strJSON---------->>>>'+strJSON);
           if(strJSON =="no bookmark exists")
           {
          
           return;
           }
           else{
           bookmarkList_viewB_next(strJSON,page_val,obj);
           }
           
           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });

}


function bookmarkList_viewB_next(xml,page,obj)
{
    //alert(123456);
    var thisObj = obj;
    var page_val = page;
//    alert('page_val');
    var xmll = xml;
    var count=0;
    var xml =jQuery.parseXML(xmll);
   
    var page_val= page;
    var news_body_mid = '';
    $(xml).find("bookmarkDetail").each(function () {
                                       var $list = $(this);
                                       $list.find("newsList").each(function () {
                                                                   var newsId= $(this).find("newsId").text();
                                                                   var newsHeading= $(this).find("newsHeading").text();
                                                                   var imageUrl= $(this).find("imageUrl").text();
                                                                   var color_li = "white";
                                                                   console.log("newsId=="+newsId)
                                                                   console.log("newsHeading--"+newsHeading)
                                                                   console.log("imageUrl==="+imageUrl);
                                                                   console.log("ashutosh kumar mishra ");
                                                                   if(imageUrl == " ")
                                                                   {
                                                                   
                                                                  // news_body_mid+='<li onclick = "bookmarkListDetail(this,'+newsId+','+page_val+')"><img src="images/logo.png" />'+newsHeading+'</li>';
																   sub_news+='<li onclick = "bookmarkListDetailPage(this,'+newsId+')" style="background:'+color_li+';" index='+newsId+'><img src="./images/logo.png" />'+newsHeading+'</li>';
                                                                   }
                                                                   else{
                                                                  // news_body_mid+='<li onclick = "bookmarkListDetail(this,'+newsId+','+page_val+')"><img src="'+imageUrl+'" />'+newsHeading+'</li>';
																   sub_news+='<li onclick = "bookmarkListDetailPage(this,'+newsId+')" style="background:'+color_li+';" index='+newsId+'><img src="'+imageUrl+'" />'+newsHeading+'</li>';
                                                                   }
                                                                   
                                                                   });
                                       
                                       });
    
    $(thisObj).find("ul").append(news_body_mid);
}


//==========Bookmark news details=======================//

function bookmarkListDetail(obj,newsId,page)
{
	 var xml = localStorage.getItem("bookmark_xml");
     var thisClickIndex = $(obj).parent().find("li").index(obj);
	    
 /*   var thisClickIndex = $(obj).parent().find("li").index(obj);
    $('.appypie-loader').show();
    var page_val = page;
    email=localStorage.getItem("newsuserid");
    var appid = localStorage.getItem("applicationID");
  var userId = localStorage.getItem("user_id");
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/news-soap#bookmarkListDetail";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><bookmarkListDetail xmlns=\"http://'+localStorage.getItem("reseller")+'/services/news-soap#bookmarkListDetail\"><appId>'+appid+'</appId><pageId>'+pageId+'</pageId><userId>'+userId+'</userId><page>'+page_val+'</page></bookmarkListDetail></soap:Body></soap:Envelope>';
//    alert("page");
    console.log('bookmarkListDetail--756243576423-------->>>>'+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           
           console.log('bookmarkListDetail--->strJSON---------->>>>'+strJSON);
           
           bookmarkListDetailPage(strJSON, thisClickIndex,newsId,page_val);
//           alert("page_val");
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log("Error in the book mark page: "+JSON.stringify(response));
                      console.log("Error : "+textStatus);
                      console.log("Error : "+errorThrown.responseText);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });*/
$('.appypie-loader').hide();
}


function bookmarkListDetailPage(xml,obj,newsId,page)
{
 
    var page_val = page;
//    alert("pa=====");
    var xml = localStorage.getItem("bookmark_xml");	
    check_read="true";	
    var obj = $(obj).parent().find("li").index(obj);
    
    var slider_home_screen='<header class="newsHeader"><ul class="news-btns"><li class="back-btn" onclick="onBackKeyDown()"></li><li class="theme-btn"></li><li class="share-btn"></li><li class="comments-btn"></li><li class="font-btn"></li><li class="bookmark-btn"></li></ul></header>';
    
    var zxp = '<section class="news-section"><div class="swiper-container" id="news-swiperD"><div class="swiper-wrapper">';
    
//    localStorage.setItem("idnews",id);
   
    $('.appypie-loader').hide();
    //    <div style="overflow-x:scroll;">
    var count_now_asw =0;
    var detail_news='';
    var result1='';
    var xmll =jQuery.parseXML(xml);
    
    $(xmll).find("bookMarkhList").each(function () {
                                          var $list = $(this);
                                       
                                          var imageUrl='';
                                          var url_type='';
                                          var videoType='';
                                          var result1='';
                                    
                                          var summary = '';
                                          var type = '';
                                          $list.find("newsList").each(function () {
                                                                        var $list = $(this);
                                                                       var newsId= $(this).find("newsId").text();
                                                                        var newsHeading= $(this).find("newsHeading").text();
                                                                        var newsLocation=$(this).find("newsLocation").text();
                                                                        var newsFullStory=$(this).find("newsFullStory").text();
                                                                        var addedon=$(this).find("addedon").text();
                                                                        var totalComments=$(this).find("totalComments").text();
                                                                        detail_news+='<div class="swiper-slide news-detail" bookmark="1" index="'+newsId+'"><div class="news-page"><h2>'+newsHeading+'</h2><time>'+addedon+', '+newsLocation+'</time><div class="news-banner swiper-container swiper-init"><div class="swiper-wrapper">';
                                                                        
                                                                        $list.find("newsMedia").each(function () {
                                                                                                     var $list = $(this);
                                                                                                     
                                                                                                     type =$(this).find("type").text();
                                                                                                     if(type=="image")
                                                                                                     {
                                                                                                     
                                                                                                     imageUrl =$(this).find("imageUrl").text();
                                                                                                     summary =$(this).find("summary").text();
                                                                                                     detail_news+='<div class="swiper-slide"><img width="" height="" src="'+imageUrl+'" onclick="openLargeImage(\''+imageUrl+'\')" /><br>'+summary+'</div>';
                                                                                                     
                                                                                                     }
                                                                                                     if(type=="video")
                                                                                                     {
                                                                                                     
                                                                                                     videoType =$(this).find("videoType").text();
                                                                                                     if(videoType=="custom")
                                                                                                     {
                                                                                                     
                                                                                                     videoUrl =$(this).find("videoUrl").text();
                                                                                                     imageUrl =$(this).find("imageUrl").text();
                                                                                                     summary =$(this).find("summary").text();
                                                                                                     console.log("qwertyuqwertyuqwerty>>>>>"+videoUrl);
                                                                                                     console.log("qwertyuqwertyuqwerty>>>>>"+imageUrl);
                                                                                                     console.log("qwertyuqwertyuqwerty>>>>>"+summary);
                                                                                                     detail_news+='<div class="swiper-slide"><img width="" height="" src="'+imageUrl+'" onclick="openLargeImage(\''+imageUrl+'\')" /></div><div class="swiper-slide"><video height="100%" width="100%" controls><source src="'+videoUrl+'"></video>'+summary+'</div>';
                                                                                                   
                                                                                                     }
                                                                                                     else{
                                                                                                     
                                                                                                     videoUrl =$(this).find("videoUrl").text();
                                                                                                     result1= videoUrl.split("=");
                                                                                                     summary =$(this).find("summary").text();
                                                                                                     var youtubeImgurl = "http://img.youtube.com/vi/"+result1[1]+"/hqdefault.jpg";
                                                                                                   //var youTubeUrl = "http://www.youtube.com/embed/"+result1[1];
                                                                                                     console.log("Abbb imgUrl..>> "+youtubeImgurl);
                                                                                                     detail_news+='<div class="swiper-slide"><button class="video-icon"><img src="images/youtubeimg.png" onclick="playYouTube(\''+result1[1]+'\')" /></button><img width="" height="" src="'+youtubeImgurl+'"  /><br>'+summary+'</div>';
//                                                                                                   detail_news+='<div class="swiper-slide"><iframe src="http://www.youtube.com/embed/'+result1[1]+'" width="100%" height="100%"></iframe><br>'+summary+'</div>';
                                                                                                     }
                                                                                                     }
                                                                                                     });
                                                                        var comment = "comments";
                                                                        if(totalComments <= 1){
                                                                     	   comment = "comment";
                                                                        }
                                                                        console.log("Abhi NewsFullStory..>>"+newsFullStory);
                                                                        detail_news+='</div><div class="swiper-pagination news-banner-'+newsId+'"></div></div><div class="news-content">'+newsFullStory+'<div class="bottom-comment">'+articlehas_news+'<span>'+totalComments+' '+comment+' </span><div class="post-comment" data-id="'+newsId+'" onclick="getComment(this)">'+postcomment_news+'</div></div></div></div></div>';
//                                                                        //alert('pk');
                                                                        count_now_asw++;
                                                                        
                                                                        });
                                          
                                                                                  });



//    var cx='<div id="news-home">'+slider_home_screen+zxp+detail_news+detail_news+detail_news+detail_news+'</div></div></section></div>+<div class="page-text-news"><div class="detail-news" id="news_heading_text"><div class="post-comment">Post a comment</div></div></div>';
//    console.log("-0-0-0-0=0=0=0=0=0=0=0=0="+cx);
//    alert("sir");
    appendHtml('<div id="news-home" data-snap-ignore="true">'+slider_home_screen+zxp+detail_news+'</div></div></section></div>','3','3','');
	//resetNewsHeader();
    $(".bookmark-btn").addClass("on");
    setTimeout(function(){
               var swiper = new Swiper('#news-swiperD');
               $(".news-detail").scrollTop=0;
               swiper.slideTo(obj)
               swiper.on("TouchStart", function(){
                         if(swiper.isEnd)
                         {
	                         page_val++;
	                         bindData_bookmarkDetail(swiper, id,page_val);
                         }
                         changeBorder(swiper.activeIndex);
                         })
               
               var bookmarkVal = parseInt($("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("bookmark"));
               if(bookmarkVal == 1)
               {
				$(".bookmark-btn").addClass("on");
               }
               swiper.on("SlideChangeEnd", function(){
                         var bookmarkVal = parseInt($("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("bookmark"));
                         ////alert(bookmarkVal)
                         if(bookmarkVal == 1)
                         {
                         $(".bookmark-btn").addClass("on");
                         }
                         else
                         {
                         $(".bookmark-btn").removeClass("on");
                         }
                         
                         })
               
               $(".bookmark-btn").click(function(){

                   var newsId = parseInt($("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("index"));
                   if($(this).is(".on"))
                   {
                   
                   
                   $(this).removeClass("on");
                   $("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("bookmark",0);
                   
                  
                   if(localStorage.getItem("bookmark_store"))
                   {
                   Bookmark_array = localStorage.getItem("bookmark_store").replace(newsId, "").replace(",,", ",")
                   localStorage.setItem("bookmark_store", Bookmark_array)
                   }
                   
                   
                   }
                   else
                   {
                   $(this).addClass("on");
                   //
                   $("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("bookmark",1);
                   if(localStorage.getItem("bookmark_store"))
                   {
                   Bookmark_array =localStorage.getItem("bookmark_store").split(",");
                   
                   Bookmark_array.push(newsId);
                   
                   localStorage.setItem("bookmark_store",Bookmark_array);
                   }
                   else{
                   Bookmark_array.push(newsId);
                   
                   localStorage.setItem("bookmark_store",Bookmark_array);
                   }
                   }
                   })
               
               $("#news-swiperD .news-detail").each(function(i){
                                                    newsBanner(this, $(this).attr("index"));
                                                    })
               
               $(".comments-btn").click(function (){
                                        var newsId_getComment = parseInt($("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("index"));
                                        var newsHeading =$("#news-swiperD .news-detail").eq(swiper.activeIndex).find("h2").text();
//                                        //alert(newsId_getComment);
//                                        //alert(newsHeading);
                                        cmnt_header(newsId_getComment,newsHeading);
                                        });
               
               $(".share-btn").click(function (){
                                     var newsId_getComment = parseInt($("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("index"));
                                     //                                      //alert(newsId_getComment);
                                     var newsHeading =$("#news-swiperD .news-detail").eq(swiper.activeIndex).find("h2").text();
                                     
                                     //                                     //alert(newsHeading);
                                     var NewsDetail_story =$("#news-swiperD .news-detail").eq(swiper.activeIndex).find(".news-content").text();		                                     
//                                   alert(NewsDetail_story);		//                                     alert(newsHeading);
                                  share_News(newsId_getComment,newsHeading,NewsDetail_story);
//                                     share_News(newsId_getComment, newsHeading);
                                     });

               
               //newsSwiper($("#news-swiper")[0]);
               }, 500)
    
    $(".news-btns .theme-btn").click(function() {
                                     if($(this).is(".on"))
                                     {
                                    
                                     $(".news-section").removeClass("theme-on");
                                     $(this).removeClass("on");
                                     }
                                     else
                                     {
                                     $(".news-section").addClass("theme-on");
                                     $(this).addClass("on");
                                     }
                                     return false;
                                     })
    
    function fontView() {
        $(".font-popup").remove();
        $("body").append('<div class="font-popup"></div>');
        $(".font-popup").hide().append('<div><a href="#" class="active"><span></span>'+small_news+'</a><a href="#"><span></span>'+normal_news+'</a><a href="#"><span></span>'+large_news+'</a></div>')
        $(".font-popup a").click(function(){
                                 $(".font-popup a").removeClass("active");
                                 $(this).addClass("active");
                                 $(".font-popup").fadeOut("slow")
                                 $(".news-page").removeClass("news-small").removeClass("news-large").removeClass("news-normal").addClass("news-" + $(this).text().toLowerCase());
                                 return false;
                                 })
        $(".font-btn").click(function(){
                             $(".font-popup").fadeIn("slow")
                             })
    }
    fontView();
    
    
}
               
               
               //=========bookmark page detail through pagrnation===========//
               
//               function bindData_bookmarkDetail()
//               {
//               
//               }

//======swiper news details==============//\\

function headerDetail()
{
    
}
//function newsSwiper(obj) {
//    var thisObj = $(obj);
//    alert(120);
//    var swiper = new Swiper('.swiper-container');
//    swiper.on("SlideChangeEnd", function(){
//              changeBorder(swiper.activeIndex);
//    })
//}

function newsBanner(newsObj, meI)
{
    var thisD = $(newsObj).find(".news-banner");
	 if(thisD.find(".swiper-slide").size() <= 1)
 {
  return false;
 }
    var subSwiper = new Swiper(thisD, {
                               pagination: '.news-banner-' + meI,
                               paginationClickable: true
                               });
}




//========================================Share The News=================================================//

//function sharelist(id)
//{
//    
//    var appid = localStorage.getItem("applicationID");
//    // pageId="news_1443688592913_53";
//    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/news-soap#addShare";
//    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><addShare xmlns=\"http://'+localStorage.getItem("reseller")+'/services/news-soap#addShare\"><appId>'+appid+'</appId><pageId>'+pageId+'</pageId><newsId>'+id+'</newsId><noofShare>1</noofShare></addShare></soap:Body></soap:Envelope>';
//    
//    console.log('addShare--756243576423-------->>>>'+soapRequest);
//    $.ajax({
//           type: "POST",
//           url: wsUrl,
//           contentType: "text/xml",
//           dataType: "text",
//           cache: false,
//           data: soapRequest,
//           success: function(data, status, req)
//           {
//           var strJSON = $(req.responseText).find("return").text();
//           
//           console.log('addShare--->strJSON---------->>>>'+strJSON);
//           newsPage(id);
//           
//           },
//           error: function(response, textStatus, errorThrown)
//           {
//           
//           console.log("Error : "+JSON.stringify(response));
//           console.log("Error : "+textStatus);
//           console.log("Error : "+errorThrown.responseText);
//           console.log("==>"+response);
//           setTimeout(function(){$('.appypie-loader').hide();},1000);132
//           }
//           });
//    
//    
//    
//}

function share_News(newsId,heading,newsdetail)
{
    var newsHeading = heading;
	var newsDdetail = newsdetail.substring(0, newsdetail.length - 42);
    var id = newsId;
    console.log("Share Text: "+newsDdetail);
    shareRssLink("Heading: "+newsHeading+" \n "+"Description: "+"\n"+newsDdetail,id);
}

//
//function reopen_News_Details()
//{
//    
//    var x=localStorage.getItem("idofNews");
//    sharelist(x);
//}

//===============================================Comment part===========================================//

function done_Post_Comment(id)
{
//    alert("done_Post_Comment");
//    alert(id);
    var xcomment=' ';
    xcomment=document.getElementById('text').value;
//    alert(xcomment);
    email=localStorage.getItem("newsuserid");
    console.log("=====================>"+email);
    var appid = localStorage.getItem("applicationID");
   
    console.log("aapid=====================>"+appid);
    console.log("pageid=====================>"+pageId);
    console.log("email======================>"+email);
    console.log("id=====================>"+id);
    console.log("xcomment=====================>"+xcomment);
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/news-soap#addComment";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><addComment xmlns=\"http://'+localStorage.getItem("reseller")+'/services/news-soap#addComment\"><appId>'+appid+'</appId><pageId>'+pageId+'</pageId><newsId>'+id+'</newsId><email>'+email+'</email><comment>'+' <![CDATA['+xcomment+']]>'+'</comment></addComment></soap:Body></soap:Envelope>';
    
    console.log('Addcomment**---------->>>>'+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml;charset=utf-8",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           
           console.log('comment**Response--->strJSON------**********************---->>>>'+strJSON);
           if(strJSON=="newsId or appId or pageId or email or content not available")
           {
        	   
           alertPopUp(sorry_news,pleaseentercomment_news);
           }
           else
           {
           show_getComment(id);
           }
           },
           error: function(response, textStatus, errorThrown)
           {
        	   
        	   console.log("Response : "+response+" Error : "+errorThrown);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    
    
    
}
function getComment(obj)
{
    //alert(obj);
    var thisBtn = $(obj);
    var newsId =thisBtn.attr("data-id");
    var newsHeading =thisBtn.parents(".news-detail").find("h2").text();
    localStorage.setItem("news_heading",newsHeading);
    //alert(newsId);
    //alert(newsHeading);
    if(!(localStorage.getItem("newsuserid")))
    {
        localStorage.setItem("login_redirect","comment");
        
        login_firstpage();
    }
    else{
    var appid = localStorage.getItem("applicationID");
    var heading= newsHeading;
//    alert("ashu-----"+newsId);
//    alert("ashu-----"+newsHeading);
    console.log("aapid=====================>"+appid);
    console.log("pageid=====================>"+pageId);
    console.log("id=====================>"+id);
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/news-soap#commentList";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><commentList xmlns=\"http://'+localStorage.getItem("reseller")+'/services/news-soap#commentList\"><newsId>'+newsId+'</newsId><appId>'+appid+'</appId><pageId>'+pageId+'</pageId></commentList></soap:Body></soap:Envelope>';
    console.log('Addcomment**---**------->>>>'+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           
           console.log('comment response--->strJSON---------->>>>'+strJSON);
           getCommentDetails(newsId,strJSON,heading);
           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    
    }
    
}

function getCommentDetails(id,xmlaa,heading)
{
	
//    alert("getCommentDetails");
//    alert(id);
    var appName = localStorage.getItem("AppName");
    console.log("ashutoshKumar Mishra"+xmlaa);
    var newsHeading= heading;
    var slider_home_screen='<header class="newsHeader"><a class="back-btn" onclick="onBackKeyDown()"></a><h1>'+appName+'</h1></header>';
    
 // var post_comment="<div class='page-text-news'>";
	var post_comment="<div>"
    var sub_comment="";
    
    post_comment+='<div class="page-text-news"><div class="detail-news" id="news_heading_text"><h4>'+newsHeading+'</h4><div class="news-text"><textarea id="text" name="" cols="" rows="5" placeholder="'+postcommenthere_new+'"></textarea></div><div class="post-comment" onclick="done_Post_Comment('+id+')">'+postcomment_news+'</div>';
    
    if(xmlaa!="no comments avalaible")
    {
        var xmlnm =jQuery.parseXML(xmlaa);
        $(xmlnm).find("commentList").each(function () {
                                          $(xmlnm).find("commentDetail").each(function () {
                                                                              var $list = $(this);
                                                                              var name= $(this).find("userName").text();
                                                                              var comment= $(this).find("comment").text();
                                                                              var time= $(this).find("commentDate").text();
                                                                              
                                                                              console.log("name=="+name)
                                                                              console.log("comment--"+comment)
                                                                              console.log("time==="+time);
                                                                              console.log("ashutosh kumar mishra ")
//                                                                              sub_comment+=' <div class="user-comments"><ul><li><div class="comment-header">'+name+',&nbsp;&nbsp;&nbsp;'+time+'<span style="color:#000;"></span></div><hr><p>'+comment+'</p></li>';
                                                                              sub_comment+='<li><p>'+comment+'</p><div class="comment-header">'+name+' <span style="color:#000;">'+time+'</span><div class="daydate"></div></div></li>';
                                                                              
                                                                              });
                                          });
    }
    appendHtml('<div id="news-home" data-snap-ignore="true">'+slider_home_screen+post_comment+'<div class="user-comments"><ul>'+sub_comment+'</ul></div>'+'</div>','4','4','news');
	resetNewsHeader();
    
}




function show_getComment(id)
{
//    alert("show_getComment");
//    var thisBtn = $(obj);
    var newsId =id;
    var newsHeading =localStorage.getItem("news_heading");
//    alert(newsId);
    var appid = localStorage.getItem("applicationID");

    if(!(localStorage.getItem("newsuserid")))
    {
        localStorage.setItem("login_redirect","comment");
        
        login_firstpage();
    }
    else{
       
//    alert(newsHeading);
    console.log("aapid=====================>"+appid);
    console.log("pageid=====================>"+pageId);
    console.log("id=====================>"+newsId);
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/news-soap#commentList";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><commentList xmlns=\"http://'+localStorage.getItem("reseller")+'/services/news-soap#commentList\"><newsId>'+newsId+'</newsId><appId>'+appid+'</appId><pageId>'+pageId+'</pageId></commentList></soap:Body></soap:Envelope>';
    console.log('show_getComment**---**------->>>>'+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           
           console.log('comment show_getComment--->strJSON---------->>>>'+strJSON);
           getCommentDetails(newsId,strJSON,newsHeading);
           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    }
}

//============================header comment ====================//

function cmnt_header(id,heading)
{
//    alert("show_getComment");
    //    var thisBtn = $(obj);
    var newsId =id;
    var newsHeading =heading;
//    alert(newsId);
    var appid = localStorage.getItem("applicationID");
//    alert(newsHeading);
    console.log("aapid=====================>"+appid);
    console.log("pageid=====================>"+pageId);
    console.log("id=====================>"+newsId);
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/news-soap#commentList";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><commentList xmlns=\"http://'+localStorage.getItem("reseller")+'/services/news-soap#commentList\"><newsId>'+newsId+'</newsId><appId>'+appid+'</appId><pageId>'+pageId+'</pageId></commentList></soap:Body></soap:Envelope>';
    console.log('cmnt_header**---**------->>>>'+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           
           console.log('cmnt_header response--->strJSON---------->>>>'+strJSON);
           show_cmnt_list(newsId,strJSON,newsHeading);
           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });

}

function show_cmnt_list(id,xml,heading)
{
//    alert("show_cmnt_list");
//    alert(id);
//    alert(heading);
    var appName = localStorage.getItem("AppName");
    console.log("ashutoshKumar Mishra"+xml);
    var newsHeading= heading;
    var newsId = id;
    localStorage.setItem("news_heading",newsHeading);
    var slider_home_screen='<header class="newsHeader"><a class="back-btn" onclick="onBackKeyDown()"></a><h1>'+appName+'</h1></center></ul></header>';
//    <div class="setting-head"><a class="back-btn"></a>Settings</div>
//    var post_comment="<div class='page-text-news'>";
	  var post_comment="<div>";
    var sub_comment="";
    
    post_comment+='<div class="page-text-news"><div class="detail-news" id="news_heading_text"><h4>'+newsHeading+'</h4><div class="news-text"></div><div class="post-comment" onclick="show_getComment('+newsId+')">'+postcomment_news+'</div>';
    
//    alert(1);
    if(xml!="no comments avalaible")
    {
        var xmlnm =jQuery.parseXML(xml);
        $(xmlnm).find("commentList").each(function () {
                                          $(xmlnm).find("commentDetail").each(function () {
                                                                              var $list = $(this);
                                                                              var name= $(this).find("userName").text();
                                                                              var comment= $(this).find("comment").text();
                                                                              var time= $(this).find("commentDate").text();
                                                                              
                                                                              
                                                                              console.log("name=="+name)
                                                                              console.log("comment--"+comment)
                                                                              console.log("time==="+time);
                                                                              console.log("ashutosh kumar mishra ")
                                                                              //                                                                              sub_comment+=' <div class="user-comments"><ul><li><div class="comment-header">'+name+',&nbsp;&nbsp;&nbsp;'+time+'<span style="color:#000;"></span></div><hr><p>'+comment+'</p></li>';
                                                                              sub_comment+='<li><p>'+comment+'</p><div class="comment-header">'+name+' <span style="color:#000;">'+time+'</span><div class="daydate"></div></div></li>';
                                                                              
                                                                              });
                                          });
    }
    
    appendHtml('<div id="news-home" data-snap-ignore="true">'+slider_home_screen+post_comment+'<div class="user-comments"><ul>'+sub_comment+'</ul></div>'+'</div>','4','4','news');
	resetNewsHeader();
}



//=======================================setting==================================//

function setting()
{

//    if(!(localStorage.getItem("newsuserid")))
//    {
//        
//        localStorage.setItem("login_redirect","setting");
//        login_firstpage();
//    }
//    else{
    var appid = localStorage.getItem("applicationID");
    // pageId="news_1443688592913_53";
    console.log("aapid=====================>"+appid);
    console.log("pageid=====================>"+pageId);
    //  console.log("id=====================>"+id);
    var deviceType="Android";
    window.toaster.startGCM();
    var uuid = toaster.getGCMId();
	 var deviceId = toaster.getUniqueDeviceId();
    var a= uuid;
    console.log("Abhi GCM Token setting() "+a);
//    alert("====device token=="+a);
   email=localStorage.getItem("newsuserid");
    
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/news-soap#notificationStatus";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><notificationStatus xmlns=\"http://'+localStorage.getItem("reseller")+'/services/news-soap#notificationStatus\"><appId>'+appid+'</appId><pageId>'+pageId+'</pageId><deviceType>'+deviceType+'</deviceType><deviceId>'+deviceId+'</deviceId><deviceToken>'+a+'</deviceToken><userEmail>'+email+'</userEmail></notificationStatus></soap:Body></soap:Envelope>';
    console.log('WSURL Is web service======notificationStatus=======>'+soapRequest);
//    alert(soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           console.log('notificationStatus response--->strJSON---------->>>>'+strJSON);
           var myXmlData=req.responseText;
           //alert("notificationStatus response---"+strJSON);
           getSettingData(strJSON);
           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
//    }
}

function getSettingData(xml)
{
    
//     var slider_home_screen='<header><ul class="news-btns"><li class="back-btn" onclick="onBackKeyDown()"></li></ul></header>';
        var xml_data=xml;
        console.log("seting "+xml_data);
        var cat_list='';
//    alert(checkedArray);
      for (i = 0; i < catItemsListArray.length; i++) {
        console.log("ashu===="+catItemsListArray[i]);
        if(checkedArray[i]==" ")
        {
            
        }
        else{
            if(document.getElementById(checkedArray[i]))
            {
            var element = document.getElementById(checkedArray[i]);
            element.parentNode.removeChild(element);
            }
        cat_list+='<li><span><input  name="alert_recieve" id="'+checkedArray[i]+'" type="checkbox" /></span>'+catItemsListArray[i]+'  </li>';
        }
        console.log("catid======"+checkedArray[i]);
    }

    $(".news-setting ul.list.checkbox").html("");
    $(".news-setting ul.list.checkbox").html(cat_list);
    checkboxList($(".news-setting ul.list.checkbox")[0]);
   
//  var categories_list='<div class="sec1" id="show"><p>You can also choose from the below categories to get alerts based on your interest.</p><strong>Interested Categories</strong><ul class="interest-category"><li style="display:block;"><div id="news_settings_"><ul>'+p+'</ul></div></li></ul></div>';
    
//    console.log("catid==catid==catid==catid======"+categories_list);
    
    if(xml_data=="no settings found")
    {
//        alert('no setting found');
//        var sub_setting='<div style="overflow-x:scroll;"><div class="page-text-news" overflow= "scroll"><div class="manage-noti"><p>Choose which alert you would like to receive.These preferences can be changed at any time.</p><div class="sec1"><h4>ALERTS I WANT TO RECEIVE</h4><ul><li><input name="alert_recieve" data-role="none" id="alert_recieve1" type="radio" value="none" /> None  </li><li><input name="alert_recieve" data-role="none"  id="alert_recieve1" type="radio" value="4-5" /> Approx 4-5 days  </li><li><input name="alert_recieve" id="alert_recieve1"  data-role="none" type="radio" value="12-15" /> Approx 12-15 days  </li></ul></div><div class="sec1 sec2"><h4>Do not receive notification between time.</h4><div class="field"><input name="dontSend" type="checkbox" value="" id="do_not_disturb" data-role="none"/>&nbsp;&nbsp;<input data-role="none" type="time" name="startcurrenttime" id="txtTime1"/>&nbsp;&nbsp;To&nbsp;&nbsp;<input data-role="none" type="time" name="endcurrenttime" id="txtTime2"/></div></div><div class="sec1" id="more"><h4>More Options&nbsp;&nbsp;</h4></div></li></ul></div>';
//        var save_html='<div class="savebtn" onclick="set_settings()"><span id="save-button">Save</span></div></div></div></div>';
//        //appendHtml('<div id="news-home">'+sub_setting+categories_list+save_html+'</div>','5','5','news');
//        $("#show").hide();
//        $("#more").click(function(){
//                         $("#show").slideToggle("fast");
//                                                  });
//
    }
    else
    {
       
        // alert("else part of setting without data");
        var mid='';
        var categories_list='';
        var alertReceive_html='';
        var Time_alerts='';
        var p='';
        var mid_show='';
        var save_html='';
        var flag=0;
        var xml_setting =jQuery.parseXML(xml);
        console.log("==============>>>>>>>>>"+xml_setting);
        $(xml_setting).find("notificationSetting").each(function () {
                                                        
                                                        var $list = $(this);
                                                        $list.find("settingDetail").each(function () {
                                                                                         
                                                                                     var $list = $(this);
                                                                                         var appId= $(this).find("appId").text();
                                                                                         var deviceType=$(this).find("deviceType").text();
                                                                                         var alertReceive=$(this).find("alertReceive").text();
                                                                                         var donotSendStartTime=$(this).find("donotSendStartTime").text();
                                                                                         var donotSendEndTime=$(this).find("donotSendEndTime").text();
                                                                                         var newsCatId= $(this).find("newsCatId").text();
                                                                                         console.log("appId=="+appId)
                                                                                         console.log("deviceType--"+deviceType)
                                                                                         console.log("alertReceive==="+alertReceive);
                                                                                         console.log("donotSendStartTime==="+donotSendStartTime);
                                                                                         console.log("donotSendEndTime==="+donotSendEndTime);
                                                                                         console.log("ashutosh kumar mishra ");
                                                                                         console.log("newsCatId==="+newsCatId);
                                                                                         console.log(newsCatId.length);
                                                                                         var result=newsCatId.split(",");
                                                                                         console.log(result.length);
                                                                                         if(alertReceive=="none")
                                                                                         {
                                                                                         
                                                                                         $(".news-setting ul.list.radio").find("li:eq(0)").addClass("on").find("input").prop("checked", true);
                                                                                         
                                                                                        }
                                                                                         else{
//                                                                                          alertReceive = 200;
                                                                                         var value13 =alertReceive;
//                                                                                         alertReceive = 200;
//                                                                                         alert(1);
                                                                                         var re = new RegExp(/^(([1-9]*)|(([1-9]*).([0-9]*)))$/);
//                                                                                          alert(123);
//                                                                                          alert(re.test(value13));
                                                                                         if (re.test(value13)) {
                                                                                         $(".news-setting ul.list.radio").find("li:eq(2)").addClass("on").find("input").prop("checked", true);
                                                                                         $("#perday").val(alertReceive);
                                                                                         
                                                                                         }
                                                                                        else{
                                                                                            if(alertReceive=="all")
                                                                                         {
                                                                                             $(".news-setting ul.list.radio").find("li:eq(1)").addClass("on").find("input").prop("checked", true);
                                                                                         }
                                                                                        
                                                                                         
                                                                                         }
                                                                                         
                                                                                         }
                                                                                         
                                                                                         if($('#do_not_disturb').is(":checked"))
                                                                                         {
                                                                                         
                                                                                         
                                                                                         if(!(donotSendStartTime==" ")&& !(donotSendEndTime==" "))
                                                                                         {
//
                                                                                         $("#do_not_disturb").prop('checked', true).parent().addClass("on").parent().addClass("on");
                                                                                         document.getElementById('txtTime1').value=donotSendStartTime;
                                                                                         $("#txtTime1").prop('disabled', false);
                                                                                         $("#txtTime2").prop('disabled', false);
                                                                                         $("#txtTime1").val(donotSendStartTime);
                                                                                         $("#txtTime2").val(donotSendEndTime);
//
                                                                                        
                                                                                         
                                                                                         }
                                                                                         }
                                                                                         else
                                                                                         {
                                                                                         $("#txtTime1").prop('disabled', true);
                                                                                         $("#txtTime2").prop('disabled', true);
                                                                                         $("#txtTime1").val("");
                                                                                         $("#txtTime2").val("");

                                                                                        
                                                                                         }
                                                                                         
//
                                                                                         console.log("===*******==>"+xml);
                                                                                         for(var x=0;x<checkedArray.length;x++)
                                                                                         {
                                                                                         if(checkedArray[x]!==" ")
                                                                                         {
                                                                                         
                                                                                         for(var y=0;y<result.length;y++)
                                                                                         {
                                                                                         
                                                                                         if(checkedArray[x]== result[y])
                                                                                         {
                                                                                         
                                                                                         flag=1;
                                                                                    
                                                                                         $("input[name='alert_recieve'][id='"+checkedArray[x]+"']").prop("checked", true).parents("li").addClass("on")
//                                                                                         //////alert("---"+checkedArray[x]);
//                                                                                         p+='<li ><input data-role="none" checked="yes" name="alert_recieve" id="'+checkedArray[x]+'" type="checkbox" /> '+catItemsListArray[x]+'  </li>';
                                                                                         
                                                                                         }
                                                                                         
                                                                                         }
//                                                                                       
                                                                                         }
                                                                                         
                                                                                         }
                                                                                         
                                                                                         });
//                                                    
                                                        
                                                        });
        
//     
    }
}


function set_settings()
{
    
    var appid = localStorage.getItem("applicationID");
    //  pageId="news_1443688592913_53";
    console.log("aapid=====================>"+appid);
    console.log("pageid=====================>"+pageId);
    var deviceType="Android";
    window.toaster.startGCM();
    var uuid = toaster.getGCMId();
    var a= uuid;
    
    var alertReceive=$('input[name=alert_recieve]:checked').val();
	 var invalidcharacters = /[^0-9]/gi;
    if(alertReceive =="4-5")
    {
        alertReceive= $("#perday").val();
        if (invalidcharacters.test(alertReceive))
        {
            alertPopUp(Alert,Please_Enter_Digits_In_Messages_per_Day_section);
            return;
        }
        
    }
//    alert($('input[name=alert_recieve]:checked').val());
    var catIds=$('input[name=news]:checked').val();
    var x='';
    var y='';
    if($('#do_not_disturb').is(":checked"))
    {
        
        x=$("#txtTime1").val();
        // alert($("#txtTime1").val());
        if(x=='')
        {
        	
        	
            alertPopUp(error_news,pleaseenterstarttime_news);
            return;
        }
        y=$("#txtTime2").val();
        // alert($("#txtTime2").val());
        if(y=='')
        {
            alertPopUp(error_news,pleaseenterendtime_news);
            return;
        }
        if(x>y)
        {
            alertPopUp(error_news,endtimealwaysgreater_news);
            return;
        }
        if(x == y)
        {
            alertPopUp(error_news,endtimealwaysgreater_news);
            return;
        }
        
    }
    else{
         x=$("#txtTime1").val();
        y=$("#txtTime2").val();
        if(x=='' && y=='')
        {
            
        }
        else{
            alertPopUp(error_news,pleaseselectcheckbox_news);
            return;
        }
    }
    var catid_send=new Array();
    var o=0;
    $('input:checkbox[name=alert_recieve]:checked').each(function () {
                                                         
                                                         //  alert("Id: " + $(this).attr("id") + " Value: " + $(this).val());
                                                         catid_send[o]=$(this).attr("id");
                                                         o++;
                                                         });
    console.log("2132213423142332,{"+catid_send+"}");
    var date = new Date();
    var timeZone=date.toTimeString();
	var split_time = timeZone.split(" ");		
    var timeZone1 =split_time[1];
    console.log("=-=-=-=-=-=-=-="+timeZone1);
    email=localStorage.getItem("newsuserid");
	var deviceId = toaster.getUniqueDeviceId();
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/news-soap#addNotification";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><addNotification xmlns=\"http://'+localStorage.getItem("reseller")+'/services/news-soap#addNotification\"><appId>'+appid+'</appId><pageId>'+pageId+'</pageId><deviceType>'+deviceType+'</deviceType><deviceId>'+deviceId+'</deviceId><deviceToken>'+a+'</deviceToken><alertReceive>'+alertReceive+'</alertReceive><donotSendStartTime>'+x+'</donotSendStartTime><donotSendEndTime>'+y+'</donotSendEndTime><catIdsJson>'+catid_send+'</catIdsJson><userEmail>'+email+'</userEmail><timeZone>'+timeZone1+'</timeZone></addNotification></soap:Body></soap:Envelope>';
    
    
    console.log('categoryNews---------->>>>'+soapRequest);
    //alert("soapRequest---------"+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           
           var strJSON = $(req.responseText).find("return").text();
           
           console.log('setting--->strJSON---------->>>>'+strJSON);
           //alert("setting -set--"+ strJSON);
           
          
           alertPopUp(ccongratulation_news,settingsaddedsuccessfully_news);
           
           },
           error: function(response, textStatus, errorThrown)
           {
          
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    
    
}


//=============== categories news======================================//

function categoryNews(catId)
{
    page =1;
    $('.appypie-loader').show();
    var appid = localStorage.getItem("applicationID");
    
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/news-soap#categoryNews";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><categoryNews xmlns=\"http://'+localStorage.getItem("reseller")+'/services/news-soap#categoryNews\"><catId>'+catId+'</catId><appId>'+appid+'</appId><pageId>'+pageId+'</pageId><page>'+page+'</page></categoryNews></soap:Body></soap:Envelope>';
    
    
    console.log('categoryNews---------->>>>'+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
			{
			   var strJSON = $(req.responseText).find("return").text();
			   
			   console.log('categoryNews--->strJSON---------->>>>'+strJSON);
			   
			   if(strJSON =='catId('+catId+') does not exists or status not active' )
			   {
				   
			   alertPopUp(sorry_news,nonewsfoundcategory_news);
			   return;
			   }
			   if(strJSON == 'news not available for this category/page')
			   {
			   alertPopUp(sorry_news,nonewsfoundcategory_news);
			   return;
			   }
			   else
			   {
			   ShowcategoryNews(strJSON,catId,page);
			   }
           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    
}

function ShowcategoryNews(xml,catId,page)
{
     var appName = localStorage.getItem("AppName");
    var page_val = page;
    var slider_home_screen='<div id="news-home" data-snap-ignore="true"><header class="newsHeader"><a href="#" class="navs"></a><h1>'+appName+'</h1><a onclick="newsSettingShow()" class="settings"></a></header><section ><div class="swiper-container" id="news-swiper" style="height:100%"><div class="swiper-wrapper" ><div class="swiper-slide news-detail" ><ul>';
    
    var xml =jQuery.parseXML(xml);
    var sub_news="<div>";
     news_id_array.length=0;
    var q=0;
    $(xml).find("categoryNewsList").each(function () {
                                         var $list = $(this);
                                         $list.find("newsList").each(function () {
                                                                     var newsId= $(this).find("newsId").text();
                                                                     var newsHeading= $(this).find("newsHeading").text();
                                                                     var imageUrl= $(this).find("imageUrl").text();
                                                                     news_id_array[q]=newsId;
                                                                     q++;
																	 //deepak
																	 var color_li = "#D96666";
                                                                     for(var i=0;i<readUnread.length;i++)
                                                                     {
                                                                     console.log('the value of the read unread ---'+readUnread[i]);
                                                                     if(newsId == readUnread[i])
                                                                     {
                                                                     color_li ="white";
                                                                     break;
                                                                     }
                                                                     }
																	 
                                                                     console.log("newsId=="+newsId)
                                                                     console.log("newsHeading--"+newsHeading)
                                                                     console.log("imageUrl==="+imageUrl);
                                                                     console.log("ashutosh kumar mishra ");
                                                                     if(imageUrl ==" ")
                                                                     {
                                                                     // sub_news+='<li onclick = "categoryNewsDetailIn('+catId+',this,'+newsId+','+page_val+')"><img src="images/logo.png" />'+newsHeading+'</li>';
																	 sub_news+='<li onclick = "categoryNewsDetailIn('+catId+',this,'+newsId+','+page_val+')" style="background:'+color_li+';" index='+newsId+'><img src="./images/logo.png" />'+newsHeading+'</li>';
                                                                     }
                                                                     else{
                                                                     // sub_news+='<li onclick = "categoryNewsDetailIn('+catId+',this,'+newsId+','+page_val+')"><img src="'+imageUrl+'" />'+newsHeading+'</li>';
																	 sub_news+='<li onclick = "categoryNewsDetailIn('+catId+',this,'+newsId+','+page_val+')" style="background:'+color_li+';" index='+newsId+'><img src="'+imageUrl+'" />'+newsHeading+'</li>'
                                                                     }
																	 
                                                                     
                                                                     });
                                         
                                         });
    appendHtml(slider_home_screen+sub_news+'</ul></div></div></div></section></div>','2','2','news');
	resetNewsHeader();
    getNavs();
    $('.appypie-loader').hide();
    setTimeout(function(){
               var page=1;
               newsSwiper_C($("#news-swiper")[0],page,catId);
               page++;
               }, 500)
    

}
function newsSwiper_C(obj,page,catId)
{
    var thisObj = $(obj);
    
//    alert(page);
    var page_val = page;
    $(".news-navs").hide();
    var swiper = new Swiper('.swiper-container');
    $(".news-detail").scrollTop=0;
    
    thisObj.find(".swiper-slide").each(function(){
                                       var rowExit = true;
                                       var rowSpan;
                                      
                                       
                                       thisObj.find(".swiper-slide").on("scroll", function(){
                                                                        var x='';
                                                                        var thisObj = this;
                                                                        
                                                                        if(rowExit)
                                                                        {
                                                                        if(rowSpan != null)
                                                                        {
                                                                        clearTimeout(rowSpan);
                                                                        }
                                                                        rowSpan = setTimeout(function(){
                                                                                             
                                                                                             if(getNextRows(thisObj))
                                                                                             {
                                                                                             
                                                                                             //                                                                                             tempforsingletym=0;
                                                                                             
                                                                                             page_val++;
                                                                                             //                                                                                             var title = $(thisObj).attr('title');
                                                                                             //
                                                                                             //                                                                                             $(thisObj).find("swiper-slide").attr("index");
                                                                                             categoryNews_Next(page_val,thisObj,catId);
                                                                                             //
                                                                                             
                                                                                             
                                                                                             }
                                                                                             
                                                                                             }, 300)
                                                                        
                                                                        }
                                                                        })
                                       
                                       })
    
    function getNextRows(obj) {
        
        var scrollHeight = $(obj).prop('scrollHeight');
        var height = $(obj).height();
        var endPoint = scrollHeight - height;
        var scrollTop =  $(obj).scrollTop();
        if(scrollTop === endPoint)
        {
            return true;
        }
        return false;
    }
    
    
    
}


function categoryNews_Next(page,obj,catId)
{
    var page_val = page;
    
    var appid = localStorage.getItem("applicationID");
//    alert('aa');
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/news-soap#categoryNews";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><categoryNews xmlns=\"http://'+localStorage.getItem("reseller")+'/services/news-soap#categoryNews\"><catId>'+catId+'</catId><appId>'+appid+'</appId><pageId>'+pageId+'</pageId><page>'+page_val+'</page></categoryNews></soap:Body></soap:Envelope>';
    
    
    console.log('categoryNews_Next---------->>>>'+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           
           console.log('categoryNews--->strJSON--Ashutosh-------->>>>'+strJSON);
           
           if(strJSON =='catId('+catId+') does not exists or status not active' )
           {
           alertPopUp(sorry_news,nonewsfoundcategory_news);
           return;
           }
           if(strJSON == 'news not available for this category/page')
           {
         
           return;
           }
           else
           {
           ShowcategoryNews_Next(strJSON,catId,page,obj);
           }
           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });

}

function ShowcategoryNews_Next(xml,catId,page,obj)
{
   
    var thisObj = obj;
    //    alert(thisObj);
    var xmll = xml;
    var catid = catId;
    var xml =jQuery.parseXML(xmll);
    
    var page_val= page;
    var news_body_mid = '';
    var sub_news="<div>";
    news_id_array.length=0;
   
    $(xml).find("categoryNewsList").each(function () {
                                         var $list = $(this);
                                         $list.find("newsList").each(function () {
                                                                     var newsId= $(this).find("newsId").text();
                                                                     var newsHeading= $(this).find("newsHeading").text();
                                                                     var imageUrl= $(this).find("imageUrl").text();
                                        
                                                                     console.log("newsId=="+newsId)
                                                                     console.log("newsHeading--"+newsHeading)
                                                                     console.log("imageUrl==="+imageUrl);
                                                                     console.log("ashutosh kumar mishra ");
                                                                     if(imageUrl ==" ")
                                                                     {
                                                                     news_body_mid+='<li onclick = "categoryNewsDetailIn('+catId+',this,'+newsId+','+page_val+')"><img src="images/logo.png" />'+newsHeading+'</li>';
                                                                     }
                                                                     else{
                                                                     news_body_mid+='<li onclick = "categoryNewsDetailIn('+catId+',this,'+newsId+','+page_val+')"><img src="'+imageUrl+'" />'+newsHeading+'</li>';
                                                                     }
                                                                     
                                                                     
                                                                     });
                                         
                                         });
   
    $(thisObj).find("ul").append(news_body_mid);
    
}

//=========================================Search News========================================//

function openSearchNews(e, s_text)
{
	console.log("Enter key code "+e.keyCode);
	if(e.keyCode === 13){
    var news_values= $(s_text).val();
   
    $('.appypie-loader').show();
    
    var appid = localStorage.getItem("applicationID");
    // pageId="news_1443688592913_53";
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/news-soap#searchNews";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><searchNews xmlns=\"http://'+localStorage.getItem("reseller")+'/services/news-soap#searchNews\"><appId>'+appid+'</appId><pageId>'+pageId+'</pageId><keyword>'+news_values+'</keyword><page>1</page></searchNews></soap:Body></soap:Envelope>';
// news_values='';
    console.log('searchNews---------->>>>'+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           
           console.log('searchNews--->strJSON---------->>>>'+strJSON);
           if(strJSON == 'no results found')
           {
           alertPopUp(sorry_news,"No related news found.");
           }
           else{
           if(strJSON == 'appId or pageId or keyword not available')
           {
         
            alertPopUp(sorry_news,pleaseenteryourkeyword_news);
           }
           else{
           
           openSearchNewsDetails(strJSON,news_values);
           }
           }
           
           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
	}
}

function openSearchNewsDetails(xml,news_values)
{
  
    var textSearch=news_values;
    localStorage.setItem("txt_val",textSearch);
     var appName = localStorage.getItem("AppName");
     var slider_home_screen='<div id="news-home" data-snap-ignore="true"><header class="newsHeader"><a href="#" class="navs"></a><h1>'+appName+'</h1><a onclick="newsSettingShow()" class="settings"></a></header><section><div class="swiper-container" id="news-swiper"  style="height:100%"><div class="swiper-wrapper"><div class="swiper-slide news-detail"><ul>';
    var xml =jQuery.parseXML(xml);
    var sub_news="<div>";
     news_id_array.length=0;
    var q=0;
    $(xml).find("searchList").each(function () {
                                   var $list = $(this);
//                                   alert("ashu==="+news_values);
                                   $list.find("newsList").each(function () {
                                                               var newsId= $(this).find("newsId").text();
                                                               var newsHeading= $(this).find("newsHeading").text();
                                                               var imageUrl= $(this).find("imageUrl").text();
                                                               console.log("newsId=="+newsId)
                                                               news_id_array[q]=newsId;
                                                               q++;
                                                               console.log("newsHeading--"+newsHeading)
                                                               console.log("imageUrl==="+imageUrl);
                                                               console.log("ashutosh kumar mishra ===="+textSearch);
															   //deepak
															    var color_li = "#D96666";
                                                               for(var i=0;i<readUnread.length;i++)
                                                               {
                                                               console.log('the value of the read unread ---'+readUnread[i]);
                                                               if(newsId == readUnread[i])
                                                               {
                                                               color_li ="white";
                                                               break;
                                                               }
                                                               }
                                                              
                                                               if(imageUrl == " ")
                                                               {
                                                              // sub_news+='<li onclick = "search_details(this,'+newsId+')"><img src="images/logo.png" />'+newsHeading+'</li>';
															   sub_news+='<li onclick = "search_details(this,'+newsId+')" style="background:'+color_li+';" index ="'+newsId+'"><img src="./images/logo.png"/>'+newsHeading+'</li>';
                                                               }
                                                               else
                                                               {
                                                              // sub_news+='<li onclick = "search_details(this,'+newsId+')"><img src="'+imageUrl+'" />'+newsHeading+'</li>';
															  sub_news+='<li onclick = "search_details(this,'+newsId+')" style="background:'+color_li+';" index ="'+newsId+'"><img src="'+imageUrl+'" />'+newsHeading+'</li>';
                                                               }
                                                              
                                                              
                                                               });
//
                                   });
    
    appendHtml(slider_home_screen+sub_news+'</ul></div></div></div></section></div>','2','2','news');
	resetNewsHeader();
    getNavs();
    $('.appypie-loader').hide();
}


//======================================search details==============================================//

function search_details(obj,newsId)
{
    var news_values =localStorage.getItem("txt_val");
	check_read ="true";
//    alert('qwweerrtty'+news_values);
    var thisClickIndex = $(obj).parent().find("li").index(obj);
    $('.appypie-loader').show();
    email=localStorage.getItem("newsuserid");
    var appid = localStorage.getItem("applicationID");
    // pageId="news_1443688592913_53";
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/news-soap#searchNewsDetail";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><searchNewsDetail xmlns=\"http://'+localStorage.getItem("reseller")+'/services/news-soap#searchNewsDetail\"><appId>'+appid+'</appId><pageId>'+pageId+'</pageId><keyword>'+news_values+'</keyword><page>1</page></searchNewsDetail></soap:Body></soap:Envelope>';
    
    console.log('searchNewsDetail--756243576423-------->>>>'+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           
           console.log('searchNewsDetail--->strJSON---------->>>>'+strJSON);

           search_details_Page(strJSON, thisClickIndex,newsId);
           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    $('.appypie-loader').hide();
}

function search_details_Page(xml,obj,newsId)
{
	//var slider_home_screen='<header class="newsHeader"><ul class="news-btns"><li class="back-btn" onclick="onBackKeyDown()"></li><li class="theme-btn"></li><li class="share-btn"></li><li class="comments-btn"></li><li class="font-btn"></li><li class="bookmark-btn"></li></ul></header>';
	 
	 var slider_home_screen='<header><ul class="news-btns"><li class="back-btn" onclick="readUnread_news_book_search()"></li><li class="theme-btn"></li><li class="share-btn"></li><li class="comments-btn"></li><li class="font-btn"></li><li class="bookmark-btn"></li></ul></header>';
	
	
	var zxp = '<section class="news-section "><div class="swiper-container" id="news-swiperD"><div class="swiper-wrapper">';
	
	
try{

	var fontfamily = sessionStorage.getItem('appPageFont');
	console.log("Font Style "+fontfamily);
	//if(fontstyle)
		if(fontfamily)
			$(".news-section").addClass(fontfamily);
  
	$(".news-detail").scrollTop=0;
	
}
catch(err){
	console.log("Font Error: "+err);
	zxp = '<section class="news-section "><div class="swiper-container" id="news-swiperD"><div class="swiper-wrapper">';
}
    localStorage.setItem("idnews",id);
    
    $('.appypie-loader').hide();
    
    var count_now_asw =0;
    var detail_news='';
    var result1='';
    var xmll =jQuery.parseXML(xml);
    
    $(xmll).find("searchList").each(function () {
                                          var $list = $(this);
                                          
                                          var imageUrl='';
                                          var url_type='';
                                          var videoType='';
                                          var result1='';
                                          var summary = '';
                                          var type = '';
                                          
                                          $list.find("newsDetail").each(function () {
                                                                        var $list = $(this);
                                                                        var totalComments= $(this).find("totalComments").text();
                                                                        var newsId= $(this).find("id").text();
                                                                        var newsHeading= $(this).find("newsHeading").text();
                                                                        var newsLocation=$(this).find("newsLocation").text();
                                                                        var newsFullStory=$(this).find("newsFullStory").text();
                                                                        var addedon=$(this).find("addedon").text();
                                                                        var bookmark=$(this).find("bookmark").text();
                                                                        
                                                                        detail_news+='<div class="swiper-slide news-detail" bookmark="'+bookmark+'" index="'+newsId+'"><div class="news-page"><h2>'+newsHeading+'</h2><time>'+addedon+', '+newsLocation+'</time><div class="news-banner swiper-container swiper-init"><div class="swiper-wrapper">';
                                                                        $(".bookmark-btn").is(".on");
                                                                        $list.find("newsMedia").each(function () {
                                                                                                     type =$(this).find("type").text();
                                                                                                     if(type=="image")
                                                                                                     {
                                                                                                     imageUrl =$(this).find("imageUrl").text();
                                                                                                     summary =$(this).find("summary").text();
                                                                                                     detail_news+='<div class="swiper-slide"><img width="" height="" src="'+imageUrl+'" onclick="openLargeImage(\''+imageUrl+'\')" /><br>'+summary+'</div>';
                                                                                                     }
                                                                                                     if(type=="video")
                                                                                                     {
                                                                                                     videoType =$(this).find("videoType").text();
                                                                                                     if(videoType=="custom")
                                                                                                     {
                                                                                                     videoUrl =$(this).find("videoUrl").text();
                                                                                                     imageUrl =$(this).find("imageUrl").text();
                                                                                                     summary =$(this).find("summary").text();
                                                                                                     console.log("qwertyuqwertyuqwerty>>>>>"+videoUrl);
                                                                                                     detail_news+='<div class="swiper-slide"><img width="" height="" src="'+imageUrl+'" onclick="openLargeImage(\''+imageUrl+'\')" /></div><div class="swiper-slide"><video height="100%" width="100%" controls><source src="'+videoUrl+'"></video>'+summary+'</div>';
                                                                                                     }
                                                                                                     else{
                                                                                                     videoUrl =$(this).find("videoUrl").text();
                                                                                                     result1= videoUrl.split("=");
                                                                                                     summary =$(this).find("summary").text();
                                                                                                     var youtubeImgurl = "http://img.youtube.com/vi/"+result1[1]+"/hqdefault.jpg";
                                                                                                   //var youTubeUrl = "http://www.youtube.com/embed/"+result1[1];
                                                                                                     console.log("imgUrl..>> "+youtubeImgurl);
                                                                                                     detail_news+='<div class="swiper-slide"><button class="video-icon"><img src="images/youtubeimg.png" onclick="playYouTube(\''+result1[1]+'\')" /></button><img width="" height="" src="'+youtubeImgurl+'"  /><br>'+summary+'</div>';
//                                                                                                     detail_news+='<div class="swiper-slide"><button class="video-icon"><img src="images/youtubeimg.png" onclick="playYouTube(\''+result1[1]+'\')" /></button><img width="" height="" src="'+youtubeImgurl+'"  /><br>'+summary+'</div>';
//                                                                                                   detail_news+='<div class="swiper-slide"><iframe src="http://www.youtube.com/embed/'+result1[1]+'" width="100%" height="100%"></iframe><br>'+summary+'</div>';
                                                                                                     }
                                                                                                     }
                                                                                                     });
                                                                        var comment = "comments";
                                                                        if(totalComments <= 1){
                                                                     	   comment = "comment";
                                                                        }
                                                                        console.log("Abhi NewsFullStory..>>"+newsFullStory);
                                                                        detail_news+='</div><div class="swiper-pagination news-banner-'+newsId+'"></div></div><div class="news-content">'+newsFullStory+'<div class="bottom-comment">'+articlehas_news+'<span>'+totalComments+' '+comment+' </span><div class="post-comment"  data-id="'+newsId+'" onclick="getComment(this)">'+postcomment_news+'</div></div></div></div></div>';
                                                                        
                                                                        count_now_asw++;
                                                                        
                                                                        });
                                          });
    
    
    //    alert(count_now_asw);
    var cx='<div id="news-home" data-snap-ignore="true">'+slider_home_screen+zxp+detail_news+'</div></div></section></div>';
    console.log("-0-0-0-0=0=0=0=0=0=0=0=0="+cx);
    
    appendHtml('<div id="news-home" data-snap-ignore="true">'+slider_home_screen+zxp+detail_news+'</div></div></section></div>','3','3','');
   // resetNewsHeader();
    setTimeout(function(){
               var swiper = new Swiper('#news-swiperD');
               $(".news-detail").scrollTop=0;
               swiper.slideTo(obj)
			   var newsId = parseInt($("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("index"));
			   readUnread.push(newsId);
			   var Bookmark_array1 = new Array();
               if(localStorage.getItem("bookmark_store"))
               {
               Bookmark_array1 = localStorage.getItem("bookmark_store");
               
               var index = Bookmark_array1.indexOf(newsId);
               if(index > -1)
               {
				$(".bookmark-btn").addClass("on");
               }
               else{
				   $(".bookmark-btn").removeClass("on");
			   }
               }
			   
               
               swiper.on("SlideChangeEnd", function(){
						 var bookmarkVal = parseInt($("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("bookmark"));
                         var newsId = parseInt($("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("index"));
						 readUnread.push(newsId);
                         if(localStorage.getItem("bookmark_store"))
                         {
                         Bookmark_array1 = localStorage.getItem("bookmark_store");
                         
							 var index = Bookmark_array1.indexOf(newsId);
							 if(index > -1)
							 {
								$(".bookmark-btn").addClass("on");
							 }
							 else
							 {
								$(".bookmark-btn").removeClass("on");
							 }
                         }
						 else{
							 $(".bookmark-btn").removeClass("on");
						 }
                         
                         })
               $(".bookmark-btn").click(function(){
                                        var newsId = parseInt($("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("index"));
                                        if($(this).is(".on"))
                                        {
											$(this).removeClass("on");
											$("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("bookmark",0);
											if(localStorage.getItem("bookmark_store"))
											{
												Bookmark_array = localStorage.getItem("bookmark_store").replace(newsId, "").replace(",,", ",")
												localStorage.setItem("bookmark_store", Bookmark_array)
											}
                                        }
                                        else
                                        {
											$(this).addClass("on");
											var flag = "add";
											//                                        addBookmark(flag,newsId);
											$("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("bookmark",1);
											if(localStorage.getItem("bookmark_store"))
											{
												Bookmark_array =localStorage.getItem("bookmark_store").split(",");
											}
												Bookmark_array.push(newsId);
												localStorage.setItem("bookmark_store",Bookmark_array);
                                        }
                                        })
               
               
               $(".comments-btn").click(function (){
                                        var newsId_getComment = parseInt($("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("index"));
                                        var newsHeading =$("#news-swiperD .news-detail").eq(swiper.activeIndex).find("h2").text();
                                        //                                        ////alert(newsId_getComment);
                                        //                                        //alert(newsHeading);
                                        cmnt_header(newsId_getComment,newsHeading);
                                        });
                                        
                                        
               
               $(".share-btn").click(function (){
                                     var newsId_getComment = parseInt($("#news-swiperD .news-detail").eq(swiper.activeIndex).attr("index"));
                                     //                                      //alert(newsId_getComment);
                                     var newsHeading =$("#news-swiperD .news-detail").eq(swiper.activeIndex).find("h2").text();
                                     var NewsDetail_story =$("#news-swiperD .news-detail").eq(swiper.activeIndex).find(".news-content").text();
                           //          var newsSummery = $("#news-swiperD .news-detail").eq(swiper.activeIndex).find("").text();
                                     
                                     //                                     //alert(newsHeading);
//                                     share_News(newsId_getComment, newsHeading);
                                     share_News(newsId_getComment,newsHeading,NewsDetail_story);
                                     });
               
               
               
               
               $("#news-swiperD .news-detail").each(function(i){
                                                    newsBanner(this, $(this).attr("index"));
                                                    })
               
               
               
               //newsSwiper($("#news-swiper")[0]);
               }, 500)
    
    $(".news-btns .theme-btn").click(function() {
                                     if($(this).is(".on"))
                                     {
                                     //                                     //alert(2);
                                     $(".news-section").removeClass("theme-on");
                                     $(this).removeClass("on");
                                     }
                                     else
                                     {
                                     //                                     //alert(1);
                                     $(".news-section").addClass("theme-on");
                                     $(this).addClass("on");
                                     }
                                     return false;
                                     })
    
    function fontView() {
        $(".font-popup").remove();
        $("body").append('<div class="font-popup"></div>');
        $(".font-popup").hide().append('<div><a href="#" class=""><span></span>'+small_news+'</a><a href="#" class="active"><span></span>'+normal_news+'</a><a href="#"><span></span>'+large_news+'</a></div>')
        $(".font-popup a").click(function(){
                                 $(".font-popup a").removeClass("active");
                                 $(this).addClass("active");
                                 $(".font-popup").fadeOut("slow")
                                 $(".news-page").removeClass("news-small").removeClass("news-large").removeClass("news-normal").addClass("news-" + $(this).text().toLowerCase());
                                 return false;
                                 })
        $(".font-btn").click(function(){
                             $(".font-popup").fadeIn("slow")
                             })
    }
    fontView();

}

//=======================================================================carousal for news=========================//

function Carousel()
{
//    //alert("12121212211222");
    $("#slide").owlCarousel({
                            
                               navigation : true,
                               slideSpeed : 1000,
                               paginationSpeed : 400,
                               singleItem : true,
                               autoHeight : true,
                               responsiveRefreshRate : 100,
                            
                               });
//    //alert(1211);
   $("#detail_news").owlCarousel({
                                  
                                  navigation : true,
                                  slideSpeed : 1000,
                                  paginationSpeed : 400,
                                  singleItem : true,
                                  autoHeight : true,
                                 responsiveRefreshRate : 100,
                                  autoPlay : 3000,
                                  });
    

}



//=====================================================logout=================================================//

function Logout()
{
navigator.notification.confirm(alert_want_logout_services, logoutCallBack, logout_services, cancel_yes_services);
   $(".hyperlocal-menu").hide();
    var ii=localStorage.getItem("newsuserid");
    console.log("==========logout function"+ii);
    localStorage.removeItem("newsuserid");
    localStorage.setItem("fooduserid","");
    toaster.setUserStatus("");
    toaster.setLoginStatus("");
   // console.log("==========logout function last"+localStorage.getItem("newsuserid"));
    news();
    }


//======================home function============================//



function homeNews()
{
  $(".news-navs").remove();
  $(".sub-menu, .login-none").remove();
  showHideAd("show");
  sessionStorage.setItem("AppPageName","");
  if(localStorage.getItem("layout")=="bottom") {
	 // alert("hhhhh");
 // $("#pgbody").append('<div data-role="footer" data-position="fixed" //class="app_navigation_bottom" data-tap-toggle="false"></div>');
				$( "body > [data-role='footer']" ).toolbar().css("display", "table");
				
				}
     /*
    if(localStorage.getItem("layout")=="bottom") {
        $("div[class^=app_navigation_bottom]").show();
        window.localStorage.setItem("bottomHide","false");
        $(".app_navigation_bottom").show();
        $(".app_navigation_bottom_carousel").show();
        $( "[data-role='footer']" ).toolbar();
        localStorage.setItem("bottomHide","false");
        $.mobile.resetActivePageHeight();
		$( "[data-role='header']" ).toolbar();
		$.mobile.resetActivePageHeight();
    }
	*/
	 
	$("body").removeClass("news");
	window.sessionStorage.setItem("EcomSubMenu","true");
	if(sessionStorage.getItem('hideHeaderBar') && sessionStorage.getItem('hideHeaderBar') == "true")
	{
		$( "[data-role='header']" ).remove();
		$( "[data-role='header']" ).toolbar('destroy');
		$.mobile.resetActivePageHeight();
	}
			$.mobile.changePage('#page1', {
				transition: 'slidefade',
				allowSamePageTransition: false
			});
    
	dynamicCSS("");
	
	$.mobile.resetActivePageHeight();
	//$("#mainback").find("img").click();
	  $("#mainback").hide();
    $("#logo").show();
    
    
}


function playYouTube(key){
	console.log("YouTube key..>> "+key);
	playYoutubeSong(key, " ");
 // toaster.liveVideoPlayMethod(url);
}





function resetNewsHeader() 
{
	headerImage=$(masterData).find("nav_header_image_name").text();
	headerBarTextColor=$(masterData).find("headerBarTextColor").text();
	navigationBarType=$(masterData).find("navigationBarType").text();
	headerBarBackgroundColor=$(masterData).find("headerBarBackgroundColor").text();
	var headerBarTitle	=$(masterData).find("headerBarTitle").text();
	var headerBarFont	=$(masterData).find("headerBarFont").text();
	var headerBarSize	=$(masterData).find("headerBarSize").text();

			
	if(headerImage == '' || navigationBarType=='text' || navigationBarType=='none')
	{
		$(".newsHeader").css({
			"color":headerBarTextColor,
			"background-color":headerBarBackgroundColor			
		}).find("i").css("color", headerBarTextColor);
		$(".newsHeader").find("h1").css(" font-weight", "normal").css(" text-shadow", "none").text(headerBarTitle)
		.addClass(headerBarFont + " " + headerBarSize);	
		
		
		
		
//alert("headerBarFont "+headerBarFont +"  headerBarSize "+headerBarSize  +" headerBarTextColor "+headerBarTextColor +" headerBarBackgroundColor  "+headerBarBackgroundColor +" headerBarTitle "+headerBarTitle);		
	}
	else
	{
		var chkDeviceRes=toaster.Header_CheckDevice_ScreenResolution();
		headerImageNew=set_headerbackground_image(chkDeviceRes);
		if($.trim(headerBarBackgroundColor) == "none")
		{
			headerBarBackgroundColor = "transparent";
		}
		$(".newsHeader").css({
			"background-image":"url("+headerImageNew+")",
			"background-color":headerBarBackgroundColor,
			"background-size":"100% 100%",
			"background-position":"center"
		}).addClass('headerImage').find("i").css("color", headerBarTextColor);	
		$(".newsHeader").find("h1").text("");
	}		
}
//deepak

function readUnread_news(id)
{
 
    if(check_read =="true")
    {
        $("#news-home").find("div.swiper-slide.news-detail").each(function(){
                                                               
                              if($(this).attr("index")==id)
                              {
                              $(this).find("li").each(function(){
                              var attr_read = $(this).attr("index");
                              var array_storage = localStorage.getItem("ashu1");
                              console.log("array length with element -------"+array_storage);
                              var readUnread = array_storage.split(",");
                              for(var i=0;i<readUnread.length;i++)
							  {
								 if(readUnread[i]== attr_read)
								 {
								   $(this).css("background","");
								 }
							  }
})
                                                                  }
                                                                  else{
                                                                  $(this).find("li").each(function(){
                                                                                         var attr_read = $(this).attr("index");
																							  for(var i=0;i<readUnread.length;i++)
	 {
		if(readUnread[i]== attr_read)
		{
			$(this).css("background","");
		}
	 }
                                                                                          })
                                                                  }
                                                                  })
    }
  
    onBackKeyDown();
    
    check_read = "false";
}
//}
//====== read and unread bookmark==============and search=======//

function readUnread_news_book_search()
{
    
    
    if(check_read=="true")
    {
        
        $("#news-home").find("div.swiper-slide.news-detail").each(function(){
                                                                  
                                                                  $(this).find("li").each(function(){
                                                                                          //                                                                                          alert($(this).attr("index"));
                                                                                          var attr_read = $(this).attr("index");
                                                                                          for(var i=0;i<readUnread.length;i++)
                                                                                          {
                                                                                          console.log('asssssssss'+attr_read+'--'+readUnread[i]);
                                                                                          if(readUnread[i]==attr_read)
                                                                                          {
                                                                                          //                                                                                          alert('asssssssss'+attr_read+'--'+readUnread[i]);
                                                                                          $(this).css("background","");
                                                                                          //                                                                                             $(this).find("li").css("background","white");
                                                                                          }
                                                                                          
                                                                                          }
                                                                                          })
                                                                  
                                                                  
                                                                  })
    }
    //    alert(123456);
    //    alert(readUnread.length);
    onBackKeyDown();
    
    check_read = "false";
}






