package java8.misuses.optional.usage;

import java8.structures.Annotations.Good;
import java8.structures.Annotations.Ugly;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalConstructorParameters {
    @Ugly
    class OptionalLeaksOutsideClass {
        public List<Email> create() {
            Email noAttachment = new Email("First!", "No attachment", Optional.empty());
            Attachment attachment = new Attachment("/mnt/files/image.png", 370);
            Email withAttachment = new Email("Second!", "With attachment", Optional.of(attachment));
            return Arrays.asList(noAttachment, withAttachment);
        }
    
        @RequiredArgsConstructor
        @Getter
        class Email implements Serializable {
            private final String subject;
            private final String body;
            private final Optional<Attachment> attachment;
        }
    }
    
    @Good
    class OverloadedConstructors {
        public List<Email> create() {
            Email noAttachment = new Email("First!", "No attachment");
            Attachment attachment = new Attachment("/mnt/files/image.png", 370);
            Email withAttachment = new Email("Second!", "With attachment", attachment);
            return Arrays.asList(noAttachment, withAttachment);
        }
    
        @RequiredArgsConstructor
        @Getter
        class Email implements Serializable {
            private final String subject;
            private final String body;
            private final Attachment attachment;
            
            
            Email(String subject, String body) {
                this(subject, body, null);
            }
        }
    }
    
    @RequiredArgsConstructor
    @Getter
    static class Attachment {
        private final String path;
        private final int size;
    }
}
