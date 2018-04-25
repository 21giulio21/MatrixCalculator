package com.giulio.matrixcalculatorperfetto;

/**
 * Created by giulio on 06/03/18.
 */

public class MatrixCalcolator {

    static int Ri = 2;
    static int C = 2;




        public static double calcoloDeterminante (double[][] matrix) {
            double temporary[][];
            double result = 0;

            if (matrix.length == 1) {
                result = matrix[0][0];
                return (result);
            }

            if (matrix.length == 2) {
                result = ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
                return (result);
            }

            for (int i = 0; i < matrix[0].length; i++) {
                temporary = new double[matrix.length - 1][matrix[0].length - 1];

                for (int j = 1; j < matrix.length; j++) {
                    for (int k = 0; k < matrix[0].length; k++) {
                        if (k < i) {
                            temporary[j - 1][k] = matrix[j][k];
                        } else if (k > i) {
                            temporary[j - 1][k - 1] = matrix[j][k];
                        }
                    }
                }

                result += matrix[0][i] * Math.pow (-1, (double) i) * calcoloDeterminante(temporary);
            }
            return (result);
        }


        public static double[][] invertMatrix (double[][] matrix) {
            double[][] auxiliaryMatrix, invertedMatrix;
            int[] index;

            auxiliaryMatrix = new double[matrix.length][matrix.length];
            invertedMatrix = new double[matrix.length][matrix.length];
            index = new int[matrix.length];

            for (int i = 0; i < matrix.length; ++i) {
                auxiliaryMatrix[i][i] = 1;
            }

            transformToUpperTriangle (matrix, index);

            for (int i = 0; i < (matrix.length - 1); ++i) {
                for (int j = (i + 1); j < matrix.length; ++j) {
                    for (int k = 0; k < matrix.length; ++k) {
                        auxiliaryMatrix[index[j]][k] -= matrix[index[j]][i] * auxiliaryMatrix[index[i]][k];
                    }
                }
            }

            for (int i = 0; i < matrix.length; ++i) {
                invertedMatrix[matrix.length - 1][i] = (auxiliaryMatrix[index[matrix.length - 1]][i] / matrix[index[matrix.length - 1]][matrix.length - 1]);

                for (int j = (matrix.length - 2); j >= 0; --j) {
                    invertedMatrix[j][i] = auxiliaryMatrix[index[j]][i];

                    for (int k = (j + 1); k < matrix.length; ++k) {
                        invertedMatrix[j][i] -= (matrix[index[j]][k] * invertedMatrix[k][i]);
                    }

                    invertedMatrix[j][i] /= matrix[index[j]][j];
                }
            }

            return (invertedMatrix);
        }

        public static void transformToUpperTriangle (double[][] matrix, int[] index) {
            double[] c;
            double c0, c1, pi0, pi1, pj;
            int itmp, k;

            c = new double[matrix.length];

            for (int i = 0; i < matrix.length; ++i) {
                index[i] = i;
            }

            for (int i = 0; i < matrix.length; ++i) {
                c1 = 0;

                for (int j = 0; j < matrix.length; ++j) {
                    c0 = Math.abs (matrix[i][j]);

                    if (c0 > c1) {
                        c1 = c0;
                    }
                }

                c[i] = c1;
            }

            k = 0;

            for (int j = 0; j < (matrix.length - 1); ++j) {
                pi1 = 0;

                for (int i = j; i < matrix.length; ++i) {
                    pi0 = Math.abs (matrix[index[i]][j]);
                    pi0 /= c[index[i]];

                    if (pi0 > pi1) {
                        pi1 = pi0;
                        k = i;
                    }
                }

                itmp = index[j];
                index[j] = index[k];
                index[k] = itmp;

                for (int i = (j + 1); i < matrix.length; ++i) {
                    pj = matrix[index[i]][j] / matrix[index[j]][j];
                    matrix[index[i]][j] = pj;

                    for (int l = (j + 1); l < matrix.length; ++l) {
                        matrix[index[i]][l] -= pj * matrix[index[j]][l];
                    }
                }
            }
        }

        public static int calcoloDelRango(double mat[][] , int dimensione )
        {
            Ri = dimensione;
            C = dimensione;
            return rankOfMatrix(mat);

        }

        // FUNZIONE INTERNA DEL RANGO
        static public int rankOfMatrix(double mat[][])
        {
            PrintMessage.stampoMessaggio("rank vale " + String.valueOf(C));
            int rank = C;

            for (int row = 0; row < rank; row++)
            {
                // Before we visit current row
                // 'row', we make sure that
                // mat[row][0],....mat[row][row-1]
                // are 0.
                // Diagonal element is not zero
               // Log.d("testt", Double.toString((mat[row][row])));
                if (mat[row][row] != 0)
                {
                    for (int col = 0; col < Ri; col++)
                    {
                        if (col != row)
                        {
                            // This makes all entries
                            // of current column
                            // as 0 except entry
                            // 'mat[row][row]'
                            double mult = mat[col][row] / mat[row][row];
                            for (int i = 0; i < rank; i++)
                                mat[col][i] -= mult * mat[row][i];
                        }
                    }
                }

                else
                {
                    boolean reduce = true;

                    for (int i = row + 1; i < Ri; i++)
                    {

                        if (mat[i][row] != 0)
                        {
                            swap(mat, row, i, rank);
                            reduce = false;
                            break ;
                        }
                    }

                    if (reduce)
                    {
                        rank--;

                        for (int i = 0; i < Ri; i ++)
                            mat[i][row] = mat[i][rank];
                    }

                    row--;
                }

            }

            return rank;
        }

        public static void swap(double mat[][], int row1, int row2, int col)
        {
            for (int i = 0; i < col; i++)
            {
                double temp = mat[row1][i];
                mat[row1][i] = mat[row2][i];
                mat[row2][i] = temp;
            }
        }

        public static double[][] transposeMatrix(double [][] m){
            double[][] temp = new double[m[0].length][m.length];
            for (int i = 0; i < m.length; i++)
                for (int j = 0; j < m[0].length; j++)
                    temp[j][i] = m[i][j];
            return temp;
        }

}
