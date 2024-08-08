package com.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.CustomerInfoDAO;
import com.spring.dto.CustomerInfoDTO;

@Service
public class CustomerInfoService {
	
	 @Autowired
	    private CustomerInfoDAO dao;

	    public void saveCustomerInfo(CustomerInfoDTO customerInfo) {
	    	dao.saveCustomerInfo(customerInfo);
	    }

	    public List<CustomerInfoDTO> getAllCustomers() {
	        return dao.getAllCustomers();
	    }

	    public CustomerInfoDTO getCustomerById(int id) {
	        return dao.getCustomerById(id);
	    }

	    public void deleteCustomers(int[] ids) {
	    	dao.deleteCustomers(ids);
	    }
	    public List<CustomerInfoDTO> getCustomersByStatus(String status) {
	        return dao.getCustomersByStatus(status);
	    }
	    
	    public int count() {
			return dao.count();
		}
	    
	    
	    public List<CustomerInfoDTO>customerList(int start){
			
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("start", start);
			m.put("count", 10);
			return dao.customerList(m);
		}
		
	    
	    public int updateCustomer(CustomerInfoDTO dto) {
			return dao.updateCustomer(dto);
		}
	   
	    public List<CustomerInfoDTO> searchCustomers(String customerName, String contact) {
	        return dao.searchCustomers(customerName, contact);
	    }
	
	    
	    
}
