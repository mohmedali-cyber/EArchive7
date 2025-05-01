package com.example.EArchive7.model;


import jakarta.persistence.*;

@Entity
//@Table(name = "db") // جدول أرشيف الشركات


@Table(
	    name = "db",
	    indexes = {
	        @Index(name = "idx_company_name", columnList = "companyName"),
	        @Index(name = "idx_statistical_number", columnList = "statisticalNumber"),
	        @Index(name = "idx_national_id", columnList = "nationalId")
	    }
	)


public class CompanyArchive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String statisticalNumber;//الرمز
    private String nationalId;//الرقم الوطني
    private String commissionername;//اسم المفوض

    @Column(columnDefinition = "bytea")
    private byte[] taxFile;  //الضرائب
    
    @Column(columnDefinition = "bytea")
   private byte[] commercialFile;//التجاري
   // private List<byte[]> commercialFiles; // قائمة لتخزين الصور

    @Column(columnDefinition = "bytea")
    private byte[] license;  //رخصة
    
    @Column(columnDefinition = "bytea")

    private byte[] chamberofcommerce;//الغرفة التجارية
    
    @Column(columnDefinition = "bytea")
    private byte[] identificationFile;//التعريف

    @Column(columnDefinition = "bytea")

    private byte[] Nationalnumber;// صورة الرقم الوطني

    @Column(columnDefinition = "bytea")

    private byte[] Statisticsmodel;// نمودج الاحصاء

    @Column(columnDefinition = "bytea")

    private byte[] industrial; // سجل صناعي

    @Column(columnDefinition = "bytea")

    private byte[] Importers; // سجل مستوردين

    
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStatisticalNumber() {
		return statisticalNumber;
	}

	public void setStatisticalNumber(String statisticalNumber) {
		this.statisticalNumber = statisticalNumber;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}
	
	public String getCommissionername() {
		return commissionername;
	}

	public void setCommissionername(String commissionername) {
		this.commissionername = commissionername;
	}

	
	public byte[] getTaxFile() {
		return taxFile;
	}

	public void setTaxFile(byte[] taxFile) {
		this.taxFile = taxFile;
	}
	
	public byte[] getCommercialFile() {
		return commercialFile;
	}

	public void setCommercialFile(byte[] commercialFile) {
		this.commercialFile = commercialFile;
	}
	public byte[] getLicense() {
		return license;
	}

	public void setLicense(byte[] license) {
		this.license = license;
	}

	public byte[] getChamberofcommerce() {
		return chamberofcommerce;
	}

	public void setChamberofcommerce(byte[] chamberofcommerce) {
		this.chamberofcommerce = chamberofcommerce;
	}

	public byte[] getIdentificationFile() {
		return identificationFile;
	}

	public void setIdentificationFile(byte[] identificationFile) {
		this.identificationFile = identificationFile;
	}

	public byte[] getNationalnumber() {
		return Nationalnumber;
	}

	public void setNationalnumber(byte[] nationalnumber) {
		Nationalnumber = nationalnumber;
	}

	public byte[] getStatisticsmodel() {
		return Statisticsmodel;
	}

	public void setStatisticsmodel(byte[] statisticsmodel) {
		Statisticsmodel = statisticsmodel;
	}

	public byte[] getIndustrial() {
		return industrial;
	}

	public void setIndustrial(byte[] industrial) {
		this.industrial = industrial;
	}

	public byte[] getImporters() {
		return Importers;
	}

	public void setImporters(byte[] importers) {
		Importers = importers;
	}

   

    
       
    
}

