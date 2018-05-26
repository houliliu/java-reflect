/**
 * ${DESCRIPTION}
 *
 * @author 温柔一刀
 * @create 2018-05-26 21:01
 **/
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
