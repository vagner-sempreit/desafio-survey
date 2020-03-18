package br.com.cielo.desafio.survey.exception;

import java.util.Arrays;
import java.util.Optional;

public enum RequiredFieldsEnum {
    NAME((byte) 1, "name", "inputName"), FLAG((byte) 2, "flag", "inputFlag"),
    REGULATORY_AGENCY((byte) 3, "regulatorAgency", "inputregulatorAgency"), START_DATE((byte) 4, "startDate", "inputstartDate"),
    END_DATE((byte) 5, "endDate", "inputEndDate");
    private byte id;
    private String field;
    private String fieldId;

    private RequiredFieldsEnum(byte id, String field, String fieldId) {
	this.id = id;
	this.field = field;
	this.fieldId = fieldId;
    }

    public static RequiredFieldsEnum converter(String field) {

	return Optional.ofNullable(field).map(fieldError -> {

	    return Arrays.stream(values()).filter(requiredEnum -> requiredEnum.field.equals(fieldError)).findFirst()
		    .orElseThrow(() -> new ServeyException(ConstantEnum.INVALID_FIELD_TO_VALIDATE.getDescription()));
	}).orElseThrow(() -> new ServeyException(ConstantEnum.INVALID_NULL_TO_VALIDATE.getDescription()));

    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }
}
