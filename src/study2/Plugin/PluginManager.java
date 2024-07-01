package study2.Plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;

import Factory.ToolServiceFactory;
import Factory.ToolServiceFactoryImpl;
import study2.utils.VariableUtil;

public class PluginManager {
    static{
        System.out.println(PluginManager.class.getName());
    }
    private Map<String ,PluginClassLoader> pluginMap = new HashMap<String,PluginClassLoader>();
    private static String packagename = "p1.service."+VariableUtil.CurrentPuluinName.split("\\.")[0];
    public PluginManager(){

    }
	private static IWorkbenchWindow window;
    public void service(String pluginName, ArrayList valueList) {
        try {
            packagename = "p1.service." + VariableUtil.CurrentPuluinName.split("\\.")[0];
            System.out.println(pluginName);
            System.out.println(packagename);
            
            // 获取类加载器
            ClassLoader loader = getLoader(pluginName);
            
            // 加载类
            Class<?> forName = Class.forName(packagename, true, loader);
            
            // 假设有无参构造函数
            Constructor<?> constructor = forName.getConstructor();
            ToolServiceFactory ins = (ToolServiceFactory) constructor.newInstance();
            
            // 如果构造函数有参数，使用下面的代码
            // Constructor<?> constructor = forName.getConstructor(ParamType1.class, ParamType2.class, ...);
            // ToolServiceFactory ins = (ToolServiceFactory) constructor.newInstance(param1, param2, ...);
            LocalDateTime now = LocalDateTime.now();
            System.out.println("Current local time: " + now);
            System.out.println("[PLUGIN]"+now+" "+ins.service(valueList));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean test(String pluginName){
  
        try{
        	packagename = "p1.service."+VariableUtil.CurrentPuluinName.split("\\.")[0];
            Class<?> forName = Class.forName(packagename, true, getLoader(pluginName));//this.pluginMap.get(pluginName).loadClass(packagename);
            ToolServiceFactory ins = (ToolServiceFactory)forName.newInstance();
            String string=ins.V();
            System.out.println(string);
            if (string.equals("SUCCESS")) {
            	return true;
            }else 
            	return false;
            
        }catch(Exception e){
            e.printStackTrace();
    		return false;
        }
    }
    
    private static String readJsonFromJar(String jarPath) {
        try (JarFile jarFile = new JarFile(jarPath)) {
            ZipEntry jsonEntry = jarFile.getEntry("json.json");

            if (jsonEntry != null) {
                try (InputStream is = jarFile.getInputStream(jsonEntry);
                     InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                     BufferedReader reader = new BufferedReader(isr)) {
                    
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    return stringBuilder.toString();
                }
            }
        } catch (Exception e) {
        	
            e.printStackTrace();
            MessageDialog.openInformation(window.getShell(), "提示信息", "提示(T0001):插件插入完成！！！");
        }
        return null;
    }
    
    public void view(String pluginName,String add){

        try{
        	System.out.println("444444444444444444444444444444444444");
        	packagename = "p1.service."+VariableUtil.CurrentPuluinName.split("\\.")[0];
            Class<?> forName = Class.forName(packagename, true, getLoader(pluginName));//this.pluginMap.get(pluginName).loadClass(packagename);
            ToolServiceFactory ins = (ToolServiceFactory)forName.newInstance();
            String jsonContent = readJsonFromJar(add);
            
            
            
            
            
            String string=jsonContent;
            System.out.println(string);
            VariableUtil.jsonstring=string;
            VariableUtil.b.setString(string);
        }catch(Exception e){
            e.printStackTrace();
    		//return false;
        }
		//return false;
    }
    private void addLoader(String pluginName,PluginClassLoader loader){
        this.pluginMap.put(pluginName, loader);
    }
    private PluginClassLoader getLoader(String pluginName){
        return this.pluginMap.get(pluginName);
    }
    public void loadPlugin(String pluginName){//加载插件
    	packagename = "p1.service."+VariableUtil.CurrentPuluinName.split("\\.")[0];
        this.pluginMap.remove(pluginName);
        PluginClassLoader loader = new PluginClassLoader();
        String pluginurl = "jar:file:/"+VariableUtil.CurrentPuluinPath+"!/";
        pluginurl=pluginurl.replace("\\","/");
        System.out.println(pluginurl);
        URL url = null;
        try {
            url = new URL(pluginurl);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        loader.addURLFile(url);
        System.out.println(pluginName.split("\\."));
        addLoader(pluginName.split("\\.")[0], loader);
        System.out.println("load " + pluginName + "  success");
    }
    public void unloadPlugin(String pluginName){
        this.pluginMap.get(pluginName.split("\\.")[0]).unloadJarFiles();
        this.pluginMap.remove(pluginName.split("\\.")[0]);
    }
}