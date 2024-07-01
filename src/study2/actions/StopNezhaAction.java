package study2.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class StopNezhaAction extends Action {

    private final IWorkbenchWindow window;

    public StopNezhaAction(IWorkbenchWindow window) {
        this.window = window;
        setText("一键关闭哪吒架构");
        setId("study2.action.StopNezhaAction"); // 设置一个唯一的 ID
        setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_STOP));
        // 可以设置 Action 的其他属性，如图标等
        // setImageDescriptor(...);
    }

    @Override
    public void run() {
        // 在这里实现关闭哪吒架构的逻辑
        // 例如，显示一个对话框提示用户哪吒架构已关闭
        MessageDialog.openInformation(
            window.getShell(),
            "哪吒架构",
            "哪吒架构已关闭");
    }
}
