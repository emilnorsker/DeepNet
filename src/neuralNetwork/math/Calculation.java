package neuralNetwork.math;

/**
 * the calculation class contains all general useful formulas.
 */
public class Calculation
{


    /**
     * calculates the Z value for a given neuron
     *
     *
     * @return
     */
    public static double weightedSum(double[] weigths, double[] inputs, double bias)
    {
        double sum = 0;
        for (int i = 0; i <weigths.length ; i++)
        {
            sum += weigths[i]*inputs[i];
        }



        return sum;
    }

    public static void fillArray(double[] array, double range)
    {
        for (int i = 0; i < array.length; i++)
        {
            double n = Math.random();

            if(n>0.5)
                array[i] = Math.random()*range;
            else
                array[i] = Math.random()*-range;
        }
    }


    // todo logistic function



}
