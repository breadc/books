package com.model;

public class JDBCdriver {

	private String url;
	private String dataName;
	private String password;
	
	
	
	
	public JDBCdriver(String url, String dataName, String password) {
		super();
		this.url = url;
		this.dataName = dataName;
		this.password = password;
	}

	public JDBCdriver() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JDBCdriver(String password) {
		super();
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
