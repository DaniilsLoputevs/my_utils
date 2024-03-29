package java8.misuses.optional.usage;


import java8.structures.Annotations.Good;
import java8.structures.Annotations.Ugly;
import java8.structures.User;

import java.util.Optional;

public class InternalOptionalUsage {
    @Ugly
    class UnclearOptionalDependencyWithCheckForNull {
        private Printer printer;
        
        public void process(User user) {
            //some processing
            if (printer != null) {
                printer.print(user);
            }
        }
        
        public void setPrinter(Printer printer) {
            this.printer = printer;
        }
    }
    
    @Good
    class ValidInternalOptionalDependency {
        private Optional<Printer> printer = Optional.empty();
        
        public void process(User user) {
            //some processing
            printer.ifPresent(p -> p.print(user));
        }
        
        public void setPrinter(Printer printer) {
            this.printer = Optional.ofNullable(printer);
        }
    }
    
    interface Printer {
        void print(User user);
    }
}
