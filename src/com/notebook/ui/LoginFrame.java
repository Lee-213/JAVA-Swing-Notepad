package com.notebook.ui;

import com.notebook.dao.UserDao;
import com.notebook.entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {
    //登录窗口
    //用户名输入框
    private JTextField userField;
    //密码输入框
    private JPasswordField passwordField;
    //登录按钮
    private JButton loginButton;
    //注册按钮
    private JButton registerButton;

    public LoginFrame(){
        initFrame();

        initView();
        
        initEvent();
        
        //设置窗口可见
        setVisible(true);
    }

    private void initView() {
        //标题
        JLabel titleLabel=new JLabel("用户登录");
        titleLabel.setBounds(160,30,100,30);

        //用户名
        JLabel userLabel=new JLabel("用户名:");
        userLabel.setBounds(70,90,60,30);

        userField=new JTextField();
        userField.setBounds(140,90,150,30);

        //密码
        JLabel passwordLabel=new JLabel("密码:");
        passwordLabel.setBounds(70,140,150,30);

        passwordField=new JPasswordField();
        passwordField.setBounds(140,140,150,30);

        //登录按钮
        loginButton=new JButton("登录");
        loginButton.setBounds(80,210,100,35);

        //注册按钮
        registerButton=new JButton("注册");
        registerButton.setBounds(210,210,100,35);

        //添加组件到窗口
        add(titleLabel);
        add(userLabel);
        add(userField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(registerButton);
    }

    private void initEvent() {
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            login();
        } else if (e.getSource() == registerButton) {
            openRegisterFrame();
        }
    }

    private void login() {
        String username = userField.getText().trim();
        String password = new String(passwordField.getPassword());
        // 验证输入
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "用户名和密码不能为空！", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // 调用UserDao进行登录验证
        UserDao userDao = new UserDao();
        User user = userDao.login(username, password);
        if (user != null) {
            JOptionPane.showMessageDialog(this, "登录成功！欢迎 " + user.getUserName(), "成功", JOptionPane.INFORMATION_MESSAGE);
            // 打开主窗口，传递用户信息
            new MainFrame(user);
            // 关闭登录窗口
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "用户名或密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
            // 清空密码框
            passwordField.setText("");
        }
    }

    private void openRegisterFrame() {
        // 打开注册窗口
        new RegisterFrame();
        // 关闭登录窗口
        dispose();
    }

    public void initFrame(){
        setTitle("登录窗口");

        setSize(400,300);

        //设置窗口居中显示
        setLocationRelativeTo(null);

        //设置窗口关闭时的操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);
    }

}
