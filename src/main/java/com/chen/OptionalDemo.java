package com.chen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;


public class OptionalDemo {
    public static void main(String[] args) {
//        Author author = getAuthor();
//        Optional<Author> authorOptional = Optional.ofNullable(author);
//        authorOptional.ifPresent(author1 -> System.out.println(author1.getName()));

//        Optional<Author> authorOptional = getAuthorOptional2();
//        authorOptional.ifPresent(author1 -> System.out.println(author1.getName()));


        /*
          获取数据并且设置数据为空时的默认值。如果数据不为空就能获取到该数据。如果为空则根据你传入的参数来创建对象作为默认值返回。
         */
//        Optional<Author> authorOptional = Optional.ofNullable(getAuthor());
//        Author author = authorOptional.orElseGet(()-> new Author());
//        System.out.printf(author.getName());

        /*
          获取数据，如果数据不为空就能获取到该数据。如果为空则根据你传入的参数来创建异常抛出。
         */
//        Optional<Author> authorOptional = Optional.ofNullable(getAuthor());
//        try {
//            Author author = authorOptional.orElseThrow(() -> new RuntimeException("数据为空"));
//            System.out.println(author.getName());
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
        //我们可以使用filter方法对数据进行过滤。如果原本是有数据的，但是不符合判断，也会变成一个无数据的Optional对象。
//        Optional<Author> authorOptional = Optional.ofNullable(getAuthor());
//        authorOptional.filter(author -> author.getAge()>100).ifPresent(author -> System.out.println(author.getName()));

        /*
          我们可以使用isPresent方法进行是否存在数据的判断。如果为空返回值为false,如果不为空，返回值为true。但是这种方式并不能体现Optional的好处，更推荐使用ifPresent方法
         */
//        Optional<Author> authorOptional = Optional.ofNullable(getAuthor());
//        if (authorOptional.isPresent()) {
//            System.out.println(authorOptional.get().getName());
//        }

        List<Author> authors = getAuthors();
        Stream<Author> authorStream = authors.stream();
        Stream<Author> authorStream1 = authorStream.filter(new Predicate<Author>() {
            @Override
            public boolean test(Author author) {
                return author.getAge() > 17;
            }
        }.and(new Predicate<Author>() {
            @Override
            public boolean test(Author author) {
                return author.getName().length() > 1;
            }
        }));
        authorStream1.forEach(author -> System.out.println(author));
    }

    private static Author getAuthor() {
        Author author = new Author(1L,"奶妈",180,"一个从菜刀中明悟哲理的祖安人", null );
        return author;
    }
    /*
     * 获取作者，如果作者为空，则返回一个默认的作者
     * 	我们一般使用Optional的静态方法ofNullable来把数据封装成一个Optional对象。无论传入的参数是否为null都不会出现问题。
     */
    private static Optional<Author> getAuthorOptional() {
        Author author = new Author(1L,"奶妈",18,"一个从菜刀中明悟哲理的祖安人", null );
        return Optional.ofNullable(author);
    }

    /*
     * 	如果你确定一个对象不是空的则可以使用Optional的静态方法of来把数据封装成Optional对象。
     */
    private static Optional<Author> getAuthorOptional2() {
        Author author = new Author(1L,"奶妈",18,"一个从菜刀中明悟哲理的祖安人", null );
//        Author author = new Author();
        return Optional.of(author);
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
