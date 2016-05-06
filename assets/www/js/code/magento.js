
var appid=window.localStorage.getItem("applicationID");
function getMagentoView(index)
{
    resellerInitial='appypiemagento18.';
    reseller='onsisdev.info';
    var magentoUser='ravinesh';
    var magentoPass='1234567';
    
    var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#login";
	soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><login xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#login\"><username>'+magentoUser+'</username><apiKey>'+magentoPass+'</apiKey></login></soap:Body></soap:Envelope>';
    
	console.log(soapRequest);
    if(!sessionStorage.getItem("magentoSessionId"))
    {
        sessionStorage.removeItem("magentocategoryid");
 $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
            console.log(req.responseText);
           var strJSON = $(req.responseText).find("loginReturn").text();
           console.log("strJSON--->"+strJSON);
           sessionStorage.setItem("magentoSessionId",strJSON);
           getQuoteIdMagento();
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           }
           });
    }
    else
    {
      getQuoteIdMagento();
    }
    
}
function getQuoteIdMagento()
{
    // for cart creation
    
    console.log("Loginquote_id---->>>"+localStorage.getItem("Loginquote_id"));
    if(!sessionStorage.getItem("quote_id") && !localStorage.getItem("Loginquote_id"))
    {
        var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>mobile_cart.create</resourcePath><args>1</args></call></soap:Body></soap:Envelope>';
        console.log(" cart creation soap request" + soapRequest);
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               dataType: "text",
               data: soapRequest,
               success: function(data,status,req)
               {
      console.log(" cart creation response" + req.responseText);
      var strJSON = req.responseText;
         var n=strJSON.split("<callReturn xsi:type=");
         var strJSON1=n[1].split("</callReturn>");
         strJSON=strJSON1[0].split(">");
               
         sessionStorage.setItem('quote_id',strJSON[1]);
               console.log("quote_id---->>>"+sessionStorage.getItem('quote_id'))
               
         // $(".loading").hide();
               
               // for default customer address creation
               
               var customerAddress={"quoteId":sessionStorage.getItem('quote_id'),"0": {
               "mode": "shipping",
               "firstname": "NULL",
               "lastname": "NULL",
               "street": "NULL",
               "city": "NULL",
               "postcode": "NULL",
               "country_id": "NULL",
               "telephone": "NULL",
               "is_default_shipping": 0,
               "is_default_billing": 0
               },
               "1": {
               "mode": "billing",
               "firstname": "NULL",
               "lastname": "NULL",
               "street": "NULL",
               "city": "NULL",
               "postcode": "NULL",
               "country_id": "NULL",
               "telephone": "NULL",
               "is_default_shipping": 0,
               "is_default_billing": 0
               }
               };
         var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
               var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>mobile_cart_customer.addresses</resourcePath><args>'+JSON.stringify(customerAddress)+'</args></call></soap:Body></soap:Envelope>';
               
               $.ajax({
                      type: "POST",
                      url: wsUrl,
                      contentType: "text/xml",
                      dataType: "text",
                      data: soapRequest,
                      success: function(data,status,req)
                      {
                      ecomHomePageMagento();
                      },
                      error: function(data,status,req)
                      {
					  
                      }
                      });
               },
               error: function(data,status,req)
               {
               alert("error");
               
               }
               });
        
    }
    else if(localStorage.getItem("Loginquote_id"))
    {
          console.log("Loginquote_id else if ---->>>"+localStorage.getItem("Loginquote_id"));
        sessionStorage.setItem('quote_id',localStorage.getItem("Loginquote_id"));
         ecomHomePageMagento();
    }
    else
    {
        ecomHomePageMagento();
    }
    
}
function ecomHomePageMagento()
{
    if(!checkNetworkConnection())
    {
    }
    else
    {
       
	$("#logo").hide();
	$("#mainback").hide();
	$("#shopmenu").show();
	$("#mainbackfoodecom").hide();
	$("#reload").hide();
    console.log("termsEcom function");
    var navigationType = localStorage.getItem("navigation_Layout");
        console.log("reseller"+reseller);
    console.log("ecomHome function");
    var ecomHomeHtml='<section id="container">\
    <aside id="appypie-search-section">\
    <input type="text" id="txtSearch" placeholder="Search Product"/>\
    <input type="button" value="search" onclick="openSearchProduct()" />\
    </aside>\
    <div class="cart-MSG" id="cart-MSG1" style="display:none">\
    <span class="er_close">x</span>\
    <div class="cart_success"></div>\
    </div>\
    <aside id="categories-listing">\
    <h3 class="catName" id="catHeading">Categories</h3> <span id="show">\
    <a>+</a></span>\
    <span id="hide" style="display:none">\
    <a>-</a>\
    </span>\
    <div id="categoryList" style="display:none"></div>\
    \
    </aside>\
    <aside class="categories-listing" >\
    <h3 class="catName" id="magentoCatName">New Products</h3>\
    <ul class="food_pro_list">\
    </ul>\
    </aside>\
    </section></ul></aside></section><ul class="sub-menu" style="display:none;" >\
    <li><a onclick="HomeEcom()" class="home-nav">Home</a></li>\
    <li id="login"><a onclick="loginEcommerceMagento()" class="login-nav">Login / Registration</a></li>\
    <li id="signup"><a onclick="MyaccountMagento()" class="account-nav">My Account</a></li>\
    <li><a onclick="mainPageMagento()" class="account-nav">Main Menu</a></li>\
    <li id="cartView"><a onclick="cart_viewMagento()" class="cart-nav">Cart</a></li>\
    <li><a onclick="termsEcomMagento()" class="terms-nav">Terms & Conditions</a></li>\
    <li><a onclick="PolicyEcomMagento()" class="policy-nav">Privacy Policy</a></li>\
    <li id="logout"><a onclick="logoutMagento()" class="policy-nav">Log out</a></li>\
    </ul>';
    appendHtml(ecomHomeHtml,'',1);
        sessionStorage.removeItem("magentosearchFlag");
        if(sessionStorage.getItem("magentocategoryid"))
        {
            
            viewAllCategoriesMagento(sessionStorage.getItem("magentocategoryid"));
            listNewProductsMagento(sessionStorage.getItem("magentocategoryid"));
            $('#catHeading').empty();
            $('#catHeading').append(sessionStorage.getItem("magentoHeadcategoryName"));
        }
        else
        {
            
            viewAllCategoriesMagento('');
            viewNewproductsMagento();
            
            
        }
    
    $("#show").click(function(){
                     $("#categoryList").slideToggle();
                     //$("#hide").show();
                     $("#hide").css("display", "block");
                     });
    
    $("#hide").click(function(){
                     $("#categoryList").slideToggle();
                     //$("#hide").show();
                     $("#hide").css("display", "none");
                     });
    
    
    
    if(sessionStorage.getItem("Magentologincheck")=="1")
    {
        $("#cart-MSG1").slideToggle();
        $(".cart_success").append('Login Successful');
        setTimeout( function(){
                   $("#cart-MSG1").slideToggle();
                   }
                   , 3000 );
          sessionStorage.setItem("Magentologincheck","0");
    }
    
    if(sessionStorage.getItem("Magentologincheck")=="2")
    {
        $("#cart-MSG1").slideToggle();
        $(".cart_success").append('Logout Successful');
        setTimeout( function(){
                   $("#cart-MSG1").slideToggle();
                   }
                   , 3000 );
        sessionStorage.setItem("Magentologincheck","0");
    }
    if(localStorage.getItem("fooduserid"))
    {
        var login=document.getElementById("login").style.display="none";
    }
    else
    {
        var acc=document.getElementById("signup").style.display="none";
        document.getElementById("logout").style.display="none";
    }
        if(parseFloat(sessionStorage.getItem("magentocheckoutFlag")) > 0)
        {
            document.getElementById("cartView").style.display="block";
            console.log("magentocheckoutFlag Value--->"+sessionStorage.getItem("magentocheckoutFlag"));
        }
        else
        {
            document.getElementById("cartView").style.display="none";
            console.log("magentocheckoutFlag Value in else condition--->"+sessionStorage.getItem("magentocheckoutFlag"));
            
        }
        
}
}

function mainPageMagento()
{
    sessionStorage.removeItem("magentocategoryid");
    ecomHomePageMagento();
}

function viewAllCategoriesMagento(magentocategoryId)
{
    
    console.log("in viewAllCategories function");
	var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
	var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>mobile_category.categorylist</resourcePath><args>'+magentocategoryId+'</args></call></soap:Body></soap:Envelope>';
    
	console.log(soapRequest);
	//jQuery.support.cors = true;
	$.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           console.log(req.responseText);
           var strJSON = req.responseText;           
           var myObj = new Array();
           
           if($(strJSON).find("faultstring").length>0)
           {
           var message='Sorry ! '+$(strJSON).find("faultstring").text();
           alertMessage(""+message);
           if($(strJSON).find("faultstring").text().indexOf("Session expired. Try to relogin.")!=-1)
           {
		   
           }
           
           }
           else
           {
           if($(strJSON).find("item").find("item").length>0)
           {
           $(strJSON).find("item").find("item").find("value").each(function()
                                                                   {
                                                                   myObj.push($(this).text());
                                                                   });
           
           var count=0;
           html='<ul class="prolist">';
           for(var j = 0; j<myObj.length; j+=8)
           {
           console.log("myObj  --->>>>"+myObj[j+1]);
           var checkFlag=myObj[j+7];
           var categoryId= myObj[j];
           var categoryName= myObj[j+1];
           html+='<li><a onclick="openListPageMagento(\''+categoryId+'\',\''+categoryName+'\',\''+checkFlag+'\')">'+categoryName+'</a></li>';
           
           }
           console.log("html---->>"+html);
           html+='</ul>';
           $("#categoryList").append(html);
           
           console.log()
           
           if(magentocategoryId.length > 0)
           {
           $("#categoryList").slideToggle();
           $("#hide").css("display", "block");
           }
           }
           else
           {
           var message='Network connection problem!';
           alertMessage(""+message);
           }
           // $(".loading").hide();
           }

           
          
           console.log("viewAllCategoriesMagento  strJSON---->>> "+strJSON);
           },
           error: function(response, textStatus, errorThrown)
           {
           }
           });
    
}

function openListPageMagento(id,categoryName,checkFlag)
{
    if(sessionStorage.getItem("magentocategoryid"))
    {
    sessionStorage.setItem("magentoPrevcategoryid",sessionStorage.getItem("magentocategoryid"));
         sessionStorage.setItem("magentoPrevcategoryName",sessionStorage.getItem("magentoHeadcategoryName"));
    }
    console.log("openListPageMagento"+id+categoryName+checkFlag);
	sessionStorage.setItem("magentocategoryid",id);
	sessionStorage.setItem("magentocategoryName",categoryName);
    
    if(checkFlag == 0)
    {
        product_PageMagento();
    }
    else
    {
        sessionStorage.setItem("magentoHeadcategoryName",categoryName);
        ecomHomePageMagento();
    }
   
	
	
}

function alertMessage(message)
{
	alertPopUp('Alert !',message);
    
}
function viewNewproductsMagento()
{
    
    var jsonArgs='{"categoryId":"","sort":"name","sorttype":"desc","page":"1"}';
    
	wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
	soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>mobile_product.newproductlist</resourcePath><args>'+jsonArgs+'</args></call></soap:Body></soap:Envelope>';
    
	console.log(soapRequest);
	
	$.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req) 
           {
           console.log("viewNewproductsMagento response---->>> "+req.responseText);
           if($(req.responseText).find("faultstring").length>0)
           {
           var message='Sorry ! '+$(req.responseText).find("faultstring").text();
           alertMessage(""+message);
           if($(req.responseText).find("faultstring").text().indexOf("Session expired. Try to relogin.")!=-1)
           {
           magento();
           }
           
           }
           else
           {
           
           if($(req.responseText).find("item").find("item").length>0)
           {
           
            html='';
           var productIdArray=new Array();
           var productnameArray=new Array();
           var productimageArray=new Array();
           var productpriceArray=new Array();
           var productspecialpriceArray=new Array();
           var productsdecsArray=new Array();
           $(req.responseText).find("item").find("item").each(function()
                                                              {
                                                              if($(this).find("key").text()=="product_id")
                                                              {
                                                              productIdArray.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="thumbnail")
                                                              {
                                                              productimageArray.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="name")
                                                              {
                                                              productnameArray.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="price")
                                                              {
                                                              productpriceArray.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="special_price")
                                                              {
                                                              productspecialpriceArray.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="description")
                                                              {
                                                               productsdecsArray.push($(this).find("value").text());
                                                              }
                                                              });
     
           console.log("productIdArray.length--->>>"+productIdArray.length);
          
           for(var i=0;i<productIdArray.length;i++)
           {
           
           
           var productId= productIdArray[i];
            console.log("productIdArray.length 1--->>>"+productIdArray.length);
           var productName= productnameArray[i];
            console.log("productIdArray.length 2--->>>"+productIdArray.length);
           var Productprice= parseFloat(productpriceArray[i]).toFixed(2);
            console.log("productIdArray.length 3--->>>"+productIdArray.length);
           var splPrice= parseFloat(productspecialpriceArray[i]).toFixed(2);
            console.log("productIdArray.length 4--->>>"+productIdArray.length);
           var productImage=productimageArray[i];
            console.log("productIdArray.length 5--->>>"+productIdArray.length);
           var descriptionList=productsdecsArray[i];
           var description=$(descriptionList).text();
            console.log("productIdArray.length 6--->>>"+productIdArray.length);
           
           if(parseInt(Productprice) > parseInt(splPrice))
           {
           var price=splPrice;
           }
           else
           {
           var price=Productprice;
           }
           
           productName=productName.substring(0,20);
           description=description.substring(0,20);
           
             html+='<li onclick="openDetailPageMagento('+productId+')"><img class="productImageClass" src="'+productImage+'"  border="0" /><h4>'+productName+'</h4><h5>'+description+'</h5><div class="food-mobileProPrice">'+price+'<span class="food-addtocart">add</span></div></li>';
           }
           $(".food_pro_list").empty();
           console.log("html=----->>"+html);
           $(".food_pro_list").append(html);
           $("#magentoCatName").empty();
            $("#magentoCatName").append("New Products");
           }
           else
           {
           html="<label>There is no new product found.</label>";
           $(".food_pro_list").append(html);
           
          
           }
          
           }
           
           },
           error: function(response, textStatus, errorThrown)
           {
           //console.log(response);
           }
           });
    
}


function listNewProductsMagento(categoryId)
{
    
    var jsonArgs='{"categoryId":"'+categoryId+'","sort":"name","sorttype":"desc","page":"1"}';
    
	wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
	soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>mobile_category.productlist</resourcePath><args>'+jsonArgs+'</args></call></soap:Body></soap:Envelope>';
    
	console.log(soapRequest);
	
	$.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           console.log("viewNewproductsMagento response---->>> "+req.responseText);
           if($(req.responseText).find("faultstring").length>0)
           {
           var message='Sorry ! '+$(req.responseText).find("faultstring").text();
           alertMessage(""+message);
           if($(req.responseText).find("faultstring").text().indexOf("Session expired. Try to relogin.")!=-1)
           {
           magento();
           }
           
           }
           else
           {
           
           if($(req.responseText).find("item").find("item").length>0)
           {
           
           html='';
           var productIdArray=new Array();
           var productnameArray=new Array();
           var productimageArray=new Array();
           var productpriceArray=new Array();
           var productspecialpriceArray=new Array();
           var productsdecsArray=new Array();
           $(req.responseText).find("item").find("item").each(function()
                                                              {
                                                              if($(this).find("key").text()=="product_id")
                                                              {
                                                              productIdArray.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="thumbnail")
                                                              {
                                                              productimageArray.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="name")
                                                              {
                                                              productnameArray.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="price")
                                                              {
                                                              productpriceArray.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="special_price")
                                                              {
                                                              productspecialpriceArray.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="description")
                                                              {
                                                              productsdecsArray.push($(this).find("value").text());
                                                              }
                                                              });
           
           console.log("productIdArray.length--->>>"+productIdArray.length);
           
           for(var i=0;i<productIdArray.length;i++)
           {
           
           
           var productId= productIdArray[i];
           console.log("productIdArray.length 1--->>>"+productIdArray.length);
           var productName= productnameArray[i];
           console.log("productIdArray.length 2--->>>"+productIdArray.length);
           //var quantity= $(this).find("quantity").text();
           var Productprice= parseFloat(productpriceArray[i]).toFixed(2);
           console.log("productIdArray.length 3--->>>"+productIdArray.length);
           var splPrice= parseFloat(productspecialpriceArray[i]).toFixed(2);
           console.log("productIdArray.length 4--->>>"+productIdArray.length);
           var productImage=productimageArray[i];
           console.log("productIdArray.length 5--->>>"+productIdArray.length);
           //var descriptionList=productsdecsArray[i];
           var description='';//$(descriptionList).text();
           console.log("productIdArray.length 6--->>>"+productIdArray.length);
           
           console.log(productsdecsArray[i]);
           
           if(parseInt(Productprice) > parseInt(splPrice))
           {
           var price=splPrice;
           }
           else
           {
           var price=Productprice;
           }
           
           productName=productName.substring(0,20);
          // description=description.substring(0,20);
           
           html+='<li onclick="openDetailPageMagento('+productId+')"><img class="productImageClass" src="'+productImage+'"  border="0" /><h4>'+productName+'</h4><h5>'+description+'</h5><div class="food-mobileProPrice">'+price+'<span class="food-addtocart">add</span></div></li>';
           }
           $(".food_pro_list").empty();
           console.log("html=----->>"+html);
           $(".food_pro_list").append(html);
           $("#magentoCatName").empty();
           $("#magentoCatName").append("Products");
           }
           else
           {
           
           $(".food_pro_list").hide();
           
           $("#magentoCatName").hide();
           
           
           }
           
           }
           
           },
           error: function(response, textStatus, errorThrown)
           {
           //console.log(response);
           }
           });
    
}


//      search functionality


function openSearchProduct()
{
    
    
    var txtSearch=document.getElementById("txtSearch").value;
    if(txtSearch == '' || txtSearch == null)
    {
        return false;
    }
    else
    {
        
        var jsonFilterData='{"name":"'+txtSearch+'","sort":"","sort_type":"","page" :"1"}';
        sessionStorage.setItem("magentosearchText",jsonFilterData);
        product_PageMagento();
    }
}
function product_PageMagento()
{
    if(!checkNetworkConnection())
    {
    } 
    else
    {
       
    console.log("termsEcom function");
    var navigationType = localStorage.getItem("navigation_Layout");
    
    
    clsvalue = 'backToDashbord';
    
    console.log("terms ecom function");
        
        console.log("magentosearchText----->>"+sessionStorage.getItem("magentosearchText"));
        if(sessionStorage.getItem("magentosearchText"))
        {
            

            var searchHtml='<aside id="appypie-search-section">\
            <input type="text" id="txtSearch" placeholder="Search Product"/>\
            <input type="button" value="search" onclick="openSearchProduct()" />\
            </aside>';
            var NonSearchHtml='';
            
        }
        else
        {
            var NonSearchHtml='<div class="product-top cart-header">\
            <h3 style="color:#000" id="product_pageHeader">Product Details</h3>\
            <div class="checkOut-Btn" id="checkOutBtn" style="display:none;" onclick="cart_viewMagento()">CheckOut</div></div>';

            
            var searchHtml='';
        }
       
        
       
       

    var productPageHtml= NonSearchHtml+'<section id="container" class="cart-container">'+searchHtml+'<div class="food-ordering-product">\
    <div class="cat-option" id="cat-option">\
    \
    </div>\
    <ul class="food_pro_list">\
    \
    </ul>\
    \
    </div>\
    </section><ul class="sub-menu" style="display:none;" >\
    <li><a onclick="HomeEcom()" class="home-nav">Home</a></li>\
    <li id="login"><a onclick="loginEcommerceMagento()" class="login-nav">Login / Registration</a></li>\
    <li id="signup"><a onclick="MyaccountMagento()" class="account-nav">My Account</a></li>\
    <li id="cartView"><a onclick="cart_viewMagento()" class="cart-nav">Cart</a></li>\
    <li><a onclick="mainPageMagento()" class="account-nav">Main Menu</a></li>\
    <li><a onclick="termsEcomMagento()" class="terms-nav">Terms & Conditions</a></li>\
    <li><a onclick="PolicyEcomMagento()" class="policy-nav">Privacy Policy</a></li>\
        <li id="logout"><a onclick="logoutMagento()" class="policy-nav">Log out</a></li>\
    </ul>';
    console.log("1" + productPageHtml);
    appendHtml(productPageHtml,2,2);
   
    //console.log("categoryid Product page--->>>"+sessionStorage.getItem("magentocategoryid"));
    console.log("in else condition---> calling showProducts function magentocategoryid---->>>"+sessionStorage.getItem("magentocategoryid"));
    showProducts(sessionStorage.getItem("magentocategoryid"));
    if(localStorage.getItem("fooduserid"))
    {
        var login=document.getElementById("login").style.display="none";
    }
    else
    {
        var acc=document.getElementById("signup").style.display="none";
        document.getElementById("logout").style.display="none";
    }
        if(parseFloat(sessionStorage.getItem("magentocheckoutFlag")) > 0)
        {
            document.getElementById("cartView").style.display="block";
             document.getElementById("checkOutBtn").style.display="block";
            
            console.log("magentocheckoutFlag Value--->"+sessionStorage.getItem("magentocheckoutFlag"));
        }
        else
        {
            
            document.getElementById("cartView").style.display="none";
            document.getElementById("checkOutBtn").style.display="none";
            console.log("magentocheckoutFlag Value in else condition--->"+sessionStorage.getItem("magentocheckoutFlag"));
            
        }
    
    if(sessionStorage.getItem("magentocategoryName") != null || sessionStorage.getItem("magentocategoryName") != '')
    {
    document.getElementById("product_pageHeader").innerHTML=sessionStorage.getItem("magentocategoryName");
    }
    
    }
    
}

function showProducts(categoryId)
{
    var jsonArgs='';
    if(sessionStorage.getItem("magentosearchText"))
    {
        jsonArgs=sessionStorage.getItem("magentosearchText");
        sessionStorage.setItem("magentosearchFlag",sessionStorage.getItem("magentosearchText"));
         sessionStorage.removeItem("magentosearchText");
        var requestType='mobile_product.productlist';
        
    }
    else
    {
        jsonArgs='{"categoryId":"'+categoryId+'","sort":"name","sorttype":"desc","page":"1"}';
        var requestType='mobile_category.productlist';
    }
	wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
	soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>'+requestType+'</resourcePath><args>'+jsonArgs+'</args></call></soap:Body></soap:Envelope>';
    
	console.log(soapRequest);
	
	$.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           console.log("viewNewproductsMagento response---->>> "+req.responseText);
           if($(req.responseText).find("faultstring").length>0)
           {
           var message='Sorry ! '+$(req.responseText).find("faultstring").text();
           alertMessage(""+message);
           if($(req.responseText).find("faultstring").text().indexOf("Session expired. Try to relogin.")!=-1)
           {
           magento();
           }
           
           }
           else
           {
           
           if($(req.responseText).find("item").find("item").length>0)
           {
           
           html='';
           var productIdArray=new Array();
           var productnameArray=new Array();
           var productimageArray=new Array();
           var productpriceArray=new Array();
           var productspecialpriceArray=new Array();
           var productsdecsArray=new Array();
           var productsInStockArray=new Array();
           $(req.responseText).find("item").find("item").each(function()
                                                              {
                                                              if($(this).find("key").text()=="product_id")
                                                              {
                                                              productIdArray.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="thumbnail")
                                                              {
                                                              productimageArray.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="name")
                                                              {
                                                              productnameArray.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="price")
                                                              {
                                                              productpriceArray.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="special_price")
                                                              {
                                                              productspecialpriceArray.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="description")
                                                              {
                                                              productsdecsArray.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="is_in_stock")
                                                              {
                                                              if($(this).find("value").text())
                                                              {
                                                              productsInStockArray.push($(this).find("value").text());
                                                              }
                                                              }
                                                              });
           
           console.log("productIdArray.length--->>>"+productIdArray.length);
         // var catNameHtml='<div class="categoriesNameList">'+sessionStorage.getItem("magentocategoryName")+'</div>';
           html='';
           for(var i=0;i<productIdArray.length;i++)
           {
           
           var productId= productIdArray[i];
           console.log("productIdArray.length 1--->>>"+productIdArray.length);
           var productName= productnameArray[i];
           console.log("productIdArray.length 2--->>>"+productIdArray.length);
           //var quantity= $(this).find("quantity").text();
           var Productprice= parseFloat(productpriceArray[i]).toFixed(2);
           console.log("productIdArray.length 3--->>>"+productIdArray.length);
           var splPrice= parseFloat(productspecialpriceArray[i]).toFixed(2);
           console.log("productIdArray.length 4--->>>"+productIdArray.length);
           var productImage=productimageArray[i];
           if(productsdecsArray.length > 0 && (productsdecsArray[i] != null || productsdecsArray[i] != ''))
           {
           var descriptionList=productsdecsArray[i];
           var description=$(descriptionList).text();
           description=description.substring(0,20);
           }
           else
           {
           var description='';
           }
           console.log("productIdArray.length 6--->>>"+productIdArray.length);
           
          // console.log(productsdecsArray[i]);
           
           if(parseInt(Productprice) > parseInt(splPrice))
           {
           var price=splPrice;
           }
           else
           {
           var price=Productprice;
           }
           
           productName=productName.substring(0,20);
           if(productsInStockArray.length > 0)
           {
           if(productsInStockArray[i] >= 1)
           {
            html+='<li onclick="openDetailPageMagento('+productId+')"><img class="productImageClass" src="'+productImage+'"  border="0" /><h4>'+productName+'</h4><h5>'+description+'</h5><div class="food-mobileProPrice">'+price+'<span class="food-addtocart">add</span></div></li>';
           }
           }
           else
           {
            html+='<li onclick="openDetailPageMagento('+productId+')"><img class="productImageClass" src="'+productImage+'"  border="0" /><h4>'+productName+'</h4><h5>'+description+'</h5><div class="food-mobileProPrice">'+price+'<span class="food-addtocart">add</span></div></li>';
           }
           
          
           
           }
           $(".food_pro_list").empty();
           console.log("html=----->>"+html);
           $(".food_pro_list").append(html);
           $("#magentoCatName").empty();
           $("#magentoCatName").append("New Products");
          
           }
           else
           {
           html="<label>There is no new product found.</label>";
           $(".food_pro_list").append(html);
           
           
           }
           
           }
           
           },
           error: function(response, textStatus, errorThrown)
           {
           //console.log(response);
           }
           });

}
function openDetailPageMagento(id)
{
    console.log("magentoproductid---->>"+id);
	sessionStorage.setItem("magentoproductid",id);
	product_detailsMagento();
    
}

// search functionality


function searchProducts()
{
	var txtSearch=sessionStorage.getItem("ecomsearchText");
	
	wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#ecommSearchXml";
	soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommSearchXml xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#ecommSearchXml\"><appId>'+appId+'</appId><search>'+txtSearch+'</search></ecommSearchXml></soap:Body></soap:Envelope>';
    
	console.log('searchProducts---------->>>>'+soapRequest);
	//jQuery.support.cors = true;
	$.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           //console.log(req.responseText);
           
           //var xmlret=StringToXML(req.responseText);
           var strJSON = $(req.responseText).find("return").text();
           
           console.log('searchProducts--->strJSON---------->>>>'+strJSON);
           listSearchResult(strJSON);
           },
           error: function(response, textStatus, errorThrown)
           {
           //console.log(response);
           }
           });
}
function listSearchResult(strJSON)
{
	console.log('listSearchResult---->>>'+strJSON);
	$.ajax({
           url: strJSON,
           dataType: "text",
           cache: false,
           crossDomain: true,
           success: function(xmll) {
           html='';
           var count=0;
           $(xmll).find("product").each(function () {
                                        var productName= $(this).find("productName").text();
                                        var price= $(this).find("price").text();
                                        var productImage= $(this).find("productImage").text();
                                        var productId= $(this).find("productId").text();
                                        var currency= $(this).find("currency").text();
                                        var description=$(this).find("description").text()
                                        count=count+1;
                                        
                                        productName=productName.substring(0,20);
                                        description=description.substring(0,20);
                                        
                                        html+='<li onclick="openDetailPageMagento('+productId+')"><img class="productImageClass" src="'+productImage+'"  border="0" /><h4>'+productName+'</h4><h5>'+description+'</h5><div class="food-mobileProPrice">'+currency+price+'<span class="food-addtocart">add</span></div></li>';
                                        
                                        });
           console.log(html);
           if(count>0)
           {
           
           $(".food_pro_list").append(html);
           
        //   $(".e-mobile-product").append(html);
           }
           else
           {
           $(".food_pro_list").append('<center><b><h2>No Product Found</h2></b><br/></center>');
           
         //  $(".food_pro_list").append(html);
           //$(".e-mobile-product").append(html);
           }
           if(window.sessionStorage.getItem("ecomsearchText"))
           {
           $("#product_pageHeader").empty();
           $("#product_pageHeader").append("Search Result for ("+window.sessionStorage.getItem("ecomsearchText")+")");
            window.sessionStorage.removeItem("ecomsearchText")
           }
           else
           {
           $("#product_pageHeader").append(window.sessionStorage.getItem("ecomcategoryName"));
           }
           xmll={};
            
           },
           error: function(XMLHttpRequest, textStatus, errorThrown) {
           console.log('fail');
           console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
           }
           });
}


function product_detailsMagento()
{
    if(!checkNetworkConnection())
    {
    }
    else
    {
     
    console.log("termsEcom function");
    var navigationType = localStorage.getItem("navigation_Layout");
    
    
    clsvalue = 'backToDashbord';
    
    console.log("terms ecom function");
    
     var productdetailsHtml='<div class="product-top cart-header">\
     <h3 style="color:#000" id="product_pageHeader"></h3>\
     <div class="checkOut-Btn" id="checkOutBtn" onclick="cart_viewMagento()" style="display:none">Checkout</div>\
     </div><section id="container" class="cart-container">\
     <div class="food-ordering-product">\
     <div class="cart-MSG" style="display:none">\
     <span class="er_close">x</span>\
     <div class="cart_success">Product successfully Added into your Cart.</div>\
     </div>\
     <div class="cart-MSG" style="display:none">\
     <span class="er_close">x</span>\
     <div class="cart_error">Please specify the product\'s required option(s).</div>\
     </div>\
    \
     <div class="food-pro_details" id="pro_details">\
     \
     </div>\
     \
        <div class="food-pro_details" id="food_quantity">\
        <ul id="config_selection"></ul>\
        </div>\
     <div class="food-pro_details" id="food_quantity">\
     <div class="food-quantity">\
     Quantity: <input type="number" id="quantity" value="1">\
     </div>\
     <span class="minus_img"><img width="35px" onclick="minusButton()" src="resources/e-com/sliderminus.png"/></span><div id="slider"></div><span class="plus_img"><img width="35px" onclick="plusButtonMagento()" src="resources/e-com/sliderplus.png"/></span>\
     </div>\
     <a onclick="cart_addMagento()" class="addtocart-btn" id="addtocart">Add To Cart</a>\
     \
     </div>\
    </section><ul class="sub-menu" style="display:none;" >\
    <li><a onclick="HomeEcom()" class="home-nav">Home</a></li>\
    <li id="login"><a onclick="loginEcommerceMagento()" class="login-nav">Login / Registration</a></li>\
    <li id="signup"><a onclick="MyaccountMagento()" class="account-nav">My Account</a></li>\
    <li id="cartView"><a onclick="cart_viewMagento()" class="cart-nav">Cart</a></li>\
    <li><a onclick="mainPageMagento()" class="account-nav">Main Menu</a></li>\
    <li><a onclick="termsEcomMagento()" class="terms-nav">Terms & Conditions</a></li>\
    <li><a onclick="PolicyEcomMagento()" class="policy-nav">Privacy Policy</a></li>\
    <li id="logout"><a onclick="logoutMagento()" class="policy-nav">Log out</a></li>\
    </ul>';
    
    console.log("1" + productdetailsHtml);
    $("#contentHolder3").empty();
	$("#contentHolder3").append(productdetailsHtml);
	$.mobile.changePage('#page4', { transition: 'slidefade',allowSamePageTransition: true});

    var productid=sessionStorage.getItem("magentoproductid");
        console.log("productid"+productid);
        if(window.localStorage.getItem("fooduserid"))
        {
            var login=document.getElementById("login").style.display="none";
        }
        else
        {
            var acc=document.getElementById("signup").style.display="none";
            document.getElementById("logout").style.display="none";
        }
    //window.sessionStorage.removeItem("productid");
    console.log('window.sessionStorage.getItem(magentoproductid)=>'+sessionStorage.getItem("magentoproductid"));
    //console.log('productid=>'+productid);
       // sessionStorage.setItem("magentoproductid",productid);
        
        showProductdetailsmagento(productid);
        console.log("After function ");
    
     var Quantity = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50','51','52','53','54','55','56','57','58','59','60','61','62','63','64','65','66','67','68','69','70','71','72','73','74','75','76','77','78','79','80','81','82','83','84','85','86','87','88','89','90','91','92','93','94','95','96','97','98','99','100'];
        console.log('1--->');
        $('#slider').slider({
                            min: 0,
                            max: 100,
                            step: 1,
                            create: function (event, ui) {
                            document.getElementById("quantity").value= Quantity[0];
                            },
                            slide: function (event, ui) {
                            
                           // console.log("ecomquantity---->>>"+sessionStorage.getItem("ecomquantity")+"Quantity--->>"+Quantity[ui.value]);
                            if(parseFloat(sessionStorage.getItem("magentoquantity")) > parseFloat(Quantity[ui.value])  )
                            {
                            document.getElementById("quantity").value=Quantity[ui.value];
                            }
                            else
                            {
                            document.getElementById("quantity").value=sessionStorage.getItem("magentoquantity");
                            }
                            }
                            });
        $("#quantity").change(function() {
                              var inputValue=document.getElementById("quantity").value;
                              //console.log("ecomquantity---->>>"+sessionStorage.getItem("ecomquantity")+"inputValue--->>"+inputValue);
                              if(inputValue < 1)
                              {
                              document.getElementById("quantity").value=1;
                              inputValue=1;
                              }
                              if(sessionStorage.getItem("magentoquantity") > inputValue)
                              {
                              document.getElementById("quantity").value=sessionStorage.getItem("magentoquantity");
                              inputValue=sessionStorage.getItem("magentoquantity");
                              }
                              $("#slider").slider("value",parseInt(inputValue));
                              });
        
    console.log('3--->');
        if(parseFloat(sessionStorage.getItem("magentocheckoutFlag")) > 0)
        {
            document.getElementById("checkOutBtn").style.display="block";
            document.getElementById("cartView").style.display="block";
            console.log("magentocheckoutFlag Value--->"+sessionStorage.getItem("magentocheckoutFlag"));
        }
        else
        {
            document.getElementById("checkOutBtn").style.display="none";
            document.getElementById("cartView").style.display="none";
            console.log("magentocheckoutFlag Value in else condition--->"+sessionStorage.getItem("magentocheckoutFlag"));
            
        }
   
    
 //   document.getElementById("product_pageHeader").innerHTML=ssessionStorage.setItem("ecomproductName");
    console.log("1--->>>");
    }
  
}
var dropdown_label_array=new Array();
var dropdown_index_array=new Array();
var dropdown_value_array=new Array();
var checkDetails=new Array();
function showProductdetailsmagento(productId)
{
    
    console.log("showProductdetailsmagento--->>>"+productId);
   var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
	var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>mobile_product.productdetail</resourcePath><args>'+productId+'</args></call></soap:Body></soap:Envelope>';
    
	console.log(soapRequest);
	//jQuery.support.cors = true;
	$.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           console.log("showProductdetails -----"+req.responseText);
           //start
           if($(req.responseText).find("faultstring").length>0)
           {
           var message='Sorry ! '+$(req.responseText).find("faultstring").text();
           alertMessage(""+message);
           if($(req.responseText).find("faultstring").text().indexOf("Session expired. Try to relogin.")!=-1)
           {
           magento();
           
           }
         
           }
           else
           {
           var priceValue="";
           var descHtml='';
           var newPriceHtml='';
           var oldPriceHtml='';
           var imageHtml='';
           var product_head='';
           $(req.responseText).find("item").each(function()
                                                                                   {
                                                                                   if($(this).find("key").text()=="product_id")
                                                                                   {
                                                                                   sessionStorage.setItem('selected_'+$(this).find("key").text(),$(this).find("value").text());
                                                                                   }
                                                                                   else if($(this).find("key").text()=="name")
                                                                                   {
                                                                                    console.log("name :--->"+$(this).find("value").text());
                                                 product_head=$(this).find("value").text();
                                                 
                                                                                   $("#product_pageHeader").append(product_head.substring(0,20));
                                                                                   }
                                                                                   else if($(this).find("key").text()=="price")
                                                                                   {
                                                                                   //console.log($(this).find("key").text()+"="+$(this).find("value").text());
                                                                                   sessionStorage.setItem("details_orginal_price",$(this).find("value").text());
                                                                                   sessionStorage.setItem("details_old_price",$(this).find("value").text());
                                                                                   //document.getElementById('product_new_price').innerHTML="$"+parseFloat($(this).find("value").text()).toFixed(2);
                                                  newPriceHtml='<h3 style="text-align:left;float:left;margin:0;padding:0;" id="product_new_price">$'+parseInt($(this).find("value").text()).toFixed(2)+'</h3>';
                                                                                   priceValue="$"+parseFloat($(this).find("value").text()).toFixed(2);
                                                 
                                                 console.log("newPriceHtml :--->"+newPriceHtml);
                                                                                   }
                                                                                   else if($(this).find("key").text()=="special_price")
                                                                                   {
                                                                                   //console.log($(this).find("key").text()+"="+$(this).find("value").text());
                                                                                   if($(this).find("value").text().length>0)
                                                                                   {
                                                                                   sessionStorage.setItem("details_orginal_price",$(this).find("value").text());
                                                                                   //document.getElementById('product_old_price').innerHTML="$"+parseFloat($(this).find("value").text()).toFixed(2);
                                                                                 oldPriceHtml='<h3 style="text-align:left;float:right;margin:0;padding:0;">$ '+parseFloat($(this).find("value").text()).toFixed(2)+'</h3>';
                                                 console.log("oldPriceHtml :--->"+oldPriceHtml);
                                                 
                                                                                   }
                                                                                   else
                                                                                   {
                                                                                     oldPriceHtml='<h3 style="text-align:left;float:right;margin:0;padding:0;" id="product_new_price">'+priceValue+'</h3>';
                                                                                     newPriceHtml='';
//                                                                                   document.getElementById('product_new_price').innerHTML="";
//                                                                                   document.getElementById('product_old_price').innerHTML=priceValue;
                                                                                    console.log("oldPriceHtml :--->"+oldPriceHtml);
                                                                                   }
                                                                                   }
                                                                                   else if($(this).find("key").text()=="thumbnail")
                                                                                   {
                                                                var thumbnailImage=$(this).find("value").text();
                                                 var splitThumb=thumbnailImage.split(',');
                                                 
                                                 
                                                                     imageHtml='<img src="'+splitThumb[0]+'" class="food-pro_details-img" />';
                                                                console.log("imageHtml :--->"+imageHtml);
                                                                                   }
                                                                                   else if($(this).find("key").text()=="image")
                                                                                   {
//                                                                                   document.getElementById('imageValue').id =""+$(this).find("value").text();
//                                                                                   document.getElementById('imageValue1').id =""+$(this).find("value").text();
                                                 
                                                 
                                                 
                                                 
                                                 }else if($(this).find("key").text()=="qty")
                                                 {
                                                 sessionStorage.setItem("magentoquantity",parseInt($(this).find("value").text()));
                                                 
                                                 }
                                                                                    else if($(this).find("key").text()=="is_in_stock")
                                                                                    {
                                                                               // sessionStorage.setItem("magentoquantity",$(this).find("value").text());
                                                 
                                                                                    }
                                                                                   else if($(this).find("key").text()=="description")
                                                                                   {
                                                                                  // document.getElementById('description').innerHTML=""+$(this).find("value").text();
                                                                                    var descriptionHtml=$(this).find("value").text();
                                                                                    var rex = /(<([^>]+)>)/ig;
                                                                                     descHtml='<p>'+descriptionHtml.replace(rex , "")+'</p>';
                                                 
                                                 
                                                                                        console.log("descHtml :--->"+descHtml);
                                                                                   }
//                                                                                   else if($(this).find("key").text()=="manufacturerpartnumber")
//                                                                                   {
//                                                                                   // console.log("manufacturerpartnumber--->>"+$(this).find("key").text());
//                                                                                   //document.getElementById('product_model_number').innerHTML="Model Number :"+$(this).find("value").text();
//                                                                                   }
                                                                                   else if($(this).find("key").text()=="type_id")
                                                                                   {
                                                                                   sessionStorage.setItem('selected_'+$(this).find("key").text(),$(this).find("value").text());
                                                                                   }
                                                                                   });
           
        
           
           var add_info_label_array=new Array();
           var add_info_value_array=new Array();
          
           var add_info_complete=false;
           dropdown_label_array.length=0;
           dropdown_value_array.length=0;
           dropdown_index_array.length=0;
           checkDetails.length=0;
           var v=0;
           var y=0;
           var xyz=0;
           var output="",output1="",configHtml="";
           var xbjj=0;
           for(var ie=0;ie<3;ie++)
           {
           checkDetails[ie]=new Array(3);
           for(var op=0;op<3;op++)
           {
           checkDetails[ie,op]=xbjj;
           xbjj++;
          // console.log("in loop checkDetails["+ie+","+op+"]------>>>"+checkDetails[ie,op]);
           }
           }

           for(var ie=0;ie<3;ie++)
           {
           for(var op=0;op<3;op++)
           {
           console.log("in loop checkDetails["+ie+","+op+"]------>>>"+checkDetails[ie,op]);
           }
           }
           

                                $(req.responseText).find("item").find("item").each(function()
                                                                                                {
                                                                                                ////console.log($(this).find("key").text()+"="+$(this).find("value").text());
                                                                                                if($(this).find("key").text().indexOf("label")!=-1)
                                                                                                {
                                                                                                add_info_complete=true;
                                                                                                if($(this).find("key").text()=="label")
                                                                                                {
                                                                                   
                                                                                                dropdown_index_array.push(""+$(this).find("value").text());
                                                                                                v++;
                                                                                                 output1="<option value='select'>"+"Select"+"</option>";
                                                                                                }
                                                                                                else
                                                                                                {
                                                                                                var dropdown_key=new Array();
                                                                                                dropdown_key=$(this).find("key").text().split("label");
                                                                                   console.log("dropdown_index_array---->>in if else condition"+dropdown_key[0]);
                                                                                                dropdown_label_array.push(dropdown_key[0]);
                                                                                              //   dropdown_label_array.push(""+$(this).find("key").text());
                                                                                                y++;
                                                                                                xyz=0;
                                                                                   
                                                                                                }
                                                                                                }
                                                                                                else
                                                                                                {
                                                                                                if(add_info_complete==false)
                                                                                                {
                                                                                                add_info_label_array.push(""+$(this).find("key").text());
                                                                                                add_info_value_array.push(""+$(this).find("value").text());
                                                                                                }
                                                                                                else
                                                                                                {
                                                                                   
                                                                                   console.log("dropdown_label_array---->>in else else condition"+$(this).find("key").text());
                                                                                   
                                                                                       dropdown_value_array.push(""+$(this).find("key").text());
                                                                                   console.log("y--->"+y+"<---xyz---->"+xyz);
                                                                                   
                                                                                   console.log("y--->"+y+"<---xyz---->"+xyz);
                                                                                   xyz++;
                                                                                   checkDetails[y,xyz]=parseInt($(this).find("key").text());
                                                                                  console.log("checkDetails["+y+","+xyz+"]------>>>"+checkDetails[y,xyz]+"<---value---->"+$(this).find("key").text());
                                                                                   
                                                                                   var dropdown_label_array_var=$(this).find("key").text();
                                                                                   var dropdown_value_array_var=$(this).find("value").text();
                                                                                   var dropdownLabelData=new Array();
                                                                                   dropdownLabelData=dropdown_value_array_var.split("###");
                                                                                   
                                                                                   if(dropdownLabelData.length!=0)
                                                                                   {
                                                                                   var isPercent="";
                                                                                   var addedPrice=0;
                                                                                   isPercent=dropdownLabelData[1];
                                                                                   if(dropdownLabelData[2].length>0)
                                                                                   {
                                                                                   addedPrice=parseFloat(dropdownLabelData[2]);
                                                                                   }
                                                                                   if(isPercent=="0")
                                                                                   {
                                                                                   addedPrice=(addedPrice).toFixed(2);
                                                                                   }
                                                                                   else
                                                                                   {
                                                                                   addedPrice=isPercent+'%';
                                                                                   }
                                                                                   }  
                                                                                   output1+="<option value="+dropdown_value_array_var+">"+dropdownLabelData[0]+","+"$"+addedPrice+"</option>";
                                                                                   sessionStorage.setItem('labelHtml'+v,output1);
                                                                                                }
                                                                                                }
                                                                                   
                                                                                                });
           
           for(var ie=1;ie<3;ie++)
           {
           for(var op=0;op<3;op++)
           {
           console.log("in loop checkDetails["+ie+","+op+"]------>>>"+checkDetails[ie,op]);
           }
           }
           
           
           
         
      
           console.log("output--->>"+output);
          // document.getElementById('addInfo').innerHTML=""+output;
           
          
           if(sessionStorage.getItem('selected_type_id')!="simple")
           {
           var chkLngth=0;
           var chkTotal=0;
           console.log("dropdown_index_array----->"+dropdown_index_array.length+"<----dropdown_index_array[2]"+dropdown_index_array[j+2]);
           for(var j=0;j<dropdown_index_array.length;j++)
           {
           console.log("value of j---->>>"+j);
           console.log("dropdown_label_array------>>>"+dropdown_label_array.length);
       
           
          
            configHtml+="<li>"+dropdown_index_array[j]+"*:"+'<select class="drop_dwn" id="dropdown_selection'+j+'" onchange="detailDropDownLabelChange('+j+');">'+sessionStorage.getItem('labelHtml'+(j+1))+"</select></li>";
           
           
           //console.log("output1---->>"+output1);
           
           console.log("configHtml---->>"+configHtml);
           }
           } 
           console.log("html---->>>"+imageHtml+descHtml+newPriceHtml+oldPriceHtml+'<div style="clear:both;height:3px"></div>');
           $("#pro_details").append(imageHtml+descHtml+newPriceHtml+oldPriceHtml+'<div style="clear:both;height:3px"></div>');
           document.getElementById('config_selection').innerHTML=configHtml;
           

          
           }
           
           
           
           // end
           },
           error: function(response, textStatus, errorThrown)
           {
            
           console.log(response);
           }
           });
    
}

var storeId=1;
function cart_addMagento()
{
     // for cart creation
    
    for(var xy=0;xy<dropdown_index_array.length;xy++)
    {
        var selectedIndex=document.getElementById('dropdown_selection'+xy).value;
        if(selectedIndex == 'select')
        {
        var msg='please select a '+dropdown_index_array[xy];
            alertMessage(""+msg);
            return false;
        }
        
    }
    
    if(!sessionStorage.getItem("quote_id"))
    {
    var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
	var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>mobile_cart.create</resourcePath><args>'+storeId+'</args></call></soap:Body></soap:Envelope>';
    console.log(" cart creation soap request" + soapRequest);
               $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               dataType: "text",
               data: soapRequest,
               success: function(data,status,req)
               {
			   console.log(" cart creation response" + req.responseText);
			   var strJSON = req.responseText;
		       var n=strJSON.split("<callReturn xsi:type=");
		       var strJSON1=n[1].split("</callReturn>");
		       strJSON=strJSON1[0].split(">");
                      
		       sessionStorage.setItem('quote_id',strJSON[1]);
                      console.log("quote_id---->>>"+sessionStorage.getItem('quote_id'))
                     
		       // $(".loading").hide();
               
               // for default customer address creation
                      
               var customerAddress={"quoteId":sessionStorage.getItem('quote_id'),"0": {
               "mode": "shipping",
               "firstname": "NULL",
               "lastname": "NULL",
               "street": "NULL",
               "city": "NULL",
               "postcode": "NULL",
               "country_id": "NULL",
               "telephone": "NULL",
               "is_default_shipping": 0,
               "is_default_billing": 0
               },
               "1": {
               "mode": "billing",
               "firstname": "NULL",
               "lastname": "NULL",
               "street": "NULL",
               "city": "NULL",
               "postcode": "NULL",
               "country_id": "NULL",
               "telephone": "NULL",
               "is_default_shipping": 0,
               "is_default_billing": 0
               }
               };
		       setCustomerAddress(customerAddress);
                      setTimeout( function(){
                                  addToCartMagento();
                                 }
                                 , 900 );
                      
                      /*
                      if(placed=="placeorder")
               {
               createCustomerToCart();
               
               }
                       
                       */
               },
               error: function(data,status,req)
               {
                      alert("error");
             
               }
               });
    
    }
    else
    {
        addToCartMagento();
    }
    
    
}

function setCustomerAddress(customerAddress,chk)
{
    
    console.log('setCustomerAddress----customerAddress var >>>>'+customerAddress);
    var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
	var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>mobile_customer_address.create</resourcePath><args>'+JSON.stringify(customerAddress)+'</args></call></soap:Body></soap:Envelope>';
    
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data,status,req)
		   {
           console.log("setCustomerAddress()---->>"+req.responseText);
           },
           error: function(data,status,req)
           {
           
           }
           });
}

function addToCartMagento()
{
    
    
    console.log("in addToCartMagento()--->>selected_product_id---->>>"+sessionStorage.getItem('selected_product_id'));
   var qtyValue=document.getElementById("quantity").value;
   if(sessionStorage.getItem('selected_type_id')=="simple")
    {
    sessionStorage.setItem("ecomAvailquantity"+sessionStorage.getItem('selected_product_id'),sessionStorage.getItem("magentoquantity"));
        var array_simple_Product="";
    array_simple_Product=[{
                              "quoteId":sessionStorage.getItem('quote_id'),
                              "product_id":sessionStorage.getItem('selected_product_id'),
                              "qty":qtyValue
                              }];
    
    console.log("array_simple_Product---->>"+array_simple_Product);
    
    var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
	var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>mobile_cart.addcart</resourcePath><args>'+JSON.stringify(array_simple_Product)+'</args></call></soap:Body></soap:Envelope>';
        console.log("soapRequest---->>"+soapRequest);
        $.ajax({
               type:"POST",
               url: wsUrl,
               contentType: "text/json",
               dataType:"text",
               data:soapRequest,
               success:successAddToCart,
               error:failAddToCart
               });
        
    }
    else
    {
        
        for(var xy=0;xy<dropdown_index_array.length;xy++)
        {
            var selectedIndex=document.getElementById('dropdown_selection'+xy).value;
            if(selectedIndex == 'select')
            {
                var msg='please select a '+dropdown_index_array[xy];
                alertMessage(""+msg);
                return false;
            }
            
        }
        var selectedIndex=document.getElementById('dropdown_selection').selectedIndex;
        if(selectedIndex!=0)
        {
            
            var index=dropdown_index_array[0];
            var indexValue=dropdown_label_array[selectedIndex-1];
            var array_config_Product="";
            //alert(sessionStorage.getItem("set_itemId_for_cart"));
            if(sessionStorage.getItem("set_itemId_for_cart")!="")
            {
                array_config_Product="[{'quoteId':"+sessionStorage.getItem('quote_id')+",'itemId':"+sessionStorage.getItem('set_itemId_for_cart')+",'product_id':"+sessionStorage.getItem('selected_product_id')+",'super_attribute':{'"+index+"':"+indexValue+"},'qty':qtyValue}]";
            }
            else
            {
                array_config_Product="[{'quoteId':"+sessionStorage.getItem('quote_id')+",'product_id':"+sessionStorage.getItem('selected_product_id')+",'super_attribute':{'"+index+"':"+indexValue+"},'qty':qtyValue}]";
            }
            //for multiple product
            // var array_config_Product="[{'quoteId':"+sessionStorage.getItem('quote_id')+",'product_id':'10303','super_attribute':{'136':'12311'},'qty':2},{'quoteId':"+sessionStorage.getItem('quote_id')+",'product_id':'3498','qty':qtyValue}]";
            
            //console.log(""+array_config_Product);
            var args=JSON.stringify(eval('('+array_config_Product+')'));
            
            var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
            var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>mobile_cart.addcart</resourcePath><args>'+args+'</args></call></soap:Body></soap:Envelope>';
            //console.log("add to cart soap request="+soapRequest);
            $.ajax({
                   type:"POST",
                   url: wsurl,
                   contentType: "text/json",
                   dataType:"text",
                   data:soapRequest,
                   success:successAddToCart,
                   error:failAddToCart
                   });
        }
        else
        {
            alertMessage("choose an option.");
        }
    }
    
}
function failAddToCart()
{
    alert("err");
}
function successAddToCart(data, status, req)
{
    console.log("successAddToCart--->>");
    
    cart_viewMagento();
    
    /*
    cart_count=parseInt(JSON.parse(localStorage.getItem('cart_number')))+parseInt(qtyValue);
    //console.log("add to cart response="+req.responseText);
    localStorage.setItem('cart_number',cart_count);
    document.getElementById('cartCount').innerHTML=localStorage.getItem('cart_number');
    getContinueAlert();
   // $(".loading").hide();
     
     */
}






function plusButtonMagento()
{
    var quantity=document.getElementById("quantity").value;
    var parsedQuantity= parseInt(quantity)+1;
    document.getElementById("quantity").value= parsedQuantity;
    $("#slider").slider("value",parsedQuantity);
    if(sessionStorage.getItem("magentoquantity"))
    {
        if( parseInt(parsedQuantity) > parseInt(sessionStorage.getItem("magentoquantity")) )
        {
            document.getElementById("quantity").value=(parseInt(sessionStorage.getItem("magentoquantity")));
            $("#slider").slider("value",(parseInt(sessionStorage.getItem("magentoquantity"))));
        }
    }
}

function detailDropDownLabelChange(i)
{
    // console.log("value s---->"+checkDetails[1,1]);
    var originalPrice=parseFloat(sessionStorage.getItem("details_orginal_price"));
    var total=0;
    var selectedIndex='';
    var selectedIndexValue='';
    console.log("dropdown_index_array.length---->"+dropdown_index_array.length);
    for(var xy=0;xy<dropdown_index_array.length;xy++)
    {
     selectedIndex=document.getElementById('dropdown_selection'+xy).value;
       selectedIndexValue=document.getElementById('dropdown_selection'+xy).selectedIndex;
        if(selectedIndex != 'select')
        {
           
            var dropdownLabelData=new Array();
            dropdownLabelData=selectedIndex.split("###");
            
            if(dropdownLabelData.length!=0)
            {
                var isPercent="";
                var addedPrice=0;
                isPercent=dropdownLabelData[1];
                if(dropdownLabelData[2].length>0)
                {
                    addedPrice=parseFloat(dropdownLabelData[2]);
                }
                if(isPercent=="0")
                {
                    addedPrice=(addedPrice).toFixed(2);
                }
                else
                {
                    addedPrice=isPercent+'%';
                }
            }
             //console.log("xy--->"+(xy+1)+"<---selectedIndexValue--->"+selectedIndexValue);
            console.log("checkDetails["+(xy+1)+","+selectedIndexValue+"]------>>>"+checkDetails[xy+1,selectedIndexValue]);
    console.log("addedPrice---->>>"+addedPrice);
    if(selectedIndex.indexOf('%') == -1)
    {
    total=total+parseFloat(addedPrice);
    }
    else
    {
    total=total+(originalPrice*parseFloat(addedPrice)/100);
    }
        }
    
    }
    console.log("total price------>>"+total);
    document.getElementById('product_new_price').innerHTML="$"+(total+originalPrice);
}

function MyaccountMagento()
{
    if(!checkNetworkConnection())
    {
    }
    else
    {
        

    console.log("MyaccountMagento function");
    var navigationType = localStorage.getItem("navigation_Layout");
    
    
    clsvalue = 'backToDashbord';
    
    console.log("MyaccountMagento function");
    var MyaccountEcomHtml='<section id="container"><nav id="user_nav"><a onclick="MyaccountMagento();" class="active">Dashboard</a><a onclick="myOrdersEcom();">My Orders</a><a onclick="logoutMagento()">Logout</a></nav><div class="userPage-text"><h2>My Dashboard</h2><div class="user_tab" style="display:none">\
    <h3>Hello, Dharmenrdra Gupta</h3>\
    From your My Account Dashboard you have the ability to view a snapshot of your recent account activity and update your account information. Select a link below to view or edit information.</div>\
    <div class="user_tab" id="user_tab1">\
    <h4>Contact Information <a class="e-user_edit" onclick="conatct_infoMagento();">edit</a></h4>\
    </div>\
    <div class="user_tab" id="user_tab2">\
    <h4>Address Book <a class="e-user_edit" onclick="address_bookMagento();">edit</a></h4>\
    </div>\
        </section><ul class="sub-menu" style="display:none;" >\
        <li><a onclick="HomeEcom()" class="home-nav">Home</a></li>\
        \
        <li><a onclick="mainPageMagento()" class="account-nav">Main Menu</a></li>\
        \
        <li><a onclick="termsEcom()" class="terms-nav">Terms & Conditions</a></li>\
        <li><a onclick="PolicyEcom()" class="policy-nav">Privacy Policy</a></li>\
        <li id="logout"><a onclick="logoutMagento()" class="policy-nav">Log out</a></li>\
        </ul>';
    
    console.log("1" + MyaccountEcomHtml);
    
    Ext.define('appypie.view.MyAccountEcom', {
               
               extend: 'Ext.Container',
               requires:['Ext.TitleBar','Ext.dataview.List'],
               alias: 'widget.MyAccountEcom',
               id: 'MyAccountEcom',
               config: {
               layout: {
               type: 'vbox'
               },
               scrollable: {
               direction: 'vertical',
               cls:'page-content',
               directionLock: false
               },
               items: [
                       {
                       xtype: 'titlebar',
                       docked: 'top',
                       height: 53,
                       title: '<h1>'+appName+'</h1>',
                       id:'header',
                       items: [ {
                               xtype: 'button',
                               align: 'center',
                               height: 45,
                               width: 45,
                               style:'border-radius:0; border:0;',
                               id:'subMenu',
                               cls: 'subMenuButton',
                               handler:function() {
                               $(".sub-menu").slideToggle();
                               }
                               },
                               {
                               xtype: 'button',
                               align: 'right',
                               height: 40,
                               width: 75,
                               style:'border-radius:0; border:0;',
                               id:'cart',
                               cls: 'cartButton',
                               handler:function() {
                               mainPageMagento();
                               
                               }
                               }
                               ]
                       },
                       {
                       
                       html: [MyaccountEcomHtml]
                       
                       },
                       {
                       html: ['<div><br><br><br><br><br><br><br></div>'],
                       }
                       ]
               }
               });
    
    
    var MyAccountEcom={
    xtype: 'MyAccountEcom'
        
    };
    Ext.Viewport.removeAll();
    Ext.Viewport.animateActiveItem(MyAccountEcom, { type: 'slide', direction: 'left' });
        
       // showBillingShippingFood();
        showBillingShippinginfoMagento('MyAccount');
        showContactInfoMagento('MyAccount');

        
        
    }
    
    
}



function showBillingShippinginfoMagento(chk)
{
    
    
    var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>mobile_customer_address.list</resourcePath><args>'+localStorage.getItem('customer_id')+'</args></call></soap:Body></soap:Envelope>';
  
    console.log("showBillingShippinginfoMagento----->>>>soapRequest---->>>"+soapRequest)
    
    $.ajax({
           type:"POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType:"text",
           data:soapRequest,
           success:function(data,status,req)
           {
           console.log("showBillingShippinginfoMagento----->>>>responce---->>>"+req.responseText);
           var billing='';
           var shipping='';
           var fname=new Array(),lname=new Array(),city=new Array(),pincode=new Array(),street=new Array(),country_id=new Array(),country_name=new Array(),telephone=new Array(),region=new Array(),is_default_billing=new Array(),is_default_shipping=new Array();
           var customerAddressId=new Array();
           if($(req.responseText).find("item").find("item").length>0)
           {
           $(req.responseText).find("item").find("item").each(function()
                                                              {
                                                              if($(this).find("key").text()=="firstname")
                                                              {
                                                              fname.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="lastname")
                                                              {
                                                              lname.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="street")
                                                              {
                                                              
                                                              if($(this).find("value").text().indexOf("\n")!=-1)
                                                              {
                                                              var streetLine=$(this).find("value").text().split("\n");
                                                              street.push(streetLine[0]+" "+streetLine[1]);
                                                              }
                                                              else
                                                              {
                                                              street.push($(this).find("value").text());
                                                              }
                                                              }
                                                              if($(this).find("key").text()=="city")
                                                              {
                                                              city.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="postcode")
                                                              {
                                                              pincode.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="region")
                                                              {
                                                              region.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="country_id")
                                                              {
                                                              country_id.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="country_name")
                                                              {
                                                              country_name.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="telephone")
                                                              {
                                                              telephone.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="customer_address_id")
                                                              {
                                                              customerAddressId.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="is_default_billing")
                                                              {
                                                              is_default_billing.push($(this).find("value").text());
                                                              }
                                                              if($(this).find("key").text()=="is_default_shipping")
                                                              {
                                                              is_default_shipping.push($(this).find("value").text());
                                                              }
                                                              });
           
           if(chk == 'MyAccount')
           {
           for(var i=0; i<is_default_shipping.length; i++)
           {
                                              // var address=fname[i]+" "+lname[i]+","+street[i]+","+city[i]+","+region[i]+" "+pincode[i]+","+country_name[i];
                                if(lname[i] == null)
                                {
                                        lname[i]=' ';
                                }
                                                if(is_default_billing[i]=="true")
                                               {
                                               billing="<strong>Default Billing Address</strong><br/>";
                                               billing+=fname[i]+' '+lname[i]+'<br/>';
                                               billing+=street[i]+'<br/>';
                                               billing+=telephone[i]+'<br/>';
                                               billing+=city[i]+', ';
                                               billing+=region[i]+', ';
                                               billing+=pincode[i]+'<br/>';
                                               billing+=country_name[i]+'<br/>';
                                              sessionStorage.setItem('phoneForMagentoSignup',telephone[i]);
                                               }
                                            if(is_default_shipping[i]=="true")
                                               {
                                               shipping="<strong>Default Shipping Address</strong><br/>";
                                               shipping+=fname[i]+' '+lname[i]+'<br/>';
                                               shipping+=street[i]+'<br/>';
                                               shipping+=telephone[i]+'<br/>';
                                               shipping+=city[i]+', ';
                                               shipping+=region[i]+', ';
                                               shipping+=pincode[i]+'<br/>';
                                               shipping+=country_name[i]+'<br/>';
                                               }
           }
           
           
           
           html=billing + "<br/>" + shipping ;
           // $("#user_tab2").empty();
           console.log("html --->>>showBillingShippinginfoFood-->> "+html);
           $("#user_tab2").append(html);
           }
           else
           {
           for(var i=0; i<is_default_shipping.length; i++)
           {
           if(lname[i] == null)
           {
           lname[i]=' ';
           }
           if(is_default_billing[i]=="true")
           {
           sessionStorage.setItem("chkDefaultBillingAddressId",customerAddressId[i]);
           var sfname=document.getElementById("sfname");
           sfname.value=fname[i]
           var slname=document.getElementById("slname");
           slname.value= lname[i];
           var spNo=document.getElementById("spNo");
           spNo.value=telephone[i];
           sessionStorage.setItem('phoneForMagentoSignup',telephone[i]);
           var ssAddress=document.getElementById("ssAddress");
           ssAddress.value=street[i];
           var sCity=document.getElementById("sCity");
           sCity.value= city[i];
           var sState=document.getElementById("sState");
           sState.value= region[i];
           var sZip=document.getElementById("sZip");
           sZip.value=pincode[i];
           
           for(var x=0;x<countryNameList.length;x++)
           {
           if(country_name[i] == countryNameList[x])
           {
           var sCountry=document.getElementById("sCountry");
           sCountry.value=countryIdList[x];
           }
           }

           
           }
           if(is_default_shipping[i]=="true")
           {
           
           sessionStorage.setItem("chkDefaultShippingAddressId",customerAddressId[i]);
           var bfname=document.getElementById("bfname");
           bfname.value=fname[i];
           var blname=document.getElementById("blname");
           blname.value= lname[i];
           var bpNo=document.getElementById("bpNo");
           bpNo.value= telephone[i];
           var bsAddress=document.getElementById("bsAddress");
           bsAddress.value=street[i];
           var bCity=document.getElementById("bCity");
           bCity.value=city[i];
           var bState=document.getElementById("bState");
           bState.value= region[i];
           var bZip=document.getElementById("bZip");
           bZip.value= pincode[i];
           for(var x=0;x<countryNameList.length;x++)
           {
           if(country_name[i] == countryNameList[x])
           {
           var bCountry=document.getElementById("bCountry");
           bCountry.value=countryIdList[x];
           }
           }
           
           
           
           
           
           
           }
           }
           if(tcount == 0)
           {
           console.log("BillingShippingFlag---->>>0");
           sessionStorage.setItem("BillingShippingFlag",0);
           }
           else
           {
           console.log("BillingShippingFlag---->>>1");
           sessionStorage.setItem("BillingShippingFlag",1);
           }
           
           }
           
           }
           else
           {
           billing= "you have not set <br/>a default billing address.";
           shipping="you have not set <br/>a default shipping address.";
           }
           
           },
           error: function(XMLHttpRequest, textStatus, errorThrown) {
           
           console.log('fail');
           console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
           }
           });
}


function showContactInfoMagento(chk)
{
    var html1='';
    
    var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>customer.info</resourcePath><args>'+localStorage.getItem('customer_id')+'</args></call></soap:Body></soap:Envelope>';
    
    //var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://www.integritysupply.com/api/soap#call/\"><sessionId>'+localStorage.getItem('token')+'</sessionId><resourcePath>customer.info</resourcePath><args>'+localStorage.getItem('customer_id')+'</args></call></soap:Body></soap:Envelope>';
    console.log("customer info soap request="+soapRequest);
    $.ajax({
           type:"POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType:"text",
           data:soapRequest,
           success:function(data,status,req)
           {
           console.log("req.responseText----->>>showContactInfoMagento()"+req.responseText);
           if($(req.responseText).find("faultstring").length>0)
           {
           var message='Sorry ! '+$(req.responseText).find("faultstring").text();
           alertMessage(""+message);
           if($(req.responseText).find("faultstring").text().indexOf("Session expired. Try to relogin.")!=-1)
           {
          // localStorage.clear();
          // window.location.href="home.html";
           }
           //$(".loading").hide();
           }
           else
           {
           if($(req.responseText).find("item").length>0)
           {
           
           var Name='';
           var email='';
           var phone=sessionStorage.getItem('phoneForMagentoSignup');
           var lastname='';

           $(req.responseText).find("item").each(function()
                                                 {
                                                 if($(this).find("key").text()=="firstname")
                                                 {
                                                 localStorage.setItem("customer_fname",$(this).find("value").text());
                                                 Name=$(this).find("value").text();
                                                 }
                                                 if($(this).find("key").text()=="lastname")
                                                 {
                                                 localStorage.setItem("customer_lname",$(this).find("value").text());
                                                 lastname=$(this).find("value").text();
                                                 }
                                                 if($(this).find("key").text()=="email")
                                                 {
                                                 email=$(this).find("value").text();
                                                 localStorage.setItem("customer_email",$(this).find("value").text());
                                                 }
                                                 });
           if(chk == 'MyAccount')
           {
           sessionStorage.setItem("nameForMagentoSignup",Name);
           if(phone == null || phone == '')
           {
           phone=' ';
           }
           if(lastname == '' || lastname == null)
           {
           lname=' ';
           }
           html1+=Name+' '+ lastname + "<br/>"+ email+ "<br/>"+ phone;
           $("#user_tab1").append(html1);
           html1='';
           }
           else
           {
           var fname=document.getElementById('fname');
           fname.value=Name;
           var emailid=document.getElementById('emailid');
           emailid.value=email;
           var lname=document.getElementById('lname');
           lname.value=lastname;
           }
           
           }
           else
           {
           alertMessage("Network problem.");
           }
           //$(".loading").hide();
           }
           },
           error: function(data,status,req)
           {
          // $(".loading").hide();
           
           }
           });
}
function logoutMagento()
{
    localStorage.removeItem("customer_email");
    localStorage.removeItem("customer_id");
    localStorage.removeItem("fooduserid");
    sessionStorage.removeItem("quote_id");
    sessionStorage.removeItem("magentocheckoutFlag");
     localStorage.removeItem("Loginquote_id");
    magento();
}





function conatct_infoMagento()
{
    if(!checkNetworkConnection())
    {
    }
    else
    {
    
    console.log("termsEcom function");
    var navigationType = localStorage.getItem("navigation_Layout");
    
    
    clsvalue = 'backToDashbord';
    
    console.log("terms ecom function");
    var conatctinfoHtml='<section id="container">\
    <div class="userPage-text">\
    <h2>Contact Information </h2><div class="cart-MSG" id="cart-MSG1" style="display:none">\
    <div class="cart_success">Information Updated Successfully.</div>\
    </div>\
    <div class="cart-MSG" id="cart-MSG2" style="display:none">\
    <div class="cart_error"></div>\
    </div>\
    \
    <div class="user_tab">\
    <h4>Personal Information <a onclick="updateMagentoPesonalInformation();" class="e-user_save">Save</a></h4>\
    <div class="form-page">\
    \
    <strong>First Name<span class="required">*</span></strong><input id="fname" type="text" placeholder="First Name">\
    \
        <strong>Last Name<span class="required">*</span></strong><input id="lname" type="text" placeholder="Last Name">\
    <strong>Email Address<span class="required">*</span></strong><input id="emailid" type="text" placeholder="Enter your Email ID" readonly="readonly">\
    \
    </div></div></div></section><ul class="sub-menu" style="display:none;" >\
    <li><a onclick="HomeEcom()" class="home-nav">Home</a></li>\
    \
    <li id="signup"><a onclick="MyaccountMagento()" class="account-nav">My Account</a></li>\
    <li><a onclick="ecomHomePageMagento()" class="account-nav">Main Menu</a></li>\
    <li id="cartView"><a onclick="cart_viewMagento()" class="cart-nav">Cart</a></li>\
    <li><a onclick="termsEcom()" class="terms-nav">Terms & Conditions</a></li>\
    <li><a onclick="PolicyEcom()" class="policy-nav">Privacy Policy</a></li>\
        <li id="logout"><a onclick="logoutMagento()" class="policy-nav">Log out</a></li>\
    </ul>';
    
    console.log("1" + conatctinfoHtml);
    
    Ext.define('appypie.view.conatctinfo', {
               
               extend: 'Ext.Container',
               requires:['Ext.TitleBar','Ext.dataview.List'],
               alias: 'widget.conatctinfo',
               id: 'conatctinfo',
               config: {
               layout: {
               type: 'vbox'
               },
               scrollable: {
               direction: 'vertical',
               cls:'page-content',
               directionLock: false
               },
               items: [
                       {
                       xtype: 'titlebar',
                       docked: 'top',
                       height: 53,
                       title: '<h1>'+appName+'</h1>',
                       id:'header',
                       items: [ {
                               xtype: 'button',
                               align: 'center',
                               height: 45,
                               width: 45,
                               style:'border-radius:0; border:0;',
                               id:'subMenu',
                               cls: 'subMenuButton',
                               handler:function() {
                               $(".sub-menu").slideToggle();
                               }
                               },
                               {
                               xtype: 'button',
                               align: 'right',
                               height: 40,
                               width: 75,
                               style:'border-radius:0; border:0;',
                               id:'cart',
                               cls: 'cartButton',
                               handler:function() {
                               MyaccountMagento();
                               
                               }
                               }
                               ]
                       },
                       {
                       
                       html: [conatctinfoHtml]
                       
                       },
                       {
                       html: ['<div><br><br><br><br><br><br><br></div>'],
                       }
                       ]
               }
               });
    
    
    var conatctinfo={
    xtype: 'conatctinfo'
        
    };
    Ext.Viewport.removeAll();
    Ext.Viewport.animateActiveItem(conatctinfo, { type: 'slide', direction: 'left' });
        
        showContactInfoMagento();
        if(parseFloat(sessionStorage.getItem("magentocheckoutFlag")) > 0)
        {
            document.getElementById("cartView").style.display="block";
            console.log("magentocheckoutFlag Value--->"+sessionStorage.getItem("magentocheckoutFlag"));
        }
        else
        {
            document.getElementById("cartView").style.display="none";
            console.log("magentocheckoutFlag Value in else condition--->"+sessionStorage.getItem("magentocheckoutFlag"));
            
        }
        
    }
}

function updateMagentoPesonalInformation()
{
    var sfname=document.getElementById("fname").value;
     var slname=document.getElementById("lname").value;
    var semailId=localStorage.getItem("fooduserid");
  
    
    
    
    if(sfname=='' || sfname=='First Name')
    {
        $(".cart_error").empty();
        $(".cart_error").append('Please enter first name');
        
        $("#cart-MSG2").slideToggle();
        
        setTimeout( function(){
                   $("#cart-MSG2").slideToggle();
                   }
                   , 3000 );
        setTimeout( function(){
                   $("#fname").focus();
                   }
                   , 3000 );
        return;
    }
    else if(slname == '' || slname == 'Last Name')
    {
        $(".cart_error").empty();
        $(".cart_error").append('Please enter last name');
        
        $("#cart-MSG2").slideToggle();
        
        setTimeout( function(){
                   $("#cart-MSG2").slideToggle();
                   }
                   , 3000 );
        setTimeout( function(){
                   $("#spNo").focus();
                   }
                   , 3000 );
        return;
    }
    else if(semailId=='' || semailId=='Enter your Email ID' || !checkEmail(semailId) )
    {
        $(".cart_error").empty();
        $(".cart_error").append('Please enter correct Email');
        
        $("#cart-MSG2").slideToggle();
        
        setTimeout( function(){
                   $("#cart-MSG2").slideToggle();
                   }
                   , 3000 );
        setTimeout( function(){
                   $("#spNo").focus();
                   }
                   , 3000 );
        return;
    }
     
    
    var jsonData={"customerId":localStorage.getItem('customer_id'),"customerData":{"firstname":sfname,"lastname":slname,"email":semailId}};
    var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
	var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>mobile_customer.update</resourcePath><args>'+JSON.stringify(jsonData)+'</args></call></soap:Body></soap:Envelope>';
    
    
    console.log("updateMagentoPesonalInformation--->>"+soapRequest);
    
   // jQuery.support.cors = true;
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           
           var strJSON = $(req.responseText).find("return").text();
           
           $("#cart-MSG1").slideToggle();
           setTimeout( function(){ 
                      $("#cart-MSG1").slideToggle();
                      MyaccountMagento();
                      }
                      , 3000 );
           
           
           },
           error: function(response, textStatus, errorThrown) 
           {
           
           console.log(response);
           }
           });
    
}






function address_bookMagento()
{
    
    if(!checkNetworkConnection())
    {
    }
    else
    {
        

    console.log("termsEcom function");
    var navigationType = localStorage.getItem("navigation_Layout");
    
    
    clsvalue = 'backToDashbord';
    
    console.log("address book function");
    var addressbookHtml='<section id="container" class="page-content"><div class="userPage-text"><h2>Address Book</h2><div class="user_tab"><h4>Billing Address <a onclick="updateMagentoDefaultBilling();" class="e-user_save">Save</a>\
    </h4>\
    <div class="cart-MSG" id="cart-MSG1" style="display:none">\
    <div class="cart_success">Shipping Information Updated Successfully.</div>\
    </div>\
    <div class="form-page">\
    <strong>\
   First Name<span class="required">*</span>\
    </strong>\
    <input id="sfname" type="text" placeholder="First Name">\
    <strong>\
        <strong>\
        Last Name<span class="required">*</span>\
        </strong>\
        <input id="slname" type="text" placeholder="Last Name">\
    Telephone No<span class="required">*</span>\
    </strong>\
    <input id="spNo"  type="text" placeholder="Telephone No">\
    <strong>\
    Street Address<span class="required">*</span>\
    </strong>\
    <input id="ssAddress"  type="text" placeholder="Street Address">\
    <strong>\
    City<span class="required">*</span>\
    </strong>\
    <input id="sCity"  type="text" placeholder="Enter Your City">\
    <strong>\
    State/Province<span class="required">*</span>\
    </strong>\
    <input id="sState" type="text" placeholder="Enter State/Province">\
    <strong>\
    Zip/Postal Code<span class="required">*</span>\
    </strong>\
    <input id="sZip"  type="text" placeholder="Enter Zip/Postal Code">\
    <strong>\
    Country<span class="required">*</span>\
    </strong>\
    <div id="scountryTry"></div>\
    <strong>\
    <input type="checkbox" id="show_billing_address"/> Billing Address Different From Shipping Address\
    </strong>\
    </div>\
    </div>\
    <div class="user_tab" id="billing_address">\
    <h4>\
    Shipping Address<a onclick="updateMagentoDefaultShipping()" class="e-user_save">Save</a>\
    </h4>\
    <div class="cart-MSG" id="cart-MSG2" style="display:none">\
    \
    <div class="cart_success">Billing Information Updated Successfully.</div>\
    </div>\
    <div class="form-page">\
    <strong>\
    First Name<span class="required">*</span>\
    </strong>\
    <input type="text"  id="bfname" placeholder="First Name">\
        <strong>\
        Last Name<span class="required">*</span>\
        </strong>\
        <input type="text"  id="blname" placeholder="Last Name">\
\
    <strong>\
    Telephone No<span class="required">*</span>\
    </strong>\
    <input type="text"  id="bpNo" placeholder="Telephone No">\
    <strong>\
    Street Address<span class="required">*</span>\
    </strong>\
    <input type="text"  id="bsAddress" placeholder="Street Address">\
    <strong>\
    City<span class="required">*</span>\
    </strong>\
    <input type="text"  id="bCity" placeholder="Enter Your City">\
    <strong>\
    State/Province<span class="required">*</span>\
    </strong>\
    <input type="text"  id="bState" placeholder="Enter State/Province">\
    <strong>\
    Zip/Postal Code<span class="required">*</span>\
    </strong>\
    <input type="text"  id="bZip" placeholder="Enter Zip/Postal Code">\
    <strong>\
    Country<span class="required">*</span>\
    </strong>\
    <div id="bcountryTry"></div>\
    </div></div></div></section><ul class="sub-menu" style="display:none;" >\
    <li><a onclick="HomeEcom()" class="home-nav">Home</a></li>\
    \
    <li id="signup"><a onclick="MyaccountMagento()" class="account-nav">My Account</a></li>\
    <li><a onclick="ecomHomePageMagento()" class="account-nav">Main Menu</a></li>\
    <li id="cartView"><a onclick="cart_viewMagento()" class="cart-nav">Cart</a></li>\
    <li><a onclick="termsEcom()" class="terms-nav">Terms & Conditions</a></li>\
    <li><a onclick="PolicyEcom()" class="policy-nav">Privacy Policy</a></li>\
    <li id="logout"><a onclick="logoutMagento()" class="policy-nav">Log out</a></li>\
    </ul>';
    
    // console.log("1" + addressbookHtml);
    
    Ext.define('appypie.view.addressbook', {
               
               extend: 'Ext.Container',
               requires:['Ext.TitleBar','Ext.dataview.List'],
               alias: 'widget.addressbook',
               id: 'addressbook',
               config: {
               layout: {
               type: 'vbox'
               },
               scrollable: {
               direction: 'vertical',
               cls:'page-content',
               directionLock: false
               },
               items: [
                       {
                       xtype: 'titlebar',
                       docked: 'top',
                       height: 53,
                       title: '<h1>'+appName+'</h1>',
                       id:'header',
                       items: [ {
                               xtype: 'button',
                               align: 'center',
                               height: 45,
                               width: 45,
                               style:'border-radius:0; border:0;',
                               id:'subMenu',
                               cls: 'subMenuButton',
                               handler:function() {
                               $(".sub-menu").slideToggle();
                               }
                               },
                               {
                               xtype: 'button',
                               align: 'right',
                               height: 40,
                               width: 75,
                               style:'border-radius:0; border:0;',
                               id:'cart',
                               cls: 'cartButton',
                               handler:function() {
                               Ext.Viewport.removeAll();
                               MyaccountMagento();
                               }
                               }
                               ]
                       },
                       {
                       
                       html: [addressbookHtml]
                       
                       },
                       {
                       html: ['<div><br><br><br><br><br><br><br></div>'],
                       }
                       ]
               }
               });
    
    
    var addressbook={
    xtype: 'addressbook'
        
    };
    Ext.Viewport.removeAll();
    Ext.Viewport.animateActiveItem(addressbook, { type: 'slide', direction: 'left' });
    loadCountryList();
        setTimeout( function(){
                   showBillingShippinginfoMagento();
                    
                   }
                   , 1000 );
        
    
        if(parseFloat(sessionStorage.getItem("magentocheckoutFlag")) > 0)
        {
            document.getElementById("cartView").style.display="block";
            console.log("magentocheckoutFlag Value--->"+sessionStorage.getItem("magentocheckoutFlag"));
        }
        else
        {
            document.getElementById("cartView").style.display="none";
            console.log("magentocheckoutFlag Value in else condition--->"+sessionStorage.getItem("magentocheckoutFlag"));
            
        }
    $(".sub-menu,.e-mobileOH_subtab, .e-mobileOD, #billing_address").hide();
    $(".e-menu").click(function(){
                       $(".sub-menu").slideToggle();
                       });
    
    // For Order Page
    $(".e-mobileOH_tab h3").click(function(){
                                  $(this).parents(".e-mobileOH_tab").toggleClass("open_tab");
                                  $(this).parents(".e-mobileOH_tab").toggleClass("close_tab");
                                  $(this).parents(".e-mobileOH_tab").children("div").slideToggle();
                                  });
    
    $(".e-mobileOH_subtab h5").click(function(){
                                     $(this).toggleClass("open_tab");
                                     $(this).toggleClass("close_tab");
                                     $(this).parents(".e-mobileOH_subtab").children("span").slideToggle();
                                     });
    
    $("#show_billing_address").click(function(){
                                     $("#billing_address").slideToggle();
                                     });
    
    
    }
}

function updateMagentoDefaultBilling()
{
    
    var sfname=document.getElementById("sfname").value;
    var slname=document.getElementById("slname").value;
    var spNo=document.getElementById("spNo").value;
    var ssAddress=document.getElementById("ssAddress").value;
    var sCity=document.getElementById("sCity").value;
    var sState=document.getElementById("sState").value;
    var sZip=document.getElementById("sZip").value;
    var sCountry=document.getElementById("sCountry").value;
    if(document.getElementById("show_billing_address").checked)
    {
        var setDefaultBilling='false';
    }
    else
    {
        var setDefaultBilling='true';
    }
    
    if(sfname=='' || sfname=='Name')
    {
    	alertPopUp('Error','Please Enter Name');
      
        $("#sfname").focus();
        return;
    }
    
    else if(spNo=='' || spNo=='Phone Number' )
    {
    	alertPopUp('Error','Please Enter Valid Phone number');
        
        $("#spNo").focus();
        return;
    }
    else if(ssAddress=='' || ssAddress=='Street Address' )
    {
    	alertPopUp('Error','Please Enter Street Address');
       
        $("#ssAddress").focus();
        return;
    }
    else if(sCity=='' || sCity=='Enter Your City' )
    {
    	alertPopUp('Error','Please Enter Your City');
        
        $("#sCity").focus();
        return;
    }
    else if(sState=='' || sState=='Enter State/Province' )
    {
    	alertPopUp('Error','Please Enter State/Province');
       
        $("#sState").focus();
        return;
    }
    else if(sZip=='' || sZip=='Enter Zip/Postal Code' )
    {
    	alertPopUp('Error','Please Enter Zip/Postal Code');
        
        $("#sZip").focus();
        return;
    }
    else if(sCountry=='' || sCountry=='Enter Country' )
    {
    	alertPopUp('Error','Please Enter Country');
       
        $("#sCountry").focus();
        return;
    }
    
    
    
   // var shipping='{"billShip":"Shipping","name":"'+bfname + '","address":"'+bsAddress+'","city":"'+bCity+'","state":"'+bState+'","country":"'+bCountry+'","zip":"'+bZip+'","phone":"'+bpNo+'"}';
    setTimeout( function(){
               //sessionStorage.setItem("chkDefaultBillingAddressId",customerAddressId[i]);
               //sessionStorage.setItem("chkDefaultShippingAddressId",customerAddressId[i]);
               if(sessionStorage.getItem("chkDefaultBillingAddressId") && sessionStorage.getItem("chkDefaultBillingAddressId") != sessionStorage.getItem("chkDefaultShippingAddressId"))
               {
               var jsonData={"addressId":sessionStorage.getItem("chkDefaultBillingAddressId"),"addressdata":{"firstname":sfname,"lastname":slname,"street":ssAddress,"city":sCity,"country_id":sCountry,"region":sState,"region_id":"","postcode":sZip, "telephone":spNo, "is_default_billing":"true","is_default_shipping":setDefaultBilling}};
               var resourcePath="mobile_customer_address.update";
               }
               else
               {
               //var jsonData={"customerId":localStorage.getItem("customer_id"),"addressdata":{"firstname":sfname,"lastname":slname,"street":ssAddress,"city":sCity,"country_id":sCountry,"region":sState,"region_id":"","postcode":sZip, "telephone":spNo, "is_default_billing":"true","is_default_shipping":setDefaultBilling}};
               var resourcePath="mobile_customer_address.create";
               
               var jsonData={"customerId":localStorage.getItem("customer_id"),"addressdata": {
               "firstname": sfname,
               "lastname":  slname,
               "street":  ssAddress,
               "city":  sCity,
               "postcode":  sZip,
               "region":sState,
               "country_id":  sCountry,
               "telephone": spNo,
               "is_default_shipping":setDefaultBilling,
               "is_default_billing": "true"
               }
               };
               
               }
               var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
               var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>'+resourcePath+'</resourcePath><args>'+JSON.stringify(jsonData)+'</args></call></soap:Body></soap:Envelope>';
    //wsUrl = "http://"+resellerInitial+reseller+"/api/soap#ecommMyAccount";
    //soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommMyAccount xmlns=\"http://appypieml.onsisdev.info/services/ecomm-soap#ecommMyAccount\"><appId>cda2e4aa8dab</appId><userName>'+window.localStorage.getItem("fooduserid")+'</userName><shipping>'+shipping+'</shipping></ecommMyAccount></soap:Body></soap:Envelope>';
    
    
   //  soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommMyAccount xmlns=\"http://'+resellerInitial+reseller+'/api/soap#ecommMyAccount\"><appId>'+appId+'</appId><userName>'+localStorage.getItem("fooduserid")+'</userName><shipping>'+shipping+'</shipping></ecommMyAccount></soap:Body></soap:Envelope>';
    console.log(soapRequest);
    //jQuery.support.cors = true;
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
          
           var xmlret=req.responseText;
           console.log('req.responseText==>'+req.responseText);
           var strJSON = $(xmlret).find("return").text();
           console.log('strJSON==>'+strJSON);
             
           $("#cart-MSG1").slideToggle();
           
           setTimeout( function(){ 
                      $("#cart-MSG1").slideToggle();
                      MyaccountMagento();
                      }
                      , 3000 );
           
           },
           error: function(response, textStatus, errorThrown) 
           {
           
           console.log(response);
           }
           });
               }
               , 100 );
}



function updateMagentoDefaultShipping()
{
    
    var bfname=document.getElementById("bfname").value;
    var blname=document.getElementById("blname").value;
    var bpNo=document.getElementById("bpNo").value;
    var bsAddress=document.getElementById("bsAddress").value;
    var bCity=document.getElementById("bCity").value;
    var bState=document.getElementById("bState").value;
    var bZip=document.getElementById("bZip").value;
    var bCountry=document.getElementById("bCountry").value;
   
    
    if(bfname=='' || bfname=='Name')
    {
    	alertPopUp('Error','Please Enter Name');
        
        $("#sCountry").focus();
        return;
    }
    
    else if(bpNo=='' || bpNo=='Phone Number' )
    {
    	alertPopUp('Error','Please Enter Valid Phone number');
        
        $("#bpNo").focus();
        return;
    }
    else if(bsAddress=='' || bsAddress=='Street Address' )
    {
    	alertPopUp('Error','Please Enter Street Address');
        
        $("#bsAddress").focus();
        return;
    }
    else if(bCity=='' || bCity=='Enter Your City' )
    {
    	alertPopUp('Error','Please Enter Your City');
       
        $("#bCity").focus();
        return;
    }
    else if(bState=='' || bState=='Enter State/Province' )
    {
    	alertPopUp('Error','Please Enter State/Province');
        
        $("#bState").focus();
        return;
    }
    else if(bZip=='' || bZip=='Enter Zip/Postal Code' )
    {
    	alertPopUp('Error','Please Enter Zip/Postal Code');
       
        $("#bZip").focus();
        return;
    }
    else if(bCountry=='' || bCountry=='Enter Country' )
    {
    	alertPopUp('Error','Please Enter Country');
        
        $("#bCountry").focus();
        return;
    }
    
    
 //  var billing= '{"billShip":"Billing","name":"'+sfname + '","address":"'+ssAddress+'","city":"'+sCity+'","state":"'+sState+'","country":"'+sCountry+'","zip":"'+sZip+'","phone":"'+spNo+'"}';
    
    
    if(sessionStorage.getItem("chkDefaultShippingAddressId") && sessionStorage.getItem("chkDefaultBillingAddressId") != sessionStorage.getItem("chkDefaultShippingAddressId"))
    {
    var jsonData={"addressId":sessionStorage.getItem("chkDefaultShippingAddressId"),"addressdata":{"firstname":bfname,"lastname":blname,"street":bsAddress,"city":bCity,"country_id":bCountry,"region":bState,"region_id":"","postcode":bZip, "telephone":bpNo, "is_default_billing":"false","is_default_shipping": "true"}};
        var resourcePath="mobile_customer_address.update";
       
    }
    else
    {
      //  var jsonData={"customerId":localStorage.getItem("customer_id"),"addressdata":{"firstname":bfname,"lastname":blname,"street":bsAddress,"city":bCity,"country_id":bCountry,"region":bState,"region_id":"","postcode":bZip, "telephone":bpNo, "is_default_billing":"","is_default_shipping": "true"}};
        
        var jsonData={"customerId":localStorage.getItem("customer_id"),"addressdata": {
                "firstname": bfname,
                "lastname":  blname,
                "street":  bsAddress,
                "city":  bCity,
                "postcode":  bZip,
                "region":bState,
                "country_id":  bCountry,
                "telephone": bpNo,
                "is_default_shipping": "true",
                "is_default_billing": "false"
            }
        };
         var resourcePath="mobile_customer_address.create";
        
    }
    
    
    setTimeout( function(){
 //   wsUrl = "http://"+resellerInitial+reseller+"/api/soap#ecommMyAccount";
               
               var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
               var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>'+resourcePath+'</resourcePath><args>'+JSON.stringify(jsonData)+'</args></call></soap:Body></soap:Envelope>';
               
             //  var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://www.integritysupply.com/api/soap#call/\"><sessionId>'+localStorage.getItem('token')+'</sessionId><resourcePath>mobile_customer_address.update</resourcePath><args>'+JSON.stringify(jsonData)+'</args></call></soap:Body></soap:Envelope>';
    
  //  soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommMyAccount xmlns=\"http://'+resellerInitial+reseller+'/api/soap#ecommMyAccount\"><appId>'+appId+'</appId><userName>'+localStorage.getItem("fooduserid")+'</userName><billing>'+billing+'</billing></ecommMyAccount></soap:Body></soap:Envelope>';
    
    console.log(soapRequest);
    //jQuery.support.cors = true;
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
            
           var xmlret=req.responseText;
           console.log('req.responseText==>'+req.responseText);
           var strJSON = $(xmlret).find("return").text();
           console.log('strJSON==>'+strJSON);
           $("#cart-MSG2").slideToggle();
           
           setTimeout( function(){ 
                      $("#cart-MSG2").slideToggle();
                      MyaccountMagento();  
                      }
                      , 3000 );
           
           },
           error: function(response, textStatus, errorThrown) 
           {
            
           console.log(response);
           }
           });
               }
               , 100 );
}




function cart_viewMagento()
{
    
    if(!checkNetworkConnection())
    {
    }
    else
    {
        
        setTimeout(function(){
                   console.log("cart ecom function");
                   var navigationType = localStorage.getItem("navigation_Layout");
                   
                   
                   clsvalue = 'backToDashbord';
                   console.log("terms ecom function");
                   var cartHtml='<div class="product-top cart-header">\
                   <h3 style="color:#000">Shopping Cart</h3>\
                   <div class="checkOut-Btn" id="editoncart1" onclick="showeditOnCartmagento();" style="display:block">Edit</div>\
                   </div><section id="container" class="cart-container">\
                   <div id="scroll_contener" class="scroll_contener">\
                   <div class="food-ordering-product">\
                   <div class="cart-MSG" id="cart-MSG1" style="display:none">\
                   <span class="er_close">x</span>\
                   <div class="cart_success" id="cart_message">Product successfully Added In Your Cart.</div>\
                   </div><div class="cart-MSG" id="cart-MSG1-coupon" style="display:none">\
                   <span class="er_close">x</span>\
                   <div class="cart_success">Coupon Applied</div>\
                   </div>\
                   <div class="cart-MSG" id="cart-MSG2" style="display:none">\
                   <span class="er_close">x</span>\
                   <div class="cart_error"></div>\
                   </div>\
                   \
                   <div  id="user_tab1">\
                   \
                   \
                   </div>\
                   \
                   \
                   <div class="user_tab" style="display:none">\
                   <h4>Discount Codes</h4>\
                   Enter your coupon code if you have one.\
                   \
                   <div class="form-page">\
                   <input type="text" />\
                   </div>\
                   <div class="cart-update-btn">\
                   <a class="food-mobile-btn f-right">Clear Cart</a>\
                   </div>\
                   \
                   </div>\
                   \
                   \
                   <div class="user_tab" id="user_tab3" style="display:none">\
                   <h4>PAYMENT DETAILS</h4>\
                   <ul class="pay-mobile-cart">\
                   <li >\
                   Subtotal <span id="subtotal"></span>\
                   </li>\
                   <li id="deliverycharges">\
                   Delivery Charge <span id="delivery"></span>\
                   </li>\
                   \
                   <li id="taxcharges">\
                   Tax <span  id="tax"></span>\
                   </li>\
                   \
                   <li id="discountcharges">\
                   Discount <span id="discount"></span>\
                   </li>\
                   <li id="couponDiv" style="display:none;">Coupon <span id="coupon"></span></li>\
                   <li>\
                   Grand Total<span id="gtotal"></span>\
                   </li>\
                   </ul>\
                   <a id="apply_coupon" class="couponsubmit_btn" id="coupons" onclick="coupon_Magento();" >Apply Coupon Code</a>\
                   <div class="food-cart-update-btn">\
                   </div>\
                   \
                   </div></div></div></section><ul class="sub-menu" style="display:none;" >\
                   <li><a onclick="HomeEcom()" class="home-nav">Home</a></li>\
                   <li id="login"><a onclick="loginEcommerceMagento()" class="login-nav">Login / Registration</a></li>\
                   <li id="signup"><a onclick="MyaccountMagento()" class="account-nav">My Account</a></li>\
                   <li><a onclick="mainPageMagento()" class="account-nav">Main Menu</a></li>\
                   <li><a onclick="termsEcomMagento()" class="terms-nav">Terms & Conditions</a></li>\
                   <li><a onclick="PolicyEcomMagento()" class="policy-nav">Privacy Policy</a></li>\
                   <li id="logout"><a onclick="logoutMagento()" class="policy-nav">Log out</a></li>\
                   </ul>';
					$("#contentHolder4").empty();
					$("#contentHolder4").append(cartHtml);
					if(window.localStorage.getItem("layout")=="bottom") {
						$(".app_navigation_bottom").hide();
						window.localStorage.setItem("bottomHide","ture");
					}else{
						$("#contentHolder4 .food-ordering-product").css('padding-bottom','61px'); 
					}
					$("#contentHolder4").append("<div class='cart-footer ui-footer ui-bar-a ui-footer-fixed slideup'><span class='addmoreCart' onclick='goHomeEcom()';>Continue Shopping</span> <span class='checkoutCart' onclick='ecomContinueCheckout()'> Checkout </span></div>");
					$.mobile.changePage('#page5', { transition: 'slide',allowSamePageTransition: true});
                   getCartList();
                   
                   console.log('yessss');
                   if(sessionStorage.getItem("ecomflag")=="1")
                   {
                   $("#cart-MSG1").slideToggle();
                   
                   setTimeout( function(){
                              $("#cart-MSG1").slideToggle();
                              }
                              , 3000 );
                   
                   sessionStorage.setItem("ecomflag","0");
                   }
                   if(window.localStorage.getItem("fooduserid"))
                   {
                   var login=document.getElementById("login").style.display="none";
                   }
                   else
                   {
                   var acc=document.getElementById("signup").style.display="none";
                   document.getElementById("logout").style.display="none";
                   }
                   
                   },1000);
        
    }
    
}



function getCartList(page)
{
    //alert(page);
    /*
     if(page!="countonly")
     {
     getTotalCartPrice();
     }
     */
    //showIndicator();
    var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
	var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>mobile_cart.cartlist</resourcePath><args>'+sessionStorage.getItem('quote_id')+'</args></call></soap:Body></soap:Envelope>';
    
    /*
     var soapRequest =
     '<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://www.integritysupply.com/api/soap#call/\"><sessionId>'+localStorage.getItem('token')+'</sessionId><resourcePath>mobile_cart.cartlist</resourcePath><args>'+sessionStorage.getItem('quote_id')+'</args></call></soap:Body></soap:Envelope>';
     */
    console.log("my cart list request soap request="+soapRequest);
    $.ajax({
           type:"POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType:"text",
           data:soapRequest,
           success:function(data,status,req)
           {
           console.log("my cart list response " + req.responseText);
           if($(req.responseText).find("faultstring").length>0)
           {
           var message='Sorry ! '+$(req.responseText).find("faultstring").text();
           alertMessage(""+message);
           if($(req.responseText).find("faultstring").text().indexOf("Session expired. Try to relogin.")!=-1)
           {
           localStorage.clear();
           // window.location.href="home.html";
           }
           //  $(".loading").hide();
           }
           else
           {
           if($(req.responseText).find("item").find("item").length<=0)
           {
           localStorage.setItem('cart_number',0);
           if(page=="countonly")
           {
           //history.back();
           // redirectedPageAfterLogin();
           }
           else
           {
           
           html='<center><b>There is no Item in the cart</b><br/></center>';
           html+='<div class="cart-update-btn" id="cart-update-btn2" style="text-align:center"> <a class="food-mobile-btn" onclick=" ecomHomePageMagento();">Continue Shopping</a> </div></div>';
           console.log("The value of magentocheckoutFlag is set to 0 in bindcart ecom");
           
         //  sessionStorage.removeItem("ecomcouponDiscount");
     //      sessionStorage.removeItem("ecomcouponDiscountFlag");
       //    sessionStorage.removeItem("ecomdiscountCouponType");
           
           
           
           sessionStorage.setItem("magentocheckoutFlag",0);
           $("#user_tab1").empty();
           $("#user_tab1").append(html);
           $("#gtotal").empty();
           $("#tax").empty();
           $("#subtotal").empty();
           $("#discount").empty();
           $("#delivery").empty();
           document.getElementById("user_tab3").style.display="none";
           document.getElementById("editoncart1").style.display="none";
           console.log("In bind cart function ecom else condition")
           $("#cart_message").removeClass('cart_success');
           $("#cart_message").addClass('cart_error');
           document.getElementById("cart_message").innerHTML='No Products Added';
           $("#cart-MSG1").slideToggle();
           setTimeout( function(){
                      $("#cart-MSG1").slideToggle();
                      
                      $("#cart_message").removeClass('cart_error');
                      $("#cart-MSG1").addClass('cart_success');
                      document.getElementById("cart_message").innerHTML='Product successfully Added In Your Cart';
                      
                      
                      },2500);
           }
           /*
            if(page!="countonly")
            {
            alertMessage("You have no items in your shopping cart.");
            
            if(window.location.href.indexOf("cart")!=-1)
            {
            var Backlen=history.length;
            if (Backlen > 0)
            {history.go(-Backlen);}
            window.location.href="home.html";
            }
            
            }
            else
            {
            history.back();
            }
            */
           //  $(".loading").hide();
           }
           else
           {
           
           var nameArray=new Array();
           var idArray=new Array();
           var priceArray=new Array();
           var subTotalArray=new Array();
           var qtyArray=new Array();
           var typeArray=new Array();
           var itemIdArray=new Array();
           var superAttributeIndexArray=new Array();
           var superAttributeIdArray=new Array();
           var productAttributeLabelArray=new Array();
           var productAttributeValueArray=new Array();
           var thumbnail=new Array();
           $(req.responseText).find("item").each(function()
                                                 {
                                                 if($(this).find("key").text()=="items_qty")
                                                 {
                                                 localStorage.setItem("cart_number",""+parseInt($(this).find("value").text()));
                                                 }
                                                 });
           
           $(req.responseText).find("item").find("item").each(function()
                                                              {
                                                              //console.log($(this).find("key").text()+"="+$(this).find("value").text());
                                                              if($(this).find("key").text() == "product_id")
                                                              {
                                                              idArray.push($(this).find("value").text());
                                                              
                                                              }
                                                              else if($(this).find("key").text()=="name")
                                                              {
                                                              console.log("");
                                                              nameArray.push($(this).find("value").text());
                                                              }
                                                              else if($(this).find("key").text()=="qty")
                                                              {
                                                              qtyArray.push($(this).find("value").text());
                                                              }
                                                              else if($(this).find("key").text()=="price")
                                                              {
                                                              priceArray.push($(this).find("value").text());
                                                              }
                                                              else if($(this).find("key").text()=="subtotal")
                                                              {
                                                              subTotalArray.push($(this).find("value").text());
                                                              }
                                                              else if($(this).find("key").text()=="type")
                                                              {
                                                              typeArray.push($(this).find("value").text());
                                                              }
                                                              else if($(this).find("key").text()=="item_id")
                                                              {
                                                              itemIdArray.push($(this).find("value").text());
                                                              }
                                                              
                                                              else if($(this).find("key").text()=="thumbnail")
                                                              {
                                                              thumbnail.push($(this).find("value").text());
                                                              }
                                                              
                                                              else if($(this).find("key").text().indexOf("product_attribue")!=-1)
                                                              {
                                                              if($(this).find("item").find("key").text()!="")
                                                              {
                                                              productAttributeLabelArray.push($(this).find("item").find("key").text());
                                                              productAttributeValueArray.push($(this).find("item").find("value").text());
                                                              }
                                                              else
                                                              {
                                                              productAttributeLabelArray.push("");
                                                              productAttributeValueArray.push("");
                                                              }
                                                              }
                                                              else if($(this).find("key").text().indexOf("super_attribute")!=-1)
                                                              {
                                                              //console.log("krishna="+$(this).find("item").find("value").text());
                                                              if($(this).find("item").find("key").text()!="")
                                                              {
                                                              superAttributeIndexArray.push($(this).find("item").find("key").text());
                                                              superAttributeIdArray.push($(this).find("item").find("value").text());
                                                              }
                                                              else
                                                              {
                                                              superAttributeIndexArray.push("");
                                                              superAttributeIdArray.push("");
                                                              }
                                                              }
                                                              });
           
           /*
            sessionStorage.setItem('cart_product_name',JSON.stringify(nameArray));
            sessionStorage.setItem('cart_product_id',JSON.stringify(idArray));
            sessionStorage.setItem('cart_product_price',JSON.stringify(priceArray));
            sessionStorage.setItem('cart_product_subprice',JSON.stringify(subTotalArray));
            sessionStorage.setItem('cart_product_qty',JSON.stringify(qtyArray));
            sessionStorage.setItem('cart_product_type',JSON.stringify(typeArray));
            sessionStorage.setItem('cart_product_itemId',JSON.stringify(itemIdArray));
            sessionStorage.setItem('cart_product_superAttributeIndex',JSON.stringify(superAttributeIndexArray));
            sessionStorage.setItem('cart_product_superAttributeId',JSON.stringify(superAttributeIdArray));
            sessionStorage.setItem('cart_product_productAttributeLabel',JSON.stringify(productAttributeLabelArray));
            sessionStorage.setItem('cart_product_productAttributeValue',JSON.stringify(productAttributeValueArray));
            
            */
           console.log("nameArray.length---->>"+nameArray.length);
           var html='';
           var counter='';
           var newsubtotal=0;
           var subtotal=0;
           cartItemsArray.length=0;
           sessionStorage.setItem("magentocurrency",'$');
           
           if(parseFloat(nameArray.length) > 0)
           {
           for (var x=0; x<nameArray.length; x++)
           {
           
           console.log('status===>'+x);
           
           
           var productId=idArray[x];
           var qty=qtyArray[x];
           console.log("1");
           var productName=nameArray[x];
           console.log("2");
           var price=priceArray[x];
           console.log("3");
           var productImage=thumbnail[x];
           console.log("4");
           //  var status=window.sessionStorage.getItem("ecomstatus"+x);
           console.log("4");
           var productDesc =nameArray[x];
           console.log("5");
           //var foodType=window.sessionStorage.getItem("ecomType"+x);
           console.log("6");
           var foodcurrency='$';
           
           
           console.log("qty-->"+qty+"productId--->"+productId+"productName--->>"+productName+"price-->"+price+"productImage--->>"+productImage+"status--->>"+status+"productDesc--->"+productDesc+"ecomcurrency--->"+foodcurrency);
           
            cartItemsArray.push(productId);
           //console.log('status===>xyz'+status);
           productDesc=productDesc.substring(0,35);
           
           // console.log("ecom bind cart function ecom discount coupon--->>>"+sessionStorage.getItem("ecomcouponDiscount"));
           
           
           newsubtotal=parseFloat(newsubtotal)+(parseFloat(price)*parseInt(qty));
           subtotal=parseFloat(subtotal)+(parseFloat(price)*parseInt(qty));
           
           
           
           
           html+='<ul class="food_pro_list">';
           html+='<li><img style="display:none;" class="deleteButton" id="deleteButton'+productId+'" width="20px" onclick="deleteFromCartMagento(\''+itemIdArray[x]+'\')" src="resources/e-com/sliderminus.png"/><img class="productImageClass" src="'+productImage+'"  border="0" /><h2 class="proname">'+productName+'</h2><ol class="prodit"><li><span class="porqu" style="font-weight:bold;text-align:center;">\
           Qty</span><span class="porqu">Price</span><span class="tot">Total</span></li><li><span class="qut"><input type="number" onchange="onQuantityChange(\''+productId+'\')" value="'+parseInt(qty)+'" id="qty'+productId+'" class="qty" min="1" style="border:none;" readonly></span><span class="porqu">'+foodcurrency+formatCurrency((parseFloat(price)),1)+'</span><span class="tot teObxrt">'+foodcurrency+formatCurrency((parseFloat(price)*parseInt(qty)),1)+'</span></li></ol><div id="sliderdiv'+productId+'" style="display:none;"><span><img width="20px" onclick="sliderMinusButton(\''+productId+'\')" src="resources/e-com/sliderminus.png"/></span><input type=range min=0 max=100 value="'+parseInt(qty)+'" id="slider'+productId+'" step=1 onchange="outputUpdate(value,\''+productId+'\')"/><span><img width="20px" onclick="sliderPlusButton(\''+productId+'\')" src="resources/e-com/sliderplus.png"/></span></div></li>';
           
           html+='</ul>';
           counter=counter+1;
           
           }
           
           getMagentoDeliveryCharges();
           setTimeout( function(){
                      getTotalCartPrice();
                      },250);
           
           sessionStorage.setItem("magentocheckoutFlag",1);
           console.log("----->"+html);
           
           
           console.log("subtotal bind cart function --->>>"+subtotal);
           
           console.log("subtotal bind cart function --->>>"+newsubtotal);
           $("#user_tab1").empty();
           // sessionStorage.setItem("couponDiscount",gtotal);
           
           $("#user_tab1").append(html);
            document.getElementById("user_tab3").style.display="block";
           
           }
          
           }
           
           
           }
           
           },
           error: function(data,status,req)
           {
           alertMessage("Network connection problem!");
           //  $(".loading").hide();
           }
           });
    
}

function getTotalCartPrice()
{
    var priceTitle=new Array();
    var priceValue=new Array();
    sessionStorage.setItem('cart_Total_Price_Title',JSON.stringify(priceTitle));
    sessionStorage.setItem('cart_Total_Price_Value',JSON.stringify(priceValue));
    var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>cart.totals</resourcePath><args>'+sessionStorage.getItem('quote_id')+'</args></call></soap:Body></soap:Envelope>';
    
    //   showIndicator();
    //  var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://www.integritysupply.com/api/soap#call/\"><sessionId>'+localStorage.getItem('token')+'</sessionId><resourcePath>cart.totals</resourcePath><args>'+localStorage.getItem('quote_id')+'</args></call></soap:Body></soap:Envelope>';
    //console.log("total cart price soap request="+soapRequest);
    $.ajax({
           type:"POST",
           url: wsUrl,
           contentType: "text/json",
           dataType:"text",
           data:soapRequest,
           success:function(data,status,req)
           {
           console.log("total cart price response="+req.responseText);
           $(req.responseText).find("item").find("item").find("value").each(function()
                                                                            {
                                                                            if($(this).text().indexOf("Subtotal")!=-1 || $(this).text().indexOf("Discount")!=-1 || $(this).text().indexOf("Shipping & Handling")!=-1 || $(this).text().indexOf("Grand Total")!=-1)
                                                                            {
                                                                            priceTitle.push($(this).text());
                                                                            }
                                                                            else
                                                                            {
                                                                            priceValue.push($(this).text());
                                                                            }
                                                                            });
           document.getElementById("discountcharges").style.display="none";
           document.getElementById("taxcharges").style.display="none";
           document.getElementById("deliverycharges").style.display="none";
           sessionStorage.setItem("magentodiscount",0);
           sessionStorage.setItem("magentotax",0);
           sessionStorage.setItem("magentodelivery",0);
           
           for(var i=0; i<priceTitle.length; i++)
           {
           console.log("title ---->>>"+priceTitle[i]+"<<<----price value---->>>"+priceValue[i]);
           
           if(priceTitle[i].indexOf("Discount")!=-1)
           {
           document.getElementById("discountcharges").style.display="block";
            $("#discount").empty();
            $("#discount").append(sessionStorage.getItem("magentocurrency")+formatCurrency(priceValue[i],1));
            sessionStorage.setItem("magentodiscount",formatCurrency(priceValue[i],1));
           }
           if(priceTitle[i] == "Shipping & Handling (Flat Rate - Fixed)")
           {
           document.getElementById("deliverycharges").style.display="block";
           sessionStorage.setItem("magentodelivery",formatCurrency(priceValue[i],1));
           $("#delivery").empty();
           $("#delivery").append(sessionStorage.getItem("magentocurrency")+formatCurrency(priceValue[i],1));
           }
           if(priceTitle[i] == "TAX")
           {
           document.getElementById("taxcharges").style.display="block";
           sessionStorage.setItem("magentotax",priceValue[i]);
           }
           if(priceTitle[i] == "Grand Total")
           {
           //document.getElementById("taxcharges").style.display="block";
           $("#gtotal").empty();
           $("#gtotal").append((sessionStorage.getItem("magentocurrency")+priceValue[i]));
           sessionStorage.setItem("magentogrosstotal",priceValue[i]);
           }
           
           if(priceTitle[i] == "Subtotal")
           {
           //document.getElementById("taxcharges").style.display="block";
           $("#subtotal").empty();
           $("#subtotal").append(sessionStorage.getItem("magentocurrency")+priceValue[i]);
         
           sessionStorage.setItem("magentosubtotal",priceValue[i]);
           }
           
           }
           sessionStorage.setItem('cart_Total_Price_Title',JSON.stringify(priceTitle));
           sessionStorage.setItem('cart_Total_Price_Value',JSON.stringify(priceValue));
           },
           error: function(data,status,req)
           {
           //console.log("total cart price response="+req.responseText);
           $(".loading").hide();
           }
           });
}

function showeditOnCartmagento()
{
    
    console.log("showeditOnCartmagento function"+document.getElementById("editoncart1").innerHTML);
    
    for(var i=0;i <cartItemsArray.length;i++){
        console.log("In cart items array,showeditOnCartmagento function value of x-->"+i+"array length-->"+cartItemsArray.length+"array value-->"+cartItemsArray[i]);
        $("#deleteButton"+cartItemsArray[i]).slideToggle();
        $("#sliderdiv"+cartItemsArray[i]).slideToggle();
        $("#qty"+cartItemsArray[i]).removeAttr("readonly");
        $("#qty"+cartItemsArray[i]).addClass("bordered");
    }
    if(document.getElementById("editoncart1").innerHTML=="Done") {
        
        console.log("in if condition showeditOnCartmagento function");
        hideeditOnCartMagento();
        console.log("The value of magentocheckoutFlag is set to 1 in showeditOnCartmagento");
        sessionStorage.setItem("magentocheckoutFlag",1);
        
        
    }
    else {
        
        console.log("in else condition showeditOnCart function");
        console.log("The value of magentocheckoutFlag is set to 0 in hideeditOnCartMagento");
        sessionStorage.setItem("magentocheckoutFlag",0);
        document.getElementById("editoncart1").innerHTML="Done";
    }
    
}
function hideeditOnCartMagento()
{
    setTimeout(function(){
               console.log("hideeditOnCart function");
               
               document.getElementById("editoncart1").innerHTML="Edit";
               var ulHtml=document.getElementsByClassName('food_pro_list')[0].innerHTML;
               var inputs = document.getElementsByTagName("input");
               var jsonData=new String("");
               
               
               for (var i = 0, len = inputs.length; i < len; i++) {
               if (inputs[i].id.indexOf("qty") == 0) {
               for(var p=0; p <= parseInt(cartItemsArray.length); p++)
               {
               var idp=inputs[i].id.replace("qty","");
               if(idp==cartItemsArray[p])
               {
              jsonData+="{\"quoteId\":"+sessionStorage.getItem('quote_id')+",\"product_id\":"+cartItemsArray[p]+",\"qty\":"+parseInt(inputs[i].value)+"},";
               
               }
               }
               }
               }
               jsonData=jsonData.substring(0,jsonData.lastIndexOf(","));
               jsonData=jsonData.slice(0,jsonData.length);
               jsonData="["+jsonData+"]";
              
               jsonData = JSON.parse(jsonData);
               
               
               var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
               var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>mobile_cart.update</resourcePath><args>'+JSON.stringify(jsonData)+'</args></call></soap:Body></soap:Envelope>';
   
               // console.log("update cart soap request="+soapRequest);
               
               
               console.log("hideeditOnCartMagento---->>>soapRequest----->>>"+soapRequest);
               $.ajax({
                      type:"POST",
                      url: wsUrl,
                      contentType: "text/xml",
                      dataType:"text",
                      data:soapRequest,
                      success:function(data,status,req)
                      {
                      //console.log("update cart soap response="+req.responseText);
                      getCartList();
                      },
                      error: function(data,status,req)
                      {
                       console.log("update cart soap response="+req.responseText);
                      
                      }
                      });

               },1000);
    
    
}




function bindeditCartMagento(){
    getCartList();
    showeditOnCartmagento();
    if(window.sessionStorage.getItem("magentocart"))
    {
        document.getElementById("editoncart1").innerHTML="Edit";
        showeditOnCartmagento();
    }
    
}



function deleteFromCartMagento(itemId)
{
    var deleteData="";
    var deleteData=[{
                    "quoteId":sessionStorage.getItem('quote_id'),
                    "itemId":itemId,
                    "store":storeId
                    }];
    
    console.log("deleteFromCartMagento------>>>>"+deleteData);
    
    
    var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
	var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>mobile_cart.remove</resourcePath><args>'+JSON.stringify(deleteData)+'</args></call></soap:Body></soap:Envelope>';
    
   // var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://www.integritysupply.com/api/soap#call/\"><sessionId>'+localStorage.getItem('token')+'</sessionId><resourcePath>mobile_cart.remove</resourcePath><args>'+JSON.stringify(deleteData)+'</args></call></soap:Body></soap:Envelope>';
    
    
      console.log("deleteFromCartMagento------>>>>"+soapRequest);
    
    $.ajax({
           type:"POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType:"text",
           data:soapRequest,
           success:function(data,status,req)
           {
           console.log("delete cart product response="+req.responseText);
         //  $(".loading").hide();
           getCartList();
           
            setTimeout(function(){
           for(var i=0;i <cartItemsArray.length;i++){
           console.log("In cart items array,showeditOnCartmagento function value of x-->"+i+"array length-->"+cartItemsArray.length+"array value-->"+cartItemsArray[i]);
           $("#deleteButton"+cartItemsArray[i]).slideToggle();
           $("#sliderdiv"+cartItemsArray[i]).slideToggle();
           $("#qty"+cartItemsArray[i]).removeAttr("readonly");
           $("#qty"+cartItemsArray[i]).addClass("bordered");
           }
          
                      },1000);
           //bindeditCartMagento();
           /*
            $(req.responseText).find("item").find("item").each(function()
            {
            });
            */
           },
           error: function(data,status,req)
           {
         //  $(".loading").hide();
           }
           });
    
}

function formatCurrency(num,decimal)
{
    num = num.toString().replace(/\$|\,/g,'');
    if(isNaN(num))
        num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*100+0.50000000001);
    cents = num%100;
    num = Math.floor(num/100).toString();
    if(cents<10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
        num = num.substring(0,num.length-(4*i+3))+','+
        num.substring(num.length-(4*i+3));
    if ( decimal == 1) {
        return (((sign)?'':'-')  + num + '.' + cents);
    } else {
        return (((sign)?'':'-') + num);
    }
}

function getecomDiscount(TotalAmount)
{
	if(!checkNetworkConnection())
    {
    }
    else
    {
        
        
        window.sessionStorage.setItem("ecomdiscount",0);
        wsUrl = "http://"+resellerInitial+reseller+"/api/soap#ecommDiscount";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommDiscount xmlns=\"http://'+resellerInitial+reseller+'/api/soap#ecommDiscount\"><appId>'+appId+'</appId><gTotal>'+TotalAmount+'</gTotal></ecommDiscount></soap:Body></soap:Envelope>';
        console.log('request=>'+soapRequest);
        //jQuery.support.cors = true;
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               cache: false,
               dataType: "text",
               async: false,
               data: soapRequest,
               success: function(data, status, req)
               {
               // var xmlret=StringToXML(req.responseText);
               console.log('response=>'+req.responseText);
               var strJSON = $(req.responseText).find("return").text();
               console.log('getfoodDiscount==>'+strJSON);
               if(strJSON!='No Discount')
               {
               
               console.log('=x=x=x=x='+strJSON);
               $.ajax({
                      url: strJSON,
                      dataType: "text",
                      crossDomain: true,
                      async: false,
                      cache: false,
                      success: function(xmldata) {
                      
                      console.log(xmldata);
                      var xml=StringToXML(xmldata)
                      $(xml).find("discount").each(function () {
                                                   console.log('yesssssss');
                                                   var grossTotal= $(this).find("grossTotal").text();
                                                   var discountPrice= $(this).find("discountPrice").text();
                                                   var discountRate= $(this).find("discountRate").text();
                                                   
                                                   console.log('discountRate===>'+ discountRate +'      discountPrice=====>'+discountPrice);
                                                   
                                                   if(discountRate=='Percentage')
                                                   {
                                                   amt=(grossTotal*discountPrice)/100;
                                                   }
                                                   else
                                                   {
                                                   amt=discountPrice;
                                                   }
                                                   amt=formatCurrency(amt,1);
                                                   sessionStorage.setItem("ecomdiscount",amt);
                                                   
                                                   });
                      },
                      error: function(XMLHttpRequest, textStatus, errorThrown) {
                      
                      console.log('fail');
                      console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
                      }
                      });
               }
               console.log('ecomdiscount=>'+window.sessionStorage.getItem("fooddiscount"));
               
               },
               error: function(response, textStatus, errorThrown)
               {
               
               console.log(response);
               }
               });
    }
    
}

function getecomtax(TotalAmount)
{
    if(!checkNetworkConnection())
    {
    }
    else
    {
        window.sessionStorage.setItem("ecomtax",0);
        wsUrl = "http://"+resellerInitial+reseller+"/api/soap#ecommTax";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommTax xmlns=\"http://'+resellerInitial+reseller+'/api/soap#ecommTax\"><appId>'+appId+'</appId><gTotal>'+TotalAmount+'</gTotal></ecommTax></soap:Body></soap:Envelope>';
        console.log('request=>'+soapRequest);
        //jQuery.support.cors = true;
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               cache: false,
               dataType: "text",
               async: false,
               data: soapRequest,
               success: function(data, status, req)
               {
               //var xmlret=StringToXML(req.responseText);
               console.log('response=>'+req.responseText);
               var strJSON = $(req.responseText).find("return").text();
               console.log('getfoodTax==>'+strJSON);
               if(strJSON!='Tax Free')
               {
               
               console.log('=x=x=x=x='+strJSON);
               $.ajax({
                      url: strJSON,
                      dataType: "text",
                      crossDomain: true,
                      async: false,
                      cache: false,
                      success: function(xmldata) {
                      
                      console.log(xmldata);
                      var xml=StringToXML(xmldata)
                      $(xml).find("discount").each(function () {
                                                   console.log('yesssssss');
                                                   var grossTotal= $(this).find("grossTotal").text();
                                                   var taxPrice= $(this).find("taxPrice").text();
                                                   var taxRate= $(this).find("taxRate").text();
                                                   
                                                   console.log('discountRate===>'+ taxRate +'      discountPrice=====>'+taxPrice);
                                                   
                                                   if(taxRate=='Percentage')
                                                   {
                                                   amt=(grossTotal*taxPrice)/100;
                                                   }
                                                   else
                                                   {
                                                   amt=taxPrice;
                                                   }
                                                   amt=formatCurrency(amt,1);
                                                   sessionStorage.setItem("ecomtax",amt);
                                                   
                                                   });
                      },
                      error: function(XMLHttpRequest, textStatus, errorThrown) {
                      console.log('fail');
                      console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
                      }
                      });
               }
               console.log('fooddiscount=>'+sessionStorage.getItem("ecomtax"));
               
               },
               error: function(response, textStatus, errorThrown)
               {
               
               console.log(response);
               }
               });
    }
    
}
function getMagentoDeliveryCharges()
{
    if(!checkNetworkConnection())
    {
    }
    else
    {
        //sessionStorage.setItem("magentodelivery",0);
        var shipingMethodJson={
            "quoteId":sessionStorage.getItem('quote_id'),
            "method":'flatrate_flatrate'
        };
        console.log("getMagentoDeliveryCharges()------->>>>"+shipingMethodJson);
        var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>mobile_cart_shipping.setshippingmethod</resourcePath><args>'+JSON.stringify(shipingMethodJson)+'</args></call></soap:Body></soap:Envelope>';
        
   //     var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://www.integritysupply.com/api/soap#call/\"><sessionId>'+localStorage.getItem('token')+'</sessionId><resourcePath>mobile_cart_shipping.setshippingmethod</resourcePath><args>'+JSON.stringify(shipingMethodJson)+'</args></call></soap:Body></soap:Envelope>';
        //console.log("set shipping Method soap request="+soapRequest);
        $.ajax({
               type:"POST",
               url: wsUrl,
               contentType: "text/xml",
               dataType:"text",
               data:soapRequest,
               success:function(data,status,req)
               {
               console.log("set shipping Method response="+req.responseText);
            // sessionStorage.setItem("magentodelivery",0);
               },
               error: function(data,status,req)
               {
               //$(".loading").hide();
               }
               });

}
}

console.log("In coupon page js");



function coupon_Magento()
{
    if(!checkNetworkConnection())
    {
    }
    else
    {
        console.log("coupon_Magento function");
        var navigationType = localStorage.getItem("navigation_Layout");
        
        
        clsvalue = 'backToDashbord';
        console.log("coupon_Magento magento function");
        var couponEcomHtml='<div class="product-top cart-header">\
        <h3 style="color:#000" id="product_pageHeader">Coupon</h3>\
        </div><section id="container" class="cart-container"><div class="food-ordering-product"><div class="cart-MSG" id="cart-MSG1-coupon" style="display:none">\
        <span class="er_close">x</span>\
        <div class="cart_success" id="cart_success">Coupon Applied</div>\
        </div><div class="food-pro_details couponDetail"><h4>Enter Coupon Code</h4><input type="text" id="couponCode" /><a class="submit_btn couponButton" onclick="getMagentoDiscountCoupon();" >Apply</a></div><div class="user_tab" id="user_tab3">\
        <h4>PAYMENT DETAILS</h4>\
        <ul class="pay-mobile-cart">\
        <li >\
        Subtotal <span id="subtotal"></span>\
        </li>\
        <li id="deliverycharges">\
        Delivery Charge <span id="delivery"></span>\
        </li>\
        \
        <li id="taxcharges">\
        Tax <span  id="tax"></span>\
        </li>\
        \
        <li id="discountcharges">\
        Discount <span id="discount"></span>\
        </li>\
        <li id="couponDiv" style="display:none;">Coupon <span id="coupon"></span></li>\
        <li>\
        Grand Total<span id="gtotal"></span>\
        </li>\
        </ul></div></div>\</section><ul class="sub-menu" style="display:none;" >\
        <li><a onclick="HomeEcom()" class="home-nav">Home</a></li>\
        <li id="login"><a onclick="loginEcommerceMagento()" class="login-nav">Login / Registration</a></li>\
        <li id="signup"><a onclick="MyaccountMagento()" class="account-nav">My Account</a></li>\
        <li><a onclick="ecomHomePageMagento()" class="account-nav">Main Menu</a></li>\
        <li id="cartView"><a onclick="cart_viewMagento()" class="cart-nav">Cart</a></li>\
        <li><a onclick="termsEcomMagento()" class="terms-nav">Terms & Conditions</a></li>\
        <li><a onclick="PolicyEcomMagento()" class="policy-nav">Privacy Policy</a></li>\
        <li id="logout"><a onclick="logoutMagento()" class="policy-nav">Log out</a></li>\
        </ul>';
        
        console.log("1" + couponEcomHtml);
        
        Ext.define('appypie.view.couponEcom', {
                   
                   extend: 'Ext.Container',
                   requires:['Ext.TitleBar','Ext.dataview.List'],
                   alias: 'widget.couponEcom',
                   id: 'couponEcom',
                   config: {
                   layout: {
                   type: 'vbox'
                   },
                   scrollable: {
                   direction: 'vertical',
                   cls:'page-content',
                   directionLock: false
                   },
                   items: [
                           {
                           xtype: 'titlebar',
                           docked: 'top',
                           height: 53,
                           title: '<h1>'+appName+'</h1>',
                           id:'header',
                           items: [ {
                                   xtype: 'button',
                                   align: 'center',
                                   height: 45,
                                   width: 45,
                                   style:'border-radius:0; border:0;',
                                   id:'subMenu',
                                   cls: 'subMenuButton',
                                   handler:function() {
                                   $(".sub-menu").slideToggle();
                                   }
                                   },
                                   {
                                   xtype: 'button',
                                   align: 'right',
                                   height: 40,
                                   width: 75,
                                   style:'border-radius:0; border:0;',
                                   id:'cart',
                                   cls: 'cartButton',
                                   handler:function() {
                                   //Ext.Viewport.removeAll();
                                   cart_viewMagento();
                                   //foodorderingMainPage();
                                   }
                                   }
                                   ]
                           },
                           {
                           
                           html: [couponEcomHtml]
                           
                           },
                           {
                           html: ['<div><br><br><br><br><br><br><br></div>'],
                           }
                           ]
                   }
                   });
        
        
        var couponEcom={
        xtype: 'couponEcom'
            
        };
        Ext.Viewport.removeAll();
        Ext.Viewport.animateActiveItem(couponEcom, { type: 'slide', direction: 'left' });
        console.log("in end function 1");
        //showCartValueFood();
        
        if(localStorage.getItem("fooduserid"))
        {
            var login=document.getElementById("login").style.display="none";
        }
        else
        {
            var acc=document.getElementById("signup").style.display="none";
        }
        
        console.log("in end function 2");
        
        
        
        console.log("magentodelivery"+sessionStorage.getItem("magentodelivery")+"magentodiscount"+sessionStorage.getItem("magentodiscount")+"magentotax"+sessionStorage.getItem("magentotax"));
        if(window.sessionStorage.getItem("magentodelivery") == 0)
        {
            document.getElementById("deliverycharges").style.display="none";
        }
        if(window.sessionStorage.getItem("magentodiscount") == 0)
        {
            document.getElementById("discountcharges").style.display="none";
        }
        if(window.sessionStorage.getItem("magentotax") == 0)
        {
            document.getElementById("taxcharges").style.display="none";
        }
        
        $("#delivery").empty();
        $("#delivery").append(sessionStorage.getItem("magentocurrency")+sessionStorage.getItem("magentodelivery"));
        $("#discount").empty();
        $("#discount").append(sessionStorage.getItem("magentocurrency")+sessionStorage.getItem("magentodiscount"));
        $("#subtotal").empty();
        $("#subtotal").append(sessionStorage.getItem("magentocurrency")+sessionStorage.getItem("magentosubtotal"));
        $("#tax").empty();
        $("#tax").append(sessionStorage.getItem("magentocurrency")+window.sessionStorage.getItem("magentotax"));
        console.log("in end function 3");
        $("#gtotal").empty();
        $("#gtotal").append(sessionStorage.getItem("magentocurrency")+sessionStorage.getItem("magentogrosstotal"));
    }
}

function getMagentoDiscountCoupon()
{
    var couponCode=document.getElementById("couponCode").value;
    if(couponCode == null || couponCode == '')
    {
    	alertPopUp('Error','Please enter the coupon code');
        
    }
    else
    {
        
        
        
        
        var array_simple_Product={
            "quoteId":sessionStorage.getItem('quote_id'),
            "couponCode":couponCode
            };
      //  array_simple_Product=;
        
        var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>mobile_cart.addcoupon</resourcePath><args>'+JSON.stringify(array_simple_Product)+'</args></call></soap:Body></soap:Envelope>';
        
        
        console.log("getMagentoDiscountCoupon---->soapRequest------>>>"+soapRequest);
        
      //ar soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://www.integritysupply.com/api/soap#call/\"><sessionId>'+localStorage.getItem('token')+'</sessionId><resourcePath>mobile_cart.addcoupon</resourcePath><args>'+JSON.stringify(array_simple_Product)+'</args></call></soap:Body></soap:Envelope>';
        //console.log("apply coupan soap request="+soapRequest);
        $.ajax({
               type:"POST",
               url: wsUrl,
               contentType: "text/json",
               dataType:"text",
               data:soapRequest,
               success:function(data,status,req)
               {
               console.log("apply coupan response="+req.responseText);
               if($(req.responseText).find("faultstring").length>0)
               {
               var message=""+$(req.responseText).find("faultstring").text();
               alertMessage(""+message);
               }
               else
               {
               if($(req.responseText).find("callReturn").text()!="true")
               {
               alertMessage("This coupan code is not  available.");
               
               $("#cart_success").empty();
               $("#cart_success").removeClass('cart_success');
               $("#cart_success").addClass('cart_error');
               
               $("#cart_success").append("Not a valid coupon");
               
               
               $("#cart-MSG1-coupon").slideToggle();
               
               setTimeout( function(){
                          $("#cart-MSG1-coupon").slideToggle();
                          $("#cart_success").removeClass('cart_error');
                          $("#cart_success").addClass('cart_success');
                          document.getElementById("cart_success").innerHTML='Coupon Applied';
                          }
                          , 6000 );
               }
               else
               {
              // sessionStorage.setItem("discount_coupan_chk",""+coupanCodeValue);
              // document.getElementById('coupanTextValue').innerHTML="Remove Coupan";
               alertMessage("coupan applied successfully.");
               //document.getElementById('coupancodeBox').readOnly="true";
               $("#cart-MSG1-coupon").slideToggle();
               
               setTimeout( function(){
                          $("#cart-MSG1-coupon").slideToggle();
                          }
                          , 2000 );
               
               
               setTimeout( function(){
                          cart_viewMagento();
                          }
                          , 3000 );
               
               }
               }
           
               },
               error: function(data,status,req)
               {
               //console.log("apply coupan response="+req.responseText);
               alertMessage("Network connection problem.");
               
             //  $(".loading").hide();
               }
               });
           }
}


function checkout_viewMagento()
{
    
    if(!checkNetworkConnection())
    {
    }
    else
    {
        
    console.log("checkout function magento");
    var navigationType = localStorage.getItem("navigation_Layout");
    
    
   
    
    console.log("checkout magento function");
        var checkoutHtml='<section id="container" >\
        <div class="e-mobile-product">\
        <h2>Checkout Page</h2>\
        \
        \
        <div class="user_tab" id="user_tab1">\
        <h4>Billing Address</h4>\
        <div class="form-page"> \
        <strong>First Name<span class="required">*</span> </strong>\
        <input id="sfname" type="text" placeholder="First Name">\
        <strong>Last Name<span class="required">*</span> </strong>\
        <input id="slname" type="text" placeholder="Last Name">\
        <strong>  Telephone No<span class="required">*</span> </strong>\
        <input id="spNo"  type="text" placeholder="Telephone No" >\
        <strong>Email<span class="required">*</span></strong><input id="semail"  type="text" placeholder="Email" >\
        <strong> Street Address<span class="required">*</span> </strong>\
        <input id="ssAddress"  type="text" placeholder="Street Address" >\
        <strong> City<span class="required">*</span> </strong>\
        <input id="sCity"  type="text" placeholder="Enter Your City" >\
        <strong> State/Province<span class="required">*</span> </strong>\
        <input id="sState" type="text" placeholder="Enter State/Province" >\
        <strong> Zip/Postal Code<span class="required">*</span> </strong>\
        <input id="sZip"  type="text" placeholder="Enter Zip/Postal Code" >\
        <strong> Country<span class="required">*</span> </strong>\
        <div id="bcountryTry"></div>\
        \
        <input type="checkbox" id="show_billing_address"/>\
        <strong>Billing Address Different From Shipping Address </strong> </div>\
        </div>\
        <div class="user_tab" id="billing_address" style="display:none">\
        <h4>Shipping Address</h4>\
        <div class="form-page"> \
        <strong> First Name<span class="required">*</span> </strong> <strong>\
        <input type="text"  id="bfname" placeholder="First Name">\
        <strong>Last Name<span class="required">*</span> </strong>\
        <input id="blname" type="text" placeholder="Last Name">\
        <strong>Telephone No<span class="required">*</span> </strong>\
        <input type="text"  id="bpNo" placeholder="Telephone No">\
        <strong> Street Address<span class="required">*</span> </strong>\
        <input type="text"  id="bsAddress" placeholder="Street Address">\
        <strong> City<span class="required">*</span> </strong>\
        <input type="text"  id="bCity" placeholder="Enter Your City">\
        <strong> State/Province<span class="required">*</span> </strong>\
        <input type="text"  id="bState" placeholder="Enter State/Province">\
        <strong> Zip/Postal Code<span class="required">*</span> </strong>\
        <input type="text"  id="bZip" placeholder="Enter Zip/Postal Code">\
        <strong> Country<span class="required">*</span> </strong>\
        <div id="scountryTry"></div>\
        \
        </div>\
        </div>\
        <div class="user_tab">\
        <h4>Payment Details</h4>\
        <ul class="pay-mobile-cart">\
        <li > Subtotal <span id="subtotal"></span> </li>\
        <li id="deliverycharges"> Delivery Charge <span id="delivery"></span> </li>\
        <li id="taxcharges"> Tax <span  id="tax"></span> </li>\
        <li id="discountcharges"> Discount <span id="discount"></span> </li>\
        <li id="couponDiv" style="display:none;">Coupon <span id="coupon"></span></li>\
        \
        <li> Grand Total<span id="gtotal"></span> </li>\
        </ul>\
        <div class="cart-update-btn"> <a class="e-mobile-btn payment-mobile-btn" onclick="PayNowMagento();">Pay Now</a> </div>\
        </div>\
        </div>\
        </section><ul class="sub-menu" style="display:none;" >\
    <li><a onclick="HomeMagento()" class="home-nav">Home</a></li>\
    <li id="login"><a onclick="loginEcommerceMagento()" class="login-nav">Login / Registration</a></li>\
    <li id="signup"><a onclick="MyaccountMagento()" class="account-nav">My Account</a></li>\
    <li><a onclick="mainPageMagento()" class="account-nav">Main Menu</a></li>\
    <li id="cartView"><a onclick="cart_viewMagento()" class="cart-nav">Cart</a></li>\
    <li><a onclick="termsEcomMagento()" class="terms-nav">Terms & Conditions</a></li>\
    <li><a onclick="PolicyEcomMagento()" class="policy-nav">Privacy Policy</a></li>\
    <li id="logout"><a onclick="logoutMagento()" class="policy-nav">Log out</a></li>\
    </ul>';
    
    console.log("1" + checkoutHtml);
    
    Ext.define('appypie.view.checkout', {
               
               extend: 'Ext.Container',
               requires:['Ext.TitleBar','Ext.dataview.List'],
               alias: 'widget.checkout',
               id: 'checkout',
               config: {
               layout: {
               type: 'vbox'
               },
               scrollable: {
               direction: 'vertical',
               cls:'page-content',
               directionLock: false
               },
               items: [
                       {
                       xtype: 'titlebar',
                       docked: 'top',
                       height: 53,
                       title: '<h1>'+appName+'</h1>',
                       id:'header',
                       items: [ {
                               xtype: 'button',
                               align: 'center',
                               height: 45,
                               width: 45,
                               style:'border-radius:0; border:0;',
                               id:'subMenu',
                               cls: 'subMenuButton',
                               handler:function() {
                               $(".sub-menu").slideToggle();
                               }
                               },
                               {
                               xtype: 'button',
                               align: 'right',
                               height: 40,
                               width: 75,
                               style:'border-radius:0; border:0;',
                               id:'cart',
                               cls: 'cartButton',
                               handler:function() {
                                Ext.Viewport.removeAll();
                               cart_viewMagento();
                               }
                               }
                               ]
                       },
                       {
                       
                       html: [checkoutHtml]
                       
                       },
                       {
                       html: ['<div><br><br><br><br><br><br><br></div>'],
                       }
                       ]
               }
               });
    
    
    var checkout={
    xtype: 'checkout'
        
    };
    Ext.Viewport.removeAll();
    Ext.Viewport.animateActiveItem(checkout, { type: 'slide', direction: 'left' });
        loadCountryList();
    if(window.sessionStorage.getItem("magentodelivery") == 0)
    {
        document.getElementById("deliverycharges").style.display="none";
    }
    if(window.sessionStorage.getItem("magentodiscount") == 0)
    {
        document.getElementById("discountcharges").style.display="none";
    }
    if(window.sessionStorage.getItem("magentotax") == 0)
    {
        document.getElementById("taxcharges").style.display="none";
    }
    
    $("#delivery").append(sessionStorage.getItem("magentocurrency")+window.sessionStorage.getItem("magentodelivery"));
    $("#discount").append(sessionStorage.getItem("magentocurrency")+window.sessionStorage.getItem("magentodiscount"));
    $("#tax").append(sessionStorage.getItem("magentocurrency")+window.sessionStorage.getItem("magentotax"));
    $("#gtotal").append(sessionStorage.getItem("magentocurrency")+sessionStorage.getItem("magentogrosstotal"));
    $("#subtotal").append(sessionStorage.getItem("magentocurrency")+sessionStorage.getItem("magentosubtotal"));
    
    
    
   
        console.log("in else condition for account page hide checkout page"+localStorage.getItem("fooduserid"));
    
    if(localStorage.getItem("fooduserid") && localStorage.getItem("customer_id"))
    {
        document.getElementById("semail").value=localStorage.getItem("fooduserid");
        var login=document.getElementById("login").style.display="none";
        setTimeout( function(){
                   showBillingShippinginfoMagento();
                   
                   }
                   , 1000 );
      //  alert(localStorage.getItem("fooduserid"));
        $("#semail").attr("readonly","true");
    }
    else
    {
        document.getElementById("user_tab1").style.display="block";
        console.log("in else condition for account page hide checkout page");
        var acc=document.getElementById("signup").style.display="none";
        
    }
    
    
    }

    
    $(".sub-menu,.e-mobileOH_subtab, .e-mobileOD, #billing_address").hide();
    
    $(".e-mobileOH_tab h3").click(function(){
                                  $(this).parents(".e-mobileOH_tab").toggleClass("open_tab");
                                  $(this).parents(".e-mobileOH_tab").toggleClass("close_tab");
                                  $(this).parents(".e-mobileOH_tab").children("div").slideToggle();
                                  });
    
    $(".e-mobileOH_subtab h5").click(function(){
                                     $(this).toggleClass("open_tab");
                                     $(this).toggleClass("close_tab");
                                     $(this).parents(".e-mobileOH_subtab").children("span").slideToggle();
                                     });
    
    $("#show_billing_address").click(function(){
                                     $("#billing_address").slideToggle();
                                     });
    
}


function PayNowMagento()
{
	
    
    var sfname=document.getElementById("sfname").value;
	//var slanme=document.getElementById("slanme").value;
	//var semailId=document.getElementById("semailId").value;
    var semailId=document.getElementById("semail").value;
	var spNo=document.getElementById("spNo").value;
	var ssAddress=document.getElementById("ssAddress").value;
	var sCity=document.getElementById("sCity").value;
	var sState=document.getElementById("sState").value;
	var sZip=document.getElementById("sZip").value;
	var sCountry=document.getElementById("sCountry").value;
    
	var bfname=document.getElementById("bfname").value;
	//var blname=document.getElementById("blname").value;
	//var bemailId=document.getElementById("bemailId").value;
	var bpNo=document.getElementById("bpNo").value;
	var bsAddress=document.getElementById("bsAddress").value;
	var bCity=document.getElementById("bCity").value;
	var bState=document.getElementById("bState").value;
	var bZip=document.getElementById("bZip").value;
	var bCountry=document.getElementById("bCountry").value;
    
   var snewname=sfname.split(' ');
    var slname=snewname[1];
    if(slname == '' || slname == null )
	{
        slname='null';
    }
    var bnewname=bfname.split(' ');
    var blname=bnewname[1];
    if(blname == '' || slname == null )
	{
        blname='null';
    }
    
  
    
	$(".cart_error2").empty();
    
    
    
	if(sfname=='' || sfname=='First Name')
	{
		navigator.notification.alert('Please Enter First Name', setTimeout, "Error", "OK");
		$("#sfname").focus();
		return;
	}
    else if(!validateEmail(semailId) || semailId=='')
    {
    	alertPopUp('Error','Invalid Email Address...');
        
        $("#semailId").focus();
        return;
    }
    
	else if(spNo=='' || spNo=='Phone Number' )
	{
		alertPopUp('Error','Please Enter Valid Phone number');
		
		$("#spNo").focus();
		return;
	}
	else if(ssAddress=='' || ssAddress=='Street Address' )
	{
		alertPopUp('Error','Please Enter Street Address');
		
		$("#ssAddress").focus();
		return;
	}
	else if(sCity=='' || sCity=='Enter Your City' )
	{
		alertPopUp('Error','Please Enter Your City');
		
		$("#sCity").focus();
		return;
	}
	else if(sState=='' || sState=='Enter State/Province' )
	{
		alertPopUp('Error','Please Enter State/Province');

		
		$("#sState").focus();
		return;
	}
	else if(sZip=='' || sZip=='Enter Zip/Postal Code' )
	{
		alertPopUp('Error','Please Enter Zip/Postal Code');
		
		$("#sZip").focus();
		return;
	}
	else if(sCountry=='' || sCountry=='Enter Country' )
	{
		alertPopUp('Error','Please Enter Country');
		
		$("#sCountry").focus();
		return;
	}
	else if(document.getElementById("show_billing_address").checked)
	{
        
		if(bfname=='' || bfname=='First Name')
		{
			alertPopUp('Error','Please Enter First name');
			
			$("#sCountry").focus();
			return;
		}
		else if(bpNo=='' || bpNo=='Phone Number' )
		{
			alertPopUp('Error','Please Enter Valid Phone number');
			
			$("#bpNo").focus();
			return;
		}
		else if(bsAddress=='' || bsAddress=='Street Address' )
		{
			alertPopUp('Error','Please Enter Street Address');
			
			$("#bsAddress").focus();
			return;
		}
		else if(bCity=='' || bCity=='Enter Your City' )
		{
			alertPopUp('Error','Please Enter Your City');
			
			$("#bCity").focus();
			return;
		}
		else if(bState=='' || bState=='Enter State/Province' )
		{
			alertPopUp('Error','Please Enter State/Province');
			
			$("#bState").focus();
			return;
		}
		else if(bZip=='' || bZip=='Enter Zip/Postal Code' )
		{
			alertPopUp('Error','Please Enter Zip/Postal Code');
			
			$("#bZip").focus();
			return;
		}
		else if(bCountry=='' || bCountry=='Enter Country' )
		{
			alertPopUp('Error','Please Enter Country');
			
			$("#bCountry").focus();
			return;
		}
	}
    
    
    
    if(document.getElementById("show_billing_address").checked)
    {
        
        var customerAddress={"customerId":localStorage.getItem("customer_id"),"addressdata": {
            "firstname": sfname,
            "lastname":  slname,
            "street":  ssAddress,
            "city":  sCity,
            "region":sState,
            "postcode":  sZip,
            "country_id":  sCountry,
            "telephone": spNo,
            "is_default_shipping": "true",
            "is_default_billing": "false"
        }
        };
        
		        setCustomerAddress(customerAddress,"new");
                
        customerAddress={"customerId":localStorage.getItem("customer_id"),"addressdata": {
            "firstname": bfname,
            "lastname":  blname,
            "street":  bsAddress,
            "city":  bCity,
            "region":bState,
            "postcode":  bZip,
            "country_id": bCountry,
            "telephone": bpNo,
            "is_default_shipping": "true",
            "is_default_billing": "false"
        }
        };
        
         setCustomerAddress(customerAddress,"new");
    }
    else
    {
        // for set customer address
        var customerAddress={"customerId":localStorage.getItem("customer_id"),"addressdata": {
                "firstname": sfname,
                "lastname":  slname,
                "street":  ssAddress,
                "city":  sCity,
                "postcode":  sZip,
                "region":sState,
                "country_id":  sCountry,
                "telephone": spNo,
                "is_default_shipping": "true",
                "is_default_billing": "true"
            }
        };

        setCustomerAddress(customerAddress,"new");
    }
    
    
		addOrderMagento();
    
    
	//;
}


function addOrderMagento()
{
	
	
	var sfname=document.getElementById("sfname").value;
	var semailId=document.getElementById("semail").value;
    sessionStorage.setItem("OrderEmailId",semailId);
	var spNo=document.getElementById("spNo").value;
	var ssAddress=document.getElementById("ssAddress").value;
	var sCity=document.getElementById("sCity").value;
	var sState=document.getElementById("sState").value;
	var sZip=document.getElementById("sZip").value;
	var sCountry=document.getElementById("sCountry").value;
	var bfname=document.getElementById("bfname").value;
	var bpNo=document.getElementById("bpNo").value;
	var bsAddress=document.getElementById("bsAddress").value;
	var bCity=document.getElementById("bCity").value;
	var bState=document.getElementById("bState").value;
	var bZip=document.getElementById("bZip").value;
	var bCountry=document.getElementById("bCountry").value;
    
   

    
    
    console.log(' addOrdermagento  cart user====>'+window.sessionStorage.getItem("foodcartuser"));
    
    if(sessionStorage.getItem("ecomcart"))
    {
        cart=window.sessionStorage.getItem("ecomcart");
        
        console.log(' addOrderEcom -->Cart====>'+cart);
        var order='';
        html='';
        html+='[';
        order+='[';
        var counter=0;
        for (var x=1; x<=parseInt(cart); x++)
        {
            console.log("1 -->"+x);
            var qty=window.sessionStorage.getItem("ecomquantity"+x);
            console.log("2 -->"+x);
            var productId=window.sessionStorage.getItem("ecomproductId"+x);
            console.log("3-->"+x);
            var productName=window.sessionStorage.getItem("ecomproductName"+x);
            console.log("4-->"+productName);
            var price=sessionStorage.getItem("ecomprice"+x);
            console.log("5-->"+x);
            var status=sessionStorage.getItem("ecomstatus"+x);
            console.log("6-->"+x);
            
            
            
            if(status=="1")
            {
                if(counter>0)
                {
                    order+=',';
                    html+=',';
                }
                order+='{"name":"'+productName+'","qty":"'+qty+'","price":"'+price+'","sku":"'+productId+'"}'
                
                html+='{"productId":"'+productId+'","qty":"'+qty+'"}';
                counter=counter+1;
            }
        }
		console.log("1");
        order+=']'
        html+=']';
        
    }
    
    
    var billingAddress = '{"name":"'+sfname+'","email":"'+semailId+'","phone":"'+spNo+'","line1":"'+ssAddress+'","city":"'+sCity+'","countryCode":"IN","postalCode":"'+sZip+'","state":"'+sState+'"}';
    console.log("3");
    
    sessionStorage.setItem("LastNamePayment",sfname);
    console.log("billingAddress -->>>>"+billingAddress);
    
    console.log("order--->>>"+order);
    sessionStorage.setItem("billingAddressPayment",billingAddress);
    sessionStorage.setItem("OrderPayment",order);
    
    console.log("2");
    
    var billing='{"billShip":"Billing","name":"'+sfname+'","address":"'+ssAddress+'","city":"'+sCity+'","state":"'+sState+'","country":"'+sCountry+'","zip":"'+sZip+'","phone":"'+spNo+'"}';
    
	var shipping='';
	if(document.getElementById("show_billing_address").checked)
	{
		shipping='{"billShip":"Shipping","name":"'+bfname  +'","address":"'+bsAddress+'","city":"'+bCity+'","state":"'+bState+'","country":"'+bCountry+'","zip":"'+bZip+'","phone":"'+bpNo+'"}';
        
	}
	else
	{
		shipping='{"billShip":"Shipping","name":"'+sfname  +'","address":"'+ssAddress+'","city":"'+sCity+'","state":"'+sState+'","country":"'+sCountry+'","zip":"'+sZip+'","phone":"'+spNo+'"}';
	}
    
    console.log(" addOrderEcom ---> shipping--->>>"+shipping+"billing--->>>"+billing+"html--->>>"+html);
    
    sessionStorage.setItem("shippingAddOrder",shipping);
    sessionStorage.setItem("billingAddOrder",billing);
    
    sessionStorage.setItem("htmlAddOrder",html);
    console.log("4");
	console.log(html);
	console.log(billing);
	console.log(shipping);
    alert("Development is in progress ,Please try after some time");
   
   // Payment_ViewMagento();
	
    
	
    
}
var countryIdList=new Array();
var countryNameList=new Array();
function loadCountryList()
{
    countryNameList.length=0;
    countryIdList.length=0;
    var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
	var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>directory_country.list</resourcePath><args></args></call></soap:Body></soap:Envelope>';
    
       $.ajax({
           type:"POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType:"text",
           data:soapRequest,
           success:function(data,status,req)
           {
           
           //console.log("country list response="+req.responseText);
           $(req.responseText).find("item").find("item").each(function()
                                                              {
                                                              if($(this).find("key").text()=="country_id")
                                                              {
                                                              countryIdList.push($(this).find("value").text());
                                                              }
                                                              else if($(this).find("key").text()=="name")
                                                              {
                                                              
                                                              countryNameList.push($(this).find("value").text());
                                                              }
                                                              });
           
           var html1='<select id="sCountry"><option>Select</option>';
           var html2='<select id="bCountry"><option>Select</option>';
           var html='';
           console.log("html--->>"+html1);
           for(var i=0; i<=countryIdList.length; i++)
           {
                                       
                                       html+='<option value="'+countryIdList[i]+'">'+countryNameList[i]+'</option>';

           }
           $("#scountryTry").empty();
           $("#scountryTry").append(html1+html+'</select>');
           
           console.log(html1);
           $("#bcountryTry").empty();
           $("#bcountryTry").append(html2+html+'</select>');
           if(localStorage.getItem("fooduserid"))
           {
          // showBillingShippingedit();
           }
           
           
          
           },
           error: function(data,status,req)
           {
           
           }
           });
}
function getCountry()
{
    
    
    var wsUrl = "http://"+resellerInitial+reseller+"/services/app-user-soap#getCountryList";
    
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getCountryList xmlns=\"http://'+resellerInitial+reseller+'/services/app-user-soap#getCountryList\"></getCountryList></soap:Body></soap:Envelope>';
    
    
    console.log(soapRequest);
    
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(data).find("return").text();
           
           console.log("getCountry---->>>>"+strJSON+"data--->"+data);
           createCountryList(strJSON);
           
           },
           error: function(response, textStatus, errorThrown)
           {
           
           console.log(response);
           
           }
           });
}


function createCountryList(strJSON)
{
    
    console.log("createCountryList ---->>> strJSON"+strJSON);
    $.ajax({
           url: strJSON,
           dataType: "text",
           crossDomain: true,
           cache: false,
           success: function(xml) {
           
          
           
           },
           error: function(XMLHttpRequest, textStatus, errorThrown) {
           
           console.log('fail');
           console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
           }
           });
    
}


function loginEcommerceMagento()
{
    
    var navigationType = localStorage.getItem("navigation_Layout");
    
    
    clsvalue = 'backToDashbord';
    
    console.log("loginEcom function");
    var loginHtml='<section class="home_screen"><div class="page-text"><div id="scroll_contener" class="scroll_contener">\
    <div class="appypie-login"><h2>Sign In</h2><div class="cart-MSG" id="cart-MSG" style="display:none">\
    <div class="cart_error2">Invalid login id or password.</div></div>\
    <div class="login-feald"><label>Email ID</label><input type="text" id="loginid"/></div>\
    <div class="login-feald"><label>Password</label><input type="password" id="loginpass" /></div>\
    <div class="login-feald"><a class="login_btn" onclick="checkMagentoUserLogin()">Login</a> <a onclick="ecomForgotPassView()">Forgot your password?</a></div>\
    <div class="or-feald"><span>OR</span></div><div class="fb-div"><a href="#"><img src="images/e-com/facebook-connect.jpg" border="0"></a> <br clear="all" />Do not have an account? <a onclick="magentoSignUpView()">Sign up Now</a></div>\
    </div></div></div></section><ul class="sub-menu" style="display:none;" >\
    <li><a onclick="HomeEcom()" class="home-nav">Home</a></li>\
    \
    \
    <li><a onclick="mainPageMagento()" class="account-nav">Main Menu</a></li>\
    <li><a onclick="termsEcom()" class="terms-nav">Terms & Conditions</a></li>\
    <li><a onclick="PolicyEcom()" class="policy-nav">Privacy Policy</a></li>\
    </ul>';
    
    console.log("1" + loginHtml);
    
    Ext.define('appypie.view.EcomLogin', {
               
               extend: 'Ext.Container',
               requires:['Ext.TitleBar','Ext.dataview.List'],
               alias: 'widget.EcomLogin',
               id: 'EcomLogin',
               config: {
               layout: {
               type: 'vbox'
               },
               scrollable: {
               direction: 'vertical',
               cls:'page-content',
               directionLock: false
               },
               items: [
                       {
                       xtype: 'titlebar',
                       docked: 'top',
                       height: 53,
                       title: '<h1>'+appName+'</h1>',
                       id:'header',
                       items: [ {
                               xtype: 'button',
                               align: 'center',
                               height: 45,
                               width: 45,
                               style:'border-radius:0; border:0;',
                               id:'subMenu',
                               cls: 'subMenuButton',
                               handler:function() {
                               $(".sub-menu").slideToggle();
                               }
                               },
                               {
                               xtype: 'button',
                               align: 'right',
                               height: 40,
                               width: 75,
                               style:'border-radius:0; border:0;',
                               id:'cart',
                               cls: 'cartButton',
                               handler:function() {
                               Ext.Viewport.removeAll();
                               if(sessionStorage.getItem("magentoCartCheck"))
                               {
                               sessionStorage.setItem("magentoCartCheck",false);
                               cart_viewMagento();
                               }
                               else
                               {
                               ecomHomePageMagento();
                               }
                               
                               }
                               }
                               ]
                       },
                       {
                       
                       html: [loginHtml]
                       
                       },
                       {
                       html: ['<div><br><br><br><br><br><br><br></div>'],
                       }
                       ]
               }
               });
    
    
    var EcomLogin={
    xtype: 'EcomLogin'
        
    };
    Ext.Viewport.removeAll();
    Ext.Viewport.animateActiveItem(EcomLogin, { type: 'slide', direction: 'left' });
}


function magentoSignUpView() {
    if(!checkNetworkConnection())
    {
        
    }
    else
    {
        
    ecomsignup_HTML='<section class="home_screen"><div class="page-text"><div id="scroll_contener" class="scroll_contener"><div class="appypie-login">\
    <h2>Sign Up Now</h2><div class="cart-MSG" id="cart-MSG" style="display:none"><div class="cart_error"></div></div>\
    <div class="cart-MSG" id="cart-MSG2" style="display:none"><div class="cart_success2"></div></div>\
    <div class="login-feald"><label>Name<sup class="red-astrick">*</sup></label><input type="text" id="fname" /></div>\
    <div class="login-feald"></div><div class="login-feald"><label>Email ID<sup class="red-astrick">*</sup></label>\
    <input type="text" id="emailId" /></div><div class="login-feald"><label>Phone No<sup class="red-astrick">*</sup>\
    </label><input type="text" id="pNo" /></div><div class="login-feald"><label>Password<sup class="red-astrick">*</sup>\
    </label><input type="password" id="pass"/></div><div class="login-feald"><label>Confirm  Password<sup class="red-astrick">*</sup></label>\
    <input type="password" id="cpass"/></div><div class="login-feald"><a class="submit_btn" onclick="addMagentoUser()">Sign Up</a></div>\
    <br /><div class="fb-div">Already have an account? <a onclick="loginEcommerceMagento()">Sign In</a></div>\
    </div></div></div></section>';
    Ext.define('appypie.view.EcomSignup', {
               extend: 'Ext.Container',
               requires:['Ext.TitleBar'],
               alias: 'widget.EcomSignup',
               id: 'EcomSignup',
               config: {
               layout: {
               type: 'vbox'
               },
               scrollable: {
               direction: 'vertical',
               cls:'page-content',
               directionLock: false
               },
               items: [
                       {
                       xtype: 'titlebar',
                       docked: 'top',
                       height: 53,
                       title: '<h1>'+appName+'</h1>',
                       id:'header',
                       items: [{
                               xtype: 'button',
                               align: 'center',
                               height: 45,
                               width: 45,
                               style:'border-radius:0; border:0;',
                               id:'subMenu',
                               cls: 'subMenuButton',
                               handler:function() {
                               $(".sub-menu").slideToggle();
                               }
                               },
                               {
                               xtype: 'button',
                               align: 'right',
                               height: 40,
                               width: 75,
                               style:'border-radius:0; border:0;',
                               id:'cart',
                               cls: 'cartButton',
                               handler:function() {
                               Ext.Viewport.removeAll();
                               loginEcommerceMagento();
                               }
                               }]
                       },
                       {
                       
                       html: [ecomsignup_HTML]
                       
                       },
                       {
                       html: ['<div><br><br><br><br><br><br><br></div>'],
                       }
                       ]
               }
               });
    
    
    var EcomSignup={
    xtype: 'EcomSignup'
    };
    Ext.Viewport.removeAll();
    Ext.Viewport.animateActiveItem(EcomSignup, {animation: false});
    }
}

function ecomForgotPassView() {
    if(!checkNetworkConnection())
    {
        
    }
    else
    {
        
    ecomforgotpass_HTML='<section class="home_screen"><div class="page-text"><div id="scroll_contener" class="scroll_contener">\
    <div class="appypie-login forgot-pss"><h2>Forgot Password</h2><div class="cart-MSG" id="cart-MSG" style="display:none">\
    <div class="cart_error"></div></div><div class="cart-MSG" id="cart-MSG1" style="display:none">\
    <div class="cart_success">We have sent an email to your email id</div></div><div class="login-feald"><label>Email ID</label>\
    <input type="text" id="loginid" /></div><br /><div class="login-feald"><a class="submit_btn" onclick="sendEcomPassword()">Reset Password</a>\
    </div<br /><div class="fb-div">Do not have an account? <a onclick="magentoSignUpView()">Sign up Now</a><br /><br />Already have an account? <a onclick="loginEcommerceMagento()">Sign In</a></div>\
    </div></div></div></section>';
    Ext.define('appypie.view.EcomForgotPass', {
               extend: 'Ext.Container',
               requires:['Ext.TitleBar'],
               alias: 'widget.EcomForgotPass',
               id: 'EcomForgotPass',
               config: {
               layout: {
               type: 'vbox'
               },
               scrollable: {
               direction: 'vertical',
               cls:'page-content',
               directionLock: false
               },
               items: [
                       {
                       xtype: 'titlebar',
                       docked: 'top',
                       height: 53,
                       title: '<h1>'+appName+'</h1>',
                       id:'header',
                       items: [{
                               xtype: 'button',
                               align: 'center',
                               height: 45,
                               width: 45,
                               style:'border-radius:0; border:0;',
                               id:'subMenu',
                               cls: 'subMenuButton',
                               handler:function() {
                               $(".sub-menu").slideToggle();
                               }
                               },
                               {
                               xtype: 'button',
                               align: 'right',
                               height: 40,
                               width: 70,
                               style:'border-radius:0; border:0;',
                               id:'cart',
                               cls: 'cartButton',
                               handler:function() {
                               Ext.Viewport.removeAll();
                               loginEcommerceMagento();
                               }
                               }]
                       },
                       {
                       
                       html: [ecomforgotpass_HTML]
                       
                       },
                       {
                       html: ['<div><br><br><br><br><br><br><br></div>'],
                       }
                       ]
               }
               });
    
    
    var EcomForgotPass={
    xtype: 'EcomForgotPass'
    };
    Ext.Viewport.removeAll();
    Ext.Viewport.animateActiveItem(EcomForgotPass, {animation: false});
    }
}
function checkMagentoUserLogin()
{
    var emailid=document.getElementById("loginid").value;
    var pass=document.getElementById("loginpass").value;
    
    if(!checkEmail(emailid) || emailid=='')
    {
    	alertPopUp('Error','Invalid Email Address...');
       
        $("#emailid").focus();
        return;
    }
    
    else if(pass==''  || pass=='Password')
    {
    	alertPopUp('Error','Please Enter Password');
           
        $("#pass").focus();
        
        return;
    }
    else
    {
        if( parseInt(pass.length) < 6)
        {
        	alertPopUp('Error','Please enter 6 or more characters. Leading or trailing spaces will be ignored');
            
            $("#pass").focus();
        }
        else
        {
            
            checkMagentologin(emailid,pass);

        }
    }
}
function checkMagentologin(emailid,pass)
{
    
    //var appid="55754035f6de";
    console.log("checkMagentologin---function"+resellerInitial+reseller);
    var wsUrl = "http://"+window.localStorage.getItem("reseller")+"/services/app-user-soap#customerLogin";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><customerLogin xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/app-user-soap#customerLogin\"><email>'+emailid+'</email><password>'+pass+'</password><appid>'+appId+'</appid></customerLogin></soap:Body></soap:Envelope>';
    
    console.log("checkMagentologin()----->>soapRequest---->>"+soapRequest);
    // jQuery.support.cors = true;
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           
           var strJSON = $(req.responseText).find("return").text();
           console.log("checkMagentologin()----->>success---->>strJSON----->>>"+strJSON);
           console.log(strJSON);
           if(strJSON != 'NotExist')
           {
           if(strJSON == 'wrong password')
           {
           console.log("checkMagentologin()----->>success---->>strJSON in if condition wrong password---->>>"+strJSON);
           
           sessionStorage.setItem("magentoLoginCheckMsg",strJSON);
           doMagentoLogin(emailid,pass);
           }
           else
           {
           console.log("checkMagentologin()----->>success---->>strJSON in else condition password going in doMagentoLogin()---->>>"+strJSON);
           sessionStorage.setItem("Magentologincheck","1");
           // code for magento login start#
           doMagentoLogin(emailid,pass);
           //End of magento login
           }
           }
           else
           {
           console.log("checkMagentologin()----->>success---->>in else strJSON condition----->>>"+strJSON);
           sessionStorage.setItem("magentoLoginCheckMsg",'DoesNotExistInAppy');
           doMagentoLogin(emailid,pass);
           }
           },
           error: function(response, textStatus, errorThrown)
           {
           
           console.log("error checkMagentologin()---->"+response);
           }
           });
    
}

function doMagentoLogin(emailId,pass)
{
    
    var arrayData= {
        "email":emailId,
        "password":pass
    };
    
    var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>mobile_customer.login</resourcePath><args>'+JSON.stringify(arrayData)+'</args></call></soap:Body></soap:Envelope>';
    console.log("doMagentoLogin() arrayData---->"+arrayData+"<<----email--->"+emailId+'<<----password----->>'+pass+'<<------soaprequest------->>'+soapRequest);
    
    // var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://www.integritysupply.com/api/soap#call/\"><sessionId>'++'</sessionId><resourcePath>mobile_customer.login</resourcePath><args>'+JSON.stringify(arrayData)+'</args></call></soap:Body></soap:Envelope>';
    ////console.log("login soap request="+soapRequest);
    $.ajax({
           type: "POST",
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           url: wsUrl,
           success: function(data,status,req)
           {
           console.log("doMagentoLogin()------->>>"+req.responseText);
           
           if($(req.responseText).find("faultstring").length>0)
           {
           var message='Sorry ! '+$(req.responseText).find("faultstring").text();
           alertMessage(""+message);
           if($(req.responseText).find("faultstring").text().indexOf("Session expired. Try to relogin.")!=-1)
           {
           //localStorage.clear();
           //window.location.href="home.html";
           }
           // $(".loading").hide();
           }
           else
           {
           if($(req.responseText).find("item").length>0)
           {
           $(req.responseText).find("item").each(function()
                                                 {
                                                 if($(this).find("key").text()=="id")
                                                 {
                                                 localStorage.setItem('customer_id',$(this).find("value").text());
                                                 localStorage.setItem("foodid",$(this).find("value").text());
                                                 }
                                                 else if($(this).find("key").text()=="email")
                                                 {
                                                 localStorage.setItem('customer_email',$(this).find("value").text());
                                                 localStorage.setItem("fooduserid",$(this).find("value").text());
                                                 }
                                                 else if($(this).find("key").text()=="firstname")
                                                 {
                                                 localStorage.setItem('customer_fname',$(this).find("value").text());
                                                 }
                                                 else if($(this).find("key").text()=="lastname")
                                                 {
                                                 localStorage.setItem('customer_lname',$(this).find("value").text());
                                                 }
                                                 });
           var strJSON = req.responseText;
           var n=strJSON.split("<callReturn xsi:type=");
           var strJSON1=n[1].split("</callReturn>");
           strJSON=strJSON1[0].split(">");
           console.log("doMagentoLogin()----->>>customer_id----->>>"+strJSON[1]);
           if(strJSON[1] == 'Invalid email id or password')
           {
        	   alertPopUp('Error','Invalid email id or password');
           
           $("#pass").focus();
           return false;

           }
           else
           {
           console.log("doMagentoLogin() in else condition req.responseText---->"+req.responseText);
           if(sessionStorage.getItem("magentoLoginCheckMsg") == 'wrong password')
           {
           changePasswordMagento(emailId,pass);
           sessionStorage.removeItem("magentoLoginCheckMsg");
           }
           if(sessionStorage.getItem("magentoLoginCheckMsg") == 'NotExist')
           {
           userMagentoAdd(localStorage.getItem('customer_fname'),null,emailId,pass);
           sessionStorage.setItem("magentoLoginCheckMsg",'DoesNotExistInAppy');
           }
           createCustomerToCart();
           }
           }
           else
           {
           
           console.log("doMagentoLogin() in else condition req.responseText---->"+req.responseText);
           var strJSON = req.responseText;
           var n=strJSON.split("<callReturn xsi:type=");
           var strJSON1=n[1].split("</callReturn>");
           strJSON=strJSON1[0].split(">");
           console.log("doMagentoLogin()----->>>customer_id----->>>"+strJSON[1]);
           if(strJSON[1] == 'Invalid email id or password')
           {
        	   alertPopUp('Error','Invalid email id or password');
         
           $("#pass").focus();
           return false;
           }
           else
           {
           if(sessionStorage.getItem("magentoLoginCheckMsg") == 'DoesNotExistInAppy')
           {
           sessionStorage.removeItem("magentoLoginCheckMsg");
           alertPopUp('Error','Invalid email id or password');
           
           return false;
           }
           else
           {
           localStorage.setItem("fooduserid",emailId);
           showContactInfo();
           setTimeout( function(){
                      var signupData={'email':emailId,'firstname':sessionStorage.getItem('nameForMagentoSignup'),'lastname':'Narang','password':pass,'website_id':'1','store_id':'1','group_id':'1'};
                      magentoSignup(signupData,emailId,pass);
                      }
                      , 1500 );
           
           //   return;
           }
           }
          
           
           //document.getElementById("login_password").value="";
           }
           //$(".loading").hide();
           }
           },
           error: function(data,status,req)
           {
            
           console.log("login response " + req.responseText);
           //  $(".loading").hide();
           }
           });
}
function addMagentoUser()
{
    $(".cart_error").empty();
    var fname=document.getElementById("fname").value;
   // var lname=document.getElementById("lname").value;
    var pNo=document.getElementById("pNo").value;
    var emailId=document.getElementById("emailId").value;
    var pass=document.getElementById("pass").value;
    var cpass=document.getElementById("cpass").value;
    
    
    
    if(fname=='' || fname=='First Name')
    {
        
        $(".cart_error").empty();
        $(".cart_error").append('Please Enter First Name.');
        
        $("#cart-MSG").slideToggle();
        setTimeout( function(){
                   $("#fname").focus();
                   $("#cart-MSG").slideToggle();
                   }
                   , 3000 );
        return;
    }
  
    else if(!checkEmail(emailId) || emailId=='')
    {
        $(".cart_error").empty();
        $(".cart_error").append('Please Enter Valid Email Id.');
        
        $("#cart-MSG").slideToggle();
        setTimeout( function(){
                   $("#emailId").focus();
                   $("#cart-MSG").slideToggle();
                   }
                   , 3000 );
        return;
    }
    else if(pNo=='' || pNo=='Phone Number' )
    {
        $(".cart_error").empty();
        $(".cart_error").append('Please Enter Phone Number.');
        
        $("#cart-MSG").slideToggle();
        setTimeout( function(){
                   $("#pNo").focus();
                   $("#cart-MSG").slideToggle();
                   }
                   , 3000 );
        return;
    }
    else if(isNaN(pNo))
    {
        $(".cart_error").empty();
        $(".cart_error").append('Please Enter Valid Phone Number.');
        
        $("#cart-MSG").slideToggle();
        setTimeout( function(){
                   $("#pNo").focus();
                   $("#cart-MSG").slideToggle();
                   }
                   , 3000 );
        return;
    }
    else if(pass==''  || pass=='Password')
    {
        $(".cart_error").empty();
        $(".cart_error").append('Please Enter Password');
        
        $("#cart-MSG").slideToggle();
        setTimeout( function(){
                   $("#pass").focus();
                   $("#cart-MSG").slideToggle();
                   }
                   , 3000 );
        return;
    }
    else if(cpass!=pass)
    {
        $(".cart_error").empty();
        $(".cart_error").append('Confirm Password do not match');
        
        $("#cart-MSG").slideToggle();
        setTimeout( function(){
                   $("#cpass").focus();
                   $("#cart-MSG").slideToggle();
                   }
                   , 3000 );
        return;
    }
    else
    {
        
        if( parseInt(pass.length) < 6)
        {
        	alertPopUp('Error','Please enter 6 or more characters. Leading or trailing spaces will be ignored');
            
            $("#pass").focus();
        }
        else
        {
            
           userMagentoAdd(fname,pNo,emailId,pass);
        }
        
        
    }
}


function changePasswordMagento(emailId,pass)
{
    var wsUrl = "http://"+window.localStorage.getItem("reseller")+"/services/app-user-soap#changePassword";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><changePassword xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/app-user-soap#changePassword\"><appId>'+appId+'</appId><email>'+emailId+'</email><password>'+pass+'</password></changePassword></soap:Body></soap:Envelope>';
    
    console.log("changePasswordMagento()----->"+soapRequest);
    // jQuery.support.cors = true;
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           
           var strJSON = $(req.responseText).find("return").text();
           console.log('strJSON==>'+strJSON);
           },
           error: function(response, textStatus, errorThrown)
           {
           
           console.log("error changePasswordMagento()"+response);
           }
           });

}
function userMagentoAdd(fname,pNo,emailId,pass)
{
    var wsUrl = "http://"+window.localStorage.getItem("reseller")+"/services/app-user-soap#customerRegistration";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><customerRegistration xmlns=\"http://'+window.localStorage.getItem("reseller")+'/services/app-user-soap#customerRegistration\"><appId>'+appId+'</appId><email>'+emailId+'</email><password>'+pass+'</password><fname>'+fname+'</fname><lname></lname><phone>'+pNo+'</phone></customerRegistration></soap:Body></soap:Envelope>';
    
    console.log("userMagentoAdd()----->"+soapRequest);
   // jQuery.support.cors = true;
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
            
           var strJSON = $(req.responseText).find("return").text();
           console.log('strJSON==>'+strJSON);
           var result=strJSON.split("_");
           if(parseInt(result.length)==2)
           {
         
                      /****login adjustment*****/
                      //window.location ="ecomlogin.html";
           if(sessionStorage.getItem("magentoLoginCheckMsg") != 'DoesNotExistInAppy')
           {
           var signupData={'email':emailId,'firstname':fname,'lastname':'Narang','password':pass,'website_id':1,'store_id':1,'group_id':1};
            console.log("userMagentoAdd() if condition----->"+signupData);
           magentoSignup(signupData,emailId,pass);
           
          
           }
           else
           {
           
           if(sessionStorage.getItem("magentoCartCheck"))
           {
           console.log("userMagentoAdd() if condition----->checkout_viewMagento()   ");
           checkout_viewMagento();
           sessionStorage.setItem("magentoCartCheck",false);
           }
           else
           {
           ecomHomePageMagento();
           }
           }
           
                 localStorage.setItem("fooduserid",emailId);
           
                     // loginEcommerceMagento();
           
           
           }
           else
           {
           localStorage.setItem("fooduserid",emailId);
           showContactInfo();
           setTimeout( function(){
                      var signupData={'email':emailId,'firstname':sessionStorage.getItem('nameForMagentoSignup'),'lastname':'Narang','password':pass,'website_id':'1','store_id':'1','group_id':'1'};
                      magentoSignup(signupData,emailId,pass);
                      }
                      , 1500 );
           
          // navigator.notification.alert('Email Id Already Registered', setTimeout, "Error", "OK");
           //$("#emailId").focus();
           return;
           }
           },
           error: function(response, textStatus, errorThrown) 
           {
            
           console.log("error userMagentoAdd()"+response);
           }
           });
    
}

function magentoSignup(signupData,emailId,pass)
{
    
    
    var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>mobile_customer.create</resourcePath><args>'+JSON.stringify(signupData)+'</args></call></soap:Body></soap:Envelope>';
     console.log("magentoSignup() else condition----->"+signupData+"<<----emal--->"+emailId+'<<----password----->>'+pass+'<<------soaprequest------->>'+soapRequest);
//	var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://www.integritysupply.com/api/soap#call/\"><sessionId>'+localStorage.getItem('token')+'</sessionId><resourcePath>mobile_customer.create</resourcePath><args>'+JSON.stringify(signupData)+'</args></call></soap:Body></soap:Envelope>';
    // //console.log("sign up soap request="+soapRequest);
    $.ajax({
           type: "POST",
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
		   url: wsUrl,
           success: function(data,status,req)
           {
          console.log("sign up response " + req.responseText);
           
           if($(req.responseText).find("faultstring").length>0)
           {
           
           var message=$(req.responseText).find("faultstring").text();
           alertMessage(""+message);
           
         //  document.getElementById("signup_password").value="";
          // document.getElementById("signup_confirm_password").value="";
           }
           else
           {
           var strJSON = req.responseText;
           console.log("magentoSignup()----->>>return----->>>"+strJSON);
           var n=strJSON.split("<callReturn xsi:type=");
           var strJSON1=n[1].split("</callReturn>");
           strJSON=strJSON1[0].split(">");
           console.log("magentoSignup()----->>>customer_id----->>>"+strJSON[1]);
           if(strJSON[1] == 'Invalid email id or password')
           {
        	   alertPopUp('Error','Please Enter Password');
          
           $("#pass").focus();
           return false;
           }
           else
           {
           localStorage.setItem('customer_id',strJSON[1]);
           
           localStorage.setItem("foodid",strJSON[1]);
           doMagentoLogin(emailId,pass);
           $("#cart-MSG2").slideToggle();
           $(".cart_success2").append('Signup Successful');
           }
         //  doSignAfterSignUp(email,pswd);
           }
           },
           error: function(data,status,req)
           {
           ////console.log("sign up response " + req.responseText);
	       //$(".loading").hide();
           
           console.log("error magentoSignup()"+req.responseText);
           }
           });

}

function createCustomerToCart(valueFrom)
{
    //  showIndicator();
    var arrayData= {"quoteId":sessionStorage.getItem('quote_id'),"entity_id":localStorage.getItem('customer_id'),"mode":"customer","store":storeId};
    
    var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>mobile_cart_customer.setcustomer</resourcePath><args>'+JSON.stringify(arrayData)+'</args></call></soap:Body></soap:Envelope>';
    
    console.log("createCustomerToCart()--->>soapRequest---->>"+soapRequest+"<<-------arrayData------>>>"+arrayData);
	//var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://www.integritysupply.com/api/soap#call/\"><sessionId>'+localStorage.getItem('token')+'</sessionId><resourcePath>mobile_cart_customer.setcustomer</resourcePath><args>'+JSON.stringify(arrayData)+'</args></call></soap:Body></soap:Envelope>';
    ////console.log("cart customer create soap request="+soapRequest);
    $.ajax({
           type: "POST",
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
		   url: wsUrl,
           success: function(data,status,req)
           {
           ////console.log("cart customer create response " + req.responseText);
           if($(req.responseText).find("callReturn").length>0)
           {
           sessionStorage.setItem("quote_id",""+$(req.responseText).find("callReturn").text());
           localStorage.setItem("Loginquote_id",""+$(req.responseText).find("callReturn").text());
           console.log("createCustomerToCart()--->>quote_id---->>"+$(req.responseText).find("callReturn").text());
           }
           // $(".loading").hide();
           if(valueFrom)
           {
           console.log("createCustomerToCart()--->>if condition---->>cart_viewMagento()");
           cart_viewMagento();
           }
           else
           {
           if(sessionStorage.getItem("magentoCartCheck"))
           {
           sessionStorage.setItem("magentoCartCheck",false);
           checkout_viewMagento();
           console.log("createCustomerToCart()--->>else condition---->>checkout_viewMagento()");

           }
           else
           {
           ecomHomePageMagento();
           console.log("createCustomerToCart()--->>else condition---->>ecomHomePageMagento()");

           }
           }
           },
           error: function(data,status,req)
           {
	        console.log("error---->>createCustomerToCart()"+req.responseText);
           }
           });
}
/**********Send Password*********/
function sendEcomPassword()
{
    var emailid=document.getElementById("loginid").value;
    
    
    if(!checkEmail(emailid) || emailid=='')
    {
        $(".cart_error").empty();
        $(".cart_error").append('Uh-oh, inValid email ID.');
        
        $("#cart-MSG").slideToggle();
        setTimeout( function(){
                   $("#loginid").focus();
                   $("#cart-MSG").slideToggle();
                   }
                   , 3000 );
        return;
        
    }
    else
    {
         
        var wsUrl = "http://"+resellerInitial+reseller+"/services/app-user-soap#forgetPassword";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><forgetPassword xmlns=\"http://'+resellerInitial+reseller+'/services/app-user-soap#forgetPassword\"><appId>'+appId+'</appId><email>'+emailid+'</email></forgetPassword></soap:Body></soap:Envelope>';
        console.log(soapRequest);
        
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
                
               var strJSON = $(req.responseText).find("return").text();
               console.log(strJSON);
               if(strJSON=='success')
               {
               var customerEmail={"email":emailid};
               var wsUrl = "http://"+resellerInitial+reseller+"/api/soap#call";
               var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://'+resellerInitial+reseller+'/api/soap#call\"><sessionId>'+sessionStorage.getItem("magentoSessionId")+'</sessionId><resourcePath>mobile_customer.forgotpassword</resourcePath><args>'+JSON.stringify(customerEmail)+'</args></call></soap:Body></soap:Envelope>';
               
        //       var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><call xmlns=\"http://www.integritysupply.com/api/soap#call/\"><sessionId>'+localStorage.getItem('token')+'</sessionId><resourcePath>mobile_customer.forgotpassword</resourcePath><args>'+JSON.stringify(customerEmail)+'</args></call></soap:Body></soap:Envelope>';
               ////console.log("forgot pwd soap request="+soapRequest);
               $.ajax({
                      type: "POST",
                      contentType: "text/xml",
                      dataType: "text",
                      data: soapRequest,
                      url: wsurl,
                      success: function(data,status,req)
                      {
                      $("#cart-MSG1").slideToggle();
                      setTimeout( function(){
                                 $("#cart-MSG1").slideToggle();
                                 setTimeout( function(){
                                            loginEcommerceMagento();
                                            }
                                            , 2000 );
                                 }
                                 , 3000 );
                      return;
                      
                      },
                      error: function(data,status,req)
                      {
                      console.log("forgot pwd response " + req.responseText);
                      
                      $(".cart_error").empty();
                      $(".cart_error").append('Email id does nit exist.');
                      
                      $("#cart-MSG").slideToggle();
                      setTimeout( function(){
                                 $("#loginid").focus();
                                 $("#cart-MSG").slideToggle();
                                 }
                                 , 3000 );
                      return;
                      
                      //$(".loading").hide();
                      }
                      });
               }
               else
               {
               $(".cart_error").empty();
               $(".cart_error").append('Email id does nit exist.');
               
               $("#cart-MSG").slideToggle();
               setTimeout( function(){
                          $("#loginid").focus();
                          $("#cart-MSG").slideToggle();
                          }
                          , 3000 );
               return;
               }
               },
               error: function(response, textStatus, errorThrown) 
               {
                
               console.log(response);
               }
               });
    }
}
