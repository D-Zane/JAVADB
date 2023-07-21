import data.materials;
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
import java.text.SimpleDateFormat;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;


public class EdSMatDialog extends JRDialog {
    // Поля класса
    private DBManager manager;
    private BigDecimal old_key; // прежнее значение ключа
    // Два варианта заголовка окна
    private final static String title_add = "Добавление нового материала";
    private final static String title_ed = "Редактирование информации о материале";
    // Объектная переменная для класса Tasks
    private materials mat = null;
    // Флаг режима «добавление новой строки»
    private boolean isNewRow = false;
    // Форматер для полей дат
    SimpleDateFormat frmt = new SimpleDateFormat("dd-MM-yyyy");
    // Элементы (поля редактирования) для полей записи
    private JTextField edId;
    private JTextField edName;
    private JTextField edDescription;
    private JTextField edUnitPrice;

    private JTextField edNumber;

    private JTextField edPrice;
    // кнопки
    private JButton btnOk;
    private JButton btnCancel;

    // Конструктор класса
    public EdSMatDialog(Window parent, materials mat,
                        DBManager manager) {
        this.manager = manager;
        // Установка флага для режима добавления новой строки
        isNewRow = mat == null ? true : false;
        // Определение заголовка для операций добавл./редакт.
        setTitle(isNewRow ? title_add : title_ed);
        // Определение объекта редактируемой строки
        if (!isNewRow) {
            this.mat = mat; // Существующий объект
            // Сохранение прежнего значения ключа
            old_key = mat.getId();
        } else
            this.mat = new materials(); // Новый объект
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
                if (!constructmaterials())
                    return;
                if (isNewRow) {
                    // вызов метода менеджера Добавить новый продукт
                    if (manager.addMaterials(mat)) {
                        // при успехе возврат Ok и закрытие окна
                        setDialogResult(JDialogResult.OK);
                        close();
                    }
                } else
                    // вызов метода менеджера Изменить продукт
                    if (manager.updateMaterials(mat, old_key)) {
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
        edName = new JTextField(25);
        edDescription = new JTextField(10);
        edUnitPrice = new JTextField(10);
        edNumber = new JTextField(10);
        edPrice = new JTextField(10);
        // Создание кнопок
        btnOk = new JButton("Сохранить");
        btnCancel = new JButton("Отмена");
        // Добавление элементов на панель
        pnl.add(new JLabel("ID Материала"));
        pnl.add(edId, "span");
        pnl.add(new JLabel("Название"));
        pnl.add(edName, "span");
        pnl.add(new JLabel("ID Поставщика"));
        pnl.add(edDescription, "span");
        pnl.add(new JLabel("Цена за шт"));
        pnl.add(edUnitPrice, "span");
        pnl.add(new JLabel("Количество"));
        pnl.add(edNumber, "span");
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
            edId.setText(mat.getMaterial_id().toString());
            edName.setText(mat.getName() == null ? "" : mat.getName().toString());
            edDescription.setText(mat.getSupplier_id() == null ? "" : mat.getSupplier_id().toString());
            edUnitPrice.setText(mat.getPrice() == null ? "" : mat.getPrice().toString());
            edNumber.setText(mat.getQuantity() == null ? "" : mat.getQuantity().toString());
        }
    }

    // Формирование объекта Продукция перед сохранением
    private boolean constructmaterials() {
        try {
            mat.setMaterial_id(edId.getText().equals("") ? null : new BigDecimal(edId.getText()));
            mat.setName(edName.getText().equals("") ? null : new String(edName.getText()));
            mat.setSupplier_id(edDescription.getText().equals("") ? null : new BigDecimal(edDescription.getText()));
            mat.setPrice(edUnitPrice.getText().equals("") ? null : new BigDecimal(edUnitPrice.getText()));
            mat.setQuantity(edNumber.getText().equals("") ? null : new BigDecimal(edNumber.getText()));


            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "Ошибка данных",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Возврат объекта Продукт
    public materials getMat() {
        return mat;
    }

}

