var masterData=$.parseXML(window.sessionStorage.getItem("xml"));
var formBuilderIndex='';
var customElementsType=[];
var customElementsLabel=[];
var customElementsMandatory=[];
var customElementsValue=[];

/*custom appointment changes */
var AppointCustomElementsType=[];
var AppointCustomElementsLabel=[];
var AppointCustomElementsMandatory=[];
var AppointCustomElementsValue=[];
/***************END ********************/


var formbuilderPageId;
var requestQuoteJson='';
var totalElements;
var submissionErrorMsg='';
var submissionSuccessMsg='';	
localStorage.setItem('dateID',"");
var globalimageurl="";
var fileName='';
var globalIndex="";
var flagglobalindex=0;
var currentSelectedId="";
var uploadFileCount=0;
var globalExactPath="";
var mailHeadingText='';

var ErrorEmailField="Please enter valid email.";
var ErrorPhoneField="Please enter valid phone number.";

var pleaseentervalidemail_builder = "Please enter valid email.";
var pleaseentervalidphonenumber_builder = "Please enter valid phone number.";
var checkschedule_builder = "check schedule";
var test_builder = "Test"; 
var schedule_builder = 'schedule';
var sun_builder = 'Sun';
var mon_builderr = 'Mon';
var tue_builder = 'Tue';
var wed_builder = 'Wed';
var thu_builder = 'Thu';
var fri_builder = 'Fri';
var sat_builder = 'Sat';
var closed_builder = ' Closed ';
var selectgender_builder = 'Select Gender';
var male_builder = 'Male';
var female_buildrer = 'Female';
var attachfile_builder = 'Attach File';
var usd_builder = 'USD';
var gbp_builder = 'GBP';
var eur_builder = 'EUR';
var aud_builder = 'AUD';
var cad_builder = 'CAD';
var chf_builder = 'CHF';
var czk_builder = 'CZK';
var dkk_builder = 'DKK';
var hkd_builder = 'HKD';
var huf_builder = 'HUF';
var jpy_builder = 'JPY';
var nok_builder = 'NOK';
var nzd_builder = 'NZD';
var pln_builder = 'PLN';
var sek_builder = 'SEK';
var sgd_builder = 'SGD';
var zar_builder = 'ZAR';
var country_builder ='Country ';
var selectcountry_builder ='Select Country';
var paymenor_builder = "Payment for ";
var notvalid_builder = 'not valid !';
var notvalidemail_builder = 'not valid email !';
var alert_builder = 'Alert';
var ok_builder = 'OK';
var attachmensizeexceed_builder = 'The attachment size exceeds from 8MB';



/*new Appointment shedule */
var appointmentShedule=0;
var appointmentStartTimeArray=[];
var appointmentEndTimeArray=[];

var appointmentStartTimeArray2=[];
var appointmentEndTimeArray2=[];
var appointOnOff=[];
var sheduleDispl='none';
var listIdForService="";
function closePopupAppoint()
{
     $('#popup-outside-page2').popup( 'close' );
  
}
/*new Appointment shedule */
function openPopupShedule()
{
    
               $('#popup-outside-page2').popup('open');
              
}
function closePopup2()
{
    setTimeout(function() {
               $('#popup-outside-page').popup( 'close' );
               },3000);
}
function openPopup()
{
    setTimeout(function() {
               $('#popup-outside-page').popup('open');
               closePopup2();
               },5000);
}

function getFormIndexForService(formType,formBuilderForm,formBuilderPageId,listId)
{
	console.log("Abhishek Rai formbuilder getFormIndexForService : formType "+formType +" , formBuilderForm  "+formBuilderForm+" , formBuilderPageId  "+formBuilderPageId +"  , listId  "+listId);
    listIdForService=listId;
    var index=0;
    $(masterData).find("Formbuilder").each(function()
                                           {
                                           var formbuilderPageIdXmlData = $(this).find("formbuilderPageId").text();
                                           if(formbuilderPageIdXmlData==formBuilderPageId)
                                           {
                                           getOnlyCustomForm(index);
                                           }
                                           index++;
                                           });
    
    
    
}


function getOnlyCustomForm(index)
{
	console.log("Abhishek Rai formbuilder getFormIndexForService : index "+index);
    console.log("getForm() index--->"+index);
    globalIndex=index;
    var html2='';
    var funname="";
    
    if(masterData.getElementsByTagName('LanguageSettings')[0])
    {
        
        ErrorEmailField=$(masterData).find("LanguageSettings").find("email").text();
        ErrorPhoneField=$(masterData).find("LanguageSettings").find("phone").text();
		
		if(ErrorEmailField=="")
        {
			
			
            ErrorEmailField=pleaseentervalidemail_builder;
        }
        if(ErrorPhoneField=="")
        {
            ErrorPhoneField=pleaseentervalidphonenumber_builder;
        }
		
        
        console.log("ErrorEmailField>>"+ErrorEmailField);
        console.log("ErrorPhoneField>>"+ErrorPhoneField);
    }
    
    $formBuilderIndex = $(masterData).find("Formbuilder[indexval="+index+"]" );
    var formBuilderId=$formBuilderIndex.find("formbuilderId").text().split(',');
    formbuilderPageId=$formBuilderIndex.find("formbuilderPageId").text();
    
    if(formBuilderId.length == 1)
    {
        if( formBuilderId[0] == 'app'){
            console.log("Abhishek Rai formbuilder showAppointmentPage : app");
            showAppointmentPage(0);
        }
        else if(formBuilderId[0] == 'quote'){
            console.log("Abhishek Rai formbuilder showQuotePage : quote");
            showQuotePage(0);
            
        }else if(formBuilderId[0] == 'custom'){
            console.log("Abhishek Rai formbuilder showCustomPage : custom");
            showCustomPage(index,0);
        }
        sessionStorage.setItem('singlePage','true');
        return;
    }else
    {
        
        html2+=" <div class='page-text'>";
        for(var i=0;i<=formBuilderId.length-1;i++)
        {
            
            if( formBuilderId[i] == 'custom')
            {
                
                 console.log("Abhishek Rai formbuilder custom : custom");
                $formBuilderIndex.find("custom").each(function()
                                                      {
                                                      var customFormName = $(this).find("customFormName").text();
                                                      var formDescription = $(this).find("formDescription").text();
                                                      var headerImage = $(this).find("headerImage").text();
                                                      var requestEmail = $(this).find("requestEmail").text();
                                                      var submissionButton = $(this).find("submissionButton").text();
                                                      var submissionEmailSub = $(this).find("submissionEmailSub").text();
                                                      var alertMsg = $(this).find("alertMsg").text();
                                                      var imageUrl = $(this).find("customIcon").text();
                                                      mailHeadingText=$(this).find("mailHeadingText").text().trim();
                                                      html2+="<a href='javascript:;' class='appypie-list' onclick='showCustomPage("+index+",1);'>"+returnImageHtml(imageUrl)+"<span>"+customFormName+"</span></a>";
                                                      
                                                      }
                                                      );
                
            }
            
        }
        
        html2+="</div>";
    }
    //    if(flagglobalindex==1)
    //    {
    //        flagglobalindex=0;
    //        $("#contentHolder").empty();
    //        $("#contentHolder").append(html2);
    //    }
    //    else
    //    {
    //
    //        appendHtml(html2,'',1);
    //    }
    //
    funcName='showCustomPage('+index+',1)';
    window.setTimeout(funcName,10);
    
}

function getForm(index){

if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }

 var html2='';
 window.toaster.startGCM();
 	 console.log("Abhishek Rai formbuilder getForm : index"+index);
 
 if(masterData.getElementsByTagName('LanguageSettings')[0])
 {
	 
	 ErrorEmailField=$(masterData).find("LanguageSettings").find("email").text();
	 ErrorPhoneField=$(masterData).find("LanguageSettings").find("phone").text();
	 if(ErrorEmailField=="")
        {
            ErrorEmailField=pleaseentervalidemail_builder;
        }
        if(ErrorPhoneField=="")
        {
            ErrorPhoneField=pleaseentervalidphonenumber_builder;
        }
	 
	 console.log("ErrorEmailField>>"+ErrorEmailField);
	 console.log("ErrorPhoneField>>"+ErrorPhoneField);
 }
 
  globalIndex=index;
    xmlFileData= masterData;
    $xml = $(xmlFileData),
    $formBuilderIndex = $xml.find( "Formbuilder[indexval="+index+"]" );
    var formBuilderId=$formBuilderIndex.find("formbuilderId").text().split(','); 
	 formbuilderPageId=$formBuilderIndex.find("formbuilderPageId").text(); 
	
	 if(formBuilderId.length == 1)
    {
	   if( formBuilderId[0] == 'app'){
		   sessionStorage.setItem("singlePage","true");
	   showAppointmentPage(0);
	   }
	   else if(formBuilderId[0] == 'quote'){
	   
	   showQuotePage(0);
	   
	   }else if(formBuilderId[0] == 'custom'){
		   sessionStorage.setItem("singlePage","true");
	   showCustomPage(index,0);
	   }
	   return false;
    }else
	{
	
			 html2+=" <div class='page-text'>";
			  for(var i=0;i<=formBuilderId.length-1;i++)
			{
				if( formBuilderId[i] == 'app')
				{
//alert($formBuilderIndex.find("appointment").text());
				$formBuilderIndex.find("appointment").each(function()
					  {
					       var appointmentFormName = $(this).find("appointmentFormName").text().trim();
						   var appointmentEmail = $(this).find("appointmentEmail").text().trim();
						   var appointmentheader = $(this).find("appointmentheader").text().trim();
						   var fieldreqtext = $(this).find("fieldreqtext").text().trim();
						   var appdate = $(this).find("appdate").text().trim();
						   var apptime = $(this).find("apptime").text().trim();
						   var appname = $(this).find("appname").text().trim();
						   var appemail = $(this).find("appemail").text().trim();
						   var appphone = $(this).find("appphone").text().trim();
						   var appremark = $(this).find("appremark").text().trim();
						   var appconfirm = $(this).find("appconfirm").text().trim();
					       var appointmentIcon = $(this).find("appointmentIcon").text().trim();
                    submissionSuccessMsg=$(this).find("submissionSuccessMsg").text();
                    submissionErrorMsg=$(this).find("submissionErrorMsg").text();
					   html2+="<a href='javascript:;' onclick='showAppointmentPage(1);' class='appypie-list'>"+returnImageHtml(appointmentIcon)+" <span>"+appointmentFormName+"</span></a>";
					  }
					  );
				
				}
				else if( formBuilderId[i] == 'quote')
				{
					  $formBuilderIndex.find("quote").each(function()
					  {
					         var quoteFormName = $(this).find("quoteFormName").text().trim();
						   var email = $(this).find("email").text().trim();
						   var subject = $(this).find("subject").text().trim();
						   var quotename = $(this).find("quotename").text().trim();
						   var quoteemail = $(this).find("quoteemail").text().trim();
						   var quotephone = $(this).find("quotephone").text().trim();
						   var quotecomment = $(this).find("quotecomment").text().trim();
						   var quotesubmit = $(this).find("quotesubmit").text().trim();
						     var quoteIcon = $(this).find("quoteIcon").text().trim();
                                                           submissionSuccessMsg=$(this).find("submissionSuccessMsg").text();
                                                           submissionErrorMsg=$(this).find("submissionErrorMsg").text();
							  //alert(quoteFormName);
				 //alert(quoteIcon);
							html2+="<a href='javascript:;' class='appypie-list' onclick='showQuotePage(1);'>"+returnImageHtml(quoteIcon)+" <span>"+quoteFormName+"</span></a>";
						 
					  }
					  );
				
				}
				else if( formBuilderId[i] == 'custom')
				{
				
				
					  $formBuilderIndex.find("custom").each(function()
					  {
					         var customFormName = $(this).find("customFormName").text().trim();
						   var formDescription = $(this).find("formDescription").text().trim();
						   var headerImage = $(this).find("headerImage").text().trim();
						   var requestEmail = $(this).find("requestEmail").text().trim();
						   var submissionButton = $(this).find("submissionButton").text().trim();
						   var submissionEmailSub = $(this).find("submissionEmailSub").text().trim();
						   var alertMsg = $(this).find("alertMsg").text().trim();
						   var customIcon = $(this).find("customIcon").text().trim();
						    mailHeadingText=$(this).find("mailHeadingText").text().trim();
							  //alert(quoteFormName);
				 //alert(quoteIcon);
							html2+="<a href='javascript:;' class='appypie-list' onclick='showCustomPage("+index+",1);'>"+returnImageHtml(customIcon)+" <span>"+customFormName+"</span></a>";
						 
					  }
					  );
				
				}
			
	}
	
	 html2+="</div>";
	
	}
   
    if(flagglobalindex==1)
    {
        flagglobalindex=0;
        appendHtml(html2,'',1);
    }
    else
    {
    	appendHtml(html2,'',1);
    	
        
    }
	
	
}

function showAppointmentPage(multiPage){
	
	
sessionStorage.setItem("ActualSelectorFor", "");

$('.appypie-loader').show();
	
	if(multiPage==1){
		
		
		
		
	}
	uploadFileCount=0;
	 globalExactPath="";
	
	$formBuilderIndex.find("appointment").each(function()
					  {
					        var appointmentFormName = $(this).find("appointmentFormName").text().trim();
						   var appointmentEmail = $(this).find("appointmentEmail").text().trim();
						   var appointmentheader = $(this).find("appointmentheader").text().trim();
						   var fieldreqtext = $(this).find("fieldreqtext").text().trim();
						   var appdate = $(this).find("appdate").text().trim();
						   var apptime = $(this).find("apptime").text().trim();
						   var appname = $(this).find("appname").text().trim();
						   var appemail = $(this).find("appemail").text().trim();
						   var appphone = $(this).find("appphone").text().trim();
						   var appremark = $(this).find("appremark").text().trim();
						   var appconfirm = $(this).find("appconfirm").text().trim();
					        var appointmentIcon = $(this).find("appointmentIcon").text().trim();
                                               submissionSuccessMsg=$(this).find("submissionSuccessMsg").text();
                                               submissionErrorMsg=$(this).find("submissionErrorMsg").text();
											   
											     /*new Appointment shedule */
                                               appointmentShedule= $(this).find("scheduleStatus").text().trim();
                                               
                                               if(appointmentShedule==1)
                                               {
                                            	   sheduleDispl='block';
												    appointOnOff=$(this).find("appSched").text().trim().split(",");
                                            	   appointmentStartTimeArray=$(this).find("startAppoint").text().trim().split(",");
                        					       appointmentEndTimeArray=$(this).find("endAppoint").text().trim().split(",");
                        					       appointmentStartTimeArray2=$(this).find("startAppoint").text().trim().split(",");
                        					       appointmentEndTimeArray2=$(this).find("endAppoint").text().trim().split(",");;
                                               }
                                               else
                                            	{
                                            	   
                                            	   sheduleDispl='none';
                                            	}
							//Modified by mohsin				   
							var hellocalc = '<div class="appointment"> <h2 style="text-align:'+sessionStorage.getItem('pageIndentation')+' !important">'+appointmentheader+'</h2> \
    <p>'+fieldreqtext+'</p> <div class="field"><label>'+appdate+'<sup class="red-astrick">*</sup></label><input data-role="none" type="text" id="txtDate" class="clockpicker" onclick="show1();" readonly /><a href="#" onclick="show1();"><img src="images/calendar.png"  border="0"></a></div> \
    <div class="field"><label>'+apptime+'<sup class="red-astrick">*</sup></label> \
    <input data-role="none" type="text" id="txtTime" class="clockpicker" onclick="time()" readonly /><a href="#" onclick="time()"><img src="images/clock.png"  border="0"></a><a onclick="openPopupShedule()"  style="float: right;display:'+sheduleDispl+'" >'+checkschedule_builder+'</a> \
    </div>\
    <div class="field"><label>'+appname+'<sup class="red-astrick">*</sup></label><input data-role="none" id="txtName" type="text"/></div> \
    <div class="field"><label>'+appemail+'<sup class="red-astrick">*</sup></label><input data-role="none" id="txtEmail" type="text"/></div> \
    <div class="field"><label>'+appphone+'<sup class="red-astrick">*</sup></label><input data-role="none"  id="txtPhone" type="text"/></div> \
    <div id="customAppointmentTags"></div>\
    <input data-role="none" type="submit" value="'+appconfirm+'" '+primaryColor+'  onclick="postAppointmentDataNew();"></div>';
                                               
                                     hellocalc+= '<div data-role="popup" id="popup-outside-page" class="popup-div cart-MSG" data-theme="b"><a id="closePop" onclick="closePopup();" href="#" class="er_close">X</a><div id="errorPopup" class="cart_error">'+test_builder+'</div><div id="scuccessPopup" class="cart_success">'+test_builder+'</div></div>';
                                     if(flagglobalindex==1)
                                     {
                                     flagglobalindex=0;
                                     $("#contentHolder2").empty();
                                     $("#contentHolder2").append("<div class='page-text' id='appDiv'>"+hellocalc+"</div>");
                                     $( "#popup-outside-page" ).enhanceWithin().popup();
                                     }
                                     else
                                     {
                                     if(multiPage==1)
                                     {
									 appendHtml("<div style='color:"+sessionStorage.getItem('pageTextColor')+";'><div class='page-text custom-color' id='appDiv'>"+hellocalc+"</div></div>",2,2);
                                     }
                                     else
                                     {
                                     appendHtml("<div style='color:"+sessionStorage.getItem('pageTextColor')+";'><div class='page-text custom-color' id='appDiv'>"+hellocalc+"</div></div>",2,1);
                                     }
                                     
                                      $( "#popup-outside-page" ).enhanceWithin().popup();
                                     }
                                     /*custom appointment changes */
									   document.getElementById("appDiv").style.display = 'none';
                                     customAppointmentFieldsget();
                                    
                                     
									 
				
				});
				


}


/*new Appointment shedule */
function openShedulePopup()
{
	
	var startTime=appointmentStartTimeArray;
	var endTime=appointmentEndTimeArray;
	var finalStartTime=[];
	
	for(var i=0;i<startTime.length;i++)
		{
		startTime[i]=startTime[i].replace("-",":");
		startTime[i]=startTime[i].replace("-"," ");
		
		endTime[i]=endTime[i].replace("-",":");
		endTime[i]=endTime[i].replace("-"," ");
		}
	
	 if($("#popup-outside-page2").html())
		 {
		 $("#popup-outside-page2").remove();
		 }
	 
	 
	var chartHtml="<div data-role='popup' id='popup-outside-page2' class='form-popup-div cart-MSG ui-popup ui-body-b ui-overlay-shadow ui-corner-all' data-theme='b'><a id='closePop' onclick='closePopupAppoint();'  class='er_close ui-link'>X</a>\
		<div id='scuccessPopup' class='' style='display: block;'>\
		<h2>"+schedule_builder+"</h2><ul>";
	
	if(appointOnOff[0]==1)
		chartHtml+="<li><span class='day'>"+sun_builder+"</span> <span style='color:#80FF00'> "+startTime[0]+" - "+endTime[0]+" </span></li>";
	else
		chartHtml+="<li><span class='day'>"+sun_builder+"</span> <span style='color:#FF0000'>"+closed_builder+"</span></li>";
	
	if(appointOnOff[1]==1)
		chartHtml+="<li><span class='day'>"+mon_builderr+"</span>  <span style='color:#80FF00'> "+startTime[1]+" - "+endTime[1]+" </span></li>";
	else
		chartHtml+="<li><span class='day'>"+mon_builderr+"</span> <span style='color:#FF0000'>"+closed_builder+"</span></li>";
	
	if(appointOnOff[2]==1)
		chartHtml+="<li><span class='day'>"+tue_builder+"</span> <span style='color:#80FF00'> "+startTime[2]+" - "+endTime[2]+" </span></li>";
	else
		chartHtml+="<li><span class='day'>"+tue_builder+"</span> <span style='color:#FF0000'>"+closed_builder+"</span></li>";
	
	if(appointOnOff[3]==1)
		chartHtml+="<li><span class='day'>"+wed_builder+"</span> <span style='color:#80FF00'> "+startTime[3]+" - "+endTime[3]+" </span></li>";
	else
		chartHtml+="<li><span class='day'>"+wed_builder+"</span> <span style='color:#FF0000'>"+closed_builder+"</span></li>";
	
	if(appointOnOff[4]==1)
		chartHtml+="<li><span class='day'>"+thu_builder+"</span><span style='color:#80FF00'> "+startTime[4]+" - "+endTime[4]+" </span></li>";
	else
		chartHtml+="<li><span class='day'>"+thu_builder+"</span> <span style='color:#FF0000'>"+closed_builder+"</span></li>";
	
	if(appointOnOff[5]==1)
		chartHtml+="<li><span class='day'>"+fri_builder+"</span><span style='color:#80FF00'>"+startTime[5]+" - "+endTime[5]+"</span></li>";
	else
		chartHtml+="<li><span class='day'>"+fri_builder+"</span> <span style='color:#FF0000'>"+closed_builder+"</span></li>";
	
	if(appointOnOff[6]==1)
		chartHtml+="<li><span class='day'>"+sat_builder+"</span> <span style='color:#80FF00'>"+startTime[6]+" - "+endTime[6]+"</span></li>";
	else
		chartHtml+="<li><span class='day'>"+sat_builder+"</span> <span style='color:#FF0000'>"+closed_builder+"</span></li>";
	
	
		chartHtml+="</ul></div></div>";
	 //$('#popup-outside-page2').remove();
	 $("#contentHolder2").append("<div class='page-text'>"+chartHtml+"</div>");
	 $('#popup-outside-page2').popup({ positionTo: "window" });
     $( "#popup-outside-page2" ).enhanceWithin().popup();
	
	



	 //openPopupShedule();
	    
	    //document.getElementById("scuccessPopup").innerHTML=chartHtml;
	    document.getElementById("scuccessPopup").style.display = 'block';
	    document.getElementById("errorPopup").style.display = 'none';
}

/*custom appointment changes */
function customAppointmentFieldsget()
{
$('.appypie-loader').show();
	var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/form-builder-soap#getAppCustomFormDetails";

	 var soapRequest =
	                '<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getAppCustomFormDetails xmlns=\"http://'+localStorage.getItem("reseller")+'/services/app-user-soap#getCustomLoginForm\"><appId>'+window.localStorage.getItem("applicationID")+'</appId><formNumber>'+formbuilderPageId+'</formNumber></getAppCustomFormDetails></soap:Body></soap:Envelope>';
	                
	                console.log("custom register" + soapRequest);
	                
	                $.ajax({
	                       type: "POST",
	                       url: wsUrl,
	                       contentType: "text/xml",
	                       dataType: "text",
	                       data: soapRequest,
	                       success: processSuccessxmlAppointmentForm,
	                       error: processErrorxmlAppointmentForm
	                       });


}
/*custom appointment changes */
function processSuccessxmlAppointmentForm(data, status, req)
{
	
	 var strJSON = $(req.responseText).find("return").text();
	 console.log("appointment custom data>>>>"+strJSON);
	 var obj = JSON.parse(strJSON);
	 var hellocalc="";
	 
	 
		for(var j=0;j<obj.length;j++)
	 	{
			AppointCustomElementsType[j]=obj[j].fieldType;
	 		AppointCustomElementsLabel[j]=obj[j].fieldLebal;
		}
	
	 for(var i=0;i<obj.length;i++){
	     
	       if(obj[i].fieldType=="name" || obj[i].fieldType=="email" || obj[i].fieldType=="phone" || obj[i].fieldType=="state" || obj[i].fieldType=="text"){
		   
		   if(obj[i].mandatory==1){
		   AppointCustomElementsMandatory[i]=1;
		     hellocalc+="<div  class='field'><label >"+obj[i].fieldLebal+" <sup class='red-astrick'>*</sup></label><div class='add_field_3'><input name='name' id="+i+" type='text' class='form-builder-form-input' data-role='none' onclick='removeFocus(this)'></div></div>";
			 
			

			 }else{
			 AppointCustomElementsMandatory[i]=0;
			 hellocalc+="<div  class='field'><label>"+obj[i].fieldLebal+" </label><div class='add_field_3'><input name='name' id="+i+" type='text' data-role='none'></div></div>";
			 
			 
			 }
		   
		   }
		   else if(obj[i].fieldType=="date"){
		    sessionStorage.setItem("ActualSelectorFor"+i, obj[i].fieldLebal);
		    if(obj[i].mandatory==1){
			AppointCustomElementsMandatory[i]=1;
		     hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+"<sup class='red-astrick'>*</sup></label><input data-role='none' type='text' id="+i+" class='clockpicker' onclick='show1(this.id);' readonly  /><a href='#' ><img src='images/calendar.png' onclick='show1("+i+");' border='0'></a></div>";
		   }else{
		   AppointCustomElementsMandatory[i]=0;
		    hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+"</label><input data-role='none' type='text' id="+i+" class='clockpicker' onclick='show1(this.id);' readonly  /><a href='#' ><img src='images/calendar.png' onclick='show1("+i+");' border='0'></a></div>";
		   }
		   }
	        else if(obj[i].fieldType=="time"){
			  if(obj[i].mandatory==1){
			  AppointCustomElementsMandatory[i]=1;
		     hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+"<sup class='red-astrick'>*</sup></label> <input data-role='none' type='text' id="+i+" class='clockpicker' onclick='time1(this.id)' readonly  /><a href='#' ><img src='images/clock.png' onclick='time1("+i+")' border='0'></a> </div>";
			 }else{
			 AppointCustomElementsMandatory[i]=0;
			   hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+"</label> <input data-role='none' type='text' id="+i+" class='clockpicker' onclick='time1(this.id)' readonly  /><a href='#' ><img src='images/clock.png' onclick='time1("+i+")' border='0'></a> </div>";
			 }
		   
		   }
		    else if(obj[i].fieldType=="gender"){
		    	
		    	
			 if(obj[i].mandatory==1){
			 AppointCustomElementsMandatory[i]=1;
		     hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+" <sup class='red-astrick'>*</sup></label><div class=''><select data-role='none' name='Country_2' id="+i+" onclick='removeFocus(this)'><option selected='selected' value='Select Gender' >"+selectgender_builder+"</option><option value='Male'>"+male_builder+"</option><option value='Female'>"+female_buildrer+"</option></select></div></div>";
		     }else{
			 AppointCustomElementsMandatory[i]=0;
			  hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+" </label><div class=''><select   data-role='none' name='Country_2' id="+i+"><option selected='selected' value='Select Gender'>"+selectgender_builder+"</option><option value='Male'>"+male_builder+"</option><option value='Female'>"+female_buildrer+"</option></select></div></div>";
			 
			 
			 }
		   }
		     else if(obj[i].fieldType=="country"){
			  if(obj[i].mandatory==1){
			   AppointCustomElementsMandatory[i]=1;
		     hellocalc+=getCountryFormBuilder(i);
			 }else{
			  AppointCustomElementsMandatory[i]=0;
			  hellocalc+=getCountryFormBuilder2(i);
			 }
		   
		   }
		     else if(obj[i].fieldType=="textarea"){
			  if(obj[i].mandatory==1){
			  AppointCustomElementsMandatory[i]=1;
		     hellocalc+=" <div class='field'><label>"+obj[i].fieldLebal+"<sup class='red-astrick'>*</sup></label><p><textarea id="+i+" data-role='none' onclick='removeFocus(this)'></textarea></p></div>";
		   }else{
		   AppointCustomElementsMandatory[i]=0;
		    hellocalc+=" <div class='field'><label>"+obj[i].fieldLebal+"</label><p><textarea id="+i+" data-role='none'></textarea></p></div>";
		   }
		   }
		     else if(obj[i].fieldType=="checkbox"){
			   if(obj[i].mandatory==1){
			   AppointCustomElementsMandatory[i]=1;
		     hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+"<sup class='red-astrick'>*</sup></label><div class='formbuildersubfield'>";
			 }else{
			 AppointCustomElementsMandatory[i]=0;
			  hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+"</label><div class='formbuildersubfield'>";
			 }
			 var tempName=obj[i].fieldLebal;
			 
			 for(var j=0;j<obj[i].countSubField;j++){
			 
			 if(obj[i][j+1].defaulSetOption==1){
			  hellocalc+="<div class='formbuilder-subfield'><input data-role='none' name="+tempName.replace(/[^A-Za-z0-9]+/g, 'X')+" id="+i+" class='secondCheckLebal' type='checkbox' checked value='"+obj[i][j+1].subFieldLebal+"'><label>"+obj[i][j+1].subFieldLebal+"</label></div>";
			  }else{
			  
			   hellocalc+="<div class='formbuilder-subfield'><input data-role='none' name="+tempName.replace(/[^A-Za-z0-9]+/g, 'X')+" id="+i+" class='secondCheckLebal' type='checkbox' value='"+obj[i][j+1].subFieldLebal+"'><label>"+obj[i][j+1].subFieldLebal+"</label></div>";
			  }
			 
			 }
			 
			  hellocalc+="</div></div>";
		   
		   }
		    else if(obj[i].fieldType=="radio"){
			
			 if(obj[i].mandatory==1){
			 AppointCustomElementsMandatory[i]=1;
		    hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+"<sup class='red-astrick'>*</sup></label><div class='formbuildersubfield'>";
			}
			else{
			AppointCustomElementsMandatory[i]=0;
			  hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+"</label><div class='formbuildersubfield'>";
			}
			  var tempName=obj[i].fieldLebal;
			 for(var j=0;j<obj[i].countSubField;j++){
			 
			 if(obj[i][j+1].defaulSetOption==1){
			  hellocalc+="<div class='formbuilder-subfield'><input data-role='none' name="+tempName.replace(/[^A-Za-z0-9]+/g, 'X')+" id="+i+" class='secondCheckLebal' type='radio' checked value='"+obj[i][j+1].subFieldLebal+"'><label>"+obj[i][j+1].subFieldLebal+"</label></div>";
			  }else{
			  
			   hellocalc+="<div class='formbuilder-subfield'><input data-role='none' name="+tempName.replace(/[^A-Za-z0-9]+/g, 'X')+" id="+i+" class='secondCheckLebal' type='radio' value='"+obj[i][j+1].subFieldLebal+"'><label>"+obj[i][j+1].subFieldLebal+"</label></div>";
			  }
			 
			 }
			 
			  hellocalc+="</div></div>";
		   
		   }
		    else if(obj[i].fieldType=="select"){
			 if(obj[i].mandatory==1){
			 AppointCustomElementsMandatory[i]=1;
		     hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+" <sup class='red-astrick'>*</sup></label><div class=''><select data-role='none' id="+i+" name='selectTxt' onclick='removeFocus(this)'>";
			 }
			 else{
			 AppointCustomElementsMandatory[i]=0;
			   hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+" </label><div class=''><select  data-role='none' id="+i+" name='selectTxt' >";
			 }
			 for(var j=0;j<obj[i].countSubField;j++){
			
			    // if(obj[i][j+1].defaulSetOption==1){
// 				
// 				 hellocalc+="<option selected='selected' value="+obj[i][j+1].subFieldValue+">"+obj[i][j+1].subFieldLebal+"</option>";
// 				}else{
// 				
// 				 hellocalc+="<option  value="+obj[i][j+1].subFieldValue+">"+obj[i][j+1].subFieldLebal+"</option>";
// 				}


if(j==0){
     hellocalc+="<option value=' ' style='display:none;'>"+obj[i][j+1].subFieldLebal+"</option>";    
    }
    else
    {   
        if(obj[i][j+1].defaulSetOption==1){
    
      hellocalc+="<option selected='selected' value="+obj[i][j+1].subFieldValue+">"+obj[i][j+1].subFieldLebal+"</option>";
     }else{
    
      hellocalc+="<option  value="+obj[i][j+1].subFieldValue+">"+obj[i][j+1].subFieldLebal+"</option>";
     }
    }


			 
		   }
		    hellocalc+="</select></div></div>";
		   
		   }
		    
		    else if(obj[i].fieldType=="multiselect"){
				 if(obj[i].mandatory==1){
				 AppointCustomElementsMandatory[i]=1;
			     hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+" <sup class='red-astrick'>*</sup></label><div class=''><select data-role='none' id="+i+" multiple='multiple' name='selectTxt2' onclick='removeFocus(this)'>";
				 }
				 else{
				 AppointCustomElementsMandatory[i]=0;
				   hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+" </label><div class=''><select  data-role='none' id="+i+" name='selectTxt2' multiple='multiple'>";
				 }
				 for(var j=0;j<obj[i].countSubField;j++){
				
				  
					
					 hellocalc+="<option value="+obj[i][j+1].subFieldValue+">"+obj[i][j+1].subFieldLebal+"</option>";
					
				 
			   }
			    hellocalc+="</select></div></div>";
			   
			   }
		    
		    
		    else if(obj[i].fieldType=="uploadPicture"){
			   
			  
			   
			   if(obj[i].mandatory==1){
				   AppointCustomElementsMandatory[i]=1;
				    uploadFileCount++;
				   hellocalc+="<div style='clear: both;' class='field'><label class='form-builder-form-label'>"+obj[i].fieldLebal+" <sup class='red-astrick'>*</sup></label>" +
				   		"<div class='add-file'><span id="+i+" onclick='selectFile(this)' class='browser'>"+attachfile_builder+"</span></div></div>";
				   
			   }else{
				   
				   AppointCustomElementsMandatory[i]=0;
				     uploadFileCount++;
				   hellocalc+="<div style='clear: both;' class='field'><label class='form-builder-form-label'>"+obj[i].fieldLebal+"</label><div class='add-file'><span onclick='selectFile(this)' id="+i+"  class='browser'>"+attachfile_builder+"</span></div></div>";
			   }
			   
		   }
	 
	 }
	   document.getElementById("appDiv").style.display = 'block';
		$("#customAppointmentTags").append(hellocalc);
		openShedulePopup();
		 $(".red-astrick").attr("style","color:red !important;");
			 setTimeout(function(){$('.appypie-loader').hide();},1000);
}
/*custom appointment changes */
function processErrorxmlAppointmentForm()
{
	

}

function showQuotePage(multiPage){
	
	
if(multiPage==1){
		
		
		/*if(window.localStorage.getItem("layout")=="slidemenu"){
		    $("#reload").hide();
		    $("#mainback").show();
		}
		else {
		    $("#logo").hide();
		    $("#mainback").show();
		}
		
		
	}else{
		sessionStorage.setItem("singlePage","yes");
		 $("#logo").hide();
        $("#mainback").show();
        $("#shopmenu").hide();*/
		
		
	}
$formBuilderIndex.find("quote").each(function()
					  {
					         var quoteFormName = $(this).find("quoteFormName").text().trim();
						   var email = $(this).find("email").text().trim();
						   var subject = $(this).find("subject").text().trim();
						   var quotename = $(this).find("quotename").text().trim();
						   var quoteemail = $(this).find("quoteemail").text().trim();
						   var quotephone = $(this).find("quotephone").text().trim();
						   var quotecomment = $(this).find("quotecomment").text().trim();
						   var quotesubmit = $(this).find("quotesubmit").text().trim();
						     var quoteIcon = $(this).find("quoteIcon").text().trim();
                                     submissionSuccessMsg=$(this).find("submissionSuccessMsg").text();
                                     submissionErrorMsg=$(this).find("submissionErrorMsg").text();
requestQuoteJson='{'
    +'"Name":"'+quotename+'",'
    +'"Email":"'+quoteemail+'",'
    +'"Phone":"'+quotephone+'",'
    +'"Comment":"'+quotecomment+'"'
    +'}';							 
							  
	var quoteHTML="";
	quoteHTML= '<div id="form-page" class="form-page"><input data-role="none" id="quotename" type="text"  placeholder="'+quotename+'*"  /><input data-role="none" id="quoteemail" type="text" placeholder="'+quoteemail+'*" /><input data-role="none" type="text" id="quotephone" placeholder="'+quotephone+'" /><textarea id="quotecomment" placeholder="'+quotecomment+'" class="comments" ></textarea><input data-role="none" data-role="none" id="submit" type="submit" value="'+quotesubmit+'" class="button" '+primaryColor+' onclick="sendQuoteData();"/></div>';
	quoteHTML+= '<div data-role="popup" id="popup-outside-page" class="popup-div cart-MSG" data-theme="b"><a id="closePop" onclick="closePopup();" href="#" class="er_close">X</a><div id="errorPopup" class="cart_error">'+test_builder+'</div><div id="scuccessPopup" class="cart_success">'+test_builder+'</div></div>';
                                     
                                     if(flagglobalindex==1)
                                     {
                                     flagglobalindex=0;
                                     $("#contentHolder2").empty();
                                     $("#contentHolder2").append("<div class='page-text'>"+quoteHTML+"</div>");
                                     $( "#popup-outside-page" ).enhanceWithin().popup();
                                     }
                                     else
                                     {
                                     if(multiPage==1)
                                     {
                                     appendHtml("<div class='page-text'>"+quoteHTML+"</div>",2,2);
                                     }
                                     else
                                     {
                                     appendHtml("<div class='page-text'>"+quoteHTML+"</div>",2,1);
                                     }
                                     
                                     setTimeout(function() {
                                                $( "#popup-outside-page" ).enhanceWithin().popup();
                                                },10);
                                     }
						 
					  }
					  );


}

function showCustomPage(indexId,multiPage){
	$('.appypie-loader').show();
	
	 uploadFileCount=0;
	 globalExactPath="";
if(multiPage==1){
		
		
		/*if(window.localStorage.getItem("layout")=="slidemenu"){
		    $("#reload").hide();
		    $("#mainback").show();
		}
		else {
		    $("#logo").hide();
		    $("#mainback").show();
		}
		
		
	}else{
		sessionStorage.setItem("singlePage","yes");
		$("#logo").hide();
        $("#mainback").show();
        $("#shopmenu").hide();*/
		
	}
	
getCustomData(indexId);


}

function getCustomData(indexValue){

 var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/form-builder-soap#getCustomeFormDetails";

 var soapRequest =
                '<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getCustomeFormDetails xmlns=\"http://'+localStorage.getItem("reseller")+'/services/form-builder-soap#getCustomeFormDetails\"><appId>'+window.localStorage.getItem("applicationID")+'</appId><formNumber>'+formbuilderPageId+'</formNumber></getCustomeFormDetails></soap:Body></soap:Envelope>';
                
                console.log(" " + soapRequest);
                
                $.ajax({
                       type: "POST",
                       url: wsUrl,
                       contentType: "text/xml",
                       dataType: "text",
                       data: soapRequest,
                       success: processSuccessxmlCustomForm,
                       error: processErrorxmlCustomForm
                       });
                       


}

function processSuccessxmlCustomForm(data, status, req){
 

			  var groupCounter=0;
			  var checkBoxCounter=0;
	         //var groupCounter=0;
			 console.log("krishna>>>>>>>>>"+$formBuilderIndex.text());
    $formBuilderIndex.find("custom").each(function()
					  {
						  console.log("krishna>>>>>>>>>"+$formBuilderIndex.find("custom").text());
					       var customFormName = $(this).find("customFormName").text().trim();
						   var formDescription = $(this).find("formDescription").text().trim();
						   var headerImage = $(this).find("headerImage").text().trim();
						   var requestEmail = $(this).find("requestEmail").text().trim();
						   var submissionButton = $(this).find("submissionButton").text().trim();
						   var submissionEmailSub = $(this).find("submissionEmailSub").text().trim();
						   var alertMsg = $(this).find("alertMsg").text().trim();
						   var customIcon = $(this).find("customIcon").text().trim();
						   var headerImage=$(this).find("headerImage").text().trim();
						   mailHeadingText=$(this).find("mailHeadingText").text().trim();
						   submissionErrorMsg=$(this).find("submissionErrorMsg").text().trim();
						   submissionSuccessMsg=$(this).find("submissionSuccessMsg").text().trim();
						   var headerImages="";
						   
						   var strJSON = $(req.responseText).find("return").text();
						  
						   console.log("Response field data : " + strJSON);
						   var obj = JSON.parse(strJSON);
						   var hellocalc="";
						   
						   if(headerImage.trim()!='')
						   {
						  
							headerImages="<div class='formbuilder-details-header' style='background-image:url("+headerImage+")'></div>"
						   }
						   else
						   {
						  
						   headerImages="<div class='formbuilder-details-header' style='display:none;'></div>"
						   
						   }
							
			   hellocalc+="<div class='main'><div class='appointment'><h2 style='text-align:"+sessionStorage.getItem("pageIndentation")+" !important'>"+customFormName+"</h2>"+headerImages+"<p class='form_description'>"+formDescription+"</p> <div class='formdis' id='custom_field_inner'>";
				
				for(var j=0;j<obj.length;j++){
				
				customElementsType[j]=obj[j].fieldType;
				customElementsLabel[j]=obj[j].fieldLebal;
				}
			 totalElements=obj.length;
 for(var i=0;i<obj.length;i++){
     
       if(obj[i].fieldType=="name" || obj[i].fieldType=="email" || obj[i].fieldType=="phone" || obj[i].fieldType=="state" || obj[i].fieldType=="text"){
	   
	   if(obj[i].mandatory==1){
	   customElementsMandatory[i]=1;
	     hellocalc+="<div style='clear: both;' class='custom_li_3'><label class='form-builder-form-label'>"+obj[i].fieldLebal+" <sup class='red-astrick'>*</sup></label><div class='add_field_3'><input name='name' id="+i+" type='text' class='form-builder-form-input' data-role='none' onclick='removeFocus(this)'></div></div>";
		 
		

		 }else{
		 customElementsMandatory[i]=0;
		 hellocalc+="<div style='clear: both;' class='custom_li_3'><label>"+obj[i].fieldLebal+" </label><div class='add_field_3'><input name='name' id="+i+" type='text' data-role='none'></div></div>";
		 
		 
		 }
	   
	   }
	   else if(obj[i].fieldType=="date"){
	   
	     sessionStorage.setItem("ActualSelectorFor"+i, obj[i].fieldLebal);
	    if(obj[i].mandatory==1){
		customElementsMandatory[i]=1;
	     hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+"<sup class='red-astrick'>*</sup></label><input data-role='none' type='text' id="+i+" class='clockpicker' onclick='show1(this.id);' readonly  /><a href='#' ><img src='images/calendar.png' onclick='show1("+i+");' border='0'></a></div>";
	   }else{
	   customElementsMandatory[i]=0;
	    hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+"</label><input data-role='none' type='text' id="+i+" class='clockpicker' onclick='show1(this.id);' readonly  /><a href='#' ><img src='images/calendar.png' onclick='show1("+i+");' border='0'></a></div>";
	   }
	   }
        else if(obj[i].fieldType=="time"){
		  if(obj[i].mandatory==1){
		  customElementsMandatory[i]=1;
	     hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+"<sup class='red-astrick'>*</sup></label> <input data-role='none' type='text' id="+i+" class='clockpicker' onclick='time1(this.id)' readonly  /><a href='#' ><img src='images/clock.png' onclick='time1("+i+")' border='0'></a> </div>";
		 }else{
		 customElementsMandatory[i]=0;
		   hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+"</label> <input data-role='none' type='text' id="+i+" class='clockpicker' onclick='time1(this.id)' readonly  /><a href='#' ><img src='images/clock.png' onclick='time1("+i+")' border='0'></a> </div>";
		 }
	   
	   }
	    else if(obj[i].fieldType=="gender"){
		 if(obj[i].mandatory==1){
		 customElementsMandatory[i]=1;
	     hellocalc+="<div><label>"+obj[i].fieldLebal+" <sup class='red-astrick'>*</sup></label><div class=''><select data-role='none' name='Country_2' id="+i+" onclick='removeFocus(this)'><option selected='selected' value='Select Gender' >"+selectgender_builder+"</option><option value='Male'>"+male_builder+"</option><option value='Female'>"+female_buildrer+"</option></select></div></div>";
	     }else{
		 customElementsMandatory[i]=0;
		  hellocalc+="<div><label>"+obj[i].fieldLebal+"</label><div class=''><select   data-role='none' name='Country_2' id="+i+"><option selected='selected' value='Select Gender'>"+selectgender_builder+"</option><option value='Male'>"+male_builder+"</option><option value='Female'>"+female_buildrer+"</option></select></div></div>";
		 
		 
		 }
	   }
	     else if(obj[i].fieldType=="country"){
		  if(obj[i].mandatory==1){
		   customElementsMandatory[i]=1;
	     hellocalc+=getCountryFormBuilder(i);
		 }else{
		  customElementsMandatory[i]=0;
		  hellocalc+=getCountryFormBuilder2(i);
		 }
	   
	   }
	     else if(obj[i].fieldType=="textarea"){
		  if(obj[i].mandatory==1){
		  customElementsMandatory[i]=1;
	     hellocalc+=" <div class='field'><label>"+obj[i].fieldLebal+"<sup class='red-astrick'>*</sup></label><p><textarea id="+i+" data-role='none' onclick='removeFocus(this)'></textarea></p></div>";
	   }else{
	   customElementsMandatory[i]=0;
	    hellocalc+=" <div class='field'><label>"+obj[i].fieldLebal+"</label><p><textarea id="+i+" data-role='none'></textarea></p></div>";
	   }
	   }
	     else if(obj[i].fieldType=="checkbox"){
		   if(obj[i].mandatory==1){
		   customElementsMandatory[i]=1;
	     hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+"<sup class='red-astrick'>*</sup></label><div class='formbuildersubfield'>";
		 }else{
		 customElementsMandatory[i]=0;
		  hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+"</label><div class='formbuildersubfield'>";
		 }
		 var tempName=obj[i].fieldLebal+checkBoxCounter;
		// tempName = tempName.replace(/ /g , "");
		 tempName = tempName.replace(/:/g , "");
		 tempName = tempName.replace(/[&\/\\#,+()$~%.'":*?<>{}]/g, '');
		 customElementsLabel[i] = tempName;
		  tempName = tempName.replace(/[A-Za-z]+/g, 'X');
		 tempName = tempName.replace(/\s/g, '');
		 //alert(tempName + "---- " + tempName.replace(/[A-Za-z]+/g, 'X'));
		  
		 for(var j=0;j<obj[i].countSubField;j++){
		 
		 if(obj[i][j+1].defaulSetOption==1){
		  hellocalc+="<div class='formbuilder-subfield'><input data-role='none' name="+tempName.replace(/[A-Za-z]+/g, 'X')+" id="+i+" class='secondCheckLebal' type='checkbox' checked value='"+obj[i][j+1].subFieldLebal+"'><label>"+obj[i][j+1].subFieldLebal+"</label></div>";
		  }else{
		  
		   hellocalc+="<div class='formbuilder-subfield'><input data-role='none' name="+tempName+" id="+i+" class='secondCheckLebal' type='checkbox' value='"+obj[i][j+1].subFieldLebal+"'><label>"+obj[i][j+1].subFieldLebal+"</label></div>";
		  }
		 
		 }
		 checkBoxCounter++;
		  hellocalc+="</div></div>";
	   
	   }
	    else if(obj[i].fieldType=="radio"){
		
		 if(obj[i].mandatory==1){
		 customElementsMandatory[i]=1;
	    hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+"<sup class='red-astrick'>*</sup></label><div class='formbuildersubfield'>";
		}
		else{
		customElementsMandatory[i]=0;
		  hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+"</label><div class='formbuildersubfield'>";
		}
		  //var tempName=obj[i].fieldLebal;
		   var tempName=obj[i].fieldLebal+groupCounter;
		   tempName = tempName.replace(/ /g , "");
			 tempName = tempName.replace(/:/g , "");
			 tempName = tempName.replace(/[&\/\\#,+()$~%.'":*?<>{}]/g, '');
		 for(var j=0;j<obj[i].countSubField;j++){
		 
		 if(obj[i][j+1].defaulSetOption==1){
		  hellocalc+="<div class='formbuilder-subfield'><input data-role='none' name="+tempName.replace(/[A-Za-z]+/g, 'X')+'r'+" id="+i+" class='secondCheckLebal' type='radio' checked value='"+obj[i][j+1].subFieldLebal+"'><label>"+obj[i][j+1].subFieldLebal+"</label></div>";
		  }else{
		  
		   hellocalc+="<div class='formbuilder-subfield'><input data-role='none' name="+tempName.replace(/[A-Za-z]+/g, 'X')+'r'+" id="+i+" class='secondCheckLebal' type='radio' value='"+obj[i][j+1].subFieldLebal+"'><label>"+obj[i][j+1].subFieldLebal+"</label></div>";
		  }
		 
		 }
		 groupCounter++;
		  hellocalc+="</div></div>";
	   
	   }
	    else if(obj[i].fieldType=="select"){
		 if(obj[i].mandatory==1){
		 customElementsMandatory[i]=1;
	     hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+" <sup class='red-astrick'>*</sup></label><div class=''><select data-role='none' id="+i+" name='selectTxt' onclick='removeFocus(this)'>";
		 }
		 else{
		 customElementsMandatory[i]=0;
		   hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+" </label><div class=''><select  data-role='none' id="+i+" name='selectTxt' >";
		 }
		 for(var j=0;j<obj[i].countSubField;j++){
		
		    if(obj[i][j+1].defaulSetOption==1){
			
			 hellocalc+="<option selected='selected' value="+obj[i][j+1].subFieldValue+">"+obj[i][j+1].subFieldLebal+"</option>";
			}else{
			
			 hellocalc+="<option  value="+obj[i][j+1].subFieldValue+">"+obj[i][j+1].subFieldLebal+"</option>";
			}
		 
	   }
	    hellocalc+="</select></div></div>";
	   
	   }
	    else if(obj[i].fieldType=="multiselect"){
			 if(obj[i].mandatory==1){
			 customElementsMandatory[i]=1;
		     hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+" <sup class='red-astrick'>*</sup></label><div class=''><select data-role='none' id="+i+" name='selectTxt2' multiple='multiple' onclick='removeFocus(this)'>";
			 }
			 else{
			 customElementsMandatory[i]=0;
			   hellocalc+="<div class='field'><label>"+obj[i].fieldLebal+" </label><div class=''><select  data-role='none' id="+i+" name='selectTxt2' multiple='multiple' >";
			 }
			 for(var j=0;j<obj[i].countSubField;j++){
			
				 hellocalc+="<option  value="+obj[i][j+1].subFieldValue+">"+obj[i][j+1].subFieldLebal+"</option>";
			
		   }
		    hellocalc+="</select></div></div>";
		   
		   }
	    
	    
	    
	    
	    else if(obj[i].fieldType=="uploadPicture"){
		  
		   if(obj[i].mandatory==1){
			   customElementsMandatory[i]=1;
			    uploadFileCount++;
			   hellocalc+="<div style='clear: both;' class='custom_li_3'><label class='form-builder-form-label'>"+obj[i].fieldLebal+" <sup class='red-astrick'>*</sup></label>" +
			   		"<div class='add-file'><span id="+i+" onclick='selectFile(this)' class='browser'>"+attachfile_builder+"</span></div></div>";
			   
		   }else{
			   
			   customElementsMandatory[i]=0;
			     uploadFileCount++;
			   hellocalc+="<div style='clear: both;' class='custom_li_3'><label class='form-builder-form-label'>"+obj[i].fieldLebal+"</label><div class='add-file'><span onclick='selectFile(this)' id="+i+"  class='browser'>"+attachfile_builder+"</span></div></div>";
		   }
		   
	   }
 
 }
						   
                                          var payStatus = $(this).find("payStatus").text();
                                          var paypalId = $(this).find("paypalId").text();
                                          var payObtion = $(this).find("payObtion").text();
                                          var amountLabel = $(this).find("amountLabel").text();
                                          var paypalAmount = $(this).find("paypalAmount").text();
                                          var amountCurrency = $(this).find("amountCurrency").text();
                                          var amountLabelToShow=amountLabel+" ("+amountCurrency+")";
                                          var defaultCurrency = $(this).find("defaultCurrency").text();
										  if(!paypalAmount.trim())
										  {
										  paypalAmount="0.0";
										  }
										  
                                          localStorage.setItem("payStatus",payStatus);
                                          localStorage.setItem("paypalId",paypalId);
                                          localStorage.setItem("payObtion",payObtion);
                                          localStorage.setItem("amountCurrency",amountCurrency);
                                          
                                          localStorage.setItem("FbPaymentSuccess","");
                                          if(payStatus!=0)
                                          {
                                          localStorage.setItem("FbPaymentSuccess",null);
                                          
                                          if(payObtion=='payAmt')
                                          {
                                          hellocalc+="<div style='clear: both;' class='custom_li_3'><label class='form-builder-form-label'><span class='field_lebal_3'>"+amountLabelToShow+"</span> <sup class='red-astrick'>*</sup></label><div class='add_field_3'><input name='name' id='payId' type='text' class='form-builder-form-input' data-role='none' onclick='removeFocus(this)' value="+paypalAmount+" readonly></div></div>";
                                          
                                          }
                                          if(payObtion=='payAmtcust')
                                          {
                                        	  
                                        	 
                                          
                                          localStorage.setItem("amountCurrency","USD");
                                          //Notes the given currency is paypaled supported
                                          hellocalc+="<div style='clear: both;' class='custom_li_3'><label class='form-builder-form-label'><span class='field_lebal_3'>"+amountLabel+"</span> <sup class='red-astrick'>*</sup></label><div class='add_field_3'><input name='name' id='payId' type='text' data-role='none' onclick='removeFocus(this)' value="+paypalAmount+" style='width:30% !important'>&nbsp;<select  id='amount_currency' data-role='none'  onclick='removeFocus(this)' style='width:20% !important'><option value='USD' selected='selected' >"+usd_builder+"</option><option value='GBP' >"+gbp_builder+"</option><option value='EUR' >"+eur_builder+"</option><option value='AUD' >"+aud_builder+"</option><option value='CAD' >"+cad_builder+"</option><option value='CHF' >"+chf_builder+"</option><option value='CZK' >"+czk_builder+"</option><option value='DKK' >"+dkk_builder+"</option><option value='HKD' >"+hkd_builder+"</option><option value='HUF' >"+huf_builder+"</option><option value='JPY' >"+jpy_builder+"</option><option value='NOK' >"+nok_builder+"</option><option value='NZD' >"+nzd_builder+"</option><option value='PLN' >"+pln_builder+"</option><option value='SEK' >"+sek_builder+"</option><option value='SGD' >"+sgd_builder+
										  "</option><option value='ZAR' >"+zar_builder+"</option></<select></div></div>";
                                          }
//                                          {
//                                          hellocalc+="<div style='clear: both;' class='custom_li_3'><label class='form-builder-form-label'><span class='field_lebal_3'>"+amountLabelToShow+"</span> <sup class='red-astrick'>*</sup></label><div class='add_field_3'><input name='name' id='payId' type='text' class='form-builder-form-input' data-role='none' onclick='removeFocus(this)' value="+paypalAmount+" ></div></div>";
//                                          }
                                          
                                          }

   hellocalc+="</div><input data-role='none' id='txt_app_confirm'  value='"+submissionButton+"' onclick='sendValueToServer("+obj.length+");' type='submit' "+primaryColor+" class='submission_button'></div></div>";	
	
  /*hellocalc+="<div data-role='popup'  id='popup-outside-page' data-theme='b' class='popup-div cart-MSG'>\
  <p id='scuccessPopup' class='cart_success'style='display:none'></p>\
  <p id='errorPopup' class='cart_error'></p>\
  <a onclick='closePopup();' href='#' class='er_close'>X</a>\
  </div>";
hellocalc+="<a href='#popup-outside-page' class='ui-btn ui-corner-all ui-shadow ui-btn-inline' data-rel='popup'>Position to window</a>\
	<div data-role='popup' id='popup-outside-page' class='popup-div cart-MSG' data-theme='b' data-position-to='window'>\
	<p id='scuccessPopup' class='cart_success'style='display:none'></p>\
          <p id='errorPopup' class='cart_error' ></p>\
          <a onclick='' href='#' class='er_close'>X</a>\
          </div>";*/
                                          
		hellocalc+='<div data-role="popup" id="popup-outside-page" class="popup-div cart-MSG" data-theme="b"><a id="closePop" onclick="closePopup();">X</a><div id="errorPopup" class="cart_error">'+test_builder+'</div><div id="scuccessPopup" class="cart_success">'+test_builder+'</div></div>';setTimeout(function(){$('.appypie-loader').hide();},1000);					
		if(flagglobalindex==1)
                                          {
                                          flagglobalindex=0;
                                          $("#contentHolder2").empty();
                                          $("#contentHolder2").append("<div style='color:"+sessionStorage.getItem('pageTextColor')+" !important;' ><div class='page-text custom-color'>"+hellocalc+"</div></div>");
                                          $( "#popup-outside-page" ).enhanceWithin().popup();
                                          }
                                          else
                                          {
                                          console.log('hellocalc 2');
                                          
                                          if(sessionStorage.getItem("DirFormAppendIndex"))
                                          {
                                          var DirFormAppendIndex=sessionStorage.getItem("DirFormAppendIndex");
                                          sessionStorage.removeItem("DirFormAppendIndex");
                                           appendHtml("<div style='color:"+sessionStorage.getItem('pageTextColor')+" !important;' ><div class='page-text custom-color'>"+hellocalc+"</div></div>",DirFormAppendIndex,DirFormAppendIndex);
                                          
                                          }
                                          else
                                          {
                                          if(sessionStorage.getItem('singlePage') == 'true')
                                          {
                                          console.log('hellocalc 3');
                                          appendHtml("<div style='color:"+sessionStorage.getItem('pageTextColor')+" !important;' ><div class='page-text custom-color'>"+hellocalc+"</div></div>",2,1);
                                          
                                          }
                                          else
                                          {
                                          
                                          appendHtml("<div style='color:"+sessionStorage.getItem('pageTextColor')+" !important;' ><div class='page-text custom-color'>"+hellocalc+"</div></div>",2,2);
                                          }
                                          }
                                          setTimeout(function()
                                                     {
													  $('#amount_currency').val(defaultCurrency);
                                                     $( "#popup-outside-page" ).enhanceWithin().popup();
                                                     },100);
                                          }
	}
				
				);
    
				

				


}

function processErrorxmlCustomForm(response, textStatus, errorThrown) {
//alert("ravi");
           console.log("Error : " + JSON.stringify(response));
           console.log("Error : " + textStatus);
           console.log("Error : " + errorThrown.responseText);
           }
		   
		   
function getCountryFormBuilder(id){
	
	

return "<div class='field'><label>"+country_builder+"<sup class='red-astrick'>*</sup></label><div class=''><select  id='"+id+"' data-role='none' name='Country_2' onclick='removeFocus(this)'><option selected='selected' value='Select Country'>"+selectcountry_builder+"</option><option value='United States'>United States</option><option value='United Kingdom'>United Kingdom</option><option value='Afghanistan'>Afghanistan</option><option value='Albania'>Albania</option><option value='Algeria'>Algeria</option><option value='American Samoa'>American Samoa</option><option value='Andorra'>Andorra</option><option value='Angola'>Angola</option><option value='Anguilla'>Anguilla</option><option value='Antarctica'>Antarctica</option><option value='Antigua and Barbuda'>Antigua and Barbuda</option><option value='Argentina'>Argentina</option><option value='Armenia'>Armenia</option><option value='Aruba'>Aruba</option><option value='Australia'>Australia</option><option value='Austria'>Austria</option><option value='Azerbaijan'>Azerbaijan</option><option value='Bahamas'>Bahamas</option><option value='Bahrain'>Bahrain</option><option value='Bangladesh'>Bangladesh</option><option value='Barbados'>Barbados</option><option value='Belarus'>Belarus</option><option value='Belgium'>Belgium</option><option value='Belize'>Belize</option><option value='Benin'>Benin</option><option value='Bermuda'>Bermuda</option><option value='Bhutan'>Bhutan</option><option value='Bolivia'>Bolivia</option><option value='Bosnia and Herzegovina'>Bosnia and Herzegovina</option><option value='Botswana'>Botswana</option><option value='Bouvet Island'>Bouvet Island</option><option value='Brazil'>Brazil</option><option value='British Indian Ocean Territory'>British Indian Ocean Territory</option><option value='Brunei Darussalam'>Brunei Darussalam</option><option value='Bulgaria'>Bulgaria</option><option value='Burkina Faso'>Burkina Faso</option><option value='Burundi'>Burundi</option><option value='Cambodia'>Cambodia</option><option value='Cameroon'>Cameroon</option><option value='Canada'>Canada</option><option value='Cape Verde'>Cape Verde</option><option value='Cayman Islands'>Cayman Islands</option><option value='Central African Republic'>Central African Republic</option><option value='Chad'>Chad</option><option value='Chile'>Chile</option><option value='China'>China</option><option value='Christmas Island'>Christmas Island</option><option value='Cocos (Keeling) Islands'>Cocos (Keeling) Islands</option><option value='Colombia'>Colombia</option><option value='Comoros'>Comoros</option><option value='Congo'>Congo</option><option value='Congo, The Democratic Republic of The'>Congo, The Democratic Republic of The</option><option value='Cook Islands'>Cook Islands</option><option value='Costa Rica'>Costa Rica</option><option value='Cote D'ivoire'>Cote D'ivoire</option><option value='Croatia'>Croatia</option><option value='Cuba'>Cuba</option><option value='Cyprus'>Cyprus</option><option value='Czech Republic'>Czech Republic</option><option value='Denmark'>Denmark</option><option value='Djibouti'>Djibouti</option><option value='Dominica'>Dominica</option><option value='Dominican Republic'>Dominican Republic</option><option value='Ecuador'>Ecuador</option><option value='Egypt'>Egypt</option><option value='El Salvador'>El Salvador</option><option value='Equatorial Guinea'>Equatorial Guinea</option><option value='Eritrea'>Eritrea</option><option value='Estonia'>Estonia</option><option value='Ethiopia'>Ethiopia</option><option value='Falkland Islands (Malvinas)'>Falkland Islands (Malvinas)</option><option value='Faroe Islands'>Faroe Islands</option><option value='Fiji'>Fiji</option><option value='Finland'>Finland</option><option value='France'>France</option><option value='French Guiana'>French Guiana</option><option value='French Polynesia'>French Polynesia</option><option value='French Southern Territories'>French Southern Territories</option><option value='Gabon'>Gabon</option><option value='Gambia'>Gambia</option><option value='Georgia'>Georgia</option><option value='Germany'>Germany</option><option value='Ghana'>Ghana</option><option value='Gibraltar'>Gibraltar</option><option value='Greece'>Greece</option><option value='Greenland'>Greenland</option><option value='Grenada'>Grenada</option><option value='Guadeloupe'>Guadeloupe</option><option value='Guam'>Guam</option><option value='Guatemala'>Guatemala</option><option value='Guinea'>Guinea</option><option value='Guinea-bissau'>Guinea-bissau</option><option value='Guyana'>Guyana</option><option value='Haiti'>Haiti</option><option value='Heard Island and Mcdonald Islands'>Heard Island and Mcdonald Islands</option><option value='Holy See (Vatican City State)'>Holy See (Vatican City State)</option><option value='Honduras'>Honduras</option><option value='Hong Kong'>Hong Kong</option><option value='Hungary'>Hungary</option><option value='Iceland'>Iceland</option><option value='India'>India</option><option value='Indonesia'>Indonesia</option><option value='Iran, Islamic Republic of'>Iran, Islamic Republic of</option><option value='Iraq'>Iraq</option><option value='Ireland'>Ireland</option><option value='Israel'>Israel</option><option value='Italy'>Italy</option><option value='Jamaica'>Jamaica</option><option value='Japan'>Japan</option><option value='Jordan'>Jordan</option><option value='Kazakhstan'>Kazakhstan</option><option value='Kenya'>Kenya</option><option value='Kiribati'>Kiribati</option><option value='Korea, Democratic People's Republic of'>Korea, Democratic People's Republic of</option><option value='Korea, Republic of'>Korea, Republic of</option><option value='Kuwait'>Kuwait</option><option value='Kyrgyzstan'>Kyrgyzstan</option><option value='Lao People's Democratic Republic'>Lao People's Democratic Republic</option><option value='Latvia'>Latvia</option><option value='Lebanon'>Lebanon</option><option value='Lesotho'>Lesotho</option><option value='Liberia'>Liberia</option><option value='Libyan Arab Jamahiriya'>Libyan Arab Jamahiriya</option><option value='Liechtenstein'>Liechtenstein</option><option value='Lithuania'>Lithuania</option><option value='Luxembourg'>Luxembourg</option><option value='Macao'>Macao</option><option value='Macedonia, The Former Yugoslav Republic of'>Macedonia, The Former Yugoslav Republic of</option><option value='Madagascar'>Madagascar</option><option value='Malawi'>Malawi</option><option value='Malaysia'>Malaysia</option><option value='Maldives'>Maldives</option><option value='Mali'>Mali</option><option value='Malta'>Malta</option><option value='Marshall Islands'>Marshall Islands</option><option value='Martinique'>Martinique</option><option value='Mauritania'>Mauritania</option><option value='Mauritius'>Mauritius</option><option value='Mayotte'>Mayotte</option><option value='Mexico'>Mexico</option><option value='Micronesia, Federated States of'>Micronesia, Federated States of</option><option value='Moldova, Republic of'>Moldova, Republic of</option><option value='Monaco'>Monaco</option><option value='Mongolia'>Mongolia</option><option value='Montserrat'>Montserrat</option><option value='Morocco'>Morocco</option><option value='Mozambique'>Mozambique</option><option value='Myanmar'>Myanmar</option><option value='Namibia'>Namibia</option><option value='Nauru'>Nauru</option><option value='Nepal'>Nepal</option><option value='Netherlands'>Netherlands</option><option value='Netherlands Antilles'>Netherlands Antilles</option><option value='New Caledonia'>New Caledonia</option><option value='New Zealand'>New Zealand</option><option value='Nicaragua'>Nicaragua</option><option value='Niger'>Niger</option><option value='Nigeria'>Nigeria</option><option value='Niue'>Niue</option><option value='Norfolk Island'>Norfolk Island</option><option value='Northern Mariana Islands'>Northern Mariana Islands</option><option value='Norway'>Norway</option><option value='Oman'>Oman</option><option value='Pakistan'>Pakistan</option><option value='Palau'>Palau</option><option value='Palestinian Territory, Occupied'>Palestinian Territory, Occupied</option><option value='Panama'>Panama</option><option value='Papua New Guinea'>Papua New Guinea</option><option value='Paraguay'>Paraguay</option><option value='Peru'>Peru</option><option value='Philippines'>Philippines</option><option value='Pitcairn'>Pitcairn</option><option value='Poland'>Poland</option><option value='Portugal'>Portugal</option><option value='Puerto Rico'>Puerto Rico</option><option value='Qatar'>Qatar</option><option value='Reunion'>Reunion</option><option value='Romania'>Romania</option><option value='Russian Federation'>Russian Federation</option><option value='Rwanda'>Rwanda</option><option value='Saint Helena'>Saint Helena</option><option value='Saint Kitts and Nevis'>Saint Kitts and Nevis</option><option value='Saint Lucia'>Saint Lucia</option><option value='Saint Pierre and Miquelon'>Saint Pierre and Miquelon</option><option value='Saint Vincent and The Grenadines'>Saint Vincent and The Grenadines</option><option value='Samoa'>Samoa</option><option value='San Marino'>San Marino</option><option value='Sao Tome and Principe'>Sao Tome and Principe</option><option value='Saudi Arabia'>Saudi Arabia</option><option value='Senegal'>Senegal</option><option value='Serbia and Montenegro'>Serbia and Montenegro</option><option value='Seychelles'>Seychelles</option><option value='Sierra Leone'>Sierra Leone</option><option value='Singapore'>Singapore</option><option value='Slovakia'>Slovakia</option><option value='Slovenia'>Slovenia</option><option value='Solomon Islands'>Solomon Islands</option><option value='Somalia'>Somalia</option><option value='South Africa'>South Africa</option><option value='South Georgia and The South Sandwich Islands'>South Georgia and The South Sandwich Islands</option><option value='Spain'>Spain</option><option value='Sri Lanka'>Sri Lanka</option><option value='Sudan'>Sudan</option><option value='Suriname'>Suriname</option><option value='Svalbard and Jan Mayen'>Svalbard and Jan Mayen</option><option value='Swaziland'>Swaziland</option><option value='Sweden'>Sweden</option><option value='Switzerland'>Switzerland</option><option value='Syrian Arab Republic'>Syrian Arab Republic</option><option value='Taiwan, Province of China'>Taiwan, Province of China</option><option value='Tajikistan'>Tajikistan</option><option value='Tanzania, United Republic of'>Tanzania, United Republic of</option><option value='Thailand'>Thailand</option><option value='Timor-leste'>Timor-leste</option><option value='Togo'>Togo</option><option value='Tokelau'>Tokelau</option><option value='Tonga'>Tonga</option><option value='Trinidad and Tobago'>Trinidad and Tobago</option><option value='Tunisia'>Tunisia</option><option value='Turkey'>Turkey</option><option value='Turkmenistan'>Turkmenistan</option><option value='Turks and Caicos Islands'>Turks and Caicos Islands</option><option value='Tuvalu'>Tuvalu</option><option value='Uganda'>Uganda</option><option value='Ukraine'>Ukraine</option><option value='United Arab Emirates'>United Arab Emirates</option><option value='United Kingdom'>United Kingdom</option><option value='United States'>United States</option><option value='United States Minor Outlying Islands'>United States Minor Outlying Islands</option><option value='Uruguay'>Uruguay</option><option value='Uzbekistan'>Uzbekistan</option><option value='Vanuatu'>Vanuatu</option><option value='Venezuela'>Venezuela</option><option value='Viet Nam'>Viet Nam</option><option value='Virgin Islands, British'>Virgin Islands, British</option><option value='Virgin Islands, U.S.'>Virgin Islands, U.S.</option><option value='Wallis and Futuna'>Wallis and Futuna</option><option value='Western Sahara'>Western Sahara</option><option value='Yemen'>Yemen</option><option value='Zambia'>Zambia</option><option value='Zimbabwe'>Zimbabwe</option></select></div></div>";

}

function getCountryFormBuilder2(id){

return "<div class='field'><label>"+country_builder+"</label><div class=''><select data-role='none' id='"+id+"' name='Country_2'><option selected='selected' value='Select Country'>"+selectcountry_builder+"</option><option value='United States'>United States</option><option value='United Kingdom'>United Kingdom</option><option value='Afghanistan'>Afghanistan</option><option value='Albania'>Albania</option><option value='Algeria'>Algeria</option><option value='American Samoa'>American Samoa</option><option value='Andorra'>Andorra</option><option value='Angola'>Angola</option><option value='Anguilla'>Anguilla</option><option value='Antarctica'>Antarctica</option><option value='Antigua and Barbuda'>Antigua and Barbuda</option><option value='Argentina'>Argentina</option><option value='Armenia'>Armenia</option><option value='Aruba'>Aruba</option><option value='Australia'>Australia</option><option value='Austria'>Austria</option><option value='Azerbaijan'>Azerbaijan</option><option value='Bahamas'>Bahamas</option><option value='Bahrain'>Bahrain</option><option value='Bangladesh'>Bangladesh</option><option value='Barbados'>Barbados</option><option value='Belarus'>Belarus</option><option value='Belgium'>Belgium</option><option value='Belize'>Belize</option><option value='Benin'>Benin</option><option value='Bermuda'>Bermuda</option><option value='Bhutan'>Bhutan</option><option value='Bolivia'>Bolivia</option><option value='Bosnia and Herzegovina'>Bosnia and Herzegovina</option><option value='Botswana'>Botswana</option><option value='Bouvet Island'>Bouvet Island</option><option value='Brazil'>Brazil</option><option value='British Indian Ocean Territory'>British Indian Ocean Territory</option><option value='Brunei Darussalam'>Brunei Darussalam</option><option value='Bulgaria'>Bulgaria</option><option value='Burkina Faso'>Burkina Faso</option><option value='Burundi'>Burundi</option><option value='Cambodia'>Cambodia</option><option value='Cameroon'>Cameroon</option><option value='Canada'>Canada</option><option value='Cape Verde'>Cape Verde</option><option value='Cayman Islands'>Cayman Islands</option><option value='Central African Republic'>Central African Republic</option><option value='Chad'>Chad</option><option value='Chile'>Chile</option><option value='China'>China</option><option value='Christmas Island'>Christmas Island</option><option value='Cocos (Keeling) Islands'>Cocos (Keeling) Islands</option><option value='Colombia'>Colombia</option><option value='Comoros'>Comoros</option><option value='Congo'>Congo</option><option value='Congo, The Democratic Republic of The'>Congo, The Democratic Republic of The</option><option value='Cook Islands'>Cook Islands</option><option value='Costa Rica'>Costa Rica</option><option value='Cote D'ivoire'>Cote D'ivoire</option><option value='Croatia'>Croatia</option><option value='Cuba'>Cuba</option><option value='Cyprus'>Cyprus</option><option value='Czech Republic'>Czech Republic</option><option value='Denmark'>Denmark</option><option value='Djibouti'>Djibouti</option><option value='Dominica'>Dominica</option><option value='Dominican Republic'>Dominican Republic</option><option value='Ecuador'>Ecuador</option><option value='Egypt'>Egypt</option><option value='El Salvador'>El Salvador</option><option value='Equatorial Guinea'>Equatorial Guinea</option><option value='Eritrea'>Eritrea</option><option value='Estonia'>Estonia</option><option value='Ethiopia'>Ethiopia</option><option value='Falkland Islands (Malvinas)'>Falkland Islands (Malvinas)</option><option value='Faroe Islands'>Faroe Islands</option><option value='Fiji'>Fiji</option><option value='Finland'>Finland</option><option value='France'>France</option><option value='French Guiana'>French Guiana</option><option value='French Polynesia'>French Polynesia</option><option value='French Southern Territories'>French Southern Territories</option><option value='Gabon'>Gabon</option><option value='Gambia'>Gambia</option><option value='Georgia'>Georgia</option><option value='Germany'>Germany</option><option value='Ghana'>Ghana</option><option value='Gibraltar'>Gibraltar</option><option value='Greece'>Greece</option><option value='Greenland'>Greenland</option><option value='Grenada'>Grenada</option><option value='Guadeloupe'>Guadeloupe</option><option value='Guam'>Guam</option><option value='Guatemala'>Guatemala</option><option value='Guinea'>Guinea</option><option value='Guinea-bissau'>Guinea-bissau</option><option value='Guyana'>Guyana</option><option value='Haiti'>Haiti</option><option value='Heard Island and Mcdonald Islands'>Heard Island and Mcdonald Islands</option><option value='Holy See (Vatican City State)'>Holy See (Vatican City State)</option><option value='Honduras'>Honduras</option><option value='Hong Kong'>Hong Kong</option><option value='Hungary'>Hungary</option><option value='Iceland'>Iceland</option><option value='India'>India</option><option value='Indonesia'>Indonesia</option><option value='Iran, Islamic Republic of'>Iran, Islamic Republic of</option><option value='Iraq'>Iraq</option><option value='Ireland'>Ireland</option><option value='Israel'>Israel</option><option value='Italy'>Italy</option><option value='Jamaica'>Jamaica</option><option value='Japan'>Japan</option><option value='Jordan'>Jordan</option><option value='Kazakhstan'>Kazakhstan</option><option value='Kenya'>Kenya</option><option value='Kiribati'>Kiribati</option><option value='Korea, Democratic People's Republic of'>Korea, Democratic People's Republic of</option><option value='Korea, Republic of'>Korea, Republic of</option><option value='Kuwait'>Kuwait</option><option value='Kyrgyzstan'>Kyrgyzstan</option><option value='Lao People's Democratic Republic'>Lao People's Democratic Republic</option><option value='Latvia'>Latvia</option><option value='Lebanon'>Lebanon</option><option value='Lesotho'>Lesotho</option><option value='Liberia'>Liberia</option><option value='Libyan Arab Jamahiriya'>Libyan Arab Jamahiriya</option><option value='Liechtenstein'>Liechtenstein</option><option value='Lithuania'>Lithuania</option><option value='Luxembourg'>Luxembourg</option><option value='Macao'>Macao</option><option value='Macedonia, The Former Yugoslav Republic of'>Macedonia, The Former Yugoslav Republic of</option><option value='Madagascar'>Madagascar</option><option value='Malawi'>Malawi</option><option value='Malaysia'>Malaysia</option><option value='Maldives'>Maldives</option><option value='Mali'>Mali</option><option value='Malta'>Malta</option><option value='Marshall Islands'>Marshall Islands</option><option value='Martinique'>Martinique</option><option value='Mauritania'>Mauritania</option><option value='Mauritius'>Mauritius</option><option value='Mayotte'>Mayotte</option><option value='Mexico'>Mexico</option><option value='Micronesia, Federated States of'>Micronesia, Federated States of</option><option value='Moldova, Republic of'>Moldova, Republic of</option><option value='Monaco'>Monaco</option><option value='Mongolia'>Mongolia</option><option value='Montserrat'>Montserrat</option><option value='Morocco'>Morocco</option><option value='Mozambique'>Mozambique</option><option value='Myanmar'>Myanmar</option><option value='Namibia'>Namibia</option><option value='Nauru'>Nauru</option><option value='Nepal'>Nepal</option><option value='Netherlands'>Netherlands</option><option value='Netherlands Antilles'>Netherlands Antilles</option><option value='New Caledonia'>New Caledonia</option><option value='New Zealand'>New Zealand</option><option value='Nicaragua'>Nicaragua</option><option value='Niger'>Niger</option><option value='Nigeria'>Nigeria</option><option value='Niue'>Niue</option><option value='Norfolk Island'>Norfolk Island</option><option value='Northern Mariana Islands'>Northern Mariana Islands</option><option value='Norway'>Norway</option><option value='Oman'>Oman</option><option value='Pakistan'>Pakistan</option><option value='Palau'>Palau</option><option value='Palestinian Territory, Occupied'>Palestinian Territory, Occupied</option><option value='Panama'>Panama</option><option value='Papua New Guinea'>Papua New Guinea</option><option value='Paraguay'>Paraguay</option><option value='Peru'>Peru</option><option value='Philippines'>Philippines</option><option value='Pitcairn'>Pitcairn</option><option value='Poland'>Poland</option><option value='Portugal'>Portugal</option><option value='Puerto Rico'>Puerto Rico</option><option value='Qatar'>Qatar</option><option value='Reunion'>Reunion</option><option value='Romania'>Romania</option><option value='Russian Federation'>Russian Federation</option><option value='Rwanda'>Rwanda</option><option value='Saint Helena'>Saint Helena</option><option value='Saint Kitts and Nevis'>Saint Kitts and Nevis</option><option value='Saint Lucia'>Saint Lucia</option><option value='Saint Pierre and Miquelon'>Saint Pierre and Miquelon</option><option value='Saint Vincent and The Grenadines'>Saint Vincent and The Grenadines</option><option value='Samoa'>Samoa</option><option value='San Marino'>San Marino</option><option value='Sao Tome and Principe'>Sao Tome and Principe</option><option value='Saudi Arabia'>Saudi Arabia</option><option value='Senegal'>Senegal</option><option value='Serbia and Montenegro'>Serbia and Montenegro</option><option value='Seychelles'>Seychelles</option><option value='Sierra Leone'>Sierra Leone</option><option value='Singapore'>Singapore</option><option value='Slovakia'>Slovakia</option><option value='Slovenia'>Slovenia</option><option value='Solomon Islands'>Solomon Islands</option><option value='Somalia'>Somalia</option><option value='South Africa'>South Africa</option><option value='South Georgia and The South Sandwich Islands'>South Georgia and The South Sandwich Islands</option><option value='Spain'>Spain</option><option value='Sri Lanka'>Sri Lanka</option><option value='Sudan'>Sudan</option><option value='Suriname'>Suriname</option><option value='Svalbard and Jan Mayen'>Svalbard and Jan Mayen</option><option value='Swaziland'>Swaziland</option><option value='Sweden'>Sweden</option><option value='Switzerland'>Switzerland</option><option value='Syrian Arab Republic'>Syrian Arab Republic</option><option value='Taiwan, Province of China'>Taiwan, Province of China</option><option value='Tajikistan'>Tajikistan</option><option value='Tanzania, United Republic of'>Tanzania, United Republic of</option><option value='Thailand'>Thailand</option><option value='Timor-leste'>Timor-leste</option><option value='Togo'>Togo</option><option value='Tokelau'>Tokelau</option><option value='Tonga'>Tonga</option><option value='Trinidad and Tobago'>Trinidad and Tobago</option><option value='Tunisia'>Tunisia</option><option value='Turkey'>Turkey</option><option value='Turkmenistan'>Turkmenistan</option><option value='Turks and Caicos Islands'>Turks and Caicos Islands</option><option value='Tuvalu'>Tuvalu</option><option value='Uganda'>Uganda</option><option value='Ukraine'>Ukraine</option><option value='United Arab Emirates'>United Arab Emirates</option><option value='United Kingdom'>United Kingdom</option><option value='United States'>United States</option><option value='United States Minor Outlying Islands'>United States Minor Outlying Islands</option><option value='Uruguay'>Uruguay</option><option value='Uzbekistan'>Uzbekistan</option><option value='Vanuatu'>Vanuatu</option><option value='Venezuela'>Venezuela</option><option value='Viet Nam'>Viet Nam</option><option value='Virgin Islands, British'>Virgin Islands, British</option><option value='Virgin Islands, U.S.'>Virgin Islands, U.S.</option><option value='Wallis and Futuna'>Wallis and Futuna</option><option value='Western Sahara'>Western Sahara</option><option value='Yemen'>Yemen</option><option value='Zambia'>Zambia</option><option value='Zimbabwe'>Zimbabwe</option></select></div></div>";

}


function setFocus(elementID){
	
 var textbox = document.getElementById(elementID);
		  textbox.style.border = "1px solid #FE2E2E";
		     textbox.focus();
		     textbox.scrollIntoView();

}

function removeFocus(object){
	
var textbox = document.getElementById(object.id);
textbox.style.removeProperty('border');

}

function paypalFormbuilderReturn(isSuccess)
{
    //alert(""+isSuccess);
    if(isSuccess=='Success')
    {
        localStorage.setItem("FbPaymentSuccess","Success");
		//alert("Deepakkkk11");
        sendValueToServer();
    }
    else  if(isSuccess=='decline')
    {
        localStorage.setItem("FbPaymentSuccess",null);
    }
    
    
}
function PayPaypalExpressForFormbuilder()
{
    //https://developer.paypal.com/docs/classic/paypal-payments-standard/ht_test-pps-buttons/
    //<paypalId>info@searchengine-optimization.net</paypalId>
    var amountCurrency;
    var payObtion=localStorage.getItem("payObtion");
    if(payObtion=="payAmt")
    {
        amountCurrency= localStorage.getItem("amountCurrency");
    }
    else
    {
        amountCurrency=document.getElementById("amount_currency").value.trim();;
    }

   
    var orderId= (new Date().getTime()).toString(36);
    var paypalAddFormHtml='<!-- Specify a Buy Now button. -->\
    <input type="hidden" name="cmd" value="_xclick">\
    <!-- Specify details about the item that buyers will purchase. -->\
    <input type="hidden" name="item_name" value="'+paymenor_builder+localStorage.getItem("AppName")+'('+localStorage.getItem("applicationID")+')">\
    <input type="hidden" name="amount" value="'+parseFloat(localStorage.getItem("formBuilderPayAmount")).toFixed(2)+'">\
    <input type="hidden" name="quantity" value="1">\
    <input type="hidden" name="currency_code" value="'+amountCurrency+'">\
    <input name="bn" value="AppyPie_SP" type=" hidden">\
    <input type="hidden" name="custom" value="1">';

    $('.appypie-loader').show();
    
    
   var  owneremail=masterData.getElementsByTagName('owneremail')[0].firstChild.nodeValue;
    var paypalIdHtml;
    var paypalUrlFormHtml;
   //  if(owneremail=="mayankr@onsinteractive.com")
//     {
//          paypalIdHtml= '<!DOCTYPE HTML><html><body onload="ClickButtonPaypal();"><form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post"><!-- Identify your business so that you can collect the payments. --><input type="hidden" name="business" value="'+localStorage.getItem('paypalId')+'">';
//         
//          paypalUrlFormHtml='<!-- URL --><input name="cancel_return" type="hidden" value="http://'+localStorage.getItem("reseller")+'/paypalmobile/cancelformbuilder"><input type="hidden" name="return" value="http://'+localStorage.getItem("reseller")+'/paypalmobile/successformbuilder" /><!-- Display the payment button. --><input type="image" src="{URL}/images/subscribe_btn.png" name="submit" id="submit" alt="PayPal - The safer, easier way to pay online!"><img alt="" border="0" src="http://www.sandbox.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1"></form><script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script><script>function ClickButtonPaypal(){$(\'#submit\').trigger(\'click\');}</script></body></html>';
//     
//     }
//     else
//     {
//     
//      paypalIdHtml= '<!DOCTYPE HTML><html><body onload="ClickButtonPaypal();"><form action="https://www.paypal.com/cgi-bin/webscr" method="post"><!-- Identify your business so that you can collect the payments. --><input type="hidden" name="business" value="'+localStorage.getItem('paypalId')+'">';
// 
//       paypalUrlFormHtml='<!-- URL --><input name="cancel_return" type="hidden" value="http://'+localStorage.getItem("reseller")+'/paypalmobile/cancelformbuilder"><input type="hidden" name="return" value="http://'+localStorage.getItem("reseller")+'/paypalmobile/successformbuilder" /><!-- Display the payment button. --><input type="image" src="{URL}/images/subscribe_btn.png" name="submit" id="submit" alt="PayPal - The safer, easier way to pay online!"><img alt="" border="0" src="http://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1"></form><script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script><script>function ClickButtonPaypal(){$(\'#submit\').trigger(\'click\');}</script></body></html>';
// 
//     }

if(owneremail=="mayankr@onsinteractive.com")
    {
         paypalIdHtml= '<!DOCTYPE HTML><html><body onload="ClickButtonPaypal();"><form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post"><!-- Identify your business so that you can collect the payments. --><input type="hidden" name="business" value="'+localStorage.getItem('paypalId')+'">';
        
         paypalUrlFormHtml='<!-- URL --><input type="hidden" name="return" value="http://'+localStorage.getItem("reseller")+'/paypalmobile/successformbuilder" /><!-- Display the payment button. --><input type="image" src="{URL}/images/subscribe_btn.png" name="submit" id="submit" alt="PayPal - The safer, easier way to pay online!"><img alt="" border="0" src="http://www.sandbox.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1"></form><script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script><script>function ClickButtonPaypal(){$(\'#submit\').trigger(\'click\');}</script></body></html>';
  //<input name="cancel_return" type="hidden" value="http://'+localStorage.getItem("reseller")+'/paypalmobile/cancelformbuilder">
    }
    else
    {
    
     paypalIdHtml= '<!DOCTYPE HTML><html><body onload="ClickButtonPaypal();"><form action="https://www.paypal.com/cgi-bin/webscr" method="post"><!-- Identify your business so that you can collect the payments. --><input type="hidden" name="business" value="'+localStorage.getItem('paypalId')+'">';

      paypalUrlFormHtml='<!-- URL --><input type="hidden" name="return" value="http://'+localStorage.getItem("reseller")+'/paypalmobile/successformbuilder" /><!-- Display the payment button. --><input type="image" src="{URL}/images/subscribe_btn.png" name="submit" id="submit" alt="PayPal - The safer, easier way to pay online!"><img alt="" border="0" src="http://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1"></form><script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script><script>function ClickButtonPaypal(){$(\'#submit\').trigger(\'click\');}</script></body></html>';
   //<input name="cancel_return" type="hidden" value="http://'+localStorage.getItem("reseller")+'/paypalmobile/cancelformbuilder">
    }

        console.log("html of paypal--->"+paypalIdHtml+paypalAddFormHtml+paypalUrlFormHtml);
        window.location="foodpaypal:"+encodeURI(paypalIdHtml+paypalAddFormHtml+paypalUrlFormHtml);
        setTimeout(function(){$('.appypie-loader').hide();},1000);

}


function sendValueToServer(len)
{
//alert("deepak");
var radioCounter=0;
    var isFbPaymentSuccess=localStorage.getItem("FbPaymentSuccess");
    if(isFbPaymentSuccess=='Success')
    {
		 //alert("Success");
        len=parseFloat(localStorage.getItem("formBuilderLength"));
    }
    
    if( customElementsLabel.length==0)
	{
        //alert("ff");
        return false;
        
    }
	else
	{
	

    for(var i=0;i<len;i++){
		//alert(i +len);
		//alert(i );//+ " , "+(customElementsType[i] +" , "+document.getElementById(i).value.trim() +" , "+customElementsMandatory[i]);
		//alert(customElementsType[i] );
		//alert(customElementsMandatory[i]);
    if(customElementsType[i]=="name" || customElementsType[i]=="email" || customElementsType[i]=="phone" || customElementsType[i]=="state" || customElementsType[i]=="text"){
	 
	 var txt=document.getElementById(i).value.trim();
	
	 if (txt == '' && customElementsMandatory[i]==1) {
	 
		
	 openPopup();
                     document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                     document.getElementById("errorPopup").style.display = 'block';
                     document.getElementById("scuccessPopup").style.display = 'none';
                     setFocus(i);
		return false;
   }
	 else if(customElementsType[i]=="phone" && customElementsMandatory[i]==1)
	   {
	   
	   		if(!IsNumeric1(txt))
	   			{
	   			openPopup();
	   			document.getElementById("errorPopup").innerHTML=ErrorPhoneField;
              document.getElementById("errorPopup").style.display = 'block';
              document.getElementById("scuccessPopup").style.display = 'none';
              setFocus(i);
              return false;
	   			}
	   	
	   
	   }
	 
	 else if(customElementsType[i]=="email" && customElementsMandatory[i]==1)
	 {
	   
	   
	   if(!validateForm1(txt))
			{
		    openPopup();
			document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
			document.getElementById("errorPopup").style.display = 'block';
			document.getElementById("scuccessPopup").style.display = 'none';
			setFocus(i);
			return false;
			}
	   else{
		   
		   userEmail=txt;
	   		}
	   
	 
	 }
		
	  customElementsValue[i]=txt;
	}
	 else if(customElementsType[i]=="date"){
	 
	  var txt=document.getElementById(i).value;
	 
	 if (txt == '' && customElementsMandatory[i]==1) {
		 
 openPopup();
                     document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                     document.getElementById("errorPopup").style.display = 'block';
                     document.getElementById("scuccessPopup").style.display = 'none';
                     setFocus(i);
		return false;
   }
	   customElementsValue[i]=txt;
	   
	   }
	    else if(customElementsType[i]=="time"){
	 
	  var txt=document.getElementById(i).value.trim();
	  
	 if (txt == '' && customElementsMandatory[i]==1) {
		 
	 openPopup();
                     document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                     document.getElementById("errorPopup").style.display = 'block';
                     document.getElementById("scuccessPopup").style.display = 'none';
                     setFocus(i);
		return false;
   }
	  customElementsValue[i]=txt;
	   
	   }
	      else if(customElementsType[i]=="textarea"){
	 
	  var txt=document.getElementById(i).value.trim();
	   
	 if (txt == '' && customElementsMandatory[i]==1) {
		
       openPopup();
                     document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                     document.getElementById("errorPopup").style.display = 'block';
                     document.getElementById("scuccessPopup").style.display = 'none';
                     setFocus(i);
		return false;
   }
	 customElementsValue[i]=txt;
	   
	   }
	    else if(customElementsType[i]=="gender"){
	 
	  var txt=document.getElementById(i).value.trim();
	 
	 if (txt == 'Select Gender' && customElementsMandatory[i]==1) {
		
	 openPopup();
                     document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                     document.getElementById("errorPopup").style.display = 'block';
                     document.getElementById("scuccessPopup").style.display = 'none';
                     setFocus(i);
		return false;
   }
	  customElementsValue[i]=txt;
	   
	   }
	    else if(customElementsType[i]=="country"){
	 
	  var txt=document.getElementById(i).value.trim();
	 //alert(txt);
	 if (txt == 'Select Country' && customElementsMandatory[i]==1) {
		 
     openPopup();
                     document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                     document.getElementById("errorPopup").style.display = 'block';
                     document.getElementById("scuccessPopup").style.display = 'none';
                     setFocus(i);
		return false;
   }
	 
	    customElementsValue[i]=txt;
	   }
	   else if(customElementsType[i]=="checkbox"){
	 
	  var favorite = [];
	
	  var tempNam=customElementsLabel[i];
	 
	  tempNam = tempNam.replace(/[A-Za-z]+/g, 'X');
	  tempNam = tempNam.replace(/\s/g, '');
	 
           $.each($("input[name="+tempNam+"]:checked"), function()
		   {  
				favorite.push($(this).val());               
           });    

		
		   if(favorite.length == 0 && customElementsMandatory[i]==1){
			   //alert(" checkbox errrr");
			  
		    openPopup();
                     document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                     document.getElementById("errorPopup").style.display = 'block';
                     document.getElementById("scuccessPopup").style.display = 'none';
		return false;
		   
		   }
	       customElementsValue[i]=favorite.join(",");
	 
	   
	   }  
	   else if(customElementsType[i]=="radio"){
	  //alert(" radioo errrr");
	  var favorite = [];
	 
	 var tempNam=customElementsLabel[i] + radioCounter;
	 tempNam = tempNam.replace(/ /g , "");
	 tempNam = tempNam.replace(/:/g , "");
	 tempNam = tempNam.replace(/\?/g, "");
	 //alert(" tempNam "+tempNam +" , "+customElementsLabel[i] +" , "+radioCounter);
	   // alert(tempNam.replace(/[A-Za-z]+/g, 'X'));
           $.each($("input[name="+tempNam.replace(/[A-Za-z]+/g, 'X')+'r'+"]:checked"), function()
		   {            
               favorite.push($(this).val());
			   
			  // alert(favorite);
           });
			
			  if(favorite.length == 0 && customElementsMandatory[i]==2){
				  
				   //alert(" radio  errrrlll  "+favorite.length  +" , "+customElementsMandatory[i]);
				   
		   openPopup();
                     document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                     document.getElementById("errorPopup").style.display = 'block';
                     document.getElementById("scuccessPopup").style.display = 'none';
		return false;
		   
		   }
			 customElementsValue[i]=favorite.join(",");
          // alert("My favourite Radio are: " + favorite.join(", "));
	radioCounter++;
	 
	   
	   }
	   else if(customElementsType[i]=="select"){
	 
	 
	var e = document.getElementById(i);
    var txt = e.options[e.selectedIndex].text;
	
	  if (txt == '' && customElementsMandatory[i]==1) {
		
       openPopup();
                     document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                     document.getElementById("errorPopup").style.display = 'block';
                     document.getElementById("scuccessPopup").style.display = 'none';
                     setFocus(i);
		return false;
   }
	  customElementsValue[i]=txt;
	
	
	 
	   
	   }
    
	   else if(customElementsType[i]=="multiselect"){
			 
			 
		   var txt = '';
	   		$('#'+i+' :selected').each(function(i, selected){ 
	   			txt= txt+$(selected).text()+","; 
	   		});
			
	   		txt=txt.substring(0, txt.length-1);
			  if (txt == '' && customElementsMandatory[i]==1) {
				
				  				openPopup();
		                     document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
		                     document.getElementById("errorPopup").style.display = 'block';
		                     document.getElementById("scuccessPopup").style.display = 'none';
		                     setFocus(i);
				return false;
		   }
			  customElementsValue[i]=txt;
			
			
			 
			   
			   }
	   else if(customElementsType[i]=="uploadPicture"){
		   var txt=document.getElementById(i).innerHTML.trim();
		   
		   if (txt == 'Attach File' && customElementsMandatory[i]==1) {
			 
			       	openPopup();
                     document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                     document.getElementById("errorPopup").style.display = 'block';
                     document.getElementById("scuccessPopup").style.display = 'none';
                     setFocus(i);
					return false;
			   }
		   // fileName=txt;
		   
		   if(txt == 'Attach File')
			   {
			   
			   customElementsValue[i]='';
			   }
		   else{
			   customElementsValue[i]=txt;
			   
		   }
		 }
	   
}
}

   var deviceToken=window.toaster.getDeviceToken();
    var payStatus= localStorage.getItem("payStatus");
    var isFbPaymentSuccess=localStorage.getItem("FbPaymentSuccess");
    
    
    if((payStatus!=0)&&(isFbPaymentSuccess==null||isFbPaymentSuccess=='null'))
    {
        
        sessionStorage.setItem("customFormLength",len);
        var payAmount=document.getElementById("payId").value.trim();
        if(payAmount=="")
        {
            openPopup();
            document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
            document.getElementById("errorPopup").style.display = 'block';
            document.getElementById("scuccessPopup").style.display = 'none';
            setFocus(payId);
            return false;
        }
        
        localStorage.setItem("formBuilderPayAmount",payAmount);
        localStorage.setItem("formBuilderLength",len);
        PayPaypalExpressForFormbuilder();
    }
    else
    {
        $('.appypie-loader').show();
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/form-builder-soap#sendCustomMailTemplate";
        var datatoSend='';
        var imagenameCount=0;
        var jsonData = {};
        var jsonData2 = {};
        var jsonData3={};
        for(var k=0;k<len+2;k++){
        console.log("krishna>>>>"+k);
        if(typeof customElementsLabel[k] =='undefined'){


        }
        else
        {
			//alert("singhal " + customElementsType[k]);

            jsonData3[k]=customElementsType[k];
            //jsonData2[k]=customElementsLabel[k].replace('&','and');
			 customElementsLabel[k] = customElementsLabel[k].replace('&','and');
			//jsonData2[k]=customElementsLabel[k].replace(/[0-9]/g, "");
			jsonData2[k]=customElementsLabel[k];
            jsonData[k] = customElementsValue[k];

        }
        if(parseFloat(k) == parseFloat(len))
        {
            jsonData3[k]="deviceToken";
            jsonData2[k]="Device Token";
            jsonData[k] =deviceToken;
        }
        else if(parseFloat(k) > parseFloat(len))
        {
            jsonData3[k]="deviceType";
            jsonData2[k]="Device Type";
            jsonData[k] ="android";
        }


    }

        
        var paypalId=localStorage.getItem("paypalId");
        var payObtion=localStorage.getItem("payObtion");
        var amountCurrency;
        
		  if(payStatus!=0)
        {
            var payAmount=document.getElementById("payId").value.trim();
            var paymentName=["Paypal Id","Payment Type","Payment Amount","Amount Currency"];
            var paymentType=["Paypal Id","Payment Type","Payment Amount","Amount Currency"];
            var payMentOption;
            if(payObtion=="payAmt")
            {
                 amountCurrency= localStorage.getItem("amountCurrency");
                payMentOption="Predefined";
            }
            else
            {
                 amountCurrency=document.getElementById("amount_currency").value.trim();
                payMentOption="Custom";
            }
            
            var paymentData=[paypalId,payMentOption,payAmount,amountCurrency];

            var  lenth=len+2;
            var indexvalue=0;
            for(var k=lenth;k<lenth+4;k++)
            {
                    jsonData3[k]=paymentType[indexvalue];
                    jsonData2[k]=paymentName[indexvalue];
                    jsonData[k] = paymentData[indexvalue];
                indexvalue++;
            }
        }
		
		
    var customFormName;
    var formDescription; 
    var headerImage; 
    var requestEmail; 
    var submissionButton ;
    var submissionEmailSub; 
    var alertMsg ;
    var customIcon;

    $formBuilderIndex.find("custom").each(function()
    {
        customFormName = $(this).find("customFormName").text().trim();
        formDescription = $(this).find("formDescription").text().trim();
        headerImage = $(this).find("headerImage").text().trim();
        requestEmail = $(this).find("requestEmail").text().trim();
        submissionButton = $(this).find("submissionButton").text().trim();
        submissionEmailSub = $(this).find("submissionEmailSub").text().trim();
        alertMsg = $(this).find("alertMsg").text().trim();
        customIcon = $(this).find("customIcon").text().trim();

        //alert(quoteFormName);
        //alert(quoteIcon);


    }
    );



    //alert("formLabel>>>"+JSON.stringify(jsonData2));
    //alert("formData>>>"+JSON.stringify(jsonData));





    var mediaFiles = globalimageurl;
    fileName = Math.random()+'_'+globalimageurl.split("/").pop();
    var fileURI = mediaFiles;
    var ft = new FileTransfer();
    var options = new FileUploadOptions();
    options.fileKey = "file";
    options.fileName = fileName;
    options.mimeType = "text/plain";
    var params = new Object();
    params.appId = localStorage.getItem('applicationID');
    params.formPageId = formbuilderPageId;
    params.emailId = requestEmail;
    params.subject = submissionEmailSub;
    params.totalFile = uploadFileCount;
    params.formName = customFormName;
    params.formData = JSON.stringify(jsonData);
    params.formLabel = JSON.stringify(jsonData2);
    options.params = params;
    if(fileURI!=null && fileURI.length>1)
    {

    console.log("10-->");
    window.toaster.uploadMultipleFiles(localStorage.getItem('applicationID'),formbuilderPageId,
    requestEmail,submissionEmailSub,uploadFileCount,customFormName, JSON.stringify(jsonData),JSON.stringify(jsonData2),globalExactPath,JSON.stringify(jsonData3),mailHeadingText);

    //ft.upload(fileURI, encodeURI("http://apps.appypie.com/formbuilder/send-custom-mail-template"), sendOnServerSuccess, sendServerError, options);
    }
    else
    {
	if(localStorage.getItem('applicationID').indexOf("f07cd1a2e5d4")!=-1)
	{
	requestEmail=userEmail;
	}
	
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><sendCustomMailTemplate xmlns=\"http://'+localStorage.getItem("reseller")+'/services/form-builder-soap#sendCustomMailTemplate\"><appId>'+window.localStorage.getItem("applicationID")+'</appId><formPageId>'+formbuilderPageId+'</formPageId><emailId>'+requestEmail+'</emailId><subject><![CDATA['+submissionEmailSub+']]></subject><alertMe>'+alertMsg+'</alertMe><formName><![CDATA['+customFormName+']]></formName><formData><![CDATA['+JSON.stringify(jsonData)+']]></formData><image></image><imageName><![CDATA['+JSON.stringify(jsonData2)+']]></imageName><userEmail></userEmail><formFields><![CDATA['+JSON.stringify(jsonData3)+']]></formFields><mailHeadingText>'+mailHeadingText+'</mailHeadingText><appName>'+localStorage.getItem("AppName")+'</appName></sendCustomMailTemplate></soap:Body></soap:Envelope>';

    console.log(" SEND DATA REQUESTTTTTT" + soapRequest);

    $.ajax({
    type: "POST",
    url: wsUrl,
    contentType: "text/xml",
    dataType: "text",
    data: soapRequest,
    success:sendOnServerSuccess,
    error: sendServerError
    });

    }


    }
}




function sendOnServerSuccess(data, status, req){
 uploadFileCount=0;
	 globalimageurl="";
  setTimeout(function(){$('.appypie-loader').hide();},1000);
 openPopup();
    
    document.getElementById("scuccessPopup").innerHTML=submissionSuccessMsg;
    document.getElementById("scuccessPopup").style.display = 'block';
    document.getElementById("errorPopup").style.display = 'none';
    
    showCustomPage(globalIndex,1);
   
}

function sendServerError(response, textStatus, errorThrown) {
 uploadFileCount=0;
	 globalimageurl="";
//alert("ravi");
  setTimeout(function(){$('.appypie-loader').hide();},1000);
           console.log("Error : " + JSON.stringify(response));
           console.log("Error : " + textStatus);
           console.log("Error : " + errorThrown.responseText);
           }
//Modified by mohsin
/*
var show1 = function(id) {
             
var textbox = document.getElementById(id);
textbox.style.removeProperty('border');
            var myNewDate = Date.parse(document.getElementById(id).value) || new Date();
            localStorage.setItem('dateID',id);
        if(typeof myNewDate === "number"){ myNewDate = new Date (myNewDate); }
            plugins.datePicker.show({
                                    date: myNewDate,
                                    mode: 'date', //date or time or blank for both
                                    allowOldDates: true,
									name:id
                                    }, cb1);
       
}
*/

var show1_id="";

var show1 = function(id) {
		var myNewDate;
		if(id===undefined){
				myNewDate = Date.parse(document.getElementById("txtDate").value) || new Date();
		}else{
			myNewDate = Date.parse(document.getElementById(id).value) || new Date();
			show1_id=id;
		}
            
        if(typeof myNewDate === "number"){ myNewDate = new Date (myNewDate); }
            plugins.datePicker.show({
                                    date: myNewDate,
                                    mode: 'date', //date or time or blank for both
                                    allowOldDates: false
                                    }, cb1);
       
}

var time1 = function(id) {
	var textbox = document.getElementById(id);
	textbox.style.removeProperty('border');
      localStorage.setItem('dateID',id);
                        plugins.datePicker.show({
                                    date: new Date(),
                                    mode:'time', //date or time or blank for both
                                    allowOldDates: true
                                    },timeDispley1);
       
    }
	
	//Modified by mohsin
	var ApointDate="";
    var ApointTime="";
	var dayNameIndex;/*new shedule day index */
    var dateOfAppointment='';/*new shedule appointment date */
	
function cb1(date) {

 var dt = new Date(date);
	dayNameIndex = dt.getDay();/*new Appointment shedule */
	
	if(sessionStorage.getItem("ActualSelectorFor"+show1_id).toUpperCase() == "DATE OF BIRTH")
	 {
		// alert(show1_id);
		 scheduleMonth = '' + (dt.getMonth() + 1),
				    scheduleDay = '' + dt.getDate(),
				    scheduleYear = dt.getFullYear();
				    if (scheduleMonth.length < 2){
				        scheduleMonth = '0' + scheduleMonth;
				    }
				    if (scheduleDay.length < 2) {
				       scheduleDay = '0' + scheduleDay;
				    }
				    scheduleDate = [scheduleMonth, scheduleDay, scheduleYear].join('/');     
				    
				    dateOfAppointment=scheduleDate;
					  if(show1_id=="")
					  //start change by dheeraj
						document.getElementById(show1_id).value = scheduleDate;
					  else
						document.getElementById("txtDate").value = scheduleDate;
						//end change by dheeraj
			          ApointDate = scheduleDate;  
	 }
	 
	 else
	 {
    var todaydate = new Date();
    currentMonth = '' + (todaydate.getMonth() + 1),
    currentDay = '' + todaydate.getDate(),
    currentYear = todaydate.getFullYear();
    if (currentMonth.length < 2){
        currentMonth = '0' + currentMonth;
    }
    if (currentDay.length < 2) {
       currentDay = '0' + currentDay;
    }
    currentDate = [currentMonth, currentDay, currentYear].join('/'); 
    
    scheduleMonth = '' + (dt.getMonth() + 1),
    scheduleDay = '' + dt.getDate(),
    scheduleYear = dt.getFullYear();
    if (scheduleMonth.length < 2){
        scheduleMonth = '0' + scheduleMonth;
    }
    if (scheduleDay.length < 2) {
       scheduleDay = '0' + scheduleDay;
    }
    scheduleDate = [scheduleMonth, scheduleDay, scheduleYear].join('/');     
   // alert(scheduleDate);
    //alert(Date.parse(currentDate));
	if(Date.parse(scheduleDate) >= Date.parse(currentDate) && appointOnOff[dayNameIndex]!=0) {
    	//alert("ravi");
		  dateOfAppointment=scheduleDate;
		  if(show1_id=="")
			document.getElementById("txtDate").value = scheduleDate;
		  else
			document.getElementById(show1_id).value = scheduleDate;
          ApointDate = scheduleDate;  
         
    }
    else
    {
    	
    	var invaliddate_builder = 'Invalid Date';
    	var appointmentdatevalid_builder = 'Appointment date is not valid.';
    	 alertPopUp(invaliddate_builder,appointmentdatevalid_builder);
          if(show1_id=="")
			document.getElementById("txtDate").value = '';
		  else
			document.getElementById(show1_id).value = '';
    }
	
	}
    show1_id=""; 
    sessionStorage.setItem("ActualSelectorFor"+show1_id, "");


  /* ---- old Code ---- */
	/*var dt = new Date(date);
	dayNameIndex = dt.getDay();
	
    var todaydate = new Date();
    currentMonth = '' + (todaydate.getMonth() + 1),
    currentDay = '' + todaydate.getDate(),
    currentYear = todaydate.getFullYear();
    if (currentMonth.length < 2){
        currentMonth = '0' + currentMonth;
    }
    if (currentDay.length < 2) {
       currentDay = '0' + currentDay;
    }
    currentDate = [currentMonth, currentDay, currentYear].join('/'); 
    
    scheduleMonth = '' + (dt.getMonth() + 1),
    scheduleDay = '' + dt.getDate(),
    scheduleYear = dt.getFullYear();
    if (scheduleMonth.length < 2){
        scheduleMonth = '0' + scheduleMonth;
    }
    if (scheduleDay.length < 2) {
       scheduleDay = '0' + scheduleDay;
    }
    scheduleDate = [scheduleMonth, scheduleDay, scheduleYear].join('/');     
   // alert(scheduleDate);
    //alert(Date.parse(currentDate));
	if(Date.parse(scheduleDate) >= Date.parse(currentDate) && appointOnOff[dayNameIndex]!=0) {
    	//alert("ravi");
		  dateOfAppointment=scheduleDate;
		  if(show1_id=="")
			document.getElementById("txtDate").value = scheduleDate;
		  else
			document.getElementById(show1_id).value = scheduleDate;
          ApointDate = scheduleDate;  
         
    }
    else
    {
    	 alertPopUp('Invalid Date','Appointment date is not valid.');
          if(show1_id=="")
			document.getElementById("txtDate").value = '';
		  else
			document.getElementById(show1_id).value = '';
    }
    show1_id=""; */     
 }
	
/*
	function cb1(date) {

	var dt = new Date(date);
    var todaydate = new Date();
    currentMonth = '' + (todaydate.getMonth() + 1),
    currentDay = '' + todaydate.getDate(),
    currentYear = todaydate.getFullYear();
    if (currentMonth.length < 2){
        currentMonth = '0' + currentMonth;
    }
    if (currentDay.length < 2) {
       currentDay = '0' + currentDay;
    }
    currentDate = [currentMonth, currentDay, currentYear].join('/'); 
    
    scheduleMonth = '' + (dt.getMonth() + 1),
    scheduleDay = '' + dt.getDate(),
    scheduleYear = dt.getFullYear();
    if (scheduleMonth.length < 2){
        scheduleMonth = '0' + scheduleMonth;
    }
    if (scheduleDay.length < 2) {
       scheduleDay = '0' + scheduleDay;
    }
    scheduleDate = [scheduleMonth, scheduleDay, scheduleYear].join('/');     
    
	if(scheduleDate.indexOf("NaN")!=-1){
	 document.getElementById(localStorage.getItem('dateID')).value = ''; 
	}else{
	document.getElementById(localStorage.getItem('dateID')).value = scheduleDate;
	}
          
		 
         
    localStorage.setItem('dateID','');
    
 }
 */
    
function timeDispley1(time) {

var timeValue;
if(time!="cancel")
{
//alert(time value>>>"+time);
        var t=time.toString().split(" ");
        //alert(t[0]);
        var t2=t[0].split(":");
        //alert(t2[1]);
        if(t2[1].toString().length==1){
        	
        	t2[1]="0"+t2[1];
        	//alert(t2[1]);
        	 timeValue =t2[0]+":"+t2[1]+" "+t[1]; 
        	 //alert(time)
        }else{
        	
        	timeValue=time;
        	
        }
        
       
       
       /* for(var i=0;i<.length; i++){
            // timeValue = n[3];
        }*/
       
        document.getElementById(localStorage.getItem('dateID')).value = timeValue;
		  localStorage.setItem('dateID','');
        ApointTime=timeValue;
}
}

function validateForm1(x)
{
    var atpos=x.indexOf("@");
    var dotpos=x.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
    {
       
        return false;
    }
    return true;

}

function IsNumeric1(input)
{
//return (input - 0) == input && (input+'').replace(/^\s+|\s+$/g, "").length > 0;
	
	// return /^[a-zA-Z0-9_-]/.test(input); 
	 return (input - 0) == input && (input+'').replace(/^\s+|\s+$/g, "").length > 0;
}

function postAppointmentDataNew()
{
    console.log("postAppointmentDataNew() starts");
    var ApDate=document.getElementById('txtDate').value.trim();
    console.log("ApDate -->"+ApDate);
   
    var ApTime=document.getElementById('txtTime').value.trim();
    console.log("ApTime -->"+ApTime);
  
    var ApName=document.getElementById('txtName').value.trim();
    console.log("ApName -->"+ApName);
    var ApEmail=document.getElementById('txtEmail').value.trim();
    console.log("ApEmail -->"+ApEmail);
    var ApRemark='';
    console.log("ApRemark -->"+ApRemark);
    var ApPhone=document.getElementById('txtPhone').value.trim();
    console.log("ApPhone -->"+ApPhone);
    if(ApDate != '' && ApDate != null) {
        if(ApTime != '' && ApTime != null) {
            if(ApName != '' && ApName != null) {
                if(ApEmail != '' && ApEmail != null && validateForm1(ApEmail)) {
                    if(ApPhone != '' && ApPhone != null && IsNumeric1(ApPhone) && ApPhone.length >= 8) {
                       
					    validateCustomAppointmentElements(ApDate,ApTime,ApName,ApEmail,ApPhone,ApRemark);
                    }
                    else {
                        openPopup();
                        document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                        document.getElementById("errorPopup").style.display = 'block';
                        document.getElementById("scuccessPopup").style.display = 'none';
                        setFocus('txtPhone');
                    }
                }
                else {
                    openPopup();
                    document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                    document.getElementById("errorPopup").style.display = 'block';
                    document.getElementById("scuccessPopup").style.display = 'none';
                     setFocus('txtEmail');
                }
            }
            else {
                openPopup();
                document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
                document.getElementById("errorPopup").style.display = 'block';
                document.getElementById("scuccessPopup").style.display = 'none';
                 setFocus('txtName');
            }
        }
        else {
            openPopup();
            document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
            document.getElementById("errorPopup").style.display = 'block';
            document.getElementById("scuccessPopup").style.display = 'none';
            setFocus('txtTime');
        }
    }
    else {
        openPopup();
        document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
        document.getElementById("errorPopup").style.display = 'block';
        document.getElementById("scuccessPopup").style.display = 'none';
         setFocus('txtDate');
    }
    
    
}

var len=0;
/*custom appointment changes */
function validateCustomAppointmentElements(ApointDate,ApointTime,ApName,ApEmail,ApPhone,ApRemark)
{
	

   if( AppointCustomElementsLabel.length > 0)
    	
   {
   	
	   len=AppointCustomElementsType.length;
       for(var i=0;i<len;i++){
       if(AppointCustomElementsType[i]=="name" || AppointCustomElementsType[i]=="email" || AppointCustomElementsType[i]=="phone" || AppointCustomElementsType[i]=="state" || AppointCustomElementsType[i]=="text"){
   	 
   	 var txt=document.getElementById(i).value.trim();
   	
   	 if (txt == '' && AppointCustomElementsMandatory[i]==1) {
   	 
   		 alertPopupforRegister1(AppointCustomElementsLabel[i]);
   
   		 setFocus(i);
   		return false;
      }
   	 else if(AppointCustomElementsType[i]=="phone")
   	   {
   		 
   	   
   	   		if(!IsNumeric1(txt))
   	   			{
   	   			
   	   			navigator.notification.alert(
   	   		            '*'+AppointCustomElementsLabel[i]+ notvalid_builder,
   	   		            alertDismissed,
   	   		      alert_builder,
   	   		ok_builder
   	   		            );
   	   		
                 setFocus(i);
                 return false;
   	   			}
   	   	
   	   
   	   }
   	 
   	 else if(AppointCustomElementsType[i]=="email")
   	 {
  		
   	   
   	   if(!validateForm1(txt))
   			{
   		   
   		   		navigator.notification.alert(
	   		            '*'+AppointCustomElementsLabel[i]+notvalidemail_builder,
	   		            alertDismissed,
	   		         alert_builder,
	   		      ok_builder
	   		            );
   		 
   			setFocus(i);
   			return false;
   			}
   	   else{
   		   
   		   userEmail=txt;
   	   		}
   	   
   	 
   	 }
   		
   	  AppointCustomElementsValue[i]=txt;
   	}
   	 else if(AppointCustomElementsType[i]=="date"){
   	 
   	  var txt=document.getElementById(i).value;
   	 
   	 if (txt == '' && AppointCustomElementsMandatory[i]==1) {
   		 alertPopupforRegister1(AppointCustomElementsLabel[i]);
   		 			
                        setFocus(i);
   		return false;
      }
   	   AppointCustomElementsValue[i]=txt;
   	   
   	   }
   	    else if(AppointCustomElementsType[i]=="time"){
   	 
   	  var txt=document.getElementById(i).value.trim();
   	  
   	 if (txt == '' && AppointCustomElementsMandatory[i]==1) {
   		 alertPopupforRegister1(AppointCustomElementsLabel[i]);
   		 			
                        setFocus(i);
   		return false;
      }
   	  AppointCustomElementsValue[i]=txt;
   	   
   	   }
   	      else if(AppointCustomElementsType[i]=="textarea"){
   	 
   	  var txt=document.getElementById(i).value.trim();
   	   
   	 if (txt == '' && AppointCustomElementsMandatory[i]==1) {
   		 alertPopupforRegister1(AppointCustomElementsLabel[i]);
   		
                        setFocus(i);
   		return false;
      }
   	 AppointCustomElementsValue[i]=txt;
   	   
   	   }
   	    else if(AppointCustomElementsType[i]=="gender"){
   	 
   	  var txt=document.getElementById(i).value.trim();
   	 
   	 if (txt == 'Select Gender' && AppointCustomElementsMandatory[i]==1) {
   		 alertPopupforRegister1(AppointCustomElementsLabel[i]);
   		 		
                        setFocus(i);
   		return false;
      }
   	  AppointCustomElementsValue[i]=txt;
   	   
   	   }
   	    else if(AppointCustomElementsType[i]=="country"){
   	 
   	  var txt=document.getElementById(i).value.trim();
   	 //alert(txt);
   	 if (txt == 'Select Country' && AppointCustomElementsMandatory[i]==1) {
   		 alertPopupforRegister1(AppointCustomElementsLabel[i]);
   		 			
                        setFocus(i);
   		return false;
      }
   	 
   	    AppointCustomElementsValue[i]=txt;
   	   }
   	   else if(AppointCustomElementsType[i]=="checkbox"){
   	 
   	  var favorite = [];
   	
   	  var tempNam=AppointCustomElementsLabel[i];
   	 
              $.each($("input[name="+ tempNam.replace(/[^A-Za-z0-9]+/g, 'X')+"]:checked"), function(){  
           	 
                  favorite.push($(this).val());
                  
              });
             // alert("My favourite sports are: " + favorite.join(", "));
   		 
   		   if(favorite.length == 0 && AppointCustomElementsMandatory[i]==1){
   			   alertPopupforRegister1(AppointCustomElementsLabel[i]);
   			   		
   		return false;
   		   
   		   }
   	       AppointCustomElementsValue[i]=favorite.join(",");
   	 
   	   
   	   }  else if(AppointCustomElementsType[i]=="radio"){
   	 
   	  var favorite = [];
   	  var tempNam=AppointCustomElementsLabel[i];
              $.each($("input[name="+tempNam.replace(/[^A-Za-z0-9]+/g, 'X')+"]:checked"), function(){            
                  favorite.push($(this).val());
              });
   			
   			  if(favorite.length == 0 && AppointCustomElementsMandatory[i]==1){
   				  alertPopupforRegister1(AppointCustomElementsLabel[i]);
   				 
   		return false;
   		   
   		   }
   			 AppointCustomElementsValue[i]=favorite[0];
            
   	
   	 
   	   
   	   }
   	   else if(AppointCustomElementsType[i]=="select"){
   	 
   	 
   	var e = document.getElementById(i);
      // var txt = e.options[e.selectedIndex].text;
   	var txt = e.options[e.selectedIndex].value.trim();
   	  if (txt == '' && AppointCustomElementsMandatory[i]==1) {
   		  alertPopupforRegister1(AppointCustomElementsLabel[i]);
   		  				
                        setFocus(i);
   		return false;
      }
   	  AppointCustomElementsValue[i]=txt;
   	
   	
   	 
   	   
   	   }
   	 else if(AppointCustomElementsType[i]=="multiselect"){
   	   	 
   		var txt = '';
   		$('#'+i+' :selected').each(function(i, selected){ 
   			txt= txt+$(selected).text()+","; 
   		});
   		
   		txt=txt.substring(0, txt.length-1);
     
   	   	
   	   	  if (txt == '' && AppointCustomElementsMandatory[i]==1) {
   	   		  alertPopupforRegister1(AppointCustomElementsLabel[i]);
   	   		  				
   	          setFocus(i);
   	   		return false;
   	      }
   	   	  AppointCustomElementsValue[i]=txt;
   	   	
   	   	
   	   	 
   	   	   
   	   	   }
       
       
       
   	   else if(AppointCustomElementsType[i]=="uploadPicture"){
   		   var txt=document.getElementById(i).innerHTML.trim();
   		   
   		   if (txt == 'Attach File' && AppointCustomElementsMandatory[i]==1) {
   			   alertPopupforRegister1(AppointCustomElementsLabel[i]);
   			      
                        setFocus(i);
   					return false;
   			   }
   		   
   		   if(txt == 'Attach File')
   			   {
   			   
   			   AppointCustomElementsValue[i]='';
   			   }
   		   else{
   			   AppointCustomElementsValue[i]=txt;
   			   
   		   }
   		   
   		   
   	   }
   	   
   }

   }
   
   var jsonData = {};
   var jsonData2 = {};
   var jsonData3={};
   for(var k=0;k<len;k++)
   {
   	if(typeof AppointCustomElementsLabel[k] =='undefined')
	 	{
	 		
	 		
	 	}
	 	else{
	 		
	 		jsonData3[k]=AppointCustomElementsType[k];
	 		jsonData2[k]=AppointCustomElementsLabel[k].replace('&','and');
	 		jsonData[k] = AppointCustomElementsValue[k];
	 		
	 	
	 		
	 		
	 	}
   }
	
	var mediaFiles = globalimageurl;
	fileName = Math.random()+'_'+globalimageurl.split("/").pop();
	var fileURI = mediaFiles;
	$('.appypie-loader').show();
	
	var ownerMail;
	var mailSubject;
	
	var token=window.toaster.getDeviceToken();
	var deviceUUID=window.toaster.getDeviceID();
	$formBuilderIndex.find("appointment").each(function()
						  {
						         ownerMail = $(this).find("appointmentEmail").text().trim();
							     mailSubject = $(this).find("appointmentSubject").text().trim();
					});
	
	if(uploadFileCount>0)
	{
	   console.log("10-->");
	   console.log("ApointTime>>>>>>"+ApointTime);
	   window.toaster.uploadMultipleFilesAppointment(localStorage.getItem('applicationID'),formbuilderPageId,
	    mailSubject,ownerMail,ApointDate, ApointTime,ApName,ApEmail,ApPhone,ApRemark,'1','Android',deviceUUID,token,JSON.stringify(jsonData),JSON.stringify(jsonData2),JSON.stringify(jsonData3),uploadFileCount,globalExactPath);
	   
	}
	else
	{
		var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/form-builder-soap#sendAppointment";
		 var soapRequest ='<?xml version="1.0" encoding="utf-8"?><soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><sendAppointment xmlns=\"http://'+localStorage.getItem("reseller")+'/services/form-builder-soap#sendAppointment\"><appId>'+window.localStorage.getItem("applicationID")+'</appId><formPageId>'+formbuilderPageId+'</formPageId><subject><![CDATA['+mailSubject+']]></subject><ownerEmail>'+ownerMail+'</ownerEmail><appointmentDate>'+ApointDate+'</appointmentDate><appointmentTime>'+ApointTime+'</appointmentTime><userName><![CDATA['+ApName+']]></userName><userEmail>'+ApEmail+'</userEmail><userPhone>'+ApPhone+'</userPhone><userRemark><![CDATA['+ApRemark+']]></userRemark><status>1</status><userDeviceType>Android</userDeviceType><userDeviceId>'+deviceUUID+'</userDeviceId><userDeviceToken>'+token+'</userDeviceToken><formData><![CDATA['+JSON.stringify(jsonData)+']]></formData><formLabel><![CDATA['+JSON.stringify(jsonData2)+']]></formLabel><formFields><![CDATA['+JSON.stringify(jsonData3)+']]></formFields><appName>'+localStorage.getItem("AppName")+'</appName><appOwmerEmail>'+localStorage.getItem("owneremail")+'</appOwmerEmail><appOwmerName>'+localStorage.getItem("ownerName")+'</appOwmerName></sendAppointment></soap:Body></soap:Envelope>';
		                
		                console.log(" SEND APPOINTMENT DATA REQUESTTTTTT" + soapRequest);
		                
		                $.ajax({
		                       type: "POST",
		                       url: wsUrl,
		                       contentType: "text/xml",
		                       dataType: "text",
		                       data: soapRequest,
		                       success:appointementDataSendSuccess,
		                       error: appointementDataSendFail
		                       });
		

	}


}

/*custom appointment changes */
function alertPopupforRegister1(labelDynamic)
{
	 openPopup();
	 document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
     document.getElementById("errorPopup").style.display = 'block';
     document.getElementById("scuccessPopup").style.display = 'none';
     setFocus(i);
}


/*function appointmentServiceCallNew(ApointDate,ApointTime,ApName,ApEmail,ApPhone,ApRemark)
{

var ownerMail;
var mailSubject;
var token=window.toaster.getDeviceToken();
var deviceUUID=window.toaster.getDeviceID();
$formBuilderIndex.find("appointment").each(function()
					  {
					         ownerMail = $(this).find("appointmentEmail").text().trim();
						     mailSubject = $(this).find("appointmentSubject").text().trim();
				});

var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/form-builder-soap#sendAppointment";
 var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><sendAppointment xmlns=\"http://'+localStorage.getItem("reseller")+'/services/form-builder-soap#sendAppointment\"><appId>'+window.localStorage.getItem("applicationID")+'</appId><formPageId>'+formbuilderPageId+'</formPageId><subject><![CDATA['+mailSubject+']]></subject><ownerEmail>'+ownerMail+'</ownerEmail><appointmentDate>'+ApointDate+'</appointmentDate><appointmentTime>'+ApointTime+'</appointmentTime><userName><![CDATA['+ApName+']]></userName><userEmail>'+ApEmail+'</userEmail><userPhone>'+ApPhone+'</userPhone><userRemark><![CDATA['+ApRemark+']]></userRemark><status>1</status><userDeviceType>Android</userDeviceType><userDeviceId>'+deviceUUID+'</userDeviceId><userDeviceToken>'+token+'</userDeviceToken></sendAppointment></soap:Body></soap:Envelope>';
                
                console.log(" SEND APPOINTMENT DATA REQUESTTTTTT" + soapRequest);
                
                $.ajax({
                       type: "POST",
                       url: wsUrl,
                       contentType: "text/xml",
                       dataType: "text",
                       data: soapRequest,
                       success:appointementDataSendSuccess,
                       error: appointementDataSendFail
                       });
}	*/

function appointementDataSendSuccess(data, status, req){
//$('.appypie-loader').show();
    setTimeout(function(){$('.appypie-loader').hide();},1000);
    openPopup();
    document.getElementById("scuccessPopup").innerHTML=submissionSuccessMsg;
    document.getElementById("scuccessPopup").style.display = 'block';
    document.getElementById("errorPopup").style.display = 'none';
    showAppointmentPage();
}  
function appointementDataSendFail(response, textStatus, errorThrown) {
//alert("ravi");$('.appypie-loader').show();
    setTimeout(function(){$('.appypie-loader').hide();},1000);
           console.log("Error : " + JSON.stringify(response));
           console.log("Error : " + textStatus);
           console.log("Error : " + errorThrown.responseText);
           }
		   
function sendQuoteData(){

var ownerMail;
var mailSubject;
$formBuilderIndex.find("quote").each(function()
					  {
					         ownerMail = $(this).find("email").text();
						     mailSubject = $(this).find("subject").text();
				});

    //$quoteIndex = $(masterData).find("quote[indexval="+quoteIndex+"]" );
    var emailId = ownerMail.trim();
    var subject = mailSubject.trim();
	var username =document.getElementById("quotename").value.trim();
    var useremailid =document.getElementById("quoteemail").value.trim();
    var userphonenumber =document.getElementById("quotephone").value.trim();
    var regexObj = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
    var usercomments =document.getElementById("quotecomment").value.trim();
    if(username != "" && username != null)
    {
        if(useremailid != "" && useremailid !=null && checkEmail(useremailid))
        {
            if(!checkNetworkConnection()){
            }
            else{
			$('.appypie-loader').show();
                sendFormBuilderQuoteMail(username,useremailid,userphonenumber,subject,usercomments,emailId);
            }
        }
        else {

		
   
            openPopup();
            document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
            document.getElementById("errorPopup").style.display = 'block';
            document.getElementById("scuccessPopup").style.display = 'none';
        }
    }
    else {
	
        openPopup();
        document.getElementById("errorPopup").innerHTML=submissionErrorMsg;
        document.getElementById("errorPopup").style.display = 'block';
        document.getElementById("scuccessPopup").style.display = 'none';
    }
    

}

function  sendFormBuilderQuoteMail(username,useremailid,userphonenumber,subject,usercomments,emailId){

var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/form-builder-soap#sendSubQuoteMailTemplate";
var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><sendSubQuoteMailTemplate xmlns=\"http://'+localStorage.getItem("reseller")+'/services/form-builder-soap#sendSubQuoteMailTemplate\"><appId>'+window.localStorage.getItem("applicationID")+'</appId><name>'+username+'</name><emailId>'+useremailid+'</emailId><phoneNo>'+userphonenumber+'</phoneNo><subject><![CDATA['+subject+']]></subject><comments><![CDATA['+usercomments+']]></comments><ownerEmail>'+emailId+'</ownerEmail><quoteLabel><![CDATA['+requestJson+']]></quoteLabel><appName>'+localStorage.getItem("AppName")+'</appName></sendSubQuoteMailTemplate></soap:Body></soap:Envelope>';
                
                console.log(" SEND Quote DATA REQUESTTTTTT" + soapRequest);
                
                $.ajax({
                       type: "POST",
                       url: wsUrl,
                       contentType: "text/xml",
                       dataType: "text",
                       data: soapRequest,
                       success:quoteDataSendSuccess,
                       error: quoteDataSendFail
                       });


}	
function quoteDataSendSuccess(data, status, req){

	
	document.getElementById("quotename").value='';
    document.getElementById("quoteemail").value='';
    document.getElementById("quotephone").value='';
   
    document.getElementById("quotecomment").value='';
	
    setTimeout(function(){$('.appypie-loader').hide();},1000);
    openPopup();
    document.getElementById("scuccessPopup").innerHTML=submissionSuccessMsg;
    document.getElementById("scuccessPopup").style.display = 'block';
    document.getElementById("errorPopup").style.display = 'none';
}  
function quoteDataSendFail(response, textStatus, errorThrown) {
       setTimeout(function(){$('.appypie-loader').hide();},1000);
           console.log("Error : " + JSON.stringify(response));
           console.log("Error : " + textStatus);
           console.log("Error : " + errorThrown.responseText);
           }	   

function selectFile(object) {
	currentSelectedId=object.id;
	var textbox = document.getElementById(object.id);
	textbox.style.removeProperty('border');
	 toaster.customFormBuilderGetfile(111);
}

function setFilePath(fileNAmeCustum) {
	
var fileSizeInKb= toaster.getFileSize(fileNAmeCustum);

 if(fileSizeInKb > 8192){
	 
	 
	 navigator.notification.alert(
			 attachmensizeexceed_builder,
     alertDismissed,
     alert_builder,
     ok_builder
     );
     
	 return false;
 }
 else{
	  globalExactPath=globalExactPath+fileNAmeCustum+",";
 }
 
	globalimageurl=fileNAmeCustum;
	var fileName=globalimageurl.split("/").pop();
	
	document.getElementById(currentSelectedId).innerHTML = fileName;
}
