package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String playerEat;
	public boolean waitForEat;
	
	private void excuteGame(Model model, String pointSelection) {
		
		// out of 9 items
		if (this.gameForm.getCounterPlayer1() > 0 || this.gameForm.getCounterPlayer2() > 0) {
			if (this.waitForEat) {
				if (eat(playerEat, pointSelection)) {
					// Reset play eat
					this.playerEat = null;
					this.waitForEat = false;
					model.addAttribute("gameForm", gameForm);
					return;
				} else {
					return;
				}
			}
			
			if (gameForm.getNextPlayer() == null) {
				gameForm.setNextPlayer(PointStatus.PLAYER1);
			}
			
			// assign to the next player
			setNextPlayer();

			String currentPlayer = GameUtils.getPlayerOnGame(gameForm.getNextPlayer());
			boolean isValidPoint = GameUtils.updatePointSelected(this.gameForm, pointSelection, false, currentPlayer);
			
			if (isValidPoint) {
				this.gameForm.setGameMessage("Lượt của " + gameForm.getNextPlayer().name());
				this.playerEat = getPlayerCanEatOne(gameForm);
				if (StringUtils.isNotEmpty(this.playerEat) && !this.waitForEat) {
					this.gameForm.setGameMessage(currentPlayer + " ăn 1 quân, chọn lấy 1 quân của đối phương");
					this.waitForEat = true;
				}
				
			} else {
				this.gameForm.setGameMessage("Không hợp lệ, xin chọn điểm khác!!!");
			}
			
		} else {
			String currentPlayer = GameUtils.getPlayerOnGame(gameForm.getNextPlayer());
			if (GameUtils.isValidSelectPointToMove(gameForm, pointSelection, currentPlayer)) {
				if (currentPlayer.equals(PointStatus.PLAYER1.name())) {
					this.gameForm.getPointMap().put(PointName.valueOf(pointSelection), PointStatus.WAIT_PLAYER1_MOVE.name());
				} else {
					this.gameForm.getPointMap().put(PointName.valueOf(pointSelection), PointStatus.WAIT_PLAYER2_MOVE.name());
				}
				this.gameForm.setGameMessage("Chọn vị trí di chuyển tới");
//				setNextPlayer();
			} else {
				this.gameForm.setGameMessage("Không hợp lệ, chọn quân của mình để di chuyển!!");
			}
		}
		
		model.addAttribute("gameForm", gameForm);
	}
	

	private void setNextPlayer () {
		// assign to the next player
		if (gameForm.getNextPlayer()  == PointStatus.PLAYER1) {
			gameForm.setNextPlayer(PointStatus.PLAYER2);
			this.gameForm.setCounterPlayer1(this.gameForm.getCounterPlayer1() - 1);
		} else {
			gameForm.setNextPlayer(PointStatus.PLAYER1);
			this.gameForm.setCounterPlayer2(this.gameForm.getCounterPlayer2() - 1);
		}
	}
	
	/**
	 * 
	 * @param playerEat
	 * @param pointEat
	 */
	private boolean eat(String playerEat, String pointEat) {
		if (GameUtils.isPointLocked(this.gameForm, pointEat)) {
				this.gameForm.setGameMessage("Không hợp lệ, chọn quân khác!!!");
				return false;
		}
		
		if (PointStatus.PLAYER1.name().equals(playerEat)) {
			this.gameForm.setNumberEatPlay1(this.gameForm.getNumberEatPlay1() + 1);
		} else {
			this.gameForm.setNumberEatPlay2(this.gameForm.getNumberEatPlay2() + 1);
		}
		
		gameForm.getPointMap().put(PointName.valueOf(pointEat), null);
		this.gameForm.setGameMessage("Đã ăn, tiếp tục trò chơi..");
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
		
		model.addAttribute("gameForm", gameForm);
		return "mainGame";
	}
	
	private String getPlayerCanEatOne(GameForm gameForm) {
		//======================A area=======================
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A1)
				, gameForm.getPointMap().get(PointName.A2), gameForm.getPointMap().get(PointName.A3)) 
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A1_A2_A3))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A1_A2_A3, true);
			return gameForm.getPointMap().get(PointName.A1);
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A3)
				, gameForm.getPointMap().get(PointName.A4), gameForm.getPointMap().get(PointName.A5))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A3_A4_A5))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A3_A4_A5, true);
			return gameForm.getPointMap().get(PointName.A3);
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A5)
				, gameForm.getPointMap().get(PointName.A6), gameForm.getPointMap().get(PointName.A7))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A5_A6_A7))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A5_A6_A7, true);
			return gameForm.getPointMap().get(PointName.A5);
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A7)
				, gameForm.getPointMap().get(PointName.A8), gameForm.getPointMap().get(PointName.A1))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A7_A8_A1))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A7_A8_A1, true);
			return gameForm.getPointMap().get(PointName.A7);
		}
		
		//======================B area=======================
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.B1)
				, gameForm.getPointMap().get(PointName.B2), gameForm.getPointMap().get(PointName.B3))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.B1_B2_B3))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.B1_B2_B3, true);
			return gameForm.getPointMap().get(PointName.B1);
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.B3)
				, gameForm.getPointMap().get(PointName.B4), gameForm.getPointMap().get(PointName.B5))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.B3_B4_B5))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.B3_B4_B5 , true);
			return gameForm.getPointMap().get(PointName.B3);
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.B5)
				, gameForm.getPointMap().get(PointName.B6), gameForm.getPointMap().get(PointName.B7))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.B5_B6_B7))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.B5_B6_B7, true);
			return gameForm.getPointMap().get(PointName.B5);
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.B7)
				, gameForm.getPointMap().get(PointName.B8), gameForm.getPointMap().get(PointName.B1))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.B7_B8_B1))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.B7_B8_B1, true);
			return gameForm.getPointMap().get(PointName.B7);
		}
		
		//======================C area=======================
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.C1)
				, gameForm.getPointMap().get(PointName.C2), gameForm.getPointMap().get(PointName.C3))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.C1_C2_C3))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.C1_C2_C3, true);
			return gameForm.getPointMap().get(PointName.C1);
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.C3)
				, gameForm.getPointMap().get(PointName.C4), gameForm.getPointMap().get(PointName.C5))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.C3_C4_C5))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.C3_C4_C5, true);
			return gameForm.getPointMap().get(PointName.C3);
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.C5)
				, gameForm.getPointMap().get(PointName.C6), gameForm.getPointMap().get(PointName.C7))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.C5_C6_C7))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.C5_C6_C7, true);
			return gameForm.getPointMap().get(PointName.C5);
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.C7)
				, gameForm.getPointMap().get(PointName.C8), gameForm.getPointMap().get(PointName.C1))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.C7_C8_C1))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.C7_C8_C1, true);
			return gameForm.getPointMap().get(PointName.C7);
		}
		
		//============================================= others
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A2)
				, gameForm.getPointMap().get(PointName.B2), gameForm.getPointMap().get(PointName.C2))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A2_B2_C2))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A2_B2_C2, true);
			return gameForm.getPointMap().get(PointName.A2);
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A4)
				, gameForm.getPointMap().get(PointName.B4), gameForm.getPointMap().get(PointName.C4))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A4_B4_C4))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A4_B4_C4, true);
			return gameForm.getPointMap().get(PointName.A4);
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A6)
				, gameForm.getPointMap().get(PointName.B6), gameForm.getPointMap().get(PointName.C6))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A6_B6_C6))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A6_B6_C6, true);
			return gameForm.getPointMap().get(PointName.A6);
		}
		
		if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A8)
				, gameForm.getPointMap().get(PointName.B8), gameForm.getPointMap().get(PointName.C8))
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A8_B8_C8))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A8_B8_C8, true);
			return gameForm.getPointMap().get(PointName.A8);
		}
		
		return null;
	}


	public GameForm getGameForm() {
		return gameForm;
	}


	public void setGameForm(GameForm gameForm) {
		this.gameForm = gameForm;
	}

	public String getPlayerEat() {
		return playerEat;
	}


	public void setPlayerEat(String playerEat) {
		this.playerEat = playerEat;
	}
	
}
