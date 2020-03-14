package neuralNetwork.math;


/**
 * @author Emil Norsker
 * The matrix class will handle all matrix operations:
 * Subtracting -> tested
 * Adding
 * Multiplying -> tested
 * *maybe derivative*
 * all numbers are made to doubles
 *
 */
public class Matrix
{


    /**
     *subtracts matrix 2 from matrix 1
     *
     * @param matrix1
     * @param matrix2
     * @return double[][]
     */
    public static  double[][] subtract(double[][] matrix1, double[][] matrix2)
    {
        //error checking
        if (matrix1.length!= matrix2.length)
        {
            System.out.println("error: Matrix length not equal; matrix 1 length = " + matrix1.length +" matrix 2 length = "+ matrix2.length);
            System.exit(1);
        }


        double result[][] = new double[matrix1.length][matrix1[0].length];

        for (int i = 0; i < matrix1.length; i++)
        {
            for (int j = 0; j < matrix1[i].length; j++)
            {
                result[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }

        return result;
    }






    /**
     * Randomly fills the matrix with values(-50 -> 50).
     *
     *
     * @param matrix
     * @return double[][]
     */
    public static double[][] fill_matrix( double[][] matrix)
    {
        double[][] result = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                result[i][j] = (Math.random()*100)-50;
            }
        }

        return  result;
    }


    /**
     * Multiplie a matrix
     * @param matrix1
     * @param matrix2
     * @return
     */
    @Deprecated
    public static double[][] multiply(double[][] matrix1, double[][] matrix2)
    {
        // error checking
        if (matrix1.length!= matrix2[0].length)
        {
            System.out.println("error: matrix1 column length not equal to matrix2 row; matrix 1 length = " + matrix1.length +" matrix 2 length = "+ matrix2[0].length);
            System.exit(1);
        }

        double[][] result = new double[matrix1.length][matrix2[0].length];

        for (int i = 0; i < result.length; i++)
        {
            for (int j = 0; j < result[i].length; j++)
            {
                result[i][j] =  calculateCellProduct(matrix1, matrix2, i, j);
            }
        }

        return result;
    }

    /**
     * calculates the value of a specific cell
     *
     *
     * @param firstMatrix
     * @param secondMatrix
     * @param i
     * @param j
     * @return
     */
    private static double calculateCellProduct(double[][] firstMatrix, double[][] secondMatrix, int i, int j) {
        double value = 0;
        for (int k = 0; k < secondMatrix.length; k++) {
            value += firstMatrix[i][k] * secondMatrix[k][j];
        }
        return value;
    }


    /**
     * converts a matrix to a printable string
     * @param matrix
     * @return
     */
    public static String ToString(double[][] matrix)
    {
        String s = "";
        for ( double[] row: matrix)
        {
            for ( double column: row)
            {
                s+=(column+", ");
            }
            s+="\n";
        }
        return s;
    }



}
