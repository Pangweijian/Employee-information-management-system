package com.ibm.wude.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.wude.mapper.EmployeeMapper;
import com.ibm.wude.model.EmployeeModel;
import com.ibm.wude.model.Pager;

@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;

	public int addEmploy(EmployeeModel employee) {
		return employeeMapper.addEmploy(employee);
	}

	public List<EmployeeModel> getAllEmployee() {
		return employeeMapper.getAllEmployee();
	}

	public EmployeeModel getEmployeeById(Integer id) {
		return employeeMapper.getEmployeeModelById(id);
	}

	public EmployeeModel getEmployeeByName(String name) {
		return employeeMapper.getEmployeeModelByName(name);
	}

	public boolean deleteEmployeeById(Integer id) {
		return employeeMapper.deleteEmployeeById(id);
	}

	public boolean updateEmployee(EmployeeModel employeeModel) {
		return employeeMapper.updateEmployee(employeeModel);
	}

	public Pager<EmployeeModel> getEmpByPage(Pager<EmployeeModel> findPage) {
		Map<String, Object> params = new HashMap<String, Object>();
		int page = findPage.getPage();
		int size = findPage.getSize();
		params.put("page", (page - 1) * size);
		params.put("size", size);
		Pager<EmployeeModel> pager = new Pager<EmployeeModel>();
		List<EmployeeModel> list = employeeMapper.getEmpByPage(params);
		pager.setPage(page);
		pager.setSize(size);
		pager.setRows(list);
		pager.setTotal(employeeMapper.count());
		return pager;
	}
}
