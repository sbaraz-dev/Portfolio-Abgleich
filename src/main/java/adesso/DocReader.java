package adesso;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DocReader {
    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\User\\IdeaProjects\\PortfolioVergleicher\\src\\main\\java\\files\\Inhaltsverzeichnis.txt";
        String path2 = "C:\\Users\\User\\IdeaProjects\\PortfolioVergleicher\\src\\main\\java\\files\\Übersicht.txt";

        List<String> inputLoB = new ArrayList<String>();
        List<String> inputOneA = new ArrayList<String>();

        loadFile(path, inputLoB);
        loadFile(path2, inputOneA);

        List<String> indivTopicsLoB = filterForTopics(inputLoB);
        List<String> indivTopicsOneA = filterForTopics(inputOneA);

        List<String> indivTopicsLoBCleaned = new ArrayList<String>();

        for (int i = 0; i < indivTopicsLoB.size(); i++) {
            try {
                indivTopicsLoB.add(indivTopicsLoB.get(i).split("\t")[1]);

            } catch (Exception e) {
                System.err.println(indivTopicsLoB.get(i).split("\t")[0]);
            }
        }

        for (int i = 0; i < indivTopicsLoBCleaned.size(); i++) {
            System.out.println(indivTopicsLoBCleaned.get(i));
        }

//        for (int i = 0; i < indivTopicsOneA.size(); i++) {
//            indivTopicsLoB.set(i, indivTopicsOneA.get(i).split("\t")[1]);
//        }

        for (String el : indivTopicsLoB) {
            System.out.println(el);
        }
//        for (String el : indivTopicsOneA) {
//            System.out.println(el);
//        }




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
