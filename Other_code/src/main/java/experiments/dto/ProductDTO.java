package experiments.dto;

import lombok.Value;

public enum ProductDTO {;
    private interface Id {  Long getId(); }
    private interface Name { String getName(); }
    private interface Price {  Double getPrice(); }
    private interface Cost {  Double getCost(); }
    
    public enum Request{;
        @Value
        public static class Create implements Name, Price, Cost {
            String name;
            Double price;
            Double cost;
        }
    }
    
    public enum Response{;
        @Value public static class Public implements Id, Name, Price {
            Long id;
            String name;
            Double price;
        }
        
        @Value public static class Private implements Id, Name, Price, Cost {
            Long id;
            String name;
            Double price;
            Double cost;
        }
    }
    
    
    public static <T extends Price & Cost> Double getMarkup(T dto){
        return (dto.getPrice() - dto.getCost()) / dto.getCost();
    }
}