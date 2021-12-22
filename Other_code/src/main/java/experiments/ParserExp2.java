package experiments;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;

import java.io.File;
import java.util.List;

public class ParserExp2 {
    @SneakyThrows
    public static void main(String[] args) {
        
        val srcPath = "C:\\Users\\Admin\\Desktop\\testQuery.txt";
        val src = new File(srcPath);
        
        
        
        val result = new ObjectMapper().readValue(src, FormStructure.class);
        System.out.println(result);
//        result.contacts.get(0).getProperties().get("hs_form_field_name");
//        for (Map.Entry<String, Value> entry : result.getProperties().entrySet()) {
//            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
//        }
    }
    
    private void findFieldInFormAndSetValue(FormStructure form, String fieldName, String  fieldValue) {
        for (val fieldGroup : form.getFormFieldGroups()) {
            for (val field : fieldGroup.getFields()) {
                if (field.getName().equals(fieldName)) {
                    field.setDefaultValue(fieldValue);
                    return;
                }
            }
        }
//        throw new IllegalArgumentException("The field=\""+fieldName+"\" was not found in formStructure=" + form);
    }
    
    
    @Data
    @NoArgsConstructor
    public static class Option{
        public String label;
        public String value;
        public int displayOrder;
        public double doubleData;
        public boolean hidden;
        public String description;
        public boolean readOnly;
    }
    
    @Data
    @NoArgsConstructor
    public static class Validation{
        public String name;
        public String message;
        public String data;
        public boolean useDefaultBlockList;
        public List<Object> blockedEmailAddresses;
    }
    
    @Data
    @NoArgsConstructor
    public static class Field{
        public String name;
        public String label;
        public String type;
        public String fieldType;
        public String description;
        public String groupName;
        public int displayOrder;
        public boolean required;
        public List<Object> selectedOptions;
        public List<Option> options;
        public Validation validation;
        public boolean enabled;
        public boolean hidden;
        public String defaultValue;
        public boolean isSmartField;
        public String unselectedLabel;
        public String placeholder;
        public List<Object> dependentFieldFilters;
        public boolean labelHidden;
        public String propertyObjectType;
        public List<Object> metaData;
        public String objectTypeId;
    }
    
    @Data
    @NoArgsConstructor
    public static class RichText{
        public String content;
        public String type;
    }
    
    @Data
    @NoArgsConstructor
    public static class FormFieldGroup{
        public List<Field> fields;
        @JsonProperty("default")
        public boolean isDefault;
        public boolean isSmartGroup;
        public RichText richText;
        public boolean isPageBreak;
    }
    
    @Data
    @NoArgsConstructor
    public static class MetaData{
        public String name;
        public String value;
    }
    
    @Data
    @NoArgsConstructor
    public static class MultivariateTest{
        public List<Object> variants;
        public int startAtTimestamp;
        public int endAtTimestamp;
        public String winningVariantId;
        public boolean finished;
        public String controlId;
    }
    
    @Data
    @NoArgsConstructor
    public static class FormStructure {
        public int portalId;
        public String guid;
        public String name;
        public String action;
        public String method;
        public String cssClass;
        public String redirect;
        public String submitText;
        public String followUpId;
        public String notifyRecipients;
        public String leadNurturingCampaignId;
        public List<FormFieldGroup> formFieldGroups;
        public long createdAt;
        public long updatedAt;
        public String performableHtml;
        public String migratedFrom;
        public boolean ignoreCurrentValues;
        public List<MetaData> metaData;
        public boolean deletable;
        public String inlineMessage;
        public String tmsId;
        public boolean captchaEnabled;
        public String campaignGuid;
        public boolean cloneable;
        public boolean editable;
        public String formType;
        public int deletedAt;
        public String themeName;
        public int parentId;
        public String style;
        public boolean isPublished;
        public int publishAt;
        public int unpublishAt;
        public int publishedAt;
        public MultivariateTest multivariateTest;
        public int kickbackEmailWorkflowId;
        public String kickbackEmailsJson;
        public String customUid;
        public boolean createMarketableContact;
        public int editVersion;
        public String thankYouMessageJson;
        public String themeColor;
        public boolean alwaysCreateNewCompany;
        public long internalUpdatedAt;
        public int businessUnitId;
        public String portableKey;
    }
    
    
    
}
