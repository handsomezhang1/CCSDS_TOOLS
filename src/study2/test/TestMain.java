package study2.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import study2.Plugin.PluginManager;

public class TestMain {

    public static void main(String[] args) throws Exception {


        PluginManager manager = new PluginManager();;
        
        ArrayList listArgument = new ArrayList();//manager.service需要的参数为string，arraylist
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cmd = br.readLine();

        while(!cmd.equals("bye")){
            if(cmd.startsWith("do")){
                String pluginName = cmd.split(" ")[1];
                manager.service(pluginName,listArgument);
            }
            if(cmd.startsWith("load")){
                String pluginName = cmd.split(" ")[1];
                manager.loadPlugin(pluginName);
            }
            if(cmd.startsWith("unload")){
                String pluginName = cmd.split(" ")[1];
                manager.unloadPlugin(pluginName);
            }
            cmd = br.readLine();
        }
    }
}
