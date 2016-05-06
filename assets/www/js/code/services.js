////////////////////////////Services////////////////////////////
var masterData=$.parseXML(localStorage.getItem("xml"));

var latLonArray=new Array();
var latLonMainPageArray=new Array();
var serviceHeaderCountArr=new Array();
var dirXmlData='';
//*********var for dyanmic text******//
 var xmlDataforFilter="";
var enter_your_search_here_dir = "Enter your search here";
var add_your_listing_dir = "Add your listing";
var add_update_listing_dir="add/update listing";
var heading_dir = "heading";
var summary_dir = "Summary";
var for_best_view_dir = "For best view add maximum 75 words";
var body_dir = "Body";
var add_more_dir = "add more";
var next_dir = "next";
var alert_dir = "Alert!";
var please_enter_the_heading_dir = "please enter the heading";
var images_dir = "Images";
var video_dir = "video";
var audio_dir = "Audio";
var upload_image_dir = "upload image";
var click_to_upload_image_dir = "click to upload image";
var instruction_recommended_image_dir = "Instruction:- Recommended image size 768*300 pixels";
var save_dir="save";
var add_a_video_dir="Add a video";
var enter_youtube_url_dir = "Enter Youtube Url";
var add_an_audio_playlist_from_dir = "Add an audio playlist from";
var enter_url_dir = "Enter url";
var sucess_dir = "Sucess!";
var data_not_availaible_dir = "Data not availaible";
var search_button_dir = "search button";
var search_directory_place_holder_value_dir = "search directory place holder value";
var search_by_google_map_dir = "Search by google map";
var go_dir = "Go";
var units_dir = "Units";
var in_the_radius_of_dir = "In the radius of";
var distance_dir = "Distance";
var no_list_in_this_category_dir = "No list in this category";
var ok_dir = "OK";
var please_insert_search_key_dir = "Please insert search key";
var dirRating=2.5;
var mainServiceIndex=0;
var minDist=0;
var maxDist=0;
var filterType = "Distance";
var distanceUnits="KM";
var countMenu=0;
var serviceHeaderName="DIRECTORY";
var catIdArray = [];
var catNameArray = []
var catIdSelected="";
var imageArraySubmitList=[];
var isFromDir="directory";
var detailImageWithCommaSep="";
var sortArray=new Array();
var xmlDataDirDeatil="";
var filterIdArray=new Array();
var filterIdArrayStored=new Array();
var filterSublistArray=new Array();
var multipleLocStr="";
 var bodyImageUpdateListing="";
var intrnalSearchCount=0;
 var mapThirdPartyFlag=0;
 
var Home="Home";
var Main_Menu="Main Menu";
var BookmarksDir="Bookmarks";
var add_your_listing_dir = "Submit Listing";
var add_update_listing_dir="Update Listing";
var Login_SignUp="LogIn/SignUp";
var LogoutTxt="Logout";
var search_directory_dir="Search Directory";
//===========================

//===========================
var Filter="Filter";
var Short_By="Short By";
var distance_dir="Distance";
 var rating_dir="Rating";
var search_button_dir="Search";
var KM_dir="KM";
var Mile_dir="MI";
//=========================

var reviews_Dir="Reviews";
var user_reviews_ratings_dir="Ratings and Reviews";
var Users_Dir="Users";
var fromDir="from";
var send_request="Send Request";
var post_your_ratingreview_dir="Post Comment";
var no_review_available="No review available";
var please_select_ratings_dir="Please select ratings";
var comments_cannot_be_leftblank_dir="Comments field cannot be left blank";
var Rating_and_Comment_alert="Rating and Comment is submitted successfully.";
var Rating_and_Comment_alert_with_review="Rating and Comment is submitted successfully and is under review by the admin.";

//======bookmark====
var Bookmarked_successfully="Bookmarked successfully";
var Bookmarked_Deleted_successfully="Bookmarked Deleted successfully";

//========Request services
var request_a_service="Request a Service";
var Submit_your_request_to_providers="Submit your request to  service providers";
var name_Dir="Name";
var enter_your_name="Enter your name";
var phone_Dir="Phone";
var Enter_your_phone_number="Enter your phone number";
var address_dir="Address";
var Enter_your_full_address="Enter your full address";
var What_is_your_budget="What is your budget?";
var Enter_your_budget="Enter your budget";
var What_is_your_requirement="What is your requirement?";
var Please_describe_your_request="Please describe your request.";
var Your_submited_request_send_successfuly="Your submited request send successfuly";

//===submit/update listing============
var Select_Category="Select Category";
var heading_dir="Heading";
var please_enter_the_heading_dir="Please enter the heading";
var summary_dir="Summary";
var please_enter_the_summary="Please enter the summary";
var body_dir="Body";
var please_enter_the_body="Please enter the body content";
var images_dir="Images";
//var upload_image_dir="";
var click_to_upload_image_dir="Click here to upload image";
var video_dir="Videos";
var add_a_video_dir="Add a video";
var enter_youtube_url_dir="Enter Youtube Url";
var audio_dir="Audios";
var Media_RSS="Media RSS";
var Radio_PLS="Radio PLS";
var CustomDir="Custom";
var enter_url_dir="Please enter the valid Url";
var please_enter_valid_youtubeurl_dir="Please enter a valid youtube url";
var url_dir="Url";
var please_enter_valid_url_dir="Please enter the valid Url";
var email_dir="Email";
var enter_valid_emailaddress_dir="Enter a valid Email address";
var phone_Dir="Phone";
var Enter_your_phone_number="Enter your phone number";
var address_dir="Address";
var Enter_your_full_address="Please enter valid address";
var Launch_map_in_application="Launch map in application";
var add_more_dir="Add More";
var listing_submitted_sucessfully_under_review_dir="Listing is submitted sucessfully and is under review";
var add_listing="Add Listing";
var instruction_recommended_image_dir = "Recommended image size 768*300 pixels";
var delete_listing="Delete Listing";
//============
var no_list_in_this_category_dir = "No list in this category";
var Successfully_Deleted = "Successfully Deleted";
var No_listing_for_update="No listing for update";
var data_not_availaible_dir = "Data not availaible";
var Please_select_your_category="Please select your category";
var save_dir="save";
var Profile_Updated_Successfully="Profile Updated Successfully";
var Fail_to_update_profile="Fail to update profile";
var Settings_Dir="Settings";
//==============================
var media_rss_url="Enter media rss url";
var media_radio_pls_url="Enter radio pls url";
var custom_url="Enter custom url";
var custom_track_name="Custom track name";
var custom_track_description="Custom track description";

var Network_connection_error_please_try_again_later="Network connection error please try again later";
var Check_In_Dir="Check In";
var Checkins_Dir="Checkins";
var Checked_In="Checked In";
var checkin="";
var dirDefaultImg="";
var defaultDistance="miles";
//===========GEETA CODE===================
var placeholder_city_state_country_services = "city,state,country";
var placeholder_soundrssData_services = "soundrssData";
var placeholder_rssradioData_services = "rssradioData";
var placeholder_customlistData_services = "customlistData";
var placeholder_customTrackNameData_services = "customTrackNameData";
var placeholder_customTrackDescriptionData_services = "customTrackDescriptionData";

var old_add_class_services = "old-view";
var search_category_services = "search-category";
var form_internal_search_services = "NO";
var distance_unit_mi_services = "MI";
var distance_unit_m_services = "M";
var distance_unit_k_services = "K";
var call_disp_none_services = "none";
var rating_disp_block_services = "block";
var address_name_not_avail_services = "Address name not available";

var alert_internet_conn_not_avail_services = "Internet connection is not available.";
var alert_services = "Alert";
var add_more_services = "Add More";
var alert_thanks_services = "Thanks!";
var alert_success_submitted_services = "Successfully submitted";
var successfully_updated_services = "successfully Uploaded!";
var alert_oops_services = "Oops";
var custom_track_cant_blank = "Custom track name can not be blank.";
var custom_track_desc_cant_blank = "Custom track description can not be blank.";
var alert_server_not_found_try_later_services = "Server not responding\n Please try again later";
var alert_enter_valid_email_address_services = "Enter a valid E-mail address";
var alert_name_field_cant_blank = "*Name field cannot be left blank";
var alert_please_switchon_location_services = "Please switch On the location services for this app, To use this feature";
var alert_select_from_where_want_services = "Select from where you want to upload!";
var alert_message_services = "Message";
var alert_gallery_camera_cancel_services = "Camera,Gallery,Cancel";
var alert_listing_submit_success_review_services = "Listing is submitted successfully and is under review by the admin.";
var alert_success_services = "Success!!";
var alert_error_submitting_listing_services = "Error in submitting the listing.";
var alert_error_services = "Error";
var alert_want_logout_services = "You want to Logout ?";
var logout_services = "LogOut";
var cancel_yes_services = "Cancel, Yes";
var alert_ok_services = "OK";
var alert_sign_alert_services = "Alert !";
var user_register_successfully_services = "User Registered Successfully";
var user_already_register_services = "User already registered";
var no_categories_avail_services = "No categories available";
var isfrom_drictory_services = "isFromDirectory";

var enter_name_services = "Please Enter Name.";
var enter_emailid_services = "Please Enter Email Id.";
var enter_valid_email_services = "Please enter a valid email";
var enter_phone_no_services = "Please Enter Phone Number.";
var enter_valid_phoneno_services = "Please Enter Valid Phone Number.";
var enter_password_services = "Please Enter Password.";
var password_mismatch_services = "Password mismatch";
var invalid_email_address_services = "Invalid Email Address...";
var password_reset_success_check_email_services = "Password Reset was successfull,\nPlease check your email for new password.";
var you_not_register_with_yet_services = "You have not been registered with us yet.";
var enter_valid_address_services = "Please Enter Valid Address";
var enter_budget_services = "Please Enter Budget";
var enter_requirement_services = "Please Enter Requirement";
var request_not_submit_try_again_services = "Request  not submitted.  please try again later";
 var message='Please Select Rating';
 var ratting_missing_services = "Rating missing!";

var filter_type_services = "Distance";
var filter_type_ratting_services = "ratting";
var Filter_search_distance_services = "distance";
var internet_connective_error_services = "Internet connectivity error";
var no_saved_bookmarks_services = "No Saved Bookmarks";
var search_delhi_services = "delhi";
var output_weatherlist_services = "weatherList";

var inner_html_location_couldnot_found_services = "The location could not be found";
var inner_html_error_has_occured_services = "An error has occurred";
var serviceHeader="";
var theusernamepasswordenteredincorrect_services = "The username or password you entered is incorrect.";
var call_dir="call";
 var  dirSummaryListingDetailsPage='';
//===========FEETA CODE===================

var SubCatNameAddListArray=[];
var SubCatIdAddListArray=[];

function getService(index)
{
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
	 mainServiceIndex=index;
           setTimeout(function(){
   
     stopScroll();
     $("#shopmenu").hide();
     sessionStorage.setItem("AppPageName","directory");
     localStorage.setItem("isFrom","");
    $( "[data-role='header']" ).remove();
    $( "[data-role='header']" ).toolbar('destroy');
    $.mobile.resetActivePageHeight();
    $('#page2').attr("style","padding:0px !important");
    $('#page3').attr("style","padding:0px !important");
    $('#page4').attr("style","padding:0px !important");
    $('#page5').attr("style","padding:0px !important");
	dynamicCSS('hyperlocal');
	showHideAd("hide");
               sessionStorage.setItem('hideHeaderBar','false');
               localStorage.setItem("hidebootomforce",'true');
	},500);
    localStorage.setItem('calculateNearByInUnits','miles');
    console.log("the units --->"+localStorage.getItem('calculateNearByInUnits'));
    console.log("getService() starts");
    sessionStorage.setItem('dirPageIndex',index);
    var dirPageIndex =$(masterData).find("pageIdentifierBecon").text().split(',');
    sessionStorage.removeItem('activePage');
    $serviceDataIndex = $(masterData).find( "services[indexval="+index+"]" );
    

    if($serviceDataIndex.find("LanguageSettings").find("enter_your_search_here_dir").text())
    {
        enter_your_search_here_dir=$menuXmlData.find("LanguageSettings").find("enter_your_search_here_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("add_your_listing_dir").text())
    {
        add_your_listing_dir=$menuXmlData.find("LanguageSettings").find("add_your_listing_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("click_here_dir").text())
    {
        click_here_dir=$menuXmlData.find("LanguageSettings").find("click_here_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("add_update_listing_dir").text())
    {
        add_update_listing_dir=$menuXmlData.find("LanguageSettings").find("add_update_listing_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("heading_dir").text())
    {
        heading_dir=$menuXmlData.find("LanguageSettings").find("heading_dir").text();
    }if($serviceDataIndex.find("LanguageSettings").find("summary_dir").text())
    {
        summary_dir=$menuXmlData.find("LanguageSettings").find("summary_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("for_best_view_dir").text())
    {
        for_best_view_dir=$menuXmlData.find("LanguageSettings").find("for_best_view_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("body_dir").text())
    {
        body_dir=$menuXmlData.find("LanguageSettings").find("body_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("add_more_dir").text())
    {
        add_more_dir=$menuXmlData.find("LanguageSettings").find("add_more_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("next_dir").text())
    {
        next_dir=$menuXmlData.find("LanguageSettings").find("next_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("alert_dir").text())
    {
        alert_dir=$menuXmlData.find("LanguageSettings").find("alert_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("please_enter_the_heading_dir").text())
    {
        please_enter_the_heading_dir=$menuXmlData.find("LanguageSettings").find("please_enter_the_heading_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("images_dir").text())
    {
        images_dir=$menuXmlData.find("LanguageSettings").find("images_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("video_dir").text())
    {
        video_dir=$menuXmlData.find("LanguageSettings").find("video_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("audio_dir").text())
    {
        audio_dir=$menuXmlData.find("LanguageSettings").find("audio_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("upload_image_dir").text())
    {
        upload_image_dir=$menuXmlData.find("LanguageSettings").find("upload_image_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("click_to_upload_image_dir").text())
    {
        click_to_upload_image_dir=$menuXmlData.find("LanguageSettings").find("click_to_upload_image_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("instruction_recommended_image_dir").text())
    {
        instruction_recommended_image_dir=$menuXmlData.find("LanguageSettings").find("instruction_recommended_image_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("save_dir").text())
    {
        save_dir=$menuXmlData.find("LanguageSettings").find("save_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("add_a_video_dir").text())
    {
        add_a_video_dir=$menuXmlData.find("LanguageSettings").find("add_a_video_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("enter_youtube_url_dir").text())
    {
        enter_youtube_url_dir=$menuXmlData.find("LanguageSettings").find("enter_youtube_url_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("add_an_audio_playlist_from_dir").text())
    {
        add_an_audio_playlist_from_dir=$menuXmlData.find("LanguageSettings").find("add_an_audio_playlist_from_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("enter_url_dir").text())
    {
        enter_url_dir=$menuXmlData.find("LanguageSettings").find("enter_url_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("sucess_dir").text())
    {
        sucess_dir=$menuXmlData.find("LanguageSettings").find("sucess_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("data_not_availaible_dir").text())
    {
        data_not_availaible_dir=$menuXmlData.find("LanguageSettings").find("data_not_availaible_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("search_button_dir").text())
    {
        search_button_dir=$menuXmlData.find("LanguageSettings").find("search_button_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("search_directory_place_holder_value_dir").text())
    {
        search_directory_place_holder_value_dir=$menuXmlData.find("LanguageSettings").find("search_directory_place_holder_value_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("search_by_google_map_dir").text())
    {
        search_by_google_map_dir=$menuXmlData.find("LanguageSettings").find("search_by_google_map_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("go_dir").text())
    {
        go_dir=$menuXmlData.find("LanguageSettings").find("go_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("units_dir").text())
    {
        units_dir=$menuXmlData.find("LanguageSettings").find("units_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("in_the_radius_of_dir").text())
    {
        in_the_radius_of_dir=$menuXmlData.find("LanguageSettings").find("in_the_radius_of_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("distance_dir").text())
    {
        distance_dir=$menuXmlData.find("LanguageSettings").find("distance_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("no_list_in_this_category_dir").text())
    {
        no_list_in_this_category_dir=$menuXmlData.find("LanguageSettings").find("no_list_in_this_category_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("ok_dir").text())
    {
        ok_dir=$menuXmlData.find("LanguageSettings").find("ok_dir").text();
        
    }
	   if($serviceDataIndex.find("LanguageSettings").find("please_insert_search_key_dir").text())
       {
           please_insert_search_key_dir=$menuXmlData.find("LanguageSettings").find("please_insert_search_key_dir").text();
       }
	   if($serviceDataIndex.find("LanguageSettings").find("delete_listing").text())
    {
        delete_listing=$menuXmlData.find("LanguageSettings").find("delete_listing").text();
    }
	
	    //======================menu===========================
    if($serviceDataIndex.find("LanguageSettings").find("Home").text())
    {
        Home=$menuXmlData.find("LanguageSettings").find("Home").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Main_Menu").text())
    {
        Main_Menu=$menuXmlData.find("LanguageSettings").find("Main_Menu").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("add_your_listing_dir").text())
    {
        add_your_listing_dir=$menuXmlData.find("LanguageSettings").find("add_your_listing_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("add_update_listing_dir").text())
    {
        add_update_listing_dir=$menuXmlData.find("LanguageSettings").find("add_update_listing_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Bookmarks").text())
    {
        BookmarksDir=$menuXmlData.find("LanguageSettings").find("Bookmarks").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Login_SignUp").text())
    {
        Login_SignUp=$menuXmlData.find("LanguageSettings").find("Login_SignUp").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("LogOut").text())
    {
        LogOutTxt=$menuXmlData.find("LanguageSettings").find("LogOut").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("search_directory_dir").text())
    {
        enter_your_search_here_dir=$menuXmlData.find("LanguageSettings").find("search_directory_dir").text();
    }
    
    
    //========================filter========================
    if($serviceDataIndex.find("LanguageSettings").find("Filter").text())
    {
        Filter=$menuXmlData.find("LanguageSettings").find("Filter").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Short_By").text())
    {
        Short_By=$menuXmlData.find("LanguageSettings").find("Short_By").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("distance_dir").text())
    {
        distance_dir=$menuXmlData.find("LanguageSettings").find("distance_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("rating_dir").text())
    {
        rating_dir=$menuXmlData.find("LanguageSettings").find("rating_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("search_button_dir").text())
    {
        search_button_dir=$menuXmlData.find("LanguageSettings").find("search_button_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("KM_dir").text())
    {
        KM_dir=$menuXmlData.find("LanguageSettings").find("KM_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Mile_dir").text())
    {
        Mile_dir=$menuXmlData.find("LanguageSettings").find("Mile_dir").text();
    }

    //=====================Detail page=============
    
    
    if($serviceDataIndex.find("LanguageSettings").find("reviews_Dir").text())
    {
        reviews_Dir=$menuXmlData.find("LanguageSettings").find("reviews_Dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("user_reviews_ratings_dir").text())
    {
        user_reviews_ratings_dir=$menuXmlData.find("LanguageSettings").find("user_reviews_ratings_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Users_Dir").text())
    {
        Users_Dir=$menuXmlData.find("LanguageSettings").find("Users_Dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("fromDir").text())
    {
        fromDir=$menuXmlData.find("LanguageSettings").find("fromDir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("send_request").text())
    {
        send_request=$menuXmlData.find("LanguageSettings").find("send_request").text();
    }
	
        dirSummaryListingDetailsPage=$serviceDataIndex.find('dirSummaryListingDetailsPage').text().trim();
    
     
    
    
    //=====================Rating page=============
    if($serviceDataIndex.find("LanguageSettings").find("post_your_ratingreview_dir").text())
    {
        post_your_ratingreview_dir=$menuXmlData.find("LanguageSettings").find("post_your_ratingreview_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("no_review_available").text())
    {
        no_review_available=$menuXmlData.find("LanguageSettings").find("no_review_available").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("please_select_ratings_dir").text())
    {
        please_select_ratings_dir=$menuXmlData.find("LanguageSettings").find("please_select_ratings_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("comments_cannot_be_leftblank_dir").text())
    {
        comments_cannot_be_leftblank_dir=$menuXmlData.find("LanguageSettings").find("comments_cannot_be_leftblank_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Rating_and_Comment_alert").text())
    {
        Rating_and_Comment_alert=$menuXmlData.find("LanguageSettings").find("Rating_and_Comment_alert").text();
    }

    if($serviceDataIndex.find("LanguageSettings").find("Rating_and_Comment_alert_with_review").text())
    {
        Rating_and_Comment_alert_with_review=$menuXmlData.find("LanguageSettings").find("Rating_and_Comment_alert_with_review").text();
    }

    
    //=====================Bookmarked=============
    if($serviceDataIndex.find("LanguageSettings").find("Bookmarked_successfully").text())
    {
        Bookmarked_successfully=$menuXmlData.find("LanguageSettings").find("Bookmarked_successfully ").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Bookmarked_Deleted_successfully").text())
    {
        Bookmarked_Deleted_successfully=$menuXmlData.find("LanguageSettings").find("Bookmarked_Deleted_successfully").text();
    }

    
    //===========send request===================
    
    if($serviceDataIndex.find("LanguageSettings").find("request_a_service").text())
    {
        request_a_service=$menuXmlData.find("LanguageSettings").find("request_a_service").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Submit_your_request_to_providers").text())
    {
        Submit_your_request_to_providers=$menuXmlData.find("LanguageSettings").find("Submit_your_request_to_providers").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("name_Dir").text())
    {
        name_Dir=$menuXmlData.find("LanguageSettings").find("name_Dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("enter_your_name").text())
    {
        enter_your_name=$menuXmlData.find("LanguageSettings").find("enter_your_name").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("phone_Dir").text())
    {
        phone_Dir=$menuXmlData.find("LanguageSettings").find("phone_Dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Enter_your_phone_number").text())
    {
        Enter_your_phone_number=$menuXmlData.find("LanguageSettings").find("Enter_your_phone_number").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("address_dir").text())
    {
        address_dir=$menuXmlData.find("LanguageSettings").find("address_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Enter_your_full_address").text())
    {
        Enter_your_full_address=$menuXmlData.find("LanguageSettings").find("Enter_your_full_address").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("What_is_your_budget").text())
    {
        What_is_your_budget=$menuXmlData.find("LanguageSettings").find("What_is_your_budget").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Enter_your_budget").text())
    {
        Enter_your_budget=$menuXmlData.find("LanguageSettings").find("Enter_your_budget").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("What_is_your_requirement").text())
    {
        What_is_your_requirement=$menuXmlData.find("LanguageSettings").find("What_is_your_requirement").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Please_describe_your_request").text())
    {
        Please_describe_your_request=$menuXmlData.find("LanguageSettings").find("Please_describe_your_request").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("upload_image_dir").text())
    {
        upload_image_dir=$menuXmlData.find("LanguageSettings").find("upload_image_dir").text();
    }
    
    if($serviceDataIndex.find("LanguageSettings").find("Your_submited_request_send_successfuly").text())
    {
        Your_submited_request_send_successfuly=$menuXmlData.find("LanguageSettings").find("Your_submited_request_send_successfuly").text();
    }
    
    //======update or submit listing===================
    
    if($serviceDataIndex.find("LanguageSettings").find("Select_Category").text())
    {
        Select_Category=$menuXmlData.find("LanguageSettings").find("Select_Category").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("heading_dir").text())
    {
        heading_dir=$menuXmlData.find("LanguageSettings").find("heading_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("please_enter_the_heading_dir").text())
    {
        please_enter_the_heading_dir=$menuXmlData.find("LanguageSettings").find("please_enter_the_heading_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("summary_dir").text())
    {
        summary_dir=$menuXmlData.find("LanguageSettings").find("summary_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("please_enter_the_summary").text())
    {
        please_enter_the_summary=$menuXmlData.find("LanguageSettings").find("please_enter_the_summary").text();
    }
    
    if($serviceDataIndex.find("LanguageSettings").find("body_dir").text())
    {
        body_dir=$menuXmlData.find("LanguageSettings").find("body_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("please_enter_the_body").text())
    {
        please_enter_the_body=$menuXmlData.find("LanguageSettings").find("please_enter_the_body").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("images_dir").text())
    {
        images_dir=$menuXmlData.find("LanguageSettings").find("images_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("click_to_upload_image_dir").text())
    {
        click_to_upload_image_dir=$menuXmlData.find("LanguageSettings").find("click_to_upload_image_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("video_dir").text())
    {
        video_dir=$menuXmlData.find("LanguageSettings").find("video_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("audio_dir").text())
    {
        audio_dir=$menuXmlData.find("LanguageSettings").find("audio_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Media_RSS").text())
    {
        Media_RSS=$menuXmlData.find("LanguageSettings").find("Media_RSS").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Radio_PLS").text())
    {
        Radio_PLS=$menuXmlData.find("LanguageSettings").find("Radio_PLS").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("CustomDir").text())
    {
        CustomDir=$menuXmlData.find("LanguageSettings").find("CustomDir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Your_submited_request_send_successfuly").text())
    {
        Your_submited_request_send_successfuly=$menuXmlData.find("LanguageSettings").find("Your_submited_request_send_successfuly").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("enter_url_dir").text())
    {
        enter_url_dir=$menuXmlData.find("LanguageSettings").find("enter_url_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("please_enter_valid_youtubeurl_dir").text())
    {
        please_enter_valid_youtubeurl_dir=$menuXmlData.find("LanguageSettings").find("please_enter_valid_youtubeurl_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("url_dir").text())
    {
        url_dir=$menuXmlData.find("LanguageSettings").find("url_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("please_enter_valid_url_dir").text())
    {
        please_enter_valid_url_dir=$menuXmlData.find("LanguageSettings").find("please_enter_valid_url_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("email_dir").text())
    {
        email_dir=$menuXmlData.find("LanguageSettings").find("email_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("enter_valid_emailaddress_dir").text())
    {
        enter_valid_emailaddress_dir=$menuXmlData.find("LanguageSettings").find("enter_valid_emailaddress_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("phone_Dir").text())
    {
        phone_Dir=$menuXmlData.find("LanguageSettings").find("phone_Dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Enter_your_phone_number").text())
    {
        Enter_your_phone_number=$menuXmlData.find("LanguageSettings").find("Enter_your_phone_number").text();
    }
        if($serviceDataIndex.find("LanguageSettings").find("address_dir").text())
    {
        address_dir=$menuXmlData.find("LanguageSettings").find("address_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Enter_your_full_address").text())
    {
        Enter_your_full_address=$menuXmlData.find("LanguageSettings").find("Enter_your_full_address").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Launch_map_in_application").text())
    {
        Launch_map_in_application=$menuXmlData.find("LanguageSettings").find("Launch_map_in_application").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("add_more_dir").text())
    {
        add_more_dir=$menuXmlData.find("LanguageSettings").find("add_more_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("listing_submitted_sucessfully_under_review_dir").text())
    {
        listing_submitted_sucessfully_under_review_dir=$menuXmlData.find("LanguageSettings").find("listing_submitted_sucessfully_under_review_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("add_listing").text())
    {
        add_listing=$menuXmlData.find("LanguageSettings").find("add_listing").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("instruction_recommended_image_dir").text())
    {
        instruction_recommended_image_dir=$menuXmlData.find("LanguageSettings").find("instruction_recommended_image_dir").text();
    }
    //================setting==========
    
    if($serviceDataIndex.find("LanguageSettings").find("save_dir").text())
    {
        save_dir=$menuXmlData.find("LanguageSettings").find("save_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Profile_Updated_Successfully").text())
    {
        Profile_Updated_Successfully=$menuXmlData.find("LanguageSettings").find("Profile_Updated_Successfully").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Fail_to_update_profile").text())
    {
        Fail_to_update_profile=$menuXmlData.find("LanguageSettings").find("Fail_to_update_profile").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Settings_Dir").text())
    {
        Settings_Dir=$menuXmlData.find("LanguageSettings").find("Settings_Dir").text();
    }
    //====================alert=========
    
    if($serviceDataIndex.find("LanguageSettings").find("no_list_in_this_category_dir").text())
    {
        no_list_in_this_category_dir=$menuXmlData.find("LanguageSettings").find("no_list_in_this_category_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Successfully_Deleted").text())
    {
        Successfully_Deleted=$menuXmlData.find("LanguageSettings").find("Successfully_Deleted").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("No_listing_for_update").text())
    {
        No_listing_for_update=$menuXmlData.find("LanguageSettings").find("No_listing_for_update").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("data_not_availaible_dir").text())
    {
        data_not_availaible_dir=$menuXmlData.find("LanguageSettings").find("data_not_availaible_dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Please_select_your_category").text())
    {
        Please_select_your_category=$menuXmlData.find("LanguageSettings").find("Please_select_your_category").text();
    }
    
    if($serviceDataIndex.find("LanguageSettings").find("Network_connection_error_please_try_again_later").text())
    {
        Network_connection_error_please_try_again_later=$menuXmlData.find("LanguageSettings").find("Network_connection_error_please_try_again_later").text();
    }
    //=============checkin===============
    if($serviceDataIndex.find('defaultDistance'))
    {
        defaultDistance=$serviceDataIndex.find('defaultDistance').text().trim();
        if(defaultDistance=="miles")
        {
            sessionStorage.setItem("distanceUnits","MI");
        }
        else
        {
            sessionStorage.setItem("distanceUnits","KM");
        }
    }
    
    if($serviceDataIndex.find('dirMapDisplay').text().trim()== '0')
    {
        sessionStorage.setItem("dirMapDisplay","false");
    }
    else
    {
        sessionStorage.setItem("dirMapDisplay","true");
        
    }
    if($serviceDataIndex.find("LanguageSettings").find("Check_In_Dir").text())
    {
        Check_In_Dir=$menuXmlData.find("LanguageSettings").find("Check_In_Dir").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("Checkins_Dir").text())
    {
        Checkins_Dir=$menuXmlData.find("LanguageSettings").find("Checkins_Dir").text();
    }
    
    if($serviceDataIndex.find("LanguageSettings").find("Checked_In").text())
    {
        Checked_In=$menuXmlData.find("LanguageSettings").find("Checked_In").text();
    }
    //========================================================
    if($serviceDataIndex.find("LanguageSettings").find("media_rss_url").text())
    {
        media_rss_url=$menuXmlData.find("LanguageSettings").find("media_rss_url").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("media_radio_pls_url").text())
    {
        media_radio_pls_url=$menuXmlData.find("LanguageSettings").find("media_radio_pls_url").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("custom_url").text())
    {
        custom_url=$menuXmlData.find("LanguageSettings").find("custom_url").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("custom_track_name").text())
    {
        custom_track_name=$menuXmlData.find("LanguageSettings").find("custom_track_name").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("custom_track_description").text())
    {
        custom_track_description=$menuXmlData.find("LanguageSettings").find("custom_track_description").text();
    }
    if($serviceDataIndex.find("LanguageSettings").find("call_dir").text())
    {
        call_dir=$menuXmlData.find("LanguageSettings").find("call_dir").text();
    }
    //**********end fetch dynamic var*******//
    
	if($serviceDataIndex.find('reviewSetting').text() == '0'||$serviceDataIndex.find('reviewSetting').text()=="")
    {
		
        sessionStorage.setItem("reviewSetting","false");
    }
    else
    {
        sessionStorage.setItem("reviewSetting","true");
    }
	
	 if($serviceDataIndex.find('formSetting').text().trim() == '0'||$serviceDataIndex.find('formSetting').text().trim()=="")
    {
        sessionStorage.setItem("fornBuilderSetting","false");
    }
    else
    {
        sessionStorage.setItem("fornBuilderSetting","true");
    }
    
    if($serviceDataIndex.find('isSearch').text() == 'true')
    {
        sessionStorage.setItem("serivceIsSearch","true");
		//console.log("removing header"+sessionStorage.getItem("serivceIsSearch"));  
    }
    else
    {
        sessionStorage.setItem("serivceIsSearch","false");
    }
	console.log("removing header"+sessionStorage.getItem("serivceIsSearch"));  
	 if($serviceDataIndex.find('checkonEnable').text().trim() == '1')
    {
        sessionStorage.setItem("checkonEnable","true");
		
		
		checkin='block';
		
    }
    else
    {
        sessionStorage.setItem("checkonEnable","false");
		//$(".hyper-addons a.checkin-icon").css("display", "none");
				checkin='none';

    }
    
    if($serviceDataIndex.find('isLocationSearch').text() == 'true')
    {
        sessionStorage.setItem("serivceisLocationSearch","true");
    }
    else
    {
        sessionStorage.setItem("serivceisLocationSearch","false");
    }
    if($serviceDataIndex.find('addListing').text() == 'true')
    {
        sessionStorage.setItem("addListing","true");
    }
    else
    {
        sessionStorage.setItem("addListing","false");
    }
    
	
	if($serviceDataIndex.find('formSetting').text() == '1')
    {
        sessionStorage.setItem("formSetting","1");
    }
    else
    {
        sessionStorage.setItem("formSetting","0");
    }
	
	
    if($serviceDataIndex.find('reviesSetting').text() == '0')
    {
        sessionStorage.setItem("reviesSetting","false");
    }
    else
    {
        sessionStorage.setItem("reviesSetting","true");
    }
	
	//change by dherarj
	 if($serviceDataIndex.find('listingShare').text() == '0')
    {
        sessionStorage.setItem("listingShare","false");
    }
    else
    {
        sessionStorage.setItem("listingShare","true");
		
    }
	
	
    console.log("getService() -----> 1");
    var finalPageId='';
    var countPageId=0;
    console.log("getService() -----> 2");
    
   if(localStorage.getItem("fooduserid"))
    {
    checkServicelogin(localStorage.getItem("loginemail"),localStorage.getItem("loginpassword"));
    }
    else
    {
       
    }
    
    //===sp
    
    if($serviceDataIndex.find('dirListingVersion').text() == '0'||$serviceDataIndex.find('dirListingVersion').text()=="")
    {
        sessionStorage.setItem("serviceoldversion", 0);
    }
    else
    {
        sessionStorage.setItem("serviceoldversion", 1);
    }
    
    if($serviceDataIndex.find('defaultImgEnable').text() == '0'||$serviceDataIndex.find('defaultImgEnable').text()=="")
    {
        sessionStorage.setItem("defaultImgEnable", 0);
    }
    else
    {
        sessionStorage.setItem("defaultImgEnable", 1);
    }
    
    
    if($serviceDataIndex.find("dirDefaultImg").text())
    {
        dirDefaultImg=$menuXmlData.find("dirDefaultImg").text();
        sessionStorage.setItem("dirDefaultImg",dirDefaultImg)
    }
    //====sp
    
    
    
    
    
    
    
 if(toaster.isLocationEnabled())
  {
   
  }
  else
  {
   toaster.openLocationSetting();
  }
	
    //getLocalCords();
   var PageName=masterData.getElementsByTagName('pageNewid')[0].firstChild.nodeValue.split(',');
    for(var w=0;w<dirPageIndex.length;w++)
    {
        console.log("getService() -----> in loop --->"+w);
        if(dirPageIndex[w].indexOf('services_') != -1)
        {
            if(countPageId == index)
            {
                finalPageId = dirPageIndex[w];
                console.log("getService() finalPageId--->"+finalPageId);
                sessionStorage.setItem('dirPageId',finalPageId);
                var PageNameName = PageName[w];
                serviceHeaderName=PageName[w];;
                getDirectoryCat();
            }
            countPageId++
        }
    }
    

   
        var htmlD="<div class='location-popup'>\
         <div class='location-search'><input placeholder='+placeholder_city_state_country_services+' id='weatherLocationDir' name='weatherLocationDir' onKeyPress='searchClickDir();' type='text' data-role='none' size='50'/><input type='button' value='Cancel' onclick='searchCancelDir()'></div>\
        <div class='location-data'>\
        <ul id='weatherListDir'></ul>\
        </div></div>";

    
    $("body").append(htmlD);
    $(".location-popup").hide();
    $("header .locate").click(function(){
                              $(".location-popup").css("top", "-100%").show().animate({
                                                                                      top:"0"
                                                                                      }, "slow");
                              })
    $(".location-popup li, .location-popup :button").click(function(){
                                                           $(".location-popup").css("top", "0").animate({
                                                                                                        top:"-100%"
                                                                                                        }, "slow", function(){
                                                                                                        $(".location-popup").hide();
                                                                                                        });
                                                           })
    
    $("header .bookmark").click(function(){
                                if($(this).is(".on"))
                                {
                                $(this).removeClass("on")
                                }
                                else
                                {
                                $(this).addClass("on")
                                }
                                return false;
                                });
  
}


function menuAppend()
{
    $(".hyperlocal-menu").remove();
    var profilePic =  localStorage.getItem("local_imageURI");
    if(!profilePic)
    {
        profilePic = "images/hyper-local/vandor-pic.png";
    }
    var userName="";
    if((localStorage.getItem("userName")==null)||(localStorage.getItem("userName")=='null'))
    {
        userName="";
    }
    else
    {
        userName=localStorage.getItem("userName");
    }
    var userLocation="";
    if((localStorage.getItem("userLocation")==null)||(localStorage.getItem("userLocation")=='null'))
    {
        userLocation="";
    }
    else
    {
        userLocation=localStorage.getItem("userLocation");
    }
    
    var signInTxt='';
    if((window.localStorage.getItem("ecomuserid")!=null)&&(window.localStorage.getItem("ecomuserid")!='null'))
    {
        signInTxt='<a href="#" class="logout" onclick="loginService();" id="logoutP">'+LogOutTxt+'</a>';
    }
    else
    {
        signInTxt='<a href="#" class="logout" onclick="loginService();" id="logoutP">'+Login_SignUp+'</a>';
    }
    var addAndupdListingDisp='none';
    if(sessionStorage.getItem("addListing")=="true")
    {
    addAndupdListingDisp='block';
    }
    var htmlD = '<div class="sub-menu hyperlocal-menu"  id="menuservice">\
    <ul>\
    <li>\
    <div class="profile-image">\
    <div>\
    <span class="image"  ><img id="profilePic" src="'+profilePic+'" onclick="settingProfile()"></span>\
    </div>\
    <a href="#" class="edit-image" onclick="settingProfile()"></a>\
    <span class="name" id="userName">'+userName+'</span>\
    <span class="location" id="userLocation">'+userLocation+'</span>\
    </div>\
    </li>\
    <li><a href="#" class="home-listing" onclick="HomeDirectory()" >'+Home+'</a></li>\
	<li><a href="#" class="mainmenu-listing" onclick="MainMenu()" >'+Main_Menu+'</a></li>\
    <li><a href="#" class="submit-listing" id="submitListing" style="display:'+addAndupdListingDisp+'"  onclick="getDirectoryCatIdForListingAddUpdate(0)" >'+add_your_listing_dir+'</a></li>\
    <li><a href="#" class="update-listings" id="updateListing"  style="display:'+addAndupdListingDisp+'" onclick="getDirectoryCatIdForListingAddUpdate(1)" >'+add_update_listing_dir+'</a></li>\
    <li><a href="#" class="requests-received" id="reqBookmarks" style="display:block" onclick="reqBookmarks()" >'+BookmarksDir+'</a></li>\
    <li>'+signInTxt+'</li>\
    </ul>\
    <div class="search-text"><input type="search" id="searchmenu" placeholder="'+search_directory_dir+'" onkeyup="searchEventDir(event, this)" data-role="none"><a class="close" href="#" onclick="menuHide()"></a> <div>\
    </div>';
   
    $("body").append(htmlD);

    
    $(".hyperlocal-menu").hide();
    $(".hyper-navs").click(function(){
                           // <a href="#" onclick="settingProfile()" class="settings">Settings</a>\
                           return false;
                           });
    
    $(".hyperlocal-menu a.close").click(function(){
                                        $(".hyperlocal-menu").css("top", "0").show().animate({"top":"-100%"}, "fast", function(){
                                                                                             $(this).hide();
                                                                                             });
                                        return false;
                                        });
    
    $(".settings").click(function(){
                         $(".hyperlocal-menu").css("top", "0").show().animate({"top":"-100%"}, "fast", function(){
                                                                              $(this).hide();
                                                                              });
                         return false;
                         });
    
    
    $(".share-view").hide().click(function(){
                                  $(".share-view").fadeOut("slow");
                                  return false;
                                  });
    
    

}

function searchEventDir(e, o)
{   
	var mEvent = e || window.event;
	var mPressed = mEvent.keyCode || mEvent.which;
	if (mPressed == 13) {
		var searchText = document.getElementById("searchmenu").value;
        if(searchText.trim()!="")
        {
            var filterType='textSearchMenu';
            filterSearch(filterType,0,0,0,0,0);
			menuHide();
        }   
	}
}

function searchCancelDir()
{
    $(".location-popup").css("top", "0").animate({
                                                 top:"-100%"
                                                 }, "slow", function(){
                                                 $(".location-popup").hide();
                                                 });
}
function searchClickDir()
{
    
    weatherGeocodeDir('weatherLocationDir','weatherListDir');
}

function weatherGeocodeDir(search,output) {
    
    var status;
    var results;
    var html = '';
    var msg = '';
    
    // Set document elements
    var search = document.getElementById(search).value;
    var output = document.getElementById(output);
    if(navigator.network.connection.type == Connection.NONE)
    {
        navigator.notification.alert(alert_internet_conn_not_avail_services, function(){}, alert_services, "");
    }
    else
    {
        
        if (search) {
            
            output.innerHTML = '';
            
            // Cache results for an hour to prevent overuse
            now = new Date();
            
            // Create Yahoo Weather feed API address
            var query = 'select * from geo.places where text="'+ search +'"';
            var api = 'http://query.yahooapis.com/v1/public/yql?q='+ encodeURIComponent(query) +'&rnd='+ now.getFullYear() + now.getMonth() + now.getDay() + now.getHours() +'&format=json&callback=?';
            
            // Send request
            $.ajax({
                   type: 'GET',
                   url: api,
                   dataType: 'json',
                   success: function(data) {
                   console.log("-------------->>>>>>"+JSON.stringify(data));
                   if (data.query.count > 0 ) {
                   
                   // List multiple returns
                   if (data.query.count > 1) {
                   for (var iCounter=0; iCounter<data.query.count; iCounter++)
                   {
                   html += _getWeatherAddressDir(data.query.results.place[iCounter]);
                   }
                   } else
                   {
                   html += _getWeatherAddressDir(data.query.results.place);
                   }
                   
                   html = html + '</ul>';
                   
                   output.innerHTML = html;
                   
                   } else {
                   output.innerHTML = inner_html_location_couldnot_found_services;
                   }
                   },
                   error: function(data) {
                   output.innerHTML = inner_html_error_has_occured_services;
                   }
                   });
            
        }
        else {
            // No search given
            output.innerHTML = '';
        }
    }
}
function _getWeatherAddressDir(data)
{
    var address = data.name;
    if (data.admin2) address += ', ' + data.admin2.content;
    if (data.admin1) address += ', ' + data.admin1.content;
    address += ', ' + data.country.content;
    var woeid = data.woeid;
    
    
     var latitude = data.centroid.latitude;
     var longitude = data.centroid.longitude;
    
    var timezone=data.timezone.content;
    if(woeid.length>0 && timezone.length>0)
    {
        return '<li onClick="addItemAlertDir(\'' + address + '\',\'' + latitude + '\',\'' + longitude + '\' )"  href="#" rel="'+ woeid +'" title="Click for to see a weather report">'+ address +'</li>';

    }
}
function addItemAlertDir(address,latitude,longitude)
{
    var distanceUnits='KM';
    var selChekId="";
    var rCountWithComma="";
     filterSearch("distance",0,50,distanceUnits,selChekId,rCountWithComma,latitude,longitude);
    $(".location-popup").css("top", "0").animate({
                                                 top:"-100%"
                                                 }, "slow", function(){
                                                 $(".location-popup").hide();
                                                 });
}

 function MainMenu()
{
    navClose();
    getService(mainServiceIndex);
    
}

function HomeDirectory()
{
    $(".hyperlocal-menu").css("top", "0").show().animate({"top":"-100%"}, "fast", function(){
                                                         $(this).hide();
                                                                                                                 });
     allowScroll();
     showHideAd("show");
    directoryHome(0);
}


function directoryHome(isHome)
{
sessionStorage.setItem("AppPageName","Home");
    if(!parseInt(isHome))
    {
   
        $('#page2').attr("style","padding-top:45px !important");
        $(".sub-menu, .login-none").remove();
        //dynamicCSS('');
        //hideslidemenuecom();
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
    getStarted();
    $("#shopmenu").hide();
    $("#logo").show();
    $("#mainback").hide();
    $("#mainbackfoodecom").hide();
    Showhideupdatebutton();
    $("#bookmark").hide();
    sessionStorage.setItem("socialpageback","false");
    sessionStorage.setItem("socialpageback_comment","false");
    sessionStorage.setItem("backfromMenu","true");
	dynamicCSS("");
	if(!parseInt(isHome))
    {
	sessionStorage.setItem("isfromDir",0);
    }
    else
    {
	 sessionStorage.setItem("isfromDir",1);
	}
    }
    else
    {
       var appName = localStorage.getItem("AppName");
        var htmlHeader='<div data-role="header" data-position="fixed" data-theme="a" data-tap-toggle="false" class="" id="appyHeader" style="background:'+sessionStorage.getItem("navBackgroundColor")+'">\
        <span class="logo ui-btn-left" id="logo" ><img src="images/logo.png" /></span>\
        <span class="ui-btn-left main-appypie-back" id="mainback" style="display:none"><img src="images/back-btn.png" onclick="onBackKeyDown();"/></span>\
        <span class="ui-btn-right slide-appypie-back" id="mainbackfoodecom" style="display:none"><img src="images/back-btn.png" onclick="onBackKeyDown(\'Food\');"/></span>\
        <span class="ui-btn-left shopingMenu appypie-shopingMenu" id="shopmenu" style="display:none"><img src="images/e-com/menu-btn.png" onclick="showEcomMenu();" width="40" height="40"/></span>\
        <h1 id="pageTitle" style="color: #fff;font-size: 20px;font-weight: normal;">'+appName+'</h1>\
        <a onclick="chkupd();" class="ui-btn ui-shadow ui-corner-all ui-icon-reload ui-btn-icon-notext ui-btn-right appypie-reload" id="reload"><sub class="value" id="supValueChange">0</sub><span>Delete</span></a>\
        <a href="javascript:;" class="ui-btn ui-shadow ui-corner-all ui-icon-bookmark ui-btn-icon-notext ui-btn-right appypie-reload" id="bookmark" onclick="bookmark();" style="display:none;"><span>bookmark</span></a></div>';
        $(''+htmlHeader).insertAfter('#page1');
        $.mobile.resetActivePageHeight();
        $( "[data-role='header']" ).toolbar();
        $("#shopmenu").hide();
        $("#logo").hide();
        $("#mainback").show();
        $("#mainbackfoodecom").hide();
        $("#reload").hide();
        $("#bookmark").hide();
        sessionStorage.setItem("isfromDir",1);
        $(".hyperlocal-menu").hide();
    }
    
}
function dirSubCatIdChange()
{
    catIdSelected=document.getElementById("dir_Subcatid").value;
    
    //alert(catIdSelected);
}
function dirCatIdChange()
{
    //    var a = catNameArray.indexOf(document.getElementById("dir_catid").value);
    //    catIdSelected=catIdArray[a];
    
    
    
    document.getElementById("dir_Subcatid").innerHTML = "";
    // get reference to select element
    var sel = document.getElementById('dir_Subcatid');
    var opt = document.createElement('option');
    opt.appendChild( document.createTextNode("Select SubCategory") );
    opt.value = "";
    sel.appendChild(opt);
    
    catIdSelected=document.getElementById("dir_catid").value;
    // alert(catIdSelected);
     $('.appypie-loader').show();
    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/directory-soap#getDirectoryListWithSubCategoryXmlNew";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getDirectoryListWithSubCategoryXmlNew xmlns=\"http://'+localStorage.getItem("reseller")+'/services/directory-soap#getDirectoryListWithSubCategoryXmlNew\"><appId>'+localStorage.getItem("applicationID")+'</appId><dirPageId>'+sessionStorage.getItem('dirPageId')+'</dirPageId><catId>'+catIdSelected+'</catId><count>3000</count><pageNo>1</pageNo><searchText></searchText><searchListId></searchListId><emailUser></emailUser></getDirectoryListWithSubCategoryXmlNew></soap:Body></soap:Envelope>';
    
    //console.log("getDirectoryListWithSubCategoryXmlNew -->soapRequest --->"+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           var returnData=$(req.responseText).find("return").text();
           SubCatNameAddListArray=[];
           SubCatIdAddListArray=[];
           if($(returnData).find('subCatrecord').text())
           {
           $(returnData).find("subCatrecord").each(function()
                                                   {
                                                   
                                                   var categoryName=$(this).find("categoryName").text();
                                                   var catId=$(this).find("catId").text();
                                                   SubCatNameAddListArray.push(categoryName);
                                                   SubCatIdAddListArray.push(catId);
                                                   
                                                   // get reference to select element
                                                   var sel = document.getElementById('dir_Subcatid');
                                                   // create new option element
                                                   var opt = document.createElement('option');
                                                   // create text node to add to option element (opt)
                                                   opt.appendChild( document.createTextNode(categoryName) );
                                                   // set value property of opt
                                                   opt.value = catId;
                                                   // add opt to end of select box (sel)
                                                   sel.appendChild(opt);
                                                   
                                                   });
           }
           
           
           
           
           },
           error: function(response, textStatus, errorThrown)
           {
           //console.log("in error --->() "+response.responseText);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    setTimeout(function(){$('.appypie-loader').hide();},500);
    
    
}
function submitListing(catIdArray,catNameArray)
{
    bodyImageUpdateListing="";
    sessionStorage.setItem("addurlCount",0);
    sessionStorage.setItem("addCallCount",0);
    sessionStorage.setItem("addEmailCount",0);
    
    imageArraySubmitList=[];
    var optionHtmlStr='<option value="Select Category" data-role="none">'+Select_Category+'</option>';
    for(var i=0;i<catIdArray.length;i++)
    {
        optionHtmlStr+='<option value="'+catIdArray[i]+'">'+catNameArray[i]+'</option>';
    }
    
    if((localStorage.getItem("isLogin")==null)||(localStorage.getItem("isLogin")=='null'))
    {
        loginService();
    }
    else
    {
        var addListing='<header class="hyper-local header-main "><a class="hyper-back" href="#" onclick="onBackKeyDown(\'Food\');"></a> <h1>'+add_your_listing_dir+'</h1></header><section class="hyper-local categorys-page"><div class="scroller-on"><ul class="add-listing"><li> <select id="dir_catid" onchange="dirCatIdChange()" data-role="none"> '+optionHtmlStr +'</select>\
        <br><br><select id="dir_Subcatid" onchange="dirSubCatIdChange()" data-role="none"><option value="" data-role="none">Select SubCategory</option></select>\
        </li>\
        <li><input type="text" placeholder="'+heading_dir+' *" id="hedingSL" data-role="none"></li>\
        <li><textarea placeholder="'+summary_dir+' *" id="summarySL" data-role="none"></textarea></li>\
        <li><textarea placeholder="'+body_dir+' *" id="bodySL" data-role="none"></textarea></li>\
        <li>\
        <div class="listing-tabs"><a href="#" class="images active"  onclick="changeTabDirUploadNew(this.id)" id="imageTabDir" data-role="none">'+images_dir+'</a><a href="#" class="videos" onclick="changeTabDirUploadNew(this.id)" id="videoTabDir" data-role="none">'+video_dir+'</a><a href="#" class="audios" onclick="changeTabDirUploadNew(this.id)" id="audioTabDir" data-role="none" >'+audio_dir+'</a></div>\
        <div class="listing-tabs-view" id="imageTabDirVw">\
        <h3>'+upload_image_dir+'</h3>\
        <ul id="ulSL0" >\
        <li>\
        <p>'+click_to_upload_image_dir+'</p>\
        <img src="images/hyper-local/no-image.png" id="imageSL0" onclick="selectPhotoDir(0);" data-role="none">\
        </li>\
        </ul>\
        <div class="add-view" id="imgId11">\
        <span>'+instruction_recommended_image_dir+'</span>\
        <a href="#" class="add-more" onclick="addMoreClick(0)" data-role="none">'+add_more_dir+'</a>\
        </div>\
        </div>\
        <div class="listing-tabs-view" style="display:none" id="videoTabDirVw" data-role="none">\
        <h3>'+add_a_video_dir+'</h3>\
        <input type="text" placeholder="'+enter_youtube_url_dir+'" id="youtubeUrl" data-role="none" data-role="none">\
        </div>\
        <div class="listing-tabs-view" style="display:none" id="audioTabDirVw" data-role="none">\
        <h3>'+add_an_audio_playlist_from_dir+'</h3>\
        <div class="providers">\
        <a class="provider" onclick="setAudioMediaType(this.id)" id="soundrss" data-role="none"><img border="0" src="images/rss.png"><div>'+Media_RSS+'</div></a>\
        <a class="provider" id="rssradio" onclick="setAudioMediaType(this.id)" data-role="none"><img border="0" src="images/custom-radio.png"> <div>'+Radio_PLS+'</div></a>\
        <a class="provider" onclick="setAudioMediaType(this.id)" id="customlist" data-role="none"><img border="0" src="images/custom.png"><div>'+CustomDir+'</div></a>\
        </div>\
        <input type="text" data-role="none"  placeholder="'+media_rss_url+'" id="soundrssData" style="display:block">\
        <input type="text" data-role="none"  placeholder="'+media_radio_pls_url+'" id="rssradioData" style="display:none">\
        <input type="text" data-role="none"  placeholder="'+custom_url+'" id="customlistData" style="display:none">\
        <input type="text" data-role="none"  placeholder="'+custom_track_name+'" id="customTrackNameData" style="display:none">\
        <input type="text" data-role="none"  placeholder="'+custom_track_description+'" id="customTrackDescriptionData" style="display:none">\
        </div>\
        </li>\
        <ul id="multipleUrlField"><li class="email-link my-url" ><input type="text" data-role="none" placeholder="'+url_dir+'" id="urlData0"><a href="#" class="add-more2" id="addDataUrl"  onclick="addMoreClickField(0,1)" data-role="none">'+add_more_dir+'</a></li></ul>\
        <ul id="multipleEmailField"><li class="email-link my-email" ><input type="text" data-role="none" placeholder="'+email_dir+'"  id="emailData0" ><a href="#" class="add-more2" onclick="addMoreClickField(0,2)" id="addDataEmail" data-role="none">'+add_more_dir+'</a></li></ul>\
        <ul id="multiplepNumField"><li class="email-link my-phone" ><input type="text" data-role="none" placeholder="'+Enter_your_phone_number+'" id="callData0"><a href="#" class="add-more2" id="addDataCall" onclick="addMoreClickField(0,3)" data-role="none">'+add_more_dir+'</a></li></ul>\
        <li>\
        <textarea placeholder="'+address_dir+' *" id="addSL" data-role="none"></textarea>\
       <textarea class="add-listing2" placeholder="Latitude, Longitude " id="addSLLatLong" data-role="none" style="margin: 0;height: 40px;padding: 0;display:none;"></textarea>\
		<a class="hyper-sent-btn icon-location-2" onclick="getMap()"  style="margin: 0;width: 40px;padding: 0;text-align: center;margin: 6px 5px 0 0;float: right;min-width: 30px;padding: 4px; display:none;"> </a>\
        <span class="map-checkbox"><input type="checkbox" id="chkSL" name="chkSL" data-role="none"> '+Launch_map_in_application+'</span>\
        </li>\
        </ul>\
        <div class="hyper-gray-wrapper">\
        <button class="hyper-sent-btn" onclick="addListingClick(\'add\')" data-role="none">'+add_listing+'</button></div></div></section>';
        
        
        // <li><input type="text" placeholder="Make a Reservation" id="MkSL"></li>\
        
        navClose();;
        appendHtml(addListing,2,2);
       resetDirectoryHeader();
        
    }
}

function getMap(){
	console.log("getMap method....");
	toaster.openMapForAddress();
}


function setLatLongService(lat, longi, address){
//	alert("Address: "+address);
	if(address){
		$("#addSL").val(" "+address);
	}
	$("#addSLLatLong").val(" "+lat+", "+longi);
	localStorage.setItem("lat", lat);
	localStorage.setItem("longi", longi);
}


function addMoreClickField(type,isFrom)
{
    var indexRowUrl=parseInt(type);
    var appendIndexUrl=parseInt(indexRowUrl)+1;
    console.log("appendIndexUrl "+appendIndexUrl);
    if(isFrom=="1")//url
    {
        sessionStorage.setItem("addurlCount",appendIndexUrl);
        $('#multipleUrlField').append('<li class="email-link my-url" id="urlliId'+appendIndexUrl+'"><input type="text" data-role="none" placeholder="'+url_dir+'" id="urlData'
                                      +appendIndexUrl+'"><a href="#" class="close-btn" id="deleteurl'+appendIndexUrl+'" onclick="deleteField('+appendIndexUrl+',1)">X</a><a href="#" class="add-more2" id="addDataUrl"  onclick="addMoreClickField('+appendIndexUrl+',1)">'+add_more_services+'</a></li>');
    }
    else if(isFrom=="2")//email
    {
        sessionStorage.setItem("addEmailCount",appendIndexUrl);
        $('#multipleEmailField').append('<li class="email-link my-email" id="emailliId'+appendIndexUrl+'"><input type="text" placeholder="'+email_dir+'" id="emailData'
                                        +appendIndexUrl+'"><a href="#" class="close-btn" id="deleteEmail'+appendIndexUrl+'" onclick="deleteField('+appendIndexUrl+',2)">X</a><a href="#" class="add-more2" id="addDataEmail"  onclick="addMoreClickField('+appendIndexUrl+',2)">'+add_more_dir+'</a></li>');
    }
    else//phone num
    {
        sessionStorage.setItem("addCallCount",appendIndexUrl);
        $('#multiplepNumField').append('<li class="email-link my-phone" id="pNumliId'+appendIndexUrl+'"><input type="text" placeholder="'+phone_Dir+'" id="callData'
                                       +appendIndexUrl+'"><a href="#" class="close-btn" id="deletecall'+appendIndexUrl+'" onclick="deleteField('+appendIndexUrl+',3)">X</a><a href="#" class="add-more2" id="addDataCall"  onclick="addMoreClickField('+appendIndexUrl+',3)">'+add_more_dir+'</a></li>');
    
    }
}
function deleteField(type,isFrom)
{
    var indexRowDe=parseInt(type);
    if(indexRowDe>0)
    {

    if(isFrom=="1")//url
    {
        $('#urlliId'+indexRowDe).remove();
        sessionStorage.setItem("addurlCount",(parseFloat(sessionStorage.getItem("addurlCount"))-1));
    }
    else if(isFrom=="2")//email
    {
        $('#emailliId'+indexRowDe).remove();
        sessionStorage.setItem("addEmailCount",(parseFloat(sessionStorage.getItem("addEmailCount"))-1));
    }
    else//phone number
    {
        $('#pNumliId'+indexRowDe).remove();
        sessionStorage.setItem("addCallCount",(parseFloat(sessionStorage.getItem("addCallCount"))-1));
        
    }
        
    }
    
}

function changeTabDirUploadNew(idOfElement)
{
    if(!idOfElement || idOfElement == 'imageTabDir')
    {
        $('#'+idOfElement).addClass('active');
         $('#imgId11').show();
        $('#imageTabDirVw').show();
        $('#videoTabDirVw').hide();
        $('#audioTabDirVw').hide();
        $('#videoTabDir').removeClass('active');
        $('#audioTabDir').removeClass('active');
        
    }
    else if(idOfElement == 'videoTabDir')
    {

                    $('#'+idOfElement).addClass('active');
                     $('#imageTabDirVw').hide();
                    $('#imgId11').hide();
                    $('#videoTabDirVw').show();
                    $('#audioTabDirVw').hide();
                    $('#imageTabDir').removeClass('active');
                    $('#audioTabDir').removeClass('active');
        
    }
    else
    {
        $('#'+idOfElement).addClass('active');
         $('#imgId11').show();
        $('#audioTabDirVw').show();
        $('#imageTabDirVw').hide();
        $('#videoTabDirVw').hide();
        $('#videoTabDir').removeClass('active');
        $('#imageTabDir').removeClass('active');
    }
}
function setAudioMediaType(mediaType)
{
    $('.provider').removeClass('active');
    $('#'+mediaType).addClass('active');
    
    document.getElementById("soundrssData").style.display="none";
    document.getElementById("rssradioData").style.display="none";
    document.getElementById("customlistData").style.display="none";
    document.getElementById("customTrackNameData").style.display="none";
    document.getElementById("customTrackDescriptionData").style.display="none";
    
    if(mediaType.trim()=="soundrss")
    {
        document.getElementById("soundrssData").style.display="block";
    }
    else if(mediaType.trim()=="rssradio")
    {
        document.getElementById("rssradioData").style.display="block";
    }
    else
    {
        document.getElementById("customlistData").style.display="block";
        document.getElementById("customTrackNameData").style.display="block";
        document.getElementById("customTrackDescriptionData").style.display="block";
    }
    
}


function addMoreClick(type)
{
    
    var indexRow=parseInt(type);
    var appendIndex=parseInt(indexRow)+1;
    var htmlAddMore='<ul id="ulSL'+appendIndex+'" >\
    <li>\
    <p>'+click_to_upload_image_dir+'</p>\
    <img src="images/hyper-local/no-image.png" id="imageSL'+appendIndex+'" onclick="selectPhotoDir('+appendIndex+');">\
    <a href="#"  onclick="removeImageData('+appendIndex+');" >X</a>\
    </li>\
    </ul>\
    <div class="add-view" id="imgId11">\
    <span>'+instruction_recommended_image_dir+'</span>\
    <a href="#" class="add-more" onclick="addMoreClick('+appendIndex+')">Add More</a>\
    </div>';
    $('.add-view').remove();
    $('#imageTabDirVw').append(htmlAddMore);
}

function getCityName(lat1, lon1) {

console.log("CurrentCity==getCityName");
var cityName=toaster.getCompleteAddressString(lat1,lon1);
console.log("CurrentCity==getCityName"+cityName);
localStorage.setItem("CurrentCity",cityName);
/*
    var geocoder;
    geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(lat1, lon1);
    
    geocoder.geocode(
                     {'latLng': latlng},
                     function(results, status) {
                     if (status == google.maps.GeocoderStatus.OK) {
                     if (results[0]) {
                     var add= results[0].formatted_address ;
                     var  value=add.split(",");
                     
                     count=value.length;
                     country=value[count-1];
                     state=value[count-2];
                     city=value[count-3];
                     
                     localStorage.setItem("CurrentCity",city);
                   // alert("city name is: " + city);
                     }
                     else  {
                     
                   //  alert("address not found");
                     }
                     }
                     else {
                    // alert("Geocoder failed due to: " + status);
                     }
                     }
                     );
  */  
}
function addListingClick(type,listId)
{
    
    if(type=='update')
    {
        sessionStorage.setItem("isfromUpdate",true);
    }
    else
    {
        sessionStorage.setItem("isfromUpdate",false);
    }
   
    if(type=="delete")
    {
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/directory-soap#directoryPageDeleteMobile";
        console.log("wsUrl---"+wsUrl);
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><directoryPageDeleteMobile xmlns=\"http://'+localStorage.getItem("reseller")+'/services/directory-soap#directoryPageDeleteMobile\"><appId>'+localStorage.getItem("applicationID")+'</appId><pageId>'+listId+'</pageId></directoryPageDeleteMobile></soap:Body></soap:Envelope>';
        
        console.log("directoryPageDeleteMobile --->"+soapRequest);
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               cache: false,
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
    
                navigator.notification.alert(
           			Successfully_Deleted,
           			setTimeout(function(){$('.appypie-loader').hide();},1000),
           			alert_services,
           			alert_ok_services
           			);
					 setTimeout(function(){$('.appypie-loader').hide();},1000)
               
            //   getDirectoryCatIdForListingAddUpdate(1);
               
               },
               error: function(response, textStatus, errorThrown)
               {
               console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
        
    
    }
    else
    {
      //  var catIdSelectedStr=document.getElementById("dir_catid").value;
	  var catIdSelectedStr= catIdSelected;
        var heading=document.getElementById('hedingSL').value;
        var summary= document.getElementById('summarySL').value;
        var body= document.getElementById('bodySL').value;
        
        
        
        var videoData=document.getElementById('youtubeUrl').value;
        if(videoData != '' && videoData.indexOf('youtube') == -1 && (videoData.indexOf('.') == -1 || (videoData.indexOf('https://') == -1 && videoData.indexOf('http://') == -1)))
        {
            alertPopUp(alert_dir,please_enter_valid_youtubeurl_dir);
            return false;
        }
        
        
        var soundrssData=document.getElementById('soundrssData').value;
        var rssradioData=document.getElementById('rssradioData').value;
        var customlistData=document.getElementById('customlistData').value;
        var customTrackNameData=document.getElementById('customTrackNameData').value;
        var customTrackDescriptionData=document.getElementById('customTrackDescriptionData').value;
        
        if(soundrssData != '')
        {
            if(soundrssData != '' && (soundrssData.indexOf('.') == -1 || (soundrssData.indexOf('https://') == -1 && soundrssData.indexOf('http://') == -1)))
            {
                alertPopUp(alert_dir,please_enter_valid_url_dir);
                return false;
            }
        }
        if(rssradioData != '')
        {
            if(rssradioData != '' && (rssradioData.indexOf('.') == -1 || (rssradioData.indexOf('https://') == -1 && rssradioData.indexOf('http://') == -1)))
            {
                alertPopUp(alert_dir,please_enter_valid_url_dir);
                return false;
            }
        }
        if(customlistData != '')
        {
            if(customlistData != '' && (customlistData.indexOf('.') == -1 || (customlistData.indexOf('https://') == -1 && customlistData.indexOf('http://') == -1)))
            {
                alertPopUp(alert_dir,please_enter_valid_url_dir);
                return false;
            }
            
            if(customTrackNameData.trim()=="")
            {
                alertPopUp(alert_dir,custom_track_cant_blank);
                return false;
            }
            if(customTrackDescriptionData.trim()=="")
            {
                alertPopUp(alert_dir,custom_track_desc_cant_blank);
                return false;
            }
        }
        
        
        
        var urlData='';
        var urlDataSend='';
        var urlLabaelSend='';
        for(var i=0;i<(parseFloat(sessionStorage.getItem("addurlCount"))+1);i++)
        {
            console.log("the value of i -->"+i);
            urlData=document.getElementById('urlData'+i).value;
            console.log("urlData --->"+urlData);
            if(urlData == '')
            {
                urlData='';
            }
            else
            {
                if(urlData != '' && (urlData.indexOf('.') == -1 || (urlData.indexOf('https://') == -1 && urlData.indexOf('http://') == -1)))
                {
                    alertPopUp(alert_dir,please_enter_valid_url_dir);
                    return false;
                }
                else
                {
                    console.log("urlDataSend --->"+urlData);
                    if(i <= parseFloat(sessionStorage.getItem("addurlCount")) && urlDataSend != '')
                    {
                        urlDataSend=urlDataSend+','+urlData;
                        urlLabaelSend=urlLabaelSend+',Url';
                    }
                    else
                    {
                        urlDataSend=urlDataSend+urlData;
                        urlLabaelSend=urlLabaelSend+'Url';
                    }
                }
            }
            console.log("urlDataSend --->"+urlDataSend);
            console.log("urlLabaelSend --->"+urlLabaelSend);
        }
        
        var emailData='';
        var emailDataSend='';
        var emailLabelSend='';
        for(var i=0;i<(parseFloat(sessionStorage.getItem("addEmailCount"))+1);i++)
        {
            emailData=document.getElementById('emailData'+i).value;
            if(emailData == '')
            {
                emailData='';
                
            }
            else
            {
                if(!validateForm1(emailData))
                {
                    alertPopUp(alert_dir,enter_valid_emailaddress_dir);
                    return false;
                }
                else
                {
                    if(i <= parseFloat(sessionStorage.getItem("addEmailCount")) && emailDataSend != '')
                    {
                        emailDataSend=emailDataSend+','+emailData;
                        emailLabelSend=emailLabelSend+',Email';
                    }
                    else
                    {
                        emailDataSend=emailDataSend+emailData;
                        emailLabelSend=emailLabelSend+'Email';
                    }
                }
            }
        }
        
        
        
        
        var callData='';
        var callDataSend='';
        var callLabelSend='';
        for(var z=0;z<(parseFloat(sessionStorage.getItem("addCallCount"))+1);z++)
        {
            console.log("i --->"+z);
            callData=document.getElementById('callData'+z).value;
            console.log("callData --->"+callData);
            if(callData == '')
            {
                callData='';
                
            }
            else
            {
                if(!IsNumeric(callData) && callData != '')
                {
                    alertPopUp(alert_dir,Enter_your_phone_number);
                    return false;
                }
                else
                {
                    if(z <= parseFloat(sessionStorage.getItem("addCallCount")) && callDataSend != '')
                    {
                        callDataSend=callDataSend+','+callData;
                        callLabelSend=callLabelSend+',Call';
                    }
                    else
                    {
                        callDataSend=callDataSend+callData;
                        callLabelSend=callLabelSend+'Call';
                    }
                    
                    console.log("callDataSend --->"+callDataSend);
                    console.log("callLabelSend --->"+callLabelSend);
                }
            }
        }
        
        
        var address=document.getElementById('addSL').value;
        var mapInApp=$("input[name='chkSL']:checked").val();
        
        if(mapInApp=="on")
        {
            mapInApp=1;
        }
        else
        {
            mapInApp=0;
        }
        
        if(!catIdSelectedStr)
        {
            catIdSelectedStr='';
            
        }
        
        if(catIdSelectedStr == Select_Category || catIdSelectedStr == 'undefined'  || catIdSelectedStr == '')
        {
            alertPopUp(alert_dir,Please_select_your_category);
            $('#dir_catid').focus();
        }
        else if(heading.trim() == '' )
        {
            alertPopUp(alert_dir,please_enter_the_heading_dir);
            $('#hedingSL').focus();
        }
        
        else if(summary.trim()  =='' )
        {
            alertPopUp(alert_dir, please_enter_the_summary  );
            $('#summarySL').focus();
        }
        else if(body.trim()  == '' )
        {
            alertPopUp(alert_dir,please_enter_the_body);
            $('#bodySL').focus();
        }
        else if(address.trim()  == '' )
        {
            alertPopUp(alert_dir,Enter_your_full_address);
            $('#addSL').focus();
            
        }
        else
        {
            console.log("callDataSend --->"+callDataSend);
            console.log("callLabelSend --->"+callLabelSend);
            var addressLabelData="Address";
            var opentableLabelData="Open Table";
            var opentableData="";
            var ownerEmail=localStorage.getItem("isLogin");
            var listId1="";
            if(type=="update")
            {
                listId1=listId;
            }
            
            
            
          var imageFinalUrlArray=new Array();
            for(var b=0;b<imageArraySubmitList.length;b++)
            {
                if(imageArraySubmitList[b].trim()!="");
                {
                    imageFinalUrlArray[imageFinalUrlArray.length]=imageArraySubmitList[b];
                }
            }
            
            var resellerId=localStorage.getItem("resellerId");
            if(resellerId=="null"||resellerId==null)
            {
                resellerId=0;
            }
            
            var bodyImage=bodyImageUpdateListing;
           var bodyImageUpdateListingArray=bodyImageUpdateListing.split(",");;
				console.log("bodyImageUpdateListingArray 00 "+bodyImageUpdateListingArray);
                var bodyImageFinalArray=new Array();
                var ImageFinalArray=new Array();
                for(var d=0;d<bodyImageUpdateListingArray.length;d++)
                {
                    for(var e=0;e<imageFinalUrlArray.length;e++)
                    {												
                        if(imageFinalUrlArray[e].substring(imageFinalUrlArray[e].lastIndexOf('/') + 1).trim() ==bodyImageUpdateListingArray[d].trim() )
                        {
                      bodyImageFinalArray[bodyImageFinalArray.length]=bodyImageUpdateListingArray[d];
                      imageFinalUrlArray[e]="";					  
                        }
                    }                   
                }	
			
			
			var bodyimgfinal=bodyImageFinalArray.toString();
			var newImgfinal=imageFinalUrlArray.toString();
			
            $('.appypie-loader').show();
          
            toaster.uploadMultipleFilesDirectory(catIdSelectedStr,heading,summary,body,emailLabelSend,emailDataSend,urlLabaelSend,urlDataSend,callLabelSend,callDataSend,addressLabelData,address,opentableLabelData,opentableData,videoData,soundrssData,rssradioData,customlistData,customTrackNameData,customTrackDescriptionData,mapInApp,localStorage.getItem("applicationID"),localStorage.getItem("AppName"),localStorage.getItem("owneremail"),localStorage.getItem("ownerName"),localStorage.getItem("isLogin"),localStorage.getItem("userName"),serviceHeaderName,bodyimgfinal,resellerId,listId1,newImgfinal, localStorage.getItem("lat"), localStorage.getItem("longi")); 
            
        }
    }
    }
function updateListing(catIdArray,catNameArray)
{
    
    
    if((localStorage.getItem("isLogin")==null)||(localStorage.getItem("isLogin")=='null'))
    {
        loginService();
    }
    else
    {
        
        
        
     var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/directory-soap#directoryGetMobileAddList";
        console.log("wsUrl---"+wsUrl);
     var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><directoryGetMobileAddList xmlns=\"http://'+localStorage.getItem("reseller")+'/services/directory-soap#directoryGetMobileAddList\"><appId>'+localStorage.getItem("applicationID")+'</appId><userEmail>'+localStorage.getItem("isLogin")+'</userEmail><dirPageId>'+sessionStorage.getItem('dirPageId')+'</dirPageId><catId></catId></directoryGetMobileAddList></soap:Body></soap:Envelope>';
   
    console.log("getDirectoryCat()  -->soapRequest --->"+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           var returnData=$(req.responseText).find("return").text();
           console.log("Abhisek Rai returnData  "+returnData);
           if(returnData != "No Category available")
           {
           var xmlData=jQuery.parseXML(returnData);
           $serviceBookMarkData=$(xmlData);
           var optionHtmlStr="";
           var isUpdateListing=false;
           var index=0;
           $serviceBookMarkData.find('record').each(function()
                                                 {
                                                 
                                                 var dirPageId=$(this).find("dirPageId").text();
                                                 var catId=$(this).find("catId").text();
                                                 var catName=$(this).find("catName").text();
                                                 var listId=$(this).find("listId").text();
                                                 var reviewRating=$(this).find("reviewRating").text();
                                                 var header=$(this).find("header").text();
                                                 var summary=$(this).find("summary").text();
                                                var mediaImage=$(this).find("mediaImage").text();
                                                 var bodyImageComment=$(this).find("bodyImageComment").text();
                                                 var detail=$(this).find("detail").text();
                                                 var mapThirdParty=$(this).find("mapThirdParty").text();
                                                 var serAddressText=$(this).find("serAddressText").text();
                                                 var serAddressFull=$(this).find("serAddressFull").text();
												 serAddressFull=serAddressFull.replace(/\s+/g, ' ');
                                                 var dirRating=$(this).find("dirRating").text();
                                                 var totalReview=$(this).find("totalReview").text();
                                                 var detail=$(this).find("detail").text();
                                                 
                                                    var imageArray= mediaImage.split(",");
                                                    var image="";
                                                    if(imageArray.length>0)
                                                    {
                                                    
                                                    image=imageArray[0];
                                                    }
                                                    
                                                    console.log("image===="+image);
                                                 
                                                 if(image.trim()=="")
                                                 {
                                                 image="images/logo.png";
                                                 }
                                                   optionHtmlStr+= '<li  onclick="updateListingPage(\''+index+'\',\''+catIdArray+'\',\''+catNameArray+'\');" ><img src='+image+'><strong> '+header+'</strong><span>'+summary+'</span><b><a href="#" class="delete"></a></b></li>';
                                                 isUpdateListing=true;
                                                 index++;
                                                 }); 
           
           
           
           var updateLst='<header class="hyper-local header-main ">\
           <a class="hyper-back" href="#" onclick="onBackKeyDown();"></a>\
           <h1>'+add_update_listing_dir+'</h1>\
           </header>\
           <section class="hyper-local update-listings">\
           <ul>'+optionHtmlStr+'</ul>\
           </section>';
           
           
           if(!isUpdateListing)
           {
             alertPopUp(alert_thanks_services,No_listing_for_update);
           }
           else
           {
           navClose();
           appendHtml(updateLst,2,2);
		   resetDirectoryHeader();
           }
      
           }
           else
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    }

 
}
function updateListingPage(index,catIdArraydata,catNameArraydata)
{
    bodyImageUpdateListing="";
    var catIdArray=catIdArraydata.split(",");
    var catNameArray=catNameArraydata.split(",");
    $serviceDataIndex= $serviceBookMarkData.find("record[indexval="+index+"]");
    var appId=$serviceDataIndex.find("appId").text();
    var dirPageId=$serviceDataIndex.find("dirPageId").text();
    var catId=$serviceDataIndex.find("catId").text();
    var catName=$serviceDataIndex.find("catName").text();
    var listId=$serviceDataIndex.find("listId").text();
    var reviewRating=$serviceDataIndex.find("reviewRating").text();
    var header=$serviceDataIndex.find("header").text();
    var summary=$serviceDataIndex.find("summary").text();
    var image=$serviceDataIndex.find("image").text();
    var bodyImageComment=$serviceDataIndex.find("bodyImageComment").text();
    var detail=$serviceDataIndex.find("detail").text();
    var mapThirdParty=$serviceDataIndex.find("mapThirdParty").text();
    var externalText=new Array();
    var externalDataLabel=new Array();
    var externalLink=new Array();
    var isUrl=false;
    $serviceDataIndex.find('url').each(function()
                                       {
                                       externalText.push($(this).find("externalText").text());
                                       externalDataLabel.push($(this).find("externalDataLabel").text());
                                       externalLink.push($(this).find("externalLink").text());
                                       isUrl=true;
                                       });
    if(!isUrl)
    {
        externalText.push("");
        externalDataLabel.push("");
        externalLink.push("");
    }
    
    var isCall=false;
    var callText=new Array();;
    var callLink=new Array();
    $serviceDataIndex.find('call').each(function()
                                        {
                                        callText.push($(this).find("callText").text());
                                        callLink.push($(this).find("callLink").text());
                                        isCall=true;
                                        });
    if(!isCall)
    {
        externalText.push("");
        externalDataLabel.push("");
        externalLink.push("");
    }
    
    var serEmailDataLabel=new Array();;
    var serEmailFull=new Array();
    var isEmail=false;
    $serviceDataIndex.find('email').each(function()
                                         {
                                         serEmailDataLabel.push($(this).find("serEmailDataLabel").text());
                                         serEmailFull.push($(this).find("serEmailFull").text());
                                         isEmail=true;
                                         });
    if(!isEmail)
    {
        serEmailDataLabel.push("");
        serEmailFull.push("");
    }
    var serAddressText=$serviceDataIndex.find("serAddressText").text();
    var serAddressFull=$serviceDataIndex.find("serAddressFull").text();
    var serlatitude=$serviceDataIndex.find("serlatitude").text();
    var serlongitude=$serviceDataIndex.find("serlongitude").text();
    var serlatitude=$serviceDataIndex.find("serlatitude").text();
    var serlongitude=$serviceDataIndex.find("serlongitude").text();
    var serviceMediaUrl=$serviceDataIndex.find("mediaImage").text();
    bodyImageUpdateListing=$serviceDataIndex.find("bodyImage").text();
	if(serlatitude.trim()==""||serlongitude.trim()=="")
                        {
                            serlatitude="0";
                            serlongitude="0";
                        }
    
    
    
    sessionStorage.setItem("addurlCount",0);
    sessionStorage.setItem("addCallCount",0);
    sessionStorage.setItem("addEmailCount",0);
    
    imageArraySubmitList=[];
    var optionHtmlStr="";
    
    optionHtmlStr+='<option selected="selected" value="'+catId+'">'+catName+'</option>';
    
    var SubCategoryAddlist="";
    if($serviceDataIndex.find("parentCatName").text())
    {
        SubCategoryAddlist='<select id="dir_Subcatid" onchange="dirSubCatIdChange()" data-role="none" disabled><option value="'+$serviceDataIndex.find("parentCatId").text()+'" data-role="none">'+$serviceDataIndex.find("parentCatName").text()+'</option></select><br><br>';
    }
    
    var addListing='<header class="hyper-local header-main "><a class="hyper-back" href="#" onclick="onBackKeyDown();"></a> <h1>'+add_update_listing_dir+'</h1></header><section class="hyper-local categorys-page"><div class="scroller-on"><ul class="add-listing"><li>'+SubCategoryAddlist+'<select id="dir_catid" onchange="dirCatIdChange()" data-role="none" disabled> '+optionHtmlStr +'</select>\
    </li>\
    <li><input type="text" placeholder="'+heading_dir+' *" id="hedingSL" value="'+header+'" data-role="none" readonly></li>\
    <li><textarea placeholder="'+summary_dir+' *" id="summarySL" data-role="none">'+summary+'</textarea></li>\
    <li><textarea placeholder="'+body_dir+' *" id="bodySL" data-role="none">'+detail+'</textarea></li>\
    <li>';
    
    
    addListing+='<div class="listing-tabs"><a href="#" class="images active"  onclick="changeTabDirUploadNew(this.id)" id="imageTabDir">'+images_dir+'</a><a href="#" class="videos" onclick="changeTabDirUploadNew(this.id)" id="videoTabDir" >'+video_dir+'</a><a href="#" class="audios" onclick="changeTabDirUploadNew(this.id)" id="audioTabDir" >'+audio_dir+'</a></div>\
    <div class="listing-tabs-view" id="imageTabDirVw">\
    <h3>'+upload_image_dir+'</h3>';
    imageArraySubmitList=serviceMediaUrl.split(",");
    var serviceImageArray = serviceMediaUrl.split(",");
    var count=-1;
    if(serviceImageArray.length>0)
    {
        for(var i=0;i<serviceImageArray.length;i++)
        {
            
            if(i==0)
            {
                addListing+='<ul id="ulSL'+i+'" >\
                <li>\
                <p>'+click_to_upload_image_dir+'</p>\
                <img src="'+serviceImageArray[i]+'" id="imageSL'+i+'" onclick="selectPhotoDir('+i+');">\
                </li>\
                </ul>';
                sessionStorage.setItem('imageData',serviceImageArray[i]);
            }
            else
            {
                addListing+='<ul id="ulSL'+i+'" >\
                <li>\
                <p>'+click_to_upload_image_dir+'</p>\
                <img src="'+serviceImageArray[i]+'" id="imageSL'+i+'" onclick="selectPhotoDir('+i+');">\
                <a href="#"  onclick="removeImageData('+i+');" >X</a>\
                </li>\
                </ul>';
            }
            count++;
        }
    }
    
    
    var youtubeUrl=$serviceDataIndex.find("youtubeUrl").text();
    var rssFeedUrl=$serviceDataIndex.find("rssFeedUrl").text();
    var radioPlsUrl=$serviceDataIndex.find("radioPlsUrl").text();
    var customTrackUrl=$serviceDataIndex.find("customTrackUrl").text();
    var customTrackName=$serviceDataIndex.find("customTrackName").text();
    var customTrackDescription=$serviceDataIndex.find("customTrackDescription").text();
    
    
    addListing+='  <div class="add-view" id="imgId11">\
    <span>'+instruction_recommended_image_dir+'</span>\
    <a href="#" class="add-more" onclick="addMoreClick('+count+')">'+add_more_dir+'</a>\
    </div> </div><div class="listing-tabs-view" style="display:none" id="videoTabDirVw">\
    <h3>'+add_a_video_dir+'</h3>\
    <input type="text" placeholder="'+enter_youtube_url_dir+'" id="youtubeUrl" value="'+youtubeUrl+'">\
    </div>\
    <div class="listing-tabs-view" style="display:none" id="audioTabDirVw">\
    <h3>'+add_an_audio_playlist_from_dir+'</h3>\
    <div class="providers">\
    <a class="provider" onclick="setAudioMediaType(this.id)" id="soundrss" ><img border="0" src="images/rss.png"><div>'+Media_RSS+'</div></a>\
    <a class="provider" id="rssradio" onclick="setAudioMediaType(this.id)" ><img border="0" src="images/custom-radio.png"> <div>'+Radio_PLS+'</div></a>\
    <a class="provider" onclick="setAudioMediaType(this.id)" id="customlist"><img border="0" src="images/custom.png"><div>'+CustomDir+'</div></a>\
    </div>\
    <input type="text" data-role="none"  placeholder="'+placeholder_soundrssData_services+'" id="soundrssData" style="display:block" value="'+rssFeedUrl+'">\
    <input type="text" data-role="none"  placeholder="'+placeholder_rssradioData_services+'" id="rssradioData" style="display:none" value="'+radioPlsUrl+'">\
    <input type="text" data-role="none"  placeholder="'+placeholder_customlistData_services+'" id="customlistData" style="display:none" value="'+customTrackUrl+'">\
    <input type="text" data-role="none"  placeholder="'+placeholder_customTrackNameData_services+'" id="customTrackNameData" style="display:none" value="'+customTrackName+'">\
    <input type="text" data-role="none"  placeholder="'+placeholder_customTrackDescriptionData_services+'" id="customTrackDescriptionData" style="display:none" value="'+customTrackDescription+'">\
    </div>\
    </li>';
    
    
    
    addListing+='<ul id="multipleUrlField">';
    for(var i=0;i<externalLink.length;i++)
    {
        if(i==0)
        {
            addListing+=' <li class="email-link"><input type="text" placeholder="'+url_dir+'" id="urlData'+i+'"  value="'+externalLink[i]+'"><a href="#" class="add-more2" id="addDataUrl"  onclick="addMoreClickField(\''+i+'\',1)">'+add_more_dir+'</a></li>';
        }
        else
        {
            addListing+='<li class="email-link" id="urlliId'+i+'"><input type="text" placeholder="'+url_dir+'" id="urlData'
            +i+'"  value="'+externalLink[i]+'"><a href="#" class="close-btn" id="deleteurl'+i+'" onclick="deleteField('+i+',1)">X</a><a href="#" class="add-more2" id="addDataUrl"  onclick="addMoreClickField('+i+',1)">'+add_more_dir+'</a></li>';
            
        }
        sessionStorage.setItem("addurlCount",i);
    }
    
    
    addListing+='</ul> <ul id="multipleEmailField">';
    
    for(var i=0;i<serEmailFull.length;i++)
    {
        if(i==0)
        {
            addListing+='<li class="email-link"><input type="text" data-role="none" placeholder="'+email_dir+'"  id="emailData'+i+'" value="'+serEmailFull[i]+'" ><a href="#" class="add-more2" onclick="addMoreClickField(\''+i+'\',2)" id="addDataEmail">'+add_more_dir+'</a></li>';
        }
        else
        {
            addListing+='<li class="email-link" id="emailliId'+i+'"><input type="text" data-role="none" placeholder="'+email_dir+'" id="emailData'
            +i+'" value="'+serEmailFull[i]+'"><a href="#" class="close-btn" id="deleteEmail'+i+'" onclick="deleteField('+i+',2)">X</a><a href="#" class="add-more2" id="addDataEmail"  onclick="addMoreClickField('+i+',2)">'+add_more_dir+'</a></li>';
        }
        
        sessionStorage.setItem("addEmailCount",i);
        
    }
    
    addListing+='</ul><ul id="multiplepNumField">';
    for(var i=0;i<callLink.length;i++)
    {
        if(i==0)
        {
            addListing+='<li class="email-link"><input type="text" placeholder="'+Enter_your_phone_number+'" id="callData'+i+'" value="'+callLink[i]+'"><a href="#" class="add-more2" id="addDataCall" onclick="addMoreClickField(\''+i+'\',3)">'+add_more_dir+'</a></li>';
        }
        else
        {
            addListing+='<li class="email-link" id="pNumliId'+i+'"><input type="text" placeholder="'+Enter_your_phone_number+'" id="callData'
            +i+'"  value="'+callLink[i]+'"><a href="#" class="close-btn" id="deletecall'+i+'" onclick="deleteField('+i+',3)">X</a><a href="#" class="add-more2" id="addDataCall"  onclick="addMoreClickField('+i+',3)">'+add_more_dir+'</a></li>';
        }
        
        sessionStorage.setItem("addCallCount",i);
    }
    
    var mapCheck='<span class="map-checkbox"><input type="checkbox" id="chkSL" name="chkSL" >'+Launch_map_in_application+'</span>';
    if(mapThirdParty.trim()=="1")
    {
    mapCheck='<span class="map-checkbox"><input type="checkbox" id="chkSL" name="chkSL" checked="checked"  >'+Launch_map_in_application+'</span>';
    }

    addListing+='</ul>\
    <li>\
    <textarea placeholder="'+address_dir+' *" id="addSL" data-role="none">'+serAddressFull+'</textarea>'+mapCheck+'</li>\
    <textarea class="add-listing2" placeholder="Latitude, Longitude " id="addSLLatLong" data-role="none" style="margin: 0;height: 40px;padding: 0;display:none"></textarea>\
	<a class="hyper-sent-btn icon-location-2" onclick="getMap()" style="margin: 0;width: 40px;padding: 0;text-align: center;margin: 6px 5px 0 0;float: right;min-width: 30px;padding: 4px; display:none"> </a>\
    </ul>\
    <div class="hyper-gray-wrapper update-btns">\
    <button class="hyper-sent-btn" onclick="addListingClick(\'update\',\''+listId+'\')">'+add_update_listing_dir+'</button><button class="hyper-sent-btn" onclick="addListingClick(\'delete\',\''+listId+'\')">'+delete_listing+'</button></div></div></section>';
    
    navClose();
    appendHtml(addListing,2,2);
    resetDirectoryHeader();
    
      
    
}
function reqRecieve()
{
    if((localStorage.getItem("isLogin")==null)||(localStorage.getItem("isLogin")=='null'))
    {
        loginService();
    }
    else
    {
        var reqRecieve='<header class="hyper-local header-main">\
         <a class="hyper-back" href="#" onclick="onBackKeyDown();"></a>\
        <h1><small>Requests Received</small></h1>\
        </header>\
        <section class="hyper-local">\
        <div class="hyper-notifications">\
        <div class="notification">\
        <img src="images/hyper-local/provider-pic.jpg">\
        <h2>Test Name</h2>\
        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry...</p>\
        <a href="#" class="accept">Accept</a>\
        <a href="#" class="cancel">Cancel</a></a>\
        </div>\
        <div class="notification">\
        <img src="images/hyper-local/provider-pic.jpg">\
        <h2>Test Name</h2>\
        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry...</p>\
        <a href="#" class="accept">Accept</a>\
        <a href="#" class="cancel">Cancel</a></a>\
        </div>\
        <div class="notification">\
        <img src="images/hyper-local/provider-pic.jpg">\
        <h2>Test Name</h2>\
        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry...</p>\
        <a href="#" class="accept">Accept</a>\
        <a href="#" class="cancel">Cancel</a></a>\
        </div>\
        <div class="notification">\
        <img src="images/hyper-local/provider-pic.jpg">\
        <h2>Test Name</h2>\
        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry...</p>\
        <a href="#" class="accept">Accept</a>\
        <a href="#" class="cancel">Cancel</a></a>\
        </div>\
        <div class="notification">\
        <img src="images/hyper-local/provider-pic.jpg">\
        <h2>Test Name</h2>\
        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry...</p>\
        <a href="#" class="accept">Accept</a>\
        <a href="#" class="cancel">Cancel</a></a>\
        </div>\
        <div class="notification">\
        <img src="images/hyper-local/provider-pic.jpg">\
        <h2>Test Name</h2>\
        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry...</p>\
        <a href="#" class="accept">Accept</a>\
        <a href="#" class="cancel">Cancel</a></a>\
        </div>\
        <div class="notification">\
        <img src="images/hyper-local/provider-pic.jpg">\
        <h2>Test Name</h2>\
        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry...</p>\
        <a href="#" class="accept">Accept</a>\
        <a href="#" class="cancel">Cancel</a></a>\
        </div>\
        </div>\
        </section>';
        navClose();
        appendHtml(reqRecieve,2,2);
		resetDirectoryHeader();
    }
}
function menuShow()
{
     menuAppend();
    $(".hyperlocal-menu").css("top", "-100%").show().animate({"top":"0"}, "fast");

    return false;
}
function navClose(){

    $(".hyperlocal-menu").css("top", "0").show().animate({"top":"-100%"}, "fast", function(){
                                                         $(this).hide();
                                                         })
    return false;
}

function getDirectoryCat(pageNo)
{
    $('.appypie-loader').show();
    var pageCount=1;
	var pageNo=1;
 /*   if(pageNo)
    {
        pageCount=pageNo;
    }
   */ 
    if(checkNetworkConnection('true'))
    {
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/directory-soap#getCategoryListXml";
        console.log("wsUrl---"+wsUrl);
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getCategoryListXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/directory-soap#getCategoryListXml\"><appId>'+localStorage.getItem("applicationID")+'</appId><dirPageId>'+sessionStorage.getItem('dirPageId')+'</dirPageId><count>3000</count><pageNo>'+pageCount+'</pageNo></getCategoryListXml></soap:Body></soap:Envelope>';
        console.log("getDirectoryCat()  -->soapRequest --->"+soapRequest);
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               cache: false,
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
               var returnData=$(req.responseText).find("return").text();
               
               console.log("returnData --->"+returnData);
               if(returnData != "No Category available")
               {
               displayDirCat(returnData,pageCount);
               dirXmlData=returnData;
               requestedFileSystem.root.getFile('dir_cat_'+sessionStorage.getItem('dirPageId')+'_pageNo'+pageCount+'.xml', {create: true, exclusive: false}, gotFileEntryDir, fail);
               }
               else
               {
               $('#showMoreButtonDirCat').remove();
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               
               },
               error: function(response, textStatus, errorThrown)
               {
               console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
        
    }
    else
    {
        $.ajax({
               type: "POST",
               url: sessionStorage.getItem('xmlPath')+'dir_cat_'+sessionStorage.getItem('dirPageId')+'_pageNo'+pageCount+'.xml',
               contentType: "text/xml",
               cache: false,
               dataType: "text",
               success: function(data, status, req)
               {
               var returnData=req.responseText;
               displayDirCat(returnData,pageCount);
               },
               error: function(response, textStatus, errorThrown)
               {
               console.log(response);
               checkNetworkConnection();
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
    }
}

function gotFileEntryDir(fileEntry)
{
    fileEntry.createWriter(gotFileWriterDir, fail);
}

function gotFileWriterDir(writer)
{
    writer.onwriteend = function(evt) {
        writer.onwriteend = function(evt) {
            writer.seek(4);
            writer.write(dirXmlData);
            writer.onwriteend = function(evt){
            }
        };
    };
    writer.write(dirXmlData);
}


function displayDirCat(data,pageCount)
{





    console.log("displayDirCat() starts  pageCount--->"+pageCount);
    var xmlData=jQuery.parseXML(data);
    xmlDataforFilter=xmlData;
    
     console.log("displayDirCat() starts  xmlData--->"+data);
    $serviceDataIndex=$(xmlData);
    var i=0;
    var serviceslist='';
    var totalCount=$serviceDataIndex.find('totalCat').text();
    var totalCatCount=(500 * parseFloat(pageCount));
    
	
	    var serivceIsSearch="";
    if(sessionStorage.getItem("serivceIsSearch")=="true")
    {
   serivceIsSearch='<div class="search-view"><input id="txtSearch" data-role="none" type="search" placeholder="'+enter_your_search_here_dir+'" onkeyup="txtSearchFunction(event, this)" class="on"><input id="btnSearch" data-role="none" type="button"></div>';
    }
	
	getLocalCords();
	 var AppendPageNo=2;
	 var serivceisLocationSearch="";
    var headerSearch='<header class="hyper-local header-main category-head ">';
    if(sessionStorage.getItem("serivceisLocationSearch")=="true")
    {
	 var citynull=localStorage.getItem("CurrentCity");
	 console.log("citynull 1  "+citynull);
     if( citynull =="undefined" || citynull =="null"  || citynull==null)
     {    
		console.log("citynull 2  "+citynull);
		localStorage.setItem("CurrentCity","");
     }
        serivceisLocationSearch='<span class="locate icon-location" onclick="searchLocationService()">'+localStorage.getItem("CurrentCity")+'</span>';
        headerSearch='<header class="hyper-local header-main category-head category-search">';
    }
//=====
    var searchcategory='';
    if(sessionStorage.getItem("serviceoldversion") == 0)
		
	  //var categoriesHTML = ''+headerSearch+'<a class="hyper-navs" href="#" onclick="menuShow()"></a> //<h1>'+serviceHeaderName+'</h1>'+serivceisLocationSearch+'<div class="right-nav"><a //class="header-hyper-filter"  href="#" onclick="filterDirectory(\'mainCat\',\''+AppendPageNo+'\')" //></a></div> </header><section class="hyper-local categorys-page">'+serivceIsSearch+'<div //class="scroller-on"><ul class="hyper-cat-listing">';
    
    {
     var oldAddClass= old_add_class_services;
     var searchcategory=search_category_services;
    }
    else
    {
        var oldAddClass='';
        
    }
  //=====sp
    
	
   var categoriesHTML = ''+headerSearch+'<a class="hyper-navs" href="#" onclick="menuShow()"></a> <h1>'+serviceHeaderName+'</h1>'+serivceisLocationSearch+'<div class="right-nav"><a class="header-hyper-filter"  href="#" onclick="filterDirectory(\'mainCat\',\''+AppendPageNo+'\')" ></a></div> </header><section class="hyper-local categorys-page '+searchcategory+' '+oldAddClass+'">'+serivceIsSearch+'<div class="scroller-on"><ul class="hyper-cat-listing">';
   
   
   
            $serviceDataIndex.find('catList').each(function()
                                           {
                                           var serviceSubHeader=$(this).find("categoryName").text();
										   sessionStorage.setItem("serviceSubHeader",serviceSubHeader);
                                           var serviceImage=$(this).find("catIcon").text();
                                                   
                                                   //=====
                                                   var serviceImagedefault = sessionStorage.getItem("dirDefaultImg");
                                                   console.log("serviceImagedefault=="+serviceImagedefault)
                                                   if( serviceImagedefault!=null)
                                                   {
                                                   if(sessionStorage.getItem("defaultImgEnable") == 1)
                                                   {
                                                   if(serviceImage=="http://angularml.pbodev.info/images/no-image.jpg")
                                                   {
                                                   serviceImage=serviceImagedefault;
                                                   }
                                                   if(serviceImage=="http://snappy.appypie.com/images/no-image.jpg")
                                                   {
                                                   serviceImage=serviceImagedefault;
                                                   }
                                                   }
                                                   
                                                   }
                                                   
                                                   //====
                                           var totalDirCat=$(this).find("totalDirCat").text();
                                                   if($(this).find("listTotal").text())
                                                   {
                                                   totalDirCat=$(this).find("listTotal").text();
                                                   
                                                   }
												   if(totalDirCat==0){
													   totalDirCat="";
												   }
                                           if(serviceImage.trim()=="")
                                           {
                                           serviceImage="images/logo.png";
                                           }
                                           var catId=$(this).find("catId").text();
                                           var listId="";
                                           if($(this).find("listId").text())
                                           {
                                            listId=$(this).find("listId").text();
                                 
                                           }
                                           var pageNo=2;
                                           var fromInternalSearch='No';
                                                 
                                           if(serviceSubHeader)
                                           {
                                                   
                                           var transparentImg = "images/hyper-local/transparent-bg.png";
                                                   if(sessionStorage.getItem("serviceoldversion") == 0)
                                                   {
                                                   transparentImg = serviceImage;
                                                   }
                                                   
                                           serviceslist += '<li onclick="createDirSubDetailService(\''+catId+'\',\''+AppendPageNo+'\',\''+fromInternalSearch+'\',\''+listId+'\')" style="background-image:url('+serviceImage+');"><img src="'+transparentImg+'" alt=""/ ><div class="overlay"></div><span class="cat-heading-text" >'+serviceSubHeader+'<span class="hyper-catNo" id="nearByCount'+catId+'">'+totalDirCat+'</span></span><span class="icon-dirctroy-more"></span></li>';
                                           
                                           }
                                        
                                           sessionStorage.setItem("serviceSubHeader"+catId,serviceSubHeader);
                                           i++;
                                           });
    
      serviceslist += ' </ul></div></section>';

     appendHtml(categoriesHTML+serviceslist,'',1);
	
	  resetDirectoryHeader();
    
    //=====
    if(sessionStorage.getItem("serviceoldversion") == 0)
    {
        $(".cat-heading-text, .icon-dirctroy-more").css("color", sessionStorage.getItem('pageTextColor'));
    }
	 if(sessionStorage.getItem("serivceIsSearch")=="false")
    {
	console.log("removing header"+sessionStorage.getItem("serivceIsSearch"));
	$(".hyper-local.categorys-page").removeClass("search-category");
    }
	  setTimeout(function(){
    $("[data-role='header']").remove();
    $("[data-role='header']").toolbar('destroy');
    $.mobile.resetActivePageHeight();
    $('#page2').attr("style","padding:0px !important");
	dynamicCSS('hyperlocal');
               sessionStorage.setItem('hideHeaderBar','false');
               localStorage.setItem("hidebootomforce",'true');
	console.log("removing header"+sessionStorage.getItem("serivceIsSearch"));   
	
              },100);
    //=======
    
    if(sessionStorage.getItem("serviceoldversion") == 0)
    {
        
        if(sessionStorage.getItem("defaultImgEnable") == 1)
        {
            
        }
        else
        {
            $(".hyper-cat-listing li").each(function(){
                try{
                    var thisAttr = $(this).attr("style");
                    if(thisAttr)
                    {
                    if(thisAttr.indexOf("no-image") != -1)
                    {
                    $(this).addClass("no-image");
                    }
                    }
                    }catch(err){
                    console.log("Listing Image ERROR: "+err);
                    }
                    });
        }
        
        $(".cat-heading-text, .icon-dirctroy-more").css("color", sessionStorage.getItem('pageTextColor'));
        
        
        
    }
    
    //=====
    
    
    
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

    $('#page2').attr("style","padding:0px !important");
    $('#page3').attr("style","padding:0px !important");
    $('#page4').attr("style","padding:0px !important");
    $('#page5').attr("style","padding:0px !important");
    
    console.log("-me"+categoriesHTML+serviceslist);
    //$(".hyper-local").parents(".ui-page").addClass("overflowNone").find("div:eq(0)").css("overflow", "hidden")
    
    $("#btnSearch").click(function(){
                          if($("#txtSearch").is(".on"))
                          {
                          $("#txtSearch").removeClass("on").focus();
                          }
                          else
                          {
                          $("#txtSearch").addClass("on").blur();
                          }
                          
                          });
    
}

function openMapWithMultipleAnnotation()
{
   // window.location=openStr;
	toaster.showDirectoryOnMap(multipleLocStr);
}


function createDirSubDetailService(catId,AppendPageNo,fromInternalSearch,listId)
{
    
    var  newhead = $("#nearByCount" + catId).parent().clone();
    newhead.find(".hyper-catNo").remove();
    sessionStorage.setItem("lastHead",newhead.text());
    //=======
    
    if(!fromInternalSearch)
    {
        fromInternalSearch=form_internal_search_services;
    }
    if(!listId)
    {
        listId="";
    }
    
    $('.appypie-loader').show();
    var pageCount=1;
    
    if(parseInt(AppendPageNo)==3)
    {
        
        if(fromInternalSearch=="internalSearch")
        {
            //  sessionStorage.setItem('dirCatId',catId);
            $.ajax({
                   type: "POST",
                   url: sessionStorage.getItem('xmlPath')+'dir_Sub_cat_detail_'+sessionStorage.getItem('dirCatId')+'_pageNo'+pageCount+'.xml',
                   contentType: "text/xml",
                   cache: false,
                   dataType: "text",
                   success: function(data, status, req)
                   {
                   var returnData=req.responseText;
                   console.log("internalSearch==="+req.responseText);
                   createDirSubDetailDisplay(returnData,AppendPageNo);
                   },
                   error: function(response, textStatus, errorThrown)
                   {
                   console.log("internalSearch==="+response);
                   checkNetworkConnection();
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   }
                   });
            
            
        }
        else
        {
            
            localStorage.setItem("isFrom","");
            if(checkNetworkConnection('true'))
            {
                var searchListId="";
                if(listId.length>0)
                {
                    searchListId= listId.split("|");
                }
                
                var emailUser="";
                if((localStorage.getItem("isLogin")==null)||(localStorage.getItem("isLogin")=='null'))
                {
                }
                else
                {
                    emailUser=localStorage.getItem("isLogin");
                }
                
                var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/directory-soap#getDirectoryListWithSubCategoryXmlNew";
                var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getDirectoryListWithSubCategoryXmlNew xmlns=\"http://'+localStorage.getItem("reseller")+'/services/directory-soap#getDirectoryListWithSubCategoryXmlNew\"><appId>'+localStorage.getItem("applicationID")+'</appId><dirPageId>'+sessionStorage.getItem('dirPageId')+'</dirPageId><catId>'+catId+'</catId><count>3000</count><pageNo>'+pageCount+'</pageNo><searchText></searchText><searchListId>'+searchListId+'</searchListId><emailUser>'+emailUser+'</emailUser></getDirectoryListWithSubCategoryXmlNew></soap:Body></soap:Envelope>';
                console.log("getDirectoryListWithSubCategoryXmlNew -->soapRequest --->"+soapRequest);
                $.ajax({
                       type: "POST",
                       url: wsUrl,
                       contentType: "text/xml",
                       cache: false,
                       dataType: "text",
                       data: soapRequest,
                       success: function(data, status, req)
                       {
                       var returnData=$(req.responseText).find("return").text();
                       console.log("getDirectoryListWithSubCategoryXml12--->"+returnData);
                       if($(returnData).find('record').text())
                       {
                         sessionStorage.setItem('dirCatId',catId);
                       createDirSubDetailDisplay(returnData,AppendPageNo);
                       dirXmlData=returnData;
                       requestedFileSystem.root.getFile('dir_Sub_cat_detail_'+sessionStorage.getItem('dirCatId')+'_pageNo'+pageCount+'.xml', {create: true, exclusive: false}, gotFileEntryDir, fail);
                       }
                       else if($(returnData).find('subCatrecord').text())
                       {
                          sessionStorage.setItem('dirCatId',catId);
                       createDirSubDetailDisplay(returnData,AppendPageNo);
                       dirXmlData=returnData;
                       requestedFileSystem.root.getFile('dir_Sub_cat_detail_'+sessionStorage.getItem('dirCatId')+'_pageNo'+pageCount+'.xml', {create: true, exclusive: false}, gotFileEntryDir, fail);
                       }
                       else
                       {
                       
                       navigator.notification.alert(
                                                    no_list_in_this_category_dir,
                                                    alertDismissed,
                                                    alert_dir,
                                                    ok_dir);
                       setTimeout(function(){$('.appypie-loader').hide();},1000);
                       }
                       
                       },
                       error: function(response, textStatus, errorThrown)
                       {
                       console.log("in error --->() "+response.responseText);
                       setTimeout(function(){$('.appypie-loader').hide();},1000);
                       }
                       });
                
            }
            else
            {
                //  sessionStorage.setItem('dirCatId',catId);
                $.ajax({
                       type: "POST",
                       url: sessionStorage.getItem('xmlPath')+'dir_Sub_cat_detail_'+sessionStorage.getItem('dirCatId')+'_pageNo'+pageCount+'.xml',
                       contentType: "text/xml",
                       cache: false,
                       dataType: "text",
                       success: function(data, status, req)
                       {
                       var returnData=req.responseText;
                       createDirSubDetailDisplay(returnData,AppendPageNo);
                       },
                       error: function(response, textStatus, errorThrown)
                       {
                       console.log(response);
                       checkNetworkConnection();
                       setTimeout(function(){$('.appypie-loader').hide();},1000);
                       }
                       });
            }
        }
    }
    else
    {
        
        
        if(fromInternalSearch=="internalSearch")
        {
            sessionStorage.setItem('dirCatId',catId);
            $.ajax({
                   type: "POST",
                   url: sessionStorage.getItem('xmlPath')+'dir_cat_detail_'+sessionStorage.getItem('dirCatId')+'_pageNo'+pageCount+'.xml',
                   contentType: "text/xml",
                   cache: false,
                   dataType: "text",
                   success: function(data, status, req)
                   {
                   var returnData=req.responseText;
                   console.log("internalSearch==="+req.responseText);
                   createDirSubDetailDisplay(returnData,AppendPageNo);
                   },
                   error: function(response, textStatus, errorThrown)
                   {
                   console.log("internalSearch==="+response);
                   checkNetworkConnection();
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   }
                   });
            
            
        }
        else
        {
            
            localStorage.setItem("isFrom","");
            if(checkNetworkConnection('true'))
            {
                var searchListId="";
                if(listId.length>0)
                {
                    searchListId= listId.split("|");
                }
                
                var emailUser="";
                if((localStorage.getItem("isLogin")==null)||(localStorage.getItem("isLogin")=='null'))
                {
                }
                else
                {
                    emailUser=localStorage.getItem("isLogin");
                }
                
                var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/directory-soap#getDirectoryListWithSubCategoryXmlNew";
                var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getDirectoryListWithSubCategoryXmlNew xmlns=\"http://'+localStorage.getItem("reseller")+'/services/directory-soap#getDirectoryListWithSubCategoryXmlNew\"><appId>'+localStorage.getItem("applicationID")+'</appId><dirPageId>'+sessionStorage.getItem('dirPageId')+'</dirPageId><catId>'+catId+'</catId><count>3000</count><pageNo>'+pageCount+'</pageNo><searchText></searchText><searchListId>'+searchListId+'</searchListId><emailUser>'+emailUser+'</emailUser></getDirectoryListWithSubCategoryXmlNew></soap:Body></soap:Envelope>';
                console.log("getDirectoryListWithSubCategoryXmlNew -->soapRequest --->"+soapRequest);
                $.ajax({
                       type: "POST",
                       url: wsUrl,
                       contentType: "text/xml",
                       cache: false,
                       dataType: "text",
                       data: soapRequest,
                       success: function(data, status, req)
                       {
                       var returnData=$(req.responseText).find("return").text();
                       console.log("getDirectoryListWithSubCategoryXmlNew12--->"+returnData);
                       if($(returnData).find('record').text())
                       {
                       sessionStorage.setItem('dirCatId',catId);
                       createDirSubDetailDisplay(returnData,AppendPageNo);
                       dirXmlData=returnData;
                       requestedFileSystem.root.getFile('dir_cat_detail_'+sessionStorage.getItem('dirCatId')+'_pageNo'+pageCount+'.xml', {create: true, exclusive: false}, gotFileEntryDir, fail);
                       }
                       else if($(returnData).find('subCatrecord').text())
                       {
                       sessionStorage.setItem('dirCatId',catId);
                       createDirSubDetailDisplay(returnData,AppendPageNo);
                       dirXmlData=returnData;
                       requestedFileSystem.root.getFile('dir_cat_detail_'+sessionStorage.getItem('dirCatId')+'_pageNo'+pageCount+'.xml', {create: true, exclusive: false}, gotFileEntryDir, fail);
                       }
                       else
                       {
                       
                       navigator.notification.alert(
                                                    no_list_in_this_category_dir,
                                                    alertDismissed,
                                                    alert_dir,
                                                    ok_dir);
                       setTimeout(function(){$('.appypie-loader').hide();},1000);
                       }
                       
                       },
                       error: function(response, textStatus, errorThrown)
                       {
                       console.log("in error --->() "+response.responseText);
                       setTimeout(function(){$('.appypie-loader').hide();},1000);
                       }
                       });
                
            }
            else
            {
                sessionStorage.setItem('dirCatId',catId);
                $.ajax({
                       type: "POST",
                       url: sessionStorage.getItem('xmlPath')+'dir_cat_detail_'+sessionStorage.getItem('dirCatId')+'_pageNo'+pageCount+'.xml',
                       contentType: "text/xml",
                       cache: false,
                       dataType: "text",
                       success: function(data, status, req)
                       {
                       var returnData=req.responseText;
                       createDirSubDetailDisplay(returnData,AppendPageNo);
                       },
                       error: function(response, textStatus, errorThrown)
                       {
                       console.log(response);
                       checkNetworkConnection();
                       setTimeout(function(){$('.appypie-loader').hide();},1000);
                       }
                       });
            }
        }
    }
}


function replaceAll(find, replace, str) {
    return str.replace(new RegExp(find, 'g'), replace);
}

function createDirSubDetailDisplay(returnData,AppendPageNo,count,isFrom)
{
    if(filterIdArray.length>0)
    {
        filterIdArrayStored=filterIdArray.slice();
        filterIdArray=new Array();
    }
    
    sessionStorage.setItem("AppendPageNo",AppendPageNo);
    var subcatCount=0;
    var pageCount=1;
    localStorage.setItem("spageCount",pageCount);
    var isFromDirectory=isfrom_drictory_services;
    var isFromSubCatList=false;
    var i=0;
    var xmlData=jQuery.parseXML(returnData);
    var serviceDataIndex=xmlData;
    console.log("returnData -===-->"+returnData);
    var pageIconColor= sessionStorage.getItem('pageIconColor');
    var serviceslist ='';
    var serviceslist2="";
    var j=0;
    var x=0;
    var searchj=0;
    var searchx=0;
    var noMatchFound=0;
    var intrnalSearchCount=0;
    var latArr=new Array();
    var longArr=new Array();
    var indexArr=new Array();
    var addArr=new Array();
	sortArray.length = 0
    var isFrom=localStorage.getItem("isFrom");
    $(serviceDataIndex).find("subCatrecord").each(function()
                                                  {
                                                  isFromSubCatList=true;
                                                  var serviceSubHeader=$(this).find("categoryName").text();
												  sessionStorage.setItem("serviceSubHeader",serviceSubHeader);
                                                  var serviceImage=$(this).find("catIcon").text();
                                                  
                                                  //-=====
                                                  var serviceImagedefault = sessionStorage.getItem("dirDefaultImg");
                                                  console.log("serviceImagedefault=="+serviceImagedefault)
                                                  if( serviceImagedefault!=null)
                                                  {  if(sessionStorage.getItem("defaultImgEnable") == 1)
                                                  {
                                                  if(serviceImage=="http://angularml.pbodev.info/images/no-image.jpg")
                                                  {
                                                  serviceImage=serviceImagedefault;
                                                  }
                                                  if(serviceImage=="http://snappy.appypie.com/images/no-image.jpg")
                                                  {
                                                  serviceImage=serviceImagedefault;
                                                  }
                                                  }
                                                  }
                                                  
                                                  //========
                                                  
                                                  var catId=$(this).find("catId").text();
                                                  var totalDirCat=$(this).find("totalList").text();
                                                  if(totalDirCat=="")
                                                  {
                                                  //totalDirCat=0;
												  totalDirCat="";
                                                  }
                                                  
                                                  if(serviceSubHeader)
                                                  {
                                                  if(serviceImage.length > 1 && serviceImage != 'http://apps.appypie.com/media/services/')
                                                  {
                                                  }
                                                  else
                                                  {
                                                  serviceImage="images/logo.png";
                                                  }
                                                  }
                                                  
                                                  if(isFrom=="internalSearch")
                                                  {
                                                  
                                                  var catDetails=$(this).find("catDetails").text();
                                                  console.log("catDetails======"+catDetails);
                                                  var obj = JSON.parse(catDetails);
                                                  var listIDArr=new Array();
                                                  var latArr1=new Array();
                                                  var longArr1=new Array();
                                                  var ratingArr1=new Array();
                                                  
                                                  for(var k=0;k<obj.length;k++)
                                                  {
                                                  
                                                  var arrayList=obj[k].split(",");
                                                  listIDArr.push(arrayList[0]);
                                                  latArr1.push(arrayList[1]);
                                                  longArr1.push(arrayList[2]);
                                                  ratingArr1.push(arrayList[3]);
                                                  
                                                  }
                                                  console.log("listIDArr======"+listIDArr);
                                                  console.log("latArr1======"+latArr1);
                                                  
                                                  
                                                  var maxDist=sessionStorage.getItem("maxDist");
                                                  var distanceUnits=sessionStorage.getItem("distanceUnits");
                                                  console.log("distanceUnits====="+distanceUnits);
                                                  
                                                  if(distanceUnits=='null'||distanceUnits==null)
                                                  {
													  distanceUnits=distance_unit_mi_services;

                                                  }
                                                  
                                                  
                                                  var distanceUnitsMain=distance_unit_k_services;
                                                  if(distanceUnits=="MI")
                                                  {
                                                  distanceUnitsMain=distance_unit_m_services;
                                                  }
                                                  
                                                  
                                                  
                                                  if(maxDist.length>1)
                                                  {
                                                  console.log("maxDist====="+distanceUnits);
                                                  console.log("latArr1====="+latArr1);
                                                  var totalCount=0;
                                                  for(var n=0;n<latArr1.length;n++)
                                                  {
                                                  var distanceCalc=distanceFromCurrentLocation(latArr1[n],longArr1[n],distanceUnitsMain);
                                                  
                                                  
                                                  console.log("distanceCalc======"+distanceCalc);
                                                  console.log("maxDist11======"+latArr1.length);
                                                  console.log("maxDist11======"+listIDArr.length);
                                                  if(parseFloat(distanceCalc)<=parseFloat(maxDist))
                                                  {
                                                  totalCount++;
                                                  console.log("listIDArr[n]======"+listIDArr[n]);
                                                  
                                                  // filterSublistArray.push(listIDArr[n]);
                                                  }
                                                  }
                                                  
                                                  
                                                  if(totalCount>0)
                                                  {
                                                  
                                                  var transparentImg = "images/hyper-local/transparent-bg.png";
                                                  if(sessionStorage.getItem("serviceoldversion") == 0)
                                                  {
                                                  transparentImg = serviceImage;
                                                  }
                                                  
                                                  
                                                  
                                                  subcatCount=totalCount;
                                                  serviceslist += '<li onclick="createDirSubDetailService(\''+catId+'\',\'3\',\'internalSearch\')" style="background-image:url('+serviceImage+');"><img src="'+transparentImg+'" alt=""/ ><div class="overlay"></div><span class="cat-heading-text">'+serviceSubHeader+'<span class="hyper-catNo" id="nearByCount'+catId+'">'+totalCount+'</span></span><span class="icon-dirctroy-more"></span></li>';
                                                  }
                                                  
                                                  
                                                  }
                                                  else
                                                  {
                                                  
                                                  var ratingFromSearch=sessionStorage.getItem("ratingFromSearch");
                                                  var totalCount=0;
                                                  var  ratingFromSearchArray= ratingFromSearch.split(",");
                                                  for(var i=0;i<ratingFromSearchArray.length;i++)
                                                  {
                                                  for(var l=0;l<ratingArr1.length;l++)
                                                  {
                                                  if(parseFloat(ratingArr1[l])==parseFloat(ratingFromSearchArray[i]))
                                                  {
                                                  totalCount++;
                                                  //  filterSublistArray.push(listIDArr[l]);
                                                  }
                                                  
                                                  }
                                                  }
                                                  if(totalCount>0)
                                                  {
                                                  var transparentImg = "images/hyper-local/transparent-bg.png";
                                                  if(sessionStorage.getItem("serviceoldversion") == 0)
                                                  {
                                                  transparentImg = serviceImage;
                                                  }
                                                  
                                                  subcatCount=totalCount;
                                                  serviceslist += '<li onclick="createDirSubDetailService(\''+catId+'\',\'3\',\'internalSearch\'))" style="background-image:url('+serviceImage+');"><img src="'+transparentImg+'" alt=""/ ><div class="overlay"></div><span class="cat-heading-text">'+serviceSubHeader+'<span class="hyper-catNo" id="nearByCount'+catId+'">'+totalCount+'</span></span><span class="icon-dirctroy-more"></span></li>';
                                                  
                                                  }
                                                  
                                                  }
                                                  
                                                  }
                                                  else
                                                  {
                                                  var transparentImg = "images/hyper-local/transparent-bg.png";
                                                  if(sessionStorage.getItem("serviceoldversion") == 0)
                                                  {
                                                  transparentImg = serviceImage;
                                                  }
                                                  subcatCount=parseInt(totalDirCat);
                                                  serviceslist += '<li onclick="createDirSubDetailService(\''+catId+'\',\'3\')" style="background-image:url('+serviceImage+');" class="category-item-d"><img src="'+transparentImg+'" alt=""/ ><div class="overlay"></div><span class="cat-heading-text">'+serviceSubHeader+'<span class="hyper-catNo" id="nearByCount'+catId+'">'+totalDirCat+'</span></span><span class="icon-dirctroy-more"></span></li>';
                                                  
                                                  
                                                  }
                                                  sessionStorage.setItem("serviceSubHeader"+catId,serviceSubHeader);
                                                  i++;
                                                  });
    
    $(serviceDataIndex).find("record").each(function ()
                                            {
											sortArray[j]=new Array();
                                            sortArray[j][0]=j;
                                            var serviceHeader=$(this).find("header").text();
                                            var serviceDescription=$(this).find("summary").text();
                                            var serviceImage=$(this).find("image").text();
                                            var serviceImagedefault = sessionStorage.getItem("dirDefaultImg");
                                            console.log("serviceImagedefault=="+serviceImagedefault)
                                            if( serviceImagedefault!=null)
                                            {
                                            if(sessionStorage.getItem("defaultImgEnable") == 1)
                                            {
                                            if(serviceImage=="http://angularml.pbodev.info/images/no-image.jpg")
                                            {
                                            serviceImage=serviceImagedefault;
                                            }
                                            if(serviceImage=="http://snappy.appypie.com/images/no-image.jpg")
                                            {
                                            serviceImage=serviceImagedefault;
                                            }
                                            }
                                            }
                                            
                                            var serAddressFull=$(this).find("serAddressFull").text();
											serAddressFull=serAddressFull.replace(/\s+/g, ' ');
                                            var serlatitude=$(this).find("serlatitude").text();
                                            var serlongitude=$(this).find("serlongitude").text();
                                            var reviewRating=$(this).find("reviewRating").text();
                                            var listId=$(this).find("listId").text();
											
											var categoryId=sessionStorage.getItem('dirCatId');
                                            var dirPageId=sessionStorage.getItem('dirPageId');
											 if(serlatitude.trim()==""||serlongitude.trim()=="")
                                            {
                                             serlatitude="0";
                                             serlongitude="0";
                                            }
											
                                            
                                            serviceHeader=serviceHeader.replace(/\s+/g, " ");
                                            serviceDescription=serviceDescription.replace(/\s+/g, " ");
                                            
                                            var reviewRating=$(this).find("dirRating").text();
                                            var totalReview=$(this).find("totalReview").text();
                                            if(reviewRating=="")
                                            {
                                            reviewRating=0;
                                            totalReview=0;
                                            }
                                            else
                                            {
                                            reviewRating=parseInt(reviewRating);
                                            }
                                            
                                            
                                            var callNumber="";
                                            if($(this).find("call").text())
                                            {
                                            $(this).find("call").each(function (m)
                                                                      {
                                                                      if(m==0)
                                                                      {
                                                                      callNumber=$(this).find("callLink").text();
                                                                      }
                                                                      else
                                                                      
                                                                      {
                                                                      callNumber+=","+$(this).find("callLink").text();
                                                                      }
                                                                      })
                                            }
                                            
                                            var openMapWithThirdParty='0';
                                            
                                            if($serviceDataIndex.find("mapThirdParty").text())
                                            {
                                            openMapWithThirdParty=$serviceDataIndex.find("mapThirdParty").text();
                                             mapThirdPartyFlag=openMapWithThirdParty;                                            }
                                            var distanceUnits=sessionStorage.getItem("distanceUnits");
                                            console.log("distanceUnits====="+distanceUnits);
                                            
                                            
                                            var callDisp=rating_disp_block_services;
                                            if($(this).find("callLink").text()=="")
                                            {
                                            callDisp=call_disp_none_services;
                                            }
                                            
                                            var ratingDisp=call_disp_none_services;
                                            if(sessionStorage.getItem("reviewSetting")=="true")
                                            {
                                            ratingDisp=rating_disp_block_services;
                                            }
											//start change by dheeraj
											var listingShare=call_disp_none_services;
												
                                            if(sessionStorage.getItem("listingShare")=="true")
                                            {
                                            	
											listingShare=rating_disp_block_services;
                                            
                                            }
											//end change by dheeraj
                                            var ptagDisp=call_disp_none_services;
                                            if(parseFloat(serlatitude)!=0)
                                            {
                                            ptagDisp=rating_disp_block_services;
                                            }
                                            
                                            
                                            var checkinDisplay=call_disp_none_services;
                                            var dirMapDisplay=call_disp_none_services;
                                            if(sessionStorage.getItem("dirMapDisplay")=="true")
                                            {
                                            if(sessionStorage.getItem("checkonEnable")=="true")
                                            {
                                            if(parseFloat(serlatitude)!=0)
                                            {
                                            checkinDisplay=rating_disp_block_services;
                                            }
                                            }
                                            if(parseFloat(serlatitude)!=0)
                                            {
                                            dirMapDisplay=rating_disp_block_services;
                                            }
                                            }
                                            
                                            
                                            if(distanceUnits=='null'||distanceUnits==null)
                                            {
												 distanceUnits=distance_unit_mi_services;
                                         
                                            }
                                            
                                            
                                            var distanceUnitsMain=distance_unit_k_services;
                                            if(distanceUnits=="MI")
                                            {
                                            distanceUnitsMain=distance_unit_m_services;
                                            }
                                            
											 var distanceCalcWithUnit="";
                                            var distanceCalc=distanceFromCurrentLocation(serlatitude,serlongitude,distanceUnitsMain);
                                            
                                           /* if(distanceCalc==""||distanceCalc==null||distanceCalc=="NAN"||distanceCalc=="null")
                                            {
                                            ptagDisp='none';
                                            }
                                            else
                                            {
                                            distanceCalcWithUnit=distanceCalc +distanceUnits;
                                            }*/
											 var distanceCalcWithUnitCheck=distanceCalcWithUnit=distanceCalc +distanceUnits;
                                            if(distanceCalcWithUnitCheck=="NaNMI")
                                                                          {
                                                                          ptagDisp=call_disp_none_services;
                                                                          }
                                                                          else
                                                                          {
                                                                          distanceCalcWithUnit=distanceCalc +distanceUnits;
                                         console.log("distanceCalcWithUnit : "+distanceCalcWithUnit);
                                                                          }
											
											
											
                                         
                                             if(serlatitude==0)
                                            
                                            {
                                            sortArray[j][1]=9999;
                                            }
                                            else
                                            
                                            {
                                            sortArray[j][1]= distanceCalc;
                                            }
											
                                            if(isFrom=="internalSearch")
                                            {
                                            
                                            var maxDist=sessionStorage.getItem("maxDist");
                                            console.log("internalSearch");
                                            console.log("maxDist==="+maxDist);
                                            console.log("distanceCalc==="+distanceCalc);
                                            
                                            if(parseFloat(maxDist)>0)
                                            {
                                            if(parseFloat(distanceCalc)<=parseFloat(maxDist))
                                            {
                                            if(parseFloat(serlatitude)!=0)
                                            {
                                            
                                            
                                            filterIdArray.push(listId);
                                            
                                            latArr.push(serlatitude);
                                            longArr.push(serlongitude);
                                            indexArr.push(j);
                                            var encdAdd="";
                                            if(serAddressFull.length > 2)
                                            {
                                            encdAdd=encodeURIComponent(serAddressFull);
                                            addArr.push(encodeURIComponent(serviceHeader));
                                            }
                                            else
                                            {
                                            encdAdd=address_name_not_avail_services;
                                            addArr.push("Address name not available");
                                            }
                                            
                                            
                                            serviceDescription=replaceAll('&lt;br&gt;','<br>',serviceDescription);
                                            
                                            if(serviceImage.length>1 && (serviceImage != 'http://apps.appypie.com/media/services/' || serviceImage != 'http://snappy.appypie.com/media/services/' || serviceImage != 'http://'+localStorage.getItem("reseller")+'/media/services/'))
                                            {
                                            
                                            }
                                            else
                                            {
                                            
                                            serviceImage="images/logo.png";
                                            
                                            }
                                            
                                            var transparentImg = "images/hyper-local/transparent-bg.png";
                                            if(sessionStorage.getItem("serviceoldversion") == 0)
                                            {
                                            transparentImg = serviceImage;
                                            }
                                            //deepak
											var oldDisp =call_disp_none_services;
                                            if(sessionStorage.getItem("serviceoldversion") == 0)
                                            {
                                             oldDisp =rating_disp_block_services;
                                             transparentImg = serviceImage;
                                            }
                                            var padd="";
                                            if(sessionStorage.getItem("defaultImgEnable") == 0)
                                            {
                                            
                                              if(serviceImage=="http://snappy.appypie.com/images/no-image.jpg")
                                              {
												
                                                padd="padding:0 0 0 0px!important;";
                                              }
                                           
                                            }
											
											
                                            serviceslist2 +='<li  id="serviceinnerSubHeaderPage'+j+'" style="background-image:url('+serviceImage+');" class="listing-item"> <span class="hyper-catNamebox categoryName" onclick="createServiceDetail(\''+pageCount+'\',\''+j+'\',\''+x+'\',\''+intrnalSearchCount+'\');"> '+serviceHeader+'<p style="display:'+ptagDisp+'">'+distanceCalcWithUnit+'</p> </span><span class="hyper-addons"><a href="#" class="checkin-icon" onclick="checkinPage(\''+listId+'\',\''+categoryId+'\',\''+dirPageId+'\',\''+ serlatitude+'\',\''+ serlongitude +'\',\''+ serAddressFull+'\',\''+ serviceHeader+'\',\''+ openMapWithThirdParty +'\');" style="display:'+checkinDisplay+'" style="display:'+checkinDisplay+'"></a><a href="#" class="map-icon"  onclick="showServicePageMapNew(\''+serlatitude+'\',\''+serlongitude+'\',\''+j+'\',\''+serviceHeader+'\',\''+serviceHeader+'\',\''+pageCount+'\',\''+isFromDirectory+'\');" style="display:'+dirMapDisplay+'"></a><a href="#" class="call-icon" onclick="MakeCall(\''+callNumber+'\')" style="display:'+callDisp+'"></a><a href="#" class="share-icon"  onclick="shareDirectory(\''+serviceImage+'\',\''+serviceHeader+'\',\''+serviceDescription+'\');" style="display:'+listingShare+'"></a></a><a href="#" class="like-icon" onclick="showDataReview(\''+AppendPageNo+'\',\''+listId+'\')" style="display:'+ratingDisp+'">'+reviewRating+'<br>'+rating_dir+'</a></span><span onclick="createServiceDetail(\''+pageCount+'\',\''+j+'\',\''+x+'\',\''+intrnalSearchCount+'\');" class="hyper-listing-overlay"></span> <p class="paddingleft" style="display:'+oldDisp+';'+padd+'">'+serviceDescription+'</p> <img src="'+transparentImg+'" alt=""/ ></li>';
                                            intrnalSearchCount++;
                                            searchj=j;
                                            searchx=x;
											
                                            }
                                            }
                                            }
                                            else
                                            {
                                            
                                            var ratingFromSearch=sessionStorage.getItem("ratingFromSearch");
                                            console.log("ratingFromSearch==="+ratingFromSearch);
                                            console.log("reviewRating==="+reviewRating);
                                            
                                            
                                            var  ratingFromSearchArray= ratingFromSearch.split(",");
                                            
                                            var isRatingFind=false;
                                            for(var i=0;i<ratingFromSearchArray.length;i++)
                                            {
                                            if(parseFloat(reviewRating)==parseFloat(ratingFromSearchArray[i]))
                                            {
                                            isRatingFind=true;
                                            break;
                                            }
                                            
                                            }
                                            if(isRatingFind)
                                            {
                                            
                                            filterIdArray.push(listId);
                                            
                                            if(parseFloat(serlatitude)!=0)
                                            {
                                            latArr.push(serlatitude);
                                            longArr.push(serlongitude);
                                            
                                            indexArr.push(j);
                                            
                                            var encdAdd="";
                                            if(serAddressFull.length > 2)
                                            {
                                            encdAdd=encodeURIComponent(serAddressFull);
                                            addArr.push(encodeURIComponent(serviceHeader));
                                            }
                                            else
                                            {
                                            encdAdd=address_name_not_avail_services;
                                            addArr.push("Address name not available");
                                            }
                                            
                                            }
                                            
                                            
                                            serviceDescription=replaceAll('&lt;br&gt;','<br>',serviceDescription);
                                            
                                            if(serviceImage.length>1 && (serviceImage != 'http://apps.appypie.com/media/services/' || serviceImage != 'http://snappy.appypie.com/media/services/' || serviceImage != 'http://'+localStorage.getItem("reseller")+'/media/services/'))
                                            {
                                            
                                            }
                                            else
                                            {
                                            
                                            serviceImage="images/logo.png";
                                            
                                            }
                                            var transparentImg = "images/hyper-local/transparent-bg.png";
                                            if(sessionStorage.getItem("serviceoldversion") == 0)
                                            {
                                            transparentImg = serviceImage;
                                            }
											//deepak
                                            var oldDisp =call_disp_none_services;
                                            if(sessionStorage.getItem("serviceoldversion") == 0)
                                            {
                                             oldDisp =rating_disp_block_services;
                                             transparentImg = serviceImage;
                                            }
                                            var padd="";
                                            if(sessionStorage.getItem("defaultImgEnable") == 0)
                                            {
                                            
                                              if(serviceImage=="http://snappy.appypie.com/images/no-image.jpg")
                                              {
												
                                                padd="padding:0 0 0 0px!important;";
                                              }
                                           
                                            }
											
											
                                            serviceslist2 +='<li class="listing-item" id="serviceinnerSubHeaderPage'+j+'" style="background-image:url('+serviceImage+');"> <span class="hyper-catNamebox categoryName" onclick="createServiceDetail(\''+pageCount+'\',\''+j+'\',\''+x+'\',\''+intrnalSearchCount+'\');"> '+serviceHeader+'<p style="display:'+ptagDisp+'">'+distanceCalcWithUnit+'</p> </span><span class="hyper-addons"><a href="#" class="checkin-icon" onclick="checkinPage(\''+listId+'\',\''+categoryId+'\',\''+dirPageId+'\',\''+ serlatitude+'\',\''+ serlongitude +'\',\''+ serAddressFull+'\',\''+ serviceHeader+'\',\''+ openMapWithThirdParty +'\');" style="display:'+checkinDisplay+'"></a><a href="#" class="map-icon"  onclick="showServicePageMapNew(\''+serlatitude+'\',\''+serlongitude+'\',\''+j+'\',\''+serviceHeader+'\',\''+serviceHeader+'\',\''+pageCount+'\',\''+isFromDirectory+'\');" style="display:'+dirMapDisplay+'"></a><a href="#" class="call-icon" onclick="MakeCall(\''+callNumber+'\')" style="display:'+callDisp+'"></a><a href="#" class="share-icon" onclick="shareDirectory(\''+serviceImage+'\',\''+serviceHeader+'\',\''+serviceDescription+'\');" style="display:'+listingShare+'"></a></a><a href="#" class="like-icon" onclick="showDataReview(\''+AppendPageNo+'\',\''+listId+'\')" style="display:'+ratingDisp+'">'+reviewRating+'<br>'+rating_dir+'</a></span><span onclick="createServiceDetail(\''+pageCount+'\',\''+j+'\',\''+x+'\',\''+intrnalSearchCount+'\');" class="hyper-listing-overlay"></span><p class="paddingleft" style="display:'+oldDisp+';'+padd+'">'+serviceDescription+'</p> <img src="'+transparentImg+'" alt=""/ ></li>';
                                            intrnalSearchCount++;
                                            searchj=j;
                                            searchx=x;
                                            }
                                            
                                            }
                                            }
                                            else
                                            {
                                            
                                            filterIdArray.push(listId);
                                            console.log("is the dcbndklsnbcflsbn");
                                            
                                            if(parseFloat(serlatitude)!=0)
                                            {
                                            latArr.push(serlatitude);
                                            longArr.push(serlongitude);
                                            
                                            indexArr.push(j);
                                            var encdAdd="";
                                            if(serAddressFull.length > 2)
                                            {
                                            encdAdd=encodeURIComponent(serAddressFull);
                                            addArr.push(encodeURIComponent(serviceHeader));
                                            }
                                            else
                                            {encdAdd=address_name_not_avail_services;
                                            addArr.push("Address name not available");
                                            }
                                            }
                                            
                                           
                                            
                                            
                                            serviceDescription=replaceAll('&lt;br&gt;','<br>',serviceDescription);
                                            
                                            if(serviceImage.length>1 && (serviceImage != 'http://apps.appypie.com/media/services/' || serviceImage != 'http://snappy.appypie.com/media/services/' || serviceImage != 'http://'+localStorage.getItem("reseller")+'/media/services/'))
                                            {
                                            
                                            }
                                            else
                                            {
                                            
                                           serviceImage="http://"+localStorage.getItem("reseller")+"/images/no-image.jpg";
                                            
                                            }
                                            var transparentImg = "images/hyper-local/transparent-bg.png";
                                            if(sessionStorage.getItem("serviceoldversion") == 0)
                                            {
                                            transparentImg = serviceImage;
                                            }
                                            //deepak
											var oldDisp =call_disp_none_services;
                                            if(sessionStorage.getItem("serviceoldversion") == 0)
                                            {
                                             oldDisp =rating_disp_block_services;
                                             transparentImg = serviceImage;
                                            }
                                            var padd="";
                                            if(sessionStorage.getItem("defaultImgEnable") == 0)
                                            {
                                            
                                              if(serviceImage=="http://snappy.appypie.com/images/no-image.jpg")
                                              {
												
                                                padd="padding:0 0 0 0px!important;";
                                              }
                                           
                                            }
											
                                            
                                            serviceslist2 +='<li class="listing-item" id="serviceinnerSubHeaderPage'+j+'" style="background-image:url('+serviceImage+');"> <span class="hyper-catNamebox categoryName" onclick="createServiceDetail(\''+pageCount+'\',\''+j+'\',\''+x+'\',\''+intrnalSearchCount+'\');"> '+serviceHeader+'<p style="display:'+ptagDisp+'">'+distanceCalcWithUnit+'</p> </span><span class="hyper-addons"><a href="#" class="checkin-icon" onclick="checkinPage(\''+listId+'\',\''+categoryId+'\',\''+dirPageId+'\',\''+ serlatitude+'\',\''+ serlongitude +'\',\''+ serAddressFull+'\',\''+ serviceHeader+'\',\''+ openMapWithThirdParty +'\');" style="display:'+checkinDisplay+'"></a><a href="#" class="map-icon"  onclick="showServicePageMapNew(\''+serlatitude+'\',\''+serlongitude+'\',\''+j+'\',\''+serviceHeader+'\',\''+serviceHeader+'\',\''+pageCount+'\',\''+isFromDirectory+'\');" style="display:'+dirMapDisplay+'"></a><a href="#" class="call-icon" onclick="MakeCall(\''+callNumber+'\')" style="display:'+callDisp+'"></a><a href="#" class="share-icon"  onclick="shareDirectory(\''+serviceImage+'\',\''+serviceHeader+'\',\''+serviceDescription+'\');" style="display:'+listingShare+'"></a></a><a href="#" class="like-icon" onclick="showDataReview(\''+AppendPageNo+'\',\''+listId+'\')" style="display:'+ratingDisp+'">'+reviewRating+'<br>'+rating_dir+'</a></span><span onclick="createServiceDetail(\''+pageCount+'\',\''+j+'\',\''+x+'\',\''+intrnalSearchCount+'\');" class="hyper-listing-overlay"></span><p class="paddingleft" style="display:'+oldDisp+';'+padd+'">'+serviceDescription+'</p> <img src="'+transparentImg+'" alt=""/ ></li>';
                                            intrnalSearchCount++;
                                            
                                            searchj=j;
                                            searchx=x;
                                            }
                                            j=parseFloat(j)+1;
                                            x=parseFloat(x)+1;
                                            });
    
	
	
     var currentLat =	sessionStorage.getItem("localLatitude");
    var currentLong=    sessionStorage.getItem("localLongitude");
	
    multipleLocStr="maplocationdir:"+latArr+"%%%"+longArr+"%%%"+indexArr+"%%%"+addArr+"%%%"+localStorage.getItem("AppName")+"%%%"+pageCount+"%%%"+currentLat+"%%%"+currentLong;
	
    
    var ServiceHeaderHtml="";
    
    
    
    var headerMapDisp=call_disp_none_services;
    
    if(sessionStorage.getItem("dirMapDisplay")=="true")
    {
        
        if(latArr.length<1)
        {
            headerMapDisp=call_disp_none_services;
        }
        else
        {
            headerMapDisp=rating_disp_block_services;
        }
        
    }
    
    console.log("multipleLocStr====="+multipleLocStr);
    
    sessionStorage.setItem("isFromBookMark","");
    if(isFrom=="BOOKMARKS")
    {       sessionStorage.setItem("isFromBookMark","BOOKMARKS");
        
        ServiceHeaderHtml='<header class="hyper-local header-main category-head "><h1>'+BookmarksDir+'</h1><a class="hyper-back" href="#" onclick="onBackKeyDown();"></a><div class="right-nav"> <a class="hyper-location" onclick="openMapWithMultipleAnnotation()"" style="display:'+headerMapDisp+'"></a></div></header>';
    }
    else
    {
        
        if(sessionStorage.getItem("reviewSetting")=="true"||sessionStorage.getItem("serivceisLocationSearch")=="true")
        {
            ServiceHeaderHtml='<header class="hyper-local header-main category-head "><h1>'+serviceHeaderName+'</h1><a class="hyper-back" href="#" onclick="onBackKeyDown();onBackArrayManage(\''+AppendPageNo+'\');"></a><div class="right-nav"> <a class="hyper-location" onclick="openMapWithMultipleAnnotation()"" style="display:'+headerMapDisp+'"></a><a class="header-hyper-filter" href="#" onclick="filterDirectory(\'internalSearch\',\''+AppendPageNo+'\')" ></a></div></header>';
        }
        else
        {
            ServiceHeaderHtml='<header class="hyper-local header-main category-head "><h1>'+serviceHeaderName+'</h1><a class="hyper-back" href="#" onclick="onBackKeyDown();onBackArrayManage(\''+AppendPageNo+'\');"></a><div class="right-nav"> <a class="hyper-location" onclick="openMapWithMultipleAnnotation()"" style="display:'+headerMapDisp+'"></a></div></header>';
            
            
        }
    }
    
    
    if(intrnalSearchCount==0&& subcatCount==0)
    {
        alertPopUp(alert_sign_alert_services,data_not_availaible_dir);
        
        filterIdArray=filterIdArrayStored.slice();
        
    }
    else
    {
        
            
            var oldAddClass='';
            var mainHead ="";
            if(sessionStorage.getItem("serviceoldversion") == 0)
            {
                oldAddClass= old_add_class_services;
                mainHead ="";
                // mainHead = '<div class="categories-accordion"><h3 class="on">'+sessionStorage.getItem("lastHead")+'</h3></div>';
            }
            
            
            
            appendHtml(ServiceHeaderHtml+'</header> <section class="hyper-local categorys-page '+oldAddClass+'"><div class="scroller-on">'+mainHead+'<ul class="hyper-cat-listing">'+serviceslist+serviceslist2+'</ul></div></section>',AppendPageNo,AppendPageNo);
			resetDirectoryHeader();
            
            if(sessionStorage.getItem("serviceoldversion") == 0)
            {
                $(".categoryName, .cat-heading-text, .icon-dirctroy-more, .categories-accordion h3, .listing-item p").css("color", sessionStorage.getItem('pageTextColor'));
            if(sessionStorage.getItem("defaultImgEnable") == 1)
            {
                
            }
            else
            {
                $(".hyper-cat-listing li").each(function(){
                    try{
                        var thisAttr = $(this).attr("style");
                        if(thisAttr)
                        {
                        if(thisAttr.indexOf("no-image") != -1)
                        {
                        $(this).addClass("no-image");
                        }
                        }
                        }catch(err){
                        console.log("Listing Image ERROR: "+err);
                        }
                        });
            }
            
            setTimeout(function(){
                       
                       var thisP = $.mobile.activePage;
                       if(thisP.find(".category-item-d").size() != 0)
                       {
                       //serviceSubHeader
                       thisP.find(".category-items").remove();
                       thisP.find(".categories-accordion").addClass("view").append('<ul class="hyper-cat-listing category-items"></ul>');
                       thisP.find(".category-item-d").appendTo(".category-items");
                       thisP.find(".categories-accordion").find("h3").click(function(){
                                                                            var thisA = $(this);
                                                                            if(thisA.is(".on"))
                                                                            {
                                                                            thisP.find(".category-items").slideUp("fast", function(){thisA.removeClass("on");})
                                                                            }
                                                                            else
                                                                            {
                                                                            thisP.find(".category-items").slideDown("fast", function(){thisA.addClass("on");})
                                                                            }
                                                                            })
                       }
                       
                       }, 1000)
            
            
        
        
        
        
        
            
        
        }
    }
  //  $(".hyper-local").parents(".ui-page").addClass("overflowNone").find("div:eq(0)").css("overflow", "hidden")
    
    
    sortArray.sort(function(a, b){return a[1]-b[1]});
    
    for(gshort=0;gshort<j;gshort++)
    {
        
        var xyz=0;
        
        
        if(gshort == 0)
        {
            $('#serviceinnerSubHeaderPage'+sortArray[gshort][0]).insertAfter(".inner_service_heading");
        }
        else
        {
            var xyz=gshort-1;
            $('#serviceinnerSubHeaderPage'+sortArray[gshort][0]).insertAfter('#serviceinnerSubHeaderPage'+sortArray[xyz][0]);
        }    }
}

function distanceFromCurrentLocation(lat2, lon2, unit) {
if(lat2.length>4 && lon2.length>4)
	{	   
		var lat1=parseFloat(sessionStorage.getItem("localLatitude")) ;
		var lon1=parseFloat(sessionStorage.getItem("localLongitude"));
		var radlat1 = Math.PI * lat1/180
		var radlat2 = Math.PI * lat2/180
		var radlon1 = Math.PI * lon1/180
		var radlon2 = Math.PI * lon2/180
	  
		var theta = lon1-lon2
		var radtheta = Math.PI * theta/180
		var dist = Math.sin(radlat1) * Math.sin(radlat2) + Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta);
		dist = Math.acos(dist)
		dist = dist * 180/Math.PI
		dist = dist * 60 * 1.1515
		if (unit=="K") { dist = dist * 1.609344 }
		if (unit=="N") { dist = dist * 0.8684 }
		return dist.toFixed(2);
	}
	else
	{
			 return "NaN";
	}
}
function createServiceDetailFromMap(j,index,i,fromPage,fromNativeCheck,annotationStr)
{
    
    var index1=j;
    var index2=index;
    var index3=i;
    var index4=fromPage;
    var index5=fromNativeCheck;
    
    $('.appypie-loader').show();
    console.log("activePage ---> "+sessionStorage.getItem("activePage"));
    console.log("annotationStr ---> "+annotationStr);
    var x=0;
    var urlData="";
    if(parseInt(sessionStorage.getItem("AppendPageNo"))==3)
    {
        urlData=sessionStorage.getItem('xmlPath')+'dir_Sub_cat_detail_'+sessionStorage.getItem('dirCatId')+'_pageNo'+j+'.xml';
    }
    else
    {
        urlData=sessionStorage.getItem('xmlPath')+'dir_cat_detail_'+sessionStorage.getItem('dirCatId')+'_pageNo'+j+'.xml';
        
    }
    
    console.log("urlData======="+urlData);
    $.ajax({
           type: "POST",
           url: urlData,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           success: function(data, status, req)
           {
           console.log("Response========"+req.responseText);
           var xmlData=jQuery.parseXML(req.responseText);
           var counter=0;
           var indexCounter=0;
           var intrnalSearchCount=0;
           var headerListArray=new Array();
           
           $(xmlData).find("record").each(function ()
                                          {
                                          
                                          var header= $(this).find("header").text();
                                          var listId=$(this).find("listId").text();
                                          
                                          header=replaceAllstr(header,":","");
                                          header=replaceAllstr(header,"%26","");
                                          header=replaceAllstr(header,",","");
                                          header=replaceAllstr(header,"&","");
                                          header=replaceAllstr(header," ","");
                                          
                                          annotationStr=replaceAllstr(annotationStr,":","");
                                          annotationStr=replaceAllstr(annotationStr,"%26","");
                                          annotationStr=replaceAllstr(annotationStr,",","");
                                          annotationStr=replaceAllstr(annotationStr,"&","");
                                          annotationStr=replaceAllstr(annotationStr," ","");
                                          
                                          
                                          
                                          headerListArray.push(listId);
                                          
                                          
                                          console.log("header.trim()==="+header.trim());
                                          console.log("annotationStr.trim()==="+annotationStr.trim());
                                          
                                          if(header.trim()==annotationStr.trim())
                                          {
                                          indexCounter=counter;
                                          }
                                          counter++;
                                          });
           
           var listId=headerListArray[indexCounter];
           
           var indexOfList=$.inArray(listId, filterIdArray);
           
           if(indexOfList>-1)
           {
           intrnalSearchCount=indexOfList;
           }
           else
           {
           intrnalSearchCount=0;
           }
           
           console.log("j=="+index1+"index="+index2+"indexCounter="+indexCounter+"intrnalSearchCount="+intrnalSearchCount);
           
           createServiceDetail(index1,index2,indexCounter,intrnalSearchCount)
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log("Error======"+response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
}
function replaceAllstr(str, find, replace) {
    return str.replace(new RegExp(find, 'g'), replace);
}
function createServiceDetail(j,index,i,intrnalSearchCount)
{
   
	 setTimeout(function(){
        
             showHideAd("hide");
            
            },100);
	    var mainIndex=i;
    $('.appypie-loader').show();
    var x=0;
    
    
    sessionStorage.setItem("AppendPageNoDetail",parseInt(sessionStorage.getItem("AppendPageNo"))+1);
    
    var urlData="";
    if(parseInt(sessionStorage.getItem("AppendPageNo"))==3)
    {
        urlData=sessionStorage.getItem('xmlPath')+'dir_Sub_cat_detail_'+sessionStorage.getItem('dirCatId')+'_pageNo'+j+'.xml';
    }
    else
    {
        urlData=sessionStorage.getItem('xmlPath')+'dir_cat_detail_'+sessionStorage.getItem('dirCatId')+'_pageNo'+j+'.xml';
        
    }
    
    var AppendPageNoDetail=sessionStorage.getItem("AppendPageNoDetail");
    console.log("urlData ---> "+urlData);
    $.ajax({
           type: "POST",
           url: urlData,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           success: function(data, status, req)
           {
           console.log(req.responseText);
           var xmlData=jQuery.parseXML(req.responseText);
           xmlDataDirDeatil=xmlData;
           var pageid="";
           var serviceDetail="";
           var bookmarkId="BookMark"+pageid;
           var isFromBookMark=sessionStorage.getItem("isFromBookMark");
           
           if(isFromBookMark=="BOOKMARKS")
           {
           serviceHeaderDir='<header class="hyper-local header-main"><h1>'+BookmarksDir+'</h1><a class="hyper-back" href="#" onclick="onBackKeyDown();"></a> <a class="bookmark" onclick="saveBookMark();" href="#"></a> </header>';
           }
           else
           {
           serviceHeaderDir='<header class="hyper-local header-main"><h1>'+serviceHeaderName+'</h1><a class="hyper-back" href="#" onclick="onBackKeyDown();"></a> <a class="bookmark" onclick="saveBookMark();" href="#"></a> </header>';
           
           }
           
           
           serviceDetail+='<section class="hyper-local detail-pages"><div class="swiper-container" id="detail-swiper"> <div class="swiper-wrapper">';
           
           $(xmlData).find('record').each(function(K)
                                          {
                                          
                                          var listId=$(this).find("listId").text();
                                          if($.inArray(listId, filterIdArray) > -1)
                                          {
                                          console.log("listId========="+listId);
                                          serviceDetail+='<div class="swiper-slide scroller-on" id="slide'+listId+'"></div>';
                                          }
                                          
                                          
                                          });//for each end
           serviceDetail+='</div></div></section>';
           
           window.location ="removewebsite:";
           
           
           appendHtml(serviceHeaderDir+serviceDetail,AppendPageNoDetail,AppendPageNoDetail);
           resetDirectoryHeader();
           
          
           
           
           
           
           setTimeout(function(){
                      
                      
                      var swiper = new Swiper('#detail-swiper', {
                                              initialSlide:intrnalSearchCount
                                              });
                      
                      swiper.on("SlideChangeEnd", function(){
                                
                                getSliderListData(swiper.activeIndex);
                                
                                })
                      
                      },1000);
           
           getSliderListData(intrnalSearchCount);
           
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           checkNetworkConnection();
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
}
function getSliderListData(newK)
{
    var AppendPageNoDetail=sessionStorage.getItem("AppendPageNoDetail");
    var newServiceData="";
    var thisID;
    var serlatitude="";
    var serlongitude="";
    var serviceMediaUrl="";
    var serAddressFull="";
    var openMapWithThirdParty='0';
    
    $(xmlDataDirDeatil).find('record').eq(newK).each(function(K)
                                                     {
                                                     
                                                      serviceHeader=$(this).find("header").text();
                                                     var serviceDescription=$(this).find("summary").text();
                                                     var serviceImage=$(this).find("image").text();
                                                     var serviceImagedefault = sessionStorage.getItem("dirDefaultImg");
                                                     console.log("serviceImagedefault=="+serviceImagedefault)
                                                     if( serviceImagedefault!=null)
                                                     {
                                                     if(sessionStorage.getItem("defaultImgEnable") == 1)
                                                     {
                                                     if(serviceImage=="http://angularml.pbodev.info/images/no-image.jpg")
                                                     {
                                                     serviceImage=serviceImagedefault;
                                                     }
                                                     if(serviceImage=="http://snappy.appypie.com/images/no-image.jpg")
                                                     {
                                                     serviceImage=serviceImagedefault;
                                                     }
                                                     }
                                                     }
                                                     
                                                     //===
                                                     
                                                     
                                                     var bodyImageComment=$(this).find("bodyImageComment").text();
                                                     var bodyImageCommentArray = bodyImageComment.split("##");
                                                     var listId=$(this).find("listId").text();
                                                     
                                                     thisID = listId;
                                                     console.log("listId==="+listId);
                                                     
                                                     
                                                     
                                                     var callDisp=rating_disp_block_services;
                                                     if($(this).find("callLink").text()=="")
                                                     {
                                                     callDisp=call_disp_none_services;
                                                     }
                                                     
                                                     var ratingDisp=call_disp_none_services;
                                                     if(sessionStorage.getItem("reviewSetting")=="true")
                                                     {
                                                     ratingDisp=rating_disp_block_services;
                                                     }
													 //start change by dheeraj
											var listingShare=call_disp_none_services;
												
                                            if(sessionStorage.getItem("listingShare")=="true")
                                            {
                                            	
											listingShare=rating_disp_block_services;
                                            
                                            }
                                                     
                                                     
                                                     var checkinDisplay=call_disp_none_services;
                                                     var dirMapDisplay=call_disp_none_services;
                                                     if(sessionStorage.getItem("dirMapDisplay")=="true")
                                                     {
                                                     if(sessionStorage.getItem("checkonEnable")=="true")
                                                     {
                                                     if(parseFloat(serlatitude)!=0)
                                                     {
                                                     checkinDisplay=rating_disp_block_services;
                                                     }
                                                     }
                                                     if(parseFloat(serlatitude)!=0)
                                                     {
                                                     dirMapDisplay=rating_disp_block_services;
                                                     }
                                                     }
                                                     
												//end change by dheeraj
                                                     
                                                     var callNumber="";
                                                     if($(this).find("call").text())
                                                     {
                                                     $(this).find("call").each(function (m)
                                                                               {
                                                                               if(m==0)
                                                                               {
                                                                               callNumber=$(this).find("callLink").text();
                                                                               }
                                                                               else
                                                                               
                                                                               {
                                                                               callNumber+=","+$(this).find("callLink").text();
                                                                               }
                                                                               })
                                                     }
                                                     
                                                     
                                                     
                                                     localStorage.setItem("listIdForRR",listId);
                                                     
                                                     var dirRating=$(this).find("dirRating").text();
                                                     var totalReview=$(this).find("totalReview").text();
                                                     if(dirRating=="")
                                                     {
                                                     dirRating=0;
                                                     totalReview=0;
                                                     }
                                                     else
                                                     {
                                                     dirRating=parseInt(dirRating);
                                                     }
                                                     var appid=localStorage.getItem("applicationID");
                                                     var pageid=localStorage.getItem("listIdForRR");
                                                     var categoryId=sessionStorage.getItem('dirCatId');
                                                     var dirPageId=sessionStorage.getItem('dirPageId');
                                                     var directoryReviewRatingData="";
                                                     var ratingfromXml='';
                                                     
                                                     var serAddressText=$(this).find("serAddressText").text();
                                                     serAddressFull=$(this).find("serAddressFull").text();
                                                     serlatitude=$(this).find("serlatitude").text();
                                                     serlongitude=$(this).find("serlongitude").text();
                                                     //start change by dheeraj
                                                     var ptagDisp='none';
                                                     if(serlatitude.trim()==""||serlongitude.trim()=="")
                                                     {
                                                     serlatitude="0";
                                                     serlongitude="0";
                                                     }
                                                     if(parseFloat(serlatitude)!=0)
                                            {
                                            ptagDisp='block';
                                            }
                                                     var checkinDisplay="none";
                                                     var dirMapDisplay="none";
                                                     if(sessionStorage.getItem("dirMapDisplay")=="true")
                                                     {
                                                     if(sessionStorage.getItem("checkonEnable")=="true")
                                                     {
                                                     if(parseFloat(serlatitude)!=0)
                                                     {
                                                     checkinDisplay='block';
                                                     }
                                                     }
                                                     if(parseFloat(serlatitude)!=0)
                                                     {
                                                     dirMapDisplay='block';
                                                     }
                                                     }
                                                     //end chnge by dheeraj
                                                     var formType=$(this).find("formType").text();
                                                     var formBuilderForm=$(this).find("formBuilderForm").text();
                                                     var formBuilderPageId=$(this).find("formBuilderPageId").text();
                                                     var formName=$(this).find("formName").text();
                                                     
                                                     var bookmarkStatus=$(this).find("bookmarkStatus").text();
                                                     if(bookmarkStatus==""||bookmarkStatus=="0")
                                                     {
                                                     bookmarkStatus="0";
                                                     }
                                                     else
                                                     {
                                                     bookmarkStatus="1"
                                                     }
                                                     var bookmarkId="BookMark"+pageid;
                                                     sessionStorage.setItem(bookmarkId,bookmarkStatus);
                                                     
                                                     var latArr=new Array();
                                                     var longArr=new Array();
                                                     var indexArr=new Array();
                                                     var addArr=new Array();
                                                     var pageCount=1;
                                                     latArr.push(serlatitude);
                                                     longArr.push(serlongitude);
                                                     indexArr.push(0);
                                                     var isFromDirectory=isfrom_drictory_services;
                                                     
                                                     if(serAddressFull.length > 2)
                                                     {
                                                     addArr.push(encodeURIComponent(serAddressFull));
                                                     }
                                                     else
                                                     {
                                                     addArr.push("Address name not available");
                                                     }
                                                     
                                                     
                                                     if($(this).find("mapThirdParty").text())
                                                     {
                                                     openMapWithThirdParty=$(this).find("mapThirdParty").text();
                                                     mapThirdPartyFlag=openMapWithThirdParty;                                                     }
                                                     
                                                     serviceMediaUrl=$(this).find("serviceMediaUrl").text();
                                                     var serviceMediaType=$(this).find("serviceMediaType").text();
                                                     
                                                     /*if($(this).find("serviceMediaType").text()&&($(this).find("serviceMediaType").text().trim()!=""))
                                                     {
                                                     newServiceData+='<ul class="hyper-cat-listing">';
                                                     newServiceData+='<li class="service_inner_image2"><div class="vendor-image" id="vendor-image'+pageid+'">';
                                                     
                                                     console.log("serviceMediaType===="+serviceMediaType);
                                                     console.log("serviceMediaUrl===="+serviceMediaUrl);
                                                     
                                                     
                                                     if(serviceMediaType == 'image')
                                                     {
                                                     if(serviceMediaUrl.length>1 && (serviceMediaUrl != 'http://apps.appypie.com/media/services/' || serviceMediaUrl != 'http://snappy.appypie.com/media/services/'))
                                                     {
                                                     detailImageWithCommaSep=serviceMediaUrl;
                                                     var serviceImageArray = serviceMediaUrl.split(",");
                                                     if(serviceImageArray.length>0)
                                                     {
                                                     for(var i=0;i<serviceImageArray.length;i++)
                                                     {
													 //alert("oooo");
                                                     newServiceData+='<img src="images/hyper-local/transparent-bg.png" data-id="'+newK+'" alt="" data-type="image" style="background-image:url('+serviceImageArray[i]+')" / >';
                                                     }
                                                     }
                                                     
                                                     newServiceData+='</div><p>'+serviceHeader+'</p><span class="hyper-addons"><a href="#" class="checkin-icon" onclick="checkinPage(\''+listId+'\',\''+categoryId+'\',\''+dirPageId+'\',\''+ serlatitude+'\',\''+ serlongitude +'\',\''+ serAddressFull+'\',\''+ serviceHeader+'\',\''+ openMapWithThirdParty +'\');" style="display:'+checkinDisplay+'"></a><a href="#" class="map-icon" onclick="showServicePageMapNew(\''+latArr+'\',\''+longArr+'\',\''+K+'\',\''+serAddressFull+'\',\''+serviceHeader+'\',\''+pageCount+'\',\''+isFromDirectory+'\',\''+openMapWithThirdParty+'\');" style="display:'+ptagDisp+'"></a><a href="#" class="call-icon" onclick="MakeCall(\''+callNumber+'\')" style="display:'+callDisp+'"></a><a href="#" class="share-icon" onclick="shareDirectory(\''+serviceImage+'\',\''+serviceHeader+'\',\''+serviceDescription+'\');"></a><a href="#" class="like-icon" onclick="showDataReview(\''+AppendPageNoDetail+'\',\''+listId+'\')" style="display:'+ratingDisp+'">'+dirRating+' <br>'+rating_dir+'</a> </span></li></ul>';
                                                     }
                                                     }
                                                     else if(serviceMediaType=="video")
                                                     {
                                                     var videoId=youtube_parser(serviceMediaUrl);
                                                     
                                                     if(serviceImage=='')
                                                     {
                                                     serviceImage='images/logo.png';
                                                     }
                                                     
                                                     newServiceData+='<div class="item"><img src="images/hyper-local/transparent-bg.png" data-id="'+newK+'" alt="" data-type="video" style="background-image:url('+serviceImage +')" onclick="playYoutubeSongFromService(\''+videoId+'\');"></div>';
                                                     newServiceData+='</div><p>'+serviceHeader+'</p><span class="hyper-addons"><a href="#" class="checkin-icon" onclick="checkinPage(\''+listId+'\',\''+categoryId+'\',\''+dirPageId+'\',\''+ serlatitude+'\',\''+ serlongitude +'\',\''+ serAddressFull+'\',\''+ serviceHeader+'\',\''+ openMapWithThirdParty +'\');" style="display:'+checkinDisplay+'"></a><a href="#" class="map-icon" onclick="showServicePageMapNew(\''+latArr+'\',\''+longArr+'\',\''+K+'\',\''+serAddressFull+'\',\''+serviceHeader+'\',\''+pageCount+'\',\''+isFromDirectory+'\',\''+openMapWithThirdParty+'\');" style="display:'+ptagDisp+'"></a><a href="#" class="call-icon" onclick="MakeCall(\''+callNumber+'\')" style="display:'+callDisp+'"></a><a href="#" class="share-icon" onclick="shareDirectory(\''+serviceImage+'\',\''+serviceHeader+'\',\''+serviceDescription+'\');"></a><a href="#" class="like-icon" onclick="showDataReview(\''+AppendPageNoDetail+'\',\''+listId+'\')" style="display:'+ratingDisp+'">'+dirRating+' <br>'+rating_dir+'</a> </span></li></ul>';
                                                     }
                                                     else if(serviceMediaType=="audio")
                                                     {
                                                     if(serviceImage=='http://apps.appypie.com/images/sound_custom_icon.jpg')
                                                     {
                                                     serviceImage='http://apps.appypie.com/images/sound_custom_icon_play.png';
                                                     }
                                                     if($(this).find("serviceAudioType").text())
                                                     {
                                                     var serviceAudioType=$(this).find("serviceAudioType").text();
                                                     
                                                     if(serviceImage=='')
                                                     {
                                                     serviceImage='images/logo.png';
                                                     }
                                                     
                                                     
                                                     if(serviceAudioType == 'soundrss')
                                                     {
                                                     serviceAudioType='rssfeed';
                                                     sessionStorage.setItem("customPlayListName","");
                                                     }
                                                     else if(serviceAudioType=="custom" || serviceAudioType=="customlist")
                                                     {
                                                     serviceAudioType="custom";
                                                     var customPlayListName=$(this).find("customPlayListName").text();
                                                     sessionStorage.setItem("customPlayListName",customPlayListName);
                                                     }
                                                     else if(serviceAudioType=="rssradio")
                                                     {
                                                     serviceAudioType="newCustomRadio";
                                                     sessionStorage.setItem("customPlayListName","");
                                                     }
                                                     //alert("efde);
                                                     newServiceData+='<div class="item"><img src="images/hyper-local/transparent-bg.png" data-id="'+newK+'" alt="" data-type="audio" style="background-image:url('+serviceImage +')" onclick="playAudioFromService(\''+serviceAudioType+'\')"></div>';
                                                     newServiceData+='</div><p>'+serviceHeader+'</p><span class="hyper-addons"><a href="#" class="checkin-icon" onclick="checkinPage(\''+listId+'\',\''+categoryId+'\',\''+dirPageId+'\',\''+ serlatitude+'\',\''+ serlongitude +'\',\''+ serAddressFull+'\',\''+ serviceHeader+'\',\''+ openMapWithThirdParty +'\');" style="display:'+checkinDisplay+'"></a><a href="#" class="map-icon" onclick="showServicePageMapNew(\''+latArr+'\',\''+longArr+'\',\''+K+'\',\''+serAddressFull+'\',\''+serviceHeader+'\',\''+pageCount+'\',\''+isFromDirectory+'\',\''+openMapWithThirdParty+'\');" style="display:'+ptagDisp+'"></a><a href="#" class="call-icon" onclick="MakeCall(\''+callNumber+'\')" style="display:'+callDisp+'"></a><a href="#" class="share-icon" onclick="shareDirectory(\''+serviceImage+'\',\''+serviceHeader+'\',\''+serviceDescription+'\');"></a><a href="#" class="like-icon" onclick="showDataReview(\''+AppendPageNoDetail+'\',\''+listId+'\')" style="display:'+ratingDisp+'">'+dirRating+' <br>'+rating_dir+'</a> </span></li></ul>';
                                                     sessionStorage.setItem("serviceAudioUrl",serviceMediaUrl);
                                                     }
                                                     }
                                                     
                                                     
                                                     
                                                     } */
													 
													 if($(this).find("serviceMediaType").text()&&($(this).find("serviceMediaType").text().trim()!=""))
                                            {
                                                    newServiceData+='<ul class="hyper-cat-listing">';
                                                    newServiceData+='<li class="service-heading"><p>'+serviceHeader+'</p></li><li class="service_inner_image2"><div class="vendor-image" id="vendor-image'+pageid+'">';
                                                     
                                                    var mediaImageUrl=$(this).find("mediaImageUrl").text();
                                                    detailImageWithCommaSep=mediaImageUrl;
                                                    var serviceImageArray = mediaImageUrl.split(",");
                                                    //start change by dheeraj
                                                    if(serviceImageArray.length>0)
                                                    {
                                                        for(var i=0;i<serviceImageArray.length;i++)
                                                        {
                                                     
                                                        if(serviceImageArray[0].trim().length>0)
                                                        {
                                                            newServiceData+='<img src="images/hyper-local/transparent-bg.png" data-id="'+newK+'" alt="" data-type="image" style="background-image:url('+serviceImageArray[i]+')" / >';
                                                        }
                                                        }
                                                    }
                                            //end change by dheeraj
                                                     
                                                     var youtubeUrl=$(this).find("youtubeUrl").text();
                                                     var youtubeUrlImage=$(this).find("youtubeUrlImage").text();

                                                     if(youtubeUrl.trim().length>1)
                                                     {
                                                        var videoId=youtube_parser(youtubeUrl);
                                                        newServiceData+='<img src="images/hyper-local/transparent-bg.png" data-id="'+newK+'" alt="" data-type="video" style="background-image:url(http://'+localStorage.getItem("reseller")+'appypie.com/images/no-image.jpg)" onclick="playYoutubeSongFromService(\''+videoId+'\');">';
                                                     }
                                                     
                                                     
                                                     var customTrackUrl=$(this).find("customTrackUrl").text();
                                                     var customTrackUrlImage=$(this).find("customTrackUrlImage").text();

                                                     if(customTrackUrl.trim().length>0)
                                                     {
                                        
                                                     var serviceAudioType="custom";
                                                     var customPlayListName=$(this).find("customPlayListName").text();
                                                     sessionStorage.setItem("customPlayListName",customPlayListName);
                                                    
                                                     newServiceData+='<img src="images/hyper-local/play-transparent.png" data-id="'+newK+'" alt="" data-type="audio" style="background-image:url('+customTrackUrlImage +')" onclick="playAudioFromService(\''+serviceAudioType+'\',\''+customTrackUrl+'\',\''+customPlayListName+'\')">';
                                                     }
                                                     
                                                     var radioPlsUrl=$(this).find("radioPlsUrl").text();
                                                     var radioPlsUrlImage=$(this).find("radioPlsUrlImage").text();
                                                     
                                                     if(radioPlsUrl.trim().length>0)
                                                     {
                                                     
                                                     var serviceAudioType="soundrss";
                                                   
                                                     newServiceData+='<img src="images/hyper-local/play-transparent.png" data-id="'+newK+'" alt="" data-type="audio" style="background-image:url('+radioPlsUrlImage +')" onclick="playAudioFromService(\''+serviceAudioType+'\',\''+radioPlsUrl+'\')">';
                                                     }

                                                     
                                                     var rssFeedUrl=$(this).find("rssFeedUrl").text();
                                                     var rssFeedUrlImage=$(this).find("rssFeedUrlImage").text();
                                                     
                                                     if(rssFeedUrl.trim().length>0)
                                                     {
                                                     
                                                     var serviceAudioType="rssfeed";
                                                     newServiceData+='<img src="images/hyper-local/play-transparent.png" data-id="'+newK+'" alt="" data-type="audio" style="background-image:url('+rssFeedUrlImage +')" onclick="playAudioFromService(\''+serviceAudioType+'\',\''+rssFeedUrl+'\')">';
                                                     
                                                     }

                                                     
                           
							
                                                     newServiceData+='</div><span class="hyper-addons"><a href="#" class="checkin-icon" style="display:'+checkinDisplay+'" onclick="checkinPage(\''+listId+'\',\''+categoryId+'\',\''+dirPageId+'\',\''+ serlatitude+'\',\''+ serlongitude +'\',\''+ serAddressFull+'\',\''+ serviceHeader+'\',\''+ openMapWithThirdParty +'\');" style="display:'+checkinDisplay+'"></a><a href="#" class="map-icon" onclick="showServicePageMapNew(\''+latArr+'\',\''+longArr+'\',\''+K+'\',\''+serAddressFull+'\',\''+serviceHeader+'\',\''+pageCount+'\',\''+isFromDirectory+'\',\''+openMapWithThirdParty+'\');" style="display:'+dirMapDisplay+'"></a><a href="#" class="call-icon" onclick="MakeCall(\''+callNumber+'\')" style="display:'+callDisp+'"></a><a href="#" class="share-icon"  onclick="shareDirectory(\''+serviceImage+'\',\''+serviceHeader+'\',\''+serviceDescription+'\');" style="display:'+listingShare+'"></a><a href="#" class="like-icon" onclick="showDataReview(\''+AppendPageNoDetail+'\',\''+listId+'\')" style="display:'+ratingDisp+'">'+dirRating+' <br>'+rating_dir+'</a> </span></li></ul>';
                                        
                                            }
                                                     else
                                                     {
                                                     if(serviceImage=='')
                                                     {
                                                     serviceImage="http://"+localStorage.getItem("reseller")+"appypie.com/images/no-image.jpg";
                                                     }
                                                     
                                                     newServiceData+='<ul class="hyper-cat-listing">';
                                                     newServiceData+='<li class="service_inner_image2"><div class="vendor-image" id="vendor-image'+pageid+'">';
                                                     newServiceData+='<img src="images/hyper-local/transparent-bg.png" data-id="'+newK+'" alt="" style="background-image:url('+serviceImage+')" / >';
                                                     
                                                     newServiceData+='</div><p>'+serviceHeader+'</p><span class="hyper-addons"><a href="#" class="checkin-icon" style="display:'+checkinDisplay+'" onclick="checkinPage(\''+listId+'\',\''+categoryId+'\',\''+dirPageId+'\',\''+ serlatitude+'\',\''+ serlongitude +'\',\''+ serAddressFull+'\',\''+ serviceHeader+'\',\''+ openMapWithThirdParty +'\');" style="display:'+checkinDisplay+'"></a><a href="#" class="map-icon" onclick="showServicePageMapNew(\''+latArr+'\',\''+longArr+'\',\''+K+'\',\''+serAddressFull+'\',\''+serviceHeader+'\',\''+pageCount+'\',\''+isFromDirectory+'\',\''+openMapWithThirdParty+'\');" style="display:'+dirMapDisplay+'"></a><a href="#" class="call-icon" onclick="MakeCall(\''+callNumber+'\')" style="display:'+callDisp+'"></a><a href="#" class="share-icon"  onclick="shareDirectory(\''+serviceImage+'\',\''+serviceHeader+'\',\''+serviceDescription+'\');" style="display:'+listingShare+'"></a><a href="#" class="like-icon" onclick="showDataReview(\''+AppendPageNoDetail+'\',\''+listId+'\')" style="display:'+ratingDisp+'">'+dirRating+' <br>'+rating_dir+'</a> </span></li></ul>';
                                                     }
                                                     
                                                     if($(this).find("summary").text().trim().length>0)
                                                     {
														 if(dirSummaryListingDetailsPage=='1')
														 {
															newServiceData+='<div class="side-margin"><p class="runing_txt">'+$(this).find("summary").text()+'</p></div>';
														 }
													 }
                                                     if($(this).find("detail").text().trim().length>0)
                                                     {
                                                     newServiceData+='<div class="side-margin"><p class="runing_txt">'+$(this).find("detail").text()+'</p></div>';
                                                     }
                                                     
                                                              var listDataHtml='';
                                                    if($(this).find("call").text())
                                            {
                                            listDataHtml='<div class="provider-info">';
                                            $(this).find("call").each(function ()
                                                                      {
																		  
																		  listDataHtml +='<span >'+ returnImageHtml($(this).find("callIcon").text())+'<a>'+call_dir+' </a>'+'<a onclick="MakeCall(\''+callNumber+'\')">'+$(this).find("callLink").text()+'</a></span>';
																		  
																		  
                                                                     
                                                                      });
                                            
                                            listDataHtml+='</div>';
                                            }
                                                     
                                                     if(sessionStorage.getItem("reviewSetting")!='false')
                                                     {
                                                     listDataHtml +='<div class="provider-info rating-icon" onclick="showDataReview(\''+AppendPageNoDetail+'\',\''+listId+'\')"> <span>'+user_reviews_ratings_dir+'</span> <p>'+rating_dir+' : <strong>'+dirRating+'</strong>/5 '+fromDir+' <strong>'+totalReview+' '+Users_Dir+'</strong></p><p>'+reviews_Dir+': <strong>'+totalReview+' '+Users_Dir+'</strong></p> </div>';
                                                     }
                                                     
                                                         if($(this).find("email").text())
                                            {
                                            listDataHtml+='<div class="provider-info">';
                                            $(this).find("email").each(function ()
                                                                       {
																		   listDataHtml +='<span id="txt_services_email">'+returnImageHtml($(this).find("serEmailIcon").text())+'<a>'+$(this).find("serEmailDataLabel").text()+' </a>'+'<a onclick="showServicePageEmail(\''+$(this).find("serEmailFull").text()+'\');">'+$(this).find("serEmailFull").text()+'</a></span>';
																		   
                                                                     
                                                                       });
                                            listDataHtml+='</div>';
                                            
                                            }
                                                     
                                                     
                                                      if($(this).find("url").text())
                                            {
                                            listDataHtml+='<div class="provider-info">';
                                            $(this).find("url").each(function ()
                                                                     {
																		 listDataHtml +='<span>'+ returnImageHtml($(this).find("externalIcon").text())+'<a>'+$(this).find("externalDataLabel").text()+' </a>'+'<a onclick="showServicePage(\''+$(this).find("externalLink").text()+'\')">'+$(this).find("externalText").text()+'</a></span>';
																		 
                                                                   
                                                                     });
                                            listDataHtml+='</div>';
                                            }
                                                     
                                                     if($(this).find("openTableId").text())
                                                     {
                                                     var opentableId=$(this).find("openTableId").text();
                                                     var opentableLabel=$(this).find("openTableLabel").text();
                                                     listDataHtml+='<div class="provider-info email-icon">';
                                                     listDataHtml +='<span id="txt_services_callmeN_"><a onclick="OpenTable(\''+opentableId+'\')">'+opentableLabel+'</a></span>';
                                                     listDataHtml+='</div>';
                                                     }
                                                     
                                                     if(serAddressText.length>1 && serAddressFull.length>1)
                                                     {
                                                     var j=0;
                                                     var pageCount=1;
													 
				
                                                     listDataHtml+='<div class="provider-info "><span onclick="showServicePageMapNew(\''+serlatitude+'\',\''+serlongitude+'\',\''+j+'\',\''+serAddressFull+'\',\''+serviceHeader+'\',\''+pageCount+'\',\''+isFromDirectory+'\',\''+openMapWithThirdParty+'\');">'+returnImageHtml($(this).find("serAddressIcon").text())+'<a>'+address_dir+' </a>'+serAddressFull+'</span></div></div>';
													 
                                                 
													 
													 var adddir="add";
                                                   // var modedir="this"
													  //var countcheckin=0;,'+countcheckin+'
													  listDataHtml+='<div class="hyper-map">\
                                                     <div class="map" id="googleMap'+newK+'" style="width:100%;height:200px;display:'+dirMapDisplay+'""></div></div>';
													 
													 //checkincount(listId,categoryId,dirPageId,"get");
													 
                                                     }
                                                     
                                                     
                                                     if($(this).find("dirCouponLabel").text() && $(this).find("dirCoupon").text())
                                                     {
                                                     var couponCode=$(this).find("dirCoupon").text();
                                                     if(couponCode.trim() != '')
                                                     {
                                                     listDataHtml+='<div class="provider-info email-icon"><span id="txt_services_email"><a onclick="openCouponCode(\''+couponCode+'\');">'+couponCode+'</a></span></div>';
                                                     }
                                                     }
                                                     if(sessionStorage.getItem("fornBuilderSetting")=='true')
                                                     {
                                                     var customFormName="";
                                                     if(formType=="formbuilder")
                                                     {
                                                     var isIdExist=false;
                                                     $(masterData).find("Formbuilder").each(function()
                                                                                            {
                                                                                            var formbuilderPageIdXmlData = $(this).find("formbuilderPageId").text();
                                                                                            if(formbuilderPageIdXmlData==formBuilderPageId)
                                                                                            {
                                                                                            $(this).find("custom").each(function(){
                                                                                                                        isIdExist=true;
                                                                                                                        customFormName = $(this).find("customFormName").text();
                                                                                                                        });
                                                                                            }
                                                                                            });
                                                     
                                                     if(isIdExist)
                                                     {
                                                      listDataHtml+='<div class="hyper-gray-wrapper"> <button class="hyper-sent-btn" data-role="none"   onclick="callFormBuilder(\''+formType+'\',\''+formBuilderForm+'\',\''+formBuilderPageId+'\',\''+listId+'\',\''+AppendPageNoDetail+'\')">'+customFormName+'</button> </div>';
                                                
                                                     }
                                                     }
                                                     else
                                                     {
                                                     
                                                     
                                                     listDataHtml+='<div class="hyper-gray-wrapper"> <button class="hyper-sent-btn"  onclick="openRequestForm(\''+pageid+'\',\''+categoryId+'\',\''+dirPageId+'\',\''+AppendPageNoDetail+'\',\''+serviceHeader+'\')" data-role="none">'+send_request+'</button> </div>';
                                                     
                                                     
                                                     }
                                                     }
                                                     else
                                                     {
                                                     listDataHtml+='<div class="hyper-gray-wrapper"> </div>';
                                                     }
                                                     
                                                     newServiceData+=listDataHtml;
                                                     
                                                     
                                                     
                                                     });
    
    $("#slide" + thisID).html(newServiceData);
    $("#slide" + thisID).next().html("");
    $("#slide" + thisID).prev().html("");
	$("#slide" + thisID).find(".vendor-image").find("img").hide();
	
	
	if(sessionStorage.getItem("serviceoldversion") == 0 )
		{
			if(sessionStorage.getItem("defaultImgEnable") == 0 )
			{
				$(".detail-pages .service_inner_image2").addClass("no-image");
			}
		}
	
    //.prev()
    //change by dheeraj
      $("#slide" + thisID).find(".vendor-image").find("img").hide().eq(0).show();
    setTimeout(function(){
    $("#slide" + thisID).find(".vendor-image").find("img").show()
    $("#slide" + thisID).find(".vendor-image").each(function(j){
                                                    
                                                    $(this).addClass("swiper-container").find("img").wrapAll('<div class="swiper-wrapper"></div>');
                                                    $(this).find("img").each(function(imgI){
                                                                             
                                                                             if($(this).attr("data-type") == "image")
                                                                             {
                                                                             $(this).wrap('<div class="swiper-slide image" data-index="'+imgI+'" data-id="'+$(this).attr("data-id")+'" onclick="openPhotoDir(this);"></div>');
                                                                             }
                                                                             else
                                                                             {
                                                                             $(this).wrap('<div class="swiper-slide"></div>');
                                                                             }
                                                                             
                                                                             
                                                                             });
                                                    
                                                    if($(this).find(".swiper-slide").size() > 1)
                                                    {
                                                    $(this).append('<div class="swiper-button-next next-'+j+'"></div><div class="swiper-button-prev prev-'+j+'"></div>');
                                                    var swiperSub = new Swiper(this, {
                                                                               nextButton: '.next-' + j,
                                                                               prevButton: '.prev-' + j,
                                                                               paginationClickable: true
                                                                               })
                                                    }
                                                    });
    
    },1000);
    var bookmarkId="BookMark"+thisID;
    var bookmarkStatus=sessionStorage.getItem(bookmarkId);
    
    if(bookmarkStatus=="0")
    {
        $("header.hyper-local .bookmark").removeClass("on");
    }
    else
    {
        
        $("header.hyper-local .bookmark").addClass("on");
    }
    
    
    sessionStorage.setItem("serviceMediaUrl",serviceMediaUrl);
    setTimeout(function(){
    
    if(parseFloat(serlatitude)!=0)
    {
        var gid='googleMap'+newK;
        var mapDiv = document.getElementById(gid);
        var map = new google.maps.Map(mapDiv, {
                                      zoom: 8,
                                      center: new google.maps.LatLng(serlatitude, serlongitude)
                                      });
        
        // We add a DOM event here to show an alert if the DIV containing the
        // map is clicked.
        google.maps.event.addDomListener(mapDiv, 'click', function() {
                                         //window.alert('Map was clicked!');
                                         var isFromDirectory=isfrom_drictory_services;
                                         showServicePageMapNew(serlatitude,serlongitude,0,encodeURIComponent(serAddressFull.replace("'","")),localStorage.getItem("AppName"),1,isFromDirectory,openMapWithThirdParty);
                                         
                                         });
        
        
        var map=new google.maps.Map(document.getElementById(gid),map);
        
        var marker=new google.maps.Marker({
                                          position:new google.maps.LatLng(serlatitude, serlongitude),
                                          });
        marker.setMap(map);
    }
    },1000);
}


function callFormBuilder(formType,formBuilderForm,formBuilderPageId,listId,AppendPageNoDetail)
{
    sessionStorage.setItem("isfromDir",1);
    sessionStorage.setItem("DirFormAppendIndex",parseInt(AppendPageNoDetail)+1);
    directoryHome(1);
    getFormIndexForService(formType,formBuilderForm,formBuilderPageId,listId);
    
}
function onBackArrayManage(AppendPageNo)
{
    if(parseInt(AppendPageNo)==3)
    {
        filterIdArray=filterIdArrayStored.slice();
        sessionStorage.setItem("AppendPageNo",2);
    }
    
}

function openPhotoDir(obj)
{
	var curObj = $(obj);
	//imageIndex,pageIndex	
	$("body").append('<div id="lightBoxView" class="swiper-container"><div class="swiper-wrapper"></div></div>');
	$("#lightBoxView").append('<a class="close">X</a>');
	var cloneObj = curObj.parent().find("div.image").clone().removeAttr("onclick").attr("class", "swiper-slide");
	$("#lightBoxView .swiper-wrapper").append(cloneObj)
	
	var initialIndex = curObj.attr("data-index");
	var lightSwiper = new Swiper('#lightBoxView', {
		initialSlide:initialIndex
	});
	$("#lightBoxView").find("a").click(function(){
		$("#lightBoxView").remove();
	})
	//var imgeUrls=sessionStorage.getItem("serviceMediaUrl");
	//window.plugins.photo.show({ videoid:imageIndex, videoInfo:imgeUrls, pictext:"", piclikes:"", piccomments:""}, function() {}, function() {} );
}


function insertListingReviewMobile()
{
    
	/**** change by geeta ******/
	localStorage.setItem("reviewandpost","greview");
	/**** change by geeta ******/
	
    if((localStorage.getItem("isLogin")==null)||(localStorage.getItem("isLogin")=='null'))
    {
        loginService();
    }else
    {
        var appid=localStorage.getItem("applicationID");
        var pageid=localStorage.getItem("listIdForRR");
        var categoryId=sessionStorage.getItem('dirCatId');
        var dirPageId=sessionStorage.getItem('dirPageId');

        var name=localStorage.getItem("userName");
        var email= localStorage.getItem("isLogin");
        var review=document.getElementById("commentsRR").value;
   
        var rating=$('#starRating').raty('score');

        if(typeof rating != 'undefined')
        {
            if(name != '' && name != null)
            {
                if(email != '' && email != null && checkEmail(email))
                {
                    //if(review != '' && review != null)
                    //{
                          var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/directory-soap#insertListingReviewMobile";
                        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><insertListingReviewMobile xmlns=\"http://'+localStorage.getItem("reseller")+'/services/directory-soap#insertListingReviewMobile\"><appId>'+localStorage.getItem("applicationID")+'</appId><pageId>'+pageid+'</pageId><categoryId>'+categoryId+'</categoryId><dirPageId>'+dirPageId+'</dirPageId><email>'+email+'</email><review>'+review+'</review><rating>'+rating+'</rating><name>'+name+'</name><appOwnerName>'+localStorage.getItem("ownerName")+'</appOwnerName><appOwnerEmail>'+localStorage.getItem("owneremail")+'</appOwnerEmail><dirName>'+serviceHeaderName+'</dirName><listingName>'+serviceHeader+'</listingName><categoryName>'+sessionStorage.getItem("serviceSubHeader")+'</categoryName><appName>'+localStorage.getItem("AppName")+'</appName></insertListingReviewMobile></soap:Body></soap:Envelope>';
                        console.log("Soap xza() -->"+soapRequest);
                        $.ajax({
                               type: "POST",
                               url: wsUrl,
                               contentType: "text/xml",
                               dataType: "text",
                               data: soapRequest,
                               success: function(data, status, req)
                               {
                               console.log("result 1"+req.responseText);
                               var returnData=$(req.responseText).find("return").text();
                               if(returnData=="success")
                               {
                                    alertPopUp(alert_thanks_services,alert_success_submitted_services);
                                    onBackKeyDown();
                               
                               
                               directoryAvgReviewRatingXml();
                               }
                               
                              // getreviewData(index)
                               },
                               error: function(response, textStatus, errorThrown)
                               {
                               alertPopUp(alert_oops_services,alert_server_not_found_try_later_services);
                               console.log(errorThrown.responseText);
                            
                               }
                               });
                   // }
                    //else
                    //{
                     //   alertPopUp(alert_services,comments_cannot_be_leftblank_dir);
                   // }
                }
                else
                {
                    alertPopUp(alert_services,alert_enter_valid_email_address_services);
                }
            }
            else
            {
                alertPopUp(alert_services,alert_name_field_cant_blank);
            }
        }
        else
        {
            alertPopUp(alert_services,please_select_ratings_dir);
        }
    }
}


function openCouponCode(heading)
{
    
    $data=$(masterData).find( "coupon[indexval="+0+"]");
    var x=0;
    $data.find("couponRecord").each(function()
                                    {
                                    var couponHeading=$(this).find('couponCode').text();
                                    if(couponHeading == heading)
                                    {
                                    coupon_view(0,x,4)
                                    }
                                    x++;
                                    });
    
}

function getLocalCords()
{
    
    console.log("getLocalCords()");
    if (navigator.geolocation){
        console.log("in if condition of getLocalCords()");
        
        navigator.geolocation.getCurrentPosition(showPositionServices, failGettingLocation);
        
    }
    else
    {
        navigator.notification.alert(
                                     alert_please_switchon_location_services,
                                     alertDismissed,
                                     alert_dir,
                                     ok_dir);
    }
}

function showPositionServices(position)
{
    
    sessionStorage.setItem("localLatitude",position.coords.latitude);
    sessionStorage.setItem("localLongitude",position.coords.longitude);

    getCityName(position.coords.latitude,position.coords.longitude);
}

function failGettingLocation()
{

    var lat1="28.5935650";
    var lon1="77.3188020";
    sessionStorage.setItem("localLatitude",lat1);
    sessionStorage.setItem("localLongitude",lon1);
    getCityName(lat1,lon1);
    console.log("failGettingLocation()");
    navigator.notification.alert(
                                 alert_please_switchon_location_services,
                                 alertDismissed,
                                 alert_dir,
                                 ok_dir);
    setTimeout(function(){$('.appypie-loader').hide();},1000);
    
}

var sortArray=new Array();
function showServicePage(externalLink)
{	
    var externalLinka = externalLink.indexOf("http");
    if(externalLinka<0)
    {
      externalLink = "http://"+externalLink;
    }
    externalLink=externalLink+"@@@@@false";	
	var link=externalLink;
	$("#contentHolder7").css({"background-color": "#fff"});
	sessionStorage.setItem('pageLevel',4);
	sessionStorage.setItem('pageContainer'+4,(parseInt(4)+1));
    link=encodeURI(link);
    if(link.indexOf(".pdf")!=-1)
        {
            //history.go("-1");
			toaster.openWebUrl(link);
            //toaster.goToPdfReader(link,"NO");
        }
        else
        {
		window.location="show"+link;
		}
}

function youtube_parser(url)
{
    var regExp = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=)([^#\&\?]*).*/;
    var match = url.match(regExp);
    if (match&&match[2].length==11){
        return match[2];
    }
    else{
        //error
    }
}

function playYoutubeSongFromService(videoId)
{
    if(checkNetworkConnection())
    {
	   var url="https://www.youtube.com/watch?v="+videoId;
    showServicePage(url);
    }
}

function playAudioFromService(serviceAudioType,serviceAudioUrl,customPlayListName)
{
	//alert("playAudioFromService " +serviceAudioType  +"  customPlayListName   "+customPlayListName);		
   console.log(" playAudioFromService serviceAudioType : "+serviceAudioType  +" playAudioFromService "+ playAudioFromService);
	
    if(checkNetworkConnection())
    {
		
        sessionStorage.setItem("fromServicePage","true");
		
		if(serviceAudioType=="rssfeed")
            {
               window.plugins.playerplugin.show({plsTypeValue:plsType,url:serviceAudioUrl,type:'customRadio',imageurl:"",channalNameValue:"",appName:localStorage.getItem("AppName"),enableautoplay:enableAutoPlay}, function() {}, function() {} );
             //window.plugins.playerplugin.show({plsTypeValue:'',url:serviceAudioUrl,type:'rssfeed',imageurl:"",channalNameValue:"",appName:localStorage.getItem("AppName"),enableautoplay:enableAutoPlay}, function() {}, function() {} );
            }
            else  if(serviceAudioType=="soundrss")
            {
             window.plugins.playerplugin.show({plsTypeValue:plsType,url:serviceAudioUrl,type:'customRadio',imageurl:"",channalNameValue:"",appName:localStorage.getItem("AppName"),enableautoplay:enableAutoPlay}, function() {}, function() {} );
            //  window.plugins.playerplugin.show({plsTypeValue:'',url:serviceAudioUrl,type:'soundrss',imageurl:"",channalNameValue:"",appName:localStorage.getItem("AppName"),enableautoplay:enableAutoPlay}, function() {}, function() {} );
            }else{
               window.plugins.playerplugin.show({plsTypeValue:plsType,url:serviceAudioUrl,type:'customRadio',imageurl:"",channalNameValue:"",appName:localStorage.getItem("AppName"),enableautoplay:enableAutoPlay}, function() {}, function() {} );
            // window.plugins.playerplugin.show({plsTypeValue:'',url:serviceAudioUrl,type:'custom',imageurl:"",channalNameValue:"",appName:localStorage.getItem("AppName"),enableautoplay:enableAutoPlay}, function() {}, function() {} );
		 }
		
		console.log("play audio>>>>"+serviceAudioType     +"  customPlayListName  "+customPlayListName);
        window[serviceAudioType](0);
        localStorage.setItem("activeView","servicedetailview");
        localStorage.setItem("servicePageIndex",localStorage.getItem("pageIndex"));
        checkServiceIndex = 'true';
    }
}

function MakeCall(number)
{
   if(number.indexOf(",")!=-1)
   {
    var totalNumber=new Array();
	totalNumber=number.split(",");
	number=totalNumber[0];
   }
   // window.location = 'tel:'+number;
  // window.location="showtel:"+number+"";
  toaster.makeCall(number);
}

function showServicePageMap(MapLat,MapLong,address,openMap)
{
    console.log("showServicePageMap--->MapLat-->"+MapLat+"<---MapLong-->"+MapLong);
    var appName=localStorage.getItem("AppName");
     var maplink="http://maps.google.com/?q="+address+"&t=m&z=15";
/*  if(parseFloat(openMap) == 1)
    {
        window.location="directionmapsthirdparty:" +maplink+"&&Current Location&&"+sessionStorage.getItem("localLatitude")+"&&"+sessionStorage.getItem("localLongitude")+"&&"+MapLat+"&&"+MapLong+"&&"+appName;
    }
    else
    {       
        window.location="directionmaps:" +maplink+"&&Current Location&&"+sessionStorage.getItem("localLatitude")+"&&"+sessionStorage.getItem("localLongitude")+"&&"+MapLat+"&&"+MapLong+"&&"+appName;
    }
	*/
	 if(!address)
   	{
   	address="";
   	}
   	console.log("showServicePageMap--->MapLat-->"+MapLat+"<---MapLong-->"+MapLong);
    window.location="maplocation:"+MapLat+"&&"+MapLong+"&&"+address;
}

function showServicePageEmail(email)
{
   // window.location="mailto:"+email+"";
   window.location="showmailto:"+email+"";
}


var imgIndexSl=0;
function selectPhotoDir(index) {
     var imgid="imageSL"+index;
    imgIndexSl=index;
    sessionStorage.setItem("imgId",imgid);
	
    navigator.notification.confirm(
                                   alert_select_from_where_want_services, // message
                                   onConfirmDir, // callback to invoke with index of button pressed
                                   alert_message_services, // title
                                   alert_gallery_camera_cancel_services
                                   );
}
function onConfirmDir(buttonIndex) {
    if (buttonIndex == 2)
    {
        var options = {
        quality: 50,
        sourceType: Camera.PictureSourceType.PHOTOLIBRARY,
        destinationType: navigator.camera.DestinationType.FILE_URI,
        }
        navigator.camera.getPicture(librarySuccessDir, libraryErrorDir, options);
    } else if (buttonIndex == 1)
    {
        navigator.device.capture.captureImage(captureSuccessDirectory, captureErrorDirectory, options);
    }
    else
    {
        
    }
}


function removeImageData(idOfSL)
{
    $('#ulSL'+idOfSL).remove();
    var imgData= sessionStorage.getItem('imageData');
    imageArraySubmitList.splice( imageArraySubmitList.indexOf(imgData), 1 );
    sessionStorage.removeItem('imageData');
  
    
//       $('#imageSL'+idOfSL).attr('src','images/hyper-local/no-image.png');
//       $('#imageSL'+idOfSL).remove();
//       sessionStorage.removeItem('imageData');
//       $('#imageSL'+idOfSL).attr('src','images/hyper-local/no-image.png');
}
function removeVideoData()
{
    document.getElementById('youtubeUrl').value='';
}
function removeAudioData()
{
    document.getElementById('audioUrlData').value='';
}

function librarySuccessDir(imageURI)
{
    var imageData = imageURI;
    sessionStorage.setItem('imageData',imageData);
    imageArraySubmitList[parseInt(imgIndexSl)]=imageData;
    var imgId= sessionStorage.getItem("imgId");
    $('#'+imgId).attr('src',imageData);
    setTimeout(function(){ window.location="hidestatusbar:";},100);
}

function libraryErrorDir()
{
    setTimeout(function(){ window.location="hidestatusbar:";},100);
}

function captureErrorDirectory()
{
    setTimeout(function(){ window.location="hidestatusbar:";},100);
}

function captureSuccessDirectory(mediaFiles)
{
    setTimeout(function(){ window.location="hidestatusbar:";},100);
	localStorage.local_imageURI = mediaFiles[0].fullPath;
    mediaFiles = goToNativeForRotation(mediaFiles[0].fullPath);	
    localStorage.local_imageURI = mediaFiles;
	imageArraySubmitList[parseInt(imgIndexSl)]=mediaFiles;
	 var imgId= sessionStorage.getItem("imgId");
	 $('#'+imgId).attr('src',mediaFiles);
     sessionStorage.setItem('imageData',mediaFiles);
}

function successFormSubmitDir()
{
    navigator.notification.alert(
                                 alert_listing_submit_success_review_services,
                                 alertDismissed,
                                 alert_success_services,
                                 ok_dir);
								 //**Start change by dheeraj**//
    if( sessionStorage.getItem("isfromUpdate")=="false")
    {
        submitListing(catIdArray,catNameArray);
    }
//**End change by dheeraj**//
    sessionStorage.setItem('pageLevel',3);
    onBackKeyDown();
    
     $('.appypie-loader').hide();
}


function errorFormSubmitDir()
{
    navigator.notification.alert(
                                    alert_error_submitting_listing_services,
                                 alertDismissed,
                                 alert_error_services,
                                 ok_dir);
     $('.appypie-loader').hide();
    
    return false;
}


function showDataReview(pageIndex,pageid)
{    
     localStorage.setItem("listIdForRR",pageid);
      pageIndex=parseInt(pageIndex)+1;
    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/directory-soap#directoryReviewRatingXml";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><directoryReviewRatingXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/directory-soap#directoryReviewRatingXml\"><appId>'+localStorage.getItem("applicationID")+'</appId><dirId>'+pageid+'</dirId></directoryReviewRatingXml></soap:Body></soap:Envelope>';
    console.log("Soap xza()directoryReviewRatingXml -->"+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           var directoryReviewRatingData=$(req.responseText).find("return").text();
           ratingAndreview(pageIndex,directoryReviewRatingData);
           },
           error: function(response, textStatus, errorThrown)
           {
           alertPopUp(alert_oops_services,alert_server_not_found_try_later_services);
           console.log(errorThrown.responseText);
           }
           });
   
}

function directoryAvgReviewRatingXml()
{
    var pageid=localStorage.getItem("listIdForRR");
    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/directory-soap#directoryAvgReviewRatingXml";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><directoryAvgReviewRatingXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/directory-soap#directoryAvgReviewRatingXml\"><appId>'+localStorage.getItem("applicationID")+'</appId><dirId>'+pageid+'</dirId></directoryAvgReviewRatingXml></soap:Body></soap:Envelope>';
    console.log("Soap xza()directoryAvgReviewRatingXml -->"+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           var resdata=$(req.responseText).find("return").text();
           console.log("xml==="+resdata);
           
             var xml=jQuery.parseXML(resdata);
           $(xml).find("avgReviewRating").each(function () {
                                                var avgReview= $(this).find("avgReview").text();
                                                var totalReview= $(this).find("totalReview").text();
                                                var avgReviewinnumber=parseFloat(avgReview)/10;
                                                document.getElementById('rrid').style.width=avgReview+"%";
                                                document.getElementById('rrid2').innerHTML='User Rating &amp; Review :'+ avgReviewinnumber+' Rating out of 5';
                                               
                                            });
       
           },
           error: function(response, textStatus, errorThrown)
           {
           alertPopUp(alert_oops_services,alert_server_not_found_try_later_services);
           console.log(errorThrown.responseText);
           }
           });
    
    
}



function menuHide()
{

    $(".hyperlocal-menu").css("top", "0").show().animate({"top":"-100%"}, "fast", function(){
                                                         $(this).hide();
                                                         });
}


function searchLocationService() {

    $("header .locate").click(function(){
                              $(".location-popup").css("top", "-100%").show().animate({
                                                                                      top:"0"
                                                                                      }, "slow");
                              });

}

/*
function txtSearchFunction()
{
    var searchText=document.getElementById('txtSearch').value;
    if(searchText.trim()!="")
    {
    var filterType='textSearch';
    filterSearch(filterType,0,0,0,0,0)
    }
}
*/
function txtSearchFunction(e, o)
{
	var mEvent = e || window.event;
	var mPressed = mEvent.keyCode || mEvent.which;
	if (mPressed == 13) {
		var searchText=document.getElementById('txtSearch').value;   
		if(searchText.trim()!="")
		{
			var filterType='textSearch';
			filterSearch(filterType,0,0,0,0,0)
		}    
	} 
}
function checkincount(listId,categoryId,dirPageId,mode,obj){
	
	console.log("checkincount==>amritesh==>"+listId);
	console.log("categoryId==>amritesh==>"+categoryId);
	console.log("dirPageId==>amritesh==>"+dirPageId);
	console.log("mode==>amritesh==>"+mode);
	console.log("obj==>amritesh==>"+obj);
	
	if(obj != null)
	{
		if($(obj).is(".on"))
		{
			return false;
		}
		$(obj).addClass("on")
	}
	
	
	  console.log("checkincount==>amritesh==>"+listId);
    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/directory-soap#checkOnListing";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><checkOnListing xmlns=\"http://'+localStorage.getItem("reseller")+'/services/directory-soap#checkOnListing\"><listId>'+listId+'</listId><appId>'+localStorage.getItem("applicationID")+'</appId><catId>'+categoryId+'</catId><pageId>'+dirPageId+'</pageId><type>'+mode+'</type></checkOnListing></soap:Body></soap:Envelope>';
   
    console.log("checkincount==>amritesh==>"+soapRequest);
    jQuery.support.cors = true;
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           
           var strJSON = $(req.responseText).find("return").text();
		   $("#checkintotalcounts").text(strJSON);
           //document.getElementById("checkintotalcounts").innerText =strJSON ;
           
			console.log("amritesh "+strJSON);

           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
     
	
}
function showServicePageMapNew(latArr,longArr,indexArr,addArr,AppName,pageCount,isFromDirectory)
{
 
      if(mapThirdPartyFlag==1){
		console.log("mapThirdPartyFlag true");
		window.location="maplocation:"+latArr+"&&"+longArr+"&&"+addArr;
	  }
	  else
{
	var currentLat =	sessionStorage.getItem("localLatitude");
    var currentLong=    sessionStorage.getItem("localLongitude");	
	
	   nativeMultipleLocStr="maplocationdir:"+latArr+"%%%"+longArr+"%%%"+indexArr+"%%%"+addArr+"%%%"+localStorage.getItem("AppName")+"%%%"+pageCount+"%%%"+currentLat+"%%%"+currentLong;	
	   toaster.showDirectoryOnMap(nativeMultipleLocStr);
	  }
 
   }
function openRequestForm(pageid,categoryId,dirPageId,AppendPageNo,heading)
{
    var requestHtml='<header class="hyper-local header-main">\
    <h1>'+serviceHeaderName+'</h1>\
    <a class="hyper-back" href="#" onclick="onBackKeyDown();"></a>\
    </header>\
    <section class="hyper-local request-service">\
    <div class="side-margin">\
    <big class="hyper-big-ttle">'+request_a_service+'</big>\
    <small class="hyper-big-sub-title">'+Submit_your_request_to_providers+'</small>\
    <div class="bdr-devider"></div>\
    <ul class="hyper-request-form">\
    <li class="user">\
    <strong>'+name_Dir+'</strong>\
    <span><input type="text" placeholder="'+enter_your_name+'" id="rsName"></span>\
    </li>\
    <li class="phone">\
    <strong>'+phone_Dir+'</strong>\
    <span><input type="text" placeholder="'+Enter_your_phone_number+'" id="rsPhone"></span>\
    </li>\
    <li class="locate"><strong>'+address_dir+'</strong>\
    <span><input type="text" placeholder="Enter your postal/zip code or full address" id="rsAddress"></span>\
    </li>\
    <li class="budget"><strong>'+What_is_your_budget+'</strong>\
    <span><input type="text" placeholder="'+Enter_your_budget+'" id="rsBudget"></span>\
    </li>\
    <li class="msg"><strong>'+What_is_your_requirement+'</strong>\
    <span><textarea placeholder="'+Please_describe_your_request+'" id="rsRequirement"></textarea></span>\
    <span>\
    <p class="pink" id="rsAttach" onclick="selectFile(this)" >Attach photos</p></span>\
    </li>\
    </ul>\
    </div>\
    <div class="hyper-gray-wrapper">\
    <button class="hyper-sent-btn" onclick="insertListFormBulder(\''+pageid+'\',\''+categoryId+'\',\''+dirPageId+'\',\''+heading+'\')" >'+send_request+'</button>\
    </div>\
    </section>';
    AppendPageNo=parseInt(AppendPageNo)+1;
    appendHtml(requestHtml,AppendPageNo,AppendPageNo);
    resetDirectoryHeader();
    console.log("requestHtm===="+requestHtml);
   
}
/*
 Sign in Sign up
 */
function signInSignUp()
{
   var  service_home = '<div class="login"><div class="login_box">\
    <div class="login_from">\
    <input type="button" class="button" value="Create Account" onclick="serviceSignUpView()">\
    <input type="button" class="button blue" value="Sign IN"  onclick="loginService()">\
    </div>\
    </div></div>';
    appendHtml(service_home,'',2);
	resetDirectoryHeader();
}

function loginService()
{
    
    if((window.localStorage.getItem("isLogin")!=null)&&(window.localStorage.getItem("isLogin")!='null'))
    {
     navigator.notification.confirm(alert_want_logout_services, logoutCallBack, logout_services, cancel_yes_services);
    
    
    }
    else
    {
    menuHide();
    
    
    var headerHTML = ' <header class="hyper-local header-main " style=" position:fixed; left:0; top:0">  <a class="hyper-back" href="#" onclick="onBackKeyDown();"></a>\ <h1>'+serviceHeaderName+'</h1> </header>';
    
    var loginHtml = "";
    loginHtml += headerHTML+'<div class="login"><div class="login_box">\
    <div class="login_from">\
    <div class="cart-MSG" id="cart-MSG" style="display:none"><div class="cart_error2">Invalid login id or password.</div></div>\
    <input data-role="none" type="text" id="loginid" class="mail" placeholder="'+email_dir+'"  />\
    <input data-role="none" type="password" id="loginpass" class="pass" placeholder="Password" />\
    <input type="button" class="button" value="LOGIN" onclick="checkServiceUserLogin()">\
    <div class="links-view">\
    <a href="#" class="f-left" onclick="serviceForgotPassView()">Forgot password?</a>\
    <a href="#" class="f-right" onclick="serviceSignUpView()">Sign up</a>\
    </div>\
    </div>\
    </div></div>'
    appendHtml(loginHtml,2,2);
    resetDirectoryHeader();
    }
    // loginHtml += '<div class="login">'+headerHTML+'<div class="login_box">\
}

function logoutCallBack(button)
{
    if(button == 2) {
        localStorage.setItem("ecomuserid",null);
        sessionStorage.setItem("ecomlogincheck",null);
        localStorage.setItem("fooduserid",null);
        sessionStorage.setItem("foodlogincheck",null);
        localStorage.setItem("foodEcomUserId",null);
        window.localStorage.setItem("isLogin",null);
        localStorage.setItem("local_imageURI","images/hyper-local/vandor-pic.png");
        localStorage.setItem("userName","");
        localStorage.setItem("userLocation","");
        menuHide();
    }
}

function serviceSignUpView() {
    var headerHTML = ' <header class="hyper-local header-main ">  <a class="hyper-back" href="#" onclick="onBackKeyDown();"></a>\ <h1>'+serviceHeaderName+'</h1> </header>';
    $('.appypie-loader').show();
    ecomsignup_HTML='<div class="login">'+headerHTML+'<div class="login_box"><div class="cart-MSG" id="cart-MSG" style="display:none"><div class="cart_error"></div></div>\
    <div class="cart-MSG" id="cart-MSG2" style="display:none"><div class="cart_success2"></div></div>\
    <div class="login_from sign-up"><input data-role="none" type="text" id="fname" placeholder="Name *" />\
    <input data-role="none" type="text" id="emailId" placeholder="'+email_dir+' *" />\
    <input data-role="none" type="number" id="pNo" placeholder="'+phone_Dir+'" />\
    <input data-role="none" type="password" id="pass" placeholder="Password" />\
    <input data-role="none" type="password" id="cpass" placeholder="Confirm Password" />\
    <input type="button" class="button" value="Sign Up" onclick="customerRegistrationService()">\
    <div class="links-view"><p>Already have an account? <a onclick="loginService()">Sign In</a></p></div></div></div></div>';
    appendHtml(ecomsignup_HTML ,3,3);
	resetDirectoryHeader();
}
function serviceForgotPassView() {
    $('.appypie-loader').show();
       var headerHTML = ' <header class="hyper-local header-main">  <a class="hyper-back" href="#" onclick="onBackKeyDown();"></a>\ <h1>'+serviceHeaderName+'</h1> </header>';
    ecomforgotpass_HTML='<div class="login">'+headerHTML+'<div class="login_box">\
    <div class="cart-MSG" id="passwordError" style="display:none"><div class="cart_error"></div></div>\
    <div class="cart-MSG" id="cart-MSG1-forgot" style="display:none"><div class="cart_success">We have sent an email to your email id</div></div>\
    <div class="login_from forgot-password">\
    <input data-role="none" type="text" id="forpasid" placeholder="Enter your email" />\
    <input type="button" class="button" value="Reset Password"  onclick="sendPasswordService()">\
    <div class="links-view">\
    <p>Do not have an account? <a onclick="serviceSignUpView()">Sign up Now</a></p>\
    <p>Already have an account? <a onclick="loginService()">Sign In</a></p>\
    </div>\
    </div></div></div>';
    appendHtml(ecomforgotpass_HTML ,3,3);
    resetDirectoryHeader();
}
/************Signup*************/
function customerRegistrationService()
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
            setTimeout(function(){$('.appypie-loader').hide();},1000);
            navigator.notification.alert(enter_name_services, setTimeout, alert_error_services, alert_ok_services);
            $("#fname").focus();
            return;
        } else if(emailId=='' || emailId==undefined){
            setTimeout(function(){$('.appypie-loader').hide();},1000);
            navigator.notification.alert(enter_emailid_services, setTimeout, alert_error_services, alert_ok_services);
            $("#emailId").focus();
            
        }
        else if(!checkEmail(emailId) || emailId=='')
        {
            setTimeout(function(){$('.appypie-loader').hide();},1000);
            navigator.notification.alert(enter_valid_email_services, setTimeout, alert_error_services, alert_ok_services);
            $("#emailId").focus();
            return;
            
        }
       /* else if(pNo=='' || pNo=='Phone Number' )
        {
            
            setTimeout(function(){$('.appypie-loader').hide();},1000);
            navigator.notification.alert(enter_phone_no_services, setTimeout, alert_error_services, alert_ok_services);
            $("#pNo").focus();
            return;
        }
		*/
        else if(isNaN(pNo))
        {
            setTimeout(function(){$('.appypie-loader').hide();},1000);
            navigator.notification.alert(enter_valid_phoneno_services   , setTimeout, alert_error_services, alert_ok_services);
            $("#pNo").focus();
            return;
        }
        else if(pass==''  || pass=='Password')
        {
            
            setTimeout(function(){$('.appypie-loader').hide();},1000);
            navigator.notification.alert(enter_password_services, setTimeout, alert_error_services, alert_ok_services);
            $("#pass").focus();
            return;
        }
        else if(cpass!=pass)
        {
            
            setTimeout(function(){$('.appypie-loader').hide();},1000);
            navigator.notification.alert(password_mismatch_services, setTimeout, alert_error_services, alert_ok_services);
            $("#cpass").focus();
            
            return;
        }
        else
        {
            customerRegistration(fname,pNo,emailId,pass);
            
        }
    }
}

function checkEmail(email) {
    
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return filter.test(email);
}

function customerRegistration(fname,pNo,emailId,pass)
{
      $('.appypie-loader').show();
    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/app-user-soap#appUserRegistration";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><appUserRegistration xmlns=\"http://'+localStorage.getItem("reseller")+'/services/app-user-soap#appUserRegistration\"><appid>'+localStorage.getItem("applicationID")+'</appid><name>'+fname+'</name><email>'+emailId+'</email><password>'+pass+'</password><phone>'+pNo+'</phone><company></company><formData></formData><formFields></formFields><formLabel></formLabel><authoStatus>0</authoStatus><profileId></profileId></appUserRegistration></soap:Body></soap:Envelope>';
   
    console.log("appUserRegistration===="+soapRequest);
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
			if(strJSON=="true")
			{
				                alertPopUp(alert_sign_alert_services,user_register_successfully_services);
                setTimeout( function(){
                      loginService();
                      
                      }, 2000 );
			}else if(strJSON=="User already registered"){
				  alertPopUp(alert_sign_alert_services,user_already_register_services);
			}
			else
			{
           var obj = JSON.parse(strJSON);
           if(obj)
           {
             if(obj.msg=="true")
                {
                alertPopUp(alert_sign_alert_services,user_register_successfully_services);
                setTimeout( function(){
                      loginService();
                      
                      }, 2000 );
                }
               else
               {
                 alertPopUp(alert_sign_alert_services,user_already_register_services);
               }
           
           }
           else
           {
             alertPopUp(alert_sign_alert_services,user_already_register_services);
           
           }
			}
         

           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
     
}
/********Login Page*************/
function checkServiceUserLogin()
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
            setTimeout(function(){$('.appypie-loader').hide();},1000);
            navigator.notification.alert(enter_emailid_services, setTimeout, alert_error_services, alert_ok_services);
            $("#loginid").focus();
            return;
        }
        else if(!checkEmail(emailid) || emailid=='')
        {
            setTimeout(function(){$('.appypie-loader').hide();},1000);
            navigator.notification.alert(invalid_email_address_services, setTimeout, alert_error_services, alert_ok_services);
            $("#loginid").focus();
            return;
        }
        
        else if(pass==''  || pass=='Password')
        {
            setTimeout(function(){$('.appypie-loader').hide();},1000);
            navigator.notification.alert(enter_password_services    , setTimeout, alert_error_services, alert_ok_services);
            $("#loginpass").focus();
            return;
        }
        else
        {
            checkServicelogin(emailid,pass);
        }
    }
}
function checkServicelogin(emailid,pass)
{
    if(!checkNetworkConnection())
    {
    }else
    {
        $('.appypie-loader').show();
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/app-user-soap#checkUserActiveStatusNew";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><checkUserActiveStatusNew xmlns=\"http://'+localStorage.getItem("reseller")+'/services/app-user-soap#checkUserActiveStatusNew\"><email>'+emailid+'</email><password>'+pass+'</password><appId>'+localStorage.getItem("applicationID")+'</appId><authoStatus></authoStatus><tokenId></tokenId><deviceType></deviceType><profileId></profileId></checkUserActiveStatusNew></soap:Body></soap:Envelope>';
        
          console.log("checkUserActiveStatusNewsoapRequest======>>"+soapRequest);
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
               var strJSON = $(req.responseText).find("return").text();
                console.log("checkUserActiveStatusNew======>>"+req.responseText);
         
               if(strJSON!='Invalid username or password')
               {
               
               
               var obj = JSON.parse(strJSON);
               var emailid=obj.email;
               var name=obj.name;
               var address=obj.address;
               var profileImage=obj.profileImage;

   window.localStorage.setItem("loginemail",emailid);
           	window.localStorage.setItem("loginpassword",pass);
               

               
               localStorage.setItem("ecomuserid",emailid);
               sessionStorage.setItem("ecomlogincheck","1");
               localStorage.setItem("fooduserid",emailid);
               sessionStorage.setItem("foodlogincheck","1");
              
               localStorage.setItem("isLogin",emailid);
                  localStorage.setItem("userName",name);

               if(profileImage=="")
               {
               localStorage.setItem("local_imageURI","images/hyper-local/vandor-pic.png");
               }
               else
               {
               console.log("profileImage======"+profileImage);
               localStorage.setItem("local_imageURI",profileImage);
               }
               
            
               if(address=="")
               {
                localStorage.setItem("userLocation","My Location");
               }
               else
               {
               localStorage.setItem("userLocation",address);
               }

              
               if((localStorage.getItem("userName")==null)||(localStorage.getItem("userName")=='null'))
               {
               
               document.getElementById('userName').innerHTML="Profile Name";
               }
               else
               {
               
               document.getElementById('userName').innerHTML=localStorage.getItem("userName");
               
               }
               
               if((localStorage.getItem("userLocation")==null)||(localStorage.getItem("userLocation")=='null'))
               {
               
               document.getElementById('userLocation').innerHTML="My Location";
               }
               else
               {
               
               document.getElementById('userLocation').innerHTML=localStorage.getItem("userLocation");
               
               }
               
			   /********* Change by geeta *******/
                if(localStorage.getItem('reviewandpost')=='greview')
               {
               //alert('ha ha');
               localStorage.removeItem('reviewandpost');
               showDataReview('3','991241')
               }
               else if(localStorage.getItem('storeaddsubmitlisting')=='glisting')
               {
               localStorage.removeItem('storeaddsubmitlisting');
                getDirectoryCatIdForListingAddUpdate(0)
               }
               else if(localStorage.getItem('storeaddupdatelisting')=='gupdatelisting')
                              {
                              localStorage.removeItem('storeaddupdatelisting');
                               getDirectoryCatIdForListingAddUpdate(1)
                              }
               else{
                // getService(mainServiceIndex);
				 getDirectoryCat();
               }
               
               /********* Change by geeta *******/
               
               
               }
               else
               {
                  alertPopUp(alert_sign_alert_services,theusernamepasswordenteredincorrect_services);
               }
                    setTimeout(function(){$('.appypie-loader').hide();},1000);
               },
               error: function(response, textStatus, errorThrown)
               {
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   console.log("Error:"+response);
               }
               });
    }
}

/**********Send Password*********/
function sendPasswordService()
{
    if(!checkNetworkConnection())
    {
    }else
    {
        $('.appypie-loader').show();
        var emailid=document.getElementById("forpasid").value;
        if(!checkEmail(emailid) || emailid=='')
        {
            alertPopUp('Alert !','Please Enter Valid Email Id');
            setTimeout(function(){$('.appypie-loader').hide();},1000);
            
        }
        else
        {
            var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/app-user-soap#forgetPassword";
            var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><forgetPassword xmlns=\"http://'+localStorage.getItem("reseller")+'/services/app-user-soap#forgetPassword\"><appId>'+localStorage.getItem("applicationID")+'</appId><email>'+emailid+'</email></forgetPassword></soap:Body></soap:Envelope>';
            
            
            console.log("forgetPassword===="+soapRequest);
            $.ajax({
                   type: "POST",
                   url: wsUrl,
                   contentType: "text/xml",
                   dataType: "text",
                   data: soapRequest,
                   success: function(data, status, req)
                   {
                   var strJSON = $(req.responseText).find("return").text();
                   if(strJSON!='NotExist')
                   {
                   alertPopUp(alert_sign_alert_services,password_reset_success_check_email_services);
                   }
                   else
                   {
                   
                    alertPopUp(alert_sign_alert_services,you_not_register_with_yet_services);
                  
                   setTimeout( function(){
                              $("#forpasid").focus();
                              }
                              , 3000 );
                  
                   }
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   },
                   error: function(response, textStatus, errorThrown)
                   {
                   console.log("Error==="+response);
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   }
                   });
        }
    }
}
/************Signup*************/

function insertListFormBulder(pageId,categoryId,dirPageId,heading)
{
    if(checkNetworkConnection())
    {
        var ownerEmailId=masterData.getElementsByTagName('owneremail')[0].firstChild.nodeValue;
        var name=document.getElementById("rsName").value;
        var phone=document.getElementById("rsPhone").value;
        var address=document.getElementById("rsAddress").value;
        var budget=document.getElementById("rsBudget").value;
        var requirement=document.getElementById("rsRequirement").value;
        var imageName=document.getElementById("rsAttach").innerHTML;
       var globalFileName="";
        if(name.trim()=='')
        {
             $("#rsName").focus();
            alertPopUp(alert_sign_alert_services,enter_name_services);
        }
        else if(phone.trim()==''||phone.length<8)
        {
               $("#rsPhone").focus();
            alertPopUp(alert_sign_alert_services,enter_valid_phoneno_services);
        }
        else if(address.trim()=='')
        {
            $("#rsAddress").focus();
            alertPopUp(alert_sign_alert_services,enter_valid_address_services);
        }
        else if(budget.trim()=='')
        {
            $("#rsBudget").focus();
            alertPopUp(alert_sign_alert_services,enter_budget_services);
        }
        else if(requirement.trim()=='')
        {
            $("#rsRequirement").focus();
            alertPopUp(alert_sign_alert_services,enter_requirement_services);
        }
        else
        {
           $('.appypie-loader').show();
            
            var appName = localStorage.getItem("AppName");
            var allvariable="requestservice:"+localStorage.getItem('applicationID')+"$$@@$$"+ownerEmailId+"$$@@$$"+pageId+"$$@@$$"+categoryId+"$$@@$$"+dirPageId+"$$@@$$"+name+"$$@@$$"+phone+"$$@@$$"+address+"$$@@$$"+budget+"$$@@$$"+requirement+"$$@@$$"+globalFileName+"$$@@$$"+globalimageurl+"$$@@$$"+appName+"$$@@$$"+heading    ;
            
            
          setTimeout(function()
		  { 				
			toaster.SaverequestserviceDirecotory(localStorage.getItem('applicationID'),ownerEmailId,pageId,categoryId,dirPageId,name,phone,address,budget,requirement,globalFileName,globalimageurl,appName,heading);
		  },1000);
             $('.appypie-loader').show();
       }
    }
}
function sendSuccessServiceRequest(isSubmit)
{
 $('.appypie-loader').hide();
    if(isSubmit=="success")
    {
        document.getElementById("rsName").value="";
        document.getElementById("rsPhone").value="";
        document.getElementById("rsAddress").value="";
        document.getElementById("rsBudget").value="";
        document.getElementById("rsRequirement").value="";
        document.getElementById("rsAttach").innerHTML="Attach photos";
        globalFileName="";
        globalimageurl="";
        alertPopUp(alert_thanks_services,Your_submited_request_send_successfuly);
    }
    else
    {
        alertPopUp(alert_sign_alert_services,request_not_submit_try_again_services);
    }
}
function filterDirectory(isFrom,AppendPageNo)
{
    AppendPageNo=parseInt(AppendPageNo);
    var disSliderActive='<li class="distance"><span></span>'+distance_dir+'</li>';
    var starSliderActive=' <li class="rating active"><span></span>'+rating_dir+'</li>';
    var displayStarBlock='block';
     var displayDistBlock='none';
    if(sessionStorage.getItem("serivceisLocationSearch")=="true")
    {
        displayDistBlock='block';
        displayStarBlock='none';
        disSliderActive='<li class="distance active"><span></span>'+distance_dir+'</li>';
        starSliderActive='<li class="rating"><span></span>'+rating_dir+'</li>';
    }
    var distabSlider='<a href="#" >KM</a><a href="#" class="active">MI</a>';
    var distabSliderVal= '<span class="min">0MI</span><span class="max">186MI</span>';
    if(defaultDistance=="km")
    {
        distabSlider='<a href="#" class="active">KM</a><a href="#" >MI</a>';
        distabSliderVal= '<span class="min">0KM</span><span class="max">482KM</span>';
    }
    
    var slidervalue=disSliderActive+starSliderActive;
var filterHtml='<header class="hyper-local header-main">\
    <h1>'+Filter+'</h1>\<a class="hyper-back" href="#" onclick="onBackKeyDown()"></a>\
    </header>\
    <section class="hyper-local filter-page">\
    <div class="filter-row short-by">\
    <h3>'+Short_By+'</h3>\
    <ul>'+slidervalue+'</ul>\
    </div>\
    <div class="filter-row" id="ds" style="display:'+displayDistBlock+'">\
    <h3>'+distance_dir+'</h3>\
    <div id="distance-slider"  > '+distabSliderVal+'<div></div>\
    </div>\
    <div id="distance-slider-tab">'+distabSlider+'</div>\
    </div>\
    <div class="filter-row" id="dr" style="display:'+displayStarBlock+'">\
    <h3>'+rating_dir+'</h3>\
    <div id="rating-list" >\
    <input type="hidden" id="ratingData">\
    <div><span class="rating" dir="5"></span> <span class="checkbox"><input type="checkbox" value="5"></span></div>\
    <div><span class="rating" dir="4"></span> <span class="checkbox"><input type="checkbox" value="4"></span></div>\
    <div><span class="rating" dir="3"></span> <span class="checkbox"><input type="checkbox" value="3"></span></div>\
    <div><span class="rating" dir="2"></span> <span class="checkbox"><input type="checkbox" value="2"></span></div>\
    <div><span class="rating" dir="1"></span> <span class="checkbox"><input type="checkbox" value="1"></span></div>\
    </div>\
    </div>\
    <div class="hyper-gray-wrapper">\
    <button class="hyper-sent-btn" onclick="filterSearchClick(\''+isFrom+'\')" >'+search_button_dir+'</button>\
    </div>\
    </section>';
    
    localStorage.setItem("isFrom","");
    if(isFrom=="internalSearch")
    {
        localStorage.setItem("isFrom","internalSearch");
        appendHtml(filterHtml,AppendPageNo,AppendPageNo);
		resetDirectoryHeader();
    }
    else
    {
		appendHtml(filterHtml,AppendPageNo,AppendPageNo);
		resetDirectoryHeader();
    }
	
    if(sessionStorage.getItem("serivceisLocationSearch")=="true"&&sessionStorage.getItem("reviewSetting")=="true")
    {
      filterType = filter_type_services;
    }
    else if(sessionStorage.getItem("reviewSetting")=="true")
    {
    
     filterType = filter_type_ratting_services;
    }
    else
    {
         filterType = filter_type_services;
        
    }
      setTimeout(function(){
                 
                  distanceUnits = $("#distance-slider-tab a.active").text();
                 
                     $(".short-by li").click(function(){
                                    if(sessionStorage.getItem("serivceisLocationSearch")=="true"&&sessionStorage.getItem("reviewSetting")=="true")
                                    {
                                        $(".short-by li").removeClass("active");
                                        $(this).addClass("active");
                                        filterType=$(this).text();

                                        if(filterType=="Distance")
                                        {

											$("#ds").show();
											$("#dr").hide();
                                        }
                                        else
                                        {

											$("#dr").show();
											$("#ds").hide();
                                        }
                                    }
                                 return false;
                      });
                 		var rangeData = "min";
						if($("body").is(".sa"))
						{
							rangeData = "max";
						}
                      $( "#distance-slider div" ).slider({
                                                         
                                                         range: rangeData,
                                                         value: 300,
                                                         max: 500,
                                                         slide: function( event, ui ) {
                                                         setDistanceSlider(ui.value);   
                                                         }
                                                         });
                 
                 
                 function setDistanceSlider(val)
                 {
                 if(distanceUnits=="MI")
                 {
                 val=val/1.609344;
                 }
                 else
                 {
                 
                  val=val*1.609344;
                 }
                 $( "#distance-slider .min").text("0" + distanceUnits);
                 $( "#distance-slider .max").text(parseInt(val) + distanceUnits);
                 }
                 setDistanceSlider(300);
                      $( "#price-range-slider .min").text("$75");
                      $( "#price-range-slider .max").text("$300");
                      $( "#price-range-slider div" ).slider({
                                                            range: true,
                                                            min: 0,
                                                            max: 500,
                                                            values: [ 75, 300 ],
                                                            slide: function( event, ui ) {			
                                                            $( "#price-range-slider .min").text("$" + ui.values[ 0 ])
                                                            $( "#price-range-slider .max").text("$" +  ui.values[ 1 ])			
                                                            }
                                                            });
               
                  $("#distance-slider-tab a").click(function()
                                                        {
                                                         $("#distance-slider-tab a").removeClass("active");
                                                         $(this).addClass("active");
                                                         distanceUnits = $(this).text();
                                                        var value = $( "#distance-slider div" ).slider( "option", "value" );
                                                         setDistanceSlider(value);
                                                         return false;
                                                        });

               
                 
                 $("#rating-list").find("span.checkbox").click(function(){
                                                               if($(this).is(".checked"))
                                                               {
                                                               $(this).find("input").prop('checked', false)
                                                               $(this).removeClass("checked")
                                                               }
                                                               else
                                                               {
                                                               $(this).find("input").prop('checked', true)				
                                                               $(this).addClass("checked")
                                                               }
                                                  
                                                               });
                },1000);

}
function filterSearchClick(isFrom)
{
    minDist="";
    maxDist="";
    var selChekId="";
    var rCountWithComma="";
    sessionStorage.setItem("maxDist","");
    sessionStorage.setItem("ratingFromSearch","");
    if(filterType=="Distance")
    {
        minDist=0;
        maxDist =$( "#distance-slider .max").text().replace("KM","").replace("MI","");
        console.log("filterType=="+filterType+",minDist==="+minDist+",maxDist==="+maxDist+",distanceUnits=="+distanceUnits+",selChekId==="+selChekId);
        if(isFrom=="internalSearch")
        {
            var catId=sessionStorage.getItem('dirCatId');
            var AppendPageNo= sessionStorage.getItem("AppendPageNo");
            createDirSubDetailService(catId,AppendPageNo,"internalSearch");
            sessionStorage.setItem("maxDist",maxDist);
            sessionStorage.setItem("distanceUnits",distanceUnits);
        }
        else
        {
      
        filterSearch(Filter_search_distance_services,minDist,maxDist,distanceUnits,selChekId,rCountWithComma);
        }
    }
    else
    {
       rCountWithComma=$("#ratingData").val($("#rating-list").find("span.checked input").map(function() {
                                                                              return $( this ).val();
                                                                              }).get().join(",")).val();
        console.log("filterType=="+filterType+",minDist==="+minDist+",maxDist==="+maxDist+",distanceUnits=="+distanceUnits+",selChekId==="+selChekId+"rCountWithComma==="+rCountWithComma);
        if(isFrom=="internalSearch")
        {
            if(rCountWithComma=="")
            {
                var message=select_ratting_services;
                navigator.notification.alert(
                                             message,
                                             alertDismissed,
                                             alert_services,
                                             alert_ok_services
                                             );
            }
            else
            {
                var catId=sessionStorage.getItem('dirCatId');
                 var AppendPageNo= sessionStorage.getItem("AppendPageNo");
                createDirSubDetailService(catId,AppendPageNo,"internalSearch");
                sessionStorage.setItem("ratingFromSearch",rCountWithComma);
            }
        }
        else
        {
        
        filterSearch("ratting",minDist,maxDist,distanceUnits,selChekId,rCountWithComma);
        }
    }
}

function filterSearch(filterType,minDist,maxDist,distanceUnits,selChekId,rCountWithComma,lat,long)
{
    if(checkNetworkConnection('true'))
    {
        
        if(sessionStorage.getItem("localLatitude") && sessionStorage.getItem("localLongitude") && checkNetworkConnection())
        {
            

        }
        else
        {
            //getLocalCords();
            setTimeout(function(){$('.appypie-loader').hide();},1000);
        }

        var dirPageId=sessionStorage.getItem('dirPageId');
        var count=3000;
        var pageCount=1;
        var latitude=sessionStorage.getItem("localLatitude") ;
        var longitude=sessionStorage.getItem("localLongitude");
      
        var mileKm=distanceUnits;
        var rating=rCountWithComma;
        var catId="";
        var minRange=0;
        var maxRange=maxDist;
        var searchText="";
        if(filterType=="textSearchMenu")
        {
              filterType="textSearch";
              searchText = document.getElementById("searchmenu").value;
        }
        else
        {
             searchText=document.getElementById('txtSearch').value;
        }
    
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/directory-soap#getListingSearchXml";
      
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getListingSearchXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/directory-soap#getListingSearchXml\"><serchType>'+filterType+'</serchType><appId>'+localStorage.getItem("applicationID")+'</appId><dirPageId>'+dirPageId+'</dirPageId><count>'+count+'</count><pageNo>'+pageCount+'</pageNo><distanceType>'+distanceUnits+'</distanceType><latitude>'+latitude+'</latitude><longitude>'+longitude+'</longitude><rating>'+rating+'</rating><catId>'+catId+'</catId><minRange>'+minRange+'</minRange><maxRange>'+maxRange+'</maxRange><searchText>'+searchText+'</searchText></getListingSearchXml></soap:Body></soap:Envelope>';
        
        
        console.log("getListingSearchXml()  -->soapRequest --->"+soapRequest);
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               cache: false,
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
               var returnData=$(req.responseText).find("return").text();
               console.log("req.responseText=="+returnData);
               if(returnData != "No Category available")
               {
               
               if(filterType=="ratting")
               {
                   if(returnData=="rating missing")
                   {
                   var message=ratting_missing_services;
                   navigator.notification.alert(
                                                message,  // message
                                                alertDismissed,         // callback
                                                alert_message_services,            // title
                                                alert_ok_services                  // buttonName
                                                );
                   }
                   else
                   {
                       displayDirCat(returnData,pageCount);
                       dirXmlData=returnData;
                       requestedFileSystem.root.getFile('dir_cat_'+sessionStorage.getItem('dirPageId')+'_pageNo'+pageCount+'.xml', {create: true, exclusive: false}, gotFileEntryDir, fail);
                   }
               }
               else
               {
                       displayDirCat(returnData,pageCount);
                       dirXmlData=returnData;
                       requestedFileSystem.root.getFile('dir_cat_'+sessionStorage.getItem('dirPageId')+'_pageNo'+pageCount+'.xml', {create: true, exclusive: false}, gotFileEntryDir, fail);
               
               }
            
               }
               else
               {
               
                 alertPopUp(alert_sign_alert_services,no_categories_avail_services);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               },
               error: function(response, textStatus, errorThrown)
               {
               console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
    }

}

function selectPhoto_Directory() {
    navigator.notification.confirm(
                                   alert_select_from_where_want_services, // message
                                   onConfirm2_directory, // callback to invoke with index of button pressed
                                   alert_message_services, // title
                                   alert_gallery_camera_cancel_services
                                   );
}

function onConfirm2_directory(buttonIndex) {
    if (buttonIndex == 2) {
        var options = {
        quality: 50,
        sourceType: Camera.PictureSourceType.PHOTOLIBRARY,
        destinationType: navigator.camera.DestinationType.FILE_URI,
        }
        navigator.camera.getPicture(win_image_lib_directory, fail_directory, options);
    } else if (buttonIndex == 1)
    {
        Image_capture_directory();
    }
    else
    {
        
    }
}


function fail_directory() {
    setTimeout(function(){ window.location="hidestatusbar:";},100);
    setTimeout(function() {
               
              // checkupdate();
               }, 60000);
}

function win_image_lib_directory(imageURI) {
    //setTimeout(function(){ window.location="hidestatusbar:";},100);
   
  //  imageURI=replaceAllstr(imageURI,"file://","");
   // localStorage.local_imageURI = imageURI;
  
    localStorage.setItem("local_imageURI",imageURI);
    
    show_image_on_box_directory();
}

function Image_capture_directory() {
    
    var options = {
    limit: 1
    };
    navigator.device.capture.captureImage(captureSuccess2_directory, captureError_directory, options);
}

function captureError_directory() {
    setTimeout(function(){ window.location="hidestatusbar:";},100);
    setTimeout(function() {
               
               checkupdate();
               }, 60000);
}

function captureSuccess2_directory(mediaFiles) {
/*    setTimeout(function(){ window.location="hidestatusbar:";},100);
//    localStorage.local_imageURI = mediaFiles[0].fullPath;
//    localStorage.local_imageURI = mediaFiles;
    var imageData = mediaFiles[0].fullPath;
    localStorage.setItem("local_imageURI",imageData);
    show_image_on_box_directory();
*/

 setTimeout(function(){ window.location="hidestatusbar:";},100);	
	localStorage.local_imageURI = mediaFiles[0].fullPath;
    mediaFiles = goToNativeForRotation(mediaFiles[0].fullPath);
    localStorage.local_imageURI = mediaFiles;
    show_image_on_box_directory();
}

function show_image_on_box_directory() {
   // Image_url_wallpost = "";
    
    localStorage.setItem("Image_selection", "Done");
    var mediaFiles =  localStorage.getItem("local_imageURI");

    document.getElementById("profilePicSetting").src = mediaFiles;
    document.getElementById("profilePic").src = mediaFiles;
    //sendImageToServerServices();
    console.log("mediaFiles===="+mediaFiles);
//    $(".appypie-wall-update .wall-pic").hide();
//    $(".appypie-wall-update .chat-pic-1").show();
}
function sendImageToServerServices() {
    $('.appypie-loader').show();
    var mediaFiles= localStorage.getItem("local_imageURI");
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
        params.appId = localStorage.getItem('applicationID');
        params.email = "tt@tt.com";
        params.type='image';
        options.params = params;
        ft.upload(fileURI,encodeURI("http://appypieml.pbodev.info/ecommerce/upload-user-image"), successFn_service_image, errorFnService, options);
    }
}
function successFn_service_image(r)
{
    $('.appypie-loader').hide();
    console.log("Response = " + r.response);
   // alert(r.response);
    if(r.response=='success')
    {
        var message=successfully_updated_services;
        navigator.notification.alert(
                                     message,  // message
                                     alertDismissed,         // callback
                                     alert_message_services,            // title
                                     alert_ok_services                  // buttonName
                                     );
    }
}
function alertDismissed()
{
    $('.appypie-loader').hide();
}

function errorFnService(error) {
    $('.appypie-loader').hide();
    alert("Error===="+error.code);
    console.log("upload error source " + error.source);
    console.log("upload error target " + error.target);
}

function getDirectoryCatIdForListingAddUpdate(isFrom)
{
    //alert(isFrom);
    console.log("isFrom --->"+isFrom);
     catIdArray = [];
    catNameArray = [];
    /**** change by geeta ****/
	localStorage.setItem("storeaddsubmitlisting", "glisting");
    localStorage.setItem("storeaddupdatelisting", "gupdatelisting");
	  /**** change by geeta ****/
	  
    $('.appypie-loader').show();
  
        pageCount=1;
 
    if(checkNetworkConnection('true'))
    {
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/directory-soap#getCategoryListXml";
        console.log("wsUrl---"+wsUrl);
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getCategoryListXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/directory-soap#getCategoryListXml\"><appId>'+localStorage.getItem("applicationID")+'</appId><dirPageId>'+sessionStorage.getItem('dirPageId')+'</dirPageId><count>3000</count><pageNo>'+pageCount+'</pageNo></getCategoryListXml></soap:Body></soap:Envelope>';
        console.log("getDirectoryCat()  -->soapRequest --->"+soapRequest);
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               cache: false,
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
                   var returnData=$(req.responseText).find("return").text();
                   if(returnData != "No Category available")
                   {
                       var xmlData=jQuery.parseXML(returnData);
                       $serviceDataIndex=$(xmlData);
                       $serviceDataIndex.find('catList').each(function()
                                                              {
                                                                  var serviceSubHeader=$(this).find("categoryName").text();
                                                                  var serviceImage=$(this).find("catIcon").text();
                                                              var serviceImagedefault = sessionStorage.getItem("dirDefaultImg");
                                                              console.log("serviceImagedefault=="+serviceImagedefault)
                                                              if( serviceImagedefault!=null)
                                                              {
                                                              if(sessionStorage.getItem("defaultImgEnable") == 1)
                                                              {
                                                              if(serviceImage=="http://angularml.pbodev.info/images/no-image.jpg")
                                                              {
                                                              serviceImage=serviceImagedefault;
                                                              }
                                                              if(serviceImage=="http://snappy.appypie.com/images/no-image.jpg")
                                                              {
                                                              serviceImage=serviceImagedefault;
                                                              }
                                                              }
                                                              }
                                                              
                                                              
                                                              
                                                              
                                                                  var totalDirCat=$(this).find("totalDirCat").text();
                                                                  if(serviceImage.trim()=="")
                                                                  {
                                                                    serviceImage="images/logo.png";
                                                                  }
                                                                  var catId=$(this).find("catId").text();
                                                                  catIdArray.push(catId);
                                                                  catNameArray.push(serviceSubHeader);
                                                              });
                       
                       
                       if(isFrom==0)
                       {
                        submitListing(catIdArray,catNameArray);
                       }
                       else
                       {
                        updateListing(catIdArray,catNameArray);
                       }
                   }
                   else
                   {
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   }
               },
               error: function(response, textStatus, errorThrown)
               {
               console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
    }
}


function ratingAndreview(pageIndex,directoryReviewRatingData)
{
    
    
    var reviewList='';
    if(directoryReviewRatingData=="No review available")
    {
        reviewList=' <li><p class="hyper-comt-txt"><strong>'+no_review_available+'</strong></p></li>';
    }
    else
    {
    var xml=jQuery.parseXML(directoryReviewRatingData);
        
        console.log("reviewRating==="+directoryReviewRatingData);
    $(xml).find("reviewRating").each(function () {
                                     $(xml).find("review").each(function () {
                                                                
                                                                var email= $(this).find("email").text();
                                                                var comments= $(this).find("reviewText").text();
                                                                var rating= $(this).find("rating").text();
                                                                var date= $(this).find("date").text();
                                                                var name= $(this).find("name").text();
                                                                var profileImg= $(this).find("profileImg").text();
                                                                
                                                                reviewList+=  '<li><span class="hyper-comt-pic"><img style="width:50px" src="'+profileImg+'" alt=""/> </span><p class="hyper-comt-txt"><strong>'+name+'</strong>'+comments+'<span class="hyper-comt-rating" rating="'+rating+'"><b>&nbsp;</b></span></p></li>';
                                                                });
                                     });
    }
    
    var ratingReview='<div id="starRating" class="star-adding"></div><script>$( document ).ready(function() {$.fn.raty.defaults.path = "images";$("#starRating").raty({size: 24, starOff: "star-off-big.png", starOn: "star-on-big.png", score: 0});});</script>';
    
    var randrHtml='<header class="hyper-local header-main">\
    <h1>'+user_reviews_ratings_dir+'</h1><a class="hyper-back" href="#" onclick="onBackKeyDown()"></a>\
    </header>\
    <section class="hyper-local review-page">\
    <div class="white-wrapper">\
    <div class="rating-div">'+ratingReview+'</div>\
    <div class="comments-box"><textarea id="commentsRR" ></textarea></div>\
    <button class="hyper-sent-btn" data-role="none"  onclick="insertListingReviewMobile()">'+post_your_ratingreview_dir+'</button>\
    </div>\
    <div class="hyper-review">\
    <ul class="review">'+reviewList+'</ul>\
    </div>\
    </section>';
    
    appendHtml(randrHtml,pageIndex,pageIndex);
	resetDirectoryHeader();
}

function settingProfile()
{
    $(".hyperlocal-menu").hide();
if((localStorage.getItem("isLogin")==null)||(localStorage.getItem("isLogin")=='null'))
{
    loginService();
}else
{
    var mediaFiles= localStorage.getItem("local_imageURI");
       console.log("mediaFiles==="+mediaFiles);
    if((localStorage.getItem("userName")==null)||(localStorage.getItem("userName")=='null'))
    {
           mediaFiles = "images/hyper-local/directory-user.png";
    }
    console.log("mediaFiles==="+mediaFiles);
    var userName="";
    if((localStorage.getItem("userName")!=null)&&(localStorage.getItem("userName")!='null'))
    {
        
         userName=localStorage.getItem("userName");
    }
   
    var userLocation="";
    if((localStorage.getItem("userLocation")!=null)&&(localStorage.getItem("userLocation")!='null'))
    {
        
       userLocation=localStorage.getItem("userLocation");
    }

    localStorage.setItem("local_imageURI_CurrentPic",mediaFiles);
    var settingHtml='<header class="hyper-local header-main">\
    <h1>'+Settings_Dir+'</h1>\<a class="hyper-back" href="#" onclick="onBackKeyDown();setPic();"></a>\
    </header>\
    <section class="hyper-local filter-page setting-page">\
    <div class="user-image-main">\
    <div class="user-image"><img src="'+mediaFiles+'" id="profilePicSetting"  onclick="selectPhoto_Directory();">\
    <div class="edit-btn"><div><img src="images/hyper-local/edit.png" onclick="selectPhoto_Directory();"></div></div>\
    </div>\
    </div>\
    <input id="settingName" type="text" value="'+userName+'" data-role="none">\
    <input type="text"  id="settingLoc" value="'+userLocation+'" data-role="none">\
    </div>\
    </div>\
    <div class="white-wrapper">\
    <button class="hyper-sent-btn"  onclick="saveSettingProfile()" data-role="none">'+save_dir+'</button>\
    </div>\
    </section>';
      appendHtml(settingHtml,2,2);
	  resetDirectoryHeader();
}
}
function setPic()
    {  
        localStorage.setItem("local_imageURI",localStorage.getItem("local_imageURI_CurrentPic"));        
    }

function saveSettingProfile()
{ 
$('.appypie-loader').show();
    var settingName= document.getElementById('settingName').value;
    var settingLoc=document.getElementById('settingLoc').value;
    var emailId=localStorage.getItem("isLogin");
    var imagePath=document.getElementById('profilePicSetting').src;

   //window.location="savesetting:"+settingName+"$,$"+emailId+"$,$"+settingLoc+"$,$"+localStorage.getItem("applicationID")+"$,$"+imagePath;
	toaster.SaveSettingProfileDirecotory(localStorage.getItem("applicationID"),settingName,settingLoc,emailId,imagePath);  
}
function successFormSaveProfile(isSuceess,urlString)
{
 /*   if(isSuceess=='success')
    {
        if(urlString=="email is not exist")
        {
             localStorage.setItem("local_imageURI","images/hyper-local/vandor-pic.png");
        }
        else
        {
        localStorage.setItem("local_imageURI",urlString);
        }
        var settingName= document.getElementById('settingName').value;
        localStorage.setItem("userName",settingName);
        var settingLoc=document.getElementById('settingLoc').value;
        localStorage.setItem("userLocation",settingLoc);
        
        alertPopUp('Alert !','Profile Updated Successfully');
    }
    else
    {
        alertPopUp('Alert !','Fail to update profile');
    }
	*/
	
	if(isSuceess=='success')
    {       
        localStorage.setItem("local_imageURI",urlString);
        var settingName= document.getElementById('settingName').value;       
        var settingLoc=document.getElementById('settingLoc').value;
        localStorage.setItem("userLocation",settingLoc);
        localStorage.setItem("userName",settingName);
        localStorage.setItem("local_imageURI",urlString);
		 localStorage.setItem("local_imageURI_CurrentPic",urlString);
        alertPopUp(alert_sign_alert_services,Profile_Updated_Successfully);
        getService(mainServiceIndex);
    }
      
    
    else if(isSuceess=="local_imageURI_exist")
    {
    	 localStorage.setItem("local_imageURI","images/hyper-local/vandor-pic.jpg");
    	  var settingName= document.getElementById('settingName').value;
          localStorage.setItem("userName",settingName);
          var settingLoc=document.getElementById('settingLoc').value;
          localStorage.setItem("userLocation",settingLoc);
          
          alertPopUp(alert_sign_alert_services,Profile_Updated_Successfully);
          getService(mainServiceIndex);
    }
    else
    {
        alertPopUp(alert_sign_alert_services,Fail_to_update_profile);
        getService(mainServiceIndex);
    }
	setTimeout(function(){$('.appypie-loader').hide();},1000);
}
function saveBookMark()
{
    
    if((localStorage.getItem("isLogin")==null)||(localStorage.getItem("isLogin")=='null'))
    {
        loginService();
    }
    else
    {
        

        var pageid=localStorage.getItem("listIdForRR");
        var appId=localStorage.getItem("applicationID");
        var categoryId=sessionStorage.getItem('dirCatId');
        var dirPageId=sessionStorage.getItem('dirPageId');

           var bookmarkId="BookMark"+pageid;
           var bookmarkStatus=sessionStorage.getItem(bookmarkId);
           
           if(bookmarkStatus!=0)
           {
           
           var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/directory-soap#directoryDeleteBookMark";
           console.log("wsUrl---"+wsUrl);
           var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><directoryDeleteBookMark xmlns=\"http://'+localStorage.getItem("reseller")+'/services/directory-soap#directoryDeleteBookMark\"><appId>'+appId+'</appId><dirPageId>'+dirPageId+'</dirPageId><userEmail>'+localStorage.getItem("isLogin")+'</userEmail><pageId>'+pageid+'</pageId><catId>'+categoryId+'</catId><type>save</type></directoryDeleteBookMark></soap:Body></soap:Envelope>';
           
           
           console.log("directoryBookMark()  -->soapRequest --->"+soapRequest);
           $.ajax({
                  type: "POST",
                  url: wsUrl,
                  contentType: "text/xml",
                  cache: false,
                  dataType: "text",
                  data: soapRequest,
                  success: function(data, status, req)
                  {
                  var returnData=$(req.responseText).find("return").text();
                  
                  console.log("returnData --->"+returnData);
                  if(returnData=="success")
                  {
                  sessionStorage.setItem(bookmarkId,0);
                  $("header.hyper-local .bookmark").removeClass("on");
                  
                  alertPopUp(alert_sign_alert_services,Bookmarked_Deleted_successfully);
                  }
                  else
                  {
                  alertPopUp(alert_sign_alert_services,internet_connective_error_services);
                  
                  }
                  
                  },
                  error: function(response, textStatus, errorThrown)
                  {
                  console.log(response);
                  setTimeout(function(){$('.appypie-loader').hide();},1000);
                  }
                  });
           
           }
           else
           {
           if(checkNetworkConnection('true'))
           {
           var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/directory-soap#directoryBookMark";
           console.log("wsUrl---"+wsUrl);
           var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><directoryBookMark xmlns=\"http://'+localStorage.getItem("reseller")+'/services/directory-soap#directoryBookMark\"><appId>'+appId+'</appId><dirPageId>'+dirPageId+'</dirPageId><userEmail>'+localStorage.getItem("isLogin")+'</userEmail><pageId>'+pageid+'</pageId><catId>'+categoryId+'</catId><type>save</type></directoryBookMark></soap:Body></soap:Envelope>';
           
           
           console.log("directoryBookMark()  -->soapRequest --->"+soapRequest);
           $.ajax({
                  type: "POST",
                  url: wsUrl,
                  contentType: "text/xml",
                  cache: false,
                  dataType: "text",
                  data: soapRequest,
                  success: function(data, status, req)
                  {
                  var returnData=$(req.responseText).find("return").text();
                  
                  console.log("directoryBookMark --->"+returnData);
                  if(returnData=="success")
                  {
                  sessionStorage.setItem(bookmarkId,1);
                  $("header.hyper-local .bookmark").addClass("on");
                  
                  alertPopUp(alert_sign_alert_services,Bookmarked_successfully);
                  }
                  else
                  {
                  alertPopUp(alert_sign_alert_services,Network_connection_error_please_try_again_later);
                  
                  }
                  
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
    
}


function reqBookmarks()
{
    if((localStorage.getItem("isLogin")==null)||(localStorage.getItem("isLogin")=='null'))
    {
        loginService();
    }else
    {
        if(checkNetworkConnection('true'))
        {
            
            var dirPageId=sessionStorage.getItem('dirPageId');
            
            var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/directory-soap#directoryBookMark";
            console.log("wsUrl---"+wsUrl);
            var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><directoryBookMark xmlns=\"http://'+localStorage.getItem("reseller")+'/services/directory-soap#directoryBookMark\"><appId>'+localStorage.getItem("applicationID")+'</appId><dirPageId>'+dirPageId+'</dirPageId><userEmail>'+localStorage.getItem("isLogin")+'</userEmail><pageId></pageId><catId></catId><type>bookmarkdata</type></directoryBookMark></soap:Body></soap:Envelope>';
            
            
            console.log("directoryBookMark()  -->soapRequest --->"+soapRequest);
            $.ajax({
                   type: "POST",
                   url: wsUrl,
                   contentType: "text/xml",
                   cache: false,
                   dataType: "text",
                   data: soapRequest,
                   success: function(data, status, req)
                   {
                   var returnData=$(req.responseText).find("return").text();
                   var pageCount=1;
                   
                   console.log("returnData --->"+returnData);
                   menuHide();
                   if($(returnData).find('record').text())
                   {
                   
                   createDirSubDetailDisplay(returnData,2,"2","BOOKMARKS");
                   dirXmlData=returnData;
                   requestedFileSystem.root.getFile('dir_cat_detail_'+sessionStorage.getItem('dirCatId')+'_pageNo'+pageCount+'.xml', {create: true, exclusive: false}, gotFileEntryDir, fail);
                   }
                   else
                   {
                   navigator.notification.alert(
                                                no_saved_bookmarks_services,
                                                alertDismissed,
                                                alert_dir,
                                                ok_dir);
               
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   }
                   
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
function shareDirectory(serviceImage,serviceHeader,serviceDescription)
{
 //window.location="sharedirectory:"+serviceImage+"$,$"+serviceHeader+serviceDescription;
 toaster.shareDirectory("Directory Title : "+serviceHeader +"\nDirectory Description :  "+serviceDescription,serviceImage);

}


function weatherGeocodes(search,output) {
    var status;
    var results;
    var html = '';
    var msg = '';
    
    // Set document elements
   var search = search_delhi_services;
   var output = output_weatherlist_services;
// weatherGeocode('weatherLocation','weatherList');
//    
//    var search = '';
//    var output = document.getElementById(output);
    if(navigator.network.connection.type == Connection.NONE)
    {
        navigator.notification.alert(alert_internet_conn_not_avail_services, function(){}, alert_services, "");
    }
    else
    {
        
        if (search) {
            
            output.innerHTML = '';
            
            // Cache results for an hour to prevent overuse
            now = new Date();
            
            // Create Yahoo Weather feed API address
            var query = 'select * from geo.places where text="'+ search +'"';
            var api = 'http://query.yahooapis.com/v1/public/yql?q='+ encodeURIComponent(query) +'&rnd='+ now.getFullYear() + now.getMonth() + now.getDay() + now.getHours() +'&format=json&callback=?';
            
            // Send request
            $.ajax({
                   type: 'GET',
                   url: api,
                   dataType: 'json',
                   success: function(data) {
                   if (data.query.count > 0 ) {
                   
                   // List multiple returns
                   if (data.query.count > 1) {
                   for (var iCounter=0; iCounter<data.query.count; iCounter++)
                   {
                   html = html + '<li>'+ _getWeatherAddress(data.query.results.place[iCounter]) +'</li>';
                   
                   console.log("====>>>"+_getWeatherAddress(data.query.results.place[iCounter]) );
                   }
                   } else
                   {
                   html = html + '<li>'+ _getWeatherAddress(data.query.results.place) +'</li>';
                   }
                   
                   html = html + '</ul>';
                   
                   output.innerHTML = html;
                   
                   // Bind callback links
                   $("a.weatherAddress").unbind('click');
                   $("a.weatherAddress").click(function(e) {
                                               e.preventDefault();
                                               
                                               var address = $(this).text();
                                               var weoid = $(this).attr('rel');
                                               
                                               showLocation(address,weoid);
                                               });
                   
                   } else {
                   output.innerHTML = inner_html_location_couldnot_found_services;
                   }
                   },
                   error: function(data) {
                   output.innerHTML = inner_html_error_has_occured_services;
                   }
                   });
            
        }
        else {
            // No search given
            output.innerHTML = '';
        }
    }
}

function showimageurl(imgeUrls,position)
	{
		//alert("showimageurl "+showimageurl);
		    imgeUrls=imgeUrls.toString();
		
			window.plugins.photo.show({ videoid:position, videoInfo:imgeUrls, pictext:"", piclikes:"", piccomments:""}, function() {}, function() {} );
 // window.plugins.photo.show({ videoid:position, videoInfo:imgeUrls, pictext:"", piclikes:"", piccomments:""}, function() {}, function() {} );

	}

function showServicePageMap(MapLat,MapLong,address)
{
if(!address)
{
address="";
}
    console.log("showServicePageMap--->MapLat-->"+MapLat+"<---MapLong-->"+MapLong);
	
if (MapLat == null || MapLat == "" || MapLat=="0") {
								urlValue = "http://maps.google.com/maps?q="
										+ address + "&t=m&z=16";
							} else {
								urlValue = "http://maps.google.com/maps?q="
										+ MapLat + "," + MapLong
										+ "&t=m&z=16";
							}

							if (address != null && address.length > 1 && address!="abc") {
								urlValue = "http://maps.google.com/maps?q="
										+ address + "&t=m&z=16";
							}	
	showServicePage(urlValue);
    //window.location="maplocation:"+MapLat+"&&"+MapLong+"&&"+address;
}

function checkinPage(listId,categoryId,dirPageId,serlatitude,serlongitude,serAddressFull,serviceHeader,openMapWithThirdParty) {
    var adddir="add";
    var checkinPage ='<header class="hyper-local header-main "><a class="hyper-back" href="#" onclick="onBackKeyDown();"></a><h1>'+serviceHeaderName+'</h1></header>\
    <section class="hyper-local categorys-page">\
    <div class="scroller-on">\
    <div class="hyper-map">\
    <span href="#">'+Checkins_Dir+'<b id="checkintotalcounts">0</b></span>\
    <div class="map" id="googleMapcheckin" style="width:100%;height:200px;"></div>\
    <a href="#" onclick="checkincount(\''+listId+'\',\''+categoryId+'\',\''+dirPageId+'\',\''+adddir+'\',this);" id="checkedinbutton">'+Check_In_Dir+'</a>\
    </div>\
    </div>\
    </section>';
    appendHtml(checkinPage,3,3);
	resetDirectoryHeader();
    setTimeout(function(){
               checkincount(listId,categoryId,dirPageId,"get");
               if(parseFloat(serlatitude)!=0)
               {
               var gid='googleMapcheckin';
               var mapDiv = document.getElementById(gid);
               var map = new google.maps.Map(mapDiv, {
                                             zoom: 8,
                                             center: new google.maps.LatLng(serlatitude, serlongitude)
                                             });
               
               // We add a DOM event here to show an alert if the DIV containing the
               // map is clicked.
               google.maps.event.addDomListener(mapDiv, 'click', function() {
                                                //window.alert('Map was clicked!');
                                                var isFromDirectory=isfrom_drictory_services;
                                                showServicePageMapNew(serlatitude,serlongitude,0,encodeURIComponent(serAddressFull.replace("'","")),serviceHeader,1,isFromDirectory,openMapWithThirdParty);
                                                
                                                });
               
               
               var map=new google.maps.Map(document.getElementById(gid),map);
               
               var marker=new google.maps.Marker({
                                                 position:new google.maps.LatLng(serlatitude, serlongitude),
                                                 });
               marker.setMap(map);
               }
               },1000);
    
}
function checkincount(listId,categoryId,dirPageId,mode,obj){
    
    if(obj != null)
    {
        if($(obj).is(".on"))
        {
            return false;
        }
        $(obj).addClass("on")
    }
    $('.appypie-loader').show();
    console.log("checkincount==>amritesh==>"+listId);
    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/directory-soap#checkOnListing";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><checkOnListing xmlns=\"http://'+localStorage.getItem("reseller")+'/services/directory-soap#checkOnListing\"><listId>'+listId+'</listId><appId>'+localStorage.getItem("applicationID")+'</appId><catId>'+categoryId+'</catId><pageId>'+dirPageId+'</pageId><type>'+mode+'</type></checkOnListing></soap:Body></soap:Envelope>';
    
    console.log("checkincount==>amritesh==>"+soapRequest);
    jQuery.support.cors = true;
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
          
		   
		   if(mode=='add')
           {
           document.getElementById("checkedinbutton").innerHTML =Checked_In ;
           }
           
           var strJSON = $(req.responseText).find("return").text();
           $("#checkintotalcounts").text(strJSON);
           //document.getElementById("checkintotalcounts").innerText =strJSON ;
           
           console.log("amritesh "+strJSON);
		    setTimeout(function(){$('.appypie-loader').hide();},500);
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    
    
}

function resetDirectoryHeader() {
	headerImage=$(masterData).find("nav_header_image_name").text();
	headerBarTextColor=$(masterData).find("headerBarTextColor").text();
	navigationBarType=$(masterData).find("navigationBarType").text();
	headerBarBackgroundColor=$(masterData).find("headerBarBackgroundColor").text();
	var headerBarTitle	=$(masterData).find("headerBarTitle").text();
	var headerBarFont	=$(masterData).find("headerBarFont").text();
	var headerBarSize	=$(masterData).find("headerBarSize").text();
	if(headerImage == '' || navigationBarType=='text' || navigationBarType=='none')
	{
		$( "header.hyper-local").css({
			"color":headerBarTextColor,
			"background":headerBarBackgroundColor			
		}).find("i").css("color", headerBarTextColor);
		$("header.hyper-local").find("h1").css(" font-weight", "normal").css(" text-shadow", "none").text(headerBarTitle)
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
		$("header.hyper-local").css({
			"background-image":"url("+headerImageNew+")",
			"background-color":headerBarBackgroundColor,
			"background-size":"100% 100%",
			"background-position":"center"
		}).addClass('headerImage').find("i").css("color", headerBarTextColor);
		$("header.hyper-local").find("h1").text("");		
	}		
}