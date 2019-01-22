package magic;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class MagicNumber {
    public static String whichFile(String s){
        String result = "";
        if(s.contains("ffffffff ffffffd8") || s.contains("ffffffff ffffffd9") || s.contains("4A 46 49 46")){
            System.out.println("Tak, ten plik jest zgodny z rozszerzeniem");
        }
        if(s.contains("47 49 46 38 39 61") || s.contains("47 49 46 38 37 61")){
            System.out.println("Tak, ten plik jest zgodny z rozszerzeniem");

        }
        return result;
    }

    public static void main(String[] args) {
        String nameOfFile;
        String stringHex = "";
        System.out.println("Proszę podać nazwę pliku: ");
        Scanner on = new Scanner(System.in);
        nameOfFile = on.nextLine();

        if (nameOfFile.contains(".txt") || nameOfFile.contains(".gif") || nameOfFile.contains(".jpg")) {
            try {
                FileInputStream in = new FileInputStream(nameOfFile);

                byte[] bytes = new byte[in.available()];
                in.read(bytes);
                for (int i = 0; i < 20; i++) {
                    stringHex += Integer.toHexString(bytes[i]) + " ";
                    System.out.println("Bytes:" + bytes[i]);
                    System.out.println("String :" + Integer.toHexString(bytes[i] & 0xFF));
                    Path path = FileSystems.getDefault().getPath(nameOfFile);
                    String mimeType = Files.probeContentType(path);
                    System.out.println(mimeType);
                }
                System.out.println(stringHex);
                whichFile(stringHex);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Takiego pliku nie obsługujemy");
        }

    }
}
