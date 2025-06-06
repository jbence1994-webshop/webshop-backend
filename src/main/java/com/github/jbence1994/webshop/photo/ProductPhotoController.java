package com.github.jbence1994.webshop.photo;

import com.github.jbence1994.webshop.product.Product;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products/{productId}/photos")
@AllArgsConstructor
@Validated
public class ProductPhotoController {
    private final PhotoService<Product, ProductPhoto> productPhotoService;
    private final PhotoMapper photoMapper;
    private final PhotoUrlBuilder photoUrlBuilder;

    @PostMapping
    public ResponseEntity<PhotoResponse> uploadProductPhoto(
            @PathVariable Long productId,
            @FileNotEmpty @RequestParam("file") MultipartFile file
    ) {
        var uploadPhotoDto = photoMapper.toDto(file);
        var productPhoto = productPhotoService.uploadPhoto(productId, uploadPhotoDto);

        var fileName = productPhoto.fileName();
        var url = photoUrlBuilder.buildUrl(fileName);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new PhotoResponse(fileName, url));

    }

    @GetMapping
    public List<PhotoResponse> getProductPhotos(@PathVariable Long productId) {
        var productPhotos = productPhotoService.getPhotos(productId);

        return productPhotos.stream()
                .map(productPhoto -> {
                    var fileName = productPhoto.fileName();
                    var url = photoUrlBuilder.buildUrl(fileName);

                    return new PhotoResponse(fileName, url);
                })
                .toList();
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<Void> deleteProductPhoto(
            @PathVariable Long productId,
            @PathVariable String fileName
    ) {
        productPhotoService.deletePhoto(productId, fileName);

        return ResponseEntity.noContent().build();
    }
}
