import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Main extends JFrame {
    private JTextArea textArea;
    private JLabel yearsProcessedLabel;
    private ProductSales productSales;
    private static final int SALES_LIMIT = 500;

    public Main() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Product Sales Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        // Create menu bar
        createMenuBar();

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton loadButton = new JButton("Load Product Data");
        JButton saveButton = new JButton("Save Product Data");

        // Create text area with scroll pane
        textArea = new JTextArea(15, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Create results panel
        JPanel resultsPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        yearsProcessedLabel = new JLabel("Years Processed: 0");
        yearsProcessedLabel.setBorder(BorderFactory.createLoweredBevelBorder());

        // Add components to button panel
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);

        // Add components to main panel
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(resultsPanel, BorderLayout.SOUTH);
        mainPanel.add(yearsProcessedLabel, BorderLayout.SOUTH);

        // Add action listeners
        loadButton.addActionListener(e -> loadProductData());
        saveButton.addActionListener(e -> saveProductData());

        add(mainPanel);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);

        // Tools menu
        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem loadMenuItem = new JMenuItem("Load Product Data");
        JMenuItem saveMenuItem = new JMenuItem("Save Product Data");
        JMenuItem clearMenuItem = new JMenuItem("Clear");

        loadMenuItem.addActionListener(e -> loadProductData());
        saveMenuItem.addActionListener(e -> saveProductData());
        clearMenuItem.addActionListener(e -> clearData());

        toolsMenu.add(loadMenuItem);
        toolsMenu.add(saveMenuItem);
        toolsMenu.add(clearMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);

        setJMenuBar(menuBar);
    }

    private void loadProductData() {
        // Sample data from the requirement
        int[][] salesData = {
                {300, 150, 700}, // Year 1: Microphone, Speakers, Mixing Desk
                {250, 200, 600} // Year 2: Microphone, Speakers, Mixing Desk
        };

        productSales = new ProductSales(salesData, SALES_LIMIT);

        // Display data in text area
        displaySalesData(salesData);

        // Update results
        updateResults();
    }

    private void displaySalesData(int[][] salesData) {
        StringBuilder sb = new StringBuilder();
        sb.append("Product Sales Data:\n\n");
        sb.append(String.format("%-15s %-12s %-12s %-12s\n",
                "Year", "Microphone", "Speakers", "Mixing Desk"));
        sb.append("-------------------------------------------------\n");

        String[] products = {"Microphone", "Speakers", "Mixing Desk"};

        for (int year = 0; year < salesData.length; year++) {
            sb.append(String.format("%-15s", "Sales for year " + (year + 1)));
            for (int product = 0; product < salesData[year].length; product++) {
                sb.append(String.format("%-12s", salesData[year][product]));
            }
            sb.append("\n");
        }

        textArea.setText(sb.toString());
    }

    private void updateResults() {
        StringBuilder results = new StringBuilder();
        results.append("RESULTS:\n");
        results.append("--------\n");
        results.append("Total Sales: ").append(productSales.GetTotalSales()).append("\n");
        results.append("Average Sales: ").append(String.format("%.0f", productSales.GetAverageSales())).append("\n");
        results.append("Sales over limit: ").append(productSales.GetSalesOverLimit()).append("\n");
        results.append("Sales under limit: ").append(productSales.GetSalesUnderLimit()).append("\n");

        textArea.append("\n\n" + results.toString());
        yearsProcessedLabel.setText("Years Processed: " + productSales.GetProductsProcessed());
    }

    private void saveProductData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data.txt"))) {
            writer.println("DATA LOG");
            writer.println("Total Sales: " + (productSales != null ? productSales.GetTotalSales() : 0));
            writer.println("Average Sales: " + (productSales != null ?
                    String.format("%.0f", productSales.GetAverageSales()) : 0));
            writer.println("Sales over Limit: " + (productSales != null ? productSales.GetSalesOverLimit() : 0));
            writer.println("Sales under Limit: " + (productSales != null ? productSales.GetSalesUnderLimit() : 0));

            JOptionPane.showMessageDialog(this, "Data saved to data.txt", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearData() {
        textArea.setText("");
        yearsProcessedLabel.setText("Years Processed: 0");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}
