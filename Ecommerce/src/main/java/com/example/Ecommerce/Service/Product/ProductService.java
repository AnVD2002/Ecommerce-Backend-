package com.example.Ecommerce.Service.Product;
import com.example.Ecommerce.DTO.Request.*;
import com.example.Ecommerce.Entity.Products.*;
import com.example.Ecommerce.Repository.ProductRepo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
@Service
public class ProductService implements IProductService{
    @Autowired
    CategoriesRepository categoriesRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ColorRepository colorRepository;
    @Autowired
    SizeRepository sizeRepository;
    @Autowired
    ProductVarientRepository productVarientRepository;
    @Autowired
    ProductTypeRepository productTypeRepository;
    @Transactional
    public ResponseEntity<?> DeleteProduct(DeleteProductDTO deleteProductDTO){
        Optional<ProductVarients> productVariant = productVarientRepository.findByProductIdAndColorIdAndSizeId(deleteProductDTO.getColorID(),deleteProductDTO.getSizeID(),deleteProductDTO.getProductID());
        if(productVariant.isPresent()){
            productVarientRepository.delete(productVariant.get());
            return new ResponseEntity<>("Lưu thành công ", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("khong tim thay san pham ", HttpStatus.NOT_FOUND);
        }
    }
    @Transactional
    public ResponseEntity<?> GetAllColor(){
        return new ResponseEntity<>(colorRepository.findAll(),HttpStatus.OK);
    }
    @Transactional
    public ResponseEntity<?> GetAllSize(){
        return new ResponseEntity<>(sizeRepository.findAll(),HttpStatus.OK);
    }
    @Transactional
    public ResponseEntity<?> CreateProduct(CreateProductDTO createProductDTO){
        Optional<Categories> category = categoriesRepository.findById(createProductDTO.getProductCategoryID());
        Optional<ProductType> productType = productTypeRepository.findById(createProductDTO.getProductTypeID());
        if(category.isPresent()&& productType.isPresent()){
            Products product = new Products();
            product.setName(createProductDTO.getProductName());
            product.setDescription(createProductDTO.getDescription());
            product.setProductImage(createProductDTO.getProductImage());
            product.setCategories(category.get());
            product.setProductType(productType.get());
            product.setProductTypeID(createProductDTO.getProductTypeID());
            product.setCategoryID(product.getCategoryID());
            productRepository.save(product);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Khong ton tai cate or productType", HttpStatus.NOT_FOUND);
        }
    }
    @Transactional
    public ResponseEntity<?> CreateProductVariant(CreateProductVariantDTO createProductVariantDTO){
        Optional<Products> product = productRepository.findById(createProductVariantDTO.getProductID());
        Optional<Colors> color = colorRepository.findById(createProductVariantDTO.getColorID());
        Optional<Sizes> size = sizeRepository.findById(createProductVariantDTO.getSizeID());
        if(product.isEmpty()){
            return new ResponseEntity<>("Khong ton tai",HttpStatus.NOT_FOUND);
        }
        if(color.isEmpty()){
            return new ResponseEntity<>("Khong ton tai", HttpStatus.NOT_FOUND);
        }
        if(size.isEmpty()){
            return new ResponseEntity<>("Khong ton tai", HttpStatus.NOT_FOUND);
        }
        ProductVarients productVariant = new ProductVarients();
        productVariant.setOldPrice(createProductVariantDTO.getOldPrice());
        productVariant.setNewPrice(createProductVariantDTO.getNewPrice());
        productVariant.setColors(color.get());
        productVariant.setSizes(size.get());
        productVariant.setProducts(product.get());
        productVariant.setStock(createProductVariantDTO.getStock());
        productVarientRepository.save(productVariant);
        return new ResponseEntity<>(productVariant,HttpStatus.OK);
    }
    @Transactional
    public ResponseEntity<?> GetAllProduct(){
        return ResponseEntity.ok(productRepository.findAll());
    }
    @Transactional
    public ResponseEntity<?> GetMenProductOutStanding(){return ResponseEntity.ok(productRepository.GetProductOutstandingProductMen());}
    @Transactional
    public ResponseEntity<?> GetWomenProductOutStanding(){return ResponseEntity.ok(productRepository.GetProductOutstandingProductWomen());}
    @Transactional
    public ResponseEntity<?> GetKidProductOutStanding(){return ResponseEntity.ok(productRepository.GetProductOutstandingProductKid());}
    @Transactional
    public ResponseEntity<?> GetProductOutStanding(){return ResponseEntity.ok(productRepository.GetProductOutstandingProduct());}
}

