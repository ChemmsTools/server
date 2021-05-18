# ChemmsToolsServer
### 狗子工具包的服务端
# 接口说明
* /chemmstools/login  
请求类型:POST  
```json
{
  "code":"sso返回的code"
}
```
返回值  
```json
{
    "success": "",
    "message": "",
    "data": {
        "username": "",
        "name": "",
        "token": ""
    }
}
```
* /chemmstools/getcontent  
请求类型:POST  
```json
{
  "contentId":""
}
```
返回值  
```json
{
    "success": "",
    "message": "",
    "data": {
        "id": "",
        "content": ""
    }
}
```

* /chemmstools/getlangmenu  
请求类型:POST  
```json
{
  "langId":""
}
```
返回值  
```json
{
    "success": "",
    "message": "",
    "data": {
        "id": "",
        "language": "",
        "data": ""
    }
}
```
* /chemmstools/getlanguage  
请求类型:POST  
```json
{

}
```
返回值  
```json
{
    "success": "",
    "message": "",
    "data": [
        {
            "id": "",
            "language": ""
        },
        {
            "id": "",
            "language": ""
        }
    ]
}
```
* /chemmstools/islogin  
请求类型:POST  
```json
{
  "token":""
}
```
返回值  
```json
{
    "success": "",
    "message": "",
    "data": {
        "username": "",
        "name": ""
    }
}
```