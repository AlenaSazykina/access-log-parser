/* Калькулятор */
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        System.out.println("Введите первое число:");
        int firstNumber = getInt();
        System.out.println("Введите второе число:");
        int secondNumber = getInt();
        System.out.println("Сложение " + firstNumber + " + " + secondNumber + " = " + add(firstNumber, secondNumber));
        System.out.println("Вычетание " + firstNumber + " - " + secondNumber + " = " + sub(firstNumber, secondNumber));
        System.out.println("Умножение " + firstNumber + " * " + secondNumber + " = " + multi(firstNumber, secondNumber));
        System.out.println("Деление " + firstNumber + " / " + secondNumber + " = " + division(firstNumber, secondNumber));
    }

    public static int getInt()  {
        int num;
        if (scanner.hasNextInt()){
            num = scanner.nextInt();
        }
        else {
            System.out.println("Вы допустили ошибку при вводе числа. Попробуйте еще раз.");
            scanner.next();
            num = getInt();
        }
        return num;
    }


    public static int add (int firstNumber, int secondNumber) {
        int operation_add = firstNumber + secondNumber;
        return operation_add;
    }

    public static int sub (int firstNumber, int secondNumber) {
        int operation_sub = firstNumber - secondNumber;
        return operation_sub;
    }

    public static int multi (int firstNumber, int secondNumber) {
        int operation_multi = firstNumber * secondNumber;
        return operation_multi;

    }

    public static double division (double firstNumber, int secondNumber) {
        double operation_division = firstNumber / secondNumber;
        return operation_division;

    }

}
