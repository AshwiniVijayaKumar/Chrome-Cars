var resellerInitial="";
var reseller='';
var wsUrl='';
var soapRequest='';
var html='';
var appid='';
var productlist = '';

function getSchedulingMultipleData(index)
{
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
	//console.log("gourav inside js");
    
    if(window.localStorage.getItem("layout")=="slidemenu"){
        snapper.close();
    }
    sessionStorage.setItem("AppPageName","scheduling");
    
    /*setTimeout(function(){
               $("#mainmenu").empty();
               $("#mainmenu").append('<a onclick="getEcommerceData()" class="home-nav">'+returnIconImageHtml('iconz-home')+' <span style="color:'+sessionStorage.getItem('navTextColor')+' !important">Home</span></a><a onclick="loginEcommerce()" class="login-nav">'+returnIconImageHtml('iconz-log-in')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">Login</span></a><a onclick="myaccountEcommerce()" class="account-nav">'+returnIconImageHtml('iconz-user')+' <span style="color:'+sessionStorage.getItem('navTextColor')+' !important">My Account</span></a><a onclick="termsEcommerce()" class="terms-nav">'+returnIconImageHtml('icon-info-circled')+' <span style="color:'+sessionStorage.getItem('navTextColor')+' !important">Terms</span></a><a onclick="PolicyEcommerce()" class="policy-nav">'+returnIconImageHtml('iconz-briefcase')+' <span style="color:'+sessionStorage.getItem('navTextColor')+' !important">Privacy Policy</span></a><a onclick="goHomeEcom()" class="menu-nav slideMainMenuBack">'+returnIconImageHtml('icon-left-1')+'  <span style="color:'+sessionStorage.getItem('navTextColor')+' !important">Main Menu</span></a>');
               },1000);*/
    
    
  
    
  /*  if(localStorage.getItem("fooduserid"))
    {
        
        accountDispl='block';
        loginDispl='none';
    }
    else
    {
        accountDispl='none';
        loginDispl='block';
    }*/
    
    appid=window.localStorage.getItem("applicationID");
    reseller=window.localStorage.getItem("reseller");
    sessionStorage.setItem("schedulingIndex",index);
  
    if(!checkNetworkConnection())
    {
    }else
    {
    	
    	  var html2='';
    	 $ecomData= $(masterData).find("Scheduling[indexval=" + index + "]");
    	 if($ecomData.find("schedulingId").text().indexOf(",")==-1)
    	 {
    		 var ecomArray = $ecomData.find("schedulingId").text();
    		 var weburl = $ecomData.find(ecomArray + "Url").text();
    		 var pagelevel = '1';
    		 openWebPage(weburl , pagelevel);
    		
    		 //alert("rohit:" + $ecomData.find("ecomsId").text().indexOf(","));
    		 //getEcommerceData(index);
    		 
    		 return true;
    	 }
    	 else
    	 {
    			console.log("gourav inside else");

    		 var ecomArray = $ecomData.find("schedulingId").text().split(",");
    		 
    		 for(var i= 0; i < ecomArray.length ; i++)
    		 {
    			 var name = $ecomData.find(ecomArray[i]+ "Name").text();
    			 var image = $ecomData.find(ecomArray[i]+ "ImageCss").text();
    			 var weburl = $ecomData.find(ecomArray[i]+ "Url").text();
                 var pagelevel = '2';
    			 html2+='<span class="audio_list" onclick="openWebPage(\''+weburl+'\' ,\''+pagelevel+'\')">'+returnImageHtml(image)+' <span>'+name+'</span></span>';

    		 }
    	 }
       
    	 appendHtml("<div class='page-text'>"+html2+"</div>",'',1);
        
    }
}