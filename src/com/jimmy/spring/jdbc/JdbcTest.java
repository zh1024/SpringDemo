package com.jimmy.spring.jdbc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class JdbcTest {

	private ApplicationContext ctx;
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	{
		ctx = new ClassPathXmlApplicationContext("beans-jdbc.xml");
		jdbcTemplate = (NamedParameterJdbcTemplate) ctx.getBean("jdbcTemplate");
	}
	
	@Test
	public void testQueryForObject2(){
		String sql = "SELECT MAX(emp_no) FROM employees";
		Integer max  = jdbcTemplate.getJdbcOperations().queryForObject(sql, Integer.class);
		System.out.println(max);
	}
	
	@Test
	public void testQueryForList(){
		String sql = "SELECT emp_no as empNo, birth_date as birthDate, first_name as firstName, last_name as lastName, gender as gender, hire_date as hireDate "
				+ "FROM employees WHERE emp_no >= :empNo";
		Employee employee = new Employee();
		employee.setEmpNo(500000);
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(employee);
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		List<Employee> employees = jdbcTemplate.query(sql, parameterSource, rowMapper);
		System.out.println(employees);
	}
	
	@Test
	public void testQueryForObject(){
		String sql = "SELECT emp_no as empNo, birth_date as birthDate, first_name as firstName, last_name as lastName, gender as gender, hire_date as hireDate "
				+ "FROM employees WHERE emp_no = :empNo";
		Employee employee = new Employee();
		employee.setEmpNo(10001);
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(employee);
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		employee = jdbcTemplate.queryForObject(sql, parameterSource, rowMapper);
		System.out.println(employee);
	}
	
	@Test
	public void testBatchUpdate() throws ParseException{
		String sql = "INSERT INTO employees(emp_no, birth_date, first_name, last_name, gender, hire_date) "
				+ "VALUES (:empNo, :birthDate, :firstName, :lastName, :gender, :hireDate)";
		SqlParameterSource ps1 = new BeanPropertySqlParameterSource(new Employee(500000, (new SimpleDateFormat("yyyy-MM-dd")).parse("1962-11-10"), "aa", "AA", "M", (new SimpleDateFormat("yyyy-MM-dd")).parse("1992-11-10")));
		SqlParameterSource ps2 = new BeanPropertySqlParameterSource(new Employee(500001, (new SimpleDateFormat("yyyy-MM-dd")).parse("1962-11-11"), "bb", "BB", "F", (new SimpleDateFormat("yyyy-MM-dd")).parse("1992-11-11")));
		SqlParameterSource ps3 = new BeanPropertySqlParameterSource(new Employee(500002, (new SimpleDateFormat("yyyy-MM-dd")).parse("1962-11-12"), "cc", "CC", "M", (new SimpleDateFormat("yyyy-MM-dd")).parse("1992-11-12")));
		SqlParameterSource[] batchArgs = {ps1, ps2, ps3};
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	
	@Test
	public void testUpdate(){
		Employee employee = new Employee();
		employee.setEmpNo(10001);
		employee.setFirstName("Jimmy");
	    
		String sql = "UPDATE employees SET first_name = :firstName WHERE EMP_NO = :empNo";
		SqlParameterSource ps = new BeanPropertySqlParameterSource(employee);
		jdbcTemplate.update(sql, ps);
	}

	@Test
	public void test() {
		System.out.println(jdbcTemplate);
	}
}
