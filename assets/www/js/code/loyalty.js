/////////////////LoyaltyCard////////////////////////
 var oops_loyalty = 'Oops';
var serverresponding_loyalty = 'Server not responding\n Please try again later';
var alert_loyalty = 'Alert!!';
var cardalreadyredeemed_loyalty = 'The card is already redeemed';
var error_loyalty = 'Error';
var pleaseuseyourfreebiestamp_loyalty = 'Please use your freebie stamp';
var typingecuritycode_loyalty = "Validate by typing the Security code";
var validatescanningcode_loyalty = "Validate by scanning the QR Code";
var congrats_loyalty = "Congrats!";
var networkproblem_loyalty = 'Network Problem';
var internetnotavailable_loyalty = 'Internet not available';
var invalidcode_loyalty = 'Invalid Code';
var pleaseentervalidcode_loyalty = 'Please enter a valid code';



function startLoyaltyParsing(index)
{
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    $loyaltyDataIndex = $(masterData).find("loyalty[indexval="+index+"]" );
    var loyaltyHeading=$loyaltyDataIndex.find('loyaltyHeading').text();
    var totalNoOfCards=$loyaltyDataIndex.find('loyaltyHeading').text();
    var loyaltyCardHtml='<div class="page-text"><div class="loyaltyCard-wrapper"><ul class="loyaltyCrad-view">';
    var i=0;
    var loyaltycardUniqueId=$loyaltyDataIndex.find('loyaltycardUniqueId').text();
    
    sessionStorage.setItem("loyaltycardUniqueId",loyaltycardUniqueId);
    
    $loyaltyDataIndex.find('loyaltyCards').each(function()
                                                {
                                                var card_visibility=$(this).find("card_visibility").text();
                                                var noOfStampsInLoyalty=$(this).find("card_no_of_stamps").text();
                                                var cardStampIconUrl=$(this).find("cardstampicon").text();
                                                var finalFreebieOfferText=$(this).find("finalfreebietext").text();
                                                var finalFreebieCardIconUrl=$(this).find("freesloticon").text();
                                                var totalNoOFCardsNeedToUsedToGetFreebie=$(this).find("ulfreebieslot_count").text();
                                                var CardSlotValue=$(this).find("freebie_slot").text();
                                                var CardNameValue=$(this).find("freebie_name").text();
                                                var midCardIcon=$(this).find("midcardicon").text();
                                                var mainCardNameValue=$(this).find("fld_card_name").text();
                                                var CardDescpritpionValue=$(this).find("fld_brief_desc").text();
                                                var validityDisp=$loyaltyDataIndex.find("loyaltyvalidtill").text();
                                                if(CardDescpritpionValue!=null && CardDescpritpionValue.length>40)
                                                {
                                                 CardDescpritpionValue=CardDescpritpionValue.substring(0,40);
                                                 CardDescpritpionValue=CardDescpritpionValue+'...';
                                                }
                                                var cardDailyLimitValue=$(this).find("carddailylimit").text();
                                                var loyalty_valid_from=$(this).find("loyalty_valid_from").text();
                                                var loyalty_valid_to=$(this).find("loyalty_valid_to").text();
                                                var imgcardheader_saveimage=$(this).find("imgcardheader_saveimage").text();
                                                var imgcardbackground_saveimage=$(this).find("imgcardbackground_saveimage").text();
                                                
                                                 var carddesclaimer=$(this).find("carddesclaimer").text();
                                                
                                                var carddesclaimerText=$loyaltyDataIndex.find("loyaltytc").text();
                                                //parseInt
                                                if(carddesclaimer==null || carddesclaimer.length==0 || carddesclaimer==" ")
                                                {
                                                 carddesclaimer="";
                                                }
                                                
                                                //vilidate date not
                                                if(loyalty_valid_to==null || loyalty_valid_to.length==0 || loyalty_valid_to.trim()=="")
                                                {
                                                loyalty_valid_to="<br/>";
                                                validityDisp='<br/>';
                                                }
                                                else
                                                {
                                                 var validToSplit=loyalty_valid_to.split('/');
                                                loyalty_valid_to= validToSplit[0]+'-'+ getMonthLoyalty(validToSplit[1])+'-'+ validToSplit[2];
                                                }
                                                
                                               var validFromSplit=loyalty_valid_from.split('/');
                                               
                                                 console.log("loyalty page ---> loop"+i);
                                                if(card_visibility==1)
                                                {
                                                
                                                 var divTempName='divTemp'+i;
                                                
                                                if(imgcardheader_saveimage.length > 1 || imgcardbackground_saveimage.length > 1)
                                                {
                                               
                                                console.log("From Background Image");
                                                loyaltyCardHtml+='<li class="pdBottom"><div class="eventVanue loyaltyVanue" id="loayality_inner_date_1" onclick="openLoyaltyInnerPage(' + i +','+ index +')"><span class="hanger"></span><div class="eventTime"><span class="month">'+getMonthLoyalty(validFromSplit[1],'full')+'</span><span class="day">'+validFromSplit[0]+'</span><span class="year">'+validFromSplit[2]+'</span>\
                                                <span class="expire-day">'+validityDisp+'</span>\
                                                <span class="date">'+loyalty_valid_to+'</span>\
                                                </div>\
                                                </div>\
                                                <div class="card-title" onclick="openLoyaltyInnerPage(' + i +','+ index +')"> '+mainCardNameValue+' </div>\
                                                <div class="card-discription" onclick="openLoyaltyInnerPage(' + i +','+ index +')"><p>'+CardDescpritpionValue+'</p>\
                                                </div>\
                                                <div class="main_banner">\
                                                <ul onclick="openLoyaltyInnerPage(' + i +','+ index +')">\
                                                <li>\
                                                <div class="bg-img">\
                                                <img  src="'+imgcardbackground_saveimage+'" style="display:block;">\
                                                </div>\
                                                <div class="image">\
                                                <div class="loyalty-thump">\
                                                <img src="'+imgcardheader_saveimage+'" style="display:block;">\
                                                </div></div></li><li class="mainOfferTitle "><span style="background-image:url('+finalFreebieCardIconUrl+');" class="'+finalFreebieCardIconUrl+' loyalbox  freeicon-btn">&nbsp;</span></li></ul>\
                                                </div><a onclick="onLoylityInfoClick(' + i +')" class="loyalty-info" name="1"><img id="showDesclaimer" src="images/coupon-deatils.png"></a>\
                                                <div id='+divTempName+' class="disclaimer"><div class="cardscroller" >'+carddesclaimerText+'</br>'+carddesclaimer+'</br></br></div></div></li>';
                                                }
                                                else
                                                {
                                                console.log("From WithOUT Background Image");
                                                loyaltyCardHtml+='<li id="cardlist_1" name="1" style="cursor: pointer;display:block;"><div class="eventVanue loyaltyVanue" id="loayality_inner_date_1" onclick="openLoyaltyInnerPage(' + i +','+ index +')"><span class="hanger"></span><div class="eventTime"><span class="month">'+getMonthLoyalty(validFromSplit[1],'full')+'</span><span class="day">'+validFromSplit[0]+'</span><span class="year">'+validFromSplit[2]+'</span>\
                                                <span class="expire-day">'+validityDisp+'</span>\
                                                <span class="date">'+loyalty_valid_to+'</span>\
                                                </div>\
                                                </div>\
                                                <div class="card-title" onclick="openLoyaltyInnerPage(' + i +','+ index +')"> '+mainCardNameValue+' </div>\
                                                <div class="card-discription" onclick="openLoyaltyInnerPage(' + i +','+ index +')"><p>'+CardDescpritpionValue+'</p>\
                                                </div>\
                                                <div class="main_banner">\
                                                <ul>\
                                                <li class="mainOfferTitle single-li"  onclick="openLoyaltyInnerPage(' + i +','+ index +')"><span style="background-image:url('+finalFreebieCardIconUrl+');" class="'+finalFreebieCardIconUrl+' loyalbox freeicon-btn ">&nbsp;</span></li>\
                                                <a onclick="onLoylityInfoClick(' + i +')" class="loyalty-info"><img id="showDesclaimer" src="images/coupon-deatils.png"></a>\
                                                <div id='+divTempName+'  class="disclaimer"><div class="cardscroller" >'+carddesclaimerText+'</br>'+carddesclaimer+' </br></br></div></div>\
                                                </ul>\
                                                \
                                                </div></li>';
                                                }
                                                }
                                                
                                                i++;
                                                
                                                });
    
    
    
    loyaltyCardHtml+='</ul></div></div></div>';
    appendHtml(loyaltyCardHtml,'',1);
}


function getMonthLoyalty(value,usageFor)
{
    if(value)
    {
    console.log('value --->'+value);
    var month = new Array();
    month[0] = "January";
    month[1] = "February";
    month[2] = "March";
    month[3] = "April";
    month[4] = "May";
    month[5] = "June";
    month[6] = "July";
    month[7] = "August";
    month[8] = "September";
    month[9] = "October";
    month[10] = "November";
    month[11] = "December";
    value--;
    if(usageFor == 'full')
    {
    return month[value].toUpperCase();
    }
    else
    {
      return month[value].substring(0,3);
    }
    }
    else
    {
        return ' ';
    }
}

function onLoylityInfoClick(i)
{
    var divTempName='divTemp'+i;
    var cName=document.getElementById(divTempName);
    if(cName.style.top=="0px")
    {
        cName.style.top="";
    }
    else
    {
        cName.style.top="0px";
    }
}




function openLoyaltyInnerPage(i,index,isfromCardValidation)
{
    $(".appypie-loader").show();
    
        var iIndex=i;
        var indexIndex=index;
        var fromCardValidation
        var reqType="";
       // var DeviceID=localStorage.getItem("UDID");
		  var DeviceID=localStorage.getItem("deviceUUID");
        if(DeviceID == null)
        {
            DeviceID ='1287531689';
        }
    
    $loyaltyDataIndex = $(masterData).find("loyalty[indexval="+index+"]" );
    
    var j=0;
    var pageId="loyalty_1445853560677_44";
    var cardNum="";
    var total="";
    var cardType="";
    var pageId= $(masterData).find("home").find("pageid").text();
    var pageIdBeacon = $(masterData).find("home").find("pageIdentifierBecon").text();
    
    if (pageId.indexOf(',') > -1)
    {
          var pageIdArray=pageId.split(',');
          var pageIdBeaconArray=pageIdBeacon.split(',');
        for(var i=0;i<pageIdArray.length;i++)
        {
            if(pageIdArray[i]=="loyalty")
            {
                pageId=pageIdBeaconArray[i];
                break;
            }
        }
    }
   else
   {
       pageId=pageIdBeacon;
   }

       $loyaltyDataIndex.find('loyaltyCards').each(function()
                                                {
                                                    if(iIndex==j)
                                                    {

                                                    total=$(this).find("card_no_of_stamps").text();
                                                    cardType=$(this).find("carduseslimit").text();
                                                    cardNum=$(this).find("cardno").text();
                                                    }
                                                    j++;
                                                });

        var total1=parseInt(total)+1;
        total=total1;
    
        if(isfromCardValidation=="1"||isfromCardValidation=="2")
        {
            reqType="add";
        }
        else
        {
            reqType="installed";
        }
    
        var wsUrl="http://"+localStorage.getItem("reseller")+"/services/utility-soap#loyaltyCardDataSave";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><loyaltyCardDataSave xmlns="\http://'+localStorage.getItem("reseller")+'/services/utility-soap#loyaltyCardDataSave/\"><appId>'+localStorage.getItem("applicationID")+'</appId><pageId>'+pageId+'</pageId><deviceId>'+DeviceID+'</deviceId><cardNo>'+cardNum+'</cardNo><reqType>'+reqType+'</reqType><total>'+total+'</total><cardType>'+cardType+'</cardType></loyaltyCardDataSave></soap:Body></soap:Envelope>';
    
        console.log("loyaltyCardDataSaveSoap "+soapRequest);
    
     var responseData="";
    
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               dataType: "text",
               data: soapRequest,
               cache:false,
               success: function(data, status, req)
               {
			     $(".appypie-loader").hide();
               console.log("result=="+req.responseText);
               responseData=$(req.responseText).find("return").text();;
             //  alert(responseData);
               
               if((isfromCardValidation!="1")&&(isfromCardValidation!="2"))
               {
                   var counter=1;
                   for(var i=0;i<parseInt(responseData);i++)
                   {
                   localStorage.setItem("loyaltyItemsConsumedAtIndex"+cardNum+"#"+sessionStorage.getItem("loyaltycardUniqueId"),counter);
                   counter++;
                   }
               }

               if(isfromCardValidation!="2")
               {
                    openLoyaltyInnerPageAfterWebValidation(iIndex,indexIndex);
               }
               else
               {
                   if(cardType=="0")
                   {
                    openLoyaltyInnerPageAfterWebValidation(iIndex,indexIndex);
                   }
               }
               
                },
               error: function(response, textStatus, errorThrown)
               {
			     $(".appypie-loader").hide();
			     
			    
               alertPopUp(oops_laoyalty,serverresponding_loyalty);
               console.log(errorThrown.responseText);
               }
               });

}
    function openLoyaltyInnerPageAfterWebValidation(i,index)
    {
    
    $('.disclaimer').attr("style",'top:;');
    
    
    console.log("the value of index is ----->openLoyaltyInnerPage()--->"+index);


        $loyaltyDataIndex = $(masterData).find("loyalty[indexval="+index+"]" );
        var j=0;
        var loyaltyInnerHTML='';
        var loyaltyCheckFree;
    
            $loyaltyDataIndex.find('loyaltyCards').each(function()
                                                    {
                                                    
                                                    if(i==j)
                                                    {
                                                    
                                                    var noOfStampsInLoyalty=$(this).find("card_no_of_stamps").text();
                                                    var cardStampIconUrl=$(this).find("cardstampicon").text();
                                                    var finalFreebieOfferText=$(this).find("finalfreebietext").text();
                                                    var finalFreebieCardIconUrl=$(this).find("freesloticon").text();
                                                    var totalNoOFCardsNeedToUsedToGetFreebie=$(this).find("ulfreebieslot_count").text();
                                                    var CardSlotValue=$(this).find("freebie_slot").text();
                                                    var CardNameValue=$(this).find("freebie_name").text();
                                                    var midCardIcon=$(this).find("midcardicon").text().split(',');
                                                    var mainCardNameValue=$(this).find("fld_card_name").text();
                                                    var CardDescpritpionValue=$(this).find("fld_brief_desc").text();
                                                    var cardDailyLimitValue=$(this).find("carddailylimit").text();
                                                    var loyalty_valid_from=$(this).find("loyalty_valid_from").text();
                                                    var loyalty_valid_to=$(this).find("loyalty_valid_to").text();
                                                    var imgcardheader_saveimage=$(this).find("imgcardheader_saveimage").text();
                                                    var imgcardbackground_saveimage=$(this).find("imgcardbackground_saveimage").text();
                                                    var show_numbers_input=$(this).find("show_numbers_input").text();
                                                    var activeClass='';
                                                    var cardno=index;
                                                    var cardUsage=$(this).find("carduseslimit").text();
                                                    if($(this).find("cardno"))
                                                    {
                                                    cardno=$(this).find("cardno").text();
                                                    }
                                               
                                                    sessionStorage.setItem('cardUsage'+cardno,cardUsage);
                                                    sessionStorage.setItem('cardno',cardno);
                                                    if(parseFloat(noOfStampsInLoyalty) > 0)
                                                    {
                                                    if(imgcardheader_saveimage)
                                                    {
                                                    loyaltyInnerHTML= '<div class="page-text"><div class="loyaltyCard-wrapper"><div class="loyalty-appContainer"><div style="background:url('+imgcardbackground_saveimage+');" id="imgcardbackground_1_inner" class="loaylty-back"><div class="middilePan"><img style="display:block;" src='+imgcardheader_saveimage+'><h1>'+mainCardNameValue+'</h1><p class="Bdiscription">'+CardDescpritpionValue+'</p><div class="loyalty-numb"><ul>';
                                                    }
													else{
                                                    loyaltyInnerHTML= '<div class="page-text"><div class="loyaltyCard-wrapper"><div class="loyalty-appContainer"><div style="background:url('+imgcardbackground_saveimage+');" id="imgcardbackground_1_inner" class="loaylty-back"><div class="middilePan"><h1>'+mainCardNameValue+'</h1><p class="Bdiscription">'+CardDescpritpionValue+'</p><div class="loyalty-numb"><ul>';
                                                    }
                                                        sessionStorage.getItem("cardno");
                                                    for (var m=1; m<=noOfStampsInLoyalty;m++)
                                                    {
                                                    if(localStorage.getItem("loyaltyItemsConsumedAtIndex"+sessionStorage.getItem('cardno')+"#"+sessionStorage.getItem("loyaltycardUniqueId")))
                                                    {
                                                    console.log("m -->"+m+'<----loyaltyItemsConsumedAtIndex---->'+localStorage.getItem("loyaltyItemsConsumedAtIndex"+sessionStorage.getItem('cardno')+"#"+sessionStorage.getItem("loyaltycardUniqueId")));
                                                    if(m > parseFloat(localStorage.getItem("loyaltyItemsConsumedAtIndex"+sessionStorage.getItem('cardno')+"#"+sessionStorage.getItem("loyaltycardUniqueId"))))
                                                    {
                                                    activeClass='';
                                                    }
                                                    else
                                                    {
                                                        
                                                      activeClass='active';
                                                        
                                                    }
                                                    }
                                                     console.log("show_numbers_input--->"+show_numbers_input);
                                                    if(parseFloat(show_numbers_input) == 1)
                                                    {
                                                    console.log("show_numbers_input--->"+show_numbers_input);
                                                    
                                                    if(activeClass == 'active')
                                                    {
                                                    loyaltyInnerHTML+='<li class="'+activeClass+'"><span class="loyalbox  '+cardStampIconUrl+'" style="background-image:url('+cardStampIconUrl+');color:#333;" onclick="onStampClick('+m+','+i+','+index+')"><a>'+m+'</a></span></li>';
                                                    }
                                                    else
                                                    {
                                                    loyaltyInnerHTML+='<li class="'+activeClass+'"><span onclick="onStampClick('+m+','+i+','+index+')" class="loyalbox" style="background-image:url()"><a>'+m+'</a></span></li>';
                                                    }
                                                    
                                                    }
                                                    else
                                                    {
                                                    loyaltyInnerHTML+='<li class="'+activeClass+'"><span class="loyalbox '+cardStampIconUrl+'" style="background-image:url('+cardStampIconUrl+');color:#333;" onclick="onStampClick('+m+','+i+','+index+')" ><a>&nbsp;</a></span></li>';
                                                    }
                                                    if(m==noOfStampsInLoyalty)
                                                    {
                                                    
                                                    loyaltyInnerHTML+='<li><span onclick="onFreeStampClick('+m+','+i+','+index+')" style="background-image:url('+finalFreebieCardIconUrl+'); color:#FFF;" class="loyalbox '+ finalFreebieCardIconUrl +'"><a>&nbsp;</a></span></li></ul></div></div></div></div></div></div>';
                                                    }
                                                    
                                                    }
                                                    }
                                                    else
                                                    {
                                                   
                                                    if(parseFloat(localStorage.getItem("loyaltyItemsConsumedAtIndex"+sessionStorage.getItem('cardno')+"#"+sessionStorage.getItem("loyaltycardUniqueId"))) == 1 && parseFloat(cardUsage) == 1)
                                                    {
                                                    
                                                    if(sessionStorage.getItem("loyaltyBackPage") == 'true')
                                                    {
                                                    sessionStorage.removeItem("loyaltyBackPage");
                                                    startLoyaltyParsing(index);
                                                    }
                                                    else
                                                    {
                                                    	
                                                    
                                                    alertPopUp(alert_loyalty,cardalreadyredeemed_loyalty);
                                                    }
                                                    }
                                                    else
                                                    {
                                                    localStorage.setItem("loyaltyItemsConsumedAtIndex"+sessionStorage.getItem('cardno')+"#"+sessionStorage.getItem("loyaltycardUniqueId"),0);
                                                    }
                                                   
                                                    onFreeStampClick(0,i,index);
                                                    loyaltyCheckFree=true;
                                                    }
                                                    }
                                                    j++;
                                                    });
    
    if(!loyaltyCheckFree)
    {
         appendHtml(loyaltyInnerHTML,2,2);
    }
    
}




function onStampClick(stampNumber,loyaltyCardIndex,index)
{

    $loyaltyDataIndex = $(masterData).find("loyalty[indexval="+index+"]" );
    var j=0;
    var loyaltyStampHTML='';
    var checkValue=0;
    $loyaltyDataIndex.find('loyaltyCards').each(function()
                                                {
                                                
                                                if(loyaltyCardIndex==j)
                                                {
                                                var noOfStampsInLoyalty=$(this).find("card_no_of_stamps").text();
                                                if(parseFloat(localStorage.getItem("loyaltyItemsConsumedAtIndex"+sessionStorage.getItem('cardno')+"#"+sessionStorage.getItem("loyaltycardUniqueId"))) == parseFloat(noOfStampsInLoyalty))
                                                {
                                                	
                                                	
                                                 alertPopUp(error_loyalty,pleaseuseyourfreebiestamp_loyalty);
                                                checkValue=1;
                                                return false;
                                                }
                                                
                                                
                                                var cardStampIconUrl=$(this).find("cardstampicon").text();
                                                var finalFreebieOfferText=$(this).find("finalfreebietext").text();
                                                var finalFreebieCardIconUrl=$(this).find("freesloticon").text();
                                                var totalNoOFCardsNeedToUsedToGetFreebie=$(this).find("ulfreebieslot_count").text();
                                                var CardSlotValue=$(this).find("freebie_slot").text();
                                                var CardNameValue=$(this).find("freebie_name").text().split(',');
                                                var midCardIcon=$(this).find("midcardicon").text().split(',');
                                                var mainCardNameValue=$(this).find("fld_card_name").text();
                                                var CardDescpritpionValue=$(this).find("fld_brief_desc").text();
                                                var cardDailyLimitValue=$(this).find("carddailylimit").text();
                                                var loyalty_valid_from=$(this).find("loyalty_valid_from").text().split('/');
                                                var loyalty_valid_to=$(this).find("loyalty_valid_to").text().split('/');
                                                var imgcardheader_saveimage=$(this).find("imgcardheader_saveimage").text();
                                                var imgcardbackground_saveimage=$(this).find("imgcardbackground_saveimage").text();
                                                var validateCode=$(this).find("unlockcode").text();
                                                var handDevice=$loyaltyDataIndex.find("loyaltyhanddevice").text();
                                                var loyaltyValidate=$loyaltyDataIndex.find("loyaltyvalidate").text();
                                                var loyaltyCancel=$loyaltyDataIndex.find("loyaltycancel").text();
                                                var cardno=index;
                                                if($(this).find("cardno"))
                                                {
                                                cardno=$(this).find("cardno").text();
                                                }
                                                var cardUsage=$(this).find("carduseslimit").text();
                                                sessionStorage.setItem('cardUsage'+cardno,cardUsage);
                                                sessionStorage.setItem('cardno',cardno);
                                                
                                                if(loyalty_valid_to.length > 3)
                                                {
                                                var newValidTo=loyalty_valid_to[1]+'/'+loyalty_valid_to[0]+'/'+loyalty_valid_to[2];
                                                }
                                                else
                                                {
                                                 var newValidTo=loyalty_valid_from[1]+'/'+loyalty_valid_from[0]+'/'+parseFloat(loyalty_valid_from[2])+1;
                                                }
                                                var newValidFrom=loyalty_valid_from[1]+'/'+loyalty_valid_from[0]+'/'+loyalty_valid_from[2];
                                                
                                                var validFromTimeStamp=new Date(newValidFrom).getTime();
                                                var validToTimeStamp=new Date(newValidTo).getTime();
                                                
                                                console.log('loyalty_valid_to--->'+newValidTo);
                                                 console.log('loyalty_valid_from-->'+newValidFrom);
                                                console.log('loyalty_valid_from--->'+validFromTimeStamp);
                                                console.log('loyalty_valid_to--->'+validToTimeStamp);
                                                sessionStorage.setItem('validateCode',validateCode);
                                                //sessionStorage.setItem('validateCode','1234');
                                               
                                                if(midCardIcon.length>1 && CardNameValue.length>1)
                                                {
                                                loyaltyStampHTML='<div class="page-text"><div class="loyaltyCard-wrapper"><div class="loyalty-appContainer"><div style="background:url('+imgcardbackground_saveimage+');" id="imgcardbackground_1_inner" class="loaylty-back"><!-- Background Image will show here  --><div  class="loyalStemp"><i class="'+midCardIcon[stampNumber-1]+'"> </i><h5>'+mainCardNameValue+'</h5><div class="Bdiscription">'+handDevice+'<span>'+CardNameValue[stampNumber-1]+' </span></div><div id="validateOptions"><a onclick="callQrOrValidate(\'code\')" class="loyalty-btn" style="border-color:'+sessionStorage.getItem("primaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("primaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("primaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+typingecuritycode_loyalty+'</a><a onclick="callQrOrValidate(\'QR\')" class="loyalty-btn" style="border-color:'+sessionStorage.getItem("secondaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("secondaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("secondaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+validatescanningcode_loyalty+'</a></div><div id="securityCode" style="display:none;"><input data-role="none" type="text" name="codeValue" class="loyalty-code" id="StampTxtFld"><a class="loyalty-btn" id="stampCardBtn" onclick="performStampCardValidation('+validFromTimeStamp+','+validToTimeStamp+','+index+','+loyaltyCardIndex+')" style="border-color:'+sessionStorage.getItem("primaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("primaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("primaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+loyaltyValidate+'</a><a onclick="openSecurity()" id="stampCancelBtn" class="loyalCancel" style="border-color:'+sessionStorage.getItem("secondaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("secondaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("secondaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+loyaltyCancel+'</a></div></div></div></div></div></div>';
                                                }
                                                else
                                                {
                                                loyaltyStampHTML='<div class="page-text"><div class="loyaltyCard-wrapper"><div class="loyalty-appContainer"><div style="background:url('+imgcardbackground_saveimage+');" id="imgcardbackground_1_inner" class="loaylty-back"><!-- Background Image will show here  --><div  class="loyalStemp"><i class="'+cardStampIconUrl+'"> </i><h5>'+mainCardNameValue+'</h5><div class="Bdiscription">'+handDevice+'</div><div id="validateOptions"><a onclick="callQrOrValidate(\'code\')" class="loyalty-btn" style="border-color:'+sessionStorage.getItem("primaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("primaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("primaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+typingecuritycode_loyalty+'</a><a onclick="callQrOrValidate(\'QR\')" class="loyalty-btn" style="border-color:'+sessionStorage.getItem("secondaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("secondaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("secondaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+validatescanningcode_loyalty+'</a></div><div id="securityCode" style="display:none;"><input data-role="none" type="text" name="codeValue" class="loyalty-code" id="StampTxtFld"><a onclick="performStampCardValidation('+validFromTimeStamp+','+validToTimeStamp+','+index+','+loyaltyCardIndex+')" id="stampCardBtn" class="loyalty-btn" style="border-color:'+sessionStorage.getItem("primaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("primaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("primaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+loyaltyValidate+'</a><a onclick="openSecurity()" id="stampCancelBtn" class="loyalCancel" style="border-color:'+sessionStorage.getItem("secondaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("secondaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("secondaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+loyaltyCancel+'</a></div></div></div></div></div></div>';
                                                }
                                                }
                                                j++;
    });
    // id="securityCode" onclick="openSecurity();"
    if(parseFloat(checkValue) == 1)
    {
        return false;
    }
    appendHtml(loyaltyStampHTML,3,3);
    console.log($("#contentHolder3").html());
}

function callQrOrValidate(type)
{
    if(type == 'code')
    {
         $('#securityCode').show();
         $('#validateOptions').hide();
    }
    else
    {
        sessionStorage.setItem("fromLoyalty",'true');
        //window.location="scanner:";
		toaster.callBarCodeReader();
        $('#securityCode').show();
        $('#validateOptions').hide();
    }
    
}

function openSecurity()
{
   $('#securityCode').hide();
     $('#validateOptions').show();
}

function onFreeStampClick(stampNumber,loyaltyCardIndex,index)
{
    console.log("stampNumber--->"+stampNumber+"<---loyaltyItemsConsumedAtIndex--->"+localStorage.getItem("loyaltyItemsConsumedAtIndex"+sessionStorage.getItem('cardno')+"#"+sessionStorage.getItem("loyaltycardUniqueId")));
    if(parseFloat(stampNumber) != parseFloat(localStorage.getItem("loyaltyItemsConsumedAtIndex"+sessionStorage.getItem('cardno')+"#"+sessionStorage.getItem("loyaltycardUniqueId"))))
    {
       // alertPopUp('Already Redeemed','The Access Has Already Been Redeemed');
        return false;
    }
    

    $loyaltyDataIndex = $(masterData).find("loyalty[indexval="+index+"]" );
    var j=0;
    var loyaltyStampHTML='';
    $loyaltyDataIndex.find('loyaltyCards').each(function()
                                                {
                                                
                                                if(loyaltyCardIndex==j)
                                                {
                                                var noOfStampsInLoyalty=$(this).find("card_no_of_stamps").text();
                                                var cardStampIconUrl=$(this).find("cardstampicon").text();
                                                var finalFreebieOfferText=$(this).find("finalfreebietext").text();
                                                var finalFreebieCardIconUrl=$(this).find("freesloticon").text();
                                                var totalNoOFCardsNeedToUsedToGetFreebie=$(this).find("ulfreebieslot_count").text();
                                                var CardSlotValue=$(this).find("freebie_slot").text();
                                                var CardNameValue=$(this).find("freebie_name").text().split(',');
                                                var midCardIcon=$(this).find("midcardicon").text().split(',');
                                                var mainCardNameValue=$(this).find("fld_card_name").text();
                                                var CardDescpritpionValue=$(this).find("fld_brief_desc").text();
                                                var cardDailyLimitValue=$(this).find("carddailylimit").text();
                                                var loyalty_valid_from=$(this).find("loyalty_valid_from").text();
                                                var loyalty_valid_to=$(this).find("loyalty_valid_to").text();
                                                var imgcardheader_saveimage=$(this).find("imgcardheader_saveimage").text();
                                                var imgcardbackground_saveimage=$(this).find("imgcardbackground_saveimage").text();
                                                var loyaltyCancel=$loyaltyDataIndex.find("loyaltycancel").text();
                                                var loyaltyEarned=$loyaltyDataIndex.find("loyaltyearned").text();
                                                var loyaltyRedeemed=$loyaltyDataIndex.find("loyaltyredeemed").text();
                                                var loyaltyValidate=$loyaltyDataIndex.find("loyaltyvalidate").text();
                                                console.log("loyaltyValidate--->"+loyaltyValidate);
                                                var cardno=index;
                                                if($(this).find("cardno"))
                                                {
                                                 cardno=$(this).find("cardno").text();
                                                }
                                                
                                                sessionStorage.setItem('cardno',cardno);
                                                
                                                
                                                if(parseFloat(localStorage.getItem("loyaltyItemsConsumedAtIndex"+sessionStorage.getItem('cardno')+"#"+sessionStorage.getItem("loyaltycardUniqueId"))) == 0)
                                                {
                                                loyaltyStampHTML='<div class="page-text"><div class="loyaltyCard-wrapper"><div class="loyalty-appContainer"><div style="background:url('+imgcardbackground_saveimage+');" id="imgcardbackground_1_inner" class="loaylty-back"><!-- Background Image will show here  --><div  class="loyalStemp"><i class="'+finalFreebieCardIconUrl+'"></i><h5>'+mainCardNameValue+'</h5><div class="Bdiscription" id="changeDisc"><span>'+congrats_loyalty+'</span> <br />'+loyaltyEarned+'<span>'+finalFreebieOfferText+'</span></div>\
                                                  <div class="Bdiscription" id="Bdiscription" style="display:none;"><span>'+congrats_loyalty+'</span> <br />'+loyaltyRedeemed+' <span>'+finalFreebieOfferText+'</span></div><div id="validateOptions"><a onclick="callQrOrValidate(\'code\')" class="loyalty-btn" style="border-color:'+sessionStorage.getItem("primaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("primaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("primaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+typingecuritycode_loyalty+'</a><a onclick="callQrOrValidate(\'QR\')" class="loyalty-btn" style="border-color:'+sessionStorage.getItem("secondaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("secondaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("secondaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+validatescanningcode_loyalty+'</a></div><div id="securityCode" style="display:none;"><input data-role="none" type="text" name="codeValue" id="StampTxtFld" class="loyalty-code"><a id="stampCardBtn" onclick="performFreeStampCardValidation('+stampNumber+','+loyaltyCardIndex+','+index+')" class="loyalty-btn" style="border-color:'+sessionStorage.getItem("primaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("primaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("primaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+loyaltyValidate+'</a><a id="stampCancelBtn" onclick="openSecurity('+index+')" class="loyalCancel" style="border-color:'+sessionStorage.getItem("secondaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("secondaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("secondaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+loyaltyCancel+'</a></div></div></div></div></div></div>';
                                                }
                                                else
                                                {
                                                loyaltyStampHTML='<div class="page-text"><div class="loyaltyCard-wrapper"><div class="loyalty-appContainer"><div style="background:url('+imgcardbackground_saveimage+');" id="imgcardbackground_1_inner" class="loaylty-back"><!-- Background Image will show here  --><div  class="loyalStemp"><i class="'+finalFreebieCardIconUrl+'"></i><h5>'+mainCardNameValue+'</h5><div class="Bdiscription" id="changeDisc"><span>'+congrats_loyalty+'</span> <br />'+loyaltyEarned+'<span>'+finalFreebieOfferText+'</span></div>\
                                                <div class="Bdiscription" id="Bdiscription" style="display:none;"><span>'+congrats_loyalty+'</span> <br />'+loyaltyRedeemed+'<span>'+finalFreebieOfferText+'</span></div><div id="validateOptions"><a onclick="callQrOrValidate(\'code\')" class="loyalty-btn" style="border-color:'+sessionStorage.getItem("primaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("primaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("primaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+typingecuritycode_loyalty+'</a><a onclick="callQrOrValidate(\'QR\')" class="loyalty-btn" style="border-color:'+sessionStorage.getItem("secondaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("secondaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("secondaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+validatescanningcode_loyalty+'</a></div><div id="securityCode" style="display:none;"><input data-role="none" type="text" name="codeValue" id="StampTxtFld" class="loyalty-code"><a id="stampCardBtn" onclick="performFreeStampCardValidation('+stampNumber+','+loyaltyCardIndex+','+index+')" class="loyalty-btn" style="border-color:'+sessionStorage.getItem("primaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("primaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("primaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+loyaltyValidate+'</a><a id="stampCancelBtn" onclick="openSecurity('+loyaltyCardIndex+','+index+')" class="loyalCancel" style="border-color:'+sessionStorage.getItem("secondaryButtonBgColor")+';!important;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,'+sessionStorage.getItem("secondaryButtonBgColor")+'), color-stop(100%,'+sessionStorage.getItem("secondaryButtonBgColor")+'))!important;color:'+sessionStorage.getItem("buttonTextColor")+'!important;">'+loyaltyCancel+'</a></div></div></div></div></div></div>';
                                                }
                                                
                                        
                                                
                                                
                                                }
                                                j++;
                                                });
    
    
    console.log("loyaltyStampHTML"+loyaltyStampHTML);
    
    appendHtml(loyaltyStampHTML,3,3);
    //console.log($("#contentHolder3").html());
}

/*
//Changed by Mohsin, code taken from Shive
function performStampCardValidation(validFrom,validTo,index,loyaltyCardIndex)
{
    console.log("the value of index is ----->performStampCardValidation()--->"+index);
    
    console.log('the time stamp for valid from--->'+validFrom);
    console.log('the time stamp for valid till--->'+validTo);
     var codeValue = document.getElementsByName('codeValue')[0].value;
     var today = new Date().getTime();
    
    console.log("the value of today--->"+today);
    var validFromTimeStamp=validFrom;
    var validToTimeStamp=validTo;
    console.log("code for validation--->"+codeValue);

    if(codeValue.indexOf("Code")>-1)
    {
        codeValue=codeValue.replace("Code", "").trim();
        codeValue=codeValue.replace(":", "").trim();
    }
  
    if( (parseFloat(today) > parseFloat(validFromTimeStamp)) && (parseFloat(today) < parseFloat(validToTimeStamp)) &&  ((sessionStorage.getItem('validateCode')).trim() == codeValue) )
    {
        console.log("code is validated--->"+sessionStorage.getItem('validateCode'));
        if(!localStorage.getItem("loyaltyItemsConsumedAtIndex"+sessionStorage.getItem('cardno')+"#"+sessionStorage.getItem("loyaltycardUniqueId")))
        {
            localStorage.setItem("loyaltyItemsConsumedAtIndex"+sessionStorage.getItem('cardno')+"#"+sessionStorage.getItem("loyaltycardUniqueId"),1);
        }
        else
        {
            localStorage.setItem("loyaltyItemsConsumedAtIndex"+sessionStorage.getItem('cardno')+"#"+sessionStorage.getItem("loyaltycardUniqueId"),(parseFloat(localStorage.getItem("loyaltyItemsConsumedAtIndex"+sessionStorage.getItem('cardno')+"#"+sessionStorage.getItem("loyaltycardUniqueId")))+1));
        }
        
        
        console.log("check the loyaltyItemsConsumedAtIndex--->"+localStorage.getItem("loyaltyItemsConsumedAtIndex"+sessionStorage.getItem('cardno')+"#"+sessionStorage.getItem("loyaltycardUniqueId")));
        document.getElementsByName('codeValue')[0].value='';
        openLoyaltyInnerPage(loyaltyCardIndex,index,"1");

        $('#stampCancelBtn').attr('onclick','performCancelCardValidation()');
    }
    else
    {
        
        alertPopUp('Invalid Code','Please enter a valid code');

       return false;
    }
}
*/
function performStampCardValidation(validFrom,validTo,index,loyaltyCardIndex)
{
  $(".appypie-loader").show();
    console.log("the value of index is ----->performStampCardValidation()--->"+index);
    
    console.log('the time stamp for valid from--->'+validFrom);
    console.log('the time stamp for valid till--->'+validTo);
    var codeValue = document.getElementsByName('codeValue')[0].value;
    if(sessionStorage.getItem('FromScaner') == 'true')
    {
       codeValue =  sessionStorage.getItem("codeValue");
    }
    
    sessionStorage.setItem("FromScaner","false");
    
    //alert("codeValue: " + codeValue);
    var today = new Date().getTime();
    
    console.log("the value of today--->"+today);
    var validFromTimeStamp=validFrom;
    var validToTimeStamp=validTo;
    console.log("code for validation--->"+codeValue);
    
    if(codeValue.indexOf("Code") > -1)
    {
      // alert("0000000");
        codeValue=codeValue.replace("Code", "").trim();
        codeValue=codeValue.replace(":", "").trim();
         //alert(codeValue);
    }
    
    //alert(codeValue);
   // alert(parseFloat(today) + " ----  " + parseFloat(validFromTimeStamp) + "----- " + parseFloat(validToTimeStamp) + "-------" +  sessionStorage.getItem('validateCode'));
    
    if( (parseFloat(today) > parseFloat(validFromTimeStamp)) && (parseFloat(today) < parseFloat(validToTimeStamp)) &&  ((sessionStorage.getItem('validateCode')).trim() == codeValue) )
    {
	 if(checkNetworkConnection())
    	{
       //alert("kkkkk");
        console.log("code is validated--->"+sessionStorage.getItem('validateCode'));
        if(!localStorage.getItem("loyaltyItemsConsumedAtIndex"+sessionStorage.getItem('cardno')+"#"+sessionStorage.getItem("loyaltycardUniqueId")))
        {
              //alert("vvvvvv123456111117777");
          
            localStorage.setItem("loyaltyItemsConsumedAtIndex"+sessionStorage.getItem('cardno')+"#"+sessionStorage.getItem("loyaltycardUniqueId"),1);
        }
        else
        {
        
             //alert("vvvvvv123456111115555555");
        
            localStorage.setItem("loyaltyItemsConsumedAtIndex"+sessionStorage.getItem('cardno')+"#"+sessionStorage.getItem("loyaltycardUniqueId"),(parseFloat(localStorage.getItem("loyaltyItemsConsumedAtIndex"+sessionStorage.getItem('cardno')+"#"+sessionStorage.getItem("loyaltycardUniqueId")))+1));
        }
        
         //alert("vvvvvv12345611111");
        console.log("check the loyaltyItemsConsumedAtIndex--->"+localStorage.getItem("loyaltyItemsConsumedAtIndex"+sessionStorage.getItem('cardno')+"#"+sessionStorage.getItem("loyaltycardUniqueId")));
        document.getElementsByName('codeValue')[0].value='';
        openLoyaltyInnerPage(loyaltyCardIndex,index,"1");
        
        $('#stampCancelBtn').attr('onclick','performCancelCardValidation()');
		}
		else
    	    {
    	        //alert("vvvvvv123456");
			
			
			   
    	        alertPopUp(networkproblem_loyalty,internetnotavailable_loyalty);
    	        
    	        return false;
    	    }
    }
    else
    {
	   $(".appypie-loader").hide();
        //alert("vvvvvv123456");
	 
	   
        alertPopUp(invalidcode_loyalty,pleaseentervalidcode_loyalty);
        
        return false;
    }
}







function performFreeStampCardValidation(stampNumber,loyaltyCardIndex,index)
{
    var codeValue = document.getElementsByName('codeValue')[0].value;
    
    if(sessionStorage.getItem('FromScaner') == 'true')
    {
       codeValue =  sessionStorage.getItem("codeValue");
    }
    
    sessionStorage.setItem("FromScaner","false");
    
    if(codeValue.indexOf("Code") > -1)
    {
      // alert("0000000");
        codeValue=codeValue.replace("Code", "").trim();
        codeValue=codeValue.replace(":", "").trim();
         //alert(codeValue);
    }
    var today = new Date();
    console.log('today performFreeStampCardValidation--->'+today);
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd
    }
    if(mm<10){
        mm='0'+mm
    }
    var today = dd+'/'+mm+'/'+yyyy;
    
   // var DeviceID=localStorage.getItem("UDID");
	  var DeviceID=localStorage.getItem("deviceUUID");
    if(DeviceID == null)
    {
        DeviceID ='1287531689';
    }
     var wsUrl="http://"+localStorage.getItem("reseller")+"/services/utility-soap#loyaltyCardDataValidate";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><loyaltyCardDataValidate xmlns="\http://'+localStorage.getItem("reseller")+'/services/utility-soap#loyaltyCardDataValidate/\"><appId>'+localStorage.getItem("applicationID")+'</appId><validateCode>'+codeValue+'</validateCode><currDate>'+today+'</currDate><pageNo>0</pageNo><cardNo>'+sessionStorage.getItem('cardno')+'</cardNo><stampId>Free</stampId><deviceToken>'+DeviceID+'</deviceToken></loyaltyCardDataValidate></soap:Body></soap:Envelope>';
    console.log("Soap "+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           cache:false,
           success: function(data, status, req)
           {
           console.log("result 1"+req.responseText);
           var rssData=$(req.responseText).find("return").text();
           console.log("result for Free Card  "+rssData);
           console.log("Data "+ rssData);
           //alert("gouracv: " + rssData);
           if(parseFloat(rssData)==1)
           {
           openLoyaltyInnerPage(loyaltyCardIndex,index,"2");
           
           if(parseFloat(sessionStorage.getItem('cardUsage'+sessionStorage.getItem('cardno'))) == 0)
           {
           localStorage.removeItem('loyaltyItemsConsumedAtIndex'+sessionStorage.getItem('cardno')+'#');
           }
           else
           {
           localStorage.setItem("loyaltyItemsConsumedAtIndex"+sessionStorage.getItem('cardno')+"#"+sessionStorage.getItem("loyaltycardUniqueId"),(parseFloat(localStorage.getItem("loyaltyItemsConsumedAtIndex"+sessionStorage.getItem('cardno')+"#"+sessionStorage.getItem("loyaltycardUniqueId")))+1));
           }
           $("#mainback").hide();
           $("#logo").show();
           
           var  element = document.getElementById('stampCardBtn');
           element.style.display = "none";
           $('#Bdiscription').show();
           $('#changeDisc').hide();
           var elementThree=document.getElementById('StampTxtFld');
           elementThree.style.display = "none";
           $('#stampCancelBtn').attr('onclick','performCancelCardValidation('+loyaltyCardIndex+','+index+')');
           $('#stampCancelBtn').html('Back');
           sessionStorage.setItem("loyaltyBackPage",'true');
           }
           else
           {
           
            alertPopUp(oops_loyalty,rssData);
          
           }
           },
           error: function(response, textStatus, errorThrown)
           {
           alertPopUp(oops_loyalty,serverresponding_loyalty);
           console.log(errorThrown.responseText);
           }
           });
    
    
}



function performCancelCardValidation(i,index)
{
    openLoyaltyInnerPage(i,index);
    //history.go(-1);
}
