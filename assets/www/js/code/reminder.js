
var DeviceID="1234567890ss";

//var DeviceID = tester12345;
//var find = '-';
//var re = new RegExp(find, 'g');
//DeviceID = DeviceID.replace(re, '');
//if(DeviceID==null||DeviceID==""||DeviceID=="null")
//{
//    DeviceID="1234567890";
//}
var reminderid = [];
var titlesarray = [];
var titles = [];
var phonenumber="";
var type ="";
var day ="";
var month ="";
var date ="";
var time ="";
var timezone ="";
var repeat ="";
var message ="";
var status ="";
var i=0;

var reminderday="";
var remindermonth="";
var reminderdate="";
var remindertime="";
var repete="";
var message="";


var phonenumberarray = [];
var typearray= [];
var afterDatearray=[];
var dayarray= [];
var montharray= [];
var datearray= [];
var timearray= [];
var timezonearray= [];
var repeatarray= [];
var messagearray= [];
var statusarray= [];

function getReminder(index)
{
    var appId = ((masterData).getElementsByTagName('appId')[0].firstChild.nodeValue);
    var DeviceID="1234567890ss";
    var wsUrl="http://angularml.pbodev.info/services/reminder-soap#getAllReminderJson";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getAllReminderJson xmlns=\"http://angularml.pbodev.info/services/reminder-soap#getAllReminderJson\"><appId>'+appId+'</appId><deviceId>'+DeviceID+'</deviceId></getAllReminderJson></soap:Body></soap:Envelope>';
    
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
           console.log("strJSON--sss-start-"+strJSON);
           
           datamessageliststart(strJSON);
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    function datamessageliststart(strJSON){
        var id="";
        // alert(strJSON)
        console.log("strJSON"+strJSON)
        var dataget = JSON.parse(strJSON)
        
        for (var i=0; i<dataget.length; i++) {
            
            reminderid[i] = dataget[i].id
            titlesarray[i] = dataget[i].title
            phonenumberarray[i] = dataget[i].phone
            typearray[i] = dataget[i].type
            afterDatearray[i] = dataget[i].afterDate
            dayarray[i] = dataget[i].day
            montharray[i] = dataget[i].month
            datearray[i] = dataget[i].date
            timearray[i] = dataget[i].time
            timezonearray[i] = dataget[i].timezone
            repeatarray[i] = dataget[i].repeat
            messagearray[i] = dataget[i].message
            statusarray[i] = dataget[i].status
            
            dayarray[i] = dayarray[i].replace(/,\s*$/, "");
            
        }
        
        
        var html="";
        var Contact ="";
        var phoneNumbers="";
        var repeate ="";
        var Message="";
        var contacts = [];
        var title="Set Your Reminder";
        var phone ="";
        var type="";
        var reminderDay="";
        var reminderMonth="";
        var reminderDate="";
        var reminderTime="";
        var repeat="";
        var message="";
        
        var displayBlock='none';
        
        html+="<div class='main-container'>";
        
        // datamessagelist();
        //main with button add , edit , delete
        html+="<div id='main' class='main-section'><div class='title'>"+title+"</div>";
        
        

        

        
       // main with button add
        html+="<div id='adddataid' class='main-section' style='display:none;'><div class='white-wrapper'><input data-role='none' type='text' placeholder='Title (ex. Name)' id='txttitle'/><input class='contact' data-role='none' type='text' placeholder='Phone Number' id='txtphonenumber'/><a class='contac-btn'><img src='images/reminder/contact-icon.png' onclick='doContactPicker()'></a><input type='text' placeholder='Message...' id='txtmessage'></div>";
        
        html+="<div id='selecttypeid' class='white-wrapper select-type'><div class='radio-pan'><span>Select Type:</span><ul><li><span class='radio-ui' data-type='abc' data-rel='a' onclick='myFunctionshowday(this)' name='radio'><input type='radio'id='dailyid' name='radio'/> Daily</span></li><li><span class='radio-ui' data-type='abc' data-rel='b' onclick='myFunctionshowmonths(this)' name='radio'><input type='radio' id='monthlyid' name='radio'/> Month</span></li><li><span class='radio-ui' data-type='abc' data-rel='c' onclick='myFunctionshowyear(this)' name='radio'><input type='radio' id='yearlyid' name='radio'/> Yearly</span></li></ul>";

        
        html+=" <div id='dayid' class='radio-tab' data-type='abc'><ul><li><input type='checkbox' name='daycheck' value='monday' data-role='none'> Monday</li><li><input type='checkbox' name='daycheck' value='tuesday' data-role='none'> Tuesday</li><li><input type='checkbox' name='daycheck' value='wednesday' data-role='none'> Wednessday</li><li><input type='checkbox' name='daycheck' value='thursday' data-role='none'> Thursday</li><li><input type='checkbox' name='daycheck' value='friday' data-role='none'> Friday</li><li><input type='checkbox' name='daycheck' value='saturday' data-role='none'> Saturday</li><li><input type='checkbox'> Sunday</li></ul><div class='everyday'><input type='checkbox' name='daycheck' value='sunday' data-role='none'>Everyday</div></div>";
        html+="<div id='monthid' class='radio-tab' data-type='abc'><ul class='month'><li><select id='dateidmonth' name='date' data-role='none'><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select></li></ul></div>";
        
        html+="<div id='yearid' class='radio-tab' data-type='abc'><ul class='month'><li><select id='dateid' name='date' data-role='none'><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select></li><li><select name='month' id='month' onchange='' size='1' data-role='none'><option value='january'>January</option><option value='february'>February</option><option value='march'>March</option><option value='april'>April</option><option value='may'>May</option><option value='june'>June</option><option value='july'>July</option><option value='august'>August</option><option value='september'>September</option><option value='october'>October</option><option value='november'>November</option><option value='december'>December</option></select></li></ul></div>";
        
        html+="</div></div>";
        
        html+="<div class='white-wrapper'><div class='inner-pan'><span>After Date/Time!</span><input type='date' class='date' placeholder='Date' id='remianderDateafter'></div></div>";
        
        html+="<div class='white-wrapper'><div class='inner-pan'><span>Reminder Time!</span><input type='time' class='date' placeholder='time' name='currenttime' id='remianderTime'></div></div>";
        
         html+="<div class='white-wrapper'><div class='inner-pan'><span>Repeate!</span><input type='checkbox' name='repeat' value='repeat:'id='repeats'></div></div>";
        
        
        html+="<div class='button-pan'><a onclick='sendDataMessage()' ><img src='images/reminder/submit-icon.png'></a><a onclick='CancelRemiandermain()' ><img src='images/reminder/cancel-icon.png'></a></div>";
      
      
        html+="</div>";
        //main with button add End
        
        
        
        //main with button Edit start
                html+="<div id='showalldataid' style='display:block;' class='main-section'>";
        
                for(var k=0;k<reminderid.length;k++)
                {
                    
                    html+="<div id='reminderiddel' class='active-window'><div class='gray-row'><h2>"+titlesarray[k]+"</h2> <a class='delete-btn' onclick='deleteRemiander("+reminderid[k]+")'></a> <a class='edit-btn' onclick='editdataAllRemiander("+reminderid[k]+")'></a></div><ul class='reminder-row'><li class='check'></li><li class='text-row'><p><b>"+typearray[k]+"</b>,<b>"+dayarray[k]+"</b><b>"+datearray[k]+"</b><b>"+montharray[k]+"</b><p></p><span>Time:-  "+timearray[k]+"</span></p></li><li class='switch-ui' data-parent='reminder-row' onclick='thisCheckbox(this,"+reminderid[k]+")' id='spancheck'><input type='checkbox' checked='checked' /></li></ul></div>";
                    
                    //html+="<div id='reminderiddel' class='active-window'><div class='reminder-row'><div class='gray-row'><a></a>  <span class='count-value'></span></div><div class='text-row'>"+titlesarray[k]+" <button type='button' data-role='none' onclick='editdataAllRemiander("+reminderid[k]+")' class='edit'> </button><button type='button' data-role='none' onclick='deleteRemiander("+reminderid[k]+")'> </button></div><div class='view'><b>"+typearray[k]+"</b>,<b>"+dayarray[k]+"</b><b>"+datearray[k]+"</b><b>"+montharray[k]+"</b><p>Time:-  "+timearray[k]+"</p><span class='switch-ui'onclick='thisCheckbox(this,"+reminderid[k]+")' id='spancheck' ><input type='checkbox' checked='checked'></span></div></div></div>";
        
                }
        html+="<div class='button-fix'><div class='button-pan button-pan2'><a style='display:block;'  onclick='AddReminder()' id='addreminderbtn'><img src='images/reminder/add-icon.png'></a></div></div>";
        
        html+="</div>";
        
      
        
        
        //        main with button Edit End
        
        
        //main with  Edit start
        html+="<div id='editlist' style='display:none;' class='main-section'>";
        for(var j=0;j<reminderid.length;j++)
        {
            
            html+="<div  id="+reminderid[j]+" class='white-wrapper' style='display:none;'><div style='display:none;'>ReminderId:<div><input type='text' id='reminderidarry' value="+reminderid[j]+" readonly></div></div><input data-role='none' type='text' placeholder='' id='txttitlearray' value="+titlesarray[j]+"><input class='contact' data-role='none' type='text' placeholder='' value="+phonenumberarray[j]+" id='txtphonenumberarray'/> <a class='contac-btn'><img src='images/reminder/contact-icon.png' onclick='doContactPickerEdit()'></a><input data-role='none' type='text' placeholder='' id='messagetxtarray'/ value="+messagearray[j]+"></div>";
           
            html+="<div id='selecttypeidedit' class='white-wrapper select-type'><div class='radio-pan'><span>Select Type:</span><ul><li><span class='radio-ui' data-type='abc' data-rel='a' onclick='myFunctionshowdaylist(this)'><input type='radio'id='dailyidedit' name='radio'/> Daily</span></li><li><span class='radio-ui' data-type='abc' data-rel='b' onclick='myFunctionshowmonthlist(this)'><input type='radio' id='monthlyidedit' name='radio'/> Month</span></li><li><span class='radio-ui' data-type='abc' data-rel='c' onclick='myFunctionshowyearlist(this)' ><input type='radio' id='yearlyidedit' name='radio'/> Yearly</span></li></ul>";
            
            //html+="<div id='selecttypeidedit' class='form-row'><label>Select Type:</label><span class='radio' onclick='myFunctionshowdaylist(this)' id='checkedto'><input type='radio' name='radio' value='daily'  class='radio' id='dailyidedit'></span>Daily<span class='radio' onclick='myFunctionshowmonthlist(this)'><input type='radio' name='radio' value='monthly' id='monthlyidedit'></span>Month <span class='radio' onclick='myFunctionshowyearlist(this)' id='dailyidchecked'><input type='radio' name='radio' value='yearly' id='yearlyidedit'></span> Yearly</div>";
            
             html+=" <div id='dayidlist' class='radio-tab' data-type='abc'><ul><li><input type='checkbox' name='daycheckarray' value='monday' data-role='none'> Monday</li><li><input type='checkbox' name='daycheckarray' value='tuesday' data-role='none'> Tuesday</li><li><input type='checkbox' name='daycheckarray' value='wednesday' data-role='none'> Wednessday</li><li><input type='checkbox' name='daycheckarray' value='thursday' data-role='none'> Thursday</li><li><input type='checkbox' name='daycheckarray' value='friday' data-role='none'> Friday</li><li><input type='checkbox' name='daycheckarray' value='saturday' data-role='none'> Saturday</li><li><input type='checkbox'> Sunday</li></ul><div class='everyday'><input type='checkbox' name='daycheckarray' value='sunday' data-role='none'>Everyday</div></div>";
            
            // html+="<div id='dayidlist' class='daily-div' style='display:none;'><input type='checkbox' name='daycheckarray' value='everyday' data-role='none' id='everydayedit'>Everyday<br><input type='checkbox' name='daycheckarray' value='monday' data-role='none' id='mondayedit'>Monday<br><input type='checkbox' name='daycheckarray' value='tuesday' data-role='none' id='tuesdayedit'>Tuesday<br><input type='checkbox' name='daycheckarray' value='wednesday' data-role='none' id='wednesdayedit'>Wednesday<br><input type='checkbox' name='daycheckarray' value='thursday' data-role='none' id='thursdayedit'>Thursday<br><input type='checkbox' name='daycheckarray' value='friday' data-role='none' id='fridayedit'>Friday<br><input type='checkbox' name='daycheckarray' value='saturday' data-role='none' id='saturdayedit'>Saturday<br><input type='checkbox' name='daycheckarray' value='sunday' data-role='none' id='sundayedit'>Sunday<br></div>";
            
             html+="<div id='monthidEdit' class='radio-tab' data-type='abc'><ul class='month'><li><select id='dateidmonthedit' name='date' data-role='none'><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select></li></ul></div>";
            
           // html+="<div id='monthidEdit' class='daily-div' style='display:none;'><div class='form-row'><label>Date:</label><select id='dateidmonthedit' name='date' data-role='none'><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select></div></div>";
            
                html+="<div id='yearlistEdit' class='radio-tab' data-type='abc'><ul class='month'><li><select id='dateideditarray' name='date' data-role='none'><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select></li><li><select name='month' id='montheditarray' onchange='' size='1' data-role='none'><option value='january'>January</option><option value='february'>February</option><option value='march'>March</option><option value='april'>April</option><option value='may'>May</option><option value='june'>June</option><option value='july'>July</option><option value='august'>August</option><option value='september'>September</option><option value='october'>October</option><option value='november'>November</option><option value='december'>December</option></select></li></ul></div></div></div>";
            
            
           // html+="<div id='yearlistEdit' class='daily-div' style='display:none;'><div class='form-row'><label>Date:</label><select id='dateideditarray' name='date' data-role='none'><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select></div><div class='form-row'><label>Month:</label><select name='month' id='montheditarray' onchange='' size='1' data-role='none'><option value='january'>January</option><option value='february'>February</option><option value='march'>March</option><option value='april'>April</option><option value='may'>May</option><option value='june'>June</option><option value='july'>July</option><option value='august'>August</option><option value='september'>September</option><option value='october'>October</option><option value='november'>November</option><option value='december'>December</option></select></div></div>";
            
           
            html+="<div class='white-wrapper'><div class='inner-pan'><span>After Date/Time!</span><input type='date' class='date' placeholder='Date' id='remianderDateafterEdit'/ value="+afterDatearray[j]+"></div></div>";
            
         //   html+="<div class='form-row'><label>After Date:</label><input data-role='none' type='date' name='currentdate' id='remianderDateafterEdit'/ value="+afterDatearray[j]+"></div>";
             html+="<div class='white-wrapper'><div class='inner-pan'><span>Reminder Time!</span><input type='time' class='date' placeholder='time' name='currenttime' id='reminderTimearray'/ value="+timearray[j]+"></div></div>";
            
            // html+="<div class='white-wrapper'><div class='inner-pan'><span>Reminder Time!</span><input type='time' class='date' placeholder='time' name='currenttime' id='remianderTime'></div></div>";
            html+="<div class='white-wrapper'><div class='inner-pan'><span>Repeate!</span><input type='checkbox' name='repeat' value='repeat:'id='repeatarray'></div></div>";
            
             html+="<div class='button-pan'><a onclick='updatedataAllRemiander("+reminderid[j]+")'  ><img src='images/reminder/submit-icon.png'></a><a  onclick='CancelRemiander("+reminderid[j]+")' ><img src='images/reminder/cancel-icon.png'></a></div>";
            
            
           // html+="<div class='form-row'><label>Time:</label><input data-role='none' type='time' name='currenttime' id='reminderTimearray'/ value="+timearray[j]+"></div><div class='form-row'><label>Message:</label><textarea data-role='none' type='text' placeholder='' id='messagetxtarray'/ value="+messagearray[j]+">"+messagearray[j]+"</textarea></div><div class='form-row'><label>Repeat:</label> <input type='checkbox' name='repeat' value='repeat: ' id='repeatarray' data-role='none'></div><div class='form-row btn'><button type='button' onclick='updatedataAllRemiander("+reminderid[j]+")' data-role='none' class='update'>Update</button><button type='button' onclick='CancelRemiander("+reminderid[j]+")' data-role='none' class='Cancel'>Cancel</button></div>";
            
        }
        html+="</div>";
       // main with Edit End
        
        
        html+="</div></div>";
        appendHtml("<div><div>"+html+"</div></div>",'',1);
 
        

        
        if((typearray=="Daily")||(typearray=="daily"))
        {
            $("#dailyidedit").parent().click();
            
            if((dayarray=="Everyday")||(dayarray=="everyday"))
            {
                document.getElementById("everydayedit").checked = true;
            }
            if((dayarray=="Monday")||(dayarray=="monday"))
            {
                document.getElementById("mondayedit").checked = true;
            }
            if((dayarray=="tuesday")||(dayarray=="Tuesday"))
            {
                document.getElementById("tuesdayedit").checked = true;
            }
            if((dayarray=="wednesday")||(dayarray=="Wednesday"))
            {
                document.getElementById("wednesdayedit").checked = true;
            }
            if((dayarray=="thursday")||(dayarray=="Thursday"))
            {
                document.getElementById("thursdayedit").checked = true;
            }
            if((dayarray=="friday")||(dayarray=="Friday"))
            {
                document.getElementById("fridayedit").checked = true;
            }
            if((dayarray=="saturday")||(dayarray=="Saturday"))
            {
                document.getElementById("saturdayedit").checked = true;
            }
            if((dayarray=="sunday")||(dayarray=="Sunday"))
            {
                document.getElementById("sundayedit").checked = true;
            }
            
        }
        if((typearray=="Monthly")||(typearray=="monthly"))
        {
            
            $("#monthlyidedit").parent().click();
            document.getElementById("dateidmonthedit").value = datearray;
            
        }
        if((typearray=="Yearly")||(typearray=="yearly"))
        {
            $("#yearlyidedit").parent().click();
            document.getElementById("dateideditarray").value = datearray;
            document.getElementById("montheditarray").value = montharray;
            
        }
        
    }
    
}



function doContactPicker() {
    navigator.contacts.pickContact(function(contact){
                                   var s = "";
                                   if(contact.phoneNumbers && contact.phoneNumbers.length) {
                                   s+= contact.phoneNumbers[0].value;
                                   }
                                   var numbers=s.replace(/\D+/g, "").replace(/^[01]/, "");
                                   
                                   if(document.getElementById("txtphonenumber").value=="")
                                   {
                                   document.getElementById("txtphonenumber").value=numbers;
                                   }
                                   else
                                   {
                                   document.getElementById("txtphonenumber").value=document.getElementById("txtphonenumber").value+","+numbers;
                                   }
                                   console.log('The following contact has been selected:'+JSON.stringify(contact));
                                   
                                   },function(err){
                                   console.log('Error: ' + err);
                                   });
    
}

function doContactPickerEdit() {
    navigator.contacts.pickContact(function(contact){
                                   //alert("aa"+JSON.stringify(contact))
                                   var s = "";
                                   if(contact.phoneNumbers && contact.phoneNumbers.length) {
                                   s+= contact.phoneNumbers[0].value;
                                   }
                                   var numbers=s.replace(/\D+/g, "").replace(/^[01]/, "");
                                   if(document.getElementById("txtphonenumberarray").value=="")
                                   {
                                   document.getElementById("txtphonenumberarray").value=numbers;
                                   }
                                   else
                                   {
                                   document.getElementById("txtphonenumberarray").value=document.getElementById("txtphonenumberarray").value+","+numbers;
                                   }
                                   
                                   console.log('The following contact has been selected:'+JSON.stringify(contact));
                                   
                                   },function(err){
                                   console.log('Error: ' + err);
                                   });
}





function AddReminder(index)
{
    document.getElementById("dayid").style.display = "block";
    document.getElementById("yearid").style.display = "none";
    document.getElementById("monthid").style.display = "none";
//    if(document.getElementById("main"))
//    {
        document.getElementById("adddataid").style.display = "block";
        document.getElementById("showalldataid").style.display = "none";
    //}
    
}


function sendDataMessage(index)
{
    var appId = ((masterData).getElementsByTagName('appId')[0].firstChild.nodeValue);
    var DeviceID="1234567890ss";
    var txttitle = document.getElementById("txttitle").value
    
    var PhoneNumber = document.getElementById("txtphonenumber").value
    
    var type = "";
    
    if(document.getElementById('monthlyid').checked)
    {
        type="monthly";
    }
    if(document.getElementById('dailyid').checked)
    {
        type="daily";
    }
    if(document.getElementById('yearlyid').checked)
    {
        type="yearly";
    }
    var daycheck = document.getElementsByName('daycheck');
    
    var day = "";
    
    for (var i = 0; i < daycheck.length; i++) {
        if (daycheck[i].checked) {
            day = day + daycheck[i].value + ",";
        }
    }
    if(day=="monday,tuesday,wednesday,thursday,friday,saturday,sunday,")
    {
        day="Everyday"
    }
    day = day.replace(/,\s*$/, "");
    
    
    
    var reminderDate=document.getElementById("dateid").value;
    var reminderMonth=document.getElementById("month").value;
    if(type=="daily")
    {   reminderMonthDate="";
        reminderDate="";
        reminderMonth="";
    }
    if(type=="monthly")
    {    day="";
        var reminderMonthDate=document.getElementById("dateidmonth").value;
        reminderDate=reminderMonthDate;
        reminderMonth="";
    }
    if(type=="yearly")
    {    day="";
        reminderMonthDate="";
    }
    var repeats = "";
    if(document.getElementById("repeats").checked)
    {
        repeats="1";
    }
    else
    {
        repeats="0";
    }
    
    
    
    var reminderAfterDate=document.getElementById("remianderDateafter").value;
    
    var remianderTime=document.getElementById("remianderTime").value;
    var txtmessage=document.getElementById("txtmessage").value;
    var split = new Date().toString().split(" ");
    var timezone = split[split.length - 2] + " " + split[split.length - 1];
    var reminderDay="";
    
    if((txttitle=="")||(PhoneNumber == "")||(remianderTime == "")||(txtmessage == ""))
    {
        alertPopUp("Alert!","Required field cannot be empty")
        return;
    }
    
    
    
    if(!checkNetworkConnection())
    {
        
        
    }
    else
    {
        
        
        var wsUrl="http://angularml.pbodev.info/services/reminder-soap#addReminderJson";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><addReminderJson xmlns=\"http://angularml.pbodev.info/services/reminder-soap#addReminderJson\"><appId>'+appId+'</appId><deviceId>'+DeviceID+'</deviceId><title>'+txttitle+'</title><phone>'+PhoneNumber+'</phone><type>'+type+'</type><afterDate>'+reminderAfterDate+'</afterDate><reminderDay>'+day+'</reminderDay><reminderMonth>'+reminderMonth+'</reminderMonth><reminderDate>'+reminderDate+'</reminderDate><reminderTime>'+remianderTime+'</reminderTime><timezone>'+timezone+'</timezone><repeat>'+repeats+'</repeat><message>'+txtmessage+'</message></addReminderJson></soap:Body></soap:Envelope>';
        
        
        
        console.log("Soap request phone "+soapRequest);
        // alert("b")
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
               openPopup()
               // alertPopUp("Alert!","Your Reminder is set this Phone Number "+PhoneNumber)
               var strJSON = $(req.responseText).find("return").text();
               console.log("strJSON--sss-send"+strJSON);
               console.log("success==="+data)
               
               if(document.getElementById("main"))
               {
               document.getElementById("adddataid").style.display = "none";
               document.getElementById("showalldataid").style.display = "block";
               }
               getReminder(index)
               
               },
               error: function(response, textStatus, errorThrown)
               {
               console.log(response);
               }
               });
    }
}

function getdataAllRemiander()
{
    for(var j=0;j<reminderid.length;j++)
    {
        document.getElementById(reminderid[j]).style.display = "none";
    }
    
    if(document.getElementById("main"))
    {
        document.getElementById("adddataid").style.display = "none";
        document.getElementById("showalldataid").style.display = "block";
    }
    var appId = ((masterData).getElementsByTagName('appId')[0].firstChild.nodeValue);
    var DeviceID="1234567890ss";
    
    
    var wsUrl="http://angularml.pbodev.info/services/reminder-soap#getAllReminderJson";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getAllReminderJson xmlns=\"http://angularml.pbodev.info/services/reminder-soap#getAllReminderJson\"><appId>'+appId+'</appId><deviceId>'+DeviceID+'</deviceId></getAllReminderJson></soap:Body></soap:Envelope>';
    
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
           console.log("strJSON--sss-main"+strJSON);
           
           datamessagelist(strJSON);
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           }
           });
    
}


function datamessagelist(strJSON){
    var id="";
    // alert(strJSON)
    console.log("strJSON"+strJSON)
    var dataget = JSON.parse(strJSON)
    
    for (var i=0; i<dataget.length; i++) {
        
        reminderid[i] = dataget[i].id
        titlesarray[i] = dataget[i].title
        phonenumberarray[i] = dataget[i].phone
        typearray[i] = dataget[i].type
        dayarray[i] = dataget[i].day
        montharray[i] = dataget[i].month
        datearray[i] = dataget[i].date
        timearray[i] = dataget[i].time
        timezonearray[i] = dataget[i].timezone
        repeatarray[i] = dataget[i].repeat
        messagearray[i] = dataget[i].message
        statusarray[i] = dataget[i].status
        
        
    }
    //alert("reminderid==="+reminderid)
    // alert("titlesarry==="+titlesarray)
    
    
}



function editdataAllRemiander(idd)
{
    
    if(repeatarray=="1")
    {
        document.getElementById("repeatarray").checked = true;
    }
    
    document.getElementById("addreminderbtn").style.display = "none";
    document.getElementById("showalldataid").style.display = "none";
    document.getElementById("editlist").style.display = "block";
    document.getElementById(idd).style.display = "block";
    
}


function updatedataAllRemiander(index,idd)
{
    
    var id=document.getElementById("reminderidarry").value;
    
    var appId = ((masterData).getElementsByTagName('appId')[0].firstChild.nodeValue);
    var DeviceID="1234567890ss";
    var titlearray=document.getElementById("txttitlearray").value;
    var phonenumber=document.getElementById("txtphonenumberarray").value;
    var type = "";
    
    if(document.getElementById("monthlyidedit").checked)
    {
        type="monthly";
    }
    
    if(document.getElementById("dailyidedit").checked)
    {
        type="daily";
    }
    if(document.getElementById("yearlyidedit").checked)
    {
        type="yearly";
    }
    
    var daycheck = document.getElementsByName('daycheckarray');
    var day = "";
    for (var i = 0; i < daycheck.length; i++) {
        if (daycheck[i].checked) {
            day = day + daycheck[i].value + ",";
        }
    }
    
    var reminderMonthDate="";
    if(day=="monday,tuesday,wednesday,thursday,friday,saturday,sunday,")
    {
        day="Everyday"
    }
    
    
    day = day.replace(/,\s*$/, "");
    var reminderDate=document.getElementById("dateideditarray").value;
    var reminderAfterDate=document.getElementById("remianderDateafterEdit").value;
    var reminderMonth=document.getElementById("montheditarray").value;
    var split = new Date().toString().split(" ");
    var timezone = split[split.length - 2] + " " + split[split.length - 1];
    if(type=="Monthly")
    {    day="";
        var reminderMonthDate=document.getElementById("dateidmonthedit").value;
        reminderDate=reminderMonthDate;
        reminderMonth="";
    }
    if(type=="Yearly")
    {
        day="";
    }
    if(type=="Daily")
    {   reminderMonthDate="";
        reminderDate="";
        reminderMonth="";
    }
    var repeats = "";
    if(document.getElementById("repeatarray").checked)
    {
        repeats="1";
    }
    else
    {
        repeats="0";
    }
    if(type=="Daily")
    {
        reminderDate="";
        reminderMonth="";
    }
    var remianderTime=document.getElementById("reminderTimearray").value;
    var txtmessage=document.getElementById("messagetxtarray").value;
    
    if((titlearray=="")||(phonenumber == "")||(remianderTime == "")||(txtmessage == ""))
    {
        alertPopUp("Alert!","Required field cannot be empty")
        return;
    }
    var wsUrl="http://angularml.pbodev.info/services/reminder-soap#updateReminderJson";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><updateReminderJson xmlns=\"http://angularml.pbodev.info/services/reminder-soap#updateReminderJson\"><id>'+id+'</id><appId>'+appId+'</appId><deviceId>'+DeviceID+'</deviceId><title>'+titlearray+'</title><phone>'+phonenumber+'</phone><type>'+type+'</type><reminderDay>'+day+'</reminderDay><reminderMonth>'+reminderMonth+'</reminderMonth><reminderDate>'+reminderDate+'</reminderDate><reminderTime>'+remianderTime+'</reminderTime><timezone>'+timezone+'</timezone><repeat>'+repeats+'</repeat><message>'+txtmessage+'</message></updateReminderJson></soap:Body></soap:Envelope>';
    
    
    
    
    console.log("wsUrl"+wsUrl)
    console.log("soapRequest to update"+soapRequest)
    
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
           console.log("strJSON to ===update"+strJSON);
           
           
           getReminder(index)
           document.getElementById("adddataid").style.display = "none";
           document.getElementById("showalldataid").style.display = "block";
           document.getElementById("editlist").style.display = "none";
           document.getElementById(idd).style.display = "none";
           },
           error: function(response, textStatus, errorThrown)
           {
           alert("errer")
           console.log(response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
}


function deleteRemiander(reminderid)
{
    var DeviceID="1234567890ss";
    var list = document.getElementById("reminderiddel");
    
    console.log("list==list.id"+list.id)
    list.parentNode.removeChild(list);
    var wsUrl="http://angularml.pbodev.info/services/reminder-soap#deleteReminderJson";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><deleteReminderJson xmlns=\"http://angularml.pbodev.info/services/reminder-soap#deleteReminderJson\"><id>'+reminderid+'</id><deviceId>'+DeviceID+'</deviceId></deleteReminderJson></soap:Body></soap:Envelope>';
    console.log("soapRequest delete"+soapRequest)
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           alertPopUp('Alert!','your reminder is deleted');
           // alert("success")
           var strJSON = $(req.responseText).find("return").text();
           console.log("strJSON--sssdelete -"+strJSON);
           getReminder(index)
           
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    
    
}

function myFunctionshowday(obj)
{
//    if(document.getElementById("selecttypeid"))
//    {
        document.getElementById("dayid").style.display = "block";
        document.getElementById("yearid").style.display = "none";
        document.getElementById("monthid").style.display = "none";
        
    //}
    thisRadio(obj)
}
function myFunctionshowyear(obj)
{
    if(document.getElementById("selecttypeid"))
    {
        document.getElementById("yearid").style.display = "block";
        document.getElementById("dayid").style.display = "none";
        document.getElementById("monthid").style.display = "none";
    }
    thisRadio(obj)
}
function myFunctionshowmonths(obj)
{
    if(document.getElementById("selecttypeid"))
    {
        document.getElementById("yearid").style.display = "none";
        document.getElementById("dayid").style.display = "none";
        document.getElementById("monthid").style.display = "block";
    }
    thisRadio(obj)
}

function thisRadio(obj) {
    
    $(obj).parent().find("span.radio").removeClass("on")
    if($(obj).is(".on"))
    {
        $(obj).removeClass("on");
        $(obj).find("input").prop('checked', false);
        
    }
    else
    {
        $(obj).addClass("on");
        $(obj).find("input").prop('checked', true);
    }
}

function myFunctionshowdaylist(obj)
{
    if(document.getElementById("selecttypeidedit"))
    {
        document.getElementById("dayidlist").style.display = "block";
        document.getElementById("yearlistEdit").style.display = "none";
        document.getElementById("monthidEdit").style.display = "none";
        
    }
    thisRadio(obj)
}
function myFunctionshowyearlist(obj)
{
    if(document.getElementById("selecttypeidedit"))
    {
        document.getElementById("yearlistEdit").style.display = "block";
        document.getElementById("dayidlist").style.display = "none";
        document.getElementById("monthidEdit").style.display = "none";
    }
    thisRadio(obj)
}
function myFunctionshowmonthlist(obj)
{
    if(document.getElementById("selecttypeidedit"))
    {
        document.getElementById("yearlistEdit").style.display = "none";
        document.getElementById("dayidlist").style.display = "none";
        document.getElementById("monthidEdit").style.display = "block";
    }
    thisRadio(obj)
}
function CancelRemiandermain()
{
    
    
    document.getElementById("adddataid").style.display = "none";
    document.getElementById("showalldataid").style.display = "block";
    document.getElementById("editlist").style.display = "none";
    
}
function CancelRemiander(idd)
{
    
    document.getElementById("addreminderbtn").style.display = "block";
    document.getElementById("adddataid").style.display = "none";
    document.getElementById("showalldataid").style.display = "block";
    document.getElementById("editlist").style.display = "none";
    document.getElementById(idd).style.display = "none";
}

function getdataAllRemiandersss()
{
    for(var j=0;j<reminderid.length;j++)
    {
        document.getElementById(reminderid[j]).style.display = "none";
    }
    
    if(document.getElementById("main"))
    {
        document.getElementById("adddataid").style.display = "none";
        document.getElementById("showalldataid").style.display = "block";
    }
    
    
}
function thisCheckbox(obj,idd) {
    var status="0";
    if($(obj).is(".on"))
    {
        $(obj).removeClass("on");
        $(obj).find("input").prop('checked', false);
        status=0;
    }
    else
    {
        $(obj).addClass("on");
        $(obj).find("input").prop('checked', true);
        status=1;
    }
    var DeviceID="1234567890ss";
    var wsUrl="http://angularml.pbodev.info/services/reminder-soap#updateStatusJson";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><updateStatusJson xmlns=\"http://angularml.pbodev.info/services/reminder-soap#updateStatusJson\"><id>'+idd+'</id><deviceId>'+DeviceID+'</deviceId><status>'+status+'</status></updateStatusJson></soap:Body></soap:Envelope>';
    
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
           console.log("strJSON--update status sss-"+strJSON);
           datamessagelist(strJSON);
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           }
           });
    
    
}






