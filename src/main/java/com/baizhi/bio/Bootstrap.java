package com.baizhi.bio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;

public class Bootstrap {
    public static void main(String[] args) throws IOException {
//        1.	创建Socket s=new Socket();
        Socket s=new Socket();
//        2.	连接服务器s.connect(new InetSocketAddress(ip,9999));
        s.connect(new InetSocketAddress("localhost",9999));
//        3.	使用流操作请求和响应 套路IO
        //①发请求
        OutputStream res = s.getOutputStream();
        PrintWriter pw=new PrintWriter(res);
        pw.println("你好，服务器我是客户端2！");
        pw.flush();
        s.shutdownOutput();//告知服务请求内容发送结束

        //②读取服务器响应
        InputStream req = s.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(req);
        BufferedReader br=new BufferedReader(inputStreamReader);
        StringBuilder sb=new StringBuilder();
        String line=null;
        while((line=br.readLine())!=null){
            sb.append(line);
        }
        System.out.println("客户端收到："+sb.toString());

//        4.	关闭socket资源
        s.close();
    }
}
