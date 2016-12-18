package com.files.util;

import java.io.File;

public class FileUtil {
	/**
	 * 递归地遍历文件夹
	 * @param path 根目录路径
	 */
	public static void traverseFolder(String path) {
    File file = new File(path);
    if (file.exists()) {
        File[] files = file.listFiles();
        if (files.length == 0) {
            System.out.println("文件夹是空的!");
            return;
        } 
        else {
        	for (File file2 : files) {
        		if (file2.isDirectory()) {
        			System.out.println("文件夹:" + file2.getAbsolutePath());
              traverseFolder(file2.getAbsolutePath());
              } 
        		else {
        			System.out.println("文件:" + file2.getAbsolutePath());
        			}
        		}
        	}
        } 
    else {
        System.out.println("文件不存在!");
    }
	}
}
