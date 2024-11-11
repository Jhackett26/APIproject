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
            if (command.equals("DOG")) {
                dogVotes += 1;
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
            if (command.equals("CAT")) {
                catVotes += 1;
                catVoteLabel.setText("TOTAL CAT VOTES: " + catVotes);
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

            String imageUrl = reader.dogImage.image;
            URL url = new URL(imageUrl);
            ImageIcon inputImageIcon = new ImageIcon(url);

            if (!imageUrl.endsWith(".gif")) {
                Image image = inputImageIcon.getImage();
                Image scaledImage = image.getScaledInstance(WIDTH / 2, HEIGHT / 2, Image.SCALE_SMOOTH);
                inputImageIcon = new ImageIcon(scaledImage);  // Update the ImageIcon with the scaled image
            }

            if (inputImageIcon.getImage() != null) {
                image1 = new JLabel(inputImageIcon);
            } else {
                System.out.println("inputImageIcon is null");
                BufferedImage errorImage = ImageIO.read(new File("errorImage.png"));
                image1 = new JLabel(new ImageIcon(errorImage.getScaledInstance(WIDTH / 2, HEIGHT / 2, Image.SCALE_SMOOTH)));
            }

            dogImage.removeAll();
            dogImage.add(image1);
            dogImage.revalidate();
            dogImage.repaint();

        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Error loading image");
            BufferedImage errorImage = ImageIO.read(new File("errorImage.png"));
            image1 = new JLabel(new ImageIcon(errorImage.getScaledInstance(WIDTH / 2, HEIGHT / 2, Image.SCALE_SMOOTH)));

            dogImage.removeAll();
            dogImage.add(image1);
            dogImage.revalidate();
            dogImage.repaint();
        }
    }

    public void addImageCat() throws IOException, ParseException {
        reader.pull();
        System.out.println(reader.catImage.image);
        try {
            String imageUrl = reader.catImage.image;
            URL url = new URL(imageUrl);
            ImageIcon inputImageIcon = new ImageIcon(url);

            if (!imageUrl.endsWith(".gif")) {
                Image image = inputImageIcon.getImage();
                Image scaledImage = image.getScaledInstance(WIDTH / 2, HEIGHT / 2, Image.SCALE_SMOOTH);
                inputImageIcon = new ImageIcon(scaledImage);  // Update the ImageIcon with the scaled image
            }

            if (inputImageIcon.getImage() != null) {
                image1 = new JLabel(inputImageIcon);
            } else {
                System.out.println("inputImageIcon is null");
                BufferedImage errorImage = ImageIO.read(new File("errorImage.png"));
                image1 = new JLabel(new ImageIcon(errorImage.getScaledInstance(WIDTH / 2, HEIGHT / 2, Image.SCALE_SMOOTH)));
            }

            catImage.removeAll();
            catImage.add(image1);
            catImage.revalidate();
            catImage.repaint();

        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Error loading image");
            BufferedImage errorImage = ImageIO.read(new File("errorImage.png"));
            image1 = new JLabel(new ImageIcon(errorImage.getScaledInstance(WIDTH / 2, HEIGHT / 2, Image.SCALE_SMOOTH)));

            catImage.removeAll();
            catImage.add(image1);
            catImage.revalidate();
            catImage.repaint();
        }
    }
}
