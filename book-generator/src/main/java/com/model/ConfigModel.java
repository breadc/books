package com.model;

import com.util.GenConstants;

public class ConfigModel {
		
	private String xml;
	private String entity;
	private String dao;
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getDao() {
		return dao;
	}
	public void setDao(String dao) {
		this.dao = dao;
	}
	public ConfigModel(String xml, String entity, String dao) {
		super();
		this.xml = GenConstants.PACKAGE_XML;
		this.entity = GenConstants.PACKAGE_ENTITY;
		this.dao = GenConstants.PACKAGE_DAO;
	}
	
	public ConfigModel(String init) {
		super();
		this.xml = GenConstants.PACKAGE_XML;
		this.entity = GenConstants.PACKAGE_ENTITY;
		this.dao = GenConstants.PACKAGE_DAO;
	}
	@Override
	public String toString() {
		return "ConfigModel [xml=" + xml + ", entity=" + entity + ", dao="
				+ dao + "]";
	}
	
	
}
