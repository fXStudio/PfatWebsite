package cn.fxtech.pfatwebsite.models;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "pfat_file")
public class EMpfatfile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String fileName;
	@JsonIgnore
	private String filePath;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp created;
	@JsonIgnore
	private Integer pfatitemId;
	@JsonIgnore
	private MultipartFile fileStream;
	private String cate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Integer getPfatitemId() {
		return pfatitemId;
	}

	public void setPfatitemId(Integer pfatitemId) {
		this.pfatitemId = pfatitemId;
	}

	public MultipartFile getFileStream() {
		return fileStream;
	}

	public void setFileStream(MultipartFile fileStream) {
		this.fileStream = fileStream;
	}

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}
}
