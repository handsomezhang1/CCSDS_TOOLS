package study2.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Slider;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;

import swing2swt.layout.BoxLayout;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;

import com.alibaba.fastjson2.JSON;

import study2.Plugin.PluginManager;
import study2.utils.VariableUtil;

public class editorView extends ViewPart {
	private Text text;
	private Text text_1;
	public Map<String, Object> mapTypes;
	public String aString;
	private List<Control> inputControls = new ArrayList<>(); // 存储所有输入控件的引用
	public editorView() {
		// TODO Auto-generated constructor stub
	}

	public void createview(Composite parent, Map<String, Object> map) {
		String titleString = (String) (((Map<String, Object>) map.get("information"))).get("title");
		String descriptionString = (String) (((Map<String, Object>) map.get("information"))).get("description");
		System.out.println(titleString);

		parent.setLayout(new GridLayout(4, false));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label lblTestlabelfsdfsd_1 = new Label(parent, SWT.NONE);
		lblTestlabelfsdfsd_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		lblTestlabelfsdfsd_1.setText(titleString);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label lblTestlabelfsdfsd = new Label(parent, SWT.NONE);
		GridData gd_lblTestlabelfsdfsd = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_lblTestlabelfsdfsd.widthHint = 130;
		lblTestlabelfsdfsd.setLayoutData(gd_lblTestlabelfsdfsd);
		lblTestlabelfsdfsd.setText(descriptionString);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

//	Map allcontent = (Map<String, Object>) map.get("allcontent");
		List<Object> list = (List<Object>) map.get("allcontent");
		int k = 0;
		ArrayList<String> kArrayList = new ArrayList<>();
		for (Object i : list) {
			Map jsonMap = new HashMap<String, Object>();
			jsonMap = (Map) i;
			jsonMap.get("pagename");
//		System.out.println(jsonMap.get("pagename"));
			// Assert.isNotNull(jsonMap.get("pagename"));
			System.out.println(jsonMap.get("pagename"));
			System.out.println(jsonMap.get("description"));
			List<Object> list2 = (List<Object>) jsonMap.get("pagecontent");
			for (Object i2 : list2) {

				Map jsonMap2 = new HashMap<String, Object>();
				jsonMap = (Map) i2;
				jsonMap.get("name");
				System.out.println(jsonMap.get("name"));

				Label lblTestlabel = new Label(parent, SWT.NONE);
				lblTestlabel.setText((String) jsonMap.get("label"));

				text = new Text(parent, SWT.BORDER);
				text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
				new Label(parent, SWT.NONE);
				text.setData("ViewValues", k);
				kArrayList.add("");
				FocusListener focusListener = new FocusListener() {
					public void focusGained(FocusEvent e) {
						// kArrayList
						Text t = (Text) e.widget;
						int kk = (int) t.getData("ViewValues");
						kArrayList.set(kk, t.getText());

					}

					public void focusLost(FocusEvent e) {
						// TODO Auto-generated method stub
						Text t = (Text) e.widget;
						int kk = (int) t.getData("ViewValues");
						kArrayList.set(kk, t.getText());
						System.out.println(kArrayList);
					}
				};

				text.addFocusListener(focusListener);

				k++;
			}

		}
		// String descriptionString=(String) ( ((Map<String, Object>)
		// map.get("information"))).get("description");

		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Button btnNewButton = new Button(parent, SWT.NONE);
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.widthHint = 61;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("Submit");

		btnNewButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				// kArrayList
				PluginManager manager = new PluginManager();
				File directory = new File("");
				System.out.println(directory.getAbsolutePath());
				VariableUtil.CurrentPuluinPath = directory.getAbsolutePath() +"/Plugin/"+ VariableUtil.CurrentPuluinName;// 修改当前路径，可以读取新路径
				manager.loadPlugin(VariableUtil.CurrentPuluinName.split("\\.")[0]);
				manager.service(VariableUtil.CurrentPuluinName.split("\\.")[0], kArrayList);
			}
		});

		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		// TODO Auto-generated method stub

	}
	
	
	  public void createview2(Composite parent, Map<String, Object> map) {
		  parent.setLayout(new GridLayout(1, false)); // 单列布局，每个控件占一行

		    // 解析information部分并创建标题和描述
		    Map<String, Object> information = (Map<String, Object>) map.get("information");
		    String titleString = (String) information.get("title");
		    String descriptionString = (String) information.get("description");

		    createTitle(parent, titleString);
		    createDescription(parent, descriptionString);

		    // 解析allcontent部分
		    List<Map<String, Object>> allcontent = (List<Map<String, Object>>) map.get("allcontent");
		    for (Map<String, Object> content : allcontent) {
		        // 创建包含每个pagecontent的新Composite，每个Composite为一行
		        Composite pageComposite = new Composite(parent, SWT.NONE);
		        pageComposite.setLayout(new GridLayout(2, false)); // 设置为两列的GridLayout
		        pageComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		        // 创建页面名称和描述的标签，保持现有布局
		        createPageLabel(pageComposite, (String) content.get("pagename"));
		        createPageDescription(pageComposite, (String) content.get("description"));

		        // 解析pagecontent部分并为每个控件创建一个新的行
		        List<Map<String, Object>> pagecontents = (List<Map<String, Object>>) content.get("pagecontent");
		        for (Map<String, Object> element : pagecontents) {
		            createControl(pageComposite, element); // 创建控件的新行
		        }
		    }

	        // 提交按钮
	        createSubmitButton(parent);
	    }

	  private void createTitle(Composite parent, String title) {
		    Label titleLabel = new Label(parent, SWT.NONE);
		    titleLabel.setText(title);
		    titleLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD)); // 字体增大并加粗
		    titleLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		}

		private void createDescription(Composite parent, String description) {
		    Label descriptionLabel = new Label(parent, SWT.WRAP);
		    descriptionLabel.setText(description);
		    descriptionLabel.setFont(SWTResourceManager.getFont("Italic", 10, SWT.ITALIC)); // 设置为斜体
		    descriptionLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		    
		}

		private void createPageLabel(Composite parent, String text) {
		    Label pageLabel = new Label(parent, SWT.NONE);
		    pageLabel.setText(text);
		    pageLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.BOLD)); // 加粗
		    pageLabel.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_BLUE)); // 设置为蓝色
		}

		private void createPageDescription(Composite parent, String description) {
		    Label pageDescLabel = new Label(parent, SWT.NONE);
		    pageDescLabel.setText(description);
		    pageDescLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.NORMAL));
		    pageDescLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		    pageDescLabel.setData(SWT.UNDERLINE_SINGLE); // 设置为下划线
		}
	  
	  private void createLabel(Composite parent, String text, boolean isTitle) {
	        Label label = new Label(parent, SWT.NONE);
	        if (isTitle) {
	            label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
	        }
	        label.setText(text);
	        new Label(parent, SWT.NONE);
	        new Label(parent, SWT.NONE);
	        new Label(parent, SWT.NONE);
	    }

	    private void createControl(Composite parent, Map<String, Object> element) {
	    	 String content ="";
	        String type = (String) element.get("type");
	        String label = (String) element.get("label");
	        if(type.equals("Inputtext")) {
	           content = (String) element.get("content");
	        }
	   
	        String description = (String) element.get("description");
	        // 创建一个新的Composite来作为一行
	        Composite controlComposite = new Composite(parent, SWT.NONE);
	        controlComposite.setLayout(new GridLayout(2, false)); // 两列布局
	        //controlComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	     
	        // 创建标签并设置ToolTip作为描述
	        Label labelControl = new Label(controlComposite, SWT.NONE);
	        labelControl.setText(label);
	        labelControl.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD)); // 设置标签为加粗字体
	        labelControl.setToolTipText(description);
	        GridData labelGridData = new GridData(SWT.RIGHT, SWT.CENTER, false, false);
	        labelGridData.horizontalAlignment = GridData.END;
	        labelGridData.widthHint = 100; // 您可以根据需要调整这个值
	        labelControl.setLayoutData(labelGridData);
	        
	        

	        // 根据type创建右边的控件
	        switch (type) {
            case "Checkbox":
            case "RadioButton":
                createOptionsControl(parent, element, type);
                break;
            case "Inputtext":
                createTextInput(parent, label,content);
                break;
            case "List":
                createDropDown(parent, element);
                break;
            case "Slider":
                createSlider(parent, element);
            default:
                System.out.println("Unknown type: " + type); // 调试语句
                break;
            // ... 其他控件类型可以在此扩展
        }
	        // 为控件设置布局数据
	      //  setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	    }

	    private void createOptionsControl(Composite parent, Map<String, Object> element, String type) {
	    	 Composite buttonsComposite = new Composite(parent, SWT.NONE);
	    	    RowLayout rowLayout = new RowLayout(SWT.HORIZONTAL); // 使用RowLayout以水平排列控件
	    	    rowLayout.marginLeft = 0;
	    	    rowLayout.marginTop = 0;
	    	    rowLayout.spacing = 10; // 控件之间的间距
	    	    buttonsComposite.setLayout(rowLayout);

	    	    List<String> options = (List<String>) element.get("content");
	    	    for (String option : options) {
	    	        Button button = new Button(buttonsComposite, type.equals("Checkbox") ? SWT.CHECK : SWT.RADIO);
	    	        button.setText(option);
	    	        inputControls.add(button); // 将文本输入框添加到列表中
	    	        // 设置按钮的布局数据（如果需要的话）
	    	    }
	    }

	    private void createTextInput(Composite parent, String label,String content) {
	    	   // 创建一个新的Composite来放置单选按钮或复选框组，以便它们能在同一行内排列
	        Composite buttonsComposite = new Composite(parent, SWT.NONE);
	        RowLayout rowLayout = new RowLayout(SWT.HORIZONTAL); // 使用RowLayout以水平排列控件
	        rowLayout.marginLeft = 0;
	        rowLayout.marginTop = 0;
	        rowLayout.spacing = 10; // 控件之间的间距
	        buttonsComposite.setLayout(rowLayout);
	        // 创建文本输入框
	       // new Label(parent, SWT.NONE).setText(label);
	        Text text = new Text(buttonsComposite, SWT.BORDER);
	        GC gc = new GC(text);
	        Point size = gc.textExtent("W"); // 使用一个典型宽度的字符，例如 'W'
	        gc.dispose();

	        int charWidth = size.x;
	        int totalWidth = charWidth * 20; // 20个字符的宽度
	        text.setSize(totalWidth, SWT.DEFAULT);
	        text.setText(content);
	        inputControls.add(text); // 将文本输入框添加到列表中
	      //  text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
	    }

	    private void createDropDown(Composite parent, Map<String, Object> element) {
	        // 创建下拉框
	        //new Label(parent, SWT.NONE).setText((String) element.get("label"));
	        Combo combo = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY);
	        List<String> items = (List<String>) element.get("content");
	        combo.setItems(items.toArray(new String[0]));
	        combo.select(0); // 选择第一项
	        inputControls.add(combo); // 将文本输入框添加到列表中
	    }

	    private void createSlider(Composite parent, Map<String, Object> element) {
	        // 创建滑块
	       // new Label(parent, SWT.NONE).setText((String) element.get("label"));
	        Slider slider = new Slider(parent, SWT.HORIZONTAL);
	        List<Integer> range = (List<Integer>) element.get("field");
	        slider.setMinimum(range.get(0));
	        slider.setMaximum(range.get(1) + slider.getThumb());
	        slider.setSelection((Integer) element.get("content"));
	        inputControls.add(slider); // 将文本输入框添加到列表中
	    }

	    private void createSubmitButton(Composite parent) {
	        // 创建提交按钮
	        Button btnNewButton = new Button(parent, SWT.NONE);
	        btnNewButton.setText("Submit");
	        // 按钮事件监听略...
	        
	        
	        btnNewButton.addSelectionListener(new SelectionAdapter() {
	            public void widgetSelected(SelectionEvent event) {
	              //  Map<String, Object> values = new HashMap<>(); // 用于存储所有控件的值
	                ArrayList<String> values=new ArrayList<>(); 
	    			
	                System.out.println("[DEBUG] inputControls size: " + inputControls.size());
	                for (Control control : inputControls) {
	                	// System.out.println(control.get);
	                	
	                	 System.out.println("[DEBUG] Control: " + control.getClass().getSimpleName());
	                    if (control instanceof Text) {
	                        Text text = (Text) control;
	                        values.add(text.getText());
	                        System.out.println("[INFO] Text元素：" + text.getText());
	                    } else if (control instanceof Button) {
	                        Button button = (Button) control;
	                        if ((button.getStyle() & SWT.CHECK) != 0 || (button.getStyle() & SWT.RADIO) != 0) {
	                            boolean selected = button.getSelection();
	                            values.add(String.valueOf(selected));
	                            System.out.println("[INFO] Button元素：" + button.getText() + " 选中状态：" + selected);
	                        }
	                    } else if (control instanceof Combo) {
	                        Combo combo = (Combo) control;
	                        String selected = combo.getText();
	                        values.add(selected);
	                        System.out.println("[INFO] Combo元素：" + selected);
	                    } else if (control instanceof Slider) {
	                        Slider slider = (Slider) control;
	                        int value = slider.getSelection();
	                        values.add(String.valueOf(value));
	                        System.out.println("[INFO] Slider元素值：" + value);
	                    }
	                    // ... 检查并获取其他类型控件的值 ...
	                }
	                
	                // 调用service函数并传递values
	                PluginManager manager = new PluginManager();
					File directory = new File("");
					System.out.println(directory.getAbsolutePath());
					VariableUtil.CurrentPuluinPath = directory.getAbsolutePath() +"/Plugin/"+ VariableUtil.CurrentPuluinName;// 修改当前路径，可以读取新路径
					manager.loadPlugin(VariableUtil.CurrentPuluinName.split("\\.")[0]);
					//manager.service(VariableUtil.CurrentPuluinName.split("\\.")[0], kArrayList);
	                // ... 加载插件并调用service函数 ...
	                manager.service(VariableUtil.CurrentPuluinName.split("\\.")[0], values);
	                //tableViewer.setInput(newInput); // 设置新的输入数据
	                //tableViewer.refresh(); // 刷新视图以显示新的输入
	                callRefreshOnDataBaseView() ;
	                MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "提示信息", "提示(T0002):数据提交完成！！！");
	            }
	        });
	        
	        
	    }
	  
	    
	    private void callRefreshOnDataBaseView() {
	        // 获取当前的工作台页面
	        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	        // 尝试找到数据库视图的实例
	        for (IViewReference viewRef : page.getViewReferences()) {
	            if (DataBaseView.ID.equals(viewRef.getId())) {
	                DataBaseView dataBaseView = (DataBaseView) viewRef.getView(false);
	                if (dataBaseView != null) {
	                    // 调用数据库视图的刷新方法
	                    dataBaseView.refreshAllTableViews();
	                }
	                break;
	            }
	        }
	    }
	    
	@Override
	public void createPartControl(Composite parent) {
		   // 创建ScrolledComposite
        ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);

        // 创建一个容器Composite，用于放置实际的视图内容
        Composite contentComposite = new Composite(scrolledComposite, SWT.NONE);
        
        
        
		  // 确保父容器设置了 GridLayout
	    GridLayout layout = new GridLayout(2, false); // 1 列
	    layout.marginWidth = 10; // 减少列间的宽度
	    layout.horizontalSpacing = 10; // 减少列间的间距
	    contentComposite.setLayout(layout);
	    layout.verticalSpacing = 10; // 设置控件之间的垂直间距
	    new Label(contentComposite, SWT.NONE);
	    new Label(contentComposite, SWT.NONE);
	    new Label(contentComposite, SWT.NONE);
	    new Label(contentComposite, SWT.NONE);
	    new Label(contentComposite, SWT.NONE);
	    new Label(contentComposite, SWT.NONE);
	    new Label(contentComposite, SWT.NONE);
	    new Label(contentComposite, SWT.NONE);
	    new Label(contentComposite, SWT.NONE);
	     new Label(contentComposite, SWT.NONE);
	     new Label(contentComposite, SWT.NONE);
	     new Label(contentComposite, SWT.NONE);
	     new Label(contentComposite, SWT.NONE);
	     // parent.setLayout(layout);

	      // 创建初始显示的几行字
	      Label defaultText = new Label(contentComposite, SWT.NONE);
//	      defaultText.setText("    提示：当前插件为空。请点击‘插件’->‘添加插件…’来加入相关的插件。");
	      defaultText.setText("    Tip: The current plug-in is empty. Please click 'Plugins' ->' Add plugins... 'to add related plugins.");
	      //defaultText.setLayoutData(gridData);

	    // 设置布局数据来调整垂直位置
	    GridData gridData = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
	    gridData.verticalIndent = 800; // 调整这个值以改变文本的垂直位置
	    
		aString = VariableUtil.jsonstring;
		VariableUtil.b.setString(aString);
		Map mapTypess = JSON.parseObject(aString);
		System.out.println("这个是用JSON类的parseObject来解析JSON字符串!!!666");
		
		if (mapTypess != null) {
			for (Object obj : mapTypess.keySet()) {
				System.out.println("key为：" + obj + "值为：" + mapTypess.get(obj));
			}
			mapTypes = mapTypess;
			VariableUtil.Map = mapTypes;
			  // 在创建视图内容之前，清除初始文本
	        defaultText.dispose(); // 清除默认显示的几行字
			createview2(contentComposite, VariableUtil.Map);
		}

		VariableUtil.b.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				aString = VariableUtil.jsonstring;
				Map mapTypess = JSON.parseObject(aString);
				System.out.println("这个是用JSON类的parseObject来解析JSON字符串!!!");
				for (Object obj : mapTypess.keySet()) {
					System.out.println("key为：" + obj + "值为：" + mapTypess.get(obj));
				}
				mapTypes = mapTypess;

				VariableUtil.Map = mapTypes;
		         // 同样，在更新视图之前，清除初始文本
	            defaultText.dispose();
	            resetview(contentComposite);
			}
		});
		  // 配置ScrolledComposite
        scrolledComposite.setContent(contentComposite);
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setExpandVertical(true);
        scrolledComposite.setMinSize(contentComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
    

	    // 请求更新和重新布局父容器
	    parent.layout();
	}

	public void resetview(Composite parent) {
		Control[] con = parent.getChildren();
		for (Control c : con) {
			c.dispose();
		}

		// 填充新内容
		// ******************
		Map<String, Object> map = VariableUtil.Map;// VariableUtil.Map;
		inputControls.clear();
		createview2(parent, map);
		// 执行刷新操作
	
		parent.requestLayout();

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
