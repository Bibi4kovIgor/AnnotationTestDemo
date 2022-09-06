package testsimple;

import anntation.Test;
import org.jetbrains.annotations.Nullable;

public class SampleImplementation implements Sample {

    @Nullable
    private String field = "";

    @Test(IllegalArgumentException.class)
    public static void m1(){
        throw new IllegalArgumentException();
    }

    public static void m2(){}

    @Test(RuntimeException.class)
    public static void m3() {
        throw new RuntimeException("Crash");
    }

    @Test(value = Exception.class, returnType = "void")
    public static void m7() {
    }


    @SuppressWarnings("unused")
    public void m4() {};

    @Test(IndexOutOfBoundsException.class)
    public static void m5() {
        throw new ArithmeticException();
    };

    @Override
    public void someMethod() {

    }

}
