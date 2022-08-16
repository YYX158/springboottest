package com.atyyx.boot05web01.controller;

import com.atyyx.boot05web01.bean.Person;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ParameterTestController {

    // car/2/owner/zhangsan
    //利用路径变量
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String,Object> getCar(@PathVariable("id") Integer id,@PathVariable("username") String username,
                                     @PathVariable Map<String,String> pv,    //把所有的路径变量的kv都提取出来，就用Map,要求就是Map必须是String
                                     @RequestHeader("User-Agent") String userAgent,//获取请求头,带Key的表示只获取某一个，不带key的表示获取所有的
                                     @RequestHeader Map<String,String> header,
                                     @RequestParam("age") Integer age,
                                     @RequestParam("inters") List<String> inters,
                                     @RequestParam Map<String,String> params,  //,// 如果是要取出所有的值的话，就不需要指定请求的参数了
                                     @CookieValue("Idea-3fd9349c") Cookie cookie// 创建一个cookie对象可以将对应的cookie全部拿出来
    )
    {
        HashMap<String, Object> map = new HashMap<>();

        map.put("id",id);
        map.put("name",username);
        map.put("pv",pv);
        //map.put("userAgent",userAgent);
        //map.put("headers",header);
        map.put("age",age);
        map.put("inters",inters);
        map.put("params",params);
        map.put("cookie",cookie);
        System.out.println(cookie.getName()+"============>"+cookie.getValue());
        return map;
    }

    @PostMapping("/save")
    public Map postMethod(@RequestBody String content) //@RequestBody是把请求体里面的数据拿进来
    {

        Map<String,Object> map=new HashMap<>();

        map.put("content",content);
        return map;
    }


    //  /cars/sell;low=34;brand=byd,audi,yd
    //SpringBoot默认禁用掉矩阵变量的功能
    // 需要手动开启: 原理：对于整个路径的处理都是用UrlPathHelper解析
    // removeSemicolonContent（移除分号内容)支持矩阵变量
    // 3. 矩阵变量必须有url路径变量才能被解析
    @GetMapping("/cars/{path}")
    public Map carsSell(@MatrixVariable("low") Integer low,
                        @MatrixVariable("brand") List<String> brand,
                        @PathVariable("path") String path)
    {

        Map<String,Object> map=new HashMap<>();
        map.put("low",low);
        map.put("brand",brand);
        map.put("path",path);
        return map;

    }
    // /boss/1;age=20/2;age=10"
    @GetMapping("/boss/{bossID}/{empId}")
    public Map boss(@MatrixVariable(value = "age",pathVar ="bossId" ) Integer bossAge,
                    @MatrixVariable(value = "age",pathVar = "empId") Integer empAge)
    {

        Map<String,Object> map=new HashMap<>();

        map.put("bossAge",bossAge);
        map.put("empAge",empAge);
        return map;
    }

    /**
     * 数据绑定：页面提交的请求（GET,POST,PUT）都可以和对象属性进行绑定
     * @param person
     * @return
     */
    @PostMapping("/saveuser")
    public Person saveuser(Person person)
    {
        return person;
    }
}
