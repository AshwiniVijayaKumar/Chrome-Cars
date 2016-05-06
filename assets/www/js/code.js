var masterData;
var iconPath12;
var iconName12;
var ftime=1;
var loginText;
var chkup=0;
var ApplicationXMLpath='';
var documentPath='';
var resrictLogin='true';
var textIndentation='left';
var d = new Date();
var homeFuncSubMenuSlider;
var loginFuncSubMenuSlider;
var accountFuncSubMenuSlider;
var mainmenuFuncSubMenuSlider;
var termsFuncSubMenuSlider;
var policyFuncSubMenuSlider;
var requestedFileSystem;
var fileCount=0;
var imagesforbackchange =new Array();
var totalIconDownloads=0;
var newXmlData;
var fileImages='';
var applockbgchange=0;
var appUpdateIcon="";
/*Sign up dynamic*/
var RegcustomElementsType=[];
var RegcustomElementsLabel=[];
var RegcustomElementsMandatory=[];
var RegcustomElementsValue=[];
var formbuilderPageId;
var requestQuoteJson='';
var RegtotalElements;
var submissionErrorMsg='';
var submissionSuccessMsg='';
localStorage.setItem('dateID',"");
var Regglobalimageurl="";
var RegfileName='';
var globalIndex="";
var flagglobalindex=0;
var currentSelectedId="";
var uploadFileCount=0;
var globalExactPath="";
var primaryColor="";
var secondaryColor="";
var mobileADS= false;
/*sign up dynamic finish*/

/*sign up dynamic language settings */
var signupenternamealert = "Name field cannot be left blank";
var signuppasswords = "Password field cannot be left blank";
var signupemailid = "Please Enter a valid Email";
var signuppasswordshouldbesevenchar ="Password length should be minimum 7 characters.";
var signuppassworddonotmatch ="Password does not match";
var signuppasswordsendmailid = "Password has been sent on your E-mail Id";
var signuppasswordunabletoreset = "Unable to reset your Password";
var registeredsuccessfully ="Registered Successfully";
var registeredsuccessfullycustomforecb1f89b5b67 ="Last step, check your email and follow the instructions sent to you";
var unabletoregister ="Unable to Register !";
var useralreadyregistered ="User Already Registered !";
var emailandpasswordmustnotbeblank ="Email id and password must not be blank.";
var invalidusernameorpassword ="Invalid username or password";
var fromfacebookloginstatus=false;


var ccconnect_code = "Connect ";
var with_code = "With";
var fffacebook_code = " Facebook";
var dddelete_code = "delete"
var bookkkmark_code = "bookmark";
var ookk_code = 'OK';
var ppdfownloadinginprogress_code = 'Pdf Downloading is in progress.'
var nointerbnconnection_code ='NO INTERNET CONNECTION';
var yyoumustbeconnectedinternet_code ='You must be connected to the Internet to use this feature. Check your Internet connection and try again.';
var applicationaunchfailed_code = "Application Launch Failed. Please try again!";
var youhaveupdatesavailableforhisapp_code = "You have updates available for this app";
var uuupdate_code ="Update";
var rremindmeater_code = "Remind me later";
var aalert_code = 'Alert';
var  ssorryhisapptemporarilydisabledpublisher_code = " Sorry, This app has been temporarily disabled by the publisher. ";
var ccontacttheowner_code = "Contact the owner";
var bbandwidthlimitonppunlocked_code = "Bandwidth limit on App has been exceeded. Please contact the App owner for getting the app unlocked. ";
var cchathere_code = "Chat here";
var wecannotaccesshidden_code = 'We can not access your app.It seems all pages in your app are hidden.';
var sselectgender_code = "Select Gender";
var mmale_code = "Male";
var femalee_code = "Female";
var aaattachfile_code = "Attach File";
var nnnotvalidemail_code = "not valid email !";
var ppleaseprovidepaypalemailid_code = 'Please provide paypal email id';
var ppleaseprovidepaymentmount_code= 'Please provide payment amount.';
var ppaymentfor_code = "Payment for ";
var ppleaseprovidepubliceforiap_code = 'Please provide public key for IAP.';
var ffieldcannotbeleftblank_code = ' field cannot be left blank';
var uunabletoegister_code = 'Unable to Register !';
var ccheckyournternetonnectioagain_code = 'Check your Internet connection and try again.';
var vverificationcodesendsuccessfully_code = "Verification code send successfully.";
var vverificationodesendingfailed_code ="Verification code sending failed.";
var ppleasetryagain_code = "Please try again.";
var verificationcodemustnotbeblank_code = "Verification code must not be blank.";
var vverificationcodenotmatched_code = "Verification code not matched.";
var eemailidmustnotbeblank_code = "Email id must not be blank.";
var notvvvalid_code = "not valid";
var ffacebookauthenticateagain_code = 'Facebook do not authenticate you. Try again.';
var pppleasentervalidmail_code = '*Please Enter a valid Email';






/*sign up dynamic language settings */


var navigationSlider = [];

            var headerBarBackgroundColor='#000';
			var headerBarTextColor='#fff';
			var headerBarFont='georgia';
			var headerBarSize='mediumHeaderBar';
			var nav_header_image_name='';
//Function login page for pages
function loginInnerPages()
{



	sessionStorage.setItem('fromInnerPages','true');

	$("#contentHolder2").empty();
	var html='<div class="appypie-login"><h2 id="lgnS"></h2><div class="login-feald"><label id="lgnE"></label><input data-role="none" name="EmailId"  type="email" data-prevent-focus-zoom="false" /></div><div class="login-feald"><label id="lgnP"></label><input data-role="none" name="Password"  type="password" data-prevent-focus-zoom="false" /></div><div class="login-feald" ><a class="login_btn" onclick="Login();" '+primaryColor+' id="lgnB"></a><a onclick="forgotpassword();" id="lgnF"></a></div><div class="fb-div" ></br><a style="display:none" id="lgnfaceb" onclick="fblogin();" class="facebook-connect" href="#">'+ccconnect_code+'<small>'+with_code+'</small>'+fffacebook_code+'</a></div><div style="text-align: center; vertical-align: middle;"> <br clear="all" /><p id="lgnA"></p> <a onclick="signup();" id="lgnSU"></a></div></div>';

	html+='<div data-role="popup" id="popup-outside-page2" class="newsstand-popup-div cart-MSG" data-theme="b"><a id="closePop" onclick="closePopup12();">X</a><h2 id="subscription_title"></h3><div id="monthlyfb" class="sub_plan">USD<span>20</span>/Month</div><div id="yearlyfb" class="sub_plan">USD<span>20</span>/Year</div><div id="onetimefb" class="sub_plan">USD<span>20</span>/Time</div></div>';

	appendHtml(html,25,1);
	//	$("#contentHolder1").append(loginData);
	$('#popup-outside-page2').popup({ positionTo: "window" });
	loginPageDetails();
	checkRegisterOption();


	/* if(localStorage.getItem('layout') != 'bottom' && localStorage.getItem('layout') != 'slidemenu')
    {
        $("#logo").hide();
        $("#mainback").show();
        $("#reload").show();
		Showhideupdatebutton();
    }
	 */

}

function forSinglePageView()
{
	if(localStorage.getItem("layout")=="slidemenu")
	{
		//$("#reload").show();
		Showhideupdatebutton();
		$("#mainback").hide();
	}
	else
	{
		if((sessionStorage.getItem("pageIndex")<4) && localStorage.getItem("layout")=="bottom")
		{
			$("#logo").show();
			$("#mainback").hide();
		}
		else
		{
			$("#logo").hide();
			$("#mainback").show();
		}
	}
}


function normalView()
{
	if(localStorage.getItem("layout")=="slidemenu")
	{
		$("#reload").hide();
		$("#mainback").show();
	}
	else {
		$("#logo").hide();
		$("#mainback").show();
	}
}
// Opens the website page from the content
function openTheWebsite(url)
{
 if(localStorage.getItem('hidebootomforce') == 'true')
    {
        openTheWebsiteOnNewPage(url);
    }
    else
    {
	var pageLevel=parseInt(sessionStorage.getItem('pageLevel'))+1;
	openWebPage(url,pageLevel);
	}
}

//for handling header bar for noheader theme
function headerForInnerPages(htmlPage,pageNo,pageLevel,pageName)
{




	var appName = localStorage.getItem("AppName");

	if(appName.length>25){
		appName=appName.split(0,25)+"...";
	}
	console.log("headerForInnerPages()  pageNo --->"+pageNo+"<---pageLevel---->"+pageLevel+"<----pageName---->"+pageName);
	var html='<div data-role="header" data-position="fixed" data-theme="a" data-tap-toggle="false" class="" id="appyHeader'+pageNo+'">\
			<span class="logo ui-btn-left" id="logo'+pageNo+'" style="display:none"><img src="images/logo.png" /></span>\
			<span class="ui-btn-left main-appypie-back" id="mainback'+pageNo+'" style="display:none"><img src="images/back-btn.png" onclick="onBackKeyDown();"/></span>\
			<span class="ui-btn-right slide-appypie-back" id="mainbackfoodecom'+pageNo+'" style="display:none"><img src="images/back-btn.png" onclick="onBackKeyDown(\'Food\');" style="width:18px"/></span>\
			<span class="ui-btn-left shopingMenu appypie-shopingMenu" id="shopmenu'+pageNo+'" style="display:none"><img src="images/e-com/menu-btn.png" onclick="showEcomMenu();" width="40" height="40"/></span>\
			<h1 id="pageTitle" style="padding-left:30px">'+appName+'</h1>';



			 if(localStorage.getItem("appUpdateIcon"))
                                            {
											  html += '<a href="javascript:;" class="ui-btn ui-shadow ui-corner-all ui-icon-reload ui-btn-icon-notext ui-btn-right appypie-reload" id="reload'+pageNo+'" onclick="chkupd();"><sub class="value" id="supValueChange" style="display:none" ></sub><span class="headerIcon">'+returnIconImageHtml(localStorage.getItem("appUpdateIcon"))+'</span></a>';

                                            }else{
										  html += '<a href="javascript:;" class="ui-btn ui-shadow ui-corner-all ui-icon-reload ui-btn-icon-notext ui-btn-right appypie-reload" id="reload'+pageNo+'" onclick="chkupd();"><sub class="value" id="supValueChange" style="display:none" ></sub><span class="defulatReload">'+dddelete_code+'</span></a>';
  	}


   html +='<a href="javascript:;" class="ui-btn ui-shadow ui-corner-all ui-icon-bookmark ui-btn-icon-notext ui-btn-right " id="bookmark'+pageNo+'"  style="display:block;">'+bookkkmark_code+'</a></div>';
			$("#contentHolder"+pageNo).empty();
			$("#contentHolder"+pageNo).addClass('smallContent');
			$("#contentHolder"+pageNo).append(""+htmlPage);

			$(".red-astrick").attr("style","color:red !important;");
			var pageNewNo=pageNo;
			if(!pageNo)
			{
				pageNewNo=1;
			}

			sessionStorage.setItem('pageLevel',pageLevel);
			sessionStorage.setItem('pageContainer'+pageLevel,(parseInt(pageNewNo)+1));
			var headerValue='false';
			if(localStorage.getItem('layout') != 'slidemenu')
			{
				console.log("pageIndex value --->"+parseFloat(sessionStorage.getItem("pageIndex")));
				if((localStorage.getItem('layout') == 'bottom' && ((parseFloat(sessionStorage.getItem("pageIndex"))>3 || parseInt(pageLevel) > 1) && sessionStorage.getItem("bottomScroll") != 'true')) || (sessionStorage.getItem("bottomScroll") == 'true' && parseInt(pageLevel) > 1)       || localStorage.getItem('layout') != 'bottom' || pageName)
				{
				if(window.localStorage.getItem("applicationID") == "c7928539dd45")
                 {
               //alert(11);
                 }
				 else
				 {
					$( "[data-role='header']" ).remove();
					$( "[data-role='header']" ).toolbar('destroy');
					$(''+html).insertAfter('#page'+(parseInt(pageNewNo)+1));
					$('#page'+(parseInt(pageNewNo)+1)).css('padding-top','45px');  //new changes
					$( "[data-role='header']" ).toolbar();
					$.mobile.resetActivePageHeight();
					headerValue='true';
					}
				}
			}

				    	console.log("application  id not found Abhishek");
				    	Showhideupdatebutton();

			$.mobile.changePage('#page'+(parseInt(pageNewNo)+1), { transition: 'slidefade',allowSamePageTransition: false});

			console.log("headerForInnerPages()  pageNo --->"+pageNo+"<---pageLevel---->"+pageLevel+"<----pageName---->"+pageName);
			if(headerValue == 'true')
			{
				if(!pageName || pageName == 'undefined')
				{
					manageBackForHideHearder(pageNo,pageLevel,null);
				}
				else
				{
					manageBackForHideHearder(pageNo,pageLevel,pageName);
				}
				$('#page'+(parseInt(pageNewNo)+1)).attr("style","");
			}
			else if(localStorage.getItem('layout') != 'slidemenu')
			{
				$( "[data-role='header']" ).remove();
				$( "[data-role='header']" ).toolbar('destroy');
				$.mobile.resetActivePageHeight();
				$('#page'+(parseInt(pageNewNo)+1)).attr("style","padding:0px !important");
			}


			//new gaurav
			var navigationBarType='text';
			if(masterData.getElementsByTagName('navigationBarType')[0])
				navigationBarType=masterData.getElementsByTagName('navigationBarType')[0].firstChild.nodeValue;


			if(masterData.getElementsByTagName('nav_header_image_name')[0])
				nav_header_image_name=masterData.getElementsByTagName('nav_header_image_name')[0].firstChild.nodeValue;

			navigationBarType=navigationBarType.trim();



			if(masterData.getElementsByTagName('headerBarTextColor')[0])
				headerBarTextColor=masterData.getElementsByTagName('headerBarTextColor')[0].firstChild.nodeValue;

			if(masterData.getElementsByTagName('headerBarBackgroundColor')[0])
				headerBarBackgroundColor=masterData.getElementsByTagName('headerBarBackgroundColor')[0].firstChild.nodeValue;

			if(masterData.getElementsByTagName('headerBarFont')[0])
				headerBarFont=masterData.getElementsByTagName('headerBarFont')[0].firstChild.nodeValue;

			if(masterData.getElementsByTagName('headerBarSize')[0])
				headerBarSize=masterData.getElementsByTagName('headerBarSize')[0].firstChild.nodeValue;
             setHeaderImage();
}

function setHeaderImage()
{
     var headerImage='';
     var navigationBarType='';
     if(masterData.getElementsByTagName('nav_header_image_name')[0])
         headerImage=masterData.getElementsByTagName('nav_header_image_name')[0].firstChild.nodeValue;

     if(masterData.getElementsByTagName('navigationBarType')[0])
      navigationBarType=masterData.getElementsByTagName('navigationBarType')[0].firstChild.nodeValue;

    // alert(navigationBarType);
     headerImage=headerImage.trim();
    if(headerImage == '' || navigationBarType=='text' || navigationBarType=='none')
     {

  if(masterData.getElementsByTagName('headerBarTextColor')[0])
    headerBarTextColor=masterData.getElementsByTagName('headerBarTextColor')[0].firstChild.nodeValue;

   if(masterData.getElementsByTagName('headerBarBackgroundColor')[0])
    headerBarBackgroundColor=masterData.getElementsByTagName('headerBarBackgroundColor')[0].firstChild.nodeValue;
  //  alert(headerBarTextColor + "," headerBarBackgroundColor)

         $( "[data-role='header']").attr('style','color:'+headerBarTextColor+';background-color:'+headerBarBackgroundColor+';');
         $( "[data-role='header']").addClass('headerColor');
         //code to add class for header size #starts

		  if(masterData.getElementsByTagName('headerBarSize')[0])
				headerBarSize=masterData.getElementsByTagName('headerBarSize')[0].firstChild.nodeValue;

         $( "#pageTitle").addClass(headerBarSize+' '+gFontsCheck(headerBarFont));
         //code to add class for header size #ends

     }
      else
     {
      var headerImageNew='';
      var chkDeviceRes=toaster.Header_CheckDevice_ScreenResolution();
      console.log("Chal gya sala", "Chal gya sala "+chkDeviceRes );
      headerImageNew=set_headerbackground_image(chkDeviceRes);
      console.log("krishna>>>>>>>>>>>"+headerImageNew);
     if(masterData.getElementsByTagName('headerBarTextColor')[0])
     headerBarTextColor=masterData.getElementsByTagName('headerBarTextColor')[0].firstChild.nodeValue;
     if(masterData.getElementsByTagName('headerBarBackgroundColor')[0])
     headerBarBackgroundColor=masterData.getElementsByTagName('headerBarBackgroundColor')[0].firstChild.nodeValue;

    if($.trim(headerBarBackgroundColor) == "none")
   {
	   headerBarBackgroundColor = "transparent";
   }
    console.log("Header Image is Avilable ");
    $( "[data-role='header']").attr('style','color:'+headerBarTextColor+' ; background-position: center  ; background-size: 100% 100%; background-image:url('+headerImageNew+');background-color:'+headerBarBackgroundColor+' !important;');
    $( "[data-role='header']").addClass('headerImage');
    $('#pageTitle').html(' ');
    $('#logo').remove();

    }
}


// common alert pop up function, used for alerts in every js
function alertPopUp(heading,message)
{

	navigator.notification.alert(
			''+message,
			alertDismissed,
			''+heading,
			ookk_code
			);
	$('.appypie-loader').hide();
}

// common function which return the image html after checking the image url
// pages in which this is used website,donation,event,video,gallery
function returnImageHtml(imageUrl)
{
	var imageHtml='';
	if (imageUrl.indexOf('.') != -1)
	{
		imageHtml ='<img  src="'+ imageUrl+'"  />';
	}
	else
	{
		imageHtml = '<i  class="' + imageUrl + '" style="color:'+sessionStorage.getItem('pageIconColor')+' !important"/></i>';
	}
	console.log("imageHtml --->"+imageHtml);
	return imageHtml;
}


function closeApp()
{
	toaster.exitApp();
}

//backfunction manage for every view
function onBackKeyDown(pageName)
{

   try
   {
   if(sessionStorage.getItem("newsDetailsId")!="")
   {
   sessionStorage.setItem("newsDetailsId","");
   readUnread_news(sessionStorage.getItem("newsDetailsId"));
   return true;
   }
   }
	catch(err)
	{
		console.log(err.message);
	}
    $(".font-popup").fadeOut("slow");

	    var isfromDir= sessionStorage.getItem("isfromDir");

    if(pageName=="directory"||isfromDir=="1")
    {

        setTimeout(function() {
                   $( "[data-role='header']" ).remove();
                   $( "[data-role='header']" ).toolbar('destroy');
                   $.mobile.resetActivePageHeight();

                   },300);
        $('#page2').attr("style","padding:0px !important");
        $('#page3').attr("style","padding:0px !important");
        $('#page4').attr("style","padding:0px !important");
        $('#page5').attr("style","padding:0px !important");
        sessionStorage.setItem("isfromDir",0);
    }

	console.log("pagelevel>>>>"+parseInt(sessionStorage.getItem('pageLevel')));
	if(localStorage.getItem("ecomOpen") == "true" && parseInt(sessionStorage.getItem('pageLevel'))==1)
    {
     homeecom();
     localStorage.setItem("ecomOpen", "false");
	 return true;
    }


	/*
	if(localStorage.getItem("newsOpen") == "true" && parseInt(sessionStorage.getItem('pageLevel'))==1)
    {
	 pageLevel=parseInt(sessionStorage.getItem('pageLevel'))-1;
	 sessionStorage.setItem('pageLevel',pageLevel);
     localStorage.setItem("newsOpen", "false");
     homeNews();
     return true;
    }

	*/
	if(localStorage.getItem("newsOpen") == "true" && parseInt(sessionStorage.getItem('pageLevel'))==2)
    {
	 pageLevel=parseInt(sessionStorage.getItem('pageLevel'))-2;
	 sessionStorage.setItem('pageLevel',pageLevel);
     localStorage.setItem("newsOpen", "false");
	if(localStorage.getItem("layout")=="bottom")
	  {
		 $('.app_navigation_bottom ').attr("style","color: #573d3e;background-color: #f5a0a3;");
		 $("#shopmenu").hide();
		 $("#mainbackfoodecom").hide();
		 $("#logo").show();
		 $("#reload").show();
	  }
     homeNews();
     return true;
    }

	if(localStorage.getItem("directoryOpen") == "true" && parseInt(sessionStorage.getItem('pageLevel'))==1)
    {
     HomeDirectory();
     localStorage.setItem("directoryOpen", "false");
	 return true;
    }


	if(sessionStorage.getItem("ereaderDownload") == "true")
	{


		 navigator.notification.alert(
				 ppdfownloadinginprogress_code,
                 "",
                 'Message',
                 ookk_code
                 );
				 return false;
	}
	if(toaster.SinglePageCheck() == false && PageID[0]=="website")
    {
     window.toaster.exitApp();
    }

	if(parseInt(sessionStorage.getItem('pageLevel')) == 0 )
	{
		window.toaster.exitApp();
	}
	else if(parseInt(sessionStorage.getItem('pageLevel'))== null && localStorage.getItem("layout") != 'list')
	{

		window.toaster.exitApp();
	}
	else if(!sessionStorage.getItem('pageLevel'))
	{

		window.toaster.exitApp();
	}
	else if(localStorage.getItem("layout")=="slidemenu" && sessionStorage.getItem('pageLevel') == 1)
	{
		window.toaster.exitApp();
	}
	else if(localStorage.getItem("layout")=="bottom" && sessionStorage.getItem('pageLevel') == 1 && !sessionStorage.getItem('openPageIndex') )
	{
	 var moreBottomScroll='NO';

            			if(masterData.getElementsByTagName('moreBottomScroll')[0])
            				moreBottomScroll=masterData.getElementsByTagName('moreBottomScroll')[0].firstChild.nodeValue;


            		if(sessionStorage.getItem("pageIndex")<=3 && moreBottomScroll=='NO'){
                    		window.toaster.exitApp();
                    		}else if(moreBottomScroll == 'YES'){
                    		  window.toaster.exitApp();
                    		 }
                    		 else{
                    		openIndexPage();
                    		}


	}
	else{

		window.location="removewebsitewithoutads:";
		var pageLevel=0;
		console.log("onBackKeyDown() starts");
		if(parseInt(sessionStorage.getItem('pageLevel')) == 1 || parseInt(sessionStorage.getItem('pageLevel')) > 1)
		{
			pageLevel=parseInt(sessionStorage.getItem('pageLevel'))-1;
			sessionStorage.setItem('pageLevel',pageLevel);
		}

     if(sessionStorage.getItem('chatRoom') && sessionStorage.getItem('chatRoom') == 'true' && localStorage.getItem("layout")=="bottom")
		{
			$(".app_navigation_bottom").hide();
			$(".app_navigation_bottom_carousel").show();
		}
		else
		{
			$(".app_navigation_bottom").show();
			$(".app_navigation_bottom_carousel").hide();
		}

		if(parseInt(sessionStorage.getItem('pageLevel')) == 1 && localStorage.getItem("layout")=="bottom")
		{
			$(".app_navigation_bottom").show();
		}

		setTimeout(function()
				{
			if(parseInt(sessionStorage.getItem('pageLevel')) < 1)
			{
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



			}
			else
			{
				var backPage=sessionStorage.getItem('pageContainer'+pageLevel);
				console.log("backPage manageBackFunction--->"+backPage);

				if($.mobile.activePage.attr("id") != 'page'+backPage)
				{
                    if(sessionStorage.getItem("setInsideNewsPage") && sessionStorage.getItem("setInsideNewsPage")!="" && backPage==2)
					{
					backPage=1;
					onBackKeyDown();
					return;
					}

					if(backPage==3 && localStorage.getItem("layout")=="slidemenu" && sessionStorage.getItem("setInsideEcommPage") && sessionStorage.getItem("setInsideEcommPage")!="")
					{
					backPage=1;
					onBackKeyDown();
					return;
					}

					$.mobile.changePage('#page'+backPage, {
						transition: 'slidefade',
						allowSamePageTransition: false
					});
				}
			}

				},100);


		if(sessionStorage.getItem('fromFoodPage') && parseInt(sessionStorage.getItem('pageLevel')) < 1)
		{
			sessionStorage.removeItem('fromFoodPage');
			pageName=null;
		}
		if(!pageName)
		{
			if(parseInt(pageLevel) < 1)
			{
				if(localStorage.getItem("layout")!="slidemenu")
				{
					$("#logo").show();
				}
				$("#mainback").hide();
				Showhideupdatebutton();
				$("#mainbackfoodecom").hide();
				$("#shopmenu").hide();
				$("#bookmark").hide();

				// Showhideupdatebutton();;

				if(localStorage.getItem("layout")=="bottom")
				{
					$(".app_navigation_bottom").show();
					$(".app_navigation_bottom_carousel").show();
				}
				if((localStorage.getItem("layout").indexOf("matrix")!=-1 || localStorage.getItem("layout").indexOf("imglist")!=-1 || localStorage.getItem("layout").indexOf("imgmatrix")!=-1) && sessionStorage.getItem('hideHeaderBar')== "true")
				{
					//alert("krishna");
					$.mobile.resetActivePageHeight();
					$('#page1').attr("style","padding-top:0px !important");
				}
			}
			else if(parseInt(pageLevel) == 1)
			{

				if((parseFloat(sessionStorage.getItem("pageIndex"))<4 || sessionStorage.getItem("bottomScroll") == 'true') && localStorage.getItem("layout")=="bottom")
				{
					if(sessionStorage.getItem('hideHeaderBar') && sessionStorage.getItem('hideHeaderBar') == "true")
					{
						$( "[data-role='header']" ).remove();
						$( "[data-role='header']" ).toolbar('destroy');
						$.mobile.resetActivePageHeight();
						$('#page'+backPage).attr("style","padding:0px !important");
					}
					else
					{
						$("#logo").show();
						$("#mainback").hide();
					}
				}
				else if(((PageID.length==5 && sessionStorage.getItem("pageIndex")==4) || sessionStorage.getItem("bottomScroll") == 'true') && localStorage.getItem("layout")=="bottom")
				{

					if(sessionStorage.getItem('hideHeaderBar') && sessionStorage.getItem('hideHeaderBar') == "true")
					{
						$( "[data-role='header']" ).remove();
						$( "[data-role='header']" ).toolbar('destroy');
						$.mobile.resetActivePageHeight();
						$('#page'+backPage).attr("style","padding:0px !important");
					}
					else
					{
						$("#logo").show();
						$("#mainback").hide();
					}
				}
				else if(localStorage.getItem("layout")=="slidemenu")
				{
					$("#mainback").hide();
					//$("#reload").show();
					Showhideupdatebutton();
					snapper.close();
				}
				else
				{
					$("#logo").hide();
					$("#mainback").show();
				}
			}
			else if(parseInt(pageLevel) > 1)
			{
				if(localStorage.getItem("layout")=="slidemenu")
				{
					$("#mainback").show();
					$("#reload").hide();
				}
				else
				{
					$("#logo").hide();
					$("#mainback").show();
				}
			}

		}
		else
		{

			if(parseInt(pageLevel) == 1 || parseInt(pageLevel) < 1)
			{
				$("#logo").hide();
				$("#mainback").hide();
				$("#shopmenu").show();
				$("#mainbackfoodecom").hide();
				//$("#reload").show();
				Showhideupdatebutton();
			}
			else
			{
				$("#shopmenu").show();
				$("#mainbackfoodecom").show();
				$("#logo").hide();
				$("#mainback").hide();
				$("#reload").hide();
			}


			if(sessionStorage.getItem("mcomCheckOut") == 'true' && pageLevel == 4)
			{
				console.log("mcomCheckOut..................");
				if(localStorage.getItem("layout")=="bottom")
				{
					if($(".app_navigation_bottom"))
					{
						$(".app_navigation_bottom").hide();
					}


					$(".app_navigation_bottom_carousel").hide();
					localStorage.setItem("bottomHide","ture");
					sessionStorage.setItem("mcomCheckOut","true");
				}
				else
				{
					$("#contentHolder4 .food-ordering-product").css('padding-bottom','61px');
				}
			}
			else if(localStorage.getItem("layout")=="bottom" && pageLevel != 4)
			{
				console.log("mcomCheckOut2..................");
				$("div[class^=app_navigation_bottom]").show();
				$(".app_navigation_bottom_carousel").show();
				$("#contentHolder4 .food-ordering-product").css('padding-bottom','');
			}
			else if(pageName == 'foodSuccess' || pageName == 'ecomSuccess')
			{
				$("#mainbackfoodecom").hide();
				//$("#reload").show();
				Showhideupdatebutton();
			}
		}
	}

	if(window.localStorage.getItem("layout")=="bottom"  && localStorage.getItem("showheader")=="showheader")
 {
	 window.toaster.exitApp();
 }

}

// common function for appending html, used in many js
function appendHtml(html,pageNo,pageLevel,pageName)
{

	if(sessionStorage.getItem('hideHeaderBar') && sessionStorage.getItem('hideHeaderBar') == "true")
	{
		headerForInnerPages(html,pageNo,pageLevel,pageName);
	}
	else
	{
		manageBackForHideHearder('',pageLevel,pageName);
		if(localStorage.getItem("applicationID") == "496639bafe62")
		{
		$("#contentHolder"+pageNo).css("background","transparent");
		}
		$("#contentHolder"+pageNo).empty();
		console.log("sessionStorage.getItem('hideHeaderBar') ---->"+sessionStorage.getItem('hideHeaderBar'));
		$("#contentHolder"+pageNo).addClass(''+sessionStorage.getItem('appPageCsize'));
		$("#contentHolder"+pageNo).append(""+html);

		if(!pageNo)
		{
			pageNo=1;
		}
		sessionStorage.setItem('pageLevel',pageLevel);
		sessionStorage.setItem('pageContainer'+pageLevel,(parseInt(pageNo)+1));
		$.mobile.changePage('#page'+(parseInt(pageNo)+1), { transition: 'slidefade',allowSamePageTransition: false});
	}
	$(".red-astrick").attr("style","color:red !important;");
	sessionStorage.removeItem("ereaderPage");
	$('.appypie-loader').hide();

}

// manageback button for hide header bar
function manageBackForHideHearder(pageNo,pageLevel,pageName)
{

	console.log("manageBackForHideHearder()  pageNo --->"+pageNo+"<---pageLevel---->"+pageLevel+"<----pageName---->"+pageName);
	if(!pageName)
	{
		if(parseInt(pageLevel) == 1)
		{
			if((parseFloat(sessionStorage.getItem("pageIndex"))<4 || sessionStorage.getItem("bottomScroll") == 'true') && localStorage.getItem("layout")=="bottom")
			{
				$("#logo"+pageNo).show();
				$("#mainback"+pageNo).hide();

			}
			else if(((PageID.length==5 && sessionStorage.getItem("pageIndex")==4) || sessionStorage.getItem("bottomScroll") == 'true') && localStorage.getItem("layout")=="bottom")
			{

				$("#logo"+pageNo).show();
				$("#mainback"+pageNo).hide();
			}
			else if(localStorage.getItem("layout")=="slidemenu")
			{
				$("#mainback"+pageNo).hide();
				//$("#reload"+pageNo).show();
				Showhideupdatebutton();
				// snapper.close();
			}
			else
			{
			 if(PageID.length==1 && PageID[0]=="website")
             {
              $("#logo"+pageNo).show();
              $("#mainback"+pageNo).hide();
             }
			 else
			 {
				$("#logo"+pageNo).hide();
				$("#mainback"+pageNo).show();
			 }
			}

		}
		else if(parseInt(pageLevel) > 1)
		{
			if(localStorage.getItem("layout")=="slidemenu")
			{
				$("#reload"+pageNo).hide();
				$("#mainback"+pageNo).show();
			}
			else
			{
				$("#logo"+pageNo).hide();
				$("#mainback"+pageNo).show();
			}
		}
		if(sessionStorage.getItem("ereaderPage") && sessionStorage.getItem("ereaderPage") == "true")
		{
			$("#bookmark"+pageNo).show();
			$("#reload"+pageNo).hide();
		}
		else
		{
			$("#bookmark"+pageNo).hide();
		}
	}
	else
	{
		if(parseInt(pageLevel) == 1 || parseInt(pageLevel) < 1)
		{
			$("#logo"+pageNo).hide();
			$("#mainback"+pageNo).hide();
			$("#shopmenu"+pageNo).show();
			$("#mainbackfoodecom"+pageNo).hide();
			//$("#reload"+pageNo).show();
			Showhideupdatebutton();
			if(pageName == 'socialAppLogin')
			{

				if((parseFloat(sessionStorage.getItem("pageIndex"))<4 || sessionStorage.getItem("bottomScroll") == 'true') && localStorage.getItem("layout")=="bottom")
				{
					$("#mainback"+pageNo).hide();
					$("#shopmenu"+pageNo).hide();
					$("#logo"+pageNo).show();

				}
				else if(((PageID.length==5 && sessionStorage.getItem("pageIndex")==4) || sessionStorage.getItem("bottomScroll") == 'true') && localStorage.getItem("layout")=="bottom")
				{
					$("#mainback"+pageNo).hide();
					$("#shopmenu"+pageNo).hide();
					$("#logo"+pageNo).show();
				}
				else
				{
				/*
					$("#mainback"+pageNo).show();
					$("#shopmenu"+pageNo).hide();
					$("#logo"+pageNo).hide();
					*/
					  $("#shopmenu"+pageNo).hide();
   $("#mainbackfoodecom"+pageNo).hide();
   $("#logo"+pageNo).show();
   $("#mainback"+pageNo).show();
   $("#reload"+pageNo).hide();

   if(localStorage.getItem("layout")=="slidemenu")
   {
      Showhideupdatebutton();
    $("#reload"+pageNo).hide();
    $("#mainback"+pageNo).hide();
   }
   else
   {

    $("#logo"+pageNo).hide();
    $("#mainback"+pageNo).show();
    Showhideupdatebutton();
   }
				}
			}

		}
		else
		{
		/*
			$("#shopmenu"+pageNo).show();
			$("#mainbackfoodecom"+pageNo).show();
			$("#logo"+pageNo).hide();
			$("#mainback"+pageNo).hide();
			$("#reload"+pageNo).hide();
			*/
		 $("#shopmenu"+pageNo).hide();
      $("#mainbackfoodecom"+pageNo).hide();
      $("#logo"+pageNo).show();
     $("#mainback"+pageNo).show();
       $("#reload"+pageNo).hide();

	   if(localStorage.getItem("layout")=="slidemenu")
   {

    $("#reload"+pageNo).hide();
    $("#mainback"+pageNo).show();
   }
   else
   {
    $("#shopmenu"+pageNo).show();
    $("#logo"+pageNo).hide();
	 $("#mainbackfoodecom"+pageNo).show();
    $("#mainback"+pageNo).hide();
    //Showhideupdatebutton();
   }

		}
	}
}

function setFoodLatLng(lat,lng)
{
	sessionStorage.setItem("foodlatitude",lat);
	sessionStorage.setItem("foodlongitude",lng);
}


function autoUpdateApp(status)
{
	//alert(status);
	localStorage.setItem("autoUpdate",status);
}

function logoutFromApp()
{
	toaster.setUserStatus("");
    toaster.setLoginStatus("");
	toaster.disconnectFromFacebook();
    window.location="removeWebSite:";
	localStorage.setItem("userLoginStatus","false");
	localStorage.setItem("fromfacebookloginstatus","false");
	fromfacebookloginstatus==false;
//	window.localStorage.setItem("FBId","");
	window.localStorage.setItem("fbId","");
	window.localStorage.setItem("fooduserid","");
	window.localStorage.setItem("fbemailId","");
	window.localStorage.setItem("fbuserName","");
	window.localStorage.setItem("SignUp", "");

	setTimeout(function() {
		if(localStorage.getItem("layout")!="slidemenu")
			{
			if(localStorage.getItem("appLanguage") == "sa")
				window.location="file:///android_asset/www/index3.html";
			else
                window.location="file:///android_asset/www/index.html";
			}
			else
			{
				if(localStorage.getItem("appLanguage") == "sa")
				window.location="file:///android_asset/www/index4.html";
			    else
                window.location="file:///android_asset/www/index2.html";
			}
	},1000);
}

function updateUserProfile(userName,userNumber)
{
	window.localStorage.setItem("profileName",userName);
	window.localStorage.setItem("profileNumber",userNumber);
}


function chkupd(){
	$("#reload").addClass("active");
	chkup=1;
	checkServerUpdate();
}

function rateAndShare()
{
	window.location="rateandshare:";
}

//check for network
function checkNetworkConnection(str)
{
	//console.log('checkNetworkConnection_appJS');
	var networkState = navigator.connection.type;
	var states = {};
	states[Connection.UNKNOWN]  = 'Unknown connection';
	states[Connection.ETHERNET] = 'Ethernet connection';
	states[Connection.WIFI]     = 'WiFi connection';
	states[Connection.CELL_2G]  = 'Cell 2G connection';
	states[Connection.CELL_3G]  = 'Cell 3G connection';
	states[Connection.CELL_4G]  = 'Cell 4G connection';
	states[Connection.NONE]     = 'No network connection';
	if(states[networkState]=='No network connection')
	{
		if(!str)//check for network and returns true and false with popup
		{

			alertPopUp(nointerbnconnection_code,yyoumustbeconnectedinternet_code);
		}
		return 0;
	}else //check for network and returns true and false without popup
	{        return 1;
	}
}


function loadData()
{
	console.log("rvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
	console.log("rvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"+window.localStorage.getItem("IconDownload"));
	if(window.localStorage.getItem("IconDownload")!="true" )
	{
		console.log("icons downloading one");


		setTimeout(function() {
			document.addEventListener("deviceready",onDeviceReady_Icon,false);
		},4000);
	}

	try
	{
		setTimeout(function() {
			//console.log("startGCM starts now....."+(new Date().getSeconds())/1000);
			toaster.startGCM();
			/*  setFilesystem();*/
			newCheckServerUpdate();
			sessionStorage.setItem("applicationUpdated","true");
		},10);
	}
	catch(err)
	{
		console.log(err.message);
	}


	setTimeout(function(){
		[
		 'js/firebase.1.0.js',
		 ].forEach(function(src) {
			 var script = document.createElement('script');
			 script.src = src;
			 script.async = false;
			 document.head.appendChild(script);
		 });
	},50);


	//console.log('applicationID--->'+localStorage.getItem("applicationID"));
	if(window.localStorage.getItem("applicationID"))
	{
		console.log('readXMLFromLocalSDCard--->');
		readXMLFromLocalSDCard();
	}
	else
	{
		console.log('readLocalXmlFile--->');
		readLocalXmlFile();
	}
	setDeviceFileSystemAndroid();
}


function setDeviceFileSystemAndroid()
{
	console.log("success file system22 setDeviceFileSystemAndroid");
	document.addEventListener('deviceready', function() {
		window.requestFileSystem  = window.requestFileSystem || window.webkitRequestFileSystem;
		window.requestFileSystem(LocalFileSystem.PERSISTENT, 0, SetFileSystem, fail);
	}, false);
}


function SetFileSystem(fileSystem)
{
	console.log("success file system22 SetFileSystem");
	window.rootFS = fileSystem.root;
	requestedFileSystem=fileSystem;
	var newdocumentPath=fileSystem.root.toURL();
	sessionStorage.setItem('xmlPath',newdocumentPath);

}



function readXMLFromLocalSDCard()
{

	var pathForXMl="file://"+toaster.getInternalStorageFileDir();
	sessionStorage.setItem('xmlPath',pathForXMl);
	setTimeout(function(){
		$.ajax({
			type: 'GET',
			url: pathForXMl,
			processData: true,
			data: {},
			dataType: "text",
			cache:false,
			success: function(txtxml) {
				console.log("txtxml----->"+txtxml);
				console.log("pathForXMl----->"+pathForXMl);
				console.log("txtxml----->"+txtxml);
				window.sessionStorage.setItem("xml",txtxml);
				masterData=$.parseXML(txtxml);

				localStorage.setItem("appLanguageCheck",masterData.getElementsByTagName('lang')[0].firstChild.nodeValue);

				if(masterData.getElementsByTagName('lang')[0].firstChild.nodeValue == "sa")
				{
					//alert("asfsf");
					arabic();
				}
				if(masterData.getElementsByTagName('appMobIdBanner')[0])
				{
					//alert("vsd");
				var tempAdsCheck = masterData.getElementsByTagName('appMobIdBanner')[0].firstChild.nodeValue;
				if(tempAdsCheck.length > 0 && tempAdsCheck != "NULL" && checkNetworkConnection('true'))
				{
				 mobileADS = true;
				}
				}

				if(masterData.getElementsByTagName('airPushAppIdAndroid')[0])
				{
					//alert("vsd");
				var tempAdsCheckForAirpush = masterData.getElementsByTagName('airPushAppIdAndroid')[0].firstChild.nodeValue;
				if(tempAdsCheckForAirpush.trim().length > 0 && tempAdsCheckForAirpush != "NULL" && checkNetworkConnection('true'))
				{
					//alert("fdgdg");

					mobileADS = true;
				}
				}

				setTimeout(function(){
					if(toaster.getUserStatus().length>3)
					{
						console.log("in if condition loaddata() 1");
						checkUserLoginStatus();
					}
					else
					{
						console.log("in if condition loaddata() 3");
						getStarted();
					}
				},10);

			},
			error:function(response, textStatus, errorThrown) {
				console.log("in error condition of pathForXMl"+pathForXMl);
				readLocalXmlFile();
			}
		});
	},100);
}

function checkUserLoginStatus()
{
	if(localStorage.getItem("fromfacebookloginstatus")=="true"){

		 if(toaster.getUserStatus().length>3 && toaster.getLoginStatus() == "true")
 {
  var wsUrl = "http://"+window.localStorage.getItem("reseller")+"/services/app-user-soap#userActiveDeactive";
  var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><userActiveDeactive xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/app-user-soap#userActiveDeactive/\"><appId>'+localStorage.getItem("applicationID")+'</appId><email>'+ window.localStorage.getItem("fooduserid")+'</email></userActiveDeactive></soap:Body></soap:Envelope>';

  //code written by amritesh on 8-01-16

  var autoStatus=localStorage.getItem("emailverification");


  console.log("Amritesh checkUserLoginStatus Saop "+soapRequest);

  jQuery.support.cors = true;
  $.ajax({
   type: "POST",
   url: wsUrl,
   contentType: "text/xml",
   dataType: "text",
   data: soapRequest,
   success: function(data, status, req)
   {
   console.log(" response of --->"+req.responseText);
   var xmlret=$.parseXML(req.responseText);

   var strJSON = $(xmlret).find("return").text();
   if(strJSON == 'false' || strJSON == 'Not Exists')
   {
    resrictLogin='true';
   }
   else
   {
    resrictLogin='false';
   }
   console.log("in if condition loaddata() 2");
   getStarted();

   },
   error: function(response, textStatus, errorThrown)
   {
    console.log(response);
    getStarted();
    resrictLogin='true';
   }
  });
 }
 else
 {
  resrictLogin='true';
  getStarted();
 }

	}else{

		 if(toaster.getUserStatus().length>3 && toaster.getLoginStatus() == "true")
 {


  //code written by amritesh on 8-01-16
  var wsUrl = "http://"+window.localStorage.getItem("reseller")+"/services/app-user-soap#checkUserActiveStatus/";
  //window.localStorage.setItem("fooduserid",email);
  var autoStatus=localStorage.getItem("emailverification");

 //console.log("email : "+window.localStorage.getItem("loginemail"));
 //console.log("password : "+window.localStorage.getItem("loginpassword"));



  var soapRequest =
    '<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><checkUserActiveStatus xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/app-user-soap#checkUserActiveStatus/\"><email>'+window.localStorage.getItem("loginemail")+'</email><password>'+window.localStorage.getItem("loginpassword")+'</password><appId>'+window.localStorage.getItem("applicationID")+'</appId><authoStatus>'+autoStatus+'</authoStatus></checkUserActiveStatus></soap:Body></soap:Envelope>';

  console.log("Amritesh checkUserLoginStatus Saop "+soapRequest);

  jQuery.support.cors = true;
  $.ajax({
   type: "POST",
   url: wsUrl,
   contentType: "text/xml",
   dataType: "text",
   data: soapRequest,
   success: function(data, status, req)
   {
   console.log(" response of --->"+req.responseText);
   var xmlret=$.parseXML(req.responseText);

   var strJSON = $(xmlret).find("return").text();
   if(strJSON != window.localStorage.getItem("loginemail").toLowerCase())
   {
    resrictLogin='true';
   }
   else
   {
    resrictLogin='false';
   }
   console.log("in if condition loaddata() 2");
   getStarted();

   },
   error: function(response, textStatus, errorThrown)
   {
    console.log(response);
    getStarted();
    resrictLogin='true';
   }
  });
 }
 else
 {
  resrictLogin='true';
  getStarted();
 }

	}


}

//reads xml from the local path and is called when app is launched for the 1 st time
function readLocalXmlFile(){
localStorage.setItem("fromfacebookloginstatus","false");
	$.ajax({
		type: 'GET',
		url: "appypie.xml",
		processData: true,
		data: {},
		dataType: "text",
		cache:false,
		success: function(txtxml) {
			window.sessionStorage.setItem("xml",txtxml);
			masterData=$.parseXML(txtxml);
			localStorage.setItem("appLanguageCheck",masterData.getElementsByTagName('lang')[0].firstChild.nodeValue);
			window.localStorage.setItem("layout",masterData.getElementsByTagName('layout')[0].firstChild.nodeValue);

			if(masterData.getElementsByTagName('appMobIdBanner')[0])
				{
					//alert("vsd");
				var tempAdsCheck = masterData.getElementsByTagName('appMobIdBanner')[0].firstChild.nodeValue;
				if(tempAdsCheck.length > 0 && tempAdsCheck != "NULL" && checkNetworkConnection('true'))
				{
					//alert("fdgdg");

					mobileADS = true;
				}
				}

			if(masterData.getElementsByTagName('airPushAppIdAndroid')[0])
				{
					//alert("vsd");
				var tempAdsCheckForAirpush = masterData.getElementsByTagName('airPushAppIdAndroid')[0].firstChild.nodeValue;
				if(tempAdsCheckForAirpush.trim().length > 0 && tempAdsCheckForAirpush != "NULL" && checkNetworkConnection('true'))
				{
					//alert("fdgdg");

					mobileADS = true;
				}
				}


			if(masterData.getElementsByTagName('navigationSlider')[0])
		   {
				window.localStorage.setItem("navigationSlider",masterData.getElementsByTagName('navigationSlider')[0].firstChild.nodeValue);
		   }
		   if(masterData.getElementsByTagName('layoutHeader')[0])
		   {
				window.localStorage.setItem("layoutHeader",masterData.getElementsByTagName('layoutHeader')[0].firstChild.nodeValue);
		   }

			window.localStorage.setItem("appVersion",masterData.getElementsByTagName('version')[0].firstChild.nodeValue);
			window.localStorage.setItem("appVersionnew",masterData.getElementsByTagName('version')[0].firstChild.nodeValue);
			window.localStorage.setItem("applicationID",masterData.getElementsByTagName('appId')[0].firstChild.nodeValue);

			localStorage.setItem("applicationPackage","com.apps.appypie"+masterData.getElementsByTagName('appId')[0].firstChild.nodeValue);

			if(masterData.getElementsByTagName('reseller')[0].firstChild.nodeValue==="angularml.pbodev.info")
			{
			window.localStorage.setItem("reseller",masterData.getElementsByTagName('reseller')[0].firstChild.nodeValue);
			}
			else
			{
			window.localStorage.setItem("reseller",masterData.getElementsByTagName('servernameappy')[0].firstChild.nodeValue+"."+masterData.getElementsByTagName('reseller')[0].firstChild.nodeValue);
			}

			//Start change by dheeraj
			if(masterData.getElementsByTagName('owneremail')[0])
		    {
		        owneremail=masterData.getElementsByTagName('owneremail')[0].firstChild.nodeValue;
		        localStorage.setItem("owneremail",owneremail);
		    }

		    if(masterData.getElementsByTagName('ownerName')[0])
		    {
		      var ownerName=masterData.getElementsByTagName('ownerName')[0].firstChild.nodeValue;
		        localStorage.setItem("ownerName",ownerName);
		    }
		    if(masterData.getElementsByTagName('resellerId')[0])
		    {
		        if(masterData.getElementsByTagName('resellerId')[0].firstChild)
		        {
		        var resellerId=masterData.getElementsByTagName('resellerId')[0].firstChild.nodeValue;
		        localStorage.setItem("resellerId",resellerId);
		        }
		        else{
		         localStorage.setItem("resellerId",0);
		        }

		    }
			//End change by dheeraj
			if(masterData.getElementsByTagName('appIcon')[0])
	        {
		    var owneremail=masterData.getElementsByTagName('appIcon')[0].firstChild.nodeValue;
			localStorage.setItem("appIcon",owneremail);
	        }

			if(masterData.getElementsByTagName('headerBarTitle')[0])
			{
				localStorage.setItem("AppName",masterData.getElementsByTagName('headerBarTitle')[0].firstChild.nodeValue);
				localStorage.setItem("ApplicationName",masterData.getElementsByTagName('appName')[0].firstChild.nodeValue);
			}
			else
			{
				localStorage.setItem("AppName",masterData.getElementsByTagName('appName')[0].firstChild.nodeValue);
				localStorage.setItem("ApplicationName",masterData.getElementsByTagName('appName')[0].firstChild.nodeValue);
			}

			//window.localStorage.setItem("AppName",masterData.getElementsByTagName('appName')[0].firstChild.nodeValue);
			if(masterData.getElementsByTagName('piwikId')[0]){
				window.localStorage.setItem("piwikId",masterData.getElementsByTagName('piwikId')[0].firstChild.nodeValue);
			}
			if(masterData.getElementsByTagName('appShareMessage')[0])
			{
				window.localStorage.setItem("appShareMessage",masterData.getElementsByTagName('appShareMessage')[0].firstChild.nodeValue);
			}

			window.localStorage.setItem("iconName",masterData.getElementsByTagName('iconName')[0].firstChild.nodeValue);
			window.localStorage.setItem("iconPath",masterData.getElementsByTagName('iconPath')[0].firstChild.nodeValue);
			window.localStorage.setItem("iconPath2","images/appicon");
			iconPath12=masterData.getElementsByTagName('iconPath')[0].firstChild.nodeValue;
			iconName12=masterData.getElementsByTagName('iconName')[0].firstChild.nodeValue;

			/*
           if(masterData.getElementsByTagName('autoUpdate')[0])
           {
           var updateBtn=masterData.getElementsByTagName('autoUpdate')[0].firstChild.nodeValue;
           if(updateBtn == 'On')
           {
           console.log('updateBtn---->'+updateBtn);
           localStorage.setItem('autoUpdate','true');
           console.log('going in loaddata');
           setTimeout(function(){
                      loadData();
                      },100);
           return false;
           }
           else
           {
           console.log('updateBtn---->'+updateBtn);
           localStorage.setItem('autoUpdate','false');
           //sessionStorage.setItem('appAutoUpdated','false');
           }
           }
			 */

			getStarted();
		},
		error:function(response, textStatus, errorThrown) {


			$("#page-text").append(applicationaunchfailed_code);
			backgroundCalling();
		}
	});
}
function showConfirm() {
	toaster.updateApplicationXML(sessionStorage.getItem("updatedXML"));
	setTimeout(function()
			{
		$('.appypie-loader').show();
		window.location="removewebsitewithoutads:";
		readUpdatedXml(sessionStorage.getItem("updatedXML"));
			},100);
}


//newCheckServerUpdate() checks for update and auto update
function newCheckServerUpdate(update)
{
	var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/angular-soap#autoUpdateAppXml";
	var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><autoUpdateAppXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/angular-soap#autoUpdateAppXml/\"><appId>'+localStorage.getItem("applicationID")+'</appId><prevVersion>'+localStorage.getItem("appVersion")+'</prevVersion></autoUpdateAppXml></soap:Body></soap:Envelope>';
	console.log("Saop "+soapRequest);
	jQuery.support.cors = true;
	$.ajax({
		type: "POST",
		url: wsUrl,
		contentType: "text/xml",
		dataType: "text",
		data: soapRequest,
		success: function(data, status, req)
		{
		var xmlret=$.parseXML(req.responseText);

		var strJSON = $(xmlret).find("return").text();

		console.log("kkkstrJSON---->"+strJSON);
		var count=toaster.returnNotificationCount();
		//alert("code"+count);
		if(strJSON=="No update available" && count == 0)
		{
			$('#supValueChange').hide();
		}
		else{
			var urlOfXml=strJSON.split('??');
			if(urlOfXml[0]!=null && urlOfXml[0].length > 4)
			{
				sessionStorage.setItem("updatedXML",urlOfXml[0]);
			}

			if(!localStorage.getItem('autoUpdate'))
			{
				localStorage.setItem('autoUpdate',urlOfXml[1]);
			}


			if(update == 'checkUpdate' || (urlOfXml[1] == 'true' && localStorage.getItem('autoUpdate') == 'true') || (urlOfXml[1] == 'false' && localStorage.getItem('autoUpdate') == 'true'))
			{
				console.log('app is autoupdating');
				console.log("check the variables -->"+update+"<---urlOfXml[1]---->"+urlOfXml[1]+"<----localStorage.getItem('autoUpdate')--->"+localStorage.getItem('autoUpdate'));

				showConfirm();
			}

			if(count != 0)
			{

				$('#supValueChange').empty();
				//$('#supValueChange').show();
				Showhideupdatebutton();
				$('#supValueChange').append(toaster.returnNotificationCount());

			}
			else if(localStorage.getItem('autoUpdate') != 'true')
			{
				$('#supValueChange').empty();
				//$('#supValueChange').show();
				Showhideupdatebutton();
				$('#supValueChange').append('1');
			}
			else
			{
				$('#supValueChange').hide();
			}

		}
		},
		error: function(response, textStatus, errorThrown)
		{
			console.log(response);


			//getStarted();
		}
	});
}



function confirmDialog(text, callback) {


	var popupDialogId = 'popupDialog';
	$('<div data-role="popup" class="radius" id="' + popupDialogId + '" data-confirmed="no" data-transition="pop" data-overlay-theme="z" data-theme="z" data-dismissible="false" style="max-width:500px;"> \
			<div role="main" class="ui-content">\
			<h3 class="ui-title">'+youhaveupdatesavailableforhisapp_code+'</h3>\
			<a onclick="showConfirm();" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-z optionConfirm" data-rel="back">'+uuupdate_code+'</a>\
			<a href="#" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-z optionCancel" data-rel="back" data-transition="flow">'+rremindmeater_code+'</a>\
			</div>\
			</div>')
			.appendTo($.mobile.pageContainer);
					var popupDialogObj = $('#' + popupDialogId);
					popupDialogObj.trigger('create');
					popupDialogObj.popup({
						afterclose: function (event, ui) {
						popupDialogObj.find(".optionConfirm").first().off('click');
						var isConfirmed = popupDialogObj.attr('data-confirmed') === 'yes' ? true : false;
						$(event.target).remove();
						if (isConfirmed && callback) {

							//readUpdatedXml(sessionStorage.getItem("updatedXML"));
						}
					}
					});
					popupDialogObj.popup('open');
					popupDialogObj.find(".optionConfirm").first().on('click', function () {
						popupDialogObj.attr('data-confirmed', 'yes');
					});
}

function readUpdatedXmlFromSdCard(pathForXMl)
{
	$.ajax({
		type: 'GET',
		url: pathForXMl,
		processData: true,
		data: {},
		dataType: "text",
		cache:false,
		success: function(xml) {
			//alert('success '+strJSON);
			masterData=jQuery.parseXML(xml);
			// updateXmlOnApp();
			sessionStorage.clear();
			sessionStorage.setItem("xml",xml);

			window.localStorage.setItem("layout",masterData.getElementsByTagName('layout')[0].firstChild.nodeValue);

			if(masterData.getElementsByTagName('navigationSlider')[0])
		   {
				window.localStorage.setItem("navigationSlider",masterData.getElementsByTagName('navigationSlider')[0].firstChild.nodeValue);
		   }
		   if(masterData.getElementsByTagName('layoutHeader')[0])
		   {
				window.localStorage.setItem("layoutHeader",masterData.getElementsByTagName('layoutHeader')[0].firstChild.nodeValue);
		   }

			window.localStorage.setItem("appVersion",masterData.getElementsByTagName('version')[0].firstChild.nodeValue);
			window.localStorage.setItem("applicationID",masterData.getElementsByTagName('appId')[0].firstChild.nodeValue);

			if(masterData.getElementsByTagName('reseller')[0].firstChild.nodeValue==="angularml.pbodev.info")
			{
			window.localStorage.setItem("reseller",masterData.getElementsByTagName('reseller')[0].firstChild.nodeValue);
			}
			else
			{
			window.localStorage.setItem("reseller",masterData.getElementsByTagName('servernameappy')[0].firstChild.nodeValue+"."+masterData.getElementsByTagName('reseller')[0].firstChild.nodeValue);
			}

			if(masterData.getElementsByTagName('headerBarTitle')[0])
			{
				localStorage.setItem("AppName",masterData.getElementsByTagName('headerBarTitle')[0].firstChild.nodeValue);
			}
			else
			{
				localStorage.setItem("AppName",masterData.getElementsByTagName('appName')[0].firstChild.nodeValue);
			}
			// window.localStorage.setItem("AppName",masterData.getElementsByTagName('appName')[0].firstChild.nodeValue);
			window.localStorage.setItem("iconName",masterData.getElementsByTagName('iconName')[0].firstChild.nodeValue);
			window.localStorage.setItem("iconPath",masterData.getElementsByTagName('iconPath')[0].firstChild.nodeValue);

			if(masterData.getElementsByTagName('piwikId')[0]){
				window.localStorage.setItem("piwikId",masterData.getElementsByTagName('piwikId')[0].firstChild.nodeValue);
			}

			if(masterData.getElementsByTagName('appShareMessage')[0])
			{
				window.localStorage.setItem("appShareMessage",masterData.getElementsByTagName('appShareMessage')[0].firstChild.nodeValue);
			}

			iconPath12=masterData.getElementsByTagName('iconPath')[0].firstChild.nodeValue;
			iconName12=masterData.getElementsByTagName('iconName')[0].firstChild.nodeValue;

			//localStorage.setItem("userLoginStatus","false");
			if(localStorage.getItem("layout")!="slidemenu")
			{
			if(localStorage.getItem("appLanguage") == "sa")
				window.location="file:///android_asset/www/index3.html";
			else
                window.location="file:///android_asset/www/index.html";
			}
			else
			{
				if(localStorage.getItem("appLanguage") == "sa")
				window.location="file:///android_asset/www/index4.html";
			    else
                window.location="file:///android_asset/www/index2.html";
			}


		},
		error:function(response, textStatus, errorThrown) {

			readUpdatedXml(sessionStorage.getItem("updatedXML"));
		}
	});

}

function readUpdatedXml(strJSON){
	fileCount=0;
	console.log('readUpdatedXml');
	// alert(strJSON);
	masterData='';
	$.ajax({
		url: strJSON,
		dataType: "text",
		crossDomain: true,
		cache: false,
		success: function(xml) {
		//alert('success '+strJSON);
		masterData=jQuery.parseXML(xml);
		// updateXmlOnApp();
		sessionStorage.clear();
		sessionStorage.setItem("xml",xml);

		window.localStorage.setItem("layout",masterData.getElementsByTagName('layout')[0].firstChild.nodeValue);
		if(masterData.getElementsByTagName('navigationSlider')[0])
		   {
				window.localStorage.setItem("navigationSlider",masterData.getElementsByTagName('navigationSlider')[0].firstChild.nodeValue);
		   }
		   if(masterData.getElementsByTagName('layoutHeader')[0])
		   {
				window.localStorage.setItem("layoutHeader",masterData.getElementsByTagName('layoutHeader')[0].firstChild.nodeValue);
		   }

		window.localStorage.setItem("appVersion",masterData.getElementsByTagName('version')[0].firstChild.nodeValue);
		window.localStorage.setItem("applicationID",masterData.getElementsByTagName('appId')[0].firstChild.nodeValue);

		if(masterData.getElementsByTagName('reseller')[0].firstChild.nodeValue==="angularml.pbodev.info")
			{
			window.localStorage.setItem("reseller",masterData.getElementsByTagName('reseller')[0].firstChild.nodeValue);
			}
			else
			{
			window.localStorage.setItem("reseller",masterData.getElementsByTagName('servernameappy')[0].firstChild.nodeValue+"."+masterData.getElementsByTagName('reseller')[0].firstChild.nodeValue);
			}

		if(masterData.getElementsByTagName('headerBarTitle')[0])
		{
			localStorage.setItem("AppName",masterData.getElementsByTagName('headerBarTitle')[0].firstChild.nodeValue);
		}
		else
		{
			localStorage.setItem("AppName",masterData.getElementsByTagName('appName')[0].firstChild.nodeValue);
		}
		// window.localStorage.setItem("AppName",masterData.getElementsByTagName('appName')[0].firstChild.nodeValue);
		window.localStorage.setItem("iconName",masterData.getElementsByTagName('iconName')[0].firstChild.nodeValue);
		window.localStorage.setItem("iconPath",masterData.getElementsByTagName('iconPath')[0].firstChild.nodeValue);

		if(masterData.getElementsByTagName('piwikId')[0]){
			window.localStorage.setItem("piwikId",masterData.getElementsByTagName('piwikId')[0].firstChild.nodeValue);
		}

		if(masterData.getElementsByTagName('appShareMessage')[0])
		{
			window.localStorage.setItem("appShareMessage",masterData.getElementsByTagName('appShareMessage')[0].firstChild.nodeValue);
		}

		iconPath12=masterData.getElementsByTagName('iconPath')[0].firstChild.nodeValue;
		iconName12=masterData.getElementsByTagName('iconName')[0].firstChild.nodeValue;

		//localStorage.setItem("userLoginStatus","false");
		if(localStorage.getItem("layout")!="slidemenu")
			{
			if(localStorage.getItem("appLanguage") == "sa")
				window.location="file:///android_asset/www/index3.html";
			else
                window.location="file:///android_asset/www/index.html";
			}
			else
			{
				if(localStorage.getItem("appLanguage") == "sa")
				window.location="file:///android_asset/www/index4.html";
			    else
                window.location="file:///android_asset/www/index2.html";
			}

	},
	error: function(XMLHttpRequest, textStatus, errorThrown) {

		navigator.notification.alert(
				applicationaunchfailed_code,
				alertDismissed,
				aalert_code,
				ookk_code
				);
		//getStarted();

	}
	});
}



function updateLocalXML(){
	console.log('updateLocalXML');
	// alert(window.sessionStorage.getItem("xml"));
	console.log("newxml?>>>>>>>>>>"+sessionStorage.getItem("xml"));
	/* if( localStorage.getItem("layout")!="slidemenu")
      {
        window.location="file:///android_asset/www/index.html";
    }
    else {
        window.location="file:///android_asset/www/index2.html";
    }*/







}


function getStarted()
{
	console.log("Read XMl");

	//start code change by amritesh on 02-02-16
	  if(localStorage.getItem('layout') == 'slidemenu' && localStorage.getItem('hidebootomforce') == 'true')
      {

      $('#mainmenu').empty();
      $('#submenunav').empty();
      var htmlHeader='<div data-role="header" data-position="fixed" data-theme="a" data-tap-toggle="false" data-snap-ignore="true"><span class="ui-btn-left appypie-slide" ><a onclick="checkSliderPosition(); " href="#" id="open-left"></a></span><h1 id="pageTitle" style="padding-left:30px"></h1><span class="ui-btn-right slide-appypie-back" id="mainback" style="display:none"><img src="images/back-btn.png" onclick="onBackKeyDown();"/></span><span class="ui-btn-right slide-appypie-back" id="mainbackfoodecom" style="display:none"><img src="images/back-btn.png" onclick="onBackKeyDown("Food");"/></span><a onclick="chkupd();" class="ui-btn ui-shadow ui-corner-all ui-icon-reload ui-btn-icon-notext ui-btn-right appypie-reload" id="reload"><sub class="value" id="supValueChange">0</sub><span class="headerIcon" id="slideheaderIcon"></span></a><a href="javascript:;" class="ui-btn ui-shadow ui-corner-all ui-icon-bookmark ui-btn-icon-notext ui-btn-right appypie-reload" id="bookmark" onclick="bookmark();" style="display:none;"><span>'+bookkkmark_code+'</span></a></div>'
      $("#pgbody").append(htmlHeader);
      $( "[data-role='header']" ).toolbar();
      $.mobile.resetActivePageHeight();
      if(localStorage.getItem("appUpdateIcon"))
      {
      var slideappUpdateIcon = returnIconImageHtml(localStorage.getItem("appUpdateIcon"));
      $("#slideheaderIcon").html(slideappUpdateIcon);
      }else{
      $("#slideheaderIcon").removeClass("headerIcon").addClass("defulatReload").html("reload");
      }

    }
	localStorage.setItem("hidebootomforce",'false');
	//end code change by amritesh on 02-02-16

	var appLockValue=masterData.getElementsByTagName('appLock')[0].firstChild.nodeValue;
	var appBandwidthExceeded='false';
	if(masterData.getElementsByTagName('appBandwidthExceeded')[0])
	{
		appBandwidthExceeded=masterData.getElementsByTagName('appBandwidthExceeded')[0].firstChild.nodeValue;
	}

	var applockValueForDevice=masterData.getElementsByTagName('blockDeviceType')[0].firstChild.nodeValue.split(',');

	if(masterData.getElementsByTagName('blockDeviceType')[0] || masterData.getElementsByTagName('appBandwidthExceeded')[0])
	{
		var applockValueForDevice=masterData.getElementsByTagName('blockDeviceType')[0].firstChild.nodeValue.split(',');
		if (applockValueForDevice.indexOf('Android') !=-1 ||applockValueForDevice=='Android' || appBandwidthExceeded=='true')
		{
			lockApp();
		}
		else
		{
			console.log("Else of Start Now");
			startNow();
		}
	}
	else
	{
		if(appLockValue=="none")
		{
			startNow();
		}
		else
		{
			lockApp();
		}

	}

}


// For displaying lock screen
function lockApp()
{


	$('.appypie-loader').hide();
	$("#logo").show();
	$("#reload").hide();
	if(localStorage.getItem("layout")=="slidemenu"){
		$(".ui-btn-left").hide();
		snapper.disable();
	}
	var owneremail='sales@appypie.com';

	if(masterData.getElementsByTagName('owneremail')[0])
	{
		owneremail=masterData.getElementsByTagName('owneremail')[0].firstChild.nodeValue;
	}
	var appBandwidthExceeded='';
	if(masterData.getElementsByTagName('appBandwidthExceeded')[0])
	{
		appBandwidthExceeded=masterData.getElementsByTagName('appBandwidthExceeded')[0].firstChild.nodeValue;
	}
	var applockValueForDevice=masterData.getElementsByTagName('blockDeviceType')[0].firstChild.nodeValue.split(',');
	if (applockValueForDevice.indexOf('Android') != -1 || applockValueForDevice=='Android')
	{
		$('#pgbody').empty();
		$('#pgbody').append('<section class="white-box-position"><div class="white-box-bg">'+ssorryhisapptemporarilydisabledpublisher_code+'</div><a onclick="emailLockFeature(\''+owneremail+'\')" class="contact-btn ui-btn" data-role="none">'+ccontacttheowner_code+'</a></section>');
	}
	else
	{
		$('#pgbody').empty();
		//  $('#pgbody').attr('style','')
		$('#pgbody').append('<section class="white-box-position"><div class="white-box-bg">'+bbandwidthlimitonppunlocked_code+'</div><a onclick="emailLockFeature(\''+owneremail+'\')" class="contact-btn ui-btn" data-role="none">'+ccontacttheowner_code+'</a></section>');
	}

	applockbgchange=1;
	if (orientation == 0) {
	if(window.localStorage.getItem("reseller").indexOf("snappy.")!=-1)
		document.getElementsByTagName('html')[0].style.backgroundImage='url(images/applock/portrait960.png)';
	else
	    document.getElementsByTagName('html')[0].style.backgroundImage='url(images/applock/portraitreseller960.png)';
	}
	else if (orientation == 90) {
	if(window.localStorage.getItem("reseller").indexOf("snappy.")!=-1)
		document.getElementsByTagName('html')[0].style.backgroundImage='url(images/applock/landscape768.png)';
	else
	    document.getElementsByTagName('html')[0].style.backgroundImage='url(images/applock/landscapereseller768.png)';
	}
	else if (orientation == -90) {
	if(window.localStorage.getItem("reseller").indexOf("snappy.")!=-1)
		document.getElementsByTagName('html')[0].style.backgroundImage='url(images/applock/landscape768.png)';
	else
	   document.getElementsByTagName('html')[0].style.backgroundImage='url(images/applock/landscapereseller768.png)';
	}
	else if (orientation == 180) {
	if(window.localStorage.getItem("reseller").indexOf("snappy.")!=-1)
		document.getElementsByTagName('html')[0].style.backgroundImage='url(images/applock/portrait960.png)';
	else
	    document.getElementsByTagName('html')[0].style.backgroundImage='url(images/applock/portraitreseller960.png)';
	}
	if($(".snap-content").hasClass("portrait-content"))
	{
		$(".snap-content").removeClass('portrait-content').addClass('portrait-lock');
	}
	else
	{
		$(".snap-content").removeClass('landscape-content').addClass('landscape-lock');
	}

	newCheckServerUpdate('checkUpdate');
}

function emailLockFeature(subtext)
{
	window.location="showmailto:"+subtext+"";
}

var tncvisibilestatus;
function startNow()
{
    tncvisibilestatus= localStorage.getItem("tncvisibilestatus");
    if(tncvisibilestatus == "1" || !masterData.getElementsByTagName('Termcondition')[0])
    {
	console.log("start now starts after....."+(new Date().getSeconds())/1000);
	var xmldata=masterData;
	$('.appypie-loader').hide();
	console.log('Success');


	iconPath=xmldata.getElementsByTagName('iconPath')[0].firstChild.nodeValue;
	PageID=xmldata.getElementsByTagName('pageid')[0].firstChild.nodeValue.split(',');
	pageIcons=xmldata.getElementsByTagName('iconName')[0].firstChild.nodeValue.split(',');
	PageName=xmldata.getElementsByTagName('pageNewid')[0].firstChild.nodeValue.split(',');
	PageIndex=xmldata.getElementsByTagName('pageNewid')[0].firstChild.nodeValue.split(',');
	apptheme=xmldata.getElementsByTagName('appTheme')[0].firstChild.nodeValue.split(',');

	$("#pgbody").addClass(""+masterData.getElementsByTagName('appTheme')[0].firstChild.nodeValue);


	if(masterData.getElementsByTagName('login')[0])
	{
		loginText=masterData.getElementsByTagName('login')[0].firstChild.nodeValue;
		console.log("loginText ---1-->>"+loginText);
	}
	else if($(masterData).find('home').find('autoLogin').text())
	{
		loginText=$(masterData).find('home').find('autoLogin').text();
		if(loginText=="false")
		{
			console.log("krishna true");
			localStorage.setItem("userLoginStatus","true");
			//toaster.setLoginStatus("true");
		}

		console.log("loginText ---1-->>"+loginText);
	}
	else
	{
		localStorage.setItem("userLoginStatus","true");
		toaster.setLoginStatus("true");
		loginText="";
	}


	if(loginText != "true" ||toaster.getLoginStatus()=="true")
	{
		// alert(localStorage.getItem("layout"));
		if(localStorage.getItem("layout")!="slidemenu")
		{
			var moreBottomScroll='No';
			if(masterData.getElementsByTagName('moreBottomScroll')[0])
				moreBottomScroll=masterData.getElementsByTagName('moreBottomScroll')[0].firstChild.nodeValue;



			if(localStorage.getItem("layout")=="bottom" && moreBottomScroll != 'YES')
			{


				$('html').find('script').each(function(){
					console.log("src --->"+$(this).attr('src'));

					var srcText=$(this).attr('src');
					if(srcText == 'js/appyPie.carousel.js')
					{
						$(this).attr('src','');
						return false;
					}

				});

				$('html').find('link').each(function(){
					console.log("src --->"+$(this).attr('href'));

					var srcText=$(this).attr('href');
					if(srcText == 'css/appypie_headerhide_bottom.css')
					{
						$(this).attr('href','');
						return false;
					}

				});
				$("#pgbody").append('<div data-role="footer" data-position="fixed" class="app_navigation_bottom" data-tap-toggle="false"></div>');
				$( "body > [data-role='footer']" ).toolbar();
				$('#iconDiv').remove();
			}
			else if(localStorage.getItem("layout")=="bottom" && moreBottomScroll == 'YES')
			{
				// alert("rv2");
				//the below variable is declared for managing the back button and header for bottom scroller
				sessionStorage.setItem("bottomScroll",'true');

				$("#pgbody").append('<div data-role="footer" data-position="fixed" class="app_navigation_bottom_carousel" data-tap-toggle="false"> <div  id="sync2" class="owl-carousel"> </div> </div>');
				$( "body > [data-role='footer']" ).toolbar();
				$('#iconDiv').remove();
			}
			else if(localStorage.getItem("layout").indexOf('matrix') != -1 && (localStorage.getItem("layout") != "imgmatrix"))
			{
				var layoutHeader = localStorage.getItem('layoutHeader');
		  if((layoutHeader == "YES"))
		  {
				$("#contentHolder1").append('<div  id="app_navigation_matrix"></div>');
				$("#app_navigation_matrix").attr("class", 'app_navigation_'+localStorage.getItem("layout"));
				$('#iconDiv').remove();

				createHomeHeader();

				/*
				resetDoc();
				setTimeout(resetDoc(), 100);

				$(window).on( "resize", function( event ) {
					resetDoc();
					setTimeout(resetDoc(), 50);
				});
				*/

		  }
		  else
		  {
				$('html').find('script').each(function(){
					var srcText=$(this).attr('src');
					if(srcText == 'js/appyPie.carousel.js')
					{
						$(this).attr('src','js/matrix_carousel.js');
						return false;
					}
				});

				$('html').find('link').each(function(){
					var srcText=$(this).attr('href');
					if(srcText == 'css/appypie_headerhide_bottom.css')
					{
						$(this).attr('href','css/matrix-list.css');
						return false;
					}
				});
		  }
			}
			else
			{
				$("#contentHolder1").append('<div  id="app_navigation_matrix"></div>');
				$("#app_navigation_matrix").attr("class", 'app_navigation_'+localStorage.getItem("layout"));
				$('#iconDiv').remove();
				if(localStorage.getItem("layout") == "imgmatrix")
                {
				 /*
                 resetDoc();
                 setTimeout(resetDoc(), 100);
                 setTimeout(resetDoc(), 100);
                 $(window).on( "resize", function( event ) {
                 resetDoc();
                 setTimeout(resetDoc(), 50);
                 });
				 */
              }

			}
		}


	}
	count=0;
	var counter;

try
	{
		if(localStorage.getItem("layout") == "bottom"){
  $('html').find('link').each(function(){
     var srcText=$(this).attr('href');
     if(srcText == '')
     {
      $(this).attr('href','css/matrix-list.css');
      return false;
     }
    });
 }
	}
	catch(err)
	{
		console.log(err.message);
	}


	jQuery.each(PageID, function() {

		counter=0;
		if(count>0)
		{
			for (var y=0; y<count; y++)
			{
				if(PageID[y]==PageID[count])
					counter++;

			}
		}
		else
		{
			PageIndex[0]=0;
		}

		PageIndex[count]=counter;
		count++;

	});

	getIndexData();

	}
	else
	{
	//localStorage.setItem("tncvisibilestatus","1");
    termsAndConditions();
	}
}
function backgroundCalling()
{
    var appBackground='images/splash-portrait.png';
    var appIpadBackground='images/splash-lanscape.png';
    if(masterData.getElementsByTagName('appBackground')[0])
        appBackground=masterData.getElementsByTagName('appBackground')[0].firstChild.nodeValue;

    if(masterData.getElementsByTagName('appIpadBackground')[0])
        appIpadBackground=masterData.getElementsByTagName('appIpadBackground')[0].firstChild.nodeValue;

    if(appIpadBackground.trim() != '')
    {
        sessionStorage.setItem("appIpadBackground",appIpadBackground);
    }
    else
    {
        sessionStorage.setItem("appIpadBackground",appBackground);
    }
    sessionStorage.setItem("appBackground",appBackground);

    // we append on #content for slide menu, below code is used to do the same
    var contentNameToAppend='body';
    if(masterData.getElementsByTagName('layout')[0].firstChild.nodeValue == 'slidemenu')
    {
        contentNameToAppend='#pgbody';
    }

    if(appBackground.indexOf('.') != -1 && appBackground.indexOf(',') == -1)
    {
        var mainbackgroundtype='';
        if(masterData.getElementsByTagName('backgroundType')[0])
        {
            mainbackgroundtype=masterData.getElementsByTagName('backgroundType')[0].firstChild.nodeValue;
        }
        if(mainbackgroundtype=='custom_image')
        {
            if(masterData.getElementsByTagName('customBackgroundImage')[0])
            {
                $customBackgroundImagetag=masterData.getElementsByTagName('customBackgroundImage')[0];
                var screenHeight=$('.ui-mobile').outerHeight(true);
                var screenWidth=$('.ui-mobile').outerWidth(true);


            }
        }

        console.log("in app background");
        var newcontentNameToAppend='html';
        if(masterData.getElementsByTagName('layout')[0].firstChild.nodeValue == 'slidemenu')
        {
            newcontentNameToAppend='#content';
            handleOrientation();
        }

        $(''+newcontentNameToAppend).css('background-image','url(' + sessionStorage.getItem("appBackground") + ')');


        //backgroundType var is declared for handling the oreintation method
        sessionStorage.setItem("backgroundType",'image');
        console.log("appBackground image -->"+appBackground);
    }
    else if(appBackground.indexOf(',') != -1 && appBackground.indexOf('.') == -1)
    {
        var gradientTop='#990000';
        var gradientBottom='#000000';

        appBackground=appBackground.split(',');
        gradientTop=appBackground[0];
        gradientBottom=appBackground[1];

        var gradientCSS='<style>'+contentNameToAppend+'{ background: -moz-linear-gradient(top, '+gradientTop+' 0%, '+gradientBottom+' 100%) !important; background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, '+gradientTop+'), color-stop(100%, '+gradientBottom+'))!important;background: -webkit-linear-gradient(top, '+gradientTop+' 0%, '+gradientBottom+' 100%)!important;background: -o-linear-gradient(top, '+gradientTop+' 0%, '+gradientBottom+' 100%)!important; background: -ms-linear-gradient(top, '+gradientTop+' 0%, '+gradientBottom+' 100%)!important;}</style>';
        $(""+contentNameToAppend).append(gradientCSS);
        console.log("appBackground gradientCSS --->"+gradientCSS);
        sessionStorage.removeItem("backgroundType");
    }
    else if(appBackground.indexOf(',') != -1 && appBackground.indexOf('.') != -1)
    {
        imagesforbackchange = appBackground.split(',');
        var forbackchange = 1;

        if(localStorage.getItem("layout") != 'slidemenu')
        {
            $('html').addClass('animated-bg');
        }
        else
        {
            $('#animatedBg').addClass('animated-bg');
        }

        setInterval(function(){
                    $('.animated-bg').animate('fast', function() {
                                              $('.animated-bg').css('background-image', function() {
                                                                    if (forbackchange >= imagesforbackchange.length) {
                                                                    forbackchange=0;
                                                                    }
                                                                    return 'url(' + imagesforbackchange[forbackchange++] + ')';
                                                                    });
                                              });

                    }, 5000);
        $('.animated-bg').css('background-image', 'url('+imagesforbackchange[0]+')');
        sessionStorage.setItem("backgroundType",'annimation');
    }
    else
    {
        $(""+contentNameToAppend).attr('style','background-color:'+appBackground+' !important;');
        sessionStorage.removeItem("backgroundType");
    }

}
function termsAndConditions()
{
  //showAndHideAdView("hide");
  backgroundCalling();
  if(localStorage.getItem("layout")=="slidemenu"){
		snapper.disable();
	}

	 $("#chatZopim").hide();
  if(masterData.getElementsByTagName('headerBarBackgroundColor')[0])
    {
        var hBarBackgroundColor=masterData.getElementsByTagName('headerBarBackgroundColor')[0].firstChild.nodeValue;
    }
    if(masterData.getElementsByTagName('primaryButtonBgColor')[0])
    {
        var primaryButtonColor=masterData.getElementsByTagName('primaryButtonBgColor')[0].firstChild.nodeValue;
    }
    if(masterData.getElementsByTagName('secondaryButtonBgColor')[0])
    {
        var secondaryButtonColor=masterData.getElementsByTagName('secondaryButtonBgColor')[0].firstChild.nodeValue;
    }
    if(masterData.getElementsByTagName('buttonTextColor')[0])
    {
        var buttonTxtColor=masterData.getElementsByTagName('buttonTextColor')[0].firstChild.nodeValue;
    }
    if(masterData.getElementsByTagName('appPageHsize')[0])
    {
        var appPgeHsize=masterData.getElementsByTagName('appPageHsize')[0].firstChild.nodeValue;
    }
    if(masterData.getElementsByTagName('appPageShsize')[0])
    {
        var appPgeShsize=masterData.getElementsByTagName('appPageShsize')[0].firstChild.nodeValue;
    }
    if(masterData.getElementsByTagName('appPageCsize')[0])
    {
        var appPgeCsize=masterData.getElementsByTagName('appPageCsize')[0].firstChild.nodeValue;
    }
    if(masterData.getElementsByTagName('heading')[0])
    {
        var heding=masterData.getElementsByTagName('heading')[0].firstChild.nodeValue;
    }
    if(masterData.getElementsByTagName('acceptButton')[0])
    {
        var acptButton=masterData.getElementsByTagName('acceptButton')[0].firstChild.nodeValue;
    }
    var data;
    $data=$(masterData).find( "Termcondition[indexval=0]");
   var temscond=($data.find("content").text());
    if($(masterData).find( "Termcondition[indexval=0]"))
    {
     $('.appypie-loader').hide();
        var bgHeight= $("body").height();
       var html=' <div id="contentHolderTNC" class="page-content" style="height:'+bgHeight+'px"> <div class="terms-text"><h2 style="background:'+hBarBackgroundColor+'; color:'+buttonTxtColor+'" class="'+appPgeHsize+'">'+heding+'</h2><p class="contnet-code '+appPgeCsize+'">'+temscond+'</p><div  onClick="startNowAfterAccept();" style="background:'+secondaryButtonColor+'; color:'+buttonTxtColor+';" class="termsFooter '+appPgeShsize+'" >'+acptButton+'</div></div></div>';
        $("#contentHolder1").empty();

        if(masterData.getElementsByTagName('layout')[0].firstChild.nodeValue == 'slidemenu')
        {
            tempcontentdata=$("#content").html();
            $("#content").html(html);
        }
        else
        {
            $("#contentHolder1").html(html);
        }

    }
    //localStorage.setItem("tncvisibilestatus","1");
	setTimeout(function(){
	toaster.closeAdsLayout();
	},1000);
}

function startNowAfterAccept()
{
 if(localStorage.getItem("layout")=="slidemenu"){
		snapper.enable();
	}
 $("#contentHolderTNC").hide();
 localStorage.setItem("tncvisibilestatus","1");
 if(localStorage.getItem("layout") == 'slidemenu')
    {
        $("#content").html(tempcontentdata);
    }
    else
    {
    $("#contentHolder1").empty();
    }
    setTimeout(function(){
                $('.appypie-loader').show();
                //loadData();
				if(localStorage.getItem("layout") == 'slidemenu')
               {
                  window.location="file:///android_asset/www/index2.html";
               }
			   else
			   {
				startNow();
			   }
               },100);


 }

function updateXML(flagValue){
	if(flagValue==true){
		//showConfirm();
	}
}

//set Header bar text with color or image
function setHeaderBarColorOrImage(){
  console.log("starts->setHeaderBarColorOrImage function");
  var navigationBarType='text';
  if(masterData.getElementsByTagName('navigationBarType')[0])
   // alert('hjfj-->?'+masterData.getElementsByTagName('navigationBarType')[0].firstChild.nodeValue);

   navigationBarType=masterData.getElementsByTagName('navigationBarType')[0].firstChild.nodeValue;
  // alert(navigationBarType);
  sessionStorage.setItem("navigationBarType",navigationBarType);
  console.log("navigationBarType")
  if(navigationBarType != 'none' && localStorage.getItem('layout') != 'slidemenu')
  {
	 $( "[data-role='header']" ).remove();
     var htmlHeader='<div data-role="header" data-position="fixed" data-theme="a" data-tap-toggle="false" class="" id="appyHeader">\
     <span class="logo ui-btn-left" id="logo" ><img src="images/logo.png" /></span>\
     <span class="ui-btn-left main-appypie-back" id="mainback" style="display:none"><img src="images/back-btn.png" onclick="onBackKeyDown();"/></span>\
     <span class="ui-btn-right slide-appypie-back" id="mainbackfoodecom" style="display:none"><img src="images/back-btn.png" onclick="onBackKeyDown(\'Food\');"/></span>\
     <span class="ui-btn-left shopingMenu appypie-shopingMenu" id="shopmenu" style="display:none"><img src="images/e-com/menu-btn.png" onclick="showEcomMenu();" width="40" height="40"/></span>\
     <h1 id="pageTitle">'+localStorage.getItem("AppName")+'</h1>';


     if(localStorage.getItem("appUpdateIcon"))
                                            {
				htmlHeader += '<a onclick="chkupd();" class="ui-btn ui-shadow ui-corner-all ui-icon-reload ui-btn-icon-notext ui-btn-right appypie-reload" id="reload"><sub class="value" id="supValueChange" style="display:none"></sub><span class="headerIcon">'+returnIconImageHtml(localStorage.getItem("appUpdateIcon"))+'</span></a>';
                }else{
				htmlHeader += '<a onclick="chkupd();" class="ui-btn ui-shadow ui-corner-all ui-icon-reload ui-btn-icon-notext ui-btn-right appypie-reload" id="reload"><sub class="value" id="supValueChange" style="display:none"></sub><span class="defulatReload">'+dddelete_code+'</span></a>';
			}
           htmlHeader +='<a href="javascript:;" class="ui-btn ui-shadow ui-corner-all ui-icon-bookmark ui-btn-icon-notext ui-btn-right appypie-reload" id="bookmark" onclick="bookmark();" style="display:none;"><span>'+bookkkmark_code+'</span></a></div>';
     $(''+htmlHeader).insertAfter('#page1');
     $('#page1').css('padding-top','45px'); //new changes
     $( "[data-role='header']" ).toolbar();
     $.mobile.resetActivePageHeight();
     //$('#page1').attr('style','');
    Showhideupdatebutton();
  }

 if(localStorage.getItem('layout') === 'slidemenu')
   {
    if(masterData.getElementsByTagName('navIconColor')[0])
     var navIconColor=masterData.getElementsByTagName('navIconColor')[0].firstChild.nodeValue;

	localStorage.setItem("navIconColor",navIconColor);

	if(localStorage.getItem("appUpdateIcon"))
	{
		var slideappUpdateIcon = returnIconImageHtmlforslid(localStorage.getItem("appUpdateIcon"),navIconColor);
		$("#slideheaderIcon").html(slideappUpdateIcon);
    }
    else
    {
             //alert("02"+slideappUpdateIcon);
             $("#slideheaderIcon").removeClass("headerIcon").addClass("defulatReload").html("reload");
    }
   }

		var headerBarBackgroundColor='#000';
		var headerBarTextColor='#fff';
		var headerBarFont='georgia';
		var headerBarSize='mediumHeaderBar';
		var nav_header_image_name='';
		if(masterData.getElementsByTagName('nav_header_image_name')[0])
			nav_header_image_name=masterData.getElementsByTagName('nav_header_image_name')[0].firstChild.nodeValue;

		console.log("navigationBarType --->"+navigationBarType);
		navigationBarType=navigationBarType.trim();


		if(masterData.getElementsByTagName('headerBarTextColor')[0])
			headerBarTextColor=masterData.getElementsByTagName('headerBarTextColor')[0].firstChild.nodeValue;

		if(masterData.getElementsByTagName('headerBarBackgroundColor')[0])
			headerBarBackgroundColor=masterData.getElementsByTagName('headerBarBackgroundColor')[0].firstChild.nodeValue;

		if(masterData.getElementsByTagName('headerBarFont')[0])
			headerBarFont=masterData.getElementsByTagName('headerBarFont')[0].firstChild.nodeValue;

		if(masterData.getElementsByTagName('headerBarSize')[0])
			headerBarSize=masterData.getElementsByTagName('headerBarSize')[0].firstChild.nodeValue;


		console.log("headerBarBackgroundColor--->"+headerBarBackgroundColor);
		if(navigationBarType == 'text')
		{
			console.log("headerBarBackgroundColor--->"+headerBarBackgroundColor);
			sessionStorage.setItem('headerBarStyleCss','color:'+headerBarTextColor+';background-color:'+headerBarBackgroundColor+';');
			sessionStorage.setItem('headerBarClasses',headerBarSize+' '+headerBarFont+' headerColor');

			$( "[data-role='header']").attr('style','color:'+headerBarTextColor+';background-color:'+headerBarBackgroundColor+';');
			$( "[data-role='header']").addClass('headerColor');
			//code to add class for header size #starts
			$( "#pageTitle").addClass(gFontsCheck(headerBarFont)+' '+headerBarSize);
			//code to add class for header size #ends
			console.log("this is the header class-->"+$("[data-role='header']").attr('class'));
		}
		else if(navigationBarType == 'image')
		{
			var chkDeviceRes=toaster.Header_CheckDevice_ScreenResolution();
			var headerImage=set_headerbackground_image(chkDeviceRes);
			sessionStorage.setItem('headerBarStyleCss','color:'+headerBarTextColor+';background-image:url('+headerImage+');background-color:'+headerBarBackgroundColor+' !important;');
			sessionStorage.setItem('headerBarClasses','headerImage');
			  if($.trim(headerBarBackgroundColor) == "none")
			  {
				   headerBarBackgroundColor = "transparent";
			  }
			$( "[data-role='header']").attr('style','color:'+headerBarTextColor+'; background-position: center  ; background-size: 100% 100% ; background-image:url('+headerImage+');background-color:'+headerBarBackgroundColor+' !important;');
			$( "[data-role='header']").addClass('headerImage');
			$('#pageTitle').html(' ');
			$('#logo').remove();
		}
		else if(navigationBarType == 'none')
		{
			console.log("navigationBarType --->"+navigationBarType);
			sessionStorage.setItem('hideHeaderBar','true');
			$('#page1').attr('style','padding:0px !important');
		}
}

var contentNameToAppend;
//set app background as a color or image
function setAppBackgroundColorOrImage()
{


	   var backgroundType_image_color=HeadeImageUrl=$(masterData).find("backgroundType").text();
        sessionStorage.setItem("backgroundType_image_color",backgroundType_image_color);

		if(backgroundType_image_color=="custom_color")
		{
			$("body").css("background", $(masterData).find("appBackground").text());
            $("#animatedBg").css("background", $(masterData).find("appBackground").text());
		}

		else
		{

		console.log("starts->setAppBackgroundColorOrImage function");
		var chkDevice_screen_Resolutaion=toaster.CheckDevice_Screen_AppBackground();
		var appBackground=	Setbackground_Img(chkDevice_screen_Resolutaion);
		console.log("Abhishek"+ "Abhishek "+appBackground );
		sessionStorage.setItem("appBackground",appBackground);
		contentNameToAppend='body';
		if(localStorage.getItem('layout') == 'slidemenu')
		{
			contentNameToAppend='#content';
		}

		if(appBackground.indexOf('.') != -1 && appBackground.indexOf(',') == -1)
		{
			console.log("in app background");
			var newcontentNameToAppend='html';
			if(localStorage.getItem('layout') == 'slidemenu')
			{
				newcontentNameToAppend='#content,body';
			}

			$(''+newcontentNameToAppend).css('background-image','url(' + appBackground + ')');
			sessionStorage.setItem("backgroundType",'image');
			console.log("appBackground image -->"+appBackground);
		}
		else if(appBackground.indexOf(',') != -1 && appBackground.indexOf('.') == -1)
		{
			var gradientTop='#990000';
			var gradientBottom='#000000';

			appBackground=appBackground.split(',');
			gradientTop=appBackground[0];
			gradientBottom=appBackground[1];

			var gradientCSS='<style>'+contentNameToAppend+'{ background: -moz-linear-gradient(top, '+gradientTop+' 0%, '+gradientBottom+' 100%) !important; background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, '+gradientTop+'), color-stop(100%, '+gradientBottom+'))!important;background: -webkit-linear-gradient(top, '+gradientTop+' 0%, '+gradientBottom+' 100%)!important;background: -o-linear-gradient(top, '+gradientTop+' 0%, '+gradientBottom+' 100%)!important; background: -ms-linear-gradient(top, '+gradientTop+' 0%, '+gradientBottom+' 100%)!important;}</style>';
			$(""+contentNameToAppend).append(gradientCSS);
			console.log("appBackground gradientCSS --->"+gradientCSS);
			sessionStorage.removeItem("backgroundType");
		}
		else if(appBackground.indexOf(',') != -1 && appBackground.indexOf('.') != -1)
		{
			imagesforbackchange = appBackground.split(',');
			var forbackchange = 1;

			if(localStorage.getItem("layout") != 'slidemenu')
			{
				$('html').addClass('animated-bg');
			}
			else
			{
				$('#animatedBg').addClass('animated-bg');
			}

			setInterval(function(){
				$('.animated-bg').animate('fast', function() {
					$('.animated-bg').css('background-image', function() {
						if (forbackchange >= imagesforbackchange.length) {
							forbackchange=0;
						}
						return 'url(' + imagesforbackchange[forbackchange++] + ')';
					});
				});

			}, 5000);
			$('.animated-bg').css('background-image', 'url('+imagesforbackchange[0]+')');
			sessionStorage.setItem("backgroundType",'annimation');
		}
		else
		{
			$(""+contentNameToAppend).attr('style','background-color:'+appBackground+' !important; background-image: url(file:///android_asset/www/custom_color);');
			sessionStorage.removeItem("backgroundType");
		}
		}
}

var appbeaconidentifierarray;
function getIndexData()
{
	if(masterData.getElementsByTagName('lang')[0].firstChild.nodeValue == "sa")
	{

		arabic();
	}

	 localStorage.setItem("appUpdateIcon",masterData.getElementsByTagName('appUpdateIcon')[0].firstChild.nodeValue);

	var appName = localStorage.getItem("AppName");

	if(appName.length>25){
		appName=appName.split(0,25)+"...";
	}
	if(localStorage.getItem('layout') == 'slidemenu')
	{
		$("#pageTitle").empty();
		$("#pageTitle").append(appName);

		if(mobileADS)
		{
			//alert(mobileADS);
			$(".snap-drawers").addClass("ads")
		}
	}

	xmldata=masterData;
	$xml = $(xmldata);

	try
	{
	if(sessionStorage.getItem("backfromMenu")=="" || sessionStorage.getItem("backfromMenu")==null)
					   {
					getLocalCords();
	                   }
	if(masterData.getElementsByTagName('zopimChatCode')[0])
  {
  if(masterData.getElementsByTagName('zopimChatCode')[0].firstChild.nodeValue.trim()!=""){
  sessionStorage.setItem("zopimchat",masterData.getElementsByTagName('zopimChatCode')[0].firstChild.nodeValue.trim());
  var htmlChat='<input type="button" value="X" src="https://www.britishorienteering.org.uk/images/close.gif" onmouseup="this.parentNode.style.display=\'none\';" />&nbsp;<b><i class="pageIcon iconz-chat"></i></b><a onclick="openTheWebsiteOnNewPage(\'https://v2.zopim.com/widget/livechat.html?key='+masterData.getElementsByTagName('zopimChatCode')[0].firstChild.nodeValue+'&mid=YLfCST0225Y9pp&lang=en&hostname=&api_calls=%5B%5D\');">'+cchathere_code+'</a>';
 //$("#chatZopim").addClass('float');
  $("#chatZopim").html(""+htmlChat);
  $("#chatZopim").show();

    if(localStorage.getItem('layout')== 'bottom')
    {
		$("#chatZopim").addClass("zopimbottom");
    }

	 if(mobileADS)
    {
    	$("#chatZopim").addClass("zopimbottomwithads");
    }
  }
  else
  {
	$("#chatZopim").hide();
  }
  }
  else
  {
	$("#chatZopim").hide();
  }
	}
	catch(err)
	{
		console.log(err.message);
	}
	/* if(masterData.getElementsByTagName('nav_header_image_name')[0])
      {
      var headerImage=masterData.getElementsByTagName('nav_header_image_name')[0].firstChild.nodeValue;
      $.mobile.resetActivePageHeight();
      console.log('headerImage--->'+headerImage);
      //   $('#pageTitle').attr('src',headerImage);
      }*/
	//var chkDeviceRes=toaster.Header_CheckDevice_ScreenResolution();
	//var headerImage=set_headerbackground_image(chkDeviceRes);
	//console.log("Chal gya sala", "Chal gya sala "+chkDeviceRes +" , " +headerImage);
     //deepak
	 if($(masterData).find("lang").text()=="sa")
	{
	localStorage.setItem("appLanguage",$(masterData).find("lang").text());
	$("body").addClass("sa");
	//alert("done1");
	}


	var appbeacon= $xml.find("appybeacon");
	var appbeaconidentifier= $xml.find("pageIdentifierBecon").text();
	appbeaconidentifierarray = appbeaconidentifier.split(",");

	var beaconlength= $(appbeacon).find("couponBeacon").length;
	var beaconCount=1;
	var beaconInfo="";
	if(localStorage.getItem("fromNotification")=='null') {
		$(appbeacon).find('couponBeacon').each(function()
				{
			var uuid = $(this).find("uuid").text();
			var major = $(this).find("major").text();
			var minor = $(this).find("minor").text();
			var status=$(this).find("status").text();
			var message=$(this).find("message").text();
			var notifyTime=$(this).find("notifyTime").text();
			//alert("notifyTime  "+notifyTime);
			if(notifyTime)
			{
				beaconInfo += uuid+"@@"+major+"@@"+minor+"@@"+status+"@@"+message+"@@"+notifyTime+",";
			}
			else
			{
				beaconInfo += uuid+"@@"+major+"@@"+minor+"@@"+status+"@@"+message+",";
			}

			if(beaconCount==beaconlength) {
				setTimeout(function() {
					//window.location ="beaconcheck:"+beaconInfo;
					toaster.holdBeaconData("beaconcheck:"+beaconInfo);
				},2000);
			}
			beaconCount++;
				});
	}
	$("#logo").show();
	$("#mainback").hide();
	//$("#reload").show();
	Showhideupdatebutton();
	$("#bookmark").hide();
	var x=0;
	var counter=0;
	if(localStorage.getItem("layout")=='bottom' && PageID.length>5)
	{
		counter=4;
	}
	else
	{
		counter=PageID.length;
	}
	$("#app_navigation_matrix").empty();

	var imagepath='';

	if((loginText == "true") && resrictLogin == "true") {
		$("#reload").hide();
		$("#logo").show();
		if(window.localStorage.getItem("layout")=="slidemenu"){
			$(".ui-btn-left").hide();
			snapper.disable();
		}
		 $("#chatZopim").hide();
//		$("#contentHolder1").append('<div class="appypie-login"><h2 id="lgnS"></h2><div class="login-feald"><label id="lgnE"></label><input data-role="none" name="EmailId"  type="text" /></div><div class="login-feald"><label id="lgnP"></label><input data-role="none" name="Password"  type="password" /></div><div class="login-feald" ><a class="login_btn" onclick="Login();" id="lgnB"></a><a onclick="forgotpassword();" id="lgnF"></a></div><div class="fb-div" style="display:none;"></br><a class="facebook-connect" href="#">Connect <small>With</small> Facebook</a> <br clear="all" /><p id="lgnA"></p> <a onclick="signup();" id="lgnSU"></a></div></div>');

	//	var loginData='<div class="appypie-login"><h2 id="lgnS"></h2><div class="login-feald"><label id="lgnE"></label><input data-role="none" name="EmailId"  type="text" /></div><div class="login-feald"><label id="lgnP"></label><input data-role="none" name="Password"  type="password" /></div><div class="login-feald" ><a class="login_btn" onclick="Login();" id="lgnB"></a><a onclick="forgotpassword();" id="lgnF"></a></div><div class="fb-div" ></br><a class="facebook-connect" href="#">Connect <small>With</small> Facebook</a> <br clear="all" /><p id="lgnA"></p><div><a onclick="signup();" id="lgnSU"></a></div><img style="text-align: center; vertical-align: middle; display:none" id="lgnfb" onclick="fblogin();" src="images/fb_login.png"></div></div>';


		//var loginData='<div class="appypie-login"><h2 id="lgnS"></h2><div class="login-feald"><label id="lgnE"></label><input data-role="none" name="EmailId"  type="text" /></div><div class="login-feald"><label id="lgnP"></label><input data-role="none" name="Password"  type="password" /></div><div class="login-feald" ><a class="login_btn" onclick="Login();" id="lgnB"></a><a onclick="forgotpassword();" id="lgnF"></a></div><div class="fb-div" ></br><a display:none" id="lgnfb" onclick="fblogin();" class="facebook-connect" href="#">Connect <small>With</small> Facebook</a> <br clear="all" /><p id="lgnA"></p></div><div style="text-align: center; vertical-align: middle;"><a onclick="signup();" id="lgnSU"></a></div></div>';
		var loginData='<div class="appypie-login"><h2 id="lgnS"></h2><div class="login-feald"><label id="lgnE"></label><input data-role="none" name="EmailId"  type="text" /></div><div class="login-feald"><label id="lgnP"></label><input data-role="none" name="Password"  type="password" /></div><div class="login-feald" ><a class="login_btn" onclick="Login();" id="lgnB"></a><a onclick="forgotpassword();" id="lgnF"></a></div><div id="signupdiv"><div class="fb-div" ></br><a display:none" id="lgnfb" onclick="fblogin();" class="facebook-connect" href="#">'+ccconnect_code+'<small>'+with_code+'</small>'+fffacebook_code+'</a> <br clear="all" /><p id="lgnA"></p></div><div style="text-align: center; vertical-align: middle;"><a onclick="signup();" id="lgnSU"></a></div></div></div>';
		loginData+='<div data-role="popup" id="popup-outside-page2" class="newsstand-popup-div cart-MSG" data-theme="b"><a id="closePop" onclick="closePopup12();">X</a><h2 id="subscription_title"></h3><div id="monthlyfb" class="sub_plan">USD<span>20</span>/Month</div><div id="yearlyfb" class="sub_plan">USD<span>20</span>/Year</div><div id="onetimefb" class="sub_plan">USD<span>20</span>/Time</div></div>';

		$("#contentHolder1").append(loginData);
		$('#popup-outside-page2').popup({ positionTo: "window" });
		loginPageDetails();
		checkRegisterOption();
		//setTimeout(function(){ $('div.ui-loader').hide();},2000);
		localStorage.setItem("appNameForLogin",appName);

		setTimeout(function() {
			//set header and app background for autologin condition
			setHeaderBarColorOrImage();
			setAppBackgroundColorOrImage();
			$("#reload").hide();
		},300);
	}
	else{
		//page's header & content variables, stores in sessionstorage

		var pageIndentation='left';
		var appPageHsize='mediumHeading';
		var appPageShsize='mediumSubHeading';
		var appPageCsize='mediumContent';

		//text indent
		if(masterData.getElementsByTagName('indentContent')[0])
			textIndentation=masterData.getElementsByTagName('indentContent')[0].firstChild.nodeValue;

		// header alignment
		if(masterData.getElementsByTagName('pageIndentation')[0])
			pageIndentation=masterData.getElementsByTagName('pageIndentation')[0].firstChild.nodeValue;

		//header text size
		if(masterData.getElementsByTagName('appPageHsize')[0])
			appPageHsize=masterData.getElementsByTagName('appPageHsize')[0].firstChild.nodeValue;

		//sub heading text size
		if(masterData.getElementsByTagName('appPageShsize')[0])
			appPageShsize=masterData.getElementsByTagName('appPageShsize')[0].firstChild.nodeValue;

		//content text size
		if(masterData.getElementsByTagName('appPageCsize')[0])
			appPageCsize=masterData.getElementsByTagName('appPageCsize')[0].firstChild.nodeValue;

		sessionStorage.setItem('pageIndentation',pageIndentation);
		sessionStorage.setItem('appPageHsize',appPageHsize);
		sessionStorage.setItem('appPageShsize',appPageShsize);
		sessionStorage.setItem('appPageCsize',appPageCsize);
		//ends


		// start of header code
		setHeaderBarColorOrImage();
		//end of header code

		//navigation Bar size and font starts
		var navigationFont='comicSansMS';
		if(masterData.getElementsByTagName('navigationFont')[0])
			navigationFont=masterData.getElementsByTagName('navigationFont')[0].firstChild.nodeValue;

		var navigationSize='mediumNav';
		if(masterData.getElementsByTagName('navigationSize')[0])
			navigationSize=masterData.getElementsByTagName('navigationSize')[0].firstChild.nodeValue;


		//Dynamic App background Img  8-Oct-2015
		setAppBackgroundColorOrImage();


		//page text color code starts

		var pageTextColor='#fff';
		var appPageFont='georgia';
		var pageIconColor='#fff';
		//var primaryButtonBgColor="";
		// var secondaryButtonBgColor="";
		var  buttonTextColor="";

		if(masterData.getElementsByTagName('pageTextColor')[0])
			pageTextColor=masterData.getElementsByTagName('pageTextColor')[0].firstChild.nodeValue;

		if(masterData.getElementsByTagName('pageIconColor')[0])
			pageIconColor=masterData.getElementsByTagName('pageIconColor')[0].firstChild.nodeValue;

		if(masterData.getElementsByTagName('appPageFont')[0])
			appPageFont=masterData.getElementsByTagName('appPageFont')[0].firstChild.nodeValue;


		if(masterData.getElementsByTagName('buttonTextColor')[0])
		{
			buttonTextColor=masterData.getElementsByTagName('buttonTextColor')[0].firstChild.nodeValue;
			sessionStorage.setItem('buttonTextColor',buttonTextColor);
		}


		if(masterData.getElementsByTagName('primaryButtonBgColor')[0])
		{
			primaryColor=masterData.getElementsByTagName('primaryButtonBgColor')[0].firstChild.nodeValue;
			sessionStorage.setItem('primaryButtonBgColor',primaryColor);
			primaryColor='style="background-color:'+primaryColor+' !important; background-image:none !important ;  border-color:'+primaryColor+'!important; color:'+sessionStorage.getItem('buttonTextColor')+'!important;"';
		}

		if(masterData.getElementsByTagName('secondaryButtonBgColor')[0])
		{
			secondaryColor=masterData.getElementsByTagName('secondaryButtonBgColor')[0].firstChild.nodeValue;
			sessionStorage.setItem('secondaryButtonBgColor',secondaryColor);
			secondaryColor='style="background-color:'+secondaryColor+' !important; background-image:none !important ;  border-color:'+secondaryColor+'!important; color:'+sessionStorage.getItem('buttonTextColor')+'!important;"';
		}

		var bodyCss=$(""+contentNameToAppend).attr('style');
		if(!bodyCss || bodyCss == 'undefined' || bodyCss == 'null')
		{
			bodyCss='';
		}
		console.log('bodyCss---->'+bodyCss);
		bodyCss=bodyCss+'color:'+pageTextColor+' !important;';
		$(""+contentNameToAppend).attr('style',bodyCss);
		$(""+contentNameToAppend).addClass(gFontsCheck(appPageFont));
		console.log('bodyCss new---->'+$(""+contentNameToAppend).attr('class'));

		$(""+contentNameToAppend).append('<style>.audio_list span,.education_list span,.appypie-list span,.service-page ,.service_text_div, .service-page-details, .service_inner_text_div{ color:'+pageTextColor+' !important}</style>');
		console.log("appPageFont--->"+appPageFont);
		console.log("pageTextColor--->"+pageTextColor);
		sessionStorage.setItem('pageTextColor',pageTextColor);
		sessionStorage.setItem('appPageFont',appPageFont);
		sessionStorage.setItem('pageIconColor',pageIconColor);
		// $("body").attr('style','background-color:'+appBackground+' !important;');


		//page text color code ends


		//bottom scroll or more button code starts here
		var moreBottomScroll='NO';

		if(localStorage.getItem("layout") == 'bottom')
		{

			if(masterData.getElementsByTagName('moreBottomScroll')[0])
				moreBottomScroll=masterData.getElementsByTagName('moreBottomScroll')[0].firstChild.nodeValue;

			if(moreBottomScroll == 'YES')
			{
				counter=PageID.length;
			}
		}
		//bottom scroll or more button code ends here


		//pagetext color , iconcolor code starts
		var navTextColor='#fff';
	var navIconColor='#fff';
	var navBorderColor='#fff';
	var showMatrixBackgrondText='YES';

		if(masterData.getElementsByTagName('navTextColor')[0])
			navTextColor=masterData.getElementsByTagName('navTextColor')[0].firstChild.nodeValue;

			if(masterData.getElementsByTagName('navBorderColor')[0])
	{
		navBorderColor = $(masterData).find("navBorderColor").text().toLowerCase();
		sessionStorage.setItem('navBorderColor',navBorderColor)
	}


		//sessionStorage.setItem('navTextColor',navTextColor);

		if(masterData.getElementsByTagName('navIconColor')[0])
			navIconColor=masterData.getElementsByTagName('navIconColor')[0].firstChild.nodeValue;


	if(masterData.getElementsByTagName('showMatrixBackgrondText')[0])
	{
		showMatrixBackgrondText=masterData.getElementsByTagName('showMatrixBackgrondText')[0].firstChild.nodeValue;
		sessionStorage.setItem('showMatrixBackgrondText',showMatrixBackgrondText)
	}

		sessionStorage.setItem('navTextColor',navTextColor);
		sessionStorage.setItem('navIconColor',navIconColor);

		// changes for new layout..
        var layoutHide='No';
		var layoutAlign = "";
		var layoutOption = "";
		var navBackgroundColor='#000000';
		console.log("navIconColor --->"+navIconColor);


		if(masterData.getElementsByTagName('navBackgroundColor')[0])
			navBackgroundColor=masterData.getElementsByTagName('navBackgroundColor')[0].firstChild.nodeValue;


		sessionStorage.setItem('navBackgroundColor',navBackgroundColor)
		console.log("navBackgroundColor--->"+navBackgroundColor);

		// changes for new layout..
		if(masterData.getElementsByTagName('layoutAlign')[0])
			{
				layoutAlign = $(masterData).find("layoutAlign").text().toLowerCase();
				layoutAlign = layoutAlign.replace(/,/g, "-align ") + "-align";
				sessionStorage.setItem('layoutAlign',layoutAlign);
			}

		if(masterData.getElementsByTagName('layoutOption')[0])
			{
				layoutOption = $(masterData).find("layoutOption").text().toLowerCase();
				layoutOption = layoutOption.replace(/,/g, "-option ") + "-option";
				sessionStorage.setItem('layoutOption',layoutOption);
			}



		if(masterData.getElementsByTagName('hideLayout')[0])
			layoutHide=$(masterData).find("hideLayout").text();

		layoutHide=layoutHide.trim();



		var textDisp='block';
		var iconDisp='block';
		var borderDisp='block';
		var addClassForIcon='';
		var compressedSlidemenu='false';

		if(layoutHide.length > 2)
		{
			layoutHide=layoutHide.split(',');
			sessionStorage.setItem('iconbackgrounddisplayval','start');
			for(g=0;g<layoutHide.length;g++)
			{
				if(layoutHide[g] == 'text' || layoutHide[g].indexOf('text') != -1)
				{
					textDisp='none';
					addClassForIcon=addClassForIcon+' textHide';
				}
				else if(layoutHide[g] == 'border')
				{
					borderDisp='none';
					addClassForIcon=addClassForIcon+' hideBorder';
				}
				else if(layoutHide[g] == 'icon')
				{
					iconDisp='none';
					addClassForIcon=addClassForIcon+' iconHide';
				}
				else if(layoutHide[g] == 'none' || layoutHide[g] == 'YES')
				{
					sessionStorage.setItem('iconbackgrounddisplayval','none');
					addClassForIcon=addClassForIcon+' backgroundHide ';
				}
				if(layoutHide[g] == 'compress' || layoutHide[g].indexOf('compress') != -1)
				{
					compressedSlidemenu='true'
				}
			}
		}



		console.log("layoutHide--->"+layoutHide);
		var count=0;
		var iconHtml='';
		var className='';
		var colorForList='';

		if(localStorage.getItem("layout") == 'list')
		{
			colorForList=navBackgroundColor;
			console.log("colorForList--->"+colorForList);
		}


		if(localStorage.getItem('layout') == 'slidemenu')
		{

			$('#slideMenuBgColor').attr('style','background:'+navBackgroundColor);
		}
		//pagetext color , iconcolor code starts
		//change layoutHide to text, when every things finished

		var mode = "right";
  if(localStorage.getItem("appLanguage") == "sa")
  {
   mode = "left";
  }

  if(localStorage.getItem("layout") == 'slidemenu' && compressedSlidemenu == 'true')
  {
   snapper = new Snap({
    element: document.getElementById('content'),
    disable: mode,
    maxPosition:100,
    minPosition: -100,
    transitionSpeed: 0.3,
    easing: 'cubic-bezier(0.175, 0.885, 0.320, 1.275)'
   });

   $('#mainmenu').attr('class','app_navigation_slide_list');
   var slideInLine=$('#slideMenuBgColor').attr('style');
   $('#slideMenuBgColor').attr('style','width:100px;'+slideInLine);
  }

  if(localStorage.getItem("layout") == 'slidemenu' && compressedSlidemenu != 'true')
  {
   if(mode == "left")
   {
    snapper = new Snap({
     element: document.getElementById('content'),
     disable: 'left',
     transitionSpeed: 0.3,
     easing: 'cubic-bezier(0.175, 0.885, 0.320, 1.275)'
    });
   }
  }

		//border,icon,text hide code for matrix,matrix2,list view starts
			var carouselTheme=" " + addClassForIcon +' '+gFontsCheck(navigationFont)+' '+navigationSize+' '+layoutOption+' '+layoutAlign;
          var layoutHeader = localStorage.getItem("layoutHeader");
		if((localStorage.getItem('layout') == 'matrix' || localStorage.getItem('layout') == 'matrix2') && layoutHeader!="YES")
		{
			setTimeout(function(){
				$("#matrixBox").addClass(carouselTheme);
			},10);

			localStorage.setItem("carouselTheme",carouselTheme);


		}
		else if(localStorage.getItem('layout') == 'list')
		{
			$("#app_navigation_matrix").addClass(carouselTheme);
		}
		else if(localStorage.getItem('layout') == 'slidemenu')
		{
			$("#mainmenu").addClass(carouselTheme);
		}
		else
	    {
		 	$("#app_navigation_matrix").addClass(carouselTheme);
			 if(showMatrixBackgrondText == "NO")
			{
				$("#app_navigation_matrix").addClass("backgroundTextHide");
			}
	    }
		//ends

		//first page auto load condition if layout is bottom starts
		var autoLoadFirstPage='YES';

		var packageName=toaster.getAppPackageName();
		documentPath='file:///data/data/'+packageName+"/homeIcon";

		if(masterData.getElementsByTagName('autoLoadFirstPage')[0])
			autoLoadFirstPage=masterData.getElementsByTagName('autoLoadFirstPage')[0].firstChild.nodeValue;

		sessionStorage.setItem('autoLoadFirstPage',autoLoadFirstPage);
		//ends
		var dirIndexToDownload=0;
		console.log("navBackgroundColor---->"+navBackgroundColor);


		if(PageName[0].trim() != '' && PageName[0])
		{
			var layoutHeader=localStorage.getItem('layoutHeader');

		   if(localStorage.getItem("layout").indexOf('matrix') != -1 && (layoutHeader != "YES") && (localStorage.getItem("layout") != "imgmatrix"))
            {
			if(PageName.length==1 && autoLoadFirstPage == 'YES' && localStorage.getItem("layout")=="matrix")
            {
            openPage(0);
            return true;
            }
				console.log("navIconColorkrishna---->"+sessionStorage.getItem('navIconColor'));
				callOnOrientation();
			}
			else
			{
				jQuery.each(PageName, function()
						{
					//setTimeout(function(){$('div.ui-loader').hide();},1000);
					if(x<counter)
					{
						if(pageIcons[x].indexOf('.')!=-1)
						{
							if(!checkNetworkConnection('true'))
							{

								imagepath=documentPath+'/' + pageIcons[x];
								console.log("rcimagepath"+imagepath)
							}
							else
							{
								if(localStorage.getItem("IconDownload")=="true" && sessionStorage.getItem("applicationUpdated")!="true" && !checkNetworkConnection('true'))
								{
									imagepath=localStorage.getItem("documentPath")+'/' + pageIcons[x];
								}
								else
								{
									imagepath=localStorage.getItem("iconPath")+'/'+pageIcons[x];

								}

							}

							if(localStorage.getItem("layout")== "matrix" || localStorage.getItem("layout")=="matrix2")
							{

     iconHtml='<img src="'+imagepath+ '" border="0" style="border-color:'+sessionStorage.getItem('navBorderColor')+'" />';
							}

							else{
								console.log("layouttttttttt2"+localStorage.getItem("layout"));
								iconHtml='<img src="'+imagepath+ '" border="0" style="display:'+iconDisp+'" />';
							}


						}
						else
						{
							if($("#app_navigation_matrix").is(".rounded-option") && localStorage.getItem("layout")=="matrix")
						{

							var backgroundC = sessionStorage.getItem('navBackgroundColor');
							if(sessionStorage.getItem('iconbackgrounddisplayval')=='none')
							{
								backgroundC = "none"
							}
							iconHtml='<i style="color:'+sessionStorage.getItem('navIconColor')+';display:'+iconDisp+';background:'+backgroundC+';border-color:'+sessionStorage.getItem('navBorderColor')+'" class="pageIcon '+pageIcons[x]+'"></i>';
						}
						else
						{
							iconHtml='<i style="color:'+sessionStorage.getItem('navIconColor')+';display:'+iconDisp+'" class="pageIcon df '+pageIcons[x]+'"></i>';
						}
						}

						if(localStorage.getItem("layout")!="slidemenu")
						{
							if(localStorage.getItem("layout")=="bottom" && moreBottomScroll != 'YES')
							{
								$(".app_navigation_bottom").append('<a href="#" id="classActive'+x+'" class="bottomClass" onclick="openPage('+x+');" >'+iconHtml+'<span style="color:'+navTextColor+';">'+ this +'</span> </a>');
							}
							else if(localStorage.getItem("layout")=="bottom" && moreBottomScroll == 'YES')
							{


								$("#sync2").append('<a href="#" id="classActive'+x+'" class="bottomClass" onclick="openPage('+x+');" >'+iconHtml+'<span style="color:'+navTextColor+';">'+ this +'</span> </a>');

							}
							else if(localStorage.getItem("layout")=="imglist")
                        {

      var matrixObj = $("#app_navigation_matrix");
     if(sessionStorage.getItem('iconbackgrounddisplayval')=='none')
     {
      matrixObj.append('<a href="#" class="fixedList '+className+'" onclick="openPage('+x+');" style=" border-color:'+sessionStorage.getItem('navBorderColor')+'; background-image:url('+imagepath+');"><img src="images/banner-image.png" /><span style="color:'+navTextColor+';background:none;">'+ this +'</span> </a>');
     }
     else
     {
      matrixObj.append('<a href="#" class="fixedList '+className+'" onclick="openPage('+x+');" style="background-color:'+colorForList+';border-color:'+sessionStorage.getItem('navBorderColor')+'; background-image:url('+imagepath+');"><img src="images/banner-image.png" /><span style="color:'+navTextColor+';background-color:'+navBackgroundColor+';">'+ this +'</span></a>');
     }

                 }
				   else if(localStorage.getItem("layout")=="imgmatrix")
                        {
                        //Code for new layout Ravi - imggrid
                        var matrixObj = $("#app_navigation_matrix");
                        if(sessionStorage.getItem('iconbackgrounddisplayval')=='none')
                        {
       matrixObj.append('<a href="#" class="fixedList '+className+'" onclick="openPage('+x+');" style=" border-color:'+sessionStorage.getItem('navBorderColor')+'; background-image:url('+imagepath+');"><img src="images/imgmatrix-image.png" /><span style="color:'+navTextColor+';background:none;">'+ this +'</span> </a>');
      }
     else
     {
      matrixObj.append('<a href="#" class="fixedList '+className+'" onclick="openPage('+x+');" style="background-color:'+colorForList+';border-color:'+sessionStorage.getItem('navBorderColor')+'; background-image:url('+imagepath+');"><img src="images/imgmatrix-image.png" /><span style="color:'+navTextColor+';background-color:'+navBackgroundColor+';">'+ this +'</span></a>');
     }
                        }
				else
                  {
					  //Code for new layout Ravi - list,rounded,center

					var layoutHeader=localStorage.getItem('layoutHeader');

					var matrixObj = $("#app_navigation_matrix");
					if(matrixObj.is(".centered-option"))
					{
						if(matrixObj.find(".nav-content").size() == 0)
						{
							matrixObj.append('<div class="nav-content"></div>');
						}
						matrixObj = matrixObj.find(".nav-content");
					}

					if(sessionStorage.getItem('iconbackgrounddisplayval')=='none')
					{
						matrixObj.append('<a href="#" class="fixedList '+className+'" onclick="openPage('+x+');" style="background:none; border-color:'+sessionStorage.getItem('navBorderColor')+';">'+iconHtml+'<span style="color:'+navTextColor+';background:none;">'+ this +'</span> </a>');
					}
					else
					{
						//matrixObj.append('<a href="#" class="fixedList '+className+'" onclick="openPage('+x+');" style="border-color:'+sessionStorage.getItem('navBorderColor')+';background-color:'+sessionStorage.getItem('navBackgroundColor')+';">'+iconHtml+'<span style="color:'+navTextColor+';background-color:'+sessionStorage.getItem('navBackgroundColor')+';">'+ this +'</span></a>');
						if(showMatrixBackgrondText == "NO")
						{
							matrixObj.append('<a href="#" class="fixedList '+className+'" onclick="openPage('+x+');" style="border-color:'+sessionStorage.getItem('navBorderColor')+';background-color:'+sessionStorage.getItem('navBackgroundColor')+';">'+iconHtml+'<span style="color:'+navTextColor+';background-color:transparent;">'+ this +'</span></a>');
						}
						else
						{
							matrixObj.append('<a href="#" class="fixedList '+className+'" onclick="openPage('+x+');" style="border-color:'+sessionStorage.getItem('navBorderColor')+';background-color:'+sessionStorage.getItem('navBackgroundColor')+';">'+iconHtml+'<span style="color:'+navTextColor+';background-color:'+sessionStorage.getItem('navBackgroundColor')+';">'+ this +'</span></a>');
						}
					}




				  	if(!$("#app_navigation_matrix").is(".centered-option"))
					{
						if(layoutHeader == "YES")
						{

							createHomeHeader();
						}
					}

					$("#contentHolder1").addClass("on");
					//$.support.touchOverflow = true;
					//$.mobile.touchOverflowEnabled = true;


                  }
						}
						else
						{
							if(PageID[x]=="ecommerce")
							{
								$("#mainmenu").append('<a href="#" class="'+className+'" onclick="openPage('+x+');"   style="border-color:'+sessionStorage.getItem('navBorderColor')+';">'+iconHtml+'<span style="color:'+navTextColor+';">'+ this +'</span> </a>');
							}

							else if(PageID[x]=="foodordering")
							{
								// $("#mainmenu").append('<a href="#" onclick="opensubmenu('+1+')"> <img src="'+imagepath+ '" border="0"  /> <span>'+ this +'</span> </a>');
								$("#mainmenu").append('<a href="#" class="'+className+'" onclick="openPage('+x+');"   style="border-color:'+sessionStorage.getItem('navBorderColor')+';">'+iconHtml+'<span style="color:'+navTextColor+';">'+ this +'</span> </a>');
							}

							else
							{
								$("#mainmenu").append('<a href="#" class="'+className+'" onclick="openPage('+x+');"   style="border-color:'+sessionStorage.getItem('navBorderColor')+';">'+iconHtml+'<span style="color:'+navTextColor+';">'+ this +'</span> </a>');
							}

							if(x==counter-1)
							{
								if(localStorage.getItem("iad")=='True')
									$("#mainmenu").append('<div style="display:inline-block; float: left; "></br></br></br></br></br></div>');
								if(sessionStorage.getItem("EcomSubMenu")!="true")
								{
									sessionStorage.removeItem("EcomSubMenu");
									setTimeout(function() {
										openPage(0); },1000);
								}
							}


						}
						count++;
					}
					if(PageID[x] == 'services')
					{
						//   downloadDirectory(dirIndexToDownload);
						dirIndexToDownload=dirIndexToDownload + 1;
					}
					x=x+1;

						});


				if(parseInt(count)==1 && autoLoadFirstPage == 'YES' && (localStorage.getItem("layout")=="slidemenu" || localStorage.getItem("layout")=="bottom"))
				{
					sessionStorage.setItem("singlePageId","true");
					if(localStorage.getItem("layout")=="slidemenu" && PageID[0]!="ecommerce" && PageID[0] != "foodordering" && PageID[0] != "socialnetwork" && PageID[0] != "newsstand")
					{
						//$('#open-left').remove();
						$('#appendLogo').append('<img src="images/logo.png" />');
						snapper.disable();
					}
					else if(localStorage.getItem("layout")=="bottom")
					{
						//openPage(0);
						$(".app_navigation_bottom").hide();
						$(".app_navigation_bottom_carousel").hide();
						$( "[data-role='footer']" ).remove();
						$( "[data-role='footer']" ).toolbar('destroy');
						$.mobile.resetActivePageHeight();
					}
				}

				console.log("moreBottomScroll>>>>>"+moreBottomScroll);
				if(localStorage.getItem("layout")=="bottom" && moreBottomScroll != 'YES')
				{
					//alert("bottom1");
					$(".app_navigation_bottom").addClass(''+addClassForIcon+' '+gFontsCheck(navigationFont)+' '+navigationSize);
					$( ".app_navigation_bottom").attr('style','color:'+navTextColor+';background-color:'+navBackgroundColor+';');
					$( ".app_navigation_bottom").addClass('headerColor');
					// change by deepak
					$( "[data-role='footer'] a" ).attr("data-role","button").attr("role","button").addClass(" ui-link ui-btn ui-shadow");

					var moreText='More';
					if(masterData.getElementsByTagName('more')[0])
					{
						moreText=masterData.getElementsByTagName('more')[0].firstChild.nodeValue;
					}
					if(parseInt(count) == 4 && PageID.length>4)
					{
						console.log("the value of count-->"+count);
						$(".app_navigation_bottom").append('<a href="JavaScript:openIndexPage();" id="classActive4" class="bottomClass"><i style="color:'+sessionStorage.getItem('navIconColor')+';display:'+iconDisp+'" class="pageIcon icon-dot-3"></i><span style="color:'+navTextColor+';">'+moreText+'</span> </a>');
					}

					console.log("autoLoadFirstPage --->"+autoLoadFirstPage);
					if(autoLoadFirstPage == 'YES')
					{
					   if((PageID[0]=="news" || PageID[0]=="ecommerce" || PageID[0]=="services") && sessionStorage.getItem("backfromMenu")=="true")
					   {
					   sessionStorage.setItem("backfromMenu","");
					   }
					   else
					   {
					   openPage(0);
					   }
					}
				}
				else if(localStorage.getItem("layout")=="bottom" && moreBottomScroll == 'YES')
				{

					//alert("bottom2");
					console.log("addClassForIcon --->"+addClassForIcon);
					$(".app_navigation_bottom_carousel").addClass(''+addClassForIcon+' '+gFontsCheck(navigationFont)+' '+navigationSize);
					$( ".app_navigation_bottom_carousel").attr('style','color:'+navTextColor+';background-color:'+navBackgroundColor+';');
					$( ".app_navigation_bottom_carousel").addClass('headerColor');
					// console.log("classes for bottom scroll --->"+$(".app_navigation_bottom_carousel").attr('class'));
					console.log("getIndexData() bottom layout moreBottomScroll ---->"+moreBottomScroll);
					no_Header_Bootom();
					console.log("autoLoadFirstPage --->"+autoLoadFirstPage);
					if(autoLoadFirstPage == 'YES')
					{
						$("#iconDiv").css({ display: "none" });
						if((PageID[0]=="news" || PageID[0]=="ecommerce" || PageID[0]=="services") && sessionStorage.getItem("backfromMenu")=="true")
					   {
					   sessionStorage.setItem("backfromMenu","");
					   }
					   else
					   {
					   openPage(0);
					   }
					}
				}


				if(localStorage.getItem("fromNotification")!='null') {

					beconNotification();
				}
			}
		}else
		{

			console.log("No pages hide");
			alertPopUp(aalert_code+'!',wecannotaccesshidden_code);

		}


	}
	$.mobile.resetActivePageHeight();
	$(document).gFontsLoad(masterData);


}

function checkAndDownloadAppypieXml(fileToDownload)
{
	var pathForXMl="file://"+toaster.getInternalStorageFileDir();

	setTimeout(function (){


		var fileTransfer = new FileTransfer();
		var uri = fileToDownload;
		if(uri.indexOf('%')==-1)
		{
			uri = encodeURI(fileToDownload);
		}
		console.log("fileToDownload :: " + fileToDownload + " to path -->  ");
		fileTransfer.download(uri,pathForXMl,function(entry)
				{
			//alert(pathForXMl);
			//alert("downloaded from phonegap");
			readUpdatedXmlFromSdCard(pathForXMl);
				},
				function (error){
					toaster.updateApplicationXML();
					readUpdatedXml(sessionStorage.getItem("updatedXML"));
				},false, {headers: {"Authorization": "Basic dGVzdHVzZXJuYW1lOnRlc3RwYXNzd29yZA=="}}
				);
	},100);
}

function checkAndDownloadDir(fileToDownload,index,dirVersion,fileName)
{
	var xmlPath=sessionStorage.getItem('xmlPath');
	var filedownloadPath=xmlPath.replace("appypie.xml",fileName);
	var fileTransfer = new FileTransfer();
	var uri = fileToDownload;
	if(uri.indexOf('%')==-1)
	{
		uri = encodeURI(fileToDownload);
	}
	console.log("fileToDownload :: " + fileToDownload + " to path -->  " + filedownloadPath);
	fileTransfer.download(uri,filedownloadPath,function(entry)
			{
		localStorage.setItem("directoryDownloadPath"+index,fileName);
		if(localStorage.getItem("directoryPrevVersion"+index) != dirVersion)
		{
			localStorage.setItem("directoryPrevVersion"+index,dirVersion);
		}

			},
			function (error){

			},false, {headers: {"Authorization": "Basic dGVzdHVzZXJuYW1lOnRlc3RwYXNzd29yZA=="}}
			);
}

//for bottom scroll page
function no_Header_Bootom()
{
	console.log("no_Header_Bootom() starts");
	//alert("no_Header_Bootom");
	if(localStorage.getItem("appLanguage") == "sa")
	{
		var sync2Clone = $("#sync2").clone();
		$("#sync2").html("");
		sync2Clone.find("a").each(function(){
			$(this).prependTo("#sync2");
		})
	}

	$("#sync2").owlCarousel({
		slideSpeed : 200,
		navigation: false,
		pagination:false,
		items :16,
		rewindNav : false,

	});

	if(localStorage.getItem("appLanguage") == "sa")
	{
		var matrixBox = $("#sync2").data('owlCarousel');
		matrixBox.jumpTo($("#sync2").find("a").size() - 1);
	}
	//alert($(".app_navigation_bottom_carousel").html());
	console.log("no_Header_Bootom() ends");
}

function beconNotification(){



	var beaconInfo= localStorage.getItem("fromNotification");
	var beaconValues=beaconInfo.split("@@");
	var xmlFileData= $.parseXML(window.sessionStorage.getItem("xml"));
	$xml = $(xmlFileData);
	var appbeacon= $xml.find("appybeacon");
	$(appbeacon).find('couponBeacon').each(function()
			{
		if(beaconValues[0].toUpperCase()==$(this).find("uuid").text() && beaconValues[1]== $(this).find("minor").text() && beaconValues[2]== $(this).find("major").text()){
			var couponIndexValue=$(this).find("couponIndex").text();
			var selectedPage=$(this).find("selectedPage").text();
			if($(this).find("status").text()==1 && couponIndexValue.length > 1){
				getcoupon(0);
				localStorage.setItem("fromNotification","null");
			}
			else if($(this).find("status").text()=='1' && (selectedPage.length>1)){
				for (var i = 0; i < appbeaconidentifierarray.length; i++) {
					if(selectedPage==appbeaconidentifierarray[i])
					{
						openPage(i);
					}

				}
				localStorage.setItem("fromNotification","null");
			}
			else {
				navigator.notification.alert(
						$(this).find("message").text(),
						alertDismissed,
						'Message',
						ookk_code
						);
			}
		}
			});



}
function openBeaconPage(pageidbeacon){
	var res = pageidbeacon.split("_");
	switch(res[0])
	{
	case 'about':

		getAboutUs((res[2]-1));
		break;
	case 'contact':

		getcontact((res[2]-1));
		break;
	case 'audio':

		getaudio((res[2]-1));
		break;
	case 'event':

		geteventPage((res[2]-1));
		break;
	case 'services':
		getService((res[2]-1));
		break;
	case 'quote':

		getquote((res[2]-1));
		break;
	case 'instagram':

		getinstagram((res[2]-1));
		break;
	case 'facebook':

		getfacebookPage((res[2]-1));
		break;
	case 'twitter':

		gettwitterPage((res[2]-1));
		break;
	case 'photo':

		photo_gallery((res[2]-1));
		break;
	case 'qrcode':

		getqrcode((res[2]-1));
		break;
	case 'map':
		if(toaster.isLocationEnabled())
		{
			getmap((res[2]-1));
		}
		else
		{
			toaster.openLocationSetting();
		}

		break;
	case 'menu':

		getmenu((res[2]-1));
		break;
	case 'tools':

		gettools((res[2]-1));
		break;
	case 'website':

		getwebsite((res[2]-1));
		break;
	case 'social':

		getsocial((res[2]-1));
		break;
	case 'textpage':

		gettextpage((res[2]-1));
		break;
	case 'codepage':

		getcodepage((res[2]-1));
		break;
	case 'appointment':

		getApointment((res[2]-1));
		break;
	case 'googleplus':

		getgoogleplusPage((res[2]-1));
		break;
	case 'education':

		geteducation((res[2]-1));
		break;
	case 'loyaltycard':

		getloyaltycardPage((res[2]-1));
		break;
	case 'survey':

		getsurvey((res[2]-1));
		break;
	case 'blog':

		getBlog((res[2]-1));
		break;
	case 'coupon':

		getcoupon((res[2]-1));
		break;
	case 'video':

		getvideo((res[2]-1));
		break;
	case 'rss':

		getRSSFeed((res[2]-1));
		break;
	case 'foodordering':
		getFoodorderingData((res[2]-1));
		break;
	case 'ecommerce':
		getEcommerceData((res[2]-1));
		break;
	case 'ereader':

		getEReaderData((res[2]-1));
		break;
	case 'testimonial':

		getTestimonial((res[2]-1));
		break;
	case 'opentable':

		getOpentable((res[2]-1));
		break;
	case 'callme':

		getCallMe((res[2]-1));
		break;
	case 'review':

		getreview((res[2]-1));
		break;

	case 'linkedin':

		getLinkedin((res[2]-1));
		break;

	case 'chatroom':
		getChat((res[2]-1));
		break;

	case 'religious':
		getreligious((res[2]-1));
		break;

	case 'socialwall':
		getWallPost((res[2]-1));
		break;
	case 'loyalty':
		startLoyaltyParsing((res[2]-1));
		break;
	case 'formbuilder':
		getForm((res[2]-1));
		break;
	case 'newsstand':
		newsstandPage((res[2]-1));
		break;
	case 'socialnetwork':
		getsocialapp((res[2]-1));
		break;
	case 'donation':
		getdonation((res[2]-1));
		break;
	}

}

var loadPageFlag=0;
function openPage(index,falgValue)
{

	//start change by amritesh on 02-02-16
  if(localStorage.getItem("hidebootomforce")=='true')
      {
      console.log("in hidebootomforce");
      localStorage.setItem("hidebootomforce",'false');
      return;
      }
//start change by amritesh on 02-02-16

    localStorage.setItem("ecomOpen", "false");
	localStorage.setItem("newsOpen", "false");
	localStorage.setItem("directoryOpen", "false");
	sessionStorage.removeItem('fromFoodPage');
	$("#contentHolder3").removeClass("whitestbg");
	$("#contentHolder2").removeClass("whitestbg");
	sessionStorage.removeItem("singlePage");
	sessionStorage.removeItem("mcomCheckOut");
	isSocial='No';

	if(sessionStorage.getItem("zopimchat")){
	$("#chatZopim").show();
	}

	if(sessionStorage.getItem('fromLoyalty'))
	{
		$('#contentHolder8').removeClass('page-content mediumContent bg-color');
	}

	if(localStorage.getItem("layout")=="slidemenu")
	{
		snapper.close();
	}

	if((localStorage.getItem("layout")=="bottom" && parseInt(index) < 5) || sessionStorage.getItem("bottomScroll") == 'true')
	{
		sessionStorage.removeItem("openPageIndex");
		$('.bottomClass').removeClass('ui-btn-active');
		$('#classActive'+index).addClass('ui-btn-active');
		$( "[data-role='footer'] a" ).attr("data-role","button").attr("role","button").addClass(" ui-link ui-btn ui-shadow");
	}
	sessionStorage.removeItem("ereaderPage");
	sessionStorage.setItem("pageIndex",index);
	sessionStorage.setItem("AppPageName",PageID[index]);

	$("#bookmark").hide();
	$("#shopmenu").hide();
	$("#mainbackfoodecom").hide();
	$("#mainback").hide();
	Showhideupdatebutton();
	var funcName='';
	var tagName='';
	if(sessionStorage.getItem("autoLoadFirstPage")=='NO' && loadPageFlag==0 && localStorage.getItem('layout') == 'slidemenu')
    {
        loadPageFlag++;
    }
	else
	{
	switch(PageID[index])
	{
	case 'about':
	window.location="removeWebSite:";
		funcName='getAboutUs('+PageIndex[index]+')';
		tagName='aboutUs';
		break;
	case 'contact':
	window.location="removeWebSite:";
		funcName='getcontact('+PageIndex[index]+')';
		tagName='contactUs';
		break;
	case 'audio':
	window.location="removeWebSite:";
		funcName='getaudio('+PageIndex[index]+')';
		tagName='audio';
		break;
	case 'event':
	window.location="removeWebSite:";
		funcName='geteventPage('+PageIndex[index]+')';
		tagName='event';
		break;
	case 'services':
	window.location="removeWebSite:";
	localStorage.setItem("directoryOpen", "true");
		funcName='getService('+PageIndex[index]+')';
		tagName='services';
		break;
	case 'quote':
	window.location="removeWebSite:";
		funcName='getquote('+PageIndex[index]+')';
		tagName='Request_Quote';
		break;
	case 'instagram':
	window.location="removeWebSite:";
		funcName='getinstagram('+PageIndex[index]+')';
		tagName='Instagram';
		break;
	case 'facebook':
	window.location="removeWebSite:";
		funcName='getfacebookPage('+PageIndex[index]+')';
		tagName='facebook';
		break;
	case 'twitter':
	window.location="removeWebSite:";
		funcName='gettwitterPage('+PageIndex[index]+')';
		tagName='TwitBlock';
		break;
	case 'photo':
	window.location="removeWebSite:";
		funcName='photo_gallery('+PageIndex[index]+')';
		tagName='photo';

		break;
	case 'qrcode':
	window.location="removeWebSite:";
		funcName='getqrcode('+PageIndex[index]+')';
		tagName='QR';

		break;

	case 'editorpage':
	window.location="removeWebSite:";
               funcName='geteditorpage('+PageIndex[index]+')';
                  tagName='editorpage';
                  break;

	case 'map':
	window.location="removeWebSite:";
		if(toaster.isLocationEnabled())
		{
			funcName='getmap('+PageIndex[index]+')';
			tagName='map';

		}
		else
		{
			toaster.openLocationSetting();
		}
		break;
	case 'menu':
	window.location="removeWebSite:";
		funcName='getmenu('+PageIndex[index]+')';
		tagName='menu';
		break;
	case 'tools':
	window.location="removeWebSite:";
		funcName='gettools('+PageIndex[index]+')';
		tagName='pocketTools';
		break;
	case 'website':
		funcName='getwebsite('+PageIndex[index]+')';
		tagName='website';
		break;
	case 'social':
	window.location="removeWebSite:";
		funcName='getsocial('+PageIndex[index]+')';
		tagName='social';
		break;
	case 'textpage':
	window.location="removeWebSite:";
		funcName='gettextpage('+PageIndex[index]+')';
		tagName='TextPage';
		break;
	case 'codepage':
	window.location="removeWebSite:";
		funcName='getcodepage('+PageIndex[index]+')';
		tagName='codePage';
		break;
	case 'appointment':
	window.location="removeWebSite:";
		funcName='getApointment('+PageIndex[index]+')';
		tagName='appointment';
		break;
	case 'googleplus':
	window.location="removeWebSite:";
		funcName='getgoogleplusPage('+PageIndex[index]+')';
		tagName='Googleplus';
		break;
	case 'education':
	window.location="removeWebSite:";
		funcName='geteducation('+PageIndex[index]+')';
		tagName='Education';
		break;
	case 'loyaltycard':
	window.location="removeWebSite:";
		funcName='getloyaltycardPage('+PageIndex[index]+')';
		tagName='loyaltyCard';
		break;
	case 'survey':
	window.location="removeWebSite:";
		funcName='getsurvey('+PageIndex[index]+')';
		tagName='Survey';
		break;
	case 'blog':
	window.location="removeWebSite:";
		funcName='getBlog('+PageIndex[index]+')';
		tagName='Blog';
		break;
	case 'coupon':
	window.location="removeWebSite:";
		funcName='getcoupon('+PageIndex[index]+')';
		tagName='coupon';
		break;
	case 'video':
	window.location="removeWebSite:";
		funcName='getvideo('+PageIndex[index]+')';
		tagName='Video';
		break;
	case 'rss':
	window.location="removeWebSite:";
		funcName='getRSSFeed('+PageIndex[index]+')';
		tagName='Rss';
		break;
	case 'foodordering':
	window.location="removeWebSite:";
		funcName='getFoodorderingData('+PageIndex[index]+')';
		tagName='foodordering';
		break;
	case 'ecommerce':
	window.location="removeWebSite:";
	localStorage.setItem("ecomOpen", "true");
		funcName='getEcommerceData('+PageIndex[index]+')';
		tagName='ecommerce';

		break;
	case 'ereader':
	window.location="removeWebSite:";
		funcName='getEReaderData('+PageIndex[index]+')';
		tagName='ereader';
		break;
	case 'testimonial':
	window.location="removeWebSite:";
		funcName='getTestimonial('+PageIndex[index]+')';
		tagName='testimonial';
		break;
	case 'opentable':
	window.location="removeWebSite:";
		funcName='getOpentable('+PageIndex[index]+')';
		tagName='opentable';
		break;
	case 'callme':
window.location="removeWebSite:";
		funcName='getCallMe('+PageIndex[index]+')';
		tagName='callMe';
		break;
	case 'review':
	window.location="removeWebSite:";
		funcName='getreview('+PageIndex[index]+')';
		tagName='review';
		break;
	case 'linkedin':
	window.location="removeWebSite:";
		funcName='getLinkedin('+PageIndex[index]+')';
		tagName='linkedin';
		break;
	case 'magento':
	window.location="removeWebSite:";
		funcName='getMagentoView('+PageIndex[index]+')';
		tagName='magento';
		break;
	case 'chatroom':
	window.location="removeWebSite:";
		funcName='getChat('+PageIndex[index]+')';
		tagName='chat';
		break;
	case 'religious':
	window.location="removeWebSite:";
		funcName='getreligious('+PageIndex[index]+')';
		tagName='religious';
		break;
	case 'socialwall':
	window.location="removeWebSite:";
		funcName='getWallPost('+PageIndex[index]+')';
		tagName='socialwall';
		break;
	case 'loyalty':
	window.location="removeWebSite:";
		funcName='startLoyaltyParsing('+PageIndex[index]+')';
		tagName='loyalty';
		break;
	case 'quiz':
window.location="removeWebSite:";
		funcName='getquiz('+PageIndex[index]+')';
		tagName='quiz';
		break;
	case 'formbuilder':
	window.location="removeWebSite:";
		funcName='getForm('+PageIndex[index]+')';
		tagName='Formbuilder';
		break;
	case 'newsstand':
	window.location="removeWebSite:";
		funcName='newsstandPage('+PageIndex[index]+')';
		tagName='newsstand';
		break;
	case 'socialnetwork':
	window.location="removeWebSite:";
		funcName='getsocialapp('+PageIndex[index]+')';
		tagName='newsstand';
		break;
	case 'donation':
	window.location="removeWebSite:";
		funcName='getdonation('+PageIndex[index]+')';
		tagName='donation';
		break;
	case 'eecommerce':
	window.location="removeWebSite:";
		funcName='getEcommerceMultipleData('+PageIndex[index]+')';
		tagName='eecommerce';
		break;
	case 'cms':
	window.location="removeWebSite:";
		funcName='getCmsMultipleData('+PageIndex[index]+')';
		tagName='cms';
		break;
	case 'scheduling':
	window.location="removeWebSite:";
		funcName='getSchedulingMultipleData('+PageIndex[index]+')';
		tagName='scheduling';
		break;
    case 'deeplink':
	window.location="removeWebSite:";
		funcName='getdeeplink('+PageIndex[index]+')';
		tagName='deeplink';
		break;
	case 'news':
	window.location="removeWebSite:";
        localStorage.setItem("newsOpen", "true");
		funcName='getnews('+PageIndex[index]+')';
		tagName='news';
		break;
	case 'reminder':
        //funcName='getReminder('+PageIndex[index]+')';
		//tagName='reminder';
		break;
	}
  }
	if(funcName.length > 3 && tagName.length >= 2)
	{
		$menuXmlData = $(masterData).find( ""+tagName+"[indexval="+PageIndex[index]+"]" );
		if($menuXmlData.find("autoLogin"))
		{
			console.log('open page --->'+PageIndex[index]);
			var autoLogin=$menuXmlData.find("autoLogin").text();
			console.log("autoLogin --->"+autoLogin);
			console.log("fooduserid --->"+localStorage.getItem("fooduserid"));
			console.log("toaster.getLoginStatus()"+toaster.getLoginStatus()+"autoLogin"+autoLogin);
			if(toaster.getLoginStatus() != "true" && autoLogin == "YES")
			{
				//alert("singhal");
				loginInnerPages();
			}
			else
			{
				if(localStorage.getItem("fooduserid"))
				{
                   // window.setTimeout(funcName,10);
					var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/app-user-soap#userActiveDeactive";

					var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><userActiveDeactive xmlns=\"http://'+localStorage.getItem("reseller")+'/services/app-user-soap#userActiveDeactive/\"><appId>'+localStorage.getItem("applicationID")+'</appId><email>'+localStorage.getItem('fooduserid')+'</email></userActiveDeactive></soap:Body></soap:Envelope>';
					jQuery.support.cors = true;
					$.ajax({
						type: "POST",
						url: wsUrl,
						contentType: "text/xml",
						dataType: "text",
						data: soapRequest,
						success: function(data, status, req)
						{
						var xmlret=$.parseXML(req.responseText);
						var strJSON = $(xmlret).find("return").text();
						if(strJSON == 'false' || strJSON == 'Not Exists')
						{
							localStorage.removeItem("fooduserid");
							logoutFromApp();
							//loginInnerPages();
						}
						else
						{
							window.setTimeout(funcName,10);
						}

						},
						error: function(response, textStatus, errorThrown)
						{
							window.setTimeout(funcName,10);
						}
					});

				}
				else{
					window.setTimeout(funcName,10);
				}
			}
		}
		else
		{
			window.setTimeout(funcName,10);
		}
	}

}
function loadjscssfile(filename, filetype){
	if (filetype=="js"){ //if filename is a external JavaScript file
		var fileref=document.createElement('script')
				fileref.setAttribute("type","text/javascript")
				fileref.setAttribute("src", filename)
	}
	else if (filetype=="css"){ //if filename is an external CSS file
		var fileref=document.createElement("link")
				fileref.setAttribute("rel", "stylesheet")
				fileref.setAttribute("type", "text/css")
				fileref.setAttribute("href", filename)
	}

	if (typeof fileref!="undefined")
		document.getElementsByTagName("head")[0].appendChild(fileref)
}

function removejscssfile(filename, filetype){
	var targetelement=(filetype=="js")? "script" : (filetype=="css")? "link" : "none" //determine element type to create nodelist from
		var targetattr=(filetype=="js")? "src" : (filetype=="css")? "href" : "none" //determine corresponding attribute to test for
			var allsuspects=document.getElementsByTagName(targetelement)
			for (var i=allsuspects.length; i>=0; i--)
			{
				if (allsuspects[i] && allsuspects[i].getAttribute(targetattr)!=null && allsuspects[i].getAttribute(targetattr).indexOf(filename)!=-1)
					allsuspects[i].parentNode.removeChild(allsuspects[i]) //remove element by calling parentNode.removeChild()
			}
}

function alertDismissed()
{

}
function showEcomMenu(){
	$(".sub-menu").stop(true, true).slideToggle();
}
function openIndexPage()
{
	sessionStorage.setItem('openPageIndex',true);
	window.location="removeWebSite:";
	if(localStorage.getItem("layout")=="bottom")
	{
		$('.bottomClass').removeClass('ui-btn-active');
		$('#classActive4').addClass('ui-btn-active');
	}

	$("#logo").show();
	$("#mainback").hide();
	$("#shopmenu").hide();
	$("#bookmark").hide();
	//$("#reload").show();
	 Showhideupdatebutton();
	$("#contentHolder1").empty();
	$("#contentHolder1").append('<section  id="app_navigation_matrix"></section>');
	$("#app_navigation_matrix").attr("class", 'app_navigation_list');
	var x=4;
	var counter=PageID.length;
	$("#app_navigation_matrix").empty();
	var imagepath='';
	var navTextColor=masterData.getElementsByTagName('navTextColor')[0].firstChild.nodeValue;
	var navIconColor=masterData.getElementsByTagName('navIconColor')[0].firstChild.nodeValue;
	var layoutHide='No';
	var pageTextColor='#fff';
	var appPageFont='georgia';

	if(masterData.getElementsByTagName('pageTextColor')[0])
		pageTextColor=masterData.getElementsByTagName('pageTextColor')[0].firstChild.nodeValue;

	if(masterData.getElementsByTagName('appPageFont')[0])
		appPageFont=masterData.getElementsByTagName('appPageFont')[0].firstChild.nodeValue;
	if(masterData.getElementsByTagName('hideLayout')[0])
	{

		layoutHide=$(masterData).find('hideLayout').text();

	}

	var textDisp='block';
	var iconDisp='block';
	var borderDisp='block';
	layoutHide=layoutHide.split(',');
	sessionStorage.setItem('iconbackgrounddisplayval','start');
	for(g=0;g<layoutHide.length;g++)
	{
		if(layoutHide[g] == 'text')
		{
			textDisp='none';
		}
		else if(layoutHide[g] == 'border')
		{
			borderDisp='none';
		}
		else if(layoutHide[g] == 'icon')
		{
			iconDisp='none';
		}
		else if(layoutHide[g] == 'none' || layoutHide[g] == 'YES')
		{
			//sessionStorage.setItem('iconbackgrounddisplayval','none');
			sessionStorage.setItem('iconbackgrounddisplayval','none');
					addClassForIcon=addClassForIcon+' backgroundHide ';
		}
	}

	while(x<counter)
	{

		if(x<counter)
		{
			var iconHtml='';
			if(pageIcons[x].indexOf('.')!=-1)
			{
				if(!checkNetworkConnection('true'))
				{
					imagepath= localStorage.getItem("documentPath")+'/' + pageIcons[x];
				}
				else
				{
					if(localStorage.getItem("IconDownload")=="true" && sessionStorage.getItem("applicationUpdated")!="true" && !checkNetworkConnection('true'))
					{
						imagepath=localStorage.getItem("documentPath")+'/' + pageIcons[x];
					}
					else
					{
						imagepath=localStorage.getItem("iconPath")+'/'+pageIcons[x];
						//console.log("the path of image -"+x+"<---"+imagepath);
					}

				}
				iconHtml='<img src="'+imagepath+ '" border="0" style="display:'+iconDisp+'" />';
			}
			else
			{
				iconHtml='<i style="color:'+sessionStorage.getItem('navIconColor')+';display:'+iconDisp+'" class="pageIcon '+pageIcons[x]+'"></i>';
			}

			$("#app_navigation_matrix").append('<a href="JavaScript:openPage('+x+');" style="background-color:'+sessionStorage.getItem('navBackgroundColor')+' !important;">'+iconHtml+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">'+ PageName[x] +'</span></a>');

		}
		x=x+1;
	}
	setTimeout(function (){
		if(sessionStorage.getItem('hideHeaderBar') && sessionStorage.getItem('hideHeaderBar') == "true")
		{
			$( "[data-role='header']" ).remove();
			$( "[data-role='header']" ).toolbar('destroy');
			$.mobile.resetActivePageHeight();
		}
		$.mobile.changePage('#page1',{transition:'slide'});
	},10);
}
function Login()
{
	$("#logo").show();
	$("#mainback").hide();
	var wsUrl = "http://"+window.localStorage.getItem("reseller")+"/services/app-user-soap#checkUserActiveStatus/";
	var email = document.getElementsByName('EmailId')[0].value;
	var password = document.getElementsByName('Password')[0].value;

	//chages by amritesh on 12-01-16
	localStorage.setItem('loyalityCardUserEmail', email);
	window.localStorage.setItem("loginemail",email);
	window.localStorage.setItem("loginpassword",password);

	if(email == "" || password == "")
	{
		//alert( 'Email id and password must not be blank.');
		navigator.notification.alert(
				emailandpasswordmustnotbeblank,
				alertDismissed,
				aalert_code,
				ookk_code
				);
	}else{
		window.localStorage.setItem("fooduserid",email);
		var autoStatus=localStorage.getItem("emailverification");

		var soapRequest =
				'<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><checkUserActiveStatus xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/app-user-soap#checkUserActiveStatus/\"><email>'+email+'</email><password>'+password+'</password><appId>'+window.localStorage.getItem("applicationID")+'</appId><authoStatus>'+autoStatus+'</authoStatus></checkUserActiveStatus></soap:Body></soap:Envelope>';

		console.log(" " + soapRequest);
		jQuery.support.cors = true;
		$.ajax({
			type: "POST",
			url: wsUrl,
			contentType: "text/xml",
			dataType: "text",
			data: soapRequest,
			success: processSuccessxmlupdatelogin,
			error: processErrorxmlupdatelogin
		});

	}
}

function removeFocusall(object)
{
	if(localStorage.getItem("oldidsignup"))
	{
		var textboxtemp = document.getElementById(localStorage.getItem("oldidsignup"));
		textboxtemp.style.removeProperty('border');
	}
	localStorage.setItem("oldidsignup", object.id);
	var textbox = document.getElementById(object.id);
	textbox.style.removeProperty('border');
	var textbox2=document.getElementById('customname');
	textbox2.style.removeProperty('border');

}
function setFocuscustom(elementID){
	if(localStorage.getItem("oldidsignup"))
	{
		var textboxtemp = document.getElementById(localStorage.getItem("oldidsignup"));
		textboxtemp.style.removeProperty('border');
	}
	localStorage.setItem("oldidsignup", elementID);
	var textbox = document.getElementById(elementID);
	textbox.style.border = "1px solid #FE2E2E";
	textbox.focus();
	textbox.scrollIntoView();

}


function processSuccessxmlupdatelogin(data, status, req)
{
	var xml = req.responseText;
	xmlDoc = $.parseXML( xml );
	$xml = $( xmlDoc );
	$title = $xml.find("return");
	var status=$title.text();
	console.log("status>>>>>>>>"+status);
	if(validateForm1(status))
	{
		var completionCallback1 = function(usercredential) {
		}
		var cancelCallback1 = function(reason) {
		}
		console.log("status krishna>>>>>>>>"+status);
		toaster.setLoginStatus("true");
		localStorage.setItem("userLoginStatus","true");
		toaster.setUserStatus("true");
		//window.plugins.nativevaluetojs.fetch({uName:"True",pass:"",chk:"",loginorlogout:'applogin'}, completionCallback1,cancelCallback1);
		$('.appypie-login').remove();
		if(window.localStorage.getItem("layout")=="slidemenu"){
			$(".ui-btn-left").show();
			snapper.enable();
		}

		if(sessionStorage.getItem('fromInnerPages') && sessionStorage.getItem('fromInnerPages') == 'true')
		{
			openPage(sessionStorage.getItem("pageIndex"));
		}
		else
		{

		try{
		    var notification_status=toaster.getFromNotification();
             console.log("notification_status->"+notification_status);
             if(notification_status=="true"){
              console.log("notification_status->"+notification_status);
              toaster.openNotificationListActivity();
                                           }
		}
	    catch(err)
	    {
		//loadData();
	    }
			loadData();
		}
		}else
	{

		var name = localStorage.getItem("fbuserName");
		var email = localStorage.getItem("fbemailId");
		var fbid = localStorage.getItem("fbId");
		console.log("Name...."+name+"  Email "+email+".... FBUserID "+fbid);
		if(fbid == null || fbid == "")
		{
			console.log("krishna false");
			localStorage.setItem("userLoginStatus","false");
			localStorage.removeItem("fooduserid");
			toaster.setLoginStatus("false");
			toaster.setUserStatus('');
			navigator.notification.alert(
                                     ''+invalidusernameorpassword,
                                     alertDismissed,
                                     aalert_code,
                                     ookk_code
                                     );
		}
		else
		{

			if(email != null || email != ""){
				console.log("Facebook Id and Email Id  available.... Signup Process");
			    signupforFB(fbid, "1");
			}
			else{
				console.log("Facebook Id available.... Email Id Missing registerFBUser Call...");
				registerFBUser(name, email, fbid);
				window.localStorage.setItem("fbemailId","");
				window.localStorage.setItem("fbuserName","");
			//	window.localStorage.setItem("fbid","");
			}


		}


	}


}
function processErrorxmlupdatelogin(data, status, req)
{
	localStorage.removeItem("fooduserid");
	console.log("Error : "+ data.responseText);
}

function checkRegisterOption() {

	var wsUrl = "http://"+window.localStorage.getItem("reseller")+"/services/app-user-soap#checkAppRegister/";
	var soapRequest =
			'<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><checkAppRegister xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/app-user-soap#checkAppRegister/\"><appid>'+window.localStorage.getItem("applicationID")+'</appid></checkAppRegister></soap:Body></soap:Envelope>';

	console.log(" " + soapRequest);

	$.ajax({
		type: "POST",
		url: wsUrl,
		contentType: "text/xml",
		dataType: "text",
		data: soapRequest,
		success:checkAppRegisterSuccess,
		error:checkAppRegisterError
	});
}

function checkAppRegisterSuccess(data, status, req){
	var xml = req.responseText;
	xmlDoc = $.parseXML(xml);
	$xml = $(xmlDoc);
	$status = $xml.find("return");
	var registerStatus=$status.text();

	//$(".fb-div").show();

	if(registerStatus!="true") {
		localStorage.setItem("registerStatus","false");
		$(".fb-div").show();
		$("#signupdiv").show();
	}
	else {
	$("#signupdiv").hide();
		localStorage.setItem("registerStatus","true");
	}
}
function checkAppRegisterError(data, status, req) {

}

function signup()
{
	//alert("signup event");
	$.mobile.changePage('#register');
	//$("#logo").show();
	$("#reload").hide();
	document.getElementById("signS").innerHTML=  localStorage.getItem("signuptext");
	document.getElementById("signN").innerHTML=  localStorage.getItem("name")+'<sup class="red-astrick">*</sup>';
	document.getElementById("signE").innerHTML=  localStorage.getItem("email")+'<sup class="red-astrick">*</sup>';
	document.getElementById("signP").innerHTML= localStorage.getItem("password")+'<sup class="red-astrick">*</sup>';
	document.getElementById("signCP").innerHTML= localStorage.getItem("confirmpwd")+'<sup class="red-astrick">*</sup>';
	document.getElementById("signSU").innerHTML= localStorage.getItem("signupbtntext");
	document.getElementById("signL").innerHTML= localStorage.getItem("signintext");
	document.getElementById("signCN").innerHTML= localStorage.getItem("company");
	document.getElementById("signA").innerHTML= localStorage.getItem("alreadyaccounttext");
	document.getElementById("signPn").innerHTML= localStorage.getItem("phoneNo")+'<sup class="red-astrick"></sup>';

	document.getElementById("signCN").style.display="none";
	document.getElementById("CompanyName").style.display="none";

	var loginHtml ='<div data-role="popup" id="popup-outside-page12" class="newsstand-popup-div cart-MSG" data-theme="b"><a id="closePop" onclick="closePopup12();">X</a><h2 id="subscription_title"></h3><div id="monthly" class="sub_plan">USD<span>20</span>/Month</div><div id="yearly" class="sub_plan">USD<span>20</span>/Year</div><div id="onetime" class="sub_plan">USD<span>20</span>/Time</div></div>';
	$("#contentHolder12").append(loginHtml);
	$('#popup-outside-page12').popup({ positionTo: "window" });

	var signupPageId;
	if(masterData.getElementsByTagName('loginfield')[0])
	{
		console.log("loginPageDetails() loginfield xml found");
		signupPageId = $(masterData).find("loginfield").find("loginPageId").text();
	}

	//for getting custom form during signup
	var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/app-user-soap#getCustomLoginForm";
	var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getCustomLoginForm xmlns=\"http://'+localStorage.getItem("reseller")+'/services/app-user-soap#getCustomLoginForm\"><appId>'+window.localStorage.getItem("applicationID")+'</appId><pageId>'+signupPageId+'</pageId></getCustomLoginForm></soap:Body></soap:Envelope>';
	console.log("custom register" + soapRequest);
	$.ajax({
		type: "POST",
		url: wsUrl,
		contentType: "text/xml",
		dataType: "text",
		data: soapRequest,
		success: processSuccessxmlRegisterForm,
		error: processErrorxmlRegisterForm
	});
}

function processSuccessxmlRegisterForm(data, status, req)
{


	var strJSON = $(req.responseText).find("return").text();
	console.log("signup custom field>>>>"+strJSON);
	var obj = JSON.parse(strJSON);
	var hellocalc="";
	for(var j=0;j<obj.length;j++)
	{
		RegcustomElementsType[j]=obj[j].fieldType;
		RegcustomElementsLabel[j]=obj[j].fieldLebal;
	}
	for(var i=0;i<obj.length;i++){
		if(obj[i].fieldType=="name" || obj[i].fieldType=="email" || obj[i].fieldType=="phone" || obj[i].fieldType=="state" || obj[i].fieldType=="text"){
			if(obj[i].mandatory==1){
				RegcustomElementsMandatory[i]=1;
				hellocalc+="<div style='clear: both;' class='login-feald'><label class='form-builder-form-label'><span class='field_lebal_3'>"+obj[i].fieldLebal+"</span> <sup class='red-astrick'>*</sup></label><div class='add_field_3'><input name='name' id="+i+" type='text' class='form-builder-form-input' data-role='none' onclick='removeFocusall(this)'></div></div>";
			}else{
				RegcustomElementsMandatory[i]=0;

				hellocalc+="<div style='clear: both;' class='login-feald'><label class='form-builder-form-label'><span class='field_lebal_3'>"+obj[i].fieldLebal+"</span> </label><div class='add_field_3'><input name='name' id="+i+" type='text' class='form-builder-form-input' data-role='none' onclick='removeFocusall(this)'></div></div>";

			}

		}
		else if(obj[i].fieldType=="date"){
		sessionStorage.setItem("ActualSelectorFor"+i, obj[i].fieldLebal);
			if(obj[i].mandatory==1){
				RegcustomElementsMandatory[i]=1;
				hellocalc+="<div class='login-feald datepicker'><label>"+obj[i].fieldLebal+"<sup class='red-astrick'>*</sup></label><input data-role='none' type='text' id="+i+" class='' onclick='show1(this.id);' readonly=''><a href='#'><img src='images/calendar.png' onclick='show1("+i+");' border='0'></a></div>";
			}else{
				RegcustomElementsMandatory[i]=0;
				hellocalc+="<div class='login-feald datepicker'><label>"+obj[i].fieldLebal+"</label><input data-role='none' type='text' id="+i+" class='' onclick='show1(this.id);' readonly=''><a href='#'><img src='images/calendar.png' onclick='show1("+i+");' border='0'></a></div>";
			}
		}
		else if(obj[i].fieldType=="time"){
			if(obj[i].mandatory==1){
				RegcustomElementsMandatory[i]=1;
				hellocalc+="<div class='login-feald datepicker'><label>"+obj[i].fieldLebal+"<sup class='red-astrick'>*</sup></label><input data-role='none' type='text' id="+i+" class='clockpicker' onclick='time1(this.id)' readonly><a href='#'><img src='images/clock.png' onclick='time1("+i+");' border='0'></a></div>";
			}else{
				RegcustomElementsMandatory[i]=0;
				hellocalc+="<div class='login-feald datepicker'><label>"+obj[i].fieldLebal+"</label><input data-role='none' type='text' id="+i+" class='clockpicker' onclick='time1(this.id)' readonly ><a href='#'><img src='images/clock.png' onclick='time1("+i+");' border='0'></a></div>";
			}

		}
		else if(obj[i].fieldType=="gender"){
			if(obj[i].mandatory==1){
				RegcustomElementsMandatory[i]=1;
				hellocalc+="<div class='login-feald'><label>"+obj[i].fieldLebal+" <sup class='red-astrick'>*</sup></label><div class=''><select data-role='none' name='Country_2' id="+i+" onclick='removeFocusall(this)' class='select-box'><option selected='selected' value='Select Gender'>"+sselectgender_code+"</option><option value='Male'>"+mmale_code+"</option><option value='Female'>"+femalee_code+"</option></select></div></div>";
			}else{
				RegcustomElementsMandatory[i]=0;
				hellocalc+="<div class='login-feald'><label>"+obj[i].fieldLebal+" </label><div class=''><select data-role='none' name='Country_2' id="+i+" onclick='removeFocusall(this)' class='select-box'><option selected='selected' value='Select Gender'>"+sselectgender_code+"</option><option value='Male'>"+mmale_code+"</option><option value='Female'>"+femalee_code+"</option></select></div></div>";


			}
		}
		else if(obj[i].fieldType=="country"){
			if(obj[i].mandatory==1){
				RegcustomElementsMandatory[i]=1;
				hellocalc+=getCountryFormBuilder(i);
			}else{
				RegcustomElementsMandatory[i]=0;
				hellocalc+=getCountryFormBuilder2(i);
			}

		}
		else if(obj[i].fieldType=="textarea"){
			if(obj[i].mandatory==1){
				RegcustomElementsMandatory[i]=1;
				hellocalc+=" <div class='login-feald'><label>"+obj[i].fieldLebal+"<sup class='red-astrick'>*</sup></label><p><textarea id="+i+" data-role='none' onclick='removeFocusall(this)'></textarea></p></div>";
			}else{
				RegcustomElementsMandatory[i]=0;
				hellocalc+=" <div class='login-feald'><label>"+obj[i].fieldLebal+"</label><p><textarea id="+i+" data-role='none'></textarea></p></div>";
			}
		}
		else if(obj[i].fieldType=="checkbox"){
			if(obj[i].mandatory==1){
				RegcustomElementsMandatory[i]=1;
				hellocalc+="<div class='login-feald'><label>"+obj[i].fieldLebal+"<sup class='red-astrick'>*</sup></label><div class=''>";
			}else{
				RegcustomElementsMandatory[i]=0;
				hellocalc+="<div class='login-feald'><label>"+obj[i].fieldLebal+"</label><div class=''>";
			}
			var tempName=obj[i].fieldLebal;

			for(var j=0;j<obj[i].countSubField;j++){

				if(obj[i][j+1].defaulSetOption==1){
					hellocalc+="<div class='optional-input'><input data-role='none' name="+tempName.replace(/[^A-Za-z]+/g, 'X')+" id="+i+" class='secondCheckLebal' type='checkbox' checked value='"+obj[i][j+1].subFieldLebal+"'><label>"+obj[i][j+1].subFieldLebal+"</label></div>";

				}else{

					hellocalc+="<div class='optional-input'><input data-role='none' name="+tempName.replace(/[^A-Za-z]+/g, 'X')+" id="+i+" class='secondCheckLebal' type='checkbox'  value='"+obj[i][j+1].subFieldLebal+"'><label>"+obj[i][j+1].subFieldLebal+"</label></div>";
				}

			}

			hellocalc+="</div></div>";

		}
		else if(obj[i].fieldType=="radio"){

			if(obj[i].mandatory==1){
				RegcustomElementsMandatory[i]=1;
				hellocalc+="<div class='login-feald'><label>"+obj[i].fieldLebal+"<sup class='red-astrick'>*</sup></label><div class=''>";
			}
			else{
				RegcustomElementsMandatory[i]=0;
				hellocalc+="<div class='login-feald'><label>"+obj[i].fieldLebal+"</label><div class=''>";
			}
			var tempName=obj[i].fieldLebal;
			for(var j=0;j<obj[i].countSubField;j++){

				if(obj[i][j+1].defaulSetOption==1){
					hellocalc+="<div class='optional-input'><input data-role='none' name="+tempName.replace(/[^A-Za-z]+/g, 'X')+" id="+i+" class='secondCheckLebal' type='radio' checked value='"+obj[i][j+1].subFieldLebal+"'><label>"+obj[i][j+1].subFieldLebal+"</label></div>";

				}else{
					hellocalc+="<div class='optional-input'><input data-role='none' name="+tempName.replace(/[^A-Za-z]+/g, 'X')+" id="+i+" class='secondCheckLebal' type='radio' value='"+obj[i][j+1].subFieldLebal+"'><label>"+obj[i][j+1].subFieldLebal+"</label></div>";
				}

			}

			hellocalc+="</div></div>";

		}
		else if(obj[i].fieldType=="select"){
			if(obj[i].mandatory==1){
				RegcustomElementsMandatory[i]=1;
				hellocalc+="<div class='login-feald'><label>"+obj[i].fieldLebal+" <sup class='red-astrick'>*</sup></label><div class=''><select data-role='none' name='Country_2' id="+i+" onclick='removeFocusall(this)' class='select-box'>";
			}
			else{
				RegcustomElementsMandatory[i]=0;
				hellocalc+="<div class='login-feald'><label>"+obj[i].fieldLebal+" </label><div class=''><select data-role='none' name='Country_2' id="+i+" onclick='removeFocusall(this)' class='select-box'>";
			}
			for(var j=0;j<obj[i].countSubField;j++){
				if(obj[i][j+1].defaulSetOption==1){
					hellocalc+="<option selected='selected' value="+obj[i][j+1].subFieldValue+">"+obj[i][j+1].subFieldLebal+"</option>";
				}else{
					hellocalc+="<option  value="+obj[i][j+1].subFieldValue+">"+obj[i][j+1].subFieldLebal+"</option>";
				}
			}
			hellocalc+="</select></div></div>";

		}else if(obj[i].fieldType=="uploadPicture"){
			if(obj[i].mandatory==1){
				RegcustomElementsMandatory[i]=1;
				uploadFileCount++;
				hellocalc+="<div style='clear: both;' class='login-feald'><label class=''>"+obj[i].fieldLebal+"<sup class='red-astrick'>*</sup></label><div class='add-file'><span id="+i+" onclick='selectFile(this)' class='browser'>Attach File</span></div></div>";
			}else{
				RegcustomElementsMandatory[i]=0;
				uploadFileCount++;
				hellocalc+="<div style='clear: both;' class='login-feald'><label class=''>"+obj[i].fieldLebal+"</label><div class='add-file'><span id="+i+" onclick='selectFile(this)' class='browser'>Attach File</span></div></div>";
			}

		}

	}

	$("#customRegisterField").empty();
	$("#customRegisterField").append(hellocalc);
	//appendHtml("<div class='page-text'>"+hellocalc+"</div>",2,1);
}

function processErrorxmlRegisterForm(response, textStatus, errorThrown)
{
	console.log("Error : " + JSON.stringify(response));
	console.log("Error : " + textStatus);
	console.log("Error : " + errorThrown.responseText);

}


function Sign()
{
	freeSignup();
}

function checkPaymentMode()
{
	var totalPaymentmode=0;
	var defaultCurrency="USD";
	//var monthText="";
 console.log("checkPaymentMode method "+localStorage.getItem("paymentMethod"));
	var type = localStorage.getItem("SignUp");
	 if(type == "FB")
	 {
		 console.log("Sign Up for FB USER.... FB ID Not Null "+localStorage.getItem("fbId"));
		 checkPaymentModeForFB();
		//$("#contentHolder1").append(loginHtml);
		// $("#popup-outside-page123").popup({ positionTo: "window" });
	 }else{
		document.getElementById("subscription_title").innerHTML="Choose Your Payment Type:";
		if(localStorage.getItem("paymentMethod")=='paypal_express')
		{
			if(localStorage.getItem("subsPriceMonthly").trim()!="")
			{
				totalPaymentmode++;
				if(localStorage.getItem("subsCurrencyMonthly"))
				{
					defaultCurrency=localStorage.getItem("subsCurrencyMonthly");
				}
				var monthText='<div class="sub_plan" onClick="inAppSignup(\''+'monthlySub'+'\',\''+localStorage.getItem("subsPriceMonthly").trim()+'\',\''+defaultCurrency+'\');">'+defaultCurrency+' <span>'+localStorage.getItem("subsPriceMonthly").trim()+'</span>/Month</div>';
				document.getElementById("monthly").innerHTML=monthText;
			}
			else
			{
				document.getElementById("monthly").style.display = 'none';
			}

			if( localStorage.getItem("subsPriceYearly").trim()!="")
			{
				totalPaymentmode++;
				if(localStorage.getItem("subsCurrencyYearly"))
				{
					defaultCurrency=localStorage.getItem("subsCurrencyYearly");
				}

				var yearText='<div class="sub_plan" onClick="inAppSignup(\''+'yearlySub'+'\',\''+localStorage.getItem("subsPriceYearly").trim()+'\',\''+defaultCurrency+'\');">'+defaultCurrency+' <span>'+localStorage.getItem("subsPriceYearly").trim()+'</span>/Year</div>';
				document.getElementById("yearly").innerHTML=yearText;
			}
			else
			{
				document.getElementById("yearly").style.display = 'none';
			}

			if(localStorage.getItem("editionPrice").trim()!="")
			{
				totalPaymentmode++;
				if(localStorage.getItem("editionCurrency"))
			{
				defaultCurrency=localStorage.getItem("editionCurrency");
			}
			var editionText='<div class="sub_plan" onClick="inAppSignup(\''+'onetimepayment'+'\',\''+localStorage.getItem("editionPrice").trim()+'\',\''+defaultCurrency+'\');">'+defaultCurrency+' <span>'+localStorage.getItem("editionPrice").trim()+'</span>/Time</div>';
			document.getElementById("onetime").innerHTML=editionText;
		}
		else
		{
			document.getElementById("onetime").style.display = 'none';
		}
	}
	else
	{
		if(localStorage.getItem("inapMonthlySub") && localStorage.getItem("inapMonthlySub").trim()!="" &&  localStorage.getItem("subsPriceMonthly").trim()!="")
		{
			totalPaymentmode++;
			if(localStorage.getItem("subsCurrencyMonthly"))
			{
				defaultCurrency=localStorage.getItem("subsCurrencyMonthly");
			}
			var monthText='<div class="sub_plan" onClick="inAppSignup(\''+'monthlySub'+'\',\''+localStorage.getItem("subsPriceMonthly").trim()+'\',\''+defaultCurrency+'\');">'+defaultCurrency+' <span>'+localStorage.getItem("subsPriceMonthly").trim()+'</span>/Month</div>';
			document.getElementById("monthly").innerHTML=monthText;
		}
		else
		{
			document.getElementById("monthly").style.display = 'none';
		}

		if(localStorage.getItem("inapYearlySub") && localStorage.getItem("inapYearlySub").trim()!="" &&  localStorage.getItem("subsPriceYearly").trim()!="")
		{
			totalPaymentmode++;
			if(localStorage.getItem("subsCurrencyYearly"))
			{
				defaultCurrency=localStorage.getItem("subsCurrencyYearly");
			}

			var yearText='<div class="sub_plan" onClick="inAppSignup(\''+'yearlySub'+'\',\''+localStorage.getItem("subsPriceYearly").trim()+'\',\''+defaultCurrency+'\');">'+defaultCurrency+' <span>'+localStorage.getItem("subsPriceYearly").trim()+'</span>/Year</div>';
			document.getElementById("yearly").innerHTML=yearText;
		}
		else
		{
			document.getElementById("yearly").style.display = 'none';
		}

		if(localStorage.getItem("anppAddProductId") && localStorage.getItem("anppAddProductId").trim()!="" &&  localStorage.getItem("editionPrice").trim()!="")
		{
			totalPaymentmode++;
			if(localStorage.getItem("editionCurrency"))
			{
				defaultCurrency=localStorage.getItem("editionCurrency");
			}
			var editionText='<div class="sub_plan" onClick="inAppSignup(\''+'onetimepayment'+'\',\''+localStorage.getItem("editionPrice").trim()+'\',\''+defaultCurrency+'\');">'+defaultCurrency+' <span>'+localStorage.getItem("editionPrice").trim()+'</span>/Time</div>';
			document.getElementById("onetime").innerHTML=editionText;
		}
		else
		{
			document.getElementById("onetime").style.display = 'none';
		}
	}


	if(parseFloat(totalPaymentmode)>0)
	{
		console.log("popup will open for payment");
		$('#popup-outside-page12').popup('open');
	}
	}
}

function freeSignup()
{
	len=RegcustomElementsType.length;
	var name = document.getElementsByName('name')[0].value;
	var email = document.getElementsByName('email')[0].value;
	var contact = document.getElementsByName('contact')[0].value;
	var password = document.getElementsByName('password')[0].value;
	var cpassword = document.getElementsByName('cpassword')[0].value;

	sessionStorage.setItem("signUpName",name);
	sessionStorage.setItem("signUpemail",email);
	sessionStorage.setItem("signUpcontact",contact);
	sessionStorage.setItem("signUppassword",password);


	if(name== null || name== '')
	{
		navigator.notification.alert(
				signupenternamealert,
				alertDismissed,
				aalert_code,
				ookk_code
				);
		return false;
	}
	else if(email== null || email== '' || !validateForm1(email))
	{
		navigator.notification.alert(
				signupemailid,
				alertDismissed,
				aalert_code,
				ookk_code
				);
		return false;
	}
	else if(password== null || password== '' )
	{
		navigator.notification.alert(
				signuppasswords,
				alertDismissed,
				aalert_code,
				ookk_code
				);
		return false;
	}
	else if(password.length<7)
	{
		navigator.notification.alert(
				signuppasswordshouldbesevenchar,
				alertDismissed,
				aalert_code,
				ookk_code
				);
		setFocuscustom("password");
		return false;
	}
	else if(password != cpassword)
	{
		navigator.notification.alert(
				signuppassworddonotmatch,
				alertDismissed,
				aalert_code,
				ookk_code
				);
		return false;
	}
	else{
		for(var i=0;i<len;i++){
			if(RegcustomElementsType[i]=="name" || RegcustomElementsType[i]=="email" || RegcustomElementsType[i]=="phone" || RegcustomElementsType[i]=="state" || RegcustomElementsType[i]=="text"){

				var txt=document.getElementById(i).value.trim();

				if (txt == '' && RegcustomElementsMandatory[i]==1) {

					alertPopupforRegister(RegcustomElementsLabel[i]);
					/* openPopup();
                         document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                         document.getElementById("errorPopup").style.display = 'block';
                         document.getElementById("scuccessPopup").style.display = 'none';
                         setFocus(i);*/
					setFocuscustom(i);
					return false;
				}
				else if(RegcustomElementsType[i]=="phone")
				{

					if(!IsNumeric1(txt))
					{
						navigator.notification.alert(
								'*"'+RegcustomElementsLabel[i]+'"'+ notvvvalid_code,
								alertDismissed,
								aalert_code,
								ookk_code
								);
						/*openPopup();
    	   			document.getElementById("errorPopup").innerHTML=ErrorPhoneField;
                  document.getElementById("errorPopup").style.display = 'block';
                  document.getElementById("scuccessPopup").style.display = 'none';*/
						setFocuscustom(i);
						return false;
					}


				}

				else if(RegcustomElementsType[i]=="email")
				{

					if(txt!=""&& !validateForm1(txt))
					{

						navigator.notification.alert(
								'*"'+RegcustomElementsLabel[i]+'" '+ nnnotvalidemail_code,
								alertDismissed,
								aalert_code,
								ookk_code
								);
						/*openPopup();
    			document.getElementById("errorPopup").innerHTML=ErrorEmailField;
    			document.getElementById("errorPopup").style.display = 'block';
    			document.getElementById("scuccessPopup").style.display = 'none';*/
						setFocuscustom(i);
						return false;
					}
					else{

						userEmail=txt;
					}


				}

				RegcustomElementsValue[i]=txt;
			}
			else if(RegcustomElementsType[i]=="date"){

				var txt=document.getElementById(i).value;

				if (txt == '' && RegcustomElementsMandatory[i]==1) {
					alertPopupforRegister(RegcustomElementsLabel[i]);
					/*openPopup();
                         document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                         document.getElementById("errorPopup").style.display = 'block';
                         document.getElementById("scuccessPopup").style.display = 'none';*/
					setFocuscustom(i);
					return false;
				}
				RegcustomElementsValue[i]=txt;

			}
			else if(RegcustomElementsType[i]=="time"){

				var txt=document.getElementById(i).value.trim();

				if (txt == '' && RegcustomElementsMandatory[i]==1) {
					alertPopupforRegister(RegcustomElementsLabel[i]);
					/*	openPopup();
                         document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                         document.getElementById("errorPopup").style.display = 'block';
                         document.getElementById("scuccessPopup").style.display = 'none';*/
					setFocuscustom(i);
					return false;
				}
				RegcustomElementsValue[i]=txt;

			}
			else if(RegcustomElementsType[i]=="textarea"){

				var txt=document.getElementById(i).value.trim();

				if (txt == '' && RegcustomElementsMandatory[i]==1) {
					alertPopupforRegister(RegcustomElementsLabel[i]);

					/*openPopup();
                         document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                         document.getElementById("errorPopup").style.display = 'block';
                         document.getElementById("scuccessPopup").style.display = 'none';*/
					setFocuscustom(i);
					return false;
				}
				RegcustomElementsValue[i]=txt;

			}
			else if(RegcustomElementsType[i]=="gender"){

				var txt=document.getElementById(i).value.trim();

				if (txt == 'Select Gender' && RegcustomElementsMandatory[i]==1) {
					alertPopupforRegister(RegcustomElementsLabel[i]);
					/*openPopup();
                         document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                         document.getElementById("errorPopup").style.display = 'block';
                         document.getElementById("scuccessPopup").style.display = 'none';*/
					setFocuscustom(i);
					return false;
				}
				RegcustomElementsValue[i]=txt;

			}
			else if(RegcustomElementsType[i]=="country"){

				var txt=document.getElementById(i).value.trim();
				//alert(txt);
				if (txt == 'Select Country' && RegcustomElementsMandatory[i]==1) {
					alertPopupforRegister(RegcustomElementsLabel[i]);
					/*openPopup();
                         document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                         document.getElementById("errorPopup").style.display = 'block';
                         document.getElementById("scuccessPopup").style.display = 'none';*/
					setFocuscustom(i);
					return false;
				}

				RegcustomElementsValue[i]=txt;
			}
			else if(RegcustomElementsType[i]=="checkbox"){

				var favorite = [];

				var tempNam=RegcustomElementsLabel[i];

				$.each($("input[name="+ tempNam.replace(/[^A-Za-z]+/g, 'X')+"]:checked"), function(){

					favorite.push($(this).val());

				});
				// alert("My favourite sports are: " + favorite.join(", "));

				if(favorite.length == 0 && RegcustomElementsMandatory[i]==1){
					alertPopupforRegister(RegcustomElementsLabel[i]);
					/*openPopup();
                         document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                         document.getElementById("errorPopup").style.display = 'block';
                         document.getElementById("scuccessPopup").style.display = 'none';*/
					return false;

				}
				RegcustomElementsValue[i]=favorite.join(",");


			}  else if(RegcustomElementsType[i]=="radio"){

				var favorite = [];
				var tempNam=RegcustomElementsLabel[i];
				$.each($("input[name="+tempNam.replace(/[^A-Za-z]+/g, 'X')+"]:checked"), function(){
					favorite.push($(this).val());
				});

				if(favorite.length == 0 && RegcustomElementsMandatory[i]==1){
					alertPopupforRegister(RegcustomElementsLabel[i]);
					/*openPopup();
                         document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                         document.getElementById("errorPopup").style.display = 'block';
                         document.getElementById("scuccessPopup").style.display = 'none';*/
					return false;

				}
				RegcustomElementsValue[i]=favorite[0];
				// alert("My favourite Radio are: " + favorite.join(", "));



			}
			else if(RegcustomElementsType[i]=="select"){


				var e = document.getElementById(i);
				var txt = e.options[e.selectedIndex].text;

				if (txt == '' && RegcustomElementsMandatory[i]==1) {
					alertPopupforRegister(RegcustomElementsLabel[i]);
					/*openPopup();
                         document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                         document.getElementById("errorPopup").style.display = 'block';
                         document.getElementById("scuccessPopup").style.display = 'none';*/
					setFocuscustom(i);
					return false;
				}
				RegcustomElementsValue[i]=txt;




			}
			else if(RegcustomElementsType[i]=="uploadPicture"){
				var txt=document.getElementById(i).innerHTML.trim();

				if (txt == 'Attach File' && RegcustomElementsMandatory[i]==1) {
					alertPopupforRegister(RegcustomElementsLabel[i]);
					/*openPopup();
                         document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                         document.getElementById("errorPopup").style.display = 'block';
                         document.getElementById("scuccessPopup").style.display = 'none';*/
					setFocuscustom(i);
					return false;
				}
				// fileName=txt;

				if(txt == 'Attach File')
				{

					RegcustomElementsValue[i]='';
				}
				else{
					RegcustomElementsValue[i]=txt;

				}


			}

		}

	}



	var jsonData = {};
	var jsonData2 = {};
	var jsonData3={};
	for(var k=0;k<len;k++)
	{
		if(typeof RegcustomElementsLabel[k] =='undefined')
		{


		}
		else{

			jsonData3[k]=RegcustomElementsType[k];
			jsonData2[k]=RegcustomElementsLabel[k].replace('&','and');
			jsonData[k] = RegcustomElementsValue[k];
		}
	}

	var mediaFiles = Regglobalimageurl;
	RegfileName = Math.random()+'_'+Regglobalimageurl.split("/").pop();
	var fileURI = mediaFiles;

	sessionStorage.setItem("uploadFileCount",uploadFileCount);
	sessionStorage.setItem("globalExactPath",globalExactPath);
	sessionStorage.setItem("jsonData",JSON.stringify(jsonData));
	sessionStorage.setItem("jsonData2",JSON.stringify(jsonData2));
	sessionStorage.setItem("jsonData3",JSON.stringify(jsonData3));
	checkForPaymentOrRegister(name,email,password,contact);
}

function checkForPaymentOrRegister(name,email,password,contact,profileFBID)
{
	console.log("signup paymentMethodkk=="+localStorage.getItem("paymentMethod"));
	if(localStorage.getItem("paymentMethod"))
	{
		console.log("signup paymentMethod=="+localStorage.getItem("paymentMethod"));
		var paymentData=localStorage.getItem("paymentMethod");
		console.log("paymentData===="+paymentData);
		if(paymentData=="free")
		{
			console.log("paymentData===="+localStorage.getItem("paymentMethod"));
			startRegistration(name,email,password,contact,profileFBID);
		}
		else if(localStorage.getItem("paymentMethod")=="paypal_express" || localStorage.getItem("paymentMethod")=="inApp")
		{
			checkPaymentMode();
		}
	}
	else
	{
		startRegistration(name,email,password,contact,profileFBID);
	}
}


function paypalExpressSignup(paymentAmt,currency,paymentType)
{

	if(!localStorage.getItem('businessId'))
	{
		alertPopUp(aalert_code+'!',ppleaseprovidepaypalemailid_code);
	}
	else if(!paymentAmt)
	{
		alertPopUp(aalert_code+'!',ppleaseprovidepaymentmount_code);
	}
	else if(parseFloat(paymentAmt)<0)
	{
		alertPopUp(aalert_code+'!',ppleaseprovidepaymentmount_code);
	}
	else
	{
		var email='';
		if(!localStorage.getItem("fooduserid"))
		{
			email='';
		}
		else
		{
			email=localStorage.getItem("fooduserid");
		}
		var paypalAddFormHtml='';
		 if(paymentType=="onetimepayment")
			{
				console.log("Payment  onetime Subscription...");
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
				<input type="hidden" name="email" value="'+email+'">\
				<!-- Specify details about the item that buyers will purchase. -->\
				<input type="hidden" name="item_name" value="'+ppaymentfor_code+''+localStorage.getItem("AppName")+'('+localStorage.getItem("applicationID")+')">\
				<input type="hidden" name="amount" value="'+parseFloat(paymentAmt).toFixed(2)+'">\
				<input type="hidden" name="quantity" value="1">\
				<input name=Õ¢nÔ value=Õ�ppyPie_SPÔ type=Ô hiddenÓ¾\
				<input type="hidden" name="currency_code" value="'+currency+'">\
				<input type="hidden" name="custom" value="1">';
				}
			else if(paymentType=="yearlySub")
			{
				console.log("Payment  Yearly Subscription...");
				paypalAddFormHtml='<!-- Specify a Buy Now button. -->\
				<input type="hidden" name="cmd" value="_xclick-subscriptions">\
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
				<input type="hidden" name="email" value="'+email+'">\
				<!-- Specify details about the item that buyers will purchase. -->\
				<input type="hidden" name="item_name" value="'+ppaymentfor_code+''+localStorage.getItem("AppName")+'('+localStorage.getItem("applicationID")+')">\
				<input type="hidden" name="amount" value="">\
				<input type="hidden" name="quantity" value="1">\
				<input type="hidden" name="currency_code" value="'+currency+'">\
				<input type="hidden" name="a3" value="'+parseFloat(paymentAmt).toFixed(2)+'">\
	            <input type="hidden" name="p3" value="1">\
	            <input type="hidden" name="t3" value="Y">\
				<input type="hidden" name="src" value="1">\
				<input type="hidden" name="sra" value="1">\
				<input type="hidden" name="custom" value="1">';
			}
			else if(paymentType=="monthlySub")
			{
				console.log("Payment  Monthly Subscription...");
				paypalAddFormHtml='<!-- Specify a Buy Now button. -->\
				<input type="hidden" name="cmd" value="_xclick-subscriptions">\
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
				<input type="hidden" name="email" value="'+email+'">\
				<!-- Specify details about the item that buyers will purchase. -->\
				<input type="hidden" name="item_name" value="Payment for '+localStorage.getItem("AppName")+'('+localStorage.getItem("applicationID")+')">\
				<input type="hidden" name="amount" value="">\
				<input type="hidden" name="quantity" value="1">\
				<input type="hidden" name="currency_code" value="'+currency+'">\
				<input type="hidden" name="a3" value="'+parseFloat(paymentAmt).toFixed(2)+'">\
	            <input type="hidden" name="p3" value="1">\
	            <input type="hidden" name="t3" value="M">\
				<input type="hidden" name="src" value="1">\
				<input type="hidden" name="sra" value="1">\
				<input type="hidden" name="custom" value="1">';
			}


		//		var paypalIdHtml= '<!DOCTYPE HTML><html><body onload="ClickButtonPaypal();"><form action="https://www.paypal.com/cgi-bin/webscr" method="post"><!-- Identify your business so that you can collect the payments. --><input type="hidden" name="business" value="'+localStorage.getItem('businessId')+'">';

		var paypalIdHtml;
		if(localStorage.getItem("owneremail")=="mayankr@onsinteractive.com" || localStorage.getItem("owneremail")=="amritesh@onsinteractive.com")
		{
			//https://www.sandbox.paypal.com/cgi-bin/webscr
			paypalIdHtml= '<!DOCTYPE HTML><html><body onload="ClickButtonPaypal();"><form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post"><!-- Identify your business so that you can collect the payments. --><input type="hidden" name="business" value="'+localStorage.getItem('businessId')+'">';
		}
		else
		{
			paypalIdHtml='<!DOCTYPE HTML><html><body onload="ClickButtonPaypal();"><form action="https://www.paypal.com/cgi-bin/webscr" method="post"><!-- Identify your business so that you can collect the payments. --><input type="hidden" name="business" value="'+localStorage.getItem('businessId')+'">';
		}

				var paypalUrlFormHtml='<!-- URL --><input name="cancel_return" type="hidden" value="signup-cancel"><input type="hidden" name="notify_url" value="http://'+localStorage.getItem("reseller")+'/paypalmobile/notify/orderId/'+sessionStorage.getItem("foodRandomId")+'/appId/'+localStorage.getItem("applicationID")+'" /><input type="hidden" name="return" value="signup-success" /><!-- Display the payment button. --><input type="image" src="{URL}/images/subscribe_btn.png" name="submit" id="submit" alt="PayPal - The safer, easier way to pay online!"><img alt="" border="0" src="http://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1"></form><script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script><script>function ClickButtonPaypal(){$(\'#submit\').trigger(\'click\');}</script></body></html>';
				window.location="foodpaypal:"+encodeURI(paypalIdHtml+paypalAddFormHtml+paypalUrlFormHtml);
				setTimeout(function(){$('.appypie-loader').hide();},1000);
	}
}

function closePopup12()
{
	setTimeout(function() {
		$('#popup-outside-page12').popup( 'close' );
	},1000);

	setTimeout(function() {
		$('#popup-outside-page2').popup( 'close' );
	},1000);
}

function inAppSignup(paymentType,paymentAmount,paymentCurrency)
{
	closePopup12();

	var paymentData=localStorage.getItem("paymentMethod");
	if(paymentData=="paypal_express")
	{
		paypalExpressSignup(paymentAmount,paymentCurrency,paymentType);
	}
	else if(paymentData=="inApp")
	{
		if(localStorage.getItem("publicKey")!="")
		{
			console.log("public key exist>>>"+localStorage.getItem("publicKey"));
			if(paymentType=="onetimepayment")
			{
				toaster.hitInAppPurchaseForSignup(localStorage.getItem("anppAddProductId"),localStorage.getItem("publicKey"),"edition");
			}
			else if(paymentType=="yearlySub")
			{
				toaster.hitInAppPurchaseForSignup(localStorage.getItem("inapYearlySub"),localStorage.getItem("publicKey"),"subscription");
			}
			else if(paymentType=="monthlySub")
			{
				toaster.hitInAppPurchaseForSignup(localStorage.getItem("inapMonthlySub"),localStorage.getItem("publicKey"),"subscription");
			}
		}
		else {

			alertPopUp(aalert_code+ '!',ppleaseprovidepubliceforiap_code);
		}
	}
}

function signUpPaymentSuccess()
{
	var name=sessionStorage.getItem("signUpName");
	var email=sessionStorage.getItem("signUpemail");
	var password=sessionStorage.getItem("signUppassword");
	var contact=sessionStorage.getItem("signUpcontact");
	var profileFBID = localStorage.getItem("fbId");
	startRegistration(name,email,password,contact,profileFBID);
}

function signUpPaymentError()
{

	/*
var name=sessionStorage.getItem("signUpName");
var email=sessionStorage.getItem("signUpemail");
var password=sessionStorage.getItem("signUppassword");
var contact=sessionStorage.getItem("signUpcontact");
startRegistration(name,email,password,contact);
	 */
}


function startRegistration(name,email,password,contact, profileidFB)
{
	if(profileidFB == null || profileidFB == ""){
		profileidFB = "";
	}
	console.log("startRegistration FBID "+profileidFB);
	console.log("startRegistration");
	$('.appypie-loader').show();
	var uploadFileCount=sessionStorage.getItem("uploadFileCount");
	var globalExactPath=sessionStorage.getItem("globalExactPath");
	var jsonData=sessionStorage.getItem("jsonData");
	var jsonData2=sessionStorage.getItem("jsonData2");
	var jsonData3=sessionStorage.getItem("jsonData3");
	var autoStatus=localStorage.getItem("emailverification");
	console.log("uploadFileCount==="+uploadFileCount);
	if(parseInt(uploadFileCount)>0)
	{
		console.log("uploadFileCount>0");
		window.toaster.uploadMultipleFilesRegister(localStorage.getItem('applicationID'),name,
				email,password,contact, uploadFileCount,globalExactPath,jsonData,jsonData2,jsonData3,autoStatus);

		//ft.upload(fileURI, encodeURI("http://apps.appypie.com/formbuilder/send-custom-mail-template"), sendOnServerSuccess, sendServerError, options);
	}
	else
	{
		window.localStorage.setItem("profileName",name);
		window.localStorage.setItem("profileEmail",email);
		var wsUrl ="http://"+window.localStorage.getItem("reseller")+"/services/app-user-soap#appUserRegistration/";

		var soapRequest =
				'<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><appUserRegistration xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/app-user-soap#appUserRegistration/\"><appid>'+window.localStorage.getItem("applicationID")+'</appid><name>'+name+'</name><email>'+email+'</email><password>'+password+'</password><phone>'+contact+'</phone><formData><![CDATA['+jsonData+']]></formData><formLabel><![CDATA['+jsonData2+']]></formLabel><formFields><![CDATA['+jsonData3+']]></formFields><authoStatus>'+autoStatus+'</authoStatus><profileId>'+profileidFB+'</profileId><verifyStatus>1</verifyStatus></appUserRegistration></soap:Body></soap:Envelope>';

		console.log("soap request register>>>>>>"+soapRequest);
		//alert("wsUrl >>>>>>"+wsUrl);
		$.ajax({
			type: "POST",
			url: wsUrl,
			contentType: "text/xml",
			dataType: "text",
			data: soapRequest,
			success: processSuccessxmlupdatesignup,
			error: processErrorxmlupdatesignup
		});


	}
}


function alertPopupforRegister(labelDynamic)
{
	navigator.notification.alert(
			'*'+labelDynamic+ffieldcannotbeleftblank_code,
			alertDismissed,
			aalert_code,
			ookk_code
			);
}

function successFileuploaded(status)
{
	$('.appypie-loader').hide();
	 var obj = JSON.parse(status);
		if(obj.msg=="true")
		{
            if(obj.verifyCode=="")
			{
			 history.back();
			 $("#logo").show();
			}
			else
			{
			showConfirmationPage();
			localStorage.setItem("verifipassword",obj.password);
			localStorage.setItem("verifyCode",obj.verifyCode);
			localStorage.setItem("verifyemail",obj.email);
			localStorage.setItem("verifyname",obj.name);
			localStorage.setItem("verifyuserData",obj.userData);
			return;
			}

			//Showhideupdatebutton();
			if(window.localStorage.getItem("applicationID") != "ecb1f89b5b67")
			{
				navigator.notification.alert(
						registeredsuccessfully,
						alertDismissed,
						aalert_code,
						ookk_code
						);
			}

			else
			{
				navigator.notification.alert(
						registeredsuccessfullycustomforecb1f89b5b67,
						alertDismissed,
						aalert_code,
						ookk_code
						);
			}

		}else if(status=="false")
		{



			window.localStorage.removeItem("profileName");
			window.localStorage.removeItem("profileEmail");
			navigator.notification.alert(
					uunabletoegister_code,
				alertDismissed,
				aalert_code,
				ookk_code
				);
		}
		else{
		
		alert("processSuccessxmlupdatesignup");
			navigator.notification.alert( 
				useralreadyregistered,
				alertDismissed,
				aalert_code,
				ookk_code
				);
		}
}

function processSuccessxmlupdatesignup(data, status, req)
{
	//alert("success");
	var xml = req.responseText;
	xmlDoc = $.parseXML( xml );
	$xml = $( xmlDoc );
	$title = $xml.find( "return" );
	var status=$title.text();

	console.log("Response ... "+status);

	var fbId = localStorage.getItem("fbId");
	var email = localStorage.getItem("fbemailId");
	var name = localStorage.getItem("fbuserName");;
	console.log("ProcessSuccessXmlUpdatesignup() method... FB user Id "+fbId);
	if(fbId == "")
		fbId = null;
	if(fbId != null){
	//	loadData();
	console.log("Load Data ..... FB user Id "+fbId+" Name "+name+" Email Id "+email);
		loadFBUser(name, email, fbId);
	//	window.localStorage.setItem("fbId", "");
		window.localStorage.setItem("fbuserName", "");
		window.localStorage.setItem("fbemailId", "");

		return;
	}else{
		console.log("Load Data ..... Normal User.....");
		//{"msg":"true","email":"klkl@klkl.con","name":"Krishna","userId":61429,"paidStatus":0,"0":"userData":,"1":"verifyCode":}", source: file:///android_asset/www/js/code.js (5130)
         var obj = JSON.parse(status);
		if(obj.msg=="true")
		{
            if(obj.verifyCode=="")
			{
			 history.back();
			 $("#logo").show();
			}
			else
			{
			showConfirmationPage();
			localStorage.setItem("verifipassword",obj.password);
			localStorage.setItem("verifyCode",obj.verifyCode);
			localStorage.setItem("verifyemail",obj.email);
			localStorage.setItem("verifyname",obj.name);
			localStorage.setItem("verifyuserData",obj.userData);
			return;
			}

			//Showhideupdatebutton();
			if(window.localStorage.getItem("applicationID") != "ecb1f89b5b67")
			{
				navigator.notification.alert(
						registeredsuccessfully,
						alertDismissed,
						aalert_code,
						ookk_code
						);
			}

			else
			{
				navigator.notification.alert(
						registeredsuccessfullycustomforecb1f89b5b67,
						alertDismissed,
						aalert_code,
						ookk_code
						);
			}

		}else if(status=="false")
		{



			window.localStorage.removeItem("profileName");
			window.localStorage.removeItem("profileEmail");
			navigator.notification.alert(
					uunabletoegister_code,
				alertDismissed,
				aalert_code,
				ookk_code
				);
		}
		else{
			navigator.notification.alert(
				useralreadyregistered,
				alertDismissed,
				aalert_code,
				ookk_code
				);
		}
	}

}

function processErrorxmlupdatesignup(data, status, req) {

	navigator.notification.alert(
			ccheckyournternetonnectioagain_code,
			alertDismissed,
			aalert_code,
			ookk_code
			);
	window.localStorage.removeItem("profileName");
	window.localStorage.removeItem("profileEmail");
}


function checkEmail(email) {


	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

	if (!filter.test(email)) {
		return false;
	}
	return true;
}
function forgotpassword()
{

	$.mobile.changePage('#forgotpassword');
	$("#logo").show();
	$("#reload").hide();
	$("#fb-div").show();
	document.getElementById("frgtF").innerHTML=   localStorage.getItem("forgotpwdtext");
	document.getElementById("frgtE").innerHTML=  localStorage.getItem("emailaddress");
	document.getElementById("frgtB").innerHTML=  localStorage.getItem("resetpwdbtn");
	//document.getElementById("frgtD").innerHTML= localStorage.getItem("forgotaccounttext");
	//document.getElementById("frgtS").innerHTML= localStorage.getItem("forgotsignuptext");
	document.getElementById("frgtL").innerHTML= localStorage.getItem("forgotsignin");
	document.getElementById("frgtA").innerHTML= localStorage.getItem("forgotalreadyaccounttext");

}


/* params
  @Krishna
  emailveriF: verfication heading
  emailveriE: verfication text
  emailveriB: verfication resend button
  emailveriL: verfication confrim button
  emailveriA: allready have account
*/
var emailveriHeading="",emailveriTitle="",emailveriResendButton="",emailveriConfirmBtn="";
function showConfirmationPage()
{
    $.mobile.changePage('#emailverification');
	$("#logo").show();
	$("#reload").hide();
	$("#fb-div").show();
	$('.appypie-loader').hide();
	// set dynamic values for email verification.
	emailveriHeading="Verification";
	emailveriTitle="Enter verfication code";
	emailveriResendButton="Resend code";
	emailveriConfirmBtn="Sign in";
	document.getElementById("emailveriF").innerHTML= emailveriHeading;
	document.getElementById("emailveriE").innerHTML= emailveriTitle;
	document.getElementById("emailveriB").innerHTML= emailveriResendButton;
	document.getElementById("emailveriC").innerHTML= "Confirm";
	document.getElementById("emailveriL").innerHTML= emailveriConfirmBtn;
	document.getElementById("emailveriA").innerHTML= localStorage.getItem("forgotalreadyaccounttext");
}


function resendEmail()
{
    var wsUrl = "http://"+window.localStorage.getItem("reseller")+"/services/app-user-soap#resendVerificationMail/";
	//var email = document.getElementById('verificationText').value;

		var soapRequest =
				'<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><resendVerificationMail xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/app-user-soap#appUserForgotPassword/\"><email>'+localStorage.getItem("verifyemail")+'</email><name>'+localStorage.getItem("verifyname")+'</name><appid>'+window.localStorage.getItem("applicationID")+'</appid><appName>'+localStorage.getItem("ApplicationName")+'</appName><password>'+window.localStorage.getItem("verifipassword")+'</password><adminEmail>'+localStorage.getItem("owneremail")+'</adminEmail><appImage>'+localStorage.getItem("appIcon")+'</appImage><verifyCode>'+localStorage.getItem("verifyCode")+'</verifyCode></resendVerificationMail></soap:Body></soap:Envelope>';

		console.log(" resendEmail" + soapRequest);

		$.ajax({
			type: "POST",
			url: wsUrl,
			contentType: "text/xml",
			dataType: "text",
			data: soapRequest,
			success: processSuccessxmlVerification,
			error: processErrorxmlVerification
		});

}

function processSuccessxmlVerification(data, status, req)
{


    var xml = req.responseText;
	xmlDoc = $.parseXML( xml );
	$xml = $( xmlDoc );
	$title = $xml.find( "return" );
	var status=$title.text();
   console.log("processSuccessxmlVerification response"+status);
	if(status=="success")
	{
		navigator.notification.alert(
				vverificationcodesendsuccessfully_code,
				alertDismissed,
				aalert_code,
				ookk_code
				);

	}else
	{

		navigator.notification.alert(
				vverificationodesendingfailed_code,
				alertDismissed,
				aalert_code,
				ookk_code
				);

	}
}

function processErrorxmlVerification(data, status, req)
{
navigator.notification.alert(
		ppleasetryagain_code,
				alertDismissed,
				aalert_code,
				ookk_code
				);
}

function ConfirmVerfication()
{
    var wsUrl = "http://"+window.localStorage.getItem("reseller")+"/services/app-user-soap#saveUserData/";
	var email = document.getElementById('verificationText').value;

	if(email == "")
	{

		navigator.notification.alert(
				verificationcodemustnotbeblank_code,
				alertDismissed,
				aalert_code,
				ookk_code
				);

	}
	else{

	 if(email==localStorage.getItem("verifyCode"))
	 {

		var soapRequest =
				'<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><saveUserData xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/app-user-soap#saveUserData/\"><appid>'+window.localStorage.getItem("applicationID")+'</appid><userData>'+window.localStorage.getItem("verifyuserData")+'</userData></saveUserData></soap:Body></soap:Envelope>';

		console.log(" resendEmail" + soapRequest);

		$.ajax({
			type: "POST",
			url: wsUrl,
			contentType: "text/xml",
			dataType: "text",
			data: soapRequest,
			success: processSuccessxmlVerificationConfirm,
			error: processErrorxmlVerification
		});
     }
	 else
	 {
	   navigator.notification.alert(
			   vverificationcodenotmatched_code,
				alertDismissed,
				aalert_code,
				ookk_code
				);
	 }

	}
}

function processSuccessxmlVerificationConfirm(data, status, req)
{

    var xml = req.responseText;
	xmlDoc = $.parseXML( xml );
	$xml = $( xmlDoc );
	$title = $xml.find( "return" );
	var status=$title.text();
    console.log("processSuccessxmlVerificationConfirm response"+status);

		navigator.notification.alert(
				status,
				status,
				aalert_code,
				ookk_code
				);
	   //history.go(-1);
	document.getElementsByName('name')[0].value="";
	document.getElementsByName('email')[0].value="";
	document.getElementsByName('contact')[0].value="";
	document.getElementsByName('password')[0].value="";
	document.getElementsByName('cpassword')[0].value="";

     showLoginPage();

}

function resetpass()
{

	var wsUrl = "http://"+window.localStorage.getItem("reseller")+"/services/app-user-soap#appUserForgotPassword/";
	var email = document.getElementById('EmailIdreset').value;
	if(email == "")
	{

		navigator.notification.alert(
				eemailidmustnotbeblank_code,
				alertDismissed,
				aalert_code,
				ookk_code
				);

	}else{

		if(!checkEmail(email))
		{
			return 0;
		}

		var soapRequest =
				'<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><appUserForgotPassword xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/app-user-soap#appUserForgotPassword/\"><email>'+email+'</email><appid>'+window.localStorage.getItem("applicationID")+'</appid></appUserForgotPassword></soap:Body></soap:Envelope>';

		console.log(" " + soapRequest);

		$.ajax({
			type: "POST",
			url: wsUrl,
			contentType: "text/xml",
			dataType: "text",
			data: soapRequest,
			success: processSuccessxmlupdatereset,
			error: processErrorxmlupdatereset
		});


	}

}
function processSuccessxmlupdatereset(data, status, req) {

	var xml = req.responseText;
	xmlDoc = $.parseXML( xml );
	$xml = $( xmlDoc );
	$title = $xml.find( "return" );
	var status=$title.text();

	if(status=="true")
	{
		navigator.notification.alert(
				signuppasswordsendmailid,
				alertDismissed,
				aalert_code,
				ookk_code
				);

	}else
	{

		navigator.notification.alert(
				signuppasswordunabletoreset,
				alertDismissed,
				aalert_code,
				ookk_code
				);

	}

}

function processErrorxmlupdatereset(data, status, req) {
	console.log("Error : "+ req.responseText);
}
function showLoginPage()
{
	if(sessionStorage.getItem('fromInnerPages') && sessionStorage.getItem('fromInnerPages') == 'true')
	{
		$.mobile.changePage('#page26');
	}
	else
	{
		$.mobile.changePage('#page1');
	}
}

function onDeviceReady_Icon() {

	console.log('one onDeviceReady_Icon()');
	window.requestFileSystem  = window.requestFileSystem || window.webkitRequestFileSystem;
	window.requestFileSystem(LocalFileSystem.PERSISTENT, 0, gotFSIcon, fail);


}

function gotFSIcon(fileSystem) {

	console.log("Icon Download one two");
	window.rootFS = fileSystem.root;
	requestedFileSystem=fileSystem;
	var packageName=toaster.getAppPackageName();
	documentPath='file:///data/data/'+packageName;
	console.log("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ"+localStorage.getItem("applicationPackage"));
	var entry=fileSystem.root;
	console.log("entry tag for the file ------->>>>>"+entry);
	if(!checkNetworkConnection())
	{
		entry.getDirectory("homeIcon", {create: true, exclusive: false}, onGetDirectorySuccess, onGetDirectoryFail);

	}else
	{


		fileSystem.root.getDirectory("homeIcon", {create:true}, function(f) {
			f.removeRecursively(function() {
				entry.getDirectory("homeIcon", {create: true, exclusive: false}, onGetDirectorySuccess, onGetDirectoryFail);

			});
		}, onError_dir);
	}


}
function onError_dir()
{
	// alert("eroor");
}

function success(entry) {
	console.log("Removal succeeded");
}

function fail(error) {
	//  alert('Error removing directory: ' + error.code);
}


function onGetDirectorySuccess(dir) {
	console.log("Created dir one "+dir.name);
	documentPath=documentPath+"/"+dir.name;
	toaster.setApplicationPackage(documentPath);
	LoadAppIcon();
}

function onGetDirectoryFail(error) {
	console.log("Error creating directory "+error.code);
}
var iconPath='';
var fileImages='';
function LoadAppIcon()
{
	console.log("LoadAppIcon");
	var xmlFileData = $.parseXML(sessionStorage.getItem('xml'));
	$xml = $(xmlFileData);
	$home = $xml.find( "home" );
	//alert("LoadAppIcon 1");
	var iconName =$home.find("iconName").text();
	fileImages= iconName.split(",");
	console.log("rvfile length"+fileImages.length);
	iconPath = $home.find("iconPath").text();
	fileListTOdownload();

}

var fileCount=0;
function fileListTOdownload()
{
	console.log("fileListTOdownload");
	// alert("downloaded 1");
	if(!checkNetworkConnection())
	{
		// appStore();

	}else{
		console.log("fileCount"+fileCount+"fileImages.length"+fileImages.length);
		if(fileCount<fileImages.length)
		{

			if(fileImages[fileCount].indexOf('.') != -1 )
			{
				console.log("rc1"+fileImages[fileCount])
				console.log("rc2"+iconPath);
				var downloadfileURl=iconPath+fileImages[fileCount];
				console.log("rvvvvvdownloadfileURl"+downloadfileURl);
				console.log("rvvvvvdocumentPath---->>"+downloadfileURl);
				var fileURL=documentPath+'/'+fileImages[fileCount];
				console.log("fileURL"+fileURL);
				console.log(fileImages[fileCount]);
				console.log(downloadfileURl);
				var xhr1 = jQuery.ajax({
					url : fileURL,
					async : false,
					dataType : 'json',
					cache:false,
				}).responseText;

				if (!xhr1.length) {
					console.log(" File Not Found .");
					downloadLessonImages(downloadfileURl,fileURL);
				}else
				{
					console.log(".... File  Found .");
					fileCount=fileCount+1;
					fileListTOdownload(fileCount);

				}

			}
			else
			{
				fileCount=parseFloat(fileCount)+1;
				fileListTOdownload(fileCount);
			}
		}else{
			console.log("Hurray Complete");
			window.localStorage.setItem("IconDownload","true");

			// appStore();
		}
	}
}

function downloadLessonImages(downloadfileURl,fileURL)
{

	console.log("fileURLnew"+fileURL);
	var fileTransfer = new FileTransfer();
	var uri = encodeURI(downloadfileURl);

	fileTransfer.download(
			uri,
			fileURL,
			function(entry) {
				console.log("download complete: " + entry.fullPath);
				// alert("downloaded");
				fileCount=fileCount+1;
				fileListTOdownload(fileCount);
			},
			function(error) {
				console.log("download error source " + error.source);
				console.log("download error target " + error.target);
				console.log("upload error code" + error.code);
				fileCount=fileCount+1;
				fileListTOdownload(fileCount);

			},
			false,
			{
				headers: {
				"Authorization": "Basic dGVzdHVzZXJuYW1lOnRlc3RwYXNzd29yZA=="
			}
			}
			);

}


if (sessionStorage.getItem("appPageFont") === null) {
	//...
	sessionStorage.setItem("appPageFont","arial")
}

if (sessionStorage.getItem("pageTextColor") === null) {
	//...
	sessionStorage.setItem("pageTextColor","red")
}



function onError(error) {
}

function returnIconImageHtml(imageUrl)
{
	var imageHtml='';
	if (imageUrl.indexOf('.') != -1)
	{
		imageHtml ='<img  src="'+ imageUrl+'"  />';
	}
	else
	{
		imageHtml = '<i  class="' + imageUrl + '" style="color:'+sessionStorage.getItem("navIconColor")+' !important"/></i>';
	}
	console.log("imageHtml --->"+imageHtml);
	return imageHtml;
}


function loginPageDetails()
{
	console.log("loginPageDetails() starts");
	var loginHeading = "Sign In";
	var loginEmailField = "Username/Email";
	var loginPasswordField ="Password";
	var loginBtnField = "Login";
	var loginForgetPwdField = "Forgot your password?";
	var loginAccountTxtField = "Do not have an account?";
	var loginSignUpTxtField = "Sign up Now";
	var loginFBTxt = "";

	var SignUpTxtField = "Sign up Now";
	var SignUpNameField = "Name";
	var SignUpEmailField = "Username/Email";
	var SignUpPwdField = "Password";
	var SignUpCnfPwdField = "Confirm  Password";
	var SignUpPhone="Phone";
	var SignUpcompanyName="Company";
	var SignUpBtnField = "Sign Up";
	var SignUpAlreadyField = "Already have an account?";
	var SignUpEmailVerify = "0";

	var ForgetPwdTxtField = "Forgot Password";
	var ForgetPwdEmailField = "Username/Email";
	var ForgetPwdBtnField = "Reset Password";
	var ForgetPwdAccountField = "Do not have an account?";
	var ForgetPwdAlreadyField = "Already have an account?";
	var ForgetPwdSigninField = "Sign In";
	var ForgetPwdSignUpField = "Sign up Now";


	var paymentMethod = "";
	var businessId = "";
	var inapMonthlySub = "";
	var inapYearlySub = "";
	var anppAddProductId = "";
	var publicKey = "";
	var subsPriceMonthly = "";
	var subsCurrencyMonthly = "";
	var subsPriceYearly = "";
	var subsCurrencyYearly = "";
	var editionPrice = "";
	var editionCurrency = "";
	var loginDescr = "";

	console.log("loginPageDetails() 1 "+$(masterData).find("loginfield"));
	if(masterData.getElementsByTagName('loginfield')[0])
	{
		console.log("loginPageDetails() loginfield xml found");
		loginHeading = $(masterData).find("loginfield").find("signintext").text();
		loginEmailField = $(masterData).find("loginfield").find("emailid").text();
		loginPasswordField = $(masterData).find("loginfield").find("loginpassword").text();
		loginBtnField = $(masterData).find("loginfield").find("loginbtntext").text();
		loginForgetPwdField = $(masterData).find("loginfield").find("forgotpwd").text();
		loginAccountTxtField = $(masterData).find("loginfield").find("accounttext").text();
		loginSignUpTxtField = $(masterData).find("loginfield").find("loginsignuptext").text();

		if($(masterData).find("loginfield").find("loginSetting"))
		loginFBTxt = $(masterData).find("loginfield").find("loginSetting").find("enableFacebookNow").text();

		SignUpTxtField = $(masterData).find("loginfield").find("signuptext").text();
		SignUpNameField = $(masterData).find("loginfield").find("name").text();
		SignUpEmailField = $(masterData).find("loginfield").find("email").text();
		SignUpPwdField = $(masterData).find("loginfield").find("pass").text();
		SignUpCnfPwdField = $(masterData).find("loginfield").find("confirmpwd").text();
		SignUpPhone=$(masterData).find("loginfield").find("phoneNo").text();
		SignUpcompanyName=$(masterData).find("loginfield").find("companyName").text();
		SignUpBtnField = $(masterData).find("loginfield").find("signupbtntext").text();
		SignUpAlreadyField = $(masterData).find("loginfield").find("alreadyaccounttext").text();
		SignUpEmailVerify = $(masterData).find("loginfield").find("emailVerification").text();

		ForgetPwdTxtField = $(masterData).find("loginfield").find("forgotpwdtext").text();
		ForgetPwdEmailField = $(masterData).find("loginfield").find("emailaddress").text();
		ForgetPwdBtnField = $(masterData).find("loginfield").find("resetpwdbtn").text();
		ForgetPwdAccountField = $(masterData).find("loginfield").find("forgotaccounttext").text();
		ForgetPwdAlreadyField = $(masterData).find("loginfield").find("forgotalreadyaccounttext").text();
		ForgetPwdSigninField = $(masterData).find("loginfield").find("forgotsignin").text();
		ForgetPwdSignUpField = $(masterData).find("loginfield").find("forgotsignuptext").text();

		console.log("loginPageDetails() 1 "+$(masterData).find("loginfield").find("inp"));
		if($(masterData).find("loginfield").find("inp"))
		{
			console.log("in app purchase tag found");
			paymentMethod = $(masterData).find("loginfield").find("paymentMethod").text();
			if(paymentMethod.trim().length>2)
			{
				businessId = $(masterData).find("loginfield").find("businessId").text();
				inapMonthlySub = $(masterData).find("loginfield").find("inapMonthlySub").text();
				inapYearlySub = $(masterData).find("loginfield").find("inapYearlySub").text();
				anppAddProductId = $(masterData).find("loginfield").find("anppAddProductId").text();
				publicKey = $(masterData).find("loginfield").find("publicKey").text();
				subsPriceMonthly = $(masterData).find("loginfield").find("subsPriceMonthly").text();
				subsCurrencyMonthly = $(masterData).find("loginfield").find("subsCurrencyMonthly").text();
				subsPriceYearly = $(masterData).find("loginfield").find("subsPriceYearly").text();
				subsCurrencyYearly = $(masterData).find("loginfield").find("subsCurrencyYearly").text();
				editionPrice = $(masterData).find("loginfield").find("editionPrice").text();
				editionCurrency = $(masterData).find("loginfield").find("editionCurrency").text();
				loginDescr = $(masterData).find("loginfield").find("loginDescr").text();


				 /*sign up dynamic language settings */

    if(masterData.getElementsByTagName('LanguageSettings')[0])
    {
        signupenternamealert = $(masterData).find("LanguageSettings").find("signup_entername_alert").text();
        signuppasswords = $(masterData).find("LanguageSettings").find("Sign_up_password").text();
        signupemailid = $(masterData).find("LanguageSettings").find("Sign_up_email").text();
        signuppasswordshouldbesevenchar = $(masterData).find("LanguageSettings").find("Sign_up_password_should_be_seven_char").text();
        signuppassworddonotmatch = $(masterData).find("LanguageSettings").find("Sign_up_password_do_not_match").text();
        signuppasswordsendmailid = $(masterData).find("LanguageSettings").find("Sign_up_password_send_mail_id").text();
        signuppasswordunabletoreset = $(masterData).find("LanguageSettings").find("Sign_up_password_unable_to_reset").text();
        registeredsuccessfully = $(masterData).find("LanguageSettings").find("Registered_successfully").text();
        unabletoregister = $(masterData).find("LanguageSettings").find("Unable_to_register").text();
        useralreadyregistered = $(masterData).find("LanguageSettings").find("User_already_registered").text();
        emailandpasswordmustnotbeblank = $(masterData).find("LanguageSettings").find("Email_and_password_must_not_be_blank").text();
        invalidusernameorpassword = $(masterData).find("LanguageSettings").find("Invalid_user_name_or_password").text();

    }

    /*End sign up dynamic language settings */





				localStorage.setItem("paymentMethod",paymentMethod);
				localStorage.setItem("businessId",businessId);
				localStorage.setItem("inapMonthlySub",inapMonthlySub);
				localStorage.setItem("inapYearlySub",inapYearlySub);
				localStorage.setItem("anppAddProductId",anppAddProductId);
				localStorage.setItem("publicKey",publicKey);
				localStorage.setItem("subsPriceMonthly",subsPriceMonthly);
				localStorage.setItem("subsCurrencyMonthly",subsCurrencyMonthly);
				localStorage.setItem("subsPriceYearly",subsPriceYearly);
				localStorage.setItem("subsCurrencyYearly",subsCurrencyYearly);
				localStorage.setItem("editionPrice",editionPrice);
				localStorage.setItem("editionCurrency",editionCurrency);
				localStorage.setItem("loginDescr",loginDescr);
			}
		}

	}
	console.log("loginPageDetails() 2");
	localStorage.setItem("signintext",loginHeading);
	localStorage.setItem("emailid",loginEmailField);
	localStorage.setItem("loginpasswordd",loginPasswordField);
	localStorage.setItem("loginbtntext",loginBtnField);
	localStorage.setItem("forgotpwd",loginForgetPwdField);
	localStorage.setItem("accounttext",loginAccountTxtField);
	localStorage.setItem("loginsignuptext",loginSignUpTxtField);
	localStorage.setItem("loginfb",loginFBTxt);
	localStorage.setItem("signuptext",SignUpTxtField);
	localStorage.setItem("name",SignUpNameField);
	localStorage.setItem("email",SignUpEmailField);
	localStorage.setItem("password",SignUpPwdField);
	localStorage.setItem("confirmpwd",SignUpCnfPwdField);
	localStorage.setItem("phoneNo",SignUpPhone);
	localStorage.setItem("company",SignUpcompanyName);
	localStorage.setItem("signupbtntext",SignUpBtnField);
	localStorage.setItem("alreadyaccounttext",SignUpAlreadyField);
	localStorage.setItem("emailverification",SignUpEmailVerify);
	localStorage.setItem("forgotpwdtext",ForgetPwdTxtField);
	localStorage.setItem("emailaddress",ForgetPwdEmailField);
	localStorage.setItem("resetpwdbtn",ForgetPwdBtnField);
	localStorage.setItem("forgotaccounttext",ForgetPwdAccountField);
	localStorage.setItem("forgotalreadyaccounttext",ForgetPwdAlreadyField);
	localStorage.setItem("forgotsignin",ForgetPwdSigninField);
	localStorage.setItem("forgotsignuptext",ForgetPwdSignUpField);

	document.getElementById("lgnS").innerHTML=localStorage.getItem("signintext");
	document.getElementById("lgnE").innerHTML=localStorage.getItem("emailid");
	document.getElementById("lgnP").innerHTML=localStorage.getItem("loginpasswordd");
	document.getElementById("lgnB").innerHTML=localStorage.getItem("loginbtntext");
	document.getElementById("lgnF").innerHTML=localStorage.getItem("forgotpwd");
	document.getElementById("lgnSU").innerHTML=localStorage.getItem("loginsignuptext");
	if(localStorage.getItem("loginfb")=="On")
	{
	//document.getElementById("lgnfb").style.display="block";
	$("#lgnfb").show();
	$("#lgnfaceb").show();
	}
	else
	{
	//document.getElementById("lgnfb").style.display="none";
	$("#lgnfb").hide();
	$("#lgnfaceb").hide();
	}
	document.getElementById("lgnA").innerHTML=localStorage.getItem("accounttext");
	console.log("loginPageDetails() ends");
}

function setFileSystem(requestedFileSystem)
{

	requestedFileSystem.root.getFile('appypie.xml', {create: false}, function(fileEntry)
			{
		fileEntry.remove(function(file){
			checkAndDownloadAppypieXml(sessionStorage.getItem("updatedXML"));
			console.log("File removed!");
		},function(){
			checkAndDownloadAppypieXml(sessionStorage.getItem("updatedXML"));
			console.log("error deleting the file " + error.code);
		});
			}, fail);
}

function gotFileEntry(fileEntry)
{
	//alert("rv2");

}

function fail(){
	console.log("failllllllllllllllllll");
	//alert("fail");
}
function gotFileWriter(writer) {
	console.log("failllllllllllllllllll2");
	writer.onwriteend = function(evt) {
		writer.onwriteend = function(evt) {
			writer.seek(4);
			writer.write(newXmlData);
			writer.onwriteend = function(evt){
			}
		};
	};
	writer.write(newXmlData);
}

function updateXmlOnApp()
{
	window.requestFileSystem  = window.requestFileSystem || window.webkitRequestFileSystem;
	window.requestFileSystem(LocalFileSystem.PERSISTENT, 0, setFileSystem, fail);
}

function successFileSystem(fileSystem)
{
	window.rootFS = fileSystem.root;
	requestedFileSystem=fileSystem;
}


function callOnOrientation(value)
{
    dataBind='false';


    var pageNumberCal;
    var lastPageTotalIcon;
    var perPageIcons;
    var counterIcon=0;
    var counterPage=1;
    var appendString="";
    var deviceHt=SetElementHeight();
    //console.log("deviceHt krishna --->"+deviceHt);
	if(mobileADS)
	{
		deviceHt = parseInt(deviceHt) - 60;
	}
    var tempHeight=0;
    if(sessionStorage.getItem('hideHeaderBar') && sessionStorage.getItem('hideHeaderBar') == "true")
    {
        tempHeight=parseInt(deviceHt)-50;
    }
    else
    {
        tempHeight=parseInt(deviceHt)-100;
    }

	//console.log("tempHeight krishna--->"+tempHeight);

	var layout = localStorage.getItem('layout');
	$("#iconDiv").show();
	var pageIconHeight = $('#iconDiv a').height() + 18;
	if(layout == "imgmatrix")
	{
		pageIconHeight = 150;
	}
	var tempIconMatrix=parseInt(tempHeight)/parseInt(pageIconHeight);

	var row=parseInt(tempIconMatrix);

	var itemW = 105; // per a tag width
	if(layout == "imgmatrix")
	{
		itemW = 150;
	}



	var size = parseInt($(window).width()/ itemW);
	size = 3;
	if(size > 5)
	{
		size = 6;
	}
	var count = row*size;
	//console.log("pageIconHeight : " + pageIconHeight + " w : " + $(window).width() + " count : " + count);


	if(localStorage.getItem("layout")=="matrix2")
    {

        //perPageIcons=row*2;
		perPageIcons=row*size;

        pageNumberCal=PageID.length/perPageIcons;

        lastPageTotalIcon=PageID.length % perPageIcons;
     	/* if(PageID.length==perPageIcons)
        {
            lastPageTotalIcon=PageID.length;
        }*/
		appendString+= "<div id='matrixBox' class='owl-carousel app_navigation_matrix2 "+localStorage.getItem("carouselTheme")+"'>";
    }
    else
    {
		//perPageIcons=row*3;
		perPageIcons=row*size;


        pageNumberCal=PageID.length/perPageIcons;
        lastPageTotalIcon=PageID.length % perPageIcons;

        appendString+= "<div id='matrixBox' class='owl-carousel app_navigation_matrix "+localStorage.getItem("carouselTheme")+"'>";
    }

    var totalPage= Math.ceil(pageNumberCal);
	var navIconColor = sessionStorage.getItem('navIconColor');
	var navBorderColor = sessionStorage.getItem('navBorderColor');
	var navTextColor = sessionStorage.getItem('navTextColor');
	var navBackgroundColor = sessionStorage.getItem('navBackgroundColor');
	var showMatrixBackgrondText = sessionStorage.getItem('showMatrixBackgrondText');

    for(var ctrPage=0; ctrPage<parseInt(totalPage); ctrPage++)
    {
		var iconHtml='';
        var imagepath1;
        appendString+= "<div class='item'>";

        if(counterPage==totalPage && lastPageTotalIcon!=0)
        {

            for(var i=0; i<parseInt(lastPageTotalIcon);i++)
            {
                if(pageIcons[counterIcon].indexOf('.')!=-1)
                {
                    if(!checkNetworkConnection('true'))
                    {
						imagepath1= localStorage.getItem("documentPath")+'/' + pageIcons[counterIcon];
                    }
                    else
                    {
						if(localStorage.getItem("IconDownload")=="true" && sessionStorage.getItem("applicationUpdated")!="true" && !checkNetworkConnection('true'))
						{
							imagepath1=localStorage.getItem("documentPath")+'/' + pageIcons[counterIcon];
						}
						else{
							imagepath1=localStorage.getItem("iconPath")+'/'+pageIcons[counterIcon];
						}
                    }
                    iconHtml='<img src="'+imagepath1+ '" border="0" style="border-color:'+navBorderColor+'" />';
                }
                else
                {
					if(localStorage.getItem("carouselTheme").indexOf("rounded-option") != -1)
					{
						var backgroundC = navBackgroundColor;
						if(sessionStorage.getItem('iconbackgrounddisplayval')=='none')
						{
							backgroundC = "none"
						}
						iconHtml='<i style="color:'+navIconColor+';display:'+sessionStorage.getItem('iconDisp')+'; border-color:'+navBorderColor+'; background:'+backgroundC+';" class="pageIcon '+pageIcons[counterIcon]+'"></i>';
					}
					else
					{
						iconHtml='<i style="color:'+navIconColor+'; display:'+sessionStorage.getItem('iconDisp')+'; border-color:'+navBorderColor+';" class="pageIcon '+pageIcons[counterIcon]+'"></i>';
					}
                }



                if($(masterData).find('appTheme').text()=="transparent"){
                	if(sessionStorage.getItem('iconbackgrounddisplayval')=='none')
                     {
					 	appendString+= "<a href='#' class='fixedList' onclick='openPage("+counterIcon+");' style='border-color:"+navBorderColor+";'>"+iconHtml+"<span style='color:"+navTextColor+";background:none;'>"+ PageName[counterIcon] +"</span> </a>";
					 }
					 else
					 {
                   		//appendString+= "<a href='#' class='fixedList' onclick='openPage("+counterIcon+");' style='border-color:"+navBorderColor+";'>"+iconHtml+"<span style='color:"+navTextColor+";background:"+navBackgroundColor+";'>"+ PageName[counterIcon] +"</span> </a>";
						if(showMatrixBackgrondText == "NO")
						{
							appendString+= "<a href='#' class='fixedList' onclick='openPage("+counterIcon+");' style='border-color:"+navBorderColor+";background:"+navBackgroundColor+";'>"+iconHtml+"<span style='color:"+navTextColor+";background:transparent;'>"+ PageName[counterIcon] +"</span> </a>";
						}
						else
						{
							appendString+= "<a href='#' class='fixedList' onclick='openPage("+counterIcon+");' style='border-color:"+navBorderColor+";'>"+iconHtml+"<span style='color:"+navTextColor+";background:"+navBackgroundColor+";'>"+ PageName[counterIcon] +"</span> </a>";
						}
                    }
				}
				else{
					if(sessionStorage.getItem('iconbackgrounddisplayval')=='none')
                     {
						 appendString+= "<a href='#' class='fixedList' onclick='openPage("+counterIcon+");' style='border-color:"+navBorderColor+";'>"+iconHtml+"<span style='color:"+navTextColor+";background:none;'>"+ PageName[counterIcon] +"</span> </a>";
					 }
					 else
					 {
                     	//appendString+= "<a href='#' class='fixedList' onclick='openPage("+counterIcon+");' style='border-color:"+navBorderColor+";'>"+iconHtml+"<span style='color:"+navTextColor+";background:"+navBackgroundColor+";'>"+ PageName[counterIcon] +"</span> </a>";
						if(showMatrixBackgrondText == "NO")
						{
							appendString+= "<a href='#' class='fixedList' onclick='openPage("+counterIcon+");' style='border-color:"+navBorderColor+";background:"+navBackgroundColor+";'>"+iconHtml+"<span style='color:"+navTextColor+";background:transparent;'>"+ PageName[counterIcon] +"</span> </a>";
						}
						else
						{
							appendString+= "<a href='#' class='fixedList' onclick='openPage("+counterIcon+");' style='border-color:"+navBorderColor+";'>"+iconHtml+"<span style='color:"+navTextColor+";background:"+navBackgroundColor+";'>"+ PageName[counterIcon] +"</span> </a>";
						}
                     }
                }
				counterIcon=parseInt(counterIcon)+1;
                }
            appendString+= "</div>";
            break;

		}
        else
        {
            for(var i=0; i<parseInt(perPageIcons);i++)
            {
                if(pageIcons[counterIcon].indexOf('.')!=-1)
                {
                    if(!checkNetworkConnection('true'))
                    {
                        imagepath1= localStorage.getItem("documentPath")+'/' + pageIcons[counterIcon];
                    }
                    else
                    {
						if(localStorage.getItem("IconDownload")=="true" && sessionStorage.getItem("applicationUpdated")!="true" && !checkNetworkConnection('true'))
						{
							imagepath1=localStorage.getItem("documentPath")+'/' + pageIcons[counterIcon];
						}
                  		else
						{
							imagepath1=localStorage.getItem("iconPath")+'/'+pageIcons[counterIcon];
                  		}
                 	}
                   iconHtml='<img src="'+imagepath1+ '" border="0" style="border-color:'+navBorderColor+'" />';
                }
                else
                {
					if(localStorage.getItem("carouselTheme").indexOf("rounded-option") != -1)
					{
						var backgroundC = navBackgroundColor;
						if(sessionStorage.getItem('iconbackgrounddisplayval')=='none')
						{
							backgroundC = "none"
						}
						iconHtml='<i style="color:'+navIconColor+';display:'+sessionStorage.getItem('iconDisp')+';border-color:'+navBorderColor+';background:'+backgroundC+'" class="pageIcon '+pageIcons[counterIcon]+'"></i>';
					}
					else
					{
						iconHtml='<i style="color:'+navIconColor+';display:'+sessionStorage.getItem('iconDisp')+';border-color:'+navBorderColor+'" class="pageIcon '+pageIcons[counterIcon]+'"></i>';
					}
                }

				 if($(masterData).find('appTheme').text()=="transparent")
				 {
					if(sessionStorage.getItem('iconbackgrounddisplayval')=='none')
					{
						appendString+= "<a href='#' class='fixedList' onclick='openPage("+counterIcon+");' style='border-color:"+navBorderColor+";' >"+iconHtml+"<span style='color:"+navTextColor+";background:none;'>"+ PageName[counterIcon] +"</span> </a>";
					}
					else
					{
						//appendString+= "<a href='#' class='fixedList' onclick='openPage("+counterIcon+");' style='border-color:"+navBorderColor+";' >"+iconHtml+"<span style='color:"+navTextColor+";background:"+navBackgroundColor+";'>"+ PageName[counterIcon] +"</span> </a>";
						if(showMatrixBackgrondText == "NO")
						{
							appendString+= "<a href='#' class='fixedList' onclick='openPage("+counterIcon+");' style='border-color:"+navBorderColor+";background:"+navBackgroundColor+";'>"+iconHtml+"<span style='color:"+navTextColor+";background:transparent;'>"+ PageName[counterIcon] +"</span> </a>";
						}
						else
						{
							appendString+= "<a href='#' class='fixedList' onclick='openPage("+counterIcon+");' style='border-color:"+navBorderColor+";'>"+iconHtml+"<span style='color:"+navTextColor+";background:"+navBackgroundColor+";'>"+ PageName[counterIcon] +"</span> </a>";
						}
					}
				}
				else{
					if(sessionStorage.getItem('iconbackgrounddisplayval')=='none')
					{
						appendString+= "<a href='#' class='fixedList' onclick='openPage("+counterIcon+");' style='border-color:"+navBorderColor+";'>"+iconHtml+"<span style='color:"+navTextColor+";background:none;'>"+ PageName[counterIcon] +"</span> </a>";
					}
					else
					{
						//appendString+= "<a href='#' class='fixedList' onclick='openPage("+counterIcon+");' style='border-color:"+navBorderColor+";'>"+iconHtml+"<span style='color:"+navTextColor+";background:"+navBackgroundColor+";'>"+ PageName[counterIcon] +"</span> </a>";
						if(showMatrixBackgrondText == "NO")
						{
							appendString+= "<a href='#' class='fixedList' onclick='openPage("+counterIcon+");' style='border-color:"+navBorderColor+";background:"+navBackgroundColor+";'>"+iconHtml+"<span style='color:"+navTextColor+";background:transparent;'>"+ PageName[counterIcon] +"</span> </a>";
						}
						else
						{
							appendString+= "<a href='#' class='fixedList' onclick='openPage("+counterIcon+");' style='border-color:"+navBorderColor+";'>"+iconHtml+"<span style='color:"+navTextColor+";background:"+navBackgroundColor+";'>"+ PageName[counterIcon] +"</span> </a>";
						}
					}
				}
                counterIcon=parseInt(counterIcon)+1;
            }
            appendString+= "</div>";
        }
        counterPage=parseInt(counterPage)+1;
    }

    appendString+= "</div>";
    $("#contentHolder1").empty().append(appendString);;



	$.mobile.resetActivePageHeight();
	matrixCarousel();


    $("#iconDiv").hide();
	if(size > 5 )
	{
		size = "max";
	}
	//$("#matrixBox").addClass(localStorage.getItem('layout') + "-size-" + size);
	if(mobileADS)
	{
		$("#matrixBox").addClass("ads");
	}

	if(showMatrixBackgrondText == "NO")
	{
		$("#matrixBox").addClass("backgroundTextHide");
	}
	var allH = parseInt($("#matrixBox").find("a").eq(0).height()*row);
	allH = $("#contentHolder1").height() - allH - 26;
	allH = parseInt(allH/row)  ;
	$("#matrixBox").find("a").css("margin", parseInt(allH/2) + "px 3.16%")

}


//matrix carousel view

function matrixCarousel()
{
if(localStorage.getItem("appLanguage") == "sa")
	{
		var matrixClone = $("#matrixBox").clone();
		$("#matrixBox").html("");
		matrixClone.find(".item").each(function(){
			$(this).prependTo("#matrixBox");
		})
	}
    $("#matrixBox").owlCarousel({
		navigation : false,
		slideSpeed : 300,
		paginationSpeed : 400,
		singleItem : true,
		responsiveRefreshRate : 100,
		//dragBeforeAnimFinish : false,
		touchDrag : true,
		loop:true
	});
	if(localStorage.getItem("appLanguage") == "sa")
	{
		var matrixBox = $("#matrixBox").data('owlCarousel');
		matrixBox.jumpTo($("#matrixBox").find(".item").size() - 1);
	}

}

function SetElementHeight()
{
	//var screenHeight=$('.ui-mobile').outerHeight(true);
	//var screenWidth=$('.ui-mobile').outerWidth(true);
	//var screenHeight="";
	//var screenWidth="";

	var xHeight = null;
	if(window.screen != null)
		xHeight = window.screen.availHeight;

	if(window.innerHeight != null)
		xHeight =   window.innerHeight;

	if(document.body != null)
		xHeight = document.body.clientHeight;

	return xHeight;
	// console.log(deviceScreenHeight);
	// alert("sdf"+screenWidth+"jhgjhg"+screenHeight);
	//console.log("outerHeight= "+ screenHeight);
	//console.log("outerWidth= "+ screenWidth);
}

//Home Page Header

function createHomeHeader()
{
	if($("#matrixHeader").size() == 0)
	{

		navigationSlider = localStorage.getItem('navigationSlider').split(",");
		if(navigationSlider.length != 0)
		{
			$('html').find('link').each(function(){
			var srcText=$(this).attr('href');
			if(srcText == 'css/appypie_headerhide_bottom.css')
			{
				$(this).attr('href','css/header-carousel.css');
				return false;
			}
			});
			var htmlHeader = '<div id="matrixHeader"></div>';
			$("#app_navigation_matrix").before(htmlHeader);

			$.each(navigationSlider, function( index, value ){
				$("#matrixHeader").append('<div class="item"><img src="images/banner-image.png"></div>');
				$("#matrixHeader").find(".item").eq(index).css({
					"background-image":"url("+value+")"
				});
			});
			$('#iconDiv').remove();

			if(localStorage.getItem("appLanguage") == "sa")
			{
				var matrixHClone = $("#matrixHeader").clone();
				$("#matrixHeader").html("");
				matrixHClone.find(".item").each(function(){
					$(this).prependTo("#matrixHeader");
				})
			}

			$("#matrixHeader").owlCarousel({
				navigation : false,
				slideSpeed : 300,
				paginationSpeed : 200,
				singleItem : true,
				responsiveRefreshRate : 100,
				autoPlay: 5000
			});

			if(localStorage.getItem("appLanguage") == "sa")
			{
				var matrixBox = $("#matrixHeader").data('owlCarousel');
				matrixBox.jumpTo($("#matrixHeader").find(".item").size() - 1);
			}
		}
	}
}

function resetDoc() {
	var layout = localStorage.getItem('layout');
	var w = $(window).width();
	$("#app_navigation_matrix").removeClass(layout+"-size-" + $("#app_navigation_matrix").attr("size"))
	var aWidth = 105;
	if(layout == "imgmatrix")
	{
		aWidth = 160;
	}
	var size = parseInt(w/aWidth);
	if(size > 5)
	{
		size = "max";
	}
	//$("#app_navigation_matrix").attr("size", size).addClass(layout + "-size-" + size);
}


function openpagefromnotification(id)
{
	$('.appypie-loader').show();
	var timeOut=10;
	if(!masterData)
	{
		timeOut = 5000;
	}
	setTimeout(function() {
		var moreBottomScroll='No';
		if(masterData.getElementsByTagName('moreBottomScroll')[0])
			moreBottomScroll=masterData.getElementsByTagName('moreBottomScroll')[0].firstChild.nodeValue;
		var pageIdentifier=masterData.getElementsByTagName('pageIdentifierBecon')[0].firstChild.nodeValue;
		var newpageIdentifier=pageIdentifier.split(',');
		for(var x=0;x<newpageIdentifier.length;x++)
		{
			console.log("x --->"+x+" <----newpageidentifier---->"+newpageIdentifier[x]);

			if(id == newpageIdentifier[x])
			{

				//if(parseInt(x) > 4 && PageID.length>4 && localStorage.getItem("layout")=="bottom" && moreBottomScroll=="YES")
				//{
					//openIndexPage(id);
				//}

				setTimeout(function() {
					$('.appypie-loader').hide();
					setTimeout(function() {openPage(x)},1000);

				},2500);
				return false;
			}
		}
	},timeOut);
}

function clearNotificationBedge()
{
	$('#supValueChange').empty();
	// $("#reload").removeClass("active");
	$('#supValueChange').hide();
}

//-----------------------------------------------------------------code App Header Background Img Dynamic----7-Oct-2015------------------------------------------------------
function set_headerbackground_image(headerimage)
{
	var HeadeImageUrl='';
	console.log(" 1 headerimage "+headerimage);
	//alert(headerimage);
	if($(masterData).find("customHeaderImage"))
	{
		if(headerimage >= 230 && headerimage < 480)
		{
			if($(masterData).find("header_320_44"))
			{
				HeadeImageUrl= $(masterData).find("customHeaderImage").find("header_320_44").text();
				console.log(" 2 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
			else
			{
				HeadeImageUrl=$(masterData).find("nav_header_image_name").text();
				console.log("  Default 1 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
		}


		//tested on abhishek rai  device 480x800
		else if(headerimage >= 480 && headerimage <= 540)
		{

			if($(masterData).find("header_320_44"))
			{
				//alert("header_480_44 "+ $(masterData).find("header_480_44"));
				HeadeImageUrl= $(masterData).find("customHeaderImage").find("header_320_44").text();
				console.log(" 3 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
			else
			{
				HeadeImageUrl=$(masterData).find("nav_header_image_name").text();
				console.log("  Default 2 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
		}



		//tested on 6 inch tab   640x960
		else if(headerimage >= 540 && headerimage < 640)
		{

			if($(masterData).find("header_480_44"))
			{
				//alert("header_480_44 "+ $(masterData).find("header_480_44"));
				HeadeImageUrl= $(masterData).find("customHeaderImage").find("header_480_44").text();
				console.log(" 3 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
			else
			{
				HeadeImageUrl=$(masterData).find("nav_header_image_name").text();
				console.log("  Default 2 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
		}



		//tested on krisna sir device    720x1280
		else if(headerimage >= 640 && headerimage < 730)
		{
			if($(masterData).find("header_640_88"))
			{
				HeadeImageUrl= $(masterData).find("customHeaderImage").find("header_640_88").text();
				console.log(" 4 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
			else
			{
				HeadeImageUrl=$(masterData).find("nav_header_image_name").text();
				console.log("  Default 3 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
		}

		else if(headerimage >= 730 && headerimage < 750)
		{
			if($(masterData).find("header_720_44"))
			{
				HeadeImageUrl= $(masterData).find("customHeaderImage").find("header_720_44").text();
				console.log(" 5 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
			else
			{
				HeadeImageUrl=$(masterData).find("nav_header_image_name").text();
				console.log("  Default 4 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
		}

		//tested on abhishek device 800x480 and 10 inch tab
		else if(headerimage >= 750 && headerimage < 980)
		{
			if($(masterData).find("header_750_88"))
			{
				HeadeImageUrl= $(masterData).find("customHeaderImage").find("header_750_88").text();
				console.log(" 6 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
			else
			{
				HeadeImageUrl=$(masterData).find("nav_header_image_name").text();
				console.log("  Default 5 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
		}


		//tested on dhiraj device  1080x1920 and 6 inch tab with land
		else if(headerimage >= 980 && headerimage < 1090)
		{
			if($(masterData).find("header_750_88"))
			{
				HeadeImageUrl= $(masterData).find("customHeaderImage").find("header_750_88").text();
				console.log(" 7 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
			else
			{
				HeadeImageUrl=$(masterData).find("nav_header_image_name").text();
				console.log("  Default 6 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}

		}

		else if(headerimage >= 1090 && headerimage < 1136)
		{
			if($(masterData).find("header_980_88"))
			{
				HeadeImageUrl= $(masterData).find("customHeaderImage").find("header_980_88").text();
				console.log(" 7 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
			else
			{
				HeadeImageUrl=$(masterData).find("nav_header_image_name").text();
				console.log("  Default 6 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}

		}

		//tested on krisna sir device    1280x720
		else if(headerimage >= 1136 && headerimage < 1242)
		{
			if($(masterData).find("header_1136_88"))
			{
				HeadeImageUrl= $(masterData).find("customHeaderImage").find("header_980_88").text();
				console.log(" 8 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
			else
			{
				HeadeImageUrl=$(masterData).find("nav_header_image_name").text();
				console.log("  Default 7 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}


		}
		else if(headerimage >= 1242 && headerimage <= 1280)
		{
		//	alert(1111111111);
			if($(masterData).find("header_1242_132"))
			{
				HeadeImageUrl= $(masterData).find("customHeaderImage").find("header_1334_88").text();
				console.log(" 9 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
			else
			{
				HeadeImageUrl=$(masterData).find("nav_header_image_name").text();
				console.log("  Default 8 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}

		}

		//tested on 10 inch device 720x1280
		else if(headerimage >= 1280 && headerimage < 1334)
		{
			if($(masterData).find("header_1280_44"))
			{
				HeadeImageUrl= $(masterData).find("customHeaderImage").find("header_1280_44").text();
				console.log(" 10 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
			else
			{
				HeadeImageUrl=$(masterData).find("nav_header_image_name").text();
				console.log("  Default 9 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}

		}


		else if(headerimage >= 1334 && headerimage < 1536)
		{
			//tested on deepak device
			if($(masterData).find("header_750_88"))
			{
				HeadeImageUrl= $(masterData).find("customHeaderImage").find("header_750_88").text();
				console.log(" 11  headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
			else
			{
				HeadeImageUrl=$(masterData).find("nav_header_image_name").text();
				console.log("  Default 10 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}

		}

		//tested on dhiraj device  1080x1920
		else if(headerimage >= 1536 && headerimage < 1925)
		{
			if($(masterData).find("header_1334_88"))
			{
				HeadeImageUrl= $(masterData).find("customHeaderImage").find("header_1334_88").text();
				console.log(" 12 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
			else
			{
				HeadeImageUrl=$(masterData).find("nav_header_image_name").text();
				console.log("  Default 11 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}

		}



		else if(headerimage >= 1925 && headerimage < 2048)
		{
			if($(masterData).find("header_1536_88"))
			{
				HeadeImageUrl= $(masterData).find("customHeaderImage").find("header_1536_88").text();
				console.log(" 12 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
			else
			{
				HeadeImageUrl=$(masterData).find("nav_header_image_name").text();
				console.log("  Default 11 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}

		}
		else if(headerimage >= 2048 && headerimage <  2208)
		{
			if($(masterData).find("header_2048_88"))
			{
				HeadeImageUrl= $(masterData).find("customHeaderImage").find("header_2048_88").text();
				console.log(" 13 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
			else
			{
				HeadeImageUrl=$(masterData).find("nav_header_image_name").text();
				console.log("  Default 12 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}

		}


		//test on deepak device. land mode.
		else if(headerimage >= 2208 )
		{
			if($(masterData).find("header_980_88"))
			{
				HeadeImageUrl= $(masterData).find("customHeaderImage").find("header_980_88").text();
				console.log(" 14 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}
			else
			{
				HeadeImageUrl=$(masterData).find("nav_header_image_name").text();
				console.log("  Default 13 headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
			}


		}
		else
		{
			HeadeImageUrl= $(masterData).find("nav_header_image_name").text();
			console.log(" 14 Default  headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
		}


	}
	else
	{
		HeadeImageUrl= $(masterData).find("nav_header_image_name").text();
		console.log(" 15 Default  headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
	}


	if(HeadeImageUrl == '')
	{


	var isPortraitt=sessionStorage.getItem("orientationstatus");
if(isPortraitt=='0' || isPortraitt=='180')
{
			HeadeImageUrl= $(masterData).find("nav_header_image_name").text();  //nav_header_image_name
			console.log(" 16 Default  headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
		}
		else
		{
			HeadeImageUrl= $(masterData).find("nav_header_ipad_image_name").text();
			console.log(" 17 Default  headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
		}

	}

	if(HeadeImageUrl == '' || HeadeImageUrl.length<10)
	{
		HeadeImageUrl= $(masterData).find("nav_header_image_name").text();  //nav_header_image_name
		console.log(" 16 Default  headerimage "+headerimage +" HeadeImageUrl "+HeadeImageUrl);
	}


	console.log("HeadeImageUrl"+ HeadeImageUrl);
	return HeadeImageUrl;
}



//======================================================================Abhishek -  8-Oct-2015==================================================================================================================================================
function Setbackground_Img(AppBackgroundImgg)
{
	//Port_Default_PHONE, Port_Default_TAB, Port_MDPI_320x480px, Port_HDPI_480x800px, Port_XHDPI_720x1280px, Port_XXHDPI_1080x1920px, Port_TAB_600x1024px, Port_TAB_720x1280px
	//Land_Default_PHONE, Land_Default_TAB, Land_MDPI_480x320px, Land_HDPI_800x480px, Land_XHDPI_1280x720px, Land_XXHDPI_1920x1080px, Land_TAB_1024x600px, Land_TAB_1280x720px
	var AppBackgroundImg=AppBackgroundImgg;
	var AppBackground='';
	console.log("Abhishek 00----------------------------"+AppBackgroundImg);


	// ----------------------------------------- default Img--------------------------------------------------------
	if(AppBackgroundImg == 'Port_Default_PHONE')
	{
		AppBackground = $(masterData).find("appBackground").text();
		console.log("Abhishek 0  Port_Default_PHONE----------------------------"+AppBackground);
	}
	else if(AppBackgroundImg == 'Land_Default_PHONE')
	{
		AppBackground = $(masterData).find("appBackground").text();
		console.log("Abhishek 2  Land_Default_PHONE----------------------------"+AppBackground);
	}
	else if(AppBackgroundImg == 'Port_Default_TAB')
	{
		AppBackground = $(masterData).find("appIpadBackground").text();
		console.log("Abhishek 3  Port_Default_TAB----------------------------"+AppBackground);
	}
	else if(AppBackgroundImg == 'Land_Default_TAB')
	{
		AppBackground = $(masterData).find("appIpadBackground").text();
		console.log("Abhishek 4  Land_Default_TAB----------------------------"+AppBackground);
	}



	// ----------------------------------------- Accourding to Device  Img--------------------------------------------------------
	else if(AppBackgroundImg == 'Port_MDPI_320x480px')
	{
		AppBackground = $(masterData).find("customBackgroundImage").find("bg_320_480").text();
		console.log("Abhishek 5  Port_MDPI_320x480px----------------------------"+AppBackground);
	}

	//tested on my device
	else if(AppBackgroundImg == 'Port_HDPI_480x800px')
	{
		AppBackground = $(masterData).find("customBackgroundImage").find("bg_480_800").text();
		console.log("Abhishek 6  Port_HDPI_480x800px----------------------------"+AppBackground);
	}


	//test on krisna sir device 730x1196  and amritesh device
	else if(AppBackgroundImg == 'Port_XHDPI_720x1280px')
	{
		AppBackground = $(masterData).find("customBackgroundImage").find("bg_720_1280").text();
		console.log("Abhishek 7  Port_XHDPI_720x1280px----------------------------"+AppBackground);
	}
	else if(AppBackgroundImg == 'Port_TAB_720x1280px')
	{
		AppBackground = $(masterData).find("customBackgroundImage").find("bg_720_1280").text();
		console.log("Abhishek 10  Port_TAB_720x1280px----------------------------"+AppBackground);
	}


	//tested on my device
	else if(AppBackgroundImg == 'Land_HDPI_800x480px')
	{
		AppBackground = $(masterData).find("customBackgroundImage").find("bg_800_480").text();
		console.log("Abhishek 12  Land_HDPI_800x480px----------------------------"+AppBackground);
	}


	// test on krisna sir device  and amritesh device.
	else if(AppBackgroundImg == 'Land_XHDPI_1280x720px')
	{
		AppBackground =  $(masterData).find("customBackgroundImage").find("bg_1334_750").text();
		console.log("Abhishek 13  Land_XHDPI_1280x720px----------------------------"+AppBackground);
		//alert("122"+AppBackground)
	}
	else if(AppBackgroundImg == 'Land_TAB_1280x720px')
	{
		AppBackground =  $(masterData).find("customBackgroundImage").find("bg_1334_750").text();
		console.log("Abhishek 20  Land_TAB_1280x720px----------------------------"+AppBackground);
	}




//tested on 6-inch dtab device
	else if(AppBackgroundImg == 'Port_TAB_600x1024px')
	{
		AppBackground =   $(masterData).find("customBackgroundImage").find("bg_640_1136").text(); ;
		console.log("Abhishek 9  Port_TAB_600x1024px----------------------------"+AppBackground);
	}
	else if(AppBackgroundImg == 'Land_TAB_1024x600px')
	{
		AppBackground =   $(masterData).find("customBackgroundImage").find("bg_1136_640").text();
		console.log("Abhishek 19  Land_TAB_1024x600px----------------------------"+AppBackground);
	}



	//tested on kr sir and dheeraj device
	else if(AppBackgroundImg == 'Port_XXHDPI_1080x1920px')
	{
		AppBackground =   $(masterData).find("customBackgroundImage").find("bg_1242_2208").text();
		console.log("Abhishek 8  Port_XXHDPI_1080x1920px----------------------------"+AppBackground);
	}
	else if(AppBackgroundImg == 'Land_XXHDPI_1920x1080px')
	{
		AppBackground =   $(masterData).find("customBackgroundImage").find("bg_2208_1242").text();
		console.log("Abhishek 18  Land_XXHDPI_1920x1080px----------------------------"+AppBackground);
	}



	else if(AppBackgroundImg == 'Port_XXXHDPI')
	{
		AppBackground =   $(masterData).find("customBackgroundImage").find("bg_1536_2048").text();
		console.log("Abhishek 8  Port_XXXHDPI----------------------------"+AppBackground);
	}
	else if(AppBackgroundImg == 'Land_XXXHDPI')
	{
		AppBackground =   $(masterData).find("customBackgroundImage").find("bg_2048_1536").text();
		console.log("Abhishek 18  Land_XXXHDPI----------------------------"+AppBackground);
	}


	else
	{
		AppBackground = $(masterData).find("appBackground").text();
		console.log("Abhishek 23  ----------------------------"+AppBackground);
	}
	// ----------------------------------------- If Image is Not assign to  AppBackground Img--------------------------------------------------------
	var isPortraitt=sessionStorage.getItem("orientationstatus");
	if(AppBackground=='')
	{
		if(isPortraitt=='0' || isPortraitt=='180')
		{
			AppBackground = $(masterData).find("appBackground").text();
			console.log("Abhishek 24  ----------------------------"+AppBackground);
		}
		else
		{
			AppBackground = $(masterData).find("appIpadBackground").text();
			console.log("Abhishek 25  ----------------------------"+AppBackground);
		}
	}

	if(AppBackground=='' || AppBackground.length<10)
	{
          var backgroundType_image_color=$(masterData).find("backgroundType").text();
			sessionStorage.setItem("backgroundType_image_color",backgroundType_image_color);

			AppBackground = $(masterData).find("appBackground").text();
			console.log("Abhishek 24  ----------------------------"+AppBackground);
	}
	console.log("Abhishek Final 26  ----------------------------"+AppBackground);
	//alert("AppBackgroundImg "+AppBackgroundImg +" \n   AppBackground "+AppBackground);
	return AppBackground;
}

function Showhideupdatebutton()
{
	var appId=localStorage.getItem("applicationID");
    console.log("applicationID for hide right update button Abhishek "+localStorage.getItem("applicationID"));
    if(appId == 'ed33b19831c5' || appId == 'e0c2d735f46a' || appId=='cc3052bb8850' || appId=='ade727c1484c' || appId=='12667f32a13c' || appId=='6fce880ac2c6' || appId=='dba50f8ea3be' || appId=='a803984e3524' || appId=='1875201f2941')
    {
    	console.log("application id  found Abhishek");
    	$("#reload").hide();
    	$('#supValueChange').empty();
    	$('#supValueChange').hide();
    }
    else
    {
    	console.log("application  id not found Abhishek");
    	$("#reload").show();
    }
    // Showhideupdatebutton();
}
function isPortrait()
{
	return window.innerHeight > window.innerWidth;
}

function openEmail(mailid,subject,body,cc,bcc)
{
var data=mailid+"######"+subject+"######"+body;
toaster.sendemail(data);
}

function openCall(number)
{
window.location="showtel:"+number+"";
}

function resetOrientation() {
/*
 if(localStorage.getItem("layout").indexOf('matrix') != -1 && localStorage.getItem("userLoginStatus")=="true" && localStorage.getItem('layoutHeader') != "YES" && (localStorage.getItem("layout") != "imgmatrix"))
    {
 setTimeout(function(){
  callOnOrientation();
   },100);
   }
   else
   {
   setTimeout(function(){
   resetDoc();
   },100);
   }
   */
}


/***   		Facebook Integration Start Here          ****/

function fblogin(){
	console.log("Facebook Login .......");
	toaster.loginToFacebook("FbLogin");
}

var loginstatus="";
function LoginWithFBUserID(name, email, fbuserId)
{
	// This method is called from Native....
    $("#logo").show();
    $("#mainback").hide();
//    var wsUrl = "http://"+window.localStorage.getItem("reseller")+"/services/app-user-soap#checkUserActiveStatus/";
//	var wsUrl = "http://angularml.pbodev.info/services/app-user-soap#checkUserActiveStatus/";
//  var email = document.getElementsByName('EmailId')[0].value;
	window.localStorage.setItem("fbId",fbuserId);
    loadFBUser(name, email, fbuserId);

}

function registerFBUser(name, email, fbId){
		console.log("registerFBUser method call...>>>Email Id or Name is blank.....");
		console.log("Email "+email);
		console.log("name "+name);
		console.log("fbuserId "+fbId);
		window.localStorage.setItem("fbId", fbId);
	 $("#contentHolder25").empty();
   var html='<div class="appypie-login"><h2 id="fbsignS"></h2><div class="login-feald"><label id="fbsignN"><sup class="red-astrick">*</sup></label><input data-role="none"  type="text"  name="name" id="fbcustomname" onclick="removeFocusall(this)" /></div><div class="login-feald"><label id="fbsignE"><sup class="red-astrick">*</sup></label><input data-role="none" type="text" name="email" id="fbemail"   onclick="removeFocusall(this)"/></div><div class="login-feald"><label id="fbsignPn"><sup class="red-astrick"></sup></label><input data-role="none" type="tel" placeholder="000-000-0000" name="contact" id="fbcontact" onclick="removeFocusall(this)"/></div><div id="fbcustomRegisterField" onclick="removeFocusall(this)"></div><a class="submit_btn" onclick="signupforFB();" id="fbsignSU"></a></div> <br /><div class="fb-div"><p id="fbsignA"></p> <a onclick="showLoginPage();" id="fbsignL" ></a></div></div>';

	appendHtml(html,25,1);
//	appendHtml(html,pageNo,pageLevel,pageName)
//	$("#contentHolder25").append(html);
//	$('#popup-outside-page1').popup({ positionTo: "window" });

    document.getElementById("fbsignS").innerHTML="User Info";
    document.getElementById("fbsignN").innerHTML=localStorage.getItem("name");
    document.getElementById("fbsignE").innerHTML=localStorage.getItem("email");
    document.getElementById("fbsignSU").innerHTML= "Submit";
    document.getElementById("fbsignL").innerHTML= localStorage.getItem("signintext");
	document.getElementById("fbsignA").innerHTML= localStorage.getItem("alreadyaccounttext");
	document.getElementById("fbsignPn").innerHTML= localStorage.getItem("phoneNo");

    document.getElementById("fbcustomname").value = name;
	document.getElementById("fbemail").value = email;

}

function loadFBUser(name, email, fbuserId){
	 var password = document.getElementsByName('Password')[0].value;
	 localStorage.setItem("fromfacebookloginstatus","true");
	 fromfacebookloginstatus=true;
	 	console.log("User Name " + name);
		console.log("User Email " + email);
   var wsUrl = "http://"+window.localStorage.getItem("reseller")+"/services/app-user-soap#checkUserActiveStatus/";
//	 var wsUrl = "http://angularml.pbodev.info/services/app-user-soap#checkUserActiveStatus/";
	if(fbuserId == "")
    {

        //alert( 'Email id and password must not be blank.');
        navigator.notification.alert(
        		ffacebookauthenticateagain_code,
                                     alertDismissed,
                                     aalert_code,
                                     ookk_code
                                     );
    }else{
		console.log("FB Login Email Id set to fooduserid "+email);
        window.localStorage.setItem("fooduserid",email);
		window.localStorage.setItem("fbemailId",email);
		window.localStorage.setItem("fbuserName",name);
	//	window.localStorage.setItem("fbId",fbuserId);
        var autoStatus=localStorage.getItem("emailverification");

      var soapRequest =
        '<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><checkUserActiveStatus xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/app-user-soap#checkUserActiveStatus/\"><email>'+email+'</email><password>'+password+'</password><appId>'+window.localStorage.getItem("applicationID")+'</appId><authoStatus>'+autoStatus+'</authoStatus><profileId>'+fbuserId+'</profileId></checkUserActiveStatus></soap:Body></soap:Envelope>';

        console.log("Request " + soapRequest);
		console.log("User Id FB " + fbuserId);
        jQuery.support.cors = true;
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               dataType: "text",
               data: soapRequest,
               success: processSuccessxmlupdatelogin,
               error: processErrorxmlupdatelogin
               });

		}
}

function signupforFB(fbUserId, flag){
		var name = "";
		var email = "";
		var contact = "";
		var password = "";
		var cpassword = "";
		var profileFBID = localStorage.getItem("fbId");;
		localStorage.setItem("SignUp","FB");
	if(flag != "1"){
		len=RegcustomElementsType.length;
		var name = document.getElementsByName('name')[0].value;
		var email = document.getElementsByName('email')[0].value;
		var contact = document.getElementsByName('contact')[0].value;
		var password = document.getElementsByName('email')[0].value;
		var cpassword = document.getElementsByName('email')[0].value;
	}
	else{
		console.log("signupforFB flag is Not null...");
				name = localStorage.getItem("fbuserName");
				email = localStorage.getItem("fbemailId");
				password = localStorage.getItem("fbemailId");
				cpassword = localStorage.getItem("fbemailId");
				profileFBID = localStorage.getItem("fbId");

	}
//	startRegistration(name,email,password,contact,profileidFB);
	console.log("signupforFB User Id "+fbUserId);
	console.log("signup for FB ... fbUserId "+profileFBID);
	sessionStorage.setItem("signUpName",name);
	sessionStorage.setItem("signUpemail",email);
	sessionStorage.setItem("signUpcontact",contact);
	sessionStorage.setItem("signUppassword",password);


     if(email== null || email== '' || !validateForm1(email))
    	{


			console.log("signup for FB ... Email "+email);
    			navigator.notification.alert(
    					pppleasentervalidmail_code,
                alertDismissed,
                aalert_code,
                ookk_code
                );
    			return false;
    	}
       else{
    	for(var i=0;i<len;i++){
        if(RegcustomElementsType[i]=="name" || RegcustomElementsType[i]=="email" || RegcustomElementsType[i]=="phone" || RegcustomElementsType[i]=="state" || RegcustomElementsType[i]=="text"){

    	 var txt=document.getElementById(i).value.trim();

    	 if (txt == '' && RegcustomElementsMandatory[i]==1) {

    		 alertPopupforRegister(RegcustomElementsLabel[i]);
    	/* openPopup();
                         document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                         document.getElementById("errorPopup").style.display = 'block';
                         document.getElementById("scuccessPopup").style.display = 'none';
                         setFocus(i);*/
    		 setFocuscustom(i);
    		return false;
       }
    	 else if(RegcustomElementsType[i]=="phone")
    	   {

    	   		if(!IsNumeric1(txt))
    	   			{

    	   			navigator.notification.alert(
    	   		            '*"'+RegcustomElementsLabel[i]+'"'+notvvvalid_code,
    	   		            alertDismissed,
    	   		         aalert_code,
    	   		      ookk_code
    	   		            );
    	   			/*openPopup();
    	   			document.getElementById("errorPopup").innerHTML=ErrorPhoneField;
                  document.getElementById("errorPopup").style.display = 'block';
                  document.getElementById("scuccessPopup").style.display = 'none';*/
                  setFocuscustom(i);
                  return false;
    	   			}


    	   }

    	 else if(RegcustomElementsType[i]=="email")
    	 {


    	   if(!validateForm1(txt))
    			{

    		   		navigator.notification.alert(
	   		            '*"'+RegcustomElementsLabel[i]+'"'+nnnotvalidemail_code,
	   		            alertDismissed,
	   		         aalert_code,
	   		      ookk_code
	   		            );
    		   	/*openPopup();
    			document.getElementById("errorPopup").innerHTML=ErrorEmailField;
    			document.getElementById("errorPopup").style.display = 'block';
    			document.getElementById("scuccessPopup").style.display = 'none';*/
    			setFocuscustom(i);
    			return false;
    			}
    	   else{

    		   userEmail=txt;
    	   		}


    	 }

    	  RegcustomElementsValue[i]=txt;
    	}
    	 else if(RegcustomElementsType[i]=="date"){

    	  var txt=document.getElementById(i).value;

    	 if (txt == '' && RegcustomElementsMandatory[i]==1) {
    		 alertPopupforRegister(RegcustomElementsLabel[i]);
    		 			/*openPopup();
                         document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                         document.getElementById("errorPopup").style.display = 'block';
                         document.getElementById("scuccessPopup").style.display = 'none';*/
                         setFocuscustom(i);
    		return false;
       }
    	   RegcustomElementsValue[i]=txt;

    	   }
    	    else if(RegcustomElementsType[i]=="time"){

    	  var txt=document.getElementById(i).value.trim();

    	 if (txt == '' && RegcustomElementsMandatory[i]==1) {
    		 alertPopupforRegister(RegcustomElementsLabel[i]);
    		 			/*	openPopup();
                         document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                         document.getElementById("errorPopup").style.display = 'block';
                         document.getElementById("scuccessPopup").style.display = 'none';*/
                         setFocuscustom(i);
    		return false;
       }
    	  RegcustomElementsValue[i]=txt;

    	   }
    	      else if(RegcustomElementsType[i]=="textarea"){

    	  var txt=document.getElementById(i).value.trim();

    	 if (txt == '' && RegcustomElementsMandatory[i]==1) {
    		 alertPopupforRegister(RegcustomElementsLabel[i]);

    		 			/*openPopup();
                         document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                         document.getElementById("errorPopup").style.display = 'block';
                         document.getElementById("scuccessPopup").style.display = 'none';*/
                         setFocuscustom(i);
    		return false;
       }
    	 RegcustomElementsValue[i]=txt;

    	   }
    	    else if(RegcustomElementsType[i]=="gender"){

    	  var txt=document.getElementById(i).value.trim();

    	 if (txt == 'Select Gender' && RegcustomElementsMandatory[i]==1) {
    		 alertPopupforRegister(RegcustomElementsLabel[i]);
    		 			/*openPopup();
                         document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                         document.getElementById("errorPopup").style.display = 'block';
                         document.getElementById("scuccessPopup").style.display = 'none';*/
                         setFocuscustom(i);
    		return false;
       }
    	  RegcustomElementsValue[i]=txt;

    	   }
    	    else if(RegcustomElementsType[i]=="country"){

    	  var txt=document.getElementById(i).value.trim();
    	 //alert(txt);
    	 if (txt == 'Select Country' && RegcustomElementsMandatory[i]==1) {
    		 alertPopupforRegister(RegcustomElementsLabel[i]);
    		 			/*openPopup();
                         document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                         document.getElementById("errorPopup").style.display = 'block';
                         document.getElementById("scuccessPopup").style.display = 'none';*/
                         setFocuscustom(i);
    		return false;
       }

    	    RegcustomElementsValue[i]=txt;
    	   }
    	   else if(RegcustomElementsType[i]=="checkbox"){

    	  var favorite = [];

    	  var tempNam=RegcustomElementsLabel[i];

               $.each($("input[name="+ tempNam.replace(/[^A-Za-z]+/g, 'X')+"]:checked"), function(){

                   favorite.push($(this).val());

               });
              // alert("My favourite sports are: " + favorite.join(", "));

    		   if(favorite.length == 0 && RegcustomElementsMandatory[i]==1){
    			   alertPopupforRegister(RegcustomElementsLabel[i]);
    			   		/*openPopup();
                         document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                         document.getElementById("errorPopup").style.display = 'block';
                         document.getElementById("scuccessPopup").style.display = 'none';*/
    		return false;

    		   }
    	       RegcustomElementsValue[i]=favorite.join(",");


    	   }  else if(RegcustomElementsType[i]=="radio"){

    	  var favorite = [];
    	  var tempNam=RegcustomElementsLabel[i];
               $.each($("input[name="+tempNam.replace(/[^A-Za-z]+/g, 'X')+"]:checked"), function(){
                   favorite.push($(this).val());
               });

    			  if(favorite.length == 0 && RegcustomElementsMandatory[i]==1){
    				  alertPopupforRegister(RegcustomElementsLabel[i]);
    				  	/*openPopup();
                         document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                         document.getElementById("errorPopup").style.display = 'block';
                         document.getElementById("scuccessPopup").style.display = 'none';*/
    		return false;

    		   }
    			 RegcustomElementsValue[i]=favorite[0];
              // alert("My favourite Radio are: " + favorite.join(", "));



    	   }
    	   else if(RegcustomElementsType[i]=="select"){


    	var e = document.getElementById(i);
        var txt = e.options[e.selectedIndex].text;

    	  if (txt == '' && RegcustomElementsMandatory[i]==1) {
    		  alertPopupforRegister(RegcustomElementsLabel[i]);
    		  				/*openPopup();
                         document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                         document.getElementById("errorPopup").style.display = 'block';
                         document.getElementById("scuccessPopup").style.display = 'none';*/
                         setFocuscustom(i);
    		return false;
       }
    	  RegcustomElementsValue[i]=txt;



    	   }
    	   else if(RegcustomElementsType[i]=="uploadPicture"){
    		   var txt=document.getElementById(i).innerHTML.trim();

    		   if (txt == 'Attach File' && RegcustomElementsMandatory[i]==1) {
    			   alertPopupforRegister(RegcustomElementsLabel[i]);
    			       	/*openPopup();
                         document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                         document.getElementById("errorPopup").style.display = 'block';
                         document.getElementById("scuccessPopup").style.display = 'none';*/
                         setFocuscustom(i);
    					return false;
    			   }
    		   // fileName=txt;

    		   if(txt == 'Attach File')
    			   {

    			   RegcustomElementsValue[i]='';
    			   }
    		   else{
    			   RegcustomElementsValue[i]=txt;

    		   }


    	   }

    }

    }


    var jsonData = {};
    var jsonData2 = {};
    var jsonData3={};
    for(var k=0;k<len;k++)
    {
    	if(typeof RegcustomElementsLabel[k] =='undefined')
	 	{


	 	}
	 	else{

	 		jsonData3[k]=RegcustomElementsType[k];
	 		jsonData2[k]=RegcustomElementsLabel[k].replace('&','and');
	 		jsonData[k] = RegcustomElementsValue[k];
	 	}
    }

 	var mediaFiles = Regglobalimageurl;
 	 RegfileName = Math.random()+'_'+Regglobalimageurl.split("/").pop();
 	var fileURI = mediaFiles;

	sessionStorage.setItem("uploadFileCount",uploadFileCount);
	sessionStorage.setItem("globalExactPath",globalExactPath);
	sessionStorage.setItem("jsonData",JSON.stringify(jsonData));
	sessionStorage.setItem("jsonData2",JSON.stringify(jsonData2));
	sessionStorage.setItem("jsonData3",JSON.stringify(jsonData3));
	checkForPaymentOrRegister(name,email,password,contact, profileFBID);
}

function checkPaymentModeForFB(){
	var totalPaymentmode=0;
	var defaultCurrency="USD";

	console.log("CheckPaymentModeForFB "+localStorage.getItem("paymentMethod"));
	document.getElementById("subscription_title").innerHTML="Choose Your Payment Type:";
		if(localStorage.getItem("paymentMethod")=='paypal_express')
		{

        if(localStorage.getItem("subsPriceMonthly").trim()!="")
        {
			totalPaymentmode++;
            if(localStorage.getItem("subsCurrencyMonthly"))
            {
                defaultCurrency=localStorage.getItem("subsCurrencyMonthly");
            }
            var monthText='<div class="sub_plan" onClick="inAppSignup(\''+'monthlySub'+'\',\''+localStorage.getItem("subsPriceMonthly").trim()+'\',\''+defaultCurrency+'\');">'+defaultCurrency+' <span>'+localStorage.getItem("subsPriceMonthly").trim()+'</span>/Month</div>';
            document.getElementById("monthlyfb").innerHTML=monthText;
        }
		else
        {
			document.getElementById("monthlyfb").style.display = 'none';
        }

        if( localStorage.getItem("subsPriceYearly").trim()!="")
        {
			totalPaymentmode++;
            if(localStorage.getItem("subsCurrencyYearly"))
            {
                defaultCurrency=localStorage.getItem("subsCurrencyYearly");
            }

            var yearText='<div class="sub_plan" onClick="inAppSignup(\''+'yearlySub'+'\',\''+localStorage.getItem("subsPriceYearly").trim()+'\',\''+defaultCurrency+'\');">'+defaultCurrency+' <span>'+localStorage.getItem("subsPriceYearly").trim()+'</span>/Year</div>';
            document.getElementById("yearlyfb").innerHTML=yearText;
        }
		else
        {
			document.getElementById("yearlyfb").style.display = 'none';
        }

        if(localStorage.getItem("editionPrice").trim()!="")
        {
			//console.log("editionPrice..>>> ");
            totalPaymentmode++;
            if(localStorage.getItem("editionCurrency"))
            {
                defaultCurrency=localStorage.getItem("editionCurrency");
            }
            var editionText='<div class="sub_plan" onClick="inAppSignup(\''+'onetimepayment'+'\',\''+localStorage.getItem("editionPrice").trim()+'\',\''+defaultCurrency+'\');">'+defaultCurrency+' <span>'+localStorage.getItem("editionPrice").trim()+'</span>/Time</div>';
            document.getElementById("onetimefb").innerHTML=editionText;
        }
		else
        {
			//console.log("Else editionPrice..>>> ");
            document.getElementById("onetimefb").style.display = 'none';
        }
      }
      else
      {
        if(localStorage.getItem("inapMonthlySub") && localStorage.getItem("inapMonthlySub").trim()!="" &&  localStorage.getItem("subsPriceMonthly").trim()!="")
        {
            totalPaymentmode++;
            if(localStorage.getItem("subsCurrencyMonthly"))
            {
                defaultCurrency=localStorage.getItem("subsCurrencyMonthly");
            }
            var monthText='<div class="sub_plan" onClick="inAppSignup(\''+'monthlySub'+'\',\''+localStorage.getItem("subsPriceMonthly").trim()+'\',\''+defaultCurrency+'\');">'+defaultCurrency+' <span>'+localStorage.getItem("subsPriceMonthly").trim()+'</span>/Month</div>';
            document.getElementById("monthlyfb").innerHTML=monthText;
        }
		else
        {
            document.getElementById("monthlyfb").style.display = 'none';
        }

        if(localStorage.getItem("inapYearlySub") && localStorage.getItem("inapYearlySub").trim()!="" &&  localStorage.getItem("subsPriceYearly").trim()!="")
        {
            totalPaymentmode++;
            if(localStorage.getItem("subsCurrencyYearly"))
            {
                defaultCurrency=localStorage.getItem("subsCurrencyYearly");
            }

            var yearText='<div class="sub_plan" onClick="inAppSignup(\''+'yearlySub'+'\',\''+localStorage.getItem("subsPriceYearly").trim()+'\',\''+defaultCurrency+'\');">'+defaultCurrency+' <span>'+localStorage.getItem("subsPriceYearly").trim()+'</span>/Year</div>';
            document.getElementById("yearlyfb").innerHTML=yearText;
        }
        else
        {
            document.getElementById("yearlyfb").style.display = 'none';
        }

        if(localStorage.getItem("anppAddProductId") && localStorage.getItem("anppAddProductId").trim()!="" &&  localStorage.getItem("editionPrice").trim()!="")
        {
            totalPaymentmode++;
            if(localStorage.getItem("editionCurrency"))
            {
                defaultCurrency=localStorage.getItem("editionCurrency");
            }
            var editionText='<div class="sub_plan" onClick="inAppSignup(\''+'onetimepayment'+'\',\''+localStorage.getItem("editionPrice").trim()+'\',\''+defaultCurrency+'\');">'+defaultCurrency+' <span>'+localStorage.getItem("editionPrice").trim()+'</span>/Time</div>';
            document.getElementById("onetimefb").innerHTML=editionText;
        }
		 else
        {
            document.getElementById("onetimefb").style.display = 'none';
        }
	  }

      console.log("TotalPay "+totalPaymentmode);
      if(parseFloat(totalPaymentmode)>0)
      {
        console.log("popup will open for payment");
        $('#popup-outside-page2').popup('open');
      }
}

function openTheWebsiteOnNewPage(zopImURL)
{
//window.location="playvimeo:"+zopImURL;
toaster.openTheWebsiteOnNewPage(zopImURL);
}


function returnIconImageHtmlforslid(imageUrl,color)
{
 var imageHtml='';
 if (imageUrl.indexOf('.') != -1)
 {
  imageHtml ='<img  src="'+ imageUrl+'"  />';
 }
 else
 {
  imageHtml = '<i  class="' + imageUrl + '" style="color:'+color+' !important"/></i>';
 }
 return imageHtml;
}

function dynamicCSS(cssvalue){
 if(cssvalue != ' ' || cssvalue != undefined || cssvalue != null )
 {
  $("#dynamic").attr('href','css/'+cssvalue+'.css');
 setTimeout(function(){
 if(mobileADS)
 toaster.changeMarginBottomForAdMob("other");
 },100);

 }
 else
{
 $("#dynamic").attr('href','');
 setTimeout(function(){
if(mobileADS)
{
if(localStorage.getItem("layout")=="bottom")
 {
 toaster.changeMarginBottomForAdMob("bottom");
 }
 else
 {
 toaster.changeMarginBottomForAdMob("other");
 }
 }
  },100);
}
}

function stopScroll(){
    $("div [data-role='page']").attr('data-snap-ignore','true');
}

function allowScroll(){
    $("div [data-role='page']").removeAttr('data-snap-ignore');
}

/*             Changes by Ravi 					*/

var gFonts = [
  	{font:'Permanent Marker', class:"permanentmarker"},
  	{font:'Lobster', class:"lobster"},
  	{font:'Indie Flower', class:"indieflower"},
  	{font:'Pacifico', class:"pacifico"},
  	{font:'Orbitron', class:"orbitron"},
  	{font:'Dancing Script', class:"dancingscript"},
  	{font:'Dosis', class:"dosis"},
  	{font:'Poiret One', class:"poiretone"},
  	{font:'Kaushan Script', class:"kaushanscript"},
  	{font:'Satisfy', class:"satisfy"},
  	{font:'Courgette', class:"courgette"},
  	{font:'Seaweed Script', class:"seaweedscript"}
]

function gFontsCheck(gFont) {
  	var gFontExit = false;
  	$.each(gFonts, function(gIndex, gValue){
  		if(gFont == gValue.class)
  		{
  			gFontExit = true;
  		}
  	})
  	if(gFontExit)
  	{
  		if(checkNetworkConnection('true'))
		{
			return gFont;
		}
		else
		{
			return "georgia";
		}
  	}
  	return gFont;
}

$.fn.gFontsLoad = function(allData) {
var docHead = $("head");
var strFonts = "headerBarFont,navigationFont,appPageFont";
strFonts = strFonts.split(",");
strFonts = $.map(strFonts, function(value, index) {return $(allData).find(value).text().toLowerCase()});
var gFontsData = [];
$.each(gFonts, function(gIndex, gValue){
	$.each(strFonts, function(index, value){
		if(value == gValue.class)
		{
			var thisFont = gValue.font.replace(/ /g, "+");
			if($.inArray(thisFont, gFontsData) == -1)
			{
				gFontsData.push(thisFont);
			}
		}
	})
})
//https://fonts.googleapis.com/css?family=Montserrat|Raleway|Dosis|Poiret+One|Lobster+Two|Kaushan+Script
if(gFontsData.length != 0)
{
	if(!checkNetworkConnection('true'))
	{
		return false;
	}
	var gFontsPath = "https://fonts.googleapis.com/css?family=";
	if($("#g-fonts").size() == 0)
	{
		docHead.append('<link id="g-fonts" href="" rel="stylesheet" type="text/css">');
	}
	$("#g-fonts").attr("href", gFontsPath+gFontsData.join("|"));
  	}
 }


function showHideAd(showHide){
	toaster.showAndHideAdView(showHide);
}

