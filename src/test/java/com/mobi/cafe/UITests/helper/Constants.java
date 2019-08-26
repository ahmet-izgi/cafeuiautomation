package com.mobi.cafe.UITests.helper;

public class Constants {
	// this constants also should be read from a property file.
	public final static String EMPLOYEE_CREATION_ERROR = "Error trying to create a new employee";
	public final static String STARTUP_URL = "http://cafetownsend-angular-rails.herokuapp.com";
	public final static long PAGE_LOAD_TIMEOUT = 5;
	public final static long IMPLICIT_WAIT = 10;

	public enum CssClasses {
		DISABLED("disabled");

		private String className;

		private CssClasses(String className) {
			this.className = className;
		}

		public String getClassName() {
			return className;
		}
	}

	public enum Browsers {
		CHROME, FIREFOX, IE, EDGE, PHANTOM;
	}

}
