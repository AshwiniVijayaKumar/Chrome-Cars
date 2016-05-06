//////////////////////Education//////////////////////////
var _index;

var khanVideoIdList=[];
var checkForLevel=0;

var enterthekeywordtosearch_education = "Enter the keyword to search";
var search_education = "Search";
var suggestions_educatiuon = " Suggestions: ";
var pleaseconnecttointernet_education = "Please Connect To Internet";
var pleasarch_education = "Please select option to search.";
var nomatch_education = "No Match Found!";
var pleasegain_education = "Please Try Again";
var selecategory_education = "Select a category";
var or_education = "OR";
function geteducation(index)
{

if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
  /* $("#reload").show();
    $("#bookmark").hide();
    $("#mainbackfoodecom").hide();*/
	_index=index;
	
	var educationHTML="";
	$(masterData).find("Education").each(function () {
		
		var id = $(this).attr('indexval');
		if(id==index)
		{
			var EducationId= ($(this).find("EducationId").text() );
			var EducationNewId= ($(this).find("EducationNewId").text() );
			var khanAcademyImg= ($(this).find("khanAcademyImg").text() );
			var dictionaryImg= ($(this).find("dictionaryImg").text() );
			var EducationIdArray = EducationId.split(',');
			var EducationNewIdArray = EducationNewId.split(',');
			
			if(EducationIdArray.length == 1)
            {
			var loadMethod=EducationIdArray[0].trim();
			checkForLevel=1;
			window[loadMethod]();
			//return;
			}
			else
			{
			var opt='';
			var pointer=0;
			jQuery.each(EducationIdArray, function() {
				var imageHtml='';
				

				if(EducationIdArray[pointer] =="KhanAcademy")
					{
					imageHtml=returnImageHtml(khanAcademyImg);
					
					}
				else
					{
					
					imageHtml=returnImageHtml(dictionaryImg);
					}
	
				opt=this.trim();

				var jsfilename="js/"+opt+".js";

				console.log(jsfilename);
				educationHTML+='<span class="appypie-list" onclick="'+opt+'()" id="'+ opt +'" class="education_list">'+imageHtml+'<span>'+ EducationNewIdArray[pointer].trim() +'</span> </span>';
				
				pointer++;
			});			
			$("#contentHolder").empty();
			appendHtml(educationHTML,'',1);
			
			}
		}
       
	});
	//setTimeout('hideModal()',1000);
}


/////////////////////Dictionary//////////////////
var htmlEdu2='';
var listEdu='';
var htmlEdu='';
var search='';
var KhanDataId='';
var KhanDataTittle='';
var KhanData='';
var KhanVideoData='';
var xmldataEdu='';
function Dictionary()
{

/* if(window.localStorage.getItem("layout")=="slidemenu"){
				$("#reload").hide();
				$("#mainback").show();	
			}
			else {
				$("#logo").hide();
				$("#mainback").show();	
			}*/
 if(checkForLevel==1)
 {
 appendHtml('<div class="page-content" id="page-text-page-content2"></div>',2,1);
 }
 else
 {
 appendHtml('<div class="page-content" id="page-text-page-content2"></div>',2,2);
 }

var checkdict='<div id="education-inner-detail"><span><input data-role="none" type="text" value="" placeholder="Enter the keyword to search" id="search" /><input data-role="none" type="button" style="background-color:'+sessionStorage.getItem("primaryButtonBgColor")+'" value="'+search_education+'" onclick="SearchDictionary();" text="search" /></span></div><div  id="result"></div>';
   
$("#page-text-page-content2").append(checkdict);
$.mobile.changePage('#page3', { transition: 'slide'} );

}
function SearchDictionary(search)
{   
 
    
    pathEdu='http://www.dictionaryapi.com/api/v1/references/thesaurus/xml/'+document.getElementById("search").value.toLowerCase()+'?key=118ba3e4-8dce-4bb7-a949-a5c8ccced33a';
 search=document.getElementById("search").value;
 console.log(pathEdu);
    MyWebservice_Education(pathEdu,search);
   
    doneEdu();
}
function doneEdu()
{   

}
function CreateDictionary(xmldataEdu)
{   
   
}
function MyWebservice_Education(pathEdu,search)
{
    hello='';
    var xhr = new XMLHttpRequest();
    
    
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState==4)
        {
            if (xhr.status==200&&xhr.responseText!=null)
            {
                
                console.log(xhr.responseText);
                xmldataEdu=$.parseXML(xhr.responseText);
                htmlEdu2='<div id="education-inner-detail-content"><div class="page-text"><h2>'+search+'</h2><ul>';
                var list123=xmldataEdu.getElementsByTagName('entry');
                if(list123.length>0)
                {
                listEdu=list123.length;
                
                for(var i=0;i<listEdu;i++)
                {
                    
                    Main=xmldataEdu.getElementsByTagName('entry')[i].getAttribute('id');
                    Part=xmldataEdu.getElementsByTagName('term')[i].getElementsByTagName('hw')[0].childNodes[0].nodeValue;
                    Definition=xmldataEdu.getElementsByTagName('mc')[i].childNodes[0].nodeValue;
                    Sentences=xmldataEdu.getElementsByTagName('vi')[i].childNodes[0].nodeValue;
                    Synonyms=xmldataEdu.getElementsByTagName('syn')[i].childNodes[0].nodeValue;
                    htmlEdu2+='<li><p><b>Main Entry: </b>'+Main+'</p><p><b>Part of Speech: </b>'+Part+'</p><p><b>Definition: </b>'+Definition+'</p><p><b>Sentences:--</b><br><span>1 --'+Sentences+'</span><br></p><p><br><b>Synonyms: </b>'+Synonyms+'</p></li></ul>';
                }
                htmlEdu2+='</ul></div></div>';
                //console.log"CreateDictionary");
                console.log(htmlEdu2);
                
                $("#result").html(htmlEdu2);
                }
                else
                {
                	
                	
                 var list123=xmldataEdu.getElementsByTagName('suggestion');
                    listEdu=list123.length;
                    
                    htmlEdu2='<div id="education-inner-detail-content"><div class="page-text"><h2>'+search+ suggestions_educatiuon+'</h2><ul>';
                    for(var i=0;i<listEdu;i++)
                    {
                         var suggest=xmldataEdu.getElementsByTagName('suggestion')[i].childNodes[0].nodeValue;
                        htmlEdu2+='<li>'+suggest+'</li></ul>';
                    }
                    htmlEdu2+='</ul></div></div>';
                    
                    $("#result").html(htmlEdu2);

                }
                
            }
            else
            {   // alert("success");
                console.log("fail");
                return "could not read file: "+path ;
            }
        }
    };
    
    
    xhr.open("GET", pathEdu, false);
    xhr.send();
    
}
//////////////////////////Khan Acadmy///////////////////////
var htmlVideo='';
var search='';
var KhanDataId='';
var KhanDataTittle='';
var KhanData='';
var KhanVideoData='';
var htmlEdu2='';
var listEdu='';
var htmlEdu='';
var pag=1;

function KhanAcademy()
{
   /* if(window.localStorage.getItem("layout")=="slidemenu"){
				$("#reload").hide();
				$("#mainback").show();	
			}
			else {
				$("#logo").hide();
				$("#mainback").show();	
			}*/
    $('.appypie-loader').show();
	htmlEdu='';
    htmlEdu2='';
    search='';
   var KhanUrl='http://www.khanacademy.org/api/v1/playlists';
    $.getJSON(KhanUrl, function(value){
              KhanData=value;
              if(KhanData == null)
             {
			 setTimeout(function(){$('.appypie-loader').hide();},1000);
			 
             alert(pleaseconnecttointernet_education);
			  }
              KhanList();
              });


   
   
}
function SearchKhanVideos()
{   
//console.log('search');
	$('.appypie-loader').show();
   var search1= document.getElementById('KhanSelect').selectedIndex; 
   search1=document.getElementsByTagName("option")[search1].value;
    var search2=document.KhanForm.search_KhanAcademyCat.value;

	//console.log(search2);
    //alert(search2+">>>>>>>>>>>"+search1);
	 if(search2 != '')
    {
     	var one=search2.toLowerCase();
        var two=one.replace(" ","-");
        //alert(two);
        var SearchUrl='http://www.khanacademy.org/api/v1/videos/'+two;
        //alert(SearchUrl);
        SearchVideos(SearchUrl);
    }
    else if(search1 != '')
    {   
        var SearchUrl='http://www.khanacademy.org/api/v1/topic/'+search1+'/videos';
        SearchVideos(SearchUrl);
    }
	else
	{
	    $('.appypie-loader').hide();
	    
	   
	    
	    alert(pleasarch_education);
	}
   // alert("Invalid Input");
}
function SearchVideos(SearchUrl)
{
	    $.ajax({
                url:        SearchUrl,
                dataType:   "jsonp", // <== JSON-P request
                success:    function(value){
                 // alert(value);
              //KhanDataTittle=value.translated_title;
              KhanVideoData=value;
             // alert(KhanVideoData.length);
              if(KhanVideoData != null)
              {
              if(KhanVideoData.length == null || KhanVideoData.length == '')
              {
              KhanSearchVideoPage(KhanVideoData);
              }
              else
              {
              KhanVideoPage(KhanVideoData);
              }
              }
              else
              {
			   $('.appypie-loader').hide();
			  
			   
              alert(nomatch_education +"\n"+ pleasegain_education);
              }
              },
			  error:function(value)
			  {
			   $('.appypie-loader').hide();
              //alert("No Match Found!\n Please Try Again");
			   alert(nomatch_education +"\n"+ pleasegain_education);
			  }
            });
}

function KhanList()
{   
	
	
	
	 htmlEdu='<div id="education-inner-detail"><div class="khanSearch"><form name="KhanForm"><select name="KhanSelect" id="KhanSelect"><option value="">'+selecategory_education+'</option>';
    for(var i=0;i<KhanData.length;i++)
    {
        htmlEdu+='<option value='+KhanData[i].slug+'>'+KhanData[i].translated_title+'</option>';
    }
    htmlEdu+='</select><span class="khanSearchOR"><span>'+or_education+'</span></span><input data-role="none" type="text" id="search_KhanAcademyCat" name="search_KhanAcademyCat"><a onclick=\'SearchKhanVideos()\' class="edu_serBtn" '+primaryColor+'> '+search_education+'</a></form></div></div>';
	$("#contentHolder2").empty();
	
	if(checkForLevel==1)
 {
appendHtml(htmlEdu,2,1);
 }
 else
 {
 appendHtml(htmlEdu,2,2);
 }
	
	
	
}



function KhanVideoPage(KhanVideoData)
{   
    /*if(window.localStorage.getItem("layout")=="slidemenu"){
				$("#reload").hide();
				$("#mainback").show();	
			}
			else {
				$("#logo").hide();
				$("#mainback").show();	
			}*/
    htmlVideo='<div id="education-inner-detail-content"><div class="page-text"><div id="khan-academy"><ul>';
	khanVideoIdList.splice(0,khanVideoIdList.length);
    for(var i=0;i<KhanVideoData.length;i++)
    {
	    var downloadUrl=KhanVideoData[i].download_urls;
		downloadUrl=downloadUrl.m3u8;
		var videoId=downloadUrl.substring(downloadUrl.lastIndexOf("/") + 1, downloadUrl.length-5);
		khanVideoIdList.push(videoId);
		var imageSRC="http://img.youtube.com/vi/"+videoId+"/0.jpg";
		htmlVideo+='<li onclick="playKhanAcademyVideo('+i+')" class="active" id="khanAcademy_video_name0"><h3 tabindex="1">'+KhanVideoData[i].translated_title+'</h3><span class="author">'+KhanVideoData[i].author_names+'</span><video poster='+imageSRC+' width="100%" height="100%" autoplay="autoplay"><source src="video/mp4.mp4" type="video/mp4" /><source src="video/ogv.theora.ogv" type="video/ogg" /></video><span>'+KhanVideoData[i].description+'</span><input data-role="none" type="hidden" value='+KhanVideoData[i].png+' id="khanAcademy_video_pic0"></li>';

    }
    htmlVideo+='</ul></div></div></div>';
    KhanVideoDetailPage();
}

function KhanSearchVideoPage(KhanVideoData)
{   
   khanVideoIdList.splice(0,khanVideoIdList.length);
   htmlVideo='<div id="education-inner-detail-content"><div class="page-text"><div id="khan-academy"><ul>';
    var downloadUrl=KhanVideoData.download_urls;
	downloadUrl=downloadUrl.m3u8;
	var videoId=downloadUrl.substring(downloadUrl.lastIndexOf("/") + 1, downloadUrl.length-5);
	khanVideoIdList.push(videoId);
	var imageSRC="http://img.youtube.com/vi/"+videoId+"/0.jpg";
	htmlVideo+='<li onclick="playKhanAcademyVideo('+0+')" class="active" id="khanAcademy_video_name0"><h3 tabindex="1">'+KhanVideoData.translated_title+'</h3><span class="author">'+KhanVideoData.author_names+'</span><video poster='+imageSRC+' width="100%" height="100%" autoplay="autoplay"><source src="video/mp4.mp4" type="video/mp4" /><source src="video/ogv.theora.ogv" type="video/ogg" /></video><span>'+KhanVideoData.description+'</span><input data-role="none" type="hidden" value='+KhanVideoData.png+' id="khanAcademy_video_pic0"></li>';
    htmlVideo+='</ul></div></div></div>';
    KhanVideoDetailPage();
}
function KhanVideoDetailPage()
{
	
	
	
    $('.appypie-loader').show();
	pag=2;
	$("#contentHolder3").empty();
	$("#contentHolder3").append("<div class='page-text'>"+htmlVideo+"</div>");
	$.mobile.changePage('#page4', { transition: 'slide',allowSamePageTransition: true});
	setTimeout(function(){$('.appypie-loader').hide();},1000);
}

function playKhanAcademyVideo(j){
	for(var i=0;i<khanVideoIdList.length;i++) {
	    if(j==i) {
			 window.plugins.youtube.show({videoid:khanVideoIdList[i], videoInfo:""}, function() {}, function() {} );
		}
	}
}