package com.gl.springJDBC.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import java.util.List;
import com.gl.springJDBC.entity.Student;

public class Main {
	//Spring templates class for JDBC
	static JdbcTemplate jdbcTemplateOBJ;
	//Spring class for getting database source (Driver, URL, Usernam and password)
	static SimpleDriverDataSource dataSourceOBJ;
	
	//Configuring Database
	static String username = "root";
	static String password = "12dec11nov";
	static String URL = "jdbc:mysql://localhost:3306/hibernatecrud";
	
	//Returns Spring DriverDataSource configured by User for preferred database. It is parameter for the JDBCTemplate object in main()
	public static SimpleDriverDataSource getDatabaseConnection() {
		//2. Inform the SPring Code about Database
		dataSourceOBJ = new SimpleDriverDataSource();
		try {
			//3. Setting Driver Class
			dataSourceOBJ.setDriver(new com.mysql.cj.jdbc.Driver());
			dataSourceOBJ.setUrl(URL);
			dataSourceOBJ.setUsername(username);
			dataSourceOBJ.setPassword(password);
		}catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return dataSourceOBJ;
	}
	
	public static void main(String[] args) {
		
		//1. Creating Connection
		//Instantiating jdbcTemplate object
		jdbcTemplateOBJ = new JdbcTemplate(getDatabaseConnection());
		System.out.println("The connection and Database settings are achieved: " + jdbcTemplateOBJ);
		if(null!=jdbcTemplateOBJ) {
			//4. SQL INSERT QUERY
			String sqlInsertQuery = "Insert into Student(nam,email,address,phoneno) VALUES(?,?,?,?)";
			for(int counter =1;counter<5;counter++) {
				jdbcTemplateOBJ.update(sqlInsertQuery,"Student"+counter,"Student"+counter+"@greatlearning.in","delhi","1234567890");
			}
			//5. SQL Update
			String sqlUpdateQuery = "UPDATE Student set email = ? where nam = ?";
			jdbcTemplateOBJ.update(sqlUpdateQuery,"admin@greatlearning.in","Student2");
			//6. SQL READ
			String sqlSelectQuery = "SELECT nam,email,address,phoneno FROM Student";
			List listStudent = jdbcTemplateOBJ.query(sqlSelectQuery,new RowMapper(){
				public Student mapRow(ResultSet result,int rowNum) throws SQLException{
					Student StudentObj = new Student();
					StudentObj.setId(result.getInt("id"));
					StudentObj.setName(result.getString("name"));
					StudentObj.setDepartment(result.getString("department"));
					StudentObj.setCountry(result.getString("country"));
					return StudentObj;
				}
			});
			
			//7. SQL DELETE
			String sqlDeleteQuery = "DELETE from Student where nam=?";
			jdbcTemplateOBJ.update(sqlDeleteQuery,"Student1");
			System.out.println(listStudent);
		}else {
			//8. Close the Connection
			System.out.println("Please check Connection.");
		}
	}

}
