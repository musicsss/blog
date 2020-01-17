##用户
###登录
* URL：/login
* METHOD:POST
* REQUEST:
```JSON
{
"userName":"user",
"passWord":"pwd",
"verifyCode":"a45f1g"
}
```
* RESPONSE:
```JSON
{
  "status":1,
  "message":"success",
  "data":"{'userName':'user',
  'gender':1,
  'birthday':'1999-01-01',
  'phone':'12345678901'
  }"
}
```
###增加用户
* URL:/user/addUser
* METHOD:POST
* REQUEST:
```JSON
{
"newUserName":"newUser",
"phone":"11010101010",
"password":"123456",
"gender":1,
"trueName":"小张",
"birthday":"2019-01-01",
"email":"110@gmail.com",
"personnalBrief":"个人简介",
"avatarImgUrl":"loclahost:8080/img/xiaozhang.jpg",
}
```

* RESPONSE:
```JSON
{
"status":1,
"message":"success",
}
```

###修改用户
* URL:/user/addUser
* METHOD:POST
* REQUEST:
```JSON

{
"newUserName":"newUser",
"phone":"11010101010",
"password":"123456",
"gender":1,
"trueName":"小张",
"birthday":"2019-01-01",
"email":"110@gmail.com",
 "personnalBrief":"个人简介",
 "avatarImgUrl":"loclahost:8080/img/xiaozhang.jpg",
 }
```

* RESPONSE:
```JSON
{
"status":1,
"message":"success",
}
```

##文章列表
* URL:/aticle/list
* METHOD:GET
* REQUEST:
```JSON
{
"userName":"user"
"orderSort":1
"listIndex":1
"pageNum":1
}
```

* RESPONSE:
```JSON
{
"status":1
"message":"success"
"data":[
{"title":"firstAticle","author":"小明","articleCategory":"日常",
"createTime":"2019-12-12 14:59:00",
"lastModified":"2020-01-01 12:12:56","content":"Hello world!"},
{"title":"secondAticle","author":"小美","articleCategory":"日常",
"createTime":"2019-12-20 09:42:01",
"lastModified":"2020-02-01 12:12:56","content":"Nice to meet you!"}
]
}
```

###获取验证码
* URL:/common/kaptcha
* METHOD:POST
* REQUEST:
```JSON
    {
    
    }
```
* RESPONSE
```JSON
{
  "status": 1,
  "message": "successs"
}
```
返回图片

###发送邮件验证码
* URL:/common/email
* METHOD:POST
* REQUEST
```JSON
{
  "emailAddress": "110@qq.com"
}
```
* RESPONSE
```JSON
{
    "status": 1,
    "message": "successs"
}
```


