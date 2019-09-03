package com.emp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.emp.entity.Dept;
import com.emp.entity.Emp;
import com.emp.service.DeptService;
import com.emp.service.EmpService;
import com.emp.utils.PageBean;

@Controller
public class EmpController {
    //注入业务层对象
	@Resource
	private EmpService empService;
	
	@Resource
	private DeptService deptService;
	
	public void loadData(HttpSession session){
		 //性别 map
		 Map<String,String> map = new HashMap<String,String>();
		 map.put("男", "男");
		 map.put("女", "女");
		 session.setAttribute("map", map);
		 // ${depts} 所有部门数据
		 List<Dept> depts = deptService.queryAllDepts();
		 session.setAttribute("depts", depts);
		 //${mgrs} 所有经理
		  List<Emp> mgrs = empService.queryMgrs();
		  session.setAttribute("mgrs", mgrs);
	}
	
	//删除员工
	@RequestMapping(value="/emp/{empno}",method=RequestMethod.DELETE)
	public String deleteEmp(@PathVariable("empno")String empno){
		 empService.deleteEmp(empno);
		 return "redirect:/emp/conditionList";
	}
	
	//跳转到修改页面
	@RequestMapping("/emp/toUpdate")
	public String toUpdate(@ModelAttribute("emp")Emp emp,
			 @RequestParam("empno")String empno ,Model model,
			  HttpSession session){
		    loadData(session);//加载数据
		    emp = empService.queryEmpById(empno);
		    //将模型属性放入到作用域中
		    model.addAttribute("emp", emp);
		    return "UpdateEmp";
	}
	
	//修改员工
	@RequestMapping(value="/emp",method=RequestMethod.PUT)
	public String updateEmp(Emp emp,HttpSession session){
		  //更新修改信息到数据库中
		  empService.updateEmp(emp);
		  //从session中取出pageBean和查询条件cd 
		  PageBean<Emp> pageBean
		    = (PageBean<Emp>)session.getAttribute("pageBean");
		  String cd =(String)session.getAttribute("cd");
		  //更新一下pageBean的list
		  pageBean  
		        = empService.queryByCondition(pageBean.getPageNo(),
				  pageBean.getPageSize(), cd);
		   //将pageBean对象重新放回session中
		   session.setAttribute("pageBean", pageBean);
		  return "redirect:/emp/reList";
	}
	
	@RequestMapping("/emp/reList")
	public String toEmpList(){
		 return "ListEmp";
	}
	
	//跳转到添加页面
	@RequestMapping("/emp/toAdd")
	public String toAddEmp(@ModelAttribute("emp") Emp emp,
			HttpSession session){
		   loadData(session);
		   return "AddEmp";
	}
	
	  //分页查询
	@RequestMapping("/emp/list")
	  public String queryByPage(
	  @RequestParam(value="pageNo",required=false,defaultValue="1")Integer pageNo,
	  @RequestParam(value="pageSize",required=false,defaultValue="3") Integer pageSize,
	  Model model){
		      PageBean<Emp> pageBean = empService.queryByPage(pageNo, pageSize);
	          //将pageBean对象放入作用域中
		      model.addAttribute("pageBean", pageBean);
		      return "ListEmp";
	  }
	
	 //添加员工
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	 public String addEmp(Emp emp){
		   //uuid生成emp主键
		    emp.setEmpno(UUID.randomUUID().toString());
		    empService.addEmp(emp);
		    return "redirect:/emp/conditionList";
	 }
	
	//条件分页查询
	@RequestMapping("/emp/conditionList")
	@RequiresPermissions(value={"emp:query","emp:del"},logical=Logical.OR)
	//@RequiresRoles(value={"普通用户","管理员"},logical=Logical.OR)
	public String queryCondition(
		@RequestParam(value="pageNo",required=false,defaultValue="1")Integer pageNo,
	    @RequestParam(value="pageSize",required=false,defaultValue="3")Integer pageSize,
		@RequestParam(value="cd",required=false,defaultValue="")String cd,
		HttpSession session
			){
		   PageBean<Emp> pageBean = empService.queryByCondition(pageNo, pageSize, cd);
		   //将pageBean和cd 放入作用域中
		   session.setAttribute("pageBean", pageBean);
		   session.setAttribute("cd", cd);
		return "ListEmp";
	}
}
