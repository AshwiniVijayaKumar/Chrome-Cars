/////////////Apointment///////////////////
var appId = "";
var iinvalidddategg_ment = 'Invalid Date';
var Appointmentdateisnotalidgg_ment = 'Appointment date is not valid.';
var eerrorgg_ment = 'Error';
var pleaseselectadatebeforetimegg_ment = 'Please select a date before time.';
var appointmentcanttimeslot_ment = 'Appointment can\'t set between this time, Please choose another time slot.';
var appointmentcurrenttime_memt = 'Appointment can\'t set in current time, Please choose another time slot.';
var appointmentpasttime_ment = 'Appointment can\'t set in past time, Please choose future time slot.';
var dateblank_ment = '*Date field cannot be left blank';
var alert_ment = 'Alert';
var remark_ment ='*Remark field cannot be left blank';
var pleaseprovideavalidphonenumber_ment ='Please provide a valid phone number';
var enteravalidmailaaaddress_ment = 'Enter a valid E-mail address';
var nameblank_ment = '*Name field cannot be left blank';
var timeblank_ment = '*Time field cannot be left blank';



function getApointment(index) {
   
   if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }

	appId = window.localStorage.getItem("applicationID");
	xmlFileData = $.parseXML(window.sessionStorage.getItem("xml"));
	$xml = $(xmlFileData);
	var hellocalc = "";

	$appointmentIndex = $xml.find("appointment[indexval=" + index + "]");
	var appointmentHeading = $appointmentIndex.find("appointmentheader").text();
	var appointmentReqField = $appointmentIndex.find("fieldreqtext").text();
	var appointmentDateField = $appointmentIndex.find("appdate").text();
	var appointmentTimeField = $appointmentIndex.find("apptime").text();
	var appointmentNameField = $appointmentIndex.find("appname").text();
	var appointmentEmailField = $appointmentIndex.find("appemail").text();
	var appointmentPhoneField = $appointmentIndex.find("appphone").text();
	var appointmentRemarkField = $appointmentIndex.find("appremark").text();
	var appointmentConfBtnField = $appointmentIndex.find("appconfirm").text();

	if (window.innerWidth > window.innerHeight) {
		// alert("changes portrait");
		hellocalc = '<div id="appointmentIdClass" class="appointment"> <h2>'
				+ appointmentHeading
				+ '</h2> \
    <p>'
				+ appointmentReqField
				+ '</p> <div class="field"><label>'
				+ appointmentDateField
				+ '<sup>*</sup></label><input data-role="none" type="text" id="txtDate" class="clockpicker" onclick="show();" readonly /><a href="#" onclick="show();"><img src="images/calendar.png"  border="0"></a></div> \
    <div class="field"><label>'
				+ appointmentTimeField
				+ '<sup>*</sup></label> \
    <input data-role="none" type="text" id="txtTime" class="clockpicker" onclick="time()" readonly /><a href="#" onclick="time()"><img src="images/clock.png"  border="0"></a> \
    </div>\
    <div class="field"><label>'
				+ appointmentNameField
				+ '<sup>*</sup></label><input data-role="none" id="txtName" type="text"/></div> \
    <div class="field"><label>'
				+ appointmentEmailField
				+ '<sup>*</sup></label><input data-role="none" id="txtEmail" type="text"/></div> \
    <div class="field"><label>'
				+ appointmentPhoneField
				+ '<sup>*</sup></label><input data-role="none"  id="txtPhone" type="text"/></div> \
    <div class="field"><label>'
				+ appointmentRemarkField
				+ '<sup>*</sup></label><p><textarea id="txtRemark"></textarea></p></div> \
    <input data-role="none" type="submit" value="'
				+ appointmentConfBtnField
				+ '" onclick="postValueToServer();"></div>';
	} else {
		// alert("changes land");
		hellocalc = '<div id="appointmentIdClass"  class="appointment portrait"> <h2>'
				+ appointmentHeading
				+ '</h2> \
    <p>'
				+ appointmentReqField
				+ '</p> <div class="field"><label>'
				+ appointmentDateField
				+ '<sup>*</sup></label><input data-role="none" type="text" id="txtDate" class="clockpicker" onclick="show();" readonly /><a href="#" onclick="show();"><img src="images/calendar.png"  border="0"></a></div> \
    <div class="field"><label>'
				+ appointmentTimeField
				+ '<sup>*</sup></label> \
    <input data-role="none" type="text" id="txtTime" class="clockpicker" onclick="time()" readonly /><a href="#" onclick="time()"><img src="images/clock.png"  border="0"></a> \
    </div>\
    <div class="field"><label>'
				+ appointmentNameField
				+ '<sup>*</sup></label><input data-role="none" id="txtName" type="text"/></div> \
    <div class="field"><label>'
				+ appointmentEmailField
				+ '<sup>*</sup></label><input data-role="none" id="txtEmail" type="text"/></div> \
    <div class="field"><label>'
				+ appointmentPhoneField
				+ '<sup>*</sup></label><input data-role="none"  id="txtPhone" type="text"/></div> \
    <div class="field"><label>'
				+ appointmentRemarkField
				+ '<sup>*</sup></label><p><textarea id="txtRemark"></textarea></p></div> \
    <input data-role="none" type="submit" value="'
				+ appointmentConfBtnField
				+ '" onclick="postValueToServer();"></div>';
	}

	window.addEventListener('orientationchange',
			function handleAppOrientation() {
				if (window.innerWidth > window.innerHeight) {
					$("#appointmentIdClass").removeClass();
					$("#appointmentIdClass").addClass("appointment");
				} else {
					$("#appointmentIdClass").removeClass();
					$("#appointmentIdClass").addClass("appointment portrait");
				}
			}, false);

	appendHtml("<div class='page-text'>" + hellocalc + "</div>", '', 1);

	// setTimeout(function(){$('.appypie-loader').hide();},1000);
}

var ApointDate = "";
var ApointTime = "";
var dayNameIndex;/* new Appointment shedule */
var dateOfAppointment = '';/* new Appointment shedule */
function cb(date) {

	document.getElementById("txtTime").value = '';
	var dt = new Date(date);
	dayNameIndex = dt.getDay();/* new Appointment shedule */

	var todaydate = new Date();
	currentMonth = '' + (todaydate.getMonth() + 1), currentDay = ''
			+ todaydate.getDate(), currentYear = todaydate.getFullYear();
	if (currentMonth.length < 2) {
		currentMonth = '0' + currentMonth;
	}
	if (currentDay.length < 2) {
		currentDay = '0' + currentDay;
	}
	currentDate = [ currentMonth, currentDay, currentYear ].join('/');

	scheduleMonth = '' + (dt.getMonth() + 1), scheduleDay = '' + dt.getDate(),
			scheduleYear = dt.getFullYear();
	if (scheduleMonth.length < 2) {
		scheduleMonth = '0' + scheduleMonth;
	}
	if (scheduleDay.length < 2) {
		scheduleDay = '0' + scheduleDay;
	}
	scheduleDate = [ scheduleMonth, scheduleDay, scheduleYear ].join('/');
	// alert(scheduleDate);
	// alert(Date.parse(currentDate));
	if (Date.parse(scheduleDate) >= Date.parse(currentDate)) {

		// alert("ravi");
		dateOfAppointment = scheduleDate;
		document.getElementById("txtDate").value = scheduleDate;
		ApointDate = scheduleDate;

	} else {
		/* need to be converted to SA */

		alertPopUp(iinvalidddategg_ment, Appointmentdateisnotalidgg_ment);

		document.getElementById("txtDate").value = '';
	}

}

function timeDispley(time) {
	if (time != "cancel") {
		/* new Appointment shedule */
		if (dateOfAppointment.length == 0) {

			/* need to be converted to SA */
			alertPopUp(eerrorgg_ment, pleaseselectadatebeforetimegg_ment);
			return false;

		}
		var timeValue = time;
		if (appointmentShedule == 1) {
			/*
			 * alert(dayNameIndex); alert(appointmentStartTimeArray2);
			 * alert(appointmentEndTimeArray2);
			 */
			var startTimeApp = appointmentStartTimeArray2[parseInt(dayNameIndex)];
			var endTimeApp = appointmentEndTimeArray2[parseInt(dayNameIndex)];

			/*
			 * alert(startTimeApp); alert(startTimeApp);
			 */

			var adminSetStartTime = startTimeApp.split("-");
			var adminSetEndtTime = endTimeApp.split("-");

			// alert(adminSetStartTime[0]+adminSetStartTime[1]);
			var appTime = timeValue.split(" ");

			if (adminSetStartTime[2] == "PM") {

				if (adminSetStartTime[0] != 0)
					adminSetStartTime[0] = parseInt(adminSetStartTime[0]) + 12;

			}
			if (adminSetEndtTime[2] == "PM") {
				if (adminSetEndtTime[0] != 0)
					adminSetEndtTime[0] = parseInt(adminSetEndtTime[0]) + 12;

			}

			var timeSplit = appTime[0].split(":");

			if (timeSplit[1].toString().length == 1) {

				timeSplit[1] = "0" + timeSplit[1];
				// alert(t2[1]);
				timeValue = timeSplit[0] + ":" + timeSplit[1] + " "
						+ appTime[1];
				// alert(time)
			} else {

				timeValue = time;

			}

			if (appTime[1] == "PM") {

				if (timeSplit[0] != 0)
					timeSplit[0] = parseInt(timeSplit[0]) + 12;

			}

			/*
			 * alert(parseInt(timeSplit[0]+timeSplit[1]));
			 * alert(adminSetStartTime[0]+adminSetStartTime[1]);
			 * alert(parseInt(adminSetEndtTime[0]+adminSetEndtTime[1]));
			 */

			if (parseInt("" + timeSplit[0] + timeSplit[1]) >= parseInt(""
					+ adminSetStartTime[0] + adminSetStartTime[1])
					&& parseInt("" + timeSplit[0] + timeSplit[1]) <= parseInt(""
							+ adminSetEndtTime[0] + adminSetEndtTime[1])) {

				document.getElementById("txtTime").value = timeValue;
				ApointTime = timeValue;

			}

			else {
				/* need to be converted to SA */
				alertPopUp(eerrorgg_ment, appointmentcanttimeslot_ment);

			}

		} else {

			var appTime = timeValue.split(" ");
			var timeSplit = appTime[0].split(":");

			if (timeSplit[1].toString().length == 1) {

				timeSplit[1] = "0" + timeSplit[1];
				// alert(t2[1]);
				timeValue = timeSplit[0] + ":" + timeSplit[1] + " "
						+ appTime[1];
				// alert(time)
			} else {

				timeValue = time;

			}

			/*
			 * timeValue has selected time in form of AM, PM it converted into
			 * 24 hours format
			 */

			var chours = Number(timeValue.match(/^(\d+)/)[1]);
			var cminutes = Number(timeValue.match(/:(\d+)/)[1]);
			var cAMPM = timeValue.match(/\s(.*)$/)[1];
			if (cAMPM == "PM" && chours < 12)
				chours = chours + 12;
			if (cAMPM == "AM" && chours == 12)
				chours = chours - 12;
			var sHours = chours.toString();
			var sMinutes = cminutes.toString();
			if (chours < 10)
				sHours = "0" + sHours;
			if (cminutes < 10)
				sMinutes = "0" + sMinutes;

			var selectedTimeIn24 = sHours + "" + sMinutes; // selected time
															// store in another
															// var in for of 24

			/*
			 * Get current time in 12 hour format, it is used in background for
			 * restrict on past time
			 */
			var currentmatchTime = new Date();
			var currenthours = currentmatchTime.getHours();
			var currentminutes = currentmatchTime.getMinutes();

			var currentTimeIn24 = currenthours + "" + currentminutes;
			if (currentminutes < 10)
				currentminutes = "0" + currentminutes

			var suffix = "AM";
			if (currenthours >= 12) {
				suffix = "PM";
				currenthours = currenthours - 12;
			}
			if (currenthours == 0) {
				currenthours = 12;
			}

			var finalCurrenttime = currenthours + ":" + currentminutes + " "
					+ suffix; // finalCurrenttime(12 hour)

			/*
			 * Restrict past time if user only select current date otherwise any
			 * time will be valid first get current date in format of mm/dd/yyyy
			 */

			var ftoday = new Date();
			var fdd = ftoday.getDate();
			var fmm = ftoday.getMonth() + 1; // January is 0!

			var fyyyy = ftoday.getFullYear();
			if (fdd < 10) {
				fdd = '0' + fdd;
			}
			if (fmm < 10) {
				fmm = '0' + fmm;
			}
			var fftoday = fmm + '/' + fdd + '/' + fyyyy;

			if (ApointDate == fftoday) { // if user select today
				if (timeValue == finalCurrenttime) { // match condtion
														// between selected and
														// current time in AM,
														// PM
					alertPopUp(eerrorgg_ment, appointmentcurrenttime_memt);
				} else if (selectedTimeIn24 > currentTimeIn24) {
					document.getElementById("txtTime").value = timeValue;
					ApointTime = timeValue;
				} else {
					alertPopUp(eerrorgg_ment, appointmentpasttime_ment);
				}
				// document.getElementById("txtTime").value = timeValue;
				// ApointTime=timeValue;

			} else {

				document.getElementById("txtTime").value = timeValue;
				ApointTime = timeValue;
			}

		}

	}
}

var show = function() {

	var myNewDate = Date.parse(document.getElementById("txtDate").value)
			|| new Date();

	if (typeof myNewDate === "number") {
		myNewDate = new Date(myNewDate);
	}
	plugins.datePicker.show({
		date : myNewDate,
		mode : 'date', // date or time or blank for both
		allowOldDates : false
	}, cb);

}

var time = function() {

	plugins.datePicker.show({
		date : new Date(),
		mode : 'time', // date or time or blank for both
		allowOldDates : false
	}, timeDispley);

}

function alertDismissed() {

}
function IsNumeric(input) {
	return (input - 0) == input
			&& (input + '').replace(/^\s+|\s+$/g, "").length > 0;
}
function postValueToServer() {

	var ApDate = document.getElementById('txtDate').value;
	var ApTime = document.getElementById('txtTime').value;
	var ApName = document.getElementById('txtName').value;
	var ApEmail = document.getElementById('txtEmail').value;
	var ApRemark = document.getElementById('txtRemark').value;
	var ApPhone = document.getElementById('txtPhone').value;
	if (ApDate != '' && ApDate != null) {
		if (ApTime != '' && ApTime != null) {
			if (ApName != '' && ApName != null) {
				if (ApEmail != '' && ApEmail != null && validateForm(ApEmail)) {
					if (ApPhone != '' && ApPhone != null && IsNumeric(ApPhone)
							&& ApPhone.length >= 8) {
						if (ApRemark != '' && ApRemark != null) {
							$('.appypie-loader').show();
							plugins.childBrowser.appointmentServiceCall(appId,
									ApointDate, ApointTime, ApName, ApEmail,
									ApPhone, ApRemark, "1", "Android");
							document.getElementById('txtDate').value = '';
							document.getElementById('txtTime').value = '';
							document.getElementById('txtName').value = '';
							document.getElementById('txtEmail').value = '';
							document.getElementById('txtRemark').value = '';
							document.getElementById('txtPhone').value = '';
							setTimeout(function() {
								$('.appypie-loader').hide();
							}, 2000);
						} else {

							/* need to be converted to SA */
							
							alertPopUp(alert_ment, remark_ment);
						}
					} else {
						
					
						/* need to be converted to SA */
						alertPopUp(alert_ment,
								pleaseprovideavalidphonenumber_ment);

					}
				} else {
					/* need to be converted to SA */
					alertPopUp(alert_ment, enteravalidmailaaaddress_ment);

				}
			} else {
				/* need to be converted to SA */
				alertPopUp(alert_ment, nameblank_ment);

			}
		} else {
			/* need to be converted to SA */
			alertPopUp(alert_ment, timeblank_ment);

		}
	} else {
		/* need to be converted to SA */
		
		alertPopUp(alert_ment, dateblank_ment);

	}
}

function validateForm(x) {
	var atpos = x.indexOf("@");
	var dotpos = x.lastIndexOf(".");
	if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= x.length) {

		return false;
	}
	return true;

}

function processSuccess(data, status, req) {
	console.log("processSuccess  : " + req.responseText);
}

function processError(data, status, req) {
	console.log("Errro : data :" + data + " status :" + status + " req : "
			+ req);
}