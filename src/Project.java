import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Project implements ActionListener {
    private JFrame mainFrame;
    private JPanel dogImage = new JPanel();
    private JPanel catImage = new JPanel();
    private JButton dogButton = new JButton("Vote dog");
    private JButton catButton = new JButton("Vote cat");
    ReadJson reader = new ReadJson();


    private int WIDTH = 800;
    private int HEIGHT = 700;


    public Project() {
        prepareGUI();
    }

    public static void main(String[] args){
        Project swingControlDemo = new Project();
        swingControlDemo.addImage();
        swingControlDemo.showEventDemo();
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
        try {
            reader.pull();
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            try {
                reader.pull();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void addImage() {
        try {

           BufferedImage catErrorImage = ImageIO.read(new File("errorImage.png"));
           ImageIcon catImageIcon= new ImageIcon(catErrorImage.getScaledInstance(400, 350, Image.SCALE_SMOOTH));
           JLabel catImageLabel = new JLabel(catImageIcon);
           catImage.add(catImageLabel);

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
//        URL url = new URL(reader.catImage.image);
//        BufferedImage ErrorImage = ImageIO.read(new File("errorImage.png"));
//        BufferedImage inputImageBuff = ImageIO.read(url.openStream());
//
//
//        ImageIcon inputImage;
//        if (inputImageBuff != null) {
//            inputImage = new ImageIcon(inputImageBuff.getScaledInstance(800, 700, Image.SCALE_SMOOTH));
//            if (inputImage != null) {
//                image = new JLabel(inputImage);
//            } else {
//                image =new JLabel(new ImageIcon(ErrorImage.getScaledInstance(800, 589, Image.SCALE_SMOOTH)));
//
//            }
//            catImage.removeAll();
//            catImage.repaint();
//
//            catImage.add(image);
//
//        }
//        else{
//            image =new JLabel(new ImageIcon(ErrorImage.getScaledInstance(800, 589, Image.SCALE_SMOOTH)));
//
//        }
//
//    } catch (IOException e) {
//        System.out.println(e);
//        System.out.println("sadness");
//        BufferedImage ErrorImage = ImageIO.read(new File("errorImage.png"));
//        JLabel imageLabel = new JLabel(new ImageIcon(ErrorImage.getScaledInstance(800, 589, Image.SCALE_SMOOTH)));
//
//        catImage.removeAll();
//        catImage.repaint();
//        catImage.add(imageLabel);
//        mainFrame.add(catImage);
//
//    }

        }
    }


