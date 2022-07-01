package family.contacts;

import com.fasterxml.jackson.annotation.*;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Name",
        "Given Name",
        "Additional Name",
        "Family Name",
        "Yomi Name",
        "Given Name Yomi",
        "Additional Name Yomi",
        "Family Name Yomi",
        "Name Prefix",
        "Name Suffix",
        "Initials",
        "Nickname",
        "Short Name",
        "Maiden Name",
        "Birthday",
        "Gender",
        "Location",
        "Billing Information",
        "Directory Server",
        "Mileage",
        "Occupation",
        "Hobby",
        "Sensitivity",
        "Priority",
        "Subject",
        "Notes",
        "Language",
        "Photo",
        "Group Membership",
        "Phone 1 - Type",
        "Phone 1 - Value",
        "Phone 2 - Type",
        "Phone 2 - Value"
})
@Generated("jsonschema2pojo")
public class GoogleContact {
    
    @CsvBindByPosition(position = 0) public String name;
    @CsvBindByPosition(position = 1) public String givenName;
    @CsvBindByPosition(position = 2) public String additionalName;
    @CsvBindByPosition(position = 3) public String familyName;
    @CsvBindByPosition(position = 4) public String yomiName;
    @CsvBindByPosition(position = 5) public String givenNameYomi;
    @CsvBindByPosition(position = 6) public String additionalNameYomi;
    @CsvBindByPosition(position = 7) public String familyNameYomi;
    @CsvBindByPosition(position = 8) public String namePrefix;
    @CsvBindByPosition(position = 9) public String nameSuffix;
    @CsvBindByPosition(position = 10) public String initials;
    @CsvBindByPosition(position = 11) public String nickname;
    @CsvBindByPosition(position = 12) public String shortName;
    @CsvBindByPosition(position = 13) public String maidenName;
    @CsvBindByPosition(position = 14) public String birthday;
    @CsvBindByPosition(position = 15) public String gender;
    @CsvBindByPosition(position = 16) public String location;
    @CsvBindByPosition(position = 17) public String billingInformation;
    @CsvBindByPosition(position = 18) public String directoryServer;
    @CsvBindByPosition(position = 19) public String mileage;
    @CsvBindByPosition(position = 20) public String occupation;
    @CsvBindByPosition(position = 21) public String hobby;
    @CsvBindByPosition(position = 22) public String sensitivity;
    @CsvBindByPosition(position = 23) public String priority;
    @CsvBindByPosition(position = 24) public String subject;
    @CsvBindByPosition(position = 25) public String notes;
    @CsvBindByPosition(position = 26) public String language;
    @CsvBindByPosition(position = 27) public String photo;
    @CsvBindByPosition(position = 28) public String groupMembership;
    @CsvBindByPosition(position = 29) public String phone1Type;
    @CsvBindByPosition(position = 30) public String phone1Value;
    @CsvBindByPosition(position = 31) public String phone2Type;
    @CsvBindByPosition(position = 32) public String phone2Value;

//    @JsonProperty("Name")
//    public String name;
//    @JsonProperty("Given Name")
//    public String givenName;
//    @JsonProperty("Additional Name")
//    public String additionalName;
//    @JsonProperty("Family Name")
//    public String familyName;
//    @JsonProperty("Yomi Name")
//    public String yomiName;
//    @JsonProperty("Given Name Yomi")
//    public String givenNameYomi;
//    @JsonProperty("Additional Name Yomi")
//    public String additionalNameYomi;
//    @JsonProperty("Family Name Yomi")
//    public String familyNameYomi;
//    @JsonProperty("Name Prefix")
//    public String namePrefix;
//    @JsonProperty("Name Suffix")
//    public String nameSuffix;
//    @JsonProperty("Initials")
//    public String initials;
//    @JsonProperty("Nickname")
//    public String nickname;
//    @JsonProperty("Short Name")
//    public String shortName;
//    @JsonProperty("Maiden Name")
//    public String maidenName;
//    @JsonProperty("Birthday")
//    public String birthday;
//    @JsonProperty("Gender")
//    public String gender;
//    @JsonProperty("Location")
//    public String location;
//    @JsonProperty("Billing Information")
//    public String billingInformation;
//    @JsonProperty("Directory Server")
//    public String directoryServer;
//    @JsonProperty("Mileage")
//    public String mileage;
//    @JsonProperty("Occupation")
//    public String occupation;
//    @JsonProperty("Hobby")
//    public String hobby;
//    @JsonProperty("Sensitivity")
//    public String sensitivity;
//    @JsonProperty("Priority")
//    public String priority;
//    @JsonProperty("Subject")
//    public String subject;
//    @JsonProperty("Notes")
//    public String notes;
//    @JsonProperty("Language")
//    public String language;
//    @JsonProperty("Photo")
//    public String photo;
//    @JsonProperty("Group Membership")
//    public String groupMembership;
//    @JsonProperty("Phone 1 - Type")
////@CsvBindByPosition(position = 29)
//    public String phone1Type;
//    @JsonProperty("Phone 1 - Value")
////@CsvBindByPosition(position = 30)
//    public String phone1Value;
//    @JsonProperty("Phone 2 - Type")
////@CsvBindByPosition(position = 31)
//    public String phone2Type;
//    @JsonProperty("Phone 2 - Value")
////@CsvBindByPosition(position = 32)
//    public String phone2Value;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
    
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    
}