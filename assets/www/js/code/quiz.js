var quizXML="";
var answerObjectsTemp=new Array();
var prefix_exam_quiz = "exam";
var alert_quiz_data_not_found_quiz = "Quiz data not found!";
var alert_quiz = "Alert";
var alert_ok_quiz = "OK";
var i_scored_quiz = "I scored";
var take_this_quiz = "%. Take this quiz and test your intellectual prowess by installing this app.";
var alert_try_again_later_quiz = "Please Try Again..";
var network_conn_error_quiz = "Network Connection Error";
//Quiz page

var isAnswerEditable=false;
function getquiz(index)
{

if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    $('.appypie-loader').show();
	showHideAd("hide");
	
    number2=1;
//    var xmldata=$.parseXML(window.sessionStorage.getItem("xml"));
//    alert("abc");
//     alert(xmldata);
//    $(xmldata).find("quiz").each(function () {
//                                  alert("cde");
//                                 var id = $(this).attr('indexval');
//                                 alert(id);
//                                 if(id==index)
//                                 {
//                                 quizXML= $(this).find("quizXMl").text();
//                                 quizXML=quizXML.replace("angularml","appypieml");
//                                 sessionStorage.setItem("quiz_name",$(this).find("quizName").text());
//                                 sessionStorage.setItem("quizPageId",$(this).find("quizPageId").text());
//                                 sessionStorage.setItem("quizQuestionSequence",$(this).find("randomQuection").text());
//                                 formShow();
//                                 }
//                                 });
//

    $quizXmlData = $(masterData).find( "quiz[indexval="+index+"]" );
    quizXML= $quizXmlData.find("quizXMl").text();
    quizXML=quizXML.replace("angularml","appypieml");
    sessionStorage.setItem("quiz_name",$quizXmlData.find("quizName").text());
    sessionStorage.setItem("quizPageId",$quizXmlData.find("quizPageId").text());
    sessionStorage.setItem("quizQuestionSequence",$quizXmlData.find("randomQuection").text());
    //  sessionStorage.setItem("quizQuestionSequence",1);
    
//    var immidiateAns=$quizXmlData.find("immidiateAns").text();
//    immidiateAns=1;
//    sessionStorage.setItem("immidiateAns",immidiateAns);
//    formShow();
    
    var displayResultQuest=$quizXmlData.find("displayResultQuest").text();
    sessionStorage.setItem("immidiateAns",displayResultQuest);
    sessionStorage.setItem("quiz_show",$quizXmlData.find("authoQuiz").text());
 
    
    
    
    var prefix=prefix_exam_quiz;
    $.ajax({
           type: 'POST',
           url: quizXML,
           dataType: 'xml',
           success: function(xml) {
           //alert($(xml).find("title").text());
           var xml = $(xml).find("quiz");
           $("#" + prefix + "_quiz_title").text($(xml).find("title").text());
           nextbutton = $(xml).find("nextbutton").text();
           submitBtntxt=$(xml).find("submit").text();
           skipbuttontxt=$(xml).find("skip").text();
           randomQuestion=$(xml).find("randomQuestion").text();
           
           $("#" + prefix + "_next").val( submitBtntxt);
           $("#" + prefix + "_prev").val($(xml).find("prevbutton").text());
           // new to 2.0
           showHint = ($(xml).find("showhint").text() == "1") ? true : false;
           if(showHint) {
           singlechoicehint = $(xml).find("singlechoice").text();
           multiplechoicehint = $(xml).find("multiplechoice").text();
           tmp = $(xml).find("order").text();
           orderhint = (tmp != undefined && tmp != "") ? tmp : "Arrange the answers";
           } else {
           singlechoicehint = "";
           multiplechoicehint = "";
           }
           var passingScore1 = $(xml).find("passingscore").text();
           passingScore=parseInt(passingScore1);
       
           certificateLink = $(xml).find("certificatelink").text();
           // --
           finishbutton = $(xml).find("finishbutton").text();
           resultscreentitle = $(xml).find("resultscreentitle").text();
           resultscreenresultline = $(xml).find("resultscreenresultline").text();
           startbutton = $(xml).find("startbutton").text();
           reviewbutton = $(xml).find("reviewbutton").text();
           welcometext = $(xml).find("welcometext").text();
           
           $("#" + prefix + "_quiz_hint").html( welcometext);
           $("#" + prefix + "_start").val( startbutton);
           timeLimit = ""
           timeLimit = $(xml).find("time").text();
           var number = 0;
           if(xml.find("question").size()>0)
           {
           console.log("xml length"+xml.find("question").size());
           loadHtmlData();
           }
           else
           {
           navigator.notification.alert(
                                        alert_quiz_data_not_found_quiz,
                                        alertDismissed,
                                        alert_quiz,
                                        alert_ok_quiz
                                        );
           return true;
           }
           //console.log("loaded");
           // go( questionId);
           },
          error: function(response, textStatus, errorThrown)
           {
           navigator.notification.alert(
                                        alert_quiz_data_not_found_quiz,
                                         alertDismissed,
                                        alert_quiz,
                                        alert_ok_quiz
                                        );

           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }		   // end success ajax
           }); // end ajax
    
}

function loadHtmlData()
{
    
    var html='<div class="appypie_quiz">  <h3 id="exam_quiz_title"></h3>\
    <div class="appypie_quiz_padding"><div class="time" id="exam_quiz_timer"></div>\
    <div class="appypie_quiz_question" id="exam_quiz_question"></div>\
    <div class="appypie_quiz_summery" id="exam_quiz_hint"></div>\
    <div class="appypie_quiz_answers" id="exam_quiz_answers"></div><div class="appypie_quiz_summery" id="exam_quiz_result"></div>\</div>\</div>\
    <div id="exam_quiz_navigation"> <div class="exam_quiz_navigation_button"><input class="previous_btn" data-role="none"  type="button" id="exam_prev" disabled="disabled" value="Previous" style="display:none;"/></div>\
    <div class="exam_quiz_navigation_button center" ><input class="start_btn" type="button" id="exam_start" data-role="none" value="Start"/></div>\<div class="exam_quiz_navigation_button"><input class="next_btn" data-role="none"  type="button" id="exam_skip" disabled="disabled" value="Skip" style="display:none;"/></div>\
    <div class="exam_quiz_navigation_button right"><input class="next_btn" type="button" id="exam_next" data-role="none"  disabled="disabled" value="Next"  /></div></div>';
    
    
    $("#contentHolder").empty();
    
    
    sessionStorage.removeItem('timerNew');
    setTimeout(function(){
               // alert('Text Append');
               appendHtml(html,'',1);
               
               $.mobile.resetActivePageHeight();
               if(localStorage.getItem('layout') == 'bottom')
               {
               $('#exam_quiz_navigation').attr('style','bottom:62px!important');
               }
               sessionStorage.setItem('timerNew','true');
               
               var q2 = new SQuiz();
               q2.init( {
                       prefix : 'exam',
                       quiz:quizXML
                       });
               
               
               },1000);
}


function share_score() {
   var appLinkOnStore=toaster.shareScore(localStorage.getItem("percent"));
   var scoreShare=i_scored_quiz+localStorage.getItem("percent")+take_this_quiz;
   window.plugins.socialsharing.share(scoreShare, null, null, appLinkOnStore);
}

function share_score2(scoreShare) {
 window.plugins.socialsharing.share(scoreShare, null, null, null);
 }

function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                  return re.test(email);
                  }
                  function formShow()
                  {
                  var wsUrl = "http://"+window.localStorage.getItem("reseller")+"/services/utility-soap#addLoginChoice";
                  /* var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" \
                   xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body> \
                   <sendSubQuoteMailTemplate xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/soap#sendSubQuoteMailTemplate/\"><appId>'+localStorage.getItem("applicationID")+'</appId><name>'+username+'</name> \
                   </sendSubQuoteMailTemplate></soap:Body></soap:Envelope>';*/
                  var quizName=sessionStorage.getItem("quiz_name");
                  var soapRequest =
                  '<?xml version="1.0" encoding="utf-8"?>'+
                  '<soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">'+
                  '<soap:Body>'+
                  '<addLoginChoice xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/utility-soap#addLoginChoice/\">'+
                  '<appId>'+localStorage.getItem("applicationID")+'</appId>'+
                  '<quizName>'+quizName+'</quizName>'+
                  '<formPageId>'+sessionStorage.getItem("quizPageId")+'</formPageId>'+
                  '</addLoginChoice>'+
                  '</soap:Body>'+
                  '</soap:Envelope>';
                    console.log("soapRequest------"+soapRequest);
                  $.ajax({
                         type: "POST",
                         url: wsUrl,
                         contentType: "text/xml",
                         dataType: "text",
                         data: soapRequest,
                         success: processSuccess,
                         error: processError
                         });
                  function processSuccess(data,status,req) {
                  
                  console.log("krishna------"+req);
                  xmlDoc = $.parseXML( req.responseText );
                  
                   console.log("krishnareq.responseText------"+req.responseText);
                  $xml = $( xmlDoc );
                  $intRate = $xml.find("return").text();
                  var a=$intRate;
                  console.log(a);
                  sessionStorage.setItem("quiz_show",a);
                  
                  }
                  function processError(data,status,req) {
                  
                  navigator.notification.alert(
                                               alert_try_again_later_quiz,
                                               alertDismissed,
                                               network_conn_error_quiz,
                                               alert_ok_quiz
                                               );
                  
                  }
                  }