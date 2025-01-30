import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;


public class Main {

    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame("My First GUI"); // Для окна нужна "рама" - Frame
        // стандартное поведение при закрытии окна - завершение приложения
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300); // размеры окна
        frame.setLocationRelativeTo(null); // окно - в центре экрана
        JButton button = new JButton("Шифровать"); // Экземпляр класса JButton
        // getContentPane() - клиентская область окна
        frame.getContentPane().add(button); // Добавляем кнопку на Frame
        frame.setVisible(true); // Делаем окно видимым



        Scanner console = new Scanner(System.in);
        String filePath = console.nextLine();
        while (!Validator.isFileExists(filePath)) {
            System.out.println("Файла не существует. Укажите корректный путь: ");
            filePath = console.nextLine();
        }

        int shifr = console.nextInt();
        while (!Validator.isValidKey(shifr, Cipher.alphabet)) {
            System.out.println("Неверный шифр. Диапазон в пределах: 0 - 40");
            shifr = console.nextInt();
        }
        Path encryptfilePathWrite = Paths.get("C:\\Programmist\\JavaRush\\new proekt\\encryptedText.txt");
        Path decryptfilePathWrite = Paths.get("C:\\Programmist\\JavaRush\\new proekt\\decryptedText.txt");

        try {
            //шифруем
            List<String> rededList = FileManager.readFile(filePath);
            List<String> encryptedList = new ArrayList<>();
            for (String str : rededList) {
                encryptedList.add(Cipher.encrypt(str.toLowerCase().toCharArray(), shifr));
            }
            //String encreptedText= Cipher.encrypt(FileManager.readFile(filePath),shifr);
            FileManager.writeFile(encryptedList, encryptfilePathWrite);

            //дешифруем
            List<String> rededList2 = FileManager.readFile(encryptfilePathWrite.toString());
            List<String> decryptedList = new ArrayList<>();
            for (String str : rededList2) {
                decryptedList.add(Cipher.decrypt(str.toCharArray(), shifr));
            }
            FileManager.writeFile(decryptedList, decryptfilePathWrite);


              BruteForce.decryptByBruteForce(encryptfilePathWrite,Cipher.alphabet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}