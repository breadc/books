package com.model;

public class GenertorModel {
	
	private String  target;
	private ConfigModel model;
	

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	public ConfigModel getModel() {
		return model;
	}

	public void setModel(ConfigModel model) {
		this.model = model;
	}

	public  String  getProjectTarger(){
		String   project_url="";
		
		return target+project_url;
	}

	@Override
	public String toString() {
		return "GenertorModel [target=" + target + ", model=" + model.toString() + "]";
	}
	
	
}
