import neuralNetwork.Layer;
import neuralNetwork.Network;
import neuralNetwork.math.Activations;

import java.util.function.Function;

public class Main
{
    public static void main(String[] args) {

        /*
        >make a network/execution class > done
        >implement training > done
        >implement testing > done
        >generate csv with weights and bias
        >test it
        >comment it
        */




        //######## X or test ##############


        double b1 = Math.random();
        double b2 = Math.random();
        // layer contructor is inputs, nodes/neurons, bias value, activation, derivative
        Layer l1 = new Layer(2, 2, b1, Activations::sigmoid, Activations::sigmoid_derivative); // hidden layer 1
        Layer l2 = new Layer(2, 2, b1, Activations::sigmoid, Activations::sigmoid_derivative); // hidden layer 2
        Layer o1 = new Layer(2, 1, b2, Activations::sigmoid, Activations::sigmoid_derivative); // output layer


        Network network = new Network(new Layer[]{l1, l2,o1});

        for (int i = 0; i <20000 ; i++)
        {

            double[][] data = generateTrainingData();
            network.train(data[0],data[1]);
        }

        for (int i = 0; i <20; i++) {
            double[][] data = generateTrainingData();
            network.test(data[0],data[1]);
        }

        System.out.println("##### Information #####\n"+ "bias 1 = " + b1+", bias 2 = "+b2);


    }

    private static double[][] generateTrainingData()
    {
        int i = (int) (Math.random()*4);

        switch (i)
        {
            case 0:
                return new double[][]{
                        {1,0}, // true
                        {1}
                };

            case 1:
                return new double[][]{
                        {0,1}, // true
                        {1}
                };
            case 2:
                return new double[][]{
                        {0,0}, // false
                        {0}
                };
            default:
                return new double[][]{

                    {1,1}, // false
                    {1}
            };

        }

    }

}
