import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 需要实现的接口
interface ISubject {
    public void hello(String str);
}

// 实际的需要被代理的对象
class SubjectImpl implements ISubject {
    public void hello(String str) {
        System.out.println("SubjectImpl.hello(): " + str);
    }
}

// Handler对象
class Handler implements InvocationHandler {
    private Object subject;
    public Handler(Object subject) {
        this.subject = subject;
    }

    public Object invoke(Object object, Method method, Object[] args) throws Throwable {
        System.out.println("before!");
        method.invoke(this.subject, args);
        System.out.println("after!");
        return null;
    }
}


public class DynamicProxy {
    public static void main(String[] args) {
        SubjectImpl subject = new SubjectImpl();
        InvocationHandler tempHandler = new Handler(subject);

// 创建代理
        ISubject iSubject = (ISubject) Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(), new Class<?>[] {ISubject.class}, tempHandler);
        iSubject.hello("world!");
    }
}