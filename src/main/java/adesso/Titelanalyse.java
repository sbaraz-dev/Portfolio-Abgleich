package adesso;

import java.io.*;
import java.util.*;

public class Titelanalyse {

    public static String path = "C:\\Users\\User\\IdeaProjects\\PortfolioVergleicher\\src\\main\\java\\files\\LoB Insurance.txt";
    public static String path2 = "C:\\Users\\User\\IdeaProjects\\PortfolioVergleicher\\src\\main\\java\\files\\One Adesso.txt";
    public static String save = "C:\\Users\\User\\IdeaProjects\\PortfolioVergleicher\\src\\main\\java\\files\\Auswertung.csv";
    public static String save2 = "C:\\Users\\User\\IdeaProjects\\PortfolioVergleicher\\src\\main\\java\\files\\Auswertung2.csv";


    public static ArrayList<String> lobInsuranceList = new ArrayList<String>();
    public static ArrayList<String> oneAdessoList = new ArrayList<String>();
    public static ArrayList<String> firstPickMatches = new ArrayList<String>();
    public static ArrayList<String> matchesList = new ArrayList<String>();
    public static HashMap<String, ArrayList<String>> hashMap = new HashMap<String, ArrayList<String>>();


    public static void main(String[] args) throws IOException {

        // Daten einlesen
        Methodensammlung.loadFile(lobInsuranceList, path);
        Methodensammlung.loadFile(oneAdessoList, path2);


        // Suchfunktion für volle Titel
        for (String el : lobInsuranceList) {
            for (String el2 : oneAdessoList) {
                if (el2.contains(el)) {
                    firstPickMatches.add(String.format("LoB Ins: \t \t %-25s  \t One Adesso: \t %-25s", el, el2));
                    Methodensammlung.addToHashmap(el, el2, hashMap);
                }
            }
        }
        for (String el : oneAdessoList) {
            for (String el2 : lobInsuranceList) {
                if (el2.contains(el)) {
                    firstPickMatches.add(String.format("One Adesso: \t \t %-25s  \t LoB Insurance: \t %-25s", el, el2));
                    Methodensammlung.addToHashmap(el, el2, hashMap);
                }
            }

        }


        // Suchoption für einzelne Worte im Titel
/*        String[] taboo = {"im", "Ein", "die", "and", "of", "für", "alle", "for", "einen", "geben", "der", "das", "aus",
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
                        Methodensammlung.addToHashmap(el, oneAdessoList.get(j), hashMap);
                    }
                }
            }
        }
        for (String el: oneAdessoList) {
            String[] splitup = el.split(" ");
            for (int k = 0; k < splitup.length; k++) {
                splitup[k] = splitup[k].replaceAll("[-+.^:,/()]","");
            }
            for (String uel : splitup) {
                for (int j = 0; j < lobInsuranceList.size(); j++) {
                    if (lobInsuranceList.get(j).contains(uel) && !Arrays.asList(taboo).contains(uel)) {
                        matchesList.add(String.format("One Adesso: \t %-20s \t %-70s  \t LoB Ins: \t %-25s", uel, el, lobInsuranceList.get(j)));
                        Methodensammlung.addToHashmap(el, lobInsuranceList.get(j), hashMap);
                    }
                }
            }
        }*/


        // Konsolenausgabe der Matches
//       for (int i = 0; i < firstPickMatches.size(); i++) {
//            System.out.println(firstPickMatches.get(i));
//        }
//
//        for (int i = 0; i < matchesList.size(); i++) {
//            System.out.println(matchesList.get(i));
//        }

        String output = "";
        for (Map.Entry<String, ArrayList<String>> entry : hashMap.entrySet()) {
            output += String.format("%-70s\t %-70s\n", entry.getKey(), entry.getValue().get(0));
            for (int i = 1; i < entry.getValue().size(); i++) {
                output += String.format("%-70s\t %-70s\n", "", entry.getValue().get(i));
            }
            //System.out.println(entry.getKey() + entry.getValue().toString());
        }
        System.out.println(output);
        System.out.println(hashMap.size());


        // Daten speichern

/*        Methodensammlung.saveFile(firstPickMatches, save, false);
        Methodensammlung.saveFile(matchesList, save, true);
        Methodensammlung.saveFile2(hashMap, save2, false);*/



    }
}
