package Network;

import java.util.Random;

public class Neuron {

    Random random = new Random();

//    Bias
    private double bias = random.nextDouble(-1, 1);
    private double posBias = random.nextDouble(-1, 1);
    private double negBias = random.nextDouble(-1, 1);

//    Weight1
    private double weight1 = random.nextDouble(-1, 1);
    private double posWeight1 = random.nextDouble(-1, 1);
    private double negWeight1 = random.nextDouble(-1, 1);

//    Weight2
    private double weight2 = random.nextDouble(-1, 1);
    private double posWeight2 = random.nextDouble(-1, 1);
    private double negWeight2 = random.nextDouble(-1, 1);

    public double compute(double input1, double input2){
        double preSigmoid = (this.weight1 * input1) + (this.weight2 * input2)+ this.bias;
        double output = Util.sigmoid(preSigmoid);
        return output;
    }

}
