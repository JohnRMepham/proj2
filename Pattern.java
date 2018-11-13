public class Pattern {
    protected double [] input_array;
    private int columns;
    private int rows;
    private int input_dimensions;

    public Pattern(double [][] ar) {
        this.columns = ar[0].length;
        this.rows = ar.length;
        this.input_dimensions = rows*columns;
        this.input_array = flatten(ar);
    }

    private double[] flatten(double[][] input) {
        double[] flattened = new double[this.input_dimensions];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++) {
                flattened[(i * columns) + j] = input[i][j];
            }
        return flattened;
    }


    public boolean check_input_index(int index, double expected_value) {
        if (input_array[index] == expected_value)
            return true;
        else
            return false;
    }

    public void update_input_array(int index, double new_value) {
        input_array[index] = new_value;
    }

    public void print_pattern(){
        int i = 0;
        while (i < input_dimensions) {
            for (int j = 0; j < columns; j++) {
                System.out.print(input_array[i] + "\t\t");
                i++;
            }
            System.out.println();
        }
    }

    public void print() {
        double [][] temp = new double[rows][columns];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++) {
                temp[i][j]= input_array[(i*columns)+j];
            }
        }
        Matrix.print(Matrix.map_to_zero_and_spaces(temp));
    }

    public double[][] get_mapped_array () {
        double [][] temp = new double[rows][columns];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++) {
                temp[i][j]= input_array[(i*columns)+j];
            }
        }
        return temp;
    }

    public int get_input_dimensions() {
        return input_dimensions;
    }

    public double[] get_input_array() {
        return input_array;
    }
}
