import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);

        FileReader reader1 = new FileReader("src//main//java//TextIn.txt");
        FileReader reader2 = new FileReader("src//main//java//TextOut1.txt");

        Scanner read1 = new Scanner(reader1);
        Scanner read2 = new Scanner(reader2);

        FileWriter writer1 = new FileWriter("src//main//java//TextOut1.txt");
        FileWriter writer2 = new FileWriter("src//main//java//TextOut2.txt");

        System.out.println("Данная программа использует шифр Цезаря для шифрования.");
        System.out.println("Введите значение для сдвига:\n");
        int d = sc.nextInt();

        String alphabetLow = "abcdefghijklmnopqrstuvwxyz";
        String alphabetUp  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String messageGet = null;

        String messageGive = "";

        // Шифрование

        while (read1.hasNextLine()) {

            messageGive = "";

            messageGet = read1.nextLine();

            for (int i = 0; i < messageGet.length(); i++) {

//                if      (messageGet.charAt(i) == ' ') messageGive += ' ';
//                else if (messageGet.charAt(i) == '!') messageGive += '!';
//                else if (messageGet.charAt(i) == '?') messageGive += '?';
//                else if (messageGet.charAt(i) == ',') messageGive += ',';
//                else if (messageGet.charAt(i) == '.') messageGive += '.';
//                else if (messageGet.charAt(i) == '<') messageGive += '<';
//                else if (messageGet.charAt(i) == '>') messageGive += '>';
//                else if (messageGet.charAt(i) == '-') messageGive += '-';
//                else if (messageGet.charAt(i) == '"') messageGive += '"';
//
//                else if (alphabetLow.lastIndexOf(messageGet.charAt(i)) != -1) {
//
//                    messageGive += alphabetLow.charAt((alphabetLow.lastIndexOf(messageGet.charAt(i)) + d) % alphabetLow.length());
//                }
//                else messageGive += alphabetUp.charAt((alphabetUp.lastIndexOf(messageGet.charAt(i)) + d) % alphabetUp.length());
            }

            writer1.write(messageGive + "\n");
        }

        read1.close();
        writer1.close();

        int temp = 0;

        // Дешифровка

        while (read2.hasNextLine()) {

            messageGet = "";

            messageGive = read2.nextLine();

            for (int i = 0; i < messageGive.length(); i++) {

                if (alphabetLow.lastIndexOf(messageGive.charAt(i)) != -1) {

                    temp = (alphabetLow.lastIndexOf(messageGive.charAt(i)) - d);
                }
                else temp = alphabetUp.lastIndexOf(messageGive.charAt(i)) - d;


                if (temp < 0) {

                    while (temp < 0) {

                        temp += alphabetLow.length();
                    }
                }

                if (messageGive.charAt(i) == ' ') messageGet += ' ';
                else if (messageGive.charAt(i) == '!') messageGet += '!';
                else if (messageGive.charAt(i) == '?') messageGet += '?';
                else if (messageGive.charAt(i) == ',') messageGet += ',';
                else if (messageGive.charAt(i) == '.') messageGet += '.';
                else if (messageGive.charAt(i) == '<') messageGet += '<';
                else if (messageGive.charAt(i) == '>') messageGet += '>';
                else if (messageGive.charAt(i) == '-') messageGet += '-';

                else if (alphabetLow.lastIndexOf(messageGive.charAt(i)) != -1) {

                    messageGet += alphabetLow.charAt(temp % alphabetLow.length());
                }
                else messageGet += alphabetUp.charAt(temp % alphabetUp.length());
            }

            writer2.write(messageGet + "\n");
        }

        read2.close();
        writer2.close();
    }
}
