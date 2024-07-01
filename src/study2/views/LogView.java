package study2.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class LogView extends ViewPart {
    private StyledText styledText;
    private PrintStream originalOut;
    private ServerSocket serverSocket;
    private Thread serverThread;

    private void startServer(int port) {
        serverThread = new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);
                System.out.println("[LOG] Listening for connections on port: " + port);

                while (!Thread.currentThread().isInterrupted()) {
                    Socket socket = serverSocket.accept();
                    System.out.println("[LOG] Client connected: " + socket.getInetAddress().getHostAddress());

                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        appendText(line + "\n");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (serverSocket != null && !serverSocket.isClosed()) {
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        serverThread.start();
    }
    
    
    
    @Override
    public void createPartControl(Composite parent) {
        styledText = new StyledText(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.READ_ONLY);
      //  redirectSystemOutput();
//(调试完成后打开)
        // 使用 System.out.println 输出文本
        // 启动监听服务器
        startServer(12348); // 在端口 12348 上启动服务器
        System.out.println("[INFO]SEMS已经正常启动！");
        System.out.println("[WARN]软件相关信息未配置");
        System.out.println("[ERR]软件运行在开发环境当中。");
        System.out.println("[LOG]软件正在正常运行。");
    }

    private void redirectSystemOutput() {
        originalOut = System.out;
        OutputStream out = new OutputStream() {
            private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            @Override
            public void write(int b) {
                buffer.write(b);
                if (b == '\n') {
                    String text = new String(buffer.toByteArray(), StandardCharsets.UTF_8);
                    buffer.reset();
                    appendText(text);
                }
            }
        };

        try {
			System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8.name()));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void appendText(String text) {
        if (!styledText.isDisposed()) {
            styledText.getDisplay().asyncExec(() -> {
                if (text.startsWith("[INFO]") || text.startsWith("[WARN]") || 
                    text.startsWith("[ERR]") || text.startsWith("[LOG]")|| text.startsWith("[PLUGIN]")) {
                    applyTextWithColor(text);
                }
            });
        }
    }

    private void applyTextWithColor(String text) {
        int start = styledText.getCharCount();
        styledText.append(text);
        int end = styledText.getCharCount();
        
        StyleRange styleRange = new StyleRange();
        styleRange.start = start;
        styleRange.length = end - start;
        styleRange.fontStyle = SWT.NORMAL;

        // Define colors for each type of log
        if (text.startsWith("[INFO]")) {
            styleRange.foreground = styledText.getDisplay().getSystemColor(SWT.COLOR_BLACK);
        } else if (text.startsWith("[WARN]")) {
            styleRange.foreground = new Color(styledText.getDisplay(), 255, 140, 0); // Yellow
        } else if (text.startsWith("[ERR]")) {
            styleRange.foreground = styledText.getDisplay().getSystemColor(SWT.COLOR_RED);
        } else if (text.startsWith("[LOG]")) {
            styleRange.foreground = styledText.getDisplay().getSystemColor(SWT.COLOR_BLUE);
        }else if (text.startsWith("[PLUGIN]")) {
            styleRange.foreground = styledText.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN);
        }

        styledText.setStyleRange(styleRange);
        styledText.setTopIndex(styledText.getLineCount() - 1); // Auto scroll to bottom
    }

    @Override
    public void dispose() {
        // 停止服务器线程
        if (serverThread != null && serverThread.isAlive()) {
            serverThread.interrupt();
        }
        
        // 关闭服务器套接字
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        System.setOut(originalOut); // 恢复标准输出
        super.dispose();
    }


    @Override
    public void setFocus() {
        styledText.setFocus();
    }
}
