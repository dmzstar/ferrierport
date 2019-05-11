package com.weifan.ferrier.springboot.admin.sec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

public class Datatables {
	
	@Data
	public static class ObjectData<T>{
		
		private Integer draw = 1;
		private Long recordsTotal = 0l;
		private Long recordsFiltered = 0l;
		private List<Map<String,Object>> data = new ArrayList<>();
		
		@JsonIgnore
		private Page<T> page;
		
		public ObjectData(Page<T> page,int draw) {
			this.recordsTotal = page.getTotalElements();
			this.recordsFiltered = page.getTotalElements();
			this.draw = draw;
			this.page = page;
		}
		
		public ObjectData<T> rowMap(Fun<T> fun) {
			for(var item : page.getContent()) {
				Map<String,Object> rowMap = new HashMap<>();
				fun.map(rowMap,item);
				data.add(rowMap);
			}
			return this;
		}
		
		public static interface Fun<T>{
			public void map(Map<String,Object> rowMap,T t);
		}
		
	}
	
	
	/**
	 * 
	 * @author dong
	 * @param <T>
	 */
	@Data
	public static class ArrayData<T>{
		
		@JsonIgnore
		private Page<T> page;
		
		/**
		 * 
		 * @param page
		 * @param draw 构造函数draw参数客户端生成并传递，否则表格渲染出错
		 */
		public ArrayData(Page<T> page,int draw) {
			this.recordsTotal = page.getTotalElements();
			this.recordsFiltered = page.getTotalElements();
			this.draw = draw;
			this.page = page;
		}
		
		public ArrayData<T> rowMap(Fun<T> fun) {
			var i = 0;
			for(var item : page.getContent()) {
				var itemList = new ArrayList<String>();
				for(int j=0;j<=1;j++) {
					itemList.add(fun.todo(j,item));
				}
				itemList.add("" + (i++));
				data.add(itemList);
			}
			return this;
		}
		
		private Integer draw = 1;
		private Long recordsTotal = 0l;
		private Long recordsFiltered = 0l;
		private List<List<String>> data = new ArrayList<>();
		
		public static interface Fun<T>{
			public String todo(int index,T t);
		}
		
	}

}
