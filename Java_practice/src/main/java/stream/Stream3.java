package stream;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream3 {

    public static SomeData SearchSomeDataName(ArrayList<SomeData> arrayList, String name){
        System.out.println("Найти первый элемент в массиве по заданному имени. В случае отсутствия такового вернуть нового пользователя");
        return arrayList.stream()
                .filter(object-> object.getName()==name)
                .findFirst()
                .orElse(new SomeData(25,name, LocalDateTime.of(2019,4,01, 17,35)));
    }


    public static SomeData SearchSomeDataNameAndData(ArrayList<SomeData> arrayList, String name){
        System.out.println("\nНайти любой элемент в массиве по заданному имени с date больше текущей даты. В случае отсутствия такового вернуть null");
        return arrayList.stream()
                .filter(object-> object.getName()==name)
                .filter(object-> object.getDate().isAfter(LocalDateTime.now()))
                .findAny()
                .orElse(null);
    }


    public static Stream FilterLong(ArrayList<String> arrayList){
        System.out.println("\n\nДан список слов. Используя стрим вернуть список, из которого исключены слова длиннее заданного значения.");
        return arrayList.stream()
                .filter(string->string.length()<=12);
    }


    public static Stream<Map.Entry<String,Long>> FileReader() throws IOException {
        return Files.lines(Paths.get("./payload/java8/stream/wordCount.txt"))
                .map(string->new ArrayList<String>(Arrays.asList(string.split(" "))))
                .reduce(new ArrayList<>(),(value1,value2)->{
                    value1.addAll(value2);
                    return value1;
                })
                .stream()
                .map(string-> {
                    if(string.contains("."))
                        string = string.substring(0,string.indexOf("."));
                    return string;
                }).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((value1,value2)->{
                    return  (int) (value2.getValue()-value1.getValue());
                });
    }


    public static ArrayList ArrayListFromStream(Stream<Object> stream){
        System.out.println("\nДан стрим, преобразовать его в ArrayList");
        return stream.map(value-> new ArrayList(Arrays.asList(value)))
                .reduce(new ArrayList(), (value1,value2)->{
                    value1.addAll(value2);
                    return value1;
                });
    }


    public static HashMap FileReaderHashMap() throws IOException {
        System.out.println("\n\nВернуть HashMap, где ключ – слово, а значение – количество встреч слова в тексте");
        return new HashMap(
                FileReader()
                        .limit(10)
                        .collect(Collectors.toMap(x->x.getKey(),x->x.getValue()))
        );
    }


    public static HashMap FileReaderHashMapPercentage() throws IOException {
        System.out.println("\nВернуть HashMap, где ключ – слово, а значение – вероятность встречи слова в тексте");
        long numberOFWords = Files.lines(Paths.get("./payload/java8/stream/wordCount.txt"))
                        .map(string->new ArrayList<String>(Arrays.asList(string.split(" "))))
                        .count();

        return new HashMap(
                FileReader()
                        .limit(10)
                        .collect(Collectors.toMap(x->x.getKey(),x->((float)x.getValue()/numberOFWords*100)))
         );
    }


    public static long ArrayCount(ArrayList arrayList){
        System.out.println("\nПосчитать количество элементов в массиве");
        return arrayList.stream()
                .count();
    }


    public static long CurrentYearCount(ArrayList<SomeData> arrayList){
        System.out.println("\n\nПосчитать количество объектов, у которых date лежит в пределах текущего года");
        return arrayList.stream()
                .filter(object->object.getDate().getYear()==LocalDateTime.now().getYear())
                .count();
    }


    public static boolean ContainsAdminName(ArrayList<SomeData> arrayList){
        System.out.println("\n\nУбедиться, есть ли в массиве пользователь с name = admin");
        return arrayList.stream()
                .anyMatch(object->object.getName()=="admin");
    }


    public static boolean AllElementsHaveCurrentYear(ArrayList<SomeData> arrayList){
        System.out.println("\n\nУбедиться, лежат ли date всех объектов в пределах текущего года");
        return arrayList.stream()
                .allMatch(object->object.getDate().getYear()==LocalDateTime.now().getYear());
    }


    public static int MinimalNumber(int[] array){
        System.out.println("\n\nНайти минимальное число из ");
        for(int number:array)
            System.out.print(" "+number);
        return Arrays.stream(array)
                .min().getAsInt();
    }


    public static int MaximalNumber(int[] array){
        System.out.println("\n\nНайти максимальное число из ");
        for(int number:array)
            System.out.print(" "+number);
        return Arrays.stream(array)
                .max().getAsInt();
    }


    private static Stream<Point> PointCalculation(ArrayList<Point> pointsArray, Point X){
        return pointsArray.stream()
                .sorted((point1,point2)->{
                    float distance1 = (Math.abs(X.getX()-point1.getX()) + Math.abs(X.getY()-point1.getY()));
                    float distance2 = (Math.abs(X.getX()-point2.getX()) + Math.abs(X.getY()-point2.getY()));
                    int result = distance1==distance2? 0 : distance1<distance2? -1 : 1;
                    return result;
                });

    }


    public static Stream<Point> SerachNearestPoint(ArrayList<Point> pointsArray, Point X){
        System.out.println("\nДана точка X с ненулевыми координатами. Найти ближайшую точку из массива к X("
        +X.getX()+", "+X.getY()+")");
        return PointCalculation(pointsArray,X)
                .limit(1);
    }


    public static Stream<Point> SerachFurtherPoint(ArrayList<Point> pointsArray, Point X){
        System.out.println("\n\nДана точка X с ненулевыми координатами. Найти наиболее удаленную точку из массива к X("
                +X.getX()+", "+X.getY()+")");
        return PointCalculation(pointsArray,X)
                .skip(pointsArray.size()-1)
                .limit(1);
    }


    public static List<Integer> Randomizer(){
        int lambda = 500;
        Random random = new Random();
        return Stream.iterate(lambda, value-> value = (int)Math.round(-Math.log(Math.random())/lambda*1000))
                .limit(100001)
                .skip(1)
                .collect(Collectors.toList());
    }


    public static void MathematicalIndicators(){
        System.out.println("\n\nСгенерировать случайную последовательность из 100000 чисел с экспоненциальным распределением и рассчитать: ");
        ArrayList<Integer> array = (ArrayList) Randomizer();

        double averange = (double)array.stream()
                .reduce((value1,value2) -> value1+=value2)
                .get()/100000;

        double dispersion = (double)((int)array.stream()
                .mapToDouble(x-> Math.pow((double)x-averange,2))
                .reduce((x,y)-> x+=y)
                .getAsDouble()
                )/100000;

        long moda = array.stream()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((value1,value2)-> Long.compare(value2.getValue(),value1.getValue()))
                .limit(1)
                .findFirst()
                .get()
                .getKey();

        double median = array.stream()
                .skip(100000/2-1)
                .limit(2)
                .mapToDouble(value -> (double)value)
                .reduce((value1,value2)-> (value1+=value2)/2)
                .getAsDouble();

        System.out.println(" Минимальное: \t"+array.stream()
                .min((value1,value2)-> Integer.compare(value1,value2))
                .get());
        System.out.println(" Максимальное: \t"+array.stream()
                .max((value1,value2)->  Integer.compare(value1,value2))
                .get());
        System.out.println(" Среднее: \t\t"+averange);
        System.out.println(" Сумма: \t\t\t"+array.stream()
                .reduce((value1,value2) -> value1+=value2)
                .get());
        System.out.println(" Мат. ожидание: \t"+(double)1/500*1000);
        System.out.println(" Дисперсия: \t\t"+ dispersion);
        System.out.println(" Ср.кв. откл.: \t"+ (double) ((int)(Math.sqrt(dispersion)*100000))  /100000);
        System.out.println(" Мода: \t\t\t"+ moda);
        System.out.println(" Медиана: \t\t"+ median);
    }


    public static ArrayList<String> StringMixingArray(String[] stringArray){

        return Arrays.stream(stringArray)
                .map( string-> new ArrayList(Arrays.asList(string)))
                .reduce(new ArrayList(), (strArray,str)->{
                    ArrayList tempArray = new ArrayList();

                    tempArray.addAll(strArray);
                    tempArray.add(str.get(0));

                    for(int number = 0;number<strArray.size();number++){
                        for (int pozition=0;pozition<=strArray.get(number).toString().length();pozition++){
                            String tempString = new String();
                            ArrayList tempStringArray = new ArrayList();

                            for(int i=0;i<strArray.get(number).toString().length();i++)
                                tempStringArray.add(strArray.get(number).toString().charAt(i));

                            tempStringArray.add(pozition,str.get(0).toString());

                            for (int i=0;i<strArray.get(number).toString().length()+1;i++)
                                tempString += tempStringArray.get(i).toString();

                            tempArray.add(tempString);
                        }
                    }
                    return tempArray;
                });
    }


    public static String StringMixingArrayToString(String[] stringArray){

        return StringMixingArray(stringArray)
                .stream()
                .reduce((a,b)->{
                    a+=b;
                    return a;
                })
                .get();
    }


    public static void Equation(double start, double end, double ... stepArray){
        double step = (end-start)/10000;
        if (stepArray.length!=0)
            step = stepArray[0];
        System.out.println(" Уравнение: y=x^2+2; x("+start+":"+end+"), шаг="+step);
        ArrayList<Double> yValueArray = new ArrayList();

        for(double x=start;x<end;x+=step)
            yValueArray.add(x*x+2);

        double max = yValueArray.stream()
                .max((y1,y2)->Double.compare(y1,y2))
                .get();
        max = (double)((int)(max*10000))/10000;

        double min = yValueArray.stream()
                .min((y1,y2)->Double.compare(y1,y2))
                .get();
        min = (double)((int)(min*10000))/10000;

        System.out.println(" Max = "+ max + ", Min = "+min);
    }


    public static void ElementFunction(String[] strings){
        System.out.println("\nПрименить к каждому элементу массива заданную функцию");
        Arrays.stream(strings)
                .forEach(element->{
                    element+="_char";
                    System.out.print(" "+element);
                });

    }


    public static IntStream IntArrayFromStrings(String[] strArray){
        return Arrays.stream(strArray)
                .filter(element->{
                    for(int i=0;i<element.length();i++){
                        if((element.charAt(i)<'0' || element.charAt(i)>'9') && element.charAt(i)!=46)
                            return false;
                    }
                    return true;
                })
                .mapToInt(element -> {
                    double tempDouble = Double.parseDouble(element);
                    int tempInt = (int)tempDouble;
                    if(tempDouble<tempInt)
                        tempInt--;
                    return tempInt;
                    });
    }


    public static int OddElementSum(int[] array){
        Integer[] arrayInteger = Arrays.stream(array).boxed().toArray(Integer[]::new);
        ArrayList<Integer> intArray = new ArrayList(Arrays.asList(arrayInteger));
        return Stream2.StreamFilterOdd(intArray)
                .sum();
    }


    public static void LoggedCollector(ArrayList<SimpleDataStream3> arrayList){
        Map<Boolean, List<SimpleDataStream3>> temp = arrayList.stream()
                .collect(Collectors.groupingBy(SimpleDataStream3::isLogged));

        System.out.println(" False: ");
        temp.get(false).forEach(x-> x.Print());

        System.out.println(" True: ");
        temp.get(true).forEach(x-> x.Print());
    }


    public static void NameCollector(ArrayList<SimpleDataStream3> arrayList){
        Map<String,List<SimpleDataStream3>> dataCollectionMap = arrayList.stream()
                .collect(Collectors.groupingBy(element -> element.getName().substring(0,1)));

        Object[] keys =  dataCollectionMap.keySet().toArray();

        for(int i=1;i<dataCollectionMap.size();i++){
            if(dataCollectionMap.get(keys[i]).size()<5){
                if( keys[i].toString().charAt(0) - keys[i-1].toString().charAt(0)==1 ){
                    String newKey;
                    if( keys[i-1].toString().length()==1 )
                        newKey = keys[i-1].toString() + "-" + keys[i];
                    else
                        newKey = keys[i-1].toString().substring(0,1) + keys[i];

                    dataCollectionMap.get(keys[i-1]).addAll(dataCollectionMap.remove(keys[i]));
                    dataCollectionMap.put(newKey, dataCollectionMap.remove(keys[i-1]));
                }
            }
        }

        keys =  dataCollectionMap.keySet().toArray();

        for (int i=0;i<dataCollectionMap.size();i++){
            System.out.println(" " + keys[i] + ":");
            dataCollectionMap.get(keys[i]).forEach(element-> element.Print());
        }
    }


    public static void FormatPrint(ArrayList<String> stringArrayList){
        System.out.println("\n\nДан список списков строк. Вернуть строку по шаблону");
        System.out.println(" div");
        stringArrayList.stream()
                .forEach(string->{
                    System.out.println(" <ul>\n  <li>"+string+"</li>\n </ul>");
                });
        System.out.println(" /div");
    }









}


