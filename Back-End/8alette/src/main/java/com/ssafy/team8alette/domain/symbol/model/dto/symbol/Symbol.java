package com.ssafy.team8alette.domain.symbol.model.dto.symbol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "symbol")
@ToString
public class
Symbol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "symbol_number", nullable = false)
	private Long symbolNumber;

	@Column(name = "symbol_name", nullable = false)
	private String symbolName;

	@Column(name = "symbol_img_name", nullable = false)
	private String symbolImgName;

	@Column(name = "symbol_code", nullable = false)
	private String symbolCode;

	@Column(name = "symbol_condition_cnt", nullable = false)
	private int symbolConditionCnt;
}
