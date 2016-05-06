/**
 * Answer Class
 * 
 * Events:
 * ready: all possible answers are checked
 * answerCoountError: to much answers are checked
 * 
 * Version 2.2
 * + added arrange questions
 * 
 */

var Answers = function (xml, classPrefix, eventListener, showHint) {
	var me = this;
	var type="";				// multiple choice (check) or single choice (radio)
	var countCorrect = 0;		// how much answers are correct
	var content = xml;			// all answers as xml
	var parent = $("#" + classPrefix + "_quiz_answers");
	var answerObjects = new Array();		// holds all answer DOM objects
	var userSelection = new Array(); 		// holds all user selected answers
	var eventListener;

	// determine if it is a single or multiple choice question
	// and what type the question is of
	xml.each(function() {
		var a = $(this);
		if(a.attr("correct") == "true") countCorrect++;
	});
	if(countCorrect == 1) {
		type = "radio";
	} else if(countCorrect > 1) {
		type = "check";
	} else {
		type = "order";	
	} /*else {
		alert("Error in question: " + "\n" + content.parent().first().text() + "\n: no correct choice marked");
	}*/
		
	/**
	 * Is called by Question class with the answer xml data
	 * Removes the old answers if present and calls after that
	 * displayNewAnswers to show the brand new answers
	 * @public
	 * 
	 */
	this.showAnswers = function (correctWrapperHeightListener) {
		// clear the answer section
		var count = $("#" + classPrefix + "_quiz_answers > div").size();
		if(count == 0) count = jQuery("#" + classPrefix + "_quiz_answers > ul").size();
		if(count == 0) {
			displayNewAnswers(correctWrapperHeightListener);	
		} else {
		
			$("#" + classPrefix + "_quiz_answers").children().each(function(){
				
				$(this).fadeOut(function() {
					count--;
					$("#" + classPrefix + "_quiz_answers").empty();
					if(count == 0) {
						displayNewAnswers(correctWrapperHeightListener);	
					}
				});
			});
		}
	};
	
    function displayNewAnswers  (correctWrapperHeightListener) {
        // initialize answerArray
        answerObjects = new Array();
        //var answerId = 0;
        var aDiv = undefined;
        
        // first give them all a id
        content.each(function(k,v) {
                     var a = $(this);
                     $("<id>" + k + "</id>").appendTo(a);
                     });
        
        // if this is an order question we must randomize the order of answers
        if(type == "order") {
            content = arrayShuffle(content);
            var target = parent;
            aDiv = jQuery("<ul id='foo'>").addClass("SQuiz2_sortable").appendTo(target);
            
            var list = document.getElementById("foo");
            new Sortable(list, {
                         
                         onEnd: function(){
                         answerChanged();
                         }
                         });
            
        }
        var screenHeight=$('.ui-mobile').outerHeight(true);
        var screenWidth=$('.ui-mobile').outerWidth(true);
        
        content.each(function() {
                     var a = $(this);
                     var id = Math.floor(Math.random() * 1000000);
                     if(type == "radio" || type == "check") {
                     aDiv = $("<div>").appendTo(parent).addClass(classPrefix + "_quiz_awrapper").hide();
                     // add the answer (as formtype or quiztype)
                     
                     var image=a.find("ansImgUrl").text();
                     var text=a.contents().eq(0).text();
                     
                     if(text=="")
                     {
                     text=".";
                     }
                     var answerObject = $("<div>",{id:id}).addClass(classPrefix + "_quiz_answer").appendTo(aDiv).scheck({
                                                                                                                        type: type,
                                                                                                                        label: text,
                                                                                                                        onValueChanged: answerChanged
                                                                                                                        });
                     
                     
                     if((image!="undefined")&&(image!=""))
                     {
                     //$("<br><img src='"+image+"' width='"+screenWidth+"'  align='middle' />").addClass(classPrefix + "_quiz_answer").appendTo(aDiv);
                    $("<div class='quiz_images'><img class='SQuiz2_qimage'  src='" + image + "' /></div>").appendTo(aDiv);
					}
                     
                     }
                     else if(type == "order")
                     {
                     var image=a.find("ansImgUrl").text();
                     var text=a.contents().eq(0).text();
                     if(text=="")
                     {
                     text=".";
                     }
                     var answerObject = jQuery("<li>",{id:id}).addClass("SQuiz2_sortable_item").appendTo(aDiv).scheck({
                                                                                                                      type: type,
                                                                                                                      label:text,
                                                                                                                      onValueChanged: answerChanged
                                                                                                                      });
                     
                     if((image!="undefined")&&(image!=""))
                     {
                    // $("<br><img src='"+image+"' width='"+screenWidth+"'  align='middle' />").addClass(classPrefix + "_quiz_answer").appendTo(aDiv);
                    $("<div class='quiz_images'><img class='SQuiz2_qimage'  src='" + image + "' /></div>").appendTo(aDiv);
					}
                     /*jQuery(".SQuiz2_sortable").sortable({
                      stop: function () {
                      answerChanged();
                      }
                      });*/
                     }
                     // save the position of this answer and the correct value in the object
                     answerObject.id = id;
                     answerObject.dbId = a.contents().eq(1).text(); // save the generated id in object to use for review and order questions
                     answerObject.correctAnswer = a.attr("correct");
                     answerObject.answer =  a.contents().eq(0).text();
                     answerObjects.push(answerObject);
                     answerObjectsTemp.push(answerObject);
                     
                     
                     //answerId++;
                     aDiv.show("slow", function() {
                               /** call the height correction of the wrapper **/
                               correctWrapperHeightListener();
                               });
                     });
    };
	
	/**
	 * @public
	 * @param answerId: Number 	the answer id to be marked
	 * @param markType : String	checked, wrong, right
	 */
	this.markAnswer = function(answerId, markType) {
		switch(markType) {
		case "checked":	// if question is already answered and this answer is checked in quizmode
			$(answerObjects[answerId]).scheck("setValue", true, true);
			break;		
		}
		
	};
	
	/* inc case of going back to an already answered question order them 
	 * 
	 */
	this.orderAnswers = function () {
		jQuery(".SQuiz2_sortable").empty();
		for(var i in userSelection) {
			userSelection[i].appendTo(jQuery(".SQuiz2_sortable"));
		}
	};
	
	
	/**
	 * The listener function for the answer buttons change event
	 * if showHint is true, activate answer button when the count of answers are
	 * correct, if it is false, activate also on single answer choice
	 * @param event
	 * @param param
	
	function answerChanged(eventObj) {
		userSelection = new Array();
		// change all other radio buttons to unchecked
		if(type == "radio") {
			for( i in answerObjects) {
				if(answerObjects[i].attr("id") != $(this).attr("id")) {
					$(answerObjects[i]).scheck('setValue', false);
				} else if(answerObjects[i].attr("id") == $(this).attr("id")) {
					userSelection.push(answerObjects[i]);
				}
			}
			// fire the ready event to notify the question class that the question is completly answered
			eventListener('ready', me);
		} else {
			var count = 0;
			for( i in answerObjects) {
				if($(answerObjects[i]).scheck('getValue') == "checked") {
					userSelection.push(answerObjects[i]);
					count++;
				}
			}
			if(count == countCorrect) {
				eventListener('ready', me);
			} else if(count != countCorrect) {
				eventListener('answerCountError', me);
			}
			// activate next button in any case if hints are off
			if(showHint == false) eventListener('ready', me);
		}
	}
	 */
	
    function answerChanged(eventObj) {
        userSelection = new Array();
        // change all other radio buttons to unchecked
        if(type == "radio") {
            
            for( i in answerObjects)
            {
                if(answerObjects[i].attr("id") != jQuery(this).attr("id"))
                {//alert(1);
                    jQuery(answerObjects[i]).scheck('setValue', false);
                }
                else if(answerObjects[i].attr("id") == jQuery(this).attr("id"))
                {
                    
                    if(localStorage.getItem('radioVlue')==i)
                    {
                        
                        localStorage.setItem('radioVlue',null);
                        eventListener('answerCountError', me);
                        //                        if($("#exam_skip").is(":visible"))
                        //                        {
                        //                            $("#exam_skip").hide();
                        //                        }
                        //                        else
                        //                        {
                        //                            $("#exam_skip").show();
                        //                        }
                    }
                    else
                    {
                        //   $("#exam_skip").hide();
                        localStorage.setItem('radioVlue',i);
                        eventListener('ready', me);
                        
                    }
                    
                    userSelection.push(answerObjects[i]);
                }
                
            }
            
            //                {
            //					userSelection.push(answerObjects[i]);
            //				}
            //			}
            //			// fire the ready event to notify the question class that the question is completly answered
            //			eventListener('ready', me);
        } else if(type == "check") {
            var count = 0;
            for( i in answerObjects) {
                if(jQuery(answerObjects[i]).scheck('getValue') == "checked") {
                    userSelection.push(answerObjects[i]);
                    count++;
                }
            }
            if(count == countCorrect) {
                eventListener('ready', me);
            } else if(count != countCorrect) {
                eventListener('answerCountError', me);
            }
            else if(count > countCorrect)
            {
                
                eventListener('answerMoreThenCorrect', me);
                
            }
            // activate next button in any case if hints are off
            if(showHint == false) eventListener('ready', me);
            
            if(count==0)
            {
                eventListener('answerCountError', me);
            }
            
            
        } else if(type == "order") {
            jQuery(".SQuiz2_sortable li").each(function () {
                                               // we have to save the answerObject and not the DOM object
                                               userSelection.push(getAnswerObject(jQuery(this).attr("id")));
                                               });
            eventListener('ready', me);
        }
        
        
        
    }
	
	// ==============  SERVICE  =================================
	function getAnswerObject (id) {
		for(i in answerObjects) {
			if(answerObjects[i].id == id) {
				return answerObjects[i];
			}
		}
	}
	
	/**
	 * Shuffle the answer array for order questions
	 * @param arr
	 * @returns
	 */
	function arrayShuffle(arr){
		var tmp, rand;
		for(var i =0; i < arr.length; i++){
			rand = Math.floor(Math.random() * arr.length);
			tmp = arr[i]; 
			arr[i] = arr[rand]; 
			arr[rand] = tmp;
		}
		return arr;
	}

	
	//  =============  getter  //  setter  ======================
	this.getType = function() {return type; };
	this.getCountCorrect = function () { return countCorrect; };
	this.getUserSelection = function () { return userSelection; };	// the answerObjects the user selected
	this.getAnswers = function () { return answerObjects; };		// all answerObjects
	/**
	 * @public
	 * Returns the number of points earned by the candidate with this question
	 */
	this.getPoints = function () {
		
	};
	this.toString = function () {
		this.getType() + "*";
	};
	
	this.getCorrectAnswer = function () {
		for( i in answerObjects) {
			if(answerObjects[i].correctAnswer == "true") {
				return answerObjects[i].id;
			}
		}
	}
	
};