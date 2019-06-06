package stream;

import java.io.IOException;
import java.time.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String arg[]) throws IOException {

/** 1. Создание стрима
 *
 */
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("One","Two","Three"));
        int[] arrayInt = {32,23,123,213,56,67,89};

/**        Создание стрима из List */
//      arrayList = {"One", "Two", "Three"}
        Stream1.ListStream(arrayList).forEach(value->System.out.print(value+" "));


/**        Создание стрима перечислением */
        Stream1.ValuesStream().forEach(value->System.out.print(value+" "));


/**       Создание стрима из массива */
//        int[] arrayInt = {32,23,123,213,56,67,89}
        Stream1.ArrayStream(arrayInt).forEach(value->System.out.print(value+" "));


//           Need JDK 11+
/**        Создание стрима из файла*/
        System.out.print("\n");
        Stream1.FileStream().forEach(element-> System.out.println(" "+element));


/**        Создание стрима из строки */
        Stream1.StringStream("Some string.").forEach(value->System.out.print(value+" "));


/**       Создание параллельного стрима */
        Stream1.ParallelStream().forEach(value->System.out.print(" "+value));


/**        Создание бесконечного стрима, который возводит каждое предыдущее значение в квадрат */
        Stream1.EndlessSquaredStream().forEach(value->System.out.print(" "+value));


/**        Создание бесконечного стрима, который возвращает последовательность фибоначчи */
        Stream1.FibonacciStream().forEach(value-> System.out.print(" "+value[0]));



/**     2. Конвейерные методы работы со стримом
 *
// */

        System.out.println("\n\n\n2. Конвейерные методы работы со стримом");

        ArrayList<Integer> intArray = new ArrayList(Arrays.asList(12,13,23,24));
        String[] arrayOfInts = {"24_323", "25_2342", "27_2342", "34_3434", "24_321"};
        String[] stringArrayText = {"1:2:3:", "foo:bar"};

        ArrayList<PeopleData> peopleArray = new ArrayList<>();
        peopleArray.add(new PeopleData("Ivan", "Ivanow", false, LocalDateTime.of(2019, Month.JANUARY, 15, 5, 17, 53)));
        peopleArray.add(new PeopleData("Ivan", "Ivanow", true, LocalDateTime.of(2019, Month.JANUARY, 16, 5, 17, 53)));
        peopleArray.add(new PeopleData("Alex", "Alex", false,   LocalDateTime.of(2019, Month.APRIL,8, 10, 55, 26)));
        peopleArray.add(new PeopleData("Peter", "Peter", true, LocalDateTime.of(2019, Month.FEBRUARY, 27, 2, 27, 31)));

        ArrayList<SomeData> userArray = new ArrayList<>();
        userArray.add(new SomeData(1,"Pete4kin", LocalDateTime.of(2019,3,20,17,35)));
        userArray.add(new SomeData(2,"Vase4kin", LocalDateTime.of(2019,2,20,17,26)));
        userArray.add(new SomeData(2,"Vase4kin", LocalDateTime.of(2019,2,20,17,26)));
        userArray.add(new SomeData(3,"Dimo4kin", LocalDateTime.of(2019,4,01, 17,35)));
        userArray.add(new SomeData(4,"Kate4kin", LocalDateTime.of(2019,3,20,17,25)));
        userArray.add(new SomeData(5,"file_outdated", LocalDateTime.of(2018,3,20,17,25)));
        userArray.add(new SomeData(6,"Kate4kin.txt", LocalDateTime.of(2018,3,20,17,25)));

        ArrayList<String> stringArrayList = new ArrayList<>(Arrays.asList(
                "Boxing Day", "New Year's Eve", "Christmas", "Christmas", "Hanukkah", "New Year's Day"
        ));

        ArrayList<String> arrayList1 = new ArrayList<>(Arrays.asList(" 1", " 2", " 3"));
        ArrayList<String> arrayList2 = new ArrayList<>(Arrays.asList(" 4", " 5", " 6"));
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>(Arrays.asList(arrayList1,arrayList2));

        ArrayList<Point> PointsArray = new ArrayList<Point>(Arrays.asList(
                new Point(0,0),
                new Point(2.4f, 3.7f),
                new Point(2.41f,3.9f),
                new Point(3f,3f),
                new Point(-2f,3.9f),
                new Point(-2f,-2f),
                new Point(10f,-10f)
        ));


/**        Отфильтровать массив, оставив только нечетные элементы */
//        intArray = {12,13,23,24}
        System.out.println("\nОтфильтровать массив, оставив только нечетные элементы");
        Stream2.StreamFilterOdd(intArray).forEach(value-> System.out.print(" "+value));


/**        Отфильтровать массив объектов (по полю createdAt, класс создайте сами,
 *         в качестве createdAt используйте LocalDateTime), оставив только созданные ранее заданной даты */
        Stream2.StreamObjectFilter(peopleArray).forEach(value->value.print());


/**        К классу из предыдущего примера добавьте поле logged типа Boolean. Отфильтровать массив объектов,
 *         оставив только созданные ранее заданной даты и с установленным полем logged в true*/
        Stream2.StreamObjectFilterLogged(peopleArray).forEach(value->value.print());


/**        Верните стрим, состоящий только из второй половины элементов входного массива */
//        int[] arrayInt = {32,23,123,213,56,67,89};
        Stream2.FilterFirstHalf(arrayInt).forEach(value -> System.out.print(" "+value));


/**        Верните стрим, из одного элемента на заданной позиции. В случае отсутствии такого  элемента верните 42.
 *         Запрещено использовать условный оператор. */
        System.out.println("\n");
        Stream2.StreamOneElementPozition(arrayInt, 2).forEach(value-> System.out.println(" Int at pozition \"2\": "+value));
        Stream2.StreamOneElementPozition(arrayInt, 20).forEach(value-> System.out.println(" Int at pozition \"20\": "+value));


/**        Верните стрим из массива, исключив дубликаты */
        Stream2.StreamDistinct(new int[]{23, 34, 23}).forEach(value-> System.out.print(" "+value));


/**        Добавьте к каждой строке массива “_outdated */
        Stream2.StringPlusOutdated(stringArrayList).forEach(element-> System.out.println(" "+element));


/**        Создайте класс с полями name типа String, date типа  LocalDate. Добавьте “_outdated” в случае
 *         если оно отсутсвует, перед расширением, к name для объектов, у которых date менее текущей даты */
        Stream2.ObjectStringPlusOutdated(userArray).forEach(value->value.print());


/**        Верните стрим, состоящий только из первой половины элементов входного массива */
//        int[] arrayInt = {32,23,123,213,56,67,89};
        Stream2.FilterSecondHalf(arrayInt).forEach(value -> System.out.print(" "+value));


/**        Верните стрим, состоящий только из трети элементов по середине */
//        int[] arrayInt = {32,23,123,213,56,67,89};
        Stream2.ShowMiddleThirdPart(arrayInt).forEach(value -> System.out.print(" "+value));


/**        Верните стрим, состоящий из двух элементов перед серединой */
//        int[] arrayInt = {32,23,123,213,56,67,89};
        Stream2.Show2ElementsBeforeMid(arrayInt).forEach(value -> System.out.print(" "+value));


/**        Верните отсортированный стрим в порядке убывания */
//        int[] arrayInt = {32,23,123,213,56,67,89};
        Stream2.DescendingOrderStream(arrayInt).forEach(value -> System.out.print(" "+value));


/**        Создайте класс с полями id типа Long, name типа String, date типа LocalDate. Верните отсортированный стрим */
        Stream2.SortSomeDataStream(userArray).forEach(value->value.print());


/**        Отсортировать коллекцию строк по алфавиту и убрать дубликаты */
        Stream2.StringCollectionSorted(stringArrayList).forEach(value -> System.out.print(" "+value));


/**        Дан массив строк по шаблону <префикс>_<число>. Отсортирвоат ьпо числу по убыванию,
 *         исключить повторения (чисел). Вернуть стрим в формате <префикс>_<число> */
//        String[] arrayOfInts = {"24_323", "25_2342", "27_2342", "34_3434", "24_321"};
        Stream2.SortedStringArray(arrayOfInts).forEach(value -> System.out.print(" "+value));


/**        Создайте натуральный стрим для целых чисел, для чисел с плавающей точкой двойной точности */
        Stream2.IntStreamCreator().forEach(value -> System.out.print(" "+value));
        Stream2.DoubleStreamCreator().forEach(value -> System.out.print(" "+value));


/**        Объедините List Listов в один Stream */
//        arrayLists[][] = {{" 1", " 2", " 3"},{" 4", " 5", " 6"}};
        Stream2.ListOfLists(arrayLists).forEach(System.out::print);


/**        Дан массив строк по шаблону: <text>:<text>:<text>:…:<text>. Вернуть массив элементов <text> */
//        String[] stringArrayText = {"1:2:3:", "foo:bar"};
        Stream2.StreamSplitedString(stringArrayText).forEach(string-> System.out.print(" "+string));



/**     3. Терминальные методы работы со стримом
 *
 */

        System.out.println("\n\n\n3. Терминальные методы работы со стримом\n");

        String[] stringArray = {"a", "b", "c"};
        String[] numbersArray = {"2", "BS12", "3", "4.16", "5", "7.233"};
        ArrayList<SimpleDataStream3> simpleDataArrayList = new ArrayList<>();
        simpleDataArrayList.add(new SimpleDataStream3(1,"Misha", true));
        simpleDataArrayList.add(new SimpleDataStream3(2,"Nyasha", false));
        simpleDataArrayList.add(new SimpleDataStream3(3,"Masha", true));
        simpleDataArrayList.add(new SimpleDataStream3(4,"Natasha", false));
        simpleDataArrayList.add(new SimpleDataStream3(5,"Oleg", false));

/**        Дан массив объектов (поля: id – Long, name - String, date - LocalDate ) Найти первый элемент в
 *         массиве по заданному имени. В случае отсутствия такового вернуть нового пользователя */
        Stream3.SearchSomeDataName(userArray,"Vase4kin").print();
        Stream3.SearchSomeDataName(userArray,"Oleg").print();


/**        Дан массив объектов (поля: id – Long, name - String, date - LocalDate ) Найти любой элемент
 *         в массиве по заданному имени с date больше текущей даты. В случае отсутствия такового вернуть null */
        if(Stream3.SearchSomeDataNameAndData(userArray,"Vase4kin")!=null)
            Stream3.SearchSomeDataNameAndData(userArray,"Vase4kin").print();
        else
            System.out.println(" Нету тут таких");


/**        Дан текстовый файл по пути ./payload/java8/stream/wordCount.txt. Вернут стрим
 *         со словами, отсортированными по частоте встречи по убыванию */
        System.out.println("\nВернут стрим со словами, отсортированными по частоте встречи по убыванию");
        Stream3.FileReader().forEach(value -> System.out.println(" "+value));


/**          Дан стрим, преобразовать его в ArrayList */
        ArrayList arrayList3 = Stream3.ArrayListFromStream(Stream.of("One","Two",3,4));
        for(Object element:arrayList3)
            System.out.print(" "+element);


/**        Дан список слов. Используя стрим вернуть список, из которого исключены слова длиннее заданного значения */
        Stream3.FilterLong(stringArrayList)
                .forEach(string-> System.out.print(" "+string));


/**          Вернуть HashMap, где ключ – слово, а значение – количество встреч слова в тексте.
 *           HashMap должен хранить только 10 самых частых слов */
        Stream3.FileReaderHashMap().forEach((value1,value2)-> System.out.println(" "+value1+" "+value2));


/**          Вернуть HashMap, где ключ – слово, а значение – вероятность встречи слова в тексте.
 *           HashMap должен хранить только 10 самых частых слов */
        Stream3.FileReaderHashMapPercentage().forEach((value1,value2)-> System.out.println(" "+value1+" "+value2+"%"));


/**          Посчитать количество элементов в массиве */
        System.out.print(" "+Stream3.ArrayCount(stringArrayList));


/**        Посчитать количество объектов, у которых date лежит в пределах текущего года */
        System.out.print(" "+Stream3.CurrentYearCount(userArray));


/**        Убедиться, есть ли в массиве пользователь с name = admin */
        if(Stream3.ContainsAdminName(userArray))
            System.out.print(" Есть такой");
        else
            System.out.print(" Не, нету");


/**        Убедиться, лежат ли date всех объектов в пределах текущего года */
        if(Stream3.AllElementsHaveCurrentYear(userArray))
            System.out.print(" Да, все подходят");
        else
            System.out.print(" Нет, не все");


/**        Найти минимальное число */
        System.out.println("\n Minimal: "+Stream3.MinimalNumber(arrayInt));


/**        Найти максимальное число */
        System.out.println("\n Maximal: "+Stream3.MaximalNumber(arrayInt));


/**        Дана точка X с ненулевыми координатами. Найти ближайшую точку из массива к X */
        Stream3.SerachNearestPoint(PointsArray, new Point(2.50f,3f))
                .forEach(point -> System.out.print(" ("+point.getX()+", "+point.getY()+")"));


/**        Дана точка X с ненулевыми координатами. Найти наиболее удаленную точку из массива к X */
        Stream3.SerachFurtherPoint(PointsArray, new Point(2.50f,3f))
                .forEach(point -> System.out.print(" ("+point.getX()+", "+point.getY()+")"));


/**        Сгенерировать случайную последовательность из 100000 чисел с экспоненциальным распределением.
 *         Рассчитать минимальное, максимальное, среднее, сумму, мат. ожидание, дисперсию, ср. квадратичное
 *         отклонение, моду, медиану */
        Stream3.MathematicalIndicators();


/**        Дан массив строк. Вернуть массив всех возможных комбинаций */
        System.out.println("\nДан массив строк. Вернуть массив всех возможных комбинаций");
        Stream3.StringMixingArray(stringArray)
                .forEach(string-> System.out.print(" "+string));


/**        Дан массив строк. Вернуть строку, являющуюся конкатенацией всех возможных вариантов перестановок для массива*/
        System.out.println("\n\nДан массив строк. Вернуть строку, являющуюся конкатенацией всех возможных вариантов перестановок для массива");
        System.out.println(" "+Stream3.StringMixingArrayToString(stringArray));


/**        Дано уравнение. Попытаться найти минимум и максимум на заданном диапазоне*/
        System.out.println("\nДано уравнение. Попытаться найти минимум и максимум на заданном диапазоне");
        Stream3.Equation(1,6);

/**        Рассчитать  на заданном диапазоне с заданным шагом*/
        System.out.println("\nРассчитать  на заданном диапазоне с заданным шагом");
        Stream3.Equation(1,6, 0.0002);


/**        Применить к каждому элементу массива заданную функцию*/
        Stream3.ElementFunction(stringArray);


/**        Дан массив строк. Некоторые элементы могут содержать целочисленные или вещественные числа.
 *         Необходимо вернуть примитимный стрим целых чисел ( вещественные необходимо округлить вниз)*/
        System.out.println("\n\nВернуть примитимный стрим целых чисел ( вещественные необходимо округлить вниз");
        Stream3.IntArrayFromStrings(numbersArray)
                .forEach(x-> System.out.print(" "+x));


/**        Вернуть сумму нечетных чисел*/
        System.out.println("\n\nВернуть сумму нечетных чисел");
        System.out.println(" Сумма = "+Stream3.OddElementSum(arrayInt));


/**        Дан массив объектов (поля: id – Long, name - String, logged - Boolean ). Разделить на две части по флагу logged*/
        System.out.println("\nРазделить на две части по флагу logged");
        Stream3.LoggedCollector(simpleDataArrayList);


/**        Дан массив объектов (поля: id – Long, name - String, logged - Boolean ). Разделить по алфавиту. Если буква
 *         содержит менее 5 записей, объединить их (например А-Г). Вернуть HashMap, где ключ: буква или диапазон,
 *         значение: список пользователей, у которых имя лежит в заданном диапазоне (или на заданную букву)*/
        System.out.println("\nВернуть HashMap, где ключ: буква или диапазон, значение: список пользователей");
        Stream3.NameCollector(simpleDataArrayList);


/**        Дан список списков строк. Вернуть строку по шаблону*/
        Stream3.FormatPrint(stringArrayList);


        
    }
}
