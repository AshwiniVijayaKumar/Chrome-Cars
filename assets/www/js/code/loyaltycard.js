/////////////////LoyaltyCard////////////////////////

//******************* Dynamic var********************//

var loyaltycardMobHeaderLabel = "Membership Request Form s";
var loyaltycardMobNameLabel = "Name";
var loyaltycardMobEmailLabel = "Email ";
var loyaltycardMobPhoneLabel = "Phone No";

var serverresponse_card = 'Server Response';
var yourcardtemporarydisapproved_card = 'Your Card is Temporary Disapproved';
var unableloaddata_card = 'Unable to load Data!';
var joinloyaltyprogram_card = "Join Our Loyalty Program"; 
var datanotfound_card = 'Data not found!';
var alert_card = 'Alert';
var pleasephonenumber_card = 'Please Enter a Valid Phone Number';
var issuedate_card = "Issue Date";
var id_card = "ID: ";
var validtill_card = "Valid Till";
var point_card = "Points";
var balance_card = "Balance";
var scancode_card = "Scan the QR Code";
var add_card = "Add";
var redeem_card = "Redeem";
var ok_card = 'OK';
var mandatoryfieldsblank_card = 'Mandatory fields should not be blank.';
var entervalid_card = 'Enter a valid Email Id';
var availablepoints_card = "Available points: ";
var pleasehandcashieraddpoints_card = "Please hand over your device to the cashier to add points.";
var validatetypingsecuritycode_card = "Validate by typing the Security code";
var validatescanningqrcode_card = "Validate by scanning the QR Code";
var cancel_card = "Cancel";
var enterpoints_card = "Enter points";
var entersecuritycode_card = "Enter security code";
var pleasehanddeviceredeempoints_card = "Please hand over your device to the cashier to Redeem points.";
var warning_card = 'Warning';
var pleaseentervalidunlockcode_card = 'Please enter valid unlock code';
var insufficientpoints_card = 'Insufficient points';
var pleaseenterredeemvaluegreater_card = 'Please enter redeem value greater then 0';
var pleaseenteraddvaluegreater_card = 'Please enter add value greater then 0';
var pleasetryagain_card = 'Please try again';
var pleaseenterpoints_card = 'Please enter points';
var nofunctionalityimplemented_card = 'no functionality implemented';
  




var totalpoints=0;
var loyaltycardUnlockCode='';
var globalindex='';
lifetime1 = "Life Time";
var loyaltypointenabled=1;
var qrcodeenabled=1;
var appbg=$(masterData).getElementById("appBackground").text();
function getloyaltycardPage(index)
{
 if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    globalindex=index;
    var couponHtml='';
    
    $data=$(masterData).find( "loyaltyCard[indexval="+index+"]");
	if($data.find("loyaltyCardRow").find("loyaltycardMobHeaderLabel").text())
    {
    	loyaltycardMobHeaderLabel=$data.find("loyaltyCardRow").find("loyaltycardMobHeaderLabel").text();
    	console.log("dheeraj header"+loyaltycardMobHeaderLabel);
    	
    }
	if($data.find("loyaltyCardRow").find("loyaltycardMobHeaderLabel").text())
    {
    	loyaltycardMobHeaderLabel=$data.find("loyaltyCardRow").find("loyaltycardMobHeaderLabel").text();
    	console.log("dheeraj header"+loyaltycardMobHeaderLabel);
    	
    }
    
    if($data.find("loyaltyCardRow").find("loyaltycardMobNameLabel").text())
    {
    	loyaltycardMobNameLabel=$data.find("loyaltyCardRow").find("loyaltycardMobNameLabel").text();
    	console.log("dheeraj name"+loyaltycardMobNameLabel);
    }
    
    if($data.find("loyaltyCardRow").find("loyaltycardMobEmailLabel").text())
    {
    	loyaltycardMobEmailLabel=$data.find("loyaltyCardRow").find("loyaltycardMobEmailLabel").text();
    	console.log("dheeraj email"+loyaltycardMobEmailLabel);
    }
    
    if($data.find("loyaltyCardRow").find("loyaltycardMobPhoneLabel").text())
    {
    	loyaltycardMobPhoneLabel=$data.find("loyaltyCardRow").find("loyaltycardMobPhoneLabel").text();
    	console.log("dheeraj phone"+loyaltycardMobPhoneLabel);
    }
	
	//change by deepak
	if($data.find("loyaltyCardRow").find("loyaltycardUnlockCode").text())
    {
        loyaltycardUnlockCode=$data.find("loyaltyCardRow").find("loyaltycardUnlockCode").text();
        
    }
    if($data.find("loyaltyCardRow").find("loyaltypointenabled").text())
    {
        loyaltypointenabled=$data.find("loyaltyCardRow").find("loyaltypointenabled").text();
        sessionStorage.setItem("loyaltypointenabled",loyaltypointenabled)
        
    }
    else{
        sessionStorage.setItem("loyaltypointenabled",loyaltypointenabled)
    }
	 if($data.find("loyaltyCardRow").find("qrcodeenabled").text())
    {
        qrcodeenabled=$data.find("loyaltyCardRow").find("qrcodeenabled").text();
        sessionStorage.setItem("qrcodeenabled",qrcodeenabled)
        
    }
    else{
        sessionStorage.setItem("qrcodeenabled",qrcodeenabled)
    }
		
    var funcName='';
    var counter=0;
    
    $data.find("loyaltyCardRow").each(function()
                                      {
                                      var couponHeading=$(this).find('loyaltycardHeading').text();
                                      var couponvalidtill=$(this).find('couponvalidtill').text();
                                      var couponValidTo=$(this).find('loyaltyCardTillDate').text();
                                      couponHtml+='<div class="coupon-category" onclick="getloyaltycardPageDataNew('+index+','+counter+');"><h4>'+couponHeading+'</h4><div class="coupon-cat-date"><span>'+couponValidTo+'</span></div></div>';
                                      counter=parseInt(counter)+1;
                                      });
    
    
    if(counter == 1)
    {
        sessionStorage.setItem('singlePage','true');

        getloyaltycardPageDataNew(index,0);
        
    }
    else
    {
        appendHtml(couponHtml,'',1);
    }
    
    
}

function getloyaltycardPageDataNew(index,pageIndex)
{
	//code start from here change by amritesh on 13-01-2016
	
		$('.appypie-loader').show();
    $data=$(masterData).find( "loyaltyCard[indexval="+index+"]");
    $pageData=$data.find("loyaltyCardRow[indexval="+pageIndex+"]");
    var loyaltycardUniqueId=$pageData.find('loyaltycardUniqueId').text();
    sessionStorage.setItem('loyaltycardUniqueId',loyaltycardUniqueId);
	deviceToken=device.uuid;
	
	var emailUser = localStorage.getItem('loyalityCardUserEmail');
		
		
        var globalemail='';
        if(localStorage.getItem("loyalityCardUserEmail"))
        {
            globalemail=localStorage.getItem('loyalityCardUserEmail');
        }
        else
        {
            globalemail='';
        }

        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/loyalty-soap#loyalityCheckXml";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><loyalityCheckXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/loyalty-soap#loyalityCheckXml/\"><appId>'+localStorage.getItem("applicationID")+'</appId><email>'+globalemail+'</email><loyaltycardUniqueId>'+sessionStorage.getItem('loyaltycardUniqueId')+'</loyaltycardUniqueId></loyalityCheckXml></soap:Body></soap:Envelope>';
        
        console.log("Soap Request "+ soapRequest);
        
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
               var GRR=$(req.responseText).find("return").text();
               console.log("GRR --->"+GRR);
               if(GRR == 'userNotRegistered')
               {
               membership_view();
               }
               else
               {
				   getLoyalityData(GRR,"false");
               }
               
               },
               error: function(response, textStatus, errorThrown)
               {
            	  
            	   
               alertPopUp(serverresponse_card,yourcardtemporarydisapproved_card);
               }
               });
        //code end from here change by amritesh on 13-01-2016
}

function processSuccessLoyalty(data, status, req) {

    var GRR=$(req.responseText).find("return").text();
    console.log("About_user>>>"+GRR);
    if(GRR == 'userNotRegistered')
    {
    	
        membership_view();
    }
	else if(GRR=='Disapproved') {
	$('.appypie-loader').hide();
		navigator.notification.alert(
				yourcardtemporarydisapproved_card,
                                 alertDismissed,
                                 serverresponse_card,
                                 ok_card
                                 );
	}
    else
    {
		getLoyalityData(GRR,"false");
    }
    
}

function processErrorLoyalty(data, status, req) {
	alertPopUp(serverresponse_card,unableloaddata_card);
   
}
function getLoyalityData(xmlPath,value) {
	
	console.log("xmlPath>>>"+xmlPath);
	$.ajax({
			url: xmlPath,
			dataType: "text",
			crossDomain: true,
			async: false,
			cache: false,
			success: function(loyalityXmlData) {
				loyaltycard_view(value,loyalityXmlData);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				console.log('failed');
				console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
			}
	});
}
function membership_view()
{
	
	
	
    var couponHtml = '<section class="home_screen">\
    <div class="page-text">\
    <div class="appypie-login">\
    <h2>'+loyaltycardMobHeaderLabel+'</h2>\
    <div class="login-feald"><label>'+loyaltycardMobNameLabel+'<span class="required">*</span></label><input data-role="none"name="Name"  type="text" /></div>\
    <div class="login-feald"><label>'+loyaltycardMobEmailLabel+'<span class="required">*</span></label><input data-role="none"name="EmailId"  type="text" /></div>\
    <div class="login-feald"><label>'+loyaltycardMobPhoneLabel+'</label><input data-role="none"name="Phone"  type="text" /></div>\
    <div class="login-feald" ><a class="login_btnforloyalty" onclick="Register();" '+primaryColor+'>'+joinloyaltyprogram_card+'</a></div>\
    </div>\
    </div>\
    </section>';
	
	
	//code change by amritesh on 13-01-16
	  if(sessionStorage.getItem('singlePage') == 'true')
    {
        appendHtml("<div class='page-text'>"+couponHtml+"</div>",8,1);
    }
    else
    {
        
        appendHtml("<div class='page-text'>"+couponHtml+"</div>",8,2);
    }
   
    if(localStorage.getItem('fooduserid'))
    {
                setTimeout(function(){
                   document.getElementsByName('Name')[0].value=localStorage.getItem("profileName");
                   document.getElementsByName('EmailId')[0].value=localStorage.getItem('fooduserid');
                   document.getElementsByName('Phone')[0].value=localStorage.getItem("profilePhoneNo");
                           var name=localStorage.getItem("profileName");
                           if(name!='' && localStorage.getItem("profileName"))
                           {
                                Register();
                           }
                   //history.go(-1);
                   },100);
    }
	
   /*  if(localStorage.getItem('loyalityCardUserEmail'))
    {
    $('.appypie-loader').show();
    appendHtml("<div class='page-text' style='display: none;'>"+couponHtml+"</div>",8,1);
       //$(".page-text").hide(100);
    }
    else
    {
     appendHtml("<div class='page-text'>"+couponHtml+"</div>",8,1);
    }
	
	if(localStorage.getItem('fooduserid'))
    {
		$('.appypie-loader').show();
                setTimeout(function(){
                   document.getElementsByName('Name')[0].value=localStorage.getItem("profileName");
                   document.getElementsByName('EmailId')[0].value=localStorage.getItem('loyalityCardUserEmail');
                   document.getElementsByName('Phone')[0].value=localStorage.getItem("profilePhoneNo");
                           var name=localStorage.getItem("profileName");
                           if(name!='' && localStorage.getItem("profileName"))
                           {
                                Register();
                           }
						   else{
                            Register();
							}
                   //history.go(-1);
                   },100);
    }*/
	
	
}	

function Register()
{
	
	//code written by amritesh
	if(checkNetworkConnection())
    {
    $('.appypie-loader').show();
    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/loyalty-soap#loyalityRegistrationXml";
	var name= document.getElementsByName('Name')[0].value;
        localStorage.setItem("profileNamemember",name);
    var email = document.getElementsByName('EmailId')[0].value;
    var phone = document.getElementsByName('Phone')[0].value;
    var regexObj = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
    
    if(email == "" || name=="")
    {
    	
         alertPopUp(alert_card,mandatoryfieldsblank_card);
    }
    else if(!checkEmail(email))
    {
        alertPopUp('',entervalid_card);
    }
    else if((regexObj.test(phone)) || (phone.length >=8 && IsNumeric(phone)) || phone==""){
        var soapRequest='';
      
            wsUrl = "http://"+localStorage.getItem("reseller")+"/services/loyalty-soap#loyalityRegistrationXml";
            soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><loyalityRegistrationXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/loyalty-soap#loyalityRegistration\"><appId>'+window.localStorage.getItem("applicationID")+'</appId><name>'+name+'</name><mobile>'+phone+'</mobile><email>'+email+'</email><device_token>'+deviceToken+'</device_token><loyaltycardUniqueId>'+sessionStorage.getItem('loyaltycardUniqueId')+'</loyaltycardUniqueId></loyalityRegistrationXml></soap:Body></soap:Envelope>';
			
			 
      //localStorage.setItem("profileEmail",email);
      localStorage.setItem("profilePhoneNo",phone);
        console.log(" amritesh loyalityRegistrationXml" + soapRequest);
			
       
        console.log(" " + soapRequest);
        
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
               var registerResponse=$(req.responseText).find("return").text();
               history.go(-1);
              
			   localStorage.setItem("profileName",name); 
			 localStorage.setItem('loyalityCardUserEmail', email);
               setTimeout(function(){
	
				    getLoyalityData(registerResponse,"true");
                         
                          },500);
               
               },
               error: function(response, textStatus, errorThrown)
               {
            	   
            	  
               	
               alertPopUp(serverresponse_card,datanotfound_card);
               }
               });
        
    }
    
    else{
    	
        alertPopUp(alert_card,pleasephonenumber_card);
    }
    }
	//end code by amritesh
     
  /*  var wsUrl = "http://"+window.localStorage.getItem("reseller")+"/services/loyalty-soap#loyalityRegistrationXml";
	var name="";
	var email="";
	var phone="";
	if(localStorage.getItem('loyalityCardUserEmail')){
		name=localStorage.getItem("profileName");
        email=localStorage.getItem('loyalityCardUserEmail');
        phone=localStorage.getItem("profilePhoneNo");

		
	}else{
		
		name= document.getElementsByName('Name')[0].value;
		email = document.getElementsByName('EmailId')[0].value;
		phone = document.getElementsByName('Phone')[0].value;
		
	}
	
    var regexObj = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
    
    if(email == "")
    {
    	alertPopUp('','Mandatory fields should not be blank.');
       
    }
	else if(!checkEmail(email))
    {
     alertPopUp('','Enter a valid Email Id');
     }
   // else if((regexObj.test(phone)) || (phone.length >=8 && IsNumeric(phone)) || phone==""){
    else{
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><loyalityRegistrationXml xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/loyalty-soap#loyalityRegistrationXml\"><appId>'+localStorage.getItem("applicationID")+'</appId><name>'+name+'</name><mobile>'+phone+'</mobile><email>'+email+'</email><device_token>'+deviceToken+'</device_token><loyaltycardUniqueId>'+sessionStorage.getItem('loyaltycardUniqueId')+'</loyaltycardUniqueId></loyalityRegistrationXml></soap:Body></soap:Envelope>';
        console.log(" amritesh loyalityRegistrationXml" + soapRequest);
       
	  localStorage.setItem("profileName",name);
      localStorage.setItem("profileEmail",email);
      localStorage.setItem("profilePhoneNo",phone);
	   
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               dataType: "text",
               data: soapRequest,
               success: processSuccessxmlLoyaltyupdateOne,
               error: processErrorxmlLoyaltyupdateOne
               });
        
    }*/
    
}

function processSuccessxmlLoyaltyupdateOne(data, status, req)
{
	//set email id
	if(localStorage.getItem('loyalityCardUserEmail')){
			toaster.setloyalityEmail(localStorage.getItem('loyalityCardUserEmail'));
	}else{
			localStorage.setItem('loyalityCardUserEmail', document.getElementsByName('EmailId')[0].value);
			toaster.setloyalityEmail(document.getElementsByName('EmailId')[0].value);
	}
	
    var GRR=$(req.responseText).find("return").text();
	
	//set XML path url
	localStorage.setItem('grr_xml',GRR);
	toaster.setloyalityXMLPath(GRR);
	
    getLoyalityData(GRR,"true");
}

function processErrorxmlLoyaltyupdateOne(data, status, req)
{
    
}

function getFormattedDate(input){
    var pattern=/(.*?)\/(.*?)\/(.*?)$/;
    var result = input.replace(pattern,function(match,p1,p2,p3){
        var months=['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
        return (p2<10?""+p2:p2)+"-"+months[(p1-1)]+"-"+p3;
    });
	return result;
   
}

function loyaltycard_view(value,loyalityXmlData)
{
	 
    localStorage.setItem("loyalityXMLData",loyalityXmlData);
    var loyalityXml = $.parseXML(loyalityXmlData);
    $xml = $(loyalityXml);
    var memberHeader=$xml.find("memberHeader").text();
	var memberName=$xml.find("memberName").text();
	
	var memberCode=$xml.find("memberCode").text();
	var memberBgImage=$xml.find("memberBgImage").text();
	console.log("memberBgImage>>>"+memberBgImage);
	var memberBarCodeImg=$xml.find("memberBarCodeImg").text();
	
	var memberBgColor=$xml.find("memberBgColor").text();
	console.log("memberBgColor>>>"+memberBgColor);
	
	var validFrom1=$xml.find("validFrom").text();
	var validTo1=$xml.find("validTo").text();
	var validFrom=getFormattedDate(validFrom1);
	var validTo=getFormattedDate(validTo1);
	console.log(" validFrom1 : "+validFrom1);
	console.log(" validTo1 : "+validTo1);
	console.log(" validFrom : "+validFrom);
	console.log(" validTo : "+validTo);
	console.log(" memberHeader : "+memberHeader);
	console.log(" memberName : "+memberName);
	console.log(" memberCode : "+memberCode);
	console.log(" memberBgImage : "+memberBgImage);
	console.log(" memberBgColor : "+memberBgColor);
	console.log(" loyalityXml : "+loyalityXml);

    sessionStorage.setItem('memberCode',memberCode);
		


     if (memberName.trim().length!=0) { 
        localStorage.setItem("profileName",memberName);
       }
	
	/*var profileNameDefault="";
	if(localStorage.getItem("profileName") === null && typeof localStorage.getItem("profileName") === "object"){
		profileNameDefault=localStorage.getItem("memberName");
	}*/
	     // if($.trim(validTo) != '')
		 // {
               
		  // var loyaltyHtml = '<section class=" membersPage"> <div class="content">  <h3 style=" background-image: url('+memberBgImage+'); " ><span class="textOverlay">'+memberHeader+'</span></h3> <p style="background-color:'+memberBgColor+'">'+localStorage.getItem("profileName")+'<span>ID: '+memberCode+'</span> </p> <p class="date"style="background-color:'+memberBgColor+'">'+validFrom+'<span>Issue Date</span></p> <p class="time"style="background-color:'+memberBgColor+'">'+validTo+'<span>Valid Till</span></p> <p id="totalpoints" style="background-color:'+memberBgColor+'">'+totalpoints+' <small>Points<small><span>Balance</span></p> <div class="image-view"> <span>Scan the QR Code</span> <img src="'+memberBarCodeImg+'" style="width:140px"> </div> <div class="member_details"><img onclick="loyalty_info();" src="images/coupon-deatils.png"></div></div><a href="#" class="point_BTN '+sessionStorage.getItem('appPageShsize')+'"  style="border-color:'+sessionStorage.getItem("primaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("primaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("primaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;" onclick="layalitycardaddpt(\'addpoint\');">Add</a> <a href="#"  class="point_BTN '+sessionStorage.getItem('appPageShsize')+'" style="border-color:'+sessionStorage.getItem("secondaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("secondaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("secondaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;" onclick="layalitycardaddpt(\'deletepoint\');">Redeem</a><section>';
		
		 // }
		// else
	   // {
						   
		//var loyaltyHtml = '<section class=" membersPage"> <div class="content">  <h3 style=" background-image: url('+memberBgImage+'); " ><span class="textOverlay">'+memberHeader+'</span></h3> <p style="background-color:'+memberBgColor+'">'+localStorage.getItem("profileName")+'<span>ID: '+memberCode+'</span> </p> <p class="date"style="background-color:'+memberBgColor+'">'+validFrom+'<span>Issue Date</span></p> <p class="time"style="background-color:'+memberBgColor+'">'+lifetime1+'<span>Valid Till</span></p> <p id="totalpoints" style="background-color:'+memberBgColor+'">'+totalpoints+' <small>Points<small><span>Balance</span></p> <div class="image-view"> <span>Scan the QR Code</span> <img src="'+memberBarCodeImg+'" style="width:140px"> </div> <div class="member_details"><img onclick="loyalty_info();" src="images/coupon-deatils.png"></div></div><a href="#" class="point_BTN '+sessionStorage.getItem('appPageShsize')+'"  style="border-color:'+sessionStorage.getItem("primaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("primaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("primaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;" onclick="layalitycardaddpt(\'addpoint\');">Add</a> <a href="#"  class="point_BTN '+sessionStorage.getItem('appPageShsize')+'" style="border-color:'+sessionStorage.getItem("secondaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("secondaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("secondaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;" onclick="layalitycardaddpt(\'deletepoint\');">Redeem</a><section>';	   
						   
	   // }
	   //change by deepak
	   if($.trim(validTo) != '')
               {
               
		
               reseller=window.localStorage.getItem("reseller");
               if(parseInt(sessionStorage.getItem("loyaltypointenabled"))==1 && parseInt(sessionStorage.getItem("qrcodeenabled"))==1)
              {
				 // alert("kkk");
              // var loyaltyHtml = '<section class=" membersPage"> <div class="content">  <h3 style=" background-image: url('+memberBgImage+'); " ><span class="textOverlay">'+memberHeader+'</span></h3> <p style="background-color:'+memberBgColor+'">'+memberName+'<span>ID: '+memberCode+'</span> </p> <p class="date"style="background-color:'+memberBgColor+'">'+validFrom+'<span>Issue Date</span></p> <p class="time"style="background-color:'+memberBgColor+'">'+validTo+'<span>Valid Till</span></p> <p id="totalpoints" style="background-color:'+memberBgColor+'">'+totalpoints+' <small>Points<small><span>Balance</span></p> <div class="image-view"> <span>Scan the QR Code</span> <img src="'+memberBarCodeImg+'" style="width:140px"> </div> <div class="member_details"><img onclick="loyalty_info();" src="images/coupon-deatils.png"></div></div><a href="#" class="point_BTN '+sessionStorage.getItem('appPageShsize')+'"  style="border-color:'+sessionStorage.getItem("primaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("primaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("primaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;" onclick="layalitycardaddpt(\'addpoint\');">Add</a> <a href="#"  class="point_BTN '+sessionStorage.getItem('appPageShsize')+'" style="border-color:'+sessionStorage.getItem("secondaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("secondaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("secondaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;" onclick="layalitycardaddptredeem(\'deletepoint\');">Redeem</a><section>';
              var loyaltyHtml = '<section class=" membersPage"><div class="content"><h3 style=" background-image: url('+memberBgImage+'); " ><span class="textOverlay">'+memberHeader+'</span></h3> <p style="background-color:'+memberBgColor+'">'+localStorage.getItem("profileName")+'<span>'+id_card+memberCode+'</span> </p> <p class="date"style="background-color:'+memberBgColor+'">'+validFrom+'<span>'+issuedate_card+'</span></p> <p class="time"style="background-color:'+memberBgColor+'">'+validTo+'<span>'+validtill_card+'</span></p> <p id="totalpoints" style="background-color:'+memberBgColor+'">'+totalpoints+' <small>'+point_card+'<small><span>'+balance_card+'</span></p><div class="image-view"> <span>'+scancode_card+'</span> <img src="'+memberBarCodeImg+'" style="width:140px"> </div> <div class="member_details"><img onclick="loyalty_info();" src="images/coupon-deatils.png"></div></div><a href="#" class="point_BTN '+sessionStorage.getItem('appPageShsize')+'"  style="border-color:'+sessionStorage.getItem("primaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("primaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("primaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;" onclick="layalitycardaddpt(\'addpoint\');">'+add_card+'</a> <a href="#"  class="point_BTN '+sessionStorage.getItem('appPageShsize')+'" style="border-color:'+sessionStorage.getItem("secondaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("secondaryButtonBgColor")+'),color-stop(100%,'+sessionStorage.getItem("secondaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;" onclick="layalitycardaddpt(\'deletepoint\');">'+redeem_card+'</a><section>';
			  }
			   else if(parseInt(sessionStorage.getItem("loyaltypointenabled"))==1 && parseInt(sessionStorage.getItem("qrcodeenabled"))==0)
               {
                var loyaltyHtml = '<section class=" membersPage"> <div class="content"><h3 style=" background-image: url('+memberBgImage+'); " ><span class="textOverlay">'+memberHeader+'</span></h3> <p style="background-color:'+memberBgColor+'">'+localStorage.getItem("profileName")+'<span>'+id_card+memberCode+'</span> </p> <p class="date"style="background-color:'+memberBgColor+'">'+validFrom+'<span>'+issuedate_card+'</span></p> <p class="time"style="background-color:'+memberBgColor+'">'+validTo+'<span>'+validtill_card+'</span></p><p id="totalpoints" style="background-color:'+memberBgColor+'">'+totalpoints+' <small>'+point_card+'<small><span>'+balance_card+'</span></p><div class="member_details" style="float: right; position: relative; right: 0; bottom: 0; margin: 10px 10px 0 0;"><img onclick="loyalty_info();" src="images/coupon-deatils.png"></div></div><a href="#" class="point_BTN '+sessionStorage.getItem('appPageShsize')+'"  style="border-color:'+sessionStorage.getItem("primaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("primaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("primaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;" onclick="layalitycardaddpt(\'addpoint\');">'+add_card+'</a> <a href="#"  class="point_BTN '+sessionStorage.getItem('appPageShsize')+'" style="border-color:'+sessionStorage.getItem("secondaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("secondaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("secondaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;" onclick="layalitycardaddpt(\'deletepoint\');">'+redeem_card+'</a><section>';
			 
			   }
			    else if(parseInt(sessionStorage.getItem("loyaltypointenabled"))==0 && parseInt(sessionStorage.getItem("qrcodeenabled"))==1)
               {
                
				    var loyaltyHtml = '<section class=" membersPage"> <div class="content">  <h3 style=" background-image: url('+memberBgImage+'); " ><span class="textOverlay">'+memberHeader+'</span></h3> <p style="background-color:'+memberBgColor+'">'+localStorage.getItem("profileName")+'<span>'+id_card+memberCode+'</span> </p> <p class="date"style="background-color:'+memberBgColor+'">'+validFrom+'<span>'+issuedate_card+'</span></p> <p class="time"style="background-color:'+memberBgColor+'">'+validTo+'<span>'+validtill_card+'</span></p> <div class="image-view"> <span>'+scancode_card+'</span> <img src="'+memberBarCodeImg+'" style="width:140px"> </div> <div class="member_details"><img onclick="loyalty_info();" src="images/coupon-deatils.png"></div></div> <section>';
			
                }
               else
               {
               
				     var loyaltyHtml = '<section class=" membersPage"> <div class="content"><h3 style=" background-image: url('+memberBgImage+'); " ><span class="textOverlay">'+memberHeader+'</span></h3> <p style="background-color:'+memberBgColor+'">'+localStorage.getItem("profileName")+'<span>'+id_card+memberCode+'</span> </p> <p class="date"style="background-color:'+memberBgColor+'">'+validFrom+'<span>'+issuedate_card+'</span></p> <p class="time"style="background-color:'+memberBgColor+'">'+validTo+'<span>'+validtill_card+'</span></p><div class="member_details" style="float: right; position: relative; right: 0; bottom: 0; margin: 10px 10px 0 0;"><img onclick="loyalty_info();" src="images/coupon-deatils.png"></div></div> <section>';
			         
                 }
               }
			    else
               {
               
               reseller=window.localStorage.getItem("reseller");
              if(parseInt(sessionStorage.getItem("loyaltypointenabled"))==1 && parseInt(sessionStorage.getItem("qrcodeenabled"))==1)
               {
              // var loyaltyHtml = '<section class=" membersPage"> <div class="content">  <h3 style=" background-image: url('+memberBgImage+'); " ><span class="textOverlay">'+memberHeader+'</span></h3> <p style="background-color:'+memberBgColor+'">'+memberName+'<span>ID: '+memberCode+'</span> </p> <p class="date" style="background-color:'+memberBgColor+'">'+validFrom+'<span>Issue Date</span></p> <p class="time" style="background-color:'+memberBgColor+'">'+lifetime1+'<span>Valid Till</span></p> <p id="totalpoints" style="background-color:'+memberBgColor+'">'+totalpoints+' <small>Points<small><span>Balance</span></p> <div class="image-view"> <span>Scan the QR Code</span> <img src="'+memberBarCodeImg+'" style="width:140px"> </div> <div class="member_details"><img onclick="loyalty_info();" src="images/coupon-deatils.png"></div></div><a href="#" class="point_BTN '+sessionStorage.getItem('appPageShsize')+'"  style="border-color:'+sessionStorage.getItem("primaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("primaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("primaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;" onclick="layalitycardaddpt(\'addpoint\');">Add</a> <a href="#"  class="point_BTN '+sessionStorage.getItem('appPageShsize')+'" style="border-color:'+sessionStorage.getItem("secondaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("secondaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("secondaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;" onclick="layalitycardaddptredeem(\'deletepoint\');">Redeem</a><section>';
               var loyaltyHtml = '<section class=" membersPage"> <div class="content">  <h3 style=" background-image: url('+memberBgImage+'); " ><span class="textOverlay">'+memberHeader+'</span></h3> <p style="background-color:'+memberBgColor+'">'+localStorage.getItem("profileName")+'<span>'+id_card+memberCode+'</span> </p> <p class="date"style="background-color:'+memberBgColor+'">'+validFrom+'<span>'+issuedate_card+'</span></p> <p class="time"style="background-color:'+memberBgColor+'">'+lifetime1+'<span>'+validtill_card+'</span></p> <p id="totalpoints" style="background-color:'+memberBgColor+'">'+totalpoints+' <small>'+point_card+'<small><span>'+balance_card+'</span></p> <div class="image-view"> <span>'+scancode_card+'</span> <img src="'+memberBarCodeImg+'" style="width:140px"> </div> <div class="member_details"><img onclick="loyalty_info();" src="images/coupon-deatils.png"></div></div><a href="#" class="point_BTN '+sessionStorage.getItem('appPageShsize')+'"  style="border-color:'+sessionStorage.getItem("primaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("primaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("primaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;" onclick="layalitycardaddpt(\'addpoint\');">'+add_card+'</a> <a href="#"  class="point_BTN '+sessionStorage.getItem('appPageShsize')+'" style="border-color:'+sessionStorage.getItem("secondaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("secondaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("secondaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;" onclick="layalitycardaddpt(\'deletepoint\');">'+redeem_card+'</a><section>';
			   }

			   else if(parseInt(sessionStorage.getItem("loyaltypointenabled"))==1 && parseInt(sessionStorage.getItem("qrcodeenabled"))==0)
               {
				var loyaltyHtml = '<section class=" membersPage"> <div class="content"><h3 style=" background-image: url('+memberBgImage+'); " ><span class="textOverlay">'+memberHeader+'</span></h3> <p style="background-color:'+memberBgColor+'">'+localStorage.getItem("profileName")+'<span>'+id_card+memberCode+'</span> </p> <p class="date"style="background-color:'+memberBgColor+'">'+validFrom+'<span>'+issuedate_card+'</span></p> <p class="time"style="background-color:'+memberBgColor+'">'+lifetime1+'<span>'+validtill_card+'</span></p> <p id="totalpoints" style="background-color:'+memberBgColor+'">'+totalpoints+' <small>'+point_card+'<small><span>'+balance_card+'</span></p>  <div class="member_details" style="float: right; position: relative; right: 0; bottom: 0; margin: 10px 10px 0 0;"><img onclick="loyalty_info();" src="images/coupon-deatils.png"></div></div><a href="#" class="point_BTN '+sessionStorage.getItem('appPageShsize')+'"  style="border-color:'+sessionStorage.getItem("primaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("primaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("primaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;" onclick="layalitycardaddpt(\'addpoint\');">'+add_card+'</a> <a href="#"  class="point_BTN '+sessionStorage.getItem('appPageShsize')+'" style="border-color:'+sessionStorage.getItem("secondaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("secondaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("secondaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;" onclick="layalitycardaddpt(\'deletepoint\');">'+redeem_card+'</a><section>';
		
			   }
			   else if(parseInt(sessionStorage.getItem("loyaltypointenabled"))==0 && parseInt(sessionStorage.getItem("qrcodeenabled"))==1)
               {
				 var loyaltyHtml = '<section class=" membersPage"> <div class="content"><h3 style=" background-image: url('+memberBgImage+'); " ><span class="textOverlay">'+memberHeader+'</span></h3> <p style="background-color:'+memberBgColor+'">'+localStorage.getItem("profileName")+'<span>'+id_card+memberCode+'</span> </p> <p class="date"style="background-color:'+memberBgColor+'">'+validFrom+'<span>'+issuedate_card+'</span></p><p class="time"style="background-color:'+memberBgColor+'">'+lifetime1+'<span>'+validtill_card+'</span></p><div class="image-view"> <span>'+scancode_card+'</span> <img src="'+memberBarCodeImg+'" style="width:140px"> </div> <div class="member_details"><img onclick="loyalty_info();" src="images/coupon-deatils.png"></div></div><section>';
			  
			   }
               else
               {
              var loyaltyHtml = '<section class=" membersPage"> <div class="content"><h3 style=" background-image: url('+memberBgImage+'); " ><span class="textOverlay">'+memberHeader+'</span></h3> <p style="background-color:'+memberBgColor+'">'+localStorage.getItem("profileName")+'<span>'+id_card+memberCode+'</span> </p> <p class="date"style="background-color:'+memberBgColor+'">'+validFrom+'<span>'+issuedate_card+'</span></p> <p class="time"style="background-color:'+memberBgColor+'">'+lifetime1+'<span>'+validtill_card+'</span></p><div class="member_details" style="float: right; position: relative; right: 0; bottom: 0; margin: 10px 10px 0 0;"><img onclick="loyalty_info();" src="images/coupon-deatils.png"></div></div> <section>';
			     }
               }
	   
	  
    if(sessionStorage.getItem('singlePage') == 'true')
    {
        appendHtml(loyaltyHtml,8,1);
    }
    else
    {
        appendHtml(loyaltyHtml,8,2);
    }
	var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/loyalty-soap#memberCardPointsDetail";
               var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><memberCardPointsDetail xmlns=\"http://'+localStorage.getItem("reseller")+'/services/loyalty-soap#memberCardPointsDetail/\"><appId>'+localStorage.getItem("applicationID")+'</appId><email>'+localStorage.getItem("loyalityCardUserEmail")+'</email><cardnumber>'+sessionStorage.getItem('memberCode')+'</cardnumber></memberCardPointsDetail></soap:Body></soap:Envelope>';
               
               console.log("Soap Request "+ soapRequest);
               
               $.ajax({
                      type: "POST",
                      url: wsUrl,
                      contentType: "text/xml",
                      dataType: "text",
                      data: soapRequest,
                      success: function(data, status, req)
                      {
                      var GRR=$(req.responseText).find("return").text();
                      totalpoints=parseInt(GRR);
                      if(document.getElementById("availablepoints"))
                      {
                    	  
                      document.getElementById("availablepoints").innerHTML=availablepoints_card+totalpoints+"";
                      }
					  if(document.getElementById("totalpoints"))
                      {
                      document.getElementById("totalpoints").innerHTML=totalpoints+" <small>"+point_card+"<small><span>"+balance_card+"</span>";
                      }
					   },
                      error: function(response, textStatus, errorThrown)
                      {
                      alertPopUp(serverresponse_card,yourcardtemporarydisapproved_card);
                      }
                      });
					  
			
	 $('#contentHolder8').addClass('page-content mediumContent bg-color');
  sessionStorage.setItem('fromLoyalty','true');
 
}
var typeofaddorredeem='';
function layalitycardaddpt(type)
{
    
    typeofaddorredeem=type;
	var loyalitycardhtml="";
	 if(typeofaddorredeem=='addpoint'){

		 
		 
    loyalitycardhtml='<div class="page-text"><div class="loyaltyCard-wrapper"><div class="loyalty-appContainer"><div  id="imgcardbackground_1_inner" class="loaylty-back"><!-- Background Image will show here  --><div  class="loyalStemp"><input data-role="none" type="number" name="codeValue" class="loyalty-code-membercard" id="addvalueinputmember" placeholder="'+enterpoints_card+'"><div class="Bdiscription" id="availablepoints">'+availablepoints_card+totalpoints+'</div><div class="Bdiscription">'+pleasehandcashieraddpoints_card+'</div><div id="validateOptionsMember"><a onclick="callQrOrValidateMember(\'code\')" class="loyalty-btn" style="border-color:'+sessionStorage.getItem("primaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("primaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("primaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+validatetypingsecuritycode_card+'</a><a onclick="callQrOrValidateMember(\'QR\')" class="loyalty-btn" style="border-color:'+sessionStorage.getItem("secondaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("secondaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("secondaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+validatescanningqrcode_card+'</a></div><div id="securityCodeMember" style="display:none;"><input data-role="none" type="text" name="codeValue" class="loyalty-code" id="StampTxtFldMember" placeholder="'+entersecuritycode_card+'"><a class="loyalty-btn" id="stampCardBtnMember" onclick="performStampCardValidationMember()" style="border-color:'+sessionStorage.getItem("primaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("primaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("primaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">Submit</a><a onclick="openSecurityMember()" id="stampCancelBtnMember" class="loyalCancel" style="border-color:'+sessionStorage.getItem("secondaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("secondaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("secondaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+cancel_card+'</a></div></div></div></div></div></div>';
	}else{
	 loyalitycardhtml='<div class="page-text"><div class="loyaltyCard-wrapper"><div class="loyalty-appContainer"><div  id="imgcardbackground_1_inner" class="loaylty-back"><!-- Background Image will show here  --><div  class="loyalStemp"><input data-role="none" type="number" name="codeValue" class="loyalty-code-membercard" id="addvalueinputmember" placeholder="'+enterpoints_card+'"><div class="Bdiscription" id="availablepoints">'+availablepoints_card+totalpoints+'</div><div class="Bdiscription">'+pleasehanddeviceredeempoints_card+'</div><div id="validateOptionsMember"><a onclick="callQrOrValidateMember(\'code\')" class="loyalty-btn" style="border-color:'+sessionStorage.getItem("primaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("primaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("primaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+validatetypingsecuritycode_card+'</a><a onclick="callQrOrValidateMember(\'QR\')" class="loyalty-btn" style="border-color:'+sessionStorage.getItem("secondaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("secondaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("secondaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+validatescanningqrcode_card+'</a></div><div id="securityCodeMember" style="display:none;"><input data-role="none" type="text" name="codeValue" class="loyalty-code" id="StampTxtFldMember" placeholder="'+entersecuritycode_card+'"><a class="loyalty-btn" id="stampCardBtnMember" onclick="performStampCardValidationMember()" style="border-color:'+sessionStorage.getItem("primaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("primaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("primaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">Submit</a><a onclick="openSecurityMember()" id="stampCancelBtnMember" class="loyalCancel" style="border-color:'+sessionStorage.getItem("secondaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("secondaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("secondaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+cancel_card+'</a></div></div></div></div></div></div>';
	}
    
    
    appendHtml(loyalitycardhtml,9,2);
    
    
    
}
function performStampCardValidationMember()
{
    var StampTxtFldMember=document.getElementById("StampTxtFldMember").value;
    var addvalueinputmember=document.getElementById("addvalueinputmember").value;
    if(StampTxtFldMember.indexOf("Code")>-1)
    {
        StampTxtFldMember=StampTxtFldMember.replace("Code", "").trim();
        StampTxtFldMember=StampTxtFldMember.replace(":", "").trim();
    }
    var addorredem='';
    if(StampTxtFldMember!=loyaltycardUnlockCode)
    {
    	

    	
        alertPopUp(warning_card,pleaseentervalidunlockcode_card);
        return;
    }
    if(typeofaddorredeem=='deletepoint')
    {
        
        
        if(parseInt(addvalueinputmember)>totalpoints)
        {
            alertPopUp(warning_card,insufficientpoints_card);
            return;
        }
        addorredem='redeem';
		 if(document.getElementById("addvalueinputmember").value==0)
        {
            alertPopUp(warning_card,pleaseenterredeemvaluegreater_card);
            return;
        }
		
      }
    else
    {
		 if(document.getElementById("addvalueinputmember").value==0)
        {
            alertPopUp(warning_card,pleaseenteraddvaluegreater_card);
            return;
        }
		
		
        addorredem='add';
    }
    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/loyalty-soap#memberCardPoints";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><memberCardPoints xmlns=\"http://'+localStorage.getItem("reseller")+'/services/loyalty-soap#memberCardPoints/\"><appId>'+localStorage.getItem("applicationID")+'</appId><email>'+localStorage.getItem("loyalityCardUserEmail")+'</email><cardnumber>'+sessionStorage.getItem('memberCode')+'</cardnumber><points>'+addvalueinputmember+'</points><type>'+addorredem+'</type></memberCardPoints></soap:Body></soap:Envelope>';
    
    console.log("Soap Request "+ soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           var GRR=$(req.responseText).find("return").text();
           console.log("GRR --->"+GRR);
           if(GRR == 'user is not attached with this card number')
           {
           alertPopUp(warning_card,pleasetryagain_card);
           getloyaltycardPageDataNew(globalindex,0);
           }
           else
           {getloyaltycardPageDataNew(globalindex,0);
           
           }
           
           },
           error: function(response, textStatus, errorThrown)
           {
           alertPopUp(serverresponse_card,yourcardtemporarydisapproved_card);
           }
           });
}
function callQrOrValidateMember(type)
{
    if(document.getElementById("addvalueinputmember").value=='')
    {
        alertPopUp(warning_card,pleaseenterpoints_card);
        return;
    }
    if(type == 'code')
    {
        $('#securityCodeMember').show();
        $('#validateOptionsMember').hide();
    }
    else
    {
        
        sessionStorage.setItem("memberfromLoyalty",'true');
        toaster.callBarCodeReader();
        $('#securityCodeMember').show();
        $('#validateOptionsMember').hide();
    }
    
}

function openSecurityMember()
{
    $('#securityCodeMember').hide();
    $('#validateOptionsMember').show();
}
function layalitycardredeempt()
{
    alert(nofunctionalityimplemented_card);
}
function loyalty_info()
{
	var loyalityXmlData = $.parseXML(localStorage.getItem("loyalityXMLData"));
    $xml = $(loyalityXmlData);
    $loyalityRegister = $xml.find("loyalityRegister");
    var memberHeader=$loyalityRegister.find("loyaltycardDescription").text();
	var memberCode=$loyalityRegister.find("memberBgColor").text();
	var term=$data.find("loyaltycardtc").text();
    var loyaltyDesHtml = '<div class="coupon-page"><style>.bg-color{background-color:'+memberCode+' !important}</style> <div class="page-text"><h2>'+term+'</h2>'+memberHeader+'</div></div>';
    if(sessionStorage.getItem('singlePage') == 'true')
    {
        appendHtml(loyaltyDesHtml,9,2);
    }
    else
    {
        appendHtml(loyaltyDesHtml,9,3);
    }
   $('#contentHolder9').addClass('page-content mediumContent bg-color');
} 