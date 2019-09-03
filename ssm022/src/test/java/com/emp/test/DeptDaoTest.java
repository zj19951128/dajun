package com.emp.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emp.dao.DeptDao;
import com.emp.dao.DeptLazyDao;
import com.emp.entity.Dept;
import com.emp.entity.Emp;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:applicationContext.xml"})  
public class DeptDaoTest {
	
	@Resource
	private DeptDao deptDao;
	@Resource
	private DeptLazyDao deptLazyDao;

	@Test
	public void testQueryAll() {
		  List<Dept> ds = deptDao.queryAll();
		  for(Dept d:ds){
			   System.out.println(d);
			   System.out.println(d.getEmps());
		  }
	}
	
	@Test
	public void testQueryAll2(){
		   List<Dept> depts = deptLazyDao.queryAll();
		   for(Dept d:depts){
			    System.out.println(d.getDname());
			    System.out.println("~~~~~~~~~~~~~~~~~~");
			     //打印部门下所有的员工姓名
			        List<Emp>  es = d.getEmps();
                    for(Emp e:es){
                    	  System.out.println(e.getEname());
                    }
			        
			    System.out.println("~~~~~~~~~~~~~~~~~~");
			    
		   }
	}
	
	
	@Test
	public void testQueryById(){
		   Dept dept = deptLazyDao.queryById("d001");
		   System.out.println(dept.getDname());
		   System.out.println("~~~~~~~~~~~~~~~~~");
		    List<Emp> emps = dept.getEmps();
		    for(Emp e:emps){
		    	 System.out.println(e.getEname());
		    }
	}

}
