package kwic2;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class KWIC2 {

    private JTextArea outputArea;
    private JTextArea inputArea;
    private JButton startButton;

    public KWIC2() {
        JFrame frame = new JFrame("KWIC Indexing System");
        // Add a window listner for close button
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.getContentPane();
        frame.setSize(400, 400);

        JPanel mainFrame = new JPanel(new BorderLayout());

        JPanel centerLayout = new JPanel();
        centerLayout.setLayout(new BoxLayout(centerLayout, BoxLayout.Y_AXIS));

        inputArea = new JTextArea();
        inputArea.setText("Type in here");
        inputArea.setBorder(BorderFactory.createTitledBorder("Input"));
        JScrollPane inputScroll = new JScrollPane(inputArea);
        centerLayout.add(inputScroll);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBorder(BorderFactory.createTitledBorder("Output"));
        JScrollPane outputScroll = new JScrollPane(outputArea);
        centerLayout.add(outputScroll);

        startButton = new JButton("Start");
        
        mainFrame.add(centerLayout, BorderLayout.CENTER);
        mainFrame.add(startButton, BorderLayout.SOUTH);
        
        frame.add(mainFrame);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new KWIC2();
    }//end main
}
