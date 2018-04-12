package com.sharmila.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tableMSISDNV11")
public class MsiSdn {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="msisdn")
	private long msiSdn;
	
	public MsiSdn() {
	}

	public MsiSdn(long msiSdn) {
		super();
		this.msiSdn = msiSdn;
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