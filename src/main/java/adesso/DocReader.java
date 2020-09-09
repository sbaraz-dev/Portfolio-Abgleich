package adesso;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DocReader {
    public static void main(String[] args) throws IOException {

//        String path = "C:\\Users\\User\\IdeaProjects\\PortfolioVergleicher\\src\\main\\java\\files\\Inhaltsverzeichnis.txt";
//        String path2 = "C:\\Users\\User\\IdeaProjects\\PortfolioVergleicher\\src\\main\\java\\files\\Übersicht.txt";
        String path = "C:\\Users\\baraz\\IdeaProjects\\Portfolio-Abgleich\\src\\main\\java\\files\\Inhaltsverzeichnis.txt";
        String path2 = "C:\\Users\\baraz\\IdeaProjects\\Portfolio-Abgleich\\src\\main\\java\\files\\Übersicht.txt";

        List<String> inputLoB = new ArrayList<String>();
        List<String> inputOneA = new ArrayList<String>();

        // load the files
        loadFile(path, inputLoB);
        loadFile(path2, inputOneA);

        //clean the files
        List<String> indivTopicsLoB = filterForTopics(inputLoB);
        List<String> indivTopicsOneA = filterForTopics(inputOneA);

        List<String> indivTopicsLoBCleaned = new ArrayList<String>();
        List<String> indivTopicsOneACleaned = new ArrayList<String>();

        for (int i = 0; i < indivTopicsLoB.size(); i++) {
            try {
                indivTopicsLoBCleaned.add(indivTopicsLoB.get(i).split(". ", 2)[1]);
            } catch (Exception e) {
                System.err.println("Fehler bei:\t" + indivTopicsLoB.get(i));
                continue;
            }
        }

        for (int i = 0; i < indivTopicsOneA.size(); i++) {
            try {
                indivTopicsOneACleaned.add(indivTopicsOneA.get(i).split(". ", 2)[1]);
            } catch (Exception e) {
                System.err.println("Fehler bei:\t" + indivTopicsOneA.get(i));
                continue;
            }
        }


        for (String el : indivTopicsLoBCleaned) {
            System.out.println(el);
        }

        for (String el : indivTopicsOneACleaned) {
            System.out.println(el);
        }

    }




    public static void loadFile(String path, List<String> listName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        while (sc.hasNextLine()) {
            listName.add(sc.nextLine());
        }
    }

    public static long getCount(List<String> list, int index) {
        return list.get(index).split("\t")[0].chars().filter(ch -> ch == '.').count();
    }

    public static ArrayList<String> filterForTopics(List<String> list) {
        ArrayList<String> relist = new ArrayList<String>();
        for (int i = 0; i < list.size() - 1; i++) {
            if (getCount(list, i) >= 1 && getCount(list, i) >= getCount(list, i + 1)) {
                relist.add(list.get(i));
            }
        }
        return relist;
    }


}
