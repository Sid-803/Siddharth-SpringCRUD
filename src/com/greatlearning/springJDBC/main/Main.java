package com.greatlearning.springJDBC.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.greatlearning.springJDBC.configuration.SpringJDBCcrud;
import com.greatlearning.springJDBC.entity.Student;
import com.mysql.cj.protocol.Resultset;

public class Main {
	// 1. Create a Connection
	public static JdbcTemplate jdbcTemplate;
	// 2. Configuration object
	static SimpleDriverDataSource dataSource;
	// 3. Configuration details
	static SpringJDBCcrud configure = new SpringJDBCcrud();

	// Method to create configuration
	public static SimpleDriverDataSource getConnection() {
		dataSource = new SimpleDriverDataSource();
		try {
			dataSource.setDriver(new com.mysql.cj.jdbc.Driver());
			dataSource.setUrl(configure.getURL());
			dataSource.setUsername(configure.getUSERNAME());
			dataSource.setPassword(configure.getPASSWORD());
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return dataSource;
	}

	public static void SQLInsert(int id, String name, String email, String address, String phoneNo) {
		jdbcTemplate = new JdbcTemplate(getConnection());
		String SQLInsertQuery = "Insert INTO Student(id,name,email,address,phoneNo) VALUES(?,?,?,?,?)";
		jdbcTemplate.update(SQLInsertQuery, id, name, email, address, phoneNo);
		System.out.println("Successfully inserted into SpringJdbc-Student");
	}

	public static void SQLUpdate(String set, int at) {
		jdbcTemplate = new JdbcTemplate(getConnection());
		String SQLUpdateQuery = "UPDATE Student set email=? where id=?";
		jdbcTemplate.update(SQLUpdateQuery, set, at);
	}

	public static void SQLRead() {
		jdbcTemplate = new JdbcTemplate(getConnection());
		String SQLReadQuery = "Select id,name,email,address,phoneNo from Student";
		List ListUpdate = jdbcTemplate.query(SQLReadQuery, new RowMapper() {
			@Override
			public Student mapRow(ResultSet result, int rowNum) throws SQLException {
				Student student = new Student();
				student.setId(result.getInt("id"));
				student.setName(result.getString("name"));
				student.setEmail(result.getString("email"));
				student.setAddress(result.getString("address"));
				student.setPhoneNo(result.getString("phoneNo"));
				return student;
			}
		});
		System.out.println(ListUpdate);
	}

	public static void main(String[] args) {
		
		//SQLInsert(6,"Dhanush","Dhanush@greatlearning.in","Bhopal", "6778889818");
		//SQLRead();
		//SQLUpdate("D@gmail.com",6);
		//SQLDelete to be added(WIP).
	}

}
