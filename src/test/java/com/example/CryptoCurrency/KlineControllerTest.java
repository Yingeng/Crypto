package com.example.CryptoCurrency;

import com.example.CryptoCurrency.controller.KlineController;
import com.example.CryptoCurrency.model.KlineResponseItem;
import com.example.CryptoCurrency.repo.KlineRepo;
import com.example.CryptoCurrency.service.KlineResponseRetrievalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(MockitoJUnitRunner.class)
public class KlineControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private KlineResponseRetrievalService klineResponseRetrievalService;

    @InjectMocks
    private KlineController klineController;

    KlineResponseItem item1 = new KlineResponseItem();
}
