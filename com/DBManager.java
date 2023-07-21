import data.workshop;
import data.materials;
import data.products;
import data.customer;
import data.book;
import data.order_items;
import data.warehouse;
import data.detailing;
import data.inventory_transactions;
import data.suppliers;
import data.sup;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DBManager {
	private Connection conn = null;

	public DBManager() throws SQLException {
	}

	public Connection getConnection() {
		return conn;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	// ����� ������������ ����������
	public String getVersion() {
		String ver = null;
		Statement statement;
		ResultSet rset;
		try {
			statement = conn.createStatement();
			rset = statement.executeQuery("SELECT VERSION()");
			while (rset.next()) {
				ver = rset.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ver;
	}


	public ArrayList<materials> loadMaterials() {
		ArrayList<materials> mat = new ArrayList<materials>();
		// Получение объекта соединения
		Connection con = this.getConnection();
		try {
			// Формирование запроса к БД
			Statement statement = con.createStatement();
			// Выполнение запроса и получение набора данных
			// ResultSet res = statement
			// .executeQuery("SELECT Tasks_id, name, description, unit_price FROM orders
			String sql = "SELECT material_id, name, supplier_id, price, quantity FROM public.materials";
			// order by name");
			ResultSet res = statement.executeQuery(sql);

			// Создание объекта – список продуктов
			mat = new ArrayList<materials>();
			// В цикле просмотра набора данных формируем список
			while (res.next()) {
				// Создаем объект продукции
				materials mate = new materials();

				// Заполняем поля объекта данными из строки набора
				mate.setMaterial_id(res.getBigDecimal(1));
				mate.setName(res.getString(2));
				mate.setSupplier_id(res.getBigDecimal(3));
				mate.setPrice(res.getBigDecimal(4));
				mate.setQuantity(res.getBigDecimal(5));
				// pd.setUnitPrice(res.getBigDecimal(4));
				// Создаем объект группы
				mat.add(mate);

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка получения данных", JOptionPane.ERROR_MESSAGE);
		}
		// Возврат списка
		return mat;
	}
	public ArrayList<products> loadProducts() {
		ArrayList<products> prod = new ArrayList<products>();
		// Получение объекта соединения
		Connection con = this.getConnection();
		try {
			// Формирование запроса к БД
			Statement statement = con.createStatement();
			// Выполнение запроса и получение набора данных
			// ResultSet res = statement
			// .executeQuery("SELECT Tasks_id, name, description, unit_price FROM orders
			String sql = "SELECT product_id, name, description, unit_price, material_id, workshop_id FROM public.products";
			// order by name");
			ResultSet res = statement.executeQuery(sql);

			// Создание объекта – список продуктов
			prod = new ArrayList<products>();
			// В цикле просмотра набора данных формируем список
			while (res.next()) {
				// Создаем объект продукции
				products prode = new products();

				// Заполняем поля объекта данными из строки набора
				prode.setProduct_id(res.getBigDecimal(1));
				prode.setName(res.getString(2));
				prode.setDescription(res.getString(3));
				prode.setUnit_price(res.getBigDecimal(4));
				prode.setMaterial_id(res.getBigDecimal(5));
				prode.setWorkshop_id(res.getBigDecimal(6));
				// pd.setUnitPrice(res.getBigDecimal(4));
				// Создаем объект группы
				prod.add(prode);

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка получения данных", JOptionPane.ERROR_MESSAGE);
		}
		// Возврат списка
		return prod;
	}

	public ArrayList<workshop> loadWorkshop() {
		ArrayList<workshop> work = new ArrayList<workshop>();
		// Получение объекта соединения
		Connection con = this.getConnection();
		try {
			// Формирование запроса к БД
			Statement statement = con.createStatement();
			// Выполнение запроса и получение набора данных
			// ResultSet res = statement
			// .executeQuery("SELECT Tasks_id, name, description, unit_price FROM orders
			String sql = "SELECT workshop_id, name, contacts FROM public.workshop";
			// order by name");
			ResultSet res = statement.executeQuery(sql);

			// Создание объекта – список продуктов
			work = new ArrayList<workshop>();
			// В цикле просмотра набора данных формируем список
			while (res.next()) {
				// Создаем объект продукции
				workshop worke = new workshop();

				// Заполняем поля объекта данными из строки набора
				worke.setWorkshop_id(res.getBigDecimal(1));
				worke.setName(res.getString(2));
				worke.setContacts(res.getString(3));
				// pd.setUnitPrice(res.getBigDecimal(4));
				// Создаем объект группы
				work.add(worke);

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка получения данных", JOptionPane.ERROR_MESSAGE);
		}
		// Возврат списка
		return work;
	}

	public ArrayList<customer> loadCustomer() {
		ArrayList<customer> cust = new ArrayList<customer>();
		// Получение объекта соединения
		Connection con = this.getConnection();
		try {
			// Формирование запроса к БД
			Statement statement = con.createStatement();
			// Выполнение запроса и получение набора данных
			// ResultSet res = statement
			// .executeQuery("SELECT Tasks_id, name, description, unit_price FROM orders
			String sql = "SELECT customer_id, name_mag, email, phone_number FROM public.customer";
			// order by name");
			ResultSet res = statement.executeQuery(sql);

			// Создание объекта – список продуктов
			cust = new ArrayList<customer>();
			// В цикле просмотра набора данных формируем список
			while (res.next()) {
				// Создаем объект продукции
				customer custe = new customer();

				// Заполняем поля объекта данными из строки набора
				custe.setCustomer_id(res.getBigDecimal(1));
				custe.setName_mag(res.getString(2));
				custe.setEmail(res.getString(3));
				custe.setPhone_number(res.getBigDecimal(4));
				// pd.setUnitPrice(res.getBigDecimal(4));
				// Создаем объект группы
				cust.add(custe);

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка получения данных", JOptionPane.ERROR_MESSAGE);
		}
		// Возврат списка
		return cust;
	}

	public ArrayList<book> loadBook() {
		ArrayList<book> bok = new ArrayList<book>();
		// Получение объекта соединения
		Connection con = this.getConnection();
		try {
			// Формирование запроса к БД
			Statement statement = con.createStatement();
			// Выполнение запроса и получение набора данных
			// ResultSet res = statement
			// .executeQuery("SELECT Tasks_id, name, description, unit_price FROM orders
			String sql = "SELECT order_id, order_date, num_doc, total_price, type_of_operation, customer_id FROM public.book";
			// order by name");
			ResultSet res = statement.executeQuery(sql);

			// Создание объекта – список продуктов
			bok = new ArrayList<book>();
			// В цикле просмотра набора данных формируем список
			while (res.next()) {
				// Создаем объект продукции
				book boke = new book();

				// Заполняем поля объекта данными из строки набора
				boke.setOrder_id(res.getBigDecimal(1));
				boke.setOrder_date(res.getDate(2));
				boke.setNum_doc(res.getBigDecimal(3));
				boke.setTotal_price(res.getBigDecimal(4));
				boke.setType_of_operation(res.getString(5));
				boke.setCustomer_id(res.getBigDecimal(6));
				// pd.setUnitPrice(res.getBigDecimal(4));
				// Создаем объект группы
				bok.add(boke);

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка получения данных", JOptionPane.ERROR_MESSAGE);
		}
		// Возврат списка
		return bok;
	}

	public ArrayList<order_items> loadOrder_items() {
		ArrayList<order_items> ori = new ArrayList<order_items>();
		// Получение объекта соединения
		Connection con = this.getConnection();
		try {
			// Формирование запроса к БД
			Statement statement = con.createStatement();
			// Выполнение запроса и получение набора данных
			// ResultSet res = statement
			// .executeQuery("SELECT Tasks_id, name, description, unit_price FROM orders
			String sql = "SELECT id_position, quantity, order_id, product_id FROM public.order_items";
			// order by name");
			ResultSet res = statement.executeQuery(sql);

			// Создание объекта – список продуктов
			ori = new ArrayList<order_items>();
			// В цикле просмотра набора данных формируем список
			while (res.next()) {
				// Создаем объект продукции
				order_items orie = new order_items();

				// Заполняем поля объекта данными из строки набора
				orie.setId_position(res.getBigDecimal(1));
				orie.setQuantity(res.getBigDecimal(2));
				orie.setOrder_id(res.getBigDecimal(3));
				orie.setProduct_id(res.getBigDecimal(4));
				// pd.setUnitPrice(res.getBigDecimal(4));
				// Создаем объект группы
				ori.add(orie);

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка получения данных", JOptionPane.ERROR_MESSAGE);
		}
		// Возврат списка
		return ori;
	}

	public ArrayList<warehouse> loadWarehouse() {
		ArrayList<warehouse> ware = new ArrayList<warehouse>();
		// Получение объекта соединения
		Connection con = this.getConnection();
		try {
			// Формирование запроса к БД
			Statement statement = con.createStatement();
			// Выполнение запроса и получение набора данных
			// ResultSet res = statement
			// .executeQuery("SELECT Tasks_id, name, description, unit_price FROM orders
			String sql = "SELECT warehouse_id, name, adress FROM public.warehouse";
			// order by name");
			ResultSet res = statement.executeQuery(sql);

			// Создание объекта – список продуктов
			ware = new ArrayList<warehouse>();
			// В цикле просмотра набора данных формируем список
			while (res.next()) {
				// Создаем объект продукции
				warehouse waree = new warehouse();

				// Заполняем поля объекта данными из строки набора
				waree.setWarehouse_id(res.getBigDecimal(1));
				waree.setName(res.getString(2));
				waree.setAdress(res.getString(3));
				// pd.setUnitPrice(res.getBigDecimal(4));
				// Создаем объект группы
				ware.add(waree);

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка получения данных", JOptionPane.ERROR_MESSAGE);
		}
		// Возврат списка
		return ware;
	}

	public ArrayList<detailing> loadDetailing() {
		ArrayList<detailing> det = new ArrayList<detailing>();
		// Получение объекта соединения
		Connection con = this.getConnection();
		try {
			// Формирование запроса к БД
			Statement statement = con.createStatement();
			// Выполнение запроса и получение набора данных
			// ResultSet res = statement
			// .executeQuery("SELECT Tasks_id, name, description, unit_price FROM orders
			String sql = "SELECT detailing, inventory_id, quantity, product_id FROM public.detailing";
			// order by name");
			ResultSet res = statement.executeQuery(sql);

			// Создание объекта – список продуктов
			det = new ArrayList<detailing>();
			// В цикле просмотра набора данных формируем список
			while (res.next()) {
				// Создаем объект продукции
				detailing dete = new detailing();

				// Заполняем поля объекта данными из строки набора
				dete.setDetailing(res.getBigDecimal(1));
				dete.setInventory_id(res.getBigDecimal(2));
				dete.setQuantity(res.getBigDecimal(3));
				dete.setProduct_id(res.getBigDecimal(4));
				// pd.setUnitPrice(res.getBigDecimal(4));
				// Создаем объект группы
				det.add(dete);

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка получения данных", JOptionPane.ERROR_MESSAGE);
		}
		// Возврат списка
		return det;
	}

	public ArrayList<inventory_transactions> loadInventory() {
		ArrayList<inventory_transactions> inv = new ArrayList<inventory_transactions>();
		// Получение объекта соединения
		Connection con = this.getConnection();
		try {
			// Формирование запроса к БД
			Statement statement = con.createStatement();
			// Выполнение запроса и получение набора данных
			// ResultSet res = statement
			// .executeQuery("SELECT Tasks_id, name, description, unit_price FROM orders
			String sql = "SELECT inventory_id, inventory_date, num_doc, warehouse_id FROM public.inventory_transactions";
			// order by name");
			ResultSet res = statement.executeQuery(sql);

			// Создание объекта – список продуктов
			inv = new ArrayList<inventory_transactions>();
			// В цикле просмотра набора данных формируем список
			while (res.next()) {
				// Создаем объект продукции
				inventory_transactions inve = new inventory_transactions();

				// Заполняем поля объекта данными из строки набора
				inve.setInventory_id(res.getBigDecimal(1));
				inve.setInventory_date(res.getDate(2));
				inve.setNum_doc(res.getBigDecimal(3));
				inve.setWarehouse_id(res.getBigDecimal(4));

				// pd.setUnitPrice(res.getBigDecimal(4));
				// Создаем объект группы
				inv.add(inve);

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка получения данных", JOptionPane.ERROR_MESSAGE);
		}
		// Возврат списка
		return inv;
	}

	public ArrayList<suppliers> loadSuppliers() {
		ArrayList<suppliers> sup = new ArrayList<suppliers>();
		// Получение объекта соединения
		Connection con = this.getConnection();
		try {
			// Формирование запроса к БД
			Statement statement = con.createStatement();
			// Выполнение запроса и получение набора данных
			// ResultSet res = statement
			// .executeQuery("SELECT Tasks_id, name, description, unit_price FROM orders
			String sql = "SELECT supplier_id, supplier_name, phone, adress FROM public.suppliers";
			// order by name");
			ResultSet res = statement.executeQuery(sql);

			// Создание объекта – список продуктов
			sup = new ArrayList<suppliers>();
			// В цикле просмотра набора данных формируем список
			while (res.next()) {
				// Создаем объект продукции
				suppliers inve = new suppliers();

				// Заполняем поля объекта данными из строки набора
				inve.setSupplier_id(res.getBigDecimal(1));
				inve.setSupplier_name(res.getString(2));
				inve.setPhone(res.getBigDecimal(3));
				inve.setAdress(res.getString(4));

				// pd.setUnitPrice(res.getBigDecimal(4));
				// Создаем объект группы
				sup.add(inve);

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка получения данных", JOptionPane.ERROR_MESSAGE);
		}
		// Возврат списка
		return sup;
	}

	// Установка путей к схемам
	public void setPath() {
		Statement statement = null;
		try {
			statement = conn.createStatement();
			// для PostgreSQL
			statement.executeUpdate("SET SEARCH_PATH=postgres");

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Откат изменений при ошибках выполнения операторов
	private void RollBack() {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Установка путей к схемам
		setPath();
	}

	// Выполнение оператора редактирования строки
	// Параметры:
	// prod – объект «Продукция» из формы редактирования
	// key – значение ключевого реквизита
	public boolean updateMaterials(materials mat, BigDecimal old_key) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "UPDATE public.materials set "
				+ "name=?,"
				+ "supplier_id=?,"
				+ "price=?,"
				+ "quantity=?"
				+ " WHERE material_id=?";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			// 1- номер параметра
			// prod.getKod() – значение параметра, «код продукта»
			// полученный из переданного объекта prod
			// методом getKod()
			pst.setString(1, mat.getName());
			pst.setBigDecimal(2, mat.getSupplier_id());
			pst.setBigDecimal(3, mat.getPrice());
			pst.setBigDecimal(4, mat.getQuantity());

			pst.setBigDecimal(5, mat.getMaterial_id());
			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
		} catch (SQLException ex) {
			// В случае ошибки – отмена транзакции
			RollBack();
			// и вывод сообщения об ошибке
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Ошибка изменения данных", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	public boolean updateProducts(products prod, BigDecimal old_key) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "UPDATE public.products set "
				+ "name=?,"
				+ "description=?,"
				+ "unit_price=?,"
				+ "material_id=?,"
				+ "workshop_id=?"
				+ " WHERE product_id=?";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			// 1- номер параметра
			// prod.getKod() – значение параметра, «код продукта»
			// полученный из переданного объекта prod
			// методом getKod()
			pst.setString(1, prod.getName());
			pst.setString(2, prod.getDescription());
			pst.setBigDecimal(3, prod.getUnit_price());
			pst.setBigDecimal(4, prod.getMaterial_id());
			pst.setBigDecimal(5, prod.getWorkshop_id());

			pst.setBigDecimal(6, prod.getProduct_id());
			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
		} catch (SQLException ex) {
			// В случае ошибки – отмена транзакции
			RollBack();
			// и вывод сообщения об ошибке
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Ошибка изменения данных", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public boolean updateWorkshop(workshop work, BigDecimal old_key) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "UPDATE public.workshop set "
				+ "name=?,"
				+ "contacts=?"
				+ " WHERE workshop_id=?";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			// 1- номер параметра
			// prod.getKod() – значение параметра, «код продукта»
			// полученный из переданного объекта prod
			// методом getKod()
			pst.setString(1, work.getName());
			pst.setString(2, work.getContacts());

			pst.setBigDecimal(3, work.getWorkshop_id());
			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
		} catch (SQLException ex) {
			// В случае ошибки – отмена транзакции
			RollBack();
			// и вывод сообщения об ошибке
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Ошибка изменения данных", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public boolean updateCustomer(customer cust, BigDecimal old_key) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "UPDATE public.customer set "
				+ "name_mag=?,"
				+ "email=?,"
				+ "phone_number=?"
				+ " WHERE customer_id=?";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			// 1- номер параметра
			// prod.getKod() – значение параметра, «код продукта»
			// полученный из переданного объекта prod
			// методом getKod()
			pst.setString(1, cust.getName_mag());
			pst.setString(2, cust.getEmail());
			pst.setBigDecimal(3, cust.getPhone_number());

			pst.setBigDecimal(4, cust.getCustomer_id());
			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
		} catch (SQLException ex) {
			// В случае ошибки – отмена транзакции
			RollBack();
			// и вывод сообщения об ошибке
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Ошибка изменения данных", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public boolean updateBook(book bok, BigDecimal old_key) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "UPDATE public.book set "
				+ "order_date=?,"
				+ "num_doc=?,"
				+ "total_price=?,"
				+ "type_of_operation=?,"
				+ "customer_id=?"
				+ " WHERE order_id=?";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			// 1- номер параметра
			// prod.getKod() – значение параметра, «код продукта»
			// полученный из переданного объекта prod
			// методом getKod()
			pst.setDate(1, bok.getOrder_date());
			pst.setBigDecimal(2, bok.getNum_doc());
			pst.setBigDecimal(3, bok.getTotal_price());
			pst.setString(4, bok.getType_of_operation());
			pst.setBigDecimal(5, bok.getCustomer_id());

			pst.setBigDecimal(6, bok.getOrder_id());
			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
		} catch (SQLException ex) {
			// В случае ошибки – отмена транзакции
			RollBack();
			// и вывод сообщения об ошибке
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Ошибка изменения данных", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public boolean updateOrder_items(order_items ori, BigDecimal old_key) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "UPDATE public.order_items set "
				+ "quantity=?,"
				+ "order_id=?,"
				+ "product_id=?"
				+ " WHERE id_position=?";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			// 1- номер параметра
			// prod.getKod() – значение параметра, «код продукта»
			// полученный из переданного объекта prod
			// методом getKod()
			pst.setBigDecimal(1, ori.getQuantity());
			pst.setBigDecimal(2, ori.getOrder_id());
			pst.setBigDecimal(3, ori.getProduct_id());

			pst.setBigDecimal(4, ori.getId_position());
			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
		} catch (SQLException ex) {
			// В случае ошибки – отмена транзакции
			RollBack();
			// и вывод сообщения об ошибке
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Ошибка изменения данных", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public boolean updateWarehouse(warehouse ware, BigDecimal old_key) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "UPDATE public.warehouse set "
				+ "name=?,"
				+ "adress=?"
				+ " WHERE warehouse_id=?";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			// 1- номер параметра
			// prod.getKod() – значение параметра, «код продукта»
			// полученный из переданного объекта prod
			// методом getKod()
			pst.setString(1, ware.getName());
			pst.setString(2, ware.getAdress());

			pst.setBigDecimal(3, ware.getWarehouse_id());
			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
		} catch (SQLException ex) {
			// В случае ошибки – отмена транзакции
			RollBack();
			// и вывод сообщения об ошибке
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Ошибка изменения данных", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public boolean updateDetailing(detailing det, BigDecimal old_key) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "UPDATE public.detailing set "
				+ "inventory_id=?,"
				+ "quantity=?,"
				+ "product_id=?"
				+ " WHERE detailing=?";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			// 1- номер параметра
			// prod.getKod() – значение параметра, «код продукта»
			// полученный из переданного объекта prod
			// методом getKod()
			pst.setBigDecimal(1, det.getInventory_id());
			pst.setBigDecimal(2, det.getQuantity());
			pst.setBigDecimal(3, det.getProduct_id());

			pst.setBigDecimal(4, det.getDetailing());
			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
		} catch (SQLException ex) {
			// В случае ошибки – отмена транзакции
			RollBack();
			// и вывод сообщения об ошибке
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Ошибка изменения данных", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public boolean updateInventory(inventory_transactions inv, BigDecimal old_key) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "UPDATE public.inventory_transactions set "
				+ "inventory_date=?,"
				+ "num_doc=?,"
				+ "warehouse_id=?"
				+ " WHERE inventory_id=?";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			// 1- номер параметра
			// prod.getKod() – значение параметра, «код продукта»
			// полученный из переданного объекта prod
			// методом getKod()
			pst.setDate(1, inv.getInventory_date());
			pst.setBigDecimal(2, inv.getNum_doc());
			pst.setBigDecimal(3, inv.getWarehouse_id());

			pst.setBigDecimal(4, inv.getInventory_id());
			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
		} catch (SQLException ex) {
			// В случае ошибки – отмена транзакции
			RollBack();
			// и вывод сообщения об ошибке
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Ошибка изменения данных", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public boolean updateSuppliers(suppliers sup, BigDecimal old_key) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "UPDATE public.suppliers set "
				+ "supplier_name=?,"
				+ "phone=?,"
				+ "adress=?"
				+ " WHERE supplier_id=?";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			// 1- номер параметра
			// prod.getKod() – значение параметра, «код продукта»
			// полученный из переданного объекта prod
			// методом getKod()
			pst.setString(1, sup.getSupplier_name());
			pst.setBigDecimal(2, sup.getPhone());
			pst.setString(3, sup.getAdress());

			pst.setBigDecimal(4, sup.getSupplier_id());
			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
		} catch (SQLException ex) {
			// В случае ошибки – отмена транзакции
			RollBack();
			// и вывод сообщения об ошибке
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Ошибка изменения данных", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public boolean addMaterials(materials mat) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "INSERT INTO public.materials(material_id," +
				"name,supplier_id,price,quantity)" + "VALUES(?,?,?,?,?)";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			pst.setBigDecimal(1, mat.getMaterial_id());
			pst.setString(2, mat.getName());
			pst.setBigDecimal(3, mat.getSupplier_id());
			pst.setBigDecimal(4, mat.getPrice());
			pst.setBigDecimal(5, mat.getQuantity());

			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
			return true;
		} catch (SQLException ex) {
			// В случае ошибки – отмена транзакции
			RollBack();
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Ошибка добавления данных", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	public boolean addProducts(products prod) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "INSERT INTO public.products(product_id," +
				"name,description,unit_price,material_id,workshop_id)" + "VALUES(?,?,?,?,?,?)";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			pst.setBigDecimal(1, prod.getProduct_id());
			pst.setString(2, prod.getName());
			pst.setString(3, prod.getDescription());
			pst.setBigDecimal(4, prod.getUnit_price());
			pst.setBigDecimal(5, prod.getMaterial_id());
			pst.setBigDecimal(6, prod.getWorkshop_id());

			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
			return true;
		} catch (SQLException ex) {
			// В случае ошибки – отмена транзакции
			RollBack();
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Ошибка добавления данных", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	public boolean addWorkshop(workshop work) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "INSERT INTO public.workshop(workshop_id," +
				"name,contacts)" + "VALUES(?,?,?)";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			pst.setBigDecimal(1, work.getWorkshop_id());
			pst.setString(2, work.getName());
			pst.setString(3, work.getContacts());

			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
			return true;
		} catch (SQLException ex) {
			// В случае ошибки – отмена транзакции
			RollBack();
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Ошибка добавления данных", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	public boolean addCustomer(customer cust) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "INSERT INTO public.customer(customer_id," +
				"name_mag,email,phone_number)" + "VALUES(?,?,?,?)";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			pst.setBigDecimal(1, cust.getCustomer_id());
			pst.setString(2, cust.getName_mag());
			pst.setString(3, cust.getEmail());
			pst.setBigDecimal(4, cust.getPhone_number());

			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
			return true;
		} catch (SQLException ex) {
			// В случае ошибки – отмена транзакции
			RollBack();
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Ошибка добавления данных", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	public boolean addBook(book bok) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "INSERT INTO public.book(order_id," +
				"order_date, num_doc, total_price, type_of_operation,customer_id)" + "VALUES(?,?,?,?,?,?)";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			pst.setBigDecimal(1, bok.getOrder_id());
			pst.setDate(2, bok.getOrder_date());
			pst.setBigDecimal(3, bok.getNum_doc());
			pst.setBigDecimal(4, bok.getTotal_price());
			pst.setString(5, bok.getType_of_operation());
			pst.setBigDecimal(6, bok.getCustomer_id());

			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
			return true;
		} catch (SQLException ex) {
			// В случае ошибки – отмена транзакции
			RollBack();
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Ошибка добавления данных", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	public boolean addOrder_items(order_items ori) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "INSERT INTO public.order_items(id_position," +
				"quantity, order_id, product_id)" + "VALUES(?,?,?,?)";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			pst.setBigDecimal(1, ori.getId_position());
			pst.setBigDecimal(2, ori.getQuantity());
			pst.setBigDecimal(3, ori.getOrder_id());
			pst.setBigDecimal(4, ori.getProduct_id());

			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
			return true;
		} catch (SQLException ex) {
			// В случае ошибки – отмена транзакции
			RollBack();
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Ошибка добавления данных", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	public boolean addWarehouse(warehouse ware) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "INSERT INTO public.warehouse(warehouse_id," +
				"name,adress)" + "VALUES(?,?,?)";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			pst.setBigDecimal(1, ware.getWarehouse_id());
			pst.setString(2, ware.getName());
			pst.setString(3, ware.getAdress());

			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
			return true;
		} catch (SQLException ex) {
			// В случае ошибки – отмена транзакции
			RollBack();
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Ошибка добавления данных", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	public boolean addDetailing(detailing det) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "INSERT INTO public.detailing(detailing," +
				"inventory_id,quantity,product_id)" + "VALUES(?,?,?,?)";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			pst.setBigDecimal(1, det.getDetailing());
			pst.setBigDecimal(2, det.getInventory_id());
			pst.setBigDecimal(3, det.getQuantity());
			pst.setBigDecimal(4, det.getProduct_id());

			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
			return true;
		} catch (SQLException ex) {
			// В случае ошибки – отмена транзакции
			RollBack();
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Ошибка добавления данных", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	public boolean addInventory(inventory_transactions inv) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "INSERT INTO public.inventory_transactions(inventory_id," +
				"inventory_date, num_doc, warehouse_id)" + "VALUES(?,?,?,?)";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			pst.setBigDecimal(1, inv.getInventory_id());
			pst.setDate(2, inv.getInventory_date());
			pst.setBigDecimal(3, inv.getNum_doc());
			pst.setBigDecimal(4, inv.getWarehouse_id());


			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
			return true;
		} catch (SQLException ex) {
			// В случае ошибки – отмена транзакции
			RollBack();
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Ошибка добавления данных", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	public boolean addSuppliers(suppliers sup) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "INSERT INTO public.suppliers(supplier_id," +
				"supplier_name, phone, adress)" + "VALUES(?,?,?,?)";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			pst.setBigDecimal(1, sup.getSupplier_id());
			pst.setString(2, sup.getSupplier_name());
			pst.setBigDecimal(3, sup.getPhone());
			pst.setString(4, sup.getAdress());


			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
			return true;
		} catch (SQLException ex) {
			// В случае ошибки – отмена транзакции
			RollBack();
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Ошибка добавления данных", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}


	public boolean deleteMaterials(BigDecimal kod) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "DELETE FROM public.materials" + " WHERE material_id=?";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			pst.setBigDecimal(1, kod);
			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	public boolean deleteProducts(BigDecimal kod) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "DELETE FROM public.products" + " WHERE product_id=?";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			pst.setBigDecimal(1, kod);
			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	public boolean deleteWorkshop(BigDecimal kod) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "DELETE FROM public.workshop" + " WHERE workshop_id=?";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			pst.setBigDecimal(1, kod);
			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	public boolean deleteCustomer(BigDecimal kod) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "DELETE FROM public.customer" + " WHERE customer_id=?";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			pst.setBigDecimal(1, kod);
			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	public boolean deleteBook(BigDecimal kod) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "DELETE FROM public.book" + " WHERE order_id=?";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			pst.setBigDecimal(1, kod);
			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	public boolean deleteOrder_items(BigDecimal kod) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "DELETE FROM public.order_items" + " WHERE id_position=?";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			pst.setBigDecimal(1, kod);
			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	public boolean deleteWarehouse(BigDecimal kod) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "DELETE FROM public.warehouse" + " WHERE warehouse_id=?";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			pst.setBigDecimal(1, kod);
			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	public boolean deleteDetailing(BigDecimal kod) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "DELETE FROM public.detailing" + " WHERE detailing=?";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			pst.setBigDecimal(1, kod);
			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	public boolean deleteInventory(BigDecimal kod) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "DELETE FROM public.inventory_transactions" + " WHERE inventory_id=?";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			pst.setBigDecimal(1, kod);
			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	public boolean deleteSuppliers(BigDecimal kod) {
		PreparedStatement pst = null;
		// Получение объекта соединения
		Connection con = this.getConnection();
		// Строка с текстом оператора (? – параметры)
		String stm = "DELETE FROM public.inventory_suppliers" + " WHERE supplier_id=?";
		try {
			// Создание объекта «Оператор с параметрами»
			pst = con.prepareStatement(stm);
			// Задаем значения параметров оператора
			pst.setBigDecimal(1, kod);
			// Выполнение оператора
			pst.executeUpdate();
			// Завершение транзакции – сохранение изменений
			con.commit();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
}
