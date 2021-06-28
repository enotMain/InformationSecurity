public class Help {

    /**
     * Генерация простого числа p
     */
    public static int generatePrime() {
        int n = 65536;

        while (true) {
            int p = (int) (Math.random()*n);
            if (isPrime(p))
                return p;
        }
    }

    public static boolean isPrime(int p) {
        for (int i = 2; i < Math.sqrt(p); i++)
        {
            if (p % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Нахождение образующего элемента g с использованием условий
     */
    public static int findElement(int p) {

        int g = 0;
        /* C is condition (условие)
        Условие 1 (Малая теорема Ферма) */
        boolean checkC1;
        boolean checkC2;
        boolean flag = false;

        while (true) {
            g = (int) (Math.random()*(p-2)) + 2;
            checkC1 = true;
            checkC2 = true;
            if (modPow(g, p-1, p) != 1)
                checkC1 = false; // Было if (modPow(g, p-1, p) == 1) checkC1 = true;
            for (int l = 1; l < p-1; l = l + 1)
                if (modPow(g, l, p) == 1) {
                    checkC2 = false;
                    break;
                }
            if( checkC1 && checkC2)
                return g;
        }
    }

    /**
     * Возведение в степень по модулю (первый параметр типа int)
     */
    public static long modPow(int g, int l, int p) {

        long c = 1;
        for (int n = 0; n < l; n++) {
            c = (c * g) % p;
        }
        return c;
    }

    /**
     * Возведение в степень по модулю (первый параметр типа long)
     */
    public static long modPow(long g, int l, int p) {

        long c = 1;
        for (int n = 0; n < l; n++) {
            c = (c * g) % p;
        }
        return c;
    }

    /**
     * Создание секретного ключа
     */
    public static long createSecretKey(int g, int p) {

        // Генерация больших чисел для собеседников
        int a = (int) (Math.random()*1000000);
        int b = (int) (Math.random()*1000000);

        // Создание открытого ключа
        long A_Open = Help.modPow(g, a, p);
        long B_Open = Help.modPow(g, b, p);

        // После обмена открытым ключом собеседники создают секретный ключ
        long A_Secret = Help.modPow(B_Open, a, p);
        long B_Secret = Help.modPow(A_Open, b, p);

        // Проверка: если ключи совпадают, то возвращаем секретный ключ, иначе 0
        if (A_Secret == B_Secret) {
            return A_Secret;
        }

        return 0;
    }
}
