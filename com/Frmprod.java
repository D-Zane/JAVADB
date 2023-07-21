import data.products;
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

public class Frmprod extends JRDialog{
    private DBManager manager;
    private JTable tblProduct;
    private JButton btnClose;
    private Frmprod.TasksTableModel tblModel;
    private TableRowSorter<Frmprod.TasksTableModel> tblSorter;
    private ArrayList<products> products;
    // кнопка редактирования продукта
    private JButton btnEdit;
    // кнопка добавления нового продукта
    private JButton btnNew;
    // кнопка удаления продукта
    private JButton btnDelete;

    public Frmprod(DBManager manager) {
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
        setTitle("Продукция");
        setLocationRelativeTo(this);

    }

    // Получение данных
    private void loadData() {
        // Получение данных через менеджер
        products = manager.loadProducts();
    }

    // Метод создания пользовательского интерфейса
    private void createGUI() {
        // Создание панели
        JPanel pnl = new JPanel(new MigLayout("insets 3, gapy 4", "[grow, fill]", "[]5[grow, fill]10[]"));
        // Создание объекта таблицы
        tblProduct = new JTable();
        // Создание объекта табличной модели на базе
        // сформированного списка
        tblProduct.setModel(tblModel = new Frmprod.TasksTableModel(products));
        // Создание объекта сортировки для табличной модели
        RowSorter<Frmprod.TasksTableModel> sorter = new TableRowSorter<Frmprod.TasksTableModel>(tblModel);
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

        pnl.add(new JLabel("Справочник продукции:"), "growx,span");
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
                addProducts();
            }
        });
        // Для кнопки редактирования
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {editProducts();
            }
        });
        // Для кнопки удаления
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProducts();
            }
        });

    }

    // Модель данных для таблицы
    private class TasksTableModel extends AbstractTableModel {
        private ArrayList<products> products;

        // Конструктор объекта класса
        public TasksTableModel(ArrayList<products> products) {
            this.products = products;
        }

        // Количество колонок в таблице
        @Override
        public int getColumnCount() {
            return 6;
        }

        // Количество строк в таблице = размеру списка
        @Override
        public int getRowCount() {
            return (products == null ? 0 : products.size());
        }

        // Определение содержимого ячеек
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            // Выделяем объект из списка по текущему индексу
            products pd = products.get(rowIndex);
            // Каждой колонке сопоставляем поле объекта
            switch (columnIndex) {
                case 0:
                    return pd.getProduct_id();
                case 1:
                    return pd.getName();
                case 2:
                    return pd.getDescription();
                case 3:
                    return pd.getUnit_price();
                case 4:
                    return pd.getMaterial_id();
                case 5:
                    return pd.getWorkshop_id();
                default:
                    return null;
            }
        }

        // Определение названия колонок
        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "Id Продукта";
                case 1:
                    return "Название";
                case 2:
                    return "Описание";
                case 3:
                    return "Цена за шт";
                case 4:
                    return "ID Материала";
                case 5:
                    return "ID Цеха";
                default:
                    return null;
            }
        }

        // Этот метод используется для определения отрисовщика
        // ячеек колонок в зависимости от типа данных
       // public Class getColumnClass(int c) {
         //   if (c == 4) // защита от null в колонке 4
           //    return java.lang.Number.class;
           // else if (c == 5) // защита от null в колонке 5

             //   return java.lang.String.class;


       //     return getValueAt(0, c).getClass();
      // }

        // Событие добавления строки
        public void addRow(products prod) {
            // Определяем положение добавляемой строки
            int len = products.size();
            // Добавление в новой строки в список модели
            products.add(prod);
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
            if (index != products.size() - 1)
                fireTableRowsUpdated(index + 1, products.size() - 1);
            // Удаление строки из списка модели
            products.remove(index);
            // Обновление отображения после удаления
            fireTableRowsDeleted(index, index);
        }
    }

    // Редактирование текущего продукта
    private void editProducts() {
        int index = tblProduct.getSelectedRow();
        if (index == -1)
            return;
        // Преобразование индекса таблицы в индекс модели
        int modelRow = tblProduct.convertRowIndexToModel(index);
        // Получаем объект из модели по индексу
        products prod = products.get(modelRow);
        BigDecimal key = prod.getId();
        // Создание объекта окна редактирования
        EdSProdDialog dlg = new EdSProdDialog(this, prod, manager);
        // Вызов окна и проверка кода возврата
        if (dlg.showDialog() == JDialogResult.OK) {
            // Вызов метода обновления строки данных табличной модели
            tblModel.updateRow(modelRow);
            System.out.println("Update products OK");
        }
    }

    // Создание нового продукта
    private void addProducts() {
        // Создание объекта окна редактирования
        EdSProdDialog dlg = new EdSProdDialog(this, null, manager);
        // Вызов окна и проверка кода возврата
        if (dlg.showDialog() == JDialogResult.OK) {
            // Создание нового объекта по введенным данным
            products prod = dlg.getProd();
            // Добавление его в табличную модель
            tblModel.addRow(prod);
        }
    }

    // Удаление текущего продукта
    private void deleteProducts() {
        // Определяем индекс текущей строки.
        int index = tblProduct.getSelectedRow();
        // Если нет выделенной строки, то выход
        if (index == -1)
            return;
        // Вывод запроса на удаления. При отказе - выход
        if (JOptionPane.showConfirmDialog(this, "Удалить продукцию?", "Подтверждение", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION)
            return;
        // Преобразование индекса представления в индекс модели
        int modelRow = tblProduct.convertRowIndexToModel(index);
        // Создание объекта для выделенной строки
        products prod = products.get(modelRow);
        try {
            // Определение кода (первичного ключа) выделенной строки
            BigDecimal kod = prod.getProduct_id();
            // Вызов метода менеджера для удаления строки
            if (manager.deleteProducts(kod)) {
                // Вызов метода удаления строки из табличной модели
                tblModel.deleteRow(modelRow);
                System.out.println("Delete products OK");
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
        URL url = Frmprod.class.getResource("/images/add.png");
        // Создание кнопки с изображением
        btnNew = new JButton(new ImageIcon(url));
        // На кнопку не устанавливается фокус
        btnNew.setFocusable(false);
        // Добавление всплывающей подсказки для кнопки
        btnNew.setToolTipText("Добавить новую продукцию");
        // Добавление кнопки «Удалить»
        url = Frmprod.class.getResource("/images/del.png");
        btnDelete = new JButton(new ImageIcon(url));
        btnDelete.setFocusable(false);
        btnDelete.setToolTipText("Удалить продукцию");
        // Добавление кнопки «Редактировать»
        url = Frmprod.class.getResource("/images/edit.png");
        btnEdit = new JButton(new ImageIcon(url));
        btnEdit.setFocusable(false);
        btnEdit.setToolTipText("Изменить данные продукции");
        // Добавление кнопок на панель
        res.add(btnNew);
        res.add(btnEdit);
        res.add(btnDelete);
        // Возврат панели в качестве результата
        return res;
    }
}


