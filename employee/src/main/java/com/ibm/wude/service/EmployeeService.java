package com.ibm.wude.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.wude.mapper.EmployeeMapper;
import com.ibm.wude.model.EmployeeModel;
import com.ibm.wude.model.Pager;
import com.ibm.wude.utils.ExcelUtil;

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

//	public void export(HttpServletRequest request, HttpServletResponse response) {
//		// 获取数据
//		List<EmployeeModel> list = employeeMapper.getAllEmployee();
//
//		// excel标题
//		String[] title = { "ID", "姓名", "工资", "性别" };
//
//		// excel文件名
//		String fileName = "员工信息表.xls";
//
//		// sheet名
//		String sheetName = "员工信息表";
//
//		String[][] content = new String[list.size()][title.length];
//		for (int i = 0; i < list.size(); i++) {
//			content[i][0] = list.get(i).getId().toString();
//			content[i][1] = list.get(i).getName().toString();
//			content[i][2] = list.get(i).getSalary().toString();
//			content[i][2] = list.get(i).getAge().toString();
//		}
//
//		// 创建XSSFWorkbook
//		XSSFWorkbook wb = ExcelUtil.getXssfWorkbook(sheetName, title, content, null);
//
//		// 响应到客户端
//		try {
//			ExcelUtil.setResponseHeader(response, fileName);
//			OutputStream os = response.getOutputStream();
//			wb.write(os);
//			os.flush();
//			os.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public void export(HttpServletResponse response) {
		// 获取导出数据
		List<EmployeeModel> list = employeeMapper.getAllEmployee();
		// 设置文件名、表单名、标题栏
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = "员工信息表" + simpleDateFormat.format(new Date()) + ".xlsx";
		String sheetname = "员工信息表";
		String[] title = { "编号", "姓名", "工资", "年龄" };
		// 声明表单内容
		String[][] content = new String[list.size()][title.length];
		// 遍历要导出的数据列表，构造表单内容
		for (int i = 0; i < list.size(); i++) {
			// 获取表单第i行
			String[] row = content[i];
			// 获取对应的数据实例
			EmployeeModel emp = list.get(i);
			// 填充内容
			row[0] = String.valueOf(emp.getId());
			row[1] = emp.getName();
			row[2] = emp.getSalary();
			row[3] = String.valueOf(emp.getAge());
		}

		// 获取文档
		XSSFWorkbook workbook = ExcelUtil.getWorkbook(sheetname, title, content);
		// 声明输出流
		OutputStream outputStream = null;
		// 响应到客户端
		try {
			// 设置响应头
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");

			// 获取输出流
			outputStream = response.getOutputStream();

			// 用文档写输出流
			workbook.write(outputStream);

			// 刷新输出流
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭输出流
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
