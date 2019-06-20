package lambda;


import java.awt.image.AreaAveragingScaleFilter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {

/**   Вернуть лямбду, которая печатает “Hello world!” */
        System.out.println("\nВернуть лямбду, которая печатает “Hello world!”");
        Consumer fPrintHelloWorld = string-> System.out.println(string);
        fPrintHelloWorld.accept(" Hello world!");


/**   Вернуть лямбду, которая поприветствует того, чье имя будет передано первым аргументом */
        System.out.println("\nВернуть лямбду, которая поприветствует того, чье имя будет передано первым аргументом");
        Consumer fSayHelloTo = name-> System.out.print(" Hello, "+name);
        fSayHelloTo.accept("Yan");


/**   Вернуть лямбду, которая принимает в качестве аргумента предыдущую лямбду, и добавляет вывод
 *    “Have a nice day!” после выполнения лямбды */
        System.out.println("\n\nВернуть лямбду, которая принимает в качестве аргумента предыдущую лямбду, и добавляет вывод “Have a nice day!”");
        Function<Consumer, Consumer> fWishNiceDay = (sayHi)-> {
            return sayHi.andThen((name)-> System.out.println(". Have a nice day!"));
        };
        fWishNiceDay.apply(fSayHelloTo).accept("Yan");


/**   Вернуть лямбду, которая возвращает текущее время */
        System.out.println("\n\nВернуть лямбду, которая возвращает текущее время");
        Supplier<LocalTime> timeNow = () ->  LocalDateTime.now().toLocalTime();
        System.out.println(" Time now: "+timeNow.get());


/**   Вернуть лямбду, которая проверяет строку на наличие в ней email. Под email понимать любую строку,
 *    которая ввыглядит как <любые символы>@<любые символы>.<любые символы>  */
        System.out.print("\n\nВернуть лямбду, которая проверяет строку на наличие в ней email.");
        String email = "www.v.skvere@hram.net";

        Predicate<String> containsEmail = (String str) -> {
                if(!str.contains("@") || !str.contains(".") || str.indexOf("@")<1 || (str.lastIndexOf(".") - str.indexOf("@") <1))
                    return false;
                return true;
            };

        if(containsEmail.test(email) )
            System.out.println("\nIt's email");
        else
            System.out.println("\nIt isn't email");


/**   К предыдущей лямбде добавить проверку на длину строки (> 10)  */
        System.out.print("\nК предыдущей лямбде добавить проверку на длину строки (> 10)");

        Predicate<String> emailLengthMoreThen10 = (emailStr) -> emailStr.length()>10;

        if(containsEmail.and(emailLengthMoreThen10).test(email))
            System.out.println("\nIt's right email");
        else
            System.out.println("\nIt isn't right email");


/**   К предыдущей лямбде добавить отрицание  */
        System.out.print("\nК предыдущей лямбде добавить отрицание");

        if(containsEmail.and(emailLengthMoreThen10).negate().test(email))
            System.out.println("\nIt's right email");
        else
            System.out.println("\nIt isn't right email");


/**   Вернуть лямбду, которая проверяет число на принадлежность к ряду Фибоначчи.
 *    Лямбда должна запоминать ранее вычисленные значения */
        System.out.println("\nВернуть лямбду, которая проверяет число на принадлежность к ряду Фибоначчи");

        Supplier<Function<Integer,Boolean>> Fibonachi = ()->{
            ArrayList<Integer> fibArray = new ArrayList<Integer>(Arrays.asList(0,1,2));
            return (value) -> {
                while(value>fibArray.get(fibArray.size()-1)){
                    fibArray.add(fibArray.get(fibArray.size()-1) + fibArray.get(fibArray.size()-2));
                    System.out.println(" Считаю ряд");
                }
                if(fibArray.contains(value))
                    return true;

                return false;
            };
        };
        Function<Integer,Boolean> FibFunck = Fibonachi.get();

        for (int i=10;i>2;i-=3) {
            if (FibFunck.apply(i))
                System.out.println(" "+i + " принадлежит фибоначи");
            else
                System.out.println(" "+i + " не принадлежит фибоначи");
        }


/**   Вернуть лямбду, которая возвращает сумму двух входных аргументов  */
        System.out.println("\nВернуть лямбду, которая возвращает сумму двух входных аргументов");

        BiFunction<Double, Double, Double> Summator = (x1,x2)->{
            return x1+x2;
        };
        double[] forSum = {4,6.205};

        System.out.println(" "+forSum[0]+"+"+forSum[1]+"="+Summator.apply(forSum[0],forSum[1]));


/**   К предыдущей лямбде добавить возведение в квадрат  */
        System.out.println("\nК предыдущей лямбде добавить возведение в квадрат");

        Function<Double,Double> Squared = (x1)->{
            return x1*x1;
        };
        System.out.println(" Квадрат суммы = "+Summator.andThen(Squared).apply(forSum[0],forSum[1]));


/**   Вернуть лямбду, которая возвращает сумму трех входных аргументов  */
        System.out.println("\nВернуть лямбду, которая возвращает сумму трех входных аргументов: 2,5,8");
        SumOfArguments SumOfArray = (x1,x2,x3)-> x1+x2+x3;
        System.out.println(SumOfArray.Summator(2,5,8));


/**   Вернуть лямбду, которая возводит число в квадрат  */
        System.out.println("\nВернуть лямбду, которая возводит число в квадрат");
        System.out.println(" Было 8, стало "+Squared.apply(8.0));


/**   Добавить к предыдущей лямбде инкремент перед  */
        System.out.println("\nДобавить к предыдущей лямбде инкремент перед");
        Function<Double, Double> Incrementor = x-> ++x;
        System.out.println(" После инкремента перед "+Squared.compose(Incrementor).apply(8.0));


/**   Добавить к предыдущей лямбде декремент после  */
        System.out.println("\nДобавить к предыдущей лямбде декремент после");
        Function<Double, Double> Decrementor = x-> x--;
        System.out.println(" После декремента после "+Squared.compose(Incrementor).andThen(Decrementor).apply(8.0));


/**   Вернуть лямбду, которая решает уравнение x^2 + 3x – 1 для заданного x  */
        System.out.println("\nВернуть лямбду, которая решает уравнение x^2 + 3x – 1 для заданного x");
        Function<Double,Double> Uravnenie = x->{
            return x*x+3*x-1;
        };
        System.out.println(" При х="+6+" x^2+3x-1 = "+Uravnenie.apply(6.0));


/**   Вернуть лямбду, которая решает уравнение (x^2 + 3x – 1)^2 + 3* (x^2 + 3x – 1) -1  для заданного x  */
        System.out.println("\nВернуть лямбду, которая решает уравнение (x^2 + 3x – 1)^2 + 3* (x^2 + 3x – 1) -1  для заданного x");
        Function<Double,Double> Uravnenie2 = x->{
            Double result = Uravnenie.apply(x);
            return result*result + 3*result-1;
        };
        System.out.println(" При х=6 (x^2+3x-1)^2+3*(x^2+3x-1)-1 = "+Uravnenie2.apply(6.0));


/**   Вернуть лямбду, которая решает уравнение x*x*x + y*y*y + z*z*z + u*u*u + v*v*v для заданных (x,y,z,u,v)  */
        System.out.println("\nВернуть лямбду, которая решает уравнение x*x*x + y*y*y + z*z*z + u*u*u + v*v*v для заданных (x,y,z,u,v)");
        Uravnenie3 Uravnenie3 = (x,y,z,u,v)-> x*x*x+y*y*y+z*z*z+u*u*u+v*v*v;
        System.out.println(" x=4, y=5, z=2, u=1, v=0 Уравнение = "+Uravnenie3.FUravnenie3(4,5,2,1,0));


/**   Вернуть лямбду, которая возвращает лямбду, возводящую в квадрат по заданной степени  */
        System.out.println("\nВернуть лямбду, которая возвращает лямбду, возводящую в квадрат по заданной степени");

        Function<Integer,Function<Integer,Integer>> NumberToPower = (power)->{
            return (number)->{
                int total = number;
                for(int i=1;i<power;i++)
                    total*=number;
                return total;
            };
        };
        System.out.println(" 2^10 = "+NumberToPower.apply(10).apply(2));


/**   Вернуть лямбду, которая производит композицию двух функций. (f1,f2) => f2(f1(x))  */
        System.out.println("\nВернуть лямбду, которая производит композицию двух функций. (f1,f2) => f2(f1(x)) ");
        FunctionComposer FunctionComposer = (f1,f2)->{
            return (value)-> f2.apply(f1.apply(value));
        };
        System.out.println(" x=10, x->x*2, x->x-1 = "+FunctionComposer.Compose(x->x*2,x->x-1).apply(10.0));
        System.out.println(" x=10, x->x*x, x->x-10, x->x/2, x->x+1 = "+FunctionComposer.Compose(FunctionComposer.Compose(x->x*x, x->x-10), FunctionComposer.Compose(x->x/2, x->x+1)).apply(10.0));


/**   Вернуть лямбду, которая вернет полином, основанный на переданных коэффициентах  */
        System.out.println("\nВернуть лямбду, которая вернет полином, основанный на переданных коэффициентах ");

        Function<ArrayList<Integer>,Function<Integer,Integer>> Polinom = (arrayMod)->{
            return (value)->{
                int result = 0;
                for(int i=0;i<arrayMod.size();i++){
                    double tempValue = 1;
                    for(int j=i+1;j<arrayMod.size();j++)
                        tempValue*=value;
                    result += arrayMod.get(i)*tempValue;
                }
                return result;
            };
        };
        ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(2,0,0,7,3));
        System.out.println(" 2*x^4+1*x^3+1*x^2+7x+3="+Polinom.apply(temp).apply(2));


/**   Вернуть лямбду, которая кеширует результат некоторой функции, которая является входных аргументом  */
        System.out.println("\nВернуть лямбду, которая кеширует результат некоторой функции, которая является входных аргументом ");

        Supplier<Function<Supplier,Double>> FuncToCash = ()->{
            ArrayList<Double> arrayList = new ArrayList<>();
            return func->{
                double result = (double) func.get();
                arrayList.add(result);
                System.out.println(arrayList);
                return result;
            };
        };
        Function<Supplier,Double> CashFunc = FuncToCash.get();

        CashFunc.apply(()->Math.random());
        CashFunc.apply(()->Math.random());
        CashFunc.apply(()->Math.random());


/**   Вернуть лямбду, которая запускает переданную функцию f, и в случае исключения перезапускает ее n раз  */
        System.out.println("\nВернуть лямбду, которая запускает переданную функцию f, и в случае исключения перезапускает ее n раз\n f=7/0, n=5 ");

        Supplier<Function<Supplier,Boolean>> FuncExept = ()->{
            int count=0;
                return func -> {
                    try {
                        func.get();
                    } catch (Exception exept) {
                        System.out.println("  Fail.");
                        return false;
                    }
                    return true;
                };
        };
        Function<Supplier,Boolean> FuncExeptCount = func->{
            int count = 5;
            boolean result;
            do{
                result = FuncExept.get().apply(func);
                count--;
                if(count==0)
                    break;
            }while(!result);
            return result;
        };

        FuncExeptCount.apply(()->7/0);


/**   Вернуть лямбду, которая перед и после запуска переданной функции f, вызывает функцию логгера logFunc  */
        System.out.println("\nВернуть лямбду, которая перед и после запуска переданной функции f, вызывает функцию логгера logFunc ");

        FuncWithLog FuncWithLog = (func1,func2)->{
            return value->{
                System.out.print("Before: ");
                func2.accept(value);
                value = func1.apply(value);
                System.out.print("After: ");
                func2.accept(value);
            };
        };

        FuncWithLog.intercept(x->x+1, System.out::println).accept(10);


/**   Вернуть лямбду, представляющую из себя последовательность, которая начинается с переданного x  */
        System.out.println("\nВернуть лямбду, представляющую из себя последовательность, которая начинается с переданного x ");

        Function<Long,Supplier<Long>> f = (value)->{
            long[]  numberNow = {value};
            return ()->{
                return numberNow[0]++;
            };
        };

        Supplier<Long> StartFrom100 = f.apply(100L);
        System.out.println(StartFrom100.get());
        System.out.println(StartFrom100.get());
        System.out.println(StartFrom100.get());


    }
}









