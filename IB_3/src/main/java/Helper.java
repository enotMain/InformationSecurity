import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Helper {

    // Константа; основание степени, равное 2
    final static BigInteger base = new BigInteger("2");
    final static BigInteger number_1 = new BigInteger("1");
    final static BigInteger number_0 = new BigInteger("0");

    /**
     * Считывание больших чисел из файла и работа с ними, затем обратная запись с результатом проверки
     * на простоту
     */
    public static void bigNumbersCircle(File input, File output) {

        String line;
        // Число раундов
        BigInteger roundNumber;
        BigInteger temp;
        // Переменные задачи s и d
        BigInteger s;
        BigInteger d;
        BigInteger[] tempMas;
        boolean isPrimary;
        // Считываемые числа
        BigInteger bigNumber;
        BigInteger base = new BigInteger("2");

        try {

            // Буфферизация для более эффективного считывания и записи (файл)
            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(input));
            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(output));

            // Первая строка хранит значение о количестве больших чисел
            line = reader.readLine();
            int end = Integer.parseInt(line);

            // Считывание отдельного большого числа, затем работам с ним
            for (int i = 0; i < end; i++) {

                // Считали большое число
                line = reader.readLine();
                bigNumber = new BigInteger(line);

                // Определяем количество раундов (сами вводим или по умолчанию)
                System.out.println("Полученное большое число: " + bigNumber);
                temp = readRoundNumber();
                if (!(temp == null)) { // Вынести в отдельный метод?

                    roundNumber = temp;
                }
                else roundNumber = log2(bigNumber);

                tempMas = getS(bigNumber);

                // Получили значения для s и d
                s = tempMas[0];
                d = tempMas[1];

                // Проверка на простоту
                isPrimary = isPrimaryBig(roundNumber, bigNumber, s, d);

                writer.write(bigNumber + " " + isPrimary);
            }
        }
        catch (IOException e) {

            System.out.println(e);
        }
    }

    /**
     * Считывание данных с консоли
     */
    public static BigInteger readRoundNumber() {

        String line;

        try {

            System.out.print("Вы желаете самостоятельно задать количество раундов? ");

            // Буферизированное чтение для большей эффективности
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            line = reader.readLine();

            /*
            Если ответ был дан "Да", то вводим количество раундов,
            иначе значение количества раундов остается первоначальным
             */
            if (line.equals("Да")) {

                System.out.print("Введите число раундов: ");
                line = reader.readLine();

                return new BigInteger(line);
            }
        }
        catch (IOException e) {

            System.out.println(e);
        }

        return null;
    }

    /**
     * Нахождение логарифма по основанию 2
     */
    public static BigInteger log2(BigInteger bigNumber) {

        // Показатель степени
        BigInteger i = new BigInteger("0");
        // Значение выражения
        BigInteger pow2 = new BigInteger("1");

        // Если большое число равно 2, то вывести 1, иначе метод выдал бы 0, что есть ошибка
        if (bigNumber.compareTo(base) == 0) {

            return number_1;
        }

        while (pow2.compareTo(bigNumber) == -1) {

            pow2 = pow2.multiply(base);
            i = i.add(i);
        }

        return i.subtract(number_1);
    }

    /**
     * Получим значения для s и d
     */
    public static BigInteger[] getS(BigInteger bigNumber) {

        // Данный массив хранит: под индексом 0 - s, под индексом 1 - d
        BigInteger[] mas = new BigInteger[2];

        bigNumber = bigNumber.subtract(new BigInteger("1"));

        BigInteger s = new BigInteger("0");

        while (bigNumber.mod(base).compareTo(new BigInteger("0")) == 0) {

            bigNumber = bigNumber.divide(base);
            s.add(number_1);
        }

        mas[0] = s;
        mas[1] = bigNumber;

        return mas;
    }

    /**
     * Возведение в степень по модулю
     */
    public static BigInteger binPowMod_1(BigInteger a, BigInteger d, BigInteger p) {

        BigInteger temp = a.mod(p);

        for (BigInteger i = number_1; i.compareTo(d) == -1; i = i.add(number_1)) {

            temp = temp.multiply(a);
            temp = temp.mod(p);
        }

        return temp;
    }

    /**
     * Возведение в степень
     */
    public static BigInteger binPow(BigInteger a, BigInteger d) {

        for (BigInteger i = number_1; i.compareTo(d) == -1; i = i.add(number_1)) {

            a = a.multiply(a);
        }

        return a;
    }

    /**
     * Проверка на простоту большого числа через нахождение свидетелей простоты
     */
    public static boolean isPrimaryBig(BigInteger roundNumber, BigInteger bigNumber, BigInteger s, BigInteger d) {

        // Простые числа
        BigInteger primaryNumber = new BigInteger("2");

        // Условия
        boolean case1;
        boolean case2;

        for (BigInteger i = number_0; i.compareTo(roundNumber) == -1; i = i.add(number_1)) {

            case1 = false;
            case2 = false;

            while (!isPrimary(primaryNumber)) {

                primaryNumber = primaryNumber.add(number_1);
            }

            if (binPowMod_1(primaryNumber, d, bigNumber).compareTo(number_1) == 0) {

                case1 = true;
            }

            if (!case1 && case2(primaryNumber, d, s, bigNumber)) {

                case2 = true;
            }

            if (case1 == false && case2 == false) {

                return false;
            }
        }

        return true;
    }

    /**
     * Проверка второго условия
     */
    public static boolean case2(BigInteger a, BigInteger d, BigInteger s, BigInteger bigNumber) {

        // Значение степени с основанием 2
        BigInteger temp2 = base;
        // Значение степени с основание a
        BigInteger tempA = number_1;

        for (BigInteger i = number_0; i.compareTo(s) == -1; i = i.add(number_1)) {

            temp2 = binPow(base, i);
            tempA = binPow(a, temp2);
            tempA = binPow(tempA, d);

            if (tempA.mod(bigNumber).intValue() == -1) {

                return true;
            }
        }

        return false;
    }

    /**
     * Проверка на простоту малого числа
     */
    public static boolean isPrimary(BigInteger temp) {

        for (BigInteger i = base; i.compareTo(temp.sqrt()) == -1; i = i.add(number_1)) {

            if (temp.mod(i).compareTo(number_0) == 0) {

                return false;
            }
        }

        return true;
    }
}
