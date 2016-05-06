
function getqrcode(index){
/*$("#reload").show();
    $("#bookmark").hide();
    $("#mainbackfoodecom").hide();*/
	
    $getqrcodeIndex = $(masterData).find( "QR[indexval="+index+"]" );
    var QrImgUrl=$getqrcodeIndex.find("QrImgUrl").text();
	var img='<img width="250"  src="'+QrImgUrl+'"/>';
	 appendHtml("<center><div class='qrcode'>"+img+"</div></center>",'',1);
	 heightcoding();
}
function heightcoding (){
	$(".qrcode").css('height',$("#contentHolder").height()); 

}