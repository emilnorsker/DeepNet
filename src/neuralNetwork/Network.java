package neuralNetwork;

import neuralNetwork.math.Matrix;

public class Network
{
    Layer[] layers;
    double[] input;
    double[] target;

    public Network(Layer[] layers)
    {
        this.layers = layers;
    }


    /**
     * this function trains the network.
     * @param input
     * @param target
     */
    public void train(double[] input, double[] target)
    {
        this.input = input;
        this.target = target;
        forward();
        backPropagate();
    }



    public void test(double[] input, double[] target)
    {
        this.input = input;
        this.target = target;
        forward();
        backPropagate();
        System.out.print("Error : "+layers[layers.length-1].getTotalError()+"\nInput: ");
        System.out.print(Matrix.ToString(new double[][]{input, target}));
        System.out.println(Matrix.ToString(new double[][]{new double[0],layers[layers.length-1].getOutputLayer()}));

        //todo calc percentage error
    }


    /**
     * the input layer will not have weights, an there fore is not included in the layer[] array.
     */
    public void forward() {
        // First bring the inputs into the input layer

        double[] lastLayerOutput = input;

        for(int i = 0; i < layers.length; i++) // for each layer calculate the neuron output while saving the weights to cache in the individual neurons
        {

            for(int j = 0; j < layers[i].neurons.length; j++)
            {
                Neuron neuron = layers[i].neurons[j];
                neuron.getOutput(lastLayerOutput); // calculates the output of a neuron with the activation of a layer
            }

            lastLayerOutput = layers[i].getOutputLayer();
        }
    }

    public double getTotalErrorSum()
    {
        double errorSum = 0;
        int lastLayerInt = layers.length-1;

        for(int i = 0; i < layers[lastLayerInt].neurons.length; i++)
        {
            Neuron neuron = layers[lastLayerInt].neurons[i];
            neuron.target= target[i];
            errorSum+= neuron.getError();
        }

        return errorSum;
    }



    public void backPropagate()
    {
        for (int i = layers.length-1; i >=0; i--)
        {
            for (int j = 0; j <layers[i].neurons.length ; j++)
            {

                Neuron neuron = layers[i].neurons[j];

                if (i==0) // if the l-1 == input layer take the former layer from input variable.
                {
                    neuron.applyFirstLayerChange(input, layers[i+1]);
                }

                else if(i>0 && i<layers.length-1) // if the layer between output and 1st hidden layer.
                {
                    neuron.applyChange(layers[i-1], layers[i+1]);
                }

                else // if the layer is the last/ the output layer
                {
                    neuron.applyOutputChange(layers[i-1], target[j]);
                }
            }
        }

        for (int i = 0; i < layers.length; i++)
        {
            for (int j = 0; j <layers[i].neurons.length; j++)
            {
                Neuron neuron = layers[i].neurons[j];
                neuron.updateWeights();
            }
        }
    }


}
