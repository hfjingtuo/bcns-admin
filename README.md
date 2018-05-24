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
