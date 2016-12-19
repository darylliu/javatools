package com.files.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.PrintWriter;

public class FileUtil {
	
	/**
	 * create a new folder
	 * @param path the path of new folder  eg: ./newFolder
	 */
	public static void creatFolder(String path){
		File newFolder = new File(path);   
		try {   
		    if (!newFolder.exists()) {   
		    	newFolder.mkdir();   
		    	System.out.println("create new folder success!");
		    }   
		}   
		catch (Exception e) {   
		    System.out.println("error in create new folder");   
		    e.printStackTrace();   
		} 
	}
	
	/**
	 * create a new file
	 * @param path the path of new file
	 */
	public static void creatFile(String path){
		File newFile = new File(path);   
		try {   
		    if (!newFile.exists()) {   
		    	newFile.createNewFile();   
		    	System.out.println("create new file success!");
		    }   
		}   
		catch (Exception e) {   
		    System.out.println("error in create new file");   
		    e.printStackTrace();   
		} 
	}
	
	/**
	 * delete the target file
	 * @param path the path of target file
	 */
	public static void deleteFile(String path){
		File targetFile = new File(path);   
		try {   
			targetFile.delete();
			System.out.println("delete target file success!");
		}   
		catch (Exception e) {   
		    System.out.println("error in delete target file");   
		    e.printStackTrace();   
		}   
	}

	/**
	 * traverse folder (recursion)
	 * @param path the path of folder
	 */
	public static void traverseFolder(String path) {
    File file = new File(path);
    if (file.exists()) {
        File[] files = file.listFiles();
        if (files.length == 0) {
            System.out.println("the folder is empty!");
            return;
        } 
        else {
        	for (File file2 : files) {
        		if (file2.isDirectory()) {
        			System.out.println("folder: " + file2.getAbsolutePath());
              traverseFolder(file2.getAbsolutePath());
              } 
        		else {
        			System.out.println("file: " + file2.getAbsolutePath());
        			}
        		}
        	}
        } 
    else {
        System.out.println("file does not exist!");
    }
	}
	
	
	
	/**
	 * * file filter
	 * @param path the path of folder
	 * @param type filter condition 
	 * @return filter files
	 */
	public static File[] fileTypeFilter(String path, String type){
		File file = new File(path);
    if (file.exists()) {
    	File[] files =file.listFiles(new MyFileFilter(type));  
  	  for(File eachFile:files){  
  	      System.out.println(eachFile.getName());  
  	  }
  	  return files;
    } 
    else {
        System.out.println("file does not exist!");
        return null;
    }
		
		
		  
	}
	
	/**
	 * my file filter 
	 * @author ln
	 *
	 */
	static class MyFileFilter implements FileFilter{  
    private String type;  
    public MyFileFilter(String type){  
        this.type = type;  
    }  
		@Override
		public boolean accept(File file) {
			return file.getName().endsWith("."+type);
		}  
}
	
}
