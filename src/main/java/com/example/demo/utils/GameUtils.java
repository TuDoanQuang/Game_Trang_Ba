package com.example.demo.utils;

import com.example.demo.enums.PointName;
import com.example.demo.enums.PointStatus;
import com.example.demo.form.GameForm;

public class GameUtils {

	private GameUtils() {}
	
	public static boolean isPointLocked(GameForm gameForm, String point) {
		if (PointName.A1.name().equalsIgnoreCase(point) 
				|| PointName.A2.name().equalsIgnoreCase(point)
				|| PointName.A3.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A1)
					, gameForm.getPointMap().get(PointName.A2), gameForm.getPointMap().get(PointName.A3))) {
				return true;
			}
		}
		
		if (PointName.A3.name().equalsIgnoreCase(point) 
				|| PointName.A4.name().equalsIgnoreCase(point)
				|| PointName.A5.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A3)
					, gameForm.getPointMap().get(PointName.A4), gameForm.getPointMap().get(PointName.A5))) {
				return true;
			}
		}
		
		if (PointName.A5.name().equalsIgnoreCase(point) 
				|| PointName.A6.name().equalsIgnoreCase(point)
				|| PointName.A7.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A5)
					, gameForm.getPointMap().get(PointName.A6), gameForm.getPointMap().get(PointName.A7))) {
				return true;
			}
		}
		
		if (PointName.A7.name().equalsIgnoreCase(point) 
				|| PointName.A8.name().equalsIgnoreCase(point)
				|| PointName.A1.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A7)
					, gameForm.getPointMap().get(PointName.A8), gameForm.getPointMap().get(PointName.A1))) {
				return true;
			}
		}
		//======================================
		
		if (PointName.B1.name().equalsIgnoreCase(point) 
				|| PointName.B2.name().equalsIgnoreCase(point)
				|| PointName.B3.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.B1)
					, gameForm.getPointMap().get(PointName.B2), gameForm.getPointMap().get(PointName.B3))) {
				return true;
			}
		}
		
		if (PointName.B3.name().equalsIgnoreCase(point) 
				|| PointName.B4.name().equalsIgnoreCase(point)
				|| PointName.B5.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.B3)
					, gameForm.getPointMap().get(PointName.B4), gameForm.getPointMap().get(PointName.B5))) {
				return true;
			}
		}
		
		if (PointName.B5.name().equalsIgnoreCase(point) 
				|| PointName.B6.name().equalsIgnoreCase(point)
				|| PointName.B7.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.B5)
					, gameForm.getPointMap().get(PointName.B6), gameForm.getPointMap().get(PointName.B7))) {
				return true;
			}
		}
		
		if (PointName.B7.name().equalsIgnoreCase(point) 
				|| PointName.B8.name().equalsIgnoreCase(point)
				|| PointName.B1.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.B7)
					, gameForm.getPointMap().get(PointName.B8), gameForm.getPointMap().get(PointName.B1))) {
				return true;
			}
		}
		
	//============================================C area
		
		if (PointName.C1.name().equalsIgnoreCase(point) 
				|| PointName.C2.name().equalsIgnoreCase(point)
				|| PointName.C3.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.C1)
					, gameForm.getPointMap().get(PointName.C2), gameForm.getPointMap().get(PointName.C3))) {
				return true;
			}
		}
		
		if (PointName.C3.name().equalsIgnoreCase(point) 
				|| PointName.C4.name().equalsIgnoreCase(point)
				|| PointName.C5.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.C3)
					, gameForm.getPointMap().get(PointName.C4), gameForm.getPointMap().get(PointName.C5))) {
				return true;
			}
		}
		
		if (PointName.C5.name().equalsIgnoreCase(point) 
				|| PointName.C6.name().equalsIgnoreCase(point)
				|| PointName.C7.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.C5)
					, gameForm.getPointMap().get(PointName.C6), gameForm.getPointMap().get(PointName.C7))) {
				return true;
			}
		}
		
		if (PointName.C7.name().equalsIgnoreCase(point) 
				|| PointName.C8.name().equalsIgnoreCase(point)
				|| PointName.C1.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.C7)
					, gameForm.getPointMap().get(PointName.C8), gameForm.getPointMap().get(PointName.C1))) {
				return true;
			}
		}
		
		//======================================others
		
		if (PointName.A2.name().equalsIgnoreCase(point) 
				|| PointName.B2.name().equalsIgnoreCase(point)
				|| PointName.C2.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A2)
					, gameForm.getPointMap().get(PointName.B2), gameForm.getPointMap().get(PointName.C2))) {
				return true;
			}
		}
		
		if (PointName.A4.name().equalsIgnoreCase(point) 
				|| PointName.B4.name().equalsIgnoreCase(point)
				|| PointName.C4.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A4)
					, gameForm.getPointMap().get(PointName.B4), gameForm.getPointMap().get(PointName.C4))) {
				return true;
			}
		}
		
		if (PointName.A6.name().equalsIgnoreCase(point) 
				|| PointName.B6.name().equalsIgnoreCase(point)
				|| PointName.C6.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A6)
					, gameForm.getPointMap().get(PointName.B6), gameForm.getPointMap().get(PointName.C6))) {
				return true;
			}
		}
		if (PointName.A8.name().equalsIgnoreCase(point) 
				|| PointName.B8.name().equalsIgnoreCase(point)
				|| PointName.C8.name().equalsIgnoreCase(point)) {
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.A8)
					, gameForm.getPointMap().get(PointName.B8), gameForm.getPointMap().get(PointName.C8))) {
				return true;
			}
		}
		
		return false;
	}
	public static String getPlayerOnGame(PointStatus nextPlayer) {
		if (nextPlayer == null) {
			return "";
		}

		return PointStatus.PLAYER1 == nextPlayer ? PointStatus.PLAYER2.name() :  PointStatus.PLAYER1.name();
	}
	
	public static boolean updatePointSelected(GameForm gameForm, String pointSelection, boolean waitForEat, String valueChange) {
		
		if (StringUtils.isNotEmpty(pointSelection)) {
			if (gameForm.getPointMap().get(PointName.valueOf(pointSelection)) == null || waitForEat) {
				gameForm.getPointMap().put(PointName.valueOf(pointSelection), valueChange);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public static boolean isValidSelectPointToMove(GameForm gameForm, String pointSelection, String player) {
		if (gameForm.getPointMap().get(PointName.valueOf(pointSelection)) != null 
				&& gameForm.getPointMap().get(PointName.valueOf(pointSelection)).equals(player)) {
			return true;
		}
		
		return false;
	}
	
}
