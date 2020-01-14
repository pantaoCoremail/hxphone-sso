Hxphone单点jar使用说明
==

### 使用步骤：
- 在项目中引入 hxphone-sso-x.x.jar
- 根据用户信息得到其对应email（要单点的邮箱号）
- 调用jar中的HXphoneUtil工具类的相应方法，传入email和对应参数，即可实现单点功能。


### 场景1：单点到"收件箱"页面
- 方法：**HXphoneUtil.getMainURL(String ssoIp, String email, String webmailUrl)**
- 示例：

```
    SSOResult result = HXphoneUtil.getMainURL("192.168.200.251", "sso@test.com", "http://192.168.200.251/coremail");
    System.out.println(result.getUrl());
    //打印结果：
    //http://192.168.200.251/coremail/hxphone/sso.html#/frame/folder/1?sid=BAzeOhGGASKcaSstzKGGOlLAyrwJFVGo
```
- result.getUrl()得到的地址直接redirect即可进入收件箱页面
 
 
### 场景2：单点到"写信"页面
- 方法：**HXphoneUtil.getComposeURL(String ssoIp, String email, String webmailUrl)**
- 示例：

```
    SSOResult result = HXphoneUtil.getComposeURL("192.168.200.251", "sso@test.com", "http://192.168.200.251/coremail");
    System.out.println(result.getUrl());
    //打印结果：
    //http://192.168.200.251/coremail/hxphone/sso.html#/frame/compose///?sid=BAFeOhGGRSKcaSVvzKGGTqEtGKETRZGo
```
- result.getUrl()得到的地址直接redirect即可进入写信页面


### 场景3：单点到"写信"页面（自动填充收件人）
- 方法：**HXphoneUtil.getComposeURL(String ssoIp, String email, String webmailUrl, String to)**
- 示例：

```
    SSOResult result = HXphoneUtil.getComposeURL("192.168.200.251", "fromUser@test.com", "http://192.168.200.251/coremail, "toUser@test.com");
    System.out.println(result.getUrl());
    //打印结果：
    //http://192.168.200.251/coremail/hxphone/sso.html#/frame/compose///?sid=BAjrOhGGgbacaSotzKGGPIriWvETRZGo&to=test@test.com
```
- result.getUrl()得到的地址直接redirect即可进入写信页面，并自动填充收件人


### 场景4：单点读信
- 方法：**HXphoneUtil.getReadMsgURL(String ssoIp, String email, String webmailUrl, String mid)**
- 示例：

```
    SSOResult result = HXphoneUtil.getReadMsgURL("192.168.200.251", "sso@test.com", "http://192.168.200.251/coremail", "1:1tbiAQAAAFuJfpsARQA9sO");
    System.out.println(result.getUrl());
    //打印结果：
    //http://192.168.200.251/coremail/hxphone/sso.html#/frame/read/-2/1:1tbiAQAAAFuJfpsARQA9sO?sid=BAmeOhGGiSKcaSVvzKGGhqPMlGOvafGo
```
- result.getUrl()得到的地址直接redirect即可进入读信页面
  

### 场景5：获取单点sid
- 方法：**HXphoneUtil.getSSOSid(String ssoIp, String email)**
- 示例：

```
    SSOResult result = HXphoneUtil.getSSOSid("192.168.200.251", "sso@test.com");
    System.out.println(result.getSid());
    //打印结果：
    //BAfeOhGGmSKcaSKtzKGGTYscglwJFVGo
```
- result.getSid()得到sid，可用于自行拼接单点地址进行单点


### 入参说明：
参数名|类型|描述
-- | :--: | --
ssoIp | String | 单点登录地址（即rmi服务所在地址），不带协议，如：192.168.100.10或192.168.100.10:6195，不带端口则默认端口为6195
email | String | 单点的邮箱号，如：sso@test.com
webmailUrl | String | webmail地址，如：http://192.168.200.251/coremail
mid | String | 信件mid，场景4用到
to | String | 收件人邮箱号，场景3用到，支持多个，用英文逗号分隔

### 响应类SSOResult字段说明：
参数名|类型|描述
-- | :--: | --
code | int | 返回码
msg | String | 错误描述
sid | String | 单点凭证sid
url | String | 单点读信/收件箱/写信页面地址

### 返回码说明：
返回码 | 描述
-- | --
10000 | 操作成功        
11000 | email格式错误         
11001 | webmailUrl格式错误         
11002 | ssoIp格式错误    
12000 | webmailUrl请求失败
12001 | ssoIp连接错误
13000 | 单点登录失败
99999 | 未知错误
