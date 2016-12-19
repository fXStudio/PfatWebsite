package cn.fxtech.pfatwebsite.protocal;

import org.springframework.web.multipart.MultipartFile;

public class PfatFile {
	private String fileName;
	private MultipartFile pfatFile;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public MultipartFile getPfatFile() {
		return pfatFile;
	}

	public void setPfatFile(MultipartFile pfatFile) {
		this.pfatFile = pfatFile;
	}
}
