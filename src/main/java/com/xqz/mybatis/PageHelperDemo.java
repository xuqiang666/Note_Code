package com.xqz.mybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class PageHelperDemo {

    @Autowired
    private UserMapper userMapper;  // 这里假设您有一个UserMapper接口

    @GetMapping("/list")
    public PageInfo<User> getUserList(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);
        
        // 执行查询（这里是示例，实际查询语句根据您的业务来写）
        List<User> users = userMapper.selectAll();
        
        // 封装分页信息 这里为什么PageInfo里面有分页信息？
        
        PageInfo<User> pageInfo = new PageInfo<>(users);
        
        return pageInfo;
    }
}

// 实体类示例
class User {
    private Long id;
    private String username;
    private String email;
    // getter和setter省略
}

// Mapper接口示例
interface UserMapper {
    List<User> selectAll();
} 