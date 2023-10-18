package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassDemo {

    List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws NoSuchFieldException, ClassNotFoundException {
        System.out.println(Long.TYPE==long.class);
        System.out.println(Long.class==long.class);
        showClassName(int.class);
        showClassName(Integer.class);
        showClassName(int[].class);
        showClassName(Integer[].class);
        showClassName(boolean[].class);
        showClassName(Boolean[].class);
        showClassName(short[].class);
        showClassName(Short[].class);
        showClassName(float[].class);
        showClassName(double[].class);
        showClassName(ClassDemo.class);

        Class<ClassDemo> classDemoClass = ClassDemo.class;
        Field list = classDemoClass.getDeclaredField("list");
        Type[] actualTypeArguments = ((ParameterizedType) list.getGenericType()).getActualTypeArguments();
        for (Type type: actualTypeArguments) {
            System.out.println(type.getTypeName());
        }

        for (Method method : classDemoClass.getDeclaredMethods()) {
            System.out.println(method);
            System.out.println(Arrays.toString(method.getParameterTypes()));
        }
    }

    public static void varMethod(int... args) {

    }

    public static void showClassName(Class<?> clazz) {
        System.out.println("-------------------------------");
        System.out.println("[getName]: " + clazz.getName());
        System.out.println("[getCanonicalName]: " + clazz.getCanonicalName());
        System.out.println("[getSimpleName]: " + clazz.getSimpleName());
    }
}
