import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
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
    String savedVotes = loadInt();
    private JLabel dogVoteLabel = new JLabel("TOTAL DOG VOTES: 0");
    private JLabel catVoteLabel = new JLabel("TOTAL CAT VOTES: 0");
    private JButton dogButton = new JButton("Vote dog");
    private JButton catButton = new JButton("Vote cat");
    JLabel image1 = new JLabel();
    ReadJson reader = new ReadJson();


    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double WIDTH = screenSize.getWidth();
    double HEIGHT = screenSize.getHeight();


    public Project() throws IOException {
        prepareGUI();
    }

    public static void main(String[] args) throws IOException, ParseException {
        Project swingControlDemo = new Project();
        swingControlDemo.pullVotes();
        swingControlDemo.addImageCat();
        swingControlDemo.addImageDog();
        swingControlDemo.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame();
        mainFrame.setSize((int) WIDTH, (int) HEIGHT);
        mainFrame.setLayout(new BorderLayout());
        imbeddedPanel.setLayout(new GridLayout(2, 2));
        topPanel.setLayout(new GridLayout(0, 2));
    }

    private void showEventDemo() {
        mainFrame.add(imbeddedPanel);
        mainFrame.add(topPanel, BorderLayout.NORTH);
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
    public void pullVotes(){
        if(!(savedVotes == null)) {
            String savedCatVotes = savedVotes.substring(0, savedVotes.indexOf(" "));
            String savedDogVotes = savedVotes.substring(savedVotes.indexOf(" ") + 1);
            catVotes = Integer.parseInt(savedCatVotes);
            dogVotes = Integer.parseInt(savedDogVotes);
        }
//            System.out.println(dogVotes);
//            System.out.println(catVotes);
            dogVoteLabel.setText("TOTAL DOG VOTES: " + dogVotes);
            catVoteLabel.setText("TOTAL CAT VOTES: " + catVotes);
            topPanel.add(dogVoteLabel);
            topPanel.add(catVoteLabel);
    }
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("DOG")) {
                dogVotes += 1;
                saveVotes(catVotes,dogVotes);
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
                saveVotes(catVotes,dogVotes);
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
//        System.out.println(reader.dogImage.image);
        try {

            String imageUrl = reader.dogImage.image;
            URL url = new URL(imageUrl);
            ImageIcon inputImageIcon = new ImageIcon(url);

            if (!imageUrl.endsWith(".gif")) {
                Image image = inputImageIcon.getImage();
                Image scaledImage = image.getScaledInstance((int) (WIDTH / 2), (int) (HEIGHT / 2), Image.SCALE_SMOOTH);
                inputImageIcon = new ImageIcon(scaledImage);  // Update the ImageIcon with the scaled image
            }

            if (inputImageIcon.getImage() != null) {
                image1 = new JLabel(inputImageIcon);
            } else {
                System.out.println("inputImageIcon is null");
                BufferedImage errorImage = ImageIO.read(new File("errorImage.png"));
                image1 = new JLabel(new ImageIcon(errorImage.getScaledInstance((int) (WIDTH / 2), (int) (HEIGHT / 2), Image.SCALE_SMOOTH)));
            }

            dogImage.removeAll();
            dogImage.add(image1);
            dogImage.revalidate();
            dogImage.repaint();

        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Error loading image");
            BufferedImage errorImage = ImageIO.read(new File("errorImage.png"));
            image1 = new JLabel(new ImageIcon(errorImage.getScaledInstance((int) (WIDTH / 2), (int) (HEIGHT / 2), Image.SCALE_SMOOTH)));

            dogImage.removeAll();
            dogImage.add(image1);
            dogImage.revalidate();
            dogImage.repaint();
        }
    }

    public void addImageCat() throws IOException, ParseException {
        reader.pull();
        while(!reader.catImage.image.endsWith(".gif")){
            reader.pull();
        }
        System.out.println(reader.catImage.image);
        try {
            String imageUrl = reader.catImage.image;
            URL url = new URL(imageUrl);

            if (imageUrl.endsWith(".gif")) {
                ImageIcon imgIcon = new ImageIcon(url);
                imgIcon.setImage(imgIcon.getImage().getScaledInstance((int) (WIDTH / 2), (int) (HEIGHT / 2), Image.SCALE_DEFAULT));
                image1 = new JLabel(imgIcon);
            }else {
                ImageIcon inputImageIcon = new ImageIcon(url);
                Image image = inputImageIcon.getImage();
                Image scaledImage = image.getScaledInstance((int) (WIDTH / 2), (int) (HEIGHT / 2), Image.SCALE_SMOOTH);
                inputImageIcon = new ImageIcon(scaledImage);
                image1 = new JLabel(inputImageIcon);
            }
            catImage.removeAll();
            catImage.add(image1);
            catImage.revalidate();
            catImage.repaint();




        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Error loading image");
            BufferedImage errorImage = ImageIO.read(new File("errorImage.png"));
            image1 = new JLabel(new ImageIcon(errorImage.getScaledInstance((int) (WIDTH / 2), (int) (HEIGHT / 2), Image.SCALE_SMOOTH)));

            catImage.removeAll();
            catImage.add(image1);
            catImage.revalidate();
            catImage.repaint();
        }
    }

        public static void saveVotes(int catVotes, int dogVotes) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("saved_votes"))) {
                writer.write(Integer.toString(catVotes)+ " "+ Integer.toString(dogVotes));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public static String loadInt() {
            try (BufferedReader reader = new BufferedReader(new FileReader("saved_votes"))) {
                String line = reader.readLine();
                return (line);
            } catch (IOException | NumberFormatException e) { System.out.println("An error occurred while reading from the file.");
                e.printStackTrace();
                return "0 0";
            }
        }
}