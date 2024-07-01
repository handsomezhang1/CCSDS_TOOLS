package study2.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class ConnectDatabaseAction extends Action {

    private IWorkbenchWindow window;

    public ConnectDatabaseAction(IWorkbenchWindow window) {
        this.window = window;
        setText("一键连接数据库");
        setId("study2.ConnectDatabaseAction");
        setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEF_VIEW));
        
    }

    @Override
    public void run() {
        // 这里可以添加实际连接数据库的逻辑
        // 目前只显示一个消息对话框
        MessageDialog.openInformation(window.getShell(), "数据库连接", "一键连接成功。");
    }
}
