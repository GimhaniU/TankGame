/**
 * Created by Gimhani on 10/21/2015.
 */
public class Massage_Decorder {

    public void process(String message) {
        if (message.length() > 1) {
            char firstChar= message.charAt(0);
            switch (firstChar) {
                case 'S':
                    processStartMessage(message);
                    break;
                case 'I':
                    processInitializationMessage(message);
                    break;
                case 'C':
                    placeCoinPile(message);
                    break;
                case 'L':
                    placeLifePack(message);
                    break;
                case 'G':
                    processGameUpdateMessage(message);
                    break;
            }
        }
    }

    private void processGameUpdateMessage(String message) {

    }

    private void placeLifePack(String message) {

    }

    private void placeCoinPile(String message) {

    }

    private void processInitializationMessage(String message) {

    }

    private void processStartMessage(String message) {

    }

}
