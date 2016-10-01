package com.jimmy.spring.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/*使用Spring的NamedParameterJdbcTemplate完成DAO操作 
NamedParameterJdbcTemplate内部包含了一个JdbcTemplate，所以JdbcTemplate能做的事情NamedParameterJdbcTemplate都能干，NamedParameterJdbcTemplate相对于JdbcTemplate主要增加了参数可以命名的功能。 

public Object queryForObject(String sql, Map paramMap, RowMapper rowMapper) 

public Object queryForObject(String sql, SqlParameterSource paramSource, RowMapper rowMapper) 

       SqlParameterSource的两个主要实现MapSqlParameterSource 

       和BeanPropertySqlParameterSource 

public int update(String sql, SqlParameterSource paramSource, KeyHolder generatedKeyHolder)保存数据获得主键。 

实例说明： */

/** 

* 一、NamedParameterJdbcTemplate提供了命名参数，用:x代替了? 

* NamedParameterJdbcTemplate传参数可以用map或者SqlParameterSource 

* 用map的好处是:x,x的值可以自由取，但是所有参数的值都必须要放到map中去，其中的key为:后面的名称，value是你传的值 

* 用SqlParameterSource的好处是不用一个个的赋值,但是:x中的x的值必须和对象的属性名称一样 



* 二、使用KeyHolder keyholder=new GeneratedKeyHolder(); 

   namedjdbctemp.update(sql, ps,keyholder);这个参数可以获得主键生成值 

*/ 

public class StuDaoImple{ 

    private NamedParameterJdbcTemplate namedjdbctemp; 



    public StuDaoImple(){ 

//       namedjdbctemp=new NamedParameterJdbcTemplate(SQLConnUtil.getDataSource()); 

    }// SQLConnUtil为JDBC工具类，提供了得到数据源的静态方法 

    /* 

     * 这里会用到NamedParameterJdbcTemplate两个好处： 

     * 1，不用一个个的为参数赋值。 

     * 2，可以轻易的得到主键自动增长值 

     */ 

    public void addStu(Stu stu) { 

       String sql="insert into stu values(:sname,:ssex,:sbrith)"; 

       //:后面的名称必须和stu属性名称一样 

       SqlParameterSource ps=new BeanPropertySqlParameterSource(stu); 

       KeyHolder keyholder=new GeneratedKeyHolder(); 

       namedjdbctemp.update(sql, ps,keyholder); 

       //加上KeyHolder这个参数可以得到添加后主键的值 

       int m=keyholder.getKey().intValue(); 

       System.out.println(m); 

       //Map map=keyholder.getKeys();//这样可以得到联合主键的值 

       //keyholder.getKeyList();//这样可以得到一些主主键值，若一次添加好几条记录 

    } 

    public Long count() { 

       String sql="select count(*) from stu";

       //可以通过NamedParameterJdbcTemplate得到JdbcTemplate 

       Long m = namedjdbctemp.getJdbcOperations().queryForObject(sql, Long.class);

       return m; 

    } 



    /* 

     * 这里会用到NamedParameterJdbcTemplate另一个好处： 

     * 位置参数 

     */ 

    public void delStu(int sid) { 

       String sql="delete stu where s_id=:id"; 

       Map map=new HashMap(); 

       map.put("id", sid); 

       namedjdbctemp.update(sql, map); 

    } 

    public List getAllStu() { 

       String sql="select s_id as sid,s_name as sname,s_sex as ssex,s_brith as sbrith from stu"; 

       List list=namedjdbctemp.getJdbcOperations().query(sql,new BeanPropertyRowMapper(Stu.class)); 

       return list; 

    } 

    public List getAllStu(Stu stu) { 

       String sql="select s_id as sid,s_name as sname,s_sex as ssex,s_brith as sbrith from stu where s_id=:sid"; 

       SqlParameterSource ps=new BeanPropertySqlParameterSource(stu); 

       return namedjdbctemp.query(sql, ps, new BeanPropertyRowMapper(Stu.class)); 

    } 

    public Stu getOneStu(Stu stu) { 

       String sql="select s_id as sid,s_name as sname,s_sex as ssex,s_brith as sbrith from stu where s_id=:sid"; 

       SqlParameterSource ps=new BeanPropertySqlParameterSource(stu); 

       return (Stu)namedjdbctemp.queryForObject(sql, ps, new BeanPropertyRowMapper(Stu.class)); 

    } 

    public String getStuName(Stu stu) { 

       String sql="select s_name as sname from stu where s_name=:sname"; 

       SqlParameterSource ps=new BeanPropertySqlParameterSource(stu); 

       return (String)namedjdbctemp.queryForObject(sql, ps, String.class); 

    } 

    public void updStu(Stu stu) { 

       String sql="update stu set s_name=:sname,s_sex=:ssex,s_brith=:sbrith where s_id=:sid"; 

       SqlParameterSource ps=new BeanPropertySqlParameterSource(stu); 

       namedjdbctemp.update(sql, ps);

    } 

}