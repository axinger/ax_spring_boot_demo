package com.axing.demo;

import com.axing.demo.entity.MessageInfo;
import com.axing.demo.entity.MsgTypeEnum;
import com.axing.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {


    @Autowired
    private MessageService messageService;

    @Test
    public void test1() {
        MessageInfo messageInfo = new MessageInfo(MsgTypeEnum.TEXT, "这是一个文本消息");
        // 处理文本消息 这是一个文本消息
        messageService.handleMessage(messageInfo);
    }

    @Test
    public void test2() {
        MessageInfo messageInfo = new MessageInfo(MsgTypeEnum.IMAGE, "这是一个图片消息");
        // 处理图片消息 这是一个图片消息
        messageService.handleMessage(messageInfo);
    }

}
