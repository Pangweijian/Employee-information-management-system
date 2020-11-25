package com.ibm.wude.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.wude.model.EmployeeModel;
import com.ibm.wude.model.Pager;
import com.ibm.wude.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(tags = "员工信息Controller")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@ApiOperation(value = "添加员工信息", notes = "传入一个POJO（JSON格式）")
	@PostMapping("/addEmploy")
	public int addEmploy(@RequestBody EmployeeModel employee) {
		return employeeService.addEmploy(employee);
	}

	@ApiOperation(value = "获取所有员工信息", notes = "不必传入参数")
	@GetMapping("/getAllEmployee")
	public List<EmployeeModel> getAllEmployee() {
		List<EmployeeModel> list = employeeService.getAllEmployee();
		return list;
	}

	/**
	 * 查询员工信息
	 * 
	 * @param id
	 * @return 查询结果
	 */
	@ApiOperation("通过ID查询员工信息")
	@GetMapping("/getEmpById/{id}")
	public EmployeeModel getEmployeeModelById(@PathVariable("id") Integer id) {
		return employeeService.getEmployeeById(id);
	}

	/**
	 * 查询员工信息
	 * 
	 * @param name
	 * @return
	 */
	@ApiOperation("通过姓名查询员工信息")
	@GetMapping("/getEmpByName/{name}")
	public EmployeeModel getEmployeeModelByName(@PathVariable("name") String name) {
		return employeeService.getEmployeeByName(name);
	}

	/**
	 * 删除员工信息
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation("通过ID删除员工信息")
	@DeleteMapping("/delEmpById/{id}")
	public boolean deleteEmployeeById(@PathVariable("id") Integer id) {
		EmployeeModel emp = new EmployeeModel();
		emp.setId(id);
		if (getEmployeeModelById(id) != null) {
			return employeeService.deleteEmployeeById(id);
		} else {
			return false;
		}

	}

	/**
	 * 更新员工信息
	 * 
	 * @param employeeModel
	 * @return
	 */
	@ApiOperation(value = "更新员工信息", notes = "传入一个POJO（JSON格式），其中“id”是必须的")
	@PostMapping("/updateEmp")
	public boolean updateEmployee(@RequestBody EmployeeModel emp) {
		if (getEmployeeModelById(emp.getId()) != null) {
			return employeeService.updateEmployee(emp);
		} else {
			return false;
		}
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @return
	 */
	@ApiOperation(value = "分页查询员工信息", notes = "传入一个POJO（JSON格式），其中“page”（页数）和“size”（每页数据量大小）是必须的")
	@PostMapping("/getEmpByPage")
	public Pager<EmployeeModel> getEmpByPage(@RequestBody Pager<EmployeeModel> page) {
		return employeeService.getEmpByPage(page);
	}

	/**
	 * 模糊查询员工信息
	 * 
	 * @param string
	 * @return
	 */
	@ApiOperation("通过ID或姓名模糊查询员工信息")
	@GetMapping("/findEmp/{string}")
	public List<EmployeeModel> finEmployeeModel(@PathVariable String string) {
		return employeeService.findEmployeeModel(string);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ApiOperation("导出员工信息表")
	@RequestMapping("/export")
	public Integer export(HttpServletRequest request, HttpServletResponse response) throws Exception {
		employeeService.export(request, response);
		return 1;
	}
}
