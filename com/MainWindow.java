
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class MainWindow extends JFrame {
	// Поля класса
	private DBManager manager;

	// Конструктор класса

	public MainWindow(DBManager manager) {

		this.manager = manager;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Тест соединения
		testConn();
		// Создание графического интерфейса окна
		createGUI();
		// Назначение окну слушателей
		bindListeners();
		// Пакуем окно
		pack();
		// Заголовок окна
		setTitle("Автоматизация производственной логистики");
		// Вывод окна по центру экрана
		setLocationRelativeTo(null);
		manager.setPath(); // СПРОСИТЬ ПРО ЭТО
	}

	// Назначение элементам окна слушателей
	private void bindListeners() {
		// Сюда будут добавлены методы для активных элементов окна
		miExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// Вызов окна просмотра справочника продукции

		miProducts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { ShowSProducts();
			}
		});

		miMaterials.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowSMaterials(); // СПРОСИТЬ ПРО ЭТО!!!!!!!!!!!!!!!!!1
			}
		});

		miWorkshop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowSWorkshop();
			}
		});

		miCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowSCustomer();
			}
		});

		miBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowSBook();
			}
		});
		miOrderitems.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowSOrder();
			}
		});
		miWarehouse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowSWarehouse();
			}
		});
		miDetailing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowSDetailing();
			}
		});
		miInventory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowSInventory();
			}
		});
		miSuppliers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowSSuppliers();
			}
		});
		miProd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShosSZak();
			}
		});
	}
	protected void ShosSZak(){
		Frmzak frm = new Frmzak();
		frm.setVisible(true);
	}
	protected void ShowSSuppliers(){
		Frmsup frm = new Frmsup(manager);
		frm.setVisible(true);
	}
	protected void ShowSInventory(){
		Frminv frm = new Frminv(manager);
		frm.setVisible(true);
	}
	protected void ShowSDetailing(){
		Frmdet frm = new Frmdet(manager);
		frm.setVisible(true);
	}
	protected void ShowSWarehouse(){
		Frmware frm = new Frmware(manager);
		frm.setVisible(true);
	}
	protected void ShowSOrder(){
		Frmord frm = new Frmord(manager);
		frm.setVisible(true);
	}
	protected void ShowSProducts() {
		Frmprod frm = new Frmprod(manager);
		frm.setVisible(true);
	}

	protected void ShowSMaterials() {
		Frmmat frm = new Frmmat(manager);
		frm.setVisible(true);
	}

	protected void ShowSWorkshop(){
		Frmwork frm = new Frmwork(manager);
		frm.setVisible(true);
	}

	protected void ShowSCustomer(){
		Frmcust frm = new Frmcust(manager);
		frm.setVisible(true);
	}
	protected void ShowSBook(){
		Frmbook frm = new Frmbook(manager);
		frm.setVisible(true);
	}
	// Метод тестирования соединения
	private void testConn() {
		// Вызов метода менеджера для получения версии PG
		String ver = manager.getVersion();
		// Вывод результата в консоль
		System.out.println(ver);
	}

	// Создание графического интерфейса окна
	private void createGUI() {
		// Создаем панель
		JPanel pnlImg = new JPanel(new FlowLayout());
		// Размещаем на панели изображение
		// Изображение находится в папке src/images
		// и в папке bin\images
		URL url = this.getClass().getResource("images/chair.jpg");
		System.out.println(url);
		BufferedImage wPic = null;
		try {
			// Падает здесь
			wPic = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Изображение размещаем на метке
		JLabel wIcon = new JLabel(new ImageIcon(wPic));
		// Метку добавляем на панель
		pnlImg.add(wIcon);
		// Задаем менеджер расположения основного окна
		// Метка заполняет все пространство окна
		getContentPane().setLayout(
				new MigLayout("insets 0 2 0 2, gapy 0", "[grow, fill]",
						"[grow, fill]"));
		// Панель размещаем на фрейме
		getContentPane().add(pnlImg, "grow");

		setJMenuBar(createMenu());// ЭТО ДОБАВЛЯЛ!

	}

	public void initialize() {
		Locale.setDefault(new Locale("ru"));
	}

	JMenuBar mainMenu; // Контейнер меню
	JMenuItem miExit; // Пункт меню
	JMenu mSpr; // Подменю Справочники
	JMenuItem miProducts; // Пункт меню Продукция
	JMenuItem miMaterials; // Пункт меню Группы
	JMenuItem miWorkshop;
	JMenuItem miCustomer;
	JMenuItem miBook;
	JMenuItem miOrderitems;
	JMenuItem miWarehouse;
	JMenuItem miDetailing;
	JMenuItem miInventory;
	JMenuItem miSuppliers;
	JMenuItem miSpok; // Пункт меню Покупатели
	JMenu mOper; // Подменю Операции
	JMenuItem miProd; // Пункт меню Продажи

	private JMenuBar createMenu() {
		// Создаем строку основного меню
		mainMenu = new JMenuBar();
		// Создаем подменю и пункты меню
		mSpr = new JMenu("Справочники");
		miExit = new JMenuItem("Выход");
		miProducts = new JMenuItem("Продукция");
		miMaterials = new JMenuItem("Материалы");
		miWorkshop = new JMenuItem("Цех");
		miCustomer = new JMenuItem("Заказчик");
		miOrderitems = new JMenuItem("Позиция накладной");
		miWarehouse = new JMenuItem("Склад");
		miDetailing = new JMenuItem("Детализация");
		miInventory = new JMenuItem("Инвентаризация");
		miSuppliers = new JMenuItem("Поставщики");
		miBook = new JMenuItem("Книга движений");
		miSpok = new JMenuItem("...");
		mOper = new JMenu("Операции");
		miProd = new JMenuItem("Заказ");
		// Формируем меню
		mSpr.add(miProducts);
		mSpr.add(miMaterials);
		mSpr.add(miWorkshop);
		mSpr.add(miCustomer);
		mSpr.add(miBook);
		mSpr.add(miOrderitems);
		mSpr.add(miWarehouse);
		mSpr.add(miDetailing);
		mSpr.add(miInventory);
		mSpr.add(miSuppliers);
		mSpr.addSeparator(); // линия-разделитель
		mSpr.add(miSpok);

		mOper.add(miProd);

		mainMenu.add(mSpr);
		mainMenu.add(mOper);
		mainMenu.add(miExit);
		return mainMenu;
	}

}
