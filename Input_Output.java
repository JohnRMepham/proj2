import sun.jvm.hotspot.debugger.cdbg.Sym;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input_Output {
    public static int input_dimensions;
    public static int number_of_image_vectors;
    public static int rows;
    public static int columns;

    public static String[][][] file_reader(String path){
        if(dimensions(path)) {
            String[][][] pattern_data = new String[number_of_image_vectors][][];
            pattern_data = data_collector(path);
            return pattern_data;
        }
        else {
            return null;
        }
    }

    private static String[][][] data_collector(String path){
        String [][][] data = new String[number_of_image_vectors][][];

        try{
            File file = new File(path);
            Scanner data_collector = new Scanner(file);
            data_collector.nextLine();
            data_collector.nextLine();
            for(int i = 0; i< number_of_image_vectors; i ++) {
                data_collector.nextLine(); //garbage
                String [][] entry = new String[rows][columns];

                for (int j = 0; j < rows; j++) {
                    String[] line = data_collector.nextLine().split("");
                    String[] data_line = new String[columns];

                    for (int k = 0; k < columns; k++) {

                        if (k > line.length-1)
                            data_line[k] = " ";
                        else{
                            if(line[k].equals("O") || line[k].equals("0"))
                                 data_line[k] = "0";
                            else
                                data_line[k] = " ";
                        }
                    }
                    entry[j]= data_line;
                }
                data[i] = entry;
            }
        }
        catch (FileNotFoundException e){
            System.out.println("DATA FILE NOT FOUND");
        }
        return data;
    }

    private static boolean dimensions(String path){
        try {
            File file = new File(path);
            Scanner sc = new Scanner(file);
            input_dimensions = Integer.parseInt(sc.nextLine().split(" ")[0]);
            number_of_image_vectors = Integer.parseInt(sc.nextLine().split(" ")[0]);

            int row_counter = 0 ;
            while(sc.hasNextLine()) {

                int temp = sc.nextLine().split("").length;
                if (temp > columns)
                    columns = temp;
                if (temp == 1)
                    row_counter = 0;
                else
                    row_counter++;
                if (row_counter > rows)
                    rows = row_counter;
            }
            if ((rows * columns)!=input_dimensions) {
                System.out.println("CHECK DATA FILE: INPUT DIMENSION MISMATCH");
                return false;
            }
            try {
                sc.nextLine();
            }
            catch (Exception e){
                sc.close();
            }
        }

        catch (FileNotFoundException ex){
            System.out.println("DATA FILE NOT FOUND");
            return false;
        }
        return true;
    }
}
