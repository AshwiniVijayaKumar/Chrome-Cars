////////////////////////////////////////Map//////////////////////////////////

var latArray;
var lngArray;
var addrArray;
var imageforsingle;
var imageforsingleArray;
var textforsingleArray;
var textforsingle;
var latitude;
var longitude;

var geolocationnotsupportedbrowser_map = "Geolocation is not supported by this browser.";

function getmap(index)
{
 if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
  /*  $("#reload").show();
    $("#bookmark").hide();
    $("#mainbackfoodecom").hide();*/
    $('.appypie-loader').show();
		var mapHTML="";
	
	$(masterData).find("map").each(function () {
		var id = $(this).attr('indexval');
		if(id==index)
		{
		latArray=new Array();
		lngArray=new Array();
		addrArray=new Array();
		imageforsingleArray=new Array();
		textforsingleArray=new Array();
		$(this).find("MapRow").each(function () {
		latArray.push($(this).find("latitude").text());
		lngArray.push($(this).find("longitude").text());
		imageforsingle=$(this).find("innerimage").text();
		textforsingle=$(this).find("text").text();
		if($(this).find("subtext").text().trim()!="")
		{
		addrArray.push($(this).find("subtext").text().replace(/,/g,'commaseprator'));
		}
		else
		{
		addrArray.push("no address");
		}
		
		imageforsingle=imageforsingle.trim();
        if(imageforsingle.length > 1)
        {
		imageforsingleArray.push(imageforsingle);
        }
		textforsingle=textforsingle.trim();
        if(textforsingle.length > 0)
        {
        textforsingleArray.push(textforsingle);
        }
		
		});
		
		setTimeout(function() {	
			if (navigator.geolocation){
				navigator.geolocation.getCurrentPosition(showPosition);
			}
			else{
				
				document.getElementById('currentAddress').value=geolocationnotsupportedbrowser_map;
			}
		},10);
		}
	});
}

function showPosition(position){ 
    latitude=position.coords.latitude;
    longitude=position.coords.longitude;
	var directoryDataToMap;
	 setTimeout(function(){$('.appypie-loader').hide();},400);
	 
	 var maplist='';
	 if(latArray.length>1)
    {
            for(var i=0;i<addrArray.length;i++)
            {
			
			var imageUrl="";
    if(imageforsingleArray[i])
    {
     imageUrl=imageforsingleArray[i];
    }
    else
	{
    imageUrl="icon-location";
    }
			console.log("address data"+addrArray[i].replace(/commaseprator/g,','));
            maplist+='<div class="list" style="color:'+sessionStorage.getItem('pageTextColor')+'; !improtant" onclick="mappageopensingle(\''+latArray[i]+'\',\''+lngArray[i]+'\',\''+addrArray[i]+'\');"><div class="page_innerIcon">'+returnImageHtml(imageUrl)+textforsingleArray[i]+'</div>'+addrArray[i].replace(/commaseprator/g,',')+'</div>';
            }
			 setTimeout(function(){
                 appendHtml(maplist,2,1);
                       },100);
	}
	else
	{
	mappageopensingle(latArray[0],lngArray[0],addrArray[0]);
	}
       
           
           // directoryDataToMap="mappagedir:"+latArray+"&&"+lngArray+"&&"+addrArray;
			//toaster.showDirectoryOnMap(directoryDataToMap);
} 

function mappageopensingle(lat1,lng1,add1)
{
var appId=localStorage.getItem("applicationID");
if(appId == 'ed33b19831c5' || appId == 'e0c2d735f46a' || appId=='cc3052bb8850' || appId=='ade727c1484c' || appId=='12667f32a13c' || appId=='6fce880ac2c6' || appId=='dba50f8ea3be' || appId=='a803984e3524' || appId == 'fcc0b97a13f8')
    {
	$("#contentHolder7").css({"background-color": "#000"});
    	 appendHtml("<div class='page-text'></div>",7,1);
              
               
              if(window.localStorage.getItem("layout")=="slidemenu"){
            $("#reload").hide();
            $("#mainback").show();
        }
        else {
            $("#logo").hide();
            $("#mainback").show();
        }
	add1=add1.replace(/commaseprator/g,',');
  var websiteLink="http://maps.google.com/maps?q="+lat1+","+lng1+"&t=m&z=16";
    var websiteLinkCustom="http://maps.google.com/maps?q="+add1+"&t=m&z=16";

  if(window.localStorage.getItem("layout")=="bottom") {
            window.location="bottom"+websiteLink;
        }
        else {
            if(appId == 'fcc0b97a13f8')
        	{
        		window.location="show"+websiteLinkCustom;
        	}
        	else
        	{
                window.location="show"+websiteLink;

        	}
        }
	return true;
	}
var directoryDataToMap;
latArray=new Array();
lngArray=new Array();
addrArray=new Array();
latArray.push(lat1);
lngArray.push(lng1);
//add1=add1.replace('$$###',',');
addrArray.push(add1);
            directoryDataToMap="mappagedir:"+latArray+"&&"+lngArray+"&&"+addrArray;
			toaster.showDirectoryOnMap(directoryDataToMap);
}