import data.detailing;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;

public class Frmdet extends JRDialog{
    private DBManager manager;
    private JTable tblProduct;
    private JButton btnClose;
    private Frmdet.TasksTableModel tblModel;
    private TableRowSorter<Frmdet.TasksTableModel> tblSorter;
    private ArrayList<detailing> detailing;
    // кнопка редактирования продукта
    private JButton btnEdit;
    // кнопка добавления нового продукта
    private JButton btnNew;
    // кнопка удаления продукта
    private JButton btnDelete;

    public Frmdet(DBManager manager) {
        super();
        this.manager = manager;
        // Установка модального режима вывода окна
        setModal(true);
        // при закрытии окна освобождаем используемые им ресурсы
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Получение данных
        loadData();
        // Построение графического интерфейса окна
        createGUI();
        // Добавление обработчиков для основных событий
        bindListeners();
        pack();
        // Заголовок окна
        setTitle("Детализация");
        setLocationRelativeTo(this);
    }

    // Получение данных
    private void loadData() {
        // Получение данных через менеджер
        detailing = manager.loadDetailing();
    }

    // Метод создания пользовательского интерфейса
    private void createGUI() {
        // Создание панели
        JPanel pnl = new JPanel(new MigLayout("insets 3, gapy 4", "[grow, fill]", "[]5[grow, fill]10[]"));
        // Создание объекта таблицы
        tblProduct = new JTable();
        // Создание объекта табличной модели на базе
        // сформированного списка
        tblProduct.setModel(tblModel = new Frmdet.TasksTableModel(detailing));
        // Создание объекта сортировки для табличной модели
        RowSorter<Frmdet.TasksTableModel> sorter = new TableRowSorter<Frmdet.TasksTableModel>(tblModel);
        // Назначение объекта сортировки таблице
        tblProduct.setRowSorter(sorter);
        // Задаем параметры внешнего вида таблицы
        // Выделение полосой всей текущей строки
        tblProduct.setRowSelectionAllowed(true);
        // Задаем промежутки между ячейками
        tblProduct.setIntercellSpacing(new Dimension(0, 1));
        // Задаем цвет сетки
        tblProduct.setGridColor(new Color(170, 170, 255).darker());
        // Автоматическое определение ширины последней колонки
        tblProduct.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        // Возможность выделения только 1 строки
        tblProduct.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Создание области прокрутки и вставка в нее таблицы
        JScrollPane scrlPane = new JScrollPane(tblProduct);
        scrlPane.getViewport().setBackground(Color.white);
        scrlPane.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(3, 0, 3, 0), scrlPane.getBorder()));
        // Создание кнопки для закрытия формы
        btnClose = new JButton("Закрыть");

        // Добавление на панель: метки, области с таблицей и кнопки
        pnl.add(scrlPane, "grow, span");
        pnl.add(btnClose, "growx 0, right");
        // Добавление панели в окно
        getContentPane().setLayout(new MigLayout("insets 0 2 0 2, gapy 0", "[grow, fill]", "[grow, fill]"));
        getContentPane().add(pnl, "grow");

        pnl.add(getToolBar(), "growx,wrap");// Создание панели

        pnl.add(new JLabel("Детализация:"), "growx,span");
        pnl.add(scrlPane, "grow, span");
        pnl.add(btnClose, "growx 0, right");

    }

    // Метод назначения обработчиков
    private void bindListeners() {
        // Для кнопки Закрыть
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Закрываем окно
                dispose();
            }
        });

        // Для кнопки добавления
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDetailing();
            }
        });
        // Для кнопки редактирования
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editDetailing();
            }
        });
        // Для кнопки удаления
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteDetailing();
            }
        });

    }

    // Модель данных для таблицы
    private class TasksTableModel extends AbstractTableModel {
        private ArrayList<detailing> detailing;

        // Конструктор объекта класса
        public TasksTableModel(ArrayList<detailing> detailing) {
            this.detailing = detailing;
        }

        // Количество колонок в таблице
        @Override
        public int getColumnCount() {
            return 4;
        }

        // Количество строк в таблице = размеру списка
        @Override
        public int getRowCount() {
            return (detailing == null ? 0 : detailing.size());
        }

        // Определение содержимого ячеек
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            // Выделяем объект из списка по текущему индексу
            detailing pd = detailing.get(rowIndex);
            // Каждой колонке сопоставляем поле объекта
            switch (columnIndex) {
                case 0:
                    return pd.getDetailing();
                case 1:
                    return pd.getInventory_id();
                case 2:
                    return pd.getQuantity();
                case 3:
                    return pd.getProduct_id();
                default:
                    return null;
            }
        }

        // Определение названия колонок
        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "ID Детализации";
                case 1:
                    return "ID Инвентаризации";
                case 2:
                    return "Количество";
                case 3:
                    return "ID Продукции";
                default:
                    return null;
            }
        }

        // Этот метод используется для определения отрисовщика
        // ячеек колонок в зависимости от типа данных
        public Class getColumnClass(int c) {
            if (c == 4) // защита от null в колонке 4
                return java.lang.Number.class;
            else if (c == 5) // защита от null в колонке 5

                return java.lang.String.class;


            return getValueAt(0, c).getClass();
        }

        // Событие добавления строки
        public void addRow(detailing det) {
            // Определяем положение добавляемой строки
            int len = detailing.size();
            // Добавление в новой строки в список модели
            detailing.add(det);
            // Обновление отображения строки с новыми данными
            fireTableRowsInserted(len, len);
        }

        // Событие редактирования
        public void updateRow(int index) {
            // Обновление отображения измененной строки
            fireTableRowsUpdated(index, index);
        }

        // Событие удаления
        public void deleteRow(int index) {
            // Если удаленная строка не конце таблицы
            // обновляем нижележащие строки
            if (index != detailing.size() - 1)
                fireTableRowsUpdated(index + 1, detailing.size() - 1);
            // Удаление строки из списка модели
            detailing.remove(index);
            // Обновление отображения после удаления
            fireTableRowsDeleted(index, index);
        }
    }

    // Редактирование текущего продукта
    private void editDetailing() {
        int index = tblProduct.getSelectedRow();
        if (index == -1)
            return;
        // Преобразование индекса таблицы в индекс модели
        int modelRow = tblProduct.convertRowIndexToModel(index);
        // Получаем объект из модели по индексу
        detailing det = detailing.get(modelRow);
        BigDecimal key = det.getId();
        // Создание объекта окна редактирования
        EdSDetDialog dlg = new EdSDetDialog(this, det, manager);
        // Вызов окна и проверка кода возврата
        if (dlg.showDialog() == JDialogResult.OK) {
            // Вызов метода обновления строки данных табличной модели
            tblModel.updateRow(modelRow);
            System.out.println("Update detailing OK");
        }
    }

    // Создание нового продукта
    private void addDetailing() {
        // Создание объекта окна редактирования
        EdSDetDialog dlg = new EdSDetDialog(this, null, manager);
        // Вызов окна и проверка кода возврата
        if (dlg.showDialog() == JDialogResult.OK) {
            // Создание нового объекта по введенным данным
            detailing det = dlg.getDet();
            // Добавление его в табличную модель
            tblModel.addRow(det);
        }
    }

    // Удаление текущего продукта
    private void deleteDetailing() {
        // Определяем индекс текущей строки.
        int index = tblProduct.getSelectedRow();
        // Если нет выделенной строки, то выход
        if (index == -1)
            return;
        // Вывод запроса на удаления. При отказе - выход
        if (JOptionPane.showConfirmDialog(this, "Удалить информацию о детализации?", "Подтверждение", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION)
            return;
        // Преобразование индекса представления в индекс модели
        int modelRow = tblProduct.convertRowIndexToModel(index);
        // Создание объекта для выделенной строки
        detailing det = detailing.get(modelRow);
        try {
            // Определение кода (первичного ключа) выделенной строки
            BigDecimal kod = det.getDetailing();
            // Вызов метода менеджера для удаления строки
            if (manager.deleteDetailing(kod)) {
                // Вызов метода удаления строки из табличной модели
                tblModel.deleteRow(modelRow);
                System.out.println("Delete detailing OK");
            } else
                JOptionPane.showMessageDialog(this, "Ошибка удаления строки", "Ошибка", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Ошибка удаления", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JToolBar getToolBar() {
        // Создание панели
        JToolBar res = new JToolBar();
        // Неизменяемое положение панели
        res.setFloatable(false);
        // Добавление кнопки «Добавить»
        // Определение местоположения изображения для кнопки
        URL url = Frmmat.class.getResource("/images/add.png");
        // Создание кнопки с изображением
        btnNew = new JButton(new ImageIcon(url));
        // На кнопку не устанавливается фокус
        btnNew.setFocusable(false);
        // Добавление всплывающей подсказки для кнопки
        btnNew.setToolTipText("Добавить новую детализацию");
        // Добавление кнопки «Удалить»
        url = Frmmat.class.getResource("/images/del.png");
        btnDelete = new JButton(new ImageIcon(url));
        btnDelete.setFocusable(false);
        btnDelete.setToolTipText("Удалить данные детализации");
        // Добавление кнопки «Редактировать»
        url = Frmmat.class.getResource("/images/edit.png");
        btnEdit = new JButton(new ImageIcon(url));
        btnEdit.setFocusable(false);
        btnEdit.setToolTipText("Изменить данные детализации");
        // Добавление кнопок на панель
        res.add(btnNew);
        res.add(btnEdit);
        res.add(btnDelete);
        // Возврат панели в качестве результата
        return res;
    }
}

