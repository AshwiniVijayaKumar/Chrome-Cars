/////////////////Survey////////////////////////
/////////////////Survey////////////////////////
function getsurvey(index)
{
    if(checkNetworkConnection())
    {
	$surveyXmlData = $(masterData).find( "Survey[indexval="+index+"]" );
        var surveyHtml='';
        var valueSurveyPage=0;
        var funcName='';
        $surveyXmlData.find("SurveyRow").each(function()
                                            {
                                            var pageName=$(this).find('SurveyName').text();
                                            var imageUrl=$(this).find('SurveyImg').text();
                                            var SurveyUrl=$(this).find('SurveyUrl').text();
                                            var surveyImageHtml='';
                                            funcName='openWebPage(\''+SurveyUrl+'\',1)';
                                              surveyHtml+='<span class="audio_list" onclick="openWebPage(\''+SurveyUrl+'\',2)">'+returnImageHtml(imageUrl)+' <span>'+pageName+'</span></span>';
                                              valueSurveyPage++;
                                             });
        if(valueSurveyPage == 1)
        {
            window.setTimeout(funcName,10);
            sessionStorage.setItem("singlePage","true");
        }
        else
        {
            setTimeout(function(){
                       appendHtml(surveyHtml,'',1);
                       },100);
        }
    }
}


