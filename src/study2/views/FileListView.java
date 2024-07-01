package study2.views;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.part.ViewPart;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import study2.Plugin.PluginManager;
import study2.actions.OpenPAction;
import study2.utils.CopyByChannel;
import study2.utils.FileInput;
import study2.utils.VariableUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class FileListView extends ViewPart {
	public FileListView() {
	}
    public static final String ID = "study2.views.FileListView";


    @Override
    public void createPartControl(Composite parent) {
    	 TableViewer viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
    	    createColumns(viewer);

    	    // 设置表格头部和网格线可见
    	    Table table = viewer.getTable();
    	    table.setHeaderVisible(true);
    	    table.setLinesVisible(true);

    	    viewer.setContentProvider(ArrayContentProvider.getInstance());
    	    try {
				viewer.setInput(getFiles());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }

    private void createColumns(TableViewer viewer) {
        // 文件名列
        TableViewerColumn colFileName = new TableViewerColumn(viewer, SWT.NONE);
        colFileName.getColumn().setWidth(80);
        colFileName.getColumn().setText("文件名");
        colFileName.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                File file = (File) element;
                return file.getName();
            }
        });
        // 插件名称列
        TableViewerColumn colPluginName = new TableViewerColumn(viewer, SWT.NONE);
        colPluginName.getColumn().setWidth(100);
        colPluginName.getColumn().setText("插件名称");
        System.out.println("aasx");
        colPluginName.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                File file = (File) element;
                System.out.println("aas");
                return getJsonAttribute(file, "title");
            }
        });
        // 描述列
        TableViewerColumn colDescription = new TableViewerColumn(viewer, SWT.NONE);
        colDescription.getColumn().setWidth(200);
        colDescription.getColumn().setText("描述");
        colDescription.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                File file = (File) element;
                return getJsonAttribute(file, "description");
            }
        });

        // 操作列
        TableViewerColumn colAction = new TableViewerColumn(viewer, SWT.NONE);
        colAction.getColumn().setWidth(100);
        colAction.getColumn().setText("操作");
        colAction.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public void update(ViewerCell cell) {
                cell.setText("打开");
                cell.setFont(new Font(cell.getControl().getDisplay(), new FontData("Arial", 10, SWT.BOLD)));
                cell.setForeground(new Color(cell.getControl().getDisplay(), new RGB(0, 0, 255)));
           
               // cell.setStyleRanges(SWT.UNDERLINE_SINGLE);
            }
        });

     // 为列添加鼠标点击事件
     viewer.getControl().addListener(SWT.MouseDown, event -> {
         ViewerCell cell = viewer.getCell(new Point(event.x, event.y));
         if (cell != null && "打开".equals(cell.getText())) {
             // 模拟编辑支持的点击事件
             new OpenActionSupport(viewer).setValue(cell.getElement(), null);
         }
     });
        
       // colAction.setEditingSupport(new OpenActionSupport(viewer));
    }

    private String getJsonAttribute(File jarFile, String attribute) {
        try (JarFile jar = new JarFile(jarFile)) {
            JarEntry entry = jar.getJarEntry("json.json");
            if (entry != null) {
                try (InputStream input = jar.getInputStream(entry);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
                     
                    StringBuilder jsonContent = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        jsonContent.append(line);
                    }
                    
                    JSONObject json = JSON.parseObject(jsonContent.toString());
                    if ("title".equals(attribute)) {
                        return json.getJSONObject("information").getString("title");
                    } else if ("description".equals(attribute)) {
                        return json.getJSONObject("information").getString("description");
                    }
                }
            } else {
                System.out.println("未找到json.json文件");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    
    
    private File[] getFiles() throws IOException {
        File directory = new File("."); 
        String path = directory.getCanonicalPath() + "/Plugin/"; // 获取当前目录的路径并构造到datas目录的路径
     // 假设 directory 是一个 File 对象，它应该代表你项目的根目录。
        // 输出目录以便检查
        System.out.println("数据目录的绝对路径是：" + path);
        
        // 创建指向datas目录的File对象
        File dataDirectory = new File(path);
        
        // 检查目录是否存在，并列出文件
        File[] files = dataDirectory.listFiles();
        if (files == null) {
            System.err.println("无法访问或读取 'datas' 目录");
            return new File[0];
        }
        return files;
    }



    @Override
    public void setFocus() {
        // 焦点设置
    }
}
class OpenActionSupport extends EditingSupport {
	private IWorkbenchWindow window;
    public OpenActionSupport(ColumnViewer viewer) {
        super(viewer);
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
        return null; // No actual editing is happening
    }

    @Override
    protected boolean canEdit(Object element) {
        return true;
    }

    @Override
    protected Object getValue(Object element) {
        return null; // No actual value is being edited
    }

    @Override
    protected void setValue(Object element, Object value) {
        // The action when the "Open" text is clicked
        if (element instanceof File) {
            File file = (File) element;
            // 这里执行您想要的操作，比如在控制台输出
            System.out.println("打开文件：" + file.getAbsolutePath());
            
            
            
         // TODO Auto-generated method stub

    		File tempFile = file;
    		String fileName = tempFile.getName();// 插件名称
    		VariableUtil.CurrentPuluinName = fileName;
    		VariableUtil.CurrentPuluinPath = file.getAbsolutePath();
    		PluginManager manager = new PluginManager();
    		;
    		manager.loadPlugin(VariableUtil.CurrentPuluinName.split("\\.")[0]);
    		if (manager.test(VariableUtil.CurrentPuluinName.split("\\.")[0]) == false) {
    			System.out.println("JAR非法格式！");

    			// XXX
    			MessageDialog.openError(getViewer().getControl().getShell(), "错误信息", "错误(E0001):JAR非法格式！");

    			manager.unloadPlugin(VariableUtil.CurrentPuluinName.split("\\.")[0]);
    		} else {
    			//CopyByChannel.copyByChannel(file.getAbsolutePath(), "Plugin/" + VariableUtil.CurrentPuluinName);
    			manager.unloadPlugin(VariableUtil.CurrentPuluinName.split("\\.")[0]);
    			File directory = new File("");
    			System.out.println(directory.getAbsolutePath());
    			VariableUtil.CurrentPuluinPath = directory.getAbsolutePath() +"/Plugin/"+ VariableUtil.CurrentPuluinName;// 修改当前路径，可以读取新路径
    			manager.loadPlugin(VariableUtil.CurrentPuluinName.split("\\.")[0]);
    			
    			manager.view(VariableUtil.CurrentPuluinName.split("\\.")[0],VariableUtil.CurrentPuluinPath);
    			 LocalDateTime now = LocalDateTime.now();
    	            System.out.println("Current local time: " + now);
    			System.out.println("[LOG]"+now+" "+"插件插入完成。");

    			// XXX
    			
    			MessageDialog.openInformation(getViewer().getControl().getShell(), "提示信息", "提示(T0001):插件插入完成！！！");
    			// manager.view(VariableUtil.CurrentPuluinName.split("\\.")[0]);
    			
    		}
    		;

    		// System.out.println("方法一：fileName = " + fileName);
    		//CopyByChannel.copyByChannel(file.getAbsolutePath(), "Plugin/" + VariableUtil.CurrentPuluinName);
    		FileInput input = new FileInput(file.getAbsolutePath());
    		// try {
    		// page.openEditor(input, editorcontent.ID);
    		// } catch (PartInitException e) {
    		// MessageDialog.openError(window.getShell(), "错误", "无法打开文件： " + path);
    		// }
            
            
            // 如果您想要弹出一个对话框
          
        }
    }
}
