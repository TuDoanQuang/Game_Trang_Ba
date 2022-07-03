package com.example.demo.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.enums.GameStatus;
import com.example.demo.enums.PointName;
import com.example.demo.enums.PointStatus;
import com.example.demo.enums.TrangBaPoints;
import com.example.demo.form.GameForm;
import com.example.demo.utils.BooleanUtils;
import com.example.demo.utils.GameUtils;
import com.example.demo.utils.StringUtils;

@Controller
public class MainGameController {


	public GameForm gameForm = new GameForm();
	public final static String redirectToMainGame = "redirect:/mainGame";
	
	private void excuteGame(Model model, String pointSelection) {
		this.gameForm.setPointSelected(pointSelection);
		if (gameForm.getGameStatus() == null) {
			gameForm.setGameStatus(GameStatus.ON_GOING);
		}
		
		// out of 9 items
		if (this.gameForm.getCounterPlayer1() > 0 || this.gameForm.getCounterPlayer2() > 0) {
			switch (gameForm.getGameStatus()) {
				case ON_GOING:
					boolean isValidPoint = GameUtils.isPointSelectedValid(this.gameForm, pointSelection, false, this.gameForm.getCurrentPlayer().name());
					
					if (isValidPoint) {
						if (isPlayerCanEatOne(gameForm)) {
							this.gameForm.setGameMessage(this.gameForm.getCurrentPlayer().name() + " ăn 1 quân, chọn lấy 1 quân của đối phương");
							gameForm.setGameStatus(GameStatus.WAIT_FOR_EATING);
							return;
						} 
						
						// assign to the next player
						assignToNextPlayer();
					} else {
						this.gameForm.setGameMessage("Không hợp lệ, xin chọn điểm khác!!!");
					}
					
					break;
				
				case WAIT_FOR_EATING: 
					doEating();
					
					break;
				default:
					break;
			}
			
			if (this.gameForm.getCounterPlayer1() == 0 && this.gameForm.getCounterPlayer2() == 0) {
				this.gameForm.setGameMessage("Hết quân để tấn công, chọn quân để di chuyển!");
			}
			
		} else {
			
			switch (gameForm.getGameStatus()) {
				case ON_GOING:
					if (GameUtils.isValidSelectPointToMove(gameForm, pointSelection)) {
						if (gameForm.getCurrentPlayer() == PointStatus.PLAYER1) {
							this.gameForm.getPointMap().put(PointName.valueOf(pointSelection), PointStatus.WAIT_PLAYER1_MOVE.name());
						} else {
							this.gameForm.getPointMap().put(PointName.valueOf(pointSelection), PointStatus.WAIT_PLAYER2_MOVE.name());
						}
						this.gameForm.setGameStatus(GameStatus.WAIT_FOR_MOVING);
						this.gameForm.setGameMessage("Chọn vị trí trống để di chuyển tới");
					} else {
						this.gameForm.setGameMessage("Không hợp lệ, chọn quân của mình để di chuyển!!");
					}
					break;
				case WAIT_FOR_EATING:
					doEating();
					break;
				case WAIT_FOR_MOVING:
					PointName pointWaitingToMove = gameForm.getPointMap().entrySet().stream()
					.filter(x -> (x.getValue() != null && (x.getValue().equals(PointStatus.WAIT_PLAYER1_MOVE.name()) 
							|| x.getValue().equals(PointStatus.WAIT_PLAYER2_MOVE.name()))))
					.map(Map.Entry::getKey).findFirst().orElse(null);
					
					if (GameUtils.isDestinationValid(gameForm, pointWaitingToMove.name(), pointSelection)) {
						this.gameForm.getPointMap().put(pointWaitingToMove, null);
						this.gameForm.getPointMap().put(PointName.valueOf(pointSelection), this.gameForm.getCurrentPlayer().name());
						removeTrangBa();
						if (isPlayerCanEatOne(gameForm)) {
							this.gameForm.setGameMessage(this.gameForm.getCurrentPlayer().name() + " ăn 1 quân, chọn lấy 1 quân của đối phương");
							gameForm.setGameStatus(GameStatus.WAIT_FOR_EATING);
						} else {
							this.gameForm.setGameStatus(GameStatus.ON_GOING);
							assignToNextPlayer();
						}
						
					} else {
						this.gameForm.setGameMessage("Không hợp lệ, chọn quân của mình để di chuyển!!");
					}
					
					break;
				default:
					break;
			}
		}
		model.addAttribute("gameForm", gameForm);
	}
	
	private void removeTrangBa() {
		String pointSelected = this.gameForm.getPointSelected();
		
		for (Map.Entry<TrangBaPoints, Boolean> entry : this.gameForm.getTrangBaStatus().entrySet()) {
			if (entry.getKey().name().contains(pointSelected) && BooleanUtils.isTrue(entry.getValue())) {
				entry.setValue(false);
				return;
			}
		}
	}
	
	private void doEating() {
		if (eatValid(this.gameForm)) {
			gameForm.setGameStatus(GameStatus.ON_GOING);
			gameForm.getPointMap().put(PointName.valueOf(gameForm.getPointSelected()), null);
			// assign to the next player
			assignToNextPlayer();
			this.gameForm.setGameMessage("Đã ăn, tiếp tục trò chơi..");
		} else {
			this.gameForm.setGameMessage("Không hợp lệ, chọn quân khác để ăn!!");
		}
	}

	private void assignToNextPlayer () {
		// assign to the next player
		if (gameForm.getCurrentPlayer()  == PointStatus.PLAYER1) {
			gameForm.setCurrentPlayer(PointStatus.PLAYER2);
			
			if (this.gameForm.getCounterPlayer1() > 0) {
				this.gameForm.setCounterPlayer1(this.gameForm.getCounterPlayer1() - 1);
			}
		} else {
			gameForm.setCurrentPlayer(PointStatus.PLAYER1);
			if (this.gameForm.getCounterPlayer2() > 0) {
				this.gameForm.setCounterPlayer2(this.gameForm.getCounterPlayer2() - 1);
			}
		}
		this.gameForm.setGameMessage("Lượt tiếp theo!");
	}
	
	/**
	 * 
	 * @param playerEat
	 * @param pointEat
	 */
	private boolean eatValid(GameForm gameForm) {
		if (GameUtils.isPointLocked(gameForm, gameForm.getPointSelected())) {
				this.gameForm.setGameMessage("Không hợp lệ, chọn quân khác!!!");
				return false;
		}
		
		if (PointStatus.PLAYER1 == gameForm.getCurrentPlayer()) {
			this.gameForm.setNumberEatPlay1(this.gameForm.getNumberEatPlay1() + 1);
		} else {
			this.gameForm.setNumberEatPlay2(this.gameForm.getNumberEatPlay2() + 1);
		}
		
		return true;
	}

	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "a1")
	public String selectPointA1(@RequestParam String a1, Model model) {
		excuteGame(model, a1);
		return redirectToMainGame;
	}
	
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "a2")
	public String selectPointA2(@RequestParam String a2, Model model) {
		excuteGame(model, a2);
		return redirectToMainGame;
	}
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "a3")
	public String selectPointA3(@RequestParam String a3, Model model) {
		excuteGame(model, a3);
		return redirectToMainGame;
	}
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "a4")
	public String selectPointA4(@RequestParam String a4, Model model) {
		excuteGame(model, a4);
		return redirectToMainGame;
	}
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "a5")
	public String selectPointA5(@RequestParam String a5, Model model) {
		excuteGame(model, a5);
		return redirectToMainGame;
	}
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "a6")
	public String selectPointA6(@RequestParam String a6, Model model) {
		excuteGame(model, a6);
		return redirectToMainGame;
	}
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "a7")
	public String selectPointA7(@RequestParam String a7, Model model) {
		excuteGame(model, a7);
		return redirectToMainGame;
	}
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "a8")
	public String selectPointA8(@RequestParam String a8, Model model) {
		excuteGame(model, a8);
		return redirectToMainGame;
	}
//	===========================================================B area
	
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "b1")
	public String selectPointB1(@RequestParam String b1, Model model) {
		excuteGame(model, b1);
		return redirectToMainGame;
	}
	
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "b2")
	public String selectPointB2(@RequestParam String b2, Model model) {
		excuteGame(model, b2);
		return redirectToMainGame;
	}
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "b3")
	public String selectPointB3(@RequestParam String b3, Model model) {
		excuteGame(model, b3);
		return redirectToMainGame;
	}
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "b4")
	public String selectPointB4(@RequestParam String b4, Model model) {
		excuteGame(model, b4);
		return redirectToMainGame;
	}
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "b5")
	public String selectPointB5(@RequestParam String b5, Model model) {
		excuteGame(model, b5);
		return redirectToMainGame;
	}
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "b6")
	public String selectPointB6(@RequestParam String b6, Model model) {
		excuteGame(model, b6);
		return redirectToMainGame;
	}
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "b7")
	public String selectPointB7(@RequestParam String b7, Model model) {
		excuteGame(model, b7);
		return redirectToMainGame;
	}
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "b8")
	public String selectPointB8(@RequestParam String b8, Model model) {
		excuteGame(model, b8);
		return redirectToMainGame;
	}
//	===========================================================C area
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "c1")
	public String selectPointC1(@RequestParam String c1, Model model) {
		excuteGame(model, c1);
		return redirectToMainGame;
	}
	
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "c2")
	public String selectPointC2(@RequestParam String c2, Model model) {
		excuteGame(model, c2);
		return redirectToMainGame;
	}
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "c3")
	public String selectPointC3(@RequestParam String c3, Model model) {
		excuteGame(model, c3);
		return redirectToMainGame;
	}
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "c4")
	public String selectPointC4(@RequestParam String c4, Model model) {
		excuteGame(model, c4);
		return redirectToMainGame;
	}
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "c5")
	public String selectPointC5(@RequestParam String c5, Model model) {
		excuteGame(model, c5);
		return redirectToMainGame;
	}
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "c6")
	public String selectPointC6(@RequestParam String c6, Model model) {
		excuteGame(model, c6);
		return redirectToMainGame;
	}
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "c7")
	public String selectPointC7(@RequestParam String c7, Model model) {
		excuteGame(model, c7);
		return redirectToMainGame;
	}
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.POST, params = "c8")
	public String selectPointC8(@RequestParam String c8, Model model) {
		excuteGame(model, c8);
		return redirectToMainGame;
	}
	
	
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.GET)
	public String getUI(Model model) {
		if (gameForm.getCurrentPlayer() == null) {
			gameForm.setCurrentPlayer(PointStatus.PLAYER1);
		}
		model.addAttribute("gameForm", gameForm);
		return "mainGame";
	}
	
	private boolean isPlayerCanEatOne(GameForm gameForm) {
		//======================A area=======================
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A1)
				, gameForm.getPointMap().get(PointName.A2), gameForm.getPointMap().get(PointName.A3)) 
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A1_A2_A3))) {
			
			gameForm.getTrangBaStatus().put(TrangBaPoints.A1_A2_A3, true);
			return true;
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A3)
				, gameForm.getPointMap().get(PointName.A4), gameForm.getPointMap().get(PointName.A5))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A3_A4_A5))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A3_A4_A5, true);
			return true;
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A5)
				, gameForm.getPointMap().get(PointName.A6), gameForm.getPointMap().get(PointName.A7))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A5_A6_A7))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A5_A6_A7, true);
			return true;
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A7)
				, gameForm.getPointMap().get(PointName.A8), gameForm.getPointMap().get(PointName.A1))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A7_A8_A1))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A7_A8_A1, true);
			return true;
		}
		
		//======================B area=======================
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.B1)
				, gameForm.getPointMap().get(PointName.B2), gameForm.getPointMap().get(PointName.B3))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.B1_B2_B3))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.B1_B2_B3, true);
			return true;
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.B3)
				, gameForm.getPointMap().get(PointName.B4), gameForm.getPointMap().get(PointName.B5))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.B3_B4_B5))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.B3_B4_B5 , true);
			return true;
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.B5)
				, gameForm.getPointMap().get(PointName.B6), gameForm.getPointMap().get(PointName.B7))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.B5_B6_B7))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.B5_B6_B7, true);
			return true;
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.B7)
				, gameForm.getPointMap().get(PointName.B8), gameForm.getPointMap().get(PointName.B1))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.B7_B8_B1))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.B7_B8_B1, true);
			return true;
		}
		
		//======================C area=======================
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.C1)
				, gameForm.getPointMap().get(PointName.C2), gameForm.getPointMap().get(PointName.C3))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.C1_C2_C3))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.C1_C2_C3, true);
			return true;
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.C3)
				, gameForm.getPointMap().get(PointName.C4), gameForm.getPointMap().get(PointName.C5))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.C3_C4_C5))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.C3_C4_C5, true);
			return true;
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.C5)
				, gameForm.getPointMap().get(PointName.C6), gameForm.getPointMap().get(PointName.C7))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.C5_C6_C7))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.C5_C6_C7, true);
			return true;
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.C7)
				, gameForm.getPointMap().get(PointName.C8), gameForm.getPointMap().get(PointName.C1))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.C7_C8_C1))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.C7_C8_C1, true);
			return true;
		}
		
		//============================================= others
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A2)
				, gameForm.getPointMap().get(PointName.B2), gameForm.getPointMap().get(PointName.C2))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A2_B2_C2))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A2_B2_C2, true);
			return true;
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A4)
				, gameForm.getPointMap().get(PointName.B4), gameForm.getPointMap().get(PointName.C4))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A4_B4_C4))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A4_B4_C4, true);
			return true;
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A6)
				, gameForm.getPointMap().get(PointName.B6), gameForm.getPointMap().get(PointName.C6))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A6_B6_C6))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A6_B6_C6, true);
			return true;
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A8)
				, gameForm.getPointMap().get(PointName.B8), gameForm.getPointMap().get(PointName.C8))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A8_B8_C8))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A8_B8_C8, true);
			return true;
		}
		
		return false;
	}


	public GameForm getGameForm() {
		return gameForm;
	}


	public void setGameForm(GameForm gameForm) {
		this.gameForm = gameForm;
	}

//	public String getPlayerEat() {
//		return playerEat;
//	}
//
//
//	public void setPlayerEat(String playerEat) {
//		this.playerEat = playerEat;
//	}
	
}
