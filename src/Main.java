import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        // комментарий
        while (true) {
            System.out.println("Укажите,что Вы хотите сделать: 1-Шифровать , 2-Дешифровать , 3-Взломать , 4- выход ? ");
            int action = console.nextInt();
            while (!Validator.correctAction(action)) {
                System.out.println("Неподдерживаемое действие. Выбирайте: 1 или 2 или 3 или 4");
                action = console.nextInt();
            }
            console.nextLine();
            switch (action) {
                case 1:
                    // action = 1;
                {
                    System.out.println("Введите путь исходного текста");
                    String filePath = console.nextLine();
                    while (!Validator.isFileExists(filePath)) {
                        System.out.println("Файла не существует. Укажите корректный путь: ");
                        filePath = console.nextLine();
                    }
                    System.out.println("Введите ключ");
                    int shifr = console.nextInt();
                    while (!Validator.isValidKey(shifr, Cipher.alphabet)) {
                        System.out.println("Неверный шифр. Диапазон в пределах: 0 - 40");
                        shifr = console.nextInt();
                    }
                    console.nextLine();
                    //шифруем
                    List<String> rededList = FileManager.readFile(filePath);
                    List<String> encryptedList = new ArrayList<>();
                    for (String str : rededList) {
                        encryptedList.add(Cipher.encrypt(str.toLowerCase().toCharArray(), shifr));
                    }

                    System.out.println("Введите путь для записи зашифрованного текста");
                    String filePathWrite = console.nextLine();

                    Path encryptfilePathWrite = Paths.get(filePathWrite);
                    FileManager.writeFile(encryptedList, encryptfilePathWrite);

                    System.out.println("Файл зашифрован. ");
                    break;
                }

                case 2:
                    // action = 2;
                {
                    System.out.println("Введите путь зашифрованного текста");
                    String filePath = console.nextLine();
                    while (!Validator.isFileExists(filePath)) {
                        System.out.println("Файла не существует. Укажите корректный путь: ");
                        filePath = console.nextLine();
                    }
                    System.out.println("Введите ключ");
                    int shifr = console.nextInt();
                    while (!Validator.isValidKey(shifr, Cipher.alphabet)) {
                        System.out.println("Неверный шифр. Диапазон в пределах: 0 - 40");
                        shifr = console.nextInt();
                    }
                    console.nextLine();
                    List<String> rededList2 = FileManager.readFile(filePath);
                    List<String> decryptedList = new ArrayList<>();
                    for (String str : rededList2) {
                        decryptedList.add(Cipher.decrypt(str.toCharArray(), shifr));
                    }
                    System.out.println("Введите путь для записи дешифрованного текста");
                    String filePathWrite = console.nextLine();
                    Path decryptfilePathWrite = Paths.get(filePathWrite);
                    FileManager.writeFile(decryptedList, decryptfilePathWrite);

                    System.out.println("Файл дешифрован.");
                    break;

                }
                case 3:
                    //  action = 3;
                {
                    System.out.println("Введите путь зашифрованного текста");
                    String filePath = console.nextLine();
                    while (!Validator.isFileExists(filePath)) {
                        System.out.println("Файла не существует. Укажите корректный путь: ");
                        filePath = console.nextLine();
                    }
                    Path filePathDecryptText = Paths.get(filePath);
                    BruteForce.decryptByBruteForce(filePathDecryptText, Cipher.alphabet);
                    break;

                }
                case 4:
                    //  action = 4;
                {
                    System.exit(0);
                }
            }
        }
    }
}