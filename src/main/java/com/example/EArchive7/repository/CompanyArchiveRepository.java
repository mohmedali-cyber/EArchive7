package com.example.EArchive7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.EArchive7.model.CompanyArchive;

import java.util.List;
import java.util.Optional;




@Repository  // تعريف الكلاس كمستودع للتعامل مع قاعدة البيانات
public interface CompanyArchiveRepository extends JpaRepository<CompanyArchive, Long> {
	
	

	

    // البحث عن شركة باستخدام الرقم  (يعيد كائن اختياري Optional لأنه قد لا يوجد سجل مطابق)

    Optional<CompanyArchive> findByStatisticalNumber(String statisticalNumber);


    
    
    List<CompanyArchive> findByStatisticalNumberContaining(String statisticalNumber);

    
    List<CompanyArchive> findByCompanyNameContainingOrStatisticalNumberContaining(String companyName, String statisticalNumber);

    
    
    
   
  
}



