import java.util.Random;
import java.util.Scanner;

public class Main {
    // Метод для преобразования строки в целое число
    static int convertToInt(String string) {
        int intNumber = 0;
        for (int i = 0; i < string.length(); i++) {
            // умножение на 10, сдвигает текущее значение на один разряд влево
            // вычитая '0' из символа, мы получаем числовое значение этой цифры
            intNumber = intNumber * 10 + (string.charAt(i) - '0');
        }
        return intNumber;
    }

    // Метод проверки является ли строка, введённая пользователем, числом
    static boolean isNumber(String string) {
        // Если строка пустая, возвращаем false
        if (string.isEmpty()) {
            return false;
        }
        for (int i = 0; i < string.length(); i++) { // Проходим по каждому символу строки
            // Проверяем, является ли символ цифрой
            if (!Character.isDigit(string.charAt(i))) {
                return false; // Если хотя бы один символ не цифра, возвращаем false
            }
        }
        // Если все символы цифры, возвращаем true
        return true;
    }

    public static void main(String[] args) {
        Random rnd = new Random();
        Scanner scanner = new Scanner(System.in);
        int bestAttempts = 100; // Переменная для хранения лучших попыток, присваиваем переменной максимальное количество попыток
        boolean continuePlay; // переменная для продолжения игры
        System.out.println("Отгадайте число от 1 до 100.");
        do {
            // если 100 - число от 0 по 99. Если 101 - число от 0 по 100. Поэтому rnd.nextInt(100) + 1 - будет от 1 по 100
            int hiddenNumber = rnd.nextInt(100) + 1; // загаданное число
            int countAttempts = 0; // счётчик попыток
            int userNumber = 0; // число, вводимое пользователем

            while (userNumber != hiddenNumber) {
                System.out.print("Что можно ввести: 1) «RESULT» - просмотр статистики; 2) «СТОП» - завершить текущую игру; 3) Целое число от 1 до 100: ");
                String inputLine = scanner.nextLine();

                // Метод equalsIgnoreCase сравнивает с заданным в параметрах метода, игнорируя регистр. Тип данных на выходе - boolean.
                // Если пользователь введет RESULT - выводим статистку
                if (inputLine.equalsIgnoreCase("RESULT")) {
                    System.out.println("Текущие попытки: " + countAttempts);
                    if (bestAttempts == 100) {
                        System.out.println("Нет завершённых игр.");
                    } else {
                        System.out.println("Рекорд по наименьшему количеству попыток: " + bestAttempts);
                    }

                } else if (inputLine.equalsIgnoreCase("СТОП")) {
                    System.out.println("Вы завершили игру");
                    System.out.println("Рекорд по наименьшему количеству попыток: " + bestAttempts);
                    break;
                    // проверяем, если введенная строка полностью число, используя созданный метод
                } else if (isNumber(inputLine)) {
                    // то преобразуем строку в число, используя созданный метод
                    userNumber = convertToInt(inputLine);
                    countAttempts++; //увеличиваем кол-во попыток на 1
                    // Блок проверки числа
                    if (userNumber >= 1 && userNumber <= 100) { // вводимое число должно быть от 1 до 100
                        if (userNumber < hiddenNumber) {
                            System.out.println("Я сам в шоке, но, загаданное число БОЛЬШЕ, брат");
                        } else if (userNumber > hiddenNumber) {
                            System.out.println("Не ожидал от тебя такого. Загаданное число МЕНЬШЕ, брат");
                        } else {
                            System.out.println("Ты угадал! Это было число: " + userNumber + ". Количество попыток, за которое ты смог угадать число в этой игре - " + countAttempts);
                            // если в текущей игре число было угадано за меньшее кол-во раз, присваиваем это значение переменной bestAttempts
                            if (countAttempts < bestAttempts) {
                                bestAttempts = countAttempts;
                                System.out.println("Установлен новый рекорд: " + bestAttempts);
                                System.out.println();
                            } else {
                                System.out.println("Рекорд по наименьшему количеству попыток: " + bestAttempts);
                            }
                        }
                    } else {
                        System.out.println("Введённое число вне диапазона, нужно вводить цело число от 1 до 100");
                    }
                } else {
                    System.out.println("Некорректный ввод. Введите «RESULT», либо число.");
                }
            }

            System.out.print("Чтобы сыграть ещё раз - введите «ДА», чтобы завершить игру - введите любой символ: ");

            // Проверяем, желает ли пользователь продолжить игру
            continuePlay = scanner.nextLine().equalsIgnoreCase("Да");

        } while (continuePlay); // Повтор цикла, если ответ да, т.е. у continuePlay значение - true
        scanner.close();
    }
}