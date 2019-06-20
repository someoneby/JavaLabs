package lambda;

import java.util.function.Function;

@FunctionalInterface
public interface FunctionComposer {
    Function<Double,Double> Compose(Function<Double,Double> f1, Function<Double,Double> f2);
}
