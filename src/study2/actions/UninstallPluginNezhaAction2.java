package study2.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import study2.utils.VariableUtil;

public class UninstallPluginNezhaAction2 extends Action {

    private IWorkbenchWindow window;

    public UninstallPluginNezhaAction2(IWorkbenchWindow window) {
        this.window = window;
        setText("卸载插件");
        setId("study2.action.UninstallPluginNezhaAction2");

	    // 设置动作的图标
	    setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ETOOL_DELETE));
    }

    @Override
    public void run() {
        // 实现打开配置窗口的逻辑
    	VariableUtil.b.setString(null);
        MessageDialog.openInformation(
                window.getShell(),
                "插件",
                "插件卸载成功！");
    }
}
