package com.bms.eai.common.lib;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.bms.eai.module.beans.FileMessageResource;
import com.bms.eai.module.prop.beans.PropDetailsMaster;

public class TestFileUpload {

	final static String UPLOAD_URL = "http://localhost:8070/propertydetails/api/1.0/propsettings/create";
	
	public static void main(String[] args) throws IOException {
		
		PropDetailsMaster pdm = getData();
		
		/*ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(pdm);
		System.out.println(jsonStr);*/
		
		  RestTemplate restTemplate = new RestTemplate();
		
		 FormHttpMessageConverter fc = new FormHttpMessageConverter();
		 fc.addPartConverter(new ByteArrayHttpMessageConverter());
		  fc.addPartConverter(new StringHttpMessageConverter());
		  fc.addPartConverter(new ResourceHttpMessageConverter(false));
		  fc.addPartConverter(new SourceHttpMessageConverter<>());
		  fc.addPartConverter(new AllEncompassingFormHttpMessageConverter());
	     fc.addPartConverter(new MappingJackson2HttpMessageConverter());
	     restTemplate.getMessageConverters().add(fc);  
		
         MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
        //bodyMap.add("file", getUserFileResource());
        //bodyMap.add("file", new FileMessageResource(FileUtils.readFileToByteArray(new File("d:/DellLaptop.pdf")), "DellLaptop.pdf"));
        bodyMap.add("reqJson", pdm);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("message", null);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

        ResponseEntity<String> response = restTemplate.exchange(UPLOAD_URL, HttpMethod.POST, requestEntity, String.class);
        System.out.println("response status: " + response.getStatusCode());
        System.out.println("response body: " + response.getBody()); 
    }

    public static Resource getUserFileResource() throws IOException {
        //todo replace tempFile with a real file
        Path tempFile = Files.createTempFile("upload-test-file", ".jpg");
        Files.write(tempFile,FileUtils.readFileToByteArray(new File("d:/file_test.jpg"))); 
        // "some test content...\nline1\nline2".getBytes());
        System.out.println("uploading: " + tempFile);
        File file = tempFile.toFile();
        //to upload in-memory bytes use ByteArrayResource instead
        return new FileSystemResource(file);
    }	
	
    
    private static PropDetailsMaster getData() {
    	PropDetailsMaster pdm = new PropDetailsMaster();
    	pdm.setDescription("description");
    	pdm.setEmail("email@gmail.com");
    	pdm.setJmbMc("MC");
    	pdm.setName("fileuploadrestabc");
    	pdm.setPropTypeId("20171213403107345");
    	pdm.setStateId("20171213102105345");
    	pdm.setTotalUnits(12);
    	return pdm;
    }
	
}
