package com.suchocki.bookfair.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "school")
public class School {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	public School() {

	}

	public School(String name) {
		this.name = name;
	}

	public School(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String convertableForm() {
		StringBuilder builder = new StringBuilder();
		builder.append(id);
		builder.append(";");
		builder.append(name);
		builder.append("|");
		builder.append(address);
		return builder.toString();
	}

	@Override
	public String toString() {
		return "School [id=" + id + ", name=" + name + ", address=" + address + "]";
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof School)) {
			return false;
		}
		School school = (School) object;
		return school.getId() == this.id && school.getName().equals(this.name)
				&& school.getAddress().equals(this.address);
	}

}
