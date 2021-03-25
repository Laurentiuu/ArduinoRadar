
public class Main {
    public static void main(String[] args) throws InterruptedException {
        SerialConnection connection = new SerialConnection();
        connection.startConnection();
        int angle=0,distance=0,dir=0;
        RadarGUI radar=new RadarGUI();

        while (connection.isOpen()) {
            if (connection.getDistance() != 0) {
                System.out.print("Unghi: " + connection.getAngle() + " ");
                angle=connection.getAngle();
                System.out.print("Distanta: " + connection.getDistance()+ " ");
                distance=connection.getDistance();
                System.out.println("Dir: " + connection.getDir());
                dir=connection.getDir();
                radar.setData(angle,distance,dir);
                Thread.sleep(50);
            }
        }
    }
}
