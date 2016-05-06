var add_new_notes = "Add New";
var placeholder_enter_text_here_notes = "Enter text here";
var done_notes = "Done";
var alert_notes = "Alert";
var alert_ok_notes = "OK";
var alert_enter_msg_notes = "Please enter message.";
var delete_notes = "delete";
var share_notes = "share";
var edit_notes = "edit";
var notify_success_delete_notes = "successfully deleted";

function getNotes()
{

if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    
	  if(window.localStorage.getItem("layout")=="slidemenu"){
            $("#reload").hide();
            $("#mainback").show();
        }
        else {
            $("#logo").hide();
            $("#mainback").show();
        }
    if(parseFloat(localStorage.getItem("notesCount")) > 0)
    {
        var complete_note=localStorage.getItem("totalNote");
        complete_note=JSON.parse(complete_note);
        //alert(JSON.stringify(complete_note));
        var keysLength=Object.keys(complete_note);
        
        var notesFirstPageHtml='<div class="note-page"><div class="note-page-heading" onclick="addNotes();"><a class="note-done ui-btn-right">'+add_new_notes+'</a></div><div class="note-page-text">';
        for(i=0;i<keysLength.length;i++)
        {
            notesFirstPageHtml+='<div class="note-date">'+dateFormate(keysLength[i])+'</div><ul class="note-listing">';
            var data=complete_note[keysLength[i]];
            
            for(j=0;j<data.length;j++)
            {
                var streamdata=data[j].id+"@@##@@"+keysLength[i]+"@@##@@"+data[j].message;
                streamdata = streamdata.replace(/ /g, '@!!@#');
                streamdata=streamdata.replace(/\n/g,'####@@####');
                notesFirstPageHtml+='<li id='+streamdata+' onclick="viewNotes(this.id);"><span class="note-text">'+data[j].message+'</span></li>';
            }
            notesFirstPageHtml+='</ul>';
        }
        notesFirstPageHtml+='</div></div>';
        
        // var notesFirstPageHtml='<div class="service_page_div" onclick="viewNotes('+parseFloat(localStorage.getItem("notesCount"))+')" style="display:block"><div class="service_image" style="background-image:url(../images/viewnotes.png);" ></div><div class="service_text_div"> <h3 id="txt_services_heading1_1_1">View Notes</h3></div><span class="more"></sapn></div><div class="service_page_div" onclick="addNotes()"><div class="service_image" style="background-image:url(../images/addnotes.png);" ></div><div class="service_text_div"> <h3 id="txt_services_heading1_1_1">Add Notes</h3></div><span class="more"></sapn></div>';
    }
    else
    {
        if(!localStorage.getItem("notesCount"))
            localStorage.setItem("notesCount",0);
        addNotes();
        return false;
    }
    
    setTimeout(function(){
    	 $("#contentHolder2").addClass("whitestbg"); 
    	 
    	 if(sessionStorage.getItem("fromTools")!=true){
    		 appendHtml(notesFirstPageHtml,2,2);
    	 }
    	 else{
    		 appendHtml(notesFirstPageHtml,2,1);
    	 }
    	 
               $.mobile.resetActivePageHeight();
               if(localStorage.getItem('layout') == 'bottom')
               {
               $('.note-page-footer').attr('style','bottom:62px!important');
               }
               },1000);
    
    
}

function dateReturn(validate)
{
    var todayDateTime = new Date();
    var dd = todayDateTime.getDate();
    var mm = todayDateTime.getMonth()+1;
    var yyyy = todayDateTime.getFullYear();
    if(validate)
    {
        var today = yyyy+'-'+getMonthLoyalty(mm)+'-'+dd;
        return today;
    }
    else
    {
        var today = dd+'-'+getMonthLoyalty(mm)+'-'+yyyy;
        return today;
    }
}
function dateFormate(value)
{
    var datetime=value.split("-");
    var dd = datetime[2];
    var mm = datetime[1];
    var yyyy = datetime[0];
    var today = dd+'-'+getMonthLoyalty(mm)+'-'+yyyy;
    return today;
}

function addNotes(count)
{
    if(count)
    {
        var count1 = count.split('@!!@#').join(' ');
        count1 = count1.split('####@@####').join('\n');
        var id_date_message=count1.split("@@##@@");
        var notesSecondPageHtml='<div class="note-page"><div class="note-page-heading"><a class="note-done ui-btn-right" id='+count+' onclick="editNotes(this.id);">'+done_notes+'</a></div><div class="note-page-text"><textarea id="notevalueedit">'+id_date_message[2]+'</textarea></div></div>';
    }
    else
    {
        var notesCount=parseFloat(localStorage.getItem("notesCount"));
        var notesSecondPageHtml='<div class="note-page"><div class="note-page-heading"><a class="note-done ui-btn-right" id="note-done" onclick="saveNotes();">'+done_notes+'</a></div><div class="note-page-text"><textarea id="notevalue" placeholder='+placeholder_enter_text_here_notes+'></textarea></div></div>';
    }
    
    setTimeout(function(){
               $("#contentHolder2").addClass("whitestbg");//on back removeClass("whitestbg");
               appendHtml(notesSecondPageHtml,2,2);
               
              
               $.mobile.resetActivePageHeight();
                 if(localStorage.getItem('layout') == 'bottom' || mobileADS == true)
               {
               $('.note-page-footer').attr('style','bottom:62px!important');
               }
               },1000);
    
}
function saveNotes()
{
    var notevalue=document.getElementById("notevalue").value;
    notevalue=notevalue.trim();
    if(notevalue!="")
	   {
           var complete_note=localStorage.getItem("totalNote");
           if(complete_note)
           {
               var notedata=localStorage.getItem("totalNote");
               notedata=JSON.parse(notedata);
               var d=new Date().toJSON().slice(0,10);
               var mcount= parseInt(localStorage.getItem("notesCount"));
               if(notedata.hasOwnProperty(d))
               {
                   var td = notedata[d];
                   td.push({"message" : notevalue,"id": mcount});
                   notedata[d]=td;
                   localStorage.setItem("totalNote",JSON.stringify(notedata));
                   mcount=mcount+1;
                   localStorage.setItem("notesCount",mcount);
               }
               else
               {
                   var td = [];
                   td.push({"message" : notevalue,"id": mcount});
                   notedata[d]=td;
                   localStorage.setItem("totalNote",JSON.stringify(notedata));
                   mcount=mcount+1;
                   localStorage.setItem("notesCount",mcount);
               }
           }
           else
           {
               var mcount= parseInt(localStorage.getItem("notesCount"));
               var d=new Date().toJSON().slice(0,10);
               var viewData = {};
               var td = [];
               td.push({"message" : notevalue,"id": mcount});
               viewData[d]=td;
               localStorage.setItem("totalNote",JSON.stringify(viewData));
               mcount=mcount+1;
               localStorage.setItem("notesCount",mcount);
           }
           sessionStorage.setItem("note_back","1");
           getNotes();
       }
    else
	   {
		   alertPopUp(alert_notes,alert_enter_msg_notes);
         
       }
}


function viewNotes(count)
{
    //alert(count);
    var count1 = count.split('@!!@#').join(' ');
    count1 = count1.split('####@@####').join('\n');
    var id_date_message=count1.split("@@##@@");
    
    
    id_date_message[2]=id_date_message[2].replace(/\n/g, '<br>');
    id_date_message[2]=id_date_message[2].replace(/ /g, '&nbsp;');
    
    var viewNotesHtml='';
    /*count=parseFloat(localStorage.getItem("notesCount"));
     for(var i=0;i<count;i++)
     {
     
     var notesSubHeader=localStorage.getItem('notesValue'+i);
     viewNotesHtml+='<div class="service-page"   onclick="addNotes('+i+');" ><h2>'+ notesSubHeader.substring(0,40) + '</h2><span class="more"></span></div>';
     }*/
    //viewNotesHtml+='<div class="note-page"><div class="note-page-text">'+id_date_message[2]+'</div><div class="note-page-footer"><span id='+count+' onclick="deleteNotes(this.id);"> delete</span><span id='+count+' onclick="shareNotes(this.id);"> share</span><span id='+count+' onclick="addNotes(this.id);"> edit</span></div></div>';
     viewNotesHtml+='<div class="note-page"><div class="note-page-text" style="word-wrap: break-word;">'+id_date_message[2]+'</div><div class="note-page-footer"><span id='+count+' onclick="deleteNotes(this.id);"> '+delete_notes+'</span><span id='+count+' onclick="shareNotes(this.id);"> '+share_notes+'</span><span id='+count+' onclick="addNotes(this.id);"> '+edit_notes+'</span></div></div>';
    $("#contentHolder3").empty();
    setTimeout(function(){
               
               $("#contentHolder3").addClass("whitestbg");
               appendHtml(viewNotesHtml,3,3);
               
               $.mobile.resetActivePageHeight();
               if(localStorage.getItem('layout') == 'bottom' || mobileADS == true)
               {
               $('.note-page-footer').attr('style','bottom:62px!important');
               }
               },1000);
    
}
function editNotes(count)
{
    var count1 = count.split('@!!@#').join(' ');
    count1 = count1.split('####@@####').join('\n');
    var id_date_message=count1.split("@@##@@");
    var id=id_date_message[0];
    var mdate=id_date_message[1];
    var notevalue=document.getElementById("notevalueedit").value;
    if(notevalue!="")
    {
        var complete_note=localStorage.getItem("totalNote");
        complete_note=JSON.parse(complete_note);
        var data=complete_note[mdate];
        for(i=0;i<data.length;i++)
        {
            if(data[i].id==id)
            {
                data[i].message=notevalue;
                localStorage.setItem("totalNote",JSON.stringify(complete_note));
                sessionStorage.setItem("note_back","1");
                getNotes();
                return;
            }
        }
    }
    else
    {
    	  alertPopUp(alert_notes,alert_enter_msg_notes);
       
    }
}
function deleteNotes(count)
{
    var count1 = count.split('@!!@#').join(' ');
    var id_date_message=count1.split("@@##@@");
    var id=id_date_message[0];
    var mdate=id_date_message[1];
    var complete_note=localStorage.getItem("totalNote");
    complete_note=JSON.parse(complete_note);
    var data=complete_note[mdate];
    if(data.length==1)
    {
        delete complete_note[mdate];
        //alert(JSON.stringify(complete_note));
        localStorage.setItem("totalNote",JSON.stringify(complete_note));
        var keysLength=Object.keys(JSON.parse(localStorage.getItem("totalNote")));
        if(keysLength==0)
            localStorage.setItem("notesCount",0);
        sessionStorage.setItem("note_back","1");
        getNotes();
		navigator.notification.alert(
     notify_success_delete_notes,
     alertDismissed,
     '',
     alert_ok_notes
     );
        return;
    }
    else
    {
        for(i=0;i<data.length;i++)
        {
            if(data[i].id==id)
            {
                data.splice(i,1);
                localStorage.setItem("totalNote",JSON.stringify(complete_note));
                sessionStorage.setItem("note_back","1");
                getNotes();
				
				navigator.notification.alert(
     notify_success_delete_notes,
     alertDismissed,
     '',
     alert_ok_notes
     );
                return;
            }
        }
    }
    
}
function shareNotes(count)
{
    var count1 = count.split('@!!@#').join(' ');
    count1 = count1.split('####@@####').join('\n');
    var id_date_message=count1.split("@@##@@");
    toaster.shareNotes(id_date_message[2]);
}


