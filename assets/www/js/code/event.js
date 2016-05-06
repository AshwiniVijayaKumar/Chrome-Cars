////////////////Events////////////////////////
var facebookEventType = '';
var dataLength = '';
var googleData = '';
var CanshowMoregoogle = 0;
var googleTabs = '';
var CalenderId = '';
var tabPressed = 0;
var eventIndex = '';
var eventBriteOrganizerId='';

var oops_event = "oops ! Unable to open";
var weburlnotavailable_event = "Weburl not available.";
var serverresponse_event = 'Server Response';
var nodatafound_event = 'No Data Found,\nPlease try after some time';
var eventee_event = 'Event';
var noupcomingeventfound_event = 'No Upcoming Event Found';
var nopasteventfound_event = 'No Past Event Found';
var showmore_event = "Show More"; 
var upcomingevents_event = "Upcoming Events";
var pastevents_event = "Past Events";
var map_event = "Map";
var share_event = "Share";

function geteventPage(index)
{
  if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    if(checkNetworkConnection())
    {
        eventIndex = index;
        if (sessionStorage.getItem("pageIndex") < 4)
        {
            sessionStorage.setItem("EventInBottom", "true");
        }
        sessionStorage.setItem("fromSingleEvent", "false");
        sessionStorage.setItem("dataLength", "");
        var event_HTML = '';
        $eventIndex = $(masterData).find("event[indexval=" + index + "]");
        var eventId = $eventIndex.find("eventId").text().split(',');
        console.log("eventId --->"+eventId);
        var counter=0;
        var funcName='';
            for (var i = 0; i <= eventId.length - 1; i++)
            {
                if (eventId[i] == 'FacebookEvent') {
                    $eventIndex.find("FacebookEvent").each(function() {
                                                           var FBPlayListName = $(this).find("FBPlayListName").text();
                                                           var FBUserName = $(this).find("FBUserName").text();
                                                           var eventfacebookInnerImage = $(this).find("eventfacebookInnerImage").text();
                                                           localStorage.setItem("FBUserName", FBUserName);
                                                           if (FBUserName.indexOf('events') != -1) {
                                                           facebookEventType = 2;
                                                           } else {
                                                           facebookEventType = 1;
                                                           }
                                                           funcName='showFacebookEvent(\'' + FBUserName + '\',\'FromFacebookInner\')';
                                                           event_HTML += '<a onclick="showFacebookEvent(\'' + FBUserName + '\',\'FromFacebookInner\');" class="appypie-list">'+returnImageHtml(eventfacebookInnerImage)+'<span>' + FBPlayListName + '</span></a>';
                                                           
                                                           console.log("hi this is -->"+i+"<---value--->"+event_HTML);
                                                           counter=parseInt(counter)+1;
                                                           });
                } else if (eventId[i] == 'Eventful') {
                    $eventIndex.find("Eventful").each(function() {
                                                      var EventfulPListName = $(this).find("EventfulPListName").text();
                                                      var EventfulUsername = $(this).find("EventfulUsername").text();
                                                      var eventfulInnerImage = $(this).find("eventfulInnerImage").text();
                                                     
                                                      var eventUrl = 'http://api.eventful.com/json/events/search?app_key=gjx98WPMc6R57c3K&keywords=' + EventfulUsername + '';
                                                      event_HTML += '<a onclick="eventinner(\'' + eventUrl + '\',\'Eventful\');" class="appypie-list">'+returnImageHtml(eventfulInnerImage)+'<span>' + EventfulPListName + '</span></a>';
                                                        console.log("hi this is -->"+i+"<---value--->"+event_HTML);
                                                      funcName='eventinner(\'' + eventUrl + '\',\'Eventful\')';
                                                      counter=parseInt(counter)+1;
                                                      });
                } else if (eventId[i] == 'Eventbrite') {
                    $eventIndex.find("Eventbrite").each(function() {
                                                        tabPressed = 0
                                                        var EventbritePListName = $(this).find("EventbritePListName").text();
                                                        var EventbriteUsername = $(this).find("EventbriteUsername").text();
                                                        eventBriteOrganizerId=EventbriteUsername;
                                                        var eventbriteInnerImage = $(this).find("eventbriteInnerImage").text();
                                                       
                                                        var eventUrl = 'https://www.eventbrite.com/json/organizer_list_events?app_key=EHHWMU473LTVEO4JFY&id=' + EventbriteUsername + '';
                                                        event_HTML += '<a onclick="eventinner(\'' + EventbriteUsername + '\',\'Eventbrite\');" class="appypie-list">'+returnImageHtml(eventbriteInnerImage)+'<span>' + EventbritePListName + '</span></a>';
                                                          console.log("hi this is -->"+i+"<---value--->"+event_HTML);
                                                        funcName='eventinner(\'' + EventbriteUsername + '\',\'Eventbrite\')';
                                                        counter=parseInt(counter)+1;
                                                        });
                }else if (eventId[i] == 'bookmyshow') {
                    $eventIndex.find("bookmyshow").each(function() {
                                                        tabPressed = 0
                                                        var bookmyshowName = $(this).find("bookmyshowName").text();
                                                        var bookmyshowImage = $(this).find("bookmyshowImage").text();
                                                        var page_url = $(this).find("link").text();
                                                        
                                                        event_HTML += '<a onclick="openWebPage(\''+page_url+'\',1);" class="appypie-list">'+returnImageHtml(bookmyshowImage)+'<span>' + bookmyshowName + '</span></a>';
                                                        funcUrl='openWebPage(\''+page_url+'\',1)';
                                                        
                                                        counter=parseInt(counter)+1;
                                                        });
                }  else if (eventId[i] == 'Google') {
                    $eventIndex.find("Google").each(function() {
                                                    var GooglePListName = $(this).find("GooglePListName").text();
                                                    var googleEventDates = $(this).find("googleEventDates").text();
                                                    CalenderId = $(this).find("GoogleUsername").text();
                                                    var googleInnerImage = $(this).find("googleInnerImage").text();
                                                   
                                                    sessionStorage.setItem("googleCalenderID", CalenderId);
                                                    event_HTML += '<a onclick="showGoogleEvent(\'' + CalenderId + '\',\'FromGoogleInner\',\'' + googleEventDates + '\');" class="appypie-list">'+returnImageHtml(googleInnerImage)+'<span>'+GooglePListName+'</span></a>';
                                                      console.log("hi this is -->"+i+"<---value--->"+event_HTML);
                                                    funcName='showGoogleEvent(\'' + CalenderId + '\',\'FromGoogleInner\',\'' + googleEventDates + '\')';
                                                    counter=parseInt(counter)+1;
                                                    });
                } else if (eventId[i] == 'Songkick') {
                    $eventIndex.find("Songkick").each(function() {
                                                      var SongkickPListName = $(this).find("SongkickPListName").text();
                                                      var SongkickUsername = $(this).find("SongkickUsername").text();
                                                      event_HTML += '<a onclick="eventinner();" class="appypie-list"> <img  src="images/songkickEvent.png"  /> <span>'+SongkickPListName+'</span></a>';
                                                        console.log("hi this is -->"+i+"<---value--->"+event_HTML);
                                                      funcName='eventinner()';
                                                      counter=parseInt(counter)+1;

                                                      });
                } else if (eventId[i] == 'Bandsintown') {
                    $eventIndex.find("Bandsintown").each(function() {
                                                         var BandsintownPListName = $(this).find("BandsintownPListName").text();
                                                         var BandsintownUsername = $(this).find("BandsintownUsername").text();
                                                         var bandsintownInnerImage = $(this).find("bandsintownInnerImage").text();
                                                        
                                                         var eventUrl = 'http://api.bandsintown.com/artists/' + BandsintownUsername + '/events.json?api_version=2.0&app_id=YOUR_APP_ID';
                                                         event_HTML += '<a onclick="eventinner(\'' + eventUrl + '\',\'Bandsintown\');" class="appypie-list">'+returnImageHtml(bandsintownInnerImage)+'<span>' + BandsintownPListName + '</span></a>';
                                                           console.log("hi this is -->"+i+"<---value--->"+event_HTML);
                                                         funcName='eventinner(\'' + eventUrl + '\',\'Bandsintown\')';
                                                         counter=parseInt(counter)+1;
                                                         });
                }
            }
            
            if(parseInt(counter) == 1)
            {
                console.log("funcName---->"+funcName);
                window.setTimeout(funcName,10);
                sessionStorage.setItem("fromSingleEvent","true");
            }
            else
            {
                appendHtml("<div class='page-text'>" + event_HTML + "</div>",'',1);
            }
        
        
    }
}
function showMap(latitude,longitude,venueAddress,eventType) {
        if(venueAddress==""){
        window.location="maplocation:"+latitude+"&&"+longitude+"&&"+longitude;
        /*
			var reverseGeocoder = new google.maps.Geocoder();
			var currentPosition = new google.maps.LatLng(latitude, longitude);
			reverseGeocoder.geocode({'latLng': currentPosition}, function(results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
					if (results[0]) {
						window.location="maplocation:"+latitude+"&&"+longitude+"&&"+results[0].formatted_address;
					}
			}
			});
			*/
		}
		else {
			window.location="maplocation:"+latitude+"&&"+longitude+"&&"+venueAddress;
		}	
}
function showFacebookEvent(userName,PageType) {
    
    if(userName==' ' || userName==null)
 {
    	
    	
 alert(oops_event);
 }
 else
 {
    
    if(!checkNetworkConnection())
    {
    }else
    {
	/*if(window.localStorage.getItem("layout")=="slidemenu"){
				$("#reload").hide();
				$("#mainback").show();	
			}
			else {
				$("#logo").hide();
				$("#mainback").show();	
			}*/
	var eventUrl=userName.replace("www","touch");
	eventUrl=eventUrl+"?v=events";
	if(eventUrl != null && eventUrl != '') {
	    if(sessionStorage.getItem("fromSingleEvent")=="true"){
		if(window.localStorage.getItem("layout")=="slidemenu"){
                    $("#reload").show();
                    $("#mainback").hide();
                }
		
		 sessionStorage.setItem("websitePageFlag","true");
		sessionStorage.setItem("FirstWebsitePge","true");
		
		openWebPage(eventUrl,1);
		
		}
		else {
		    if(eventUrl.indexOf("http://") !=-1 || eventUrl.indexOf("https://") !=-1 ){
        sessionStorage.setItem("websitePageFlag","false");     
        openWebPage(eventUrl,2);
     }
   else
    {
	   
    alert(weburlnotavailable_event);
    }
		}
	}
	}
	}
}
function showGoogleEvent(CalenderId,PageType,date) {
    
    if(!checkNetworkConnection())
    {
    }else
    {
	/*if(window.localStorage.getItem("layout")=="slidemenu"){
				$("#reload").hide();
				$("#mainback").show();	
	}
	else {
        if((sessionStorage.getItem("pageIndex")<4) && window.localStorage.getItem("layout")=="bottom"){
            $("#logo").show();
            $("#mainback").hide();
        }
        else
        {
            $("#logo").hide();
            $("#mainback").show();
        }
    }*/
	/*else {
				$("#logo").hide();
				$("#mainback").show();	
	}*/
	 var eventUrl="https://www.google.com/calendar/embed?showTitle=0&showNav=0&showDate=0&dates="+date+"&showPrint=0&showTabs=0&showCalendars=0&mode=AGENDA&src="+CalenderId;
	if(eventUrl != null && eventUrl != '') {
	    if(sessionStorage.getItem("fromSingleEvent")=="true"){
		
		if(window.localStorage.getItem("layout")=="slidemenu"){
                    $("#reload").show();
                    $("#mainback").hide();
                }
		 sessionStorage.setItem("websitePageFlag","true");
		sessionStorage.setItem("FirstWebsitePge","true");
		openWebPage(eventUrl,1);
		
		}
		else {
		    sessionStorage.setItem("websitePageFlag","false");
		    openWebPage(eventUrl,2);
		}
	}
	}
}

function eventinner(eventUrl,eventType) {
		$('.appypie-loader').show();
		if(eventType=="FacebookEvent") {
				var wsUrl = "http://"+window.localStorage.getItem("reseller")+"/services/soap#facebookEvent";
				var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><facebookEvent xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/soap#facebookEvent/\"><username>'+eventUrl+'</username></facebookEvent></soap:Body></soap:Envelope>';
				console.log("Soap Request "+ soapRequest);
				$.ajax({
                          type: "POST",
                          url: wsUrl,
                          contentType: "text/xml",
                          dataType: "text",
                          data: soapRequest,
                          success: function(data, status, req)
					{
						var eventJSON = $(req.responseText).find("return").text();
						//alert(strJSON);
						
						var obj = jQuery.parseJSON(eventJSON);
						ParseEventJson(obj,eventType);
					},
					error: function(response, textStatus, errorThrown)
					{
						console.log(errorThrown.responseText);
					}
				});
			}
else if (eventType == "Eventbrite") {
            var wsUrl = "http://"+window.localStorage.getItem("reseller")+"/services/utility-soap#appEventBrite_Json";
            var soapRequest = '<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><appEventBrite_Json xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/utility-soap#appEventBrite_Json\"><orgid>' + eventUrl + '</orgid><type>upcoming</type></appEventBrite_Json></soap:Body></soap:Envelope>';
            console.log("Soap Request " + soapRequest);
            $.ajax({
                type: "POST",
                url: wsUrl,
                contentType: "text/xml",
                dataType: "text",
                data: soapRequest,
                success: function(data, status, req) {
                    var eventJSON = $(req.responseText).find("return").text();

                    console.log("Return Data " + eventJSON);

                    var obj = jQuery.parseJSON(eventJSON);
                    //alert(obj.length);
                    ParseEventJson(obj, eventType);
                },
                error: function(response, textStatus, errorThrown) {

                    setTimeout(function() {
                        $('.appypie-loader').hide();
                    }, 1000);
                    console.log(errorThrown.responseText);
                }
            });

        } 			
			else {	
				
				console.log("eventUrl>>>>>"+eventUrl);
				$.ajax({
                   type: "GET",
                   url: eventUrl,
                   contentType: "text/json",
                   dataType: "text",
                   cache:false,
                   success: function(data, status, req)
                   {
                   var obj = jQuery.parseJSON(req.responseText);
				   if(obj.total_items)
                   {
                   if(obj.total_items==0)
                   {
                	   
                	  
                   alertPopUp(serverresponse_event,nodatafound_event);
                   return false;
                   }
                   
                   }
                   ParseEventJson(obj,eventType);
                   },
                   error: function(response, textStatus, errorThrown)
                   {
                  // console.log(errorThrown.responseText);
                   }
                   });
			}			  
}

var eventListHtml;
function ParseEventJson(eventJSON,eventType) {
    eventListHtml = '';
    Date.prototype.getMonthName = function() {
          var monthNames = [ "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", 
                        "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER" ];
          return monthNames[this.getMonth()];
    }
	if(eventType == 'Eventful') {
	var eventJsonData=eventJSON.events;
	for(var i=0;i<eventJsonData.event.length;i++)
    { 
		var start_time=eventJsonData.event[i].start_time;
		var stop_time=eventJsonData.event[i].stop_time;
		if(stop_time == null || stop_time == ''){
			stop_time = '';
		}
		var dateTimeValues = start_time.split(' ');
		var dateValues = dateTimeValues[0];
		var separatedDates = dateValues.split('-');
		var dayValue = separatedDates[2];
		var monthValue = new Date(dateValues).getMonthName();
		var yearValue = separatedDates[0];
		var timeValues = dateTimeValues[1];
		timeValues = timeValues.substring(0,timeValues.length-3);
		var title=eventJsonData.event[i].title;
        var venue_address=eventJsonData.event[i].venue_address;
		var city_name=eventJsonData.event[i].city_name;
		var latitude=eventJsonData.event[i].latitude;
		var longitude=eventJsonData.event[i].longitude;
		if(eventJsonData.event[i].image) {
			var imageUrl=eventJsonData.event[i].image.medium.url;
		}
		if(imageUrl=='' || imageUrl==null)
        {
            imageUrl='images/eventfulEvent.png';
        }
		var description=eventJsonData.event[i].description;
		if(description == null || description == ''){
			description = '';
		}
		var eventUrl = eventJsonData.event[i].url;
		localStorage.setItem('event_'+i,description);
        eventListHtml += '<li onclick="eventdetail(\''+imageUrl+'\',\''+start_time+'\',\''+title.replace(/'/g, '')+'\',\''+latitude+'\',\''+longitude+'\',\''+i+'\',\''+eventUrl+'\',\''+venue_address+'\',\''+city_name+'\',\''+stop_time+'\',\''+eventType+'\');">\
        <div class="eventVanue"><span class="hanger"></span><div class="eventTime"><span class="month">'+monthValue+'</span><span class="day">'+dayValue+'</span><span class="year">'+yearValue+'</span><span class="time">'+timeValues+'</span></div></div>\
          <h3>'+title+'</h3>\
          <p>'+venue_address+'/'+city_name+'</p>\
          <div class="eventImp"><a><img src="images/event-ticket.png" /></a><a><img src="images/event-map.png" /></a></div>\
        </li>';
		if(i == eventJsonData.event.length-1) {
			googleTabs=eventType;
			eventinner_view();
		}
	}
	}
	else if(eventType == 'Eventbrite') {
	     var eventJsonData = eventJSON;
        
        for (var i = 0; i < eventJsonData.length; i++)
        {
             if (eventJsonData != 'No Record found') {
            var start_date = eventJsonData[i].start_date;
            var title = eventJsonData[i].title;
			//title=title.replace(/[^\d\s]+/, '').replace(/\s{2,}/, ' ');
            var address = eventJsonData[i].address;
            var city = eventJsonData[i].city;
            var latitude = eventJsonData[i].latitude;
            var longitude = eventJsonData[i].longitude;
            var dateTimeValues = start_date.split(' ');
            var dateValues = dateTimeValues[0];
            var separatedDates = dateValues.split('-');
            var dayValue = separatedDates[2];
            var monthValue = new Date(dateValues).getMonthName();
            var yearValue = separatedDates[0];
            var timeValues = dateTimeValues[1];
            timeValues = timeValues.substring(0, timeValues.length - 3);
            var imageUrl = eventJsonData[i].logo;
            if (imageUrl == '' || imageUrl == null) {
                imageUrl = 'images/eventbriteEvent.png';
            }

           var timeZone=eventJsonData[i].timezone;
		   localStorage.setItem("timeZone_"+ i,timeZone);
           var stop_time = eventJsonData[i].end_date;
           
		   
            var description = eventJsonData[i].description;
            if (description == null || description == '') {
                description = '';
            }
            localStorage.setItem('event_' + i, description);
            var eventUrl = eventJsonData[i].url;
            eventListHtml += '<li onclick="eventdetail(\'' + imageUrl + '\',\'' + start_date + '\',\'' + title.replace(/'/g, '') + '\',\'' + latitude + '\',\'' + longitude + '\',\'' + i + '\',\'' + eventUrl + '\',\'' + address + '\',\'' + city + '\',\'' + stop_time + '\',\'' + eventType + '\');">\
            <div class="eventVanue"><span class="hanger"></span><div class="eventTime"><span class="month">' + monthValue + '</span><span class="day">' + dayValue + '</span><span class="year">' + yearValue + '</span><span class="time">' + timeValues + '</span></div></div>\
            <h3>' + title + '</h3>\
            <p>' + address + '/' + city + '</p>\
            <div class="eventImp"><a><img src="images/event-ticket.png" /></a><a><img src="images/event-map.png" /></a></div>\
            </li>';
             }
             else
             {
                 setTimeout(function() {
                            if (tabPressed == 0) {
                            eventListHtml = '';
                            
                            alertPopUp(eventee_event,noupcomingeventfound_event);
                          
                            } else {
                            eventListHtml = '';
                            alertPopUp(eventee_event,nopasteventfound_event);
                           
                            }
                            }, 1500);
             }
            if (i == eventJsonData.length - 1) {

                setTimeout(function() {
                    googleTabs = eventType;
                    eventinner_view();
                }, 10);
            }
             
            
        }
	}
	else if(eventType == 'Bandsintown') {
	    for(var i=0;i<eventJSON.length;i++)
        { 
		var start_time=eventJSON[i].datetime;
		var stop_time = '';
		var dateTimeValues = start_time.split('T');
		var dateValues = dateTimeValues[0];
		var separatedDates = dateValues.split('-');
		var dayValue = separatedDates[2];
		var monthValue = new Date(dateValues).getMonthName();
		var yearValue = separatedDates[0];
		var timeValues = dateTimeValues[1];
		timeValues = timeValues.substring(0,timeValues.length-3);
		var title=eventJSON[i].title;
        var venue_address=eventJSON[i].formatted_location;
		var city_name=eventJSON[i].venue.city;
		var latitude=eventJSON[i].venue.latitude;
		var longitude=eventJSON[i].venue.longitude;
		var imageUrl=eventJSON[i].artists.image_url;
		if(imageUrl=='' || imageUrl==null)
            {
                imageUrl='images/bandsintownEvent.png';
            }
		var description=eventJSON[i].description;
		if(description == null || description == ''){
			description = '';
		}
		var eventUrl = eventJSON[i].ticket_url;
		if(eventUrl != null) {
		   eventUrl = eventUrl.replace(/\\\//g, "/");
		}
		localStorage.setItem('event_'+i,description);
        eventListHtml += '<li onclick="eventdetail(\''+imageUrl+'\',\''+start_time+'\',\''+title.replace(/'/g, '')+'\',\''+latitude+'\',\''+longitude+'\',\''+i+'\',\''+eventUrl+'\',\''+venue_address+'\',\''+city_name+'\',\''+stop_time+'\',\''+eventType+'\');">\
        <div class="eventVanue"><span class="hanger"></span><div class="eventTime"><span class="month">'+monthValue+'</span><span class="day">'+dayValue+'</span><span class="year">'+yearValue+'</span><span class="time">'+timeValues+'</span></div></div>\
          <h3>'+title+'</h3>\
          <p>'+venue_address+'/'+city_name+'</p>\
          <div class="eventImp"><a><img src="images/event-ticket.png" /></a><a><img src="images/event-map.png" /></a></div>\
        </li>';
		if(i == eventJSON.length-1) {
		   googleTabs=eventType;
			eventinner_view();
		}
	    }
	}
	else if(eventType == 'FacebookEvent') {
	if(facebookEventType==1)
        {
	    var eventJsonData=eventJSON.data;
		
	    for(var i=0;i<1;i++)
        { 
		if(eventJsonData[0].to) {
		if(eventJsonData[0].to.data[0]) {
		if(eventJsonData[0].to.data[0].start_time) {
		    var start_time=eventJsonData[0].to.data[0].start_time;
			
			var dateTimeValues = start_time.split('T');
		    var dateValues = dateTimeValues[0];
		    var separatedDates = dateValues.split('-');
		    var dayValue = separatedDates[2];
		    var monthValue = new Date(dateValues).getMonthName();
		    var yearValue = separatedDates[0];
		    var timeValues = dateTimeValues[1];
			if(timeValues) { 
				timeValues = timeValues.substring(0,timeValues.length-3);
			}
			else {
				timeValues = "";
			}
		}
		else {
			var start_time='';
		}
		if(eventJsonData[0].to.data[0].end_time) {
		    var stop_time =eventJsonData[0].to.data[0].end_time;
		}
		else {
			var stop_time='';
		}
		if(eventJsonData[0].to.data[0].location) {
		    var venue_address =eventJsonData[0].to.data[0].location;
		}
		else {
			var venue_address='';
		}
		if(eventJsonData[0].to.data[0].name) {
		    var title =eventJsonData[0].to.data[0].name;
		}
		else {
			var title='';
		}
		}
		}
		var city_name="";
		var latitude="";
		var longitude="";
		if(eventJsonData[0].picture){
		var imageUrl=eventJsonData[0].picture;
		}
		if(imageUrl=='' || imageUrl==null)
                    {
                        imageUrl='images/facebookEvent.png';
                    }
		if(eventJsonData[0].story) {
		var description=eventJsonData[0].story;
		}
		else {
			var description='';
		}
		if(description == null || description == ''){
			description = '';
		}
		var eventUrl = localStorage.getItem("FBUserName");
		localStorage.setItem('event_0',description);
        eventListHtml += '<li onclick="eventdetail(\''+imageUrl+'\',\''+start_time+'\',\''+title.replace(/'/g, '')+'\',\''+latitude+'\',\''+longitude+'\',\'0\',\''+eventUrl+'\',\''+venue_address+'\',\''+city_name+'\',\''+stop_time+'\',\''+eventType+'\');">\
        <div class="eventVanue"><span class="hanger"></span><div class="eventTime"><span class="month">'+monthValue+'</span><span class="day">'+dayValue+'</span><span class="year">'+yearValue+'</span><span class="time">'+timeValues+'</span></div></div>\
          <h3>'+title+'</h3>\
          <p>'+venue_address+'/'+city_name+'</p>\
          <div class="eventImp"><a><img src="images/event-ticket.png" /></a><a><img src="images/event-map.png" /></a></div>\
        </li>';
		  
			eventinner_view();
	    }
		}
		else if(facebookEventType==2)
        {
            var eventJsonData=eventJSON.data;
            for(var i=0;i<eventJsonData.length;i++)
            {
                var title=eventJsonData[i].name;
                var start_time=eventJsonData[i].start_time;
                var stop_time = '';
                var venue_address=eventJsonData[i].location;
                var dateTimeValues = start_time.split('T');
                if(dateTimeValues)
                {
                    var dateValues = dateTimeValues[0];
                    var separatedDates = dateValues.split('-');
                    var dayValue = separatedDates[2];
                    var monthValue = new Date(dateValues).getMonthName();
                    var yearValue = separatedDates[0];
                    if(dateTimeValues.length>1)
                    {
                        var timeValues = dateTimeValues[1];
                        timeValues = timeValues.substring(0,timeValues.length-6);
                    }
                    
                    else
                    {
                        timeValues='';
                    }
                }
                
                var city_name="";
                var latitude="";
                var longitude="";
                var imageUrl='images/facebookEvent.png';
                var description='';
                var eventUrl = localStorage.getItem("FBUserName");
                localStorage.setItem('event_0',description);
                eventListHtml += '<li onclick="eventdetail(\''+imageUrl+'\',\''+start_time+'\',\''+title.replace(/'/g, '')+'\',\''+latitude+'\',\''+longitude+'\',\''+0+'\',\''+eventUrl+'\',\''+venue_address+'\',\''+city_name+'\',\''+stop_time+'\',\''+eventType+'\');"">\
                <div class="eventVanue"><span class="hanger"></span><div class="eventTime"><span class="month">'+monthValue+'</span><span class="day">'+dayValue+'</span><span class="year">'+yearValue+'</span><span class="time">'+timeValues+'</span></div></div>\
                <h3>'+title+'</h3>\
                <p>'+venue_address+'/'+city_name+'</p>\
                <div class="eventImp"><a><img src="images/event-ticket.png" /></a><a><img src="images/event-map.png" /></a></div>\
                </li>';
                
               
                eventinner_view();
                
                
            }
        }
	}
	else if(eventType == 'Google')
    {
        if(CanshowMoregoogle==0)
        {
        var eventJsonData=eventJSON;
        console.log(eventJsonData);
            googleData=eventJsonData;
        }
        else
        {
            eventJsonData=googleData;
        }
       
        if(eventJsonData.length>19)
        {
		    
            for(var i=0;i<sessionStorage.getItem("dataLength");i++)
            {
			    if(eventJsonData[i].visibility != "private")
                {
                    
                    var start_time=eventJsonData[i].dateTime;
					
                    var stop_time=eventJsonData[i].dateTime;
                    var start_Date=eventJsonData[i].date;
					
                    if(stop_time == null || stop_time == '')
                    {
                        
                        stop_time = '';
                        
                    }
                    if(start_time==null || start_time=='' || (typeof start_time == 'undefined'))
                    {
                        
                        if(typeof start_Date != 'undefined')
                            start_time=start_Date;
                        else
                        {
                            start_Date='';
                            start_time='';
                        }
                    }
                    
                    var dateTimeValues = start_time.split('T');
                    console.log("________%%%%%%%%%"+dateTimeValues[0]);
                    if(dateTimeValues)
                    {
                        var dateValues = dateTimeValues[0];
                        var separatedDates = dateValues.split('-');
                        var dayValue = separatedDates[2];
                        var monthValue = new Date(dateValues).getMonthName();
                        var yearValue = separatedDates[0];
                        if(dateTimeValues.length>1)
                        {
                            var timeValues = dateTimeValues[1];
                            timeValues = timeValues.substring(0,timeValues.length-6);
                        }
                        
                        else
                        {
                            timeValues='';
                        }
                    }
					var timeZone=eventJsonData[i].timezoneAvl;
                    var splitTimeZoneWithDate=timeZone.split(',');
                    var splitTimeZone=splitTimeZoneWithDate[0].split(' ');
					timeValues=splitTimeZoneWithDate[1];
					var dayValue=splitTimeZone[0];
					 if(dayValue.length==3) {
                        dayValue=dayValue.substring(0,1);
                    }
                    else if(dayValue.length==4) {
                        dayValue=dayValue.substring(0,2);
					}
                    var monthValue=splitTimeZone[1];
                    var yearValue=splitTimeZone[2];
                    var title=eventJsonData[i].summary;
                    title=title.replace("'",'');
                    if(eventJsonData[i].location)
                    {
                        var venue_address=eventJsonData[i].location;
                        if(venue_address==''||venue_address==null)
                        {
                            venue_address='';
                        }
                    }
                    else
                        var venue_address='';
                    var city_name="";
                    var latitude="";
                    var longitude="";
                    var	imageUrl='images/googleEvent.png';
                    var description=eventJsonData[i].description;
                    if(description == null || description == ''){
                        description = '';
                    }
                    localStorage.setItem('event_'+i,description);
                    var eventUrl = eventJsonData[i].htmlLink;
                    eventListHtml += '<li onclick="eventdetail(\''+imageUrl+'\',\''+start_time+'\',\''+title.replace(/'/g, '')+'\',\''+latitude+'\',\''+longitude+'\',\''+i+'\',\''+eventUrl+'\',\''+venue_address+'\',\''+city_name+'\',\''+timeZone+'\',\''+eventType+'\');">\
                    <div class="eventVanue"><span class="hanger"></span><div class="eventTime"><span class="month">'+monthValue+'</span><span class="day">'+dayValue+'</span><span class="year">'+yearValue+'</span><span class="time">'+timeValues+'</span></div></div>\
                    <h3>'+title+'</h3>\
                    <p>'+venue_address+'/'+city_name+'</p>\
                    <div class="eventImp"><a><img src="images/event-ticket.png" /></a><a><img src="images/event-map.png" /></a></div>\
                    </li>';
                }
                
                if(i==sessionStorage.getItem("dataLength")-1||i==eventJsonData.length-1)
                {
                    if(i!=eventJsonData.length-1)
                    {
                      
                    eventListHtml += '<div><a class="show-more" onclick="showMoreGoogle(\''+eventType+'\',\''+googleData+'\');">'+showmore_event+'</a></div>';
                    }
					googleTabs=eventType;
                    eventinner_view();
                    
                }
            }
                
            
        }
        else
        {    
        for(var i=0;i<eventJsonData.length;i++)
        {
             if(eventJsonData[i].visibility != "private")
            {
				 if(eventJsonData!='No Record found')
				{
                var start_time=eventJsonData[i].dateTime;
                var stop_time=eventJsonData[i].dateTime;
                var start_Date=eventJsonData[i].date;
                if(stop_time == null || stop_time == '')
                {
                    
                    stop_time = '';
                    
                }
                if(start_time==null || start_time=='' || (typeof start_time == 'undefined'))
                {
                    
                    if(typeof start_Date != 'undefined')
                        start_time=start_Date;
                    else
                    {
                        start_Date='';
                        start_time='';
                    }
                }
                
               var dateTimeValues = start_time.split('T');
                console.log("________%%%%%%%%%"+dateTimeValues[0]);
                if(dateTimeValues)
                {
                    var dateValues = dateTimeValues[0];
                    var separatedDates = dateValues.split('-');
                    var dayValue = separatedDates[2];
                    var monthValue = new Date(dateValues).getMonthName();
                    var yearValue = separatedDates[0];
                    if(dateTimeValues.length>1)
                    {
                        var timeValues = dateTimeValues[1];
                        timeValues = timeValues.substring(0,timeValues.length-6);
                    }
                    
                    else
                    {
                        timeValues='';
                    }
                }
				var timeZone=eventJsonData[i].timezoneAvl;
                    var splitTimeZoneWithDate=timeZone.split(',');
                    var splitTimeZone=splitTimeZoneWithDate[0].split(' ');
					timeValues=splitTimeZoneWithDate[1];
					var dayValue=splitTimeZone[0];
					 if(dayValue.length==3) {
                        dayValue=dayValue.substring(0,1);
                    }
                    else if(dayValue.length==4) {
                        dayValue=dayValue.substring(0,2);
					}
                    var monthValue=splitTimeZone[1];
                    var yearValue=splitTimeZone[2];
				if(eventJsonData[i].summary)
                {
					var title=eventJsonData[i].summary;
					title=title.replace("'",'');
				}
                if(eventJsonData[i].location)
                {
                    var venue_address=eventJsonData[i].location;
                    if(venue_address==''||venue_address==null)
                    {
                        venue_address='';
                    }
                }
                else
                    var venue_address='';
                var city_name="";
                var latitude="";
                var longitude="";
                var	imageUrl='images/googleEvent.png';
                var description=eventJsonData[i].description;
                if(description == null || description == ''){
                    description = '';
                }
                localStorage.setItem('event_'+i,description);
                var eventUrl = eventJsonData[i].htmlLink;
                eventListHtml += '<li onclick="eventdetail(\''+imageUrl+'\',\''+start_time+'\',\''+title.replace(/'/g, '')+'\',\''+latitude+'\',\''+longitude+'\',\''+i+'\',\''+eventUrl+'\',\''+venue_address+'\',\''+city_name+'\',\''+timeZone+'\',\''+eventType+'\');">\
                <div class="eventVanue"><span class="hanger"></span><div class="eventTime"><span class="month">'+monthValue+'</span><span class="day">'+dayValue+'</span><span class="year">'+yearValue+'</span><span class="time">'+timeValues+'</span></div></div>\
                <h3>'+title+'</h3>\
                <p>'+venue_address+'/'+city_name+'</p>\
                <div class="eventImp"><a><img src="images/event-ticket.png" /></a><a><img src="images/event-map.png" /></a></div>\
                </li>';
            }
			else {
				if(tabPressed==0)
                        {
                        eventListHtml ='';
                        alertPopUp(eventee_event,noupcomingeventfound_event);
                        
                        }
                        else
                        {
                            eventListHtml ='';
                            alertPopUp(eventee_event,nopasteventfound_event);
                           
                        }
			}
			}
            if(i==eventJsonData.length-1)
            {
                googleTabs=eventType;
                eventinner_view();
               
            }
        }
        }
   	}
}
function showMoreGoogle(eventType,Data)
{
    console.log("called");
    CanshowMoregoogle=1;
	console.log("called1");
	var dataLength=sessionStorage.getItem("dataLength");
    dataLength=dataLength+20;
	sessionStorage.setItem("dataLength",dataLength);
	console.log("called2");
    ParseEventJson(Data,eventType);
	console.log("called3");
}
function eventinner_view(){
    localStorage.setItem("eventListView","true");
    var eventList = '';
    var eventinner_HTML1 = '';
    var eventinner_HTML2 = '';
    clsvalue = 'backToDashbord';
    if(googleTabs=='Eventbrite')
    {
    eventinner_HTML1 = '<section>\
    <!--div class="no-event"></div-->\
   \
    <div class="page-text google-event-list">\
    <div  class="event-list"><ul>';
    }
    else
    {
        eventinner_HTML1 = '<section>\
        <!--div class="no-event"></div-->\
        <div class="page-text">\
        <div  class="event-list"><ul>';
    }
    eventinner_HTML2='</ul>\
 </div>\
  </div>\
</section>';
    eventList = eventinner_HTML1+eventListHtml.substring(0,eventListHtml.length)+eventinner_HTML2;
	if(sessionStorage.getItem("fromSingleEvent")=="true") {
	
	/*if(window.localStorage.getItem("layout")=="slidemenu"){
				$("#reload").show();
				$("#mainback").hide();
			}
			else {
				if((sessionStorage.getItem("pageIndex")<4) && window.localStorage.getItem("layout")=="bottom"){
            $("#logo").show();
            $("#mainback").hide();
        }
        else
        {
			$("#logo").hide();
			$("#mainback").show();
        }  
			}*/
			if(googleTabs=='Eventbrite')
    {
        var tempp;
        
       
        if(tabPressed==0)
            {
            tempp='<div class="appypie-page-tab" id="gaurav"><span class="active" onclick="showFutureEventbrite();" id="tab3">'+upcomingevents_event+'</span><span class="last" onclick="showPastEventbrite();" id="tab1">'+pastevents_event+'</span></div>';
            }
            
            else if(tabPressed==2)
            {
              
              tempp='<div class="appypie-page-tab" id="gaurav"><span onclick="showFutureEventbrite();" id="tab3">'+upcomingevents_event+'</span><span class="active" class="last" onclick="showPastEventbrite();" id="tab1">'+pastevents_event+'</span></div>';
            }
        
        appendHtml(""+tempp+"<div class='page-text'><br clear='all'><br clear='all'>"+eventList+"</div>",'',1);

	}
	else {
		 appendHtml(eventList,'',1);
	   
	}
	}
	else {
	i/*f(window.localStorage.getItem("layout")=="slidemenu"){
				$("#reload").hide();
				$("#mainback").show();	
			}
			else {
				$("#logo").hide();
				$("#mainback").show();	
			}*/
	if(googleTabs=='Eventbrite')
    {
        console.log("in if ");
        var tempp;
        if(tabPressed==0)
            {
           
            tempp='<div class="appypie-page-tab" id="gaurav"><span class="active" onclick="showFutureEventbrite();" id="tab3">'+upcomingevents_event+'</span><span class="last" onclick="showPastEventbrite();" id="tab1">'+pastevents_event+'</span></div>';
            }
            
            else if(tabPressed==2)
            {
              
              tempp='<div class="appypie-page-tab" id="gaurav"><span onclick="showFutureEventbrite();" id="tab3">'+upcomingevents_event+'</span><span class="active" class="last" onclick="showPastEventbrite();" id="tab1">'+pastevents_event+'</span></div>';
            }
        appendHtml(""+tempp+"<div class='page-text'><br clear='all'><br clear='all'>"+eventList+"</div>",2,2);
        

	}
	else {
		
		appendHtml(eventList,2,2);
		
	   
	}
	}
	setTimeout(function(){$('.appypie-loader').hide();},1000);
}		
     
function showPastEventbrite() 
{
    $('.appypie-loader').show();
    tabPressed = 2;
    var wsUrl = "http://"+window.localStorage.getItem("reseller")+"/services/utility-soap#appEventBrite_Json";
    var soapRequest = '<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><appEventBrite_Json xmlns=\"http://appypieml.pbodev.info/services/utility-soap#appEventBrite_Json\"><orgid>'+ eventBriteOrganizerId + '</orgid><type>past</type></appEventBrite_Json></soap:Body></soap:Envelope>';
    console.log("Soap Request " + soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req) {
           var eventJSON = $(req.responseText).find("return").text();
           
           console.log("Return Data " + eventJSON);
           
           var obj = jQuery.parseJSON(eventJSON);
           //alert(obj.length);
           
           ParseEventJson(obj, googleTabs);
           },
           error: function(response, textStatus, errorThrown) {
           
           setTimeout(function() {
                      $('.appypie-loader').hide();
                      }, 1000);
           console.log(errorThrown.responseText);
           }
           });
}
	 
function showFutureEventbrite() {
    $('.appypie-loader').show();
    tabPressed = 0;
    var wsUrl = "http://appypieml.pbodev.info/services/utility-soap#appEventBrite_Json";
    var soapRequest = '<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><appEventBrite_Json xmlns=\"http://appypieml.pbodev.info/services/utility-soap#appEventBrite_Json\"><orgid>' + eventBriteOrganizerId + '</orgid><type>upcoming</type></appEventBrite_Json></soap:Body></soap:Envelope>';
    console.log("Soap Request " + soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req) {
           var eventJSON = $(req.responseText).find("return").text();
           
           console.log("Return Data " + eventJSON);
           
           var obj = jQuery.parseJSON(eventJSON);
          
           ParseEventJson(obj, googleTabs);
           },
           error: function(response, textStatus, errorThrown) {
           
           setTimeout(function() {
                      $('.appypie-loader').hide();
                      }, 1000);
           console.log(errorThrown.responseText);
           }
           });

}
	 
function showPastGoogleEvent()
{
   $('.appypie-loader').show();
   var eventUrl='http://'+window.localStorage.getItem("reseller")+'/mobile_api/gEvent.php?username='+CalenderId+'&type=past';
   
    tabPressed=1;
    if(CanshowMoregoogle==1)
    {
        CanshowMoregoogle=0;
        dataLength=20;
    }
    eventinner(eventUrl,googleTabs);
}

function showCurrentGoogleEvent()
{
    $('.appypie-loader').show();
    var eventUrl='http://'+window.localStorage.getItem("reseller")+'/mobile_api/gEvent.php?username='+sessionStorage.getItem("googleCalenderID");
   
    tabPressed=2;
    if(CanshowMoregoogle==1)
    {
        CanshowMoregoogle=0;
         dataLength=20;
    }

    eventinner(eventUrl,googleTabs);
    
    
}


function showFutureGoogleEvent()
{
    $('.appypie-loader').show();
    var eventUrl='http://'+window.localStorage.getItem("reseller")+'/mobile_api/gEvent.php?username='+sessionStorage.getItem("googleCalenderID")+'&type=future';
	
    tabPressed=0;
    if(CanshowMoregoogle==1)
    {
        CanshowMoregoogle=0;
		
         dataLength=20;
    }

    eventinner(eventUrl,googleTabs);

}
function showEvent(eventUrl,eventType) {
	
	
			if(sessionStorage.getItem("fromSingleEvent")=="true"){
				openWebPage(eventUrl,3);
		}
		else {
			openWebPage(eventUrl,4);
		}
}
function eventdetail(imageUrl,eventTime,title,latitude,longitude,description,eventUrl,venueAddress,cityName,stopTime,eventType) {
	clsvalue = 'backToDashbord';
	//$('.appypie-loader').show();
	localStorage.setItem("imageUrl",imageUrl);
                                                                            localStorage.setItem("eventTime",eventTime);
                                                                            localStorage.setItem("title",title);
                                                                            localStorage.setItem("latitude",latitude);
                                                                            localStorage.setItem("longitude",longitude);
                                                                            localStorage.setItem("description",description);
                                                                            localStorage.setItem("eventUrl",eventUrl);
                                                                            localStorage.setItem("venueAddress",venueAddress);
                                                                            localStorage.setItem("cityName",cityName);
                                                                            localStorage.setItem("stopTime",stopTime);
                                                                            localStorage.setItem("eventType",eventType);
    if(eventType=='Bandsintown' ||eventType=='FacebookEvent'||eventType=='Google') {
                                                                            var infoDate=eventTime.split('T');
																			}
                                                                            else {
                                                                            var infoDate=eventTime.split(' ');
																			}
                                                                            var dateVal=infoDate[0];
                                                                            if(infoDate.length>1) {
																				var timeVal=infoDate[1];
																			}	
																			else {
																				timeVal='';
																			}
                                                                           var timeNew=[];
    if(timeVal.indexOf('+') != -1)
    {
        timeNew=timeVal.split('+');
        timeVal=timeNew[0];
    }
    else if(timeVal.indexOf('-') != -1)
    {
        timeNew=timeVal.split('-');
        timeVal=timeNew[0];
    }
                                                                            
                                                                            
                                                                            var separatedDates = dateVal.split('-');
                                                                            var dayValue = separatedDates[2];
                                                                            var monthValue = new Date(dateVal).getMonthName();
                                                                            var yearValue = separatedDates[0];
                                                                            monthValue=monthValue.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
                                                                            var days = ["Sunday", "Monday", "Tueday", "Wednesday", "Thrusday", "Friday", "Saturday"];
                                                                            var today = new Date(dateVal);
                                                                            dayValue=days[today.getDay()];
                                                                           // monthValue=monthValue.substring(0,3);
                                                                            //yearValue=yearValue.substring(2,4);
																			if (eventType == 'Eventbrite' )
    {
        stopTime=stopTime.split(' ');
        stopTime=stopTime[1];
        stopTime=stopTime.substring(0,5);
        timeVal=timeVal.substring(0,5);
        monthValue = monthValue+" "+separatedDates[2];
        yearValue = yearValue+" ("+localStorage.getItem("timeZone_"+ description)+")";
                                            
                                            var hours = ((parseInt(timeVal.substring(0,2))+24))%24;
                                            var mid='am';
                                            
                                            if(hours==0){ //At 00 hours we need to show 12 am
                                            hours=12;
                                            }
                                            else if (hours>12){
                                            hours=hours%12;
                                            mid='pm';
                                            }
                                             if(timeVal.substring(0,2)<=12)
                                            {
                                            timeVal=hours+timeVal.substring(2,5)+" AM";
                                            }
                                            else
                                            timeVal=hours+timeVal.substring(2,5)+" PM";
                                            
                                            var shours = ((parseInt(stopTime.substring(0,2))+24))%24;
                                            var smid='am';
                                            
                                            if(shours==0){ //At 00 hours we need to show 12 am
                                            shours=12;
                                            }
                                            else if (shours>12){
                                            shours=shours%12;
                                            mid='pm';
                                            }
                                           
                                            if(stopTime.substring(0,2)<=12)
                                            {
                                            stopTime=shours+stopTime.substring(2,5)+" AM";
                                            }
                                            else
                                            {
                                            stopTime=shours+stopTime.substring(2,5)+" PM";
                                            }
                                            
    }                                                    
                                                                            
                                                                            
                                                                        clsvalue = 'backToDashbord';
                                                                        descriptionValue = localStorage.getItem('event_'+description);
																		var div = document.createElement('div');
    div.innerHTML = descriptionValue;
    var link = div.getElementsByTagName("a");
    for (var i=0; i<link.length; i++) {
        var text = link[i].href;
		if(text.substring(0,4)=="tel:") {
			
		}
		else if(text.substring(0,7)=="mailto:"){
		
		}
		else {
			localStorage.setItem("eventPageLink"+i+"",text);
			link[i].removeAttribute("href");
			link[i].setAttribute("onclick","openLinkFromEventDetail('"+i+"')");
		}
    }
	if (eventType == 'Eventbrite' )
    {
		
		
                                                                        eventdetail_HTML = '<section class="page-content">\
                            <div class="event-details-header" style="background-image:url('+imageUrl+')"><div class="event-details-box" ><span>'+dayValue+' '+monthValue+' '+yearValue+'</br> ' + timeVal + ' To ' + stopTime+ '</span><h3>'+title+'</h3></div></div>\
                                                                        <div class="page-text">\
                                                                        <a '+primaryColor+' onclick="showEvent(\''+eventUrl+'\',\''+eventType+'\')" class="event-link eventticket">'+eventee_event+'</a>  <a '+secondaryColor+' onclick="showMap(\''+latitude+'\',\''+longitude+'\',\''+venueAddress+'\',\''+eventType+'\')" class="event-link eventmap">'+map_event+'</a>\
                                                                        <!--  <a onclick="window.plugins.childBrowser.eventShare(\''+eventUrl+'\')" class="event-link eventshare">'+share_event+'</a> -->\
                                                                        <br clear="all" />\
                                                                        <div class="event-details">'+div.innerHTML+'\
                                                                        <div class="event-details-venue"><h3>Location</h3><p>'+cityName+'</p><h3>Venue</h3><p>'+venueAddress+'</p></div>\
                                                                        </div> \
                                                                        </div>\
                                                                        </section>';
			}	
else
{
 eventdetail_HTML = '<section class="page-content">\
                            <div class="event-details-header" style="background-image:url('+imageUrl+')"><div class="event-details-box" ><span>'+dayValue+' '+monthValue+' '+yearValue+'</br> '+timeVal+'</span><h3>'+title+'</h3></div></div>\
                                                                        <div class="page-text">\
                                                                        <a '+primaryColor+' onclick="showEvent(\''+eventUrl+'\',\''+eventType+'\')" class="event-link eventticket">'+eventee_event+'</a>  <a '+secondaryColor+' onclick="showMap(\''+latitude+'\',\''+longitude+'\',\''+venueAddress+'\',\''+eventType+'\')" class="event-link eventmap">'+map_event+'</a>\
                                                                        <!--  <a onclick="window.plugins.childBrowser.eventShare(\''+eventUrl+'\')" class="event-link eventshare">'+share_event+'</a> -->\
                                                                        <br clear="all" />\
                                                                        <div class="event-details">'+div.innerHTML+'\
                                                                        <div class="event-details-venue"><h3>Location</h3><p>'+cityName+'</p><h3>Venue</h3><p>'+venueAddress+'</p></div>\
                                                                        </div> \
                                                                        </div>\
                                                                        </section>';
}			
	if(sessionStorage.getItem("fromSingleEvent")=="true") {
		appendHtml(eventdetail_HTML,2,2);
		
	}
	else {
		appendHtml(eventdetail_HTML,3,3);
	
    }  
}	
function openLinkFromEventDetail(i) {
    if(window.localStorage.getItem("layout")=="bottom"){
				sessionStorage.setItem("websitePageFlag","true");
			}
			else {
			 if (window.localStorage.getItem("layout") != "slidemenu")
			 {
				sessionStorage.setItem("websitePageFlag","false");
			 }
			}
   /* if(window.localStorage.getItem("layout")=="slidemenu"){
				$("#reload").hide();
				$("#mainback").show();	
			}
			else {
				$("#logo").hide();
				$("#mainback").show();	
			}*/
    
			openWebPage(localStorage.getItem("eventPageLink"+i+""),1);
	
}