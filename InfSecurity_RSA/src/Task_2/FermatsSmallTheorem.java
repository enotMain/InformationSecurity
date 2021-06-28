package Task_2;

import java.math.BigInteger;

// Реализаци Малой теоремы Ферма
public class FermatsSmallTheorem {

    // ??? Теорема Эйлера ???

    /**
     * Малая теорема Ферма с проверкой на следующее условие: целое a и простое b взаимопросты? Если да, то
     * возврат a^(p-2)
     * @param a
     * @param b
     * @return
     */
    public static BigInteger fermaMethod(BigInteger a,
                                         BigInteger b)
    {
        if (a.mod(b) != Helper.CONST_0)
            return a.modPow(b.subtract(Helper.BASE_2), b);
        else
            return BigInteger.ZERO;

    }
}