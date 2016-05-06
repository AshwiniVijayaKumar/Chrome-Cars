var masterData=$.parseXML(window.sessionStorage.getItem("xml"));
var resellerInitial="";
var reseller='';
var wsUrl='';
var soapRequest='';
var html='';
var cartItemsArray=new Array();
var appid='';
var saveProductID = [];
var Quantity = [];
var CONSTANTIMAGEURL = "";
var productPriceforSorting = [];
var pImageforSorting = [];
var pTitleforSorting = [];
var pDescriptionforSorting = [];
var pIdforSorting = [];
var apply_coupon_code_mcom = "Apply Coupon";

/*
FOR FEATURED Products
*/
var fProductID = [];
var fProductImage = [];

/*
Language translation
*/
var home_mcom = "Home";
var my_shop_mcom = "My Shop";
var login_signup_mcom = "Login/Sign Up";
var cart_mcom = "Cart";
var my_account_mcom = "My Account";
var My_Orders_mcom = "My Orders";
var featured_product_mcom = "Featured Product";
var categories_mcom = "Categories";
var submit_mcom = "Submit";
var forgot_password_mcom = "Forgot Password";
var email_id_mcom = "Email Id";
var password_mcom = "Password";
var do_not_have_an_account_mcom = "Do not have an account?";
var search_mcom = "Search";
var sign_up_now_mcom = "Sign Up Now";
var name_mcom = "Name";
var phone_mcom = "Phone";
var confirm_password_mcom = "Confirm Password";
var sign_up_mcom = "Sign Up";
var already_have_an_account_mcom = "Already have an account?";
var sign_in_mcom = "Sign In";
var contact_information_mcom = "Contact Information";
var my_address_mcom = "My Address";
var default_billing_address_mcom = "Default Billing Address";
var default_shipping_address_mcom = "Default Shipping Address";
var order_history_mcom = "Order History";
var order_id_mcom = "Order Id";
var billing_address_mcom = "Billing Address";
var shipping_address_mcom = "Shipping Address";
var order_detail_mcom = "Order Detail";
var product_name_mcom = "Product Name";
var price_mcom = "Price";
var qty_mcom = "Qty";
var order_date_mcom = "Order Date";
var subtotal_mcom = "Subtotal";
var tax_mcom = "Tax";
var coupon_mcom = "Coupon";
var discount_mcom = "Discount";
var shipping_mcom = "Shipping";
var grand_total_mcom = "Grand Total";
var payment_method_mcom = "Payment Method";
var order_status_mcom = "Order Status";
var personal_information_mcom = "Personal Information";
var change_password_mcom = "Change Password";
var current_password_mcom = "Current Password";
var confirm_new_password_mcom = "Confirm New Password";
var no_product_exists_mcom = "No Product Exists";
var add_to_cart_mcom = "Add To Cart";
var cart_list_mcom = "Cart List";
var edit_mcom = "Edit";
var done_mcom = "Done";
var payment_details_mcom = "Payment Details";
var apply_coupon_code_mcom = "Apply Coupon Code";
var enter_your_coupon_code_if_you_have_one_mcom = "Enter coupon code";
var apply_mcom = "Apply";
var continue_shopping_mcom = "Continue Shopping";
var checkout_mcom = "Checkout";
var first_name_mcom = "First Name";
var last_name_mcom = "Last Name";
var address_mcom = "Address";
var zip_postal_code_mcom = "Zip/Postal Code";
var city_mcom = "City";
var country_mcom = "Country";
var state_province_mcom = "State/Province";
var telephone_mcom = "Telephone";
var fax_mcom = "Fax";
var same_as_billing_address_mcom = "Same as Billing Address";
var pay_now_mcom = "PROCEED TO PAY";
var cash_on_delivery_mcom = "Cash On Delivery";
var please_click_on_place_order_button_to_place_the_order_mcom = "Please Click On Place Order Button To Place The Order";
var credit_card_mcom = "Credit Card";
var paypal_mcom = "Paypal";
var you_will_be_redirected_to_paypal_site_mcom = "You will be redirected to paypal site";
var order_by_phone_mcom = "Order by Phone";
var you_can_order_by_calling_mcom = "You can order by calling";
var place_order_mcom = "Place Order";
var instock_mcom = "Instock";
var out_of_stock_mcom = "Out Of Stock";
var test_mcom = "Test mcom";
var instructions_mcom="Instructions";
var search_product_mcom="Serach Product";
var login_mcom="Login";
var Total_Payable_Amount="";
//*********start new var**********//
var logout_mcom="Logout";
var notifications_mcom="Notifications";
var offers_mcom ="Offers";
var wishlist_mcom="Wish List";
var settings_mcom="Settings";
var tc_mcom="Terms and conditions";
var privacy_policy_mcom="Privacy policy";

var alert_mcom = 'Alert!';
var please_mcom ='Please enter a keyword to search';
var empty_mcom = "Empty search";
var result_mcom = "result!";
var tryanother_mcom = "try another keyword";
var searchresult_mcom = "Search Result for";
var sortby_mcom = "Sort By";
var popularityg_mcom = "Popularity";
var pricehighlow_mcom = "Price High-Low";
var pricelowhigh_mcom = "Price Low-High";
var msgg_mcom = "There is no product added";
var selectg_mcom = "Select";
var sorryg_mcom = "Sorry"; 
var quantityoutofstock_mcom = "Quantity Out of Stock";
var pleaseselectg_mcom = "Please select ";
var errorg_mcom = "Error";
var okg_mcom = "Ok";
var productsuccessfullyaddedinyourcartg_mcom = "Product successfully Added In Your Cart.";
var quantityshouldbegreaterthanzerog_mcom = "Quantity should be greater than zero";
var pproductsuccessfullyaddedinyourcartg_mcom = 'Product successfully Added In Your Cart';
var qquantity_mcom = "Quantity";
var pprice_mcom = "Price";
var ttotalpayableamount_mcom = "Total Payable Amount:"; 
var coupong_mcom = "Coupon";
var subtotalg_mcom = "Sub Total";
var discountg_mcom = "Discount ";
var deliverychargeg_mcom = "Delivery Charge ";
var cartemptyg_mcom = "Your Cart is ";
var eemptyg_mcom = "Empty!";
var additemnowg_mcom = "Add items to it now";
var gotohomeg_mcom = "Go to Home"; var ccouponpageg_mcom = "Coupon Page";
var notavalidcoupong_mcom = "Not a valid coupon ";
var entercouponcodeg_mcom = "Enter Coupon Code";
var applygg_mcom = "Apply";
var ddeliverychargegg_mcom = "Delivery Charge ";
var homeeg_mcom = "Home";
var myAccountg_mcom = "My Account";
var termsconditionsgg_mcom ="Terms & Conditions";
var privacypolicygg_mcom = "Privacy Policy";
var shippingaddressdifferentfrombillingdddressgg_mcom = " Shipping Address Different From Billing Address";
var dddeliverychargegg_mcom = "Delivery Charge ";
var ccompletepurchasegg_mcom ="COMPLETE PURCHASE";
var iinvalidloginidorpasswordgg_mcom = "Invalid login id or password.";
var nnamegg_mcom = "Name ";
var pphonenumbergg_mcom = "Phone Number";
var wwehavesentaemailtoyouremailidgg_mcom = "We have sent an email to your email id";
var eenteryyouremailgg_mcom = "Enter your email";
var ppleaseenterusernamegg_mcom = "Please Enter Username.";
var ppleaseemailidgg ="Please Email Id.";
var ppleaseentervalidemailidgg_mcom = "Please Enter Valid Email Id.";
var ppleaseenterphonenumbergg_mcom = "Please Enter Phone Number.";
var ppleaseentervalidphonenumbergg_mcom = "Please Enter Valid Phone Number.";
var ppleaseenterpasswordgg_mcom = "Please Enter Password.";
var cconfirmpassworddonotmatchgg_mcom = "Confirm Password do not match.";
var eemailidalreadyregisteredgg_mcom = "Email Id Already Registered";
var eerrorgg_mcom = "Error";
var OoKgg_mcom = "OK";
var uuhohgg_mcom ="Uh-oh";
var iinvalidemailiidggg_mcom = "Invalid email ID.";
var yyouhavenotbeenregisteredwithusyetgg_mcom ="You have not been registered with us yet.";
var ppasswordresetwassuccessfullpleasecheckyouremailfornewpasswordgg_mcom = "Password Reset was successfull,Please check your email for new password.";
var eeerrorgg_mcom = "Error";
var ppleaseenteremailidggg_mcom ="Please enter email id";
var iiinvalidemailaddressggg_mcom = "Invalid email address";
var ppppleaseenterpasswordgggg_mcom = "Please enter password";
var uuusernotexistggg_mcom = "User not exist";
var yyyouhaveenteredwrongpasswordggg_mcom = "You have entered wrong password";
var ooordersnotfoundgggg_mcom = "Orders not found.";
var ppproductnnamegg_mcom = "Product Name";
var qqquantitygggg_mcom = "Quantity";
var ppppricegggg_mcom = "Price";
var dddddeliverychargeggg_mcom = "delivery Charge";
var tttipggg_mcom = "Tip";
var ccccouponddiscountgggg_mcom = "Coupon Discount";
var tttotalpayableaamountggg_mcom = "Total Payable Amount";
var cccommentgggg_mcom = "Comment";
var rrrrreordergggg_mcom = "Reorder";
var pppaymentofproductorderforgggg_mcom = "Payment of Product order for";
var ttthankggg_mcom = "Thank";
var yyyouggg_mcom = "You";
var ooorderidddggg_mcom = "Order ID";
var cccontinuemypoordersggg_mcom = "Continue My Orders";
var gggtohhomegggg_mcom = "Go to Home";
var wwevereceivedouordergg_mcom = "We\'ve received your order";
var yyourorderwassuccessfulgg_mocom = "Your Order was successful";
var wwearesorrygg_com = "We are sorry";
var wwwriteeviewgg_mcom  = "Write a Review";
var postreviewguidelinesggg_mcom = "Guidelines for writing a product review. All fields are mandatory. Please do not include: HTML, references to other retailers, pricing, personal information, any profane, inflammatory or copyrighted comments, or any copied content.";
var pppposteviewggg_mocom = "Post Review";
var eviewitleggg_mcom = "Review Title";
var ooommentgg_mcom = "Comment";
var ppppostreviewgg_mcom = "Post Review";
var eeeerrorggg_mcom = "Error";
var ppleaseenterthereviewtitleggg_mcom = "Please enter the review title";
var pppleaseenterthereviewtextggg_mcom = "Please enter the review text";
var rrrequiredfieldsaremissingggg_mcom = "Required fields are missing";
var nnetworkerrorggg_mcom = "Network error";
var ppleasecheckyourinternetconnectionggg_mcom = "Please check your internet connection";
var ooorderhasbeennottakenorcompletedgggg_mcom = "Order has been not taken or completed";
var nnnotificationggg_mcom = "Notification";
var eeeeemptygggg_mcom = "Empty";
var ttthereisnonotificationlistgggggg_mocom = "There is no notification list.";
var ggotohhomeggg_mcpm = "Go to Home";
var iiiiteminyourordergggg_mcom = "item in your order";
var hhhhasbeenshippedbysellerggggg_mcom = "has been shipped by seller.";
var iinformationupdatedssuccessfullygg_mcom="Information Updated Successfully.";
var nnnameggg_mcom = "Name";
var eeemailaaddressggg_mcom = "Email Address";
var eeenteryouremailiddggg_mcom = "Enter your Email ID";
var tttelephonenoggg_mcom = "Telephone No";
var uupdateccontactinformationgg_mcom = "Update Contact Information";
var bbbillinginformationupdatedsuccessfullyggg_mcom = "Billing Information Updated Successfully.";
var sstreetaaddressgg_mcom = "Street Address";
var eenteryyourccitygg_mcom = "Enter Your City";
var eeentersstatepprovincegg_mcom = "Enter State/Province";
var eeenterzzippostalcodegg_mcom = "Enter Zip/Postal Code";
var zzipppostalcodegg_mcom = "Zip/Postal Code";
var sstreetaaddressggg_mcom = "Street Address";
var aaaddressdifferentfrombillingaaddressgg_mcom = "Address Different From Billing Address";
var uupdatebillingiinformationgg_mcom = "Update Billing Information";
var sshippinginformationupdatedsuccessfullyggg_mcom = "Shipping Information Updated Successfully";
var uupdatesshippingiinformationgg_mcom = "Update Shipping Information";

var sselectfromwhereyouwanttouuuploadgg_mcom = "Select from where you want to upload!";
var mmessagegg_mcom = "Message";
var cameragg_mcomn = "Camera"
var ggallerygg_mcom = "Gallery";
 var primaryButtonBgColor="";
var sssuccessfullyuuploadedgg_mcom='successfully Uploaded!';
var uuploadeerrorgg_mcom = 'Upload error';
var eerrorggg_mcom = 'Error';
var ppleastryagaingg_mcom = "Please try again";
var nnetworkrrorgg_mcom = 'Network error';
var nnoreviewavalaiblegg_mcom = "No reviews avalaible";
var cccreatecountgg_mcom = "Create Account"; 
var missingrequiredfieldspleasereloginntragaingg_mcom = "Missing required fields please re-login and try again";

var pproductsgg_mcom = "Products ";
var tthereisnishlisproductgg_mcom = "There is no Wishlist product.";

var tthereisnofferedproductgg_mcom = "There is no offered product.";
var tthereisnofeatureproductgg_mcom = "There is no featured product.";

//*********end new var**********//


var appName = localStorage.getItem("AppName");
var ecomHeader = "";
function commonHeader2(){
	if(window.sessionStorage.getItem("ecomcartQuantitywise") == undefined || window.sessionStorage.getItem("ecomcartQuantitywise") == null) {
		sessionStorage.setItem("ecomcartQuantitywise", 0)
	}
	
	if(appName==null)
	{
		if(masterData.getElementsByTagName('headerBarTitle')[0]){
			localStorage.setItem("AppName",masterData.getElementsByTagName('headerBarTitle')[0].firstChild.nodeValue);
			appName = localStorage.getItem("AppName");
		}
		else
			appName="";
	}	
	ecomHeader = '<header class="ui-header headerColor ecomHeader"><a onclick="showEcomMenu();" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" ></i></div></a><a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback"><div class="headerIcon"><i class="icon-left-open"></i></div></a><h1 class="ui-title"></h1><a class="localHeaderIconRight" onclick="ecomCart_View()"; id="localEcomCart"><span class="subValue"></span><div class="headerIcon"><i class="icon-cart" ></i></div></a></header>';
}

/* Welcome and Home page  */
function getEcommerceData(index)
{
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
	stopScroll();
	
	//arabic();
    CONSTANTIMAGEURL = "http://"+localStorage.getItem("reseller") + "/";
//http://snappy.appypie.com/ecommerce/upload-user-image
//window.sessionStorage.setItem("fromPageIdentity", "ecom");
showHideAd("hide");
    $("#shopmenu").hide();
	sessionStorage.setItem("AppPageName","ecommerce");
		$("body").addClass("ecom")
    setTimeout(function(){
    $("[data-role='header']").remove();
    $("[data-role='header']").toolbar('destroy');
    $.mobile.resetActivePageHeight();
    $('#page2').attr("style","padding:0px !important");
	dynamicCSS('ecommerce');
               sessionStorage.setItem('hideHeaderBar','false');
               localStorage.setItem("hidebootomforce",'true');
              },100)
    if(localStorage.getItem("layout")=="bottom")
    {
        if($(".app_navigation_bottom"))
        {
            $(".app_navigation_bottom").hide();
        }
        localStorage.setItem("hidebootomforce",'true');
        $(".app_navigation_bottom_carousel").hide();
        localStorage.setItem("bottomHide","ture");
        $( "[data-role='footer']" ).remove();
        $( "[data-role='footer']" ).toolbar('destroy');
       
      //  $('#page1').attr('style','padding:0px !important');
         $.mobile.resetActivePageHeight();
    }

	/*
	Parse language translation
	*/
	$menuXmlData = $(masterData).find( ""+"ecommerce"+"[indexval="+PageIndex[index]+"]" );
 
	 if($menuXmlData.find("LanguageSettings").find("home_mcom").text())
      {
      console.log('open page vij --->'+PageIndex[index]);
      home_mcom=$menuXmlData.find("LanguageSettings").find("home_mcom").text();
	   console.log('home_mcom --->'+home_mcom);
	  }
	 if($menuXmlData.find("LanguageSettings").find("my_shop_mcom").text())
     {
		my_shop_mcom=$menuXmlData.find("LanguageSettings").find("my_shop_mcom").text();
	  }
	  if($menuXmlData.find("LanguageSettings").find("login_mcom").text())
      {
		  login_mcom=$menuXmlData.find("LanguageSettings").find("login_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("search_product_mcom").text())
      {
		search_product_mcom=$menuXmlData.find("LanguageSettings").find("search_product_mcom").text();
	  }
	  
	   if($menuXmlData.find("LanguageSettings").find("login_signup_mcom").text())
      {
		login_signup_mcom=$menuXmlData.find("LanguageSettings").find("login_signup_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("cart_mcom").text())
      {
		cart_mcom=$menuXmlData.find("LanguageSettings").find("cart_mcom").text();
	  }
	     if($menuXmlData.find("LanguageSettings").find("apply_coupon_code_mcom").text())
      {
  apply_coupon_code_mcom=$menuXmlData.find("LanguageSettings").find("apply_coupon_code_mcom").text();
   }
	   if($menuXmlData.find("LanguageSettings").find("my_account_mcom").text())
      {
		my_account_mcom=$menuXmlData.find("LanguageSettings").find("my_account_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("My_Orders_mcom").text())
      {
		My_Orders_mcom=$menuXmlData.find("LanguageSettings").find("My_Orders_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("featured_product_mcom").text())
      {
		featured_product_mcom=$menuXmlData.find("LanguageSettings").find("featured_product_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("categories_mcom").text())
      {
		categories_mcom=$menuXmlData.find("LanguageSettings").find("categories_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("submit_mcom").text())
      {
		submit_mcom=$menuXmlData.find("LanguageSettings").find("submit_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("forgot_password_mcom").text())
      {
		forgot_password_mcom=$menuXmlData.find("LanguageSettings").find("forgot_password_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("email_id_mcom").text())
      {
		email_id_mcom=$menuXmlData.find("LanguageSettings").find("email_id_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("password_mcom").text())
      {
		password_mcom=$menuXmlData.find("LanguageSettings").find("password_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("do_not_have_an_account_mcom").text())
      {
		do_not_have_an_account_mcom=$menuXmlData.find("LanguageSettings").find("do_not_have_an_account_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("search_mcom").text())
      {
		search_mcom=$menuXmlData.find("LanguageSettings").find("search_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("sign_up_now_mcom").text())
      {
		sign_up_now_mcom=$menuXmlData.find("LanguageSettings").find("sign_up_now_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("name_mcom").text())
      {
		name_mcom=$menuXmlData.find("LanguageSettings").find("name_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("phone_mcom").text())
      {
		phone_mcom=$menuXmlData.find("LanguageSettings").find("phone_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("confirm_password_mcom").text())
      {
		confirm_password_mcom=$menuXmlData.find("LanguageSettings").find("confirm_password_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("sign_up_mcom").text())
      {
		sign_up_mcom=$menuXmlData.find("LanguageSettings").find("sign_up_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("already_have_an_account_mcom").text())
      {
		already_have_an_account_mcom=$menuXmlData.find("LanguageSettings").find("already_have_an_account_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("sign_in_mcom").text())
      {
		sign_in_mcom=$menuXmlData.find("LanguageSettings").find("sign_in_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("contact_information_mcom").text())
      {
		contact_information_mcom=$menuXmlData.find("LanguageSettings").find("contact_information_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("my_address_mcom").text())
      {
		my_address_mcom=$menuXmlData.find("LanguageSettings").find("my_address_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("default_billing_address_mcom").text())
      {
		default_billing_address_mcom=$menuXmlData.find("LanguageSettings").find("default_billing_address_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("default_shipping_address_mcom").text())
      {
		default_shipping_address_mcom=$menuXmlData.find("LanguageSettings").find("default_shipping_address_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("order_history_mcom").text())
      {
		order_history_mcom=$menuXmlData.find("LanguageSettings").find("order_history_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("order_id_mcom").text())
      {
		order_id_mcom=$menuXmlData.find("LanguageSettings").find("order_id_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("billing_address_mcom").text())
      {
		billing_address_mcom=$menuXmlData.find("LanguageSettings").find("billing_address_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("shipping_address_mcom").text())
      {
		shipping_address_mcom=$menuXmlData.find("LanguageSettings").find("shipping_address_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("order_detail_mcom").text())
      {
		order_detail_mcom=$menuXmlData.find("LanguageSettings").find("order_detail_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("product_name_mcom").text())
      {
		product_name_mcom=$menuXmlData.find("LanguageSettings").find("product_name_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("price_mcom").text())
      {
		price_mcom=$menuXmlData.find("LanguageSettings").find("price_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("qty_mcom").text())
      {
		qty_mcom=$menuXmlData.find("LanguageSettings").find("qty_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("order_date_mcom").text())
      {
		order_date_mcom=$menuXmlData.find("LanguageSettings").find("order_date_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("subtotal_mcom").text())
      {
		subtotal_mcom=$menuXmlData.find("LanguageSettings").find("subtotal_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("tax_mcom").text())
      {
		tax_mcom=$menuXmlData.find("LanguageSettings").find("tax_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("coupon_mcom").text())
      {
		coupon_mcom=$menuXmlData.find("LanguageSettings").find("coupon_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("discount_mcom").text())
      {
		discount_mcom=$menuXmlData.find("LanguageSettings").find("discount_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("shipping_mcom").text())
      {
		shipping_mcom=$menuXmlData.find("LanguageSettings").find("shipping_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("grand_total_mcom").text())
      {
		grand_total_mcom=$menuXmlData.find("LanguageSettings").find("grand_total_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("payment_method_mcom").text())
      {
		payment_method_mcom=$menuXmlData.find("LanguageSettings").find("payment_method_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("order_status_mcom").text())
      {
		order_status_mcom=$menuXmlData.find("LanguageSettings").find("order_status_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("personal_information_mcom").text())
      {
		personal_information_mcom=$menuXmlData.find("LanguageSettings").find("personal_information_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("change_password_mcom").text())
      {
		change_password_mcom=$menuXmlData.find("LanguageSettings").find("change_password_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("current_password_mcom").text())
      {
		current_password_mcom=$menuXmlData.find("LanguageSettings").find("current_password_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("confirm_new_password_mcom").text())
      {
		confirm_new_password_mcom=$menuXmlData.find("LanguageSettings").find("confirm_new_password_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("no_product_exists_mcom").text())
      {
		no_product_exists_mcom=$menuXmlData.find("LanguageSettings").find("no_product_exists_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("add_to_cart_mcom").text())
      {
		add_to_cart_mcom=$menuXmlData.find("LanguageSettings").find("add_to_cart_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("cart_list_mcom").text())
      {
		cart_list_mcom=$menuXmlData.find("LanguageSettings").find("cart_list_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("edit_mcom").text())
      {
		edit_mcom=$menuXmlData.find("LanguageSettings").find("edit_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("done_mcom").text())
      {
		done_mcom=$menuXmlData.find("LanguageSettings").find("done_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("payment_details_mcom").text())
      {
		payment_details_mcom=$menuXmlData.find("LanguageSettings").find("payment_details_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("apply_coupon_code_mcom").text())
      {
		apply_coupon_code_mcom=$menuXmlData.find("LanguageSettings").find("apply_coupon_code_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("enter_your_coupon_code_if_you_have_one_mcom").text())
      {
		enter_your_coupon_code_if_you_have_one_mcom=$menuXmlData.find("LanguageSettings").find("enter_your_coupon_code_if_you_have_one_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("apply_mcom").text())
      {
		apply_mcom=$menuXmlData.find("LanguageSettings").find("apply_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("continue_shopping_mcom").text())
      {
		continue_shopping_mcom=$menuXmlData.find("LanguageSettings").find("continue_shopping_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("checkout_mcom").text())
      {
		checkout_mcom=$menuXmlData.find("LanguageSettings").find("checkout_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("first_name_mcom").text())
      {
		first_name_mcom=$menuXmlData.find("LanguageSettings").find("first_name_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("last_name_mcom").text())
      {
		last_name_mcom=$menuXmlData.find("LanguageSettings").find("last_name_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("address_mcom").text())
      {
		address_mcom=$menuXmlData.find("LanguageSettings").find("address_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("zip_postal_code_mcom").text())
      {
		zip_postal_code_mcom=$menuXmlData.find("LanguageSettings").find("zip_postal_code_mcom").text();
	  } 
	  if($menuXmlData.find("LanguageSettings").find("city_mcom").text())
      {
		city_mcom=$menuXmlData.find("LanguageSettings").find("city_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("country_mcom").text())
      {
		country_mcom=$menuXmlData.find("LanguageSettings").find("country_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("state_province_mcom").text())
      {
		state_province_mcom=$menuXmlData.find("LanguageSettings").find("state_province_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("telephone_mcom").text())
      {
		telephone_mcom=$menuXmlData.find("LanguageSettings").find("telephone_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("fax_mcom").text())
      {
		fax_mcom=$menuXmlData.find("LanguageSettings").find("fax_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("same_as_billing_address_mcom").text())
      {
		same_as_billing_address_mcom=$menuXmlData.find("LanguageSettings").find("same_as_billing_address_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("pay_now_mcom").text())
      {
		pay_now_mcom=$menuXmlData.find("LanguageSettings").find("pay_now_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("cash_on_delivery_mcom").text())
      {
		cash_on_delivery_mcom=$menuXmlData.find("LanguageSettings").find("cash_on_delivery_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("please_click_on_place_order_button_to_place_the_order_mcom").text())
      {
		please_click_on_place_order_button_to_place_the_order_mcom=$menuXmlData.find("LanguageSettings").find("please_click_on_place_order_button_to_place_the_order_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("credit_card_mcom").text())
      {
		credit_card_mcom=$menuXmlData.find("LanguageSettings").find("credit_card_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("paypal_mcom").text())
      {
		paypal_mcom=$menuXmlData.find("LanguageSettings").find("paypal_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("you_will_be_redirected_to_paypal_site_mcom").text())
      {
		you_will_be_redirected_to_paypal_site_mcom=$menuXmlData.find("LanguageSettings").find("you_will_be_redirected_to_paypal_site_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("order_by_phone_mcom").text())
      {
		order_by_phone_mcom=$menuXmlData.find("LanguageSettings").find("order_by_phone_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("you_can_order_by_calling_mcom").text())
      {
		you_can_order_by_calling_mcom=$menuXmlData.find("LanguageSettings").find("you_can_order_by_calling_mcom").text();
		you_can_order_by_calling_food=you_can_order_by_calling_mcom;
      }
	   if($menuXmlData.find("LanguageSettings").find("place_order_mcom").text())
      {
		place_order_mcom=$menuXmlData.find("LanguageSettings").find("place_order_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("instock_mcom").text())
      {
		instock_mcom=$menuXmlData.find("LanguageSettings").find("instock_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("out_of_stock_mcom").text())
      {
		out_of_stock_mcom=$menuXmlData.find("LanguageSettings").find("out_of_stock_mcom").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("test_mcom").text())
      {
		test_mcom=$menuXmlData.find("LanguageSettings").find("test_mcom").text();
	  }
		if($menuXmlData.find("LanguageSettings").find("instructions_mcom").text())
      {
		instructions_mcom=$menuXmlData.find("LanguageSettings").find("instructions_mcom").text();
		sessionStorage.setItem("instructions_mcom",instructions_mcom);
	  }
	  
	   if($(masterData).find("primaryButtonBgColor").text()){
	  primaryButtonBgColor=$(masterData).find("primaryButtonBgColor").text();
	  }
    
	/*
	End translation
	*/
	//start new var fetching//
	if($menuXmlData.find("LanguageSettings").find("logout_mcom").text())
      {
		logout_mcom=$menuXmlData.find("LanguageSettings").find("logout_mcom").text();
		sessionStorage.setItem("logout_mcom",logout_mcom);
	  }
	  if($menuXmlData.find("LanguageSettings").find("notifications_mcom").text())
      {
		notifications_mcom=$menuXmlData.find("LanguageSettings").find("notifications_mcom").text();
		sessionStorage.setItem("notifications_mcom",notifications_mcom);
	  }
	  if($menuXmlData.find("LanguageSettings").find("offers_mcom").text())
      {
		offers_mcom=$menuXmlData.find("LanguageSettings").find("offers_mcom").text();
		sessionStorage.setItem("offers_mcom",offers_mcom);
	  }
	  if($menuXmlData.find("LanguageSettings").find("wishlist_mcom").text())
      {
		wishlist_mcom=$menuXmlData.find("LanguageSettings").find("wishlist_mcom").text();
		sessionStorage.setItem("wishlist_mcom",wishlist_mcom);
	  }
if($menuXmlData.find("LanguageSettings").find("settings_mcom").text())
      {
		settings_mcom=$menuXmlData.find("LanguageSettings").find("settings_mcom").text();
		sessionStorage.setItem("settings_mcom",settings_mcom);
	  }
	  if($menuXmlData.find("LanguageSettings").find("tc_mcom").text())
      {
		tc_mcom=$menuXmlData.find("LanguageSettings").find("tc_mcom").text();
		sessionStorage.setItem("tc_mcom",tc_mcom);
	  }
	  if($menuXmlData.find("LanguageSettings").find("privacy_policy_mcom").text())
      {
		  privacy_policy_mcom=$menuXmlData.find("LanguageSettings").find("privacy_policy_mcom").text();
		sessionStorage.setItem("privacy_policy_mcom",privacy_policy_mcom);
	  }
	 
	  
	//end new var fetching//
	
    if(window.localStorage.getItem("layout")=="slidemenu"){
        snapper.close();
    }
    sessionStorage.setItem("AppPageName","ecommerce");
	
	/*setTimeout(function(){
		$("#mainmenu").empty();
		$("#mainmenu").append('<a onclick="getEcommerceData()" class="home-nav">'+returnIconImageHtml('iconz-home')+' <span style="color:'+sessionStorage.getItem('navTextColor')+' !important">Home</span></a><a onclick="loginEcommerce()" class="login-nav">'+returnIconImageHtml('iconz-log-in')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">Login</span></a><a onclick="myaccountEcommerce()" class="account-nav">'+returnIconImageHtml('iconz-user')+' <span style="color:'+sessionStorage.getItem('navTextColor')+' !important">My Account</span></a><a onclick="termsEcommerce()" class="terms-nav">'+returnIconImageHtml('icon-info-circled')+' <span style="color:'+sessionStorage.getItem('navTextColor')+' !important">Terms</span></a><a onclick="PolicyEcommerce()" class="policy-nav">'+returnIconImageHtml('iconz-briefcase')+' <span style="color:'+sessionStorage.getItem('navTextColor')+' !important">Privacy Policy</span></a><a onclick="goHomeEcom()" class="menu-nav slideMainMenuBack">'+returnIconImageHtml('icon-left-1')+'  <span style="color:'+sessionStorage.getItem('navTextColor')+' !important">Main Menu</span></a>');
	},1000);*/
    
    if(localStorage.getItem("fooduserid"))
    {
        accountDispl='block';
        loginDispl='none';
    }
    else
    {
        accountDispl='none';
        loginDispl='block';
    }
    
    appid=window.localStorage.getItem("applicationID");
    reseller=window.localStorage.getItem("reseller");
    sessionStorage.setItem("ecommerceIndex",index);
  
     //dynamicCSS('ecommerce');
    if(!checkNetworkConnection())
    {
		
    }
	else
    {
	/*
		$('html').find('link').each(function(){
          var srcText=$(this).attr('id');
           if(srcText == 'dynamic')
               {
				$(this).attr('href','css/ecommerce.css');
				return false;
				}      
		});
		
		*/
		
    	//$('head').append('<link rel="stylesheet" href="css/ecommerce.css" type="text/css" />');
		 if(localStorage.getItem("fooduserid"))
	    {
			ecomCategories();
			getPaypalId();
			console.log("AppBg: "+ sessionStorage.getItem("appBackground"));
		}
		else
		{
			ecomCategories();
			getPaypalId();
			console.log("AppBg2: "+ sessionStorage.getItem("appBackground"));
			/*
			Login main screen
			*/
			/*ecomHTML_home = '<div class="login"><div class="login_box">\
							<div class="login_from">\
								<input type="button" class="button" value="Create Account" onclick="ecomSignUpView()">\
								<input type="button" class="button blue" value="Sign IN"  onclick="loginEcommerce()">\
							</div>\
							</div></div>';
			ecomHTML_home += ecomSubMenu();		
			appendHtml(ecomHTML_home,'',1,'food');*/
		}
    }
}



/* SubMenuHTML */

function closeSubMenu(obj) {
	//alert(obj);
	$("#localEcomMenu").trigger("click")
}
function ecomSubMenu() {
	
	var newName = window.localStorage.getItem("userName");
	console.log("newName: "+newName);
	if(newName == null || newName == undefined) {
		newName = "&nbsp;";
	}
	
	var loginClass = "sub-menu";
	if(loginDispl == "block")
	{
		loginClass += " login-none"
	}
	var tmp = search_product_mcom;  
	var ecomSubMenuHTML = '<div id="left-navs" class="'+loginClass+'" style="display:none"><div class="scroll-area">\
	<div class="profile-image">\
	<div>\
	<span class="image" id="profile_add_pics" style=background-image:url(images/no-profile-img.png)"></span>\
	</div>\
	<a href="#" onclick="image_uploadFromDevice();" class="edit-image"></a>\
	<span class="name">'+newName+'</span>\
	</div>\
	<ul>\
	<li><a onclick="homeecom()">'+home_mcom+'</a></li>\
	<li style="display:'+loginDispl+';"><a onclick="loginEcommerce()" class="login-nav">'+login_signup_mcom+'</a></li>\
	<li style="display:'+accountDispl+';"><a onclick="myOrdersEcom()" class="my-orders">'+My_Orders_mcom+'</a></li>\
	<li style="display:'+accountDispl+';"><a onclick="mcomNotifications()" class="notifications">'+notifications_mcom+'</a></li>\
	<li style="display:'+accountDispl+';"><a onclick="ecomCart_View()" class="cart">'+cart_mcom+'</a></li>\
	<li style="display:'+accountDispl+';"><a onclick="showOfferProducts()" class="offers">'+offers_mcom+'</a></li>\
	<li><a onclick="getFeaturedProducts()" class="featured">'+featured_product_mcom+'</a></li>\
	<li style="display:'+accountDispl+';"><a onclick="showWishListProducts()" class="wishlist"">'+wishlist_mcom+'</a></li>\
	<li style="display:'+accountDispl+';"><a onclick="mcomSetting()" class="settings">'+settings_mcom+'</a></li>\
	<li style="display:'+accountDispl+';"><a onclick="ecomLogout();" class="logout">'+logout_mcom+'</a></li>\
	<li><a onclick="termsEcommerce()" class="terms-and-conditions">'+tc_mcom+'</a></li>\
	<li><a onclick="PolicyEcommerce()" class="privacy-policy">'+privacy_policy_mcom+'</a></li>\
	</ul>\
	</div><div class="search-area"><input type="text" placeholder="'+tmp+'" onfocus="searchEvent(event, this)" onblur="searchEvent(event, this)" onkeyup="searchEvent(event, this)" data-role="none"><a class="close" href="#" onclick="closeSubMenu(this)"></a></div>\
	</div>'
	
	return ecomSubMenuHTML;
}

/* View Categories Page  */

function ecomCategories()
{
	var appName = localStorage.getItem("AppName");
	setTimeout(function(){
		//getFeaturedProducts();
		},1000);
	
	if(window.sessionStorage.getItem("ecomcart") == null || window.sessionStorage.getItem("ecomcart") == undefined) {
		//window.sessionStorage.setItem("ecomcart", 0);
		window.sessionStorage.setItem("ecomcartQuantitywise", 0);
	}
    if(!checkNetworkConnection())
    {
		
    }
	else
    {
		var tmp = search_product_mcom;
		ecomHTML_home = '<div class="ecom-search"  data-snap-ignore="true">\
        <input data-role="none" type="text" id="txtSearch" class="on" placeholder="'+tmp+'" onfocus="searchEvent(event, this)" onblur="searchEvent(event, this)" onkeyup="searchEvent(event, this)" />\
        <input data-role="none" type="button" value="Search" onclick="clickSearchBtn(this)" />\
        </div><div class="mcom_list scrolling-head mcom-home">\
        <div class="cart-MSG" id="cart-MSG1" style="display:none"><span class="er_close">x</span><div class="cart_success"></div></div>\
        <div id="categoryList"></div>\
        <div class="categories-listing" id="featuredProducts" style="display:none"><h3 class="catName">Featured Products</h3>\
        <ul class="food_pro_list_new"></ul></div>';
		//ecomHTML_home += 
		ecomHTML_home += ecomSubMenu();
		commonHeader2();
		appendHtml(ecomHeader + ecomHTML_home,'',1,'food');
		resetEcomHeader();
        viewAllEcomCategories(); //common for cat and subcategory
        $('#featuredProducts').hide();
		$.mobile.resetActivePageHeight();
		//setTimeout(function(){closeSubMenu(this);},1000);
		// changes by krishna
		//$("body").append(ecomSubMenu());
		/*
		MANAGE PROFILE IMAGE ON MENU
		*/
		console.log("pImage: "+ window.localStorage.getItem("uProfilePic"));
		if(window.localStorage.getItem("uProfilePic") == undefined || window.localStorage.getItem("uProfilePic") == "") {
			document.getElementById('profile_add_pics').style.backgroundImage = "url('images/no-profile-img.png')";
		} else {
			document.getElementById('profile_add_pics').style.backgroundImage = "url('" + window.localStorage.getItem("uProfilePic") + "')";
		} 
		
		
    }
}

function clickSearchBtn(obj) {
 if($(obj).parent().find(":text").is(".on"))
 {
  $(obj).parent().find(":text").removeClass("on"); 
 }
 else
 {
  $(obj).parent().find(":text").addClass("on");  
 }
}
function openSearchProduct(obj)
{
	var tempHeader = '<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
  <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
  <h1 class="ui-title">'+appName+'</h1>\
  <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>';
  
    var txtSearch=$(obj).val();
    if(checkNetworkConnection())
    {
        $('.appypie-loader').show();
        console.log("txtSearch --->"+txtSearch);
        if(txtSearch.length > 0)
        {
			//var tmp = "What's your wish today?";
			var tmp = search_product_mcom;			
            sessionStorage.setItem("ecomsearchText",txtSearch);
          //sessionStorage.getItem('appPageFont');
            searchPage_HTML='<div class="ecom-search ecom-search2" data-snap-ignore="true">\
						<input data-role="none" type="text" id="txtSearch" value="'+txtSearch+'" placeholder="'+tmp+'" class="on" onfocus="searchEvent(event, this)" onblur="searchEvent(event, this)" onkeyup="searchEvent(event, this)" />\
						<input data-role="none" type="button" value="Search" onclick="clickSearchBtn(this)" />\
						</div><div class="mcom_list scrolling-head">\
            <div class="cart-MSG" id="cart-MSG1" style="display:none"><span class="er_close">x</span><div class="cart_success"></div></div>\
			<div class="mCom_content_wrapper mcom_list" id="searchResults"><h3 class="catName" style="display:none">'+txtSearch+'</h3><div class="food_pro_list">\
            </div></div></div>';

			
			//searchPage_HTML  += ecomSubMenu();
            
            appendHtml(ecomHeader+searchPage_HTML,2,2,'ecom');
			resetEcomHeader();
            if(sessionStorage.getItem("ecomsearchText"))
            {
                searchProducts(txtSearch);
            }
            else
            {
                
                openEcomListPage(sessionStorage.getItem("ecomcategoryid"),sessionStorage.getItem("ecomcategoryName"));
            }
        }
        else
        {
        	
            alertPopUp(alert_mcom,please_mcom);
            return false;
        }
        
    }

}
/****************Seaech product****************/
function searchProducts(inputSearch)
{
	console.log("inputSearch: "+ inputSearch);
    wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#ecommSearchXml";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommSearchXml xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#ecommSearchXml\"><appId>'+appid+'</appId><search>'+inputSearch+'</search></ecommSearchXml></soap:Body></soap:Envelope>';
    
    console.log('searchProducts---------->>>>'+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           
           console.log('searchProducts--->strJSON---------->>>>'+strJSON);
           listSearchResult(strJSON);
           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
}
function listSearchResult(strJSON)
{
    $.ajax({
           url: strJSON,
           dataType: "text",
           cache: false,
           crossDomain: true,
           success: function(xmll) {
           var xml =jQuery.parseXML(xmll);
           html='';
           var count=0;
           $(xml).find("product").each(function () {
                                       var productName= $(this).find("productName").text();
                                       var price= $(this).find("price").text();
                                       var productImage= $(this).find("productImage").text();
                                       var productId= $(this).find("productId").text();
                                       var currency= $(this).find("currency").text();
                                       var description=$(this).find("description").text()
                                       count=count+1;
                                      /* if(productName.length>30) {
                                       productName=productName.substring(0,30);
                                       }*/
                                       if(description.length>30) {
                                       description=description.substring(0,30);
                                       }
	                								   
	var productBox = '<div class="product_box"><img style="background-image:url('+productImage+')" src="images/ecom-image.png" alt="" onclick="openEcomDetailPage('+productId+')"/>\
	<div class="discription_box">\
	<p class="title">'+productName+'</p>\
	<p class="description" id="pDiscription" style="display:none">'+description+'</p>\
	<div class="product_price"><big>'+currency+price+'</big></div>\
	<div class="product_price_buttons"><a href="#" onclick="addTocartEcommorce(\''+productId+'\',\''+productName+'\',\''+productImage+'\',\''+price+'\',\''+currency+'\');" class="addtoCart">+ Add</a></div>\
	</div></div>';
                                  
								  
									   html+=productBox;
                                       
                                       });
           console.log(html);
           if(count>0)
           {
           $(".food_pro_list").append(html);
		   //closeSubMenu(this);
		   document.getElementById("left-navs").style.display = 'block';
		   document.getElementById("left-navs").style.display = 'none';
           }
           else
           {
        	   
        	  
//			   var tmpmsg = '<div class="thankyou">\
//				  <h1>\
//				   Empty search <span>result!</span>\
//				  </h1>\
//				  <p>\
//				  try another keyword\
//				  </p>\
//				  </div>'
//				  $(".food_pro_list").append(tmpmsg);
        	   
        	   var tmpmsg = '<div class="thankyou"><h1>'+empty_mcom+'<span>'+result_mcom+'</span></h1><p>' +tryanother_mcom+'</p>\</div>';
     				  $(".food_pro_list").append(tmpmsg);
           //$(".food_pro_list").append('<center style="margin:50px 0 0 0"><b><h2>No Product //Found</h2></b><br/></center>'); //top, left, bottom, right
           
           document.getElementById("noproduct").style.top = "px";
           }
           if(window.sessionStorage.getItem("ecomsearchText"))
           {
           $("#product_pageHeader").empty();
           
          
           $("#product_pageHeader").append(searchresult_mcom +  "("+window.sessionStorage.getItem("ecomsearchText")+")");
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
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
           }
           });
    setTimeout(function(){  $('.appypie-loader').hide();},1000);
    
}
function viewAllEcomCategories(catid) //Call init based id common service(Parent and child cat)
{
	
    if(!checkNetworkConnection())
    {
    }else
    {
        
        wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#generateCategoryXml";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><generateCategoryXml xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#generateCategoryXml\"><appId>'+appid+'</appId><parentId>'+catid+'</parentId></generateCategoryXml></soap:Body></soap:Envelope>';
        console.log("wsUrl--->"+wsUrl);
        console.log("soapRequest--->"+soapRequest);
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
			  
               console.log("ALL "+ strJSON); //Cat list Response xml file for Tab
               listEcomCategories(strJSON); 
               },
               error: function(response, textStatus, errorThrown)
               {
               console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
    }
}
function listEcomCategories(strJSON)
{
    if(!checkNetworkConnection())
    {
    }else
    {
		
		$("#categoryList").html("");
		$("#mcomListing").html("");
		
        $.ajax({
               url: strJSON,
               dataType: "text",
               crossDomain: true,
               cache: false,
               success: function(xml) {
               
               var xml =jQuery.parseXML(xml);
			   
				   html='<div id="mcomNavSlider" class="mcom_nav swiper-container"><ul class="swiper-wrapper">';				
					$(xml).find("category").each(function (i) {										
						var categoryId= $(this).find("categoryId").text();
						var categoryName= $(this).find("categoryName").text();
						var categoryImage= $(this).find("categoryImage").text();
						saveProductID[i]=categoryId;
						
						html+='<li class="swiper-slide"><a onclick="addSubCategory(\''+categoryId+'\',\''+categoryName+'\')">'+categoryName+'</a></li>';
						
						addSubCategory(categoryId);
						
						
					});
				   html+='</ul></div>';
				   $("#categoryList").append(html);
				   
				   
				   
					setTimeout(function(){
						$('.appypie-loader').hide();
						callSlideNew();
					},500);
					
               },
               error: function(XMLHttpRequest, textStatus, errorThrown) {
				   console.log('fail');
				   setTimeout(function(){$('.appypie-loader').hide();},1000);
				   console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
               }
               });
    }
}


function addSubCategory(catID) {
	
	var catHTML = "";
	if($("#mcomListing").size() == 0)
	{
		$("#categoryList").after('<div id="mcomListing" class="swiper-container"><div class="swiper-wrapper mcomListing"></div></div>');	
	}
	catHTML += '<div class="mcomPro_listing pan item swiper-slide" id="cat'+catID+'" index="'+catID+'">'+""+'</div>';
	
	$("#mcomListing .mcomListing").append(catHTML);	
	
}

function getCatXML(catID){
	
	console.log("Enter addSubCategory");
	$('.appypie-loader').show();
        wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#generateCategoryXml";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><generateCategoryXml xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#generateCategoryXml\"><appId>'+appid+'</appId><parentId>'+catID+'</parentId></generateCategoryXml></soap:Body></soap:Envelope>';
        console.log("wsUrl_addSubCategory--->"+wsUrl);
        console.log("soapRequestAddSubCategory--->"+soapRequest);
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
			  
               console.log("addSubCategory_ALL "+ strJSON); //Cat list Response xml file for Tab
               parseSubCat(strJSON, catID);			   
               },
               error: function(response, textStatus, errorThrown)
               {
               console.log("ErrorVij: "+response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
}
/*
Fuction parse subcategory data
*/
var ifImageNA = '';
var catHTMLonHome = "";
function parseSubCat(strJSON, catID) {
	console.log("enterParseCat");
	var isDataAva = false;
	var catHTMLonHome1 = "";
	//$('.appypie-loader').show();
	$.ajax({
               url: strJSON,
               dataType: "text",
               crossDomain: true,
               cache: false,
               success: function(xml) {
              
               var xml =jQuery.parseXML(xml);
			   console.log("xmldata:"+ $(xml).find("appData").text());
			   console.log("xmldata2:"+ xml);
				catHTMLonHome = '';
				  catHTMLonHome1 += '<ul class="verticle">';				  
					$(xml).find("category").each(function (i) {										
						var categoryId= $(this).find("categoryId").text();
						var categoryName= $(this).find("categoryName").text();
						var categoryImage= $(this).find("categoryImage").text();
						console.log("categoryId "+ categoryId);
						console.log("categoryName "+ categoryName);
						console.log("categoryImage "+ categoryImage);
						if(categoryImage.length > 1) 
							ifImageNA = categoryImage;
						else
							ifImageNA="images/no-image.png"; // changes by krishna
					
						console.log("ifImageNA: "+ ifImageNA);
						isDataAva = true;
					catHTMLonHome1 += '<li><a onclick="openEcomListPage(\''+categoryId+'\',\''+categoryName+'\',\''+ifImageNA+'\')"><img style="background-image:url('+ifImageNA+')" src="images/ecom-image2x1.png" alt=""/><small>'+categoryName+'</small></a></li>';						
						
					});
				   catHTMLonHome1 += '</ul>'	
						   
				   setTimeout(function(){
						$('.appypie-loader').hide();
						console.log("enterParseCat3: "+ isDataAva);
						if(isDataAva) {
								getCategoriesProductsEcom(catID);
								catHTMLonHome += catHTMLonHome1;
								
							
						} else {
							getCategoriesProductsEcom(catID);
						var tmpmsg = '<center style="margin:100px 0 0 0"><h3 style="color:black;"></h3></center>';
								$("#cat" + catID).html("").append(tmpmsg);	
						}
						
					},500);
				   
               },
               error: function(XMLHttpRequest, textStatus, errorThrown) {
				   console.log('parseSubCat fail');
				   setTimeout(function(){$('.appypie-loader').hide();},1000);
				   console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
               }
               });
	
	
	//if ($('#element').is(':empty')){
  //do something
	//}
	
}

function getCategoriesProductsEcom(catID) {
	$('.appypie-loader').show();
	console.log("Enter listOfCategoryProductXml");
        wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#listOfCategoryProductXml";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><listOfCategoryProductXml xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#listOfCategoryProductXml\"><appId>'+appid+'</appId><catId>'+catID+'</catId></listOfCategoryProductXml></soap:Body></soap:Envelope>';
        console.log("wsUrl_listOfCategoryProductXml--->"+wsUrl);
        console.log("soapRequestlistOfCategoryProductXml--->"+soapRequest);
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
				
               console.log("listOfCategoryProductXml_ALL "+ strJSON); //Cat list Response xml file for Tab
               parseCatProducts(strJSON, catID);
				setTimeout(function(){$('.appypie-loader').hide();},1000);			   
               },
               error: function(response, textStatus, errorThrown)
               {
               console.log("ErrorVij: "+response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
	
}
  
var ifImageNA2 = '';
function parseCatProducts(strJSON, catID) {
	console.log("enterParseCat");
	
	var catHTMLonHome2 = '';
	$.ajax({
               url: strJSON,
               dataType: "text",
               crossDomain: true,
               cache: false,
               success: function(xml) {
              
               var xml =jQuery.parseXML(xml);
			   console.log("xmldata:"+ $(xml).find("appData").text());
			   console.log("xmldata2:"+ xml);
				  //catHTMLonHome += '<ul class="verticle">';				  
					$(xml).find("product").each(function (i) {										
						var productId= $(this).find("productId").text();
						var productName= $(this).find("productName").text();
						var productImage= $(this).find("productImage").text();
						var price= $(this).find("price").text();
						var currency= $(this).find("currency").text();
						var customOption= $(this).find("customOption").text();
						
						console.log("productIdInCat "+ productId);
						//console.log("productNameInCat "+ productName);
						console.log("customOption "+ customOption);
						if(productImage.length > 1) 
							ifImageNA2 = productImage;
						else
							ifImageNA2="images/no-image.png"; // changes by krishna
					
						console.log("ifImageNA: "+ ifImageNA2);
						
				if(customOption>0)
                  {
                 catHTMLonHome2 += '<div class="product_box"><img style="background-image:url('+ifImageNA2+')" src="images/ecom-image.png" alt="" onclick="openEcomDetailPage('+productId+')"/>\
				<div class="discription_box">\
				<p class="title">'+productName+'</p>\
				<p class="description" id="pDiscription" style="display:none">'+""+'</p>\
				<div class="product_price"><big>'+currency+price+'</big></div>\
				<div class="product_price_buttons"><a href="#" onclick="openEcomDetailPage('+productId+');" class="addtoCart">+ Add</a></div>\
				</div></div>';
                           }
               else
                {
                 catHTMLonHome2 += '<div class="product_box"><img style="background-image:url('+ifImageNA2+')" src="images/ecom-image.png" alt="" onclick="openEcomDetailPage('+productId+')"/>\
				<div class="discription_box">\
				<p class="title">'+productName+'</p>\
				<p class="description" id="pDiscription" style="display:none">'+""+'</p>\
				<div class="product_price"><big>'+currency+price+'</big></div>\
				<div class="product_price_buttons"><a href="#" onclick="addTocartEcommorce(\''+productId+'\',\''+productName+'\',\''+ifImageNA2+'\',\''+price+'\',\''+currency+'\');" class="addtoCart">+ Add</a></div>\
				</div></div>';
                           }
						

								
						
					});
						
					catHTMLonHome += catHTMLonHome2;
					$("#cat" + catID).html("").append(catHTMLonHome);
					console.log("Product_appended");
					
				   
               },
               error: function(XMLHttpRequest, textStatus, errorThrown) {
				   console.log('parseSubCat fail');
				   setTimeout(function(){$('.appypie-loader').hide();},1000);
				   console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
               }
               });
}  
/*
END CAT AND PRODUCT LISTING
*/

function openEcomListPage(id,categoryName, catImage)
{
	//closeSubMenu(this);
	if(window.sessionStorage.getItem("ecomcart") == null || window.sessionStorage.getItem("ecomcart") == undefined) {
			//window.sessionStorage.setItem("ecomcart",0);
			window.sessionStorage.setItem("ecomcartQuantitywise", 0);
		}
	var tmpHeader = '<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
  <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
  <h1 class="ui-title">'+appName+'</h1>\
  <a class="localHeaderIconRight" onclick="ecomCart_View()"; id="localEcomCart" style="display:block"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>';
  
	var ecomProducts_HTML = '';
	var topSlider='';	
	document.getElementById("left-navs").style.display = 'block';
    // hide the lorem ipsum text
    document.getElementById("left-navs").style.display = 'none';
	console.log("catImage: "+ catImage);
	console.log("enter openEcomListPage");
    if(!checkNetworkConnection())
    {
    }else
    {
        $('.appypie-loader').show();
        if(window.sessionStorage.getItem("ecomcheckoutFlag"))
        {
            var checkDisp="block";
        }
        else
        {
            var checkDisp="none";
        }
        
        $("#mainbackfoodecom").show();
        $("#reload").hide();
		//$("#logo").show();
		$("#mainback").show();
		//$("#shopmenu").hide();
		//$("#bookmark").hide();
		//$("#reload").show();
        window.sessionStorage.setItem("ecomcategoryid",id);
        window.sessionStorage.setItem("ecomcategoryName",categoryName);
       
	   
		// changes by krishna
		var topCatName = '<h3 id="product_pageHeader" style="display:none;">'+categoryName+'</h3>';
		
		
		console.log("fProductID size: "+ fProductID.length+", "+ fProductImage[0]);	
		
		if(catImage) {
			topSlider = '<div class="slider-mcom category-head" id="categorySlider" data-snap-ignore="true">\
						<div class="item" style="background-image:url('+catImage+')"><img src="images/ecom-image2x1.png" alt=""/><span>'+categoryName+'</span></div>\
						</div>';
						
			
		} else {
			topSlider = '';
			
		}
		
		// changes by krishna
		if(window.sessionStorage.getItem("ecomcart") == null || window.sessionStorage.getItem("ecomcart") == undefined) {				
			//window.sessionStorage.setItem("ecomcart",0);		
			window.sessionStorage.setItem("ecomcartQuantitywise", 0);		
		}
		
		ecomProducts_HTML += topCatName;
		ecomProducts_HTML += topSlider;
	ecomProducts_HTML +='<div class="mCom_content_wrapper mcom_list" data-snap-ignore="true">';		
	//ecomProducts_HTML +='<div class="product-button"></div>';
		
	
		var topSortBy = '<div class="top_toolBar" id="topfilter">\
						<p>'+sortby_mcom+'</p>\
						<div class="cat">\
							<select id = "searchpopup" onchange="showcatEcom()" data-role="none">\
								<option>'+popularityg_mcom+'</option>\
								<option>'+pricehighlow_mcom+'</option>\
								<option>'+pricelowhigh_mcom+'</option>\
							</select>\
						</div>\
						</div>';
		ecomProducts_HTML += topSortBy;
		console.log("ProductCount: "+ pIdforSorting.length);
		//ecomProducts_HTML += '<div class="checkOut-Btn" id="checkoutbtn" style="display:'+checkDisp+';" onclick="ecomCart_View()">CheckOut</div>\
		ecomProducts_HTML += '\
		<div class="cat-option"></div>\
		<div class="food_pro_list"></div></div>'
     
        appendHtml(ecomHeader+'<div class="dharm" style="height:calc(100% - 46px); -webkit-height:calc(100% - 46px); -webkit-overflow-scrolling: touch; overflow-y:scroll;">'+ecomProducts_HTML,2,2,'food');	
		resetEcomHeader();
		console.log("ecomcategoryid: " +window.sessionStorage.getItem("ecomcategoryid"));
        showEcomProducts(window.sessionStorage.getItem("ecomcategoryid"));
		$("#product_pageHeader").hide();


		$('#categorySlider' + id).owlCarousel({
			singleItem : true,
			nav:true
			});
		/*setTimeout(function(){
		$('#categorySlider' + id).owlCarousel({
			singleItem : true,
			nav:true
			});
		}, 500)	*/	

    }
}

/*
SEARCH POPUP LIST(DROP-DOWN)
*/

function cleanArray(actual) {
  var newArray = new Array();
  for (var i = 0; i < actual.length; i++) {
    if (actual[i]) {
      newArray.push(actual[i]);
    }
  }
  return newArray;
}

function showcatEcom(catname)
{
    
    console.log('the value of session storage in show cat function--->'+sessionStorage.getItem(document.getElementById("searchpopup").value));
    console.log(document.getElementById("searchpopup").value);
	//alert(document.getElementById("searchpopup").value);
	var sortInpSelected = '';
	var tmpVar = '';
	sortInpSelected = document.getElementById("searchpopup").value;
	if(sortInpSelected == "Price High-Low") {
		tmpVar = 'pricehightolow';
		showEcomProducts(window.sessionStorage.getItem("ecomcategoryid"),tmpVar);
	} else if(sortInpSelected == "Price Low-High") {
		tmpVar = 'pricelowtohigh';
		showEcomProducts(window.sessionStorage.getItem("ecomcategoryid"),tmpVar);
	} else {
		tmpVar = 'popularity';
		showEcomProducts(window.sessionStorage.getItem("ecomcategoryid"),tmpVar);
	}
    
}

/*
END
*/
/*********Product Listing Page**************/
/*
//Display second click data and also manage sort by product
*/
function showEcomProducts(catid, sortby) 
{
	//alert(catid)
	//console.log("catid: " +catid);
	//console.log("sortby: " +sortby);
    if(!checkNetworkConnection())
    {
    }else
    {
        var wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#ecommSubCategoryProductXml";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommSubCategoryProductXml xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#ecommSubCategoryProductXml\"><appId>'+appid+'</appId><parentId>'+catid+'</parentId><sortby>'+sortby+'</sortby></ecommSubCategoryProductXml></soap:Body></soap:Envelope>';
        console.log("showEcomProducts: "+ soapRequest);
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
               
               var strJSON = $(req.responseText).find("return").text();
               console.log("ShowEcomProducts: "+strJSON);
               parseShowEcomProducts(strJSON);
			   
               },
               error: function(response, textStatus, errorThrown)
               {
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
    }
}


function parseShowEcomProducts(xml)
{
  
    $.ajax({
           url: xml,
           dataType: "text",
           cache: false,
           crossDomain: true,
           success: function(xmll) {
           var xml =jQuery.parseXML(xmll);
           var tcount=0;
           var ccount=0;
		   
		   var categorieslist='<select id="cat_list" onchange="showcatEcom()"><option>all</option>';
							
           var totalCount=0;
           var productlist='';
           $(xml).find("subcategory").each(function () {
                                           var subcatName= $(this).find("subcatName").text();
                                          categorieslist+='<option value="'+subcatName+'">'+subcatName+'</option>';
										   
                                           totalCount++;
                                           var $list = $(this);
                                           var sub_cat='';
                                           $list.find("product").each(function (i) {

																	 
                                                      var ptitle= $(this).find("ptitle").text();
													    pTitleforSorting[i]=ptitle;
																	  
                                                                      var id= $(this).find("id").text();
														pIdforSorting[i] = id;
                                                                      var productImage= $(this).find("productImage").text();
														pImageforSorting[i] = productImage;
                                                                      var description= $(this).find("description").text();
														pDescriptionforSorting[i] = description;
                                                                      var currency= $(this).find("currency").text();
                                                                      var price= $(this).find("price").text();
														productPriceforSorting[i]=price;
														
																	  
                                                                      var status= $(this).find("status").text();
                                                                     // if(ptitle.length>20) {
                                                                    //  ptitle=ptitle.substring(0,20);
                                                                    //  }
                                                                      if(description.length>20) {
                                                                      description=description.substring(0,20);
                                                                      }
                                                                      ccount=0;
                                                                      if(status=="1")
                                                                      {
                                                                      ccount=ccount+1;
                                                                      tcount=tcount+1;

//<div class="product_price"><big>'+currency+price+'</big> //<small>'+currency+price+'</small></div>\

	var productBox = '<div class="product_box"><img style="background-image:url('+productImage+')" src="images/ecom-image.png" alt="" onclick="openEcomDetailPage('+id+')"/>\
	<div class="discription_box">\
	<p class="title">'+ptitle+'</p>\
	<p class="description" id="pDiscription" style="display:none">'+description+'</p>\
	<div class="product_price"><big>'+currency+price+'</big></div>\
	<div class="product_price_buttons"><a href="#" onclick="addTocartEcommorce(\''+id+'\',\''+ptitle+'\',\''+productImage+'\',\''+price+'\',\''+currency+'\');" class="addtoCart">+ Add</a></div>\
	</div></div>';
	
	
	sub_cat +=productBox;
	productlist +=productBox;
	
                                                                      }
                                                                      if(ccount>0)
                                                                      {
                                                                      sessionStorage.setItem(subcatName,sub_cat);
                                                                      }
                                                                      else
                                                                      {
                                                                    	 
                                                                      var msg='<center><b><h2>'+msgg_mcom+'</h2></b><br/></center>';
                                                                      sessionStorage.setItem(subcatName,msg);
                                                                      
                                                                      }
                                                                      //console.log(ptitle);
                                                                      });
                                           
                                           });
           categorieslist+='</select></div>\
						</div>';
						
           $(".cat-option").empty();
           $(".cat-option").append(categorieslist);
           
           $(".food_pro_list").empty();
           
           if(tcount>0) {
           $(".food_pro_list").append(productlist);
		    $("#topfilter").show();
		   }
           else {
           $(".food_pro_list").append('<center><b><h2>'+msgg_mcom+'</h2></b><br/></center>');
		   $("#topfilter").hide();
           console.log("total count of the categoreies under food product page");
		   }
           if(totalCount > 1)
           {
           $(".cat-option").show();
           
           }
           else
           {
           $(".cat-option").hide();
           }
           
           if(tcount>0)
			//$("#topfilter").show();
           sessionStorage.setItem("all",productlist);
           else
           sessionStorage.setItem("all",'<center><b><h2>'+msgg_mcom+'</h2></b><br/></center>');
           //$("#topfilter").hide();
           setTimeout(function(){ $('.appypie-loader').hide();},1000);
           },
           error: function(XMLHttpRequest, textStatus, errorThrown) {
           console.log('fail');
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
           
           }
           });
    
}

/* Product Details Page */
function openEcomDetailPage(id,value)
{
	console.log("layVijay: "+ window.localStorage.getItem("layout"));
	
		if(window.sessionStorage.getItem("ecomcart") == null || window.sessionStorage.getItem("ecomcart") == undefined) {
			//window.sessionStorage.setItem("ecomcart",0);
			window.sessionStorage.setItem("ecomcartQuantitywise", 0);
		}
	var tmpHeader = '<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
	  <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
	  <h1 class="ui-title">'+appName+'</h1>\
	  <a onclick="ecomCart_View()"; class="localHeaderIconRight" id="localEcomCart" style="display:block"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>';
	
	
	
	if(window.localStorage.getItem("layout")=="bottom") {
		$("div[class^=app_navigation_bottom]").hide();
            window.localStorage.setItem("bottomHide","ture");
        }
	
    if(!checkNetworkConnection())
    {
    }else
    {
        //$('.appypie-loader').show();
        //$("#mainbackfoodecom").show();
		$("#reload").removeClass("active");
        $("#reload").hide();
        if(sessionStorage.getItem("ecomcheckoutFlag"))
        {
            var checkDisp="block";
        }
        else
        {
            var checkDisp="none";
        }
		     
		ecomproductDetail_HTML='<div class="product-detail scrolling-bottom">\
	<div class="product-image"></div>\
	<div class="product-button">\
    	<a href="#" class="add-cart" id="addtocartid" onclick="addToCartEcom()"></a>\
    </div>\
    <div class="wrap">\
        <div class="product-description">\
            <h3 id="productName"></h3>\
			<p class="price"></p>\
			<p class="stok"></p>\
			<p class="description" id="pDiscription"></p>\
        </div>\
        <ul class="product-attribute" id="configurableProduct"></ul>\
    </div>\
    \
</div><footer id="footer-nav">\
	<a href="#" onclick="addLikes(\''+id+'\', this);" class="like"><span></span></a>\
	<a href="#" onclick="showReviewList(\''+id+'\', this);" class="comment"><span></span></a>\
	<a href="#" onclick="showMore(this)" class="more"><span></span></a>\
</footer>';
     //addLikes(appId,productId,email,liketype)  openEcomDetailPage('+id+') 
       
		
//ecomproductDetail_HTML += ecomSubMenu();
		
		console.log(value+" "+id);
        localStorage.setItem("EcomProductId",id); //for future use
        
        showEcomProductdetails(id, value, tmpHeader);
		
    }
}
//var tmpVar = false;
function showMore(obj) {
	console.log("tmpVar: "+ tmpVar);
	/*
	if(tmpVar){ //class="more"
		console.log("if");
		tmpVar = false;
		console.log("obj: "+ obj);
	 $('#contentHolder3').animate({
        scrollTop: 50
    }, "slow");	
	*/
	
	var alltext = sessionStorage.getItem("ecomproductdescription");
	$(".product-detail").find(".description").html(alltext);
	if($(obj).is(".less"))
	{
		$(obj).removeClass("less");
		$(".product-detail").find(".description").addClass("less");
	}
	else{
		$(obj).addClass("less");
		$(".product-detail").find(".description").removeClass("less");		
	}
	
	//return false;
	/*
	//$(".product-detail").find(".description").html(cutString(alltext,50));
	$(".product-detail").find(".description").html("");//.append(cutString(alltext,50));
	$(".product-detail").find(".description").html(alltext.substr(0, 200));
	} else {
		console.log("else");
		tmpVar = true;
		$('#contentHolder3').animate({
        scrollTop: 200
    }, "slow");	
	var alltext = sessionStorage.getItem("ecomproductdescription");
	$(".product-detail").find(".description").html(cutString(alltext,5000));
	}*/
	
}
/**************Product Details Page*******************/
function showEcomProductdetails(productid, val, tmpHeader)
{
	$('.appypie-loader').show();
	$("#mainbackfoodecom").show();
	console.log("emailId: "+ window.localStorage.getItem("appUserEmail"));
    wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#productDetailXml";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><productDetailXml xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#productDetailXml\"><appId>'+appid+'</appId><emailId>'+window.localStorage.getItem("appUserEmail")+'</emailId><id>'+productid+'</id></productDetailXml></soap:Body></soap:Envelope>';
    console.log('soapRequest--->'+soapRequest);
    
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
			   console.log('showEcomProductdetails--->'+strJSON);
			   showEcomDetail(strJSON, val, tmpHeader);
           },
           error: function(response, textStatus, errorThrown)
           {
			   setTimeout(function(){$('.appypie-loader').hide();},1000);
			   console.log(response);
			   console.log(textStatus);
			   console.log(errorThrown);
           }
           });
    
}

var option_id=new Array();
var option_title=new Array();
var option_req=new Array();
var saveWishList = '';
function showEcomDetail(strJSON, val, tmpHeader)
{
	var total_qty = '';
	
    if(!checkNetworkConnection())
    {
    }else
    {
        $.ajax({
               url: strJSON,
               dataType: "text",
               crossDomain: true,
               cache: false,
               success: function(xml) {
               
               var xml =jQuery.parseXML(xml);
               html='<ul>';
               option_id.length=0;
               option_title.length=0;
               option_req.length=0;
               var option_sort=new Array();
               var option_row=new Array();
               
               var dropdownHtml='';
			   
			  
			   
               var prodWeightValue="";
               $(xml).find("product").each(function () {
				   var productId= $(this).find("productId").text();
				   var productName= $(this).find("productName").text();
				  /* if(productName.length>20) {
						productName=productName.substring(0,20);
				   }*/
				   var catName= $(this).find("catName").text();
				   var price= $(this).find("price").text();
				   var currency= $(this).find("currency").text();
				   window.sessionStorage.setItem("currency",currency);
				   var discount= $(this).find("discount").text();
				   var productDescription= $(this).find("description").text();
				   var productImage= $(this).find("productImage").text();
				   var quantity= $(this).find("quantity").text();
                   var wishlist = $(this).find("wishlist").text();
                   var productweight_value= $(this).find("weight_value").text();						saveWishList = wishlist;
					total_qty = quantity;
					/*for (var i = 1; i <= quantity; i++) {
								Quantity.push(i);
						}*/
						   
                                   if($(this).find("customoptions").find("option").text())
                                           {
											   $(this).find("customoptions").find("option").each(function (optionIndex) {                                                                                             
													 option_id.push($(this).find("option_id").text());
													 option_title.push($(this).find("option_title").text());
													 option_req.push($(this).find("option_req").text());
													 option_sort.push($(this).find("option_sort").text());
													 option_row.push($(this).find("option_row").text());
												
													 dropdownHtml+='<li><span>'+$(this).find("option_title").text()+':-</span><select id="'+$(this).find("option_id").text()+'" onchange="checkConfigProduct();"><option value="select">'+selectg_mcom+'</option>';
												
													 
													 var jsonObj=jQuery.parseJSON($(this).find("option_row").text());
													 //console.log('jsonObj 1');
													 for(var i=0;i<jsonObj.length;i++)
													 {
													 
														 var row_title=jsonObj[i].row_title;
														 //console.log('jsonObj 2');
														 var row_price=jsonObj[i].row_price;
														 //console.log('jsonObj 3');
														 var row_pricetype=jsonObj[i].row_pricetype;
														// console.log('jsonObj 4');
														 if(row_pricetype=='m')
														 {
															 row_price='-'+row_price.trim();
														 }
														 else if(row_pricetype == '')                                                                                             {
															 row_price='+0';
														 }
														 else
														 {
															row_price='+'+row_price.trim();
														 }
													 //console.log('jsonObj 5');
													   var newPrice="";
                                        if(parseInt(jsonObj[i].row_price.trim())<1)
                                            {             
                                         newPrice=row_title;
                                               }
                                              else
                                          {
                                           newPrice=row_title+','+row_price.trim();
                                                }
              
													 
													 
													 //console.log('jsonObj 6');
													 dropdownHtml+='<option value="'+newPrice+'">'+newPrice+'</option>';
													 //dropdownHtml+='<option value="'+row_title+'">'+row_title+'</option>';
													 //thisLI.find("select").append('<option value="'+newPrice+'">'+newPrice+'</option>');
													 }
													 dropdownHtml+='</select></li>';
											});
                                           $("#configurableProduct").empty().append(dropdownHtml);
										   setTimeout(function(){ $('.appypie-loader').hide();},1000);
                                           }
                                           else
                                           {
                                           sessionStorage.setItem("ecomconfigProduct",'false');
                                           }
                                           //console.log('dropdownHtml---->'+dropdownHtml);
                                           var stock='';
                                           if(parseInt(quantity)>0) {
                                           	   stock="In Stock";
											   $("#food-quantity").show();
											   $("#addtocartButton").show();
                                           }
                                           else {
											   //deepak
											    $("#addtocartid").hide();
											  
                                               setTimeout(function(){ alertPopUp(sorryg_mcom,quantityoutofstock_mcom) },500);
											   $("#food-quantity").hide();
											   $("#addtocartButton").hide();
                                           }
                                           sessionStorage.setItem("ecomquantity",quantity);
                                           sessionStorage.setItem("ecomproductId",productId);
                                           sessionStorage.setItem("ecomproductName",productName);
                                           sessionStorage.setItem("ecomprice",price);
                                           sessionStorage.setItem("ecomproductImage",productImage);
                                           sessionStorage.setItem("ecomproductdescription",productDescription);
                                           sessionStorage.setItem("ecomproductweight_value",productweight_value);
                                           $("#productName").empty().append(productName);
										  

										   var proDetail = $(".product-detail");
										   proDetail.find(".product-image").attr("id", "productImgSlider").html("");
										   
										   for(var i=0; i<1; i++)
										   {
											   proDetail.find(".product-image").append('<div class="item" onclick="pinchZoom(this)" data-image="'+productImage+'"><img style="background-image:url('+productImage+')" src="images/ecom-image2x1.png" alt=""/></div>');
											
										   }
										   
										   proDetail.find(".description").html(cutString(productDescription,30));
										   proDetail.find(".price").html(currency + '<span id="productPrice">'+price+'</span>');
                                           prodWeightValue=cutString(productweight_value,30);
										   //proDetail.find(".weight_value").html(cutString(productweight_value,30));										   proDetail.find(".stockid").html(stock);
										
                     });
					 
				//var Quantity2 = ['1', '2', '3', '4', '5'];
					
			   //$(".product-attribute").prepend('<li><span>Qty:</span><select data-role="none" id="quantity"></select></li>')
			   console.log("total_qty: "+ total_qty);
			   
			   $(".product-attribute").prepend('<li><span>Qty:</span><input type="number" data-role="none" value= "1" onKeyUp="limit(this.value ,\''+total_qty+'\' );"" id="quantity" style="border: 1px solid;"><p class="weight_value">'+prodWeightValue+'</p></li>');
			  // $(".product-attribute").prepend('<li><span>Qty:</span><input type="number" data-role="none" value= "1" onKeyPress="return ( this.value < '+total_qty+' );"" id="quantity"></li>');
			   /*
			   if (input.value < 0) input.value = 1;
				if (input.value > total_qty) input.value = total_qty;
			   */
			   /*$.each(Quantity2, function( index, value ) {
				   console.log("QuantityValue: "+ value);
  					$(".product-attribute").find("select").eq(0).append('<option value="'+value+'">'+value+'</option>')
				});*/
			   
			   
/*$("#quantity").change(function() {
	var inputValue=$(this).val();
	if(parseInt(inputValue) >= parseInt(sessionStorage.getItem("ecomquantity")))
	{
		document.getElementById("quantity").value = sessionStorage.getItem("ecomquantity");
		inputValue=sessionStorage.getItem("ecomquantity");
		$(this).val(inputValue)
	}
	//$("#slider").slider("value",parseInt(inputValue));
});*/
			setTimeout(function(){ $('.appypie-loader').hide();},5000);
					

			$('#productImgSlider').owlCarousel({
				singleItem : true,
				nav:true
			});
			
			 console.log("saveWishList: "+ saveWishList);
			   if(saveWishList == 1)
			   $(".like").addClass("on");
				 else
					 $(".like").removeClass("on");
					
               },
               error: function(XMLHttpRequest, textStatus, errorThrown) {
				   console.log('fail');
				   console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
				   setTimeout(function(){ $('.appypie-loader').hide();},1000);
               }
               });
			   
    }
	
	//setTimeout(function(){ alert("Hello"); }, 3000);
	   if(val=="true")
        {
        	 appendHtml(ecomHeader+ecomproductDetail_HTML,2,2,'food');
        }
        else
        {
        	 appendHtml(ecomHeader+ecomproductDetail_HTML,3,3,'food');
            
        }
	resetEcomHeader();
}

function cutString(text, wordsToCut){    
            var wordsArray = text.split(" ");
            if(wordsArray.length>wordsToCut){
                var strShort = "";
                for(i = 0; i < wordsToCut; i++){
                    strShort += wordsArray[i] + " ";
                }   
                return strShort+"...";
            }else{
                return text;
            }
         };

function checkConfigProduct()
{
    
    var total=0;
    for(var x=0;x<option_id.length;x++)
    {
        var selectedIndex=unescape(document.getElementById(''+option_id[x]).value);
        var value=selectedIndex.split(',');
        console.log('selectedIndex---->'+selectedIndex);
        if(selectedIndex != 'select')
        {
            console.log("value---->"+value[1]);
            var addedPrice=0;
            if(value[1])
            {
                
                addedPrice=parseFloat(value[1]);
                //console.log("addedPrice---->>>"+addedPrice);
                total=total+parseFloat(addedPrice);
            }
            else
            {
                total=total;
                //console.log("total---->>>"+total);
            }
        }
    }
    if(sessionStorage.getItem("ecomprice"))
    {
        var currentPrice=sessionStorage.getItem("ecomprice");
    }
    else
    {
        var currentPrice=sessionStorage.getItem("price");
    }
    console.log("currentPrice--->"+currentPrice);
    
    var newPrice=parseFloat(currentPrice) + parseFloat(total);
    //  sessionStorage.setItem("ecomNewPrice",newPrice);
    console.log('price---->'+newPrice);
    
    $('#productPrice').empty();
    $('#productPrice').append(parseFloat(newPrice).toFixed(2));
    
}

/*
Fuction add to cart
*/

function addTocartEcommorce(pId, pName, pImage, pPrice, pCurrency) {
	console.log("AddCartData: "+ pId +" ,"+ pName+" ,"+pImage+" ,"+pPrice+" ,"+pCurrency);
	$("#reload").removeClass("active");
	$("#reload").hide();
	var price=parseFloat(pPrice);
	localStorage.setItem("EcomProductId",pId);
	
		var productId=pId;
        var productName=pName;
       
		var quantity= 1;
        var productImage=pImage;
        var productdescription=pName;
        window.sessionStorage.setItem('currency',pCurrency);
		console.log('cart_add price--->'+price);
	
	
	
	
    if(parseFloat(price) > 0)
    {
        $('.appypie-loader').show();
        var configTitle='';
        var sendData='';
        if(option_id.length>0)
        {
            for(var x=0;x<option_id.length;x++)
            {
                var selectedIndexnew=document.getElementById(''+option_id[x]).value;
                var valueOfselectedIndex=selectedIndexnew.split(',');
                console.log("selectedIndexnew--->"+selectedIndexnew+"<----option_req[x]--->"+option_req[x]);
                if(selectedIndexnew == 'select' && parseFloat(option_req[x]) == 1)
                {
                    setTimeout(function(){ $('.appypie-loader').hide();},1000);
                   
                    navigator.notification.alert(pleaseselectg_mcom + option_title[x], setTimeout, errorg_mcom, okg_mcom);
                    return false;
                }
                else
                {
                    console.log("in else selectedIndexnew--->"+selectedIndexnew+"<---option_req[x]--->"+option_req[x]);
                    configTitle=configTitle.trim();
                    if(x!=0 && configTitle.length > 0)
                    {
                        if(selectedIndexnew != 'select')
                        {
                            configTitle=configTitle+','+option_title[x];
                            sendData=sendData + ',' + valueOfselectedIndex[0];
                        }
                        
                    }
                    else
                    {
                        
                        if(selectedIndexnew == 'select')
                        {
                            configTitle='';
                            sendData='';
                        }
                        else
                        { configTitle=option_title[x];
                            sendData=valueOfselectedIndex[0];
                            
                        }
                    }
                }
            }
        }
        else
        {
            configTitle='';
            sendData='';
        }
		
        console.log("productName---->"+productName+"---configTitle---->"+configTitle+"----sendData---->"+sendData);
        //console.log("the product price is ---->"+price+'-----quantity----->'+quantity);
        
        var cart;
        var flag=0;
        if(sessionStorage.getItem("ecomcart"))
        {
            cart=sessionStorage.getItem("ecomcart");
            cart=parseInt(cart)+1;
            
            for (var x=1; x<cart; x++)
            {
				if(sessionStorage.getItem("ecomproductId"+x)==productId && sessionStorage.getItem("ecomstatus"+x)=="1" && sessionStorage.getItem("ecomconfigValueProduct"+x)==sendData)
                {
                    console.log('in if condition ecomconfigTitleProduct--->'+sessionStorage.getItem("ecomconfigValueProduct"+x)+'===='+configTitle);
                    if(parseInt(quantity) > parseInt(sessionStorage.getItem("ecomquantity")))
                    {
                        var qty=window.sessionStorage.getItem("ecomquantity");
                        console.log("Cart Quantity" + qty);
                        qty=parseInt(qty);
                    }
                    else
                    {
                        var qty=sessionStorage.getItem("ecomquantity"+x);
                        qty=parseInt(qty)+parseInt(quantity);
                    }
                    window.sessionStorage.setItem("ecomquantity"+x,qty);
                    flag=1;
                }
                else
                {
                    flag=0;
                }
            }
            
            sessionStorage.setItem("ecomcart",cart);
        }
        else
        {
            window.sessionStorage.setItem("ecomcart",1);
            cart=1;
        }
        
        console.log("value of flag---->"+flag);
        
        if(flag==0)
        {
            window.sessionStorage.setItem("ecomproductId"+cart,productId);
            window.sessionStorage.setItem("ecomquantity"+cart,quantity);
            window.sessionStorage.setItem("ecomproductName"+cart,productName);
            window.sessionStorage.setItem("ecomprice"+cart,price);
            window.sessionStorage.setItem("ecomproductImage"+cart,productImage);
            window.sessionStorage.setItem("ecomproductdescription"+cart,productdescription);
            window.sessionStorage.setItem("ecomstatus"+cart,"1");
            sessionStorage.setItem("ecomconfigTitleProduct"+cart,configTitle);
            sessionStorage.setItem("ecomconfigValueProduct"+cart,sendData);
            window.sessionStorage.setItem("ecomflag","1");
            sessionStorage.setItem("ecomAvailquantity"+cart,sessionStorage.getItem("ecomquantity"));
            // sessionStorage.removeItem("ecomquantity");
        }
        else
        {
            cart=cart-1;
            sessionStorage.setItem("ecomcart",cart);
        }
        if(!checkNetworkConnection())
        {
        }else
        {
            window.sessionStorage.setItem("DeleteCalled","false");
           
			setTimeout(function(){
				ecomCart_View();
			}, 500)
        }
    }
}


/*
Click on (+) to add products in basket
*/
function addToCartEcom()
{
var quantity=parseInt(document.getElementById("quantity").value);
		
		if(quantity=='0')
		{
		alert("Please fill quantity with non zero integer");
		
		return;
		}
	$("#reload").removeClass("active");
	$("#reload").hide();
    var price=parseFloat(document.getElementById('productPrice').innerHTML);
    console.log('cart_add price--->'+price);
	
	
	
	
    if(parseFloat(price) > 0)
    {
        $('.appypie-loader').show();
        var configTitle='';
        var sendData='';
        if(option_id.length>0)
        {
            for(var x=0;x<option_id.length;x++)
            {
                var selectedIndexnew=document.getElementById(''+option_id[x]).value;
                var valueOfselectedIndex=selectedIndexnew.split(',');
                console.log("selectedIndexnew--->"+selectedIndexnew+"<----option_req[x]--->"+option_req[x]);
                if(selectedIndexnew == 'select' && parseFloat(option_req[x]) == 1)
                {
                    setTimeout(function(){ $('.appypie-loader').hide();},1000);
                    //navigator.notification.alert('Please select '+option_title[x], setTimeout, "Error", "OK");
                    navigator.notification.alert(pleaseselectg_mcom + option_title[x], setTimeout, errorg_mcom, okg_mcom);
                    return false;
                }
                else
                {
                    console.log("in else selectedIndexnew--->"+selectedIndexnew+"<---option_req[x]--->"+option_req[x]);
                    configTitle=configTitle.trim();
                    if(x!=0 && configTitle.length > 0)
                    {
                        if(selectedIndexnew != 'select')
                        {
                            configTitle=configTitle+','+option_title[x];
                            sendData=sendData + ',' + valueOfselectedIndex[0];
                        }
                        
                    }
                    else
                    {
                        
                        if(selectedIndexnew == 'select')
                        {
                            configTitle='';
                            sendData='';
                        }
                        else
                        { configTitle=option_title[x];
                            sendData=valueOfselectedIndex[0];
                            
                        }
                    }
                }
            }
        }
        else
        {
            configTitle='';
            sendData='';
        }
        
        var productId=window.sessionStorage.getItem("ecomproductId");
        
        
        var productName=sessionStorage.getItem("ecomproductName");
        
        console.log("productName---->"+productName+"---configTitle---->"+configTitle+"----sendData---->"+sendData);
        
        
		var quantity=parseInt(document.getElementById("quantity").value);
        //console.log("the product price is ---->"+price+'-----quantity----->'+quantity);
        var productImage=window.sessionStorage.getItem("ecomproductImage");
        var productdescription=window.sessionStorage.getItem("ecomproductdescription");
        
        var cart;
        var flag=0;
        if(sessionStorage.getItem("ecomcart"))
        {
            cart=sessionStorage.getItem("ecomcart");
            cart=parseInt(cart)+1;
            
            for (var x=1; x<cart; x++)
            {
				if(sessionStorage.getItem("ecomproductId"+x)==productId && sessionStorage.getItem("ecomstatus"+x)=="1" && sessionStorage.getItem("ecomconfigValueProduct"+x)==sendData)
                {
                    console.log('in if condition ecomconfigTitleProduct--->'+sessionStorage.getItem("ecomconfigValueProduct"+x)+'===='+configTitle);
                    if(parseInt(quantity) > parseInt(sessionStorage.getItem("ecomquantity")))
                    {
                        var qty=window.sessionStorage.getItem("ecomquantity");
                        console.log("Cart Quantity" + qty);
                        qty=parseInt(qty);
                    }
                    else
                    {
                        var qty=sessionStorage.getItem("ecomquantity"+x);
                        qty=parseInt(qty)+parseInt(quantity);
                    }
                    window.sessionStorage.setItem("ecomquantity"+x,qty);
                    flag=1;
                }
                else
                {
                    flag=0;
                }
            }
            
            sessionStorage.setItem("ecomcart",cart);
        }
        else
        {
            window.sessionStorage.setItem("ecomcart",1);
            cart=1;
        }
        
        console.log("value of flag---->"+flag);
        
        if(flag==0)
        {
            window.sessionStorage.setItem("ecomproductId"+cart,productId);
            window.sessionStorage.setItem("ecomquantity"+cart,quantity);
            window.sessionStorage.setItem("ecomproductName"+cart,productName);
            window.sessionStorage.setItem("ecomprice"+cart,price);
            window.sessionStorage.setItem("ecomproductImage"+cart,productImage);
            window.sessionStorage.setItem("ecomproductdescription"+cart,productdescription);
            window.sessionStorage.setItem("ecomstatus"+cart,"1");
            sessionStorage.setItem("ecomconfigTitleProduct"+cart,configTitle);
            sessionStorage.setItem("ecomconfigValueProduct"+cart,sendData);
            window.sessionStorage.setItem("ecomflag","1");
            sessionStorage.setItem("ecomAvailquantity"+cart,sessionStorage.getItem("ecomquantity"));
            // sessionStorage.removeItem("ecomquantity");
        }
        else
        {
            cart=cart-1;
            sessionStorage.setItem("ecomcart",cart);
        }
        if(!checkNetworkConnection())
        {
        }else
        {
            window.sessionStorage.setItem("DeleteCalled","false");
           
			setTimeout(function(){
				ecomCart_View();
			}, 500)
        }
    }
}
function ecomContinueCheckout(){
	
   	console.log("checkout ---->"+sessionStorage.getItem("ecomcheckoutFlag"));
	//console.log("editoncart: " +document.getElementById("editoncart").innerHTML);
	//console.log("editoncart2: " +document.getElementById("editoncart"));
    if(parseInt(sessionStorage.getItem("ecomcheckoutFlag")) > 0){
        $('.appypie-loader').show();
       
            if(document.getElementById("editoncart").innerHTML=="Done"){
                setTimeout(function(){ $('.appypie-loader').hide();},1000);
				console.log("0");
				//alert("0");
				ecomcheckoutView();
            }
            else {
				console.log("1");
				//alert("2");
                ecomcheckoutView();
                setTimeout(function(){ $('.appypie-loader').hide();},1000);
            }
        
    }
    else
    {
		//console.log("2");
		//alert("3");
        setTimeout(function(){ $('.appypie-loader').hide();},1000);
    }    
}

function goHomeEcom(){
    
    
    if(window.localStorage.getItem("layout")=="bottom") {
        $("div[class^=app_navigation_bottom]").show();
        window.localStorage.setItem("bottomHide","false");
    }
    
    
    window.sessionStorage.setItem("EcomSubMenu","true");
    
    
    if(window.localStorage.getItem("layout")=="slidemenu") {
        $("#mainmenu").empty();
        getIndexData();
    }
    else
    {
        getEcommerceData(sessionStorage.getItem("ecommerceIndex"));
    }
}

/* Cart View */

function ecomCart_View(getTmp) {
	//closeSubMenu(this);
	//$("#reload").removeClass("active");
	//$("#reload").hide();
	document.getElementById("left-navs").style.display = 'block';
    // hide the lorem ipsum text
    document.getElementById("left-navs").style.display = 'none';
    //$('.appypie-loader').show();
	window.sessionStorage.setItem("Fromecomcart", "true");
	var headerTmp = '<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
      <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
      <h1 class="ui-title">'+appName+'</h1>\
      <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>';
	
	
	
    if(window.sessionStorage.getItem("ecomDeleteCalled")=="true") {
				
    }
    else{
        
    }
 

var ecomCart_HTML='<div class="cart-page scrolling-bottom">\
				<h3 style="display:none">Cart</h3>\
				<div class="cart-MSG" id="cart-MSG1" style="display:none"><span class="er_close">x</span><div class="cart_success">'+productsuccessfullyaddedinyourcartg_mcom+'</div></div>\
				<div class="cart-MSG" id="cart-MSG2" style="display:none"><span class="er_close">x</span><div class="cart_error">'+quantityshouldbegreaterthanzerog_mcom+'</div></div>\
				<div class="checkOut-Btn" id="editoncart" onclick="showeditOnCartEcom()" style="height:0px; border:0px;">'+edit_mcom+'</div>\
				<div class="wrap">\
					<div id="user_tab1" class="cart-item"></div>\
					<div class="cart-details">\
					<p class="total" style="color:#333">     \
					'+Total_Payable_Amount+' <strong id="grandTotal"></strong>\
					</p>\
					<p class="coupen">     \
						<input type="text" placeholder="'+enter_your_coupon_code_if_you_have_one_mcom+'" data-role="none" id="couponCode" name="couponCode">\
						<input type="button" value="'+apply_coupon_code_mcom+'" class="apply" onClick="applyEcomCoupon()" data-role="none">\
					</p>\
					<div id="user_tab3" style="display:none">\
					<h4>'+payment_details_mcom+'</h4>\
					<ul class="pay-mobile-cart">\
						<li>Sub Total(+) <span id="ecomsubtotal"></span></li>\
						<li id="DeliveryDiv">Delivery Charge <span id="ecomdelivery"></span></li>\
						<li id="taxDiv">'+tax_mcom+' <span  id="ecomtax"></span></li>\
						<li id="discountDiv">'+discount_mcom+' <span id="ecomdiscount"></span></li>\
						<li id="couponDID" style="display:none;">Coupon <span id="ecomcoupon"></span></li>\
						<li>'+grand_total_mcom+' <span class="gtotal grand-total" id="ecomgtotal"></span></li>\
						<li>Sub Total <span>$598.00</span></li>\
						<li>'+discount_mcom+' <span>$22.00</span></li>\
						<li>Tip <span>$00.00</span></li>\
						<li>'+grand_total_mcom+' <span class="grand-total">$1598.00</span></li>\
					</ul>\
					</div>\
					<input type="button" value="'+continue_shopping_mcom+'" class="continue-shopping" style="background-color:'+primaryButtonBgColor+'" onclick="goToHomePage()">\
					<a class="couponsubmit_btn" id="coupons" onclick="showEcomCouponPage();" style="display:none">'+apply_coupon_code_mcom+'</a>\
					<div>\
				</div>\
			   </div>';
	//ecomCart_HTML += ecomSubMenu();
	appendHtml(ecomHeader+ecomCart_HTML,3,3,'food');
	resetEcomHeader();

    
	
	/*console.log("getStr: "+ getStr);
	if(getStr == 1) {
		appendHtml(ecomCart_HTML,3,3,'food');
		console.log("getStr If: ");
		$("#contentHolder4").append('<footer id="footer-nav"><a href="#" class="next" onclick="ecomContinueCheckout()">PROCEED TO PAY</a></footer>');
	} else {
		appendHtml(ecomCart_HTML,4,4,'food');
		console.log("getStr else: ");
		$("#contentHolder4").append('<footer id="footer-nav"><a href="#" class="next" onclick="ecomContinueCheckout()">PROCEED TO PAY</a></footer>');
	}*/

    if((window.localStorage.getItem("layout")=="bottom") || (window.localStorage.getItem("layout")=="slidemenu"))
	{
		console.log("layout: "+ window.localStorage.getItem("layout")=="bottom");
        if(window.localStorage.getItem("layout")=="bottom"){        	
            $("div[class^=app_navigation_bottom]").hide();
            window.localStorage.setItem("bottomHide","ture");
			//$("#contentHolder4").append('<footer id="footer-nav"><a href="#" class="next" //onclick="ecomContinueCheckout()">'+pay_now_mcom+'</a></footer>');
			$("#contentHolder3").append('<footer id="footer-nav"><a href="#" class="next" onclick="ecomContinueCheckout()">'+pay_now_mcom+'</a></footer>');
			//alert("3");
        }
		else
		{
		//slidemenu
			$("#contentHolder3").append('<footer id="footer-nav"><a href="#" class="next" onclick="ecomContinueCheckout()">'+pay_now_mcom+'</a></footer>');
			//alert("1");
        }        
        //documentHeightecom();
    }else{
       
		$("#contentHolder3").append('<footer id="footer-nav"><a href="#" class="next" onclick="ecomContinueCheckout()">'+pay_now_mcom+'</a></footer>');
		$("#footer-nav").show();
		//alert("2");
    }
    
  
    if(window.sessionStorage.getItem("clearCart")=='true') {
        window.sessionStorage.setItem("clearCart","false");
    }
    else {
        if(window.sessionStorage.getItem("flag")=="1")
        {
            setTimeout( function(){
                       $("#cart-MSG1").slideToggle();
                       }, 3000 );
            window.sessionStorage.setItem("flag","0");
        }
    }
	
    $('.appypie-loader').hide();
    ecommMiscTax();
	//bindecomCart();
    
}

function ecommMiscTax(pageName)
{
	console.log("pageName: "+ pageName);
    $('.appypie-loader').show();
    if(pageName == 'food')
    {
        var wsUrl = "http://"+resellerInitial+reseller+"/services/food-soap#foodMiscTaxJson";        
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodMiscTaxJson xmlns=\"http://'+resellerInitial+reseller+'/services/food-soap#foodMiscTaxJson\"><appId>'+appid+'</appId></foodMiscTaxJson></soap:Body></soap:Envelope>';
    }
    else
    {
        var wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#ecommMiscTaxJson";        
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommMiscTaxJson xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#ecommMiscTaxJson\"><appId>'+appid+'</appId></ecommMiscTaxJson></soap:Body></soap:Envelope>';
    }
    console.log("applyEcomCoupon() soapRequest--->"+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
			   
			   
			   //$("#contentHolder3").append(ecomCart_HTML);
			   
			   var strJSON = $(data).find("return").text();
			   console.log("strJSON--->ecommMiscTax()---->"+strJSON);
			   var obj = jQuery.parseJSON(strJSON);
			   for(i=0;i<obj.length;i++)
			   {
				   if(parseFloat(obj[i].status) > 0)
				   {
					   sessionStorage.setItem("taxType"+i,obj[i].taxType);
					   sessionStorage.setItem("taxAmount"+i,obj[i].taxAmount);
					   sessionStorage.setItem("taxRate"+i,obj[i].taxRate);
					   console.log("taxType--->"+obj[i].taxType);
				   }			   
			   }
			   sessionStorage.setItem("noOfTax",parseFloat(obj.length));
			   
				if(pageName=='food')
				{
					bindCartView();
					//alert("1");
				}
				else
				{
					bindecomCart();
					//alert("2");
				}
							
           },
           error: function(response, textStatus, errorThrown)
           {
					setTimeout(function(){$('.appypie-loader').hide();},1000);
					console.log("ecommMiscTax()---->"+response);
					if(pageName=='food')
					{
						bindCartView();
					}
					else
					{
						bindecomCart();
					}
			   }
           });
    
}


function documentHeightecom(){
    if(window.localStorage.getItem("layout")=="bottom") {var height=$("#contentHolder4").height()-5;}else{var height=$("#contentHolder4").height()-65;}
    $(".main-ecom-cart-div").css("height",height);
}
function deleteEcomFromCart(id)
{
	var tmp = "fromdelete";
    $('.appypie-loader').show();
    if(window.sessionStorage.getItem("ecomcart") )
    {
        cart=window.sessionStorage.getItem("ecomcart");
        for (var x=1; x<=parseInt(cart); x++)
        {
            var productId=window.sessionStorage.getItem("ecomproductId"+x);
            if(x==id)
            {
                window.sessionStorage.setItem("ecomstatus"+x,"0");
                //window.sessionStorage.setItem("cart",parseInt(cart)-1);
				
            }
        }
    }
    window.sessionStorage.setItem("ecomDeleteCalled","true");
    $('.appypie-loader').hide();
    ecomCart_View(tmp);

}

function onQuantityChange(productId)
{
    if(parseFloat(document.getElementById("qty"+productId).value)>0){
        //console.log("productId ---> onQuantityChange function in if condition-->"+parseInt(document.getElementById("qty"+productId).value));
        var inputValue=document.getElementById("qty"+productId).value;
        //document.getElementById("slider"+productId).value= inputValue;        
        if(sessionStorage.getItem("ecomAvailquantity"+productId))
        {
			
            if( parseInt(inputValue) > parseInt(sessionStorage.getItem("ecomAvailquantity"+productId)) )
            {
                document.getElementById("qty"+productId).value=(parseInt(sessionStorage.getItem("ecomAvailquantity"+productId)));
            }            
        }
    }
    else {
    	
    
        document.getElementById("qty"+productId).value=1;
        //console.log("productId ---> onQuantityChange function in else condition-->"+parseInt(document.getElementById("qty"+productId).value));
        $("#cart_message").removeClass('cart_success');
        $("#cart_message").addClass('cart_error');
        document.getElementById("cart_message").innerHTML=quantityshouldbegreaterthanzerog_mcom;
        $("#cart-MSG1").slideToggle();
        setTimeout( function(){
			$("#cart-MSG1").slideToggle();                   
			$("#cart_message").removeClass('cart_error');
			$("#cart-MSG1").addClass('cart_success');
			document.getElementById("cart_message").innerHTML=pproductsuccessfullyaddedinyourcartg_mcom;                   
		},2500);        
    }
}

function goToHomePage()
{
    if(window.localStorage.getItem("layout")=="bottom") {
        $("div[class^=app_navigation_bottom]").show();
        window.localStorage.setItem("bottomHide","false");
    }
    
    
    window.sessionStorage.setItem("EcomSubMenu","true");
    
    $("#mainmenu").empty();
	    
    getEcommerceData(sessionStorage.getItem("ecommerceIndex"));
    //getIndexData();
}

function outputUpdate(value,id) {
    if(value > window.sessionStorage.getItem("ecomAvailquantity"+id)) {
        document.getElementById("qty"+id).value= window.sessionStorage.getItem("ecomAvailquantity"+id);
    }
    else {
        document.getElementById("qty"+id).value= value;
    }
}

function sumOfProductQuantity(input){
             
 if (toString.call(input) !== "[object Array]")
    return false;
      
            var total =  0;
            for(var i=0;i<input.length;i++)
              {                  
                if(isNaN(input[i])){
                continue;
                 }
                  total += Number(input[i]);
               }
             return total;
   }

var sumOfQuantity = [];
function bindecomCart()
{
    //$('.appypie-loader').show();
    var cart;
    html='';
    cartItemsArray.length = 0;
    var subtotal=0;
    var discount=0;
    var newsubtotal=0;
	
    if(window.sessionStorage.getItem("ecomcart") || window.sessionStorage.getItem("Fromecomcart"))
    {
		cart=window.sessionStorage.getItem("ecomcart");
		console.log("Cart: " + parseInt(cart));
        html+='<div class="user_tab" >';
        var counter=0;
        var foodcurrency=window.sessionStorage.getItem('currency');
        for (var x=1; x<=parseInt(cart); x++)
        {
            var qty=window.sessionStorage.getItem("ecomquantity"+x);
			console.log("qtyTotal: "+ qty);
			sumOfQuantity[x] = qty;
			console.log("len: "+ sumOfQuantity.length);
			for (var i = 0; i <= sumOfQuantity.length; i++) {
				console.log("qtyTotalSum: "+ sumOfProductQuantity(sumOfQuantity));
			//window.sessionStorage.setItem("ecomcart", sumOfProductQuantity(sumOfQuantity));
			//window.sessionStorage.setItem("ecomcartQuantitywise", sumOfProductQuantity(sumOfQuantity));
			}
            var productId=window.sessionStorage.getItem("ecomproductId"+x);
            if(parseInt(qty) > parseInt(sessionStorage.getItem("ecomAvailquantity"+x))) {
                qty=parseInt(sessionStorage.getItem("ecomAvailquantity"+x));
                sessionStorage.setItem("ecomquantity"+x,qty);
            }
            
			
			
			
            var productName='';
            var configTitle=sessionStorage.getItem("ecomconfigTitleProduct"+x);
            var sendData= sessionStorage.getItem("ecomconfigValueProduct"+x);
			
            if(configTitle != null && configTitle.length>1)
            {
                productName=sessionStorage.getItem("ecomproductName"+x)+ '(' +sendData + ')';
            }
            else
            {
                productName=window.sessionStorage.getItem("ecomproductName"+x);
            }
            
           /* if(productName.length>30) {
                productName=productName.substring(0,30);
            }*/
            var	price=window.sessionStorage.getItem("ecomprice"+x);
            var productImage=window.sessionStorage.getItem("ecomproductImage"+x);
            var status=window.sessionStorage.getItem("ecomstatus"+x);
            var productDesc = window.sessionStorage.getItem("ecomproductdescription"+x);
            console.log('productName--->>'+productName+'<<---status===>'+status);
            if(status=="1")
            {
                cartItemsArray.push(x);
                if(productDesc.length>30) {
                    productDesc=productDesc.substring(0,30);
                }
                subtotal=(parseFloat(subtotal)+(parseFloat(price)*parseInt(qty)));
                newsubtotal=(parseFloat(newsubtotal)+(parseFloat(price)*parseInt(qty)));
                var couponAmount=0;
				
                   
				
				html+= '<div class="product_box"><img style="background-image:url('+productImage+')" src="images/ecom-image.png" alt="" onclick="openEcomDetailPage('+productId+')"/>\
				<div class="discription_box">\
				<h3>'+productName+'</h3>\
				<p style="display:none">lorem ipsum doler</p>\
				<div class="product_price"><big>'+foodcurrency+' '+price+'</big></div>\
				<div class="product-qty"><input type="button" class="less" value="-" onclick="ecomQuantityManage(this,\''+x+'\',\''+price+'\')"> '+qty_mcom+' : <input data-role="none" type="number" onchange="onQuantityChange(\''+x+'\')" value="'+parseInt(qty)+'" id="qty'+x+'" class="qty" readonly /><input type="button" class="add" value="+" onclick="ecomQuantityManage(this,\''+x+'\',\''+price+'\')"></div>\
		    	</div>\
				<ol class="prodit" style="display:none">\
				<li><span class="porqu">'+qquantity_mcom+'</span><span class="porqu">'+pprice_mcom+'</span><span class="tot">'+ttotalpayableamount_mcom+'</span></li>\
				<li>\
				<span class="qut"></span>\
				<span class="qut">'+foodcurrency+' '+price+'</span>\
				<span class="tot teObxrt">'+foodcurrency+' '+formatCurrency(parseFloat((parseFloat(price)*parseInt(qty))),1)+'</span>\
				</li>\
				</ol>\
				<input type="button" class="delete" value=" " id="deleteButton'+x+'" onclick="deleteEcomFromCart(\''+x+'\')">\
				</div>';				
                counter=counter+1;                
            }
			window.sessionStorage.setItem("ecomcartQuantitywise", counter);
        }
        var couponNewHTML='';

        if(sessionStorage.getItem("ecomdiscountType"))
        {
            console.log("bindcart ecom in coupon if condition");
            if(window.sessionStorage.getItem("ecomdiscountType")=='percentage')
            {
                couponAmount=(subtotal*parseFloat(sessionStorage.getItem("ecomcouponDiscount")/100));
            }
            else
            {
                couponAmount=parseFloat(sessionStorage.getItem("ecomcouponDiscount"));
            }
            sessionStorage.setItem("ecomcouponAmount",couponAmount.toFixed(2));
            subtotal=parseFloat(subtotal)-parseFloat(couponAmount);
            console.log("in coupon discount type"+couponAmount);
            document.getElementById('couponDID').style.display = 'block';
            //$("#couponDID").show();
            //$("#ecomcoupon").empty();
            
           
            couponNewHTML='<li id="couponDID">'+coupong_mcom+'<span id="ecomcoupon">'+sessionStorage.getItem("currency")+couponAmount.toFixed(2)+'</span></li>';
            //$("#ecomcoupon").append(foodcurrency+couponAmount.toFixed(2));
        }
        else
        {
            sessionStorage.setItem("ecomcouponAmount",0);
            couponNewHTML='';
            $("#couponDID").hide();
        }
        
		
        sessionStorage.setItem("subtotal",newsubtotal.toFixed(2));
        if(counter>0)
        {
            document.getElementById("user_tab3").style.display="block";
            getecomDiscount(newsubtotal);
            getecomtax(newsubtotal);
            sessionStorage.setItem("ecomdiscountAmount",sessionStorage.getItem("ecomdiscount"));
            //console.log("1");
            //console.log("2");
            sessionStorage.setItem("ecomsubtotal",(parseFloat(newsubtotal)).toFixed(2));
            sessionStorage.setItem("ecomsubtotalAmount",(parseFloat(newsubtotal)).toFixed(2));
            //console.log("3");
            sessionStorage.setItem("ecomTaxAmount",parseFloat(sessionStorage.getItem("ecomtax")).toFixed(2));
            var newTaxTotalAmount=calculateMiscTax(subtotal);
            var gtotral=parseFloat(subtotal)-parseFloat(sessionStorage.getItem("ecomdiscount"))+parseFloat(sessionStorage.getItem("ecomtax"));
			//change by deepak
             var gtotral1 = parseFloat(gtotral)+parseFloat(newTaxTotalAmount);
			// alert("Deepak11 "+gtotral);
             sessionStorage.setItem("ecomgtotal",gtotral1);
             sessionStorage.setItem("ecomcheckoutFlag",1);
			
			getecomDeliveryCharges(parseFloat(gtotral1));
            
            sessionStorage.setItem("ecomdeliveryAmount",sessionStorage.getItem("ecomdelivery"));
            
           // var newTaxTotalAmount=calculateMiscTax(subtotal);
            gtotral=parseFloat(gtotral)+parseFloat(sessionStorage.getItem("ecomdelivery"))+parseFloat(newTaxTotalAmount);
            //console.log("gtotral --->"+gtotral+"<----subtotal--->"+parseFloat(subtotal)+"<----format--->"+formatCurrency(subtotal,1));
            // subtotal=formatCurrency(subtotal,1);
            sessionStorage.setItem("ecomnewgtotal",gtotral);
            gtotral=formatCurrency(gtotral,1);
            
			$("#user_tab1").empty().append(html);
			
			//$(".cart-item").empty().append(html)
			
			/*
			DISPLAY ECOM CART LIKE SUBTOTAL, DELIVERY CHARGE etc
			*/
			
		
			var newSubHtml='<li>'+subtotalg_mcom+'<span id="ecomsubtotal">'+sessionStorage.getItem("currency")+' '+(parseFloat(newsubtotal)).toFixed(2)+'</span></li>';
    		newSubHtml +='<li id="taxDiv">'+tax_mcom+' (+'+window.localStorage.getItem("taxPrice")+'%) <span id=ecomtax">'+sessionStorage.getItem("currency")+' '+sessionStorage.getItem("ecomtax")+'</span></li>';
			newSubHtml +=calculateMiscTax(subtotal,'cart');
			newSubHtml +='<li id="discountDiv">'+discountg_mcom+'(-) <span id="ecomdiscount">'+sessionStorage.getItem("currency")+' '+sessionStorage.getItem("ecomdiscount")+'</span></li>';
			newSubHtml +='<li id="DeliveryDiv">'+deliverychargeg_mcom+' (+) <span id="ecomdelivery">'+sessionStorage.getItem("currency")+' '+sessionStorage.getItem("ecomdelivery")+'</span></li>';			
			//newSubHtml +='<li id="couponDID" style="display:none;">Coupon <span id="ecomcoupon"></span></li>';
			newSubHtml +=couponNewHTML;
			newSubHtml +='<li>'+grand_total_mcom+' <span class="gtotal grand-total" id="ecomgtotal">'+sessionStorage.getItem("currency")+' '+gtotral+'</span></li>';

			$("#grandTotal").text(sessionStorage.getItem("currency")+' '+gtotral);
		
            
            $(".pay-mobile-cart").empty().append(newSubHtml);
			$(".cart-details").show();
            sessionStorage.setItem("ecomgtotal",gtotral);
            sessionStorage.setItem("ecomcheckoutFlag",1);
        }
        else
        {
           window.sessionStorage.setItem("ecomcart",0);
		    //html='<p class="total"><b>There is no Item in the cart</b></p><input //type="button" value="Continue Shopping" class="continue-shopping" //onclick="goToHomePage()">';
			
			 window.sessionStorage.setItem("ecomcart",0);
html='<div class="thankyou"><h1>'+cartemptyg_mcom+'<span>'+eemptyg_mcom+'</span></h1><p>'+additemnowg_mcom+'</p><input type="button" onclick="goToHomePage()" value="'+continue_shopping_mcom+'" class="continue-shopping"><input type="button" onclick="homeecom()" value="'+gotohomeg_mcom+'" style="display:none"></div>';
			
            $("#user_tab1").empty().html(html)
			$(".cart-details").hide();
            window.sessionStorage.setItem("ecomcartQuantitywise", 0);
            document.getElementById("editoncart").style.display="none";
            sessionStorage.removeItem("ecomcheckoutFlag");
            sessionStorage.setItem("ecomdiscountType",'');
            sessionStorage.setItem("ecomcouponDiscount",'0.00');
            setTimeout(function(){$('.appypie-loader').hide();},1000);
			//$("#contentHolder3").append('<footer id="footer-nav"><a href="#" class="next" onclick=""></a></footer>');
			$("#footer-nav").hide();
        }
    }
    else
    {
		window.sessionStorage.setItem("ecomcart",0);
		window.sessionStorage.setItem("ecomcartQuantitywise", 0);
		
		html='<div class="thankyou"><h1>'+cartemptyg_mcom+'<span>'+eemptyg_mcom+'</span></h1><p>'+additemnowg_mcom+'</p></div>';
		
        //html='<center><b>There is no Item in the cart</b><br/></center>';
        html+='<div class="cart-update-btn" id="cart-update-btn2" style="text-align:center"> <a class="e-mobile-btn" onclick="goToHomePage();">'+continue_shopping_mcom+'</a> </div></div>';
        $("#user_tab").append(html);
        document.getElementById("editoncart").style.display="none";
        sessionStorage.removeItem("ecomcheckoutFlag");
		//$("#contentHolder3").append('<footer id="footer-nav"><a href="#" class="next" onclick=""></a></footer>');
		$("#footer-nav").hide();
    }
    ShowPaymentDetails();
    //window.sessionStorage.setItem("ecomdiscountType",'');
    //window.sessionStorage.setItem("ecomcouponDiscount",'0.00');
    if(window.sessionStorage.getItem("ecomDeleteCalled")=="true") {
        // alert("in if condition");
        document.getElementById("editoncart").innerHTML="Done";
        showeditOnCartEcom();
    }
    setTimeout(function(){ $('.appypie-loader').hide();},1000);
    
}

function calculateMiscTax(subtotal,pageName)
{
    var noOfTax=sessionStorage.getItem("noOfTax");
    var newTaxTotalAmount=0;
    var html='';
    var jsonObj='[';
    
    for(var x=0;x<parseFloat(noOfTax);x++)
    {
        if(sessionStorage.getItem("taxType"+x))
        {
            var taxName=sessionStorage.getItem("taxType"+x);
            var taxAmount=sessionStorage.getItem("taxAmount"+x);
            var taxRate=sessionStorage.getItem("taxRate"+x);
            var newTaxAmount=0;
            
            if(taxRate == "Flat")
            {
                
                newTaxTotalAmount=parseFloat(newTaxTotalAmount)+parseFloat(taxAmount);
                newTaxAmount=parseFloat(taxAmount);
                //console.log("taxRate--->"+taxRate+"<---name---->"+taxName);
                //console.log("in if newTaxTotalAmount--->"+newTaxTotalAmount+"<-----newTaxAmount--->"+newTaxAmount);
            }
            else
            {
                if(subtotal)
                {
                    newTaxTotalAmount=parseFloat(newTaxTotalAmount)+(parseFloat(subtotal)*taxAmount)/100;
                    newTaxAmount=(parseFloat(subtotal)*taxAmount)/100;
                    
                   // console.log("taxRate--->"+taxRate);
                    //console.log("in else newTaxTotalAmount--->"+newTaxTotalAmount+"<-----newTaxAmount--->"+newTaxAmount);
                }
            }
            //console.log("newTaxTotalAmount--->"+newTaxTotalAmount+"<-----newTaxAmount--->"+newTaxAmount);
            //console.log("pageName--->"+pageName);
            if(pageName == "json")
            {
                if(x>0 && x<parseFloat(noOfTax))
                {
                    jsonObj+=',';
                }
                jsonObj+='{"taxType":"'+taxName+'","taxAmount":"'+taxAmount+'","taxRate":"'+taxRate+'","tax":"'+newTaxAmount.toFixed(2)+'"}';
                if(x==(parseFloat(noOfTax)-1))
                {
                    jsonObj+=']';
                }
            }
            else
            {
                html+='<li id="newTax'+x+'">'+taxName+'<span>'+sessionStorage.getItem("currency")+' '+newTaxAmount.toFixed(2)+'</span></li>';
            }
        }
    }
    
    //console.log("jsonObj--->"+jsonObj);
    if(!pageName)
    {
        return newTaxTotalAmount.toFixed(2);
    }
    else if(pageName == "json")
    {
        if(parseFloat(noOfTax)>0)
        {
            return jsonObj;
        }else
        {
            return "";
        }
        
    }
    else
    {
        return html;
    }
}
function showeditOnCartEcom(){
    
    /*for(var i=0;i <cartItemsArray.length;i++){
        //$("#deleteButton"+cartItemsArray[i]).slideToggle();
        $("#sliderdiv"+cartItemsArray[i]).slideToggle();
        $("#qty"+cartItemsArray[i]).removeAttr("readonly");
        $("#qty"+cartItemsArray[i]).addClass("bordered");
    }
    
    if(document.getElementById("editoncart").innerHTML == "Done") {
        
        hideeditOnCartEcom();
    }
    else {
        
        document.getElementById("editoncart").innerHTML="Done";
    }*/
    
}
function hideeditOnCartEcom()
{
    document.getElementById("editoncart").innerHTML=edit_mcom;
    var ulHtml=document.getElementsByClassName('food_pro_list')[0].innerHTML;
    var inputs = document.getElementsByTagName("input");
    for (var i = 0, len = inputs.length; i < len; i++) {
        if (inputs[i].id.indexOf("qty") == 0) {
            for(var p=1; p <=parseInt(window.sessionStorage.getItem("ecomcart")); p++)
            {
                var idp=inputs[i].id.replace("qty","");
                if(idp==p)
                {
                    sessionStorage.setItem("ecomquantity"+p,inputs[i].value)
                }
            }
        }
    }
    if(window.sessionStorage.getItem("ecomDeleteCalled")=="true") {
        document.getElementById("editoncart").innerHTML="Done";
        window.sessionStorage.setItem("ecomDeleteCalled","false");
    }
    else{
        //ecomCart_View();
        bindecomCart();
    }
    
}
function showEcomCouponPage(){
    
    
    
    if(window.sessionStorage.getItem("ecomcheckoutFlag"))
    {
        var checkDisp="block";
    }
    else
    {
        var checkDisp="none";
    }
    
    var foodcurrency=window.sessionStorage.getItem("currency");
    var subtotal= window.sessionStorage.getItem("ecomsubtotalAmount");
    var extraTaxHtml=calculateMiscTax(subtotal,'couponpage');
    var couponDisp='none';
    var deliveryDisp='block';
    var discountDisp='block';
    var taxDisp='block';
    var tipDisp='block';
    var deliveryDisp='block';
    if(window.sessionStorage.getItem("ecomcouponAmount")) {
        var couponDisp='block';
    }
    
    
    if(window.sessionStorage.getItem("ecomdeliveryAmount") == 0)
    {
        deliveryDisp="none";
    }
    if(window.sessionStorage.getItem("ecomdiscountAmount") == 0)
    {
        discountDisp="none";
    }
    if(sessionStorage.getItem("ecomTaxAmount") == 0)
    {
        taxDisp="none";
    }
    
    if(sessionStorage.getItem("ecomcouponAmount") == 0)
    {
        var couponDisp='none';
    }
    
    var counter=0;
    $('.appypie-loader').show();
    if(window.localStorage.getItem("layout")=="bottom") {
        $("div[class^=app_navigation_bottom]").show();
        window.localStorage.setItem("bottomHide","false");
    }
   
    var coupon_HTML='<div class="product-top"><h3>'+ccouponpageg_mcom+'</h3><div class="checkOut-Btn" onclick="bindCart()" id="checkoutbtn" style="display:none">'+checkout_mcom+'</div></div><div class="cart-MSG" id="coupon-MSG" style="display:none"><div class="cart_success">'+notavalidcoupong_mcom+'<div class="er_close"  onclick="closeCoupon()">X</div></div></div><section id="container" ><div class="food-ordering-product"><div class="food-pro_details"><h4>'+entercouponcodeg_mcom+'</h4><input data-role="none" type="text" id="couponCode" class="couponBox" /><a class="submit_btn couponButton" onclick="applyEcomCoupon();" >'+applygg_mcom+'</a></div><div class="user_tab"><h4>'+payment_details_mcom+'</h4><ul class="pay-mobile-cart"><li >'+subtotal_mcom+' <span style="color:#000" id="subtotal">'+foodcurrency+' '+
    subtotal+'</span></li><li style="display:'+deliveryDisp+'">'+ddeliverychargegg_mcom+'<span style="color:#000" id="delivery">'+foodcurrency+' '+sessionStorage.getItem("ecomdeliveryAmount")+'</span></li><li style="display:'+taxDisp+'">'+tax_mcom+' <span style="color:#000;" id="tax">'+foodcurrency+' '+window.sessionStorage.getItem("ecomTaxAmount")+'</span></li>'+extraTaxHtml+'<li style="display:'+discountDisp+'">'+discount_mcom+' <span style="color:#000;" id="discount">'+foodcurrency+' '+window.sessionStorage.getItem("ecomdiscountAmount")+'</span></li><li id="couponDID" style="color:#000;display:'+couponDisp+'">Coupon <span id="coupon" tyle="color:#000">'+foodcurrency+' '+' '+window.sessionStorage.getItem("ecomcouponAmount")+'</span></li><li>'+grand_total_mcom+'<span style="color:#000" class="gtotal" id="gtotal">'+foodcurrency+' '+window.sessionStorage.getItem("ecomgtotal")+'</span></li></ul></div></div></section><ul class="sub-menu" style="display:none;" ><li><a onclick="homeecom()" class="home-nav">'+homeeg_mcom+'</a></li><li style="display:'+loginDispl+';"><a onclick="loginEcommerce()" class="login-nav">'+login_signup_mcom+'</a></li><li style="display:'+accountDispl+';"><a onclick="" class="account-nav">'+myAccountg_mcom+'</a></li><li><a onclick="goHomeEcom()" class="menu-nav">Main Menu</a></li><li><a onclick="termsEcommerce()" class="terms-nav">'+termsconditionsgg_mcom+'</a></li><li><a onclick="PolicyEcommerce()" class="policy-nav">'+privacypolicygg_mcom+'</a></li></ul>';
    
    appendHtml(coupon_HTML,5,5,'food');
    resetEcomHeader();
}

function applyEcomCoupon(){
	var cpInput=null; 
	cpInput= document.getElementById("couponCode").value;
	console.log("cpInput: "+ cpInput);
	if(cpInput == null || cpInput == undefined || cpInput == '') {
		 alertPopUp('Error !','Please enter coupon code');
	} else {
	
    $('.appypie-loader').show();
    var wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#ecommCoupon";
    var couponCode=document.getElementById("couponCode").value;
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommCoupon xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#ecommCoupon\"><appId>'+appid+'</appId><couponCode>'+couponCode+'</couponCode></ecommCoupon></soap:Body></soap:Envelope>';
     console.log("copun: "+soapRequest);
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
           console.log("coupon_strJSON: "+ strJSON);
           if(strJSON == "No Discount" || strJSON == "Coupon Expire" || strJSON == "Not a valid coupon")
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           
           $("#coupon-MSG").show();
           setTimeout( function(){
                      $("#coupon-MSG").hide();
                      },2500 );
			 alertPopUp('Coupon code',strJSON);
           
           }
           else
           {
           ShowEcomCoupon(strJSON);
           }
           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           console.log(response);
           }
           });
	}
    
}
function closeCoupon()
{$("#coupon-MSG").hide();}
function ShowEcomCoupon(xml){
    $.ajax({
           url: xml,
           dataType: "text",
           cache: false,
           crossDomain: true,
           success: function(xmll) {
           var couponDiscount =$(xmll).find("couponDiscount").text();
           
           var discountType=$(xmll).find("discountType").text();
           console.log("discountType-->"+discountType+"couponDiscount ---->>>"+couponDiscount);
           window.sessionStorage.setItem("ecomdiscountType",discountType);
           window.sessionStorage.setItem("ecomcouponDiscount",parseFloat(couponDiscount).toFixed(2));
           $('.appypie-loader').hide();
           getecomDeliveryCharges(parseFloat(window.sessionStorage.getItem("ecomsubtotal")));
           ecomCart_View();
           },
           error: function(XMLHttpRequest, textStatus, errorThrown) {
           console.log('fail');
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
           
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

/*
GET ECOM DISCOUNT
*/

function getecomDiscount(TotalAmount)
{
    window.sessionStorage.setItem("ecomdiscount",0);
    wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#ecommDiscount";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommDiscount xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#ecommDiscount\"><appId>'+appid+'</appId><gTotal>'+TotalAmount+'</gTotal></ecommDiscount></soap:Body></soap:Envelope>';
    console.log('request=>'+soapRequest);
    jQuery.support.cors = true;
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
                  var grossTotal= $(xmldata).find("grossTotal").text();
                  var discountPrice= $(xmldata).find("discountPrice").text();
                  var discountRate= $(xmldata).find("discountRate").text();
                  if(discountRate=='Percentage')
                  {
                  amt=(grossTotal*discountPrice)/100;
                  }
                  else
                  {
                  amt=discountPrice;
                  }
                  
                  window.sessionStorage.setItem("ecomdiscount",parseFloat(amt).toFixed(2));
                  
                  },
                  error: function(XMLHttpRequest, textStatus, errorThrown) {
                  console.log('fail');
                  setTimeout(function(){$('.appypie-loader').hide();},1000);
                  console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
                  }
                  });
           }
           
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           }
           });
    
}

/*
GET ECOM TAX
*/
function getecomtax(TotalAmount)
{
    window.sessionStorage.setItem("ecomtax",0);
    wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#ecommTax";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommTax xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#ecommTax\"><appId>'+appid+'</appId><gTotal>'+TotalAmount+'</gTotal></ecommTax></soap:Body></soap:Envelope>';
    console.log('request=>'+soapRequest);
    jQuery.support.cors = true;
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
                  console.log('yesssssss');
                  var grossTotal= $(xmldata).find("grossTotal").text();
                  var taxPrice= $(xmldata).find("taxPrice").text();
                  var taxRate= $(xmldata).find("taxRate").text();
				  window.localStorage.setItem("taxPrice", taxPrice);
                  if(taxRate=='Percentage')
                  {
                  amt=(grossTotal*taxPrice)/100;
                  }
                  else
                  {
                  amt=taxPrice;
                  }
                  
                  window.sessionStorage.setItem("ecomtax",parseFloat(amt).toFixed(2));
                  
                  },
                  error: function(XMLHttpRequest, textStatus, errorThrown) {
                  console.log('fail');
                  setTimeout(function(){$('.appypie-loader').hide();},1000);
                  console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
                  }
                  });
           }
           
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           }
           });
    
}
/*
CALCULATE DELIVERY CHARGE 
*/
function getecomDeliveryCharges(TotalAmount)
{
    window.sessionStorage.setItem("ecomdelivery",0);
    wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#ecommShippingCharge";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommShippingCharge xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#ecommShippingCharge\"><appId>'+appid+'</appId><gTotal>'+TotalAmount+'</gTotal></ecommShippingCharge></soap:Body></soap:Envelope>';
    console.log('getecomDeliveryCharges_request=>'+soapRequest);
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
           console.log('getecomDeliveryCharges_response=>'+req.responseText);
           var strJSON = $(req.responseText).find("return").text();
           console.log('foodDeliveryCharge==>'+strJSON);
           if(strJSON!='Free Shipping')
           {
           console.log('=x=x=x=x='+strJSON);
           $.ajax({
                  url: strJSON,
                  dataType: "text",
                  crossDomain: true,
                  async: false,
                  cache: false,
                  success: function(xmldata) {
                  console.log('yesssssss');
                  var grossTotal= $(xmldata).find("grossTotal").text();
                  var shippingPrice= $(xmldata).find("shippingPrice").text();
                  var shippingRate= $(xmldata).find("shippingRate").text();
                  if(shippingRate=='Percentage')
                  {
					var grandTotal = sessionStorage.getItem("ecomgtotal");
					console.log("grandTotal: "+ grandTotal);
                  //amt=(grossTotal*shippingPrice)/100;
					amt=(grandTotal*shippingPrice)/100;
				  console.log("Amount_Aft_Shipping: "+ amt);
                  }
                  else
                  {
                  amt=shippingPrice;
                  }
                  window.sessionStorage.setItem("ecomdelivery",parseFloat(amt).toFixed(2));
                  
                  },
                  error: function(XMLHttpRequest, textStatus, errorThrown) {
                  console.log('fail');
                  setTimeout(function(){$('.appypie-loader').hide();},1000);
                  console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
                  }
                  });
           }
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    
}


/* Billing and Shipping Page  */
function ecomcheckoutView() {
	
	
    window.sessionStorage.setItem("CheckOutViewOpen","ecom");
	var tmpHeader = '<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
	<a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
  <h1 class="ui-title">'+appName+'</h1>\
  <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>';
	
	
    if(!checkNetworkConnection())
    {
    }else
    {
        $('.appypie-loader').show();
        if(window.localStorage.getItem("layout")=="bottom") {
            //$("div[class^=app_navigation_bottom]").show();
            //window.localStorage.setItem("bottomHide","false");
			
			$("div[class^=app_navigation_bottom]").hide();
            window.localStorage.setItem("bottomHide","ture");
        }
        var couponDis='block';
        var deliveryDis='block';
        var discountDis='block';
        var taxDis='block';
        if(sessionStorage.getItem("ecomcouponAmount") == 0) {
            couponDis='none';
        }
        if(sessionStorage.getItem("ecomdeliveryAmount") == 0)
        {
            deliveryDis='none';
        }
        if(parseFloat(sessionStorage.getItem("ecomdiscountAmount")) < 1)
        {
            discountDis='none';
        }
        if(sessionStorage.getItem("ecomTaxAmount") == 0)
        {
            taxDis='none';
        }
        var ecomcurrency=sessionStorage.getItem("currency");
        var subtotal=sessionStorage.getItem("ecomsubtotalAmount");
        var extraTaxHtml=calculateMiscTax(subtotal,'checkoutpage');
        
		
		
		var billingAddress = '<div class="dashboard scrolling-bottom"><div class="wrap">\
		<div class="billing-address">\
		<h2>'+billing_address_mcom+'</h2>\
		<input data-role="none" id="bfname" type="text" placeholder="'+name_mcom+'">\
		<input data-role="none" id="bpNo"  type="text" placeholder="'+telephone_mcom+'"  >\
		<input data-role="none" id="bemail" type="text" placeholder="'+email_id_mcom+'" >\
		<input data-role="none" id="bsAddress" type="text" placeholder="'+address_mcom+'">\
		<input data-role="none" id="bCity" type="text" placeholder="'+city_mcom+'">\
		<input data-role="none" id="bState" type="text" placeholder="'+state_province_mcom+'" >\
        <input data-role="none" id="bZip" type="text" placeholder="'+zip_postal_code_mcom+'">\
		<div id="bcountryTry"></div>\
		<div id="showBillingAddress" onclick="showShippingAddress(this)" dir=".shipping-address"><span></span><input data-role="none" type="checkbox" id="show_billing_address"/>'+shippingaddressdifferentfrombillingdddressgg_mcom+'</div>\
		</div>';
		var shippingAddress = '<div class="shipping-address" id="billing_address"><h2>'+shipping_address_mcom+'</h2>\
		<input data-role="none" type="text" id="sfname" placeholder="'+name_mcom+'">\
		<input data-role="none" type="text" id="spNo" placeholder="'+telephone_mcom+'">\
		<input data-role="none" type="text" id="ssAddress" placeholder="'+address_mcom+'">\
        <input data-role="none" type="text" id="sCity" placeholder="'+city_mcom+'"  >\
        </strong><input data-role="none" type="text"  id="sState" placeholder="'+state_province_mcom+'" >\
        </strong><input data-role="none" type="text"  id="sZip" placeholder="'+zip_postal_code_mcom+'" >\
        <div id="scountryTry"></div>\
		</div>';
		
		var instructionsTab = '<div class="e-mobileOH_tab open_tab billing-address" id="InstructionsTab"><h2>'+instructions_mcom+'</h2><textarea data-role="none" style="width:100%;height:100px" id="instructionsText"></textarea></div>';
		
		
		/*
		Display before the cart
		*/
		var paymentDetails = '<div class="billing-address payment-details">\
        <h2>'+payment_details_mcom+'</h2><ul class="pay-mobile-cart">\
        <li>'+subtotal_mcom+' <span id="ecomsubtotal">'+ecomcurrency+subtotal+'</span></li>\
		<li id="DeliveryDiv" style="display:'+deliveryDis+';">'+dddeliverychargegg_mcom+'(+) <span id="ecomdelivery">'+ecomcurrency+' '+sessionStorage.getItem("ecomdeliveryAmount")+'</span></li>\
		<li id="taxDiv" style="display:'+taxDis+';">'+tax_mcom+' (+'+window.localStorage.getItem("taxPrice")+'%) <span  id="ecomtax">'+ecomcurrency+' '+sessionStorage.getItem("ecomTaxAmount")+'</span></li>'+extraTaxHtml+'\
        <li id="discountDiv" style="display:'+discountDis+';">'+discount_mcom+' (-) <span id="ecomdiscount">'+ecomcurrency+' '+sessionStorage.getItem("ecomdiscountAmount")+'</span></li>\
        <li id="couponDID" style="display:'+couponDis+';">'+coupon_mcom+' <span id="ecomcoupon">'+ecomcurrency+' '+sessionStorage.getItem("ecomcouponAmount")+'</span></li>\
        <li>'+grand_total_mcom+'<span class="gtotal grand-total" id="ecomgtotal">'+ecomcurrency+' '+sessionStorage.getItem("ecomgtotal")+'</span></li>\
		</ul><div class="cart-update-btn" style="display:none">\
        <a class="e-mobile-btn payment-mobile-btn" onclick="PayNow();" >'+ccompletepurchasegg_mcom+'</a></div>\
        </div>\
        <ul class="food_pro_list"></ul></div></div></section></div><footer id="footer-nav">\
		<a href="#" class="next" onclick="PayNow();">'+ccompletepurchasegg_mcom+'</a>\
	</footer>';
	
		
		 var ckechout_HTML='<div class="cart-MSG" id="cart-MSG1" style="display:none"><span class="er_close">x</span><div class="cart_success"></div></div>\
		<div class="cart-MSG" id="cart-MSG2" style="display:none"><span class="er_close">x</span><div class="cart_error2"></div></div>'
		
		//$("#contentHolder4").append('<footer id="footer-nav"><a href="#" class="next" //onclick="PayNow()">COMPLETE PURCHASE</a></footer>');
		
		//ckechout_HTML='';
		ckechout_HTML +=    billingAddress + shippingAddress + instructionsTab + paymentDetails;
		
		
		//ckechout_HTML += ecomSubMenu();
		
        appendHtml(ecomHeader+ckechout_HTML,5,5,'food');
        resetEcomHeader();
        var subtotal=sessionStorage.getItem("subtotal");
		showContactInfoedit();
		$("#billing_address").hide();
        
        
        ShowPaymentDetails();
        
        //$("#instructionsText").hide();
        $("#InstructionsTab h2").click(function(){
			//$(this).parents("#InstructionsTabb").toggleClass("open_tab");                                       
			//$(this).parents("#InstructionsTab").children("textarea").slideToggle();
		});
        getCountry("checkout");		
        setTimeout(function(){$('.appypie-loader').hide();},1000);
    }
}

function ShowPaymentDetails()
{
    console.log("ecomdeliveryAmount--->>"+sessionStorage.getItem("ecomdeliveryAmount")+"<<----ecomdiscountAmount--->>"+sessionStorage.getItem("ecomdiscountAmount")+"<<----ecomsubtotalAmount----->>>"+sessionStorage.getItem("ecomsubtotalAmount")+"<<----ecomTaxAmount---->>"+sessionStorage.getItem("ecomTaxAmount")+"<<-----foodcurrency--->>");
    
    if(sessionStorage.getItem("ecomcouponAmount") == 0) {
        $("#couponDID").hide();
    }
    if(sessionStorage.getItem("ecomdeliveryAmount") == 0)
    {
        $("#DeliveryDiv").hide();
    }
    if(parseFloat(sessionStorage.getItem("ecomdiscountAmount")) < 1)
    {
        $("#discountDiv").hide();
    }
    if(sessionStorage.getItem("ecomTaxAmount") == 0)
    {
        $("#taxDiv").hide();
    }
}

/* Terms & Conditions Page  */

function termsEcommerce()
{
	//closeSubMenu(this);
	document.getElementById("left-navs").style.display = 'block';
    // hide the lorem ipsum text
    document.getElementById("left-navs").style.display = 'none';
    $('.appypie-loader').show();
	var headerTmp = '<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
      <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
      <h1 class="ui-title">'+appName+'</h1>\
      <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>';
    
    if(window.localStorage.getItem("layout")=="slidemenu"){
        snapper.close();
    }
    
    var TermsHtml='<section id="container">\
    <h3 style="color:#fff;" id="pageHead"></h3>\
    <div class="food-ordering-product" style="color:#fff; padding:10px">\
    \
    \
    </div>\
    </section>';
	//TermsHtml += ecomSubMenu();
    appendHtml(ecomHeader+"<div class='page-text'>"+TermsHtml+"</div>",2,2,'food');
    resetEcomHeader();
    getCMS('terms_and_conditions',"ecom");
    setTimeout(function(){   $('.appypie-loader').hide();},1000);
}


/* Privacy Policy */

function PolicyEcommerce()
{
	//closeSubMenu(this);
	document.getElementById("left-navs").style.display = 'block';
    // hide the lorem ipsum text
    document.getElementById("left-navs").style.display = 'none';
    $('.appypie-loader').show();
    
    if(window.localStorage.getItem("layout")=="slidemenu"){
        snapper.close();
    }
    var headerTmp = '<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
      <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
      <h1 class="ui-title">'+appName+'</h1>\
      <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>';
	  
    var PolicyHtml='<section id="container">\
    <h3 style="color:#fff;" id="pageHead"></h3>\
    <div class="food-ordering-product" style="color:#fff; padding:10px">\
    \
    \
    </div>\
    </section><ul class="food_pro_list"></ul></div></div></section>';
	
	//PolicyHtml += ecomSubMenu();
	
    appendHtml(ecomHeader+"<div class='page-text'>"+PolicyHtml+"</div>",2,2,'food');
    resetEcomHeader();
    getCMS('privacy_policy',"ecom");
}

function ecomLogout() {
	sessionStorage.setItem("logincheck","2");
    window.sessionStorage.setItem("ecomlogincheck","2");
    localStorage.removeItem("fooduserid");
    accountDispl='none';
    loginDispl='block';
    localStorage.removeItem("ecomuserid");
    localStorage.removeItem("foodEcomUserId")
   // getFoodorderingData(sessionStorage.getItem("foodorderingIndex"));
    toaster.setUserStatus("");
    toaster.setLoginStatus("");
    window.location="removeWebSite:";
    localStorage.setItem("userLoginStatus","false");
	window.localStorage.setItem("uProfilePic","");
	window.sessionStorage.removeItem("ecomcartQuantitywise");
	setTimeout(function(){
		ecomCategories();
	navigator.notification.confirm(alert_want_logout_services, logoutCallBack, logout_services, cancel_yes_services);
		//loginEcommerce();
		/*
		var ecomHTML_home = '<div><div class="login_box">\
       <div class="login_from">\
        <input type="button" class="button" value="Create Account" onclick="ecomSignUpView()">\
        <input type="button" class="button blue" value="'+sign_in_mcom+'"  onclick="loginEcommerce()">\
       </div>\
       </div></div>';
			//ecomHTML_home += ecomSubMenu();  
			appendHtml(ecomHTML_home,'',1,'food');
			*/
		},500);
	
}

/*
Ecom Main Screen
*/
function loginEcommerce()
{
	
    var loginHtml = "";
	loginHtml += '<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important;"><a onclick="showEcomMenu();" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
  <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
  <h1 class="ui-title">'+appName+'</h1>\
  <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>\
  <div><div class="login_box mcom-home">\
		<div class="login_from">\
			<div class="cart-MSG" id="cart-MSG" style="display:none"><div class="cart_error2">'+iinvalidloginidorpasswordgg_mcom+'</div></div>\
			<input data-role="none" type="text" value="" id="loginid" class="mail" placeholder="Email"  />\
			<input data-role="none" value="" type="password" id="loginpass" class="pass" placeholder="Password" />\
			<input type="button" class="button" value="'+login_mcom+'" style="display:none" onclick="getEcommerceDataSec()">\
			<input type="button" class="button" value="'+login_mcom+'" onclick="checkEcomUserLogin()">\
			<div class="links-view">\
			<a href="#" class="f-left" onclick="ecomForgotPassView()">'+forgot_password_mcom+'</a>\
			<a href="#" class="f-right" onclick="ecomSignUpView()">'+sign_up_mcom+'</a>\
			</div>\
		</div>\
	</div></div>'
	loginHtml += ecomSubMenu();		
	appendHtml(loginHtml,2,2,'food');
	resetEcomHeader();
}
function ecomSignUpView() {
	
	
    $('.appypie-loader').show();
    ecomsignup_HTML='<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
  <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
  <h1 class="ui-title">'+appName+'</h1>\
  <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>\
  <div>\
	<div class="login_box"><div class="cart-MSG" id="cart-MSG" style="display:none"><div class="cart_error"></div></div>\
    <div class="cart-MSG" id="cart-MSG2" style="display:none"><div class="cart_success2"></div></div>\
    <div class="login_from sign-up"><input data-role="none" type="text" id="fname" placeholder="'+nnamegg_mcom+' *" />\
    <input data-role="none" type="text" id="emailId" placeholder="'+email_id_mcom+' *" />\
	<input data-role="none" type="text" id="pNo" placeholder="'+pphonenumbergg_mcom+'" />\
	<input data-role="none" type="password" id="pass" placeholder="'+password_mcom+'" />\
	<input data-role="none" type="password" id="cpass" placeholder="'+confirm_password_mcom+'" />\
	<input type="button" class="button" value="'+sign_up_mcom+'" onclick="addEcomUser()">\
	<div class="links-view"><p>'+already_have_an_account_mcom+' <a onclick="loginEcommerce()">'+sign_in_mcom+'</a></p></div></div></div></div>';
    appendHtml(ecomsignup_HTML,3,3,'food');
	resetEcomHeader();
}
function ecomForgotPassView() {
	
	
    $('.appypie-loader').show();
    ecomforgotpass_HTML='<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
  <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
  <h1 class="ui-title">'+appName+'</h1>\
  <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>\
  <div>\
	<div class="login_box">\
							<div class="cart-MSG" id="passwordError" style="display:none"><div class="cart_error"></div></div>\
							<div class="cart-MSG" id="cart-MSG1-forgot" style="display:none"><div class="cart_success">'+wwehavesentaemailtoyouremailidgg_mcom+'</div></div>\
						<div class="login_from forgot-password">\
							<input data-role="none" type="text" id="forpasid" placeholder="'+eenteryyouremailgg_mcom+'" />\
							<input type="button" class="button" value="Reset Password"  onclick="sendEcomPassword()">\
							<div class="links-view">\
							<p>'+do_not_have_an_account_mcom+' <a onclick="ecomSignUpView()">'+sign_up_now_mcom+'</a></p>\
							<p>'+already_have_an_account_mcom+' <a onclick="loginEcommerce()">'+sign_in_mcom+'</a></p>\
							</div>\
						</div></div></div>';
    appendHtml(ecomforgotpass_HTML,3,3,'food');
    resetEcomHeader();
}
/************Signup*************/
function addEcomUser()
{
	
	
    if(!checkNetworkConnection())
    {
    }else
    {
        
        var fname=document.getElementById("fname").value;
        var pNo=document.getElementById("pNo").value;
        var emailId=document.getElementById("emailId").value;
        var pass=document.getElementById("pass").value;
        var cpass=document.getElementById("cpass").value;
        
        
        
        if(fname=='' || fname=='First Name')
        {
            
            /*$(".cart_error").empty();
            $(".cart_error").append('Please Enter Name.');
            
            $("#cart-MSG").slideToggle();
            setTimeout( function(){
                       $("#fname").focus();
                       $("#cart-MSG").slideToggle();
                       }
                       , 3000 );
					   setTimeout(function(){$('.appypie-loader').hide();},1000);*/
			setTimeout(function(){$('.appypie-loader').hide();},1000);
            navigator.notification.alert(ppleaseenterusernamegg_mcom, setTimeout, "Error", "OK");
            $("#fname").focus();
            return;
        } else if(emailId=='' || emailId==undefined){
			setTimeout(function(){$('.appypie-loader').hide();},1000);
            navigator.notification.alert(ppleaseemailidgg, setTimeout, "Error", "OK");
            $("#emailId").focus();
			
		}
        else if(!checkEmail(emailId) || emailId=='')
        {
			setTimeout(function(){$('.appypie-loader').hide();},1000);
            navigator.notification.alert(ppleaseentervalidemailidgg_mcom, setTimeout, "Error", "OK");
            $("#emailId").focus();
            return;

        }
       /* else if(pNo=='' || pNo=='Phone Number' )
        {
					   
		    setTimeout(function(){$('.appypie-loader').hide();},1000);
            navigator.notification.alert(ppleaseenterphonenumbergg_mcom, setTimeout, "Error", "OK");
            $("#pNo").focus();
            return;
        }*/
        else if(isNaN(pNo))
        {
			setTimeout(function(){$('.appypie-loader').hide();},1000);
            navigator.notification.alert(ppleaseentervalidphonenumbergg_mcom, setTimeout, "Error", "OK");
            $("#pNo").focus();
            return;
        }
        else if(pass==''  || pass=='Password')
        {
			
			setTimeout(function(){$('.appypie-loader').hide();},1000);
            navigator.notification.alert(ppleaseenterpasswordgg_mcom, setTimeout, "Error", "OK");
            $("#pass").focus();
            return;
        }
        else if(cpass!=pass)
        {
			
			setTimeout(function(){$('.appypie-loader').hide();},1000);
            navigator.notification.alert(cconfirmpassworddonotmatchgg_mcom, setTimeout, "Error", "OK");
            $("#cpass").focus();
		
            return;
        }
        else
        {
            userEcomAdd(fname,pNo,emailId,pass);
            
        }
    }
}

function checkEmail(email) {    
    
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;    
    /*
	if (!filter.test(email)) {
        return false;
    }
    return true;
	*/
	return filter.test(email);
}

function userEcomAdd(fname,pNo,emailId,pass)
{
	$('.appypie-loader').show();
    var wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#ecommCustomerRegistration";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommCustomerRegistration xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#ecommCustomerRegistration\"><appId>'+appid+'</appId><email>'+emailId+'</email><password>'+pass+'</password><fname>'+fname+'</fname><lname></lname><phone>'+pNo+'</phone></ecommCustomerRegistration></soap:Body></soap:Envelope>';
    
    console.log(soapRequest);
    jQuery.support.cors = true;
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           var strJSON = $(req.responseText).find("return").text();
           console.log('strJSON==>'+strJSON);
           var result=strJSON.split("_");
           if(parseInt(result.length)==2)
           {
           $("#cart-MSG2").slideToggle();
           $(".cart_success2").append('Signup Successful');
           setTimeout( function(){
                      $("#cart-MSG2").slideToggle();
                      
                      }
                      , 3000 );
           setTimeout( function(){
                      loginEcommerce();
                      
                      }
                      , 5000 );
           }
           else
           {
        	   
           navigator.notification.alert(eemailidalreadyregisteredgg_mcom, setTimeout, eerrorgg_mcom, OoKgg_mcom);
           $("#emailId").focus();
           return;
           }
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    
}
/**********Send Password*********/
function sendEcomPassword()
{
	
	
    if(!checkNetworkConnection())
    {
    }else
    {
        $('.appypie-loader').show();
        var emailid=document.getElementById("forpasid").value;
        
        
        if(!checkEmail(emailid) || emailid=='')
        {
            $(".cart_error").empty();
            $(".cart_error").append(uuhohgg_mcom, iinvalidemailiidggg_mcom);
            
            $("#passwordError").slideToggle();
            setTimeout( function(){
                       $("#forpasid").focus();
                       $("#passwordError").slideToggle();
                       }
                       , 3000 );
            setTimeout(function(){$('.appypie-loader').hide();},1000);
            return;
            
        }
        else
        {
            var wsUrl = "http://"+resellerInitial+reseller+"/services/app-user-soap#forgetPassword";
            var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><forgetPassword xmlns=\"http://'+resellerInitial+reseller+'/services/app-user-soap#forgetPassword\"><appId>'+appid+'</appId><email>'+emailid+'</email></forgetPassword></soap:Body></soap:Envelope>';
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
				   if(strJSON == "Not Exists") {
					   $(".cart_error").empty();
					   $(".cart_error").append(yyouhavenotbeenregisteredwithusyetgg_mcom);
                   
                   $("#passwordError").slideToggle();
                   setTimeout( function(){
                              $("#forpasid").focus();
                              $("#passwordError").slideToggle();
                              }
                              , 3000 );
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
					   return false;
				   } else if(strJSON!='NotExist')
                   {
                   $(".cart_error").empty();
                   $(".cart_error").append(ppasswordresetwassuccessfullpleasecheckyouremailfornewpasswordgg_mcom);
                   $("#cart-MSG1-forgot").slideToggle();
                   setTimeout( function(){
                              $("#cart-MSG1-forgot").slideToggle();
                              }
                              , 3000 );
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   return false;
                   }
                   else
                   {
                   $(".cart_error").empty();
                   $(".cart_error").append(yyouhavenotbeenregisteredwithusyetgg_mcom);
                   
                   $("#passwordError").slideToggle();
                   setTimeout( function(){
                              $("#forpasid").focus();
                              $("#passwordError").slideToggle();
                              }
                              , 3000 );
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   return false;
                   }
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   },
                   error: function(response, textStatus, errorThrown)
                   {
                   console.log(response);
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   }
                   });
        }
    }
}
/********Login Page*************/
function checkEcomUserLogin()
{
	
	
	
    if(!checkNetworkConnection())
    {
		
    }
	else
    {
        
		        
		
		var emailid=$("#loginid").val().trim();
        var pass=$("#loginpass").val();
		
        if(emailid=='' || emailid == undefined)
        {
            //setTimeout(function(){$('.appypie-loader').hide();},1000);
            //navigator.notification.alert('Please Enter Email Id', setTimeout, "Error", "OK");
            $("#loginid").focus();
			 alertPopUp(eeerrorgg_mcom + ' !',ppleaseenteremailidggg_mcom);
            return;
        }
        else if(!checkEmail(emailid) || emailid=='')
        {
            //setTimeout(function(){$('.appypie-loader').hide();},1000);
          //navigator.notification.alert('Invalid Email Address...', setTimeout, "Error", "OK");
			alertPopUp(eeerrorgg_mcom + ' !',iiinvalidemailaddressggg_mcom);
            $("#loginid").focus();
            return;
        }
        
        else if(pass==''  || pass=='Password')
        {
            //setTimeout(function(){$('.appypie-loader').hide();},1000);
            //navigator.notification.alert('Please Enter Password', setTimeout, "Error", "OK");
			alertPopUp(eeerrorgg_mcom + ' !',ppppleaseenterpasswordgggg_mcom);
            $("#loginpass").focus();
            return;
        }
        else
        {
            checkEcomlogin(emailid,pass);
        }
    }
}
function checkEcomlogin(emailid,pass)
{
	
    if(!checkNetworkConnection())
    {
    }else
    {
		$('.appypie-loader').show();
        var wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#EcommUserLogin";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><EcommUserLogin xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#EcommUserLogin\"><email>'+emailid+'</email><password>'+pass+'</password><appid>'+appid+'</appid></EcommUserLogin></soap:Body></soap:Envelope>';
		console.log("checkEcomlogin_soapRequest: "+ soapRequest);
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
				   var strJSON = $(req.responseText).find("return").text();
				   console.log("strJSON: "+ strJSON);
				   if(strJSON == "NotExist") {
					alertPopUp(eeerrorgg_mcom + ' !',uuusernotexistggg_mcom);
					   return false;
					   
				   } else if(strJSON == "wrong password") {
					   alertPopUp(eeerrorgg_mcom + ' !',yyyouhaveenteredwrongpasswordggg_mcom);
					   return false;
				   }
				   else if(strJSON!='NotExist' && strJSON != 'wrong password')
				   {
					   var jsonData = jQuery.parseJSON(strJSON);
					   var uId, uProfleImage, uName; 
					   
					   uId= jsonData.id;
					   name= jsonData.name;
					   profileImage= jsonData.profileImage;
						console.log("UserDetails: "+ uId+ ", "+name+", "+CONSTANTIMAGEURL+"/"+profileImage);
					   window.localStorage.setItem("ecomuserid",emailid);
					   window.sessionStorage.setItem("ecomlogincheck","1");
					   window.localStorage.setItem("fooduserid",emailid);
					   window.localStorage.setItem("appUserEmail",emailid);
					   window.localStorage.setItem("userName",name);
					   window.sessionStorage.setItem("foodlogincheck","1");
					   getEcommerceData(sessionStorage.getItem("ecommerceIndex"));
					   window.localStorage.setItem("foodEcomUserId",uId);
					   var locTemp=CONSTANTIMAGEURL+"/"+profileImage;
					   //change by deepak
					   if(locTemp==CONSTANTIMAGEURL+"/media/user_space/"+appid+"/userprofile/")
					   {  
						 window.localStorage.setItem("uProfilePic", "");
					   }
						else
						{
						 window.localStorage.setItem("uProfilePic", locTemp);
					     setTimeout(function(){$('.appypie-loader').hide();},1000);
						}
					   /*
      MANAGE PROFILE IMAGE ON MENU
      */
      console.log("pImage: "+ window.localStorage.getItem("uProfilePic"));
      if(window.localStorage.getItem("uProfilePic") == undefined || window.localStorage.getItem("uProfilePic") == "") {
       document.getElementById('profile_add_pics').style.backgroundImage = "url('images/no-profile-img.png')";
      } else {
       document.getElementById('profile_add_pics').style.backgroundImage = "url('" + window.localStorage.getItem("uProfilePic") + "')";
      }
				   return false;
			   }
				   else
				   {
					 setTimeout(function(){$('.appypie-loader').hide();},1000);
					 return false;
				   }
               },
               error: function(response, textStatus, errorThrown)
               {
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               console.log(response);
               }
               });
    }
}

function myOrdersEcom()
{
	
	
	//closeSubMenu(this);
	document.getElementById("left-navs").style.display = 'block';
    // hide the lorem ipsum text
    document.getElementById("left-navs").style.display = 'none';
    if(!checkNetworkConnection())
    {
    }else
    {
        sessionStorage.setItem("AppPageName","Myorder");
        
		
		var MyOrderHtml ='<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
		<a onclick="goBackFromOrderPageEcomHome();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
		<h1 class="ui-title">'+appName+'</h1>\
		<a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>\
  <div class="my-order">\
	<div id="order-nav" style="display:none">\
    	<a href="#" class="active">OPEN ORDERS</a>\
    	<a href="#">COMPLETED</a>\
    	<a href="#">CANCELLED</a> \
    </div>\
	<div class="wrap userPage-text" id="paymentList">\
    </div> \
</div>'
//MyOrderHtml += ecomSubMenu();
	//MyOrderHtml += commonHeader2();
	appendHtml(MyOrderHtml,2,2,'food');
	resetEcomHeader();
	listOrdersEcom();
$('#order-nav a').on('click',function(e){
   var newVar = '';
   newVar = $(this).html();
   if(newVar.toUpperCase() =="COMPLETED") {
	   //
	   $("#order-nav a").each(function(){
		   $(this).removeClass("active");
		   if($(this).html()=="COMPLETED")
		   {
			   $(this).addClass('active');
			   //myOrdersEcom();
			   $(".userPage-text").empty();
			   $(".userPage-text").append('<center style="margin:100px 0 0 0"><h3 style="color:black;">'+ooordersnotfoundgggg_mcom+'</h3></center>');
		   }
	   });
	   //
   } else if(newVar.toUpperCase() =="CANCELLED") {
	   $("#order-nav a").each(function(){
		   $(this).removeClass("active");
		   if($(this).html()=="CANCELLED")
		   {
			   $(this).addClass('active');
			   //myOrdersEcom();
			   $(".userPage-text").empty();
			   $(".userPage-text").append('<center style="margin:100px 0 0 0"><h3 style="color:black;">'+ooordersnotfoundgggg_mcom+'</h3></center>');
	
		   }
	   });
   } else if(newVar.toUpperCase() =="OPEN ORDERS") {
	   $("#order-nav a").each(function(){
		   $(this).removeClass("active");
		   if($(this).html()=="OPEN ORDERS")
		   {
			   $(this).addClass('active');
			   //myOrdersEcom();
		   }
	   });
   } else{
	   //alertPopUp('Error !',$(this).html());
	   alertPopUp(eeerrorgg_mcom + ' !',$(this).html());
	   
   }
});
        
        
    }
}
function listOrdersEcom()
{
    if(!checkNetworkConnection())
    {
    }else
    {
		$('.appypie-loader').show();
        wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#ecommOrderListDetailXml";
        
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><ecommOrderListDetailXml xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#ecommOrderListDetailXml\"><appId>'+appid+'</appId><userId>'+window.localStorage.getItem("foodEcomUserId")+'</userId></ecommOrderListDetailXml></soap:Body></soap:Envelope>';
        console.log('foodEcomUserId---->'+localStorage.getItem("foodEcomUserId"));
        console.log('listOrdersEcom_soap---->'+soapRequest);
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               cache: false,
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
               console.log("success"+req.responseText);
               var strJSON = $(req.responseText).find("return").text();
			   console.log("listofOrdersEcom:"+ strJSON);
			   if(strJSON == "no order exists") {
				   //var tmpmsg = '<center style="margin:100px 0 0 0"><h3 //style="color:black;">There is no order history.</h3></center>';
				   var headerTmp = '<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
				  <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
				  <h1 class="ui-title">'+appName+'</h1>\
				  <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>';
				   
				  var tmpmsg ='<div class="thankyou"><h1>'+cartemptyg_mcom+'<span>'+eemptyg_mcom+'</span></h1><p>'+additemnowg_mcom+'</p><input type="button" style="background-color:'+primaryButtonBgColor+'" onclick="goToHomePage()" value="'+continue_shopping_mcom+'" '+secondaryColor+' class="continue-shopping"><input type="button" onclick="homeecom()" value="'+gotohomeg_mcom+'" style="display:none"></div>';

				   
//				  var tmpmsg = '<div class="thankyou">\
//				  <h1>\
//				   Your Orders is <span>empty!</span>\
//				  </h1>\
//				  <p>\
//				  Add items to it now\
//				  </p>\
//				  <input type="button" onclick="goToHomePage()" value="'+continue_shopping_mcom+'" class="continue-shopping">\
//				  <input type="button" onclick="homeecom()" value="Go to Home" style="display:none">\
//				</div>';
				   appendHtml(ecomHeader+tmpmsg,2,2,'food');
				   resetEcomHeader();
				   setTimeout(function(){
						$('#localEcomMenu').hide();
						$('#localEcomback').show();
						$('#localEcomCart').hide();
				   },500);
				   setTimeout(function(){$('.appypie-loader').hide();},1000);
			   } else{
				   listofOrdersEcom(strJSON);
				   console.log("listofOrdersEcom:"+ strJSON);
				   window.localStorage.setItem("saveEcomOrders", strJSON);
			   }
              
               
			   
               },
               error: function(response, textStatus, errorThrown)
               {
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               console.log(response);
               }
               });
    }
}

var noOfListItems = [];
var checkOrderStatus = [];
var checkOderDate = [];
var checkOderId = [];
var checkProductName = [];
var checkProductImage = [];
var checkPrice = [];
var checkProductId = [];
var tmpVar = 0;
var tmpVar2 = 0;
var caughtPImages = [];
function listofOrdersEcom(strJSON)
{
	
	var productOrderId = '';
	var productOrderDate = '';
	var productOrderStatus = '';
	var totalImages = '';
	
    console.log("in list of ordersvij"+strJSON);
    $.ajax({
           url: strJSON,
           dataType: "text",
           crossDomain: true,
           cache: false,
           success: function(xml) {
           html='';
		   var tmpHold = '';
           $(xml).find("orderDetail").each(function () {
			   
										var $productList = $(this);
                                        var sub_cat='';

			/*
		   OTHER DETAILS
		   */
		    var subTaxPrice = 0.0;
			$productList.find("otherDetail").each(function (i){
										
						productOrderId = $(this).find("orderId").text();
                        productOrderDate = $(this).find("orderDate").text();
						productOrderStatus = $(this).find("status").text();
						checkOrderStatus[tmpVar] = productOrderStatus;
						checkOderDate[tmpVar] = productOrderDate;
						checkOderId[tmpVar] = productOrderId;
						console.log("OTHERDETAILS: "+ productOrderId+" ,"+ productOrderDate +" ,"+productOrderStatus);
						console.log("checkOrderStatus0:" +checkOrderStatus[0]);
						console.log("checkOrderStatus1:" +checkOrderStatus[1]);
						tmpVar++;
						var totalAmount=(parseFloat($(this).find("delivery").text())+parseFloat($(this).find("tax").text())+parseFloat($(this).find("tip").text()))-(parseFloat($(this).find("discount").text())+parseFloat($(this).find("coupon").text()));
               
					    subTaxPrice = parseFloat(subTaxPrice)+parseFloat(totalAmount);
					    subTaxPrice=subTaxPrice.toFixed(2);
						var mistax=	$(this).find("misctax").html().trim();											
						if(mistax!="")
						{
							console.log("mistax->"+mistax);
							mistax = mistax.replace("<!--[CDATA[", "").replace("]]-->", "");												
							var mistaxobj = JSON.parse(mistax);												
							console.log("krishna mistax mistaxobj"+mistaxobj);							
							for (var i = 0; i < mistaxobj.length; i++) 
							{							
							  var taxType= mistaxobj[i].taxType;
							  var taxAmount= mistaxobj[i].taxAmount;
							  var tax= mistaxobj[i].tax;
							  var taxRate= mistaxobj[i].taxRate;							 														 
							  subTaxPrice=parseFloat(subTaxPrice)+parseFloat(tax);
							}
							console.log("krishna mistax subTaxPrice"+subTaxPrice);						
						}
			});
										
										
                                        $productList.find("product").each(function (i){
											
										 var productId= $(this).find("productId").text();
										 //checkProductId[tmpVar2] = productId;
                                         var productName= $(this).find("productName").text();
										 //checkProductName[tmpVar2] = productName;
										 var quantity= $(this).find("quantity").text();
										 var price= parseFloat($(this).find("price").text() * quantity)+parseFloat(subTaxPrice);
										 console.log("Price >> "+subTaxPrice);
										 //checkPrice[tmpVar2] = price;
										 var currency= $(this).find("currency").text();
										 var productImage= $(this).find("productImage").text();
										 //checkProductImage[tmpVar2] = productImage;
										 caughtPImages.push(productImage);
										 noOfListItems[i]=productId;

										 //tmpVar2++;
										 tmpHold ='order-row'
									console.log("caughtPImages.length: "+ caughtPImages.length);
											/*if(noOfListItems.length >= 3) {
												tmpHold ='<div class="order-row item-3">'
												totalImages += productImage;
												tmpHold ='order-row item-3'
											}
											else if(noOfListItems.length == 2) {
												tmpHold ='<div class="order-row item-3">'
												tmpHold ='order-row item-2'
											} else {
												tmpHold ='<div class="order-row item">'
												tmpHold ='order-row'
											}*/
											
											html+='<div class="'+tmpHold+'">\
											<div class="order-items">\
												<div class="product_box"><img src="'+productImage+'" alt="" onclick="viewOrderQuantity(\''+productName+'\',\''+quantity+'\', \''+price+'\', \''+currency+'\', \''+productOrderId+'\', \''+productOrderDate+'\')">\
													<div class="discription_box">\
													<p>'+productName+'</p>\
													</div>\
												</div>\</div>\
											<a href="#" class="order-id">Order ID #'+productOrderId+'</a>\
											<p class="order-status">\
												<time>'+productOrderDate+'</time>\
												<span>'+order_status_mcom+'</span>\
											</p>\
											<p class="order-status total">\
												<strong>Total '+currency+price+'</strong>\
												<span>'+productOrderStatus+'</span>\
											</p>\
											<div class="bottom-btn">\
											<a href="#" onclick="postReviewOfProductUI(\''+productId+'\')" on class="view-detail">'+wwriterreviewggg_mcom+'</a>\
											<a href="#" onclick="cancelEcomProduct(\''+productOrderId+'\')">'+cccancelggg_mcom+'</a>\
											<a href="#" onclick="openEcomDetailPage(\''+productId+'\')"class="reorder">'+rrreorderggg_mcom+'</a>\
											</div></div>'
																		
										
		   });
		   
		  console.log("ArrLenV: "+ $productList.find("product").length);
			   
										 
                        });
										 html+='</div>';
					
           
           $(".sub-menu,.e-mobileOH_subtab, .e-mobileOD,#billing_address").hide();
		   $(".userPage-text").append(html);
		   $("#paymentList").show();
		   console.log("lengthV: "+ checkOrderStatus.length);
		   
		 setTimeout(function(){$('.appypie-loader').hide();},1000);
           
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
			$(".item-3").each(function(){
				
					$(this).find(".product_box").wrapAll('<div class="product-scroll"></div>');
					var thisSize = $(this).find(".product_box").size();
					$(this).find(".product-scroll").width((134 * thisSize) - 12);
			})
			
           },
           error: function(XMLHttpRequest, textStatus, errorThrown) {
           console.log('fail');
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
           }
           });
    
}
function getbillingEcom(orderid,billship)
{
    if(!checkNetworkConnection())
    {
    }else
    {
        $('.appypie-loader').show();
        if(billship==1)
            billship='Billing';
        else
            billship='Shipping';
        
        wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#billingShippingXML";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><billingShippingXML xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#billingShippingXML\"><appId>'+appid+'</appId><orderId>'+orderid+'</orderId><userId>'+window.localStorage.getItem("id")+'</userId><billShip>'+billship+'</billShip></billingShippingXML></soap:Body></soap:Envelope>';
        
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
               console.log('billship===>'+strJSON);
               if(billship==1)
               billship='Billing';
               else
               billship='Shipping';
               
               billingDetailsEcom(strJSON,billship,orderid);
               
               
               },
               error: function(response, textStatus, errorThrown)
               {
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               console.log(response);
               }
               });
    }
}
function billingDetailsEcom(strJSON,billship,orderid)
{
    if(!checkNetworkConnection())
    {
    }else
    {
        $.ajax({
               url: strJSON,
               dataType: "text",
               crossDomain: true,
               cache: false,
               success: function(xml) {
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               $(xml).find("billingShippingList").each(function () {
                                                       var Billing= $(this).find("Billing").text();
                                                       console.log('result====>'+Billing);
                                                       
                                                       
                                                       
                                                       var name= $(this).find("name").text();
                                                       var address= $(this).find("address").text();
                                                       var city= $(this).find("city").text();
                                                       var state= $(this).find("state").text();
                                                       var country= $(this).find("country").text();
                                                       var zip= $(this).find("zip").text();
                                                       var phone= $(this).find("phone").text();
                                                       
                                                       $("#"+billship+orderid).empty();
                                                       $("#"+billship+orderid).append(name+'<br/>'+address+'<br/>'+city+'<br/>'+state+'<br/>'+country+'<br/>'+zip+'<br/>'+phone);
                                                       });
               
               },
               error: function(XMLHttpRequest, textStatus, errorThrown) {
               console.log('fail');
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
               }
               });
    }
}
function getOrderEcom(orderid)
{
    if(!checkNetworkConnection())
    {
    }else
    {
        $('.appypie-loader').show();
        wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#ecommOrderDetail";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommOrderDetail xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#ecommOrderDetail\"><appId>'+appid+'</appId><orderId>'+orderid+'</orderId></ecommOrderDetail></soap:Body></soap:Envelope>';
        
        console.log(soapRequest);
        
        jQuery.support.cors = true;
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
               var strJSON = $(xmlret).find("return").text();
               console.log(strJSON);
               getOrderDetailsXMLEcom(strJSON,orderid);
               
               
               },
               error: function(response, textStatus, errorThrown)
               {
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               console.log(response);
               }
               });
    }
}
function getOrderDetailsXMLEcom(strJSON,orderid)
{
	
	
    if(!checkNetworkConnection())
    {
    }else
    {
        $.ajax({
               url: strJSON,
               dataType: "text",
               crossDomain: true,
               cache: false,
               success: function(xml) {
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               var xml =jQuery.parseXML(xml);
               html='';
               var totalAmount=0;
               var currency='';
               $(xml).find("product").each(function () {
                                           var customOption=$(this).find("custom_option").text();
                                           if(customOption.length>1)
                                           {
                                           html+='<span class="e-mobileOD-list"><label>'+ppproductnnamegg_mcom+':</label>'+$(this).find("productName").text()+'('+customOption+')</span>';
                                           }
                                           else
                                           {
                                           html+='<span class="e-mobileOD-list"><label>'+ppproductnnamegg_mcom+':</label>'+$(this).find("productName").text()+'</span>';
                                           }
                                           currency='';
                                           currency=$(this).find("currency").text();
                                           sessionStorage.setItem("currency",currency);
                                           html+='<span class="e-mobileOD-list"><label>'+qqquantitygggg_mcom+':</label>'+$(this).find("quantity").text()+'</span>';
                                           html+='<span class="e-mobileOD-list"><label>'+ppppricegggg_mcom+':</label>'+$(this).find("price").text()+'</span>';
                                           totalAmount +=parseInt($(this).find("quantity").text())*parseFloat($(this).find("price").text());
                                           
                                           });
               totalAmount=parseFloat(totalAmount)+(parseFloat($(xml).find("delivery").text())+parseFloat($(xml).find("tax").text())+parseFloat($(xml).find("tip").text()))-(parseFloat($(xml).find("discount").text())+parseFloat($(xml).find("coupon").text()));
               
               
               
               var delivery=$(xml).find("delivery").text();
               var tax=$(xml).find("tax").text();
               var tip=$(xml).find("tip").text();
               var discount=$(xml).find("discount").text();
               var coupon=$(xml).find("coupon").text();
               if(parseFloat(delivery) > 0)
               {
               console.log('1');
               html+='<span class="e-mobileOD-list"><label>'+dddddeliverychargeggg_mcom+':</label>'+delivery+'</span>';
               }
               if(parseFloat(tax) > 0)
               {
               console.log('2');
               html+='<span class="e-mobileOD-list"><label>'+tax_mcom+'</label>'+tax+'</span>';
               }
               if(parseFloat(tip) > 0)
               {
               console.log('3');
               html+='<span class="e-mobileOD-list"><label>'+tttipggg_mcom+':</label>'+tip+'</span>';
               }
               if(parseFloat(discount) > 0)
               {
               console.log('4');
               html+='<span class="e-mobileOD-list"><label>'+discount_mcom+':</label>'+parseFloat(discount)+'</span>';
               }
               if(parseFloat(coupon) > 0)
               {
               console.log('5');
               html+='<span class="e-mobileOD-list"><label>'+ccccouponddiscountgggg_mcom+':</label>'+coupon+'</span>';
               }
              
			   
			   var tempmisctax=$(xml).find("misctax").text();
               tempmisctax=$.trim(tempmisctax);
               //console.log("misctax.length---->"+misctax.length);
               console.log("the value of totalAmount is-->"+totalAmount);
               var newTotalAmt=0.00;
               if(tempmisctax!="")
               {
               var misctax=jQuery.parseJSON(tempmisctax);
               for(c=0;c<misctax.length;c++)
               {
                //console.log("the value of c");
               var taxMiscAmount=misctax[c].tax;
                html+='<span class="e-mobileOD-list"><label>'+misctax[c].taxType+':</label>'+formatCurrency(taxMiscAmount,1)+'</span>';
                //console.log("the value of c-->"+c);
               newTotalAmt=parseFloat(newTotalAmt) + parseFloat(misctax[c].tax);
               
               }
               newTotalAmt=newTotalAmt.toFixed(2);
               }
			   
			   
			   
			   
               console.log("the value of totalAmount-->"+newTotalAmt);
               totalAmount=parseFloat(newTotalAmt)+parseFloat(totalAmount);
               totalAmount=formatCurrency(totalAmount,1);
               console.log('Complete_Total_Amount: '+ currency+totalAmount);
               html+='<span class="e-mobileOD-list"><label>'+tttotalpayableaamountggg_mcom+':</label>'+currency+totalAmount+'</span>';
               if($(xml).find("pickupComment").text())
               {
               var comment=$(xml).find("pickupComment").text();
               console.log('comment length greater then 0===================>'+comment);
               if(comment.length>2)
               html+='<span class="e-mobileOD-list" style="white-space:pre-wrap !important;"><label>'+cccommentgggg_mcom+':</label>'+comment+'</span>';
               }
               html+='<span class="e-user_edit" onclick="wantToReorder(\'ecom\',\''+orderid+'\')">'+rrrrreordergggg_mcom+'</span>';
               console.log("html--->"+html);
               $("#Order"+orderid).empty();
               $("#Order"+orderid).append(html);
               },
               error: function(XMLHttpRequest, textStatus, errorThrown) {
               console.log('fail');
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
               }
               });
    }
}
function addecomOrder()
{

    var bfname=document.getElementById("bfname").value;
    var bpNo=document.getElementById("bpNo").value;
    var bemail=document.getElementById("bemail").value;
    window.sessionStorage.setItem("OrderEmailId",bemail);
    var bsAddress=document.getElementById("bsAddress").value;
    var bCity=document.getElementById("bCity").value;
    var bState=document.getElementById("bState").value;
    var bZip=document.getElementById("bZip").value;
    var bCountry=document.getElementById("bCountry").value;
    
    var sfname=document.getElementById("sfname").value;
    var spNo=document.getElementById("spNo").value;
    var ssAddress=document.getElementById("ssAddress").value;
    var sCity=document.getElementById("sCity").value;
    var sState=document.getElementById("sState").value;
    var sZip=document.getElementById("sZip").value;
    var sCountry=document.getElementById("sCountry").value;
    
    
    if(window.sessionStorage.getItem("ecomcart") )
    {
        cart=window.sessionStorage.getItem("ecomcart");
        console.log('Cart====>'+cart);
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
                var configTitle=sessionStorage.getItem("ecomconfigTitleProduct"+x);
                var configTitle1=configTitle.split(',');
                var sendData= sessionStorage.getItem("ecomconfigValueProduct"+x);
                var sendData1=sendData.split(',');
                var coustomOption='';
                for(var i=0;i<sendData1.length;i++)
                {
                    coustomOption+=configTitle1[i]+':'+sendData1[i];
                    if((i+1) != sendData1.length)
                    {
                        coustomOption+='|';
                    }
                }
                console.log('coustomOption---->'+coustomOption);
                
                order+='{"name":"'+productName+'","qty":"'+qty+'","price":"'+price+'","sku":"'+productId+'"}';
                if(coustomOption.length > 2)
                {
                    html+='{"productId":"'+productId+'","qty":"'+qty+'","custom_option":"'+coustomOption+'","price":"'+price+'"}';
                }
                else
                {
                    html+='{"productId":"'+productId+'","qty":"'+qty+'","custom_option":""}';
                }
                counter=counter+1;
            }
        }
        console.log("1");
        order+=']'
        html+=']';
    }
    var billingAddress = '{"name":"'+bfname+'","email":"'+bemail+'","phone":"'+bpNo+'","line1":"'+bsAddress+'","city":"'+bCity+'","countryCode":"'+bCountry+'","postalCode":"'+bZip+'","state":"'+bState+'"}';
    sessionStorage.setItem("LastNamePayment",bfname);
    sessionStorage.setItem("billingAddressPayment",billingAddress);
    sessionStorage.setItem("OrderPayment",order);
    console.log("billingAddressvi:"+ billingAddress);
    var billing='{"billShip":"Billing","name":"'+bfname+'","address":"'+bsAddress+'","city":"'+bCity+'","state":"'+bState+'","country":"'+bCountry+'","zip":"'+bZip+'","phone":"'+bpNo+'"}';
    
    var shipping='';
    if(document.getElementById("show_billing_address").checked)
    {
        shipping='{"billShip":"Shipping","name":"'+sfname  +'","address":"'+ssAddress+'","city":"'+sCity+'","state":"'+sState+'","country":"'+sCountry+'","zip":"'+sZip+'","phone":"'+spNo+'"}';
    }
    else
    {
        shipping='{"billShip":"Shipping","name":"'+bfname  +'","address":"'+bsAddress+'","city":"'+bCity+'","state":"'+bState+'","country":"'+bCountry+'","zip":"'+bZip+'","phone":"'+bpNo+'"}';
    }
    console.log("bfname--->"+bfname);
    if(bfname.indexOf(' '))
    {
        var blfname=bfname.split(' ');
        console.log("bfname--->1"+bfname);
        var blname='';
        console.log("bfname--->2"+bfname);
        if(blfname[1] != null)
        {
            blname=blfname[1];
        }
    }
    else
    {
        var blname='';
    }
    console.log("blname--->"+blname);
    
	var appnameValue="";
	if(localStorage.getItem("AppName")=="BidBookIQ")
	{
	appnameValue="BidBookIQ"+"&trade;";
    }
	else
	{
	appnameValue=localStorage.getItem("AppName");
	}
	
	
	
    var paypalAddFormHtml='<!-- Specify a Buy Now button. -->\
    <input type="hidden" name="cmd" value="_xclick">\
    <input type="hidden" name="first_name" value="'+bfname+'" >\
    <input type="hidden" name="last_name" value="'+blname+'" >\
    <input type="hidden" name="address1" value="'+bsAddress+'" >\
    <input type="hidden" name="city" value="'+bCity+'">\
    <input type="hidden" name="state" value="'+bState+'">\
    <input type="hidden" name="zip" value="'+bZip+'">\
    <input type="hidden" name="country" value="'+bCountry+'">\
    <input type="hidden" name="night_phone_a" value="'+bpNo+'">\
    <input type="hidden" name="night_phone_b" value="{MOB2}">\
    <input type="hidden" name="night_phone_c" value="{MOB3}">\
    <input type="hidden" name="email" value="'+bemail+'">\
    <!-- Specify details about the item that buyers will purchase. -->\
    <input type="hidden" name="item_name" value="'+pppaymentofproductorderforgggg_mcom+' '+appnameValue+'('+localStorage.getItem("applicationID")+')">\
    <input type="hidden" name="amount" value="'+parseFloat(window.sessionStorage.getItem("ecomgtotal")).toFixed(2)+'">\
    <input type="hidden" name="quantity" value="1">\
	<input name=â€�bnâ€� value=â€�AppyPie_SPâ€� type=â€� hiddenâ€œ>\
    <input type="hidden" name="currency_code" value="'+sessionStorage.getItem("currency")+'">\
    <input type="hidden" name="custom" value="1">';
    
    
    console.log('paypalAddFormHtml');
    sessionStorage.setItem('paypalFormHtml',paypalAddFormHtml);
    
    console.log("4");
    console.log(html);
    console.log(billing);
    console.log(shipping);
    
    sessionStorage.setItem("ecomudetails",html);
    sessionStorage.setItem("ecomubilling",billing);
    sessionStorage.setItem("ecomushipping",shipping);
    paymentTypeView("ecom");
}

function successViewEcom(paymentResult,paymentType){
    $('.appypie-loader').show();
    $("#mainbackfoodecom").hide();
	console.log("successViewEcom_paymentResult: "+paymentResult);
	console.log("successViewEcom_paymentType: "+paymentType);
    /*var success_HTML='<section class="home_screen">\
    <div class="page-text"><div id="scroll_contener" class="scroll_contener">\
    <div class="appypie-login thankyoupage"><h2 id="message"></h2><p id="mess">\
    <strong id="orderId">Order ID: </strong></p><div class="login-feald">\
    <a id="can" class="login_btn3" onclick="cancelOrderEcom();">Cancel</a>\
    <a id="retry" class="login_btn" onclick="retryOderEcom();">Retry</a>\
	<div class="post-review">\<h2>Post Review</h2><div class="rating">\
		<b></b>\
    </div>\
    <input placeholder="Review Title" id="comentInputTitle" data-role="none" type="text">\
    <textarea data-role="none" id="comentInputTxt" placeholder="Comment"></textarea>\
    <input value="Post Review" onclick="postReviewOfProduct();" type="button">\
	</div>\
    </div></div></section>';*/
	//success_HTML += ecomSubMenu();
    //appendHtml(success_HTML,2,2,'ecomSuccess');
	
	
	 var tmpmsg = '<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a><a onclick="clearAllHistoryAfter();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a><h1 class="ui-title">'+appName+'</h1><a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header><div class="thankyou"><h1>'+ttthankggg_mcom+' <span>'+yyyouggg_mcom+'!</span></h1><p id="message"></p><p id="mess"><strong id="orderId">'+ooorderidddggg_mcom+': </strong></p><input type="button" onclick="clearAllHistory()" value="'+cccontinuemypoordersggg_mcom+'" class="continue-shopping"><input type="button" onclick="homeecom()" value="'+gggtohhomegggg_mcom+'" style="display:none"></div>';
	//tmpmsg += ecomSubMenu();
	appendHtml(tmpmsg,2,2,'food');
	resetEcomHeader();
	window.sessionStorage.setItem("ecomcartQuantitywise", 0);
	
    if(paymentResult=='Success')
    {
        
        if(paymentType)
        {
            var message='You will receive an email, when your order is ready to be delivered';
            document.getElementById("message").innerHTML=wwevereceivedouordergg_mcom;
            
        }
        else
        {
            var message='Thank you for your Order. Your transaction has been completed, and a receipt for your purchase has been emailed to you for your records<br><strong id="orderId"></strong><br><strong id='+window.sessionStorage.getItem("transectionId")+'></strong>';
            document.getElementById("message").innerHTML=yyourorderwassuccessfulgg_mocom +'!';
            
        }
        
        
        document.getElementById("mess").innerHTML=message;
        
        if(sessionStorage.getItem("PaymentTypeFlag") == "paypal")
        {
            document.getElementById("orderId").innerHTML='Order ID:'+sessionStorage.getItem("foodOrderId");
            document.getElementById("PayId").innerHTML='Payment ID:'+sessionStorage.getItem("foodPaymentId");
        }
        document.getElementById("retry").style.display="none";
        document.getElementById("can").style.display="none";
    }
    else
    {
        var message='Your order could not be processed. Please try placing the order again.';
        document.getElementById("mess").innerHTML=message;
        document.getElementById("message").innerHTML=wwearesorrygg_com + '!';
        document.getElementById("pay").style.display="none";
    }
	
}

/*
MOVING TO HOME PAGE FROM THANKS PAGE
*/

function clearAllHistoryAfter() {
 history.go(-(history.length - 2));
 //setTimeout(function(){getEcommerceData();},1000);
}

/*
POST REVIEW OF PRODUCTS(ADD REVIEW)
*/
function postReviewOfProductUI(productId) {
	
	
	
	var tmpHeader = '<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
   <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
   <h1 class="ui-title">'+appName+'</h1>\
   <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>';
   
	var reviewPage = '<div class="thankyou"><h1>'+wwwriteeviewgg_mcom+'</h1><p>'+postreviewguidelinesggg_mcom+'</p><div class="post-review">\<h2>'+pppposteviewggg_mocom+'</h2><div class="rating"><b></b></div><input placeholder="'+eviewitleggg_mcom+'" id="comentInputTitle" data-role="none" type="text"><textarea data-role="none" id="comentInputTxt" placeholder="'+ooommentgg_mcom+'"></textarea><input value="'+ppppostreviewgg_mcom+'" onclick="postReviewOfProduct(\''+productId+'\')" type="button"></div></div>';
	
	
	
	/*reviewPage += '<div class="post-review">\<h2>Post Review</h2><div class="rating">\
		<b></b>\
		</div>\
    <input placeholder="Review Title" id="comentInputTitle" data-role="none" type="text">\
    <textarea data-role="none" id="comentInputTxt" placeholder="Comment"></textarea>\
    <input value="Post Review" onclick="postReviewOfProduct(\''+productId+'\')" type="button"></div>'*/
	
	appendHtml(ecomHeader+reviewPage,3,3,'food');
	resetEcomHeader();
	$('.rating b').raty({
		click: function(score, evt) {
		//alert('ID: '+this.id+' Score: '+score);
		window.localStorage.setItem("postRating", score); 

		$('.rating span').attr("data-value",score);
		}
	});
	
	
}



function postReviewOfProduct(productId) { //addReview(appId,productId,email,review)
	
	
	
	var inputTitle = document.getElementById("comentInputTitle").value;
	console.log("post title is : " + inputTitle);
	var imputArea = document.getElementById("comentInputTxt").value;
	console.log("post imputArea is : " + imputArea);
	var uEmail = window.localStorage.getItem("appUserEmail"); //email
	//uEmail = "manikanth@onsinteractive.com";
	console.log("post uEmail is : " + uEmail);
	var appyId = window.localStorage.getItem("applicationID");
	console.log("post appyId is : " + appyId);
	console.log("post productId is : " + productId);
	//var productId = localStorage.getItem("EcomProductId");
	var postRate = window.localStorage.getItem("postRating");
	console.log("post postRate is : " + postRate);
	//inputTitle = document.getElementById("comentInputTitle").value="";
	//imputArea = document.getElementById("comentInputTxt").value="";
	console.log("PostData: "+inputTitle+ ", "+ imputArea+", "+uEmail+", "+appyId+", "+productId +", "+ parseInt(postRate));
	//return false;
	var mcomReviewListHTML = '';
	if(inputTitle == null || inputTitle == undefined || inputTitle == "") {
		alertPopUp(eeeerrorggg_mcom + '!',ppleaseenterthereviewtitleggg_mcom);
		return false;
	}
	else if(imputArea == null || imputArea == undefined || imputArea == "") {
		alertPopUp(eeeerrorggg_mcom + '!',pppleaseenterthereviewtextggg_mcom);
		return false;
	} else if(appyId == undefined || appyId =="" || productId == undefined || productId == "" || uEmail == undefined || uEmail == "" || uEmail == null) {
		alertPopUp(eeeerrorggg_mcom + '!',rrrequiredfieldsaremissingggg_mcom);
		return false;
	} else if(!checkNetworkConnection()) {
		alertPopUp(nnetworkerrorggg_mcom + '!',ppleasecheckyourinternetconnectionggg_mcom);
		return false;
	} else { 
		$('.appypie-loader').show();
		wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#addReview";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><addReview xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#addReview\"><appId>'+appyId+'</appId><productId>'+productId+'</productId><email>'+uEmail+'</email><review>'+imputArea+'</review><title>'+inputTitle+'</title><rating>'+postRate+'</rating></addReview></soap:Body></soap:Envelope>';
        console.log("wsUrl--->"+wsUrl);
        console.log("postReviewOfProduct_soapRequest--->"+soapRequest);
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
			  
               console.log("postReviewOfProduct_Response "+ strJSON); 
               
			   if(strJSON == "success") {
				   myOrdersEcom();
			   } else {
				   alertPopUp(eeeerrorggg_mcom + '!',ooorderhasbeennottakenorcompletedgggg_mcom);
			   }		
				setTimeout(function(){$('.appypie-loader').hide();},1000);				
               },
               error: function(response, textStatus, errorThrown)
               {
               console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
			   return false;
	}

}
/*
END POST REVIEW(END REVIEW)
*/

function paymentSuccessEcom()
{
    sessionStorage.removeItem("ecomushipping");
    sessionStorage.removeItem("ecomubilling");
    sessionStorage.removeItem("ecomushipping");
    $('.appypie-loader').show();
    myOrdersEcom();
    sessionStorage.removeItem("ecomcart");
}
function cancelOrderEcom()
{
    sessionStorage.removeItem("ecomushipping");
    sessionStorage.removeItem("ecomubilling");
    sessionStorage.removeItem("ecomushipping");
    $('.appypie-loader').show();
    getEcommerceData(sessionStorage.getItem("ecommerceIndex"));
}

function retryOderEcom()
{
    paymentTypeView(sessionStorage.getItem("payTypeFoodOrEcom"));
}

function payNowCall() {
	//PayNow();
	//checkPaymentTabs("Order by Phone");
	/*var payNowHTML = '<div class="payment-mode">\
	<div class="payment-nav tabs-click" dir="paymentTabs">\
		<a href="#" onclick="checkPaymentTabs(this);" class="card">Card</a>\
    	<a href="#" onclick="checkPaymentTabs(this);" class="obp">Order by Phone</a>\
    	<a href="#" onclick="checkPaymentTabs(this);" class="paypal">Paypal</a>\
    	<a href="#" onclick="checkPaymentTabs(this);" class="cod">COD</a>\
	</div>\
	<div class="wrap" id="paymentTabs">\
	 <div class="payment-tab tabs-view" id="payCard">\
  		<div class="login-feald" style="display:none">\
            <label>Card Type</label>\
            <select id="card_type" data-role="none" >\
            <option value="visa">visa</option>\
            <option value="mastercard">mastercard</option>\
            <option value="amex">amex</option>\
            <option value="discover">discover</option>\
            </select>\
        </div>\
        <div class="login-feald">\
        	<label style="display:none">Card Number</label>\
            <input data-role="none" type="number" id="cnumber" placeholder="XXXX-XXXX-XXXX-XXXX" class="card-number">\
        </div>\
        <div class="expiry-date">\
        <span>\
		<input type="number" data-role="none"  id="ExpairyMonth" placeholder="MM">\
        Expiry Month\
        </span>\
        <span class="mid">\
        &nbsp;\
        </span>\
         <span>\
         	<input type="number" data-role="none" id="ExpairyYear" placeholder="YYYY">\
        Expiry Year\
        </span>\
        </div>\
        <div class="login-feald" style="display:none">\
        <label style="display:none">Card Holder</label>\
        <input data-role="none" type="text" id="cHolder" placeholder="Card Holder" class="card-number" style="display:none">\
        </div>\
        <div class="login-feald" style="display:none">\
        <label>Card Security Code</label>\
        </div>\
		 <div class="cvv">\
        	<input data-role="none" type="number" id="cvvCode" placeholder="CVV">\
			<p>check the back of your credit card for cvv</p>\
        </div>\
    </div>\
    <div class="payment-tab tabs-view">\
		<p>You can order by calling <a id="payThroughCallNumber" onClick="submitOrderCCPhoneEcom(\'1\');"> </a><span id="payThroughCallText"></span></p>\
        <a onClick="submitOrderCCPhoneEcom(\'1\');" class="BtText" id="callToPay">Call Now</a>\
    </div>\
     <div class="payment-tab tabs-view">\
		<p>Please click on the confirm to pay through Paypal</p>\
        <a onClick="userRegistrationPaypalExpress();" class="BtText">Confirm</a>\
    </div>\
     <div class="payment-tab tabs-view">\
		<p>Please click On confirm button to Place the Order</p>\
        <p><a onClick="submitOrder(\'1\');" class="BtText">Confirm</a></p>\
    </div>\
    </div>\
	</div><footer id="footer-nav">\
	<a href="#" class="next" id="payBtn" onclick="thankYouCall()">PLACE ORDER</a>\
</footer>'
	//thankYouCall();
	//ecomSubMenu()
	appendHtml(payNowHTML,4,4,'food');	*/
	$(".tabs-click").tabsClick();
    $('.appypie-loader').hide();
	 setTimeout(function(){ $("#payBtn").hide();},100);
	//return false;
}

function mcomNotifications() {
	
	
	//closeSubMenu(this);
	document.getElementById("left-navs").style.display = 'block';
    // hide the lorem ipsum text
    document.getElementById("left-navs").style.display = 'none';
	var headerTmp = '<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
      <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
      <h1 class="ui-title">'+appName+'</h1>\
      <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>';
	var mcomNotificationHTML = '';
	var strJSON = '';
	strJSON = window.localStorage.getItem("saveEcomOrders");
	
	if(strJSON == "" || strJSON == undefined) {
		//mcomNotificationHTML = '<div class="reviews">\
									//<center><h2>No Notification</h2></center>\</div>';
		mcomNotificationHTML='<div class="thankyou scrolling-head"><h1>'+nnnotificationggg_mcom+'<span>'+eeeeemptygggg_mcom+'!</span></h1><p>'+ttthereisnonotificationlistgggggg_mocom+'</p><input type="button" onclick="goToHomePage()" value="'+continue_shopping_mcom+'" class="continue-shopping" style="display:none"><input type="button" onclick="homeecom()" value="'+ggotohhomeggg_mcpm+'" style="display:none"></div>';
		appendHtml(ecomHeader+mcomNotificationHTML,2,2,'food');
		resetEcomHeader();
		return false;
		
	} else {
		
	var productOrderId = '';
	var productOrderDate = '';
	var productOrderStatus = '';
	
    console.log("Notification_res: "+strJSON);
    $.ajax({
           url: strJSON,
           dataType: "text",
           crossDomain: true,
           cache: false,
           success: function(xml) {
           html='';
		   var tmpHold = '';
		   mcomNotificationHTML = '<div class="notifications scrolling-head">'
           $(xml).find("orderDetail").each(function () {
			   
										var $productList = $(this);
                                        var sub_cat='';

			/*
		   OTHER DETAILS
		   */
		   
			$productList.find("otherDetail").each(function (i){
										
						productOrderId = $(this).find("orderId").text();
                        productOrderDate = $(this).find("orderDate").text();
						productOrderStatus = $(this).find("status").text();
						console.log("OTHERDETAILS: "+ productOrderId+" ,"+ productOrderDate +" ,"+productOrderStatus);
						console.log("checkOrderStatus0:" +checkOrderStatus[0]);
						console.log("checkOrderStatus1:" +checkOrderStatus[1]);
						
											
		   });
										

                                        $productList.find("product").each(function (i){
											
										 var productId= $(this).find("productId").text();
										
                                         var productName= $(this).find("productName").text();
										 
										 var quantity= $(this).find("quantity").text();
										 var price= $(this).find("price").text();
										 
										 var currency= $(this).find("currency").text();
										 var productImage= $(this).find("productImage").text();
										 
							mcomNotificationHTML+='<div class="wrap">\
										<div class="notification">\
											<h4>'+productName+'</h4>\
											<p>\
											'+quantity+' ' +iiiiteminyourordergggg_mcom+ ' '+productOrderId+ ' ' +hhhhasbeenshippedbysellerggggg_mcom+'\
											</p>\
											<p class="time">\
											<time>'+productOrderDate+'</time>\
											</p>\
										</div>\
								</div>'
										
										
		   });
		   							 
                        });
				mcomNotificationHTML+='</div>';
				//mcomNotificationHTML+= ecomSubMenu();
			appendHtml(ecomHeader+mcomNotificationHTML,2,2,'food');
			resetEcomHeader();
			setTimeout(function(){$('.appypie-loader').hide();},1000);
         	
           },
           error: function(XMLHttpRequest, textStatus, errorThrown) {
           console.log('fail');
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
           }
           });
		   
		   return false;
		   	
	}

}

function mcomSetting() {
	
	//closeSubMenu(this);
	document.getElementById("left-navs").style.display = 'block';
    // hide the lorem ipsum text
    document.getElementById("left-navs").style.display = 'none';
	//var mcomSettingHTML = "mcomSetting Call";
	var headerTmp = '<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
      <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
      <h1 class="ui-title">'+appName+'</h1>\
      <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>';
	var mcomSettingHTML = '<div class="dashboard none-margin scrolling-head">\
	<div class="wrap">	\
    <div class="cart-MSG" id="cart-MSG1" style="display:none">\
        <div class="cart_success">'+iinformationupdatedssuccessfullygg_mcom+'</div>\
	</div>\
    <div class="cart-MSG" id="cart-MSG2" style="display:none">\
		<div class="cart_error"></div>\
	</div>\
	<div class="profile-image">\
    	<div>\
        	<img src="images/no-profile-img.png" id="ecomSettingsProfile">			\
        </div>\
    </div>\
	<div class="profile-detail">\
    	<h2>'+contact_information_mcom+'</h2>\
        <strong>'+name_mcom+'<span class="required">*</span></strong>\
        <input data-role="none" id="fname" type="text" placeholder="'+nnnameggg_mcom+'">\
        <strong>'+eeemailaaddressggg_mcom+'<span class="required">*</span></strong>\
        <input data-role="none" id="emailid" type="text" placeholder="'+eeenteryouremailiddggg_mcom+'" readonly="readonly">\
        <strong>'+telephone_mcom+' No<span class="required">*</span></strong>\
        <input data-role="none" id="telno" type="text" placeholder="'+tttelephonenoggg_mcom+'">\
        <a onClick="updatePesonalInformation();" class="e-user_save">'+uupdateccontactinformationgg_mcom+'</a>\
    </div>\
    <div class="cart-MSG" id="cart-MSG1" style="display:none">\
        <div class="cart_success">'+bbbillinginformationupdatedsuccessfullyggg_mcom+'</div>\
	</div>\
	<div class="billing-address">\
    <h2>'+billing_address_mcom+'</h2>\
        <strong>\
        '+name_mcom+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" id="bfname" type="text" placeholder="'+nnnameggg_mcom+'">\
        <strong>\
        '+phone_mcom+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" id="bpNo"  type="text" placeholder="'+tttelephonenoggg_mcom+'">\
        <strong>\
        '+sstreetaaddressggg_mcom+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" id="bsAddress"  type="text" placeholder="'+sstreetaaddressgg_mcom+'">\
        <strong>\
        '+city_mcom+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" id="bCity"  type="text" placeholder="'+eenteryyourccitygg_mcom+'">\
        <strong>\
        '+state_province_mcom+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" id="bState" type="text" placeholder="'+eeentersstatepprovincegg_mcom+'">\
        <strong>\
        '+zzipppostalcodegg_mcom+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" id="bZip"  type="text" placeholder="'+eeenterzzippostalcodegg_mcom+'">\
        <strong>\
        '+country_mcom+'<span class="required">*</span>\
        </strong>\
        <div id="bcountryTry"></div>\
         <strong class="showShippingAddress" onClick="showShippingAddress(this)" dir=".shipping-address">\
        	<span></span>\
        	<input data-role="none" type="checkbox" id="show_billing_address"/> '+shipping_mcom +' '+aaaddressdifferentfrombillingaaddressgg_mcom +' \
        </strong>\
        <a onClick="updateDefaultBilling();" class="e-user_save">'+uupdatebillingiinformationgg_mcom+'</a>    \
    </div>\
    <div class="cart-MSG" id="cart-MSG2" style="display:none">\
		<div class="cart_success">'+sshippinginformationupdatedsuccessfullyggg_mcom+'</div>\
	</div>\
	<div class="shipping-address">\
    	<h2>'+shipping_address_mcom+'</h2>\
		<strong>\
        '+name_mcom+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" type="text"  id="sfname" placeholder="'+first_name_mcom+'">\
        <strong>\
        '+telephone_mcom+' No<span class="required">*</span>\
        </strong>\
        <input data-role="none" type="text"  id="spNo" placeholder="'+tttelephonenoggg_mcom+'">\
        <strong>\
        '+sstreetaaddressgg_mcom+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" type="text"  id="ssAddress" placeholder="'+sstreetaaddressgg_mcom+'">\
        <strong>\
        '+city_mcom+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" type="text"  id="sCity" placeholder="'+eenteryyourccitygg_mcom+'">\
        <strong>\
        '+state_province_mcom+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" type="text"  id="sState" placeholder="'+eeentersstatepprovincegg_mcom+'">\
        <strong>\
        '+zip_postal_code_mcom+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" type="text"  id="sZip" placeholder="'+eeenterzzippostalcodegg_mcom+'">\
        <strong>\
        '+country_mcom+'<span class="required">*</span>\
        </strong>\
        <div id="scountryTry"></div>\
        <a onClick="updateDefaultShipping()" class="e-user_save">'+uupdatesshippingiinformationgg_mcom+'</a>\
    </div>\
    </div>\
</div>';
	appendHtml(ecomHeader+mcomSettingHTML,2,2,'food');	
	resetEcomHeader();
    $('.appypie-loader').hide();
	showContactInfoedit(); // function defined in foodordering.js
	getCountry("addressbook");
	console.log("pImage: "+ window.localStorage.getItem("uProfilePic"));
	if(window.localStorage.getItem("uProfilePic") == undefined || window.localStorage.getItem("uProfilePic") == "") {
		document.getElementById("ecomSettingsProfile").src = "images/no-profile-img.png";
	} else {
		document.getElementById("ecomSettingsProfile").src = window.localStorage.getItem("uProfilePic");
	}
	
}

function thankYouCall() {
	var thankYouHTML='<div class="thankyou">\
		<h1>\
			Thank <span>you</span>\
		</h1>\
		<p>\
		lorem ipsum doler sit amet is dummy \
	text for only fomat and displaying \
	lorem ipsum doler sit amet is dummy \
	text for only fomat\
		</p>\
		<input type="button" style="background-color:'+primaryButtonBgColor+'" onclick="goToHomePage()" value="'+continue_shopping_mcom+'" class="continue-shopping">\
		<input type="button" onclick="homeecom()" value="Go to Home">\
	</div>';
	
    appendHtml(thankYouHTML + ecomSubMenu(),4,4,'food');	
	resetEcomHeader();
    $('.appypie-loader').hide();
	//return false;
}


function searchEvent(e, obj) {
	var thisObj = $(obj);
	var type = e.type;
	if(type == "focus")
	{
		thisObj.removeClass("on");
	}
	if(type == "keyup")
	{
		var mEvent = e || window.event;
		var mPressed = mEvent.keyCode || mEvent.which;
		if (mPressed == 13) {
			setTimeout(openSearchProduct(obj), 100);
		}	
	}	
	if(type == "blur")
	{
		thisObj.addClass("on");
	}
}




function showShippingAddress(obj) {
	if($(obj).is(".on"))
	{
		$(obj).find("input").prop( "checked", false );
		$($(obj).attr("dir")).slideUp("slow");
		$(obj).removeClass("on");
	}
	else
	{
		$($(obj).attr("dir")).slideDown("slow");
		$(obj).find("input").prop( "checked", true );
		$(obj).addClass("on");
	}
}

function ecomQuantityManage(obj, id, productprice){
	
	//var getSubTotalAmount = document.getElementById("ecomsubtotal").value;
	//ID :(GrandTotal): ecomgtotal, (SubTotal): ecomsubtotal, (Total): grandTotal
	var getSubTotalAmount = sessionStorage.getItem("ecomsubtotal");   
	var getCurrency = sessionStorage.getItem("currency");
	var getDeliveryCharge = sessionStorage.getItem("ecomdeliveryAmount");
	var getTax = sessionStorage.getItem("ecomTaxAmount");
	var grandTotal = sessionStorage.getItem("ecomgtotal");
	var getDiscount = sessionStorage.getItem("ecomdiscount");
	
	console.log("All Amount: "+ "getSubTotalAmount- "+getSubTotalAmount+ " getCurrency- "+getCurrency+ " getDeliveryCharge- "+getDeliveryCharge+ " getTax- "+getTax+ " grandTotal- "+grandTotal+ "getDiscount- "+getDiscount);
	
	console.log("productprice: "+ productprice);
	
	var quantity = $("#qty"+id).val();
	console.log("obj: "+ obj+"id: "+ id+"quantity: "+ quantity);
	
	if(isNaN(quantity))
	{
		quantity = 0;
	}
	else
	{
		quantity =  parseInt(quantity);
	}
	
	if($(obj).val() == "-")
	{
		if(parseInt(quantity) > 1) {
			quantity = quantity-1;			
		}
	}
	else
	{
		quantity = quantity+1;		
		if(sessionStorage.getItem("ecomAvailquantity"+id))
		{
			if( parseInt(quantity) > parseInt(sessionStorage.getItem("ecomAvailquantity"+id)) )
			{
				quantity=(parseInt(sessionStorage.getItem("ecomAvailquantity"+id)));
			}
		}		
	}
	sessionStorage.setItem("ecomquantity"+id, quantity);
	//window.sessionStorage.setItem("ecomcart",quantity);
	$("#qty"+id).val(quantity);	
	
	console.log("quantity: "+ quantity);
	console.log("cartCount: "+window.sessionStorage.getItem("ecomcartQuantitywise"));
	//window.sessionStorage.setItem("ecomcartQuantitywise", quantity)
	//var updateSubTotal = parseInt(quantity) *  getSubTotalAmount;
	var updateSubTotal = parseInt(quantity) *  productprice;
	console.log("updateSubTotal: "+ updateSubTotal);
	
	var updatedGrandTotal = parseFloat(updateSubTotal) +parseFloat(getDeliveryCharge)+parseFloat(getTax)- parseFloat(getDiscount);
		
	console.log("updatedGrandTotal: "+ parseFloat(updatedGrandTotal).toFixed(2));
	
	
	$("#grandTotal").text(sessionStorage.getItem("currency")+' '+parseFloat(updatedGrandTotal).toFixed(2)); //total
	$("#ecomsubtotal").text(sessionStorage.getItem("currency")+' '+updateSubTotal); //subTotal
	$("#ecomgtotal").text(sessionStorage.getItem("currency")+' '+parseFloat(updatedGrandTotal).toFixed(2)); //GrandTotal
	sessionStorage.setItem("ecomgtotal",updatedGrandTotal);
	
	ecomCart_View();
}

$.fn.tabsClick = function() {
	var thisObj = $(this);
	var thisClick = thisObj.find("a");
	var thisTab = $("#" + thisObj.attr("dir")).find(".tabs-view");
	thisClick.click(function(){
		var thisIndex = thisClick.index(this);
		showTab(thisIndex);
	})
	function showTab(index) {
		thisClick.removeClass("active").eq(index).addClass("active");
		thisTab.hide().eq(index).show();
	}
	showTab(0);
}




function callSlideNew() {
	var thisNav = $(".mcom_nav ul");	
	var size = $("#mcomListing .pan").size();
	var diffX = 0;
	var thisX = 0;
	var swiper = new Swiper('#mcomListing');
	var mcomNavSlider = new Swiper("#mcomNavSlider");
	var swiperD = new Swiper('#mcomListing .slider-mcom', {
		pagination: '.swiper-pagination',
        paginationClickable: true,
	});
	var index = 0;
	var slideMode = false;
	var w = 0;
	var h = 0;
	
	$("#mcomNavSlider li a").removeAttr("onclick").on("click", function(){
		var index = $("#mcomNavSlider li a").index(this);
		swiper.slideTo(index);
	});
	
	
	
	
	getCatXML($("#mcomListing .pan").eq(0).attr("index"));
	mcomNavSlider.on("slideChangeStart", function(){
		$(".mcom_nav ul a").removeClass("next").removeClass("active").removeClass("prev");
		$(".mcom_nav ul a").eq(mcomNavSlider.activeIndex-1).addClass("prev");
		$(".mcom_nav ul a").eq(mcomNavSlider.activeIndex).addClass("active");
		$(".mcom_nav ul a").eq(mcomNavSlider.activeIndex+1).addClass("next");	
	})
	
	
	mcomNavSlider.on("slideChangeEnd", function(){
		swiper.slideTo(mcomNavSlider.activeIndex);
		setTimeout(function(){ 
		//getCatXML($("#mcomListing .pan").eq(mcomNavSlider.activeIndex).attr("index"));
		},500);
		
		$(".mcom_nav ul a").removeClass("next").removeClass("active").removeClass("prev");
		$(".mcom_nav ul a").eq(mcomNavSlider.activeIndex-1).addClass("prev");
		$(".mcom_nav ul a").eq(mcomNavSlider.activeIndex).addClass("active");
		$(".mcom_nav ul a").eq(mcomNavSlider.activeIndex+1).addClass("next");	
	})
	
	swiper.on("slideChangeEnd", function(){			
		mcomNavSlider.slideTo(swiper.activeIndex);
		setTimeout(function(){ 
		getCatXML($("#mcomListing .pan").eq(swiper.activeIndex).attr("index"));
		},500);
		
	})
	
	function firstToUpperCase( str ) {
    return str.substr(0, 1).toUpperCase() + str.substr(1);
	}

	//var str = 'hello, I\'m a string';
	//var uc_str = firstToUpperCase( str );
	//console.log( uc_str ); //Hello, I'm a string
	
	
	function resetObj() {
		index = 0;
		w = $(window).width();
		h = $(window).height();
		$("#mcomListing").height(h - ($( "[data-role='header']" ).eq(0).height() + 56));
		$(".mcom_nav ul a").removeClass("next").removeClass("active").removeClass("prev");
		$(".mcom_nav ul a").eq(mcomNavSlider.activeIndex-1).addClass("prev");
		$(".mcom_nav ul a").eq(mcomNavSlider.activeIndex).addClass("active");
		$(".mcom_nav ul a").eq(mcomNavSlider.activeIndex+1).addClass("next");		
	}
	
	resetObj();
	setTimeout(resetObj(), 300);
	$(window).resize(function(){
		resetObj();
		setTimeout(resetObj(), 300);
	})
	
	
	$("#mcomListing .pan").scroll(function(e) {
		$(".hideMe").hide();
		if($(this).scrollTop() > 50)
		{
			$("#txtSearch").addClass("on");
		}
		else
		{
			$("#txtSearch").removeClass("on");
			$("#txtSearch").blur();
		}		
	});	
	
	
			

	
	function noSlide() {
		if(slideMode)
		{
			var moveIndex = swiper.activeIndex;
			if(diffX > 0)
			{
				moveIndex = moveIndex + 1;				
			}
			else
			{
				moveIndex = moveIndex - 1;				
			}
			thisNav.addClass("on").css("left", "-" + (w*moveIndex) + "px");				
			$(".mcom_nav ul a").removeClass("next").removeClass("active").removeClass("prev");
			$(".mcom_nav ul a").eq(moveIndex-1).addClass("prev");
			$(".mcom_nav ul a").eq(moveIndex).addClass("active");
			$(".mcom_nav ul a").eq(moveIndex+1).addClass("next");	
		}
		else
		{
			thisNav.addClass("on").css("left", "-" + (w*moveIndex) + "px");
		}			
	}
	
	function yesSlide() {
		thisNav.addClass("on").css("left", thisX +  "px");
	}
}

/*
UPLOAD IMAGE FROM 
*/
/*
UPLOAD IMAGE FROM 
*/
function image_uploadFromDevice()
{
	
	

	navigator.notification.confirm(
			sselectfromwhereyouwanttouuuploadgg_mcom,  // message
			onConfirm_ecom,              // callback to invoke with index of button pressed
			mmessagegg_mcom,            // title
			'Camera,Gallery'
	);
}

function onConfirm_ecom(buttonIndex)
{

	if(buttonIndex==2)
	{
		var options = {
				quality: 50,
				sourceType : Camera.PictureSourceType.PHOTOLIBRARY,
				destinationType: navigator.camera.DestinationType.FILE_URI,
		}
		navigator.camera.getPicture(win_image_lib_ecom, fail, options);
	}
	if(buttonIndex==1)
	{
		Image_capture_ecom();
	}
}

function win_image_lib_ecom(imageURI)
{
	localStorage.local_imageURI=imageURI;

	show_image_on_box_ecom();
}

function Image_capture_ecom()
{
	var options = {
			limit: 1
	};
	navigator.device.capture.captureImage(captureSuccess2_ecom, captureError, options);
}
function captureError()
{

}
function captureSuccess2_ecom(mediaFiles)
{
    setTimeout(function(){ window.location="hidestatusbar:";},100);
    localStorage.local_imageURI = mediaFiles[0].fullPath;
    mediaFiles = goToNativeForRotation(mediaFiles[0].fullPath);
    localStorage.local_imageURI = mediaFiles;
    show_image_on_box_ecom();
	//localStorage.local_imageURI=mediaFiles[0].fullPath;
	//show_image_on_box_social();
}
function goToNativeForRotation(str)
 {
    return toaster.CheckIamgeOrientation(str);
 }
function show_image_on_box_ecom()
{
	localStorage.uploadtype='image';
	var mediaFiles=localStorage.local_imageURI;
		console.log("mediaFiles: " +mediaFiles);
		document.getElementById('profile_add_pics').style.backgroundImage = "url('" + mediaFiles + "')";
		//document.getElementById("ecomSettingsProfile").src = mediaFiles;
		sendImageToServer();
}
function fail()
{

}

function sendImageToServer() { //uploadUserImageAction (appId,email)
	$('.appypie-loader').show();
	
	var mediaFiles=localStorage.local_imageURI;
	
	console.log("mediaFilesViaj: "+ mediaFiles + " , "+localStorage.getItem("appUserEmail"));
		if(mediaFiles!="")
		{
			
			var fileName='profile_pic.jpg';
			var fileURI;
			fileURI=mediaFiles;
			var ft = new FileTransfer();
			var options = new FileUploadOptions();
			options.fileKey="file";
			options.fileName =  fileName;
			options.mimeType="image/jpeg";
			var params = new Object();
			params.emailId = localStorage.getItem("appUserEmail");
			params.appId = localStorage.getItem('applicationID');
			params.type='image';
			options.params = params;
			ft.upload(fileURI,encodeURI(CONSTANTIMAGEURL+"/ecommerce/upload-user-image"), successFn_ProfileEcom, errorFnEcom, options);
}
}
function successFn_ProfileEcom(r)
{
	
	
	$('.appypie-loader').hide();
	console.log("Response Image>>>Vijay= " + r.response);
	if(r.response=='success')
	{
		
		window.localStorage.setItem("uProfilePic", localStorage.local_imageURI);
		navigator.notification.alert(
				sssuccessfullyuuploadedgg_mcom,  // message
				alertDismissedEcom,         // callback
				mmessagegg_mcom,            // title
				OoKgg_mcom                  // buttonName
		);
	}
}
function alertDismissedEcom()
{
	$('.appypie-loader').hide();
}

function errorFnEcom(error) {
	

	
	$('.appypie-loader').hide();
	alertPopUp(uuploadeerrorgg_mcom +' !',error.code);
	console.log("upload error source " + error.source);
	console.log("upload error target " + error.target);
}

/*
END IMAGE WORK
*/


/*
ADD REVIEW LIST
*/
var mcomReviewListHTML = '';
function showReviewList(productId) { //reviewList(appId,productId)
var tmpHeader = '<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
   <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
   <h1 class="ui-title">'+appName+'</h1>\
   <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>';


console.log(window.localStorage.getItem("applicationID") +"," +productId+", "+window.localStorage.getItem("fooduserid"))
	var appyId = window.localStorage.getItem("applicationID");
	
	if(appyId == undefined || appyId =="" || productId == undefined || productId == "") {
		alertPopUp(eerrorggg_mcom + ' !',ppleastryagaingg_mcom);
	} else if(!checkNetworkConnection()) {
		alertPopUp(nnetworkrrorgg_mcom + ' !',ppleasecheckyourinternetconnectionggg_mcom);
	} else { //required param (appId, productId, email, wishlistType)
		$('.appypie-loader').show();
		wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#reviewList";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><reviewList xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#reviewList\"><appId>'+appyId+'</appId><productId>'+productId+'</productId></reviewList></soap:Body></soap:Envelope>';
        console.log("wsUrl--->"+wsUrl);
        console.log("soapRequest--->"+soapRequest);
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
			  
               console.log("reviewList_Response "+ strJSON); 
               
			   if(strJSON == "no reviews avalaible") {
				   mcomReviewListHTML = '<div class="reviews">\
									<center><h2>'+nnoreviewavalaiblegg_mcom+'</h2></center>\</div>'
									
				   appendHtml(ecomHeader+mcomReviewListHTML,4,4,'food');
				   resetEcomHeader();
			   } else {
				   parseReviewList(strJSON, ecomHeader);
			   }		
				setTimeout(function(){$('.appypie-loader').hide();},1000);				
               },
               error: function(response, textStatus, errorThrown)
               {
               //console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
	}
}
	
function parseReviewList(strJSON, tmpHeader) {
	
	mcomReviewListHTML = "";
	//$('.appypie-loader').show();
	$.ajax({
               url: strJSON,
               dataType: "text",
               crossDomain: true,
               cache: false,
               success: function(xml) {
               
               var xml =jQuery.parseXML(xml);
			
				  
				  mcomReviewListHTML += '<div class="reviews"><h2>Users  Reviews</h2>';				  
					$(xml).find("reviewDetail").each(function (i) {										
						var rUname= $(this).find("userName").text();
						var title= $(this).find("title").text();
						var review= $(this).find("review").text();
						var rating= $(this).find("rating").text();
						console.log("rUname "+ rUname);

					mcomReviewListHTML += '<div class="review-row"><time></time><p class="user">'+rUname+'</p><p class="rating"><span data-value="'+rating+'"></span></p><h4 class="title">'+title+'</h4><div class="comment">'+review+'</div></div>';						
						
					});
				   mcomReviewListHTML += '</div>'	
					appendHtml(ecomHeader+mcomReviewListHTML,4,4,'food');	
					resetEcomHeader();					
				   setTimeout(function(){
						
					},2000);
				   
				   
					setTimeout(function(){
						$('.appypie-loader').hide();
					},500);
					
               },
               error: function(XMLHttpRequest, textStatus, errorThrown) {
				   console.log('parseSubCat fail');
				   setTimeout(function(){$('.appypie-loader').hide();},1000);
				   console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
               }
               });
	
}  
/*
END REVIEW LIST
*/

/*
(ADD TO WISHLIST)
*/
//var isLiketype = sessionStorage.getItem("isWishList");
var isLiketype = false;
function addLikes(productId, thisObj) {
	

	console.log(window.localStorage.getItem("applicationID") +"," +productId+", "+window.localStorage.getItem("fooduserid"))
	
	
	var headerTmp = '<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
      <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
      <h1 class="ui-title">'+appName+'</h1>\
      <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>';
	
	
	var appyId = window.localStorage.getItem("applicationID");
	var uEmail = window.localStorage.getItem("fooduserid");
	console.log("isLiketype: "+ isLiketype);
	
	if(isLiketype == undefined || isLiketype == null) {
		liketype = 'add';
		isLiketype = 'true';
		sessionStorage.setItem("isWishList",isLiketype);
	}
	else if(isLiketype == 'false' || isLiketype == false){
		liketype = 'add';
		isLiketype = 'true';
		sessionStorage.setItem("isWishList",isLiketype);
	}
	else {
		liketype = 'remove';
		isLiketype = 'false';
		sessionStorage.setItem("isWishList",isLiketype);
	}
	console.log("isType: "+liketype +"isLiketype: "+isLiketype);
	
	if(appyId == undefined || appyId =="" || uEmail == undefined || uEmail == "") {

		
		  
		
		var ecomHTML_home = '<div><div class="login_box">\
							<div class="login_from">\
								<input type="button" class="button" value="'+cccreatecountgg_mcom+'" onclick="ecomSignUpView()">\
								<input type="button" class="button blue" value="'+sign_in_mcom+'"  onclick="loginEcommerce()">\
							</div>\
							</div></div>';
			
		
			
			ecomHTML_home += ecomSubMenu();
   
   appendHtml(ecomHeader+"<div class='page-text'>"+ecomHTML_home+"</div>",4,4,'food');
   resetEcomHeader();
		return false;
	} else if(!checkNetworkConnection()) {
		alertPopUp(nnetworkrrorgg_mcom + ' !',ppleasecheckyourinternetconnectionggg_mcom);
		return false;
	} else { 
		$('.appypie-loader').show();
		wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#addWishlist";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><addWishlist xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#addWishlist\"><appId>'+appyId+'</appId><productId>'+productId+'</productId><email>'+uEmail+'</email><wishlistType>'+liketype+'</wishlistType></addWishlist></soap:Body></soap:Envelope>';
        console.log("wsUrl--->"+wsUrl);
        console.log("soapRequest--->"+soapRequest);
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
			  
               console.log("Like_Response "+ strJSON); //Cat list Response xml file for Tab
			   if(strJSON == "added") {
				  $(thisObj).addClass("on");
				  //sessionStorage.setItem("isWishList","like on");
					//alert("Item added to Wishlist");				  
			   } else {
				   $(thisObj).removeClass("on");
				   //alert("Item removed from Wishlist");
				   //sessionStorage.setItem("isWishList","like off");
			   }
					
				setTimeout(function(){$('.appypie-loader').hide();},1000);				
               },
               error: function(response, textStatus, errorThrown)
               {
               //console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
			  return false;
	}
}
/*
END LIKES
*/

/*
Remove and update from wishlist
*/
function removeFromWishlist(productId) { 
	
	
		console.log(window.localStorage.getItem("applicationID") +"," +productId+", "+window.localStorage.getItem("fooduserid"))
	var appyId = window.localStorage.getItem("applicationID");
	var uEmail = window.localStorage.getItem("fooduserid");
	var liketype = 'remove';
	console.log("isType: "+liketype);
	
	if(appyId == undefined || appyId =="" || uEmail == undefined || uEmail == "") {
		alertPopUp(eeeerrorggg_mcom + ' !',missingrequiredfieldspleasereloginntragaingg_mcom);
		return false;
	} else if(!checkNetworkConnection()) {
		alertPopUp(nnetworkrrorgg_mcom + ' !',ppleasecheckyourinternetconnectionggg_mcom);
		return false;
	} else { //required param (appId, productId, email, wishlistType)
		$('.appypie-loader').show();
		wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#addWishlist";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><addWishlist xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#addWishlist\"><appId>'+appyId+'</appId><productId>'+productId+'</productId><email>'+uEmail+'</email><wishlistType>'+liketype+'</wishlistType></addWishlist></soap:Body></soap:Envelope>';
        console.log("wsUrl--->"+wsUrl);
        console.log("soapRequest--->"+soapRequest);
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
			  
               console.log("RemoveWishlistResponse "+ strJSON); //Cat list Response xml file for Tab
			   if(strJSON == "added") {
				  //$(thisObj).addClass("on");
				  //sessionStorage.setItem("isWishList","like on");
					//alert("Item added to Wishlist");				  
			   } else {
				   //$(thisObj).removeClass("on");
				   //alert("Item removed from Wishlist");
				   setTimeout(function(){
					   //closeSubMenu(this);
					   showWishListProducts();
					   },1000);
				   
				   //sessionStorage.setItem("isWishList","like off");
			   }
					
				setTimeout(function(){$('.appypie-loader').hide();},1000);				
               },
               error: function(response, textStatus, errorThrown)
               {
               //console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
	}
}

/*
SHOW WISHLIST
*/
function showWishListProducts() { //wishList(appId, email)
	//closeSubMenu(this);
	document.getElementById("left-navs").style.display = 'block';
    // hide the lorem ipsum text
    document.getElementById("left-navs").style.display = 'none';
	var headerTmp = '<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
      <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
      <h1 class="ui-title">'+appName+'</h1>\
      <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>';
	var appyId = window.localStorage.getItem("applicationID");
	var uEmail = window.localStorage.getItem("fooduserid");

	console.log("appyId: "+appyId +"uEmail: "+uEmail);
	
	if(appyId == undefined || appyId =="" || uEmail == undefined || uEmail == "") {
		//alert("Something wrong please try again");
		var ecomHTML_home = '<div><div class="login_box">\
							<div class="login_from">\
								<input type="button" class="button" value="'+cccreatecountgg_mcom+'" onclick="ecomSignUpView()">\
								<input type="button" class="button blue" value="'+sign_in_mcom+'"  onclick="loginEcommerce()">\
							</div>\
							</div></div>';
			//ecomHTML_home += ecomSubMenu();		
			appendHtml(ecomHTML_home,'',1,'food');
			resetEcomHeader();
	 return false;
	} else if(!checkNetworkConnection()) {
		alertPopUp(nnetworkrrorgg_mcom + ' !',ppleasecheckyourinternetconnectionggg_mcom);
		return false;
	} else { 
		$('.appypie-loader').show();
		wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#wishList";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><wishList xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#wishList\"><appId>'+appyId+'</appId><email>'+uEmail+'</email></wishList></soap:Body></soap:Envelope>';
        console.log("wsUrl--->"+wsUrl);
        console.log("soapRequest--->"+soapRequest);
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
               
            
			  
               console.log("wishList_Response "+ strJSON); //Cat list Response xml file for Tab
               if(strJSON == "no wishlist available") {
				  // var tmpmsg = '<center style="margin:100px 0 0 0"><h3 //style="color:black;">There is no Wishlist product.</h3></center>';
				  var tmpmsg='<div class="thankyou scrolling-head">\
								<h1>\
									'+pproductsgg_mcom+' <span>'+eemptyg_mcom+'</span>\
								</h1>\
								<p>\
								'+tthereisnishlisproductgg_mcom+'\
								</p>\
								<input type="button" style="background-color:'+primaryButtonBgColor+'" onclick="goToHomePage()" value="'+continue_shopping_mcom+'" class="continue-shopping" style="display:none">\
								<input type="button" onclick="homeecom()" value="'+ggotohhomeggg_mcpm+'" style="display:none">\
							</div>';
				  
				   appendHtml(ecomHeader+tmpmsg,2,2,'food');
				resetEcomHeader();		
				setTimeout(function(){$('.appypie-loader').hide();},1000);
			   } else{
				   parseWishListProducts(strJSON);
				//alert(strJSON);		
				setTimeout(function(){$('.appypie-loader').hide();},1000);
			   }
			   				
               },
               error: function(response, textStatus, errorThrown)
               {
               //console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
			   
			   return false;
	}
}

function parseWishListProducts(strJSON) {

	var wishlistHTML = '';
	var headerTmp = '<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
      <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
      <h1 class="ui-title">'+appName+'</h1>\
      <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>';
    if(!checkNetworkConnection())
    {
    }else
    {
		
		//$("#categoryList").html("");
		
		
        $.ajax({
               url: strJSON,
               dataType: "text",
               crossDomain: true,
               cache: false,
               success: function(xml) {
               
               var xml =jQuery.parseXML(xml);
			   
				   wishlistHTML='<div class="mCom_content_wrapper mcom_list wish-list scrolling-head">';				
					$(xml).find("wishlistDetail").each(function (i) {										
						var productId= $(this).find("productId").text();
						console.log("wishlist id: "+ productId);
						var productName= $(this).find("productName").text();
						var productImage= $(this).find("productImage").text();
						var price= $(this).find("price").text();
						var currency= $(this).find("currency").text();
										
						wishlistHTML+='<div class="product_box"><a class="wishlist" onclick="removeFromWishlist('+productId+')"></a><img style="background-image:url('+productImage+')" src="images/ecom-image.png" alt=""/><div class="discription_box"><p  class="title">'+productName+'</p>\
							<div class="product_price"><big>'+currency+price+'</big></div>\</div></div>';
						
						
						
					});
				   wishlistHTML+='</div>';
				   //wishlistHTML += ecomSubMenu();
				   appendHtml(ecomHeader+wishlistHTML,2,2,'food');
				   resetEcomHeader();
					setTimeout(function(){
						$('.appypie-loader').hide();
					},1000);
					
               },
               error: function(XMLHttpRequest, textStatus, errorThrown) {
				   console.log('fail');
				   setTimeout(function(){$('.appypie-loader').hide();},1000);
				   console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
               }
               });
    }
}
/*
END
*/



function checkPaymentTabs(current) {
	//alert($(current).html());
	
			
			
	
	var newVar = $(current).html();
	
	var order_by_phone=order_by_phone_food;
	var pay_pal=paypal_food;
	var credit_card=credit_card_food;
	var cash_on_del=cash_on_delivery_food;
	
	
	var newVar = $(current).html();
	if(newVar==order_by_phone) 
	{
		
		$("#payBtn").hide();
		$("#payCard").hide();
		$("#payCashOnD").hide();
		$("#payPaypal").hide();
		$("#payPayu").hide();
		$("#payPhoneCall").show();
	} else if(newVar==pay_pal) {
		
		$("#payBtn").hide();
		$("#payCard").hide();
		$("#payCashOnD").hide();
		$("#payPaypal").show();
		$("#payPayu").hide();
		$("#payPhoneCall").hide();
	} else if(newVar ==credit_card) {

		$("#payBtn").show();
		$("#payCard").show();
		$("#payCashOnD").hide();
		$("#payPaypal").hide();
		$("#payPayu").hide();
		$("#payPhoneCall").hide();
	}else if(newVar.toUpperCase() =="PAYU")
      	  {
      	       $("#payBtn").hide();
                 $("#payCard").hide();
                 $("#payCashOnD").hide();
                 $("#payPaypal").hide();
                 $("#payPhoneCall").hide();
                 $("#payPayu").show();
            }
	
       else if(cash_on_del) {
		
		$("#payBtn").hide();
		$("#payCard").hide();
		$("#payCashOnD").show();
		$("#payPaypal").hide();
		$("#payPhoneCall").hide();
        $("#payPayu").hide();
	} else {
		console.log("Nothing");
	}
}

/*
OFFER PRODUCT LIST
*/
function showOfferProducts() { //offeredProductList(appId)
	//closeSubMenu(this);
	document.getElementById("left-navs").style.display = 'block';
    // hide the lorem ipsum text
    document.getElementById("left-navs").style.display = 'none';
	var headerTmp = '<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
      <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
      <h1 class="ui-title">'+appName+'</h1>\
      <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>';
	  
	
	
	var appyId = window.localStorage.getItem("applicationID");
	$('.appypie-loader').show();
		wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#offeredProductList";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><offeredProductList xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#offeredProductList\"><appId>'+appyId+'</appId></offeredProductList></soap:Body></soap:Envelope>';
        console.log("wsUrl--->"+wsUrl);
        console.log("showOfferProducts--->"+soapRequest);
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
			  
               console.log("showOfferProducts_Response "+ strJSON); //
			   if(strJSON == "no offered products available") {
					var tmpmsg='<div class="thankyou scrolling-head">\
								<h1>\
									'+pproductsgg_mcom+' <span>'+eeeeemptygggg_mcom+'!</span>\
								</h1>\
								<p>\
								'+tthereisnofferedproductgg_mcom+'\
								</p>\
								<input type="button" style="background-color:'+primaryButtonBgColor+'" onclick="goToHomePage()" value="'+continue_shopping_mcom+'" class="continue-shopping" style="display:none">\
								<input type="button" onclick="homeecom()" value="'+ggotohhomeggg_mcpm+'" style="display:none">\
							</div>';
					appendHtml(ecomHeader+tmpmsg,2,2,'food');	
resetEcomHeader();					
				setTimeout(function(){$('.appypie-loader').hide();},1000);
			   } else {
				   displayOfferProducts(strJSON);	
				setTimeout(function(){$('.appypie-loader').hide();},1000);
			   }		
							
               },
               error: function(response, textStatus, errorThrown)
               {
               //console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
}


function displayOfferProducts(strJSON) {
var headerTmp = '<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
      <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
      <h1 class="ui-title">'+appName+'</h1>\
      <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>';
	var offerhtml = '';
	if(!checkNetworkConnection())
    {
    }else
    {
		
		//$("#categoryList").html("");
		//$("#mcomListing").html("");
		
        $.ajax({
               url: strJSON,
               dataType: "text",
               crossDomain: true,
               cache: false,
               success: function(xml) {
               
               var xml =jQuery.parseXML(xml);
			   
				   offerhtml='<div class="offters scrolling-head">';				
					$(xml).find("productDetail").each(function (i) {										
						var productId= $(this).find("productId").text();
						var productName= $(this).find("productName").text();
						var productImage= $(this).find("productImage").text();
						var currency= $(this).find("currency").text();
						var price= $(this).find("price").text();
						
						offerhtml+='<div class="product_box"><img style="background-image:url('+productImage+')" src="images/ecom-image.png" alt="" onclick="openEcomDetailPage('+productId+')"/>\
						<div class="discription_box">\
						<p class="title">'+productName+'</p>\
						<p class="description" id="pDiscription" style="display:none">'+productName+'</p>\
						<div class="product_price"><big>'+currency+price+'</big></div>\
						<div class="product_price_buttons"><a href="#" onclick="addTocartEcommorce(\''+productId+'\',\''+productName+'\',\''+productImage+'\',\''+price+'\',\''+currency+'\');" class="addtoCart">+ Add</a></div>\
						</div></div>';
						
					});
				   offerhtml+='</div>';
				   
				   //offerhtml += ecomSubMenu();
				   appendHtml(ecomHeader+offerhtml,2,2,'food');
				  resetEcomHeader();
					setTimeout(function(){
						$('.appypie-loader').hide();
					},1000);
					
               },
               error: function(XMLHttpRequest, textStatus, errorThrown) {
				   console.log('fail');
				   setTimeout(function(){$('.appypie-loader').hide();},1000);
				   console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
               }
               });
    }

}
/*
END
*/

/*
CANCEL ORDER PRODUCT
*/
//ecommCancelOrderDetail(appId,orderId)
function cancelEcomProduct(orderId) {
	var appyId = window.localStorage.getItem("applicationID");
	console.log("orderId: "+ orderId);
	$('.appypie-loader').show();
		wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#ecommCancelOrderDetail";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommCancelOrderDetail xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#ecommCancelOrderDetail\"><appId>'+appyId+'</appId><orderId>'+orderId+'</orderId></ecommCancelOrderDetail></soap:Body></soap:Envelope>';
        console.log("wsUrl--->"+wsUrl);
        console.log("cancelEcomProduct--->"+soapRequest);
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
			  
               console.log("cancelEcomProduct_Response "+ strJSON); //
				//alert(strJSON);
				if(strJSON == "cancelled") {
					myOrdersEcom();
					setTimeout(function(){$('.appypie-loader').hide();},1000);
				}	else {
					setTimeout(function(){$('.appypie-loader').hide();},1000);
				}			
								
               },
               error: function(response, textStatus, errorThrown)
               {
               //console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
}
/*
END
*/

/*
LIST OF FEATURED PRODUCT
*/
//getFeaturedProductXml(appId)
function getFeaturedProducts() {
	document.getElementById("left-navs").style.display = 'block';
    // hide the lorem ipsum text
    document.getElementById("left-navs").style.display = 'none';
	
	  
	var appyId = window.localStorage.getItem("applicationID");
	//$('.appypie-loader').show();
		wsUrl = "http://"+resellerInitial+reseller+"/services/ecomm-soap#getFeaturedProductXml";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getFeaturedProductXml xmlns=\"http://'+resellerInitial+reseller+'/services/ecomm-soap#getFeaturedProductXml\"><appId>'+appyId+'</appId></getFeaturedProductXml></soap:Body></soap:Envelope>';
        console.log("wsUrl--->"+wsUrl);
        console.log("getFeaturedProducts--->"+soapRequest);
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
			  
               console.log("getFeaturedProducts_Response "+ strJSON); //
			   displayFeaturedProducts(strJSON);		
				setTimeout(function(){$('.appypie-loader').hide();},1000);				
               },
               error: function(response, textStatus, errorThrown)
               {
               //console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
}

function displayFeaturedProducts(strJSON) {
	var headerTmp = '<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
      <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
      <h1 class="ui-title">'+appName+'</h1>\
      <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>';
	var featuredhtml = '';
	var isFeaturedData = false;
	if(!checkNetworkConnection())
    {
    }else
    {
        $.ajax({
               url: strJSON,
               dataType: "text",
               crossDomain: true,
               cache: false,
               success: function(xml) {
               
               var xml =jQuery.parseXML(xml);
					 featuredhtml='<div class="offters scrolling-head">';
					$(xml).find("product").each(function (i) {										
						var productId= $(this).find("productId").text();
						var productName= $(this).find("productName").text();
						var productImage= $(this).find("productImage").text();
						var price= $(this).find("price").text();
						var currency= $(this).find("currency").text();
						fProductID[i] = productId;
						fProductImage[i] = productImage;
						console.log("FeaturedproductId: "+ productId);
						isFeaturedData = true;
						
						featuredhtml+='<div class="product_box"><img style="background-image:url('+productImage+')" src="images/ecom-image.png" alt="" onclick="openEcomDetailPage('+productId+')"/>\
						<div class="discription_box">\
						<p class="title">'+productName+'</p>\
						<p class="description" id="pDiscription" style="display:none">'+productName+'</p>\
						<div class="product_price"><big>'+currency+price+'</big></div>\
						<div class="product_price_buttons"><a href="#" onclick="addTocartEcommorce(\''+productId+'\',\''+productName+'\',\''+productImage+'\',\''+price+'\',\''+currency+'\');" class="addtoCart">+ Add</a></div>\
						</div></div>';
					});
				  
				  
					setTimeout(function(){
						$('.appypie-loader').hide();
						if(isFeaturedData) {
							//featuredhtml += ecomSubMenu();
							appendHtml(ecomHeader+featuredhtml,2,2,'food');
							resetEcomHeader();
						} else {
							// var tmpmsg = '<center style="margin:100px 0 0 0"><h3 //style="color:black;">There is no featured product.</h3></center>';
							 
							 var tmpmsg='<div class="thankyou">\
								<h1>\
									'+pproductsgg_mcom+'<span>'+eeeeemptygggg_mcom+' !</span>\
								</h1>\
								<p>\
								'+tthereisnofeatureproductgg_mcom+'\
								</p>\
								<input type="button" style="background-color:'+primaryButtonBgColor+'" onclick="goToHomePage()" value="'+continue_shopping_mcom+'" class="continue-shopping" style="display:none">\
								<input type="button" onclick="homeecom()" value="'+ggotohhomeggg_mcpm+'" style="display:none">\
							</div>';
							 
							 
							  appendHtml(ecomHeader+tmpmsg,2,2,'food');
							  resetEcomHeader();
						}
					},1000);
					
					
               },
               error: function(XMLHttpRequest, textStatus, errorThrown) {
				   console.log('fail');
				   setTimeout(function(){$('.appypie-loader').hide();},1000);
				   console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
               }
               });
    }
}
/*
END
*/

/*
GET PAYPAL ID
*/
function getPaypalId() {

	var appyId = window.localStorage.getItem("applicationID");
	//$('.appypie-loader').show();
		var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/ecomm-soap#getEcomPaymentCredential";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getEcomPaymentCredential xmlns=\"http://'+localStorage.getItem("reseller")+'/services/ecomm-soap#getEcomPaymentCredential\"><appId>'+appyId+'</appId></getEcomPaymentCredential></soap:Body></soap:Envelope>';
        console.log("wsUrl--->"+wsUrl);
        console.log("getPaypalId_soap--->"+soapRequest);
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
			  
               console.log("getPaypalId_Response "+ strJSON); //
			   sessionStorage.setItem('paypalClientId',strJSON);
				setTimeout(function(){$('.appypie-loader').hide();},1000);				
               },
               error: function(response, textStatus, errorThrown)
               {
               //console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
}

function viewOrderQuantity(pName, pQty, price, pcurrency, pId, pTime) {
	var descHtml = '';
	var tmpHeader = '<header class="ui-header headerColor ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
   <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
   <h1 class="ui-title">'+appName+'</h1>\
   <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>';
	
	descHtml = '<div class="notifications">\
<div class="wrap">\
   <div class="notification">\
  <h4>'+pName+'</h4>\
  <p>Order Id: \
     '+pId+'\
       </p>\
      <p>Quantity: \
     '+pQty+'\
       </p>\
	   <p>Total Price: \
     '+pcurrency+price+'\
       </p>\
        <p class="time">\
        <time>'+pTime+'</time>\
     </p>\
     </div>\
     </div></div>';
	 
	 appendHtml(ecomHeader+descHtml,3,3,'food');
	resetEcomHeader();
}
function goBackFromOrderPageEcomHome()
{
	window.history.back();
	ecomCategories();
	
}

function goBackFromOrderPageEcom() {
	window.history.back();
	//alert(window.history.back);
	/*
	For Latest ECOM
	*/
	if(window.sessionStorage.getItem("ecomcartQuantitywise") == null || window.sessionStorage.getItem("ecomcartQuantitywise") == undefined) {
		//window.sessionStorage.setItem("ecomcart", 0);
		window.sessionStorage.setItem("ecomcartQuantitywise", 0);
	}
	//closeSubMenu(this); //closed ECOM menu on back
	//$('.product-button span').html(parseInt(window.sessionStorage.getItem("ecomcartQuantitywise")));
	$('.subValue').html(parseInt(window.sessionStorage.getItem("ecomcartQuantitywise")));
	
	if(window.localStorage.getItem("layout")=="bottom"){        	
            $("div[class^=app_navigation_bottom]").hide();
            window.localStorage.setItem("bottomHide","ture");
     }
}
function clearAllHistory() {
	history.go(-(history.length - 2));
	setTimeout(function(){myOrdersEcom();},1000);
}

function homeecom()
{
$("body").removeClass("ecom")

	allowScroll();
    sessionStorage.setItem("AppPageName","");
    $(".sub-menu, .login-none").remove();
    showHideAd("show");
   // hideslidemenuecom();
	$('#page2').attr("style","padding-top:45px !important");
    $.mobile.changePage("#page1", {transition: 'slidefade',reverse: true });
    if(localStorage.getItem("layout")=="bottom") {
        $("div[class^=app_navigation_bottom]").show();
        window.localStorage.setItem("bottomHide","false");
        $(".app_navigation_bottom").show();
        $(".app_navigation_bottom_carousel").show();
        $( "[data-role='footer']" ).toolbar();
        localStorage.setItem("bottomHide","false");
        $.mobile.resetActivePageHeight();
    }
	window.sessionStorage.setItem("EcomSubMenu","true");
   setTimeout(function(){
     dynamicCSS('');
     }, 1200);
    $("#shopmenu").hide();
    $("#logo").show();
    $("#mainback").hide();
    $("#mainbackfoodecom").hide();
    Showhideupdatebutton();
    $("#bookmark").hide();
    sessionStorage.setItem("socialpageback","false");
	sessionStorage.setItem("backfromMenu","true");
    sessionStorage.setItem("socialpageback_comment","false");
    //openPage(0);
	getStarted();
	
}

function limit(element, total) {
 var qtyTotal=parseInt(total);
    if (document.getElementById("quantity").value < 0)
     {

     document.getElementById("quantity").value=0;
     }
    
    if (document.getElementById("quantity").value > qtyTotal)
     {
     document.getElementById("quantity").value=qtyTotal;
     }
  }
  
  
  
  
  function resetEcomHeader() {
	headerImage=$(masterData).find("nav_header_image_name").text();
	headerBarTextColor=$(masterData).find("headerBarTextColor").text();
	navigationBarType=$(masterData).find("navigationBarType").text();
	headerBarBackgroundColor=$(masterData).find("headerBarBackgroundColor").text();
	var headerBarTitle	=$(masterData).find("headerBarTitle").text();
	var headerBarFont	=$(masterData).find("headerBarFont").text();
	var headerBarSize	=$(masterData).find("headerBarSize").text();
	if(headerImage == '' || navigationBarType=='text' || navigationBarType=='none')
	{
		$(".ecomHeader").css({
			"color":headerBarTextColor,
			"background-color":headerBarBackgroundColor			
		}).find("i").css("color", headerBarTextColor);
		$(".ecomHeader").find("h1").css(" font-weight", "normal").css(" text-shadow", "none").text(headerBarTitle)
		.addClass(headerBarFont + " " + headerBarSize);
		
	}
	else
	{
		var chkDeviceRes=toaster.Header_CheckDevice_ScreenResolution();
		headerImageNew=set_headerbackground_image(chkDeviceRes);
		if($.trim(headerBarBackgroundColor) == "none")
		{
			headerBarBackgroundColor = "transparent";
		}
		$(".ecomHeader").css({
			"background-image":"url("+headerImageNew+")",
			"background-color":headerBarBackgroundColor,
			"background-size":"100% 100%",
			"background-position":"center"
		}).find("i").css("color", headerBarTextColor);	
		$(".ecomHeader").find("h1").text("");	
	}		
	$(".ecomHeader #localEcomCart span").text(sessionStorage.getItem("ecomcartQuantitywise"));
	if($.mobile.activePage.find(".mcom-home").size() != 0)
	{
		$.mobile.activePage.find(".ecomHeader a").eq(1).hide();
	}
	else
	{
		$.mobile.activePage.find(".ecomHeader a").eq(0).hide();
	}
	$.mobile.resetActivePageHeight();
}

function hammerZoom(elm) {
	hammerMe = new Hammer(elm, {});
	hammerMe.get('pinch').set({
		enable: true
	});
	var posX = 0,
		posY = 0,
		scale = 1,
		last_scale = 1,
		last_posX = 0,
		last_posY = 0,
		max_pos_x = 0,
		max_pos_y = 0,
		transform = "",
		el = elm;

	hammerMe.on('doubletap pan pinch panend pinchend', function(ev) {
		if (ev.type == "doubletap") {
			transform =
				"translate3d(0, 0, 0) " +
				"scale3d(2, 2, 1) ";
			scale = 2;
			last_scale = 2;
			try {
				if (window.getComputedStyle(el, null).getPropertyValue('-webkit-transform').toString() != "matrix(1, 0, 0, 1, 0, 0)") {
					transform =
						"translate3d(0, 0, 0) " +
						"scale3d(1, 1, 1) ";
					scale = 1;
					last_scale = 1;
				}
			} catch (err) {}
			el.style.webkitTransform = transform;
			transform = "";
		}

		//pan    
		if (scale != 1) {
			posX = last_posX + ev.deltaX;
			posY = last_posY + ev.deltaY;
			max_pos_x = Math.ceil((scale - 1) * el.clientWidth / 2);
			max_pos_y = Math.ceil((scale - 1) * el.clientHeight / 2);
			if (posX > max_pos_x) {
				posX = max_pos_x;
			}
			if (posX < -max_pos_x) {
				posX = -max_pos_x;
			}
			if (posY > max_pos_y) {
				posY = max_pos_y;
			}
			if (posY < -max_pos_y) {
				posY = -max_pos_y;
			}
		}


		//pinch
		if (ev.type == "pinch") {
			scale = Math.max(.999, Math.min(last_scale * (ev.scale), 4));
		}
    	if(ev.type == "pinchend"){last_scale = scale;}

		//panend
		if(ev.type == "panend"){
			last_posX = posX < max_pos_x ? posX : max_pos_x;
			last_posY = posY < max_pos_y ? posY : max_pos_y;
		}

		if (scale != 1) {
			transform =
				"translate3d(" + posX + "px," + posY + "px, 0) " +
				"scale3d(" + scale + ", " + scale + ", 1)";
		}

		if (transform) {
			el.style.webkitTransform = transform;
		}
	});
}


function pinchZoom(obj) {
	var pinchObj = $(obj);
	var pinchHTML = '<div class="pinchZoom"></div>';
	$(".pinchZoom").remove();
	$("body").append(pinchHTML);	
	if(pinchObj.parents(".pinch-zoom").size() == 0)
	{
		$(".pinchZoom").append('<img src="images/imgmatrix-image.png" style="background-image:url('+pinchObj.attr("data-image")+')" />');
	}
	else
	{
		var parentZoom = pinchObj.parents(".pinch-zoom");
		if(parentZoom.find("[data-image]").size() == 0)
		{
			$(".pinchZoom").append('<img src="images/imgmatrix-image.png" style="background-image:url('+pinchObj.attr("data-image")+')" />');			
		}
		else
		{
			$(".pinchZoom").addClass("swiper-container").append('<div class="swiper-wrapper"></div>');
			parentZoom.find("[data-image]").each(function(){
				$(".pinchZoom .swiper-wrapper").append('<div class="swiper-slide"><img src="images/imgmatrix-image.png" style="background-image:url('+pinchObj.attr("data-image")+')"></div>');
			})
			
			var mySwiper = new Swiper('.swiper-container', {
				initialSlide:parentZoom.find("[data-image]").index(obj)
			});
		}
	}
	
	$(".pinchZoom").find("img").each(function(){
		hammerZoom(this);
	})

	$(".pinchZoom").append('<a>X</a>');	
	$(".pinchZoom a").click(function(){
		$(".pinchZoom").remove();
	})
}
