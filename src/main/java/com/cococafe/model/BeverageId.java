package com.cococafe.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BeverageId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String beverageName;
	
	private BeverageSize beverageSize;
}
