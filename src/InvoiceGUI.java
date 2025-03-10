import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoiceGUI extends JFrame {
    private final JTextField productNameField;
    private final JTextField unitPriceField;
    private final JTextField quantityField;
    private final JTextArea invoiceTextArea;
    private final Invoice invoice;

    public InvoiceGUI() {
        setTitle("Invoice System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        invoice = new Invoice();

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 5, 5));

        inputPanel.add(new JLabel("Product Name:"));
        productNameField = new JTextField();
        inputPanel.add(productNameField);

        inputPanel.add(new JLabel("Unit Price:"));
        unitPriceField = new JTextField();
        inputPanel.add(unitPriceField);

        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        JButton addButton = new JButton("Add Item");
        inputPanel.add(addButton);

        JButton generateButton = new JButton("Generate Invoice");
        inputPanel.add(generateButton);

        add(inputPanel, BorderLayout.NORTH);

        // Invoice Output Area
        invoiceTextArea = new JTextArea();
        invoiceTextArea.setEditable(false);
        add(new JScrollPane(invoiceTextArea), BorderLayout.CENTER);

        // Button Actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addLineItem();
            }
        });

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateInvoice();
            }
        });
    }

    private void addLineItem() {
        try {
            String productName = productNameField.getText();
            double unitPrice = Double.parseDouble(unitPriceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            if (productName.isEmpty() || unitPrice <= 0 || quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid values.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Product product = new Product(productName, unitPrice);
            LineItem lineItem = new LineItem(product, quantity);
            invoice.addLineItem(lineItem);

            // Clear input fields
            productNameField.setText("");
            unitPriceField.setText("");
            quantityField.setText("");

            JOptionPane.showMessageDialog(this, "Item added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter numbers for Unit Price and Quantity.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generateInvoice() {
        invoiceTextArea.setText(invoice.generateInvoice());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InvoiceGUI gui = new InvoiceGUI();
            gui.setVisible(true);
        });
    }
}
