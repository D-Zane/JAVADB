import data.order_items;
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

public class EdSOrdDialog extends JRDialog {
    // Поля класса
    private DBManager manager;
    private BigDecimal old_key; // прежнее значение ключа
    // Два варианта заголовка окна
    private final static String title_add = "Добавление новой позиции накладной";
    private final static String title_ed = "Редактирование информации о позиции накладной";
    // Объектная переменная для класса Tasks
    private order_items ori = null;
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
    // кнопки
    private JButton btnOk;
    private JButton btnCancel;

    // Конструктор класса
    public EdSOrdDialog(Window parent, order_items ori,
                         DBManager manager) {

        this.manager = manager;
        // Установка флага для режима добавления новой строки
        isNewRow = ori == null ? true : false;
        // Определение заголовка для операций добавл./редакт.
        setTitle(isNewRow ? title_add : title_ed);
        // Определение объекта редактируемой строки
        if (!isNewRow) {
            this.ori = ori; // Существующий объект
            // Сохранение прежнего значения ключа
            old_key = ori.getId();
        } else
            this.ori = new order_items(); // Новый объект
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
                if (!constructororder_items())
                    return;
                if (isNewRow) {
                    // вызов метода менеджера Добавить новый продукт
                    if (manager.addOrder_items(ori)) {
                        // при успехе возврат Ok и закрытие окна
                        setDialogResult(JDialogResult.OK);
                        close();
                    }
                } else
                    // вызов метода менеджера Изменить продукт
                    if (manager.updateOrder_items(ori, old_key)) {
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
        edName = new JTextField(10);
        edDescription = new JTextField(50);
        edUnitPrice = new JTextField(10);
        edMaterialid = new JTextField(10);
        // Создание кнопок
        btnOk = new JButton("Сохранить");
        btnCancel = new JButton("Отмена");
        // Добавление элементов на панель
        pnl.add(new JLabel("ID Позиции"));
        pnl.add(edId, "span");
        pnl.add(new JLabel("Количество"));
        pnl.add(edName, "span");
        pnl.add(new JLabel("ID Заказа"));
        pnl.add(edUnitPrice, "span");
        pnl.add(new JLabel("ID Продукци"));
        pnl.add(edMaterialid, "span");
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
            edId.setText(ori.getId_position().toString());
            edName.setText(ori.getQuantity() == null ? "" : ori.getQuantity().toString());
            edUnitPrice.setText(ori.getOrder_id() == null ? "" : ori.getOrder_id().toString());
            edMaterialid.setText(ori.getProduct_id() == null ? "" : ori.getProduct_id().toString());
        }
    }

    // Формирование объекта Продукция перед сохранением
    private boolean constructororder_items() {
        try {
            ori.setId_position(edId.getText().equals("") ? null : new BigDecimal(edId.getText()));
            ori.setQuantity(edName.getText().equals("") ? null : new BigDecimal(edName.getText()));
            ori.setOrder_id(edUnitPrice.getText().equals("") ? null : new BigDecimal(edUnitPrice.getText()));
            ori.setProduct_id(edMaterialid.getText().equals("") ? null : new BigDecimal(edMaterialid.getText()));

            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "Ошибка данных",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Возврат объекта Продукт
    public order_items getOri() {
        return ori;
    }

}
