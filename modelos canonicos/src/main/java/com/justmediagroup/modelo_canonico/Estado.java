package com.justmediagroup.modelo_canonico;

/**
 * @author jesus
 *
 */
public enum Estado {

	ACTIVO("ACTIVO"), INACTIVO("INACTIVO");

	private String value;

	Estado(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static Estado fromValue(String value) {
		for (Estado b : Estado.values()) {
			if (b.value.equals(value)) {
				return b;
			}
		}
		throw new IllegalArgumentException("Unexpected value '" + value + "'");
	}

}
