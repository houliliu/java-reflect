public class Main {

    public static void main(String[] args) {
        System.out.println("reflect !");
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


    }
}
