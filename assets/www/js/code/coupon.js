////////////////////////////////////////////Coupon//////////////////////////////
var pageTextColor;

var couponcode_coupon = 'Coupon Code';
var termcondition_coupon = 'Terms & Conditions ';

function getcoupon(index)
{
 if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
pageTextColor  = sessionStorage.getItem("pageTextColor");
	//var isiPad = 'Android';
    window.location ="removewebsite:";
    
        var couponHtml='';
        
        $data=$(masterData).find( "coupon[indexval="+index+"]");
        var funcName='';
        var counter=0;
        var uniquecodedataCode='';
        $data.find("couponRecord").each(function()
                                            {
                                            var couponHeading=$(this).find('couponHeading').text();
                                            var couponvalidtill=$(this).find('couponvalidtill').text();
                                            var couponValidTo=$(this).find('couponValidTo').text();
                                            var uniquecodedata=$(this).find('uniquecodedata').text();
                                            couponHtml+='<div class="coupon-category" onclick="coupon_view('+index+','+counter+');"><h4>'+couponHeading+'</h4><div class="coupon-cat-date">'+couponvalidtill+': <span>'+couponValidTo+'</span></div></div>';
                                            uniquecodedataCode='';
                                            counter=parseInt(counter)+1;
                                            });
        
        
        if(counter == 1)
        {
            sessionStorage.setItem('singlePage','true');
            coupon_view(index,0);
            
        }
        else
        {
             appendHtml(couponHtml,'',1);
        }
    
   
}



function coupon_view(index,dataIndex,pageLevel)
{
    pageTextColor  = sessionStorage.getItem("pageTextColor");
   $data=$(masterData).find( "coupon[indexval="+index+"]");
    $couponData=$data.find( "couponRecord[indexval="+dataIndex+"]");
   var couponHeading = ($couponData.find("couponHeading").text() );
   var couponBgImaged = ($couponData.find("couponBgImaged").text() );
   var couponValidFrom = ($couponData.find("couponValidFrom").text() );
   var couponValidTo = ($couponData.find("couponValidTo").text() );
   var couponCode = ($couponData.find("couponCode").text() );
   var couponImgName = ($couponData.find("couponImgName").text() );
   var couponbgColor = ($couponData.find("couponbgColor").text() );
   var couponDescription = ($couponData.find("couponDescription").text());
   sessionStorage.setItem("couponDescription",couponDescription);
    var couponissuedate = ($couponData.find("couponissuedate").text() );
   var couponvalidtill = ($couponData.find("couponvalidtill").text() );
    var color='';
    if(couponbgColor.indexOf('rgb') != -1)
    {
    color=couponbgColor.substring(couponbgColor.search('rgb'),couponbgColor.search(';'));
    }
    else
    {
   color=couponbgColor;
    }
                            
   document.getElementById('contentHolder8').style.background=color;

  // var couponHtml = '<div class="coupon-page"><style>.bg-color{'+couponbgColor+'}</style><h3 style="color:#000000">Coupon Code: '+couponCode+'</h3> <div class="coupon-img" style="background-image:url('+couponBgImaged+')"><h2>'+couponHeading+'</h2></div><div class="page-text"><div class="coupon_dates"><div class="issue_date">Date of Issue<span>'+couponValidFrom+'</span></div><div class="valid_date">Valid Till<span>'+couponValidTo+'</span></div></div><div class="coupon_barcode"><img src="'+couponImgName+'"></div><div class="coupon_details"><img onclick="coupon_info(\''+couponbgColor+'\',\''+couponDescription+'\');" src="images/coupon-deatils.png"></div></div></div>';
    var couponHtml = '<div class="coupon-page"><style>.bg-color{'+couponbgColor+'}</style><h3 style="color:'+pageTextColor+'">'+couponcode_coupon+': '+couponCode+'</h3> <div class="coupon-img" style="background-image:url('+couponBgImaged+')"><h2 style="color:'+pageTextColor+'">'+couponHeading+'</h2></div><div class="page-text"><div class="coupon_dates"><div class="issue_date" style="color:'+pageTextColor+'">'+couponissuedate+'<span style="color:'+pageTextColor+'">'+couponValidFrom+'</span></div><div class="valid_date" style="color:'+pageTextColor+'">'+couponvalidtill+'<span style="color:'+pageTextColor+'">'+couponValidTo+'</span></div></div><div class="coupon_barcode"><img src="'+couponImgName+'"></div><div class="coupon_details"><img onclick="coupon_info(\''+couponbgColor+'\',\''+couponDescription+'\');" src="images/coupon-deatils.png"></div></div></div>';
    if(sessionStorage.getItem('singlePage') == 'true' && !pageLevel)
    {
        appendHtml(couponHtml,8,1);
    }
    else if(pageLevel)
    {
        appendHtml(couponHtml,8,pageLevel);
    }
    else
    {
        appendHtml(couponHtml,8,2);
    }
	
	
	
}


function coupon_info(couponbgColor,couponDescription)
{
	pageTextColor  = sessionStorage.getItem("pageTextColor");
	var couponDesHtml = '<div class="coupon-page"><style>.bg-color{'+couponbgColor+'}</style> <div class="page-text" style="color:'+pageTextColor+'"><h2>'+termcondition_coupon+'</h2>'+couponDescription+'</div></div>';
    //var color=couponbgColor.substring(couponbgColor.search('rgb'),couponbgColor.search(';'));
    var color='';
    if(couponbgColor.indexOf('rgb') != -1)
    {
    color=couponbgColor.substring(couponbgColor.search('rgb'),couponbgColor.search(';'));
    }
    else
    {
   color=couponbgColor;
    }
	
	document.getElementById('contentHolder9').style.background=color;
    
    couponDesHtml="<div class='page-text'>"+couponDesHtml+"</div>";
    if(sessionStorage.getItem('singlePage') == 'true')
    {
        appendHtml(couponDesHtml,9,2);
    }
    else
    {
        appendHtml(couponDesHtml,9,3);
    }
}