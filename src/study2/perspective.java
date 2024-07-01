package study2;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

import study2.views.MessageConsoleView;

public class perspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		 layout.setEditorAreaVisible(true);
		layout.setFixed(false);
		String editorArea = layout.getEditorArea();
		//System.out.println("3576085135813947");
		IFolderLayout tabs = layout.createFolder("BOTTOM", IPageLayout.BOTTOM, 0.55f, editorArea);
		// 属性
//		tabs.addPlaceholder(IPageLayout.ID_PROP_SHEET);
		// console view 绑定
		//layout.addView("study2.views.LogView", IPageLayout.BOTTOM,IPageLayout.RATIO_MAX, IPageLayout.ID_EDITOR_AREA);

	IFolderLayout leftTopFolder = layout.createFolder("LEFT", IPageLayout.LEFT, 0.18f, editorArea);
		leftTopFolder.addView("study2.views.DataBaseView");//study2.views.DataBaseView
		leftTopFolder.addView("study2.views.TelemetryDataView");//study2.views.DataBaseView
		leftTopFolder.addView("study2.views.FileListView");//study2.views.DataBaseView
		//leftTopFolder.addView("study2.view");
		IFolderLayout leftBOTTOMFolder = layout.createFolder("BOTTOM", IPageLayout.BOTTOM, 0.8f, editorArea);
		leftBOTTOMFolder.addView("study2.views.LogView");
		// 右侧
        // 在左侧下方创建一个文件夹，用于“哪吒架构控制台”
		IFolderLayout leftRIGHTFolder = layout.createFolder("RIGHT", IPageLayout.RIGHT, 0.55f, editorArea);
        IFolderLayout bottomLeftFolder = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, 0.5f, "RIGHT");
        bottomLeftFolder.addView("study2.views.NezhaArchitectureConsoleView");
		leftRIGHTFolder.addView("study2.views.editorView"); // 假设“插件操作台”的视图ID为 study2.views.PluginOperationView
		leftRIGHTFolder.addView("study2.views.ImageView");
		IFolderLayout centerFolder = layout.createFolder("center", IPageLayout.TOP, 0.7f, editorArea);
		centerFolder.addPlaceholder(editorArea);
		centerFolder.addView("study2.views.WelcomeView");




		

		

		//leftRIGHTFolder.addView("study2.views.NezhaArchitectureConsoleView");
		//leftRIGHTFolder.addView("study2.views.editorView");
	       
	    		  
	       
	}

}
