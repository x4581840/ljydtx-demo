package com.demo.service;

import com.demo.dynamic_datasource.datasource.TargetDataSource;
import com.demo.mapper.StudentMapper;
import com.demo.mapper.impl.StudentImplMapper;
import com.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    TransactionDefinition transactionDefinition;

    public Student getStudentById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    public Student getStudentById1(Integer id) {
        return studentMapper.selectById(id);
    }

    @org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void insertStudent() {
        // test(); 这样调用test回滚不生效
        Student record = new Student();
        record.setName("ss");
        record.setId(2999);
        studentMapper.insert(record);
        record = new Student();
        record.setName("ss");
        record.setId(2999);
        studentMapper.insert(record);
    }

    public void insertStudent1(){
        //手动开启事务
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {
            Student record = new Student();
            record.setName("ss");
            record.setId(2999);
            studentMapper.insert(record);
            record = new Student();
            record.setName("ss");
            record.setId(2990);
            studentMapper.insert(record);
            //手动提交事务
            dataSourceTransactionManager.commit(transactionStatus);//提交
        }catch (Exception e) {
            //手动回滚事务
            dataSourceTransactionManager.rollback(transactionStatus);//最好是放在catch 里面,防止程序异常而事务一直卡在哪里未提交
        }
    }

    @org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void test() {
        Student record = new Student();
        record.setName("ss");
        record.setId(2999);
        studentMapper.insert(record);
        record = new Student();
        record.setName("ss");
        record.setId(2999);
        studentMapper.insert(record);
    }

    public List<Student> getStudentList(Date startDate, Date endDate) {
        return studentImplMapper.getStudentList(startDate, endDate);
    }

    @TargetDataSource(name = "ds2")
    public List<Student> likeName(String name) {
        return studentImplMapper.likeName(name);
    }

    public List<Student> likeNameByDefaultDataSource(String name) {
        return studentImplMapper.likeName(name);
    }

    public void updateCount() {
        studentImplMapper.updateCount();
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
