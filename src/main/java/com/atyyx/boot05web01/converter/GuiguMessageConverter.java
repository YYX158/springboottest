package com.atyyx.boot05web01.converter;

import com.atyyx.boot05web01.bean.Person;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 自定义的Converter
 */
public class GuiguMessageConverter implements HttpMessageConverter<Person> {
    @Override
    /**
     * 能不能把Person类型的数据都读成数据流的形式
     * 我们都写成false的意思就是不支持读
     */
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return clazz.isAssignableFrom(Person.class);
    }

    @Override
    /**
     * 服务器要统计所有的MessageConverter都能写出哪些内容的类型
     * 获取所有支持的媒体类型
     * application/x-guigu
     * @return
     */
    public List<MediaType> getSupportedMediaTypes() {
        //声明一下我们能够返回的数据类型是application/x-guigu
        return MediaType.parseMediaTypes("application/x-guigu");
    }

    @Override
    public Person read(Class<? extends Person> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(Person person, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        //自定义数据的写出
        String data=person.getUserName()+";"+person.getAge()+";"+person.getBirth()+";"+person.getPet();

        //写出自定义的数据
        OutputStream body = outputMessage.getBody();
        //将自定义数据写出去
        body.write(data.getBytes());
    }
}
