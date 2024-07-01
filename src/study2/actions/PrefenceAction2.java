package study2.actions;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import study2.Activator;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class PrefenceAction2 extends Action  {
	private IWorkbenchWindow window;
	public final String ID = "id";
	/**
	 * The constructor.
	 */
	public PrefenceAction2() {
		setId(ID);//如果没有setID就会出现编译成功，但是没有界面的情况
	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	   @Override 
	    public void run() { 

			Preferences preferences = new ConfigurationScope()
					.getNode(Activator.PLUGIN_ID);
					// This would be using default n scope
					// Preferences preferences = new DefaultScope()
					// .getNode(Application.PLUGIN_ID);
					Preferences sub1 = preferences.node("note1");
					Preferences sub2 = preferences.node("node2");
					sub1.put("h1", "Hello");
					sub1.put("h2", "Hello again");
					sub2.put("h1", "Moin");
					MessageDialog.openInformation(window.getShell(), "Info", sub1.get("h1",
					"default"));
					MessageDialog.openInformation(window.getShell(), "Info", sub1.get("h2",
					"default"));
					MessageDialog.openInformation(window.getShell(), "Info", sub2.get("h1",
					"default"));
					
					// Forces the application to save the preferences
					try {
					preferences.flush();
					} catch (BackingStoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
	        super.run(); 
	    } 

	   @Override 
	    public ImageDescriptor getImageDescriptor() { 
	        // TODO Auto-generated method stub 
	        return Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/eclipse16.png"); //设置action图标
	    } 

	    @Override 
	    public String getText() { 
	        // TODO Auto-generated method stub 
	        return "设置"; //设置action标题
	    } 
}