package com.emp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.emp.entity.Dept;
//编写懒加载 部门Dao
public interface DeptDao {
    //查询所有部门
	@Select("select deptno,dname,location from t_dept ")
	List<Dept> queryAll();
	
	//依据部门编号查询部门
	@Select("select deptno,dname,location from t_dept "
			+ " where deptno=#{deptno} ")
	Dept queryById(@Param("deptno")String deptno);
	
	//增
	@Insert("insert into t_dept values(#{deptno},#{dname},#{location})")
	 void addDept(Dept dept);
	//删除 -- 依据编号删除
	@Delete("delete from t_dept where deptno=#{deptno}")
	void deleteDept(@Param("deptno")String deptno);
	//改
	@Update("update t_dept set dname=#{dname},location=#{location} "
			+ " where deptno=#{deptno}")
	void updateDept(Dept dept);
}
