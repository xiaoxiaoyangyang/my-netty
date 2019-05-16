package com.baizhi.factory;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/14 19:01
 * @Version 1.0
 */
public class MailSender implements Sender {
    @Override
    public void send() {
        System.out.println("this is mailsender!");
    }
}
