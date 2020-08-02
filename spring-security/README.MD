# 基于spring-security实现OAUTH2.0
## How to use
启动order-api资源服务和order-auth认证服务

授权码authorization code模式：
1. 获取code

	```
	Get http://localhost:8080/oauth/authorize?
	response_type=code
	&client_id=client_2
	&redirect_uri=www.baidu.com
	&scope=select
	```
	跳转到登录页面，输入用户名密码 user_1 123456,点击允许授权后返回code
	
	```
	http://localhost:8080/oauth/www.baidu.com?code=oVm13H
	```

4.  根据code换取Token
	
	```
	POST http://localhost:8080/oauth/token?
	client_id=client_2&
	client_secret=123456&
	grant_type=authorization_code&
	code=IhUJjj&
	redirect_uri=www.baidu.com
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
Authorization: bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb3VudHJ5IjoiY2hpbmEiLCJhdWQiOlsib3BlbmFwaSJdLCJ1c2VyX25hbWUiOiJzYWlsIiwic2NvcGUiOlsic2VsZWN0Il0sImV4cCI6MTU5NjM4MjAyMSwiYXV0aG9yaXRpZXMiOlsiVVNFUiJdLCJqdGkiOiI4Nzk1ZWViZC1kYjNlLTQ0MTktYWI5MC0zZjA5MjZjYzRhNTYiLCJhZ2UiOjY3LCJjbGllbnRfaWQiOiJjbGllbnRfMiJ9.8AqmwEsCbPdFFCezRdmxMOPe3AuuEAl1_yq3AACZtYs
```
