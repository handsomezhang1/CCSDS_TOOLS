package study2.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;

public class RefreshDatabaseAction extends Action {

    private IWorkbenchWindow window;

    public RefreshDatabaseAction(IWorkbenchWindow window) {
        this.window = window;
        this.setId("study2.actions.RefreshDatabaseAction");
        this.setText("一键刷新数据库(&R)");
        this.setToolTipText("刷新数据库视图");
        this.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_REDO));
    }

    @Override
    public void run() {
        ICommandService commandService = window.getService(ICommandService.class);
        IHandlerService handlerService = window.getService(IHandlerService.class);
        try {
            // 触发一个命令，这个命令应该被数据库视图的handler监听和处理
            handlerService.executeCommand("study2.commands.refreshDatabase", null);
        } catch (Exception e) {
          //  throw new RuntimeException("刷新数据库命令执行失败", e);
        	  e.printStackTrace();
        }
    }
}
