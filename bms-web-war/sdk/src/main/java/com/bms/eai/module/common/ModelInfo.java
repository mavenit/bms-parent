package com.bms.eai.module.common;

import javax.validation.Valid;

import com.bms.eai.module.core.AbstractSdkEntity;

/**
 * Reference fields should be set directly on the model domain object.
 * Business objects should be passed as separate fields in subclasses
 * to be manually validated by controllers.
 * @author kul_sudhakar
 *
 */
public class ModelInfo<T extends AbstractSdkEntity> {

	@Valid
	private T model;

	private String actionMessage;

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public String getActionMessage() {
		return actionMessage;
	}

	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}
}
