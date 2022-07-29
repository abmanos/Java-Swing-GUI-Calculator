import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CalculatorGUI {

    JFrame frame = new JFrame("My Calculator");
    JPanel grid = new JPanel();
    int centX = (int) getScreenSize().getWidth();
    int centY = (int) getScreenSize().getHeight();
    int defaultWidth = (int) (centX * 0.5);
    int defaultHeight = (int) (centY * 0.8);

    JFormattedTextField calculation = new JFormattedTextField();
    Operations prevOperation;
    double currentAns;

    public CalculatorGUI(){}
    public void setup() {
        frame.setBackground(new Color(26, 26, 33));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(defaultWidth, defaultHeight));
        frame.setAlwaysOnTop(true);
        frame.setLocation((centX/2) - defaultWidth/2, (centY/2) - defaultHeight/2);
        grid.setLayout(new GridLayout(2,1,0,3));
        frame.add(grid);
        setupTextArea();
        setupButtons();

        frame.pack();
        frame.setVisible(true);
    }
    private void setupButtons(){
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(6,5,0,0));
        ArrayList<JButton> allButtons = new ArrayList<JButton>();
        String[] buttonText = {"+", "-", "*", "/", "<-", "sqrt(x)", "x^2", "x!", "|x|", "π","7", "8", "9", "(", ")", "4", "5", "6", "e", "C", "1", "2", "3", "ln", "CE", "0", ".", "+/-", "%", "="};
        for(String text : buttonText){
            allButtons.add(new JButton(text));
        }
        buttons.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        for(JButton button : allButtons){
            button.setFont(button.getFont().deriveFont(centX/50+0.0f));
            button.setForeground(new Color(255,255,255));
            button.setBackground(new Color(33, 34, 41));
            button.addActionListener(new ButtonPressListener(this, button.getText()));
            buttons.add(button);
        }
        grid.add(buttons);
    }

    private void setupTextArea(){
        calculation.setForeground(new Color(255,255,255));
        calculation.setBackground(new Color(42, 42, 54));
        calculation.setFont(calculation.getFont().deriveFont(centX/10+0.0f));
        grid.add(calculation);
    }

    /*
     * Getters & Setters
     */
    private Dimension getScreenSize(){
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public JFormattedTextField getCalculation() {
        return calculation;
    }

    public void setCalculation(JFormattedTextField calculation) {
        this.calculation = calculation;
    }

    public Operations getPrevOperation() {
        return prevOperation;
    }

    public void setPrevOperation(Operations prevOperation) {
        this.prevOperation = prevOperation;
    }
    public double getCurrentAns() {
        return currentAns;
    }

    public void setCurrentAns(double currentAns) {
        this.currentAns = currentAns;
    }


}
