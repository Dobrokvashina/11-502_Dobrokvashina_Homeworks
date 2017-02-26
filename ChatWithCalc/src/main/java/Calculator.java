/**
 * Created by Саоша on 21.12.2016.
 */
public class Calculator {

    private int result;

    public String doMath(String maths) {
        char[] math = maths.substring(1).toCharArray();
        result = 0;
        int cur = 0;
        int operation = 1;
        //0-пусто, 1-сложение, 2-вычитание, 3-умножение, 4-деление
        for (int i = 0; i < math.length; i++) {
            switch (math[i]) {
                case '+':
                    choseOper(operation, cur);
                    cur = 0;
                    operation = 1;
                    break;
                case '/':
                    choseOper(operation, cur);
                    cur = 0;
                    operation = 4;
                    break;
                case '-':
                    choseOper(operation, cur);
                    cur = 0;
                    operation = 2;
                    break;
                case '*':
                    choseOper(operation, cur);
                    cur = 0;
                    operation = 3;
                    break;
                case '1':
                    cur = cur * 10 + 1;
                    break;
                case '2':
                    cur = cur * 10 + 2;
                    break;
                case '3':
                    cur = cur * 10 + 3;
                    break;
                case '4':
                    cur = cur * 10 + 4;
                    break;
                case '5':
                    cur = cur * 10 + 5;
                    break;
                case '6':
                    cur = cur * 10 + 6;
                    break;
                case '7':
                    cur = cur * 10 + 7;
                    break;
                case '8':
                    cur = cur * 10 + 8;
                    break;
                case '9':
                    cur = cur * 10 + 9;
                    break;
                case '0':
                    cur = cur * 10;
                    break;
            }

        }
        choseOper(operation, cur);

        return maths.substring(1) + " = " + result;
    }

    private void choseOper(int operation, int cur) {
        if (operation != 0)
            switch (operation) {
                case 1:
                    result += cur;
                    break;
                case 2:
                    result -= cur;
                    break;
                case 3:
                    result *= cur;
                    break;
                case 4:
                    result /= cur;
                    break;
            }
    }
}
