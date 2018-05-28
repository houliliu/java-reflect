一、反射的概述
JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性；这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。
要想解剖一个类,必须先要获取到该类的字节码文件对象。而解剖使用的就是Class类中的方法.所以先要获取到每一个字节码文件对应的Class类型的对象.
以上的总结就是什么是反射
反射就是把java类中的各种成分映射成一个个的Java对象
例如：一个类有：成员变量、方法、构造方法、包等等信息，利用反射技术可以对一个类进行解剖，把个个组成部分映射成一个个对象。
     （其实：一个类中这些成员方法、构造方法、在加入类中都有一个类来描述）
如图是类的正常加载过程：反射的原理在与class对象。
熟悉一下加载的时候：Class对象的由来是将class文件读入内存，并为之创建一个Class对象。




其中这个Class对象很特殊。我们先了解一下这个Class类

二、查看Class类在java中的api详解（1.7的API）
如何阅读java中的api详见java基础之——String字符串处理




Class 类的实例表示正在运行的 Java 应用程序中的类和接口。也就是jvm中有N多的实例每个类都有该Class对象。（包括基本数据类型）
Class 没有公共构造方法。Class 对象是在加载类时由 Java 虚拟机以及通过调用类加载器中的defineClass 方法自动构造的。也就是这不需要我们自己去处理创建，JVM已经帮我们创建好了。

没有公共的构造方法，方法共有64个太多了。下面用到哪个就详解哪个吧




三、反射的使用
/**
 * 反射实现方式一 获取Class对象
 */

ReflectTest reflectTest =new ReflectTest();
Class className1= reflectTest.getClass();

System.out.println("class  " +className1);


/**
 * 反射实现方式二 获取Class对象
 */

Class className2= ReflectTest.class;

System.out.println("class  " +className2);
//判断第一种方式获取的Class对象和第二种方式获取的是否是同一个
System.out.println(className1 == className2);


/**
 * 反射实现方式三 获取Class对象
 */

try {
    Class className3= Class.forName("ReflectTest");
    System.out.println("class  " +className3);

    //判断第一种方式获取的Class对象和第二种方式获取的是否是同一个
    System.out.println(className1 == className2);
    System.out.println(className3 == className2);
} catch (ClassNotFoundException e) {
    e.printStackTrace();
}
反射测试类：
public class ReflectTest {

    public   String  name;
    private  String  des;


    //---------------构造方法-------------------
    //（默认的构造方法）
    ReflectTest(String str){
        System.out.println("(默认)的构造方法   ReflectTest(String str) s = " + str);
    }

    //无参构造方法
    public ReflectTest(){
        System.out.println("  public ReflectTest() ");
    }

    //有一个参数的构造方法
    public ReflectTest(char name){
        System.out.println("   public ReflectTest(char name)  姓名：" + name);
    }

    //有多个参数的构造方法
    public ReflectTest(String name ,int age){
        System.out.println("  public ReflectTest(String name ,int age) 姓名："+name+"年龄："+ age);//这的执行效率有问题，以后解决。
    }

    //受保护的构造方法
    protected ReflectTest(boolean n){
        System.out.println(" protected ReflectTest(boolean n) 受保护的构造方法 n = " + n);
    }

    //私有构造方法
    private ReflectTest(int age){
        System.out.println(" private ReflectTest(int age) 私有的构造方法   年龄："+ age);
    }
    public  String  say(String name){

        return  "hello "+name;
    }
    private  String  say( ){

        return  "hello "+this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "ReflectTest{" +
                "name='" + name + '\'' +
                ", des='" + des + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReflectTest that = (ReflectTest) o;

        if (!name.equals(that.name)) return false;
        return des.equals(that.des);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + des.hashCode();
        return result;
    }
}

反射获取构造 方法测试：

public class ConstructorsTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        /* 1 加载class */
       Class className=Class.forName("ReflectTest");

        //2.获取所有公有构造方法
        System.out.println("**********************所有公有构造方法*********************************");
        Constructor[] conArray = className.getConstructors();
        for(Constructor c : conArray){
            System.out.println(c);
        }
        /** 结果：
         * **********************所有公有构造方法*********************************
         * public ReflectTest(java.lang.String,int)
         * public ReflectTest()
         * public ReflectTest(char)
         *
         */

        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        conArray = className.getDeclaredConstructors();
        for(Constructor c : conArray){
            System.out.println(c);
        }

        /**
         * 结果：
         * ************所有的构造方法(包括：私有、受保护、默认、公有)***************
         * private ReflectTest(int)
         * protected ReflectTest(boolean)
         * public ReflectTest(java.lang.String,int)
         * ReflectTest(java.lang.String)
         * public ReflectTest()
         * public ReflectTest(char)
         *
         */

        System.out.println("*****************获取公有、无参的构造方法*******************************");
        Constructor constructor = className.getConstructor();
        System.out.println("constructor = " + constructor);
        //调用构造方法
        Object obj = constructor.newInstance();
        /**结果：
         * *****************获取公有、无参的构造方法*******************************
         *  constructor = public ReflectTest()
         *  public ReflectTest()
         *
         */

        System.out.println("******************获取私有构造方法，并调用*******************************");
        constructor = className.getDeclaredConstructor(int.class);
        System.out.println(constructor);
        //调用构造方法
        constructor.setAccessible(true);//暴力访问(忽略掉访问修饰符)
        obj = constructor.newInstance(23);

        /**结果：
         * ******************获取私有构造方法，并调用*******************************
         * private ReflectTest(int)
         * private ReflectTest(int age) 私有的构造方法   年龄：23
         *
         *
         */
    }

结果：
**********************所有公有构造方法*********************************
public ReflectTest(java.lang.String,int)
public ReflectTest()
public ReflectTest(char)
************所有的构造方法(包括：私有、受保护、默认、公有)***************
private ReflectTest(int)
protected ReflectTest(boolean)
public ReflectTest(java.lang.String,int)
ReflectTest(java.lang.String)
public ReflectTest()
public ReflectTest(char)
*****************获取公有、无参的构造方法*******************************
constructor = public ReflectTest()
  public ReflectTest() 
******************获取私有构造方法，并调用*******************************
private ReflectTest(int)
 private ReflectTest(int age) 私有的构造方法   年龄：23

构造方法调用方法：
1.获取构造方法：
  1).批量的方法：
public Constructor[] getConstructors()：所有"公有的"构造方法
            public Constructor[] getDeclaredConstructors()：获取所有的构造方法(包括私有、受保护、默认、公有)
     
  2).获取单个的方法，并调用：
public Constructor getConstructor(Class... parameterTypes):获取单个的"公有的"构造方法：
public Constructor getDeclaredConstructor(Class... parameterTypes):获取"某个构造方法"可以是私有的，或受保护、默认、公有；

  调用构造方法：
Constructor-->newInstance(Object... initargs)

2、newInstance是 Constructor类的方法（管理构造函数的类）
api的解释为：
newInstance(Object... initargs)
           使用此 Constructor 对象表示的构造方法来创建该构造方法的声明类的新实例，并用指定的初始化参数初始化该实例。
它的返回值是T类型，所以newInstance是创建了一个构造方法的声明类的新实例对象。并为之调用

3、获取成员变量并调用
public class FieldsTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
