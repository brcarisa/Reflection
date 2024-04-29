package edu.school21.program.main;

import edu.school21.program.classes.Car;
import edu.school21.program.classes.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        Car car = new Car();
        System.out.println("Classes:");
        System.out.println("\t" + car.getClass().getSimpleName());
        System.out.println("\t" + user.getClass().getSimpleName());
        System.out.println("Enter class name:");
        String choise = scanner.nextLine();
        if(choise.equals("User")) {
            userProcessing(user);
            System.out.println("Let's create an object");
            objectCreation(false, scanner, user, car);
            try {
                updateInfo(car, user, scanner);
            }catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if(choise.equals("Car")) {
            carProcessing(car);
            System.out.println("Let's create an object");
            objectCreation(true, scanner, user, car);
            try {
                updateInfo(car, user, scanner);
            }catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }



    }


    private static void updateInfo(Car car, User user, Scanner scanner) throws NoSuchFieldException, IllegalAccessException {
        if(car != null) {
            System.out.println("Enter name of the field for changing:");
            String fieldName = scanner.nextLine();
            String newValue = scanner.nextLine();
            Field field = car.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(car, newValue);
            System.out.println("Updated object: " + car.toString());
        } else {

        }
    }



    private static void objectCreation(boolean isCar, Scanner scanner, User user, Car car){
        if(isCar){
            System.out.println("BrandName:");
            String brandName = scanner.nextLine();
            System.out.println("Model:");
            String model = scanner.nextLine();
            System.out.println("Weight:");
            int weight = Integer.parseInt(scanner.nextLine());
            car = new Car(brandName, model, weight);
            System.out.println("Object created: " + car.toString());
        } else {
            System.out.println("firstName:");
            String firstName = scanner.nextLine();
            System.out.println("lastName:");
            String lastName = scanner.nextLine();
            System.out.println("height:");
            int height = Integer.parseInt(scanner.nextLine());
            user = new User(firstName, lastName, height);
            System.out.println("Object created: " + user.toString());
        }
    }




    private static void carProcessing(Car car) {
        Field[] fields = car.getClass().getDeclaredFields();
        Method[] methods = car.getClass().getDeclaredMethods();
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
    }

    private static void userProcessing(User user) {
        Field[] fields = user.getClass().getDeclaredFields();
        Method[] methods = user.getClass().getDeclaredMethods();
        System.out.println("Fields:");
        for(Field field : fields) {
            System.out.println("\t" + field.getName());
        }
        System.out.println("Methods:");
        for(Method method : methods) {
            System.out.print("\t" + method.getReturnType().getSimpleName() + " " + method.getName() + "(");
            Class<?>[] parameterTypes = method.getParameterTypes();
            for(Class<?> parameterType : parameterTypes) {
                System.out.print(parameterType.getSimpleName());
            }
            System.out.println(")");
        }
    }
}