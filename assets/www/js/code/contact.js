var latitude;
var longitude;
var subtext;
var websiteURL = '';

var error_contact = "error in network connection";
function getcontact(index)
{
	if(localStorage.getItem("appLanguageCheck") == "sa")
	{
		arabic();
	}
	
    $("#reload").show();
    $("#bookmark").hide();
    $("#mainbackfoodecom").hide();
    sessionStorage.setItem("contactPageIndex",index);
	
	var contactHTML = '<div class="contact-us">\
	<div class="logo"><img src="images/logo.png"></div>\
	<h1></h1>\
	<ul></ul>\
	</div>';
		
	var contactRow = '<li><em><i></i></em><span></span><strong></strong></li>';	
	var contactData = $(masterData).find("contactUs[indexval="+index+"]");
	
	appendHtml(contactHTML,'',1);
	
	var contactObj = $(".contact-us");
	contactObj.find("h1").text(contactData.find('title').text());
	
	contactData.find("contactUsrow").each(function(i){
		//alert("Hjfdep");
		var thisRow = $(this);
		
		contactObj.find("ul").append(contactRow);
		var thisLI = contactObj.find("ul li").eq(i);
		thisLI.attr("type", thisRow.find("image").text())
		thisLI.find("i").addClass(thisRow.find("innerimage").text());
		thisLI.find("span").text(thisRow.find("text").text() + ":");
		thisLI.find("strong").text(thisRow.find("subtext").text());	
		if (thisRow.find("innerimage").text().indexOf('.') != -1)
		{
			thisLI.find("i").remove();
			thisLI.find("em").append('<img src="'+thisRow.find("innerimage").text()+'" />');
		}
			
		
		var type = thisRow.find("image").text();		
		if(type == "map")
		{
			if(thisRow.find("latitude").size() == 0) 
			{
				thisLI.attr("lat", contactData.find("latitude").text()).attr("long", contactData.find("longitude").text())
			}
			else
			{
				thisLI.attr("lat", thisRow.find("latitude").text()).attr("long", thisRow.find("longitude").text())
			}
		}
		
		if(type == "link")
		{
			var webLink = thisRow.find("subtext").text();
			thisLI.attr("link", webLink);
			thisLI.find("strong").text(webLink.replace("http://", "").replace("https://", "").replace("/", ""));
		}
	})
	
	contactObj.find("li").click(function(){
		var thisType = $(this).attr("type");
		if(thisType == "map")
		{
			showmapcontact($(this).attr("lat"),$(this).attr("long"),$(this).find("strong").text());
		}
		else if(thisType == "link")
		{
			contactUsListEvents(thisType,$(this).attr("link"));	
		}
		else
		{
			contactUsListEvents(thisType,$(this).find("strong").text());				
		}
	})
	
	
	var backgroundOption = contactData.find("contactBackground").text();
	var backgroundLandscape = contactData.find("contactIpadBackground").text();
	var layoutOption = contactData.find("contactLayout").text();
	var hideOption = contactData.find("contactHideIcon").text().toLowerCase();
	var borderOption = contactData.find("contactBorderColor").text();
	var contactfieldBgColor = contactData.find("contactfieldBgColor").text();		
	var roundedOption = contactData.find("contactRounded").text().toLowerCase();
	var headingOptions = contactData.find("contactHeading").text().split(",");
	var contentOptions = contactData.find("contactContent").text().split(",");
	var labelOptions = contactData.find("contactLabel").text().split(",");
	var iconOptions = contactData.find("contactIcon").text();
	
	
	contactObj.addClass(contentOptions[0]).addClass(contentOptions[1]).css("color", contentOptions[2])	
	contactObj.find("h1").addClass(headingOptions[0]).addClass(headingOptions[1]).css("color", headingOptions[2])		
	contactObj.find("span").addClass(labelOptions[0]).addClass(labelOptions[1]).css("color", labelOptions[2])		
	if(layoutOption == 2)
	{
		contactObj.find("i").css({
			background:iconOptions.split(",#")[0],
		 	color:"#" + iconOptions.split(",#")[1].split(',')[0]
		})
	}
	else
	{
		contactObj.find("em").css({
			background:iconOptions.split(",#")[0]
		})
		contactObj.find("i").css({
			color:"#" + iconOptions.split(",#")[1].split(',')[0]
		})
	}
	
	contactObj.find("li").css({
		background:contactfieldBgColor
	})
	
	contactObj.addClass("heading-" + headingOptions[3]).addClass("content-" + contentOptions[3]).addClass("label-" + labelOptions[3])
	.addClass("theme-" + layoutOption).addClass("icon-" + iconOptions.split(",#")[1].split(',')[1]).addClass("rounded-" + roundedOption).addClass("hide-" + hideOption);
	
	if(backgroundOption.charAt(0)=="#"){
		contactObj.css("background-color", backgroundOption);	
	}
	else if(backgroundOption != "none")
	{
		if(backgroundLandscape == "none"){
			backgroundLandscape = backgroundOption;
		}
		var styleStr  = '<style>';
			styleStr += '@media screen and (orientation:portrait) {';
			styleStr += '.contact-us {background-image:url('+backgroundOption+')}';
			styleStr += '}';
			styleStr += '@media screen and (orientation:landscape) {';
			styleStr += '.contact-us {background-image:url('+backgroundLandscape+')}';			
			styleStr += '}</style>';
			
		$.mobile.activePage.find(".page-content").prepend(styleStr);		
		contactObj.addClass("bg-image");	
	//	contactObj.css("background-image", "url("+backgroundOption+")").addClass("bg-image");	
	}
	
			
	if(layoutOption == 2) {
		var styleStr  = '<style>';
			styleStr += '.contact-us.theme-2 ul li,';
			styleStr += '.contact-us.theme-2 ul li em,';
			styleStr += '.contact-us.theme-2 ul li i,';
			styleStr += '.contact-us.theme-2 ul li img {';
			styleStr += 'border-color:'+borderOption+' !important}';			
			styleStr += '</style>';
		$.mobile.activePage.find(".page-content").prepend(styleStr);
	}
	
	if(layoutOption == 3) {
		var styleStr  = '<style>';
			styleStr += '.contact-us.theme-3 ul li:before{background:'+iconOptions.split(",#")[0]+'}';
			styleStr += '.contact-us.theme-3 ul li {border-color:'+borderOption+' !important}';
			styleStr += '</style>';
		$.mobile.activePage.find(".page-content").prepend(styleStr);
	}
}

function contactUsAddressEvents(title,subtext,latitude,longitude){
    console.log("title"+title+"subtext"+subtext+"latitude"+latitude+"longitude"+longitude);
    showmapcontact(latitude,longitude,subtext);
}
function contactUsListEvents(title,subtext)
{
    if (title =='email' )
    {
        window.location="showmailto:"+subtext+"";
    }
    else if(title == 'skype')
    {
        toaster.callSkype(subtext);        
    }
    else if (title =='link')
    {
        if(window.localStorage.getItem("layout")=="bottom"){
            if(sessionStorage.getItem("pageIndex")<=4)
            {
                sessionStorage.setItem("websitePageFlag","true");
            }
            else
            {
                sessionStorage.setItem("websitePageFlag","true");
            }
        }
        else {
            sessionStorage.setItem("websitePageFlag","false");
        }
        if(window.localStorage.getItem("layout")=="slidemenu"){
            $("#reload").hide();
            $("#mainback").show();
        }
        else {
            $("#logo").hide();
            $("#mainback").show();
        }
        var websiteLink = '';
        if(subtext.substring(0,3) === 'www')
        {
            websiteLink="http://"+subtext;
        }
        else {
            websiteLink = subtext;
        }
        $("#contentHolder7").css({"background-color": "#000"});
        appendHtml("<div class='page-text'></div>",7,2);
      
        if(window.localStorage.getItem("layout")=="bottom") {
            window.location="bottom"+websiteLink;
        }
        else {
            window.location="show"+websiteLink;
        }
    }
    else if (title =='phone')
    {
        window.location="showtel:"+subtext+"";
    }
}	


function getCurrentLocation()
{
	navigator.geolocation.getCurrentPosition(onsuccessLocation, onerrorLocation, {
		enableHighAccuracy: true
		,timeout : 5000
	});
}

function onsuccessLocation(position) 
{
    var lat = position.coords.latitude;
    var long = position.coords.longitude;
	setTimeout(function(){
		$("#contentHolder7").css({"background-color": "#000"});
		appendHtml("<div class='page-text'></div>",7,2);
		
		if(window.localStorage.getItem("layout")=="slidemenu"){
			$("#reload").hide();
			$("#mainback").show();
		}
		else {
			$("#logo").hide();
			$("#mainback").show();
		}
	
		if(window.localStorage.getItem("layout")=="bottom"){
			window.location="gpslocation:1"+latitude+"&&"+longitude+"&&"+subtext+"&&"+lat+"&&"+long;
		}
		else 
		{
			var arabic =  /[\u0600-\u06ff]|[\u0750-\u077f]|[\ufb50-\ufc3f]|[\ufe70-\ufefc]/;
			var string = subtext;
			var checkurduornot = arabic.test(string)
			if(checkurduornot)
			{
				var subtextnull='';
				window.location="gpslocation:1"+latitude+"&&"+longitude+"&&"+subtextnull+"&&"+lat+"&&"+long;
			}
			else
			{
				window.location="gpslocation:1"+latitude+"&&"+longitude+"&&"+subtext+"&&"+lat+"&&"+long;
			}             
		}	
	},1000);
}

function onerrorLocation(error) {	
    alert("error in network connection");
}

function showmapcontact(latitude1,longitude1,subtext1) {
	getCurrentLocation();	
	latitude=latitude1;
	longitude=longitude1;
	subtext=subtext1;
}

function setlocation(address) {    
    websiteURL=address;
}

function gatLocation(lat,lng) {    
    window.location="neeti@@@"+lat+"@@@"+lng;
}