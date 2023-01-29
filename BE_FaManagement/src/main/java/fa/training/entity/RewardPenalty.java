package fa.training.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reward_penalty")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RewardPenalty {
	@Id
	@Column(name = "reward_penalty_id")
	private Long rewardPenaltyId;
	@Column(name = "milestones")
	private LocalDate milestones;
	@Column(name = "bonus_point")
	private Integer bonusPoint;
	@Column(name="penalty_point")
	private Integer penalty_point;
	@Column(name = "comments")
	private String comments;
	

}
