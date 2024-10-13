package Network;

public class NetworkTrainer {

    public NetworkResults results = new NetworkResults();



    public NetworkResults train(Network network){

        for(Neuron neuron: network.network){

        }

        return results;
    }

    public NetworkResults getResults() {
        return results;
    }

    public void setResults(NetworkResults results) {
        this.results = results;
    }
}
