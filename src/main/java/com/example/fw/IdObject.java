package com.example.fw;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdObject {

	private String id = "";

	@Override
	public String toString() {
		return this.id;
	}

	@Override
	public boolean equals(Object anObject) {
		boolean equalObjects = false;

		if(anObject != null && this.getClass() == anObject.getClass()) {
			IdObject typeObject = (IdObject)anObject;
			equalObjects = this.id.equals(typeObject.getId());
		}
		return equalObjects;
	}

	@Override
	public int hashCode() {
		int hashCodeValue = (45217 * 269) + this.id.hashCode();

		return hashCodeValue;
	}
}
