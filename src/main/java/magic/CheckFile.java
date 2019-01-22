package magic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class CheckFile {
    private static String nameOfFile;

    CheckFile(String nameOfFile) {
        this.nameOfFile = nameOfFile;
    }

    public static String whichFile() {
        String stringHex = "";
        String result = "";
        if (nameOfFile.contains(".txt") || nameOfFile.contains(".gif") || nameOfFile.contains(".jpg")) {
            try {
                FileInputStream in = new FileInputStream(nameOfFile);

                byte[] bytes = new byte[in.available()];
                int numbers = in.read(bytes);
                for (int i = 0; i < numbers; i++) {
                    stringHex += Integer.toHexString(bytes[i] & 0xFF) + " ";
                }
                if (nameOfFile.contains((".gif")))
                    result = checkMagicNumberForGif(stringHex);
                if (nameOfFile.contains((".jpg")))
                    result = checkMagicNumberForJpg(stringHex);
                if (nameOfFile.contains((".txt")))
                    result = checkMagicNumberForTxt();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Takiego pliku nie obsługujemy");
        }
        return result;
    }

    public static String checkMagicNumberForGif(String s) {
        String result;
        if (s.contains("47 49 46 38 39 61") || s.contains("47 49 46 38 37 61")) {
            result = "Tak, ten plik jest zgodny z rozszerzeniem.";
        } else
            result = "Nie, ten plik nie jest zgodny z rozszerzeniem.";

        return result;
    }

    public static String checkMagicNumberForJpg(String s) {
        String result = "";
        if (s.contains("ff d8") || s.contains("ff d9") || s.contains("4a 46 49 46") || s.contains("45 78 69 66") || s.contains("FF D8 FF E0 00 10 4A 46 49 46 00 01") || s.contains("ff d8 ff e1" + " 45 78 69 66 00 00")) {
            result = "Tak, ten plik jest zgodny z rozszerzeniem.";
        } else
            result = "Nie, ten plik nie jest zgodny z rozszerzeniem";
        return result;
    }

    public static String checkMagicNumberForTxt() throws IOException {
        String result = "";
        Path path = FileSystems.getDefault().getPath(nameOfFile);
        String mimeType = Files.probeContentType(path);

        if (mimeType.contains("text")) {
            result = "Tak, ten plik jest zgodny z rozszerzeniem.";
        } else {
            result = "Nie, ten plik nie jest zgodny z rozszerzeniem";
        }
        return result;
    }

}
