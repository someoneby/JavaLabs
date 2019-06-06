package stream;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream2 {

    public static IntStream StreamFilterOdd(ArrayList<Integer> array){
        return array.stream()
                .filter(value -> Integer.lowestOneBit(value) == 1)
                .mapToInt(x->x);
    }

    public static Stream<PeopleData> StreamObjectFilter(ArrayList<PeopleData> array){
        System.out.println("\n\nОтфильтровать массив объектов");
        return array.stream()
                .filter(value->value.getCreatedAt().isBefore(LocalDateTime.of(2019, Month.FEBRUARY, 1, 2, 27, 31)));
    }

    public static Stream<PeopleData> StreamObjectFilterLogged(ArrayList<PeopleData> array){
        System.out.println("\nОтфильтровать массив объектов, оставив только созданные ранее заданной даты и с установленным полем logged в true");
        return array.stream()
                .filter(value->value.getCreatedAt().isBefore(LocalDateTime.of(2019, Month.FEBRUARY, 1, 2, 27, 31)))
                .filter(value->value.isLogged());
    }

    public static IntStream FilterFirstHalf(int[] array){
        System.out.println("\nВерните стрим, состоящий только из второй половины элементов входного массива");
        return Arrays.stream(array)
                .skip(array.length/2);
    }

    public static Stream StreamOneElementPozition(int[] array, int pozition){
        System.out.println("Верните стрим, из одного элемента на заданной позиции. В случае отсутствии такого  элемента верните 42");
        int x = array.length<pozition? 42: array[pozition];
        return Stream.of(x);
    }

    public static Stream StringPlusOutdated(ArrayList<String> arrayList){
        System.out.println("\n\nДобавьте к каждой строке массива “_outdated");
        return arrayList.stream()
                .map(string->string+"_outdated");
    }

    public static Stream<SomeData> ObjectStringPlusOutdated(ArrayList<SomeData> arrayList){
        System.out.println("\nДобавьте “_outdated” в случае если оно отсутсвует, перед расширением, к name для объектов, у которых date менее текущей даты");
        LocalDateTime time = LocalDateTime.of(2019,3,13,13,13);
        return arrayList.stream()
                .map(value-> {
                    if (value.getDate().isBefore(time)) {
                        if (!value.getName().contains("_outdated")) {
                            if (value.getName().contains(".txt") )
                                value.setName(value.getName().replaceAll(".txt", "_outdated.txt"));
                            else if(value.getName().contains(".exe"))
                                value.setName(value.getName().replaceAll(".exe", "_outdated.exe"));
                        }
                    }
                    return value;
                });
    }

    public static IntStream FilterSecondHalf(int[] array){
        System.out.println("\nВерните стрим, состоящий только из первой половины элементов входного массива");
        return Arrays.stream(array)
                .limit(array.length/2);
    }

    public static IntStream ShowMiddleThirdPart(int[] array){
        System.out.println("\n\nВерните стрим, состоящий только из трети элементов по середине");
        return Arrays.stream(array)
                .skip(array.length/3)
                .limit(array.length/3);
    }

    public static IntStream Show2ElementsBeforeMid(int[] array){
        System.out.println("\n\nВерните стрим, состоящий из двух элементов перед серединой");
        return Arrays.stream(array)
                .skip(array.length/2-2)
                .limit(2);
    }

    public static IntStream DescendingOrderStream(int[] array){
        System.out.println("\n\nВерните отсортированный стрим в порядке убывания");
        return Arrays.stream(array)
                .map(value -> value*-1)
                .sorted()
                .map(value -> value*-1);
    }

    public static Stream<SomeData> SortSomeDataStream(ArrayList<SomeData> array){
        System.out.println("\n\nВерните отсортированный стрим SomeData");
        return array.stream()
                .sorted((a,b)->b.compareTo(a));
    }

    public static Stream StringCollectionSorted(ArrayList<String> array){
        System.out.println("\nОтсортировать коллекцию строк по алфавиту и убрать дубликаты");
        return array.stream()
                .distinct()
                .sorted((a,b)-> a.compareTo(b));
    }

    private static Comparator<String> comparator = (string1, string2) -> {
        int value1 = Integer.parseInt(string1.substring(string1.indexOf("_")+1));
        int value2 = Integer.parseInt(string2.substring(string2.indexOf("_")+1));
        return value2-value1;
    };

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static Stream SortedStringArray(String[] arrayOfInts){
        System.out.println("\n\nОтсортировать по числу по убыванию, исключить повторения (чисел). Вернуть стрим в формате <префикс>_<число>");
        return Arrays.stream(arrayOfInts)
                .sorted((string1,string2)->comparator.compare(string1,string2))
                .filter(distinctByKey(string -> string.substring(string.indexOf("_")+1)));
    }

    public static Stream ListOfLists(ArrayList<ArrayList<String>> arrayList){
        System.out.println("\n\nОбъедините List Listов в один Stream");
        return arrayList.stream()
                .reduce(new ArrayList<>(),(array1,array2)->{
                    array1.addAll(array2);
                    return array1;
                })
                .stream();
    }

    public static ArrayList<String> StreamSplitedString(String[] arrayList){
        System.out.println("\n\nРазрезка строк и сборка их в массиве");
        return Arrays.stream(arrayList)
                .map(string -> new ArrayList<>(Arrays.asList(string.split(":"))))
                .reduce(new ArrayList<>(),(array1,array2)->{
                    array1.addAll(array2);
                    return array1;
                });
    }

    public static IntStream StreamDistinct(int[] array){
        System.out.println("\nСтрим массива без дубликатов");
        return Arrays.stream(array)
                .distinct();
    }

    public static IntStream IntStreamCreator(){
        System.out.println("\n\nСоздание стрима натуральных чисел");
        return IntStream.of(12,23,25,26);
    }

    public static DoubleStream DoubleStreamCreator(){
        System.out.println("\n\nСоздание стрима чисел с плавающей точкой двойной точности");
        return DoubleStream.of(23.23,24.256,23.1);
    }


}
