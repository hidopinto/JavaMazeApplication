package model;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * an interface that represents a generic client handler
 * @author Yehuda Hido Pinto & Adir Ben Avi
 *
 */
public interface ClientHandler {
	public void handleClient(InputStream in , OutputStream out);
}
