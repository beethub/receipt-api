package mx.com.beethub.receipt.model.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mx.com.beethub.receipt.model.value.File;

@Data
@Entity
@Table(name = "invoice")
@ToString(exclude = {"id"})
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="invoice_id")
	private long id;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "key", column = @Column(name= "xml_key") )
	})
	private File xml;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "key", column = @Column(name= "pdf_key") )
	})
	private File pdf;
	
}
