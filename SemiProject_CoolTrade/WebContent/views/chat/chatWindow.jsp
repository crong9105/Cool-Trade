<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
	String pno = (String)request.getAttribute("pno");
%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        * {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
        }

        a {
            text-decoration: none;
        }
        .outer{
        	margin-bottom:100px;
        }

        .wrap {
            padding: 50px 0;
            background-color: #A8C0D6;
            width:700px;
            height:1000px;
            max-height: 600px; /* 채팅창의 최대 높이를 지정합니다. */
            overflow-y: auto; /* 내용이 넘칠 때 스크롤 표시합니다. */
            margin-bottom:50px;
        }
        
        .wrap .chat {
            display: flex;
            align-items: flex-start;
            padding: 20px;
        }
	
		
		
		 .timestamp{
			position: relative; 
	        max-width: calc(100% - 70px);
	        padding: 10px;
	        margin-top: 7px;
	        font-size: 13px;
	        border-radius: 10px;
		 }

		
        .wrap .chat .icon {
            position: relative;
            overflow: hidden;
            width: 50px;
            height: 50px;
            min-width: 50px; 
       		 min-height: 50px; 
            border-radius: 30%;
            background-color: #eee;
        }

        .wrap .chat .icon i {
            position: absolute;
            top: 10px;
            left: 50%;
            font-size: 0.8rem;
            line-height: 30px;
            color: #aaa;
            transform: translateX(-50%);
        }

        .wrap .chat .textbox {
            position: relative;
            display: inline-block;
            max-width: calc(100% - 70px);
            padding: 10px;
            margin-top: 7px;
            font-size: 13px;
            border-radius: 10px;
        }

        .wrap .chat .textbox::before {
            position: absolute;
            display: block;
            top: 0;
            font-size: 1.5rem;
        }

        .wrap .ch1 .textbox {
            margin-left: 20px;
            background-color: #ddd;
        }

        .wrap .ch2 {
            flex-direction: row-reverse;
        }

        .wrap .ch2 .textbox {
            margin-right: 20px;
            background-color: #04b4fc;
            color: black;
        }
    </style>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<br><br><br><br>
	<br><br><br><br>
	<br><br>
	<h2>?번채팅방(구매자:? 판매자:?)</h2>
	<div class="outer">
	<div class="wrap">
		
        <div class="chat ch1">
            <div class="icon"><i class="fa-solid fa-user"><%= pno %></i></div>
            <div class="textbox">안녕하세요. 반갑습니다.</div>
            
        </div>
        <div class="chat ch2">
            <div class="icon"><i class="fa-solid fa-user"><%= loginUser.getUserId() %></i></div>
            <div class="textbox">안녕하세요. 친절한효자손입니다. 그동안 잘 지내셨어요?</div>
            <div class="timestamp"></div>
        </div>
        <div class="chat ch1">
            <div class="icon"><i class="fa-solid fa-user"><%= pno %></i></div>
            <div class="textbox">아유~ 너무요너무요! 요즘 어떻게 지내세요?</div>
        </div>
        <div class="chat ch2">
            <div class="icon"><i class="fa-solid fa-user"><%= loginUser.getUserId() %></i></div>
            <div class="textbox">뭐~ 늘 똑같은 하루 하루를 보내는 중이에요. 코로나가 다시 극성이어서 모이지도 못하구 있군요 ㅠㅠ 얼른 좀 잠잠해졌으면 좋겠습니다요!</div>
        </div>
        <div class="chat ch1">
            <div class="icon"><i class="fa-solid fa-user"><%= pno %></i></div>
            <div class="textbox">아유~ 너무요너무요! 요즘 어떻게 지내세요?</div>
        </div>
        
    </div>
    <div align="right">
    <form id="messageForm">
    <input type="text" id="messageInput" style="width:600px;">
    <button type="submit">전송</button>
	</form>
	</div>
	</div>
    
    <script>
	$(document).ready(function() {
    $('#messageForm').submit(function(event) {
        event.preventDefault(); // 기본 이벤트 동작 방지
        
        var messageText = $('#messageInput').val().trim(); // 입력된 메시지
        if (messageText === '') return; // 입력된 메시지가 없으면 종료
        
        var chatDiv = $('<div>').addClass('chat ch2');
        var iconDiv = $('<div>').addClass('icon');
        var userIcon = $('<i>').addClass('fa-solid fa-user').text('<%= loginUser.getUserId() %>');
        iconDiv.append(userIcon);
        var textboxDiv = $('<div>').addClass('textbox').text(messageText);
        var timestampDiv = $('<div>').addClass('timestamp').text(getCurrentTime());
        chatDiv.append(iconDiv, textboxDiv, timestampDiv);
        
        $('.wrap').append(chatDiv); // 채팅창에 메시지 추가
        console.log(chatDiv);
        $('#messageInput').val(''); // 입력 필드 초기화
        scrollToBottom(); // 스크롤을 항상 아래로 이동
    });
    
    function getCurrentTime() {
        var now = new Date();
        var hours = now.getHours().toString().padStart(2, '0');
        var minutes = now.getMinutes().toString().padStart(2, '0');
        return hours + ':' + minutes;
    }

    function scrollToBottom() {
        var wrap = $('.wrap')[0];
        wrap.scrollTop = wrap.scrollHeight;
    }
	});
	</script>
    
    <%@ include file="../common/footer.jsp"%>
</body>
</html>