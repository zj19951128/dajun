package com.emp.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emp.dao.EmpDao;
import com.emp.dao.EmpLazyDao;
import com.emp.entity.Dept;
import com.emp.entity.Emp;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:applicationContext.xml"})  
public class EmpDaoTest {
	@Resource
   private EmpDao empDao;
	@Test
	public void testQueryAll() {
	       List<Emp> emps = empDao.queryAll();
	       for(Emp e:emps){
//	    	    System.out.println(e.getEname()+","+e.getDept().getDname()
//	    	    		+","+e.getMgr().getEname());
	    	   System.out.println(e);
	       }
	}
	
	@Test
	public void testQueryById(){
		   Emp e = empDao.queryById("d529218e-e7b9-4270-83d1-e44bf19a94ce");
		   System.out.println(e);
	}
	
	@Test
	public void testQueryLike(){
		  List<Emp> es = empDao.queryLikeName("李");
		  for(Emp e:es){
			   System.out.println(e); 
		  }
	}
	
	@Test//添加员工
	public void testAddEmp(){
		   //创建一个员工对象
		  Emp e = new Emp();
		  e.setEmpno("e668");
		  e.setEname("eric");
		  e.setEsex("男");
		  e.setEage(33);
		  e.setEsalary(30000F);
		  //创建一个部门对象
		  Dept dept = new Dept();
		  dept.setDeptno("d001");
		  e.setDept(dept);
		 //创建一个经理对象
		  Emp mgr = new Emp();
		  mgr.setEmpno("e001");
		  e.setMgr(mgr);
		  //将e对象保存到数据库中
		  empDao.addEmp(e);
		  System.out.println("OK");
		  
	}
	
	@Test//删除
	public void testDelete(){
		    empDao.deleteEmp("e668");
		    System.out.println("OK");
	}
	
	@Test
	public void testUpdate(){
		   Emp e = empDao.queryById("e002");
	      System.out.println(e);
	       e.setEsalary(15000F);
	       e.getDept().setDeptno("d002");
	       e.getMgr().setEmpno("e004");
	      //将修改的数据更新到数据库中
	       empDao.updateEmp(e);
	       System.out.println("OK");       
	}
	
	@Test//依据部门编号查询部门下所有的员工
	public void testQueryByDeptno(){
		     List<Emp> es = empDao.queryEmpByDeptno("d001");
		     for(Emp e:es){
		    	  System.out.println(e);
		     }
	}

	@Test
	public void testQueryMgr(){
		     List<Emp> mgrs = empDao.queryMgrs();
		     for(Emp m:mgrs){
		    	  System.out.println(m);
		     }
	}
	
	@Resource
	private EmpLazyDao empLazyDao;
	
	@Test//测试懒加载
	public void testLazy(){
		Emp e = empLazyDao.queryById("e002");
		/*
		System.out.println(e.getEname());
		System.out.println("~~~~~~~~~~~~~~~~~~");
		System.out.println(e.getDept().getDname());*/
		System.out.println(e);
	}
	
	@Test//测试懒加载
	public void testLazy2(){
	   List<Emp> es = empLazyDao.queryAll();
	    for(Emp e:es){
	    	 if(e.getDept()!=null)
	    	 System.out.println(e.getEname()+","+e.getDept().getDname());
	    }

	}
	
}
