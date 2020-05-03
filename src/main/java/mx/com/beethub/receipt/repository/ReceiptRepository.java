package mx.com.beethub.receipt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.com.beethub.receipt.model.entity.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

	
	List<Receipt> findByAccount(String account);
	
	@Query(value = "SELECT * FROM receipt WHERE MATCH(text_search) AGAINST (:filter IN BOOLEAN MODE)", 
			nativeQuery = true)
	List<Receipt> findReceiptsByFilter(@Param("filter") String filter);

}
