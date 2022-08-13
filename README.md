# 商品购买项目：

>介绍

用户选择商品，并进行支付，管理人员查看情况，并统计

用户：

​	1.普通用户：主要是进行商品购买

​	2.管理人员：查看购买情况，编辑用户信息，系统根据购买情况生成一些分析图



## 1、项目起因

为了对学习进度项目的学习，进一步巩固自己的能力

自己独立完成一个项目来提高自己的效率和处理技巧



## 2、项目内容

普通用户：用户注册，用户登录，查看商品信息，进行商品购买

管理人员：上传商品信息，查看商品购买情况，编辑用户信息，系统根据购买情况生成一些分析图



## 3、技术支持

SpringBoot + MyBatis-Plus + Validation + knife4j + redis + logB

+easyExcel



## 4、项目分析

![image-20220813111428677](E:\Typora\images\typora-user-images\image-20220813111428677.png)



## 5、记录

**用户表：**

- user_id：用户Id
- username ：昵称
- phone：电话
- password：密码
- avator：头像
- description：描述
- roles：角色（管理员或普通用户）
- 创建时间
- 修改时间
- 逻辑删除字段



**商品表：**

- product_id：商品Id
- product_name：商品名称
- product_picture：商品照片
- product_des：商品描述
- product_price：商品价钱
- asscss_id：评价
- buy：购买( 默认是0：0代表未购买，1代表购买)
- 三件套



**评价表：**

- assess_id：评价Id
- user_id：用户(到时候之返回用户昵称和头像)
- assess_content：评价内容



**商品和评价的中间表：**

- record_id：中间表id
- product_id：商品Id
- assess_id：评价Id
- assess_content：评价内容





### Mybatis-Plus的id自动生成策略（雪花算法）

```properties
mybatis-plus.global-config.db-config.id-type=assign_id
```

实体类中给主键加入@TableId注解





### controller和service里要写的代码都是什么？



### 前端库：

antdesign组件库：[Ant Design of Vue - Ant Design Vue (antdv.com)](https://www.antdv.com/docs/vue/introduce-cn)

Vite构建工具：[Home | Vite中文网 (vitejs.cn)](https://vitejs.cn/)

axios：[axios中文网|axios API 中文文档 | axios (axios-js.com)](http://www.axios-js.com/)

vue-router路由：[Home | Vue Router (vuejs.org)](https://router.vuejs.org/zh/)

vue：[Vue.js - 渐进式 JavaScript 框架 | Vue.js (vuejs.org)](https://cn.vuejs.org/)



### 跨域：

浏览器的一个同源策略

1. 协议（https,http）
2. 域名（aaa.com,bbb.com）
3. 端口（aaaa.com:81,aaaa.com:82）



## 6、功能介绍

### 用户

#### 1、用户注册

通过邮箱email进行用户注册



#### 2、用户登录

1. 告诉系统当前使用这个系统的用户是谁
2. 系统记录当前使用的用户
3. 鉴权功能
4. 类似动作类的都要用post
5. 入参的账号(邮箱) 和密码封装成一个类
6. 登录常规后返回登录成功的这个用户的部分信息
7. 通过user_id存储当前用户信息



#### 3、通过session储存用户信息

>  问题：

1. 如果要做分布式，那么我们该如何对于多个相同的服务去判断用户是否登录
2. 我们需要一个中间件去管理多个相同的服务之间用户的状态
3. 这里我们需要用到redis



#### 4、查询所有用户

- 管理员权限
- 密码不存在
- 逻辑删除字段不存在



##### 5、用户头像上传



### 商品

#### 1、**普通用户**

- 查询所有商品信息
- 购买商品
- 查看评价
- 新增评价
- 删除评价

#### 2、**管理员：**

- 新增商品及其信息
- 修改商品信息
- 删除商品信息
- 查看评价
- 新增评价
- 删除评价



#### 3、商品照片上传

与用户头像上传类似，可提取为公共类



### 评价

1、新增评价

2、查看评价

3、修改评价

4、删除评价