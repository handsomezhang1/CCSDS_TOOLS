package study2.views;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.sql.SQLException;

import study2.utils.SqliteUtil;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.TabFolder;

public class DataBaseView extends ViewPart {
    public static final String ID = "study2.views.DataBaseView";
    private CTabFolder tabFolder ;
   // private Map<String, TableViewer> tableViewers = new HashMap<>();
    public DataBaseView() {
    }

    // 保存所有TableViewer的映射，以便刷新它们
    private Map<String, TableViewer> tableViewers = new HashMap<>();
    
    
    @Override
    public void createPartControl(Composite parent) {
    	 // 初始化this.tabFolder，而不是声明一个新的局部变量
     //   this.tabFolder = new CTabFolder(parent, SWT.BOTTOM);
        SqliteUtil sqlite = new SqliteUtil();
        // 创建选项卡容器
       tabFolder = new CTabFolder(parent, SWT.BOTTOM);
        
        List<String> tableNames = null;
        try {
            tableNames = sqlite.getTableNames("database1");
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }

        if (tableNames != null) {
            // 为每个表创建一个选项卡
            for (String tableName : tableNames) {
                // 创建新的选项卡项
                CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
                // 保存CTabFolder引用
              //  this.tabFolder = new CTabFolder(parent, SWT.BOTTOM);
                tabItem.setText(tableName); // 设置选项卡的标题

                // 创建 TableViewer 来显示表内容
                TableViewer tableViewer = new TableViewer(tabFolder, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
                Table table = tableViewer.getTable();
                table.setHeaderVisible(true); // 显示表头
                table.setLinesVisible(true); // 显示表格线
                // 确保以下这行代码放在你设置好tableViewer之后
                createContextMenu(table, tableViewer);
                // 创建上下文菜单
                // createContextMenu(table, tableViewer); // 确保这个方法存在
                
                List<Map<String, Object>> mapsList = null;
                try {
                	String sql  = "SELECT * FROM "+tableName+";";
                    mapsList = sqlite.select("database1", sql, null);
                } catch (ClassNotFoundException | SQLException | IOException e) {
                    e.printStackTrace();
                }
                if (mapsList != null && !mapsList.isEmpty()) {
                    Map<String, Object> firstRow = mapsList.get(0);
                    for (String columnName : firstRow.keySet()) {
                        TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
                        viewerColumn.getColumn().setText(columnName);
                        viewerColumn.getColumn().setWidth(100); // 设定列宽度
                        viewerColumn.setLabelProvider(new ColumnLabelProvider() {
                            @Override
                            public String getText(Object element) {
                                Map row = (Map) element;
                                return row.get(columnName) != null ? row.get(columnName).toString() : "";
                            }
                        });
                    }
                }
                
                // 设置内容提供者和输入
                tableViewer.setContentProvider(new ArrayContentProvider());
                tableViewer.setInput(mapsList);

                // 设置选项卡控件
                tabItem.setControl(tableViewer.getControl());
                tableViewers.put(tableName, tableViewer);
            }
        }

        tabFolder.setSelection(0); // 选择第一个选项卡
        
        int refreshInterval = 60000; // 比如每5秒刷新一次，根据需要设定
        Display.getCurrent().timerExec(refreshInterval, new Runnable() {
            public void run() {
                if (!parent.isDisposed()) {
                    refreshAllTableViews();
                    Display.getCurrent().timerExec(refreshInterval, this); // 重新调度任务
                }
            }
        });
    }

    // 新方法：刷新所有表格及其数据
    public void refreshAllTableViews() {
        for (Map.Entry<String, TableViewer> entry : tableViewers.entrySet()) {
            String tableName = entry.getKey();
            TableViewer tableViewer = entry.getValue();
            
            // 重新获取该表的数据
            List<Map<String, Object>> mapsList = null;
            try {
                String sql = "SELECT * FROM " + tableName + ";";
                mapsList = SqliteUtil.select("database1", sql, null);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 如果获取数据成功，更新TableViewer
            if (mapsList != null) {
                tableViewer.setInput(mapsList);
                tableViewer.refresh();
            }
        }
    }


    private void createContextMenu(Table table, TableViewer viewer) {
        MenuManager menuManager = new MenuManager();
        Menu menu = menuManager.createContextMenu(table);
        table.setMenu(menu);
        getSite().registerContextMenu(menuManager, viewer);

        // 为表格添加鼠标监听器
        table.addListener(SWT.MouseDown, event -> {
            Point pt = new Point(event.x, event.y);
            TableItem item = table.getItem(pt);
            if (item == null) return;

            // 遍历所有列，查看点击位置的点是否在列的区域内
            for (int i = 0; i < table.getColumnCount(); i++) {
                Rectangle rect = item.getBounds(i);
                if (rect.contains(pt)) {
                    // 用户点击的是第 i 列
                    final String selectedCellValue = item.getText(i);

                    // 创建复制动作
                    Action copyCellAction = new Action("复制") {
                        public void run() {
                            Clipboard clipboard = new Clipboard(Display.getCurrent());
                            TextTransfer textTransfer = TextTransfer.getInstance();
                            clipboard.setContents(new Object[]{selectedCellValue}, new Transfer[]{textTransfer});
                            clipboard.dispose();
                        }
                    };

                    // 用复制动作更新菜单
                    menuManager.removeAll();
                    menuManager.add(copyCellAction);
                    // 复制整行内容的动作
                    Action copyRowAction = new Action("复制整行") {
                        @Override
                        public void run() {
                            IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
                            Map selectedRow = (Map) selection.getFirstElement();
                            if (selectedRow != null) {
                            	String rowData = (String) selectedRow.values().stream()
                                        .map(obj -> obj != null ? obj.toString() : "")
                                        .collect(Collectors.joining(", "));


                                Clipboard clipboard = new Clipboard(Display.getCurrent());
                                TextTransfer textTransfer = TextTransfer.getInstance();
                                clipboard.setContents(new Object[]{rowData}, new Transfer[]{textTransfer});
                                clipboard.dispose();
                            }
                        }
                    };
                    menuManager.add(copyRowAction);

                    // 删除行的动作
                    Action deleteRowAction = new Action("删除") {
                        @Override
                        public void run() {
                            IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
                            Map selectedRow = (Map) selection.getFirstElement();
                            if (selectedRow != null && !selectedRow.isEmpty()) {
                                boolean confirm = MessageDialog.openConfirm(viewer.getControl().getShell(), 
                                    "确认删除", "您确定要删除这条记录吗？");
                                if (confirm) {
                                    try {
                                        // 获取表名和id
                                        String tableName = getCurrentTableName(); // 确保此处正确获取到表名
                                        Object id = selectedRow.get("id"); // 确保表中有名为'id'的列
                                        // 调试输出
                                        System.out.println("Deleting from table: " + tableName + ", id: " + id);
                                        // 执行删除操作
                                        int result = SqliteUtil.deleteRow("database1", tableName, id);
                                        System.out.println("Delete result: " + result);
                                        // 刷新表格视图
                                        refreshAllTableViews();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    };
                    menuManager.add(deleteRowAction);
                    // 刷新数据库动作
                    Action refreshDatabaseAction = new Action("刷新数据库") {
                        @Override
                        public void run() {
                            refreshAllTableViews();
                        }
                    };
                    menuManager.add(refreshDatabaseAction);
                    
                    table.setMenu(menu);
                    break;
                }
            }
        });

        // 给表格设置菜单
        viewer.getControl().setMenu(menu);
    }
    private String getCurrentTableName() {
        CTabItem selectedItem = tabFolder.getSelection();
        return selectedItem != null ? selectedItem.getText() : null;
    }

    // 覆盖setFocus方法，添加刷新功能的调用
    @Override
    public void setFocus() {
        // 刷新视图中的所有表格及其数据
        refreshAllTableViews();
    }

}
