package com.spring.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.spring.dto.CustomerInfoDTO;

@Mapper
public interface CustomerInfoDAO {
	@Insert("insert into CustomerInfo (CustomerName, Email, Address, DateOfBirth, Contact, Status, RegistrationDate, Memo) "
			+ "values (#{customerName}, #{email}, #{address}, #{dateOfBirth}, #{contact}, #{status}, now(), #{memo})")
	void saveCustomerInfo(CustomerInfoDTO customerInfo); //고객정보추가

	@Select("select * from CustomerInfo order by CustomerID desc")
	List<CustomerInfoDTO> getAllCustomers(); // 고객정보조회

	@Select("select * from CustomerInfo where CustomerID = #{id}")
	CustomerInfoDTO getCustomerById(int id); //고객번호 자동지정

	@Delete("<script>" + "delete from CustomerInfo where CustomerID in "
			+ "<foreach item='id' collection='array' open='(' separator=',' close=')'>" + "#{id}" + "</foreach>"
			+ "</script>")
	void deleteCustomers(int[] ids); // 고객삭제
	
	
	@Select("SELECT * FROM CustomerInfo WHERE Status = #{status} ORDER BY CustomerID DESC")
	List<CustomerInfoDTO> getCustomersByStatus(String status);//진행상태 목록별 오름차순정렬
	
	@Select("select count(*) from CustomerInfo")
    int count(); //전체글 갯수
	
	@Select("select * from CustomerInfo order by CustomerID desc limit #{start}, #{count}")
	List<CustomerInfoDTO> customerList(Map<String,Object>m); // 글목록 리스트 최신글이 먼저 보이게 order by CustomerID desc 걸어둠
		
	@Update("update CustomerInfo set contact=#{contact}, email=#{email}, address=#{address}, memo=#{memo},status=#{status} " +
            "where customerID=#{customerID}")
    int updateCustomer(CustomerInfoDTO dto); // 고객정보 수정
	
	@Select("<script>"
	        + "select * from CustomerInfo "
	        + "where 1=1 "
	        + "<if test='customerName != null'>and customerName like concat('%', #{customerName}, '%')</if> "
	        + "<if test='contact != null'>and contact like concat('%', #{contact}, '%')</if> "
	        + "order by CustomerID desc"
	        + "</script>")
	List<CustomerInfoDTO> searchCustomers(@Param("customerName") String customerName, @Param("contact") String contact);

	
	

}
