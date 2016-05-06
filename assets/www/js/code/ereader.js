var masterData = $.parseXML(window.sessionStorage.getItem("xml"));
var coverImageCounter = 0;
var readerFilesURL = "";
var readerFilesTitleName = "";
var downloadingFileCount = 0;
var k = 0;
var totalDownloadingUrl = new Array();
var myurl_array = [];
var myTitle_array = [];
var output_delete = "";
var filesystemepub;
var image_url_reader = [];
var readerFilesimage = ""
var checkForNativeReader="NO";

var pleasecheckinternetconnection_ereader = 'Please check Internet connection.';
var message_ereader = 'Message';
var ok_erader = 'OK';
var downloadingerror_ereader = "downloading error";
var delete_ereader = "Delete";
var selecookselete_ereader = 'Select Books to delete';
var done_ereader = 'Done';
var booklist_ereader = "Book List!";



function getEReaderData(index) {
if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
    $('.appypie-loader').show();
    jQuery("#bookmark").on("tap", function(event) {
                           bookmark();
                           });
    sessionStorage.setItem("ereaderPage", "true");
    sessionStorage.setItem("downloadingFileCount", 0);
    
    readerFilesURL = "";
    readerFilesTitleName = "";
    output = "";
    if (false) {
        //alert("22");
        loadLibrary();
        
    } else {
        $ereaderIndex = $(masterData).find("ereader[indexval=" + index + "]");
        readerFilesURL = $ereaderIndex.find("ereaderurls").text();
        readerFilesTitleName = $ereaderIndex.find("ereadername").text();
        readerFilesimage = $ereaderIndex.find("ereaderIcon").text();
        search1();
		checkForNativeReader=$ereaderIndex.find("enablePdfThirdparty").text().trim();
    }
}

function search1() {
    window.requestFileSystem = window.requestFileSystem || window.webkitRequestFileSystem;
    window.requestFileSystem(LocalFileSystem.PERSISTENT, 0, gotFSReader, fail);
    //alert("39");
}

function fail() {
    //alert("43");
}

function gotFSReader(fileSystem) {
   // alert("goFSReader");
    $('.appypie-loader').show();
    
    var viewData = new Array();
    myurl_array.splice(0, myurl_array.length);
    myTitle_array.splice(0, myTitle_array.length);
    window.rootFS = fileSystem.root;
    //alert("root path>>>>>>"+JSON.stringify(fileSystem.root));
    //applicationDocumentDIR = fileSystem.root.nativeURL;
	applicationDocumentDIR = "storage/emulated/0/";
    var my_url = readerFilesURL;
    var my_urltitle = readerFilesTitleName;
    myurl_array = my_url.split(",");
    myTitle_array = my_urltitle.split("|");
    image_url_reader = readerFilesimage.split("|");
    //alert("53"+myurl_array.length);
    for (i = myurl_array.length - 1; i >= 0; i--) {
        var n = myurl_array[i].substr(myurl_array[i].lastIndexOf('/') + 1);
        var n = n.replace(/\//g, "");
                          //alert("urls >>>"+n);
                          viewData[i] = n;
                          }
                          
						  /*
                          // code to delete previous files...from Downloads folder
                          fileSystem.root.getDirectory("Downloads", {
                                                       create: true,
                                                       exclusive: false
                                                       }, function(dirEntry) {
                                                       var directoryReader = dirEntry.createReader();
                                                       directoryReader.readEntries(function(entries) {
                                                                                   //alert("65"+entries.length);
                                                                                   for (j = entries.length - 1; j >= 0; j--) {
                                                                                   var flag = false;
                                                                                   for (i = myurl_array.length - 1; i >= 0; i--) {
                                                                                   var temp_n = viewData[i];
                                                                                   temp_n = temp_n.replace(/%20/g, '');
                                                                                   temp_n = temp_n.replace(/ /g, '');
                                                                                   if (entries[j].name == temp_n) {
                                                                                   flag = true;
                                                                                   break;
                                                                                   }
                                                                                   }
                                                                                   
                                                                                   if (flag == false) {
                                                                                   deleteBook(entries[j].name);
                                                                                   //console.log("deleting files.."+entries[j].name);
                                                                                   }
                                                                                   }
                                                                                   }, fail);
                                                       }, fail);
                          
                          
                          // code to delete previous files...from epubtemp folder
                          fileSystem.root.getDirectory("epubtemp", {
                                                       create: true,
                                                       exclusive: false
                                                       }, function(dirEntry) {
                                                       var directoryReader = dirEntry.createReader();
                                                       directoryReader.readEntries(function(entries) {
                                                                                   //alert("90"+entries.length);
                                                                                   for (j = entries.length - 1; j >= 0; j--) {
                                                                                   var flag = false;
                                                                                   for (i = myurl_array.length - 1; i >= 0; i--) {
                                                                                   var temp_n = viewData[i];
                                                                                   temp_n = temp_n.replace(/%20/g, '');
                                                                                   temp_n = temp_n.replace(/ /g, '');
                                                                                   if (entries[j].name == temp_n) {
                                                                                   flag = true;
                                                                                   break;
                                                                                   }
                                                                                   }
                                                                                   
                                                                                   if (flag == false) {
                                                                                   deleteBook(entries[j].name);
                                                                                   //console.log("deleting files.."+entries[j].name);
                                                                                   }
                                                                                   }
                                                                                   }, fail);
                                                       }, fail);
                          
                          */
                          for (i = myurl_array.length - 1; i >= 0; i--) {
                          
                          ////////checking for downloaded files in downloads folder.......
                          if (viewData[i].indexOf(".epub") != -1) {
                          var iValue = 0;
                          fileSystem.root.getDirectory("Downloads", {
                                                       create: true,
                                                       exclusive: false
                                                       }, function(dirEntry) {
                                                       var directoryReader = dirEntry.createReader();
                                                       directoryReader.readEntries(function(entries) {
                                                                                   
                                                                                   //alert("down="+iValue);
                                                                                   var flag = false;
                                                                                   for (j = entries.length - 1; j >= 0; j--) {
                                                                                   if (entries[j].name == viewData[iValue]) {
                                                                                   flag = true;
                                                                                   break;
                                                                                   }
                                                                                   }
                                                                                   
                                                                                   if (flag == false) {
                                                                                   //alert("135");
                                                                                   //alert("pushing epub"+myurl_array[iValue]);
                                                                                   totalDownloadingUrl.push(myurl_array[iValue]);
                                                                                   }
                                                                                   iValue++;
                                                                                   }, fail);
                                                       }, fail);
                          }
                          
                          ////////checking for download files in epubtemp folder.......
                          
                          if (viewData[i].indexOf(".pdf") != -1) {
                          var iValue = 0;
                          fileSystem.root.getDirectory("epubtemp", {
                                                       create: true,
                                                       exclusive: false
                                                       }, function(dirEntry) {
                                                       var directoryReader = dirEntry.createReader();
                                                       directoryReader.readEntries(function(entries) {
                                                                                   var flag = false;
                                                                                   //alert("epub="+iValue);
                                                                                   for (j = entries.length - 1; j >= 0; j--) {
                                                                                   
                                                                                   if (entries[j].name == viewData[i]) {
                                                                                   flag = true;
                                                                                   break;
                                                                                   }
                                                                                   }
                                                                                   
                                                                                   if (flag == false) {
                                                                                   //alert("pushing pdf"+myurl_array[iValue]);
                                                                                   totalDownloadingUrl.push(myurl_array[iValue]);
                                                                                   }
                                                                                   iValue++;
                                                                                   }, fail);
                                                       }, fail);
                          }
                          
                          //alert(i+"\n"+(myurl_array.length-1));
                          /*  if(i==(myurl_array.length-1))
                           {
                           downloadingFileCount=totalDownloadingUrl.length;
                           downloadfiles();
                           }*/
                          
                          }
                          loadLibrary();
                          
                          // },200);
                          }
                          
                          
                          function downloadfiles() {
                          //alert("190");
                          setTimeout(function() {
                                     //alert(totalDownloadingUrl.length);
                                     if (totalDownloadingUrl.length == 0) {
                                     loadLibrary();
                                     } else {
                                     sessionStorage.setItem("downloadingFileCount", totalDownloadingUrl.length);
                                     for (j = totalDownloadingUrl.length - 1; j >= 0; j--) {
                                     //console.log("downloading file>>>>"+totalDownloadingUrl[j]+"  start");
                                     fileListTOdownloadForLesson(totalDownloadingUrl[j], j);
                                     }
                                     }
                                     }, 1000);
                          //}
                          }
                          
                          function deleteBook(bookname) {
                          window.requestFileSystem = window.requestFileSystem || window.webkitRequestFileSystem;
                          window.requestFileSystem(LocalFileSystem.PERSISTENT, 0, function(fileSystem) {
                                                   
                                                   if (bookname.indexOf(".epub") != -1) {
                                                   // delete folder from epubtemp directory
                                                   fileSystem.root.getDirectory("epubtemp/" + bookname + "", {
                                                                                create: false
                                                                                }, function(directory) {
                                                                                directory.removeRecursively(function() {}, fail);
                                                                                });
                                                   
                                                   // delete file from downloads directory
                                                   fileSystem.root.getFile("Downloads/" + bookname + "", {
                                                                           create: false
                                                                           }, function(f) {
                                                                           f.remove(function() {}, fail);
                                                                           });
                                                   } else if (bookname.indexOf(".pdf") != -1) {
                                                   // delete file from epubtemp directory
                                                   fileSystem.root.getFile("epubtemp/" + bookname + "", {
                                                                           create: false
                                                                           }, function(f) {
                                                                           f.remove(function() {}, fail);
                                                                           });
                                                   }
                                                   
                                                   }, fail);
                          }
                          
                          
                          function fileListTOdownloadForLesson(url) {
                          //alert(url);
						  
                        	  //sessionStorage.setItem("ereaderDownload", "true");
                          if (!checkNetworkConnection()) {
						  
						  
						  sessionStorage.setItem("ereaderDownload", "false");
                          // show alert message "Please  enable intenet to download file."
                          navigator.notification.alert(
                        		  pleasecheckinternetconnection_ereader,
                                                       alertDismissed,
                                                       message_ereader,
                                                       ok_erader
                                                       );
                          } else {
                          
                          var url = decodeURIComponent(url);
                          var n = url.substr(url.lastIndexOf('/') + 1);
                          n = n.replace(/\//g, "");
                                        
                                        var fileURl = "";
                                        if (url.indexOf(".epub") != -1) {
                                        fileURl = applicationDocumentDIR + "Downloads/" + n;
                                        } else if (url.indexOf(".pdf") != -1) {
                                        fileURl = applicationDocumentDIR + "epubtemp/" + n;
                                        fileURl = fileURl.replace('file://', "");
                                        fileURl = fileURl.replace(/\/\//g, "/");
                                                                  fileURl = fileURl.replace(/ /g, "");
                                                                  }
                                                                  downloadBooks(url, fileURl);
                                                                  }
                                                                  }
                                                                  
                                                                  function downloadBooks(downloadfileURl, fileURL) {
                                                                 console.log("krishna reader==="+downloadfileURl +"\n"+fileURL);
                                                                 var fileUrlValue=toaster.checkFile(fileURL);
                                                                 if(fileUrlValue)
																 {
																 console.log("krishna reader===fileUrlValue exist");
																 if(fileURL.indexOf(".epub") != -1)
                                                                 {
																 var leafname= fileURL.split('\\').pop().split('/').pop();
																 console.log("krishna reader===leafname"+leafname);
																 readBook(leafname);
																 }
																 else
																 {
																 readBook(fileURL);
																 }
																 }
																 else
																 {
															      $('.appypie-loader').show();
                                                                  var fileTransfer = new FileTransfer();
                                                                  var uri = encodeURI(downloadfileURl);
                                                                  fileTransfer.download(uri, fileURL,
                                                                                        function(entry) {
																						sessionStorage.setItem("ereaderDownload", "false");
                                                                                        $('.appypie-loader').hide();
                                                                                        k++;
                                                                                        //alert(JSON.stringify(entry));
                                                                                        if (entry.nativeURL.indexOf(".epub") != -1) {
                                                                                        window.plugins.downloadunzip.show({
                                                                                                                          url: entry.nativeURL,
                                                                                                                          info: entry.name
																													
                                                                                                                          }, function() {
                                                                                                                          //alert("success");
                                                                                                                          }, function() {});
                                                                                            //alert("entry.name"+entry.name);                              
                                                                                        //console.log("unzipping epub file: " + entry.nativeURL);
                                                                                        }
                                                                                        // console.log("download complete: " + entry.nativeURL);
                                                                                        // var downloadingFileCount1=sessionStorage.getItem("downloadingFileCount");
                                                                                        // if(k==downloadingFileCount1) {
                                                                                        // alert("loading library");
                                                                                        setTimeout(function() {
                                                                                                  // readBook(entry.name);
                                                                                                  if(downloadfileURl.indexOf(".epub") != -1)
                                                                                        	{
                                                                                        		readBook(entry.name);
                                                                                        	}
                                                                                        	else
                                                                                        	{
                                                                                        		 readBook(entry.nativeURL);
                                                                                        	}
                                                                                                   }, 1000);
                                                                                        // }
                                                                                        },
                                                                                        function(error) {
																						sessionStorage.setItem("ereaderDownload", "false");
																						
                                                                                        alert(downloadingerror_ereader);
                                                                                        k++;
                                                                                        $('.appypie-loader').hide();
                                                                                        console.log("download error source " + error.source);
                                                                                        console.log("download error target " + error.target);
                                                                                        console.log("upload error code" + error.code);
                                                                                        },
                                                                                        false
                                                                                        );
                                                                  }
                                                                  }
                                                                  function loadLibrary() {
                                                                  gotoNextPage();
                                                                  }
                                                                  
                                                                  function gotoNextPage() {
                                                                	  
                                                                	  
                                                                  var readerhome_HTML = "";
                                                                  var index = localStorage.getItem("readerIndex");
                                                                  var pageIndex = localStorage.getItem("readerpageIndex");
                                                                  readerhome_HTML = '<div class="main">\
                                                                  <div class="top_line"></div>\
                                                                  \
                                                                  <div class="bookmark_txt" style="font-size:22px;" id="dynamic_header_delete"></div>\
                                                                  <span id="delete_button" style="display:none;" onclick="delete_books();" class="delete-reader-library">'+delete_ereader+'</span>\
                                                                  <div class="today_view" style="padding-bottom:70px;">\
                                                                  <div class="today_view_content">\
                                                                  <table width="100%" border="0" cellspacing="5" cellpadding="5">\
                                                                  <tr>\
                                                                  <ul class="main2 custom-color" id="dynamic_ereader">\
                                                                  \
                                                                  </ul>\
                                                                  <ul class="main2" id="dynamic_hidden_delete" style="display:none;">\
                                                                  \
                                                                  </ul>\
                                                                  </tr>\
                                                                  </table>\
                                                                  </div>\
                                                                  </div>\
                                                                  \
                                                                  </div>';
                                                                  
                                                                  appendHtml('<div style="color:' + sessionStorage.getItem('pageTextColor') + ' !important;" >' + readerhome_HTML + '</div>', '', 1);
                                                                  
                                                                  
                                                                  localStorage.setItem("activeView", "reader");
                                                                  localStorage.setItem("pageIndex", pageIndex);
                                                                  localStorage.setItem("index", index);
                                                                  window.requestFileSystem = window.requestFileSystem || window.webkitRequestFileSystem;
                                                                  window.requestFileSystem(LocalFileSystem.PERSISTENT, 0, gotFSFromSDCard, failFromSDCard);
                                                                  
                                                                  }
                                                                  
                                                                  function failFromSDCard(error) {}
                                                                  
                                                                  function gotFSFromSDCard(fileSystem) {
																   var pdfUrl = [];
                                                                  output = '';
                                                                  //alert(myurl_array.length);
                                                                  var checkDriveOnly = "";
                                                                  var myJson = {
                                                                  objArray1: {}
                                                                  };
                                                                  
                                                                  $("#reload").hide();
                                                                  //$("#bookmark").show();
                                                                  $("#mainbackfoodecom").hide();
                                                                  window.rootFS = fileSystem.root;
                                                       applicationDocumentDIR ="storage/emulated/0/";
                                                                  filesystemepub = fileSystem;
                                                                  
                                                                  
                                                                  
                                                                  var i = myurl_array.length - 1;
                                                                  //
																  var pdfFileName=readerFilesTitleName.split("|");
																  //
                                                                  while (i >= 0) {
                                                                  
                                                                  var fileext2 = getFileExtension(myurl_array[i]);
                                                                  if (fileext2 == 'epub') {
                                                                  
                                                                  var n = myurl_array[i].substr(myurl_array[i].lastIndexOf('/') + 1);
                                                                  var n = n.replace(/\//g, "");
                                                                                    var fileURl = applicationDocumentDIR + "/Downloads/" + n;
                                                                                    fileURl = decodeURI(fileURl);
                                                                                    
                                                                                    checkepub(myurl_array[i], fileURl, filesystemepub, i);
                                                                                    } else if (fileext2 == 'pdf') {
                                                                                    //var n1 = myurl_array[i].lastIndexOf("/");
                                                                                    //var n = myurl_array[i].substr(n1+1);
                                                                                    //var n = myurl_array[i].substr(myurl_array[i].indexOf('://') + 3);
                                                                                    //n = n.replace(/\//g, "");
                                                                                    //var fileURl = applicationDocumentDIR + "/epubtemp/" + n;
                                                                                    // fileURl = decodeURI(fileURl);
																					pdfUrl[i] = myurl_array[i];
                                                                                    //checkpdf(myurl_array[i], filesystemepub, i);
                                                                                    } else {
                                                                                    image_url = "images/pdf1.png";
                                                                                   var bookImage=image_url_reader[i];
																				  // alert("bookImage"+bookImage);
																				 //  alert("myurl_array[i]"+myurl_array[i])
                                                                                    //output += "<li id=" + myurl_array[i] + " onclick='readDriveFile(this.id);'><div class='e-readerimage'> "+ returnImageHtml(bookImage)+ "</div><span class='book_name'>" + myurl_array[i] + "</span><span class='author_name'></span></li>";
																					output += "<li id=" + myurl_array[i] + " onclick='readDriveFile(this.id);'><div class='e-readerimage'> "+ returnImageHtml(bookImage)+ "</div><span class='book_name'>" + pdfFileName[i] + "</span><span class='author_name'></span></li>";
                                                                                    document.getElementById('dynamic_ereader').innerHTML = output;
                                                                                    }
                                                                                    i--;
                                                                                    }
                                                                                    checkpdf(pdfUrl,filesystemepub);
                                                                                    
                                                                                    }
                                                                                    
                                                                                    function checkepub(downloadfileURl, fileURL, fileSystem, ivalue) {
                                                                                    
                                                                                    	//alert("dgsdljkg");
                                                                                    var n = downloadfileURl.substr(downloadfileURl.lastIndexOf('/') + 1);
                                                                                    n = n.replace(/\//g, "");
                                                                                                  n = decodeURI(n);
                                                                                    
                                                                                    //alert(n);
                                                                                                  
                                                                                                  fileSystem.root.getDirectory("epubtemp/" + n, {
                                                                                                                               create: false
                                                                                                                               }, function(directory) {
                                                                                                                               getfilenameandimage(n, directory.nativeURL, fileSystem,ivalue);
                                                                                                                               }, function() {
                                                                                                                                 //alert(n);
                                                                                                                               image_url = "images/epup.png";
                                                                                                                               var bookTitle = "";
                                                                                                                               if (myTitle_array[ivalue] == "NULL") {
                                                                                                                               bookTitle = myurl_array[ivalue];
                                                                                                                               } else {
                                                                                                                               bookTitle = myTitle_array[ivalue];
                                                                                                                               }
                                                                                                                               //var bookImage=image_url_reader[ivalue];  
																																if(image_url_reader[ivalue] != "null" && window.localStorage.getItem("applicationID") != "98740ab4f91c")
                                                                                                                                                              {
                                                                                                                                                               image_url = image_url_reader[ivalue];
                                                                                                                                                              }
                                                                                                                                                              else
                                                                                                                                                              {
                                                                                                                                                               image_url = "images/epup.png";
                                                                                                                                                              }
																																//alert(image_url);
																																
																																output += "<li id=" + myurl_array[ivalue] + " onclick='fileListTOdownloadForLesson(this.id);'><div class='e-readerimage'> "+ returnImageHtml(image_url)+ "</div><span class='book_name'>" + bookTitle + "</span><span class='author_name'></span></li>";
                                                                                                                               document.getElementById('dynamic_ereader').innerHTML = output;
                                                                                                                               (function($) {
                                                                                                                                
                                                                                                                                $.fn.equalHeights = function() {
                                                                                                                                var maxHeight = 0,
                                                                                                                                $this = $(this);
                                                                                                                                
                                                                                                                                $this.each(function() {
                                                                                                                                           var height = $(this).innerHeight();
                                                                                                                                           
                                                                                                                                           if (height > maxHeight) {
                                                                                                                                           maxHeight = height;
                                                                                                                                           }
                                                                                                                                           });
                                                                                                                                return $this.css('min-height', maxHeight);
                                                                                                                                };
                                                                                                                                
                                                                                                                                })(jQuery);
                                                                                                                               setTimeout(function() {
                                                                                                                                          $('.main2 li').equalHeights();
                                                                                                                                          }, 500);
                                                                                                                               });
                                                                                                  }
                                                                                                  
                                                                                                  function checkpdf(downloadfileURl,fileSystem,ivalue)
                                                                                      {
                                                                                      
                                                                                    	   //alert(downloadfileURl);
                                                                                      /* var n1 = downloadfileURl.lastIndexOf("/");
                                                                                       var n = downloadfileURl.substr(n1+1);*/
                                                                                    	  var p = downloadfileURl.length - 1;
                                                                                    	  //alert(myTitle_array[p]);
                                                                                    	  var x =0;
                                                                                    	  var y = p;
                                                                                    	  while(x <= p)
                                                                                    	  {
																						  
																						   var newValue=""+downloadfileURl[x];
                                                                                    		  if(newValue.trim() == "undefined")
                                                                                    			  {
                                                                                    			 // alert("djkdf");
                                                                                    			    x++;
                                                                                    			  } 
                                                                                    		 // alert(x);
                                                                                    		 // alert(downloadfileURl[x]);
                                                                                      var n = downloadfileURl[x].substr(downloadfileURl[x].lastIndexOf('/')+1);
                                                                                      n = n.replace(/\//g, "");
                                                                                                     n=n.replace(/%20/g, '');
                                                                                      //alert("n: "+ n);
                                                                                      
                                                                                                    fileSystem.root.getDirectory("epubtemp",{create: true, exclusive: false}, function(dirEntry){
                                                                                                                                 var directoryReader = dirEntry.createReader();
                                                                                                                                 directoryReader.readEntries(function(entries)
                                                                                                                                                             {
                                                                                                                                	 
                                                                                                                                	                         //alert("entries: " + entries.length);
                                                                                                                                                             var flag=false;
                                                                                                                                                             var ivalueinner;
                                                                                                                                                             for(j=entries.length-1;j>=0;j--)
                                                                                                                                                             {
                                                                                                                                                             /*alert("entries[j].name :"+ entries[j].name);
                                                                                                                                                             alert("n :"+ myurl_array[y].substr(myurl_array[y].lastIndexOf('/')+1).replace(/\//g, "").replace(/%20/g, ''));
*/                                                                                                                                                             if(entries[j].name==myurl_array[y].substr(myurl_array[y].lastIndexOf('/')+1).replace(/\//g, "").replace(/%20/g, ''))
                                                                                                                                                             {
                                                                                                                                                            	 n = myurl_array[y].substr(myurl_array[y].lastIndexOf('/')+1).replace(/\//g, "").replace(/%20/g, '');
                                                                                                                                                             flag=true;
                                                                                                                                                             ivalueinner=j;
                                                                                                                                                             break;
                                                                                                                                                             }
                                                                                                                                                             
                                                                                                                                                             }
                                                                                                                                                             
                                                                                                                                                             var bookTitle="";
                                                                                                                                                             var bookUrl="";
                                                                                                                                                             if(myTitle_array[y]=="NULL")
                                                                                                                                                             {
                                                                                                                                                             bookTitle=myurl_array[y];
                                                                                                                                                             }
                                                                                                                                                             else
                                                                                                                                                             {
                                                                                                                                                             bookTitle=myTitle_array[y];
                                                                                                                                                             }
                                                                                                                                                             
                                                                                                                                                             bookUrl = myurl_array[y];
                                                                                                                                                             if(flag==false)
                                                                                                                                                             {
                                                                                                                                                            if(image_url_reader[y] != "null" && window.localStorage.getItem("applicationID") != "98740ab4f91c")
                                                                                                                                                              {
                                                                                                                                                               image_url = image_url_reader[y];
                                                                                                                                                              }
                                                                                                                                                              else
                                                                                                                                                              {
                                                                                                                                                               image_url = "images/pdf.png";
                                                                                                                                                              }
                                                                                                                                                             output  = "<li id=" +bookTitle + " onclick='fileListTOdownloadForLesson(\""+bookUrl+"\");'><div class='e-readerimage'> "+ returnImageHtml(image_url)+ "</div><span class='book_name'>" + bookTitle+ "</span><span class='author_name'></span></li>"+output;
                                                                                                                                                             document.getElementById('dynamic_ereader').innerHTML = output;
                                                                                                                                                             
                                                                                                                                                             }
                                                                                                                                                             else
                                                                                                                                                             {
                                                                                                                                                            	/* alert("flag true");
                                                                                                                                                            	 alert(n);*/
                                                                                                                                                             if(image_url_reader[y] != "null" && window.localStorage.getItem("applicationID") != "98740ab4f91c")
                                                                                                                                                              {
                                                                                                                                                               image_url = image_url_reader[y];
                                                                                                                                                              }
                                                                                                                                                              else
                                                                                                                                                              {
                                                                                                                                                               image_url = "images/pdf1.png";
                                                                                                                                                              }
                                                                                                                                                             result=applicationDocumentDIR+"/epubtemp/"+n;
                                                                                                                                                             output = "<li id=" + result + " onclick='readBook(this.id);'><div class='e-readerimage'> "+ returnImageHtml(image_url)+ "</div><span class='book_name'>" + bookTitle + "</span><span class='author_name'></span></li>"+output;
                                                                                                                                                             document.getElementById('dynamic_ereader').innerHTML = output;
                                                                                                                                                             }
                                                                                                                                                             (function($) {
                                                                                                                                                              
                                                                                                                                                              $.fn.equalHeights = function() {
                                                                                                                                                              var maxHeight = 0,
                                                                                                                                                              $this = $(this);
                                                                                                                                                              
                                                                                                                                                              $this.each( function() {
                                                                                                                                                                         var height = $(this).innerHeight();
                                                                                                                                                                         
                                                                                                                                                                         if ( height > maxHeight ) { maxHeight = height; }
                                                                                                                                                                         });
                                                                                                                                                              return $this.css('min-height', maxHeight);
                                                                                                                                                              };
                                                                                                                                                              
                                                                                                                                                              })(jQuery);
                                                                                                                                                              setTimeout(function(){
                                                                                                                                                                           $('.main2 li').equalHeights();
                                                                                                                                                                           },100);
                                                                                                                                                            
                                                                                                                                	  y--;
                                                                                                                                	
                                                                                                                                	 },fail);
                                                                                                                                 //gaurav
                                                                                                                                 });
                                                                                                    x++;
                                                                                                    
                                                                                    	  }
                                                                                    	  
                                                                                    	    
                                                                                                    }
                                                                                                                
                                                                                                                
                                                                                                                
                                                                                                                
                                                                                                                function getfilenameandimage(entryname, directoryname, fileSystem,ivalue) {
                                                                                                                //alert(applicationDocumentDIR);
                                                                                                                // directoryname="file:///storage/emulated/0/epubtemp";
                                                                                                                directoryname = applicationDocumentDIR + "epubtemp";
                                                                                                                var path_of_filee;
                                                                                                                if (entryname != '.DS_Store') {
                                                                                                                path_of_filee = directoryname + "/" + entryname + "/META-INF/container.xml";
                                                                                                                
                                                                                                                $.ajax({
                                                                                                                       type: "GET",
                                                                                                                       url: path_of_filee,
                                                                                                                       success: function(xml) {
                                                                                                                       
                                                                                                                       $(xml).find('rootfile').each(function(i, j) {
                                                                                                                                                    var path_my = $(j).attr("full-path");
                                                                                                                                                    var n = path_my.lastIndexOf('/');
                                                                                                                                                    var temp = path_my.substring(0, n);
                                                                                                                                                    var ddd = [directoryname, "/", entryname, "/", path_my];
                                                                                                                                                    var path_of_file = ddd.join("");
                                                                                                                                                    var directoryname2 = directoryname + "/" + entryname;
                                                                                                                                                    if (temp != "") {
                                                                                                                                                    directoryname2 = directoryname + "/" + entryname + "/" + temp;
                                                                                                                                                    }
                                                                                                                                                    authername(path_of_file, directoryname2, fileSystem,ivalue);
                                                                                                                                                    });
                                                                                                                       }
                                                                                                                       });
                                                                                                                }
                                                                                                                }
                                                                                                                
                                                                                                                
                                                                                                                function authername(path_of_file, directoryname, fileSystem,ivalue) {
                                                                                                                var n = directoryname.lastIndexOf('/');
                                                                                                                var temp = directoryname.substring(n + 1);
                                                                                                                
                                                                                                                var fileext2 = getFileExtension(temp);
                                                                                                                var result;
                                                                                                                if (fileext2 == 'epub' || fileext2 == 'Epub' || fileext2 == 'EPUB') {
                                                                                                                result = temp;
                                                                                                                } else {
                                                                                                                result = directoryname.substring(0, n);
                                                                                                                n = result.lastIndexOf('/');
                                                                                                                result = result.substring(n + 1);
                                                                                                                }
                                                                                                                
                                                                                                                var image_url = '';
                                                                                                                var directoryname2 = directoryname.substring(7);
                                                                                                                var pathh = directoryname2 + "/images";
                                                                                                                fileSystem.root.getDirectory(pathh, {
                                                                                                                                             create: false
                                                                                                                                             },
                                                                                                                                             function(directory) {
                                                                                                                                             var directoryReader = directory.createReader();
                                                                                                                                             directoryReader.readEntries(function(images_file) {
                                                                                                                                                                         for (j = images_file.length - 1; j >= 0; j--) {
                                                                                                                                                                         var fileext = getFileExtension(images_file[j].name);
                                                                                                                                                                         if (fileext == 'jpg' || fileext == 'png' || fileext == 'jpeg') {
                                                                                                                                                                         image_url = images_file[j].nativeURL;
                                                                                                                                                                       //  j = images_file.length;
                                                                                                                                                                         }
                                                                                                                                                                         }
                                                                                                                                                                         }, failFromSDCard);
                                                                                                                                             });
                                                                                                                var pathh = directoryname2 + "/Images";
                                                                                                                
                                                                                                                fileSystem.root.getDirectory(pathh, {
                                                                                                                                             create: false
                                                                                                                                             },
                                                                                                                                             function(directory) {
                                                                                                                                             
                                                                                                                                             var directoryReader = directory.createReader();
                                                                                                                                             directoryReader.readEntries(function(images_file) {
                                                                                                                                                                         for (j = images_file.length - 1; j >= 0; j--) {
                                                                                                                                                                         var fileext = getFileExtension(images_file[j].name);
                                                                                                                                                                         if (fileext == 'jpg' || fileext == 'png' || fileext == 'jpeg') {
                                                                                                                                                                         image_url = images_file[j].nativeURL;
                                                                                                                                                                         //j = images_file.length;
                                                                                                                                                                         }
                                                                                                                                                                         }
                                                                                                                                                                         }, failFromSDCard);
                                                                                                                                             });
                                                                                                                
                                                                                                                var pathh = directoryname2 + "/Text";
                                                                                                                
                                                                                                                fileSystem.root.getDirectory(pathh, {
                                                                                                                                             create: false
                                                                                                                                             },
                                                                                                                                             function(directory) {
                                                                                                                                             
                                                                                                                                             var directoryReader = directory.createReader();
                                                                                                                                             directoryReader.readEntries(function(images_file) {
                                                                                                                                                                         for (j = images_file.length - 1; j >= 0; j--) {
                                                                                                                                                                         var fileext = getFileExtension(images_file[j].name);
                                                                                                                                                                         if (fileext == 'jpg' || fileext == 'png' || fileext == 'jpeg') {
                                                                                                                                                                         image_url = images_file[j].nativeURL;
                                                                                                                                                                         //j = images_file.length;
                                                                                                                                                                         }
                                                                                                                                                                         }
                                                                                                                                                                         }, failFromSDCard);
                                                                                                                                             });
                                                                                                                
                                                                                                                var pathh = directoryname2 + "/Image";
                                                                                                                fileSystem.root.getDirectory(pathh, {
                                                                                                                                             create: false
                                                                                                                                             },
                                                                                                                                             function(directory) {
                                                                                                                                             var directoryReader = directory.createReader();
                                                                                                                                             directoryReader.readEntries(function(images_file) {
                                                                                                                                                                         for (j = images_file.length - 1; j >= 0; j--) {
                                                                                                                                                                         var fileext = getFileExtension(images_file[j].name);
                                                                                                                                                                         if (fileext == 'jpg' || fileext == 'png' || fileext == 'jpeg') {
                                                                                                                                                                         image_url = images_file[j].nativeURL;
                                                                                                                                                                         //j = images_file.length;
                                                                                                                                                                         }
                                                                                                                                                                         }
                                                                                                                                                                         }, failFromSDCard);
                                                                                                                                             });
                                                                                                                var pathh = directoryname2 + "/image";
                                                                                                                fileSystem.root.getDirectory(pathh, {
                                                                                                                                             create: false
                                                                                                                                             },
                                                                                                                                             function(directory) {
                                                                                                                                             var directoryReader = directory.createReader();
                                                                                                                                             directoryReader.readEntries(function(images_file) {
                                                                                                                                                                         for (j = images_file.length - 1; j >= 0; j--) {
                                                                                                                                                                         var fileext = getFileExtension(images_file[j].name);
                                                                                                                                                                         if (fileext == 'jpg' || fileext == 'png' || fileext == 'jpeg') {
                                                                                                                                                                         image_url = images_file[j].nativeURL;
                                                                                                                                                                         //j = images_file.length;
                                                                                                                                                                         }
                                                                                                                                                                         }
                                                                                                                                                                         }, failFromSDCard);
                                                                                                                                             });
                                                                                                                var pathh = directoryname2 + "/img";
                                                                                                                fileSystem.root.getDirectory(pathh, {
                                                                                                                                             create: false
                                                                                                                                             },
                                                                                                                                             function(directory) {
                                                                                                                                             var directoryReader = directory.createReader();
                                                                                                                                             directoryReader.readEntries(function(images_file) {
                                                                                                                                                                         for (j = images_file.length - 1; j >= 0; j--) {
                                                                                                                                                                         var fileext = getFileExtension(images_file[j].name);
                                                                                                                                                                         if (fileext == 'jpg' || fileext == 'png' || fileext == 'jpeg') {
                                                                                                                                                                         image_url = images_file[j].nativeURL;
                                                                                                                                                                        // j = images_file.length;
                                                                                                                                                                         }
                                                                                                                                                                         }
                                                                                                                                                                         }, failFromSDCard);
                                                                                                                                             });
                                                                                                                var pathh = directoryname2 + "/Img";
                                                                                                                fileSystem.root.getDirectory(pathh, {
                                                                                                                                             create: false
                                                                                                                                             },
                                                                                                                                             function(directory) {
                                                                                                                                             var directoryReader = directory.createReader();
                                                                                                                                             directoryReader.readEntries(function(images_file) {
                                                                                                                                                                         for (j = images_file.length - 1; j >= 0; j--) {
                                                                                                                                                                         var fileext = getFileExtension(images_file[j].name);
                                                                                                                                                                         if (fileext == 'jpg' || fileext == 'png' || fileext == 'jpeg') {
                                                                                                                                                                         image_url = images_file[j].nativeURL;
                                                                                                                                                                        // j = images_file.length;
                                                                                                                                                                         }
                                                                                                                                                                         }
                                                                                                                                                                         }, failFromSDCard);
                                                                                                                                             });
                                                                                                                var pathhh = directoryname2 + "/data";
                                                                                                                
                                                                                                                fileSystem.root.getDirectory(pathhh, {
                                                                                                                                             create: false
                                                                                                                                             },
                                                                                                                                             function(directory) {
                                                                                                                                             var directoryReader = directory.createReader();
                                                                                                                                             directoryReader.readEntries(function(images_file) {
                                                                                                                                                                         for (j = images_file.length - 1; j >= 0; j--) {
                                                                                                                                                                         var fileext = getFileExtension(images_file[j].name);
                                                                                                                                                                         if (fileext == 'jpg' || fileext == 'png' || fileext == 'jpeg') {
                                                                                                                                                                         image_url = images_file[j].nativeURL;
                                                                                                                                                                         //j = images_file.length;
                                                                                                                                                                         }
                                                                                                                                                                         }
                                                                                                                                                                         }, failFromSDCard);
                                                                                                                                             });
                                                                                                                var pathhh = directoryname2 + "/Data";
                                                                                                                
                                                                                                                fileSystem.root.getDirectory(pathhh, {
                                                                                                                                             create: false
                                                                                                                                             },
                                                                                                                                             function(directory) {
                                                                                                                                             var directoryReader = directory.createReader();
                                                                                                                                             directoryReader.readEntries(function(images_file) {
                                                                                                                                                                         for (j = images_file.length - 1; j >= 0; j--) {
                                                                                                                                                                         var fileext = getFileExtension(images_file[j].name);
                                                                                                                                                                         if (fileext == 'jpg' || fileext == 'png' || fileext == 'jpeg') {
                                                                                                                                                                         image_url = images_file[j].nativeURL;
                                                                                                                                                                         //j = images_file.length;
                                                                                                                                                                         }
                                                                                                                                                                         }
                                                                                                                                                                         }, failFromSDCard);
                                                                                                                                             });
                                                                                                                var pathhw = directoryname2;
                                                                                                                fileSystem.root.getDirectory(pathhw, {
                                                                                                                                             create: false
                                                                                                                                             },
                                                                                                                                             function(directory) {
                                                                                                                                             var directoryReader = directory.createReader();
                                                                                                                                             directoryReader.readEntries(function(all_files) {
                                                                                                                                                                         for (j = all_files.length - 1; j >= 0; j--) {
                                                                                                                                                                         //var fileext=getFileExtension(all_files[j].name);
                                                                                                                                                                         //if (s.match(/cover*/)) {
                                                                                                                                                                         
                                                                                                                                                                         var str = all_files[j].name;
                                                                                                                                                                         if (str.search("cover") != -1) {
                                                                                                                                                                         var fileext = getFileExtension(all_files[j].name);
                                                                                                                                                                         if (fileext == 'jpg' || fileext == 'png' || fileext == 'jpeg') {
                                                                                                                                                                         image_url = all_files[j].nativeURL;
                                                                                                                                                                         //j = all_files.length;
                                                                                                                                                                         }
                                                                                                                                                                         }
                                                                                                                                                                         }
                                                                                                                                                                         
                                                                                                                                                                         }, failFromSDCard);
                                                                                                                                             });
                                                                                                                if (image_url != "") {
                                                                                                                
                                                                                                                } else {
                                                                                                                image_url = "images/epup1.png";
                                                                                                                }
                                                                                                                console.log("image_url=" + image_url);
                                                                                                                $('.appypie-loader').show();
                                                                                                                setTimeout(function() {
                                                                                                                           $.ajax({
                                                                                                                                  type: "GET",
                                                                                                                                  url: path_of_file,
                                                                                                                                  dataType: "xml",
                                                                                                                                  success: function(xml) {
                                                                                                                                  var title = $('title:first', xml).text();
                                                                                                                                  localStorage.setItem("title", title);
                                                                                                                                  
                                                                                                                                  //var title = $(xml).find("<dc:title").find("text").text();
                                                                                                                                  var Author = $('creator:first', xml).text();
                                                                                                                                  if (Author == null) {
                                                                                                                                  Author = $('contributor:first', xml).text();
                                                                                                                                  }
                                                                                                                                  localStorage.setItem("AuthorName", Author);
                                                                                                                                  if (search_value_global == 0) {
                                                                                                                                  
                                                                                                                                  result = result.replace(/ /g, '++++');
                                                                                 var bookImage=image_url_reader[ivalue];                                                 output = "<li id=" + result + " onclick='readBook(this.id);'><div class='e-readerimage'> "+ returnImageHtml(bookImage)+ "</div><span class='book_name'>" + title + "</span><span class='author_name'>" + Author + "</span></li>" + output;
                                                                                                                                  
                                                                                                                                  output_delete += "<li id='delete:" + result + "' onclick='select_book(this.id);'><img src='" + image_url + "'/><span class='book_name'>" + title + "</span><span class='author_name'>" + Author + "</span></li>";
                                                                                                                                  } else {
                                                                                                                                  
                                                                                                                                  if (title.search(new RegExp(search_value_data, "i")) != -1) {
                                                                                                                                  output = "<li id=" + result + " onclick='readBook(this.id);'><img src='" + image_url + "'/><span class='book_name'>" + title + "</span><span class='author_name'>" + Author + "</span></li>" + output;
                                                                                                                                  }
                                                                                                                                  
                                                                                                                                  }
                                                                                                                                  document.getElementById('dynamic_ereader').innerHTML = output;
                                                                                                                                  document.getElementById('dynamic_hidden_delete').innerHTML = output_delete;
                                                                                                                                  (function($) {
                                                                                                                                   
                                                                                                                                   $.fn.equalHeights = function() {
                                                                                                                                   var maxHeight = 0,
                                                                                                                                   $this = $(this);
                                                                                                                                   
                                                                                                                                   $this.each(function() {
                                                                                                                                              var height = $(this).innerHeight();
                                                                                                                                              
                                                                                                                                              if (height > maxHeight) {
                                                                                                                                              maxHeight = height;
                                                                                                                                              }
                                                                                                                                              });
                                                                                                                                   return $this.css('min-height', maxHeight);
                                                                                                                                   };
                                                                                                                                   
                                                                                                                                   })(jQuery);
                                                                                                                                  setTimeout(function() {
                                                                                                                                             $('.main2 li').equalHeights();
                                                                                                                                             }, 500);
                                                                                                                                  image_url = '';
                                                                                                                                  }
                                                                                                                                  });
                                                                                                                           $('.appypie-loader').hide();
                                                                                                                           }, 1000);
                                                                                                                
                                                                                                                
                                                                                                                
                                                                                                                
                                                                                                                }
        /**/
                                                                                                                function getFileExtension(name) {
                                                                                                                
                                                                                                                var found = name.lastIndexOf('.') + 1;
                                                                                                                return (found > 0 ? name.substr(found) : "");
                                                                                                                }
                                                                                                                var search_value_global = 0,
                                                                                                                search_value_data = '';
                                                                                                                
                                                                                                                function onblur_target() {
                                                                                                                var search_value = document.getElementById('target_box').value;
                                                                                                                
                                                                                                                search_value_global = 1;
                                                                                                                searchValue(search_value);
                                                                                                                
                                                                                                                }
                                                                                                                
                                                                                                                function searchValue(search_value) {
                                                                                                                search_value_data = search_value;
                                                                                                                output = '';
                                                                                                                window.requestFileSystem = window.requestFileSystem || window.webkitRequestFileSystem;
                                                                                                                window.requestFileSystem(LocalFileSystem.PERSISTENT, 0, gotFSFromSDCard, failFromSDCard);
                                                                                                                }
                                                                                                                
                                                                                                                function search_btn_clicked() {
                                                                                                                document.getElementById("my1").style.display = 'none';
                                                                                                                document.getElementById("my2").style.display = 'block';
                                                                                                                document.getElementById("delete_button").style.display = 'none';
                                                                                                                
                                                                                                                }
                                                                                                                
                                                                                                                function search_btn_clicked_hide() {
                                                                                                                document.getElementById('target_box').value = '';
                                                                                                                // onblur_target();
                                                                                                                document.getElementById("my1").style.display = 'block';
                                                                                                                document.getElementById("delete_button").style.display = 'block';
                                                                                                                document.getElementById("my2").style.display = 'none';
                                                                                                                }
                                                                                                                
                                                                                                                function delete_books() {
                                                                                                                	
                                                                                                                	
                                                                                                                	
                                                                                                                document.getElementById("dynamic_hidden_delete").style.display = 'block';
                                                                                                                document.getElementById("dynamic_ereader").style.display = 'none';
                                                                                                                document.getElementById("dynamic_header_delete").innerHTML = selecookselete_ereader;
                                                                                                                document.getElementById("delete_button").innerHTML = "<span onclick='Done_delete();'>"+done_ereader+"</span>";
                                                                                                                }
                                                                                                                
                                                                                                                function Done_delete() {
                                                                                                                
                                                                                                                window.requestFileSystem = window.requestFileSystem || window.webkitRequestFileSystem;
                                                                                                                window.requestFileSystem(LocalFileSystem.PERSISTENT, 0, gotFS_delete, failFromSDCard);
                                                                                                                //
                                                                                                                //directory.removeRecursively(function () { }, fail);
                                                                                                                }
                                                                                                                var test = "";
                                                                                                                
                                                                                                                function gotFS_delete(fileSystem) {
                                                                                                                all_id_delete = all_id_delete.split('++++').join(' ');
                                                                                                                if (all_id_delete != "") {
                                                                                                                var all_id_delete_array = all_id_delete.split('@$@$');
                                                                                                                //  var stringforbook = "databasedelete:"+ all_id_delete_array;
                                                                                                                // window.location = stringforbook;
                                                                                                                all_id_delete = "";
                                                                                                                for (i = all_id_delete_array.length - 1; i >= 0; i--) {
                                                                                                                fileSystem.root.getDirectory("epubtemp/" + all_id_delete_array[i] + "", {
                                                                                                                                             create: false
                                                                                                                                             }, function(directory) {
                                                                                                                                             directory.removeRecursively(function() {
                                                                                                                                                                         document.getElementById("dynamic_ereader").style.display = 'block';
                                                                                                                                                                         document.getElementById("dynamic_hidden_delete").style.display = 'none';
                                                                                                                                                                         document.getElementById("dynamic_header_delete").innerHTML = booklist_ereader;
                                                                                                                                                                         document.getElementById("delete_button").innerHTML = "<span onclick='delete_books();'>"+delete_ereader+"</span>";
                                                                                                                                                                         //onDeviceReady();
                                                                                                                                                                         loadLibrary();
                                                                                                                                                                         }, failFromSDCard);
                                                                                                                                             });
                                                                                                                
                                                                                                                
                                                                                                                window.plugins.bookmarkdelete.show({
                                                                                                                                                   bookname: all_id_delete_array[i]
                                                                                                                                                   }, function() {}, function() {});
                                                                                                                
                                                                                                                fileSystem.root.getFile("Downloads/" + all_id_delete_array[i] + "", {
                                                                                                                                        create: false
                                                                                                                                        }, function(f) {
                                                                                                                                        f.remove(function() {
                                                                                                                                                 
                                                                                                                                                 }, failFromSDCard);
                                                                                                                                        });
                                                                                                                
                                                                                                                
                                                                                                                }
                                                                                                                
                                                                                                                onDeviceReady();
                                                                                                                } else {
                                                                                                                	
                                                                                                                document.getElementById("dynamic_ereader").style.display = 'block';
                                                                                                                document.getElementById("dynamic_hidden_delete").style.display = 'none';
                                                                                                                document.getElementById("dynamic_header_delete").innerHTML = booklist_ereader;
                                                                                                                document.getElementById("delete_button").innerHTML = "<span onclick='delete_books();'>"+delete_ereader+"</span>";
                                                                                                                loadLibrary();
                                                                                                                }
                                                                                                                
                                                                                                                
                                                                                                                }
                                                                                                                var all_id_delete = "";
                                                                                                                
                                                                                                                function select_book(id_del) {
                                                                                                                
                                                                                                                var temp_ele = document.getElementById(id_del);
                                                                                                                var flag = hasClass(temp_ele, 'active');
                                                                                                                if (flag == false) {
                                                                                                                $(temp_ele).addClass("active");
                                                                                                                
                                                                                                                var temp_id = id_del.substring(7);
                                                                                                                if (all_id_delete == "") {
                                                                                                                all_id_delete += temp_id;
                                                                                                                } else {
                                                                                                                all_id_delete += "@$@$" + temp_id;
                                                                                                                }
                                                                                                                } else {
                                                                                                                $(temp_ele).removeClass("active");
                                                                                                                var temp_id = id_del.substring(7);
                                                                                                                var tempp = "@$@$" + temp_id;
                                                                                                                if (all_id_delete.indexOf(tempp) != -1) {
                                                                                                                all_id_delete = all_id_delete.split(tempp).join('');
                                                                                                                } else {
                                                                                                                tempp = temp_id + "@$@$";
                                                                                                                if (all_id_delete.indexOf(tempp) != -1) {
                                                                                                                all_id_delete = all_id_delete.split(tempp).join('');
                                                                                                                } else {
                                                                                                                all_id_delete = all_id_delete.split(temp_id).join('');
                                                                                                                }
                                                                                                                }
                                                                                                                }
                                                                                                                }
                                                                                                                
                                                                                                                
                                                                                                                function hasClass(element, cls) {
                                                                                                                return (' ' + element.className + ' ').indexOf(' ' + cls + ' ') > -1;
                                                                                                                }
                                                                                                                
                                                                                                                function readBook(fileName) {
                                                                                                                
                                                                                                                if (fileName.indexOf(".epub") != -1) {
                                                                                                                //toaster.openBook("/storage/sdcard0/epubtemp/" + fileName + "/");
                                                                                                                
                                                                                                                 var stringforbook = "/storage/sdcard0/Downloads/"+fileName;
                                                                                                                 window.plugins.ebookviewer.show({ url:stringforbook, info:fileName,authorname:localStorage.getItem("AuthorName"),title:fileName}, function() {}, function() {} );
                                                                                                                 
                                                                                                                } else if (fileName.indexOf(".pdf") != -1) {
                                                                                                                //fileName =fileName.split('++++').join(' ');
                                                                                                                //var stringforbook = "/storage/sdcard0/epubtemp/"+fileName;
                                                                                                               // var stringforbook = fileName.replace("file://", "");
                                                                                                               // window.plugins.pdfviewerplugin.show({
                                                                                                                //                                    url: stringforbook
                                                                                                                //                                    }, function() {}, function() {});
																												  var stringforbook = fileName.replace("file://", "");
                                                                                                                
                                                                                                                if(window.localStorage.getItem("applicationID") != "d1acdadd0aea" && window.localStorage.getItem("applicationID") != "9b07b3b0d64a" && window.localStorage.getItem("applicationID") != "77c8dd3c1f9e" && window.localStorage.getItem("applicationID") != "4837b29dc68d" && window.localStorage.getItem("applicationID") != "39e8cb34c754")
        {
		console.log("checkForNativeReader value>>"+checkForNativeReader);
         toaster.goToPdfReader(stringforbook,checkForNativeReader);
        }
                                                                                                                
                                                                                                                else
                                                                                                                {
                                                                                                                  window.plugins.pdfviewerplugin.show({
                                                                                                                         url: stringforbook
                                                                                                                         }, function() {}, function() {});
                                                                                                                }
																												
                                                                                                                }
                                                                                                                }
                                                                                                                
                                                                                                                function bookmark() {
                                                                                                                window.plugins.bookmarksave.show({
                                                                                                                                                 videoid: '',
                                                                                                                                                 videoInfo: ''
                                                                                                                                                 }, function() {}, function() {});
                                                                                                                }
                                                                                                                
                                                                                                                function readDriveFile(url) {
                                                                                                                var stringforbook = url;
                                                                                                                window.plugins.pdfviewerplugin.show({
                                                                                                                                                    url: stringforbook
                                                                                                                                                    }, function() {}, function() {});
                                                                                                                
                                                                                                                /*
                                                                                                                 $("#contentHolder7").css({"background-color": "#000"});
                                                                                                                 $("#contentHolder7").empty();
                                                                                                                 $("#contentHolder7").append("<div class='page-text'></div>");
                                                                                                                 $.mobile.changePage('#page8', { transition: 'slide',allowSamePageTransition: true});
                                                                                                                 if(window.localStorage.getItem("layout")=="bottom") {
                                                                                                                 window.location="bottom"+url;
                                                                                                                 }
                                                                                                                 else {
                                                                                                                 window.location="show"+url;
                                                                                                                 }
                                                                                                                 */
                                                                                                                }