//////////////////////////////Text Page//////////////////////////

var imageSavedUrl="";
var updateAvailable='';
var notificationDateArray;
var notificationNameArray;
var notificationUrlArray="" ,notificationImageArray="";
var notificationIdArray="";

function checkServerUpdate(){
    $('.appypie-loader').show();
	//console.log('checkUpdate');
    //sessionStorage.setItem('fromRefreshPage',true);
   // $("#mainback").show();
    //$("#logo").hide();
    //$("#mainback").show();
    //$("#reload").hide();
	notificationNameArray=new Array();
	notificationDateArray=new Array();
	notificationUrlArray= new Array();
	notificationImageArray = new Array();
	notificationIdArray= new Array();
	//$("#shareButton").show();
	
   // window.location="removeWebSite:";
	//var wsUrl = "http://"+window.localStorage.getItem("reseller")+"/services/angular-soap#appXml";
	//var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><appXml xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/angular-soap#appXml/\"><appId>'+window.localStorage.getItem("applicationID")+'</appId><prevVersion>'+window.localStorage.getItem("appVersion")+'</prevVersion></appXml></soap:Body></soap:Envelope>';
	var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/angular-soap#autoUpdateAppXml";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><autoUpdateAppXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/angular-soap#autoUpdateAppXml/\"><appId>'+localStorage.getItem("applicationID")+'</appId><prevVersion>'+localStorage.getItem("appVersion")+'</prevVersion></autoUpdateAppXml></soap:Body></soap:Envelope>';

	
	console.log("kkkSaopr "+soapRequest);
	jQuery.support.cors = true;
	$.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           cache:false,
           success: function(data, status, req)
           {
           var xmlret=$.parseXML(req.responseText);
           
           var strJSON = $(xmlret).find("return").text();
		   console.log("kkkstrJSONr---->"+strJSON);
		   sessionStorage.setItem("updatedXML",strJSON);
          
		   if(strJSON=="No update available")
           {
		   sessionStorage.setItem("updatedXML",strJSON);
           }
		   else if(strJSON.indexOf('.xml') != -1)
		   {
		   var urlOfXml=strJSON.split('??');
           if(urlOfXml[0]!=null && urlOfXml[0].length > 4)
           {
           sessionStorage.setItem("updatedXML",urlOfXml[0]);
           }
		   localStorage.setItem('autoUpdate',urlOfXml[1]);
		   }
		   
           //var updateXmlHtml='';
           
		   if(toaster.returnNotificationCount() !=0)
            {
               updateAvailable="true";
               $('#supValueChange').show();
               $('#supValueChange').empty();
               var count=toaster.returnNotificationCount();
               //alert("pushandrefresy"+count);
               $('#supValueChange').append(toaster.returnNotificationCount());
            }
           else if(strJSON.indexOf('.xml') != -1 && urlOfXml[1]=="true")
           {
		   updateAvailable="true";
           $('#supValueChange').show();
           $('#supValueChange').empty();
           $('#supValueChange').append('1');
           }
		   else{
		    updateAvailable="false";
		$('#supValueChange').empty();
          // $("#reload").removeClass("active");
           $('#supValueChange').hide();
           //updateXmlHtml='<div class="service-page">No updates are available</div>';
           }
           
           var wsUrl1="http://"+localStorage.getItem("reseller")+"/services/utility-soap#getNotification";
           var soapRequest1 ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getNotification xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/utility-soap#getNotification/\"><appId>'+localStorage.getItem("applicationID")+'</appId><type>Android</type></getNotification></soap:Body></soap:Envelope>';
           console.log("Saop >>>>>>>>"+soapRequest);
           jQuery.support.cors = true;
           $.ajax({
                  type: "POST",
                  url: wsUrl1,
                  contentType: "text/xml",
                  dataType: "text",
                  data: soapRequest1,
                  success: function(data, status, req)
                  {
			     // alert(""+req.responseText);
				 //var pushHtml='';
				  var pushJSON = $(req.responseText).find("return").text();
				  if(pushJSON.indexOf("No Notification")!=-1)
				  {
				  // pushHtml="No notifications found";
				  }
				  else{
			// alert("json"+pushJSON);
                  var obj = jQuery.parseJSON(pushJSON);
                  //alert("obj"+obj);
                  console.log('xmlret>>>>>>>>----->'+pushJSON);
                  
               //   var totalPush=obj.notification;
                 // console.log('totalPush---->'+totalPush);
                 // console.log('totalPush---->'+totalPush.length);
                // alert("total notification"+obj.length);
                  for(x=0;x<obj.length;x++)
                  {
                                                   
                                                                                        
                                                        var summary=obj[x].notification;
                                                        
														if(summary.indexOf(',')!=-1)
                                                          {
														   var find = ',';
                                                           var re = new RegExp(find, 'g');
                                                          summary = summary.replace(re, "$1234$");
														
                                                          }
														
                                               notificationNameArray.push(summary);
                                               var  url = obj[x].url;
                                               var image = obj[x].image;
                                                var id=obj[x].id;
                                               notificationUrlArray.push(url);
                                               notificationImageArray.push(image);
                                               notificationIdArray.push(id);   
                                                                               
                                                        var date=obj[x].date;
                                               notificationDateArray.push(date);
                                                      //  pushHtml+= '<div class="service-page">'+ summary +'<br/>Date:- '+date+'</div>';
                                                   console.log('summary---->'+summary);
                  }
				  }
				  $('.appypie-loader').hide();
				  var sharemessage="";
				  if(window.localStorage.getItem("appShareMessage"))
				  {
				  sharemessage=""+window.localStorage.getItem("appShareMessage");
				  }
				  else
				  {
				  sharemessage="";
				  }
				  var shareStr="refreshandnot:" +notificationNameArray.toString()+"%%% "+notificationDateArray.toString()+"%%% "+updateAvailable+"%%% "+sharemessage+"%%% "+localStorage.getItem('autoUpdate')+"%%% "+localStorage.getItem('profileName')+"%%% "+localStorage.getItem('fooduserid')+"%%% "+localStorage.getItem('profileNumber')+"%%% "+notificationUrlArray.toString()+"%%% "+notificationImageArray.toString()+"%%% "+notificationIdArray.toString();
				  //+"%%% "+notificationUrlArray.toString()+"%%% "+notificationImageArray.toString()
				  //window.location="refreshandnot:" +notificationNameArray.toString()+"%%%"+notificationDateArray.toString()+"%%%"+updateAvailable+"%%%"+shareStr;
				  console.log("shareStr===>>>"+shareStr);
				  toaster.rateAndShare(shareStr);
				  
                  //$("#contentHolder").empty();
                  //sessionStorage.setItem('notificationPage','true');
                  /*setTimeout(function(){
                             $("#contentHolder").append("<div class='page-text'><h2>Updates</h2>"+updateXmlHtml+'<h2>Notifications</h2>'+pushHtml+"</div>");
                             $.mobile.changePage('#page2', { transition: 'slidefade',allowSamePageTransition: false});
                             $('.appypie-loader').show();
                              $('.appypie-loader').hide();},1000);
							  */
                  },
                  error: function(response, textStatus, errorThrown)
                  {
                  console.log(errorThrown.responseText);
                  
                  setTimeout(function(){$('.appypie-loader').hide();},300);
                  }
                  });
           
           
           /*
           $("#contentHolder").empty();
           
           setTimeout(function(){
                      $("#contentHolder").append("<div class='page-text'>"+updateXmlHtml+"</div>");
                      $.mobile.changePage('#page2', { transition: 'slidefade',allowSamePageTransition: false});
                      $('.appypie-loader').hide();},1000);
           */
           
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(errorThrown.responseText);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           
           //getStarted();
           }
           });
    
}

function supValueFunc()
{
    $('#supValueChange').hide();
}
