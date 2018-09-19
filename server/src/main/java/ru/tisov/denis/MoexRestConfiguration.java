package ru.tisov.denis;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.jaxb.JaxbConverterFactory;
import ru.tisov.denis.dao.MoexDao;

import java.util.concurrent.TimeUnit;

@Configuration
public class MoexRestConfiguration {

    @Bean
    public MoexDao moexDao() {
        return new Retrofit.Builder()
                .baseUrl("http://iss.moex.com")
                .client(httpClient())
                .addConverterFactory(JaxbConverterFactory.create())
                .build()
                .create(MoexDao.class);
    }

    private OkHttpClient httpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
//                .addInterceptor(logging)
                .build();
    }

    private Converter.Factory jsonConverterFactory() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return JacksonConverterFactory.create(mapper);
    }
}
