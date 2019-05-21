package reflection;

//public class SimpleClass extends SuperSimpleClass implements StandartInterface {
public class SimpleClass extends SuperSimpleClass {
    private int number;
    private static String str;
    protected final String hello = "Hello!";

    public SimpleClass() {
        this.number = 20;
    }

    private SimpleClass(int number) {
        this.number = number;
    }

    public int GetNumber() {
        return number;
    }

    private void SetNumber(int number) {
        this.number = number;
    }

    public void Show(){
        System.out.println(hello+" String is: "+str+", and number is: "+number);
    }

    public void SayGoodBye() {
        System.out.println(goodbye+"!");
    }
}
