////////////////////Share////////////////////
function getreligious(index)
{
    if(checkNetworkConnection())
    {
    window.location="removewebsite:"
    var religiousPageHtml="";
    $religiousIndex = $(masterData).find( "religious[indexval="+index+"]" );
    var religiousId = $religiousIndex.find("religiousId").text();
    religiousId = religiousId.split(',');
    var religiousnewId = $religiousIndex.find("religiousName").text();
    religiousnewId = religiousnewId.split(',');
    if(religiousId.length==1)
    {
        sessionStorage.setItem("fromSingleReligious", "true");
        openReligiousPage(religiousId[0]);
    }
    else
    {
        for(var i=0;i<religiousId.length;i++)
        {
            var imageUrl=$religiousIndex.find(religiousId[i]+"ImageUrl").text();
        religiousPageHtml+='<span class="audio_list" onclick="openReligiousPage(\''+religiousId[i]+'\')">'+returnImageHtml(imageUrl)+'<span>'+religiousnewId[i]+'</span></span>';
        }
        
        appendHtml(religiousPageHtml,'',1);
    }
    }
}

function openReligiousPage(pageName)
{
    if(pageName == 'quran')
    {
       //var url="http://mobile.qurandislam.com";
	   var url="http://quran.ksu.edu.sa";
    }
    else if(pageName == 'gita')
    {
       var url="http://www.bhagavad-gita.org/";
    }
    else{
       var url="https://www.bible.com/bible/1/jhn.1.kjv";
    }
    if (sessionStorage.getItem("fromSingleReligious") == "true")
    {
        openWebPage(url,1);
    }
    else
    {
        openWebPage(url,2);
    }
}

