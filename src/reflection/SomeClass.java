package reflection;

public class SomeClass extends Superclass implements Interface1, Interface2{
    private int privateFields=10;
    protected int protectedFields=10;
    public int publicFields=10;
    public String name;
    
    public SomeClass() {
        name= "gf";
    }
    
    public SomeClass(int i, String s) {
        name= "gf";
    }
    
    static {
        System.out.println("Запущен static блок класса SomeClass");
    }
    
    public void print() {
        System.out.println("Запущен метод print");
    }
    
    public void print(String s) {
        System.out.println("Запущен метод print " + s);
    }
}
