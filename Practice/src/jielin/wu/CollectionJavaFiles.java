package jielin.wu;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class CollectionJavaFiles {
	public void collectFiles(String project) throws UnsupportedEncodingException, IOException{
		File src=new File("D:/workspace/"+project+"/src");
		File[] dirs=src.listFiles();
		Arrays.sort(dirs, new Compare());
		for (File dir : dirs) {
			if(dir.isDirectory()){
				File[] files=dir.listFiles();
				Arrays.sort(files,new Compare());
				for (File file : files) {
					BufferedReader br=new BufferedReader(
							new InputStreamReader(
									new FileInputStream(file))
							);
					BufferedWriter bw=new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream("d:/collection.java", true))
							);
					String s=null;
					while((s=br.readLine())!=null){
						bw.write(s+"\n");
					}
					br.close();
					bw.close();
				}
			}
		}
	}
	public static void main(String[] args) {
		CollectionJavaFiles coll=new CollectionJavaFiles();
		try {
			coll.collectFiles("SE01");
			coll.collectFiles("SE02");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class Compare implements Comparator<File>{
	@Override
	public int compare(File file1, File file2) {
		
		return (int)(file1.lastModified()-file2.lastModified());
	}

	
}