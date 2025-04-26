package com.example.EArchive7.service;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EArchive7.model.CompanyArchive;
import com.example.EArchive7.repository.CompanyArchiveRepository;


import java.util.List;
import java.util.Optional;


//import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CompanyArchiveService {

    private final CompanyArchiveRepository companyArchiveRepository;

    public CompanyArchiveService(CompanyArchiveRepository companyArchiveRepository) {
        this.companyArchiveRepository = companyArchiveRepository;
    }

    // التحقق من وجود الرمز
    public boolean existsByStatisticalNumber(String statisticalNumber) {
        return companyArchiveRepository.findByStatisticalNumber(statisticalNumber).isPresent();
    }

    // حفظ بيانات الشركة (مع ملفات byte[]) ← مهم يكون داخل Transaction
    @Transactional
    public String saveCompanyArchive(CompanyArchive companyArchive) {
        companyArchiveRepository.save(companyArchive);
        return "تمت إضافة الشركة بنجاح!";
    }

    // البحث باستخدام الاسم أو الرمز
    public List<CompanyArchive> searchCompanyArchive(String query) {
        return companyArchiveRepository.findByCompanyNameContainingOrStatisticalNumberContaining(query, query);
    }

    // البحث باستخدام الرمز فقط
    public List<CompanyArchive> searchCompanyArchivee(String query) {
        return companyArchiveRepository.findByStatisticalNumberContaining(query);
    }

    // استرجاع شركة باستخدام ID (يُفضل يكون Transactional مع byte[])
    @Transactional(readOnly = true)
    public Optional<CompanyArchive> getCompanyById(Long id) {
        return companyArchiveRepository.findById(id);
    }
}








