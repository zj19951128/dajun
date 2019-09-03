package com.emp.service;

import java.util.List;

import com.emp.entity.Emp;
import com.emp.utils.PageBean;

public interface EmpService {
      //分页查询
	   PageBean<Emp>  queryByPage(Integer pageNo,Integer pageSize);
	  //条件分页查询
	  PageBean<Emp> queryByCondition(Integer pageNo,Integer pageSize,
			     String ename);
	  //依据编号查询员工
	  Emp queryEmpById(String empno);
	  //添加员工
	  void addEmp(Emp emp);	 
	  //修改员工
	  void updateEmp(Emp emp);
	  //删除员工	
	   void deleteEmp(String empno);
	   
	   //查询所有的经理
	   List<Emp> queryMgrs();
}
