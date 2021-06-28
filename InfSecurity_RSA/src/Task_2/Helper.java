package Task_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Random;

// Побочные методы
public class Helper {

    // Базоыые константы. Некоторые излишне, так как точно такие же есть в классе BigInteger
    public static final BigInteger BASE_2  = new BigInteger("2");
    public static final BigInteger CONST_0 = new BigInteger("0");
    public static final BigInteger CONST_1 = new BigInteger("1");

    /**
     * Значение битовости по умолчание 1024
     * Метод для ввода битовости
     * @return
     */
    public static int getM() {

        int m = 1024;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Вы хотите ввести самостоятельно значение m?");
            if (reader.readLine().equals("Да")) {
                System.out.print("Введите значение m: ");
                m = Integer.parseInt(reader.readLine());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return m;
    }

    /**
     * Метод для считывания сообщения
     * @return
     */
    public static String getMessage() {
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите сообщения, которое будет использовано для шифрования:");
            return reader.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Генерация взимно простых чисел
     * @param bigInteger
     * @param m
     * @return
     */
    public static BigInteger getMutuallySimple(BigInteger bigInteger, int m) {
        TheExtendedEuclideanAlgorithm algorithm;
        BigInteger temp;
        while (true) {
            // Генерация чисел в нужном диапазоне
            temp = (new BigInteger(m - 1, new Random(System.currentTimeMillis()))).
                    add((new BigInteger("2")).pow(m - 1));
            algorithm = new TheExtendedEuclideanAlgorithm(bigInteger, temp);
            algorithm.methodAlg();
            // Проверка с помощью алгоритма Евклида: НОД = 1?
            if (algorithm.getD().compareTo(BigInteger.ONE) == 0) {
                return temp;
            }
        }
    }
}