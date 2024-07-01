package study2;
import study2.actions.NezhaHelpAction;
import study2.actions.StopNezhaAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.StatusLineContributionItem;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.actions.OpenInNewWindowAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard;

import study2.actions.ConfigNezhaAction;
import study2.actions.ConnectDatabaseAction;
import study2.actions.CustomNewFileResourceWizard;
import study2.actions.DatabaseConfigAction;
import study2.actions.DeleteAction;
import study2.actions.ExportDataAction;
import study2.actions.ModifyTelemetryAction;
import study2.actions.MoreAction;
import study2.actions.OpenAction;
import study2.actions.OpenCustomNewFileWizardAction;
import study2.actions.OpenPAction;
import study2.actions.OpenPreferencePageAction;
import study2.actions.PrefenceAction2;
import study2.actions.PreferenceAction;
import study2.actions.RefreshDatabaseAction;
import study2.actions.SampleAction;
import study2.actions.SaveAction;
import study2.actions.StartInternet2;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
//引入 StartNezhaAction
import study2.actions.StartNezhaAction;
/**
 * An action bar advisor is responsible for creating, adding, and disposing of
 * the actions added to a workbench window. Each window will be populated with
 * new actions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	// 定义自定义的操作
    private OpenCustomNewFileWizardAction openCustomNewFileWizardAction;
	// Actions - important to allocate these only in makeActions, and then use
	// them
	// in the fill methods. This ensures that the actions aren't recreated
	// when fillActionBars is called with FILL_PROXY.	
	private IWorkbenchAction iExitAction;
	private IWorkbenchAction iAboutAction;
	private IWorkbenchAction iNewWindowAction;
	private IWorkbenchAction iSaveAction;
	 private RefreshDatabaseAction refreshAction;
	private SampleAction new1;
	private Action helpAction; // 您的帮助 Action
	 private Action nezhaHelpAction;
	 private OpenPreferencePageAction openPrefsAction;
	 // 新添加的 Action 声明
	    private Action configNezhaAction;
	    private Action modifyTelemetryAction;
	    private Action moreAction;
	    private Action connectDatabaseAction;
	    private Action databaseConfigAction;
	    private Action exportDataAction;
	    private Action stopNezhaAction;
	 private Action startNezhaAction;
	 private Action startIAction;
	private Action preferenceAction;
	private DeleteAction deleteAction;
	private IWorkbenchAction saveProjectAction;// 保存工程文件
	private IWorkbenchAction saveAllAction;// 保存全部
	private IWorkbenchAction exitAction;
	private IWorkbenchAction aboutAction;
	private Action  testAction3;
	private IWorkbenchAction preferenceAction1;
	private Action openAction;
	private Action openPAction;
	private  SaveAction startStopSaveAction;
	  private RefreshDatabaseAction refreshDatabaseAction;
	 private CustomNewFileResourceWizard customNewFileWizardAction;
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}
	
	protected void makeActions(IWorkbenchWindow window) {
		 refreshDatabaseAction = new RefreshDatabaseAction(window);
	        register(refreshDatabaseAction);
		 // 创建自定义的新建文件向导操作
        openCustomNewFileWizardAction = new OpenCustomNewFileWizardAction();
        // 创建动作
        //SaveAction startStopSaveAction = new SaveAction(null);

        // 注册自定义的新建文件向导操作
        register(openCustomNewFileWizardAction);
        helpAction = new NezhaHelpAction();
        register(helpAction); // 注册 Action
		   openPrefsAction = new OpenPreferencePageAction();
	        register(openPrefsAction);
		preferenceAction1 = ActionFactory.PREFERENCES.create(window);
		register(preferenceAction1);
		 startNezhaAction = new StartNezhaAction(window);
	        register(startNezhaAction);
	        startIAction = new StartInternet2(window);
	        register(startIAction);
	        configNezhaAction = new ConfigNezhaAction(window);
	        register(configNezhaAction);
	        modifyTelemetryAction = new ModifyTelemetryAction(window);
	        register(modifyTelemetryAction);

	        moreAction = new MoreAction(window);
	        register(moreAction);

	        connectDatabaseAction = new ConnectDatabaseAction(window);
	        register(connectDatabaseAction);

	        databaseConfigAction = new DatabaseConfigAction(window);
	        register(databaseConfigAction);

	        exportDataAction = new ExportDataAction(window);
	        register(exportDataAction);
		exitAction = ActionFactory.QUIT.create(window);
		register(exitAction);
		aboutAction = ActionFactory.ABOUT.create(window);
		register(aboutAction);

        stopNezhaAction = new StopNezhaAction(window);
        register(stopNezhaAction);
		saveProjectAction = ActionFactory.SAVE.create(window);
		saveAllAction = ActionFactory.SAVE_ALL.create(window);
		this.getActionBarConfigurer().registerGlobalAction(saveProjectAction); // ctrl+s，保存工程文件。
		this.getActionBarConfigurer().registerGlobalAction(saveAllAction);// 激活快捷键，ctrl+shift+s,全部保存。

		iExitAction = ActionFactory.QUIT.create(window);
		register(iExitAction);
		iSaveAction = ActionFactory.SAVE.create(window);
		register(iSaveAction);
		
		testAction3=new SampleAction();
    	register(testAction3);
		
		iAboutAction = ActionFactory.ABOUT.create(window);
		register(iAboutAction);
		iNewWindowAction = ActionFactory.OPEN_NEW_WINDOW.create(window);
		register(iNewWindowAction);
		
		preferenceAction=new PrefenceAction2();
		preferenceAction.setText("设置");
		register(preferenceAction);
		
		deleteAction=new DeleteAction();
		deleteAction.setText("删除");
		
		register(deleteAction);
		
		new1=new SampleAction();
		new1.setText("TEST");
		register(new1);
		openAction = new OpenAction(window);
		register(openAction);
		openPAction = new OpenPAction(window);
	
		openPAction.setText("添加插件…");	register(openPAction);
		}
	public void fillTrayItem(MenuManager trayMenu) {
		trayMenu.add(new1);
		trayMenu.add(exitAction);
		}
	protected void fillMenuBar(IMenuManager menuBar) {
	    MenuManager fileMenu = new MenuManager("&File", IWorkbenchActionConstants.M_FILE);
	    MenuManager helpMenu = new MenuManager("&Help", IWorkbenchActionConstants.M_HELP);
	    helpMenu.add(helpAction);
	    
	    
	    MenuManager help2Menu = new MenuManager("&About", IWorkbenchActionConstants.M_HELP);
	    MenuManager cjMenu = new MenuManager("&Plug-in");
	    MenuManager xjMenu = new MenuManager("&新建");
	    MenuManager nezhaMenu = new MenuManager("&NeZha");
	    nezhaMenu.add(startIAction);
	    nezhaMenu.add(new Separator());
	    nezhaMenu.add(startNezhaAction);
	    nezhaMenu.add(stopNezhaAction); // 添加关闭哪吒架构
	    // 新添加的哪吒菜单项
	    nezhaMenu.add(configNezhaAction); // 架构基本配置
	    nezhaMenu.add(new Separator());
	    nezhaMenu.add(modifyTelemetryAction); // 遥测数据修改
	    nezhaMenu.add(moreAction); // 更多...
	    MenuManager sTMenu = new MenuManager("&View", IWorkbenchActionConstants.M_VIEW);
	    MenuManager bjMenu = new MenuManager("&Edit", IWorkbenchActionConstants.M_EDIT);
	    MenuManager dataMenu = new MenuManager("&Data", IWorkbenchActionConstants.MB_ADDITIONS);
	    // 新添加的数据菜单项
	    dataMenu.add(connectDatabaseAction); // 一键连接数据库
	    dataMenu.add(refreshDatabaseAction);
	    dataMenu.add(databaseConfigAction); // 数据库连接配置
	    dataMenu.add(exportDataAction); // 数据导出
	 //   dataMenu.add(startStopSaveAction);
	    menuBar.add(fileMenu);
	    menuBar.add(bjMenu);
	    menuBar.add(cjMenu);
	    menuBar.add(nezhaMenu);
	    menuBar.add(dataMenu);
	    menuBar.add(sTMenu);
	    menuBar.add(helpMenu);
	    menuBar.add(help2Menu);
	    // File Menu
	    xjMenu.add(openAction);
	    //fileMenu.add(xjMenu);
	     // 添加自定义的新建文件向导操作到 "文件" 菜单
        fileMenu.add(openCustomNewFileWizardAction);
	    fileMenu.add(openAction);
	    fileMenu.add(iSaveAction);
	    fileMenu.add(new Separator());
	    fileMenu.add(openPrefsAction);
	    //fileMenu.add(testAction3);
	    fileMenu.add(new Separator());
	    fileMenu.add(iExitAction);
	
	    cjMenu.add(openPAction);
	    // Help Menu
	    help2Menu.add(iAboutAction);
	    bjMenu.add(new1);
	    bjMenu.add(new Separator());
	    bjMenu.add(deleteAction);

	    sTMenu.add(new1);
	}

	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		// This will add a new toolbar
		IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT); 
		coolBar.add(new ToolBarContributionItem(toolbar, "main"));
		// Add the entry to the
		toolbar.add(openCustomNewFileWizardAction);
		toolbar.add(openAction);
		toolbar.add(iSaveAction);
		 // 	toolbar.add(startStopSaveAction);
	     // 添加自定义的新建文件向导操作到 "文件" 菜单

	
		
	}
	
    @Override	protected void fillStatusLine(IStatusLineManager statusLine) {
    	super.fillStatusLine(statusLine);	
    // 定义StatusLineContributionItem对象	
    final StatusLineContributionItem statusItem = new StatusLineContributionItem("");	
    // 获取进度监视器，并在状态栏显示		
    statusLine.getProgressMonitor();	
    // 设置状态栏文本		

    
//    statusItem.setText("开发:中国科学院国家空间科学中心-张峻巍");	
    statusItem.setText("Developed by: National Space Science Center, Chinese Academy of Sciences - Zhang Junwei");	
    //将statusItem注册到statusLine	
    statusLine.add(statusItem);	
    final StatusLineContributionItem statusItem2 = new StatusLineContributionItem("");	
    statusItem2.setText("V0.8.30223.56-Beta");	
    //将statusItem注册到statusLine	
    statusLine.add(statusItem2);
    }
    


	
	

}
