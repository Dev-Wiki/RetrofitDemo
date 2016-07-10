# RetrofitDemo

最近呢，我租了个服务器，博客也开始转移至新的地址：http://blog.devwiki.net

如果你访问http://www.devwiki.net 仍旧会跳转到旧的博客地址。废话不多说，说一下今天的内容吧。

前一段时间，我发布了几篇关于[Retrofit使用的教程](http://www.devwiki.net/categories/Retrofit%E4%BD%BF%E7%94%A8%E6%95%99%E7%A8%8B/)，里面使用多说的接口作为案例，测试的那篇文章已经被童鞋们添加了7000+的评论,在这样下去估计快过万了。

这段时间我在购买的服务器上配置的Nginx和Tomcat。然后又学习了javaweb的知识，做了一个简单的Retrofit测试接口api:**http://retrofit.devwiki.net**

目前可供测试的接口有三个。

##说明：以下接口仅供测试，请不要恶意攻击我的服务器！

## simple接口

此接口仅供简单的GET和POST请求。

**GET请求**

请求地址：http://retrofit.devwiki.net/simple
请求方法：GET
请求参数：无
结果返回：

```
{
    "code": 10001,
    "desc": "success",
    "data": {
        "method": "GET"
    }
}
```

**POST请求**

请求地址：http://retrofit.devwiki.net/simple
请求方法：POST
请求参数：无
结果返回：

```
{
  "code": 10001,
  "desc": "success",
  "data": {
    "method": "POST"
  }
}
```

## param接口

此接口仅供简单的GET和POST请求。

**GET请求**

请求地址：http://retrofit.devwiki.net/param
请求方法：GET
请求参数：
> 关键字：id
> 值：任意字符串

结果返回：
1.若不带id，返回结果如下：

```
{
  "code": 1002,
  "desc": "param is invalid:id = null"
}
```

2.若带有id参数，返回结果如下：
```
{
  "code": 10001,
  "desc": "success",
  "data": {
    "id": "123"
  }
}
```

**POST请求**

请求地址：http://retrofit.devwiki.net/param
请求方法：POST
请求参数：
> param:id, value:任意值
> header:type, value: POST

结果返回：
仅当id不为空并且type=POST时，返回成功

```
{
  "code": 10001,
  "desc": "success",
  "data": {
    "id": "dddd",
    "type": "POST"
  }
}
```
否则返回失败
```
{
  "code": 1002,
  "desc": "param is invalid:id = null"
}
```

## ip接口

该接口仅接受GET请求测试,返回客户端的ip地址。

**GET请求**

请求地址：http://retrofit.devwiki.net/ip
请求方法：GET
请求参数：无
结果返回：
```
{
  "code": 10001,
  "desc": "success",
  "data": {
    "ip": "127.0.0.1"
  }
}
```

目前只提供以上三个接口，以后待学习更多的javaweb，Retrofit，HTTP等知识再进行相应的增加接口。
