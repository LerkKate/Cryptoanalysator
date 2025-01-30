import java.util.List;

public class Cipher {
    public static final char[] alphabet = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public static String encrypt(char[] chars, int shifr) {
        String finalText = "";
        //перевод каждой строки из листа в массив чаров

        for (int i = 0; i < chars.length; i++) {
            int index = 0;
            boolean needEncrypt = false;
            //Внутренний цикл для определения индекса текущего символа в алфавите
            for (int j = 0; j < alphabet.length; j++) {
                if (chars[i] == alphabet[j]) {
                    index = j;
                    needEncrypt = true;
                    break;
                }
            }
            // Определение позиции зашифрованного элемента
            // Условие в ситуации выхода зашифрованного элемента за границы алфавита
            int encryptedIndex = index + shifr;
            if (encryptedIndex >= alphabet.length) {
                encryptedIndex -= alphabet.length;
            }
            if (needEncrypt) {
                chars[i] = alphabet[encryptedIndex];
            }
            String tmpText;
            tmpText = Character.toString(chars[i]);
            finalText += tmpText;
        }
        System.out.println(finalText);
        return finalText;
    }

    public static String decrypt(char[] chars, int shifr) {
        String finalText = "";
        for (int i = 0; i < chars.length; i++) {
            int index = 0;
            boolean needEncrypt = false;
            //Внутренний цикл для определения индекса текущего символа в алфавите
            for (int j = 0; j < alphabet.length; j++) {
                if (chars[i] == alphabet[j]) {
                    index = j;
                    needEncrypt = true;
                    break;
                }
            }
            // Определение позиции зашифрованного элемента
            // Условие в ситуации выхода зашифрованного элемента за границы алфавита

            int decryptedIndex = index - shifr;
            if (decryptedIndex < 0) {
                decryptedIndex += alphabet.length;
            }
            if (needEncrypt) {
                chars[i] = alphabet[decryptedIndex];
            }
            String tmpText;
            tmpText = Character.toString(chars[i]);
            finalText += tmpText;
        }
        //System.out.println(finalText);
        return finalText;
    }
}
