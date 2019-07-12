package newIO;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {


/** Получить список всех файлов рекурсивно от заданного пути */
        System.out.println("\nПолучить список всех файлов рекурсивно от заданного пути (D:/test)");
        ArrayList<File> dir1 = DirReader.DirectoryReader(new File("D:/test"));
        for(File file:dir1)
            System.out.println(" "+file);


/** Получить список всех файлов рекурсивно от заданного пути с заданным форматом (.txt)*/
        System.out.println("\nПолучить список всех файлов рекурсивно от заданного пути с заданным форматом");
        dir1 = dir1.stream()
                .filter(value-> value.toString().contains(".txt"))
                .collect(Collectors.toCollection(ArrayList::new));
        for(File file:dir1)
            System.out.println(" "+file);


/** Проверить, есть ли файл по заданному пути */
        System.out.println("\nПроверить, есть ли файл по заданному пути (D:/test)");
        dir1 = DirReader.DirectoryReader(new File("D:/test"));
        if(dir1.stream().anyMatch(value->value.isFile()))
            System.out.println(" Есть");
        else
            System.out.println(" Нету");


/** Проверить, файл или папка расположен по заданному пути? */
        System.out.println("\nПроверить, файл или папка расположен по заданному пути? (D:/test/test2)");
        if(new File("D:/test/test2").isDirectory())
            System.out.println(" Ит ис директори");
        else
            System.out.println(" Ит ис файл");


/** Получить размер файла в килобайтах */
        System.out.println("\nПолучить размер файла в килобайтах (D:/test/test2/wordCount.txt)");
        System.out.println(" Размер файла: "+Files.size(Paths.get("D:/test/test2/wordCount.txt"))/1000+" KB");


/** Прочитать файл в массив байт */
        System.out.print("\nПрочитать файл в массив байт (D:/test/test2/wordCount.txt)\n ");
        byte[] tempBytes = Files.readAllBytes(Paths.get("D:/test/test2/wordCount.txt"));
        for(byte b:tempBytes)
            System.out.print(b);


/** Прочитать файл в массив строк */
        System.out.println("\n\nПрочитать файл в массив строк (D:/test/test2/wordCount.txt) ");
        List<String> tempString = Files.readAllLines(Paths.get("D:/test/test2/wordCount.txt"));
//        for (String s:tempString)
//            System.out.println(" " + s);


/** Сымитировать ошибку в процессе чтения файла (выбросить исключение). Корректно освободить все ресурсы */
        System.out.println("\nСымитировать ошибку в процессе чтения файла (выбросить исключение). Корректно освободить все ресурсы ");
        try(FileInputStream fis = new FileInputStream("D:/test/test2/wordCount.txt")){
            throw new IOException();
        }
        catch (IOException ex){
            System.out.println(" Получена ошибка IOException, поток закрыт при помощи Try с ресурсами");
        }


/** Добавить строку в конец файла (D:/test/test2/addedFile.txt)*/
        System.out.println("\nДобавить строку в конец файла (D:/test/test2/addedFile.txt)");
        try(FileWriter fileWriter = new FileWriter("D:/test/test2/addedFile.txt",true)){
            fileWriter.append("ADDED STRING\n");
        }
        System.out.println(" Добавил");


/** Записать в массив первые три строки из файла (D:/test/test2/wordCount.txt)*/
        System.out.println("\nЗаписать в массив первые три строки из файла (D:/test/test2/wordCount.txt)");
        try (BufferedReader fileReader = new BufferedReader(new FileReader("D:/test/test2/wordCount.txt"))){
            String[] strings = new String[3];
            for (int i = 0;i<3;i++)
                strings[i] = fileReader.readLine();

            for(String s:strings)
                System.out.println(" "+s);
        }


/** Записать в массив последние две строки из файла (D:/test/test2/wordCount)*/
        System.out.println("\nЗаписать в массив последние две строки из файла (D:/test/test2/wordCount)");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("D:/test/test2/wordCount.txt"))){
            ArrayList<String> stringArrayList = new ArrayList<>();
            while (true) {
                String temp = bufferedReader.readLine();
                if(temp==null)
                    break;
                stringArrayList.add(temp);
            }
            String[] strings = new String[2];
            strings[0] = stringArrayList.get(stringArrayList.size()-2);
            strings[1] = stringArrayList.get(stringArrayList.size()-1);

            System.out.println(" Предпоследняя:\t"+strings[0]);
            System.out.println(" Последняя:\t"+strings[1]);
        }


/** ЗНайти самое длинное слово в файле (D:/test/test2/wordCount)*/
        System.out.println("\nНайти самое длинное слово в файле (D:/test/test2/wordCount)");
        try(FileReader fis = new FileReader("D:/test/test2/wordCount.txt")){
            String longestWord="", tempStr="";
            int simbol;
            while ((simbol=fis.read())!=-1){
                if(((char)simbol >64 && (char)simbol<91) || ((char)simbol >96 && (char)simbol<123))
                    tempStr += (char) simbol;
                else {
                    if (longestWord.length() < tempStr.length())
                        longestWord = tempStr;
                    tempStr="";
                }
            }
            System.out.println(" Самое длинное слово: "+longestWord);
        }


/** ЗНайти самое частое слово в файле (D:/test/test2/wordCount)*/
        System.out.println("\nНайти самое частое слово в файле (D:/test/test2/wordCount)");
        try (FileReader fis = new FileReader("D:/test/test2/wordCount.txt")){
            HashMap<String,Integer> words = new HashMap<>();
            String tempStr="";
            int simbol;

            while ((simbol=fis.read())!=-1){
                if(((char)simbol >64 && (char)simbol<91) || ((char)simbol >96 && (char)simbol<123))
                    tempStr += (char) simbol;
                else {
                    if(words.putIfAbsent(tempStr,1) != null){
                        int value = words.get(tempStr);
                        words.put(tempStr,++value);
                    }
                    tempStr="";
                }
            }
            words.remove("");

            String popularWord = words.entrySet().stream()
                    .max((value1,value2)->{
                        if(value1.getValue()<value2.getValue())
                            return -1;
                        else
                            return 1;
                    })
                    .get().getKey();

            System.out.println(" Самое частое слово в файле: "+popularWord);
        }


/** Преобразовать ByteArrayInputStream в ByteArrayOutputStream и обратно*/
        System.out.println("\nПреобразовать ByteArrayInputStream в ByteArrayOutputStream и обратно");
        try(ByteArrayInputStream bais = new ByteArrayInputStream("Some String".getBytes())){
            try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
                int readByte;
                while ((readByte=bais.read()) !=-1)
                    baos.write(readByte);

                System.out.println(" ByteArrayOutputStream: "+baos.toString());

                try (ByteArrayInputStream bais2 = new ByteArrayInputStream(baos.toByteArray())){
                    int readenByte2;
                    String tempStr = "";
                    while ((readenByte2=bais2.read()) !=-1)
                        tempStr += (char)readenByte2;

                    System.out.println(" ByteArrayInputStream: "+tempStr);
                }
            }
        }


/** Преобразовать BufferedInputStream в BufferedOutputStream и обратно*/
        System.out.println("\nПреобразовать BufferedInputStream в BufferedOutputStream и обратно");
        try (BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream("Some string".getBytes()))){
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 BufferedOutputStream bos = new BufferedOutputStream(baos)){
                int readByte;
                while((readByte=bis.read()) !=-1)
                    bos.write(readByte);
                bos.flush();

                System.out.println(" BufferedOutputStream: "+baos.toString());

                try (BufferedInputStream bis2 = new BufferedInputStream(new ByteArrayInputStream(baos.toString().getBytes()))){
                    int readenByte2;
                    String tempStr = "";
                    while ((readenByte2=bis2.read()) !=-1)
                        tempStr += (char)readenByte2;

                    System.out.println(" ByteArrayInputStream: "+tempStr);
                }
            }
        }


/** Использовать PrintStream для вывода в файл (D:/test/test2/task16.txt)*/
        System.out.println("\nИспользовать PrintStream для вывода в файл (D:/test/test2/task16.txt) ");
        try (PrintStream printStream = new PrintStream("D:/test/test2/task16.txt")){
            printStream.println("Some string");
            System.out.println(" Готово, можно проверять.");
        }


/** Использовать DataInputStream для записи примитивов в файл. Прочитать с использованием DataOutputStream (D:/test/test2/task17.txt)*/
        System.out.println("\nИспользовать DataInputStream для записи примитивов в файл. Прочитать с использованием DataOutputStream (D:/test/test2/task17.txt)");
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("D:/test/test2/task17.txt"))){
            dos.writeUTF("Some string");
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream("D:/test/test2/task17.txt"))){
            System.out.println(" Содержимое: "+dis.readUTF());
        }


/** Использовать ObjectInputStream для сериализации. Десериализовать серилизованный объект (D:/test/test2/task18.txt)*/
        System.out.println("\nИспользовать ObjectInputStream для сериализации. Десериализовать серилизованный объект (D:/test/test2/task18.txt)");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:/test/test2/task18.txt"))){
            RandomClass obj = new RandomClass(23,"Pasha");
            oos.writeObject(obj);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:/test/test2/task18.txt"))){
            RandomClass objTemp = (RandomClass)ois.readObject();
            System.out.println(" RandomClass: id="+objTemp.getId()+", name="+objTemp.getName());
        }
        catch (ClassNotFoundException ex){
            System.out.println(" Да всё нормально будет.");
        }


/** Заархивировать в zip несколько файлов. Разархивировать файлы из zip. Расчитать размер каждого файла в архиве*/
        System.out.println("\nЗаархивировать в zip несколько файлов. Разархивировать файлы из zip. Расчитать размер каждого файла в архиве");
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("D:/test/test2/tasks.zip"))){
            String[] tasks = {  "D:/test/test2/task16.txt",
                                "D:/test/test2/task17.txt",
                                "D:/test/test2/task18.txt"};

            for(int i=0;i<3;i++){
                zout.putNextEntry(new ZipEntry(tasks[i].substring(tasks[i].lastIndexOf('/')+1)));

                try(FileInputStream fis = new FileInputStream(tasks[i])){
                    byte[] byteBuffer = Files.readAllBytes(Paths.get(tasks[i]));
                    zout.write(byteBuffer);
                }

                zout.closeEntry();
            }
        }

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream("D:/test/test2/tasks.zip"))){
            if(Files.exists(Paths.get("D:/test/test2/unzipped"))){
                String[] filesToDelete = {
                        "D:/test/test2/unzipped/task16.txt",
                        "D:/test/test2/unzipped/task17.txt",
                        "D:/test/test2/unzipped/task18.txt",
                        "D:/test/test2/unzipped",
                };
                for(int i =0;i<4;i++)
                    Files.deleteIfExists(Paths.get(filesToDelete[i]));
            }

            Files.createDirectory(Paths.get("D:/test/test2/unzipped"));
            for (int i=0;i<3;i++){
                ZipEntry entry = zis.getNextEntry();
                try (FileOutputStream fos = new FileOutputStream("D:/test/test2/unzipped/"+entry.getName())) {
                    for(int readByte=zis.read();readByte!=-1;readByte=zis.read())
                        fos.write(readByte);
                    fos.flush();
                }
                zis.closeEntry();
            }
        }
        System.out.println(" Ready.");


/** Считать последние 1000 символов из файла (D:/test/test2/wordCount.txt)*/
        System.out.print("\nСчитать последние 1000 символов из файла (D:/test/test2/wordCount.txt)\n ");
        try (FileInputStream fis = new FileInputStream("D:/test/test2/wordCount.txt")){
            byte[] bytes = new byte[1000];

            if(Files.size(Paths.get("D:/test/test2/wordCount.txt"))<1000)
                fis.read(bytes);
            else{
                fis.skip(Files.size(Paths.get("D:/test/test2/wordCount.txt"))-1000);
                fis.read(bytes);
            }

            for (byte b:bytes)
                System.out.print((char)b);
        }


/** Используя сокет реализовать сервер, который принимает на вход TimeZone, а возвращает текущее время в заданной TimeZone.
 *  Реализовать клиента, который будет отправлять свою либо случайную TimeZone на сервер*/
        System.out.println("\n\nИспользуя сокет реализовать сервер, который принимает на вход TimeZone, а возвращает текущее время" +
                " в заданной TimeZone. Реализовать клиента, который будет отправлять свою либо случайную TimeZone на сервер");
        Server server = new Server("Server");
        server.start();
        new Client("Client").start();
        server.join();


/** Переведите текст из ANSII в UTF-8, затем ASCII, затем в кодировку по умолчанию*/
        System.out.println("\nПереведите текст из ANSII в UTF-8, затем ASCII, затем в кодировку по умолчанию");
        try {
            String stringToEncode = "Some string!";
            String[] charsets = new String[]{
                    "windows-1251",
                    "UTF-8",
                    "ASCII"
            };
            for (int i=0;i<3;i++){
                Charset charset = Charset.forName(charsets[i]);
                byte[] byteBuffer = charset.encode(stringToEncode).array();
                System.out.print(" "+charsets[i]+": ");
                for (byte b:byteBuffer)
                    System.out.print((char) b);
                System.out.println();
            }
            Charset charset = Charset.defaultCharset();
            byte[] byteBuffer = charset.encode(stringToEncode).array();
            System.out.print(" default: " );
            for (byte b:byteBuffer)
                System.out.print((char)b);

        }finally {

        }





    }
}
