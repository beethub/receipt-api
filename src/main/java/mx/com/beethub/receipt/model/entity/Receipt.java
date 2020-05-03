package mx.com.beethub.receipt.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mx.com.beethub.receipt.model.value.File;
import mx.com.beethub.receipt.model.value.ReceiptStatus;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Access(AccessType.FIELD)
@Table(name = "receipt")
@ToString(exclude = {"id"})
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="receipt_id")
	private long id;
	
	@Column(name="account", nullable = false)
	private String account;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="business_id")
	private Business business;
	
	@Column(name = "date", nullable = true)
	private LocalDate date;
	
	@Column(name = "amount", nullable = true, precision = 20)
	private Long amount;
	
	@CreatedDate
	@Column(name= "created_at")
	private LocalDateTime createdAt;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "key", column = @Column(name= "ticket_key") )
	})
	private File ticket;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="invoice_id")
	private Invoice invoice;
	
	@Basic
    @Enumerated(EnumType.STRING)
	@Column(name= "status", columnDefinition = "ENUM('IN_PROGRESS', 'GENERATING','DONE','ERROR')")
	private ReceiptStatus status;
	
	@Transient
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY MM dd");
	
	@Access(AccessType.PROPERTY)
	@Column(name="text_search")
	public String getTextSearch() {
		StringBuilder str = new StringBuilder();
		
		str.append(this.account);
		
		if(this.business != null) {
			str.append(" " + this.business.getName());
			if(this.amount != null) str.append(" " + new BigDecimal(this.amount).movePointLeft(2));
			if(this.date != null) str.append(" " + formatter.format(this.date));
		}
		else {
			str.append(" Identificando");
		}
		if(this.createdAt != null) str.append(" " + formatter.format(this.createdAt.toLocalDate()));
		
		return str.toString();
	}
	
	public void setTextSearch(String textSearch) {
		
	}
	
}
