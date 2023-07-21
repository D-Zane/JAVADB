import data.products;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class EdSProdDialog extends JRDialog {
    // Поля класса
    private DBManager manager;
    private BigDecimal old_key; // прежнее значение ключа
    // Два варианта заголовка окна
    private final static String title_add = "Добавление новой продукции";
    private final static String title_ed = "Редактирование информации о продукции";
    // Объектная переменная для класса Tasks
    private products prod = null;
    // Флаг режима «добавление новой строки»
    private boolean isNewRow = false;
    // Форматер для полей дат
    SimpleDateFormat frmt = new SimpleDateFormat("dd-MM-yyyy");
    // Элементы (поля редактирования) для полей записи
    private JTextField edId;
    private JTextField edName;
    private JTextField edDescription;
    private JTextField edUnitPrice;

    private JTextField edMaterialid;
    private JTextField edWorkshop;
    private JTextField edQuantityfo;
    // кнопки
    private JButton btnOk;
    private JButton btnCancel;

    // Конструктор класса
    public EdSProdDialog(Window parent, products prod,
                           DBManager manager) {

        this.manager = manager;
        // Установка флага для режима добавления новой строки
        isNewRow = prod == null ? true : false;
        // Определение заголовка для операций добавл./редакт.
        setTitle(isNewRow ? title_add : title_ed);
        // Определение объекта редактируемой строки
        if (!isNewRow) {
            this.prod = prod; // Существующий объект
            // Сохранение прежнего значения ключа
            old_key = prod.getId();
        } else
            this.prod = new products(); // Новый объект
        // Создание графического интерфейса окна
        createGui();
        // Назначение обработчиков для основных событий
        bindListeners();
        // Получение данных
        loadData();
        pack();
        // Задание режима неизменяемых размеров окна
        setResizable(false);
        setLocationRelativeTo(parent);

    }

    // Метод назначения обработчиков основных событий
    private void bindListeners() {
        // Обработчик нажатия клавиш
        setKeyListener(this, new KeyAdapter() {
            @Override
            // Обработка нажатия клавиши ESC – закрытие окна
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    setDialogResult(JDialogResult.Cancel);
                    close();
                    e.consume();
                } else
                    super.keyPressed(e);
            }
        });
        // Обработка нажатия кнопки закрытия окна
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }
        });
        // Обработка кнопки «Отмена»
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Возврат Cancel и закрытие окна
                setDialogResult(JDialogResult.Cancel);
                close();
            }
        });
        // Обработка кнопки «Сохранить»
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Проверка данных, выход
                // при неправильном заполнении полей
                if (!constructorproducts())
                    return;
                if (isNewRow) {
                    // вызов метода менеджера Добавить новый продукт
                    if (manager.addProducts(prod)) {
                        // при успехе возврат Ok и закрытие окна
                        setDialogResult(JDialogResult.OK);
                        close();
                    }
                } else
                    // вызов метода менеджера Изменить продукт
                    if (manager.updateProducts(prod, old_key)) {
                        setDialogResult(JDialogResult.OK);
                        close();
                    }
            }
        });
    }

    // Метод создания графического интерфейса
    private void createGui() {
        // Создание панели
        JPanel pnl = new JPanel(new MigLayout(
                "insets 6", "[][]", "[]5[]10[]"));
        // Создание полей для редактирования данных
        edId = new JTextField(10);
        edName = new JTextField(50);
        edDescription = new JTextField(50);
        edUnitPrice = new JTextField(10);
        edMaterialid = new JTextField(10);
        edWorkshop = new JTextField(10);
        edQuantityfo = new JTextField(10);
        // Создание кнопок
        btnOk = new JButton("Сохранить");
        btnCancel = new JButton("Отмена");
        // Добавление элементов на панель
        pnl.add(new JLabel("ID Продукта"));
        pnl.add(edId, "span");
        pnl.add(new JLabel("Название"));
        pnl.add(edName, "span");
        pnl.add(new JLabel("Описание"));
        pnl.add(edDescription, "span");
        pnl.add(new JLabel("Цена за шт"));
        pnl.add(edUnitPrice, "span");
        pnl.add(new JLabel("ID Материала"));
        pnl.add(edMaterialid, "span");
        pnl.add(new JLabel("Id Цеха"));
        pnl.add(edWorkshop , "span");
        pnl.add(btnOk, "growx 0, right, sg 1");
        pnl.add(btnCancel, "sg 1");
        // Добавление панели в окно фрейма
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pnl, BorderLayout.CENTER);
    }

    // Метод добавление слушателя клавиатуры
    // к компонентам окна
    private void setKeyListener(Component c, KeyListener kl) {
        c.addKeyListener(kl);
        if (c instanceof Container)
            for (Component comp : ((Container) c).getComponents())
                setKeyListener(comp, kl);
    }

    // Метод инициализации полей формы (при редактировании)
    private void loadData() {
        if (!isNewRow) {
            edId.setText(prod.getProduct_id().toString());
            edName.setText(prod.getName() == null ? "" : prod.getName().toString());
            edDescription.setText(prod.getDescription() == null ? "" : prod.getDescription().toString());
            edUnitPrice.setText(prod.getUnit_price() == null ? "" : prod.getUnit_price().toString());
            edMaterialid.setText(prod.getMaterial_id() == null ? "" : prod.getMaterial_id().toString());
            edWorkshop.setText(prod.getWorkshop_id() == null ? "" : prod.getWorkshop_id().toString());

        }
    }

    // Формирование объекта Продукция перед сохранением
    private boolean constructorproducts() {
        try {
            prod.setProduct_id(edId.getText().equals("") ? null : new BigDecimal(edId.getText()));
            prod.setName(edName.getText().equals("") ? null : new String(edName.getText()));
            prod.setDescription(edDescription.getText().equals("") ? null : new String(edDescription.getText()));
            prod.setUnit_price(edUnitPrice.getText().equals("") ? null : new BigDecimal(edUnitPrice.getText()));
            prod.setMaterial_id(edMaterialid.getText().equals("") ? null : new BigDecimal(edMaterialid.getText()));
            prod.setWorkshop_id(edWorkshop.getText().equals("")? null : new BigDecimal(edWorkshop.getText()));

            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "Ошибка данных",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Возврат объекта Продукт
    public products getProd() {
        return prod;
    }

}
