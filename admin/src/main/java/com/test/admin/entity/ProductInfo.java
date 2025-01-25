package com.test.admin.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.test.admin.board.Board;
import com.test.admin.dto.ProductInfoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Builder
@Table(name = "productInfo")
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo extends Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;

	private String productImage;

	@Column(name = "CompanyName")
	private String companyName;
	private String productName;

	@Column(name = "ReportNo")
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

	@ManyToMany
	@JoinTable(name = "ingredientProduct", joinColumns = @JoinColumn(name = "product_seq"), inverseJoinColumns = @JoinColumn(name = "ingredient_seq"))
	private List<Ingredient> ingredients;

	@Override
	public ProductInfoDTO toDTO() {
		ProductInfoDTO.ProductInfoDTOBuilder builder = ProductInfoDTO.builder()
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
				.ifPresent(ings -> builder.ingredients(ings.stream().map(Ingredient::toDTO).toList()));

		return builder.build();
	}
}
