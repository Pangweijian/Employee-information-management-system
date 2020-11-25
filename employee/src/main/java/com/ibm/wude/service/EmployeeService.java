package com.ibm.wude.service;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.wude.ExcelUtil;
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

	public List<EmployeeModel> findEmployeeModel(String string) {
		return employeeMapper.findEmployeeModel(string);
	}

	public void export(HttpServletRequest request, HttpServletResponse response) {
		// 获取数据
		List<EmployeeModel> list = employeeMapper.getAllEmployee();

		// excel标题
		String[] title = { "ID", "姓名", "工资", "性别" };

		// excel文件名
		String fileName = "员工信息表.xls";

		// sheet名
		String sheetName = "员工信息表";

		String[][] content = new String[list.size()][title.length];
		for (int i = 0; i < list.size(); i++) {
			content[i][0] = list.get(i).getId().toString();
			content[i][1] = list.get(i).getName().toString();
			content[i][2] = list.get(i).getSalary().toString();
			content[i][2] = list.get(i).getAge().toString();
		}

		// 创建HSSFWorkbook
		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

		// 响应到客户端
		try {
			ExcelUtil.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
