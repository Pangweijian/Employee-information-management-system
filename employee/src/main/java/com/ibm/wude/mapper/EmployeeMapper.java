package com.ibm.wude.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ibm.wude.model.EmployeeModel;

@Mapper
public interface EmployeeMapper {

	public int addEmploy(EmployeeModel employee);

	public boolean deleteEmployeeById(Integer id);

	public boolean updateEmployee(EmployeeModel employeeModel);

	public EmployeeModel getEmployeeModelByName(String name);

	public EmployeeModel getEmployeeModelById(Integer id);

	public List<EmployeeModel> getAllEmployee();

	public List<EmployeeModel> getEmpByPage(Map<String, Object> params);

	public long count();

}
