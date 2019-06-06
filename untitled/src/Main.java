import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {


    public static int OddElementSum(int[] array){
        ArrayList<Integer> intArray = new ArrayList(Arrays.asList(array));
        Object[] temp = StreamFilterOdd(intArray)
                .toArray();

        return Arrays.stream(temp)
                .mapToInt(value -> (Integer)value)
                .sum();
    }

    public static Stream<Integer> StreamFilterOdd(ArrayList<Integer> array){
        System.out.println("\n\nОтфильтровать массив, оставив только нечетные элементы");
        return array.stream()
                .filter(value -> Integer.lowestOneBit(value) == 1)
                .map(x->(int)x);
    }


    public static void main(String[] args) {

        int[] arrayInt = {32,23,123,213,56,67,89};

        /**        Вернуть сумму нечетных чисел*/
        System.out.println("\n\nВернуть сумму нечетных чисел");
        System.out.println("Сумма = "+OddElementSum(arrayInt));



    }
}
