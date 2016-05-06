var myDataRef = "";
var chatFireBaseUrl = "";
var IsMessageSent = '';
var lastname = "", lasttext = "", lastimageurl = "", lastTime = "";
var scrollHeightValue = 0;
var messageCounter = 0;
var skypeUser = '';
$chatXmlData = '';

var namefiled_chat = ' Name field cannot be left blank';
var alert_chat = 'Alert';
var ok_chat = 'OK';
var send_chat = "send";
var uploadpic_chat = "Upload Pic";
var screen_chat = "Screen Name";
var next_chat = "Next";
var update_chat = "Update";
var selectfromwhereyouwanttoupload_chat = 'Select from where you want to upload';
var message_chat ='Message';
var unableto_chat = 'Unable to select image';



function getChat(index) {
	//arabic();
	var chatHtml = "";
	$chatXmlData = $(masterData).find("chat[indexval=" + index + "]");
	var itemTypeArray = [ "skype", "wechat", "whatsapp", "snapchat",
			"linechat", "location", "zopimChat" ];
	var valueChatPage = 0;
	var funcName = '';
	var imageUrl = '';
	var pageName = '';
	console.log("hi this is test ");

	if ($chatXmlData.find('chatRoomUrl').text()) {
		imageUrl = $chatXmlData.find('chatRoomImage').text();
		pageName = $chatXmlData.find('chatRooms').text();
		var chatRooms = pageName.split(',');
		var chatIcons = imageUrl.split(',');
		for ( var z = 0; z < chatRooms.length; z++) {

			chatHtml += '<span class="audio_list" onclick="getChatRooms(\''
					+ index + '\',\'' + z + '\')">'
					+ returnImageHtml(chatIcons[z]) + '<span>' + chatRooms[z]
					+ '</span></span>';
			funcName = 'getChatRooms(\'' + index + '\',\'' + z + '\')';
			valueChatPage++;
		}

	}

	for ( var x = 0; x < itemTypeArray.length; x++) {
		console.log("itemTypeArray --->" + x);
		if ($chatXmlData.find('' + itemTypeArray[x] + 'Name').text()) {

			imageUrl = $chatXmlData.find(itemTypeArray[x] + 'Icon').text();
			pageName = $chatXmlData.find(itemTypeArray[x] + 'Name').text();

			if (itemTypeArray[x].toUpperCase() == "SKYPE") {
				skypeUser = pageName;
			}

			chatHtml += '<span class="audio_list" onclick="openChatOptions(\''
					+ itemTypeArray[x] + '\')">' + returnImageHtml(imageUrl)
					+ ' <span>' + pageName + '</span></span>';
			funcName = 'openChatOptions(\'' + itemTypeArray[x] + '\')';
			valueChatPage++;

		}
	}

	if (parseInt(valueChatPage) == 1) {
		window.setTimeout(funcName, 10);
		sessionStorage.setItem("singlePage", "true");
	} else {
		console.log("videoPageHtml--->" + chatHtml);
		appendHtml("<div class='page-text'>" + chatHtml + "</div>", '', 1);

	}
}

function openChatOptions(type) {
	if (type.toUpperCase() == "SKYPE") {
		toaster.openChat(type, skypeUser);
	} else if (type.toUpperCase() == "ZOPIMCHAT") {
		console.log("in if.. zopim");
		if (sessionStorage.getItem("singlePage") == "true")
			openWebPage("https://v2.zopim.com/widget/livechat.html?key="
					+ $chatXmlData.find(type).text()
					+ "&mid=YLfCST0225Y9pp&lang=pt&hostname=&api_calls=%5B%5D",
					1);
		else
			openWebPage("https://v2.zopim.com/widget/livechat.html?key="
					+ $chatXmlData.find(type).text()
					+ "&mid=YLfCST0225Y9pp&lang=pt&hostname=&api_calls=%5B%5D",
					2);
	} else {
		toaster.openChat(type, "");
	}
}

function getChatRooms(index, pageIndex) {
	myDataRef = "";
	chatFireBaseUrl = '';
	sessionStorage.setItem('chatRoom', 'true');
	sessionStorage.setItem("indexValue", index);
	sessionStorage.setItem('chatPageIndex', pageIndex);
	if (localStorage.getItem("userName")) {
		openRoomsList(false);
	} else {
		editProfilePicture(true);
	}
}

function openRoomsList(value) {
	// alert(value);
	
	chatFireBaseUrl = "";

	if (($('#userName').val() == null || $('#userName').val() == '')
			&& localStorage.getItem("userName") == null) {
		/* need to be converted to SA */
		navigator.notification.alert(namefiled_chat, alertDismissed,
				alert_chat, ok_chat);
	} else {
		if (value == true) {
			localStorage.setItem("userName", $('#userName').val());
			$('#userName').val('');
			// gaurav

			var mediaFiles = localStorage.local_imageURI;
			if (typeof mediaFiles != 'undefined') {
				var wsUrll = "http://"
						+ window.localStorage.getItem("reseller")
						+ "/services/utility-soap#uploadpic";
				var soapRequestt = '<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><uploadpic xmlns=\"http://'
						+ window.localStorage.getItem("reseller")
						+ '/services/social-soap#uploadpic\"><image>'
						+ mediaFiles
						+ '</image><uuid>'
						+ localStorage.getItem("deviceUUID")
						+ '</uuid></uploadpic></soap:Body></soap:Envelope>';
				console.log("SoapRequest : " + soapRequestt);
				$.ajax({
							type : "POST",
							url : wsUrll,
							contentType : "text/xml",
							dataType : "text",
							data : soapRequestt,
							success : function(data, status, req) {
								var strJSON = $(req.responseText)
										.find("return").text();

								console.log("success textupload gaurav2 : "
										+ req.responseText);
								console.log("success textupload gaurav : "
										+ strJSON);
								var image = 'http://apps.appypie.com/media/chatroom/'
										+ localStorage.getItem("deviceUUID")
										+ '.jpg';
								localStorage.setItem("Image_url", image);
								document.getElementById("updatepic").src = '';

								document.getElementById("updatepic").src = image;
							},
							error : function(response, textStatus, errorThrown) {
								localStorage.setItem("Image_url", "NoImage");
								console.log("Error : "
										+ JSON.stringify(response));
								console.log("Error : " + textStatus);
								console.log("Error : "
										+ errorThrown.responseText);
							}
						});
			} else {
				localStorage.setItem("Image_url", "NoImage");
			}
		}
		$data = $(masterData).find(
				"chat[indexval=" + sessionStorage.getItem("indexValue") + "]");

		var chatRooms = $data.find("chatRooms").text();
		var chatRoomUrl = $data.find("chatRoomUrl").text();
		var chatRoomImage = $data.find("chatRoomImage").text();
		sessionStorage.setItem('baseurl', chatRoomUrl);
		var rooms = '', image = '';
		if (chatRooms.indexOf(",") != -1) {
			rooms = chatRooms.split(",");
		} else {
			rooms = [ chatRooms ];
		}

		if (chatRoomImage.indexOf(",") != -1) {
			image = chatRoomImage.split(",");
		} else {
			image = [ chatRoomImage ];
		}
		for ( var i = 0; i < rooms.length; i++) {
			if (parseFloat(sessionStorage.getItem('chatPageIndex')) == i) {
				openChatRoom(rooms[i]);

			}
		}
	}
}

function loadFireBase() {
	// alert("myDataRef : " + myDataRef);
	// alert("chatFireBaseUrl : " + chatFireBaseUrl);

	if (localStorage.getItem("messageCounter") == 0
			|| localStorage.getItem("messageCounter") == null) {
		setTimeout(function() {
			$('.appypie-loader').hide();
		}, 10);
	}

	if (myDataRef != chatFireBaseUrl) {
		myDataRef = new Firebase(chatFireBaseUrl);
	}
	sessionStorage.setItem("FireBaseRef", myDataRef);

	try {
		myDataRef.on('child_added', function(snapshot) {
			var message = snapshot.val();
			if (message.name == lastname && message.text == lasttext
					&& message.image == lastimageurl
					&& message.chatId == lastTime) {
			} else {
				// alert("singhal");
				lastname = message.name;
				lasttext = message.text;
				lastimageurl = message.image;
				lastTime = message.chatId;
				setTimeout(function() {
					// message.image = $("#updatepic").attr()
					if (localStorage.getItem("deviceUUID") == message.id) {
						displayChatMessage(message.name, message.text, $(
								"#updatepic").attr("src"), message.id);
					} else {
						displayChatMessage(message.name, message.text,
								message.image, message.id);
					}

				}, 10);
			}
			// displayChatMessage(message.name,
			// message.text,message.image,message.id,message.chatID);
			setTimeout(function() {
				$('.appypie-loader').hide();
			}, 1000);
		});
	} catch (err) {
		setTimeout(function() {
			$('.appypie-loader').hide();
		}, 10);
	}
}

function openChatRoom(value) {
	// $('.appypie-loader').show();

	// alert("gou: " + value);
	var chatList = "";
	var img_url;
	IsMessageSent = 'FromRoom';
	if (value != "") {
		localStorage.setItem("userName", localStorage.getItem("userName"));
		chatFireBaseUrl = sessionStorage.getItem('baseurl') + "/"
				+ value.trim();

	} else {
		localStorage.setItem("userName", $('#userName').val());
		$('#userName').val('');

		var mediaFiles = localStorage.local_imageURI;
		var wsUrll = "http://" + window.localStorage.getItem("reseller")
				+ "/services/utility-soap#uploadpic";
		var soapRequestt = '<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><uploadpic xmlns=\"http://'
				+ window.localStorage.getItem("reseller")
				+ '/services/social-soap#uploadpic\"><image>'
				+ mediaFiles
				+ '</image><uuid></uuid></uploadpic></soap:Body></soap:Envelope>';
		console.log("SoapRequest : " + soapRequestt);
		$.ajax({
			type : "POST",
			url : wsUrll,
			contentType : "text/xml",
			dataType : "text",
			data : soapRequestt,
			success : function(data, status, req) {
				var strJSON = $(req.responseText).find("return").text();
				var image = 'http://apps.appypie.com/media/chatroom/'
						+ localStorage.getItem("deviceUUID") + '.jpg';
				localStorage.setItem("Image_url", image);
				document.getElementById("updatepic").src = localStorage
						.getItem("Image_url");

			},
			error : function(response, textStatus, errorThrown) {
				console.log("Error : " + JSON.stringify(response));
				console.log("Error : " + textStatus);
				console.log("Error : " + errorThrown.responseText);
			}
		});

	}

	if (localStorage.getItem("Image_url") == "NoImage"
			|| localStorage.getItem("Image_url") == null) {
		// alert("gggg");
		img_url = "images/chat/default-user.png";
	} else {
		// alert("kkkkk");
		img_url = localStorage.getItem("Image_url");
	}

	console.log(img_url);

	chatList = '<div id="appypie-chat" class="appypie-chat" style="overflow-y:auto;"></div>';

	$('#contentHolder15').css('background', '#A9D0F5');

	if ((window.localStorage.getItem("layout") == "bottom")
			|| (window.localStorage.getItem("layout") == "slidemenu")) {
		if (sessionStorage.getItem("singlePage") == 'true') {
			$(".app_navigation_bottom").hide();
			$(".app_navigation_bottom_carousel").hide();
			documentHeight();
		} else {
			$(".app_navigation_bottom").hide();
			$(".app_navigation_bottom_carousel").hide();
			documentHeight();
			$("#contentHolder15 .appypie-chat").css('padding-bottom', '80px');
		}
	}
	// loadFireBase();
	
	
	setTimeout(function() {
		loadFireBase();
	}, 1000);
	if (sessionStorage.getItem("singlePage") == 'true') {
		if (window.localStorage.getItem("layout") == "bottom") {
			appendHtml(
					chatList
							+ '<div  class="chat-footer ui-footer ui-bar-a ui-footer-fixed slideup" ><span class="chat-user"><img data-dom-cache="false" id="updatepic" onclick="editProfilePicture(false)" src="'
							+ img_url
							+ '"></span><input id="messageInput" type="text" data-role="none" autofocus><a onclick="sendMessage()" data-role="none">'+send_chat+'</a></div>',
					15, 2);
		} else {

			appendHtml(
					chatList
							+ '<div  class="chat-footer ui-footer ui-bar-a ui-footer-fixed slideup" ><span class="chat-user"><img data-dom-cache="false" id="updatepic" onclick="editProfilePicture(false)" src="'
							+ img_url
							+ '"></span><input id="messageInput" type="text" data-role="none" autofocus><a onclick="sendMessage()" data-role="none">'+send_chat+'</a></div>',
					15, 1);
		}
	} else {
		appendHtml(
				chatList
						+ '<div  class="chat-footer ui-footer ui-bar-a ui-footer-fixed slideup" ><span class="chat-user"><img data-dom-cache="false" id="updatepic" onclick="editProfilePicture(false)" src="'
						+ img_url
						+ '"></span><input id="messageInput" type="text" data-role="none" autofocus><a onclick="sendMessage()" data-role="none">'+send_chat+'</a></div>',
				15, 2);

	}
	$("#contentHolder15 .appypie-chat").css('padding-bottom', '61px');
	// loadFireBase();
}

function documentHeight() {
	if (window.localStorage.getItem("layout") == "bottom") {
		var height = $("#contentHolder15").height() - 15;
	} else {
		var height = $("#contentHolder15").height() - 65;
	}
	$(".appypie-chat").css("height", height);
	$(".app_navigation_bottom").hide();
	$(".app_navigation_bottom_carousel").hide();
}

function sendMessage() {
	localStorage.setItem("messageCounter", messageCounter++);
	var text = $('#messageInput').val().trim();
	if (text == "" || text.length <= 0) {
		// do nothing..
	} else {
		var image = '';
		var name = localStorage.getItem("userName");

		if (localStorage.getItem("Image_url") != "NoImage") {
			image = localStorage.getItem("Image_url");
		}
		$('#messageInput').val('');
		IsMessageSent = 'true';
		var time = new Date().getTime();
		while (time == new Date().getTime())
			;
		var chatId = new Date().getTime();

		myDataRef.push({
			name : name,
			text : text,
			image : image,
			id : localStorage.getItem("deviceUUID"),
			chatID : chatId
		});
	}
	document.getElementById("messageInput").focus();
}

function displayChatMessage(name, text, image, id, chatID) {

	var lasttop = $('#appypie-chat').height();
	if (lasttop <= scrollHeightValue) {
		lasttop = scrollHeightValue;
	} else {
		scrollHeightValue = lasttop;

	}
	if (text) {
		if (IsMessageSent == 'true') {
			if (!image) {
				image = 'images/chat/default-user.png';
			}

			if (localStorage.getItem("deviceUUID") == id) {
				$('#appypie-chat').append(
						'<div class="user"><div class="chat-pic"><img src="'
								+ image + '"></div><div class="chat-MGS">'
								+ text + '</div> <div class="username">' + name
								+ '</div></div>');
			} else {
				$('#appypie-chat').append(
						'<div class="other"><div class="chat-pic"><img src="'
								+ image + '"></div><div class="chat-MGS">'
								+ text + '</div> <div class="username">' + name
								+ '</div></div>');
			}
			// $('#appypie-chat').find(".user img").attr("src",
			// $("#updatepic").attr("src"));
			$('#appypie-chat')[0].scrollTop = $('#appypie-chat')[0].scrollHeight;
			$('#contentHolder15').animate({
				scrollTop : lasttop
			}, 10);

			IsMessageSent = 'false';
		}

		if (IsMessageSent == 'FromRoom') {
			if (!image) {
				image = 'images/chat/default-user.png';
			}
			if (localStorage.getItem("deviceUUID") == id) {
				$('#appypie-chat').append(
						'<div class="user"><div class="chat-pic"><img src="'
								+ image + '"></div><div class="chat-MGS">'
								+ text + '</div> <div class="username">' + name
								+ '</div></div>');
			} else {
				$('#appypie-chat').append(
						'<div class="other"><div class="chat-pic"><img src="'
								+ image + '"></div><div class="chat-MGS">'
								+ text + '</div> <div class="username">' + name
								+ '</div></div>');
			}
			$('#appypie-chat')[0].scrollTop = $('#appypie-chat')[0].scrollHeight;
			$('#contentHolder15').animate({
				scrollTop : lasttop
			}, 10);
		}
		if (IsMessageSent == 'false'
				&& localStorage.getItem("deviceUUID") != id) {
			if (!image) {
				image = 'images/chat/default-user.png';
			}
			if (localStorage.getItem("deviceUUID") == id) {
				$('#appypie-chat').append(
						'<div class="user"><div class="chat-pic"><img src="'
								+ image + '"></div><div class="chat-MGS">'
								+ text + '</div> <div class="username">' + name
								+ '</div></div>');
			} else {
				$('#appypie-chat').append(
						'<div class="other"><div class="chat-pic"><img src="'
								+ image + '"></div><div class="chat-MGS">'
								+ text + '</div> <div class="username">' + name
								+ '</div></div>');
			}
			$('#appypie-chat')[0].scrollTop = $('#appypie-chat')[0].scrollHeight;
			$('#contentHolder15').animate({
				scrollTop : lasttop
			}, 10);

		}

	}
}
function editProfilePicture(value) {
	


	if (window.localStorage.getItem("layout") == "bottom") {
		$(".app_navigation_bottom").show();
		$(".app_navigation_bottom_carousel").hide();
	}
	var profile = "";
	if (value == true) {
		$("#reload").show();
		$("#bookmark").hide();
		profile = '<div class="appypie-chat-update" id="appypie-chat-update"><div class="chat-pic" onclick="selectPhoto();">'+uploadpic_chat+'</div><div class="chat-pic-1" onclick="selectPhoto();" style="display:none"><img id="set_chat_pic"></div><input id="userName" data-role="none" type="text" placeholder="'+screen_chat+'"><a onClick="openRoomsList(true);" '
				+ primaryColor + '>'+next_chat+'</a></div>';
	} else {
		if (window.localStorage.getItem("layout") == "slidemenu") {
			$("#reload").hide();
			$("#mainback").show();
		} else {
			$("#logo").hide();
			$("#mainback").show();
		}
		profile = '<div class="appypie-chat-update" id="appypie-chat-update"><div class="chat-pic" onclick="selectPhoto();">'+uploadpic_chat+'</div><div class="chat-pic-1" onclick="selectPhoto();" style="display:none"><img id="set_chat_pic"></div><input id="userName" data-role="none" type="text"><a onClick="UpdateProfilePic();">'+update_chat+'</a></div>';
	}
	if (value == true) {
		$('#contentHolder14').css('background', '#A9D0F5');
		if (sessionStorage.getItem("singlePage") == 'true') {
			appendHtml(profile, 14, 1);
		} else {
			appendHtml(profile, 14, 2);
		}

	} else {
		$('#contentHolder16').css('background', '#A9D0F5');

		if (sessionStorage.getItem("singlePage") == 'true') {
			$('#contentHolder14').empty()
			appendHtml(profile, 16, 2);
		} else {
			$('#contentHolder14').empty()
			appendHtml(profile, 16, 3);
		}

		if (localStorage.getItem("Image_url") == "NoImage") {
			$(".appypie-chat-update .chat-pic").show();
		} else {
			document.getElementById("set_chat_pic").src = localStorage
					.getItem("Image_url");
			$(".appypie-chat-update .chat-pic").hide();
			$(".appypie-chat-update .chat-pic-1").show();
		}

		$('#userName').val(localStorage.getItem("userName"));
	}
}
function selectPhoto() {
	/* need to be converted to SA */
	
	
	navigator.notification.confirm(selectfromwhereyouwanttoupload_chat +'!', // message
	onConfirm2, // callback to invoke with index of button pressed
	message_chat, // title
	'Camera,Gallery');
}

function onConfirm2(buttonIndex) {
	if (buttonIndex == 2) {
		var options = {
			quality : 50,
			sourceType : Camera.PictureSourceType.SAVEDPHOTOALBUM,
			destinationType : navigator.camera.DestinationType.DATA_URL,
		}
		navigator.camera.getPicture(win_image_lib, fail, options);
	} else {
		Image_capture();
	}
}
function fail() {
	/* need to be converted to SA */

	navigator.notification.alert(unableto_chat, alertDismissed,
			alert_chat, ok_chat);
}
function win_image_lib(imageURI) {
	localStorage.local_imageURI = imageURI;
	show_image_on_box();
}

function Image_capture() {
	navigator.camera.getPicture(captureSuccess2, captureError, {
		quality : 10,
		destinationType : Camera.DestinationType.DATA_URL,
		correctOrientation : true,
		allowEdit : true
	});
}

function captureSuccess2(mediaFiles) {
	// mediaFiles = goToNativeForRotation(mediaFiles[0].fullPath);
	localStorage.local_imageURI = mediaFiles;
	show_image_on_box();
}

function show_image_on_box() {
	var mediaFiles = localStorage.local_imageURI;
	document.getElementById("set_chat_pic").src = "data:image/jpeg;base64,"
			+ mediaFiles;
	$(".appypie-chat-update .chat-pic").hide();
	$(".appypie-chat-update .chat-pic-1").show();
}

function UpdateProfilePic() {
	if ($('#userName').val() == null || $('#userName').val() == '') {
		/* need to be converted to SA */
		navigator.notification.alert(namefiled_chat,
				alertDismissed, alert_chat, ok_chat);
	} else {
		$('.appypie-loader').show();
		var mediaFiles = localStorage.local_imageURI;
		var randomnumber = Math.floor((Math.random() * 100) + 1);
		if (typeof mediaFiles != 'undefined') {
			localStorage.setItem("userName", $('#userName').val());
			$('#userName').val('');
			var wsUrll = "http://" + window.localStorage.getItem("reseller")
					+ "/services/utility-soap#uploadpic";
			var soapRequestt = '<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><uploadpic xmlns=\"http://'
					+ window.localStorage.getItem("reseller")
					+ '/services/social-soap#uploadpic\"><image>'
					+ mediaFiles
					+ '</image><uuid>'
					+ localStorage.getItem("deviceUUID")
					+ '</uuid></uploadpic></soap:Body></soap:Envelope>';
			console.log("SoapRequest : " + soapRequestt);
			$
					.ajax({
						type : "POST",
						url : wsUrll,
						contentType : "text/xml",
						dataType : "text",
						data : soapRequestt,
						success : function(data, status, req) {
							var strJSON = $(req.responseText).find("return")
									.text();

							console.log("success textupload gaurav2 : "
									+ req.responseText);
							console.log("success textupload gaurav : "
									+ strJSON);
							var image = 'http://'
									+ window.localStorage.getItem("reseller")
									+ '/media/chatroom/' + strJSON + '?'
									+ randomnumber;
							console.log("ImageUpdated " + image);
							localStorage.setItem("Image_url", image);
							document.getElementById("updatepic").src = '';

							document.getElementById("updatepic").src = image;
							setTimeout(function() {
								$('.appypie-loader').hide();
							}, 1000);
							// myDataRef.remove();
							// loadFireBase();
							// openRoomsList(false);
							// history.go(-1);

							$data = $(masterData).find(
									"chat[indexval="
											+ sessionStorage
													.getItem("indexValue")
											+ "]");

							var chatRooms = $data.find("chatRooms").text();
							var chatRoomUrl = $data.find("chatRoomUrl").text();
							var chatRoomImage = $data.find("chatRoomImage")
									.text();
							sessionStorage.setItem('baseurl', chatRoomUrl);
							var rooms = '', image = '';
							if (chatRooms.indexOf(",") != -1) {
								rooms = chatRooms.split(",");
							} else {
								rooms = [ chatRooms ];
							}

							if (chatRoomImage.indexOf(",") != -1) {
								image = chatRoomImage.split(",");
							} else {
								image = [ chatRoomImage ];
							}
							for ( var i = 0; i < rooms.length; i++) {
								if (parseFloat(sessionStorage
										.getItem('chatPageIndex')) == i) {
									openChatRoom(rooms[i]);

								}
							}

						},
						error : function(response, textStatus, errorThrown) {
							setTimeout(function() {
								$('.appypie-loader').hide();
							}, 1000);
							history.go(-1);
							console.log("Error : " + JSON.stringify(response));
							console.log("Error : " + textStatus);
							console.log("Error : " + errorThrown.responseText);

						}
					});
		} else {
			setTimeout(function() {
				$('.appypie-loader').hide();
			}, 1000);
			onBackKeyDown();
			localStorage.setItem("Image_url", "NoImage");
		}

		if (window.localStorage.getItem("layout") == "bottom") {
			$(".app_navigation_bottom").hide();
			$(".app_navigation_bottom_carousel").hide();
			sessionStorage.setItem('chatRoom', 'true');
		}
	}

}
