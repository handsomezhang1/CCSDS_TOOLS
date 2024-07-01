package study2.views;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import study2.utils.PreferenceConstants;
import study2.utils.SocketUtil;

public class NezhaArchitectureConsoleView extends ViewPart {
	public NezhaArchitectureConsoleView() {
	}
	   private SocketUtil socketUtil; // 套接字工具类成员变量
    private Text textArea;
    private Tree commandTree;
    private Label[] statusLabels; // UI标签数组
    private Color[] statusColors; // 颜色数组，红色为0，绿色为1
    private Composite parentComposite;
    private Canvas[] statusIndicators; // 用于存储所有状态指示器的Canvas控件

    @Override
    public void createPartControl(Composite parent) {
   

    	 statusIndicators = new Canvas[6]; 
        this.parentComposite = parent;
    	 parent.setLayout(new GridLayout(1, false)); // One column layout for the main parent
    	 // 初始化SocketUtil实例
         socketUtil = new SocketUtil(PreferenceConstants.P_IP_ADDRESS,  Integer.parseInt(PreferenceConstants.P_PORT)); // 替换为实际的服务器地址和端口号
         // Status indicators at the top, adjusted to 2 rows of 4 columns
         Composite statusComposite = new Composite(parent, SWT.NONE);
         // 初始化statusLabels数组
         statusLabels = new Label[6]; // 数组大小为6，根据实际需要调整
         GridLayout gridLayout = new GridLayout(4, false); 
         statusComposite.setLayout(gridLayout); // 4 columns, will create 2 rows automatically

//
//         // 初始化每个标签并添加到状态复合组件中
//         String[] statusLabelss = {"以太网连接", "1553B总线", "状态1", "状态2", "状态3", "状态4"};
//         statusIndicators[0]=createStatusIndicator(statusComposite, statusLabelss[0], 0);
//         GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
// 	    gridData.heightHint = 2; // 设置行高为 30 像素，根据需要调整
//         for (int i = 1; i < statusLabelss.length; i++) {
//        	 GridData gridData2 = new GridData(SWT.FILL, SWT.CENTER, true, false);
//        	    gridData2.heightHint = 2; // 设置行高为 30 像素，根据需要调整
//        	    statusIndicators[i] = createStatusIndicator(statusComposite, statusLabelss[i], 0); // Initially all statuses are red
//        	}
//
//        
//
//         
//         
//         statusColors = new Color[6];
//        // createStatusIndicator(statusComposite, "以太网连接", SocketUtil.checkConnectivity("192.168.137.1", 8008));
//        // System.out.println("[LOG]测试66");
//        // for (int i=1;i<6;i++) {
//         //    createStatusIndicator(statusComposite, statusLabelss[i], 0); // Initially all statuses are false
//        // }
//
//         // Button bar below the status indicators and above the input area, 2 rows of 4 buttons each
//         Composite buttonBarComposite = new Composite(parent, SWT.NONE);
//         buttonBarComposite.setLayout(new GridLayout(4, true)); // 4 columns, will create 2 rows automatically
//         buttonBarComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
//
//         // Create buttons in the button bar
//         for (int i = 1; i <= 8; i++) {
//             Button button = new Button(buttonBarComposite, SWT.PUSH);
//             button.setText("事件表 " + i);
//             GridData buttonGridData = new GridData(SWT.FILL, SWT.FILL, true, false);
//             button.setLayoutData(buttonGridData);
//         }

         // Input area and Send button
         Composite inputComposite = new Composite(parent, SWT.NONE);
         inputComposite.setLayout(new GridLayout(2, false));
         inputComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

         textArea = new Text(inputComposite, SWT.BORDER| SWT.WRAP  | SWT.MULTI | SWT.V_SCROLL);
         GridData textAreaData = new GridData(SWT.FILL, SWT.CENTER, true, false);
         textAreaData.heightHint = 50; // 50 pixels high
         textArea.setLayoutData(textAreaData);

         Button sendButton = new Button(inputComposite, SWT.PUSH);
         sendButton.setText("Sends");
         sendButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        // Bottom part is divided into left (Tree and Button) and right (Table)
        Composite bottomComposite = new Composite(parent, SWT.NONE);
        bottomComposite.setLayout(new GridLayout(2, true)); // Two equal columns
        bottomComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        // Left side for Tree and Button
        Composite treeComposite = new Composite(bottomComposite, SWT.NONE);
        treeComposite.setLayout(new GridLayout(1, false));
        treeComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        commandTree = new Tree(treeComposite, SWT.BORDER | SWT.V_SCROLL);
        commandTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        populateTree(commandTree); // Add some example values to the tree

        Button fillCommandButton = new Button(treeComposite, SWT.PUSH);
        fillCommandButton.setText("Fill Command");
        fillCommandButton.setLayoutData(new GridData(SWT.FILL, SWT.END, true, false));
        fillCommandButton.addListener(SWT.Selection, e -> fillCommandFromTree());

        // Right side for Table
        Table table = new Table(bottomComposite, SWT.BORDER | SWT.FULL_SELECTION);
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        // Create columns for the table
        TableColumn column1 = new TableColumn(table, SWT.NONE);
        column1.setText("name");//名称
        column1.setWidth(150);

        TableColumn column2 = new TableColumn(table, SWT.NONE);
        column2.setText("value");//值
        column2.setWidth(150);

        // Add some example data to the table
        for (int i = 0; i < 4; i++) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(new String[] { "Telemetry parameter " + (i + 1), String.valueOf(i * 10) });//遥测参数
        }

        // Adjust column width
        table.getColumn(0).pack();
        table.getColumn(1).pack();
        
        sendButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // 获取输入区域的文本
                String message = textArea.getText();
                // 使用SocketUtil发送文本
                try {
                    socketUtil.sendString(message);
                    System.out.println("[LOG]IOException:疑似发送成功！");
                    
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.out.println("[ERR]IOException:发送失败！");
                }
            }
        });
        
    }

    private Canvas createStatusIndicator(Composite parent, String label, int statusValue) {
        Composite indicatorComposite = new Composite(parent, SWT.NONE);
        indicatorComposite.setLayout(new GridLayout(2, false)); // 1个用于Canvas，1个用于Label
        indicatorComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        Canvas statusCanvas = new Canvas(indicatorComposite, SWT.NONE);
        GridData canvasGridData = new GridData(20, 20); // 设置Canvas的大小为圆形的直径
        statusCanvas.setLayoutData(canvasGridData);
        statusCanvas.addPaintListener(e -> {
            Color color = (statusValue == 1) ? e.display.getSystemColor(SWT.COLOR_GREEN) : e.display.getSystemColor(SWT.COLOR_RED);
            e.gc.setBackground(color);
            
            e.gc.fillOval(3, 3, 14, 14); // 填充一个圆形
            e.gc.drawOval(3, 3, 14, 14);
        });

        Label statusLabel = new Label(indicatorComposite, SWT.NONE);
        statusLabel.setText(label);
        statusLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        return statusCanvas; // 返回创建的Canvas对象
    }
    
    
 
    public void updateStatusIndicator(int index, int statusValue) {
        if (index < 0 || index >= statusIndicators.length || statusIndicators[index] == null) {
            return; // 保护性检查
        }
        Display.getDefault().asyncExec(() -> {
            statusIndicators[index].addPaintListener(e -> {
                Color color = (statusValue == 1) ? e.display.getSystemColor(SWT.COLOR_GREEN) : e.display.getSystemColor(SWT.COLOR_RED);
                e.gc.setBackground(color);
                GridData canvasGridData = (GridData) statusIndicators[index].getLayoutData();
              
                e.gc.fillOval(3, 3, 14, 14); // 重新填充圆形
                e.gc.drawOval(3, 3, 14, 14);
            });
            statusIndicators[index].redraw(); // 重绘Canvas，以触发新的绘图事件
        });
    }






    private void populateTree(Tree tree) {
        TreeItem item1 = new TreeItem(tree, SWT.NONE);
        item1.setText("Load manager prestarts");//载荷管理器预启动
        item1.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT));
        item1.setData("000AAA");
        TreeItem item2 = new TreeItem(tree, SWT.NONE);
        item2.setText("Load manager start instruction");//载荷管理器启动指令
        item2.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT));
        item2.setData("000BBB");
        TreeItem item3 = new TreeItem(tree, SWT.NONE);
        item3.setText("Load manager shutdown instruction");//载荷管理器关闭指令
        item3.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT));
        item3.setData("000CCC");
        TreeItem item4 = new TreeItem(tree, SWT.NONE);
        item4.setText("Add monitor");//新增监视项
        item4.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT));
        item4.setData("ddd");
        TreeItem item5 = new TreeItem(tree, SWT.NONE);
        item5.setText("Delete monitor");//删除监视项
        item5.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT));
        item5.setData("eee");
        TreeItem item6 = new TreeItem(tree, SWT.NONE);
        item6.setText("Change monitor");//更改监视项
        item6.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT));
        item6.setData("fff");
        // Add more items as needed...
    }

    private void fillCommandFromTree() {
        TreeItem[] selection = commandTree.getSelection();
        if (selection.length > 0) {
        	 Object hiddenData = selection[0].getData(); // 获取隐藏属性
        	 if (hiddenData != null) {
                 textArea.setText(hiddenData.toString()); // 假设隐藏属性是字符串
             }
        }
    }

    @Override
    public void setFocus() {
        if (textArea != null && !textArea.isDisposed()) {
            textArea.setFocus();
        }
    }
    @Override
    public void dispose() {
        for (Color color : statusColors) {
            if (color != null && !color.isDisposed()) {
                color.dispose();
            }
        }
        super.dispose();
    }

}
