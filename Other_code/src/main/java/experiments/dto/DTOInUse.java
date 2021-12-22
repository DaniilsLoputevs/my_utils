package experiments.dto;

import lombok.val;

public class DTOInUse {
    public static void main(String[] args) {
        val req = new ProductDTO.Request.Create("name ", 20d, 40d);
        System.out.println(req);
        
//        new ProductDTO();
    }
    
  
}
