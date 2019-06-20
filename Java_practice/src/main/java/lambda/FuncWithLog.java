package lambda;

import java.util.function.Consumer;
import java.util.function.Function;

@FunctionalInterface
public interface FuncWithLog {
    Consumer<Integer> intercept(Function<Integer, Integer> func1, Consumer<Integer> func2);
}
