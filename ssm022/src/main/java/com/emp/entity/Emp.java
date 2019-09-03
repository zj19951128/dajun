package com.emp.entity;

//员工类
public class Emp {
	private String empno;// 员工编号

	private String ename;// 姓名

	private String esex;// 性别

	private Integer eage;// 年龄

	private Float esalary;// 薪资

	//private String deptno;// 部门编号
    private Dept dept; //部门 关联属性
    
	//private String mgrno;// 经理编号
    private Emp mgr;//经理 关联属性

	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Emp(String empno, String ename, String esex, Integer eage, Float esalary, Dept dept, Emp mgr) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.esex = esex;
		this.eage = eage;
		this.esalary = esalary;
		this.dept = dept;
		this.mgr = mgr;
	}



	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEsex() {
		return esex;
	}

	public void setEsex(String esex) {
		this.esex = esex;
	}

	public Integer getEage() {
		return eage;
	}

	public void setEage(Integer eage) {
		this.eage = eage;
	}

	public Float getEsalary() {
		return esalary;
	}

	public void setEsalary(Float esalary) {
		this.esalary = esalary;
	}



	public Dept getDept() {
		return dept;
	}



	public void setDept(Dept dept) {
		this.dept = dept;
	}



	public Emp getMgr() {
		return mgr;
	}



	public void setMgr(Emp mgr) {
		this.mgr = mgr;
	}



	@Override
	public String toString() {
		return "Emp [empno=" + empno + ", ename=" + ename + ", esex=" + esex + ", eage=" + eage + ", esalary=" + esalary
				+ ", dept=" + dept + ", mgr=" + mgr + "]";
	}

	

	

}
