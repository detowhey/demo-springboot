package com.henriquealmeida.democrud.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_category")
public class Category {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@JsonIgnore
	//mappedBy = collection name
	@ManyToMany(mappedBy = "categories")
	private Set<Product> products = new HashSet<>();

}
