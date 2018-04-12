package com.sharmila.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;

@Entity
@Table(name="smslog")
public class SmsLog {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="text",nullable=false)
	private String text;
	@NonNull
	private long recieptent;
	@NonNull
	private long sender;
	@CreatedDate
	private Date sentDate;
	private String value;

	@OneToOne
	private MsiSdn msiSdn;
	
	public SmsLog() {
	}

	public SmsLog(int id, String text, long recieptent, long sender, Date sentDate, String value) {
		super();
		this.id = id;
		this.text = text;
		this.recieptent = recieptent;
		this.sender = sender;
		this.sentDate = sentDate;
		this.value = value;
	}

	public SmsLog(Builder builder) {
		this.text = builder.text;
		this.recieptent = builder.recieptent;
		this.sentDate = builder.sentDate;
		this.value=builder.value;
		this.sender=builder.sender;
		this.msiSdn=builder.msisdn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
	public long getRecieptent() {
		return recieptent;
	}

	public void setRecieptent(long recieptent) {
		this.recieptent = recieptent;
	}

	public long getSender() {
		return sender;
	}

	public void setSender(long sender) {
		this.sender = sender;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public MsiSdn getMsiSdn() {
		return msiSdn;
	}

	public void setMsiSdn(MsiSdn msiSdn) {
		this.msiSdn = msiSdn;
	}


	public static class Builder {
		private int id;
		private String text;
		private MsiSdn msisdn;
		private Date sentDate;
		private String value;
		private long recieptent;
		private long sender;

		public Builder id(String text) {
			this.text = text;
			return this;
		}

		public Builder msisdn(MsiSdn msisdn) {
			this.msisdn = msisdn;
			return this;
		}

		public Builder sentDate(Date sentDate) {
			this.sentDate = sentDate;
			return this;
		}

		public Builder text(String text) {
			this.text = text;
			return this;
		}
		
		
		public Builder recieptent(long recieptent) {
			this.recieptent = recieptent;
			return this;
		}
		public Builder sender(long sender) {
			this.sender = sender;
			return this;
		}

		public SmsLog build() {
			return new SmsLog(this);
		}
	}

}