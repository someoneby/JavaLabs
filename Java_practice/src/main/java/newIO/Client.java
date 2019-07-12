package newIO;

import javax.imageio.spi.ImageReaderSpi;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Client extends Thread {

    public Client(String name) {
        super(name);
    }

    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", 4040)) {

            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))){

                out.write("Europe/Minsk\n");
                out.flush();
                String time = in.readLine();
                System.out.println(" now: " + time);

                System.out.println("\nОткройте картинку как бинарный файл, переведите ее в Base64. Отправьте по сокету. Раскодируйте из Base64 и сохраните на диск. Убедитесь, что картинка не повреждена");
                try (FileInputStream fis = new FileInputStream("D:/test/test2/photo.jpg")){
                    byte[] photoInBytes = Files.readAllBytes(Paths.get("D:/test/test2/photo.jpg"));
                    String encodedPhoto = Base64.getEncoder().encodeToString(photoInBytes);
                    out.write(encodedPhoto+"\n");
                    out.flush();
                }
                out.write(String.valueOf(Files.size(Paths.get("D:/test/test2/photo.jpg"))));
            }
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
