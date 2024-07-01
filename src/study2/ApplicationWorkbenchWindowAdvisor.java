package study2;

import java.awt.Image;
import java.awt.Menu;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.NewWizardAction;
import org.eclipse.ui.actions.SimpleWildcardTester;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	private TrayItem trayItem;
	private org.eclipse.swt.graphics.Image trayImage;
	private IWorkbenchWindow window;
	public ApplicationActionBarAdvisor actionBarAdvisor;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}
	
	@Override
	public ActionBarAdvisor createActionBarAdvisor(
			IActionBarConfigurer configurer) {
		actionBarAdvisor=new ApplicationActionBarAdvisor(configurer);
		return actionBarAdvisor;
	}

	@Override
	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(1200, 800));
		configurer.setShowCoolBar(true);
		configurer.setShowStatusLine(true);
		configurer.setShowStatusLine(true);
		configurer.setTitle("航天器电子数据单管理系统-Spacecraft EDS Management System-NSSC");
		System.out.println("357 7");
		configurer.setShowPerspectiveBar(true);
		// Set the preference toolbar
		// If other menus exists then this will be on the left of them to the left place
		IPreferenceStore apiStore = PlatformUI.getPreferenceStore();
		apiStore.setValue(IWorkbenchPreferenceConstants.DOCK_PERSPECTIVE_BAR,
		"TOP_LEFT");
	}
	public void postWindowCreate() {
	    super.postWindowCreate();
	    //设置打开时最大化窗口
	    getWindowConfigurer().getWindow().getShell().setMaximized(true);
	}
	@Override 
	public void postWindowOpen() {
		super.postWindowOpen();
		window=getWindowConfigurer().getWindow();
		trayItem=initTaskItem(window);
		if(trayItem!=null) {
			createMinimize();
			hookPopupMenu(window);
		}
		
	}
	
	private void hookPopupMenu(IWorkbenchWindow window) {
		trayItem.addListener(SWT.MenuDetect, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				MenuManager trayMenu=new MenuManager();
				org.eclipse.swt.widgets.Menu menu=trayMenu.createContextMenu(window.getShell());
				actionBarAdvisor.fillTrayItem(trayMenu);
				menu.setVisible(true);
			}
		});
	}
	private void createMinimize() {
		window.getShell().addShellListener(new ShellAdapter() {
			
			public void shelliconified(ShellEvent e) {
				window.getShell().setVisible(false);
			}
		});
		trayItem.addListener(SWT.DefaultSelection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				Shell shell =window.getShell();
				if(!shell.isVisible()) {
					shell.setVisible(true);
					window.getShell().setMinimized(false);
				}
			}
		});
	}
	private TrayItem initTaskItem(IWorkbenchWindow window) {
		final Tray tray = window.getShell().getDisplay().getSystemTray();
		TrayItem trayItem = new TrayItem(tray, SWT.NONE);
		trayImage = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "/icons/32.ico").createImage();
		trayItem.setImage(trayImage);
		trayItem.setToolTipText("航天器电子数据单管理系统-Spacecraft EDS Management System");
		return trayItem;
		}
	public void dispose(){
		if (trayImage!=null){
		trayImage.dispose();
		trayItem.dispose();
		}
		}

}
