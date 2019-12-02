public class instance {

    static{
        System.out.println("static函数");
    }

    {
        System.out.println("非static方法");
    }

    instance(){
        System.out.println("构造函数");
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        instance.class.newInstance();
    }
}
