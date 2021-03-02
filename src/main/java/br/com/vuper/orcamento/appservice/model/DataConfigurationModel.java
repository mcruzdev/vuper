package br.com.vuper.orcamento.appservice.model;

public class DataConfigurationModel {

	private String name;
	private boolean configured;

	public DataConfigurationModel() {
	}

	public DataConfigurationModel(String name, boolean configured) {
		this.name = name;
		this.configured = configured;
	}

	public DataConfigurationModel(String name, long quantity) {
		this.name = name;
		this.configured = quantity > 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isConfigured() {
		return configured;
	}

	public void setConfigured(boolean configured) {
		this.configured = configured;
	}
}
