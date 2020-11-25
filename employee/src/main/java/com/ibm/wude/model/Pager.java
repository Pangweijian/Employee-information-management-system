package com.ibm.wude.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Pager<T> {
	@ApiModelProperty(value = "分页页数")
	private int page; // 分页页数
	@ApiModelProperty(value = "每页记录数")
	private int size; // 每页记录数
	@ApiModelProperty(value = "返回的记录集合")
	private List<T> rows; // 返回的记录集合
	@ApiModelProperty(value = "总记录条数")
	private long total; // 总记录条数

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}
