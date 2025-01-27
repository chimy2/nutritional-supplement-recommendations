package com.test.admin.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.test.admin.board.BoardDTO;
import com.test.admin.entity.ProductInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoDTO extends BoardDTO<ProductInfo> {

	private Long seq;
	
	private String productImage;
	
	private String companyName;
	private String productName;
	
	private String reportNo;

	private LocalDateTime registrationDate;
	private String expirationDate;
	private String medicationType;
	private String ingestionMethod;
	private String packagingMaterial;
	private String packagingMethod;
	private String preservation;
	private String precautionsForIngestion;
	private String functionalContent;
	private String standardsAndSpecifications;
	
	private List<IngredientDTO> ingredients;
	
	private List<Long> ingredientSeqs;

	@Override
	public ProductInfo toEntity() {
		ProductInfo.ProductInfoBuilder builder = ProductInfo.builder()
	            .seq(this.seq)
	            .productImage(this.productImage)
	            .companyName(this.companyName)
	            .productName(this.productName)
	            .reportNo(this.reportNo)
	            .registrationDate(this.registrationDate)
	            .expirationDate(this.expirationDate)
	            .medicationType(this.medicationType)
	            .ingestionMethod(this.ingestionMethod)
	            .packagingMaterial(this.packagingMaterial)
	            .packagingMethod(this.packagingMethod)
	            .preservation(this.preservation)
	            .precautionsForIngestion(this.precautionsForIngestion)
	            .functionalContent(this.functionalContent)
	            .standardsAndSpecifications(this.standardsAndSpecifications);

		Optional.ofNullable(this.ingredients)
				.ifPresent(ings -> builder.ingredients(ings.stream().map(IngredientDTO::toEntity).toList()));
		
		 return builder.build();
	}
}
