public class Setup {
    public static void main(String[] args) {
        CalculatorGUI calc = new CalculatorGUI();
        javax.swing.SwingUtilities.invokeLater(() -> calc.setup());
    }
}
