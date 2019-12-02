package com.michalkaluzinski.fantasyleague.dtos;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameDTO {

  private Integer id;

  private Integer leagueId;

  private Integer homeTeamId;

  private Integer awayTeamId;

  private Integer round;

  private Integer homeTeamScore;

  private Integer awayTeamScore;
  
  private Timestamp startAt;
}
