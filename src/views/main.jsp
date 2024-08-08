<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task Management Portal</title>
    <link rel="stylesheet" type="text/css" href="/css/main.css" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
<!--     <script>
        $(document).ready(function() {
            // 통합업무 버튼 클릭 시 AJAX 요청
            $('a[href="customerList"]').click(function(e) {
                e.preventDefault(); // 링크 기본 동작 방지
                $.ajax({
                    url: 'customerList',
                    type: 'GET',
                    dataType: 'html',
                    success: function(response) {
                        var extractedContent = $(response).find('.main').html();
                        $('.main').html(extractedContent); // 받은 HTML로 .main 내용을 교체
                    }
                });
            });
        });
    </script> -->
    
</head>
<body>
    <div class="container">
        <div class="header">
            <div class="profile">
                <img src="profile_picture_url" alt="Profile Picture" width="50" height="50">
                <div class="profile-info">
                    <div class="name">Java Park</div>
                    <div class="rank">Lv.5</div>
                    <div>Java Park님 환영합니다.</div>
                </div>
            </div>
            <a href="#" class="logout">로그아웃</a>
        </div>
        <div class="content">
            <div class="sidebar">
                <ul>
                    <li><a href="/attendance/managementList">통합업무</a></li>
                    <li><a href="customerList">게시판</a></li>
                    <li><a href="#">익명게시판</a></li>
                    <li><a href="#">전자결재</a></li>
                    <li><a href="#">직원관리</a></li>
                    <li><a href="#">팀장전자결재</a></li>
                    <li><a href="#">캘린더</a></li>
                    <li><a href="#">권한관리</a></li>
                </ul>
            </div>
            <div class="main">
                <!-- 여기에 기본 메인 내용이 들어갑니다 -->
                <div class="status-box">
                    <table>
                        <tr>
                            <th>구분</th>
                            <th>기준</th>
                            <th>사용</th>
                            <th>잔여</th>
                        </tr>
                        <tr>
                            <td>연차</td>
                            <td>11일</td>
                            <td>2일</td>
                            <td>5일</td>
                        </tr>
                        <tr>
                            <td>출근</td>
                            <td>11일</td>
                            <td>2일</td>
                            <td>0일</td>
                        </tr>
                    </table>
                </div>
                <div class="calendar">
                    <div class="month">September, 2024</div>
                    <div>일정관리</div>
                </div>
                
                <table class="calendar-table">
                    <thead>
                        <tr>
                            <th>Mon</th>
                            <th>Tue</th>
                            <th>Wed</th>
                            <th>Thu</th>
                            <th>Fri</th>
                            <th>Sat</th>
                            <th>Sun</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>29</td>
                            <td>30</td>
                            <td>31</td>
                            <td>1</td>
                            <td>2</td>
                            <td>3</td>
                            <td>4</td>
                        </tr>
                        <tr>
                            <td style="background-color: #bae7ff;">5</td>
                            <td style="background-color: #bae7ff;">6</td>
                            <td style="background-color: #bae7ff;">7</td>
                            <td style="background-color: #bae7ff;">8</td>
                            <td style="background-color: #bae7ff;">9</td>
                            <td>10</td>
                            <td>11</td>
                        </tr>
                        <tr>
                            <td>12</td>
                            <td>13</td>
                            <td>14</td>
                            <td>15</td>
                            <td>16</td>
                            <td>17</td>
                            <td>18</td>
                        </tr>
                        <tr>
                            <td>19</td>
                            <td>20</td>
                            <td>21</td>
                            <td>22</td>
                            <td>23</td>
                            <td>24</td>
                            <td>25</td>
                        </tr>
                        <tr>
                            <td>26</td>
                            <td>27</td>
                            <td>28</td>
                            <td>29</td>
                            <td>30</td>
                            <td>1</td>
                            <td>2</td>
                        </tr>
                    </tbody>
                </table>
                <div class="notes">
                    <div class="note">
                        12:20 부장님과 점심<br>
                        14:50 미팅
                    </div>
                    <div class="note green">
                        Add notes
                    </div>
                </div>
            </div>
        </div>
        <footer>
            <div>
                <a href="#">© 2022 Brand, Inc.</a>
                <a href="#">Privacy</a>
                <a href="#">Terms</a>
                <a href="#">Sitemap</a>
            </div>
            <div>
                <a href="#">USD</a>
                <a href="#">Language</a>
                <a href="#"><img src="facebook_icon_url" alt="Facebook"></a>
                <a href="#"><img src="twitter_icon_url" alt="Twitter"></a>
                <a href="#"><img src="instagram_icon_url" alt="Instagram"></a>
            </div>
        </footer>
    </div>
</body>
</html>