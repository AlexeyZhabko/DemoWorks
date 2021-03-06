package by.academy.jc.ht.zhabko;


import static by.academy.jc.io.FileSystemUtils.getResourcePath;

import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WordCounter {

    Map<String,Integer> frequencyMap;


    public boolean isContains (String word) {
        return frequencyMap.containsKey(word);
    }

    public int getFrequency (String word) {

        if (frequencyMap.containsKey(word)) {
            return frequencyMap.get(word);
        } else {
            return -1;
        }

    }


    public void countWord() throws IOException {

        FileReader input = null;
        StringBuilder allText = new StringBuilder();
        frequencyMap = new TreeMap<>();

        try {
            input = new FileReader(getResourcePath("Frankenstein.txt"));

            int c;
            while ((c = input.read()) != -1) {
                allText.append((char) c);
            }
        } finally {
            if (input != null) {
                input.close();
            }
        }
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(allText);

        while (matcher.find()) {
            String word = getRootWord(matcher.group());
            if (!frequencyMap.containsKey(word))  {
                frequencyMap.put(word,1);
            } else {
                int freq = frequencyMap.get(word);
                frequencyMap.put(word,freq+1);
            }
        }
        System.out.println(frequencyMap);
    }

    public boolean isVowel (char ch) {
        switch (ch) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
            default:
                return false;
        }
    }

    public boolean containsVowel (CharSequence word) {
        int i = 0;
        while (i < word.length()) {
             if (isVowel(word.charAt(i))) {
                 return true;
             }
             i++;
        }
        return false;
    }

    public boolean isCVC (CharSequence word) {
        if (word.length() < 3) {
            return false;
        }
        boolean lastCharNotXYW = !(word.charAt(word.length()-1) == 'w' || word.charAt(word.length()-1) == 'x' || word.charAt(word.length()-1) == 'y');
        boolean c1 = !isVowel(word.charAt(word.length()-3));
        boolean v = isVowel(word.charAt(word.length()-2));
        boolean c2 = !isVowel(word.charAt(word.length()-1));
        return c1 && v && c2 && lastCharNotXYW;
    }


    //Porter Stemmer Algorithm
    public String getRootWord(String word) {
        //Step 0
        StringBuilder rootWord = new StringBuilder(word.toLowerCase(Locale.ROOT));
        int m = 0;
        for (int i=0; i<rootWord.length()-1; i++) {
            if (isVowel(rootWord.charAt(i))&&!isVowel(rootWord.charAt(i+1))) {
                m++;
            }
        }


        //Step 1a

        int switcher = 0;

        if (rootWord.length() > 4 && switcher == 0 && (rootWord.subSequence(rootWord.length()-4,rootWord.length()).equals("sses"))) {
            rootWord.delete(rootWord.length()-4,rootWord.length());
            rootWord.append("ss");
            switcher++;
        }

        if (rootWord.length() > 3 && switcher == 0 && (rootWord.subSequence(rootWord.length()-3,rootWord.length()).equals("ies"))) {
            rootWord.delete(rootWord.length()-3,rootWord.length());
            rootWord.append("i");
            switcher++;
        }

        if (rootWord.length() > 2 && switcher == 0 && (rootWord.subSequence(rootWord.length()-2,rootWord.length()).equals("ss"))) {
            rootWord.delete(rootWord.length()-2,rootWord.length());
            rootWord.append("ss");
            switcher++;
        }

        if (switcher == 0 && (rootWord.charAt(rootWord.length()-1)=='s')) {
            rootWord.deleteCharAt(rootWord.length()-1);
            switcher++;
        }



        //Step 1b
        switcher = 0;


        if (rootWord.length() > 3 && switcher == 0 && ((m>1) && (rootWord.subSequence(rootWord.length()-3,rootWord.length()).equals("eed")))) {
            rootWord.delete(rootWord.length()-3,rootWord.length());
            rootWord.append("ee");
            switcher++;
        }

        if (rootWord.length() > 2 && switcher == 0 && ((rootWord.subSequence(rootWord.length()-2,rootWord.length()).equals("ed"))&&containsVowel(rootWord.subSequence(0,rootWord.length()-3)))) {
            rootWord.delete(rootWord.length()-2,rootWord.length());
            switcher++;
        }

        if (rootWord.length() > 3 && switcher == 0 && ((rootWord.subSequence(rootWord.length()-3,rootWord.length()).equals("ing"))&&containsVowel(rootWord.subSequence(0,rootWord.length()-4)))) {
            rootWord.delete(rootWord.length()-3,rootWord.length());
            switcher++;
        }

        //Step 1c (cleanup)
        if (rootWord.length() > 2 && switcher == 1 && rootWord.subSequence(rootWord.length()-2,rootWord.length()).equals("at")) {
            rootWord.delete(rootWord.length()-2,rootWord.length());
            rootWord.append("ate");
            switcher++;
        }

        if (rootWord.length() > 2 && switcher == 1 && rootWord.subSequence(rootWord.length()-2,rootWord.length()).equals("bl")) {
            rootWord.delete(rootWord.length()-2,rootWord.length());
            rootWord.append("ble");
            switcher++;
        }

        if (switcher == 1 && !(rootWord.charAt(rootWord.length()-1) == 'l' || rootWord.charAt(rootWord.length()-1) == 's' || rootWord.charAt(rootWord.length()-1) == 'z') && rootWord.charAt(rootWord.length()-1) == rootWord.charAt(rootWord.length()-2)) {
            rootWord.deleteCharAt(rootWord.length()-1);
            switcher++;
        }

        if (switcher == 1 && m >= 1 && isCVC(rootWord)) {
            rootWord.append("e");
        }

        switcher = 0;

        //Step 1c (y char)
        if (rootWord.length() > 1 && switcher == 0 && rootWord.charAt(rootWord.length()-1) == 'y' && containsVowel(rootWord.subSequence(0,rootWord.length()-1))) {
            rootWord.deleteCharAt(rootWord.length()-1);
            rootWord.append('i');
        }

        //Step 2
        if (rootWord.length() > 7 && switcher == 0 && m > 0 && rootWord.subSequence(rootWord.length()-7,rootWord.length()).equals("ational")) {
            rootWord.delete(rootWord.length()-7,rootWord.length());
            rootWord.append("ate");
            switcher++;
        }

        if (rootWord.length() > 7 && switcher == 0 && m > 0 && rootWord.subSequence(rootWord.length()-7,rootWord.length()).equals("ization")) {
            rootWord.delete(rootWord.length()-7,rootWord.length());
            rootWord.append("ize");
            switcher++;
        }

        if (rootWord.length() > 6 && switcher == 0 && m > 0 && rootWord.subSequence(rootWord.length()-6,rootWord.length()).equals("biliti")) {
            rootWord.delete(rootWord.length()-6,rootWord.length());
            rootWord.append("ble");
            switcher++;
        }

        switcher = 0;

        //Step 3
        if (rootWord.length() > 5 && switcher == 0 && m > 0 && rootWord.subSequence(rootWord.length()-5,rootWord.length()).equals("icate")) {
            rootWord.delete(rootWord.length()-5,rootWord.length());
            rootWord.append("ic");
            switcher++;
        }

        if (rootWord.length() > 3 && switcher == 0 && m > 0 && rootWord.subSequence(rootWord.length()-3,rootWord.length()).equals("ful")) {
            rootWord.delete(rootWord.length()-3,rootWord.length());
            switcher++;
        }

        if (rootWord.length() > 4 && switcher == 0 && m > 0 && rootWord.subSequence(rootWord.length()-4,rootWord.length()).equals("ness")) {
            rootWord.delete(rootWord.length()-4,rootWord.length());
            switcher++;
        }

        //Step 4
        if (rootWord.length() > 4 && switcher == 0 && m > 0 && rootWord.subSequence(rootWord.length()-4,rootWord.length()).equals("ance")) {
            rootWord.delete(rootWord.length()-4,rootWord.length());
            switcher++;
        }

        if (rootWord.length() > 3 && switcher == 0 && m > 0 && rootWord.subSequence(rootWord.length()-3,rootWord.length()).equals("ent")) {
            rootWord.delete(rootWord.length()-3,rootWord.length());
            switcher++;
        }

        if (rootWord.length() > 3 && switcher == 0 && m > 0 && rootWord.subSequence(rootWord.length()-3,rootWord.length()).equals("ive")) {
            rootWord.delete(rootWord.length()-3,rootWord.length());
            switcher++;
        }

        switcher = 0;

        //Step 5 (cleanup)

        if (switcher == 0 && m > 1 && rootWord.charAt(rootWord.length()-1) == 'e') {
            rootWord.deleteCharAt(rootWord.length()-1);
            switcher++;
        }

        if (rootWord.length() > 4 && switcher == 0 && m >= 1 && !isCVC(rootWord) && rootWord.subSequence(rootWord.length()-4,rootWord.length()).equals("ness")) {
            rootWord.delete(rootWord.length()-4,rootWord.length());
        }

        //Step 5b
        switcher = 0;

        if (rootWord.length() > 2 && m > 1 && rootWord.charAt(rootWord.length()-1) == 'l' && rootWord.charAt(rootWord.length()-2) == 'l') {
            rootWord.deleteCharAt(rootWord.length()-1);
        }

        return rootWord.toString();
    }
}
