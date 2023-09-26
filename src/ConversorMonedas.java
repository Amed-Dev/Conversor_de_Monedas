import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class ConversorMonedas extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel amountLabel, fromLabel, toLabel, resultLabel, infoLabel;
    private JTextField amountField;
    private JComboBox<String> fromComboBox, toComboBox;
    private JButton convertButton, clearButton;
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    private final String[] currencies = {"USD-Dolar estadounidense", "EUR-Euro", "JPY-Yen Japonés", "GBP-Libra esterlina", "CAD-Dolar canadiense", "AUD-Dolar austrialiano", "CHF-Franco suizo", "CNY-Renminbi","INR-Rupia india", "PEN-Sol peruano"};
    private double[] exchangeRates = {1.00, 0.94, 148.87, 0.82, 1.35, 1.56, 0.91, 7.31, 83.20, 3.77};

    public ConversorMonedas() {
    	setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Conversor de Monedas");
        setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());

        amountLabel = new JLabel("Monto:");
        amountLabel.setBounds(20, 19, 200, 30);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(220, 19, 200, 30);
        add(amountField);

        fromLabel = new JLabel("De:");
        fromLabel.setBounds(20, 50, 200, 30);
        add(fromLabel);

        fromComboBox = new JComboBox<>(currencies);
        fromComboBox.setBounds(220, 50, 200, 30);
        add(fromComboBox);

        toLabel = new JLabel("Para:");
        toLabel.setBounds(20, 82, 200, 30);
        add(toLabel);

        toComboBox = new JComboBox<>(currencies);
        toComboBox.setBounds(220, 82, 200, 30);
        add(toComboBox);

        resultLabel = new JLabel();
        resultLabel.setBounds(75, 119, 300, 30);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        add(resultLabel);

        infoLabel = new JLabel("<html><font color='red'>*</font><font color='#333'>Las conversiones son en referencia a los USD</font></html>");
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        infoLabel.setBounds(20, 139, 400, 30);
        add(infoLabel);

        convertButton = new JButton("Convertir");
        convertButton.setBounds(60, 170, 150, 30);
        add(convertButton);

        clearButton = new JButton("Limpiar");
        clearButton.setBounds(230, 170, 150, 30);
        add(clearButton);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    String fromCurrency = (String) fromComboBox.getSelectedItem();
                    String fromCurrencyName = fromCurrency.substring(0, 3);
                    String toCurrency = (String) toComboBox.getSelectedItem();
                    String toCcurrencyName = toCurrency.substring(0, 3);
                    double exchangeRate = exchangeRates[getIndex(toCurrency)] / exchangeRates[getIndex(fromCurrency)];
                    double result = amount * exchangeRate;
                    String messageResult = amount + " " + fromCurrencyName + " = "+ decimalFormat.format(result) + " " + toCcurrencyName;
                    resultLabel.setText(messageResult);
                } catch (Exception ex) {
                	resultLabel.setForeground(new Color(153,80,72));
                    resultLabel.setText("Debe de ingresar un número");
                }
            }
        });
        clearButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                amountField.setText("");
                resultLabel.setText("");
            }
        });
        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private int getIndex(String currency) {
        for (int i = 0; i < currencies.length; i++) {
            if (currency.equals(currencies[i])) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ConversorMonedas conversor = new ConversorMonedas();
        conversor.setBounds(0, 0, 450, 250);
        conversor.setVisible(true);
        conversor.setResizable(false);
        conversor.setLocationRelativeTo(null);
    }
}
