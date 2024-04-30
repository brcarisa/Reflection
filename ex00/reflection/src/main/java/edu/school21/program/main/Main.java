package edu.school21.program.main;

import edu.school21.program.classes.Car;
import edu.school21.program.classes.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    private static Object object;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
        System.out.println("Classes:");
        System.out.println("\t" + "Car");
        System.out.println("\t" + "User");
        System.out.println("Enter class name:");
        String choice = scanner.nextLine();
        if(choice.equals("User")) {
            object = new User();
        } else if (choice.equals("Car")) {
            object = new Car();
        }
        processing(object, scanner);
        update(object, scanner);
        methodCall(object, scanner);
    }

    private static void processing(Object object, Scanner scanner) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        Method[] methods = object.getClass().getDeclaredMethods();
        System.out.println("Fields:");
        for(Field field : fields) {
            System.out.println("\t" + field.getName());
        }
        System.out.println("Methods:");
        for(Method method : methods) {
            System.out.print("\t" + method.getReturnType().getSimpleName()+ " " + method.getName() + "(");
            Class<?>[] parameterTypes = method.getParameterTypes();
            for(Class<?> parameterType : parameterTypes) {
                System.out.print(parameterType.getSimpleName());
            }
            System.out.println(")");
        }
        System.out.println("Let’s create an object.");
        for(Field field : fields) {
            System.out.println(field.getName());
            field.setAccessible(true);
            if(field.getType() == int.class) {
                int value = scanner.nextInt();
                field.set(object, value);
            } else if (field.getType() == String.class){
                String value = scanner.nextLine();
                field.set(object, value);
            }
        }
        System.out.println("Object created: " + object.toString());
    }

    private static void update(Object object, Scanner scanner) throws NoSuchFieldException, IllegalAccessException {
        System.out.println("Enter name of the field for changing:");
        String fieldName = scanner.next();
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        System.out.println("Enter " + field.getType().getSimpleName() + " value:");
        if(field.getType() == int.class) {
            int value = Integer.parseInt(scanner.next());
            field.set(object, value);
        } else if (field.getType() == String.class) {
            String value = scanner.next();
            field.set(object, value);
        }
        System.out.println("Object updated: " + object.toString());
    }

    private static void methodCall(Object object, Scanner scanner) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("Enter name of the method for call:");
        String[] methodName = scanner.next().split("\\(");
        Method method = object.getClass().getDeclaredMethod(methodName[0], int.class);
        method.setAccessible(true);
        if(method.getReturnType() == void.class && method.getParameterTypes().length == 0) {
            System.out.println("Method " + methodName + " returned void.");
            method.invoke(object);
        } else {
            System.out.println("Enter int value:");
            int value = Integer.parseInt(scanner.next());
            int res = (int)method.invoke(object, value);
            System.out.println("Method returned: \n" + res);
        }
    }


}