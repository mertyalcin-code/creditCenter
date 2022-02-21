package com.mertyalcin.creditcenter.core.utils.messages;

public class Messages {
    //Exceptions
    public static final String DATA_INTEGRITY_VIOLATION= "Veri tabanı ilişki hatası";

    //Validations
    public static final String NATIONALITY_NO_ONLY_CONTAINS_NUMBERS ="TC Kimlik Numarası Sadece Rakam İçerebilir" ;
    public static final String NATIONALITY_NO_SHOULD_BE_11_DIGIT = "TC Kimlik Numarası 11 Haneli Olmalıdır";
    public static final String INVALID_FIRST_NAME = "İsim Bilgisi Hatalı";
    public static final String INVALID_LAST_NAME = "İsim Bilgisi Hatalı";

    //Customer
    public static final String MONTHLY_SALARY_CANNOT_BE_NAGATIVE = "Aylık Gelir Sıfırdan Düşük Olamaz";
    public static final String INVALID_PHONE_NUMBER ="Geçersiz Telefon Numarası" ;
    public static final String CUSTOMER_LIST = "Müşteri Bilgileri Getirildi";
    public static final String CUSTOMER_NOT_FOUND ="Müşteri Bulunamadı" ;
    public static final String CUSTOMER_EXIST_WITH_NATIONALITY_NO = "Bu TC Kimlik Numarası İle Kayıtlı Müşteri Bulunmaktadır";
    public static final String CUSTOMER_EXIST_WITH_PHONE_NUMBER = "Bu Telefon Numarası İle Kayıtlı Müşteri Bulunmaktadır";

    public static final String CUSTOMER_CREATE = "Müşteri Oluşturuldu";
    public static final String CUSTOMER_UPDATE = "Müşteri Güncellendi";
    public static final String CUSTOMER_DELETE = "Müşteri Silindi";
    //Employee
    public static final String EMPLOYEE_LIST = "Çalışan Listelendi";
    public static final String EMPLOYEE_NOT_FOUND = "Çalışan Bulunamadı";
    public static final String EMPLOYEE_CREATE = "Employee Oluşturuldu";
    public static final String EMPLOYEE_UPDATE = "Employee Güncellendi";
    public static final String EMPLOYEE_DELETE = "Employee Silindi";
    public static final String USERNAME_ALREADY_EXISTS = "Bu Kullanıcı Adı Kullanılıyor";
    public static final String NO_CREDIT_CALCULATOR = "Uygun Kredi Hesaplayıcı Bulunamadı";
    public static final String CREDIT_APPLIED_SUCCESS = "Kredi Başvurunuz Başarı İle Alınmıştır";
    public static final String CREDIT_LIST = "Credit Displayed" ;
    public static final String CREDIT_UPDATED = "Credit Updated";
    public static final String CREDIT_DELETE = "Credit Deleted";
    public static final String CREDIT_NOT_FOUND = "Credit Not Found";
}
