package study2.Plugin.PluginFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import study2.Plugin.ToolServiceFactory;

public class Plugin1 implements ToolServiceFactory{
	private String ToolName="test1";
	private String Category="42534523";


	private String name="test1";
	private String description="42534523";
	private String author="kfzjw008";
	private String date="2022-09-22";
	private String jsonString="{\r\n"
			+ "    \"information\": {\r\n"
			+ "        \"title\": \"test1\",\r\n"
			+ "        \"description\": \"this is a example test.\"\r\n"
			+ "    },\r\n"
			+ "    \"allcontent\": [\r\n"
			+ "        {\r\n"
			+ "            \"pagename\": \"testpage\",\r\n"
			+ "            \"description\": \"test content\",\r\n"
			+ "            \"pagecontent\": [\r\n"
			+ "                {\r\n"
			+ "                    \"name\": \"a\",\r\n"
			+ "                    \"label\": \"a\",\r\n"
			+ "                    \"description\": \"aaa\",\r\n"
			+ "                    \"type\": \"inputtext\",\r\n"
			+ "                    \"content\": 0,\r\n"
			+ "                    \"field\": null,\r\n"
			+ "                    \"datatype\": \"int\"\r\n"
			+ "                },\r\n"
			+ "                {\r\n"
			+ "                    \"name\": \"b\",\r\n"
			+ "                    \"label\": \"b\",\r\n"
			+ "                    \"description\": \"bbb\",\r\n"
			+ "                    \"type\": \"inputtext\",\r\n"
			+ "                    \"content\": 0,\r\n"
			+ "                    \"field\": null,\r\n"
			+ "                    \"datatype\": \"int\"\r\n"
			+ "                }\r\n"
			+ "            ]\r\n"
			+ "        }\r\n"
			+ "    ]\r\n"
			+ "}";
	public List<String> service(ArrayList  list){//此处为service的实现类，这里是实现了业务（a+b）并输出c
	List<String> resuList= new ArrayList<>() ;
	int c=(int)list.get(0)+(int)list.get(1);  
	resuList.add(String.valueOf(c));
	return resuList;
	
}
	


	public String getName() {
		
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String V() {
		return "SUCCESS";
	}






	
}
