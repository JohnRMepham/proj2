public class Matrix {

    public static double[][] multiply(double[][] vector_transposed, double[] vector) {
        int v1_length = vector_transposed.length;
        int v2_length = vector.length;
        if (v1_length != v2_length) throw new RuntimeException("MATRIX DIMENSION MISMATCH");
        double[][] weights = new double[v1_length][v2_length];

        for(int i = 0; i < v1_length; i++)
                for (int j = 0; j < v2_length; j++)
                    weights[i][j] += vector_transposed[i][0] * vector[j];
        return weights;
    }

    public static double[][] transpose(double[] matrix) {
        int m = matrix.length;
        double[][] transpose = new double[m][1];
        for (int i = 0; i < m; i++)
            transpose[i][0] = matrix[i];
        return transpose;
    }

    public static double[][] add(double[][] matrix1, double[][] matrix2) {
        int rows = matrix1.length;
        int columns = matrix1[0].length;
        double[][] sum = new double[rows][columns];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                sum[i][j] = matrix1[i][j] + matrix2[i][j];

        return sum;
    }

    public static double[][] zero_diagonal(double[][] matrix){
        int rows = matrix.length;
        int columns  = matrix[0].length;

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++) {
                if (i == j)
                    matrix[i][j] = 0;
            }
        }
        return matrix;
    }


    public static void print(double[][] a) {
        int rows = a.length;
        int columns = a[0].length;

        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < columns; i++)
                System.out.print(String.valueOf(a[j][i]) + "\t\t");
            System.out.println();
        }
    }

    public static void print(String[][] a) {
        int rows = a.length;
        int columns = a[0].length;
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < columns; i++)
                System.out.print(a[j][i] +  "\t\t");
            System.out.println();
        }
    }

    public static void print(String[] a) {
        int columns = a.length;
        for (int i = 0; i < columns; i++)
            System.out.print(a[i] +  "\t\t");
    }

    public static void print(double[] a) {
        int columns = a.length;
        for (int i = 0; i < columns; i++)
            System.out.print(String.valueOf(a[i]) + "\t\t");
        System.out.println();
    }

    public static void print(int[] a) {
        int columns = a.length;
        for (int i = 0; i < columns; i++)
            System.out.print(String.valueOf(a[i]) + "\t\t");
        System.out.println();
    }

    public static double[][] map_to_binary(String[][] a) {
        int columns = a.length;
        int rows = a[0].length;
        double[][] mapped = new double[rows][columns];

        for (int j = 0; j < rows; j++) {

            for (int i = 0; i < columns; i++){

                if(a[j][i] == " ")
                    mapped[j][i] = 0;

                else if (a[j][i] == "0")
                    mapped[j][i] = 1;

                else
                    mapped[j][i] =0;
            }
        }
        return mapped;
    }

    public static double[][] map_to_bipolar(String[][] a) {
        int columns = a.length;
        int rows = a[0].length;
        double[][] mapped = new double[rows][columns];

        for (int j = 0; j < rows; j++) {

            for (int i = 0; i < columns; i++){
                if(a[j][i] == " ")
                    mapped[j][i] = -1;

                else if (a[j][i] == "0")
                    mapped[j][i] = 1;

                else
                    mapped[j][i] =0;
            }
        }
        return mapped;
    }

    public static String[][] map_to_zero_and_spaces(double[][] a) {
        int rows = a.length;
        int columns = a[0].length;
        String[][] mapped = new String[rows][columns];

        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < columns; i++){

                if(a[j][i] == -1)
                    mapped[j][i] = " ";
                else if (a[j][i] == 1)
                    mapped[j][i] = "0";
                else
                    mapped[j][i] = " ";
            }
        }
        return mapped;
    }

    public static double[][][] map_input_patterns(String[][][] input) {
        double[][][] input_patterns = new double[input.length][][];
        for(int i = 0; i < input.length; i ++)
        {
            input_patterns[i] = map_to_bipolar(input[i]);
        }
        return input_patterns;
    }
}
