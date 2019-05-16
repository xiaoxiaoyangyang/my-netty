package com.baizhi.factory.sort;

import com.baizhi.factory.entity.User;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/15 17:31
 * @Version 1.0
 */
public class SortUser {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User(1, "xiaohua", "13213"),
                new User(3, "lala", "12312"),
                new User(2, "hahaha", "87128")
        );

        List<User> user = Arrays.asList(
                new User(1, "xiaohua", "13213"),
                new User(3, "lala", "12312"),
                new User(2, "hahaha", "87128")
        );

//        List<User> collect = Stream.concat(users.stream(), user.stream()).collect(Collectors.toList());
//        User xiaohua = collect.stream().filter(o1 -> o1.getName().equals("xiaohua")).findAny().get();
//        System.out.println(xiaohua.toString());

        List<User> collect = Stream.of(user, users).flatMap(o1->o1.stream()).collect(Collectors.toList());
//        collect.forEach(jj->{
//            System.out.println(jj.toString());
//        });
        IntStream intStream = collect.stream().mapToInt(User::getId);
        intStream.forEach(s1-> System.out.println(s1));

//        boolean b = users.stream().allMatch(new Predicate<User>() {
//            @Override
//            public boolean test(User user) {
//                if (user.getId() == 1) {
//                    return true;
//                }
//                return false;
//            }
//        });
//        System.out.println("------------------------->");
//        System.out.println(b);
//        boolean b1 = users.stream().anyMatch(new Predicate<User>() {
//            @Override
//            public boolean test(User user) {
//                if (user.getId() == 1) {
//                    return true;
//                }
//                return false;
//            }
//        });
//        System.out.println("<-------------------->");
//        System.out.println(b1);

//        users.sort(Comparator.comparingInt(User::getId).reversed());
//        users.forEach(o1->{
//            System.out.println(o1.toString());
//        });
//        System.out.println("----------------------->");

//        Collections.sort(users, new Comparator<User>() {
//            @Override
//            public int compare(User o1, User o2) {
//                return o1.getId()-o2.getId();
//            }
//        }.reversed());
//        users.forEach(o2->{
//            System.out.println(o2.toString());
//        });
//        System.out.println("---------------------->");

//        users.sort(Comparator.comparing(User::getId).reversed());
//        users.forEach(o2->{
//            System.out.println(o2.toString());
//        });
//        System.out.println("<----------------------->");

//        Comparator<User> comparing = Comparator.comparing(User::getId);
//        System.out.println("------------------------->");

//        Collections.sort(users,Comparator.comparing(User::getId).reversed());
//        users.forEach(o1->{
//            System.out.println(o1.toString());
//        });
    }
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return object -> seen.putIfAbsent(keyExtractor.apply(object), Boolean.TRUE)==null;
    }

}
