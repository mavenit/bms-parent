package com.bms.eai.portal.lib;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.TypeReference;

/**
 * @author kul_sudhakar
 *
 */
public class JsonUtil {

	//private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	
	private static final ObjectMapper defaultObjectMapper = new ObjectMapper();
    private static volatile ObjectMapper objectMapper = null;

    // Ensures that there always is *a* object mapper
    private static ObjectMapper mapper() {
        if (objectMapper == null) {
            return defaultObjectMapper;
        } else {
            return objectMapper;
        }
    }
    
    /**
     * Convert an object to JsonNode.
     *
     * @param data Value to convert in Json.
     */
    public static JsonNode toJsonNode(final Object data) {
        try {
            return mapper().valueToTree(data);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convert a JsonNode to a Java value
     *
     * @param json Json value to convert.
     * @param clazz Expected Java value type.
     */
    public static <A> A fromJsonNode(JsonNode json, Class<A> clazz) {
        try {
            return mapper().treeToValue(json, clazz);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Transfer Json object to Java Object
     * 
     * @param json
     * @param clazz - Entity or Class Type
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <A> A transferToObject(JsonNode json, Class<A> clazz) throws JsonParseException, JsonMappingException, IOException {
    	return mapper().readValue(json, clazz);
    }
    
    /**
     * Transfer Json String to Java Object
     * 
     * @param json
     * @param clazz - Entity or Class Type
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    
    public static <A> A transferToObject(String json, Class<A> clazz) throws JsonParseException, JsonMappingException, IOException {
    	return mapper().readValue(json, clazz);
    }
    
    /**
     * Transfer Json object to Java List
     * 
     * @param json - JsonNode
     * @param clazz - Entity or Class Type
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static List<?> transferToList(JsonNode json, Class<?> clazz) throws JsonParseException, JsonMappingException, IOException {
    	return (List<?>) mapper().readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, clazz));
    }

	public static void setObjectMapper(ObjectMapper mapper) {
        objectMapper = mapper;
    }
	
	public static Map<String, Object> convertJsonToMap(String jsonStr) throws JsonParseException, JsonMappingException, IOException {
		return mapper().readValue(jsonStr, new TypeReference<Map<String, Object>>() {});
	}

	public static String convertMapToJson(Map<String, Object> map) throws JsonGenerationException, JsonMappingException, IOException {
		return mapper().writeValueAsString(map);
	}
	
}
