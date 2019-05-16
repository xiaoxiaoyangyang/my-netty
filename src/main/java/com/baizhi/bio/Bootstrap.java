package com.baizhi.bio;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bootstrap {
    public static void main2(String[] args) throws IOException {
//        1.	创建Socket s=new Socket();
        Socket s=new Socket();
//        2.	连接服务器s.connect(new InetSocketAddress(ip,9999));
        s.connect(new InetSocketAddress("10.5.1.221",12345));
//        3.	使用流操作请求和响应 套路IO
        //①发请求
        OutputStream res = s.getOutputStream();
        PrintWriter pw=new PrintWriter(res);
        pw.println("AT+STACH0=1,3\n");
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

    public static void main6(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost",8888));
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.println("你好，我是客户端");
        printWriter.flush();
        socket.shutdownOutput();
        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line=null;
        while ((line=bufferedReader.readLine())!=null){
            stringBuilder.append(line);
        }
        System.out.println(stringBuilder.toString());
    }

    public static void main19(String[] args) {
        List<Integer> integers = Arrays.asList(1, 1, 2, 2, 4, 1, 4, 5, 5, 2,2,2);
        List<List<Integer>> li = new ArrayList<>();
        int mark=integers.get(0);
        List<Integer> list = new ArrayList<>();
        for(Integer in:integers){
            if(in==mark){
                list.add(in);
            }else{
                mark=in;
                li.add(list);
                list = new ArrayList<>();
                list.add(in);
            }
        }
        li.add(list);
        for (List<Integer> lis:li) {
            System.out.println("------------------->");
            for(Integer st:lis){
                System.out.println(st);
            }
        }
    }

    public static void main(String[] args) {
        float a=0.123123f;
        double a1 = a;
        System.out.println(a1);
    }

    public static void main7(String[] args) {

        List<Integer> collect = Stream.of(1,2,4,1,5,6,3).collect(Collectors.toList());
        collect.sort(Comparator.comparing(va->va));
        for (Integer in:collect){
            System.out.println(in);
        }


        Collections.sort(collect, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });

    }

    public static void main12(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost",9999));
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.println("这是测试的");
        printWriter.flush();
        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line= null;
        while((line=bufferedReader.readLine())!=null){
            stringBuilder.append(line);
        }
        bufferedReader.close();
        System.out.println(stringBuilder.toString());
    }
}
