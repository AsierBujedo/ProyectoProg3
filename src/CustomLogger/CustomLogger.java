package CustomLogger;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Clase CustomLogger.
 * Logger diseñado para la implementación del chat en el proyecto, puede usarse para otras cosas.
 * @author GR08
 */

public class CustomLogger {
	static PrintStream ps;
	static File f;

	/**
	 * Método create. Debe llamarse siempre al inicio del programa para generar el
	 * archivo (log).
	 * 
	 * @param name
	 * @return {@link Boolean}
	 */
	public boolean create(String name) {
		f = new File(name);
		try {
			ps = new PrintStream(f);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			ps.println("[BEGIN] CUSTOMLOGGER | FILE: " + name + " " + dtf.format(LocalDateTime.now()));
			return true;
		} catch (IOException e) {
			ps.println(e.toString());
			return false;
		}
	}

	/**
	 * Método close. Debe llamarse siempre al final del programa para cerrar el
	 * archivo (log) una vez cerrado, no se podrá modificar.
	 * 
	 * @return {@link Boolean}
	 */
	public boolean close() {
		ps.print("[END] END OF FILE");
		ps.close();
		return true;
	}

	/**
	 * Método log(), debe ser llamado a la hora de querer loguear algo, antes debe
	 * usarse el método create() al inicio del programa una sola vez. La gravedad
	 * está definida por el enum "LEVEL"
	 * 
	 * @param lvl {@link LEVEL}
	 * @param str {@link String}
	 * @return {@link Boolean}
	 */
	public boolean log(LEVEL lvl, String str) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		if (lvl == LEVEL.BEGIN) {
			ps.println("[" + dtf.format(LocalDateTime.now()) + "]" + "[BEGIN]  " + str);
			System.err.println("[" + dtf.format(LocalDateTime.now()) + "]" + "[BEGIN]  " + str);
		} else if (lvl == LEVEL.DONE) {
			ps.println("[" + dtf.format(LocalDateTime.now()) + "]" + "[DONE]  " + str);
			System.err.println("[" + dtf.format(LocalDateTime.now()) + "]" + "[DONE]  " + str);
		} else if (lvl == LEVEL.END) {
			ps.println("[" + dtf.format(LocalDateTime.now()) + "]" + "[END]  " + str);
			System.err.println("[" + dtf.format(LocalDateTime.now()) + "]" + "[END]  " + str);
		} else if (lvl == LEVEL.ERROR) {
			ps.println("[" + dtf.format(LocalDateTime.now()) + "]" + "[ERROR]  " + str);
			System.err.println("[" + dtf.format(LocalDateTime.now()) + "]" + "[ERROR]  " + str);
		} else if (lvl == LEVEL.INFO) {
			ps.println("[" + dtf.format(LocalDateTime.now()) + "]" + "[INFO]  " + str);
			System.err.println("[" + dtf.format(LocalDateTime.now()) + "]" + "[INFO]  " + str);
		} else if (lvl == LEVEL.OK) {
			ps.println("[" + dtf.format(LocalDateTime.now()) + "]" + "[OK]  " + str);
			System.err.println("[" + dtf.format(LocalDateTime.now()) + "]" + "[OK]  " + str);
		} else if (lvl == LEVEL.WARN) {
			ps.println("[" + dtf.format(LocalDateTime.now()) + "]" + "[WARN]  " + str);
			System.err.println("[" + dtf.format(LocalDateTime.now()) + "]" + "[WARN]  " + str);
		} else {
			ps.println("[" + dtf.format(LocalDateTime.now()) + "]" + "[OTHER]  " + str);
			System.err.println("[" + dtf.format(LocalDateTime.now()) + "]" + "[OTHER]  " + str);
		}
		return true;
	}

	/** Método readlog(), puede ser llamado en cualquier momento, lee el archivo log
	 * y lo imprime por pantalla
	 * @return {@link Boolean}
	 */
	public boolean readlog() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String ln = reader.readLine();
			while (ln != null) {
				System.out.println(ln);
				ln = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("CANNOT LOCATE THE FILE");
			ps.println(e.toString());
		}
		return true;
	}

}
