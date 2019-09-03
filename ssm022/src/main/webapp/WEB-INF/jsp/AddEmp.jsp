<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="fm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
   table{
        width:240px;
        border:3px solid #ccc;
        border-collapse: collapse;
        margin:auto;
        margin-top:20px;
   }
   
   table td{
       border:1px solid #ccc;
   }

</style>
</head>
<body>
     <h2 align="center">添加员工</h2>
     <!--  使用spring表单标签,表单中必须要有modelAttribute -->
      <fm:form modelAttribute="emp"  name="addForm"
          action="${pageContext.request.contextPath}/emp" 
          method="POST">
          <p align="center">
            <!-- 显示所有的错误消息 -->
               <fm:errors path="*"  cssStyle="color:red"></fm:errors>
          </p>
          <fm:hidden path="empno"/>
         <table>
             <tr>
                <td>姓名</td>
                <td>
                   <fm:input path="ename"/>
                
                </td>
             </tr>
              <tr>
                <td>性别</td>
                <td>
   <!-- 
                <label>
                  <input type="radio" name="esex" value="男" checked/> 男
                </label>
                <label>
                   <input type="radio" name="esex" value="女"  /> 女
                </label> -->
                <fm:radiobuttons path="esex" items="${map}"/>               
                </td>
             </tr>
             <tr>
               <td>年龄</td>
               <td>
                  <fm:input path="eage"/>
               </td>
             </tr>
            <tr>
                <td>薪资</td>
                <td>
                  <fm:input path="esalary"  />
                </td>
            </tr>
            <tr>
               <td>部门</td>
               <td>
              <!--     <select name="deptno">
                     <option value="d001">java开发部</option>
                     <option value="d002">市场部</option>
                     <option value="d003">后勤部</option>
                  </select>
                  itemLabel 给用户看的 应该是dname
                  itemValue 提交给后台服务器的数据 应该是deptno
                   -->
                 <fm:select path="dept.deptno" items="${depts}"
                    itemLabel="dname"  itemValue="deptno"
                 ></fm:select>
               </td>          
            </tr>
            <tr>
                <td>经理</td>
                <td>
                 <!--  
                    select * from t_emp
                    where empno in(
                    select mgrno from t_emp)
                    
                    select distinct m.*
                    from t_emp e inner join t_emp m 
                    on e.mgrno = m.empno
                  -->
                 <!--   <select name="mgrno">
                        <option value="e001">熊大</option>
                        <option value="e004">李四</option>
                        <option value="e007">孙七</option>
                   </select> -->
                   <fm:select path="mgr.empno" items="${mgrs}"
                     itemLabel="ename" itemValue="empno"
                   ></fm:select>
                   
                </td>
            </tr>
            <tr align="center">
               <td colspan="2">
                  <input type="submit"  value="保存"/>
                  <input type="button" value="重置"  onclick="doReset();"/>
               </td>
            </tr>
         </table>
      </fm:form>
      <script type="text/javascript">
          //性别默认选中男
          var ck = document.getElementById("esex2");
          ck.checked = true;
          //表单重置函数
           function doReset(){
        	    //document.addForm.reset();
        	    document.addForm.ename.value="";
        	     var ck = document.getElementById("esex2");
                 ck.checked = true;
        	    document.addForm.eage.value="";
        	    document.addForm.esalary.value="";
        	    document.addForm.deptno.value="d001";
        	    document.addForm.mgrno.value="e001";        	    
          }
      </script>
</body>
</html>