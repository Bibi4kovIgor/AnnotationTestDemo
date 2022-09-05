package testsimple;

import anntation.Test;

public class Sample {
    @Test(IllegalArgumentException.class)
    public static void m1(){
        throw new IllegalArgumentException();
    }

    public static void m2(){}

    @Test(RuntimeException.class)
    public static void m3() {
        throw new RuntimeException("Crash");
    }

    public void m4() {};

    @Test(IndexOutOfBoundsException.class)
    public static void m5() {
        throw new ArithmeticException();
    };

}
