package com.atyyx.boot05web01.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.io.IOException;

/**
 * 文件上传试试
 */
@Controller
@Slf4j
public class FormController {

    @GetMapping("/form_layouts")
    public String form_layouts()
    {
        return "form/form_layouts";
    }


    /**
     * 多文件上传的时候，MultipartFile[]会自动封装上传来的文件
     * @param email
     * @param username
     * @param headerImg
     * @param photos
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String username,
                         @RequestPart("headerImg")MultipartFile headerImg,  // MultipartFile 用来上传图片文件
                         @RequestPart("photos") MultipartFile[] photos) throws IOException {

        log.info("上传的信息是:email={},username={},hearderImg={},photo={}",email,username,headerImg.getSize(),photos.length);
        if(!headerImg.isEmpty())
        {
            // 保存到文件服务器中，OS服务器中
            String originalFilename = headerImg.getOriginalFilename();//获取原始的文件名
            headerImg.transferTo(new File("E:\\cache\\"+originalFilename));
        }

        if(photos.length>0)
        {
            for (MultipartFile photo : photos)
            {
                if(!photo.isEmpty())
                {
                    String originalFilename1 = photo.getOriginalFilename();
                    photo.transferTo(new File("E:\\cache\\"+originalFilename1));
                }
            }
        }
        return "hard";
    }
}
