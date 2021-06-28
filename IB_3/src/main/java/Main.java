import java.io.File;

public class Main {
    public static void main(String[] args) {

        File input  = new File("src\\main\\java\\BigNumbers.txt");
        File output = new File("src\\main\\java\\Result.txt");

        Helper.bigNumbersCircle(input, output);
    }
}
