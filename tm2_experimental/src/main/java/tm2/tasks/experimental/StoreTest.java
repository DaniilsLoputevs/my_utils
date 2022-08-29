package tm2.tasks.experimental;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class StoreTest {
    @Autowired
    private StringBuilder stringBuilder;
    
}
