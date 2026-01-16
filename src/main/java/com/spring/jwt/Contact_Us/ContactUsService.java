package com.spring.jwt.Contact_Us;


import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ContactUsService {

    void submitMessage(ContactUsRequestDTO request);

    String exportContactUsToExcelFile() throws IOException;
}
