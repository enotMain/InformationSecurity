public class Main {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        // Простое число p
        int p = Help.generatePrime();
        System.out.println("Простое число p: " + p);

        // Образующий элемент g
        int g = Help.findElement(p);
        System.out.println("Образующий элемент g: " + g);

        // Секретный ключ
        long secretKey = Help.createSecretKey(g, p);
        System.out.println("Секретный ключ: " + secretKey);

        System.out.println("Время выполнения программы: " + (System.currentTimeMillis() - startTime) + " миллисекунд");
    }
}