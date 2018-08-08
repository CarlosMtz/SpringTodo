package com.springmongo.spring.rest;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class Item {
	@Id
    private String id;

    @NotBlank
    @Size(max=100)
    @Indexed(unique=true)
    
	private String description;
	private boolean checked;
	private Date createdAt = new Date();

	public Item() {
	}

	public Item(String id, String description, boolean checked, Date createdAt) {
		super();
		this.id = id;
		this.description = description;
		this.checked = checked;
		this.createdAt = createdAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", description=" + description + ", checked=" + checked + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
