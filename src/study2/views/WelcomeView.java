package study2.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import study2.utils.CsvReaderUtil;

import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import java.io.IOException;
import java.util.List;


public class WelcomeView extends ViewPart {
	public WelcomeView() {
	}
    public static final String ID = "study2.views.WelcomeView"; // Ensure this ID is unique and used in the perspective layout


    @Override
    public void createPartControl(Composite parent) {
        FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        ScrolledForm form = toolkit.createScrolledForm(parent);
//        form.setText("欢迎");
        form.setText("Welcome");
        form.getBody().setLayout(new GridLayout());
//        
//        String welcomeText = "欢迎来到国家空间科学中心复杂航天系统电子信息技术重点实验室，一个致力于航天创新的先锋阵地。\n" +
//        	    "本软件是由自适应科学探测研究团队精心打造，是我们在有效载荷集成领域追求卓越的象征，展现了科研团队的独到见解与突破性成果。\n" +
//        	    "我们深深感激课题组不懈的追求和无穷的创新精神，他们在国家航天科技的征途上砥砺前行，铸就辉煌。\n" +
//        	    "课题组以其非凡的洞察力和卓越的领导能力，引领着团队攀登科研的高峰。他的贡献犹如北斗，在夜空中指引我们的方向，我们为有他们而感到骄傲，并满怀期待地看向未来的成就——无疑，将与科学的殿堂紧紧相连。\n" +
//        	    "在这里，我们不仅见证科学的进步，更是感受到每一位研究者对探索未知的深深执着。自适应科学探测，不仅仅是一个名词，它代表着一种永不停息的探索和对完美的追求。\n" +
//        	    "请允许我以最深的敬意，赞美这支充满活力、勇于探索、不断创新的团队。他们像璀璨的星辰，照亮了科研的天空，也照亮了我们前进的道路。\n" +
//        	    "而今，让我们以诗的形式，缅怀那些为科研贡献力量的人——\n" +
//        	    "在茫茫宇宙间，探秘的心不曾停歇，\n" +
//        	    "何博以智慧筑桥，连接未知的彼岸。\n" +
//        	    "勇敢的心，砺炼科学的锋芒，\n" +
//        	    "在有效载荷的征途上，我们携手并肩。\n" +
//        	    "每个理论的跃动，都映射着无限的可能，\n" +
//        	    "在这光辉璀璨的岁月里，向着未来，我们一同前行。\n \n \n最后请让我用一首诗歌总结： \n "+ "天地英雄气， \n 千秋尚凛然。  \n 有效载荷集成，  \n 科研路上更进展。";
//        
        String welcomeText = "Welcome to the Key Laboratory of Technology in Geo-Spatial Information Processing and Application System, under the National Space Science Center, a pioneer position dedicated to aerospace innovation.\n" +
        	    "This software is meticulously crafted by the Adaptive Scientific Exploration Research Team, symbolizing our pursuit of excellence in the payload integration field and showcasing the research team's unique insights and breakthrough achievements.\n" +
        	    "We are profoundly grateful for the relentless pursuit and boundless innovative spirit of the project team, who forge ahead on the journey of national aerospace science and technology, creating brilliance.\n" +
        	    "With their exceptional insight and outstanding leadership, the project team leads us to climb the peaks of scientific research. Their contributions, like the Big Dipper in the night sky, guide our direction. We take pride in having them and look forward to future accomplishments—undoubtedly linked closely with the halls of science.\n" +
        	    "Here, we witness not only the progress of science but also feel every researcher's deep dedication to exploring the unknown. Adaptive scientific exploration is not just a term; it represents an unceasing quest and pursuit of perfection.\n" +
        	    "Please allow me to express my deepest respect and praise for this vibrant, exploratory, and continuously innovative team. Like brilliant stars, they illuminate the sky of scientific research and the path we tread.\n" +
        	    "Now, let us reminisce about those who have contributed to scientific research in the form of a poem—\n" +
        	    "In the vast universe, the heart of exploration never ceases,\n" +
        	    "Wisdom builds bridges to the unknown shores.\n" +
        	    "Brave hearts, hone the edge of science,\n" +
        	    "On the journey of payload integration, we stand united.\n" +
        	    "Each leap in theory reflects infinite possibilities,\n" +
        	    "In these glorious and bright years, towards the future, we march together.\n \n \nFinally, please allow me to conclude with a poem: \n" +
        	    "The heroic spirit of heaven and earth, \n Endures through thousands of years. \n Payload integration, \n Advances further on the path of scientific research.";
     // 使用 <br/> 替换所有的 \n 以实现换行
        welcomeText = welcomeText.replace("\n", "<br/>");

        FormText rtf = toolkit.createFormText(form.getBody(), false);
        rtf.setText("<form><p>" + welcomeText + "</p></form>", true, false);
       
        GridData gd = new GridData(SWT.FILL, SWT.TOP, true, false);
        System.out.println("[INFO]测试");
        System.out.println("[WARN]测试");
        System.out.println("[ERR]测试");
        System.out.println("[LOG]测试");
        rtf.setLayoutData(gd);
        
        // 设置更大的字号
        rtf.setFont("headerFont", new Font(parent.getDisplay(), "Arial", 14, SWT.BOLD));
        
        // 设置文本格式
        rtf.setColor("header", parent.getDisplay().getSystemColor(SWT.COLOR_BLUE));
        rtf.setHyperlinkSettings(toolkit.getHyperlinkGroup());
        
        // 定义和设置富文本
//        String formTextData = "<form><p><span font=\"headerFont\" color=\"header\">欢迎来到SEMS：</span>" +
//            welcomeText + "</p>" +
//            "<li><a href=\"link1\">点击这里</a>查看更多信息。</li>" +
//            "<li><a href=\"link2\">点击这里</a>了解相关项目。</li>" +
//            "</form>";
        String formTextData = "<form><p><span font=\"headerFont\" color=\"header\">Welcome to SEMS：</span>" +
                welcomeText + "</p>" +
                "<li><a href=\"link1\">点击这里</a>查看更多信息。</li>" +
                "<li><a href=\"link2\">点击这里</a>了解相关项目。</li>" +
                "</form>";
        
        rtf.setText(formTextData, true, true);
        
        // 添加超链接监听器
        rtf.addHyperlinkListener(new HyperlinkAdapter() {
            public void linkActivated(HyperlinkEvent e) {
                // 处理超链接点击事件
                System.out.println("超链接被点击: " + e.getHref());
                // 这里可以打开浏览器或其他视图等操作
            }
        });
    }


    @Override
    public void setFocus() {
        // Set focus if necessary
    }


}
