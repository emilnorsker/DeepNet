package neuralNetwork;

import neuralNetwork.math.Activations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {

    @Test
    void run()
    {
        // arrange
        double[] input = {0.05, 0.10};
        double[] target = {0.01, 0.99};

        Layer h = new Layer(2,2, 0.35 ,Activations::logistic, Activations::logistic_derivative);
        Layer o = new Layer(2,2, 0.6, Activations::logistic, Activations::logistic_derivative);

        Neuron h1 = h.neurons[0];
        h1.weights = new double[]{0.15 , 0.20};

        Neuron h2 = h.neurons[1];
        h2.weights = new double[]{0.25 , 0.30};

        Neuron o1 = o.neurons[0];
        o1.weights = new double[]{0.40 , 0.45};

        Neuron o2 = o.neurons[1];
        o2.weights = new double[]{0.50 , 0.55};

        Layer[] layers = {h, o};

        Network network = new Network(layers);


        // act
        network.train(input, target);


        //assert
        assertEquals(0.3589, o1.weights[0], 0.001);
        assertEquals(0.5113, o2.weights[0], 0.001);
        assertEquals(0.1497, h1.weights[0], 0.001);
    }

}