/**
 * 
 */
package com.mobi.cafe.UITests.models;

/**
 * @author Ahmet.Izgi
 *
 */
public class Employee {

	private String firstName;
	private String lastName;
	private String startDate;
	private String email;

	public Employee(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Employee(String firstName, String lastName, String startDate, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.startDate = startDate;
		this.email = email;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
