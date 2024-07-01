package study2.utils;

import java.util.Objects;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

/**
 * 应用消息打印器
 * 
 * @author xzbd
 *
 */
public class AppPrinter {

	public static IConsoleManager getConsoleManager() {
		return ConsolePlugin.getDefault().getConsoleManager();
	}

	public static MessageConsole getConsole() {

		IConsoleManager consoleManager = getConsoleManager();
		IConsole[] consoles = consoleManager.getConsoles();
		if (Objects.isNull(consoles) || consoles.length < 1) {
			MessageConsole messageConsole = new MessageConsole("", null);
			consoleManager.addConsoles(new IConsole[] { messageConsole });
		}
		IConsole console = consoleManager.getConsoles()[0];
		return (MessageConsole) console;
	}

	/**
	 * 获取新的消息流
	 * 
	 * @return
	 */
	private static MessageConsoleStream newMessageStream() {
		return getConsole().newMessageStream();
	}

	/**
	 * 打印message并换行
	 * 
	 * @param message
	 */
	public static void println(String message) {
		newMessageStream().println(message);
	}

	/**
	 * 打印换行
	 */
	public static void println() {
		newMessageStream().println();
	}

	/**
	 * 只打印message不换行
	 * 
	 * @param message
	 */
	public static void print(String message) {
		newMessageStream().print(message);
	}

}
