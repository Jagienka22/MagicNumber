package magic;

import java.util.Scanner;

public class MagicNumber {

    public static void main(String[] args) {
        String nameOfFile;
        System.out.println("Proszę podać nazwę pliku: ");
        Scanner on = new Scanner(System.in);
        nameOfFile = on.nextLine();

        CheckFile check = new CheckFile(nameOfFile);
        System.out.println(check.whichFile());

    }
}
