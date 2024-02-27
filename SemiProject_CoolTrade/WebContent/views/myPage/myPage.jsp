<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    @font-face {
        font-family: 'GmarketSansMedium';
        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
        font-weight: normal;
        font-style: normal;
    }

    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: 'GmarketSansMedium';
        color: #444444;
    }

    div {
        /* border: 1px solid red; */
    }

    #mypage-wrap {
        width: 1000px;
        margin: auto;
        display: flex;
        margin-top: 200px;
    }

    /* 왼쪽 컨텐츠 CSS */
    #left-content {
        width: 20%;
    }

    #tit {
        font-weight: 800;
        letter-spacing: -1.5px;
        height: 60px;
    }

    li {
        list-style: none;
    }

    a {
        text-decoration: none;
        color: #444444;
    }

    a:hover {
        color: rgb(4, 180, 252);
    }

    .sub-title-list {
        padding: 3px 0px;
        font-size: 15px;
    }

    .sub-title-h3 {
        font-size: 18px;
        font-weight: 600;
    }

    .sub-title-li {
        padding-bottom: 20px;
    }

    /* 오른쪽 컨텐츠 css */
    #right-content {
        width: 80%;
    }

    #profile-div {
        background-color: rgb(4, 180, 252);
        display: flex;
        width: 100%;
        height: 100%;
        align-items: center;
        font-size: 18px;
        padding: 10px;
    }

    #right-content-top {
        width: 80%;
        height: 50px;
        border: 1px solid #e6e6e6;
        margin: auto;
        border-radius: 5px;
    }

    #profile-div img {
        height: 40px;
        width: 40px;
        margin-right: 10px;
    }

    .progress {
        width: 90%;
    }

    #right-content-bottom {
        width: 80%;
        margin: auto;
        margin-top: 10px;
        display: flex;
        justify-content: space-between;
    }

    #temperature {
        width: 40%;
        height: 150px;
        border: 1px solid #e6e6e6;
        border-radius: 5px;
    }

    #content-navi {
        width: 58%;
        height: 150px;
        border: 1px solid #e6e6e6;
        border-radius: 5px;
        display: flex;
        align-items: center;
        justify-content: space-around;
    }

    #temperature-div {
        display: flex;
        justify-content: space-between;
        padding: 10px;
    }

    #progress-bar-div {
        display: flex;
        justify-content: space-around;
    }

    #temperature-content {
        padding: 10px;
    }

    #temperature-content li {
        color: rgb(4, 180, 252);
        font-size: 13px;
    }

    #temperature-content ul {
        padding-top: 20px;
    }

    #content-navi ul {
        display: flex;
        justify-content: space-around;
        width: 100%;
        margin: 0;
    }

    #content-navi li {
        padding: 10px;
        text-align: center;
        font-size: 18px;
        font-weight: 500;
    }
</style>
</head>
<body>

	<%@ include file = "../common/header.jsp" %>

    <div id="mypage-wrap">
        <div id="left-content">
            <div id="mypage-tit">
                <h2 id="tit">마이페이지</h2>
            </div>
            <div>
               <div>
                    <ul>
                        <li class="sub-title-li">
                            <h3 class="sub-title-h3">내정보</h3>
                            <ul>
                                <li class="sub-title-list">
                                    <a href="">회원정보 수정</a>
                                </li>
                                <li class="sub-title-list">
                                    <a href="">이용 후기</a>
                                </li>
                            </ul>
                        </li>
                        
                        <li class="sub-title-li">
                            <h3 class="sub-title-h3">마이 쇼핑</h3>
                            <ul>
                                <li class="sub-title-list">
                                    <a href="">찜한 상품</a>
                                </li>
                                <li class="sub-title-list">
                                    <a href="">구매 내역</a>
                                </li>
                                <li class="sub-title-list">
                                    <a href="">판매 내역</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
               </div>
            </div>
        </div>
        <div id="right-content">
            <div id="right-content-wrap">
                <div id="right-content-top">
                    <div id="profile-div">
                        <div>
                            <img src="resources/images/free-icon-user-847969.png" alt="회원 프로필 사진 이미지">
                        </div>
                        <div>
                            <b>김뫄뫄님</b> 환영합니다!
                        </div>
                    </div>
                </div>

                <div id="right-content-bottom">
                    <div id="temperature">
                        <div id="temperature-div">
                            <div>매너온도</div>
                            <div>27.0℃</div>
                        </div>
                        <div id="progress-bar-div">
                            <div class="progress">
                                <div class="progress-bar" style="width:70%"></div>
                            </div>
                        </div>
                        <div id="temperature-content">
                            <ul>
                                <li>거래 우수 회원!</li>
                                <li>10℃부터 쿨거래 이용이 가능해요</li>
                            </ul>
                        </div>
                    </div>
                    <div id="content-navi">
                        <ul>
                            <li>
                                <div>쿨거래</div>
                                <div>
                                    <a href="#">0</a>
                                </div>
                            </li>
                            <li>
                                <div>이용후기</div>
                                <div>
                                    <a href="#">127</a>
                                </div>
                            </li>
                            <li>
                                <div>찜한상품</div>
                                <div>
                                    <a href="#">3</a>
                                </div>
                            </li>
                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </div>
    
    <%@ include file = "../common/footer.jsp" %>

</body>
</html>