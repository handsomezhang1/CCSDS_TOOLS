package study2.Plugin;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.print.DocFlavor.STRING;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 * 工具箱的工厂类主要解决各个工具链和工具的统一标准接口.所有的工具都需要继承该类来实现具体的操作。

 * 
 * @author kfzjw008
 * @version 1.0.0
 * @throws IOexception 可能会出现文件的读取和写入，需要在此处处理异常。
 * @param o object to be compared for equality with this map entry
 * @return {@code true} if the specified object is equal to this map
 *         entry
 */
public  abstract class  ToolServiceFactory{
	private  int ToolID;
	private  String ToolName;
	private  String Author;
	private  String Category;
	private  String Date;
	private  String Version;
	private  String Description;
	private  String remark;
	private  String jSONString;
	


	public ToolServiceFactory(String ToolName,String Category) {
		this.ToolName=ToolName;
		this.Category=Category;
		this.Author="Admin";
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
		this.Date=dateFormat.format(date);
		this.Version="1.0.0";
		this.Description="New Plugin";
		this.remark="null";
	}
	
	/**
	 * 用于工具箱各类工具的核心操作和核心业务，其中不包括文件输出业务，只包括数据处理业务。
	 * 文件输出业务有具体的工具类进行更深入的实现。
	 * @category 核心业务类
	 * @author kfzjw008
	 * @version 1.0.0
	 * @param valueList 指所有需要的参数，最外围由list包括，可以按照序列顺序处理相关的需求参数。在使用的时候，可以向该值内传入无限的且不限类型的参数。
	 * list的每个元素都是一个参数的输入，这一个参数可以是数组，可以是单个对象等。
	 * @return {@code List<String>} 返回的值以List为主。其中每个list中的元素可以对应后续函数生成对应的一个文件。如需生成多个文件则需要多个List元素支持。
	 */
	public abstract List<String> service(ArrayList valueList);
	
	
	
	/**
	 * 用于工具箱的视图层控制，将视图文件（*.json）放至class根目录下，传入该文件，输出Map类之后交由视图渲染引擎进行渲染。隔离了视图的单独实现方式。
	 * @category 视图类
	 * @author kfzjw008
	 * @version 1.0.0
	 * @param Filename 指视图文件的名称。
	 * @return {@code Map <String,String>} json格式的map表示方法。
	 */
	public   Map<String, Object> tomap(){
		JSONObject user = JSONObject.parseObject(jSONString);
		 Map<String, Object> userMap = new HashMap<>();       
		//循环转换                
		for (Map.Entry<String, Object> entry : user.entrySet()) {                    
		    userMap.put(entry.getKey(), entry.getValue());                
		}            
		System.out.println("map对象:" + userMap.toString()); 


		return userMap;
	}

	/**
	 * @return the toolName
	 */
	public String getToolName() {
		return ToolName;
	}

	/**
	 * @param toolName the toolName to set
	 */
	public void setToolName(String toolName) {
		ToolName = toolName;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return Author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		Author = author;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return Category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		Category = category;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return Version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		Version = version;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the jSONString
	 */
	public String getjSONString() {
		return jSONString;
	}

	/**
	 * @param jSONString the jSONString to set
	 */
	public void setjSONString(String jSONString) {
		this.jSONString = jSONString;
	}
	
	public String V() {
		return "SUCCESS";
	}
	
}