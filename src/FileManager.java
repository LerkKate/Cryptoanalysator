import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    public static List<String> readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        List<String> list = Files.readAllLines(path);
        return list;

    }

    public static void writeFile(List<String> encreptedText, Path filePathWrite) {
        try {
            if (!Files.exists(filePathWrite)) {
                Files.createFile(filePathWrite);
            }
            Files.write(filePathWrite, encreptedText);
            System.out.println("Создан файл: " + filePathWrite);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
