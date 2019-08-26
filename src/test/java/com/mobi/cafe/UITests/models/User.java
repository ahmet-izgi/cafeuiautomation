/**
 * 
 */
package com.mobi.cafe.UITests.models;

/**
 * @author Ahmet.Izgi
 *
 */
public class User {
	private String username;
	private String password;

	public static User getDefaultCorrectUser() {
		return new User("Luke", "Skywalker");
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
