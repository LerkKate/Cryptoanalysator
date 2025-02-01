import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BruteForce {
    public static void decryptByBruteForce(Path encryptfilePathWrite, char[] alphabet) throws IOException {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите путь эталонного зашифрованного файла для сравнения");
        String etalon = console.nextLine();
        while (!Validator.isFileExists(etalon)) {
            System.out.println("Файла не существует. Укажите корректный путь: ");
            etalon = console.nextLine();
        }
        Path pathEtalon = Paths.get(etalon);
        List<String> reference = Files.readAllLines(pathEtalon);
        int bestKey = 0;
        int maxSovpad = 0;
        List<String> bestList = new ArrayList<>();

        //файл для записи расшифрованного брутом текста
        System.out.println("Введите путь для записи дешифрованного текста");
        String textfinal = console.nextLine();
        Path filePathWrite = Paths.get(textfinal);
        for (int i = 0; i < alphabet.length; i++) {
            int countSovpad = 0;
            //System.out.println("Пробую ключ " + i);
            //получение списка расшифрованных строк по ключу i
            List<String> encryptedText = FileManager.readFile(encryptfilePathWrite.toString());
            List<String> decryptedList = new ArrayList<>();
            for (String str : encryptedText) {
                decryptedList.add(Cipher.decrypt(str.toLowerCase().toCharArray(), i));
            }
            // для каждой расшифрованной строки
            for (String decryptedString : decryptedList) {
                // для каждого слова в этой строке
                for (String word : decryptedString.split(" ")) {
                    // проверяем наличие в референсе
                    for (String referenceString : reference) {
                        if (referenceString.contains(word)) {
                            countSovpad++;
                            break;
                        }
                    }
                }
            }
            //раскомментируем,если хотим видеть кол-во совпадений с каждым ключом
//            if (countSovpad > 0) {
//                System.out.println("С ключом <%s> найдено %s совпадений с эталоном".formatted(i, countSovpad));
//            } else {
//                System.out.println("С ключом %s совпадений с эталоном не найдено".formatted(i));
//            }
            if (maxSovpad < countSovpad) {
                maxSovpad = countSovpad;
                bestKey = i;
                bestList = decryptedList;
            }
        }
        FileManager.writeFile(bestList,filePathWrite);
        System.out.println("Подобранный Ключ = " + bestKey + " Количество совпадений с эталоном " + maxSovpad + ". Записан файл с результатом: " + filePathWrite);
    }
}

