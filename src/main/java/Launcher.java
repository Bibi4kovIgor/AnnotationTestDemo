import anntation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Launcher {
    public static void main(String[] args) throws ClassNotFoundException {
        int testsCount = 0;
        int passedCount = 0;

        Class<?> testClass = Class.forName("testsimple.Sample");

        for (Method method : testClass.getMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                testsCount++;
                try {
                    method.invoke(new Object());
                    System.out.printf("No available exception %s\n", method);
                } catch (InvocationTargetException e) {
                    Throwable exc = e.getCause();
                    Class<? extends Throwable> excType = method.getAnnotation(Test.class).value();
                    if(excType.isInstance(exc)) {
                        passedCount++;
                    } else {
                        System.err.printf("Test was not passed %s." +
                                "Expected test %s\n" +
                                " There was raised problem %s\n",method, excType.getName(), exc);
                    }

                } catch (Exception e) {
                    System.err.println("Invalid Test\n" + e.getMessage());
                }
            }
        }
        System.out.printf("Tests at all: %d, Passed %d, Failed %d\n", testsCount, passedCount, testsCount - passedCount);

    }
}
