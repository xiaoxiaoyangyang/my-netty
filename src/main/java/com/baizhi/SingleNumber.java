package com.baizhi;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/3/21 19:32
 * @Version 1.0
 */
public class SingleNumber {
    private static final SingleNumber singleNumber=new SingleNumber();
    public static SingleNumber get(){
        return singleNumber;
    }
}
class SingleNumber1{
    private static  SingleNumber1 sin=null;
    public static SingleNumber1 ge(){
        if(sin==null){
            sin=new SingleNumber1();
        }
        return sin;
    }
}
class SingleNumber2{
    private static  SingleNumber2 sin=null;
    public static SingleNumber2 gg(){
        Lock lock = new ReentrantLock();
        if(sin==null){
            try {
                lock.lock();
                sin=new SingleNumber2();
            } finally {
                lock.unlock();
            }
        }
        return sin;
    }
}
