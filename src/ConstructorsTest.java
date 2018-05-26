import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * ${DESCRIPTION} 反射构造方法测试
 *
 * @author 温柔一刀
 * @create 2018-05-26 21:50
 **/
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

}
