package ru.tisov.denis.dao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.tisov.denis.dto.History;

public interface MoexDao {

    @GET("/iss/history/engines/stock/markets/shares/boards/TQBR/securities/{securityId}.xml?from=2010-01-01&till=2018-09-16&limit=100")
    Call<History> getHistoryData(@Path("securityId") String securityId, @Query("start") long start);

}
