import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Project implements ActionListener {
    private JFrame mainFrame;
    private JLabel dogImage = new JLabel();
    private JLabel catImage = new JLabel();
    private JButton dogButton = new JButton("Vote dog");
    private JButton catButton = new JButton("Vote cat");


    private int WIDTH = 800;
    private int HEIGHT = 700;


    public Project() {
        prepareGUI();
    }

    public static void main(String[] args) throws IOException {
        Project swingControlDemo = new Project();
        swingControlDemo.showEventDemo();
        swingControlDemo.addImage();
    }

    private void prepareGUI() {
        mainFrame = new JFrame();
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(2, 2));
    }

    private void showEventDemo() {
        mainFrame.add(dogImage);
        mainFrame.add(catImage);
        mainFrame.add(dogButton);
        mainFrame.add(catButton);
        mainFrame.setVisible(true);
        System.out.println(ReadJson.catImage.image);
        System.out.println(ReadJson.dogImage.image);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            ReadJson reader = new ReadJson();
            try {
                reader.pull();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void addImage() throws IOException {
//        URL url = new URL(ReadJson.catImage.image);
//        BufferedImage ErrorImage = ImageIO.read(new File("Error.png"));
//        BufferedImage inputImageBuff = ImageIO.read(url.openStream());
//
//
//        ImageIcon inputImage;
//        if (inputImageBuff != null) {
//            inputImage = new ImageIcon(inputImageBuff.getScaledInstance(800, 700, Image.SCALE_SMOOTH));
//            if (inputImage != null) {
//                imageLabel = new JLabel(inputImage);
//            } else {
//                imageLabel =new JLabel(new ImageIcon(ErrorImage.getScaledInstance(800, 589, Image.SCALE_SMOOTH)));
//
//            }
//            imagePanel.removeAll();
//            imagePanel.repaint();
//
//            imagePanel.add(imageLabel);
//
//        }
//        else{
//            imageLabel =new JLabel(new ImageIcon(ErrorImage.getScaledInstance(800, 589, Image.SCALE_SMOOTH)));
//
//        }
    }
}
