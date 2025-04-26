package com.example.EArchive7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.EArchive7.model.CompanyArchive;
import com.example.EArchive7.repository.CompanyArchiveRepository;
import com.example.EArchive7.service.CompanyArchiveService;

import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/archive")
public class CompanyController {
	
	
	@Autowired
   private final CompanyArchiveService companyArchiveService ;

    public CompanyController(CompanyArchiveService companyArchiveService) {
      this.companyArchiveService = companyArchiveService;
    }


    
   
    
   
    
    
    
    @GetMapping("/checkStatisticalNumber")
    public @ResponseBody Boolean checkStatisticalNumber(@RequestParam String statisticalNumber) {
        return companyArchiveService.existsByStatisticalNumber(statisticalNumber);
    }


    
    

    
    // إضافة بيانات الشركة مع رفع الملفات
    @PostMapping("/add")
    public ResponseEntity<String> addCompanyArchive(
            @RequestParam("companyName") String companyName,
            @RequestParam("statisticalNumber") String statisticalNumber,
            @RequestParam("nationalId") String nationalId,
            @RequestParam("commissionername") String commissionername,

            @RequestParam("taxFile") MultipartFile taxFile,
            @RequestParam("identificationFile") MultipartFile identificationFile,
            @RequestParam("license") MultipartFile license,
            @RequestParam("chamberofcommerce") MultipartFile chamberofcommerce,
            @RequestParam("Nationalnumber") MultipartFile Nationalnumber,
            @RequestParam("Statisticsmodel") MultipartFile Statisticsmodel, 
            @RequestParam("industrial") MultipartFile industrial, 
            @RequestParam("Importers") MultipartFile Importers,
            
           @RequestParam("commercialFile") MultipartFile commercialFile){
    
    
    if (companyArchiveService.existsByStatisticalNumber(statisticalNumber)) {
        return ResponseEntity.badRequest().body("الرقم الاحصائي موجود مسبقًا");
    }


    
        CompanyArchive companyArchive = new CompanyArchive();
        companyArchive.setCompanyName(companyName);
       companyArchive.setStatisticalNumber(statisticalNumber);
        companyArchive.setNationalId(nationalId);
        companyArchive.setCommissionername(commissionername);

        try {
            companyArchive.setTaxFile(taxFile.getBytes());
            companyArchive.setIdentificationFile(identificationFile.getBytes());
            companyArchive.setLicense(license.getBytes());
            companyArchive.setChamberofcommerce(chamberofcommerce.getBytes());
            companyArchive.setNationalnumber(Nationalnumber.getBytes());
            companyArchive.setStatisticsmodel(Statisticsmodel.getBytes());
            companyArchive.setIndustrial(industrial.getBytes());
            companyArchive.setImporters(Importers.getBytes());
            companyArchive.setCommercialFile(commercialFile.getBytes());

            

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing files");
        }

        
        companyArchiveService.saveCompanyArchive(companyArchive);
        return ResponseEntity.ok("تمت إضافة الشركة بنجاح!");
}

      //  String resultMessage = companyArchiveService.saveCompanyArchive(companyArchive);


//        String resultMessage = companyArchiveService.saveCompanyArchive(companyArchive);
//        
//       if (resultMessage.equals("الرقم الاحصائي موجود مسبقآ")) {
//  
//
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMessage);
//        }
//
//        return ResponseEntity.ok(resultMessage);
//    }
//   
  
    
    
    // البحث عن الشركات 
    @GetMapping("/searche")
    public ResponseEntity<List<CompanyArchive>> searchCompanyArchivee(@RequestParam String query) {
        List<CompanyArchive> records = companyArchiveService.searchCompanyArchivee(query);
        return ResponseEntity.ok(records);
    }
    
    
    
    
     


//    @GetMapping("/")
//    public String home() {
//        return "index"; // يقوم بإرجاع index.html من مجلد templates
//    }

//   
       
//        @GetMapping("/")
//        public String showAddCompany(HttpSession session) {
//            if (session.getAttribute("username") == null) {
//                return "redirect:/login";
//            }
//            return "archive/index"; // صفحة الإضافة الرئيسية
//        }
    
    @GetMapping("/index2")
    public String showIndex2(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        return "index2"; // يجب أن يكون موجود في templates/archive/index2.html
    }


        @GetMapping("/index3")
        public String archivePage(HttpSession session) {
            if (session.getAttribute("username") == null) {
                return "redirect:/login";
            }
            return "index3";
        }
    

    
   

//        @GetMapping("/index3")
//        public String showIndex3() {
//            return "index3"; // تأكد من وجود ملف index3.html في مجلد templates
//        }
//    

        
      

        
        
    
        @GetMapping("/subpage")
        public String showSubPage() {
            return "subpage"; // اسم الملف داخل templates بدون .html
        }
        
        
        
        
        
    
    
    
    @GetMapping("/edit-company/{id}")
    public String editCompany(@PathVariable Long id, Model model) {
        Optional<CompanyArchive> companyArchive = companyArchiveService.getCompanyById(id);
        if (companyArchive.isPresent()) {
            model.addAttribute("company", companyArchive.get());
            return "editCompany"; // يجب أن تنشئ صفحة editCompany.html
        } else {
            return "redirect:/archive"; // إعادة توجيه في حال عدم العثور على الشركة
        }
    }

    
   
    
    
    
    
    
    
    
    
    @PostMapping("/update-company/{id}")
    public String updateCompany(
            @PathVariable Long id,
            @RequestParam("companyName") String companyName,
            @RequestParam("statisticalNumber") String statisticalNumber,
            @RequestParam("nationalId") String nationalId,
            @RequestParam("commissionername") String commissionername,
            @RequestParam(value = "taxFile", required = false) MultipartFile taxFile,
            @RequestParam(value = "commercialFile", required = false) MultipartFile commercialFile,
            @RequestParam(value = "license", required = false) MultipartFile license,
            @RequestParam(value = "chamberofcommerce", required = false) MultipartFile chamberofcommerce,
            @RequestParam(value = "Nationalnumber", required = false) MultipartFile Nationalnumber,
            @RequestParam(value = "identificationFile", required = false) MultipartFile identificationFile,
            @RequestParam(value = "Statisticsmodel", required = false) MultipartFile Statisticsmodel,
            @RequestParam(value = "industrial", required = false) MultipartFile industrial,
            @RequestParam(value = "Importers", required = false) MultipartFile Importers,
            Model model
    ) {
        Optional<CompanyArchive> companyArchiveOptional = companyArchiveService.getCompanyById(id);
        if (companyArchiveOptional.isPresent()) {
            CompanyArchive companyArchive = companyArchiveOptional.get();
            companyArchive.setCompanyName(companyName);
            companyArchive.setStatisticalNumber(statisticalNumber);
            companyArchive.setNationalId(nationalId);
            companyArchive.setCommissionername(commissionername);

            try {
                if (taxFile != null && !taxFile.isEmpty()) {
                    companyArchive.setTaxFile(taxFile.getBytes());
                }
                if (commercialFile != null && !commercialFile.isEmpty()) {
                    companyArchive.setCommercialFile(commercialFile.getBytes());
                }
                if (license != null && !license.isEmpty()) {
                    companyArchive.setLicense(license.getBytes());
                }
                if (chamberofcommerce != null && !chamberofcommerce.isEmpty()) {
                    companyArchive.setChamberofcommerce(chamberofcommerce.getBytes());
                }
                if (Nationalnumber != null && !Nationalnumber.isEmpty()) {
                    companyArchive.setNationalnumber(Nationalnumber.getBytes());
                }
                if (identificationFile != null && !identificationFile.isEmpty()) {
                    companyArchive.setIdentificationFile(identificationFile.getBytes());
                }
                
                if (Statisticsmodel != null && !Statisticsmodel.isEmpty()) {
                    companyArchive.setStatisticsmodel(Statisticsmodel.getBytes());
                }
                if (industrial != null && !industrial.isEmpty()) {
                    companyArchive.setIndustrial(industrial.getBytes());
                }
                if (Importers != null && !Importers.isEmpty()) {
                    companyArchive.setImporters(Importers.getBytes());
                }
            } catch (IOException e) {
                model.addAttribute("message", "حدث خطأ أثناء معالجة الملفات.");
                return "success";
            }

            companyArchiveService.saveCompanyArchive(companyArchive);
            model.addAttribute("message", "تم تحديث الشركة بنجاح!");
            return "success";
        } else {
            model.addAttribute("message", "الشركة غير موجودة.");
            return "success";
        }
    }


    // البحث عن الشركات باستخدام الاسم أو الرقم القومي
  @GetMapping("/search")
  public ResponseEntity<List<CompanyArchive>> searchCompanyArchive(@RequestParam String query) {
      List<CompanyArchive> records = companyArchiveService.searchCompanyArchive(query);
      return ResponseEntity.ok(records);
  }

    
    
}
   


        


