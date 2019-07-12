package newIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class Server extends Thread {

    public Server(String name) {
        super(name);
    }

    @Override
    public void run() {
        try (ServerSocket server = new ServerSocket(4040)) {
            Socket clientSocket = server.accept();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

                String timeZone = in.readLine();
                LocalDateTime time = LocalDateTime.now(ZoneId.of(timeZone));
                out.write(time.format(DateTimeFormatter.ofPattern("YYYY:MM:dd HH:mm"))+"\n");
                out.flush();

                String encodedPhoto = in.readLine();
                byte[] photoInBytes = Base64.getDecoder().decode(encodedPhoto);
                Files.write(Paths.get("D:/test/test2/server/photo.jpg"),photoInBytes);
                String size = in.readLine();
                if(Integer.parseInt(size)==photoInBytes.length)
                    System.out.println(" Фото передано полностью.");
                else
                    System.out.println(" Фото повреждено.");
            }
        }
        catch (IOException ex){
            System.out.println(ex);
        }
    }
}
