package likou.threadlocaldemo.tools;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 
 * 类说明: 文件处理工具
 * @author Brando 2017-10-16 下午2:39:03
 */
public class FileTools {

	/**
	 * 
	 * 方法说明: 删除文件或者目录下所有类容.
	 * @author Brando 2017-10-16 下午2:40:06
	 * @param file
	 */
	public static void deleteFile(File file) {
		if (file.exists()) {//判断文件是否存在  
			if (file.isFile()) {//判断是否是文件  
				file.delete();//删除文件   
			} else if (file.isDirectory()) {//否则如果它是一个目录  
				File[] files = file.listFiles();//声明目录下所有的文件 files[];  
				for (int i = 0;i < files.length;i ++) {//遍历目录下所有的文件  
					deleteFile(files[i]);//把每个文件用这个方法进行迭代  
				}  
				file.delete();//删除文件夹  
			}  
		} else {  
			System.out.println("删除的文件不存在");
		}  
	}
	
	/**
	 * 
	 * 方法说明: 删除文件或者目录下所有类容.
	 * @author Brando 2017-10-16 下午2:44:47
	 * @param filePath
	 */
	public static void deleteFile(String filePath) {
		File file = new File(filePath);
		deleteFile(file);
	}
	
	/**
	 * 
	 * 方法说明: 加载文件到StringBuffer里面.
	 * @author Brando 2017-12-22 下午2:11:39
	 * @param filePath
	 * @return
	 */
	public static StringBuffer loadFileToStringBuffer(String filePath) {
		StringBuffer buf = new StringBuffer();
		File file = new File(filePath);  
        BufferedReader reader = null;  
        try {  
            reader = new BufferedReader(new FileReader(file));  
            String tempString = null;  
            while ((tempString = reader.readLine()) != null) {  
            	buf.append(tempString);  
            }
            reader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e1) {  
                }  
            }  
        }  
		return buf;
	}
	
	/**
	 * 
	 * 方法说明: 创建文件目录.
	 * @author Brando 2015-2-3 下午01:26:56
	 * @param filePath String 文件路径.
	 */
	public static void makeDir(String filePath) {
		try {
			File file = new File(filePath);
			file.mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 方法说明: 保存文件
	 * @author Brando 2015-2-15 上午11:09:41
	 * @param filePath 保存文件路径.
	 * @param content 保存文件类容.
	 */
	public static String saveFile(String filePath, String fileName, String content) {
		makeDir(filePath);
		appendContent(filePath + "/" + fileName, content);
		return filePath + "/" + fileName;
	}
	
	/**
	 * 
	 * 方法说明: 追加文本文件.
	 * @author Brando 2015-2-3 下午04:49:17
	 * @param filePath String 保存文件路径.
	 * @param content String 保存正文.
	 */
	public static void appendContent(String filePath, String content) {
	    try {
	    	OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8");
	    	out.write(content);
	    	out.flush();
	        out.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * 
	 * 方法说明: 获取文件扩展名
	 * @author Brando 2016-2-18 上午11:29:10
	 * @param fileName 文件名称.
	 * @return Stirng 文件扩展名, 如:png
	 */
	public static String getFileExtName(String fileName) {
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		return fileExt;
	}

	/**  
	 * 方法说明:
	 * @author BrandoLv 2018/9/7 10:22
	 * @param filePath 完整目录的文件地址. 如: C:/temp/temp.png
	 * @return String 文件名称.	如: temp.png
	 */
	public static String getFileNameFromPath(String filePath) {
		String fileName = filePath;
		int index = filePath.lastIndexOf("/");
		if(index == -1) {
			index = filePath.lastIndexOf("\\");
		}
		if(index != -1) {
			fileName = filePath.substring(index + 1);
		}
		return fileName;
	}

	/**
	 * 方法说明:拷贝文件目录.
	 * @author BrandoLv 2018/9/13 18:28
	 */
	public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {
		//新建目标目录
		(new File(targetDir)).mkdirs();
		//获取源文件夹当下的文件或目录
		File[] file = (new File(sourceDir)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				//源文件
				File sourceFile = file[i];
				//目标文件
				File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
				copyFile(sourceFile, targetFile);
			}
			if (file[i].isDirectory()) {
				//准备复制的源文件夹
				String dir1 = sourceDir + file[i].getName();
				//准备复制的目标文件夹
				String dir2 = targetDir + File.separator + file[i].getName();
				copyDirectiory(dir1, dir2);
			}
		}

	}

	/**
	 * 方法说明:拷贝文件
	 * @author BrandoLv 2018/9/13 18:28
	 */
	public static void copyFile(File sourcefile, File targetFile) throws IOException {

		//新建文件输入流并对它进行缓冲
		FileInputStream input = new FileInputStream(sourcefile);
		BufferedInputStream inbuff = new BufferedInputStream(input);

		//新建文件输出流并对它进行缓冲
		FileOutputStream out = new FileOutputStream(targetFile);
		BufferedOutputStream outbuff = new BufferedOutputStream(out);

		//缓冲数组
		byte[] b = new byte[1024 * 5];
		int len = 0;
		while ((len = inbuff.read(b)) != -1) {
			outbuff.write(b, 0, len);
		}

		//刷新此缓冲的输出流
		outbuff.flush();

		//关闭流
		inbuff.close();
		outbuff.close();
		out.close();
		input.close();
	}


	public static void main(String[] args) {
		String path = "E:/Temp_Upload/xx.txt";
		System.out.println(getFileNameFromPath(path));
	}

	/**
	 * MultipartFile 转 File
	 *
	 * @param file
	 * @throws Exception
	 */
	public static File multipartFileToFile(MultipartFile file) throws Exception {

		File toFile = null;
		if (file.equals("") || file.getSize() <= 0) {
			file = null;
		} else {
			InputStream ins = null;
			ins = file.getInputStream();
			toFile = new File(file.getOriginalFilename());
			inputStreamToFile(ins, toFile);
			ins.close();
		}
		return toFile;
	}
	//获取流文件
	private static void inputStreamToFile(InputStream ins, File file) {
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
