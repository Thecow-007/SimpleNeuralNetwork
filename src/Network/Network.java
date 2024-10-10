package Network;

import java.util.Arrays;
import java.util.List;

public class Network {

    public List<Neuron> layer1 = Arrays.asList(new Neuron(), new Neuron(), new Neuron());
    public List<Neuron> layer2 = Arrays.asList(new Neuron(), new Neuron(), new Neuron(), new Neuron());
    public List<Neuron> layer3 = Arrays.asList(new Neuron(), new Neuron(), new Neuron());

    public double predict(double input1, double input2){
        double output1, output2, output3;

        output1 = layer3.get(0).compute(
                layer2.get(0).compute(
                        layer1.get(0).compute(input1,input2),
                        layer1.get(1).compute(input1,input2)
                ),
                layer2.get(1).compute(
                        layer1.get(0).compute(input1,input2),
                        layer1.get(1).compute(input1,input2)
                )
        );

        output2 = layer3.get(1).compute(
                layer2.get(1).compute(
                        layer1.get(0).compute(input1,input2),
                        layer1.get(1).compute(input1,input2)
                ),
                layer2.get(2).compute(
                        layer1.get(1).compute(input1,input2),
                        layer1.get(2).compute(input1,input2)
                )
        );

        output3 = layer3.get(2).compute(
                layer2.get(2).compute(
                        layer1.get(1).compute(input1,input2),
                        layer1.get(2).compute(input1,input2)
                ),
                layer2.get(3).compute(
                        layer1.get(1).compute(input1,input2),
                        layer1.get(2).compute(input1,input2)
                )
        );

        double avg = (output1 + output2 + output3) / 3;
        return avg;
    }

}
