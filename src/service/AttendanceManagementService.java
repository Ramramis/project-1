package com.spring.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.AttendanceManagementDao;
import com.spring.dto.AttendanceManagementDto;

@Service
public class AttendanceManagementService {
    
	 	@Autowired
	    private AttendanceManagementDao dao;

	 	  public void checkIn(int empno, int deptno) { // 출근
		        dao.insertStartTmie(empno, deptno);
		    }

		    public void checkOut(int empno) { //퇴근
		        dao.updateEndtime(empno);
		    }

	    public List<AttendanceManagementDto> getAttendanceByDateRange(int empno, LocalDate startDate, LocalDate endDate) { //검색 된 리스트
	        return dao.getAttendanceByDateRange(empno, startDate, endDate);
	    }

	    
	    public int count(int empno) { //글 갯수
	    	return dao.count(empno);
	    }
	    
	    public List<AttendanceManagementDto> getAllManagement() { // 엑셀 다운로드할때 필요한 값
	        return dao.getAllManagement();
	    }
	    
	    public int count2() { // 전체 글갯수
	    	return dao.count2();
	    }
	    
	    public List<AttendanceManagementDto>managementList(int start){ //페이징 리스트
			
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("start", start);
			m.put("count", 10);
			return dao.managementList(m);
		}
}