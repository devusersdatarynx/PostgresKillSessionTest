package com.postgreskillsession.test.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SessionDetails {
	
	@Id
	private Long process_id;
	private String database_name;
	private String application_name;
	private String state;
	
	
	public Long getProcess_id() {
		return process_id;
	}
	public void setProcess_id(Long process_id) {
		this.process_id = process_id;
	}
	public String getDatabase_name() {
		return database_name;
	}
	public void setDatabase_name(String database_name) {
		this.database_name = database_name;
	}
	public String getApplication_name() {
		return application_name;
	}
	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	

}
