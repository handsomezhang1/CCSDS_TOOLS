package study2.actions;

import java.util.ArrayList;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.osgi.internal.loader.classpath.ClasspathManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.jface.dialogs.MessageDialog;

/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class SampleAction extends Action  {
	private IWorkbenchWindow window;
	public final String ID = "id";
	/**
	 * The constructor.
	 */
	public SampleAction() {
		setId(ID);//如果没有setID就会出现编译成功，但是没有界面的情况
	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {

		  /*取得当前DefaultCl

	ClasspathManager manager = ((DefaultClassLoader) DataFileManager.class.getClassLoader()).getClasspathManager();  

	 /* 准备第三方JAR的路径列表  
	
	ArrayList<String> paths = new ArrayList<String>();  
		
	paths.add("external: D:/external.jar");  
		
	 /* 把这些JAR的路径作为plugin的fragment在运行时加入到plugin中去.  
	
	manager.attachFragment(manager.getBaseData(),null,paths.toArray(new String[paths.size()]));  

	assLoader的ClasspathManager*/  
	}

	/**
	 * Selection in the workbench has been changed. We 
	 * can change the state of the 'real' action here
	 * if we want, but this can only happen after 
	 * the delegate has been created.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system
	 * resources we previously allocated.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to
	 * be able to provide parent shell for the message dialog.
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}