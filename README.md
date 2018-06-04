关于RESTful登录设计的介绍可以参考[RESTful登录设计（基于Spring及Redis的Token鉴权）][1]
SpringBoot拦截器和自定义注解验证是否登录：
https://blog.csdn.net/Emily201314/article/details/78881192[2]
使用 spring 拦截器和自定义注解进行登录拦截：
https://www.jianshu.com/p/97362fdf039e

使用 Token 进行身份鉴权
网站应用一般使用 Session 进行登录用户信息的存储及验证，而在移动端使用 Token 则更加普遍。它们之间并没有太大区别，Token 比较像是一个更加精简的自定义的 Session。Session 的主要功能是保持会话信息，而 Token 则只用于登录用户的身份鉴权。所以在移动端使用 Token 会比使用 Session 更加简易并且有更高的安全性，同时也更加符合 RESTful 中无状态的定义。
交互流程

    客户端通过登录请求提交用户名和密码，服务端验证通过后生成一个 Token 与该用户进行关联，并将 Token 返回给客户端。
    客户端在接下来的请求中都会携带 Token，服务端通过解析 Token 检查登录状态。
    当用户退出登录、其他终端登录同一账号（被顶号）、长时间未进行操作时 Token 会失效，这时用户需要重新登录。


**BCNS平台系统后台**

**服务框架**：springboot+mybatis+redis+mysql

**主要模块介绍**：

Service：服务管理【用于第三方服务的注册,注销】

Workflow：工作流管理【工作流的注册和编辑】 

Loans：业务申请管理【业务注册和查询】       
     
Operation：业务操作管理【业务流程跟踪】

User：用户管理【用户注册和权限管理】

服务器IP：39.104.123.195

mysql 端口：8000 root/root

redis 端口：9000 zx1234


Token：在用户第一次登录成功后，服务端返回一个token回来，这个token是根据userId进行加密的，密钥只有服务器知道，然后浏览器每次请求都把这个token放在Header里请求，这样服务器只需进行简单的解密就知道是哪个用户了。
