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

import lombok.Data;
import lombok.ToString;
import mx.com.beethub.receipt.model.value.Rfc;

@Entity
@Data
@Table(name = "business")
@ToString(exclude = {"id"}, includeFieldNames = false)
public class Business {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="business_id")
	private long id;
	
	@Column(name = "name", nullable = false, length = 255)
	private String name; 
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name= "value", column=@Column(name= "rfc", nullable = true, length = 15))
	})
	private Rfc rfc;

}
