package com.notebook.dao;

import com.notebook.entity.User;
import com.notebook.util.FileUtil;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDao {
    //数据访问层
    private static final String USER_FILE = "data/users.txt";

    //用户登录验证
    public User login(String username, String password) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // 用户注册

    public boolean register(String username, String password) {
        // 检查用户名是否已存在
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return false; // 用户名已存在
            }
        }

        // 创建新用户
        String id = String.valueOf(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String registerTime = sdf.format(new Date());
        User newUser = new User(id, username, password, registerTime);

        // 保存到文件
        users.add(newUser);
        return saveAllUsers(users);
    }

    //检查用户名是否存在

    public boolean isUsernameExists(String username) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    // 获取所有用户

    private List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(USER_FILE);
        
        if (!file.exists()) {
            return users;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    User user = new User(parts[0].trim(), parts[1].trim(), 
                                       parts[2].trim(), parts[3].trim());
                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return users;
    }

    //保存所有用户到文件

    private boolean saveAllUsers(List<User> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (User user : users) {
                bw.write(user.toString());
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
