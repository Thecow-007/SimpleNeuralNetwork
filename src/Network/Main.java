package Network;

public class Main {

    public static void main(String[] args){
//        Start the timer
        double startTime = System.currentTimeMillis();

        Network network = new Network();

        double prediction = network.predict(100, 50);

//        End the timer
        double endTime = System.currentTimeMillis();


        System.out.println("Prediction:" + prediction);
        System.out.println("Time Taken: " + (endTime - startTime));
    }
}
