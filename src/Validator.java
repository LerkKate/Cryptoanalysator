import java.nio.file.Files;
import java.nio.file.Path;

public class Validator {
    public static boolean isValidKey(int shifr, char[] alphabet) {
        boolean isValidKey = false;
        if ((shifr < alphabet.length) && (shifr >= 0)) {
            isValidKey = true;
        }
        return isValidKey;
    }

    public static boolean isFileExists(String filePath) {
        if (filePath == "") {
            return false;
        }
        boolean isFileExists = false;
        if (Files.exists(Path.of(filePath))) {
            isFileExists = true;
        }
        return isFileExists;
    }

    public static boolean correctAction(int action) {
        boolean correctAction = false;
        if (action <= 4 && action > 0) {
            correctAction = true;
        }
        return correctAction;
    }

}