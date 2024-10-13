package Network;

import java.util.*;

public class Neuron {

    public String signature = super.toString();

    Random random = new Random();

    private double bias = random.nextDouble(-1, 1);
    private double weight1 = random.nextDouble(-1, 1);
    private double weight2 = random.nextDouble(-1, 1);

    public double value = 0;

    public LinkedHashSet<Neuron> backConnections = new LinkedHashSet<Neuron>();
    public LinkedHashSet<Neuron> forwardConnections = new LinkedHashSet<Neuron>();

    private int propertyToChangeIndex = 0;
    private int maxPropertyValue = 2;

    public Neuron(){

    }

    public Neuron(double bias, double weight1, double weight2) {
        this.bias = bias;
        this.weight1 = weight1;
        this.weight2 = weight2;
    }

    public void addBackLink(Neuron backNeuron){
        backConnections.add(backNeuron);
        backNeuron.forwardConnections.add(this);
    }

    public void addForwardLink(Neuron forwardNeuron){
        forwardConnections.add(forwardNeuron);
        forwardNeuron.backConnections.add(this);
    }

    public double compute(double input1, double input2){
        double preSigmoid = (this.weight1 * input1) + (this.weight2 * input2) + this.bias;
        double output = Util.sigmoid(preSigmoid);
        this.value = output;
        return output;
    }

    public double compute

    public double compute(List<Neuron> neurons){
        double runningTotal = 0;
        for(int i = 1; i < neurons.size(); i++){
            runningTotal += compute(neurons.get(i - 1).value, neurons.get(i).value);
        }
        double output = runningTotal / neurons.size();
        this.value = output;
        return output;
    }

    public double backCompute(){
        ArrayList<Neuron> BCList = new ArrayList<Neuron>();
        BCList.addAll(this.backConnections);
        double runningTotal = 0;
        for(int i = 1; i < BCList.size(); i++) {
            runningTotal += compute(BCList.get(i - 1).value, BCList.get(i).value);
        }
        double output = runningTotal / BCList.size();
        this.value = output;
        return output;
    }

    public void mutate(){}


    public String toString(){
        StringBuilder backConsString = new StringBuilder();
        StringBuilder forwardConsString = new StringBuilder();

        for(Neuron neuron: backConnections){
            backConsString.append(String.format("- [%s](%s.md)", neuron.signature, neuron.signature));
            backConsString.append("\n");
        }

        for(Neuron neuron: forwardConnections){
            forwardConsString.append(String.format("- [%s](%s.md)", neuron.signature, neuron.signature));
            forwardConsString.append("\n");
        }

        String output = String.format(
                "**Bias**: %f\n" +
                "**Weight1**: %f\n" +
                "**Weght2**: %f\n" +
                "**Value**: %f\n" +
                "**Back Connections**: \n" +
                "%s" +
                "**Forward Connections**:\n" +
                "%s",
                this.bias,
                this.weight1,
                this.weight2,
                this.value,
                backConsString.toString(),
                forwardConsString.toString()
        ); //end of String.format for [output]

        return output;
    }

    private void increasePropertyIndex(){
        propertyToChangeIndex++;
        if(propertyToChangeIndex > maxPropertyValue){
            propertyToChangeIndex = 0;
        }
    }




}
