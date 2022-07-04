package com.greatlearning.springJDBC.configuration;

public class SpringJDBCcrud {
	private String URL = "jdbc:mysql://localhost:3306/SpringJdbc";
	private String USERNAME = "root";
	private String PASSWORD = "12dec11nov";

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

}
