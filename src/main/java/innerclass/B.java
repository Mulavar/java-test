package innerclass;

public class B extends A {

    public static void main(String[] args) {
        print(new InnerA());
    }

    public void printA() {
        print(new InnerA());
    }

    public static void print(InnerA a) {

    }
}
