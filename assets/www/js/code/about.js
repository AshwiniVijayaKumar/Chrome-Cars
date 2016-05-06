//about page

function getAboutUs(index)
{

	$('.appypie-loader').show();
	var aboutData = $(masterData).find("aboutUs[indexval="+index+"]");
	var aboutHTML = '<div class="about-us">\
    	<h1></h1>\
        <p class="description"></p>\
        <ul class="founderList"></ul>\
		<ul class="missionList"></ul>\
    </div>'
	
	var founderHTML = '<li>\
	<img src="about-us-pic.png" />\
	<h3></h3>\
	<p></p>\
	<div class="smo-urls"></div>\
	</li>'
	
	var missionHTML = '<li>\
	<h3></h3>\
	<p></p>\
	</li>'	
	appendHtml(aboutHTML,'',1);
	
	$(".about-us h1").text(aboutData.find("title").eq(0).text());
	
	var desc = aboutData.find("Description").eq(0).text().trim();
	
	$(".about-us p.description").html(desc);
	if(desc.length < 3){
		$("p.description").hide();
	}
	aboutData.find("founderList row").each(function(index){
		var thisRow = $(this);
		var title = thisRow.find("title").text();
		console.log("Founder Title ... "+title);
//		alert("Title Length "+title.length);
		
		$(".about-us .founderList").append(founderHTML);
		
		var founderList = $(".about-us .founderList li").eq(index);
		if(thisRow.find("image").text() == "none")
		{
			founderList.find("img").attr("src", "images/about-us-pic.png").remove();
		}
		else
		{
			founderList.find("img").attr("src", thisRow.find("image").text());
		}
		founderList.find("h3").html(thisRow.find("label").text() + '<span>'+thisRow.find("title").text()+'</span>');
		founderList.find("p").html(thisRow.find("text").text());
		
		thisRow.find("url").each(function(urlIndex){
			var thisURL = $(this);
		//	alert("Url Length "+thisURL.find("urlPath").text().length);
			if(thisURL.find("urlPath").text().length > 1){
				founderList.find(".smo-urls").append('<a  onClick="openWebPage(\''+thisURL.find("urlPath").text()+'\',2)">'+returnImageHtml(thisURL.find("urlImage").text())+'</a>')
			}
			else{
				console.log("smo.url hide ");
			//	$(".smo-urls").hide();
			}
//			founderList.find(".smo-urls").append('<a href="'+thisURL.find("urlPath").text()+'">'+returnImageHtml(thisURL.find("urlImage").text())+'</a>')
		})
		
		if(thisRow.find("url").size() == 0)
		{
			founderList.find(".smo-urls").remove();
		}
	})
	
	
	aboutData.find("missionList row").each(function(index){
		var thisRow = $(this);
		var missionText = thisRow.find("text").text().trim();
		if(missionText.length > 3){
			$(".about-us .missionList").append(missionHTML);
			
			var missionList = $(".about-us .missionList li").eq(index);
			missionList.find("h3").html(returnImageHtml(thisRow.find("image").text()) + thisRow.find("label").text());
			missionList.find("p").html(thisRow.find("text").text());
		}
	})
	var foundedYear = aboutData.find("foundedYear").text();
	if(foundedYear.length > 1){
		$(".about-us .missionList").prepend('<li><h3><span class="foundedicon">'+returnImageHtml(aboutData.find("foundedIcon").text())+aboutData.find("foundedTitle").text()+' :</span> <span class="year">'+aboutData.find("foundedYear").text()+'</span></h3></li>');
	}
	else{
//		$(".about-us .missionList").prepend('<li><h3><span>'+returnImageHtml(aboutData.find("foundedIcon").text())+aboutData.find("foundedTitle").text()+' :</span> <span class="year">'+aboutData.find("foundedYear").text()+'</span></h3></li>');
	}
	var backgroundOption = aboutData.find("aboutBackground").text();
	var backgroundLandscape = aboutData.find("aboutIpadBackground").text();
	var layoutOption = aboutData.find("aboutLayout").text();
	var headingOptions = aboutData.find("aboutHeading").text().split(",");
	var subHeadingOptions = aboutData.find("aboutsubHeading").text().split(",");
	var contentOptions = aboutData.find("aboutContent").text().split(",");
	var iconOptions = "";
	if(aboutData.find("aboutIcon").text().indexOf("rgba") > -1)
	{
		iconOptions = aboutData.find("aboutIcon").text().split("#");	
	}
	else{
		iconOptions = aboutData.find("aboutIcon").text().split(",");
	}
	

	var aboutObj = $(".about-us");

	aboutObj.addClass(gFontsCheck(contentOptions[0])).addClass(contentOptions[1]).css("color", contentOptions[2])	
	aboutObj.find("h1").addClass(gFontsCheck(headingOptions[0])).addClass(headingOptions[1]).css("color", headingOptions[2]);
	aboutObj.find("h3").addClass(gFontsCheck(subHeadingOptions[0])).addClass(subHeadingOptions[1]).css("color", subHeadingOptions[2]);

	aboutObj.find("a").css("background", iconOptions[0].substring(0, iconOptions[0].length - 1));
//	aboutObj.find("i").css("color", "#"+iconOptions[1]);
	aboutObj.find("i").attr("style", 'color:#'+iconOptions[1]);

	aboutObj.find(".year, .founderList h3 span").css("color", contentOptions[2]);
	aboutObj.addClass("heading-" + headingOptions[3]).addClass("subHeading-" + subHeadingOptions[3]).addClass("content-" + contentOptions[3]).addClass("layout-" + layoutOption)
//	alert("Orientation "+sessionStorage.getItem("orientationstatus"));

	
	if(backgroundOption.charAt(0)=="#"){
		aboutObj.css("background-color", backgroundOption);	
	}
	else if(backgroundOption != "none")
	{
		if(backgroundLandscape == "none"){
			backgroundLandscape = backgroundOption;
		}

		var styleStr  = '<style>';
			styleStr += '@media screen and (orientation:portrait) {';
			styleStr += '.about-us {background-image:url('+backgroundOption+')}';
			styleStr += '}';
			styleStr += '@media screen and (orientation:landscape) {';
			styleStr += '.about-us {background-image:url('+backgroundLandscape+')}';			
			styleStr += '}</style>';
		$.mobile.activePage.find(".page-content").prepend(styleStr);		
		aboutObj.addClass("bg-image");	
	}
	
}