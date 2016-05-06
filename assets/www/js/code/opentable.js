//  open table page
function getOpentable(index)
{
    if(!checkNetworkConnection())
    {
       /* $("#logo").show();
        $("#mainback").hide();
        $("#shopmenu").hide();*/
    }
    else
    {
        $opentableXmlData = $(masterData).find( "opentable[indexval="+index+"]" );
        var opentablekPageUrl=($opentableXmlData.find("opentableUrl").text());
        openWebPage(opentablekPageUrl,1);
    }
}