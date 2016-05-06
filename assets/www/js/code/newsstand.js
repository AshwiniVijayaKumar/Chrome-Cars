/*changes 30/10/15 shivshankar*/
var paymentTypeGlobal='';
var productidinapp='';
var subsPlanMonthGlobal="";
var subsPlanYearGlobal="";
var subsPlanMonthCurrencyGlobal="";
var subsPlanYearCurrencyGlobal="";
var globalPublicKey="";
var paidPaymentTypeGlobal="";
var albumPrice="",albumPriceCurrency="";
var prevEditionMonthly="",prevEditionYearly="";
var amt="";
var subsciptionBundleId;
var subscriptionBundleIdYearly;

var restore__your_purchases_newsstand = "Restore your Purchases";
var if_u_ve_reinstalled_newsstand = "If you have reinstalled";
var on_ur_device_recover_purchase_newsstand = "on your device or restored a backup onto a new device you can recover your purchases.";
var restore_newsstand = "Restore";
var my_dashboard_newsstand = "My Dashboard";
var home_newsstand = "Home";
var login_signup_newsstand = "Log In/Sign up";
var my_account_newsstand = "My Account";
var restore_purchase_newsstand = "Restore Purchases";
var main_menu_newsstand = "Main Menu";
var logout_newsstand = "Logout";
var view_all_newsstand = "View All";
var my_collection_newsstand = "My Collection";
var preview_newsstand = "Preview";
var subscribe_now_newsstand = "Subscribe Now";
var buy_this_for_newsstand = "Buy this for";
var buy_now_newsstand = "Buy now";
var add_to_collection = "Add To Collection";
var view_newsstand = "View";
var usd_newsstand = "USD";
var slash_month_newsstand = "/Month";
var slash_year_newsstand = "/Year";
var buy_album_newsstand = "Buy Album";
var buy_edition_newsstand = "Buy Edition";
var news_newsstand = "news";
var inner_html_choose_ur_subscription_newsstand ="Choose Your Subscription Type:";

var notify_enter_validno_newsstand = "Please enter valid Number";
var alert_error_newsstand = "Error";
var alert_ok_newsstand = "OK";
var alert_enter_valid_exp_month_newsstand = "Please enter valid Expairy Month";
var alert_enter_valid_exp_year_newsstand = "Please enter valid Expairy year";
var alert_enter_valid_card_holder_name_newsstand = "Please enter valid Card Holder Name";
var alert_enter_valid_cvv_no_newsstand = "Please enter valid cvv Code";
var alert_payment_unsuccessful_newsstand = "Payment was unsuccessfull,\nPlease check your card details \n or \n try after sometime";
var alert_newsstand = "Alert";
var alert_message_newsstand ="Message";
var alert_ur_order_success_newsstand = "Your Order was successful!";
var alert_we_are_sorry_newsstand = "We are sorry!";
var alert_file_not_found_newsstand = "File not found.";
var alert_check_internet_conn_newsstand = "Please check Internet connection.";
var alert_there_no_preview_avail_newsstand = "There is no preview available.";
var alert_check_net_conn_try_again_newsstand = "Please check your Internet connection and try again.";
var alert_download_unsuccessfull = "Download unsuccessful!";
var alert_no_purchase_yet_newsstand = "There are no purchases yet";
var alert_server_error_try_some_time_newsstand = "Server Error,\nPlease try after sometime";
var alert_emailid_pass_cant_blank_newsstand = "Email id and password must not be blank.";
var alert_usrname_or_pass_invalid_newsstand = "User name or password is invalid";
var alert_emailid_cant_blank_newsstand = "Email id must not be blank.";
var alert_pass_sent_ur_emailid_newsstand = "Password has been sent on your E-mail Id.";
var alert_unable_reset_pass_newsstand = "Unable to reset your Password.";
var alert_enter_phone_no_newsstand = "Please enter phone number!";
var alert_pass_conpass_doesnt_match_newsstand = "Your password and confirmation password do not match";
var alert_pass_cant_blank_newsstand = "*Password field cannot be left blank";
var alert_enter_valid_emailid_newsstand = "*Please Enter a valid Email";
var alert_name_field_cant_blank_newsstand = "*Name field cannot be left blank";
var alert_register_success_newsstand = "Registered Successfully";
var alert_unable_register_newsstand = "Unable to Register !";
var alert_usr_already_registered_newsstand = "User Already Registered !";

var payment_type_newsstand = "type:";
var payment_number_newsstand = "number:";
var payment_expireMonth_newsstand = "expireMonth:";
var payment_expireYear_newsstand = "expireYear:";
var payment_cvv2_newsstand = "cvv2:";
var payment_firstName_newsstand = "firstName:";
var payment_lastName_newsstand = "lastName:";

var html_log_in_newsstand = "Log In";
var html_emailid_newsstand = "Email ID";
var html_password_newsstand = "Password";
var html_login_newsstand = "Login";
var html_forgot_pass_newsstand = "Forgot your password?";
var html_dont_ve_account_newsstand = "Do not have an account?";
var html_signup_now = "Sign up Now";

function resgisterPage(pageName)
{

    sessionStorage.setItem('loginNews','true');
    $('.appypie-loader').show();
    var counter=0;
    var MyaccountHtml='';
    var y='';
    var pageLevel=1;
    if(pageName)
    {
        y=2;
        pageLevel=2;
    }
    else
    {
        pageName="";
    }

    var test=sessionStorage.getItem('newsStandPageIndex');
    MyaccountHtml='<div class="page-text ><div class="page-text text-center"><div class="appypie-login">\
    <h2>You are not signed in</h2>\
    <a class="white-action-btn" '+primaryColor+' onclick="loginpageNewstand(\''+pageName+'\')">Log In</a><a class="white-action-btn" '+secondaryColor+' onclick="signup_newstand(\''+pageName+'\')">Sign Up</a><a style="text-align:center;display:block;" onclick="loginAsGuest()">Login as guest?</a>\
    </div></div></div><ul class="sub-menu" style="display:none;" >\
    <li><a onclick="newsstandPage(\''+test+'\')" class="home-nav">Home</a></li>\
    <li><a onclick="resgisterPage()" class="login-nav" id="loginNewstand">Log In / Sign Up</a></li>\
    <li><a onclick="myaccount_newstand()" class="account-nav" id="myAccNewstandrestore">My Account</a></li>\
    <li id="homeIcon" class="homeIconClass"><a onclick="mainPageNewstand()" class="menu-nav">Main Menu</a></li>\
    <li><a onclick="logoutNewstand()" class="login-nav" id="logoutBtn">Logout</a></li></ul>';


	appendHtml(MyaccountHtml,y,1,'news');
    if(localStorage.getItem('fooduserid'))
    {
        $('#logoutBtn').show();
    }
    else
    {
        $('#logoutBtn').hide();
    }
}

function loginAsGuest()
{
    $('.appypie-loader').show();
    var newdate=new Date().getTime();
    if(!localStorage.getItem("guestUserLogin"))
    {
        localStorage.setItem("guestUserLogin",newdate);
    }
    else
    {
        localStorage.setItem('fooduserid',"guestUser"+localStorage.getItem("guestUserLogin")+"@noreply.com");
        if(globalVar4Back_newstand==1)
        {
            newsstandPagePaid(globalIndex_newstand);
        }
        else if(globalVar4Back_newstand==2)
        {
            purchasePreview(global_i_newstand,global_index_newstand,global_paymentAmt_newstand,global_newsstandEditionId_newstand)
        }
        else if(globalVar4Back_newstand==3)
        {
            newsstandPage(sessionStorage.getItem('newsStandPageIndex'));
        }
        else
        {
            newsstandPage(sessionStorage.getItem('newsStandPageIndex'));
        }
        return false;
    }

    var email="guestUser"+localStorage.getItem("guestUserLogin")+"@noreply.com";


    var password='1234567';
    var contact=''+newdate;
    localStorage.setItem("profileName","Guset User");
    var wsUrl ="http://"+window.localStorage.getItem("reseller")+"/services/app-user-soap#appUserRegister/";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><appUserRegister xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/app-user-soap#appUserRegister/\"><appid>'+localStorage.getItem("applicationID")+'</appid><name>Guset User</name><email>'+email+'</email><password>'+password+'</password><phone>'+contact+'</phone></appUserRegister></soap:Body></soap:Envelope>';
    console.log("SOAPSignUp " + soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           localStorage.setItem('fooduserid',email);
           if(sessionStorage.getItem("fromSubscribeButton") == 'true')
           {
           sessionStorage.removeItem("fromSubscribeButton");
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           var funcName=$('subscribe_btn_neswtand').attr('onclick');
           newsstandPage(sessionStorage.getItem('newsStandPageIndex'));
           window.setTimeout(funcName,10);
           }
           else
           {
           if(globalVar4Back_newstand==1)
           {
           newsstandPagePaid(globalIndex_newstand);
           }
           else if(globalVar4Back_newstand==2)
           {
           purchasePreview(global_i_newstand,global_index_newstand,global_paymentAmt_newstand,global_newsstandEditionId_newstand)
           }
           else if(globalVar4Back_newstand==3)
           {
           newsstandPage(sessionStorage.getItem('newsStandPageIndex'));
           }
           else
           {
           newsstandPage(sessionStorage.getItem('newsStandPageIndex'));
           }
           }

           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });

}



function restorePage()
{
    if(checkNetworkConnection())
    {
        $('.appypie-loader').show();
        var counter=0;
        globalVar4Back_newstand==1
        var MyaccountHtml='';
        MyaccountHtml='<div class="page-text restore-box"><h2>'+restore__your_purchases_newsstand+'</h2><br><br><p>'+if_u_ve_reinstalled_newsstand+' '+localStorage.getItem("AppName")+' '+on_ur_device_recover_purchase_newsstand+'</p><br> <span onclick="newsstandPagePaid('+sessionStorage.getItem('newsStandPageIndex')+')" class="restore-btn">'+restore_newsstand+'</span></div><ul class="sub-menu" style="display:none;" >\
        <li><a onclick="newsstandPage('+sessionStorage.getItem('newsStandPageIndex')+')" class="home-nav">Home</a></li>\
        <li><a onclick="mainPageNewstand()" class="menu-nav">Main Menu</a></li>\
        <li><a onclick="myaccount_newstand()" class="account-nav" id="myAccNewstandrestore">My Account</a></li>\
        <li><a onclick="logoutNewstand()" class="login-nav" id="logoutBtn">Logout</a></li></ul>';


        if(toaster.getUserStatus().length>3)
        {
            $('#logoutBtn').show();
            $('#myAccNewstandrestore').show();
        }
        else
        {
            $('#logoutBtn').hide();
            $('#myAccNewstandrestore').hide();
        }
        appendHtml(MyaccountHtml,'',1,news_newsstand);
    }
}
function logoutNewstand()
{
	//localStorage.setItem("userLogin","false");
   // localStorage.removeItem("fooduserid");
	//localStorage.removeItem("PaidEditionData")
	toaster.setUserStatus("");
	toaster.setloyalityEmail("");
    newsstandPage(sessionStorage.getItem('newsStandPageIndex'));
	 sessionStorage.setItem('pageLevel',1);
}

function mainPageNewstand()
{

  /* $("#mainmenu").empty();
   $("#mainmenu").hide();*/

    if(localStorage.getItem("layout")=="slidemenu")
    {
        $("#mainmenu").empty();
        getIndexData();
    }
    else
    {
        sessionStorage.setItem('pageLevel',1);
		onBackKeyDown();
    }
}

function myaccount_newstand()
{
    if(checkNetworkConnection())
    {
       // if(toaster.getUserStatus().length<3)
      // {
         //   resgisterPage();
			//modified by mohsin
			//$('#login_register').show();
		//	$('#logoutBtn').hide();
			//--
       // }

	   if(!localStorage.getItem("fooduserid"))
        {
            resgisterPage();
        }

        else
        {
            $('.appypie-loader').show();
            var counter=0;
            var MyaccountHtml='';
            MyaccountHtml='<section id="container"><div class="userPage-text"><h2>'+my_dashboard_newsstand+'</h2><div class="user_tab" style="display:none">\
            <h3>Hello, Dharmenrdra Gupta</h3>\
            From your My Account Dashboard you have the ability to view a snapshot of your recent account activity and update your account information. Select a link below to view or edit information.</div>\
            <div class="user_tab" id="user_tab1">\
            <h4>Contact Information</h4>\
            </div>\
            \
            </section><ul class="sub-menu" style="display:none;" >\
            <li><a onclick="newsstandPage('+sessionStorage.getItem('newsStandPageIndex')+')" class="home-nav">Home</a></li>\
            <li><a onclick="mainPageNewstand()" class="menu-nav">Main Menu</a></li>\
            <li><a onclick="restorePage()" class="restore-nav">Restore Purchases</a></li>\
            <li><a onclick="logoutNewstand()" class="login-nav" id="logoutBtn">Logout</a></li></ul>';
            appendHtml(MyaccountHtml,'',1,news_newsstand);
            showContactInfoNewstand();
			//modified by mohsin
			$('#login_register').hide();
			$('#logoutBtn').show();
			//--
        }
    }
}

function showContactInfoNewstand()
{
    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/app-user-soap#getUserProfileDetailJson";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getUserProfileDetailJson xmlns=\"http://'+localStorage.getItem("reseller")+'/services/app-user-soap#getUserProfileDetailJson\"><appId>'+localStorage.getItem('applicationID')+'</appId><email>'+localStorage.getItem('fooduserid')+'</email></getUserProfileDetailJson></soap:Body></soap:Envelope>';
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {

           console.log("data --->"+req.responseText);
           var dataObj=$(req.responseText).find("return").text();
           var obj=jQuery.parseJSON(dataObj);
           var name=obj[0].name;
           var email=obj[1].email;
           var phnNo=obj[2].phone;
           var html=name+ "<br/>"+ email + "<br/>" +  phnNo;
           $("#user_tab1").append(html);
           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
}

function newsstandPage(index)
{
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    sessionStorage.setItem('newsStandPageIndex',index);
    getFileSystemNewsstand();

    $('.appypie-loader').show();
    setTimeout(function(){
               $("#mainmenu").empty();
				//modified by mohsin
               $("#mainmenu").append('<a onclick="newsstandPage('+sessionStorage.getItem('newsStandPageIndex')+')" class="home-nav"><img src="images/e-com/home_icon.png" border="0"/> <span>'+home_newsstand+'</span></a><a onclick="resgisterPage()" class="login-nav" id="login_register"><img src="images/e-com/login_icon.png" border="0"/> <span>'+login_signup_newsstand+'</span></a><a onclick="myaccount_newstand()" class="account-nav"><img src="images/e-com/myAccount_icon.png" border="0"/> <span>'+my_account_newsstand+'</span></a><a onclick="restorePage()" class="restore-nav" id="restorePurchases"><img src="images/e-com/trems_icon.png" border="0"/> <span>'+restore_purchase_newsstand+'</span></a><a onclick="mainPageNewstand()" class="menu-nav slideMainMenuBack"><img src="images/e-com/back-to-main.png" border="0"/> <span>'+main_menu_newsstand+'</span></a><a onclick="logoutFromApp()" class="login-nav" id="logoutBtn"><img src="images/e-com/login_icon.png" border="0"/> <span>'+logout_newsstand+'</span></a>');
               },1000);

    $('.appypie-loader').show();

    //modified by mohsin
		if(toaster.getloyalityEmail()){
			localStorage.setItem('fooduserid',toaster.getloyalityEmail());
		}else{
			toaster.setUserStatus("");
		}
		//--

	//modified by mohsin
	if(toaster.getUserStatus().length>3)
               {
					$('#login_register').hide();
               }
               else
               {
					$('#login_register').show();
               }
	//--
    $newsStandIndex = $(masterData).find("newsstand[indexval="+index+"]" );

    var checkSubscribed="";
    var magDecs=$newsStandIndex.find('newsstandMagazineDescr').text().trim();
    paymentTypeGlobal=$newsStandIndex.find('paymentType').text().trim();

	paidPaymentTypeGlobal=$newsStandIndex.find('recuringAlbumPrice').text().trim();
	$languageCheck=$newsStandIndex.find('newsstandLanguageText');


	if(paidPaymentTypeGlobal=="album")
	{
	albumPrice=$newsStandIndex.find('albumPrice').text().trim();
	albumPriceCurrency=$newsStandIndex.find('albumCurrency').text().trim();
	}
	else if(paidPaymentTypeGlobal=="recuring")
	{
	prevEditionMonthly=$newsStandIndex.find('previousEditionMonthly').text().trim();
	prevEditionYearly=$newsStandIndex.find('previousEditionYearly').text().trim();
	}

    var viewAllTxt=view_all_newsstand;
	var myCollectionTxt=my_collection_newsstand;
	var previewText=preview_newsstand;
	var subscribeNowText=subscribe_now_newsstand;
	var buyThisFor=buy_this_for_newsstand;
	var buyNowAlbum=buy_now_newsstand;
	var addToCollection=add_to_collection;
	//alert(""+languageCheck);
	if($languageCheck)
	{
	viewAllTxt=$languageCheck.find('viewAll').text().trim();
	myCollectionTxt=$languageCheck.find('myCollection').text().trim();
	previewText=$languageCheck.find('previewText').text().trim();
	subscribeNowText=$languageCheck.find('subscribeNowText').text().trim();
	buyThisFor=$languageCheck.find('buyThisFor').text().trim();
	buyNowAlbum=$languageCheck.find('buyNowAlbum').text().trim();
	addToCollection=$languageCheck.find('addToCollection').text().trim();
	}

    subsPlanMonthGlobal=$newsStandIndex.find('subscriptionPriceMonthly').text().trim();
    subsPlanYearGlobal=$newsStandIndex.find('subscriptionPriceYearly').text().trim();
	globalPublicKey=$newsStandIndex.find('publicKey').text().trim();
    subsPlanMonthCurrencyGlobal=$newsStandIndex.find('subscriptionCurrencyMonthly').text().trim();
    subsPlanYearCurrencyGlobal=$newsStandIndex.find('subscriptionCurrencyYearly').text().trim();
    if($newsStandIndex.find('editionBundleId'))
    {
        productidinapp=$newsStandIndex.find('editionBundleId').text().trim();
    }
    //var subsciptionType='monthly';

    if($newsStandIndex.find('subscriptionBundleId'))
    {
        subsciptionBundleId=$newsStandIndex.find('subscriptionBundleId').text().trim();
		sessionStorage.setItem('paypalClientId',subsciptionBundleId);
    }
    if($newsStandIndex.find('subscriptionBundleIdYearly'))
    {
       subscriptionBundleIdYearly=$newsStandIndex.find('subscriptionBundleIdYearly').text().trim();
    }
    //sessionStorage.setItem('subsciptionType',subsciptionType);
    var i=0;
    console.log("loyalty page --->"+index);
    var featuredHtml='';
    var previousIssuesHtml='';
    amt=$newsStandIndex.find('EditionPrice').text();



	var finalPageId='';
    var countPageId=0;
    var newsStandPageIndex =$(masterData).find("pageIdentifierBecon").text().split(',');
    for(var w=0;w<newsStandPageIndex.length;w++)
    {
        if(newsStandPageIndex[w].indexOf('news')!=-1)
        {
            if(countPageId == index)
            {
                finalPageId = newsStandPageIndex[w];
                console.log("finalPageId--->"+finalPageId);
            }
            countPageId++;
        }
    }


    $newsStandIndex.find('newsstandHeader').each(function()
                                                 {
                                                 amt=amt.trim();
                                                 var Heading=$(this).find('newsstandSubHeader').text();
                                                 var checkSampleFile=$(this).find('newsSampleFileType').text();
                                                 var checkFullFile=$(this).find('newsFullFileType').text();
                                                 var newsstandEditionId=$(this).find('newsstandEditionId').text();
                                                 var fileTypeFullPreview='';
                                                 var fileTypeForFullPrev='';
                                                 var fileTypeSample='';
                                                 var fileTypeForSample='';
                                                 var openPaidFunction='';
                                                 var filePreviewHtml='';
												 var FeaturedBuyHtml='';
                                                 var subsciptionType='';
                                                 if($(this).find('newsstandFeatured'))
                                                 {
                                                 var featuredItem=$(this).find('newsstandFeatured').text();
                                                 }
                                                 else if(!featuredItem)
                                                 {
                                                 var featuredItem=0;
                                                 }
                                                 console.log("1");
                                                 if(checkSampleFile.length > 2)
                                                 {
                                                 fileTypeSample=$(this).find('newsSampleFile').text().trim();
                                                 fileTypeForSample=$(this).find('newsSampleFileTypeGen').text();
                                                 filePreviewHtml='<div  class="newstand-mobile-subscribe"><a  '+primaryColor+' onclick="filePreview(\''+fileTypeSample+'\',\''+fileTypeForSample+'\')">'+previewText+'</a></div>';
                                                 }
												 console.log("2");
                                                 if(checkFullFile.length > 2)
                                                 {
                                                 fileTypeFullPreview=$(this).find('newsFullFile').text();
                                                 fileTypeForFullPrev=$(this).find('newsFullFileTypeGen').text();
                                                 }
                                                 console.log("3");
                                                 var Summary=$(this).find('newsstandSummary').text();
                                                 console.log("4");
                                                 var image=$(this).find('newsstandImage').text();
                                                 console.log("5");

                                                 console.log("6");
                                                 var currency=$newsStandIndex.find('EditionCurrency').text();

                                                 console.log("7");
                                                 sessionStorage.setItem("currency",currency);
                                                 var lang=$(this).find('newsstandLang').text();
                                                 console.log("8");
                                                 var date=$(this).find('newsstandMonthYear').text();
                                                 console.log("9");
                                                 if(date.length > 1)
                                                 {
                                                 var newDate=date.split('-');
                                                 var releaseMon=newDate[0];
                                                 var releaseYear=newDate[1];
                                                 var finalDate=releaseMon+' - '+releaseYear;
                                                 }
                                                 else
                                                 {
                                                 var newDate='';
                                                 var releaseMon='';
                                                 var releaseYear='';
                                                 var finalDate='';
                                                 }
                                                 if(fileTypeFullPreview != '')
                                                 {
                                                 if(localStorage.getItem('newsStandPaidData'+newsstandEditionId) == localStorage.getItem("fooduserid") && localStorage.getItem("fooduserid")!=null)
                                                 {
												 checkSubscribed="true";
												 console.log("1111"+fileTypeFullPreview+"<---fileTypeForFullPrev--->"+fileTypeForFullPrev);
                                                 filePreviewHtml='';
                                                 openPaidFunction='<div '+secondaryColor+' class="newstand-mobile-botPrice"> <a  onclick="filePreview(\''+fileTypeFullPreview+'\',\''+fileTypeForFullPrev+'\');">'+view_newsstand+'</a> </div>';
                                                 }
												 else if(localStorage.getItem("globalPaidCheck"))
                                                 {
                                                 checkSubscribed="true";
												 console.log("1111"+fileTypeFullPreview+"<---fileTypeForFullPrev--->"+fileTypeForFullPrev);
                                                 filePreviewHtml='';
                                                 openPaidFunction='<div  '+secondaryColor+' class="newstand-mobile-botPrice"> <a  onclick="filePreview(\''+fileTypeFullPreview+'\',\''+fileTypeForFullPrev+'\');">'+view_newsstand+'</a> </div>';
                                                 }
                                                 else if(amt != '' && productidinapp !='')
                                                 {
                                                var freeOrPaid=currency+' '+parseFloat(amt);
												 if(localStorage.getItem('fooduserid') && localStorage.getItem('subscriptionValuepageId'+finalPageId+'user'+localStorage.getItem('fooduserid')))
                                                 {
                                                var checksSubscription=localStorage.getItem('subscriptionValuepageId'+finalPageId+'user'+localStorage.getItem('fooduserid'));

                                                 console.log("checksSubscription --->"+checksSubscription);
                                                 console.log("getCurrentTimeStamp --->"+getCurrentTimeStamp());
                                                 if(parseFloat(checksSubscription) > parseFloat(getCurrentTimeStamp()))
                                                 {

                                                 openPaidFunction='<div  '+secondaryColor+' class="newstand-mobile-botPrice"> <a  onclick="addtoCollection(\''+newsstandEditionId+'\');">'+add_to_collection+'</a> </div>';

                                                 }
                                                 else
                                                 {
                                                 localStorage.removeItem('subscriptionValuepageId'+finalPageId+'user'+localStorage.getItem('fooduserid'));
                                                 openPaidFunction='<div  '+secondaryColor+' class="newstand-mobile-botPrice"> <a  onclick="purchasePreview(\''+i+'\',\''+index+'\',\''+parseFloat(amt)+'\',\''+newsstandEditionId+'\',\''+subsciptionType+'\');">'+freeOrPaid+'</a> </div>';
                                                 }
                                                 }
                                                 else
                                                 {
                                                 openPaidFunction='<div  '+secondaryColor+' class="newstand-mobile-botPrice"> <a  onclick="purchasePreview(\''+i+'\',\''+index+'\',\''+parseFloat(amt)+'\',\''+newsstandEditionId+'\',\''+subsciptionType+'\');">'+freeOrPaid+'</a> </div>';
                                                 }
                                                 }
                                                 else if(amt == '')
                                                 {
                                                 var freeOrPaid='Free';

												if((subsciptionBundleId!='' || subscriptionBundleIdYearly!='' )&& !localStorage.getItem('newsStandPaidData'+newsstandEditionId))
												{
												openPaidFunction='<div  '+secondaryColor+' class="newstand-mobile-subscribe newstand-mobile-onrangBtn">\
                                                 <a id="subscribe_btn_neswtand"  onclick="openPopUpForSubscriptionType(\''+i+'\',\''+index+'\',\''+parseFloat(amt)+'\',\''+newsstandEditionId+'\',\''+subsciptionBundleId+'\',\''+subscriptionBundleIdYearly+'\')">'+subscribeNowText+'</a>\
                                                 </div>';
												}
												else
												{
												 openPaidFunction='<div  '+secondaryColor+' class="newstand-mobile-botPrice"> <a  onclick="filePreview(\''+fileTypeFullPreview+'\',\''+fileTypeForFullPrev+'\');">'+'View'+'</a> </div>';
                                                }

                                                filePreviewHtml='<div  class="newstand-mobile-subscribe"><a '+secondaryColor+' onclick="filePreview(\''+fileTypeSample+'\',\''+fileTypeForSample+'\')">'+previewText+'</a></div>';
                                                 }

												  if(paymentTypeGlobal=="free")
                                                 {
                                                 openPaidFunction='<div  '+secondaryColor+' class="newstand-mobile-botPrice"> <a  onclick="filePreview(\''+fileTypeFullPreview+'\',\''+fileTypeForFullPrev+'\');">'+'Free'+'</a> </div>';
                                                 }

                                                 }
                                                 else
                                                 {
												 if(amt != '' && productidinapp !='')
                                                 {
												 amt='';
                                                 }
                                                 var freeOrPaid='';
                                                 openPaidFunction='';
                                                 }
                                                 console.log("featuredItem--->"+featuredItem+"<--i--->"+i);
                                                 if(parseFloat(featuredItem) > 0)
                                                 {

                                                 if(openPaidFunction.length > 2 && localStorage.getItem('newsStandPaidData'+newsstandEditionId) == localStorage.getItem("fooduserid"))

												 {

												 checkSubscribed="true";
												 console.log("checkSubscribed set");

												 //openPaidFunction='<div class="newstand-mobile-botPrice"> <a href="#" class="ui-link">Free</a> </div>';



                                                 //changes 30/10/15
                                                 if((!localStorage.getItem("fooduserid") || localStorage.getItem('newsStandPaidData'+newsstandEditionId) != localStorage.getItem("fooduserid") || localStorage.getItem("fooduserid")==null) && paymentTypeGlobal != 'free')
                                                 {


                                                openPaidFunction='<a  onclick="filePreview(\''+fileTypeSample+'\',\''+fileTypeForSample+'\');">'+previewText+'</a>';
                                                 }else{

                                                 openPaidFunction='<a onclick="filePreview(\''+fileTypeFullPreview+'\',\''+fileTypeForFullPrev+'\');">'+view_newsstand+'</a>';
                                                 }


                                                 }
                                                 else
                                                 {

												 //openPaidFunction='<div class="newstand-mobile-botPrice"> <a href="#" class="ui-link">Free</a> </div>';
                                                 //openPaidFunction='<a onclick="purchasePreview(\''+i+'\',\''+index+'\',\''+parseFloat(amt)+'\',\''+newsstandEditionId+'\');">Preview</a>';
//                                                 openPaidFunction='<a  onclick="filePreview(\''+fileTypeSample+'\',\''+fileTypeForSample+'\');">'+previewText+'</a>';



                                                 if((!localStorage.getItem("fooduserid") || localStorage.getItem('newsStandPaidData'+newsstandEditionId) != localStorage.getItem("fooduserid") || localStorage.getItem("fooduserid")==null) && paymentTypeGlobal != 'free')
                                                 {


                                                 openPaidFunction='<a  onclick="filePreview(\''+fileTypeSample+'\',\''+fileTypeForSample+'\');">'+previewText+'</a>';
                                                 }else{

                                                 openPaidFunction='<a onclick="filePreview(\''+fileTypeFullPreview+'\',\''+fileTypeForFullPrev+'\');">'+view_newsstand+'</a>';
                                                 }


												 }
												  //console.log("kkkkkkkkkkkkkkkkkkk"+amt);


												 if(amt != '')
												 {

												 if(paymentTypeGlobal=="inApp" && productidinapp !='')
												 {

												 if(paidPaymentTypeGlobal=="album")
												 {

												 console.log("albumPrice "+albumPrice.trim());
												 if(albumPrice.trim()!="")
												 {
													console.log("Abhi >> albumPrice "+albumPrice);
												 //open popup for buy now in case of one time payment for featured product
												 FeaturedBuyHtml='<a  onclick="openPopUpForAlbumType(\''+i+'\',\''+index+'\',\''+parseFloat(amt)+'\',\''+newsstandEditionId+'\',\''+'inApp'+'\');">'+buyNowAlbum+'</a>';
												 }
												 else
												 {
													console.log("Abhi else >> albumPrice "+albumPrice);
												 FeaturedBuyHtml='<a  onclick="purchasePreview(\''+i+'\',\''+index+'\',\''+parseFloat(amt)+'\',\''+newsstandEditionId+'\');">'+buyNowAlbum+'</a>';
												 }
												 }
												 else{
													console.log("Abhi >> Payment Type  "+paidPaymentTypeGlobal);
												 FeaturedBuyHtml='<a  onclick="purchasePreview(\''+i+'\',\''+index+'\',\''+parseFloat(amt)+'\',\''+newsstandEditionId+'\');">'+buyThisFor+" "+freeOrPaid+'</a>';
												 }

												 }
												 else if(paymentTypeGlobal=="inApp" && productidinapp =='')
												 {
												 //  alertPopUp("error","Please enter edition id");
												 }
												 else if(paymentTypeGlobal=="paypal_express")
												 {
												 if(paidPaymentTypeGlobal=="album")
												 {
												 if(albumPrice.trim()!="")
												 {
												 //open popup for buy now in case of one time payment for featured product
												 FeaturedBuyHtml='<a  onclick="openPopUpForAlbumType(\''+i+'\',\''+index+'\',\''+parseFloat(amt)+'\',\''+newsstandEditionId+'\',\''+'paypal_express'+'\');">'+buyNowAlbum+'</a>';
												 }
												 else
												 {
												 FeaturedBuyHtml='<a  onclick="purchasePreview(\''+i+'\',\''+index+'\',\''+parseFloat(amt)+'\',\''+newsstandEditionId+'\');">'+buyNowAlbum+'</a>';
												 }
												 }
												 else{
												 FeaturedBuyHtml='<a  onclick="purchasePreview(\''+i+'\',\''+index+'\',\''+parseFloat(amt)+'\',\''+newsstandEditionId+'\');">'+buyThisFor+" "+freeOrPaid+'</a>';
												 }
												 }
												 }


												 if((subsciptionBundleId!='' || subscriptionBundleIdYearly!='' ) && localStorage.getItem('newsStandPaidData'+newsstandEditionId) && localStorage.getItem("fooduserid"))
												 {
												 //    changes 30/10/15
//												  FeaturedBuyHtml='<a  onclick="filePreview(\''+fileTypeFullPreview+'\',\''+fileTypeForFullPrev+'\');">'+'View'+'</a>';
                                                 }


												 //filePreviewHtml for image html
												 //openPaidFunction for button below html
												 //FeaturedBuyHtml for button for featured buy
                                                 if(filePreviewHtml.length > 2)
                                                 {
//                                                  filePreviewHtml='<a onclick="filePreview(\''+fileTypeSample+'\',\''+fileTypeForSample+'\')"><img style="height: 166px; width: 130px;" src="'+image+'"></a>';
                                                 //    changes 30/10/15
                                                 if((!localStorage.getItem("fooduserid") || localStorage.getItem('newsStandPaidData'+newsstandEditionId) != localStorage.getItem("fooduserid") || localStorage.getItem("fooduserid")==null) && paymentTypeGlobal != 'free')
                                                 {
                                                 filePreviewHtml='<a onclick="filePreview(\''+fileTypeSample+'\',\''+fileTypeForSample+'\')"><img style="height: 166px; width: 130px;" src="'+image+'"></a>';
                                                 }
                                                 else
                                                 {
                                                 filePreviewHtml='<a onclick="filePreview(\''+fileTypeFullPreview+'\',\''+fileTypeForFullPrev+'\')"><img style="height: 166px; width: 130px;" src="'+image+'"></a>';

                                                 }
                                                 }
                                                 else
                                                 {
//                                                 filePreviewHtml='<img style="height: 166px; width: 130px;" src="'+image+'">';
                                                 //    changes 30/10/15
                                                 if((!localStorage.getItem("fooduserid") || localStorage.getItem('newsStandPaidData'+newsstandEditionId) != localStorage.getItem("fooduserid") || localStorage.getItem("fooduserid")==null) && paymentTypeGlobal != 'free')
                                                 {
                                                 filePreviewHtml='<a onclick="filePreview(\''+fileTypeSample+'\',\''+fileTypeForSample+'\')"><img style="height: 166px; width: 130px;" src="'+image+'"></a>';
                                                 }
                                                 else
                                                 {
                                                 filePreviewHtml='<a onclick="filePreview(\''+fileTypeFullPreview+'\',\''+fileTypeForFullPrev+'\')"><img style="height: 166px; width: 130px;" src="'+image+'"></a>';

                                                 }
                                                 }

												  if(paymentTypeGlobal=="free")
                                                 {
                                                 FeaturedBuyHtml='<a onclick="filePreview(\''+fileTypeFullPreview+'\',\''+fileTypeForFullPrev+'\');">'+'Free'+'</a>';
                                                 }

												 featuredHtml='<div class="newstand-mobileWrapper" >\
                                                 <div style="color:'+sessionStorage.getItem("pageTextColor")+';"><div class="custom-color"><div class="newstand-Mobile-date"> '+finalDate+' </div></div></div>\
                                                 <div class="newstand-mobile-topPic" > '+filePreviewHtml+'\
                                                 <div class="newstand-mobile-price" '+primaryColor+'>'+openPaidFunction+'</div></div>\
                                                 <div class="newstand-mobile-rightBox">\
                                                 <div class="newstand-mobile-title">'+Heading+'</div>\
                                                 <div style="color:'+sessionStorage.getItem("pageTextColor")+';"><div class="custom-color"><div class="newstand-description big-discp">'+Summary+'</div></div></div>\
                                                 <div class="newstand-mobile-subscribe newstand-mobile-onrangBtn" '+primaryColor+'>\
                                                 <a id="subscribe_btn_neswtand"  onclick="openPopUpForSubscriptionType(\''+i+'\',\''+index+'\',\''+parseFloat(amt)+'\',\''+newsstandEditionId+'\',\''+subsciptionBundleId+'\',\''+subscriptionBundleIdYearly+'\')">'+subscribeNowText+'</a>\
                                                 </div>\
												 <div id="featured_purchase_btn_neswtand" '+secondaryColor+' class="newstand-mobile-botPrice">'+FeaturedBuyHtml+'</div>\
                                                 <div style="color:'+sessionStorage.getItem("pageTextColor")+';"><div class="custom-color"><div class="newstnad-mobile-disc">'+magDecs+'</div></div></div></div></div>';

                                                 //add this to 2nd line  <a href="#">Subscribe Now</a>
                                                 }
                                                 else
                                                 {
												 previousIssuesHtml+='<div class="newstand-mobile-botView" >\
                                                 <div style="color:'+sessionStorage.getItem("pageTextColor")+';"><div class="custom-color"><div class="newstand-mobile-monthTtle"> '+finalDate+' </div></div></div>\
                                                 <div class="newstand-mobile-botPic"><img src="'+image+'"></div>\
                                                 <div class="newstand-mobile-botTile">'+Heading+'</div>\
                                                 <div style="color:'+sessionStorage.getItem("pageTextColor")+';"><div class="custom-color"><div class="newstand-mobile-description">'+Summary+'</div></div></div>\
                                                 '+filePreviewHtml+openPaidFunction+'</div>';
                                                 }
                                                 i++;
                                                 });
    //krishna
    var newsstandHtml;
    if(previousIssuesHtml!='')
    {

     newsstandHtml='<div class="page-text">\
    <div class="newsTand-MobileTab">\
    <div class="tabViewall" onclick="newsstandPage(\''+index+'\');"><a href="#" class="active" >'+viewAllTxt+'</a></div>\
    <div class="tabmyCollection" onclick="newsstandPagePaid(\''+index+'\');"><a href="#">'+myCollectionTxt+'</a></div></div>\
    '+featuredHtml+'\
    <div class="newstand-mobile-botTitle">\
    <b>Previous issues</b></div>\
    '+previousIssuesHtml+'</div>';
    }
    else
    {
	    newsstandHtml='<div class="page-text">\
        <div class="newsTand-MobileTab">\
        <div class="tabViewall" onclick="newsstandPage(\''+index+'\');"><a href="#" class="active" >'+viewAllTxt+'</a></div>\
        <div class="tabmyCollection" onclick="newsstandPagePaid(\''+index+'\');"><a href="#">'+myCollectionTxt+'</a></div></div>'+featuredHtml;
    }
      newsstandHtml+='<div data-role="popup" id="popup-outside-page1" class="newsstand-popup-div cart-MSG" data-theme="b"><a id="closePop" onclick="closePopup();">X</a><h2 id="subscription_title"></h3><div id="monthly" class="sub_plan">'+usd_newsstand+'<span>20</span>'+slash_month_newsstand+'</div><div id="yearly" class="sub_plan">'+usd_newsstand+'<span>20</span>'+slash_year_newsstand+'</div></div>';

	window.requestFileSystem = window.requestFileSystem || window.webkitRequestFileSystem;
    window.requestFileSystem(LocalFileSystem.PERSISTENT, 0, gotSuccessFS1, fail);

    setTimeout(function(){
		var menuHtml='';

		if(localStorage.getItem("fooduserid"))
		{

	           var menuHtml='<ul class="sub-menu" style="display:none;" >\
               <li><a onclick="myaccount_newstand()" class="account-nav" id="myAccNewstand">My Account</a></li>\
               <li><a onclick="restorePage()" class="restore-nav" id="restorePurchases">Restore Purchases</a></li>\
               <li><a onclick="mainPageNewstand()" class="menu-nav">Main Menu</a></li>\
               <li><a onclick="logoutFromApp()" class="login-nav" id="logoutNewstand">Logout</a></li>\
               </ul>';
    	}

		else
		{

                var menuHtml='<ul class="sub-menu" style="display:none;" >\
               <li><a onclick="resgisterPage()" class="login-nav" id="loginNewstand" style="display:block;">Log In/Sign up</a></li>\
               <li><a onclick="myaccount_newstand()" class="account-nav" id="myAccNewstand">My Account</a></li>\
               <li><a onclick="restorePage()" class="restore-nav" id="restorePurchases">Restore Purchases</a></li>\
               <li><a onclick="mainPageNewstand()" class="menu-nav">Main Menu</a></li>\
			   </ul>';

	    }

    	       appendHtml(newsstandHtml+menuHtml,'',1,news_newsstand);

                $('#popup-outside-page1').popup({ positionTo: "window" });
               //subsciptionBundleId=subsciptionBundleId.trim();
              // subscriptionBundleIdYearly=subscriptionBundleIdYearly;
			   //alert("subsciptionBundleId "+subsciptionBundleId.length);
			    console.log("subsciptionBundleId "+subsciptionBundleId);
			    console.log("subscriptionBundleIdYearly "+subscriptionBundleIdYearly);
				console.log("checkSubscribed "+checkSubscribed);
			   // alert("subscriptionBundleIdYearly "+subscriptionBundleIdYearly.length);
                   if(subsciptionBundleId=='' && subscriptionBundleIdYearly=='')
                   {
                   document.getElementById("subscribe_btn_neswtand").style.display = 'none';
                   }

				   if(paidPaymentTypeGlobal=='album')
                   {
                   document.getElementById("subscribe_btn_neswtand").style.display = 'none';
                   }

				   if(localStorage.getItem("globalPaidCheck"))
				   {
				   document.getElementById("subscribe_btn_neswtand").style.display = 'none';
				   }
               //modified by mohsin
               if(toaster.getUserStatus().length>3)
               {
               $('#logoutNewstand').show();
               $('#loginNewstand').show();
               $('#logoutBtn').show();
			   $('#login_register').hide();
               }
               else
               {
               $('myAccNewstand').hide();
               $('#loginNewstand').show();
               $('#logoutNewstand').show();
               $('#logoutBtn').hide();
			   $('#login_register').hide();

               }

               //  alert('Text Animation Complete');
               },1000);

}

function openPopUpForAlbumType(i,index,paymentAmt,newsstandEditionId, type)
{
    global_i_newstand=i;
    global_index_newstand=index;
    global_paymentAmt_newstand=paymentAmt;
    global_newsstandEditionId_newstand=newsstandEditionId;
    //global_type=type;
    if((!localStorage.getItem("fooduserid")) || localStorage.getItem("fooduserid")==null || localStorage.getItem("fooduserid")=="" || typeof localStorage.getItem("fooduserid") == 'undefined')
    {
        globalVar4Back_newstand=3;
       // loginpageNewstand();
       resgisterPage('newsstandPagePaid');

    }
    else
    {
        console.log("openPopUpForAlbumType start now");
        var monthText="";
        var yearText="";
        var subtype="";
		console.log("Abhi >> ... Type "+type);
		document.getElementById("subscription_title").innerHTML="Choose Your Payment Type:";
        if(albumPrice)
        {
            subtype="album";
            paymentAmt=albumPrice;
            monthText+='<div class="sub_plan" onClick="purchaseSubscription(\''+i+'\',\''+index+'\',\''+albumPrice+'\',\''+newsstandEditionId+'\',\''+subscriptionBundleIdYearly+'\',\''+subtype+'\',\''+albumPriceCurrency+'\');"><span>'+buy_album_newsstand+' '+albumPriceCurrency+" "+albumPrice+'</span></div>';
            document.getElementById("monthly").innerHTML=monthText;
        }
        else
        {
            document.getElementById("monthly").style.display = 'none';
        }
        if(amt)
        {
			console.log("Abhi >> subtype edition ... amt "+amt);
		//	purchasePreview(i,index,paymentAmt,newsstandEditionId,currency)
            subtype="edition";
            paymentAmt=amt;
			if(type == "inApp"){
				console.log("Abhi inApp >>>");
            yearText+='<div class="sub_plan" onClick="purchasePreview(\''+i+'\',\''+index+'\',\''+amt+'\',\''+newsstandEditionId+'\',\''+sessionStorage.getItem("currency")+'\');">'+buy_edition_newsstand+' <span >'+sessionStorage.getItem("currency")+" "+amt+'</span></div>';
            document.getElementById("yearly").innerHTML=yearText;
			}
			else{
				console.log("Abhi paypal_express >>>");
			yearText+='<div class="sub_plan" onClick="purchaseSubscription(\''+i+'\',\''+index+'\',\''+amt+'\',\''+newsstandEditionId+'\',\''+subscriptionBundleIdYearly+'\',\''+subtype+'\',\''+sessionStorage.getItem("currency")+'\');">'+buy_edition_newsstand+' <span >'+sessionStorage.getItem("currency")+" "+amt+'</span></div>';
            document.getElementById("yearly").innerHTML=yearText;
			}
        }
        else
        {
            document.getElementById("yearly").style.display = 'none';
        }
		$('#popup-outside-page1').popup('open');
	}
}


function gotSuccessFS1(fileSystem) {
    window.rootFS = fileSystem.root;
    appDocumentDIR = fileSystem.root.nativeURL;
	}


var globalVar4Back_newstand=0;
var globalIndex_newstand;
function newsstandPagePaid(index)
{
    globalIndex_newstand=index;
    if((!localStorage.getItem("fooduserid")) || localStorage.getItem("fooduserid")==null || localStorage.getItem("fooduserid")=="" || typeof localStorage.getItem("fooduserid") == 'undefined')
    {
        globalVar4Back_newstand=1;
        resgisterPage('newsstandPagePaid');
    }
    else
    {
        if(checkNetworkConnection())
        {
		   console.log("purchasedItems start");
            purchasedItems(index);
            getFileSystemNewsstand();
        }
        else
        {

            $('.appypie-loader').show();
            console.log("index--->newsstandPagePaid-->"+index);
            var xmlFileData=masterData;
            $xml = $(xmlFileData),
            $newsStandIndex = $xml.find("newsstand[indexval="+index+"]" );
            var v=0;
            var innerPageHtml='';
            //newsStandPaidDataPageId'+finalPageId+'#'+index
            var newsStandPageIndex = $xml.find("pageIdentifierBecon").text().split(',');
            var finalPageId='';
            var countPageId=0;
            sessionStorage.setItem("currency",'USD');

			$("#subscribe_btn_neswtand").hide();
            $("#featured_purchase_btn_neswtand").hide();

            $newsStandIndex.find('newsstandHeader').each(function()
                                                         {
                                                         var newsstandEditionId=$(this).find('newsstandEditionId').text();
                                                         if(localStorage.getItem('newsStandPaidData'+newsstandEditionId)==localStorage.getItem("fooduserid"))
                                                         {
                                                         var Heading=$(this).find('newsstandSubHeader').text();
                                                         var checkFullFile=$(this).find('newsFullFileType').text();
                                                         var fileTypeFullPreview='';
                                                         var fileTypeForFullPrev='';
                                                         var openPaidFunction='';
                                                         if(checkFullFile.length > 2)
                                                         {
                                                         fileTypeFullPreview=$(this).find('newsFullFile').text();
                                                         fileTypeForFullPrev=$(this).find('newsFullFileTypeGen').text();
                                                         }

                                                         var Summary=$(this).find('newsstandSummary').text();
                                                         var image=$(this).find('newsstandImage').text();
                                                         var lang=$(this).find('newsstandLang').text();
                                                         var date=$(this).find('newsstandMonthYear').text();
                                                         var newDate=date.split('-');
                                                         var releaseMon=getMonthLoyalty(newDate[0],'full');
                                                         var releaseYear=newDate[1];
                                                         openPaidFunction='<div class="newstand-mobile-botPrice"> <a onclick="checkLocalFile(\''+fileTypeFullPreview+'\',\''+fileTypeForFullPrev+'\',\''+v+'\',\''+index+'\');">'+preview_newsstand+'</a> </div>';


                                                         innerPageHtml+='<div class="newstand-mobile-botView" >\
                                                         <div class="newstand-mobile-monthTtle"> '+releaseMon+' - '+releaseYear+' </div>\
                                                         <div class="newstand-mobile-botPic"><img src="'+image+'"></div>\
                                                         <div class="newstand-mobile-botTile">'+Heading+'</div>\
                                                         <div class="newstand-mobile-description">'+Summary+'</div>\
                                                         '+openPaidFunction+'</div>';


                                                         }
                                                         v++;
                                                         });




            var newsstandHtml='<div class="page-text">\
            <div class="newsTand-MobileTab">\
            <div class="tabViewall" onclick="newsstandPage(\''+index+'\');"><a href="#" >View All</a></div>\
            <div class="tabmyCollection" onclick="newsstandPagePaid(\''+index+'\');" ><a href="#" class="active">My Collection</a></div></div>\
            '+innerPageHtml+'</div>';

            var menuHtml='<ul class="sub-menu" style="display:none;" >\
            <li><a onclick="resgisterPage()" class="login-nav" id="loginNewstand">Log In/Sign up</a></li>\
            <li><a onclick="myaccount_newstand()" class="account-nav" id="myAccNewstand">My Account</a></li>\
            <li><a onclick="restorePage()" class="restore-nav" id="restorePurchases">Restore Purchases</a></li>\
            <li><a onclick="mainPageNewstand()" class="menu-nav">Main Menu</a></li>\
            <li><a onclick="logoutFromApp()" class="login-nav" id="logoutNewstand">Logout</a></li>\
            </ul>';

            console.log("paid page newsstandHtml--->"+newsstandHtml);
            appendHtml(newsstandHtml+menuHtml,'',1,news_newsstand);
        }

    }


}
function openPopUpForSubscriptionType(i,index,paymentAmt,newsstandEditionId,type,secondtype)
{
    global_i_newstand=i;
    global_index_newstand=index;
    global_paymentAmt_newstand=paymentAmt;
    global_newsstandEditionId_newstand=newsstandEditionId;
    global_type=type;
    if((!localStorage.getItem("fooduserid")) || localStorage.getItem("fooduserid")==null || localStorage.getItem("fooduserid")=="" || typeof localStorage.getItem("fooduserid") == 'undefined')
    {
        globalVar4Back_newstand=3;
        resgisterPage('openPopUpForSubscriptionType');
    }
    else
    {

        console.log("openPopUpForSubscriptionType start now");
        var monthText="";
        var yearText="";
        var subtype="";
		document.getElementById("subscription_title").innerHTML=inner_html_choose_ur_subscription_newsstand;

        if(subsPlanMonthGlobal)
        {
            subtype="monthly";
            paymentAmt=subsPlanMonthGlobal;
            monthText+='<div class="sub_plan" onClick="purchaseSubscription(\''+i+'\',\''+index+'\',\''+subsPlanMonthGlobal+'\',\''+newsstandEditionId+'\',\''+type+'\',\''+subtype+'\',\''+subsPlanMonthCurrencyGlobal+'\');">'+subsPlanMonthCurrencyGlobal+' <span>'+subsPlanMonthGlobal+'</span>'+slash_month_newsstand+'</div>';
            document.getElementById("monthly").innerHTML=monthText;
        }
        else
        {
            document.getElementById("monthly").style.display = 'none';
        }
        if(subsPlanYearGlobal)
        {
            subtype="yearly";
            paymentAmt=subsPlanYearGlobal;
            yearText+='<div class="sub_plan" onClick="purchaseSubscription(\''+i+'\',\''+index+'\',\''+subsPlanYearGlobal+'\',\''+newsstandEditionId+'\',\''+secondtype+'\',\''+subtype+'\',\''+subsPlanYearCurrencyGlobal+'\');">'+subsPlanYearCurrencyGlobal+' <span >'+subsPlanYearGlobal+'</span>'+slash_year_newsstand+'</div>';
            document.getElementById("yearly").innerHTML=yearText;
        }
        else
        {
            document.getElementById("yearly").style.display = 'none';
        }
		console.log("type "+type);
		console.log("secondtype "+secondtype);
		$('#popup-outside-page1').popup('open');
		/*
        if(type!='' && secondtype!='')
        {
		console.log("open popup");
            $('#popup-outside-page1').popup('open');
        }
        else
        {
            if(type!='')
            {
                purchaseSubscription(i,index,paymentAmt,newsstandEditionId,type,subtype);
            }
            else if(secondtype!='')
            {
                purchaseSubscription(i,index,paymentAmt,newsstandEditionId,secondtype,subtype);
            }
            else
            {

            }

        }
		*/
    }
    //setTimeout(function() {console.log($("body").html()) },2000);
}

function closePopup()
{
    $('#popup-outside-page1').popup( 'close' );
}
function purchaseSubscription(i,index,paymentAmt,newsstandEditionId,type,subtype,currency)
{
        closePopup();
		console.log("Abhi purchaseSubscription.... type "+type);
        $('.appypie-loader').show();
		sessionStorage.setItem('productEditionId',newsstandEditionId);
        sessionStorage.setItem('subType',subtype);

        if(paymentTypeGlobal=='inApp' && checkNetworkConnection())
        {
            console.log("Abhi globaltype inApp  globalPublicKey "+globalPublicKey);
			if(type!="" && globalPublicKey!="")
			{
			toaster.hitInAppPurchase(type,globalPublicKey,"subscription");
			console.log("inappSuccess purchaseSubscription "+newsstandEditionId);
			//inappSuccess('success');
			}

        }
        else
        {
		if(currency)
		{
			console.log("Abhi purchasePreview call... newsstandEditionId "+newsstandEditionId);
            purchasePreview(i,index,paymentAmt,newsstandEditionId,currency);

		}
		else
		{
			console.log("Abhi purchasePreview call.... >> Amt "+paymentAmt);
		purchasePreview(i,index,paymentAmt,newsstandEditionId);
		}
            $('.appypie-loader').show();
        }

  //  productidinapp='nishant';

}
function paypaLHideShow()
{
    $("#paypal_express").show();
    $("#paypal").hide();
}

function showHidePaypal()
{
    $("#paypal_express").hide();
    $("#paypal").show();
}


function newstand_video(url) {

    window.location = "removewebsite:";
    if (!checkNetworkConnection()) {} else {
        if (window.localStorage.getItem("layout") == "bottom") {
            sessionStorage.setItem("websitePageFlag", "true");
        } else {
            sessionStorage.setItem("websitePageFlag", "false");
        }

        if (sessionStorage.getItem("singleUStreamPage") == "true") {
            sessionStorage.setItem("websitePageFlag", "true");
            appendHtml("",7,1,news_newsstand);



            window.location = "websites:" + url;

        } else {

        	appendHtml("<div class='page-text'></div>",7,2,news_newsstand);


            window.location = "websites:" + url;

        }
    }
}
var global_i_newstand,global_index_newstand,global_paymentAmt_newstand,global_newsstandEditionId_newstand,global_type;
function purchasePreview(i,index,paymentAmt,newsstandEditionId,currency)
{
	console.log("Abhi purchasePreview AMT "+paymentAmt);
	console.log("Abhi purchasePreview newsstandEditionId "+newsstandEditionId);
    global_i_newstand=i;
    global_index_newstand=index;
    global_paymentAmt_newstand=paymentAmt;
    global_newsstandEditionId_newstand=newsstandEditionId;
    if((!localStorage.getItem("fooduserid")) || localStorage.getItem("fooduserid")==null || localStorage.getItem("fooduserid")=="" || typeof localStorage.getItem("fooduserid") == 'undefined')
    {
        globalVar4Back_newstand=2;
        resgisterPage('purchasePreview');
		 $('.appypie-loader').hide();
		 //alert("User not found");
    }
    else
    {
		console.log("Abhi ... UserID >>> "+localStorage.getItem("fooduserid"));
	/*
        if(localStorage.getItem('newsStandPaidData'+newsstandEditionId) == localStorage.getItem("fooduserid"))
        {
            navigator.notification.alert(
                                         'You have already purchased this Item',
                                         alertDismissed,
                                         'Alert!',
                                         'OK'
                                         );
            $('.appypie-loader').hide();
            return false;
        }
	*/
		$('.appypie-loader').hide();
        if(paymentTypeGlobal=='inApp' && checkNetworkConnection())
        {
            sessionStorage.setItem('productEditionId',newsstandEditionId);
            $('.appypie-loader').show();
            //window.location = "inappnewstand:" + productidinapp+"@$$@"+newsstandEditionId;
			if(productidinapp!="" && globalPublicKey!="")
			{
				console.log("inappSuccess purchasePreview managed product Id...>>>");
				toaster.hitInAppPurchase(productidinapp,globalPublicKey,"managed");
				$('.appypie-loader').hide();
			//inappSuccess('success');
			}
        }
        else if(checkNetworkConnection())
        {
			sessionStorage.setItem('productEditionId',newsstandEditionId);
			if(currency)
			{
			paypalExpressNewsStand(i,index,paymentAmt,currency);
			}
			else
			{
			paypalExpressNewsStand(i,index,paymentAmt,sessionStorage.getItem("currency"));
			}
        }
    }
 }

function PayButtonNewstand(i,index,paymentAmt)
{
        $('.appypie-loader').show();
        var card_type=document.getElementById("card_type").value;
        var cnumber=document.getElementById("cnumber").value;
        var ExpairyMonth=document.getElementById("ExpairyMonth").value;
        var ExpairyYear=document.getElementById("ExpairyYear").value;
        var cHolder=document.getElementById("cHolder").value;
        var cvvCode=document.getElementById("cvvCode").value;

        if(isNaN(cnumber) || cnumber.length < 15)
        {
            navigator.notification.alert(notify_enter_validno_newsstand, setTimeout, alert_error_newsstand, alert_ok_newsstand);
            setTimeout(function(){$('.appypie-loader').hide();},1000);
        }
        else if(ExpairyMonth == null || ExpairyMonth == '')
        {
            console.log("ExpairyMonth-->>>"+ExpairyMonth);
            navigator.notification.alert(alert_enter_valid_exp_month_newsstand, setTimeout, alert_error_newsstand, alert_ok_newsstand);
            setTimeout(function(){$('.appypie-loader').hide();},1000);
        }
        else if(ExpairyYear == null || ExpairyYear == '')
        {
            console.log("ExpairyYear-->>>"+ExpairyYear);
            navigator.notification.alert(alert_enter_valid_exp_year_newsstand, setTimeout, alert_error_newsstand, alert_ok_newsstand);
            setTimeout(function(){$('.appypie-loader').hide();},1000);
        }
        else if(!isNaN(cHolder) || cHolder == null || cHolder == '')
        {
            console.log("cHolder-->>>"+cHolder);
            navigator.notification.alert(alert_enter_valid_card_holder_name_newsstand, setTimeout, alert_error_newsstand, alert_ok_newsstand);
            setTimeout(function(){$('.appypie-loader').hide();},1000);
        }
        else if(isNaN(cvvCode) || cvvCode.length < 3)
        {
            console.log("cvvCode-->>>"+cvvCode);
            navigator.notification.alert(alert_enter_valid_cvv_no_newsstand, setTimeout, alert_error_newsstand, alert_ok_newsstand);
            setTimeout(function(){$('.appypie-loader').hide();},1000);
        }
        else
        {

            var cHolder1=cHolder.split(" ");
            var firstName=cHolder1[0];
            var lastName= cHolder1[1];
            var paymentDetail='{"type":"'+card_type+'","number":"'+cnumber+'","expireMonth":"'+ExpairyMonth+'","expireYear":"'+ExpairyYear+'","cvv2":"'+cvvCode+'","firstName":"'+firstName+'","lastName":"'+lastName+'"}';

    var xmlFileData=masterData;
    $xml = $(xmlFileData);
    var newsStandPageIndex = $xml.find("pageIdentifierBecon").text().split(',');
    var fileTypeFullPreview='';
    var fileTypeForFullPrev='';
    $newsStandIndex = $xml.find("newsstand[indexval="+index+"]" );
    var z=0;
    var newsstandEditionId='';
    var currency='';
    var dataType='';
	var productName="";
    $newsStandIndex.find('newsstandHeader').each(function()
                                                 {
                                                 if(i==z)
                                                 {
												 productName=$(this).find('newsstandSubHeader').text();
                                                 newsstandEditionId=$(this).find('newsstandEditionId').text();
                                                 currency=$(this).find('newsstandCurrency').text();
                                                 fileTypeFullPreview=$(this).find('newsFullFile').text();
                                                 fileTypeForFullPrev=$(this).find('newsFullFileTypeGen').text();
                                                 dataType=$(this).find('newsFullFileType').text();

                                                 sessionStorage.setItem("newsStandData"+index,$(this));
                                                 }
                                                 z++;
                                                 });
    var finalPageId='';
    var countPageId=0;

    for(var w=0;w<newsStandPageIndex.length;w++)
    {
        if(newsStandPageIndex[w].indexOf('news')!=-1)
        {
            if(countPageId == index)
            {
                finalPageId = newsStandPageIndex[w];
                console.log("finalPageId--->"+finalPageId);
            }
            countPageId++;
        }


    }

//    // have to remove these lines after development
//    localStorage.setItem('newsStandPaidData'+newsstandEditionId,''+localStorage.getItem("fooduserid"));
//   $('.appypie-loader').hide();
//    if(dataType != null && dataType != 'url')
//    {   getFileSystemNewsstand();
//
//        setTimeout(function(){
//                   downloadNewsStandData(fileTypeFullPreview,fileTypeForFullPrev);
//                   },500);
//
//    }
//    else
//    {
//        filePreview(fileTypeFullPreview,fileTypeForFullPrev,i,index,dataType);
//        return false;
//    }
//
//    return false;
//
//
//    //end

    console.log("fileTypeFullPreview--->"+fileTypeFullPreview+'<---fileTypeForFullPrev--->'+fileTypeForFullPrev);
    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/newsstand-soap#paymentInfoNewsstand";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><paymentInfoNewsstand xmlns=\"http://'+localStorage.getItem("reseller")+'/services/newsstand-soap#paymentInfoNewsstand\"><appId>'+localStorage.getItem('applicationID')+'</appId><pageId>'+finalPageId+'</pageId><paymentDetail>'+paymentDetail+'</paymentDetail><productName>'+productName+'</productName><price>'+paymentAmt+'</price><currency>'+currency+'</currency><transType>authorize</transType></paymentInfoNewsstand></soap:Body></soap:Envelope>';

    console.log("soapRequest--->"+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           console.log('rrrrr=>'+req.responseText);


           var wsUrl1 = "http://"+localStorage.getItem("reseller")+"/services/newsstand-soap#paymentInfoNewsstand";
           var soapRequest1 ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><paymentInfoNewsstand xmlns=\"http://'+localStorage.getItem("reseller")+'/services/newsstand-soap#paymentInfoNewsstand\"><appId>'+localStorage.getItem('applicationID')+'</appId><pageId>'+finalPageId+'</pageId><paymentDetail>'+paymentDetail+'</paymentDetail><productName>'+productName+'</productName><price>'+paymentAmt+'</price><currency>'+currency+'</currency><transType>sale</transType></paymentInfoNewsstand></soap:Body></soap:Envelope>';


           $.ajax({
                  type: "POST",
                  url: wsUrl,
                  contentType: "text/xml",
                  dataType: "text",
                  data: soapRequest,
                  success: function(data, status, req)
                  {
                  console.log("req.responseText--->"+req.responseText);
                  var strJSON=$(req.responseText).find("return").text();
                  console.log("strJSON--->"+strJSON);
                  console.log("indexof--->"+strJSON.indexOf('Decline'));

                  console.log("i--->"+i+"<--index--->"+index);
                  if(strJSON.indexOf('Decline')==-1)
                  {
                  if(sessionStorage.getItem('subType'))
            {
             localStorage.setItem("globalPaidCheck","true");
            }
                  localStorage.setItem('newsStandPaidData'+newsstandEditionId,''+localStorage.getItem("fooduserid"));
                  setTimeout(function(){
                             newsstandPage(index);
                             filePreview(fileTypeFullPreview,fileTypeForFullPrev);

                             },200);

                  }
                  else
                  {
                  navigator.notification.alert(
                                               alert_payment_unsuccessful_newsstand,
                                               alertDismissed,
                                               alert_newsstand,
                                               alert_ok_newsstand
                                               );
                  }

                  console.log("strJSON--->"+strJSON);

                  },
                  error: function(response, textStatus, errorThrown)
                  {
                  navigator.notification.alert(
                                               alert_payment_unsuccessful_newsstand,
                                               alertDismissed,
                                               alert_newsstand,
                                               alert_ok_newsstand
                                               );
                  console.log("error--->"+errorThrown.responseText);
                  setTimeout(function(){$('.appypie-loader').hide();},1000);
                  }
                  });


           setTimeout(function(){$('.appypie-loader').hide();},1000);
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log("error--->"+errorThrown.responseText);
           navigator.notification.alert(
                                        alert_payment_unsuccessful_newsstand,
                                        alertDismissed,
                                        alert_newsstand,
                                        alert_ok_newsstand
                                        );
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });

    }
}

function paypalExpressNewsStand(i,index,paymentAmt,currency)
{
    var paypalAddFormHtml='<!-- Specify a Buy Now button. -->\
    <input type="hidden" name="cmd" value="_xclick">\
    <input type="hidden" name="first_name" value="" >\
    <input type="hidden" name="last_name" value="" >\
    <input type="hidden" name="address1" value="" >\
    <input type="hidden" name="city" value="">\
    <input type="hidden" name="state" value="">\
    <input type="hidden" name="zip" value="">\
    <input type="hidden" name="country" value="">\
    <input type="hidden" name="night_phone_a" value="">\
    <input type="hidden" name="night_phone_b" value="{MOB2}">\
    <input type="hidden" name="night_phone_c" value="{MOB3}">\
    <input type="hidden" name="email" value="'+localStorage.getItem("fooduserid")+'">\
    <!-- Specify details about the item that buyers will purchase. -->\
    <input type="hidden" name="item_name" value="Payment for '+localStorage.getItem("AppName")+'('+localStorage.getItem("applicationID")+')">\
    <input type="hidden" name="amount" value="'+parseFloat(paymentAmt).toFixed(2)+'">\
    <input type="hidden" name="quantity" value="1">\
	<input name=?bn? value=?AppyPie_SP? type=? hidden?>\
    <input type="hidden" name="currency_code" value="'+currency+'">\
    <input type="hidden" name="custom" value="1">';
    var paypalIdHtml;
	if(localStorage.getItem("owneremail").indexOf("mayankr@onsinteractive.com")!=-1)
	{
	 //https://www.sandbox.paypal.com/cgi-bin/webscr
	paypalIdHtml= '<!DOCTYPE HTML><html><body onload="ClickButtonPaypal();"><form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post"><!-- Identify your business so that you can collect the payments. --><input type="hidden" name="business" value="'+sessionStorage.getItem('paypalClientId')+'">';
	}
	else
	{
    paypalIdHtml= '<!DOCTYPE HTML><html><body onload="ClickButtonPaypal();"><form action="https://www.paypal.com/cgi-bin/webscr" method="post"><!-- Identify your business so that you can collect the payments. --><input type="hidden" name="business" value="'+sessionStorage.getItem('paypalClientId')+'">';
    }
	var paypalUrlFormHtml='<!-- URL --><input name="cancel_return" type="hidden" value="http://'+localStorage.getItem("reseller")+'/paypalmobile/newsstand-cancel"><input type="hidden" name="notify_url" value="http://'+localStorage.getItem("reseller")+'/paypalmobile/notify/orderId/'+sessionStorage.getItem("foodRandomId")+'/appId/'+localStorage.getItem("applicationID")+'" /><input type="hidden" name="return" value="http://'+localStorage.getItem("reseller")+'/paypalmobile/newsstand-success" /><!-- Display the payment button. --><input type="image" src="{URL}/images/subscribe_btn.png" name="submit" id="submit" alt="PayPal - The safer, easier way to pay online!"><img alt="" border="0" src="http://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1"></form><script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script><script>function ClickButtonPaypal(){$(\'#submit\').trigger(\'click\');}</script></body></html>';
    window.location="foodpaypal:"+encodeURI(paypalIdHtml+paypalAddFormHtml+paypalUrlFormHtml);
    setTimeout(function(){$('.appypie-loader').hide();},1000);


}

function newsStandpaypalExpressReturn(returnValue)
{
$('.appypie-loader').show();
    if(returnValue == 'Success')
   {
        sessionStorage.clear();
		alertPopUp(alert_message_newsstand,alert_ur_order_success_newsstand);
   }
   else
   {
       alertPopUp(alert_message_newsstand,alert_we_are_sorry_newsstand);
   }
}


function checkLocalFile(fileUrl,fileType)
{

   if(fileUrl)
   {

    $('.appypie-loader').show();
    console.log("checkLocalFile() starts");
    console.log("checkLocalFile() fileUrl---->"+fileUrl+"<---fileType--->"+fileType);

    var fileName1=fileUrl.split('/');
    var fileName=fileName1[(parseFloat(fileName1.length)-1)];
    console.log("checkLocalFile() fileName---->"+fileName+"<---fileUrl--->"+fileUrl);
    console.log("requestedFileSystem--->"+requestedFileSystem);

    if(fileName.indexOf('epub') == -1)
    {

	  $('.appypie-loader').hide();

	  if(fileName.indexOf('jpg') != -1)
	  {
		  window.plugins.photo.show({ videoid:'0', videoInfo:fileUrl}, function() {}, function() {} );

		 	 setTimeout(function(){$('.appypie-loader').hide();},500);

	  }
	   else if(fileType=="Audio")
      {
          var songInfo="";
          var plsType="shoutcast";
          if(fileUrl.indexOf('.m3u')!=-1)
          {
              fileUrl=fileUrl.replace(".m3u", "");
              songInfo=songInfo+fileUrl+",";
          }
          else if(fileUrl.indexOf('.mp3')!=-1)
          {
              plsType="mp3";
              songInfo=songInfo+"@@--@@"+"title"+"#####"+fileUrl+"#####"+"";
          }
          else
          {
              songInfo=songInfo+fileUrl+",";
          }
          window.plugins.playerplugin.show({plsTypeValue:plsType,url:songInfo,type:'customRadio',imageurl:"",channalNameValue:"Music"}, function() {}, function() {} );

          setTimeout(function(){$('.appypie-loader').hide();},500);
      }

      /***************if preview is video***************************/
      else if(fileType=="Video")
      {


          if(fileUrl.indexOf('.vimeo')!=-1)
          {
              var fileUrl=fileUrl.split('/');
              var vimeoId=fileUrl[fileUrl.length - 1];
              window.location ="playvimeo:"+vimeoId;
              setTimeout(function(){$('.appypie-loader').hide();},500);
          }
          else if(fileUrl.indexOf('.m3u8')!=-1)
          {
              $('.appypie-loader').show();
              window.location ="removeWebSite:";
              if(!checkNetworkConnection())
              {
                  setTimeout(function(){$('.appypie-loader').hide();},500);
              }else
              {
                  window.location="m3u8url:"+fileUrl;
                  setTimeout(function(){$('.appypie-loader').hide();},500);
              }
          }
          else
          {
              newstand_video(fileUrl);
              setTimeout(function(){$('.appypie-loader').hide();},500);
          }
      }

	  else
	  {
		  toaster.PdfUrlToaster(fileUrl);
	  }


        //requestedFileSystem.root.getFile("newStandData/"+fileName+"", {create: false}, function (directory) {
           //                              console.log("the file exist "+fileName);
                                        // var documentPathForIcon=localStorage.getItem("documentPath");
                                         //var documentPath=documentPathForIcon.replace("/homeIcon", "");
              //                           var filedownloadPath=appDocumentDIR.replace("file://","") + "newStandData/"+fileName;
               //                          console.log("checkLocalFile() filedownloadPath--->"+filedownloadPath);
               //                          filePreview(filedownloadPath.replace("//","/"),fileType);
                //                         },function (){
                //                         console.log("checkLocalFile() error condition");
                //                         downloadNewsStandData(fileUrl,fileType);
                //                         });
    }
    else
    {
        requestedFileSystem.root.getDirectory("newStandData/"+fileName+"", {create: false}, function (directory) {
                                              console.log("the file exist "+fileName);
                                             // var documentPathForIcon=localStorage.getItem("documentPath");
                                              //var documentPath=documentPathForIcon.replace("/homeIcon", "");
                                              var filedownloadPath=appDocumentDIR.replace("file://","") + "/newStandData/"+fileName;
                                              console.log("checkLocalFile() filedownloadPath--->"+filedownloadPath);

                                              filePreview(filedownloadPath.replace("//","/"),fileType);

                                              },function (){

                                              console.log("checkLocalFile() error condition");
                                              downloadNewsStandData(fileUrl,fileType);
                                              });

    }

    console.log("checkLocalFile() ends");
	}
	else
	{
   navigator.notification.alert(
                                     alert_file_not_found_newsstand,
                                     alertDismissed,
                                     alert_message_newsstand,
                                     alert_ok_newsstand
                                     );
	}
}
/****************************seprate code for iOS and Android***********************/

function filePreview(fileUrl,fileType)
{
    console.log("fileUrl"+fileUrl);

    if(fileUrl)
	{
    if(!checkNetworkConnection())
    {
        // show alert message "Please  enable intenet to download file."
        navigator.notification.alert(
                                     alert_check_internet_conn_newsstand,
                                     alertDismissed,
                                     alert_message_newsstand,
                                     alert_ok_newsstand
                                     );
    }
    else
    {
        if(fileUrl.trim().length>0)
        {
            /***************if preview is image***************************/
            if(fileType=="Image")
            {
                //alert(fileUrl);

            	window.plugins.photo.show({ videoid:'0', videoInfo:fileUrl}, function() {}, function() {} );

            	 setTimeout(function(){$('.appypie-loader').hide();},500);
            }

            /***************if preview is audio***************************/
            else if(fileType=="Audio")
            {
                var songInfo="";
                var plsType="shoutcast";
                if(fileUrl.indexOf('.m3u')!=-1)
                {
                    fileUrl=fileUrl.replace(".m3u", "");
                    songInfo=songInfo+fileUrl+",";
                }
                else if(fileUrl.indexOf('.mp3')!=-1)
                {
                    plsType="mp3";
                    songInfo=songInfo+"@@--@@"+"title"+"#####"+fileUrl+"#####"+"";
                }
                else
                {
                    songInfo=songInfo+fileUrl+",";
                }
                window.plugins.playerplugin.show({plsTypeValue:plsType,url:songInfo,type:'customRadio',imageurl:"",channalNameValue:"Music"}, function() {}, function() {} );

                setTimeout(function(){$('.appypie-loader').hide();},500);
            }

            /***************if preview is video***************************/
            else if(fileType=="Video")
            {
                if(fileUrl.indexOf('.vimeo')!=-1)
                {
                    var fileUrl=fileUrl.split('/');
                    var vimeoId=fileUrl[fileUrl.length - 1];
                    window.location ="playvimeo:"+vimeoId;
                    setTimeout(function(){$('.appypie-loader').hide();},500);
                }
                else if(fileUrl.indexOf('.m3u8')!=-1)
                {
                    $('.appypie-loader').show();
                    window.location ="removeWebSite:";
                    if(!checkNetworkConnection())
                    {
                        setTimeout(function(){$('.appypie-loader').hide();},500);
                    }else
                    {
                        window.location="m3u8url:"+fileUrl;
                        setTimeout(function(){$('.appypie-loader').hide();},500);
                    }
                }
                else
                {
                    newstand_video(fileUrl);
                    setTimeout(function(){$('.appypie-loader').hide();},500);
                }
            }
            else if(fileType=="E-book")
            {
                //downloadFileAndOpen(fileUrl);
				OpenEbook(fileUrl);
				 setTimeout(function(){$('.appypie-loader').hide();},3000);
            }
            else
            {
                newstand_video(fileUrl);
                setTimeout(function(){$('.appypie-loader').hide();},500);
            }
        }
    }
	}
	else
	{
	navigator.notification.alert(
                                 alert_there_no_preview_avail_newsstand,
                                 alertDismissed,
                                 alert_message_newsstand,
                                 alert_ok_newsstand
                                 );
	 setTimeout(function(){$('.appypie-loader').hide();},500);
	}
}

var readerFilesURLValue="";
var appDocumentDIR="";
function downloadFileAndOpen(fileUrl)
{
    $('.appypie-loader').show();
    readerFilesURLValue=fileUrl;
    window.requestFileSystem = window.requestFileSystem || window.webkitRequestFileSystem;
    window.requestFileSystem(LocalFileSystem.PERSISTENT, 0, gotSuccessFS, fail);

}


function OpenEbook(fileUrl)
{
   //alert(fileUrl);
   readEBook(fileUrl);
}


function gotSuccessFS(fileSystem) {

    window.rootFS = fileSystem.root;
    appDocumentDIR = fileSystem.root.nativeURL;

    var url=decodeURIComponent(readerFilesURLValue);
    var n = url.substr(url.indexOf('://') + 3);
    n = n.replace(/\//g, "");
                  var fileURl="";
                  if(url.indexOf(".epub")!=-1)
                  {
                  fileURl = appDocumentDIR + "NewsDownloads/" + n;
                  }
                  else if(url.indexOf(".pdf")!=-1)
                  {
                  fileURl = appDocumentDIR + "Newsepubtemp/" + n;
                  }
                  downloadEBooks(url, fileURl);
                  }


                  function downloadEBooks(downloadfileURl,fileURL)
                  {
                  var fileTransfer = new FileTransfer();
                  var uri = encodeURI(downloadfileURl);
                  fileTransfer.download(uri,fileURL,
                                        function (entry)
                                        {
                                        //$('.appypie-loader').hide();
                                        if(entry.nativeURL.indexOf(".epub")!=-1)
                                        {
                                        window.plugins.downloadunzip.show({url:entry.nativeURL,info:entry.name}, function() {}, function() {});
                                        }
                                        setTimeout(function(){
                                                   var result=entry.name;
                                                   result=result.replace(/ /g, '++++')
                                                   readEBook(result);
                                                   },500);
                                        },
                                        function (error)
                                        {
                                        $('.appypie-loader').hide();
                                        console.log("download error source " + error.source);
                                        },
                                        false
                                        );
                  }

                  function readEBook(fileName)
                  {
                  $('.appypie-loader').hide();
                  if(fileName.indexOf(".epub")!=-1)
                  {
                  var stringforbook = "/storage/sdcard0/NewsDownloads/"+fileName;
                  window.plugins.ebookviewer.show({ url:stringforbook, info:fileName,authorname:localStorage.getItem("AuthorName"),title:fileName}, function() {}, function() {} );
                  }
                  else  if(fileName.indexOf(".pdf")!=-1)
                  {
                  //fileName =fileName.split('++++').join(' ');
                  //var stringforbook = "/storage/sdcard0/epubtemp/"+fileName;

                  //stringforbook = "/storage/sdcard0/Newsepubtemp/"+stringforbook;

				  //var stringforbook=fileName.replace("file://","");
                  //window.plugins.pdfviewerplugin.show({ url:stringforbook}, function() {}, function() {} );

				   toaster.PdfUrlToaster(fileName);
                  }
                  }

                  function fail() {

                  }

                  function newstand_video(url) {

                  if(window.localStorage.getItem("layout")=="bottom"){
                  sessionStorage.setItem("websitePageFlag","true");
                  }
                  else {
                  sessionStorage.setItem("websitePageFlag","false");
                  }

                  appendHtml("<div class='page-text'></div>",7,2,'news');


                  if(window.localStorage.getItem("layout")=="bottom") {
                  window.location="bottom"+url;
                  }
                  else {
                  window.location="show"+url;
                  }
                  }


                  /****************************seprate code for iOS and Android end here***********************/
//requestedFileSystem it is defined in code.js for global use in every file

function downloadNewsStandData(fileToDownload,fileType)
{

    if(!checkNetworkConnection())
    {

    }
    else
    {
        $('.appypie-loader').show();
        $('#appyLoaderText').html("Please Wait while we download data for offline use");
        console.log("downloadNewsStandData() starts");
      //  var documentPathForIcon=localStorage.getItem("documentPath");
       // var documentPath=documentPathForIcon.replace("/homeIcon", "");
        //console.log("documentPath---->"+documentPath);
        //console.log("fileToDownload---->"+documentPath);
        var fileName1=fileToDownload.split('/');
        var fileName=fileName1[(parseFloat(fileName1.length)-1)];

        console.log("fileName1.length--->"+fileName1.length+"<--fileName1[fileName1.length]--->"+fileName);
        var newDirPath=requestedFileSystem.root.nativeURL;
        var filedownloadPath=newDirPath + "newStandData/"+fileName;
        requestedFileSystem.root.getDirectory("newStandData", {create: true, exclusive: false}, function (directory) {
                                              console.log("success function");
                                              var fileTransfer = new FileTransfer();
                                              var uri = fileToDownload;
                                              console.log("uri--->"+uri);
                                              if(uri.indexOf('%')!=-1)
                                              {
                                              }
                                              else
                                              {
                                              uri = encodeURI(fileToDownload);
                                              }
                                              console.log("fileToDownload :: " + fileToDownload + " to path -->  " + filedownloadPath);
                                              fileTransfer.download(uri,filedownloadPath,function(entry)
                                                                    {
                                                                    filedownloadPath=filedownloadPath.replace("file://","");
                                                                    filedownloadPath=filedownloadPath.replace("//","/");
                                                                    if(fileName.indexOf('epub') != -1)
                                                                    {
                                                                   // unzipepuballNewstand(filedownloadPath,fileName);
																	 window.plugins.downloadunzip.show({url:filedownloadPath,info:fileName}, function() {}, function() {});
                                                                    }
                                                                    else
                                                                    {
                                                                    filePreview(filedownloadPath,fileType);
                                                                    }
                                                                    $('#appyLoaderText').html("download complete");
                                                                    setTimeout(function(){
                                                                                $('#appyLoaderText').html("");
                                                                               $('.appypie-loader').hide();
                                                                               },1000);

                                                                    },
                                                                    function (error){

    navigator.notification.alert(
                                 alert_check_net_conn_try_again_newsstand,
                                 alertDismissed,
                                 alert_download_unsuccessfull,
                                 alert_ok_newsstand
                                 );
                                                                    $('#appyLoaderText').html("");
                                                                    $('.appypie-loader').hide();

                                                                    if(fileName.indexOf('epub') != -1)
                                                                    {
                                                                    }
                                                                    else
                                                                    {
                                                                    filePreview(filedownloadPath,fileType);
                                                                    }
                                                                    },false, {headers: {"Authorization": "Basic dGVzdHVzZXJuYW1lOnRlc3RwYXNzd29yZA=="}}
                                                                    );
                                              },function (){
                                              console.log("error condition");
                                              });
    }
    console.log("downloadNewsStandData() ends");
}

function getFileSystemNewsstand()
{

    var newFileSystem='';
    window.requestFileSystem = window.requestFileSystem || window.webkitRequestFileSystem;
    window.requestFileSystem(LocalFileSystem.PERSISTENT, 0,gotFileSystemNewsStand, fail);
}


var requestedFileSystem;
function gotFileSystemNewsStand(fileSystem)
{
    requestedFileSystem=fileSystem;
    console.log("requestedFileSystem--->"+requestedFileSystem);
}

function inappSuccess(value)
{
//alert(value);
$('.appypie-loader').hide();
console.log("onProductPurchased inappsuccess========"+value);
if(value=='failed')
{
 setTimeout(function(){$('.appypie-loader').hide();},1000);
}
else
{
    var editionId=sessionStorage.getItem('productEditionId');
    var finalPageId='';
    var index=sessionStorage.getItem('newsStandPageIndex');
    var newsStandPageIndex = $(masterData).find("pageIdentifierBecon").text().split(',');
    var countPageId=0;
    for(var w=0;w<newsStandPageIndex.length;w++)
    {
        if(newsStandPageIndex[w].indexOf('news')!=-1)
        {
            if(countPageId == index)
            {
                finalPageId = newsStandPageIndex[w];
                console.log("finalPageId--->"+finalPageId);
            }
            countPageId++
        }
    }

   if(sessionStorage.getItem('subType'))
   {
     sessionStorage.setItem('subsciptionType',sessionStorage.getItem('subType'));
        var currentTimeStamp = getCurrentTimeStamp();
        var days=0;
        if(subsciptionTypeValue == 'monthly')
        {
            days=30;
        }
        if(subsciptionTypeValue == 'yearly')
        {
            days=365;
        }

        console.log("currentTimeStamp --->"+currentTimeStamp);
        console.log("getCurrentTimeStamp --->"+getCurrentTimeStamp());
        console.log("(parseFloat(currentTimeStamp)+parseFloat(timeStampForDate(days)))   --->"+(parseFloat(currentTimeStamp)+parseFloat(timeStampForDate(days))));

        localStorage.setItem('subscriptionValuepageId'+finalPageId+'user'+localStorage.getItem('fooduserid'),(parseFloat(currentTimeStamp)+parseFloat(timeStampForDate(days))));


   }


    if(sessionStorage.getItem('subsciptionType'))
    {
        var subsciptionTypeValue=sessionStorage.getItem('subsciptionType');
        sessionStorage.removeItem('subsciptionType');
    }
    else
    {
        var subsciptionTypeValue='edition';
    }

    var Heading='';
    var checkFullFile='';
    var Summary='';
    var image='';
    var fileTypeFullPreview='';
    var fileTypeForFullPrev='';
    var checkFullFile='';
    var index=sessionStorage.getItem('newsStandPageIndex');
    var newsStandPageIndex = $(masterData).find("pageIdentifierBecon").text().split(',');
    var countPageId=0;
    for(var w=0;w<newsStandPageIndex.length;w++)
    {
        if(newsStandPageIndex[w].indexOf('news')!=-1)
        {
            if(countPageId == index)
            {
                finalPageId = newsStandPageIndex[w];
                console.log("finalPageId--->"+finalPageId);
            }
            countPageId++;
        }
    }
    $newsStandIndex = $(masterData).find("newsstand[indexval="+index+"]" );
    $newsStandIndex.find('newsstandHeader').each(function()
                                                 {
                                                 var newsstandEditionId=$(this).find('newsstandEditionId').text();
                                                 if(newsstandEditionId == editionId)
                                                 {
                                                  Heading=$(this).find('newsstandSubHeader').text();
                                                  checkFullFile=$(this).find('newsFullFileType').text();
                                                  fileTypeFullPreview=$(this).find('newsFullFile').text();
                                                  fileTypeForFullPrev=$(this).find('newsFullFileTypeGen').text();
                                                  Summary=$(this).find('newsstandSummary').text();
                                                  image=$(this).find('newsstandImage').text();
                                                 }
												 if(sessionStorage.getItem('subsciptionType'))
                                                 {
												 localStorage.setItem('newsStandPaidData'+newsstandEditionId,''+localStorage.getItem("fooduserid"));
												 }

                                                 });



   var paymentInfo='{"periodType":"'+subsciptionTypeValue+'","currency":"USD","paymentType":"inApp","totalPrice":"550","transictionId":"PAY-'+editionId+'"}';

    var productInfo= '[{"editionId":"'+editionId+'","fullFileType":"'+checkFullFile+'","fullFile":"'+fileTypeFullPreview+'","fullFileTypeGen":"'+fileTypeForFullPrev+'","image":"'+image+'","price":"0","language":""}]';


    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/newsstand-soap#createOrderSubscription";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><createOrderSubscription xmlns=\"http://'+localStorage.getItem("reseller")+'/services/newsstand-soap#createOrderSubscription\"><appId>'+localStorage.getItem('applicationID')+'</appId><pageId>'+finalPageId+'</pageId><userEmail>'+localStorage.getItem('fooduserid')+'</userEmail><newsstandJson>'+paymentInfo+'</newsstandJson><subscriptionList>'+productInfo+'</subscriptionList><heading><![CDATA['+Heading+']]></heading><summary><![CDATA['+Summary+']]></summary></createOrderSubscription></soap:Body></soap:Envelope>';
    console.log("soapRequest--->"+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
		    localStorage.setItem('newsStandPaidData'+editionId,''+localStorage.getItem("fooduserid"));
		    if(sessionStorage.getItem('subType'))
            {
             localStorage.setItem("globalPaidCheck","true");
			 sessionStorage.removeItem('subType');
            }

           console.log("response--->"+req.responseText);
           var strJSON=$(req.responseText).find('return').text();
           console.log("str ---> "+strJSON);

           $('.appypie-loader').hide();
           if(checkFullFile != null && checkFullFile != 'url')
           {
		    getFileSystemNewsstand();
            //downloadNewsStandData(fileTypeFullPreview,fileTypeForFullPrev);
            setTimeout(function(){
                     newsstandPagePaid(index);
                      },1000);
           }
           else
           {
		   setTimeout(function(){
                     newsstandPagePaid(index);
                      },1000);
           filePreview(fileTypeFullPreview,fileTypeForFullPrev);
           return false;
           }
           },
           error: function(response, textStatus, errorThrown)
           {
           localStorage.setItem('newsStandPaidData'+editionId,''+localStorage.getItem("fooduserid"));
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
     }
}

function purchasedItems(index)
{
    $('.appypie-loader').show();
    var newsStandPageIndex = $(masterData).find("pageIdentifierBecon").text().split(',');
    var finalPageId=0;
    var countPageId=0;
    for(var w=0;w<newsStandPageIndex.length;w++)
    {
        if(newsStandPageIndex[w].indexOf('news')!=-1)
        {
            if(countPageId == index)
            {
                finalPageId = newsStandPageIndex[w];
                console.log("finalPageId--->"+finalPageId);
            }
            countPageId++;
        }
    }

    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/newsstand-soap#orderHistoryJson";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><orderHistoryJson xmlns=\"http://'+localStorage.getItem("reseller")+'/services/newsstand-soap#orderHistoryJson\"><appId>'+localStorage.getItem('applicationID')+'</appId><pageId>'+finalPageId+'</pageId><userEmail>'+localStorage.getItem('fooduserid')+'</userEmail></orderHistoryJson></soap:Body></soap:Envelope>';
    var htmlData='';
    console.log("soapRequest--->"+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON=$(req.responseText).find('return').text();
           if(typeof(strJSON) != "undefined" && strJSON != "null" && strJSON != null)
           {
            var strJSON=$(req.responseText).find('return').text();
           console.log("strJson"+strJSON);
           var obj=jQuery.parseJSON(strJSON);
           for(var x=0;x<obj.length;x++)
           {
           var objList=obj[x].orderList;
           var heading=objList[0].newsstandSubHeader;
           var newsstandEditionId=objList[0].newsstandEditionId;
           var newsFullFile=objList[0].newsFullFile;
           var newsFullFileTypeGen=objList[0].newsFullFileTypeGen;
           var newsstandSummary=objList[0].newsstandSummary;
           var newsstandImage=objList[0].newsstandImage;
           localStorage.setItem('newsStandPaidData'+newsstandEditionId,localStorage.getItem("fooduserid"));
           console.log("heading -->"+heading+"<---newsstandEditionId-->"+newsstandEditionId);
           htmlData+='<div class="newstand-mobile-botView" ><div class="newstand-mobile-monthTtle"> </div>\
           <div class="newstand-mobile-botPic"><img src="'+newsstandImage+'"></div>\
           <div class="newstand-mobile-botTile">'+heading+'</div>\
           <div class="newstand-mobile-description">'+newsstandSummary+'</div>\<div class="newstand-mobile-botPrice"> <a onclick="checkLocalFile(\''+newsFullFile+'\',\''+newsFullFileTypeGen+'\');">View</a> </div></div>';

           }

           var newsstandHtml='<div class="page-text">\
           <div class="newsTand-MobileTab">\
           <div class="tabViewall" onclick="newsstandPage(\''+index+'\');"><a href="#" >View All</a></div>\
           <div class="tabmyCollection" onclick="newsstandPagePaid(\''+index+'\');"><a href="#" class="active">My Collection</a></div></div>\
           \
           <div class="newstand-mobile-botTitle">\
           <b>Purchased issues</b></div>\
           '+htmlData+'</div>';


          console.log("paid page newsstandHtml--->"+newsstandHtml);
           var menuHtml='<ul class="sub-menu" style="display:none;" >\
           <li><a onclick="myaccount_newstand();" class="account-nav" id="myAccNewstand">My Account</a></li>\
           <li><a onclick="restorePage();" class="restore-nav" id="restorePurchases">Restore Purchases</a></li>\
           <li id="homeIcon" class="homeIconClass"><a onclick="mainPageNewstand();" class="menu-nav">Main Menu</a></li>\
           <li><a onclick="logoutFromApp();" class="login-nav" id="logoutNewstand">Logout</a></li>\
           </ul>';
           //appendHtml(newsstandHtml+menuHtml,'',1,'news');
           console.log("changes for issue-->"+newsstandHtml);
            appendHtml(newsstandHtml+menuHtml,'',1);
                      //  alert('Text Animation Complete');
           }
           else
           {
           navigator.notification.alert(
                                            alert_no_purchase_yet_newsstand,
                                        alertDismissed,
                                        alert_newsstand,
                                        alert_ok_newsstand
                                        );
           $('.appypie-loader').hide();
           }
           },
           error: function(response, textStatus, errorThrown)
           {
           navigator.notification.alert(
                                        alert_server_error_try_some_time_newsstand,
                                        alertDismissed,
                                        alert_newsstand,
                                        alert_ok_newsstand
                                        );
           console.log("error--->"+errorThrown.responseText);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
}

var logincheck="";
function loginpageNewstand(pageName)
{
console.log("loginpageNewstand "+pageName);
                if(toaster.getUserStatus().length>3)
                  {
                  myaccount_newstand();
                  }
                  else
                  {
				  logincheck="true";
				  var y=2;
                  var pageLevel=2;
                  if(pageName)
                  {
                  y=3;
                  pageLevel=3;
                  }
                  else
                  {
                  pageName="";
                  }

                  var menuHtml='<ul class="sub-menu" style="display:none;" >\
                  <li><a onclick="newsstandPage('+sessionStorage.getItem('newsStandPageIndex')+')" class="home-nav">Home</a></li>\
                  <li><a onclick="restorePage()" class="restore-nav" id="restorePurchases">Restore Purchases</a></li>\
                  <li><a onclick="mainPageNewstand()" class="menu-nav">Main Menu</a></li>\
                  </ul>';
                  var html='<div class="appypie-login"><h2 id="lgnS">'+html_log_in_newsstand+'</h2><div class="login-feald"><label id="lgnE">'+html_emailid_newsstand+'</label><input data-role="none" name="EmailId_newstand"  type="email" data-prevent-focus-zoom="false" /></div><div class="login-feald"><label id="lgnP">'+html_password_newsstand+'</label><input data-role="none" name="Password_newstand"  type="password" data-prevent-focus-zoom="false" /></div><div class="login-feald" ><a class="login_btn" '+primaryColor+' onclick="Login_newstand();" id="lgnB">'+html_login_newsstand+'</a><a onclick="forgotpassword_newstand(\''+pageName+'\');" id="lgnF">'+html_forgot_pass_newsstand+'</a></div><div class="fb-div"></br><br clear="all" /><p id="lgnA">'+html_dont_ve_account_newsstand+'</p> <a onclick="signup_newstand(\''+pageName+'\');" id="lgnSU">'+html_signup_now+'</a></div></div>';
    appendHtml(html+menuHtml,y,pageLevel,'news');
    }


}
function Login_newstand()
{

    setTimeout(function() {
               window.scrollTo(document.body.scrollLeft, document.body.scrollTop);
              $("#logo").show();
               $("#mainback").hide();
               var wsUrl ="http://"+window.localStorage.getItem("reseller")+"/services/app-user-soap#appUserLoginNew/";
               var email = document.getElementsByName('EmailId_newstand')[0].value;
               var password = document.getElementsByName('Password_newstand')[0].value;

               if(email == "" || password == "")
               {
               navigator.notification.alert(
                                            alert_emailid_pass_cant_blank_newsstand,
                                            alertDismissed,
                                            alert_newsstand,
                                            alert_ok_newsstand
                                            );
               }else{

               var soapRequest =
               '<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><appUserLoginNew xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/app-user-soap#appUserLoginNew/\"><email>'+email+'</email><password>'+password+'</password><appid>'+window.localStorage.getItem("applicationID")+'</appid></appUserLoginNew></soap:Body></soap:Envelope>';
               //modified by mohsin
			   window.localStorage.setItem("profileEmail",email);
			   //--
               //console.log(" " + soapRequest);
               jQuery.support.cors = true;
               $.ajax({
                      type: "POST",
                      url: wsUrl,
                      contentType: "text/xml",
                      dataType: "text",
                      data: soapRequest,
                      success: processSuccessxmlupdatelogin_newstand,
                      error: processErrorxmlupdatelogin_newstand
                      });

               } }, 0);
}

function processSuccessxmlupdatelogin_newstand(data, status, req)
{
    var xml = req.responseText;
    xmlDoc = $.parseXML( xml );
    $xml = $( xmlDoc );
    $title = $xml.find("return");
    var status=$title.text();


    if(status!="false")
    {
        localStorage.setItem("userLogin","true");
		//modified by mohsin
        localStorage.setItem('fooduserid',window.localStorage.getItem("profileEmail"));
		toaster.setloyalityEmail(localStorage.getItem('fooduserid'));
		//--
		toaster.setUserStatus(status);
		if(logincheck=="true")
		{
         myaccount_newstand();
		}
		else
		{
		history.go(-1);
		}

		/*
        if(globalVar4Back_newstand==1)
        {
		history.go(-1);
            newsstandPagePaid(globalIndex_newstand);
        }
        else if(globalVar4Back_newstand==2)
        {
		history.go(-1);
            purchasePreview(global_i_newstand,global_index_newstand,global_paymentAmt_newstand,global_newsstandEditionId_newstand)
        }
        else if(globalVar4Back_newstand==3)
        {
            history.go(-1);
        }
        else
        {
		history.go(-1);
            newsstandPage(sessionStorage.getItem('newsStandPageIndex'));
        }
        */
    }else
    {
        localStorage.setItem("userLogin","false");
        localStorage.removeItem('fooduserid');

        navigator.notification.alert(
                                     alert_usrname_or_pass_invalid_newsstand,
                                     alertDismissed,
                                     alert_newsstand,
                                     alert_ok_newsstand
                                     );

    }

}
function processErrorxmlupdatelogin_newstand(data, status, req)
{
    //console.log("Error : "+ req.responseText);
}
function forgotpassword_newstand(pageName)
{
    $("#fb-div").show();
                  var y=3;
                  var pageLevel=3;
                  if(pageName)
                  {
                  y=4;
                  pageLevel=4;
                  }
                  else
                  {
                  pageName="";
                  }

                  var html='<div class="appypie-login forgot-pss">\
                  <h2 >Forgot Password</h2>\
                  <div class="login-feald"><label>Email Id</label><input data-role="none" id="EmailIdreset_newstand" name="EmailId" type="text" /></div><br />\
                  <div class="login-feald"><a class="submit_btn" onclick="resetpass_newstand();">Reset Password</a></div><br />\
                  <div class="fb-div"><div id="fb-div" style="display:none;"><p></p> <a   onclick="Sign_newstand(\''+pageName+'\');" id="frgtS"></a></div><br /><br />\
                  <p >Do not have an account?</p> <a onclick="loginpageNewstand(\''+pageName+'\');" >Log In</a></div></div>';

                  var menuHtml='<ul class="sub-menu" style="display:none;" >\
                  <li><a onclick="newsstandPage('+sessionStorage.getItem('newsStandPageIndex')+')" class="home-nav">Home</a></li>\
                  <li><a onclick="restorePage()" class="restore-nav" id="restorePurchases">Restore Purchases</a></li>\
                  <li><a onclick="mainPageNewstand()" class="menu-nav">Main Menu</a></li>\
                  </ul>';
    appendHtml(html+menuHtml,y,pageLevel,news_newsstand);


}

function resetpass_newstand()
{

        var wsUrl = "http://"+window.localStorage.getItem("reseller")+"/services/app-user-soap#appUserForgotPassword/";
        var email = document.getElementById('EmailIdreset_newstand').value;
        if(email == "")
        {


        navigator.notification.alert(
                                    alert_emailid_cant_blank_newsstand,
                                    alertDismissed,
                                    alert_newsstand,
                                    alert_ok_newsstand
                                    );

        }else{

        if(!checkEmail(email))
        {
        return 0;
        }

        var soapRequest =
        '<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><appUserForgotPassword xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/app-user-soap#appUserForgotPassword/\"><email>'+email+'</email><appid>'+window.localStorage.getItem("applicationID")+'</appid></appUserForgotPassword></soap:Body></soap:Envelope>';

        //console.log(" " + soapRequest);

        $.ajax({
              type: "POST",
              url: wsUrl,
              contentType: "text/xml",
              dataType: "text",
              data: soapRequest,
              success: processSuccessxmlupdatereset_newstand,
              error: processErrorxmlupdatereset_newstand
              });


        }

}
function processSuccessxmlupdatereset_newstand(data, status, req) {

    var xml = req.responseText;
    xmlDoc = $.parseXML( xml );
    $xml = $( xmlDoc );
    $title = $xml.find( "return" );
    var status=$title.text();

    if(status=="true")
    {

    navigator.notification.alert(
                                alert_pass_sent_ur_emailid_newsstand,
                                alertDismissed,
                                alert_newsstand,
                                alert_ok_newsstand
                                );
    onBackKeyDown('food');

    }else
    {

    navigator.notification.alert(
                                alert_unable_reset_pass_newsstand,
                                alertDismissed,
                                alert_newsstand,
                                alert_ok_newsstand
                                );


    }

}

function processErrorxmlupdatereset_newstand(data, status, req) {
//console.log("Error : "+ req.responseText);
}
function signup_newstand(pageName)
{

                  var y=2;
                  var pageLevel=2;
                  if(pageName)
                  {
                  y=3;
                  pageLevel=3;
                  }
                  else
                  {
                  pageName="";
                  }
               var menuHtml='<ul class="sub-menu" style="display:none;" >\
               <li><a onclick="newsstandPage('+sessionStorage.getItem('newsStandPageIndex')+')" class="home-nav">Home</a></li>\
               <li><a onclick="restorePage()" class="restore-nav" id="restorePurchases">Restore Purchases</a></li>\
               <li><a onclick="mainPageNewstand()" class="menu-nav">Main Menu</a></li>\
               </ul>';
                  var html='<div class="appypie-login">\
                  <h2>Create a profile that you can use on all of your devices</h2>\
                  <div class="login-feald"><label>Name<sup class="red-astrick">*</sup></label><input data-role="none"  type="text"  name="name_newstand"  /></div>\
                  <div class="login-feald"><label>Email Id<sup class="red-astrick">*</sup></label><input data-role="none" type="text" name="email_newstand" /></div>\
                  <div class="login-feald"><label>Phone<sup class="red-astrick">*</sup></label><input data-role="none" type="tel" placeholder="000-000-0000" name="contact_newstand" /></div>\
                  <div class="login-feald"><label >Password<sup class="red-astrick">*</sup></label><input data-role="none" type="password" name="password_newstand" /></div>\
                  <div class="login-feald"><label >Confirm Password<sup class="red-astrick">*</sup></label><input data-role="none" type="password" name="cpassword_newstand" /></div>\
                  <div class="login-feald"><a class="submit_btn" '+primaryColor+' onclick="Sign_newstand();" >Sign Up</a></div>\
                  <br />\
                  <div class="fb-div"><p >Already have an account?</p> <a onclick="loginpageNewstand(\''+pageName+'\');"  >Log In</a></div>\
                  </div>'
               appendHtml(html+menuHtml,y,pageLevel,news_newsstand);
}


function Sign_newstand()
{

  var name = document.getElementsByName('name_newstand')[0].value;
  var email = document.getElementsByName('email_newstand')[0].value;
  var contact = document.getElementsByName('contact_newstand')[0].value;
  var password = document.getElementsByName('password_newstand')[0].value;
  var cpassword = document.getElementsByName('cpassword_newstand')[0].value;

  if(name!= null && name!= '') {
  if(email!= null && email!= '' && checkEmail(email)) {
  if(password!= null && password!= '') {
  if(password == cpassword)
  {

 // if(contact.length < 4)
  //{
  //navigator.notification.alert(
                              // alert_enter_phone_no_newsstand,
                              // alertDismissed,
                              // alert_newsstand,
                               //alert_ok_newsstand
                               //);
 // return;
 // }
  //else
  //{

  window.localStorage.setItem("profileName",name);
  window.localStorage.setItem("profileEmail",email);

  var wsUrl ="http://"+window.localStorage.getItem("reseller")+"/services/app-user-soap#appUserRegister/";

  var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><appUserRegister xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/app-user-soap#appUserRegister/\"><appid>'+window.localStorage.getItem("applicationID")+'</appid><name>'+name+'</name><email>'+email+'</email><password>'+password+'</password><phone>'+contact+'</phone></appUserRegister></soap:Body></soap:Envelope>';


  console.log("SOAPSignUp " + soapRequest);
  $.ajax({
         type: "POST",
         url: wsUrl,
         contentType: "text/xml",
         dataType: "text",
         data: soapRequest,
         success: processSuccessxmlupdatesignup_newstand,
         error: processErrorxmlupdatesignup_newstand
         });
 // }


  }
  else {

  navigator.notification.alert(
                               alert_pass_conpass_doesnt_match_newsstand,
                               alertDismissed,
                               alert_newsstand,
                               alert_ok_newsstand
                               );
  }
  }
  else {

  navigator.notification.alert(
                               alert_pass_cant_blank_newsstand,
                               alertDismissed,
                               alert_newsstand,
                               alert_ok_newsstand
                               );
  }
  }
  else {

  navigator.notification.alert(
                               alert_enter_valid_emailid_newsstand,
                               alertDismissed,
                               alert_newsstand,
                               alert_ok_newsstand
                               );
  }
  }
  else {

  navigator.notification.alert(alert_name_field_cant_blank_newsstand,alertDismissed,alert_newsstand,alert_ok_newsstand);

  }
}

function processSuccessxmlupdatesignup_newstand(data, status, req)
{
        var xml = req.responseText;
        xmlDoc = $.parseXML( xml );
        $xml = $( xmlDoc );
        $title = $xml.find( "return" );
        var status=$title.text();

        if(status=="true")
        {
		localStorage.setItem("userLogin","true");
        //modified by mohsin
        localStorage.setItem('fooduserid',window.localStorage.getItem("profileEmail"));
		toaster.setloyalityEmail(localStorage.getItem('fooduserid'));
		//--
		toaster.setUserStatus(status);
		myaccount_newstand();
		//onBackKeyDown('food');
		//history.go(-1);
        navigator.notification.alert(
                                   alert_register_success_newsstand,
                                   alertDismissed,
                                   alert_newsstand,
                                   alert_ok_newsstand
                                   );

        }else if(status=="false")
        {
        navigator.notification.alert(
                                   alert_unable_register_newsstand,
                                   alertDismissed,
                                   alert_newsstand,
                                   alert_ok_newsstand
                                   );
        }
        else{
        navigator.notification.alert(
                                   alert_usr_already_registered_newsstand,
                                   alertDismissed,
                                   alert_newsstand,
                                   alert_ok_newsstand
                                   );
        }

}

function processErrorxmlupdatesignup_newstand(data, status, req) {
//console.log("Error : "+ status.responseText);
}


function getCurrentTimeStamp()
{
    var d = new Date();
    var n=d.getTime();
    return n;
}

function addtoCollection(editionId)
{
    var Heading='';
    var checkFullFile='';
    var Summary='';
    var image='';
    var fileTypeFullPreview='';
    var fileTypeForFullPrev='';
    var checkFullFile='';
    var index=sessionStorage.getItem('newsStandPageIndex');
    $newsStandIndex = $(masterData).find("newsstand[indexval="+index+"]" );
    var finalPageId='';
    var countPageId=0;
    var newsStandPageIndex =$(masterData).find("pageIdentifierBecon").text().split(',');
    for(var w=0;w<newsStandPageIndex.length;w++)
    {
        if(newsStandPageIndex[w].indexOf('news')!=-1)
        {
            if(countPageId == index)
            {
                finalPageId = newsStandPageIndex[w];
                console.log("finalPageId--->"+finalPageId);
            }
            countPageId++
        }


    }
    $newsStandIndex.find('newsstandHeader').each(function()
                                                 {
                                                 var newsstandEditionId=$(this).find('newsstandEditionId').text();
                                                 if(newsstandEditionId == editionId)
                                                 {
                                                 Heading=$(this).find('newsstandSubHeader').text();
                                                 checkFullFile=$(this).find('newsFullFileType').text();
                                                 fileTypeFullPreview=$(this).find('newsFullFile').text();
                                                 fileTypeForFullPrev=$(this).find('newsFullFileTypeGen').text();
                                                 Summary=$(this).find('newsstandSummary').text();
                                                 image=$(this).find('newsstandImage').text();
                                                 }

                                                 });
    var paymentInfo='{"periodType":"forever","currency":"USD","paymentType":"inApp","totalPrice":"","transictionId":"PAY-'+editionId+'"}';

    var productInfo= '[{"editionId":"'+editionId+'","fullFileType":"'+checkFullFile+'","fullFile":"'+fileTypeFullPreview+'","fullFileTypeGen":"'+fileTypeForFullPrev+'","image":"'+image+'","price":"0","language":""}]';


    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/newsstand-soap#createOrderSubscription";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><createOrderSubscription xmlns=\"http://'+localStorage.getItem("reseller")+'/services/newsstand-soap#createOrderSubscription\"><appId>'+localStorage.getItem('applicationID')+'</appId><pageId>'+finalPageId+'</pageId><userEmail>'+localStorage.getItem('fooduserid')+'</userEmail><newsstandJson>'+paymentInfo+'</newsstandJson><subscriptionList>'+productInfo+'</subscriptionList><heading><![CDATA['+Heading+']]></heading><summary><![CDATA['+Summary+']]></summary></createOrderSubscription></soap:Body></soap:Envelope>';
    console.log("soapRequest--->"+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           console.log("response--->"+req.responseText);
           var strJSON=$(req.responseText).find('return').text();
           console.log("str ---> "+strJSON);
           localStorage.setItem('newsStandPaidData'+editionId,''+localStorage.getItem("fooduserid"));
           $('.appypie-loader').hide();
           if(checkFullFile != null && checkFullFile != 'url')
           {
           getFileSystemNewsstand();
            //downloadNewsStandData(fileTypeFullPreview,fileTypeForFullPrev);
            setTimeout(function(){
                     newsstandPagePaid(index);
                      },1000);
           }
           else
           {
           setTimeout(function(){
                     newsstandPagePaid(index);
                      },1000);
           filePreview(fileTypeFullPreview,fileTypeForFullPrev);
           return false;
           }
           },
           error: function(response, textStatus, errorThrown)
           {
           localStorage.setItem('newsStandPaidData'+editionId,''+localStorage.getItem("fooduserid"));
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
}


function timeStampForDate(days)
{
    var value=60*60*24*parseFloat(days);
    return value;
}