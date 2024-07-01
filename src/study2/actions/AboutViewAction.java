/**
 * 
 */
package study2.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import study2.views.AboutView;
/**
 * @author kfzjw008
 *
 */
public class AboutViewAction  implements IViewActionDelegate{
	AboutView myView;
	public void init(IViewPart view) {
		// TODO Auto-generated method stub
		this.myView = (AboutView) view;
		}
		public void run(IAction action) {
		// TODO Auto-generated method stub
		MessageDialog.openInformation(myView.getViewSite().getShell(),
		"Information",
		"Very well, you did it, you did add an action to this view. You are my hero!");
		}
		public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		}
}
