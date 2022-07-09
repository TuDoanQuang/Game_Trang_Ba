package com.example.demo.utils;

import com.example.demo.enums.PointName;
import com.example.demo.enums.TrangBaPoints;
import com.example.demo.form.GameForm;

public class GameUtils {

	private GameUtils() {}
	
	public static boolean isPointLocked(GameForm gameForm) {
		String pointSelected = gameForm.getPointSelected();
		String round = pointSelected.substring(0, 1);
		int pointNumber = Integer.valueOf(pointSelected.substring(1));
		
		if (pointNumber ==  1 || pointNumber == 2 || pointNumber == 3) {
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.valueOf(round+1))
					, gameForm.getPointMap().get(PointName.valueOf(round+2))
					, gameForm.getPointMap().get(PointName.valueOf(round+3)))) {
				return true;
			}
		}
		
		if (pointNumber ==  3 || pointNumber == 4 || pointNumber == 5) {
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.valueOf(round+3))
					, gameForm.getPointMap().get(PointName.valueOf(round+4))
					, gameForm.getPointMap().get(PointName.valueOf(round+5)))) {
				return true;
			}
		}
		if (pointNumber ==  5 || pointNumber == 6 || pointNumber == 7) {
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.valueOf(round+5))
					, gameForm.getPointMap().get(PointName.valueOf(round+6))
					, gameForm.getPointMap().get(PointName.valueOf(round+7)))) {
				return true;
			}
		}
		
		if (pointNumber ==  7 || pointNumber == 8 || pointNumber == 1) {
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.valueOf(round+7))
					, gameForm.getPointMap().get(PointName.valueOf(round+8))
					, gameForm.getPointMap().get(PointName.valueOf(round+1)))) {
				return true;
			}
		}
		
		if (pointNumber == 2 || pointNumber == 4 || pointNumber == 6 || pointNumber == 8) {
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.valueOf("A" + pointNumber))
					, gameForm.getPointMap().get(PointName.valueOf("B" + pointNumber))
					, gameForm.getPointMap().get(PointName.valueOf("C" + pointNumber)))) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * check if 3 points then eat one
	 * @param gameForm
	 * @return
	 */
	public static boolean isPlayerCanEatOne(GameForm gameForm) {
		String[] rounds = {"A", "B" , "C"};
		String underLine = "_";
		for (String round : rounds) {
			String point1 = round + 1;
			String point2 = round + 2;
			String point3 = round + 3;
			String point4 = round + 4;
			String point5 = round + 5;
			String point6 = round + 6;
			String point7 = round + 7;
			String point8 = round + 8;
			
			String point123 = point1.concat(underLine).concat(point2).concat(underLine).concat(point3);
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.valueOf(point1))
					, gameForm.getPointMap().get(PointName.valueOf(point2))
					, gameForm.getPointMap().get(PointName.valueOf(point3))) 
					&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.valueOf(point123)))) {
				
				gameForm.getTrangBaStatus().put(TrangBaPoints.valueOf(point123), true);
				return true;
			}
			
			String point345 = point3.concat(underLine).concat(point4).concat(underLine).concat(point5);
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.valueOf(point3))
					, gameForm.getPointMap().get(PointName.valueOf(point4))
					, gameForm.getPointMap().get(PointName.valueOf(point5)))
					&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.valueOf(point345)))) {
				gameForm.getTrangBaStatus().put(TrangBaPoints.valueOf(point345), true);
				return true;
			}
			
			String point567 = point5.concat(underLine).concat(point6).concat(underLine).concat(point7);
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.valueOf(point5))
			, gameForm.getPointMap().get(PointName.valueOf(point6)), gameForm.getPointMap().get(PointName.valueOf(point7)))
			&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.valueOf(point567)))) {
				gameForm.getTrangBaStatus().put(TrangBaPoints.valueOf(point567), true);
				return true;
			}
			
			String point781 = point7.concat(underLine).concat(point8).concat(underLine).concat(point1);
			if (StringUtils.isAllEqual(gameForm.getPointMap().get(PointName.valueOf(point7))
					, gameForm.getPointMap().get(PointName.valueOf(point8)), gameForm.getPointMap().get(PointName.valueOf(point1)))
					&& !BooleanUtils.isTrue(gameForm.getTrangBaStatus().get(TrangBaPoints.valueOf(point781)))) {
				gameForm.getTrangBaStatus().put(TrangBaPoints.valueOf(point781), true);
				return true;
			}
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
	
	public static boolean isPointSelectedValid(GameForm gameForm, String pointSelection, boolean waitForEat, String valueChange) {
		
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
	
	public static boolean isValidSelectPointToMove(GameForm gameForm, String pointSelection) {
		if (gameForm.getPointMap().get(PointName.valueOf(pointSelection)) != null 
				&& gameForm.getPointMap().get(PointName.valueOf(pointSelection)).equals(gameForm.getCurrentPlayer().name())) {
			return true;
		}
		
		return false;
	}

	public static boolean isDestinationValid(GameForm gameForm, String pointWaitingToMove, String pointSelection) {
		// Point selected already selected before
		if (gameForm.getPointMap().get(PointName.valueOf(pointSelection)) != null) {
			return false;
		}
		
		String roundMoving = pointWaitingToMove.substring(0, 1);
		
		int pointNumerMoving = Integer.valueOf(pointWaitingToMove.substring(1));
		int pointNumerSelected = Integer.valueOf(pointSelection.substring(1));
		// If point selected is same round with the waiting point
		if (pointSelection.contains(roundMoving)) {
			if (pointNumerMoving == (pointNumerSelected - 1) || pointNumerMoving == (pointNumerSelected + 1)
					|| pointNumerMoving == (pointNumerSelected - 7) || pointNumerMoving == (pointNumerSelected + 7)) {
				return true;
			}
		} else {
			if (pointNumerMoving == pointNumerSelected 
					&& (pointNumerMoving == 2 || pointNumerMoving == 4|| pointNumerMoving == 6|| pointNumerMoving == 8)) {
				return true;
			}
		}
		
		return false;
	}
	
}
