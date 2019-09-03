package com.emp.service;

import java.util.List;

import com.emp.entity.Dept;

//部门业务层对象
public interface DeptService {
  
	 //查询所有部门
	 List<Dept> queryAllDepts();
}
