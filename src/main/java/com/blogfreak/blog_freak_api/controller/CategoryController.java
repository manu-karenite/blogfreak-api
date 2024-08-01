package com.blogfreak.blog_freak_api.controller;

import com.blogfreak.blog_freak_api.dto.CreateCategoryDTO;
import com.blogfreak.blog_freak_api.dto.UpdateCategoryDTO;
import com.blogfreak.blog_freak_api.entity.Category;
import com.blogfreak.blog_freak_api.oas.schema.error.Exception400;
import com.blogfreak.blog_freak_api.oas.schema.error.Exception404;
import com.blogfreak.blog_freak_api.oas.schema.error.Exception500;
import com.blogfreak.blog_freak_api.oas.schema.success.SuccessCategory;
import com.blogfreak.blog_freak_api.oas.schema.success.SuccessListOfAllCategories;
import com.blogfreak.blog_freak_api.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    @Operation(
            operationId = "getAllCategories",
            description = "Get list of all categories",
            summary = "Get list of all categories")
    @ApiResponse(
            responseCode = "200",
            content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SuccessListOfAllCategories.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    @Tag(name = "Categories")
    public ResponseEntity<GlobalResponseEntity> getAllCategories() {
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(HttpStatus.OK, categoryService.getAllCategories()), HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryId}")
    @Operation(
            operationId = "getCategoryById",
            description = "Get category details by id",
            summary = "Get category details by id")
    @ApiResponse(
            responseCode = "200",
            content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessCategory.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception404.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    @Tag(name = "Categories")
    public ResponseEntity<GlobalResponseEntity> getCategoryById(@PathVariable String categoryId) {
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(HttpStatus.OK, categoryService.getCategoryById(categoryId)), HttpStatus.OK);
    }

    @PostMapping("/categories")
    @Operation(operationId = "createCategory", description = "Create a category", summary = "Create a category")
    @Tag(name = "Categories")
    @ApiResponse(
            responseCode = "201",
            content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessCategory.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception400.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    public ResponseEntity<GlobalResponseEntity> createCategory(
            @Valid @RequestBody CreateCategoryDTO createCategoryDTORequest) {
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(
                        HttpStatus.CREATED, categoryService.createCategory(createCategoryDTORequest)),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/categories/{categoryId}")
    @Operation(operationId = "deleteCategory", description = "Delete a category", summary = "Delete a category")
    @Tag(name = "Categories")
    @ApiResponse(
            responseCode = "410",
            content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessCategory.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception404.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    public ResponseEntity<GlobalResponseEntity> deleteCategory(@PathVariable String categoryId) {
        Category deletedCategory = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new GlobalResponseEntity<>(HttpStatus.GONE, deletedCategory), HttpStatus.GONE);
    }

    @PatchMapping("/categories/{categoryId}")
    @Operation(operationId = "updateCategory", description = "Update a category", summary = "Update a category")
    @Tag(name = "Categories")
    @ApiResponse(
            responseCode = "200",
            content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessCategory.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception400.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception404.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    public ResponseEntity<GlobalResponseEntity> updateCategory(
            @PathVariable String categoryId, @Valid @RequestBody UpdateCategoryDTO updateCategoryDTO) {
        Category updatedCategory = categoryService.updateCategory(categoryId, updateCategoryDTO);
        return new ResponseEntity<>(new GlobalResponseEntity<>(HttpStatus.OK, updatedCategory), HttpStatus.OK);
    }
}
