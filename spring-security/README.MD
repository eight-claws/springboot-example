# About
基于[spring-security-oauth](https://github.com/spring-projects/spring-security-oauth)实现OAuth2.0
为了尽量接近实际开发，认证服务和资源服务模块分开，用户和第三方客户端认证信息都放在数据库中

# Getting Started
启动redis服务和postgres数据库服务，数据库脚本和初始化数据在[init.sql](./doc/init.sql)

启动order-api资源服务和order-auth认证服务

# How To Use

### 授权码authorization code模式：
1. 获取code

	```
	Get http://localhost:8080/oauth/authorize?
	response_type=code
	&client_id=client_2
	&redirect_uri=http://www.baidu.com
	&scope=openapi
	```
	跳转到登录页面，输入用户名密码 test@163.com 123456,点击允许授权后，会再次跳转到redirect_uri指定的网址并返回code
	
	```
	https://www.baidu.com/?code=cDG0IN
	```

4.  根据code换取Token
	
	```
	POST http://localhost:8080/oauth/token?
	client_id=client_2&
	client_secret=123456&
	grant_type=authorization_code&
	code=cDG0IN&
	redirect_uri=http://www.baidu.com
	```
	返回
	```
	{
	  "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb3VudHJ5IjoiY2hpbmEiLCJhdWQiOlsib3BlbmFwaSJdLCJ1c2VyX25hbWUiOiJzYWlsIiwic2NvcGUiOlsic2VsZWN0Il0sImV4cCI6MTU5NjM4MjAyMSwiYXV0aG9yaXRpZXMiOlsiVVNFUiJdLCJqdGkiOiI4Nzk1ZWViZC1kYjNlLTQ0MTktYWI5MC0zZjA5MjZjYzRhNTYiLCJhZ2UiOjY3LCJjbGllbnRfaWQiOiJjbGllbnRfMiJ9.8AqmwEsCbPdFFCezRdmxMOPe3AuuEAl1_yq3AACZtYs",
	  "token_type": "bearer",
	  "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb3VudHJ5IjoiY2hpbmEiLCJhdWQiOlsib3BlbmFwaSJdLCJ1c2VyX25hbWUiOiJzYWlsIiwic2NvcGUiOlsic2VsZWN0Il0sImF0aSI6Ijg3OTVlZWJkLWRiM2UtNDQxOS1hYjkwLTNmMDkyNmNjNGE1NiIsImV4cCI6MTU5ODkzMDgyMSwiYXV0aG9yaXRpZXMiOlsiVVNFUiJdLCJqdGkiOiI4ODQ0ZWYyYy03NThiLTRhZWItOGMwZi1mNWQzZDM1YTc4MTIiLCJhZ2UiOjY3LCJjbGllbnRfaWQiOiJjbGllbnRfMiJ9.G_BVtkUeJ1-RH5PareiamZC1bGkbSBWLLokNKwtg21o",
	  "expires_in": 43199,
	  "scope": "select",
	  "country": "china",
	  "user_name": "sail",
	  "age": 67,
	  "jti": "8795eebd-db3e-4419-ab90-3f0926cc4a56"
	}
	```
3.然后访问资源服务：
   ```
    GET http://localhost:8081/openApi/order/1
        Accept: application/json
        Authorization: bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb3VudHJ5IjoiY2hpbmEiLCJhdWQiOlsib3BlbmFwaSJdLCJ1c2VyX25hbWUiOiJzYWlsIiwic2NvcGUiOlsic2VsZWN0Il0sImV4cCI6MTU5NjM4MjAyMSwiYXV0aG9yaXRpZXMiOlsiVVNFUiJdLCJqdGkiOiI4Nzk1ZWViZC1kYjNlLTQ0MTktYWI5MC0zZjA5MjZjYzRhNTYiLCJhZ2UiOjY3LCJjbGllbnRfaWQiOiJjbGllbnRfMiJ9.8AqmwEsCbPdFFCezRdmxMOPe3AuuEAl1_yq3AACZtY
   ```

### 刷新授权码
	

```
POST http://localhost:8080/oauth/token?
	client_id=client_2&
	client_secret=123456&
	grant_type=refresh_token&
	refresh_token=refresh_token
```

## Doc
[spring-security-oauth User Guide](https://projects.spring.io/spring-security-oauth/docs/oauth2.html)