package stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream1 {

    public static Stream ListStream(ArrayList arrayList){
        System.out.print("\n\nСоздание стрима из List\n ");
        return arrayList.stream();
    }

    public static Stream ValuesStream(){
        System.out.print("\n\nСоздание стрима перечислением\n ");
        return Stream.of(23,24,25,26);
    }

    public static IntStream ArrayStream(int[] array){
        System.out.print("\n\nСоздание стрима из массива\n ");
        return Arrays.stream(array);
    }

    public static Stream FileStream() throws IOException {
        System.out.print("\nСоздание стрима из файла\n");
        return Files.lines(Paths.get("file.txt"));
    }

    public static Stream StringStream(String string){
        System.out.print("\nСоздание стрима из строки\n ");
        return string.chars().mapToObj(str -> (char)str);
    }

    public static Stream ParallelStream(){
        System.out.println("\n\nСоздание параллельного стрима");
        return Stream.of(23,24,25,26).parallel();
    }

    public static Stream EndlessSquaredStream(){
        System.out.println("\n\nСоздание бесконечного стрима, который возводит каждое предыдущее значение в квадрат (max 20)");
        int max = 5, start = 2;
        return Stream.iterate(start,value->value*value)
                .limit(max);
    }

    public static Stream<int[]> FibonacciStream(){
        int max = 20;
        System.out.println("\n\nСоздание бесконечного стрима, который возвращает последовательность фибоначчи (max 20)");
        return Stream.iterate(new int[]{0,1}, value-> new int[]{value[1],value[1]+value[0]})
                .limit(max);
    }

}
