package CustomLogger;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/* CustomLogger
 * UNIVERSIDAD DE DEUSTO
 */

public class CustomLogger {
	static PrintStream ps;
	static File f;

	/*Método create(), debe llamarse siempre al inicio
	 * del programa para generar el archivo (log)
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
	/*Método close(), debe llamarse siempre al final
	 * del programa para cerrar el archivo (log)
	 * una vez cerrado, no se podrá modificar.
	 */
	public boolean close() {
		ps.print("[END] END OF FILE");
		ps.close();
		return true;
	}

	/* Método log(), debe ser llamado a la hora de querer loguear algo,
	 * antes debe usarse el método create() al inicio del programa una sola vez.
	 * La gravedad está definida por el enum "LEVEL"
	 */
	public boolean log(LEVEL lvl, String str) {
		if (lvl == LEVEL.BEGIN) {
			ps.println("[BEGIN]  " + str);
		} else if (lvl == LEVEL.DONE) {
			ps.println("[DONE]  " + str);
		} else if (lvl == LEVEL.END) {
			ps.println("[END]  " + str);
		} else if (lvl == LEVEL.ERROR) {
			ps.println("[ERROR]  " + str);
		} else if (lvl == LEVEL.INFO) {
			ps.println("[INFO]  " + str);
		} else if (lvl == LEVEL.OK) {
			ps.println("[OK]  " + str);
		} else if (lvl == LEVEL.WARN) {
			ps.println("[WARN]  " + str);
		} else {
			ps.println("[OTHER]  " + str);
		}
		return true;
	}

	/*Método readlog(), puede ser llamado en cualquier momento,
	 *lee el archivo log y lo imprime por pantalla
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
