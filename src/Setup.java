public class Setup {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        javax.swing.SwingUtilities.invokeLater(() -> calc.setup());
    }
}
