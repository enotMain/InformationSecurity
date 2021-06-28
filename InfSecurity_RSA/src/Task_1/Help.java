package Task_1;

import Task_2.FermatsSmallTheorem;
import Task_2.TheExtendedEuclideanAlgorithm;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

// Класс для тестирования и последующего хранения полученных данных
public class Help {

    // Хранение данных о времени для графика
    public static ArrayList<Long> timeEuc = new ArrayList<>();
    public static ArrayList<Long> timeFer = new ArrayList<>();

    /**
     * Тестирование
     */
    public static void main() {

        // Случайное простое и случайное целое
        BigInteger randomPrime;
        BigInteger random;

        // Переменные для хранения времени и битовости
        long time;
        int m;

        for (int i = 0; i < 50; i++) {

            m = (i + 1) * 27;

            // Генерация случайных чисел
            random = (new BigInteger(m - 1, new Random(System.currentTimeMillis()))).
                    add((new BigInteger("2")).pow(m - 1));
            randomPrime = BigInteger.probablePrime(m, new Random(System.currentTimeMillis()));

            // Нужно ли находить подходящие для МТФ числа? Поиск чисел, удовлетворяющих условию МТФ
            while (random.mod(randomPrime).compareTo(BigInteger.ZERO) == 0) {
                randomPrime = BigInteger.probablePrime(m, new Random(System.currentTimeMillis()));
            }

            // Тестирование с зачетом времени
            time = System.nanoTime();
            new TheExtendedEuclideanAlgorithm(random, randomPrime).getReverse();
            timeEuc.add(System.nanoTime() - time);

            time = System.nanoTime();
            FermatsSmallTheorem.fermaMethod(random, randomPrime);
            timeFer.add(System.nanoTime() - time);
        }
    }
}
