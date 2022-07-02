package com.example.demo.form;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.enums.PointName;
import com.example.demo.enums.PointStatus;
import com.example.demo.enums.TrangBaPoints;

public class GameForm {

	private PointStatus nextPlayer;
	private int counterPlayer1 = 2;
	private int counterPlayer2 = 2;
	private int numberEatPlay1 = 0;
	private int numberEatPlay2 = 0;
//	private String a1;
//	private String a2;
//	private String a3;
//	private String a4;
//	private String a5;
//	private String a6;
//	private String a7;
//	private String a8;
//	
//	private String b1;
//	private String b2;
//	private String b3;
//	private String b4;
//	private String b5;
//	private String b6;
//	private String b7;
//	private String b8;
//	
//	private String c1;
//	private String c2;
//	private String c3;
//	private String c4;
//	private String c5;
//	private String c6;
//	private String c7;
//	private String c8;
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
	
//	public String getA1() {
//		return a1;
//	}
//	public void setA1(String a1) {
//		this.a1 = a1;
//	}
//	public String getA2() {
//		return a2;
//	}
//	public void setA2(String a2) {
//		this.a2 = a2;
//	}
//	public String getA3() {
//		return a3;
//	}
//	public void setA3(String a3) {
//		this.a3 = a3;
//	}
//	public String getA4() {
//		return a4;
//	}
//	public void setA4(String a4) {
//		this.a4 = a4;
//	}
//	public String getA5() {
//		return a5;
//	}
//	public void setA5(String a5) {
//		this.a5 = a5;
//	}
//	public String getA6() {
//		return a6;
//	}
//	public void setA6(String a6) {
//		this.a6 = a6;
//	}
//	public String getA7() {
//		return a7;
//	}
//	public void setA7(String a7) {
//		this.a7 = a7;
//	}
//	public String getA8() {
//		return a8;
//	}
//	public void setA8(String a8) {
//		this.a8 = a8;
//	}
//	public String getB1() {
//		return b1;
//	}
//	public void setB1(String b1) {
//		this.b1 = b1;
//	}
//	public String getB2() {
//		return b2;
//	}
//	public void setB2(String b2) {
//		this.b2 = b2;
//	}
//	public String getB3() {
//		return b3;
//	}
//	public void setB3(String b3) {
//		this.b3 = b3;
//	}
//	public String getB4() {
//		return b4;
//	}
//	public void setB4(String b4) {
//		this.b4 = b4;
//	}
//	public String getB5() {
//		return b5;
//	}
//	public void setB5(String b5) {
//		this.b5 = b5;
//	}
//	public String getB6() {
//		return b6;
//	}
//	public void setB6(String b6) {
//		this.b6 = b6;
//	}
//	public String getB7() {
//		return b7;
//	}
//	public void setB7(String b7) {
//		this.b7 = b7;
//	}
//	public String getB8() {
//		return b8;
//	}
//	public void setB8(String b8) {
//		this.b8 = b8;
//	}
//	public String getC1() {
//		return c1;
//	}
//	public void setC1(String c1) {
//		this.c1 = c1;
//	}
//	public String getC2() {
//		return c2;
//	}
//	public void setC2(String c2) {
//		this.c2 = c2;
//	}
//	public String getC3() {
//		return c3;
//	}
//	public void setC3(String c3) {
//		this.c3 = c3;
//	}
//	public String getC4() {
//		return c4;
//	}
//	public void setC4(String c4) {
//		this.c4 = c4;
//	}
//	public String getC5() {
//		return c5;
//	}
//	public void setC5(String c5) {
//		this.c5 = c5;
//	}
//	public String getC6() {
//		return c6;
//	}
//	public void setC6(String c6) {
//		this.c6 = c6;
//	}
//	public String getC7() {
//		return c7;
//	}
//	public void setC7(String c7) {
//		this.c7 = c7;
//	}
//	public String getC8() {
//		return c8;
//	}
//	public void setC8(String c8) {
//		this.c8 = c8;
//	}
	public PointStatus getNextPlayer() {
		return nextPlayer;
	}

	public void setNextPlayer(PointStatus nextPlayer) {
		this.nextPlayer = nextPlayer;
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


	
	
	
}
