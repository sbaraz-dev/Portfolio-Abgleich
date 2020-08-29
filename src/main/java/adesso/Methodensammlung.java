package adesso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Methodensammlung {


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
    public static void loadFile(ArrayList arrayList, String path) throws FileNotFoundException {
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

    public static void saveFile(ArrayList<String> list, String path, boolean append) throws IOException {
        FileWriter fileWriter = new FileWriter(path, append);
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
