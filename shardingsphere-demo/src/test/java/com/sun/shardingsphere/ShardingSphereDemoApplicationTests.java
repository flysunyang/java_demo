package com.sun.shardingsphere;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.shardingsphere.bean.Course;
import com.sun.shardingsphere.bean.Dictionary;
import com.sun.shardingsphere.bean.User;
import com.sun.shardingsphere.mapper.CourseMapper;
import com.sun.shardingsphere.mapper.DictionaryMapper;
import com.sun.shardingsphere.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShardingSphereDemoApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    DictionaryMapper dictionaryMapper;

    /**
     * 主从复制测试
     */
    @Test
    void repl() {
        User user = new User();
        user.setUsername("文文");
        user.setPassword("wenwen");
        user.setRoleId(2);
        userMapper.insert(user);
    }

    @Test
    void slaveRead() {
        User user = userMapper.selectById(1278946813063225346L);
        System.out.println(user);
    }

    @Test
    void addDict() {
        Dictionary dictionary = new Dictionary();
        dictionary.setName("男");
        dictionary.setCode("man");
        int result = dictionaryMapper.insert(dictionary);
        System.out.println(result);
    }

    @Test
    void courseList() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        List<Course> courses = courseMapper.selectList(queryWrapper);
        courses.stream().forEach(System.out::println);
    }

    @Test
    void getCourse() {
        Course course = courseMapper.selectById(1278889094587445250L);
        System.out.println(course);
    }

    @Test
    void addCourse() {
        Course course = new Course();
        course.setUserId(1278870466353733633L);
        course.setCourseName("语文课");
        int result = courseMapper.insert(course);
        System.out.println(result);
    }

    @Test
    void addUser() {
        User user = new User();
        user.setUsername("朝阳");
        user.setPassword("zhaoyang");
        userMapper.insert(user);
    }

    @Test
    public void list() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> users = userMapper.selectList(queryWrapper);
        users.stream().forEach(System.out::println);
    }

}
