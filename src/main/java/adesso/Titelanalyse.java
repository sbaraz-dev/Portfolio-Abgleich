package adesso;

import java.io.*;
import java.util.*;

public class Titelanalyse {

    public static String path = "C:\\Users\\User\\IdeaProjects\\PortfolioVergleicher\\src\\main\\java\\files\\LoB Insurance.txt";
    public static String path2 = "C:\\Users\\User\\IdeaProjects\\PortfolioVergleicher\\src\\main\\java\\files\\One Adesso.txt";
    public static String save = "C:\\Users\\User\\IdeaProjects\\PortfolioVergleicher\\src\\main\\java\\files\\Auswertung.csv";
    public static String save2 = "C:\\Users\\User\\IdeaProjects\\PortfolioVergleicher\\src\\main\\java\\files\\Auswertung2.csv";


    public static void main(String[] args) throws IOException {

        ArrayList<String> lobInsuranceList = new ArrayList<String>();
        ArrayList<String> oneAdessoList = new ArrayList<String>();
        ArrayList<String> firstPickMatches = new ArrayList<String>();
        ArrayList<String> matchesList = new ArrayList<String>();

        HashMap<String, ArrayList<String>> hashMap = new HashMap<String, ArrayList<String>>();

        // Daten einlesen
        readFile(lobInsuranceList, path);
        readFile(oneAdessoList, path2);


        // Suchfunktion für volle Titel
        for (String el : lobInsuranceList) {
            for (String el2 : oneAdessoList) {
                if (el2.contains(el)) {
                    firstPickMatches.add(String.format("LoB Ins: \t \t %-25s  \t One Adesso: \t %-25s", el, el2));
                    addToHashmap(el, el2, hashMap);
                }
            }
        }
        for (String el : oneAdessoList) {
            for (String el2 : lobInsuranceList) {
                if (el2.contains(el)) {
                    firstPickMatches.add(String.format("One Adesso: \t \t %-25s  \t LoB Insurance: \t %-25s", el, el2));
                    addToHashmap(el, el2, hashMap);
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
                        addToHashmap(el, oneAdessoList.get(j), hashMap);
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
                        addToHashmap(el, lobInsuranceList.get(j), hashMap);
                    }
                }
            }
        }


        // Konsolenausgabe der Matches
/*        for (int i = 0; i < firstPickMatches.size(); i++) {
            System.out.println(firstPickMatches.get(i));
        }*/

/*        for (int i = 0; i < matchesList.size(); i++) {
            System.out.println(matchesList.get(i));
        }*/

        //System.out.println(hashMap.toString());
        //System.out.println(hashMap.toString().replaceAll(",", "\n"));

        String output = "";
        for (Map.Entry<String, ArrayList<String>> entry : hashMap.entrySet()) {
            output += String.format("%-70s\t %-70s\n", entry.getKey(), entry.getValue().get(0));
            for (int i = 1; i < entry.getValue().size(); i++) {
                output += String.format("%-70s\t %-70s\n", "", entry.getValue().get(i));
            }
            //System.out.println(entry.getKey() + entry.getValue().toString());
        }
        System.out.println(output);
        saveFile2(hashMap, save2, true);




        // Daten speichern

        //saveFile(firstPickMatches, save, false);
        //saveFile(matchesList, save, false);



    }













    public static void addToHashmap(String el1, String el2, HashMap<String, ArrayList<String>> hashMap) {
        if (hashMap.get(el1) != null){
            boolean alreadyIncluded = false;
            for (int i = 0; i < hashMap.get(el1).size(); i++) {
                if (hashMap.get(el1).get(i).contains(el2)) {
                    alreadyIncluded = true;
                }
            }
            if (!alreadyIncluded) {
                hashMap.get(el1).add(el2.replace(",", "~"));
            }
        } else {
            hashMap.put(el1, new ArrayList<String>(Arrays.asList(el2)));
        }
    }


    //Methoden
    public static void readFile(ArrayList arrayList, String path) throws FileNotFoundException {
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

    public static void saveFile(ArrayList<String> list, String path, boolean override) throws IOException {
        FileWriter fileWriter = new FileWriter(path, override);
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output = output.concat(list.get(i) + "\n");
        }
        fileWriter.write("Quelle \t Schlüsselwort \t Portfolioelement \t Ziel \t Portfolioelement \n");
        fileWriter.write(output);
        fileWriter.close();
    }

    public static void saveFile2(HashMap<String, ArrayList<String>> hashMap, String path, boolean override) throws IOException {
        FileWriter fileWriter = new FileWriter(path, override);
        String output = "";
        for (Map.Entry<String, ArrayList<String>> entry : hashMap.entrySet()) {
            output += String.format("%-70s\t %-70s\n", entry.getKey(), entry.getValue().get(0));
            for (int i = 1; i < entry.getValue().size(); i++) {
                output += String.format("%-70s\t %-70s\n", "", entry.getValue().get(i));
            }
        }
        fileWriter.write("Quelle \t Ziel \n");
        fileWriter.write(output);
        fileWriter.close();
    }

}
