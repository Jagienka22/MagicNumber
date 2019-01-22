package magic;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String nameOfFile;
        System.out.println("Please enter name of file (for example 'nameOfFile.txt' ): ");
        Scanner in= new Scanner(System.in);
        nameOfFile = in.nextLine();

        CheckFile file = new CheckFile(nameOfFile);
        System.out.println(file.checkFileContentMatchesFileExtension());

    }
}
