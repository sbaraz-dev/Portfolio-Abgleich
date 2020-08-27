package basis;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static String path = "C:\\Users\\User\\IdeaProjects\\PortfolioVergleicher\\src\\main\\java\\files\\LoB Insurance.txt";
    public static String path2 = "C:\\Users\\User\\IdeaProjects\\PortfolioVergleicher\\src\\main\\java\\files\\One Adesso.txt";
    public static String save = "C:\\Users\\User\\IdeaProjects\\PortfolioVergleicher\\src\\main\\java\\files\\Auswertung.csv";


    public static void main(String[] args) throws IOException {

        ArrayList<String> lobInsuranceList = new ArrayList<String>();
        ArrayList<String> oneAdessoList = new ArrayList<String>();
        ArrayList<String> matchesList = new ArrayList<String>();


        readFile(path, lobInsuranceList);
        readFile(path2, oneAdessoList);

        // Suchfunktion für volle Titel

        for (String el : lobInsuranceList) {
            for (String el2 : oneAdessoList) {
                if (el2.contains(el)) {
                    matchesList.add(String.format("LoB Ins: \t \t %-25s  \t One Adesso: \t %-25s", el, el2));
                }
            }

        }

        for (String el : oneAdessoList) {
            for (String el2 : lobInsuranceList) {
                if (el2.contains(el)) {
                    matchesList.add(String.format("One Adesso: \t \t %-25s  \t LoB Insurance: \t %-25s", el, el2));
                }
            }

        }

        // Suchoption für einzelne Worte im Titel
        String[] taboo = {"im", "Ein", "die", "and", "of", "für", "alle", "for", "einen", "geben", "der", "das", "aus",
                "und", "", "von", "muss", "neue", "gedacht", "werden", "zur", "or", "in", "&", "E", "|", "des"};
        for (String el: lobInsuranceList) {
            String[] splitup = el.split(" ");
            for (int k = 0; k < splitup.length; k++) {
                splitup[k] = splitup[k].replaceAll("[-+.^:,/()]","");
            }
            for (String uel : splitup) {
                for (int j = 0; j < oneAdessoList.size(); j++) {
                    if (oneAdessoList.get(j).contains(uel) && !Arrays.asList(taboo).contains(uel)) {
                        matchesList.add(String.format("LoB Ins: \t %-20s \t %-70s  \t One Adesso: \t %-25s", uel, el, oneAdessoList.get(j)));
                    }
                }
            }
        }

        for (String el: oneAdessoList) {
            String[] splitup = el.split(" ");
            for (int k = 0; k < splitup.length; k++) {
                splitup[k] = splitup[k].replaceAll("[-+.^:,/()]","");
            }

            //System.out.println(Arrays.toString(splitup));

            for (String uel : splitup) {
                for (int j = 0; j < lobInsuranceList.size(); j++) {
                    if (lobInsuranceList.get(j).contains(uel) && !Arrays.asList(taboo).contains(uel)) {
                        matchesList.add(String.format("One Adesso: \t %-20s \t %-70s  \t LoB Ins: \t %-25s", uel, el, lobInsuranceList.get(j)));
                    }
                }
            }
        }



/*        for (int i = 0; i < matchesList.size(); i++) {
            System.out.println(matchesList.get(i));
        }*/

        System.out.println(lobInsuranceList.size());
        System.out.println(oneAdessoList.size());


        // FILEWRITER
        FileWriter fileWriter = new FileWriter(save, false);
        String output = "";
        for (int i = 0; i < matchesList.size(); i++) {
            output = output.concat(matchesList.get(i) + "\n");
        }
        fileWriter.write("Quelle \t Schlüsselwort \t Portfolioelement \t Ziel \t Portfolioelement \n");
        fileWriter.write(output);
        fileWriter.close();


    }

    public static void readFile(String path, ArrayList arrayList) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNextLine()) {
            arrayList.add(scanner.nextLine().replaceAll("•", "").replaceAll("o ", "").trim());
        }
        while (arrayList.contains("")) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i).equals("")) {
                    arrayList.remove(i);
                }
            }
        }
    }

/*    public static void saveFileInCSV(String name) throws IOException {
        FileWriter fileWriter = new FileWriter(String.format("%s%s.csv", save, name), false);


        fileWriter.write("Lob Insurance Portfolio, One Adesso Portfolio");
        fileWriter.write(output);
        fileWriter.close();
    }*/


}
