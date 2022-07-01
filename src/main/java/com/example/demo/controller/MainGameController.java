package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.enums.Player;
import com.example.demo.enums.TrangBaPoints;
import com.example.demo.form.GameForm;
import com.example.demo.utils.BooleanUtils;
import com.example.demo.utils.GameUtils;
import com.example.demo.utils.StringUtils;

@Controller
public class MainGameController {


	public GameForm gameForm = new GameForm();
	public final static String redirectToMainGame = "redirect:/mainGame";
	public Player playerTurn;
	public String playerEat;
	public boolean waitForEat;
//	public String gameMessage;
	
	private void excuteGame(Model model, String pointSelection) {
		
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
		
		if (gameForm.getPlayerTurn() == null) {
			playerTurn = Player.PLAYER1;
			if (this.gameForm.getCounterPlayer1() > 0) {
				this.gameForm.setCounterPlayer1(this.gameForm.getCounterPlayer1() - 1);
			}
		} else {
			if (gameForm.getPlayerTurn()  == Player.PLAYER1) {
				playerTurn = Player.PLAYER2;
				if (this.gameForm.getCounterPlayer2() > 0) {
					this.gameForm.setCounterPlayer2(this.gameForm.getCounterPlayer2() - 1);
				}
			} else {
				playerTurn = Player.PLAYER1;
				if (this.gameForm.getCounterPlayer1() > 0) {
					this.gameForm.setCounterPlayer1(this.gameForm.getCounterPlayer1() - 1);
				}
			}
		}
		gameForm.setPlayerTurn(playerTurn);
		
		GameUtils.updatePointSelected(this.gameForm, pointSelection, false, playerTurn.name());
		
		this.playerEat = getPlayerCanEatOne(gameForm);
		if (StringUtils.isNotEmpty(this.playerEat) && !this.waitForEat) {
			this.gameForm.setGameMessage(playerTurn.name() + " ăn 1 quân, chọn lấy 1 quân của đối phương");
			this.waitForEat = true;
		}
		
		model.addAttribute("gameForm", gameForm);
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
		String updateValue;
		
		if (Player.PLAYER1.name().equals(playerEat)) {
			updateValue = Player.PLAYER1_EAT.name();
			this.gameForm.setNumberEatPlay1(this.gameForm.getNumberEatPlay1() + 1);
		} else {
			updateValue = Player.PLAYER2_EAT.name();
			this.gameForm.setNumberEatPlay2(this.gameForm.getNumberEatPlay2() + 1);
		}
		GameUtils.updatePointSelected(this.gameForm, pointEat, this.waitForEat, updateValue);
		System.out.println("====================" + this.gameForm.getB1());
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
		
//		this.gameForm = new GameForm();
		this.playerTurn = null;
		model.addAttribute("gameForm", gameForm);
		return "mainGame";
	}
	
	private String getPlayerCanEatOne(GameForm gameForm) {
		//======================A area=======================
		if (StringUtils.isAllEqual(gameForm.getA1(), gameForm.getA2(), gameForm.getA3()) 
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A1_A2_A3))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A1_A2_A3, true);
			return gameForm.getA1();
		}
		
		if (StringUtils.isAllEqual(gameForm.getA3(), gameForm.getA4(), gameForm.getA5())
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A3_A4_A5))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A3_A4_A5, true);
			return gameForm.getA3();
		}
		
		if (StringUtils.isAllEqual(gameForm.getA5(), gameForm.getA6(), gameForm.getA7())
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A5_A6_A7))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A5_A6_A7, true);
			return gameForm.getA5();
		}
		
		if (StringUtils.isAllEqual(gameForm.getA7(), gameForm.getA8(), gameForm.getA1())
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A7_A8_A1))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A7_A8_A1, true);
			return gameForm.getA7();
		}
		
		//======================B area=======================
		
		if (StringUtils.isAllEqual(gameForm.getB1(), gameForm.getB2(), gameForm.getB3())
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.B1_B2_B3))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.B1_B2_B3, true);
			return gameForm.getB1();
		}
		
		if (StringUtils.isAllEqual(gameForm.getB3(), gameForm.getB4(), gameForm.getB5())
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.B3_B4_B5))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.B3_B4_B5 , true);
			return gameForm.getB3();
		}
		
		if (StringUtils.isAllEqual(gameForm.getB5(), gameForm.getB6(), gameForm.getB7())
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.B5_B6_B7))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.B5_B6_B7, true);
			return gameForm.getB5();
		}
		
		if (StringUtils.isAllEqual(gameForm.getB7(), gameForm.getB8(), gameForm.getB1())
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.B7_B8_B1))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.B7_B8_B1, true);
			return gameForm.getB7();
		}
		
		//======================C area=======================
		if (StringUtils.isAllEqual(gameForm.getC1(), gameForm.getC2(), gameForm.getC3())
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.C1_C2_C3))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.C1_C2_C3, true);
			return gameForm.getC1();
		}
		
		if (StringUtils.isAllEqual(gameForm.getC3(), gameForm.getC4(), gameForm.getC5())
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.C3_C4_C5))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.C3_C4_C5, true);
			return gameForm.getC3();
		}
		
		if (StringUtils.isAllEqual(gameForm.getC5(), gameForm.getC6(), gameForm.getC7())
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.C5_C6_C7))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.C5_C6_C7, true);
			return gameForm.getC5();
		}
		
		if (StringUtils.isAllEqual(gameForm.getC7(), gameForm.getC8(), gameForm.getC1())
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.C7_C8_C1))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.C7_C8_C1, true);
			return gameForm.getC7();
		}
		
		//============================================= others
		if (StringUtils.isAllEqual(gameForm.getA2(), gameForm.getB2(), gameForm.getC2())
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A2_B2_C2))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A2_B2_C2, true);
			return gameForm.getA2();
		}
		
		if (StringUtils.isAllEqual(gameForm.getA4(), gameForm.getB4(), gameForm.getC4())
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A4_B4_C4))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A4_B4_C4, true);
			return gameForm.getA4();
		}
		
		if (StringUtils.isAllEqual(gameForm.getA6(), gameForm.getB6(), gameForm.getC6())
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A6_B6_C6))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A6_B6_C6, true);
			return gameForm.getA6();
		}
		
		if (StringUtils.isAllEqual(gameForm.getA8(), gameForm.getB8(), gameForm.getC8())
				&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.A8_B8_C8))) {
			gameForm.getTrangBaStatus().put(TrangBaPoints.A8_B8_C8, true);
			return gameForm.getA6();
		}
		
		return "";
	}


	public GameForm getGameForm() {
		return gameForm;
	}


	public void setGameForm(GameForm gameForm) {
		this.gameForm = gameForm;
	}


	public Player getPlayerTurn() {
		return playerTurn;
	}


	public void setPlayerTurn(Player playerTurn) {
		this.playerTurn = playerTurn;
	}


	public String getPlayerEat() {
		return playerEat;
	}


	public void setPlayerEat(String playerEat) {
		this.playerEat = playerEat;
	}


	
	
	
//	private Map<PointName, Player> getInstancePoints() {
//		Map<PointName, Player> positionsMap = new HashMap<>();
//		positionsMap.put(PointName.a1, null);
//		positionsMap.put(PointName.a2, null);
//		positionsMap.put(PointName.a3, null);
//		positionsMap.put(PointName.a4, null);
//		positionsMap.put(PointName.a5, null);
//		positionsMap.put(PointName.a6, null);
//		positionsMap.put(PointName.a7, null);
//		positionsMap.put(PointName.a8, null);
//		
//		positionsMap.put(PointName.b1, null);
//		positionsMap.put(PointName.b2, null);
//		positionsMap.put(PointName.b3, null);
//		positionsMap.put(PointName.b4, null);
//		positionsMap.put(PointName.b5, null);
//		positionsMap.put(PointName.b6, null);
//		positionsMap.put(PointName.b7, null);
//		positionsMap.put(PointName.b8, null);
//		
//		positionsMap.put(PointName.c1, null);
//		positionsMap.put(PointName.c2, null);
//		positionsMap.put(PointName.c3, null);
//		positionsMap.put(PointName.c4, null);
//		positionsMap.put(PointName.c5, null);
//		positionsMap.put(PointName.c6, null);
//		positionsMap.put(PointName.c7, null);
//		positionsMap.put(PointName.c8, null);
//		
//		return positionsMap;
//	}
	
	
}
