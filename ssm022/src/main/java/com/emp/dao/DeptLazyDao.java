package com.emp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import com.emp.entity.Dept;

public interface DeptLazyDao {  
	 //查询所有的部门
	@Select("select deptno,dname,location from t_dept")
	@Results(id="deptMapper",value={
			@Result(id=true,column="deptno",property="deptno"),
			@Result(column="dname",property="dname"),
			@Result(column="location",property="location"),
			@Result(column="deptno",property="emps",
			  many=@Many(select="com.emp.dao.EmpDao.queryEmpByDeptno",
			      fetchType=FetchType.LAZY)
					)
	})
	 List<Dept> queryAll();
	 
	 //依据部门编号查询部门
	 @Select("select deptno,dname,location from t_dept where deptno=#{deptno}")
	 @ResultMap("deptMapper")
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
