////////////////////////////Quote///////////////////////////////

var alert_request_quote = "Your Request";
var alert_feedback_send_successfully_quote = "Feedback sent successfully";
var alert_failed_send_try_later_quote = "Failed to send,Please try again later";
var blank_filed_quote = "Blank Field";
var enter_comment_quote = "Please enter comment.";
var enter_valid_phoneno_quote = "Please enter a valid phone number.";
var enter_phoneno_quote = "Please enter a phone number.";
var invalid_emailid_quote = "Invalid Email id";
var enter_valid_mailid_quote = "Enter a valid Email id";
var enter_usrname_quote = "Please enter a name";

var requestJson='';
function getquote(index)
{
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    $quoteIndex = $(masterData).find("Request_Quote[indexval="+index+"]" );
    var quoteNameField= $quoteIndex.find("quotename").text();
    var quoteEmailField= $quoteIndex.find("quoteemail").text();
    var quotePhoneField= $quoteIndex.find("quotephone").text();
    var quoteCommentsField= $quoteIndex.find("quotecomment").text();
    var quoteSubmitBtnField= $quoteIndex.find("quotesubmit").text();
    
    requestJson='{'
    +'"Name":"'+quoteNameField+'",'
    +'"Email":"'+quoteEmailField+'",'
    +'"Phone":"'+quotePhoneField+'",'
    +'"Comment":"'+quoteCommentsField+'"'
    +'}';
    
	var quoteHTML= '<div id="form-page" class="form-page"><input data-role="none" type="text" placeholder="'+quoteNameField+'*" id="name" data-prevent-focus-zoom="false" /><input data-role="none" type="email" placeholder="'+quoteEmailField+'*" id="email" data-prevent-focus-zoom="false" /><input data-role="none" type="text" placeholder="'+quotePhoneField+'" id="phno" data-prevent-focus-zoom="false" /><textarea placeholder="'+quoteCommentsField+'" class="comments" id="comment" data-prevent-focus-zoom="false"></textarea><input data-role="none" data-role="none" type="submit" value="'+quoteSubmitBtnField+'" class="button" id="submit" onclick="submitQuote('+index+');" data-prevent-focus-zoom="false" /></div>';
    
	appendHtml("<div class='page-text'>"+quoteHTML+"</div>",'',1);
}
function submitQuote(index)
{
    if(!checkNetworkConnection())
    {
    }
    else
    {
    $('.appypie-loader').show();
    $quoteIndex = $(masterData).find("Request_Quote[indexval="+index+"]" );
    var emailId = $quoteIndex.find("email").text();
    var subject = $quoteIndex.find("subject").text();
	
	var username =document.getElementById("name").value;
    var useremailid =document.getElementById("email").value;
    var userphonenumber =document.getElementById("phno").value;
    var regexObj = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
    var usercomments =document.getElementById("comment").value;
    
    if(username != null && username != "")
    {
        if(useremailid !=null  && useremailid != "" && checkEmail(useremailid))
        {
            if(userphonenumber!=null && userphonenumber!="")
            {
                if(IsNumeric(userphonenumber))
                {
                    if(usercomments!=null && usercomments!="")
                    {
                        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/soap#sendSubQuoteMailTemplate";
                        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><sendSubQuoteMailTemplate xmlns=\"http://'+localStorage.getItem("reseller")+'/services/soap#sendSubQuoteMailTemplate/\"><appId>'+localStorage.getItem("applicationID")+'</appId><name>'+username+'</name><emailId>'+useremailid+'</emailId><phoneNo>'+userphonenumber+'</phoneNo><subject>'+subject+'</subject><comments>'+usercomments+'</comments><quoteLabel>'+requestJson+'</quoteLabel><ownerEmail>'+emailId+'</ownerEmail></sendSubQuoteMailTemplate></soap:Body></soap:Envelope>';
                        
                        console.log("Saop"+ soapRequest);
                        $.ajax({
                               type: "POST",
                               url: wsUrl,
                               contentType: "text/xml",
                               dataType: "text",
                               data: soapRequest,
                               success: function(data, status, req)
                               {
                               setTimeout(function(){$('.appypie-loader').hide();},1000);
                               alertPopUp(alert_request_quote,alert_feedback_send_successfully_quote);
                               document.getElementById("name").value='' ;
                               document.getElementById("email").value='';
                               document.getElementById("phno").value='';
                               document.getElementById("comment").value='';
                               
                               },
                               error: function(response, textStatus, errorThrown)
                               {
                             
                               alertPopUp(alert_request_quote,alert_failed_send_try_later_quote);
                               }
                               });
                    }
                    else
                    {
                        alertPopUp(blank_filed_quote,enter_comment_quote);
                        
                    }
                }
                else
                {
                    alertPopUp(blank_filed_quote,enter_valid_phoneno_quote);
                }
            }
            else
            {
                alertPopUp(blank_filed_quote,enter_phoneno_quote);
            }
            
        }
        else {
            alertPopUp(invalid_emailid_quote,enter_valid_mailid_quote);
        }
    }
    else {
        alertPopUp(blank_filed_quote,enter_usrname_quote);
        }
        
        }
}




