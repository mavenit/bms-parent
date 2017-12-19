package com.bms.eai.common.lib;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
public class JsonApiUtil {

	//private static final Logger logger = LoggerFactory.getLogger(JsonApiUtil.class);

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
    public static Optional<JsonNode> toJsonNode(final Object data) {
    	 return Optional.ofNullable(mapper().valueToTree(data));
    }

    /**
     * Convert a JsonNode to a Java value
     *
     * @param json Json value to convert.
     * @param clazz Expected Java value type.
     */
    public static <A> Optional<A> fromJsonNode(JsonNode json, Class<A> clazz) {
        try {    
        	return Optional.ofNullable(mapper().treeToValue(json, clazz));
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
    public static <A> Optional<A> transferToObject(JsonNode json, Class<A> clazz) throws JsonParseException, JsonMappingException, IOException {
    	return Optional.ofNullable(mapper().readValue(json, clazz));
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
    public static <A> Optional<A> transferToObject(String json, Class<A> clazz) throws JsonParseException, JsonMappingException, IOException {
    	return Optional.ofNullable(mapper().readValue(json, clazz));
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
    @SuppressWarnings("rawtypes")
	public static List<?> transferToList(JsonNode json, Class clazz) throws JsonParseException, JsonMappingException, IOException {
    	return (List<?>) mapper().readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, clazz));
    }
    
    public static void setObjectMapper(ObjectMapper mapper) {
        objectMapper = mapper;
    }
    
    public static Optional<Map<String, Object>> convertJsonToMap(String jsonStr) throws JsonParseException, JsonMappingException, IOException {
		return Optional.ofNullable(mapper().readValue(jsonStr, new TypeReference<Map<String, Object>>() {}));
	}
    
    public static Optional<String> convertMapToJson(Map<String, Object> map) throws JsonGenerationException, JsonMappingException, IOException {
		return Optional.ofNullable(mapper().writeValueAsString(map));
	}
    
}
