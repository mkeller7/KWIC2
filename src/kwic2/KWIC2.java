package kwic2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private final JTextArea outputArea;
    private final JTextArea inputArea;
    private final JButton startButton;

    final private ISetFilter lineStorage = new LineStorage();
    final private IFilter circularShift = new CircularShift(lineStorage);
    final private IFilter noiseWords = new RemoveNoiseWord(lineStorage, circularShift);
    final private IFilter output = new Output(lineStorage, noiseWords);

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
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processInput();
            }
        });

        mainFrame.add(centerLayout, BorderLayout.CENTER);
        mainFrame.add(startButton, BorderLayout.SOUTH);

        frame.add(mainFrame);
        frame.setVisible(true);
    }

    private void processInput() {
        //Mark the end of the text with a flag
        lineStorage.setText(inputArea.getText() + IFilter.END_OF_FILE_FLAG);

        //Display the info of the text that is read in
        System.out.println(lineStorage);
        
        circularShift.process();
        noiseWords.process();
        output.process();

        //Display the results
        outputArea.setText(((Output) output).getText());
    }

    public static void main(String[] args) {
        new KWIC2();
    }//end main
}
