import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Project implements ActionListener {
    private JFrame mainFrame;
    private JPanel dogImage = new JPanel();
    private JPanel catImage = new JPanel();
    private JButton dogButton = new JButton("Vote dog");
    private JButton catButton = new JButton("Vote cat");
    JLabel image1 = new JLabel();
    ReadJson reader = new ReadJson();


    private int WIDTH = 800;
    private int HEIGHT = 700;


    public Project() throws IOException {
        prepareGUI();
    }

    public static void main(String[] args) throws IOException, ParseException {
        Project swingControlDemo = new Project();
        swingControlDemo.addImageCat();
        swingControlDemo.addImageDog();
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

    public void addImageDog() throws IOException, ParseException {
        reader.pull();
        System.out.println(reader.dogImage.image);
        try {
//
//           BufferedImage catErrorImage = ImageIO.read(new File("errorImage.png"));
//           ImageIcon catImageIcon= new ImageIcon(catErrorImage.getScaledInstance(400, 350, Image.SCALE_SMOOTH));
//           JLabel catImageLabel = new JLabel(catImageIcon);
//           catImage.add(catImageLabel);
//
//        }
//        catch (Exception e) {
//            throw new RuntimeException(e);
//        }
            URL url = new URL(reader.dogImage.image);
            BufferedImage ErrorImage = ImageIO.read(new File("errorImage.png"));
            BufferedImage inputImageBuff = ImageIO.read(url.openStream());


            ImageIcon inputImage;
            if (inputImageBuff != null) {
                inputImage = new ImageIcon(inputImageBuff.getScaledInstance(400, 350, Image.SCALE_SMOOTH));
                if (inputImage != null) {
                    image1 = new JLabel(inputImage);
                } else {
                    System.out.println("inputImage is null");
                    image1 = new JLabel(new ImageIcon(ErrorImage.getScaledInstance(400, 350, Image.SCALE_SMOOTH)));

                }
                dogImage.removeAll();
                dogImage.repaint();

                dogImage.add(image1);

            } else {
                System.out.println("the other issue");
                image1 = new JLabel(new ImageIcon(ErrorImage.getScaledInstance(400, 350, Image.SCALE_SMOOTH)));

            }

        } catch (IOException e) {
            System.out.println(e);
            System.out.println("worst case scenario");
            BufferedImage ErrorImage = ImageIO.read(new File("errorImage.png"));
            JLabel imageLabel = new JLabel(new ImageIcon(ErrorImage.getScaledInstance(400, 350, Image.SCALE_SMOOTH)));

            catImage.removeAll();
            catImage.repaint();
            catImage.add(imageLabel);
            mainFrame.add(catImage);

        }
    }
        public void addImageCat() throws IOException, ParseException {
            reader.pull();
            System.out.println(reader.catImage.image);
            try {
//
//           BufferedImage catErrorImage = ImageIO.read(new File("errorImage.png"));
//           ImageIcon catImageIcon= new ImageIcon(catErrorImage.getScaledInstance(400, 350, Image.SCALE_SMOOTH));
//           JLabel catImageLabel = new JLabel(catImageIcon);
//           catImage.add(catImageLabel);
//
//        }
//        catch (Exception e) {
//            throw new RuntimeException(e);
//        }
                URL url = new URL(reader.catImage.image);
                BufferedImage ErrorImage = ImageIO.read(new File("errorImage.png"));
                BufferedImage inputImageBuff = ImageIO.read(url.openStream());


                ImageIcon inputImage;
                if (inputImageBuff != null) {
                    inputImage = new ImageIcon(inputImageBuff.getScaledInstance(400, 350, Image.SCALE_SMOOTH));
                    if (inputImage != null) {
                        image1 = new JLabel(inputImage);
                    } else {
                        System.out.println("inputImage is null");
                        image1 =new JLabel(new ImageIcon(ErrorImage.getScaledInstance(400, 350, Image.SCALE_SMOOTH)));

                    }
                    catImage.removeAll();
                    catImage.repaint();

                    catImage.add(image1);

                }
                else{
                    System.out.println("the other issue");
                    image1 =new JLabel(new ImageIcon(ErrorImage.getScaledInstance(400, 350, Image.SCALE_SMOOTH)));

                }

            } catch (IOException e) {
                System.out.println(e);
                System.out.println("worst case scenario");
                BufferedImage ErrorImage = ImageIO.read(new File("errorImage.png"));
                JLabel imageLabel = new JLabel(new ImageIcon(ErrorImage.getScaledInstance(400, 350, Image.SCALE_SMOOTH)));

                catImage.removeAll();
                catImage.repaint();
                catImage.add(imageLabel);
                mainFrame.add(catImage);

            }

        }
    }


