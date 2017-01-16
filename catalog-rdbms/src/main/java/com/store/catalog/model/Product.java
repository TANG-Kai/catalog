package com.store.catalog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_PRODUCT")
@Access(AccessType.FIELD)
public  class Product implements AbstractBean {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6699161208358545698L;

	// ======================================
    // =             Attributes             =
    // ======================================
	@Id
	@Column(name = "id")
    private Long id;

	@Column(name = "name")
    private String name;

	@Column(name = "description")
    private String description;

	
	//@JsonBackReference is used in pair with @JsonManagedReference to solve the Infinite recursion problem
	//For avoiding the problem, linkage is handled such that the property annotated with @JsonManagedReference annotation is handled normally (serialized normally, no special handling for deserialization) and the property annotated with @JsonBackReference annotation is not serialized; and during deserialization, its value is set to instance that has the "managed" (forward) link.
	//TODO: Test if this is still necessary(since there is no @JsonManagedReference in Category.java)
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "category_fk",
			nullable = false)
    private Category category;

	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY,
			mappedBy = "product",
			cascade = CascadeType.ALL)
    private Set<Item> items = new HashSet<Item>();
    
 

    // ======================================
    // =            Constructors            =
    // ======================================
    public Product() {
    }

    public Product(final Long id, final String name, final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
    	this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
    	this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
    	this.name = name;
    }


    public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

    public Set<Item> getItems() {
        return items;
    }   
    
	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id) .append(name).append(description).hashCode();
    }


}
