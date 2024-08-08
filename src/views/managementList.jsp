<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>근태현황</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 30px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        form {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }
        label {
            margin: 0 10px;
            font-weight: bold;
        }
        input[type="date"] {
            padding: 5px;
            margin: 0 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            padding: 5px 10px;
            background-color: #00bfff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #f8f8f8;
        }
        .button-group {
            text-align: center;
            margin-bottom: 20px;
        }
        .button-group button {
            margin: 0 5px;
        }
       .pagination {
            text-align: center;
            margin: 20px 0;
        }
          .pagination a {
            margin: 0 5px;
            padding: 8px 16px;
            text-decoration: none;
            border: 1px solid #ddd;
            color: #007bff;
            border-radius: 4px;
        }
        .pagination a.active {
            font-weight: bold;
            background-color: #007bff;
            color: white;
        }
        .pagination a:hover {
            background-color: #0056b3;
            color: white;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>${user.empno}님의 근태현황 입니다.</h1>

        <form action="/attendance/search" method="get">
            <label for="startDate">출근일자:</label>
            <input type="date" id="startDate" name="startDate"required>
            
            <label for="endDate">~</label>
            <input type="date" id="endDate" name="endDate" required>
            <button type="submit">검색</button>
            <button type="button" onclick="location.href='/attendance/managementList?p=1'">전체</button>
        </form>
         

        <div class="button-group">
            <form action="/attendance/checkIn" method="post" style="display:inline;">
                <button type="submit">출근</button>
            </form>
            <form action="/attendance/checkOut" method="post" style="display:inline;">
                <button type="submit">퇴근</button>
            </form>
            
            <button type="button" onclick="location.href='/attendance/downloadExcel2'">엑셀다운로드</button>
        </div>

        <table>
            <thead>
                <tr>
                    <th>출근번호</th>
                    <th>출근일자</th>
                    <th>출근시간</th>
                    <th>퇴근시간</th>
                    <th>근무유형</th>
                    <th>연차사용</th>
                    <th>잔여연차</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${count == 0}">
                    <tr>
                        <td colspan="7" class="tac">저장된 출근현황이 없습니다.</td>
                    </tr>
                </c:if>
                <c:if test="${count > 0}">
                    <c:forEach var="attendance" items="${attendanceList}">
                        <tr>
                            <td>${attendance.attendance_no}</td>
                            <td><fmt:formatDate value="${attendance.date}" pattern="yyyy-MM-dd"/></td>
                            <td><fmt:formatDate value="${attendance.check_in}" pattern="HH:mm:ss"/></td>
                            <td><fmt:formatDate value="${attendance.check_out}" pattern="HH:mm:ss"/></td>
                            <td>${attendance.worktype}</td>
                            <td>${attendance.early_leave}</td>
                             <td></td>
                    
                        </tr>
                    </c:forEach>
                </c:if>
            </tbody>
        </table>

      <div class="pagination">
            <c:if test="${begin > pageNum}">
                <a href="managementList?p=${begin-1}">[이전]</a>
            </c:if>
            <c:forEach begin="${begin}" end="${end}" var="i">
                <a href="managementList?p=${i}" class="${i == page ? 'active' : ''}" >${i}</a>
            </c:forEach>
            <c:if test="${end < totalPages}">
                <a href="managementList?p=${end+1}">[다음]</a>
            </c:if>
            <c:if test="${count == 0}">
                아직 입력한 정보가 없습니다.
            </c:if>
        </div>
    </div>

    
    
</body>
</html>