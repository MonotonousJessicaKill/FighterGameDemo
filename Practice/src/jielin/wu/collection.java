package day01;

/**
 * 这个类的作用是练习文档注释 文档注释可以卸载类，方法，常量上面
 * 
 * @author wu.jielin
 * @version 1.0
 * @see java.util.Date
 * @since jdk1.7
 */
public class DocDemo {

	public static final int CONST_FIELD = 1;

	/**
	 * main，程序的入口
	 * 
	 * @param args
	 *  
	 * @return
	 */
	public static void main(String[] args) {

	}
}
package day01;
/**
 * 认识String特性
 * @author wu.jielin
 *
 */
public class StringDemo {
	public static void main(String[] args) {
		/**
		 * 在执行时字符串新建以前会先从常量池中寻找，若以有该字符串便不会新建
		 * 另外，==比较的是值（引用的值为地址）
		 */
		String str1="123Hello";
		String str2="123Hello";
		/**
		 * 编译器小特性
		 * 当一个表达式运算两边都是字面量，那么编译过程中就会计算。将结果生成在
		 * 表达式中的位置，节省运行时开销
		 */
		String str3="123"+"Hello";
		//String str3="123Hello" 字节码中的样子
		
		String str4=1+23+"Hello";
		String str5="1"+23+"Hello";
		String str6=1+'2'+"3"+"Hello";//不与前面==  '2'以assic码进行运算
		String str7=new String("123Hello");//new是强制操作，只有使用字面量才会使用
		//常量池中已有的常量
		
		int index1=str7.indexOf("");//0
		int index2=str7.indexOf('1');//0
		/**输出0，0*/
		System.out.println(index1+","+index2);
	}
}
package day01;

public class StringDemo2 {
	/**
	 * 检查回文
	 */
	public static void main(String[] args) {
		String str="上海自来水来自海上";
		int len=str.length();
		for (int i = 0; i < len/2; i++) {
			if(str.charAt(i)!=str.charAt(len-1-i)){
				System.out.println("不是回文");
				return;
			}
		}
		System.out.println("是回文");
	}
	
			

}
package day01;
/**
 * 频繁修改常用的StringBulder,StringBuffer的方法完全相同
 * @author wu.jielin
 *
 */
public class StringBuilderDemo {
	public static void main(String[] args) {
		StringBuilder builder=new StringBuilder("努力学习，");
		
		//追加
		builder.append("为了改变生活");
		System.out.println(builder);
		
		//删除7 8
		builder.delete(7, 9);
		System.out.println(builder);
		
		//插入到7，原7位及以后顺延展
		builder.insert(7, "改变");
		String str=builder.toString();
		System.out.println(str);
		
		//回文检查可以用reverse
		String s="上海自来水来自海上";
		StringBuilder strb=new StringBuilder(s);
		String s1=strb.reverse().toString();
		System.out.println(s.equals(s1));
	}

}
package day02;
/**
 * 一句话概括多态：父类或接口引用接收子类对象
 * 人类思维：看见一个新老师，以一个人（多态方式）看待其很好奇，知道其
 * 为新老师后可以问他学习问题！
 * 
 * @author wu.jielin
 * 
 *虚方法调用就是调用父类引用类型的方法，执行子类的方法。
 */
public class Object1 {
	/**
	 * object翻译过来是对象，但用中文其实就是东西，
	 * 一切是都是对象其实就是一切都是东西一切都是object
	 * 一切东西都有共性，即object中定义的属性和行为
	 * 
	 * toString()
	 * 
	 */
	public static void main(String[] args) {
		Object1  obj1=new Object1();
		System.out.println(
				//重写的： this is Object1's override toString()
				obj1.toString());//不重写day2.Object1@15db9742
	}
	public String toString(){
		return "this is Object1's override toString()";
	}
	
}
package day02;
/**
 * 身份证号验证
 * @author wu.jielin
 *
 */
public class Regular2 {
	/**
	 * 15位或者18位，先从相同点开始
	 * \d{15}(\d{2}[0-9xX])?
	 * \d=[0-9]
	 * 
	 */
	public static void main(String[] args) {
		String regex="\\d{15}(\\d{2}[\\dxX])?";
		String id="182384928328485382";
		boolean match =id.matches(regex);
		System.out.println(match);
	}

}
package day02;

public class Object2 {
	/**
	 * println(Object obj)传入的参数都将作为object调用toString()方法
	 * @param args
	 */
	public static void main(String[] args) {
		StringBuilder builder
			=new StringBuilder("lalala");
		System.out.println(builder);
		//lalala,Stringbuilder重写了toString() 
	
	/**
	 * == 比较值引用类型存的值就是地址,即是否为同一对象
	 * equals()比较内容(属性)，但是需要重载,object的equals仅仅是调用==
	 */
	System.out.println();
	}
	public boolean equals(Object obj){
		if(obj==null){
			return false;
		}
		if(obj==this){
			return true;
		}
		if(obj instanceof Object2){
			//比较逻辑
		}
		return false;
	}
}
package day02;

public class Regular4replaceAll {
	/**
	 * replaceAll负责将字符串中满足正则表达式的部分替换成
	 * 输出值
	 * 
	 */
	public static void main(String[] args) {
		String str="ghjk345t6yfghj56789";
		System.out.println(
				str.replaceAll("\\d+", "RR")//ghjkRRtRRyfghjRR
				);
		
		String message="wqnmlgb!你怎么这么sb!cnm!你个djb";
		System.out.println(
				new Regular4replaceAll().hh(message));
				//和谐!你怎么这么和谐!和谐!你个和谐
	}
	/**
	 * 和谐用语
	 */
	public String hh(String s){
		String regex
			="(wqnmlgb|cnm|dsb|rnmb|sb|db|djb)";
		return s.replaceAll(regex, "和谐");
	}

}
package day02;
/**
 * 包装类除了int为Integer，char为Character其他均为基本类型首字母大写
 * 包装类都是final的,均继承自Number抽象类
 * @author wu.jielin
 *
 */
public class IntegerDemo {
	public static void main(String[] args) {
		/**
		 * 穿马甲//主要用于参与面向对象
		 * 自动包装需要注意的是，和String常量池类似，
		 * 从-127到128的Integer已经做好，直接取用，
		 * 因此自动包装的
		 * 这个范围的Integer的对象只有一个
		 * 并且这是编译器干的活，在需要拆装的地方帮你做了操作
		 */
	int a=2;
	//手动包装
	Integer i=new Integer(a);
	Integer i1=new Integer(2);
	Character ch=new Character('a');
	Boolean b=new Boolean(true);
	//自动包装
	Integer i2=a;
	
	/**
	 * 脱马甲
	 */
	int a2=i.intValue();
	
	
	}
}
package day02;
/**
 * Number类中相关方法
 * @author wu.jielin
 *
 */
public class NumberDemo {
	public static void main(String[] args) {
		
	/**
	 * 可以**Value成别的类型，一般只转为本类型
	 */
	Double d= new Double(1.2);
	double dd=d.doubleValue();
	int i=d.intValue();
	short s=d.shortValue();
	byte b=d.byteValue();
	
	System.out.println(dd+" "+i+" "+s+" "+b);
	/**
	 * 1.2 1 1 1
	 */
	}
}
package day02;

public class Regular1 {
	/**
	 * 正则表达式只关注格式
	 */
	public static void main(String[] args) {
		/**
		 * 邮箱地址 [a-zA-Z0-9_\.]+@[a-zA-Z0-9_]+(\.[a-zA-Z0-9]+)+
		 */
		// '\'在java中为转义字符,.在java中没有特殊含义，
		// \.表示转义但java并不支持转义，所以报错
		// \\表示的是字符\，从而
		//
		String regex = "[a-zA-Z0-9_\\.]+@[a-zA-Z0-9_]+(\\.[a-zA-Z0-9]+)+";
		String mail="wu.jielin@oe.21.vianet.com";
		
		//字符串matches方法测试字符串是否满足传入正则。
		//但是matches方法只支持全匹配，即从头到尾，也即默认开头^结尾$
		boolean match=mail.matches(regex);
		System.out.println(match);//true
	}
}
package day02;

public class Regular3split {
	/**
	 * split方法用于 以满足正则表达式的字符串为点
	 * 切字符串，形成的字符串片段作为字符串数组返回
	 */
	
	public static void main(String[] args) {
		String str="boss jack   marry      jackson";
		String[] array=str.split("\\s+");
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
		
	}

}
package day02;

public class Regular0 {
	public static void main(String[] args) {
		//第一个汉字的unicode码值
		char c='\u4e00';
		//最后一个汉字的unicode码值
		char c1='\u9fa5';
		/**
		 * 输出所汉字
		 */
		int count=0;
		for (char c2='\u4e00',i=0; c2 <='\u9fa5'; c2++,i++) {
			count++;
			if(i%50==0){
				System.out.println("\n");
			}
			System.out.print(c2);
		}
		System.out.println(count);//20902个汉字
	}
}
package day03;
import java.util.Date;
/**
 * Date内部维护一个从纪元（epoch）到目前的毫秒值，long值
 * 纪元：UTC 1970年1月1日 00：00：00
 * Date内部大部分方法已经被Calendar取代
 * 请记住getTime() & setTime() 方法
 * @author wu.jielin
 */
public class DateDemo {
	public static void main(String[] args) {
	Date date=new Date();//默认当前系统时间
	System.out.println(date);//Fri Sep 08 13:28:51 CST 2017
	/**
	 * 获取Date内部的long值
	 */
	long time=date.getTime();
	System.out.println(time);//1504848817659
	//设置时间
	/**
	 * 给定毫秒数设置long值，即设置时间
	 */
	date.setTime(0);
	System.out.println(date);//Thu Jan 01 08:00:00 CST 1970
	System.out.println(new Date(new Date().getTime()
			+24*60*60*1000));//Sat Sep 09 13:46:46 CST 2017
	}
}
package day03;

import java.text.SimpleDateFormat;//text包下
import java.util.*;

/**
 * 根据一个特殊的格式是的友好时间字符串与date之间相互转换
 * 构造方法接受一个格式串，format方法返回友好字符串
 * y年M月d日m分s秒E星期
 * a  AM或PM标识
 * H 小时（24h制）
 * h 小时（12h制）
 * @author wu.jielin
 *yyyy-MM-dd HH:mm:ss
 */
public class SimpleDateFormatDemo0 {
	public static void main(String[] args) {
		Date date=new Date();//系统时间
		System.out.println(date);//默认字符串
		/**
		 * 输出格式
		 */
		String dateFormat
			="yyyy-MM-dd HH:mm:ss a EEE";
		SimpleDateFormat sdf =new SimpleDateFormat(dateFormat);
		String str=sdf.format(date);
		System.out.println(str);//2017-09-08 14:18:19 下午 星期五
		
	}

}
package day03;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 使用sdf字符串转为Date
 * 用格式去初始化sdf，用sdf去parse日期字符串
 * parse 为Sat Sep 09 14:00:00 CST 2017 
 * @author wu.jielin
 *
 */
public class SimpleDateFormatDemo1 {
	public static void main(String[] args) throws ParseException {
		String str="2017-09-09 14:00:00";
		String format="yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		Date date=sdf.parse(str);
		System.out.println(date);
	}
}
package day03;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * 计算生命值
 * @author wu.jielin
 *
 */
public class SimpleDateFormatDemo2 {
	public static void main(String[] args) throws ParseException {
		/**
		 * 输入生日，算出今天活了多少天
		 */
		Date date =new Date();//系统时间
		long now=date.getTime();
		Scanner scanner=new Scanner(System.in);
		String birthDay=scanner.nextLine();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		long birth=sdf.parse(birthDay).getTime();
		int days=(int)((now-birth)/(24*60*60*1000));//编译器会优化乘法部分
		System.out.println("活了"+days+"天");
	}
	

}
package day03;

import java.util.Calendar;
import java.util.Date;

/**
 * 抽象类，使用广泛的是GregorianCalendar类
 * getInstance（）默认获取当前系统时间的gregorian历法时间
 * @author wu.jielin
 *
 */
public class CalendarDemo0 {
	public static void main(String[] args) {
		//获取gregorian历法系统时间
		//从calendar到date
		Calendar calendar=Calendar.getInstance();
		Date date=calendar.getTime();//与Date返回long值不同，这里返回Date
		System.out.println(date);//Fri Sep 08 14:55:27 CST 2017
		
	    //从date转为calendar
		Date date1= new Date();
		/**
		 * 用Calendar表示Date所表示的时间
		 */
		calendar.setTime(date1);
		
	}

}
package day03;

import java.util.Calendar;
import java.util.Date;

/**
 * 使用Calendar设置时间方法
 * @author wu.jielin
 *
 */
public class CalendarDemo1 {
	public static void main(String[] args) {
		Calendar calendar =Calendar.getInstance();
		/**
		 * 表示2017-08-08 15：00：03
		 * hour_of_day是24进制HOUR是12进制
		 */
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH,Calendar.AUGUST);
		calendar.set(Calendar.DAY_OF_MONTH, 8);//Date和Day_Of_Month等价
		calendar.set(Calendar.HOUR_OF_DAY,15);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 3);
		Date date=calendar.getTime();
		System.out.println(date);//Tue Aug 08 15:00:03 CST 2017
	}
}
package day03;

import java.util.Calendar;

/**
 * 获取Calendar中某个时间分量的值
 * int get(int field)根据给定时间分量获取其值
 * Calendar.MONTH和Calendar.WEEK_OF_DAY特殊
 * MONTH Jan1 ...DEC11 因此get回来int要+1
 * weekOfDay sunday1,Monday2...Saturday7 一次get回来int要-1
 * @author wu.jielin
 *
 */
public class CalendarDemo2 {
	public static void main(String[] args) {
		Calendar calendar=Calendar.getInstance();
		int year=calendar.get(Calendar.YEAR);
		//calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		int month = calendar.get(Calendar.MONTH)+1;
		int weekDay=calendar.get(Calendar.DAY_OF_WEEK)-1;
		System.out.println(month+"月");
		System.out.println("星期"+(weekDay==0?7:weekDay));
		
	}
}
package day03;

import java.util.Calendar;

/**
 * 使用Calendar计算时间
 * void add(int field, int days)
 * 一般加天数用DAY_OF_YEAR
 * @author wu.jielin
 *
 */
public class CalendarDemo3 {
	public static void main(String[] args) {
		Calendar calendar=Calendar.getInstance();
		//calendar.add(Calendar.DAY_OF_YEAR, 15);
		/**
		 * 计算3个月后10天那周的周三是哪一天
		 * 注意，到了那一周时，再设置周三就成了那一天
		 */
		calendar.add(Calendar.MONTH, 3);
		calendar.add(Calendar.DAY_OF_YEAR, 10);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		System.out.println(calendar.getTime());
		
	}
}
package day03;

import java.util.Calendar;

/**
 * 获取当前年，月的天数
 * @author wu.jielin
 *
 */
public class CalendarDemo4 {
	public static void main(String[] args) {
		Calendar calendar=Calendar.getInstance();
		int days=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println(days);//当月多少天30
		days=calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
		System.out.println(days);//当年多少天365
	}
}
package day03;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * 需求 输入日期，计算3年3月3天后的周3是哪一天
 * 1 创建Scanner
 * 2 获取用户输入日期
 * 3 创建SimpleDateFormat
 * 4 字符串-->Date
 * 5 Date-->Calendar
 * 6 计算时间
 * 7 Calendar-->Date
 * 8 Date-->字符串
 * 9 输出
 * @author wu.jielin
 *
 */
public class CalendarDemo5 {
	public static void main(String[] args) throws ParseException {
		Scanner scanner=new Scanner(System.in);
		System.out.println(""
				+ "请输入日期，格式yyyy-MM-dd");
		String dateStr=scanner.nextLine();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=sdf.parse(dateStr);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, 3);
		calendar.add(Calendar.MONTH, 3);
		calendar.add(Calendar.DAY_OF_YEAR, 3);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		date=calendar.getTime();
		System.out.println(sdf.format(date));
	}
}
package day03;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * 计算保质期
 * 
 * @author wu.jielin
 *
 */
public class Test {
	/**
	 * output 
	 * Pls input 生产日期 2017-9-9 
	 * 输入保质期天数 60 
	 * 2017-11-08
	 * 
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Pls input 生产日期");
		String str = scanner.nextLine();
		System.out.println("输入保质期天数");
		int day = Integer.parseInt(scanner.nextLine());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf.parse(str);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		calendar.add(Calendar.DAY_OF_YEAR, day);
		String expiredDate = sdf.format(calendar.getTime());
		System.out.println(expiredDate);
	}

}
String------------>Date------------->Calendar
	  sdf.parse()       calendar.setTime()
----------------->calendar------------->Date--------->String
计算calendar.add()       calendar.getTime()  sdf.format()                
package day04;
/**
 * Contains()是基于equals的，
 * Set和List的重复与否也是基于equals的
 * collection接口规定方法还有size(),isEmpty(),clear()
 * @author wu.jielin
 *
 */
public class CellectionDemo0 {
	
	
	public static void main(String[] args) {
		Integer in1= new Integer(1);
		Integer in2=new Integer(1);
		System.out.println(in1.equals(in2));//integer的equals重写了
		System.out.println(in1==in2);
		Integer in3=1;
		Integer in4=1;
		System.out.println(in3==in4);//true
		
	}

}
package day04;
import java.util.*;
/**
 * 集合的批量处理方法
 * 方法摘要：addAdll(),containsAll()
 * @author wu.jielin
 *
 */
public class CollectionDemo1 {
	public static void main(String[] args) {
		Collection conn1=new ArrayList();
		conn1.add("java");
		conn1.add(".net");
		conn1.add("c#");
		conn1.add("php");
		System.out.println(conn1);
		Collection conn2=new ArrayList();
		conn2.add("andriod");
		conn2.add("ios");
		System.out.println(conn2);//collection的toString()重写了
		/**
		 * boolean addAll(Collection c),若添加之后集合数量发生了变化，则返回true
		 */
		conn1.addAll(conn2);
		System.out.println(conn1);
        //[java, .net, c#, php]  [andriod, ios]  [java, .net, c#, php, andriod, ios]
		
		Collection conn3=new ArrayList();
		conn3.add("java");
		conn3.add("ios");
		conn3.add("c++");
		/**
		 * containsAll()必须每一个都包含才返回true，同contains一样基于equals比较
		 */
		System.out.println(conn1.containsAll(conn3));//false
		

		
	}
}
package day04;

import java.util.*;

/**
 * 删除元素,remove()方法根据equals比较方法删除元素，
 * 并且remove只删除第一次碰到的equals为true的元素
 * @author wu.jielin
 *
 */
public class CollectionDemo2 {
	public static void main(String[] args) {
		Collection conn=new ArrayList();
		conn.add("java");
		conn.remove("java");
		System.out.println(conn);//[]
	}
}
package day04;
import java.util.*;
/**
 * foreach,只能用来遍历。不能代替传统循环
 * 其实是在编译器编译时将其转为迭代器存入class文件
 * 因此也不能在遍历过程中去修改元素
 * @author wu.jielin
 *
 */
public class ForeachDemo {
	public static void main(String[] args) {
		Collection c=new ArrayList();
		c.add("one");
		c.add("#");
		c.add("two");
		c.add("#");
		c.add("three");
		c.add("#");
		c.add("four");
		for(Object obj:c){//循环次数由元素个数决定
			String str=(String)obj;
			System.out.println(str);
			if("#".equals(str)){
				System.out.println("遍历过程中不能修改");
			}
			
		}
		
		
	}
}
package day04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 取集合元素，迭代器,遍历集合.是一个接口，统一的方式遍历集合
 * 遍历过程  问‘取、删（删除不是必要的）
 * 问：boolean hasNext()
 * @author wu.jielin
 *
 */
public class IteratorDemo {
	public static void main(String[] args) {
		Collection<String> c=new ArrayList<String>();
		c.add("a");
		c.add("b");
		Iterator it=c.iterator();
		while(it.hasNext()){
			Object obj=it.next();
			String str=(String)it.next();
			System.out.println(str);
		}
		/**
		 * 迭代器要求在遍历过程中不可以通过集合的方法改变集合元素
		 * 使用equals时尽量使用字面量.equals避免空指针异常
		 */
		c.clear();
		System.out.println(c);
		c.add(null);
		c.add(null);
		c.add("nullString");
		Iterator i=c.iterator();
		while(i.hasNext()){
			Object o=i.next();
			if(o==null){
				//c.remove(o);
				i.remove();//删除遍历到的元素
			}
		}
		System.out.println(c);
	}
}
package day04;
import java.util.*;
/**
 * 集合中的泛型用来定义集合中元素的类型，所有集合都支持泛型
 * 即使集合未设置泛型，迭代器设置正确泛型也能遍历
 * @author wu.jielin
 *
 */
public class TypeDemo {
	public static void main(String[] args) {
		Collection<String> c=new ArrayList<String>();
		c.add("one");
		c.add("two");
		/*for (String string : c) {//取出来就是String了
			System.out.println(string);
		}*/
		Iterator<String> it=c.iterator();
		while(it.hasNext()){
			String str=it.next();
			System.out.println(str);
		}
	}

}
package day04;

import java.util.*;

/**
 * List集合，通常是有序集，特点是可以根据下表操作元素.List的方法 T get(int index)
 * T set(int index,T t)将t设置到index上，返回值为原位置上的元素
 * @author wu.jielin
 *
 */
public class ListDemo0 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");

		String str = list.get(3);
		System.out.println(str);// four
		for (int i = 0; i < list.size(); i++) {

			String str1 = list.get(i);
			if ("two".equals(str1)) {
				String old=list.set(i, "hhh");
			}
		}
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			String string = iterator.next();
			System.out.println(string);
		}

	}
}
package day04;
import java.util.*;
/**
 * List方法，插入与删除
 * void add(int index,T t),将t设置到index上，原位置及以后均向后移动
 * 为插入操作
 * @author wu.jielin
 *
 */
public class ListDemo1 {
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
		System.out.println(list);
		list.add(0, "zero");
		System.out.println(list);
		//[one, two, three, four]
		//[zero, one, two, three, four]
		/**
		 * E remove(int index)把index位置上的元素删除，返回值为刚才删除的元素
		 */
		System.out.println(list.remove(0));//返回被删除的zero

	}
}
package day04;

import java.util.*;

/**
 * 获取子集
 * sublist引用的是list一样的内存，因此改变sublist也会改变原有list
 * @author wu.jielin
 *
 */
public class ListDemo2 {
	public static void main(String[] args) {
		List<Integer> list=new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			list.add(i);
		}
		System.out.println(list);
		List sublist=list.subList(3, 8);
		System.out.println(sublist);//[3,4,5,6,7]
		
	}
}
package day04;

import java.util.*;

/**
 * 数组可以转化成List，但是不能转换成Set，原因set不能放重复元素
 * 转为的list是只读的。需要new ArrayList<String>(Collection list) 方可操作。
 * @author wu.jielin
 *
 */
public class ArrayToListDemo {
	public static void main(String[] args) {
		String[] array ={"one","two","three"};
		List<String> list=Arrays.asList(array);
		//list.add("four");//报错
		
		List<String> list1= new ArrayList<String>();
		list.addAll(list);
		
		List<String> list2=new ArrayList<String>(list);
	}
}
package day04;

public class Cell implements Comparable<Cell> {
	private int x;
	private int y;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	/**
	 * 比较大小，返回不关注具体的值。只关注取值范围。
	 * 返回值<0，当前对象比给定的对象小
	 * =0 相等
	 * 返回值>0，当前对象比给定的对象大
	 */
	public int compareTo(Cell o) {
	return this.x<o.x?-1:
		this.x==o.x?0:1;
	//return this.x-o.x;
	}

}
package day04;
import java.util.*;
/**
 * 自定义比较器解决默认中文等按着unicode码比较的没有意义的比较
 * 就是已经实现了的比较规则不适用
 * @author wu.jielin
 *
 */
public class CollectionSortDemo2 {
	public static void main(String[] args) {
		List<String	> list=new ArrayList<String>();
		list.add("吴琳");
		list.add("张力凡");
		list.add("何浪hhhh");
		list.add("小");
		list.add("吴轻微");
		System.out.println(list);//[吴琳, 张力凡, 何浪hhhh, 小, 吴轻微] 默认根据unicode比较
		Collections.sort(list, new MyComparator());
		System.out.println(list);//[小, 吴琳, 张力凡, 吴轻微, 何浪hhhh] 自定义根据字符数量比较
	}
}
/**
 * 自定义比较器
 * 实现Comparator<T>接口，覆盖compare方法
 * @author wu.jielin
 *
 */
class MyComparator implements Comparator<String> {
	public int compare(String s1,String s2) {
		return s1.length()-s2.length();
	}

}
package day04;

import java.util.*;

/**
 * 集合排序
 * Collections类工具的使用,Collections.sort(List<T> list)
 * @author wu.jielin
 *
 */
public class CollectionSortDemo0 {
	public static void main(String[] args) {
		List<Integer> c=new ArrayList<Integer>();
		Random rand=new Random(47);
		for(int i=0;i<10;i++){
			c.add(rand.nextInt(100));
		}
		System.out.println(c);
		Collections.sort(c);
		System.out.println(c);
	}
}
package day04;
import java.util.*;
/**
 * Collection--->toArray() //c.toArray(new String[c.size]);
 *  Arrays.asList(T... a),数组只能转为List
 * @author wu.jielin
 *
 */
public class CollectionToArrayDemo {
	public static void main(String[] args) {
		Collection<String> c=new ArrayList<String>();
		c.add("one");
		c.add("two");
		c.add("three");
		
//		Object[] array=c.toArray();  不常用
		//new String[size] 数组size如果小于collection的size，会自己给定数组大小
		String[] array=c.toArray(new String[c.size()]);
		for (String string : array) {
			System.out.print(string);
		}
		
		
	}
}
package day04;
import java.util.*;
/**
 * 使用Collections的sort方法必须要元素实现Comparable接口
 * String的comparableTo()方法已经重写
 * @author wu.jielin
 *
 */
public class CollectionSortDemo1 {
	public static void main(String[] args) {
		List<Cell> list=new ArrayList<Cell>();
		list.add(new Cell(1,2));
		list.add(new Cell(2,4));
		list.add(new Cell(3,2));
		list.add(new Cell(0,2));
		list.add(new Cell(3,8));
		Collections.sort(list);
		System.out.println(list);
		
		List<String> list1=new ArrayList<String>();
		list1.add("Tom");
		list1.add("Jack");
		list1.add("Mary");
		list1.add("Jason");
		Collections.sort(list1);
		System.out.println(list1);
		list1.add("苍#null");
		System.out.println(list1);//[Jack, Jason, Mary, Tom, 苍#null]
	}
}



package day04;
import java.util.*;
/**
 * 当需要使用一个继承或者实现接口的对象，且只用一次，使用匿名内部类的形式创建对象当炮灰
 * 如:Conllections.sort(list,new Comparator<T>(){});
 * @author wu.jielin
 *
 */
public class CollecionsSortTest {
	public static void main(String[] args) {
		List<Emp> emps=new ArrayList<Emp>();
		emps.add(new Emp("Terry",26,6000,'男'));
		emps.add(new Emp("Aleen",23,9000,'女'));
		emps.add(new Emp("Smith",45,9830,'男'));
		emps.add(new Emp("Ketty",23,1244,'女'));
		
		Collections.sort(emps, 
				new Comparator<Emp>(){
			public int compare(Emp emp1,Emp emp2){
				return emp1.name.compareTo(emp2.name);
			}
		});
		System.out.println(emps);
	}
}

class Emp{
	public String name;
	public int age;
	public char gender;
	public int salary;
	public Emp(String name,int age,int salary,char gender){
		this.name=name;
		this.age=age;
		this.salary=salary;
		this.gender=gender;
	}
	
	public String toString(){
		return "name:"+this.name+", gender:"+this.gender+", age:"+this.age+", salary:"+this.salary;
	}
}
package day05;
import java.util.*;
/**
 * 接口java.util.Queue队列，保存一组数据，FIFO.
 * 常用方法offer,poll,peek
 * 遍历队列是一次性的
 * @author wu.jielin
 */
public class QueueDemo {
	/**
	 * boolean offer入队,将元素添加至队尾，添加成功返回true
	 * 永远只能获取队首元素，使用T poll出队方法,获取后该队列不再包含该元素
	 * T peek获取队首，但remove改元素
	 * @param args
	 */
	public static void main(String[] args) {
		Queue<String> queue=new LinkedList<String>();
		queue.offer("A");
		queue.offer("B");
		queue.offer("C");
		queue.offer("D");
		System.out.println(queue);
		String str=queue.poll();
		System.out.println(str);//A
		System.out.println(queue);//[B,C,D]
		String str1=queue.peek();
		System.out.println(str1+"\n"+queue);
		//遍历队列
		while(queue.size()>0){
			String string=queue.poll();
			System.out.println(string);
		}
		System.out.println(queue);//[]
		
	}
}
package day05;
/**
 * 需要在重写equals方法时重写hashCode方法，
 * 重写规则：
 * 若equals比较为true，hashCode值必须相同，反之不是必须的但也应尽量避免。否则影响HashMap性能。
 * hashCode方法在当前对象内容没有发生改变的前提下多次调用应当返回相同的数字,即为稳定值。内容发生改变，hashCode可以改变.
 * source中复equals和hashCode的选项需要那几个equals的就选哪几个属性
 * @author wu.jielin
 *
 */
public class HashCodeDemo {
	private int x;
	private int y;
	public HashCodeDemo(int x,int y){
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HashCodeDemo other = (HashCodeDemo) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
package day05;

import java.util.*;

/**
 * 接口Map,多行两列的表格，每一条数据包含两个信息key--value 其中key在Map中不允许重复。重复值得是equals等于true
 * 常用方法put,get,containsKey,remove
 * 可以设置初始容量，默认16
 * 
 * @author wu.jielin
 *
 */
public class MapDemo0 {
	/**
	 * V put(K k, V v) 若key不存在，添加新内容。若key已存在则替换，并返回被替换的内容 get给定不存在的key返回null
	 * HashMap,先调用key的hashCode方法算key的hashCode，得到一个整数，然后将key-value放在数组第几位。因此尽量避免hashCode值比较和equals比较结果相同。
	 * 并且hashMap内部数组每一位存的是一个LinkedList
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("语文", 80);
		map.put("数学", 90);
		map.put("英语", 93);
		map.put("物理", 92);
		map.put("化学", 90);
		System.out.println(map.put("生物", 83));// null
		System.out.println(map);// {物理=92, 生物=83, 数学=90, 化学=90, 语文=80, 英语=93}
		System.out.println(map.put("语文", 99));// 80
		System.out.println(map);// {物理=92, 生物=83, 数学=90, 化学=90, 语文=99, 英语=93}

		int chinese = map.get("语文");
		System.out.println(chinese);// 99
		Integer dance = map.get("舞蹈");// int
										// dance=map.get("舞蹈")会报错，编译器会将其变为null.intValue()
		System.out.println(dance);// null

		if (map.containsKey("语文")) {
			System.out.println("包含语文");
			int value = map.remove("语文");// 返回被删除的value，没有语文项则返回null
			System.out.println(value);
		}
		System.out.println(map);// {物理=92, 生物=83, 数学=90, 化学=90, 英语=93}
	}
}
package day05;
import java.util.*;
/**
 * 栈，FILO，用于一组操作可追溯
 * 常用方法：入栈push;出栈pop,peek
 * 栈的遍历也是一次性的，
 * @author wu.jielin
 *
 */
public class StackDemo {
	public static void main(String[] args) {
		Deque<String> stack=new LinkedList<String>();//Deque实现了Queue
		/**
		 * void push(T t)入栈
		 * T pop();去除栈顶元素，取出后该元素被stack removed
		 */
		stack.push("A");
		stack.push("B");
		stack.push("C");
		stack.push("D");
		System.out.println(stack);
		
		String str=stack.pop();
		System.out.println(str);//D
		
		String string=stack.peek();
		System.out.println(string);//C
		System.out.println(stack);//[C,B,A]
		
		while(stack.size()>0){
			String s=stack.pop();
			System.out.println(s);
		}
		System.out.println(stack);//[]
	}
}
package day05;
import java.util.*;
import java.util.Map.Entry;
/**
 * 遍历map的三种方式：
 * 1： 利用keySet遍历所有的key 2：使用entrySet遍历所有的键值对 3：使用values遍历所有的value（不常用）
 * note:Entry=key-value
 * values返回的是一个集合
 * @author wu.jielin
 *
 */
public class MapDemo1 {
	public static void main(String[] args) {
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("语文", 80);
		map.put("数学", 90);
		map.put("英语", 93);
		map.put("物理", 92);
		map.put("化学", 90);
		//使用Set<T> keySet遍历所有的key,遍历该集合就等于遍历了所有的key了
		Set<String> keys=map.keySet();
		for (String string : keys) {
			System.out.println("key:"+string);
		}
		//使用entrySet遍历key-value。Set<Entry<K k,V v>> entrySet()
		Set<Entry<String,Integer>> set=map.entrySet();
		for (Entry<String, Integer> entry : set) {
			String s=entry.getKey();
			int v=entry.getValue();
			System.out.println("key:"+s+"-value:"+v);
		}
		//使用values方法遍历所有value,value是可以从重复的，返回set会丢数据
		Collection<Integer> values=map.values();
		for (Integer integer : values) {
			System.out.println(integer);
		}
	}

}
package day05;
import java.util.*;
/**
 * hashCode对Map操作的影响
 * 使用HashMap是应当注意，作为key的对象：1 首先当key这个类重写了equals方法，那么重写hashCode方法必须满足。2 作为key的对象若存入HashMap后，其会影响hashcode值
 * 的内容不要发生变化，否则会找不到该key--value
 * 解释，各种操作如get都是先算hashcode使用再比较equals。
 * @author wu.jielin
 *
 */
public class HashCodeDemoTest {
	public static void main(String[] args) {
		Map<HashCodeDemo,Integer> map=new HashMap<HashCodeDemo, Integer>();
		HashCodeDemo h1=new HashCodeDemo(1,2);
		map.put(h1,1);
		map.put(new HashCodeDemo(2,3),2);
		map.put(new HashCodeDemo(3,4),3);
		map.put(new HashCodeDemo(2,2),14);
		map.put(new HashCodeDemo(1,4),23);
		map.put(new HashCodeDemo(1,5),32);
		h1.setX(10);
		map.put(h1, 11);//两个相同的对象作为了两个key
		System.out.println(map);
		System.out.println(map.get(h1));//11
//		h1.setX(1);
//		System.out.println(map.get(h1));//1
		Set<HashCodeDemo> set=map.keySet();
		System.out.println(set);
	}
}
TreeSet是有序的
LinkedHashMap是有序的

CPU----内存---硬盘
厨师---配菜---库房
package day05;
import java.io.*;
/**
 * java.io.File,穿件文件或者目录对象，获取其周边信息
 * get...
 * exists判断文件或目录是否真实
 * long length()获取文件占多少字节
 * lastModified, isFile
 * @author wu.jielin
 *
 */
public class FileDemo0 {
	public static void main(String[] args) {
		File file=new File("."+File.separator+"demo.txt");
		//获取文件或目录名
		String name=file.getName();
		System.out.println(name);
		//真实性测试
		boolean isExist=file.exists();
		System.out.println(isExist);//true
		//获取大小
		long length=file.length();
		System.out.println(length);//16
		
		boolean read=file.canRead();//可读不
		file.canWrite();
		file.canExecute();
		
		long time=file.lastModified();//最后修改时间
		//判断是否是文件
		boolean isfile=file.isFile();
		System.out.println("是文件："+isfile);//是文件:true
	}
}
package day05;
import java.util.*;
/**
 * 统计字符数
 * @author wu.jielin
 *
 */
public class MapTest {
	public static void main(String[] args) {
		String string="good good study, day day up university";
		Map<Character,Integer> map=new LinkedHashMap<Character,Integer>();
		for(int i=0;i<string.length();i++){
			Character ch=string.charAt(i);
			if(map.containsKey(ch)){
				map.put(ch, map.get(ch)+1);
			}else{
				map.put(ch, 1);
			}
		}
		System.out.println(map);
				
	}

}
package day05;
/**
 * 20行代码以下，从1累加，每累加一次输出一次结果，到5050结束。
 * 不得使用for if while等关键字。
 * @author wu.jielin
 *
 */
public class hhh {
	static int sum,i=0;
	public static boolean add(int i){
		sum+=i;
		System.out.println(sum);
		i++;
		return sum==5050?true:add(i);
	}
	public static void main(String[] args) {
		add(i);
		System.out.println(sum);
	}
}
package day05;

import java.io.*;

/**
 * FIle[] listFiles(),返回抽象路径名数组。必须是目录，否则返回nul或者报io错
 * 即获取子项信息
 * @author wu.jielin
 *
 */
public class FileDemo2 {
	public static void main(String[] args) {
		//查看当前项目根目录下的所有子项
		File dir=new File(".");
		if(dir.isDirectory()){
			//获取文件名字符串数组
			String[] fs=dir.list();
			for (String string : fs) {
				System.out.println(string);
			}
			
			//获取抽象路径File数组
			File[] files=dir.listFiles();
			for (File file : files) {
				String name=file.getName();
				long length=file.length();//目录没有长度
				System.out.println("name:"+name+" length:"+length);
			}
		}
		
	}
}
package day05;

import java.io.File;
import java.io.FileFilter;

/**
 * listFiles支持文件过滤器，FileFilter接口。
 * 实现该接口的方法boolean accept(FIle f)
 * 定义过滤条件，并将当前目录下满足条件的子项返回
 * @author wu.jielin
 *
 */
public class FileDemo3 {
	public static void main(String[] args) {
		File dir=new File("C:\\Users\\wu.jielin"
				+File.separator+"Desktop"+File.separator+"a");
		if(dir.isDirectory()){
			File[] subs=dir.listFiles(new MyFilter());
			for (File file : subs) {
				System.out.println(file.getName());
			}
		}
	}
}
/**
 * 过滤器
 * @author wu.jielin
 *
 */
class MyFilter implements FileFilter{

	@Override
	public boolean accept(File pathname) {
		String name=pathname.getName();
		return name.endsWith(".txt");
	}
	
}
package day05;

import java.io.*;

/**
 * 使用File操作文件或目录
 * note:要创建多级目录（不存在）下的文件，
 * 需要先创建多级目录(mkdirs)再createNewFile
 * 
 * @author wu.jielin
 *
 */
public class FileDemo1 {
	public static void main(String[] args) throws IOException {
		// 在当前根目录下创建文件
		File file = new File("love.txt");
		if (!file.exists()) {
			file.createNewFile();
			System.out.println("创建文件成功");
		}
		// 删除
		if (file.exists()) {
			file.delete();
			System.out.println("deleted");
		}
		// 创建目录
		File dir = new File("demo");
		if (!dir.exists()) {
			dir.mkdir();
			System.out.println("demo directery creaed");
		}
		// 判断是否目录
		boolean isDir = dir.isDirectory();
		// 删除目录
		if (dir.exists()) {
			dir.delete();
			System.out.println("目录deleted");
		}
		//多级创建
		File newFile=new File("a/b/c/d.txt");
		newFile.mkdirs();
		if(newFile.exists()){
			System.out.println("a/b/c/d.txt 存在");
		}
		//删除
		newFile.delete();
		if(!newFile.exists()){
			System.out.println("a/b/c/d.txt dleeted");
		}
	}
}
package day05;

import java.io.File;

/**
 * 创建多级目录下的文件
 * @author wu.jielin
 *
 */
public class FileDemo4 {
	public static void main(String[] args) throws Exception {
		File file=new File("D:"+File.separator+"a"+
				File.separator+"/c/demo.txt");
		File parent=file.getParentFile();
		if(!parent.exists()){
			parent.mkdirs();
			file.createNewFile();
		}
	}

}
package day05;
import java.io.*;
/**
 * 删除目录
 * 只有空目录才能被删除
 * @author wu.jielin
 *
 */
public class DeleteDir {
	public static void main(String[] args) {
		File file=new File("D:"+File.separator+"cs file - copy");
		System.out.println(file.toString()+"  "+file.exists());
		//file.delete();//没用哒并且会锁定该目录
		deleteDir(file);
		System.out.println(file.exists());
		
	}
	
	public static void deleteDir(File dir){
		if(dir.isDirectory()){
			for (File file : dir.listFiles()) {
				deleteDir(file);
			};
		}
		dir.delete();
	}
}
package day06;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo1 {
	public static void main(String[] args) throws IOException {
		String path="D:/a/c/demo.txt";
		RandomAccessFile raf=new RandomAccessFile(path, "rw");
		/**
		 * 低八位有效，只读到低八位一个字节
		 * 读到-1即读到EOF(文件末尾)
		 */
		int i=raf.read();
		System.out.println(i);
		raf.close();
	}

}
package day06;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 可以方便的随机读写文件内容。
 * 基于指针当前位置读写,即总是在当前指针位置进行读写
 * RandomAccessFile raf=new RandomAccessFile(File file,String mode)  |
 * RandomAccessFile raf=new RandomAccessFile(String path,String mode)
 * 不存在的话会创建该文件
 * 方法void write(int n)写出n的低八位,只写一个字节
 * @author wu.jielin
 *
 */
public class RandomAccessFileDemo0 {
	public static void main(String[] args) throws IOException {
		String path="D:/a/c/demo.txt";
		RandomAccessFile raf=new RandomAccessFile(path, "rw");
		//Integer n=15;
		//一个字节最大255. 128 64 32 16 8 4 2 1 
		System.out.println(Integer.toBinaryString(0xff));//11111111
		//覆盖写
		raf.write(97);
		raf.close();
	}
}
计算机按照字符集读取二进制码
早期---ASCII码 一个字节表示一个字符
发展---GBK国标编码
	   GB2312支持繁体
互联网国际化---参考Unicode编码(一个子集)出来了UTF-8
		      utf-8里面中文三字节，英文一字节
note：java只支持unicode，即char两个字节，
		String str="我爱你"
		Bytes[] buff=str.getBytes("UTF-8")
	//  该方法找到每个字符在utf-8中找到每个字符的的编码，并返回三字节二进制码
package day06;

import java.io.*;

/**
 * 批量写出一组字节;
 * String的getBytes按照系统默认字符集转换为对应的字节
 * 
 * @author wu.jielin
 *
 */
public class RandomAccessFileDemo3 {
	public static void main(String[] args) throws IOException {
		RandomAccessFile rafw=
				new RandomAccessFile("C:/Users/wu.jielin/Pictures/1.txt","rw");
		String str="我爱张力凡小姐";
		byte[] buff=str.getBytes("utf-8");
		rafw.write(buff);
		rafw.close();
	}
}
package day06;
import java.io.*;
import java.util.Arrays;
/**
 * 读取一个字节数组的数据
 * int read(byte[] buf)
 * 尝试从文件中读取buf数组length个字节并从该数组第一个位置处存放实际读取的字节。
 * 返回实际读取到的字节数
 * @author wu.jielin
 *
 */
public class RandomAccessFileDemo4 {
	public static void main(String[] args) throws IOException {
		RandomAccessFile rafr=new RandomAccessFile("C:/Users/wu.jielin/Pictures/1.txt", "r");
		byte[] buf=new byte[50];
		int len=rafr.read(buf);
		rafr.close();
		System.out.println(len);
		System.out.println(Arrays.toString(buf));
		/**
		 * 从字节到字符串
		 */
		String str=new String(buf, "utf-8");
		System.out.println(str.trim());//我爱张力凡小姐
	}
}
package day06;
import java.io.*;
/**
 * 文件使用缓冲数组拷贝
 * note：循环读到内存的循环结束
 * @author wu.jielin
 *
 */
public class RandomAccessFileDemo5 {
	public static void main(String[] args) throws IOException {
		RandomAccessFile rafr=new RandomAccessFile(
				"C:/Users/wu.jielin/Pictures/90fba609e42716fb3d8e0a.jpg", "r");
		RandomAccessFile rafw=new RandomAccessFile(
				"C:/Users/wu.jielin/Pictures/lala.jpg", "rw");
		byte[] buff=new byte[100];
		int i;
		while((i=rafr.read(buff))!=-1){
			//避免最后一次只读了i个，却写出了i-1位置后面的上一次读到的被写出
			//从0读i个就够了
			rafw.write(buff,0,i);
		}
		rafr.close();
		rafw.close();
	}
}
package day06;
import java.io.*;
/**
 * 向文件中写入基本类型数据
 * @author wu.jielin
 *
 */
public class RandomAccessFileDemo6 {
	public static void main(String[] args) throws IOException {
		RandomAccessFile raf
			=new  RandomAccessFile("1.txt", "rw");
		int max=Integer.MAX_VALUE;
		System.out.println(max);
		//01111111 1111111 1111111 1111111
		/*
		raf.write(max>>>24);
		raf.write(max>>>16);
		raf.write(max>>>8);
		raf.write(max);
		*/
		raf.writeInt(max);
		
		raf.close();
		
		
//		System.out.println(i);
	}
}
package day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Temp {
	public static void main(String[] args) throws IOException {
		File file = new File("C:/Users/wu.jielin/Pictures/90fba609e42716fb3d8e0A.jpg");
		if (file.exists()) {
			System.out.println("CaseNotSensitive");
		}
		//测试读取int值
		RandomAccessFile raf = new RandomAccessFile("1.txt", "rw");
		int max=raf.readInt();
		System.out.println(max);
	}
}
package day06;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 看一个字节写一个字节。
 * 效率差，若想提高读写效率，就需要增加缓存，其作用是在读写数据时减少读写次数。
 * @author wu.jielin
 *
 */
public class RandomAccessFileDemo2 {
	public static void main(String[] args) throws IOException {
		RandomAccessFile rafr=new RandomAccessFile(//File name CaseNotSensitive
					"C:/Users/wu.jielin/Pictures/90fba609e42716fb3d8e0a.jpg", "r");
		RandomAccessFile rafw=new RandomAccessFile(
				"C:/Users/wu.jielin/Pictures/copy.jpg", "rw");
		int i;
		while(-1!=(i=rafr.read())){
			rafw.write(i);
		}
		rafr.close();
		rafw.close();
		
	}

}
randomAccessFile
的指针一个字节宽，进来默认指向第0字节
package day06;
import java.io.*;
/**
 * RAF总是基于当前指针所指向的位置就行读写操作，并且一次读写一个字节并向后移动
 * 若想从文件的某个地方读取字节需要先将指针移动到这个文职
 * void seek（long position），
 * int skipBytes(int n)尝试跳过n个字节，返回值为实际跳过的字节数
 * 只能向后，要向前就用seek
 * @author wu.jielin
 *
 */
public class RandomAccessFileDemo7 {
	public static void main(String[] args) throws IOException {
		RandomAccessFile raf=new RandomAccessFile("d:/a.txt", "rw");//没有会新建
		long p=raf.getFilePointer();
		System.out.println(p);//0
		raf.write(97);//写一个字节
		System.out.println(raf.getFilePointer());//1
		raf.writeInt(97);
		System.out.println(raf.getFilePointer());//5,文件5个字节，指针指向第五个字节
		//通过raf都一个字节
		System.out.println(raf.read());//-1,后面没有第六个字节了
		
		//移动指针位置
		raf.seek(0);
		System.out.println(raf.read());//97
		
		//位置1，skip2Byte ,指向第四个字节，read，读取第四个字节
		raf.skipBytes(3);
		System.out.println(raf.read());//97
	}

}
package day07;
/**
 * 
 * 按照流是否与特定地方相连分类
 * 节点流--低级流--->数据流方向明确,即最靠近源的流。
 * 处理流--高级流、过滤流--->不独立存在，通常用来处理另一个流，为了简化操作。
 * 处理流的构造方法总是需要传入另一个流。这种一个流经过其他流的多次包装，成为流的链接
 * 
 * InputStream & OutputStream 是抽象类。是所有字节流的父类
 * 其中的read方法和write方法和RAF中基本相同
 * @author wu.jielin
 *
 */
public class InputAndOutputStream {

}
package day07;
import java.io.*;
/**
 * FileInputStream节点流，用于从文件读取字节的流
 * @author wu.jielin
 *
 */
public class FISDemo {
	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("d:/a.txt");
		int i=fis.read();//读取第一个字节
		
		byte[] buf=new byte[22];
		//实际读取字节数
		int len=fis.read(buf);//从第二个字节开始读取
		fis.close();
		String str=new String(buf, "utf-8");
		System.out.println(i);//97
		System.out.println(str);//我在学弟一个流[]
		System.out.println(len);//21
	}
}
package day07;
import java.io.*;
/**
 * FileOutputStream,节点流，用于向文件写出字节的流
 * 使用FOS的注意事项，没有文件会新建。对已有文件直接write会先清理之前内容，再写入、与RAF区别在于RAF是覆盖。
 * 追加写操作：重载构造方法FileOutputStream(String path, true) true代表追加
 * @author wu.jielin
 *
 */
public class FOSDemo {
	public static void main(String[] args) throws IOException {
		FileOutputStream fos=new FileOutputStream("d:/a");//没有会新建
		//写一个字节
		fos.write(97);
		//从第二个位置写一个字节数组
		String str="我在学弟一个流";
		byte[] buff=str.getBytes("UTF-8");
		fos.write(buff);
		fos.close();
	}
}
package day07;
import java.io.*;
/**
 * 使用fis和fos进行复制
 * fis.read(buff),fos.write(buff,0,len)
 * @author wu.jielin
 *
 */
public class CopyDemo {
	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("D:/j2e_x86.zip");
		
		
		FileOutputStream fos=new FileOutputStream("D:/copy.zip");
		byte[] buff=new byte[10240];
		int len=-1;
		
		while((len=fis.read(buff))!=-1){
			fos.write(buff,0,len);
		}
		fis.close();
		fos.close();
	}
}
package day07;
import java.io.*;
/**
 * 缓冲输出流的注意事项
 * 强制写出flush，因为如果不到bos的缓冲大小，bos.write(buff)不起作用、
 * 比如即时通信。
 * 另外，close方法会先调flush。
 * @author wu.jielin
 *
 */

public class BosDemo {
	public static void main(String[] args) throws IOException {
		String str="kajfkjalsjfklasjf";
		FileOutputStream fos=new FileOutputStream("D:/aaaaaaaa.txt");
		BufferedOutputStream bos=new BufferedOutputStream(fos);
		byte[] buf=str.getBytes("utf-8");
		bos.write(buf);
		
		bos.flush();
		bos.close();
	}
}
package day07;
import java.util.*;
import java.io.*;
/**
 * 1.将一个特定数据结构转换成一组字节---序列化
 * 2.将字节写入硬盘---持久化
 *ArrayList实现了序列化接口。接口List并没有实现，序列化时不关心引用
 * @author wu.jielin
 *
 */
public class OOSDemo {
	public static void main(String[] args) throws IOException {
		List<String>  list=new ArrayList<String>();
		list.add("wo");
		list.add("ai");
		list.add("ni");
		
		FileOutputStream fos=new FileOutputStream("d:/lala.obj");
		//写出的对象必须实现了serializable接口
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		//先把list转为字节，然后写出
		oos.writeObject(list);
		oos.close();
		
	}
}
package day07;
import java.io.*;
/**
 * BufferedInputSteam和Bos是一组高级流/处理流。
 * 缓冲流用于提高速率
 * @author wu.jielin
 *
 */
public class BufferedInputStreamDemo {
	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("D:/copy.zip");
		FileOutputStream fos=new FileOutputStream("D:/BufCopy.zip");
		
		BufferedInputStream bis=new BufferedInputStream(fis);
		BufferedOutputStream bos=new BufferedOutputStream(fos);
		
		int d=-1;
		//每次并不会只操作一个字节，bif/bos内部维护的有缓冲数组
		//比自己写缓冲慢的原因是拿到的一组数据在bis和bos交接过程是一个字节一个字节的交接。浪费时间
		while((d=bis.read())!=-1){
			bos.write(d);
		}
		//只关最外层高级流
		bis.close();
		bos.close();
	}
}
package day07;
import java.io.*;
import java.util.*;
/**
 * OIS作用是将字节转为对象.即反序列化
 * transient关键字会让属性不能序列化，反序列化的时候会得到默认值。
 * invaildClassException-----serialCersionUID=**反序列化时类即使变了，版本号不变也可以实现反序列化。
 * 新属性会成为默认值
 * 若没有，则不能反序列化。
 * @author wu.jielin
 *
 */
public class OISDemo {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FileInputStream fis=new FileInputStream("D:/lala.obj");
		ObjectInputStream ois=new ObjectInputStream(fis);
		List obj=(List)ois.readObject();
		System.out.println(obj);//[wo,ai,ni]
		ois.close();
	}
}
package day07;
import java.io.*;
/**
 * OutputStreamWriter字符输出流，以字符为单位写粗数据，
 * 字符流只用于字符数据
 * @author wu.jielin
 *
 */
public class OSWDemo {
	public static void main(String[] args) throws IOException {
		/**
		 * 向文件中写出文本数据（写字符串）
		 * 1.FileOutputStream向file写数据
		 * 2.写的是文本数据（）
		 */
		FileOutputStream fos=new FileOutputStream("D:/coco.txt");
		fos.write("大家好，我爱你们".getBytes("utf-8"));
		fos.write("fghjk".getBytes("utf-8"));
		/**
		 * 直接在流级别指定编码
		 */
		OutputStreamWriter osw=new OutputStreamWriter(fos, "utf-8");
		osw.write("\n我们都是好人的");
		osw.close();
	}
}
package day07;
import java.io.*;
/**
 * InputStreamReader字符输入流用于读取字符数据的流
 * @author wu.jielin
 *
 */
public class ISRDemo {
	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("d:/coco.txt");
		InputStreamReader isr=new InputStreamReader(fis,"utf-8");
		int d=-1;
		while((d=isr.read())!=-1){
			System.out.print((char)d);
		}
		isr.close();
	}
}
package day07;
import java.io.*;
/**
 * 字符流复制文件
 * @author wu.jielin
 *
 */
public class CopyDomoCharStream {
	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("d:/wujielin.txt");
		FileOutputStream fos=new FileOutputStream("d:/wujielinaa.txt");
		
		InputStreamReader isr=new InputStreamReader(fis);
		OutputStreamWriter osw=new OutputStreamWriter(fos);
		int d=-1;
		while((d=isr.read())!=-1){
			osw.write(d);
		}
		isr.close();
		osw.close();
	}
}
package day07;
import java.io.*;
/**
 * PrintWriter缓冲字符输出流，带有自动行刷新。可以以行为单位写出字符串
 * 其为高级流，构造方法内部自己构建了低级流.
 * 自动行刷新特点：
 * 需要及时性的话(设置自动行刷新）PW pw=new PW("shenghuo",true);true代表每一次syso都自动一次autoflush；会增加写出次数，降低写出效率。
 * 当然保证了输出的即时性。
 * @author wu.jielin
 *
 */
public class PrintWriterDemo {
	public static void main(String[] args) throws IOException {
		PrintWriter pw=new PrintWriter("d:/pwpw.txt");
		//使用系统默认的编码输出到文件
		pw.print("上哪干活");
		pw.print("lala");
		pw.close();
		
		//要改编码集的话
		PrintWriter pw1=new PrintWriter(
				new OutputStreamWriter(
						new FileOutputStream("d:/kk.txt"),"utf-8"));
		pw1.print("这是用字节流构建writer并设置编码集，然后组装printwriter的示例");
		pw1.close();//close都会自动行刷新
	}
}
package day07;
import java.io.*;
/**
 * BUfferedReader缓冲字符输入流。可以一行为单位读取字符串。
 * BR(Reader reader)  
 * String s=br.readLine();
 *读到换行符结束。将换行符之前的字符串给你。返回为null则读到文件末尾了
 * @author wu.jielin
 *
 */
public class BufferedReaderDemo {
	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("d:/pwpw.txt");
		BufferedReader br=new BufferedReader(
				new InputStreamReader(fis,"utf-8")
				);
		String string=null;
		while((string=br.readLine())!=null){
			System.out.println(string);
		}
		br.close();
	}
}
package day08;
/**
 * Throwable派生出Error和Exception
 * 程序一般只对exception做处理 error（系统级别）是不处理的。
 * @author wu.jielin
 *
 */
public class Doc {

}
package day08;
/**
 * try catch
 * main 是jvm调的，如果没有处理，汇报exception抛给调用main的jvm。jvm处理方式就是kill
 * 一个exception出现，跳进catch执行完catch块后就执行trycatch之后的语句了。不在执行后面的try块了。
 * @author wu.jielin
 *
 */
public class ExceptionDemo01 {
	
	public static void main(String[] args) {
		System.out.println("start...");
		try{
		String string="a";
		System.out.println(string.length());//不catch的话闪退
		System.out.println(Integer.parseInt(string));
		//jvm new一个nullpointerexception
		//遍历所有catch，找到合适的并作为参数传给catch.
		//找不到的话就抛给方法，方法抛给调用者。
		
		char ch=string.charAt(0);
		}catch(NullPointerException e){//catch(关注的异常类型)
			System.out.println("一个空指针");
		}catch(StringIndexOutOfBoundsException e){
			System.out.println("StringIndexOutofBoundsException");
		}catch(NumberFormatException e){
			System.out.println("NumberFormatException");
		}catch(Exception e){//处理多数异常，放在最后否则其他具体一场永远执行不到
			System.out.println("不明确错误");
		}
		System.out.println("end...");//正常退出
	}
}
package day08;

/**
 * 无论try块是否报错，finally块都要执行
 * 
 * @author wu.jielin
 *
 */
public class FinallyDemo {
	public static void main(String[] args) {
		try {
			System.out.println("链接数据库");
			System.out.println("连接成功");
			System.out.println("查询数据");
			System.out.println("数据操作");
			String s = "a";
			int i = Integer.parseInt(s);
			System.out.println("数据处理结束");
			System.out.println("数据保存至数据库");
		} catch (Exception e) {
			System.out.println("出现了错误");
		} finally {
			System.out.println("断开连接");// 必须要做的操作
		}
		System.out.println("程序结束");
	}
}
package day08;

/**
 * return和finally。 catch结束后将返回值赋值为1，然后finally将返回值赋值成为了3. 最后1,2,3-->3,3,3
 * 因此finally中一般不写return。否则其他return都将失效
 */
public class Excercise {
	public static void main(String[] args) {
		System.out.println(test(null) + "," + test("") + "," + test("0"));
	}

	public static int test(String str) {

		try {
			return str.charAt(0) - '0';
		} catch (NullPointerException e) {
			return 1;
		} catch (Exception e) {
			return 2;
		} finally {
			return 3;
		}
	}
}
package day08;
/**
 * 通常情况下方法中如果throw一个异常，则我们必须处理：
 * 1.为你的throw添加try catch。
 * 2.在方法上声明该类异常的抛出，以通知调用者处理该异常
 * 3.调用者处理遵循上述两条
 * @author wu.jielin
 *
 */
public class ExceptionDemo02 {
	private static int age;
	public static void setAge(int x) throws NullPointerException{
		if(x<0||x>100){//空指针异常属于runtimeException，是非检查异常
			throw new NullPointerException("不是人类的年龄");
		}
		age=x;
	}
	//main调用了setAge（抛出异常），main必须处理异常
	public static void main(String[] args){
		setAge(200);
	}
}
package day08;
/**
 * 1.当前方法除了异常，但是处理这个异常的责任不该当前方法管。应该throws
 * 2.当程序满足语法要求，但是不符合业务逻辑，这种也可以主动throw一个异常
 * 
 * 通常情况下如果方法中throw了一个异常实例，我们则必须处理该异常。处理方式
 * 1.为你的throw添加try catch
 * 2.方法体前throws该类异常给调用方法者
 * 3.方法调用者处理方式同上两种方式。
 * 不要再main方法throws Exception/
 * 检查异常：编译器会判断其是否被处理，不处理编译不通过。
 * 非检查异常：也称运行时异常。空指针，下标出界等由于出现频率太高，
 * 应该程序员自己处理。就不再强制要求，编译器不判断其是否被处理。简单说就是太贫就没劲了。
 * RuntimeException: IllegalArgumentException,NullPointerException,ArrayIndexOutOfBoundsException,ClassCastException,NumberFormatException等/
 * 重写父类带有throws的方法:可以不处理，可以抛出部分异常，可以抛出子类异常。但是不能扩大异常范围。即额外的，或者父类异常的父类。
 * 最后，catch里面也可throw
 * @author wu.jielin
 *
 */
public class ThrowDemo {
	private static int age;
	public static void setAge (int s) throws Exception{
		if(s<0||s>100){//如果是RuntimeException就不会报错
			throw new Exception("不是人类的年龄");
		}
		age=s;
	}
	public static void main(String[] args) {
		try {
			setAge(200);
		} catch (Exception e) {
			System.out.println(e.toString());//java.lang.Exception: 不是人类的年龄
            e.printStackTrace();//定位位置
		}
	}
}
package day08;
/**
 * Exception方法
 * 	void printStackTrace();
 * 	String getMessage()返回printStackTrace中的一句话，错误在哪儿；
 * 	Throwable getCause()获取风格统一的异常包装在里面的真是异常。如果已经是本质异常了，返回null
 * @author wu.jielin
 *
 */
public class ExceptionMethodDemo {
	public static void main(String[] args) {
		System.out.println("start...");
		try{
			String str="a";
			System.out.println(str.length());
			System.out.println(Integer.parseInt(str));
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("error...");
			System.out.println(e.getCause());//null
		}
		System.out.println("end...");
	}
}
package day08;
/**
 * 获取真实异常
 * @author wu.jielin
 *
 */
public class ExceptionMethodDemo1 {
	public static void main(String[] args) {
		try {
			doSome();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getCause());//java.lang.NullPointerException
		}
	}
	
	public static void doSome() throws Exception{
		try{
			String s=null;
			System.out.println(s.length());
		}catch(NullPointerException e){
			throw new Exception(e);
		}
	}
}
package day08;
/**
 * 自定义年纪异常
 * 序列版本号写好，完事儿就直接生成构造器就行。
 * @author wu.jielin
 *
 */
public class IllegalAgeException extends Exception{
	private static final long
						serialversionUID=1L;

	public IllegalAgeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IllegalAgeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public IllegalAgeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IllegalAgeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public IllegalAgeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
