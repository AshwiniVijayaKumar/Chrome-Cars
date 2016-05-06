/**
 * Question Class
 * For every question a question object is build which is hold in an array
 * in the Quiz engine.
 * 
 * Every question contains an Answer object which holds all the answers
 * as scheck or squiz button. 
 * Answer object fires 'ready' event if answer count is correct
 * Answer object fires 'answerCountError' event if to much answers are checked
 * Answer object can return the type (radio or check) and the number of correct answers
 * 
 * 
 * Version 2.2
 * + order questions added
 * 
 */


// Find all YouTube videos
var $allVideos = $("iframe[src^='//www.youtube.com']"),

    // The element that is fluid width
    $fluidEl = $("body");

// Figure out and save aspect ratio for each video
$allVideos.each(function() {

  $(this)
    .data('aspectRatio', this.height / this.width)

    // and remove the hard coded width/height
    .removeAttr('height')
    .removeAttr('width');

});

// When the window is resized
$(window).resize(function() {

  var newWidth = $fluidEl.width();

  // Resize all videos according to their own aspect ratio
  $allVideos.each(function() {

    var $el = $(this);
    $el
      .width(newWidth)
      .height(newWidth * $el.data('aspectRatio'));

  });

// Kick off one resize to fix all videos on page load
}).resize();

var number2=1;
var Question = function (options) {
	
	var mode = (options.mode == undefined) ? "quiz" : "review";
	var question = options.question;
	var image = options.image;
	var video = options.video;
	var audio=	options.audio;
	var mediaType=options.mediaType;
	var prefix = options.prefix;
	var answers = "";									// holds the Answer object with all answers
	//var result = new Array();							// holds question number
	var qListener = options.questionListener;			// function that act as listener in SQuiz for zthis question
	var correctWrapperHeightListener = options.fixHeightListener;
	var singlechoicehint = options.singlechoicehint;
	var multiplechoicehint = options.multiplechoicehint;
	var orderhint = options.orderhint;
	var number = options.number;
	var showHint = options.showHint;					// 2.0 = if this is true, activate next button on first answer
	var type = "";										// the question type for now choice or order
	
	this.createAnswers = function (xml) {
		answers = new Answers(xml, prefix, listener, showHint);
		type = (answers.getType() == "order") ? "order" : "choice";
	};
	
	/**
	 * @public
	 * Listens to the answers events ready, answerCountError to switch the navigation buttons and
	 * save the answer status in the result array.
	 * @param event : String event type
	 * @param obj : the Answer Class instance reference
	 */
	function listener(event, obj) {
		if(event == "ready") {
			qListener(event,obj );
		} else if (event == "answerCountError") {
			
			
		
			
		
			
			qListener(event,obj);
		}
	};
	
	/**
	 * @public
	 * Show the question and answers
	 */
	/*var videoSplitArray;
	var newViedoID;
	var youtubeFinalUrl;
	if(video != "undefined" && video != "" && video.length > 4)
		{
		
		 videoSplitArray=video.split("/");
		 newViedoID=videoSplitArray[videoSplitArray.length-1];
		 youtubeFinalUrl='https://www.youtube.com/embed/'+newViedoID;
		}*/
	
	
	
	this.showQuestion = function ( questionId) {
		var imageTag = (image != "undefined" && image != "" && image.length > 4) ?  "<div class='quiz_images'><img class='SQuiz2_qimage'  src='" + image + "' /></div>" : "";
        
		var videoTag = (video != "undefined" && video != "" && video.length > 4) ?  "<div class='videoWrapper'><iframe width='320px' height='230px' src='"+video+"' frameborder='0' allowfullscreen></iframe></div>" : "";
		
		var audioTag=(audio != "undefined" && audio != "" && audio.length > 4) ? "<audio id='player2' src='"+audio+"' type='audio/mp3' controls='controls'>		</audio>" : "";
		
		var finalShowTag='';
		
		
		
		if(mediaType == "image")
			{
			
			finalShowTag=imageTag;
			}
		if(mediaType == "video")
		{
		
		finalShowTag=videoTag;
		}
		if(mediaType == "audio")
		{
		
		finalShowTag=audioTag;
		}
		
		
		

        if( sessionStorage.getItem("quizQuestionSequence")=="1"||parseInt(sessionStorage.getItem("quizQuestionSequence"))==1)
        {
//    		$("#" + prefix + "_quiz_question").html( "<p> <span class='question-number'>Question" + number2 + "</span> " + question + "</p><br>" + finalShowTag + '<br>');
//    		number2=number2+1;
                
                	$("#" + prefix + "_quiz_question").html( "<p> <span class='question-number'>Question" + (questionId+1) + "</span> " + question + "</p><br>" + finalShowTag + '<br>');
    		}
    	else{
    		
    		$("#" + prefix + "_quiz_question").html( "<p> <span class='question-number'>Question" + number + "</span> " + question + "</p><br>" + finalShowTag + '<br>');
    		
    	}   
		
		
		//number=number+1;
	//	$("#" + prefix + "_quiz_question").html("<p>" + number + ". " + question + "</p><br>" + imageTag + '<br><p>' + videoTag + '</p>'); //New Updated
		var correct = answers.getCountCorrect();
		var multihint = multiplechoicehint.replace("[$]", " " + correct + " ");
		var hint = "";
		switch(answers.getType()) {
		case "radio":
			hint = "<p>" + singlechoicehint + "</p>";
			break;
		case "check":
			hint = "<p>" + multihint + "</p>";
			break;
		case "order":
			hint = "<p>" + orderhint + "</p>";
			break;
		
		}
		
		//var hint = (answers.getType() == "radio") ? "<p>" + singlechoicehint + "</p>" : "<p>" + multihint + "</p>";
		$("#" + prefix + "_quiz_hint").html(hint);
		
		/** show answer and call fixing the height of the wrapper **/
		answers.showAnswers(correctWrapperHeightListener);
	};
	
	// ==============  getter  // setter  =========================
	
	this.getQuestion = function () { return question;};
	this.getType = function () { return type;};
	this.getAnswers = function() { return answers.getAnswers(); };
	this.getCorrectAnswer = function() { return answers.getCorrectAnswer(); };
	
};