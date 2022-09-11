package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.enums.GameMode;
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

	public GameForm gameForm  ;
	private final static String redirectToMainGame = "mainGame";
	private void callRobotPlay(Model model) {
			System.out.println("gameForm.getGameStatus() " + gameForm.getGameStatus());
		if (gameForm.getGameStatus() == GameStatus.ON_GOING) {
			if (this.gameForm.getCounterPlayer1() > 0 || this.gameForm.getCounterPlayer2() > 0) {
				// Find the point which able to become Trang Ba first
				List<String> pointsAbleToTrangBa = GameUtils.getPositionsAbleTrangBa(gameForm);
				if (pointsAbleToTrangBa != null && pointsAbleToTrangBa.size() > 0) {
					String robotPointSelection = pointsAbleToTrangBa.get(getRandompoint(pointsAbleToTrangBa.size()));
					excuteGame(model, robotPointSelection);
				} else {
					
					// make a point random
					List<PointName> pointValid = new ArrayList<>();
					this.gameForm.getPointMap().forEach((k, v) -> {
						if (v == null || v.isEmpty()) {
							pointValid.add(k);
						}
					});
					PointName robotPointSelection = pointValid.get(getRandompoint(pointValid.size()));
					excuteGame(model, robotPointSelection.name());
				}
			} else {
				List<PointName> validPointsCanMove = new ArrayList<>();
				this.gameForm.getPointMap().forEach((k, v) -> {
					if (PointStatus.PLAYER2.name().equals(v)) {
						validPointsCanMove.add(k);
					}
				});
				
				if (validPointsCanMove.size() > 0) {
					PointName robotPointSelection = validPointsCanMove.get(getRandompoint(validPointsCanMove.size()));
					System.out.println("Point wait to move: " + robotPointSelection);
					excuteGame(model, robotPointSelection.name());
				}
			}
			
		} else if (gameForm.getGameStatus() == GameStatus.WAIT_FOR_MOVING) {
			// random point to move on
			PointName pointWaitingToMove = gameForm.getPointMap().entrySet().stream()
					.filter(x -> (x.getValue() != null && (x.getValue().equals(PointStatus.WAIT_PLAYER2_MOVE.name()))))
					.map(Map.Entry::getKey).findFirst().orElse(null);
			if (pointWaitingToMove != null) {
				List<String> pointsCanMoveOn = GameUtils.getPointsCanMoveOn(gameForm, pointWaitingToMove.name());
				if (pointsCanMoveOn.size() > 0) {
					String pointMoveOn = pointsCanMoveOn.get(getRandompoint(pointsCanMoveOn.size()));
					System.out.println("pointMoveOn: " + pointMoveOn);
					excuteGame(model, pointMoveOn);
				}
			}
			
		} else if (gameForm.getGameStatus() == GameStatus.WAIT_FOR_EATING) {
			List<String> pointsCanEat = GameUtils.getPointsCanEat(gameForm);
			if (pointsCanEat.size() > 0) {
				String pointCanEat = pointsCanEat.get(getRandompoint(pointsCanEat.size()));
				System.out.println("pointCanEat: " + pointCanEat);
				excuteGame(model, pointCanEat);
			}
		}
			
	}
	private int getRandompoint(int upperbound) {
		 Random rand = new Random(); //instance of random class
	     int a = rand.nextInt(upperbound);
	     return a; 
	}
	
	private void excuteGame(Model model, String pointSelection) {
		// play with robot
		
		this.gameForm.setPointSelected(pointSelection);
		
		// out of 9 items
		if (this.gameForm.getCounterPlayer1() > 0 || this.gameForm.getCounterPlayer2() > 0) {
			switch (gameForm.getGameStatus()) {
			case ON_GOING:
				boolean isValidPoint = GameUtils.isPointSelectedValid(this.gameForm, pointSelection, false, this.gameForm.getCurrentPlayer().name());
				
				if (isValidPoint) {
					if (GameUtils.isPlayerCanEatOne(this.gameForm)) {
						this.gameForm.setGameMessage(this.gameForm.getCurrentPlayer().name() + " ăn 1 quân, chọn lấy 1 quân của đối phương");
						gameForm.setGameStatus(GameStatus.WAIT_FOR_EATING);
						break;
					} 
					
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
				
				String playerWin = getPlayerWin();
				if (StringUtils.isNotEmpty(playerWin)) {
					this.gameForm.setGameStatus(GameStatus.FINISH);
					this.gameForm.setGameMessage(playerWin + " thắng! Kết thúc game!");
				}
				break;
			case WAIT_FOR_MOVING:
				PointName pointWaitingToMove = gameForm.getPointMap().entrySet().stream()
				.filter(x -> (x.getValue() != null && (x.getValue().equals(PointStatus.WAIT_PLAYER1_MOVE.name()) 
						|| x.getValue().equals(PointStatus.WAIT_PLAYER2_MOVE.name()))))
				.map(Map.Entry::getKey).findFirst().orElse(null);
				
				// Player select another his point
				if (gameForm.getPointMap().get(PointName.valueOf(pointSelection)) != null && pointWaitingToMove != null
						&& pointWaitingToMove != PointName.valueOf(pointSelection)
						&& this.gameForm.getPointMap().get(pointWaitingToMove).contains(gameForm.getPointMap().get(PointName.valueOf(pointSelection)))) {
					if (gameForm.getCurrentPlayer() == PointStatus.PLAYER1) {
						this.gameForm.getPointMap().put(PointName.valueOf(pointSelection), PointStatus.WAIT_PLAYER1_MOVE.name());
					} else {
						this.gameForm.getPointMap().put(PointName.valueOf(pointSelection), PointStatus.WAIT_PLAYER2_MOVE.name());
					}
					this.gameForm.getPointMap().put(pointWaitingToMove, gameForm.getCurrentPlayer().name());
					
					break;
				}
				
				if (GameUtils.isDestinationValid(gameForm, pointWaitingToMove.name(), pointSelection)) {
					this.gameForm.getPointMap().put(pointWaitingToMove, null);
					this.gameForm.getPointMap().put(PointName.valueOf(pointSelection), this.gameForm.getCurrentPlayer().name());
					removeTrangBa();
					if (GameUtils.isPlayerCanEatOne(gameForm)) {
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
			case FINISH:
				break;
			default:
				break;
			}
		}
		if (this.gameForm.getMode() == GameMode.ONE_PLAYER) {
			if (gameForm.getCurrentPlayer() == PointStatus.PLAYER2 
					&& (gameForm.getGameStatus() == GameStatus.ON_GOING 
				|| gameForm.getGameStatus() == GameStatus.WAIT_FOR_MOVING
				|| gameForm.getGameStatus() == GameStatus.WAIT_FOR_EATING)) {
				callRobotPlay(model);
			}
		} 
		
		model.addAttribute("gameForm", gameForm);
	}
	
	private String getPlayerWin() {
		String playerWin = "";
		if ((this.gameForm.COUNTER - this.gameForm.getNumberEatPlay1()) <= 2) {
			playerWin = PointStatus.PLAYER1.name();
		}
		
		if ((this.gameForm.COUNTER - this.gameForm.getNumberEatPlay2()) <= 2) {
			playerWin = PointStatus.PLAYER2.name();
		}
		return playerWin;
	}
	
	/**
	 * Set Trang Ba to false because of moving a Trang Ba's item
	 */
	private void removeTrangBa() {
		String pointSelected = this.gameForm.getPointSelected();
		
		for (Map.Entry<TrangBaPoints, Boolean> entry : this.gameForm.getTrangBaStatus().entrySet()) {
			if (entry.getKey().name().contains(pointSelected) && BooleanUtils.isTrue(entry.getValue())) {
				entry.setValue(false);
				return;
			}
		}
	}
	
	/**
	 * Eat an item
	 */
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

	/**
	 * assign to the next player
	 */
	private void assignToNextPlayer () {
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
		if (!StringUtils.isNotEmpty(gameForm.getPointMap().get(PointName.valueOf(gameForm.getPointSelected()))) 
				|| gameForm.getCurrentPlayer().name().equals(gameForm.getPointMap().get(PointName.valueOf(gameForm.getPointSelected())))
				|| GameUtils.isPointLocked(gameForm, gameForm.getPointSelected())) {
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
	
	@RequestMapping(value = { "/mainGame" }, method = RequestMethod.GET)
	public String getUI(Model model, @RequestParam("mode") int mode) {
		this.gameForm = new GameForm();// add temporary
		this.gameForm.setCurrentPlayer(PointStatus.PLAYER1);
		this.gameForm.setGameStatus(GameStatus.ON_GOING);
		this.gameForm.setMode(GameMode.getGameModeByValue(mode));
		model.addAttribute("gameForm", gameForm);
		return "mainGame";
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
	
	public GameForm getGameForm() {
		return gameForm;
	}


	public void setGameForm(GameForm gameForm) {
		this.gameForm = gameForm;
	}

}
