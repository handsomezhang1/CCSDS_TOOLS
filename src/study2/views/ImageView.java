package study2.views;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class ImageView extends ViewPart {
    private Label imageLabel;

    public ImageView() {
    }

    @Override
    public void createPartControl(Composite parent) {
        // 创建一个Label用于显示图像
        imageLabel = new Label(parent, SWT.NONE);

        // 启动一个线程以持续监听端口
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(12346)) {
                while (!Thread.currentThread().isInterrupted()) {
                    try (Socket clientSocket = serverSocket.accept(); // 等待并接受新连接
                         InputStream inputStream = clientSocket.getInputStream()) { // 获取输入流

                        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                        int nRead;
                        byte[] data = new byte[1024];

                        // 读取输入流中的所有数据到buffer
                        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                            buffer.write(data, 0, nRead);
                        }

                        buffer.flush();
                        byte[] imageData = buffer.toByteArray();

                        // 在UI线程中更新图像
                        Display.getDefault().asyncExec(() -> {
                            try (ByteArrayInputStream imageStream = new ByteArrayInputStream(imageData)) {
                                Image image = new Image(Display.getDefault(), imageStream);
                                // 释放旧图像资源
                                if (imageLabel.getImage() != null && !imageLabel.getImage().isDisposed()) {
                                    imageLabel.getImage().dispose();
                                }
                                // 设置新图像
                                imageLabel.setImage(image);
                                imageLabel.getParent().layout(); // 重新布局以显示新图像
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void setFocus() {
        if (imageLabel != null && !imageLabel.isDisposed()) {
            imageLabel.setFocus();
        }
    }
}
