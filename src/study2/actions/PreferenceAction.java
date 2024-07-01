/**
 * 
 */
package study2.actions;

/**
 * @author kfzjw008
 *
 */
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import study2.Activator;
public class PreferenceAction extends Action implements IWorkbenchAction {
private IWorkbenchWindow window;
public final String ID = "id2";
/**
* The constructor.
*/
public PreferenceAction() {
	setId(ID);
}
/**
* The action has been activated. The argument of the
* method represents the 'real' action sitting
* in the workbench UI.
* @see IWorkbenchWindowActionDelegate#run
*/
public void run(IAction action) {
// This would be using instance scope
// Preferences preferences = new InstanceScope()
// .getNode(Application.PLUGIN_ID);
// This is using configuration scope
	
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
			