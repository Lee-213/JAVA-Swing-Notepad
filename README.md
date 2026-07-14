# Java Swing Notepad

一个基于 Java Swing 开发的桌面记事本应用，实现了类似 Windows Notepad 的基本文本编辑功能。

本项目使用 Java 原生 GUI 框架 Swing 开发，主要用于学习 Java GUI 编程、事件监听机制以及 Java IO 文件操作。

## ✨ Features

### 📝 Text Editing

- 新建文本
- 文本输入与编辑
- 全选
- 复制
- 剪切
- 粘贴
- 删除


### 📂 File Management

- 创建新文件
- 打开本地文本文件
- 保存文件
- 另存为文件

支持：

- `.txt` 文件


### 🎨 Format Settings

- 修改字体类型
- 修改字体大小
- 调整文本显示效果


### 🔧 Tools

- 插入当前系统时间
- 字数统计
- 行数统计


### 👤 User System

- 用户注册
- 用户登录
- 本地文件保存用户信息


---

## 🖥️ Screenshot

> 项目运行截图

<!-- 在这里放项目截图 -->

例如：

![Main Interface](images/main.png)

---

## 🛠️ Technology Stack

| Technology | Description |
|------------|-------------|
| Java | Programming Language |
| Swing | GUI Framework |
| Java IO | File Read / Write |
| IntelliJ IDEA | Development Tool |
| JDK 8+ | Runtime Environment |


---

## 📁 Project Structure

'''text

Java-Notepad
│
├── src
│ │
│ ├── dao
│ │ └── UserDao.java
│ │
│ ├── entity
│ │ └── User.java
│ │
│ ├── listener
│ │ └── ActionListener classes
│ │
│ ├── view
│ │ ├── LoginFrame.java
│ │ ├── RegisterFrame.java
│ │ └── NotepadFrame.java
│ │
│ └── Main.java
│
├── data
│ └── users.txt
│
└── README.md
