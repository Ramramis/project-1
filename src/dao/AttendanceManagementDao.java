package com.spring.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.spring.dto.AttendanceManagementDto;

@Mapper
public interface AttendanceManagementDao {
	@Select("select count(*) from Attendance_Management where empno = #{empno}")
		int count(@Param("empno")int empno); // 목록 카운팅 글 목록이 없을때 글이 없다고 표시함
	
//	@Insert("insert into Attendance_Management(empno,deptno) values(#{empno},#{deptno})")
//	   int insertStartTmie(@Param("empno")int empno,@Param("deptno")int deptno);//출근 시간과 출근일자를 입력해줌
		
	 @Insert("insert into Attendance_Management(empno, deptno, date, check_in, worktype) values(#{empno}, #{deptno}, current_date , now() , '정상근무')")
	    int insertStartTmie(@Param("empno") int empno, @Param("deptno") int deptno); // 출근 시간과 출근일자를 입력해줌
	
	@Update("update Attendance_Management set check_out = now() where empno = #{empno} and date = current_date()")
	   int updateEndtime(int empno);//퇴근 시간을 입력해줌
	
	@Select("select check_in from Attendance_Management where empno = #{empno} and date = current_date() ")
	   Date startTime(int empno); //체크인시간 들고 오는
	
   
    @Select("SELECT * FROM Attendance_Management WHERE empno = #{empno} AND date BETWEEN #{startDate} AND #{endDate}") // 검색
    List<AttendanceManagementDto> getAttendanceByDateRange(@Param("empno") int empno, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    
	@Select("select * from Attendance_Management order by attendance_no desc limit #{start}, #{count}")
	List<AttendanceManagementDto> managementList(Map<String,Object>m); // 글목록 리스트 최신글이 먼저 보이게 order by CustomerID desc 걸어둠
	
	@Select("select count(*) from Attendance_Management")
	int count2(); //전체글 갯수
	
	@Select("select * from Attendance_Management order by attendance_no desc")
	List<AttendanceManagementDto> getAllManagement(); // 고객정보조회 엑셀출력
	
	}