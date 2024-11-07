import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Project implements ActionListener {
    private JFrame mainFrame;
    private JPanel imbeddedPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private JPanel dogImage = new JPanel();
    private JPanel catImage = new JPanel();
    int dogVotes = 0;
    int catVotes = 0;
    private JLabel dogVoteLabel = new JLabel("TOTAL DOG VOTES: 0");
    private JLabel catVoteLabel = new JLabel("TOTAL CAT VOTES: 0");
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
        mainFrame.setLayout(new BorderLayout());
        imbeddedPanel.setLayout(new GridLayout(2, 2));
        topPanel.setLayout(new GridLayout(0, 2));
    }

    private void showEventDemo() {
        mainFrame.add(imbeddedPanel);
        mainFrame.add(topPanel, BorderLayout.NORTH);
        topPanel.add(dogVoteLabel);
        topPanel.add(catVoteLabel);
        imbeddedPanel.add(dogImage);
        imbeddedPanel.add(catImage);
        imbeddedPanel.add(dogButton);
        imbeddedPanel.add(catButton);
        mainFrame.setVisible(true);
        dogButton.setActionCommand("DOG");
        dogButton.addActionListener(new ButtonClickListener());
        catButton.setActionCommand("CAT");
        catButton.addActionListener(new ButtonClickListener());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("DOG")){
                dogVotes+=1;
                dogVoteLabel.setText("TOTAL DOG VOTES: " + dogVotes);
                try {
                    addImageDog();
                    addImageCat();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

            }
            if (command.equals("CAT")){
                catVotes+=1;
                catVoteLabel.setText("TOTAL DOG VOTES: " + catVotes);
                try {
                    addImageDog();
                    addImageCat();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public void addImageDog() throws IOException, ParseException {
        reader.pull();
        System.out.println(reader.dogImage.image);
        try {
            URL url = new URL(reader.dogImage.image);
            BufferedImage ErrorImage = ImageIO.read(new File("errorImage.png"));
            BufferedImage inputImageBuff = ImageIO.read(url.openStream());


            ImageIcon inputImage;
            if (inputImageBuff != null) {
                inputImage = new ImageIcon(inputImageBuff.getScaledInstance(WIDTH/2, HEIGHT/2, Image.SCALE_SMOOTH));
                if (inputImage != null) {
                    image1 = new JLabel(inputImage);
                } else {
                    System.out.println("inputImage is null");
                    image1 = new JLabel(new ImageIcon(ErrorImage.getScaledInstance(WIDTH/2, HEIGHT/2, Image.SCALE_SMOOTH)));

                }
                dogImage.removeAll();
                dogImage.repaint();

                dogImage.add(image1);

            } else {
                System.out.println("the other issue");
                image1 = new JLabel(new ImageIcon(ErrorImage.getScaledInstance(WIDTH/2, HEIGHT/2, Image.SCALE_SMOOTH)));

            }

        } catch (IOException e) {
            System.out.println(e);
            System.out.println("worst case scenario");
            BufferedImage ErrorImage = ImageIO.read(new File("errorImage.png"));
            JLabel imageLabel = new JLabel(new ImageIcon(ErrorImage.getScaledInstance(WIDTH/2, HEIGHT/2, Image.SCALE_SMOOTH)));

            catImage.removeAll();
            catImage.repaint();
            catImage.add(imageLabel);
            imbeddedPanel.add(catImage);

        }
    }
        public void addImageCat() throws IOException, ParseException {
            reader.pull();
            System.out.println(reader.catImage.image);
            try {
                URL url = new URL(reader.catImage.image);
                BufferedImage ErrorImage = ImageIO.read(new File("errorImage.png"));
                BufferedImage inputImageBuff = ImageIO.read(url.openStream());


                ImageIcon inputImage;
                if (inputImageBuff != null) {
                    inputImage = new ImageIcon(inputImageBuff.getScaledInstance(WIDTH/2, HEIGHT/2, Image.SCALE_SMOOTH));
                    if (inputImage != null) {
                        image1 = new JLabel(inputImage);
                    } else {
                        System.out.println("inputImage is null");
                        image1 =new JLabel(new ImageIcon(ErrorImage.getScaledInstance(WIDTH/2, HEIGHT/2, Image.SCALE_SMOOTH)));

                    }
                    catImage.removeAll();
                    catImage.repaint();

                    catImage.add(image1);

                }
                else{
                    System.out.println("the other issue");
                    image1 =new JLabel(new ImageIcon(ErrorImage.getScaledInstance(WIDTH/2, HEIGHT/2, Image.SCALE_SMOOTH)));

                }

            } catch (IOException e) {
                System.out.println(e);
                System.out.println("worst case scenario");
                BufferedImage ErrorImage = ImageIO.read(new File("errorImage.png"));
                JLabel imageLabel = new JLabel(new ImageIcon(ErrorImage.getScaledInstance(WIDTH/2, HEIGHT/2, Image.SCALE_SMOOTH)));

                catImage.removeAll();
                catImage.repaint();
                catImage.add(imageLabel);
                imbeddedPanel.add(catImage);

            }

        }
    }


