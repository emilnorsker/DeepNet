package neuralNetwork;


/*
todo:
> make comments
 */

import neuralNetwork.math.Calculation;

import java.util.function.Function;

/**
 * The layers will contain neurons.
 *
 */
public class Layer
{
    public Neuron[] neurons;
    public Function<Double, Double> activation;
    public Function<Double, Double> derivative;

    double errorSum;




    /**
     * initiates a layer with randomly filled weights.
     * not tested
     *
     * todo use Kian the boss's lib for cool math for the
     *
     * @param inputs the amount of neuron that enters the layers.
     * @param nodes the amount of neurons the layer has.
     */
    public Layer(int inputs, int nodes, double bias ,Function<Double,Double> activation, Function<Double, Double> derivative)
    {
        this.neurons = new Neuron[nodes];

        for (int i = 0; i <nodes ; i++)
        {
            double weights[] = new double[inputs];
            Calculation.fillArray(weights, 1);
            neurons[i] = new Neuron(weights, bias, activation, derivative);
        }
    }


    public double[] getOutputLayer()
    {
        double[] result = new double[neurons.length];

        for (int i = 0; i <result.length ; i++)
        {
            result[i] = neurons[i].output; // gets the output value of each neuron
        }

        return result;
    }





    public double getTotalError()
    {
        double errorSum = 0;

        for(int i = 0; i < neurons.length; i++)
        {
            Neuron neuron = neurons[i];
            errorSum+= neuron.getError();
        }

        this.errorSum = errorSum;
        return errorSum;
    }

}
