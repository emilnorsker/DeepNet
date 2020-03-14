package neuralNetwork;

import neuralNetwork.math.Activations;

import java.util.function.Function;

public class Neuron
{
    double[] weights;
    double[] cacheWeights;

    double biasWeight;
    double learningRate = 0.1;
    double net; // sum
    double output;
    double error;
    double target;

    Function<Double, Double> activation;
    Function<Double, Double> derivative;


    /**
     * constructor neuron
     *
     * @param weights
     * @param biasWeight
     */
    public Neuron(double[] weights, double biasWeight, Function<Double, Double> activation, Function<Double, Double> derivative)
    {
        this.weights = weights;
        this.biasWeight = biasWeight;
        this.cacheWeights = weights;
        this.activation = activation;
        this.derivative = derivative;

    }

    public double getSum(double[] input)
    {
        double sum = 0;

        for (int i = 0; i <weights.length ; i++) // for each weight, calculate the weigth and input and add to the nodes sum.
        {
            sum+= weights[i]*input[i];
        }
        return sum+(biasWeight*1);

    }

    public double getOutput(double[] input)
    {
        this.net = getSum(input);
        this.output = activation.apply(net);
        return output;
    }


    public double getError()
    {
        error = (target-output);
        return error;
    }


    /*
        _____________BackPropegation__________
     */

    public double calcTarget(Layer layerInFront)
    {
        target = layerInFront.getTotalError();
        return error;
    }



    public double getDelta(int i, Layer formerLayer, Layer layerInFront)
    {
        calcTarget(layerInFront);

        double result = target* derivative.apply(target);   //Activations.derivative(activation, output);  //output*(1-output) //derivative function



        result*= formerLayer.neurons[i].output;
        return result;
    }


    public void applyChange(Layer formerLayer, Layer layerInFront)
    {
        this.cacheWeights = weights.clone();
        for (int i = 0; i < cacheWeights.length; i++)
        {
            cacheWeights[i] = weights[i] -learningRate*getDelta(i, formerLayer, layerInFront);
        }
    }




    /*
    the following methods are used only for the output layer
     */

    /**
     * this function backpropagates trough the last layer.
     * @param i todo delete
     * @param formerLayer the layer "l-1".
     * @param target the label to the specific neuron.
     * @return
     */
    public double getOutputDelta(int i, Layer formerLayer, double target)
    {
        this.target = target;
        double result = (output-target)*output*(1-output);
        result *= formerLayer.neurons[i].output;
        return result;
    }

    public void applyOutputChange(Layer formerLayer, double target)
    {
        this.cacheWeights = weights.clone();
        for (int i = 0; i < cacheWeights.length; i++)
        {
            cacheWeights[i] = weights[i]  -learningRate*getOutputDelta(i, formerLayer, target);
        }
    }

    /*
    the following methods are used only for the first hidden layer
     */
    public double getFirstLayerDelta(int i, double[] input, Layer layerInFront)
    {
        calcTarget(layerInFront);
        double result = (output-target)*output*(1-output);
        result *= input[i];
        return result;
    }

    public void applyFirstLayerChange(double[] input, Layer layerInFront)
    {
        this.cacheWeights = weights.clone();
        for (int i = 0; i < cacheWeights.length; i++)
        {
            cacheWeights[i] = weights[i] -learningRate*getFirstLayerDelta(i, input, layerInFront);
        }

    }


    public void updateWeights()
    {
        this.weights = cacheWeights.clone();
    }



}
