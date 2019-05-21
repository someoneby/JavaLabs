package reflection;

import java.io.PrintStream;
import java.lang.reflect.*;
import java.util.Scanner;

public class Main {

    public static Class TakingObjectClass(Object object){
        return object.getClass();
    }

    public static void ClassInformation(Object object){
        // Modificator
        System.out.println("It has "+Modifier.toString(object.getClass().getModifiers())+" mod");

        // Fields
        Field[] fields = object.getClass().getDeclaredFields();
        System.out.println("Fields:");
        for(int i=0;i<fields.length;i++){
            System.out.println("  "+fields[i]);
        }

        // Methods
        Method[] methods = object.getClass().getDeclaredMethods();
        System.out.println("Methods:");
        for(int i=0;i<methods.length;i++){
            System.out.println("  "+methods[i]);
        }

        // Constructors
        Constructor[] constructors = object.getClass().getConstructors();
        System.out.println("Constructors:");
        for(int i=0;i<constructors.length;i++){
            System.out.println("  "+constructors[i]);
        }

        // Super class
        System.out.println("Super class:");
        System.out.println("  "+object.getClass().getSuperclass());
    }

    public static void InterfaceMethods (Object object){
        Class[] interfacesOfClass = object.getClass().getInterfaces();
        if(interfacesOfClass.length!=0){
            for(int i=0;i<interfacesOfClass.length;i++){
                System.out.println("Methods of "+interfacesOfClass[i].getSimpleName()+
                        " interface of class "+object.getClass().getSimpleName()+" are:");
                Method[] interfaceMethod = interfacesOfClass[i].getDeclaredMethods();
                for(int j=0;j<interfaceMethod.length;j++)
                    System.out.println("  "+interfaceMethod[j]);
            }
        }
        else
            System.out.println("This class don't implements interface.");
    }

    public static Object ObjectCreator(String ObjectName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class createdClass = Class.forName(ObjectName);
        Object simpleClass_2 = createdClass.newInstance();
        System.out.println("\nWe create "+simpleClass_2.getClass().getSimpleName()+" object.");
        return simpleClass_2;
    }

    public static void FieldSetter(Object object) throws NoSuchFieldException, IllegalAccessException {
        Scanner cin = new Scanner(System.in);
        System.out.println("\nPlease, choose the field that you need: ");
        Field[] fields1 = object.getClass().getDeclaredFields();
        for(Field field: fields1){
            System.out.println("   "+field);
        }

        System.out.println("Your choise: ");
        String tempName = cin.nextLine();
        Field choosenField = object.getClass().getDeclaredField(tempName);
        choosenField.setAccessible(true);
        System.out.println("The value of the field \""+tempName+"\" is: "+choosenField.get(object));
        System.out.println("Please, enter the new value: ");
        String value = cin.nextLine();

        Type type = choosenField.getType();
        String typeString = type.getTypeName();
        switch (typeString){
            case "int":
                choosenField.setInt(object, Integer.parseInt(value));
                break;
            case "java.lang.String":
                choosenField.set(object, value);
                break;
        }

        System.out.println("The value of the field \""+tempName+"\" is: "+choosenField.get(object)+" now.");
    }

    public static void MethodInvoke(Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Scanner cin = new Scanner(System.in);
        System.out.println("\nPlease, choose the method that you need: ");
        Method[] methods1 = object.getClass().getDeclaredMethods();
        for(Method method: methods1){
            System.out.println("   "+method);
        }

        System.out.println("Your choise: ");
        String tempName = cin.nextLine();
        Method choosenMethod = object.getClass().getDeclaredMethod(tempName);
        choosenMethod.setAccessible(true);
        choosenMethod.invoke(object);
    }

    public static void FieldsList(Object object){
        Field[] fields = object.getClass().getDeclaredFields();

        System.out.println("\nPublic fields:");
        for(Field field: fields){
            if(Modifier.isPublic(field.getModifiers()))
                System.out.println("   "+field);
        }

        System.out.println("\nPrivate fields:");
        for(Field field: fields){
            if(Modifier.isPrivate(field.getModifiers()))
                System.out.println("   "+field);
        }
    }

    public static void MethodList(Object object){
        Method[] methods = object.getClass().getDeclaredMethods();

        System.out.println("\nPublic methods:");
        for(Method method: methods){
            if(Modifier.isPublic(method.getModifiers()))
                System.out.println("   "+method);
        }

        System.out.println("\nPrivate methods:");
        for(Method method: methods){
            if(Modifier.isPrivate(method.getModifiers()))
                System.out.println("   "+method);
        }
    }

    public static void MethodWithParam(Object object) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        Method methodPrivate = object.getClass().getDeclaredMethod("SetNumber", int.class);
        methodPrivate.setAccessible(true);
        methodPrivate.invoke(object, 2);
        Field fieldNumber = object.getClass().getDeclaredField("number");
        fieldNumber.setAccessible(true);
        System.out.println("Number now: "+fieldNumber.get(object));
    }

    public static Object ConstructorWithParam() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructorPrivate = SimpleClass.class.getDeclaredConstructor(int.class);
        constructorPrivate.setAccessible(true);
        Object simpleClass_3 = constructorPrivate.newInstance(25);
        return  simpleClass_3;
    }

    public static void SayHello() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("hello");

        Field stringValue = "hello".getClass().getDeclaredField("value");
        stringValue.setAccessible(true);
        stringValue.set("hello", "bye".toCharArray());

        System.out.println("hello");
    }

//    public static void FalseIsTrue(int a, int b) throws NoSuchFieldException, IllegalAccessException {
//        if(a==b)
//            System.out.println("First condition is true");
//
//        Field intValue = Integer.class.getDeclaredField("value");
//        intValue.setAccessible(true);
//        intValue.set(1, 42);
//        System.out.println(1);
//
//        if(a==b)
//            System.out.println("Second condition is true");
//    }

    public static void main (String arg[]) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchMethodException, InvocationTargetException {


    /** /////////////////////////////
    //    Taking the object's class
    ////////////////////////////////
    */
        SimpleClass simpleClass = new SimpleClass();
        System.out.println("\"\\nName of class: \""+ TakingObjectClass(simpleClass).getSimpleName());


    /** /////////////////////////////
     //    Information about class
     ////////////////////////////////
     */
        ClassInformation(simpleClass);


    /** /////////////////////////////
     //    Methods of interface
     ////////////////////////////////
     */
        SuperSimpleClass superSimpleClass = new SuperSimpleClass();
        InterfaceMethods(superSimpleClass);


    /** /////////////////////////////
     //    Creation of object
     ////////////////////////////////
     */
    //      The name should be taken from command line parameters like "reflection.SimpleClass"
        Object simpleClass_2 = ObjectCreator(arg[0]);


    /** /////////////////////////////
     //    Take and set the value of the field
     ////////////////////////////////
     */
        FieldSetter(simpleClass_2);


    /** /////////////////////////////
     //    Choose the method and invoke
     ////////////////////////////////
     */
        MethodInvoke(simpleClass_2);


    /** /////////////////////////////
     //    List of public and private fields
     ////////////////////////////////
     */
        FieldsList(simpleClass_2);


    /** /////////////////////////////
     //    List of public and private methods
     ////////////////////////////////
     */
        MethodList(simpleClass_2);


    /** /////////////////////////////
     //    Private method with parameters
     ////////////////////////////////
     */
        MethodWithParam(simpleClass_2);


    /** /////////////////////////////
     //    Private constructor with parameters
     ////////////////////////////////
     */
        Object simpleClass_3 = ConstructorWithParam();

        Field fieldNumber = SimpleClass.class.getDeclaredField("number");
        fieldNumber.setAccessible(true);
        System.out.println("Number simpleClass3: "+fieldNumber.get(simpleClass_3));

    /** /////////////////////////////
     //    Say "bye" instead "hello"
     ////////////////////////////////
     */
        SayHello();
//
//    /** /////////////////////////////
//     //    True instead False
//     ////////////////////////////////
//     */
//        FalseIsTrue(1,42);


    }
}
