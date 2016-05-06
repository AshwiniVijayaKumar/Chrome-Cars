//////////////////////////////Tools///////////////////////////
var _index;
var isCalled='';
var woeiD="";
var lat="";
var log="";
var weatherType='';

var flag="";
var parser;
var xmlDoc;

var pagelevelcurrencyconverter;
///******** START DEFINE VAR ************////
var mortage_cal_heading_tools = "Mortgage Calculator";
var mortage_cal_home_price_tools = "Home Price";
var mortage_cal_down_payment_tools = "Down Payment";
var mortage_cal_down_payment_less_home_price_tools = "Down Payment should be less than Home Price";
var mortage_cal_loan_amt_tools = "Loan Amount";
var mortage_cal_interest_rate_tools = "Interest Rate";
var mortage_cal_loan_term_tools = "Loan Term";
var mortage_cal_annual_prop_tax_tools = "Annual Property Taxes";
var mortage_cal_insurance_tools = "Insurance";
var mortage_cal_monthly_amt_tools = "Monthly Amount";
var mortage_cal_amt_tools = "Amount";
var mortage_cal_calculate_tools = "CALCULATE";
var mortage_cal_estimate_monthly_cal_tools = "This estimated payment does not include monthly mortgage insurance premiums. Your loan could be subject to monthly mortgage insurance. Please contact your lender to clarify if your loan will have monthly mortgage insurance.";
var alert_blank_field_tools = "Blank Field";
var alert_enter_price_amt_tools = "Please Enter the Price Amount";
var alert_enter_down_pay_amt_tools = "Please Enter the Down Payment Amount";
var weather_address_title_tools = "Click for to see a weather report";
var inner_html_msg_location_not_found_tools = "The location could not be found";
var inner_html_msg_error_occured_tools = "An error has occurred";
var alert_for_alert_title_tools = "Alert";
var alert_internet_conn_not_avail_tools = "Internet connection is not available";
var alert_unable_to_retrive_current_location_tools = "Unable to retrieve your current location. Please turn on Settings->Privacy->Location Services for App.";
var in_serach_click_weather_geocode_weatherloaction_tools = "weatherLocation";
var in_serach_click_weather_geocode_weatherlist_tools = "weatherList";
///******** END DEFINE VAR ************////

var editorClientId;
var editorClientSecret

function gettools(index)
{
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
   //arabic();
  /*  $("#reload").show();
    $("#bookmark").hide();*/
    html1='<div class="page-text">';
    var html2='';
   
    $toolsIndex = $(masterData).find( "pocketTools[indexval="+index+"]" );
    var toolsId = $toolsIndex.find("toolsId").text();
    toolsId = toolsId.split(',');
    var index = toolsId.indexOf('cricket');
    var toolsnewId = $toolsIndex.find("toolsNewId").text();
    toolsnewId = toolsnewId.split(',');
    if (index > -1) {
        toolsId.splice(index, 1);
        toolsnewId.splice(index, 1);
        
    }
    var index2 = toolsId.indexOf('practo');
    if (index2 > -1) {
        toolsnewId.splice(index2, 1);
        toolsId.splice(index2, 1);
        
    }
    var cameraImageUrl=$toolsIndex.find("cameraImageUrl").text();
    var geoImageUrl=$toolsIndex.find("geoImageUrl").text();
    var calculatorImageUrl=$toolsIndex.find("calculatorImageUrl").text();
    var scannerImageUrl=$toolsIndex.find("scannerImageUrl").text();
    var recorderImageUrl=$toolsIndex.find("recorderImageUrl").text();
    var bibleImageUrl=$toolsIndex.find("bibleImageUrl").text();
    var quranImageUrl=$toolsIndex.find("quranImageUrl").text();
    var videoRecorderImageUrl=$toolsIndex.find("videoImageUrl").text();
    var torchImageUrl=$toolsIndex.find("torchImageUrl").text();
    var weatherImageUrl=$toolsIndex.find("weatherImageUrl").text();
    weatherType=$toolsIndex.find("weatherType").text();
    var scientificImageUrl=$toolsIndex.find("scientificImageUrl").text();
    var noteImgeUrl=$toolsIndex.find("noteImageUrl").text();
	var pedometerImgeUrl=$toolsIndex.find("pedometerImageUrl").text();
	var coloringBookImgeUrl=$toolsIndex.find("coloringbookImageUrl").text();
	var editorImageUrl=$toolsIndex.find("editorImageUrl").text();
	editorClientId=$toolsIndex.find("editorClientId").text();
	editorClientSecret=$toolsIndex.find("editorClientSecret").text();

    var imageUrl='';
    var imageHtml='';
	var curConverterImageUrl=$toolsIndex.find("curConverterImageUrl").text();
    var totalpagesyo=toolsId.length;
	
    $toolsIndex.find('cricket').each(function()
                                     {
                                     totalpagesyo=totalpagesyo+1;
                                     });
    $toolsIndex.find('practo').each(function()
                                    {
                                    totalpagesyo=totalpagesyo+1;
                                    });
	
    pagelevelcurrencyconverter=0;
	
    if(totalpagesyo==1)
    {
        $toolsIndex.find('cricket').each(function()
                                         {
                                         var page_url=$(this).find("link").text();
                                         openWebPage(page_url,1);
                                         return false;
                                         });
        $toolsIndex.find('practo').each(function()
                                        {
                                        var page_url=$(this).find("link").text();
                                        openWebPage(page_url,1);
                                        return false;
                                        });
	sessionStorage.setItem('singlePage',"true");
        if(toolsId[0] == 'notes')
        {
            sessionStorage.setItem("fromTools" ,true);
            
            getNotes();
            
            return false;
        }
        
		if(toolsId[0] == 'curConverter')
            {
                toolsId[0]='curConverter';
                imageUrl=curConverterImageUrl;
                pagelevelcurrencyconverter=1;
				//return
            }
		
		if(toolsId[0] == 'pedometer')
        {
            
            getPedoMeter();
            
            return false;
        }

        if(toolsId[0] == 'editor')
        {
            openImageEditor();
            return false;
        }
		
		if(toolsId[0] == 'coloringbook')
        {
            
            getColoringBook();
            
            return false;
        }
		
        if(toolsId[0] == 'weather')
        {
            sessionStorage.setItem("singleWeatherPage", "true");
            weather();
            
            return false;
        }
        
        if(toolsId[0] == 'camera')
        {
            toolsId[0]='opencamera';
            
            if(cameraImageUrl!=' ')
            {
            	imageHtml=returnImageHtml(cameraImageUrl);
            }
           
            
        }
        if(toolsId[0] == 'geoLocation')
        {
            toolsId[0]='mapScreen';
            if(geoImageUrl!=' ')
            {
            	imageHtml=returnImageHtml(geoImageUrl);
            }
           
        }
        if(toolsId[0] == 'soundRecorder')
        {
            
            toolsId[0]='soundRecorder';
            if(recorderImageUrl!=' ')
            {
            	imageHtml=returnImageHtml(recorderImageUrl);
            }
           
        }
        if(toolsId[0] == 'mortgageCalculator')
        {
            toolsId[0]='mortgageCalculator';
            if(calculatorImageUrl!=' ')
            {
            	imageHtml=returnImageHtml(calculatorImageUrl);
            }
           
        }
        if(toolsId[0] == 'barcodeScanner')
        {
            toolsId[0]='barcodeScanner';
            if(scannerImageUrl!=' ')
            {
            	imageHtml=returnImageHtml(scannerImageUrl);
            }
           
        }
        if(toolsId[0] == 'bible'){
            toolsId[0]='bible';
            if(bibleImageUrl!=' ')
            {
            	imageHtml=returnImageHtml(bibleImageUrl);
            }
           
        }
        
        if(toolsId[0] == 'quran'){
            toolsId[0]='quran';
            if(quranImageUrl!=' ')
            {
            	imageHtml=returnImageHtml(quranImageUrl);
            }
            
        }
        
        if(toolsId[0] == 'weather'){
            toolsId[0]='weather';
            if(weatherImageUrl!=' ')
            {
            	imageHtml=returnImageHtml(weatherImageUrl);
            }
           
        }
        if(toolsId[0] == 'scientificCalculator'){
            toolsId[0]='scientificCalculator';
            if(scientificImageUrl!=' ')
            {
            	imageHtml=returnImageHtml(scientificImageUrl);
                
            }
            
        }
        if(toolsId[0] == 'videoRecorder'){
            toolsId[0]='videoRecorder';
            if(videoRecorderImageUrl!=' ')
            {
            	imageHtml=returnImageHtml(videoRecorderImageUrl);
            }
          
        }
        
        if(toolsId[0] == 'torch'){
            toolsId[0]='torch';
            if(torchImageUrl!=' ')
            {
            	imageHtml=returnImageHtml(torchImageUrl);
            }
            
        }
        
        
        html2+='<span class="audio_list" onclick="'+toolsId[0]+'(true,'+index+',true)">'+imageHtml+'<span>'+toolsnewId[0]+'</span></span>';
        
        var html=html1+html2+'</div>';
        $("#contentHolder").empty();
        
        setTimeout(function(){
        	 appendHtml(html,'',1);
                   
                   if(window.localStorage.getItem("iad")=='True')
                   $("#contentHolder").append('</br></br>');
                   },1000);
        
    }
    else
    {
        for(var i=0;i<toolsId.length;i++) {
            
            if(toolsId[i] == 'notes'){
                
                toolsId[i]='getNotes';// Function name
                if(noteImgeUrl!=' ')
                {
                	imageHtml=returnImageHtml(noteImgeUrl);
                }
                
            }
			
			if(toolsId[i] == 'curConverter')
                {
                    pagelevelcurrencyconverter=2;
                    toolsId[i]='curConverter';
                    if(curConverterImageUrl!=' ')
                    {
                       imageHtml=returnImageHtml(curConverterImageUrl);
                    }
                    else
                    {
                        imageHtml='images/'+toolsId[i]+'.png';
                    }
                }
			
			if(toolsId[i] == 'pedometer'){
                
                toolsId[i]='getPedoMeter';// Function name
                if(pedometerImgeUrl!=' ')
                {
                	imageHtml=returnImageHtml(pedometerImgeUrl);
                }
                
            }

            if(toolsId[i] == 'editor'){

                toolsId[i]='openImageEditor';// Function name
                if(editorImageUrl!=' ')
                {
                    imageHtml=returnImageHtml(editorImageUrl);
                }

            }
            
			if(toolsId[i] == 'coloringbook')
             {
            
                toolsId[i]='getColoringBook';// Function name
                if(coloringBookImgeUrl!=' ')
                {
                	imageHtml=returnImageHtml(coloringBookImgeUrl);
                }
             }
			
            if(toolsId[i] == 'camera')
            {
                toolsId[i]='opencamera';
                
                if(cameraImageUrl!=' ')
                {
                	imageHtml=returnImageHtml(cameraImageUrl);
                }
               
            }
            if(toolsId[i] == 'geoLocation')
            {
                toolsId[i]='mapScreen';
                if(geoImageUrl!=' ')
                {
                	imageHtml=returnImageHtml(geoImageUrl);
                }
               
            }
            if(toolsId[i] == 'soundRecorder')
            {
                
                toolsId[i]='soundRecorder';
                if(recorderImageUrl!=' ')
                {
                	imageHtml=returnImageHtml(recorderImageUrl);
                }
               
            }
            if(toolsId[i] == 'mortgageCalculator')
            {
                toolsId[i]='mortgageCalculator';
                if(calculatorImageUrl!=' ')
                {
                	imageHtml=returnImageHtml(calculatorImageUrl);
                }
               
            }
            if(toolsId[i] == 'barcodeScanner')
            {
                toolsId[i]='barcodeScanner';
                if(scannerImageUrl!=' ')
                {
                	imageHtml=returnImageHtml(scannerImageUrl);
                }
                
            }
            if(toolsId[i] == 'bible'){
                toolsId[i]='bible';
                if(bibleImageUrl!=' ')
                {
                	imageHtml=returnImageHtml(bibleImageUrl);
                }
                
            }
            
            if(toolsId[i] == 'quran'){
                toolsId[i]='quran';
                if(quranImageUrl!=' ')
                {
                	imageHtml=returnImageHtml(quranImageUrl);
                }
               
            }
            
            if(toolsId[i] == 'weather'){
                toolsId[i]='weather';
                if(weatherImageUrl!=' ')
                {
                	imageHtml=returnImageHtml(weatherImageUrl);
                }
               
            }
            if(toolsId[i] == 'scientificCalculator'){
                toolsId[i]='scientificCalculator';
                if(scientificImageUrl!=' ')
                {
                	imageHtml=returnImageHtml(scientificImageUrl);
                }
                
            }
            if(toolsId[i] == 'videoRecorder'){
                toolsId[0]='videoRecorder';
                if(videoRecorderImageUrl!=' ')
                {
                	imageHtml=returnImageHtml(videoRecorderImageUrl);
                }
                
            }
            
            if(toolsId[i] == 'torch'){
                toolsId[i]='torch';
                if(torchImageUrl!=' ')
                {
                	imageHtml=returnImageHtml(torchImageUrl);
                }
                
            }
            toolsnewId[i]= $('<div/>').text(toolsnewId[i]).html();
            //alert("second"+toolsId[i]);
            html2+='<span class="audio_list" onclick="'+toolsId[i]+'(true,'+index+',true)" >'+imageHtml+'<span>'+toolsnewId[i]+'</span></span>';
            
        }
        $toolsIndex.find('cricket').each(function()
                                         {
                                         var page_url=$(this).find("link").text();
                                         var cricketName=$(this).find("cricketName").text();
                                         var cricketImage=$(this).find("cricketImage").text();
                                         html2+='<span class="audio_list" onclick="openWebPage(\''+page_url+'\',2)">'+returnImageHtml(cricketImage)+'<span>'+cricketName+'</span></span>';
                                         });
        $toolsIndex.find('practo').each(function()
                                        {
                                        var page_url=$(this).find("link").text();
                                        var practoName=$(this).find("practoName").text();
                                        var practoImage=$(this).find("practoImage").text();
                                        html2+='<span class="audio_list" onclick="openWebPage(\''+page_url+'\',2)">'+returnImageHtml(practoImage)+'<span>'+practoName+'</span></span>';
                                        });
        var html=html1+html2+'</div>';
        setTimeout(function(){
        	 appendHtml(html,'',1);
                   
                   if(window.localStorage.getItem("iad")=='True')
                   $("#contentHolder").append('</br></br>');
                   },1000);
    }
}

function getPedoMeter()
{
toaster.startPedoMeter();
}

function getColoringBook()
{
    if(sessionStorage.getItem('singlePage'))
    {
       openWebPage("http://"+window.localStorage.getItem("reseller")+"/coloring_book",1);
    }
    else
    {
        openWebPage("http://"+window.localStorage.getItem("reseller")+"/coloring_book",2);
    }
	//openWebPage("http://apps.appypie.com/media/testapp/coloring_book/",1);
    //window.open("http://snappy.appypie.com/coloring_book", "_system");
}

window.localStorage.setItem("_isSwitchedOn1","false");
function torch()
{
	
	//alert("ravi torch");
	
    /*if(window.localStorage.getItem("layout")=="slidemenu"){
        $("#reload").hide();
        $("#mainback").show();
    }
    else {
        $("#logo").hide();
        $("#mainback").show();
    }*/
    
    if(window.localStorage.getItem("_isSwitchedOn1")=='true'){
        
        helloTorch = '<center style="height:100%; display:table; width:100%; background:#191f2e"><div class="qrcode"><a href="#"  onclick="TorchONOFF();" style="text-decoration:none; color: black;"><img id="torchimageonoff"  src="images/torch-on.png"></a></div></center>'
    }
    else{
        
        helloTorch = '<center style="height:100%; display:table; width:100%; background:#191f2e"><div class="qrcode"><a href="#"  onclick="TorchONOFF();" style="text-decoration:none; color: black;"><img id="torchimageonoff"  src="images/torch-off.png"></a></div></center>'
    }
    
    
    
    setTimeout(function(){
    	 appendHtml(helloTorch,2,2);
               if(window.localStorage.getItem("iad")=='True')
               $("#contentHolder2").append('</br></br>');
               },1000);
    
}

function heightcoding (){
    $(".qrcode").css('height',$("#contentHolder").height());
    
}

function TorchONOFF()
{
	
	
    if(window.localStorage.getItem("_isSwitchedOn1")=='true')
        
    {
        
        window.localStorage.setItem("_isSwitchedOn1","false");
        $('#torchimageonoff').attr("src", "images/torch-off.png");
        toaster.switchTorch("off");
        
    } else {
        window.localStorage.setItem("_isSwitchedOn1","true");
        
        $('#torchimageonoff').attr("src", "images/torch-on.png");
        toaster.switchTorch("on");
    }
    
    
    /*
     window.plugins.flashlight.available(function(isAvailable) {
     if (isAvailable) {
     // switch on
     window.plugins.flashlight.toggle(); // success/error callbacks may be passed
     
     
     // switch off after 3 seconds
     setTimeout(function() {
     window.plugins.flashlight.switchOff(); // success/error callbacks may be passed
     }, 3000);
     
     } else {
     alert("Flashlight not available on this device");
     }
     });
     */
}


function scientificCalculator()
{
    if(!checkNetworkConnection())
    {
       /* $("#logo").show();
        $("#mainback").hide();
        $("#shopmenu").hide();*/
        $('.appypie-loader').hide();
    }
    else
    {
        if(window.localStorage.getItem("layout")=="bottom"){
            sessionStorage.setItem("websitePageFlag","true");
            sessionStorage.setItem("websiteInnerPageFlag","true");
        }
        else {
            sessionStorage.setItem("websitePageFlag","false");
        }
        /*if(window.localStorage.getItem("layout")=="slidemenu"){
            $("#reload").hide();
            $("#mainback").show();
        }
        else {
            $("#logo").hide();
            $("#mainback").show();
        }*/
        // $("#contentHolder7").css({"background-color": "#000"});
        openWebPage("http://apps.appypie.com/calculator.html",2); 
       
    }
}


function weather()
{
    //alert("location is enabled");
    if(toaster.isLocationEnabled())
    {
        isCalled='Yes';
        $('.appypie-loader').show();
        var options = { };
        //for simulator onSuccessGetLocation('');
        var watchID = navigator.geolocation.watchPosition(onSuccessGetLocation, onError, options);
    }
    else
    {
        toaster.openLocationSetting();
    }
}

function onSuccessGetLocation(position)
{
    //alert("position"+position);
    if(checkNetwork())
    {
        lat=position.coords.latitude;
        log=position.coords.longitude;
        //lat=28.5700;
        //log=77.3200;
        locationSuccess();
    }
    else
    {
        
        alertPopUp(alert_for_alert_title_tools,alert_internet_conn_not_avail_tools);
       
    }
}


function locationSuccess()
{
    var geoAPI = 'http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20geo.placefinder%20where%20text%3D%22'+lat+'%2C'+log+'%22%20and%20gflags%3D%22R%22&format=json&diagnostics=true&callback=';
    
    var myjson;
    
    $.getJSON(geoAPI, function(json){
             
              myjson=json;
           
              woeiD = myjson.query.diagnostics.url.content;
               
              if(woeiD)
			  {
           
            
              getWeatherIdFromURL(woeiD);
              }
			  else
			  {
			
			  var urlInside= myjson.query.publiclyCallable.url.content;
			  getWeatherIdFromURL(urlInside);
			  }
              }).error(function(){
                       
              //alertPopUp('Alert','Your browser does not support CORS requests!');
                       
                       //showError("Your browser does not support CORS requests!");
                       });
}


function getWeatherIdFromURL(url)
{
var geoAPI = url;
    var myjson;
   $.ajax({
			type: 'GET',
			url: geoAPI,
			processData: true,
			data: {},
			dataType: "text",
			cache:false,
			success: function(txtxml) {
				console.log("txtxml----->"+txtxml);
				
				var xmlData=jQuery.parseXML(txtxml);
				
				 var woeiD=$(xmlData).find("Result woeid").html();
                getWeatheId(woeiD);

			},
			error:function(response, textStatus, errorThrown) {
				
			}
		});
}

function getWeatheId(woeiD)
{
    
    localStorage.setItem("CurrentWID",woeiD);
    
    if(weatherType=='c')
    {
        $('#testWeather').weatherfeed([woeiD],{
                                      woeid: true,
                                      unit: 'c',
                                      image: true,
                                      country: true,
                                      highlow: true,
                                      wind: true,
                                      humidity: true,
                                      visibility: true,
                                      sunrise: true,
                                      sunset: true,
                                      forecast: true,
                                      link: true
                                      });
    }
    else
    {
        $('#testWeather').weatherfeed([woeiD],{
                                      woeid: true,
                                      unit: 'f',
                                      image: true,
                                      country: true,
                                      highlow: true,
                                      wind: true,
                                      humidity: true,
                                      visibility: true,
                                      sunrise: true,
                                      sunset: true,
                                      forecast: true,
                                      link: true
                                      });
        
    }
}

function onError(error)
{
	alertPopUp(alert_for_alert_title_tools,alert_unable_to_retrive_current_location_tools);
    
}

function changeLink()
{
    if(isCalled=='Yes')
    {
        
      /*  if(window.localStorage.getItem("layout")=="slidemenu"){
            $("#reload").hide();
            $("#mainback").show();
        }
        else {
            $("#logo").hide();
            $("#mainback").show();
        }*/
        
        isCalled='No';
        var cityName= localStorage["cityName"];
        cityName= cityName.toUpperCase();
        
        var countryName= localStorage["currentCountry"];
        var textDes=localStorage["currentText"];
        
        var humVal=localStorage["currentHumidity"];
        var highTemp=localStorage["currentHigh"];
        var lowTemp=localStorage["currentLow"];
        var currentTemp=localStorage["weatherTemp"];
        
        var weatherIconSrc=localStorage["weatherIcon"];
        var buildDate=localStorage["buildDate"];
        var locationRegion=localStorage["locationRegion"];
        
        var shortCountryName=localStorage["shortCountryName"];
        
        var windSpeed=localStorage["WindSpeed"];
        var windUnit=localStorage["WindUnit"];
        var wSpeedUnit=windSpeed + windUnit;
        
        
        
        var windDirection=localStorage["WindDirection"];
        
        
        if(parseInt(windDirection)>=0 && parseInt(windDirection)<=22)
        {
            windDirection="N";
        }else if(parseInt(windDirection)<=360 && parseInt(windDirection)>=337)
        {
            windDirection="N";
        }
        else if(parseInt(windDirection)>=23 && parseInt(windDirection)<=67)
        {
            windDirection="NE";
        }
        else if(parseInt(windDirection)>=68 && parseInt(windDirection)<=112)
        {
            windDirection="E";
        }
        else if(parseInt(windDirection)>=113 && parseInt(windDirection)<=157)
        {
            windDirection="SE";
        }
        else if(parseInt(windDirection)>=158 && parseInt(windDirection)<=202)
        {
            windDirection="S";
        }
        else if(parseInt(windDirection)>=203 && parseInt(windDirection)<=248)
        {
            windDirection="SW";
        }
        else if(parseInt(windDirection)>=248 && parseInt(windDirection)<=291)
        {
            windDirection="W";
        }
        else if(parseInt(windDirection)>=291 && parseInt(windDirection)<=336)
        {
            windDirection="NW";
        }
        
        //alert(windDirection);
        var humidity=localStorage["currentHumidity"];
        
        var unit=localStorage["weatherUnit"];
        var sunrise=localStorage["sunrise"];
        var sunset=localStorage["sunset"];
        
        
        
        var pageHTML="<div class='wheater-search'><div class='weather-main'>\
        <div class='weatherDaydate'>"+buildDate+"</div>\
        <span class='weatherSearch'><img onclick='openSearchPage();' src='images/searcg.png'></span>\
        <div class='weathercityName'>"+cityName+"</div>\
        <div class='weatherdecription'>"+textDes+"</div>\
        <div class='weatherTemp'><span class='weatherNumber'>"+currentTemp+"<sup>o</sup></span><small class='small-size'>"+unit+"</small></div>\
        <div class='weatherCloud' style='background-image:url("+weatherIconSrc+")'></div>\
        <div class='weatherOther'><ul>\
        <li> High:  <span>"+highTemp+"</span>  </li>\
        <li> Low:  <span>"+lowTemp+"</span>  </li>\
        <li> Humidity: <span>"+humVal+"</span>  </li>\
        <li> Windy:  <span>"+windDirection+" "+wSpeedUnit+"</span>  </li>\
        <li> Sunrise:  <span>"+sunrise+"</span>  </li>\
        <li> Sunset:  <span>"+sunset+"</span>  </li>\
        </ul></div>\
        </div> </div>";
        
        sessionStorage.setItem("WeatherPage","true");
        if(sessionStorage.getItem("singleWeatherPage")== "true")
        {
            if(window.localStorage.getItem("layout")=="slidemenu"){
                //                $("#reload").hide();
                //                $("#mainback").show();
            }
            else {
                
               /* if ((sessionStorage.getItem("pageIndex") < 4) && window.localStorage.getItem("layout") == "bottom") {
                    $("#logo").show();
                    $("#mainback").hide();
                } else {
                    $("#logo").hide();
                    $("#mainback").show();
                }*/
            }
            
            sessionStorage.setItem("WeatherPage","true");
            setTimeout(function(){
            	appendHtml(pageHTML,'',1);
                       if(window.localStorage.getItem("iad")=='True')
                       $("#contentHolder2").append('</br></br>');
                       },2500);
            
        }
        else
        {
            
            /*if(window.localStorage.getItem("layout")=="slidemenu"){
                $("#reload").hide();
                $("#mainback").show();
            }
            else {
                $("#logo").hide();
                $("#mainback").show();
            }*/
            
            sessionStorage.setItem("WeatherPage","true");
            setTimeout(function(){
            	appendHtml(pageHTML,2,2);
                       if(window.localStorage.getItem("iad")=='True')
                       $("#contentHolder2").append('</br></br>');
                       },2500);
            
        }
        
        
        
    }
}

function checkNetwork()
{
    if(navigator.network.connection.type == Connection.NONE)
    {
        return false;
    }
    else
    {
        return true;
    }
}

function getCurrentWeather()
{
    isCalled='Yes';
    var options = {};
    //for simulator onSuccessGetLocation('');
    var watchID = navigator.geolocation.watchPosition(onSuccessGetLocation, onError, options);
}

//weather search page
function openSearchPage()
{
    //alert("OSP");
    isCalled='No';
   /* if(window.localStorage.getItem("layout")=="slidemenu"){
        $("#reload").hide();
        $("#mainback").show();
    }
    else {
        $("#logo").hide();
        $("#mainback").show();
    }*/
    
    
    
    sessionStorage.setItem("WeatherPage","false");
    var searchHtml="<div class='wheater-search'>\
    <input placeholder='city,state,country' id='weatherLocation' name='weatherLocation' onkeyup='searchClick();' type='text' data-role='none' size='50'/>\
    <a onclick='getCurrentWeather();' class='current-loaction'>current location</a>\
    <ul id='weatherList'>  </ul>\
    <div id='weatherReport'></div>\
    </div>";
    console.log(searchHtml);
    
    if(sessionStorage.getItem("singleWeatherPage")== "true")
    {
    	appendHtml(searchHtml,2,2);
        if(window.localStorage.getItem("iad")=='True')
            $("#contentHolder3").append('</br></br>');
    }
    else
    {
    	appendHtml(searchHtml,3,3);
        if(window.localStorage.getItem("iad")=='True')
            $("#contentHolder3").append('</br></br>');
    }
}

function searchClick()
{
    weatherGeocode(in_serach_click_weather_geocode_weatherloaction_tools,in_serach_click_weather_geocode_weatherlist_tools);
}

function weatherGeocode(search,output) {
    var status;
    var results;
    var html = '';
    var msg = '';
    isCalled='No';
    // Set document elements
    var search = document.getElementById(search).value;
    var output = document.getElementById(output);
    
    if(navigator.network.connection.type == Connection.NONE)
    {
    	alertPopUp(alert_for_alert_title_tools,alert_internet_conn_not_avail_tools);
       
    }
    else
    {
        
        if (search) {
            
            output.innerHTML = '';
            
            // Cache results for an hour to prevent overuse
            now = new Date();
            
            // Create Yahoo Weather feed API address
            var query = 'select * from geo.places where text="'+ search +'"';
            var api = 'http://query.yahooapis.com/v1/public/yql?q='+ encodeURIComponent(query) +'&rnd='+ now.getFullYear() + now.getMonth() + now.getDay() + now.getHours() +'&format=json&callback=?';
            
            // Send request
            $.ajax({
                   type: 'GET',
                   url: api,
                   dataType: 'json',
                   success: function(data) {
                   //alert(JSON.stringify(data));
                   if (data.query.count > 0 ) {
                   
                   // List multiple returns
                   if (data.query.count > 1) {
                   for (var iCounter=0; iCounter<data.query.count; iCounter++)
                   {
                   html = html + '<li>'+ _getWeatherAddress(data.query.results.place[iCounter]) +'</li>';
                   }
                   } else
                   {
                   html = html + '<li>'+ _getWeatherAddress(data.query.results.place) +'</li>';
                   }
                   
                   //html = html + '</ul>';
                   
                   output.innerHTML = html;
                   
                   // Bind callback links
                   /*
                    $("a.weatherAddress").unbind('click');
                    $("a.weatherAddress").click(function(e) {
                    e.preventDefault();
                    
                    var address = $(this).text();
                    var weoid = $(this).attr('rel');
                    
                    showLocation(address,weoid);
                    });
                    */
                   } else {
                   output.innerHTML = inner_html_msg_location_not_found_tools;
                   }
                   },
                   error: function(data) {
                   output.innerHTML = inner_html_msg_error_occured_tools;
                   }
                   });
            isCalled='No';
        }
        else {
            // No search given
            output.innerHTML = '';
            isCalled='No';
        }
    }
}

function _getWeatherAddress(data)
{
    // Get address
    
    var address = data.name;
    
    if (data.admin2) address += ', ' + data.admin2.content;
    if (data.admin1) address += ', ' + data.admin1.content;
    
    address += ', ' + data.country.content;
    
    
    // Get WEOID
    var woeid = data.woeid;
    // var timezone=data.timezone.content;
    if(woeid.length>0)
    {
        return '<a onClick="addItemAlert(\'' + woeid + '\' )"  href="#" rel="'+ woeid +'" title='+weather_address_title_tools+'>'+ address +'</a>';
        //  <small>('+ woeid +')</small>';
    }
}

function addItemAlert(WId)
{
    isCalled='Yes';
    sessionStorage.setItem("WeatherPage","false");
    getWeatheId(WId);
}

function showLocation(address,woeid)
{
    $('#weatherReport').empty();
    
    $('#weatherReport').weatherfeed([woeid],{
                                    woeid: true
                                    });
}


function bible(){
    if(window.localStorage.getItem("layout")=="bottom"){
        sessionStorage.setItem("websitePageFlag","true");
    }
    else {
        sessionStorage.setItem("websitePageFlag","false");
    }
   /* if(window.localStorage.getItem("layout")=="slidemenu"){
        $("#reload").hide();
        $("#mainback").show();
    }
    else {
        $("#logo").hide();
        $("#mainback").show();
    }*/
    
    openWebPage("https://www.bible.com/bible/1/jhn.1.kjv",2); 
   
}
function quran(){
    if(window.localStorage.getItem("layout")=="bottom"){
        sessionStorage.setItem("websitePageFlag","true");
    }
    else {
        sessionStorage.setItem("websitePageFlag","false");
    }
   /* if(window.localStorage.getItem("layout")=="slidemenu"){
        $("#reload").hide();
        $("#mainback").show();
    }
    else {
        $("#logo").hide();
        $("#mainback").show();
    }*/
    
    openWebPage("http://mobile.qurandislam.com",2); 
    
}
function opencamera()
{
   if(window.localStorage.getItem("_isSwitchedOn1")=='true')
        
    {
        
        window.localStorage.setItem("_isSwitchedOn1","false");
        $('#torchimageonoff').attr("src", "images/torch-off.png");
        toaster.switchTorch("off");
        
    }
  
    
    setTimeout(function() {
     window.location="openSocialShare:";
  },10);
}
function soundRecorder()
{
    window.location="soundRecorder:";
}
function get_monthly_payment()
{
    
    var price = document.getElementById("price").value;
    var down_payment = document.getElementById("down_payment").value;
    var insurance = document.getElementById("insurance").value;
    var interestValue = document.getElementById("interest_rate");
    var interest_rate = interestValue.options[interestValue.selectedIndex].text;
    interest_rate = interest_rate.substring(0,interest_rate.length - 1);
    var propTexes = document.getElementById("proptexes").value;
    var loantermValue = document.getElementById("loan_term");
    var loan_term = loantermValue.options[loantermValue.selectedIndex].text;
    loan_term = loan_term.substring(0,loan_term.length - 3);
    if(price == null || price == '')
    {
    	alertPopUp(alert_blank_field_tools,alert_enter_price_amt_tools);
       
        document.getElementById("price").focus();
        
        return false
    }
    if(down_payment == null || down_payment == '')
    {
    	alertPopUp(alert_blank_field_tools,alert_enter_down_pay_amt_tools);
       
        document.getElementById("down_payment").focus();
        return false
    }
    var PR = price - down_payment;
    var IN = (interest_rate * 0.01) / 12;
    var PE = loan_term * 12;
    if(insurance.length > 0) {
        var payment = ((PR * IN) / (1 - Math.pow(1 + IN, -PE))) + (insurance/12) + ((propTexes)/12);
    }
    else {
        var payment = ((PR * IN) / (1 - Math.pow(1 + IN, -PE))) + ((propTexes)/12);
    }
    payment = round_number(payment,2);
    $("#monthly_payment").html(payment);
}

function round_number(value,decimal_len)
{
    return Math.round(value * Math.pow(10,decimal_len))/Math.pow(10,decimal_len);
    
}
function get_loan_amt()
{
    var price = document.getElementById("price").value;
    var down_payment = document.getElementById("down_payment").value;
    if(price != null || price != '')
    {
        if(down_payment != null || down_payment != '')
        {
            if (parseInt(price) > parseInt(down_payment))
            {
                var loan_amt= price - down_payment;
                loan_amt=round_number(loan_amt,2);
                $("#loan_amt").html(loan_amt);
            }
            else
            {
                document.getElementById("down_payment").value="";
            }
        }
        else {
        	alertPopUp(alert_blank_field_tools,alert_enter_down_pay_amt_tools);
           
        }
    }
    else {
    	alertPopUp(alert_blank_field_tools,alert_enter_price_amt_tools);
       
    }
}
function mortgageCalculator()
{
   /* if(window.localStorage.getItem("layout")=="slidemenu"){
        $("#reload").hide();
        $("#mainback").show();
    }
    else {
        $("#logo").hide();
        $("#mainback").show();
    }*/
    //$('.appypie-loader').show();
    hellocalc = '<section id="container_mor"><h2 id="heading_mortgage">'+mortage_cal_heading_tools+'</h2><aside id="calculator_form"><ul id="loan_mor_cal"><li><label>'+mortage_cal_home_price_tools+'</label><input data-role="none" type="tel" onchange="get_loan_amt()" id="price" name="Price" placeholder="Price"/></li><li><label>'+mortage_cal_down_payment_tools+'</label><input data-role="none" type="tel" name="Down Payment" id="down_payment" onblur="get_loan_amt()" placeholder="Down Payment"/></li><li><div class="span_text">'+mortage_cal_down_payment_less_home_price_tools+'</div></li><li><label>'+mortage_cal_loan_amt_tools+'</label><label id="loan_amt" class="loan_amount">'+mortage_cal_loan_amt_tools+'</label></li><li><label>'+mortage_cal_interest_rate_tools+'</label><select data-role="none" id="interest_rate" class="select_rate" ><option value="1.000">1.000%</option><option value="1.125">1.125%</option><option value="1.250">1.250%</option><option value="1.375">1.375%</option><option value="1.500">1.500%</option><option value="1.625">1.625%</option><option value="1.750">1.750%</option><option value="1.875">1.750%</option><option value="2.000">2.000%</option><option value="2.125">2.125%</option><option value="2.250">2.250%</option><option value="2.500">2.500%</option><option value="2.625">2.625%</option><option value="2.750">2.750%</option><option value="2.875">2.875%</option><option value="3.000">3.000%</option><option value="3.125">3.125%</option><option value="3.250">3.250%</option><option value="3.375">3.375%</option><option value="3.500">3.500%</option><option value="3.625">3.625%</option><option value="3.750">3.750%</option><option value="3.875">3.875%</option><option value="4.000">4.000%</option><option value="4.125">4.125%</option><option value="4.250">4.250%</option><option value="4.375">4.375%</option><option value="4.500">4.500%</option><option value="4.625">4.625%</option><option value="4.750">4.750%</option><option value="4.875">4.875%</option><option value="5.000">5.000%</option><option value="5.125">5.125%</option><option value="5.250">5.250%</option><option value="5.375">5.375%</option><option value="5.500">5.500%</option><option value="5.625">5.625%</option><option value="5.750">5.750%</option><option value="5.875">5.875%</option><option value="6.000">6.000%</option><option value="6.125">6.125%</option><option value="6.250">6.250%</option><option value="6.375">6.375%</option><option value="6.500">6.500%</option><option value="6.625">6.625%</option><option value="6.750">6.750%</option><option value="6.875">6.875%</option><option value="7.000">7.000%</option><option value="7.125">7.125%</option><option value="7.250">7.250%</option><option value="7.375">7.375%</option><option value="7.500">7.500%</option><option value="7.625">7.625%</option><option value="7.750">7.750%</option><option value="7.875">7.875%</option><option value="8.000">8.000%</option><option value="8.125">8.125%</option><option value="8.250">8.250%</option><option value="8.375">8.375%</option><option value="8.500">8.500%</option><option value="8.625">8.625%</option><option value="8.750">8.750%</option><option value="8.875">8.875%</option><option value="9.000">9.000%</option><option value="9.125">9.125%</option><option value="9.250">9.250%</option><option value="9.375">9.375%</option><option value="9.500">9.500%</option><option value="9.625">9.625%</option><option value="9.750">9.750%</option><option value="9.875">9.875%</option><option value="10.000">10.000%</option></select></li><li><label>'+mortage_cal_loan_term_tools+' </label><select data-role="none" class="select_rate" id="loan_term"><option value="480">40 Yr. </option><option selected="selected" value="360">30 Yr. </option><option value="300">25 Yr. </option><option value="240">20 Yr. </option><option value="180">15 Yr. </option><option value="120">10 Yr. </option></select></li><li><label>'+mortage_cal_annual_prop_tax_tools+'</label><input data-role="none" type="tel" id="proptexes" name="PropertyTax" placeholder="Property Taxes"/></li><li><label>'+mortage_cal_insurance_tools+'</label><input data-role="none" type="tel" id="insurance" name="Insurance" placeholder="Insurance"/></li><li><label>'+mortage_cal_monthly_amt_tools+'</label><label id="monthly_payment" class="loan_amount">'+mortage_cal_amt_tools+'</label></li><li><button type="button" name="calculate" value="Calculate" onclick="get_monthly_payment()" class="calculate_mor_button">'+mortage_cal_calculate_tools+'</button></li><li><div class="span_text">'+mortage_cal_estimate_monthly_cal_tools+'</div></li></ul></aside></section>'
    '<div class="page-text">' + hellocalc + '</div>'
  //  appendHtml(hellocalc,'2','2');
    appendHtml(hellocalc,2,2);
}


function getBarCodeText(txt1, txt2) {
	//alert(txt1);
	//alert(txt2);
	                        if(ValidUrl(txt1)) {
	                            sessionStorage.setItem("barcodeURL",txt1);
	                            barcode_view(txt1,txt2,"true");
	                            }
	                            else {
	                            barcode_view(txt1,txt2,"false");
	                            }
	}

function barcodeScanner(firstValue,index,secondValue) {
    // toaster.setOrientationForBarcodeCancel();
	 toaster.callBarCodeReader();
}

function barcode_view(text,format,isURL){
    
	if(sessionStorage.getItem("memberfromLoyalty") == 'true')
    {
        document.getElementById('StampTxtFldMember').value='';
        document.getElementById('StampTxtFldMember').value=text;
        var func=$('#stampCardBtnMember').attr('onclick');
        window.setTimeout(func,10);
        sessionStorage.removeItem("memberfromLoyalty")
        return false;
    }
  else if(sessionStorage.getItem("fromLoyalty") == 'true')
    {
        $('#StampTxtFld').attr('value',text);
        var func=$('#stampCardBtn').attr('onclick');
        window.setTimeout(func,10);
		sessionStorage.setItem('FromScaner', 'true');
        sessionStorage.setItem("codeValue",text);
        sessionStorage.removeItem("fromLoyalty")
        return false;
    }


    if(isURL=="true"){
       // alert("isURL");
        var barcodeHtml = '<div class="bar_main">\
        <div class="top_text" >Barcode Information</div>\
        <div class="img"><img src="images/barcodeScanner.png" width="100%" height="170"  alt=""/></div>\
        <div class="barcode_details">\
        <ul class="123">\
        <li class="no_dis" onclick="openBarcodeLink()">Item: "'+text+'"</li>\
        <li class="code_dis">Format: "'+format+'"</li>\
        </ul>\
        </div>\
        </div>';
    }
    else {
		//alert("else isURL");
         var barcodeHtml = '<div class="bar_main">\
        <div class="top_text" >Barcode Information</div>\
        <div class="img"><img src="images/barcodeScanner.png" width="100%" height="170"  alt=""/></div>\
        <div  class="barcode_details">\
        <ul  class="123">\
        <li  ><div class="no_dis">Item: "'+text+'"</div></li>\
        <li  class="code_dis">Format: "'+format+'"</li>\
        </ul>\
        </div>\
        </div>';
    }
    appendHtml(barcodeHtml,2,2);
}
function mapScreen(){
    $('.appypie-loader').show();
    if(toaster.isLocationEnabled())
    {
        if (navigator.geolocation){
            navigator.geolocation.getCurrentPosition(showMapPosition);
        }
    }
    else
    {
        toaster.openLocationSetting();
    }
}

function showMapPosition(position){
 setTimeout(function() {
		 $('.appypie-loader').hide();
	  },100);
    latitude=position.coords.latitude;
    longitude=position.coords.longitude;
    
    window.location="maplocation:"+latitude+"&&"+longitude+"&&"+"abc";
    
    /*
     var geocoder = new google.maps.Geocoder();
     var latLng = new google.maps.LatLng(latitude,longitude);
     
     if(geocoder) {
     geocoder.geocode({ 'latLng': latLng}, function (results, status) {
     if (status == google.maps.GeocoderStatus.OK) {
     window.location="maplocation:"+latitude+"&&"+longitude+"&&"+results[0].formatted_address;
     }
     else {
     
     }
     });
     }
     */
}

function ValidUrl(str) {
    var pattern = new RegExp('^(https?:\\/\\/)?'+ // protocol
                             '((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.)+[a-z]{2,}|'+ // domain name
                             '((\\d{1,3}\\.){3}\\d{1,3}))'+ // OR ip (v4) address
                             '(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*'+ // port and path
                             '(\\?[;&a-z\\d%_.~+=-]*)?'+ // query string
                             '(\\#[-a-z\\d_]*)?$','i'); // fragment locator
    if(!pattern.test(str)) {
        return false;
    } else {
        return true;
    }
}
function openBarcodeLink(){
    sessionStorage.setItem("websitePageFlag","false");
   /* if(window.localStorage.getItem("layout")=="slidemenu"){
        $("#reload").hide();
        $("#mainback").show();
    }
    else {
        $("#logo").hide();
        $("#mainback").show();
    }*/
     barcodeLink=sessionStorage.getItem("barcodeURL");
     openWebPage(barcodeLink,2);     

    
}


function videoRecorder()
{
   if(window.localStorage.getItem("_isSwitchedOn1")=='true')
    {
        window.localStorage.setItem("_isSwitchedOn1","false");
        $('#torchimageonoff').attr("src", "images/torch-off.png");
        toaster.switchTorch("off");
    }
    
    setTimeout(function() {
     toaster.Recorder();
  },10);
}

function curConverter()
{
   openWebPage('http://'+window.localStorage.getItem("reseller")+'/cool-currency-converter/',pagelevelcurrencyconverter);
}

function openImageEditor(){
    if(editorClientId==' '){
        alert("Client Id can not be blank!");
        return;
    }
    if(editorClientSecret==' '){
        alert("Client Secret can not be blank!");
        return;
    }
    toaster.customFormBuilderGetfile(987);
}