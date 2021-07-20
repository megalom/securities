package ru.megalomaniac.securities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.megalomaniac.securities.model.SecuritiesInfo;

public interface SecuritiesInfoRepository extends JpaRepository<SecuritiesInfo,Integer> {
    Boolean existsBySecid(String secid); //Проверка существует ли ценная бумага с указанным индексом
    SecuritiesInfo findBySecid(String secid);

    @Modifying
    @Query(value="INSERT INTO securities_info (id,secid, regnumber, name,emitent_title) " +
            "VALUES(:id,:secid,:regnumber,:name,:emitent_title) ",
            nativeQuery = true
    )
    int saveWithCustomId(@Param("id") int id,
                         @Param("secid") String secid,
                         @Param("regnumber") String regnumber,
                         @Param("name") String name,
                         @Param("emitent_title") String emitent_title);

}
