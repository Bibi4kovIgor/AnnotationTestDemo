import anntation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Launcher {
    public static void main(String[] args) throws ClassNotFoundException {
        int testsCount = 0;
        int passedCount = 0;

        Class<?> testClass = Class.forName("testsimple.SampleImplementation");

        for (Method method : testClass.getMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                testsCount++;
                try {
                    method.invoke(new Object());
                    System.out.printf("Return type %s, from method %s\n",
                            method.getAnnotation(Test.class).returnType(),
                            method);
                } catch (InvocationTargetException e) {
                    Throwable exc = e.getCause();
                    Class<? extends Throwable> excType = method.getAnnotation(Test.class).value();
                    if(excType.isInstance(exc)) {
                        passedCount++;
                        System.out.printf("Return type of method %s is %s \n", method.getName(),
                                method.getAnnotation(Test.class).returnType());
                    } else {
                        System.err.printf("""
                                Test was not passed %s.Expected test %s\s
                                 There was raised problem %s
                                """,method, excType.getName(), exc);
                    }

                } catch (Exception e) {
                    System.err.println("Invalid Test\n" + e.getMessage());
                }
            }
        }
        System.out.printf("Tests at all: %d, Passed %d, Failed %d\n", testsCount, passedCount, testsCount - passedCount);
    }
}
