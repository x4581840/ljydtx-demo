package com.demo.service;

import com.demo.dynamic_datasource.datasource.TargetDataSource;
import com.demo.mapper.StudentMapper;
import com.demo.mapper.impl.StudentImplMapper;
import com.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/5/27 11:27 AM
 * @Version 1.0
 **/

@Service
public class StudentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // MyBatis的Mapper方法定义接口
    @Autowired
    private StudentImplMapper studentImplMapper;

    @TargetDataSource(name = "ds2")
    public List<Student> likeName(String name) {
        return studentImplMapper.likeName(name);
    }

    public List<Student> likeNameByDefaultDataSource(String name) {
        return studentImplMapper.likeName(name);
    }

    /**
     * 不指定数据源使用默认数据源
     *
     * @return
     * @author SHANHY
     * @create 2016年1月24日
     */
    public List<Student> getList() {
        String sql = "SELECT ID,NAME,SCORE_SUM,SCORE_AVG, AGE   FROM STUDENT";
        return (List<Student>) jdbcTemplate.query(sql, new RowMapper<Student>() {

            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student stu = new Student();
                stu.setId(rs.getInt("ID"));
                stu.setAge(rs.getInt("AGE"));
                stu.setName(rs.getString("NAME"));
                stu.setScoreSum(rs.getString("SCORE_SUM"));
                stu.setScoreAvg(rs.getString("SCORE_AVG"));
                return stu;
            }

        });
    }

    /**
     * 指定数据源
     *
     * @return
     * @author SHANHY
     * @create 2016年1月24日
     */
    @TargetDataSource(name = "ds1")
    public List<Student> getListByDs1() {
        String sql = "SELECT ID,NAME,SCORE_SUM,SCORE_AVG, AGE   FROM STUDENT";
        return (List<Student>) jdbcTemplate.query(sql, new RowMapper<Student>() {

            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student stu = new Student();
                stu.setId(rs.getInt("ID"));
                stu.setAge(rs.getInt("AGE"));
                stu.setName(rs.getString("NAME"));
                stu.setScoreSum(rs.getString("SCORE_SUM"));
                stu.setScoreAvg(rs.getString("SCORE_AVG"));
                return stu;
            }

        });
    }

    /**
     * 指定数据源
     *
     * @return
     * @author SHANHY
     * @create 2016年1月24日
     */
    @TargetDataSource(name = "ds2")
    public List<Student> getListByDs2() {
        String sql = "SELECT ID,NAME,SCORE_SUM,SCORE_AVG, AGE   FROM STUDENT";
        return (List<Student>) jdbcTemplate.query(sql, new RowMapper<Student>() {

            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student stu = new Student();
                stu.setId(rs.getInt("ID"));
                stu.setAge(rs.getInt("AGE"));
                stu.setName(rs.getString("NAME"));
                stu.setScoreSum(rs.getString("SCORE_SUM"));
                stu.setScoreAvg(rs.getString("SCORE_AVG"));
                return stu;
            }

        });
    }

    /**
     * 先test1查询，再查询test2 数据源切换有问题，查的都是test2的，test1的数据源失效
     *
     * @return
     * @author SHANHY
     * @create 2016年1月24日
     */
    @TargetDataSource(name = "ds2")
    // @Transactional
    public Map<String, List<Student>> getAndGet() {
        String sql = "SELECT ID,NAME,SCORE_SUM,SCORE_AVG,AGE  FROM STUDENT";
        List<Student> list1 = getListByDs1();
        List<Student> list2 = (List<Student>) jdbcTemplate.query(sql, new RowMapper<Student>() {

            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student stu = new Student();
                stu.setId(rs.getInt("ID"));
                stu.setAge(rs.getInt("AGE"));
                stu.setName(rs.getString("NAME"));
                stu.setScoreSum(rs.getString("SCORE_SUM"));
                stu.setScoreAvg(rs.getString("SCORE_AVG"));
                return stu;
            }

        });
        Map<String, List<Student>> res = new HashMap<>();
        res.put("ds1", list1);
        res.put("ds2", list2);
        return res;
    }

}
