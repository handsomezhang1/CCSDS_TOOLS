package study2.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import study2.Activator;
import study2.utils.PreferenceConstants;
import study2.utils.SocketUtil;
import study2.views.NezhaArchitectureConsoleView;

public class StartInternet2 extends Action {

    private final IWorkbenchWindow window;

    public StartInternet2(IWorkbenchWindow window) {
        this.window = window;
        setText("检查/更新架构以太网链路…");
        setAccelerator(SWT.CTRL | 'E');
        setId("study2.actions.StartNezhaAction"); // 设置唯一的 ID
        setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED));
        
		
        // 设置 Action 的其他属性，如图标等
        // setImageDescriptor(...);
    }

    @Override
    public void run() {
    	 IPreferenceStore store = Activator.getDefault().getPreferenceStore();
        String ipAddress = store.getString(PreferenceConstants.P_IP_ADDRESS);
        int port = store.getInt(PreferenceConstants.P_PORT); // 假设您也想获取端口号
        int result = SocketUtil.checkConnectivity(ipAddress, port);

       
        Display.getDefault().asyncExec(() -> {
            
            IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            if (window != null) {
               
                IWorkbenchPage page = window.getActivePage();
                NezhaArchitectureConsoleView view = (NezhaArchitectureConsoleView) page.findView("study2.views.NezhaArchitectureConsoleView");
                if (view != null) {
                    
                    view.updateStatusIndicator(0, result);
                   
                    if (result == 1) {
                       
                        MessageDialog.openInformation(
                                window.getShell(),
                                "以太网连接",
                                "以太网已连接");
                    } else {
                    	
                        MessageDialog.openError(
                                window.getShell(),
                                "以太网连接",
                                "以太网连接失败！");
                        
                    }
                } else {
                   
                }
            }
        });
    }


}
