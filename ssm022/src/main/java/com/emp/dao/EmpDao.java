package com.emp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.emp.entity.Emp;

public interface EmpDao {
    //查询所有员工
	@Select(" select e.empno,e.ename,e.esex,e.eage,e.esalary, "
			+ " d.deptno,d.dname,d.location, "
			+ " m.empno mno, m.ename mname "
			+ " from t_emp e left join t_dept d "
			+ " on e.deptno = d.deptno "
			+ " left join t_emp m "
			+ " on e.mgrno = m.empno ")
	@Results(id="empMapper",value={
		 @Result(id=true,column="empno",property="empno"),
		 @Result(column="ename",property="ename"),
		 @Result(column="esex",property="esex"),
		 @Result(column="eage",property="eage"),
		 @Result(column="esalary",property="esalary"),
		 @Result(column="deptno",property="dept.deptno"),
		 @Result(column="dname",property="dept.dname"),
		 @Result(column="location",property="dept.location"),
		 @Result(column="mno",property="mgr.empno"),
		 @Result(column="mname",property="mgr.ename")
	})
	List<Emp> queryAll();
    //依据员工编号查询员工
	@Select(" select e.empno,e.ename,e.esex,e.eage,e.esalary, "
			+ " d.deptno,d.dname,d.location, "
			+ " m.empno mno, m.ename mname "
			+ " from t_emp e left join t_dept d "
			+ " on e.deptno = d.deptno "
			+ " left join t_emp m "
			+ " on e.mgrno = m.empno  "
			+ " where e.empno=#{empno} ") 
	@ResultMap("empMapper")
	Emp queryById(@Param("empno")String empno);
	//依据姓名模糊查询
	@Select(" select e.empno,e.ename,e.esex,e.eage,e.esalary, "
			+ " d.deptno,d.dname,d.location, "
			+ " m.empno mno, m.ename mname "
			+ " from t_emp e left join t_dept d "
			+ " on e.deptno = d.deptno "
			+ " left join t_emp m "
			+ " on e.mgrno = m.empno  "
			//+ " where  e.ename like CONCAT('%',#{ename},'%')" ) 
			+" where INSTR(e.ename,#{ename}) >0 ") //优化
	@ResultMap("empMapper")
	List<Emp> queryLikeName(@Param("ename")String ename);
	//添加员工
	@Insert(" insert into t_emp values(#{empno},#{ename},#{esex},#{eage},#{esalary} "
			+ ", #{dept.deptno},#{mgr.empno}) ")
	void addEmp(Emp emp);
	//修改员工
	@Update(" update  t_emp set ename=#{ename},esex=#{esex},eage=#{eage}, "
			+ " esalary=#{esalary}, deptno=#{dept.deptno},mgrno=#{mgr.empno} "
			+ "  where empno=#{empno}")
	void updateEmp(Emp emp);
	//依据编号删除员工
	@Delete(" delete from t_emp where empno=#{empno}")
	void deleteEmp(@Param("empno")String empno);
	
	 //作业
	 //a.依据部门编号查询部门下所有的员工
	@Select(" select e.empno,e.ename,e.esex,e.eage,e.esalary, "
			+ " d.deptno,d.dname,d.location, "
			+ " m.empno mno, m.ename mname "
			+ " from t_emp e left join t_dept d "
			+ " on e.deptno = d.deptno "
			+ " left join t_emp m "
			+ " on e.mgrno = m.empno "
			+ " where e.deptno=#{deptno} ")
	@ResultMap("empMapper")
	List<Emp> queryEmpByDeptno(@Param("deptno")String deptno);
	
	 //b.完成部门Dao DeptDao的编写
	 // 查询所有 ,依据编号查询,增删改  
	
	//查询出所有的经理		
	@Select(" select DISTINCT m.empno,m.ename "
			+ " from t_emp m inner join t_emp e "
			+ " on m.empno = e.mgrno  "
			+ " where m.deptno is not null ")
	List<Emp> queryMgrs();
	
}
