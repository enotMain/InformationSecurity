package Task_2;

import java.math.BigInteger;

public class TheExtendedEuclideanAlgorithm {

    // Храние данных
    private BigInteger a, b, x, y, d;

    /**
     * Получение вводных данных
     * @param a
     * @param b
     */
    public TheExtendedEuclideanAlgorithm(BigInteger a,
                                         BigInteger b)
    {
        this.a = a;
        this.b = b;
    }

    /**
     * Реализация самого алгоритма
     */
    public void methodAlg() {

        if (b.compareTo(Helper.CONST_0) == 0) {
            d = a;
            x = Helper.CONST_1;
            y = Helper.CONST_0;
            return;
        }

        BigInteger aTemp = getA();
        BigInteger bTemp = getB();
        BigInteger x2 = Helper.CONST_1;
        BigInteger x1 = Helper.CONST_0;
        BigInteger y2 = Helper.CONST_0;
        BigInteger y1 = Helper.CONST_1;
        BigInteger q, r;

        while (bTemp.compareTo(Helper.CONST_0) == 1) {

            q = aTemp.divide(bTemp);
            r = aTemp.subtract(q.multiply(bTemp));
            x = x2.subtract(q.multiply(x1));
            y = y2.subtract(q.multiply(y1));

            aTemp = bTemp;
            bTemp = r;
            x2 = x1;
            x1 = x;
            y2 = y1;
            y1 = y;
        }

        d = aTemp;
        x = x2;
        y = y2;
    }

    /**
     * Получение обратного числа для поля a
     * @return
     */
    public BigInteger getReverse() {
        methodAlg();
        if (d.compareTo(Helper.CONST_1) == 1)
            return null;
        else
            return x;

    }

    /**
     * Getter для всех полей
     * @return
     */
    public BigInteger getA() {
        return a;
    }

    public BigInteger getB() {
        return b;
    }

    public BigInteger getD() {
        return d;
    }

    public BigInteger getX() {
        return x;
    }

    public BigInteger getY() {
        return y;
    }
}
