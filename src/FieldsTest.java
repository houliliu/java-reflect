import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * ${DESCRIPTION}
 *
 * @author 温柔一刀
 * @create 2018-05-26 22:52
 **/
public class FieldsTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        System.out.println("反射 成员属性获取 ------------");

        Class className =Class.forName("ReflectTest");

        //1.获取Class对象
        //2.获取字段
        System.out.println("************获取所有公有的字段********************");
        Field[] fieldArray = className.getFields();
        for(Field f : fieldArray){
            System.out.println(f);
        }
        /**
         * 结果：
         * ************获取所有公有的字段********************
         * public java.lang.String ReflectTest.name
         */
        System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
        fieldArray = className.getDeclaredFields();
        for(Field f : fieldArray){
            System.out.println(f);
        }
        /**
         * 结果：
         *
         * ************获取所有的字段(包括私有、受保护、默认的)********************
         * public java.lang.String ReflectTest.name
         * private java.lang.String ReflectTest.des
         */

        System.out.println("*************获取公有字段**并调用***********************************");
        Field f = className.getField("name");
        System.out.println(f);
        //获取一个对象
        Object obj = className.getConstructor().newInstance();
        //为字段设置值
        f.set(obj, "znn");
        //验证
        ReflectTest reflectTest = (ReflectTest)obj;
        System.out.println("验证姓名：" + reflectTest.name);


        System.out.println("**************获取私有字段****并调用********************************");
        f = className.getDeclaredField("des");
        System.out.println(f);
        f.setAccessible(true);//暴力反射，解除私有限定
        f.set(obj, "des");
        System.out.println("验证des：" + reflectTest);
    }
}

