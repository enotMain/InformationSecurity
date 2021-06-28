package Task_2;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Random;

// Главный класс для выполнения алгоритма
public class Main_2 {

    public static void main(String[] args) {

        // Хранение битовости
        int m = Helper.getM();
        // Считывание сообщения
        String message = Helper.getMessage();
        // Перевод в массив байт сообщения по кодировки UTF-8
        byte[] byteMessage = message.getBytes(Charset.forName("UTF-8"));
        // Перевод в BigInteger
        BigInteger messageBig = new BigInteger(byteMessage);

        // Генерация простых чисел
        BigInteger p = BigInteger.probablePrime(m, new Random(System.currentTimeMillis()));
        BigInteger q = BigInteger.probablePrime(m, new Random(System.currentTimeMillis()));

        /*
         Вычисление нужных параметров по алгоритму.
         Здесь нельзя использовать Малую торему Ферма, так как она имеет ограничение на использование:
         1) модуль - простое число;
         2) модуль и целое число должны быть взаимопросты.
         */
        BigInteger n = p.multiply(q);
        BigInteger fn = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = Helper.getMutuallySimple(fn, m);
        BigInteger d = new TheExtendedEuclideanAlgorithm(e, fn).getReverse();
        BigInteger c = messageBig.modPow(e, n);

        System.out.println("\nОткрытый ключ (" + e + ", " + n + ")");
        System.out.println("Закрытый ключ " + d);

        System.out.println("\nШифрованное сообщение " + c);
        System.out.println("Расшифровка " + new String(c.modPow(d, n).toByteArray()));

    }
}