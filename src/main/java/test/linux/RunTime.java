package test.linux;

import com.jcraft.jsch.*;

import java.io.InputStream;
import java.lang.reflect.Field;

public class RunTime {
    private static Session session;
    public static void main(String[] args) {
        String ip = "192.168.1.202";
        Integer port = 22;
        String user = "root";
        String password = "root";
        connect(ip, port, user, password);
        System.out.println();
    }

    public static void connect(String ip, Integer port, String user, String password) {
        //获取一个连接
        try {
            JSch jsch = new JSch();
            if (port != null) {
                session = jsch.getSession(user, ip, port);
            } else {
                session = jsch.getSession(user, ip);
            }
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(20000);
        } catch (JSchException e) {
            e.printStackTrace();
            session = null;
        }
        //执行一个命令
        int exit = -1;
        try {
            ChannelExec channel = (ChannelExec)session.openChannel("exec");
            channel.setCommand("ifconfig");
            InputStream in = channel.getInputStream();
            channel.connect();
            byte[] tmp = new byte[1024];
            String s = "";
            while (true){
                int i;
                if((i=in.read(tmp,0,1024))<0){
                    break;
                }
                s = new String(tmp,0,i);
            }
            Thread.sleep(500);
            for(int i = 0; i < 10; i++) {
                exit = channel.getExitStatus();
                if(exit != -1)break;
                Thread.sleep(100);
            }
            channel.disconnect();
            System.out.println(s);
        }catch (Exception e){
            e.printStackTrace();
        }
        //传输一个文件
        String localFile = "C:\\Users\\asus\\Desktop\\";
        String remotePath = "/opt/csbit/b.txt";
        try{
            ChannelSftp sftp = (ChannelSftp)session.openChannel("sftp");
            sftp.connect();
            Class cl = ChannelSftp.class;
            Field f1 =cl.getDeclaredField("server_version");
            f1.setAccessible(true);
            f1.set(sftp, 2);
            sftp.setFilenameEncoding("GBK");
            if(localFile==null||localFile.isEmpty()||remotePath==null||remotePath.isEmpty()){

            }
            //sftp.put(localFile,remotePath);
            sftp.get(remotePath,localFile);
        }catch (Exception e){
            e.printStackTrace();

        }



    }
}
