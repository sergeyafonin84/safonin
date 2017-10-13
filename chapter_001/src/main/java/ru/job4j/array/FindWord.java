package ru.job4j.array;

public class FindWord {

    public boolean FindSubWordInWord(String word, String subWord) {

        char[] arrayWord = TransformWordToArray(word);
        char[] arraySubWord = TransformWordToArray(subWord);

        for (int i = 0; i < arrayWord.length - arraySubWord.length+1; i++) {
            for (int j = 0; j < arraySubWord.length; j++) {
                if (arraySubWord[j] != arrayWord[i + j]) {
                    break;
                }
                if (j == arraySubWord.length - 1) {
                    return true;
                }
            }
        }
        return  false;
    }

    private char[] TransformWordToArray(String word) {
        int lengthWord = word.length();
        char[] arrayWord = new char[lengthWord];
        for (int i = 0; i < lengthWord; i++) {
            arrayWord[i] = word.charAt(i);
        }
        return arrayWord;
    }
}