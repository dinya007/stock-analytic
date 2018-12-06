package ru.tisov.denis.dao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.tisov.denis.dto.History;

import java.time.LocalDate;

public interface MoexDao {

    @GET("/iss/history/engines/stock/markets/shares/boards/TQBR/securities/{securityId}.xml?from=2010-01-01")
    Call<History> getHistoryData(@Path("securityId") String securityId, @Query("till") LocalDate till, @Query("start") long start, @Query("limit") long limit);

    @GET("/iss/history/engines/stock/markets/shares/boards/TQBR/securities/{securityId}.xml?from=2010-01-01")
    Call<History> getCandles(@Path("securityId") String securityId, @Query("till") LocalDate till, @Query("start") long start, @Query("limit") long limit);

}
