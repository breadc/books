package com.model;

import java.util.ArrayList;
import java.util.List;

public class TableModel {

	private List<Tables> tabls =new ArrayList<Tables>();
	
	
	public List<Tables> getTabls() {
		return tabls;
	}




	public void setTabls(List<Tables> tabls) {
		this.tabls = tabls;
	}


	public void addTable(String tablName, String dominName) {
		 if(this.tabls ==null){
			 this.setTabls(new ArrayList<Tables>());
		 }
		 this.tabls.add(new Tables(tablName, dominName));
	}
	
	public  List<String[]>  getTableList(){
		List<String[]>  list=new  ArrayList<String[]>();
		for (Tables tables : this.tabls) {
			list.add(new String[]{tables.getTablName(),tables.getDominName()});
		}
		return list;
	}
}

class Tables {
	private String tablName;
	private String dominName;

	public Tables(String tablName, String dominName) {
		super();
		this.tablName = tablName;
		this.dominName = dominName;
	}

	public String getTablName() {
		return tablName;
	}

	public void setTablName(String tablName) {
		this.tablName = tablName;
	}

	public String getDominName() {
		return dominName;
	}

	public void setDominName(String dominName) {
		this.dominName = dominName;
	}
}