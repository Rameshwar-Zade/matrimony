package com.spring.jwt.Contact_Us.impl;

import com.spring.jwt.Contact_Us.ContactUsMessage;
import com.spring.jwt.Contact_Us.ContactUsRepository;
import com.spring.jwt.Contact_Us.ContactUsRequestDTO;
import com.spring.jwt.Contact_Us.ContactUsService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ContactUsServiceImpl implements ContactUsService {

    @Autowired
    private ContactUsRepository contactUsRepository;

    @Value("${app.export.contactus.path}")
    private String exportPath;

    @Override
    public void submitMessage(ContactUsRequestDTO request) {

        if (request == null) {
            throw new RuntimeException("Please Fill all mandatory details");
        }

        if (request.getFullName() == null || request.getEmail() == null
                || request.getMobileNumber() == null
                || request.getSubject() == null) {

            throw new RuntimeException("Required details Missing");
        }

        if(contactUsRepository.existsByMobileNumber(request.getMobileNumber())
                || contactUsRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException(
                    "We Already got your request !! Please Wait For 24 Hours Before sending Another Request....");
        }


        ContactUsMessage message = new ContactUsMessage();
        message.setFullName(request.getFullName());
        message.setEmail(request.getEmail());
        message.setMobileNumber(request.getMobileNumber());
        message.setSubject(request.getSubject());
        message.setMessage(request.getMessage());
        message.setCreatedAt(LocalDateTime.now());

        contactUsRepository.save(message);
    }

    @Override
    public String exportContactUsToExcelFile() throws IOException {

        List<ContactUsMessage> messages =
                contactUsRepository.findAll();

        //Create folder if not exists
        File folder = new File(exportPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        //Fixed file name (IMPORTANT)
        String fileName = "contact-us-details.xlsx";
        File file = new File(folder, fileName);

        Workbook workbook;
        Sheet sheet;

        //If file exists → OPEN it
        if (file.exists()) {

            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            fis.close();

            sheet = workbook.getSheet("Contact Us");
            if (sheet == null) {
                sheet = workbook.createSheet("Contact Us");
            }

            //Clear existing rows (except header)
            int lastRowNum = sheet.getLastRowNum();
            for (int i = lastRowNum; i > 0; i--) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    sheet.removeRow(row);
                }
            }

        } else {
            //File does NOT exist → CREATE new
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Contact Us");

            // Header row
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Full Name");
            header.createCell(2).setCellValue("Email");
            header.createCell(3).setCellValue("Phone Number");
            header.createCell(4).setCellValue("Subject");
            header.createCell(5).setCellValue("Message");
            header.createCell(6).setCellValue("Created At");
        }

        // Write fresh data
        int rowIndex = 1;
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        for (ContactUsMessage msg : messages) {

            Row row = sheet.createRow(rowIndex);

            row.createCell(0).setCellValue(msg.getId());
            row.createCell(1).setCellValue(msg.getFullName());
            row.createCell(2).setCellValue(msg.getEmail());
            row.createCell(3).setCellValue(msg.getMobileNumber());
            row.createCell(4).setCellValue(msg.getSubject());
            row.createCell(5).setCellValue(msg.getMessage());

            if (msg.getCreatedAt() != null) {
                row.createCell(6)
                        .setCellValue(
                                msg.getCreatedAt().format(formatter));
            }

            rowIndex++;
        }

        //Auto-size columns
        for (int i = 0; i <= 6; i++) {
            sheet.autoSizeColumn(i);
        }

        //Write back to SAME FILE
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);

        fos.close();
        workbook.close();

        return file.getAbsolutePath();
    }

}
