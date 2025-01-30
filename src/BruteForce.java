import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BruteForce {
    public static void decryptByBruteForce(Path encryptfilePathWrite, char[] alphabet) throws IOException {
        Path path = Paths.get("C:\\Programmist\\JavaRush\\new proekt\\ideal.txt");
        List<String> reference = Files.readAllLines(path);
        int bestKey = 0;
        int maxSovpad = 0;
        List<String> bestList = new ArrayList<>();

        //файл для записи расшифрованного брутом текста
        Path filePathWrite = Paths.get("C:\\Programmist\\JavaRush\\new proekt\\ByBrutForce.txt");
        for (int i = 0; i < alphabet.length; i++) {
            int countSovpad = 0;
            System.out.println("Пробую ключ " + i);
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
            if (countSovpad > 0) {
                System.out.println("С ключом <%s> найдено %s совпадений с эталоном".formatted(i, countSovpad));
            } else {
                System.out.println("С ключом %s совпадений с эталоном не найдено".formatted(i));
            }
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

