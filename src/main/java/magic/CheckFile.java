package magic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

class CheckFile {
    private String nameOfFile;

    CheckFile(String nameOfFile) {
        this.nameOfFile = nameOfFile;
    }

    String checkFileContentMatchesFileExtension() {
        String stringHex = "";
        String result = "";
        if (nameOfFile.contains(".txt") || nameOfFile.contains(".gif") || nameOfFile.contains(".jpg") || nameOfFile.contains(".jpeg")) {
            try {
                FileInputStream in = new FileInputStream(nameOfFile);

                byte[] bytes = new byte[in.available()];
                int numbers = in.read(bytes);
                for (int i = 0; i < numbers; i++) {
                    stringHex += Integer.toHexString(bytes[i] & 0xFF) + " ";
                }
                if (nameOfFile.contains((".gif")))
                    result = checkMagicNumberForGif(stringHex);
                if (nameOfFile.contains((".jpg")) || nameOfFile.contains(".jpeg"))
                    result = checkMagicNumberForJpg(stringHex);
                if (nameOfFile.contains((".txt")))
                    result = checkMagicNumberForTxt();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            result = "This file isn't handled.";
        }
        return result;
    }

    private String checkMagicNumberForGif(String s) {
        String result;
        if (s.contains("47 49 46 38 39 61") || s.contains("47 49 46 38 37 61")) {
            result = "Yes, file content matches file extension (gif).";
        } else
            result = "No, file content doesn't match file extension (gif).";

        return result;
    }

    private String checkMagicNumberForJpg(String s) {
        String result = "";
        if ((s.startsWith("ff d8") && s.endsWith("ff d9")) || s.contains("4a 46 49 46") || s.contains("45 78 69 66") || s.contains("ff d8 ff e0 00 10 4a 46 49 46 00 01") || (s.startsWith("ff d8 ff e1") && s.substring(18, 35).equals("45 78 69 66 00 00"))) {
            result = "Yes, file content matches file extension (jpg).";
        } else
            result = "No, file content doesn't match file extension (jpg).";
        return result;
    }

    private String checkMagicNumberForTxt() throws IOException {
        String result = "";
        Path path = FileSystems.getDefault().getPath(nameOfFile);
        String mimeType = Files.probeContentType(path);

        if (mimeType.contains("text")) {
            result = "Yes, file content matches file extension (txt). ";
        } else {
            result = "No, file content doesn't match file extension (txt).";
        }
        return result;
    }

}
