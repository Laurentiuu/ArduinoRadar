import com.fazecast.jSerialComm.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class SerialConnection {

    private int angle;
    private int distance;
    private int dir;
    private SerialPort serialPort;

    public void startConnection() {
        Scanner input = new Scanner(System.in);

        SerialPort ports[] = SerialPort.getCommPorts();
        int i = 1;
        //User port selection
        System.out.println("Porturi COM disponibile de pe PC");
        for (SerialPort port : ports) {
            System.out.println(i++ + ": " + port.getSystemPortName());
        }
        System.out.println("\nPlease select COM PORT: 'COM#'");
        serialPort = SerialPort.getCommPort(input.nextLine());

        //deschid conexiunea seriala
        serialPort.openPort();
        if (serialPort.isOpen()) {
            System.out.println("\nPort initializat!\nSe citesc date");
        } else {
            System.out.println("Conexiune esuata");
            return;
        }
    }

    public boolean isOpen() {
        if (serialPort.isOpen()) {
            //aici colectex datale de pe placuta
            serialPort.addDataListener(new SerialPortDataListener() {
                @Override
                public int getListeningEvents() {
                    return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
                }

                public void serialEvent(SerialPortEvent event) {
                    InputStream input = serialPort.getInputStream();
                    String newLine = new String();
                    int ascii = 10;
                    do {
                        try {
                            ascii = input.read();
                            newLine = newLine + (char) ascii;
                        } catch (IOException e) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                            System.out.println("Placuta deconectata");
                            return;
                        }
                    } while (ascii != 10);//citesc pana dau in Serial Monitor de \n
                    getData(newLine);
                }
            });
            return true;
        } else {
            return false;
        }
    }

    private void getData(String newLine) {
        if (newLine.charAt(1) == ',') {
            this.angle = Integer.parseInt(newLine.substring(0, 1));
            newLine = newLine.substring(2, newLine.length());
        } else if (newLine.charAt(2) == ',') {
            this.angle = Integer.parseInt(newLine.substring(0, 2));
            newLine = newLine.substring(3, newLine.length());
        } else if (newLine.charAt(3) == ',') {
            this.angle = Integer.parseInt(newLine.substring(0, 3));
            newLine = newLine.substring(4, newLine.length());
        }

        if (newLine.charAt(1) == ',') {
            this.distance = Integer.parseInt(newLine.substring(0, 1));
            newLine = newLine.substring(2, newLine.length());
        } else if (newLine.charAt(2) == ',') {
            this.distance = Integer.parseInt(newLine.substring(0, 2));
            newLine = newLine.substring(3, newLine.length());
        } else if (newLine.charAt(3) == ',') {
            this.distance = Integer.parseInt(newLine.substring(0, 3));
            newLine = newLine.substring(4, newLine.length());
        }
        if (newLine.charAt(1) == '.') {
            this.dir = Integer.parseInt(newLine.substring(0, 1));
        } else if (newLine.charAt(2) == '.') {
            this.dir = Integer.parseInt(newLine.substring(0, 2));
        } else if (newLine.charAt(3) == '.') {
            this.dir = Integer.parseInt(newLine.substring(0, 3));

        }

    }

    public int getAngle() {
        return angle;
    }

    public int getDistance() {
        return distance;
    }
    public int getDir() {
        return dir;
    }
}