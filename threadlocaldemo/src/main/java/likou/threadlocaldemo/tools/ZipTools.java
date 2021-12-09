package likou.threadlocaldemo.tools;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 类说明:
 *
 * @author BrandoLv 2018/9/13 18:19
 */
public class ZipTools {


    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     * If a deletion fails, the method stops attempting to
     * delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 压缩zip格式文件
     *
     * @param targetFile  输出的文件。
     * @param sourceFiles 带压缩的文件数组。
     * @return 如果所有文件压缩成功，则返回true；如果有任何文件未成功压缩，则返回false。
     * @throws IOException 如果出错后无法删除目标文件或无法覆盖目标文件。
     */
    public static boolean compressZip(File targetFile, File... sourceFiles) throws IOException {
        ZipOutputStream zipOut;
        boolean flag;
        if (targetFile.exists() && !targetFile.delete()) {
            throw new IOException();
        }
        try {
            zipOut = new ZipOutputStream(new FileOutputStream(targetFile));
            BufferedOutputStream out = new BufferedOutputStream(zipOut);
            flag = compressZip(zipOut, out, "", sourceFiles);
            out.close();
            zipOut.close();
        } catch (IOException e) {
            targetFile.delete();
            throw new IOException(e);
        }
        return flag;
    }

    private static boolean compressZip(ZipOutputStream zipOut, BufferedOutputStream out, String filePath, File... sourceFiles)
            throws IOException {
        if (null != filePath && !"".equals(filePath)) {
            filePath += filePath.endsWith(File.separator) ? "" : File.separator;
        } else {
            filePath = "";
        }
        boolean flag = true;
        for (File file : sourceFiles) {
            if (null == file) {
                continue;
            }
            if (file.isDirectory()) {
                File[] fileList = file.listFiles();
                if (null == fileList) {
                    return false;
                } else if (1 > fileList.length) {
                    zipOut.putNextEntry(new ZipEntry(filePath + file.getName() + File.separator));
                } else {
                    flag = compressZip(zipOut, out, filePath + File.separator + file.getName(), fileList) && flag; // 只要flag有一次为false，整个递归的结果都为false。
                }
            } else {
                zipOut.putNextEntry(new ZipEntry(filePath + file.getName()));
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                int bytesRead;
                while (-1 != (bytesRead = in.read())) {
                    out.write(bytesRead);
                }
                in.close();
            }
            out.flush();
        }
        return flag;
    }

    /**
     * 解压zip格式文件
     * @param originFile zip文件。
     * @param targetDir  要解压到的目标路径
     * @return 如果目标文件不是zip文件则返回false
     * @throws IOException 如果发生I/O错误
     */
    public static boolean decompressZip(File originFile, String targetDir) throws IOException {
        if (!targetDir.endsWith(File.separator)) {
            targetDir += File.separator;
        }
        ZipFile zipFile = new ZipFile(originFile);
        ZipEntry zipEntry;
        Enumeration<ZipEntry> entry = (Enumeration<ZipEntry>) zipFile.entries();
        while (entry.hasMoreElements()) {
            zipEntry = entry.nextElement();
            String fileName = zipEntry.getName();
            File outputFile = new File(targetDir + fileName);
            if (zipEntry.isDirectory()) {
                forceMkdirs(outputFile);
                continue;
            } else if (!outputFile.getParentFile().exists()) {
                forceMkdirs(outputFile.getParent());
            }
            OutputStream outputStream = new FileOutputStream(outputFile);
            InputStream inputStream = zipFile.getInputStream(zipEntry);
            int len;
            byte[] buffer = new byte[8192];
            while (-1 != (len = inputStream.read(buffer))) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
            inputStream.close();
        }
        zipFile.close();
        return true;
    }

    public static String convertCygwinPath(String path) {
        path = path.replaceAll("\\\\", "/");
        if (System.getProperty("os.name").toLowerCase().contains("win"))
            path = "/cygdrive/" + path.replaceFirst(":", "");
        return path;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static File forceMkdirs(File file) {
        if (!file.exists()) {
            file.mkdirs();
        } else if (!file.isDirectory()) {
            file.delete();
            file.mkdirs();
        }
        return file;
    }

    public static File forceMkdirs(String pathName) {
        return forceMkdirs(new File(pathName));
    }



    public static void main(String[] args) {
        String filePath = "E:/FileUploadRoot/f859c190b741475b9aa2e785f1aebade.zip";
        File zipFile = new File(filePath);
        try {
            decompressZip(zipFile, "E:/FileUploadRoot");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
