//calls request for the contact information

var defaultbillingaddress_ecomFooddir = 'Default Billing Address';
var defaultshippingaddress_ecomFooddir = 'Default Shipping Address';
var pleaseenterfirstname_ecomFooddir = 'Please enter first name';
var pleaseentertelephoneno_ecomFooddir = 'Please enter telephone no.';
var pleaseentervalidtelephoneo_ecomFooddir = 'Please enter valid telephone no.';
var pleaseenteremailaddress_ecomFooddir = 'Please enter Email Address';
var pleaseentername_ecomFooddir = 'Please Enter Name';
var error_ecomFooddir = 'Error';
var ok_ecomFooddir = 'OK';
var pleaseentervalidphonenumber_ecomFooddir = 'Please Enter Valid Phone number';
var peaseenterreetaddress_ecomFooddir = 'Please Enter Street Address';
var pleaseenteryourcity_ecomFooddir = 'Please Enter Your City';
var pleaserovince_ecomFooddir = 'Please Enter State/Province';
var pleaseostalcode_ecomFooddir = 'Please Enter Zip/Postal Code';
var pleaseentercountry_ecomFooddir = 'Please Enter Country';


function showContactInfo(pageName)
{
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    if(checkNetworkConnection())
    {
        if(pageName == 'food')
        {
            wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodContactInfoXml";
            soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodContactInfoXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodContactInfoXml\"><appId>'+appid+'</appId><email>'+localStorage.getItem("fooduserid")+'</email></foodContactInfoXml></soap:Body></soap:Envelope>';
        }
        else
        {
            wsUrl = "http://"+localStorage.getItem("reseller")+"/services/ecomm-soap#ecommContactInfoXml";
            soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommContactInfoXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/ecomm-soap#ecommContactInfoXml\"><appId>'+appid+'</appId><email>'+localStorage.getItem("fooduserid")+'</email></ecommContactInfoXml></soap:Body></soap:Envelope>';
        }
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
               showContactDetails(strJSON,pageName);
               },
               error: function(response, textStatus, errorThrown)
               {
               console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
    }
}
function showContactDetails(strJSON,pageName)
{
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    if(checkNetworkConnection())
    {
        html='';
        $.ajax({
               url: strJSON,
               dataType: "text",
               crossDomain: true,
               cache: false,
               success: function(xml) {
               
               var xmlDoc = $.parseXML(xml);
               $xml = $(xmlDoc);
               var Name= $xml.find("name").text();
               var email=$xml.find("email").text();
               var phone=$xml.find("phone").text();
               html+=Name+ "<br/>"+ email + "<br/>" +  phone;
               $("#user_tab1").append(html);
               showBillingShipping(pageName);
               },
               error: function(XMLHttpRequest, textStatus, errorThrown) {
               console.log('fail');
               console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
    }
}

function showBillingShipping(pageName)
{
	
	 
    if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    
    if(pageName == 'food')
    {
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodAddressBookXml";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodAddressBookXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodAddressBookXml\"><appId>'+appid+'</appId><userName>'+localStorage.getItem("fooduserid")+'</userName><billing>Billing</billing><shipping>Shipping</shipping></foodAddressBookXml></soap:Body></soap:Envelope>';
    }
    else
    {
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/ecomm-soap#ecommDefaultAddressBookXml";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommDefaultAddressBookXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/ecomm-soap#ecommDefaultAddressBookXml\"><appId>'+appid+'</appId><userName>'+localStorage.getItem("fooduserid")+'</userName><billing>Billing</billing><shipping>Shipping</shipping></ecommDefaultAddressBookXml></soap:Body></soap:Envelope>';
    }
    console.log(soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           console.log('yesysysy==>'+data);
           var xmlret=req.responseText;
           console.log("the value of xmlret"+xmlret);
           var strJSON = $(xmlret).find("return").text();
           console.log(strJSON);
           showBillingShippinginfo(strJSON);
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
		  
           }
           });
}

function showBillingShippinginfo(strJSON)
{
	if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
	
	
    console.log(strJSON);
    $.ajax({
           url: strJSON,
           dataType: "text",
           crossDomain: true,
           cache: false,
           success: function(xml) {
           var xml =jQuery.parseXML(xml);
           var billing='';
           var shipping='';
           
           $(xml).find("customerAddress").each(function () {
                                               
                                               if($(this).find("BillingShipping").text()=="Billing")
                                               {
                                               billing="<strong>"+defaultbillingaddress_ecomFooddir+"</strong><br/>";
                                               billing+=$(this).find("name").text()+'<br/>';
                                               billing+=$(this).find("address").text()+'<br/>';
                                               billing+=$(this).find("phone").text()+'<br/>';
                                               billing+=$(this).find("city").text()+', ';
                                               billing+=$(this).find("state").text()+', ';
                                               billing+=$(this).find("zip").text()+'<br/>';
                                               billing+=$(this).find("country").text()+'<br/>';
                                               
                                               }
                                               else
                                               {
                                               shipping="<strong>"+defaultshippingaddress_ecomFooddir+"</strong><br/>";
                                               shipping+=$(this).find("name").text()+'<br/>';
                                               shipping+=$(this).find("address").text()+'<br/>';
                                               shipping+=$(this).find("phone").text()+'<br/>';
                                               shipping+=$(this).find("city").text()+', ';
                                               shipping+=$(this).find("state").text()+', ';
                                               shipping+=$(this).find("zip").text()+'<br/>';
                                               shipping+=$(this).find("country").text()+'<br/>';
                                               }
                                               });
           
           
           
           html=billing + "<br/>" + shipping ;
           $("#user_tab2").append(html);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           },
           error: function(XMLHttpRequest, textStatus, errorThrown) {
           console.log('fail');
           console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
		    
           }
           });
}


// works in case of edit button

function updatePesonalInformation()
{
	if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }

    $('.appypie-loader').show();
    var sfname=document.getElementById("fname").value;
    var spNo=document.getElementById("telno").value;
    var semailId=localStorage.getItem("fooduserid");
    if(sfname=='' || sfname=='First Name')
    {
        $(".cart_error").empty();
        $(".cart_error").append(pleaseenterfirstname_ecomFooddir);
        
        $("#cart-MSG2").slideToggle();
        
        setTimeout( function(){
                   $("#cart-MSG2").slideToggle();
                   }
                   , 3000 );
        setTimeout( function(){
                   $("#fname").focus();
                   }
                   , 3000 );
        $('.appypie-loader').hide();
        return;
    }
    else if(spNo=='' || spNo=='Telephone No')
    {
        $(".cart_error").empty().append(pleaseentertelephoneno_ecomFooddir);
        
        $("#cart-MSG2").slideToggle();
        
        setTimeout( function(){
                   $("#cart-MSG2").slideToggle();
                   }
                   , 3000 );
        setTimeout( function(){
                   $("#spNo").focus();
                   }
                   , 3000 );
        $('.appypie-loader').hide();
        return;
    }
    else if(isNaN(spNo))
    {
        $(".cart_error").empty();
        $(".cart_error").append(pleaseentervalidtelephoneo_ecomFooddir);
        
        $("#cart-MSG2").slideToggle();
        
        setTimeout( function(){
                   $("#cart-MSG2").slideToggle();
                   }
                   , 3000 );
        setTimeout( function(){
                   $("#spNo").focus();
                   }
                   , 3000 );
        $('.appypie-loader').hide();
        return;
    }
    else if(semailId=='')
    {
        $(".cart_error").empty();
        $(".cart_error").append(pleaseenteremailaddress_ecomFooddir);
        
        $("#cart-MSG2").slideToggle();
        
        setTimeout( function(){
                   $("#cart-MSG2").slideToggle();
                   }
                   , 3000 );
        $('.appypie-loader').hide();
        return;
    }
    if(checkNetworkConnection())
    {
        if(localStorage.getItem("payTypeFoodOrEcom")=="food")
        {
            wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodCustomerProfile";
            
            soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodCustomerProfile xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodCustomerProfile\"><appId>'+appid+'</appId><email>'+semailId+'</email><fname>'+sfname+'</fname><lname></lname><phone>'+spNo+'</phone></foodCustomerProfile></soap:Body></soap:Envelope>';
            
        }
        else
        {
            // wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodCustomerProfile";
            
            // soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodCustomerProfile xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodCustomerProfile\"><appId>'+appid+'</appId><email>'+semailId+'</email><fname>'+sfname+'</fname><lname></lname><phone>'+spNo+'</phone></foodCustomerProfile></soap:Body></soap:Envelope>';
            wsUrl = "http://"+localStorage.getItem("reseller")+"/services/ecomm-soap#ecommCustomerProfile";
            soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommCustomerProfile xmlns=\"http://'+localStorage.getItem("reseller")+'/services/ecomm-soap#ecommCustomerProfile\"><appId>'+appid+'</appId><email>'+semailId+'</email><fname>'+sfname+'</fname><lname></lname><phone>'+spNo+'</phone></ecommCustomerProfile></soap:Body></soap:Envelope>';
            
        }
        
        console.log("updatePesonalInformation soap request-->>>"+soapRequest);
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
               
               $("#cart-MSG1").slideToggle();
               
               setTimeout( function(){
                          $("#cart-MSG1").slideToggle();
                          setTimeout( function(){
                                     if(localStorage.getItem("payTypeFoodOrEcom")=="food")
                                     {
                                     myaccount();
                                     }
                                     else
                                     {
                                     //myaccountEcommerce();
									 mcomSetting();
                                     }
                                     
                                     }
                                     , 1000 );
									alert("Successfully Updated");
                          , 3000 );
						  
               console.log("success");
               },
               error: function(response, textStatus, errorThrown)
               {
               console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
    }
}

function updateDefaultBilling()
{
	if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }

    $('.appypie-loader').show();
    var bfname=document.getElementById("bfname").value;
   
    var bpNo=document.getElementById("bpNo").value;
    var bsAddress=document.getElementById("bsAddress").value;
    var bCity=document.getElementById("bCity").value;
    var bState=document.getElementById("bState").value;
    var bZip=document.getElementById("bZip").value;
    var bCountry=document.getElementById("bCountry").value;
    
    
    if(bfname=='' || bfname=='Name')
    {
        navigator.notification.alert(pleaseentername_ecomFooddir, setTimeout, error_ecomFooddir, ok_ecomFooddir);
        $("#sCountry").focus();
        $('.appypie-loader').hide();
        return;
    }
    
    else if(bpNo=='' || bpNo=='Phone Number' )
    {
        navigator.notification.alert(pleaseentervalidphonenumber_ecomFooddir, setTimeout, error_ecomFooddir, ok_ecomFooddir);
        $("#bpNo").focus();
        $('.appypie-loader').hide();
        return;
    }
    else if(bsAddress=='' || bsAddress=='Street Address' )
    {
        navigator.notification.alert(peaseenterreetaddress_ecomFooddir, setTimeout, error_ecomFooddir, ok_ecomFooddir);
        $("#bsAddress").focus();
        $('.appypie-loader').hide();
        return;
    }
    else if(bCity=='' || bCity=='Enter Your City' )
    {
        navigator.notification.alert(pleaseenteryourcity_ecomFooddir, setTimeout, error_ecomFooddir, ok_ecomFooddir);
        $("#bCity").focus();
        $('.appypie-loader').hide();
        return;
    }
    else if(bState=='' || bState=='Enter State/Province' )
    {
        navigator.notification.alert(pleaserovince_ecomFooddir, setTimeout, error_ecomFooddir, ok_ecomFooddir);
        $("#bState").focus();
        $('.appypie-loader').hide();
        return;
    }
    else if(bZip=='' || bZip=='Enter Zip/Postal Code' )
    {
        navigator.notification.alert(pleaseostalcode_ecomFooddir, setTimeout, error_ecomFooddir, ok_ecomFooddir);
        $("#bZip").focus();
        $('.appypie-loader').hide();
        return;
    }
    else if(bCountry=='' || bCountry=='Enter Country' || bCountry=='Select')
    {
        navigator.notification.alert(pleaseentercountry_ecomFooddir, setTimeout, error_ecomFooddir, ok_ecomFooddir);
        $("#bCountry").focus();
        $('.appypie-loader').hide();
        return;
    }
    
    if(checkNetworkConnection())
    {
        var billing='{"billShip":"Billing","name":"'+bfname + '","address":"'+bsAddress+'","city":"'+bCity+'","state":"'+bState+'","country":"'+bCountry+'","zip":"'+bZip+'","phone":"'+bpNo+'"}';
        
        
       if(localStorage.getItem("payTypeFoodOrEcom")=="food")
        {
            wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#ecommMyAccount";
            soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodMyAccount xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodMyAccount\"><appId>'+appid+'</appId><userName>'+localStorage.getItem("fooduserid")+'</userName><billing>'+billing+'</billing><shipping></shipping></foodMyAccount></soap:Body></soap:Envelope>';
        }
        else
        {
            wsUrl = "http://"+localStorage.getItem("reseller")+"/services/ecomm-soap#ecommMyAccount";
            soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommMyAccount xmlns=\"http://'+localStorage.getItem("reseller")+'/services/ecomm-soap#ecommMyAccount\"><appId>'+appid+'</appId><userName>'+localStorage.getItem("fooduserid")+'</userName><billing>'+billing+'</billing><shipping></shipping></ecommMyAccount></soap:Body></soap:Envelope>';
            //            wsUrl = "http://"+localStorage.getItem("reseller")+"/services/ecomm-soap#ecommMyAccount";
            //            soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommMyAccount xmlns=\"http://'+localStorage.getItem("reseller")+'/services/ecomm-soap#ecommMyAccount\"><appId>'+appid+'</appId><userName>'+localStorage.getItem("fooduserid")+'</userName><billing>'+billing+'</billing><shipping></shipping></ecommMyAccount></soap:Body></soap:Envelope>';
        }
        console.log(soapRequest);
        jQuery.support.cors = true;
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               cache: false,
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
               var xmlret=req.responseText;
               console.log('req.responseText==>'+req.responseText);
               var strJSON = $(xmlret).find("return").text();
               console.log('strJSON==>'+strJSON);
               $("#cart-MSG2").slideToggle();
               
               setTimeout( function(){
                          $("#cart-MSG2").slideToggle();
                          }
                          , 3000 );
               setTimeout( function(){
                          if(localStorage.getItem("payTypeFoodOrEcom")=="food")
                          {
                          myaccount();
                          }
                          else
                          {
                          //myaccountEcommerce();
						  mcomSetting();
                          }
                          $("#contentHolder3").empty();
                          }
                          , 1000 );
						  
						   alert("successfully Updated");
               
               },
               error: function(response, textStatus, errorThrown)
               {
               console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
    }
}
function updateDefaultShipping()
{
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }

    $('.appypie-loader').show();
    var sfname=document.getElementById("sfname").value;
    var spNo=document.getElementById("spNo").value;
    var ssAddress=document.getElementById("ssAddress").value;
    var sCity=document.getElementById("sCity").value;
    var sState=document.getElementById("sState").value;
    var sZip=document.getElementById("sZip").value;
    var sCountry=document.getElementById("sCountry").value;
    
    if(sfname=='' || sfname=='Name')
    {
        navigator.notification.alert(pleaseentername_ecomFooddir, setTimeout, error_ecomFooddir, ok_ecomFooddir);
        $("#sfname").focus();
        setTimeout(function(){$('.appypie-loader').hide();},1000);
        return;
    }
    
    else if(spNo=='' || spNo=='Phone Number' )
    {
        navigator.notification.alert(pleaseentervalidphonenumber_ecomFooddir, setTimeout, error_ecomFooddir, ok_ecomFooddir);
        setTimeout(function(){$('.appypie-loader').hide();},1000);
        $("#spNo").focus();
        return;
    }
    else if(ssAddress=='' || ssAddress=='Street Address' )
    {
        navigator.notification.alert(peaseenterreetaddress_ecomFooddir, setTimeout, error_ecomFooddir, ok_ecomFooddir);
        $("#ssAddress").focus();
        setTimeout(function(){$('.appypie-loader').hide();},1000);
        return;
    }
    else if(sCity=='' || sCity=='Enter Your City' )
    {
        navigator.notification.alert(pleaseenteryourcity_ecomFooddir, setTimeout, error_ecomFooddir, ok_ecomFooddir);
        setTimeout(function(){$('.appypie-loader').hide();},1000);
        $("#sCity").focus();
        return;
    }
    else if(sState=='' || sState=='Enter State/Province' )
    {
        navigator.notification.alert(pleaserovince_ecomFooddir, setTimeout, error_ecomFooddir, ok_ecomFooddir);
        setTimeout(function(){$('.appypie-loader').hide();},1000);
        $("#sState").focus();
        return;
    }
    else if(sZip=='' || sZip=='Enter Zip/Postal Code' )
    {
        navigator.notification.alert(pleaseostalcode_ecomFooddir, setTimeout, error_ecomFooddir, ok_ecomFooddir);
        setTimeout(function(){$('.appypie-loader').hide();},1000);
        $("#sZip").focus();
        return;
    }
    else if(sCountry=='' || sCountry=='Enter Country' || sCountry=='Select')
    {
        navigator.notification.alert(pleaseentercountry_ecomFooddir, setTimeout, error_ecomFooddir, ok_ecomFooddir);
        setTimeout(function(){$('.appypie-loader').hide();},1000);
        $("#sCountry").focus();
        return;
    }
    
    if(checkNetworkConnection())
    {
        var shipping='{"billShip":"Shipping","name":"'+sfname + '","address":"'+ssAddress+'","city":"'+sCity+'","state":"'+sState+'","country":"'+sCountry+'","zip":"'+sZip+'","phone":"'+spNo+'"}';
        
        
        
        if(localStorage.getItem("payTypeFoodOrEcom")=="food")
        {
            wsUrl = "http://"+resellerInitial+reseller+"/services/food-soap#foodMyAccount";
            soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodMyAccount xmlns=\"http://'+resellerInitial+reseller+'/services/food-soap#foodMyAccount\"><appId>'+appid+'</appId><userName>'+localStorage.getItem("fooduserid")+'</userName><billing></billing><shipping>'+shipping+'</shipping></foodMyAccount></soap:Body></soap:Envelope>';
        }
        else
        {
            wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#ecommMyAccount";
            soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommMyAccount xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#ecommMyAccount\"><appId>'+appid+'</appId><userName>'+localStorage.getItem("fooduserid")+'</userName><billing></billing><shipping>'+shipping+'</shipping></ecommMyAccount></soap:Body></soap:Envelope>';
        }
        
        console.log(soapRequest);
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               cache: false,
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
               var xmlret=req.responseText;
               console.log('req.responseText==>'+req.responseText);
               var strJSON = $(xmlret).find("return").text();
               console.log('strJSON==>'+strJSON);
               $("#cart-MSG1").slideToggle();
               
               setTimeout( function(){
                          $("#cart-MSG1").slideToggle();
                          }
                          , 3000 );
               setTimeout( function(){
                          if(localStorage.getItem("payTypeFoodOrEcom")=="food")
                          {
                          myaccount();
                          }
                          else
                          {
                          //myaccountEcommerce();
						  mcomSetting();
                          }
                          
                          }
                          , 1000 );
						    alert("successfully Updated");
               },
               error: function(response, textStatus, errorThrown)
               {
               console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
    }
}

//

function showBillingShippingedit(value)
{
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    if(checkNetworkConnection())
    {
        $('.appypie-loader').show();
        if(localStorage.getItem("payTypeFoodOrEcom")=="food")
        {
            var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodAddressBookXml";
            var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodAddressBookXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodAddressBookXml\"><appId>'+appid+'</appId><userName>'+localStorage.getItem("fooduserid")+'</userName><billing>Billing</billing><shipping>Shipping</shipping></foodAddressBookXml></soap:Body></soap:Envelope>';
        }
        else
        {
            var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/ecomm-soap#ecommDefaultAddressBookXml";
            var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommDefaultAddressBookXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/ecomm-soap#ecommDefaultAddressBookXml\"><appId>'+appid+'</appId><userName>'+localStorage.getItem("fooduserid")+'</userName><billing>Billing</billing><shipping>Shipping</shipping></ecommDefaultAddressBookXml></soap:Body></soap:Envelope>';
        }
        console.log("showBillingShippingedit()---> soapRequest--->"+soapRequest);
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               cache: false,
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
               console.log('yesysysy==>'+data);
               var xmlret=req.responseText;
               var strJSON = $(xmlret).find("return").text();
               showBillingShippingedit2(strJSON,value);
               },
               error: function(response, textStatus, errorThrown)
               {
               console.log("showBillingShippingedit() error---> soapRequest--->"+response.responseText);
               console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
    }
}
function showBillingShippingedit2(strJSON,value)
{
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    if(checkNetworkConnection())
    {
        $.ajax({
               url: strJSON,
               dataType: "text",
               crossDomain: true,
               cache: false,
               success: function(xml) {
               var xml =jQuery.parseXML(xml);
               $(xml).find("customerAddress").each(function () {
                                                   
                                                   if($(this).find("BillingShipping").text()=="Billing")
                                                   {
                                                   var bfname=document.getElementById("bfname");
                                                   bfname.value= $(this).find("name").text();
                                                   var bpNo=document.getElementById("bpNo");
                                                   bpNo.value= $(this).find("phone").text();
                                                   if(value=="checkout") {
                                                   var bemail=document.getElementById("bemail");
                                                   bemail.value=localStorage.getItem("fooduserid");
                                                   }
                                                   var bsAddress=document.getElementById("bsAddress");
                                                   bsAddress.value= $(this).find("address").text();
                                                   var bCity=document.getElementById("bCity");
                                                   bCity.value= $(this).find("city").text();
                                                   var bState=document.getElementById("bState");
                                                   bState.value= $(this).find("state").text();
                                                   var bZip=document.getElementById("bZip");
                                                   bZip.value= $(this).find("zip").text();
                                                   var bCountry=document.getElementById("bCountry");
                                                   bCountry.value= $(this).find("country").text();
                                                   
                                                   console.log('country---->'+$(this).find("country").text());
                                                   
                                                   }
                                                   else
                                                   {
                                                   var sfname=document.getElementById("sfname");
                                                   sfname.value= $(this).find("name").text();
                                                   var spNo=document.getElementById("spNo");
                                                   spNo.value= $(this).find("phone").text();
                                                   var ssAddress=document.getElementById("ssAddress");
                                                   ssAddress.value= $(this).find("address").text();
                                                   var sCity=document.getElementById("sCity");
                                                   sCity.value= $(this).find("city").text();
                                                   var sState=document.getElementById("sState");
                                                   sState.value= $(this).find("state").text();
                                                   var sZip=document.getElementById("sZip");
                                                   sZip.value= $(this).find("zip").text();
                                                   var sCountry=document.getElementById("sCountry");
                                                   sCountry.value=$(this).find("country").text();
                                                   console.log('country 2---->'+$(this).find("country").text());
                                                   }
                                                   });
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               },
               error: function(XMLHttpRequest, textStatus, errorThrown) {
               console.log('fail');
               console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
    }
}