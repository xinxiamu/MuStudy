package co.jufeng.web.multipart.commons;

import java.util.List;

import org.apache.commons.fileupload.FileItem;

public class CommonsMultipartFile {
	
	private List<FileItem> fileItems;
	
	public CommonsMultipartFile() {
	}
	
	public CommonsMultipartFile(List<FileItem> fileItems) {
		this.fileItems = fileItems;
	}


	public List<FileItem> getFileItems() {
		return fileItems;
	}

	public void setFileItems(List<FileItem> fileItems) {
		this.fileItems = fileItems;
	}

}
