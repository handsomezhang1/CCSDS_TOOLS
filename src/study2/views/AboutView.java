/**
 * 
 */
package study2.views;

import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
/**
 * @author kfzjw008
 *
 */
public class AboutView extends ViewPart {
	public static final String ID = "study2.views.AboutView";
	public AboutView() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void createPartControl(Composite parent) {
	// TODO Auto-generated method stub
	Text text = new Text(parent, SWT.BORDER);
	text.setText("Imagine a fantastic user interface here");
	}
	@Override
	public void setFocus() {
	// TODO Auto-generated method stub
	}
}
