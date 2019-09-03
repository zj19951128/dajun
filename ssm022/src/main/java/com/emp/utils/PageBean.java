package com.emp.utils;

import java.util.List;

//分页工具组件
public class PageBean<E>{ //泛型E代表任意的引用类型
	private Integer pageNo=1;//当前页码
	private Integer pageSize = 3;//页容量
	private List<E> list; //当前页的数据
	private Integer totalCount;//进行分页的总记录数
	
	//上一页  ${previous}
	public Integer getPrevious(){
		 return  this.pageNo==1?1:this.pageNo-1;
		 //return this.pageNo>1?this.pageNo-1:1;
	}	
	//下一页  ${next}
	public Integer getNext(){
		 return this.pageNo==getLast()?getLast():this.pageNo+1;
	}
	
	//首页   jsp页码取值  ${first}
	public Integer getFirst(){
		 return 1;
	}
	//尾页  ${last}
	public Integer getLast(){
		 return getTotalPages();
	}
	//总页数   ${totalPages}
	public Integer getTotalPages(){
		return
		  totalCount%pageSize==0?
				  totalCount/pageSize:totalCount/pageSize+1;
	}
	
	//获得当前页页码  
	//在JSP页码获取这个值   ${pageNo}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	// ${list}
	public List<E> getList() {
		return list;
	}
	public void setList(List<E> list) {
		this.list = list;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
	

}
