package com.spring.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.dto.AttendanceManagementDto;
import com.spring.dto.EmpDto;
import com.spring.service.AttendanceManagementService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/attendance")
@SessionAttributes("user")
public class AttendanceManagementController {

    @Autowired
    private AttendanceManagementService service;

    @ModelAttribute("user") // @SessionAttributes 로그인정보 받아 오는 메서드
    public EmpDto getDto() {
       return new EmpDto();
    }
    
    @GetMapping("/managementList") // 페이징처리
    public String getManagementList(@ModelAttribute("user") 
    												@RequestParam(name = "p", defaultValue = "1") int page, Model model) {
    	int count = service.count2();
    	if (count > 0) {
            int perPage = 10;
            int startRow = (page - 1) * perPage;

            List<AttendanceManagementDto> attendanceList = service.managementList(startRow);
            model.addAttribute("attendanceList", attendanceList);

            int pageNum = 5;
            int totalPages = count / perPage + (count % perPage > 0 ? 1 : 0);
            int begin = (page - 1) / pageNum * pageNum + 1;
            int end = begin + pageNum - 1;
            if (end > totalPages) {
                end = totalPages;
            }
            model.addAttribute("begin", begin);
            model.addAttribute("end", end);
            model.addAttribute("pageNum", pageNum);
            model.addAttribute("totalPages", totalPages);
        }
        model.addAttribute("count", count);
        return "amc/managementList";
    }

    @PostMapping("/checkIn") //출근시간입력
    public String checkIn(@ModelAttribute("user") EmpDto user ) {   
        service.checkIn(user.getEmpno(),user.getDeptno());
        return "redirect:/attendance/managementList";
    }

    @PostMapping("/checkOut") // 퇴근시간입력
    public String checkOut(@ModelAttribute("user") EmpDto user) {
        service.checkOut(user.getEmpno());
        return "redirect:/attendance/managementList";
    }

    @GetMapping("/search") // 날짜검색
    public String search(@RequestParam("startDate") String startDate,
            						@RequestParam("endDate") String endDate,
            							@ModelAttribute("user") EmpDto user,
            								Model model) {
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");//날짜값을 DB형식에 맞게 초기화
    	LocalDate start = LocalDate.parse(startDate,formatter);
    	LocalDate end = LocalDate.parse(endDate,formatter);
        List<AttendanceManagementDto> filteredAttendanceList = service.getAttendanceByDateRange(user.getEmpno(), start, end);
        
        model.addAttribute("attendanceList", filteredAttendanceList);
        model.addAttribute("count", filteredAttendanceList.size()); //모델에 검색결과 추가 이 기능 추가로 검색 기능 해결 요소의 개수를 반환 즉 검색된 출근기록 반환
        return "amc/managementList";
    }


    
    @GetMapping("/downloadExcel2") // 엑셀 다운로드
	public void downloadExcel(HttpServletResponse response) throws IOException {
		List<AttendanceManagementDto> attendanceList = service.getAllManagement();

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Attendance");

		// 헤더행
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("출근번호");
		headerRow.createCell(1).setCellValue("출근일자");
		headerRow.createCell(2).setCellValue("출근시간");
		headerRow.createCell(3).setCellValue("퇴근시간");
		headerRow.createCell(4).setCellValue("근무유형");
		headerRow.createCell(5).setCellValue("연차사용");
		headerRow.createCell(6).setCellValue("잔여연차");
		

		// 데이터행
		int rowNum = 1;
		for (AttendanceManagementDto Attendance : attendanceList) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(Attendance.getAttendance_no());
            row.createCell(1).setCellValue(Attendance.getDate().toString());
            row.createCell(2).setCellValue(Attendance.getCheck_in() != null ? Attendance.getCheck_in().toString() : "");
            row.createCell(3).setCellValue(Attendance.getCheck_out() != null ? Attendance.getCheck_out().toString() : "");
            row.createCell(4).setCellValue(Attendance.getWorktype());
            row.createCell(5).setCellValue(Attendance.getAnnual_leave());
		
		}

		// 콘텐츠 유형과 첨부 파일 헤더를 설정
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=customers.xlsx");

		// 출력 스트림에 통합 문서 쓰기
		workbook.write(response.getOutputStream());
		workbook.close();
	}

	
	
}