import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class DataFrame {
    private String[][] data;
    private String[] columnHeadings;
    private String[] dataTypes;
    private String dataFrameName;
    private int numberOfRows = 0;
    private static int numberOfColumns = 0;

    public DataFrame(String frameName, String[] columnHeadings, String[] dataTypes){ //parametrized constructor
        dataFrameName = frameName;
        this.columnHeadings = columnHeadings;
        this.dataTypes = dataTypes;
    }

    public void importNewCSVFile(String fileName){
        try{
            dataFrameName = fileName;
            numberOfRows = 0;
            File readFile = new File(fileName);
            Scanner FileScanner1 = new Scanner(readFile);
            String line = FileScanner1.nextLine();
            columnHeadings = line.split(",");
            numberOfColumns = columnHeadings.length;
            line = FileScanner1.nextLine();
            dataTypes = line.split(",");
            while(FileScanner1.hasNextLine()){
                numberOfRows++;
                FileScanner1.nextLine();
            }
            if(numberOfRows>25000){ //stops the method if number of rows exceeds 25000
                System.out.println("This file contains more than 25000 lines of text and therefore isn't a valid input. Please enter a file which contains less than 25000 lines");
                numberOfRows = 0; //resets all values previously assigned in method; this is to prevent lingering data from the failed importation.
                numberOfColumns = 0;
                dataFrameName = null;
                columnHeadings = null;
                dataTypes = null;
            }else{
                data = new String[numberOfRows][numberOfColumns];
                Scanner FileScanner2 = new Scanner(readFile);
                FileScanner2.nextLine();
                FileScanner2.nextLine();
                for(int i=0;i<=numberOfRows-1;i++) {
                    String readLine = FileScanner2.nextLine();
                    String[] readLineWithoutCommas = readLine.split(",");
                    for (int j = 0; j <= numberOfColumns - 1; j++) {
                        data[i][j] = readLineWithoutCommas[j];
                    }
                }
            }
        }catch(FileNotFoundException fnfe) {
            System.out.println("Message: " + fnfe.getMessage());
            System.out.println("\ntoString( ): " + fnfe + "\n");
            DataFrameSystem.start();
        }
    }

    public int getNumberOfRows(){
        return numberOfRows;
    }

    public static int getNumberOfColumns(){
        return numberOfColumns;
    }

    private void printData(){
        for(int i=0;i<=numberOfRows-1;i++){
            for(int j=0;j<=numberOfColumns-1;j++){
                if(data[i][j]==null){
                    System.out.print("null ");
                }else{
                    System.out.print(data[i][j]+" ");
                }
            }
            System.out.println("\n");
        }
    }

    public void test(){ //for debugging purposes
        if(numberOfColumns==0){
            System.out.println("No data has been imported into the data frame yet. Results shown below may be missing/inaccurate for this reason.");
        }
        printData();
        System.out.println("Data frame name: "+dataFrameName+"\nNumber of rows of data: "+numberOfRows+"\nNumber of rows total: "+(numberOfRows+2)+"\nNumber of columns: "+numberOfColumns+"\nColumn headings: ");
        for(int i = 0; i<=columnHeadings.length-1;i++){
            System.out.print(columnHeadings[i]+", ");
        }
        System.out.println("\nData types: ");
        for(int j=0;j<=dataTypes.length-1;j++){
            System.out.print(dataTypes[j]+", ");
        }
        System.out.println();
        System.out.println("Testing average on all columns:");
        for(int i=1;i<=numberOfColumns-1;i++){
            System.out.println(findAverage(i));
        }
        System.out.println("\nTesting findMinimum on all columns:");
        for(int i=1;i<=numberOfColumns-1;i++){
            System.out.println(findMinimum(i));
        }
        System.out.println("\nTesting findMaximum on all columns:");
        for(int i=1;i<=numberOfColumns-1;i++){
            System.out.println(findMaximum(i));
        }
        System.out.println();
        System.out.println("Testing adding an empty row to existing data frame:");
        String testString1 = "";
        String testString2 = "0,";
        for(int i=0;i<numberOfColumns;i++){
            testString1 += testString2;
        }
        addRow(testString1);
        System.out.println("Added row: ");
        for(int i=0;i<=numberOfColumns-1;i++){
            System.out.print(data[numberOfRows-1][i]+" ");
        }
    }

    public void addRow(String s){
        String[]temp = s.split(",");
        if((temp.length)!=numberOfColumns){
            System.out.println("\nNot enough data to fill all fields/columns. It is recommended that you try again but instead input values for all fields. Separate your fields with a single space for increased compatibility.");
        }else{
            ArrayList<String>tempList = new ArrayList<String>(0);
            numberOfRows++;
            String[][]newDataSet = new String[numberOfRows][numberOfColumns];
            for(int i=0;i<=numberOfRows-2;i++){ //copy values from old dataset onto an arraylist
                for(int j=0;j<=numberOfColumns-1;j++){
                    tempList.add(data[i][j]);
                }
            }
            for(int i=0;i<=temp.length-1;i++){
                tempList.add(temp[i]);
            }
            for(int i=0;i<=numberOfRows-1;i++){ //add values from arraylist to new dataset
                for(int j=0;j<=numberOfColumns-1;j++){
                    newDataSet[i][j] = tempList.get(0);
                    tempList.remove(0);
                }
            }
            data = newDataSet; //sets old dataset to updated values
        }
    }

    private boolean isANumber(int column){
        if(dataTypes[column-1].equalsIgnoreCase("byte")){
            return true;
        }else if(dataTypes[column-1].equalsIgnoreCase("short")){
            return true;
        }else if(dataTypes[column-1].equalsIgnoreCase("int")){
            return true;
        }else if(dataTypes[column-1].equalsIgnoreCase("long")){
            return true;
        }else if(dataTypes[column-1].equalsIgnoreCase("float")){
            return true;
        }else if(dataTypes[column-1].equalsIgnoreCase("double")){
            return true;
        }else{
            return false;
        }
    }

    public double findAverage(int column){
            if(column<=numberOfColumns-1){
                if(isANumber(column)){
                    double average;
                    double sum = 0;
                    int counter = 0;
                    for(int i=0;i<=numberOfRows-1;i++){
                        if (data[i][column-1] == null || data[i][column-1].equalsIgnoreCase(""))
                            continue;
                        sum += Double.parseDouble(data[i][column-1]);
                        counter++;
                    }
                    average = sum/counter;
                    return average;
                }else{
                    System.out.println("Column "+column+" doesn't contain a data type that can be averaged");
                    return 0;
                }
            }else{
                System.out.println("Invalid number of columns/Could not compute average for unknown reasons");
                return 0;
            }
    }

    public double findMinimum(int column){
        try {
            if (column <= numberOfColumns - 1) {
                if (isANumber(column)) {
                    double minimum = Double.parseDouble(data[0][column - 1]);
                    double temp;
                    for (int i = 0; i <= numberOfRows - 1; i++) {
                        if (data[i][column-1] == null || data[i][column-1].equalsIgnoreCase(""))
                            continue;
                        temp = Double.parseDouble(data[i][column - 1]);
                        if (temp < minimum) {
                            minimum = temp;
                        }
                    }
                    return minimum;
                } else {
                    System.out.println("Column " + column + " doesn't contain a data type in which the minimum can be found");
                    return 0;
                }
            } else {
                System.out.println("Invalid number of columns/Could not compute minimum for unknown reasons. This could be because no data was imported into the frame yet");
                return 0;
            }
        }catch(NumberFormatException e){
            System.out.println("NumberFormatException for input column. Could not compute");
            return 0;
        }
    }

    public double findMaximum(int column){
        try {
            if (column <= numberOfColumns - 1) {
                if (isANumber(column)) {
                    double maximum = Double.parseDouble(data[0][column - 1]);
                    double temp;
                    for (int i = 0; i <= numberOfRows - 1; i++) {
                        if (data[i][column-1] == null || data[i][column-1].equalsIgnoreCase(""))
                            continue;
                        temp = Double.parseDouble(data[i][column - 1]);
                        if (temp > maximum) {
                            maximum = temp;
                        }
                    }
                    return maximum;
                } else {
                    System.out.println("Column " + column + " doesn't contain a data type in which the minimum can be found");
                    return 0;
                }
            } else {
                System.out.println("Invalid number of columns/Could not compute maximum for unknown reasons. This could be because no data was imported into the frame yet");
                return 0;
            }
        }catch(NumberFormatException e){
            System.out.println("NumberFormatException for input column. Could not compute");
            return 0;
        }
    }
}
