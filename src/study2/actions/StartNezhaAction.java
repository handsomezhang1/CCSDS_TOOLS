package study2.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import study2.Activator;
import study2.utils.PreferenceConstants;
import study2.utils.SocketUtil;
import study2.views.NezhaArchitectureConsoleView;

public class StartNezhaAction extends Action {

    private final IWorkbenchWindow window;

    public StartNezhaAction(IWorkbenchWindow window) {
        this.window = window;
        setText("一键启动哪吒架构");
        setId("study2.actions.StartNezhaAction"); // 设置唯一的 ID
        setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_FORWARD));
        
        // 设置 Action 的其他属性，如图标等
        // setImageDescriptor(...);
    }

    @Override
    public void run() {
    	 IPreferenceStore store = Activator.getDefault().getPreferenceStore();
    	 // 获取实际保存的偏好值
        String ipAddress = store.getString(PreferenceConstants.P_IP_ADDRESS);
        int port = store.getInt(PreferenceConstants.P_PORT); // 假设您也想获取端口号
        int result = SocketUtil.checkConnectivity(ipAddress, port);
        
        System.out.println("[LOG]NezhaArchiw found!——"+PreferenceConstants.P_IP_ADDRESS);
        Display.getDefault().asyncExec(() -> {
            System.out.println("[LOG]NezhaAr11111chiw not found!");
            IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            if (window != null) {
                System.out.println("[LOG]NezhaArch2222iw not found!");
                IWorkbenchPage page = window.getActivePage();
                NezhaArchitectureConsoleView view = (NezhaArchitectureConsoleView) page.findView("study2.views.NezhaArchitectureConsoleView");
                if (view != null) {
                    System.out.println("[LOG]NezhaArc333333hiw not found!");
                    view.updateStatusIndicator(0, result);
                    System.out.println("[LOG]NezhaArc33888888888888883333hiw not found!");
                    if (result == 1) {
                        System.out.println("[LOG]NezhaArc344444w not found!");
                        MessageDialog.openInformation(
                                window.getShell(),
                                "哪吒架构",
                                "哪吒架构已启动");
                    } else {
                    	 System.out.println("[LOG]NezhaArc3466666w not found!");
                        MessageDialog.openError(
                                window.getShell(),
                                "哪吒架构",
                                "哪吒架构启动失败！");
                        
                    }
                } else {
                    System.out.println("NezhaArchitectureConsoleView not found!");
                }
            }
        });
    }


}
