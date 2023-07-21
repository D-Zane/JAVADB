import net.miginfocom.swing.MigLayout;
import data.products;
import data.materials;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Frmzak extends JRDialog{
    private JFrame frame;
    private JTextField productNameField;
    private JTextField materialCountField;
    private JButton addButton;
    private JPanel productPanel;
    private Connection connection;
    // Параметры подключения к базе данных
    public Frmzak() {
        this.connection = connection;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Product Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 300, 200);

        // Создаем панель с помощью MigLayout
        JPanel mainPanel = new JPanel(new MigLayout("fill"));

        // Создаем компоненты GUI
        JLabel productNameLabel = new JLabel("Product Name:");
        productNameField = new JTextField(20);
        JLabel materialCountLabel = new JLabel("Number of Materials:");
        materialCountField = new JTextField(5);
        addButton = new JButton("Add Product");
        productPanel = new JPanel(new MigLayout("wrap"));

        // Добавляем компоненты на панель
        mainPanel.add(productNameLabel, "gap para");
        mainPanel.add(productNameField, "wrap");
        mainPanel.add(materialCountLabel, "gap para");
        mainPanel.add(materialCountField, "wrap");
        mainPanel.add(addButton, "span, grow, wrap");
        mainPanel.add(new JScrollPane(productPanel), "span, grow");

        // Добавляем обработчик событий для кнопки
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = productNameField.getText();
                int materialCount = Integer.parseInt(materialCountField.getText());
                addProduct(productName, materialCount);
            }
        });

        // Устанавливаем панель на фрейм и отображаем GUI
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    private void addProduct(String productName, int materialCount) {
        // Создаем панель для нового продукта
        JPanel newProductPanel = new JPanel(new MigLayout("wrap"));

        // Добавляем метку с именем продукта
        JLabel productNameLabel = new JLabel("Product: " + productName);
        newProductPanel.add(productNameLabel, "wrap");

        // Удаляем n * m материалов
        for (int i = 0; i < materialCount; i++) {
            JLabel materialLabel = new JLabel("Material " + (i + 1));
            newProductPanel.add(materialLabel, "wrap");
        }

        // Добавляем панель продукта на основную панель
        productPanel.add(newProductPanel, "wrap");

        // Обновляем GUI
        frame.revalidate();
        frame.repaint();

        // Очищаем поля ввода
        productNameField.setText("");
        materialCountField.setText("");

        // Добавляем продукт и удаляем материалы из базы данных
      //  try {
         // Connection connection = DriverManager.getConnection(URL_DB, User, Pwd);

            // Добавляем продукт в базу данных
      //      String insertProductQuery = "UPDATE INTO public.products (product_name) VALUES (?)";
      //      PreparedStatement insertProductStmt = connection.prepareStatement(insertProductQuery);
      //      insertProductStmt.setString(1 );
      //      insertProductStmt.executeUpdate();

            // Удаляем материалы из базы данных
      //  /    String deleteMaterialsQuery = "UPDATE FROM public.materials";
       //     PreparedStatement deleteMaterialsStmt = connection.prepareStatement(deleteMaterialsQuery);
   //         deleteMaterialsStmt.executeUpdate();

       //     connection.close();
      //  } catch (SQLException ex) {
      //      ex.printStackTrace();
      //  }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Frmzak();
            }
        });
    }
}