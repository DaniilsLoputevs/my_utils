package experiments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public class FunctionConstructor {
    
    interface Extension {
        String getExtension();
    }
    
    @Data
    @NoArgsConstructor
    class MySimpleFile implements Extension {
        private String fullName;
        
        @Override
        public String getExtension() {
            return fullName.split("\\.")[1];
        }
    }
    
    static final List<String> BASIC_OK_EXTENSIONS = Arrays.asList("pdf", "txt", "img");
    
    public <T extends Extension> Function<List<String>, Boolean> funcConstructor(
            Supplier<T> objectProvider,
            Function<T, List<String>> dynamicExtensionsProvider
    ) {
        return (extensions) -> {
            val myObj = objectProvider.get();
            val ext = myObj.getExtension();
            System.out.println("print obj is [" + myObj.getClass() + "] : " + myObj);
            
            var rsl = extensions.contains(ext);
            if (dynamicExtensionsProvider != null)
                rsl = dynamicExtensionsProvider.apply(myObj).contains(ext);
            
            return rsl;
        };
    }
    
    private void run() {
        MySimpleFile file = new FckFile(UUID.randomUUID());
        file.setFullName("my_nft.tk");
        val validationFunc = funcConstructor(
                () -> file,
                f -> f instanceof FckFile
                        ? Collections.singletonList("tk")
                        : Arrays.asList("jpg", "jpeg")
        
        );
        
        val result = validationFunc.apply(BASIC_OK_EXTENSIONS);
        System.out.println("result = " + result);
    }
    
    @AllArgsConstructor
    @Data
    class FckFile extends MySimpleFile {
        private UUID ntf;
    }
}
