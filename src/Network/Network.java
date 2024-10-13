package Network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Network {

    public HashSet<Neuron> network = new HashSet<Neuron>();
    public List<Neuron> initialNeurons = new ArrayList<Neuron>();
    public ArrayList<Neuron> lastNeurons = new ArrayList<Neuron>();


    /*
      [neuronExpand], [networkBreadth], and [neuronShrink] control the initial size of the network.
      The way this works is that neuron expand is the amount of times that the neurons per row increases by 1.
      The neurons start at 3 per layer and more will be added per layer for each of the [neuronExpand]
      E.x., if the [neuronExpand] is 2, then the first layer will be 3 neurons (as is the default),
      the second layer will have 4 neurons and the third layer 5 neurons. Now the subsequent layers will not have more
      than 5 neurons, as the network has finished expanding.

      Now comes the [networkBreadth]. Breadth determines for how many
      layers the network will remain at that size, so if the breadth is 2 in our previous example then layers 5 and 6 will
      have 5 neurons a -piece because the breadth value is 2.

      Finally, the [neuronShrink] value. This is the inverse of [neuronExpand]
      so instead of the network expanding by 1 row each layer, it shrinks by 1 row each layer as many times as the value
      of [neuronShrink]. E.x., going back to the previous example, if the [neuronShrink] value was 3 then
      layer 7 will have 4 neurons, layer 8 will have 3 neurons, and layer 9 will have 2 neurons. And that is where
      the network will end.

     */
    public int neuronExpand;
    public int networkBreadth;
    public int neuronShrink;
    public int connectionBreadth;
    public int currentMaxRows;

    public Network(){
        this.neuronExpand = 1;
        this.networkBreadth = 1;
        this.neuronShrink = 2;
        this.connectionBreadth = 1;
        this.currentMaxRows = 3;
    }



    public Network(Network oldNetwork){
        this.network = oldNetwork.network.clone();
    }


    public Network(int neuronExpand, int networkBreadth, int neuronShrink, int connectionBreadth, int currentMaxRows) {
        this.neuronExpand = neuronExpand;
        this.networkBreadth = networkBreadth;
        this.neuronShrink = neuronShrink;
        this.connectionBreadth = connectionBreadth;
        this.currentMaxRows = currentMaxRows;
        this.assemble();
    }

    public void assemble(){
        List<Neuron> previousLayer = new ArrayList<Neuron>();
        List<Neuron> currentLayer = new ArrayList<Neuron>();
//        Network Expansion
        for(int j = 0; j < neuronExpand; j++){
            for(int i = 0; i  < currentMaxRows; i++){
                Neuron neuron = new Neuron();
                //if we are in the first layer, add these neurons to the initial neurons list as well so we can use them
                //to start the compute series.
                if(j == 0){
                    initialNeurons.add(neuron);
                }
                network.add(neuron);
                currentLayer.add(neuron);
                for(int k = 0; k < previousLayer.size(); k++){
                    //This check allows us to determine the interconnectivity of the network
                    if(i >= k - connectionBreadth && i <= k + connectionBreadth){
                        neuron.addBackLink(previousLayer.get(k));
                    }
                }
            }
//            Increment this as we are part of expansion stage
            currentMaxRows++;
            previousLayer.clear();
            previousLayer.addAll(currentLayer);
            currentLayer.clear();
        }

//        Network Breadth
        for(int j = 0; j < networkBreadth; j++){
            for(int i = 0; i  < currentMaxRows; i++){
                Neuron neuron = new Neuron();
                network.add(neuron);
                currentLayer.add(neuron);
                for(int k = 0; k < previousLayer.size(); k++){
                    //This check allows us to determine the interconnectivity of the network
                    if(i >= k - connectionBreadth && i <= k + connectionBreadth){
                        neuron.addBackLink(previousLayer.get(k));
                    }
                }
            }
            previousLayer.clear();
            previousLayer.addAll(currentLayer);
            currentLayer.clear();
        }

//        Network Shrinking
        for(int j = 0; j <= neuronShrink; j++){
            for(int i = 0; i  < currentMaxRows; i++){
                Neuron neuron = new Neuron();
                if(j == neuronShrink){
                    lastNeurons.add(neuron);
                }
                network.add(neuron);
                currentLayer.add(neuron);
                for(int k = 0; k < previousLayer.size(); k++){
                    //This check allows us to determine the interconnectivity of the network
                    if(i >= k - connectionBreadth && i <= k + connectionBreadth){
                        neuron.addBackLink(previousLayer.get(k));
                    }
                }
            }
//            Decrement the current max rows as we are in the shrinking stage
            currentMaxRows--;
            previousLayer.clear();
            previousLayer.addAll(currentLayer);
            currentLayer.clear();
        }
    }


    public double predict(double input1, double input2){
        List<Neuron> previousLayer = new ArrayList<Neuron>();
        List<Neuron> currentLayer = new ArrayList<Neuron>();
        ArrayList<Neuron> runningLastLayer = new ArrayList<Neuron>();

        //First loop through initial neurons and compute initial values (input 1 and 2)
        for(Neuron initialNeuron: initialNeurons){
            initialNeuron.compute(input1, input2);
        }

        previousLayer.addAll(initialNeurons);

        while(!runningLastLayer.equals(lastNeurons)){
            for(Neuron previousNeuron: previousLayer){
                for(Neuron neuron: previousNeuron.forwardConnections){
                    if(neuron.value != 0){
                        break;
                    }
                    neuron.backCompute();
                    if(!currentLayer.contains(neuron)){
                        currentLayer.add(neuron);
                    }
                    if(neuron.forwardConnections.isEmpty()){
                        runningLastLayer.add(neuron);
                    }
                }
            }
            previousLayer.clear();
            previousLayer.addAll(currentLayer);
            currentLayer.clear();
        }

        double runningavg = 0;

        //calculate average of last neurons
        for(Neuron neuron: lastNeurons){
            runningavg += neuron.value;
        }

        runningavg = runningavg / lastNeurons.size();


        return runningavg;


    }

    public int getCurrentMaxRows() {
        return currentMaxRows;
    }

    public void setCurrentMaxRows(int currentMaxRows) {
        this.currentMaxRows = currentMaxRows;
    }

    public int getConnectionBreadth() {
        return connectionBreadth;
    }

    public void setConnectionBreadth(int connectionBreadth) {
        this.connectionBreadth = connectionBreadth;
    }

    public int getNeuronShrink() {
        return neuronShrink;
    }

    public void setNeuronShrink(int neuronShrink) {
        this.neuronShrink = neuronShrink;
    }

    public int getNetworkBreadth() {
        return networkBreadth;
    }

    public void setNetworkBreadth(int networkBreadth) {
        this.networkBreadth = networkBreadth;
    }

    public int getNeuronExpand() {
        return neuronExpand;
    }

    public void setNeuronExpand(int neuronExpand) {
        this.neuronExpand = neuronExpand;
    }
}
