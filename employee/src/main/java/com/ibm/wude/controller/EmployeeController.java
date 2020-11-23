package com.ibm.wude.controller;

import java.util.List;

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

@CrossOrigin
@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/addEmploy")
	public int addEmploy(@RequestBody EmployeeModel employee) {
		return employeeService.addEmploy(employee);
	}

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
//	@GetMapping("/delEmpById/{id}")
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
	@RequestMapping("/getEmpByPage")
	public Pager<EmployeeModel> getEmpByPage(@RequestBody Pager<EmployeeModel> page) {
		return employeeService.getEmpByPage(page);
	}
}
