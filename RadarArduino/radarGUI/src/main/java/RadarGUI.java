
import javax.swing.*;
import java.awt.*;


public class RadarGUI extends JPanel {

    JPanel panel;
    JFrame frame;
    Graphics2D g2;
    Color lineColor = new Color(0, 153, 122);

    Color green=new Color(0, 255, 0);
    Color red=new Color(255, 0, 0);

    Color c1=green;
    Color c2=green;
    Color c3=green;
    Color c4=green;
    Color c5=green;
    Color c6=green;

    Integer angle = 0, distance = 0, dir = 0;

    int x = 600;
    int y = 295;

    int xP;
    int yP;

    public RadarGUI() {
        panel = new JPanel();
        setBackground(Color.black);
        createFrame();
    }

    //creez frame-ul
    private void createFrame() {
        frame = new JFrame("radar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(610, 400);
        frame.add(this);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setPaint(lineColor);
        g2.setStroke(new BasicStroke(4));
        g2.drawLine(20, 290, 570, 290);

        //linia 1
        g2.setPaint(lineColor);
        g2.setStroke(new BasicStroke(4));
        g2.drawArc(20, 10, 550, 560, 0, 180);

        g2.setPaint(c6);
        g2.drawString(">50cm", 40, 40);

        g2.setPaint(c6);
        g2.drawString(">50cm", 520, 40);

        g2.setPaint(c5);
        g2.drawString("40-50cm", 272, 35);

        //linia 2
        g2.setPaint(lineColor);
        g2.setStroke(new BasicStroke(4));
        g2.drawArc(60, 50, 470, 480, 0, 180);

        g2.setPaint(c4);
        g2.drawString("30-40cm", 272, 75);

        //linia 3
        g2.setPaint(lineColor);
        g2.setStroke(new BasicStroke(4));
        g2.drawArc(100, 90, 390, 400, 0, 180);

        g2.setPaint(c3);
        g2.drawString("20-30cm", 272, 115);

        //linia 4
        g2.setPaint(lineColor);
        g2.setStroke(new BasicStroke(4));
        g2.drawArc(140, 130, 310, 320, 0, 180);

        g2.setPaint(c2);
        g2.drawString("10-20cm", 272, 155);

        //linia 5
        g2.setPaint(lineColor);
        g2.setStroke(new BasicStroke(4));
        g2.drawArc(180, 170, 230, 240, 0, 180);

        g2.setPaint(c1);
        g2.drawString("0-10cm", 275, 210);

        //centru
        g2.setPaint(lineColor);
        g2.setStroke(new BasicStroke(100));
        g2.fillArc(240, 230, 110, 120, 0, 180);

        //g2.setPaint(objColor);
        //g2.drawString("0cm", 235, 235);

        g2.setPaint(new Color(255, 40, 20));
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g2.drawString("Angle: " + angle.toString() + " degrees", 20, 340);
        g2.setPaint(new Color(255, 40, 20));
        g2.drawString("Distance: " + distance.toString() + " cm", 320, 340);

        g2.setPaint(lineColor);
        g2.setStroke(new BasicStroke(4));
        g2.drawLine(295, 290, x, y);
    }

    public void setData(Integer angle, Integer distance, Integer dir) {
        this.angle = angle;
        this.distance = distance;
        this.dir = dir;
        repaint(angle);
        repaint(distance);
        startScan();
    }

    private void startScan() {
        xP=this.x-20;
        yP=this.y;
        if (dir == 1) {
            if (this.angle == 0) {
                this.x = 600;
                this.y = 295;
            }
            if (this.angle == 180) {
                this.x = 0;
                this.y = 295;
            }
            if (x == 600 && y > 0) {
                y -= 5;
                drawObject();
            } else if (x > 0 && y == 0) {
                x -= 6;
                drawObject();
            } else if (x == 0 && y == 0 || y < 295) {
                y += 6;
                drawObject();
            }
        } else if (dir == 0) {
            if (this.angle == 180) {
                this.x = 0;
                this.y = 295;
            }
            if (this.angle == 0) {
                this.x = 600;
                this.y = 295;
            }
            if (x == 0 && y > 0) {
                y -= 5;
                drawObject();
            } else if (y == 0 && x == 0 || x < 600) {
                x += 6;
                drawObject();
            } else if (x == 600 && y == 0 || y < 295) {
                y += 6;
                drawObject();
            }
        }
    }

    private void drawObject() {
        if (this.distance > 0 && this.distance < 10) {
            this.c1=red;
            this.c2=green;
            this.c3=green;
            this.c4=green;
            this.c5=green;
            this.c6=green;
        }else if (this.distance > 10 && this.distance < 20) {
            this.c1=green;
            this.c2=red;
            this.c3=green;
            this.c4=green;
            this.c5=green;
            this.c6=green;
        }else if (this.distance > 20 && this.distance < 30) {
            this.c1=green;
            this.c2=green;
            this.c3=red;
            this.c4=green;
            this.c5=green;
            this.c6=green;
        }else if (this.distance > 30 && this.distance < 40) {
            this.c1=green;
            this.c2=green;
            this.c3=green;
            this.c4=red;
            this.c5=green;
            this.c6=green;
        }else if (this.distance > 40 && this.distance < 50) {
            this.c1=green;
            this.c2=green;
            this.c3=green;
            this.c4=green;
            this.c5=red;
            this.c6=green;
        }else if (this.distance > 50) {
            this.c1=green;
            this.c2=green;
            this.c3=green;
            this.c4=green;
            this.c5=green;
            this.c6=red;
        }
    }
}