package com.example.demo.form;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.enums.GameMode;
import com.example.demo.enums.GameStatus;
import com.example.demo.enums.PointName;
import com.example.demo.enums.PointStatus;
import com.example.demo.enums.TrangBaPoints;
import com.example.demo.utils.StringUtils;

public class GameForm {

	// the law fix value is 9
	public final int COUNTER = 9;
	private GameStatus gameStatus;
	private PointStatus currentPlayer;
	private String pointSelected;
	private int counterPlayer1 = COUNTER;
	private int counterPlayer2 = COUNTER;
	private int numberEatPlay1 = 0;
	private int numberEatPlay2 = 0;
	private GameMode mode;
	private String gameMessage;
	private Map<PointName, String> pointMap;
	private Map<TrangBaPoints, Boolean> trangBaStatus;
	
	public GameForm() {
		this.trangBaStatus = new HashMap<>();
		this.trangBaStatus.put(TrangBaPoints.A1_A2_A3, null);
		this.trangBaStatus.put(TrangBaPoints.A3_A4_A5, null);
		this.trangBaStatus.put(TrangBaPoints.A5_A6_A7, null);
		this.trangBaStatus.put(TrangBaPoints.A7_A8_A1, null);
		
		this.trangBaStatus.put(TrangBaPoints.B1_B2_B3, null);
		this.trangBaStatus.put(TrangBaPoints.B3_B4_B5, null);
		this.trangBaStatus.put(TrangBaPoints.B5_B6_B7, null);
		this.trangBaStatus.put(TrangBaPoints.B7_B8_B1, null);
		
		this.trangBaStatus.put(TrangBaPoints.C1_C2_C3, null);
		this.trangBaStatus.put(TrangBaPoints.C3_C4_C5, null);
		this.trangBaStatus.put(TrangBaPoints.C5_C6_C7, null);
		this.trangBaStatus.put(TrangBaPoints.C7_C8_C1, null);
		
		this.trangBaStatus.put(TrangBaPoints.A2_B2_C2, null);
		this.trangBaStatus.put(TrangBaPoints.A4_B4_C4, null);
		this.trangBaStatus.put(TrangBaPoints.A6_B6_C6, null);
		this.trangBaStatus.put(TrangBaPoints.A8_B8_C8, null);
		
		
		this.pointMap = new HashMap<>();
		this.pointMap.put(PointName.A1, null);
		this.pointMap.put(PointName.A2, null);
		this.pointMap.put(PointName.A3, null);
		this.pointMap.put(PointName.A4, null);
		this.pointMap.put(PointName.A5, null);
		this.pointMap.put(PointName.A6, null);
		this.pointMap.put(PointName.A7, null);
		this.pointMap.put(PointName.A8, null);
		
		this.pointMap.put(PointName.B1, null);
		this.pointMap.put(PointName.B2, null);
		this.pointMap.put(PointName.B3, null);
		this.pointMap.put(PointName.B4, null);
		this.pointMap.put(PointName.B5, null);
		this.pointMap.put(PointName.B6, null);
		this.pointMap.put(PointName.B7, null);
		this.pointMap.put(PointName.B8, null);
		
		this.pointMap.put(PointName.C1, null);
		this.pointMap.put(PointName.C2, null);
		this.pointMap.put(PointName.C3, null);
		this.pointMap.put(PointName.C4, null);
		this.pointMap.put(PointName.C5, null);
		this.pointMap.put(PointName.C6, null);
		this.pointMap.put(PointName.C7, null);
		this.pointMap.put(PointName.C8, null);
		
	}
	
	public String getColorClassPoint(String value) {
		if (StringUtils.isNotEmpty(value)) {
			if (value.equals(PointStatus.PLAYER1.name())) {
				return "player1-selected";
			}
			
			if (value.equals(PointStatus.PLAYER2.name())) {
				return "player2-selected";
			}
			
			if (value.equals(PointStatus.WAIT_PLAYER1_MOVE.name()) || value.equals(PointStatus.WAIT_PLAYER2_MOVE.name())) {
				return "wait_moving";
			}
		}
		return "";		
	}
	public int getCounterPlayer1() {
		return counterPlayer1;
	}
	public void setCounterPlayer1(int counterPlayer1) {
		this.counterPlayer1 = counterPlayer1;
	}
	public int getCounterPlayer2() {
		return counterPlayer2;
	}
	public void setCounterPlayer2(int counterPlayer2) {
		this.counterPlayer2 = counterPlayer2;
	}
	public int getNumberEatPlay1() {
		return numberEatPlay1;
	}
	public void setNumberEatPlay1(int numberEatPlay1) {
		this.numberEatPlay1 = numberEatPlay1;
	}
	public int getNumberEatPlay2() {
		return numberEatPlay2;
	}
	public void setNumberEatPlay2(int numberEatPlay2) {
		this.numberEatPlay2 = numberEatPlay2;
	}
	public String getGameMessage() {
		return gameMessage;
	}
	public void setGameMessage(String gameMessage) {
		this.gameMessage = gameMessage;
	}

	public Map<TrangBaPoints, Boolean> getTrangBaStatus() {
		return trangBaStatus;
	}

	public void setTrangBaStatus(Map<TrangBaPoints, Boolean> trangBaStatus) {
		this.trangBaStatus = trangBaStatus;
	}

	public Map<PointName, String> getPointMap() {
		return pointMap;
	}

	public void setPointMap(Map<PointName, String> pointMap) {
		this.pointMap = pointMap;
	}

	public PointStatus getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(PointStatus currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public String getPointSelected() {
		return pointSelected;
	}

	public void setPointSelected(String pointSelected) {
		this.pointSelected = pointSelected;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public GameMode getMode() {
		return mode;
	}

	public void setMode(GameMode mode) {
		this.mode = mode;
	}
	
}
