import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.HashMap;
public class ZooAnimals {
    public static void main(String[] args){
        HashMap<String, String[]> allAnimalData = new HashMap<String, String[]>();
        HashMap<String, Boolean> usedNames = new HashMap<String, Boolean>();
        Random rand = new Random();
        String[][] zooIds = new String[4][4];
        genZooHabitats(zooIds, allAnimalData);

        // Get Names
        String animalNamesFilePath = new File("").getAbsolutePath();
        animalNamesFilePath = animalNamesFilePath.concat("\\animalNames.txt");
        String[] animalNames = new String[0];
        try {
            String names = "";
            File namesFile = new File(animalNamesFilePath);
            Scanner scannerPhil = new Scanner(namesFile);
            while (scannerPhil.hasNextLine()){
                String data = scannerPhil.nextLine();
                if (data.contains(",")){
                    names = names.concat(data + ";");
                }
            }
            scannerPhil.close();
            animalNames = names.split(";");
        } catch (FileNotFoundException e){
            System.out.println("who");
            e.printStackTrace();
        }
        String[][] allNames = {animalNames[0].split(", "),animalNames[1].split(", "),animalNames[2].split(", "),animalNames[3].split(", ")};
        // Get Arriving Animal Data
        String arrivingDataFilePath = new File("").getAbsolutePath();
        arrivingDataFilePath = arrivingDataFilePath.concat("\\arrivingAnimals.txt");
        String[][] unParsedData = new String[0][0];
        int lineCount = 0;
        try {
            File dataFile = new File(arrivingDataFilePath);
            Scanner scannerBob = new Scanner(dataFile);
            while (scannerBob.hasNextLine()){
                Scanner scanner = new Scanner(dataFile);
                while (scanner.hasNextLine()){
                    lineCount++;
                    scannerBob.nextLine();
                    scanner.nextLine();
                }
                scannerBob.close();
                scanner.close();

            } catch (FileNotFoundException e){
                System.out.println("Bad");
                e.printStackTrace();
            }
            try {
                File dataFile = new File(arrivingDataFilePath);
                unParsedData = new String[lineCount][0];
                Scanner scannerBob = new Scanner(dataFile);
                Scanner scanner = new Scanner(dataFile);
                int i = 0;
                while (scannerBob.hasNextLine()){
                    unParsedData[i] = scannerBob.nextLine().split(", ");
                    while (scanner.hasNextLine()){
                        unParsedData[i] = scanner.nextLine().split(", ");
                        i++;
                    }
                    scannerBob.close();
                    scanner.close();
                } catch (FileNotFoundException e){
                    System.out.println("Bad");
                    e.printStackTrace();
                }
                // Get Zoo Data
                String zooDataFilepath = new File("").getAbsolutePath();
                zooDataFilepath = zooDataFilepath.concat("\\zooData.txt");
                String[] unParsedZooData = new String[0];
                lineCount = 0;
                try {
                    File dataFile = new File(zooDataFilepath);
                    Scanner scannerBob = new Scanner(dataFile);
                    while (scannerBob.hasNextLine()){
                        Scanner scanner = new Scanner(dataFile);
                        while (scanner.hasNextLine()){
                            lineCount++;
                            scannerBob.nextLine();
                            scanner.nextLine();
                        }
                        scannerBob.close();
                        scanner.close();

                    } catch (FileNotFoundException e){
                        System.out.println("Bad");
                        e.printStackTrace();
                    }

                    try {
                        File dataFile = new File(zooDataFilepath);
                        Scanner scannerBob = new Scanner(dataFile);
                        Scanner scanner = new Scanner(dataFile);
                        unParsedZooData = new String[lineCount];
                        int i = 0;
                        while (scannerBob.hasNextLine()){
                            unParsedZooData[i] = scannerBob.nextLine();
                            while (scanner.hasNextLine()){
                                unParsedZooData[i] = scanner.nextLine();
                                i++;
                            }
                            scannerBob.close();
                            scanner.close();

                        } catch (FileNotFoundException e){
                            System.out.println("Bad");
                            e.printStackTrace();
                        }
                        for (int i = 0; i < lineCount; i++) {
                            addData(unParsedZooData[i], allAnimalData, usedNames, zooIds);
                        }
                        for (int i = 0; i < 16; i++) {
                            addAnimal(unParsedData[i], allAnimalData, allNames, usedNames, zooIds);
                        }
                        updatePopulation(allAnimalData, zooIds);
                    }

                    public static void genZooHabitats(String[][] zoo, HashMap<String, String[]> map){
                        zoo  = new String[4][4];
                        map.clear();
                    }

                    public static void updatePopulation(HashMap<String, String[]> allAnimalData, String[][] zoo){
                        String zooDataFilepath = new File("").getAbsolutePath();
                        zooDataFilepath = zooDataFilepath.concat("\\zooData.txt");
                        try {
                            FileWriter writer = new FileWriter(zooDataFilepath);
                            for (int i = 0; i < 4; i++){
                                for (int j = 0; j < 4; j++) {
                                    if (zoo[i][j] != null) {
                                        writer.write(toTextData(allAnimalData.get(zoo[i][j]), zoo[i][j]) + "\n");
                                    }
                                }
                            }
                            writer.close();
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                        String zooPopFilepath = new File("").getAbsolutePath();
                        zooPopFilepath = zooPopFilepath.concat("\\zooPopulation.txt");
                        try {
                            FileWriter writer = new FileWriter(zooPopFilepath);
                            writer.write("Hyena Habitat: \n\n");
                            for (int i = 0; i < 4; i++){
                                if (zoo[0][i] != null) {
                                    writer.write(toText(zoo[0][i], allAnimalData) + "\n");
                                }
                            }
                            writer.write("\nLion Habitat: \n\n");
                            for (int i = 0; i < 4; i++){
                                if (zoo[1][i] != null) {
                                    writer.write(toText(zoo[1][i], allAnimalData) + "\n");
                                }
                            }
                            writer.write("\nBear Habitat: \n\n");
                            for (int i = 0; i < 4; i++){
                                if (zoo[2][i] != null) {
                                    writer.write(toText(zoo[2][i], allAnimalData) + "\n");
                                }
                            }
                            writer.write("\nTiger Habitat: \n\n");
                            for (int i = 0; i < 4; i++){
                                if (zoo[3][i] != null) {
                                    writer.write(toText(zoo[3][i], allAnimalData) + "\n");
                                }
                            }
                            writer.close();
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    public static String toText(String id, HashMap<String, String[]> allAnimalData){
                        String text;
                        String[] data = allAnimalData.get(id);
                        text = id + "; " + data[7] + "; " + data[0] + " years old; birthdate: " + data[1] + "; " + data[2] + " color; " + data[8] + "; " + data[3] + " pounds; from " + data[4] + ", " + data[5];
                        return text;
                    }
                    public static String toTextData(String[] data, String id){
                        String text = "";
                        for (String t: data){
                            text = text + t + "; ";
                        }
                        text = text + id;
                        return text;
                    }
                    public static void addData(String data, HashMap<String, String[]> allAnimalData, HashMap<String, Boolean> usedNames, String[][] zoo){
                        String[] currentAnimalData = new String[9];
                        String[] splitData = data.split("; ");
                        for (int i = 0; i < 9; i++){
                            currentAnimalData[i] = splitData[i];
                        }
                        allAnimalData.put(splitData[9], currentAnimalData);
                        int speciesId = 0;
                        switch (splitData[9].split("0")[0]) {
                            case "Hy":
                                speciesId = 0;
                                break;
                            case "Li":
                                speciesId = 1;
                                break;
                            case "Be":
                                speciesId = 2;
                                break;
                            case "Ti":
                                speciesId = 3;
                                break;
                            default:
                                speciesId = 0;
                        }
                        zoo[speciesId][Integer.parseInt(splitData[9].split("0")[1])-1] = splitData[9];
                    }
                    public static void addAnimal(String[] animalData, HashMap<String, String[]> allAnimalData, String[][] names,HashMap<String, Boolean> usedNames, String[][] zoo){
                        String[] currentAnimalData = new String[9];
                        currentAnimalData[0] = animalData[0].split(" ")[0];
                        currentAnimalData[1] = genBirthDay(currentAnimalData[0], animalData[1]);
                        currentAnimalData[2] = animalData[2].replace(" color", "");
                        currentAnimalData[3] = animalData[3].split(" ")[0];
                        currentAnimalData[4] = animalData[4].replace("from ", "");
                        currentAnimalData[5] = animalData[5];
                        currentAnimalData[6] = animalData[0].split(" ")[4].substring(0, 1).toUpperCase() + animalData[0].split(" ")[4].substring(1, 2);
                        currentAnimalData[7] = genName(usedNames, names, currentAnimalData[6]);
                        currentAnimalData[7] = genAnimalName(usedNames, names, currentAnimalData[6]);
                        if (animalData[0].contains("female")) {
                            currentAnimalData[8] = "female";
                        } else {
                            currentAnimalData[8] = "male";
                        }
                        int speciesId;
                        switch (currentAnimalData[6]){
                            case "Hy":
                                speciesId = 0;
                                break;
                            case "Li":
                                speciesId = 1;
                                break;
                            case "Be":
                                speciesId = 2;
                                break;
                            case "Ti":
                                speciesId = 3;
                                break;
                            default:
                                System.out.println("This animal is not compatible with out zoo.");
                                return;
                        }
                        for (int i = 0; i < 4; i++) {
                            if (zoo[speciesId][i] == null) {
                                zoo[speciesId][i] = genId(allAnimalData, currentAnimalData[6], currentAnimalData);
                                zoo[speciesId][i] = genUanimalID(allAnimalData, currentAnimalData[6], currentAnimalData);
                                System.out.println(currentAnimalData[7] + " was successfully added to the zoo!");
                                return;
                            }
                        }
                        System.out.println("There was no space for the animal.");
                    }


                    public static String genId(HashMap<String, String[]> animals, String species, String[] data){
                        public static String genUanimalID(HashMap<String, String[]> animals, String species, String[] data){
                            String id = "";
                            for (int i = 0; i < 4; i++){
                                if (!animals.containsKey((species + 0) + (i+1))){
                                    id = (species + 0) + (i+1);
                                    break;
                                }
                            }
                            animals.put(id, data);
                            return id;
                        }
                        public static String genBirthDay(String age, String unparsedSeason){
                            String birthday = "";
                            if (unparsedSeason.contains("spring")){
                                birthday = birthday.concat("Mar 21, ");
                            } else if (unparsedSeason.contains("summer")){
                                birthday = birthday.concat("Jun 21, ");
                            } else if (unparsedSeason.contains("fall")){
                                birthday = birthday.concat("Sept 21, ");
                            } else if (unparsedSeason.contains("winter")){
                                birthday = birthday.concat("Dec 21, ");
                            } else {
                                birthday = birthday.concat("Jan 1, ");
                            }
                            birthday = birthday.concat("" + (2022 - Integer.parseInt(age)));
                            return birthday;
                        }

                        public static String genName(HashMap<String, Boolean> used, String[][] names, String species){
                            public static String genAnimalName(HashMap<String, Boolean> used, String[][] names, String species){
                                int speciesId;
                                Random rand = new Random();
                                switch (species){
                                    case "Hy":
                                        speciesId = 0;
                                        break;
                                    case "Li":
                                        speciesId = 1;
                                        break;
                                    case "Be":
                                        speciesId = 2;
                                        break;
                                    case "Ti":
                                        speciesId = 3;
                                        break;
                                    default:
                                        speciesId = 0;
                                }
                                String name = "";
                                do {
                                    name = names[speciesId][rand.nextInt(names[speciesId].length)];
                                } while (used.containsKey(name));
                                used.put(name, true);
                                return name;
                            }
                        }