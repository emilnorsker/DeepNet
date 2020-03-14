package neuralNetwork.math;

import java.awt.geom.CubicCurve2D;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Activations
{

    static Map<Function<Double,Double>,Function<Double,Double>> derivativeMap = new HashMap<>();
    private static boolean instantiated = false;




    public static double relu(double x)
    {
        if (x>0) return 1;
        else     return 0;
    }

    public static double relu_derivative(double x)
    {
        if (x>0) return 1;
        else     return 0.1; //arbitrarily choosen, *make a softplus function maybe
    }

    public static double logistic(double x)
    {
        double result = 1/(1+ Math.pow(Math.E, -x));
        return result;
    }


    public static double logistic_derivative(double x)
    {
        double result = x*(1-x);
        return result;
    }

    public static double none(double x)
    {
        return  x;
    }

    public static double tanh(double x)
    {
        return Math.tanh(x);
    }

    public static double tanh_derivative(double x)
    {
        return 1 - Math.tanh(x) * Math.tanh(x);
    }

    public static double sigmoid(double x)
    {
        return 1 / (1 + Math.exp(-x));
    }

    public static double sigmoid_derivative(double x) // error
    {
        x = sigmoid(x);
        return x * (1 - x);
    }


    /**
     * this method gets the
     */
    public static double derivative(Function activation, double value)
    {
        if (instantiated=false)
        {
            derivativeMap.put(Activations::relu, Activations::relu_derivative);
            derivativeMap.put(Activations::logistic, Activations::logistic_derivative);
            derivativeMap.put(Activations::sigmoid, Activations::sigmoid_derivative);
            derivativeMap.put(Activations::tanh, Activations::tanh_derivative);
        }

        return derivativeMap.get(activation).apply(value);
    }


}
