package ru.itis.inform;

public class FareySequenceGeneratorArrayImpl {

    public FareySequenceGeneratorArrayImpl() {
    }

    public RationalNumber[] generate(int n) {

        RationalNumber[] rationalNumbers = new RationalNumber[n * n];

        rationalNumbers[0] = new RationalNumber(0, 1);
        rationalNumbers[1] = new RationalNumber(1, 1);

        for (int i = 2; i < n*n ;i++) {
            rationalNumbers[i] = null;
        }

        int count = 2;
        int determinator1;
        int determinator2;

        for (int i = 2; i <= n; i++)
            for (int j = 0; j <= count - 2; j++) {

                if (rationalNumbers[j] != null && rationalNumbers[j+1] != null ) {

                    determinator2 = rationalNumbers[j].getB();
                    determinator1 = rationalNumbers[j + 1].getB();

                    if (determinator2 + determinator1 == i) {
                         count++;

                        RationalNumber newOne = new RationalNumber((rationalNumbers[j].getA() + rationalNumbers[j + 1].getA()), (rationalNumbers[j].getB() + rationalNumbers[j + 1].getB()));
                        for (int l = count; l > j ; l--) {
                            rationalNumbers[l] = rationalNumbers[l-1];
                        }
                         rationalNumbers[j + 1] = newOne;
                }
                }
            }
        return rationalNumbers;
    }

}
