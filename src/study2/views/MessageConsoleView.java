/**
 * 
 */
package study2.views;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.internal.console.ConsoleManager;
import org.eclipse.ui.internal.console.ConsoleView;

import study2.utils.AppPrinter;


public class MessageConsoleView extends ConsoleView {
	public static final String ID = "study2.views.MessageConsoleView";
	public MessageConsoleView() {
		super();
		
		// 视图初始化时绑定唯一的MessageConsole 
		ConsoleManager consoleManager = (ConsoleManager)AppPrinter.getConsoleManager();
		consoleManager.registerConsoleView(this);
		consoleManager.showConsoleView(AppPrinter.getConsole());
		consoleManager.addConsoleListener(this);
	}
	
	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		IViewSite viewSite = getViewSite();
		IActionBars actionBars = viewSite.getActionBars();
		// 隐藏视图菜单
		IMenuManager menuManager = actionBars.getMenuManager();
		menuManager.setVisible(false);
		// 隐藏工具条按钮
		IToolBarManager toolBarManager = actionBars.getToolBarManager();
		IContributionItem[] items = toolBarManager.getItems();
		for(IContributionItem item: items) {
			item.setVisible(true);
		}
		// 更新视图菜单
		actionBars.updateActionBars();
	}
	
}

