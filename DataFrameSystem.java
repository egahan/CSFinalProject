import java.util.Scanner;
import java.util.ArrayList;

public class DataFrameSystem {
    public static ArrayList<DataFrame> dataFrameList = new ArrayList<DataFrame>();
    public static ArrayList<String> dataFrameListNames = new ArrayList<>(0);
    public static int activeDataFrame;

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("Please choose from the following commands (enter the number for the corresponding action):");
        System.out.println("You can type 'start' at any time to go back to this menu");
        System.out.println("1) Create a new data frame");
        System.out.println("2) Check which data frame is active");
        System.out.println("3) Choose the active data frame");
        System.out.println("4) Delete a data frame");
        System.out.println("5) Import a file into the active data frame");
        System.out.println("6) Find the average of a column in the active data frame");
        System.out.println("7) Find the minimum value of a column in the active data frame");
        System.out.println("8) Find the maximum value of a column in the active data frame");
        System.out.println("9) Add a row onto the active data frame");
        System.out.println("10) Create a frequency table with 5 intervals using the active data frame");
        System.out.println("11) Create a new data frame from the active data frame by selecting rows where an operator value is true for the data in a column");
        System.out.println("12) Exit the program");
        String answer = input.nextLine();
        if (answer.equalsIgnoreCase("1")) {
            createDataFrame();
        } else if (answer.equalsIgnoreCase("2")) {
            checkActiveDataFrame();
        } else if (answer.equalsIgnoreCase("3")) {
            chooseDataFrame();
        } else if (answer.equalsIgnoreCase("4")) {
            deleteADataFrame();
        } else if (answer.equalsIgnoreCase("5")) {
            importAFile();
        } else if (answer.equalsIgnoreCase("6")) {
            findAverage();
        } else if (answer.equalsIgnoreCase("7")) {
            findMinimum();
        } else if (answer.equalsIgnoreCase("8")) {
            findMaximum();
        } else if (answer.equalsIgnoreCase("9")) {
            addRow();
        } else if (answer.equalsIgnoreCase("10")) {
            start();
        } else if (answer.equalsIgnoreCase("11")) {
            start();
        } else if (answer.equalsIgnoreCase("12")) {
            System.out.println("Goodbye");
            exitProgram();
        } else if (answer.equalsIgnoreCase("dev")) {
            dev();
        } else {
            System.out.println("Invalid input. Try selecting from one of the valid numbers please.");
            start();
        }
    }

    private static void deleteADataFrame() {
        if (dataFrameList.size() == 0) {
            System.out.println("No data frames to delete!");
            start();
        } else {
            Scanner input = new Scanner(System.in);
            String temp;
            System.out.println("Please select a data frame to delete:");
            for (int i = 0; i <= dataFrameListNames.size() - 1; i++) {
                System.out.print(dataFrameListNames.get(i) + " ");
            }
            System.out.println();
            temp = input.nextLine();
            if (temp.equalsIgnoreCase("start")) {
                start();
            } else {
                for (int i = 0; i <= dataFrameListNames.size() - 1; i++) {
                    if (temp.equalsIgnoreCase(dataFrameListNames.get(i))) {
                        dataFrameList.remove(i);
                        dataFrameListNames.remove(i);
                        if (dataFrameList.size() == 0) {
                            activeDataFrame = 0;
                        }
                        System.out.println("Removed data frame "+temp+"! You now have " + dataFrameList.size() + " active data frames!");
                        start();
                    }
                }
                System.out.println("Please enter a valid input which correlates with the list given");
                chooseDataFrame();
            }

        }
    }

    private static void addRow() {
        if (dataFrameList.size() == 0) {
            System.out.println("Must have an existing and active data frame to add a row");
            start();
        } else if (dataFrameList.get(activeDataFrame).getNumberOfColumns() == 0) {
            System.out.println("No data has yet been imported to the data frame yet!");
            start();
        } else {
            Scanner input = new Scanner(System.in);
            String temp;
            System.out.println("Please enter the values that you want to add onto the data frame (separated by spaces):");
            temp = input.nextLine();
            if (temp.equalsIgnoreCase("start")) {
                start();
            } else {
                dataFrameList.get(activeDataFrame).addRow(temp);
                System.out.println("Your values have been added to the active data frame!");
                start();
            }
        }
    }

    private static void checkActiveDataFrame() {
        if (dataFrameList.size() == 0) {
            System.out.println("No data frames have been created yet!");
            start();
        } else {
            System.out.println("Your active data frame is " + dataFrameListNames.get(activeDataFrame));
            start();
        }
    }

    private static void findAverage() {
        if (dataFrameList.size() == 0) {
            System.out.println("Must have an existing and active data frame to find the average of a column");
            start();
        } else if (dataFrameList.get(activeDataFrame).getNumberOfColumns() == 0) {
            System.out.println("No data has yet been imported to the data frame yet!");
            start();
        } else {
            Scanner input = new Scanner(System.in);
            String temp;
            int column;
            System.out.println("Please enter the column number for which you want to find the average of:");
            temp = input.nextLine();
            if (temp.equalsIgnoreCase("start")) {
                start();
            } else {
                try {
                    column = Integer.parseInt(temp);
                    System.out.println("Your average for column " + column + " is " + dataFrameList.get(activeDataFrame).findAverage(column));
                    start();
                } catch (NumberFormatException e) {
                    System.out.println("Your inputted value is not considered an integer. Please enter a valid integer");
                    findAverage();
                }
            }
        }
    }

    private static void findMinimum() {
        if (dataFrameList.size() == 0) {
            System.out.println("Must have an existing and active data frame to find the minimum of a column");
            start();
        } else if (dataFrameList.get(activeDataFrame).getNumberOfColumns() == 0) {
            System.out.println("No data has yet been imported to the data frame yet!");
            start();
        } else {
            Scanner input = new Scanner(System.in);
            String temp;
            int column;
            System.out.println("Please enter the column number for which you want to find the minimum of:");
            temp = input.nextLine();
            if (temp.equalsIgnoreCase("start")) {
                start();
            } else {
                try {
                    column = Integer.parseInt(temp);
                    System.out.println("Your minimum for column " + column + " is " + dataFrameList.get(activeDataFrame).findMinimum(column));
                    start();
                } catch (NumberFormatException e) {
                    System.out.println("Your inputted value is not considered an integer. Please enter a valid integer");
                    findMinimum();
                }
            }
        }
    }

    private static void findMaximum() {
        if (dataFrameList.size() == 0) {
            System.out.println("Must have an existing and active data frame to find the maximum of a column");
            start();
        } else if (dataFrameList.get(activeDataFrame).getNumberOfColumns() == 0) {
            System.out.println("No data has yet been imported to the data frame yet!");
            start();
        } else {
            Scanner input = new Scanner(System.in);
            String temp;
            int column;
            System.out.println("Please enter the column number for which you want to find the maximum of:");
            temp = input.nextLine();
            if (temp.equalsIgnoreCase("start")) {
                start();
            } else {
                try {
                    column = Integer.parseInt(temp);
                    System.out.println("Your maximum for column " + column + " is " + dataFrameList.get(activeDataFrame).findMaximum(column));
                    start();
                } catch (NumberFormatException e) {
                    System.out.println("Your inputted value is not considered an integer. Please enter a valid integer");
                    findMaximum();
                }
            }
        }
    }

    private static void importAFile() {
        if (dataFrameList.size() == 0) {
            System.out.println("Must have an existing and active data frame to import a file");
            start();
        } else {
            Scanner input = new Scanner(System.in);
            String temp;
            System.out.println("Please enter the full name of a file to import into the active data frame (must include file type as well)");
            temp = input.nextLine();
            if (temp.equalsIgnoreCase("start")) {
                start();
            } else {
                dataFrameList.get(activeDataFrame).importNewCSVFile(temp);
                System.out.println("Successfully imported file into active data frame!");
                start();
            }
        }
    }

    private static void chooseDataFrame() {
        if (dataFrameList.size() == 0) {
            System.out.println("No data frames have been created yet!");
            start();
        } else {
            Scanner input = new Scanner(System.in);
            String temp;
            System.out.println("Data frames with these names are active:");
            for (int i = 0; i <= dataFrameListNames.size() - 1; i++) {
                System.out.print(dataFrameListNames.get(i) + " ");
            }
            System.out.println("\nPlease enter a name for the data frame you want to manipulate: ");
            temp = input.nextLine();
            if (temp.equalsIgnoreCase("start")) {
                start();
            } else {
                for (int i = 0; i <= dataFrameListNames.size() - 1; i++) {
                    if (temp.equalsIgnoreCase(dataFrameListNames.get(i))) {
                        activeDataFrame = i;
                        System.out.println("Your active data frame is now " + dataFrameListNames.get(i) + "!");
                        start();
                    }
                }
                System.out.println("Please enter a valid input which correlates with the list given");
                chooseDataFrame();
            }
        }
    }

    private static void exitProgram() {
        System.exit(0);
    }

    private static void createDataFrame() {
        Scanner input = new Scanner(System.in);
        String name;
        String temp;
        String[] columnHeadings;
        String[] dataTypes;
        System.out.println("Please enter the name of the data frame:");
        name = input.nextLine();
        if (name.equalsIgnoreCase("") || name.equalsIgnoreCase(null)) {
            System.out.println("Null values are not accepted for the data frame name. Please enter a valid name");
            createDataFrame();
        }
        dataFrameListNames.add(name);
        System.out.println("Now please enter the column headings for all of your columns in order (separated by spaces):");
        temp = input.nextLine();
        columnHeadings = temp.split(" ");
        System.out.println("Finally, please enter the data types for each of your columns in order (separated by spaces):");
        temp = input.nextLine();
        dataTypes = temp.split(" ");
        if (columnHeadings.length != dataTypes.length) {
            System.out.println("It seems as if your amount of column headings don't equal your amount of data types. This could be caused by a grammatical error. Please check that your inputs are separated by spaces and then try again.");
            dataFrameListNames.remove(name);
            start();
        } else {
            if (dataFrameList.size() >= 10) {
                System.out.println("Max number of data frames have already been generated. Try manipulating existing frames instead");
                dataFrameListNames.remove(name);
                start();
            }
            dataFrameList.add(new DataFrame(name, columnHeadings, dataTypes));
            System.out.println("Your data frame has been generated given your input!");
            start();
        }
    }

    private static void dev() {
        if (dataFrameList.size() == 0) {
            System.out.println("Must have an existing and active data frame to access developer options");
            start();
        } else {
            dataFrameList.get(activeDataFrame).test();
            start();
        }
    }
}
