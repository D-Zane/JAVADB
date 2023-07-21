import data.book;
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

public class EdSBookDialog extends JRDialog {
    // Поля класса
    private DBManager manager;
    private BigDecimal old_key; // прежнее значение ключа
    // Два варианта заголовка окна
    private final static String title_add = "Добавление нового движения";
    private final static String title_ed = "Редактирование информации о движении";
    // Объектная переменная для класса Tasks
    private book bok = null;
    // Флаг режима «добавление новой строки»
    private boolean isNewRow = false;
    // Форматер для полей дат
    SimpleDateFormat frmt = new SimpleDateFormat("dd-MM-yyyy");
    // Элементы (поля редактирования) для полей записи
    private JTextField edId;
    private JTextField edName;
    private JTextField edDescription;
    private JTextField edUnitPrice;

    private JTextField edCustomer;

    private JTextField edMaterialid;

    private JTextField edMaterial;
    // кнопки
    private JButton btnOk;
    private JButton btnCancel;

    // Конструктор класса
    public EdSBookDialog(Window parent, book bok,
                         DBManager manager) {

        this.manager = manager;
        // Установка флага для режима добавления новой строки
        isNewRow = bok == null ? true : false;
        // Определение заголовка для операций добавл./редакт.
        setTitle(isNewRow ? title_add : title_ed);
        // Определение объекта редактируемой строки
        if (!isNewRow) {
            this.bok = bok; // Существующий объект
            // Сохранение прежнего значения ключа
            old_key = bok.getId();
        } else
            this.bok = new book(); // Новый объект
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
                if (!constructorbook())
                    return;
                if (isNewRow) {
                    // вызов метода менеджера Добавить новый продукт
                    if (manager.addBook(bok)) {
                        // при успехе возврат Ok и закрытие окна
                        setDialogResult(JDialogResult.OK);
                        close();
                    }
                } else
                    // вызов метода менеджера Изменить продукт
                    if (manager.updateBook(bok, old_key)) {
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
        edCustomer = new JTextField(10);
        edMaterial = new JTextField(10);
        // Создание кнопок
        btnOk = new JButton("Сохранить");
        btnCancel = new JButton("Отмена");
        // Добавление элементов на панель
        pnl.add(new JLabel("ID Заказа"));
        pnl.add(edId, "span");
        pnl.add(new JLabel("Дата Заказа"));
        pnl.add(edName, "span");
        pnl.add(new JLabel("Номер Документа"));
        pnl.add(edDescription, "span");
        pnl.add(new JLabel("Цена Заказа"));
        pnl.add(edMaterial, "span");
        pnl.add(new JLabel("Тип Операции"));
        pnl.add(edUnitPrice, "span");
        pnl.add(new JLabel("ID Заказчика"));
        pnl.add(edCustomer, "span");
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
            edId.setText(bok.getOrder_id().toString());
            edName.setText(bok.getOrder_date() == null ? "" : bok.getOrder_date().toString());
            edDescription.setText(bok.getNum_doc() == null ? "" : bok.getNum_doc().toString());
            edMaterial.setText(bok.getTotal_price() == null ? "" : bok.getTotal_price().toString());
            edUnitPrice.setText(bok.getType_of_operation() == null ? "" : bok.getType_of_operation().toString());
            edCustomer.setText(bok.getCustomer_id() == null ? "" : bok.getCustomer_id().toString());

        }
    }

    // Формирование объекта Продукция перед сохранением
    private boolean constructorbook() {
        try {
            bok.setOrder_id(edId.getText().equals("") ? null : new BigDecimal(edId.getText()));
            Date utilDate = null;
            if (!edName.getText().equals("")) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    utilDate = formatter.parse(edName.getText());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            java.sql.Date sqlDate = null;
            if (utilDate != null) {
                sqlDate = new java.sql.Date(utilDate.getTime());
            }
            bok.setOrder_date(sqlDate);
            bok.setNum_doc(edDescription.getText().equals("") ? null : new BigDecimal(edDescription.getText()));
            bok.setTotal_price(edMaterial.getText().equals("") ? null : new BigDecimal(edMaterial.getText()));
            bok.setType_of_operation(edUnitPrice.getText().equals("") ? null : new String(edUnitPrice.getText()));
            bok.setCustomer_id(edCustomer.getText().equals("") ? null : new BigDecimal(edCustomer.getText()));

            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "Ошибка данных",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Возврат объекта Продукт
    public book getBok() {
        return bok;
    }

}
