package com.example.Ecommerce.Service.Product;

import com.example.Ecommerce.DTO.Request.AddProductDTO;
import com.example.Ecommerce.DTO.Request.DeleteProductDTO;
import com.example.Ecommerce.DTO.Request.UpdateProductDTO;
import org.springframework.http.ResponseEntity;

public interface IProductService {
    public ResponseEntity<?> DeleteProduct(DeleteProductDTO deleteProductDTO);
    public ResponseEntity<?> GetAllColor();
    public ResponseEntity<?> GetAllSize();
}
