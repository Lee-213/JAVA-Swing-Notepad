package com.notebook.ui;

import com.notebook.dao.UserDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame implements ActionListener {
    //注册窗口

    private JTextField userField;

    private JPasswordField passwordField;

    private JPasswordField confirmField;

    private JButton registerButton;

    private JButton cancelButton;

    public RegisterFrame(){
        initFrame();

        initView();

        initEvent();

        setVisible(true);
    }

    private void initEvent() {
        cancelButton.addActionListener(e ->
            dispose()
        );
            
        registerButton.addActionListener(this);
    }

    private void initView() {
        JLabel titleLabel=new JLabel("用户注册");
        titleLabel.setBounds(160,30,100,30);

        JLabel userLabel=new JLabel("用户名:");
        userLabel.setBounds(60,90,80,30);

        userField=new JTextField();
        userField.setBounds(140,90,150,30);

        JLabel passwordLabel=new JLabel("密码:");
        passwordLabel.setBounds(60,140,80,30);

        passwordField=new JPasswordField();
        passwordField.setBounds(140,140,150,30);

        JLabel confirmLabel=new JLabel("确认密码:");
        confirmLabel.setBounds(60,190,80,30);

        confirmField=new JPasswordField();
        confirmField.setBounds(140,190,150,30);

        registerButton=new JButton("注册");
        registerButton.setBounds(80,260,100,35);

        cancelButton=new JButton("取消");
        cancelButton.setBounds(210,260,100,35);

        add(titleLabel);
        add(userLabel);
        add(userField);
        add(passwordLabel);
        add(passwordField);
        add(confirmLabel);
        add(confirmField);
        add(registerButton);
        add(cancelButton);
    }

    private void initFrame() {
        setTitle("用户注册");

        setSize(400,350);

        setLocationRelativeTo(null);

        setLayout(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            register();
        }
    }

    private void register() {
        String username = userField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmField.getPassword());

        // 验证输入
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "所有字段不能为空！", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 验证用户名长度
        if (username.length() < 3) {
            JOptionPane.showMessageDialog(this, "用户名长度不能少于3个字符！", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 验证密码长度
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(this, "密码长度不能少于6个字符！", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 验证两次密码是否一致
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "两次输入的密码不一致！", "错误", JOptionPane.ERROR_MESSAGE);
            confirmField.setText("");
            return;
        }

        // 调用UserDao进行注册
        UserDao userDao = new UserDao();
        
        // 检查用户名是否已存在
        if (userDao.isUsernameExists(username)) {
            JOptionPane.showMessageDialog(this, "用户名已存在，请使用其他用户名！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 执行注册
        boolean success = userDao.register(username, password);
        if (success) {
            JOptionPane.showMessageDialog(this, "注册成功！请登录", "成功", JOptionPane.INFORMATION_MESSAGE);
            // 关闭注册窗口
            dispose();
            // 打开登录窗口
            new LoginFrame();
        } else {
            JOptionPane.showMessageDialog(this, "注册失败，请稍后重试！", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

}
