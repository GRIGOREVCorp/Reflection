package reflection;

import java.lang.reflect.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reflection {

    public static void main(String[] args) {
        //Введите название метода
    }
    
    // Описание:
    // Метод поллучает объект класса
    //
    public static void getClassObject() {
        String s = "some";
	Class getClass = s.getClass();
    }
    
    // Описание:
    // Метод вызывает класс, передаваемый
    // параметром в метод .forName()
    //
    public static void callClass() {
        try {
            Class.forName("reflection.SomeClass");
        } catch (ClassNotFoundException ex) { }
    }
    
    // Описание: 
    // Метод создает экземпляр класса
    // и вызывает у него его метод
    //
    public static void createInstanceObject() {
        try {
            Class someClass = Class.forName("reflection.SomeClass");
            SomeClass sc = (SomeClass) someClass.newInstance();
            sc.print();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException ex) { }
    }
    
    // Описание:
    // Метод исследования некоторых функций класса
    //
    // Функции получения:
    // .getName(), .getCanonicalName(), .getSimpleName(),
    // .getTypeName()
    //
    // Функции проверки:
    // .isInterface(), .isLocalClass()
    public static void methodsGet() {
        try {
            Class someClass = Class.forName("reflection.SomeClass");
            
            System.out.println("Имя класса: " + someClass.getName() + " .getName()");
            System.out.println("Каноническое имя класса: " + someClass.getCanonicalName() + " .getCanonicalName()");
            System.out.println("Название класса: " + someClass.getSimpleName() + " .getSimpleName()");
            System.out.println("Тип класса: " + someClass.getTypeName() + " .getTypeName()");
            
            System.out.println("Это интерфейс? " + someClass.isInterface() + " .isInterface()");
            System.out.println("Это локальный класс? " + someClass.isLocalClass() + " .isLocalClass()");
        } catch (ClassNotFoundException ex) { }
    }
    
    // Описание:
    // Получение модификаторов оъекта
    //
    public static void getModifiers() {
        try {
            Class someClass = Class.forName("reflection.SomeClass");
            
            int mods = someClass.getModifiers();
            if (Modifier.isPublic(mods))    System.out.println("Этот класс public");
            if (Modifier.isAbstract(mods))  System.out.println("Этот класс Abstract");
            if (Modifier.isFinal(mods))     System.out.println("Этот класс Final");
            if (Modifier.isInterface(mods)) System.out.println("Этот Interface");
            if (Modifier.isPrivate(mods))   System.out.println("Этот класс Private");
            if (Modifier.isProtected(mods)) System.out.println("Этот класс Protected");
            if (Modifier.isStatic(mods))    System.out.println("Этот класс Static");
        } catch (ClassNotFoundException ex) { }
    }
    
    // Описание:
    // Получение суперкласса объекта
    //
    public static void getSuperclass() {
        System.out.println(SomeClass.class.getSuperclass());
    }
    
    // Описание:
    // Получение всех наследуемых интерфейсов
    //
    public  static void getInterfaces() {
        Class[] interfaces = SomeClass.class.getInterfaces();
        for (Class cInter : interfaces) { System.out.println(cInter.getName()); }
    }
    
    // Описание:
    // Получение только public полей
    // получение всех полейсразу
    //
    // .getFields() - получение только открытых полей
    // .getDeclaredFields() - получение всех полей
    //
    public static void getFields() {
        Field[] publicFields = SomeClass.class.getFields();
	for (Field field : publicFields) { 
    		Class fieldType = field.getType();
    		System.out.println("Имя: " + field.getName());
    		System.out.println("Тип: " + fieldType.getName());		
	}
        System.out.println("\n");
	Field[] allFields = SomeClass.class.getDeclaredFields();
	for (Field field : allFields) { 
    		Class fieldType = field.getType(); 
    		System.out.println("Имя: " + field.getName()); 
    		System.out.println("Тип: " + fieldType.getName());		
	}
    }
    
    // Описание:
    // Получение и изменение значений
    // полей
    //
    public static void getOrSetValueFields() {
        try {
            SomeClass s = new SomeClass();
            Field field = s.getClass().getField("name");
            System.out.println(field.get(s));
            field.set(s, "newValue");
            System.out.println(field.get(s));
        } catch (IllegalAccessException | IllegalArgumentException | 
                SecurityException | NoSuchFieldException ex) {System.out.println(ex);}
    }
    
    // Описание:
    // Метод получения параметров всех 
    // конструкторов
    //
    public static void getParametersConstructorsClass() {
        try {
            Class someClass = Class.forName("reflection.SomeClass");
            Constructor[] constructors = someClass.getConstructors();
            for(Constructor constructor : constructors) {
                Class[] typeParameters = constructor.getParameterTypes();
                for(Class typeParameter : typeParameters)
                    System.out.println(typeParameter.getName());
            }
        } catch (ClassNotFoundException ex) { }        
    }
    
    // Описание:
    // Проверка наличия конструктора
    //
    public static void checkAvailabilityConstructor() {
        Class[] paramTypes2 = new Class[] { int.class, String.class };
        try {
            Constructor con = SomeClass.class.getConstructor(paramTypes2);
        } catch (SecurityException | NoSuchMethodException ex) {System.out.println("Конструктор не найден");}
    }
    
    // Описание:
    // Метод получения всех методов
    //
    public static void getMethod() {
        Method[] methods = SomeClass.class.getMethods();
        for (Method method : methods) {
            System.out.println("Имя: " + method.getName());
            System.out.println("Возвращаемый тип: " + method.getReturnType().getName());
            Class[] paramTypes = method.getParameterTypes();
            System.out.println("Типы параметров: ");
            for (Class paramType : paramTypes) {
                System.out.println(" " + paramType.getName());
            }
            System.out.println();
        }
    }
    
    // Описание:
    // Метода проверки наличия метода
    //
    public static void checkAvailabilityMethod() {
        SomeClass someClass = new SomeClass();
        Class[] paramTypes = new Class[] {String.class};
        try {
            Method method = someClass.getClass().getMethod("print", paramTypes);
        } catch (SecurityException | NoSuchMethodException ex) { System.out.println("Метод не найден " + ex);}
    }
    
    // Описание:
    // Метод вызова метода с заданными
    // параметрами
    //
    public static void callMethod() {
        SomeClass someClass = new SomeClass();
        Class[] paramTypes = new Class[] {String.class};
        Object[] args = new Object[] { new String("AddData")};
        try {
            Method method = someClass.getClass().getMethod("print", paramTypes);
            method.invoke(someClass, args);
        } catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException | 
                SecurityException | NoSuchMethodException ex) { System.out.println("Метод не найден " + ex);}
    }
    
    // Описание:
    // Метод получения доступа к private полю
    // игнорируя метод инкапсуляции
    //
    public static void getAccessToPrivateField() {
        try {
            SomeClass s = new SomeClass();
            Field field = s.getClass().getDeclaredField("privateFields");
            field.setAccessible(true);
            System.out.println(field.get(s));
            field.set(s, 12);
            System.out.println(field.get(s));
        } catch (IllegalAccessException | IllegalArgumentException | 
                SecurityException | NoSuchFieldException ex) {System.out.println(ex);}
    }
}
