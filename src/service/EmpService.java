package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.Empdao;
import com.spring.dto.EmpDto;

@Service
public class EmpService {
	
	@Autowired
	Empdao dao;
	
	public EmpDto login(int no) {
		return dao.login(no);
	}
	public int getRight(String position) {
		return dao.getRight(position);
	}
	public int loginCount(int count) {
		return dao.loginCount(count);
	}
	public int getCount(int empno) {
		return dao.getLoginCount(empno);
	}
	
	
}