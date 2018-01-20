package com.bms.eai.common.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.util.StringUtils;

import com.bms.eai.common.model.core.AbstractEntity;

/**
 * @author kul_sudhakar
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "prop_attachments",uniqueConstraints = @UniqueConstraint(columnNames = "link_id"))
public class PropAttachments extends AbstractEntity<PropAttachments,String>  implements java.io.Serializable {

	@Id
	@Column(name = "pa_id",  length = 45)
	private String id;
	
	@Column(name = "link_id", unique = true, nullable = false, length = 45)
	private String linkId;
	
	@Column(name = "filename", nullable = false, length = 250)
	private String fileName;
	
	@Column(name = "contentType", nullable = false, length = 300)
	private String contentType;
	
	@Lob
	@Column(name = "file_bytes", length = 8000000)
	private byte[] fileBytes;

	@Override
	protected void doCopyUpdateFieldsFrom(PropAttachments fromEntity) {
		this.linkId = StringUtils.hasText(fromEntity.linkId) ? fromEntity.linkId : this.linkId;
		this.fileName = StringUtils.hasText(fromEntity.fileName) ? fromEntity.fileName : this.fileName;
		this.contentType = StringUtils.hasText(fromEntity.contentType) ? fromEntity.contentType : this.contentType;
		this.fileBytes =  (fromEntity.fileBytes!=null && fromEntity.fileBytes.length>0) ? fromEntity.fileBytes :this.fileBytes;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getFileBytes() {
		return fileBytes;
	}

	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}

}
