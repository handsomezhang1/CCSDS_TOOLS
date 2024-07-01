package study2.Plugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Factory.ToolServiceFactory;
public interface ToolServiceFactoryImpl {
public abstract List<String> service(ArrayList valueList);
	
	
	
	/**
	 * 用于工具箱的视图层控制，将视图文件（*.json）放至class根目录下，传入该文件，输出Map类之后交由视图渲染引擎进行渲染。隔离了视图的单独实现方式。
	 * @category 视图类
	 * @author kfzjw008
	 * @version 1.0.0
	 * @param Filename 指视图文件的名称。
	 * @return {@code Map <String,String>} json格式的map表示方法。
	 */
	public   Map<String, Object> tomap();


	
	public String V();
	}