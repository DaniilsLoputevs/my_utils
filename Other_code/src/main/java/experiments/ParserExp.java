package experiments;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ParserExp {
    @SneakyThrows
    public static void main(String[] args) {
//        val src = "{\n" +
//                "            \"hs_is_unworked\":{\n" +
//                "               \"value\":\"true\"\n" +
//                "            },\n" +
//                "            \"hs_analytics_last_url\":{\n" +
//                "               \"value\":\"https://share.hsforms.com/1Jkb2sX0uQgamDikAHZYwVg4oee9\"\n" +
//                "            },\n" +
//                "            \"num_unique_conversion_events\":{\n" +
//                "               \"value\":\"1\"\n" +
//                "            },\n" +
//                "            \"hs_analytics_revenue\":{\n" +
//                "               \"value\":\"0.0\"\n" +
//                "            }" +
//                "}";
//        Map<String,Object> result = new ObjectMapper().readValue(src, HashMap.class);
//        for (Map.Entry<String, Object> entry : result.entrySet()) {
//            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
//        }


//        val src =
//               " {" +
//                       " \"vid\":896451," +
//                       " \"canonical-vid\":896451," +
//                       " \"merged-vids\":[" +
//                       "" +
//                       " ]," +
//                       " \"portal-id\":7856865," +
//                       " \"is-contact\":true," +
//                       " \"properties\":{" +
//                       "\"hs_is_unworked\":{" +
//                       "   \"value\":\"true\"" +
//                       "}," +
//                       "\"hs_analytics_last_url\":{" +
//                       "   \"value\":\"https://share.hsforms.com/1Jkb2sX0uQgamDikAHZYwVg4oee9\"" +
//                       "}," +
//                       "\"num_unique_conversion_events\":{" +
//                       "   \"value\":\"1\"" +
//                       "}," +
//                       "\"hs_analytics_revenue\":{" +
//                       "   \"value\":\"0.0\"" +
//                       "}," +
//                       "\"hs_pipeline\":{" +
//                       "   \"value\":\"contacts-lifecycle-pipeline\"" +
//                       "}" +
//                       "}" +
//                       "}";
//
//        val result = new ObjectMapper().readValue(src, Contact.class);
//        for (Map.Entry<String, Value> entry : result.getProperties().entrySet()) {
//            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
//        }
        
        
        val srcPath = "C:\\Users\\Admin\\Desktop\\testQuery.txt";
        val src = new File(srcPath);
        
        // TODO : заполнить несколько форм и проверить что всё ОК
        // TODO : заполнить 1 форму несколько раз и проверить ОК
        // TODO : сделать DTO для отдачи на бек.
        // TODO :
        val result = new ObjectMapper().readValue(src, HSQueryResult.class);
        System.out.println(result);
        result.contacts.get(0).getProperties().get("hs_form_field_name");
//        for (Map.Entry<String, Value> entry : result.getProperties().entrySet()) {
//            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
//        }
    }
    
    
    @Data
    @NoArgsConstructor
    public static class Value {
        private String value;
    }
    
    
    @Data
    @NoArgsConstructor
    public static class FormSubmission {
        @JsonProperty("conversion-id")
        private String conversionId;
        private long timestamp;
        @JsonProperty("form-id")
        private String formId;
        @JsonProperty("portal-id")
        private int portalId;
        @JsonProperty("page-url")
        private String pageUrl;
        @JsonProperty("canonical-url")
        private String canonicalUrl;
        private String title;
        @JsonProperty("form-type")
        private String formType;
        @JsonProperty("meta-data")
        private List<Object> metaData;
    }
    
    @Data
    @NoArgsConstructor
    public static class Identity {
        private String type;
        private String value;
        private Object timestamp;
        @JsonProperty("is-primary")
        private boolean isPrimary;
    }
    
    @Data
    @NoArgsConstructor
    public static class IdentityProfile {
        private int vid;
        @JsonProperty("saved-at-timestamp")
        private int savedAtTimestamp;
        @JsonProperty("deleted-changed-timestamp")
        private int deletedChangedTimestamp;
        private List<Identity> identities;
    }
    
    @Data
    @NoArgsConstructor
    public static class Contact {
        private int vid;
        @JsonProperty("canonical-vid")
        private int canonicalVid;
        @JsonProperty("merged-vids")
        private List<Object> mergedVids;
        @JsonProperty("portal-id")
        private int portalId;
        @JsonProperty("is-contact")
        private boolean isContact;
        private Map<String, Value> properties;
        @JsonProperty("form-submissions")
        private List<FormSubmission> formSubmissions;
        @JsonProperty("identity-profiles")
        private List<IdentityProfile> identityProfiles;
        @JsonProperty("merge-audits")
        private List<Object> mergeAudits;
    }
    
    @Data
    @NoArgsConstructor
    public static class HSQueryResult {
        private String query;
        private int offset;
        @JsonProperty("has-more")
        private boolean hasMore;
        private int total;
        private List<Contact> contacts;
    }
    
    
}
