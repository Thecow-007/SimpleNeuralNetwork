package Network;

public class Util {
    /**
     * Sigmoid funtion compresses values to a range of (-1, 1)
     * @param input input to sigmoid function
     * @return
     */
    public static double sigmoid(double input){
        return 1 / (1 + Math.exp(-input));
    }
}
