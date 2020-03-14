package neuralNetwork.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivasionTest {

    @Test
    void reluTest()
    {
    }

    @Test
    void relu_derivativeTest() // dont use relu until a proper derivative have been found
    {

    }

    @Test
    void logistic()
    {
        assertEquals(0.5932, Activations.logistic(0.3775), 0.001);
    }

    @Test
    void noneTest() {
    }

    @Test
    void tanhTest()
    {
        double expected = 0.4621171572600097585023;
        //act
        double actual = Activations.tanh(0.5);
        //assert
        assertEquals(expected, actual, 0.00001);
    }

    @Test
    void tanh_derivativeTest()
    {
        //arrange
        double expected = 0.7864477329659274101497;
        //act
        double actual = Activations.tanh_derivative(0.5);
        //assert
        assertEquals(expected, actual, 0.00001);
    }

    @Test
    void sigmoidTest()
    {
        //arrange
        double expected = 0.006692850924;
        //act
        double actual = Activations.sigmoid(-5);
        //assert
        assertEquals(expected, actual, 0.00001);
    }


    @Test
    void sigmoid_derivativeTest()
    {
        //arrange
        double expected = 0.006648056671;
        //act
        double actual = Activations.sigmoid_derivative(-5);
        //assert
        assertEquals(expected, actual, 0.00001);
        //test all values between -5 an 5
    }
}