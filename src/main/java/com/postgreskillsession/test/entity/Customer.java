package com.postgreskillsession.test.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "insert_customer", procedureName = "insert_customer", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "firstname", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "lastname", type = String.class)}),
	@NamedStoredProcedureQuery(name = "getAllCustomer", procedureName = "select_customer", resultClasses = Customer.class),
	@NamedStoredProcedureQuery(name = "update_customer", procedureName = "update_customer", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "firstname", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "lastname", type = String.class)})
})
public class Customer implements Serializable {
	
private static final long serialVersionUID = -2343243243242432341L;

@Id
private int id;

@Column(name = "firstname")
private String firstName;

@Column(name = "lastname")
private String lastName;

public Customer() {
	super();
}

public Customer(String firstName, String lastName) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
}

public Customer(int id, String firstName, String lastName) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

}