
import java.util.ArrayList;
import java.util.Random;

public class Model {
    private int input_dimension;
    private double[][] weights;
    private Pattern[] stored_patterns;

    public Model(Pattern[] s_p)
    {
        this.input_dimension = s_p[0].get_input_dimensions();
        this.stored_patterns = s_p;
        this.weights = compute_weights(stored_patterns);

    }

    private double[][] compute_weights(Pattern[] stored_patterns)
    {
        double[][] w = new double[stored_patterns[0].get_input_dimensions()][stored_patterns[0].get_input_dimensions()];

        for (int i=0; i< stored_patterns.length;i++)
            w = Matrix.add(w, Matrix.multiply(Matrix.transpose(stored_patterns[i].get_input_array()),
                           stored_patterns[i].get_input_array()));

        w = Matrix.zero_diagonal(w);
//        Matrix.print(w);
        return w;
    }

    public double activation_function(double neuron_in)
    {
        if (neuron_in>0)
            return 1;
        else if (neuron_in<0)
            return -1;
        else
            return 0;
    }
    public Pattern run_pattern(Pattern input)
    {
        double[] model_input;
        int [] order = neuron_order();
        boolean change_in_activation_flag = true;
        int max_iterations = 25;
        int iteration = 0;
//        change_in_activation_flag && iteration < max_iterations
//
        while(change_in_activation_flag && iteration < max_iterations)
        {
            change_in_activation_flag = false;
            iteration++;

            for (int i = 0; i < order.length; i++)
            {
                int neuron = order[i];
                model_input = input.get_input_array();
                double sum = model_input[neuron];

                for (int j = 0; j < input_dimension; j++)
                    sum += (weights[neuron][j] * model_input[j]);

                double activation = activation_function(sum);

                if(!(input.check_input_index(neuron, activation)))
                {
                    input.update_input_array(neuron, activation);
                    change_in_activation_flag = true;
                }

            }
        }
        return input;
    }

    private int [] neuron_order()
    {
     int [] order = new int[input_dimension];

     ArrayList<Integer> options = new ArrayList<Integer>(input_dimension);
     for(int i = 0; i < input_dimension; i++) {
         options.add(i);
     }

                Random rand = new Random();
                int index = 0;
                while(options.size() > 0) {
                    order[index] = options.remove(rand.nextInt(options.size()));
                    index++;
                }
       return order;
    }

    public double[][] getWeights() {
        return weights;
    }
}
