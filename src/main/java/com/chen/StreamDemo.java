package com.chen;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;


public class StreamDemo {
    public static void main(String[] args) {

//        test06();
//        test07();
//        test08();
//        test09();
//        test10();
//        test11();
//        test12();
//        test13();
//        test14();
//        test15();
//        test16();
//        test17();
//        test18();
//        test19();
//        test20();
//        test21();
//        test22();
//        test23();
//        test24();
        test25();
    }
    //使用reduce求所有作者中年龄的最小值
    private static void test25() {
        List<Author> authors = getAuthors();
        Integer reduce = authors.stream()
                .map(Author::getAge)
                .reduce(Integer.MAX_VALUE, (result, element) -> result > element ? element : result);
        System.out.println(reduce);
    }

    //使用reduce求所有作者中年龄的最大值
    private static void test24() {
        List<Author> authors = getAuthors();
        Integer reduce = authors.stream()
                .map(Author::getAge)
                .reduce(Integer.MIN_VALUE, (result, element) -> result < element ? element : result);
        System.out.println(reduce);
    }

    // 使用reduce求所有作者年龄的和
    private static void test23() {
        List<Author> authors = getAuthors();
        Integer reduce = authors.stream()
                .distinct()
                .map(Author::getAge)
                .reduce(0, (result, element) -> result + element);
        System.out.println(reduce);
    }

    //获取一个年龄最小的作家，并输出他的姓名。
    private static void test22() {
        List<Author> authors = getAuthors();
        Optional<Author> first = authors.stream()
                .sorted((o1, o2) -> o1.getAge() - o2.getAge())
                .findFirst();
        first.ifPresent(author -> System.out.println(author.getName()));
    }

    //获取任意一个年龄大于18的作家，如果存在就输出他的名字
    private static void test21() {
        List<Author> authors = getAuthors();
        Optional<Author> optionalAuthor = authors.stream()
                .filter(author -> author.getAge()>18)
                .findAny();
        optionalAuthor.ifPresent(author -> System.out.println(author.getName()));

    }

    //判断作家是否都没有超过100岁的。
    private static void test20() {
        List<Author> authors = getAuthors();
        boolean flag = authors.stream()
                .noneMatch(author -> author.getAge() > 100);
        System.out.println(flag);
    }

    //判断是否所有的作家都是成年人,age大于18
    private static void test19() {
        List<Author> authors = getAuthors();
        boolean flag = authors.stream()
                .allMatch(author -> author.getAge() >= 18);
        System.out.println(flag);
    }

    //判断是否有年龄在29以上的作家
    private static void test18() {
        List<Author> authors = getAuthors();
        boolean flag = authors.stream()
                .anyMatch(author -> author.getAge() > 29);
        System.out.println(flag);
    }

    //获取一个Map集合，map的key为作者名，value为List<Book>
    private static void test17() {
        List<Author> authors = getAuthors();
        Map<String, List<Book>> collect = authors.stream()
                //需要对作者名进行去重，因为作者名作为key，key值重复会报错
                .distinct()
                .collect(Collectors.toMap(author -> author.getName(), author -> author.getBooks()));
        System.out.println(collect);
    }

    //获取一个所有书名的Set集合。
    private static void test16() {
        List<Author> authors = getAuthors();
        List<Book> collect = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    //获取一个存放所有作者名字的List集合。
    private static void test15() {
        List<Author> authors = getAuthors();
        List<String> collect = authors.stream()
                .map(author -> author.getName())
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    //分别获取这些作家的所出书籍的最高分和最低分并打印。
    //Stream<Author>  -> Stream<Book> ->Stream<Integer>  ->求值
    private static void test14() {
        List<Author> authors = getAuthors();
        Optional<Integer> max = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .max((score1, score2) -> score1 - score2);
        Optional<Integer> min = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .min((score1, score2) -> score1 - score2);
        System.out.println(min.get());
        System.out.println(max.get());
    }

    //打印这些作家的所出书籍的数目，注意删除重复元素。
    private static void test13() {
        List<Author> authors = getAuthors();
        long count = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .count();
        System.out.println(count);
    }

    //输出所有作家的名字
    private static void test12() {
        List<Author> authors = getAuthors();
        authors.stream()
                .map(author -> author.getName())
                .distinct()
                .forEach(name -> System.out.println(name));
    }

    //打印现有数据的所有分类。要求对分类进行去重。不能出现这种格式：哲学,爱情     爱情
    private static void test11() {
        List<Author> authors = getAuthors();
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .flatMap(book -> Arrays.stream(book.getCategory().split(",")))
                .distinct()
                .forEach(category -> System.out.println(category));

    }

    //打印所有书籍的名字。要求对重复的元素进行去重。
    private static void test10() {
        List<Author> authors = getAuthors();
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .forEach(book -> System.out.println(book.getName()));
    }

    //打印除了年龄最大的作家外的其他作家，要求不能有重复元素，并且按照年龄降序排序。
    private static void test09() {
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted()
                .skip(1)
                .forEach(author -> System.out.println(author.getName()));
    }

    //对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素,然后打印其中年龄最大的两个作家的姓名。
    private static void test08() {
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted()
                .limit(2)
                .forEach(author -> System.out.println(String.valueOf(author.getAge())));
    }

    //对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素。
    private static void test06() {
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .forEach(author -> System.out.println(author.getIntro()));
    }

    // 对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素。
    private static void test07() {
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted()
                .forEach(author -> System.out.println(String.valueOf(author.getAge())));
    }


    private static List<Author> getAuthors() {
        //数据初始化
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        Author author2 = new Author(2L, "亚拉索", 15, "狂风也追逐不上他的思考速度", null);
        Author author3 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);
        Author author4 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);

        //书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L, "刀的两侧是光明与黑暗", "哲学,爱情", 88, "用一把刀划分了爱恨"));
        books1.add(new Book(2L, "一个人不能死在同一把刀下", "个人成长,爱情", 99, "讲述如何从失败中明悟真理"));

        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(4L, "吹或不吹", "爱情,个人传记", 56, "一个哲学家的恋爱观注定很难把他所在的时代理解"));

        books3.add(new Book(5L, "你的剑就是我的剑", "爱情", 56, "无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author, author2, author3, author4));
        return authorList;
    }

}

