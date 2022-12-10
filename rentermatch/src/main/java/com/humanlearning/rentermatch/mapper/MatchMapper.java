package com.humanlearning.rentermatch.mapper;

import com.humanlearning.rentermatch.domain.Tenant;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MatchMapper {

  //    @Select("SELECT tClientId, tPhone FROM coms4156.tenant WHERE tConstellation = #{tConstellation} AND tCooking = #{tCooking} AND " +
//            "tEarlyTimeSleep = #{tEarlyTimeSleep} AND tExpenditure = #{tExpenditure} AND tGender = #{tGender} AND tJob = #{tJob} AND " +
//            "tLateTimeSleep = #{tLateTimeSleep} AND tNumOfRoomates = #{tNumOfRoomates} AND tPet = #{tPet} AND tPreferLocation = #{tPreferLocation} AND " +
//            "tPreferType = #{tPreferType} AND tPreferZipCode = #{tPreferZipCode} AND tSmoking = #{tSmoking} AND tClientId != tClientId")
  @Select(
      "SELECT tid, tAge, tClientId, tConstellation, tCooking, tEarlyTimeSleep, tExpenditure, tGender, tJob, tLateTimeSleep, "
          +
          "tMatches, tNumOfRoomates, tPet, tPhone, tPreferLocation, tPreferType, tPreferZipCode, tSmoking FROM coms4156.tenant "
          +
          "WHERE tConstellation = #{tConstellation} AND tCooking = #{tCooking} AND tEarlyTimeSleep = #{tEarlyTimeSleep} AND "
          +
          "tExpenditure = #{tExpenditure} AND tGender = #{tGender} AND tJob = #{tJob} AND tLateTimeSleep = #{tLateTimeSleep} AND "
          +
          "tNumOfRoomates = #{tNumOfRoomates} AND tPet = #{tPet} AND tPreferLocation = #{tPreferLocation} AND tPreferType = #{tPreferType} AND "
          +
          "tPreferZipCode = #{tPreferZipCode} AND tSmoking = #{tSmoking} AND tClientId != #{tClientId}")
  List<Tenant> getMatch(@Param("tClientId") String tClientId,
      @Param("tConstellation") String tConstellation,
      @Param("tCooking") String tCooking, @Param("tEarlyTimeSleep") String tEarlyTimeSleep,
      @Param("tExpenditure") Integer tExpenditure, @Param("tGender") String tGender,
      @Param("tJob") String tJob, @Param("tLateTimeSleep") String tLateTimeSleep,
      @Param("tNumOfRoomates") Integer tNumOfRoomates, @Param("tPet") String tPet,
      @Param("tPreferLocation") String tPreferLocation, @Param("tPreferType") String tPreferType,
      @Param("tPreferZipCode") String tPreferZipCode, @Param("tSmoking") String tSmoking);
}
