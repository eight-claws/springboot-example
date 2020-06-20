package com.jun.cloud;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CloudStartApplicationTest {

    @Test
    public void str(){
        //Stream stream;
        //
        ////由单独的值构成
        //Stream<String> strStream = Stream.of("one", "two", "three", "four");
        //Stream<Integer> intStream = Stream.of(1, 232, 34);
        //
        ////由数组构成
        //String [] strArray = new String[] {"a", "bb", "c"};
        //stream = Stream.of(strArray);
        //stream = Arrays.stream(strArray);
        //
        ////由集合构成，最常用了
        //List<String> list = Arrays.asList(strArray);
        //stream = list.stream();
        //
        //
        //IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
        //IntStream.range(1, 3).forEach(System.out::println);
        //IntStream.rangeClosed(1, 3).forEach(System.out::println);
        //
        //
        //
        //Stream<String> stream = Stream.of("one", "two", "three", "four");
        //
        //// 1. 转化为数组
        //String[] strArray1 = stream.toArray(String[]::new);
        //// 2. 转化为集合
        //List<String> list1 = stream.collect(Collectors.toList());
        //List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
        //Set set1 = stream.collect(Collectors.toSet());
        //Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
        //// 3. 转为String
        //String str = stream.collect(Collectors.joining(",")).toString();


        //生成15个随机整数，用来构造测试不失为一种简便的方式



        ////生成100以内的15个随机整数，用来构造测试不失为一种简便的方式
        //Stream.generate(() -> new Random().nextInt(100)).limit(15).forEach(System.out::println);
        //
        ////Another way
        //IntStream.generate(() -> (int) (System.nanoTime() % 100)).
        //        limit(15).forEach(System.out::println);
        //
        ////random提供了更方便的ints()方法
        //new Random().ints().limit(15).forEach(System.out::println);



    }



}