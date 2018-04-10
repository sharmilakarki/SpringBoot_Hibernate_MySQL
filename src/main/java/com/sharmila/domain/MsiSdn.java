package com.sharmila.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tableMSISDN")
public class MsiSdn {

	@EmbeddedId
	private int id;
	@Column(name="msi_sdn")
	private long msiSdn;
	
	public MsiSdn() {
	}

	public MsiSdn(int id, long msiSdn) {
		this.id = id;
		this.msiSdn = msiSdn;
	}
	
	public MsiSdn(Builder builder) {
		this.id = builder.id;
		this.msiSdn =builder.msiSdn;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getMsiSdn() {
		return msiSdn;
	}
	public void setMsiSdn(long msiSdn) {
		this.msiSdn = msiSdn;
	}
	
	public static class Builder{
		private int id;
		private long msiSdn;
		
		public Builder id(int id){
			this.id=id;
			return this;
		}

		public Builder msiSdn(long msiSdn){
			this.msiSdn=msiSdn;
			return this;
		}
		
		public MsiSdn build(){
			return new MsiSdn(this);
		}
	}
}