var SQuiz = function () {
    // general settings
    var prefix = "";   	// the style class prefix
    var questions = new Array();
    // quiz settings
    var timeLimit = 0;	// 0 = no limit, no timer visible, else limit in seconds
    var questionId = 0;
    // quiz text elements
    var nextbutton = "";
    var prevbutton ="";
    var finishbutton = "";
    var startbutton = "";
    var reviewbutton = "";
    var resultscreentitle = "";
    var resultscreenresultline = "";
    var welcometext = "";
    var singlechoicehint = "";
    var multiplechoicehint = "";
    var orderhint = "";
    var passingScore = 0;
    var showHint = true;
    var certificateLink = "";
    var startTime = "";				// time quiz starts
    var timeCounter = 0;			// aux variable
    var elapsed = "";				// time elapsed since quiz starts
    var timerInstance = "";			// the interval instance
    var arrayShuffledNumber;
    var submitBtntxt="";
    var skipbuttontxt="";
    var randomQuestion=0;
    var skipQueArray=new Array();
    userAnswers = new Array();
    var currQuestionForCal=0;
    var imidiateAns=1;
    var allQuestionsArrayCount=0;
    var isTrue=true;
    this.init = function (options) {
        
        prefix = options.prefix;
        filename = options.quiz;
        $("#" + prefix + "_prev").hide();
        $("#" + prefix + "_next").hide();
          $("#" + prefix + "_skip").hide();
        // hide the footer
        $("#" + prefix + "_quiz_footer").hide();
        
        loadQuizData();
        
        $("#" + prefix + "_start").click(function () {
                                
                         if(localStorage.getItem("quiz_status")=="finish")
                         {
                             var html = "<div class='appypie_quiz_result'><h3>Submit your score</h3> <p><br>";
                             html += '<div class="score-page"><div><label>Name</label><input type="text" id="name"  data-role="none"></div><div><label>Email</label><input type="text" id="email" data-role="none"></div></div>';
                             console.log(html);
                             $("#" + prefix + "_quiz_question").html(html);
                             $("#" + prefix + "_quiz_answers").hide();
                             localStorage.setItem("quiz_status","submit_score");
                         }
                         else if(localStorage.getItem("quiz_status")=="submit_score")
                         {
                          var quiz_data=localStorage.getItem("quiz_data");
                          console.log("quiz_data before Random"+quiz_data);
                                
                         if(parseInt(sessionStorage.getItem("quizQuestionSequence"))==1)
                         {
                                 var data=quiz_data;
                                 var randamSeries=localStorage.getItem("randomSeries");
                                 var randamSeriesArray = randamSeries.split(',');
                                 var randamSeriesAnswerArray=new Array();
                                 var obj=jQuery.parseJSON(data);
                                 var counts=0;
                                 for( var i in obj)
                                 {
                                 randamSeriesAnswerArray[counts]=obj[i];
                                 counts++;
                                 }
                                 
                                 var sortedAnswerArray=new Array();
                                 var arrayLength = randamSeriesArray.length;
                                         
                                   //parse all array element
                                 for(var i = 0; i < randamSeriesArray.length; i++) {
                                 
                                 randamSeriesArray[i] = parseInt(randamSeriesArray[i]);
                                 }
                                         
                                 
                                 console.log("randamSeriesArray"+randamSeriesArray);
                                 console.log("Randam Series Answer"+randamSeriesAnswerArray);
                                 var c, d, t,s;
                                          // for (c = 0 ; c < ( arrayLength ); c++)
                                for (c = 0 ; c < ( arrayLength-1 ); c++)
                                 {
                                 for (d = 0 ; d < arrayLength - c - 1; d++)
                                 {
                                 if (randamSeriesArray[d] > randamSeriesArray[d+1])
                                 {
                                 t= randamSeriesArray[d];
                                 randamSeriesArray[d]   = randamSeriesArray[d+1];
                                 randamSeriesArray[d+1] = t;
                                 
                                 s= randamSeriesAnswerArray[d];
                                 randamSeriesAnswerArray[d]   = randamSeriesAnswerArray[d+1];
                                 randamSeriesAnswerArray[d+1] = s;
                                 }
                                 }
                                 }
                                         
//                                 for(var i=0;i<randamSeriesArray.length;i++)
//                                 {
//                                 if(sortedData=="")
//                                 {
//                                 sortedData="\"ques"+(parseInt(randamSeriesArray[i])+1)+"\""+":"+"\""+randamSeriesAnswerArray[i]+"\"";
//                                 }
//                                 else
//                                 {
//                                 sortedData=sortedData+","+"\"ques"+(parseInt(randamSeriesArray[i])+1)+"\""+":"+"\""+randamSeriesAnswerArray[i]+"\"";
//                                 }
//                                 }
//                                         for(var i=0;i<randamSeriesArray.length;i++)
//                                         {
//                                         if(sortedData=="")
//                                         {
//                                         sortedData="\"ques"+(parseInt(randamSeriesArray[i])+1)+"\""+":"+"\""+randamSeriesAnswerArray[i]+"\"";
//                                         }
//                                         else
//                                         {
//                                         sortedData=sortedData+","+"\"ques"+(parseInt(randamSeriesArray[i])+1)+"\""+":"+"\""+randamSeriesAnswerArray[i]+"\"";
//                                         }
//                                         }

                                         
                                         var sortedData="";
                                         var counte=0;
                                         for(var i=0;i<allQuestionsArrayCount;i++)
                                         {
                                         var Index = (randamSeriesArray.indexOf(i) > -1);
                                         if(Index)
                                         {
                                         if(sortedData=="")
                                         {
                                         sortedData="\"ques"+(parseInt(randamSeriesArray[counte])+1)+"\""+":"+"\""+randamSeriesAnswerArray[counte]+"\"";
                                         }
                                         else
                                         {
                                         sortedData=sortedData+","+"\"ques"+(parseInt(randamSeriesArray[counte])+1)+"\""+":"+"\""+randamSeriesAnswerArray[counte]+"\"";
                                         }
                                         counte++;
                                         }
                                         else
                                         {
                                         if(sortedData=="")
                                         {
                                         sortedData="\"ques"+(i+1)+"\""+":\"4\"";
                                         }
                                         else
                                         {
                                         sortedData=sortedData+","+"\"ques"+(i+1)+"\""+":\"4\"";
                                         }
                                         
                                         }
                                         }
                                         
                                 sortedData="{"+sortedData+"}";
                                         
                                 console.log("randamSeriesArray"+randamSeriesArray);
                                 console.log("Randam Series Answer"+randamSeriesAnswerArray);
                                 console.log("sorteddata"+ sortedData);
                                 quiz_data=sortedData;
                               //  alert(quiz_data);
                                 console.log("quiz_data after Random"+quiz_data);
                                 }
                         
                        
                                 
        //                                         var obj=jQuery.parseJSON(quiz_data);
        //                                         alert(object);
                                 
                                 var score=localStorage.getItem("percent");
                                 var name=$("#name").val().trim();
                                 var email=$("#email").val().trim();
                                 if(name=="")
                                 {
                                 navigator.notification.alert(
                                                              'Please enter valid name!',
                                                              alertDismissed,
                                                              'Alert',
                                                              'OK'
                                                              );
                                 }else if(email=="")
                                 {
                                 navigator.notification.alert(
                                                              'Please enter valid email!',
                                                              alertDismissed,
                                                              'Alert',
                                                              'OK'
                                                              );
                                 }
                                 else if(validateEmailForQuiz(email)==false)
                                 {
                                 navigator.notification.alert(
                                                              'Please enter valid email id!',
                                                              alertDismissed,
                                                              'Alert',
                                                              'OK'
                                                              );
                                 }
                                 else
                                 {
                                 //---------------Ishan code/////////////////////////
                                 var wsUrl = "http://"+window.localStorage.getItem("reseller")+"/services/utility-soap#addQuizTest";
                                 var quiz_name=sessionStorage.getItem("quiz_name");
                                 var quizPageId=sessionStorage.getItem("quizPageId");
                                 console.log(localStorage.getItem("applicationID"));

                                 var soapRequest =
                                 '<?xml version="1.0" encoding="utf-8"?>'+
                                 '<soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">'+
                                 '<soap:Body>'+
                                 '<addQuizTest xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/utility-soap#addQuizTest/\">'+
                                 '<appId>'+localStorage.getItem("applicationID")+'</appId>'+
                                 '<deviceId>'+localStorage.getItem("deviceUUID")+'</deviceId>'+
                                 '<quizData>'+quiz_data+'</quizData>'+
                                 '<quizName>'+quiz_name+'</quizName>'+
                                 '<formPageId>'+quizPageId+'</formPageId>'+
                                 '<score>'+score+'</score>'+
                                 '<email>'+email+'</email>'+
                                 '<name>'+name+'</name>'+
                                 '</addQuizTest>'+
                                 '</soap:Body>'+
                                 '</soap:Envelope>';
                                 
                                 console.log("soapRequest="+soapRequest);
                                 
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
                                 xmlDoc = $.parseXML( req.responseText );
                                 $xml = $( xmlDoc );
                                 $intRate = $xml.find("return").text();
                                 var a=$intRate;
                                 console.log("intRate=========="+a);
                                 var html = "<div class='appypie_quiz_result'><h3>Thank You</h3> <p><br>";
                                 $("#" + prefix + "_quiz_question").html(html);
                                 $("#" + prefix + "_start").val( "Start the quiz").show("slow");
                                 localStorage.setItem("quiz_status","start");
                                 // alert("response="+a);
                                 }
                                 function processError(data,status,req) {
                                 //alert("fail");
                                 navigator.notification.alert(
                                                              'Please Try Again..',
                                                              alertDismissed,
                                                              'Network Connection Error',
                                                              'OK'
                                                              );

                                 }
                               
                                 }
                         }
                          else
                         {
                         
						  shuffle(questions);
                         $("#" + prefix + "_quiz_answers").show();
                         $("#" + prefix + "_SQuiz2_overlay").css('display','none');
                         userAnswers = new Array();
                         $(this).hide("slow", function() {
                                      questionId = 0;
                                      go();
                                      });
                         $("#" + prefix + "_next").show();
                          $("#" + prefix + "_skip").show();
                                   $("#" + prefix + "_skip").unbind('click');
                                 $("#" + prefix + "_skip").click(function () {
                                                                 // everytime:
                                                                 if(questionId < questions.length)
                                                                 {
                                                                 if( questionId == questions.length - 1)
                                                                 {
                                                                 skipQueArray[skipQueArray.length]=questionId;
                                                                 $("#" + prefix + "_prev").hide("slow");
                                                                 $("#" + prefix + "_next").hide("slow");
                                                                 $(this).hide("slow");
                                                                 evaluation();
                                                                 }
                                                                 else
                                                                 {
                                                                
                                                                 skipQueArray[skipQueArray.length]=questionId;
                                                                 nextButtonDefault();
                                                                 questionId++;
                                                                 go();
                                                                 
                                                                 }
                                                                 }
                                                                 });

//                                 if(userAnswers.length < questionId + 1) {
//                                 $("#" + prefix + "_next").attr("disabled", "disabled");
//                                 } else {
                             $("#" + prefix + "_skip").removeAttr("disabled");
                            // }
                                 
                                 
                         //$("#" + prefix + "_next").val( nextbutton);
                         
                         
                         //To see the previos
                         //$("#" + prefix + "_prev").show();
                         nextButtonDefault();
                         $("#" + prefix + "_prev").unbind('click');
                         $("#" + prefix + "_prev").click(function () {
                                                         // everytime:
                                                         nextButtonDefault();
                                                         questionId--;
                                                         go();
                                                         });
                         if(!$.support.opacity)  ieFix();
                         // start the timer
                         elapsed = 0;
                         timeCounter = 0;
                         startTime = new Date().getTime();
                         timerInstance = setTimeout(timerCallback, 1000);
                         }
                         });
        
    };
    function evaluationWithQuestion()
    {

          $("#" + prefix + "_skip").hide();
        var correctAnswers = 0;
        var reviewObject = new Array();
        for(var z in questions)
        {
            console.log("8888888888888>>>>"+questions[z]);
            questions[z].correct=false;
        }
        
        
        for(var i in userAnswers) {
            var rev				= new Object();
            rev.question 		= questions[i].getQuestion();
            rev.type			= questions[i].getType();
            rev.answers			= new Object();
            var answers 		= userAnswers[i].getUserSelection();
            var correct 		= true;
            // choice questions first
            if(userAnswers[i].getType() != "order") {
                // walk through ALL answers, get them by question.getAnswers
                var allAnswers = questions[i].getAnswers();
                for( var n in allAnswers) {
                    var savedInReview = false;
                    // check for every user selected answer
                    for( var a in answers) {
                        if(answers[a].dbId == allAnswers[n].dbId) {
                            // the user has selected this answer, save it to rev object
                            corr = (allAnswers[n].correctAnswer == "true") ? true : false;
                            rev.answers[n]= {text:allAnswers[n].answer, correct:corr, userselection:true};
                            // remember that it is saved already
                            savedInReview = true;
                        }
                        // if one of the possible answers is wrong, mark question completly as wrong
                        //if(answers[a].correctAnswer == "false") correct = false;
						  if(answers[a].correctAnswer == "false" || answers[a].correctAnswer == "") correct = false;

                    }
                    // if it is not saved already:
                    if(savedInReview == false) {
                        corr = (allAnswers[n].correctAnswer == "true") ? true : false;
                        rev.answers[n]= {text:allAnswers[n].answer, correct:corr, userselection:false};
                    }
                }
                //console.log("correct ans---->"+correct);
            } else {
                // order question:
                /*
                 * We generated id's before we shuffled the answers, so correct order is 0,1,2,3 ...
                 * so increment after testing dbId ( which holds the correct order position) the var test
                 */
                var test = 0;
                for( var a in answers) {
                    if( answers[a].dbId != test) {
                        correct = false;
                    };
                    test++;
                }
                // the answers in the original answer object are in the correct order
                var allAnswers = questions[i].getAnswers();
                allAnswers = allAnswers.sort(sortById);
                for(var a = 0; a < allAnswers.length; a++) {
                    rev.answers[a]= {text:allAnswers[a].answer, correct:true};
                }
            }
            //console.log("correct ans---->"+correct);
            
            questions[i].correct = correct;
            console.log("evaluation()--->questions[i].correct--->"+questions[i].correct);
            reviewObject.push(rev);
            if(correct == true) correctAnswers++;
        }
        /*
         // Not all questions are answered?
         if(questions.length > userAnswers.length) {
         for(var n = userAnswers.length; n < questions.length; n++) {
         saveCorrectStatus(questions[n].getQId(), false);
         var answers 		= answerList[questions[n].getQId()];
         var rev				= new Object();
         rev.question 		= questions[n].getQuestion();
         rev.type 			= getQuestionType(questions[n].getQId());
         rev.answers			= new Object();
         for( var a in answers) {
         var correct = (rev.type == "choice") ? (answers[a].correct==1)? true : false : true;
         rev.answers[a]= {text:answers[a].text, correct:correct, userselection:false};
         }
         reviewObject.push(rev);
         }
         }
         */
        //        var viewData = {
        //
        //        };
//        alert( questionId);
//        for(var k=0;k<questions.length;k++)
//        {
         //  alert(questions[questionId].correct);
   
        
            if(questions[questionId].correct==true)
            {
                var resultText="Correct answer.";
                $("#" + prefix + "_quiz_result").html("<h4>"+resultText+"<h4>");
                $("#" + prefix + "_quiz_result").css("color", "#1EBF28");
            }
            else
            {
                var resultText="Wrong answer.";;
                $("#" + prefix + "_quiz_result").html("<h1>"+resultText+"<h1>");
                $("#" + prefix + "_quiz_result").css("color", "#F90C0C");
            }
        
//        $("#" + prefix + "_quiz_answers").children().each(function(){
//                                                          $(this).children().css("color", "#F90C0C");
//                                                                        $(this).children().each(function()
//                                                                        {
//                                                                          $(this).children().css("color", "#1EBF28");
//                                                                                                
//                                                                                                $($(this).children()+" > input").attr("readonly", true);
//                                                                                                $($(this).children()+"> input").attr("disabled", true);
//                                                                                                $($(this).children()+" > select").attr("readonly", true);
//                                                                        });
//
//      alert(12);  
        
//                        for( var i in answerObjectsTemp)
//                           {
//                              
//                               $("#" + answerObjectsTemp[i].id).css("color", "#1EBF28");
//                           }
        
        isAnswerEditable=true;
        answerObjectsTemp=new Array();

        
    };

    // =============  service functions =================
    // load a specified quiz file
    function loadQuizData(){
        
        localStorage.setItem("quiz_status","start");
        console.log("filename==>"+filename);
        isTrue=true;
        $.ajax({
               type: 'POST',
               url: filename,
               dataType: 'xml',
               success: function(xml) {
               //alert($(xml).find("title").text());
               var xml = $(xml).find("quiz");
               $("#" + prefix + "_quiz_title").text($(xml).find("title").text());
               
               nextbutton = $(xml).find("nextbutton").text();
               submitBtntxt=$(xml).find("submit").text();
               skipbuttontxt=$(xml).find("skip").text();
               randomQuestion=$(xml).find("randomQuestion").text();
               
                 $("#" + prefix + "_next").val( nextbutton);
               $("#" + prefix + "_skip").val( skipbuttontxt);
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
               
               timeLimit = $(xml).find("time").text();
               var number = 0;
               xml.find("question").each(function(){
                                         var q = $(this);
                                         number++;
                                         console.log("========"+q.find("videourl").text());
                                         
                                         var url=q.find("videourl").text();
                                         if ((url.indexOf("youtube")>-1)||(url.indexOf("youtu.be")>-1))
                                        {
                                            var myId = getId(url);
                                            url="http://www.youtube.com/embed/"+myId;
                                         }
                                         var image11=q.find("media").text();
                                         image11=image11.replace("angularml","appypieml");
                                         
                                         var question = new Question({
                                                                     question:q.contents().first().text(),
                                                                     image:image11,
                                                                     video:url,
                                                                     audio:q.find("audiourl").text(),
                                                                     mediaType:q.find("mediaActive").text(),
                                                                     prefix: prefix,
                                                                     number: number,
                                                                     fixHeightListener: fixWrapperHeight,
                                                                     questionListener: questionListener,
                                                                     singlechoicehint: singlechoicehint,
                                                                     multiplechoicehint: multiplechoicehint,
                                                                     orderhint: orderhint,
                                                                     showHint: showHint
                                                                     });
                                         question.createAnswers(q.children("answer"));
                                         questions.push(question);
                                      
                                         setTimeout(function(){$('.appypie-loader').hide();},1000);
                                         
                                         });
             

                allQuestionsArrayCount=questions.length;
            
             
                if(parseInt(sessionStorage.getItem("quizQuestionSequence"))==1)
                {
                   shuffle(questions);
                   if(questions.length>randomQuestion)
                   {
                   questions.splice(randomQuestion,questions.length);
                   }
                }

               
               } // end success ajax
               }); // end ajax
      
    };  // end loadData
    function shuffle(array) {
        var RandamQueIndexArray=new Array();
        var currentIndex = array.length, temporaryValue, randomIndex ;
        var temporaryValue2, randomIndex2,temporaryValue3 ;
        
        var currentIndex2= array.length, temporaryValue, randomIndex ;
         arrayShuffledNumber= new Array();
        for(var i=0;i<array.length;i++)
        {
            arrayShuffledNumber[i]=i;
        }
        // While there remain elements to shuffle...
        while (0 !== currentIndex) {
            
            // Pick a remaining element...
            randomIndex = Math.floor(Math.random() * currentIndex);
            currentIndex -= 1;
            randomIndex2=randomIndex;
            // And swap it with the current element.
          
            temporaryValue = array[currentIndex];
            array[currentIndex] = array[randomIndex];
            array[randomIndex] = temporaryValue;
            
            temporaryValue2 = arrayShuffledNumber[currentIndex];
            arrayShuffledNumber[currentIndex] = arrayShuffledNumber[randomIndex];
            arrayShuffledNumber[randomIndex] = temporaryValue2;

        }

        if(array.length>randomQuestion)
        {
            array.splice(randomQuestion,array.length);
            arrayShuffledNumber.splice(randomQuestion,arrayShuffledNumber.length);
        }
        localStorage.setItem("randomSeries",arrayShuffledNumber);
        return array;
    }
    
    function getId(url) {
        var regExp = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=)([^#\&\?]*).*/;
        var match = url.match(regExp);
        
        if (match && match[2].length == 11) {
            return match[2];
        } else {
            return 'error';
        }
    }

    /**
     * Everytime a next or previouus button is clicked, this function is called.
     * 1. case: User has not answered the question.
     * 		Show the question and answers
     */
    function go() {
        isAnswerEditable=false;
        //console.log("QuestionId " + questionId);
        // clear the answer section
        // moved to the answer class to accomplish animated removal.
        //$("#" + prefix + "_quiz_answers").empty();
        // show the new question and fix wrapper height
    	 // questions[questionId].showQuestion();
            $("#" + prefix + "_skip").show();
    	 $("#" + prefix + "_quiz_result").html("");
      
    	if( sessionStorage.getItem("quizQuestionSequence")=="1")
    		{
                
                //alert(questions.length);
                //var $random = UniqueRandom.generate(questions.length);
  	      
    			//	questions[$random].showQuestion();
                  questions[questionId].showQuestion(questionId);
    		
    		}
    	else{
    		
    		  questions[questionId].showQuestion(questionId);
    		
    	}
     
    		
        // moved to fixWrapperHeight() because this function is a kind
        // of event listener function for the answer class. if all answers are
        // rendered completly the function is called. Them we can perform any changes
        // on answers such as marking already answered entries.
        //showMarkedAnswers ();
        // all questions answered, last question is reached if
        
        
        localStorage.setItem('radioVlue',null);
       

        if( userAnswers.length == questions.length && questionId == questions.length - 1) {
           
            nextButtonToFinishButton();
        } else {
          
            // disable next button if next question is NOT answered
            if(userAnswers.length < questionId + 1) {
                $("#" + prefix + "_next").attr("disabled", "disabled");
               
            } else {
                $("#" + prefix + "_next").removeAttr("disabled");
                
            }
            // We are not at the end of the quiz, show the 'prev' button
            
            // We are at the beginning of the quiz, disable the prev button
            if( questionId == 0) {
                $("#" + prefix + "_prev").attr("disabled", "disabled");
            }
            if(!$.support.opacity)  ieFix();
        }
    };
    
    /**
     * If a question is already answered mark the user answers
     */
    function showMarkedAnswers () {
        
        // there is already an answer object for the specified question ID
        if( userAnswers[questionId] != undefined ) {
            
            if(questions[questionId].getType() == "order") {
                userAnswers[questionId].orderAnswers();
            } else {
                var answersArray =  userAnswers[ questionId].getUserSelection();
                
                for(var i = 0; i < answersArray.length; i++){
                    // mark all answers
                    userAnswers[ questionId].markAnswer(answersArray[i].id, "checked");
                }
            }
            
            
        }
        if( questionId <= questions.length - 1 && questionId > 0) {
            $("#" + prefix + "_prev").removeAttr("disabled");
        }
        
    };
    
    /*
     *
     * Walk throuht user answers and test them, save all results in the review object
     *
     * useranswers hold all user selected answers as answer objects
     */
    
    function evaluation() {
       
        $("#" + prefix + "_quiz_result").html("");
        $("#" + prefix + "_start").val( "submit").show("slow");
        localStorage.setItem("quiz_status","finish");
        
        // clear question, answer and hint
        $("#" + prefix + "_quiz_question").html("");
        $("#" + prefix + "_quiz_hint").html("");
        $("#" + prefix + "_quiz_answers").html("");
        $("#" + prefix + "_quiz_timer").html("");
        
        // stop timer
        clearTimeout(timerInstance);
        var html = "<div class='appypie_quiz_result'><h3>" +  resultscreentitle + "</h3> <p><br>";
        var correctAnswers = 0;
        // holds for every question exactly on rev object. This will hold all answers and the userselection
        var reviewObject = new Array();
        for(var z in questions)
        {
            console.log("8888888888888>>>>"+questions[z]);
            questions[z].correct=false;
        }
        
        
        for(var i in userAnswers) {
            var rev				= new Object();
            rev.question 		= questions[i].getQuestion();
            rev.type			= questions[i].getType();
            rev.answers			= new Object();
            
            var answers 		= userAnswers[i].getUserSelection();
            var correct 		= true;
            // choice questions first
            if(userAnswers[i].getType() != "order") {
                // walk through ALL answers, get them by question.getAnswers
                var allAnswers = questions[i].getAnswers();
                for( var n in allAnswers) {
                    var savedInReview = false;
                    // check for every user selected answer
                    for( var a in answers) {
                        if(answers[a].dbId == allAnswers[n].dbId) {
                            // the user has selected this answer, save it to rev object
                            corr = (allAnswers[n].correctAnswer == "true") ? true : false;
                            rev.answers[n]= {text:allAnswers[n].answer, correct:corr, userselection:true};
                            // remember that it is saved already
                            savedInReview = true;
                        }
                        // if one of the possible answers is wrong, mark question completly as wrong
						  if(answers[a].correctAnswer == "false" || answers[a].correctAnswer == "") correct = false;
                    }
                    // if it is not saved already:
                    if(savedInReview == false) {
                        corr = (allAnswers[n].correctAnswer == "true") ? true : false;
                        rev.answers[n]= {text:allAnswers[n].answer, correct:corr, userselection:false};
                    }
                }
                //console.log("correct ans---->"+correct);
            } else {
                // order question:
                /*
                 * We generated id's before we shuffled the answers, so correct order is 0,1,2,3 ...
                 * so increment after testing dbId ( which holds the correct order position) the var test
                 */
                var test = 0;
                for( var a in answers) {
                    if( answers[a].dbId != test) {
                        correct = false;
                    };
                    test++;
                }
                // the answers in the original answer object are in the correct order
                var allAnswers = questions[i].getAnswers();
                allAnswers = allAnswers.sort(sortById);
                for(var a = 0; a < allAnswers.length; a++) {
                    rev.answers[a]= {text:allAnswers[a].answer, correct:true};
                }
            }
            //console.log("correct ans---->"+correct);
            
            questions[i].correct = correct;
            console.log("evaluation()--->questions[i].correct--->"+questions[i].correct);
            reviewObject.push(rev);
            if(correct == true) correctAnswers++;
        }
        /*
         // Not all questions are answered?
         if(questions.length > userAnswers.length) {
         for(var n = userAnswers.length; n < questions.length; n++) {
         saveCorrectStatus(questions[n].getQId(), false);
         var answers 		= answerList[questions[n].getQId()];
         var rev				= new Object();
         rev.question 		= questions[n].getQuestion();
         rev.type 			= getQuestionType(questions[n].getQId());
         rev.answers			= new Object();
         for( var a in answers) {
         var correct = (rev.type == "choice") ? (answers[a].correct==1)? true : false : true;
         rev.answers[a]= {text:answers[a].text, correct:correct, userselection:false};
         }
         reviewObject.push(rev);
         }
         }
         */
        var viewData = {
            
        };
    
        var incorrectAnsCount=0;
        var correctAnsCount=0;
        var skipedAnsCount=0;
        var notAttemptAnsCount=0;
        for(var k=0;k<questions.length;k++)
        {
            
            if(k<currQuestionForCal)
            {
                var skipQueIndex = (skipQueArray.indexOf(parseInt(k)) > -1);
                if(skipQueIndex)
                {
                    skipedAnsCount++;
                    viewData["ques"+(k+1)] = "2";
                }
                else
                {
                    if(questions[k].correct==true)
                    {
                        correctAnsCount++;
                        viewData["ques"+(k+1)] = "1";
                    }
                    else
                    {
                        incorrectAnsCount++;
                        viewData["ques"+(k+1)] = "0";
                    }
                }
            }
            else
            {
                notAttemptAnsCount++;
                viewData["ques"+(k+1)] = "3";
            }
        }
       
        
        viewData=JSON.stringify(viewData);
		
        //var percent = (Math.floor(correctAnswers /  questions.length * 1000)) / 10;
       // localStorage.setItem("quiz_data",viewData);
       // localStorage.setItem("percent",percent);
	      var percent=0;
               if(correctAnsCount>0)
               {
                percent = (Math.floor(correctAnsCount /  questions.length * 1000)) / 10;
               }

               localStorage.setItem("quiz_data",viewData);
               localStorage.setItem("percent",percent);
        
        
//        var skipedAnsCount=(questions.length-currQuestionForCal)+skipQueArray.length;
//        var incorrectAnsCount=(questions.length-correctAnswers-skipedAnsCount);        
//        html += 'Questions Asked: '+questions.length+'<br> Questions Correct: '+ correctAnswers+'<br> Questions Incorrect:'+incorrectAnsCount+'<br> Questions Skiped:'+skipedAnsCount+'<br><br>Final Score: '+ percent + " % " +  resultscreenresultline + "<img src='images/share_btn.png' onclick='share_score();' data-role='none' style='float:right; height:30px;'><br> Time Elapsed: " + fmtTime(elapsed) + " </p>";
        
//        var questionsNotAttempt=10;
//        var incorrectAnsCount=(questions.length-(correctAnswers+skipQueArray.length));
//        var skipedAnsCount=questions.length-incorrectAnsCount;
        html += 'Questions Asked: '+questions.length+'<br> Questions Correct: '+ correctAnsCount+'<br> Questions Incorrect:'+incorrectAnsCount+'<br> Questions Skiped:'+skipedAnsCount+'<br> Questions Not Attempt:'+notAttemptAnsCount+'<br><br>Final Score: '+ percent + " % " +  resultscreenresultline + "<img src='images/share_btn.png' onclick='share_score();' data-role='none' style='float:right; height:30px;'><br> Time Elapsed: " + fmtTime(elapsed) + " </p>";
        
        // new to 2.0
        // If passing score is reached, make some special things
        if(percent >= passingScore) {
            // write cookie with a unique token
            // this cookie will be deleted by the cert page
            var unique = getToken(36);
            setCookie('SQuiz', unique + "|" + percent, 1);
            //html += "<p><a href='certification.html?token=" + unique + "' >" + certificateLink + "</a></p>";
        } else {
            html += "<p>Sorry, the passing score was: " + passingScore +" %</p>";
        }
        html +="<br> <span><small style='text-align:right; display:block; font-style:italic'>*Q 1 = Question 1 and so-on</small></span></div>";
        $("#" + prefix + "_quiz_question").html(html);
        if(sessionStorage.getItem("quiz_show")=="0")
        {
            $("#" + prefix + "_start").val( "Start the quiz").show("slow");
            localStorage.setItem("quiz_status","start");
            //---------------Ishan code/////////////////////////
            var quiz_data=localStorage.getItem("quiz_data");
            var score=localStorage.getItem("percent");
            var wsUrl = "http://"+window.localStorage.getItem("reseller")+"/services/utility-soap#addQuizTest";
            var quiz_name=sessionStorage.getItem("quiz_name");
            var quizPageId=sessionStorage.getItem("quizPageId");
            console.log(localStorage.getItem("applicationID"));
            if(!window.localStorage.getItem("fooduserid"))
            {
                window.localStorage.setItem("fooduserid","");
            }
            
            var soapRequest =
            '<?xml version="1.0" encoding="utf-8"?>'+
		    	     '<soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">'+
		    	     '<soap:Body>'+
		    	     '<addQuizTest xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/utility-soap#addQuizTest/\">'+
		    	     '<appId>'+localStorage.getItem("applicationID")+'</appId>'+
            '<deviceId>'+localStorage.getItem("deviceUUID")+'</deviceId>'+
            '<quizData>'+quiz_data+'</quizData>'+
            '<quizName>'+quiz_name+'</quizName>'+
            '<formPageId>'+quizPageId+'</formPageId>'+
            '<score>'+score+'</score>'+
            '<email>'+window.localStorage.getItem("fooduserid")+'</email>'+
            '<name>'+""+'</name>'+
		    	     '</addQuizTest>'+
		    	     '</soap:Body>'+
		    	     '</soap:Envelope>';
            
            console.log("soapRequest="+soapRequest);
            
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
		    		        xmlDoc = $.parseXML( req.responseText );
		    		        $xml = $( xmlDoc );
		    		        $intRate = $xml.find("return").text();
		    		        var a=$intRate;
                localStorage.setItem("quiz_status","start");
            }
            function processError(data,status,req) {
               // alert("fail");
                navigator.notification.alert(
                                             'Please Try Again..',
                                             alertDismissed,
                                             'Network Connection Error',
                                             'OK'
                                             );

            }
            
            //-------------------------------------------------
        }
        
        review(reviewObject);
        userAnswers.length=0;
        skipQueArray.splice(0,skipQueArray.length);
    };
    /**
     * Show all answers in the _SQuiz2_answers div with the result
     */
    function review (reviewObject) {

        var i = 0;
        $("#" + prefix + "_quiz_answers").hide();
        $("#" + prefix + "_quiz_answers").fadeIn();

        for(i in questions) {
            var correct = questions[i].correct;
            console.log("review()--->questions[i].correct--->"+questions[i].correct);
            var qNumber = i;

                var bgImageClass;
            
           
            if(i<currQuestionForCal)
            {
                var skipQueIndex = (skipQueArray.indexOf(parseInt(i)) > -1);
                if(skipQueIndex)
                {
                    bgImageClass="SQuiz2_skip";
                }
                else
                {
                    bgImageClass = (correct == true) ? "SQuiz2_correct" : "SQuiz2_wrong";
                }
            }
            else
            {
                bgImageClass="SQuiz2_na";
            }
            
            
            var reviewItem = jQuery("<div id='rev" + i + "'>")
            .text("Q "+parseInt(++qNumber))
            .addClass(bgImageClass).addClass("SQuiz2_reviewdiv")
            .appendTo($("#" + prefix + "_quiz_answers"))
            .click(function(e) {
                   var id = jQuery(this).attr("id").replace("rev", "");
                   jQuery("#" + prefix + "_SQuiz2_overlay").css({
                                                                display:'block',
                                                                top:e.pageY + 15,
                                                                left:e.pageX + 15
                                                                }).html( getReviewContent(reviewObject[id])).click(function(){
                                                                                                                   jQuery(this).fadeOut('fast');
                                                                                                                   });
                   });
        };
        
        // real height is only in hide mode available. So fix the wrapper height and after that
        // show the reviews
        fixWrapperHeight(false);
        jQuery("#" + prefix + "_SQuiz2_answers").show("slow");
        jQuery("#" + prefix + "_SQuiz2_footer").show("slow");
        
    };
    
    /**
     * This function gets the complete review object for a particular
     * question and builds the html code for the review popup
     * @param rev Object
     */
    function getReviewContent (rev) {
        var html = "";
        html += "<p>" + rev.question + "</p>";
        for(var i in rev.answers) {
            var classes = "";
            if( rev.type == "choice") {
                if(rev.answers[i].userselection == true && rev.answers[i].correct == true) {
                    classes = "SQuiz2_correct SQuiz2_review_answer_correct";
                } else if (rev.answers[i].userselection == true && rev.answers[i].correct == false) {
                    classes = "SQuiz2_wrong SQuiz2_review_answer_userselection";
                } else if (rev.answers[i].userselection == false && rev.answers[i].correct == true) {
                    classes = "SQuiz2_correct";
                }
            } else if (rev.type == "order") {
                // all answers are comes up in the correct order. We have no information
                // about the userselection / user order
                classes = "SQuiz2_review_answer_correct";
            }
            var no = Number(i) + 1;
            html += "<span class='SQuiz2_review_answer " + classes + "'>" + no + ". " + rev.answers[i].text + "</span><br>";
            
        }
        
        return html;
    }
    
    // ===============================================================================================
    
    function sortById(a, b){
        var aId = Number(a.dbId);
        var biD = Number(b.dbId);
        return ((aId < biD) ? -1 : ((aId > biD) ? 1 : 0));
    }
    
    function timerCallback () {
        if(sessionStorage.getItem('timerNew'))
        {
            console.log("timerCallback()--->timeCounter--->"+timeCounter);
            console.log("timerCallback()--->elapsed--->"+elapsed);
            var exit = false;
            timeCounter += 1000;
            elapsed = Math.floor(timeCounter / 100) / 10;
            var currQuestion = questionId + 1;
            currQuestionForCal=currQuestion;
            if(timeLimit > 0) { // we use the time limit
                
                $("#" + prefix + "_quiz_timer").html("<span class='questioncounter'>Question: " + currQuestion + "/" + questions.length + "</span> Time Left: - " + fmtTime(timeLimit - elapsed));
                $("#" + prefix + "_quiz_timer").css("color", "#666");
                // make the clock red if last 10 seconds begin
                if(timeLimit - elapsed < 11) {
                    $("#" + prefix + "_quiz_timer").css("color", "#F00");
                }
                if(timeLimit - elapsed <= 0) { // this is the end
                    exit = true;
                    
                    // the previous button must be hidden here because quiz is at the end
                    //$("#" + prefix + "_prev").hide("slow");
                    endQuiz();
                }
            } else {  // show only the elapsed time
                $("#" + prefix + "_quiz_timer").html("<span class='questioncounter'>Question: " + currQuestion + "/" + questions.length + "</span> Time - " + fmtTime(elapsed));
                $("#" + prefix + "_quiz_timer").css("color", "#666");
            }
            // calculate the difference between the interval and the real elapsed interval
            var diff = (new Date().getTime() - startTime) - timeCounter;
            if(exit == false)
            {
                timerInstance = setTimeout(timerCallback, (1000 - diff));
                // sessionStorage.getItem("timerInstance",timerInstance);
            }
        }
        else
        {
            timeCounter=0;
        }
    };
    
    function fmtTime (value) {
        var hours = Math.floor(value / 3600);
        var minutes = Math.floor((value - (hours * 3600)) / 60);
        if(minutes < 10) minutes = "0" + minutes;
        var seconds = value % 60;
        if(seconds < 10) seconds = "0" + seconds;
        return hours + ":" + minutes +":"+ seconds;
    };
    
    
    
    
    //  ============  gui manipulation  ======================================
    
    function nextButtonDefault () {
       
          localStorage.setItem('radioVlue',null);
        $("#" + prefix + "_next").unbind('click');
        $("#" + prefix + "_next").val( submitBtntxt);
        $("#" + prefix + "_next").click(function () {
                                        
                                        imidiateAns=parseInt(sessionStorage.getItem("immidiateAns"));
                                        if(imidiateAns)
                                        {
                                        if(isTrue)
                                        {
                                        $("#" + prefix + "_next").val( nextbutton);
                                        isTrue=false;
                                        evaluationWithQuestion();
                                        }
                                        else
                                        {
                                        $("#" + prefix + "_next").val( submitBtntxt);
                                        isTrue=true;
                                        questionId++;
                                        go();
                                        }
                                        }
                                        else
                                        {
                                        $("#" + prefix + "_skip").hide();
                                        $("#" + prefix + "_next").val( submitBtntxt);
                                        isTrue=true;
                                        questionId++;
                                        go();
                                        }
                                        
                                        
                                        });
        //console.log("nextButtonDefault");
        
    };
    
    function nextButtonToFinishButton () {
        
        imidiateAns=parseInt(sessionStorage.getItem("immidiateAns"));
        if(imidiateAns)
        {
                $("#" + prefix + "_next").val( finishbutton);
                isTrue=true;
                //$("#" + prefix + "_skip").hide();
                $("#" + prefix + "_next").unbind('click');
                $("#" + prefix + "_next").val(submitBtntxt).click(function() {
                                                                    evaluationWithQuestion();
                                                                  $("#" + prefix + "_skip").hide();
                                                                  $("#" + prefix + "_next").unbind('click');
                                                                  $("#" + prefix + "_next").val(finishbutton).click(function() {
                                                                                                                    // remove previous button
                                                                                                                    $("#" + prefix + "_prev").hide("slow");
                                                                                                                    $(this).hide("slow");
                                                                                                                    //$("#" + prefix + "_quiz_hint").html( welcometext);
                                                                                                                    //$("#" + prefix + "_start").val( startbutton).show("slow");
                                                                                                                    //$("#" + prefix + "_start").val( "submit").show("slow");
//                                                                                                                sessionStorage.setItem("quiz_show",1);
                                                                                                                    evaluation();
                                                                                                                    });

                                                                  });
           
           
        }
        else
        {
        //console.log("nextButtonToFinishButton");
         $("#" + prefix + "_skip").hide();
        $("#" + prefix + "_next").unbind('click');
        $("#" + prefix + "_next").val(finishbutton).click(function() {
                                                          // remove previous button
                                                          $("#" + prefix + "_prev").hide("slow");
                                                          
                                                          $(this).hide("slow");
                                                          //$("#" + prefix + "_quiz_hint").html( welcometext);
                                                          //$("#" + prefix + "_start").val( startbutton).show("slow");
                                                          //$("#" + prefix + "_start").val( "submit").show("slow");
                                                         //  sessionStorage.setItem("quiz_show",1);
                                                          evaluation();
                                                          });
        }
    };
    
    function ieFix() {
        if($("#" + prefix + "_next").attr("disabled") == "disabled") {
            $("#" + prefix + "_next").addClass("iefix");
        } else {
            $("#" + prefix + "_next").removeClass("iefix");
        }
        if($("#" + prefix + "_prev").attr("disabled") == "disabled") {
            $("#" + prefix + "_prev").addClass("iefix");
        } else {
            $("#" + prefix + "_prev").removeClass("iefix");
        }
    };
    
    function fixWrapperHeight(markAnswers) {
        // moved to here from go()
        // we must wait until all answers are rendered befor we can mark
        // already answered questions.
        if(markAnswers != false) showMarkedAnswers ();
        // a this point the answers are rendered so far
        var targetHeight = 0;
        $("#" + prefix + "_quiz_wrapper").children().each(function () {
                                                          targetHeight += $(this).height();
                                                          });
        targetHeight += $("#" + prefix + "_quiz_navigation").height();
        $("#" + prefix + "_quiz_wrapper").stop().animate({
                                                         height:targetHeight
                                                         }, function () {
                                                         jQuery('.SQuiz2_qimage').stop().animate({opacity:1});
                                                         });
    };
    //  =============  listener  and service  ================================
    function questionListener(event, ans) {
        //console.log("questionListener");
        if(event == "ready") {
            
            $("#" + prefix + "_next").removeAttr("disabled");
            userAnswers[ questionId] = ans;
            debug();
            // if he answered the last question change next button label and the callback
            if( questionId == questions.length - 1) {
                /*
                 $("#" + prefix + "_next").val(finishbutton).click(function() {
                 // remove previous button
                 $("#" + prefix + "_prev").hide("slow");
                 $(this).hide("slow");
                 //$("#" + prefix + "_quiz_hint").html( welcometext);
                 $("#" + prefix + "_start").val( startbutton).show("slow");
                 evaluation();
                 });*/
                nextButtonToFinishButton();
            }
        } else {
            $("#" + prefix + "_next").attr("disabled", "disabled");
        }
        if(!$.support.opacity)  ieFix();
    };
    
    function endQuiz () {
        //  alert("s");
        // clear question, answer and hint
        $("#" + prefix + "_quiz_question").html("");
        $("#" + prefix + "_quiz_hint").html("");
        $("#" + prefix + "_quiz_answers").html("");
        $("#" + prefix + "_quiz_result").html("");
        // change the button state
        $("#" + prefix + "_next").removeAttr("disabled");
        $("#" + prefix + "_next").unbind('click');
        if(!$.support.opacity)  ieFix();
        
        $("#" + prefix + "_skip").hide();
        // change NEXT button to FINISH button
        $("#" + prefix + "_next").val( finishbutton).click(function() {
                                                           // remove previous button
                                                           $("#" + prefix + "_prev").hide("slow");
                                                           $(this).hide("slow");
                                                           //$("#" + prefix + "_quiz_hint").html( welcometext);
                                                           $("#" + prefix + "_start").val( startbutton).show("slow");
                                                           // sessionStorage.setItem("quiz_show",1);
                                                           evaluation();
                                                           });
    };
    
    function getToken (length) {
        return  Math.random().toString(length).substr(2)
    }
    
    
    function getCookie(c_name) {
        var c_value = document.cookie;
        var c_start = c_value.indexOf(" " + c_name + "=");
        if (c_start == -1) {
            c_start = c_value.indexOf(c_name + "=");
        }
        if (c_start == -1) {
            c_value = null;
        } else {
            c_start = c_value.indexOf("=", c_start) + 1;
            var c_end = c_value.indexOf(";", c_start);
            if (c_end == -1) {
                c_end = c_value.length;
            }
            c_value = unescape(c_value.substring(c_start, c_end));
        }
        return c_value;
    }
    
    function setCookie(c_name,value,exdays)	{
        var exdate=new Date();
        exdate.setDate(exdate.getDate() + exdays);
        var c_value=escape(value) + ((exdays==null) ? "" : "; expires="+exdate.toUTCString());
        document.cookie=c_name + "=" + c_value;
    }
    
    /**
     *
     * Look up the question type as saved in questions[i]
     * (used in performEvaluation())
     *
     * @param qId
     * @returns
     */
    function getQuestionType(qId) {
        count = questions.length;
        for(var i = 0; i < count;i++) {
            if (questions[i].getQId() == qId) {
                return questions[i].getType();
            }
        }
    }
    
    
    function debug(message) {
        /*
         var answerNumber = questionId;
         var html = "<p>Useranswers: " +  userAnswers.length + "<br>";
         for(var i in  userAnswers) {
         html += "Question id: " + i + "<br>";
         html += "User selected:<br>";
         // userAnswers holds the complete uanswer obj
         // obj.getUserSelection() holds user selected answers
         //console.log( userAnswers[i].getUserSelection().length);
         for( var a in  userAnswers[i].getUserSelection()) {
         html += "a: " +  userAnswers[i].getUserSelection()[a].id + "<br>";
         }
         }
         html +="</p>";
         html += "<p>count: " +  questions.length + "<br>";
         html += "answered: " + (answerNumber + 1) + "</p>";
         $("#debug").html(html);
         */
        $("#debug").html(message);
    };
};

function validateEmailForQuiz(email) { 
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
} 


var UniqueRandom = { NumHistory: new Array(), generate: function(maxNum) {
	
    var current = Math.round(Math.random()*(maxNum-1));
    if (maxNum > 1 && this.NumHistory.length > 0) {
        if (this.NumHistory.length != maxNum) {
            while($.inArray(current, this.NumHistory) != -1) { current = Math.round(Math.random()*(maxNum-1)); }
            this.NumHistory.push(current);
            return current;
        } else {
            //unique numbers done, continue outputting random numbers
            //alert('done outputting unique numbers, now just returning random number');
        
        	this.NumHistory.length=0;
        	//number2=1;
        	this.NumHistory.push(current);
        	
        	return current;
        }
    } else {
        //first time only
        this.NumHistory.push(current);
        return current;
    }
}
};
