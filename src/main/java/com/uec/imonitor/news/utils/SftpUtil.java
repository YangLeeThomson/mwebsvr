package com.uec.imonitor.news.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.ChannelSftp.LsEntry;

/**
 * <p>
 * Copyright: All Rights Reserved
 * </p>
 * <p>
 * Company: 北京荣之联科技股份有限公司 http://www.ronglian.com
 * </p>
 * <p>
 * Description: sftp读取工具类
 * </p>
 * <p>
 * Author:xkwang/王西坤
 * </p>
 */
public class SftpUtil {

	private ChannelSftp sftp;
	private Session sshSession;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public ChannelSftp connect(String host, int port, String username, String password) {
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			// Session sshSession = jsch.getSession(username, host, port);
			sshSession = jsch.getSession(username, host, port);
			log.info("Session is build");
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			log.info("Session is connected");
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			// sftp.setFilenameEncoding("UTF-8"); //不起作用
			log.info(String.format("sftp server host:[%s] port:[%s] is connect successfull", host, port));
		} catch (Exception e) {
			log.error("Cannot connect to specified sftp server : {}:{} \n Exception message is: {}",
					new Object[] { host, port, e.getMessage() });
		}
		return sftp;
	}

	/**
	 * 关闭连接 server
	 */
	public void logout() {
		if (sftp != null) {
			if (sftp.isConnected()) {
				sftp.disconnect();
				log.info("sftp is closed already");
			}
		}
		if (sshSession != null) {
			if (sshSession.isConnected()) {
				sshSession.disconnect();
				log.info("sshSession is closed already");
			}
		}
	}

	/**
	 * 批量下载文件
	 * 
	 * @param directory
	 *            ftp目录路径
	 * @param localPath
	 *            本地保存路径
	 * @param sftp
	 */
	public void batchDownLoadFile(String directory, String localPath, ChannelSftp sftp) {
		try {
			if (!new File(localPath).exists()) {
				new File(localPath).mkdirs();
			}
			sftp.cd(directory);
			// 获取指定路径下所有文件
			Vector ls = sftp.ls(directory);
			if (ls.size() > 0) {
				int num = 0;
				for (Object item : ls) {
					LsEntry entry = (LsEntry) item;
					if (entry.getLongname().contains(".txt")) {
						log.info("本次处理文件" + (ls.size() - 2) + "个" + "正在处理第" + ++num + "个");
						// 如果是txt文件，则进行处理
						download(entry, directory, localPath, sftp);
					}
					if (entry.getLongname().startsWith("d")) {
						// 如果是文件夹
						// System.err.println(entry.getAttrs());//drwxrwxr-x
						// 1002 1002 141 Mon Apr 24 16:22:17 CST 2017
						// System.err.println(entry.getFilename());//. 00
						// System.err.println(entry.getLongname());//drwxrwxr-x
						// 4 share share 141 Apr 24 16:22 .
						if (!entry.getFilename().contains(".")) {
							batchDownLoadFile(directory + "/" + entry.getFilename(),
									localPath + "/" + entry.getFilename(), sftp);
						}
					}
				}
			} else {
				log.info("该目录暂无文件");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 批量删除指定路径下的所有文件、包括文件夹
	 * 
	 * @param entry
	 * @param directory
	 * @param localPath
	 * @param sftp
	 */
	public void batchDel(String directory, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			// 获取指定路径下所有文件
			Vector ls = sftp.ls(directory);
			int num = 0;
			for (Object item : ls) {
				LsEntry entry = (LsEntry) item;
				if (entry.getLongname().contains(".txt")) {
					System.err.println(directory + "/" + entry.getFilename());
					sftp.cd(directory);
					sftp.rm(entry.getFilename());
				}
				if (entry.getLongname().startsWith("d")) {
					if (!entry.getFilename().contains(".")) {
						batchDel(directory + "/" + entry.getFilename(), sftp);
					}
				}
			}
			log.info(directory);
			sftp.rmdir(directory);
			log.info("文件夹删除成功。。。");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 下载单个文件
	public void download(LsEntry entry, String directory, String localPath, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			sftp.get(entry.getFilename(), new FileOutputStream(new File(localPath) + "\\" + entry.getFilename()));
		} catch (Exception e) {
			log.error(directory + "/" + entry.getFilename() + "传输失败!");
			e.printStackTrace();
		}
	}
}
