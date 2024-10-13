package Network;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NetworkSave {

    public static FileSave fileSave = FileSave.getInstance();

    public static void saveNetwork(Network network){
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());


        String networkPath = "C:\\Users\\bierm\\Documents\\Obsidian\\Projects\\Neural Networks\\Simple Neural Networks\\Network" + timeStamp;
        fileSave.writeFolder(networkPath);
        for(Neuron neuron: network.network){
            saveNeuron(neuron, networkPath + "\\" + neuron.signature + ".md");
        }

    }

    public static void saveNeuron(Neuron neuron, String filepath){
        fileSave.writeFile(filepath, neuron.toString());
    }



}
