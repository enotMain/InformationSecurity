import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        FileWriter writerFirst = new FileWriter("src//main//java//TextOut.txt");
        FileWriter writer = new FileWriter("src//main//java//TextOut.txt", true);

        writerFirst.write("");

        String alphabetLow = "abcdefghijklmnopqrstuvwxyz";
        String alphabetUp  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String messageGet;
        String messageGive;

        int temp = 0;

        for (Integer d = 0; d < alphabetLow.length(); d++) {

            FileReader reader = new FileReader("src//main//java//TextIn.txt");
            Scanner read = new Scanner(reader);

            writer.write(d.toString() + "\n");

            while (read.hasNextLine()) {

                messageGive = "";

                messageGet = read.nextLine();

                for (int i = 0; i < messageGet.length(); i++) {

                    if (alphabetLow.lastIndexOf(messageGet.charAt(i)) != -1) {

                        temp = (alphabetLow.lastIndexOf(messageGet.charAt(i)) - d);
                    }
                    else temp = alphabetUp.lastIndexOf(messageGet.charAt(i)) - d;


                    if (temp < 0) {

                        while (temp < 0) {

                            temp += alphabetLow.length();
                        }
                    }

                    if (messageGet.charAt(i) == ' ') messageGive += ' ';
                    else if (messageGet.charAt(i) == '!') messageGive += '!';
                    else if (messageGet.charAt(i) == '?') messageGive += '?';
                    else if (messageGet.charAt(i) == ',') messageGive += ',';
                    else if (messageGet.charAt(i) == '.') messageGive += '.';
                    else if (messageGet.charAt(i) == '<') messageGive += '<';
                    else if (messageGet.charAt(i) == '>') messageGive += '>';
                    else if (messageGet.charAt(i) == '-') messageGive += '-';
                    else if (messageGet.charAt(i) == '"') messageGive += '"';

                    else if (alphabetLow.lastIndexOf(messageGet.charAt(i)) != -1) {

                        messageGive += alphabetLow.charAt(temp % alphabetLow.length());
                    }
                    else messageGive += alphabetUp.charAt(temp % alphabetUp.length());
                }

                writer.write(messageGive + "\n");
            }

            read.close();

            writer.write("\n");
        }

        writer.close();
    }
}
