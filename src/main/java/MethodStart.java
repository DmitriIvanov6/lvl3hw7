import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class MethodStart {


    public static void startTest(Class TestClass) {
        ArrayList<Method> methods = new ArrayList<>();
        Method[] methodsAll = TestClass.getDeclaredMethods();
        for (Method m : methodsAll) {
            if (m.isAnnotationPresent(Test.class)) {
                methods.add(m);
            }
        }
        methods.sort(Comparator.comparingInt((Method i) -> i.getAnnotation(Test.class).priority()).reversed());
        beforeAfter(methods, methodsAll);
        for (Method m : methods) {
            try {
                m.invoke(null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

        }
    }


    public static void beforeAfter(ArrayList<Method> methods, Method[] methodsAll) {
        int checkBefore = 0;
        int checkAfter = 0;
        for (Method m : methodsAll) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                checkBefore++;
            }
            if (m.isAnnotationPresent(AfterSuite.class)) {
                checkAfter++;
            }
        }
        if (checkBefore > 1 || checkAfter > 1 ) {
            throw new RuntimeException("Должно быть не более 1 BeforeSuite и 1 AfterSuite");
        } else {
            for (Method m : methodsAll) {
                if (m.isAnnotationPresent(BeforeSuite.class)) {
                    methods.add(0, m);
                }
                if (m.isAnnotationPresent(AfterSuite.class)) {
                    methods.add(m);
                }
            }
        }
    }
}
