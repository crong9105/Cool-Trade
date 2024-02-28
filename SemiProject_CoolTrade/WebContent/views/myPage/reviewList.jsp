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

    .flex-class {
        display: flex;
    }

    div {
        /* border: 1px solid red; */
    }

    #mypage-wrap {
        width: 1000px;
        margin: auto;
        display: flex;
        margin-top: 200px;
        padding-top: 20px;
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

    #right-content-wrap>div {
        padding: 10px 0px;
    }

    #right-content-title {
        font-size: 18px;
        font-weight: 700;
        width: 100%;
    }

    #trade-review-count {
        font-size: 16px;
        /* border-bottom: 1px solid #e6e6e6; */
        width: 100%;
    }

    #trade-review-count>span {
        color: rgb(4, 180, 252);
    }

    #trade-review-total {
        display: flex;
        justify-content: space-evenly;
        border: 1px solid #e6e6e6;
        border-radius: 5px;
    }

    #trade-review-total>div {
        display: flex;
        align-items: center;
        flex-direction: column;
    }

    #review-select-section-top>div, #review-select-section-bottom>div {
        align-items: center;
        background-color: #ebebeb;
        margin: 10px 0px;
        padding: 0px 15px;
        height: 35px;
        border-radius: 5px;
        display: flex;
        justify-content: space-between;
    }

    #review-select-section-bottom {
        display: none;
    }

    #arrow-img {
        display: flex;
        justify-content: space-around;
        cursor: pointer;
    }

</style>
</head>
<body>

	<%@ include file = "../common/header.jsp" %>

    <div id="mypage-wrap">
        <div id="left-content">
            <div id="mypage-tit">
                <h2 id="tit">
                	<a href="<%= contextPath %>/mypage.me">마이페이지</a>
                </h2>
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
                                    <a href="<%= contextPath %>/review.me">거래 후기</a>
                                </li>
                            </ul>
                        </li>
                        
                        <li class="sub-title-li">
                            <h3 class="sub-title-h3">마이 쇼핑</h3>
                            <ul>
                                <li class="sub-title-list">
                                    <a href="<%= contextPath %>/likelist.me">찜한 상품</a>
                                </li>
                                <li class="sub-title-list">
                                    <a href="<%= contextPath %>/buylist.me">구매 내역</a>
                                </li>
                                <li class="sub-title-list">
                                    <a href="<%= contextPath %>/selllist.me">판매 내역</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
               </div>
            </div>
        </div>
        <div id="right-content">
            <div id="right-content-wrap">
                <div id="right-content-title">
                    거래 후기
                </div>

                <div id="trade-review-count">
                    받은 후기
                    <span>38</span>
                </div>

                <div id="trade-review-total">                                               
                    <div>
                        <div>5</div>
                        <div>⭐⭐⭐⭐⭐</div>
                    </div>
                    <div>
                        <div>100%</div>
                        <div><small>만족후기</small></div>
                    </div>
                </div>

                <div>
                    <div id="review-select-section-top">
                        <div class="flex-class">
                            <div class="flex-class">
                                <div>❤️</div>
                                <div>친절하고 배려가 넘쳐요.</div>
                            </div>
                            <div>2</div>
                        </div>
                        <div class="flex-class">
                            <div class="flex-class">
                                <div>🎁</div>
                                <div>포장이 깔끔해요.</div>
                            </div>
                            <div>1</div>
                        </div>
                        <div class="flex-class">
                            <div class="flex-class">
                                <div>🗨️</div>
                                <div>채팅 답변이 빨라요.</div>
                            </div>
                            <div>4</div>
                        </div>
                    </div>
                    <div id="review-select-section-bottom">
                        <div class="flex-class">
                            <div class="flex-class">
                                <div>✅</div>
                                <div>상품 설명과 실제 상품이 동일해요.</div>
                            </div>
                            <div>3</div>
                        </div>
                        <div class="flex-class">
                            <div class="flex-class">
                                <div>📄</div>
                                <div>상품 정보가 자세히 적혀있어요.</div>
                            </div>
                            <div>5</div>
                        </div>
                        <div class="flex-class">
                            <div class="flex-class">
                                <div>🛻</div>
                                <div>배송이 빨라요.</div>
                            </div>
                            <div>2</div>
                        </div>
                    </div>
                </div>

                <div>
                    <div id="arrow-img">
                        <img src="resources/images/downArrow.png" alt="화살표 이미지" width="20" height="20" onclick="reviewOpen(this);">
                    </div>
                </div>

                <script>
                    
                    let newImg = "resources/images/upArrow.png";
                    let currentImg = "resources/images/downArrow.png";

                    function reviewOpen(btn){

                        const reviewBtn = btn;
                        
                        if($("#review-select-section-bottom").css('display') == 'none'){
                            $("#review-select-section-bottom").css('display', 'block');
                            $(reviewBtn).attr('src', newImg);
                        } else {
                            $("#review-select-section-bottom").css('display', 'none');
                            $(reviewBtn).attr('src', currentImg);
                        }
                    }

                </script>

                <div>
                    <div>구매자 프로필 사진 자리</div>
                    <div>
                        <div>
                            <div>구매자 닉네임</div>
                            <div>1개월 전</div>
                        </div>
                        <div>⭐⭐⭐⭐⭐</div>
                        <div>판매상품 제목</div>
                        <div>리뷰 사진</div>
                        <div>리뷰 내용</div>
                        <div>
                            <div>상품 설명과 실제 상품이 동일해요.</div>
                            <div>배송이 빨라요.</div>
                        </div>
                        <div>신고하기</div>
                    </div>
                </div>

            </div>
        </div>
    </div>


	<%@ include file = "../common/footer.jsp" %>

</body>
</html>