package main.java.courses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class InputFile {
    public static void main(String[] args) {
        int count = 0; // счетчик успешных файлов
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите путь к файлу:");
            String path = scanner.nextLine();
            if (fileExists(path) && !isDirectory(path)) {
                System.out.println("Путь указан верно");
                count += 1;
                System.out.println("Это файл №" + count);
                
                // Обработка файла
                try {
                    processFile(path);
                } catch (Exception ex) {
                    System.out.println("Произошла ошибка при обработке файла: " + ex.getMessage());
                }
            } else {
                System.out.println("Указанный файл не существует или указанный путь является путем к папке, а не к файлу");
            }
        }
    }

    private static boolean fileExists(String path) {
        File file = new File(path);
        return file.exists();
    }

    private static boolean isDirectory(String path) {
        File file = new File(path);
        return file.isDirectory();
    }

    // Метод для обработки файла
    private static void processFile(String path) throws Exception {
        int totalLines = 0;
        int maxLength = Integer.MIN_VALUE;
        int minLength = Integer.MAX_VALUE;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int length = line.length();

                // Проверка на длинную строку
                if (length > 1024) {
                    throw new RuntimeException("Строка длинее 1024 символов");
                }

                totalLines++;
                if (length > maxLength) {
                    maxLength = length;
                }
                if (length < minLength) {
                    minLength = length;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex; // пробрасываем дальше, чтобы остановить выполнение
        }

        // Вывод результатов
        System.out.println("Общее количество строк: " + totalLines);
        System.out.println("Длина самой длинной строки: " + maxLength);
        System.out.println("Длина самой короткой строки: " + minLength);
    }
}
