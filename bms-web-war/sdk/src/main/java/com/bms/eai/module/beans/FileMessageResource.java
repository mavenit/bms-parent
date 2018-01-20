package com.bms.eai.module.beans;

import org.springframework.core.io.ByteArrayResource;

/**
 * @author kul_sudhakar
 *
 */
public class FileMessageResource extends ByteArrayResource {

	private final String filename;
	
	public FileMessageResource(final byte[] byteArray, final String filename) {
        super(byteArray);
        this.filename = filename;
    }

    @Override
    public String getFilename() {
        return filename;
    }
	
}
