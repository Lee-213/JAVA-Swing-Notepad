package com.notebook.ui;

import com.notebook.entity.User;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class MainFrame extends JFrame {
    // 主窗口
    //文本编辑区
    private JTextArea textArea;

    //状态栏
    private JLabel statusLabel1;

    private File currentFile;

    private JMenuItem newItem;
    private JMenuItem openItem;
    private JMenuItem saveItem;
    private JMenuItem saveAsItem;
    private JMenuItem exitItem;

    private JMenuItem copyItem;
    private JMenuItem cutItem;
    private JMenuItem pasteItem;

    private JMenuItem searchItem;
    private JMenuItem replaceItem;

    private JMenuItem siezMenu;
    private JMenuItem size12Item;
    private JMenuItem size16Item;
    private JMenuItem size20Item;
    private JMenuItem size24Item;
    private JMenuItem colorItem;

    private JMenuItem wordCountItem;
    private JMenuItem timeItem;

    private JMenu fontMenu;

    private JMenuItem songItem;
    private JMenuItem heiItem;
    private JMenuItem kaiItem;
    private JMenuItem yaheiItem;

    // 新增菜单项
    private JMenuItem aboutItem;
    private JMenuItem currentUserItem;
    private JMenuItem logoutItem;
    private JMenuItem toLowerCaseItem;
    private JMenuItem toUpperCaseItem;
    private JMenuItem deleteBlankLinesItem;

    // 当前登录用户
    private User currentUser;

    public MainFrame() {
        // 初始化主窗口
        initFrame();
        // 初始化菜单栏
        initMenu();
        // 初始化文本编辑区
        initTextArea();
        // 初始化状态栏
        initStatusBar();
        // 初始化事件
        initEvent();
        // 设置窗口可见
        setVisible(true);
    }

    // 带用户信息的构造函数
    public MainFrame(User user) {
        this.currentUser = user;
        initFrame();
        initMenu();
        initTextArea();
        initStatusBar();
        initEvent();
        setVisible(true);
    }


    //插入时间方法
    private void insertTime() {

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "yyyy-MM-dd HH:mm:ss");

        String time = now.format(formatter);

        textArea.insert(
                time,
                textArea.getCaretPosition()
        );

    }

    //替换方法
    private void replaceText() {
        String oldText = JOptionPane.showInputDialog(this, "请输入要替换的内容");
        if (oldText == null || oldText.isEmpty()) {
            return;
        }
        String newText = JOptionPane.showInputDialog(this, "请输入替换内容");
        if (newText == null) {
            return;
        }
        String content = textArea.getText();
        content = content.replaceAll(oldText, newText);
        textArea.setText(content);
        statusLabel1.setText("替换完成");
    }

    //查找方法
    private void searchText() {
        String keyword = JOptionPane.showInputDialog(this, "请输入查找内容");

        if (keyword == null || keyword.isEmpty()) {
            return;
        }
        String content = textArea.getText();

        int index = content.indexOf(keyword);
        if (index != -1) {
            textArea.requestFocus();
            textArea.select(index, index + keyword.length());
        } else {
            JOptionPane.showMessageDialog(this, "未找到查找内容");
        }

    }


    // 初始化事件
    private void initEvent() {
        //退出事件
        exitItem.addActionListener(e -> {
            System.exit(0);
        });

        // 新建事件
        newItem.addActionListener(e -> {
            textArea.setText("");
        });

        // 打开事件
        openItem.addActionListener(e -> {
            // 打开文件
            openFile();
        });
        saveItem.addActionListener(e -> {
            // 保存文件
            saveFile();
        });

        // 另存为事件
        saveAsItem.addActionListener(e -> {
            // 另存为文件
            saveAsFile();
        });

        //复制事件
        copyItem.addActionListener(e -> {
            textArea.copy();
        });

        //剪切事件
        cutItem.addActionListener(e -> {
            textArea.cut();
        });

        //粘贴事件
        pasteItem.addActionListener(e -> {
            textArea.paste();
        });

        //查找事件
        searchItem.addActionListener(e -> {
            searchText();
        });

        //替换事件
        replaceItem.addActionListener(e -> {
            replaceText();
        });

        //统计事件
        wordCountItem.addActionListener(e -> {
            countWords();
        });

        //插入时间事件
        timeItem.addActionListener(e -> {
            insertTime();
        });

        //更改字体大小事件
        size12Item.addActionListener(e -> setFontSize(12));
        size16Item.addActionListener(e -> setFontSize(16));
        size20Item.addActionListener(e -> setFontSize(20));
        size24Item.addActionListener(e -> setFontSize(24));

        //更改字体颜色事件
        colorItem.addActionListener(e -> {
            setFontColor();
        });
        songItem.addActionListener(
                e -> setFontFamily("宋体")
        );

        heiItem.addActionListener(
                e -> setFontFamily("黑体")
        );

        kaiItem.addActionListener(
                e -> setFontFamily("楷体")
        );

        yaheiItem.addActionListener(
                e -> setFontFamily("微软雅黑")
        );

        // 文本处理事件
        toLowerCaseItem.addActionListener(e -> {
            convertToLowerCase();
        });

        toUpperCaseItem.addActionListener(e -> {
            convertToUpperCase();
        });

        deleteBlankLinesItem.addActionListener(e -> {
            deleteBlankLines();
        });

        // 帮助菜单事件
        aboutItem.addActionListener(e -> {
            showAbout();
        });

        // 用户菜单事件
        currentUserItem.addActionListener(e -> {
            showCurrentUser();
        });

        logoutItem.addActionListener(e -> {
            logout();
        });
    }

    private void setFontFamily(String fontName) {
        Font oldFont = textArea.getFont();

        Font newFont =
                new Font(
                        fontName,
                        oldFont.getStyle(),
                        oldFont.getSize()
                );

        textArea.setFont(newFont);
    }

    //更改字体颜色方法
    private void setFontColor() {
        Color color = JColorChooser.showDialog(this, "选择字体颜色", Color.BLACK);

        if (color != null) {
            textArea.setForeground(color);
        }
    }

    //更改字体大小方法
    private void setFontSize(int fontSize) {
        textArea.setFont(new Font("微软雅黑", Font.PLAIN, fontSize));
    }


    //字数统计方法
    private void countWords() {
        String content = textArea.getText();
        if (content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "文本为空，无内容");
            return;
        }
        //字符计数器
        int chineseCount = 0;
        int englishCount = 0;
        int digitCount = 0;
        int symbolCount = 0;
        int spaceCount = 0;

        for (int i = 0; i < content.length(); i++) {
            char ch = content.charAt(i);
            if (ch >= '\u4e00' && ch <= '\u9fa5') {
                chineseCount++;
            } else if (Character.isLetter(ch)) {
                englishCount++;
            } else if (Character.isDigit(ch)) {
                digitCount++;
            } else if (Character.isWhitespace(ch)) {
                spaceCount++;
            } else {
                symbolCount++;
            }
        }
        JOptionPane.showMessageDialog(
                this,
                "中文字符：" + chineseCount +
                        "\n英文字母：" + englishCount +
                        "\n数字字符：" + digitCount +
                        "\n符号数量：" + symbolCount +
                        "\n空白字符：" + spaceCount +
                        "\n总字符数：" + content.length() +
                        "\n总行数：" + textArea.getLineCount()
        );
    }

    // 大写转小写
    private void convertToLowerCase() {
        String content = textArea.getText();
        if (content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "文本为空！", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }
        textArea.setText(content.toLowerCase());
        statusLabel1.setText("已转换为小写");
    }

    // 小写转大写
    private void convertToUpperCase() {
        String content = textArea.getText();
        if (content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "文本为空！", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }
        textArea.setText(content.toUpperCase());
        statusLabel1.setText("已转换为大写");
    }

    // 删除空白行
    private void deleteBlankLines() {
        String content = textArea.getText();
        if (content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "文本为空！", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String[] lines = content.split("\n");
        StringBuilder sb = new StringBuilder();
        int deletedCount = 0;
        
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                sb.append(line).append("\n");
            } else {
                deletedCount++;
            }
        }
        
        textArea.setText(sb.toString());
        statusLabel1.setText("已删除 " + deletedCount + " 个空白行");
    }

    // 显示关于系统
    private void showAbout() {
        JOptionPane.showMessageDialog(
                this,
                "智能记事本系统\n版本：1.0\n开发工具：Java Swing"
        );
    }

    // 显示当前用户
    private void showCurrentUser() {
        if (currentUser != null) {
            JOptionPane.showMessageDialog(this,
                    "当前登录用户：\n" +
                    "用户名：" + currentUser.getUserName() + "\n" +
                    "注册时间：" + currentUser.getRegisterTime(),
                    "当前用户",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "未登录",
                    "提示",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // 退出登录
    private void logout() {
        int result = JOptionPane.showConfirmDialog(this,
                "确定要退出登录吗？",
                "确认",
                JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            dispose(); // 关闭主窗口
            new LoginFrame(); // 打开登录窗口
        }
    }

    // 另存为文件事件
    private void saveAsFile() {
        JFileChooser fileChooser = new JFileChooser();

        // 显示文件选择器
        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            String path = currentFile.getAbsolutePath();
            if (!path.endsWith(".txt")) {
                currentFile = new File(path + ".txt");
            }
            try {
                BufferedWriter bw =
                        new BufferedWriter(
                                new FileWriter(currentFile));
                bw.write(textArea.getText());
                bw.close();
                JOptionPane.showMessageDialog(this, "保存成功");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "保存失败");
            }
        }
    }

    // 保存文件事件
    private void saveFile() {
        if (currentFile == null) {
            JOptionPane.showMessageDialog(this, "请先打开文件");
            return;
        }
        String path = currentFile.getAbsolutePath();
        if (!path.endsWith(".txt")) {
            currentFile = new File(path + ".txt");
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(currentFile));
            bw.write(textArea.getText());
            bw.close();

            JOptionPane.showMessageDialog(this, "保存成功");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "保存失败");
        }

    }

    //打开文件事件
    private void openFile() {
        //创建文件选择器
        JFileChooser fileChooser = new JFileChooser();
        //显示文件选择器
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File fIle = fileChooser.getSelectedFile();
        }
        currentFile = fileChooser.getSelectedFile();
        //异常处理
        try {
            BufferedReader br = new BufferedReader(new FileReader(currentFile));

            StringBuilder sb = new StringBuilder();
            String line;

            while (((line = br.readLine()) != null)) {
                sb.append(line).append("\n");
            }
            br.close();
            textArea.setText(sb.toString());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "打开文件失败");
        }


    }

    //初始化状态栏
    private void initStatusBar() {
        // 初始化状态栏
        statusLabel1 = new JLabel("字数0 行数1");
        add(statusLabel1, BorderLayout.SOUTH);
    }

    //初始化文本编辑区
    private void initTextArea() {

        textArea = new JTextArea();
        // 设置文本编辑区的字体
        textArea.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        // 创建滚动面板
        JScrollPane scrollpane = new JScrollPane(textArea);
        // 添加滚动面板到窗口
        add(scrollpane, BorderLayout.CENTER);

        // 监听文本变化，实时更新状态栏
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateStatus();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateStatus();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateStatus();
            }
        });
    }

    private void updateStatus() {
        int charCount = textArea.getText().length();
        int lineCount = textArea.getLineCount();
        statusLabel1.setText("字数" + charCount + " 行数" + lineCount);
    }

    //初始化菜单栏
    private void initMenu() {
        // 初始化菜单栏
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("文件");
        JMenu editMenu = new JMenu("编辑");
        JMenu formatMenu = new JMenu("格式");
        JMenu toolMenu = new JMenu("工具");
        JMenu helpMenu = new JMenu("帮助");

        // 文件菜单
        newItem = new JMenuItem("新建");
        openItem = new JMenuItem("打开");
        saveItem = new JMenuItem("保存");
        saveAsItem = new JMenuItem("另存为");
        exitItem = new JMenuItem("退出");

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        // 添加分隔线
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // 编辑菜单
        copyItem = new JMenuItem("复制");
        cutItem = new JMenuItem("剪切");
        pasteItem = new JMenuItem("粘贴");

        editMenu.add(copyItem);
        editMenu.add(cutItem);
        editMenu.add(pasteItem);

        editMenu.addSeparator();

        searchItem = new JMenuItem("查找");
        replaceItem = new JMenuItem("替换");

        editMenu.add(searchItem);
        editMenu.add(replaceItem);

        // 格式菜单
        fontMenu = new JMenu("字体设置");
        colorItem=new JMenuItem("字体颜色");
        siezMenu = new JMenu("字体大小");
        size12Item = new JMenuItem("12");
        size16Item = new JMenuItem("16");
        size20Item = new JMenuItem("20");
        size24Item = new JMenuItem("24");
        siezMenu.add(size12Item);
        siezMenu.add(size16Item);
        siezMenu.add(size20Item);
        siezMenu.add(size24Item);
        fontMenu = new JMenu("字体");

        songItem = new JMenuItem("宋体");
        heiItem = new JMenuItem("黑体");
        kaiItem = new JMenuItem("楷体");
        yaheiItem = new JMenuItem("微软雅黑");

        fontMenu.add(songItem);
        fontMenu.add(heiItem);
        fontMenu.add(kaiItem);
        fontMenu.add(yaheiItem);

        formatMenu.add(fontMenu);


        formatMenu.add(siezMenu);
        formatMenu.add(colorItem);

        // 工具菜单
        wordCountItem = new JMenuItem("文本统计");
        timeItem = new JMenuItem("插入时间");

        toolMenu.add(wordCountItem);
        toolMenu.add(timeItem);

        // 文本处理扩展菜单
        JMenu textProcessMenu = new JMenu("文本处理");
        toLowerCaseItem = new JMenuItem("大写转小写");
        toUpperCaseItem = new JMenuItem("小写转大写");
        deleteBlankLinesItem = new JMenuItem("删除空白行");
        
        textProcessMenu.add(toLowerCaseItem);
        textProcessMenu.add(toUpperCaseItem);
        textProcessMenu.add(deleteBlankLinesItem);
        toolMenu.add(textProcessMenu);

        // 用户菜单
        JMenu userMenu = new JMenu("用户");
        currentUserItem = new JMenuItem("当前用户");
        logoutItem = new JMenuItem("退出登录");
        
        userMenu.add(currentUserItem);
        userMenu.addSeparator();
        userMenu.add(logoutItem);

        //帮助菜单
        aboutItem = new JMenuItem("关于系统");
        
        helpMenu.add(aboutItem);


        // 添加一级菜单
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
        menuBar.add(toolMenu);
        menuBar.add(userMenu);
        menuBar.add(helpMenu);

        // 设置菜单栏为窗口的菜单栏
        setJMenuBar(menuBar);
    }

    //初始化主窗口
    private void initFrame() {
        // 设置窗口标题
        setTitle("记事本");
        //设置窗口大小
        setSize(800, 600);
        // 设置窗口位置
        setLocationRelativeTo(null);
        // 设置窗口关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口图标
        setLayout(new BorderLayout());
    }

}
