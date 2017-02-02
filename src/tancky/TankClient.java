package tancky;


import java.awt.Button;
/*** 
 * Time閿熸枻鎷� 2016.07.21
 * 0.6閿熻姤鏈敓鏂ゆ嫹瑕佸疄閿熸枻鎷烽敓鍓跨》鎷烽敓鏁欏尅鎷烽敓鏂ゆ嫹鍧﹂敓鍓跨鎷烽敓鐙¤鎷�
 * 1.瀹為敓鏂ゆ嫹閿熷壙瀵硅揪鎷烽敓鑺傜殑鎲嬫嫹缂橀敓鏂ゆ嫹閿燂拷
 * 閿熸埅鐧告嫹閿熷姭锝忔嫹閿熸枻鎷峰潶閿熷壙纰夋嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鎻唻鎷风紭閿熸枻鎷烽敓鐫拝鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹涓洪敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷峰睍
 * 閿熸埅鐧告嫹閿熻妭锝忔嫹閿熸枻鎷峰潶閿熷壙纰夋嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鎻唻鎷风紭閿熸枻鎷烽敓鐫拝鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹涓洪敓鏂ゆ嫹閿熸嵎杈炬嫹閿熻妭鐨勮揪鎷峰皬閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹妯￠敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熺Ц锟�
 * 
 * Time:2016.07.22
 * 0.7閿熻姤鏈敓鏂ゆ嫹瑕佷负閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷锋瘬閿熸枻鎷烽敓鏂ゆ嫹閿熸暀鐧告嫹璇撮敓鏂ゆ嫹閿熸枻鎷烽敓闃跺府鎷烽敓鎻紮鎷烽敓鏂ゆ嫹鍟殿剨鎷烽敓鏂ゆ嫹閿熸暀鐧告嫹璇存瘝閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷疯閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓绉革拷
 * 
 * 
 * Time:2016.07.23
 * 0.8閿熻姤鏈敓鏂ゆ嫹瑕侀敓鏂ゆ嫹閿熸枻鎷峰睍鍧﹂敓鍓跨鎷烽敓鐙¤鎷烽敓鏂ゆ嫹閿熸枻鎷蜂负8閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷峰潶閿熸枻鎷烽敓鏂ゆ嫹閿熶茎闈╂嫹閿熸枻鎷�45閿熼ズ鍑ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹璐敓锟�
 * 1.閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹鎴忛敓鏂ゆ嫹閿熸枻鎷烽敓閰垫斁鐨勭》鎷烽敓鏂ゆ嫹
 * 
 * 
 * Time:2016.07.23
 * 0.9閿熻姤鏈敓鏂ゆ嫹瑕侀敓鏂ゆ嫹閿熸帴璁规嫹鍧﹂敓鍓跨殑鎲嬫嫹缂橀敓鏂ゆ嫹鐚擃剨鎷烽敓鏂ゆ嫹鍧﹂敓鏂ゆ嫹閿熺嫛璁规嫹閿熸枻鎷烽敓鏂ゆ嫹骞曢敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
 * 1.閿熸枻鎷�0.9閿熻姤鏈敓鏂ゆ嫹閿熺潾闈╂嫹閿熸枻鎷峰潶閿熷壙纰夋嫹閿熺嫛璁规嫹閿熸枻鎷蜂綅WASD閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鐨嗙尨鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓閾扮殑甯嫹閿熸枻鎷�
 * 閿熸埅鐧告嫹閿熷姭锝忔嫹閿熸枻鎷稵ank閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷疯閿熸枻鎷蜂竴浜涢敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹涓洪敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鎺ヨ揪鎷烽敓鏂ゆ嫹鐩敓鏂ゆ嫹閿熺Ц鐧告嫹閿燂拷
 * 
 * Time:2016.07.23
 * 1.0閿熻姤鏈敓鏂ゆ嫹瑕侀敓鏂ゆ嫹閿熸枻鎷烽敓鑺傜鎷烽敓鏂ゆ嫹Missile閿熸枻鎷烽敓鏂ゆ嫹1.0閿熻姤鏈敓鏂ゆ嫹鍙疄閿熻鍖℃嫹閿熺殕浼欐嫹閿熺嫛绛规嫹閿熸帴纰夋嫹閿熸枻鎷烽敓鏂ゆ嫹
 * 閿熸埅鐧告嫹閿熷姭锝忔嫹閿熸枻鎷烽敓鏂ゆ嫹鍧﹂敓鏂ゆ嫹鍓嶉敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹娌￠敓鏂ゆ嫹閿熸枻鎷烽敓缂搭偓鎷锋媷閿熸枻鎷烽敓鍙揪鎷烽敓绲媋nk閿熸枻鎷烽敓鍙鎷穌raw()閿熸枻鎷烽敓鏂ゆ嫹
 * 
 * 
 * Time:2016.08.17
 * 1.1閿熻姤鏈敓鏂ゆ嫹瑕侀敓鏂ゆ嫹閿熸帴鐧告嫹閿熸澃鍖℃嫹閿熺殕闈╂嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸暀鐧告嫹璇撮敓杞夸紮鎷风叅澶撮敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鎺ョ鎷�
 * 1.閿熸枻鎷烽敓鏂ゆ嫹璇撮敓鏂ゆ嫹閿熸枻鎷烽敓鏁欑櫢鎷疯閿熸枻鎷烽敓鏂ゆ嫹杈栭敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹bug閿熸枻鎷烽敓鏂ゆ嫹涓洪敓鏂ゆ嫹鍧﹂敓鍓跨鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�
 * 
 * Time:2016.08.22
 * 1.2閿熻姤鏈敓鏂ゆ嫹瑕侀敓鏂ゆ嫹閿熸暀鐧告嫹鍙搁敓琛楃櫢鎷烽敓鏂ゆ嫹鑹介敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�
 * 1.閿熸枻鎷烽敓鏂ゆ嫹閿熷壙鎲嬫嫹绀洪敓鏂ゆ嫹绛掗敓鏂ゆ嫹閿熸枻鎷锋煇閿熺殕鎲嬫嫹閿熸枻鎷烽敓绲檃rrelDir閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸暀鐧告嫹鍙搁敓琛楃櫢鎷烽敓鏂ゆ嫹鑹介敓鏂ゆ嫹閿熻妭纰夋嫹閿熸枻鎷穊ug
 * 
 * 
 * Time:2016.08.22
 * 1.3閿熻姤鏈敓鏂ゆ嫹瑕侀敓鏂ゆ嫹閿熻甯嫹閿熻姤鏈敓鏂ゆ嫹鍧﹂敓鏂ゆ嫹鍙敓鏉拌揪鎷烽敓鎻紮鎷烽敓鏂ゆ嫹璇橀敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿燂拷
 * 1.閿熸枻鎷烽敓鏂ゆ嫹涓�閿熸枻鎷稟rrayList閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熻姤鍌ㄩ敓鑺傜鎷烽敓鏂ゆ嫹姣忛敓鏂ゆ嫹閿熼叺鏀惧尅鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹涓�鏋氶敓鏂ゆ嫹閿熸枻鎷�
 * 2.閿熸枻鎷烽敓鏂ゆ嫹骞曢敓鏂ゆ嫹閿熸枻鎷烽敓杈冩枻鎷烽敓鏂ゆ嫹閿熸枻鎷蜂竴閿熸枻鎷烽敓鏂ゆ嫹绀洪敓鏂ゆ嫹鍓嶉敓鑺傜鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹骞曢敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鎺ョ鎷峰墠閿熻妭纰夋嫹閿熸枻鎷�
 * 
 * 
 * Time:2016.09.01
 * 1.4閿熻姤鏈敓鏂ゆ嫹瑕侀敓鏂ゆ嫹閿熸枻鎷疯瘶閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�
 * 1.閿熸枻鎷稭issile閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鎺ヨ鎷稵ankClient閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹鑼敓鏂ゆ嫹閿熸枻鎷烽敓瑙ｅ埌Missile閿熸枻鎷穢閿熸枻鎷穣閿熸枻鎷烽敓鐤ヨ秴閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷锋椂閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷穜emove
 * 
 * 
 * 
 * 
 * Time:2016.09.05
 * 1.5閿熻姤鏈敓鏂ゆ嫹瑕侀敓鏂ゆ嫹閿熸枻鎷烽敓鎺ヤ紮鎷烽敓鐙＄鎷烽敓鏂ゆ嫹鍧﹂敓鍓跨殑鐧告嫹閿熸澃锝忔嫹閿熻妭姝ょ増鏈敓鏂ゆ嫹鏈敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熼摪鐨勭鎷烽敓鏂ゆ嫹鍧﹂敓鏂ゆ嫹閿熸磥锛岄敓鏂ゆ嫹閿熺殕鐚存嫹闄屽Ч鎾呮嫹姣撻敓鏂ゆ嫹閿熸帴锔兼嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹鎷ラ敓鏂ゆ嫹閿熸暀鐧告嫹閿熸枻鎷烽敓锟�
 * 1.閿熸枻鎷稵ank閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹good閿熸枻鎷烽敓鐨嗭綇鎷烽敓鐨嗚揪鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熻鍑ゆ嫹閿熻纰夋嫹閿熸枻鎷峰潶閿熸枻鎷�
 * 
 * Time:2016.09.05
 * 1.6閿熻姤鏈敓鏂ゆ嫹瑕侀敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷蜂笣閿熸枻鎷烽敓渚ョ櫢鎷烽敓鏉帮綇鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷锋挒閿熸枻鎷烽敓鍙嚖鎷峰潶閿熸枻鎷锋椂閿熸枻鎷烽敓鍙嚖鎷峰潶閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸彮鍑ゆ嫹閿熸帴纰夋嫹閿熸枻鎷烽敓鏂ゆ嫹
 * 1.Missle閿熷彨纭锋嫹閿熸枻鎷穐itTank(Tank)閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸埅璇ф嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�
 * 2.閿熸枻鎷锋挒閿熸枻鎷烽敓渚ラ潻鎷烽敓鏂ゆ嫹閿熸枻鎷稲ectangle
 * 3.涓篢ank閿熸枻鎷稭issle閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷穏etRect閿熸枻鎷烽敓鏂ゆ嫹
 * 4.閿熸枻鎷烽敓鏂ゆ嫹閿熷彨纰夋嫹閿熸枻鎷峰潶閿熸枻鎷锋椂閿熸枻鎷峰潶閿熷壙鎲嬫嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鎺ョ鎷蜂篃閿熸枻鎷峰幓    
 * 5.閿熸枻鎷烽敓鎺ュ尅鎷烽敓鏂ゆ嫹Tank閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹live  
 * 
 * 
 * Time:2016.09.05
 * 1.7.1閿熻姤鏈敓鏂ゆ嫹瑕侀敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹涓�閿熸枻鎷烽敓鏂ゆ嫹鐐搁敓娲侊紝閿熸枻鎷烽敓鏂ゆ嫹閿熻妭纰夋嫹閿熸枻鎷烽敓鏂ゆ嫹閿熷彨鏁屽嚖鎷峰潶閿熸枻鎷蜂箣閿熸枻鎷烽敓鏂ゆ嫹鐬ラ敓鏂ゆ嫹閿熺Ц銊愌嶆嫹閿熸枻鎷烽敓锟�
 * 1.閿熺潾闈╂嫹閿熸枻鎷蜂竴閿熸枻鎷烽敓杈冮潻鎷烽敓鑺ユ湰閿熸枻鎷稵ank閿熸枻鎷烽敓鍙櫢鎷烽敓鏂ゆ嫹鍧﹂敓鍓胯揪鎷烽敓渚ユ唻鎷烽敓鏂ゆ嫹live閿熶茎鐧告嫹閿熷眾鍑介敓鏂ゆ嫹閿熸枻鎷峰�奸敓鏂ゆ嫹閿熸枻鎷�
 * 
 * 
 * Time:2016.09.06
 * 1.7.2閿熻姤鏈敓鏂ゆ嫹閿熸枻鎷风偢閿熸枻鎷烽敓璇泦閿熸枻鎷烽敓鍙綇鎷蜂娇閿熺煫鍖℃嫹閿熺殕璇ф嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓绉革拷
 * 
 * 
 * Time:2016.09.06
 * 1.8閿熻姤鏈敓鏂ゆ嫹閿熸帴璁规嫹閿熸枻鎷烽敓鏂ゆ嫹鎬佸潶閿熷壙锝忔嫹閿熸枻鎷烽敓鏂ゆ嫹锜归敓鏁欑櫢鎷锋�濋敓鏂ゆ嫹甯岄敓鏂ゆ嫹閿熸枻鎷烽锜归敓鏁欑櫢鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�
 * 
 * 
 * 
 * Time:2016.09.07
 * 1.9閿熻姤鏈敓鏂ゆ嫹瑕佷娇閿熷彨鍑ゆ嫹鍧﹂敓鏂ゆ嫹閿熷壙璁规嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鐨嗗嚖鎷烽敓鏂ゆ嫹閿熻妭纰夋嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹鍧﹂敓鍓匡綇鎷烽敓鏂ゆ嫹閿熸帴绠�鍗曠鎷稟I
 * 1.閿熸枻鎷风‖閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸暀鐧告嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹钖伴敓鏂ゆ嫹閿熸枻鎷烽敓鍓胯鎷�
 * 2.閿熸枻鎷烽敓鏂ゆ嫹Missile閿熸枻鎷烽敓绲瀘od閿熸枻鎷烽敓鐨嗭綇鎷峰彧閿熷彨纰夋嫹Missile閿熸枻鎷稵ank閿熸枻鎷穏ood閿熸枻鎷蜂竴閿熼摪锝忔嫹閿熻剼鍖℃嫹閿熺殕杈炬嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹鑹介敓鏂ゆ嫹
 * 
 * 
 * 
 * 
 * 
 */
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


public class TankClient extends JFrame {
	
	
	
	NetClient nc = new NetClient ( this ) ; 

	ConnectDialog cd = new ConnectDialog();
	
	
	//娴ｇ姴銈� 
	//閿熸枻鎷烽敓钘夊父閿熸枻鎷烽敓鏂ゆ嫹鎴忛敓鏂ゆ嫹閿熻妭鐨勫尅鎷风兢閫忓彣閿燂拷
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	
	//閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏁欑櫢鎷烽敓锟�
	Tank myTank = new Tank(0,570,true,Direction.STOP,this); 
	
	List<Missile> missiles = new ArrayList<Missile> ();
	
	
	List<Explode> explodes = new ArrayList<Explode> ();
	
	List<Tank> enemyTanks = new ArrayList<Tank> () ;
	
	
	
	
	//鍙岄敓鏂ゆ嫹閿熸枻鎷疯皨閿熸枻鎷烽敓閰佃锟�
	Image offSceenImage = null;
	
	
	
	
	public void paint(Graphics g) {
		//閿熸枻鎷烽敓鎴粯鍑介敓鏂ゆ嫹閿熸枻鎷峰疄閿熸枻鎷峰弻閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�
		//閿熸枻鎷锋閿熸埅闈╂嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
		if (offSceenImage == null){
			offSceenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		//閿熸枻鎷峰獟閿熼ズ鈯ヨ顒婃嫹骞曢敓鏂ゆ嫹閿燂拷
		Graphics gOffSceen = offSceenImage.getGraphics();
		//閿熸枻鎷峰彇閿熸枻鎷烽敓鏂ゆ嫹閿熶茎纰夋嫹鑹查敓鏂ゆ嫹閿熺煫纰夋嫹鑹查敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿燂拷
		Color c = gOffSceen.getColor();
		gOffSceen.setColor(Color.white);
		//閿熸枻鎷烽敓鎻紮鎷烽敓缁炵鎷锋湪閿熸枻鎷疯皳閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�
		gOffSceen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		//閿熸枻鎷烽敓鐭潻鎷烽敓鏂ゆ嫹閿熸枻鎷烽紬濞ｆ枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓琛楃櫢鎷锋嫢鎾為敓鏂ゆ嫹閿熸枻鎷烽敓鎴紮鎷�
		super.paint(gOffSceen);  
		
		myTank.draw(gOffSceen);
		
	
		
		gOffSceen.setColor(Color.black);
		gOffSceen.drawString("Missiles   Count:" + missiles.size(),10,50);
		gOffSceen.drawString("Explodes   Count:" + explodes.size(),10,70);
		gOffSceen.drawString("enemyTanks Count:" + enemyTanks.size(),10,90);
		
		
		for(int i = 0 ; i < enemyTanks .size() ; i ++){
			Tank et = enemyTanks.get(i);
			et.draw(gOffSceen);
			
		}
		
		for(int i = 0 ; i < missiles.size(); i++){
			
			Missile m = missiles.get(i);
			
			if(m.hitTank(myTank)){
				TankDeadMsg tdm = new TankDeadMsg (myTank.id);
				nc.send(tdm);
//System.out.println("TANKID IS " + m.tankID);  100
				MissileDeadMsg mdm = new MissileDeadMsg (m.tankID ,m.id);
				nc.send(mdm);
			}
			//m.hitTanks(enemyTanks);
			
			
			m.draw(gOffSceen);
		}
		
		
		for(int i = 0 ; i < explodes.size() ; i ++){
			Explode e = explodes.get(i);
			e.draw(gOffSceen);
		}
		
		
		
		gOffSceen.setColor(c);
		//閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷疯皨閿熸枻鎷烽敓閰佃顒婃嫹閿熸枻鎷锋皭閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�
		g.drawImage(offSceenImage, 0, 0, null);
		
	}
	
	

	public void lauchFrame () {
		
//		for(int i = 0 ; i < 10 ; i ++){
//			Tank t = new Tank(130+(40*i) ,130, false , Direction.STOP, this );
//			this.enemyTanks.add(t);
//			
//		}
		this.setLocation(300, 150);
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("TankWar");
		this.setVisible(true);
		this.addKeyListener(new KeyMonitor());
		new Thread (new PaintThread()).start();
		

	}
	
	
	
	
	public static void main(String[] args) {
		TankClient tc = new TankClient () ;
		tc.lauchFrame();
		

	}
	
	
	private class PaintThread implements Runnable{
		public void run() {
			while(true){
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO 閿熺殕璁规嫹閿熸枻鎷烽敓缂寸鎷� catch 閿熸枻鎷�
					e.printStackTrace();
				}
			}
			
		}
	}
	
	
	
	private class KeyMonitor extends KeyAdapter{
		
		
		public void keyReleased(KeyEvent e) {
			myTank.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_C){
				cd.setVisible(true);
			}
			else{
				myTank.keyPressed(e);
			}
		
		}
		
		
		
	}
	
	
	class ConnectDialog extends Dialog {
		Button b = new Button ("Link");
		TextField tfIP = new TextField("182.254.247.128",12);
		TextField tfPort = new TextField("" + TankServer.TCP_SERVER,4);
		
		int randomUDPPort = ( int ) (Math.random() * 10000) ;
		String srandomUDPPort = String.valueOf(randomUDPPort);
		TextField tfUDPPort = new TextField(srandomUDPPort,4);
		
		public ConnectDialog (){
			super(TankClient.this , true);
			this.setLayout(new FlowLayout());
			this.add(new Label("IP:"));
			this.add(tfIP);
			this.add(new Label("Port:"));
			this.add(tfPort);
			this.add(new Label("UDP Port:"));
			this.add(tfUDPPort);
			this.add(b);
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String IP = tfIP.getText().trim();
					int TCPPort = Integer.parseInt(tfPort.getText().trim());
					int UDPPort = Integer.parseInt(tfUDPPort.getText().trim());
					nc.setUdpPort(UDPPort);
					nc.connect(IP, TCPPort);
					setVisible(false);
				}
			});
			this.pack();
			this.setLocation(450, 300);
			this.addWindowListener(new WindowAdapter() {

				public void windowClosing(WindowEvent e) {
					setVisible(false);
				}
			});
			
		}
		
		
		
		
		
	}

	
}
